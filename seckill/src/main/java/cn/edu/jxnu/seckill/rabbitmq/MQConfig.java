package cn.edu.jxnu.seckill.rabbitmq;

import java.util.HashMap;
import java.util.Map;

import org.springframework.amqp.core.Binding;
import org.springframework.amqp.core.BindingBuilder;
import org.springframework.amqp.core.FanoutExchange;
import org.springframework.amqp.core.HeadersExchange;
import org.springframework.amqp.core.Queue;
import org.springframework.amqp.core.TopicExchange;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class MQConfig {
	
	public static final String SECKILL_QUEUE = "seckill.queue";
	public static final String QUEUE = "queue";
	public static final String HEADER_QUEUE = "header.queue";
	public static final String TOPIC_QUEUE1 = "topic.queue1";
	public static final String TOPIC_QUEUE2 = "topic.queue2";
	public static final String TOPIC_EXCHANGE = "topicExchage";
	public static final String FANOUT_EXCHANGE = "fanoutExchage";
	public static final String HEADERS_EXCHANGE = "headersExchage";

	@Bean
	public Queue seckillQueue() {
		return new Queue(SECKILL_QUEUE, true);
	}

	/**
	 * Direct模式 交换机Exchange
	 */
	@Bean
	public Queue queue() {
		return new Queue(QUEUE, true);
	}

	/**
	 * Topic模式 交换机Exchange
	 */

	@Bean
	public Queue topicQueue1() {
		return new Queue(TOPIC_QUEUE1, true);
	}

	@Bean
	public Queue topicQueue2() {
		return new Queue(TOPIC_QUEUE2, true);
	}

	@Bean
	public TopicExchange topicExchange() {
		return new TopicExchange(TOPIC_EXCHANGE);
	}

	@Bean
	public Binding topicBinding1() {
		return BindingBuilder.bind(topicQueue1()).to(topicExchange()).with("topic.key1");
	}

	@Bean
	public Binding topicBinding2() {
		return BindingBuilder.bind(topicQueue2()).to(topicExchange()).with("topic.#");
	}

	/**
	 * Fanout模式 交换机Exchange
	 */
	@Bean
	public FanoutExchange fanoutExchange() {
		return new FanoutExchange(FANOUT_EXCHANGE);
	}

	@Bean
	public Binding fanoutBinding1() {
		return BindingBuilder.bind(topicQueue1()).to(fanoutExchange());
	}

	@Bean
	public Binding fanoutBinding2() {
		return BindingBuilder.bind(topicQueue2()).to(fanoutExchange());
	}

	/**
	 * Header 交换机Exchange
	 */
	@Bean
	public HeadersExchange headersExchange() {
		return new HeadersExchange(HEADERS_EXCHANGE);
	}

	@Bean
	public Queue headerQueue() {
		return new Queue(HEADER_QUEUE, true);
	}

	@Bean
	public Binding headersBinding() {
		Map<String, Object> map = new HashMap<>();
		map.put("header1", "value1");
		map.put("header2", "value2");
		return BindingBuilder.bind(headerQueue()).to(headersExchange()).whereAll(map).match();
	}
}
