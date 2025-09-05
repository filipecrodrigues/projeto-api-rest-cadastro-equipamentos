package com.cadastro.perifericos.controller;

import com.cadastro.perifericos.entites.Periferico;
import com.cadastro.perifericos.service.PerifericoService;
import io.swagger.v3.oas.annotations.Operation;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/perifericos")


public class PerifericoController {

    private final PerifericoService service;

    public PerifericoController(PerifericoService service) {
        this.service = service;
    }

    //metodo Post
    @PostMapping ("/")
    @Operation(summary = "Registrar um novo periferico")
    public ResponseEntity<Periferico> addPeriferico(@RequestBody Periferico periferico) {
        Periferico salvo = service.salvarPeriferico(periferico);
        return ResponseEntity.status(HttpStatus.CREATED).body(salvo);
    }

    //metodo GET- listar
    @GetMapping("/")
    @Operation(summary = "Listar todos os periféricos")
    public ResponseEntity<List<Periferico>> listarTodos() {
        return ResponseEntity.ok(service.listarTodos());
    }

    // Get - Buscar por ID
    @GetMapping("/{id}")
    @Operation(summary = "Buscar periférico por número de série")
    public ResponseEntity<Periferico> buscarPorId(@PathVariable String id) {
        return service.buscarPorId(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    //PUT - Atualizar
    @PutMapping("/{id}")
    @Operation(summary = "Atualizar informações de um periférico")
    public ResponseEntity<Periferico> atualizar(@PathVariable String id, @RequestBody Periferico periferico) {
        return ResponseEntity.ok(service.atualizar(id, periferico));
    }

    //Delete - Excluir
    @DeleteMapping("/{id}")
    @Operation(summary = "Excluir periferico pelo número de série")
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
