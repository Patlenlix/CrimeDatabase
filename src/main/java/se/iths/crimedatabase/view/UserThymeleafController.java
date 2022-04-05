package se.iths.crimedatabase.view;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.servlet.ModelAndView;
import se.iths.crimedatabase.entity.User;
import se.iths.crimedatabase.service.UserService;

@Controller
public class UserThymeleafController {

    private final UserService service;

    @Autowired
    public UserThymeleafController(UserService service) {
        this.service = service;
    }

    @GetMapping("/users")
    public ModelAndView showUsers() {
        ModelAndView mav = new ModelAndView("list-users");
        Iterable<User> allUsers = service.findAll();
        mav.addObject("users", allUsers);
        return mav;
    }
    
}
