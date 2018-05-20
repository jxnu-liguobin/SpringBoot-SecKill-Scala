package cn.edu.jxnu.seckill.vo

import scala.beans.BeanProperty
import scala.language.implicitConversions
import cn.edu.jxnu.seckill.domain.OrderInfo

/**
 * 订单详情视图对象对象
 *
 * @author 梦境迷离.
 * @time 2018年5月19日
 * @version v1.0
 */
class OrderDetailVo {

    /**
     * 商品视图对象
     */
    @BeanProperty
    var goods: GoodsVo = _

    /**
     * 订单信息
     */
    @BeanProperty
    var order: OrderInfo = _

}