package prototype.java.jsf.project2.user.api.services;

import java.util.List;
import prototype.java.jsf.project2.user.api.models.dto.UserDTO;

public interface IUserService {

    UserDTO create(UserDTO user) throws Exception;

    UserDTO getById(long id) throws Exception;

    UserDTO update(UserDTO user) throws Exception;

    void delete(long id) throws Exception;

    List<UserDTO> getAll() throws Exception;

}
