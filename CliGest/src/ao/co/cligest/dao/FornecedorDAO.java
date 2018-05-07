package ao.co.cligest.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import ao.co.cligest.entidades.Agenda;
import ao.co.cligest.entidades.Fornecedor;
import ao.co.cligest.entidades.Funcionario;
import ao.co.cligest.entidades.Grupos;



public class FornecedorDAO {

	private Connection con ;
	
//	1-Mostrar os dados do Fornecedor
	public List <Fornecedor> fornecedorVisualizar()
	{
		List <Fornecedor> lista = new ArrayList<Fornecedor>();
		String sql = "SELECT * FROM vwseguradoras ORDER BY empresa asc";
		try{
			con = Conexao.getConexao();
			PreparedStatement preparador = con.prepareStatement(sql);
			ResultSet ag = preparador.executeQuery();
			while(ag.next())
			 {
				Fornecedor pn = new Fornecedor();
				pn.setFK_forncdor(ag.getInt("FK_fornecedor"));
				pn.setN_alvara(ag.getString("numero_doalvara"));
				pn.setEmpresa(ag.getString("empresa"));
				pn.setNif(ag.getString("nif"));
				pn.setNota(ag.getString("nota"));
				
			    lista.add(pn);
			    }
			    preparador.close();
//			    con.close();
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
	
//	1-Mostrar os dados do Fornecedor
	public List <Fornecedor> fornecedorVisualizar2()
	{
		List <Fornecedor> lista = new ArrayList<Fornecedor>();
		String sql = "select emp.FK_fornecedor, emp.numero_fornecedor, emp.numero_doalvara, emp.nif, emp.empresa, emp.nota, emp.paginaWeb, fone.telefone, emai.email, ende.endereco from tblfornecedor emp, tbltelefone fone, tblemail emai, tblendereco ende where emai.FK_entidade = emp.FK_fornecedor and ende.FK_entidade = emp.FK_fornecedor and fone.FK_entidade=emp.FK_fornecedor order by empresa asc";
		try{
			con = Conexao.getConexao();
			PreparedStatement preparador = con.prepareStatement(sql);
			ResultSet ag = preparador.executeQuery();
			while(ag.next())
			 {
				Fornecedor pn = new Fornecedor();
				pn.setFK_forncdor(ag.getInt("FK_fornecedor"));
				pn.setN_alvara(ag.getString("numero_doalvara"));
				pn.setEmpresa(ag.getString("empresa"));
				pn.setNif(ag.getString("nif"));
				pn.setNota(ag.getString("nota"));
				
			    lista.add(pn);
			    }
			    preparador.close();
//			    con.close();
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
	
//	2-Mostrar os dados do Cobertura
	public List <Fornecedor> coberturaVisualizar()
	{
		List <Fornecedor> lista = new ArrayList<Fornecedor>();
		String sql = "SELECT * FROM TBLTIPODECOBERTURA";
		try{
			con = Conexao.getConexao();
			PreparedStatement preparador = con.prepareStatement(sql);
			ResultSet ag = preparador.executeQuery();
			while(ag.next())
			 {
				Fornecedor pn = new Fornecedor();
				pn.setId_tp_cobertura(ag.getInt("id_tipo_dcobertura"));
				pn.setTipo_cobertura(ag.getString("nome_do_plano"));
			    lista.add(pn);
			    }
			    preparador.close();
//			    con.close();
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
//	3- Mostrar o nome da Empresa no text field
	public Fornecedor buscarEmpresa (int num)
	{
		Fornecedor lista = new Fornecedor();
		String sql = "SELECT * FROM vwfornecedor WHERE FK_fornecedor LIKE ?";
		try {
			con = Conexao.getConexao();
			 PreparedStatement preparador = con.prepareStatement(sql);
			 preparador.setInt(1, num);
			 ResultSet rs = preparador.executeQuery();
			 if(rs.next())
			 {
				 	lista.setFK_forncdor(rs.getInt("FK_fornecedor"));
					lista.setN_alvara(rs.getString("numero_doalvara"));
					lista.setEmpresa(rs.getString("empresa"));
					lista.setNif(rs.getString("nif"));
					lista.setNota(rs.getString("nota"));
				
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
		System.out.println("Nome da Empresa: "+lista.getEmpresa());
		return lista;
}
//	4-INSERIR OS DADOS NO BANCO
	public void add_fornecedor(Fornecedor fr)
	{
		String sql = "INSERT INTO TBLASEGURADORA (FK_fornecedor, FK_tipo_cobertura) VALUES (?,?)";
		try {
			con = Conexao.getConexao();
			PreparedStatement fornc = con.prepareStatement(sql);
			
			fornc.setInt(1, fr.getFK_forncdor());
			fornc.setInt(2, fr.getFK_tipo_cobertura());
			fornc.execute();
			fornc.close();
			System.out.println("Cadastro de sucesso...TBLASEGURADORA"); 
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
//	5-Mostrar os dados do Fornecedor
	public List <Fornecedor> asseguradoraVisualizar()
	{
		List <Fornecedor> lista = new ArrayList<Fornecedor>();
		String sql = "SELECT * FROM vwaseguradora";
		try{
			con = Conexao.getConexao();
			PreparedStatement preparador = con.prepareStatement(sql);
			ResultSet ag = preparador.executeQuery();
			while(ag.next())
			 {
				Fornecedor pn = new Fornecedor();
				pn.setFK_forncdor(ag.getInt("fk_fornecedor"));
				pn.setTelefone(ag.getLong("telefone"));
				pn.setEmpresa(ag.getString("empresa"));
				pn.setEmail(ag.getString("email"));
			    lista.add(pn);
			    }
			    preparador.close();
//			    con.close();
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
	public List <Fornecedor> asseguradoraVisualizar(String cod)
	{
		List <Fornecedor> lista = new ArrayList<Fornecedor>();
		String sql = "SELECT * FROM VWASEGURADORA WHERE fk_fornecedor =?";
		try{
			con = Conexao.getConexao();
			PreparedStatement preparador = con.prepareStatement(sql);
			preparador.setString(1, cod);
			ResultSet ag = preparador.executeQuery();
			while(ag.next())
			 {
				Fornecedor pn = new Fornecedor();
				pn.setFK_forncdor(ag.getInt("fk_fornecedor"));
				pn.setTelefone(ag.getLong("telefone"));
				pn.setEmpresa(ag.getString("empresa"));
			    lista.add(pn);
			    }
			    preparador.close();
//			    con.close();
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
	//Xecar o valor se já existe.
	public int Xequeasseguradora(int empresa, String plano)
	{
		int ok = 0;
		String sql = "SELECT * FROM tbltipodecobertura WHERE FK_fornecedor =? and nome_do_plano =?";
		try {
			con = Conexao.getConexao();
			PreparedStatement preparador = con.prepareStatement(sql);
			 preparador.setInt(1, empresa);
			 preparador.setString(2, plano);
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
	public Fornecedor edit_asseguradora(int ed) {
		Fornecedor fun = null;
		String sql = "SELECT * FROM vwtbltipodecobertura WHERE id_tipo_dcobertura = ?";
		try {
			con = Conexao.getConexao();
			PreparedStatement preparador = con.prepareStatement(sql);
			preparador.setInt(1, ed);
			ResultSet rs = preparador.executeQuery();
			while (rs.next()) {
				fun = new Fornecedor();
				fun.setId_tp_cobertura(rs.getInt("id_tipo_dcobertura"));
				fun.setFK_forncdor(rs.getInt("fk_fornecedor"));
				fun.setNome_do_plano(rs.getString("nome_do_plano"));
				fun.setDescricao(rs.getString("descricao_p_s"));
				fun.setTeto(rs.getDouble("teto"));
				fun.setCoparticipacao(rs.getDouble("coparticipacao"));
				fun.setDesconto(rs.getDouble("desconto"));
				fun.setValor_acrescido(rs.getDouble("valor_acrescido"));
			}
			preparador.close();
		} catch (SQLException e) {
			e.printStackTrace();
		} catch (Exception e) {
			// TODO: handle exception
		} finally {
			try {
				con.close();
			} catch (SQLException ef) {
				System.out.println("Erro finalizar: " + ef);
			}
		}
		return fun;
	}
	public void alt_asseguradora(Fornecedor fc) {
		String sql = "UPDATE tbltipodecobertura SET nome_do_plano =?, teto =?, descricao_p_s =?, valor_acrescido = ?, coparticipacao = ?, desconto = ? WHERE id_tipo_dcobertura = ?";
		try {
			con = Conexao.getConexao();
			PreparedStatement ps = con.prepareStatement(sql);
			ps.setString(1, fc.getNome_do_plano());
			ps.setDouble(2, fc.getTeto());
			ps.setString(3, fc.getDescricao());
			ps.setDouble(4, fc.getValor_acrescido());
			ps.setDouble(5, fc.getCoparticipacao());
			ps.setDouble(6, fc.getDesconto());
			ps.setInt(7, fc.getId_tp_cobertura());
			ps.execute();
			ps.close();
			System.out.println("Alteracao com sucesso...");
		} catch (SQLException e) {
			e.printStackTrace();
		} catch (Exception e) {
			// TODO: handle exception
		} finally {
			try {
				con.close();
			} catch (SQLException ef) {
				System.out.println("Erro finalizar: " + ef);
			}
		}
	}
	// coberturas
	public List <Fornecedor> coberturasVisualizar(String cod)
	{
		List <Fornecedor> lista = new ArrayList<Fornecedor>();
		String sql = "SELECT * FROM vwtbltipodecobertura WHERE fk_fornecedor =?";
		try{
			con = Conexao.getConexao();
			PreparedStatement preparador = con.prepareStatement(sql);
			preparador.setString(1, cod);
			ResultSet ag = preparador.executeQuery();
			while(ag.next())
			 {
				Fornecedor pn = new Fornecedor();
				pn.setId_tp_cobertura(ag.getInt("id_tipo_dcobertura"));
				pn.setFK_forncdor(ag.getInt("fk_fornecedor"));
				pn.setNome_do_plano(ag.getString("nome_do_plano"));
				pn.setDescricao(ag.getString("descricao_p_s"));
				pn.setTeto(ag.getDouble("teto"));
			    lista.add(pn);
			    }
			    preparador.close();
//			    con.close();
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
	// add_asseguradora 
	public void add_asseguradora(Fornecedor fr)
	{
		String sql = "INSERT INTO tbltipodecobertura (nome_do_plano, teto, FK_fornecedor, descricao_p_s, data_criacao_p_s, hora_criacao_p_s, FK_funcionario,valor_acrescido,coparticipacao,desconto) VALUES (?,?,?,?,?,?,?,?,?,?)";
		try {
			con = Conexao.getConexao();
			PreparedStatement ps = con.prepareStatement(sql);
			ps.setString(1, fr.getNome_do_plano());
			ps.setDouble(2, fr.getTeto());
			ps.setInt(3, fr.getFK_forncdor());
			ps.setString(4, fr.getDescricao());
			ps.setDate(5, new Formatando().data_registo());
			ps.setString(6, new Formatando().horaAtual());
			ps.setInt(7, fr.getFK_funcionario()); 
			ps.setDouble(8, fr.getValor_acrescido());
			ps.setDouble(9, fr.getCoparticipacao());
			ps.setDouble(10, fr.getDesconto());
			ps.execute();
			ps.close();
			System.out.println("Cadastro de sucesso...TBLASEGURADORA"); 
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
//	6-Remover o asseguradora
	public void del_asseguradora(int id)
	{
		String sql = "DELETE FROM tbltipodecobertura WHERE id_tipo_dcobertura=?";
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
//	6-Remover o fornecedor
	public void del_fornecedor(Fornecedor fr)
	{
		String sql = "Delete from TBLASEGURADORA where FK_fornecedor = ? and FK_tipo_cobertura = ? ";
		try {
			con = Conexao.getConexao();
			PreparedStatement frc = con.prepareStatement(sql);
			
			frc.setInt(1, fr.getFK_forncdor());
			frc.setInt(2, fr.getFK_tipo_cobertura());
			frc.execute();
			frc.close();
			System.out.println("Delete com sucesso...TBLASEGURADORA"); 
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
