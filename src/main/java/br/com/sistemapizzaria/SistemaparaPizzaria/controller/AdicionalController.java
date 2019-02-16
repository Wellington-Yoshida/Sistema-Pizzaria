package br.com.sistemapizzaria.SistemaparaPizzaria.controller;

import br.com.sistemapizzaria.SistemaparaPizzaria.dto.Adicional.AdicinalPersonalizadoDto;
import br.com.sistemapizzaria.SistemaparaPizzaria.dto.Adicional.AdicionalDto;
import br.com.sistemapizzaria.SistemaparaPizzaria.model.Adicional;
import br.com.sistemapizzaria.SistemaparaPizzaria.model.Pizza;
import br.com.sistemapizzaria.SistemaparaPizzaria.service.Adicional.AdicionalService;
import br.com.sistemapizzaria.SistemaparaPizzaria.service.Pizza.PizzaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

import java.util.ArrayList;
import java.util.List;

import static java.lang.Long.parseLong;

@RestController
@RequestMapping(value = "api/pizzaria")
public class AdicionalController {

    @Autowired
    private AdicionalService adicionalService;

    @Autowired
    private PizzaService pizzaService;

    @PostMapping(value = "/personalizarPizza")
    public ResponseEntity<?> personalizarPizza(@Valid @RequestBody AdicionalDto adicionalDto) {
        Adicional adicional = new Adicional();
        try {
            AdicinalPersonalizadoDto adicinalPersonalizadoDto = adicionalService.validaPernalizacao(adicionalDto);
            final Pizza pizza = pizzaService.findBy(parseLong(adicinalPersonalizadoDto.getPizza_id()));
            adicional = adicionalService.converteDtoParaAdicional(adicinalPersonalizadoDto, pizza);
            adicionalService.salvar(adicional);
        } catch (Exception e) {
            return ResponseEntity.badRequest().body("Ocorreu um erro ao personalizar sua pizza. " + HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<>(adicional, HttpStatus.OK);
    }

    @GetMapping(value = "/findAllAdicionais")
    public ResponseEntity<?> findAllAdicional() {
        List<Adicional> adicionalList = new ArrayList<Adicional>();
        try {
            adicionalList = adicionalService.findAll();
        } catch (Exception e) {
            return ResponseEntity.badRequest().body("Não foi localizado nenhum adicional cadastrada no sistema. " + HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<>(adicionalList, HttpStatus.OK);
    }

    @GetMapping(value = "/findAdicionalById/{id}")
    public ResponseEntity<?> findAdicionalById(@PathVariable("id") Long id) {
        Adicional adicional = new Adicional();
        try {
            adicional = adicionalService.findBy(id);
        } catch (Exception e) {
            return ResponseEntity.badRequest().body("Não foi localizado adicional com id: " + id + "," + HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<>(adicional, HttpStatus.OK);
    }
}
