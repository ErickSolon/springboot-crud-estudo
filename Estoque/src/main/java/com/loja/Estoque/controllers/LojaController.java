package com.loja.Estoque.controllers;

import com.loja.Estoque.models.Produtos;
import com.loja.Estoque.services.LojaService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/loja")
@RequiredArgsConstructor
public class LojaController {
    private final LojaService lojaService;

    @GetMapping
    public ResponseEntity getProdutos(@PageableDefault(page = 0, size = 5) Pageable pageable) {
        var produtos = lojaService.findAllProdutosNoEstoque(pageable);

        return ResponseEntity.ok(produtos);
    }

    @PostMapping
    public ResponseEntity saveProduto(@RequestBody Produtos produtos) {
        lojaService.addProdutoNoEstoque(produtos);
        return ResponseEntity.ok().body(produtos);
    }

    @GetMapping("{id}")
    public ResponseEntity getProdutoById(@PathVariable Long id) {
        return ResponseEntity.ok(lojaService.produtoById(id));
    }

    @PutMapping("{id}")
    public ResponseEntity updateProdutoById(@PathVariable Long id, @RequestBody Produtos produtos) {
        return ResponseEntity.ok(lojaService.updateByid(id, produtos));
    }

    @DeleteMapping("{id}")
    public ResponseEntity deleteProdutoById(@PathVariable Long id) {
        lojaService.deleteByProdutoById(id);
        return ResponseEntity.ok("Produto deletado com sucesso!");
    }
}
