package ao.co.cligest.negocio;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import ao.co.cligest.entidades.ConfNomeFactura;

public class ConfNomeFacturaDAO {
	
private Connection con ;
	
	public List<ConfNomeFactura> configFacturaMostrar ()
	{
		List <ConfNomeFactura> listaMostrar = new ArrayList<ConfNomeFactura>();
		String sql = "SELECT * FROM tblconfigurarnomefactura LIMIT 0,1";
		try {
			 con = Conexao.getConexao();
			 PreparedStatement preparador = con.prepareStatement(sql);
			 ResultSet menor = preparador.executeQuery();
			 while(menor.next())
			 {
				 ConfNomeFactura fun = new ConfNomeFactura();  
				 fun.setId_nome_factura(menor.getInt("id_nome_factura"));				
				 fun.setConfig_nome_factura(menor.getString("config_nome_factura"));
				 fun.setData(menor.getString("data"));
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
	
	
	

	public List<ConfNomeFactura> configFacturaMostraredit (String num)
	{
		ConfNomeFactura fun = null;
		
		List <ConfNomeFactura> listaMostrar = new ArrayList<ConfNomeFactura>();
		String sql = "SELECT * FROM tblconfigurarnomefactura where id_nome_factura like ?";
		try {
			 con = Conexao.getConexao();
			 PreparedStatement preparador = con.prepareStatement(sql);
			 preparador.setString(1, num);
			 ResultSet menor = preparador.executeQuery();
			 if(menor.next())
			 {
				 fun = new ConfNomeFactura();
				 fun.setId_nome_factura(menor.getInt("id_nome_factura"));				 
				 fun.setConfig_nome_factura(menor.getString("config_nome_factura"));
				 fun.setData(menor.getString("data"));
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
	
	
	
	public void up_config_facturaModificar(ConfNomeFactura fun)
	{
		String sql = "UPDATE tblconfigurarnomefactura SET config_nome_factura=? WHERE id_nome_factura=?";
		try {
			con = Conexao.getConexao();
			PreparedStatement agend = con.prepareStatement(sql);			
			
			agend.setString(1, fun.getConfig_nome_factura());
			agend.setInt(2, fun.getId_nome_factura());
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
	
	// Metodo Para Configurar Numero da Encomenda
	
	public ConfNomeFactura configNomEncomenda() {
		ConfNomeFactura num = new ConfNomeFactura();
		String sql1 = "SELECT count(*) as total FROM tblnumerodaencomenda";
		String sql2 = "SELECT config_nome_factura as nomEnc FROM tblconfigurarnomefactura";
		try {
			con = Conexao.getConexao();
			PreparedStatement preparador = con.prepareStatement(sql1);
			ResultSet rs = preparador.executeQuery();
			if (rs.next()) {
				num.setNumEnco(rs.getInt("total")+1);
			}
			preparador = con.prepareStatement(sql2);
			ResultSet rss = preparador.executeQuery();
			if (rss.next()) {
				num.setConfig_nome(rss.getString("nomEnc"));
			}
			preparador.close();
		} catch (SQLException e) {
			e.printStackTrace();
		} catch (Exception e) {
			System.out.println(e);
		} finally {
			try {
				con.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		return num;
	}
	
	public ConfNomeFactura configNumDiferenca() {
		ConfNomeFactura num = new ConfNomeFactura();
		String sql1 = "SELECT count(*) as total FROM tbldiferencanoinventario";
		String sql2 = "SELECT config_nome_factura as nomEnc FROM tblconfigurarnomefactura";
		try {
			con = Conexao.getConexao();
			PreparedStatement preparador = con.prepareStatement(sql1);
			ResultSet rs = preparador.executeQuery();
			if (rs.next()) {
				num.setNumEnco(rs.getInt("total")+1);
			}
			preparador = con.prepareStatement(sql2);
			ResultSet rss = preparador.executeQuery();
			if (rss.next()) {
				num.setConfig_nome(rss.getString("nomEnc"));
			}
			preparador.close();
		} catch (SQLException e) {
			e.printStackTrace();
		} catch (Exception e) {
			System.out.println(e);
		} finally {
			try {
				con.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		return num;
	}

}
