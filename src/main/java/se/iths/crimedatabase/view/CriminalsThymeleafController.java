package se.iths.crimedatabase.view;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import se.iths.crimedatabase.service.AddressService;
import se.iths.crimedatabase.service.CrimeService;
import se.iths.crimedatabase.service.CriminalService;

@Controller
public class CriminalsThymeleafController {

    private final CriminalService service;
    private final CrimeService crimeService;
    private final AddressService addressService;

    @Autowired
    public CriminalsThymeleafController(CriminalService service, CrimeService crimeService, AddressService addressService) {
        this.service = service;
        this.crimeService = crimeService;
        this.addressService = addressService;
    }
    

}