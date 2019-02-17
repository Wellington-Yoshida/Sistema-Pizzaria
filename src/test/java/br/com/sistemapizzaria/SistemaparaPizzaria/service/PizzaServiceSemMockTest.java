package br.com.sistemapizzaria.SistemaparaPizzaria.service;

import br.com.sistemapizzaria.SistemaparaPizzaria.dto.Pizza.PizzaDto;
import br.com.sistemapizzaria.SistemaparaPizzaria.dto.Pizza.PizzaDtoComTempoEPreco;
import br.com.sistemapizzaria.SistemaparaPizzaria.enums.Sabor;
import br.com.sistemapizzaria.SistemaparaPizzaria.enums.Tamanho;
import br.com.sistemapizzaria.SistemaparaPizzaria.model.Pizza;
import br.com.sistemapizzaria.SistemaparaPizzaria.service.Pizza.PizzaService;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.transaction.annotation.Transactional;

import java.text.ParseException;

import static br.com.sistemapizzaria.SistemaparaPizzaria.enums.Sabor.CALABRESA;
import static br.com.sistemapizzaria.SistemaparaPizzaria.enums.Sabor.PORTUGUESA;
import static br.com.sistemapizzaria.SistemaparaPizzaria.enums.Tamanho.GRANDE;
import static br.com.sistemapizzaria.SistemaparaPizzaria.enums.Tamanho.MEDIA;
import static org.junit.Assert.assertEquals;

@RunWith(SpringRunner.class)
@SpringBootTest
@TestPropertySource(locations = "classpath:application-test.properties")
@Transactional
public class PizzaServiceSemMockTest {

    @Autowired
    private PizzaService pizzaService;

    @Test
    public void deveConverteDtoParaPizza() throws ParseException {
        PizzaDtoComTempoEPreco pizzaDtoComTempoEPreco = criaPizzaDtoComTempoEPreco();
        Pizza pizza = pizzaService.converteDtoParaPizza(pizzaDtoComTempoEPreco);
        assertEquals("Media", pizza.getTamanho());
        assertEquals("Calabresa", pizza.getSabor());
        assertEquals("00:15", pizza.getTempoPreparo());
        double valorPizza = pizza.getValorPizza();
        assertEquals(20.0, valorPizza, 20.0);
    }

    @Test
    public void deveValidaTamhoESaborPizza() {
        PizzaDto pizzaDto = criaPizzaDto();
        PizzaDtoComTempoEPreco pizzaDtoComTempoEPreco = pizzaService.validaTamhoESaborPizza(pizzaDto);
        assertEquals("40.00", pizzaDtoComTempoEPreco.getPrecoPizza());
        assertEquals("00:30", pizzaDtoComTempoEPreco.getTempoPreparo());
        assertEquals("Grande", pizzaDtoComTempoEPreco.getTamanho());
        assertEquals("Portuguesa", pizzaDtoComTempoEPreco.getSabor());
    }

    private PizzaDto criaPizzaDto() {
        PizzaDto pizzaDto = new PizzaDto();
        pizzaDto.setSabor(PORTUGUESA.getSabor());
        pizzaDto.setTamanho(GRANDE.getTamanho());
        return pizzaDto;
    }

    private PizzaDtoComTempoEPreco criaPizzaDtoComTempoEPreco() {
        PizzaDtoComTempoEPreco pizzaDtoComTempoEPreco = new PizzaDtoComTempoEPreco();
        pizzaDtoComTempoEPreco.setTempoPreparo("00:15");
        pizzaDtoComTempoEPreco.setPrecoPizza("20.00");
        pizzaDtoComTempoEPreco.setSabor(CALABRESA.getSabor());
        pizzaDtoComTempoEPreco.setTamanho(MEDIA.getTamanho());
        return pizzaDtoComTempoEPreco;
    }
}
