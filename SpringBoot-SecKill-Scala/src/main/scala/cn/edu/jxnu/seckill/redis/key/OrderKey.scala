package cn.edu.jxnu.seckill.redis.key

import scala.beans.BeanProperty
import cn.edu.jxnu.seckill.redis.BasePrefix

/**
 * 定义Redis 订单键 前缀
 *
 * @author 梦境迷离.
 * @time 2018年5月20日
 * @version v1.0
 */
class OrderKey private (var prefix: String) extends BasePrefix(prefix) {

}
object OrderKey {

    //获取秒杀生成的订单，从中取值，检查用户是否多次秒杀，
    final val getSeckillOrderByUidGid: OrderKey = new OrderKey("moug")
}