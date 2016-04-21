package prototype.java.jsf.project2.user.core.dao;

import java.util.List;
import prototype.java.jsf.project2.datasource.connections.ConnectionJPA;
import prototype.java.jsf.project2.user.core.models.po.UserPO;

public interface IUserDAO {

    void setConnectionJPA(ConnectionJPA connectionJPA) throws Exception;

    UserPO create(UserPO user) throws Exception;

    UserPO getById(long id) throws Exception;

    UserPO update(UserPO user) throws Exception;

    void delete(long id) throws Exception;

    List<UserPO> getAll() throws Exception;

}
