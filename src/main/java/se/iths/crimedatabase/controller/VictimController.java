package se.iths.crimedatabase.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import se.iths.crimedatabase.entity.Victim;
import se.iths.crimedatabase.service.VictimService;

import java.util.Optional;

@RestController
@RequestMapping("victims")
public class VictimController {
    private final VictimService victimService;

    public VictimController(VictimService victimService) {
        this.victimService = victimService;
    }

    @PostMapping
    public ResponseEntity<Victim> create(@RequestBody Victim victim) {
        Victim createdVictim = victimService.create(victim);
        return new ResponseEntity<>(createdVictim, HttpStatus.CREATED);
    }

    @DeleteMapping("{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        victimService.delete(id);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @GetMapping("{id}")
    public ResponseEntity<Optional<Victim>> findById(@PathVariable Long id) {
        Optional<Victim> foundVictim = victimService.findById(id);
        return new ResponseEntity<>(foundVictim, HttpStatus.OK);
    }

    @GetMapping
    public ResponseEntity<Iterable<Victim>> findAll() {
        Iterable<Victim> victims = victimService.findAll();
        return new ResponseEntity<>(victims, HttpStatus.OK);
    }

    @PutMapping("{id}")
    public ResponseEntity<Victim> update(@PathVariable Long id, @RequestBody Victim victim) {
    /*    if(victim.getId() == null){
            //throw new
        }
       */
        victimService.update(victim, id);
        return new ResponseEntity<>(HttpStatus.OK);
    }

}
