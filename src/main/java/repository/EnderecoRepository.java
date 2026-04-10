package repository;

import Config.HibernateConfig;
import jakarta.persistence.*;
import model.Endereco;

import java.util.List;

public class EnderecoRepository {

    public void salvar(Endereco endereco) {
        EntityManager em = HibernateConfig.getEntityManager();
        em.getTransaction().begin();
        em.persist(endereco);
        em.getTransaction().commit();
        em.close();
    }

    public List<Endereco> listar() {
        EntityManager em = HibernateConfig.getEntityManager();
        List<Endereco> lista = em
                .createQuery("FROM Endereco", Endereco.class)
                .getResultList();
        em.close();
        return lista;
    }

    public void atualizar(Endereco endereco) {
        EntityManager em = HibernateConfig.getEntityManager();
        em.getTransaction().begin();
        em.merge(endereco);
        em.getTransaction().commit();
        em.close();
    }

    public void deletar(Long id) {
        EntityManager em = HibernateConfig.getEntityManager();
        em.getTransaction().begin();
        Endereco e = em.find(Endereco.class, id);
        if (e != null) {
            em.remove(e);
        }
        em.getTransaction().commit();
        em.close();
    }
}