package ao.co.cligest.negocio;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.sql.Date;
import java.util.List;

import ao.co.cligest.entidades.*;


public class TriagemDAO {

private Connection con ;
 
//Esta lista serve para visualizar os pacientes
    public List<Paciente> buscarpacienteFacturado(){
		List <Paciente> lista = new ArrayList<Paciente>();
		String sql = "SELECT * FROM vwpacientesparatriar";
		try {
			 con = Conexao.getConexao();
			 PreparedStatement preparador = con.prepareStatement(sql);
			 ResultSet rs = preparador.executeQuery();
			 while(rs.next())
			 {
				 Paciente fun = new Paciente();
				 fun.setId_entidade(rs.getInt("FK_consulta_marcada"));
				 fun.setNumero_processo(rs.getString("numero_processo"));
				 fun.setNome(rs.getString("paciente"));
//				 fun.setNomem(rs.getString("nome_meio"));
//				 fun.setApelido(rs.getString("ultimo_nome"));
				 fun.setServico(rs.getString("servico"));
				 fun.setId_servico(rs.getInt("id_servico"));
				 fun.setFK_doutor(rs.getInt("fk_doutor"));
				fun.setFK_paciente(rs.getInt("FK_paciente"));
				fun.setNome_doutor(rs.getString("medico"));
				fun.setPreco(rs.getDouble("preco"));;
				Calendar data = Calendar.getInstance();
				data.setTime(rs.getDate("data_consulta"));
				fun.setData_do_agendamento(data);
				 fun.setId_confirmada(rs.getInt("id_consulta_confirmada"));
				 fun.setHora(rs.getTime("Hora_Consulta"));
				 lista.add(fun);
			 }
			 preparador.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		catch (Exception e) {
			// TODO: handle exception
		}
		finally{
				try{
					con.close();
				}catch(Throwable e){
					System.out.println("Erro ao fechar as conexão");
					e.printStackTrace();
				}
			}
		return lista;
	}
	public List <Triagem> PacienteVisualizar ()
	{
		List <Triagem> lista = new ArrayList<Triagem>();
		String sql = "SELECT * FROM vwconsultaconfirmada LIMIT 0,10";
		try {
			 con = Conexao.getConexao();
			 PreparedStatement preparador = con.prepareStatement(sql);
			 ResultSet rs = preparador.executeQuery();
			 while(rs.next())
			 {
				 Triagem fun = new Triagem();
				 fun.setId_cons_conf(rs.getInt("id_consulta_confirmada"));
				 fun.setNumero_processo(rs.getString("numero_processo"));
			     fun.setNome(rs.getString("nome"));
			     fun.setNomem(rs.getString("nome_meio"));
			     fun.setApelido(rs.getString("ultimo_nome"));
			     fun.setServico(rs.getString("servico"));
				 lista.add(fun);
			 }
			 preparador.close();
			  
		} catch (SQLException e) {
			e.printStackTrace();
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
		return lista;
	  }
	
//	Esta lista serve para pesquisar os pacientes
	public List <Funcionario> pesquisarNomePaciente (String nome)
	{
		List <Funcionario> lista = new ArrayList<Funcionario>();
		String sql = "SELECT * FROM vwentidadecidadaocomofuncionario WHERE NOME LIKE ?";
		try {
			con = Conexao.getConexao();
			 PreparedStatement preparador = con.prepareStatement(sql);
			 preparador.setString(1, nome);
			 ResultSet rs = preparador.executeQuery();
			 while(rs.next())
			 {
				 Funcionario fun = new Funcionario();
				 fun.setId(rs.getInt("ID_ENTIDADE"));
				 fun.setNome(rs.getString("NOME"));
				 fun.setNomem(rs.getString("NOME_MEIO"));
				 fun.setApelido(rs.getString("ULTIMO_NOME"));
				 fun.setNomeEsp(rs.getString("ESPECIALIDADE"));
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
	
//	Este metodo serve para inserir os dados no banco
	public int InserirDadosPaciente(Triagem fun)
	{
		int ultimoCod = 0;
		String sql = "INSERT INTO TBLTRIAGEM(FK_consulta_confirmada,"
												+ "FK_paciente, "
												+ "FK_doutor, "
												+ "FK_funcionario, "
												+ "FK_estado_paciente, "
												+ "FK_servico, "
												+ "FK_temperatura, " 
												+ "FK_pulso, 	"
												+ "FK_respiracao,"
												+ "FK_tensao_sistolica,"
												+ "FK_tensao_diastolica,"
												+ "peso,"
												+ "data,"
												+ "imc,"
												+ "altura,"
												+ "diagnostico_preliminar, hora_registo)" + " VALUES(?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)";
		try{
			con = Conexao.getConexao();
			PreparedStatement triagPac = con.prepareStatement(sql);
			triagPac.setInt(1, fun.getId_cons_conf());
			triagPac.setInt(2, fun.getFK_paciente());
			triagPac.setInt(3, fun.getFK_doutor());
			triagPac.setInt(4, fun.getFk_funcionario());
			triagPac.setInt(5, fun.getFK_estado_do_paciente());
			triagPac.setInt(6, fun.getId_servico());
			triagPac.setInt(7, fun.getFk_temperatura());
			triagPac.setInt(8, fun.getFk_pulso());
			triagPac.setInt(9, fun.getFk_respiracao());
			triagPac.setInt(10, fun.getFK_tensao_sistolica());
			triagPac.setInt(11, fun.getFK_tensao_diastolica());
			triagPac.setDouble(12, fun.getPeso());
			triagPac.setDate(13, data_registo());
			triagPac.setDouble(14, fun.getImc());
			triagPac.setDouble(15,fun.getAltura());
//			triagPac.setInt(15, fun.getUsuario());
			triagPac.setString(16, fun.getDiagnostico_preliminar());
			triagPac.setString(17, new Formatando().horaAtual());
			triagPac.execute();
			
			ResultSet rs = triagPac.executeQuery("SELECT LAST_INSERT_ID()");
			 if(rs.next()){
				ultimoCod = rs.getInt(1); 
			 }
			triagPac.close();
			 
			System.out.println("Cadastro de Sucesso....TBLTRIAGEM");
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
		return ultimoCod;
	}
	
	public  int buscarEstPac (String num)
	   {
	   int fk_estado=0;
	    String sql = "SELECT * FROM tblestadodopaciente where estado_do_paciente LIKE ?";
	    try {
	    	con = Conexao.getConexao();
	     PreparedStatement preparador = con.prepareStatement(sql);
	     preparador.setString(1, num);
	     ResultSet rs = preparador.executeQuery();
	      if(rs.next())
	      {
	    	  fk_estado= rs.getInt("id_estado_do_paciente");
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
	    return fk_estado;
	   }
	public Triagem sinais(int cod){ 
		Triagem pac = null;
		String sql = "SELECT * FROM vwsinais where fk_paciente = ? order by FK_consulta_confirmada desc";
		try
		{
			con = Conexao.getConexao();
			 PreparedStatement preparador = con.prepareStatement(sql);
			 preparador.setInt(1, cod);
			
			 ResultSet rs = preparador.executeQuery();
			 if(rs.next())
			 {
				 pac = new Triagem();
				 pac.setAltura_s(rs.getString("altura"));
				 pac.setPeso(rs.getDouble("peso"));
				 pac.setImc(rs.getDouble("imc"));
				 pac.setValor_tmp(rs.getString("temperatura"));
				 pac.setValor_pulso(rs.getString("pulso"));
				 pac.setValor_tns(rs.getString("tensao"));
				 pac.setValor_resp(rs.getString("respiracao"));
				 pac.setEstado(rs.getString("estado_do_paciente"));
				 pac.setDescricao(rs.getString("diagnostico_preliminar"));
				 
			 }
			 preparador.close();
			  
		}
		catch(SQLException er){
		System.out.println(er);	
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
		return pac;
	}
	public Triagem sinais(int cod,java.sql.Date data)
	{
		Triagem pac = null;
		String sql = "SELECT * FROM vwsinais where fk_paciente = ? and data = ?";
		try
		{
			con = Conexao.getConexao();
			 PreparedStatement preparador = con.prepareStatement(sql);
			 preparador.setInt(1, cod);
			 preparador.setDate(2, data);
			 ResultSet rs = preparador.executeQuery();
			 if(rs.next())
			 {
				 pac = new Triagem();
				 pac.setAltura(rs.getDouble("altura"));
				 pac.setPeso(rs.getDouble("peso"));
				 pac.setImc(rs.getDouble("imc"));
				 pac.setValor_tmp(rs.getString("temperatura"));
				 pac.setValor_pulso(rs.getString("pulso"));
				 pac.setValor_tns(rs.getString("tensao"));
				 pac.setValor_resp(rs.getString("respiracao"));
				 pac.setEstado(rs.getString("estado_do_paciente"));
				 pac.setDescricao(rs.getString("diagnostico_preliminar"));
				 
			 }
			 preparador.close();
		}
		
		catch(SQLException er){
		System.out.println(er);	
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
		return pac;
	}
	
	public List<Triagem> diagnosticoDatas(int cod)
	 {
	  List<Triagem> lista = new ArrayList<Triagem>();
	  //Triagem pac = null;
	  String sql = "SELECT * FROM vwdiagnosticados where fk_paciente = ? group by (data_daconsulta) order by data_daconsulta desc";
	  try
	  {
	   con = Conexao.getConexao();
	    PreparedStatement preparador = con.prepareStatement(sql);
	    preparador.setInt(1, cod);    
	    ResultSet rs = preparador.executeQuery();
	    while(rs.next())
	    {
	     Triagem pac  = new Triagem();
	     pac.setNome(rs.getString("nome_enfermeiro"));
//	     pac.setNomem(rs.getString("nome_meio"));
//	     pac.setApelido(rs.getString("ultimo_nome"));
	     Calendar data = Calendar.getInstance();
	     data.setTime(rs.getDate("data_daconsulta"));
	     pac.setData(data);
	     pac.setFK_paciente(rs.getInt("fk_paciente"));
	     lista.add(pac);
	    }
	    preparador.close();
	  }
	  catch(SQLException er){
	   er.printStackTrace();
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
	
	public List<Triagem> consultasDatas(int cod)
	{
		List<Triagem> lista = new ArrayList<Triagem>();
		//Triagem pac = null;
		String sql = "SELECT * FROM vwsinais where fk_paciente = ? group by (data) order by data desc";
		try
		{
			con = Conexao.getConexao();
			 PreparedStatement preparador = con.prepareStatement(sql);
			 preparador.setInt(1, cod);    
			 ResultSet rs = preparador.executeQuery();
			 while(rs.next())
			 {
				 Triagem pac  = new Triagem();
				 pac.setNome(rs.getString("nome_enfermeiro"));
//				 pac.setNomem(rs.getString("nome_meio"));
//				 pac.setApelido(rs.getString("ultimo_nome"));
				 Calendar data = Calendar.getInstance();
				 data.setTime(rs.getDate("data"));
				 pac.setData(data);
				 pac.setFK_paciente(rs.getInt("fk_paciente"));
				 lista.add(pac);
			 }
			 preparador.close();
		}
		catch(SQLException er){
			System.out.println(er);
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
	
	public List<Triagem> receituarioDatas(int cod)
	{
		List<Triagem> lista = new ArrayList<Triagem>();
		//Triagem pac = null;
		String sql = "SELECT * FROM vwreceituario where fk_paciente = ? group by (data_daconsulta) order by data_daconsulta desc";
		try
		{
			con = Conexao.getConexao();
			 PreparedStatement preparador = con.prepareStatement(sql);
			 preparador.setInt(1, cod);
			 ResultSet rs = preparador.executeQuery();
			 while(rs.next())
			 {
				 Triagem pac  = new Triagem();
				 pac.setNome(rs.getString("nome"));
				 pac.setApelido(rs.getString("ultimo_nome"));
				 Calendar data = Calendar.getInstance();
				 data.setTime(rs.getDate("data_daconsulta"));
				 pac.setData(data);
				 pac.setFK_paciente(rs.getInt("fk_paciente"));
				 lista.add(pac);
			 }
			 preparador.close();
		}
		catch(SQLException er){
			System.out.println(er);
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
	public List<Triagem> receituarioVW(int cod,int fkcons)
	{
		List<Triagem> lista = new ArrayList<Triagem>();
		//Triagem pac = null;
		String sql = "SELECT * FROM vwreceituario where fk_paciente = ? and id_consulta = ?";
		try
		{
			con = Conexao.getConexao();
			 PreparedStatement preparador = con.prepareStatement(sql);
			 preparador.setInt(1, cod);
			 preparador.setInt(2, fkcons);
			 ResultSet rs = preparador.executeQuery();
			 while(rs.next())
			 {
				 Triagem pac  = new Triagem();
				 pac.setId_consulta(rs.getInt("id_consulta"));
				 pac.setFarmaco(rs.getString("nome_comercial")); 
				 pac.setVidAd(rs.getString("viaadministrar"));
				 pac.setQuantidade(rs.getInt("quantidade"));
				 pac.setDosagem(rs.getString("dosagem"));
				 pac.setPossologia(rs.getString("possologia"));
				 pac.setId_recetuario(rs.getInt("id_receituario"));
				 pac.setDescricao(rs.getString("observacao"));
				 pac.setFk_farmaco(rs.getInt("FK_farmaco"));
				 lista.add(pac);
			 }
			 preparador.close();
		}
		catch(SQLException er){
			System.out.println(er);
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
	
	public Triagem receituario_views(int cod, java.sql.Date data)
	 {
	  Triagem pac = null;
	  //Triagem pac = null;
	  String sql = "SELECT * FROM vwreceituario where fk_paciente = ? and data_daconsulta = ?";
	  try
	  {  
	   con = Conexao.getConexao();
	    PreparedStatement preparador = con.prepareStatement(sql);
	    preparador.setInt(1, cod);
	    preparador.setDate(2, data);
	    ResultSet rs = preparador.executeQuery();
	    
	    if(rs.next())
	    {
	     pac  = new Triagem();
	     pac.setFarmaco(rs.getString("nome_comercial"));
	     pac.setVidAd(rs.getString("viaadministrar"));
	     pac.setQuantidade(rs.getInt("quantidade"));
	     pac.setDosagem(rs.getString("dosagem"));
	     pac.setPosologia(rs.getString("possologia"));
	     pac.setDescricao(rs.getString("observacao"));
	     
	    }
	    preparador.close();
	  }
	  catch(SQLException er){
	   System.out.println(er);
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
	  return pac;
	 }
	
	public List <Triagem> receituario_viewss(int cod, java.sql.Date data)
	 {
		List <Triagem> lista = new ArrayList<Triagem>();
	  Triagem pac = null;
	  //Triagem pac = null;
	  String sql = "SELECT * FROM vwreceituario where fk_paciente = ? and data_daconsulta = ?";
	  try
	  {  
	   con = Conexao.getConexao();
	    PreparedStatement preparador = con.prepareStatement(sql);
	    preparador.setInt(1, cod);
	    preparador.setDate(2, data);
	    ResultSet rs = preparador.executeQuery();
	    
	    while(rs.next())
	    {
	     pac  = new Triagem();
	     pac.setFarmaco(rs.getString("nome_comercial"));
	     pac.setVidAd(rs.getString("viaadministrar"));
	     pac.setQuantidade(rs.getInt("quantidade"));
	     pac.setDosagem(rs.getString("dosagem"));
	     pac.setPosologia(rs.getString("possologia"));
	     pac.setDescricao(rs.getString("observacao"));
	     lista.add(pac);
	    }
	    preparador.close();
	  }
	  catch(SQLException er){
	   System.out.println(er);
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
	
	public List<Triagem> ExamesClinicoDatas(int cod)
	{
		List<Triagem> lista = new ArrayList<Triagem>();
		//Triagem pac = null;
		String sql = "SELECT * FROM vwexamesclinicosdatas where fk_paciente = ? group by (id_consulta) order by data_daconsulta desc";
		try
		{
			con = Conexao.getConexao();
			 PreparedStatement preparador = con.prepareStatement(sql);
			 preparador.setInt(1, cod);
			 ResultSet rs = preparador.executeQuery();
			 while(rs.next())
			 {
				 Triagem pac  = new Triagem();
				 pac.setNome(rs.getString("nome"));
				 pac.setApelido(rs.getString("ultimo_nome"));
				 Calendar data = Calendar.getInstance();
				 data.setTime(rs.getDate("data_daconsulta"));
				 pac.setData(data);
				 pac.setFK_paciente(rs.getInt("fk_paciente"));
				 lista.add(pac);
			 }
			 preparador.close();
		}
		catch(SQLException er){
			System.out.println(er);
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
	
	public List<Triagem> TratamentoDatas(int cod)
	{
		List<Triagem> lista = new ArrayList<Triagem>();
		//Triagem pac = null;
		String sql = "SELECT * FROM vwtratamentosrecomendas where fk_paciente = ? group by data_daconsulta order by data_daconsulta desc";
		try
		{
			con = Conexao.getConexao();
			 PreparedStatement preparador = con.prepareStatement(sql);
			 preparador.setInt(1, cod);
			 ResultSet rs = preparador.executeQuery();
			 while(rs.next())
			 {
				 
				 Triagem pac  = new Triagem();
				 pac.setNome(rs.getString("nome"));
				 pac.setApelido(rs.getString("ultimo_nome"));
				 Calendar data = Calendar.getInstance();
				 data.setTime(rs.getDate("data_daconsulta"));
				 pac.setData(data);
				 pac.setFK_paciente(rs.getInt("fk_paciente"));
				 pac.setTratamento(rs.getString("descricao_tratamento"));
				 pac.setRecomendacao(rs.getString("descricao_recomendacao"));
				 lista.add(pac);
				  
			 }
			 preparador.close();
		}
		catch(SQLException er){
			System.out.println(er);
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
	public List<Triagem> ExamesClinicoDatas(int cod,java.sql.Date data)
	{
		List<Triagem> lista = new ArrayList<Triagem>();
		Triagem pac = null;
		String sql = "SELECT * FROM vwexamesclinicosdatas where fk_paciente = ? and data_daconsulta = ?";
		try
		{
			con = Conexao.getConexao();
			 PreparedStatement preparador = con.prepareStatement(sql);
			 preparador.setInt(1, cod);
			 preparador.setDate(2, data);
			 ResultSet rs = preparador.executeQuery();
			 while(rs.next())
			 {
				 pac = new Triagem();
				 pac.setAnalise(rs.getString("analise_clinica"));
				 pac.setPreco(rs.getDouble("preco"));
				 pac.setDescricao(rs.getString("observacao"));
				 lista.add(pac);
			 }
			 preparador.close();
		}
		catch(SQLException er){
			System.out.println("Eror aqui: "+er);
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
	public List<Triagem> exameClinico_views(int cod,java.sql.Date data)
	{
		List<Triagem> lista = new ArrayList<Triagem>();
		Triagem pac = null;
		String sql = "SELECT * FROM vwexamesclinicosdatas where fk_paciente = ? and data_daconsulta = ?";
		try
		{
			con = Conexao.getConexao();
			 PreparedStatement preparador = con.prepareStatement(sql);
			 preparador.setInt(1, cod);
			 preparador.setDate(2, data);
			 ResultSet rs = preparador.executeQuery();
			 while(rs.next())
			 {
				 pac = new Triagem();
				 pac.setAnalise(rs.getString("analise_clinica"));
				 pac.setPreco(rs.getDouble("preco"));
				 pac.setDescricao(rs.getString("observacao"));
				 lista.add(pac);
			 }
			 preparador.close();
		}
		catch(SQLException er){
			System.out.println("Eror aqui: "+er);
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
	public List<Triagem> ExamesClinicoDatasViews(int cod)
	{
		List<Triagem> lista = new ArrayList<Triagem>();
		Triagem pac = null;
		String sql = "SELECT * FROM vwexamesclinicosdatas where fk_paciente = ? and data_daconsulta = curdate() order by data_daconsulta desc";
		try
		{
			con = Conexao.getConexao();
			 PreparedStatement preparador = con.prepareStatement(sql);
			 preparador.setInt(1, cod);
			 ResultSet rs = preparador.executeQuery();
			 while(rs.next())
			 {
				 pac = new Triagem();
				 pac.setId_consulta(rs.getInt("id_consulta"));
				 pac.setAnalise(rs.getString("analise_clinica"));
				 pac.setPreco(rs.getDouble("preco"));
				 pac.setDescricao(rs.getString("observacao"));
				 lista.add(pac);
			 }
			 preparador.close();
		}
		catch(SQLException er){
			System.out.println("Eror aqui: "+er);
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
	public List<Triagem> consultasAntecedentes(int cod)
	{
		List<Triagem> lista = new ArrayList<Triagem>();
		//Triagem pac = null;
		String sql = "SELECT * FROM vwantecedentes where fk_paciente = ? order by data_daconsulta desc";
		try
		{
			con = Conexao.getConexao();
			 PreparedStatement preparador = con.prepareStatement(sql);
			 preparador.setInt(1, cod);
			 ResultSet rs = preparador.executeQuery();
			 while(rs.next())
			 {
				 Triagem pac  = new Triagem();
				 pac.setNome(rs.getString("nome"));
				 pac.setNomem(rs.getString("nome_meio"));
				 pac.setApelido(rs.getString("ultimo_nome"));
				 Calendar data = Calendar.getInstance();
				 data.setTime(rs.getDate("data_daconsulta"));
				 pac.setData(data);
				 pac.setFK_paciente(rs.getInt("fk_paciente"));
				 lista.add(pac);
				 
			 }
			 preparador.close();
		}
		catch(SQLException er){System.out.println(er);	}
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
	
	public Triagem antecedentes_views(int cod,java.sql.Date data)
	{
		Triagem pac = null;
		String sql = "SELECT * FROM vwantecedentes where fk_paciente = ? and data_daconsulta = ?";
		try
		{
			con = Conexao.getConexao();
			 PreparedStatement preparador = con.prepareStatement(sql);
			 preparador.setInt(1, cod);
			 preparador.setDate(2, data);
			 ResultSet rs = preparador.executeQuery();
			 if(rs.next())
			 {
				 pac = new Triagem();
				 pac.setDescricao(rs.getString("obs_antecedentepessoal"));
				 pac.setTransfusao(rs.getInt("transfusao"));
				 pac.setCardiopatia(rs.getInt("cardiopatia"));
				 pac.setOsteoporose(rs.getInt("osteoporose"));
				 pac.setTuberculose(rs.getInt("tuberculose"));
				 pac.setAcidente(rs.getInt("acidente"));
				 pac.setTabagismo(rs.getInt("tabagismo"));
				 pac.setDiabete(rs.getInt("diabete"));
				 pac.setCirurgia(rs.getInt("cirurgia"));
				 pac.setHta(rs.getInt("hta"));
				 pac.setAvc(rs.getInt("avc"));
				 pac.setDoenca_renal_cronica(rs.getInt("doenca_renal_cronica"));
				 pac.setEndocrinas_metabolica(rs.getInt("endocrinas_metabolica"));
				 pac.setEtilismo(rs.getInt("etilismo"));
				 pac.setAlergia(rs.getInt("alergia"));
				 pac.setAnemia(rs.getInt("anemia"));
				 pac.setDrogas(rs.getInt("drogas"));
				 pac.setVirose(rs.getInt("virose"));
				 pac.setCancro(rs.getInt("cancro"));
				 pac.setDts(rs.getInt("dts"));
			 }
			 preparador.close();
		}
		catch(SQLException er){
			System.out.println("Eror aqui: "+er);
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
		return pac;
	}
	
	public Triagem antecedentes_views(int cod)
	{
		Triagem pac = null;
		String sql = "SELECT * FROM vwantecedentes where fk_paciente = ? ";
		try
		{
			con = Conexao.getConexao();
			 PreparedStatement preparador = con.prepareStatement(sql);
			 preparador.setInt(1, cod);
			 ResultSet rs = preparador.executeQuery();
			 if(rs.next())
			 {
				 pac = new Triagem();
				 pac.setDescricao(rs.getString("obs_antecedentepessoal"));
				 pac.setTransfusao(rs.getInt("transfusao"));
				 pac.setCardiopatia(rs.getInt("cardiopatia"));
				 pac.setOsteoporose(rs.getInt("osteoporose"));
				 pac.setTuberculose(rs.getInt("tuberculose"));
				 pac.setAcidente(rs.getInt("acidente"));
				 pac.setTabagismo(rs.getInt("tabagismo"));
				 pac.setDiabete(rs.getInt("diabete"));
				 pac.setCirurgia(rs.getInt("cirurgia"));
				 pac.setHta(rs.getInt("hta"));
				 pac.setAvc(rs.getInt("avc"));
				 pac.setDoenca_renal_cronica(rs.getInt("doenca_renal_cronica"));
				 pac.setEndocrinas_metabolica(rs.getInt("endocrinas_metabolica"));
				 pac.setEtilismo(rs.getInt("etilismo"));
				 pac.setAlergia(rs.getInt("alergia"));
				 pac.setAnemia(rs.getInt("anemia"));
				 pac.setDrogas(rs.getInt("drogas"));
				 pac.setVirose(rs.getInt("virose"));
				 pac.setCancro(rs.getInt("cancro"));
				 pac.setDts(rs.getInt("dts"));
			 }
			 preparador.close();
		}
		catch(SQLException er){
			System.out.println("Eror aqui: "+er);
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
		return pac;
	}
	public List<Triagem> diagnosticados_views(int cod,java.sql.Date data)
	{
		List<Triagem> lista = new ArrayList<Triagem>();
		Triagem pac = null;
		String sql = "SELECT * FROM vwdiagnosticados where fk_paciente = ? and data_daconsulta = ?";
		try
		{
			con = Conexao.getConexao();
			 PreparedStatement preparador = con.prepareStatement(sql);
			 preparador.setInt(1, cod);
			 preparador.setDate(2, data);
			 ResultSet rs = preparador.executeQuery();
			 while(rs.next())
			 {
				 pac = new Triagem();
				 pac.setAnalise(rs.getString("diagnostico_cid"));
				 pac.setFk_cid(rs.getString("cod_cid"));
//				 pac.setPreco(rs.getDouble("preco"));
				 pac.setDescricao(rs.getString("obs_diagnostico"));
				 lista.add(pac);
			 }
			 preparador.close();
		}
		catch(SQLException er){
			System.out.println("Eror aqui: "+er);
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
	public List<Triagem> diagnosticados_views(int cod)
	{
		List<Triagem> lista = new ArrayList<Triagem>();
		String sql = "SELECT * FROM vwdiagnosticados where fk_paciente = ? and data_daconsulta = CURDATE() ";
		try
		{
			 con = Conexao.getConexao();
			 PreparedStatement preparador = con.prepareStatement(sql);
			 preparador.setInt(1, cod);
			 ResultSet rs = preparador.executeQuery();
			 while(rs.next())
			 {
				 Triagem pac = new Triagem();
				 pac.setDescricao(rs.getString("diagnostico_cid"));
				 pac.setFk_cid(rs.getString("cod_cid"));
				 lista.add(pac);
			 }
			 preparador.close();
		}
		catch(SQLException er){
			System.out.println("Eror aqui: "+er);
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
	
	public List<Triagem> diagnosticados_dia(int cod)
	{
		List<Triagem> lista = new ArrayList<Triagem>();
		String sql = "SELECT * FROM vwdiagnosticados where id_consulta = ? and data_daconsulta = CURDATE() order by data_daconsulta desc ";
		try
		{
			 con = Conexao.getConexao();
			 PreparedStatement preparador = con.prepareStatement(sql);
			 preparador.setInt(1, cod);
			 ResultSet rs = preparador.executeQuery();
			 while(rs.next())
			 {
				 Triagem pac = new Triagem();
				 pac.setDescricao(rs.getString("diagnostico_cid"));
				 pac.setFk_cid(rs.getString("cod_cid"));
				 pac.setDiagnostico_preliminar(rs.getString("obs_diagnostico"));
				 lista.add(pac);
			 }
			 preparador.close();
		}
		catch(SQLException er){
			System.out.println("Eror aqui: "+er);
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
	public Triagem diagnosticados_doc(int cod)
	{
		Triagem pac = new Triagem();
		String sql = "SELECT * FROM vwdiagnosticados where fk_paciente = ? and data_daconsulta = CURDATE()";
		try
		{
			 con = Conexao.getConexao();
			 PreparedStatement preparador = con.prepareStatement(sql);
			 preparador.setInt(1, cod);
			 ResultSet rs = preparador.executeQuery();
			 if(rs.next())
			 {
				 pac.setId_consulta(rs.getInt("id_consulta"));
				 pac.setFK_doutor(rs.getInt("fk_medico"));
				 pac.setFK_paciente(rs.getInt("fk_paciente")); 
			 }
			 preparador.close();
		}
		catch(SQLException er){
			System.out.println("Eror aqui: "+er);
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
		return pac;
	}
	public Triagem exam_fisicos_views(int cod,java.sql.Date data)
	{
		Triagem pac = null;
		String sql = "SELECT * FROM vwexamesfisicos where fk_paciente = ? and data_daconsulta = ?";
		try
		{
			con = Conexao.getConexao();
			 PreparedStatement preparador = con.prepareStatement(sql);
			 preparador.setInt(1, cod);
			 preparador.setDate(2, data);
			 ResultSet rs = preparador.executeQuery();
			 if(rs.next())
			 {
				 
				 pac = new Triagem();
				 pac.setExa_cabeca(rs.getString("cabeca"));
				 pac.setExa_abdomen(rs.getString("abdomen"));
				 pac.setExa_membInf(rs.getString("mebro_inferior"));
				 pac.setExa_membSup(rs.getString("membro_superior"));
				 pac.setExa_pescoco(rs.getString("pescoco"));
				 pac.setExa_torax(rs.getString("torax"));
				 pac.setExa_urinario(rs.getString("genito_urinario"));
				 pac.setObjectivo_geral(rs.getString("objectivo_geral"));
				 pac.setSistema_nervoso(rs.getString("sistema_nervoso"));
			 }
			 preparador.close();
		}
		catch(SQLException er){
			System.out.println("Eror aqui: "+er);
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
		return pac;
	}
	
	public Triagem tratamentos_views(int cod,java.sql.Date data)
	{
		Triagem pac = null;
		String sql = "SELECT * FROM vwtratamentosrecomendas where fk_paciente = ? and data_daconsulta = ?";
		try
		{
			con = Conexao.getConexao();
			 PreparedStatement preparador = con.prepareStatement(sql);
			 preparador.setInt(1, cod);
			 preparador.setDate(2, data);
			 ResultSet rs = preparador.executeQuery();
			 if(rs.next())
			 {
				 
				 pac = new Triagem();
				 pac.setTratamento(rs.getString("descricao_tratamento"));
				 pac.setRecomendacao(rs.getString("descricao_recomendacao"));
			 }
			 preparador.close();
		}
		catch(SQLException er){
			System.out.println("Eror aqui: "+er);
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
		return pac;
	}
	public List<Triagem> consultasDatasQH(int cod)
	{
		List<Triagem> lista = new ArrayList<Triagem>();
		//Triagem pac = null;
		String sql = "SELECT * FROM vwqueixashistoricos where fk_paciente = ? order by data_daconsulta desc";
		try
		{  
			con = Conexao.getConexao();
			 PreparedStatement preparador = con.prepareStatement(sql);
			 preparador.setInt(1, cod);
			 ResultSet rs = preparador.executeQuery();
			 while(rs.next())
			 {
				 Triagem pac  = new Triagem();
				 pac.setNome(rs.getString("nome"));
				 pac.setApelido(rs.getString("ultimo_nome")); 
 				 Calendar data = Calendar.getInstance();
 				 data.setTime(rs.getDate("data_daconsulta"));
 				 pac.setData(data);
 				 pac.setFK_paciente(rs.getInt("fk_paciente"));
 				 pac.setDescricao(rs.getString("descricao"));
 				 pac.setHistorial(rs.getString("historico_dadoenca"));
				 lista.add(pac);
			 }
			 preparador.close();
		}
		catch(SQLException er){
			System.out.println(er);
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
	public List<Triagem> consultasDatasEF(int cod)
	{
		List<Triagem> lista = new ArrayList<Triagem>();
		//Triagem pac = null;
		String sql = "SELECT * FROM vwexamesfisicos where fk_paciente = ? order by data_daconsulta desc";
		try
		{  
			con = Conexao.getConexao();
			 PreparedStatement preparador = con.prepareStatement(sql);
			 preparador.setInt(1, cod);
			 ResultSet rs = preparador.executeQuery();
			 while(rs.next())
			 {
				 Triagem pac  = new Triagem();
				 pac.setNome(rs.getString("nome"));
				 pac.setApelido(rs.getString("ultimo_nome")); 
 				 Calendar data = Calendar.getInstance();
 				 data.setTime(rs.getDate("data_daconsulta"));
 				 pac.setData(data);
 				 pac.setFK_paciente(rs.getInt("fk_paciente"));
 				 pac.setExa_cabeca(rs.getString("cabeca"));
 				 pac.setExa_membSup(rs.getString("membro_superior"));
 				 pac.setExa_membInf(rs.getString("mebro_inferior"));
 				 pac.setExa_abdomen(rs.getString("abdomen"));
 				 pac.setExa_pescoco(rs.getString("pescoco"));
 				 pac.setExa_torax(rs.getString("torax"));
 				 pac.setExa_urinario(rs.getString("genito_urinario"));
 				 pac.setObjectivo_geral(rs.getString("objectivo_geral"));
 				 pac.setSistema_nervoso(rs.getString("sistema_nervoso"));
				 lista.add(pac);
			 }
			 preparador.close();
		}
		catch(SQLException er){
			System.out.println(er);
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
	public List<Triagem> consultasHipot(int cod)
	{
		List<Triagem> lista = new ArrayList<Triagem>();
		//Triagem pac = null;
		String sql = "SELECT * FROM vwhipoteses where fk_paciente = ? group by (data_daconsulta) order by data_daconsulta desc";
		try
		{   
			con = Conexao.getConexao();
			 PreparedStatement preparador = con.prepareStatement(sql);
			 preparador.setInt(1, cod);
			 ResultSet rs = preparador.executeQuery();
			 while(rs.next())
			 {
				 Triagem pac  = new Triagem();
				 pac.setNome(rs.getString("nome"));
				 pac.setApelido(rs.getString("ultimo_nome")); 
 				 Calendar data = Calendar.getInstance();
 				 data.setTime(rs.getDate("data_daconsulta"));
 				 pac.setData(data);
 				 pac.setFK_paciente(rs.getInt("fk_paciente"));
				 lista.add(pac);
			 }
			 preparador.close();
		}
		catch(SQLException er){
			System.out.println(er);
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
	public List<Triagem> consultasHipots(int cod)
	{
		List<Triagem> lista = new ArrayList<Triagem>();
		//Triagem pac = null;
		String sql = "SELECT * FROM vwhipotesecids where fk_paciente = ? and data_daconsulta = curdate() order by data_daconsulta desc";
		try
		{   
			con = Conexao.getConexao();
			 PreparedStatement preparador = con.prepareStatement(sql);
			 preparador.setInt(1, cod);
			 ResultSet rs = preparador.executeQuery();
			 while(rs.next())
			 {
				 Triagem pac  = new Triagem();
				 pac.setDescricao(rs.getString("obs_hipotese"));
				 pac.setFk_cid(rs.getString("cid"));
				 pac.setDiagnostico_preliminar(rs.getString("descricao_cid"));
				 lista.add(pac);
			 }
			 preparador.close();
		}
		catch(SQLException er){
			System.out.println(er);
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
	public Triagem consultasHipot_views(int cod,java.sql.Date data)
	{
		Triagem pac = null;
		//Triagem pac = null;
		String sql = "SELECT * FROM vwhipotesecids where fk_paciente = ? and data_daconsulta = ?";
		try
		{  
			con = Conexao.getConexao();
			 PreparedStatement preparador = con.prepareStatement(sql);
			 preparador.setInt(1, cod);
			 preparador.setDate(2, data);
			 ResultSet rs = preparador.executeQuery();
			 
			 if(rs.next())
			 {
				 pac  = new Triagem();
				 pac.setDescricao(rs.getString("obs_hipotese"));
				 pac.setFk_cid(rs.getString("cid"));
				 pac.setDiagnostico_preliminar(rs.getString("descricao_cid"));
				 
			 }
			 preparador.close();
		}
		catch(SQLException er){
			System.out.println(er);
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
		return pac;
	}
	
	public List <Triagem> consultasHipot_viewss(int cod,java.sql.Date data)
	{
		List <Triagem> lista = new ArrayList<Triagem>();
		Triagem pac = null;
		//Triagem pac = null;
		String sql = "SELECT * FROM vwhipotesecids where fk_paciente = ? and data_daconsulta = ?";
		try
		{  
			con = Conexao.getConexao();
			 PreparedStatement preparador = con.prepareStatement(sql);
			 preparador.setInt(1, cod);
			 preparador.setDate(2, data);
			 ResultSet rs = preparador.executeQuery();
			 
			 while(rs.next())
			 {
				 pac  = new Triagem();
				 pac.setDescricao(rs.getString("obs_hipotese"));
				 pac.setFk_cid(rs.getString("cid"));
				 pac.setDiagnostico_preliminar(rs.getString("descricao_cid"));
				 lista.add(pac);
				 
			 }
			 preparador.close();
		}
		catch(SQLException er){
			System.out.println(er);
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
	
	public Triagem consultasDatasQH_views(int cod,java.sql.Date data)
	{
		Triagem pac = null;
		//Triagem pac = null;
		String sql = "SELECT * FROM vwinformtodasconsultaspacientes where fk_paciente = ? and data_daconsulta = ?";
		try
		{   
			con = Conexao.getConexao();
			 PreparedStatement preparador = con.prepareStatement(sql);
			 preparador.setInt(1, cod);
			 preparador.setDate(2, data);
			 ResultSet rs = preparador.executeQuery();
			 if(rs.next())
			 {
				 pac  = new Triagem();
				 pac.setQueixa(rs.getString("queixa_principal"));
				 pac.setHistorial(rs.getString("historico_doenca"));
				 
			 }
			 preparador.close();
		}
		catch(SQLException er){
			System.out.println(er);
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
		return pac;
	}
	public List <Triagem> VisualizarEstado()
	{
		List <Triagem> lista = new ArrayList<Triagem>();
		String sql = "SELECT * FROM TBLESTADODOPACIENTE";
		try{
			con = Conexao.getConexao();
			PreparedStatement preparador = con.prepareStatement(sql);
			ResultSet ag = preparador.executeQuery();
			while(ag.next())
			 {
			     Triagem fun = new Triagem();
			     fun.setFK_estado_do_paciente(ag.getInt("id_estado_do_paciente"));
			     fun.setNome_estado_do_paciente(ag.getString("estado_do_paciente"));
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
	
	public Triagem  edit_triagem(String ed)
	{
		Triagem tr = null;
		String sql = "SELECT * FROM vwpacientetriado_nd WHERE id_triagem = ?";
		try {
			con = Conexao.getConexao();
			 PreparedStatement preparador = con.prepareStatement(sql);
	         preparador.setString(1, ed);
			 ResultSet rs = preparador.executeQuery();
			 while(rs.next())
			 {
				 tr=new Triagem();
				 tr.setNome_estado_do_paciente(rs.getString("estado_do_paciente"));
				 tr.setFk_temperatura(rs.getInt("FK_temperatura"));
				 tr.setValor_tmp(rs.getString("temperatura"));
				 tr.setFk_pulso(rs.getInt("FK_pulso"));
				 tr.setValor_pulso(rs.getString("pulso"));
				 tr.setFk_respiracao(rs.getInt("FK_respiracao"));
				 tr.setValor_resp(rs.getString("respiracao"));
				 tr.setFK_tensao_sistolica(rs.getInt("FK_ta_sistolica"));
				 tr.setFK_tensao_diastolica(rs.getInt("FK_ta_diastolica"));
				 tr.setValor_tns(rs.getString("tensao_arterial"));
				 tr.setPeso(rs.getDouble("peso"));
				 tr.setDiagnostico_preliminar(rs.getString("diagnostico_preliminar"));
				 tr.setAltura_s(rs.getString("altura"));
				 tr.setImc(rs.getDouble("imc"));
				 tr.setFK_paciente(rs.getInt("fk_paciente"));
				 tr.setId_servico(rs.getInt("fk_servico"));
				 tr.setFK_doutor(rs.getInt("fk_doutor"));
			     tr.setFk_consulta_confirmada(rs.getInt("fk_consulta_confirmada"));				
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
		return tr;
	}
	
	public void alt_triagem(Triagem fun)
	{
		String sql = "UPDATE TBLTRIAGEM SET FK_estado_paciente =?, FK_temperatura=?, FK_pulso=?, FK_respiracao=?, FK_tensao_sistolica=?, FK_tensao_diastolica=?, peso=?, altura =?, " +
				"diagnostico_preliminar=?, hora_registo = ? WHERE id_triagem=?";
		try {
			con = Conexao.getConexao();
			PreparedStatement tr = con.prepareStatement(sql);
			tr.setInt(1, fun.getFK_estado_do_paciente());
			tr.setDouble(2, fun.getFk_temperatura());
			tr.setDouble(3, fun.getFk_pulso());
			tr.setDouble(4, fun.getFk_respiracao());
			tr.setInt(5, fun.getFK_tensao_sistolica());
			tr.setInt(6, fun.getFK_tensao_diastolica());
			tr.setDouble(7, fun.getPeso());
			tr.setDouble(8, fun.getAltura());
			tr.setString(9, fun.getDiagnostico_preliminar());
			tr.setString(10, new Formatando().horaAtual());
			tr.setInt(11, fun.getId_triagem());
			tr.execute();
			tr.close();
			System.out.println("Alteracao com sucesso...TBLAGENDA"); 
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
	
	public void salvar(Triagem trg)
	{
		if(trg.getId_triagem()!=0)
		{
			alt_triagem(trg);
		}
		else
		{
			InserirDadosPaciente(trg);
		}
	}
	
	public boolean getConsulta(int cod)
	{
		Triagem fun = new Triagem();
		String sql = "SELECT * FROM vwpacientetriado  where fk_paciente like ?";
		try {
			con = Conexao.getConexao(); 
			PreparedStatement preparador = con.prepareStatement(sql);
			 ResultSet rs = preparador.executeQuery();
			 if(rs.next())
			 {
			     fun.setFK_paciente(rs.getInt("fk_paciente"));
				 fun.setId_servico(rs.getInt("id_servico"));
			     fun.setFk_consulta_confirmada(rs.getInt("fk_consulta_confirmada"));
			 }
			 sql = "insert into tblconsulta (data_daconsulta,hora_daconsulta,fk_medico,fk_paciente,fk_servico)";
			 preparador = con.prepareStatement(sql);
			 preparador.setTime(1, null);
			 preparador.setTime(2, null);
			 preparador.setInt(3,0);
			 preparador.setInt(4, fun.getFK_paciente());
			 preparador.setInt(5, fun.getId_servico());
			 
			 boolean passou = preparador.execute();
			 	 
			 preparador.close();
			 return passou;
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
		return false;
	  }
	
	public List <Triagem> PacConfirmado ()
	{
		List <Triagem> lista = new ArrayList<Triagem>();
		String sql = "SELECT * FROM vwpacientetriado_nd where data  = curdate() order by hora_registo asc  ";
		try {
			con = Conexao.getConexao();
			 PreparedStatement preparador = con.prepareStatement(sql);
			 ResultSet rs = preparador.executeQuery();
			 while(rs.next())
			 {
				 Triagem fun = new Triagem();
				 fun.setNumero_processo(rs.getString("numeroProcesso"));
				 fun.setId_triagem(rs.getInt("id_triagem"));
			     fun.setNome(rs.getString("nome"));
			     fun.setNomem(rs.getString("nome_meio"));
			     fun.setApelido(rs.getString("ultimo_nome"));
			     fun.setServico(rs.getString("descricao"));
			     fun.setFK_paciente(rs.getInt("fk_paciente"));
				 fun.setId_servico(rs.getInt("fk_servico"));
				 fun.setFK_doutor(rs.getInt("FK_doutor"));
				 fun.setNome_doutor(rs.getString("nomecompleto"));
			     fun.setFk_consulta_confirmada(rs.getInt("fk_consulta_confirmada"));
			     Calendar data = Calendar.getInstance();
			     data.setTime(rs.getDate("data"));
			     fun.setData(data);
			     fun.setHoraConsulta(rs.getString("hora_registo"));
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
	
	public  List<Triagem> buscaGruposAnalise ()
 {
		List<Triagem> lista = new ArrayList<Triagem>();
		String sql = "SELECT * FROM tblgrupodeanalises";
		try {
			con = Conexao.getConexao();
			PreparedStatement preparador = con.prepareStatement(sql);
			ResultSet rs = preparador.executeQuery();
			while (rs.next()) {
				Triagem t = new Triagem();
				t.setId_grupoanalise(rs.getInt("id_grupo_analises_clinicas"));
				t.setGrupoanalise(rs.getString("grupo_danalise"));
				lista.add(t);
			}
			preparador.close();
		} catch (SQLException e) {

			e.printStackTrace();
		} catch (Exception e) {
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
	
	public  List<Triagem> buscaAnalises (int fkgrupo)
	   {
		List<Triagem> lista = new ArrayList<Triagem>();
		String sql = "SELECT * FROM vwexameslaboratoriais where id_grupo_analises_clinicas = ?"; 
		try {
			con = Conexao.getConexao();
			PreparedStatement preparador = con.prepareStatement(sql);
			preparador.setInt(1, fkgrupo);
			ResultSet rs = preparador.executeQuery();
			while (rs.next()) {
				Triagem t = new Triagem();
				t.setId_analise(rs.getInt("id_servicodeanalise_clinica"));
				t.setAnalise(rs.getString("analise_clinica"));
				t.setGrupoanalise(rs.getString("grupo_danalise"));
				t.setDisponibilidade(rs.getInt("disponibilidade"));
				lista.add(t);
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
	
	public  Triagem buscaAnalisesPorId (int id_analise)
	   {
		Triagem t = null;
	    String sql = "SELECT * FROM vwexameslaboratoriais where id_servicodeanalise_clinica = ?";
	    try {
	    	con = Conexao.getConexao();
	     PreparedStatement preparador = con.prepareStatement(sql);
	     preparador.setInt(1, id_analise);
	     ResultSet rs = preparador.executeQuery();
	      if(rs.next())
	      {
	    	  t = new Triagem();
	    	  t.setId_analise(rs.getInt("id_servicodeanalise_clinica"));
	    	  t.setAnalise(rs.getString("analise_clinica"));
	    	  t.setGrupoanalise(rs.getString("grupo_danalise")); 
	    	  t.setPreco(rs.getDouble("preco"));  
	      }
	      preparador.close();
	    } catch (SQLException e) {
	    	System.out.println(e.getMessage());
	    }
	    catch (Exception e) {
	    	System.out.println(e.getMessage());
		}
		finally{
			try{
				con.close();
			}
			catch(SQLException ef){
				System.out.println("Erro finalizar: "+ef);
			}
		}
	    return t;
	   }
	
	public  List<Triagem> buscaAnalises ()
	   {
	   List<Triagem> lista = new ArrayList<Triagem>();
	    String sql = "SELECT * FROM vwexameslaboratoriais group by (grupo_danalise)";
	    try {
	    	con = Conexao.getConexao();
	    	PreparedStatement preparador = con.prepareStatement(sql);
	    	ResultSet rs = preparador.executeQuery();
	    	while(rs.next())
	    	{
	    	  Triagem t = new Triagem();
	    	  t.setId_analise(rs.getInt("id_servicodeanalise_clinica"));
	    	  t.setAnalise(rs.getString("analise_clinica"));
	    	  t.setGrupoanalise(rs.getString("grupo_danalise"));
	    	  t.setDisponibilidade(rs.getInt("disponibilidade"));
	    	  lista.add(t);
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
	
	//INSERIR DATA AUTOMM�TICA
	 public java.sql.Date data_registo()
	 {
		 java.sql.Date dt_registo = null;
			Calendar c = Calendar.getInstance(); // Cria um objeto para armazenar a data atual
	        c.setTime(new java.util.Date()); // pega a data atual do sistema
	        int diaAtual = c.get(Calendar.DAY_OF_MONTH); // DIA
	        int mesAtual = c.get(Calendar.MONTH)+1; // MES
	        int anoAtual = c.get(Calendar.YEAR);  // ANO
	        String hoje = anoAtual+"/"+mesAtual+"/"+diaAtual;
	        DateFormat formate = new SimpleDateFormat("yyyy/MM/dd");
		    java.util.Date a; 
			try {
				a = (java.util.Date) formate.parse(hoje);
				dt_registo = new java.sql.Date(a.getTime());
				} catch (ParseException e) { }
			return dt_registo;
	 }
	
}
