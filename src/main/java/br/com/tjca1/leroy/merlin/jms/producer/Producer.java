package br.com.tjca1.leroy.merlin.jms.producer;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jms.core.JmsTemplate;
import org.springframework.stereotype.Component;

@Component
public class Producer {

	@Autowired
	private JmsTemplate jmsTemplate;
	
	public void sendMessage(Object msg) {
		jmsTemplate.convertAndSend("jms.message.product.sheet", msg);
	}
	
}
