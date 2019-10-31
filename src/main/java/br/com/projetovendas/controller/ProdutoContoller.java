package br.com.projetovendas.controller;

import br.com.projetovendas.entity.Categoria;
import br.com.projetovendas.entity.Produto;
import br.com.projetovendas.service.CategoriaService;
import br.com.projetovendas.service.ProdutoService;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import javax.validation.Valid;
import java.util.List;

@Controller
@RequestMapping("/produto")
public class ProdutoContoller {

    private ProdutoService produtoService;
    private CategoriaService categoriaService;

    public ProdutoContoller(ProdutoService produtoService, CategoriaService categoriaService){
        this.categoriaService = categoriaService;
        this.produtoService = produtoService;
    }
    @GetMapping
    public ModelAndView listar() {
        ModelAndView mv = new ModelAndView("produto/listarproduto");
        List<Produto> produtos = produtoService.listarTodosProdutos();
        mv.addObject("p", produtos);
        return mv;
    }
    @GetMapping("/adicionar")
    public ModelAndView adicionarProduto(Produto produto) {
        ModelAndView mv = new ModelAndView("produto/adicionarproduto");
        mv.addObject("produto", produto);
        return mv;
    }
    @PostMapping("/adicionar")
    public ModelAndView adicionar(@Valid Produto produto, BindingResult result) {
        if (result.hasErrors()) {
            return adicionarProduto(produto);
        }
        produtoService.cadastrar(produto);
        return listar();
    }
    @GetMapping("/editar/{id}")
    public ModelAndView editarProduto(@PathVariable("id") Long id) {
        Produto produto = produtoService.listarUmProduto(id);
        return adicionarProduto(produto);
    }
    @GetMapping("/detalhes/{id}")
    public ModelAndView detalhesproduto(@PathVariable("id") Long id) {
        Produto produto = produtoService.listarUmProduto(id);
        ModelAndView mv = new ModelAndView("produto/detalhesproduto");
        mv.addObject("produto", produto);
        List<Categoria> categorias = categoriaService.listarTodasCategoriasProduto(produto);
        mv.addObject("categorias", categorias);
        return mv;
    }
    @GetMapping("/deletar/{id}")
    public ModelAndView deletar(@PathVariable Long id) {
        produtoService.deletarProduto(id);
        return listar();
    }
}
