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

    public EstoqueDTO produtoById(Long id) {
        var produto = lojaRepository.findByIdProdutos(id);
        EstoqueDTO estoqueDTO = new EstoqueDTO(produto.precos.pid, produto.precos.valor, produto.id, produto.titulo, produto.qtd);

        return estoqueDTO;
    }

    public void deleteByProdutoById(Long id) {
        var produto = lojaRepository.findByIdProdutos(id);;

        lojaRepository.delete(produto);
    }

    public Produtos updateByid(Long id, Produtos produtos) {
        var produto = lojaRepository.findByIdProdutos(id);;
        produto.id = id;
        produto.titulo = produtos.titulo;
        produto.qtd = produtos.qtd;
        produto.precos.valor = produtos.precos.valor;
        lojaRepository.save(produto);
        return produto;
    }
}
