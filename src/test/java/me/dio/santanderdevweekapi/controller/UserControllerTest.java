package me.dio.santanderdevweekapi.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import me.dio.santanderdevweekapi.domain.model.Account;
import me.dio.santanderdevweekapi.domain.model.Card;
import me.dio.santanderdevweekapi.domain.model.User;
import me.dio.santanderdevweekapi.service.UserService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.web.servlet.MockMvc;

import java.math.BigDecimal;
import java.util.Arrays;
import java.util.NoSuchElementException;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

/**
 * Testes unitários para o UserController.
 */
@WebMvcTest(UserController.class)
@ActiveProfiles("test")
class UserControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private UserService userService;

    @Autowired
    private ObjectMapper objectMapper;

    private User mockUser;

    @BeforeEach
    void setUp() {
        mockUser = new User();
        mockUser.setId(1L);
        mockUser.setName("João Silva");
        
        Account account = new Account();
        account.setNumber("12345678-9");
        account.setAgency("0001");
        account.setBalance(new BigDecimal("1500.00"));
        account.setLimit(new BigDecimal("500.00"));
        mockUser.setAccount(account);
        
        Card card = new Card();
        card.setNumber("**** **** **** 1234");
        card.setLimit(new BigDecimal("2000.00"));
        mockUser.setCard(card);
    }

    @Test
    void findAll_ShouldReturnListOfUsers() throws Exception {
        when(userService.findAll()).thenReturn(Arrays.asList(mockUser));

        mockMvc.perform(get("/users"))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$[0].name").value("João Silva"));
    }    @Test
    void findById_ShouldReturnUser_WhenUserExists() throws Exception {
        when(userService.findById(1L)).thenReturn(mockUser);

        mockMvc.perform(get("/users/1"))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$.name").value("João Silva"));
    }

    @Test
    void findById_ShouldReturnNotFound_WhenUserDoesNotExist() throws Exception {
        when(userService.findById(999L)).thenThrow(new NoSuchElementException("Usuário não encontrado"));

        mockMvc.perform(get("/users/999"))
                .andExpect(status().isNotFound());
    }

    @Test
    void create_ShouldReturnCreatedUser_WhenValidUser() throws Exception {
        when(userService.create(any(User.class))).thenReturn(mockUser);

        mockMvc.perform(post("/users")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(mockUser)))
                .andExpect(status().isCreated())
                .andExpect(header().exists("Location"))
                .andExpect(jsonPath("$.name").value("João Silva"));
    }

    @Test
    void update_ShouldReturnUpdatedUser_WhenValidUser() throws Exception {
        when(userService.update(eq(1L), any(User.class))).thenReturn(mockUser);

        mockMvc.perform(put("/users/1")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(mockUser)))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.name").value("João Silva"));
    }

    @Test
    void delete_ShouldReturnNoContent_WhenUserExists() throws Exception {
        mockMvc.perform(delete("/users/1"))
                .andExpect(status().isNoContent());
    }
}
