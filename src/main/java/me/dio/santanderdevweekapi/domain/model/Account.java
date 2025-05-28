package me.dio.santanderdevweekapi.domain.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

import java.math.BigDecimal;

/**
 * Entidade que representa uma conta bancária.
 */
@Entity(name = "tb_account")
public class Account extends BaseEntity {
    
    @NotBlank(message = "O número da conta é obrigatório")
    @Column(unique = true)
    private String number;
    
    @NotBlank(message = "A agência é obrigatória")
    private String agency;
    
    @NotNull(message = "O saldo é obrigatório")
    @Column(precision = 13, scale = 2)
    private BigDecimal balance;
    
    @NotNull(message = "O limite é obrigatório")
    @Column(name = "additional_limit", precision = 13, scale = 2)
    private BigDecimal limit;
    
    public Account() {}
    
    public Account(String number, String agency, BigDecimal balance, BigDecimal limit) {
        this.number = number;
        this.agency = agency;
        this.balance = balance;
        this.limit = limit;
    }
    
    public String getNumber() {
        return number;
    }
    
    public void setNumber(String number) {
        this.number = number;
    }
    
    public String getAgency() {
        return agency;
    }
    
    public void setAgency(String agency) {
        this.agency = agency;
    }
    
    public BigDecimal getBalance() {
        return balance;
    }
    
    public void setBalance(BigDecimal balance) {
        this.balance = balance;
    }
    
    public BigDecimal getLimit() {
        return limit;
    }
    
    public void setLimit(BigDecimal limit) {
        this.limit = limit;
    }
}
