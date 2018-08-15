package cn.edu.jxnu.seckill.rabbitmq

import cn.edu.jxnu.seckill.redis.RedisService
import org.slf4j.LoggerFactory
import org.springframework.amqp.core.{AmqpTemplate, Message, MessageProperties}
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Service

/**
 * RabbitMQ发送者
 *
 * @author 梦境迷离.
 * @time 2018年5月21日
 * @version v1.0
 */
@Service
class RabbitMQSender @Autowired() (amqpTemplate: AmqpTemplate) {

    private final val log = LoggerFactory.getLogger(classOf[RabbitMQSender])

    /**
     * 秒杀消息由商品ID和秒杀用户，唯一确定
     */
    def sendSeckillMessage(mm: SeckillMessage) {
        //将秒杀消息传入队列，发送到接收者【消费者】
        val msg = RedisService.beanToString(mm)
        log.info("send message:" + msg)
        amqpTemplate.convertAndSend(RabbitMQConst.SECKILL_QUEUE, msg)
    }

    def send(message: AnyRef) {
        val msg = RedisService.beanToString(message)
        log.info("send message:" + msg)
        amqpTemplate.convertAndSend(RabbitMQConst.QUEUE, msg)
    }

    def sendTopic(message: AnyRef) {
        val msg = RedisService.beanToString(message)
        log.info("send topic message:" + msg)
        amqpTemplate.convertAndSend(RabbitMQConst.TOPIC_EXCHANGE, "topic.key1", msg + "1")
        amqpTemplate.convertAndSend(RabbitMQConst.TOPIC_EXCHANGE, "topic.key2", msg + "2")
    }

    def sendFanout(message: AnyRef) {
        val msg = RedisService.beanToString(message)
        log.info("send fanout message:" + msg)
        amqpTemplate.convertAndSend(RabbitMQConst.FANOUT_EXCHANGE, "", msg)
    }

    def sendHeader(message: AnyRef) {
        val msg = RedisService.beanToString(message)
        log.info("send header message:" + msg)
        val properties = new MessageProperties()
        properties.setHeader("header1", "value1")
        properties.setHeader("header2", "value2")
        val obj = new Message(msg.getBytes(), properties)
        amqpTemplate.convertAndSend(RabbitMQConst.HEADERS_EXCHANGE, "", obj)
    }
}