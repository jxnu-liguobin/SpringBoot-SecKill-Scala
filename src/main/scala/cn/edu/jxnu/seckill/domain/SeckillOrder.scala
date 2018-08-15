package cn.edu.jxnu.seckill.domain

import scala.beans.BeanProperty
import scala.language.implicitConversions

/**
 * 秒杀订单
 *
 * @author 梦境迷离.
 * @time 2018年5月19日
 * @version v1.0
 */
class SeckillOrder {

    //秒杀订单实体 id
    @BeanProperty
    private[seckill] var id: Long = _

    //用户id
    @BeanProperty
    private[seckill] var userId: Long = _

    //订单id
    @BeanProperty
    private[seckill] var orderId: Long = _

    //商品id
    @BeanProperty
    private[seckill] var goodsId: Long = _

    override def toString(): String = {
        "SeckillOrder [id=" + id + ", userId=" + userId + ", orderId=" + orderId + ", goodsId=" + goodsId + "]"
    }

}