package repository;

import jakarta.persistence.*;
import model.Produto;


import java.util.List;

public class ProdutoRepository implements IRepository<Produto>{
    EntityManager em;

    public ProdutoRepository(EntityManager em){
        this.em = em;
    }

    public void salvar(Produto Produto){
        em.getTransaction().begin();
        em.persist(Produto);
        em.getTransaction().commit();
    }
    public void atualizar(Produto produto){
        em.getTransaction().begin();
        em.merge(produto);
        em.getTransaction().commit();
    }

    public void delete(Produto produto){
        em.getTransaction().begin();
        em.remove(produto);
        em.getTransaction().commit();
    }

    public Produto buscarPorId(long id){
        return em.find(Produto.class, id);
    }

    public List<Produto> listarTodos(){
        return em.createQuery("select p from Produto p", Produto.class).getResultList();
    }
}
