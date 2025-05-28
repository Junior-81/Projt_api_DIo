package me.dio.santanderdevweekapi.domain.model;

import jakarta.persistence.*;

/**
 * Classe base abstrata para entidades que possuem ID.
 */
@MappedSuperclass
public abstract class BaseEntity {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    
    public Long getId() {
        return id;
    }
    
    public void setId(Long id) {
        this.id = id;
    }
}
