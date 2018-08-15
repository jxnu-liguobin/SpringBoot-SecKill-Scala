package cn.edu.jxnu.seckill.controller

import java.util.{List => JavaList}

import cn.edu.jxnu.seckill.dao.GoodsDao
import cn.edu.jxnu.seckill.domain.User
import cn.edu.jxnu.seckill.rabbitmq.RabbitMQSender
import cn.edu.jxnu.seckill.redis.RedisService
import cn.edu.jxnu.seckill.redis.key.UserKey
import cn.edu.jxnu.seckill.result.{CodeMsg, Result}
import cn.edu.jxnu.seckill.service.GoodsService
import cn.edu.jxnu.seckill.vo.GoodsVo
import io.swagger.annotations.{Api, ApiOperation}
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.ui.Model
import org.springframework.web.bind.annotation.{GetMapping, RequestMapping, RestController}

import scala.collection.JavaConversions._
import scala.collection.immutable.{List => _}
import scala.language.implicitConversions

@RestController
@RequestMapping(Array("/sample"))
@Api(value = "测试controller", tags = { Array("测试接口") })
class SampleController @Autowired() (goodsDao: GoodsDao, goodsService: GoodsService, redisService: RedisService,
    rabbitMQSender: RabbitMQSender) {

    /**
     * Hello World
     */
    @ApiOperation(value = "控制器测试", notes = { "控制器测试" })
    @GetMapping(Array("/hello"))
    def home() = {
        "Hello World!"
    }

    /**
     * 测试Thymeleaf
     */
    @ApiOperation(value = "测试Thymeleaf", notes = { "测试Thymeleaf" })
    @GetMapping(Array("/thymeleaf"))
    def thymeleaf(model: Model) = {
        model.addAttribute("name", "HiphopMan")
        "hello"
    }

    /**
     * 测试mybatis和druid
     */
    @ApiOperation(value = "测试mybatis和druid", notes = { "测试mybatis和druid" })
    @GetMapping(Array("/db/get"))
    def dbGet(): JavaList[GoodsVo] = {
        goodsDao.listGoodsVo()
    }

    /**
     * 测试返回成功
     */
    @ApiOperation(value = "测试返回成功", notes = { "测试返回成功" })
    @GetMapping(Array("/success"))
    def success(): Result[String] = {
        Result.success("测试成功啦")

    }

    /**
     * 测试返回失败
     */
    @ApiOperation(value = "测试返回失败", notes = { "测试返回失败" })
    @GetMapping(Array("/error"))
    def error(): Result[CodeMsg] = {
        Result.error(CodeMsg.SERVER_ERROR)
    }

    /**
     * 测试服务层写法
     * 并测试toString方法
     */
    @ApiOperation(value = "服务层写法", notes = { "服务层写法" })
    @GetMapping(Array("/service"))
    def service() = {
        for (g <- goodsService.listGoodsVo()) {
            println("商品视图对象toString方法=>" + g)
        }
        goodsService.listGoodsVo()
    }

    /**
     * 测试Redis
     */
    @ApiOperation(value = "测试Redis取", notes = { "测试Redis取" })
    @GetMapping(Array("/redis/get"))
    def redisGet(): Result[User] = {
        val user = redisService.get(UserKey.getById, "" + 1, classOf[User])
        Result.success(user)
    }

    /**
     * 测试Redis
     */
    @ApiOperation(value = "测试Redis存", notes = { "测试Redis存" })
    @GetMapping(Array("/redis/set"))
    def redisSet(): Result[Boolean] = {
        val user = new User()
        user.setId(1)
        user.setName("1111")
        redisService.set(UserKey.getById, "" + 1, user) // UserKey:id1
        Result.success(true)
    }

    @ApiOperation(value = "测试RabbitMQ", notes = { "测试RabbitMQ" })
    @GetMapping(Array("/mq"))
    def mq(): Result[String] = {
        rabbitMQSender.send("你好呀！")
        Result.success("Hello")
    }

}