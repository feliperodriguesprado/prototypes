package user.view.controllers;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import javax.annotation.PostConstruct;
import javax.faces.view.ViewScoped;
import javax.inject.Named;
import user.api.models.dto.UserDTO;

@ViewScoped
@Named
public class UserListController implements Serializable {

    private static final long serialVersionUID = 1L;
    private List<UserDTO> userList;
    private UserDTO selectedUser;

    @PostConstruct
    public void init() {
        try {
            userList = new ArrayList<>();
            
            UserDTO user1 = new UserDTO();
            user1.setId(new Date().getTime());
            user1.setUserName("felipeprado");
            user1.setEmail("rodriguesprado.felipe@gmail.com");
            user1.setPassword("Pa$$w0rd");
            user1.setRepeatPassword("Pa$$w0rd");
            
            UserDTO user2 = new UserDTO();
            user2.setId(new Date().getTime());
            user2.setUserName("feliperodriguesprado");
            user2.setEmail("feliperodriguesprado@hotmail.com");
            user2.setPassword("Pa$$w0rd");
            user2.setRepeatPassword("Pa$$w0rd");
            
            userList.add(user1);
            userList.add(user2);
            
        } catch (Exception e) {
            System.out.println("Error: " + e.getMessage());
        }
    }

    public List<UserDTO> getUserList() {
        return userList;
    }

    public UserDTO getSelectedUser() {
        return selectedUser;
    }

    public void setSelectedUser(UserDTO selectedUser) {
        this.selectedUser = selectedUser;
    }

    public void listenerRowDblselect() {
        if (selectedUser != null) {
            System.out.println(selectedUser);
        }
    }

    public void editUserFromList(UserDTO userFromList) {
        System.out.println("Edit user from list...");
        System.out.println(userFromList);
    }

    public void removeUserFromList(UserDTO userFromList) {
        System.out.println("Remove user from list...");
        System.out.println(userFromList);
    }

}
