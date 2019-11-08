package br.com.projetovendas.service;

import br.com.projetovendas.entity.Cliente;
import br.com.projetovendas.entity.Endereco;
import br.com.projetovendas.repository.EnderecoRepository;
import org.hibernate.ObjectNotFoundException;
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

    public Endereco listarUmEndereco(Long id) {
        return this.enderecoRepository.findById(id).orElseThrow(() ->
                new ObjectNotFoundException("Endereço não encontrado", Endereco.class.getName()));
    }

    public List<Endereco> listarTodosEnderecosCliente(Cliente cliente) {
        return enderecoRepository.findAllByCliente(cliente);
    }
    public void deleteEndereco(Long id) {
        Endereco endereco = listarUmEndereco(id);
       enderecoRepository.delete(endereco);
    }

    public List<Endereco> listarTodosEnderecos() {
        return this.enderecoRepository.findAll();

    }
}
