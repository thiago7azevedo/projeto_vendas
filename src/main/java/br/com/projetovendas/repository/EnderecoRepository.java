package br.com.projetovendas.repository;

import br.com.projetovendas.entity.Cliente;
import br.com.projetovendas.entity.Endereco;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface EnderecoRepository extends JpaRepository<Endereco, Long> {

    List<Endereco> findByCliente(Cliente cliente);

}
