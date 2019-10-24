package br.com.tjca1.leroy.merlin.productservice;

import static org.junit.Assert.assertNotNull;
import static org.mockito.Mockito.when;
import java.util.Optional;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.ArgumentMatchers;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import br.com.tjca1.leroy.merlin.orm.LogORM;
import br.com.tjca1.leroy.merlin.repository.LogRepository;
import br.com.tjca1.leroy.merlin.service.LogServices;

@RunWith(SpringRunner.class)
@SpringBootTest
public class LogServiceTest {

	@InjectMocks
	private LogServices service;
	
	@Mock
	private LogRepository repository;
	
	@Test(expected = IllegalArgumentException.class)
	public void testLogByIdEmpty() {
		when(repository.findById(ArgumentMatchers.anyLong())).thenReturn(Optional.empty());
		
		service.getLogById(1L);
	}

	@Test
	public void testLogByIdLong() {
		when(repository.findById(ArgumentMatchers.anyLong())).thenReturn(Optional.of(new LogORM()));
		LogORM logORM = service.getLogById(1L);
		assertNotNull(logORM);
	}
	
}
