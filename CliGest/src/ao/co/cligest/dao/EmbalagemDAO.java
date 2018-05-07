package ao.co.cligest.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import ao.co.cligest.entidades.Embalagem;

public class EmbalagemDAO {
	
	private Connection con ;
	
	public void addEmbalagem(Embalagem pd)
	{
		String sql = "INSERT INTO tblembalagem (embalagem) VALUES (?)";
		try {
			con = Conexao.getConexao();
			PreparedStatement cp = con.prepareStatement(sql);
			cp.setString(1, pd.getValor_embalagem());
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
	public List <Embalagem> buscarEmbalagem ()
	{
		List <Embalagem> lista = new ArrayList<Embalagem>();
		String sql = "SELECT * FROM tblembalagem order by embalagem asc";
		try {
			con = Conexao.getConexao();
			 PreparedStatement preparador = con.prepareStatement(sql);
			 ResultSet rs = preparador.executeQuery();
			 while(rs.next())
			 {
				 Embalagem pd = new Embalagem();
				 pd.setId_embalagem(rs.getInt("id_embalagem"));
				 pd.setValor_embalagem(rs.getString("embalagem"));
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
	public Embalagem edit_embalagem(String ed)
	{
		Embalagem bg = new Embalagem();
		String sql = "SELECT * FROM TBLEMBALAGEM WHERE id_embalagem = ?";
		try {
			con = Conexao.getConexao();
			PreparedStatement preparador = con.prepareStatement(sql);
			 preparador.setString(1, ed);
			 ResultSet rs = preparador.executeQuery();
			 while(rs.next())
			 {
				 bg.setId_embalagem(rs.getInt("id_embalagem"));
				 bg.setValor_embalagem(rs.getString("embalagem"));				 
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
	public void alt_embalagem(Embalagem gb)
	{
		String sql = "UPDATE TBLEMBALAGEM SET embalagem =? WHERE id_embalagem = ?";
		try {
			con = Conexao.getConexao();
			PreparedStatement bg = con.prepareStatement(sql);
			
			bg.setString(1, gb.getValor_embalagem());
			bg.setInt(2, gb.getId_embalagem());
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
	public int XequeEmbalagem(String termo)
	{
		int ok = 0;
		String sql = "SELECT * FROM TBLEMBALAGEM WHERE embalagem Like ?";
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
			public void del_embalagem (int id)
			{
				String sql = "DELETE FROM TBLEMBALAGEM  WHERE id_embalagem=?";
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
