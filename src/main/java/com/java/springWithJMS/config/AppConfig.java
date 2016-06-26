package com.java.springWithJMS.config;

import javax.jms.ConnectionFactory;
import javax.jms.JMSException;

import org.apache.activemq.ActiveMQConnectionFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.jms.annotation.EnableJms;
import org.springframework.jms.config.JmsListenerContainerFactory;
import org.springframework.jms.config.SimpleJmsListenerContainerFactory;
import org.springframework.jms.core.JmsTemplate;

@Configuration
@ComponentScan("com.java.springWithJMS")
@EnableJms
public class AppConfig {

	public AppConfig() {
	}

	@Bean // Strictly speaking this bean is not necessary as boot creates a default
	public JmsListenerContainerFactory<?> myJmsContainerFactory(ConnectionFactory connectionFactory) {
		SimpleJmsListenerContainerFactory factory = new SimpleJmsListenerContainerFactory();
		//		factory.setPubSubDomain(true); // as topic
		factory.setConnectionFactory(connectionFactory);

		return factory;
	}

	@Bean
	public ConnectionFactory jmsConsumerConnection() throws JMSException {
		String brokerUrl = "tcp://localhost:61616";
		ActiveMQConnectionFactory activeMQConnectionFactory = new ActiveMQConnectionFactory(brokerUrl);
		return activeMQConnectionFactory;
	}


	@Bean
	public JmsTemplate jmsTemplate(ConnectionFactory connectionFactory) throws JMSException {
		return new JmsTemplate(connectionFactory);
	}


}
