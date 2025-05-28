package me.dio.santanderdevweekapi.domain.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;

/**
 * Entidade que representa uma funcionalidade do sistema.
 */
@Entity(name = "tb_feature")
public class Feature extends BaseEntity {
    
    @NotBlank(message = "O ícone é obrigatório")
    private String icon;
    
    @NotBlank(message = "A descrição é obrigatória")
    private String description;
    
    public Feature() {}
    
    public Feature(String icon, String description) {
        this.icon = icon;
        this.description = description;
    }
    
    public String getIcon() {
        return icon;
    }
    
    public void setIcon(String icon) {
        this.icon = icon;
    }
    
    public String getDescription() {
        return description;
    }
    
    public void setDescription(String description) {
        this.description = description;
    }
}
