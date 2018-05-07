package ao.co.cligest.negocio;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import ao.co.cligest.entidades.Dosagem;

public class DosagemDAO {
	
private Connection con ;
	
	public void addDosagem(Dosagem pd)
	{
		String sql = "INSERT INTO tbldosagem (dosagem) VALUES (?)";
		try {
			con = Conexao.getConexao();
			PreparedStatement cp = con.prepareStatement(sql);
			cp.setString(1, pd.getDosagem());
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
	public List <Dosagem> buscarDosagem ()
	{
		List <Dosagem> lista = new ArrayList<Dosagem>();
		String sql = "SELECT * FROM tbldosagem where id_dosagem != 0 order by dosagem asc";
		try {
			con = Conexao.getConexao();
			 PreparedStatement preparador = con.prepareStatement(sql);
			 ResultSet rs = preparador.executeQuery();
			 while(rs.next())
			 {
				 Dosagem pd = new Dosagem();
				 pd.setId_dosagem(rs.getInt("id_dosagem"));
				 pd.setDosagem(rs.getString("dosagem"));
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
	public Dosagem edit_dosagem(String ed)
	{
		Dosagem bg = new Dosagem();
		String sql = "SELECT * FROM tbldosagem WHERE id_dosagem = ? and id_dosagem != 0";
		try {
			con = Conexao.getConexao();
			PreparedStatement preparador = con.prepareStatement(sql);
			 preparador.setString(1, ed);
			 ResultSet rs = preparador.executeQuery();
			 while(rs.next())
			 {
				 bg.setId_dosagem(rs.getInt("id_dosagem"));
				 bg.setDosagem(rs.getString("dosagem"));				 
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
	public void alt_dosagem(Dosagem gb)
	{
		String sql = "UPDATE tbldosagem SET dosagem =? WHERE id_dosagem = ?";
		try {
			con = Conexao.getConexao();
			PreparedStatement bg = con.prepareStatement(sql);
			
			bg.setString(1, gb.getDosagem());
			bg.setInt(2, gb.getId_dosagem());
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
	public int XequeDosagem(String termo)
	{
		int ok = 0;
		String sql = "SELECT * FROM tbldosagem WHERE dosagem Like ?";
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
			public void del_dosaggem (int id)
			{
				String sql = "DELETE FROM tbldosagem  WHERE id_dosagem=?";
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
