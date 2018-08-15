package cn.edu.jxnu.seckill.controller

import cn.edu.jxnu.seckill.domain.SeckillUser
import cn.edu.jxnu.seckill.redis.RedisService
import cn.edu.jxnu.seckill.redis.key.GoodsKey
import cn.edu.jxnu.seckill.result.Result
import cn.edu.jxnu.seckill.service.GoodsService
import cn.edu.jxnu.seckill.vo.GoodsDetailVo
import io.swagger.annotations.{Api, ApiImplicitParam, ApiOperation}
import javax.servlet.http.{HttpServletRequest, HttpServletResponse}
import org.apache.commons.lang3.StringUtils
import org.slf4j.LoggerFactory
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.context.ApplicationContext
import org.springframework.ui.Model
import org.springframework.web.bind.annotation.{GetMapping, PathVariable, RequestMapping, RestController}
import org.thymeleaf.spring4.context.SpringWebContext
import org.thymeleaf.spring4.view.ThymeleafViewResolver
import springfox.documentation.annotations.ApiIgnore

import scala.collection.immutable.{HashMap => _, Map => _}
import scala.language.implicitConversions

/**
 * 商品控制器
 *
 * @author 梦境迷离.
 * @time 2018年5月21日
 * @version v1.0
 */
@RestController
@RequestMapping(Array("/goods"))
@Api(value = "商品controller", tags = { Array("商品接口") })
class GoodsController @Autowired() (goodsService: GoodsService,
    redisService: RedisService, thymeleafViewResolver: ThymeleafViewResolver,
    applicationContext: ApplicationContext) {

    private final val log = LoggerFactory.getLogger(classOf[GoodsController])

    /**
     * 商品列表 QPS:123.9 1000 * 10
     */
    @GetMapping(Array("/to_list"))
    @ApiOperation(value = "获取商品信息列表", notes = { "获取商品信息列表" })
    @ApiImplicitParam(name     = "seckillUser", value = "SeckillUser", required = true, dataType = "SeckillUser")
    def list(request: HttpServletRequest, response: HttpServletResponse, model: Model,
        user: SeckillUser): String = {

        log.info("【商品列表秒杀用户】:" + user.toString())
        // 取缓存，缓存默认1分钟
        var html = redisService.get(GoodsKey.getGoodsList, "", classOf[String])
        if (!StringUtils.isEmpty(html))
            return html
        model.addAttribute("user", user)
        // 查询商品列表
        val goodsList = goodsService.listGoodsVo()
        model.addAttribute("goodsList", goodsList)
        // return "goods_list"
        val ctx = new SpringWebContext(request, response, request.getServletContext(), request.getLocale(),
                                       model.asMap(), applicationContext)
        // 手动渲染，并存进缓存
        html = thymeleafViewResolver.getTemplateEngine().process("goods_list", ctx)
        if (!StringUtils.isEmpty(html))
            redisService.set(GoodsKey.getGoodsList, "", html)

        html
    }

    /**
     * ApiImplicitParams
     * Scala注解数组无法实现
     */
    @ApiIgnore
    @RequestMapping(value = Array("/detail/{goodsId}"))
    def detail(request: HttpServletRequest, response: HttpServletResponse, model: Model,
        user: SeckillUser, @PathVariable("goodsId") goodsId: Long): Result[GoodsDetailVo] = {

        log.info("【查看商品详情携带的用户】:" + user.toString())
        val goods = goodsService.getGoodsVoByGoodsId(goodsId)
        //定义2个Long类型，操作之后再把它强转为Int
        val startAt: Long = goods.getStartDate().getTime()
        val endAt: Long = goods.getEndDate().getTime()
        val now = System.currentTimeMillis()
        var seckillStatus = 0
        var remainSeconds: Long = 0
        if (now < startAt) { // 秒杀还没开始，倒计时
            seckillStatus = 0
            remainSeconds = ((startAt - now) / 1000)
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
        vo.setRemainSeconds(remainSeconds.toInt)
        vo.setSeckillStatus(seckillStatus)
        Result.success(vo)
    }

}