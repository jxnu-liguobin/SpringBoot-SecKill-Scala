package cn.edu.jxnu.seckill.config

import java.util.{List => JavaList}

import cn.edu.jxnu.seckill.access.AccessInterceptor
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.context.annotation.Configuration
import org.springframework.web.method.support.HandlerMethodArgumentResolver
import org.springframework.web.servlet.config.annotation.{InterceptorRegistry, ResourceHandlerRegistry, WebMvcConfigurerAdapter}

import scala.collection.immutable.{List => _}
import scala.language.implicitConversions

/**
 * 拦截器注册
 *
 * @author 梦境迷离.
 * @time 2018年5月22日
 * @version v1.0
 */
@Configuration
class WebConfig @Autowired() (userArgumentResolver: UserArgumentResolver, accessInterceptor: AccessInterceptor)
    extends WebMvcConfigurerAdapter {

    override def addArgumentResolvers(argumentResolvers: JavaList[HandlerMethodArgumentResolver]) {
        //注册用户参数解析器
        argumentResolvers.add(userArgumentResolver)
    }

    override def addInterceptors(registry: InterceptorRegistry) {
        //注册访问限制拦截器
        registry.addInterceptor(accessInterceptor)
    }

    /**
     * addResourceLocations是必须的，否则swagger被拦截
     */
    override def addResourceHandlers(registry: ResourceHandlerRegistry) {
        
        registry.addResourceHandler("/**").addResourceLocations("classpath:/static/")
        registry.addResourceHandler("swagger-ui.html").addResourceLocations("classpath:/META-INF/resources/")
        super.addResourceHandlers(registry)
    }

}