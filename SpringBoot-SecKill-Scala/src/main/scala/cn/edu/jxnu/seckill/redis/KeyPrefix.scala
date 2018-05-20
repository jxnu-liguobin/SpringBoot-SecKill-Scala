package cn.edu.jxnu.seckill.redis

/**
 * Redis 键
 *
 * @author 梦境迷离.
 * @time 2018年5月20日
 * @version v1.0
 */
trait KeyPrefix {

    def expireSeconds(): Integer

    def getPrefix(): String
}