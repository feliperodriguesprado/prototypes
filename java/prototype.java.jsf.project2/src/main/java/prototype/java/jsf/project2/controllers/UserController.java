package prototype.java.jsf.project2.controllers;

import java.io.Serializable;
import java.util.Set;
import javax.annotation.PostConstruct;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.faces.view.ViewScoped;
import javax.inject.Inject;
import javax.inject.Named;
import javax.validation.ConstraintViolation;
import javax.validation.Validation;
import javax.validation.Validator;
import javax.validation.ValidatorFactory;
import prototype.java.jsf.project2.enums.PeopleType;
import prototype.java.jsf.project2.models.PeoplePO;
import prototype.java.jsf.project2.models.UserPO;
import prototype.java.jsf.project2.services.IUserService;

@ViewScoped
@Named
public class UserController implements Serializable {

    private static final long serialVersionUID = 1L;
    @Inject
    private IUserService userService;
    private Validator validator;
    private PeoplePO people;
    private UserPO user;

    @PostConstruct
    public void init() {
        people = new PeoplePO();
        user = new UserPO();
    }

    public UserPO getUser() {
        return user;
    }

    public void save() {

        people.setName(user.getUserName());
        people.setType(PeopleType.USER);
        user.setPeople(people);

        System.out.println(user);

        ValidatorFactory factory = Validation.buildDefaultValidatorFactory();
        validator = factory.getValidator();
        Set<ConstraintViolation<UserPO>> constraintViolations = validator.validate(user);

        System.out.println("Size constraint validation: " + constraintViolations.size());
        System.out.println("Message(s): ");

        constraintViolations.stream().forEach((constraintViolation) -> {
            System.out.println(constraintViolation.getMessage());
        });

        try {
            userService.create(user);
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "User register", user.toString()));
        } catch (Exception ex) {
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_WARN, "User register", ex.getMessage()));
        }

        people = new PeoplePO();
        user = new UserPO();
    }

}
