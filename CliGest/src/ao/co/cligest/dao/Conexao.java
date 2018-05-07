package ao.co.cligest.dao;
import java.sql.*;

import ao.co.cligest.util.AdressCredencias;
import ao.co.cligest.util.MetodosBuscas;

	public class Conexao 
	{
		public static Connection getConexao()
		{
			Connection conn = null;
			try 
			{
				AdressCredencias cred =  MetodosBuscas.getCredencia();
				String url  = "jdbc:mysql://"+cred.getUrl()+"/"+cred.getBdname();
				String user = cred.getUser();
				String pass = cred.getPass();
				Class.forName("org.gjt.mm.mysql.Driver");
				conn = DriverManager.getConnection(url,user,pass);
				//System.out.print("Conexao efectuada com sucesso...");
			} catch (SQLException e){
				String aux = e.getMessage().toString()+"\n"+e.getLocalizedMessage();
				MetodosBuscas.logs("erroConexao.txt",aux);
				System.out.print("Conexao nao efectuada com sucesso..."+e.getMessage());
			} catch (ClassNotFoundException e){
				String aux = e.getMessage().toString()+"\n"+e.getLocalizedMessage();
				MetodosBuscas.logs("erroConexao.txt",aux);
			}
			return conn;
		}	
}
