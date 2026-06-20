package repositories;

import Config.HibernateConfig;
import jakarta.persistence.EntityManager;
import models.ContaPagar;

import java.util.List;

public class ContaPagarRepository {

    public void salvar(ContaPagar conta) {
        EntityManager em = HibernateConfig.getEntityManager();
        try {
            em.getTransaction().begin();
            em.persist(conta);
            em.getTransaction().commit();
        } catch (Exception e) {
            if (em.getTransaction().isActive()) {
                em.getTransaction().rollback();
            }
            throw new RuntimeException("Erro ao salvar conta a pagar!", e);
        } finally {
            em.close();
        }
    }

    public ContaPagar buscarPorId(Long id) {
        EntityManager em = HibernateConfig.getEntityManager();
        try {
            return em.find(ContaPagar.class, id);
        } finally {
            em.close();
        }
    }

    public List<ContaPagar> listarTodos() {
        EntityManager em = HibernateConfig.getEntityManager();
        try {
            return em.createQuery("SELECT c FROM ContaPagar c", ContaPagar.class).getResultList();
        } finally {
            em.close();
        }
    }

    public void atualizar(ContaPagar conta) {
        EntityManager em = HibernateConfig.getEntityManager();
        try {
            em.getTransaction().begin();
            em.merge(conta);
            em.getTransaction().commit();
        } catch (Exception e) {
            if (em.getTransaction().isActive()) {
                em.getTransaction().rollback();
            }
            throw new RuntimeException("Erro ao atualizar conta a pagar!", e);
        } finally {
            em.close();
        }
    }

    public void deletar(Long id) {
        EntityManager em = HibernateConfig.getEntityManager();
        try {
            ContaPagar conta = em.find(ContaPagar.class, id);
            if (conta != null) {
                em.getTransaction().begin();
                em.remove(conta);
                em.getTransaction().commit();
            }
        } catch (Exception e) {
            if (em.getTransaction().isActive()) {
                em.getTransaction().rollback();
            }
            throw new RuntimeException("Erro ao deletar conta a pagar!", e);
        } finally {
            em.close();
        }
    }
}