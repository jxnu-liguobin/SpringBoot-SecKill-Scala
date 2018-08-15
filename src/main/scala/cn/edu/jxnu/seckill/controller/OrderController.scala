package cn.edu.jxnu.seckill.controller

import cn.edu.jxnu.seckill.domain.SeckillUser
import cn.edu.jxnu.seckill.result.{CodeMsg, Result}
import cn.edu.jxnu.seckill.service.{GoodsService, OrderService}
import cn.edu.jxnu.seckill.vo.OrderDetailVo
import io.swagger.annotations.Api
import org.slf4j.LoggerFactory
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.ui.Model
import org.springframework.web.bind.annotation.{GetMapping, RequestMapping, RequestParam, RestController}

/**
 * 订单控制器
 *
 * @author 梦境迷离.
 * @time 2018年5月21日
 * @version v1.0
 */
@RestController
@RequestMapping(Array("/order"))
@Api(value = "订单controller", tags = { Array("订单接口") })
class OrderController @Autowired() (orderService: OrderService,
    goodsService: GoodsService) {

    private final val log = LoggerFactory.getLogger(classOf[OrderController])

    @GetMapping(Array("/detail"))
    def info(model: Model, user: SeckillUser, @RequestParam("orderId") orderId: Long): Result[OrderDetailVo] = {

        if (user == null)
            return Result.error(CodeMsg.SESSION_ERROR)
        val order = orderService.getOrderById(orderId)
        if (order == null)
            return Result.error(CodeMsg.ORDER_NOT_EXIST)
        val goods = goodsService.getGoodsVoByGoodsId(order.getGoodsId())
        val vo = new OrderDetailVo()
        vo.setOrder(order)
        vo.setGoods(goods)
        log.info("订单ID：" + order.getGoodsId())
        Result.success(vo)
    }

}