package cn.edu.jxnu.seckill.config

import scala.language.implicitConversions
import scala.collection.JavaConversions._
import scala.collection.immutable.{ List => _, _ }
import java.util.{ List => JavaList }
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.context.annotation.Configuration
import org.springframework.web.method.support.HandlerMethodArgumentResolver
import org.springframework.web.servlet.config.annotation.InterceptorRegistry
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter
import cn.edu.jxnu.seckill.access.AccessInterceptor
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry

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
        argumentResolvers.add(userArgumentResolver)
    }

    override def addInterceptors(registry: InterceptorRegistry) {
        registry.addInterceptor(accessInterceptor)
    }

    /**
     * addResourceLocations是必须的，否则swagger被拦截
     */
    override def addResourceHandlers(registry: ResourceHandlerRegistry) {
        registry.addResourceHandler("/**").addResourceLocations("classpath:/static/")
        registry.addResourceHandler("swagger-ui.html")
            .addResourceLocations("classpath:/META-INF/resources/")
        super.addResourceHandlers(registry)
    }

}