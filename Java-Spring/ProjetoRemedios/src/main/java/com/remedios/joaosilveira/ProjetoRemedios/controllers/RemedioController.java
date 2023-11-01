package com.remedios.joaosilveira.ProjetoRemedios.controllers;


import com.remedios.joaosilveira.ProjetoRemedios.remedio.DadosAtualizarRemedio;
import com.remedios.joaosilveira.ProjetoRemedios.remedio.DadosCadastroRemedio;
import com.remedios.joaosilveira.ProjetoRemedios.remedio.DadosListarRemedio;
import com.remedios.joaosilveira.ProjetoRemedios.remedio.Remedio;
import com.remedios.joaosilveira.ProjetoRemedios.service.RemedioService;
import jakarta.transaction.Transactional;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;

import java.util.List;

@RestController
@RequestMapping("/remedios")
public class RemedioController {

    @Autowired
    RemedioService remedioService;

    @PostMapping
    @Transactional
    public ResponseEntity cadastrar(@RequestBody @Valid DadosCadastroRemedio dados, UriComponentsBuilder uriBuilder) {
        var remedio = new Remedio(dados);
       return remedioService.save(remedio, uriBuilder);
    }

    @GetMapping
    public ResponseEntity<List<DadosListarRemedio>> listarAtivo() {
        return remedioService.findAllByAtivoTrue();
    }

    @GetMapping("/inativo")
    public ResponseEntity<List<DadosListarRemedio>> listarInativo() {
        return remedioService.findAllByAtivoFalse();
    }

    @GetMapping("/{id}")
    public ResponseEntity<DadosListarRemedio> findById(@PathVariable(value = "id") @Valid Long id) {
        return remedioService.findById(id);
    }

    @PutMapping
    @Transactional
    public ResponseEntity atualizar(@RequestBody @Valid DadosAtualizarRemedio dados) {
        return remedioService.put(dados);
    }

    @DeleteMapping("/{id}")
    @Transactional
    public void delete(@PathVariable(value = "id") Long id) {
        remedioService.deleteById(id);
    }

    @DeleteMapping("inativar/{id}")
    @Transactional
    public ResponseEntity inativar(@PathVariable(value = "id") Long id) {
        return remedioService.inativar(id);
    }

    @PutMapping("ativar/{id}")
    @Transactional
    public ResponseEntity ativar(@PathVariable(value = "id") Long id) {
        return remedioService.ativar(id);
    }
}
