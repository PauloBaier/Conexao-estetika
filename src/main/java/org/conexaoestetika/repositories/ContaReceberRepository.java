package org.conexaoestetika.repositories;

import jakarta.persistence.EntityManager;
import org.conexaoestetika.models.Cliente;
import org.conexaoestetika.models.ContaPagar;
import org.conexaoestetika.models.ContaReceber;

import java.util.List;

public class ContaReceberRepository implements IRepository<ContaReceber>{
    EntityManager em;

    public ContaReceberRepository(EntityManager em){
        this.em = em;
    }

    public void salvar(ContaReceber contaReceber){
        em.getTransaction().begin();
        em.persist(contaReceber);
        em.getTransaction().commit();
    }
    public void atualizar(ContaReceber contaReceber){
        em.getTransaction().begin();
        em.merge(contaReceber);
        em.getTransaction().commit();
    }

    public void delete(ContaReceber contaReceber){
        em.getTransaction().begin();
        em.remove(contaReceber);
        em.getTransaction().commit();
    }

    public ContaReceber buscarPorId(long id){
        return em.find(ContaReceber.class, id);
    }

    public List<ContaReceber> listarTodos(){
        return em.createQuery("select cr from ContaReceber cr", ContaReceber.class).getResultList();
    }
}
