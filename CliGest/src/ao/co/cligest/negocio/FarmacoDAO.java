package ao.co.cligest.negocio;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import ao.co.cligest.entidades.Cid;
import ao.co.cligest.entidades.Farmacos;

public class FarmacoDAO {

	private Connection con ;
	
	public List<Farmacos> buscarFarmacos(String aux)
	{
		List<Farmacos> listar = new ArrayList<Farmacos>();
		
		String sql = "SELECT * FROM vwfarmacos where nome_comercial like ?  ";
		
		try {
			con = Conexao.getConexao();
			PreparedStatement preparador = con.prepareStatement(sql);
			preparador.setString(1, aux+"%");
			ResultSet rs = preparador.executeQuery();
		
			while (rs.next()){
				Farmacos farLis = new Farmacos();			
				farLis.setId_farmaco(rs.getInt("id_produto"));
				farLis.setNome_cm(rs.getString("nome_comercial"));
				listar .add(farLis);
			}
			preparador.close();
			
    	} catch (SQLException e) {
    		e.printStackTrace();
    	}
		catch (Exception e) {	System.out.println(e); }
		finally{
			try{
				con.close();
			}
			catch(SQLException ef){
				System.out.println("Erro finalizar: "+ef);
			}
		}
		return listar ;
	}
	
	public Farmacos buscarFarmaco(String aux)
	{
	 
		Farmacos farLis = null;			
		
		String sql = "SELECT * FROM vwfarmacos where nome_comercial like ?";
		
		try {
			con = Conexao.getConexao();
			PreparedStatement preparador = con.prepareStatement(sql);
			preparador.setString(1, aux);
			ResultSet rs = preparador.executeQuery();
		
			if (rs.next()){
				farLis = new Farmacos();
				farLis.setId_farmaco(rs.getInt("id_produto"));
				farLis.setNome_cm(rs.getString("nome_comercial"));
				farLis.setDosagem(rs.getString("dosagem"));
			}
			preparador.close();
			
    	} catch (SQLException e) {
    		e.printStackTrace();
    	}
		catch (Exception e) {	System.out.println(e); }
		finally{
			try{
				con.close();
			}
			catch(SQLException ef){
				System.out.println("Erro finalizar: "+ef);
			}
		}
		return farLis;
	}

}
