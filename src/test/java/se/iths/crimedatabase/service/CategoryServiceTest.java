package se.iths.crimedatabase.service;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import se.iths.crimedatabase.entity.Category;
import se.iths.crimedatabase.repository.CategoryRepository;

import javax.persistence.EntityNotFoundException;
import java.util.List;
import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class CategoryServiceTest {

    @Mock
    private CategoryRepository repository;
    @InjectMocks
    private CategoryService service;

    @Test
    void callingCreateShouldReturnTheCreatedCategory() {
        Category category = new Category().setName("Test").setId(1L);
        when(repository.save(category)).thenReturn(category);

        Category createdCategory = service.create(category);

        verify(repository, times(1)).save(category);
        assertThat(createdCategory).isEqualTo(category);
    }

    @Test
    void callingFindAllShouldReturnAllCategories() {
        List<Category> categories = List.of(
                new Category().setName("Theft").setId(1L),
                new Category().setName("Burglary").setId(2L),
                new Category().setName("Shoplifting").setId(3L));

        when(repository.findAll()).thenReturn(categories);

        Iterable<Category> foundCategories = service.findAll();

        verify(repository, times(1)).findAll();
        assertThat(foundCategories).isEqualTo(categories);
    }

    @Test
    void callingFindByIdShouldReturnAnOptionalWithCorrespondingCategoryIfPresent() {
        Long validId = 1L;
        Long invalidId = 2L;
        Category category = new Category();
        category.setName("Test").setId(validId);
        when(repository.findById(validId)).thenReturn(Optional.of(category));
        when(repository.findById(invalidId)).thenReturn(Optional.empty());

        Optional<Category> expectedCategory = service.findById(validId);
        Optional<Category> expectedEmpty = service.findById(invalidId);

        assertThat(expectedCategory).isEqualTo(Optional.of(category));
        assertThat(expectedEmpty).isNotPresent();
    }


    @Test
    void callingDeleteWithAValidIShouldDeleteCategoryWithSaidIdElseExceptionShouldBeThrown() {
        Long validId = 1L;
        Long invalidId = 2L;
        Category category = new Category().setName("Test").setId(validId);
        when(repository.findById(invalidId)).thenReturn(Optional.empty());
        when(repository.findById(validId)).thenReturn(Optional.of(category));

        service.delete(validId);

        verify(repository, times(1)).deleteById(validId);
        verify(repository, times(0)).deleteById(invalidId);
        assertThrows(EntityNotFoundException.class, () -> service.delete(invalidId));
    }

    @Test
    void callingUpdateWithACategoryAndAnIdSaidCategoryShouldBeUpdated() {
        Long Id = 1L;
        Category category = new Category().setName("Test").setId(Id);

        service.update(category, Id);

        verify(repository, times(1)).save(category);
    }
}