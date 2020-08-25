// package per.lkc.base.aspects;
//
// import org.aspectj.lang.ProceedingJoinPoint;
// import org.aspectj.lang.annotation.Around;
// import org.aspectj.lang.annotation.Aspect;
// import org.aspectj.lang.annotation.Pointcut;
// import org.slf4j.Logger;
// import org.slf4j.LoggerFactory;
// import org.springframework.beans.factory.annotation.Autowired;
// import org.springframework.stereotype.Component;
// import per.lkc.base.annotations.RedisLockSchedule;
//
// /**
//  * 定时任务分布式锁切面
//  * <p>
//  * </p>
//  * DATE 2019/12/12.
//  *
//  * @author zhangjunbo.
//  */
// @Aspect
// @Component
// public class RedisLockScheduleAspect {
//     @Autowired
//     private RedissonLockService redissonLockService;
//
//     /**
//      * 标识注解
//      */
//     @Pointcut("@annotation(per.lkc.base.annotations.RedisLockSchedule)")
//     private void pointcut() {
//     }
//
//     @Around("pointcut()&&@annotation(schedule)")
//     public Object around(ProceedingJoinPoint joinPoint, RedisLockSchedule schedule) throws Throwable {
//         // 获取目标Logger
//         Logger logger = LoggerFactory.getLogger(joinPoint.getTarget().getClass());
//         // 获取目标类方法名称
//         String methodName = joinPoint.getSignature().getName();
//         logger.info("***********************{} start************************", methodName);
//
//
//         String key = RedisUtil.getkey(schedule.key());
//         boolean lockResult;
//         try {
//             lockResult = redissonLockService.tryLock(key, schedule.waitTime(), schedule.leaseTime());
//             if (lockResult) {
//                 long start = System.currentTimeMillis();
//                 // 调用目标方法
//                 Object result = joinPoint.proceed();
//                 long time = System.currentTimeMillis() - start;
//                 logger.info("***********************{} end cost:{}ms************************", methodName, time);
//                 return result;
//             } else {
//                 logger.info("***********************{} lock fail end************************", methodName);
//                 return null;
//             }
//         } catch (Exception e) {
//             logger.error(e.getMessage(), e);
//             logger.info("***********************{} error************************", methodName);
//             return null;
//         } finally {
//             redissonLockService.unlock(key);
//         }
//     }
// }
