package prototype.java.jsf.project2.controllers;

import java.io.Serializable;
import java.util.Date;
import java.util.Set;
import javax.annotation.PostConstruct;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.faces.view.ViewScoped;
import javax.inject.Named;
import javax.validation.ConstraintViolation;
import javax.validation.Validation;
import javax.validation.Validator;
import javax.validation.ValidatorFactory;
import prototype.java.jsf.project2.models.UserModel;

@ViewScoped
@Named
public class UserController implements Serializable {

    private static final long serialVersionUID = 1L;
    private Validator validator;
    private UserModel user;

    @PostConstruct
    public void init() {
        user = new UserModel();
    }

    public UserModel getUser() {
        return user;
    }

    public void save() {
        System.out.println(user);

        ValidatorFactory factory = Validation.buildDefaultValidatorFactory();
        validator = factory.getValidator();
        Set<ConstraintViolation<UserModel>> constraintViolations = validator.validate(user);

        System.out.println("Size constraint validation: " + constraintViolations.size());
        System.out.println("Message(s): ");
        for (ConstraintViolation<UserModel> constraintViolation : constraintViolations) {
            System.out.println(constraintViolation.getMessage());
        }

        FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "User register", user.toString()));
    }

}
