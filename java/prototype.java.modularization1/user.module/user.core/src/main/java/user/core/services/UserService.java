package user.core.services;

import java.util.List;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import user.api.models.dto.UserDTO;
import user.api.services.User;

@Stateless
public class UserService implements User {

    @PersistenceContext(unitName = "postgresql")
    private EntityManager em;

    @Override
    public UserDTO create(UserDTO user) throws Exception {
        return null;
    }

    @Override
    public UserDTO getById(long id) throws Exception {
        return null;
    }

    @Override
    public UserDTO update(UserDTO user) throws Exception {
        return null;
    }

    @Override
    public void delete(long id) throws Exception {

    }

    @Override
    public List<UserDTO> getAll() throws Exception {
        return null;
    }

}
