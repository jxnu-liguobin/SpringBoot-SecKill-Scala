package cn.edu.jxnu.seckill.redis.key

import cn.edu.jxnu.seckill.redis.BasePrefix

/**
 * 定义Redis 访问键 前缀
 *
 * @author 梦境迷离.
 * @time 2018年5月20日
 * @version v1.0
 */
class AccessKey private (expireSe: Integer, prefix: String)
    extends BasePrefix(expireSe, prefix) {

}

object AccessKey {

    def withExpire(expireSeconds: Integer): AccessKey = {
        new AccessKey(expireSeconds, "access")
    }
}