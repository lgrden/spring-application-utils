package io.wegetit.sau.log.execution;

import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.reflect.MethodSignature;

@Aspect
@Slf4j
public class ExecutionLoggerService {

    @Around(value = "@annotation(LogExecution)")
    public Object log(ProceedingJoinPoint joinPoint) throws Throwable {
        long start = System.currentTimeMillis();
        Object result = null;
        Throwable throwable = null;
        try {
            result = joinPoint.proceed();
        } catch (Throwable t) {
            throwable = t;
        }
        long end = System.currentTimeMillis();
        String methodSignature = joinPoint.getTarget().getClass().getSimpleName() + "." + ((MethodSignature) joinPoint.getSignature()).getMethod().getName();
        log.info("{} executed with {} in {} ms.", methodSignature, (throwable == null ? "SUCCESS" : "ERROR"), (end - start));
        if (throwable != null) {
            throw throwable;
        }
        return result;
    }
}
