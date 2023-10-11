package com.loja.Estoque.services;

import com.loja.Estoque.models.Produtos;
import com.loja.Estoque.models.dtos.EstoqueDTO;
import com.loja.Estoque.repositories.LojaRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class LojaService {
    private final LojaRepository lojaRepository;

    public Page<EstoqueDTO> findAllProdutosNoEstoque(Pageable pageable) {
        var produtos = lojaRepository.findAllProdutos(pageable);
        Page<EstoqueDTO> produtosDTO = produtos.map(produto ->
                new EstoqueDTO(produto.getPrecos().getPid(), produto.getPrecos().getValor(), produto.getId(), produto.getTitulo(), produto.getQtd())
        );

        return produtosDTO;
    }

    public Produtos addProdutoNoEstoque(Produtos produtos) {
        return lojaRepository.save(produtos);
    }

    public Produtos produtoById(Long id) {
        var produto = lojaRepository.findByIdProdutos(id);

        return produto;
    }

    public void deleteByProdutoById(Long id) {
        var produto = produtoById(id);
        lojaRepository.delete(produto);
    }

    public Produtos updateByid(Long id, Produtos produtos) {
        var produto = produtoById(id);
        produto.id = id;
        produto.titulo = produtos.titulo;
        produto.qtd = produtos.qtd;
        produto.precos.valor = produtos.precos.valor;
        lojaRepository.save(produto);
        return produto;
    }
}
