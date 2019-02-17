package br.com.sistemapizzaria.SistemaparaPizzaria.Repository.Adicional;

import br.com.sistemapizzaria.SistemaparaPizzaria.model.Adicional;
import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.Restrictions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import java.util.List;


@Repository
public class AdicionalRepositoryImpl implements AdicionalRepository {

    @Autowired
    private EntityManager entityManager;

    @Override
    public void salvar(Adicional objeto) {
        entityManager.persist(objeto);
    }

    @Override
    public void deletar(Long id) {
        entityManager.remove(entityManager.getReference(Adicional.class, id));
    }

    @Override
    public void atualizar(Adicional objeto) {
        entityManager.merge(objeto);
    }

    @Override
    public List<Adicional> findAll() {
        return entityManager.createQuery("select a from Adicional a", Adicional.class).getResultList();
    }

    @Override
    public Adicional findBy(Long id) {
        return entityManager.createQuery("select a from Adicional a where a.id = :id", Adicional.class)
                .setParameter("id", id).getSingleResult();
    }
}
