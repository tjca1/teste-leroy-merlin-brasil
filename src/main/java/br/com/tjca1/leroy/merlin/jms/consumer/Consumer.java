package br.com.tjca1.leroy.merlin.jms.consumer;

import java.io.IOException;

import javax.jms.JMSException;

import org.apache.activemq.command.ActiveMQTextMessage;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jms.annotation.JmsListener;
import org.springframework.stereotype.Component;

import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.databind.JsonMappingException;

import br.com.tjca1.leroy.merlin.jms.message.Message;
import br.com.tjca1.leroy.merlin.service.ProcessServices;
import br.com.tjca1.leroy.merlin.utils.JsonUtils;

@Component
public class Consumer {

	private Logger logger = LoggerFactory.getLogger(Consumer.class);

	@Autowired
	private ProcessServices processServices;

	@JmsListener(destination = "jms.message.product.sheet")
	public void receiveMessage(Object message) throws JMSException, JsonParseException, JsonMappingException, IOException {
		logger.info("Received " + message);

		if (message instanceof ActiveMQTextMessage) {
			ActiveMQTextMessage activeMQTextMessage = (ActiveMQTextMessage) message;
			
			Message bodyMessage = (Message) JsonUtils
					.getObject(activeMQTextMessage.getText(), Message.class);
			
			processServices.processSheet(bodyMessage);
		} else {
			throw new IllegalArgumentException(
					String.format("Invalid message at queue %s", "jms.message.product.sheet"));
		}

	}

}
