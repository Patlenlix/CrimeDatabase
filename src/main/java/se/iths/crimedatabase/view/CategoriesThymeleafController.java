package se.iths.crimedatabase.view;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;
import se.iths.crimedatabase.entity.Category;
import se.iths.crimedatabase.service.CategoryService;


@Controller
public class CategoriesThymeleafController {

    private final CategoryService service;

    @Autowired
    public CategoriesThymeleafController(CategoryService service) {
        this.service = service;
    }

    @GetMapping("/categories")
    public ModelAndView showCategories() {
        ModelAndView mav = new ModelAndView("list-categories");
        Iterable<Category> allCategories = service.findAll();
        mav.addObject("category", allCategories);
        return mav;
    }

    @GetMapping("/addCategoryForm")
    public ModelAndView addCategoryForm() {
        ModelAndView mav = new ModelAndView("add-category-form");
        Category newCategory = new Category();
        mav.addObject("category", newCategory);
        return mav;
    }

    @PostMapping("/saveCategory")
    public String saveEmployee(@ModelAttribute Category category) {
        service.create(category);
        return "redirect:/categories";
    }

    @GetMapping("/categoriesUpdateForm")
    public ModelAndView showUpdateForm(@RequestParam Long id) {
        ModelAndView mav = new ModelAndView("add-category-form");
        Category existingCategory = service.findById(id).orElseThrow();
        mav.addObject("category", existingCategory);
        return mav;
    }

    @GetMapping("/deleteCategory")
    public String deleteCategory(@RequestParam Long id) {
        service.delete(id);
        return "redirect:/categories";
    }


}
