package prototype.java.jsf.project2.services;

import java.util.Set;
import prototype.java.jsf.project2.models.UserPO;

public interface IUserService {

    public UserPO create(UserPO user) throws Exception;
    
    public Set<UserPO> getAll () throws Exception;

}
