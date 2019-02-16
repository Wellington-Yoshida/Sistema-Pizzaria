package br.com.sistemapizzaria.SistemaparaPizzaria.service;

import br.com.sistemapizzaria.SistemaparaPizzaria.Repository.PizzaRepository;
import br.com.sistemapizzaria.SistemaparaPizzaria.dto.PizzaDto;
import br.com.sistemapizzaria.SistemaparaPizzaria.dto.PizzaDtoComTempoEPreco;
import br.com.sistemapizzaria.SistemaparaPizzaria.model.Pizza;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.ObjectUtils;

import java.text.ParseException;
import java.util.List;
import java.util.function.Predicate;

import static br.com.sistemapizzaria.SistemaparaPizzaria.arquivosuteis.Preco.*;
import static br.com.sistemapizzaria.SistemaparaPizzaria.enums.Sabor.PORTUGUESA;
import static br.com.sistemapizzaria.SistemaparaPizzaria.enums.Tamanho.*;
import static java.lang.Boolean.FALSE;
import static java.lang.Boolean.TRUE;
import static java.lang.Double.parseDouble;

@Service
@Transactional
public class PizzaServiceImpl implements PizzaService{

    Predicate<String> validaPizzaIsNotNull = p -> !ObjectUtils.isEmpty(p);

    @Autowired
    private PizzaRepository pizzaRepository;

    @Override
    public void salvar(Pizza objeto) {
        pizzaRepository.salvar(objeto);
    }

    @Override
    public void atualizar(Pizza objeto) {
        pizzaRepository.atualizar(objeto);
    }

    @Override
    public void deletar(Long id) {
        pizzaRepository.deletar(id);
    }

    @Override
    public List<Pizza> findAll() {
        return pizzaRepository.findAll();
    }

    @Override
    public Pizza findBy(Long id) {
        return pizzaRepository.findBy(id);
    }

    @Override
    public Pizza converteDtoParaPizza(PizzaDtoComTempoEPreco pizzaDto) throws ParseException {
        final Pizza pizza = new Pizza();
        if (verificaSaborETamanha(pizzaDto)) {
            pizza.setTamanho(pizzaDto.getTamanho());
            pizza.setSabor(pizzaDto.getSabor());
            pizza.setTempoPreparo(pizzaDto.getTempoPreparo());
            pizza.setValorPizza(parseDouble(pizzaDto.getPrecoPizza()));
        }
        return pizza;
    }

    private boolean verificaSaborETamanha(PizzaDto pizzaDto) {
        return (validaPizzaIsNotNull.test(pizzaDto.getTamanho()) && validaPizzaIsNotNull.test(pizzaDto.getSabor())) ? TRUE : FALSE;
    }

    @Override
    public PizzaDtoComTempoEPreco validaTamhoESaborPizza(PizzaDto pizzaDto) {
        PizzaDtoComTempoEPreco pizzaDtoComTempoEPreco = new PizzaDtoComTempoEPreco();
        if (verificaSaborETamanha(pizzaDto)) {
            pizzaDtoComTempoEPreco.setTamanho(pizzaDto.getTamanho());
            pizzaDtoComTempoEPreco.setSabor(pizzaDto.getSabor());
            verificaSePizzaEPequena(pizzaDtoComTempoEPreco, pizzaDto);
            verificaSePizzaEMedia(pizzaDtoComTempoEPreco, pizzaDto);
            verificaSePizzaEGrande(pizzaDtoComTempoEPreco, pizzaDto);
        }
        return pizzaDtoComTempoEPreco;
    }

    private void verificaSePizzaEGrande(PizzaDtoComTempoEPreco pizzaDtoComTempoEPreco, PizzaDto pizzaDto) {
        if (pizzaDto.getTamanho().equals(GRANDE.getTamanho())) {
            pizzaDtoComTempoEPreco.setPrecoPizza(_R$_40);
            if (pizzaDto.getSabor().equals(PORTUGUESA.getSabor())) {
                pizzaDtoComTempoEPreco.setTempoPreparo("00:30");
            } else {
                pizzaDtoComTempoEPreco.setTempoPreparo("00:25");
            }
        }
    }

    private void verificaSePizzaEMedia(PizzaDtoComTempoEPreco pizzaDtoComTempoEPreco, PizzaDto pizzaDto) {
        if (pizzaDto.getTamanho().equals(MEDIA.getTamanho())) {
            pizzaDtoComTempoEPreco.setPrecoPizza(_R$_30);
            if (pizzaDto.getSabor().equals(PORTUGUESA.getSabor())) {
                pizzaDtoComTempoEPreco.setTempoPreparo("00:25");
            } else {
                pizzaDtoComTempoEPreco.setTempoPreparo("00:20");
            }
        }
    }

    private void verificaSePizzaEPequena(PizzaDtoComTempoEPreco pizzaDtoComTempoEPreco, PizzaDto pizzaDto) {
        if (pizzaDto.getTamanho().equals(PEQUENA.getTamanho())) {
            pizzaDtoComTempoEPreco.setPrecoPizza(_R$_20);
            if (pizzaDto.getSabor().equals(PORTUGUESA.getSabor())) {
                pizzaDtoComTempoEPreco.setTempoPreparo("00:20");
            } else {
                pizzaDtoComTempoEPreco.setTempoPreparo("00:15");
            }
        }
    }

}
