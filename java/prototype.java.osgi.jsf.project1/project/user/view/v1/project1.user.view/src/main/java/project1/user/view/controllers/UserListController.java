package project1.user.view.controllers;

import java.io.Serializable;
import java.util.List;
import java.util.logging.Logger;
import javax.annotation.PostConstruct;
import javax.faces.application.FacesMessage;
import javax.faces.context.ExternalContext;
import javax.faces.context.FacesContext;
import javax.faces.view.ViewScoped;
import javax.inject.Named;
import org.osgi.framework.BundleContext;
import org.osgi.framework.FrameworkUtil;
import org.osgi.framework.ServiceReference;
import project1.user.api.models.dto.UserDTO;
import project1.user.api.services.IUserService;

@ViewScoped
@Named
public class UserListController implements Serializable {

    private static final long serialVersionUID = 1L;
    private IUserService userService;
    private List<UserDTO> userList;
    private UserDTO selectedUser;

    @PostConstruct
    public void init() {

        BundleContext bundleContext = FrameworkUtil.getBundle(IUserService.class).getBundleContext();
        ServiceReference serviceReference = bundleContext.getServiceReference(IUserService.class.getName());
        userService = (IUserService) bundleContext.getService(serviceReference);

        try {
            if (userService != null) {
                userList = userService.getAll();
            } else {
                Logger.getLogger("UserLog").warning("Service IUserService not found.");
            }

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
            
            if (userService != null) {
                userService.delete(userFromList.getId());
                userList = userService.getAll();
                FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "User deleted", userFromList.toString()));
            } else {
                Logger.getLogger("UserLog").warning("Service IUserService not found.");
            }
            
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
