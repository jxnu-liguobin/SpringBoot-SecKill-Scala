package cn.edu.jxnu.seckill.redis

/**
 * Redis 键
 *
 * @author 梦境迷离.
 * @time 2018年5月20日
 * @version v1.0
 */
trait KeyPrefix {

    /** 过期时间. */
    def expireSeconds(): Integer

    /** redis前缀. */
    def getPrefix(): String
}