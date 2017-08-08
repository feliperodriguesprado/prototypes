package user.core.services;

import java.util.ArrayList;
import java.util.List;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import org.modelmapper.ModelMapper;
import user.api.models.dto.UserDTO;
import user.api.services.User;
import user.core.models.po.PeoplePO;
import user.core.models.po.UserPO;

@Stateless
public class UserService implements User {

    @PersistenceContext(unitName = "postgresql")
    private EntityManager entityManager;
    
    public int test() {
        return 1;
    }

    @Override
    public List<UserDTO> getAll() {

        List<UserPO> userPOList;
        List<UserDTO> userList = new ArrayList<>();
        ModelMapper modelMapper = new ModelMapper();

        try {

            Query query = entityManager.createQuery("DELETE FROM UserPO");
            query.executeUpdate();
            
            for (int i = 0; i < 100; i++) {
                PeoplePO people = new PeoplePO();
                people.setName("Name People " + i);

                UserPO user = new UserPO();
                user.setUserName("username_" + i);
                user.setEmail(user.getUserName() + "@email.com.br");
                user.setPassword("password_" + i);
                user.setPeople(people);

                entityManager.persist(user);
            }
            
            query = entityManager.createQuery("SELECT P FROM UserPO AS P ORDER BY P.id", UserPO.class);
            userPOList = query.getResultList();

            userPOList.stream().forEach((userPO) -> {
                UserDTO userDTO = modelMapper.map(userPO, UserDTO.class);
                userList.add(userDTO);
            });

        } catch (Exception e) {
            System.out.println("Error: " + e.getMessage());
        }

        return userList;

    }

}
