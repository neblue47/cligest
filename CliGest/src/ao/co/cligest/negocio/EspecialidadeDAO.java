package ao.co.cligest.negocio;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import ao.co.cligest.entidades.*;

public class EspecialidadeDAO {
	
	private Connection con ;

	 //1-INSERIR ESPECIALIDADE
	public void EspecInserir(Especialidade esp)
	{
		String sql = "INSERT INTO TBLESPECIALIDADE (especialidade) VALUES (?)";
		try {
			con = Conexao.getConexao();
			PreparedStatement espcc = con.prepareStatement(sql);
			espcc.setString(1, esp.getEspec());
			espcc.execute();
			espcc.close();
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

//	2-Visualizar os dados do especialidade
	public List<Especialidade> BuscaEspec ()
	{
		List <Especialidade> lista = new ArrayList<Especialidade>();
		String sql = "SELECT * FROM TBLESPECIALIDADE ORDER BY ID_ESPECIALIDADE = 17 DESC, ESPECIALIDADE ASC";
		try {
			con = Conexao.getConexao();
			 PreparedStatement preparador = con.prepareStatement(sql);
			 ResultSet rs = preparador.executeQuery();
			 while(rs.next())
			 {
				 Especialidade espcd = new Especialidade();
				 espcd.setId_espec(rs.getInt("id_especialidade"));
				 espcd.setEspec(rs.getString("especialidade"));
				 lista.add(espcd);
			 }
			 preparador.close();
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
		return lista;
	}
//	3-Mostrar especialidade  modificar
	public String buscaEspMod(int cod)
	{
		String lista = "";
		String sql = "SELECT * FROM TBLESPECIALIDADE where id_especialidade =?";
		try {
			con = Conexao.getConexao();
			 PreparedStatement ent = con.prepareStatement(sql);
			 ent.setInt(1, cod);
			 ResultSet rs = ent.executeQuery();
			 if(rs.next())
				 lista = rs.getString("especialidade");
			 ent.close();
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
		return lista;
	}
//	4-Eliminar especialidade
	public void EliminarEspec (int id)
	{
		String sql = "DELETE FROM TBLESPECIALIDADE  WHERE id_especialidade=?";
		try {
			con = Conexao.getConexao();
			 PreparedStatement preparador = con.prepareStatement(sql);
			 preparador.setInt   (1, id);
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
	public int XequeEspec(String termo)
	{
		int ok = 0;
		String sql = "SELECT * FROM TBLESPECIALIDADE WHERE especialidade Like ?";
		try {
			con = Conexao.getConexao();
			PreparedStatement preparador = con.prepareStatement(sql);
			 preparador.setString(1, termo);
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
//	5-Pesquisar Especialidade
	public List <Especialidade> EspecPesquisar (String nome)
	{
		List <Especialidade> lista = new ArrayList<Especialidade>();
		String sql = "SELECT * FROM TBLESPECIALIDADE WHERE especialidade LIKE ?";
		try {
			con = Conexao.getConexao();
			 PreparedStatement preparador = con.prepareStatement(sql);
			 preparador.setString(1, nome);
			 ResultSet rs = preparador.executeQuery();
			 while(rs.next())
			 {
				 Especialidade espcc = new Especialidade();
				 espcc.setEspec(rs.getString("especialidade"));
				 lista.add(espcc);
			 }
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
		return lista;
	}
	
//	6- Alterar Especialidade
	public void alt_espec(Especialidade ag)
	{
		String sql = "UPDATE TBLESPECIALIDADE SET especialidade =? WHERE id_especialidade = ?";
		try {
			con = Conexao.getConexao();
			PreparedStatement espC = con.prepareStatement(sql);
			
			espC.setString(1, ag.getEspec());
			espC.setInt(2, ag.getId_espec());
			espC.execute();
			espC.close();
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
//	7-salvar especialidade
	public void salvar(Especialidade es)
	{
		if(es.getId_espec()!=0)
		{
			alt_espec(es);
		}
		else
		{
			EspecInserir(es);
		}
	}
//	  8-Editar especialidade
	public Especialidade edit_espec(String ed)
	{
		Especialidade fun = new Especialidade();
		String sql = "SELECT * FROM TBLESPECIALIDADE WHERE id_especialidade = ?";
		try {
			con = Conexao.getConexao();
			PreparedStatement preparador = con.prepareStatement(sql);
			 preparador.setString(1, ed);
			 ResultSet rs = preparador.executeQuery();
			 while(rs.next())
			 {
				 fun.setId_espec(rs.getInt("id_especialidade"));
				 fun.setEspec(rs.getString("especialidade"));	 				 
			 }
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
		return fun;
	}
}
