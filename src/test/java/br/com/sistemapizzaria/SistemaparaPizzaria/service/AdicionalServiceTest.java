package br.com.sistemapizzaria.SistemaparaPizzaria.service;

import br.com.sistemapizzaria.SistemaparaPizzaria.enums.Sabor;
import br.com.sistemapizzaria.SistemaparaPizzaria.model.Adicional;
import br.com.sistemapizzaria.SistemaparaPizzaria.model.Pizza;
import br.com.sistemapizzaria.SistemaparaPizzaria.service.Adicional.AdicionalService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.TestPropertySource;
import org.springframework.transaction.annotation.Transactional;

import static br.com.sistemapizzaria.SistemaparaPizzaria.arquivosuteis.Personalizacao.BANCON_EXTRA;
import static br.com.sistemapizzaria.SistemaparaPizzaria.arquivosuteis.Personalizacao.SEM_CEBOLA;
import static br.com.sistemapizzaria.SistemaparaPizzaria.enums.Tamanho.MEDIA;
import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@RunWith(MockitoJUnitRunner.class)
@SpringBootTest
@TestPropertySource(locations = "classpath:application-test.properties")
@Transactional
public class AdicionalServiceTest {

    @Mock
    private AdicionalService adicionalService;

    @Test
    public void deveSalvarAdcional() {
        Pizza pizza = new Pizza(MEDIA.getTamanho(), Sabor.CALABRESA.getSabor(),"00:20", 30.00);
        Adicional adicional = new Adicional(1L, BANCON_EXTRA, pizza);
        adicionalService.salvar(adicional);
        verify(adicionalService).salvar(adicional);
    }

    @Test
    public void deveAtualizarAdicional() {
        Pizza pizza = new Pizza(MEDIA.getTamanho(), Sabor.CALABRESA.getSabor(),"00:20", 30.00);
        Adicional adicional = new Adicional(1L, BANCON_EXTRA, pizza);
        adicional.setPersonalizacao(SEM_CEBOLA);
        adicionalService.atualizar(adicional);
        verify(adicionalService).atualizar(adicional);
    }

    @Test
    public void deveBuscarAdicionalPeloId() {
        Pizza pizza = new Pizza(MEDIA.getTamanho(), Sabor.CALABRESA.getSabor(),"00:20", 30.00);
        Adicional adicional = new Adicional(1L, BANCON_EXTRA, pizza);
        when(adicionalService.findBy(1L)).thenReturn(adicional);
        adicional = adicionalService.findBy(1L);
        verify(adicionalService).findBy(1L);
        long id = adicional.getId();
        assertEquals(1, id);
        assertEquals(BANCON_EXTRA, adicional.getPersonalizacao());
    }

}
