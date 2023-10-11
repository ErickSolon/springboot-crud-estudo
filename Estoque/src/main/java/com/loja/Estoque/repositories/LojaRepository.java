package com.loja.Estoque.repositories;

import com.loja.Estoque.models.Produtos;
import com.loja.Estoque.models.dtos.EstoqueDTO;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface LojaRepository extends JpaRepository<Produtos, Long> {
    @Query(nativeQuery = true, value = "SELECT * FROM Produtos INNER JOIN Precos ON Produtos.id = Precos.pid")
    Page<Produtos> findAllProdutos(Pageable pageable);

    @Query(nativeQuery = true, value = "SELECT * FROM Produtos INNER JOIN Precos ON Produtos.id = Precos.pid WHERE Produtos.id = ?1")
    Produtos findByIdProdutos(Long id);
}
