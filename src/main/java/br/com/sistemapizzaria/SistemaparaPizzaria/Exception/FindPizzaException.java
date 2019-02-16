package br.com.sistemapizzaria.SistemaparaPizzaria.Exception;

public class FindPizzaException extends Exception{

    private String mensagem;

    public FindPizzaException(String message) {
        this.mensagem = mensagem;
    }

    public String getMensagem() {
        return mensagem;
    }
}
