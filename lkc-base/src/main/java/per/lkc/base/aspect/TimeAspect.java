package per.lkc.base.aspect;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

/**
 * 查看方法耗时
 * <p>
 * </p>
 * DATE 2019/12/9.
 *
 * @author zhangjunbo.
 */
@Aspect
@Component
public class TimeAspect {
    /**
     * 标识注解
     */
    @Pointcut("@annotation(per.lkc.base.annotation.Timer)")
    private void pointcut() {
    }

    @Around("pointcut()")
    public Object around(ProceedingJoinPoint joinPoint) throws Throwable {
        // 获取目标Logger
        Logger logger = LoggerFactory.getLogger(joinPoint.getTarget().getClass());

        // 获取目标类名称
        String clazzName = joinPoint.getTarget().getClass().getName();

        // 获取目标类方法名称
        String methodName = joinPoint.getSignature().getName();

        long start = System.currentTimeMillis();
        logger.info("{}: {}: start...", clazzName, methodName);

        // 调用目标方法
        Object result = joinPoint.proceed();

        long time = System.currentTimeMillis() - start;
        logger.info("{}: {}: : end... cost time: {} ms", clazzName, methodName, time);

        return result;
    }
}
