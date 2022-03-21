package se.iths.crimedatabase.service;

import org.springframework.stereotype.Service;
import se.iths.crimedatabase.entity.Category;
import se.iths.crimedatabase.repository.CategoryRepository;

import javax.persistence.EntityNotFoundException;
import java.util.Optional;

@Service
public class CategoryService {
    private final CategoryRepository categoryRepository;

    public CategoryService(CategoryRepository categoryService) {
        this.categoryRepository = categoryService;
    }

    public Category create(Category category) {
        return categoryRepository.save(category);
    }

    public void delete(Long id) {
        Category foundCategory = categoryRepository.findById(id).orElseThrow(EntityNotFoundException::new);
        categoryRepository.deleteById(foundCategory.getId());
    }

    public Optional<Category> findById(Long id) {
        return categoryRepository.findById(id);
    }

    public Iterable<Category> findAll() {
        return categoryRepository.findAll();
    }

    public void update(Category category, Long id) {
        category.setId(id);
        categoryRepository.save(category);
    }
}
