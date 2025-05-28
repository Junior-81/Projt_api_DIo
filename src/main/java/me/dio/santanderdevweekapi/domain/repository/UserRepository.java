package me.dio.santanderdevweekapi.domain.repository;

import me.dio.santanderdevweekapi.domain.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * Repositório para operações CRUD da entidade User.
 */
@Repository
public interface UserRepository extends JpaRepository<User, Long> {
    
    /**
     * Verifica se existe um usuário com o número de conta especificado.
     *
     * @param accountNumber o número da conta
     * @return true se existir, false caso contrário
     */
    boolean existsByAccountNumber(String accountNumber);
    
    /**
     * Verifica se existe um usuário com o número de cartão especificado.
     *
     * @param cardNumber o número do cartão
     * @return true se existir, false caso contrário
     */
    boolean existsByCardNumber(String cardNumber);
}
