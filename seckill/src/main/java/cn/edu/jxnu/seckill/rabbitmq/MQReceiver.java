package cn.edu.jxnu.seckill.rabbitmq;

import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import cn.edu.jxnu.seckill.domain.SeckillOrder;
import cn.edu.jxnu.seckill.domain.SeckillUser;
import cn.edu.jxnu.seckill.redis.RedisService;
import cn.edu.jxnu.seckill.service.GoodsService;
import cn.edu.jxnu.seckill.service.OrderService;
import cn.edu.jxnu.seckill.service.SeckillService;
import cn.edu.jxnu.seckill.vo.GoodsVO;
import lombok.extern.slf4j.Slf4j;

@Service
@Slf4j
public class MQReceiver {

	@Autowired
	private GoodsService goodsService;

	@Autowired
	private OrderService orderService;

	@Autowired
	private SeckillService seckillService;

	@RabbitListener(queues = MQConfig.SECKILL_QUEUE)
	public void receive(String message) {
		log.info("receive message:" + message);
		SeckillMessage seckillMessage = RedisService.stringToBean(message, SeckillMessage.class);
		SeckillUser user = seckillMessage.getSeckillUser();
		long goodsId = seckillMessage.getGoodsId();

		// 判断库存
		GoodsVO goods = goodsService.getGoodsVOById(goodsId);
		int stock = goods.getStockCount();
		if (stock < 1) {
			return;
		}

		// 判断是否已经秒杀到了
		SeckillOrder order = orderService.getSeckillOrderByUserIdGoodsId(user.getId(), goodsId);
		if (order != null) {
			return;
		}

		// 减库存 下订单 写入秒杀订单
		seckillService.seckill(user, goods);
	}

	// @RabbitListener(queues = MQConfig.QUEUE)
	// public void receive(String message) {
	// log.info("receive message:" + message);
	// }
	//
	// @RabbitListener(queues = MQConfig.TOPIC_QUEUE1)
	// public void receiveTopic1(String message) {
	// log.info("topic1 message:" + message);
	// }
	//
	// @RabbitListener(queues = MQConfig.TOPIC_QUEUE2)
	// public void receiveTopic2(String message) {
	// log.info("topic2 message:" + message);
	// }
	//
	// @RabbitListener(queues = MQConfig.HEADER_QUEUE)
	// public void receiveHeader(byte[] message) {
	// log.info("headers queue message:" + new String(message));
	// }

}
