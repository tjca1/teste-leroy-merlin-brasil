package br.com.tjca1.leroy.merlin.service;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import br.com.tjca1.leroy.merlin.constant.Messages;
import br.com.tjca1.leroy.merlin.jms.message.Message;
import br.com.tjca1.leroy.merlin.jms.message.builder.MessageBuilder;
import br.com.tjca1.leroy.merlin.jms.producer.Producer;
import br.com.tjca1.leroy.merlin.orm.LogORM;
import br.com.tjca1.leroy.merlin.orm.ORM;
import br.com.tjca1.leroy.merlin.repository.Repositor;
import br.com.tjca1.leroy.merlin.rest.response.RestResponse;
import br.com.tjca1.leroy.merlin.utils.PoiUtils;

@Service
public class ProcessServices {

	private Logger logger = LoggerFactory.getLogger(ProcessServices.class);

	@Autowired
	private Producer producer;

	@Autowired
	private LogServices logService;

	@Autowired
	private MessageBuilder builder;

	@Autowired
	private Repositor repository;

	@Autowired
	private PoiUtils sheetUtils;

	public RestResponse importSheet(MultipartFile file) throws IOException {

		LogORM logORM = logService.createLog(file);

		Message message = builder.build(file, logORM);

		producer.sendMessage(message);
		RestResponse response = new RestResponse(logORM.getId(),
				Messages.IMPORT);

		return response;
	}

	public void processSheet(Message message) {

		try {

			logService.updateLogStatusInProcessing(message.getProcessId());
			List<ORM> l = sheetUtils.extractProducts(new ByteArrayInputStream(message.getFileContent()));
			List<String> errors = new ArrayList<String>();

			l.forEach(entity -> {
				try {
					repository.save(entity);
				} catch (Exception e) {
					errors.add(e.getMessage());
				}
			});

			if (!errors.isEmpty()) {
				logService.updateLogStatusError(message.getProcessId(), errors.toString());
			} else {
				logService.updateLogStatusSuccess(message.getProcessId());
			}

		} catch (Exception e) {
			logService.updateLogStatusError(message.getProcessId(), e.getMessage());
			logger.error(e.getMessage(), e);
		}

	}

}
