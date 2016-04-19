package prototype.java.jsf.project2.datasource.settings;

import javax.enterprise.context.ApplicationScoped;
import prototype.java.jsf.project2.datasource.connections.ConnectionJPAPostgreSQL;
import prototype.java.jsf.project2.datasource.connections.ConnectionJPA;

@ApplicationScoped
public class DatasourcePostgreSQL implements IDatasource {

    @Override
    public ConnectionJPA getConnectionJPAPostgreSQL() {
        return new ConnectionJPAPostgreSQL();
    }
}
