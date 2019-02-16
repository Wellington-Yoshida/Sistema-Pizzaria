package br.com.sistemapizzaria.SistemaparaPizzaria.model;

import javax.persistence.*;
import java.io.Serializable;

@Entity
@Table(name = "ADICIONAL")
public class Adicional implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @Column(name = "PERSONALIZACAO")
    private String personalizacao;

    @ManyToOne
    @JoinColumn(name = "pizza_id")
    private Pizza pizza;

    public String getPersonalizacao() {
        return personalizacao;
    }

    public void setPersonalizacao(String personalizacao) {
        this.personalizacao = personalizacao;
    }

    public Pizza getPizza() {
        return pizza;
    }

    public void setPizza(Pizza pizza) {
        this.pizza = pizza;
    }
}
