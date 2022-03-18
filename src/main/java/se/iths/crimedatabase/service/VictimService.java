package se.iths.crimedatabase.service;

import org.springframework.stereotype.Service;
import se.iths.crimedatabase.entity.Victim;
import se.iths.crimedatabase.repository.VictimRepository;

import javax.persistence.EntityNotFoundException;
import java.util.Optional;

@Service
public class VictimService {
    private final VictimRepository victimRepository;

    public VictimService(VictimRepository victimRepository) {
        this.victimRepository = victimRepository;
    }

    public Victim create(Victim victim) {
        return victimRepository.save(victim);
    }

    public void delete(Long id) {
        Victim foundVictim = victimRepository.findById(id).orElseThrow(EntityNotFoundException::new);
        victimRepository.deleteById(foundVictim.getId());
    }

    public Optional<Victim> findById(Long id) {
        return victimRepository.findById(id);
    }

    public Iterable<Victim> findAll() {
        return victimRepository.findAll();
    }

    public void update(Victim victim, Long id) {
        if (findById(id).isEmpty()) {
            //throw new
        }
        victim.setId(id);
        victimRepository.save(victim);
    }

}
