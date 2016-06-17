package user.core.services;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import org.modelmapper.ModelMapper;
import user.api.models.dto.UserDTO;
import user.api.services.User;
import user.core.models.po.UserPO;

@Stateless
public class UserService implements User {

    @PersistenceContext(unitName = "postgresql")
    private EntityManager entityManager;

    @Override
    public List<UserDTO> getAll() {

        List<UserPO> userPOList;
        List<UserDTO> userList = new ArrayList<>();
        ModelMapper modelMapper = new ModelMapper();

        try {
            
//            for (int i = 0; i < 1000; i++) {
//                UserPO user = new UserPO();
//                int random = new Random().nextInt(100000);
//                user.setUserName("username_" + random);
//                user.setEmail(user.getUserName() + "@email.com.br");
//                user.setPassword("password_" + random);
//                user.setPeopleId(2);
//                
//                entityManager.persist(user);
//            }

            Query query = entityManager.createQuery("SELECT P FROM UserPO AS P ORDER BY P.id", UserPO.class);
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
