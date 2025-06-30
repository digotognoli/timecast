package com.timecast.controller;

import com.timecast.model.Episodio;
import com.timecast.model.Podcast;
import com.timecast.repository.EpisodioRepository;
import com.timecast.repository.PodcastRepository;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.UUID;

@RestController
@RequestMapping("/api/episodios")
@CrossOrigin(origins = "*")
public class EpisodioController {

    private final EpisodioRepository episodioRepository;
    private final PodcastRepository podcastRepository;

    // Define o caminho da pasta onde os áudios serão salvos
    private final Path rootLocation = Paths.get("uploads/audios");

    public EpisodioController(EpisodioRepository episodioRepository, PodcastRepository podcastRepository) {
        this.episodioRepository = episodioRepository;
        this.podcastRepository = podcastRepository;
        // Cria a pasta de uploads se ela não existir
        try {
            Files.createDirectories(rootLocation);
        } catch (IOException e) {
            throw new RuntimeException("Não foi possível criar a pasta para upload!", e);
        }
    }

    @PostMapping("/upload")
    public ResponseEntity<Episodio> handleFileUpload(@RequestParam("audioFile") MultipartFile file,
                                                     @RequestParam("titulo") String titulo,
                                                     @RequestParam("podcastId") Long podcastId) {
        if (file.isEmpty()) {
            return ResponseEntity.badRequest().build();
        }

        Podcast podcast = podcastRepository.findById(podcastId)
                .orElseThrow(() -> new RuntimeException("Podcast não encontrado com id: " + podcastId));

        try {
            // Gera um nome de arquivo único para evitar conflitos
            String originalFilename = file.getOriginalFilename();
            String extension = originalFilename.substring(originalFilename.lastIndexOf("."));
            String uniqueFilename = UUID.randomUUID().toString() + extension;

            // Salva o arquivo na pasta de uploads
            Files.copy(file.getInputStream(), this.rootLocation.resolve(uniqueFilename));

            // Cria e salva a entidade Episodio no banco de dados
            Episodio episodio = new Episodio();
            episodio.setTitulo(titulo);
            episodio.setPodcast(podcast);
            episodio.setAudioUrl("/audios/" + uniqueFilename); // URL para acessar o áudio
            // A duração pode ser extraída do arquivo de áudio com bibliotecas mais avançadas
            // Por enquanto, vamos deixar como 0
            episodio.setDuracao(0);

            Episodio savedEpisodio = episodioRepository.save(episodio);

            return ResponseEntity.status(HttpStatus.CREATED).body(savedEpisodio);

        } catch (IOException e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }
}