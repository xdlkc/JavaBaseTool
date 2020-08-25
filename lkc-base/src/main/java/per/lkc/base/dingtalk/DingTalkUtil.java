package per.lkc.base.dingtalk;

import lombok.extern.slf4j.Slf4j;
import org.apache.commons.codec.binary.Base64;
import org.apache.commons.lang3.StringUtils;
import org.apache.http.client.utils.URIBuilder;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.web.client.RestTemplate;
import per.lkc.base.constant.CommonConstant;
import per.lkc.base.dingtalk.DingTalkSendRequest.Text;
import per.lkc.base.util.JsonUtil;

import javax.crypto.Mac;
import javax.crypto.spec.SecretKeySpec;
import java.net.URLEncoder;
import java.nio.charset.StandardCharsets;

/**
 * 描述用途
 * <p>
 * </p>
 * DATE 2019/12/19.
 *
 * @author zhangjunbo.
 */
@Slf4j
public class DingTalkUtil {

    /**
     * 向顶顶群推送消息
     *
     * @param msg
     * @return
     */
    public static boolean error(String msg, String token, String secret) {
        if (StringUtils.isEmpty(token)) {
            log.warn("no token configured, cannot push ding talk message");
            return false;
        }
        // 地址唯一，所以不配置环境变量
        String url = "https://oapi.dingtalk.com/robot/send";
        RestTemplate template = new RestTemplate();
        HttpHeaders headers = new HttpHeaders();
        headers.add(HttpHeaders.CONTENT_TYPE, CommonConstant.APPLICATION_JSON_UTF8_VALUE);
        HttpEntity<String> request = new HttpEntity<>(buildRequestBody(msg, DingTalkTypeEnum.DING_TALK_ERROR_MSG),
                headers);

        try {
            if (StringUtils.isNotEmpty(secret)) {
                Long timestamp = System.currentTimeMillis();
                String sign = getSign(secret, timestamp);
                url = new URIBuilder(url).addParameter("access_token", token)
                        .addParameter("sign", URLEncoder.encode(sign, "UTF-8"))
                        .addParameter("timestamp", String.valueOf(timestamp)).build().toString();
            } else {
                url = new URIBuilder(url).addParameter("access_token", token).build().toString();
            }
            String responseResponseEntity = template.postForObject(url, request, String.class);
            DingTalkSendResponse response = JsonUtil.parseObject(responseResponseEntity, DingTalkSendResponse.class);
            if (response == null) {
                log.warn("ding talk push msg err, response is null");
                return false;
            }
            if (!response.isSuccess()) {
                log.warn("ding talk push msg err, msg:{}", response.getErrmsg());
                return false;
            }
            return true;
        } catch (Exception e) {
            log.warn("parse ding talk response err:", e);
            return false;
        }
    }

    /**
     * 构造请求body
     *
     * @param msg
     * @param typeEnum
     * @return
     */
    public static String buildRequestBody(String msg, DingTalkTypeEnum typeEnum) {
        Text text = new Text();
        text.setContent(buildContent(msg, typeEnum.getDesc()));

        DingTalkSendRequest robotSendRequest = new DingTalkSendRequest();
        robotSendRequest.setMsgtype(DingTalkMsgTypeEnum.TEXT.getType());
        robotSendRequest.setText(JsonUtil.toJSONString(text));
        return JsonUtil.toJSONString(robotSendRequest);
    }

    /**
     * 构造消息内容
     *
     * @param msg
     * @param type
     * @return
     */
    private static String buildContent(String msg, String type) {
        return msg;
    }

    /**
     * 获取签名，若未设置则不用获取
     *
     * @param secret
     * @param timestamp
     * @return
     */
    public static String getSign(String secret, Long timestamp) {
        try {
            String stringToSign = timestamp + "\n" + secret;
            Mac mac = Mac.getInstance("HmacSHA256");
            mac.init(new SecretKeySpec(secret.getBytes(StandardCharsets.UTF_8), "HmacSHA256"));
            byte[] signData = mac.doFinal(stringToSign.getBytes(StandardCharsets.UTF_8));
            return new String(Base64.encodeBase64(signData));
        } catch (Exception e) {
            log.error("get sign err", e);
            return "";
        }
    }
}
