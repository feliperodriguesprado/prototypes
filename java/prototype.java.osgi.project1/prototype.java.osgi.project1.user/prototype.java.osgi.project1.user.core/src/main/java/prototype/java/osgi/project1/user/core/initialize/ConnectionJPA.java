package prototype.java.osgi.project1.user.core.initialize;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

public class ConnectionJPA {

    public ConnectionJPA() {
        EntityManagerFactory entityManagerFactory;
        EntityManager entityManager;

        entityManagerFactory = Persistence.createEntityManagerFactory("postgresql");
        entityManager = entityManagerFactory.createEntityManager();
        System.out.println(entityManagerFactory);
        System.out.println(entityManager);
    }

}
