package prototype.java.jsf.project2.controllers;

import java.io.Serializable;
import java.util.List;
import javax.annotation.PostConstruct;
import javax.faces.view.ViewScoped;
import javax.inject.Inject;
import javax.inject.Named;
import prototype.java.jsf.project2.models.dto.UserDTO;
import prototype.java.jsf.project2.services.IUserService;

@ViewScoped
@Named
public class UserListController implements Serializable {

    private static final long serialVersionUID = 1L;
    @Inject
    private IUserService userService;
    private List<UserDTO> userList;
    private UserDTO selectedUser;

    @PostConstruct
    public void init() {
        try {
            userList = userService.getAll();
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

}
