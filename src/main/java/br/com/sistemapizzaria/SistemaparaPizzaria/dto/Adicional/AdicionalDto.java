package br.com.sistemapizzaria.SistemaparaPizzaria.dto.Adicional;

import java.util.ArrayList;
import java.util.List;

public class AdicionalDto {

    private String pizza_id;
    private List<String> personalizacoes = new ArrayList<String>();

    public String getPizza_id() {
        return pizza_id;
    }

    public void setPizza_id(String pizza_id) {
        this.pizza_id = pizza_id;
    }

    public List<String> getPersonalizacoes() {
        return personalizacoes;
    }

    public void setPersonalizacoes(List<String> personalizacoes) {
        this.personalizacoes = personalizacoes;
    }

    public void addPersonalizacoes(String adicional){
        this.getPersonalizacoes().add(adicional);
    }
}
