package per.lkc.base.str;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.codec.binary.Base64;

import javax.crypto.Mac;
import javax.crypto.spec.SecretKeySpec;
import java.net.URLEncoder;
import java.util.Arrays;

/**
 * 描述用途
 * <p>
 * </p>
 * DATE 2019/12/18.
 *
 * @author zhangjunbo.
 */
@Slf4j
public class CompressUtil {
    public static String getUrl() {
        String url = "https://oapi.dingtalk" +
                ".com/robot/send?access_token=58c258a030f3dec9d1e482c547b549160c2f4ef7c5899c4dd883859e490a6ec8";
        String secret = "SECc37b5bab07f0b3f5c34f2a7a0940e5698b3a65fe57a9f4fe334c1fefac430d5d";
        Long timestamp = System.currentTimeMillis();
        String sign = getSign(secret, timestamp);
        url = url + "&timestamp=" + timestamp + "&sign=" + sign;
        return url;
    }

    public static String getSign(String secret, Long timestamp) {
        try {
            String stringToSign = timestamp + "\n" + secret;
            Mac mac = Mac.getInstance("HmacSHA256");
            mac.init(new SecretKeySpec(secret.getBytes("UTF-8"), "HmacSHA256"));
            byte[] signData = mac.doFinal(stringToSign.getBytes("UTF-8"));
            return URLEncoder.encode(new String(Base64.encodeBase64(signData)), "UTF-8");
        } catch (Exception e) {
            log.error("get sign err", e);
            return "";
        }
    }
}
