package repository;

import Config.HibernateConfig;
import jakarta.persistence.EntityManager;
import model.ContaPagar;

import java.util.List;

public class ContaPagarRepository {

    public void salvar(ContaPagar conta) {
        EntityManager em = HibernateConfig.getEntityManager();
        try {
            em.getTransaction().begin();
            em.persist(conta);
            em.getTransaction().commit();
        } catch (Exception e) {
            em.getTransaction().rollback();
            throw new RuntimeException("Erro ao salvar conta pagar", e);
        } finally {
            em.close();
        }
    }

    public ContaPagar buscarPorId(Long id) {
        EntityManager em = HibernateConfig.getEntityManager();
        ContaPagar conta = em.find(ContaPagar.class, id);
        em.close();
        return conta;
    }

    public List<ContaPagar> listarTodos() {
        EntityManager em = HibernateConfig.getEntityManager();
        List<ContaPagar> lista = em.createQuery("SELECT c FROM ContaPagar c", ContaPagar.class).getResultList();
        em.close();
        return lista;
    }

    public void atualizar(ContaPagar conta) {
        EntityManager em = HibernateConfig.getEntityManager();
        try {
            em.getTransaction().begin();
            em.merge(conta);
            em.getTransaction().commit();
        } catch (Exception e) {
            em.getTransaction().rollback();
            throw new RuntimeException("Erro ao atualizar conta pagar", e);
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
            em.getTransaction().rollback();
            throw new RuntimeException("Erro ao deletar conta pagar", e);
        } finally {
            em.close();
        }
    }
}
