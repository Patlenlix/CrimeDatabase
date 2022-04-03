package se.iths.crimedatabase;

import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.context.annotation.Import;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.web.servlet.MockMvc;
import se.iths.crimedatabase.controller.*;
import se.iths.crimedatabase.entity.*;
import se.iths.crimedatabase.security.SecurityConfig;
import se.iths.crimedatabase.service.*;

import java.util.List;

import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@Import({SecurityConfig.class})
@WebMvcTest({CriminalController.class, CategoryController.class,
        AddressController.class, CrimeController.class,
        VictimController.class})
public class SecurityTest {
    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private CriminalService criminalService;

    @MockBean
    private CategoryService categoryService;

    @MockBean
    private AddressService addressService;

    @MockBean
    private CrimeService crimeService;

    @MockBean
    private VictimService victimService;

    @Nested
    class Victims {

        @Test
        void whenUnauthorizedAndRequestOnSecuredEndpointThenFailWith401() throws Exception {
            mockMvc.perform(get("/victims"))
                    .andExpect(status().isUnauthorized());
        }

        @WithMockUser()
        @Test
        void whenUserAndRequestOnSecuredEndpointOnlyForAdminThenFailWith403() throws Exception {
            mockMvc.perform(get("/victims"))
                    .andExpect(status().isForbidden());
        }

        @WithMockUser(roles = {"ADMIN"})
        @Test
        void whenAdminAndRequestOnSecuredEndpointOnlyForAdminsThenSuccessWith201() throws Exception {
            Iterable<Victim> victims = List.of(
                    new Victim().setFirstName("Jane").setLastName("Doe"));

            when(victimService.findAll()).thenReturn(victims);

            mockMvc.perform(get("/victims")).andExpect(status().isOk());
        }
    }

    @Nested
    class Crimes {
        @Test
        void whenUnauthorizedAndRequestOnSecuredEndpointThenFailWith401() throws Exception {
            mockMvc.perform(get("/crimes"))
                    .andExpect(status().isUnauthorized());
        }

        @WithMockUser()
        @Test
        void whenAuthorizedAndRequestOnSecuredEndpointThenSuccessWith201() throws Exception {
            Iterable<Crime> crimes = List.of(new Crime().setName("Example"));

            when(crimeService.findAll()).thenReturn(crimes);

            mockMvc.perform(get("/crimes")).andExpect(status().isOk());
        }
    }

    @Nested
    class Addresses {
        @Test
        void whenUnauthorizedAndRequestOnSecuredEndpointThenFailWith401() throws Exception {
            mockMvc.perform(get("/addresses"))
                    .andExpect(status().isUnauthorized());
        }

        @WithMockUser()
        @Test
        void whenAuthorizedAndRequestOnSecuredEndpointThenSuccessWith201() throws Exception {
            Iterable<Address> addresses = List.of(
                    new Address().setCity("Gothenburg").setStreetAddress("Example 5").setZipCode("11111"));

            when(addressService.findAll()).thenReturn(addresses);

            mockMvc.perform(get("/addresses")).andExpect(status().isOk());
        }
    }

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
                    new Criminal().setFirstName("Jane").setLastName("Doe"),
                    new Criminal().setFirstName("John").setLastName("Doe"));

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
