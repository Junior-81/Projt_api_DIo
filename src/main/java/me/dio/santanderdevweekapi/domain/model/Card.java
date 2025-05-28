package me.dio.santanderdevweekapi.domain.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

import java.math.BigDecimal;

/**
 * Entidade que representa um cartão de crédito.
 */
@Entity(name = "tb_card")
public class Card extends BaseEntity {
    
    @NotBlank(message = "O número do cartão é obrigatório")
    @Column(unique = true)
    private String number;
    
    @NotNull(message = "O limite é obrigatório")
    @Column(name = "available_limit", precision = 13, scale = 2)
    private BigDecimal limit;
    
    public Card() {}
    
    public Card(String number, BigDecimal limit) {
        this.number = number;
        this.limit = limit;
    }
    
    public String getNumber() {
        return number;
    }
    
    public void setNumber(String number) {
        this.number = number;
    }
    
    public BigDecimal getLimit() {
        return limit;
    }
    
    public void setLimit(BigDecimal limit) {
        this.limit = limit;
    }
}
