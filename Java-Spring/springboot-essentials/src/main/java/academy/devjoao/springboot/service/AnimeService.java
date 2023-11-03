package academy.devjoao.springboot.service;


import academy.devjoao.springboot.domain.Anime;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.concurrent.ThreadLocalRandom;

// COLOCA AS REGRAS DE NEGOCIO DOS CONTROLLER
@Service
public class AnimeService {
    private static List<Anime> animes;
    static {
        animes = List.copyOf(List.of(
                new Anime( 1L, "DBZ"),
                new Anime( 2L, "Naruto"),
                new Anime( 3L, "HxH")
        ));
    }


    public List<Anime> listAll() {
        return  animes;
    }

    public Anime findByID(Long id) {
        return animes.stream()
                .filter(anime -> anime.getId() == (id))
                .findFirst()
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.BAD_REQUEST, "Anime ID not found"));
    }


    public Anime save(Anime anime) {
        anime.setId(ThreadLocalRandom.current().nextLong(3, 100000));
        animes.add(anime);
        return anime;
    }
}
