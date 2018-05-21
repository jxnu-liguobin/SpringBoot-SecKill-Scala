package cn.edu.jxnu.seckill.rabbitmq

import java.util.{ HashMap => JavaHashMap }
import java.util.Map
import scala.language.implicitConversions
import scala.collection.JavaConversions._
import org.springframework.amqp.core.Binding
import org.springframework.amqp.core.BindingBuilder
import org.springframework.amqp.core.FanoutExchange
import org.springframework.amqp.core.HeadersExchange
import org.springframework.amqp.core.Queue
import org.springframework.amqp.core.TopicExchange
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import scala.collection.immutable.{ HashMap => _, _ }
import scala.collection.immutable.{ Map => _, _ }

/**
 * RabbitMQ配置
 *
 * @author 梦境迷离.
 * @time 2018年5月21日
 * @version v1.0
 */
@Configuration
class RabbitMQConfig {

    @Bean
    def seckillQueue() = new Queue(RabbitMQConst.SECKILL_QUEUE, true)

    /**
     * Direct模式 交换机Exchange
     */
    @Bean
    def queue() = new Queue(RabbitMQConst.QUEUE, true)

    /**
     * Topic模式 交换机Exchange
     */
    @Bean
    def topicQueue1() = new Queue(RabbitMQConst.TOPIC_QUEUE1, true)

    @Bean
    def topicQueue2() = new Queue(RabbitMQConst.TOPIC_QUEUE2, true)

    @Bean
    def topicExchage() = new TopicExchange(RabbitMQConst.TOPIC_EXCHANGE)

    @Bean
    def topicBinding1() = BindingBuilder.bind(topicQueue1()).to(topicExchage()).`with`("topic.key1")

    @Bean
    def topicBinding2() = BindingBuilder.bind(topicQueue2()).to(topicExchage()).`with`("topic.#")

    /**
     * Fanout模式 交换机Exchange
     */
    @Bean
    def fanoutExchage() = new FanoutExchange(RabbitMQConst.FANOUT_EXCHANGE)

    @Bean
    def FanoutBinding1() = BindingBuilder.bind(topicQueue1()).to(fanoutExchage())

    @Bean
    def FanoutBinding2() = BindingBuilder.bind(topicQueue2()).to(fanoutExchage())

    /**
     * Header模式 交换机Exchange
     */
    @Bean
    def headersExchage() = new HeadersExchange(RabbitMQConst.HEADERS_EXCHANGE)

    @Bean
    def headerQueue1() = new Queue(RabbitMQConst.HEADER_QUEUE, true)

    @Bean
    def headerBinding(): Binding = {
        val map = new JavaHashMap[String, Object]()
        map.put("header1", "value1")
        map.put("header2", "value2")
        BindingBuilder.bind(headerQueue1()).to(headersExchage()).whereAll(map).`match`()
    }

}
/**
 * 包对象存放常量，不能有类型
 */
package object RabbitMQConst {

    final val SECKILL_QUEUE = "seckill.queue"
    final val QUEUE = "queue"
    final val TOPIC_QUEUE1 = "topic.queue1"
    final val TOPIC_QUEUE2 = "topic.queue2"
    final val HEADER_QUEUE = "header.queue"
    final val TOPIC_EXCHANGE = "topicExchage"
    final val FANOUT_EXCHANGE = "fanoutxchage"
    final val HEADERS_EXCHANGE = "headersExchage"
}