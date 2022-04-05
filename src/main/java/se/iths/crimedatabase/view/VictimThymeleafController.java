package se.iths.crimedatabase.view;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.ModelAndView;
import se.iths.crimedatabase.entity.Address;
import se.iths.crimedatabase.entity.Crime;
import se.iths.crimedatabase.entity.Victim;
import se.iths.crimedatabase.service.AddressService;
import se.iths.crimedatabase.service.CrimeService;
import se.iths.crimedatabase.service.VictimService;

@Controller
public class VictimThymeleafController {

    private final VictimService victimService;
    private final CrimeService crimeService;
    private final AddressService addressService;

    @Autowired
    public VictimThymeleafController(VictimService victimService, CrimeService crimeService, AddressService addressService) {
        this.victimService = victimService;
        this.crimeService = crimeService;
        this.addressService = addressService;
    }

    @GetMapping("/victims")
    public ModelAndView showVictims() {
        ModelAndView mav = new ModelAndView("list-victims");
        Iterable<Victim> allVictims = victimService.findAll();
        mav.addObject("victims", allVictims);
        return mav;
    }

    @GetMapping("/addVictimForm")
    public ModelAndView addVictimForm() {
        ModelAndView mav = new ModelAndView("add-victim-form");
        Iterable<Crime> listCrimes = crimeService.findAll();
        Iterable<Address> listAddresses = addressService.findAll();
        Victim newVictim = new Victim();
        mav.addObject("victim", newVictim);
        mav.addObject("listAddresses", listAddresses);
        mav.addObject("listCrimes", listCrimes);
        return mav;
    }

    @PostMapping("/saveVictim")
    public String saveVictim(@ModelAttribute Victim victim) {
        victimService.create(victim);
        return "redirect:/victims";
    }


}
