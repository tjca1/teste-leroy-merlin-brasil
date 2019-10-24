package br.com.tjca1.leroy.merlin.service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import javax.persistence.EntityNotFoundException;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;
import br.com.tjca1.leroy.merlin.orm.ORM;
import br.com.tjca1.leroy.merlin.repository.Repositor;

@Service
public class Services {

	private Logger logger = LoggerFactory.getLogger(Services.class);
	
	@Autowired
	private Repositor Repositor;

	public ORM getById(Long id) {
		Optional<ORM> optional = Repositor.findById(id);
		if (optional.isPresent()) {
			return optional.get();
		} else {
			throw new EntityNotFoundException("Produto não encontrado!");
		}
	}

	public List<ORM> getAllByPage(Integer pageNumber, Integer pageSize) {

		Page<ORM> sResult = Repositor
				.findAll(PageRequest.of(pageNumber == null ? 0 : pageNumber, pageSize == null ? 25 : pageSize));
		List<ORM> s = sResult.stream().collect(Collectors.toList());

		if (!s.isEmpty()) {
			return s;
		} else {
			throw new EntityNotFoundException("Produto não encontrado!");
		}
	}
	
	public void deleteById(Long id) {
		Optional<ORM> optional = Repositor.findById(id);
		
		if(optional.isPresent()) {
			Repositor.deleteById(id);
		} else {
			throw new IllegalArgumentException("O Produto não existe!");
		}
	}
	
	public void update(ORM p) {
		try {
			Repositor.save(p);
		} catch (Exception e) {
			logger.error(e.getMessage(), e);
			throw new IllegalArgumentException("O Produto informado não pode ser atualizado!");
		}
		
	}

}
