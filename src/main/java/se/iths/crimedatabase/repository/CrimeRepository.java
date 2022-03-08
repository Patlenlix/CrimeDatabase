package se.iths.crimedatabase.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import se.iths.crimedatabase.entity.Crime;

@Repository
public interface CrimeRepository extends CrudRepository<Crime, Long> {

}
