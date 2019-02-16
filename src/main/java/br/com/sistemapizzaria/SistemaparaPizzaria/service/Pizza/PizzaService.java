package br.com.sistemapizzaria.SistemaparaPizzaria.service.Pizza;

import br.com.sistemapizzaria.SistemaparaPizzaria.dto.Pizza.PizzaDto;
import br.com.sistemapizzaria.SistemaparaPizzaria.dto.Pizza.PizzaDtoComTempoEPreco;
import br.com.sistemapizzaria.SistemaparaPizzaria.model.Pizza;
import br.com.sistemapizzaria.SistemaparaPizzaria.service.Service;

import java.text.ParseException;

public interface PizzaService extends Service<Pizza> {

    Pizza converteDtoParaPizza(PizzaDtoComTempoEPreco pizzaDto) throws ParseException;

    PizzaDtoComTempoEPreco validaTamhoESaborPizza(PizzaDto pizzaDto);
}
