package per.lkc.base.annotation;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * 定时任务分布式锁注解
 * <p>
 * </p>
 * DATE 2019/12/12.
 *
 * @author zhangjunbo.
 */
@Target(ElementType.METHOD)
@Retention(RetentionPolicy.RUNTIME)
public @interface RedisLockSchedule {
    /**
     * 锁的key
     *
     * @return
     */
    String key();

    /**
     * 等待时间
     *
     * @return
     */
    long waitTime();

    /**
     * 锁超时时间
     *
     * @return
     */
    long leaseTime();
}
