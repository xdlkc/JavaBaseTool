package per.lkc.base.dingtalk;

import lombok.AllArgsConstructor;
import lombok.Getter;

/**
 * 描述用途
 * <p>
 * </p>
 * DATE 2019/12/23.
 *
 * @author zhangjunbo.
 */
@AllArgsConstructor
@Getter
public enum DingTalkMsgTypeEnum {
    MARKDOWN("markdown"),
    ACTION_CARD("actionCard"),
    FEED_CARD("feedCard"),
    LINK("link"),
    TEXT("text");

    private String type;

}
