package se.iths.crimedatabase.view;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.servlet.ModelAndView;
import se.iths.crimedatabase.entity.Category;
import se.iths.crimedatabase.service.CategoryService;


@Controller
public class CategoriesThymeleafController {

    @Autowired
    private CategoryService service;

    @GetMapping("/showCategories")
    public ModelAndView showCategories() {
        ModelAndView mav = new ModelAndView("list-categories");
        Iterable<Category> allCategories = service.findAll();
        mav.addObject("category", allCategories);
        return mav;
    }

}
