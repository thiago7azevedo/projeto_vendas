package br.com.projetovendas.entity;

import org.hibernate.envers.Audited;

import javax.persistence.*;
import javax.validation.constraints.NotEmpty;
import java.util.ArrayList;
import java.util.List;

@Entity
@Audited
@Table(name = "categoria")
public class Categoria {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    @NotEmpty(message = "Precisamos do seu nome!")
    private String name;

    @ManyToMany
    @JoinTable(name = "produto_categoria",
                joinColumns = @JoinColumn(name = "id.categoria"),
                inverseJoinColumns = @JoinColumn(name = "id.produto")
    )
    private List<Produto> produto = new ArrayList<>();

    public Categoria(){

    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public List<Produto> getProduto() {
        return produto;
    }

    public void setProduto(List<Produto> produto) {
        this.produto = produto;
    }
}
