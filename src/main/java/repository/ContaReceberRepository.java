package repository;

import Config.HibernateConfig;
import jakarta.persistence.EntityManager;
import model.ContaReceber;
import java.util.List;

public class ContaReceberRepository {

    public void salvar(ContaReceber conta) {
        EntityManager em = HibernateConfig.getEntityManager();
        try {
            em.getTransaction().begin();
            em.persist(conta);
            em.getTransaction().commit();
        } catch (Exception e) {
            em.getTransaction().rollback();
            throw new RuntimeException("Erro ao salvar conta receber", e);
        } finally {
            em.close();
        }
    }

    public ContaReceber buscarPorId(Long id) {
        EntityManager em = HibernateConfig.getEntityManager();
        ContaReceber conta = em.find(ContaReceber.class, id);
        em.close();
        return conta;
    }

    public List<ContaReceber> listarTodos() {
        EntityManager em = HibernateConfig.getEntityManager();
        List<ContaReceber> lista = em.createQuery("SELECT c FROM ContaReceber c", ContaReceber.class).getResultList();
        em.close();
        return lista;
    }

    public void atualizar(ContaReceber conta) {
        EntityManager em = HibernateConfig.getEntityManager();
        try {
            em.getTransaction().begin();
            em.merge(conta);
            em.getTransaction().commit();
        } catch (Exception e) {
            em.getTransaction().rollback();
            throw new RuntimeException("Erro ao atualizar conta receber", e);
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
            em.getTransaction().rollback();
            throw new RuntimeException("Erro ao deletar conta receber", e);
        } finally {
            em.close();
        }
    }

}
