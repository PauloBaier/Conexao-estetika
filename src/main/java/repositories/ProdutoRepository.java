package repositories;

import Config.HibernateConfig;
import jakarta.persistence.EntityManager;
import models.Produto;

import java.util.List;

public class ProdutoRepository {

    public void salvar(Produto produto) {
        EntityManager em = HibernateConfig.getEntityManager();
        try {
            em.getTransaction().begin();
            em.persist(produto);
            em.getTransaction().commit();
        } catch (Exception e) {
            if (em.getTransaction().isActive()) {
                em.getTransaction().rollback();
            }
            throw new RuntimeException("Erro ao salvar produto!", e);
        } finally {
            em.close();
        }
    }

    public void atualizar(Produto produto) {
        EntityManager em = HibernateConfig.getEntityManager();
        try {
            em.getTransaction().begin();
            em.merge(produto);
            em.getTransaction().commit();
        } catch (Exception e) {
            if (em.getTransaction().isActive()) {
                em.getTransaction().rollback();
            }
            throw new RuntimeException("Erro ao atualizar produto!", e);
        } finally {
            em.close();
        }
    }

    public void delete(Produto produto) {
        EntityManager em = HibernateConfig.getEntityManager();
        try {
            em.getTransaction().begin();
            Produto produtoGerenciado = em.find(Produto.class, produto.getId());
            if (produtoGerenciado != null) {
                em.remove(produtoGerenciado);
            }
            em.getTransaction().commit();
        } catch (Exception e) {
            if (em.getTransaction().isActive()) {
                em.getTransaction().rollback();
            }
            throw new RuntimeException("Erro ao deletar produto!", e);
        } finally {
            em.close();
        }
    }

    public Produto buscarPorId(long id) {
        EntityManager em = HibernateConfig.getEntityManager();
        try {
            return em.find(Produto.class, id);
        } finally {
            em.close();
        }
    }

    public List<Produto> listarTodos() {
        EntityManager em = HibernateConfig.getEntityManager();
        try {
            return em.createQuery("SELECT p FROM Produto p", Produto.class).getResultList();
        } finally {
            em.close();
        }
    }
}