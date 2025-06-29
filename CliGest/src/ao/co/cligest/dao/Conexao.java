package ao.co.cligest.dao;
import java.sql.*;

import ao.co.cligest.util.AdressCredencias;
import ao.co.cligest.util.MetodosBuscas;

	public class Conexao 
	{
		private static Connection conn;
		private static String url  = "jdbc:mysql://localhost/gestclinicabd";
		private static String user = "root";
		private static String pass = "sistema47";
		
		public static Connection getLocalConection()
		{
			Connection conn2 = null;
			try 
			{
				AdressCredencias cred =  MetodosBuscas.getCredencia();
				String url  = "jdbc:mysql://"+cred.getUrl()+"/"+cred.getBdname();
				String user = cred.getUser();
				String pass = cred.getPass();
				Class.forName("org.gjt.mm.mysql.Driver");
				conn2 = DriverManager.getConnection(url,user,pass);
				//System.out.print("Conexao efectuada com sucesso...");
			} catch (SQLException e){
				String aux = e.getMessage().toString()+"\n"+e.getLocalizedMessage();
				MetodosBuscas.logs("erroConexao.txt",aux);
				System.out.print("Conexao nao efectuada com sucesso..."+e.getMessage());
			} catch (ClassNotFoundException e){
				String aux = e.getMessage().toString()+"\n"+e.getLocalizedMessage();
				MetodosBuscas.logs("erroConexao.txt",aux);
			}
			return conn2;
	  }	
		
		public static Connection getConexao(){
			 
			try {
				//Class.forName("org.gjt.mm.mysql.Driver"); 
				Class.forName("com.mysql.cj.jdbc.Driver"); 
				conn = DriverManager.getConnection(url,user,pass);
			} catch (SQLException e) {
				System.out.print("Conexao nao efectuada com sucesso..."+e.getMessage());
				e.printStackTrace();
			} catch (ClassNotFoundException e) {
				 
				e.printStackTrace();
			}
			
		 
			return conn;
		}
}
