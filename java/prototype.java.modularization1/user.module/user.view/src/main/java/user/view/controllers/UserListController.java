package user.view.controllers;

import java.io.Serializable;
import java.util.List;
import javax.annotation.PostConstruct;
import javax.faces.application.FacesMessage;
import javax.faces.view.ViewScoped;
import javax.inject.Named;
import org.primefaces.context.RequestContext;
import user.api.models.dto.UserDTO;
import user.api.services.User;
import util.servicelocator.providers.BundleProvider;

@ViewScoped
@Named
public class UserListController implements Serializable {

    private static final long serialVersionUID = 1L;
    private User userService;
    private List<UserDTO> userList;
    private UserDTO selectedUser;

    @PostConstruct
    public void init() {
        try {
            userService = (User) BundleProvider.getBundleService(User.class);

            if (userService != null) {
                userList = userService.getAll();
            } else {
                FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_WARN, "Warning", "User Module Core not found");
                RequestContext.getCurrentInstance().showMessageInDialog(message);
            }

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
