package project1.user.view.controllers;

import java.io.Serializable;
import java.util.logging.Logger;
import javax.annotation.PostConstruct;
import javax.faces.application.FacesMessage;
import javax.faces.context.ExternalContext;
import javax.faces.context.FacesContext;
import javax.faces.view.ViewScoped;
import javax.inject.Inject;
import javax.inject.Named;
import org.osgi.framework.BundleContext;
import org.osgi.framework.FrameworkUtil;
import org.osgi.framework.ServiceReference;
import org.primefaces.context.RequestContext;
import project1.user.api.enums.PeopleType;
import project1.user.api.models.dto.PeopleDTO;
import project1.user.api.models.dto.UserDTO;
import project1.user.api.services.IUserService;

@ViewScoped
@Named
public class UserController implements Serializable {

    private static final long serialVersionUID = 1L;
    private IUserService userService;

    private long paramUserId;
    private PeopleDTO people;
    private UserDTO user;

    @PostConstruct
    public void init() {
        
        BundleContext bundleContext = FrameworkUtil.getBundle(IUserService.class).getBundleContext();
        ServiceReference serviceReference = bundleContext.getServiceReference(IUserService.class.getName());
        userService = (IUserService) bundleContext.getService(serviceReference);
        
        people = new PeopleDTO();
        user = new UserDTO();
    }

    public void onload() {
        if (paramUserId > 0) {
            try {
                
                if (userService != null) {
                    user = userService.getById(paramUserId);
                } else {
                    Logger.getLogger("UserLog").warning("Service IUserService not found.");
                }
                
            } catch (Exception e) {
                System.out.println("Error: " + e.getMessage());
            }
        }
    }

    public UserDTO getUser() {
        return user;
    }

    public void save() {

        try {
            people.setName(user.getUserName());
            people.setType(PeopleType.USER);
            user.setPeople(people);

            if (userService != null) {
                if (user.getId() == 0) {
                    userService.create(user);
                } else {
                    userService.update(user);
                }
            } else {
                Logger.getLogger("UserLog").warning("Service IUserService not found.");
            }

            RequestContext requestContext = RequestContext.getCurrentInstance();
            requestContext.execute("PF('userRegister').show();");
        } catch (Exception ex) {
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_WARN, "User register", ex.getMessage()));
        }
    }

    public void redirect() {

        try {
            FacesContext fc = FacesContext.getCurrentInstance();
            ExternalContext ec = fc.getExternalContext();
            ec.redirect("list.xhtml");
        } catch (Exception e) {
            System.out.println("error: " + e.getMessage());
        }
    }

    public long getParamUserId() {
        return paramUserId;
    }

    public void setParamUserId(long paramUserId) {
        this.paramUserId = paramUserId;
    }

}
