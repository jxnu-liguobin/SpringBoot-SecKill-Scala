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
    private[domain] var id: Long = _

    //秒杀的商品id
    @BeanProperty
    private[domain] var goodsId: Long = _

    //秒杀数量
    @BeanProperty
    private[domain] var stockCount: Integer = _

    //秒杀开始日期
    @BeanProperty
    private[domain] var startDate: Date = _

    //秒杀结束日期
    @BeanProperty
    private[domain] var endDate: Date = _

    //秒杀价格
    @BeanProperty
    private[domain] var seckillPrice: Double = _

}