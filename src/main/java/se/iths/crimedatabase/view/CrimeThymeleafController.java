package se.iths.crimedatabase.view;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import se.iths.crimedatabase.service.AddressService;
import se.iths.crimedatabase.service.CategoryService;
import se.iths.crimedatabase.service.CrimeService;
import se.iths.crimedatabase.service.CriminalService;

@Controller
public class CrimeThymeleafController {

    private final CrimeService crimeService;
    private final CategoryService categoryService;
    private final AddressService addressService;
    private final CriminalService criminalService;


    @Autowired
    public CrimeThymeleafController(CrimeService service, CategoryService categoryService, AddressService addressService, CriminalService criminalService) {
        this.crimeService = service;
        this.categoryService = categoryService;
        this.addressService = addressService;
        this.criminalService = criminalService;
    }


}