package com.weasleyclock.linebot.aspect;

import org.springframework.stereotype.Component;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import java.util.ArrayList;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

@Aspect
@Component
public class LoggingAspect{
    private final Logger LOGGER = LoggerFactory.getLogger(LoggingAspect.class);

    @Before("execution(* com.weasleyclock.linebot.*..*Service..*(..))")
    public void invokeApiControllerBefore(JoinPoint joinPoint) {
          outputLog(joinPoint);
    }
    // TODO 正式実装
    // ここではeventをほぞんする
    //@Before(value = "execution(* com.weasleyclock.linebot.handler.EchoHandler.handleTextMessageEvent(..))")
    public void invokeEventStore(JoinPoint joinPoint) {
        LOGGER.info("[event] Eventを受け取りました.");
    }
  
    //@AfterReturning(pointcut = "execution(* com.weasleyclock.linebot.*..*(..))", returning = "returnValue")
    public void invokeControllerAfter(JoinPoint joinPoint, Object returnValue) {
        outputLog(joinPoint, returnValue);
    } 

    
    private void outputLog(JoinPoint joinPoint) {
        String logMessage = "[" + getSessionId() + "]:" + getClassName(joinPoint) + "." + getSignatureName(joinPoint) + ":START:" + getArguments(joinPoint);
        LOGGER.info(logMessage);
    }
    private void outputLog(JoinPoint joinPoint, Object returnValue) {
        String logMessage = "[" + getSessionId() + "]:" + getClassName(joinPoint) + "." + getSignatureName(joinPoint) + ":END:" + getReturnValue(returnValue);
        LOGGER.info(logMessage);
    }
    private String getSessionId() {
        return ((ServletRequestAttributes) RequestContextHolder.getRequestAttributes()).getRequest().getSession().getId();
    }

    private String getClassName(JoinPoint joinPoint) {
        return joinPoint.getTarget().getClass().toString();
    }

    private String getSignatureName(JoinPoint joinPoint) {
        return joinPoint.getSignature().getName();
    }

    private String getArguments(JoinPoint joinPoint) {
        if (joinPoint.getArgs() == null) {
            return "argument is null";
        }

        Object[] arguments = joinPoint.getArgs();
        ArrayList<String> argumentStrings = new ArrayList<>();

        for (Object argument : arguments) {
            if (argument != null) {
                argumentStrings.add(argument.toString());
            }
        }
        return String.join(",", argumentStrings);
    }

    private String getReturnValue(Object returnValue) {
        return (returnValue != null) ? returnValue.toString() : "return value is null";
    }

}