package io.wegetit.sau.core.log.exception;

import io.wegetit.sau.shared.utils.ExceptionUtils;
import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.reflect.MethodSignature;

@Aspect
@Slf4j
public class ExceptionLoggerService {

    @Around(value = "@annotation(LogException)")
    public Object log(ProceedingJoinPoint joinPoint) throws Throwable {
        Object result = null;
        try {
            result = joinPoint.proceed();
        } catch (Throwable t) {
            String methodSignature = joinPoint.getTarget().getClass().getSimpleName() + "." + ((MethodSignature) joinPoint.getSignature()).getMethod().getName();
            log.warn("{} executed with error: {}.", methodSignature, ExceptionUtils.convertToLineTrace(t));
        }
        return result;
    }
}
