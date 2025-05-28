package me.dio.santanderdevweekapi.domain.model;

import jakarta.persistence.*;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotBlank;

import java.util.List;

/**
 * Entidade que representa um usuário do sistema bancário.
 */
@Entity(name = "tb_user")
public class User extends BaseEntity {
    
    @NotBlank(message = "O nome é obrigatório")
    private String name;
    
    @OneToOne(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    @Valid
    private Account account;
    
    @OneToOne(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    @Valid
    private Card card;
      @OneToMany(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    @JoinColumn(name = "user_id")
    private List<Feature> features;
    
    @OneToMany(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    @JoinColumn(name = "user_id")
    private List<News> news;
    
    public User() {}
    
    public User(String name, Account account, Card card, List<Feature> features, List<News> news) {
        this.name = name;
        this.account = account;
        this.card = card;
        this.features = features;
        this.news = news;
    }
    
    public String getName() {
        return name;
    }
    
    public void setName(String name) {
        this.name = name;
    }
    
    public Account getAccount() {
        return account;
    }
    
    public void setAccount(Account account) {
        this.account = account;
    }
    
    public Card getCard() {
        return card;
    }
    
    public void setCard(Card card) {
        this.card = card;
    }
    
    public List<Feature> getFeatures() {
        return features;
    }
    
    public void setFeatures(List<Feature> features) {
        this.features = features;
    }
    
    public List<News> getNews() {
        return news;
    }
    
    public void setNews(List<News> news) {
        this.news = news;
    }
}
