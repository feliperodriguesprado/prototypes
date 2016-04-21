package prototype.java.jsf.project2.user.core.repositories;

import java.util.ArrayList;
import java.util.List;
import javax.annotation.PostConstruct;
import javax.enterprise.context.Dependent;
import javax.inject.Inject;
import org.modelmapper.ModelMapper;
import prototype.java.jsf.project2.datasource.connections.ConnectionJPA;
import prototype.java.jsf.project2.datasource.enums.Database;
import prototype.java.jsf.project2.datasource.qualifiers.Connection;
import prototype.java.jsf.project2.user.api.models.dto.UserDTO;
import prototype.java.jsf.project2.user.core.dao.IUserDAO;
import prototype.java.jsf.project2.user.core.models.po.UserPO;

@Dependent
public class UserRepository implements IUserRepository {

    @Inject
    @Connection(database = Database.POSTGRESQL)
    private ConnectionJPA _connectionJPAPostgreSQL;

    @Inject
    private IUserDAO _userDAO;

    private ModelMapper _modelMapper;

    @PostConstruct
    public void postConstruct() {
        _modelMapper = new ModelMapper();
    }

    @Override
    public UserDTO create(UserDTO user) throws Exception {

        UserPO userPO;
        UserDTO userDTO;

        try {
            userPO = _modelMapper.map(user, UserPO.class);
            _connectionJPAPostgreSQL.begin();
            _userDAO.setConnectionJPA(_connectionJPAPostgreSQL);
            userPO = _userDAO.create(userPO);
            userDTO = _modelMapper.map(userPO, UserDTO.class);
            _connectionJPAPostgreSQL.commit();
            return userDTO;
        } catch (Exception e) {
            System.out.println("Error: " + e.getMessage());
            _connectionJPAPostgreSQL.rollback();
            throw e;
        }
    }

    @Override
    public UserDTO getById(long id) throws Exception {

        UserPO userPO;
        UserDTO userDTO;

        _userDAO.setConnectionJPA(_connectionJPAPostgreSQL);
        userPO = _userDAO.getById(id);
        userDTO = _modelMapper.map(userPO, UserDTO.class);
        return userDTO;

    }

    @Override
    public UserDTO update(UserDTO user) throws Exception {

        UserPO userPO;

        try {
            userPO = _modelMapper.map(user, UserPO.class);
            _connectionJPAPostgreSQL.begin();
            _userDAO.setConnectionJPA(_connectionJPAPostgreSQL);
            userPO = _userDAO.update(userPO);
            user = _modelMapper.map(userPO, UserDTO.class);
            _connectionJPAPostgreSQL.commit();
            return user;
        } catch (Exception e) {
            System.out.println("Error: " + e.getMessage());
            _connectionJPAPostgreSQL.rollback();
            throw e;
        }
    }

    @Override
    public void delete(long id) throws Exception {

        try {
            _connectionJPAPostgreSQL.begin();
            _userDAO.setConnectionJPA(_connectionJPAPostgreSQL);
            _userDAO.delete(id);
            _connectionJPAPostgreSQL.commit();
        } catch (Exception e) {
            System.out.println("Error: " + e.getMessage());
            _connectionJPAPostgreSQL.rollback();
            throw e;
        }
    }

    @Override
    public List<UserDTO> getAll() throws Exception {

        List<UserPO> userListPO;
        List<UserDTO> userListDTO;

        _userDAO.setConnectionJPA(_connectionJPAPostgreSQL);
        userListPO = _userDAO.getAll();

        userListDTO = new ArrayList<>();

        userListPO.stream().forEach((userPO) -> {
            UserDTO userDTO = _modelMapper.map(userPO, UserDTO.class);
            userListDTO.add(userDTO);
        });

        return userListDTO;
    }

}
