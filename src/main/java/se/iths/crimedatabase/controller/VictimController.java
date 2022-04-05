package se.iths.crimedatabase.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import se.iths.crimedatabase.entity.Victim;
import se.iths.crimedatabase.exception.BadRequestException;
import se.iths.crimedatabase.exception.MethodNotAllowedException;
import se.iths.crimedatabase.exception.NotFoundException;
import se.iths.crimedatabase.service.VictimService;

import java.util.Optional;

@RestController
@RequestMapping("api/victims")
public class VictimController {
    private final VictimService victimService;

    public VictimController(VictimService victimService) {
        this.victimService = victimService;
    }

    @PostMapping
    public ResponseEntity<Victim> create(@RequestBody Victim victim) {
        if (victim.getFirstName().isEmpty() || victim.getLastName().isEmpty())
            throw new BadRequestException("First and last name cannot be empty");

        Victim createdVictim = victimService.create(victim);
        return new ResponseEntity<>(createdVictim, HttpStatus.CREATED);
    }

    @DeleteMapping("{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        if (victimService.findById(id).isEmpty())
            throw new NotFoundException(notFoundMessage(id));

        victimService.delete(id);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @GetMapping("{id}")
    public ResponseEntity<Optional<Victim>> findById(@PathVariable Long id) {
        if (victimService.findById(id).isEmpty())
            throw new NotFoundException(notFoundMessage(id));

        Optional<Victim> foundVictim = victimService.findById(id);
        return new ResponseEntity<>(foundVictim, HttpStatus.OK);
    }

    @GetMapping
    public ResponseEntity<Iterable<Victim>> findAll() {
        Iterable<Victim> victims = victimService.findAll();
        if (!victims.iterator().hasNext())
            throw new NotFoundException("No victims found");

        return new ResponseEntity<>(victims, HttpStatus.OK);
    }

    @PutMapping("{id}")
    public ResponseEntity<Victim> update(@PathVariable Long id, @RequestBody Victim victim) {
        if (victimService.findById(id).isEmpty())
            throw new MethodNotAllowedException("Victim with id: " + id + " cannot be updated since it does not exist");

        victimService.update(victim, id);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    private String notFoundMessage(Long id) {
        return "Victim with id: " + id + " cannot be found";
    }

}
