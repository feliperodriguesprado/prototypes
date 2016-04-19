package prototype.java.jsf.project2.datasource.connections;

import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;
import javax.enterprise.context.Dependent;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import prototype.java.jsf.project2.datasource.enums.Database;
import prototype.java.jsf.project2.datasource.qualifiers.Connection;

@Dependent
@Connection(database = Database.POSTGRESQL)
public class ConnectionJPAPostgreSQL implements ConnectionJPA {

    private EntityManagerFactory entityManagerFactory;
    private EntityManager entityManager;

    @PostConstruct
    public void postConstruct() {
        System.out.println("Object " + this.toString() + " built...");
        entityManagerFactory = Persistence.createEntityManagerFactory("postgresql");
        entityManager = entityManagerFactory.createEntityManager();
    }

    @PreDestroy
    public void preDestroy() {
        System.out.println("Object " + this.toString() + " destroyed...");
        entityManager.close();
        entityManagerFactory.close();
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

}
