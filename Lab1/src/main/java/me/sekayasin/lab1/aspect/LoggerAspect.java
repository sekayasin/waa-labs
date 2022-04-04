package me.sekayasin.lab1.aspect;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.stereotype.Component;

@Aspect
@Component
public class LoggerAspect {

    private static final Logger logger = LogManager.getLogger(LoggerAspect.class.getName());

    @Pointcut("@annotation(me.sekayasin.lab1.aspect.annotations.ExecutionTime)")
    public void logExecutionTime() {

    }

//    @Pointcut("execution(* me.sekayasin.lab1.controller.UserController.findById(..))")
//    public void logUserById(){
//
//    }

    @Around("logExecutionTime()")
    public Object executionTime(ProceedingJoinPoint point) throws Throwable {
        long startTime = System.currentTimeMillis();
        Object object = point.proceed();
        long endTime = System.currentTimeMillis();
        logger.info("Class Name: " + point.getSignature().getDeclaringTypeName() +
                ". Method Name: "+ point.getSignature().getName() + ". Time taken for Execution is: " +
                (endTime - startTime) + "ms");
        return object;
    }

}
