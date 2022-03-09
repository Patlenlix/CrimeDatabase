package se.iths.crimedatabase.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import se.iths.crimedatabase.entity.Criminal;

@Repository
public interface CriminalRepository extends CrudRepository<Criminal, Long> {
}
