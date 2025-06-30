package com.timecast.controller;

import com.timecast.model.Podcast;
import com.timecast.repository.PodcastRepository;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/podcasts")
@CrossOrigin(origins = "*")
public class PodcastController {

    private final PodcastRepository repo;

    public PodcastController(PodcastRepository repo) {
        this.repo = repo;
    }

    @GetMapping
    public List<Podcast> listar() {
        return repo.findAll();
    }

    // NOVO ENDPOINT PARA BUSCA
    @GetMapping("/search")
    public List<Podcast> buscar(@RequestParam("q") String query) {
        return repo.findByTituloContainingIgnoreCase(query);
    }

    @GetMapping("/{identificador}")
    public Podcast porIdentificador(@PathVariable Long identificador) {
        return repo.findByIdentificador(identificador).orElse(null);
    }
}