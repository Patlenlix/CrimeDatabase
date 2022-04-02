package se.iths.crimedatabase.view;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;
import se.iths.crimedatabase.entity.Address;
import se.iths.crimedatabase.service.AddressService;


@Controller
public class AddressThymeleafController {

    private final AddressService service;

    @Autowired
    public AddressThymeleafController(AddressService service) {
        this.service = service;
    }

    @GetMapping("/showAddresses")
    public ModelAndView showAddresses() {
        ModelAndView mav = new ModelAndView("list-addresses");
        Iterable<Address> allAddresses = service.findAll();
        mav.addObject("addresses", allAddresses);
        return mav;
    }

    @GetMapping("/addAddressesForm")
    public ModelAndView addAddressesForm() {
        ModelAndView mav = new ModelAndView("add-addresses-form");
        Address address = new Address();
        mav.addObject("address", address);
        return mav;
    }

    @PostMapping("/saveAddress")
    public String saveAddresses(@ModelAttribute Address address) {
        service.create(address);
        return "redirect:/showAddresses";
    }

    @GetMapping("/showAddressesUpdateForm")
    public ModelAndView showAddressesUpdateForm(@RequestParam Long id) {
        ModelAndView mav = new ModelAndView("add-addresses-form");
        Address address = service.findById(id).orElseThrow();
        mav.addObject("address", address);
        return mav;
    }


}
