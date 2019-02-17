package br.com.sistemapizzaria.SistemaparaPizzaria.enums;

public enum Tamanho {

    PEQUENA("Pequena"),
    MEDIA("Media"),
    GRANDE("Grande");

    private String tamanho;

    Tamanho(String tamanho) {
        this.tamanho = tamanho;
    }

    public String getTamanho() {
        return tamanho;
    }
}
