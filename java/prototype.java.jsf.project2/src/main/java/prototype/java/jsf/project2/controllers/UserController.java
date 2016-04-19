package prototype.java.jsf.project2.controllers;

import java.io.Serializable;
import javax.annotation.PostConstruct;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.faces.view.ViewScoped;
import javax.inject.Inject;
import javax.inject.Named;
import prototype.java.jsf.project2.enums.PeopleType;
import prototype.java.jsf.project2.models.dto.PeopleDTO;
import prototype.java.jsf.project2.models.dto.UserDTO;
import prototype.java.jsf.project2.services.IUserService;

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

        people.setName(user.getUserName());
        people.setType(PeopleType.USER);
        user.setPeople(people);

        System.out.println(user);

        try {
            userService.create(user);
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "User register", user.toString()));
        } catch (Exception ex) {
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_WARN, "User register", ex.getMessage()));
        }

        people = new PeopleDTO();
        user = new UserDTO();
    }

    public long getParamUserId() {
        return paramUserId;
    }

    public void setParamUserId(long paramUserId) {
        this.paramUserId = paramUserId;
    }

}
