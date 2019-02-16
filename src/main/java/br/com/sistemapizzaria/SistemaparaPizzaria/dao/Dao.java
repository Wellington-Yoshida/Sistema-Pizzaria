package br.com.sistemapizzaria.SistemaparaPizzaria.dao;

import java.util.List;

public interface Dao<T> {

    void salvar(T objeto);
    void deletar(Long id);
    void atualizar(T objeto);
    List<T> findAll();
    T findBy(Long id);
}
