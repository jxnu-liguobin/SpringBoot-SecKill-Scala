package cn.edu.jxnu.seckill.config;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.method.support.HandlerMethodArgumentResolver;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;

import cn.edu.jxnu.seckill.access.AccessInterceptor;

@Configuration
public class WebConfig extends WebMvcConfigurerAdapter {

	@Autowired
	private UserArgumentResolver userArgumentResolver;

	@SuppressWarnings("unused")
	@Autowired
	private LoginInterceptor loginInterceptor;

	@Autowired
	private AccessInterceptor accessInterceptor;

	@Override
	public void addArgumentResolvers(List<HandlerMethodArgumentResolver> argumentResolvers) {

		argumentResolvers.add(userArgumentResolver);
	}

	@Override
	public void addInterceptors(InterceptorRegistry registry) {
		// registry.addInterceptor(loginInterceptor);
		registry.addInterceptor(accessInterceptor);
	}

	@Override
	public void addResourceHandlers(ResourceHandlerRegistry registry) {
		registry.addResourceHandler("/**").addResourceLocations("classpath:/static/");
		registry.addResourceHandler("swagger-ui.html").addResourceLocations("classpath:/META-INF/resources/");
		super.addResourceHandlers(registry);
	}
}
