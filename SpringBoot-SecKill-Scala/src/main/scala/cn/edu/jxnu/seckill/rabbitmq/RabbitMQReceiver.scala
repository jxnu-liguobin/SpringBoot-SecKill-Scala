package cn.edu.jxnu.seckill.rabbitmq

import org.springframework.stereotype.Service
import org.springframework.beans.factory.annotation.Autowired
import cn.edu.jxnu.seckill.redis.RedisService
import cn.edu.jxnu.seckill.service.GoodsService
import cn.edu.jxnu.seckill.service.OrderService
import cn.edu.jxnu.seckill.service.SeckillService
import org.slf4j.LoggerFactory
import org.springframework.amqp.rabbit.annotation.RabbitListener

/**
 * RabbitMQ接收者
 *
 * @author 梦境迷离.
 * @time 2018年5月21日
 * @version v1.0
 */
@Service
class RabbitMQReceiver @Autowired() (redisService: RedisService,
    goodsService: GoodsService, orderService: OrderService, seckillService: SeckillService) {

    private final val log = LoggerFactory.getLogger(classOf[RabbitMQReceiver])

    // 这里使用包对象和伴生对象均报非常量的错，因为常量不能有类型
    @RabbitListener(queues = Array(RabbitMQConst.SECKILL_QUEUE))
    def receiveSeckill(message: String) {
        log.info("receive message:" + message)
        val mm = RedisService.stringToBean(message, classOf[SeckillMessage])
        val user = mm.getUser()
        val goodsId = mm.getGoodsId()

        val goods = goodsService.getGoodsVoByGoodsId(goodsId)
        val stock = goods.getStockCount()
        if (stock <= 0) {
            return
        }
        // 判断是否已经秒杀到了
        val order = orderService.getSeckillOrderByUserIdGoodsId(user.getId(), goodsId)
        if (order != null) {
            return
        }
        // 减库存 下订单 写入秒杀订单
        seckillService.seckill(user, goods)
    }

    @RabbitListener(queues = Array(RabbitMQConst.QUEUE))
    def receive(message: String) {
        log.info("receive message:" + message)
    }

    @RabbitListener(queues = Array(RabbitMQConst.TOPIC_QUEUE1))
    def receiveTopic1(message: String) {
        log.info(" topic  queue1 message:" + message)
    }

    @RabbitListener(queues = Array(RabbitMQConst.TOPIC_QUEUE2))
    def receiveTopic2(message: String) {
        log.info(" topic  queue2 message:" + message)
    }

    @RabbitListener(queues = Array(RabbitMQConst.HEADER_QUEUE))
    def receiveHeaderQueue(message: Array[Byte]) {
        log.info(" header  queue message:" + new String(message))
    }
}
