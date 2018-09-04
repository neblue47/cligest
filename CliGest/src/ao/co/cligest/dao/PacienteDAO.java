package ao.co.cligest.dao;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

import ao.co.cligest.entidades.Arquivos;
import ao.co.cligest.entidades.InforPaciente;
import ao.co.cligest.entidades.Paciente;
import ao.co.cligest.entidades.PacientePlano;

public class PacienteDAO {
	 
	private Connection con;
   Formatando ft = new  Formatando();
	//INSERIR DATA
	public Paciente novo()
	{
		Paciente f = new Paciente();
		int fun = 0;
		String sql = "SELECT IFNULL(max(id_paciente),0) num FROM tblpaciente";
		try {
			con = Conexao.getConexao();
			PreparedStatement preparador = con.prepareStatement(sql);
			ResultSet rs = preparador.executeQuery();
			if(rs.last())
				fun = rs.getInt("num");
			    fun++;
			
			f.setNumero_processo("PROC0"+fun);
		}
		catch (Exception e) {
			
		}
	    finally{
					try{
						con.close();
					}
					catch(SQLException ef){
						System.out.println("Erro finalizar: "+ef);
					}
			}
			return f;
	}
	
	public String  numProcesso()
	{
		Paciente f = new Paciente();
		int fun = 0;
		String sql = "SELECT IFNULL(max(id_paciente),0) num FROM tblpaciente";
		try {
			con = Conexao.getConexao();
			PreparedStatement preparador = con.prepareStatement(sql);
			ResultSet rs = preparador.executeQuery();
			if(rs.last())
				fun = rs.getInt("num");
			    fun++;
			
			f.setNumero_processo("PROC0"+fun);
			preparador.close();
			con.close();
		}
		 
		catch (Exception e) {
			
		}
	 
			return f.getNumero_processo();
	}
	
	public int fk_paciente(String pac)
	{
		int fkp = 0;
		String sql = "SELECT fk_entidade FROM TBLPACIENTE where numero_processo Like ?";
		try {
			con = Conexao.getConexao();
			PreparedStatement preparador = con.prepareStatement(sql);
			preparador.setString(1, pac);
			ResultSet rs = preparador.executeQuery();
			
			if(rs.next())
				fkp = (rs.getInt("fk_entidade"));
		}
		catch (Exception e) {
			
		}
	    finally{
					try{
						con.close();
					}
					catch(SQLException ef){
						System.out.println("Erro finalizar: "+ef);
					}
			}
			return fkp;
	}
	public String foto(int id)
	{
		String sql = "SELECT NOME_FICHEIRO FROM TBLARQUIVODOFUNCIONARIO WHERE fk_entidade=? AND fk_arquivo=?";
		String ficheiro="usuarios.jpg";
		try {
			con = Conexao.getConexao();
			PreparedStatement ent = con.prepareStatement(sql);
			ent.setInt(1,id);
			ent.setInt(2, 1);
			ResultSet rs = ent.executeQuery();
			if(rs.next())
				ficheiro = rs.getString("nome_ficheiro");
			ent.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		catch (Exception e) {
			
		}
	    finally{
					try{
						con.close();
					}
					catch(SQLException ef){
						System.out.println("Erro finalizar: "+ef);
					}
			}
		return ficheiro;
	}
	
	public String getFotoPefil(String numP)
	{
		String sql = "select nome_ficheiro from vwrelperfildopaciente where NumeroProcesso = ?";
		String ficheiro="usuarios.jpg";
		try {
			con = Conexao.getConexao();
			PreparedStatement ent = con.prepareStatement(sql);
			ent.setString(1,numP);
			ResultSet rs = ent.executeQuery();
			if(rs.next()){
				if(rs.getString("nome_ficheiro")!=null)
					ficheiro = rs.getString("nome_ficheiro");
			}
				 	 
			
			ent.close();
			con.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return ficheiro;
	}
	
	public void  eliminarFoto(int id)
	{
		String sql = "delete from TBLARQUIVODOFUNCIONARIO where fk_entidade = ? ";
		 
		try {
			con = Conexao.getConexao();
			PreparedStatement ent = con.prepareStatement(sql);
			ent.setInt(1,id);
			ent.execute();
			 
			ent.close();
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
	}
	
	public List<Paciente> buscarOstriados()
	{
		List <Paciente> lista = new ArrayList<Paciente>();
		String sql = "SELECT * FROM vwpacientetriado where data = current_date() ";
		try
		{
			con = Conexao.getConexao();
			 PreparedStatement preparador = con.prepareStatement(sql);
			 ResultSet rs = preparador.executeQuery();
			 while(rs.next())
			 {
				 Paciente pac = new Paciente();
				 pac.setId(rs.getInt("fk_entidade"));
				 pac.setNumero_processo(rs.getString("numero_processo"));
				 pac.setNome(rs.getString("nome"));
				 pac.setNomem(rs.getString("nome_meio"));
				 pac.setApelido(rs.getString("ultimo_nome"));
				 lista.add(pac);
		
			 }
			 preparador.close();
		}
		
		catch(SQLException er){
			
		}
		catch (Exception e) {
			
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
	public List<Paciente> buscarOstriados(int doutor)
	{
		List <Paciente> lista = new ArrayList<Paciente>();
		String sql = "select * from vwpacientetriado_nd where (fk_doutor = ? or 1 = ?) and data  = current_date() "
				   + "and fk_consulta_confirmada not in (select fk_consulta_confirmada from tblconsultafinalizada)";
		try
		{
			con = Conexao.getConexao();
			 PreparedStatement preparador = con.prepareStatement(sql);
			 preparador.setInt(1, doutor);
			 preparador.setInt(2, doutor);
			 ResultSet rs = preparador.executeQuery();
			 while(rs.next())
			 {
				 Paciente pac = new Paciente();
				 pac.setId(rs.getInt("fk_paciente"));
				 pac.setFK_consulta_confirmada(rs.getInt("fk_consulta_confirmada"));
				 pac.setNumero_processo(rs.getString("numeroprocesso"));
				 pac.setNome(rs.getString("nome"));
				 pac.setNomem(rs.getString("nome_meio"));
				 pac.setApelido(rs.getString("ultimo_nome"));
				 lista.add(pac);
			 }
			 preparador.close();
		}
		
		catch(SQLException er){
			System.out.println(er);
		}
		catch (Exception e) {
			
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
	
	public List<Paciente> buscarOstriados(String txtp)
	{
		List <Paciente> lista = new ArrayList<Paciente>();
		String sql = "SELECT * FROM vwpacientetriado where data = current_date() and nome like ? or ultimo_nome like ?";
		try
		{
			con = Conexao.getConexao();
			 PreparedStatement preparador = con.prepareStatement(sql);
			 preparador.setString(1, txtp+"%");
			 preparador.setString(2, txtp+"%");
			 ResultSet rs = preparador.executeQuery();
			 while(rs.next())
			 {
				 Paciente pac = new Paciente();
				 pac.setId(rs.getInt("fk_paciente"));
				 pac.setNumero_processo(rs.getString("numero_processo"));
				 pac.setNome(rs.getString("nome"));
				 pac.setNomem(rs.getString("nome_meio"));
				 pac.setApelido(rs.getString("ultimo_nome"));
				 lista.add(pac);
		
			 }
			 preparador.close();
		}
		
		catch(SQLException er){
			
		}
		catch (Exception e) {
			
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
	public Paciente Perfil_triado(int cod)
	{
		Paciente pac = null;
		String sql = "SELECT * FROM vwpacientetriado_nd p, tblestadocivil c, tblgenero g where p.fk_paciente = ? "
					+"and p.fk_estadocivil = c.id_estadocivil "
					+ "and p.fk_genero = g.id_genero "
					+ "and data  = current_date() "
					+ "and fk_consulta_confirmada not in (select fk_consulta_confirmada from tblconsultafinalizada)";
		try
		{
			con = Conexao.getConexao(); 
			PreparedStatement preparador = con.prepareStatement(sql);
			 preparador.setInt(1, cod);
			 ResultSet rs = preparador.executeQuery();
			 if(rs.next())
			 {
				 pac = new Paciente();
				 pac.setId(rs.getInt("fk_paciente"));
				 pac.setNumero_processo(rs.getString("numeroprocesso"));
				 pac.setNome(rs.getString("nome"));
				 pac.setNomem(rs.getString("nome_meio"));
				 pac.setApelido(rs.getString("ultimo_nome"));
				 pac.setNomeEC(rs.getString("estado_civil"));
				 pac.setNomegenero(rs.getString("genero"));
				 pac.setIdade(rs.getDate("data_nascimento"));
				 pac.setFK_consulta_confirmada(rs.getInt("fk_consulta_confirmada"));
				 pac.setSanguineo(rs.getString("grupo_sanguineo"));
				 Calendar data = Calendar.getInstance();
				 data.setTime(rs.getDate("data_nascimento"));
				 pac.setDataNasc(data);
			 }
		}
		catch(SQLException er){
			System.out.println(er);
		}
		catch (Exception e) {
			
		}
		finally {
			try {
				con.close();
			} catch (SQLException e) {
				
				e.printStackTrace();
			}
		}
		return pac;
	}
	
	
//	INSERIR ENTIDADE
	public int nova_entidade()
	{
		String sql = "Select count(id_entidade) total from tblentidade";
		int cod=0;
		try {
			con = Conexao.getConexao();
			PreparedStatement ent = con.prepareStatement(sql);
			ResultSet rs = ent.executeQuery();
			if(rs.next())
			  cod = rs.getInt("total");	
			  cod++;
			ent.close();
			return cod;
			
		} catch (SQLException e) {
			e.printStackTrace();
		}
		catch (Exception e) {
			
		}
	    finally{
					try{
						con.close();
					}
					catch(SQLException ef){
						System.out.println("Erro finalizar: "+ef);
					}
			}
		return 0;
	}
	public int fk_entidade()
	{
		int fun = 0;
		String sql = "SELECT MAX(ID_ENTIDADE) ID FROM TBLENTIDADE";
		try {
			con = Conexao.getConexao();
			PreparedStatement preparador = con.prepareStatement(sql);
			ResultSet rs = preparador.executeQuery();
			
			if(rs.next())
				fun = rs.getInt("ID");
		}
		catch (Exception e) {
			
		}
	    finally{
					try{
						con.close();
					}
					catch(SQLException ef){
						System.out.println("Erro finalizar: "+ef);
					}
			}
			return fun +1;  
		
	}
	
	public List<Paciente> buscaEstadoCivil ()
	{
		//Preenchimento das comb boox
		List <Paciente> lista = new ArrayList<Paciente>();
		String sql = "SELECT * FROM TBLESTADOCIVIL ORDER BY ESTADO_CIVIL ASC";
		try {
			con = Conexao.getConexao();
			 PreparedStatement preparador = con.prepareStatement(sql);
			 ResultSet rs = preparador.executeQuery();
			 while(rs.next())
			 {
				 Paciente fun = new Paciente();
				 fun.setEst_civil(rs.getInt("id_estadocivil"));
				 fun.setNomeEC(rs.getString("estado_civil"));
				 lista.add(fun);
			 }
			 preparador.close();
		} catch (SQLException e) {
			
			e.printStackTrace();
		}
		catch (Exception e) {
			
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
	
	public List<Paciente> buscaConvenio (int codigo)
	{
		//Preenchimento das comb boox
		List <Paciente> lista = new ArrayList<Paciente>();
		String sql = "SELECT * FROM vwpacienteassegurado_semgroup where fk_paciente = ?";
		try {
			con = Conexao.getConexao();
			 PreparedStatement preparador = con.prepareStatement(sql);
			 preparador.setInt(1, codigo);
			 ResultSet rs = preparador.executeQuery();
			 while(rs.next())
			 {
				 Paciente fun = new Paciente();
				 fun.setId_convenio(rs.getInt("FK_fornecedor"));
				 fun.setConvenio(rs.getString("empresa"));
				 lista.add(fun);
			 }
			 preparador.close();
		} catch (SQLException e) {
			
			e.printStackTrace();
		}
		catch (Exception e) {
			
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
	
	public List<Paciente> buscaConvenio ()
	{
		//Preenchimento das comb boox
		List <Paciente> lista = new ArrayList<Paciente>();
		String sql = "SELECT * FROM TBLFORNECEDOR ORDER BY empresa ASC";
		try {
			con = Conexao.getConexao();
			 PreparedStatement preparador = con.prepareStatement(sql);
			 ResultSet rs = preparador.executeQuery();
			 while(rs.next())
			 {
				 Paciente fun = new Paciente();
				 fun.setId_convenio(rs.getInt("FK_fornecedor"));
				 fun.setConvenio(rs.getString("empresa"));
				 lista.add(fun);
			 }
			 preparador.close();
		} catch (SQLException e) {
			
			e.printStackTrace();
		}
		catch (Exception e) {
			
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
	
	public int fk_convenio(int codigo)
	{
		int fun = 0;
		String sql = "SELECT FK_fornecedor FROM vwpacienteassegurado_semgroup where fk_paciente = ?";
		try {
			con = Conexao.getConexao();
			PreparedStatement preparador = con.prepareStatement(sql);
			preparador.setInt(1, codigo);
			ResultSet rs = preparador.executeQuery();
			
			if(rs.next())
				fun = rs.getInt("FK_fornecedor");
		}
		catch (Exception e) {
			
		}
	    finally{
					try{
						con.close();
					}
					catch(SQLException ef){
						System.out.println("Erro finalizar: "+ef);
					}
			}
			return fun;  
		
	}
	
	public Paciente buscaConvenios (int codigo)
	{
		Paciente pac = new Paciente();
		String sql = "SELECT * FROM vwpacienteassegurado_semgroup where fk_paciente = ?";
		try {
			 con = Conexao.getConexao();
			 PreparedStatement preparador = con.prepareStatement(sql);
			 preparador.setInt(1, codigo);
			 ResultSet rs = preparador.executeQuery();
			 if(rs.next())
			 {
				 pac.setId_convenio(rs.getInt("FK_fornecedor"));
				 pac.setConvenio(rs.getString("empresa"));
			 }
			 preparador.close();
		} catch (SQLException e) {
			
			e.printStackTrace();
		}
		catch (Exception e) {
			
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
	
	public List<Paciente> buscaPlano (int codigo)
	{
		//Preenchimento das comb boox
		List <Paciente> lista = new ArrayList<Paciente>();
		String sql = "SELECT * FROM TBLTIPODECOBERTURA t,TBLFORNECEDOR f where t.FK_fornecedor = f.fk_fornecedor and t.FK_fornecedor = ? ORDER BY t.nome_do_plano ASC";
		try {
			con = Conexao.getConexao();
			 PreparedStatement preparador = con.prepareStatement(sql);
			 preparador.setInt(1, codigo);
			 ResultSet rs = preparador.executeQuery();
			 while(rs.next())
			 {
				 Paciente fun = new Paciente();
				 fun.setId(rs.getInt("id_tipo_dcobertura"));
				 fun.setNomePlano(rs.getString("nome_do_plano"));
				 lista.add(fun);
			 }
			 preparador.close();
		} catch (SQLException e) {
			
			e.printStackTrace();
		}
		catch (Exception e) {
			
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
	
	public List<Paciente> buscaTipoDocumento ()
	{
		List <Paciente> lista = new ArrayList<Paciente>();
		String sql = "SELECT * FROM TBLTIPODOCUMENTO ORDER BY ID_TIPO_DOCUMENTO = 0 DESC, TIPO_DOCUMENTO ASC";
		try {
			con = Conexao.getConexao();
			 PreparedStatement preparador = con.prepareStatement(sql);
			 ResultSet rs = preparador.executeQuery();
			 while(rs.next())
			 {
				 Paciente fun = new Paciente();
				 fun.setTipo_doc(rs.getInt("id_tipo_documento"));
				 fun.setNomeDoc(rs.getString("tipo_documento"));
				 lista.add(fun);
			 }
			 preparador.close();
		} catch (SQLException e) {
			
			e.printStackTrace();
		}
		catch (Exception e) {
			
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

	public Paciente buscarNome (String num)
	{
		Paciente pac = new Paciente();
		String sql = "SELECT * FROM vwentidadecomopaciente WHERE FK_entidade LIKE ?";
		try {
			 con = Conexao.getConexao();
			 PreparedStatement preparador = con.prepareStatement(sql);
			 preparador.setString(1, num);
			 ResultSet rs = preparador.executeQuery();
			 if(rs.next())
			 {
				 pac.setId_entidade(rs.getInt("FK_entidade"));
				 pac.setFK_paciente(rs.getInt("FK_entidade"));
				 pac.setNome(rs.getString("NOME"));
				 pac.setNomem(rs.getString("NOME_MEIO"));
				 pac.setApelido(rs.getString("ULTIMO_NOME"));
				 pac.setNumero_processo(rs.getString("numero_processo"));
			 }
			 preparador.close();
		} catch (SQLException e) {
			
			e.printStackTrace();
		}
		catch (Exception e) {
			
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
	
	public List<Paciente> buscaPais ()
	{
		List <Paciente> lista = new ArrayList<Paciente>();
		String sql = "SELECT * FROM TBLPAIS ORDER BY ID_PAIS = 0 DESC, PAIS ASC";
		try {
			con = Conexao.getConexao();
			 PreparedStatement preparador = con.prepareStatement(sql);
			 ResultSet rs = preparador.executeQuery();
			 while(rs.next())
			 {
				 Paciente fun = new Paciente();
				 fun.setPais(rs.getInt("id_pais"));
				 fun.setNomePais(rs.getString("pais"));
				 lista.add(fun);
			 }
			 preparador.close();
		} catch (SQLException e) {
			
			e.printStackTrace();
		}
		catch (Exception e) {
			
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
	
	public List<Paciente> buscaProvincia (int codigo)
	{
		List <Paciente> lista = new ArrayList<Paciente>();
		String sql = "SELECT * FROM TBLPROVINCIA where FK_pais=  ? ";
		try {
			con = Conexao.getConexao();
			 PreparedStatement preparador = con.prepareStatement(sql);
			 preparador.setInt(1, codigo);
			 ResultSet rs = preparador.executeQuery();
			 while(rs.next())
			 {
				 Paciente fun = new Paciente();
				 fun.setProvincia(rs.getInt("id_provincia"));
				 fun.setNomeProv(rs.getString("provincia"));
				 lista.add(fun);
			 }
			 preparador.close();
		} catch (SQLException e) {
			
			e.printStackTrace();
		}
		catch (Exception e) {
			
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
	
	public List<Paciente> buscaMunicipio (int codigo)
	{
		List <Paciente> lista = new ArrayList<Paciente>();
		String sql = "SELECT * FROM TBLMUNICIPIO where FK_provincia=  ? ";
		try {
			con = Conexao.getConexao();
			 PreparedStatement preparador = con.prepareStatement(sql);
			 preparador.setInt(1, codigo);
			 ResultSet rs = preparador.executeQuery();
			 while(rs.next())
			 {
				 Paciente fun = new Paciente();
				 fun.setMunicipio(rs.getInt("id_municipio"));
				 fun.setNomeMunicipio(rs.getString("municipio"));
				 lista.add(fun);
			 }
			 preparador.close();
		} catch (SQLException e) {
			
			e.printStackTrace();
		}
		catch (Exception e) {
			
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
	
	public List<Paciente> buscaDistrito (int codigo)
	{
		List <Paciente> lista = new ArrayList<Paciente>();
		String sql = "SELECT * FROM TBLDISTRITO where FK_municipio=  ? ";
		try {
			con = Conexao.getConexao();
			 PreparedStatement preparador = con.prepareStatement(sql);
			 preparador.setInt(1, codigo);
			 ResultSet rs = preparador.executeQuery();
			 while(rs.next())
			 {
				 Paciente fun = new Paciente();
				 fun.setDistrito(rs.getInt("id_distrito"));
				 fun.setNomeDistrito(rs.getString("distrito"));
				 lista.add(fun);
			 }
			 preparador.close();
		} catch (SQLException e) {
			
			e.printStackTrace();
		}
		catch (Exception e) {
			
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
	
	public List<Paciente> buscaBairro (int codigo)
	{
		List <Paciente> lista = new ArrayList<Paciente>();
		String sql = "SELECT * FROM TBLBAIRRO where FK_municipio= ? ";
		try {
			con = Conexao.getConexao();
			 PreparedStatement preparador = con.prepareStatement(sql);
			 preparador.setInt(1, codigo);
			 ResultSet rs = preparador.executeQuery();
			 while(rs.next())
			 {
				 Paciente fun = new Paciente();
				 fun.setBairro(rs.getInt("id_bairro"));
				 fun.setNomeBairro(rs.getString("bairro"));
				 lista.add(fun);
			 }
			 preparador.close();  
		} catch (SQLException e) {
			// TODO Auto-generated catch block
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
	   
	
	
	public List<Paciente> buscaProfissao ()
	{
		List <Paciente> lista = new ArrayList<Paciente>();
		String sql = "SELECT * FROM TBLPROFISSAO ORDER BY ID_PROFISSAO = 0 DESC, PROFISSAO ASC";
		try {
			con = Conexao.getConexao();
			 PreparedStatement preparador = con.prepareStatement(sql);
			 ResultSet rs = preparador.executeQuery();
			 while(rs.next())
			 {
				 Paciente fun = new Paciente();
				 fun.setProfissao(rs.getInt("id_profissao"));
				 fun.setNomeProf(rs.getString("profissao"));
				 lista.add(fun);
			 }
			 preparador.close();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
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
		
		public List<Paciente> buscaAfilicao ()
		{
			List <Paciente> lista = new ArrayList<Paciente>();
			String sql = "SELECT * FROM TBLAFILIACAO ORDER BY ID_AFILIACAO = 0 DESC, AFILIACAO ASC";
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
	
	//Gerar n�mero de processo aut�tico
		public int getIdEntidade(){
			int ultimoId = -1;
			String sql = "INSERT INTO TBLENTIDADE (EFECTIVIDADE) VALUE (-1)";
			try {
				con = Conexao.getConexao();
				PreparedStatement ent = con.prepareStatement(sql);
			    ent.execute();
			    ResultSet rs = ent.executeQuery("SELECT LAST_INSERT_ID()");
			    if(rs.next())
			      ultimoId = rs.getInt(1);
			    ent.close();
			    System.out.println("Cadastro de sucesso...TBLENTIDADE"); 
			}catch (SQLException e) {
				System.out.println(e);
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
			return ultimoId;
		}
	public Paciente numerprocesso ()
	{ 

		Paciente fun = new Paciente();
		int numero=0;
		String sql = "SELECT MAX(numero_processo) ID FROM TBLPACIENTE";
		try {
			con = Conexao.getConexao();
			PreparedStatement preparador = con.prepareStatement(sql);
			ResultSet rs = preparador.executeQuery();
			if(rs.last())
				numero = rs.getInt("ID");
			
			String nump = ""+numero;	
			fun.setNumero_processo(nump);
			
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
			return fun;  
		
	}
        //	INSERIR DADOS NA BASE DE DADOS
	
      //	Inserir dados na tabela entidade
	
	public void entidade(Paciente fun){
		
		String sql = "INSERT INTO TBLENTIDADE (ID_ENTIDADE) VALUE (?)";
		try {
			con = Conexao.getConexao();
			PreparedStatement ent = con.prepareStatement(sql);
		    ent.setInt(1, fun.getId_entidade());
		    ent.execute();
		    ent.close();
		    System.out.println("Cadastro de sucesso...TBLENTIDADE"); 
		}catch (SQLException e) {
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
	}

       //    Inserir dados na tabela entidade 
	
	public void cidadao(Paciente fun){
		
	    String sql = "INSERT INTO TBLCIDADAO (fk_entidade," +"nome,nome_meio," + "ultimo_nome," + "data_nascimento,fk_estadocivil,fk_genero,fk_tipo_documento," 
	    +   		 		"numero_documento,data_registo,fk_pais) " +
	    		"VALUES (?,?,?,?,?,?,?,?,?,?,?)";
	    try {
			 
			 //1-tblcidadao
	    	con = Conexao.getConexao();
			 PreparedStatement cid = con.prepareStatement(sql);
			 cid.setInt(1, fun.getId_entidade());
			 cid.setString(2, fun.getNome());
			 cid.setString(3, fun.getNomem());
			 cid.setString(4, fun.getApelido());
			 cid.setDate(5, new Date( fun.getDataNasc().getTimeInMillis()));
			 cid.setInt (6, fun.getEst_civil());
			 cid.setInt(7, fun.getGenero());
			 cid.setInt(8, fun.getTipo_doc());
			 cid.setString(9, fun.getNumero_doc());
			 cid.setDate(10, new Formatando().data_registo());
			 cid.setInt(11, 1);
			 cid.execute();
			 cid.close();
			 System.out.println("Cadastro de sucesso...TBLCIDADAO"); 
		} catch (SQLException e) {
			// TODO Auto-generated catch block
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
	}

	// 2-INSERIR PACIENTE
	
	    public void paciente(Paciente fun){
	    	
	    	String sql = "INSERT INTO TBLPACIENTE (fk_entidade, numero_processo)" +
	    	"VALUES (?,?)";
	    	try {
				 
				 //tblpaciente
	    		con = Conexao.getConexao();
				 PreparedStatement cid = con.prepareStatement(sql);
				 cid.setInt(1, fun.getId_entidade());
				 cid.setString(2, fun.getNumero_processo());
				 cid.execute();
				 cid.close();
				 System.out.println("Cadastro de sucesso...TBLPACIENTE"); 
			} catch (SQLException e) {
				e.printStackTrace();
			}
	    	catch (Exception e) {
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
	
	    
	    
	    
	    //3-INSERIR TELEFONE
		public void telefone(Paciente fun)
		{
			String sql = "INSERT INTO TBLTELEFONE (fk_entidade,telefone) VALUES (?,?)";
			try {
				con = Conexao.getConexao();
				PreparedStatement ent = con.prepareStatement(sql);
				ent.setInt(1, fun.getId_entidade());
				ent.setLong(2, fun.getTelefone());
				ent.execute();
				ent.close();
				System.out.println("Cadastro de sucesso...TBLTELEFONE"); 
			} catch (SQLException e) {
				// TODO Auto-generated catch block
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
		}
		
		// 4-INSERIR EMAIL
		public void email(Paciente fun)
		{
			String sql = "INSERT INTO TBLEMAIL (fk_entidade,email) VALUES (?,?)";
			try {
				con = Conexao.getConexao();
				PreparedStatement ent = con.prepareStatement(sql);
				ent.setInt(1, fun.getId_entidade());
				ent.setString(2, fun.getEmail());
				ent.execute();
				ent.close();
				System.out.println("Cadastro de sucesso...TBLEMAIL"); 
			} catch (SQLException e) {
				// TODO Auto-generated catch block
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
		}
		// 5-INSERIR PARENTE
		public void parente(Paciente fun)
		{
			String sql = "INSERT INTO TBLPARENTE (fk_entidade,nome_parente,telefone_parente,fk_afiliacao) " +
					               "VALUES (?,?,?,?)";
			try {
				con = Conexao.getConexao();
				PreparedStatement ent = con.prepareStatement(sql);
				ent.setInt(1, fun.getId_entidade());
				ent.setString(2, fun.getNomep());
				ent.setLong(3, fun.getTelefonep());
				ent.setInt(4, fun.getIdAfiliacao());
				ent.execute();			
				ent.close();
				System.out.println("Cadastro de sucesso...TBLPARENTE"); 
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
				}
			}
		}
	 //6- INSERIR NATURALIDADE
		public void naturalidade (Paciente fun){	
				
String sql = "INSERT INTO TBLNATURALIDADE (fk_entidade,fk_pais,fk_provincia,fk_municipio,fk_distrito,bairro ) " +
					"VALUES (?,?,?,?,?,?)";
			try {
				con = Conexao.getConexao();
				PreparedStatement ent = con.prepareStatement(sql);
				ent.setInt(1, fun.getId_entidade());
				ent.setInt(2, 1);
				ent.setInt(3, 1);
				ent.setInt(4, 1);
				ent.setInt(5, 1);
				ent.setString(6, fun.getEndereco());
				ent.execute();
				ent.close();
				System.out.println("Cadastro de sucesso...TBLNATURALIDADE"); 
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
		}
	
	   //7- INSERIR MORADA
	
		public void endereco(Paciente fun)
		{
			String sql = "INSERT INTO TBLENDERECO (fk_entidade,endereco) VALUE (?,?)";
			try {
				con = Conexao.getConexao();
				PreparedStatement ent = con.prepareStatement(sql);
				ent.setInt(1,fun.getId_entidade());
				ent.setString(2, fun.getEndereco());
				ent.execute();
				ent.close();
				System.out.println("Cadastro de sucesso...TBLENDERECO"); 
			} catch (SQLException e) {
				// TODO Auto-generated catch block
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
		}
	
		public Paciente buscarpacienteperfil(String num)
		  
		  {
		   Paciente fun = null;
		   String sql = "SELECT * FROM VWENTIDADECOMOPACIENTE where fk_entidade LIKE ? or numero_processo LIKE ?";
		   try {
			   con = Conexao.getConexao();
		    PreparedStatement preparador = con.prepareStatement(sql);
		     preparador.setString(1, num);
		     preparador.setString(2, num);
		     ResultSet rs = preparador.executeQuery();
		     if(rs.next())
		     {
		      fun = new Paciente();
		      fun.setId_entidade(rs.getInt("FK_entidade"));
		      fun.setNumero_processo(rs.getString("numero_processo"));
		      fun.setNome(rs.getString("nome"));
		      fun.setNomem(rs.getString("nome_meio"));
		      fun.setApelido(rs.getString("ultimo_nome"));
		      Calendar data = Calendar.getInstance();
		      data.setTime(rs.getDate("data_nascimento"));
		      fun.setDataNasc(data);
		      fun.setEndereco(rs.getString("endereco"));
		      fun.setNomePais(rs.getString("pais"));
		      fun.setNomegenero(rs.getString("genero"));
		      fun.setNomeProf(rs.getString("profissao"));
		      fun.setEmail(rs.getString("email"));
		      fun.setEditfone(rs.getLong("telefone"));
		      fun.setNomeEC(rs.getString("estado_civil"));
		      fun.setEditfonep(rs.getLong("telefone_parente"));
		      fun.setNomep(rs.getString("nome_parente"));
		      
		     }
		     preparador.close();
		   } catch (SQLException e) {
		    // TODO Auto-generated catch block
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
		   return fun;
		  }
		
		
		public List<Paciente> buscarpacienteeditar(String num)
		
		{
			Paciente fun = null;
			List <Paciente> lista = new ArrayList<Paciente>();
			String sql = "SELECT * FROM VWENTIDADECOMOPACIENTE where numero_processo LIKE ?";
			try {
				con = Conexao.getConexao();
				PreparedStatement preparador = con.prepareStatement(sql);
				 preparador.setString(1, num);
				 ResultSet rs = preparador.executeQuery();
				 if(rs.next())
				 {
					 fun = new Paciente();
					 fun.setId_entidade(rs.getInt("FK_entidade"));
					 fun.setNumero_processo(rs.getString("numero_processo"));
					 fun.setNome(rs.getString("nome"));
					 fun.setNomem(rs.getString("nome_meio"));
					 fun.setApelido(rs.getString("ultimo_nome"));
					 fun.setNumero_doc(rs.getString("numero_documento"));
					 Calendar data = Calendar.getInstance();
				      data.setTime(rs.getDate("data_nascimento"));
				      fun.setDataNasc(data);
					 fun.setEndereco(rs.getString("endereco"));
					 fun.setNomePais(rs.getString("pais"));
					 fun.setNomegenero(rs.getString("genero"));
					 fun.setNomeProv(rs.getString("provincia"));
					 
					 fun.setNomeDistrito(rs.getString("distrito"));
					 fun.setNomeBairro(rs.getString("bairro"));
					 fun.setNomeProf(rs.getString("profissao"));
					 fun.setEmail(rs.getString("email"));
					 fun.setEditfone(rs.getLong("telefone"));
					 fun.setNomep(rs.getString("nome_parente"));
					 fun.setEditfonep(rs.getLong("telefone_parente"));
					 fun.setNomeAfl(rs.getString("afiliacao"));
					 fun.setNomeEC(rs.getString("estado_civil"));
					 fun.setNomeDoc(rs.getString("tipo_documento"));
					 fun.setNomeMunicipio(rs.getString("municipio"));
					 fun.setNomegenero(rs.getString("genero"));
					 lista.add(fun);
				 }
				 preparador.close();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
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
		public List<Paciente> buscarPacientePorNome(String nome)
		{
			List <Paciente> lista = new ArrayList<Paciente>();
			String sql = "SELECT vw.*,grs.* FROM  vwtodasinformacoespaciente vw "
					   + "LEFT JOIN tblinformacaoclinica iclin ON vw.Entidade = iclin.FK_paciente "
					   + "LEFT JOIN tblgruposanguineo grs ON iclin.FK_grupo_sanguineo = grs.id_grupo_sanguineo "
					   + "WHERE vw.NomeCompleto LIKE ? ";
			try {
				con = Conexao.getConexao();
				 PreparedStatement ps = con.prepareStatement(sql);
				 ps.setString(1, nome.trim()+"%");
				 ResultSet rs = ps.executeQuery();
				 while(rs.next())
				 {
					 Paciente p = new Paciente();
					p.setId_entidade(rs.getInt("entidade"));
					p.setFK_paciente(rs.getInt("entidade"));
					p.setNumero_processo(rs.getString("numeroprocesso"));
					p.setNome(rs.getString("nomecompleto"));
					p.setNumero_doc(rs.getString("numero_documento"));
					Calendar data = Calendar.getInstance();
					data.setTime(rs.getDate("data_nascimento"));
					p.setDataNasc(data);
					p.setIdade(rs.getDate("data_nascimento"));
					p.setEndereco(rs.getString("endereco"));
					p.setNomePais(rs.getString("pais"));
					p.setNomegenero(rs.getString("genero").substring(0,1));
					p.setNomeProv(rs.getString("provincia"));
					p.setNomeMunicipio(rs.getString("municipio"));
					p.setNomeDistrito(rs.getString("distrito"));
					p.setNomeBairro(rs.getString("bairro"));
					p.setNomeProf(rs.getString("profissao"));
					p.setEmail(rs.getString("email"));
					p.setTelefone(rs.getLong("telefone"));
					p.setNomep(rs.getString("nome_parente"));
					p.setTelefonep(rs.getLong("telefone_parente"));
					p.setNomeAfl(rs.getString("afiliacao"));
					p.setNomeEC(rs.getString("estado_civil"));
					p.setNomeDoc(rs.getString("tipo_documento"));
					p.setSanguineo(rs.getString("grupo_sanguineo"));
					
					lista.add(p);
				 }
				 ps.close();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			catch (Exception e) {
				 
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
		
		public List<Paciente> buscarPaciente()
		{
			List <Paciente> lista = new ArrayList<Paciente>();
			String sql = "SELECT vw.*,grs.* FROM  vwtodasinformacoespaciente vw "
					   + "LEFT JOIN tblinformacaoclinica iclin ON vw.Entidade = iclin.FK_paciente "
					   + "LEFT JOIN tblgruposanguineo grs ON iclin.FK_grupo_sanguineo = grs.id_grupo_sanguineo order by entidade desc  ";
			try {
				con = Conexao.getConexao();
				 PreparedStatement preparador = con.prepareStatement(sql);
				 ResultSet rs = preparador.executeQuery();
				 while(rs.next())
				 {
					 Paciente p = new Paciente();
					p.setId_entidade(rs.getInt("entidade"));
					p.setFK_paciente(rs.getInt("entidade"));
					p.setNumero_processo(rs.getString("numeroprocesso"));
					p.setNome(rs.getString("nomecompleto"));
					p.setNumero_doc(rs.getString("numero_documento"));
					Calendar data = Calendar.getInstance();
					data.setTime(rs.getDate("data_nascimento"));
					p.setDataNasc(data);
					p.setIdade(rs.getDate("data_nascimento"));
					p.setEndereco(rs.getString("endereco"));
					p.setNomePais(rs.getString("pais"));
					p.setNomegenero(rs.getString("genero").substring(0,1));
					p.setNomeProv(rs.getString("provincia"));
					p.setNomeMunicipio(rs.getString("municipio"));
					p.setNomeDistrito(rs.getString("distrito"));
					p.setNomeBairro(rs.getString("bairro"));
					p.setNomeProf(rs.getString("profissao"));
					p.setEmail(rs.getString("email"));
					p.setTelefone(rs.getLong("telefone"));
					p.setNomep(rs.getString("nome_parente"));
					p.setTelefonep(rs.getLong("telefone_parente"));
					p.setNomeAfl(rs.getString("afiliacao"));
					p.setNomeEC(rs.getString("estado_civil"));
					p.setNomeDoc(rs.getString("tipo_documento"));
					p.setSanguineo(rs.getString("grupo_sanguineo"));
					
					lista.add(p);
				 }
				 preparador.close();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			catch (Exception e) {
				 
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
		
		
		public List<Paciente> buscarPacienteSimples()
		  
		  {
		   Paciente fun = null;
		   List <Paciente> lista = new ArrayList<Paciente>();
		   String sql = "SELECT * FROM vwpacientesimples";
		   try {
		    con = Conexao.getConexao();
		     PreparedStatement preparador = con.prepareStatement(sql);
		     ResultSet rs = preparador.executeQuery();
		     while(rs.next())
		     {
		     fun = new Paciente();
		     fun.setFK_paciente(rs.getInt("entidade"));
		     fun.setNumero_processo(rs.getString("numeroprocesso"));
		     fun.setNome(rs.getString("nomecompleto"));
		     Calendar data = Calendar.getInstance();
		     data.setTime(rs.getDate("data_nascimento"));
		     fun.setDataNasc(data);
		     fun.setPac_idade(rs.getInt("idade"));
		     fun.setNomegenero(rs.getString("genero"));
		     lista.add(fun);
		     }
		     preparador.close();
		   } catch (SQLException e) {
		    // TODO Auto-generated catch block
		    e.printStackTrace();
		   }
		   catch (Exception e) {
		     
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
		
		
		
public List<Paciente> buscarpacientePesquisar(String nome)
		{
			Paciente fun = null;
			List <Paciente> lista = new ArrayList<Paciente>();
			String sql = "SELECT * FROM vwtodasinformacoespaciente WHERE nomecompleto LIKE ? or numeroprocesso LIKE ? limit 100 ";
			try {
				con = Conexao.getConexao();
				 PreparedStatement preparador = con.prepareStatement(sql);
				 preparador.setString(1, nome+"%");
				 preparador.setString(2, nome+"%");
				 ResultSet rs = preparador.executeQuery();
				 while(rs.next())
				 {
					fun = new Paciente();
					fun.setId_entidade(rs.getInt("entidade"));
					fun.setFK_paciente(rs.getInt("entidade"));
					fun.setNumero_processo(rs.getString("numeroprocesso"));
					fun.setNome(rs.getString("nomecompleto"));
					fun.setNumero_doc(rs.getString("numero_documento"));
					Calendar data = Calendar.getInstance();
					data.setTime(rs.getDate("data_nascimento"));
					fun.setDataNasc(data);
					fun.setIdade(rs.getDate("data_nascimento"));
					fun.setEndereco(rs.getString("endereco"));
					fun.setNomePais(rs.getString("pais"));
					fun.setNomegenero(rs.getString("genero"));
					fun.setNomeProv(rs.getString("provincia"));
					fun.setNomeMunicipio(rs.getString("municipio"));
					fun.setNomeDistrito(rs.getString("distrito"));
					fun.setNomeBairro(rs.getString("bairro"));
					fun.setNomeProf(rs.getString("profissao"));
					fun.setEmail(rs.getString("email"));
					fun.setTelefone(rs.getLong("telefone"));
					fun.setNomep(rs.getString("nome_parente"));
					fun.setTelefonep(rs.getLong("telefone_parente"));
					fun.setNomeAfl(rs.getString("afiliacao"));
					fun.setNomeEC(rs.getString("estado_civil"));
					fun.setNomeDoc(rs.getString("tipo_documento"));
					lista.add(fun);
				 }
				 preparador.close();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			catch (Exception e) {
				 
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
		
public List<Paciente> buscarpacienteAgenda()
		
		{
			Paciente fun = null;
			List <Paciente> lista = new ArrayList<Paciente>();
			String sql = "SELECT * FROM vwtodasinformacoespaciente order by data_registo desc limit 500 ";
			try {
				con = Conexao.getConexao();
				 PreparedStatement preparador = con.prepareStatement(sql);
				 ResultSet rs = preparador.executeQuery();
				 while(rs.next())
				 {
					fun = new Paciente();
					fun.setId_entidade(rs.getInt("entidade"));
					fun.setFK_paciente(rs.getInt("entidade"));
					fun.setNumero_processo(rs.getString("numeroprocesso"));
					fun.setNome(rs.getString("nomecompleto"));
					fun.setNumero_doc(rs.getString("numero_documento"));
					Calendar data = Calendar.getInstance();
					data.setTime(rs.getDate("data_nascimento"));
					fun.setDataNasc(data);
					fun.setIdade(rs.getDate("data_nascimento"));
					fun.setEndereco(rs.getString("endereco"));
					fun.setNomePais(rs.getString("pais"));
					fun.setNomegenero(rs.getString("genero"));
					fun.setNomeProv(rs.getString("provincia"));
					fun.setNomeMunicipio(rs.getString("municipio"));
					fun.setNomeDistrito(rs.getString("distrito"));
					fun.setNomeBairro(rs.getString("bairro"));
					fun.setNomeProf(rs.getString("profissao"));
					fun.setEmail(rs.getString("email"));
					fun.setTelefone(rs.getLong("telefone"));
					fun.setNomep(rs.getString("nome_parente"));
					fun.setTelefonep(rs.getLong("telefone_parente"));
					fun.setNomeAfl(rs.getString("afiliacao"));
					fun.setNomeEC(rs.getString("estado_civil"));
					fun.setNomeDoc(rs.getString("tipo_documento"));
					lista.add(fun);
				 }
				 preparador.close();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			catch (Exception e) {
				 
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
		
/*-listar pacientes na tela dos pacientes no modulo paciente-*/
public List<Paciente> ListarPacienteNosPacientes()

{
 Paciente fun = null;
 List <Paciente> lista = new ArrayList<Paciente>();
 String sql = "SELECT * FROM vwlistarPacienteNosPacientes order by numero_processo desc limit 500 ";
 try {
  con = Conexao.getConexao();
  PreparedStatement preparador = con.prepareStatement(sql);
  ResultSet rs = preparador.executeQuery();
  while(rs.next())
  {
   fun = new Paciente();
   fun.setFK_paciente(rs.getInt("fk_entidade"));
   fun.setNumero_processo(rs.getString("numero_processo"));
   fun.setNome(rs.getString("nomecompleto"));
   Calendar data = Calendar.getInstance();
   data.setTime(rs.getDate("data_nascimento"));
   fun.setDataNasc(data);
   fun.setIdade(rs.getDate("data_nascimento"));
   fun.setEndereco(rs.getString("endereco"));
   fun.setNomegenero(rs.getString("genero"));
   fun.setTelefone(rs.getLong("telefone"));
   lista.add(fun);
  }
  preparador.close();
 } catch (SQLException e) {
  // TODO Auto-generated catch block
  e.printStackTrace();
 }
catch (Exception e) {
 
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










		public List<Paciente> buscarpaciente(String valor)
		
		{
			Paciente fun = null;
			List <Paciente> lista = new ArrayList<Paciente>();
			String sql = "SELECT * FROM vwtodasinformacoespaciente where nomecompleto Like ? or numeroprocesso Like ? ";
			try {
				con = Conexao.getConexao();
				 PreparedStatement preparador = con.prepareStatement(sql);
				 preparador.setString(1, valor+"%");
				 preparador.setString(2, valor);
				 ResultSet rs = preparador.executeQuery();
				 while(rs.next())
				 {
					fun = new Paciente();
					fun.setId_entidade(rs.getInt("entidade"));
					fun.setNumero_processo(rs.getString("numeroprocesso"));
					fun.setNome(rs.getString("nomecompleto"));
					fun.setNome_paciente(rs.getString("nomecompleto"));
					fun.setNumero_doc(rs.getString("numero_documento"));
					Calendar data = Calendar.getInstance();
					data.setTime(rs.getDate("data_nascimento"));
					fun.setDataNasc(data);
					fun.setIdade(rs.getDate("data_nascimento"));
					fun.setEndereco(rs.getString("endereco"));
					fun.setNomePais(rs.getString("pais"));
					fun.setNomegenero(rs.getString("genero"));
					fun.setNomeProv(rs.getString("provincia"));
					fun.setNomeMunicipio(rs.getString("municipio"));
					fun.setNomeDistrito(rs.getString("distrito"));
					fun.setNomeBairro(rs.getString("bairro"));
					fun.setNomeProf(rs.getString("profissao"));
					fun.setEmail(rs.getString("email"));
					fun.setTelefone(rs.getLong("telefone"));
					fun.setNomep(rs.getString("nome_parente"));
					fun.setTelefonep(rs.getLong("telefone_parente"));
					fun.setNomeAfl(rs.getString("afiliacao"));
					fun.setNomeEC(rs.getString("estado_civil"));
					fun.setNomeDoc(rs.getString("tipo_documento"));
					lista.add(fun);
				 }
				 preparador.close();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			catch (Exception e) {
				 
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
		
		
public List<Paciente> buscarPacienteNaAgenda(String valor)
		
		{
			Paciente fun = null;
			List <Paciente> lista = new ArrayList<Paciente>();
			String sql = "SELECT * FROM vwpacientenaagenda where nomecompleto Like ? or numero_processo Like ? ";
			try {
				con = Conexao.getConexao();
				 PreparedStatement preparador = con.prepareStatement(sql);
				 preparador.setString(1, valor+"%");
				 preparador.setString(2, valor);
				 ResultSet rs = preparador.executeQuery();
				 while(rs.next())
				 {
					 Paciente pac = new Paciente();
					 pac.setId_entidade(rs.getInt("fk_entidade"));
					 pac.setNumero_processo(rs.getString("numero_processo"));
					 pac.setNome(rs.getString("nome"));
					 pac.setNomem(rs.getString("nome_meio"));
					 pac.setApelido(rs.getString("ultimo_nome"));
	    		     pac.setPac_idade(rs.getInt("idade"));
					 pac.setNomegenero(rs.getString("genero"));
					 pac.setGestante(rs.getInt("id_genero"));
					 
					 
					
					
					lista.add(pac);
				 }
				 preparador.close();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			catch (Exception e) {
				 
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
		
		
		public Paciente buscarUmPaciente(String valor)
		
		{
			Paciente fun = null;
			int pos = valor.indexOf("-");
			 System.out.println(valor.substring(0, pos-1));
			String sql = "SELECT * FROM vwtodasinformacoespaciente where nomecompleto Like ?  or numeroprocesso Like ? ";
			try {
				con = Conexao.getConexao();
				 PreparedStatement preparador = con.prepareStatement(sql);
				 preparador.setString(1, "%"+valor.substring(pos+1)+"%");
			 
				 preparador.setString(2, valor.substring(0, pos-1));
				 
				 ResultSet rs = preparador.executeQuery();
				 if(rs.next())
				 {
					fun = new Paciente();
					fun.setId_entidade(rs.getInt("entidade"));
					fun.setFK_paciente(rs.getInt("entidade"));
					fun.setNumero_processo(rs.getString("numeroprocesso"));
					fun.setNome(rs.getString("nomecompleto"));
					fun.setNome_paciente(rs.getString("nomecompleto"));
					fun.setNumero_doc(rs.getString("numero_documento"));
					fun.setNomeCompleto(rs.getString("nomecompleto"));
					Calendar data = Calendar.getInstance();
					data.setTime(rs.getDate("data_nascimento"));
					fun.setDataNasc(data);
					fun.setIdade(rs.getDate("data_nascimento"));
					fun.setEndereco(rs.getString("endereco"));
					fun.setNomePais(rs.getString("pais"));
					fun.setNomegenero(rs.getString("genero"));
					fun.setNomeProv(rs.getString("provincia"));
					fun.setNomeMunicipio(rs.getString("municipio"));
					fun.setNomeDistrito(rs.getString("distrito"));
					fun.setNomeBairro(rs.getString("bairro"));
					fun.setNomeProf(rs.getString("profissao"));
					fun.setEmail(rs.getString("email"));
					fun.setTelefone(rs.getLong("telefone"));
					fun.setNomep(rs.getString("nome_parente"));
					fun.setTelefonep(rs.getLong("telefone_parente"));
					fun.setNomeAfl(rs.getString("afiliacao"));
					fun.setNomeEC(rs.getString("estado_civil"));
					fun.setNomeDoc(rs.getString("tipo_documento"));
					  
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
			return fun;
		}
		
public Paciente buscarUmPacientes(int valor)
		
		{
			Paciente fun = null;
			 
			String sql = "SELECT * FROM vwtodasinformacoespaciente where entidade = ? ";
			try {
				con = Conexao.getConexao();
				 PreparedStatement preparador = con.prepareStatement(sql);
				 preparador.setInt(1, valor);
				 
				 ResultSet rs = preparador.executeQuery();
				 if(rs.next())
				 {
					fun = new Paciente();
					fun.setId_entidade(rs.getInt("entidade"));
					fun.setFK_paciente(rs.getInt("entidade"));
					fun.setNumero_processo(rs.getString("numeroprocesso"));
					fun.setNome(rs.getString("nomecompleto"));
					fun.setNome_paciente(rs.getString("nomecompleto"));
					fun.setNumero_doc(rs.getString("numero_documento"));
					Calendar data = Calendar.getInstance();
					data.setTime(rs.getDate("data_nascimento"));
					fun.setDataNasc(data);
					fun.setIdade(rs.getDate("data_nascimento"));
					fun.setEndereco(rs.getString("endereco"));
					fun.setNomePais(rs.getString("pais"));
					fun.setNomegenero(rs.getString("genero"));
					fun.setNomeProv(rs.getString("provincia"));
					fun.setNomeMunicipio(rs.getString("municipio"));
					fun.setNomeDistrito(rs.getString("distrito"));
					fun.setNomeBairro(rs.getString("bairro"));
					fun.setNomeProf(rs.getString("profissao"));
					fun.setEmail(rs.getString("email"));
					fun.setTelefone(rs.getLong("telefone"));
					fun.setNomep(rs.getString("nome_parente"));
					fun.setTelefonep(rs.getLong("telefone_parente"));
					fun.setNomeAfl(rs.getString("afiliacao"));
					fun.setNomeEC(rs.getString("estado_civil"));
					fun.setNomeDoc(rs.getString("tipo_documento"));
					  
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
			return fun;
		}
		
public List<Paciente> buscarInforpaciente()
		
		{
			Paciente fun = null;
			List <Paciente> lista = new ArrayList<Paciente>();
			String sql = "SELECT * FROM vwtodasinformacoespaciente where Entidade "
						 +"and Entidade NOT IN(SELECT FK_paciente FROM tblinformacaoclinica)";
			try {
				con = Conexao.getConexao();
				 PreparedStatement preparador = con.prepareStatement(sql);
				 ResultSet rs = preparador.executeQuery();
				 while(rs.next())
				 {
					fun = new Paciente();
					fun.setId_entidade(rs.getInt("entidade"));
					fun.setNumero_processo(rs.getString("numeroprocesso"));
					fun.setNome(rs.getString("nomecompleto"));
					fun.setNumero_doc(rs.getString("numero_documento"));
					Calendar data = Calendar.getInstance();
					data.setTime(rs.getDate("data_nascimento"));
					fun.setDataNasc(data);
					fun.setIdade(rs.getDate("data_nascimento"));
					fun.setEndereco(rs.getString("endereco"));
					fun.setNomePais(rs.getString("pais"));
					fun.setNomegenero(rs.getString("genero"));
					fun.setNomeProv(rs.getString("provincia"));
					fun.setNomeMunicipio(rs.getString("municipio"));
					fun.setNomeDistrito(rs.getString("distrito"));
					fun.setNomeBairro(rs.getString("bairro"));
					fun.setNomeProf(rs.getString("profissao"));
					fun.setEmail(rs.getString("email"));
					fun.setTelefone(rs.getLong("telefone"));
					fun.setNomep(rs.getString("nome_parente"));
					fun.setTelefonep(rs.getLong("telefone_parente"));
					fun.setNomeAfl(rs.getString("afiliacao"));
					fun.setNomeEC(rs.getString("estado_civil"));
					fun.setNomeDoc(rs.getString("tipo_documento"));
					lista.add(fun);
				 }
				 preparador.close();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
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
		
public List<Paciente> buscarpacientePlano()
		
		{
			Paciente fun = null;
			List <Paciente> lista = new ArrayList<Paciente>();
			String sql = "SELECT * FROM vwpacientessemplanodesaude ";
			try {
				con = Conexao.getConexao();
				 PreparedStatement preparador = con.prepareStatement(sql);
				 ResultSet rs = preparador.executeQuery();
				 while(rs.next())
				 {
					fun = new Paciente();
					fun.setId_entidade(rs.getInt("entidade"));
					fun.setNumero_processo(rs.getString("numeroprocesso"));
					fun.setNome(rs.getString("nomecompleto"));

					Calendar data = Calendar.getInstance();
					data.setTime(rs.getDate("data_nascimento"));
					fun.setDataNasc(data);
					fun.setIdade(rs.getDate("data_nascimento"));

					lista.add(fun);
				 }
				 preparador.close();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
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

	public List<Paciente> buscarpacientepornumero(String num)

	{
		Paciente fun = null;
		List<Paciente> lista = new ArrayList<Paciente>();
		String sql = "SELECT * FROM VWENTIDADECOMOPACIENTE where numero_processo LIKE ?";
		try {
			con = Conexao.getConexao();
			PreparedStatement preparador = con.prepareStatement(sql);
			preparador.setString(1, num);
			ResultSet rs = preparador.executeQuery();
			if (rs.next()) {
				fun = new Paciente();
				fun.setId_entidade(rs.getInt("FK_entidade"));
				fun.setNumero_processo(rs.getString("numero_processo"));
				fun.setNome(rs.getString("nome"));
				fun.setNomem(rs.getString("nome_meio"));
				fun.setApelido(rs.getString("ultimo_nome"));
				Calendar data = Calendar.getInstance();
				data.setTime(rs.getDate("data_nascimento"));
				fun.setDataNasc(data);
				fun.setIdade(rs.getDate("data_nascimento"));
				fun.setTelefone(rs.getLong("telefone"));
				fun.setEmail(rs.getString("email"));
				fun.setEndereco(rs.getString("endereco"));
				lista.add(fun);
			}
			preparador.close();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
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
       
	public List<Paciente> buscarpacienteporbi(String num)

	{
		Paciente fun = null;
		List<Paciente> lista = new ArrayList<Paciente>();
		String sql = "SELECT * FROM VWENTIDADECOMOPACIENTE where numero_documento LIKE ?";
		try {
			con = Conexao.getConexao();
			PreparedStatement preparador = con.prepareStatement(sql);
			preparador.setString(1, num);
			ResultSet rs = preparador.executeQuery();
			if (rs.next()) {
				fun = new Paciente();
				fun.setId_entidade(rs.getInt("FK_entidade"));
				fun.setNumero_processo(rs.getString("numero_processo"));
				fun.setNome(rs.getString("nome"));
				fun.setNomem(rs.getString("nome_meio"));
				fun.setApelido(rs.getString("ultimo_nome"));
				Calendar data = Calendar.getInstance();
				data.setTime(rs.getDate("data_nascimento"));
				fun.setDataNasc(data);
				fun.setIdade(rs.getDate("data_nascimento"));
				fun.setTelefone(rs.getLong("telefone"));
				fun.setEmail(rs.getString("email"));
				fun.setEndereco(rs.getString("endereco"));
				lista.add(fun);
			}
			preparador.close();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
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
       
       public List<Paciente> buscarpacientepornome(String nome)
		
		{
			Paciente fun = null;
			List <Paciente> lista = new ArrayList<Paciente>();
			String sql = "SELECT * FROM VWENTIDADECOMOPACIENTE where nome LIKE '"+nome+"%'";
			try {
				con = Conexao.getConexao();
				PreparedStatement preparador = con.prepareStatement(sql);
				 ResultSet rs = preparador.executeQuery();
				 while (rs.next())
				 {
					 fun = new Paciente();
					 fun.setId_entidade(rs.getInt("FK_entidade"));
					 fun.setNumero_processo(rs.getString("numero_processo"));
					 fun.setNome(rs.getString("nome"));
					 fun.setNomem(rs.getString("nome_meio"));
					 fun.setApelido(rs.getString("ultimo_nome"));
					 Calendar data = Calendar.getInstance();
				      data.setTime(rs.getDate("data_nascimento"));
				      fun.setDataNasc(data);
				      fun.setIdade(rs.getDate("data_nascimento"));
					 fun.setTelefone(rs.getLong("telefone"));
					 fun.setEmail(rs.getString("email"));
					 fun.setEndereco(rs.getString("endereco"));
					 lista.add(fun);
				 }
				 preparador.close();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
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
       
       
	public int buscarprovid(String provincia) {
		int id = 0;
		String sql = "SELECT * FROM TBLPROVINCIA WHERE PROVINCIA LIKE ?";
		try {
			con = Conexao.getConexao();
			PreparedStatement preparador = con.prepareStatement(sql);
			preparador.setString(1, provincia);
			ResultSet rs = preparador.executeQuery();
			if (rs.next())
				id = rs.getInt("id_provincia");
			preparador.close();
		} catch (SQLException e) {
			e.printStackTrace();
		} catch (Exception e) {
			// TODO: handle exception
		} finally {
			try {
				con.close();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		return id;

	}
       
       public int buscarmunicipioid(String municipio)
      	{
       	   int id=0;
       	   String sql = "SELECT * FROM TBLMUNICIPIO WHERE MUNICIPIO LIKE ?";
       	   try {
       		con = Conexao.getConexao();
       	     PreparedStatement preparador = con.prepareStatement(sql);
       	     preparador.setString(1, municipio);
       	     ResultSet rs = preparador.executeQuery();
       	     if(rs.next())
       	     id = rs.getInt("id_municipio"); 
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
       	   return id;
       	   
          }
       
       public int buscardestritoid(String distrito)
     	{
      	   int id=0;
      	   String sql = "SELECT * FROM TBLDISTRITO WHERE DISTRITO LIKE ?";
      	   try {
      		 con = Conexao.getConexao();
      	     PreparedStatement preparador = con.prepareStatement(sql);
      	     preparador.setString(1, distrito);
      	     ResultSet rs = preparador.executeQuery();
      	     if(rs.next())
      	     id = rs.getInt("id_distrito");
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
      	   return id;
      	   
         }
       public int buscarbairroid(String bairro)
    	{
     	   int id=0;
     	   String sql = "SELECT * FROM TBLBAIRRO WHERE BAIRRO LIKE ?";
     	   try {
     		  con = Conexao.getConexao();
     	     PreparedStatement preparador = con.prepareStatement(sql);
     	     preparador.setString(1, bairro);
     	     ResultSet rs = preparador.executeQuery();
     	     if(rs.next())
     	     id = rs.getInt("id_bairro");
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
     	   return id;
     	   
        }
       // METODO PARA MODIFICAR DADOS
       
       public void modificarendereco(Paciente pc)
   	{
   		String sql = "UPDATE TBLENDERECO SET endereco =? WHERE fk_entidade=?";
   		try {
   			con = Conexao.getConexao();
   			PreparedStatement ent = con.prepareStatement(sql);  			
   			ent.setString(1, pc.getEndereco());
   			ent.setInt(2, pc.getId_entidade());
   			ent.execute();
   			ent.close();
   			System.out.println("Alteracao com sucesso...TBLENDERECO"); 
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
   	}
       
    // 8 tabela - naturalidade
   	public void modificarnaturalidade(Paciente pc)
   	{
   		String sql = "UPDATE TBLNATURALIDADE SET fk_pais=?,fk_provincia=? WHERE fk_entidade=?";
   		try {
   			con = Conexao.getConexao();
   			PreparedStatement ent = con.prepareStatement(sql);   			
   			ent.setInt(1, pc.getPais());
   			ent.setInt(2, pc.getProvincia());
   			ent.setInt(3, pc.getId_entidade());
   			ent.execute();
   			ent.close();
   			System.out.println("Cadastro de sucesso...TBLNATURALIDADE"); 
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
   	}
   	
   	// cidad�o
   	
	public void modificarcidadao(Paciente pc) {

		String sql = "UPDATE TBLCIDADAO SET nome=?," + "nome_meio=?," + "ultimo_nome=?," + "data_nascimento=?,"
				+ "fk_estadocivil=?," + "fk_genero=?," + "fk_pais=?," + "fk_tipo_documento=?," + "numero_documento=? "
				+ "WHERE  fk_entidade=?";
		try {
			con = Conexao.getConexao();
			PreparedStatement cid = con.prepareStatement(sql);
			cid.setString(1, pc.getNome());
			cid.setString(2, pc.getNomem());
			cid.setString(3, pc.getApelido());
			cid.setDate(4, new Date(pc.getDataNasc().getTimeInMillis()));
			cid.setInt(5, pc.getEst_civil());
			cid.setInt(6, pc.getGenero());
			cid.setInt(7, pc.getPais());
			cid.setInt(8, pc.getTipo_doc());
			cid.setString(9, pc.getNumero_doc());
			cid.setInt(10, pc.getId_entidade());
			cid.execute();
			cid.close();
			System.out.println("Alteracao efectuada com sucesso...TBLCIDADAO");
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
   	
   	public void modificarfone(Paciente pc)
	{
		String sql = "UPDATE TBLTELEFONE SET telefone=? WHERE fk_entidade =?";
		try {
			con = Conexao.getConexao();
			PreparedStatement ent = con.prepareStatement(sql);			
			ent.setLong(1, pc.getTelefone());
			ent.setInt(2, pc.getId_entidade());
			ent.execute();
			ent.close();
			System.out.println("Alteracao efectuada com sucesso...TBLTELEFONE"); 
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
	// 4 tabela - email
	public void modificaremail(Paciente pc)
	{
		String sql = "UPDATE TBLEMAIL SET email=? WHERE fk_entidade=?";
		try {
			con = Conexao.getConexao();
			PreparedStatement ent = con.prepareStatement(sql);			
			ent.setString(1, pc.getEmail());
			ent.setInt(2, pc.getId_entidade());
			ent.execute();
			ent.close();
			System.out.println("Alteracao efectuada com sucesso...TBLEMAIL"); 
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
	}
	
	// 6 tabela - parente
		public void modificarparente(Paciente pc)
		{
			String sql = "UPDATE TBLPARENTE SET nome_parente=?,telefone_parente=?,fk_afiliacao=? WHERE fk_entidade =?";
			try {
				con = Conexao.getConexao();
				PreparedStatement ent = con.prepareStatement(sql);				
				ent.setString(1, pc.getNomep());
				ent.setLong(2, pc.getTelefonep());
				ent.setInt(3, pc.getIdAfiliacao());
				ent.setInt(4, pc.getId_entidade());
				ent.execute();			
				ent.close();
				System.out.println("ALTERACAO com sucesso...TBLPARENTE"); 
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
		}
		
		public void modificarpaciente(Paciente pc)
		{
			String sql = "UPDATE TBLPACIENTE SET fk_profissao=? WHERE fk_entidade=?";
			try {
				con = Conexao.getConexao();
				PreparedStatement ent = con.prepareStatement(sql);
				ent.setInt   (1, pc.getProfissao());
				ent.setInt   (2, pc.getId_entidade());
				ent.execute();
				ent.close();
				System.out.println("ALTERACAO com sucesso...TBLPACIENTE"); 
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
		}
		
		
		// METODO PARA ELIMINAR DADOS
		
		public void eliminarcidadao(int id)
		{
			String sql = "DELETE FROM TBLCIDADAO  WHERE FK_entidade=?";
			try {
				con = Conexao.getConexao(); 
				PreparedStatement preparador = con.prepareStatement(sql);
				 preparador.setInt   (1, id);
				 preparador.execute();
				 preparador.close();
				 System.out.println("Exclusao com sucesso...TBLCIDADAO"); 
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
		
		public void eliminarendereco(int id)
		{
			String sql = "DELETE FROM TBLENDERECO  WHERE FK_entidade=?";
			try {
				con = Conexao.getConexao(); 
				PreparedStatement preparador = con.prepareStatement(sql);
				 preparador.setInt   (1, id);
				 preparador.execute();
				 preparador.close();
				 System.out.println("Exclusao com sucesso...TBLENDERECO"); 
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
		}
		
		public void eliminarparente(int id)
		{
			String sql = "DELETE FROM TBLPARENTE  WHERE FK_entidade=?";
			try {
				con = Conexao.getConexao();
				 PreparedStatement preparador = con.prepareStatement(sql);
				 preparador.setInt   (1, id);
				 preparador.execute();
				 preparador.close();
				 System.out.println("Exclusao com sucesso...TBLPARENTE"); 
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
		}
		public void eliminarfone(int id)
		{
			String sql = "DELETE FROM TBLTELEFONE  WHERE FK_entidade=?";
			try {
				con = Conexao.getConexao();
				 PreparedStatement preparador = con.prepareStatement(sql);
				 preparador.setInt   (1, id);
				 preparador.execute();
				 preparador.close();
				 System.out.println("Exclusao com sucesso...TBLTELEFONE"); 
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
		}
		
		public void eliminarpaciente(int id)
		{
			String sql = "DELETE FROM TBLPACIENTE  WHERE FK_entidade=?";
			try {
				con = Conexao.getConexao();
				 PreparedStatement preparador = con.prepareStatement(sql);
				 preparador.setInt   (1, id);
				 preparador.execute();
				 preparador.close();
				 System.out.println("Exclusao com sucesso...TBLPACIENTE"); 
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
		
		
		public void eliminarentidade (int id)
		{
			String sql = "DELETE FROM TBLENTIDADE  WHERE id_entidade=?";
			try {
				con = Conexao.getConexao();
				 PreparedStatement preparador = con.prepareStatement(sql);
				 preparador.setInt   (1, id);
				 preparador.execute();
				 preparador.close();
				 System.out.println("Exclusao com sucesso...TBLENTIDADE"); 
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
		public void eliminarnaturalidade (int id)
		{
			String sql = "DELETE FROM TBLNATURALIDADE WHERE FK_entidade=?";
			try {
				con = Conexao.getConexao();
				 PreparedStatement preparador = con.prepareStatement(sql);
				 preparador.setInt   (1, id);
				 preparador.execute();
				 preparador.close();
				 System.out.println("Exclusao com sucesso...TBLENTIDADE"); 
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
		}
		public void eliminaremail (int id)
		{
			String sql = "DELETE FROM TBLEMAIL  WHERE FK_entidade=?";
			try {
				con = Conexao.getConexao();
				 PreparedStatement preparador = con.prepareStatement(sql);
				 preparador.setInt   (1, id);
				 preparador.execute();
				 preparador.close();
				 System.out.println("Exclusao com sucesso...TBLEMAIL"); 
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
		}
		
	   //// MEDOTODOS PARA MARCA��O DE CONSULTAS
		
		 // pesquisa paciente
        public List<Paciente> buscarpacientemarcacao()
        {
			List <Paciente> lista = new ArrayList<Paciente>();
			String sql = "SELECT * FROM vwpacientenaagenda where fk_entidade <> 0 ORDER BY numero_processo desc limit 100";
			try 
			{
				con = Conexao.getConexao();
				PreparedStatement preparador = con.prepareStatement(sql);
				 ResultSet rs = preparador.executeQuery();
				 while(rs.next())
				 {
					 Paciente pac = new Paciente();
					 pac.setId_entidade(rs.getInt("fk_entidade"));
					 pac.setNumero_processo(rs.getString("numero_processo"));
					 pac.setNome(rs.getString("nome"));
					 pac.setNomem(rs.getString("nome_meio"));
					 pac.setApelido(rs.getString("ultimo_nome"));
	    		     pac.setPac_idade(rs.getInt("idade"));
					 pac.setNomegenero(rs.getString("genero"));
					 pac.setGestante(rs.getInt("id_genero"));
					 lista.add(pac);
				 }
				 preparador.close();
			} catch (SQLException e) 
			{
				// TODO Auto-generated catch block
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
           
           public List<Paciente> buscarpacienteconfirmado(){
   			List <Paciente> lista = new ArrayList<Paciente>();
   			String sql =  "SELECT * FROM vwconsultaconfirmadafaturando where data_confirmacao = curdate() "
   						+ "and id_consulta_confirmada NOT IN(SELECT fk_consulta_confirmada FROM tblfacturaconsulta) "
   						+ "and id_consulta_confirmada NOT IN(SELECT fk_consulta_confirmada FROM tblisencaoatribuida) ";
   			try {
   				con = Conexao.getConexao();
   				PreparedStatement preparador = con.prepareStatement(sql);
   				 ResultSet rs = preparador.executeQuery();
   				 while(rs.next())
   				 {
   					 Paciente fun = new Paciente();
   					 fun.setId_entidade(rs.getInt("FK_consulta_marcada"));
   					 fun.setNumero_processo(rs.getString("numero_processo"));
   					 fun.setNome(rs.getString("nome"));
   					 fun.setNomem(rs.getString("nome_meio"));
   					 fun.setApelido(rs.getString("ultimo_nome"));
   					 fun.setServico(rs.getString("servico"));
   					 fun.setId_servico(rs.getInt("id_servico"));
   					 fun.setFK_doutor(rs.getInt("fk_doutor"));
   					fun.setFK_paciente(rs.getInt("FK_entidade_paciente"));
   					fun.setPreco(rs.getDouble("preco"));;
   					Calendar data = Calendar.getInstance();
   					data.setTime(rs.getDate("data_confirmacao"));
   					fun.setData_do_agendamento(data);
   					 fun.setId_confirmada(rs.getInt("id_consulta_confirmada"));
   					 lista.add(fun);
   				 }
   				 preparador.close();
   			} catch (SQLException e) {
   				 
   				e.printStackTrace();
   			}
   			catch (Exception e) {
 
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
           ///// 
           
           // Para preencher o textfild
           public Paciente buscarpacientevisualizar (int num)
 		  
 		  {
 		   Paciente pac = null;
 		   String sql = "SELECT * FROM VWENTIDADECOMOPACIENTE where FK_entidade LIKE ?";
 		   try {
 			  con = Conexao.getConexao();
 		    PreparedStatement preparador = con.prepareStatement(sql);
 		     preparador.setInt(1, num);
 		     ResultSet rs = preparador.executeQuery();
 		     if(rs.next())
 		     {
 		     pac = new Paciente();
 		     pac.setId_entidade(rs.getInt("FK_entidade"));
 		     pac.setNumero_processo(rs.getString("numero_processo"));
 		     pac.setNome(rs.getString("nome"));
 		     pac.setNomem(rs.getString("nome_meio"));
 		     pac.setApelido(rs.getString("ultimo_nome"));
 		     }
 		     preparador.close();
 		   } catch (SQLException e) {
 		    // TODO Auto-generated catch block
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
 		 // System.out.print("Nome: "+pac.getNome());
 		   return pac;
 		  }
           
           // pesquisa por nome
           public List<Paciente> buscaPorNomepesquisar (String nome)
           {
        	   List <Paciente> lista = new ArrayList<Paciente>();
            String sql = "SELECT * FROM VWENTIDADECOMOPACIENTE WHERE nome LIKE '"+nome+"%'";
            try {
            	con = Conexao.getConexao();
              PreparedStatement preparador = con.prepareStatement(sql);
              ResultSet rs = preparador.executeQuery();
              while(rs.next())
              {
            	  Paciente pac = new Paciente();
            	  pac.setId_entidade(rs.getInt("FK_entidade"));
            	  pac.setNumero_processo(rs.getString("numero_processo"));
            	  pac.setNome(rs.getString("nome"));
            	  pac.setNomem(rs.getString("nome_meio"));
            	  pac.setApelido(rs.getString("ultimo_nome"));
            	  Calendar data = Calendar.getInstance();
    		      data.setTime(rs.getDate("data_nascimento"));
    		      pac.setDataNasc(data);
            	  lista.add(pac);
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
            //System.out.print(lista.get(0).getNome());
            return lista;
           }
           
           
           // pesquisa por numero de processo
           public List<Paciente> buscaPorNumeropesquisar (String num) 
           
           {
        	   List <Paciente> lista = new ArrayList<Paciente>();
           
            String sql = "SELECT * FROM VWENTIDADECOMOPACIENTE WHERE numero_processo LIKE ?";
            try {
            	con = Conexao.getConexao();
              PreparedStatement preparador = con.prepareStatement(sql);
              preparador.setString(1, num);
              ResultSet rs = preparador.executeQuery();
              while(rs.next())
              {
            	  Paciente pac = new Paciente();
            	  pac.setId_entidade(rs.getInt("FK_entidade"));
            	  pac.setNumero_processo(rs.getString("numero_processo"));
            	  pac.setNome(rs.getString("nome"));
            	  pac.setNomem(rs.getString("nome_meio"));
            	  pac.setApelido(rs.getString("ultimo_nome"));
            	  Calendar data = Calendar.getInstance();
    		      data.setTime(rs.getDate("data_nascimento"));
    		      pac.setDataNasc(data);
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
            //System.out.print(lista.get(0).getNumero_processo());
            return lista;
           }
		public int buscarDoutor(String id) {
			Paciente pac = null;
	 		   String sql = "SELECT * FROM tblconsultamarcada where FK_entidade LIKE ?";
	 		   try {
	 		    PreparedStatement preparador = con.prepareStatement(sql);
	 		     preparador.setString(1, id);
	 		     ResultSet rs = preparador.executeQuery();
	 		     if(rs.next())
	 		     {
	 		     pac = new Paciente();
	 		     pac.setId_entidade(rs.getInt("FK_entidade"));
	 		     pac.setNumero_processo(rs.getString("numero_processo"));
	 		     pac.setNome(rs.getString("nome"));
	 		     pac.setNomem(rs.getString("nome_meio"));
	 		     pac.setApelido(rs.getString("ultimo_nome"));
	 		     }
	 		     preparador.close();
	 		   } catch (SQLException e) {
	 		    // TODO Auto-generated catch block
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
			return 0;
		}
           
//		1- Mostrar o nome da Empresa no text field Aridja
		public Paciente buscarPacienteV (int num)
		{
			Paciente lista = new Paciente();
			String sql = "SELECT * FROM vwconsultaconfirmadafaturando where FK_entidade_paciente = ? and data_confirmacao = curdate() "
					+ "and id_consulta_confirmada NOT IN(SELECT fk_consulta_confirmada FROM tblfacturaconsulta)";
			try { 
				con = Conexao.getConexao();
				 PreparedStatement preparador = con.prepareStatement(sql);
				 preparador.setInt(1, num);
				 ResultSet rs = preparador.executeQuery();
				 if(rs.next())
				 {
					 	lista.setId_consulta_confirmada(rs.getInt("id_consulta_confirmada"));
					 	lista.setFK_paciente(rs.getInt("FK_entidade_paciente"));
					 	lista.setNumero_processo(rs.getString("numero_processo"));
	            	  	lista.setNome(rs.getString("nome"));
	            	  	lista.setNomem(rs.getString("nome_meio"));
						lista.setApelido(rs.getString("ultimo_nome"));
						lista.setServico(rs.getString("servico"));
						lista.setPreco(rs.getDouble("preco"));
						lista.setId_servico(rs.getInt("id_servico"));
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
//			System.out.println("Nome do Paciente: "+lista.getNome());
			return lista;
	}
//		--------------------------------------- Isentar
//		Inserir dados na tabela entidade
		
		public void isentarPaciente(Paciente fun){
			String sql = "INSERT INTO tblisencaoatribuida (FK_paciente, FK_consulta_confirmada, motivo, FK_funcionario, data, hora, FK_servico) VALUE (?,?,?,?,?,?,?)";
			try {
				con = Conexao.getConexao();
				PreparedStatement ent = con.prepareStatement(sql);
			    ent.setInt(1, fun.getFK_entidade_paciente());
			    ent.setInt(2, fun.getFK_consulta_confirmada());
			    ent.setString(3, fun.getMotivo());
			    ent.setInt(4, fun.getFK_funcionario());
			    ent.setDate(5, new Formatando().data_registo());
			    ent.setString(6, new Formatando().getHoraAtual()); 
			    ent.setInt(7, fun.getFK_servico());
			    ent.execute();
			    ent.close();
			    con.close();
			    System.out.println("Cadastro com sucesso...tblisencaoatribuida"); 
			}catch (SQLException e) {
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
		}
		 
		// Numero de Isenção
		  
		  public int numeroIsencao ()
		  { 
		   
		   int fun = 0;
		   String sql = "SELECT count(FK_paciente) ID FROM tblisencaoatribuida";
		   try {
			   con = Conexao.getConexao();
		    PreparedStatement preparador = con.prepareStatement(sql);
		    ResultSet rs = preparador.executeQuery();
		    
		    if(rs.next())
		     fun = rs.getInt("ID");
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
		    return fun+1;  
		   
		  }

//			5-Pesquisar PacienteIsenção
			public List <Paciente> buscarPacienteIsencao(String nome)
			{
				List <Paciente> lista = new ArrayList<Paciente>();
				String sql = "SELECT * FROM vwconsultaconfirmada WHERE nome LIKE ? or numero_processo LIKE ? or servico LIKE ? or FK_paciente LIKE ?";
				try {
					con = Conexao.getConexao();
					 PreparedStatement preparador = con.prepareStatement(sql);
					 preparador.setString(1, nome);
					 preparador.setString(2, nome);
					 preparador.setString(3, nome);
					 preparador.setString(4, nome);
					 ResultSet rs = preparador.executeQuery();
					 while(rs.next())
					 {
						 Paciente fun = new Paciente();
						 fun.setNumero_processo(rs.getString("numero_processo"));
						 fun.setFK_paciente(rs.getInt("FK_paciente"));
						 fun.setNome(rs.getString("nome"));
						 fun.setNomem(rs.getString("nome_meio"));
						 fun.setApelido(rs.getString("ultimo_nome"));
						 fun.setServico(rs.getString("servico"));
						 fun.setPreco(rs.getDouble("preco"));
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
//			6- Alterar Produto
			public void alt_pacienteIsentado(Paciente p)
			{
				String sql = "UPDATE tblconsultaconfirmada SET isento =? WHERE id_consulta_confirmada = ?";
				try {
					con = Conexao.getConexao();
					PreparedStatement preparador = con.prepareStatement(sql);
					preparador.setInt(1, p.getIsento());
					preparador.setInt(2, p.getId_consulta_confirmada());
					preparador.execute();
					preparador.close();
					System.out.println("Alteracao com sucesso...tblconsultaconfirmada TUDO CAPOTE"); 
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
			}
//			  8-Editar produto
			public Paciente edit_pacienteIsentado(int ed)
			{
				Paciente fun = new Paciente();
				String sql = "SELECT * FROM tblconsultaconfirmada WHERE id_consulta_confirmada = ?";
				try {
					con = Conexao.getConexao();
					PreparedStatement preparador = con.prepareStatement(sql);
					 preparador.setInt(1, ed);
					 ResultSet rs = preparador.executeQuery();
					 while(rs.next())
					 {
						 fun.setId_consulta_confirmada(rs.getInt("id_consulta_confirmada"));
						 fun.setGestante(rs.getInt("gestante"));
						 fun.setIsento(rs.getInt("isento"));
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
				return fun;
			}
			
	public Paciente buscaValorEmkz() {
		Paciente fun = null;
		List<Paciente> lista = new ArrayList<Paciente>();
		String sql = "SELECT * FROM vwtotalemkz";
		try {
			con = Conexao.getConexao();
			PreparedStatement preparador = con.prepareStatement(sql);
			ResultSet rs = preparador.executeQuery();
			while (rs.next()) {
				fun = new Paciente();
				Calendar data = Calendar.getInstance();
				if (rs.getDate("data") != null)
					data.setTime(rs.getDate("data"));
				fun.setDataIsencao(data);
				fun.setNum_coluna(rs.getInt("numerodecolunas"));
				fun.setTotal_s(rs.getString("total"));
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
				
				return fun; 
		}  
			
			  //// MEDOTODOS PARA MARCAÇÃO DE EXAMES
	   		
	  		 // pesquisa paciente
	             public Paciente marcacaoexame(String cod){
	            	 Paciente pac =null;
	  			String sql = "SELECT * FROM VWENTIDADECOMOPACIENTE where FK_entidade =?";
	  			try {
	  				con = Conexao.getConexao();
	  				PreparedStatement preparador = con.prepareStatement(sql);
	  				preparador.setString(1, cod);
	  				 ResultSet rs = preparador.executeQuery();
	  				 if(rs.next())
	  				 {
	  					 pac = new Paciente();
	  					 pac.setId_entidade(rs.getInt("FK_entidade"));  
	  					 pac.setId_tipo_dcobertura(rs.getInt("id_plano_dsaude"));
	  					 pac.setNome(rs.getString("nome"));
	  					 pac.setNomem(rs.getString("nome_meio"));
	  					 pac.setApelido(rs.getString("ultimo_nome"));
	  					 pac.setEmpresa(rs.getString("empresa"));  	
	  					 pac.setIdade(rs.getDate("data_nascimento"));
	  					 pac.setGenero(rs.getInt("FK_genero"));
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
	  			return pac;
	  		}

	             
	        public Paciente getPerfilPaciente(String codp){
	        	
	        	Paciente perfil = new Paciente();
	        	String sql = "Select * from vwentidadecomopaciente where FK_entidade = ?";
	        	try{
	        		con = Conexao.getConexao();
	        		PreparedStatement ps = con.prepareStatement(sql);
	        		ps.setString(1, codp);
	        		ResultSet rs = ps.executeQuery();
	        		if(rs.next()){
	        		perfil.setNome(rs.getString("nome").toUpperCase().trim());
	        		perfil.setNomem(rs.getString("nome_meio").toUpperCase().trim());
	        		perfil.setApelido(rs.getString("ultimo_nome").toUpperCase().trim());
	        		perfil.setNumero_processo(rs.getString("numero_processo"));
	        		perfil.setIdade(rs.getDate("data_nascimento"));
	        		perfil.setNomeProf(rs.getString("profissAo"));
	        		perfil.setNomeEC(rs.getString("estado_civil"));
	        		perfil.setEmail(rs.getString("email"));
	        		perfil.setEditfone(rs.getLong("telefone"));
	        		perfil.setNomegenero(rs.getString("genero"));
	        		perfil.setGenero(rs.getInt("FK_genero"));
	        		perfil.setFK_paciente(rs.getInt("FK_entidade"));
	        		perfil.setSanguineo(rs.getString("grupo_sanguineo"));
	        		perfil.setFK_sanguineo (rs.getInt("FK_grupo_sanguineo"));
	        		perfil.setEndereco(ft.transforma(rs.getString("endereco")+", "+rs.getString("provincia")));
	        		Calendar data = Calendar.getInstance();
	        		data.setTime(rs.getDate("data_nascimento"));
	        		perfil.setDataNasc(data);
	        		perfil.setIdade(rs.getDate("data_nascimento"));
	        		}
	        	}catch(SQLException er){
	        		er.printStackTrace();
	        	}
	        	return perfil;
	        }

	        public boolean xequePlanoBeneficioPaciente(int codp, int cods){
	        	String sql = "SELECT * from vwservicosnoplanopaciente where FK_paciente = ? and id_servico = ? ";
	        	try {
					con = Conexao.getConexao();
					PreparedStatement ps = con.prepareStatement(sql);
					ps.setInt(1, codp);
					ps.setInt(2, cods);
					ResultSet rs = ps.executeQuery();
					if(rs.next())
						return true;
				} catch (Exception e) {
					e.printStackTrace();
				}
	        	return false;
	        }
	         public boolean xequeConsultaPlanoBeneficioPaciente(int codp, int cods){
	        	String sql = "SELECT * from vwservicosconsultanoplanopaciente where FK_paciente = ? and id_servico = ? ";
	        	try {
					con = Conexao.getConexao();
					PreparedStatement ps = con.prepareStatement(sql);
					ps.setInt(1, codp);
					ps.setInt(2, cods);
					ResultSet rs = ps.executeQuery();
					if(rs.next())
						return true;
				} catch (Exception e) {
					e.printStackTrace();
				}
	        	return false;
	        }
	         
	         public int getTitularPlanoBeneficioPaciente(int codp, int cods){
		        	String sql = "SELECT * from vwservicosconsultanoplanopaciente where FK_paciente = ? and id_servico = ? ";
		        	try {
						con = Conexao.getConexao();
						PreparedStatement ps = con.prepareStatement(sql);
						ps.setInt(1, codp);
						ps.setInt(2, cods);
						ResultSet rs = ps.executeQuery();
						if(rs.next())
							return rs.getInt("FK_parente");
					} catch (Exception e) {
						e.printStackTrace();
					}
		        	return 0;
		        }
	         public int getTitularPlanoBeneficioPacienteTratamento(int codp, int cods){
		        	String sql = "SELECT * from vwservicosnoplanopaciente where FK_paciente = ? and id_servico = ? ";
		        	try {
						con = Conexao.getConexao();
						PreparedStatement ps = con.prepareStatement(sql);
						ps.setInt(1, codp);
						ps.setInt(2, cods);
						ResultSet rs = ps.executeQuery();
						if(rs.next())
							return rs.getInt("FK_parente");
					} catch (Exception e) {
						e.printStackTrace();
					}
		        	return 0;
		        }
	         public boolean xequeExamePlanoBeneficioPaciente(int codp, int code){
		        	String sql = "SELECT * from vwservicosexamesnoplanopaciente where FK_paciente = ? and FK_servico_exame = ? ";
		        	try {
						con = Conexao.getConexao();
						PreparedStatement ps = con.prepareStatement(sql);
						ps.setInt(1, codp);
						ps.setInt(2, code);
						ResultSet rs = ps.executeQuery();
						if(rs.next())
							return true;
					} catch (Exception e) {
						e.printStackTrace();
					}
		        	return false;
		        }
	         public int getTitularPlanoBeneficioPacienteExame(int codp, int cods){
		        	String sql = "SELECT * from vwservicosexamesnoplanopaciente where FK_paciente = ? and FK_servico_exame = ? ";
		        	try {
						con = Conexao.getConexao();
						PreparedStatement ps = con.prepareStatement(sql);
						ps.setInt(1, codp);
						ps.setInt(2, cods);
						ResultSet rs = ps.executeQuery();
						if(rs.next())
							return rs.getInt("FK_parente");
					} catch (Exception e) {
						e.printStackTrace();
					}
		        	return 0;
		        }
	        public PacientePlano getPlanoBeneficioPaciente(int codp, int cods){
	        	PacientePlano p = new PacientePlano();
	        	String sql = "SELECT * from vwservicosnoplanopaciente where FK_paciente = ? and id_servico = ? ";
	        	try {
					con = Conexao.getConexao();
					PreparedStatement ps = con.prepareStatement(sql);
					ps.setInt(1, codp);
					ps.setInt(2, cods);
					ResultSet rs = ps.executeQuery();
					if(rs.next())
						{
						
						p.setNum_apolice(rs.getString("num_apolice"));
						p.setNomefornecedor(rs.getString("empresa"));
						p.setId_plano_dsaude(rs.getInt("id_plano_dsaude"));
						p.setFK_paciente(rs.getInt("FK_paciente"));
						p.setFK_fornecedor(rs.getInt("FK_fornecedor"));
						p.setNomePlano(rs.getString("nome_do_plano"));
						p.setFK_tipo_dcobertura(rs.getInt("FK_tipo_dcobertura"));
						
						}
				} catch (Exception e) {
					e.printStackTrace();
				}
	        	return p;
	        }
			
			public PacientePlano buscarPlanoBeneficioPaciente(int codp){
	        	PacientePlano pl = new  PacientePlano();
	        	String sql = "SELECT * from vwservicosnoplanopaciente where FK_paciente = ? ";
	        	try {
					con = Conexao.getConexao();
					PreparedStatement ps = con.prepareStatement(sql);
					ps.setInt(1, codp);
					 
					ResultSet rs = ps.executeQuery();
					if(rs.next())
						{
						
						pl.setFK_paciente(rs.getInt("FK_paciente"));
						pl.setFK_tipo_dcobertura(rs.getInt("FK_tipo_dcobertura"));
						pl.setFK_fornecedor(rs.getInt("FK_fornecedor"));
						}
				} catch (Exception e) {
					e.printStackTrace();
				}
	        	return pl;
	        }

			public void novoPaciente(Paciente p) throws Exception
			{
				p.setId_entidade(getIdEntidade());
				p.setNumero_processo(numProcesso());
				cidadao(p);
				paciente(p);
				naturalidade(p);
				telefone(p);
				endereco(p);
				email(p);
				inforClinico(p);
				novoArquivo(p);
				System.out.println("FEITO OK");
			}
			 
			public void inforClinico (Paciente p){	
				
				String sql = "INSERT INTO TBLINFORMACAOCLINICA (FK_paciente, FK_grupo_sanguineo, proveniencia ) VALUES (?,?,?)";
							try {
								con = Conexao.getConexao();
								PreparedStatement inf = con.prepareStatement(sql);
								inf.setInt(1, p.getId_entidade());
								inf.setInt(2, p.getGruposg());
								inf.setString(3, "Paciente");
								inf.execute();
								inf.close();
								System.out.println("Cadastro de sucesso...TBLINFORMACAOCLINICA"); 
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
				}
			
			public void novoArquivo(Paciente arq)
			{
				String sql = "INSERT INTO TBLARQUIVODOFUNCIONARIO (fk_entidade,nome_ficheiro) VALUES (?,?)";
				try {
					con = Conexao.getConexao();
					PreparedStatement ent = con.prepareStatement(sql);
					ent.setInt(1, arq.getId_entidade());
					ent.setString(2, arq.getNomeArq());
					ent.execute();
					ent.close();
					System.out.println("Cadastro de sucesso...TBLARQUIVOS"); 
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
			}
}

