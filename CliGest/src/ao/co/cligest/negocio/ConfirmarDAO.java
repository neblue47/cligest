package ao.co.cligest.negocio;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

import ao.co.cligest.entidades.Paciente;
import ao.co.cligest.util.MetodosBuscas;

public class ConfirmarDAO {
	
	private Connection con  ;
	
	 public List<Paciente> buscarmarcacaotabela(){
			List <Paciente> lista = new ArrayList<Paciente>();
			String sql = "SELECT * FROM vwmarcadasemconfirmar where    id_consulta_marcada  limit 10";
			try {
				con = Conexao.getConexao();
				PreparedStatement preparador = con.prepareStatement(sql);
				 ResultSet rs = preparador.executeQuery();
				 while(rs.next())
				 {
					 Paciente fun = new Paciente();
					 fun.setFK_entidade(rs.getInt("FK_paciente"));
					 fun.setNumero_processo(rs.getString("NumeroProcesso"));
					 fun.setNome_paciente(rs.getString("nomecompleto"));
					 fun.setNome_doutor(rs.getString("nomecompleto1")); 
					 fun.setServico(rs.getString("servico"));
					 fun.setFK_consulta_marcada(rs.getInt("id_consulta_marcada"));
//					 fun.setNumero_da_consulta(rs.getString("numero_da_consulta"));
					 fun.setFK_fornecedor(rs.getInt("fk_convenio"));
					 Calendar data = Calendar.getInstance();
					 data.setTime(rs.getDate("data_do_agendamento"));
					 fun.setData_confirmacao(data);
					 fun.setHora(rs.getTime("hora_da_marcacao"));
					 lista.add(fun);
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
	
	
 /// pesquisa por nome
    public List<Paciente> marcacaopesquisarnome (String num)
    {
 	   List <Paciente> lista = new ArrayList<Paciente>();
     String sql = "SELECT * FROM VWCONSULTAMARCADA WHERE nome LIKE ?";
     try {
    	 con = Conexao.getConexao();
       PreparedStatement preparador = con.prepareStatement(sql);
       preparador.setString(1, num);
       ResultSet rs = preparador.executeQuery();
       while(rs.next())
       {
    	   Paciente pac = new Paciente();
    	   pac.setFK_entidade(rs.getInt("FK_entidade"));
     	  pac.setNumero_processo(rs.getString("numero_processo"));
     	  pac.setNome(rs.getString("nome"));
     	  pac.setNomem(rs.getString("nome_meio"));
     	  pac.setApelido(rs.getString("ultimo_nome"));
     	// pac.setFK_doutor(rs.getInt("FK_doutor"));
     	 pac.setNum_fun(rs.getString("numero_funcionario"));
	     pac.setServico(rs.getString("servico"));
	     pac.setDia_da_semana(rs.getString("dia_da_semana"));
	     Calendar data = Calendar.getInstance();
	      data.setTime(rs.getDate("data_nascimento"));
	      pac.setDataNasc(data);
	     pac.setNomeBairro(rs.getString("bairro"));
     	  lista.add(pac);
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
    // System.out.print(lista.get(0).getNome());
     return lista;
    }
    
    public List<Paciente> buscarmarcacaopornome (String num)
    {
 	   List <Paciente> lista = new ArrayList<Paciente>();
     String sql = "SELECT * FROM VWCONSULTAMARCADA WHERE FK_entidade LIKE ?";
     try {
    	 con = Conexao.getConexao();
       PreparedStatement preparador = con.prepareStatement(sql);
       preparador.setString(1, num);
       ResultSet rs = preparador.executeQuery();
       while(rs.next())
       {
    	   Paciente pac = new Paciente();
    	   pac.setFK_entidade(rs.getInt("FK_entidade"));
     	  pac.setNumero_processo(rs.getString("numero_processo"));
 	     pac.setNome(rs.getString("nome")+" "+rs.getString("nome_meio")+" "+rs.getString("ultimo_nome"));
     	// pac.setFK_doutor(rs.getInt("FK_doutor"));
	     pac.setServico(rs.getString("servico"));
	     pac.setNum_fun(rs.getString("numero_funcionario"));
     	  lista.add(pac);
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
    // System.out.print(lista.get(0).getNome());
     return lista; 
    }
    
    
    
    public Paciente buscarpacientePaciente (int num)
	  
	  {
    	Paciente pac = null;
	   String sql = "SELECT * FROM vwconsultamarcadas where FK_paciente LIKE ? and data_do_agendamento = current_date()";
	   try {
		   con = Conexao.getConexao();
	    PreparedStatement preparador = con.prepareStatement(sql);
	     preparador.setInt(1, num);
	     ResultSet rs = preparador.executeQuery();
	     if(rs.next())
	     {
	     pac = new Paciente();
	     pac.setFK_entidade(rs.getInt("FK_paciente"));
	     pac.setFK_consulta_marcada(rs.getInt("id_consulta_marcada"));
	     pac.setEspecialidade(rs.getString("especialidade"));
	     pac.setServico(rs.getString("servico"));
	     pac.setNome_paciente(rs.getString("nomecompleto"));
	     pac.setFK_doutor(rs.getInt("fK_doutor"));
	     pac.setNumero_da_consulta(rs.getString("numero_da_consulta"));
	     pac.setNome_doutor(rs.getString("NomeCompleto1")); 
	     pac.setFK_consulta_marcada(rs.getInt("id_consulta_marcada"));
	     pac.setDia_da_semana(rs.getString("dia_da_semana"));
	     pac.setGestante(rs.getInt("gestante_pra_consulta"));
	     Calendar data = Calendar.getInstance();
	      data.setTime(rs.getDate("data_nascimento"));
	      pac.setDataNasc(data);
	      pac.setIdade(rs.getDate("data_nascimento"));
	     pac.setNomeBairro(rs.getString("bairro"));
	     pac.setData_agendamento(rs.getDate("data_do_agendamento"));
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
	 // System.out.print("Nome: "+pac.getNome());
	   return pac;
	  }
    
    public  int buscarServico (String num)
	  {
    	int fk_servico=0;
	   String sql = "SELECT * FROM tblservico where servico LIKE ?";
	   try {
		   con = Conexao.getConexao();
	    PreparedStatement preparador = con.prepareStatement(sql);
	     preparador.setString(1, num);
	     ResultSet rs = preparador.executeQuery();
	     if(rs.next())
	     {
	     
	     fk_servico= rs.getInt("id_servico");
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
 
	   return fk_servico;
	  }
	
    public  int buscarBairro (String num)
	  {
  	int fk_bairro=0;
	   String sql = "SELECT * FROM tblbairro where bairro LIKE ?";
	   try {
		   con = Conexao.getConexao();
	    PreparedStatement preparador = con.prepareStatement(sql);
	     preparador.setString(1, num);
	     ResultSet rs = preparador.executeQuery();
	     if(rs.next())
	     {
	     
	    	 fk_bairro= rs.getInt("id_bairro");
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
	   return fk_bairro;
	  }
    
    public  int buscarespecialidade (String num)
	  {
	int fk_especialidade=0;
	   String sql = "SELECT * FROM tblespecialidade where especialidade LIKE ?";
	   try {
		   con = Conexao.getConexao();
	    PreparedStatement preparador = con.prepareStatement(sql);
	     preparador.setString(1, num);
	     ResultSet rs = preparador.executeQuery();
	     if(rs.next())
	     {
	    fk_especialidade= rs.getInt("id_especialidade");
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
	   return fk_especialidade;
	  }
    
    
    public  int buscardiasemana (String num)
	  {
	int fk_diasemana=0;
	   String sql = "SELECT * FROM tbldiadasemana where dia_da_semana LIKE ?";
	   try {
		   con = Conexao.getConexao();
	    PreparedStatement preparador = con.prepareStatement(sql);
	     preparador.setString(1, num);
	     ResultSet rs = preparador.executeQuery();
	     if(rs.next())
	     {
	     
	    	 fk_diasemana= rs.getInt("id_dia_da_semana");
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
	   return fk_diasemana;
	  }
public int inserirconfirmacao(Paciente p){
	int ultimoId = -1;
	    String sql = "insert into tblconsultaconfirmada (fk_consulta_marcada,fk_servico,data_confirmacao,fk_funcionario,fk_entidade_paciente,FK_dia_dasemana,fk_bairro,hora_daconfirmacao,idade_dopaciente,fk_doutor,gestante, FK_fornecedor, FK_cont_forn) values (?,?,?,?,?,?,?,?,?,?,?,?,?)";
	    try {
			 //1-TBLCONSULTACONFIRMADA
	    	  con = Conexao.getConexao();
			 PreparedStatement cid = con.prepareStatement(sql); 
			 cid.setInt(1, p.getFK_consulta_marcada());
			 cid.setInt(2, p.getFK_servico());
			 cid.setDate(3,new Formatando().data_registo());
			 cid.setInt(4, p.getFK_funcionario());
			 cid.setInt(5, p.getFK_paciente());
			 cid.setInt(6, p.getFK_dia_dasemana());
			 cid.setInt(7, p.getFK_bairro());
			 cid.setString(8, new Formatando().getHoraAtual());
			 cid.setInt(9, p.getIdade_dopaciente());
			 cid.setInt(10, p.getFK_doutor());
			 cid.setInt(11, p.getGestante());
			 cid.setInt(12, p.getId_convenio());
			 cid.setInt(13, p.getId());
			 cid.execute();
			 ResultSet rs = cid.executeQuery("SELECT LAST_INSERT_ID()");
			 if(rs.next())
	    	        ultimoId = rs.getInt(1);
			 cid.close();
			System.out.println("Cadastro de sucesso...tblconsultaconfirmada"); 
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
	    return ultimoId;
	}



public List<Paciente> buscarpacientepesquisar (String num)
{
		List<Paciente> lista = new ArrayList<Paciente>();
		String sql = "SELECT * FROM VWCONSULTAMARCADA where numero_processo LIKE ? OR nome LIKE ? OR servico LIKE ? OR data_do_agendamento LIKE ? ";
		try {
			con = Conexao.getConexao();
			PreparedStatement preparador = con.prepareStatement(sql);
			preparador.setString(1, num);
			preparador.setString(2, num);
			preparador.setString(3, num);
			preparador.setString(4, num);
			ResultSet rs = preparador.executeQuery();
			while (rs.next()) {
				Paciente pac = new Paciente();
				pac.setFK_entidade(rs.getInt("FK_entidade"));
				pac.setNumero_processo(rs.getString("numero_processo"));
				pac.setNome(rs.getString("nome"));
				pac.setNomem(rs.getString("nome_meio"));
				pac.setApelido(rs.getString("ultimo_nome"));
				// pac.setFK_doutor(rs.getInt("FK_doutor"));
				pac.setNum_fun(rs.getString("numero_funcionario"));
				pac.setServico(rs.getString("servico"));
				pac.setDia_da_semana(rs.getString("dia_da_semana"));
				Calendar nascido = Calendar.getInstance();
				nascido.setTime(rs.getDate("data_nascimento"));
				pac.setDataNasc(nascido);
				pac.setNomeBairro(rs.getString("bairro"));
				Calendar data = Calendar.getInstance();
				data.setTime(rs.getDate("data_do_agendamento"));
				pac.setData_do_agendamento(data);
				lista.add(pac);
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
// System.out.print(lista.get(0).getNome());
 return lista;
}

public List<Paciente> tabelaconsultaporfazer(){
	List <Paciente> lista = new ArrayList<Paciente>();
	String sql = "SELECT * FROM vwmarcadasemconfirmar order by data_do_agendamento desc limit 50";
	
	try {
		con = Conexao.getConexao();
		PreparedStatement preparador = con.prepareStatement(sql);
		
		 ResultSet rs = preparador.executeQuery();
		 while(rs.next())
		 {
			 Paciente fun = new Paciente();
			 fun.setFK_entidade(rs.getInt("FK_paciente"));
			 fun.setNumero_processo(rs.getString("numeroprocesso"));
			 fun.setNome(rs.getString("nomecompleto"));
			 fun.setServico(rs.getString("servico"));
			 fun.setNome_doutor(rs.getString("nome_d")+" "+rs.getString("ultimo_nome_d"));
			 Calendar data = Calendar.getInstance();
			 data.setTime(rs.getDate("data_do_agendamento"));
			 fun.setData_do_agendamento(data);
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

public List<Paciente> tabelaconsultaporfazerPsq(String aux){
	List <Paciente> lista = new ArrayList<Paciente>();
	String sql = "SELECT * FROM vwmarcadasemconfirmar where numeroprocesso = ? or nomecompleto Like ? order by data_do_agendamento desc";
	
	try {
		con = Conexao.getConexao();
		PreparedStatement preparador = con.prepareStatement(sql);
		preparador.setString(1, aux);
		preparador.setString(2, aux+"%");
		
		 ResultSet rs = preparador.executeQuery();
		 while(rs.next())
		 {
			 Paciente fun = new Paciente();
			 fun.setFK_entidade(rs.getInt("FK_paciente"));
			 fun.setNumero_processo(rs.getString("numeroprocesso"));
			 fun.setNome(rs.getString("nomecompleto"));
			 fun.setServico(rs.getString("servico"));
			 fun.setNome_doutor(rs.getString("nome_d")+" "+rs.getString("ultimo_nome_d"));
			 Calendar data = Calendar.getInstance();
			 data.setTime(rs.getDate("data_do_agendamento"));
			 fun.setData_do_agendamento(data);
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
public List<Paciente> tabelaconsultaporfazeres(){
	List <Paciente> lista = new ArrayList<Paciente>();
	String sql = "SELECT * FROM vwmarcadasemconfirmar ";
	
	try {
		con = Conexao.getConexao();
		PreparedStatement preparador = con.prepareStatement(sql);
		
		 ResultSet rs = preparador.executeQuery();
		 while(rs.next())
		 {
			 Paciente fun = new Paciente();
			 fun.setFK_entidade(rs.getInt("FK_paciente"));
			 fun.setNumero_processo(rs.getString("numeroprocesso"));
			 fun.setNome(rs.getString("nomecompleto"));
			 fun.setIdade_dopaciente(rs.getInt("idade"));
			 fun.setTelefone(rs.getLong("telefone"));
			 fun.setServico(rs.getString("servico"));
			 fun.setNome_doutor(rs.getString("nome_d")+" "+rs.getString("ultimo_nome_d"));
			 Calendar data = Calendar.getInstance();
			 data.setTime(rs.getDate("data_do_agendamento"));
			 fun.setData_do_agendamento(data);
			 fun.setHora(rs.getTime("hora_da_marcacao"));
			 lista.add(fun);
		 }
		 preparador.close();
	} catch (SQLException e) {
		// TODO Auto-generated catch block
		throw new RuntimeException(e);
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
public List<Paciente> tabelaconsultaporfazeres(int doutor, Date data){
	List <Paciente> lista = new ArrayList<Paciente>();
	String sql = "SELECT * FROM vwmarcadasemconfirmar where data_do_agendamento = ? and fk_doutor = ?";
	
	try {
		con = Conexao.getConexao();
		PreparedStatement preparador = con.prepareStatement(sql);
		preparador.setDate(1, data);
		 preparador.setInt(2, doutor);
		 ResultSet rs = preparador.executeQuery();
		 while(rs.next())
		 {
			 Paciente fun = new Paciente();
			 fun.setFK_entidade(rs.getInt("FK_paciente"));
			 fun.setNumero_processo(rs.getString("numeroprocesso"));
			 fun.setNome(rs.getString("nomecompleto"));
			 fun.setIdade_dopaciente(rs.getInt("idade"));
			 fun.setTelefone(rs.getLong("telefone"));
			 fun.setServico(rs.getString("servico"));
			 fun.setNome_doutor(rs.getString("nome_d")+" "+rs.getString("ultimo_nome_d"));
			 Calendar data1 = Calendar.getInstance();
			 data1.setTime(rs.getDate("data_do_agendamento"));
			 fun.setData_do_agendamento(data1);
			 fun.setHora(rs.getTime("hora_da_marcacao"));
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

public List<Paciente> tabelaconsultaporfazeres(Date data){
	List <Paciente> lista = new ArrayList<Paciente>();
	String sql = "SELECT * FROM vwmarcadasemconfirmar where data_do_agendamento = ? ";
	
	try {
		con = Conexao.getConexao();
		PreparedStatement preparador = con.prepareStatement(sql);
		preparador.setDate(1, data);
		 
		 ResultSet rs = preparador.executeQuery();
		 while(rs.next())
		 {
			 Paciente fun = new Paciente();
			 fun.setFK_entidade(rs.getInt("FK_paciente"));
			 fun.setNumero_processo(rs.getString("numeroprocesso"));
			 fun.setNome(rs.getString("nomecompleto"));
			 fun.setIdade_dopaciente(rs.getInt("idade"));
			 fun.setTelefone(rs.getLong("telefone"));
			 fun.setServico(rs.getString("servico"));
			 fun.setNome_doutor(rs.getString("nome_d")+" "+rs.getString("ultimo_nome_d"));
			 Calendar data1 = Calendar.getInstance();
			 data1.setTime(rs.getDate("data_do_agendamento"));
			 fun.setData_do_agendamento(data1);
			 fun.setHora(rs.getTime("hora_da_marcacao"));
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
public List<Paciente> tabelaconsultaporfazer(String valor){
	List <Paciente> lista = new ArrayList<Paciente>();
	String sql = "SELECT * FROM VWCONSULTAMARCADA WHERE data_do_agendamento >= 'Getdate' and (numero_processo LIKE '"+valor+"' OR nome LIKE '"+valor+"' OR servico LIKE '"+valor+"' OR data_do_agendamento LIKE '"+valor+"')";
	
	try {
		con = Conexao.getConexao();
		PreparedStatement pr = con.prepareStatement(sql);
		 ResultSet rs = pr.executeQuery();
		 while(rs.next())
		 {
			 Paciente fun = new Paciente();
			 fun.setFK_entidade(rs.getInt("FK_entidade"));
			 fun.setNumero_processo(rs.getString("numero_processo"));
			 fun.setNome(rs.getString("nome"));
			 fun.setNomem(rs.getString("nome_meio"));
			 fun.setApelido(rs.getString("ultimo_nome"));
			// fun.setFK_doutor(rs.getInt("FK_doutor"));
			fun.setServico(rs.getString("servico"));
			fun.setNum_fun(rs.getString("numero_funcionario"));
			Calendar data = Calendar.getInstance();
			data.setTime(rs.getDate("data_do_agendamento"));
			fun.setData_do_agendamento(data);
			 lista.add(fun);
		 }
		 pr.close();
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
	lista.get(0).getData_agendamento();
	return lista;
}

public List<Paciente> tabelaconsultafeita(){
	List <Paciente> lista = new ArrayList<Paciente>();
	String sql = "SELECT * FROM vwconsultaconfirmadacomdoutor order by data_confirmacao desc limit 50";
	try {
		con = Conexao.getConexao();
		PreparedStatement preparador = con.prepareStatement(sql);
		
		 ResultSet rs = preparador.executeQuery();
		 
		 while(rs.next())
		 {
			 Paciente fun = new Paciente();
			 //fun.setFK_entidade(rs.getInt("FK_entidade"));
			 fun.setNumero_processo(rs.getString("numero_processo"));
			 fun.setNome(rs.getString("nome"));
			 fun.setNomem(rs.getString("nome_meio"));
			 fun.setApelido(rs.getString("ultimo_nome"));
			// fun.setFK_doutor(rs.getInt("FK_doutor"));
			 fun.setServico(rs.getString("servico"));
			 fun.setNome_doutor(rs.getString("nome_d")+" "+rs.getString("ultimo_nome_d"));
			 Calendar data = Calendar.getInstance();
			 data.setTime(rs.getDate("data_confirmacao"));
			
			 fun.setData_confirmacao(data);
			 lista.add(fun);
		 }
		 preparador.close();
		 con.close();
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

public List<Paciente> tabelaconsultafeitaPsq(String aux){
	List <Paciente> lista = new ArrayList<Paciente>();
	String sql = "SELECT * FROM vwconsultaconfirmadacomdoutor where  numero_processo = ? or nome Like ? order by data_confirmacao desc limit 50";
	try {
		con = Conexao.getConexao();
		PreparedStatement preparador = con.prepareStatement(sql);
		preparador.setString(1, aux);
		preparador.setString(2, aux+"%");
		 ResultSet rs = preparador.executeQuery();
		 
		 while(rs.next())
		 {
			 Paciente fun = new Paciente();
			 //fun.setFK_entidade(rs.getInt("FK_entidade"));
			 fun.setNumero_processo(rs.getString("numero_processo"));
			 fun.setNome(rs.getString("nome"));
			 fun.setNomem(rs.getString("nome_meio"));
			 fun.setApelido(rs.getString("ultimo_nome"));
			// fun.setFK_doutor(rs.getInt("FK_doutor"));
			 fun.setServico(rs.getString("servico"));
			 fun.setNome_doutor(rs.getString("nome_d")+" "+rs.getString("ultimo_nome_d"));
			 Calendar data = Calendar.getInstance();
			 data.setTime(rs.getDate("data_confirmacao"));
			
			 fun.setData_confirmacao(data);
			 lista.add(fun);
		 }
		 preparador.close();
		 con.close();
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
 public List<Paciente> tabelaconsultafeita(String valor){
	List <Paciente> lista = new ArrayList<Paciente>();
	String sql = "SELECT * FROM VWCONSULTAMARCADA WHERE data_do_agendamento < 'Getdate' and (numero_processo LIKE '"+valor+"' OR nome LIKE '"+valor+"' OR servico LIKE '"+valor+"' OR data_do_agendamento LIKE '"+valor+"')";
	try {
		con = Conexao.getConexao();
		 PreparedStatement pr = con.prepareStatement(sql);
		 ResultSet rs = pr.executeQuery();
		 while(rs.next())
		 {
			 Paciente fun = new Paciente();
			 fun.setFK_entidade(rs.getInt("FK_entidade"));
			 fun.setNumero_processo(rs.getString("numero_processo"));
			 fun.setNome(rs.getString("nome"));
			 fun.setNomem(rs.getString("nome_meio"));
			 fun.setApelido(rs.getString("ultimo_nome"));
			// fun.setFK_doutor(rs.getInt("FK_doutor"));
			 fun.setServico(rs.getString("servico"));
			 fun.setNum_fun(rs.getString("numero_funcionario"));
		     Calendar data = Calendar.getInstance();
		     data.setTime(rs.getDate("data_do_agendamento"));
		     fun.setData_do_agendamento(data);
			 lista.add(fun);
		 }
		 pr.close();
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

 
 	public void ConcelarConsulta(String cod_cs, String cod_ms){
 		String sql = "Delete from tblconsultaconfirmada where id_consulta_confirmada = ?";
 		int aux1 = Integer.parseInt(cod_cs);
 		int aux2 = Integer.parseInt(cod_ms);
 		try {
			con = Conexao.getConexao();
			PreparedStatement ps = con.prepareStatement(sql);
			ps.setInt(1, aux1);
			ps.execute();
			System.out.println("Confirmacao da Consulta Desfeita com sucesso!");
			// Excluindo a consulta
			sql = "Delete from tblconsultamarcada where id_consulta_marcada = ?";
			ps = con.prepareStatement(sql);
			ps.setInt(1, aux2);
			ps.execute();
			System.out.println("Agendamento da Consulta Excluida com sucesso!");
			ps.close();
			con.close();			
		} catch (Exception e) {
			e.printStackTrace();
		}
 	}
}
