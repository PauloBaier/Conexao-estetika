package repository;

import Config.HibernateConfig;
import model.Categoria;
import jakarta.persistence.*;

import java.util.List;

public class CategoriaRepository {

    public void salvar(Categoria categoria){
        EntityManager em = HibernateConfig.getEntityManager();
        try {
            em.getTransaction().begin();
            em.persist(categoria);
            em.getTransaction().commit();
        }catch (Exception e){
            em.getTransaction().rollback();
            throw new RuntimeException("Erro ao salvar Categoria!",e);
        } finally {
            em.close();
        }
    }
    public Categoria buscarPorId(Long id){
        EntityManager em = HibernateConfig.getEntityManager();
            Categoria categoria = em.find(Categoria.class,id);
        em.close();
        return categoria;
    }

    public List<Categoria> listarTodos(){
        EntityManager em = HibernateConfig.getEntityManager();
        List<Categoria> lista = em.createQuery("SELECT c FROM Categoria c", Categoria.class).getResultList();
        em.close();
        return lista;
    }

    public void atualizar(Categoria categoria){
        EntityManager em = HibernateConfig.getEntityManager();
        try {
            em.getTransaction().begin();
            em.merge(categoria);
            em.getTransaction().commit();
        } catch (Exception e){
            em.getTransaction().rollback();
            throw new RuntimeException("Erro ao atualizar Categoria!",e);
        } finally {
            em.close();
        }
    }

    public void deletar(Long id){
        EntityManager em = HibernateConfig.getEntityManager();
        try {
            Categoria categoria = em.find(Categoria.class,id);
            if(categoria!=null){
                em.getTransaction().begin();
                em.remove(categoria);
                em.getTransaction().commit();
            }
        } catch (Exception e){
            em.getTransaction().rollback();
            throw new RuntimeException("Erro ao deletar Categoria!",e);
        }finally {
            em.close();
        }
    }
}
