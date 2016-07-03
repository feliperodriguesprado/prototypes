package user.api.services;

import java.util.List;
import user.api.models.dto.UserDTO;

public interface User {
    
    public List<UserDTO> getAll();
}
