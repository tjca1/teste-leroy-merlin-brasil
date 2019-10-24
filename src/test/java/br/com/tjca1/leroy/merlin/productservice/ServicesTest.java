package br.com.tjca1.leroy.merlin.productservice;

import static org.junit.Assert.assertNotNull;
import static org.mockito.Mockito.when;

import java.util.ArrayList;
import java.util.Optional;

import javax.persistence.EntityNotFoundException;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.ArgumentMatchers;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.test.context.junit4.SpringRunner;
import br.com.tjca1.leroy.merlin.orm.ORM;
import br.com.tjca1.leroy.merlin.repository.Repositor;
import br.com.tjca1.leroy.merlin.service.Services;

@RunWith(SpringRunner.class)
@SpringBootTest
@SuppressWarnings({ "unchecked", "rawtypes" })
public class ServicesTest {

	@InjectMocks
	private Services Service;

	@Mock
	private Repositor repo;

	@Test
	public void testByIdLong() {
		when(repo.findById(ArgumentMatchers.anyLong())).thenReturn(Optional.of(new ORM()));

		ORM b = Service.getById(1L);
		assertNotNull(b);
	}

	@Test(expected = EntityNotFoundException.class)
	public void testeByIdEmpty() {
		when(repo.findById(ArgumentMatchers.anyLong())).thenReturn(Optional.empty());

		Service.getById(1L);
	}

	@Test(expected = EntityNotFoundException.class)
	public void testAllByPage() {
		Integer pageNumber = 0;
		Integer pageSize = 25;
		when(repo.findAll(PageRequest.of(pageNumber, pageSize)))
				.thenReturn(new PageImpl(new ArrayList<>(0)));
		Service.getAllByPage(pageNumber, pageSize);
	}

	@Test
	public void testDeleteByIdLong() {
		
		Long id = ArgumentMatchers.anyLong();
		when(repo.findById(id)).thenReturn(Optional.of(new ORM(id)));
		Service.deleteById(id);
	}

	@Test(expected = IllegalArgumentException.class)
	public void testDeleteByIdEmpty() {
		when(repo.findById(ArgumentMatchers.anyLong())).thenReturn(Optional.empty());
		Service.deleteById(ArgumentMatchers.anyLong());
	}

}
