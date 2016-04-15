package prototype.java.jsf.project2.services;

import java.util.List;
import java.util.Set;
import javax.enterprise.context.RequestScoped;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.Query;
import javax.validation.ConstraintViolation;
import javax.validation.Validation;
import javax.validation.Validator;
import javax.validation.ValidatorFactory;
import org.modelmapper.ModelMapper;
import prototype.java.jsf.project2.models.dto.UserDTO;
import prototype.java.jsf.project2.models.po.UserPO;

@RequestScoped
public class UserService implements IUserService {

    @Override
    public UserDTO create(UserDTO user) throws Exception {

        ModelMapper modelMapper = new ModelMapper();
        UserPO userPO = modelMapper.map(user, UserPO.class);

        Validator validator;
        ValidatorFactory factory = Validation.buildDefaultValidatorFactory();
        validator = factory.getValidator();
        Set<ConstraintViolation<UserPO>> constraintViolations = validator.validate(userPO);

        System.out.println("Size constraint validation: " + constraintViolations.size());
        System.out.println("Message(s): ");

        constraintViolations.stream().forEach((constraintViolation) -> {
            System.out.println(constraintViolation.getMessage());
        });

        EntityManagerFactory emf = Persistence.createEntityManagerFactory("postgresql");
        EntityManager em = emf.createEntityManager();

        try {
            em.getTransaction().begin();

            em.unwrap(java.sql.Connection.class).setAutoCommit(false);
            // references:
            // http://sofc.developer-works.com/article/21717711/EclipseLink+%3A+JPA+Entitty+Manager
            // http://wiki.eclipse.org/EclipseLink/Examples/JPA/EMAPI#Getting_a_JDBC_Connection_from_an_EntityManager

            em.persist(userPO);
            em.getTransaction().commit();

            Query query = em.createQuery("select p from UserPO p", UserPO.class);
            List<UserPO> userList = query.getResultList();

            userList.stream().forEach((userPO1) -> {
                System.out.println(userPO1);
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
    public List<UserDTO> getAll() {
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("postgresql");
        EntityManager em = emf.createEntityManager();

        Query query = em.createQuery("select p from UserPO p", UserPO.class);
        List<UserPO> userList = query.getResultList();

        ModelMapper modelMapper = new ModelMapper();
        List<UserDTO> userListDTO = modelMapper.map(userList, List.class);

        return userListDTO;
    }

}
