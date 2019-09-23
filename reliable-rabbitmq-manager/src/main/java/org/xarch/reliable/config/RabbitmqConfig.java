package org.xarch.reliable.config;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.amqp.core.AcknowledgeMode;
import org.springframework.amqp.core.Message;
import org.springframework.amqp.rabbit.config.SimpleRabbitListenerContainerFactory;
import org.springframework.amqp.rabbit.connection.CachingConnectionFactory;
import org.springframework.amqp.rabbit.connection.CorrelationData;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.amqp.support.converter.Jackson2JsonMessageConverter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.amqp.SimpleRabbitListenerContainerFactoryConfigurer;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
*
*  @Description  The configuration of rabbitMQ
*
*  @Author  Jim_Carrey
*
*  @Date  2019年9月22日
*/
@Configuration
public class RabbitmqConfig {
	
	private static final Logger logger = LoggerFactory.getLogger(RabbitmqConfig.class);
	
	@Autowired
	private CachingConnectionFactory connectionFactory;
	
	@Autowired
	private SimpleRabbitListenerContainerFactoryConfigurer factoryConfigurer;
	
	//单一消费者
	@Bean(name = "singleListenerContainer")
	public SimpleRabbitListenerContainerFactory listenerContainerFactory() {
		
		SimpleRabbitListenerContainerFactory factory = new SimpleRabbitListenerContainerFactory();
		factory.setConnectionFactory(connectionFactory);
		factory.setMessageConverter(new Jackson2JsonMessageConverter());
		factory.setConcurrentConsumers(1);
		factory.setMaxConcurrentConsumers(1);
		factory.setPrefetchCount(1);
		factory.setTxSize(1);
		return factory;
	}
	
	//多个消费者
	@Bean(name = "multiListenerContainer")
	public SimpleRabbitListenerContainerFactory multiListenerContainerFactory() {
		SimpleRabbitListenerContainerFactory factory = new SimpleRabbitListenerContainerFactory();
		factoryConfigurer.configure(factory, connectionFactory);
		factory.setMessageConverter(new Jackson2JsonMessageConverter());
		//确定消费模式-NONE
		factory.setAcknowledgeMode(AcknowledgeMode.NONE);
		return factory;
	}
	
	//rabbitTemplate模板配置
	public RabbitTemplate rabbitTemplate() {
		connectionFactory.setPublisherConfirms(true);
		connectionFactory.setPublisherReturns(true);
		RabbitTemplate rabbitTemplate = new RabbitTemplate(connectionFactory);
		rabbitTemplate.setMandatory(true);
		rabbitTemplate.setConfirmCallback(new RabbitTemplate.ConfirmCallback() {
			
			//消息发送确认
			@Override
			public void confirm(CorrelationData correlationData, boolean ack, String cause) {
				logger.info("消息发送成功：correlationData({}),ack({}),cause({})", correlationData, ack, cause);
			}
		});
		
		rabbitTemplate.setReturnCallback(new RabbitTemplate.ReturnCallback() {
			
			//消息丢失回调(消息找不到对应队列)
			@Override
			public void returnedMessage(Message message, int replyCode, String replyText, String exchange, String routingKey) {
				logger.warn("消息丢失：exchange({}),route({}),replyCode({}),replyText({}),mesage:{}", exchange, routingKey, replyCode,replyText,message);
			}
		});
		return rabbitTemplate;
		
	}
	
	
	//refund消息队列
	@Bean
	public org.springframework.amqp.core.Queue refundQueue() {
		return new org.springframework.amqp.core.Queue("refund");
	}
	
	/*
	 * //activity消息队列
	 * 
	 * @Bean public org.springframework.amqp.core.Queue activityQueue() { return new
	 * org.springframework.amqp.core.Queue("activity"); }
	 */
}
