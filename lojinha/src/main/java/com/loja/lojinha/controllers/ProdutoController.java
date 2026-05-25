package com.loja.lojinha.controllers;

import com.loja.lojinha.dto.ProdutoDTO;
import com.loja.lojinha.model.Produto;
import com.loja.lojinha.repository.ProdutosRepository;
import com.loja.lojinha.service.ProdutoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping("/lojinha")
public class ProdutoController {
    private final ProdutoService service;

    public ProdutoController(ProdutoService service) {
        this.service = service;
    }

    @PostMapping
    public ResponseEntity<Produto> cadastrar(@RequestBody @Validated ProdutoDTO dto) {

        Produto produto = service.cadastrar(dto);

        return ResponseEntity.status(HttpStatus.CREATED).body(produto);
    }

    @GetMapping
    public ResponseEntity<List<Produto>> listarTodos() {

        return ResponseEntity.ok(service.listarTodos());
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> buscarPorId(@PathVariable Long id) {

        Produto produto = service.buscarPorId(id);

        if (produto == null) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND)
                    .body("Produto não encontrado");
        }

        return ResponseEntity.ok(produto);
    }

    @PutMapping("/{id}")
    public ResponseEntity<?> atualizar(@PathVariable Long id,
                                       @RequestBody @Validated ProdutoDTO dto) {

        Produto produto = service.atualizar(id, dto);

        if (produto == null) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND)
                    .body("Produto não encontrado");
        }

        return ResponseEntity.ok(produto);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> deletar(@PathVariable Long id) {

        boolean removido = service.deletar(id);

        if (!removido) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND)
                    .body("Produto não encontrado");
        }

        return ResponseEntity.ok("Produto removido com sucesso");
    }

}
