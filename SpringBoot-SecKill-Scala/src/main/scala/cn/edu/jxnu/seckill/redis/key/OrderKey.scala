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
class OrderKey private (prefix: String) extends BasePrefix(prefix) {

}
object OrderKey {

    final val getSeckillOrderByUidGid: OrderKey = new OrderKey("moug")
}