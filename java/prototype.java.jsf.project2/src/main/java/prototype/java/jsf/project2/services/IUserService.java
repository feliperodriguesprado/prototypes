package prototype.java.jsf.project2.services;

import java.util.Set;
import prototype.java.jsf.project2.models.dto.UserDTO;

public interface IUserService {

    public UserDTO create(UserDTO user) throws Exception;

    public Set<UserDTO> getAll() throws Exception;

}
