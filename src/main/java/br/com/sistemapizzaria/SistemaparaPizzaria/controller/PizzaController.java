package br.com.sistemapizzaria.SistemaparaPizzaria.controller;

import br.com.sistemapizzaria.SistemaparaPizzaria.dto.PizzaDto;
import br.com.sistemapizzaria.SistemaparaPizzaria.dto.PizzaDtoComTempoEPreco;
import br.com.sistemapizzaria.SistemaparaPizzaria.model.Pizza;
import br.com.sistemapizzaria.SistemaparaPizzaria.service.PizzaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;
import java.text.ParseException;

@RestController
@RequestMapping(value = "api/pizzaria")
public class PizzaController {

    @Autowired
    private PizzaService pizzaService;

    @PostMapping(value = "/montarPizza")
    public ResponseEntity<?> montarPizza(@Valid @RequestBody PizzaDto pizzaDto, BindingResult result) throws ParseException {
        Pizza pizza = new Pizza();
        try {
            PizzaDtoComTempoEPreco pizzaDtoComTempoEPreco = pizzaService.validaTamhoESaborPizza(pizzaDto);
            pizza = pizzaService.converteDtoParaPizza(pizzaDtoComTempoEPreco);
            pizzaService.salvar(pizza);
        } catch (Exception e) {
            return ResponseEntity.badRequest().body("Ocorreu um erro ao montar seu pedido. " + HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<>(pizza, HttpStatus.OK);
    }

}
