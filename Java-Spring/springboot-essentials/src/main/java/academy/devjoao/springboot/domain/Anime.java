package academy.devjoao.springboot.domain;

import lombok.AllArgsConstructor;
import lombok.Data;


// O que temos do Banco de dados

@Data
@AllArgsConstructor
public class Anime {
    private String name;
    private long id;
}
