package Config;

import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.Persistence;

public class HibernateConfig {

    private static final EntityManagerFactory emf =
            Persistence.createEntityManagerFactory("meu-persistence");

    public static EntityManager getEntityManager() {
        return emf.createEntityManager();
    }
}
