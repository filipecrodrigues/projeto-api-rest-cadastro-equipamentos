package com.cadastro.equipamentos.controller;

import com.cadastro.equipamentos.entites.Equipamento;
import com.cadastro.equipamentos.service.EquipamentoService;
import io.swagger.v3.oas.annotations.Operation;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/equipamentos")


public class EquipamentoController {

    private final EquipamentoService service;

    public EquipamentoController(EquipamentoService service) {
        this.service = service;
    }

    //metodo Post
    @PostMapping
    @Operation(summary = "Registrar um novo equipamento")
    public ResponseEntity<Equipamento> addEquipamento(@RequestBody Equipamento equipamento) {
        Equipamento salvo = service.salvarEquipamento(equipamento);
        return ResponseEntity.status(HttpStatus.CREATED).body(salvo);
    }

    //metodo GET- listar
    @GetMapping
    @Operation(summary = "Listar todos os equipamentos")
    public ResponseEntity<List<Equipamento>> listarTodos() {
        return ResponseEntity.ok(service.listarTodos());
    }

    // Get - Buscar por ID
    @GetMapping("/{id}")
    @Operation(summary = "Buscar equipamento por número de série")
    public ResponseEntity<Equipamento> buscarPorId(@PathVariable String id) {
        return service.buscarPorId(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    //PUT - Atualizar
    @PutMapping("/{id}")
    @Operation(summary = "Atualizar informações de um equipamento")
    public ResponseEntity<Equipamento> atualizar(@PathVariable String id, @RequestBody Equipamento equipamento) {
        return ResponseEntity.ok(service.atualizar(id, equipamento));
    }

    //Delete - Excluir
    @DeleteMapping("/{id}")
    @Operation(summary = "Excluir equipamento pelo número de série")
    public ResponseEntity<Void> deletar(@PathVariable String id) {
        service.deletar(id);
        return ResponseEntity.noContent().build();
    }

    //GET - Gerar relatório Excel
    @GetMapping("/excel")
    @Operation(summary = "Gerar relatório Excel com tempo de uso.")
    public ResponseEntity<byte[]> exportExcel() throws Exception{
    byte[] excel = service.generateExcel();
    HttpHeaders headers = new HttpHeaders();
            headers.setContentType(MediaType.APPLICATION_OCTET_STREAM);
            headers.set(HttpHeaders.CONTENT_DISPOSITION,"attachment; filename=perifericos.xlsx");
            return new ResponseEntity<>(excel,headers,HttpStatus.OK);
    }
}
