package com.cqsd.logger;

import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.core.annotation.AnnotationUtils;
import org.springframework.stereotype.Component;

import java.util.Arrays;


@Aspect
@Component
@Slf4j
public class Logger {
	
	
	@Pointcut("@annotation(com.cqsd.logger.Log))")
	public void pointCut() {
	}
	
	@Around("pointCut()")
	public Object logSave(ProceedingJoinPoint pjp) throws Throwable {
		return null;
	}
	
	private void handleErr(Throwable throwable) {
	
	}
	
	/**
	 * 因为CGLIB代理对象的特殊性 必须使用Spring提供的AnnotaionUtils.findAnnotation()方法才能拿到注解对象
	 *
	 * @param pjp
	 * @return
	 * @throws NoSuchMethodException
	 */
	private Log getLogAnnotation(ProceedingJoinPoint pjp) {
		try {
			final var mothodName = pjp.getSignature().getName();
			final var args = pjp.getArgs();
			final var types = Arrays.stream(args).map(Object::getClass).toArray(Class[]::new);
			final Class<?> clazz = pjp.getThis().getClass();
			final var method = clazz.getDeclaredMethod(mothodName, types);
			return AnnotationUtils.findAnnotation(method, Log.class);
		} catch (NoSuchMethodException e) {
			handleErr(e);
			return null;
		}
		
	}
	
}
