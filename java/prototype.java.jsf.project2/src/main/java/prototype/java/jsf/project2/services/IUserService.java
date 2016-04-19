package prototype.java.jsf.project2.services;

import java.util.List;
import prototype.java.jsf.project2.models.dto.UserDTO;

public interface IUserService {

    public UserDTO create(UserDTO user) throws Exception;
    
    public UserDTO getById(long id) throws Exception;

    public List<UserDTO> getAll() throws Exception;

}
