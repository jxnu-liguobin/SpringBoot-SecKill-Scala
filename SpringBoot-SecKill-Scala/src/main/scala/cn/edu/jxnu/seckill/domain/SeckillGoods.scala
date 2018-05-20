package cn.edu.jxnu.seckill.domain

import scala.language.implicitConversions
import scala.beans.BeanProperty
import java.util.Date

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
    var id: Long = _

    //秒杀的商品id
    @BeanProperty
    var goodsId: Long = _

    //秒杀数量
    @BeanProperty
    var stockCount: Integer = _

    //秒杀开始日期
    @BeanProperty
    var startDate: Date = _

    //秒杀结束日期
    @BeanProperty
    var endDate: Date = _

    //秒杀价格
    @BeanProperty
    var seckillPrice: Double = _

    override def toString(): String = {
        "SeckillGoods [id=" + id + ", goodsId=" + goodsId + ", stockCount=" + stockCount + ", startDate=" +
            startDate + ", endDate=" + endDate + ", seckillPrice=" + seckillPrice + "]"
    }

}