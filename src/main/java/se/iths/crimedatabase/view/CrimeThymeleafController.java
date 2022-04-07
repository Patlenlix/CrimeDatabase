package se.iths.crimedatabase.view;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;
import se.iths.crimedatabase.entity.*;
import se.iths.crimedatabase.service.*;

@Controller
public class CrimeThymeleafController {

    private final CrimeService crimeService;
    private final CategoryService categoryService;
    private final AddressService addressService;
    private final CriminalService criminalService;
    private final VictimService victimService;

    @Autowired
    public CrimeThymeleafController(CrimeService crimeService, CategoryService categoryService, AddressService addressService, CriminalService criminalService, VictimService victimService) {
        this.crimeService = crimeService;
        this.categoryService = categoryService;
        this.addressService = addressService;
        this.criminalService = criminalService;
        this.victimService = victimService;
    }


    @GetMapping("/crimes")
    public ModelAndView showCrimes() {
        ModelAndView mav = new ModelAndView("list-crimes");
        Iterable<Crime> allCrimes = crimeService.findAll();
        mav.addObject("crimes", allCrimes);
        return mav;
    }

    @GetMapping("/addCrimeForm")
    public ModelAndView addCrimeForm() {
        ModelAndView mav = new ModelAndView("add-crime-form");
        Iterable<Category> listCategories = categoryService.findAll();
        Iterable<Address> listAddresses = addressService.findAll();
        Iterable<Criminal> listCriminals = criminalService.findAll();
        Iterable<Victim> listVictims = victimService.findAll();
        Crime newCrime = new Crime();
        mav.addObject("crime", newCrime);
        mav.addObject("listVictims", listVictims);
        mav.addObject("listCategories", listCategories);
        mav.addObject("listAddresses", listAddresses);
        mav.addObject("listCriminals", listCriminals);
        return mav;
    }

    @PostMapping("/saveCrime")
    public String saveCrime(@ModelAttribute Crime crime) {
        crimeService.create(crime);
        return "redirect:/crimes";
    }

    @GetMapping("/crimeUpdateForm")
    public ModelAndView showCrimeUpdateForm(@RequestParam Long id) {
        ModelAndView mav = new ModelAndView("add-crime-form");
        Crime crime = crimeService.findById(id).orElseThrow();
        Iterable<Category> listCategories = categoryService.findAll();
        Iterable<Address> listAddresses = addressService.findAll();
        Iterable<Criminal> listCriminals = criminalService.findAll();
        Iterable<Victim> listVictims = victimService.findAll();
        mav.addObject("crime", crime);
        mav.addObject("listVictims", listVictims);
        mav.addObject("listCategories", listCategories);
        mav.addObject("listAddresses", listAddresses);
        mav.addObject("listCriminals", listCriminals);
        return mav;
    }

    @GetMapping("/deleteCrime")
    public String deleteCrime(@RequestParam Long id) {
        crimeService.delete(id);
        return "redirect:/crimes";
    }


}
