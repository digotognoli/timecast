package com.timecast.repository;

import com.timecast.model.Podcast;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List; // Importe a classe List
import java.util.Optional;

public interface PodcastRepository extends JpaRepository<Podcast, Long> {
    Optional<Podcast> findByIdentificador(Long identificador);

    // NOVO MÉTODO PARA BUSCA:
    // Encontra todos os podcasts cujo título contenha o texto da busca, ignorando maiúsculas/minúsculas.
    List<Podcast> findByTituloContainingIgnoreCase(String titulo);
}