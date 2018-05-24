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
 * 与反射相关功能类似，到目前版本(Scala 2.12)为止，注解相关功能依然是Expermental(实验性)的，注解相关API一直处于变化中。
 * 
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
