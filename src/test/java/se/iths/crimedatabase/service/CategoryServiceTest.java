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

    // CREATE
    // When we call the create-method with a category,
    // said category should be passes to the save method of the repository
    @Test
    void callingCreateShouldReturnTheCreatedCategory() {
        Category category = new Category();
        category.setName("Test").setId(1L);
        when(repository.save(category)).thenReturn(category);

        Category createdCategory = service.create(category);

        assertThat(createdCategory).isEqualTo(category);
    }

    // FIND ALL
    // When we call the findAll-method a List of all categories should be returned.
    @Test
    void callingFindAllShouldReturnAllCategories() {
        List<Category> categories = List.of(
                new Category().setName("Theft").setId(1L),
                new Category().setName("Burglary").setId(2L),
                new Category().setName("shoplifting").setId(3L));

        when(repository.findAll()).thenReturn(categories);

        Iterable<Category> foundCategories = service.findAll();

        assertThat(foundCategories).isEqualTo(categories);
    }

    // FIND BY ID
    // When we call the findById-method with an id an optional<category> should be returned. It contains a category with that id if it exists
    // else it's empty.
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


    // DELETE
    // When we call the delete-method with an id, the category with that id should be deleted if it exists,
    // else an EntityNotFoundException should be thrown.
    @Test
    void callingDeleteWithAValidIdACategoryShouldBeDeletedElseExceptionShouldBeThrown() {
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

    // UPDATE
    // When we call the update-method with a category and id, the category with that id should be updated if it exists.
    @Test
    void callingUpdateWithACategoryAndAnIdSaidCategoryShouldBeUpdated() {
        Long Id = 1L;
        Category category = new Category().setName("Test").setId(Id);

        service.update(category, Id);

        verify(repository, times(1)).save(category);
    }
}