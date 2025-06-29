package ao.co.cligest.util;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.net.InetAddress;
import java.net.UnknownHostException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import ao.co.cligest.dao.Conexao;
import ao.co.cligest.entidades.Arquivos;
import ao.co.cligest.entidades.Diverso;
import ao.co.cligest.entidades.Farmacos;
import ao.co.cligest.entidades.Funcionario;
import ao.co.cligest.entidades.Paciente;
import ao.co.cligest.entidades.Servico;

public class MetodosBuscas 
{
	private Connection con;

	// Meu PC
	private static String server = "C:\\Users\\nelson.diwidi\\git\\cligest\\CliGest\\WebContent\\config\\";
	
	public List<Diverso> buscaEspecialidade ()
	{
		List <Diverso> lista = new ArrayList<Diverso>();
		String sql = "SELECT * FROM TBLESPECIALIDADE ORDER BY ID_ESPECIALIDADE = 17 DESC, ESPECIALIDADE ASC";
		try {
			con = Conexao.getConexao(); 
			PreparedStatement preparador = con.prepareStatement(sql);
			 ResultSet rs = preparador.executeQuery();
			 while(rs.next())
			 {
				 Diverso fun = new Diverso();
				 fun.setEspecialidade(rs.getInt("id_especialidade"));
				 fun.setNomeEsp(rs.getString("especialidade"));
				 lista.add(fun);
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
		return lista;
	}
	// Metodos de Pesquisas (Consultas no BD)
	public List<Diverso> buscaProfissao ()
	{
		List <Diverso> lista = new ArrayList<Diverso>();
		String sql = "SELECT * FROM TBLPROFISSAO ";
		try {
			con = Conexao.getConexao();  
			PreparedStatement preparador = con.prepareStatement(sql);
			 ResultSet rs = preparador.executeQuery();
			 while(rs.next())
			 {
				 Diverso fun = new Diverso();
				 fun.setProfissao(rs.getInt("id_profissao"));
				 fun.setNomeProf(rs.getString("profissao"));
				 lista.add(fun);
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
		return lista;
	}
	
	public List<Diverso> buscaTensaoArterial ()
	{
		List <Diverso> lista = new ArrayList<Diverso>();
		String sql = "SELECT * FROM tblTensaoArterial order by id_tensao_arterial ";
		try {
			con = Conexao.getConexao();  
			PreparedStatement preparador = con.prepareStatement(sql);
			 ResultSet rs = preparador.executeQuery();
			 while(rs.next())
			 {
				 Diverso tmp = new Diverso();
				 tmp.setId_tensao_arterial(rs.getInt("id_tensao_arterial"));
				 tmp.setValor_tns(rs.getString("valor"));
				 lista.add(tmp);
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
		return lista;
	}
	
	public List<Diverso> buscaTensaoArterial2 ()
	{
		List <Diverso> lista = new ArrayList<Diverso>();
		String sql = "SELECT * FROM tbltensaoarterialdois order by id_tensao_arterial_dois ";
		try {
			con = Conexao.getConexao();  
			PreparedStatement preparador = con.prepareStatement(sql);
			 ResultSet rs = preparador.executeQuery();
			 while(rs.next())
			 {
				 Diverso tmp = new Diverso();
				 tmp.setId_tensao_arterial(rs.getInt("id_tensao_arterial_dois"));
				 tmp.setValor_tns(rs.getString("valor_dois"));
				 lista.add(tmp);
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
		return lista;
	}
	
	public List<Diverso> buscaPulso ()
	{
		List <Diverso> lista = new ArrayList<Diverso>();
		String sql = "SELECT * FROM tblPulso order by id_pulso ";
		try {
			con = Conexao.getConexao();  
			PreparedStatement preparador = con.prepareStatement(sql);
			 ResultSet rs = preparador.executeQuery();
			 while(rs.next())
			 {
				 Diverso tmp = new Diverso();
				 tmp.setId_pulso(rs.getInt("id_pulso"));
				 tmp.setValor_pulso(rs.getString("valor"));
				 lista.add(tmp);
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
		return lista;
	}
	
	public List<Diverso> buscaGeneros()
	{
		List <Diverso> lista = new ArrayList<Diverso>();
		String sql = "SELECT * FROM tblgenero  ";
		try {
			con = Conexao.getConexao();  
			PreparedStatement preparador = con.prepareStatement(sql);
			 ResultSet rs = preparador.executeQuery();
			 while(rs.next())
			 {
				 Diverso tmp = new Diverso();
				 tmp.setGenero(rs.getInt("id_genero"));
				 tmp.setNomgenero(rs.getString("genero"));
				 lista.add(tmp);
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
		return lista;
	}
	
	public List<Diverso> buscacomoXegou()
	{
		List <Diverso> lista = new ArrayList<Diverso>();
		String sql = "SELECT * FROM tblemergenciacomochegou  ";
		try {
			con = Conexao.getConexao();  
			PreparedStatement preparador = con.prepareStatement(sql);
			 ResultSet rs = preparador.executeQuery();
			 while(rs.next())
			 {
				 Diverso tmp = new Diverso();
				 tmp.setId_como_chegou(rs.getInt("id_como_chegou"));
				 tmp.setComo_chegou(rs.getString("opcao"));
				 lista.add(tmp);
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
		return lista;
	}
	
	public List<Diverso> buscaRespiracao ()
	{
		List <Diverso> lista = new ArrayList<Diverso>();
		String sql = "SELECT * FROM tblRespiracao order by id_respiracao ";
		try {
			con = Conexao.getConexao();  
			PreparedStatement preparador = con.prepareStatement(sql);
			 ResultSet rs = preparador.executeQuery();
			 while(rs.next())
			 {
				 Diverso tmp = new Diverso();
				 tmp.setId_respiracao(rs.getInt("id_respiracao"));
				 tmp.setValor_resp(rs.getString("valor"));
				 lista.add(tmp);
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
		return lista;
	}
	
	public List<Diverso> buscaTemperatura ()
	{
		List <Diverso> lista = new ArrayList<Diverso>();
		String sql = "SELECT * FROM tbltemperatura order by id_temperatura ";
		try {
			con = Conexao.getConexao();  
			PreparedStatement preparador = con.prepareStatement(sql);
			 ResultSet rs = preparador.executeQuery();
			 while(rs.next())
			 {
				 Diverso tmp = new Diverso();
				 tmp.setId_temperatura(rs.getInt("id_temperatura"));
				 tmp.setValor_tmp(rs.getString("valor"));
				 lista.add(tmp);
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
		return lista;
	}
	
	public List<Diverso> buscaEstadoPaciente ()
	{
		List <Diverso> lista = new ArrayList<Diverso>();
		String sql = "SELECT * FROM tblestadodopaciente  ";
		try {
			con = Conexao.getConexao();  
			PreparedStatement preparador = con.prepareStatement(sql);
			 ResultSet rs = preparador.executeQuery();
			 while(rs.next())
			 {
				 Diverso tmp = new Diverso();
				 tmp.setId_estadoPaciente(rs.getInt("id_estado_do_paciente"));
				 tmp.setEstadoPaciente(rs.getString("estado_do_paciente"));
				 lista.add(tmp);
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
		return lista;
	}

	public List<Diverso> buscaViaAdministrar ()
	{
		List <Diverso> lista = new ArrayList<Diverso>();
		String sql = "SELECT * FROM tblviasadministracao ";
		try {
			con = Conexao.getConexao();  
			PreparedStatement preparador = con.prepareStatement(sql);
			 ResultSet rs = preparador.executeQuery();
			 while(rs.next())
			 {
				 Diverso fun = new Diverso();
				 fun.setId_via(rs.getInt("id_viadministrar"));
				 fun.setVia(rs.getString("viaadministrar"));
				 lista.add(fun);
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
		return lista;
	}
	
	public Diverso buscaViaAdministrarNome (String aux)
	{
		Diverso lista = new Diverso();
		String sql = "SELECT * FROM tblviasadministracao where id_viadministrar like ? or viaadministrar like ? ";
		try {
			con = Conexao.getConexao();  
			PreparedStatement preparador = con.prepareStatement(sql);
			 preparador.setString(1, aux);
			 preparador.setString(2, aux);
			 ResultSet rs = preparador.executeQuery();
			 if(rs.next())
			 {
				  
				 lista.setId_via(rs.getInt("id_viadministrar"));
				 lista.setVia(rs.getString("viaadministrar"));
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
		return lista;
	}
	public  Farmacos buscarFarmacosN(String aux)
	{
		Farmacos farLis = null;
		
		String sql = "SELECT id_produto, nome_comercial FROM tblprodutofarmaceuticos where nome_comercial like ? ";
		
		try {
			con = Conexao.getConexao(); 
			PreparedStatement preparador = con.prepareStatement(sql);
			preparador.setString(1, aux);
			ResultSet rs = preparador.executeQuery();
		
			if (rs.next()){
				farLis = new Farmacos();
				farLis.setId_farmaco(rs.getInt("id_produto"));
				farLis.setNome_cm(rs.getString("nome_comercial"));
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
		return farLis ;
	}
	public List<Diverso> buscaDosagem ()
	{
		List <Diverso> lista = new ArrayList<Diverso>();
		String sql = "SELECT * FROM tbldosagem ";
		try {
			con = Conexao.getConexao();  
			PreparedStatement preparador = con.prepareStatement(sql);
			 ResultSet rs = preparador.executeQuery();
			 while(rs.next())
			 {
				 Diverso dv = new Diverso();
				 dv.setId_dosagem(rs.getInt("id_dosagem"));
				 dv.setDosagem(rs.getString("dosagem"));
				 lista.add(dv);
			 }
			 preparador.close();
		} catch (SQLException e) {
			
			System.out.println("Erro: "+e);
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
		return lista;
	}
	public Diverso buscagrupoexame (String codigo)
	{
		 Diverso bc = new Diverso();
//		List <Diverso> lista = new ArrayList<Diverso>();
		String sql = "SELECT * FROM tblservicosdeexamesclinicos where analise_clinica=  ?";
		try {
			 con = Conexao.getConexao(); 
			PreparedStatement preparador = con.prepareStatement(sql);
			 preparador.setString(1, codigo);
			 ResultSet rs = preparador.executeQuery();
			 while(rs.next())
			 {
//				 Diverso bc = new Diverso();
				 bc.setId_servicodeanalise_clinica(rs.getInt("id_servicodeanalise_clinica"));
				 bc.setAnalise_clinica(rs.getString("analise_clinica"));
				 bc.setPreco(rs.getDouble("preco"));
//				 lista.add(bc);
			 }
			 preparador.close();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
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
			}}
		return bc;
	}
	public Diverso buscaDosagemNome (String aux)
	{
		Diverso dosagen = new Diverso();
		String sql = "SELECT * FROM tbldosagem where id_dosagem like  ? or dosagem like ? ";
		try {
			con = Conexao.getConexao();  
			PreparedStatement preparador = con.prepareStatement(sql);
			 preparador.setString(1,aux);
			 preparador.setString(2,aux);
			 ResultSet rs = preparador.executeQuery();
			 while(rs.next())
			 {
				 dosagen.setId_dosagem(rs.getInt("id_dosagem"));
				 dosagen.setDosagem(rs.getString("dosagem"));
			 }
			 preparador.close();
		} catch (SQLException e) {
			
			System.out.println("Erro: "+e);
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
		return dosagen;
	}
	public List<Diverso> buscaModulos ()
	{
		List <Diverso> lista = new ArrayList<Diverso>();
		String sql = "SELECT * FROM TBLMODULO ORDER BY ID_MODULO ";
		try {
			con = Conexao.getConexao();  
			PreparedStatement preparador = con.prepareStatement(sql);
			 ResultSet rs = preparador.executeQuery();
			 while(rs.next())
			 {
				 Diverso md = new Diverso();
				 md.setId_mod(rs.getInt("id_modulo"));
				 md.setMod_nome(rs.getString("modulo"));
				 md.setMdlink(rs.getString("linkmod"));
				 md.setCodCl(rs.getString("codMod"));
				 lista.add(md);
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
		
		return lista;
	}
	
	public List<Diverso> buscaModulos (int grupo)
	{
		List <Diverso> lista = new ArrayList<Diverso>();
		String sql = "select DISTINCT id_modulo,modulo, linkmod,codMod, imagem,imagem_md  from vwacesso where id_grupo like ? order by id_modulo ";
		try {
			con = Conexao.getConexao();  
			PreparedStatement pr = con.prepareStatement(sql);
			 pr.setInt(1, grupo);
			 ResultSet rs = pr.executeQuery();
			 while(rs.next())
			 {
				 Diverso md = new Diverso();
				 md.setMod_nome(rs.getString("modulo"));
				 md.setMdlink(rs.getString("linkmod"));
				 md.setCodCl(rs.getString("codMod"));
				 md.setImagem(rs.getString("imagem"));
				 md.setImagem_md(rs.getString("imagem_md"));
				 lista.add(md);
			 }
			 pr.close();
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
		
		return lista;
	}
	
	public List<Diverso> buscaTelas (int grupo,String mod)
	{
		List <Diverso> lista = new ArrayList<Diverso>();
		String sql = "select DISTINCT id_tela, tela, linktela,codTela,imagens_tela from vwacesso where id_grupo like ? and codMod like ? order by id_tela ";
		try {
			con = Conexao.getConexao();  
			PreparedStatement pr = con.prepareStatement(sql);
			 pr.setInt(1, grupo);
			 pr.setString(2, mod);
			 ResultSet rs = pr.executeQuery();
			 while(rs.next())
			 {
				 Diverso md = new Diverso();
				 md.setTela(rs.getString("tela"));
				 md.setLinktela(rs.getString("linktela"));
				 md.setCodTela(rs.getString("codTela"));
				 lista.add(md);
			 }
			 pr.close();
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
		
		return lista;
	}
	
	
	
	public List<Diverso> buscaTCid ()
	{
		List <Diverso> lista = new ArrayList<Diverso>();
		String sql = "SELECT * FROM tbltela WHERE id_tela between 18 and 22 or id_tela between 95 and 95 ";
			
		try {
			con = Conexao.getConexao();  
			PreparedStatement pr = con.prepareStatement(sql);
			 ResultSet rs = pr.executeQuery();
			 while(rs.next())
			 {
				 Diverso md = new Diverso();
				 md.setTela(rs.getString("tela"));
				 md.setLinktela(rs.getString("link"));
				 md.setCodTela(rs.getString("codTela"));
				 lista.add(md);
			 }
			 pr.close();
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
		
		return lista;
	}
	
	
	public List<Diverso> buscaTConfig ()
	{
		List <Diverso> lista = new ArrayList<Diverso>();
		String sql = "SELECT * FROM tbltela WHERE id_tela = 17";
			
		try {
			con = Conexao.getConexao();  
			PreparedStatement pr = con.prepareStatement(sql);
			 ResultSet rs = pr.executeQuery();
			 while(rs.next())
			 {
				 Diverso md = new Diverso();
				 md.setTela(rs.getString("tela"));
				 md.setLinktela(rs.getString("link"));
				 md.setCodTela(rs.getString("codTela"));
				 lista.add(md);
			 }
			 pr.close();
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
		
		return lista;
	}
	
	
	public List<Diverso> buscaTInternamento (int grupo)
	{
		List <Diverso> lista = new ArrayList<Diverso>();
		String sql = "select DISTINCT id_tela, tela, linktela,codTela,imagens_tela from vwacesso where id_grupo = ? "
				+ "and (id_tela = 47 "
//				+ "OR id_tela = 53 "
//				+ "OR id_tela = 295 "
				+ "OR id_tela = 360 "
				+ "OR id_tela = 361 "
				+ "OR id_tela =362 "
				+ "OR id_tela = 363) order by id_tela";
		try {
			con = Conexao.getConexao();  
			PreparedStatement pr = con.prepareStatement(sql);
			pr.setInt(1, grupo);
			 ResultSet rs = pr.executeQuery();
			 while(rs.next())
			 {
				 Diverso md = new Diverso();
				 md.setTela(rs.getString("tela"));
				 md.setLinktela(rs.getString("linktela"));
				 md.setCodTela(rs.getString("codTela"));
				 md.setImagens_tela(rs.getString("imagens_tela"));
				 lista.add(md);
			 }
			 pr.close();
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
		return lista;
	}
	
	public List<Diverso> buscaProIntRequisicao (int grupo)
	{
		List <Diverso> lista = new ArrayList<Diverso>();
		String sql = "select DISTINCT id_tela, tela, linktela,codTela,imagens_tela from vwacesso where id_grupo = ? "
					+ "and id_tela between 296 and 305 order by id_tela ";
		try {  
			con = Conexao.getConexao();  
			PreparedStatement pr = con.prepareStatement(sql);
			 pr.setInt(1, grupo);
			 ResultSet rs = pr.executeQuery();
			 while(rs.next())
			 {
				 Diverso md = new Diverso();
				 md.setTela(rs.getString("tela"));
				 md.setLinktela(rs.getString("linktela"));
				 md.setCodTela(rs.getString("codTela"));
				 md.setImagens_tela(rs.getString("imagens_tela"));
				 lista.add(md);
			 }
			 pr.close();
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
		
		return lista;
	}
	
	//Banco de Urgencia e Emergencia
	
	public List<Diverso> buscaTBUE (int cod)
	{
		 
		List <Diverso> lista = new ArrayList<Diverso>();
 
		String sql = "select DISTINCT id_tela, tela, linktela,codTela,imagens_tela from vwacesso where id_grupo = ? and  id_tela between 75 and 78 order by id_tela";
 
		try {
			con = Conexao.getConexao();  
			PreparedStatement pr = con.prepareStatement(sql);
			pr.setInt(1, cod);
			 ResultSet rs = pr.executeQuery();
			 while(rs.next())
			 {
				 Diverso md = new Diverso();
				 md.setTela(rs.getString("tela"));
				 md.setLinktela(rs.getString("linktela"));
				 md.setCodTela(rs.getString("codTela"));
				 md.setImagens_tela(rs.getString("imagens_tela"));
				 lista.add(md);
			 }
			 pr.close();
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
		return lista;
	}
	
	public List<Diverso> buscaProBUERequisicao (int grupo)
	{
		List <Diverso> lista = new ArrayList<Diverso>();
		String sql = "select DISTINCT id_tela, tela, linktela,codTela,imagens_tela from vwacesso where id_grupo = ? and id_tela between 307 and 316 order by id_tela ";
		try {  
			con = Conexao.getConexao();  
			PreparedStatement pr = con.prepareStatement(sql);
			 pr.setInt(1, grupo);
			 ResultSet rs = pr.executeQuery();
			 while(rs.next())
			 {
				 Diverso md = new Diverso();
				 md.setTela(rs.getString("tela"));
				 md.setLinktela(rs.getString("linktela"));
				 md.setCodTela(rs.getString("codTela"));
				 md.setImagens_tela(rs.getString("imagens_tela"));
				 lista.add(md);
			 }
			 pr.close();
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
		
		return lista;
	}
	
	public List<Diverso> buscaProConsultaExternaGerir (int grupo)
	{
		List <Diverso> lista = new ArrayList<Diverso>();
		String sql = "select DISTINCT id_tela, tela, linktela,codTela,imagens_tela from vwacesso where id_grupo = ? and id_tela between 340 and 349 order by id_tela ";
		try {  
			con = Conexao.getConexao();  
			PreparedStatement pr = con.prepareStatement(sql);
			 pr.setInt(1, grupo);
			 ResultSet rs = pr.executeQuery();
			 while(rs.next())
			 {
				 Diverso md = new Diverso();
				 md.setTela(rs.getString("tela"));
				 md.setLinktela(rs.getString("linktela"));
				 md.setCodTela(rs.getString("codTela"));
				 md.setImagens_tela(rs.getString("imagens_tela"));
				 lista.add(md);
			 }
			 pr.close();
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
		
		return lista;
	}
//	Vacina��o
	public List<Diverso> buscaTelaVacinacao (int grupo)
	{
		List <Diverso> lista = new ArrayList<Diverso>();
		String sql = "select DISTINCT id_tela, tela, linktela,codTela,imagens_tela from vwacesso where id_grupo = ? and id_tela between 373 and 382 order by id_tela ";
		try {  
			con = Conexao.getConexao();  
			PreparedStatement pr = con.prepareStatement(sql);
			 pr.setInt(1, grupo);
			 ResultSet rs = pr.executeQuery();
			 while(rs.next())
			 {
				 Diverso md = new Diverso();
				 md.setTela(rs.getString("tela"));
				 md.setLinktela(rs.getString("linktela"));
				 md.setCodTela(rs.getString("codTela"));
				 md.setImagens_tela(rs.getString("imagens_tela"));
				 lista.add(md);
			 }
			 pr.close();
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
		
		return lista;
	}
	
	public List<Diverso> buscaTelagVacinacao (int grupo)
	{
		List <Diverso> lista = new ArrayList<Diverso>();
		String sql = "select DISTINCT id_tela, tela, linktela,codTela,imagens_tela from vwacesso where id_grupo = ? and id_tela = 452 or id_tela = 453 or id_tela = 454 order by id_tela ";
		try {  
			con = Conexao.getConexao();  
			PreparedStatement pr = con.prepareStatement(sql);
			 pr.setInt(1, grupo);
			 ResultSet rs = pr.executeQuery();
			 while(rs.next())
			 {
				 Diverso md = new Diverso();
				 md.setTela(rs.getString("tela"));
				 md.setLinktela(rs.getString("linktela"));
				 md.setCodTela(rs.getString("codTela"));
				 md.setImagens_tela(rs.getString("imagens_tela"));
				 lista.add(md);
			 }
			 pr.close();
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
		
		return lista;
	}
	
//	Hemoterapia
	public List<Diverso> buscaTelaHemoterapia(int grupo)
	{
		List <Diverso> lista = new ArrayList<Diverso>();
		String sql = "select DISTINCT id_tela, tela, linktela,codTela,imagens_tela from vwacesso where id_grupo = ? and id_tela between 383 and 392 order by id_tela ";
		try {  
			con = Conexao.getConexao();  
			PreparedStatement pr = con.prepareStatement(sql);
			 pr.setInt(1, grupo);
			 ResultSet rs = pr.executeQuery();
			 while(rs.next())
			 {
				 Diverso md = new Diverso();
				 md.setTela(rs.getString("tela"));
				 md.setLinktela(rs.getString("linktela"));
				 md.setCodTela(rs.getString("codTela"));
				 md.setImagens_tela(rs.getString("imagens_tela"));
				 lista.add(md);
			 }
			 pr.close();
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
		
		return lista;
	}
	
//	Ber�ario
	public List<Diverso> buscaTelaBercario(int grupo)
	{
		List <Diverso> lista = new ArrayList<Diverso>();
		String sql = "select DISTINCT id_tela, tela, linktela,codTela,imagens_tela from vwacesso where id_grupo = ? and id_tela between 393 and 402 order by id_tela ";
		try {  
			con = Conexao.getConexao();  
			PreparedStatement pr = con.prepareStatement(sql);
			 pr.setInt(1, grupo);
			 ResultSet rs = pr.executeQuery();
			 while(rs.next())
			 {
				 Diverso md = new Diverso();
				 md.setTela(rs.getString("tela"));
				 md.setLinktela(rs.getString("linktela"));
				 md.setCodTela(rs.getString("codTela"));
				 md.setImagens_tela(rs.getString("imagens_tela"));
				 lista.add(md);
			 }
			 pr.close();
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
		
		return lista;
	}
	
//	Ber�ario
	public List<Diverso> buscaTelaUTI(int grupo)
	{
		List <Diverso> lista = new ArrayList<Diverso>();
		String sql = "select DISTINCT id_tela, tela, linktela,codTela,imagens_tela from vwacesso where id_grupo = ? and id_tela between 403 and 412 order by id_tela ";
		try {  
			con = Conexao.getConexao();  
			PreparedStatement pr = con.prepareStatement(sql);
			 pr.setInt(1, grupo);
			 ResultSet rs = pr.executeQuery();
			 while(rs.next())
			 {
				 Diverso md = new Diverso();
				 md.setTela(rs.getString("tela"));
				 md.setLinktela(rs.getString("linktela"));
				 md.setCodTela(rs.getString("codTela"));
				 md.setImagens_tela(rs.getString("imagens_tela"));
				 lista.add(md);
			 }
			 pr.close();
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
		
		return lista;
	}
	
	//Atendimento
	
		public List<Diverso> buscaTAT (int cod)
		{
			 
			List <Diverso> lista = new ArrayList<Diverso>();
	 
			String sql = "select DISTINCT id_tela, tela, linktela,codTela,imagens_tela from vwacesso where id_grupo = ? and  id_tela between 79 and 81 order by id_tela";
	 
			try {
				con = Conexao.getConexao();  
				PreparedStatement pr = con.prepareStatement(sql);
				pr.setInt(1, cod);
				 ResultSet rs = pr.executeQuery();
				 while(rs.next())
				 {
					 Diverso md = new Diverso();
					 md.setTela(rs.getString("tela"));
					 md.setLinktela(rs.getString("linktela"));
					 md.setCodTela(rs.getString("codTela"));
					 md.setImagens_tela(rs.getString("imagens_tela"));
					 lista.add(md);
				 }
				 pr.close();
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
			return lista;
		}
		
		//Triagem no Banco Urgencia
		
			public List<Diverso> buscaTTG (int cod)
			{
				 
				List <Diverso> lista = new ArrayList<Diverso>();
		 
				String sql = "select DISTINCT id_tela, tela, linktela,codTela,imagens_tela from vwacesso where id_grupo = ? and  id_tela between 82 and 83 order by id_tela";
		 
				try {
					con = Conexao.getConexao();  
					PreparedStatement pr = con.prepareStatement(sql);
					pr.setInt(1, cod);
					 ResultSet rs = pr.executeQuery();
					 while(rs.next())
					 {
						 Diverso md = new Diverso();
						 md.setTela(rs.getString("tela"));
						 md.setLinktela(rs.getString("linktela"));
						 md.setCodTela(rs.getString("codTela"));
						 md.setImagens_tela(rs.getString("imagens_tela"));
						 lista.add(md);
					 }
					 pr.close();
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
				return lista;
			}
			
			// Banco Urgencia
			
			public List<Diverso> buscaTURG (int cod)
			{
				 
				List <Diverso> lista = new ArrayList<Diverso>();
		 
				String sql = "select DISTINCT id_tela, tela, linktela,codTela,imagens_tela from vwacesso where id_grupo = ? and  id_tela between 84 and 85 order by id_tela";
		 
				try {
					con = Conexao.getConexao();  
					PreparedStatement pr = con.prepareStatement(sql);
					pr.setInt(1, cod);
					 ResultSet rs = pr.executeQuery();
					 while(rs.next())
					 {
						 Diverso md = new Diverso();
						 md.setTela(rs.getString("tela"));
						 md.setLinktela(rs.getString("linktela"));
						 md.setCodTela(rs.getString("codTela"));
						 md.setImagens_tela(rs.getString("imagens_tela"));
						 lista.add(md);
					 }
					 pr.close();
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
				return lista;
			}
			
			// Banco Emergencia
			
			public List<Diverso> buscaTEMERG (int cod)
			{
				 
				List <Diverso> lista = new ArrayList<Diverso>();
		 
				String sql = "select DISTINCT id_tela, tela, linktela,codTela,imagens_tela from vwacesso where id_grupo = ? and  id_tela between 86 and 89 order by id_tela";
		 
				try {
					con = Conexao.getConexao();  
					PreparedStatement pr = con.prepareStatement(sql);
					pr.setInt(1, cod);
					 ResultSet rs = pr.executeQuery();
					 while(rs.next())
					 {
						 Diverso md = new Diverso();
						 md.setTela(rs.getString("tela"));
						 md.setLinktela(rs.getString("linktela"));
						 md.setCodTela(rs.getString("codTela"));
						 md.setImagens_tela(rs.getString("imagens_tela"));
						 lista.add(md);
					 }
					 pr.close();
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
				return lista;
			}
			
			
	public List<Diverso> buscaTRH (int cod)
	{
		 
		List <Diverso> lista = new ArrayList<Diverso>();
 
		String sql = "select DISTINCT id_tela, tela, linktela,codTela,imagens_tela from vwacesso where id_grupo = ? and  id_tela between 1 and 6 order by id_tela";
 
		try {
			con = Conexao.getConexao();  
			PreparedStatement pr = con.prepareStatement(sql);
			pr.setInt(1, cod);
			 ResultSet rs = pr.executeQuery();
			 while(rs.next())
			 {
				 Diverso md = new Diverso();
				 md.setTela(rs.getString("tela"));
				 md.setLinktela(rs.getString("linktela"));
				 md.setCodTela(rs.getString("codTela"));
				 md.setImagens_tela(rs.getString("imagens_tela"));
				 lista.add(md);
			 }
			 pr.close();
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
		return lista;
	}
	
	
	public List<Diverso> buscaTFORNEC (int cod)
	{
		 
		List <Diverso> lista = new ArrayList<Diverso>();
 
		String sql = "select DISTINCT id_tela, tela, linktela,codTela,imagens_tela from vwacesso where id_grupo = ? and  (id_tela = 96) order by id_tela";
 
		try {
			con = Conexao.getConexao();  
			PreparedStatement pr = con.prepareStatement(sql);
			pr.setInt(1, cod);
			 ResultSet rs = pr.executeQuery();
			 while(rs.next())
			 {
				 Diverso md = new Diverso();
				 md.setTela(rs.getString("tela"));
				 md.setLinktela(rs.getString("linktela"));
				 md.setCodTela(rs.getString("codTela"));
				 md.setImagens_tela(rs.getString("imagens_tela"));
				 lista.add(md);
			 }
			 pr.close();
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
		return lista;
	}
	
	
	public List<Diverso> buscaTINST (int cod)
	{
		 
		List <Diverso> lista = new ArrayList<Diverso>();
 
		String sql = "select DISTINCT id_tela, tela, linktela,codTela,imagens_tela from vwacesso where id_grupo = ? and  id_tela between 71 and 74 order by id_tela";
 
		try {
			con = Conexao.getConexao();  
			PreparedStatement pr = con.prepareStatement(sql);
			pr.setInt(1, cod);
			 ResultSet rs = pr.executeQuery();
			 while(rs.next())
			 {
				 Diverso md = new Diverso();
				 md.setTela(rs.getString("tela"));
				 md.setLinktela(rs.getString("linktela"));
				 md.setCodTela(rs.getString("codTela"));
				 md.setImagens_tela(rs.getString("imagens_tela"));
				 lista.add(md);
			 }
			 pr.close();
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
		return lista;
	}
	
	public List<Diverso> buscaTESTAT (int cod)
	{
		 
		List <Diverso> lista = new ArrayList<Diverso>();
 
		String sql = "select DISTINCT id_tela, tela, linktela,codTela,imagens_tela from vwacesso where id_grupo = ? and  id_tela between 140 and 143 or id_tela = 164 or id_tela = 177 order by id_tela";
 
		try {
			con = Conexao.getConexao();  
			PreparedStatement pr = con.prepareStatement(sql);
			pr.setInt(1, cod);
			 ResultSet rs = pr.executeQuery();
			 while(rs.next())
			 {
				 Diverso md = new Diverso();
				 md.setTela(rs.getString("tela"));
				 md.setLinktela(rs.getString("linktela"));
				 md.setCodTela(rs.getString("codTela"));
				 md.setImagens_tela(rs.getString("imagens_tela"));
				 lista.add(md);
			 }
			 pr.close();
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
		return lista;
	}
	
	//Estatistica Consultas Externas
	
	public List<Diverso> buscaTESTATCE (int grupo)
	{
		List <Diverso> lista = new ArrayList<Diverso>();
		String sql = "select DISTINCT id_tela, tela, linktela,codTela,imagens_tela from vwacesso where id_grupo = ? "
				+ "and (id_tela = 144"
				+ "	OR id_tela = 165"
				+ " OR id_tela = 167) order by id_tela";
		try {
			con = Conexao.getConexao();  
			PreparedStatement pr = con.prepareStatement(sql);
			 pr.setInt(1, grupo);
			 ResultSet rs = pr.executeQuery();
			 while(rs.next())
			 {
				 Diverso md = new Diverso();
				 md.setTela(rs.getString("tela"));
				 md.setLinktela(rs.getString("linktela"));
				 md.setCodTela(rs.getString("codTela"));
				 md.setImagens_tela(rs.getString("imagens_tela"));
				 lista.add(md);
			 }
			 pr.close();
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

		return lista;
	}
	
	public List<Diverso> buscaTESTATCES (int grupo)
	{
		List <Diverso> lista = new ArrayList<Diverso>();
		String sql = "select DISTINCT id_tela, tela, linktela,codTela,imagens_tela from vwacesso where id_grupo = ? "
				+ "and (id_tela = 168"
				+ "  OR id_tela = 169 OR id_tela = 198) order by id_tela";
		try {
			con = Conexao.getConexao();  
			PreparedStatement pr = con.prepareStatement(sql);
			 pr.setInt(1, grupo);
			 ResultSet rs = pr.executeQuery();
			 while(rs.next())
			 {
				 Diverso md = new Diverso();
				 md.setTela(rs.getString("tela"));
				 md.setLinktela(rs.getString("linktela"));
				 md.setCodTela(rs.getString("codTela"));
				 md.setImagens_tela(rs.getString("imagens_tela"));
				 lista.add(md);
			 }
			 pr.close();
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

		return lista;
	}

	public List<Diverso> buscaTESTATCEBUE (int grupo)
	{
		List <Diverso> lista = new ArrayList<Diverso>();
		String sql = "select DISTINCT id_tela, tela, linktela,codTela,imagens_tela from vwacesso where id_grupo = ? "
				+ "and (id_tela = 170"
				+ "  OR id_tela = 171"
				+ "  OR id_tela = 172"
				+ "  OR id_tela = 173) order by id_tela";
		try {
			con = Conexao.getConexao();  
			PreparedStatement pr = con.prepareStatement(sql);
			 pr.setInt(1, grupo);
			 ResultSet rs = pr.executeQuery();
			 while(rs.next())
			 {
				 Diverso md = new Diverso();
				 md.setTela(rs.getString("tela"));
				 md.setLinktela(rs.getString("linktela"));
				 md.setCodTela(rs.getString("codTela"));
				 md.setImagens_tela(rs.getString("imagens_tela"));
				 lista.add(md);
			 }
			 pr.close();
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

		return lista;
	}

	public List<Diverso> buscaTESTATCELAB (int grupo)
	{
		List <Diverso> lista = new ArrayList<Diverso>();
		String sql = "select DISTINCT id_tela, tela, linktela,codTela,imagens_tela from vwacesso where id_grupo = ? "
				+ "and (id_tela = 174"
				+ "  OR id_tela = 175"
				+ "  OR id_tela = 176) order by id_tela";
		try {
			con = Conexao.getConexao();  
			PreparedStatement pr = con.prepareStatement(sql);
			 pr.setInt(1, grupo);
			 ResultSet rs = pr.executeQuery();
			 while(rs.next())
			 {
				 Diverso md = new Diverso();
				 md.setTela(rs.getString("tela"));
				 md.setLinktela(rs.getString("linktela"));
				 md.setCodTela(rs.getString("codTela"));
				 md.setImagens_tela(rs.getString("imagens_tela"));
				 lista.add(md);
			 }
			 pr.close();
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

		return lista;
	}

	public List<Diverso> buscaTFATTesourariaTQ (int grupo)
	 {
	  List <Diverso> lista = new ArrayList<Diverso>();
	  String sql = "select DISTINCT id_tela, tela, linktela,codTela,imagens_tela from vwacesso where id_grupo = ? "
	    + "and (id_tela = 235 ) order by id_tela";
	  try {
	   con = Conexao.getConexao();  
	   PreparedStatement pr = con.prepareStatement(sql);
	    pr.setInt(1, grupo);
	    ResultSet rs = pr.executeQuery();
	    while(rs.next())
	    {
	     Diverso md = new Diverso();
	     md.setTela(rs.getString("tela"));
	     md.setLinktela(rs.getString("linktela"));
	     md.setCodTela(rs.getString("codTela"));
	     md.setImagens_tela(rs.getString("imagens_tela"));
	     lista.add(md);
	    }
	    pr.close();
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

	  return lista;
	 }
	
	public List<Diverso> buscaTFATFacturarTQTela (int grupo)
	 {
	  List <Diverso> lista = new ArrayList<Diverso>();
	  String sql = "select DISTINCT id_tela, tela, linktela,codTela,imagens_tela from vwacesso where id_grupo = ? "
	    + "and (id_tela = 236 or id_tela = 237 or id_tela = 238 or id_tela = 239 or id_tela = 240) order by id_tela";
	  try {
	   con = Conexao.getConexao();  
	   PreparedStatement pr = con.prepareStatement(sql);
	    pr.setInt(1, grupo);
	    ResultSet rs = pr.executeQuery();
	    while(rs.next())
	    {
	     Diverso md = new Diverso();
	     md.setTela(rs.getString("tela"));
	     md.setLinktela(rs.getString("linktela"));
	     md.setCodTela(rs.getString("codTela"));
	     md.setImagens_tela(rs.getString("imagens_tela"));
	     lista.add(md);
	    }
	    pr.close();
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

	  return lista;
	 }
	
	public List<Diverso> buscaTESTATPAG (int grupo)
	{
		List <Diverso> lista = new ArrayList<Diverso>();
		String sql = "select DISTINCT id_tela, tela, linktela,codTela,imagens_tela from vwacesso where id_grupo = ? "
				+ "and (id_tela = 178   OR id_tela = 179  OR id_tela = 180 OR id_tela = 181 OR id_tela = 182 OR id_tela = 189 OR id_tela = 190) order by id_tela";
		try {
			con = Conexao.getConexao();  
			PreparedStatement pr = con.prepareStatement(sql);
			 pr.setInt(1, grupo);
			 ResultSet rs = pr.executeQuery();
			 while(rs.next())
			 {
				 Diverso md = new Diverso();
				 md.setTela(rs.getString("tela"));
				 md.setLinktela(rs.getString("linktela"));
				 md.setCodTela(rs.getString("codTela"));
				 md.setImagens_tela(rs.getString("imagens_tela"));
				 lista.add(md);
			 }
			 pr.close();
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

		return lista;
	}

	
	public List<Diverso> buscaTADM (int grupo)
	{
		List <Diverso> lista = new ArrayList<Diverso>();
		String sql = "select DISTINCT id_tela, tela, linktela,codTela,imagens_tela from vwacesso where id_grupo = ? "
					+ "and ( id_tela = 16 "
					+ "OR id_tela = 17 "
					+ "OR id_tela = 29 "
					+ "OR id_tela = 30 "
					+ "OR id_tela = 31 "
					+ "OR id_tela = 35 "
					+ "OR id_tela = 43 "
					+ "OR id_tela = 45 "
					+ "OR id_tela = 70) order by id_tela";
		try {
			con = Conexao.getConexao();  
			PreparedStatement pr = con.prepareStatement(sql);
			 pr.setInt(1, grupo);
			 ResultSet rs = pr.executeQuery();
			 while(rs.next())
			 {
				 Diverso md = new Diverso();
				 md.setTela(rs.getString("tela"));
				 md.setLinktela(rs.getString("linktela"));
				 md.setCodTela(rs.getString("codTela"));
				 md.setImagens_tela(rs.getString("imagens_tela"));
				 lista.add(md);
			 }
			 pr.close();
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
		
		return lista;
	}
	
	public List<Diverso> buscaTFAT (int grupo)
	{
		List <Diverso> lista = new ArrayList<Diverso>();
		String sql = "select DISTINCT id_tela, tela, linktela,codTela,imagens_tela from vwacesso where id_grupo = ? "
				+ "and (id_tela = 54 OR id_tela = 55 OR id_tela = 56 OR id_tela = 57 OR id_tela = 114 OR id_tela = 115 OR id_tela = 234) order by id_tela  ";
		try {
			con = Conexao.getConexao();  
			PreparedStatement pr = con.prepareStatement(sql);
			 pr.setInt(1, grupo);
			 ResultSet rs = pr.executeQuery();
			 while(rs.next())
			 {
				 Diverso md = new Diverso();
				 md.setTela(rs.getString("tela"));
				 md.setLinktela(rs.getString("linktela"));
				 md.setCodTela(rs.getString("codTela"));
				 md.setImagens_tela(rs.getString("imagens_tela"));
				 lista.add(md);
			 }
			 pr.close();
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
//		System.out.println(lista.get(0).getLinktela());
		
		return lista;
	}

	public List<Diverso> buscaTFATATRZ (int grupo)
	{
		List <Diverso> lista = new ArrayList<Diverso>();
		String sql = "select DISTINCT id_tela, tela, linktela,codTela,imagens_tela from vwacesso where id_grupo = ? "
				+ "and (id_tela = 213 OR id_tela = 214 OR id_tela = 215 ) order by id_tela ";
		try {
			con = Conexao.getConexao();  
			PreparedStatement pr = con.prepareStatement(sql);
			 pr.setInt(1, grupo);
			 ResultSet rs = pr.executeQuery();
			 while(rs.next())
			 {
				 Diverso md = new Diverso();
				 md.setTela(rs.getString("tela"));
				 md.setLinktela(rs.getString("linktela"));
				 md.setCodTela(rs.getString("codTela"));
				 md.setImagens_tela(rs.getString("imagens_tela"));
				 lista.add(md);
			 }
			 pr.close();
			 con.close();
		} catch (Exception   e) {
			
			e.printStackTrace();
		}
		
		return lista;
	}
	
	public List<Diverso> buscaTFATPROD (int grupo)
	{
		List <Diverso> lista = new ArrayList<Diverso>();
		String sql = "select DISTINCT id_tela, tela, linktela,codTela,imagens_tela from vwacesso where id_grupo = ? "
				+ "and (id_tela = 157 OR id_tela = 158 OR id_tela = 159 OR id_tela = 160 ) order by id_tela ";
		try {
			con = Conexao.getConexao();  
			PreparedStatement pr = con.prepareStatement(sql);
			 pr.setInt(1, grupo);
			 ResultSet rs = pr.executeQuery();
			 while(rs.next())
			 {
				 Diverso md = new Diverso();
				 md.setTela(rs.getString("tela"));
				 md.setLinktela(rs.getString("linktela"));
				 md.setCodTela(rs.getString("codTela"));
				 md.setImagens_tela(rs.getString("imagens_tela"));
				 lista.add(md);
			 }
			 pr.close();
			 con.close();
		} catch (Exception   e) {
			
			e.printStackTrace();
		}
		
		return lista;
	}
	
	
	
	public List<Diverso> buscaTFATTeOutros (int grupo)
	{
		List <Diverso> lista = new ArrayList<Diverso>();
		String sql = "select DISTINCT id_tela, tela, linktela,codTela,imagens_tela from vwacesso where id_grupo = ? "
				+ "and (id_tela = 209 OR id_tela = 210 OR id_tela = 211 OR id_tela = 212 ) order by id_tela";
		try {
			con = Conexao.getConexao();  
			PreparedStatement pr = con.prepareStatement(sql);
			 pr.setInt(1, grupo);
			 ResultSet rs = pr.executeQuery();
			 while(rs.next())
			 {
				 Diverso md = new Diverso();
				 md.setTela(rs.getString("tela"));
				 md.setLinktela(rs.getString("linktela"));
				 md.setCodTela(rs.getString("codTela"));
				 md.setImagens_tela(rs.getString("imagens_tela"));
				 lista.add(md);
			 }
			 pr.close();
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

		return lista;
	}
	
	public List<Diverso> buscaTFATTesouraria (int grupo)
	{
		List <Diverso> lista = new ArrayList<Diverso>();
		String sql = "select DISTINCT id_tela, tela, linktela,codTela,imagens_tela from vwacesso where id_grupo = ? "
				+ "and (id_tela = 28 OR id_tela = 97 OR id_tela = 116 OR id_tela = 117 or id_tela = 118 or id_tela = 147 or id_tela = 199 or id_tela = 208 OR id_tela = 235 OR id_tela = 455) order by id_tela";
		try {
			con = Conexao.getConexao();  
			PreparedStatement pr = con.prepareStatement(sql);
			 pr.setInt(1, grupo);
			 ResultSet rs = pr.executeQuery();
			 while(rs.next())
			 {
				 Diverso md = new Diverso();
				 md.setTela(rs.getString("tela"));
				 md.setLinktela(rs.getString("linktela"));
				 md.setCodTela(rs.getString("codTela"));
				 md.setImagens_tela(rs.getString("imagens_tela"));
				 lista.add(md);
			 }
			 pr.close();
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

		return lista;
	}
	
	public List<Diverso> buscaTFATFacturarTQ (int grupo)
	 {
	  List <Diverso> lista = new ArrayList<Diverso>();
	  String sql = "select DISTINCT id_tela, tela, linktela,codTela,imagens_tela from vwacesso where id_grupo = ? "
	    + "and (id_tela = 236) order by id_tela";
	  try {
	   con = Conexao.getConexao();  
	   PreparedStatement pr = con.prepareStatement(sql);
	    pr.setInt(1, grupo);
	    ResultSet rs = pr.executeQuery();
	    while(rs.next())
	    {
	     Diverso md = new Diverso();
	     md.setTela(rs.getString("tela"));
	     md.setLinktela(rs.getString("linktela"));
	     md.setCodTela(rs.getString("codTela"));
	     md.setImagens_tela(rs.getString("imagens_tela"));
	     lista.add(md);
	    }
	    pr.close();
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

	  return lista;
	 }
	
	//Para pegar os dados da tela que permite mostrar a tela de confirmacao ao eliminar uma empresa
	public List<Diverso> buscarTelaDeConfElimEmp (int grupo) 
	 {
	  List <Diverso> lista = new ArrayList<Diverso>();
	  String sql = "select DISTINCT id_tela, tela, linktela,codTela from vwacesso where id_grupo = ? and id_tela = 243";
	  try {
	   con = Conexao.getConexao();  
	   PreparedStatement pr = con.prepareStatement(sql);
	    pr.setInt(1, grupo);
	    ResultSet rs = pr.executeQuery();
	    while(rs.next())
	    {
	     Diverso md = new Diverso();
	     md.setTela(rs.getString("tela"));
	     md.setLinktela(rs.getString("linktela"));
	     md.setCodTela(rs.getString("codTela"));
	     lista.add(md);
	    }
	    pr.close();
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
	  
	  return lista;
	 }
	
	public List<Diverso> buscarTelAndaTTQ (int grupo)
	  {
	   List <Diverso> lista = new ArrayList<Diverso>();
	   String sql = "select DISTINCT id_tela, tela, linktela,codTela from vwacesso where id_grupo = ? and id_tela = 242";//232
	   try {
	    con = Conexao.getConexao();  
	    PreparedStatement pr = con.prepareStatement(sql);
	     pr.setInt(1, grupo);
	     ResultSet rs = pr.executeQuery();
	     while(rs.next())
	     {
	      Diverso md = new Diverso();
	      md.setTela(rs.getString("tela"));
	      md.setLinktela(rs.getString("linktela"));
	      md.setCodTela(rs.getString("codTela"));
	      lista.add(md);
	     }
	     pr.close();
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
	   
	   return lista;
	  }
	
	public List<Diverso> buscarTelaDeTesteQualidade (int grupo)
	 {
	  List <Diverso> lista = new ArrayList<Diverso>();
	  String sql = "select DISTINCT id_tela, tela, linktela,codTela from vwacesso where id_grupo = ? and (id_tela = 232 OR id_tela = 233)";
	  try {
	   con = Conexao.getConexao();  
	   PreparedStatement pr = con.prepareStatement(sql);
	    pr.setInt(1, grupo);
	    ResultSet rs = pr.executeQuery();
	    while(rs.next())
	    {
	     Diverso md = new Diverso();
	     md.setTela(rs.getString("tela"));
	     md.setLinktela(rs.getString("linktela"));
	     md.setCodTela(rs.getString("codTela"));
	     lista.add(md);
	    }
	    pr.close();
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
	  
	  return lista;
	 }
	
	public List<Diverso> buscaTFATCons (int grupo)
	{
		List <Diverso> lista = new ArrayList<Diverso>();
		String sql = "select DISTINCT id_tela, tela, linktela,codTela,imagens_tela from vwacesso where id_grupo = ? "
				+ "and (id_tela = 151 OR id_tela = 152 OR id_tela = 228 OR id_tela = 229 OR id_tela = 230  ) order by id_tela   ";
		try {
			con = Conexao.getConexao();  
			PreparedStatement pr = con.prepareStatement(sql);
			 pr.setInt(1, grupo);
			 ResultSet rs = pr.executeQuery();
			 while(rs.next())
			 {
				 Diverso md = new Diverso();
				 md.setTela(rs.getString("tela"));
				 md.setLinktela(rs.getString("linktela"));
				 md.setCodTela(rs.getString("codTela"));
				 md.setImagens_tela(rs.getString("imagens_tela"));
				 lista.add(md);
			 }
			 pr.close();
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

		return lista;
	}
	
	public List<Diverso> buscaTFATConsConfirm (int grupo)
	{
		List <Diverso> lista = new ArrayList<Diverso>();
		String sql = "select DISTINCT id_tela, tela, linktela,codTela,imagens_tela from vwacesso where id_grupo = ? "
				+ "and (id_tela = 228  ) order by id_tela   ";
		try {
			con = Conexao.getConexao();  
			PreparedStatement pr = con.prepareStatement(sql);
			 pr.setInt(1, grupo);
			 ResultSet rs = pr.executeQuery();
			 while(rs.next())
			 {
				 Diverso md = new Diverso();
				 md.setTela(rs.getString("tela"));
				 md.setLinktela(rs.getString("linktela"));
				 md.setCodTela(rs.getString("codTela"));
				 md.setImagens_tela(rs.getString("imagens_tela"));
				 lista.add(md);
			 }
			 pr.close();
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

		return lista;
	}
	
	public List<Diverso> buscaTFATExames (int grupo)
	{
		List <Diverso> lista = new ArrayList<Diverso>();
		String sql = "select DISTINCT id_tela, tela, linktela,codTela,imagens_tela from vwacesso where id_grupo = ? "
				+ "and (id_tela = 124 OR id_tela = 120 OR id_tela = 121 or id_tela = 122 or id_tela = 123 or id_tela = 249) order by id_tela";
		try {
			con = Conexao.getConexao();  
			PreparedStatement pr = con.prepareStatement(sql);
			 pr.setInt(1, grupo);
			 ResultSet rs = pr.executeQuery();
			 while(rs.next())
			 {
				 Diverso md = new Diverso();
				 md.setTela(rs.getString("tela"));
				 md.setLinktela(rs.getString("linktela"));
				 md.setCodTela(rs.getString("codTela"));
				 md.setImagens_tela(rs.getString("imagens_tela"));
				 lista.add(md);
			 }
			 pr.close();
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

		return lista;
	}
	
	public List<Diverso> buscaTFATAbrirCaixa (int grupo)
	{
		List <Diverso> lista = new ArrayList<Diverso>();
		String sql = "select DISTINCT id_tela, tela, linktela,codTela,imagens_tela from vwacesso where id_grupo = ? "
				+ "and (id_tela = 125 OR id_tela = 126 OR id_tela = 127 or id_tela = 128 ) order by id_tela";
		try {
			con = Conexao.getConexao();  
			PreparedStatement pr = con.prepareStatement(sql);
			 pr.setInt(1, grupo);
			 ResultSet rs = pr.executeQuery();
			 while(rs.next())
			 {
				 Diverso md = new Diverso();
				 md.setTela(rs.getString("tela"));
				 md.setLinktela(rs.getString("linktela"));
				 md.setCodTela(rs.getString("codTela"));
				 md.setImagens_tela(rs.getString("imagens_tela"));
				 lista.add(md);
			 }
			 pr.close();
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

		return lista;
	}
	
	public List<Diverso> buscaTFATTesourariaCSG (int grupo)
	{
		List <Diverso> lista = new ArrayList<Diverso>();
		String sql = "select DISTINCT id_tela, tela, linktela,codTela,imagens_tela from vwacesso where id_grupo = ? "
				+ "and (id_tela = 58 or id_tela = 119) order by id_tela";
		try {
			con = Conexao.getConexao();  
			PreparedStatement pr = con.prepareStatement(sql);
			 pr.setInt(1, grupo);
			 ResultSet rs = pr.executeQuery();
			 while(rs.next())
			 {
				 Diverso md = new Diverso();
				 md.setTela(rs.getString("tela"));
				 md.setLinktela(rs.getString("linktela"));
				 md.setCodTela(rs.getString("codTela"));
				 md.setImagens_tela(rs.getString("imagens_tela"));
				 lista.add(md);
			 }
			 pr.close();
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

		return lista;
	}
	
	public List<Diverso> buscaTFATIsencao (int grupo)
	{
		List <Diverso> lista = new ArrayList<Diverso>();
		String sql = "select DISTINCT id_tela, tela, linktela,codTela,imagens_tela from vwacesso where id_grupo = ? "
				+ "and (id_tela = 147 or id_tela = 148 or id_tela = 253) order by id_tela"; 
		try {
			con = Conexao.getConexao();  
			PreparedStatement pr = con.prepareStatement(sql);
			 pr.setInt(1, grupo);
			 ResultSet rs = pr.executeQuery();
			 while(rs.next())
			 {
				 Diverso md = new Diverso();
				 md.setTela(rs.getString("tela"));
				 md.setLinktela(rs.getString("linktela"));
				 md.setCodTela(rs.getString("codTela"));
				 md.setImagens_tela(rs.getString("imagens_tela"));
				 lista.add(md);
			 }
			 pr.close();
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

		return lista;
	}
	
	public List<Diverso> buscaTFATFSG (int grupo)
	{
		List <Diverso> lista = new ArrayList<Diverso>();
		String sql = "select DISTINCT id_tela, tela, linktela,codTela,imagens_tela from vwacesso where id_grupo = ? "
				+ "and (id_tela = 134 or id_tela = 135 or id_tela = 136 or id_tela = 137 or id_tela = 138 or id_tela = 139) order by id_tela = 137, id_tela = 136, id_tela = 135, id_tela = 134, id_tela = 139, id_tela = 138";
		try {
			con = Conexao.getConexao();  
			PreparedStatement pr = con.prepareStatement(sql);
			 pr.setInt(1, grupo);
			 ResultSet rs = pr.executeQuery();
			 while(rs.next())
			 {
				 Diverso md = new Diverso();
				 md.setTela(rs.getString("tela"));
				 md.setLinktela(rs.getString("linktela"));
				 md.setCodTela(rs.getString("codTela"));
				 md.setImagens_tela(rs.getString("imagens_tela"));
				 lista.add(md);
			 }
			 pr.close();
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

		return lista;
	}

	// Metodo que lista as telas do Mudulo Agenda
	public List<Diverso> buscaTAG (int grupo)
	{
		List <Diverso> lista = new ArrayList<Diverso>();
		String sql = "select DISTINCT id_tela, tela, linktela,codTela,imagens_tela from vwacesso where id_grupo = ? "
					+ "and (id_tela = 39 OR id_tela = 40 OR id_tela = 231) order by id_tela";
		try {
			 con = Conexao.getConexao();  
			 PreparedStatement pr = con.prepareStatement(sql);
			 pr.setInt(1, grupo);
			 ResultSet rs = pr.executeQuery();
			 while(rs.next())
			 {
				 Diverso md = new Diverso();
				 md.setTela(rs.getString("tela"));
				 md.setLinktela(rs.getString("linktela"));
				 md.setCodTela(rs.getString("codTela"));
				 md.setImagens_tela(rs.getString("imagens_tela"));
				 lista.add(md);
			 }
			 pr.close();
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
		
		return lista;
	}
	
	
	// Metodo que lista as telas do Mudulo Farmacia
		public List<Diverso> buscaTFAR (int grupo)
		{
			List <Diverso> lista = new ArrayList<Diverso>();
			String sql = "select DISTINCT id_tela, tela, linktela,codTela,imagens_tela from vwacesso where id_grupo = ? "
						+ "and (id_tela = 59 OR id_tela = 61 OR id_tela = 62 OR id_tela = 254) order by id_tela";
			try {
				con = Conexao.getConexao();  
				PreparedStatement pr = con.prepareStatement(sql);
				 pr.setInt(1, grupo);
				 ResultSet rs = pr.executeQuery();
				 while(rs.next())
				 {
					 Diverso md = new Diverso();
					 md.setTela(rs.getString("tela"));
					 md.setLinktela(rs.getString("linktela"));
					 md.setCodTela(rs.getString("codTela"));
					 md.setImagens_tela(rs.getString("imagens_tela"));
					 lista.add(md);
				 }
				 pr.close();
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
			
			return lista;
		}
		
		// Metodo que lista as telas do Mudulo Farmacia Requisi��o Interna
				public List<Diverso> buscaTFARInterna (int grupo)
				{
					List <Diverso> lista = new ArrayList<Diverso>();
					String sql = "select DISTINCT id_tela, tela, linktela,codTela,imagens_tela from vwacesso where id_grupo = ? and ( id_tela = 277 or id_tela = 276 or id_tela = 261 or id_tela = 260 or id_tela = 259 or id_tela = 258 or id_tela = 257 or id_tela = 256 or id_tela = 275 or id_tela = 275 or id_tela = 258 or id_tela = 255) order by id_tela = 277, id_tela = 276, id_tela = 261, id_tela = 260, id_tela = 259, id_tela = 257,id_tela = 256, id_tela = 275, id_tela = 275, id_tela = 255, id_tela = 258";
					try {  
						con = Conexao.getConexao();  
						PreparedStatement pr = con.prepareStatement(sql);
						 pr.setInt(1, grupo);
						 ResultSet rs = pr.executeQuery();
						 while(rs.next())
						 {
							 Diverso md = new Diverso();
							 md.setTela(rs.getString("tela"));
							 md.setLinktela(rs.getString("linktela"));
							 md.setCodTela(rs.getString("codTela"));
							 md.setImagens_tela(rs.getString("imagens_tela"));
							 lista.add(md);
						 }
						 pr.close();
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
					
					return lista;
				}
				
				// Metodo que lista as telas do Mudulo Farmacia Requisi��o Interna
				public List<Diverso> buscaTFARInternaDentro (int grupo)
				{
					List <Diverso> lista = new ArrayList<Diverso>();
					String sql = "select DISTINCT id_tela, tela, linktela,codTela,imagens_tela from vwacesso where id_grupo = ? "
								+ "and (id_tela between 262 and 265) order by id_tela";
					try {  
						con = Conexao.getConexao();  
						PreparedStatement pr = con.prepareStatement(sql);
						 pr.setInt(1, grupo);
						 ResultSet rs = pr.executeQuery();
						 while(rs.next())
						 {
							 Diverso md = new Diverso();
							 md.setTela(rs.getString("tela"));
							 md.setLinktela(rs.getString("linktela"));
							 md.setCodTela(rs.getString("codTela"));
							 md.setImagens_tela(rs.getString("imagens_tela"));
							 lista.add(md);
						 }
						 pr.close();
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
					
					return lista;
				}
		
		public List<Diverso> buscaTPFAR (int grupo)
		{
			List <Diverso> lista = new ArrayList<Diverso>();
			String sql = "select DISTINCT id_tela, tela, linktela,codTela,imagens_tela from vwacesso where id_grupo = ? and (id_tela = 274 or id_tela = 278 or id_tela = 63 OR id_tela = 65 OR id_tela = 66 OR id_tela = 90 OR id_tela = 67 OR id_tela = 68) order by id_tela = 274, id_tela = 278, id_tela = 68, id_tela = 67, id_tela = 90, id_tela = 66, id_tela = 65, id_tela = 63";
			try {
				con = Conexao.getConexao();  
				PreparedStatement pr = con.prepareStatement(sql);
				 pr.setInt(1, grupo);
				 ResultSet rs = pr.executeQuery();
				 while(rs.next())
				 {
					 Diverso md = new Diverso();
					 md.setTela(rs.getString("tela"));
					 md.setLinktela(rs.getString("linktela"));
					 md.setCodTela(rs.getString("codTela"));
					 md.setImagens_tela(rs.getString("imagens_tela"));
					 lista.add(md);
				 }
				 pr.close();
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
			
			return lista;
		}
		
		public List<Diverso> buscaTPFARPersonalizar (int grupo)
		{
			List <Diverso> lista = new ArrayList<Diverso>();
			String sql = "select DISTINCT id_tela, tela, linktela,codTela,imagens_tela from vwacesso where id_grupo = ? and id_tela = 69 order by id_tela = 69";
			try {
				con = Conexao.getConexao();  
				PreparedStatement pr = con.prepareStatement(sql);
				 pr.setInt(1, grupo);
				 ResultSet rs = pr.executeQuery();
				 while(rs.next())
				 {
					 Diverso md = new Diverso();
					 md.setTela(rs.getString("tela"));
					 md.setLinktela(rs.getString("linktela"));
					 md.setCodTela(rs.getString("codTela"));
					 md.setImagens_tela(rs.getString("imagens_tela"));
					 lista.add(md);
				 }
				 pr.close();
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
			
			return lista;
		}
		
		public List<Diverso> buscaTPFARCadastrarProd (int grupo)
		{
			List <Diverso> lista = new ArrayList<Diverso>();
			String sql = "select DISTINCT id_tela, tela, linktela,codTela,imagens_tela from vwacesso where id_grupo = ? and id_tela = 64 order by id_tela";
			try {
				con = Conexao.getConexao();  
				PreparedStatement pr = con.prepareStatement(sql);
				 pr.setInt(1, grupo);
				 ResultSet rs = pr.executeQuery();
				 while(rs.next())
				 {
					 Diverso md = new Diverso();
					 md.setTela(rs.getString("tela"));
					 md.setLinktela(rs.getString("linktela"));
					 md.setCodTela(rs.getString("codTela"));
					 md.setImagens_tela(rs.getString("imagens_tela"));
					 lista.add(md);
				 }
				 pr.close();
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
			
			return lista;
		}
		
//		Centro de distribui��o cussunga
		public List<Diverso> buscaTPFARCentroDistr (int grupo)
		{
			List <Diverso> lista = new ArrayList<Diverso>();
			String sql = "select DISTINCT id_tela, tela, linktela,codTela,imagens_tela from vwacesso where id_grupo = ? and id_tela = 266  order by id_tela"; //or id_tela = 268 or id_tela = 269 or id_tela = 270,  tiremos outros boloes na 
			try {
				con = Conexao.getConexao();  
				PreparedStatement pr = con.prepareStatement(sql);
				 pr.setInt(1, grupo);
				 ResultSet rs = pr.executeQuery();
				 while(rs.next())
				 {
					 Diverso md = new Diverso();
					 md.setTela(rs.getString("tela"));
					 md.setLinktela(rs.getString("linktela"));
					 md.setCodTela(rs.getString("codTela"));
					 md.setImagens_tela(rs.getString("imagens_tela"));
					 lista.add(md);
				 }
				 pr.close();
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
			
			return lista;
		}
		
//		Centro de distribui��o telas cussunga
		public List<Diverso> buscaTPFARCentroDistrTelas (int grupo)
		{
			List <Diverso> lista = new ArrayList<Diverso>();
			String sql = "select DISTINCT id_tela, tela, linktela,codTela,imagens_tela from vwacesso where id_grupo = ? and  id_tela = 272 or id_tela = 273 or id_tela = 267 order by id_tela =267, id_tela = 273, id_tela = 272";
			try {
				con = Conexao.getConexao();  
				PreparedStatement pr = con.prepareStatement(sql);
				 pr.setInt(1, grupo);
				 ResultSet rs = pr.executeQuery();
				 while(rs.next())
				 {
					 Diverso md = new Diverso();
					 md.setTela(rs.getString("tela"));
					 md.setLinktela(rs.getString("linktela"));
					 md.setCodTela(rs.getString("codTela"));
					 md.setImagens_tela(rs.getString("imagens_tela"));
					 lista.add(md);
				 }
				 pr.close();
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
			
			return lista;
		}
		
		public List<Diverso> buscaTREL (int grupo)
		{
			List <Diverso> lista = new ArrayList<Diverso>();
			String sql = "select DISTINCT id_tela, tela, linktela,codTela,imagens_tela from vwacesso where id_grupo = ? and  id_tela between 98 and 103 or id_tela = 216 order by id_tela";
			try {
				con = Conexao.getConexao();  
				PreparedStatement pr = con.prepareStatement(sql);
				 pr.setInt(1, grupo);
				 ResultSet rs = pr.executeQuery();
				 while(rs.next())
				 {
					 Diverso md = new Diverso();
					 md.setTela(rs.getString("tela"));
					 md.setLinktela(rs.getString("linktela"));
					 md.setCodTela(rs.getString("codTela"));
					 md.setImagens_tela(rs.getString("imagens_tela"));
					 lista.add(md);
				 }
				 pr.close();
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
			
			return lista;
		}
		
		public List<Diverso> buscaTRELLAB (int grupo)
		{
			List <Diverso> lista = new ArrayList<Diverso>();
			String sql = "select DISTINCT id_tela, tela, linktela,codTela,imagens_tela from vwacesso where id_grupo = ? and  id_tela between 217 and 220   order by id_tela";
			try {
				con = Conexao.getConexao();  
				PreparedStatement pr = con.prepareStatement(sql);
				 pr.setInt(1, grupo);
				 ResultSet rs = pr.executeQuery();
				 while(rs.next())
				 {
					 Diverso md = new Diverso();
					 md.setTela(rs.getString("tela"));
					 md.setLinktela(rs.getString("linktela"));
					 md.setCodTela(rs.getString("codTela"));
					 md.setImagens_tela(rs.getString("imagens_tela"));
					 lista.add(md);
				 }
				 pr.close();
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
			
			return lista;
		}
		
		public List<Diverso> buscaTRELLABGerirStockLab (int grupo)
		{
			List <Diverso> lista = new ArrayList<Diverso>();
			String sql = "select DISTINCT id_tela, tela, linktela,codTela,imagens_tela from vwacesso where id_grupo = ? and  id_tela between 318 and 327 order by id_tela";
			try {
				con = Conexao.getConexao();  
				PreparedStatement pr = con.prepareStatement(sql);
				 pr.setInt(1, grupo);
				 ResultSet rs = pr.executeQuery();
				 while(rs.next())
				 {
					 Diverso md = new Diverso();
					 md.setTela(rs.getString("tela"));
					 md.setLinktela(rs.getString("linktela"));
					 md.setCodTela(rs.getString("codTela"));
					 md.setImagens_tela(rs.getString("imagens_tela"));
					 lista.add(md);
				 }
				 pr.close();
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
			
			return lista;
		}
		
		public List<Diverso> buscaTRELLABGerirStockImagio (int grupo)
		{
			List <Diverso> lista = new ArrayList<Diverso>();
			String sql = "select DISTINCT id_tela, tela, linktela,codTela,imagens_tela from vwacesso where id_grupo = ? and  id_tela between 329 and 338 order by id_tela";
			try {
				con = Conexao.getConexao();  
				PreparedStatement pr = con.prepareStatement(sql);
				 pr.setInt(1, grupo);
				 ResultSet rs = pr.executeQuery();
				 while(rs.next())
				 {
					 Diverso md = new Diverso();
					 md.setTela(rs.getString("tela"));
					 md.setLinktela(rs.getString("linktela"));
					 md.setCodTela(rs.getString("codTela"));
					 md.setImagens_tela(rs.getString("imagens_tela"));
					 lista.add(md);
				 }
				 pr.close();
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
			
			return lista;
		}
		
		public List<Diverso> buscaTRELPRoperatorio (int grupo)
		{
			List <Diverso> lista = new ArrayList<Diverso>();
			String sql = "select DISTINCT id_tela, tela, linktela,codTela,imagens_tela from vwacesso where id_grupo = ? and  id_tela = 424 order by id_tela";
			try {
				con = Conexao.getConexao();  
				PreparedStatement pr = con.prepareStatement(sql);
				 pr.setInt(1, grupo);
				 ResultSet rs = pr.executeQuery();
				 while(rs.next())
				 {
					 Diverso md = new Diverso();
					 md.setTela(rs.getString("tela"));
					 md.setLinktela(rs.getString("linktela"));
					 md.setCodTela(rs.getString("codTela"));
					 md.setImagens_tela(rs.getString("imagens_tela"));
					 lista.add(md);
				 }
				 pr.close();
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
			
			return lista;
		} 
		
		public List<Diverso> buscaTRELPROCOperatorio (int grupo)
		{
			List <Diverso> lista = new ArrayList<Diverso>();
			String sql = "select DISTINCT id_tela, tela, linktela,codTela,imagens_tela from vwacesso where id_grupo = ? and  id_tela between 414 and 423 order by id_tela";
			try {
				con = Conexao.getConexao();  
				PreparedStatement pr = con.prepareStatement(sql);
				 pr.setInt(1, grupo);
				 ResultSet rs = pr.executeQuery();
				 while(rs.next())
				 {
					 Diverso md = new Diverso();
					 md.setTela(rs.getString("tela"));
					 md.setLinktela(rs.getString("linktela"));
					 md.setCodTela(rs.getString("codTela"));
					 md.setImagens_tela(rs.getString("imagens_tela"));
					 lista.add(md);
				 }
				 pr.close();
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
			
			return lista;
		}
		
		public List<Diverso> buscaTRELPROCGinasio (int grupo)
		{
			List <Diverso> lista = new ArrayList<Diverso>();
			String sql = "select DISTINCT id_tela, tela, linktela,codTela,imagens_tela from vwacesso where id_grupo = ? and  id_tela between 426 and 435 order by id_tela";
			try {
				con = Conexao.getConexao();  
				PreparedStatement pr = con.prepareStatement(sql);
				 pr.setInt(1, grupo);
				 ResultSet rs = pr.executeQuery();
				 while(rs.next())
				 {
					 Diverso md = new Diverso();
					 md.setTela(rs.getString("tela"));
					 md.setLinktela(rs.getString("linktela"));
					 md.setCodTela(rs.getString("codTela"));
					 md.setImagens_tela(rs.getString("imagens_tela"));
					 lista.add(md);
				 }
				 pr.close();
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
			
			return lista;
		}
	
		public List<Diverso> buscaTAGEND (int grupo)
		{
			List <Diverso> lista = new ArrayList<Diverso>();
			String sql = "select DISTINCT id_tela, tela, linktela,codTela,imagens_tela from vwacesso where id_grupo = ? and  id_tela between 104 and 105 order by id_tela";
			try {
				con = Conexao.getConexao();  
				PreparedStatement pr = con.prepareStatement(sql);
				 pr.setInt(1, grupo);
				 ResultSet rs = pr.executeQuery();
				 while(rs.next())
				 {
					 Diverso md = new Diverso();
					 md.setTela(rs.getString("tela"));
					 md.setLinktela(rs.getString("linktela"));
					 md.setCodTela(rs.getString("codTela"));
					 md.setImagens_tela(rs.getString("imagens_tela"));
					 lista.add(md);
				 }
				 pr.close();
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
			
			return lista;
		}
		
		
		public List<Diverso> buscaTAGENDTestq (int grupo)
		{
			List <Diverso> lista = new ArrayList<Diverso>();
			String sql = "select DISTINCT id_tela, tela, linktela,codTela,imagens_tela from vwacesso where id_grupo = ? and  id_tela = 246 order by id_tela";
			try {
				con = Conexao.getConexao();  
				PreparedStatement pr = con.prepareStatement(sql);
				 pr.setInt(1, grupo);
				 ResultSet rs = pr.executeQuery();
				 while(rs.next())
				 {
					 Diverso md = new Diverso();
					 md.setTela(rs.getString("tela"));
					 md.setLinktela(rs.getString("linktela"));
					 md.setCodTela(rs.getString("codTela"));
					 md.setImagens_tela(rs.getString("imagens_tela"));
					 lista.add(md);
				 }
				 pr.close();
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
			
			return lista;
		}
		
		
		public List<Diverso> buscaTAGENDExams (int grupo)
		{
			List <Diverso> lista = new ArrayList<Diverso>();
			String sql = "select DISTINCT id_tela, tela, linktela,codTela,imagens_tela from vwacesso where id_grupo = ? and  id_tela between 191 and 192 order by id_tela";
			try {
				con = Conexao.getConexao();  
				PreparedStatement pr = con.prepareStatement(sql);
				 pr.setInt(1, grupo);
				 ResultSet rs = pr.executeQuery();
				 while(rs.next())
				 {
					 Diverso md = new Diverso();
					 md.setTela(rs.getString("tela"));
					 md.setLinktela(rs.getString("linktela"));
					 md.setCodTela(rs.getString("codTela"));
					 md.setImagens_tela(rs.getString("imagens_tela"));
					 lista.add(md);
				 }
				 pr.close();
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
			
			return lista;
		}
		
		public List<Diverso> buscaTAGENDTs (int grupo)
		{
			List <Diverso> lista = new ArrayList<Diverso>();
			String sql = "select DISTINCT id_tela, tela, linktela,codTela,imagens_tela from vwacesso where id_grupo = ? and  id_tela between 161 and 163 or id_tela=244 order by id_tela";
			try {
				con = Conexao.getConexao();  
				PreparedStatement pr = con.prepareStatement(sql);
				 pr.setInt(1, grupo);
				 ResultSet rs = pr.executeQuery();
				 while(rs.next())
				 {
					 Diverso md = new Diverso();
					 md.setTela(rs.getString("tela"));
					 md.setLinktela(rs.getString("linktela"));
					 md.setCodTela(rs.getString("codTela"));
					 md.setImagens_tela(rs.getString("imagens_tela"));
					 lista.add(md);
				 }
				 pr.close();
				 con.close();
			} catch ( Exception e) {
				
				e.printStackTrace();
			}
			   
			
			return lista;
		}
		
		public List<Diverso> buscaTAGENDPac (int grupo)
		{
			List <Diverso> lista = new ArrayList<Diverso>();
			String sql = "select DISTINCT id_tela, tela, linktela,codTela,imagens_tela from vwacesso where id_grupo = ? and  id_tela between 106 and 106 order by id_tela";
			try {
				con = Conexao.getConexao();  
				PreparedStatement pr = con.prepareStatement(sql);
				 pr.setInt(1, grupo);
				 ResultSet rs = pr.executeQuery();
				 while(rs.next())
				 {
					 Diverso md = new Diverso();
					 md.setTela(rs.getString("tela"));
					 md.setLinktela(rs.getString("linktela"));
					 md.setCodTela(rs.getString("codTela"));
					 md.setImagens_tela(rs.getString("imagens_tela"));
					 lista.add(md);
				 }
				 pr.close();
				 con.close();
			} catch ( Exception e) {
				
				e.printStackTrace();
			}
			   
			
			return lista;
		}
		
		public List<Diverso> buscaTCONS (int grupo)
		{
			List <Diverso> lista = new ArrayList<Diverso>();
			String sql = "select DISTINCT id_tela, tela, linktela,codTela,imagens_tela from vwacesso where id_grupo = ? and  id_tela = 107 order by id_tela";
			try {
				con = Conexao.getConexao();  
				PreparedStatement pr = con.prepareStatement(sql);
				 pr.setInt(1, grupo);
				 ResultSet rs = pr.executeQuery();
				 while(rs.next())
				 {
					 Diverso md = new Diverso();
					 md.setTela(rs.getString("tela"));
					 md.setLinktela(rs.getString("linktela"));
					 md.setCodTela(rs.getString("codTela"));
					 md.setImagens_tela(rs.getString("imagens_tela"));
					 lista.add(md);
				 }
				 pr.close();
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
			
			return lista;
		}
		
		
		public List<Diverso> buscaTTRIAG (int grupo)
		{
			List <Diverso> lista = new ArrayList<Diverso>();
			String sql = "select DISTINCT id_tela, tela, linktela,codTela,imagens_tela from vwacesso where id_grupo = ? and  id_tela = 108 order by id_tela";
			try {
				con = Conexao.getConexao();  
				PreparedStatement pr = con.prepareStatement(sql);
				 pr.setInt(1, grupo);
				 ResultSet rs = pr.executeQuery();
				 while(rs.next())
				 {
					 Diverso md = new Diverso();
					 md.setTela(rs.getString("tela"));
					 md.setLinktela(rs.getString("linktela"));
					 md.setCodTela(rs.getString("codTela"));
					 md.setImagens_tela(rs.getString("imagens_tela"));
					 lista.add(md);
				 }
				 pr.close();
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
			
			return lista;
		}
		
		public List<Diverso> buscaTPAGAM (int grupo)
		{
			List <Diverso> lista = new ArrayList<Diverso>();
			String sql = "select DISTINCT id_tela, tela, linktela,codTela,imagens_tela from vwacesso where id_grupo = ? and  id_tela between 183 and 188 or id_tela=245 order by id_tela";
			try {
				con = Conexao.getConexao();  
				PreparedStatement pr = con.prepareStatement(sql);
				 pr.setInt(1, grupo);
				 ResultSet rs = pr.executeQuery();
				 while(rs.next())
				 {
					 Diverso md = new Diverso();
					 md.setTela(rs.getString("tela"));
					 md.setLinktela(rs.getString("linktela"));
					 md.setCodTela(rs.getString("codTela"));
					 md.setImagens_tela(rs.getString("imagens_tela"));
					 lista.add(md);
				 }
				 pr.close();
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
			
			return lista;
		}
		
		public List<Diverso> buscaTPAGAMFCAIXA (int grupo)
		{
			List <Diverso> lista = new ArrayList<Diverso>();
			String sql = "select DISTINCT id_tela, tela, linktela,codTela,imagens_tela from vwacesso where id_grupo = ? and  id_tela between 111 and 111 order by id_tela";
			try {
				con = Conexao.getConexao();  
				PreparedStatement pr = con.prepareStatement(sql);
				 pr.setInt(1, grupo);
				 ResultSet rs = pr.executeQuery();
				 while(rs.next())
				 {
					 Diverso md = new Diverso();
					 md.setTela(rs.getString("tela"));
					 md.setLinktela(rs.getString("linktela"));
					 md.setCodTela(rs.getString("codTela"));
					 md.setImagens_tela(rs.getString("imagens_tela"));
					 lista.add(md);
				 }
				 pr.close();
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
			
			return lista;
		}

		public List<Diverso> buscaTPAGAMCONS (int grupo)
		{
			List <Diverso> lista = new ArrayList<Diverso>();
			String sql = "select DISTINCT id_tela, tela, linktela,codTela,imagens_tela from vwacesso where id_grupo = ? and  id_tela between 109 and 110 order by id_tela";
			try {
				con = Conexao.getConexao();  
				PreparedStatement pr = con.prepareStatement(sql);
				 pr.setInt(1, grupo);
				 ResultSet rs = pr.executeQuery();
				 while(rs.next())
				 {
					 Diverso md = new Diverso();
					 md.setTela(rs.getString("tela"));
					 md.setLinktela(rs.getString("linktela"));
					 md.setCodTela(rs.getString("codTela"));
					 md.setImagens_tela(rs.getString("imagens_tela"));
					 lista.add(md);
				 }
				 pr.close();
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
			
			return lista;
		}
		
		public List<Diverso> buscaTPAGAMEXAM (int grupo)
		{
			List <Diverso> lista = new ArrayList<Diverso>();
			String sql = "select DISTINCT id_tela, tela, linktela,codTela,imagens_tela from vwacesso where id_grupo = ? and  id_tela = 200 or id_tela = 202 order by id_tela";
			try {
				con = Conexao.getConexao();  
				PreparedStatement pr = con.prepareStatement(sql);
				 pr.setInt(1, grupo);
				 ResultSet rs = pr.executeQuery();
				 while(rs.next())
				 {
					 Diverso md = new Diverso();
					 md.setTela(rs.getString("tela"));
					 md.setLinktela(rs.getString("linktela"));
					 md.setCodTela(rs.getString("codTela"));
					 md.setImagens_tela(rs.getString("imagens_tela"));
					 lista.add(md);
				 }
				 pr.close();
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
			
			return lista;
		}
		
		
		public List<Diverso> buscaTPAGAMTESTQ (int grupo)
		{
			List <Diverso> lista = new ArrayList<Diverso>();
			String sql = "select DISTINCT id_tela, tela, linktela,codTela,imagens_tela from vwacesso where id_grupo = ? and  id_tela = 247 or id_tela = 248 order by id_tela";
			try {
				con = Conexao.getConexao();  
				PreparedStatement pr = con.prepareStatement(sql);
				 pr.setInt(1, grupo);
				 ResultSet rs = pr.executeQuery();
				 while(rs.next())
				 {
					 Diverso md = new Diverso();
					 md.setTela(rs.getString("tela"));
					 md.setLinktela(rs.getString("linktela"));
					 md.setCodTela(rs.getString("codTela"));
					 md.setImagens_tela(rs.getString("imagens_tela"));
					 lista.add(md);
				 }
				 pr.close();
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
			
			return lista;
		}
		
		
		public List<Diverso> buscaTPAGAMISENTO (int grupo)
		{
			List <Diverso> lista = new ArrayList<Diverso>();
			String sql = "select DISTINCT id_tela, tela, linktela,codTela,imagens_tela from vwacesso where id_grupo = ? and  id_tela between 205 and 205 order by id_tela";
			try {
				con = Conexao.getConexao();  
				PreparedStatement pr = con.prepareStatement(sql);
				 pr.setInt(1, grupo);
				 ResultSet rs = pr.executeQuery();
				 while(rs.next())
				 {
					 Diverso md = new Diverso();
					 md.setTela(rs.getString("tela"));
					 md.setLinktela(rs.getString("linktela"));
					 md.setCodTela(rs.getString("codTela"));
					 md.setImagens_tela(rs.getString("imagens_tela"));
					 lista.add(md);
				 }
				 pr.close();
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
			
			return lista;
		}
		public List<Diverso> buscaTPAGAMEXAMSER (int grupo)
		{
			List <Diverso> lista = new ArrayList<Diverso>();
			String sql = "select DISTINCT id_tela, tela, linktela,codTela,imagens_tela from vwacesso where id_grupo = ? and  id_tela between 203 and 204 order by id_tela";
			try {
				con = Conexao.getConexao();  
				PreparedStatement pr = con.prepareStatement(sql);
				 pr.setInt(1, grupo);
				 ResultSet rs = pr.executeQuery();
				 while(rs.next())
				 {
					 Diverso md = new Diverso();
					 md.setTela(rs.getString("tela"));
					 md.setLinktela(rs.getString("linktela"));
					 md.setCodTela(rs.getString("codTela"));
					 md.setImagens_tela(rs.getString("imagens_tela"));
					 lista.add(md);
				 }
				 pr.close();
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
			
			return lista;
		}
		public List<Diverso> buscaTPAGAMPROD (int grupo)
		{
			List <Diverso> lista = new ArrayList<Diverso>();
			String sql = "select DISTINCT id_tela, tela, linktela,codTela,imagens_tela from vwacesso where id_grupo = ? and  id_tela between 206 and 207 order by id_tela";
			try {
				con = Conexao.getConexao();  
				PreparedStatement pr = con.prepareStatement(sql);
				 pr.setInt(1, grupo);
				 ResultSet rs = pr.executeQuery();
				 while(rs.next())
				 {
					 Diverso md = new Diverso();
					 md.setTela(rs.getString("tela"));
					 md.setLinktela(rs.getString("linktela"));
					 md.setCodTela(rs.getString("codTela"));
					 md.setImagens_tela(rs.getString("imagens_tela"));
					 lista.add(md);
				 }
				 pr.close();
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
			
			return lista;
		}
		public List<Diverso> buscaTINTERN (int grupo)
		{
			List <Diverso> lista = new ArrayList<Diverso>();
			String sql = "select DISTINCT id_tela, tela, linktela,codTela,imagens_tela from vwacesso where id_grupo = ? and  id_tela between 112 and 113 order by id_tela";
			try {
				con = Conexao.getConexao();  
				PreparedStatement pr = con.prepareStatement(sql);
				 pr.setInt(1, grupo);
				 ResultSet rs = pr.executeQuery();
				 while(rs.next())
				 {
					 Diverso md = new Diverso();
					 md.setTela(rs.getString("tela"));
					 md.setLinktela(rs.getString("linktela"));
					 md.setCodTela(rs.getString("codTela"));
					 md.setImagens_tela(rs.getString("imagens_tela"));
					 lista.add(md);
				 }
				 pr.close();
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
			
			return lista;
		}
		
		
		
		public List<Diverso> buscaTALFAR (int grupo)
		{
			List <Diverso> lista = new ArrayList<Diverso>();
			String sql = "select DISTINCT id_tela, tela, linktela,codTela,imagens_tela from vwacesso where id_grupo = ? and  id_tela between 91 and 94 order by id_tela";
			try {
				con = Conexao.getConexao();  
				PreparedStatement pr = con.prepareStatement(sql);
				 pr.setInt(1, grupo);
				 ResultSet rs = pr.executeQuery();
				 while(rs.next())
				 {
					 Diverso md = new Diverso();
					 md.setTela(rs.getString("tela"));
					 md.setLinktela(rs.getString("linktela"));
					 md.setCodTela(rs.getString("codTela"));
					 md.setImagens_tela(rs.getString("imagens_tela"));
					 lista.add(md);
				 }
				 pr.close();
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
			
			return lista;
		}
		
		
	
	public List<Diverso> buscaTPAG (int grupo)
	{
		List <Diverso> lista = new ArrayList<Diverso>();
		String sql = "select DISTINCT id_tela, tela, linktela,codTela from vwacesso where id_grupo = ? and (id_tela = 10 "
						+ "OR id_tela = 11 "
						+ "OR id_tela = 23 "
						+ "OR id_tela = 24)";
		try {
			con = Conexao.getConexao();  
			PreparedStatement pr = con.prepareStatement(sql);
			 pr.setInt(1, grupo);
			 ResultSet rs = pr.executeQuery();
			 while(rs.next())
			 {
				 Diverso md = new Diverso();
				 md.setTela(rs.getString("tela"));
				 md.setLinktela(rs.getString("linktela"));
				 md.setCodTela(rs.getString("codTela"));
				 lista.add(md);
			 }
			 pr.close();
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
		
		return lista;
	}
	
	public List<Diverso> buscaTCAG (int grupo)
	{
		List <Diverso> lista = new ArrayList<Diverso>();
		String sql = "select DISTINCT id_tela, tela, linktela,codTela from vwacesso where id_grupo = ? and (id_tela = 12 "
						+ "OR id_tela = 13 "
						+ "OR id_tela = 14 "
						+ "OR id_tela = 145 "
						+ "OR id_tela = 146 "
						+ "OR id_tela = 149 ) order by id_tela = 149, id_tela = 146, id_tela = 14, id_tela = 145, id_tela = 13, id_tela = 12  ";
		try {
			con = Conexao.getConexao();  
			PreparedStatement pr = con.prepareStatement(sql);
			 pr.setInt(1, grupo);
			 ResultSet rs = pr.executeQuery();
			 while(rs.next())
			 {
				 Diverso md = new Diverso();
				 md.setTela(rs.getString("tela"));
				 md.setLinktela(rs.getString("linktela"));
				 md.setCodTela(rs.getString("codTela"));
				 lista.add(md);
			 }
			 pr.close();
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
		
		return lista;
	}
	
	// Metodo que lista as telas do Mudulo Agenda
		public List<Diverso> buscaTPRC (int grupo)
		{
			List <Diverso> lista = new ArrayList<Diverso>();
			String sql = "select DISTINCT id_tela, tela, linktela,codTela,imagens_tela from vwacesso where id_grupo = ? "
						+ " and (id_tela = 9 "
						+ "OR id_tela = 221 OR id_tela = 350 or id_tela = 351 or id_tela = 353 or id_tela = 356 or id_tela = 413) order by id_tela";
			try {
				con = Conexao.getConexao();  
				PreparedStatement pr = con.prepareStatement(sql);
				 pr.setInt(1, grupo);
				 ResultSet rs = pr.executeQuery();
				 while(rs.next())
				 {
					 Diverso md = new Diverso();
					 md.setTela(rs.getString("tela"));
					 md.setLinktela(rs.getString("linktela"));
					 md.setCodTela(rs.getString("codTela"));
					 md.setImagens_tela(rs.getString("imagens_tela"));
					 lista.add(md);
				 }
				 pr.close();
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
			
			return lista;
		}
		
//		Ambolat�rio
		public List<Diverso> buscaTAMBOL (int grupo)
		{
			List <Diverso> lista = new ArrayList<Diverso>();
			String sql = "select DISTINCT id_tela, tela, linktela,codTela,imagens_tela from vwacesso where id_grupo = ? "
						+ "and (id_tela = 8 OR id_tela = 339 or id_tela = 150) order by id_tela";
			try {
				con = Conexao.getConexao();  
				PreparedStatement pr = con.prepareStatement(sql);
				 pr.setInt(1, grupo);
				 ResultSet rs = pr.executeQuery();
				 while(rs.next())
				 {
					 Diverso md = new Diverso();
					 md.setTela(rs.getString("tela"));
					 md.setLinktela(rs.getString("linktela"));
					 md.setCodTela(rs.getString("codTela"));
					 md.setImagens_tela(rs.getString("imagens_tela"));
					 lista.add(md);
				 }
				 pr.close();
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
			
			return lista;
		}
		
//		Enfermagem
		public List<Diverso> buscaTEnfermagem (int grupo)
		{
			List <Diverso> lista = new ArrayList<Diverso>();
			String sql = "select DISTINCT id_tela, tela, linktela,codTela,imagens_tela from vwacesso where id_grupo = ? "
						+ "and (id_tela = 7 OR id_tela = 44 or id_tela = 352) order by id_tela";
			try {
				con = Conexao.getConexao();  
				PreparedStatement pr = con.prepareStatement(sql);
				 pr.setInt(1, grupo);
				 ResultSet rs = pr.executeQuery();
				 while(rs.next())
				 {
					 Diverso md = new Diverso();
					 md.setTela(rs.getString("tela"));
					 md.setLinktela(rs.getString("linktela"));
					 md.setCodTela(rs.getString("codTela"));
					 md.setImagens_tela(rs.getString("imagens_tela"));
					 lista.add(md);
				 }
				 pr.close();
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
			
			return lista;
		}
		
//		Enfermagem
		public List<Diverso> buscaTVacina (int grupo)
		{
			List <Diverso> lista = new ArrayList<Diverso>();
			String sql = "select DISTINCT id_tela, tela, linktela,codTela,imagens_tela from vwacesso where id_grupo = ? "
						+ "and (id_tela = 354 OR id_tela = 355) order by id_tela";
			try {
				con = Conexao.getConexao();  
				PreparedStatement pr = con.prepareStatement(sql);
				 pr.setInt(1, grupo);
				 ResultSet rs = pr.executeQuery();
				 while(rs.next())
				 {
					 Diverso md = new Diverso();
					 md.setTela(rs.getString("tela"));
					 md.setLinktela(rs.getString("linktela"));
					 md.setCodTela(rs.getString("codTela"));
					 md.setImagens_tela(rs.getString("imagens_tela"));
					 lista.add(md);
				 }
				 pr.close();
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
			
			return lista;
		}
		
//		Enfermagem
		public List<Diverso> buscaTHemoterapia (int grupo)
		{
			List <Diverso> lista = new ArrayList<Diverso>();
			String sql = "select DISTINCT id_tela, tela, linktela,codTela,imagens_tela from vwacesso where id_grupo = ? "
						+ "and (id_tela = 356 OR id_tela = 358 or id_tela = 359) order by id_tela";
			try {
				con = Conexao.getConexao();  
				PreparedStatement pr = con.prepareStatement(sql);
				 pr.setInt(1, grupo);
				 ResultSet rs = pr.executeQuery();
				 while(rs.next())
				 {
					 Diverso md = new Diverso();
					 md.setTela(rs.getString("tela"));
					 md.setLinktela(rs.getString("linktela"));
					 md.setCodTela(rs.getString("codTela"));
					 md.setImagens_tela(rs.getString("imagens_tela"));
					 lista.add(md);
				 }
				 pr.close();
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
			
			return lista;
		}
		
//		Enfermagem
		public List<Diverso> buscaTEnfGeral (int grupo)
		{
			List <Diverso> lista = new ArrayList<Diverso>();
			String sql = "select DISTINCT id_tela, tela, linktela,codTela,imagens_tela from vwacesso where id_grupo = ? "
						+ "and (id_tela = 46 OR id_tela = 53 or id_tela = 295) order by id_tela";
			try {
				con = Conexao.getConexao();  
				PreparedStatement pr = con.prepareStatement(sql);
				 pr.setInt(1, grupo);
				 ResultSet rs = pr.executeQuery();
				 while(rs.next())
				 {
					 Diverso md = new Diverso();
					 md.setTela(rs.getString("tela"));
					 md.setLinktela(rs.getString("linktela"));
					 md.setCodTela(rs.getString("codTela"));
					 md.setImagens_tela(rs.getString("imagens_tela"));
					 lista.add(md);
				 }
				 pr.close();
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
			
			return lista;
		}
		
//		Enfermagem
		public List<Diverso> buscaTEnfBUE (int grupo)
		{
			List <Diverso> lista = new ArrayList<Diverso>();
			String sql = "select DISTINCT id_tela, tela, linktela,codTela,imagens_tela from vwacesso where id_grupo = ? "
						+ "and (id_tela = 364 OR id_tela = 365 or id_tela = 306) order by id_tela";
			try {
				con = Conexao.getConexao();  
				PreparedStatement pr = con.prepareStatement(sql);
				 pr.setInt(1, grupo);
				 ResultSet rs = pr.executeQuery();
				 while(rs.next())
				 {
					 Diverso md = new Diverso();
					 md.setTela(rs.getString("tela"));
					 md.setLinktela(rs.getString("linktela"));
					 md.setCodTela(rs.getString("codTela"));
					 md.setImagens_tela(rs.getString("imagens_tela"));
					 lista.add(md);
				 }
				 pr.close();
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
			
			return lista;
		}
		
//		UCI
		public List<Diverso> buscaTEnfUCI (int grupo)
		{
			List <Diverso> lista = new ArrayList<Diverso>();
			String sql = "select DISTINCT id_tela, tela, linktela,codTela,imagens_tela from vwacesso where id_grupo = ? "
						+ "and (id_tela = 367 OR id_tela = 368 or id_tela = 369) order by id_tela";
			try {
				con = Conexao.getConexao();  
				PreparedStatement pr = con.prepareStatement(sql);
				 pr.setInt(1, grupo);
				 ResultSet rs = pr.executeQuery();
				 while(rs.next())
				 {
					 Diverso md = new Diverso();
					 md.setTela(rs.getString("tela"));
					 md.setLinktela(rs.getString("linktela"));
					 md.setCodTela(rs.getString("codTela"));
					 md.setImagens_tela(rs.getString("imagens_tela"));
					 lista.add(md);
				 }
				 pr.close();
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
			
			return lista;
		}
		
//		INC
		public List<Diverso> buscaTEnfINC (int grupo)
		{
			List <Diverso> lista = new ArrayList<Diverso>();
			String sql = "select DISTINCT id_tela, tela, linktela,codTela,imagens_tela from vwacesso where id_grupo = ? "
						+ "and (id_tela = 370 OR id_tela = 371 or id_tela = 372) order by id_tela";
			try {
				con = Conexao.getConexao();  
				PreparedStatement pr = con.prepareStatement(sql);
				 pr.setInt(1, grupo);
				 ResultSet rs = pr.executeQuery();
				 while(rs.next())
				 {
					 Diverso md = new Diverso();
					 md.setTela(rs.getString("tela"));
					 md.setLinktela(rs.getString("linktela"));
					 md.setCodTela(rs.getString("codTela"));
					 md.setImagens_tela(rs.getString("imagens_tela"));
					 lista.add(md);
				 }
				 pr.close();
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
			
			return lista;
		}
		
	public List<Diverso> buscaTFOR (int grupo)
	{
		List <Diverso> lista = new ArrayList<Diverso>();
		String sql = "select DISTINCT id_tela, tela, linktela,codTela,imagens_tela from vwacesso where id_grupo = ? "
				+ "and  id_tela = 42 "
				+ "OR id_tela = 41";
		try {
			con = Conexao.getConexao();  
			PreparedStatement pr = con.prepareStatement(sql);
			pr.setInt(1, grupo);
			 ResultSet rs = pr.executeQuery();
			 while(rs.next())
			 {
				 Diverso md = new Diverso();
				 md.setTela(rs.getString("tela"));
				 md.setLinktela(rs.getString("linktela"));
				 md.setCodTela(rs.getString("codTela"));
				 lista.add(md);
			 }
			 pr.close();
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
		
		return lista;
	}
	
	public List<Diverso> buscaTPRCGinasio (int grupo)
	{
		List <Diverso> lista = new ArrayList<Diverso>();
		String sql = "select DISTINCT id_tela, tela, linktela,codTela,imagens_tela from vwacesso where id_grupo = ? "
				+ " AND  id_tela = 222 "
				+ " OR   id_tela = 223 "
				+ " OR   id_tela = 224 "
				+ " OR   id_tela = 425 ";
		try {
			con = Conexao.getConexao();  
			PreparedStatement pr = con.prepareStatement(sql);
			pr.setInt(1, grupo);
			 ResultSet rs = pr.executeQuery();
			 while(rs.next())
			 {
				 Diverso md = new Diverso();
				 md.setTela(rs.getString("tela"));
				 md.setLinktela(rs.getString("linktela"));
				 md.setCodTela(rs.getString("codTela"));
				 md.setImagens_tela(rs.getString("imagens_tela"));
				 lista.add(md);
			 }
			 pr.close();
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
		
		return lista;
	}
	
	public List<Diverso> buscaTPRCTratarPaciente (int grupo)
	{
		List <Diverso> lista = new ArrayList<Diverso>();
		String sql = "select DISTINCT id_tela, tela, linktela,codTela from vwacesso where id_grupo = ? "
				+ " AND  id_tela = 225 OR id_tela = 226";
		try {
			con = Conexao.getConexao();  
			PreparedStatement pr = con.prepareStatement(sql);
			pr.setInt(1, grupo);
			 ResultSet rs = pr.executeQuery();
			 while(rs.next())
			 {
				 Diverso md = new Diverso();
				 md.setTela(rs.getString("tela"));
				 md.setLinktela(rs.getString("linktela"));
				 md.setCodTela(rs.getString("codTela"));
				 lista.add(md);
			 }
			 pr.close();
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
		
		return lista;
	}
	
	public List<Diverso> buscaTPRCGinasioPaciente (int grupo)
	{
		List <Diverso> lista = new ArrayList<Diverso>();
		String sql = "select DISTINCT id_tela, tela, linktela,codTela from vwacesso where id_grupo = ? "
				+ " AND  id_tela = 227";
		try {
			con = Conexao.getConexao();  
			PreparedStatement pr = con.prepareStatement(sql);
			pr.setInt(1, grupo);
			 ResultSet rs = pr.executeQuery();
			 while(rs.next())
			 {
				 Diverso md = new Diverso();
				 md.setTela(rs.getString("tela"));
				 md.setLinktela(rs.getString("linktela"));
				 md.setCodTela(rs.getString("codTela"));
				 lista.add(md);
			 }
			 pr.close();
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
		
		return lista;
	}
	
	public List<Diverso> buscaTEFOR (int grupo)
	{
		List <Diverso> lista = new ArrayList<Diverso>();
		String sql = "select DISTINCT id_tela, tela, linktela,codTela from vwacesso where id_grupo = ? and id_tela = 32 or id_tela =241";
		try {
			con = Conexao.getConexao();  
			PreparedStatement pr = con.prepareStatement(sql);
			pr.setInt(1, grupo);
			 ResultSet rs = pr.executeQuery();
			 while(rs.next())
			 {
				 Diverso md = new Diverso();
				 md.setTela(rs.getString("tela"));
				 md.setLinktela(rs.getString("linktela"));
				 md.setCodTela(rs.getString("codTela"));
				 lista.add(md);
			 }
			 pr.close();
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
		
		return lista;
	}
	public List<Diverso> buscaTSFOR (int grupo)
	{
		List <Diverso> lista = new ArrayList<Diverso>();
		String sql = "select DISTINCT id_tela, tela, linktela,codTela from vwacesso where id_grupo = ? "
						+ "and id_tela = 34 "
						+ "OR id_tela = 48 "
						+ "OR id_tela = 49 "
						+ "OR id_tela = 50 "
						+ "OR id_tela = 51 "
						+ " order by id_tela = 49 desc, id_tela = 34 asc";
		try {
			con = Conexao.getConexao();  
			PreparedStatement pr = con.prepareStatement(sql);
			pr.setInt(1, grupo);
			 ResultSet rs = pr.executeQuery();
			 while(rs.next())
			 {
				 Diverso md = new Diverso();
				 md.setTela(rs.getString("tela"));
				 md.setLinktela(rs.getString("linktela"));
				 md.setCodTela(rs.getString("codTela"));
				 lista.add(md);
			 }
			 pr.close();
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
		
		return lista;
	}
	public List<Diverso> buscaEstadoCivil ()
	{
		List <Diverso> lista = new ArrayList<Diverso>();
		String sql = "SELECT * FROM TBLESTADOCIVIL ";
		try {
			con = Conexao.getConexao();  
			PreparedStatement preparador = con.prepareStatement(sql);
			 ResultSet rs = preparador.executeQuery();
			 while(rs.next())
			 {
				 Diverso fun = new Diverso();
				 fun.setEst_civil(rs.getInt("id_estadocivil"));
				 fun.setNomeEC(rs.getString("estado_civil"));
				 lista.add(fun);
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
		return lista;
	}
	public List<Diverso> buscaTipoDocumento ()
	{
		List <Diverso> lista = new ArrayList<Diverso>();
		String sql = "SELECT * FROM TBLTIPODOCUMENTO ";
		try {
			con = Conexao.getConexao();  
			PreparedStatement preparador = con.prepareStatement(sql);
			 ResultSet rs = preparador.executeQuery();
			 while(rs.next())
			 {
				 Diverso fun = new Diverso();
				 fun.setTipo_doc(rs.getInt("id_tipo_documento"));
				 fun.setNomeDoc(rs.getString("tipo_documento"));
				 lista.add(fun);
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
		return lista;
	}
	
	public List<Diverso> buscaPais ()
	{
		List <Diverso> lista = new ArrayList<Diverso>();
		String sql = "SELECT * FROM TBLPAIS ORDER BY ID_PAIS = 0 DESC, PAIS ASC";
		try {
			con = Conexao.getConexao();  
			PreparedStatement preparador = con.prepareStatement(sql);
			 ResultSet rs = preparador.executeQuery();
			 while(rs.next())
			 {
				 Diverso fun = new Diverso();
				 fun.setPais(rs.getInt("id_pais"));
				 fun.setNomePais(rs.getString("pais"));
				 lista.add(fun);
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
		return lista;
	}
	
	public List<Diverso> buscaProvincias ()
	{
		List <Diverso> lista = new ArrayList<Diverso>();
		String sql = "SELECT * FROM TBLPROVINCIA ORDER BY ID_PROVINCIA = 0 DESC, PROVINCIA ASC";
		try {
			con = Conexao.getConexao();  
			PreparedStatement preparador = con.prepareStatement(sql);
//			preparador.setInt(1, codigo);
			 ResultSet rs = preparador.executeQuery();
			 while(rs.next())
			 {
				 Diverso fun = new Diverso();
				 fun.setProvincia(rs.getInt("id_provincia"));
				 fun.setNomeProv(rs.getString("provincia"));
				 lista.add(fun);
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
		return lista;
	}
	
	public List<Diverso> buscaMunicipio ()
	{
		List <Diverso> lista = new ArrayList<Diverso>();
		String sql = "SELECT * FROM TBLMUNICIPIO ORDER BY ID_MUNICIPIO = 0 DESC, MUNICIPIO ASC";
		try {
			con = Conexao.getConexao();  
			PreparedStatement preparador = con.prepareStatement(sql);
//			preparador.setInt(1, codigo);
			 ResultSet rs = preparador.executeQuery();
			 while(rs.next())
			 {
				 Diverso fun = new Diverso();
				 fun.setMunicipio(rs.getInt("id_municipio"));
				 fun.setNomeMunicipio(rs.getString("municipio"));
				 lista.add(fun);
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
		return lista;
	}
	
	public List<Diverso> buscaDistrito ()
	{
		List <Diverso> lista = new ArrayList<Diverso>();
		String sql = "SELECT * FROM TBLDISTRITO ORDER BY ID_DISTRITO = 0 DESC, DISTRITO ASC";
		try {
			con = Conexao.getConexao();  
			PreparedStatement preparador = con.prepareStatement(sql);
//			preparador.setInt(1, codigo);
			 ResultSet rs = preparador.executeQuery();
			 while(rs.next())
			 {
				 Diverso fun = new Diverso();
				 fun.setDistrito(rs.getInt("id_distrito"));
				 fun.setNomeDistrito(rs.getString("distrito"));
				 lista.add(fun);
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
		return lista;
	}
	
	public List<Diverso> buscaFuncao()
	{
		List <Diverso> lista = new ArrayList<Diverso>();
		String sql = "SELECT * FROM TBLFUNCAO ";
		try {
			con = Conexao.getConexao();  
			PreparedStatement preparador = con.prepareStatement(sql);
			 ResultSet rs = preparador.executeQuery();
			 while(rs.next())
			 {
				 Diverso fun = new Diverso();
				 fun.setFuncao(rs.getInt("id_funcao"));
				 fun.setNomeFun(rs.getString("funcao"));
				 lista.add(fun);
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
		return lista;
	}
	
	public List<Funcionario> buscaAfilicao_fun ()
	{
		List <Funcionario> lista = new ArrayList<Funcionario>();
		String sql = "SELECT * FROM TBLAFILIACAO ";
		try {
			con = Conexao.getConexao();  
			PreparedStatement preparador = con.prepareStatement(sql);
			 ResultSet rs = preparador.executeQuery();
			 while(rs.next())
			 {
				 Funcionario fun = new Funcionario();
				 fun.setAfiliacao(rs.getInt("id_afiliacao"));
				 fun.setNomeAfl(rs.getString("afiliacao"));
				 lista.add(fun);
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
		return lista;
	 }
	
		public List<Paciente> buscaAfilicao_pc ()
		{
			List <Paciente> lista = new ArrayList<Paciente>();
			String sql = "SELECT * FROM TBLAFILIACAO ";
			try {
				con = Conexao.getConexao();  
				PreparedStatement preparador = con.prepareStatement(sql);
				 ResultSet rs = preparador.executeQuery();
				 while(rs.next())
				 {
					 Paciente fun = new Paciente();
					 fun.setIdAfiliacao(rs.getInt("id_afiliacao"));
					 fun.setNomeAfl(rs.getString("afiliacao"));
					 lista.add(fun);
				 }
				 preparador.close();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
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
		return lista;
	}
	public List<Diverso> buscaMes ()
	{
		List <Diverso> lista = new ArrayList<Diverso>();
		String sql = "SELECT * FROM TBLMES ";
		try {
			con = Conexao.getConexao();  
			PreparedStatement preparador = con.prepareStatement(sql);
			 ResultSet rs = preparador.executeQuery();
			 while(rs.next())
			 {
				 Diverso dm = new Diverso();
				 dm.setId_mes(rs.getInt("id_mes"));
				 dm.setMes(rs.getString("mes"));
				 lista.add(dm);
			 }
			 preparador.close();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
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
		return lista;
	}
	
	public List<Diverso> buscaDSemana ()
	{
		List <Diverso> lista = new ArrayList<Diverso>();
		String sql = "SELECT * FROM TBLDIADASEMANA ";
		try {
			con = Conexao.getConexao();  
			PreparedStatement preparador = con.prepareStatement(sql);
			 ResultSet rs = preparador.executeQuery();
			 while(rs.next())
			 {
				 Diverso dm = new Diverso();
				 dm.setId_semana(rs.getInt("id_dia_da_semana"));
				 dm.setSemana(rs.getString("dia_da_semana"));
				 lista.add(dm);
			 }
			 preparador.close();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
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
		return lista;
	}
	
	public List<Diverso> buscaPais (String aux)
	{
		List <Diverso> lista = new ArrayList<Diverso>();
		String sql = "SELECT * FROM TBLPAIS WHERE PAIS LIKE '"+aux+"%'";
		try {
			 
			con = Conexao.getConexao(); 
			PreparedStatement preparador = con.prepareStatement(sql);
			 ResultSet rs = preparador.executeQuery();
			 while(rs.next())
			 {
				 Diverso fun = new Diverso();
				 fun.setPais(rs.getInt("id_pais"));
				 fun.setNomePais(rs.getString("pais"));
				 lista.add(fun);
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
		return lista;
	}
	
	public List<Diverso> buscarAndar ()
	{
		List <Diverso> lista = new ArrayList<Diverso>();
		String sql = "SELECT * FROM tblandarbloco order by (id_andar_bloco) ";
		try {
			con = Conexao.getConexao(); 
			PreparedStatement preparador = con.prepareStatement(sql);
			 ResultSet rs = preparador.executeQuery();
			 while(rs.next())
			 {
				 Diverso d = new Diverso();
				 d.setId_andar_bloco(rs.getInt("id_andar_bloco"));
				 d.setAndar_bloco(rs.getString("andar_bloco"));
				 lista.add(d);
			 }
			 preparador.close();
		} catch (SQLException e) {System.out.println(e);	}
		  catch (Exception e) {	e.printStackTrace();		}
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
	
	public List<Diverso> buscarSector ()
	{
		List <Diverso> lista = new ArrayList<Diverso>();
		String sql = "SELECT * FROM tblsector order by (id_sector) ";
		try {
			con = Conexao.getConexao(); 
			PreparedStatement preparador = con.prepareStatement(sql);
			 ResultSet rs = preparador.executeQuery();
			 while(rs.next())
			 {
				 Diverso d = new Diverso();
				 d.setId_sector(rs.getInt("id_sector"));
				 d.setSector(rs.getString("sector"));
				 lista.add(d);
			 }
			 preparador.close();
		} catch (SQLException e) {System.out.println(e);	}
		  catch (Exception e) {	e.printStackTrace();		}
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
	
	public List<Diverso> buscarSala ()
	{
		List <Diverso> lista = new ArrayList<Diverso>();
		String sql = "SELECT * FROM tblsala order by (id_sala) ";
		try {
			con = Conexao.getConexao(); 
			PreparedStatement preparador = con.prepareStatement(sql);
			 ResultSet rs = preparador.executeQuery();
			 while(rs.next())
			 {
				 Diverso d = new Diverso();
				 d.setId_sala(rs.getInt("id_sala"));
				 d.setDesc_sala(rs.getString("descricao"));
				 d.setNum_sala(rs.getInt("numero_sala"));
				 lista.add(d);
			 }
			 preparador.close();
		} catch (SQLException e) {System.out.println(e);	}
		  catch (Exception e) {	e.printStackTrace();		}
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
	
	public List<Diverso> buscarInternaAndSala (int sector)
	{
		List <Diverso> lista = new ArrayList<Diverso>();
		String sql = "SELECT a.andar_bloco,	s.sector, s.extensao, s.id_sector, s.FK_internamento, i.internamento_interno,a.id_andar_bloco  FROM tblandarbloco a JOIN tblsector s ON a.id_andar_bloco = s.FK_andar_bloco join tblinternamentointerno i on s.FK_internamento = i.id_internamento_interno where s.FK_internamento = ?";
		try {
			con = Conexao.getConexao(); 
			PreparedStatement preparador = con.prepareStatement(sql);
			preparador.setInt(1, sector);
			 ResultSet rs = preparador.executeQuery();
			 while(rs.next())
			 {
				 Diverso d = new Diverso();
				 d.setAndar_bloco(rs.getString("andar_bloco"));
				 d.setId_andar_bloco(rs.getInt("id_andar_bloco"));
				 lista.add(d);
			 }
			 preparador.close();
		} catch (SQLException e) {System.out.println(e);	}
		  catch (Exception e) {	e.printStackTrace();		}
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
	
	public List<Diverso> buscarSala (int sector)
	{
		List <Diverso> lista = new ArrayList<Diverso>();
		String sql = "SELECT a.andar_bloco , a.id_andar_bloco,	s.sector, s.extensao, s.id_sector, s.FK_internamento, i.internamento_interno, sa.id_sala, sa.numero_sala  FROM tblandarbloco a JOIN tblsector s ON a.id_andar_bloco = s.FK_andar_bloco join tblinternamentointerno i  on s.FK_internamento = i.id_internamento_interno join tblsala sa on s.id_sector=sa.FK_sector where s.id_sector =? ";
		try {
			con = Conexao.getConexao(); 
			PreparedStatement preparador = con.prepareStatement(sql);
			preparador.setInt(1, sector);
			 ResultSet rs = preparador.executeQuery();
			 while(rs.next())
			 {
				 Diverso d = new Diverso();
				 d.setId_sala(rs.getInt("id_sala"));
				 d.setAndar_bloco(rs.getString("andar_bloco"));
				 d.setNum_sala(rs.getInt("numero_sala"));
				 lista.add(d);
			 }
			 preparador.close();
		} catch (SQLException e) {System.out.println(e);	}
		  catch (Exception e) {	e.printStackTrace();		}
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
	
	public List<Diverso> buscarInternamentoAndSetor(int sector)
	{
		List <Diverso> lista = new ArrayList<Diverso>();
		String sql = "SELECT a.andar_bloco s, a.id_andar_bloco,	s.sector, s.extensao, s.id_sector, s.FK_internamento, i.internamento_interno  FROM tblandarbloco a JOIN tblsector s ON a.id_andar_bloco = s.FK_andar_bloco join tblinternamentointerno i on s.FK_internamento = i.id_internamento_interno where s.FK_internamento =? ";
		try {
			con = Conexao.getConexao(); 
			PreparedStatement preparador = con.prepareStatement(sql);
			preparador.setInt(1, sector);
			 ResultSet rs = preparador.executeQuery();
			 while(rs.next())
			 {
				 Diverso d = new Diverso();
				 d.setId_sector(rs.getInt("id_sector"));
				 d.setSector(rs.getString("sector"));
				 lista.add(d);
			 }
			 preparador.close();
		} catch (SQLException e) {System.out.println(e);	}
		  catch (Exception e) {	e.printStackTrace();		}
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
	
	public List<Diverso> buscarInternamentoAndSetores(int sector) 
	{
		List <Diverso> lista = new ArrayList<Diverso>();
		String sql = "SELECT ab.andar_bloco, i.internamento_interno, ab.FK_internamento, ab.id_andar_bloco FROM tblandarbloco ab INNER JOIN tblinternamentointerno i ON ab.FK_internamento = i.id_internamento_interno where ab.FK_internamento =? ";
		try {
			con = Conexao.getConexao(); 
			PreparedStatement preparador = con.prepareStatement(sql);
			preparador.setInt(1, sector);
			 ResultSet rs = preparador.executeQuery();
			 while(rs.next())
			 {
				 Diverso d = new Diverso();
				 d.setId_andar_bloco(rs.getInt("id_andar_bloco"));
				 d.setAndar_bloco(rs.getString("andar_bloco"));
				 lista.add(d);
			 }
			 preparador.close();
		} catch (SQLException e) {System.out.println(e);	}
		  catch (Exception e) {	e.printStackTrace();		}
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
	
	public List<Diverso> buscarSala (String sector)
	{
		List <Diverso> lista = new ArrayList<Diverso>();
		String sql = "SELECT * FROM vwsalas where sector Like ? order by (id_sala) ";
		try {
			con = Conexao.getConexao(); 
			PreparedStatement preparador = con.prepareStatement(sql);
			preparador.setString(1, sector);
			 ResultSet rs = preparador.executeQuery();
			 while(rs.next())
			 {
				 Diverso d = new Diverso();
				 d.setId_sala(rs.getInt("id_sala"));
				 d.setDesc_sala(rs.getString("descricao"));
				 d.setNum_sala(rs.getInt("numero_sala"));
				 d.setAndar_bloco(rs.getString("andar_bloco"));
				 lista.add(d);
			 }
			 preparador.close();
		} catch (SQLException e) {System.out.println(e);	}
		  catch (Exception e) {	e.printStackTrace();		}
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
	public List<Diverso> buscarLeitos ()
	{
		List <Diverso> lista = new ArrayList<Diverso>();
		String sql = "SELECT * FROM tblleito order by (id_leito) ";
		try {
			con = Conexao.getConexao(); 
			PreparedStatement preparador = con.prepareStatement(sql);
			 ResultSet rs = preparador.executeQuery();
			 while(rs.next())
			 {
				 Diverso d = new Diverso();
				 d.setId_leito(rs.getInt("id_leito"));
				 d.setLeito(rs.getString("leito"));
				 lista.add(d);
			 }
			 preparador.close();
		} catch (SQLException e) {System.out.println(e);	}
		  catch (Exception e) {	e.printStackTrace();		}
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
	
	public List<Diverso> buscarLeitos (int sala)
	{
		List <Diverso> lista = new ArrayList<Diverso>();
		String sql = "SELECT * FROM vwsectores where numero_sala = ? order by (id_leito) ";
		try {
			con = Conexao.getConexao(); 
			PreparedStatement preparador = con.prepareStatement(sql);
			preparador.setInt(1, sala);
			 ResultSet rs = preparador.executeQuery();
			 while(rs.next())
			 {
				 Diverso d = new Diverso();
				 d.setId_leito(rs.getInt("id_leito"));
				 d.setLeito(rs.getString("leito"));
				 lista.add(d);
			 }
			 preparador.close();
		} catch (SQLException e) {System.out.println(e);	}
		  catch (Exception e) {	e.printStackTrace();		}
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
	
	public List<Diverso> buscarLeitos (String sala)
	{
		List <Diverso> lista = new ArrayList<Diverso>();
		String sql = "SELECT * FROM vwsectores where id_sala Like ? and status != 1 order by (id_leito) ";
		try {
			con = Conexao.getConexao(); 
			PreparedStatement preparador = con.prepareStatement(sql);
			preparador.setString(1, sala);
			 ResultSet rs = preparador.executeQuery();
			 while(rs.next())
			 {
				 Diverso d = new Diverso();
				 d.setId_leito(rs.getInt("id_leito"));
				 d.setLeito(rs.getString("leito"));
				 lista.add(d);
			 }
			 preparador.close();
		} catch (SQLException e) {System.out.println(e);	}
		  catch (Exception e) {	e.printStackTrace();		}
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
	
	
	public long getTLF(String tlf)
	{
		long tlfs = 0;
		String sql = "select * from TBLTELEFONE where telefone = ?";
		try{
			con = Conexao.getConexao();
			PreparedStatement ps = con.prepareStatement(sql);
			ps.setString(1, tlf);
			ResultSet rs = ps.executeQuery();
			if(rs.next()){
//				System.out.println("Capturou algo no Banco....");
				tlfs = rs.getLong("telefone");
			}
			
		}catch(SQLException e){
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
		
		return tlfs;
	}
	
	public String getCapCID(String cap)
	{
		String caps = "";
		String sql = "select * from tblcapitulo where codigo_capitulo LIke ?";
		try{
			con = Conexao.getConexao();
			PreparedStatement ps = con.prepareStatement(sql);
			ps.setString(1, cap);
			ResultSet rs = ps.executeQuery();
			if(rs.next()){
//				System.out.println("Capturou algo no Banco....");
				caps = rs.getString("codigo_capitulo");
			}
			
		}catch(SQLException e){
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
		
		return caps;
	}
	
	public List<Diverso> buscaLabs() {
		List<Diverso> lista = new ArrayList<Diverso>();
		String sql = "SELECT * FROM tbllaboratorio ORDER BY id_laboratorio = 1 desc, laboratorio asc";
		try {
			con = Conexao.getConexao();
			PreparedStatement preparador = con.prepareStatement(sql);
			ResultSet rs = preparador.executeQuery();
			while (rs.next()) {
				Diverso dm = new Diverso();
				dm.setId_lab(rs.getInt("id_laboratorio"));
				dm.setLab(rs.getString("laboratorio"));
				lista.add(dm);
			}
			preparador.close();
		} catch (SQLException e) {	e.printStackTrace();	} 
		catch (Exception e) {	e.printStackTrace();	} 
		finally {
			try {
				con.close();
			} catch (SQLException ef) {
				System.out.println("Erro finalizar: " + ef);
			}
		}
		return lista;
	}
	public List<Diverso> buscaHospitalClinico(String nome) {
		List<Diverso> lista = new ArrayList<Diverso>();
		String sql = "SELECT * FROM tblhospitaisclinicas WHERE NOME LIKE ?";
		try {
			con = Conexao.getConexao();
			PreparedStatement preparador = con.prepareStatement(sql);
			preparador.setString(1, nome+"%");
			ResultSet rs = preparador.executeQuery();
			while (rs.next()) {
				Diverso fun = new Diverso();
				fun.setNomeHospital(rs.getString("NOME"));
				lista.add(fun);
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
		return lista;
	}
	
	public List<Diverso> buscarTaxa() {
		List<Diverso> lista = new ArrayList<Diverso>();
		String sql = "SELECT * FROM tbltaxas";
		try {
			con = Conexao.getConexao();
			PreparedStatement preparador = con.prepareStatement(sql);
			ResultSet rs = preparador.executeQuery();
			while (rs.next()) {
				Diverso pd = new Diverso();
				pd.setId_taxa(rs.getInt("id_taxa"));
				pd.setTipo_de_taxa(rs.getString("tipo_de_taxa"));
				pd.setTaxa(rs.getDouble("taxa"));
				lista.add(pd);
			}
			preparador.close();
		} catch (SQLException e) {

			e.printStackTrace();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			try {
				con.close();
			} catch (SQLException ef) {
				System.out.println("Erro finalizar: " + ef);
			}
		}
		return lista;
	}
	
	public Funcionario getProfissional (String cod){
		String sql = "Select * from vwcidadaomediconomecompleto where entidade = ?";
		Funcionario func = new Funcionario();
		int codf = Integer.parseInt(cod);
		try{
			con = Conexao.getConexao();
			PreparedStatement ps = con.prepareStatement(sql);
			ps.setInt(1, codf);
			ResultSet rs = ps.executeQuery();
			if(rs.next()){
				func.setFK_entidade(rs.getInt("entidade"));
				func.setNome(rs.getString("nomecompleto"));
			}
			ps.close();
			con.close();
		}catch(Exception e){
			e.printStackTrace();
		}
		return func;
	}
	
	public Servico getTipoServico (String cod){
		String sql = "Select * from vwservico where fk_tipo_de_servico = ?";
		Servico serv = new Servico();
		int codf = Integer.parseInt(cod);
		try{
			con = Conexao.getConexao();
			PreparedStatement ps = con.prepareStatement(sql);
			ps.setInt(1, codf);
			ResultSet rs = ps.executeQuery();
			if(rs.next()){
				//serv.setId_tipo_servico(rs.getInt("fk_tipo_de_servico"));
				serv.setTipo_de_servico(rs.getString("tipo_de_servico"));
				serv.setFK_especialidade(rs.getInt("FK_especialidade"));
				serv.setEspecialidade(rs.getString("especialidade"));
			}
			ps.close();
			con.close();
		}catch(Exception e){
			e.printStackTrace();
		}
		return serv;
	}
	
	public List<Diverso> buscaTELASLABPrincipal (int grupo)
	{
		List <Diverso> lista = new ArrayList<Diverso>();
		String sql = "select DISTINCT id_tela, tela, linktela,codTela,imagens_tela from vwacesso where id_grupo = ? "
				+ "and  id_tela between 284 and 285 or id_tela = 290 or id_tela = 449 order by id_tela";
		try {
			con = Conexao.getConexao();  
			PreparedStatement pr = con.prepareStatement(sql);
			 pr.setInt(1, grupo);
			 ResultSet rs = pr.executeQuery();
			 while(rs.next())
			 {
				 Diverso md = new Diverso();
				 md.setTela(rs.getString("tela"));
				 md.setLinktela(rs.getString("linktela"));
				 md.setCodTela(rs.getString("codTela"));
				 md.setImagens_tela(rs.getString("imagens_tela"));
				 lista.add(md);
			 }
			 pr.close();
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

		return lista;
	}
	public List<Diverso> buscaTELASLAB(int grupo)
	{
		List <Diverso> lista = new ArrayList<Diverso>();
		String sql = "select DISTINCT id_tela, tela, linktela,codTela,imagens_tela from vwacesso where id_grupo = ? "
				+ "and  id_tela between 193 and 195 or id_tela = 317 order by id_tela";
		try {
			con = Conexao.getConexao();  
			PreparedStatement pr = con.prepareStatement(sql);
			 pr.setInt(1, grupo);
			 ResultSet rs = pr.executeQuery();
			 while(rs.next())
			 {
				 Diverso md = new Diverso();
				 md.setTela(rs.getString("tela"));
				 md.setLinktela(rs.getString("linktela"));
				 md.setCodTela(rs.getString("codTela"));
				 md.setImagens_tela(rs.getString("imagens_tela"));
				 lista.add(md);
			 }
			 pr.close();
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

		return lista;
	}
	
	public List<Diverso> buscaTELASLABATD(int grupo)
	{
		List <Diverso> lista = new ArrayList<Diverso>();
		String sql = "select DISTINCT id_tela, tela, linktela,codTela,imagens_tela from vwacesso where id_grupo = ? "
				+ "and  (id_tela = 131 or id_tela = 133 or id_tela = 197) order by id_tela";
		try {
			con = Conexao.getConexao();  
			PreparedStatement pr = con.prepareStatement(sql);
			 pr.setInt(1, grupo);
			 ResultSet rs = pr.executeQuery();
			 while(rs.next())
			 {
				 Diverso md = new Diverso();
				 md.setTela(rs.getString("tela"));
				 md.setLinktela(rs.getString("linktela"));
				 md.setCodTela(rs.getString("codTela"));
				 md.setImagens_tela(rs.getString("imagens_tela"));
				 lista.add(md);
			 }
			 pr.close();
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

		return lista;
	}
	
	public List<Diverso> buscaTELASLABConfg(int grupo)
	{
		List <Diverso> lista = new ArrayList<Diverso>();
		String sql = "select DISTINCT id_tela, tela, linktela,codTela,imagens_tela from vwacesso where id_grupo = ? "
				+ "and  (id_tela = 252 or id_tela = 450 or id_tela = 451) order by id_tela";
		try {
			con = Conexao.getConexao();  
			PreparedStatement pr = con.prepareStatement(sql);
			 pr.setInt(1, grupo);
			 ResultSet rs = pr.executeQuery();
			 while(rs.next())
			 {
				 Diverso md = new Diverso();
				 md.setTela(rs.getString("tela"));
				 md.setLinktela(rs.getString("linktela"));
				 md.setCodTela(rs.getString("codTela"));
				 md.setImagens_tela(rs.getString("imagens_tela"));
				 lista.add(md);
			 }
			 pr.close();
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

		return lista;
	}
	
	public List<Diverso> buscaTELASLABImagGerirExame(int grupo)
	{
		List <Diverso> lista = new ArrayList<Diverso>();
		String sql = "select DISTINCT id_tela, tela, linktela,codTela,imagens_tela from vwacesso where id_grupo = ? "
				+ "and  (id_tela = 436 or id_tela = 460 or id_tela = 461) order by id_tela";
		try {
			con = Conexao.getConexao();  
			PreparedStatement pr = con.prepareStatement(sql);
			 pr.setInt(1, grupo);
			 ResultSet rs = pr.executeQuery();
			 while(rs.next())
			 {
				 Diverso md = new Diverso();
				 md.setTela(rs.getString("tela"));
				 md.setLinktela(rs.getString("linktela"));
				 md.setCodTela(rs.getString("codTela"));
				 md.setImagens_tela(rs.getString("imagens_tela"));
				 lista.add(md);
			 }
			 pr.close();
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

		return lista;
	}
	
	public List<Diverso> buscaTELASLABImagExt(int grupo)
	{
		List <Diverso> lista = new ArrayList<Diverso>();
		String sql = "select DISTINCT id_tela, tela, linktela,codTela,imagens_tela from vwacesso where id_grupo = ? "
				+ "and  (id_tela = 437 or id_tela = 438 or id_tela = 439) order by id_tela";
		try {
			con = Conexao.getConexao();  
			PreparedStatement pr = con.prepareStatement(sql);
			 pr.setInt(1, grupo);
			 ResultSet rs = pr.executeQuery();
			 while(rs.next())
			 {
				 Diverso md = new Diverso();
				 md.setTela(rs.getString("tela"));
				 md.setLinktela(rs.getString("linktela"));
				 md.setCodTela(rs.getString("codTela"));
				 md.setImagens_tela(rs.getString("imagens_tela"));
				 lista.add(md);
			 }
			 pr.close();
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

		return lista;
	}
	
	public List<Diverso> buscaTELASLABImagExts(int grupo)
	{
		List <Diverso> lista = new ArrayList<Diverso>();
		String sql = "select DISTINCT id_tela, tela, linktela,codTela,imagens_tela from vwacesso where id_grupo = ? "
				+ "and  (id_tela = 440 or id_tela = 441 or id_tela = 442) order by id_tela";
		try {
			con = Conexao.getConexao();  
			PreparedStatement pr = con.prepareStatement(sql);
			 pr.setInt(1, grupo);
			 ResultSet rs = pr.executeQuery();
			 while(rs.next())
			 {
				 Diverso md = new Diverso();
				 md.setTela(rs.getString("tela"));
				 md.setLinktela(rs.getString("linktela"));
				 md.setCodTela(rs.getString("codTela"));
				 md.setImagens_tela(rs.getString("imagens_tela"));
				 lista.add(md);
			 }
			 pr.close();
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

		return lista;
	}
	
	public List<Diverso> buscaTELASLABImagExtss(int grupo)
	{
		List <Diverso> lista = new ArrayList<Diverso>();
		String sql = "select DISTINCT id_tela, tela, linktela,codTela,imagens_tela from vwacesso where id_grupo = ? "
				+ "and  (id_tela = 443 or id_tela = 444 or id_tela = 445) order by id_tela";
		try {
			con = Conexao.getConexao();  
			PreparedStatement pr = con.prepareStatement(sql);
			 pr.setInt(1, grupo);
			 ResultSet rs = pr.executeQuery();
			 while(rs.next())
			 {
				 Diverso md = new Diverso();
				 md.setTela(rs.getString("tela"));
				 md.setLinktela(rs.getString("linktela"));
				 md.setCodTela(rs.getString("codTela"));
				 md.setImagens_tela(rs.getString("imagens_tela"));
				 lista.add(md);
			 }
			 pr.close();
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

		return lista;
	}
	
	public List<Diverso> buscaTELASLABImagExtsss(int grupo)
	{
		List <Diverso> lista = new ArrayList<Diverso>();
		String sql = "select DISTINCT id_tela, tela, linktela,codTela,imagens_tela from vwacesso where id_grupo = ? "
				+ "and  (id_tela = 446 or id_tela = 447 or id_tela = 448) order by id_tela";
		try {
			con = Conexao.getConexao();  
			PreparedStatement pr = con.prepareStatement(sql);
			 pr.setInt(1, grupo);
			 ResultSet rs = pr.executeQuery();
			 while(rs.next())
			 {
				 Diverso md = new Diverso();
				 md.setTela(rs.getString("tela"));
				 md.setLinktela(rs.getString("linktela"));
				 md.setCodTela(rs.getString("codTela"));
				 md.setImagens_tela(rs.getString("imagens_tela"));
				 lista.add(md);
			 }
			 pr.close();
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

		return lista;
	}
	
	public List<Diverso> buscaTELASLABOTREXAME(int grupo)
	{
		List <Diverso> lista = new ArrayList<Diverso>();
		String sql = "select DISTINCT id_tela, tela, linktela,codTela,imagens_tela from vwacesso where id_grupo = ? "
				+ "and  (id_tela = 291 or id_tela = 292 or id_tela = 293) order by id_tela";
		try {
			con = Conexao.getConexao();  
			PreparedStatement pr = con.prepareStatement(sql);
			 pr.setInt(1, grupo);
			 ResultSet rs = pr.executeQuery();
			 while(rs.next())
			 {
				 Diverso md = new Diverso();
				 md.setTela(rs.getString("tela"));
				 md.setLinktela(rs.getString("linktela"));
				 md.setCodTela(rs.getString("codTela"));
				 md.setImagens_tela(rs.getString("imagens_tela"));
				 lista.add(md);
			 }
			 pr.close();
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

		return lista;
	}
	
	public List<Diverso> buscaTelaCadastrarOutrosExames(int grupo)
	{
		List <Diverso> lista = new ArrayList<Diverso>();
		String sql = "select DISTINCT id_tela, tela, linktela,codTela,imagens_tela from vwacesso where id_grupo = ? "
				+ "and  (id_tela = 294 or id_tela = 462 or id_tela = 463) order by id_tela";
		try {
			con = Conexao.getConexao();  
			PreparedStatement pr = con.prepareStatement(sql);
			 pr.setInt(1, grupo);
			 ResultSet rs = pr.executeQuery();
			 while(rs.next())
			 {
				 Diverso md = new Diverso();
				 md.setTela(rs.getString("tela"));
				 md.setLinktela(rs.getString("linktela"));
				 md.setCodTela(rs.getString("codTela"));
				 md.setImagens_tela(rs.getString("imagens_tela"));
				 lista.add(md);
			 }
			 pr.close();
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

		return lista;
	}
	
	public List<Diverso> buscaTELASLABATDEX(int grupo)
	{
		List <Diverso> lista = new ArrayList<Diverso>();
//		ok
		String sql = "select DISTINCT id_tela, tela, linktela,codTela,imagens_tela from vwacesso where id_grupo = ? "
				+ "and  (id_tela = 251 or id_tela = 458 or id_tela = 459 or id_tela = 464 or id_tela = 465) order by id_tela";
		try {
			con = Conexao.getConexao();  
			PreparedStatement pr = con.prepareStatement(sql);
			 pr.setInt(1, grupo);
			 ResultSet rs = pr.executeQuery();
			 while(rs.next())
			 {
				 Diverso md = new Diverso();
				 md.setTela(rs.getString("tela"));
				 md.setLinktela(rs.getString("linktela"));
				 md.setCodTela(rs.getString("codTela"));
				 md.setImagens_tela(rs.getString("imagens_tela"));
				 lista.add(md);
			 }
			 pr.close();
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

		return lista;
	}
	
	public List<Diverso> buscaTELASLABImagio(int grupo)
	{
		List <Diverso> lista = new ArrayList<Diverso>();
		String sql = "select DISTINCT id_tela, tela, linktela,codTela,imagens_tela from vwacesso where id_grupo = ? "
				+ "and  id_tela = 286 or id_tela = 287 or id_tela = 288 or id_tela = 328 order by id_tela";
		try {
			con = Conexao.getConexao();  
			PreparedStatement pr = con.prepareStatement(sql);
			 pr.setInt(1, grupo);
			 ResultSet rs = pr.executeQuery();
			 while(rs.next())
			 {
				 Diverso md = new Diverso();
				 md.setTela(rs.getString("tela"));
				 md.setLinktela(rs.getString("linktela"));
				 md.setCodTela(rs.getString("codTela"));
				 md.setImagens_tela(rs.getString("imagens_tela"));
				 lista.add(md);
			 }
			 pr.close();
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

		return lista;
	}

	public List<Diverso> buscaTelaCobraCredito(int grupo)
	{
		List <Diverso> lista = new ArrayList<Diverso>();
		String sql = "select DISTINCT id_tela, tela, linktela,codTela,imagens_tela from vwacesso where id_grupo = ?	and id_tela between 281 and 283 order by id_tela asc";
		try {
			con = Conexao.getConexao();  
			PreparedStatement pr = con.prepareStatement(sql);
			 pr.setInt(1, grupo);
			 ResultSet rs = pr.executeQuery();
			 while(rs.next())
			 {
				 Diverso md = new Diverso();
				 md.setTela(rs.getString("tela"));
				 md.setLinktela(rs.getString("linktela"));
				 md.setCodTela(rs.getString("codTela"));
				 md.setImagens_tela(rs.getString("imagens_tela"));
				 lista.add(md);
			 }
			 pr.close();
			 con.close();
		} catch (Exception e) {
			
			e.printStackTrace();
		}
		return lista;
	}
	
	public List<Diverso> buscaTelaSeguroSaude(int grupo)
	{
		List <Diverso> lista = new ArrayList<Diverso>();
		String sql = "select DISTINCT id_tela, tela, linktela,codTela,imagens_tela from vwacesso where id_grupo = ?	and id_tela = 279 or id_tela = 289 or id_tela = 280 order by id_tela asc";
		try {
			con = Conexao.getConexao();  
			PreparedStatement pr = con.prepareStatement(sql);
			 pr.setInt(1, grupo);
			 ResultSet rs = pr.executeQuery();
			 while(rs.next())
			 {
				 Diverso md = new Diverso();
				 md.setTela(rs.getString("tela"));
				 md.setLinktela(rs.getString("linktela"));
				 md.setCodTela(rs.getString("codTela"));
				 md.setImagens_tela(rs.getString("imagens_tela"));
				 lista.add(md);
			 }
			 pr.close();
			 con.close();
		} catch (Exception e) {
			
			e.printStackTrace();
		}
		return lista;
	}

	public static AdressCredencias getCredencia(){
		String [] linha = {};
		
		AdressCredencias adr = new AdressCredencias();
		try {
			String fileConexao = getLocalFile();
			Scanner in = new Scanner(new FileReader(fileConexao));
			if(in.hasNextLine()){
				String line = in.nextLine();
				linha  =  line.split("-");
				
			}
			if(linha.length > 0){
				
					adr.setUrl(linha[0].split(":")[1]);
					adr.setBdname(linha[1].split(":")[2]);
					adr.setUser(linha[2].split(":")[2]);
					adr.setPass(linha[3].split(":")[2]);
			}
		} catch (FileNotFoundException e) {
			logs("erroficheiro.txt",e.getMessage().toString());
		} catch (UnknownHostException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return adr;
	}
	
	public static void logs(String aux,String msg)  
	{
	
		try {
			File dir = new File( "c:\\gestlogs");
			 
			if( dir.mkdir() ){
			     System.out.println("Diret�rio Criado");
			     FileWriter arq = new FileWriter("c:\\gestlogs\\"+aux+".txt");
				 PrintWriter gravarArq = new PrintWriter(arq); 
			     gravarArq.printf(msg);	 
			     arq.close();
			}
			
		} catch (IOException e) { 
			e.printStackTrace();
		}
	}
	
	public static String getLocalFile() throws UnknownHostException
	{
		String  local = "";
		String maquina =  InetAddress.getLocalHost().getHostName();
		if(maquina.equals("APV-DSV28"))
			local = "C:\\Users\\nelson.joao\\git\\CliGest\\WebContent\\config\\apvLocal.txt";
		else if(maquina.equals("Caricoco"))
			local = "C:\\ATW\\ATGesthosp\\WebContent\\config\\local.txt";
		else if(maquina.equals("AT"))
			local = "C:\\Users\\NB47\\Documents\\workspace_extra\\CliGest\\WebContent\\config\\local.txt";
		else
			local = server;
		return local;
	}
	
	public static String getLocal()
	{
		String  local = "";
		try {
			String maquina =  InetAddress.getLocalHost().getHostName();
			if(maquina.equals("APV-DSV28"))
				local = "C:\\Users\\nelson.joao\\git\\CliGest\\WebContent\\config\\";
			else if(maquina.equals("Caricoco"))
				local = "C:\\ATW\\ATGesthosp\\WebContent\\config\\";
			else if(maquina.equals("AT"))
				local = "C:\\Users\\NB47\\Documents\\workspace_extra\\CliGest\\WebContent\\config\\";
			else
				local = server;
		} catch (Exception e) {
			// TODO: handle exception
		}
		return local;
	}
}


