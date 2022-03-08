package se.iths.crimedatabase.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import se.iths.crimedatabase.entity.Crime;
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
        Crime createdCrime = crimeService.create(crime);
        return new ResponseEntity<>(createdCrime, HttpStatus.CREATED);
    }

    @DeleteMapping("{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        crimeService.delete(id);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @GetMapping("{id}")
    public ResponseEntity<Optional<Crime>> findById(@PathVariable Long id) {
        Optional<Crime> foundCrime = crimeService.findById(id);
        return new ResponseEntity<>(foundCrime, HttpStatus.OK);
    }

    @GetMapping
    public ResponseEntity<Iterable<Crime>> findAll() {
        Iterable<Crime> crimes = crimeService.findAll();
        return new ResponseEntity<>(crimes, HttpStatus.OK);
    }

}
