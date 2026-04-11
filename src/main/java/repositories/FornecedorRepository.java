package repositories;

import Config.HibernateConfig;
import jakarta.persistence.EntityManager;
import models.Fornecedor;

import java.util.List;

public class FornecedorRepository {

    public void salvar(Fornecedor fornecedor) {
        EntityManager em = HibernateConfig.getEntityManager();
        try {
            em.getTransaction().begin();
            em.persist(fornecedor);
            em.getTransaction().commit();
        } catch (Exception e) {
            if (em.getTransaction().isActive()) {
                em.getTransaction().rollback();
            }
            throw new RuntimeException("Erro ao salvar fornecedor!", e);
        } finally {
            em.close();
        }
    }

    public void atualizar(Fornecedor fornecedor) {
        EntityManager em = HibernateConfig.getEntityManager();
        try {
            em.getTransaction().begin();
            em.merge(fornecedor);
            em.getTransaction().commit();
        } catch (Exception e) {
            if (em.getTransaction().isActive()) {
                em.getTransaction().rollback();
            }
            throw new RuntimeException("Erro ao atualizar fornecedor!", e);
        } finally {
            em.close();
        }
    }

    public void delete(Fornecedor fornecedor) {
        EntityManager em = HibernateConfig.getEntityManager();
        try {
            em.getTransaction().begin();
            Fornecedor fornecedorGerenciado = em.find(Fornecedor.class, fornecedor.getId());
            if (fornecedorGerenciado != null) {
                em.remove(fornecedorGerenciado);
            }
            em.getTransaction().commit();
        } catch (Exception e) {
            if (em.getTransaction().isActive()) {
                em.getTransaction().rollback();
            }
            throw new RuntimeException("Erro ao deletar fornecedor!", e);
        } finally {
            em.close();
        }
    }

    public Fornecedor buscarPorId(long id) {
        EntityManager em = HibernateConfig.getEntityManager();
        try {
            return em.find(Fornecedor.class, id);
        } finally {
            em.close();
        }
    }

    public List<Fornecedor> listarTodos() {
        EntityManager em = HibernateConfig.getEntityManager();
        try {
            return em.createQuery("SELECT f FROM Fornecedor f", Fornecedor.class).getResultList();
        } finally {
            em.close();
        }
    }
}