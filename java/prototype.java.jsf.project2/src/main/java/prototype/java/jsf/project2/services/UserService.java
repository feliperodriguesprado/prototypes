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
import prototype.java.jsf.project2.datasource.connections.ConnectionJPA;
import prototype.java.jsf.project2.datasource.enums.Database;
import prototype.java.jsf.project2.datasource.qualifiers.Connection;

@RequestScoped
public class UserService implements IUserService {

    @Inject
    @Connection(database = Database.POSTGRESQL)
    private ConnectionJPA connectionJPAPostgreSQL;

    @Override
    public UserDTO create(UserDTO user) throws Exception {

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

        connectionJPAPostgreSQL.begin();
        em = connectionJPAPostgreSQL.getEntityManager();

        try {
            if (userPO.getId() == 0) {
                em.persist(userPO);
            } else {
                em.merge(userPO);
            }

            connectionJPAPostgreSQL.commit();
            return user;
        } catch (Exception e) {
            connectionJPAPostgreSQL.rollback();

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
        
        EntityManager em = connectionJPAPostgreSQL.getEntityManager();

        Query query = em.createQuery("SELECT P FROM UserPO AS P ORDER BY P.id", UserPO.class);
        List<UserPO> userList = query.getResultList();

        ModelMapper modelMapper = new ModelMapper();

        List<UserDTO> userListDTO = new ArrayList<>();

        for (UserPO userPO : userList) {
            UserDTO userDTO = modelMapper.map(userPO, UserDTO.class);
            userListDTO.add(userDTO);
        }

        return userListDTO;
    }

    @Override
    public UserDTO getById(long id) throws Exception {

        EntityManager em = connectionJPAPostgreSQL.getEntityManager();
        UserPO userPO = (UserPO) em.find(UserPO.class, id);

        ModelMapper modelMapper = new ModelMapper();
        UserDTO userDTO = modelMapper.map(userPO, UserDTO.class);

        return userDTO;

    }

}
