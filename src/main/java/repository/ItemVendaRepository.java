package repository;

import Config.HibernateConfig;
import jakarta.persistence.*;
import model.ItemVenda;

import java.util.List;

public class ItemVendaRepository {

    public void salvar(ItemVenda item) {
        EntityManager em = HibernateConfig.getEntityManager();
        try {
            em.getTransaction().begin();
            em.persist(item);
            em.getTransaction().commit();
        } catch (Exception e) {
            em.getTransaction().rollback();
            throw new RuntimeException("Erro ao salvar item de venda", e);
        } finally {
            em.close();
        }
    }

    public ItemVenda buscarPorId(Long id) {
        EntityManager em = HibernateConfig.getEntityManager();
        ItemVenda item = em.find(ItemVenda.class, id);
        em.close();
        return item;
    }

    public List<ItemVenda> listarTodos() {
        EntityManager em = HibernateConfig.getEntityManager();
        List<ItemVenda> lista = em.createQuery("SELECT i FROM ItemVenda i", ItemVenda.class).getResultList();
        em.close();
        return lista;
    }

    public void atualizar(ItemVenda item) {
        EntityManager em = HibernateConfig.getEntityManager();
        try {
            em.getTransaction().begin();
            em.merge(item);
            em.getTransaction().commit();
        } catch (Exception e) {
            em.getTransaction().rollback();
            throw new RuntimeException("Erro ao atualizar item de venda", e);
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
            em.getTransaction().rollback();
            throw new RuntimeException("Erro ao deletar item de venda", e);
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
                    )
                    .setParameter("vendaId", vendaId)
                    .getResultList();

        } finally {
            em.close();
        }
    }
}