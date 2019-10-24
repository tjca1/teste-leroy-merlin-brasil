package br.com.tjca1.leroy.merlin.jms.message.builder;

import java.io.IOException;

import org.springframework.stereotype.Component;
import org.springframework.web.multipart.MultipartFile;

import br.com.tjca1.leroy.merlin.jms.message.Message;
import br.com.tjca1.leroy.merlin.orm.LogORM;



@Component
public class MessageBuilder {

	public Message build(MultipartFile file, LogORM logORM)
			throws IOException {
		Message message = new Message();
		message.setFileName(file.getOriginalFilename());
		message.setFileContent(file.getBytes());
		message.setProcessId(logORM.getId());

		return message;
	}

}
