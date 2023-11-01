package com.remedios.joaosilveira.ProjetoRemedios.service;

import com.remedios.joaosilveira.ProjetoRemedios.remedio.*;
import com.remedios.joaosilveira.ProjetoRemedios.repository.RemedioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.util.UriComponentsBuilder;

import java.util.List;
import java.util.Objects;
import java.util.Optional;

@Service
public class RemedioService {

    @Autowired
    RemedioRepository remedioRepository;

    public ResponseEntity<DadosDetalhamentoRemedio> save(Remedio remedio, UriComponentsBuilder uriBuilder) {
        remedioRepository.save(remedio);

        var uri = uriBuilder.path("/remedios/{id}").buildAndExpand(remedio.getId()).toUri();

        return ResponseEntity.created(uri).body(new DadosDetalhamentoRemedio(remedio));

    }


    public ResponseEntity<DadosDetalhamentoRemedio> put(DadosAtualizarRemedio dados) {

        var remedio = remedioRepository.getReferenceById(dados.id());
        if (dados.nome() != null) {
            remedio.setNome(dados.nome());

        }
        if (dados.via() != null) {
            remedio.setVia(dados.via());

        }
        if (dados.laboratorio() != null) {
            remedio.setLaboratorio(dados.laboratorio());

        }

    return ResponseEntity.ok(new DadosDetalhamentoRemedio(remedio));

    }

    public ResponseEntity<Object> deleteById(Long id) {
        remedioRepository.deleteById(id);
        return ResponseEntity.noContent().build();
    }

    public ResponseEntity<Object> inativar(Long id) {
        var remedio = remedioRepository.getReferenceById(id);
        remedio.setAtivo(false);
        return ResponseEntity.noContent().build();

    }

    public ResponseEntity<Object> ativar(Long id) {
        var remedio = remedioRepository.getReferenceById(id);
        remedio.setAtivo(true);
        return ResponseEntity.noContent().build();
    }

    public ResponseEntity<List<DadosListarRemedio>> findAllByAtivoTrue() {
        return ResponseEntity.status(HttpStatus.OK).body(remedioRepository.findAllByAtivo(true).stream().map(DadosListarRemedio::new).toList());

    }

    public ResponseEntity<List<DadosListarRemedio>> findAllByAtivoFalse() {
        return ResponseEntity.status(HttpStatus.OK).body(remedioRepository.findAllByAtivo(false).stream().map(DadosListarRemedio::new).toList());

    }


    public ResponseEntity<DadosListarRemedio> findById(Long id) {
        var objRemedio = remedioRepository.findById(id);
        var remedio = new DadosListarRemedio(objRemedio.get());
        return ResponseEntity.ok(remedio);
    }
}
