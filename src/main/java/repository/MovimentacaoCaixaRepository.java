package repository;

import Config.HibernateConfig;
import jakarta.persistence.*;
import model.MovimentacaoCaixa;

import java.util.List;

public class MovimentacaoCaixaRepository {
    public void salvar(MovimentacaoCaixa mov){
        EntityManager em = HibernateConfig.getEntityManager();
        try {
            em.getTransaction().begin();
            em.persist(mov);
            em.getTransaction().commit();
        }catch (Exception e){
            em.getTransaction().rollback();
            throw new RuntimeException("Erro ao salvar Movimentação!",e);
        } finally {
            em.close();
        }
    }
    public MovimentacaoCaixa buscarPorId(Long id){
        EntityManager em = HibernateConfig.getEntityManager();
        MovimentacaoCaixa mov = em.find(MovimentacaoCaixa.class,id);
        em.close();
        return mov;
    }

    public List<MovimentacaoCaixa> listarTodos(){
        EntityManager em = HibernateConfig.getEntityManager();
        List<MovimentacaoCaixa> lista =
                em.createQuery("SELECT m FROM MovimentacaoCaixa m", MovimentacaoCaixa.class)
                        .getResultList();
        em.close();
        return lista;
    }

    public void atualizar(MovimentacaoCaixa mov){
        EntityManager em = HibernateConfig.getEntityManager();
        try {
            em.getTransaction().begin();
            em.merge(mov);
            em.getTransaction().commit();
        } catch (Exception e){
            em.getTransaction().rollback();
            throw new RuntimeException("Erro ao atualizar Movimentação!",e);
        } finally {
            em.close();
        }
    }

    public void deletar(Long id){
        EntityManager em = HibernateConfig.getEntityManager();
        try {
            MovimentacaoCaixa mov = em.find(MovimentacaoCaixa.class,id);
            if(mov!=null){
                em.getTransaction().begin();
                em.remove(mov);
                em.getTransaction().commit();
            }
        } catch (Exception e){
            em.getTransaction().rollback();
            throw new RuntimeException("Erro ao deletar Movimentação!",e);
        }finally {
            em.close();
        }
    }
}
