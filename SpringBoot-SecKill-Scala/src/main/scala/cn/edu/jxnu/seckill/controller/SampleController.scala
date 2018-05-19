package cn.edu.jxnu.seckill.controller

import scala.language.implicitConversions
import scala.collection.JavaConversions._
import scala.collection.immutable.{ List => _, _ }
import java.util.{ List => JavaList }
import org.springframework.stereotype.Controller
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController
import org.springframework.ui.Model
import cn.edu.jxnu.seckill.dao.GoodsDao
import org.springframework.beans.factory.annotation.Autowired
import cn.edu.jxnu.seckill.vo.GoodsVo
import cn.edu.jxnu.seckill.result.Result
import cn.edu.jxnu.seckill.result.CodeMsg

@RestController
@RequestMapping(Array("/sample"))
class SampleController @Autowired() (val goodsDao: GoodsDao) {

    /**
     * Hello World
     */
    @RequestMapping(Array("/hello"))
    def home() = {
        "Hello World!"
    }

    /**
     * 测试Thymeleaf
     */
    @RequestMapping(Array("/thymeleaf"))
    def thymeleaf(model: Model) = {
        model.addAttribute("name", "HiphopMan")
        "hello"
    }

    /**
     * 测试mybatis和druid
     */
    @RequestMapping(Array("/db/get"))
    def dbGet(): JavaList[GoodsVo] = {
        goodsDao.listGoodsVo()
    }

    /**
     * 测试返回成功
     */
    @RequestMapping(Array("/success"))
    def success(): Result[String] = {
        Result.success("测试成功啦")

    }

    /**
     * 测试返回失败
     */
    @RequestMapping(Array("/error"))
    def error(): Result[CodeMsg] = {
        Result.error(CodeMsg.SERVER_ERROR)
    }

}