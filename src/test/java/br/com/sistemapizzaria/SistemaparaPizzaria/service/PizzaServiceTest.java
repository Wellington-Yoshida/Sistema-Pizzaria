package br.com.sistemapizzaria.SistemaparaPizzaria.service;

import br.com.sistemapizzaria.SistemaparaPizzaria.dto.Pizza.PizzaDtoComTempoEPreco;
import br.com.sistemapizzaria.SistemaparaPizzaria.enums.Sabor;
import br.com.sistemapizzaria.SistemaparaPizzaria.model.Pizza;
import br.com.sistemapizzaria.SistemaparaPizzaria.service.Pizza.PizzaService;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.runners.MockitoJUnitRunner;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.TestPropertySource;
import org.springframework.transaction.annotation.Transactional;

import java.text.ParseException;

import static br.com.sistemapizzaria.SistemaparaPizzaria.enums.Sabor.MARGUERITA;
import static br.com.sistemapizzaria.SistemaparaPizzaria.enums.Tamanho.GRANDE;
import static br.com.sistemapizzaria.SistemaparaPizzaria.enums.Tamanho.MEDIA;
import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@RunWith(MockitoJUnitRunner.class)
@SpringBootTest
@TestPropertySource(locations = "classpath:application-test.properties")
@Transactional
public class PizzaServiceTest {

    @Mock
    private PizzaService pizzaService;


    @Test
    public void deveSalvarPizza() {
        Pizza pizza = new Pizza(MEDIA.getTamanho(), Sabor.CALABRESA.getSabor(),"00:20", 30.00);
        pizzaService.salvar(pizza);
        verify(pizzaService).salvar(pizza);
    }

    @Test
    public void deveAtualizarPizza() {
        Pizza pizza = new Pizza(MEDIA.getTamanho(), Sabor.CALABRESA.getSabor(),"00:20", 30.00);
        pizza.setTamanho(GRANDE.getTamanho());
        pizza.setValorPizza(40.00);
        pizza.setTempoPreparo("00:25");
        pizzaService.atualizar(pizza);
        verify(pizzaService).atualizar(pizza);
    }

    @Test
    public void deveBuscarPizzaPeloId() {
        Pizza pizza = new Pizza(MEDIA.getTamanho(), Sabor.CALABRESA.getSabor(),"00:20", 30.00);
        pizza.setId(1L);
        when(pizzaService.findBy(1L)).thenReturn(pizza);
        pizza = pizzaService.findBy(1L);
        verify(pizzaService).findBy(1L);
        long id = pizza.getId();
        assertEquals(1, id);
    }

    @Test
    public void deveConverteDtoParaPizza() throws ParseException {
        Pizza pizza = new Pizza(GRANDE.getTamanho(), MARGUERITA.getSabor(),"00:20", 40.00);
        PizzaDtoComTempoEPreco pizzaDtoComTempoEPreco = new PizzaDtoComTempoEPreco();
        when(pizzaService.converteDtoParaPizza(pizzaDtoComTempoEPreco)).thenReturn(pizza);
        pizza = pizzaService.converteDtoParaPizza(pizzaDtoComTempoEPreco);
        verify(pizzaService).converteDtoParaPizza(pizzaDtoComTempoEPreco);
        double valorPizza = pizza.getValorPizza();
        assertEquals(40.00, valorPizza, 40.00);
        assertEquals(GRANDE.getTamanho(), pizza.getTamanho());
        assertEquals(MARGUERITA.getSabor(), pizza.getSabor());
    }
}
