package br.com.sistemapizzaria.SistemaparaPizzaria.service;

import java.util.List;

public interface Service<T> {

    void salvar(T objeto);
    void atualizar(T objeto);
    void deletar(Long id);
    List<T> findAll();
    T findBy(Long id);
}
