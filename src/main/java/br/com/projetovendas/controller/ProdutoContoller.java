package br.com.projetovendas.controller;


import br.com.projetovendas.entity.Categoria;
import br.com.projetovendas.entity.Produto;
import br.com.projetovendas.service.CategoriaService;
import br.com.projetovendas.service.ProdutoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;


import javax.validation.Valid;
import java.util.Arrays;
import java.util.List;

@Controller
@RequestMapping("/produto")
public class ProdutoContoller {

    private final  ProdutoService produtoService;
    private final CategoriaService categoriaService;

    @Autowired
    public ProdutoContoller(ProdutoService produtoService, CategoriaService categoriaService){

        this.categoriaService = categoriaService;
        this.produtoService = produtoService;
    }
    @GetMapping("")
    public ModelAndView listar() {
        ModelAndView mv = new ModelAndView("produto/listarproduto");
        List<Produto> produtos = this.produtoService.listarTodosProdutos();
        mv.addObject("produtos", produtos);
        return mv;
    }

    @GetMapping("/adicionar")
    public ModelAndView adicionarProduto(Produto produto) {
        ModelAndView mv = new ModelAndView("produto/adicionarproduto");
        mv.addObject("produto", produto);
        List<Categoria> categoria = this.categoriaService.listarTodasCategorias();
        mv.addObject("todasCategorias", categoria);
        return mv;
    }
/*
    @PostMapping("/adicionar")
    public ModelAndView adicionar(@Valid Produto produto, Long[] id) {
        List<Categoria> categorias = categoriaService.listarCategoriaId(Arrays.asList(id));
        produto.setCategoria(categorias);
        produtoService.cadastrar(produto);
        return listar();
    }
    */

    @PostMapping("/adicionar")
    public ModelAndView adicionar(@Valid Produto produto, BindingResult result) {
        if(result.hasErrors()) {
            return adicionarProduto(produto);
        }

        this.produtoService.cadastrar(produto);
        return listar();
    }

    @GetMapping("/editar/{id}")
    public ModelAndView editar(@PathVariable("id") Long id) {
        Produto produto = produtoService.listarUmProduto(id);
        return adicionarProduto(produto);
    }
    @GetMapping("/detalhes/{id}")
    public ModelAndView detalhes(@PathVariable("id") Long id) {
        Produto produto = produtoService.listarUmProduto(id);
        ModelAndView mv = new ModelAndView("produto/detalhesproduto");
        mv.addObject("produto", produto);
        List<Categoria> categorias = categoriaService.listarTodasCategorias(produto);
        mv.addObject("categorias", categorias);
        return mv;
    }
    @GetMapping("/deletar/{id}")
    public ModelAndView deletar(@PathVariable Long id) {
        produtoService.deletarProduto(id);
        return listar();
    }
}
