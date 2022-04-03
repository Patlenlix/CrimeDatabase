package se.iths.crimedatabase;

import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.context.annotation.Import;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.web.servlet.MockMvc;
import se.iths.crimedatabase.controller.CategoryController;
import se.iths.crimedatabase.controller.CriminalController;
import se.iths.crimedatabase.entity.Category;
import se.iths.crimedatabase.entity.Criminal;
import se.iths.crimedatabase.security.SecurityConfig;
import se.iths.crimedatabase.service.CategoryService;
import se.iths.crimedatabase.service.CriminalService;

import java.util.List;

import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@Import({SecurityConfig.class})
@WebMvcTest({CriminalController.class, CategoryController.class})
public class SecurityTest {
    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private CriminalService criminalService;

    @MockBean
    private CategoryService categoryService;

    @Nested
    class Criminals {

        @Test
        void whenUnauthorizedAndRequestOnSecuredEndpointThenFailWith401() throws Exception {
            mockMvc.perform(get("/criminals"))
                    .andExpect(status().isUnauthorized());
        }

        @WithMockUser()
        @Test
        void whenUserAndRequestOnSecuredEndpointOnlyForAdminThenFailWith403() throws Exception {
            mockMvc.perform(get("/criminals"))
                    .andExpect(status().isForbidden());
        }

        @WithMockUser(roles = {"ADMIN"})
        @Test
        void whenAdminAndRequestOnSecuredEndpointOnlyForAdminsThenSuccessWith201() throws Exception {
            Iterable<Criminal> criminals = List.of(
                    new Criminal().setLastName("Jane").setLastName("Doe"),
                    new Criminal().setLastName("John").setLastName("Doe"));

            when(criminalService.findAll()).thenReturn(criminals);

            mockMvc.perform(get("/criminals")).andExpect(status().isOk());
        }
    }

    @Nested
    class Categories {

        @Test
        void whenUnauthorizedAndRequestOnSecuredEndpointThenFailWith401() throws Exception {
            mockMvc.perform(get("/categories"))
                    .andExpect(status().isUnauthorized());
        }

        @WithMockUser()
        @Test
        void whenAuthorizedAndRequestOnSecuredEndpointThenSuccessWith201() throws Exception {
            Iterable<Category> categories = List.of(new Category().setName("test"));

            when(categoryService.findAll()).thenReturn(categories);

            mockMvc.perform(get("/categories")).andExpect(status().isOk());
        }
    }
}
