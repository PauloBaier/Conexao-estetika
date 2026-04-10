package repository;

import Config.HibernateConfig;
import jakarta.persistence.*;
import model.Cliente;

import java.util.List;

public class ClienteRepository {

    public void salvar(Cliente cliente) {
        EntityManager em = HibernateConfig.getEntityManager();
        em.getTransaction().begin();
        em.persist(cliente);
        em.getTransaction().commit();
        em.close();
    }

    public Cliente buscarPorId(Long id) {
        EntityManager em = HibernateConfig.getEntityManager();
        Cliente c = em.find(Cliente.class, id);
        em.close();
        return c;
    }

    public List<Cliente> listar() {
        EntityManager em = HibernateConfig.getEntityManager();
        List<Cliente> lista = em.createQuery("FROM Cliente", Cliente.class).getResultList();
        em.close();
        return lista;
    }

    public void atualizar(Cliente cliente) {
        EntityManager em = HibernateConfig.getEntityManager();
        em.getTransaction().begin();
        em.merge(cliente);
        em.getTransaction().commit();
        em.close();
    }
    public void deletar(Long id) {
        EntityManager em = HibernateConfig.getEntityManager();
        try {
            em.getTransaction().begin();
            // Busca o cliente pelo ID
            Cliente cliente = em.find(Cliente.class, id);
            if (cliente != null) {
                em.remove(cliente);
            }
            em.getTransaction().commit();
        } catch (Exception e) {
            if (em.getTransaction().isActive()) {
                em.getTransaction().rollback();
            }
            throw new RuntimeException("Erro ao deletar: " + e.getMessage());
        } finally {
            em.close();
        }
    }
}