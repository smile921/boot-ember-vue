/**
 * 
 */
package org.smile921.app.conf;

import java.util.Arrays;

import javax.servlet.http.HttpServletRequest;

import org.apache.log4j.Logger;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.AfterReturning;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

/**
 * @author Administrator
 *
 */
@Aspect
@Order(5)
@Component
public class WebLogAspect {

	 private Logger LOG = Logger.getLogger(getClass());

	    ThreadLocal<Long> startTime = new ThreadLocal<>();

	    @Pointcut("execution(public * org.smile921.app.ember.controller..*.*(..))")
	    public void webLog(){}

	    @Before("webLog()")
	    public void doBefore(JoinPoint joinPoint) throws Throwable {
	        startTime.set(System.currentTimeMillis());
	        ServletRequestAttributes attributes = (ServletRequestAttributes) RequestContextHolder.getRequestAttributes();
	        HttpServletRequest request = attributes.getRequest();
	        LOG.info("URL : " + request.getRequestURL().toString());
	        LOG.info("HTTP_METHOD : " + request.getMethod());
	        LOG.info("IP : " + request.getRemoteAddr());
	        LOG.info("CLASS_METHOD : " + joinPoint.getSignature().getDeclaringTypeName() + "." + joinPoint.getSignature().getName());
	        LOG.info("ARGS : " + Arrays.toString(joinPoint.getArgs()));
	    }

	    @AfterReturning(returning = "ret", pointcut = "webLog()")
	    public void doAfterReturning(Object ret) throws Throwable {
	        LOG.info("RESPONSE : " + ret);
	        LOG.info("SPEND TIME : " + (System.currentTimeMillis() - startTime.get()));
	    }

}
