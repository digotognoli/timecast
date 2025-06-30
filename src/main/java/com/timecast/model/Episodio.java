package com.timecast.model;

import com.fasterxml.jackson.annotation.JsonBackReference;
import jakarta.persistence.*;

@Entity
@Table(name = "episodios")
public class Episodio {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String titulo;
    private int duracao; // em segundos
    private String audioUrl; // Caminho para o arquivo de áudio

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "podcast_id") // Chave estrangeira na tabela de episódios
    @JsonBackReference // Evita loop infinito na serialização JSON
    private Podcast podcast;

    // Getters e Setters ...
    // (O código dos getters e setters permanece o mesmo, adicione para os novos campos)

    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }
    public String getTitulo() { return titulo; }
    public void setTitulo(String titulo) { this.titulo = titulo; }
    public int getDuracao() { return duracao; }
    public void setDuracao(int duracao) { this.duracao = duracao; }
    public String getAudioUrl() { return audioUrl; }
    public void setAudioUrl(String audioUrl) { this.audioUrl = audioUrl; }
    public Podcast getPodcast() { return podcast; }
    public void setPodcast(Podcast podcast) { this.podcast = podcast; }
}