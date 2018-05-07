package ao.co.cligest.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

import ao.co.cligest.entidades.Cid;
import ao.co.cligest.entidades.Farmacos;
import ao.co.cligest.entidades.Paciente;
import ao.co.cligest.entidades.Servico;
import ao.co.cligest.entidades.Triagem;
import sun.security.action.GetIntegerAction;


/**
 * 
 * @author NELSON DIWIDI - ANGOTECH
 * @manutencao NELSON DIWIDI, ARIDJA ANDRE
 *
 */
public class UrgenciaDAO {

	private Connection con;
	Formatando ft = new Formatando();
	
	public List<Paciente> getPacienteUrgencia()
	{
		List <Paciente> lista = new ArrayList<Paciente>();
		String sql = "SELECT * from vwpacientesnobancoue";
		try {
			con = Conexao.getConexao();
			 PreparedStatement preparador = con.prepareStatement(sql);
			 ResultSet rs = preparador.executeQuery();
			 while(rs.next())
			 {
				Paciente p = new Paciente();
				p.setFK_paciente(rs.getInt("FK_paciente"));;
				p.setNumero_processo(rs.getString("numeroprocesso"));
				p.setNome(rs.getString("nomecompleto"));
				p.setIdade(rs.getDate("data_nascimento"));
				p.setHora_registo(rs.getString("hora_registo"));
				Calendar data = Calendar.getInstance();
				data.setTime(rs.getDate("data_registo"));
				p.setData_registo(data);
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

	

	public List<Triagem> getTriadosBancoUrgencia()
	{
		List <Triagem> lista = new ArrayList<Triagem>();
		String sql = "SELECT * from vwtriadosnobancourgencia where data_triagem = curdate()  ";
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
				p.setNomem(rs.getString("nome")+" "+rs.getString("ultimo_nome"));
				p.setIdade(rs.getDate("data_nascimento"));
				p.setHoraConsulta(rs.getString("hora_triagem"));
				p.setEstado(rs.getString("estado_do_paciente"));
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
	
	public List<Triagem> getTriadosParaUrgencia()
	{
		List <Triagem> lista = new ArrayList<Triagem>();
		String sql = "SELECT * from vwtriadosnobancourgencia  where data_triagem = curdate() and encaminhado not like 'Emergencia' and fk_paciente not in("
				   + "select fk_paciente from tblurgencia where data_registo = curdate() and status  <> 'Em Assistencia') ";
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
	
	public List<Triagem> getAtendidosNaUrgenciaHJ()
	{
		List <Triagem> lista = new ArrayList<Triagem>();
		String sql = "SELECT * from vwpacientesatendidoshojepraurgencia ";
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
	
	public Triagem getTriadosParaUrgencia(int cod)
	{
		Triagem p = new Triagem();
		String sql = "SELECT * from vwtriadosnobancourgencia,tblgenero where FK_paciente = ? and fk_genero = id_genero and data_triagem = curdate()";
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

	public int checkPacienteEnc(Paciente t){
		int aux = 0;
		String sql = "select * from tblpacientenobancourgencia where fk_paciente = ? and data_registo = curdate() ";
		try {
			 con = Conexao.getConexao();
			 PreparedStatement tr = con.prepareStatement(sql);
			 tr.setInt(1, t.getFK_paciente());
			 ResultSet rs = tr.executeQuery();
			 if(rs.next())
				 aux = 1;
				
			 tr.close();
			 System.out.println("Paciente ja encaminhado no BU - Triagem...");
		}
		catch(Exception e){
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
		return aux;
	}
	   public List <Paciente> buscarPorNome(String nome)
       {
			List <Paciente> lista = new ArrayList<Paciente>();
			String sql = "SELECT * FROM VWENTIDADECOMOPACIENTE WHERE NOME LIKE '"+nome+"%' OR ULTIMO_NOME LIKE '"+nome+"%' AND FK_entidade = ? ORDER BY (NOME)";
			try {
				con = Conexao.getConexao();
				PreparedStatement preparador = con.prepareStatement(sql);
				preparador.setString(1, nome);
				ResultSet rs = preparador.executeQuery();
				while (rs.next())
				 {
					 Paciente fun = new Paciente();
					 fun.setNome(rs.getString("nome"));
					 fun.setId_entidade(rs.getInt("FK_entidade"));
					 fun.setNumero_processo(rs.getString("numero_processo"));
					 fun.setNomem(rs.getString("nome_meio"));
					 fun.setApelido(rs.getString("ultimo_nome"));
					 Calendar data = Calendar.getInstance();
				     data.setTime(rs.getDate("data_nascimento"));
				     fun.setDataNasc(data);
				     fun.setIdade(rs.getDate("data_nascimento"));
					 fun.setTelefone(rs.getLong("telefone"));
					 fun.setEmail(rs.getString("email"));
					 fun.setEndereco(rs.getString("endereco"));
					 fun.setNumero_doc(rs.getString("numero_documento"));
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
       
       public List <Paciente> buscarPorNumProc(String nome)
       {
			List <Paciente> lista = new ArrayList<Paciente>();
			String sql = "SELECT * FROM VWENTIDADECOMOPACIENTE WHERE numero_processo LIKE ?";
			try {
				con = Conexao.getConexao();
				PreparedStatement preparador = con.prepareStatement(sql);
				preparador.setString(1, nome);
				ResultSet rs = preparador.executeQuery();
				while (rs.next())
				 {
					 Paciente fun = new Paciente();
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
					 fun.setNumero_doc(rs.getString("numero_documento"));
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
       
       public List <Paciente> buscarPorNumDoc(String nome)
       {
			List <Paciente> lista = new ArrayList<Paciente>();
			String sql = "SELECT * FROM VWENTIDADECOMOPACIENTE WHERE numero_documento LIKE ?";
			try {
				con = Conexao.getConexao();
				PreparedStatement preparador = con.prepareStatement(sql);
				preparador.setString(1, nome);
				ResultSet rs = preparador.executeQuery();
				while (rs.next())
				 {
					 Paciente fun = new Paciente();
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
					 fun.setNumero_doc(rs.getString("numero_documento"));
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

       public void addPacienteBancoDeUrgengia(Paciente pd)
		{
			String sql = "INSERT INTO tblpacientenobancourgencia(fk_paciente, data_registo, hora_registo, FK_funcionario,encaminhamento) VALUES (?,?,?,?,?)";
			try {
				con = Conexao.getConexao();
				PreparedStatement cp = con.prepareStatement(sql);
				cp.setInt(1, pd.getFK_paciente());
				cp.setDate(2, new Formatando().data_registo());
				cp.setString(3, new Formatando().horaAtual());
				cp.setInt(4, pd.getFK_funcionario());
				cp.setString(5, pd.getEncaminhados());
				cp.execute();
				cp.close();
				System.out.println("Cadastrado com sucesso..Paciente no banco de urgencia");
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
	
	public List<Paciente> buscarPacienteEncaminhar()
	{
		List<Paciente> lista = new ArrayList<Paciente>();
		String sql = "SELECT * FROM vwpacientesencaminhados where data_registo = curdate() order by hora_registo asc";
		try {
			con = Conexao.getConexao();
			PreparedStatement preparador = con.prepareStatement(sql);
			ResultSet rs = preparador.executeQuery();
			while(rs.next()) {
				Paciente fun = new Paciente();
				fun.setNumero_processo(rs.getString("numeroProcesso"));
				fun.setNome(rs.getString("nome"));
				fun.setNomem(rs.getString("nome_meio"));
				fun.setApelido(rs.getString("ultimo_nome"));
				Calendar data = Calendar.getInstance();
			    data.setTime(rs.getDate("data_registo"));
			    fun.setData_registo(data);
			    fun.setHora_registo(rs.getString("hora_registo"));
			    fun.setIdade_dopaciente(rs.getInt("idade"));
				fun.setEncaminhados(rs.getString("encaminhamento"));
				fun.setNomeCompleto(rs.getString("nomeCompleto"));
				lista.add(fun);
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
	
	public List<Cid> buscarHipotesesUrgencia(int cod)
	{
		List<Cid> lista = new ArrayList<Cid>();
		String sql = "SELECT * FROM vwcidshipotese where FK_urgencia = ? ";
		try {
			con = Conexao.getConexao();
			PreparedStatement preparador = con.prepareStatement(sql);
			preparador.setInt(1, cod);
			ResultSet rs = preparador.executeQuery();
			while(rs.next()) {
				Cid c = new Cid();
				c.setIdCid(rs.getInt("id_hipotese"));
				c.setDescricao(rs.getString("descricao_cid"));
				c.setCodigocid(rs.getString("cid"));
				lista.add(c);
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
	
	public List<Triagem> buscarExameClinico(int cod)
	{
		List<Triagem> lista = new ArrayList<Triagem>();
		String sql = "SELECT * FROM vwurgenciaexameclinico where id_urgencia = ? ";
		try {
			con = Conexao.getConexao();
			PreparedStatement preparador = con.prepareStatement(sql);
			preparador.setInt(1, cod);
			ResultSet rs = preparador.executeQuery();
			while(rs.next()) {
				Triagem c = new Triagem();
				c.setFK_urgencia(rs.getInt("id_exame_clinico"));
				c.setAnalise_clinica(rs.getString("analise_clinica"));
				lista.add(c);
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
	public void del_ExameClinico(int id)
	{
		String sql = "DELETE FROM tblurgenciaexameclinico  WHERE id_exame_clinico=?";
		try {
			con = Conexao.getConexao();
			 PreparedStatement preparador = con.prepareStatement(sql);
			 preparador.setInt(1, id);
			 preparador.execute();
			 preparador.close();
			 System.out.println("Excluido com sucesso..urgencia Exame clinico");
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
	public List<Servico> buscarExamesClinicoUrgencia(int cod)
	{
		List<Servico> lista = new ArrayList<Servico>();
		String sql = "SELECT * FROM vwurgenciaexameclinico where id_urgencia = ? ";
		try {
			con = Conexao.getConexao();
			PreparedStatement preparador = con.prepareStatement(sql);
			preparador.setInt(1, cod);
			ResultSet rs = preparador.executeQuery();
			while(rs.next()) {
				Servico c = new Servico();
				c.setId_servico(rs.getInt("id_exame_clinico"));
				c.setServico(rs.getString("analise_clinica"));
				lista.add(c);
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
	public List<Farmacos> buscarFarmacrosUrgencia(int cod)
	{
		List<Farmacos> lista = new ArrayList<Farmacos>();
		String sql = "SELECT * FROM vwfarmacosurgencia where FK_urgencia = ?";
		try {
			con = Conexao.getConexao();
			PreparedStatement preparador = con.prepareStatement(sql);
			preparador.setInt(1, cod);
			ResultSet rs = preparador.executeQuery();
			while(rs.next()) {
				Farmacos f = new Farmacos();
				f.setId_farmaco(rs.getInt("id_receita"));
				f.setFarmaco(rs.getString("nome_comercial"));
				f.setPosologia(rs.getString("posologia"));
				f.setQuantidade(rs.getInt("quantidade"));
				f.setDosagem(rs.getString("dosagem"));
				f.setVadministrar(rs.getString("viaadministrar"));
				lista.add(f);
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
	public Farmacos getFarmacos(String termo)
	{
		Farmacos nv = null;
		String sql = "SELECT * FROM vwfarmacosurgencia where FK_urgencia = ?";
		try {
			 con = Conexao.getConexao();
			 PreparedStatement preparador = con.prepareStatement(sql);
			 preparador.setString(1, termo);
			 ResultSet rs = preparador.executeQuery();
			 if(rs.next())
			 {
				 nv = new Farmacos();
				 nv.setId_farmaco(rs.getInt("id_receita"));
				 nv.setFarmaco(rs.getString("nome_comercial"));
				 nv.setPosologia(rs.getString("posologia"));
				 nv.setQuantidade(rs.getInt("quantidade"));
				 nv.setDosagem(rs.getString("dosagem"));
				 nv.setVadministrar(rs.getString("viaadministrar"));	 
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
//}
	
	public void triarBancoUrgencia(Triagem t){
		String sql = "Insert into tbltriagembancoue (FK_entidade,FK_pulso,FK_temperatura,FK_tensao_arterial,FK_tensao_arterial_dois,FK_respiracao,FK_estado_do_paciente,peso,altura,imc,observacao,situacao,encaminhado,data_triagem,hora_triagem,FK_funcionario) "
				+ "  values (?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?) ";
		try {
			 con = Conexao.getConexao();
			 PreparedStatement tr = con.prepareStatement(sql);
			 tr.setInt(1, t.getFK_paciente());
			 tr.setInt(2, t.getFk_pulso());
			 tr.setInt(3, t.getFk_temperatura());
			 tr.setInt(4, t.getFK_tensao_sistolica());
			 tr.setInt(5, t.getFK_tensao_diastolica());
			 tr.setInt(6, t.getFk_respiracao());
			 tr.setInt(7, t.getFK_estado_do_paciente());
			 tr.setDouble(8, t.getPeso());
			 tr.setDouble(9, t.getAltura());
			 tr.setDouble(10, t.getImc());
			 tr.setString(11, t.getDiagnostico_preliminar());
			 tr.setString(12, t.getSituacao());
			 tr.setString(13, t.getEncaminhar());
			 tr.setDate(14, ft.data_registo());
			 tr.setString(15, ft.getHoraAtual());
			 tr.setInt(16, t.getFk_funcionario());
			 tr.execute();
			 tr.close();
			 System.out.println("Salvo e Encaminhado");
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
	
	public void encaminharPaciente(Triagem t){
		String sql = "Update tblpacientenobancourgencia set encaminhamento = ?, hora_registo = ? where Fk_paciente = ? and data_registo = ? ";
		try {
			 con = Conexao.getConexao();
			 PreparedStatement tr = con.prepareStatement(sql);		
			 tr.setString(1, t.getEncaminhar());
			 tr.setString(2, ft.getHoraAtual());
			 tr.setInt   (3, t.getFK_paciente());
			 tr.setDate  (4, ft.data_registo());
			 tr.execute();
			 tr.close();
			 System.out.println("Encaminhada...Triagem");
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
	
	public void encaminharEmergencia(Triagem t){
		String sql = "Update tbltriagembancoue set encaminhado = ?,  FK_estado_do_paciente = ? where Fk_entidade = ? and data_triagem = CURDATE() ";
		try {
			 con = Conexao.getConexao();
			 PreparedStatement tr = con.prepareStatement(sql);		
			 tr.setString(1, t.getEncaminhar());
			 tr.setInt(2, 1);
			 tr.setInt   (3, t.getFK_paciente());
			 
			 tr.execute();
			 tr.close();
			 System.out.println("Encaminhada...Emergencia");
		}
		catch(Exception e){
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
	}
	
	public void situacaoPaciente(Triagem t){
		String sql = "Update tblpacientenobancourgencia set situacao = ?, hora_registo = ? where Fk_paciente = ? and data_registo = ? and id_urgencia = ? ";
		try {
			 con = Conexao.getConexao();
			 PreparedStatement tr = con.prepareStatement(sql);		
			 tr.setString(1, t.getStatus());
			 tr.setString(2, ft.getHoraAtual());
			 tr.setInt   (3, t.getFK_paciente());
			 tr.setDate  (4, ft.data_registo());
			 tr.setInt	 (5, t.getFK_urgencia());
			 tr.execute();
			 tr.close();
			 System.out.println("Encaminhada...Situacao");
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
	
	public void statusPaciente(Triagem t){
		String sql = "Update tblurgencia set status = ?, FK_medico = ?, hora_registo = ? where Fk_paciente = ? and data_registo = ? and id_urgencia = ?";
		try {
			 con = Conexao.getConexao();
			 PreparedStatement tr = con.prepareStatement(sql);
			 tr.setString(1, t.getStatus());		
			 tr.setInt 	 (2, t.getFK_doutor());
			 tr.setString(3, ft.getHoraAtual());
			 tr.setInt   (4, t.getFK_paciente());
			 tr.setDate  (5, ft.data_registo());
			 tr.setInt	 (6, t.getFK_urgencia());
			 tr.execute();
			 tr.close();
			 System.out.println("Encaminhada...Status");
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
	
	public List <Triagem> buscarNivelDeConsciencia()
	{
		List <Triagem> lista = new ArrayList<Triagem>();
		String sql = "SELECT * FROM tblnivelconsciencia";
		try{
			con = Conexao.getConexao();
			PreparedStatement preparador = con.prepareStatement(sql);
			ResultSet ag = preparador.executeQuery();
			while(ag.next())
			 {
				Triagem pn = new Triagem();
				pn.setId_nivel_consciencia(ag.getInt("id_nivel_consciencia"));
				pn.setNivel_consciencia(ag.getString("nivel_consciencia"));
			     lista.add(pn);
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
	
	public List <Triagem> buscarAlergia()
	{
		List <Triagem> lista = new ArrayList<Triagem>();
		String sql = "SELECT * FROM tblalergia";
		try{
			con = Conexao.getConexao();
			PreparedStatement preparador = con.prepareStatement(sql);
			ResultSet ag = preparador.executeQuery();
			while(ag.next())
			 {
				Triagem pn = new Triagem();
				pn.setId_alergia(ag.getInt("id_alergia"));
				pn.setNome_alergia(ag.getString("alergia"));
			     lista.add(pn);
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
	
	public void addUrgenciaQueixas(Triagem pd)
	{
		String sql = "INSERT INTO tblurgencia(FK_paciente,FK_medico,FK_alergia,FK_nivel_consciencia,queixa,medicamento,status,data_registo,hora_registo) VALUES (?,?,?,?,?,?,?,?,?)";
		try {
			con = Conexao.getConexao();
			PreparedStatement cp = con.prepareStatement(sql);
			cp.setInt(1, pd.getFK_paciente());
			cp.setInt(2, pd.getFK_medico());
			cp.setInt(3, pd.getFK_alergia());
			cp.setInt(4, pd.getFK_nivel_consciencia());
			cp.setString(5, pd.getQueixa());
			cp.setString(6, pd.getMedicamento());
			cp.setString(7, pd.getStatus());
			cp.setDate(8, new Formatando().data_registo());
			cp.setString(9, new Formatando().horaAtual());
			cp.execute();
			cp.close();
			System.out.println("Cadastrado com sucesso..urgencia Queixas");
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
	
	public int UrgenciaQueixas(Triagem pd)
	{
		int ultId = 0;
		String sql = "INSERT INTO tblurgencia(FK_paciente,FK_medico,FK_alergia,FK_nivel_consciencia,queixa,medicamento,status,data_registo,hora_registo) VALUES (?,?,?,?,?,?,?,?,?)";
		try {
			con = Conexao.getConexao();
			PreparedStatement cp = con.prepareStatement(sql);
			cp.setInt(1, pd.getFK_paciente());
			cp.setInt(2, pd.getFK_medico());
			cp.setInt(3, pd.getFK_alergia());
			cp.setInt(4, pd.getFK_nivel_consciencia());
			cp.setString(5, pd.getQueixa());
			cp.setString(6, pd.getMedicamento());
			cp.setString(7, pd.getStatus()); 
			cp.setDate(8, new Formatando().data_registo());
			cp.setString(9, new Formatando().horaAtual());
			cp.execute();
			ResultSet rs = cp.executeQuery("SELECT LAST_INSERT_ID()");
			 if(rs.next()){
				 ultId = rs.getInt(1); 
			 }
			cp.close();
			System.out.println("Cadastrado com sucesso..Urgencia Queixas");
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
		return ultId;
	}
	public Triagem buscarUrgenciaQueixas(int cod)
	{
		Triagem pd = null;
		String sql = "SELECT * FROM tblurgencia WHERE id_urgencia =?";
		try {
			con = Conexao.getConexao();
			PreparedStatement preparador = con.prepareStatement(sql);
			preparador.setInt(1, cod);
			 ResultSet rs = preparador.executeQuery();
			 while(rs.next())
			 {
				 pd = new Triagem();
				 pd.setFK_alergia(rs.getInt("fK_alergia"));
				 pd.setFK_nivel_consciencia(rs.getInt("fK_nivel_consciencia"));
				 pd.setQueixa(rs.getString("queixa"));
				 pd.setMedicamento(rs.getString("medicamento"));
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
		return pd;
	}

	public void alt_queixas(Triagem ins)
	{
		String sql = "UPDATE tblurgencia set FK_alergia = ?, FK_nivel_consciencia = ?,queixa = ?, medicamento =? where id_urgencia = ?";
		try {
			con = Conexao.getConexao();
			PreparedStatement cp = con.prepareStatement(sql);
			
			cp.setInt(1, ins.getFK_alergia());
			cp.setInt(2, ins.getFK_nivel_consciencia());
			cp.setString(3, ins.getQueixa());
			cp.setString(4, ins.getMedicamento());
			cp.setInt	(5, ins.getFK_urgencia());
			cp.execute();
			cp.close();
			System.out.println("As queixas foram actualizadas com sucesso...!");
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
	public void UrgenciasDoenca(Triagem t) {
		String sql = "Insert into tblurgenciadoenca (FK_urgencia,FK_doenca) values(?,?) ";
		try {
			 con = Conexao.getConexao();
			 PreparedStatement em = con.prepareStatement(sql);
			 em.setInt(1, t.getFK_urgencia());
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
	public void alt_doencas(Triagem ins)
	{
		String sql = "UPDATE tblurgenciadoenca set FK_doenca = ? where FK_urgencia = ?";
		try {
			con = Conexao.getConexao();
			PreparedStatement cp = con.prepareStatement(sql);
			
			cp.setInt(1, ins.getFk_doenca());
			cp.setInt(2, ins.getFK_urgencia());
			cp.execute();
			cp.close();
			System.out.println("As doen�as foram actualizadas.....!!");
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
	public void urgenciaExcluirDoenca(Triagem t) {
		String sql = "Delete from tblurgenciadoenca where FK_urgencia= ? ";
		try {
			 con = Conexao.getConexao();
			 PreparedStatement em = con.prepareStatement(sql);
			 em.setInt(1, t.getFk_doenca());
			 em.execute();
			 em.close();
			 System.out.println("Doen�a Eliminada");
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
	public Triagem getHipoteses(String termo)
	{
		Triagem nv = null;
		String sql = "SELECT * from tblcid where descricao_cid Like ?";
		try {
			 con = Conexao.getConexao();
			 PreparedStatement preparador = con.prepareStatement(sql);
			 preparador.setString(1, termo);
			 ResultSet rs = preparador.executeQuery();
			 if(rs.next())
			 {
				nv = new Triagem();
				nv.setFK_cid(rs.getInt("id_cid"));
				nv.setDescricao(rs.getString("descricao_cid"));	 
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
	
	public List<Triagem> getSinaisUrgencia(int cod)
	{
		List<Triagem> lista = new ArrayList<Triagem>();
		String sql = "SELECT * from vwurgenciasinaivitais where fk_paciente = ? and data_triagem = curdate()";
		try {
			 con = Conexao.getConexao();
			 PreparedStatement preparador = con.prepareStatement(sql);
			 preparador.setInt(1, cod);
			 ResultSet rs = preparador.executeQuery();
			 while(rs.next())
			 {
				    Triagem p = new Triagem();
					p.setNome_doutor(rs.getString("funcionario"));
					p.setHoraConsulta(rs.getString("hora_triagem"));
					lista.add(p);
			 }
			 preparador.close();
		} catch (SQLException e) {
			System.out.println(e.getMessage());
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
		return lista;
	}
	
	public Triagem getSinaisUrgencia(int cod,String hora)
	{
		Triagem p = new Triagem();
		String sql = "SELECT * from vwurgenciasinaivitais where fk_paciente = ? and hora_triagem = ? and data_triagem = curdate()";
		try {
			 con = Conexao.getConexao();
			 PreparedStatement preparador = con.prepareStatement(sql);
			 preparador.setInt(1, cod);
			 preparador.setString(2, hora);
			 ResultSet rs = preparador.executeQuery();
			 if(rs.next())
			 {			    
					p.setEstado(rs.getString("estado_do_paciente"));
					p.setValor_pulso(rs.getString("pulso"));
					p.setValor_resp(rs.getString("respiracao"));
					p.setValor_tmp(rs.getString("temperatura"));
					p.setValor_tns(rs.getString("tensao"));
					p.setPeso(rs.getDouble("peso"));
					p.setAltura(rs.getDouble("altura"));
					p.setImc(rs.getDouble("imc"));
					p.setDiagnostico_preliminar(rs.getString("observacao")); 					
			 }
			 preparador.close();
		} catch (SQLException e) {
			System.out.println(e.getMessage());
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
		return p;
	}
	
	public void UrgenciaHipoteses(Triagem pd)
	{
		String sql = "INSERT INTO tblurgenciahipotese(FK_urgencia,FK_cid) VALUES (?,?)";
		try {
			con = Conexao.getConexao();
			PreparedStatement cp = con.prepareStatement(sql);
			cp.setInt(1, pd.getFK_urgencia());
			cp.setInt(2, pd.getFK_cid());
			cp.execute();
			cp.close();
			System.out.println("Cadastrado com sucesso..urgencia Hipoteses");
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
	
	public List <Triagem> buscarHipoteses(int cod)
	{
		List <Triagem> lista = new ArrayList<Triagem>();
		String sql = "SELECT * FROM tblcid where id_cid =?";
		try{
			con = Conexao.getConexao();
			PreparedStatement preparador = con.prepareStatement(sql);
			preparador.setInt(1, cod);
			ResultSet ag = preparador.executeQuery();
			while(ag.next())
			 {
				Triagem pn = new Triagem();
				pn.setFK_cid(ag.getInt("id_cid"));
//				pn.setCid(ag.getString("cid"));
				pn.setDescricao(ag.getString("descricao_cid"));
			     lista.add(pn);
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
	
	//3-Eliminar Servi�o
	public void del_hipoteses(int id)
	{
		String sql = "DELETE FROM tblurgenciahipotese  WHERE id_hipotese=?";
		try {
			con = Conexao.getConexao();
			 PreparedStatement preparador = con.prepareStatement(sql);
			 preparador.setInt(1, id);
			 preparador.execute();
			 preparador.close();
			 System.out.println("Excluido com sucesso..urgencia Hipoteses");
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
	public void UrgenciaDiagnostico(Triagem pd)
	{
		String sql = "INSERT INTO tblurgenciadiagnostico(FK_urgencia,FK_cid) VALUES (?,?)";
		try {
			con = Conexao.getConexao();
			PreparedStatement cp = con.prepareStatement(sql);
			cp.setInt(1, pd.getFK_urgencia());
			cp.setInt(2, pd.getFK_cid());
			cp.execute();
			cp.close();
			System.out.println("Cadastrado com sucesso..urgencia Diagnostico");
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
	public List<Cid> buscarUrgenciaDiagnostico(int cod)
	{
		List<Cid> lista = new ArrayList<Cid>();
		String sql = "SELECT * FROM vwurgenciadiagnosticos where id_urgencia = ? ";
		try {
			con = Conexao.getConexao();
			PreparedStatement preparador = con.prepareStatement(sql);
			preparador.setInt(1, cod);
			ResultSet rs = preparador.executeQuery();
			while(rs.next()) {
				Cid c = new Cid();
				c.setIdCid(rs.getInt("id_diagnostico"));
				c.setFK_paciente(rs.getInt("id_urgencia"));
				c.setDescricao(rs.getString("descricao_cid"));
				c.setCodigocid(rs.getString("cid"));
				lista.add(c);
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
	public void del_diagnostico(int id)
	{
		String sql = "DELETE FROM tblurgenciadiagnostico  WHERE id_diagnostico=?";
		try {
			con = Conexao.getConexao();
			 PreparedStatement preparador = con.prepareStatement(sql);
			 preparador.setInt(1, id);
			 preparador.execute();
			 preparador.close();
			 System.out.println("Excluido com sucesso..urgencia Diagnostico");
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
	 public int buscarIdCid() {
			int id = 0;
			String sql = "SELECT * FROM tblcid ";
			try {
				con = Conexao.getConexao();
				PreparedStatement preparador = con.prepareStatement(sql);
				ResultSet rs = preparador.executeQuery();
				if (rs.next())
					id = rs.getInt("id_cid");
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
	 
	 public int buscarIdProduto() {
			int id = 0;
			String sql = "SELECT * FROM tblprodutofarmaceuticos ";
			try {
				con = Conexao.getConexao();
				PreparedStatement preparador = con.prepareStatement(sql);
				ResultSet rs = preparador.executeQuery();
				while (rs.next())
					id = rs.getInt("id_produto");
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
	
	public List <Triagem> getExamesClinico(String nome,String grupExame)
	{
		List <Triagem> lista = new ArrayList<Triagem>();
		String sql = "SELECT * FROM tblservicosdeexamesclinicos WHERE analise_clinica Like ? and FK_grupo_analise_clinica = ?";
		try{
			con = Conexao.getConexao();
			PreparedStatement preparador = con.prepareStatement(sql);
			preparador.setString(1, nome+"%");
			preparador.setString(2, grupExame);
			ResultSet ag = preparador.executeQuery();
			while(ag.next())
			 {
				Triagem pn = new Triagem();
				pn.setId_servico(ag.getInt("id_servicodeanalise_clinica"));
				pn.setAnalise_clinica(ag.getString("analise_clinica"));
			    lista.add(pn);
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
	
	public void addUrgenciaExames(Triagem pd)
	{
		String sql = "INSERT INTO tblurgenciaexameclinico(FK_urgencia,FK_servico,FK_requisicao_dexame) VALUES (?,?,?)";
		try {
			con = Conexao.getConexao();
			PreparedStatement cp = con.prepareStatement(sql);
			cp.setInt(1, pd.getFK_urgencia());
			cp.setInt(2, pd.getFK_servico());
			cp.setInt(3, pd.getFK_numero_requisicao());
			cp.execute();
			cp.close();
			System.out.println("Cadastrado com sucesso..urgencia Exames");
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
	
	public Triagem getExameClinico(String termo)
	{
		Triagem nv = null;
		String sql = "SELECT * from tblservicosdeexamesclinicos where analise_clinica Like ?";
		try {
			 con = Conexao.getConexao();
			 PreparedStatement preparador = con.prepareStatement(sql);
			 preparador.setString(1, termo);
			 ResultSet rs = preparador.executeQuery();
			 if(rs.next())
			 {
				nv = new Triagem();
				nv.setId_servicodeanalise_clinica(rs.getInt("id_servicodeanalise_clinica"));
				nv.setAnalise_clinica(rs.getString("analise_clinica"));	 
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
	
	public void addUrgenciaRecetuarios(Triagem pd)
	{
		String sql = "INSERT INTO tblurgenciareceituario(FK_urgencia,FK_produto,FK_dosagem,FK_viadministrar,quantidade,posologia) VALUES (?,?,?,?,?,?)";
		try {
			con = Conexao.getConexao();
			PreparedStatement cp = con.prepareStatement(sql);
			cp.setInt(1, pd.getFK_urgencia());
			cp.setInt(2, pd.getFK_produto());
			cp.setInt(3, pd.getFk_dosagem());
			cp.setInt(4, pd.getFK_viadministrar());
			cp.setInt(5, pd.getQuantidade());
			cp.setString(6, pd.getPosologia());
			cp.execute();
			cp.close();
			System.out.println("Cadastrado com sucesso..urgencia Receituario");
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
	public void del_Receituario(int id)
	{
		String sql = "DELETE FROM tblurgenciareceituario  WHERE id_receita=?";
		try {
			con = Conexao.getConexao();
			 PreparedStatement preparador = con.prepareStatement(sql);
			 preparador.setInt(1, id);
			 preparador.execute();
			 preparador.close();
			 System.out.println("Excluido com sucesso..urgencia Receituario");
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
	 public void addUrgenciaRecomendacoes(Triagem pd)
		{
			String sql = "INSERT INTO tblurgenciarecomendacao(FK_urgencia,recomendacao) VALUES (?,?)";
			try {
				con = Conexao.getConexao();
				PreparedStatement cp = con.prepareStatement(sql);
				cp.setInt(1, pd.getFK_urgencia());
				cp.setString(2, pd.getRecomendacao());
				cp.execute();
				cp.close();
				System.out.println("Cadastrado com sucesso..Recomenda��es");
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
	 public Triagem buscarQueixas(int cod)
		{
		 Triagem queixas = new Triagem();
			String sql = "SELECT * FROM vwurgenciaqueixas where id_urgencia = ?";
			try {
				con = Conexao.getConexao();
				PreparedStatement preparador = con.prepareStatement(sql);
				preparador.setInt(1, cod);
				ResultSet rs = preparador.executeQuery();
				if(rs.next()) {
					
					queixas.setFK_urgencia(rs.getInt("id_urgencia"));
					queixas.setFK_alergia(rs.getInt("fK_alergia"));
					queixas.setNomAlergia(rs.getString("alergia"));
					queixas.setFK_nivel_consciencia(rs.getInt("fK_nivel_consciencia"));
					queixas.setNivel_consciencia(rs.getString("nivel_consciencia"));
					queixas.setQueixa(rs.getString("queixa"));
					queixas.setMedicamento(rs.getString("medicamento"));
					queixas.setStatus(rs.getString("status"));
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
	 
	 public List<Triagem> buscarDoencas(int cod)
		{
			List<Triagem> lista = new ArrayList<Triagem>();
			String sql = "SELECT * FROM tblurgenciadoenca where fk_urgencia =?";
			try {
				con = Conexao.getConexao();
				PreparedStatement preparador = con.prepareStatement(sql);
				preparador.setInt(1, cod);
				ResultSet rs = preparador.executeQuery();
				while(rs.next()) {				
					Triagem q = new Triagem();
					q.setFk_doenca(rs.getInt("FK_doenca"));
					lista.add(q);
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
	 public List<Triagem> getPacienteAtendidoHojeUrgenciaAtualizado()
		{
			List <Triagem> lista = new ArrayList<Triagem>();
			String sql = "select * from vwtriagemdaurgencia";
			try {
				con = Conexao.getConexao();
				 PreparedStatement preparador = con.prepareStatement(sql);
				 ResultSet rs = preparador.executeQuery();
				 while(rs.next())
				 {
					 Triagem p = new Triagem();
					p.setFK_urgencia(rs.getInt("id_urgencia"));
					p.setNome(rs.getString("nomecompleto"));
					p.setIdade(rs.getDate("data_nascimento"));
					p.setHoraConsulta(rs.getString("hora_triagem"));
//					p.setEstado(rs.getString("estado_paciente"));
					p.setFK_estado_do_paciente(rs.getInt("estado_paciente"));
					p.setStatus(rs.getString("status"));
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
	 public List<Triagem> getPacienteAtendidoHojeUrgencia()
		{
			List <Triagem> lista = new ArrayList<Triagem>();
			String sql = "select tbue.situacao as situacao, cpc.numeroprocesso as numeroprocesso, cpc.NomeCompleto as nomecompleto, cpc.data_nascimento as data_nascimento, tbue.fk_estado_do_paciente as estado_paciente, tbue.fk_entidade as fk_entidade, urgc.id_urgencia as id_urgencia, urgc.data_registo as data_triagem,urgc.hora_registo as hora_triagem from (select * from tbltriagembancoue ORDER BY id_triagem_bancoue DESC) AS tbue JOIN tblurgencia urgc on urgc.FK_paciente = tbue.fk_entidade JOIN vwcidadaopacientenomecompleto cpc ON cpc.Entidade = tbue.FK_entidade where data_triagem = curdate() and urgc.data_registo = curdate() GROUP BY tbue.FK_entidade";
			try {
				con = Conexao.getConexao();
				 PreparedStatement preparador = con.prepareStatement(sql);
				 ResultSet rs = preparador.executeQuery();
				 while(rs.next())
				 {
					 Triagem p = new Triagem();
					p.setFK_urgencia(rs.getInt("id_urgencia"));
					p.setFK_paciente(rs.getInt("FK_entidade"));
					p.setNumero_processo(rs.getString("numeroprocesso"));
					p.setNome(rs.getString("nomecompleto"));
					p.setIdade(rs.getDate("data_nascimento"));
					p.setHoraConsulta(rs.getString("hora_triagem"));
					//p.setEstado(rs.getString("estado_paciente"));
					p.setFK_estado_do_paciente(rs.getInt("estado_paciente"));
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
	 
	 public Triagem getPacienteAtendidoHojeUrgencia(int cod)
		{
			Triagem p = new Triagem();
			String sql = "SELECT * from vwpacientesatendidoshojepraurgencia where id_urgencia = ?";
			try {
				con = Conexao.getConexao();
				 PreparedStatement preparador = con.prepareStatement(sql);
				 preparador.setInt(1, cod);
				 ResultSet rs = preparador.executeQuery();
				 if(rs.next())
				 {
					p.setFK_urgencia(rs.getInt("id_urgencia"));
					p.setFK_paciente(rs.getInt("FK_paciente"));;
					p.setNumero_processo(rs.getString("numeroprocesso"));
					p.setNome(rs.getString("nomecompleto"));
					p.setIdade(rs.getDate("data_nascimento"));
					p.setEstado(rs.getString("estado_do_paciente"));
					p.setValor_pulso(rs.getString("pulso"));
					p.setValor_resp(rs.getString("respiracao"));
					p.setValor_tmp(rs.getString("temperatura"));
					p.setValor_tns(rs.getString("tensao_arterial"));
					p.setPeso(rs.getDouble("peso"));
					p.setAltura(rs.getDouble("altura"));
					p.setImc(rs.getDouble("imc"));
					p.setDiagnostico_preliminar(rs.getString("observacao")); 
					p.setGenero(rs.getString("genero"));
					p.setStatus(rs.getString("status"));
					p.setFK_doutor(rs.getInt("FK_medico"));
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



	public Triagem buscarRecomendacao(int cod) {
		Triagem rc = new Triagem();
		String sql = "SELECT * from tblurgenciarecomendacao where fk_urgencia = ?";
		try {
			 con = Conexao.getConexao();
			 PreparedStatement preparador = con.prepareStatement(sql);
			 preparador.setInt(1, cod);
			 ResultSet rs = preparador.executeQuery();
			 if(rs.next())
			 {
				rc.setId_urgencia_recomendacao(rs.getInt("id_urgencia_recomendacao"));
				rc.setRecomendacao(rs.getString("recomendacao"));
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
		return rc;
	}
	public void alt_recomenda(Triagem ins)
	{
		String sql = "UPDATE tblurgenciarecomendacao set recomendacao = ? where FK_urgencia = ?";
		try {
			con = Conexao.getConexao();
			PreparedStatement cp = con.prepareStatement(sql);
			
			cp.setString(1, ins.getRecomendacao());
			cp.setInt(2, ins.getFK_urgencia());
			cp.execute();
			cp.close();
			System.out.println("Update recomendacao com sucesso..!");
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
	
	public int checkPacienteUrg(String us){
		int u = Integer.parseInt(us);
		String sql = "Select *from tblurgencia where FK_paciente = ? and data_registo = curdate() and status <> 'Finalizado' ";
		int codU = 0;
		try {
			con = Conexao.getConexao();
			PreparedStatement ps = con.prepareStatement(sql);
			ps.setInt(1, u);
			ResultSet rs = ps.executeQuery();
			if(rs.next()){
				codU = rs.getInt("id_urgencia");
			}
			ps.close();
			con.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return codU;
	}
	public List<Triagem> buscarDiagnosticos(int fkPac) {
		// TODO Auto-generated method stub
		return null;
	}
	 
	}
