package br.com.projetovendas.entity;


import org.hibernate.envers.Audited;

import javax.persistence.*;

import javax.validation.constraints.DecimalMin;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import java.util.ArrayList;
import java.util.List;

@Entity
@Audited
@Table(name = "produto")
public class Produto extends BaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    @NotEmpty(message = "Insira seu nome!")
    private String nome;
    @NotNull(message="Valor é obrigatório")
    @DecimalMin(message="Valor mínimo exigido",value="0.01")
    private Double valor;
    @NotNull(message = "Precisamos da quantidade do produto")
    private Integer quantidade;

    @ManyToMany(cascade=CascadeType.ALL, fetch=FetchType.EAGER)
    @JoinTable(name="produto_categoria",
            joinColumns=@JoinColumn(name="produto_id"),
            inverseJoinColumns=@JoinColumn(name="categoria_id"))
    @NotEmpty(message="Obrigatório definir ao menos 1 categoria para o produto")
    private List<Categoria> categorias = new ArrayList<>();

    public Produto() {
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

    public Double getValor() {
        return valor;
    }

    public void setValor(Double valor) {
        this.valor = valor;
    }

    public Integer getQuantidade() {
        return quantidade;
    }

    public void setQuantidade(Integer quantidade) {
        this.quantidade = quantidade;
    }

    public List<Categoria> getCategoria() {
        return categorias;
    }

    public void setCategoria(List<Categoria> categoria) {
        this.categorias = categoria;
    }
}
