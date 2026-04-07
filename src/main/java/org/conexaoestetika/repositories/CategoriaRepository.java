package org.conexaoestetika.repositories;

import jakarta.persistence.EntityManager;

import java.util.List;

public class CategoriaRepository implements IRepository<Categoria>{
    EntityManager em;

    public CategoriaRepository(EntityManager em){
        this.em = em;
    }

    public void salvar(Categoria categoria){
        em.getTransaction().begin();
        em.persist(categoria);
        em.getTransaction().commit();
    }
    public void atualizar(Categoria categoria){
        em.getTransaction().begin();
        em.merge(categoria);
        em.getTransaction().commit();
    }

    public void delete(Categoria categoria){
        em.getTransaction().begin();
        em.remove(categoria);
        em.getTransaction().commit();
    }

    public Categoria buscarPorId(long id){
        return em.find(Categoria.class, id);
    }

    public List<Categoria> listarTodos(){
        return em.createQuery("select c from Categoria c", Categoria.class).getResultList();
    }
}
