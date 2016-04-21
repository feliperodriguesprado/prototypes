package prototype.java.jsf.project2.user.core.dao;

import java.util.List;
import javax.enterprise.context.Dependent;
import javax.persistence.EntityManager;
import javax.persistence.Query;
import prototype.java.jsf.project2.datasource.connections.ConnectionJPA;
import prototype.java.jsf.project2.datasource.connections.ConnectionJPAPostgreSQL;
import prototype.java.jsf.project2.user.core.models.po.UserPO;

@Dependent
public class UserDAOPostgreSQLJPA implements IUserDAO {

    private ConnectionJPAPostgreSQL _connectionJPAPostgreSQL;

    @Override
    public void setConnectionJPA(ConnectionJPA connectionJPA) throws Exception {
        this._connectionJPAPostgreSQL = (ConnectionJPAPostgreSQL) connectionJPA;
    }

    @Override
    public UserPO create(UserPO user) throws Exception {

        EntityManager entityManager;

        entityManager = _connectionJPAPostgreSQL.getEntityManager();
        entityManager.persist(user);
        return user;

    }

    @Override
    public UserPO getById(long id) throws Exception {

        EntityManager entityManager;
        UserPO userPO;

        entityManager = _connectionJPAPostgreSQL.getEntityManager();
        userPO = (UserPO) entityManager.find(UserPO.class, id);
        return userPO;
    }

    @Override
    public UserPO update(UserPO user) throws Exception {

        EntityManager entityManager;

        entityManager = _connectionJPAPostgreSQL.getEntityManager();
        user = entityManager.merge(user);
        return user;
    }

    @Override
    public void delete(long id) throws Exception {

        EntityManager entityManager;
        UserPO userPO;

        entityManager = _connectionJPAPostgreSQL.getEntityManager();
        userPO = getById(id);

        if (userPO != null) {
            entityManager.remove(userPO);
        }
    }

    @Override
    public List<UserPO> getAll() throws Exception {

        EntityManager entityManager;
        List<UserPO> userList;

        entityManager = _connectionJPAPostgreSQL.getEntityManager();
        Query query = entityManager.createQuery("SELECT P FROM UserPO AS P ORDER BY P.id", UserPO.class);
        userList = query.getResultList();
        return userList;
    }

}
