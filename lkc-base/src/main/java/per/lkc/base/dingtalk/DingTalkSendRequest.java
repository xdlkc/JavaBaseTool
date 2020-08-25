package per.lkc.base.dingtalk;

import lombok.Builder;
import lombok.Data;

import java.util.List;

/**
 * 描述用途
 * <p>
 * </p>
 * DATE 2019/12/20.
 *
 * @author zhangjunbo.
 */
@Data
public class DingTalkSendRequest {
    private String actionCard;
    private At at;
    private String feedCard;
    private String link;
    private String markdown;
    private String msgtype;
    private String text;

    @Data
    public static class Text {
        private String content;
    }

    @Builder
    @Data
    public static class At {
        private List<String> atMobiles;
        private Boolean isAtAll;
    }
}
