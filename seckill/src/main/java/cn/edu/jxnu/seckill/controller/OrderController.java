package cn.edu.jxnu.seckill.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import cn.edu.jxnu.seckill.domain.OrderInfo;
import cn.edu.jxnu.seckill.domain.SeckillUser;
import cn.edu.jxnu.seckill.result.CodeMsg;
import cn.edu.jxnu.seckill.result.Result;
import cn.edu.jxnu.seckill.service.GoodsService;
import cn.edu.jxnu.seckill.service.OrderService;
import cn.edu.jxnu.seckill.vo.GoodsVO;
import cn.edu.jxnu.seckill.vo.OrderDetailVO;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;

@RestController
@RequestMapping("/order")
@Api(value = "订单controller", tags = { "订单接口" })
public class OrderController {

	@Autowired
	private OrderService orderService;

	@Autowired
	private GoodsService goodsService;

	@ApiOperation("订单详情接口")
	@ApiImplicitParams({ @ApiImplicitParam(name = "user", value = "用户实体", required = true, dataType = "SeckillUser"),
			@ApiImplicitParam(name = "orderId", value = "订单ID", required = true, paramType = "path", dataType = "Long") })
	@GetMapping("/detail")
	public Result<OrderDetailVO> seckill(SeckillUser seckillUser, @RequestParam("orderId") long orderId) {
		if (seckillUser == null) {
			return Result.error(CodeMsg.SESSION_ERROR);
		}

		OrderInfo order = orderService.getOrderById(orderId);
		if (order == null) {
			return Result.error(CodeMsg.ORDER_NOT_EXIST);
		}

		long goodsId = order.getGoodsId();
		GoodsVO goodsVO = goodsService.getGoodsVOById(goodsId);
		OrderDetailVO orderDetailVO = new OrderDetailVO();
		orderDetailVO.setOrder(order);
		orderDetailVO.setGoods(goodsVO);

		return Result.success(orderDetailVO);
	}
}
