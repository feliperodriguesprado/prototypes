package prototype.java.jsf.project2.controllers;

import java.io.Serializable;
import java.util.List;
import java.util.Set;
import javax.annotation.PostConstruct;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.faces.view.ViewScoped;
import javax.inject.Named;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;
import javax.persistence.Query;
import javax.persistence.RollbackException;
import javax.validation.ConstraintViolation;
import javax.validation.Validation;
import javax.validation.Validator;
import javax.validation.ValidatorFactory;
import prototype.java.jsf.project2.enums.PeopleType;
import prototype.java.jsf.project2.models.PeoplePO;
import prototype.java.jsf.project2.models.UserPO;

@ViewScoped
@Named
public class UserController implements Serializable {

    private static final long serialVersionUID = 1L;
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

        String errorPersist = null;

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

        EntityManagerFactory emf = Persistence.createEntityManagerFactory("postgresql");
        EntityManager em = emf.createEntityManager();

        try {
            em.getTransaction().begin();
            em.persist(user);
            em.getTransaction().commit();

            Query query = em.createQuery("select p from UserPO p", UserPO.class);
            List<UserPO> userList = query.getResultList();

            userList.stream().forEach((userPO) -> {
                System.out.println(userPO);
            });

            em.close();
            emf.close();
        } catch (Exception e) {

            if (em.getTransaction().isActive()) {
                em.getTransaction().rollback();
                em.close();
                emf.close();
            }

            errorPersist = e.getMessage();
            System.out.println("Error: " + errorPersist);
        }

        if (errorPersist == null || errorPersist.isEmpty()) {
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(
                    FacesMessage.SEVERITY_INFO,
                    "User register",
                    user.toString()));

            people = new PeoplePO();
            user = new UserPO();
        } else if (errorPersist.contains("uk_users_username")) {
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(
                    FacesMessage.SEVERITY_WARN,
                    "User register",
                    String.format("Username %s already exists", user.getUserName())));
        } else if (errorPersist.contains("uk_users_email")) {
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(
                    FacesMessage.SEVERITY_WARN,
                    "User register",
                    String.format("Email %s already exists", user.getEmail())));
        }
    }

}
