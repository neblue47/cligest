package ao.co.cligest.negocio;
import ao.co.cligest.cripto.EncriptaDecriptaRSA;
import ao.co.cligest.entidades.Funcionario;
import ao.co.cligest.entidades.Usuario;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class UserDAO 
{
	private Connection con ;
 
	private static final String key = "140b41b22a29beb4061bda66b6747e14";
	public void cadastrar (Usuario usu)
	{
		String sql = "INSERT INTO UTILIZADORES (nome,senha,login) VALUES (?,?,?)";
		try {
			con = Conexao.getConexao(); 
			PreparedStatement preparador = con.prepareStatement(sql);
			 preparador.setString(1, usu.getNome());
			 preparador.setString(2, usu.getSenha());
			 preparador.setString   (3, usu.getLogin());
			 preparador.execute();
			 preparador.close();
			 System.out.println("Cadastro de sucesso..."); 
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
	public void alterar (Usuario usu)
	{
		String sql = "UPDATE UTILIZADORES SET nome=?,senha=?,login=? WHERE id=?";
		try {
			con = Conexao.getConexao(); 
			PreparedStatement preparador = con.prepareStatement(sql);
			 preparador.setString(1, usu.getNome());
			 preparador.setString(2, usu.getSenha());
			 preparador.setString(3, usu.getLogin());
			 preparador.setInt   (4, usu.getId());
			 preparador.execute();
			 preparador.close();
			 System.out.println("Alteracao com sucesso..."); 
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
	public void excluir (Usuario usu)
	{
		String sql = "DELETE FROM UTILIZADORES  WHERE id=?";
		try {
			con = Conexao.getConexao(); 
			PreparedStatement preparador = con.prepareStatement(sql);
			 preparador.setInt   (1, usu.getId());
			 preparador.execute();
			 preparador.close();
			 System.out.println("Excuisao com sucesso..."); 
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
	public List<Usuario> buscaTodos ()
	{
		List <Usuario> lista = new ArrayList<Usuario>();
		String sql = "SELECT * FROM UTILIZADORES ";
		try {
			con = Conexao.getConexao(); 
			PreparedStatement preparador = con.prepareStatement(sql);
			 
			 ResultSet rs = preparador.executeQuery();
			 
			 while(rs.next())
			 {
				 Usuario usu = new Usuario();
				 usu.setId(rs.getInt("id"));
				 usu.setNome(rs.getString("nome"));
				 usu.setSenha(rs.getString("senha"));
				 usu.setLogin(rs.getString("login"));
				 lista.add(usu);
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
	
	public Usuario buscarPorId(int id)
	{
		Usuario usu=null;
		String sql = "Select * from vwusuarios where FK_entidade = ?";
		try {
			con = Conexao.getConexao();
			PreparedStatement preparador = con.prepareStatement(sql);
			preparador.setInt(1, id);
			ResultSet rs = preparador.executeQuery();
			
			if(rs.next())
			{
				usu = new Usuario();
				usu.setId(rs.getInt("FK_entidade"));
				usu.setSenha(rs.getString("senha"));
				 
			}
			
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
		return usu;
	}
	public List<Usuario> buscarPorNome(String nome)
	{
		Usuario usu=null;
		String sql = "SELECT * FROM UTILIZADORES WHERE nome LIKE ?";
		List <Usuario> lista = new ArrayList<Usuario>();
		try {
			con = Conexao.getConexao();
			PreparedStatement preparador = con.prepareStatement(sql);
			preparador.setString(1, "%"+nome+"%");
			ResultSet rs = preparador.executeQuery();
			
			while(rs.next())
			{
				usu = new Usuario();
				usu.setId(rs.getInt("id"));
				usu.setNome(rs.getString("nome"));
				usu.setSenha(rs.getString("senha"));
				usu.setLogin(rs.getString("login"));
				lista.add(usu);
			}
			
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
	public Usuario autenticado(Usuario usu)
	{
		Usuario usuRt=null;
		String sql = "SELECT * FROM vwusuarios WHERE nome_usuario LIKE ?  ";
		try {
			con = Conexao.getConexao();
			PreparedStatement preparador = con.prepareStatement(sql);
			preparador.setString(1, usu.getNome());
		 
			ResultSet rs = preparador.executeQuery();
			if(rs.next())
			{
				usuRt = new Usuario();
				usuRt.setId(rs.getInt("fk_entidade"));
				usuRt.setNome(rs.getString("nome"));
				usuRt.setSenha(rs.getString("senha"));
				usuRt.setNomComp(rs.getString("nome")+" "+rs.getString("ultimo_nome"));
				usuRt.setNomgrupo(rs.getString("grupo"));
				usuRt.setFk_grupo(rs.getInt("id_grupo"));
		   }
			
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
		return usuRt;
	}
	
	public boolean existe_usuario(Usuario usu)
	{
		String sql = "SELECT * FROM vwusuarios WHERE nome_usuario LIKE ? and senha LIKE ?";
		//boolean rest = false;
		try {
			con = Conexao.getConexao();
			PreparedStatement preparador = con.prepareStatement(sql);
			preparador.setString(1, usu.getNome());
			preparador.setString(2, usu.getSenha());
			ResultSet rs = preparador.executeQuery();
			if(rs.next())
			  return true;
			
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
		return false;
	}
	
	public boolean existe_user(Usuario usu)
	{
		String sql = "SELECT * FROM tblusuario WHERE nome_usuario LIKE ? ";
	    
		try {
			con = Conexao.getConexao();
			PreparedStatement preparador = con.prepareStatement(sql);
			preparador.setString(1, usu.getNome());
			 
			ResultSet rs = preparador.executeQuery();
			if(rs.next())
			  {
				     
				     String senha =  EncriptaDecriptaRSA.decriptografa(rs.getString("senha"));   
					 if(usu.getSenha().equals(senha))
						 return true;
					 
			  }
			
			preparador.close();
			con.close();
		}
		catch (Exception e) {
			  
			e.printStackTrace();
		}
		 
		return false;
	}
	
	public void salvar(Usuario usu){
		if(usu.getId()!=0){
			alterar(usu);
		}
		else{
			cadastrar(usu);
		}
	}
}
