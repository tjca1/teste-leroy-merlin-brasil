package br.com.tjca1.leroy.merlin.service;

import java.util.Date;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import br.com.tjca1.leroy.merlin.constant.Status;
import br.com.tjca1.leroy.merlin.jpa.builder.Builder;
import br.com.tjca1.leroy.merlin.orm.LogORM;
import br.com.tjca1.leroy.merlin.repository.LogRepository;

@Service
public class LogServices {

	@Autowired
	private Builder builder; 

	@Autowired
	private LogRepository log;

	public LogORM createLog(MultipartFile file) {
		LogORM l  = builder.build(file);
		l = log.save(l );
		return l;
	}

	public void updateLogStatusInProcessing(Long processId) {
		Optional<LogORM> o = log.findById(processId);
		LogORM l = o.get();
		l.setStatus(Status.PROCESSING);
		log.save(l);
	}
	
	public void updateLogStatusSuccess(Long processId) {
		Optional<LogORM> o = log.findById(processId);
		LogORM l  = o.get();
		l.setStatus(Status.PROCESSED_SUCCESS);
		l.setEndProcessDate(new Date());
		log.save(l);
	}
	
	public void updateLogStatusError(Long processId, String errorMessage) {
		Optional<LogORM> o = log.findById(processId);
		LogORM l = o.get();
		l.setStatus(Status.PROCESSED_ERROR);
		l.setEndProcessDate(new Date());
		l.setMessageError(errorMessage);
		log.save(l);
	}
	
	public LogORM getLogById(Long id) {
		Optional<LogORM> o = log.findById(id);
		if(o.isPresent()) {
			return o.get();
		}else {
			throw new IllegalArgumentException("Log de processamento inválido!");
		}
	}

}
