package se.iths.crimedatabase.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import se.iths.crimedatabase.entity.Address;
import se.iths.crimedatabase.exception.BadRequestException;
import se.iths.crimedatabase.exception.NotFoundException;
import se.iths.crimedatabase.service.AddressService;

import java.util.Optional;

@RestController
@RequestMapping("addresses")
public class AddressController {
    private final AddressService addressService;

    public AddressController(AddressService addressService) {
        this.addressService = addressService;
    }

    @PostMapping
    public ResponseEntity<Address> create(@RequestBody Address address) {
        if (address.getStreetAddress().isEmpty() || address.getCity().isEmpty() || address.getZipCode().isEmpty())
            throw new BadRequestException("Street address, city and zipcode cannot be empty");

        Address createdAddress = addressService.create(address);
        return new ResponseEntity<>(createdAddress, HttpStatus.CREATED);
    }

    @DeleteMapping("{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        if (addressService.findById(id).isEmpty())
            throw new NotFoundException(responseMessage(id));

        addressService.delete(id);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @GetMapping("{id}")
    public ResponseEntity<Optional<Address>> findById(@PathVariable Long id) {
        if (addressService.findById(id).isEmpty())
            throw new NotFoundException(responseMessage(id));

        Optional<Address> foundAddress = addressService.findById(id);
        return new ResponseEntity<>(foundAddress, HttpStatus.OK);
    }

    @GetMapping
    public ResponseEntity<Iterable<Address>> findAll() {
        Iterable<Address> addresses = addressService.findAll();
        if (!addresses.iterator().hasNext())
            throw new NotFoundException("No addresses found");

        return new ResponseEntity<>(addresses, HttpStatus.FOUND);
    }

    private String responseMessage(Long id) {
        return "Address with id: " + id + " cannot be found";
    }
}
