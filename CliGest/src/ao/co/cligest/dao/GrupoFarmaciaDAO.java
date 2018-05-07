package ao.co.cligest.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import ao.co.cligest.entidades.GrupoFarmacia;


public class GrupoFarmaciaDAO {
	
	private Connection con;
	public void addGrupoFarmacia(GrupoFarmacia pd)
	{
		String sql = "INSERT INTO tblgrupofarmacia (nome_grupo) VALUES (?)";
		try {
			con = Conexao.getConexao();
			PreparedStatement cp = con.prepareStatement(sql);
			cp.setString(1, pd.getNome_grupo());
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
	public List <GrupoFarmacia> buscarGrupoFarmacia()
	{
		List <GrupoFarmacia> lista = new ArrayList<GrupoFarmacia>();
		String sql = "SELECT * FROM tblgrupofarmacia order by nome_grupo asc";
		try {
			con = Conexao.getConexao();
			 PreparedStatement preparador = con.prepareStatement(sql);
			 ResultSet rs = preparador.executeQuery();
			 while(rs.next())
			 {
				 GrupoFarmacia pd = new GrupoFarmacia();
				 pd.setId_grupo(rs.getInt("id_grupo"));
				 pd.setNome_grupo(rs.getString("nome_grupo"));
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
	public GrupoFarmacia edit_grupoFarmacia(String ed)
	{
		GrupoFarmacia bg = new GrupoFarmacia();
		String sql = "SELECT * FROM tblgrupofarmacia WHERE id_grupo = ?";
		try {
			con = Conexao.getConexao();
			PreparedStatement preparador = con.prepareStatement(sql);
			 preparador.setString(1, ed);
			 ResultSet rs = preparador.executeQuery();
			 while(rs.next())
			 {
				 bg.setId_grupo(rs.getInt("id_grupo"));
				 bg.setNome_grupo(rs.getString("nome_grupo"));				 
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
	public void alt_grupoFarmacia(GrupoFarmacia gb)
	{
		String sql = "UPDATE tblgrupofarmacia SET nome_grupo =? WHERE id_grupo = ?";
		try {
			con = Conexao.getConexao();
			PreparedStatement bg = con.prepareStatement(sql);
			
			bg.setString(1, gb.getNome_grupo());
			bg.setInt(2, gb.getId_grupo());
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
	public int XequeGrupoFarmacia(String termo)
	{
		int ok = 0;
		String sql = "SELECT * FROM tblgrupofarmacia WHERE nome_grupo Like ?";
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
			public void del_grupoFarmacia(int id)
			{
				String sql = "DELETE FROM tblgrupofarmacia  WHERE id_grupo=?";
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
