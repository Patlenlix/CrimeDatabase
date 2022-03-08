package se.iths.crimedatabase.service;

import org.springframework.stereotype.Service;
import se.iths.crimedatabase.entity.Address;
import se.iths.crimedatabase.repository.AddressRepository;

import javax.persistence.EntityNotFoundException;
import java.util.Optional;

@Service
public class AddressService {
    private final AddressRepository addressRepository;

    public AddressService(AddressRepository addressRepository) {
        this.addressRepository = addressRepository;
    }

    public Address create(Address address) {
        return addressRepository.save(address);
    }

    public void delete(Long id) {
        Address foundAddress = addressRepository.findById(id).orElseThrow(EntityNotFoundException::new);
        addressRepository.deleteById(foundAddress.getId());
    }

    public Optional<Address> findById(Long id) {
        return addressRepository.findById(id);
    }

    public Iterable<Address> findAll() {
        return addressRepository.findAll();
    }

}
