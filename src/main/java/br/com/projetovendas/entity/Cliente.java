package br.com.projetovendas.entity;


import org.hibernate.envers.Audited;
import org.hibernate.validator.constraints.Length;

import javax.persistence.*;
import javax.validation.constraints.Email;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import java.util.ArrayList;
import java.util.List;

@Entity
@Audited
public class Cliente extends BaseEntity {

    @Id
    @GeneratedValue(strategy=GenerationType.AUTO)
    private Long id;

    @NotEmpty(message="Nome é obrigatório")
    private String nome;

    @NotEmpty(message="E-mail é obrigatório")
    @Email(message="E-mail inválido")
    private String email;

    @NotEmpty(message="Senha é obrigatório")
    @Length(min=6,max=8,message="A senha deve possuir no mínimo 6 e no máximo 8 caracteres")
    private String senha;

    @NotNull(message="Idade é obrigatório")
    @Min(value=18,message="Não são permitidos cadastros de clientes menores de 18 anos")
    private String idade;

    @NotEmpty(message="Profissão é obrigatório")
    @Length(min=3,max=200, message="Profissão deve conter pelo menos 3 caracteres")
    private String profissao;

    @OneToMany(mappedBy = "cliente", cascade = CascadeType.ALL)
    private List<Endereco> enderecos = new ArrayList<>();

    public Cliente() {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getIdade() {
        return idade;
    }

    public void setIdade(String idade) {
        this.idade = idade;
    }

    public String getProfissao() {
        return profissao;
    }

    public void setProfissao(String profissao) {
        this.profissao = profissao;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public List<Endereco> getEnderecos() {
        return enderecos;
    }

    public void setEnderecos(List<Endereco> enderecos) {
        this.enderecos = enderecos;
    }

    public String getSenha() {
        return senha;
    }

    public void setSenha(String senha) {
        this.senha = senha;
    }

}
