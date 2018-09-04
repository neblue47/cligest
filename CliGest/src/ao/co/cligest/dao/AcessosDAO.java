package ao.co.cligest.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

 

import ao.co.cligest.cripto.EncriptaDecriptaRSA;
import ao.co.cligest.entidades.Diverso;
import ao.co.cligest.entidades.Usuario;

public class AcessosDAO {

	private Connection con;
	private EncriptaDecriptaRSA rsa;
	
	public List<Diverso> buscarPerifisTelas ()
	{
		List <Diverso> lista = new ArrayList<Diverso>();
		String sql = "select perfil, fk_perfil,modulo, count(tela) as qtd from vwprivilegios group by perfil, fk_perfil,modulo ";
		try {
			 con = Conexao.getConexao();
			 PreparedStatement preparador = con.prepareStatement(sql);
			 ResultSet rs = preparador.executeQuery();
			 while(rs.next())
			 {
				 Diverso dd = new Diverso();
				 dd.setModulo(rs.getString("modulo")); 
				 dd.setPerfil(rs.getString("perfil"));
				 dd.setFk_perfil(rs.getInt("fk_perfil"));
				 dd.setQtd_telas(rs.getInt("qtd"));
				 lista.add(dd);
			 }
			  
			 preparador.close();
			 con.close();
		}catch (Exception e) {
			e.printStackTrace();
		}
		return lista;
	}
	
	public List<Diverso> buscarListaPerfil ()
	{
		List <Diverso> lista = new ArrayList<Diverso>();
		String sql = "select * from tblperfil ";
		try {
			 con = Conexao.getConexao();
			 PreparedStatement preparador = con.prepareStatement(sql);
			 ResultSet rs = preparador.executeQuery();
			 while(rs.next())
			 {
				 Diverso dd = new Diverso();
				 dd.setPerfil(rs.getString("perfil"));
				 dd.setFk_perfil(rs.getInt("id"));
				 lista.add(dd);
				  
			 }
			  
			 preparador.close();
			 con.close();
		}catch (Exception e) {
			e.printStackTrace();
		}
		return lista;
	}
	
	public Diverso verPerfilPrivilegio(String codPrf){
		
		String sql = "SELECT * FROM vwprivilegios WHERE fk_perfil = ?";
		Diverso perfil = new Diverso();
		try {
			//String aux = rsa.decriptografa(codPrf);
			int cod = Integer.parseInt(codPrf);
			con = Conexao.getConexao();
			PreparedStatement ps = con.prepareStatement(sql);
			ps.setInt(1, cod);
			ResultSet rs = ps.executeQuery();
			if(rs.next()){
				perfil.setFk_perfil(rs.getInt("fk_perfil"));
				perfil.setPerfil(rs.getString("perfil"));
			}
			ps.close();
			con.close();
		} catch (Exception e) {
			// TODO: handle exception
		}
		return perfil;
	}
	
	
	public List<Diverso> buscarTelas ()
	{
		List <Diverso> lista = new ArrayList<Diverso>();
		String sql = "Select * from tbltela t, tblmodulo m where t.fk_modulo = m.id order by tela desc limit 12";
		try {
			 
			 con = Conexao.getConexao();
			 PreparedStatement pr = con.prepareStatement(sql);
			 ResultSet rs = pr.executeQuery();
			 while(rs.next())
			 {
				 Diverso md = new Diverso();
				 md.setId_tela(rs.getInt("Id"));
				 md.setTela(rs.getString("tela"));
				 md.setCodTela(rs.getString("cod_tela"));
				 md.setModulo(rs.getString("modulo"));
				 lista.add(md);
			 }
			 pr.close();
			 con.close();
		} catch (SQLException e) {
			
			e.printStackTrace();
		}
		
		return lista;
	}
	
	public List<Diverso> buscarTelas (String valor)
	{
		List <Diverso> lista = new ArrayList<Diverso>();
		String sql = "Select * from tbltela t, tblmodulo m where t.fk_modulo = m.id  and (tela like ? or modulo like ? ) order by tela desc limit 12";
		try {
			 
			 con = Conexao.getConexao();
			 PreparedStatement pr = con.prepareStatement(sql);
			 pr.setString(1, valor+"%");
			 pr.setString(2, valor+"%");
			 ResultSet rs = pr.executeQuery();
			 while(rs.next())
			 {
				 Diverso md = new Diverso();
				 md.setId_tela(rs.getInt("Id"));
				 md.setTela(rs.getString("tela"));
				 md.setCodTela(rs.getString("cod_tela"));
				 md.setModulo(rs.getString("modulo"));
				 lista.add(md);
			 }
			 pr.close();
			 con.close();
		} catch (SQLException e) {
			
			e.printStackTrace();
		}
		
		return lista;
	}
	
	public List<Diverso> buscarTelasPorPerfil (int perfil,int modulo)
	{
		List <Diverso> lista = new ArrayList<Diverso>();
		String sql = "select * from tbltela where fk_modulo = ? and id not in (select fk_tela   from vwprivilegios where fk_perfil = ? and fk_modulo = ?)";
		try {
			 
			 con = Conexao.getConexao();
			 PreparedStatement pr = con.prepareStatement(sql);
			 pr.setInt(1, modulo);
			 pr.setInt(2, perfil);
			 pr.setInt(3, modulo);
			 ResultSet rs = pr.executeQuery();
			 while(rs.next())
			 {
				 Diverso md = new Diverso();
				 md.setId_tela(rs.getInt("Id"));
				 md.setTela(rs.getString("tela"));
				 md.setCodTela(rs.getString("cod_tela"));
				 lista.add(md);
				 System.out.println("JAJAJ");
			 }
			 pr.close();
			 con.close();
		} catch (SQLException e) {
			
			e.printStackTrace();
		}
		
		return lista;
	}
	
	public List<Diverso>AcessoModulosLogin (int idUsuario)
	{
		List <Diverso> lista = new ArrayList<Diverso>();
		String sql = "select * from vwmodulos where FK_grupo = ? group by fk_modulo order by fk_modulo";
		try {
			 con = Conexao.getConexao();
			 PreparedStatement pr = con.prepareStatement(sql);
			 pr.setInt(1, idUsuario);
			 ResultSet rs = pr.executeQuery();
			 while(rs.next())
			 {
				 Diverso md = new Diverso();
				 md.setId_mod(rs.getInt("fk_modulo"));
				 md.setModulo(rs.getString("modulo"));
				 md.setMdlink(rs.getString("link"));
				 md.setCodCl(rs.getString("codMod"));
				 lista.add(md);
			 }
			 pr.close();
			 con.close();
		} catch (SQLException e) {
			
			e.printStackTrace();
		}
		
		return lista;
	}
	
	public List<Diverso>AcessoTelasLogin (int idUsuario)
	{
		List <Diverso> lista = new ArrayList<Diverso>();
		String sql = "select * from vwperfilacessos where id_utilizador = ?  and obs_tela is null order by fk_tela";
		try {
			 con = Conexao.getConexao();
			 PreparedStatement pr = con.prepareStatement(sql);
			 pr.setInt(1, idUsuario);
			 ResultSet rs = pr.executeQuery();
			 while(rs.next())
			 {
				 Diverso md = new Diverso();
				 md.setId_mod(rs.getInt("fk_modulo"));
				 md.setModulo(rs.getString("modulo"));
				 md.setMdlink(rs.getString("controller"));
				 md.setCodCl(rs.getString("modcod"));
				 md.setTela(rs.getString("tela"));
				 md.setCodTela(rs.getString("cod_tela"));
				 md.setId_tela(rs.getInt("fk_tela"));
				  
				 lista.add(md);
				 
			 }
			 pr.close();
			 con.close();
		} catch (SQLException e) {
			
			e.printStackTrace();
		}
		
		return lista;
	}
	
	public List<Diverso>AcessoTelasPrivPai(int idUsuario)
	{
		List <Diverso> lista = new ArrayList<Diverso>();
		String sql = "select * from vwprivilegios where FK_grupo = ? AND tela_pai = 0 order by tela";
		try {
			 con = Conexao.getConexao();
			 PreparedStatement pr = con.prepareStatement(sql);
			 pr.setInt(1, idUsuario);
			 ResultSet rs = pr.executeQuery();
			 while(rs.next())
			 {
				 Diverso md = new Diverso();
				 md.setId_mod(rs.getInt("fk_modulo"));
				 md.setTela(rs.getString("tela"));
				 md.setLinktela(rs.getString("link"));
				 md.setNivel(rs.getInt("nivel"));
				 md.setTela_pai(rs.getInt("tela_pai"));
				 md.setId_tela(rs.getInt("FK_tela"));
				 lista.add(md);
				 
			 }
			 pr.close();
			 con.close();
		} catch (SQLException e) {
			
			e.printStackTrace();
		}
		
		return lista;
	}
	
	public List<Diverso>AcessoTelasPriv(int idUsuario)
	{
		List <Diverso> lista = new ArrayList<Diverso>();
		String sql = "select * from vwprivilegios where FK_grupo = ? AND tela_pai <> 0 order by tela";
		try {
			 con = Conexao.getConexao();
			 PreparedStatement pr = con.prepareStatement(sql);
			 pr.setInt(1, idUsuario);
			 ResultSet rs = pr.executeQuery();
			 while(rs.next())
			 {
				 Diverso md = new Diverso();
				 md.setId_mod(rs.getInt("fk_modulo"));
				 md.setTela(rs.getString("tela"));
				 md.setLinktela(rs.getString("link"));
				 md.setNivel(rs.getInt("nivel"));
				 md.setTela_pai(rs.getInt("tela_pai"));
				 md.setId_tela(rs.getInt("FK_tela"));
				 lista.add(md);
				 
			 }
			 pr.close();
			 con.close();
		} catch (SQLException e) {
			
			e.printStackTrace();
		}
		
		return lista;
	}

	public List<Diverso>AcessoTelasConfigLogin (int idUsuario)
	{
		List <Diverso> lista = new ArrayList<Diverso>();
		String sql = "select * from vwperfilacessos where id_utilizador = ?  and obs_tela  is null and fk_modulo = 3 order by fk_tela;";
		try {
			 con = Conexao.getConexao();
			 PreparedStatement pr = con.prepareStatement(sql);
			 pr.setInt(1, idUsuario);
			 ResultSet rs = pr.executeQuery();
			 while(rs.next())
			 {
				 Diverso md = new Diverso();
				 md.setId_mod(rs.getInt("fk_modulo"));
				 md.setModulo(rs.getString("modulo"));
				 md.setMdlink(rs.getString("controller"));
				 md.setCodCl(rs.getString("modcod"));
				 md.setTela(rs.getString("tela"));
				 md.setCodTela(rs.getString("cod_tela"));
				 md.setId_tela(rs.getInt("fk_tela"));
				  
				 lista.add(md);
				 
			 }
			 pr.close();
			 con.close();
		} catch (SQLException e) {
			
			e.printStackTrace();
		}
		
		return lista;
	}

	public List<Diverso> buscarModulos()
	{
		List <Diverso> lista = new ArrayList<Diverso>();
		String sql = "Select * from tblmodulo order by 2  asc  ";
		try {
			 
			 con = Conexao.getConexao();
			 PreparedStatement pr = con.prepareStatement(sql);
			 ResultSet rs = pr.executeQuery();
			 while(rs.next())
			 {
				 Diverso md = new Diverso();
				 md.setId_mod(rs.getInt("id"));
				 md.setModulo(rs.getString("modulo"));
				 md.setMdlink(rs.getString("controller"));
				 md.setCodCl(rs.getString("modcod"));
				 lista.add(md);
			 }
			 pr.close();
			 con.close();
		} catch (SQLException e) {
			
			e.printStackTrace();
		}
		
		return lista;
	}
	public void novoPerifilTelas(List <Diverso> acessos){
		int LastID=0;
		String sql = "INSERT INTO tblperfil (perfil) value(?)";
		try {
			String perfil = acessos.get(0).getPerfil();
			con = Conexao.getConexao();
			PreparedStatement ps = con.prepareStatement(sql);
			ps.setString(1, perfil);
			ps.execute();
			ResultSet rs = ps.executeQuery("SELECT LAST_INSERT_ID()");
			if(rs.next()){
				LastID = rs.getInt(1);
				 for(Diverso dd: acessos){
					   sql = "INSERT INTO tblprivilegio (fk_modulo,fk_tela,fk_perfil) value(?,?,?)";
					    ps = con.prepareStatement(sql);
					    ps.setInt(1, dd.getId_mod());
						ps.setInt(2, dd.getId_tela());
						ps.setInt(3, LastID);
						ps.execute();
				 }
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	public void EditarPerifilTelas(List <Diverso> acessos){
		 
		   String sql = " ";
		try {
			if(!acessos.isEmpty()){
				 
				con = Conexao.getConexao();
				PreparedStatement ps = con.prepareStatement(sql);				 
				 for(Diverso dd: acessos){
					   sql = "INSERT INTO tblprivilegio (fk_modulo,fk_tela,fk_perfil) value(?,?,?)";
					    
					    ps = con.prepareStatement(sql);
					    ps.setInt(1, dd.getId_mod());
						ps.setInt(2, dd.getId_tela());
						ps.setInt(3, dd.getFk_perfil());
						ps.execute();
				 }
					
				ps.close();
				con.close();
			}
			
			 
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	
	public void AssociarPerfilUtilizador(Usuario d){
		 
		String  sql = "UPDATE tblutilizador SET fk_perfil = ? WHERE id_utilizador = ?";
		try {
			 		 
				con = Conexao.getConexao();
				PreparedStatement ps = con.prepareStatement(sql);				 
				ps.setInt(1, d.getFk_perfil());
				ps.setInt(2, d.getId());
				ps.execute();
				ps.close();
				con.close();
			 
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	public void EliminarPerfilUtilizador(int usu){
		 
		String  sql = "UPDATE tblutilizador SET fk_perfil = ? WHERE id_utilizador = ?";
		try {
			 		 
				con = Conexao.getConexao();
				PreparedStatement ps = con.prepareStatement(sql);				 
				ps.setInt(1, 0);
				ps.setInt(2, usu);
				ps.execute();
				ps.close();
				con.close();
			 
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
}

