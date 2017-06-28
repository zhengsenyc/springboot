package org.zhengsen.aspect;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.http.HttpServletRequest;

/**
 * Created by Administrator on 2017/6/27 0027.
 */
@Aspect
@Component
public class LogAspect {

    private static final Logger logger = LoggerFactory.getLogger(LogAspect.class);

    @Pointcut("execution(public * org.zhengsen.controller.GirlController.*(..))")
    public void log(){}

    @Before("log()")
    public void logBefore(JoinPoint joinPoint){
        ServletRequestAttributes attributes = (ServletRequestAttributes) RequestContextHolder.getRequestAttributes();
        HttpServletRequest request = attributes.getRequest();
        //url
        logger.info("url={}",request.getRequestURL());
        //method
        logger.info("method={}",request.getMethod());
        //客户端ip
        logger.info("ip={}",request.getRemoteAddr());
        /*请求的方法
            import org.aspectj.lang.JoinPoint
         * getDeclaringTypeName() : 获取到类名
         * getName() : 获取方法名
         */
        logger.info("class_method={}",joinPoint.getSignature().getDeclaringTypeName()+"."+joinPoint.getSignature().getName());
        //获取请求方法的参数
        logger.info("args={}",joinPoint.getArgs());
    }

    @After("log()")
    public void logAfter(){
        logger.info("22222222222");
    }

    /**
     * 记录方法的返回值
     * @param obj
     */
    @AfterReturning(returning = "obj",pointcut = "log()")
    public void afterReturning(Object obj){
        logger.info("response={}",obj);
    }
}
