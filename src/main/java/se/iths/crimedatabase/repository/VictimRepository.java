package se.iths.crimedatabase.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import se.iths.crimedatabase.entity.Victim;

@Repository
public interface VictimRepository extends CrudRepository<Victim, Long> {

}
