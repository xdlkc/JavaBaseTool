package per.lkc.base.dingtalk;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;

/**
 * 描述用途
 * <p>
 * </p>
 * DATE 2019/12/20.
 *
 * @author zhangjunbo.
 */
@Data
@ConfigurationProperties(prefix = "ding")
public class DingTalkProps {
    private String accessToken;
    private String secret;
}
