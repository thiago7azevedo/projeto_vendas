package br.com.projetovendas.service;


import br.com.projetovendas.entity.Categoria;

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

     public Categoria listarCategoria(Long id){
        return categoriaRepository.findById(id).orElseThrow(() ->
                new ObjectNotFoundException("categoria não encontrada!", Categoria.class.getName()));
   }
    public List<Categoria> listarTodasCategorias(){
        return categoriaRepository.findAll();
    }

    public void deletarCategoria(Long id){
        Categoria categoria = listarCategoria(id);
        categoriaRepository.delete(categoria);
    }
    public void salvarCategoria(Categoria categoria){
        categoriaRepository.save(categoria);
    }
    public List<Categoria> listarCategoriaId (List id){
        return categoriaRepository.findAllById(id);
    }
}
