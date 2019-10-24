package br.com.tjca1.leroy.merlin.productservice;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

import org.apache.commons.io.FileUtils;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.mock.web.MockMultipartFile;
import org.springframework.test.context.junit4.SpringRunner;

import br.com.tjca1.leroy.merlin.rest.validator.RestValidator;
;

@RunWith(SpringRunner.class)
@SpringBootTest
public class ValidatorTest {

	@InjectMocks
	private RestValidator validator;

	private File validFile = null;
	
	private File invalidFile = null;
	
	@Before
	public void setup() {
		
		Path resourceDirectory = Paths.get("");
		
		String resourceName = "itens_laroy.docx";
		invalidFile = new File(resourceDirectory.toFile().getAbsolutePath() + "\\" + resourceName);
		
		resourceName = "itens_laroy.xlsx";
		validFile = new File(resourceDirectory.toFile().getAbsolutePath() + "\\" + resourceName);
	}
	

	@Test
	public void testVazioInvalido() throws IOException {
		validator.validateFileIsEmpty(new MockMultipartFile(validFile.getName(), FileUtils.readFileToByteArray(validFile)));
	}
	
	@Test(expected = IllegalArgumentException.class)
	public void testNomeInvalido() throws IOException {
		validator.validateMimeType(new MockMultipartFile(invalidFile.getName(), invalidFile.getName(), "",
				Files.readAllBytes(invalidFile.toPath())));
	}

	@Test
	public void testArquivoInvalido() throws IOException {
		validator.validateMimeType(new MockMultipartFile(validFile.getName(), validFile.getName(), "",
				Files.readAllBytes(validFile.toPath())));
	}
	
}
