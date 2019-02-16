package br.com.sistemapizzaria.SistemaparaPizzaria.dto;

public class PizzaDtoComTempoEPreco extends PizzaDto{

    private String precoPizza;
    private String tempoPreparo;

    public String getPrecoPizza() {
        return precoPizza;
    }

    public void setPrecoPizza(String precoPizza) {
        this.precoPizza = precoPizza;
    }

    public String getTempoPreparo() {
        return tempoPreparo;
    }

    public void setTempoPreparo(String tempoPreparo) {
        this.tempoPreparo = tempoPreparo;
    }
}
