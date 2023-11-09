package com.joaosilveira.workshopmongo.dtos;

import java.time.Instant;

public record CommentDTO(
        String text,
        Instant date,
        AuthorDTO author
) {

}
