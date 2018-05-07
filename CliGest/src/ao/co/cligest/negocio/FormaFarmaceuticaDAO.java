package ao.co.cligest.negocio;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import ao.co.cligest.entidades.Embalagem;
import ao.co.cligest.entidades.FormaFarmaceutica;

import java.sql.Connection;

public class FormaFarmaceuticaDAO {

	private Connection con;
	
	public void registarFormaf(FormaFarmaceutica ff)
	{
		String sql = "INSERT INTO tblformafarmaceutica (forma_farmaceutica) VALUES (?)";
		try {
			con = Conexao.getConexao();
			PreparedStatement cp = con.prepareStatement(sql);
			cp.setString(1, ff.getForma_farmaceutica());
			System.out.println("Dentro do metodo valor: "+ff.getForma_farmaceutica());
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
	
//	2-Visualizar produtos
	public List <FormaFarmaceutica> buscarFormaFarmaceutica ()
	{
		List <FormaFarmaceutica> lista = new ArrayList<FormaFarmaceutica>();
		String sql = "SELECT * FROM tblformafarmaceutica WHERE id_forma_farmaceutica != 0 order by forma_farmaceutica asc";
		try {
			con = Conexao.getConexao();
			 PreparedStatement preparador = con.prepareStatement(sql);
			 ResultSet rs = preparador.executeQuery();
			 while(rs.next())
			 {
				 FormaFarmaceutica pd = new FormaFarmaceutica();
				 pd.setId_forma_farmaceutica(rs.getInt("id_forma_farmaceutica"));
				 pd.setForma_farmaceutica(rs.getString("forma_farmaceutica"));
				 lista.add(pd);
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
	
//	  6-Editar servi�o
	public FormaFarmaceutica edit_formaFarmaceutica(String ed)
	{
		FormaFarmaceutica bg = new FormaFarmaceutica();
		String sql = "SELECT * FROM tblformafarmaceutica WHERE id_forma_farmaceutica = ? and id_forma_farmaceutica != 0";
		try {
			con = Conexao.getConexao();
			PreparedStatement preparador = con.prepareStatement(sql);
			 preparador.setString(1, ed);
			 ResultSet rs = preparador.executeQuery();
			 while(rs.next())
			 {
				 bg.setId_forma_farmaceutica(rs.getInt("id_forma_farmaceutica"));
				 bg.setForma_farmaceutica(rs.getString("forma_farmaceutica"));				 
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
		return bg;
	}
	
//	5- Alterar servi�o
	public void alt_formaFarmaceuica(FormaFarmaceutica gb)
	{
		String sql = "UPDATE tblformafarmaceutica SET forma_farmaceutica =? WHERE id_forma_farmaceutica = ?";
		try {
			con = Conexao.getConexao();
			PreparedStatement bg = con.prepareStatement(sql);
			
			bg.setString(1, gb.getForma_farmaceutica());
			bg.setInt(2, gb.getId_forma_farmaceutica());
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
	public int XequeFormaFarmaceutica(String termo)
	{
		int ok = 0;
		String sql = "SELECT * FROM tblformafarmaceutica WHERE forma_farmaceutica Like ?";
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
	//3-Eliminar Servi�o
			public void del_formaFarmaceutica(int id)
			{
				String sql = "DELETE FROM tblformafarmaceutica  WHERE id_forma_farmaceutica=?";
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
