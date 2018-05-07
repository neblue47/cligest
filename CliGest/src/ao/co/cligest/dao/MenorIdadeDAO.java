package ao.co.cligest.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import ao.co.cligest.entidades.*;

public class MenorIdadeDAO {
	
	private Connection con;
	
	public List<MenorIdade> menorIdadeMostrar ()
	{
		List <MenorIdade> listaMostrar = new ArrayList<MenorIdade>();
		String sql = "SELECT * FROM tblconfigurarmenor LIMIT 0,1";
		try {
			con = Conexao.getConexao();
			 PreparedStatement preparador = con.prepareStatement(sql);
			 ResultSet menor = preparador.executeQuery();
			 while(menor.next())
			 {
				 MenorIdade fun = new MenorIdade();  
				 fun.setId_configurar_menor(menor.getInt("id_configurar_menor"));
				 fun.setData(menor.getString("data"));
				 fun.setIdade(menor.getInt("idade_maxima"));
				 fun.setPaga_maxima(menor.getInt("getPaga_maxima"));
				 fun.setFK_funcionario(menor.getInt("FK_funcionario"));
				 listaMostrar.add(fun);
			 }
			 preparador.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		catch (Exception e) {
			// TODO: handle exception
		}
		finally {
			try {
				con.close();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		return listaMostrar;
	}
	
	
	

	public List<MenorIdade> menorIdadeMostraredit (String num)
	{
		MenorIdade fun = null;
		
		List <MenorIdade> listaMostrar = new ArrayList<MenorIdade>();
		String sql = "SELECT * FROM tblconfigurarmenor where id_configurar_menor like ?";
		try {
			con = Conexao.getConexao();
			 PreparedStatement preparador = con.prepareStatement(sql);
			 preparador.setString(1, num);
			 ResultSet menor = preparador.executeQuery();
			 if(menor.next())
			 {
				  fun = new MenorIdade();
				 fun.setId_configurar_menor(menor.getInt("id_configurar_menor"));
				 fun.setData(menor.getString("data"));
				 fun.setIdade(menor.getInt("idade_maxima"));
				 fun.setPaga_maxima(menor.getInt("Paga_maxima"));
				 fun.setFK_funcionario(menor.getInt("FK_funcionario"));
				 listaMostrar.add(fun);
			 }
			 preparador.close();
		} catch (SQLException e) {

			e.printStackTrace();
		}
		catch (Exception e) {
			// TODO: handle exception
		}
		finally {
			try {
				con.close();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		return listaMostrar;
	}
	
	
	
	public void up_menorIdadeModificar(MenorIdade fun)
	{
		String sql = "UPDATE tblconfigurarmenor SET paga_ou_nao=?, idade_maxima=? WHERE id_configurar_menor=?";
		try {
			con = Conexao.getConexao();
			PreparedStatement agend = con.prepareStatement(sql);
			agend.setInt(1, fun.getPaga_maxima());
			agend.setInt(2, fun.getIdade());
			agend.setInt(3, fun.getId_configurar_menor());
			agend.execute();
			agend.close();
			System.out.println("ALTERACAO com sucesso..."); 
		} catch (SQLException e) {
			e.printStackTrace();
		}
		catch (Exception e) {
			// TODO: handle exception
		}
		finally {
			try {
				con.close();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}
	//--------------------------------------------------------
		//------------Esta parte é para as radio buttons
		public String checkedcmzero(int cmzero){
			String check=null;
			if(cmzero == 0){
			   check = "checked = 'checked'";
			}
			return check;
		}
		
		public String checkedcmum(int cmum){
			String check=null;
			if(cmum == 1){
			   check = "checked = 'checked'";
			}
			return check;
		}

}
