package se.iths.crimedatabase.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import se.iths.crimedatabase.entity.Crime;
import se.iths.crimedatabase.exception.BadRequestException;
import se.iths.crimedatabase.exception.NotFoundException;
import se.iths.crimedatabase.service.CrimeService;

import java.util.Optional;

@RestController
@RequestMapping("crimes")
public class CrimeController {
    private final CrimeService crimeService;

    public CrimeController(CrimeService crimeService) {
        this.crimeService = crimeService;
    }

    @PostMapping
    public ResponseEntity<Crime> create(@RequestBody Crime crime) {
        if (crime.getName().isEmpty())
            throw new BadRequestException("Crime name cannot be empty");

        Crime createdCrime = crimeService.create(crime);
        return new ResponseEntity<>(createdCrime, HttpStatus.CREATED);
    }

    @DeleteMapping("{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        if (crimeService.findById(id).isEmpty())
            throw new NotFoundException(responseMessage(id));

        crimeService.delete(id);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @GetMapping("{id}")
    public ResponseEntity<Optional<Crime>> findById(@PathVariable Long id) {
        if (crimeService.findById(id).isEmpty())
            throw new NotFoundException(responseMessage(id));

        Optional<Crime> foundCrime = crimeService.findById(id);
        return new ResponseEntity<>(foundCrime, HttpStatus.OK);
    }

    @GetMapping
    public ResponseEntity<Iterable<Crime>> findAll() {
        Iterable<Crime> crimes = crimeService.findAll();
        if (!crimes.iterator().hasNext())
            throw new NotFoundException("No crimes found");

        return new ResponseEntity<>(crimes, HttpStatus.FOUND);
    }

    private String responseMessage(Long id) {
        return "Crime with id: " + id + " cannot be found";
    }

    @PutMapping("{id}")
    public ResponseEntity<Crime> update(@PathVariable Long id, @RequestBody Crime crime) {
    /*    if(crime.getId() == null){
            //throw new
        }
       */
        crimeService.update(crime, id);
        return new ResponseEntity<>(HttpStatus.OK);
    }

}
