package repositories;

import Config.HibernateConfig;
import jakarta.persistence.EntityManager;
import models.ItemVenda;

import java.util.List;

public class ItemVendaRepository {

    public void salvar(ItemVenda item) {
        EntityManager em = HibernateConfig.getEntityManager();
        try {
            em.getTransaction().begin();
            em.persist(item);
            em.getTransaction().commit();
        } catch (Exception e) {
            if (em.getTransaction().isActive()) {
                em.getTransaction().rollback();
            }

            e.printStackTrace(); // mostra o erro real no console

            Throwable causa = e;
            while (causa.getCause() != null) {
                causa = causa.getCause();
            }

            throw new RuntimeException("Erro ao salvar item de venda! Causa real: " + causa.getMessage(), e);
        } finally {
            em.close();
        }
    }

    public ItemVenda buscarPorId(Long id) {
        EntityManager em = HibernateConfig.getEntityManager();
        try {
            return em.find(ItemVenda.class, id);
        } finally {
            em.close();
        }
    }

    public List<ItemVenda> listarTodos() {
        EntityManager em = HibernateConfig.getEntityManager();
        try {
            return em.createQuery("SELECT i FROM ItemVenda i", ItemVenda.class).getResultList();
        } finally {
            em.close();
        }
    }

    public void atualizar(ItemVenda item) {
        EntityManager em = HibernateConfig.getEntityManager();
        try {
            em.getTransaction().begin();
            em.merge(item);
            em.getTransaction().commit();
        } catch (Exception e) {
            if (em.getTransaction().isActive()) {
                em.getTransaction().rollback();
            }
            throw new RuntimeException("Erro ao atualizar item de venda!", e);
        } finally {
            em.close();
        }
    }

    public void deletar(Long id) {
        EntityManager em = HibernateConfig.getEntityManager();
        try {
            ItemVenda item = em.find(ItemVenda.class, id);
            if (item != null) {
                em.getTransaction().begin();
                em.remove(item);
                em.getTransaction().commit();
            }
        } catch (Exception e) {
            if (em.getTransaction().isActive()) {
                em.getTransaction().rollback();
            }
            throw new RuntimeException("Erro ao deletar item de venda!", e);
        } finally {
            em.close();
        }
    }

    public List<ItemVenda> buscarPorVenda(Long vendaId) {
        EntityManager em = HibernateConfig.getEntityManager();
        try {
            return em.createQuery(
                            "SELECT i FROM ItemVenda i WHERE i.venda.id = :vendaId",
                            ItemVenda.class
                    ).setParameter("vendaId", vendaId)
                    .getResultList();
        } finally {
            em.close();
        }
    }
}