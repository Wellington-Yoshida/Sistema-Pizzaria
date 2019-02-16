package br.com.sistemapizzaria.SistemaparaPizzaria.enums;

public enum Tamanho {

    PEQUENA("PEQUENA"),
    MEDIA("MEDIA"),
    GRANDE("GRANDE");

    private String tamanho;

    Tamanho(String tamanho) {
        this.tamanho = tamanho;
    }

    public String getTamanho() {
        return tamanho;
    }
}
