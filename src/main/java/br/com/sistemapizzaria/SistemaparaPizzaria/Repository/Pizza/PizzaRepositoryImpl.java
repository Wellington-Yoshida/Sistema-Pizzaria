package br.com.sistemapizzaria.SistemaparaPizzaria.Repository.Pizza;

import br.com.sistemapizzaria.SistemaparaPizzaria.model.Pizza;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import java.util.List;

@Repository
public class PizzaRepositoryImpl implements PizzaRepository {

    @Autowired
    private EntityManager entityManager;

    @Override
    public void salvar(Pizza objeto) {
        entityManager.persist(objeto);
    }

    @Override
    public void deletar(Long id) {
        entityManager.remove(entityManager.getReference(Pizza.class, id));
    }

    @Override
    public void atualizar(Pizza objeto) {
        entityManager.merge(objeto);
    }

    @Override
    public List<Pizza> findAll() {
        return entityManager.createQuery("select p from Pizza p", Pizza.class).getResultList();
    }

    @Override
    public Pizza findBy(Long id) {
        return entityManager.createQuery("select p from Pizza p where p.id = :id", Pizza.class)
                .setParameter("id", id).getSingleResult();
    }
}
