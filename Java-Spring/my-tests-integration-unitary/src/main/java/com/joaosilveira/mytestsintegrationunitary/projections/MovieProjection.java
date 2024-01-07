package com.joaosilveira.mytestsintegrationunitary.projections;

import com.joaosilveira.mytestsintegrationunitary.dtos.GenreDTO;

import java.util.Set;

public interface MovieProjection {

    Long getId();
    String getMovieName();
    String getDescription();
    Set<GenreProjection> getGenres();


}
