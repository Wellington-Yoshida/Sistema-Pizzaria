package br.com.sistemapizzaria.SistemaparaPizzaria.service;

import br.com.sistemapizzaria.SistemaparaPizzaria.dto.PizzaDto;
import br.com.sistemapizzaria.SistemaparaPizzaria.dto.PizzaDtoComTempoEPreco;
import br.com.sistemapizzaria.SistemaparaPizzaria.model.Pizza;

import java.text.ParseException;

public interface PizzaService extends Service<Pizza> {

    Pizza converteDtoParaPizza(PizzaDtoComTempoEPreco pizzaDto) throws ParseException;

    PizzaDtoComTempoEPreco validaTamhoESaborPizza(PizzaDto pizzaDto);
}
