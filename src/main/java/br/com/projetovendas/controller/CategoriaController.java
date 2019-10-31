package br.com.projetovendas.controller;

import br.com.projetovendas.entity.Categoria;

import br.com.projetovendas.entity.Produto;
import br.com.projetovendas.service.CategoriaService;
import br.com.projetovendas.service.ProdutoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.ArrayList;
import java.util.List;

@Controller
@RequestMapping("/categoria")
public class CategoriaController {

    private final CategoriaService categoriaService;
    private final ProdutoService produtoService;

    @Autowired
    public CategoriaController(CategoriaService categoriaService, ProdutoService produtoService){
        this.categoriaService = categoriaService;
        this.produtoService = produtoService;
    }

    @PostMapping("/salvar/{id}")
    public String salvarCategoria(@PathVariable("id") Long id, Categoria categoria) {
       List<Produto> produto = new ArrayList<>();
        for (Produto p : produto ){
            categoria.setProduto(produto);
        }
        categoriaService.salvarCategoria(categoria);
        return "redirect:/produto/detalhes/{id}";
    }
}
