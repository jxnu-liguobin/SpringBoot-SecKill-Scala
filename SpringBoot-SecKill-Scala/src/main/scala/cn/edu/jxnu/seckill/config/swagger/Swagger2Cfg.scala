package cn.edu.jxnu.seckill.config.swagger

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.builders.ParameterBuilder;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.schema.ModelRef;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.service.Parameter;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

/**
 * swagger配置
 *
 * @author 梦境迷离.
 * @time 2018年5月28日
 * @version v1.0
 */
@Configuration
@EnableSwagger2
class Swagger2Cfg {

    /**
     * swagger2的配置文件，这里可以配置swagger2的一些基本的内容，比如扫描的包等等
     *
     * @author 梦境迷离.
     * @time 2018年5月28日
     * @version v1.0
     * @return
     */
    @Bean
    def createRestApi() = new Docket(DocumentationType.SWAGGER_2).apiInfo(apiInfo())
        .select().apis(RequestHandlerSelectors.basePackage("cn.edu.jxnu.seckill.controller"))
        .paths(PathSelectors.any()).build()

    private def apiInfo() = new ApiInfoBuilder()
        // 页面标题
        .title("SpringBoot-Base-System-Document")
        // 创建人
        .description("梦境迷离：https://github.com/jxnu-liguobin").termsOfServiceUrl("https://github.com/jxnu-liguobin")
        // 创建人
        .contact("梦境迷离，注意：测试秒杀接口之前，需要调用reset重置秒杀数据")
        // 版本号
        .version("1.0").build()

}