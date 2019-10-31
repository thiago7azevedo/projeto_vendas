package br.com.projetovendas.service;


import br.com.projetovendas.entity.Categoria;
import br.com.projetovendas.entity.Produto;
import br.com.projetovendas.repository.CategoriaRepository;
import org.hibernate.ObjectNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CategoriaService {

    private final CategoriaRepository categoriaRepository;

    @Autowired
    public CategoriaService(CategoriaRepository categoriaRepository){
        this.categoriaRepository = categoriaRepository;
    }

//    public Categoria listarUmProduto(Long id){
//        return categoriaRepository.findById(id).orElseThrow(() ->
//                new ObjectNotFoundException("Produto não encontrado!", Categoria.class.getName()));
//    }
    public List<Categoria> listarTodasCategoriasProduto(Produto produto){
        return categoriaRepository.findByProduto(produto);
    }

//    public void deletarProduto(Long id){
//        Categoria categoria = listarUmProduto(id);
//        categoriaRepository.delete(categoria);
//    }
    public void salvarCategoria(Categoria categoria){
        categoriaRepository.save(categoria);
    }
}
