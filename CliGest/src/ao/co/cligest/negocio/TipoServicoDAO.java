package ao.co.cligest.negocio;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import ao.co.cligest.entidades.*;

public class TipoServicoDAO {
	
	private Connection con;
 
	 //1-Inserir os dados tipo de servi�o
		public void TipoServInserir(TipoServico svc)
		{
			String sql = "INSERT INTO TBLTIPODESERVICO (FK_especialidade, tipo_de_servico) VALUES (?,?)";
			try {
				con = Conexao.getConexao();
				PreparedStatement serv = con.prepareStatement(sql);
				serv.setInt(1, svc.getFK_especialidade());
				serv.setString(2, svc.getTipo_de_servico());
				serv.execute();
				serv.close();
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
		public int XequeTipoServico(String termo)
		{
			int ok = 0;
			String sql = "SELECT * FROM TBLTIPODESERVICO WHERE tipo_de_servico Like ?";
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
//		2-Visualizar os dados do tipo de servico
		public List<TipoServico> BuscarTipoServico ()
		{
			List <TipoServico> lista = new ArrayList<TipoServico>();
			String sql = "SELECT * from vwtiposervicos order by tipo_de_servico asc";
			try {
				con = Conexao.getConexao();
				 PreparedStatement preparador = con.prepareStatement(sql);
				 ResultSet rs = preparador.executeQuery();
				 while(rs.next())
				 {
					 TipoServico tipser = new TipoServico();
					 tipser.setId_tipo_servico(rs.getInt("id_tipo_de_servico"));
					 tipser.setEspecialidade(rs.getString("especialidade"));
					 tipser.setTipo_de_servico(rs.getString("tipo_de_servico"));
					 lista.add(tipser);
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
		public List<TipoServico> BuscarTipoServicoDependente (int cod)
		{
			List <TipoServico> lista = new ArrayList<TipoServico>();
			String sql = "SELECT * from vwtiposervicos where id_especialidade = ? order by (id_tipo_de_servico)";
			try {
				con = Conexao.getConexao();
				 PreparedStatement preparador = con.prepareStatement(sql);
				 preparador.setInt(1, cod);
				 ResultSet rs = preparador.executeQuery();
				 while(rs.next())
				 {
					 TipoServico tipser = new TipoServico();
					 tipser.setId_tipo_servico(rs.getInt("id_tipo_de_servico"));
					 tipser.setFK_especialidade(rs.getInt("id_especialidade"));
					 tipser.setEspecialidade(rs.getString("especialidade"));
					 tipser.setTipo_de_servico(rs.getString("tipo_de_servico"));
					 lista.add(tipser);
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
//		3- Mostrar especialidade
		public List <Especialidade> BuscarTipoS()
		{
			List <Especialidade> lista = new ArrayList<Especialidade>();
			String sql = "SELECT * FROM TBLESPECIALIDADE";
			try{
				con = Conexao.getConexao();
				PreparedStatement preparador = con.prepareStatement(sql);
				ResultSet espc = preparador.executeQuery();
				while(espc.next())
				 {
					Especialidade esP = new Especialidade();
					esP.setId_espec(espc.getInt("id_especialidade"));
					esP.setEspec(espc.getString("especialidade"));
				     lista.add(esP);
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
		
//		4-Eliminar Servi�o
		public void tServicoElim (int id)
		{
			String sql = "DELETE FROM TBLTIPODESERVICO  WHERE id_tipo_de_servico=?";
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
		
//		5-Pesquisar tipo de servi�o
		public List <TipoServico> PesqTipoServ (String nome)
		{
			List <TipoServico> lista = new ArrayList<TipoServico>();
			String sql = "SELECT * FROM vwservico WHERE tipo_de_servico LIKE ?";
			try {
				con = Conexao.getConexao();
				 PreparedStatement preparador = con.prepareStatement(sql);
				 preparador.setString(1, nome);
				 ResultSet rs = preparador.executeQuery();
				 while(rs.next())
				 {
					 TipoServico ts = new TipoServico();
					 ts.setTipo_de_servico(rs.getString("tipo_de_servico"));
					 ts.setEspecialidade(rs.getString("especialidade"));
					 lista.add(ts);
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
		
//		6- Alterar servi�o
		public void alt_tservico(TipoServico srv)
		{
			String sql = "UPDATE TBLTIPODESERVICO SET tipo_de_servico =?, FK_especialidade =? WHERE id_tipo_de_servico = ?";
			try {
				con = Conexao.getConexao();
				PreparedStatement serC = con.prepareStatement(sql);
				
				serC.setString(1, srv.getTipo_de_servico());
				serC.setInt(2, srv.getFK_especialidade());
				serC.setInt(3, srv.getId_tipo_servico());
				serC.execute();
				serC.close();
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
		
//		 7-Editar servi�o
		public TipoServico edit_tservico(String ed)
		{
			TipoServico tsrv = new TipoServico();
			String sql = "SELECT * FROM TBLTIPODESERVICO WHERE id_tipo_de_servico = ?";
			try {
				con = Conexao.getConexao();
				PreparedStatement preparador = con.prepareStatement(sql);
				 preparador.setString(1, ed);
				 ResultSet rs = preparador.executeQuery();
				 while(rs.next())
				 {
					 tsrv.setId_tipo_servico(rs.getInt("id_tipo_de_servico"));
					 tsrv.setFK_especialidade(rs.getInt("FK_especialidade"));
					 tsrv.setTipo_de_servico(rs.getString("tipo_de_servico"));			 
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
			return tsrv;
		}
		
//		7-salvar especialidade
		public void salvar(TipoServico es)
		{
			if(es.getId_tipo_servico()!=0)
			{
				alt_tservico(es);
			}
			else
			{
				TipoServInserir(es);
			}
		}
}
