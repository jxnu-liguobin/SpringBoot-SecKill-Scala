package cn.edu.jxnu.seckill.redis

import org.springframework.stereotype.Component
import org.springframework.boot.context.properties.ConfigurationProperties
import scala.language.implicitConversions
import scala.beans.BeanProperty

/**
 * SpringBoot安全注入
 *
 * @author 梦境迷离.
 * @time 2018年5月20日
 * @version v1.0
 */
@Component
@ConfigurationProperties(prefix = "redis")
class RedisConfig {

    @BeanProperty
    var host: String = _

    @BeanProperty
    var port: Integer = _

    // 秒
    @BeanProperty
    var timeout: Integer = _

    @BeanProperty
    var password: String = _

    @BeanProperty
    var poolMaxTotal: Integer = _

    @BeanProperty
    var poolMaxIdle: Integer = _

    // 秒
    @BeanProperty
    var poolMaxWait: Integer = _
}