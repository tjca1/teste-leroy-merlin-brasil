package br.com.tjca1.leroy.merlin.jpa.builder;

import java.util.Date;

import org.springframework.stereotype.Component;
import org.springframework.web.multipart.MultipartFile;

import br.com.tjca1.leroy.merlin.constant.Status;
import br.com.tjca1.leroy.merlin.orm.LogORM;


@Component
public class Builder {

	public LogORM build(MultipartFile arq) {
		
		LogORM orm = new LogORM();
		orm.setFileName(arq.getOriginalFilename());
		orm.setStartProcessDate(new Date());
		orm.setStatus(Status.WAITING_PROCESS);
		
		return orm;
	}
	
}
