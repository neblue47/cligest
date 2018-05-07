package ao.co.cligest.dao;
import java.sql.Connection;
import java.sql.SQLException;
import javax.naming.InitialContext;
import javax.sql.DataSource;

public class Conexao2 {

	public static Connection getConexao() throws Exception {
		InitialContext context = new InitialContext();
		DataSource ds = (DataSource) context.lookup("java:comp/env/jdbc/atgbanco");	
		try {
			return ds.getConnection(); 
		} 
		catch (SQLException e) {
			throw new Exception(e.getMessage());
		}
		
	}
}
