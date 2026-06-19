package repositories;

import Config.HibernateConfig;
import jakarta.persistence.EntityManager;
import models.MovimentacaoCaixa;

import java.util.List;

public class MovimentacaoCaixaRepository {

    public void salvar(MovimentacaoCaixa mov) {
        EntityManager em = HibernateConfig.getEntityManager();
        try {
            em.getTransaction().begin();
            em.persist(mov);
            em.getTransaction().commit();
        } catch (Exception e) {
            if (em.getTransaction().isActive()) {
                em.getTransaction().rollback();
            }
            throw new RuntimeException("Erro ao salvar movimentação!", e);
        } finally {
            em.close();
        }
    }

    public MovimentacaoCaixa buscarPorId(Long id) {
        EntityManager em = HibernateConfig.getEntityManager();
        try {
            return em.find(MovimentacaoCaixa.class, id);
        } finally {
            em.close();
        }
    }

    public List<MovimentacaoCaixa> listarTodos() {
        EntityManager em = HibernateConfig.getEntityManager();
        try {
            return em.createQuery(
                    "SELECT m FROM MovimentacaoCaixa m",
                    MovimentacaoCaixa.class
            ).getResultList();
        } finally {
            em.close();
        }
    }

    public void atualizar(MovimentacaoCaixa mov) {
        EntityManager em = HibernateConfig.getEntityManager();
        try {
            em.getTransaction().begin();
            em.merge(mov);
            em.getTransaction().commit();
        } catch (Exception e) {
            if (em.getTransaction().isActive()) {
                em.getTransaction().rollback();
            }
            throw new RuntimeException("Erro ao atualizar movimentação!", e);
        } finally {
            em.close();
        }
    }

    public void deletar(Long id) {
        EntityManager em = HibernateConfig.getEntityManager();
        try {
            MovimentacaoCaixa mov = em.find(MovimentacaoCaixa.class, id);
            if (mov != null) {
                em.getTransaction().begin();
                em.remove(mov);
                em.getTransaction().commit();
            }
        } catch (Exception e) {
            if (em.getTransaction().isActive()) {
                em.getTransaction().rollback();
            }
            throw new RuntimeException("Erro ao deletar movimentação!", e);
        } finally {
            em.close();
        }
    }
}