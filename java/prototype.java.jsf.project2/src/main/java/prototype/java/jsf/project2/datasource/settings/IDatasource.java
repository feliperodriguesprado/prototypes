package prototype.java.jsf.project2.datasource.settings;

import prototype.java.jsf.project2.datasource.connections.ConnectionJPA;

public interface IDatasource {

    ConnectionJPA getConnectionJPAPostgreSQL();
}
