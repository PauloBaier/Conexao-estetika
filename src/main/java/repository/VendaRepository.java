package repository;

import Config.HibernateConfig;
import jakarta.persistence.*;
import model.Venda;

import java.util.List;

public class VendaRepository {

    public void salvar(Venda venda) {
        EntityManager em = HibernateConfig.getEntityManager();
        try {
            em.getTransaction().begin();
            em.persist(venda);
            em.getTransaction().commit();
        } catch (Exception e) {
            em.getTransaction().rollback();
            throw new RuntimeException("Erro ao salvar venda", e);
        } finally {
            em.close();
        }
    }

    public Venda buscarPorId(Long id) {
        EntityManager em = HibernateConfig.getEntityManager();
        Venda venda = em.find(Venda.class, id);
        em.close();
        return venda;
    }

    public List<Venda> listarTodos() {
        EntityManager em = HibernateConfig.getEntityManager();
        List<Venda> lista = em.createQuery("SELECT v FROM Venda v", Venda.class).getResultList();
        em.close();
        return lista;
    }

    public void atualizar(Venda venda) {
        EntityManager em = HibernateConfig.getEntityManager();
        try {
            em.getTransaction().begin();
            em.merge(venda);
            em.getTransaction().commit();
        } catch (Exception e) {
            em.getTransaction().rollback();
            throw new RuntimeException("Erro ao atualizar venda", e);
        } finally {
            em.close();
        }
    }

    public void deletar(Long id) {
        EntityManager em = HibernateConfig.getEntityManager();
        try {
            Venda venda = em.find(Venda.class, id);
            if (venda != null) {
                em.getTransaction().begin();
                em.remove(venda);
                em.getTransaction().commit();
            }
        } catch (Exception e) {
            em.getTransaction().rollback();
            throw new RuntimeException("Erro ao deletar venda", e);
        } finally {
            em.close();
        }
    }
}