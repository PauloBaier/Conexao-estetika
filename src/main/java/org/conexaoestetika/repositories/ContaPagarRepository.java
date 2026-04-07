package org.conexaoestetika.repositories;

import jakarta.persistence.EntityManager;
import org.conexaoestetika.models.Cliente;
import org.conexaoestetika.models.ContaPagar;

import java.util.List;

public class ContaPagarRepository implements IRepository<ContaPagar>{
    EntityManager em;

    public ContaPagarRepository(EntityManager em){
        this.em = em;
    }

    public void salvar(ContaPagar contaPagar){
        em.getTransaction().begin();
        em.persist(contaPagar);
        em.getTransaction().commit();
    }
    public void atualizar(ContaPagar contaPagar){
        em.getTransaction().begin();
        em.merge(contaPagar);
        em.getTransaction().commit();
    }

    public void delete(ContaPagar contaPagar){
        em.getTransaction().begin();
        em.remove(contaPagar);
        em.getTransaction().commit();
    }

    public ContaPagar buscarPorId(long id){
        return em.find(ContaPagar.class, id);
    }

    public List<ContaPagar> listarTodos(){
        return em.createQuery("select cp from ContaPagar cp", ContaPagar.class).getResultList();
    }
}
