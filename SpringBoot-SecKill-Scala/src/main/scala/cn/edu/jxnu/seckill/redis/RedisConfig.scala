package cn.edu.jxnu.seckill.redis

import org.springframework.stereotype.Component
import org.springframework.boot.context.properties.ConfigurationProperties
import scala.language.implicitConversions
import scala.beans.BeanProperty

/**
 * SpringBoot安全注入
 *
 *
 * @author 梦境迷离.
 * @time 2018年5月20日
 * @version v1.0
 */
@Component
@ConfigurationProperties(prefix = "redis")
class RedisConfig {

    @BeanProperty
    private[seckill] var host: String = _

    @BeanProperty
    private[seckill] var port: Integer = _

    // 秒
    @BeanProperty
    private[seckill] var timeout: Integer = _

    @BeanProperty
    private[seckill] var password: String = _

    @BeanProperty
    private[seckill] var poolMaxTotal: Integer = _

    @BeanProperty
    private[seckill] var poolMaxIdle: Integer = _

    // 秒
    @BeanProperty
    private[seckill] var poolMaxWait: Integer = _
}