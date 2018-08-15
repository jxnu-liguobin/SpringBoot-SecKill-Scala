package cn.edu.jxnu.seckill.rabbitmq

import cn.edu.jxnu.seckill.domain.SeckillUser

import scala.beans.BeanProperty
import scala.language.implicitConversions

/**
 * RabbitMQ秒杀消息
 *
 * @author 梦境迷离.
 * @time 2018年5月21日
 * @version v1.0
 */
class SeckillMessage {

    @BeanProperty
    private[seckill] var user: SeckillUser = _

    @BeanProperty
    private[seckill] var goodsId: Long = _

}