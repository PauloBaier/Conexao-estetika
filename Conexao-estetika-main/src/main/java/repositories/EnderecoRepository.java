package repositories;

import Config.HibernateConfig;
import jakarta.persistence.EntityManager;
import models.Endereco;

import java.util.List;

public class EnderecoRepository {

    public void salvar(Endereco endereco) {
        EntityManager em = HibernateConfig.getEntityManager();
        try {
            em.getTransaction().begin();
            em.persist(endereco);
            em.getTransaction().commit();
        } catch (Exception e) {
            if (em.getTransaction().isActive()) {
                em.getTransaction().rollback();
            }
            throw new RuntimeException("Erro ao salvar endereço!", e);
        } finally {
            em.close();
        }
    }

    public Endereco buscarPorId(Long id) {
        EntityManager em = HibernateConfig.getEntityManager();
        try {
            return em.find(Endereco.class, id);
        } finally {
            em.close();
        }
    }

    public List<Endereco> listar() {
        EntityManager em = HibernateConfig.getEntityManager();
        try {
            return em.createQuery("FROM Endereco", Endereco.class).getResultList();
        } finally {
            em.close();
        }
    }

    public void atualizar(Endereco endereco) {
        EntityManager em = HibernateConfig.getEntityManager();
        try {
            em.getTransaction().begin();
            em.merge(endereco);
            em.getTransaction().commit();
        } catch (Exception e) {
            if (em.getTransaction().isActive()) {
                em.getTransaction().rollback();
            }
            throw new RuntimeException("Erro ao atualizar endereço!", e);
        } finally {
            em.close();
        }
    }

    public void deletar(Long id) {
        EntityManager em = HibernateConfig.getEntityManager();
        try {
            em.getTransaction().begin();
            Endereco endereco = em.find(Endereco.class, id);
            if (endereco != null) {
                em.remove(endereco);
            }
            em.getTransaction().commit();
        } catch (Exception e) {
            if (em.getTransaction().isActive()) {
                em.getTransaction().rollback();
            }
            throw new RuntimeException("Erro ao deletar endereço!", e);
        } finally {
            em.close();
        }
    }
}