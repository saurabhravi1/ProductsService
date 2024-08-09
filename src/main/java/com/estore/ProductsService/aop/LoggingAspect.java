package com.estore.ProductsService.aop;

import java.time.Duration;
import java.time.Instant;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

@Component
@Aspect
public class LoggingAspect {

	private static final Logger logger = LoggerFactory.getLogger(LoggingAspect.class);

	@Around("execution (* com.estore.ProductsService.**.*(..))")
	public Object log(ProceedingJoinPoint joinPoint) throws Throwable {
		logger.info(joinPoint.getSignature().toString() + " method execution starts");
		Instant start = Instant.now();
		Object result = joinPoint.proceed();
		Instant end = Instant.now();
		logger.info("Time Taken: {} ms", Duration.between(start, end).toMillis());
		logger.info(joinPoint.getSignature().toString() + " method execution ends");
		return result;
	}
}
