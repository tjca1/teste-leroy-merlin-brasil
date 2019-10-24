package br.com.tjca1.leroy.merlin.rest.controller;
import java.io.IOException;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;
import br.com.tjca1.leroy.merlin.orm.LogORM;
import br.com.tjca1.leroy.merlin.orm.ORM;
import br.com.tjca1.leroy.merlin.rest.response.RestResponse;
import br.com.tjca1.leroy.merlin.rest.validator.RestValidator;
import br.com.tjca1.leroy.merlin.service.LogServices;
import br.com.tjca1.leroy.merlin.service.ProcessServices;
import br.com.tjca1.leroy.merlin.service.Services;

@RestController
@SuppressWarnings("rawtypes")
public class Controller {

	@Autowired
	private RestValidator validator;

	@Autowired
	private ProcessServices processServices;
	
	@Autowired
	private LogServices logService;
	
	@Autowired
	private Services service;

	@PostMapping(path = "/products/sheets", produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
	public ResponseEntity importProductSheet(@RequestParam("sheetFile") MultipartFile sheetFile) throws IOException {

		validator.validate(sheetFile);

		RestResponse response = processServices.importSheet(sheetFile);

		return ResponseEntity.ok(response);
	}

	@GetMapping(path = "/products/sheets/status/{id}", produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
	public ResponseEntity getImportSheetStatus(@PathVariable("id") Long importProcessId) {
		LogORM log = logService.getLogById(importProcessId);
		return ResponseEntity.ok(log);
	}

	@GetMapping(path = "/products", produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
	public ResponseEntity getAllProducts(@RequestParam(value = "pageNumber", required = false) Integer pageNumber,
			@RequestParam(value = "pageSize", required = false) Integer pageSize) {
		List<ORM> list = service.getAllByPage(pageNumber, pageSize);
		return ResponseEntity.ok(list);
	}

	@GetMapping(path = "/products/{id}", produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
	public ResponseEntity getProductById(@PathVariable("id") Long id) {
		ORM o = service.getById(id);
		return ResponseEntity.ok(o);
	}

	@PutMapping(path = "/products", consumes = MediaType.APPLICATION_JSON_UTF8_VALUE, produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
	public ResponseEntity updateProduct(@RequestBody ORM o ) {
		service.update(o);
		return ResponseEntity.ok().build();
	}

	@DeleteMapping(path = "/products/{id}", produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
	public ResponseEntity deleteProductById(@PathVariable("id") Long id) {
		service.deleteById(id);
		return ResponseEntity.ok().build();
	}

}
