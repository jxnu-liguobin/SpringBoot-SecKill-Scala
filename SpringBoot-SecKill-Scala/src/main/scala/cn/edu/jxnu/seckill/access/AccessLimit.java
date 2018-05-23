package cn.edu.jxnu.seckill.access;

import static java.lang.annotation.ElementType.METHOD;
import static java.lang.annotation.RetentionPolicy.RUNTIME;

import java.lang.annotation.Retention;
import java.lang.annotation.Target;

/**
 * 限流访问注解
 * 
 * Java
 *
 * @author 梦境迷离.
 * @time 2018年5月20日
 * @version v1.0
 */
@Retention(RUNTIME)
@Target(METHOD)
public @interface AccessLimit {
	int seconds();

	int maxCount();

	boolean needLogin() default true;
}
