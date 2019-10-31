package br.com.projetovendas.service;

import br.com.projetovendas.entity.Produto;
import br.com.projetovendas.repository.ProdutoRepository;
import org.hibernate.ObjectNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ProdutoService {

    private final ProdutoRepository produtoRepository;

    @Autowired
    public ProdutoService(ProdutoRepository produtoRepository) {
        this.produtoRepository = produtoRepository;
    }

    public Produto listarUmProduto(Long id) {
        return produtoRepository.findById(id).orElseThrow(() ->
                new ObjectNotFoundException("Produto não encontrado", Produto.class.getName()));
    }

    public List<Produto> listarTodosProdutos() {
        return produtoRepository.findAll();
    }

    public void cadastrar(Produto produto) {
        produtoRepository.save(produto);
    }

    public void deletarProduto(Long id) {
        Produto produto = listarUmProduto(id);
        produtoRepository.delete(produto);
    }
}