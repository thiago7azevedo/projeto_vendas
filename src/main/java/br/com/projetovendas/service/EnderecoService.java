package br.com.projetovendas.service;

import br.com.projetovendas.entity.Cliente;
import br.com.projetovendas.entity.Endereco;
import br.com.projetovendas.repository.EnderecoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class EnderecoService {

    private final EnderecoRepository enderecoRepository;

    @Autowired
    public EnderecoService(EnderecoRepository enderecoRepository) {
        this.enderecoRepository = enderecoRepository;
    }

    public void salvarEndereco(Endereco endereco) {
        enderecoRepository.save(endereco);
    }

    public List<Endereco> listarTodosEnderecosCliente(Cliente cliente) {
        return enderecoRepository.findByCliente(cliente);
    }

}
