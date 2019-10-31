package br.com.projetovendas.repository;

import br.com.projetovendas.entity.Categoria;

import br.com.projetovendas.entity.Produto;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CategoriaRepository extends JpaRepository<Categoria, Long> {
    List<Categoria> findByProduto(Produto produto);
}
