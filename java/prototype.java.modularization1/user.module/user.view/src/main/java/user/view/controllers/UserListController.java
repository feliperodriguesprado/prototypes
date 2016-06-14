package user.view.controllers;

import java.io.Serializable;
import java.util.List;
import javax.annotation.PostConstruct;
import javax.faces.application.FacesMessage;
import javax.faces.context.ExternalContext;
import javax.faces.context.FacesContext;
import javax.faces.view.ViewScoped;
import javax.inject.Named;
import user.api.models.dto.UserDTO;
import user.api.services.User;

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
            userList = userService.getAll();
        } catch (Exception e) {
            System.out.println("Error: " + e.getMessage());
        }
    }

    public void listenerRowDblselect() {        
        if (selectedUser != null) {
            redirectToEditUser(selectedUser.getId());
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
    
    public void editUserFromList(UserDTO userFromList) {
        redirectToEditUser(userFromList.getId());
    }
    
    public void removeUserFromList(UserDTO userFromList) {
        
        
        try {
            userService.delete(userFromList.getId());
            userList = userService.getAll();
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "User deleted", userFromList.toString()));
        } catch (Exception ex) {
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_WARN, "User register", ex.getMessage()));
        }
        
    }

    private void redirectToEditUser(long userId) {
        FacesContext fc = FacesContext.getCurrentInstance();
        ExternalContext ec = fc.getExternalContext();

        try {
            ec.redirect("register.xhtml?userId=" + userId);
        } catch (Exception e) {
            System.out.println("error: " + e.getMessage());
        }
    }
    
}
