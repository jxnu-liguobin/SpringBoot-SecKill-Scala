package cn.edu.jxnu.seckill.redis

import scala.language.implicitConversions
import scala.beans.BeanProperty

/**
 * 定义Redis 键 和 过期时间
 *
 * @author 梦境迷离.
 * @time 2018年5月20日
 * @version v1.0
 */
abstract class BasePrefix extends KeyPrefix {

    /** 默认0代表永不过期. */
    private var expireSe: Integer = 0

    private var prefix: String = _

    def this(prefix: String) {
        this()
        this.prefix = prefix

    }

    def this(expireSeconds: Integer, prefix: String) {
        this()
        this.expireSe = expireSeconds
        this.prefix = prefix

    }

    override def getPrefix() = {
        val className = getClass().getSimpleName();
        className + ":" + prefix;
    }

    override def expireSeconds() = expireSe
}