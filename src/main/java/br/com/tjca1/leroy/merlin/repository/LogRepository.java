package br.com.tjca1.leroy.merlin.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import br.com.tjca1.leroy.merlin.orm.LogORM;

@Repository
public interface LogRepository extends JpaRepository<LogORM, Long> {

}
