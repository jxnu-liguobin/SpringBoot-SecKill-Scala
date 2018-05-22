package cn.edu.jxnu.seckill.controller

import org.springframework.web.bind.annotation.RestController
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.beans.factory.annotation.Autowired
import cn.edu.jxnu.seckill.redis.RedisService
import cn.edu.jxnu.seckill.service.SeckillUserService
import org.springframework.ui.Model
import cn.edu.jxnu.seckill.domain.SeckillUser
import cn.edu.jxnu.seckill.result.Result
import org.slf4j.LoggerFactory

/**
 * 用户控制器
 *
 * 压测专用
 * @author 梦境迷离.
 * @time 2018年5月21日
 * @version v1.0
 */
@RestController
@RequestMapping(Array("/user"))
class UserController @Autowired() (userService: SeckillUserService,
    redisService: RedisService) {

    private final val log = LoggerFactory.getLogger(classOf[UserController])

    /**
     * QPS:366.6 1000 * 10
     */
    @RequestMapping(Array("/info"))
    def info(model: Model, user: SeckillUser): Result[SeckillUser] = {

        log.info("用户user：" + user.toString())
        Result.success(user)
    }

}