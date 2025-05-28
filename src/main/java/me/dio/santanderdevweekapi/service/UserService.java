package me.dio.santanderdevweekapi.service;

import me.dio.santanderdevweekapi.domain.model.User;

import java.util.List;

/**
 * Interface que define os serviços para operações com usuários.
 */
public interface UserService {
    
    /**
     * Busca todos os usuários.
     *
     * @return lista de usuários
     */
    List<User> findAll();
    
    /**
     * Busca um usuário por ID.
     *
     * @param id o ID do usuário
     * @return o usuário encontrado
     * @throws IllegalArgumentException se o usuário não for encontrado
     */
    User findById(Long id);
    
    /**
     * Cria um novo usuário.
     *
     * @param userToCreate o usuário a ser criado
     * @return o usuário criado
     * @throws IllegalArgumentException se o usuário já existir
     */
    User create(User userToCreate);
    
    /**
     * Atualiza um usuário existente.
     *
     * @param id o ID do usuário a ser atualizado
     * @param userToUpdate os dados atualizados do usuário
     * @return o usuário atualizado
     * @throws IllegalArgumentException se o usuário não for encontrado
     */
    User update(Long id, User userToUpdate);
    
    /**
     * Deleta um usuário por ID.
     *
     * @param id o ID do usuário a ser deletado
     * @throws IllegalArgumentException se o usuário não for encontrado
     */
    void delete(Long id);
}
