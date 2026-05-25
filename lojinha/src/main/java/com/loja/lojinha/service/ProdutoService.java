package com.loja.lojinha.service;

import com.loja.lojinha.dto.ProdutoDTO;
import com.loja.lojinha.model.Produto;
import com.loja.lojinha.repository.ProdutosRepository;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.List;
import java.util.Optional;

import static org.springframework.data.jpa.domain.AbstractPersistable_.id;

@Service
public class ProdutoService {
    private final ProdutosRepository repository;

    public ProdutoService(ProdutosRepository repository) {
        this.repository = repository;
    }

    public Produto cadastrar(ProdutoDTO dto) {

        Produto produto = new Produto();

        produto.setNome(dto.getNome());
        produto.setCategoria(dto.getCategoria());
        produto.setPreco(dto.getPreco());

        return repository.save(produto);
    }

    public List<Produto> listarTodos() {
        return repository.findAll();
    }

    public Produto buscarPorId(Long id) {
        Optional<Produto> produto = repository.findById(id);

        return produto.orElse(null);
    }

    public Produto atualizar(Long id, ProdutoDTO dto) {

        Optional<Produto> produtoOptional = repository.findById(id);

        if (produtoOptional.isEmpty()) {
            return null;
        }

        Produto produto = produtoOptional.get();

        produto.setNome(dto.getNome());
        produto.setCategoria(dto.getCategoria());
        produto.setPreco(dto.getPreco());

        return repository.save(produto);
    }

    public boolean deletar(Long id) {

        Optional<Produto> produto = repository.findById(id);

        if (produto.isEmpty()) {
            return false;
        }

        repository.deleteById(id);

        return true;
    }
}