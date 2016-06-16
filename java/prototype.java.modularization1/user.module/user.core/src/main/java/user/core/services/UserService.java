package user.core.services;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import user.api.services.User;

@Stateless
public class UserService implements User {

    @PersistenceContext(unitName = "postgresql")
    private EntityManager em;

    @Override
    public void test() {
        System.out.println("Test user module core...");
        System.out.println(em.toString());
    }

}
