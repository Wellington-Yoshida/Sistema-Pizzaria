package br.com.sistemapizzaria.SistemaparaPizzaria.service;

import br.com.sistemapizzaria.SistemaparaPizzaria.Repository.PizzaRepository;
import br.com.sistemapizzaria.SistemaparaPizzaria.model.Pizza;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PizzaServiceImpl implements PizzaService{

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
}
