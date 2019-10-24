package br.com.tjca1.leroy.merlin.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import br.com.tjca1.leroy.merlin.orm.ORM;
import org.springframework.stereotype.Repository;

/**
 * 
 * @author Thiago Araujo
 *
 */
@Repository
public interface Repositor extends JpaRepository<ORM, Long> {

}
