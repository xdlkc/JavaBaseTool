package per.lkc.base.dingtalk;

import lombok.Data;

@Data
public class DingTalkSendResponse {
    private Long errcode;
    private String errmsg;

    public DingTalkSendResponse() {
    }

    public boolean isSuccess() {
        return this.getErrcode() == null || this.getErrcode().equals(0L);
    }
}