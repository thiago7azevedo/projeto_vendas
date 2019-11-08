package br.com.projetovendas.repository;

import br.com.projetovendas.entity.Categoria;
import br.com.projetovendas.entity.Produto;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ProdutoRepository extends JpaRepository<Produto, Long> {
    public List<Produto> findAllByCategorias(Categoria categoria);

    public List<Produto> findAll();
}
