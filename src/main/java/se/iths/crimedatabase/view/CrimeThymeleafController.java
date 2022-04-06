package se.iths.crimedatabase.view;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.servlet.ModelAndView;
import se.iths.crimedatabase.entity.Crime;
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


    @GetMapping("/crimes")
    public ModelAndView showCrimes() {
        ModelAndView mav = new ModelAndView("list-crimes");
        Iterable<Crime> allCrimes = crimeService.findAll();
        mav.addObject("crimes", allCrimes);
        return mav;
    }
    

}