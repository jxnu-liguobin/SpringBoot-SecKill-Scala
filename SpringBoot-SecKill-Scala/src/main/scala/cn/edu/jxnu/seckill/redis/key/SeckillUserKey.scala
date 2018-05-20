package cn.edu.jxnu.seckill.redis.key

import cn.edu.jxnu.seckill.redis.BasePrefix

/**
 * 定义Redis秒杀用户键 前缀
 *
 * @author 梦境迷离.
 * @time 2018年5月20日
 * @version v1.0
 */
class SeckillUserKey private (expireSe: Integer, prefix: String)
    extends BasePrefix(expireSe, prefix) {

}

object SeckillUserKey {

    final val TOKEN_EXPIRE: Integer = 3600 * 24 * 2

    final val token: SeckillUserKey = new SeckillUserKey(TOKEN_EXPIRE, "tk")

    final val getById: SeckillUserKey = new SeckillUserKey(0, "id")
}