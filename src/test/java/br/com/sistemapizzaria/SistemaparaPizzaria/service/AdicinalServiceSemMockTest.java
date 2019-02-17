package br.com.sistemapizzaria.SistemaparaPizzaria.service;

import br.com.sistemapizzaria.SistemaparaPizzaria.arquivosuteis.Personalizacao;
import br.com.sistemapizzaria.SistemaparaPizzaria.dto.Adicional.AdicinalPersonalizadoDto;
import br.com.sistemapizzaria.SistemaparaPizzaria.dto.Adicional.AdicionalDto;
import br.com.sistemapizzaria.SistemaparaPizzaria.enums.Sabor;
import br.com.sistemapizzaria.SistemaparaPizzaria.model.Adicional;
import br.com.sistemapizzaria.SistemaparaPizzaria.model.Pizza;
import br.com.sistemapizzaria.SistemaparaPizzaria.service.Adicional.AdicionalService;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.runners.MockitoJUnitRunner;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

import static br.com.sistemapizzaria.SistemaparaPizzaria.arquivosuteis.Personalizacao.BORDA_RECHEADA;
import static br.com.sistemapizzaria.SistemaparaPizzaria.enums.Tamanho.MEDIA;
import static org.junit.Assert.assertEquals;

@RunWith(SpringRunner.class)
@SpringBootTest
@TestPropertySource(locations = "classpath:application-test.properties")
@Transactional
public class AdicinalServiceSemMockTest {

    @Autowired
    private AdicionalService adicionalService;

    @Test
    public void deveConverterDtoParaAdicional() {
        Pizza pizza = new Pizza(MEDIA.getTamanho(), Sabor.CALABRESA.getSabor(),"00:20", 30.00);
        pizza.setId(1L);
        AdicinalPersonalizadoDto adicinalPersonalizadoDto = criaAdicionalPersonalizadoDto(pizza);
        Adicional adicional = adicionalService.converteDtoParaAdicional(adicinalPersonalizadoDto, pizza);
        assertEquals(pizza, adicional.getPizza());
    }

    @Test
    public void deveValidaPersonalizacao() {
        Pizza pizza = new Pizza(MEDIA.getTamanho(), Sabor.CALABRESA.getSabor(),"00:20", 30.00);
        pizza.setId(1L);
        AdicionalDto adicionalDto = criaAdicionalDto(pizza);
        AdicinalPersonalizadoDto adicinalPersonalizadoDto = adicionalService.validaPersonalizacao(adicionalDto);
        assertEquals("00:05", adicinalPersonalizadoDto.getTempoAdicional());
        assertEquals("1", adicinalPersonalizadoDto.getPizza_id());
        double valorAdicional = adicinalPersonalizadoDto.getValorAdicional();
        assertEquals(5.0, adicinalPersonalizadoDto.getValorAdicional(), 5.0);
    }

    private AdicionalDto criaAdicionalDto(Pizza pizza) {
        AdicionalDto adicionalDto = new AdicionalDto();
        adicionalDto.setPizza_id(pizza.getId().toString());
        adicionalDto.setPersonalizacoes(addPersonalizacoes());
        return adicionalDto;
    }

    private AdicinalPersonalizadoDto criaAdicionalPersonalizadoDto(Pizza pizza) {
        AdicinalPersonalizadoDto adicinalPersonalizadoDto = new AdicinalPersonalizadoDto();
        adicinalPersonalizadoDto.setTempoAdicional("00:05");
        adicinalPersonalizadoDto.setValorAdicional(5.00);
        adicinalPersonalizadoDto.setPizza_id(pizza.getId().toString());
        adicinalPersonalizadoDto.setPersonalizacoes(addPersonalizacoes());
        return adicinalPersonalizadoDto;
    }

    private List<String> addPersonalizacoes() {
        List<String> personalizacoes = new ArrayList<String>();
        personalizacoes.add(BORDA_RECHEADA);
        return personalizacoes;
    }
}
