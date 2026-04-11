package repositories;

import Config.HibernateConfig;
import jakarta.persistence.EntityManager;
import jakarta.persistence.TypedQuery;
import models.Caixa;
import models.enums.StatusCaixa;

import java.util.List;

public class CaixaRepository {

    public void salvar(Caixa caixa) {
        EntityManager em = HibernateConfig.getEntityManager();
        try {
            em.getTransaction().begin();
            em.persist(caixa);
            em.getTransaction().commit();
        } catch (Exception e) {
            if (em.getTransaction().isActive()) {
                em.getTransaction().rollback();
            }
            throw new RuntimeException("Erro ao salvar caixa!", e);
        } finally {
            em.close();
        }
    }

    public Caixa buscarPorId(Long id) {
        EntityManager em = HibernateConfig.getEntityManager();
        try {
            return em.find(Caixa.class, id);
        } finally {
            em.close();
        }
    }

    public List<Caixa> listarTodos() {
        EntityManager em = HibernateConfig.getEntityManager();
        try {
            return em.createQuery("SELECT c FROM Caixa c", Caixa.class).getResultList();
        } finally {
            em.close();
        }
    }

    public void atualizar(Caixa caixa) {
        EntityManager em = HibernateConfig.getEntityManager();
        try {
            em.getTransaction().begin();
            em.merge(caixa);
            em.getTransaction().commit();
        } catch (Exception e) {
            if (em.getTransaction().isActive()) {
                em.getTransaction().rollback();
            }
            throw new RuntimeException("Erro ao atualizar caixa!", e);
        } finally {
            em.close();
        }
    }

    public void deletar(Long id) {
        EntityManager em = HibernateConfig.getEntityManager();
        try {
            Caixa caixa = em.find(Caixa.class, id);
            if (caixa != null) {
                em.getTransaction().begin();
                em.remove(caixa);
                em.getTransaction().commit();
            }
        } catch (Exception e) {
            if (em.getTransaction().isActive()) {
                em.getTransaction().rollback();
            }
            throw new RuntimeException("Erro ao deletar caixa!", e);
        } finally {
            em.close();
        }
    }

    public Caixa buscarCaixaAberto() {
        EntityManager em = HibernateConfig.getEntityManager();
        try {
            TypedQuery<Caixa> query = em.createQuery(
                    "SELECT c FROM Caixa c WHERE c.status = :status",
                    Caixa.class
            );
            query.setParameter("status", StatusCaixa.ABERTO);
            query.setMaxResults(1);

            List<Caixa> resultado = query.getResultList();
            return resultado.isEmpty() ? null : resultado.get(0);
        } finally {
            em.close();
        }
    }
}