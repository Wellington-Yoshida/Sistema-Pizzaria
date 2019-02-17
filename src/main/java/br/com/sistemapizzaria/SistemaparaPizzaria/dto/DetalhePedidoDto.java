package br.com.sistemapizzaria.SistemaparaPizzaria.dto;

import java.util.ArrayList;
import java.util.List;

public class DetalhePedidoDto {

    private String tamanho;
    private String sabor;
    private List<String> personalizacao = new ArrayList<String>();
    private Double valorTotal;
    private String tempoPrepado;

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

    public List<String> getPersonalizacao() {
        return personalizacao;
    }

    public void setPersonalizacao(List<String> personalizacao) {
        this.personalizacao = personalizacao;
    }

    public void addPersonalizacao(String personalizacao) {
        this.getPersonalizacao().add(personalizacao);
    }

    public Double getValorTotal() {
        return valorTotal;
    }

    public void setValorTotal(Double valorTotal) {
        this.valorTotal = valorTotal;
    }

    public String getTempoPrepado() {
        return tempoPrepado;
    }

    public void setTempoPrepado(String tempoPrepado) {
        this.tempoPrepado = tempoPrepado;
    }
}
