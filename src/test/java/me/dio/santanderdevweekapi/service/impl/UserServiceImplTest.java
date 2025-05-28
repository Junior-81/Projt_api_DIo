package me.dio.santanderdevweekapi.service.impl;

import me.dio.santanderdevweekapi.domain.model.Account;
import me.dio.santanderdevweekapi.domain.model.Card;
import me.dio.santanderdevweekapi.domain.model.User;
import me.dio.santanderdevweekapi.domain.repository.UserRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.test.context.ActiveProfiles;

import java.math.BigDecimal;
import java.util.Arrays;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.*;

/**
 * Testes unitários para o UserServiceImpl.
 */
@ExtendWith(MockitoExtension.class)
@ActiveProfiles("test")
class UserServiceImplTest {

    @Mock
    private UserRepository userRepository;

    @InjectMocks
    private UserServiceImpl userService;

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
    void findAll_ShouldReturnListOfUsers() {
        List<User> expectedUsers = Arrays.asList(mockUser);
        when(userRepository.findAll()).thenReturn(expectedUsers);

        List<User> result = userService.findAll();

        assertEquals(expectedUsers, result);
        verify(userRepository).findAll();
    }

    @Test
    void findById_ShouldReturnUser_WhenUserExists() {
        when(userRepository.findById(1L)).thenReturn(Optional.of(mockUser));

        User result = userService.findById(1L);

        assertEquals(mockUser, result);
        verify(userRepository).findById(1L);
    }

    @Test
    void findById_ShouldThrowException_WhenUserDoesNotExist() {
        when(userRepository.findById(999L)).thenReturn(Optional.empty());

        assertThrows(NoSuchElementException.class, () -> userService.findById(999L));
        verify(userRepository).findById(999L);
    }

    @Test
    void create_ShouldReturnCreatedUser_WhenValidUser() {
        when(userRepository.existsByAccountNumber(anyString())).thenReturn(false);
        when(userRepository.existsByCardNumber(anyString())).thenReturn(false);
        when(userRepository.save(any(User.class))).thenReturn(mockUser);

        User result = userService.create(mockUser);

        assertEquals(mockUser, result);
        verify(userRepository).existsByAccountNumber(mockUser.getAccount().getNumber());
        verify(userRepository).existsByCardNumber(mockUser.getCard().getNumber());
        verify(userRepository).save(mockUser);
    }

    @Test
    void create_ShouldThrowException_WhenAccountNumberExists() {
        when(userRepository.existsByAccountNumber(anyString())).thenReturn(true);

        IllegalArgumentException exception = assertThrows(
            IllegalArgumentException.class, 
            () -> userService.create(mockUser)
        );
        
        assertEquals("Este número de conta já existe.", exception.getMessage());
        verify(userRepository).existsByAccountNumber(mockUser.getAccount().getNumber());
        verify(userRepository, never()).save(any());
    }

    @Test
    void create_ShouldThrowException_WhenCardNumberExists() {
        when(userRepository.existsByAccountNumber(anyString())).thenReturn(false);
        when(userRepository.existsByCardNumber(anyString())).thenReturn(true);

        IllegalArgumentException exception = assertThrows(
            IllegalArgumentException.class, 
            () -> userService.create(mockUser)
        );
        
        assertEquals("Este número de cartão já existe.", exception.getMessage());
        verify(userRepository).existsByAccountNumber(mockUser.getAccount().getNumber());
        verify(userRepository).existsByCardNumber(mockUser.getCard().getNumber());
        verify(userRepository, never()).save(any());
    }

    @Test
    void update_ShouldReturnUpdatedUser_WhenValidUser() {
        User updatedUser = new User();
        updatedUser.setId(1L);
        updatedUser.setName("João Silva Atualizado");
        
        Account updatedAccount = new Account();
        updatedAccount.setNumber("87654321-0");
        updatedAccount.setAgency("0002");
        updatedAccount.setBalance(new BigDecimal("2000.00"));
        updatedAccount.setLimit(new BigDecimal("1000.00"));
        updatedUser.setAccount(updatedAccount);
        
        Card updatedCard = new Card();
        updatedCard.setNumber("**** **** **** 5678");
        updatedCard.setLimit(new BigDecimal("3000.00"));
        updatedUser.setCard(updatedCard);

        when(userRepository.existsById(1L)).thenReturn(true);
        when(userRepository.existsByAccountNumberAndIdNot(anyString(), eq(1L))).thenReturn(false);
        when(userRepository.existsByCardNumberAndIdNot(anyString(), eq(1L))).thenReturn(false);
        when(userRepository.save(any(User.class))).thenReturn(updatedUser);

        User result = userService.update(1L, updatedUser);

        assertEquals(updatedUser, result);
        assertEquals(1L, result.getId());
        verify(userRepository).existsById(1L);
        verify(userRepository).existsByAccountNumberAndIdNot(updatedUser.getAccount().getNumber(), 1L);
        verify(userRepository).existsByCardNumberAndIdNot(updatedUser.getCard().getNumber(), 1L);
        verify(userRepository).save(updatedUser);
    }

    @Test
    void update_ShouldThrowException_WhenUserDoesNotExist() {
        when(userRepository.existsById(999L)).thenReturn(false);

        NoSuchElementException exception = assertThrows(
            NoSuchElementException.class, 
            () -> userService.update(999L, mockUser)
        );
        
        assertEquals("Usuário não encontrado.", exception.getMessage());
        verify(userRepository).existsById(999L);
        verify(userRepository, never()).save(any());
    }

    @Test
    void delete_ShouldDeleteUser_WhenUserExists() {
        when(userRepository.existsById(1L)).thenReturn(true);

        userService.delete(1L);

        verify(userRepository).existsById(1L);
        verify(userRepository).deleteById(1L);
    }

    @Test
    void delete_ShouldThrowException_WhenUserDoesNotExist() {
        when(userRepository.existsById(999L)).thenReturn(false);

        NoSuchElementException exception = assertThrows(
            NoSuchElementException.class, 
            () -> userService.delete(999L)
        );
        
        assertEquals("Usuário não encontrado.", exception.getMessage());
        verify(userRepository).existsById(999L);
        verify(userRepository, never()).deleteById(any());
    }
}
