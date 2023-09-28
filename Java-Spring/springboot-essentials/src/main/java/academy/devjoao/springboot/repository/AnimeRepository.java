package academy.devjoao.springboot.repository;

import academy.devjoao.springboot.domain.Anime;

import java.util.List;

// Conexão com o banco de dados CRUD


public interface AnimeRepository {
    List<Anime> listAll();
}
