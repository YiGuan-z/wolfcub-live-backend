package com.cqsd.utils;

import org.aspectj.lang.ProceedingJoinPoint;
import org.springframework.core.annotation.AnnotationUtils;

import java.lang.annotation.Annotation;
import java.util.Arrays;

public class AnnotationUtil {
	/**
	 * 因为代理对象的特殊性质，必须使用Spring提供的AnnotationUtils.findAnnotation方法才能获取class对象上的真实对象
	 * 可以考虑在这里做一个缓冲池，使用方法对象当key，方法上的注解存入一个list
	 *
	 * @param proceedingJoinPoint
	 * @param clazz
	 * @param <A>
	 * @return
	 */
	public static <A extends Annotation> A getAnnotationFormProceedingJoinPoint(ProceedingJoinPoint proceedingJoinPoint, Class<A> clazz) {
		try {
			final var mothodName = proceedingJoinPoint.getSignature().getName();
			final var types = Arrays.stream(proceedingJoinPoint.getArgs()).map(Object::getClass).toArray(Class<?>[]::new);
			final var method = clazz.getDeclaredMethod(mothodName, types);
			return AnnotationUtils.findAnnotation(method, clazz);
		} catch (Exception e) {
			return null;
		}
	}
}
