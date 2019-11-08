package br.com.projetovendas.controller;

import br.com.projetovendas.entity.Categoria;


import br.com.projetovendas.service.CategoriaService;
import br.com.projetovendas.service.ProdutoService;
import org.springframework.beans.factory.annotation.Autowired;
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
@RequestMapping("/categoria")
public class CategoriaController {

    private final CategoriaService categoriaService;
    private final ProdutoService produtoService;


    @Autowired
    public CategoriaController(CategoriaService categoriaService, ProdutoService produtoService) {

        this.categoriaService = categoriaService;
        this.produtoService = produtoService;

    }

    @GetMapping("")
    public ModelAndView listar() {
        ModelAndView mv = new ModelAndView("categoria/listarcategoria");
        List<Categoria> categorias = categoriaService.listarTodasCategorias();
        mv.addObject("categorias", categorias);
        return mv;

    }

    @GetMapping("/adicionar")
    public ModelAndView adicionarCategoria (Categoria categoria){
        ModelAndView mv = new ModelAndView("categoria/adicionarcategoria");
        mv.addObject("categoria", categoria);
        return mv;
    }

    @PostMapping("/adicionar")
    public ModelAndView adicionar(@Valid Categoria categoria, BindingResult result) {
        if (result.hasErrors()) {
            return adicionarCategoria(categoria);
        }
      this.categoriaService.salvarCategoria(categoria);
        return listar();
    }

    @GetMapping("/editar/{id}")
    public ModelAndView editarCategoria(@PathVariable("id") Long id) {
        Categoria categoria = this.categoriaService.listarCategoria(id);
        return adicionarCategoria(categoria);

    }
    /*
    	@GetMapping("/detalhes/{id}")
	public ModelAndView detalhar(@PathVariable("id") Long id) {
		Categoria categoria = this.categoriaService.obter(id);
		ModelAndView mv = new ModelAndView("categoria/detalhes");
		mv.addObject("categoria", categoria);
//		List<Produto> produtos = produtoService.listarTodasCategoria(categoria);
//		mv.addObject("produtos", produtos);
		return mv;
	}
     */

    @GetMapping("/deletar/{id}")
    public ModelAndView deletar(@PathVariable("id") Long id) {
        categoriaService.deletarCategoria(id);
        return listar();
    }

    }

