package br.com.tjca1.leroy.merlin.rest.validator;

import org.apache.tika.Tika;
import org.springframework.stereotype.Component;
import org.springframework.web.multipart.MultipartFile;
import br.com.tjca1.leroy.merlin.constant.MimeType;

@Component
public class RestValidator {

	public void validate(MultipartFile file) {
		
		validateFileIsEmpty(file);
		
		validateMimeType(file);
		
	}
	
	public void validateFileIsEmpty(MultipartFile file) {
		if(file.isEmpty()) {
			throw new IllegalArgumentException("Arquivo inválido!");
		}
	}
	
	public void validateMimeType(MultipartFile file) {
		Tika tika = new Tika();
		try {
			String mimeType = tika.detect(file.getOriginalFilename());
			if(!mimeType.equals(MimeType.XLS_TYPE)) {
				throw new RuntimeException();
			}
		} catch (Exception e) {
			throw new IllegalArgumentException("Arquivo inválido!");
		}
	}
	
}
