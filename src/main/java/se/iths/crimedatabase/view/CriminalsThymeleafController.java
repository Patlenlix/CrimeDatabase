package se.iths.crimedatabase.view;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;
import se.iths.crimedatabase.entity.Address;
import se.iths.crimedatabase.entity.Crime;
import se.iths.crimedatabase.entity.Criminal;
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

    @GetMapping("/criminals")
    public ModelAndView showCriminals() {
        ModelAndView mav = new ModelAndView("list-criminals");
        Iterable<Criminal> allCriminals = service.findAll();
        mav.addObject("criminals", allCriminals);
        return mav;
    }

    @GetMapping("/addCriminalForm")
    public ModelAndView addCategoryForm() {
        ModelAndView mav = new ModelAndView("add-criminal-form");
        Iterable<Crime> listCrimes = crimeService.findAll();
        Iterable<Address> listAddresses = addressService.findAll();
        Criminal newCriminal = new Criminal();
        mav.addObject("listAddresses", listAddresses);
        mav.addObject("listCrimes", listCrimes);
        mav.addObject("criminal", newCriminal);
        return mav;
    }

    @PostMapping("/saveCriminal")
    public String saveCriminal(@ModelAttribute Criminal criminal) {
        service.create(criminal);
        return "redirect:/criminals";
    }

    @GetMapping("/criminalUpdateForm")
    public ModelAndView showUpdateForm(@RequestParam Long id) {
        ModelAndView mav = new ModelAndView("add-criminal-form");
        Iterable<Crime> listCrimes = crimeService.findAll();
        Iterable<Address> listAddresses = addressService.findAll();
        Criminal criminal = service.findById(id).orElseThrow();
        mav.addObject("listAddresses", listAddresses);
        mav.addObject("listCrimes", listCrimes);
        mav.addObject("criminal", criminal);
        return mav;
    }

    @GetMapping("/deleteCriminal")
    public String deleteCriminal(@RequestParam Long id) {
        service.delete(id);
        return "redirect:/criminals";
    }

}