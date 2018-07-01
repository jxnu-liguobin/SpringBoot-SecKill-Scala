package cn.edu.jxnu.seckill.service;

import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import cn.edu.jxnu.seckill.dao.OrderDao;
import cn.edu.jxnu.seckill.domain.OrderInfo;
import cn.edu.jxnu.seckill.domain.SeckillOrder;
import cn.edu.jxnu.seckill.domain.SeckillUser;
import cn.edu.jxnu.seckill.redis.RedisService;
import cn.edu.jxnu.seckill.redis.key.OrderKey;
import cn.edu.jxnu.seckill.vo.GoodsVO;

@Service
public class OrderService {
	@Autowired
	private OrderDao orderDao;

	@Autowired
	private RedisService redisService;

	public SeckillOrder getSeckillOrderByUserIdGoodsId(Long userId, long goodsId) {

		return redisService.get(OrderKey.getSeckillOrderByUidGid, "" + userId + "_" + goodsId, SeckillOrder.class);
	}

	public OrderInfo getOrderById(long orderId) {
		return orderDao.getOrderById(orderId);
	}

	@Transactional
	public OrderInfo createOrder(SeckillUser seckillUser, GoodsVO goods) {
		OrderInfo orderInfo = new OrderInfo();
		orderInfo.setCreateTime(new Date());
		orderInfo.setDeliveryAddrId(0L);
		orderInfo.setGoodsId(goods.getId());
		orderInfo.setGoodsName(goods.getGoodsName());
		orderInfo.setGoodsPrice(goods.getSeckillPrice());
		orderInfo.setOrderChannel(1);
		orderInfo.setStatus(0);
		orderInfo.setUserId(seckillUser.getId());
		orderDao.insert(orderInfo);

		SeckillOrder seckillOrder = new SeckillOrder();
		seckillOrder.setOrderId(orderInfo.getId());
		seckillOrder.setUserId(1L);
		seckillOrder.setGoodsId(goods.getId());
		orderDao.insertSeckillOrder(seckillOrder);

		redisService.set(OrderKey.getSeckillOrderByUidGid, "" + seckillUser.getId() + "_" + goods.getId(),
				seckillOrder);

		return orderInfo;
	}

	public void deleteOrders() {
		orderDao.deleteOrders();
		orderDao.deleteSeckillOrders();
	}

}
