package se.iths.crimedatabase.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import se.iths.crimedatabase.entity.Criminal;
import se.iths.crimedatabase.exception.BadRequestException;
import se.iths.crimedatabase.exception.NotFoundException;
import se.iths.crimedatabase.service.CriminalService;

import java.util.Optional;

@RestController
@RequestMapping("criminals")
public class CriminalController {
    private final CriminalService criminalService;

    public CriminalController(CriminalService criminalService) {
        this.criminalService = criminalService;
    }

    @PostMapping
    public ResponseEntity<Criminal> create(@RequestBody Criminal criminal) {
        if(criminal.getFirstName().isEmpty() || criminal.getLastName().isEmpty())
            throw new BadRequestException("First and last name cannot be empty");

        Criminal createdCriminal = criminalService.create(criminal);
        return new ResponseEntity<>(createdCriminal, HttpStatus.CREATED);
    }

    @DeleteMapping("{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        if (criminalService.findById(id).isEmpty())
            throw new NotFoundException(responseMessage(id));

        criminalService.delete(id);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @GetMapping("{id}")
    public ResponseEntity<Optional<Criminal>> findById(@PathVariable Long id) {
        if (criminalService.findById(id).isEmpty())
            throw new NotFoundException(responseMessage(id));

        Optional<Criminal> foundCriminal = criminalService.findById(id);
        return new ResponseEntity<>(foundCriminal, HttpStatus.OK);
    }

    @GetMapping
    public ResponseEntity<Iterable<Criminal>> findAll() {
        Iterable<Criminal> criminals = criminalService.findAll();
        if (!criminals.iterator().hasNext())
            throw new NotFoundException("No criminals found");

        return new ResponseEntity<>(criminals, HttpStatus.OK);
    }

    private String responseMessage(Long id) {
        return "Criminal with id: " + id + " cannot be found";
    }
}
