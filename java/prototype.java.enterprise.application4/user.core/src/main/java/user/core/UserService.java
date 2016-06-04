package user.core;

import javax.ejb.Stateless;
import user.api.User;

@Stateless
public class UserService implements User {

    @Override
    public String test() {
        return "Test EJB Core User Service.";
    }

}
