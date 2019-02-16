package br.com.sistemapizzaria.SistemaparaPizzaria.enums;

public enum Sabor {

    CALABRESA("CALABRESA"),
    MARGUERITA("MARGUERITA"),
    PORTUGUESA("PORTUGUESA");

    private String sabor;

    Sabor(String sabor) {
        this.sabor = sabor;
    }

    public String getSabor() {
        return sabor;
    }
}
