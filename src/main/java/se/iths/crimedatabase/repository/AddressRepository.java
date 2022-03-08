package se.iths.crimedatabase.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import se.iths.crimedatabase.entity.Address;

@Repository
public interface AddressRepository extends CrudRepository<Address, Long> {

}
