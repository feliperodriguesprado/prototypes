package prototype.java.jsf.project2.user.view.controllers;

import java.io.Serializable;
import javax.annotation.PostConstruct;
import javax.faces.application.FacesMessage;
import javax.faces.context.ExternalContext;
import javax.faces.context.FacesContext;
import javax.faces.view.ViewScoped;
import javax.inject.Inject;
import javax.inject.Named;
import org.primefaces.context.RequestContext;
import prototype.java.jsf.project2.user.api.enums.PeopleType;
import prototype.java.jsf.project2.user.api.models.dto.PeopleDTO;
import prototype.java.jsf.project2.user.api.models.dto.UserDTO;
import prototype.java.jsf.project2.user.api.services.IUserService;

@ViewScoped
@Named
public class UserController implements Serializable {

    private static final long serialVersionUID = 1L;
    @Inject
    private IUserService userService;

    private long paramUserId;
    private PeopleDTO people;
    private UserDTO user;

    @PostConstruct
    public void init() {
        people = new PeopleDTO();
        user = new UserDTO();
    }

    public void onload() {
        if (paramUserId > 0) {
            try {
                user = userService.getById(paramUserId);
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
