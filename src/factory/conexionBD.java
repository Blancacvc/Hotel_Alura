package factory;

import java.sql.Connection;
import java.sql.SQLException;
import javax.sql.DataSource;
import com.mchange.v2.c3p0.ComboPooledDataSource;

public class conexionBD {
	public DataSource datasource;
	public conexionBD() {
	    ComboPooledDataSource comboPool = new ComboPooledDataSource();
	    comboPool.setJdbcUrl("jdbc:mysql://localhost:3306/hotel_alura");
	    comboPool.setUser("root");
	    comboPool.setPassword("PassMySQLBench");
	    
	    this.datasource = comboPool;
	}
	public Connection recuperarConexion() {
	    try {
	        return this.datasource.getConnection();
	    } catch (SQLException e) {
	        throw new RuntimeException(e);
	    }
	}
	
}

