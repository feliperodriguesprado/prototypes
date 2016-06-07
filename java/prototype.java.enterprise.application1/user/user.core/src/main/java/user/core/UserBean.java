package user.core;

import javax.ejb.Stateless;
import user.api.User;

@Stateless
public class UserBean implements User {

    @Override
    public void test() {
        System.out.println("Test User Bean...");
    }

}
