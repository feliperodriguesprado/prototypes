package prototype.java.jsf.project2.controllers;

import java.io.Serializable;
import javax.annotation.PostConstruct;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.faces.view.ViewScoped;
import javax.inject.Named;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import prototype.java.jsf.project2.models.PeopleModel;
import prototype.java.jsf.project2.models.UserModel;

@ViewScoped
@Named
public class UserController implements Serializable {

    private static final long serialVersionUID = 1L;

    private PeopleModel people;
    private UserModel user;

    @PostConstruct
    public void init() {
        people = new PeopleModel();
        user = new UserModel();
    }

    public UserModel getUser() {
        return user;
    }

    public void save() {

        user.setPeople(people);
        people.setUser(user);
        

        EntityManagerFactory emf = Persistence.createEntityManagerFactory("postgresql");
        EntityManager em = emf.createEntityManager();
//        EntityTransaction transaction = em.getTransaction();
//
//        transaction.begin();
//        em.persist(people);
//        transaction.commit();
        em.close();
        emf.close();

        FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "Customer OK", user.toString()));
    }

}
