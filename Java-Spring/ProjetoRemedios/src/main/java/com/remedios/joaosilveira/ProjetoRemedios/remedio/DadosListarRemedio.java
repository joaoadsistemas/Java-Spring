package com.remedios.joaosilveira.ProjetoRemedios.remedio;

import java.time.LocalDate;

public record DadosListarRemedio(
        Long id,
        String nome,
        Via via,
        String lote,
        Laboratorio laboratorio,
        LocalDate validade
) {

    public DadosListarRemedio(Remedio remedio) {
        this(remedio.getId(), remedio.getNome(), remedio.getVia(), remedio.getLote(), remedio.getLaboratorio(),
                remedio.getValidade());
    }

}
