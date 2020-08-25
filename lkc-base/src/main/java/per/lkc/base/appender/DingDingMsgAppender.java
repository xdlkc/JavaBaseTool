package per.lkc.base.appender;

import ch.qos.logback.classic.Level;
import ch.qos.logback.classic.spi.ILoggingEvent;
import ch.qos.logback.classic.spi.IThrowableProxy;
import ch.qos.logback.classic.spi.StackTraceElementProxy;
import ch.qos.logback.classic.spi.ThrowableProxy;
import ch.qos.logback.core.UnsynchronizedAppenderBase;
import lombok.Setter;
import org.apache.commons.lang3.StringUtils;
import per.lkc.base.dingtalk.DingTalkUtil;

/**
 * 自动推送异常日志到钉钉群
 * @author ZhangJunbo
 */
@Setter
public class DingDingMsgAppender extends UnsynchronizedAppenderBase<ILoggingEvent> {
    private String token;
    private String secret;

    @Override
    public void append(ILoggingEvent event) {
        if (event.getLevel() == Level.ERROR) {
            IThrowableProxy iThrowableProxy = event.getThrowableProxy();
            StringBuilder sb = new StringBuilder();
            if (iThrowableProxy instanceof ThrowableProxy) {
                ThrowableProxy throwableProxy = (ThrowableProxy) iThrowableProxy;
                Throwable throwable = throwableProxy.getThrowable();
                String throwableMsg = throwable.getMessage();
                StackTraceElementProxy[] stackTraceElementProxy = iThrowableProxy.getStackTraceElementProxyArray();
                sb.append(event.getFormattedMessage()).append("\n");
                if (StringUtils.isNotEmpty(throwableMsg)) {
                    sb.append(throwableMsg).append("\n");
                }
                int i = 0;
                for (StackTraceElementProxy proxy : stackTraceElementProxy) {
                    //只打印一部分堆栈信息
                    if (i < 5) {
                        sb.append(proxy.getSTEAsString()).append("\n");
                    } else {
                        break;
                    }
                    i++;
                }
            } else {
                sb.append(event.getFormattedMessage());
            }
            String msg = sb.toString();
            // if (StringUtils.isNotEmpty(msg)) {
            //     DingTalkUtil.error(msg, token, secret);
            // }
        }
    }

}