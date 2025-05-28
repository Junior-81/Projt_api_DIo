package me.dio.santanderdevweekapi.service.impl;

import me.dio.santanderdevweekapi.domain.model.User;
import me.dio.santanderdevweekapi.domain.repository.UserRepository;
import me.dio.santanderdevweekapi.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.NoSuchElementException;

/**
 * Implementação do serviço de usuários.
 */
@Service
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;

    @Autowired
    public UserServiceImpl(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Transactional(readOnly = true)
    @Override
    public List<User> findAll() {
        return this.userRepository.findAll();
    }

    @Transactional(readOnly = true)
    @Override
    public User findById(Long id) {
        return this.userRepository.findById(id)
                .orElseThrow(() -> new NoSuchElementException("Usuário não encontrado com ID: " + id));
    }

    @Transactional
    @Override
    public User create(User userToCreate) {
        if (userToCreate.getId() != null) {
            throw new IllegalArgumentException("Usuário a ser criado não deve ter um ID.");
        }
        this.validateChangeableId(userToCreate, "criado");
        return this.userRepository.save(userToCreate);
    }

    @Transactional
    @Override
    public User update(Long id, User userToUpdate) {
        User dbUser = this.findById(id);
        if (!dbUser.getId().equals(userToUpdate.getId())) {
            throw new IllegalArgumentException("IDs de atualização devem ser iguais.");
        }

        dbUser.setName(userToUpdate.getName());
        dbUser.setAccount(userToUpdate.getAccount());
        dbUser.setCard(userToUpdate.getCard());
        dbUser.setFeatures(userToUpdate.getFeatures());
        dbUser.setNews(userToUpdate.getNews());

        return this.userRepository.save(dbUser);
    }

    @Transactional
    @Override
    public void delete(Long id) {
        User dbUser = this.findById(id);
        this.userRepository.delete(dbUser);
    }

    private void validateChangeableId(User user, String operation) {
        if (user.getAccount() != null && user.getAccount().getNumber() != null) {
            if (userRepository.existsByAccountNumber(user.getAccount().getNumber())) {
                throw new IllegalArgumentException("Este número de conta já existe e não pode ser " + operation + ".");
            }
        }
        if (user.getCard() != null && user.getCard().getNumber() != null) {
            if (userRepository.existsByCardNumber(user.getCard().getNumber())) {
                throw new IllegalArgumentException("Este número de cartão já existe e não pode ser " + operation + ".");
            }
        }
    }
}
