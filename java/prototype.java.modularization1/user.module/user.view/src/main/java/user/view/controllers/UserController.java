package user.view.controllers;

import java.io.Serializable;
import javax.annotation.PostConstruct;
import javax.faces.application.FacesMessage;
import javax.faces.context.ExternalContext;
import javax.faces.context.FacesContext;
import javax.faces.view.ViewScoped;
import javax.inject.Named;
import org.primefaces.context.RequestContext;
import user.api.enums.PeopleType;
import user.api.models.dto.PeopleDTO;
import user.api.models.dto.UserDTO;
import user.api.services.User;
import util.servicelocator.providers.BundleProvider;

@ViewScoped
@Named
public class UserController implements Serializable {

    private static final long serialVersionUID = 1L;

    private User userService;

    private long paramUserId;
    private PeopleDTO people;
    private UserDTO user;

    @PostConstruct
    public void init() {
        people = new PeopleDTO();
        user = new UserDTO();
    }

    public void onload() {

        userService = (User) BundleProvider.getBundleService(User.class);

        if (userService != null) {

            if (paramUserId > 0) {
                try {
                    user = userService.getById(paramUserId);
                } catch (Exception e) {
                    System.out.println("Error: " + e.getMessage());
                }
            }
        }

    }

    public UserDTO getUser() {
        return user;
    }

    public void save() {

        userService = (User) BundleProvider.getBundleService(User.class);

        if (userService != null) {
            
            try {
                people.setName(user.getUserName());
                people.setType(PeopleType.USER);
                user.setPeople(people);

                if (user.getId() == 0) {
                    userService.create(user);
                } else {
                    userService.update(user);
                }

                RequestContext requestContext = RequestContext.getCurrentInstance();
                requestContext.execute("PF('userRegister').show();");
            } catch (Exception ex) {
                FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_WARN, "User register", ex.getMessage()));
            }
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
