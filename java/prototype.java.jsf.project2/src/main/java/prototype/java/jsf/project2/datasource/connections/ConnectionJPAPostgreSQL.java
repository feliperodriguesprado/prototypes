package prototype.java.jsf.project2.datasource.connections;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

public class ConnectionJPAPostgreSQL implements ConnectionJPA {

    private final EntityManagerFactory entityManagerFactory;
    private final EntityManager entityManager;

    public ConnectionJPAPostgreSQL() {
        entityManagerFactory = Persistence.createEntityManagerFactory("postgresql");
        entityManager = entityManagerFactory.createEntityManager();
    }

    @Override
    public EntityManager getEntityManager() {
        return entityManager;
    }

    @Override
    public void begin() throws Exception {
        entityManager.getTransaction().begin();
        entityManager.unwrap(java.sql.Connection.class).setAutoCommit(false);
        // references:
        // http://sofc.developer-works.com/article/21717711/EclipseLink+%3A+JPA+Entitty+Manager
        // http://wiki.eclipse.org/EclipseLink/Examples/JPA/EMAPI#Getting_a_JDBC_Connection_from_an_EntityManager
    }

    @Override
    public void commit() throws Exception {
        if (entityManager.getTransaction().isActive()) {
            entityManager.getTransaction().commit();
        }
    }

    @Override
    public void rollback() throws Exception {
        if (entityManager.getTransaction().isActive()) {
            entityManager.getTransaction().rollback();
        }
    }

    @Override
    public void end() throws Exception {
        entityManager.close();
        entityManagerFactory.close();
    }

}
