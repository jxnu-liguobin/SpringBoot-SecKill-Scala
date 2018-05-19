package cn.edu.jxnu.seckill.domain
import scala.language.implicitConversions
import scala.beans.BeanProperty
import java.util.Date

/**
 * 订单信息
 *
 * @author 梦境迷离.
 * @time 2018年5月19日
 * @version v1.0
 */
class OrderInfo {

    //订单id
    @BeanProperty
    private[domain] var id: Long = _

    //下单用户id
    @BeanProperty
    private[domain] var userId: Long = _

    //商品id
    @BeanProperty
    private[domain] var goodsId: Long = _

    //交付地址id
    @BeanProperty
    private[domain] var deliveryAddrId: Long = _

    //商品名称
    @BeanProperty
    private[domain] var goodsName: String = _

    //商品数量
    @BeanProperty
    private[domain] var goodsCount: Integer = _

    //商品价格
    @BeanProperty
    private[domain] var goodsPrice: Double = _

    //订单通道
    @BeanProperty
    private[domain] var orderChannel: Integer = _

    //订单状态
    @BeanProperty
    private[domain] var status: Integer = _

    //创建日期
    @BeanProperty
    private[domain] var createDate: Date = _

    //支付日期
    @BeanProperty
    private[domain] var payDate: Date = _
}