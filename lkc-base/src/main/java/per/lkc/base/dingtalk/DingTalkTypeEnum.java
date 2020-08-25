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
public enum DingTalkTypeEnum {
    DING_TALK_NORMAL_MSG("DING_TALK_NORMAL_MSG", "普通消息"),
    DING_TALK_SCHEDULE_MSG("DING_TALK_SCHEDULE_MSG", "定时任务"),
    DING_TALK_ERROR_MSG("DING_TALK_ERROR_MSG", "异常日志");
    private String type;
    private String desc;
}
