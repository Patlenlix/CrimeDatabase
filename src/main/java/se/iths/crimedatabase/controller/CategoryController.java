package se.iths.crimedatabase.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import se.iths.crimedatabase.entity.Category;
import se.iths.crimedatabase.exception.BadRequestException;
import se.iths.crimedatabase.exception.MethodNotAllowedException;
import se.iths.crimedatabase.exception.NotFoundException;
import se.iths.crimedatabase.service.CategoryService;

import java.util.Optional;

@RestController
@RequestMapping("api/categories")
public class CategoryController {
    private final CategoryService categoryService;

    public CategoryController(CategoryService categoryService) {
        this.categoryService = categoryService;
    }

    @PostMapping
    public ResponseEntity<Category> create(@RequestBody Category category) {
        if (category.getName().isEmpty()) //TODO: Failar om man inte skickar med name, blir null pointer exception.
            throw new BadRequestException("Category name cannot be empty");

        Category createdCategory = categoryService.create(category);
        return new ResponseEntity<>(createdCategory, HttpStatus.CREATED);
    }

    @DeleteMapping("{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        if (categoryService.findById(id).isEmpty())
            throw new NotFoundException(notFoundMessage(id));

        categoryService.delete(id);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @GetMapping("{id}")
    public ResponseEntity<Optional<Category>> findById(@PathVariable Long id) {
        if (categoryService.findById(id).isEmpty())
            throw new NotFoundException(notFoundMessage(id));

        Optional<Category> foundCategory = categoryService.findById(id);
        return new ResponseEntity<>(foundCategory, HttpStatus.OK);
    }

    @GetMapping
    public ResponseEntity<Iterable<Category>> findAll() {
        Iterable<Category> categories = categoryService.findAll();
        if (!categories.iterator().hasNext())
            throw new NotFoundException("No categories found");

        return new ResponseEntity<>(categories, HttpStatus.OK);
    }

    @PutMapping("{id}")
    public ResponseEntity<Category> update(@PathVariable Long id, @RequestBody Category category) {
        if (categoryService.findById(id).isEmpty())
            throw new MethodNotAllowedException("Category with id: " + id + " cannot be updated since it does not exist");


        categoryService.update(category, id);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    private String notFoundMessage(Long id) {
        return "Category with id: " + id + " cannot be found";
    }

}
