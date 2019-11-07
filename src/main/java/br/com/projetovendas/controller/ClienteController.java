package br.com.projetovendas.controller;

import br.com.projetovendas.entity.Cliente;
import br.com.projetovendas.entity.Endereco;
import br.com.projetovendas.service.ClienteService;
import br.com.projetovendas.service.EnderecoService;
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
@RequestMapping("/cliente")
public class ClienteController {

    // O uso do lombok (@RequiredArgsConstructor(onConstructor=@__(@Autowired)))
    // faz com que não seja necessário criar o construtor recebendo os services
    // e marcar este construtor com @Autowired

    private final ClienteService clienteService;
    private final EnderecoService enderecoService;

    @Autowired
    public ClienteController(ClienteService clienteService, EnderecoService enderecoService) {
        this.clienteService = clienteService;
        this.enderecoService = enderecoService;
    }

    @GetMapping("")
    public ModelAndView listar() {
        ModelAndView mv = new ModelAndView("cliente/listar");
        List<Cliente> clientes = this.clienteService.listarTodosClientes();
        mv.addObject("clientes", clientes);
        return mv;
    }

    @GetMapping("/adicionar")
    public ModelAndView adicionarCliente(Cliente cliente) {
        ModelAndView mv = new ModelAndView("cliente/adicionar");
        mv.addObject("cliente", cliente);
        return mv;
    }

    @PostMapping("/adicionar")
    public ModelAndView adicionar(@Valid Cliente cliente, BindingResult result) {
        if (result.hasErrors()) {
            return adicionarCliente(cliente);
        }
        this.clienteService.cadastrar(cliente);
        return listar();
    }

    @GetMapping("/editar/{id}")
    public ModelAndView editarCliente(@PathVariable("id") Long id) {
        Cliente cliente = this.clienteService.listarUmCliente(id);
        return adicionarCliente(cliente);
    }

    @GetMapping("/detalhes/{id}")
    public ModelAndView detalhes(@PathVariable("id") Long id) {
        Cliente cliente = this.clienteService.listarUmCliente(id);
        ModelAndView mv = new ModelAndView("cliente/detalhes");
        mv.addObject("cliente", cliente);
        List<Endereco> enderecos = enderecoService.listarTodosEnderecosCliente(cliente);
        mv.addObject("enderecos", enderecos);
        return mv;
    }

    @GetMapping("/deletar/{id}")
    public ModelAndView deletar(@PathVariable Long id) {
        this.clienteService.deletar(id);
        return listar();
    }

}