package com.java.springWithJMS;

import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.jms.annotation.EnableJms;
import org.springframework.jms.core.JmsTemplate;
import org.springframework.jms.core.MessageCreator;

import com.java.springWithJMS.config.AppConfig;

@EnableJms
public class Application {

	private static AnnotationConfigApplicationContext applicationContext ;


	public static void main(String[] args) {

		// Launch the application
		applicationContext  = new AnnotationConfigApplicationContext(
				AppConfig.class
				);

		// Send a message
		MessageCreator messageCreator = session -> session.createTextMessage("ping!");
		JmsTemplate jmsTemplate = applicationContext.getBean(JmsTemplate.class);
		System.out.println("Sending a new message.");
		jmsTemplate.send("mailbox-destination", messageCreator);
	}

}
