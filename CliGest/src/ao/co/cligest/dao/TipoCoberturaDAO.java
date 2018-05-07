package ao.co.cligest.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import ao.co.cligest.entidades.Agenda;
import ao.co.cligest.entidades.TipoCobertura;

public class TipoCoberturaDAO {
	
	private Connection con = null;
	PreparedStatement prd = null;
	ResultSet rs =null;
	
//1-Mostrar os dados do Servi�o
	public List<TipoCobertura> buscarTipoCobertura()
	{
	List <TipoCobertura> lista = new ArrayList<TipoCobertura>();
	String sql = "SELECT * FROM tbltipodecobertura";
	try{
		con = Conexao.getConexao();
	prd = con.prepareStatement(sql);
	rs = prd.executeQuery();
	while(rs.next())
	 {
		TipoCobertura pn = new TipoCobertura();
		pn.setId_tp_cobertura(rs.getInt("id_tipo_dcobertura"));
		pn.setTipo_cobertura(rs.getString("nome_do_plano"));
//		pn.setFK_benef_saude(rs.getInt("FK_beneficio_dsaude"));
	    lista.add(pn);
	    }
	  } catch (SQLException e) {
	   // TODO Auto-generated catch block
	   e.printStackTrace();
	  }
	catch (Exception e) {
		// TODO: handle exception
	}
	finally{
			try{
				rs.close();
				prd.close();
				con.close();
			}catch(Throwable e){
				System.out.println("Erro ao fechar as conex�o");
				e.printStackTrace();
			}
		}
		return lista;
	}
	
	//Salvar na tabela tblbeneficio
    public void inseriTipoCobertura(TipoCobertura ex)
	{
		String sql = "insert into tbltipodecobertura (tipo_dcobertura) values(?)";
		try {
			con = Conexao.getConexao();
			 PreparedStatement preparador = con.prepareStatement(sql);
			 preparador = con.prepareStatement(sql);
			 preparador.setString(1, ex.getTipo_cobertura());
			 preparador.execute();
			 preparador.close();
			
		} catch (SQLException e) {
			throw new RuntimeException(e);
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

    public void alt_tipoCobertura(TipoCobertura ag)
	{
		String sql = "UPDATE tbltipodecobertura SET tipo_dcobertura =? WHERE id_tipo_dcobertura = ?";
		try {
			con = Conexao.getConexao();
			PreparedStatement preparador = con.prepareStatement(sql);
			
			preparador.setString(1, ag.getTipo_cobertura());
			preparador.setInt(2, ag.getId_tp_cobertura());;
			preparador.execute();
			preparador.close();
			System.out.println("Alteracao com sucesso..."); 
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
    
    public TipoCobertura edit_tipoCobertura(int ed)
	{
    	TipoCobertura srv = new TipoCobertura();
		String sql = "SELECT * FROM tbltipodecobertura WHERE id_tipo_dcobertura = ?";
		try {
			con = Conexao.getConexao();
			prd = con.prepareStatement(sql);
			prd.setInt(1, ed);
			rs = prd.executeQuery();
			 while(rs.next())
			 {
				 srv.setId_tp_cobertura(rs.getInt("id_tipo_dcobertura"));
				 srv.setTipo_cobertura(rs.getString("tipo_dcobertura"));
			 }
			 System.out.println("Encontrado com sucesso");
			 prd.close();
		} catch (SQLException e) {
			
			e.printStackTrace();
		}
		catch (Exception e) {
			// TODO: handle exception
		}
		finally{
			try{
				rs.close();
				prd.close();
				con.close();
			}catch(Throwable e){
				System.out.println("Erro ao fechar as conex�o");
				e.printStackTrace();
			}
		}
		return srv;
	}
    
    public void del_tipoCobertura(int id)
	{
		String sql = "DELETE FROM tbltipodecobertura  WHERE id_tipo_dcobertura=?";
		try {
			con = Conexao.getConexao();
			 PreparedStatement preparador = con.prepareStatement(sql);
			 preparador.setInt   (1, id);
			 preparador.execute();
			 preparador.close();
			 System.out.println("Exclusao com sucesso..."); 
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
}
