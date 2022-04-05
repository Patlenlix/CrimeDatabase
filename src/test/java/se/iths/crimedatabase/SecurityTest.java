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
import se.iths.crimedatabase.repository.UserRepository;
import se.iths.crimedatabase.security.SecurityConfigAPI;
import se.iths.crimedatabase.service.*;

import java.util.List;

import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@Import({SecurityConfigAPI.class})
@WebMvcTest({CriminalController.class, CategoryController.class, AddressController.class,
        CrimeController.class, VictimController.class, UserController.class})
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
    @MockBean
    private UserRepository userRepository;

    @Nested
    class Users {
        @Test
        void whenUnauthorizedAndRequestOnSecuredEndpointThenFailWith401() throws Exception {
            mockMvc.perform(get("/api/users"))
                    .andExpect(status().isUnauthorized());
        }

        @WithMockUser()
        @Test
        void whenUserAndRequestOnSecuredEndpointOnlyForAdminThenFailWith403() throws Exception {
            mockMvc.perform(get("/api/users"))
                    .andExpect(status().isForbidden());
        }

        @WithMockUser(roles = {"ADMIN"})
        @Test
        void whenAdminAndRequestOnSecuredEndpointOnlyForAdminsThenSuccessWith201() throws Exception {
            List<User> users = List.of(new User());

            when(userRepository.findAll()).thenReturn(users);

            mockMvc.perform(get("/api/users")).andExpect(status().isOk());
        }

    }

    @Nested
    class Victims {
        @Test
        void whenUnauthorizedAndRequestOnSecuredEndpointThenFailWith401() throws Exception {
            mockMvc.perform(get("/api/victims"))
                    .andExpect(status().isUnauthorized());
        }

        @WithMockUser()
        @Test
        void whenUserAndRequestOnSecuredEndpointOnlyForAdminThenFailWith403() throws Exception {
            mockMvc.perform(get("/api/victims"))
                    .andExpect(status().isForbidden());
        }

        @WithMockUser(roles = {"ADMIN"})
        @Test
        void whenAdminAndRequestOnSecuredEndpointOnlyForAdminsThenSuccessWith201() throws Exception {
            Iterable<Victim> victims = List.of(new Victim());

            when(victimService.findAll()).thenReturn(victims);

            mockMvc.perform(get("/api/victims")).andExpect(status().isOk());
        }

    }

    @Nested
    class Crimes {
        @Test
        void whenUnauthorizedAndRequestOnSecuredEndpointThenFailWith401() throws Exception {
            mockMvc.perform(get("/api/crimes"))
                    .andExpect(status().isUnauthorized());
        }

        @WithMockUser()
        @Test
        void whenUserAndRequestOnSecuredEndpointOnlyForAdminThenFailWith403() throws Exception {
            mockMvc.perform(get("/api/crimes"))
                    .andExpect(status().isForbidden());
        }

        @WithMockUser(roles = {"ADMIN"})
        @Test
        void whenAuthorizedAndRequestOnSecuredEndpointThenSuccessWith201() throws Exception {
            Iterable<Crime> crimes = List.of(new Crime());

            when(crimeService.findAll()).thenReturn(crimes);

            mockMvc.perform(get("/api/crimes")).andExpect(status().isOk());
        }

    }

    @Nested
    class Addresses {
        @Test
        void whenUnauthorizedAndRequestOnSecuredEndpointThenFailWith401() throws Exception {
            mockMvc.perform(get("/api/addresses"))
                    .andExpect(status().isUnauthorized());
        }

        @WithMockUser()
        @Test
        void whenUserAndRequestOnSecuredEndpointOnlyForAdminThenFailWith403() throws Exception {
            mockMvc.perform(get("/api/addresses"))
                    .andExpect(status().isForbidden());
        }

        @WithMockUser(roles = {"ADMIN"})
        @Test
        void whenAuthorizedAndRequestOnSecuredEndpointThenSuccessWith201() throws Exception {
            Iterable<Address> addresses = List.of(new Address());

            when(addressService.findAll()).thenReturn(addresses);

            mockMvc.perform(get("/api/addresses")).andExpect(status().isOk());
        }

    }

    @Nested
    class Criminals {
        @Test
        void whenUnauthorizedAndRequestOnSecuredEndpointThenFailWith401() throws Exception {
            mockMvc.perform(get("/api/criminals"))
                    .andExpect(status().isUnauthorized());
        }

        @WithMockUser()
        @Test
        void whenUserAndRequestOnSecuredEndpointOnlyForAdminThenFailWith403() throws Exception {
            mockMvc.perform(get("/api/criminals"))
                    .andExpect(status().isForbidden());
        }

        @WithMockUser(roles = {"ADMIN"})
        @Test
        void whenAdminAndRequestOnSecuredEndpointOnlyForAdminsThenSuccessWith201() throws Exception {
            Iterable<Criminal> criminals = List.of(new Criminal());

            when(criminalService.findAll()).thenReturn(criminals);

            mockMvc.perform(get("/api/criminals")).andExpect(status().isOk());
        }

    }

    @Nested
    class Categories {
        @Test
        void whenUnauthorizedAndRequestOnSecuredEndpointThenFailWith401() throws Exception {
            mockMvc.perform(get("/api/categories"))
                    .andExpect(status().isUnauthorized());
        }

        @WithMockUser()
        @Test
        void whenUserAndRequestOnSecuredEndpointOnlyForAdminThenFailWith403() throws Exception {
            mockMvc.perform(get("/api/categories"))
                    .andExpect(status().isForbidden());
        }

        @WithMockUser(roles = {"ADMIN"})
        @Test
        void whenAuthorizedAndRequestOnSecuredEndpointThenSuccessWith201() throws Exception {
            Iterable<Category> categories = List.of(new Category());

            when(categoryService.findAll()).thenReturn(categories);

            mockMvc.perform(get("/api/categories")).andExpect(status().isOk());
        }

    }

}

