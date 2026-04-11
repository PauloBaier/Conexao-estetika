package repositories;

import Config.HibernateConfig;
import jakarta.persistence.EntityManager;
import models.Categoria;

import java.util.List;

public class CategoriaRepository {

    public void salvar(Categoria categoria) {
        EntityManager em = HibernateConfig.getEntityManager();
        try {
            em.getTransaction().begin();
            em.persist(categoria);
            em.getTransaction().commit();
        } catch (Exception e) {
            if (em.getTransaction().isActive()) {
                em.getTransaction().rollback();
            }
            throw new RuntimeException("Erro ao salvar categoria!", e);
        } finally {
            em.close();
        }
    }

    public Categoria buscarPorId(Long id) {
        EntityManager em = HibernateConfig.getEntityManager();
        try {
            return em.find(Categoria.class, id);
        } finally {
            em.close();
        }
    }

    public List<Categoria> listarTodos() {
        EntityManager em = HibernateConfig.getEntityManager();
        try {
            return em.createQuery("SELECT c FROM Categoria c", Categoria.class).getResultList();
        } finally {
            em.close();
        }
    }

    public void atualizar(Categoria categoria) {
        EntityManager em = HibernateConfig.getEntityManager();
        try {
            em.getTransaction().begin();
            em.merge(categoria);
            em.getTransaction().commit();
        } catch (Exception e) {
            if (em.getTransaction().isActive()) {
                em.getTransaction().rollback();
            }
            throw new RuntimeException("Erro ao atualizar categoria!", e);
        } finally {
            em.close();
        }
    }

    public void deletar(Long id) {
        EntityManager em = HibernateConfig.getEntityManager();
        try {
            Categoria categoria = em.find(Categoria.class, id);
            if (categoria != null) {
                em.getTransaction().begin();
                em.remove(categoria);
                em.getTransaction().commit();
            }
        } catch (Exception e) {
            if (em.getTransaction().isActive()) {
                em.getTransaction().rollback();
            }
            throw new RuntimeException("Erro ao deletar categoria!", e);
        } finally {
            em.close();
        }
    }
}