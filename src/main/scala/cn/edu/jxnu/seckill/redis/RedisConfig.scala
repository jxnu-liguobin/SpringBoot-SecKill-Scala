package cn.edu.jxnu.seckill.redis

import org.springframework.boot.context.properties.ConfigurationProperties
import org.springframework.stereotype.Component

import scala.beans.BeanProperty
import scala.language.implicitConversions

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