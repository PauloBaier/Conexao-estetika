package repository;

import Config.HibernateConfig;
import jakarta.persistence.EntityManager;
import model.Caixa;

import java.util.List;

public class CaixaRepository {

    public void salvar(Caixa caixa){
        EntityManager em = HibernateConfig.getEntityManager();
        try {
            em.getTransaction().begin();
            em.persist(caixa);
            em.getTransaction().commit();
        }catch (Exception e){
            em.getTransaction().rollback();
            throw new RuntimeException("Erro ao salvar Caixa!",e);
        } finally {
            em.close();
        }
    }
    public Caixa buscarPorId(Long id){
        EntityManager em = HibernateConfig.getEntityManager();
        Caixa caixa = em.find(Caixa.class,id);
        em.close();
        return caixa;
    }

    public List<Caixa> listarTodos(){
        EntityManager em = HibernateConfig.getEntityManager();
        List<Caixa> lista = em.createQuery("SELECT c FROM Caixa c", Caixa.class).getResultList();
        em.close();
        return lista;
    }

    public void atualizar(Caixa caixa){
        EntityManager em = HibernateConfig.getEntityManager();
        try {
            em.getTransaction().begin();
            em.merge(caixa);
            em.getTransaction().commit();
        } catch (Exception e){
            em.getTransaction().rollback();
            throw new RuntimeException("Erro ao atualizar Caixa!",e);
        } finally {
            em.close();
        }
    }

    public void deletar(Long id){
        EntityManager em = HibernateConfig.getEntityManager();
        try {
            Caixa caixa = em.find(Caixa.class,id);
            if(caixa!=null){
                em.getTransaction().begin();
                em.remove(caixa);
                em.getTransaction().commit();
            }
        } catch (Exception e){
            em.getTransaction().rollback();
            throw new RuntimeException("Erro ao deletar Caixa!",e);
        }finally {
            em.close();
        }
    }
}
