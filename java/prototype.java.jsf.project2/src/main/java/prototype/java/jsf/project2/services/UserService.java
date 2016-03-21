package prototype.java.jsf.project2.services;

import java.util.List;
import java.util.Set;
import javax.enterprise.context.RequestScoped;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.Query;
import prototype.java.jsf.project2.models.UserPO;

@RequestScoped
public class UserService implements IUserService {

    @Override
    public UserPO create(UserPO user) throws Exception {

        EntityManagerFactory emf = Persistence.createEntityManagerFactory("postgresql");
        EntityManager em = emf.createEntityManager();

        try {
            em.getTransaction().begin();

            em.unwrap(java.sql.Connection.class).setAutoCommit(false);
            // references:
            // http://sofc.developer-works.com/article/21717711/EclipseLink+%3A+JPA+Entitty+Manager
            // http://wiki.eclipse.org/EclipseLink/Examples/JPA/EMAPI#Getting_a_JDBC_Connection_from_an_EntityManager

            em.persist(user);
            em.getTransaction().commit();

            Query query = em.createQuery("select p from UserPO p", UserPO.class);
            List<UserPO> userList = query.getResultList();

            userList.stream().forEach((userPO) -> {
                System.out.println(userPO);
            });

            em.close();
            emf.close();
            return user;

        } catch (Exception e) {

            if (em.getTransaction().isActive()) {
                em.getTransaction().rollback();
            }

            em.close();
            emf.close();

            if (e.getMessage().contains("uk_users_username")) {
                throw new Exception(String.format("Username %s already exists", user.getUserName()));
            } else if (e.getMessage().contains("uk_users_email")) {
                throw new Exception(String.format("Email %s already exists", user.getUserName()));
            } else {
                throw new Exception(String.format("Error create user. Details: ", e.getMessage()));
            }
        }
    }

    @Override
    public Set<UserPO> getAll() {
        return null;
    }

}
