package br.com.sistemapizzaria.SistemaparaPizzaria.dto.Adicional;

public class AdicinalPersonalizadoDto extends AdicionalDto{

    private Double valorAdicional;
    private String tempoAdicional;

    public Double getValorAdicional() {
        return valorAdicional;
    }

    public void setValorAdicional(Double valorAdicional) {
        this.valorAdicional = valorAdicional;
    }

    public String getTempoAdicional() {
        return tempoAdicional;
    }

    public void setTempoAdicional(String tempoAdicional) {
        this.tempoAdicional = tempoAdicional;
    }

    public void addPersonalizacao(String adicional){
        this.getPersonalizações().add(adicional);
    }
}
