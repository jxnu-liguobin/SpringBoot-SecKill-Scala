package cn.edu.jxnu.seckill.redis

import java.lang.Long
import java.util.{ArrayList => JavaArrayList, List => JavaList}

import com.alibaba.fastjson.JSON
import org.slf4j.LoggerFactory
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Service
import redis.clients.jedis.{Jedis, JedisPool, ScanParams}

import scala.collection.JavaConversions._
import scala.collection.immutable.{List => _}
import scala.collection.mutable.ArrayBuffer
import scala.language.implicitConversions

/**
 * Redis服务层
 *
 * @author 梦境迷离.
 * @time 2018年5月20日
 * @version v1.0
 */
@Service
class RedisService @Autowired() (val jedisPool: JedisPool) {

    private final val log = LoggerFactory.getLogger(classOf[RedisService])

    def get[T](prefix: KeyPrefix, key: String, clazz: Class[T]): T = {

        var jedis: Jedis = null
        try {
            jedis = jedisPool.getResource()
            // 生成真正的key
            val realKey = prefix.getPrefix() + key
            val str = jedis.get(realKey)
            RedisService.stringToBean(str, clazz)
        } finally {
            RedisService.returnToPool(jedis)
        }

    }

    /**
     * 设置对象
     */
    def set[T](prefix: KeyPrefix, key: String, value: T): Boolean = {

        var jedis: Jedis = null
        try {
            jedis = jedisPool.getResource()
            val str = RedisService.beanToString(value)
            if (str == null || str.length() <= 0)
                return false
            // 生成真正的key
            val realKey = prefix.getPrefix() + key
            val seconds = prefix.expireSeconds()
            if (seconds <= 0)
                jedis.set(realKey, str)
            else
                jedis.setex(realKey, seconds, str)
            true
        } finally {
            RedisService.returnToPool(jedis)
        }
    }

    /**
     * 判断key是否存在
     */
    def exists[T](prefix: KeyPrefix, key: String): Boolean = {

        var jedis: Jedis = null
        try {
            jedis = jedisPool.getResource()
            // 生成真正的key
            val realKey = prefix.getPrefix() + key
            jedis.exists(realKey)
        } finally {
            RedisService.returnToPool(jedis)
        }
    }

    /**
     * 增加值
     */
    def incr[T](prefix: KeyPrefix, key: String): Long = {

        var jedis: Jedis = null
        try {
            jedis = jedisPool.getResource()
            // 生成真正的key
            val realKey = prefix.getPrefix() + key
            jedis.incr(realKey)
        } finally {
            RedisService.returnToPool(jedis)
        }
    }

    /**
     * 减少值
     */
    def decr[T](prefix: KeyPrefix, key: String): Long = {

        var jedis: Jedis = null
        try {
            jedis = jedisPool.getResource()
            // 生成真正的key
            val realKey = prefix.getPrefix() + key
            jedis.decr(realKey)
        } finally {
            RedisService.returnToPool(jedis)
        }
    }

    /**
     * 删除
     */
    def delete(prefix: KeyPrefix, key: String): Boolean = {

        var jedis: Jedis = null
        try {
            jedis = jedisPool.getResource()
            // 生成真正的key
            val realKey = prefix.getPrefix() + key
            jedis.del(realKey) > 0
        } finally {
            RedisService.returnToPool(jedis)
        }
    }

    def delete(prefix: KeyPrefix): Boolean = {

        var jedis: Jedis = null
        var keys: JavaList[String] = scanKeys(prefix.getPrefix())
        val keyss = new ArrayBuffer[String]()
        for (k <- keys) {
            if (k != null)
                keyss.+=(k)
        }
        if (prefix == null) {
            return false
        }
        if (keys == null || keys.size() <= 0) {
            return true
        }
        try {
            jedis = jedisPool.getResource()
            //TODO 参数，数组元素作为每个小单元传入参数,必须注意这里的参数传递
            jedis.del(keyss: _*)
            true
        } catch {
            case e: Exception =>
                e.printStackTrace()
                false
        } finally {
            RedisService.returnToPool(jedis)
        }
    }

    def scanKeys(key: String): JavaList[String] = {

        var jedis: Jedis = null
        try {
            jedis = jedisPool.getResource()
            val keys = new JavaArrayList[String]()
            var cursor = "0"
            val sp: ScanParams = new ScanParams()
            //注意这里不能直接用match
            sp.`match`("*" + key + "*")
            sp.count(100)
            do {
                val ret = jedis.scan(cursor, sp)
                val result = ret.getResult()
                if (result != null && result.size() > 0) {
                    keys.addAll(result)
                }
                // 再处理cursor
                cursor = ret.getStringCursor()
            } while (!cursor.equals("0"))
            keys
        } finally {
            RedisService.returnToPool(jedis)
        }
    }
}

object RedisService {

    /**
     * 通用的工具，将bean转化为String
     */
    def beanToString[T](value: T): String = {

        if (value == null)
            return null
        val clazz = value.getClass()
        if (clazz == classOf[Int] || clazz == classOf[Integer]) {
            "" + value
        } else if (clazz == classOf[String]) {
            value.asInstanceOf[String]
        } else if (clazz == classOf[Long] || clazz == classOf[Long]) {
            "" + value
        } else {
            //TODO 对齐的JSON，必须加true/false,否则有二义性，与Java不同
            JSON.toJSONString(value, false)
        }
    }

    /**
     * 通用的工具，将String转化为bean
     */
    def stringToBean[T](str: String, clazz: Class[T]): T = {
        /**
         * Scala基本类型就是包装类型,可以说没有原生类型一说
         */
        if (str == null || str.length() <= 0 || clazz == null) {
            return null.asInstanceOf[T]
        }
        if (clazz == classOf[Int] || clazz == classOf[Integer]) {
            str.toInt.asInstanceOf[T]
        } else if (clazz == classOf[String]) {
            str.asInstanceOf[T]
        } else if (clazz == classOf[Long] || clazz == classOf[Long]) {
            str.toLong.asInstanceOf[T]
        } else {
            JSON.toJavaObject(JSON.parseObject(str), clazz)
        }
    }

    private def returnToPool(jedis: Jedis) {

        if (jedis != null) jedis.close()

    }
}