package ao.co.cligest.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

import ao.co.cligest.entidades.Emergencia;
import ao.co.cligest.entidades.Paciente;
import ao.co.cligest.entidades.Triagem;


/**
 * @datacriacao 22-09-2015
 * @author NELSON DIWIDI - ANGOTECH
 * @manutencao NELSON DIWIDI, ARIDJA ANDRE
 *
 */
public class EmergenciaDAO extends PacienteDAO {

	private Connection con;
	Formatando ft = new Formatando();
	
		
	public List<Triagem> getTriadosParaEmergencia()
	{
		List <Triagem> lista = new ArrayList<Triagem>();
		String sql = "SELECT * from vwtriadosnobancourgencia where data_triagem = curdate() "
					+ "and encaminhado Like 'Emergencia' and fk_paciente not in(select fk_paciente from tblemergencia where data_registo = curdate() and status <> 'observacao') "
					+ "order by fk_estado asc, hora_triagem ";
		try {
			con = Conexao.getConexao();
			 PreparedStatement preparador = con.prepareStatement(sql);
			 ResultSet rs = preparador.executeQuery();
			 while(rs.next())
			 {
				 Triagem p = new Triagem();
				p.setFK_paciente(rs.getInt("FK_paciente"));;
				p.setNumero_processo(rs.getString("numeroprocesso"));
				p.setNome(rs.getString("nomecompleto"));
				p.setIdade(rs.getDate("data_nascimento"));
				p.setHoraConsulta(rs.getString("hora_triagem"));
				p.setEstado(rs.getString("estado_do_paciente"));
				p.setFK_estado_do_paciente(rs.getInt("fk_estado"));
				p.setEncaminhar(rs.getString("encaminhado"));
				p.setSituacao(rs.getString("situacao"));
				Calendar data = Calendar.getInstance();
				data.setTime(rs.getDate("data_triagem"));
				p.setDataConsulta(data);
				lista.add(p);
			 }
			 preparador.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		catch (Exception e) {e.getMessage();	}
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
	
	public List<Triagem> getAtendidosNaEmergencia()
	{
		List <Triagem> lista = new ArrayList<Triagem>();
		String sql = "SELECT * from vwemergenciapacientesatendidos where data_registo = curdate() ";
		try {
			con = Conexao.getConexao();
			 PreparedStatement preparador = con.prepareStatement(sql);
			 ResultSet rs = preparador.executeQuery();
			 while(rs.next())
			 {
				 Triagem p = new Triagem();
				p.setFK_paciente(rs.getInt("FK_paciente"));
				p.setId_emergencia(rs.getInt("id_emergencia"));
				p.setNumero_processo(rs.getString("numeroprocesso"));
				p.setNome(rs.getString("nomecompleto"));
				p.setIdade(rs.getDate("data_nascimento"));
				p.setHoraConsulta(rs.getString("hora_registo"));
//				p.setEstado(rs.getString("estado_do_paciente"));
//				p.setFK_estado_do_paciente(rs.getInt("fk_estado"));
//				p.setEncaminhar(rs.getString("encaminhado"));
//				p.setSituacao(rs.getString("situacao"));
				Calendar data = Calendar.getInstance();
				data.setTime(rs.getDate("data_registo"));
				p.setDataConsulta(data);
				lista.add(p);
			 }
			 preparador.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		catch (Exception e) {e.getMessage();	}
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
	
	public Triagem getTriadosParaEmergencia(int cod)
	{
		Triagem p = new Triagem();
		String sql = "SELECT * from vwtriadosnobancourgencia,tblgenero where FK_paciente = ?"
				+ " and fk_genero = id_genero and data_triagem = curdate() "
				+ "and (encaminhado Like 'Emergencia' or encaminhado Like 'EmergenciaHJ' ) order by id_triagem_bancoue desc";
		try {
			con = Conexao.getConexao();
			 PreparedStatement preparador = con.prepareStatement(sql);
			 preparador.setInt(1, cod);
			 ResultSet rs = preparador.executeQuery();
			 if(rs.next())
			 {
				 
				p.setFK_paciente(rs.getInt("FK_paciente"));;
				p.setNumero_processo(rs.getString("numeroprocesso"));
				p.setNome(rs.getString("nomecompleto"));
				p.setIdade(rs.getDate("data_nascimento"));
				p.setEstado(rs.getString("estado_do_paciente"));
				p.setValor_pulso(rs.getString("pulso"));
				p.setValor_resp(rs.getString("respiracao"));
				p.setValor_tmp(rs.getString("temperatura"));
				p.setValor_tns(rs.getString("tensao"));
				p.setPeso(rs.getDouble("peso"));
				p.setAltura(rs.getDouble("altura"));
				p.setImc(rs.getDouble("imc"));
				p.setDiagnostico_preliminar(rs.getString("observacao")); 
				p.setGenero(rs.getString("genero"));
			 }
			 preparador.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		catch (Exception e) {e.getMessage();	}
	    finally{
					try{
						con.close();
					}
					catch(SQLException ef){
						System.out.println("Erro finalizar: "+ef);
					}
			}
		return p;
	}     
	
	public List<Triagem> getUrgenciasParaEmergencia()
	{
		List <Triagem> lista = new ArrayList<Triagem>();
		String sql = "SELECT * from vwpacientesatendidoshojepraurgencia where status Like 'Emergencia' and data_triagem = curdate() "
					+ "and fk_paciente not in (select fk_paciente, hora_registo from tblemergencia where status <> 'Observacao') order by hora_registo";
		try {
			con = Conexao.getConexao();
			 PreparedStatement preparador = con.prepareStatement(sql);
			 ResultSet rs = preparador.executeQuery();
			 while(rs.next())
			 {
				Triagem p = new Triagem();
				p.setFK_urgencia(rs.getInt("id_urgencia"));
				p.setFK_paciente(rs.getInt("FK_paciente"));;
				p.setNumero_processo(rs.getString("numeroprocesso"));
				p.setNome(rs.getString("nomecompleto"));
				p.setIdade(rs.getDate("data_nascimento"));
				p.setHoraConsulta(rs.getString("hora_registo"));
				p.setEstado(rs.getString("estado_do_paciente"));
				p.setFK_estado_do_paciente(rs.getInt("fk_estado_do_paciente"));
				p.setEncaminhar(rs.getString("status"));
				p.setSituacao(rs.getString("situacao"));
				Calendar data = Calendar.getInstance();
				data.setTime(rs.getDate("data_registo"));
				p.setDataConsulta(data);			
				lista.add(p);
			 }
			 preparador.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		catch (Exception e) {e.getMessage();	}
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
      
	public Paciente getPacienteUrgencia(int cod)
	{
		Paciente p = new Paciente();
		String sql = "SELECT * from vwperfilcompleto where fk_paciente = ?";
		try {
			con = Conexao.getConexao();
			 PreparedStatement preparador = con.prepareStatement(sql);
			 preparador.setInt(1, cod);
			 ResultSet rs = preparador.executeQuery();
			 if(rs.next())
			 {
				p.setFK_paciente(rs.getInt("FK_paciente"));;
				p.setNumero_processo(rs.getString("numeroprocesso"));
				p.setNome(rs.getString("nomecompleto"));
				p.setNumero_doc(rs.getString("numero_documento"));
				Calendar data = Calendar.getInstance();
				data.setTime(rs.getDate("data_nascimento"));
				p.setDataNasc(data);
				p.setIdade(rs.getDate("data_nascimento"));
				p.setEndereco(rs.getString("municipio")+", "+ rs.getString("bairro")+", "+rs.getString("endereco"));
				p.setNomegenero(rs.getString("genero"));
				p.setEmail(rs.getString("email"));
				p.setEditfone(rs.getLong("telefone"));
				p.setNomep(rs.getString("nome_parente"));
				p.setEditfonep(rs.getLong("telefone_parente"));
				p.setNomeAfl(rs.getString("afiliacao"));
				p.setNomeEC(rs.getString("estado_civil"));
				p.setSanguineo(rs.getString("grupo_sanguineo"));		 
			 }
			 preparador.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		catch (Exception e) {e.getMessage();	}
	    finally{
					try{
						con.close();
					}
					catch(SQLException ef){
						System.out.println("Erro finalizar: "+ef);
					}
			}
		return p;
	}
	
	public boolean checkPacienteEmergencia(int cod)
	{
		boolean rsult = false;
		Paciente p = new Paciente();
		String sql = "SELECT * from tblemergencia where fk_paciente = ? and data_registo = curdate()";
		try {
			con = Conexao.getConexao();
			 PreparedStatement preparador = con.prepareStatement(sql);
			 preparador.setInt(1, cod);
			 ResultSet rs = preparador.executeQuery();
			 if(rs.next())
			 {
				p.setFK_paciente(rs.getInt("FK_paciente"));
				rsult = true;
						 
			 }
			 preparador.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		catch (Exception e) {e.getMessage();	}
	    finally{
					try{
						con.close();
					}
					catch(SQLException ef){
						System.out.println("Erro finalizar: "+ef);
					}
			}
		return rsult;
	}
	
	public int getPacienteEmergencia(int cod)
	{
		int rsult = 0;
		String sql = "SELECT * from tblemergencia where id_emergencia = ? and data_registo = curdate()";
		try {
			con = Conexao.getConexao();
			 PreparedStatement preparador = con.prepareStatement(sql);
			 preparador.setInt(1, cod);
			 ResultSet rs = preparador.executeQuery();
			 if(rs.next())
			 {
				 rsult = rs.getInt("FK_paciente");
				  						 
			 }
			 preparador.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		catch (Exception e) {e.getMessage();	}
	    finally{
					try{
						con.close();
					}
					catch(SQLException ef){
						System.out.println("Erro finalizar: "+ef);
					}
			}
		return rsult;
	}
	
	public List<Triagem> getNiveisConciencia()
	{
		List<Triagem> lNiveis = new ArrayList<Triagem>();
		String sql = "SELECT * from tblnivelconsciencia ";
		try {
			 con = Conexao.getConexao();
			 PreparedStatement preparador = con.prepareStatement(sql);
			 ResultSet rs = preparador.executeQuery();
			 while(rs.next())
			 {
				Triagem nv = new Triagem();
				nv.setId_nivel_consciencia(rs.getInt("id_nivel_consciencia"));
				nv.setNivel_consciencia(rs.getString("nivel_consciencia"));
				lNiveis.add(nv);		 
			 }
			 preparador.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		catch (Exception e) {e.getMessage();	}
	    finally{
					try{
						con.close();
					}
					catch(SQLException ef){
						System.out.println("Erro finalizar: "+ef);
					}
			}
		return lNiveis;
	}
	
	public List<Triagem> getAlergias()
	{
		List<Triagem> lNiveis = new ArrayList<Triagem>();
		String sql = "SELECT * from tblalergia ";
		try {
			 con = Conexao.getConexao();
			 PreparedStatement preparador = con.prepareStatement(sql);
			 ResultSet rs = preparador.executeQuery();
			 while(rs.next())
			 {
				Triagem nv = new Triagem();
				nv.setAlergia(rs.getInt("id_alergia"));
				nv.setNomAlergia(rs.getString("alergia"));
				lNiveis.add(nv);		 
			 }
			 preparador.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		catch (Exception e) {e.getMessage();	}
	    finally{
					try{
						con.close();
					}
					catch(SQLException ef){
						System.out.println("Erro finalizar: "+ef);
					}
			}
		return lNiveis;
	}
	
	public List<Triagem> getProcedimentos(String termo)
	{
		List<Triagem> ls = new ArrayList<Triagem>();
		String sql = "SELECT * from tblprocedimentos where procedimento Like ?";
		try {
			 con = Conexao.getConexao();
			 PreparedStatement preparador = con.prepareStatement(sql);
			 preparador.setString(1, termo+"%");
			 ResultSet rs = preparador.executeQuery();
			 while(rs.next())
			 {
				Triagem nv = new Triagem();
				nv.setId_procedimento(rs.getInt("id_procedimento"));;
				nv.setProcedimento(rs.getString("procedimento"));;
				ls.add(nv);		 
			 }
			 preparador.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		catch (Exception e) {System.out.println(e.getMessage());	}
	    finally{
					try{
						con.close();
					}
					catch(SQLException ef){
						System.out.println("Erro finalizar: "+ef);
					}
			}
		return ls;
	}
	
	public Triagem getProcedimento(String termo)
	{
		Triagem nv = null;
		String sql = "SELECT * from tblprocedimentos where procedimento Like ?";
		try {
			 con = Conexao.getConexao();
			 PreparedStatement preparador = con.prepareStatement(sql);
			 preparador.setString(1, termo);
			 ResultSet rs = preparador.executeQuery();
			 if(rs.next())
			 {
				nv = new Triagem();
				nv.setId_procedimento(rs.getInt("id_procedimento"));
				nv.setProcedimento(rs.getString("procedimento"));	 
			 }
			 preparador.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		catch (Exception e) {System.out.println(e.getMessage());	}
	    finally{
					try{
						con.close();
					}
					catch(SQLException ef){
						System.out.println("Erro finalizar: "+ef);
					}
			}
		return nv;
	}
	public List<Triagem> getEmergenciasProcedimentos(int cod)
	{
		List<Triagem> lsProcedimento = new ArrayList<Triagem>();
		String sql = "SELECT * from vwemergenciaprocedimentos where fk_emergencia = ? group by id_emergencia_procedimento";
		try {
			 con = Conexao.getConexao();
			 PreparedStatement preparador = con.prepareStatement(sql);
			 preparador.setInt(1, cod);
			 ResultSet rs = preparador.executeQuery();
			 while(rs.next())
			 {
				Triagem nv = new Triagem();
				nv.setId_procedimento(rs.getInt("fk_procedimento"));
				nv.setId_emergencia(rs.getInt("fk_emergencia"));
				nv.setProcedimento(rs.getString("procedimento"));
				nv.setId_servico(rs.getInt("id_emergencia_procedimento"));
				nv.setDescricao(rs.getString("observacao"));
				lsProcedimento.add(nv);
			 }
			 preparador.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		catch (Exception e) {System.out.println(e.getMessage());	}
	    finally{
					try{
						con.close();
					}
					catch(SQLException ef){
						System.out.println("Erro finalizar: "+ef);
					}
			}
		return lsProcedimento;
	}
	public List<Triagem> getMateriais(String termo)
	{
		List<Triagem> lsMateiriais = new ArrayList<Triagem>();
		String sql = "SELECT * from tblmateriais where material Like ? ";
		try {
			 con = Conexao.getConexao();
			 PreparedStatement preparador = con.prepareStatement(sql);
			 preparador.setString(1, termo+"%");
			 ResultSet rs = preparador.executeQuery();
			 while(rs.next())
			 {
				Triagem nv = new Triagem();
				 
				nv.setMaterial(rs.getString("material"));
				 
				lsMateiriais.add(nv);		 
			 }
			 preparador.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		catch (Exception e) {e.getMessage();	}
	    finally{
					try{
						con.close();
					}
					catch(SQLException ef){
						System.out.println("Erro finalizar: "+ef);
					}
			}
		return lsMateiriais;
	}
	
	public List<Triagem> getEmergenciasMateriais(int cod)
	{
		List<Triagem> ls = new ArrayList<Triagem>();
		String sql = "SELECT * from vwemergenciamateriais where fk_emergencia = ? ";
		try {
			 con = Conexao.getConexao();
			 PreparedStatement preparador = con.prepareStatement(sql);
			 preparador.setInt(1, cod);
			 ResultSet rs = preparador.executeQuery();
			 while(rs.next())
			 {
				Triagem nv = new Triagem();
				nv.setId_material(rs.getInt("fk_material"));
				nv.setMaterial(rs.getString("material"));
				nv.setDescricao(rs.getString("observacao"));
				nv.setQuantidade(rs.getInt("quantidade"));
				nv.setId_servico(rs.getInt("id_emergencia_materiais"));
				ls.add(nv);		 
			 }
			 preparador.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		catch (Exception e) {e.getMessage();	}
	    finally{
					try{
						con.close();
					}
					catch(SQLException ef){
						System.out.println("Erro finalizar: "+ef);
					}
			}
		return ls;
	}
	
	public Triagem getMaterial(String termo)
	{
		Triagem nv = null;
		String sql = "SELECT * from tblmateriais where material Like ? ";
		try {
			 con = Conexao.getConexao();
			 PreparedStatement preparador = con.prepareStatement(sql);
			 preparador.setString(1, termo);
			 ResultSet rs = preparador.executeQuery();
			 while(rs.next())
			 {
				nv = new Triagem();
				nv.setId_material(rs.getInt("id_material"));;
				nv.setMaterial(rs.getString("material"));;
				 		 
			 }
			 preparador.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		catch (Exception e) {e.getMessage();	}
	    finally{
					try{
						con.close();
					}
					catch(SQLException ef){
						System.out.println("Erro finalizar: "+ef);
					}
			}
		return nv;
	}
	
	public List<Triagem> getMedicamentos(String termo)
	{
		List<Triagem> ls = new ArrayList<Triagem>();
		String sql = "SELECT * from tblmedicamentosbue where medicamento Like ? ";
		try {
			 con = Conexao.getConexao();
			 PreparedStatement preparador = con.prepareStatement(sql);
			 preparador.setString(1, termo+"%");
			 ResultSet rs = preparador.executeQuery();
			 while(rs.next())
			 {
				Triagem nv = new Triagem();
				nv.setId_medicamento(rs.getInt("id_medicamento"));
				nv.setMedicamento(rs.getString("medicamento"));
				ls.add(nv);		 
			 }
			 preparador.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		catch (Exception e) {e.getMessage();	}
	    finally{
					try{
						con.close();
					}
					catch(SQLException ef){
						System.out.println("Erro finalizar: "+ef);
					}
			}
		return ls;
	}
	
	public List<Triagem> getEmergenciasMedicamentos(int cod)
	{
		List<Triagem> lsMedicamentos = new ArrayList<Triagem>();
		String sql = "SELECT * from vwemergenciamedicamentos where fk_emergencia = ? ";
		try {
			 con = Conexao.getConexao();
			 PreparedStatement preparador = con.prepareStatement(sql);
			 preparador.setInt(1, cod);
			 ResultSet rs = preparador.executeQuery();
			 while(rs.next())
			 {
				Triagem nv = new Triagem();
				nv.setId_medicamento(rs.getInt("fk_medicamento"));
				nv.setMedicamento(rs.getString("medicamento"));
				nv.setQuantidade(rs.getInt("quantidade"));
				nv.setDescricao(rs.getString("observacao")); 
				nv.setId_servico(rs.getInt("id_emergencia_medicamento"));
				lsMedicamentos.add(nv);		 
			 }
			 preparador.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		catch (Exception e) {e.getMessage();	}
	    finally{
					try{
						con.close();
					}
					catch(SQLException ef){
						System.out.println("Erro finalizar: "+ef);
					}
			}
		return lsMedicamentos;
	}
	
	public Triagem getMedicamento(String termo)
	{
		Triagem nv = null; 
		String sql = "SELECT * from tblmedicamentosbue where medicamento Like ? ";
		try {
			 con = Conexao.getConexao();
			 PreparedStatement preparador = con.prepareStatement(sql);
			 preparador.setString(1, termo);
			 ResultSet rs = preparador.executeQuery();
			 while(rs.next())
			 {
				nv = new Triagem();
				nv.setId_medicamento(rs.getInt("id_medicamento"));
				nv.setMedicamento(rs.getString("medicamento"));
				 		 
			 }
			 preparador.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		catch (Exception e) {e.getMessage();	}
	    finally{
					try{
						con.close();
					}
					catch(SQLException ef){
						System.out.println("Erro finalizar: "+ef);
					}
			}
		return nv;
	}

	public int Emergencias(Triagem t) {
		int ultId = -1;
		String sql = "Insert into tblemergencia (FK_paciente,FK_medico,FK_temperatura,FK_pulso,FK_tensao_arterial, FK_tensao_arterial_dois,FK_respiracao,FK_nivel_consciencia,FK_alergia,queixas,medicamento_uso,peso,status,data_registo,hora_registo) values(?,?,?,?,?,?,?,?,?,?,?,?,?,?,?) ";
		try {
			 con = Conexao.getConexao();
			 PreparedStatement em = con.prepareStatement(sql);
			 em.setInt(1, t.getFK_paciente());
			 em.setInt(2, t.getFK_doutor());
			 em.setInt(3, t.getFk_temperatura());
			 em.setInt(4, t.getFk_pulso());
			 em.setInt(5, t.getFK_tensao_diastolica());
			 em.setInt(6, t.getFK_tensao_sistolica());
			 em.setInt(7, t.getFk_respiracao());
			 em.setInt(8, t.getId_nivel_consciencia());
			 em.setInt(9, t.getAlergia());
			 em.setString(10, t.getQueixa());
			 em.setString(11, t.getFarmaco());
			 em.setDouble(12, t.getPeso());
			 em.setString(13, "Observacao");
			 em.setDate(14, ft.data_registo());
			 em.setString(15, ft.getHoraAtual());
			 em.execute();
			 ResultSet rs = em.executeQuery("SELECT LAST_INSERT_ID()");
			 if(rs.next()){
				 ultId = rs.getInt(1); 
			 }
			 em.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		catch (Exception e) {e.getMessage();	}
	    finally{
					try{
						con.close();
					}
					catch(SQLException ef){
						System.out.println("Erro finalizar: "+ef);
					}
			}
		return ultId;
	}
	
	public void EmergenciasEdit(Triagem t) {
		 
		String sql = "update tblemergencia set FK_paciente = ?,FK_medico = ?,FK_temperatura = ?,FK_pulso = ?,FK_tensao_arterial = ?,FK_respiracao = ?,FK_nivel_consciencia = ?,FK_alergia = ?,queixas = ?,medicamento_uso = ?,peso = ?,status = ?,data_registo = ?,hora_registo = ? where id_emergencia = ? ";
		try {
			 con = Conexao.getConexao();
			 PreparedStatement em = con.prepareStatement(sql);
			 em.setInt(1, t.getFK_paciente());
			 em.setInt(2, t.getFK_doutor());
			 em.setInt(3, t.getFk_temperatura());
			 em.setInt(4, t.getFk_pulso());
			 em.setInt(5, t.getFK_tensao_sistolica());
			 em.setInt(6, t.getFk_respiracao());
			 em.setInt(7, t.getId_nivel_consciencia());
			 em.setInt(8, t.getAlergia());
			 em.setString(9, t.getQueixa());
			 em.setString(10, t.getFarmaco());
			 em.setDouble(11, t.getPeso());
			 em.setString(12, "Observacao");
			 em.setDate(13, ft.data_registo());
			 em.setString(14, ft.getHoraAtual());
			 em.setInt(15, t.getId_emergencia());
			 em.execute();
			 em.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		catch (Exception e) {e.getMessage();	}
	    finally{
					try{
						con.close();
					}
					catch(SQLException ef){
						System.out.println("Erro finalizar: "+ef);
					}
			}
		 
	}
	
	
	public void EmergenciasDoenca(Triagem t) {
		String sql = "Insert into tblemergenciadoenca (FK_emergencia,FK_doenca) values(?,?) ";
		try {
			 con = Conexao.getConexao();
			 PreparedStatement em = con.prepareStatement(sql);
			 em.setInt(1, t.getId_emergencia());
			 em.setInt(2, t.getFk_doenca());
			 em.execute();
			 em.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		catch (Exception e) {e.getMessage();	}
	    finally{
					try{
						con.close();
					}
					catch(SQLException ef){
						System.out.println("Erro finalizar: "+ef);
					}
			}
	}
	
	public void EmergenciasExcluirDoenca(Triagem t) {
		String sql = "Delete from tblemergenciadoenca where FK_emergencia= ? ";
		try {
			 con = Conexao.getConexao();
			 PreparedStatement em = con.prepareStatement(sql);
			 em.setInt(1, t.getId_emergencia());
			 em.execute();
			 em.close();
			 System.out.println("Doença Eliminada");
		} catch (SQLException e) {
			e.printStackTrace();
		}
		catch (Exception e) {e.getMessage();	}
	    finally{
					try{
						con.close();
					}
					catch(SQLException ef){
						System.out.println("Erro finalizar: "+ef);
					}
			}
	}
	public void EmergenciasProcedimento(Triagem t) {
		String sql = "Insert into tblemergenciaprocedimento (FK_emergencia,FK_procedimento) values(?,?) ";
		try {
			 con = Conexao.getConexao();
			 PreparedStatement em = con.prepareStatement(sql);
			 em.setInt(1, t.getId_emergencia());
			 em.setInt(2, t.getId_procedimento());
			 em.execute();
			 em.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		catch (Exception e) {e.getMessage();	}
	    finally{
					try{
						con.close();
					}
					catch(SQLException ef){
						System.out.println("Erro finalizar: "+ef);
					}
			}
	}
	
	public void EmergenciasExcluirProcedimento(int cod) {
		String sql = "Delete from tblemergenciaprocedimento where id_emergencia_procedimento = ? ";
		try {
			 con = Conexao.getConexao();
			 PreparedStatement em = con.prepareStatement(sql);
			 em.setInt(1, cod);
			 em.execute();
			 em.close();
			 System.out.println("Procedimento Eliminada");
		} catch (SQLException e) {
			e.printStackTrace();
		}
		catch (Exception e) {System.out.println(e.getMessage());;	}
	    finally{
					try{
						con.close();
					}
					catch(SQLException ef){
						System.out.println("Erro finalizar: "+ef);
					}
			}
	}
	
	public void EmergenciasProcedimentoObs(Triagem t) {
		String sql = "Insert into tblemergenciaprocedimentoobs (FK_emergencia,observacao) values(?,?) ";
		try {
			 con = Conexao.getConexao();
			 PreparedStatement em = con.prepareStatement(sql);
			 em.setInt(1, t.getId_emergencia());
			 em.setString(2, t.getDescricao());
			 em.execute();
			 em.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		catch (Exception e) {e.getMessage();	}
	    finally{
					try{
						con.close();
					}
					catch(SQLException ef){
						System.out.println("Erro finalizar: "+ef);
					}
			}
	}
	
	public void EmergenciasProcedimentoObsAtual(Triagem t) {
		String sql = "update tblemergenciaprocedimentoobs set observacao = ? where  FK_emergencia = ? ";
		try {
			 con = Conexao.getConexao();
			 PreparedStatement em = con.prepareStatement(sql);
			 em.setString(1, t.getDescricao());
			 em.setInt(2, t.getId_emergencia());
			 em.execute();
			 em.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		catch (Exception e) {e.getMessage();	}
	    finally{
					try{
						con.close();
					}
					catch(SQLException ef){
						System.out.println("Erro finalizar: "+ef);
					}
			}
	}
	
	 public void statusPaciente(Emergencia t){
			String sql = "Update tblemergencia set status = ?  where data_registo = curdate() and id_emergencia = ? and fk_paciente = ?";
			try {
				 con = Conexao.getConexao();
				 PreparedStatement tr = con.prepareStatement(sql);
				 tr.setString(1, t.getStatus());
				 tr.setInt(2, t.getId_emergencia());
				 tr.setInt(3, t.getFK_paciente());
				 tr.execute();
				 tr.close();
				 System.out.println("Emergencia...Status");
			}
			catch(Exception e){
				e.getMessage();
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
	 
	public void EmergenciasMateriais(Triagem t) {
		String sql = "Insert into tblemergenciamateriais (FK_emergencia,FK_material,quantidade) values(?,?,?) ";
		try {
			 con = Conexao.getConexao();
			 PreparedStatement em = con.prepareStatement(sql);
			 em.setInt(1, t.getId_emergencia());
			 em.setInt(2, t.getId_material());
			 em.setInt(3, t.getQuantidade());
			 em.execute();
			 em.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		catch (Exception e) {e.getMessage();	}
	    finally{
					try{
						con.close();
					}
					catch(SQLException ef){
						System.out.println("Erro finalizar: "+ef);
					}
			}
	}
	
	public void EmergenciasExcluirMaterial(int cod) {
		String sql = "Delete from tblemergenciamateriais where id_emergencia_materiais = ? ";
		try {
			 con = Conexao.getConexao();
			 PreparedStatement em = con.prepareStatement(sql);
			 em.setInt(1, cod);
			 em.execute();
			 em.close();
			 System.out.println("Material Eliminada");
		} catch (SQLException e) {
			e.printStackTrace();
		}
		catch (Exception e) {System.out.println(e.getMessage());;	}
	    finally{
					try{
						con.close();
					}
					catch(SQLException ef){
						System.out.println("Erro finalizar: "+ef);
					}
			}
	}
	
	public void EmergenciasMateriaisObs(Triagem t) {
		String sql = "Insert into tblemergenciamateriaisobs (FK_emergencia,observacao) values(?,?) ";
		try {
			 con = Conexao.getConexao();
			 PreparedStatement em = con.prepareStatement(sql);
			 em.setInt(1, t.getId_emergencia());
			 em.setString(2, t.getDescricao());
			 em.execute();
			 em.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		catch (Exception e) {e.getMessage();	}
	    finally{
					try{
						con.close();
					}
					catch(SQLException ef){
						System.out.println("Erro finalizar: "+ef);
					}
			}
	}
	
	public void EmergenciasMateriaisObsAtual(Triagem t) {
		String sql = "update tblemergenciamateriaisobs set observacao = ? where fk_emergencia = ?";
		try {
			 con = Conexao.getConexao();
			 PreparedStatement em = con.prepareStatement(sql);
			 em.setString(1, t.getDescricao());
			 em.setInt(2, t.getId_emergencia());
			 em.execute();
			 em.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		catch (Exception e) {e.getMessage();	}
	    finally{
					try{
						con.close();
					}
					catch(SQLException ef){
						System.out.println("Erro finalizar: "+ef);
					}
			}
	}
	
	public void EmergenciasMedicamentos(Triagem t) {
		String sql = "Insert into tblemergenciamedicamentos (FK_emergencia,FK_medicamento,quantidade) values(?,?,?) ";
		try {
			 con = Conexao.getConexao();
			 PreparedStatement em = con.prepareStatement(sql);
			 em.setInt(1, t.getId_emergencia());
			 em.setInt(2, t.getId_medicamento());
			 em.setInt(3, t.getQuantidade());
			 em.execute();
			 em.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		catch (Exception e) {e.getMessage();	}
	    finally{
					try{
						con.close();
					}
					catch(SQLException ef){
						System.out.println("Erro finalizar: "+ef);
					}
			}
	}
	
	public void EmergenciasExcluirMedicamento(int cod) {
		String sql = "Delete from tblemergenciamedicamentos where id_emergencia_medicamento = ? ";
		try {
			 con = Conexao.getConexao();
			 PreparedStatement em = con.prepareStatement(sql);
			 em.setInt(1, cod);
			 em.execute();
			 em.close();
			 System.out.println("Medicamento Eliminada");
		} catch (SQLException e) {
			e.printStackTrace();
		}
		catch (Exception e) {System.out.println(e.getMessage());;	}
	    finally{
					try{
						con.close();
					}
					catch(SQLException ef){
						System.out.println("Erro finalizar: "+ef);
					}
			}
	}
	public void EmergenciasMedicamentosObs(Triagem t) {
		String sql = "Insert into tblemergenciamedicamentosobs (FK_emergencia,observacao) values(?,?) ";
		try {
			 con = Conexao.getConexao();
			 PreparedStatement em = con.prepareStatement(sql);
			 em.setInt(1, t.getId_emergencia());
			 em.setString(2, t.getDescricao());
			 em.execute();
			 em.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		catch (Exception e) {e.getMessage();	}
	    finally{
					try{
						con.close();
					}
					catch(SQLException ef){
						System.out.println("Erro finalizar: "+ef);
					}
			}
	}
	
	public void EmergenciasMedicamentosObsAtual(Triagem t) {
		String sql = "Update tblemergenciamedicamentosobs set observacao =? where fk_emergencia = ? ";
		try {
			 con = Conexao.getConexao();
			 PreparedStatement em = con.prepareStatement(sql);
			 em.setString(1, t.getDescricao());
			 em.setInt(2, t.getId_emergencia());
			 em.execute();
			 em.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		catch (Exception e) {e.getMessage();	}
	    finally{
					try{
						con.close();
					}
					catch(SQLException ef){
						System.out.println("Erro finalizar: "+ef);
					}
			}
	}
	
	public int EmergenciasProcedimentosObsXeck(Triagem t) {
		int retor = 0;
		String sql = "select * from tblemergenciaprocedimentoobs where fk_emergencia = ? ";
		try {
			 con = Conexao.getConexao();
			 PreparedStatement em = con.prepareStatement(sql);
			 em.setInt(1, t.getId_emergencia());
			 ResultSet rs =  em.executeQuery();
			 if(rs.next()){
				 retor = 1;
			 }
			 em.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		catch (Exception e) {e.getMessage();	}
	    finally{
					try{
						con.close();
					}
					catch(SQLException ef){
						System.out.println("Erro finalizar: "+ef);
					}
			}
		return retor;
	}
	
	public int EmergenciasMateriaisObsXeck(Triagem t) {
		int retor = 0;
		String sql = "select * from tblemergenciamateriaisobs where fk_emergencia = ? ";
		try {
			 con = Conexao.getConexao();
			 PreparedStatement em = con.prepareStatement(sql);
			 em.setInt(1, t.getId_emergencia());
			 ResultSet rs =  em.executeQuery();
			 if(rs.next()){
				 retor = 1;
			 }
			 em.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		catch (Exception e) {e.getMessage();	}
	    finally{
					try{
						con.close();
					}
					catch(SQLException ef){
						System.out.println("Erro finalizar: "+ef);
					}
			}
		return retor;
	}
	
	public int EmergenciasMedicamentosObsXeck(Triagem t) {
		int retor = 0;
		String sql = "select * from tblemergenciamedicamentosobs where fk_emergencia = ? ";
		try {
			 con = Conexao.getConexao();
			 PreparedStatement em = con.prepareStatement(sql);
			 em.setInt(1, t.getId_emergencia());
			 ResultSet rs =  em.executeQuery();
			 if(rs.next()){
				 retor = 1;
			 }
			 em.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		catch (Exception e) {e.getMessage();	}
	    finally{
					try{
						con.close();
					}
					catch(SQLException ef){
						System.out.println("Erro finalizar: "+ef);
					}
			}
		return retor;
	}
	
	public void EmergenciasExcluirComoXegou(Emergencia e) {
		String sql = "Delete from tblemergenciadadoscomochegou where FK_emergencia = ? ";
		try {
			 con = Conexao.getConexao();
			 System.out.println("TEST: "+e.getId_emergencia());
			 PreparedStatement em = con.prepareStatement(sql);
			 em.setInt(1, e.getId_emergencia());
			 em.execute();
			 sql = "Delete from tblemergenciaresgateparente where FK_emergencia = ? ";
			 em = con.prepareStatement(sql);
			 em.setInt(1, e.getId_emergencia());
			 em.execute();
			 sql = "Delete from tblemergencianossaambulancia where FK_emergencia = ? ";
			 em = con.prepareStatement(sql);
			 em.setInt(1, e.getId_emergencia());
			 em.execute();
			 sql = "Delete from tblemergenciadesconhecido where FK_emergencia = ? ";
			 em = con.prepareStatement(sql);
			 em.setInt(1, e.getId_emergencia());
			 em.execute();
			 em.close();
			 System.out.println("Excluido Como Xegou ...");
		} catch (SQLException er) {
			er.printStackTrace();
		}
		catch (Exception er) {er.getMessage();	}
	    finally{
					try{
						con.close();
					}
					catch(SQLException ef){
						System.out.println("Erro finalizar: "+ef);
					}
			}
	}
	
	public void EmergenciasComoXegou(Emergencia e) {
		String sql = "Insert into tblemergenciadadoscomochegou (FK_emergencia,FK_como_chegou,chefe_brigada,unidade_pertence,matricula_carro) values(?,?,?,?,?) ";
		try {
			 con = Conexao.getConexao();
			 PreparedStatement em = con.prepareStatement(sql);
			 em.setInt(1, e.getId_emergencia());
			 em.setInt(2, e.getFk_cexegou());
			 em.setString(3, e.getRondate());
			 em.setString(4, e.getUnidade());
			 em.setString(5, e.getMatricula());
			 em.execute();
			 em.close();
			 System.out.println("Como Xegou Salvo...");
		} catch (SQLException er) {
			er.printStackTrace();
		}
		catch (Exception er) {er.getMessage();	}
	    finally{
					try{
						con.close();
					}
					catch(SQLException ef){
						System.out.println("Erro finalizar: "+ef);
					}
			}
	}
	
	public void EmergenciasXegouResgate(Emergencia e) {
		String sql = "Insert into tblemergenciaresgateparente (FK_emergencia,FK_como_chegou,FK_tipo_documento,numero_documento,nome) values(?,?,?,?,?) ";
		try {
			 con = Conexao.getConexao();
			 PreparedStatement em = con.prepareStatement(sql);
			 em.setInt(1, e.getId_emergencia());
			 em.setInt(2, e.getFk_cexegou());
			 em.setInt(3, e.getTipoDoc());
			 em.setString(4, e.getNumDocs());
			 em.setString(5, e.getRondate());
			 em.execute();
			 em.close();
			 System.out.println("Como Xegou Salvo...");
		} catch (SQLException er) {
			er.printStackTrace();
		}
		catch (Exception er) {er.getMessage();	}
	    finally{
					try{
						con.close();
					}
					catch(SQLException ef){
						System.out.println("Erro finalizar: "+ef);
					}
			}
	}
	
	public void EmergenciasXegouAmbulanacia(Emergencia e) {
		String sql = "Insert into tblemergencianossaambulancia (FK_emergencia,FK_como_chegou,matricula_ambulancia,nome) values(?,?,?,?) ";
		try {
			 con = Conexao.getConexao();
			 PreparedStatement em = con.prepareStatement(sql);
			 em.setInt(1, e.getId_emergencia());
			 em.setInt(2, e.getFk_cexegou()); 
			 em.setString(3, e.getMatricula());
			 em.setString(4, e.getRondate());
			 em.execute();
			 em.close();
			 System.out.println("Como Xegou Salvo...");
		} catch (SQLException er) {
			er.printStackTrace();
		}
		catch (Exception er) {er.getMessage();	}
	    finally{
					try{
						con.close();
					}
					catch(SQLException ef){
						System.out.println("Erro finalizar: "+ef);
					}
			}
	}
	
	public void EmergenciasXegouDesconhecido(Emergencia e) {
		String sql = "Insert into tblemergenciadesconhecido (FK_emergencia,FK_como_chegou,observacao) values(?,?,?) ";
		try {
			 con = Conexao.getConexao();
			 PreparedStatement em = con.prepareStatement(sql);
			 em.setInt(1, e.getId_emergencia());
			 em.setInt(2, e.getFk_cexegou()); 
			 em.setString(3, e.getDescricao());
			 em.execute();
			 em.close();
			 System.out.println("Como Xegou Salvo...");
		} catch (SQLException er) {
			er.printStackTrace();
		}
		catch (Exception er) {er.getMessage();	}
	    finally{
					try{
						con.close();
					}
					catch(SQLException ef){
						System.out.println("Erro finalizar: "+ef);
					}
			}
	}
	public Triagem buscarQueixas(int cod)
	{
		Triagem queixas = null;
		String sql = "SELECT * FROM vwemergenciaqueixas where id_emergencia = ?";
		try {
			con = Conexao.getConexao();
			PreparedStatement preparador = con.prepareStatement(sql);
			preparador.setInt(1, cod);
			ResultSet rs = preparador.executeQuery();
			if(rs.next()) {
				queixas = new Triagem();
				queixas.setId_emergencia(rs.getInt("id_emergencia"));
				queixas.setFK_alergia(rs.getInt("fK_alergia"));
				queixas.setNomAlergia(rs.getString("alergia"));
				queixas.setFK_nivel_consciencia(rs.getInt("fK_nivel_consciencia"));
				queixas.setNivel_consciencia(rs.getString("nivel_consciencia"));
				queixas.setFk_temperatura(rs.getInt("FK_temperatura"));
				queixas.setFk_pulso(rs.getInt("fk_pulso"));
				queixas.setFK_tensao_diastolica(rs.getInt("FK_tensao_arterial"));
				queixas.setFK_tensao_sistolica(rs.getInt("FK_tensao_arterial_dois"));
				queixas.setFk_respiracao(rs.getInt("FK_respiracao"));
				queixas.setQueixa(rs.getString("queixas"));
				queixas.setMedicamento(rs.getString("medicamento_uso"));
				queixas.setStatus(rs.getString("status"));
				queixas.setPeso(rs.getDouble("peso"));
				queixas.setFK_paciente(rs.getInt("fk_paciente"));
				 
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
		return queixas;
	}
	
	public Triagem buscarQueixasATHoje(int cod)
	{
		Triagem queixas = new Triagem();
		String sql = "SELECT * FROM vwemergenciapacientesatendidos where id_emergencia = ? and  data_registo = curdate()";
		try {
			con = Conexao.getConexao();
			PreparedStatement preparador = con.prepareStatement(sql);
			preparador.setInt(1, cod);
			ResultSet rs = preparador.executeQuery();
			if(rs.next()) {
				
				queixas.setId_emergencia(rs.getInt("id_emergencia"));
				queixas.setFK_alergia(rs.getInt("fK_alergia"));
				queixas.setNomAlergia(rs.getString("alergia"));
				queixas.setFK_nivel_consciencia(rs.getInt("fK_nivel_consciencia"));
				queixas.setNivel_consciencia(rs.getString("nivel_consciencia"));
				queixas.setFk_temperatura(rs.getInt("FK_temperatura"));
				queixas.setValor_tmp(rs.getString("temperatura"));
				queixas.setFk_pulso(rs.getInt("fk_pulso"));
				queixas.setValor_pulso(rs.getString("pulso"));
				queixas.setFK_tensao_sistolica(rs.getInt("FK_tensao_arterial"));
				queixas.setValor_tns(rs.getString("tensao")+" / "+rs.getString("tensao_dois"));
				queixas.setFk_respiracao(rs.getInt("FK_respiracao"));
				queixas.setValor_resp(rs.getString("respiracao"));
				queixas.setQueixa(rs.getString("queixas"));
				queixas.setMedicamento(rs.getString("medicamento_uso"));
				queixas.setStatus(rs.getString("status"));
				queixas.setPeso(rs.getDouble("peso"));
				queixas.setFK_paciente(rs.getInt("fk_paciente"));
				queixas.setNome(rs.getString("nomecompleto"));
				queixas.setNomem(rs.getString("nome")+" "+rs.getString("nomem"));
				queixas.setApelido(rs.getString("apelido"));
				queixas.setNumero_processo(rs.getString("numeroprocesso"));
				queixas.setGenero(rs.getString("genero"));
				queixas.setEstado("Emergencia");
				queixas.setIdade(rs.getDate("data_nascimento"));
				 
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
		return queixas;
	}
 
	public List<Triagem> buscarDoencas(int cod)
	{
		List<Triagem> lista = new ArrayList<Triagem>();
		String sql = "SELECT * FROM tblemergenciadoenca where FK_emergencia = ?";
		try {
			con = Conexao.getConexao();
			PreparedStatement preparador = con.prepareStatement(sql);
			preparador.setInt(1, cod);
			ResultSet rs = preparador.executeQuery();
			while(rs.next()) {				
				Triagem q = new Triagem();
				q.setFk_doenca(rs.getInt("FK_doenca"));
				lista.add(q);
//				System.out.println("OK");
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
	
	public Emergencia buscarComoXegou(int cod)
	{
		Emergencia ec = new Emergencia();
		int aux = 0;
		String sql = "SELECT * FROM tblemergenciadadoscomochegou where FK_emergencia = ?";
		try {
			con = Conexao.getConexao();
			PreparedStatement preparador = con.prepareStatement(sql);
			preparador.setInt(1, cod);
			ResultSet rs = preparador.executeQuery();
			if(rs.next()) {				
				 
				ec.setFk_cexegou(rs.getInt("FK_como_chegou"));
				ec.setRondate(rs.getString("chefe_brigada"));
				ec.setMatricula(rs.getString("matricula_carro"));
				ec.setUnidade(rs.getString("unidade_pertence"));
//				ec.setUnidade(unidade);
			}
			else {
				  sql = "SELECT * FROM tblemergenciaresgateparente where FK_emergencia = ?";
				  preparador = con.prepareStatement(sql);
				  preparador.setInt(1, cod);
				  rs = preparador.executeQuery();
					if(rs.next()) {				

						ec.setFk_cexegou(rs.getInt("FK_como_chegou"));
						ec.setRondate(rs.getString("nome"));
						ec.setNumDocs(rs.getString("numero_documento"));
						ec.setTipoDoc(rs.getInt("FK_tipo_documento"));
						aux = rs.getInt("FK_tipo_documento");
						if(aux == 1){ec.setNomeDoc("Bilhete de Identidade");}
						if(aux == 2){ec.setNomeDoc("Cedula Pessoal");}
						if(aux == 3){ec.setNomeDoc("Passaporte");}
						 
					}
					else {
						  sql = "SELECT * FROM tblemergencianossaambulancia where FK_emergencia = ?";
						  preparador = con.prepareStatement(sql);
						  preparador.setInt(1, cod);
						  rs = preparador.executeQuery();
							if(rs.next()) {				
								ec.setFk_cexegou(rs.getInt("FK_como_chegou"));
								ec.setRondate(rs.getString("nome"));
								ec.setMatricula(rs.getString("matricula_ambulancia"));
								
							}
							else {
								  sql = "SELECT * FROM tblemergenciadesconhecido where FK_emergencia = ?";
								  preparador = con.prepareStatement(sql);
								  preparador.setInt(1, cod);
								  rs = preparador.executeQuery();
									if(rs.next()) {					 
										ec.setFk_cexegou(rs.getInt("FK_como_chegou"));
										ec.setDescricao(rs.getString("observacao"));
									}
							}
					}
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
		return ec;
	}
}
