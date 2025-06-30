package com.timecast;

import com.timecast.model.Podcast;
import com.timecast.repository.PodcastRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.util.Arrays;
import java.util.List;

@Component
public class DataInitializer implements CommandLineRunner {

    private final PodcastRepository podcastRepository;

    public DataInitializer(PodcastRepository podcastRepository) {
        this.podcastRepository = podcastRepository;
    }

    @Override
    public void run(String... args) throws Exception {
        // Limpa o repositório para evitar duplicatas a cada reinicialização (opcional, mas bom para dev)
        podcastRepository.deleteAll();

        // Cria uma lista de podcasts de exemplo
        List<Podcast> podcasts = Arrays.asList(
            createPodcast("Café Com Inspiração", "Café T&D", "O podcast perfeito para despertar sua criatividade.", "Imagens/cafe.jpg"),
            createPodcast("Podcast de Tech", "Tech Guru C", "Análises semanais sobre gadgets e software.", "Imagens/tecnologia.jpg"),
            createPodcast("Histórias Incríveis", "Contador D", "Narrativas e contos fascinantes de diversos cantos.", "Imagens/historias.jpg"),
            createPodcast("Game Over Review", "Gamer G", "Análises e discussões sobre videogames.", "Imagens/game.jpg"),
            createPodcast("Fotografia Diária", "Fotógrafo F", "Dicas e técnicas para amantes da fotografia.", "Imagens/fotografia.jpg")
        );

        // Salva todos os podcasts no banco de dados
        podcastRepository.saveAll(podcasts);

        System.out.println(">>> " + podcasts.size() + " podcasts de exemplo foram inseridos no banco de dados H2. <<<");
    }

    private Podcast createPodcast(String titulo, String autor, String descricao, String imagemUrl) {
        Podcast p = new Podcast();
        p.setTitulo(titulo);
        p.setAutor(autor);
        p.setDescricao(descricao);
        p.setImagemUrl(imagemUrl);
        return p;
    }
}