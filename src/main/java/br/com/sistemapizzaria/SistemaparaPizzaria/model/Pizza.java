package br.com.sistemapizzaria.SistemaparaPizzaria.model;

import br.com.sistemapizzaria.SistemaparaPizzaria.enums.Sabor;
import br.com.sistemapizzaria.SistemaparaPizzaria.enums.Tamanho;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.io.Serializable;
import java.util.Date;

@Entity
@Table(name = "PIZZA")
public class Pizza implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @Enumerated(EnumType.STRING)
    @Column(name = "TAMANHO")
    @NotNull
    private Tamanho tamanho;

    @Enumerated(EnumType.STRING)
    @Column(name = "SABOR")
    @NotNull
    private Sabor sabor;

    @Temporal(TemporalType.TIME)
    @Column(name = "TEMPO_PREPARO")
    private Date tempoPreparo;

    @Column(name = "VALOR_TOTAL")
    private Double valorTotal;

    @Column(name = "VALOR_PIZZA")
    private Double valorPizza;

    public Tamanho getTamanho() {
        return tamanho;
    }

    public void setTamanho(Tamanho tamanho) {
        this.tamanho = tamanho;
    }

    public Sabor getSabor() {
        return sabor;
    }

    public void setSabor(Sabor sabor) {
        this.sabor = sabor;
    }

    public Date getTempoPreparo() {
        return tempoPreparo;
    }

    public void setTempoPreparo(Date tempoPreparo) {
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
}
