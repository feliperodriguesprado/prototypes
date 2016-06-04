package user.api;

import javax.ejb.Remote;

@Remote
public interface User {

    String test();
    
}
