package org.conexaoestetika.repositories;

import jakarta.persistence.EntityManager;
import org.conexaoestetika.models.Cliente;

import java.util.List;

public class ClienteRepository implements IRepository<Cliente>{
    EntityManager em;

    public ClienteRepository(EntityManager em){
        this.em = em;
    }

    public void salvar(Cliente cliente){
        em.getTransaction().begin();
        em.persist(cliente);
        em.getTransaction().commit();
    }
    public void atualizar(Cliente cliente){
        em.getTransaction().begin();
        //em.persist(cliente);
        em.merge(cliente);
        em.getTransaction().commit();
    }

    public void delete(Cliente cliente){
        em.getTransaction().begin();
        em.remove(cliente);
        em.getTransaction().commit();
    }

    public Cliente buscarPorId(long id){
        return em.find(Cliente.class, id);
    }

    public List<Cliente> listarTodos(){
        return em.createQuery("select c from Cliente c", Cliente.class).getResultList();
    }
}
