package org.conexaoestetika.repositories;

import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

import java.util.List;

public class CustomizerFactory {
    private static final EntityManagerFactory emf;

    static{
        SessionFactory sessionFactory = new Configuration()
                .configure("hibernate.cfg.xml")
                .buildSessionFactory();

        emf = sessionFactory.unwrap(EntityManagerFactory.class);
    }

    public static EntityManager getEntityManager(){
        return emf.createEntityManager();
    }

    public static void fechar(){
        emf.close();
    }

    public static class RelatorioRepository {

        public void salvar(Relatorio relatorio) {
            EntityManager em = HibernateConfig.getEntityManager();
            try {
                em.getTransaction().begin();
                em.persist(relatorio);
                em.getTransaction().commit();
            } catch (Exception e) {
                em.getTransaction().rollback();
                throw e;
            } finally {
                em.close();
            }
        }

        public List<Relatorio> listar() {
            EntityManager em = HibernateConfig.getEntityManager();
            try {
                return em.createQuery("from Relatorio", Relatorio.class).getResultList();
            } finally {
                em.close();
            }
        }

        public Relatorio buscarPorId(Long id) {
            EntityManager em = HibernateConfig.getEntityManager();
            try {
                return em.find(Relatorio.class, id);
            } finally {
                em.close();
            }
        }

        public void deletar(Long id) {
            EntityManager em = HibernateConfig.getEntityManager();
            try {
                em.getTransaction().begin();
                Relatorio relatorio = em.find(Relatorio.class, id);
                if (relatorio != null) {
                    em.remove(relatorio);
                }
                em.getTransaction().commit();
            } catch (Exception e) {
                em.getTransaction().rollback();
                throw e;
            } finally {
                em.close();
            }
        }
    }
}
