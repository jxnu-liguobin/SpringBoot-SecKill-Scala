package cn.edu.jxnu.seckill.redis

import org.springframework.beans.factory.annotation.Autowired
import org.springframework.context.annotation.Bean
import org.springframework.stereotype.Service
import redis.clients.jedis.{JedisPool, JedisPoolConfig}

/**
 * Jedis 连接池服务
 *
 * @author 梦境迷离.
 * @time 2018年5月20日
 * @version v1.0
 */
@Service
class RedisPoolFactory @Autowired() (redisConfig: RedisConfig) {

    @Bean
    def JedisPoolFactory(): JedisPool = {
        val poolConfig = new JedisPoolConfig()
        poolConfig.setMaxIdle(redisConfig.getPoolMaxIdle())
        poolConfig.setMaxTotal(redisConfig.getPoolMaxTotal())
        poolConfig.setMaxWaitMillis(redisConfig.getPoolMaxWait() * 1000)
        new JedisPool(poolConfig, redisConfig.getHost(), redisConfig.getPort(),
            redisConfig.getTimeout() * 1000, redisConfig.getPassword(), 0)

    }
}