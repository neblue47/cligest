package ao.co.cligest.dao;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

import ao.co.cligest.entidades.*;

public class GrupoDAO 
{ 
	private Connection con ;
	
	public List<Grupos> buscaModulo ()
	{
		List <Grupos> lista = new ArrayList<Grupos>();
		String sql = "SELECT * FROM TBLMODULO ";
		try {
			con = Conexao.getConexao();
			 PreparedStatement preparador = con.prepareStatement(sql);
			 ResultSet rs = preparador.executeQuery();
			 while(rs.next())
			 {
				 Grupos md = new Grupos();
				 md.setId_modulo(rs.getInt("id_modulo"));
				 md.setModulo(rs.getString("modulo"));
				 lista.add(md);
			 }
			 preparador.close();
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
		return lista;
	}
	public List<Grupos> buscaTelas (int md,int gr)
	{
		List <Grupos> lista = new ArrayList<Grupos>();
		String sql = "SELECT * from tbltela WHERE FK_modulo = ? AND id_tela NOT IN(SELECT FK_tela FROM tblprevilegioatribuido WHERE FK_grupo = ?)";
		try {
			con = Conexao.getConexao();
			 PreparedStatement preparador = con.prepareStatement(sql);
			 preparador.setInt(1, md);
			 preparador.setInt(2,gr);
			 ResultSet rs = preparador.executeQuery();
			 while(rs.next())
			 {
				 Grupos fun = new Grupos();
				 fun.setId_tela(rs.getInt("id_tela"));
				 fun.setTela(rs.getString("tela"));
				 lista.add(fun);
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
	public List<Grupos> buscaGrupos ()
	{
		List <Grupos> lista = new ArrayList<Grupos>();
		String sql = "SELECT * FROM TBLGRUPO";
		try {
			con = Conexao.getConexao();
			 PreparedStatement preparador = con.prepareStatement(sql);
			 ResultSet rs = preparador.executeQuery();
			 while(rs.next())
			 {
				 Grupos gr = new Grupos();
				 gr.setId_grupo(rs.getInt("id_grupo"));
				 gr.setNomgrupo(rs.getString("grupo"));
				 lista.add(gr);
			 }
			 preparador.close();
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
		return lista;
	}
	public List<Grupos> buscaGrupos (int cod)
	{
		List <Grupos> lista = new ArrayList<Grupos>();
		String sql = "SELECT * FROM TBLGRUPO WHERE id_grupo =?";
		try {
			con = Conexao.getConexao();
			 PreparedStatement preparador = con.prepareStatement(sql);
			 preparador.setInt(1, cod);
			 ResultSet rs = preparador.executeQuery();
			 while(rs.next())
			 {
				 Grupos gr = new Grupos();
				 gr.setId_grupo(rs.getInt("id_grupo"));
				 gr.setNomgrupo(rs.getString("grupo"));
				 lista.add(gr);
			 }
			 preparador.close();
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
		return lista;
	}
	public String buscaGrupo(int cod)
	{
		String lista = "";
		String sql = "SELECT * FROM TBLGRUPO WHERE ID_GRUPO =?";
		try {
			con = Conexao.getConexao();
			 PreparedStatement ent = con.prepareStatement(sql);
			 ent.setInt(1, cod);
			 ResultSet rs = ent.executeQuery();
			 if(rs.next())
				 lista = rs.getString("grupo");
			 ent.close();
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
		return lista;
	}
	
	public List<Grupos> buscaFuncionalidade(int tela)
	{
		List <Grupos> lista = new ArrayList<Grupos>();
		String sql = "SELECT * FROM TBLLISTARFUNCIONALIDADE ";
		try {
			con = Conexao.getConexao();
			 PreparedStatement ent = con.prepareStatement(sql);
			 //ent.setInt(1, tela);
			 ResultSet rs = ent.executeQuery();
			 while(rs.next()){
				 Grupos gr = new Grupos();
				 gr.setId_funcionalidade(rs.getInt("id_funcionalidade"));
				 gr.setFk_tela(rs.getInt("fk_tela"));
				 gr.setCodigo_ext(rs.getString("codigo_externo"));
				 gr.setFuncionalidade(rs.getString("funcionalidade"));
				 gr.setDescricao(rs.getString("descricao"));
				 lista.add(gr);
			 }
				
			 ent.close();
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
		return lista;
	}
	public List<Grupos> buscaFuncionalidade(int fk_grupo,int fk_modulo)
	{
		List <Grupos> lista = new ArrayList<Grupos>();
		String sql = "SELECT * FROM vwprivilegios where fk_modulo like ? and fk_grupo like ?";
		try {
			con = Conexao.getConexao();
			 PreparedStatement ent = con.prepareStatement(sql);
			 ent.setInt(1, fk_modulo);
			 ent.setInt(2, fk_grupo);
			 ResultSet rs = ent.executeQuery();
			 while(rs.next()){
				 Grupos gr = new Grupos();
				 gr.setNomgrupo(rs.getString("grupo"));
				 gr.setModulo(rs.getString("modulo"));
				 gr.setTela(rs.getString("tela"));
				 gr.setFk_telas(rs.getInt("fk_tela"));
				 lista.add(gr);
			 }
		 	
			 ent.close();
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
		
		return lista;
	}
	public List<Grupos> buscaPrivilegios()
	{
		List <Grupos> lista = new ArrayList<Grupos>();
		String sql = "SELECT * FROM vwprivilegios  ";
		try {
			con = Conexao.getConexao();
			 PreparedStatement ent = con.prepareStatement(sql);
			 ResultSet rs = ent.executeQuery();
			 while(rs.next()){
				 Grupos gr = new Grupos();
				 gr.setNomgrupo(rs.getString("grupo"));
				 gr.setModulo(rs.getString("modulo"));
				 gr.setTela(rs.getString("tela"));
				 gr.setFk_telas(rs.getInt("fk_tela"));
				 lista.add(gr);
			 }
				
			 ent.close();
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
		return lista;
	}
	
	public List<Grupos> buscaPrivilegios(String txtPesquisa)
	{
		List <Grupos> lista = new ArrayList<Grupos>();
		String sql = "SELECT * FROM vwprivilegios where modulo like '"+txtPesquisa+"' or grupo like '"+txtPesquisa+"'";
		try {
			con = Conexao.getConexao();
			 PreparedStatement ent = con.prepareStatement(sql);
			 ResultSet rs = ent.executeQuery();
			 while(rs.next()){
				 Grupos gr = new Grupos();
				 gr.setNomgrupo(rs.getString("grupo"));
				 gr.setModulo(rs.getString("modulo"));
				 gr.setTela(rs.getString("tela"));
				 gr.setFk_telas(rs.getInt("fk_tela"));
				 lista.add(gr);
			 }	
			 ent.close();
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
	public void add_grupo(Grupos gr)
	{
		String sql = "INSERT INTO TBLGRUPO (grupo) VALUE (?)";
		try {
			con = Conexao.getConexao();
			PreparedStatement ent = con.prepareStatement(sql);
			ent.setString(1, gr.getNomgrupo());
			ent.execute();
			ent.close();
			System.out.println("Cadastro de sucesso...TBLGRUPO"); 
		} catch (SQLException e) {
			System.out.println("Eroo Aqui "+e);
		}
		catch (Exception e) {
			// TODO: handle exception
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
	
	public void add_funcionalidade(Grupos gr)
	{
		String sql = "INSERT INTO TBLPREVILEGIOATRIBUIDO (fk_grupo,fk_funcionario,data,fk_tela,fk_modulo,fk_funcionalidade) VALUES (?,?,?,?,?,?)";
		try {
			con = Conexao.getConexao();
			PreparedStatement ent = con.prepareStatement(sql);
			
			ent.setInt(1, gr.getFk_grupo());
			ent.setInt(2, gr.getFk_funcionario());
			ent.setDate(3, gr.getDt_registo());
			ent.setInt(4, gr.getFk_telas());
			ent.setInt(5, gr.getFk_modulos());
			ent.setInt(6, 0);
			ent.execute();
			ent.close();
			System.out.println("Cadastro de sucesso...TBLPREVILEGIOATRIBUIDO"); 
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
	
	public void del_funcionalidade(Grupos gr)
	{
		String sql = "Delete from tblprevilegioatribuido where FK_grupo = ? and FK_modulo = ? and FK_tela = ?";
		try {
			con = Conexao.getConexao();
			PreparedStatement ent = con.prepareStatement(sql);
			
			ent.setInt(1, gr.getFk_grupo());
			ent.setInt(2, gr.getFk_modulos());
			ent.setInt(3, gr.getFk_telas());
			ent.execute();
			ent.close();
			System.out.println("Delete com sucesso...TBLPREVILEGIOATRIBUIDO"); 
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
	
	public void del_grupo(int cod)
	{
		String sql = "DELETE FROM TBLGRUPO WHERE ID_GRUPO = ?";
		try {
			con = Conexao.getConexao();
			PreparedStatement ent = con.prepareStatement(sql);
			ent.setInt(1, cod);
			ent.execute();
			ent.close();
			System.out.println("Cadastro de sucesso...TBLGRUPO"); 
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
	public void alt_grupo(Grupos gr)
	{
		String sql = "UPDATE TBLGRUPO SET GRUPO = ? WHERE ID_GRUPO = ?";
		try {
			con = Conexao.getConexao();
			PreparedStatement ent = con.prepareStatement(sql);
			ent.setString(1, gr.getNomgrupo());
			ent.setInt(2, gr.getId_grupo());
			ent.execute();
			ent.close();
			System.out.println("Alteracao com sucesso...TBLGRUPO"); 
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
	
	public void salvar(Grupos gr)
	{
		if(gr.getId_grupo()!=null )
		{
			alt_grupo(gr);
		}
		else
		{
			add_grupo(gr);
		}
	}
}
