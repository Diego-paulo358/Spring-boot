package com.loja.lojinha.controllers;

import com.loja.lojinha.model.Produto;
import com.loja.lojinha.repository.ProdutosRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping("/lojinha")
public class ProdutoController {
    @Autowired
    private ProdutosRepository repository;

    @PostMapping
    public Produto cadastrar(@RequestBody Produto produto){
        return repository.save(produto);
    }

    @GetMapping
    public List<Produto> listarprodutos(Produto produto){
        return repository.findAll();
    }

    @PutMapping("/{id}")
    public Produto atualizarproduto(@PathVariable Long id, @RequestBody Produto produtoAtualizado){
        Produto produto = repository.findById(id).orElse(null);
        if (produto != null){
            produto.setNome(produtoAtualizado.getNome());
            produto.setPreco(produtoAtualizado.getPreco());
            produto.setCategoria(produtoAtualizado.getCategoria());
            return repository.save(produto);
        }
        return null;
    }

    @DeleteMapping("/{id}")
    public String deletarProduto(@PathVariable Long id){
        repository.deleteById(id);
        return "Produto removido com sucesso";
    }
}
