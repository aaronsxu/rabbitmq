package com.mo9.rabbitmq.listener;

import java.io.IOException;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.amqp.core.Message;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import com.mo9.rabbitmq.config.RabbitmqConfig;
import com.rabbitmq.client.Channel;

@Component
public class ConsumerListener {
	protected Logger logger = LoggerFactory.getLogger(getClass());

	@RabbitListener(queues = "Q.DEV.DURABLE.rabbitmq.testDirectQueue1")
	public void directConsumer1(String message) {
		logger.info("directConsumer1 message ---------------------------------->" + message);
	}

	@RabbitListener(queues = "Q.DEV.DURABLE.rabbitmq.testDirectQueue2")
	public void directConsumer2(String message) {
		logger.info("directConsumer2 message ---------------------------------->" + message);
	}

	@RabbitListener(queues = "Q.DEV.DURABLE.rabbitmq.testTopicQueue1")
	public void topicConsumer1(String message) {
		logger.info("topicConsumer1 message ---------------------------------->" + message);
	}

	@RabbitListener(queues = "Q.DEV.DURABLE.rabbitmq.testTopicQueue2")
	public void topicConsumer2(String message) {
		logger.info("topicConsumer2 message ---------------------------------->" + message);
	}

	@RabbitListener(queues = "Q.DEV.DURABLE.rabbitmq.testFanoutQueue1")
	public void fanoutConsumer1(String message) {
		logger.info("fanoutConsumer1 message ---------------------------------->" + message);
	}

	@RabbitListener(queues = "Q.DEV.DURABLE.rabbitmq.testFanoutQueue2")
	public void fanoutConsumer2(String message) {
		logger.info("fanoutConsumer2 message ---------------------------------->" + message);
	}
	
	/**
     * 监听替补队列 来验证死信.
     *
     * @param message the message
     * @param channel the channel
     * @throws IOException the io exception  这里异常需要处理
     */
    @RabbitListener(queues = {"REDIRECT_QUEUE"})
    public void redirect(Message message, Channel channel) throws IOException {
        channel.basicAck(message.getMessageProperties().getDeliveryTag(), false);
//        log.debug("dead message  10s 后 消费消息 {}",new String (message.getBody()));
    }

}
