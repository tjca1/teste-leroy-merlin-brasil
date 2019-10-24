package br.com.tjca1.leroy.merlin.productservice;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;

import java.io.ByteArrayInputStream;
import java.io.File;
import java.io.IOException;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;

import org.apache.commons.io.FileUtils;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import br.com.tjca1.leroy.merlin.orm.ORM;
import br.com.tjca1.leroy.merlin.utils.PoiUtils;

@RunWith(SpringRunner.class)
@SpringBootTest
public class PoiUtilsTest {

	private static final int _LIST_SIZE = 6;
	
	private File file = null;

	@InjectMocks
	private PoiUtils poiUtils;

	@Before
	public void setup() {
		Path resourceDirectory = Paths.get("");

		String resourceName = "planilha_leroy.xlsx";
		file = new File(resourceDirectory.toFile().getAbsolutePath() + "\\" + resourceName);
	}

	@Test
	public void testeExtractXlss()
			throws IOException {
		List<ORM> list = poiUtils.extractProducts(new ByteArrayInputStream(FileUtils.readFileToByteArray(file)));
		assertNotNull(list);
		assertFalse(list.isEmpty());
		assertTrue(list.size() == _LIST_SIZE);
	}

}
