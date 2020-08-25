package per.lkc.base.dingtalk;

import lombok.Data;

/**
 * 描述用途
 * <p>
 * </p>
 * DATE 2019/12/23.
 *
 * @author zhangjunbo.
 */
@Data
public class DingTalkSendContent {
    private String app;
    private String profile;
    private String content;
    private String type;
    private String ip;

    @Override
    public String toString() {
        return "服务名：" + app
                + "\n服务ip：" + ip
                + "\n环境：" + profile
                + "\n消息类型：" + type
                + "\n消息内容：" + content;
    }
}
