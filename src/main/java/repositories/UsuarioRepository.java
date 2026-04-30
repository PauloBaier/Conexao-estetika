package repositories;
import Config.HibernateConfig;
import jakarta.persistence.EntityManager;
import models.Produto;
import models.Usuario;

import java.util.List;

public class UsuarioRepository implements IRepository<Usuario>{

    @Override
    public void salvar(Usuario usuario) {
        EntityManager em = HibernateConfig.getEntityManager();
        try {
            em.getTransaction().begin();
            em.persist(usuario);
            em.getTransaction().commit();
        } catch (Exception e) {
            if (em.getTransaction().isActive()) {
                em.getTransaction().rollback();
            }
            throw new RuntimeException("Erro ao salvar Usuário!", e);
        } finally {
            em.close();
        }
    }

    @Override
    public void atualizar(Usuario usuario) {
        EntityManager em = HibernateConfig.getEntityManager();
        try {
            em.getTransaction().begin();
            em.merge(usuario);
            em.getTransaction().commit();
        } catch (Exception e) {
            if (em.getTransaction().isActive()) {
                em.getTransaction().rollback();
            }
            throw new RuntimeException("Erro ao atualizar Usuário!", e);
        } finally {
            em.close();
        }
    }

    @Override
    public void delete(Usuario usuario) {
        EntityManager em = HibernateConfig.getEntityManager();
        try {
            em.getTransaction().begin();
            Usuario usuarioGerenciado = em.find(Usuario.class, usuario.getId());
            if (usuarioGerenciado != null) {
                em.remove(usuarioGerenciado);
            }
            em.getTransaction().commit();
        } catch (Exception e) {
            if (em.getTransaction().isActive()) {
                em.getTransaction().rollback();
            }
            throw new RuntimeException("Erro ao deletar Usuario!", e);
        } finally {
            em.close();
        }
    }

    @Override
    public Usuario buscarPorId(long id) {
        EntityManager em = HibernateConfig.getEntityManager();
        try {
            return em.find(Usuario.class, id);
        } finally {
            em.close();
        }
    }

    @Override
    public List<Usuario> listarTodos() {
        EntityManager em = HibernateConfig.getEntityManager();
        try {
            return em.createQuery("SELECT u FROM Usuario u", Usuario.class).getResultList();
        } finally {
            em.close();
        }
    }
}
