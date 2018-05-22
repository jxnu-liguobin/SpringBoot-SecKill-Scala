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
     *
     * 处理路由键。需要将一个队列绑定到交换机上，要求该消息与一个特定的路由键完全匹配。
     * 这是一个完整的匹配。如果一个队列绑定到该交换机上要求路由键 “abc”，则只有被标记为“abc”的消息才被转发，不会转发abc.def，也不会转发dog.ghi，只会转发abc。
     */
    @Bean
    def queue() = new Queue(RabbitMQConst.QUEUE, true)

    /**
     * Topic模式 交换机Exchange
     * 
     * 将路由键和某模式进行匹配。此时队列需要绑定要一个模式上。
     * 符号“#”匹配一个或多个词，符号“*”匹配不多不少一个词。
     * 因此“abc.#”能够匹配到“abc.def.ghi”，但是“abc.*” 只会匹配到“abc.def”。
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
     * 
     * 不处理路由键。你只需要简单的将队列绑定到交换机上。
     * 一个发送到交换机的消息都会被转发到与该交换机绑定的所有队列上。
     * 很像子网广播，每台子网内的主机都获得了一份复制的消息。
     * Fanout交换机转发消息是最快的。
     */
    @Bean
    def fanoutExchage() = new FanoutExchange(RabbitMQConst.FANOUT_EXCHANGE)

    @Bean
    def FanoutBinding1() = BindingBuilder.bind(topicQueue1()).to(fanoutExchage())

    @Bean
    def FanoutBinding2() = BindingBuilder.bind(topicQueue2()).to(fanoutExchage())

    /**
     * Header模式 交换机Exchange
     * 
     * 不处理路由键。而是根据发送的消息内容中的headers属性进行匹配。
     * 在绑定Queue与Exchange时指定一组键值对；当消息发送到RabbitMQ时会取到该消息的headers与Exchange绑定时指定的键值对进行匹配；
     * 如果完全匹配则消息会路由到该队列，否则不会路由到该队列。
     * headers属性是一个键值对，可以是Hashtable，键值对的值可以是任何类型。
     * 而fanout，direct，topic 的路由键都需要要字符串形式的。
     * 匹配规则x-match有下列两种类型：
     * x-match = all ：表示所有的键值对都匹配才能接受到消息
     * x-match = any ：表示只要有键值对匹配就能接受到消息
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
        //全部匹配才会接受消息
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