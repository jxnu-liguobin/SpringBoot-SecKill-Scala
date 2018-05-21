package cn.edu.jxnu.seckill.controller

import scala.language.implicitConversions
import scala.collection.JavaConversions._
import scala.collection.immutable.{ HashMap => _, _ }
import scala.collection.immutable.{ Map => _, _ }
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController
import org.springframework.beans.factory.annotation.Autowired
import cn.edu.jxnu.seckill.redis.RedisService
import org.springframework.context.ApplicationContext
import cn.edu.jxnu.seckill.service.GoodsService
import org.thymeleaf.spring4.view.ThymeleafViewResolver
import javax.servlet.http.HttpServletRequest
import javax.servlet.http.HttpServletResponse
import org.springframework.ui.Model
import cn.edu.jxnu.seckill.domain.SeckillUser
import org.apache.commons.lang3.StringUtils
import org.thymeleaf.spring4.context.SpringWebContext
import cn.edu.jxnu.seckill.redis.key.GoodsKey
import org.springframework.web.bind.annotation.PathVariable
import cn.edu.jxnu.seckill.result.Result
import cn.edu.jxnu.seckill.vo.GoodsDetailVo

/**
 * 商品控制器
 *
 * @author 梦境迷离.
 * @time 2018年5月21日
 * @version v1.0
 */
@RestController
@RequestMapping(Array("/goods"))
class GoodsController @Autowired() (goodsService: GoodsService,
    redisService: RedisService, thymeleafViewResolver: ThymeleafViewResolver,
    applicationContext: ApplicationContext) {

    /**
     * 商品列表 QPS:123.9 1000 * 10
     */
    @RequestMapping(Array("/to_list"))
    def list(request: HttpServletRequest, response: HttpServletResponse, model: Model, user: SeckillUser): String = {
        // 取缓存
        var html = redisService.get(GoodsKey.getGoodsList, "", classOf[String])
        if (!StringUtils.isEmpty(html))
            html
        model.addAttribute("user", user)
        // 查询商品列表
        val goodsList = goodsService.listGoodsVo()
        model.addAttribute("goodsList", goodsList)
        // return "goods_list"
        val ctx = new SpringWebContext(request, response, request.getServletContext(), request.getLocale(),
                                       model.asMap(), applicationContext)
        // 手动渲染
        html = thymeleafViewResolver.getTemplateEngine().process("goods_list", ctx)
        if (!StringUtils.isEmpty(html))
            redisService.set(GoodsKey.getGoodsList, "", html)

        html
    }

    @RequestMapping(value = Array("/detail/{goodsId}"))
    def detail(request: HttpServletRequest, response: HttpServletResponse, model: Model,
        user: SeckillUser, @PathVariable("goodsId") goodsId: Long): Result[GoodsDetailVo] = {

        val goods = goodsService.getGoodsVoByGoodsId(goodsId)
        val startAt = goods.getStartDate().getTime()
        val endAt = goods.getEndDate().getTime()
        val now = System.currentTimeMillis()
        var seckillStatus = 0
        var remainSeconds = 0
        if (now < startAt) { // 秒杀还没开始，倒计时
            seckillStatus = 0
            remainSeconds = ((startAt - now) / 1000).asInstanceOf[Integer]
        } else if (now > endAt) { // 秒杀已经结束
            seckillStatus = 2
            remainSeconds = -1
        } else { // 秒杀进行中
            seckillStatus = 1
            remainSeconds = 0
        }
        val vo = new GoodsDetailVo()
        vo.setGoods(goods)
        vo.setUser(user)
        vo.setRemainSeconds(remainSeconds)
        vo.setSeckillStatus(seckillStatus)
        Result.success(vo)
    }

}