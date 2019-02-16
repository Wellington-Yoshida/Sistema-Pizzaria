package br.com.sistemapizzaria.SistemaparaPizzaria.enums;

public enum Sabor {

    CALABRESA("Calabresa"),
    MARGUERITA("Marguerita"),
    PORTUGUESA("Portuguesa");

    private String sabor;

    Sabor(String sabor) {
        this.sabor = sabor;
    }

    public String getSabor() {
        return sabor;
    }
}
