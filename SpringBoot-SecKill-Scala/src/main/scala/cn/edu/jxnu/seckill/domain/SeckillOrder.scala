package cn.edu.jxnu.seckill.domain
import scala.language.implicitConversions
import scala.beans.BeanProperty
import java.util.Date

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
    private[domain] var id: Long = _

    //用户id
    @BeanProperty
    private[domain] var userId: Long = _

    //订单id
    @BeanProperty
    private[domain] var orderId: Long = _

    //商品id
    @BeanProperty
    private[domain] var goodsId: Long = _

}