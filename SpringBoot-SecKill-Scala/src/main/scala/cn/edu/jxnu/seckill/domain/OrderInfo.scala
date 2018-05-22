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
    var id: Long = _

    //下单用户id
    @BeanProperty
    var userId: Long = _

    //商品id
    @BeanProperty
    var goodsId: Long = _

    //交付地址id
    @BeanProperty
    var deliveryAddrId: Long = _

    //商品名称
    @BeanProperty
    var goodsName: String = _

    //商品数量
    @BeanProperty
    var goodsCount: Integer = _

    //商品价格
    @BeanProperty
    var goodsPrice: Double = _

    //订单渠道
    @BeanProperty
    var orderChannel: Integer = _

    //订单状态 未支付，已支付等等
    @BeanProperty
    var status: Integer = _

    //创建日期
    @BeanProperty
    var createDate: Date = _

    //支付日期
    @BeanProperty
    var payDate: Date = _

    override def toString(): String = {
        "OrderInfo [id=" + id + ", userId=" + userId + ", goodsId=" + goodsId + ", deliveryAddrId="
        +deliveryAddrId + ", goodsName=" + goodsName + ", goodsCount=" + goodsCount + ", goodsPrice="
        +goodsPrice + ", orderChannel=" + orderChannel + ", status=" + status + ", createDate=" + createDate +
            ", payDate=" + payDate + "]"
    }
}