package prototype.java.jsf.project2.user.core.services;

import prototype.java.jsf.project2.user.api.services.IUserService;
import java.util.List;
import java.util.Set;
import javax.enterprise.context.RequestScoped;
import javax.inject.Inject;
import javax.validation.ConstraintViolation;
import javax.validation.Validation;
import javax.validation.Validator;
import javax.validation.ValidatorFactory;
import prototype.java.jsf.project2.user.api.models.dto.UserDTO;
import prototype.java.jsf.project2.user.core.repositories.IUserRepository;

@RequestScoped
public class UserService implements IUserService {

    @Inject
    private IUserRepository _userRepository;

    @Override
    public UserDTO create(UserDTO user) throws Exception {

        Validator validator;
        ValidatorFactory factory = Validation.buildDefaultValidatorFactory();
        validator = factory.getValidator();
        Set<ConstraintViolation<UserDTO>> constraintViolations = validator.validate(user);

        System.out.println("Size constraint validation: " + constraintViolations.size());
        System.out.println("Message(s): ");

        constraintViolations.stream().forEach((constraintViolation) -> {
            System.out.println(constraintViolation.getMessage());
        });

        try {
            user = _userRepository.create(user);
            return user;
        } catch (Exception e) {

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
    public UserDTO getById(long id) throws Exception {

        UserDTO userDTO;

        try {
            userDTO = _userRepository.getById(id);
            return userDTO;
        } catch (Exception e) {
            throw new Exception(String.format("Error get user by ID. Details: ", e.getMessage()));
        }
    }

    @Override
    public UserDTO update(UserDTO user) throws Exception {
        try {
            user = _userRepository.update(user);
            return user;
        } catch (Exception e) {

            if (e.getMessage().contains("uk_users_username")) {
                throw new Exception(String.format("Username %s already exists", user.getUserName()));
            } else if (e.getMessage().contains("uk_users_email")) {
                throw new Exception(String.format("Email %s already exists", user.getUserName()));
            } else {
                throw new Exception(String.format("Error update user. Details: ", e.getMessage()));
            }
        }
    }

    @Override
    public void delete(long id) throws Exception {

        try {
            _userRepository.delete(id);
        } catch (Exception e) {
            throw new Exception(String.format("Error delete user. Details: ", e.getMessage()));
        }

    }

    @Override
    public List<UserDTO> getAll() throws Exception {

        List<UserDTO> userListDTO;

        try {
            userListDTO = _userRepository.getAll();
            return userListDTO;
        } catch (Exception e) {
            throw new Exception(String.format("Error get user all. Details: ", e.getMessage()));
        }
    }

}
