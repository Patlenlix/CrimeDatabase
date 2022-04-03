package se.iths.crimedatabase;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Test;
import org.mockito.ArgumentCaptor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.context.annotation.Import;
import org.springframework.http.MediaType;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.web.servlet.MockMvc;
import se.iths.crimedatabase.controller.CategoryController;
import se.iths.crimedatabase.entity.Category;
import se.iths.crimedatabase.security.SecurityConfig;
import se.iths.crimedatabase.service.CategoryService;

import java.util.List;
import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;


@Import({SecurityConfig.class})
@WebMvcTest(CategoryController.class)
class CategoryWebLayerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private CategoryService service;

    @Autowired
    private ObjectMapper objectMapper;

    //1.  Verifying HTTP Request Matching
    @Test
    @WithMockUser
    void verifyingHttpRequestMatchingForGetAll() throws Exception {
        when(service.findAll()).thenReturn(List.of(new Category(1L, "Test")));

        mockMvc.perform(get("/categories"))
                .andExpect(status().isOk());
    }

    @Test
    @WithMockUser
    void verifyingHttpRequestMatchingForGetById() throws Exception {
        Long id = 1L;
        Category category = new Category(id, "Test");
        when(service.findById(id)).thenReturn(Optional.of(category));

        mockMvc.perform(get("/categories/{id}", 1L))
                .andExpect(status().isOk());
    }

    @Test
    @WithMockUser
    void verifyingHttpRequestMatchingForDelete() throws Exception {
        Long id = 1L;
        when(service.findById(id)).thenReturn(Optional.of(new Category(id, "Test")));
        doNothing().when(service).delete(id);

        mockMvc.perform(delete("/categories/{id}", id))
                .andExpect(status().isOk());
    }

    // 2. Verifying Input Deserialization
    @Test
    @WithMockUser
    void verifyingInputDeserialization() throws Exception {
        Category category = new Category(1L, "Test");
        when(service.create(category)).thenReturn(category);

        mockMvc.perform(post("/categories")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(category)))
                .andExpect(status().isCreated());

    }

    // 3. Verifying Input Validation - we do not have any input validation


    // 4. Verifying Business Logic Calls
    @Test
    @WithMockUser
    void verifyingBusinessLogicCalls() throws Exception {
        Category category = new Category(1L, "Test");
        when(service.create(category)).thenReturn(category);

        mockMvc.perform(post("/categories")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(category)));

        ArgumentCaptor<Category> categoryCaptor = ArgumentCaptor.forClass(Category.class);
        verify(service, times(1)).create(categoryCaptor.capture());
        assertThat(categoryCaptor.getValue().getName()).isEqualTo("Test");
        assertThat(categoryCaptor.getValue().getId()).isEqualTo(1L);
    }

    // 5. Verifying Output Serialization
    @Test
    @WithMockUser
    void verifyingOutputSerialization() throws Exception {
        Category category = new Category(1L, "Test");

        when(service.findById(1L)).thenReturn(Optional.of(category));

        mockMvc.perform(get("/categories/{id}", 1L))
                .andExpect(jsonPath("$.id").value(1L))
                .andExpect(jsonPath("$.name").value("Test"));
    }

    // 6. Verifying Exception Handling
    @Test
    @WithMockUser
    void verifyingInputValidation() throws Exception {
        Category category = new Category(1L, "");
        when(service.create(category)).thenReturn(category);

        mockMvc.perform(post("/categories")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(category)))
                .andExpect(status().isBadRequest());
    }
}
