package prototype.java.jsf.project2.services;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;
import javax.enterprise.context.RequestScoped;
import javax.inject.Inject;
import javax.persistence.EntityManager;
import javax.persistence.Query;
import javax.validation.ConstraintViolation;
import javax.validation.Validation;
import javax.validation.Validator;
import javax.validation.ValidatorFactory;
import org.modelmapper.ModelMapper;
import prototype.java.jsf.project2.models.dto.UserDTO;
import prototype.java.jsf.project2.models.po.UserPO;
import prototype.java.jsf.project2.datasource.settings.IDatasource;
import prototype.java.jsf.project2.datasource.connections.ConnectionJPA;

@RequestScoped
public class UserService implements IUserService {

    @Inject
    private IDatasource datasource;

    @Override
    public UserDTO create(UserDTO user) throws Exception {

        ConnectionJPA conJPAPostgreSQL;
        EntityManager em;
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

        conJPAPostgreSQL = datasource.getConnectionJPAPostgreSQL();
        conJPAPostgreSQL.begin();
        em = conJPAPostgreSQL.getEntityManager();

        try {
            if (userPO.getId() == 0) {
                em.persist(userPO);
            } else {
                em.merge(userPO);
            }

            Query query = em.createQuery("select p from UserPO p", UserPO.class);
            List<UserPO> userList = query.getResultList();

            userList.stream().forEach((userPO1) -> {
                System.out.println(userPO1);
            });

            conJPAPostgreSQL.commit();
            conJPAPostgreSQL.end();
            return user;

        } catch (Exception e) {
            conJPAPostgreSQL.rollback();

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
    public List<UserDTO> getAll() throws Exception {

        ConnectionJPA conJPAPostgreSQL = datasource.getConnectionJPAPostgreSQL();
        EntityManager em = conJPAPostgreSQL.getEntityManager();

        Query query = em.createQuery("select p from UserPO p", UserPO.class);
        List<UserPO> userList = query.getResultList();

        ModelMapper modelMapper = new ModelMapper();

        List<UserDTO> userListDTO = new ArrayList<>();

        for (UserPO userPO : userList) {
            UserDTO userDTO = modelMapper.map(userPO, UserDTO.class);
            userListDTO.add(userDTO);
        }

        conJPAPostgreSQL.end();
        return userListDTO;
    }

    @Override
    public UserDTO getById(long id) throws Exception {

        ConnectionJPA conJPAPostgreSQL = datasource.getConnectionJPAPostgreSQL();
        EntityManager em = conJPAPostgreSQL.getEntityManager();

        Query query = em.createQuery("select p from UserPO p where p.id = :id", UserPO.class);
        UserPO userPO = (UserPO) query.setParameter("id", id).getSingleResult();

        ModelMapper modelMapper = new ModelMapper();
        UserDTO userDTO = modelMapper.map(userPO, UserDTO.class);

        conJPAPostgreSQL.end();
        return userDTO;

    }

}
