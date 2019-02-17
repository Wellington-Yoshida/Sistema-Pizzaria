package br.com.sistemapizzaria.SistemaparaPizzaria.controller;

import br.com.sistemapizzaria.SistemaparaPizzaria.dto.Pizza.PizzaDto;
import br.com.sistemapizzaria.SistemaparaPizzaria.dto.Pizza.PizzaDtoComTempoEPreco;
import br.com.sistemapizzaria.SistemaparaPizzaria.model.Pizza;
import br.com.sistemapizzaria.SistemaparaPizzaria.service.Pizza.PizzaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping(value = "api/pizzaria")
public class PizzaController {

    @Autowired
    private PizzaService pizzaService;

    @PostMapping(value = "/montarPizza")
    public ResponseEntity<?> montarPizza(@Valid @RequestBody PizzaDto pizzaDto) throws ParseException {
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

    @GetMapping(value = "/findAllPizza")
    public ResponseEntity<?> findAllPizza() {
        List<Pizza> pizzas = new ArrayList<Pizza>();
        try {
            pizzas = pizzaService.findAll();
        } catch (Exception e) {
            return ResponseEntity.badRequest().body("Não foi localizado nenhuma pizza cadastrada no sistema. " + HttpStatus.NOT_FOUND);
        }
        return ResponseEntity.ok(pizzas);
    }

    @GetMapping(value = "/findPizzaById/{id}")
    public ResponseEntity<?> findPizzaById(@PathVariable("id") Long id) {
        Pizza pizza = new Pizza();
        try {
            pizza = pizzaService.findBy(id);
        } catch (Exception e) {
            return ResponseEntity.badRequest().body("Não foi localizado a pizza com id: " + id + "," + HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<>(pizza, HttpStatus.OK);
    }
}
