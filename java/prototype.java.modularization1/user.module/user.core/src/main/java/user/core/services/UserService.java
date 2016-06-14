package user.core.services;

import java.util.Date;
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
    public UserDTO getUser() {

        System.out.println(em);
        
        UserDTO userDTO = new UserDTO();
        userDTO.setId(new Date().getTime());
        userDTO.setUserName("felipeprado");
        userDTO.setEmail("rodriguesprado.felipe@gmail.com");
        return userDTO;

    }

}
