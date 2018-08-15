package cn.edu.jxnu.seckill.redis.key

import cn.edu.jxnu.seckill.redis.BasePrefix

/**
 * 定义Redis 用户键 前缀
 *
 * @author 梦境迷离.
 * @time 2018年5月20日
 * @version v1.0
 */
class UserKey private (var prefix: String) extends BasePrefix(prefix) {

}

object UserKey {

    final val getById = new UserKey("id")

    final val getByName = new UserKey("name")

}