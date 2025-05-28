package me.dio.santanderdevweekapi.integration;

import com.fasterxml.jackson.databind.ObjectMapper;
import me.dio.santanderdevweekapi.domain.model.Account;
import me.dio.santanderdevweekapi.domain.model.Card;
import me.dio.santanderdevweekapi.domain.model.User;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureWebMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

/**
 * Testes de integração para a API REST de usuários.
 */
@SpringBootTest
@AutoConfigureWebMvc
@ActiveProfiles("test")
@Transactional
class UserIntegrationTest {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private ObjectMapper objectMapper;

    @Test
    void fullUserCrud_ShouldWorkCorrectly() throws Exception {
        // Criar um novo usuário
        User newUser = createTestUser();
          String userJson = mockMvc.perform(post("/users")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(newUser)))
                .andExpect(status().isCreated())
                .andExpect(jsonPath("$.name").value("Teste Integration"))
                .andReturn()
                .getResponse()
                .getContentAsString();
        
        User createdUser = objectMapper.readValue(userJson, User.class);
        Long userId = createdUser.getId();
          // Buscar o usuário criado
        mockMvc.perform(get("/users/" + userId))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.name").value("Teste Integration"));
        
        // Listar todos os usuários (deve conter o usuário criado)
        mockMvc.perform(get("/users"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$").isArray());
        
        // Atualizar o usuário
        createdUser.setName("Teste Integration Atualizado");
        mockMvc.perform(put("/users/" + userId)
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(createdUser)))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.name").value("Teste Integration Atualizado"));
        
        // Deletar o usuário
        mockMvc.perform(delete("/users/" + userId))
                .andExpect(status().isNoContent());
        
        // Verificar que o usuário foi deletado
        mockMvc.perform(get("/users/" + userId))
                .andExpect(status().isNotFound());
    }

    @Test
    void createUser_ShouldFailWithDuplicateAccountNumber() throws Exception {
        User user1 = createTestUser();
          // Criar primeiro usuário
        mockMvc.perform(post("/users")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(user1)))
                .andExpect(status().isCreated());
        
        // Tentar criar segundo usuário com mesmo número de conta
        User user2 = createTestUser();
        user2.setName("Outro Usuário");
        user2.getCard().setNumber("**** **** **** 9999");
        
        mockMvc.perform(post("/users")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(user2)))
                .andExpect(status().isUnprocessableEntity());
    }

    private User createTestUser() {
        User user = new User();
        user.setName("Teste Integration");
        
        Account account = new Account();
        account.setNumber("99999999-9");
        account.setAgency("9999");
        account.setBalance(new BigDecimal("1000.00"));
        account.setLimit(new BigDecimal("500.00"));
        user.setAccount(account);
        
        Card card = new Card();
        card.setNumber("**** **** **** 9876");
        card.setLimit(new BigDecimal("1500.00"));
        user.setCard(card);
        
        return user;
    }
}
