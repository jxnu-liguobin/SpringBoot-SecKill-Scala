package cn.edu.jxnu.seckill.rabbitmq

import scala.language.implicitConversions
import scala.beans.BeanProperty
import cn.edu.jxnu.seckill.domain.SeckillUser

/**
 * RabbitMQ秒杀消息
 *
 * @author 梦境迷离.
 * @time 2018年5月21日
 * @version v1.0
 */
class SeckillMessage {

    @BeanProperty
    var user: SeckillUser = _

    @BeanProperty
    var goodsId: Long = _

}