package com.remedios.joaosilveira.ProjetoRemedios.remedio;

import jakarta.persistence.Enumerated;
import jakarta.validation.constraints.Future;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

import java.time.LocalDate;

public record DadosCadastroRemedio(
        @Enumerated
        Via via,
        @NotBlank
        String nome,
        @NotBlank
        String lote,
        Integer quantidade,
        @Future
        LocalDate validade,
        @Enumerated
        Laboratorio laboratorio
        ) {
}
