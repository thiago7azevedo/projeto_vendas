package br.com.projetovendas.controller;


import br.com.projetovendas.entity.Categoria;
import br.com.projetovendas.entity.Produto;
import br.com.projetovendas.service.CategoriaService;
import br.com.projetovendas.service.ProdutoService;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;


import java.util.Arrays;
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

    @PostMapping("/adicionar")
    public ModelAndView adicionar(Produto produto, Long[] id) {
        List<Categoria> categorias = categoriaService.listarCategoriaId(Arrays.asList(id));
        produto.setCategoria(categorias);
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
        List<Categoria> categorias = categoriaService.listarTodasCategorias();
        mv.addObject("categorias", categorias);
        return mv;
    }
    @GetMapping("/deletar/{id}")
    public ModelAndView deletar(@PathVariable Long id) {
        produtoService.deletarProduto(id);
        return listar();
    }
}
