package cn.edu.jxnu.seckill.vo

import scala.language.implicitConversions
import scala.beans.BeanProperty
import cn.edu.jxnu.seckill.domain.SeckillUser

/**
 * 商品详情视图对象
 *
 * @author 梦境迷离.
 * @time 2018年5月20日
 * @version v1.0
 */
class GoodsDetailVo {

    /**
     * 秒杀状态
     */
    @BeanProperty
    var seckillStatus: Integer = _

    /**
     * 遗留时间
     */
    @BeanProperty
    var remainSeconds: Integer = _

    /**
     * 商品视图对象
     */
    @BeanProperty
    var goods: GoodsVo = _

    /**
     * 秒杀用户
     */
    @BeanProperty
    var user: SeckillUser = _

}