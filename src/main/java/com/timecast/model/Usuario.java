package com.timecast.model;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table; // Importe esta anotação também

@Entity // Marca esta classe como uma entidade JPA
@Table(name = "usuarios") // Mapeia para a tabela 'usuarios' no banco de dados
public class Usuario {

    @Id // Marca o campo 'id' como a chave primária
    @GeneratedValue(strategy = GenerationType.IDENTITY) // Define a estratégia de geração automática de ID
    private Long id; // Adicione este campo para ser a chave primária

    private String email;
    private String senha;

    public Usuario() {
        // Construtor padrão é necessário para o JPA
    }

    public Usuario(String email, String senha) {
        this.email = email;
        this.senha = senha;
    }

    // Adicione os getters e setters para o campo 'id'
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getSenha() {
        return senha;
    }

    public void setSenha(String senha) {
        this.senha = senha;
    }
}