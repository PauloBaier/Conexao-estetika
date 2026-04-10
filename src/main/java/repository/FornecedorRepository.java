package repository;

import jakarta.persistence.*;
import model.Fornecedor;

import java.util.List;

public class FornecedorRepository implements IRepository<Fornecedor>{
    EntityManager em;

    public FornecedorRepository(EntityManager em){
        this.em = em;
    }

    public void salvar(Fornecedor fornecedor){
        em.getTransaction().begin();
        em.persist(fornecedor);
        em.getTransaction().commit();
    }
    public void atualizar(Fornecedor fornecedor){
        em.getTransaction().begin();
        em.merge(fornecedor);
        em.getTransaction().commit();
    }

    public void delete(Fornecedor fornecedor){
        em.getTransaction().begin();
        em.remove(fornecedor);
        em.getTransaction().commit();
    }

    public Fornecedor buscarPorId(long id){
        return em.find(Fornecedor.class, id);
    }

    public List<Fornecedor> listarTodos(){
        return em.createQuery("select f from Fornecedor f", Fornecedor.class).getResultList();
    }
}
