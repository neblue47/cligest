package ao.co.cligest.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import ao.co.cligest.entidades.Bancos;

public class BancosDAO {

	private Connection con ;
	
	public void cadastrarBanco(Bancos bc){
		String sql = "INSERT INTO tblbanco (banco,telefone,email,site) VALUES (?,?,?,?)";
		try {
			con = Conexao.getConexao();
			PreparedStatement cp = con.prepareStatement(sql);
			cp.setString(1, bc.getBanco());
			cp.setString(2, bc.getTelefone());
			cp.setString(3, bc.getEmail());
			cp.setString(4, bc.getSite());
			cp.execute();
			cp.close(); 
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		finally{
			try{
				con.close();
			}
			catch(SQLException ef){
				System.out.println("Erro finalizar: "+ef);
			}
		}
	}
	
	public List<Bancos> visualizarBancos() {
		List<Bancos> lista = new ArrayList<Bancos>();
		String sql = "SELECT * FROM tblbanco order by banco";
		try {
			Bancos pd = null;
			con = Conexao.getConexao();
			PreparedStatement preparador = con.prepareStatement(sql);
			ResultSet rs = preparador.executeQuery();
			while (rs.next()) {
				pd = new Bancos();
				pd.setId_banco(rs.getInt("id_banco"));
				pd.setBanco(rs.getString("banco"));
				pd.setTelefone(rs.getString("telefone"));
				pd.setEmail(rs.getString("email"));
				pd.setSite(rs.getString("site"));
				lista.add(pd);
			}
			preparador.close();
		} catch (SQLException e) {

			e.printStackTrace();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			try {
				con.close();
			} catch (SQLException ef) {
				System.out.println("Erro finalizar: " + ef);
			}
		}
		return lista;
	}
	public Bancos edit_banco(String ed)
	{
		Bancos bg = new Bancos();
		String sql = "SELECT * FROM tblbanco WHERE id_banco = ?";
		try {
			con = Conexao.getConexao();
			PreparedStatement preparador = con.prepareStatement(sql);
			 preparador.setString(1, ed);
			 ResultSet rs = preparador.executeQuery();
			 while(rs.next())
			 {
				 bg.setId_banco(rs.getInt("id_banco"));
				 bg.setBanco(rs.getString("banco"));
				 bg.setTelefone(rs.getString("telefone"));
				 bg.setEmail(rs.getString("email"));
				 bg.setSite(rs.getString("site"));
			 }
			 preparador.close();
		} catch (SQLException e) {			
			e.printStackTrace();
		}
		catch (Exception e) {
			e.printStackTrace();
		}
		finally{
			try{
				con.close();
			}
			catch(SQLException ef){
				System.out.println("Erro finalizar: "+ef);
			}
		}
		return bg;
	}
	
	public int XequeBanco(String termo)
	{
		int ok = 0;
		String sql = "SELECT * FROM tblbanco WHERE banco Like ?";
		try {
			con = Conexao.getConexao();
			PreparedStatement preparador = con.prepareStatement(sql);
			 preparador.setString(1, termo);
			 ResultSet rs = preparador.executeQuery();
			 if(rs.next())
			 {
				 ok = rs.getInt("id_banco");				 
			 }
			 preparador.close();
		} catch (SQLException e) {			
			e.printStackTrace();
		}
		catch (Exception e) {
			e.printStackTrace();
		}
		finally{
			try{
				con.close();
			}
			catch(SQLException ef){
				System.out.println("Erro finalizar: "+ef);
			}
		}
		return ok;
	}
	
	public int XequeBanco(String termo,String telefone,String email,String site)
	{
		int ok = 0;
		String sql = "SELECT * FROM tblbanco WHERE banco = ? and telefone =? and email =? and site =?";
		try {
			con = Conexao.getConexao();
			PreparedStatement preparador = con.prepareStatement(sql);
			 preparador.setString(1, termo);
			 preparador.setString(2, telefone);
			 preparador.setString(3, email);
			 preparador.setString(4, site);
			 ResultSet rs = preparador.executeQuery();
			 if(rs.next())
			 {
				 ok = 1;				 
			 }
			 preparador.close();
		} catch (SQLException e) {			
			e.printStackTrace();
		}
		catch (Exception e) {
			e.printStackTrace();
		}
		finally{
			try{
				con.close();
			}
			catch(SQLException ef){
				System.out.println("Erro finalizar: "+ef);
			}
		}
		return ok;
	}
	
//	5- Alterar servi�o
	public void alt_banco(Bancos bc)
	{
		String sql = "UPDATE tblbanco SET banco =?, telefone =?, email =?, site =? WHERE id_banco = ?";
		try {
			con = Conexao.getConexao();
			PreparedStatement bg = con.prepareStatement(sql);
			
			bg.setString(1, bc.getBanco());
			bg.setString(2, bc.getTelefone());
			bg.setString(3, bc.getEmail());
			bg.setString(4, bc.getSite());
			bg.setInt(5, bc.getId_banco());
			bg.execute();
			bg.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		finally{
			try{
				con.close();
			}
			catch(SQLException ef){
				System.out.println("Erro finalizar: "+ef);
			}
		}
	}
	public void del_banco(int id)
	{
		String sql = "DELETE FROM tblbanco  WHERE id_banco=?";
		try {
			con = Conexao.getConexao();
			 PreparedStatement preparador = con.prepareStatement(sql);
			 preparador.setInt(1, id);
			 preparador.execute();
			 preparador.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		finally{
			try{
				con.close();
			}
			catch(SQLException ef){
				System.out.println("Erro finalizar: "+ef);
			}
		}
	}
	
}
