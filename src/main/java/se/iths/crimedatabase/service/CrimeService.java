package se.iths.crimedatabase.service;

import org.springframework.stereotype.Service;
import se.iths.crimedatabase.entity.Crime;
import se.iths.crimedatabase.repository.CrimeRepository;

import javax.persistence.EntityNotFoundException;
import java.util.Optional;

@Service
public class CrimeService {
    private final CrimeRepository crimeRepository;

    public CrimeService(CrimeRepository crimeRepository) {
        this.crimeRepository = crimeRepository;
    }

    public Crime create(Crime crime) {
        return crimeRepository.save(crime);
    }

    public void delete(Long id) {
        Crime foundCrime = crimeRepository.findById(id).orElseThrow(EntityNotFoundException::new);
        crimeRepository.deleteById(foundCrime.getId());
    }

    public Optional<Crime> findById(Long id) {
        return crimeRepository.findById(id);
    }

    public Iterable<Crime> findAll() {
        return crimeRepository.findAll();
    }


    public void update(Crime crime, Long id) {
        if (findById(id).isEmpty()) {
            //throw new
        }
        crime.setId(id);
        crimeRepository.save(crime);
    }


}
