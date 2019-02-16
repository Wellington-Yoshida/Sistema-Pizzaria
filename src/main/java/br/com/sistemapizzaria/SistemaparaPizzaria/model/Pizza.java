package br.com.sistemapizzaria.SistemaparaPizzaria.model;

import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.io.Serializable;
import java.util.List;

@Entity
@Table(name = "PIZZA")
public class Pizza implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @Column(name = "TAMANHO")
    private String tamanho;

    @Column(name = "SABOR")
    private String sabor;

    @Column(name = "TEMPO_PREPARO")
    private String tempoPreparo;

    @Column(name = "VALOR_TOTAL")
    private Double valorTotal;

    @Column(name = "VALOR_PIZZA")
    private Double valorPizza;

    @OneToMany(mappedBy = "pizza", cascade = CascadeType.ALL)
    @JsonIgnore
    private List<Adicional> adicionalList;

    public String getTamanho() {
        return tamanho;
    }

    public void setTamanho(String tamanho) {
        this.tamanho = tamanho;
    }

    public String getSabor() {
        return sabor;
    }

    public void setSabor(String sabor) {
        this.sabor = sabor;
    }

    public String getTempoPreparo() {
        return tempoPreparo;
    }

    public void setTempoPreparo(String tempoPreparo) {
        this.tempoPreparo = tempoPreparo;
    }

    public Double getValorTotal() {
        return valorTotal;
    }

    public void setValorTotal(Double valorTotal) {
        this.valorTotal = valorTotal;
    }

    public Double getValorPizza() {
        return valorPizza;
    }

    public void setValorPizza(Double valorPizza) {
        this.valorPizza = valorPizza;
    }

    public List<Adicional> getAdicionalList() {
        return adicionalList;
    }

    public void setAdicionalList(List<Adicional> adicionalList) {
        this.adicionalList = adicionalList;
    }
}
