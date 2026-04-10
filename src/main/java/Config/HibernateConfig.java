package Config;


import jakarta.persistence.*;

public class HibernateConfig {

    private static final EntityManagerFactory emf =
            Persistence.createEntityManagerFactory("meu-persistence");

    public static EntityManager getEntityManager() {
        return emf.createEntityManager();
    }
}
