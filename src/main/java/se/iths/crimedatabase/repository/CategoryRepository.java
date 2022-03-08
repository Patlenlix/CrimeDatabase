package se.iths.crimedatabase.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import se.iths.crimedatabase.entity.Category;

@Repository
public interface CategoryRepository extends CrudRepository<Category, Long> {
}
