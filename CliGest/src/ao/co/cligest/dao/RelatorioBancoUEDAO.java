package ao.co.cligest.dao;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

import ao.co.cligest.entidades.Farmacos;
import ao.co.cligest.entidades.Paciente;
import ao.co.cligest.entidades.Triagem;

public class RelatorioBancoUEDAO extends UrgenciaDAO{

	private Connection con;
	
	public List<Triagem> getTriadosBancoUrgencia()
	{
		List <Triagem> lista = new ArrayList<Triagem>();
		String sql = "SELECT * from vwtriadosnobancourgencia where data_triagem = curdate() order by data_triagem desc  ";
		try {
			 con = Conexao.getConexao();
			 PreparedStatement preparador = con.prepareStatement(sql);
			 ResultSet rs = preparador.executeQuery();
			 while(rs.next())
			 {
				 Triagem p = new Triagem();
				p.setFK_paciente(rs.getInt("FK_paciente"));;
				p.setId_triagem(rs.getInt("id_triagem_bancoue"));
				p.setNumero_processo(rs.getString("numeroprocesso"));
				p.setNome(rs.getString("nomecompleto"));
				p.setIdade(rs.getDate("data_nascimento"));
				p.setHoraConsulta(rs.getString("hora_triagem"));
				p.setEstado(rs.getString("estado_do_paciente"));
				p.setEncaminhar(rs.getString("encaminhado"));
				p.setSituacao(rs.getString("situacao"));
				Calendar data = Calendar.getInstance();
				data.setTime(rs.getDate("data_triagem"));
				p.setDataConsulta(data);
				if(rs.getInt("fk_genero")==1){
					p.setGenero("Masculino");
				}
				if(rs.getInt("fk_genero")==2){
					p.setGenero("Feminino");
				}
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
	
	public List<Triagem> getTriadosBancoUrgencia(Date data_in,Date data_fi)
	{
		List <Triagem> lista = new ArrayList<Triagem>();
		String sql = "SELECT * from vwtriadosnobancourgencia where data_triagem between ? and ? order by data_triagem desc  ";
		try {
			 con = Conexao.getConexao();
			 PreparedStatement preparador = con.prepareStatement(sql);
			 preparador.setDate(1, data_in);
			 preparador.setDate(2, data_fi);
			 ResultSet rs = preparador.executeQuery();
			 while(rs.next())
			 {
				 Triagem p = new Triagem();
				p.setFK_paciente(rs.getInt("FK_paciente"));;
				p.setId_triagem(rs.getInt("id_triagem_bancoue"));
				p.setNumero_processo(rs.getString("numeroprocesso"));
				p.setNome(rs.getString("nomecompleto"));
				p.setIdade(rs.getDate("data_nascimento"));
				p.setHoraConsulta(rs.getString("hora_triagem"));
				p.setEstado(rs.getString("estado_do_paciente"));
				p.setEncaminhar(rs.getString("encaminhado"));
				p.setSituacao(rs.getString("situacao"));
				Calendar data = Calendar.getInstance();
				data.setTime(rs.getDate("data_triagem"));
				p.setDataConsulta(data);
				if(rs.getInt("fk_genero")==1){
					p.setGenero("Masculino");
				}
				if(rs.getInt("fk_genero")==2){
					p.setGenero("Feminino");
				}
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
	
	public List<Triagem> getTriadosBancoUrgencia(Date data_in,Date data_fi,String aux)
	{
		List <Triagem> lista = new ArrayList<Triagem>();
		String sql = "SELECT * from vwtriadosnobancourgencia where data_triagem between ? and ?  and nomecompleto like ? order by data_triagem desc  ";
		try {
			 con = Conexao.getConexao();
			 PreparedStatement preparador = con.prepareStatement(sql);
			 preparador.setDate(1, data_in);
			 preparador.setDate(2, data_fi);
			 preparador.setString(3, aux+"%");
			 ResultSet rs = preparador.executeQuery();
			 while(rs.next())
			 {
				 Triagem p = new Triagem();
				p.setId_triagem(rs.getInt("id_triagem_bancoue"));
				p.setFK_paciente(rs.getInt("FK_paciente"));;
				p.setNumero_processo(rs.getString("numeroprocesso"));
				p.setNome(rs.getString("nomecompleto"));
				p.setIdade(rs.getDate("data_nascimento"));
				p.setHoraConsulta(rs.getString("hora_triagem"));
				p.setEstado(rs.getString("estado_do_paciente"));
				p.setEncaminhar(rs.getString("encaminhado"));
				p.setSituacao(rs.getString("situacao"));
				Calendar data = Calendar.getInstance();
				data.setTime(rs.getDate("data_triagem"));
				p.setDataConsulta(data);
				if(rs.getInt("fk_genero")==1){
					p.setGenero("Masculino");
				}
				if(rs.getInt("fk_genero")==2){
					p.setGenero("Feminino");
				}
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
	
	public List<Triagem> getTriadosBancoUrgencia(Date data_in,Date data_fi,int id1,int id2)
	{
		List <Triagem> lista = new ArrayList<Triagem>();
		String sql = "SELECT * from vwtriadosnobancourgencia where data_triagem between ? and ?  and idade between ? and ? order by data_triagem desc  ";
		try {
			 con = Conexao.getConexao();
			 PreparedStatement preparador = con.prepareStatement(sql);
			 preparador.setDate(1, data_in);
			 preparador.setDate(2, data_fi);
			 preparador.setInt(3, id1);
			 preparador.setInt(4, id2);
			 ResultSet rs = preparador.executeQuery();
			 while(rs.next())
			 {
				Triagem p = new Triagem();
				p.setId_triagem(rs.getInt("id_triagem_bancoue"));
				p.setFK_paciente(rs.getInt("FK_paciente"));;
				p.setNumero_processo(rs.getString("numeroprocesso"));
				p.setNome(rs.getString("nomecompleto"));
				p.setIdade(rs.getDate("data_nascimento"));
				p.setHoraConsulta(rs.getString("hora_triagem"));
				p.setEstado(rs.getString("estado_do_paciente"));
				p.setEncaminhar(rs.getString("encaminhado"));
				p.setSituacao(rs.getString("situacao"));
				Calendar data = Calendar.getInstance();
				data.setTime(rs.getDate("data_triagem"));
				p.setDataConsulta(data);
				if(rs.getInt("fk_genero")==1){
					p.setGenero("Masculino");
				}
				if(rs.getInt("fk_genero")==2){
					p.setGenero("Feminino");
				}
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
	
	public List<Triagem> getTriadosBancoUrgencia(Date data_in,Date data_fi,int aux)
	{
		List <Triagem> lista = new ArrayList<Triagem>();
		String sql = "SELECT * from vwtriadosnobancourgencia where data_triagem between ? and ?  and fk_genero = ? order by data_triagem desc  ";
		try {
			 con = Conexao.getConexao();
			 PreparedStatement preparador = con.prepareStatement(sql);
			 preparador.setDate(1, data_in);
			 preparador.setDate(2, data_fi);
			 preparador.setInt(3, aux);
			 ResultSet rs = preparador.executeQuery();
			 while(rs.next())
			 {
				 Triagem p = new Triagem();
				p.setId_triagem(rs.getInt("id_triagem_bancoue"));
				p.setFK_paciente(rs.getInt("FK_paciente"));;
				p.setNumero_processo(rs.getString("numeroprocesso"));
				p.setNome(rs.getString("nomecompleto"));
				p.setIdade(rs.getDate("data_nascimento"));
				p.setHoraConsulta(rs.getString("hora_triagem"));
				p.setEstado(rs.getString("estado_do_paciente"));
				p.setEncaminhar(rs.getString("encaminhado"));
				p.setSituacao(rs.getString("situacao"));
				Calendar data = Calendar.getInstance();
				data.setTime(rs.getDate("data_triagem"));
				p.setDataConsulta(data);
				if(rs.getInt("fk_genero")==1){
					p.setGenero("Masculino");
				}
				if(rs.getInt("fk_genero")==2){
					p.setGenero("Feminino");
				}
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

	public List<Triagem> getTriadosBancoUrgenciaEncam(Date data_in,Date data_fi,String op)
	{
		List <Triagem> lista = new ArrayList<Triagem>();
		String sql = "";
		if(op.equals("1")){
			sql = "SELECT * from vwtriadosnobancourgencia where data_triagem between ? and ?  and encaminhado Like 'Urgencia' order by data_triagem desc  ";
		}
		if(op.equals("2")){
			sql = "SELECT * from vwtriadosnobancourgencia where data_triagem between ? and ?  and encaminhado Like 'Emergencia' order by data_triagem desc  ";
		}
		try {
			 con = Conexao.getConexao();
			 PreparedStatement preparador = con.prepareStatement(sql);
			 preparador.setDate(1, data_in);
			 preparador.setDate(2, data_fi);
			 
			 ResultSet rs = preparador.executeQuery();
			 while(rs.next())
			 {
				 Triagem p = new Triagem();
				 p.setId_triagem(rs.getInt("id_triagem_bancoue"));
				p.setFK_paciente(rs.getInt("FK_paciente"));;
				p.setNumero_processo(rs.getString("numeroprocesso"));
				p.setNome(rs.getString("nomecompleto"));
				p.setIdade(rs.getDate("data_nascimento"));
				p.setHoraConsulta(rs.getString("hora_triagem"));
				p.setEstado(rs.getString("estado_do_paciente"));
				p.setEncaminhar(rs.getString("encaminhado"));
				p.setSituacao(rs.getString("situacao"));
				Calendar data = Calendar.getInstance();
				data.setTime(rs.getDate("data_triagem"));
				p.setDataConsulta(data);
				if(rs.getInt("fk_genero")==1){
					p.setGenero("Masculino");
				}
				if(rs.getInt("fk_genero")==2){
					p.setGenero("Feminino");
				}
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

	public List<Triagem> getTriadosBancoUrgencia(Date data_in,Date data_fi,String aux,int gen)
	{
		List <Triagem> lista = new ArrayList<Triagem>();
		String sql = "SELECT * from vwtriadosnobancourgencia where data_triagem between ? and ?  and nomecompleto like ? and fk_genero = ? order by data_triagem desc  ";
		try {
			 con = Conexao.getConexao();
			 PreparedStatement preparador = con.prepareStatement(sql);
			 preparador.setDate(1, data_in);
			 preparador.setDate(2, data_fi);
			 preparador.setString(3, aux+"%");
			 preparador.setInt(4, gen);
			 ResultSet rs = preparador.executeQuery();
			 while(rs.next())
			 {
				 Triagem p = new Triagem();
				p.setId_triagem(rs.getInt("id_triagem_bancoue"));
				p.setFK_paciente(rs.getInt("FK_paciente"));;
				p.setNumero_processo(rs.getString("numeroprocesso"));
				p.setNome(rs.getString("nomecompleto"));
				p.setIdade(rs.getDate("data_nascimento"));
				p.setHoraConsulta(rs.getString("hora_triagem"));
				p.setEstado(rs.getString("estado_do_paciente"));
				p.setEncaminhar(rs.getString("encaminhado"));
				p.setSituacao(rs.getString("situacao"));
				Calendar data = Calendar.getInstance();
				data.setTime(rs.getDate("data_triagem"));
				p.setDataConsulta(data);
				if(rs.getInt("fk_genero")==1){
					p.setGenero("Masculino");
				}
				if(rs.getInt("fk_genero")==2){
					p.setGenero("Feminino");
				}
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
	
	public List<Triagem> getTriadosBancoUrgenciaNomEnc(Date data_in,Date data_fi,String aux,int op)
	{
		List <Triagem> lista = new ArrayList<Triagem>();
		String sql = "";
		if(op == 1){
			sql = "SELECT * from vwtriadosnobancourgencia where data_triagem between ? and ?  and nomecompleto like ? and encaminhado Like 'Urgencia' order by data_triagem desc  ";
		}
		if(op == 2){
			sql = "SELECT * from vwtriadosnobancourgencia where data_triagem between ? and ?  and nomecompleto like ? and encaminhado Like 'Emergencia' order by data_triagem desc  ";
		}
		try {
			 con = Conexao.getConexao();
			 PreparedStatement preparador = con.prepareStatement(sql);
			 preparador.setDate(1, data_in);
			 preparador.setDate(2, data_fi);
			 preparador.setString(3, aux+"%");
			 
			 ResultSet rs = preparador.executeQuery();
			 while(rs.next())
			 {
				 Triagem p = new Triagem();
				 p.setId_triagem(rs.getInt("id_triagem_bancoue"));
				p.setFK_paciente(rs.getInt("FK_paciente"));;
				p.setNumero_processo(rs.getString("numeroprocesso"));
				p.setNome(rs.getString("nomecompleto"));
				p.setIdade(rs.getDate("data_nascimento"));
				p.setHoraConsulta(rs.getString("hora_triagem"));
				p.setEstado(rs.getString("estado_do_paciente"));
				p.setEncaminhar(rs.getString("encaminhado"));
				p.setSituacao(rs.getString("situacao"));
				Calendar data = Calendar.getInstance();
				data.setTime(rs.getDate("data_triagem"));
				p.setDataConsulta(data);
				if(rs.getInt("fk_genero")==1){
					p.setGenero("Masculino");
				}
				if(rs.getInt("fk_genero")==2){
					p.setGenero("Feminino");
				}
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
		String sql = "SELECT * from vwpacientesnaurgencia where status = 'Finalizar' order by data_registo desc limit 500";
		try {
			 con = Conexao.getConexao();
			 PreparedStatement preparador = con.prepareStatement(sql);
			 ResultSet rs = preparador.executeQuery();
			 while(rs.next())
			 {
				Triagem p = new Triagem();
			
				p.setFK_paciente(rs.getInt("FK_paciente"));
				p.setFK_urgencia(rs.getInt("id_urgencia"));
				p.setNumero_processo(rs.getString("numeroprocesso"));
				p.setNome(rs.getString("nomecompleto"));
				p.setIdade(rs.getDate("data_nascimento"));
				p.setEncaminhar(rs.getString("status"));
				p.setHoraConsulta(rs.getString("hora_registo"));
				Calendar data = Calendar.getInstance();
				data.setTime(rs.getDate("data_registo"));
				p.setDataConsulta(data);
				if(rs.getInt("fk_genero") == 1)
					p.setGenero("Masculino");
				if(rs.getInt("fk_genero") == 2)
					p.setGenero("Feminino");
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
	
	public List<Triagem> getAtendidosNaUrgenciaHJ(String valor)
	{
		List <Triagem> lista = new ArrayList<Triagem>();
		String sql = "SELECT * from vwpacientesnaurgencia where NomeCompleto like ?";
		try {
			 con = Conexao.getConexao();
			 PreparedStatement preparador = con.prepareStatement(sql);
			 preparador.setString(1, "%"+valor+"%");
			 ResultSet rs = preparador.executeQuery();
			 while(rs.next())
			 {
				Triagem p = new Triagem();
			
				p.setFK_paciente(rs.getInt("FK_paciente"));
				p.setFK_urgencia(rs.getInt("id_urgencia"));
				p.setNumero_processo(rs.getString("numeroprocesso"));
				p.setNome(rs.getString("nomecompleto"));
				p.setIdade(rs.getDate("data_nascimento"));
				p.setEncaminhar(rs.getString("status"));
				p.setHoraConsulta(rs.getString("hora_registo"));
				Calendar data = Calendar.getInstance();
				data.setTime(rs.getDate("data_registo"));
				p.setDataConsulta(data);
				if(rs.getInt("fk_genero") == 1)
					p.setGenero("Masculino");
				if(rs.getInt("fk_genero") == 2)
					p.setGenero("Feminino");
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
	
	public List<Paciente> getPacientesNaUrgencia()
	{
		List <Paciente> lista = new ArrayList<Paciente>();
		String sql = "SELECT * from vwpacientesnaurgencia  order by data_registo desc";
		try {
			 con = Conexao.getConexao();
			 PreparedStatement preparador = con.prepareStatement(sql);
			 ResultSet rs = preparador.executeQuery();
			 while(rs.next())
			 {
				 Paciente p = new Paciente();
				p.setFK_paciente(rs.getInt("FK_paciente"));
				p.setFK_urgencia(rs.getInt("id_urgencia"));
				p.setNumero_processo(rs.getString("numeroprocesso"));
				p.setNome(rs.getString("nomecompleto"));
				p.setIdade(rs.getDate("data_nascimento"));
				p.setEncaminhados(rs.getString("status"));
				p.setHora_registo(rs.getString("hora_registo"));
				Calendar data = Calendar.getInstance();
				data.setTime(rs.getDate("data_registo"));
				p.setData_registo(data);
				if(rs.getInt("fk_genero") == 1)
					p.setNomegenero("Masculino");
				if(rs.getInt("fk_genero") == 2)
					p.setNomegenero("Feminino");
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
	
	public List<Triagem> getDatasExameClinico(int cod)
	{
		List<Triagem> lista = new ArrayList<Triagem>();
		String sql = "SELECT * FROM vwurgenciaexameclinico where FK_paciente = ? group by data_registo ";
		try {
			con = Conexao.getConexao();
			PreparedStatement preparador = con.prepareStatement(sql);
			preparador.setInt(1, cod);
			ResultSet rs = preparador.executeQuery();
			while(rs.next()) {
				Triagem c = new Triagem();
				Calendar data = Calendar.getInstance();
				data.setTime(rs.getDate("data_registo"));
				c.setDataConsulta(data);
				c.setHoraConsulta(rs.getString("hora_registo"));
				c.setFK_urgencia(rs.getInt("id_urgencia"));
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
	
	public List<Triagem> getDatasRecomendacao(int cod)
	{
		List<Triagem> lista = new ArrayList<Triagem>();
		String sql = "SELECT * FROM vwurgenciarecomendacao where FK_paciente = ? group by data_registo ";
		try {
			con = Conexao.getConexao();
			PreparedStatement preparador = con.prepareStatement(sql);
			preparador.setInt(1, cod);
			ResultSet rs = preparador.executeQuery();
			while(rs.next()) {
				Triagem c = new Triagem();
				Calendar data = Calendar.getInstance();
				data.setTime(rs.getDate("data_registo"));
				c.setDataConsulta(data);
				c.setHoraConsulta(rs.getString("hora_registo"));
				c.setFK_urgencia(rs.getInt("fk_urgencia"));
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
	
	public List<Triagem> getDatasJustificativos(int cod)
	{
		List<Triagem> lista = new ArrayList<Triagem>();
		String sql = "SELECT * FROM vwurgenciajustificativos where FK_paciente = ? group by data_registo ";
		try {
			con = Conexao.getConexao();
			PreparedStatement preparador = con.prepareStatement(sql);
			preparador.setInt(1, cod);
			ResultSet rs = preparador.executeQuery();
			while(rs.next()) {
				Triagem c = new Triagem();
				Calendar data = Calendar.getInstance();
				data.setTime(rs.getDate("data_registo"));
				c.setDataConsulta(data);
				c.setHoraConsulta(rs.getString("hora_registo"));
				c.setFK_urgencia(rs.getInt("fk_urgencia"));
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
	
	public List<Triagem> getDatasFichaAtendtimentos(int cod)
	{
		List<Triagem> lista = new ArrayList<Triagem>();
		String sql = "SELECT * FROM tblurgencia where FK_paciente = ?   ";
		try {
			con = Conexao.getConexao();
			PreparedStatement preparador = con.prepareStatement(sql);
			preparador.setInt(1, cod);
			ResultSet rs = preparador.executeQuery();
			while(rs.next()) {
				Triagem c = new Triagem();
				Calendar data = Calendar.getInstance();
				data.setTime(rs.getDate("data_registo"));
				c.setDataConsulta(data);
				c.setHoraConsulta(rs.getString("hora_registo"));
				c.setFK_urgencia(rs.getInt("id_urgencia"));
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
	

	public List<Triagem> getDatasReceituario(int cod)
	{
		List<Triagem> lista = new ArrayList<Triagem>();
		String sql = "SELECT * FROM vwurgenciareceituario where FK_paciente = ? group by data_registo ";
		try {
			con = Conexao.getConexao();
			PreparedStatement preparador = con.prepareStatement(sql);
			preparador.setInt(1, cod);
			ResultSet rs = preparador.executeQuery();
			while(rs.next()) {
				Triagem c = new Triagem();
				Calendar data = Calendar.getInstance();
				data.setTime(rs.getDate("data_registo"));
				c.setDataConsulta(data);
				c.setHoraConsulta(rs.getString("hora_registo"));
				c.setFK_urgencia(rs.getInt("fk_urgencia"));
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
	public List<Farmacos> getReceituarios(int cod)
	{
		List<Farmacos> lista = new ArrayList<Farmacos>();
		String sql = "SELECT * FROM vwurgenciareceituario where fk_urgencia = ? ";
		try {
			con = Conexao.getConexao();
			PreparedStatement preparador = con.prepareStatement(sql);
			preparador.setInt(1, cod);
			ResultSet rs = preparador.executeQuery();
			while(rs.next()) {
				Farmacos f = new Farmacos();
				f.setFarmaco(rs.getString("nome_comercial")); 
				f.setDosagem(rs.getString("dosagem"));
				f.setQuantidade(rs.getInt("quantidade"));
				f.setPosologia(rs.getString("posologia"));
				f.setFK_urgencia(rs.getInt("fk_urgencia"));
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
	
	public Triagem getRecomendacao(int cod)
	{
		Triagem recom = new Triagem();
		String sql = "SELECT * FROM vwurgenciarecomendacao where fk_urgencia = ?  ";
		try {
			con = Conexao.getConexao();
			PreparedStatement preparador = con.prepareStatement(sql);
			preparador.setInt(1, cod);
			ResultSet rs = preparador.executeQuery();
			if(rs.next()) {			
				recom.setFK_urgencia(rs.getInt("fk_urgencia"));
				recom.setRecomendacao(rs.getString("recomendacao"));			 
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
		return recom;
	}
	
	public List<Triagem> getExamesClinicos(int cod)
	{
		List<Triagem> lista = new ArrayList<Triagem>();
		String sql = "SELECT * FROM vwurgenciaexameclinico where id_urgencia = ?  ";
		try {
			con = Conexao.getConexao();
			PreparedStatement preparador = con.prepareStatement(sql);
			preparador.setInt(1, cod);
			ResultSet rs = preparador.executeQuery();
			while(rs.next()) {
				Triagem c = new Triagem();
				c.setFK_urgencia(rs.getInt("id_urgencia"));
				c.setAnalise(rs.getString("analise_clinica"));
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
	
	public List<Paciente> getPacientesNaEmergencia()
	{
		List <Paciente> lista = new ArrayList<Paciente>();
		String sql = "SELECT * from vwpacientesnaemergencia where status <> 'Observacao' and data_registo = curdate() order by data_registo desc  ";
		try {
			 con = Conexao.getConexao();
			 PreparedStatement preparador = con.prepareStatement(sql);
			 ResultSet rs = preparador.executeQuery();
			 while(rs.next())
			 {
				 Paciente p = new Paciente();
				p.setFK_paciente(rs.getInt("FK_paciente"));;
				p.setFK_emergencia(rs.getInt("id_emergencia")); 
				p.setNome(rs.getString("nomecompleto"));
				p.setIdade(rs.getDate("data_nascimento"));
				p.setEncaminhados(rs.getString("status"));
				Calendar data = Calendar.getInstance();
				data.setTime(rs.getDate("data_registo"));
				p.setData_registo(data);
				p.setHora_registo(rs.getString("hora_registo"));
				if(rs.getInt("fk_genero")==1){
					p.setNomegenero("Masculino");
				}
				if(rs.getInt("fk_genero")==2){
					p.setNomegenero("Feminino");
				}
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
	
	public List<Paciente> getPacientesNaUrgencia(Date data_in, Date data_fi)
	{
		List <Paciente> lista = new ArrayList<Paciente>();
		String sql = "SELECT * from vwpacientesnaurgencia where data_registo between ? and ? order by data_registo desc";
		try {
			 con = Conexao.getConexao();
			 PreparedStatement preparador = con.prepareStatement(sql);
			 preparador.setDate(1, data_in);
			 preparador.setDate(2, data_fi);
			 ResultSet rs = preparador.executeQuery();
			 while(rs.next())
			 {
				 Paciente p = new Paciente();
				p.setFK_paciente(rs.getInt("FK_paciente"));;
				p.setFK_urgencia(rs.getInt("id_urgencia")); 
				p.setNome(rs.getString("nomecompleto"));
				p.setIdade(rs.getDate("data_nascimento"));
				p.setEncaminhados(rs.getString("status"));
				Calendar data = Calendar.getInstance();
				data.setTime(rs.getDate("data_registo"));
				p.setData_registo(data);
				p.setHora_registo(rs.getString("hora_registo"));
				if(rs.getInt("fk_genero")==1){
					p.setNomegenero("Masculino");
				}
				if(rs.getInt("fk_genero")==2){
					p.setNomegenero("Feminino");
				}
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
	
	public List<Paciente> getPacientesNaUrgencia(Date data_in, Date data_fi,String nome)
	{
		List <Paciente> lista = new ArrayList<Paciente>();
		String sql = "SELECT * from vwpacientesnaurgencia where data_registo between ? and ? and nomecompleto like ? order by data_registo desc";
		try {
			 con = Conexao.getConexao();
			 PreparedStatement preparador = con.prepareStatement(sql);
			 preparador.setDate(1, data_in);
			 preparador.setDate(2, data_fi);
			 preparador.setString(3, nome+"%");
			 ResultSet rs = preparador.executeQuery();
			 while(rs.next())
			 {
				Paciente p = new Paciente();
				p.setFK_paciente(rs.getInt("FK_paciente"));;
				 
				p.setNome(rs.getString("nomecompleto"));
				p.setFK_urgencia(rs.getInt("id_urgencia"));
				p.setIdade(rs.getDate("data_nascimento"));
				p.setEncaminhados(rs.getString("status"));
				Calendar data = Calendar.getInstance();
				data.setTime(rs.getDate("data_registo"));
				p.setData_registo(data);
				p.setHora_registo(rs.getString("hora_registo"));
				if(rs.getInt("fk_genero")==1){
					p.setNomegenero("Masculino");
				}
				if(rs.getInt("fk_genero")==2){
					p.setNomegenero("Feminino");
				}
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
	
	public List<Paciente> getPacientesNaUrgencia(Date data_in, Date data_fi,int idade1, int idade2)
	{
		List <Paciente> lista = new ArrayList<Paciente>();
		String sql = "SELECT * from vwpacientesnaurgencia where data_registo between ? and ? and idade between ? and ? order by data_registo desc";
		try {
			 con = Conexao.getConexao();
			 PreparedStatement preparador = con.prepareStatement(sql);
			 preparador.setDate(1, data_in);
			 preparador.setDate(2, data_fi);
			 preparador.setInt(3, idade1);
			 preparador.setInt(4, idade2);
			 ResultSet rs = preparador.executeQuery();
			 while(rs.next())
			 {
				 Paciente p = new Paciente();
				p.setFK_paciente(rs.getInt("FK_paciente"));;
				p.setFK_urgencia(rs.getInt("id_urgencia")); 
				p.setNome(rs.getString("nomecompleto"));
				p.setIdade(rs.getDate("data_nascimento"));
				p.setEncaminhados(rs.getString("status"));
				Calendar data = Calendar.getInstance();
				data.setTime(rs.getDate("data_registo"));
				p.setData_registo(data);
				p.setHora_registo(rs.getString("hora_registo"));
				if(rs.getInt("fk_genero")==1){
					p.setNomegenero("Masculino");
				}
				if(rs.getInt("fk_genero")==2){
					p.setNomegenero("Feminino");
				}
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
	
	public List<Paciente> getPacientesNaUrgencia(Date data_in, Date data_fi,int sexo)
	{
		List <Paciente> lista = new ArrayList<Paciente>();
		String sql = "SELECT * from vwpacientesnaurgencia where data_registo between ? and ? and fk_genero = ? order by data_registo desc";
		try {
			 con = Conexao.getConexao();
			 PreparedStatement preparador = con.prepareStatement(sql);
			 preparador.setDate(1, data_in);
			 preparador.setDate(2, data_fi);
			 preparador.setInt(3, sexo);
			 ResultSet rs = preparador.executeQuery();
			 while(rs.next())
			 {
				 Paciente p = new Paciente();
				p.setFK_paciente(rs.getInt("FK_paciente"));;
				p.setFK_urgencia(rs.getInt("id_urgencia")); 
				p.setNome(rs.getString("nomecompleto"));
				p.setIdade(rs.getDate("data_nascimento"));
				p.setEncaminhados(rs.getString("status"));
				Calendar data = Calendar.getInstance();
				data.setTime(rs.getDate("data_registo"));
				p.setData_registo(data);
				p.setHora_registo(rs.getString("hora_registo"));
				if(rs.getInt("fk_genero")==1){
					p.setNomegenero("Masculino");
				}
				if(rs.getInt("fk_genero")==2){
					p.setNomegenero("Feminino");
				}
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

	public List<Paciente> getPacientesNaUrgenciaEnc(Date data_in, Date data_fi, int encam_op) {
		List <Paciente> lista = new ArrayList<Paciente>();
		String sql ="";
		if(encam_op==1){
			sql = "SELECT * from vwpacientesnaurgencia where data_registo between ? and ? and status Like 'Emergencia' order by data_registo desc";
		}
		if(encam_op==2){
			sql = "SELECT * from vwpacientesnaurgencia where data_registo between ? and ? and status Like 'Observacao' order by data_registo desc";
		}
		if(encam_op==3){
			sql = "SELECT * from vwpacientesnaurgencia where data_registo between ? and ? and status Like 'Internado' order by data_registo desc";
		}
		try {
			 con = Conexao.getConexao();
			 PreparedStatement preparador = con.prepareStatement(sql);
			 preparador.setDate(1, data_in);
			 preparador.setDate(2, data_fi);
			 
			 ResultSet rs = preparador.executeQuery();
			 while(rs.next())
			 {
				 Paciente p = new Paciente();
				p.setFK_paciente(rs.getInt("FK_paciente"));;
				p.setFK_urgencia(rs.getInt("id_urgencia")); 
				p.setNome(rs.getString("nomecompleto"));
				p.setIdade(rs.getDate("data_nascimento"));
				p.setEncaminhados(rs.getString("status"));
				Calendar data = Calendar.getInstance();
				data.setTime(rs.getDate("data_registo"));
				p.setData_registo(data);
				p.setHora_registo(rs.getString("hora_registo"));
				if(rs.getInt("fk_genero")==1){
					p.setNomegenero("Masculino");
				}
				if(rs.getInt("fk_genero")==2){
					p.setNomegenero("Feminino");
				}
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

	public List<Paciente> getPacientesNaUrgenciaNomGen(Date data1, Date data2, String nome, int sexo) {
		
		List <Paciente> lista = new ArrayList<Paciente>();
		String sql = "SELECT * from vwpacientesnaurgencia where data_registo between ? and ? and fk_genero = ? and nomecompleto Like ? order by data_registo desc";
		try {
			 con = Conexao.getConexao();
			 PreparedStatement preparador = con.prepareStatement(sql);
			 preparador.setDate(1, data1);
			 preparador.setDate(2, data2);
			 preparador.setInt(3, sexo);
			 preparador.setString(4, nome+"%");
			 ResultSet rs = preparador.executeQuery();
			 while(rs.next())
			 {
				Paciente p = new Paciente();
				p.setFK_paciente(rs.getInt("FK_paciente"));
				p.setFK_urgencia(rs.getInt("id_urgencia"));
				p.setNome(rs.getString("nomecompleto"));
				p.setIdade(rs.getDate("data_nascimento"));
				p.setEncaminhados(rs.getString("status"));
				Calendar data = Calendar.getInstance();
				data.setTime(rs.getDate("data_registo"));
				p.setData_registo(data);
				p.setHora_registo(rs.getString("hora_registo"));
				if(rs.getInt("fk_genero")==1){
					p.setNomegenero("Masculino");
				}
				if(rs.getInt("fk_genero")==2){
					p.setNomegenero("Feminino");
				}
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
	
public List<Paciente> getPacientesNaUrgenciaIdaGen(Date data1, Date data2, int dade1, int idade2, int sexo) {
		
		List <Paciente> lista = new ArrayList<Paciente>();
		String sql = "SELECT * from vwpacientesnaurgencia where data_registo between ? and ? and fk_genero = ? and idade between ? and ? order by data_registo desc";
		try {
			 con = Conexao.getConexao();
			 PreparedStatement preparador = con.prepareStatement(sql);
			 preparador.setDate(1, data1);
			 preparador.setDate(2, data2);
			 preparador.setInt(3, sexo);
			 preparador.setInt(4, dade1);
			 preparador.setInt(5, idade2);
			 ResultSet rs = preparador.executeQuery();
			 while(rs.next())
			 {
				Paciente p = new Paciente();
				p.setFK_paciente(rs.getInt("FK_paciente"));
				p.setFK_urgencia(rs.getInt("id_urgencia"));
				p.setNome(rs.getString("nomecompleto"));
				p.setIdade(rs.getDate("data_nascimento"));
				p.setEncaminhados(rs.getString("status"));
				Calendar data = Calendar.getInstance();
				data.setTime(rs.getDate("data_registo"));
				p.setData_registo(data);
				p.setHora_registo(rs.getString("hora_registo"));
				if(rs.getInt("fk_genero")==1){
					p.setNomegenero("Masculino");
				}
				if(rs.getInt("fk_genero")==2){
					p.setNomegenero("Feminino");
				}
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

public List<Paciente> getPacientesNaUrgenciaNomEncam(Date data1, Date data2, String nome, int encam_op) {
	List <Paciente> lista = new ArrayList<Paciente>();
	String sql ="";
	if(encam_op==1){
		sql = "SELECT * from vwpacientesnaurgencia where data_registo between ? and ? and status Like 'Emergencia' and nomecompleto Like ? order by data_registo desc";
	}
	if(encam_op==2){
		sql = "SELECT * from vwpacientesnaurgencia where data_registo between ? and ? and status Like 'Observacao' and nomecompleto Like ?  order by data_registo desc";
	}
	if(encam_op==3){
		sql = "SELECT * from vwpacientesnaurgencia where data_registo between ? and ? and status Like 'Internado' and nomecompleto Like ?  order by data_registo desc";
	}
	try {
		 con = Conexao.getConexao();
		 PreparedStatement preparador = con.prepareStatement(sql);
		 preparador.setDate(1, data1);
		 preparador.setDate(2, data2);
		 preparador.setString(3, nome+"%");
		 ResultSet rs = preparador.executeQuery();
		 while(rs.next())
		 {
			 Paciente p = new Paciente();
			p.setFK_paciente(rs.getInt("FK_paciente"));;
			p.setFK_urgencia(rs.getInt("id_urgencia")); 
			p.setNome(rs.getString("nomecompleto"));
			p.setIdade(rs.getDate("data_nascimento"));
			p.setEncaminhados(rs.getString("status"));
			Calendar data = Calendar.getInstance();
			data.setTime(rs.getDate("data_registo"));
			p.setData_registo(data);
			p.setHora_registo(rs.getString("hora_registo"));
			if(rs.getInt("fk_genero")==1){
				p.setNomegenero("Masculino");
			}
			if(rs.getInt("fk_genero")==2){
				p.setNomegenero("Feminino");
			}
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

public List<Paciente> getPacientesNaUrgenciaGenEncam(Date data1, Date data2, int sexo, int encam_op) {
	List <Paciente> lista = new ArrayList<Paciente>();
	String sql ="";
	if(encam_op==1){
		sql = "SELECT * from vwpacientesnaurgencia where data_registo between ? and ? and status Like 'Emergencia' and fk_genero = ? order by data_registo desc";
	}
	if(encam_op==2){
		sql = "SELECT * from vwpacientesnaurgencia where data_registo between ? and ? and status Like 'Observacao' and fk_genero = ?  order by data_registo desc";
	}
	if(encam_op==3){
		sql = "SELECT * from vwpacientesnaurgencia where data_registo between ? and ? and status Like 'Internado' and fk_genero = ?  order by data_registo desc";
	}
	try {
		 con = Conexao.getConexao();
		 PreparedStatement preparador = con.prepareStatement(sql);
		 preparador.setDate(1, data1);
		 preparador.setDate(2, data2);
		 preparador.setInt(3, sexo);
		 ResultSet rs = preparador.executeQuery();
		 while(rs.next())
		 {
			 Paciente p = new Paciente();
			p.setFK_paciente(rs.getInt("FK_paciente"));;
			p.setFK_urgencia(rs.getInt("id_urgencia")); 
			p.setNome(rs.getString("nomecompleto"));
			p.setIdade(rs.getDate("data_nascimento"));
			p.setEncaminhados(rs.getString("status"));
			Calendar data = Calendar.getInstance();
			data.setTime(rs.getDate("data_registo"));
			p.setData_registo(data);
			p.setHora_registo(rs.getString("hora_registo"));
			if(rs.getInt("fk_genero")==1){
				p.setNomegenero("Masculino");
			}
			if(rs.getInt("fk_genero")==2){
				p.setNomegenero("Feminino");
			}
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


public List<Paciente> getPacientesNaEmergencia(Date data_in, Date data_fi)
{
	List <Paciente> lista = new ArrayList<Paciente>();
	String sql = "SELECT * from vwpacientesnaemergencia where status <> 'Observacao' and data_registo between ? and ? order by data_registo desc";
	try {
		 con = Conexao.getConexao();
		 PreparedStatement preparador = con.prepareStatement(sql);
		 preparador.setDate(1, data_in);
		 preparador.setDate(2, data_fi);
		 ResultSet rs = preparador.executeQuery();
		 while(rs.next())
		 {
			 Paciente p = new Paciente();
			p.setFK_paciente(rs.getInt("FK_paciente"));;
			p.setFK_emergencia(rs.getInt("id_emergencia")); 
			p.setNome(rs.getString("nomecompleto"));
			p.setIdade(rs.getDate("data_nascimento"));
			p.setEncaminhados(rs.getString("status"));
			Calendar data = Calendar.getInstance();
			data.setTime(rs.getDate("data_registo"));
			p.setData_registo(data);
			p.setHora_registo(rs.getString("hora_registo"));
			if(rs.getInt("fk_genero")==1){
				p.setNomegenero("Masculino");
			}
			if(rs.getInt("fk_genero")==2){
				p.setNomegenero("Feminino");
			}
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

public List<Paciente> getPacientesNaEmergencia(Date data_in, Date data_fi,String nome)
{
	List <Paciente> lista = new ArrayList<Paciente>();
	String sql = "SELECT * from vwpacientesnaemergencia where data_registo between ? and ? and nomecompleto like ? order by data_registo desc";
	try {
		 con = Conexao.getConexao();
		 PreparedStatement preparador = con.prepareStatement(sql);
		 preparador.setDate(1, data_in);
		 preparador.setDate(2, data_fi);
		 preparador.setString(3, nome+"%");
		 ResultSet rs = preparador.executeQuery();
		 while(rs.next())
		 {
			Paciente p = new Paciente();
			p.setFK_paciente(rs.getInt("FK_paciente"));;
			p.setFK_emergencia(rs.getInt("id_emergencia"));
			p.setNome(rs.getString("nomecompleto"));
			p.setIdade(rs.getDate("data_nascimento"));
			p.setEncaminhados(rs.getString("status"));
			Calendar data = Calendar.getInstance();
			data.setTime(rs.getDate("data_registo"));
			p.setData_registo(data);
			p.setHora_registo(rs.getString("hora_registo"));
			if(rs.getInt("fk_genero")==1){
				p.setNomegenero("Masculino");
			}
			if(rs.getInt("fk_genero")==2){
				p.setNomegenero("Feminino");
			}
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

public List<Paciente> getPacientesNaEmergencia(Date data_in, Date data_fi,int idade1, int idade2)
{
	List <Paciente> lista = new ArrayList<Paciente>();
	String sql = "SELECT * from vwpacientesnaemergencia where status <> 'Observacao' and data_registo between ? and ? and idade between ? and ? order by data_registo desc";
	try {
		 con = Conexao.getConexao();
		 PreparedStatement preparador = con.prepareStatement(sql);
		 preparador.setDate(1, data_in);
		 preparador.setDate(2, data_fi);
		 preparador.setInt(3, idade1);
		 preparador.setInt(4, idade2);
		 ResultSet rs = preparador.executeQuery();
		 while(rs.next())
		 {
			 Paciente p = new Paciente();
			p.setFK_paciente(rs.getInt("FK_paciente"));;
			 
			p.setNome(rs.getString("nomecompleto"));
			p.setFK_emergencia(rs.getInt("id_emergencia"));
			p.setIdade(rs.getDate("data_nascimento"));
			p.setEncaminhados(rs.getString("status"));
			Calendar data = Calendar.getInstance();
			data.setTime(rs.getDate("data_registo"));
			p.setData_registo(data);
			p.setHora_registo(rs.getString("hora_registo"));
			if(rs.getInt("fk_genero")==1){
				p.setNomegenero("Masculino");
			}
			if(rs.getInt("fk_genero")==2){
				p.setNomegenero("Feminino");
			}
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

public List<Paciente> getPacientesNaEmergencia(Date data_in, Date data_fi,int sexo)
{
	List <Paciente> lista = new ArrayList<Paciente>();
	String sql = "SELECT * from vwpacientesnaemergencia where status <> 'Observacao' and data_registo between ? and ? and fk_genero = ? order by data_registo desc";
	try {
		 con = Conexao.getConexao();
		 PreparedStatement preparador = con.prepareStatement(sql);
		 preparador.setDate(1, data_in);
		 preparador.setDate(2, data_fi);
		 preparador.setInt(3, sexo);
		 ResultSet rs = preparador.executeQuery();
		 while(rs.next())
		 {
			 Paciente p = new Paciente();
			p.setFK_paciente(rs.getInt("FK_paciente"));;
			p.setFK_emergencia(rs.getInt("id_emergencia"));
			p.setNome(rs.getString("nomecompleto"));
			p.setIdade(rs.getDate("data_nascimento"));
			p.setEncaminhados(rs.getString("status"));
			Calendar data = Calendar.getInstance();
			data.setTime(rs.getDate("data_registo"));
			p.setData_registo(data);
			p.setHora_registo(rs.getString("hora_registo"));
			if(rs.getInt("fk_genero")==1){
				p.setNomegenero("Masculino");
			}
			if(rs.getInt("fk_genero")==2){
				p.setNomegenero("Feminino");
			}
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

public List<Paciente> getPacientesNaEmergenciaEnc(Date data_in, Date data_fi, int encam_op) {
	List <Paciente> lista = new ArrayList<Paciente>();
	String sql ="";
	if(encam_op==1){
		sql = "SELECT * from vwpacientesnaemergencia where status <> 'Observacao' and data_registo between ? and ? and status Like 'Bloco' order by data_registo desc";
	}
	if(encam_op==2){
		sql = "SELECT * from vwpacientesnaemergencia where status <> 'Observacao' and data_registo between ? and ? and status Like 'Internado' order by data_registo desc";
	}
	try {
		 con = Conexao.getConexao();
		 PreparedStatement preparador = con.prepareStatement(sql);
		 preparador.setDate(1, data_in);
		 preparador.setDate(2, data_fi);
		 
		 ResultSet rs = preparador.executeQuery();
		 while(rs.next())
		 {
			 Paciente p = new Paciente();
			p.setFK_paciente(rs.getInt("FK_paciente"));;
			 
			p.setNome(rs.getString("nomecompleto"));
			p.setFK_emergencia(rs.getInt("id_emergencia"));
			p.setIdade(rs.getDate("data_nascimento"));
			p.setEncaminhados(rs.getString("status"));
			Calendar data = Calendar.getInstance();
			data.setTime(rs.getDate("data_registo"));
			p.setData_registo(data);
			p.setHora_registo(rs.getString("hora_registo"));
			if(rs.getInt("fk_genero")==1){
				p.setNomegenero("Masculino");
			}
			if(rs.getInt("fk_genero")==2){
				p.setNomegenero("Feminino");
			}
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

public List<Paciente> getPacientesNaEmergenciaNomGen(Date data1, Date data2, String nome, int sexo) {
	
	List <Paciente> lista = new ArrayList<Paciente>();
	String sql = "SELECT * from vwpacientesnaemergencia where status <> 'Observacao' and data_registo between ? and ? and fk_genero = ? and nomecompleto Like ? order by data_registo desc";
	try {
		 con = Conexao.getConexao();
		 PreparedStatement preparador = con.prepareStatement(sql);
		 preparador.setDate(1, data1);
		 preparador.setDate(2, data2);
		 preparador.setInt(3, sexo);
		 preparador.setString(4, nome+"%");
		 ResultSet rs = preparador.executeQuery();
		 while(rs.next())
		 {
			Paciente p = new Paciente();
			p.setFK_paciente(rs.getInt("FK_paciente"));
			p.setFK_emergencia(rs.getInt("id_emergencia"));
			p.setNome(rs.getString("nomecompleto"));
			p.setIdade(rs.getDate("data_nascimento"));
			p.setEncaminhados(rs.getString("status"));
			Calendar data = Calendar.getInstance();
			data.setTime(rs.getDate("data_registo"));
			p.setData_registo(data);
			p.setHora_registo(rs.getString("hora_registo"));
			if(rs.getInt("fk_genero")==1){
				p.setNomegenero("Masculino");
			}
			if(rs.getInt("fk_genero")==2){
				p.setNomegenero("Feminino");
			}
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

public List<Paciente> getPacientesNaEmergenciaIdaGen(Date data1, Date data2, int dade1, int idade2, int sexo) {
	
	List <Paciente> lista = new ArrayList<Paciente>();
	String sql = "SELECT * from vwpacientesnaemergencia where status <> 'Observacao' and data_registo between ? and ? and fk_genero = ? and idade between ? and ? order by data_registo desc";
	try {
		 con = Conexao.getConexao();
		 PreparedStatement preparador = con.prepareStatement(sql);
		 preparador.setDate(1, data1);
		 preparador.setDate(2, data2);
		 preparador.setInt(3, sexo);
		 preparador.setInt(4, dade1);
		 preparador.setInt(5, idade2);
		 ResultSet rs = preparador.executeQuery();
		 while(rs.next())
		 {
			Paciente p = new Paciente();
			p.setFK_paciente(rs.getInt("FK_paciente"));
			p.setFK_emergencia(rs.getInt("id_emergencia"));
			p.setNome(rs.getString("nomecompleto"));
			p.setIdade(rs.getDate("data_nascimento"));
			p.setEncaminhados(rs.getString("status"));
			Calendar data = Calendar.getInstance();
			data.setTime(rs.getDate("data_registo"));
			p.setData_registo(data);
			p.setHora_registo(rs.getString("hora_registo"));
			if(rs.getInt("fk_genero")==1){
				p.setNomegenero("Masculino");
			}
			if(rs.getInt("fk_genero")==2){
				p.setNomegenero("Feminino");
			}
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

public List<Paciente> getPacientesNaEmergenciaNomEncam(Date data1, Date data2, String nome, int encam_op) {
List <Paciente> lista = new ArrayList<Paciente>();
String sql ="";
if(encam_op==1){
	sql = "SELECT * from vwpacientesnaemergencia where status <> 'Observacao' and data_registo between ? and ? and status Like 'Bloco' and nomecompleto Like ? order by data_registo desc";
}
if(encam_op==2){
	sql = "SELECT * from vwpacientesnaemergencia where status <> 'Observacao' and data_registo between ? and ? and status Like 'Internado' and nomecompleto Like ?  order by data_registo desc";
}
try {
	 con = Conexao.getConexao();
	 PreparedStatement preparador = con.prepareStatement(sql);
	 preparador.setDate(1, data1);
	 preparador.setDate(2, data2);
	 preparador.setString(3, nome+"%");
	 ResultSet rs = preparador.executeQuery();
	 while(rs.next())
	 {
		 Paciente p = new Paciente();
		p.setFK_paciente(rs.getInt("FK_paciente"));;
		p.setFK_emergencia(rs.getInt("id_emergencia")); 
		p.setNome(rs.getString("nomecompleto"));
		p.setIdade(rs.getDate("data_nascimento"));
		p.setEncaminhados(rs.getString("status"));
		Calendar data = Calendar.getInstance();
		data.setTime(rs.getDate("data_registo"));
		p.setData_registo(data);
		p.setHora_registo(rs.getString("hora_registo"));
		if(rs.getInt("fk_genero")==1){
			p.setNomegenero("Masculino");
		}
		if(rs.getInt("fk_genero")==2){
			p.setNomegenero("Feminino");
		}
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

public List<Paciente> getPacientesNaEmergenciaGenEncam(Date data1, Date data2, int sexo, int encam_op) {
List <Paciente> lista = new ArrayList<Paciente>();
String sql ="";
if(encam_op==1){
	sql = "SELECT * from vwpacientesnaemergencia where status <> 'Observacao' and data_registo between ? and ? and status Like 'Bloco' and fk_genero = ? order by data_registo desc";
}
if(encam_op==2){
	sql = "SELECT * from vwpacientesnaemergencia where status <> 'Observacao' and data_registo between ? and ? and status Like 'Internado' and fk_genero = ?  order by data_registo desc";
}
try {
	 con = Conexao.getConexao();
	 PreparedStatement preparador = con.prepareStatement(sql);
	 preparador.setDate(1, data1);
	 preparador.setDate(2, data2);
	 preparador.setInt(3, sexo);
	 ResultSet rs = preparador.executeQuery();
	 while(rs.next())
	 {
		 Paciente p = new Paciente();
		p.setFK_paciente(rs.getInt("FK_paciente"));;
		p.setFK_emergencia(rs.getInt("id_emergencia")); 
		p.setNome(rs.getString("nomecompleto"));
		p.setIdade(rs.getDate("data_nascimento"));
		p.setEncaminhados(rs.getString("status"));
		Calendar data = Calendar.getInstance();
		data.setTime(rs.getDate("data_registo"));
		p.setData_registo(data);
		p.setHora_registo(rs.getString("hora_registo"));
		if(rs.getInt("fk_genero")==1){
			p.setNomegenero("Masculino");
		}
		if(rs.getInt("fk_genero")==2){
			p.setNomegenero("Feminino");
		}
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
	
}
