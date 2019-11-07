package br.com.projetovendas.service;

import br.com.projetovendas.entity.Cliente;

import br.com.projetovendas.repository.ClienteRepository;
import org.hibernate.ObjectNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ClienteService {

    private final ClienteRepository clienteRepository;

    @Autowired
    public ClienteService(ClienteRepository clienteRepository) {
        this.clienteRepository = clienteRepository;
    }

    public Cliente listarUmCliente(Long id) {
        return clienteRepository.findById(id).orElseThrow(() ->
                new ObjectNotFoundException("Cliente não encontrado", Cliente.class.getName()));
    }

    public List<Cliente> listarTodosClientes() {
        return clienteRepository.findAll();
    }

    public void cadastrar(Cliente cliente) {
        clienteRepository.save(cliente);
    }

    public void deletar(Long id) {
        Cliente cliente = listarUmCliente(id);
        clienteRepository.delete(cliente);
    }

}
