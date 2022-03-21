package se.iths.crimedatabase.service;

import org.springframework.stereotype.Service;
import se.iths.crimedatabase.entity.Criminal;
import se.iths.crimedatabase.repository.CriminalRepository;

import javax.persistence.EntityNotFoundException;
import java.util.Optional;

@Service
public class CriminalService {
    private final CriminalRepository criminalRepository;

    public CriminalService(CriminalRepository criminalService) {
        this.criminalRepository = criminalService;
    }

    public Criminal create(Criminal criminal) {
        return criminalRepository.save(criminal);
    }

    public void delete(Long id) {
        Criminal foundCriminal = criminalRepository.findById(id).orElseThrow(EntityNotFoundException::new);
        criminalRepository.deleteById(foundCriminal.getId());
    }

    public Optional<Criminal> findById(Long id) {
        return criminalRepository.findById(id);
    }

    public Iterable<Criminal> findAll() {
        return criminalRepository.findAll();
    }

    public void update(Criminal criminal, Long id) {
        if (findById(id).isEmpty()) {
            //throw new
        }
        criminal.setId(id);
        criminalRepository.save(criminal);
    }

}
