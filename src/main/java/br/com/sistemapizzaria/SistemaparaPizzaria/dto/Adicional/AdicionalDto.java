package br.com.sistemapizzaria.SistemaparaPizzaria.dto.Adicional;

import java.util.ArrayList;
import java.util.List;

public class AdicionalDto {

    private String pizza_id;
    private List<String> personalizações = new ArrayList<String>();

    public String getPizza_id() {
        return pizza_id;
    }

    public void setPizza_id(String pizza_id) {
        this.pizza_id = pizza_id;
    }

    public List<String> getPersonalizações() {
        return personalizações;
    }

    public void setPersonalizações(List<String> personalizações) {
        this.personalizações = personalizações;
    }
}
