package se.iths.crimedatabase.init;

import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import se.iths.crimedatabase.entity.*;
import se.iths.crimedatabase.repository.*;

import java.time.LocalDate;
import java.time.LocalDateTime;

@Configuration
public class InitialData {

    @Bean
    public CommandLineRunner setUpSampleAddressData(AddressRepository addressRepository) {
        return args -> {
            if (addressRepository.findById(1L).isEmpty())
                addressRepository.save(new Address("Gothenburg", "41111", "Street 1"));
            if (addressRepository.findById(2L).isEmpty())
                addressRepository.save(new Address("Lerum", "31111", "Street 2"));
        };
    }

    @Bean
    public CommandLineRunner setUpSampleCategoryData(CategoryRepository categoryRepository) {
        return args -> {
            if (categoryRepository.findById(1L).isEmpty())
                categoryRepository.save(new Category("Theft"));
            if (categoryRepository.findById(2L).isEmpty())
                categoryRepository.save(new Category("Jay-walking"));
        };
    }

    @Bean
    public CommandLineRunner setUpSampleCrimeData(CrimeRepository crimeRepository) {
        return args -> {
            if (crimeRepository.findById(1L).isEmpty())
                crimeRepository.save(new Crime("Jewelry theft", LocalDateTime.of(2020, 1, 1, 10, 0)));
            if (crimeRepository.findById(2L).isEmpty())
                crimeRepository.save(new Crime("Stopping traffic", LocalDateTime.of(2021, 2, 2, 20, 0)));
        };
    }

    @Bean
    public CommandLineRunner setUpSampleCriminalData(CriminalRepository criminalRepository) {
        return args -> {
            if (criminalRepository.findById(1L).isEmpty())
                criminalRepository.save(new Criminal("John", "Doe", LocalDate.of(1985, 3, 3)));
            if (criminalRepository.findById(2L).isEmpty())
                criminalRepository.save(new Criminal("Jane", "Dawson", LocalDate.of(1995, 4, 4)));
        };
    }

    @Bean
    public CommandLineRunner setUpSampleVictimData(VictimRepository victimRepository) {
        return args -> {
            if (victimRepository.findById(1L).isEmpty())
                victimRepository.save(new Victim("Matt", "Mark", LocalDate.of(1975, 5, 5)));
            if (victimRepository.findById(2L).isEmpty())
                victimRepository.save(new Victim("Louise", "Clark", LocalDate.of(1965, 6, 6)));
        };
    }
}
