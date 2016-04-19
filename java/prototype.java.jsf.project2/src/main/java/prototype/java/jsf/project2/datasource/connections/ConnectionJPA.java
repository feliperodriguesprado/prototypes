package prototype.java.jsf.project2.datasource.connections;

import javax.persistence.EntityManager;

public interface ConnectionJPA {

    EntityManager getEntityManager() throws Exception;

    void begin() throws Exception;

    void commit() throws Exception;

    void rollback() throws Exception;
}
