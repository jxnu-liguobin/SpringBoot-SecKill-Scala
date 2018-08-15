package cn.edu.jxnu.seckill.domain

import java.util.Date

import scala.beans.BeanProperty
import scala.language.implicitConversions

/**
 * 秒杀商品
 *
 * @author 梦境迷离.
 * @time 2018年5月19日
 * @version v1.0
 */
class SeckillGoods {

    //秒杀商品实体的id
    @BeanProperty
    private[seckill] var id: Long = _

    //秒杀的商品id
    @BeanProperty
    private[seckill] var goodsId: Long = _

    //秒杀数量
    @BeanProperty
    private[seckill] var stockCount: Integer = _

    //秒杀开始日期
    @BeanProperty
    private[seckill] var startDate: Date = _

    //秒杀结束日期
    @BeanProperty
    private[seckill] var endDate: Date = _

    //秒杀价格
    @BeanProperty
    private[seckill] var seckillPrice: Double = _

    override def toString(): String = {
        "SeckillGoods [id=" + id + ", goodsId=" + goodsId + ", stockCount=" + stockCount + ", startDate=" +
            startDate + ", endDate=" + endDate + ", seckillPrice=" + seckillPrice + "]"
    }

}