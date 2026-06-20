package repositories;

import Config.HibernateConfig;
import jakarta.persistence.EntityManager;
import models.ContaReceber;

import java.util.List;

public class ContaReceberRepository {

    public void salvar(ContaReceber conta) {
        EntityManager em = HibernateConfig.getEntityManager();
        try {
            em.getTransaction().begin();
            em.persist(conta);
            em.getTransaction().commit();
        } catch (Exception e) {
            if (em.getTransaction().isActive()) {
                em.getTransaction().rollback();
            }
            throw new RuntimeException("Erro ao salvar conta a receber!", e);
        } finally {
            em.close();
        }
    }

    public ContaReceber buscarPorId(Long id) {
        EntityManager em = HibernateConfig.getEntityManager();
        try {
            return em.find(ContaReceber.class, id);
        } finally {
            em.close();
        }
    }

    public List<ContaReceber> listarTodos() {
        EntityManager em = HibernateConfig.getEntityManager();
        try {
            return em.createQuery("SELECT c FROM ContaReceber c", ContaReceber.class).getResultList();
        } finally {
            em.close();
        }
    }

    public void atualizar(ContaReceber conta) {
        EntityManager em = HibernateConfig.getEntityManager();
        try {
            em.getTransaction().begin();
            em.merge(conta);
            em.getTransaction().commit();
        } catch (Exception e) {
            if (em.getTransaction().isActive()) {
                em.getTransaction().rollback();
            }
            throw new RuntimeException("Erro ao atualizar conta a receber!", e);
        } finally {
            em.close();
        }
    }

    public void deletar(Long id) {
        EntityManager em = HibernateConfig.getEntityManager();
        try {
            ContaReceber conta = em.find(ContaReceber.class, id);
            if (conta != null) {
                em.getTransaction().begin();
                em.remove(conta);
                em.getTransaction().commit();
            }
        } catch (Exception e) {
            if (em.getTransaction().isActive()) {
                em.getTransaction().rollback();
            }
            throw new RuntimeException("Erro ao deletar conta a receber!", e);
        } finally {
            em.close();
        }
    }
}