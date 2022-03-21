package se.iths.crimedatabase;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Test;
import org.mockito.ArgumentCaptor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import se.iths.crimedatabase.controller.CategoryController;
import se.iths.crimedatabase.entity.Category;
import se.iths.crimedatabase.service.CategoryService;

import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(CategoryController.class)
class CategoryWebLayerTest {

    @Autowired
    MockMvc mockMvc; //simulates HTTP requests
    /* We don't want to test we don’t want to test integration between controller and business logic
    but between controller and the HTTP layer */
    @MockBean
    CategoryService service;
    @Autowired
    private ObjectMapper objectMapper; //maps to and from JSON

    // 1.  Verifying HTTP Request Matching
    @Test
    void verifyingHttpRequestMatching() throws Exception {
        mockMvc.perform(get("/categories"))
                .andExpect(status().isOk());
    }

    // 2. Verifying Input Deserialization
    @Test
    void verifyingInputDeserialization() throws Exception {
        Category category = new Category();
        category.setName("Test");

        mockMvc.perform(post("/categories/")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(category)))
                .andExpect(status().isCreated());
    }

    // 3. Verifying Input Validation
    @Test
    void verifyingInputValidation() throws Exception {
        //TODO: Add when exceptions have been implemented

    }

    // 4. Verifying Business Logic Calls
    @Test
    void verifyingBusinessLogicCalls() throws Exception {
        Category category = new Category();
        category.setName("Test").setId(1L);

        mockMvc.perform(post("/categories/")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(category)));

        ArgumentCaptor<Category> categoryCaptor = ArgumentCaptor.forClass(Category.class);
        verify(service, times(1)).create(categoryCaptor.capture());
        assertThat(categoryCaptor.getValue().getName()).isEqualTo("Test");
        assertThat(categoryCaptor.getValue().getId()).isEqualTo(1L);
    }

    // 5. Verifying Output Serialization
    @Test
    void verifyingOutputSerialization() throws Exception {
        Category category = new Category();
        category.setName("Test").setId(1L);

        when(service.findById(1L)).thenReturn(Optional.of(category));

        mockMvc.perform(get("/categories/{id}", 1L))
                .andExpect(jsonPath("$.id").value(1L))
                .andExpect(jsonPath("$.name").value("Test"));
    }


    // 6. Verifying Exception Handling
    @Test
    void verifyingExceptionHandling() {
        //TODO: Add when exceptions have been implemented
    }

}
