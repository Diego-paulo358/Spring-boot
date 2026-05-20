package com.loja.lojinha.repository;

import com.loja.lojinha.model.Produto;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ProdutosRepository extends JpaRepository<Produto, Long> {
}
