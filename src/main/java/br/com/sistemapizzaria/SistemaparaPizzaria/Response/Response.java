package br.com.sistemapizzaria.SistemaparaPizzaria.Response;

import java.util.ArrayList;
import java.util.List;

public class Response<T> {

    private T resultado;
    private List<String> errors;

    public T getResultado() {
        return resultado;
    }

    public void setResultado(T resultado) {
        this.resultado = resultado;
    }

    public List<String> getErrors() {
        if (this.errors == null) {
            this.errors = new ArrayList<String>();
        }
        return errors;
    }

    public void setErrors(List<String> errors) {
        this.errors = errors;
    }
}
