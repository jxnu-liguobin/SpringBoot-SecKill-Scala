package cn.edu.jxnu.seckill.redis.key

import cn.edu.jxnu.seckill.redis.BasePrefix

/**
 * 定义Redis 秒杀键 前缀
 *
 * @author 梦境迷离.
 * @time 2018年5月20日
 * @version v1.0
 */
class SeckillKey private (expireSe: Integer, prefix: String) extends BasePrefix(expireSe, prefix) {

}

object SeckillKey {
    
    final val isGoodsOver = new SeckillKey(0, "go")
    final val getSeckillPath = new SeckillKey(60, "mp")
    final val getSeckillVerifyCode = new SeckillKey(300, "vc")
}