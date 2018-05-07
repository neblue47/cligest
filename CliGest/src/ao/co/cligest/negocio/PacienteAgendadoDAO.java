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
import ao.co.cligest.entidades.PacienteRelatorio;

public class PacienteAgendadoDAO{
	
	private Connection con;
	Formatando ft = new Formatando();
	
	public List <Paciente> buscarPaciente()
    {
			List <Paciente> lista = new ArrayList<Paciente>();
			String sql = "SELECT * FROM vwmarcadasemconfirmar where data = current_date() AND id_consulta_marcada NOT IN(SELECT FK_consulta_marcada FROM tblconsultaconfirmada)";
			try {
				con = Conexao.getConexao();
				PreparedStatement preparador = con.prepareStatement(sql);
//				preparador.setString(1, nome);
				ResultSet rs = preparador.executeQuery();
				while (rs.next())
				 {
					 Paciente fun = new Paciente();
					 fun.setNomeCompleto(rs.getString("NomeCompleto"));
					 fun.setServico(rs.getString("servico"));
					 Calendar data = Calendar.getInstance();
				     data.setTime(rs.getDate("data_nascimento"));
				     fun.setDataNasc(data);
				     fun.setIdade(rs.getDate("data_nascimento"));
				     fun.setData_do_agendamento(data);
				     fun.setData(rs.getDate("data_do_agendamento"));
				     fun.setNome_doutor(rs.getString("NomeCompleto1"));
				     fun.setNomegenero(rs.getString("genero"));
				     fun.setFK_consulta_marcada(rs.getInt("id_consulta_marcada"));
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
	public List <Paciente> buscarPorNome(Date dataI, Date dataF, String nome)
    {
			List <Paciente> lista = new ArrayList<Paciente>();
			String sql = "SELECT * FROM vwmarcadasemconfirmar WHERE data_do_agendamento BETWEEN ? AND ? AND nomeCompleto LIKE '"+nome+"%' OR nomeCompleto LIKE '"+nome+"%' AND FK_paciente = ? ORDER BY (nomeCompleto)";
			try {
				con = Conexao.getConexao();
				PreparedStatement preparador = con.prepareStatement(sql);
				preparador.setDate(1, dataI);
				preparador.setDate(2, dataF);
				preparador.setString(3, nome);
				ResultSet rs = preparador.executeQuery();
				while (rs.next())
				 {
					 Paciente fun = new Paciente();
					 fun.setNomeCompleto(rs.getString("NomeCompleto"));
					 fun.setServico(rs.getString("servico"));
					 Calendar data = Calendar.getInstance();
				     data.setTime(rs.getDate("data_nascimento"));
				     fun.setDataNasc(data);
				     fun.setIdade(rs.getDate("data_nascimento"));
				     fun.setData_do_agendamento(data);
				     fun.setData(rs.getDate("data_do_agendamento"));
				     fun.setNome_doutor(rs.getString("NomeCompleto1"));
				     fun.setNomegenero(rs.getString("genero"));
				     fun.setFK_consulta_marcada(rs.getInt("id_consulta_marcada"));
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
    public List <Paciente> buscarPorIdade(Date dataI, Date dataF, String idade)
    {
			List <Paciente> lista = new ArrayList<Paciente>();
			String sql = "SELECT * FROM vwmarcadasemconfirmar where data_do_agendamento BETWEEN ? AND ? AND IDADE =?";
			try {
				con = Conexao.getConexao();
				PreparedStatement preparador = con.prepareStatement(sql);
				preparador.setDate(1, dataI);
				preparador.setDate(2, dataF);
				preparador.setString(3, idade);
				ResultSet rs = preparador.executeQuery();
				while (rs.next())
				 {
					 Paciente fun = new Paciente();
					 fun.setNomeCompleto(rs.getString("NomeCompleto"));
					 fun.setServico(rs.getString("servico"));
					 Calendar data = Calendar.getInstance();
				     data.setTime(rs.getDate("data_nascimento"));
				     fun.setDataNasc(data);
				     fun.setIdade(rs.getDate("data_nascimento"));
				     fun.setData_do_agendamento(data);
				     fun.setData(rs.getDate("data_do_agendamento"));
				     fun.setNome_doutor(rs.getString("NomeCompleto1"));
				     fun.setNomegenero(rs.getString("genero"));
				     fun.setFK_consulta_marcada(rs.getInt("id_consulta_marcada"));
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
    public List <Paciente> buscarPorIdades(Date dataI, Date dataF, String idade1, String idade2)
    {
			List <Paciente> lista = new ArrayList<Paciente>();
			String sql = "SELECT * FROM vwmarcadasemconfirmar where data_do_agendamento BETWEEN ? AND ? AND IDADE BETWEEN ? AND ?";
			try {
				con = Conexao.getConexao();
				PreparedStatement preparador = con.prepareStatement(sql);
				preparador.setDate(1, dataI);
				preparador.setDate(2, dataF);
				preparador.setString(3, idade1);
				preparador.setString(4, idade2);
				ResultSet rs = preparador.executeQuery();
				while (rs.next())
				 {
					 Paciente fun = new Paciente();
					 fun.setNomeCompleto(rs.getString("NomeCompleto"));
					 fun.setServico(rs.getString("servico"));
					 Calendar data = Calendar.getInstance();
				     data.setTime(rs.getDate("data_nascimento"));
				     fun.setDataNasc(data);
				     fun.setIdade(rs.getDate("data_nascimento"));
				     fun.setData_do_agendamento(data);
				     fun.setData(rs.getDate("data_do_agendamento"));
				     fun.setNome_doutor(rs.getString("NomeCompleto1"));
				     fun.setNomegenero(rs.getString("genero"));
				     fun.setFK_consulta_marcada(rs.getInt("id_consulta_marcada"));
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
    public List <Paciente> buscarPorServico(Date dataI, Date dataF, String servico)
    {
			List <Paciente> lista = new ArrayList<Paciente>();
			String sql = "SELECT * FROM vwmarcadasemconfirmar WHERE data_do_agendamento BETWEEN ? AND ? AND servico =?";
			try {
				con = Conexao.getConexao();
				PreparedStatement preparador = con.prepareStatement(sql);
				preparador.setDate(1, dataI);
				preparador.setDate(2, dataF);
				preparador.setString(3, servico);
				ResultSet rs = preparador.executeQuery();
				while (rs.next())
				 {
					 Paciente fun = new Paciente();
					 fun.setNomeCompleto(rs.getString("NomeCompleto"));
					 fun.setServico(rs.getString("servico"));
					 Calendar data = Calendar.getInstance();
				     data.setTime(rs.getDate("data_nascimento"));
				     fun.setDataNasc(data);
				     fun.setIdade(rs.getDate("data_nascimento"));
				     fun.setData_do_agendamento(data);
				     fun.setData(rs.getDate("data_do_agendamento"));
				     fun.setNome_doutor(rs.getString("NomeCompleto1"));
				     fun.setNomegenero(rs.getString("genero"));
				     fun.setFK_consulta_marcada(rs.getInt("id_consulta_marcada"));
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
    public List <Paciente> buscarPorMedico(Date dataI, Date dataF, String medico)
    {
			List <Paciente> lista = new ArrayList<Paciente>();
			String sql = "SELECT * FROM vwmarcadasemconfirmar WHERE data_do_agendamento BETWEEN ? AND ? AND nomeCompleto1 = ?";
			try {
				con = Conexao.getConexao();
				PreparedStatement preparador = con.prepareStatement(sql);
				preparador.setDate(1, dataI);
				preparador.setDate(2, dataF);
				preparador.setString(3, medico);
				ResultSet rs = preparador.executeQuery();
				while (rs.next())
				 {
					 Paciente fun = new Paciente();
					 fun.setNomeCompleto(rs.getString("NomeCompleto"));
					 fun.setServico(rs.getString("servico"));
					 Calendar data = Calendar.getInstance();
				     data.setTime(rs.getDate("data_nascimento"));
				     fun.setDataNasc(data);
				     fun.setIdade(rs.getDate("data_nascimento"));
				     fun.setData_do_agendamento(data);
				     fun.setData(rs.getDate("data_do_agendamento"));
				     fun.setNome_doutor(rs.getString("NomeCompleto1"));
				     fun.setNomegenero(rs.getString("genero"));
				     fun.setFK_consulta_marcada(rs.getInt("id_consulta_marcada"));
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
    public List <Paciente> buscarPorGenero(Date dataI, Date dataF, String genero)
    {
			List <Paciente> lista = new ArrayList<Paciente>();
			String sql = "SELECT * FROM vwmarcadasemconfirmar WHERE data_do_agendamento BETWEEN ? AND ? AND FK_genero = ?";
			try {
				con = Conexao.getConexao();
				PreparedStatement preparador = con.prepareStatement(sql);
				preparador.setDate(1, dataI);
				preparador.setDate(2, dataF);
				preparador.setString(3, genero);
				ResultSet rs = preparador.executeQuery();
				while (rs.next())
				 {
					 Paciente fun = new Paciente();
					 fun.setNomeCompleto(rs.getString("NomeCompleto"));
					 fun.setServico(rs.getString("servico"));
					 Calendar data = Calendar.getInstance();
				     data.setTime(rs.getDate("data_nascimento"));
				     fun.setDataNasc(data);
				     fun.setIdade(rs.getDate("data_nascimento"));
				     fun.setData_do_agendamento(data);
				     fun.setData(rs.getDate("data_do_agendamento"));
				     fun.setNome_doutor(rs.getString("NomeCompleto1"));
				     fun.setNomegenero(rs.getString("genero"));
				     fun.setFK_consulta_marcada(rs.getInt("id_consulta_marcada"));
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
    public List <Paciente> buscarPorData(Date dataI, Date dataF)
    {
			List <Paciente> lista = new ArrayList<Paciente>();
			String sql = "SELECT * FROM vwmarcadasemconfirmar WHERE data_do_agendamento BETWEEN ? and ?";
			try {
				con = Conexao.getConexao();
				PreparedStatement preparador = con.prepareStatement(sql);
				preparador.setDate(1, dataI);
				preparador.setDate(2, dataF);
				ResultSet rs = preparador.executeQuery();
				while (rs.next())
				 {
					 Paciente fun = new Paciente();
					 fun.setNomeCompleto(rs.getString("NomeCompleto"));
					 fun.setServico(rs.getString("servico"));
					 Calendar data = Calendar.getInstance();
				     data.setTime(rs.getDate("data_nascimento"));
				     fun.setDataNasc(data);
				     fun.setIdade(rs.getDate("data_nascimento"));
				     fun.setData_do_agendamento(data);
				     fun.setData(rs.getDate("data_do_agendamento"));
				     fun.setNome_doutor(rs.getString("NomeCompleto1"));
				     fun.setNomegenero(rs.getString("genero"));
				     fun.setFK_consulta_marcada(rs.getInt("id_consulta_marcada"));
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
    public List <Paciente> buscarPorNomeAndIdade(String dataI, String dataF, String nome, String idade)
    {
			List <Paciente> lista = new ArrayList<Paciente>();
			String sql = "SELECT * FROM vwmarcadasemconfirmar WHERE data_do_agendamento BETWEEN ? and ? AND nomeCompleto LIKE '"+nome+"%' OR ULTIMO_NOME LIKE '"+nome+"%' AND FK_paciente = ? ORDER BY (NOME) AND IDADE BETWEEN ? AND ?";
			try {
				con = Conexao.getConexao();
				PreparedStatement preparador = con.prepareStatement(sql);
				preparador.setString(1, dataI);
				preparador.setString(2, dataF);
				preparador.setString(3, nome);
				preparador.setString(4, idade);
				ResultSet rs = preparador.executeQuery();
				while (rs.next())
				 {
					 Paciente fun = new Paciente();
					 fun.setNomeCompleto(rs.getString("NomeCompleto"));
					 fun.setServico(rs.getString("servico"));
					 Calendar data = Calendar.getInstance();
				     data.setTime(rs.getDate("data_nascimento"));
				     fun.setDataNasc(data);
				     fun.setIdade(rs.getDate("data_nascimento"));
				     fun.setData_do_agendamento(data);
				     fun.setData(rs.getDate("data_do_agendamento"));
				     fun.setNome_doutor(rs.getString("NomeCompleto1"));
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
    public List <Paciente> buscarPorNomeAndIdade(Date dataI, Date dataF, String nome, String idade, String idade1)
    {
			List <Paciente> lista = new ArrayList<Paciente>();
			String sql = "SELECT * FROM vwmarcadasemconfirmar WHERE data_do_agendamento BETWEEN ? and ? AND nomeCompleto LIKE '"+nome+"%' OR ULTIMO_NOME LIKE '"+nome+"%' AND FK_paciente = ? ORDER BY (NOME) AND IDADE BETWEEN ? AND ?";
			try {
				con = Conexao.getConexao();
				PreparedStatement preparador = con.prepareStatement(sql);
				preparador.setDate(1, dataI);
				preparador.setDate(2, dataF);
				preparador.setString(3, nome);
				preparador.setString(4, idade);
				preparador.setString(5, idade1);
				ResultSet rs = preparador.executeQuery();
				while (rs.next())
				 {
					 Paciente fun = new Paciente();
					 fun.setNomeCompleto(rs.getString("NomeCompleto"));
					 fun.setServico(rs.getString("servico"));
					 Calendar data = Calendar.getInstance();
				     data.setTime(rs.getDate("data_nascimento"));
				     fun.setDataNasc(data);
				     fun.setIdade(rs.getDate("data_nascimento"));
				     fun.setData_do_agendamento(data);
				     fun.setData(rs.getDate("data_do_agendamento"));
				     fun.setNome_doutor(rs.getString("NomeCompleto1"));
				     fun.setNomegenero(rs.getString("genero"));
				     fun.setFK_consulta_marcada(rs.getInt("id_consulta_marcada"));
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
    public List <Paciente> buscarPorNomeAndGenero(Date dataI,Date dataF,String nome, String genero)
    {
			List <Paciente> lista = new ArrayList<Paciente>();
			String sql = "SELECT * FROM vwmarcadasemconfirmar WHERE data_do_agendamento BETWEEN ? and ? AND nomeCompleto LIKE ? AND FK_genero =?";
			try {
				con = Conexao.getConexao();
				PreparedStatement preparador = con.prepareStatement(sql);
				preparador.setDate(1, dataI);
				preparador.setDate(2, dataF);
				preparador.setString(3, nome);
				preparador.setString(4, genero);
				ResultSet rs = preparador.executeQuery();
				while (rs.next())
				 {
					 Paciente fun = new Paciente();
					 fun.setNomeCompleto(rs.getString("NomeCompleto"));
					 fun.setServico(rs.getString("servico"));
					 Calendar data = Calendar.getInstance();
				     data.setTime(rs.getDate("data_nascimento"));
				     fun.setDataNasc(data);
				     fun.setIdade(rs.getDate("data_nascimento"));
				     fun.setData_do_agendamento(data);
				     fun.setData(rs.getDate("data_do_agendamento"));
				     fun.setNome_doutor(rs.getString("NomeCompleto1"));
				     fun.setNomegenero(rs.getString("genero"));
				     fun.setFK_consulta_marcada(rs.getInt("id_consulta_marcada"));
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
    public List <Paciente> buscarPorNomeAndServico(Date dataI, Date dataF, String nome, String servico)
    {
			List <Paciente> lista = new ArrayList<Paciente>();
			String sql = "SELECT * FROM vwmarcadasemconfirmar WHERE data_do_agendamento BETWEEN ? and ? AND nomeCompleto LIKE ? AND servico = ?";
			try {
				con = Conexao.getConexao();
				PreparedStatement preparador = con.prepareStatement(sql);
				preparador.setDate(1, dataI);
				preparador.setDate(2, dataF);
				preparador.setString(3, nome);
				preparador.setString(4, servico);
				ResultSet rs = preparador.executeQuery();
				while (rs.next())
				 {
					 Paciente fun = new Paciente();
					 fun.setNomeCompleto(rs.getString("NomeCompleto"));
					 fun.setServico(rs.getString("servico"));
					 Calendar data = Calendar.getInstance();
				     data.setTime(rs.getDate("data_nascimento"));
				     fun.setDataNasc(data);
				     fun.setIdade(rs.getDate("data_nascimento"));
				     fun.setData_do_agendamento(data);
				     fun.setData(rs.getDate("data_do_agendamento"));
				     fun.setNome_doutor(rs.getString("NomeCompleto1"));
				     fun.setNomegenero(rs.getString("genero"));
				     fun.setFK_consulta_marcada(rs.getInt("id_consulta_marcada"));
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
    public List <Paciente> buscarPorNomeAndMedico(Date dataI,Date dataF,String nome,String medico)
    {
			List <Paciente> lista = new ArrayList<Paciente>();
			String sql = "SELECT * FROM vwhistoricodeconsultas WHERE data_do_agendamento BETWEEN ? and ? AND nomeCompleto LIKE ? AND nomeCompleto1 = ?";
			try {
				con = Conexao.getConexao();
				PreparedStatement preparador = con.prepareStatement(sql);
				preparador.setDate(1, dataI);
				preparador.setDate(2, dataF);
				preparador.setString(3, "%" + nome + "%");
				preparador.setString(4, medico);
				ResultSet rs = preparador.executeQuery();
				while (rs.next())
				 {
					 Paciente fun = new Paciente();
					 fun.setNomeCompleto(rs.getString("NomeCompleto"));
					 fun.setServico(rs.getString("servico"));
					 Calendar data = Calendar.getInstance();
				     data.setTime(rs.getDate("data_nascimento"));
				     fun.setDataNasc(data);
				     fun.setIdade(rs.getDate("data_nascimento"));
				     fun.setData_do_agendamento(data);
				     fun.setData(rs.getDate("data_do_agendamento"));
				     fun.setNome_doutor(rs.getString("NomeCompleto1"));
				     fun.setNomegenero(rs.getString("genero"));
				     fun.setFK_consulta_marcada(rs.getInt("id_consulta_marcada"));
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
    public List <Paciente> buscarPorIdadesAndGenero(Date dataI, Date dataF, String idade, String idades, String genero)
    {
			List <Paciente> lista = new ArrayList<Paciente>();
			String sql = "SELECT * FROM vwmarcadasemconfirmar WHERE data_do_agendamento BETWEEN ? and ? AND idade BETWEEN ? and ? AND FK_genero =?";
			try {
				con = Conexao.getConexao();
				PreparedStatement preparador = con.prepareStatement(sql);
				preparador.setDate(1, dataI);
				preparador.setDate(2, dataF);
				preparador.setString(3, idade);
				preparador.setString(4, idades);
				preparador.setString(5, genero);
				ResultSet rs = preparador.executeQuery();
				while (rs.next())
				 {
					 Paciente fun = new Paciente();
					 fun.setNomeCompleto(rs.getString("NomeCompleto"));
					 fun.setServico(rs.getString("servico"));
					 Calendar data = Calendar.getInstance();
				     data.setTime(rs.getDate("data_nascimento"));
				     fun.setDataNasc(data);
				     fun.setIdade(rs.getDate("data_nascimento"));
				     fun.setData_do_agendamento(data);
				     fun.setData(rs.getDate("data_do_agendamento"));
				     fun.setNome_doutor(rs.getString("NomeCompleto1"));
				     fun.setNomegenero(rs.getString("genero"));
				     fun.setFK_consulta_marcada(rs.getInt("id_consulta_marcada"));
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
    public List <Paciente> buscarPorIdadesAndGeneroAndServico(Date dataI, Date dataF, String idade, String idades, String genero, String servico)
    {
			List <Paciente> lista = new ArrayList<Paciente>();
			String sql = "SELECT * FROM vwmarcadasemconfirmar WHERE data_do_agendamento BETWEEN ? and ? AND IDADE BETWEEN ? and ? AND FK_genero =? AND servico =?";
			try {
				con = Conexao.getConexao();
				PreparedStatement preparador = con.prepareStatement(sql);
				preparador.setDate(1, dataI);
				preparador.setDate(2, dataF);
				preparador.setString(3, idade);
				preparador.setString(4, idades);
				preparador.setString(5, genero);
				preparador.setString(6, servico);
				ResultSet rs = preparador.executeQuery();
				while (rs.next())
				 {
					 Paciente fun = new Paciente();
					 fun.setNomeCompleto(rs.getString("NomeCompleto"));
					 fun.setServico(rs.getString("servico"));
					 Calendar data = Calendar.getInstance();
				     data.setTime(rs.getDate("data_nascimento"));
				     fun.setDataNasc(data);
				     fun.setIdade(rs.getDate("data_nascimento"));
				     fun.setData_do_agendamento(data);
				     fun.setData(rs.getDate("data_do_agendamento"));
				     fun.setNome_doutor(rs.getString("NomeCompleto1"));
				     fun.setNomegenero(rs.getString("genero"));
				     fun.setFK_consulta_marcada(rs.getInt("id_consulta_marcada"));
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
//  Em falta
    public List <Paciente> buscarPorIdadesAndGeneroAndMedico(Date dataI, Date dataF, String idade, String idades, String genero, String medico)
    {
			List <Paciente> lista = new ArrayList<Paciente>();
			String sql = "SELECT * FROM vwmarcadasemconfirmar WHERE data_do_agendamento BETWEEN ? and ? AND IDADE BETWEEN ? and ? AND FK_genero =? AND nomeCompleto1 =?";
			try {
				con = Conexao.getConexao();
				PreparedStatement preparador = con.prepareStatement(sql);
				preparador.setDate(1, dataI);
				preparador.setDate(2, dataF);
				preparador.setString(3, idade);
				preparador.setString(4, idades);
				preparador.setString(5, genero);
				preparador.setString(6, medico);
				ResultSet rs = preparador.executeQuery();
				while (rs.next())
				 {
					 Paciente fun = new Paciente();
					 fun.setNomeCompleto(rs.getString("NomeCompleto"));
					 fun.setServico(rs.getString("servico"));
					 Calendar data = Calendar.getInstance();
				     data.setTime(rs.getDate("data_nascimento"));
				     fun.setDataNasc(data);
				     fun.setIdade(rs.getDate("data_nascimento"));
				     fun.setData_do_agendamento(data);
				     fun.setData(rs.getDate("data_do_agendamento"));
				     fun.setNome_doutor(rs.getString("NomeCompleto1"));
				     fun.setNomegenero(rs.getString("genero"));
				     fun.setFK_consulta_marcada(rs.getInt("id_consulta_marcada"));
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
    public List <Paciente> buscarPorGeneroAndServico(Date dataI, Date dataF, String genero, String servico)
    {
			List <Paciente> lista = new ArrayList<Paciente>();
			String sql = "SELECT * FROM vwmarcadasemconfirmar WHERE data_do_agendamento BETWEEN ? and ? AND FK_genero =? AND servico =?";
			try {
				con = Conexao.getConexao();
				PreparedStatement preparador = con.prepareStatement(sql);
				preparador.setDate(1, dataI);
				preparador.setDate(2, dataF);
				preparador.setString(3, genero);
				preparador.setString(4, servico);
				ResultSet rs = preparador.executeQuery();
				while (rs.next())
				 {
					 Paciente fun = new Paciente();
					 fun.setNomeCompleto(rs.getString("NomeCompleto"));
					 fun.setServico(rs.getString("servico"));
					 Calendar data = Calendar.getInstance();
				     data.setTime(rs.getDate("data_nascimento"));
				     fun.setDataNasc(data);
				     fun.setIdade(rs.getDate("data_nascimento"));
				     fun.setData_do_agendamento(data);
				     fun.setData(rs.getDate("data_do_agendamento"));
				     fun.setNome_doutor(rs.getString("NomeCompleto1"));
				     fun.setNomegenero(rs.getString("genero"));
				     fun.setFK_consulta_marcada(rs.getInt("id_consulta_marcada"));
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
    public List <Paciente> buscarPorGeneroAndMedico(Date dataI, Date dataF, String genero, String medico)
    {
			List <Paciente> lista = new ArrayList<Paciente>();
			String sql = "SELECT * FROM vwmarcadasemconfirmar WHERE data_do_agendamento BETWEEN ? and ? AND FK_genero =? AND nomeCompleto1 =?";
			try {
				con = Conexao.getConexao();
				PreparedStatement preparador = con.prepareStatement(sql);
				preparador.setDate	(1, dataI);
				preparador.setDate	(2, dataF);
				preparador.setString(3, genero);
				preparador.setString(4, medico);
				ResultSet rs = preparador.executeQuery();
				while (rs.next())
				 {
					 Paciente fun = new Paciente();
					 fun.setNomeCompleto(rs.getString("NomeCompleto"));
					 fun.setServico(rs.getString("servico"));
					 Calendar data = Calendar.getInstance();
				     data.setTime(rs.getDate("data_nascimento"));
				     fun.setDataNasc(data);
				     fun.setIdade(rs.getDate("data_nascimento"));
				     fun.setData_do_agendamento(data);
				     fun.setData(rs.getDate("data_do_agendamento"));
				     fun.setNome_doutor(rs.getString("NomeCompleto1"));
				     fun.setNomegenero(rs.getString("genero"));
				     fun.setFK_consulta_marcada(rs.getInt("id_consulta_marcada"));
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
    public List <Paciente> buscarPorServicoAndMedico(Date dataI, Date dataF, String servico, String medico)
    {
			List <Paciente> lista = new ArrayList<Paciente>();
			String sql = "SELECT * FROM vwmarcadasemconfirmar WHERE data_do_agendamento BETWEEN ? and ? AND FK_servico =? AND nomeCompleto1 =?";
			try {
				con = Conexao.getConexao();
				PreparedStatement preparador = con.prepareStatement(sql);
				preparador.setDate	(1, dataI);
				preparador.setDate	(2, dataF);
				preparador.setString(3, servico);
				preparador.setString(4, medico);
				ResultSet rs = preparador.executeQuery();
				while (rs.next())
				 {
					 Paciente fun = new Paciente();
					 fun.setNomeCompleto(rs.getString("NomeCompleto"));
					 fun.setServico(rs.getString("servico"));
					 Calendar data = Calendar.getInstance();
				     data.setTime(rs.getDate("data_nascimento"));
				     fun.setDataNasc(data);
				     fun.setIdade(rs.getDate("data_nascimento"));
				     fun.setData_do_agendamento(data);
				     fun.setData(rs.getDate("data_do_agendamento"));
				     fun.setNome_doutor(rs.getString("NomeCompleto1"));
				     fun.setNomegenero(rs.getString("genero"));
				     fun.setFK_consulta_marcada(rs.getInt("id_consulta_marcada"));
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
    
//    HISTÓRICO DE CONFIRMAÇÃO
    public List <Paciente> buscarPacienteConfirmado()
    {
			List <Paciente> lista = new ArrayList<Paciente>();
			String sql = "SELECT * FROM vwconsultaconfirmadacomdoutor where data_confirmacao = curdate();";
			try {
				con = Conexao.getConexao();
				PreparedStatement preparador = con.prepareStatement(sql);
				ResultSet rs = preparador.executeQuery();
				while (rs.next())
				 {
					 Paciente fun = new Paciente();
					 fun.setNome(rs.getString("nome"));
					 fun.setNomem(rs.getString("nome_meio"));
					 fun.setApelido(rs.getString("ultimo_nome"));
					 fun.setNomegenero(rs.getString("genero"));
					 fun.setServico(rs.getString("servico"));
					 Calendar data = Calendar.getInstance();
				     data.setTime(rs.getDate("data_nascimento"));
				     fun.setDataNasc(data);
				     fun.setIdade(rs.getDate("data_nascimento"));
				     fun.setData_do_agendamento(data);
				     fun.setData(rs.getDate("data_confirmacao"));
				     fun.setNome_doutor(rs.getString("nomeCompleto"));
				     fun.setFK_paciente(rs.getInt("FK_paciente"));
				     fun.setFK_doutor(rs.getInt("FK_doutor"));
				     fun.setFK_consulta_confirmada(rs.getInt("id_consulta_confirmada"));
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
    public List <Paciente> buscarPorNomeConf(Date dataI, Date dataF, String nome)
    {
			List <Paciente> lista = new ArrayList<Paciente>();
			String sql = "SELECT * FROM vwconsultaconfirmadacomdoutor WHERE data_confirmacao BETWEEN ? and ? AND nome LIKE '"+nome+"%' OR ULTIMO_NOME LIKE '"+nome+"%' AND FK_paciente = ? ORDER BY (NOME)";
			try {
				con = Conexao.getConexao();
				PreparedStatement preparador = con.prepareStatement(sql);
				preparador.setDate(1, dataI);
				preparador.setDate(2, dataF);
				preparador.setString(3, nome);
				ResultSet rs = preparador.executeQuery();
				while (rs.next())
				 {
					 Paciente fun = new Paciente();
					 fun.setNome(rs.getString("nome"));
					 fun.setNomem(rs.getString("nome_meio"));
					 fun.setApelido(rs.getString("ultimo_nome"));
					 fun.setNomegenero(rs.getString("genero"));
					 fun.setServico(rs.getString("servico"));
					 Calendar data = Calendar.getInstance();
				     data.setTime(rs.getDate("data_nascimento"));
				     fun.setDataNasc(data);
				     fun.setIdade(rs.getDate("data_nascimento"));
				     fun.setData_do_agendamento(data);
				     fun.setData(rs.getDate("data_confirmacao"));
				     fun.setNome_doutor(rs.getString("nomeCompleto"));
				     fun.setFK_paciente(rs.getInt("FK_paciente"));
				     fun.setFK_doutor(rs.getInt("FK_doutor"));
				     fun.setFK_consulta_confirmada(rs.getInt("id_consulta_confirmada"));
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
    public List <Paciente> buscarPorIdadeConf(Date dataI,Date dataF, String idade)
    {
			List <Paciente> lista = new ArrayList<Paciente>();
			String sql = "SELECT * FROM vwconsultaconfirmadacomdoutor WHERE data_confirmacao BETWEEN ? AND ? AND IDADE =?";
			try {
				con = Conexao.getConexao();
				PreparedStatement preparador = con.prepareStatement(sql);
				preparador.setDate(1, dataI);
				preparador.setDate(2, dataF);
				preparador.setString(3, idade);
				ResultSet rs = preparador.executeQuery();
				while (rs.next())
				 {
					 Paciente fun = new Paciente();
					 fun.setNome(rs.getString("nome"));
					 fun.setNomem(rs.getString("nome_meio"));
					 fun.setApelido(rs.getString("ultimo_nome"));
					 fun.setNomegenero(rs.getString("genero"));
					 fun.setServico(rs.getString("servico"));
					 Calendar data = Calendar.getInstance();
				     data.setTime(rs.getDate("data_nascimento"));
				     fun.setDataNasc(data);
				     fun.setIdade(rs.getDate("data_nascimento"));
				     fun.setData_do_agendamento(data);
				     fun.setData(rs.getDate("data_confirmacao"));
				     fun.setNome_doutor(rs.getString("nomeCompleto"));
				     fun.setFK_paciente(rs.getInt("FK_paciente"));
				     fun.setFK_doutor(rs.getInt("FK_doutor"));
				     fun.setFK_consulta_confirmada(rs.getInt("id_consulta_confirmada"));
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
    public List <Paciente> buscarPorIdadeConfs(Date dataI, Date dataF, String idade1, String idade2)
    {
			List <Paciente> lista = new ArrayList<Paciente>();
			String sql = "SELECT * FROM vwconsultaconfirmadacomdoutor WHERE data_confirmacao BETWEEN ? AND ? AND idade BETWEEN ? AND ?";
			try {
				con = Conexao.getConexao();
				PreparedStatement preparador = con.prepareStatement(sql);
				preparador.setDate(1, dataI);
				preparador.setDate(2, dataF);
				preparador.setString(3, idade1);
				preparador.setString(4, idade2);
				ResultSet rs = preparador.executeQuery();
				while (rs.next())
				 {
					 Paciente fun = new Paciente();
					 fun.setNome(rs.getString("nome"));
					 fun.setNomem(rs.getString("nome_meio"));
					 fun.setApelido(rs.getString("ultimo_nome"));
					 fun.setNomegenero(rs.getString("genero"));
					 fun.setServico(rs.getString("servico"));
					 Calendar data = Calendar.getInstance();
				     data.setTime(rs.getDate("data_nascimento"));
				     fun.setDataNasc(data);
				     fun.setIdade(rs.getDate("data_nascimento"));
				     fun.setData(rs.getDate("data_confirmacao"));
				     fun.setNome_doutor(rs.getString("nomeCompleto"));
				     fun.setFK_paciente(rs.getInt("FK_paciente"));
				     fun.setFK_doutor(rs.getInt("FK_doutor"));
				     fun.setFK_consulta_confirmada(rs.getInt("id_consulta_confirmada"));
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
    public List <Paciente> buscarPorGeneroConf(Date dataI, Date dataF, String genero)
    {
			List <Paciente> lista = new ArrayList<Paciente>();
			String sql = "SELECT * FROM vwconsultaconfirmadacomdoutor WHERE data_confirmacao BETWEEN ? AND ? AND id_genero = ?";
			try {
				con = Conexao.getConexao();
				PreparedStatement preparador = con.prepareStatement(sql);
				preparador.setDate(1, dataI);
				preparador.setDate(2, dataF);
				preparador.setString(3, genero);
				ResultSet rs = preparador.executeQuery();
				while (rs.next())
				 {
					 Paciente fun = new Paciente();
					 fun.setNome(rs.getString("nome"));
					 fun.setNomem(rs.getString("nome_meio"));
					 fun.setApelido(rs.getString("ultimo_nome"));
					 fun.setNomegenero(rs.getString("genero"));
					 fun.setServico(rs.getString("servico"));
					 Calendar data = Calendar.getInstance();
				     data.setTime(rs.getDate("data_nascimento"));
				     fun.setDataNasc(data);
				     fun.setIdade(rs.getDate("data_nascimento"));
				     fun.setData_do_agendamento(data);
				     fun.setData(rs.getDate("data_confirmacao"));
				     fun.setNome_doutor(rs.getString("nomeCompleto"));
				     fun.setFK_paciente(rs.getInt("FK_paciente"));
				     fun.setFK_doutor(rs.getInt("FK_doutor"));
				     fun.setFK_consulta_confirmada(rs.getInt("id_consulta_confirmada"));
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
    public List <Paciente> buscarPorServicoConf(Date dataI, Date dataF, String servico)
    {
			List <Paciente> lista = new ArrayList<Paciente>();
			String sql = "SELECT * FROM vwconsultaconfirmadacomdoutor WHERE data_confirmacao BETWEEN ? AND ? AND servico =?";
			try {
				con = Conexao.getConexao();
				PreparedStatement preparador = con.prepareStatement(sql);
				preparador.setDate(1, dataI);
				preparador.setDate(2, dataF);
				preparador.setString(3, servico);
				ResultSet rs = preparador.executeQuery();
				while (rs.next())
				 {
					 Paciente fun = new Paciente();
					 fun.setNome(rs.getString("nome"));
					 fun.setNomem(rs.getString("nome_meio"));
					 fun.setApelido(rs.getString("ultimo_nome"));
					 fun.setNomegenero(rs.getString("genero"));
					 fun.setServico(rs.getString("servico"));
					 Calendar data = Calendar.getInstance();
				     data.setTime(rs.getDate("data_nascimento"));
				     fun.setDataNasc(data);
				     fun.setIdade(rs.getDate("data_nascimento"));
				     fun.setData_do_agendamento(data);
				     fun.setData(rs.getDate("data_confirmacao"));
				     fun.setNome_doutor(rs.getString("nomeCompleto"));
				     fun.setFK_paciente(rs.getInt("FK_paciente"));
				     fun.setFK_doutor(rs.getInt("FK_doutor"));
				     fun.setFK_consulta_confirmada(rs.getInt("id_consulta_confirmada"));
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
    public List <Paciente> buscarPorMedicoConf(Date dataI, Date dataF, String nome)
    {
			List <Paciente> lista = new ArrayList<Paciente>();
			String sql = "SELECT * FROM vwconsultaconfirmadacomdoutor WHERE data_confirmacao BETWEEN ? AND ? AND nomeCompleto LIKE ?";
			try {
				con = Conexao.getConexao();
				PreparedStatement preparador = con.prepareStatement(sql);
				preparador.setDate(1, dataI);
				preparador.setDate(2, dataF);
				preparador.setString(3, nome);
				ResultSet rs = preparador.executeQuery();
				while (rs.next())
				 {
					 Paciente fun = new Paciente();
					 fun.setNome(rs.getString("nome"));
					 fun.setNomem(rs.getString("nome_meio"));
					 fun.setApelido(rs.getString("ultimo_nome"));
					 fun.setNomegenero(rs.getString("genero"));
					 fun.setServico(rs.getString("servico"));
					 Calendar data = Calendar.getInstance();
				     data.setTime(rs.getDate("data_nascimento"));
				     fun.setDataNasc(data);
				     fun.setIdade(rs.getDate("data_nascimento"));
				     fun.setData_do_agendamento(data);
				     fun.setData(rs.getDate("data_confirmacao"));
				     fun.setNome_doutor(rs.getString("nomeCompleto"));
				     fun.setFK_paciente(rs.getInt("FK_paciente"));
				     fun.setFK_doutor(rs.getInt("FK_doutor"));
				     fun.setFK_consulta_confirmada(rs.getInt("id_consulta_confirmada"));
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
    public List <Paciente> buscarPorDataConf(Date dataI,Date dataF)
    {
			List <Paciente> lista = new ArrayList<Paciente>();
			String sql = "SELECT * FROM vwconsultaconfirmadacomdoutor WHERE data_confirmacao BETWEEN ? AND ?";
			try {
				con = Conexao.getConexao();
				PreparedStatement preparador = con.prepareStatement(sql);
				preparador.setDate(1, dataI);
				preparador.setDate(2, dataF);
				ResultSet rs = preparador.executeQuery();
				while (rs.next())
				 {
					 Paciente fun = new Paciente();
					 fun.setNome(rs.getString("nome"));
					 fun.setNomem(rs.getString("nome_meio"));
					 fun.setApelido(rs.getString("ultimo_nome"));
					 fun.setNomegenero(rs.getString("genero"));
					 fun.setServico(rs.getString("servico"));
					 Calendar data = Calendar.getInstance();
				     data.setTime(rs.getDate("data_nascimento"));
				     fun.setDataNasc(data);
				     fun.setIdade(rs.getDate("data_nascimento"));
				     fun.setData_do_agendamento(data);
				     fun.setData(rs.getDate("data_confirmacao"));
				     fun.setNome_doutor(rs.getString("nomeCompleto"));
				     fun.setFK_paciente(rs.getInt("FK_paciente"));
				     fun.setFK_doutor(rs.getInt("FK_doutor"));
				     fun.setFK_consulta_confirmada(rs.getInt("id_consulta_confirmada"));
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
    public List <Paciente> buscarPorNomeAndIdadeConf(Date dataI,Date dataF, String nome, String idade1, String idade2)
    {
			List <Paciente> lista = new ArrayList<Paciente>();
			String sql = "SELECT * FROM vwconsultaconfirmadacomdoutor WHERE data_confirmacao BETWEEN ? AND ? AND nome LIKE '"+nome+"%' OR ULTIMO_NOME LIKE '"+nome+"%' AND FK_paciente = ? ORDER BY (NOME) AND IDADE BETWEEN ? AND ?";
			try {
				con = Conexao.getConexao();
				PreparedStatement preparador = con.prepareStatement(sql);
				preparador.setDate(1, dataI);
				preparador.setDate(2, dataF);
				preparador.setString(3, nome);
				preparador.setString(4, idade1);
				preparador.setString(5, idade2);
				ResultSet rs = preparador.executeQuery();
				while (rs.next())
				 {
					 Paciente fun = new Paciente();
					 fun.setNome(rs.getString("nome"));
					 fun.setNomem(rs.getString("nome_meio"));
					 fun.setApelido(rs.getString("ultimo_nome"));
					 fun.setNomegenero(rs.getString("genero"));
					 fun.setServico(rs.getString("servico"));
					 Calendar data = Calendar.getInstance();
				     data.setTime(rs.getDate("data_nascimento"));
				     fun.setDataNasc(data);
				     fun.setIdade(rs.getDate("data_nascimento"));
				     fun.setData_do_agendamento(data);
				     fun.setData(rs.getDate("data_confirmacao"));
				     fun.setNome_doutor(rs.getString("nomeCompleto"));
				     fun.setFK_paciente(rs.getInt("FK_paciente"));
				     fun.setFK_doutor(rs.getInt("FK_doutor"));
				     fun.setFK_consulta_confirmada(rs.getInt("id_consulta_confirmada"));
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
    public List <Paciente> buscarPorNomeAndGeneroConf(Date dataI,Date dataF, String nome, String genero)
    {
			List <Paciente> lista = new ArrayList<Paciente>();
			String sql = "SELECT * FROM vwconsultaconfirmadacomdoutor WHERE data_confirmacao BETWEEN ? AND ? AND nome LIKE '"+nome+"%' OR ULTIMO_NOME LIKE '"+nome+"%' AND FK_paciente = ? ORDER BY (NOME) AND id_genero =?";
			try {
				con = Conexao.getConexao();
				PreparedStatement preparador = con.prepareStatement(sql);
				preparador.setDate(1, dataI);
				preparador.setDate(2, dataF);
				preparador.setString(3, nome);
				preparador.setString(4, genero);
				ResultSet rs = preparador.executeQuery();
				while (rs.next())
				 {
					 Paciente fun = new Paciente();
					 fun.setNome(rs.getString("nome"));
					 fun.setNomem(rs.getString("nome_meio"));
					 fun.setApelido(rs.getString("ultimo_nome"));
					 fun.setNomegenero(rs.getString("genero"));
					 fun.setServico(rs.getString("servico"));
					 Calendar data = Calendar.getInstance();
				     data.setTime(rs.getDate("data_nascimento"));
				     fun.setDataNasc(data);
				     fun.setIdade(rs.getDate("data_nascimento"));
				     fun.setData_do_agendamento(data);
				     fun.setData(rs.getDate("data_confirmacao"));
				     fun.setNome_doutor(rs.getString("nomeCompleto"));
				     fun.setFK_paciente(rs.getInt("FK_paciente"));
				     fun.setFK_doutor(rs.getInt("FK_doutor"));
				     fun.setFK_consulta_confirmada(rs.getInt("id_consulta_confirmada"));
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
    public List <Paciente> buscarPorNomeAndServicoConf(Date dataI,Date dataF, String nome, String servico)
    {
			List <Paciente> lista = new ArrayList<Paciente>();
			String sql = "SELECT * FROM vwconsultaconfirmadacomdoutor WHERE data_confirmacao BETWEEN ? AND ? AND nome LIKE '"+nome+"%' OR ULTIMO_NOME LIKE '"+nome+"%' AND FK_paciente = ? ORDER BY (NOME) AND servico =?";
			try {
				con = Conexao.getConexao();
				PreparedStatement preparador = con.prepareStatement(sql);
				preparador.setDate(1, dataI);
				preparador.setDate(2, dataF);
				preparador.setString(3, nome);
				preparador.setString(4, servico);
				ResultSet rs = preparador.executeQuery();
				while (rs.next())
				 {
					 Paciente fun = new Paciente();
					 fun.setNome(rs.getString("nome"));
					 fun.setNomem(rs.getString("nome_meio"));
					 fun.setApelido(rs.getString("ultimo_nome"));
					 fun.setNomegenero(rs.getString("genero"));
					 fun.setServico(rs.getString("servico"));
					 Calendar data = Calendar.getInstance();
				     data.setTime(rs.getDate("data_nascimento"));
				     fun.setDataNasc(data);
				     fun.setIdade(rs.getDate("data_nascimento"));
				     fun.setData_do_agendamento(data);
				     fun.setData(rs.getDate("data_confirmacao"));
				     fun.setNome_doutor(rs.getString("nomeCompleto"));
				     fun.setFK_paciente(rs.getInt("FK_paciente"));
				     fun.setFK_doutor(rs.getInt("FK_doutor"));
				     fun.setFK_consulta_confirmada(rs.getInt("id_consulta_confirmada"));
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
    public List <Paciente> buscarPorNomeAndMedicoConf(Date dataI,Date dataF, String nome, String medico)
    {
			List <Paciente> lista = new ArrayList<Paciente>();
			String sql = "SELECT * FROM vwconsultaconfirmadacomdoutor WHERE data_confirmacao BETWEEN ? AND ? AND nome LIKE '"+nome+"%' OR ULTIMO_NOME LIKE '"+nome+"%' AND FK_paciente = ? ORDER BY (NOME) AND medico =?";
			try {
				con = Conexao.getConexao();
				PreparedStatement preparador = con.prepareStatement(sql);
				preparador.setDate(1, dataI);
				preparador.setDate(2, dataF);
				preparador.setString(3, nome);
				preparador.setString(4, medico);
				ResultSet rs = preparador.executeQuery();
				while (rs.next())
				 {
					 Paciente fun = new Paciente();
					 fun.setNome(rs.getString("nome"));
					 fun.setNomem(rs.getString("nome_meio"));
					 fun.setApelido(rs.getString("ultimo_nome"));
					 fun.setNomegenero(rs.getString("genero"));
					 fun.setServico(rs.getString("servico"));
					 Calendar data = Calendar.getInstance();
				     data.setTime(rs.getDate("data_nascimento"));
				     fun.setDataNasc(data);
				     fun.setIdade(rs.getDate("data_nascimento"));
				     fun.setData_do_agendamento(data);
				     fun.setData(rs.getDate("data_confirmacao"));
				     fun.setNome_doutor(rs.getString("nomeCompleto"));
				     fun.setFK_paciente(rs.getInt("FK_paciente"));
				     fun.setFK_doutor(rs.getInt("FK_doutor"));
				     fun.setFK_consulta_confirmada(rs.getInt("id_consulta_confirmada"));
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
    public List <Paciente> buscarPorIdadeAndGeneroConf(Date dataI,Date dataF, String idade1, String idade2, String genero)
    {
			List <Paciente> lista = new ArrayList<Paciente>();
			String sql = "SELECT * FROM vwconsultaconfirmadacomdoutor WHERE data_confirmacao BETWEEN ? AND ? AND idade BETWEEN ? AND ? AND sexo =?";
			try {
				con = Conexao.getConexao();
				PreparedStatement preparador = con.prepareStatement(sql);
				preparador.setDate(1, dataI);
				preparador.setDate(2, dataF);
				preparador.setString(3, idade1);
				preparador.setString(4, idade2);
				preparador.setString(5, genero);
				ResultSet rs = preparador.executeQuery();
				while (rs.next())
				 {
					 Paciente fun = new Paciente();
					 fun.setNome(rs.getString("nome"));
					 fun.setNomem(rs.getString("nome_meio"));
					 fun.setApelido(rs.getString("ultimo_nome"));
					 fun.setNomegenero(rs.getString("genero"));
					 fun.setServico(rs.getString("servico"));
					 Calendar data = Calendar.getInstance();
				     data.setTime(rs.getDate("data_nascimento"));
				     fun.setDataNasc(data);
				     fun.setIdade(rs.getDate("data_nascimento"));
				     fun.setData_do_agendamento(data);
				     fun.setData(rs.getDate("data_confirmacao"));
				     fun.setNome_doutor(rs.getString("nomeCompleto"));
				     fun.setFK_paciente(rs.getInt("FK_paciente"));
				     fun.setFK_doutor(rs.getInt("FK_doutor"));
				     fun.setFK_consulta_confirmada(rs.getInt("id_consulta_confirmada"));
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
    public List <Paciente> buscarPorIdadeAndServicoConf(Date dataI,Date dataF, String idade1, String idade2, String servico)
    {
			List <Paciente> lista = new ArrayList<Paciente>();
			String sql = "SELECT * FROM vwconsultaconfirmadacomdoutor WHERE data_confirmacao BETWEEN ? AND ? AND idade BETWEEN ? AND ? AND servico =?";
			try {
				con = Conexao.getConexao();
				PreparedStatement preparador = con.prepareStatement(sql);
				preparador.setDate(1, dataI);
				preparador.setDate(2, dataF);
				preparador.setString(3, idade1);
				preparador.setString(4, idade2);
				preparador.setString(5, servico);
				ResultSet rs = preparador.executeQuery();
				while (rs.next())
				 {
					 Paciente fun = new Paciente();
					 fun.setNome(rs.getString("nome"));
					 fun.setNomem(rs.getString("nome_meio"));
					 fun.setApelido(rs.getString("ultimo_nome"));
					 fun.setNomegenero(rs.getString("genero"));
					 fun.setServico(rs.getString("servico"));
					 Calendar data = Calendar.getInstance();
				     data.setTime(rs.getDate("data_nascimento"));
				     fun.setDataNasc(data);
				     fun.setIdade(rs.getDate("data_nascimento"));
				     fun.setData_do_agendamento(data);
				     fun.setData(rs.getDate("data_confirmacao"));
				     fun.setNome_doutor(rs.getString("nomeCompleto"));
				     fun.setFK_paciente(rs.getInt("FK_paciente"));
				     fun.setFK_doutor(rs.getInt("FK_doutor"));
				     fun.setFK_consulta_confirmada(rs.getInt("id_consulta_confirmada"));
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
    public List <Paciente> buscarPorIdadeAndMedicoConf(Date dataI,Date dataF, String idade1, String idade2, String medico)
    {
			List <Paciente> lista = new ArrayList<Paciente>();
			String sql = "SELECT * FROM vwconsultaconfirmadacomdoutor WHERE data_confirmacao BETWEEN ? AND ? AND idade BETWEEN ? AND ? AND medico =?";
			try {
				con = Conexao.getConexao();
				PreparedStatement preparador = con.prepareStatement(sql);
				preparador.setDate(1, dataI);
				preparador.setDate(2, dataF);
				preparador.setString(3, idade1);
				preparador.setString(4, idade2);
				preparador.setString(5, medico);
				ResultSet rs = preparador.executeQuery();
				while (rs.next())
				 {
					 Paciente fun = new Paciente();
					 fun.setNome(rs.getString("nome"));
					 fun.setNomem(rs.getString("nome_meio"));
					 fun.setApelido(rs.getString("ultimo_nome"));
					 fun.setNomegenero(rs.getString("genero"));
					 fun.setServico(rs.getString("servico"));
					 Calendar data = Calendar.getInstance();
				     data.setTime(rs.getDate("data_nascimento"));
				     fun.setDataNasc(data);
				     fun.setIdade(rs.getDate("data_nascimento"));
				     fun.setData_do_agendamento(data);
				     fun.setData(rs.getDate("data_confirmacao"));
				     fun.setNome_doutor(rs.getString("nomeCompleto"));
				     fun.setFK_paciente(rs.getInt("FK_paciente"));
				     fun.setFK_doutor(rs.getInt("FK_doutor"));
				     fun.setFK_consulta_confirmada(rs.getInt("id_consulta_confirmada"));
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
    public List <Paciente> buscarPorGeneroAndServicoConf(Date dataI,Date dataF, String genero, String servico)
    {
			List <Paciente> lista = new ArrayList<Paciente>();
			String sql = "SELECT * FROM vwconsultaconfirmadacomdoutor WHERE data_confirmacao BETWEEN ? AND ? AND sexo = ? AND servico =?";
			try {
				con = Conexao.getConexao();
				PreparedStatement preparador = con.prepareStatement(sql);
				preparador.setDate(1, dataI);
				preparador.setDate(2, dataF);
				preparador.setString(3, servico);
				ResultSet rs = preparador.executeQuery();
				while (rs.next())
				 {
					 Paciente fun = new Paciente();
					 fun.setNome(rs.getString("nome"));
					 fun.setNomem(rs.getString("nome_meio"));
					 fun.setApelido(rs.getString("ultimo_nome"));
					 fun.setNomegenero(rs.getString("genero"));
					 fun.setServico(rs.getString("servico"));
					 Calendar data = Calendar.getInstance();
				     data.setTime(rs.getDate("data_nascimento"));
				     fun.setDataNasc(data);
				     fun.setIdade(rs.getDate("data_nascimento"));
				     fun.setData_do_agendamento(data);
				     fun.setData(rs.getDate("data_confirmacao"));
				     fun.setNome_doutor(rs.getString("nomeCompleto"));
				     fun.setFK_paciente(rs.getInt("FK_paciente"));
				     fun.setFK_doutor(rs.getInt("FK_doutor"));
				     fun.setFK_consulta_confirmada(rs.getInt("id_consulta_confirmada"));
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
    public List <Paciente> buscarPorGeneroAndMedicoConf(Date dataI,Date dataF, String genero, String medico)
    {
			List <Paciente> lista = new ArrayList<Paciente>();
			String sql = "SELECT * FROM vwconsultaconfirmadacomdoutor WHERE data_confirmacao BETWEEN ? AND ? AND sexo = ? AND medico =?";
			try {
				con = Conexao.getConexao();
				PreparedStatement preparador = con.prepareStatement(sql);
				preparador.setDate(1, dataI);
				preparador.setDate(2, dataF);
				preparador.setString(3, medico);
				ResultSet rs = preparador.executeQuery();
				while (rs.next())
				 {
					 Paciente fun = new Paciente();
					 fun.setNome(rs.getString("nome"));
					 fun.setNomem(rs.getString("nome_meio"));
					 fun.setApelido(rs.getString("ultimo_nome"));
					 fun.setNomegenero(rs.getString("genero"));
					 fun.setServico(rs.getString("servico"));
					 Calendar data = Calendar.getInstance();
				     data.setTime(rs.getDate("data_nascimento"));
				     fun.setDataNasc(data);
				     fun.setIdade(rs.getDate("data_nascimento"));
				     fun.setData_do_agendamento(data);
				     fun.setData(rs.getDate("data_confirmacao"));
				     fun.setNome_doutor(rs.getString("nomeCompleto"));
				     fun.setFK_paciente(rs.getInt("FK_paciente"));
				     fun.setFK_doutor(rs.getInt("FK_doutor"));
				     fun.setFK_consulta_confirmada(rs.getInt("id_consulta_confirmada"));
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
    public List <Paciente> buscarPorServicoAndMedicoConf(Date dataI,Date dataF, String servico, String medico)
    {
			List <Paciente> lista = new ArrayList<Paciente>();
			String sql = "SELECT * FROM vwconsultaconfirmadacomdoutor WHERE data_confirmacao BETWEEN ? AND ? AND servico = ? AND medico =?";
			try {
				con = Conexao.getConexao();
				PreparedStatement preparador = con.prepareStatement(sql);
				preparador.setDate(1, dataI);
				preparador.setDate(2, dataF);
				preparador.setString(3, servico);
				preparador.setString(4, medico);
				ResultSet rs = preparador.executeQuery();
				while (rs.next())
				 {
					 Paciente fun = new Paciente();
					 fun.setNome(rs.getString("nome"));
					 fun.setNomem(rs.getString("nome_meio"));
					 fun.setApelido(rs.getString("ultimo_nome"));
					 fun.setNomegenero(rs.getString("genero"));
					 fun.setServico(rs.getString("servico"));
					 Calendar data = Calendar.getInstance();
				     data.setTime(rs.getDate("data_nascimento"));
				     fun.setDataNasc(data);
				     fun.setIdade(rs.getDate("data_nascimento"));
				     fun.setData_do_agendamento(data);
				     fun.setData(rs.getDate("data_confirmacao"));
				     fun.setNome_doutor(rs.getString("nomeCompleto"));
				     fun.setFK_paciente(rs.getInt("FK_paciente"));
				     fun.setFK_doutor(rs.getInt("FK_doutor"));
				     fun.setFK_consulta_confirmada(rs.getInt("id_consulta_confirmada"));
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
    
    
//    HISTÓRICO DE CADASTRO DE PACIENTE  SELECTS >>>
    public List <PacienteRelatorio> buscarPacienteCadastro()
    {
			List <PacienteRelatorio> lista = new ArrayList<PacienteRelatorio>();
			String sql = "SELECT * FROM vwtodasinformacoespaciente where data_registo = curdate();";
			try {
				con = Conexao.getConexao();
				PreparedStatement preparador = con.prepareStatement(sql);
				ResultSet rs = preparador.executeQuery();
				while (rs.next())
				 {
					PacienteRelatorio fun = new PacienteRelatorio();
					 fun.setNumero(rs.getString("numeroProcesso"));
					 fun.setNome(rs.getString("nomeCompleto"));
					 fun.setNomegenero(rs.getString("genero"));
					 fun.setEndereco(rs.getString("endereco"));
					 Calendar data = Calendar.getInstance();
				     data.setTime(rs.getDate("data_nascimento"));
//				     fun.setDataNasc(data);
//					 fun.setTelefone(rs.getLong("telefone"));
//					 fun.setIdade_dopaciente(rs.getInt("idade"));
//					 fun.setNomePais(rs.getString("pais"));
//					 fun.setNomeProv(rs.getString("provincia"));
//					 fun.setNomeMunicipio(rs.getString("municipio"));
//					 fun.setNomeDistrito(rs.getString("distrito"));
//					 fun.setNumero_doc(rs.getString("numero_documento"));
//					 Calendar data1 = Calendar.getInstance();
//					 fun.setData(rs.getDate("data_registo"));
//					 fun.setData_confirmacao(data1);
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
    public List <PacienteRelatorio> buscarPorNomeHistPaciente(Date dataI, Date dataF, String nome)
    {
			List <PacienteRelatorio> lista = new ArrayList<PacienteRelatorio>();
			String sql = "SELECT * FROM vwtodasinformacoespaciente WHERE data_registo BETWEEN ? AND ? AND nomeCompleto LIKE '"+nome+"%' OR entidade = ? ORDER BY (nomeCompleto)";
			try {
				con = Conexao.getConexao();
				PreparedStatement preparador = con.prepareStatement(sql);
				preparador.setDate(1, dataI);
				preparador.setDate(2, dataF);
				preparador.setString(3, nome);
				ResultSet rs = preparador.executeQuery();
				while (rs.next())
				 {
					PacienteRelatorio fun = new PacienteRelatorio();
					 fun.setNumero(rs.getString("numeroProcesso"));
					 fun.setNome(rs.getString("nomeCompleto"));
					 fun.setNomegenero(rs.getString("genero"));
					 fun.setEndereco(rs.getString("endereco"));
					 Calendar data = Calendar.getInstance();
				     data.setTime(rs.getDate("data_nascimento"));
//				     fun.setDataNasc(data);
//					 fun.setTelefone(rs.getLong("telefone"));
//					 fun.setIdade_dopaciente(rs.getInt("idade"));
//					 fun.setNumero_doc(rs.getString("numero_documento"));
//					 fun.setNomePais(rs.getString("pais"));
//					 fun.setNomeProv(rs.getString("provincia"));
//					 fun.setNomeMunicipio(rs.getString("municipio"));
//					 fun.setNomeDistrito(rs.getString("distrito"));
//					 Calendar data1 = Calendar.getInstance();
//					 fun.setData(rs.getDate("data_registo"));
//					 fun.setData_confirmacao(data1);
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
    public List <PacienteRelatorio> buscarPorIdadeHistPaciente(Date dataI, Date dataF, String idade1, String idade2)
    {
			List <PacienteRelatorio> lista = new ArrayList<PacienteRelatorio>();
			String sql = "SELECT * FROM vwtodasinformacoespaciente WHERE data_registo BETWEEN ? AND ? AND IDADE BETWEEN ? AND ?";
			try {
				con = Conexao.getConexao();
				PreparedStatement preparador = con.prepareStatement(sql);
				preparador.setDate(1, dataI);
				preparador.setDate(2, dataF);
				preparador.setString(3, idade1);
				preparador.setString(4, idade2);
				ResultSet rs = preparador.executeQuery();
				while (rs.next())
				 {
					PacienteRelatorio fun = new PacienteRelatorio();
					 fun.setNumero(rs.getString("numeroProcesso"));
					 fun.setNome(rs.getString("nomeCompleto"));
//					 fun.setNumero_doc(rs.getString("numero_documento"));
					 fun.setEndereco(rs.getString("endereco"));
					 fun.setNomegenero(rs.getString("genero"));
//					 Calendar data = Calendar.getInstance();
//				     data.setTime(rs.getDate("data_nascimento"));
//				     fun.setDataNasc(data);
//					 fun.setTelefone(rs.getLong("telefone"));
//					 fun.setIdade_dopaciente(rs.getInt("idade"));
//					 fun.setNomePais(rs.getString("pais"));
//					 fun.setNomeProv(rs.getString("provincia"));
//					 fun.setNomeMunicipio(rs.getString("municipio"));
//					 fun.setNomeDistrito(rs.getString("distrito"));
//					 Calendar data1 = Calendar.getInstance();
//					 fun.setData(rs.getDate("data_registo"));
//					 fun.setData_confirmacao(data1);
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
    public List <PacienteRelatorio> buscarPorIdadeHistPaciente(Date dataI, Date dataF, String idade)
    {
			List <PacienteRelatorio> lista = new ArrayList<PacienteRelatorio>();
			String sql = "SELECT * FROM vwtodasinformacoespaciente WHERE data_registo BETWEEN ? AND ? AND IDADE =?";
			try {
				con = Conexao.getConexao();
				PreparedStatement preparador = con.prepareStatement(sql);
				preparador.setDate(1, dataI);
				preparador.setDate(2, dataF);
				preparador.setString(3, idade);
				ResultSet rs = preparador.executeQuery();
				while (rs.next())
				 {
					PacienteRelatorio fun = new PacienteRelatorio();
					 fun.setNumero(rs.getString("numeroProcesso"));
					 fun.setNome(rs.getString("nomeCompleto"));
//					 fun.setNumero_doc(rs.getString("numero_documento"));
					 fun.setNomegenero(rs.getString("genero"));
					 fun.setEndereco(rs.getString("endereco"));
					 Calendar data = Calendar.getInstance();
				     data.setTime(rs.getDate("data_nascimento"));
//				     fun.setDataNasc(data);
//					 fun.setTelefone(rs.getLong("telefone"));
//					 fun.setIdade_dopaciente(rs.getInt("idade"));
//					 fun.setNumero_doc(rs.getString("numero_documento"));
//					 fun.setNomePais(rs.getString("pais"));
//					 fun.setNomeProv(rs.getString("provincia"));
//					 fun.setNomeMunicipio(rs.getString("municipio"));
//					 fun.setNomeDistrito(rs.getString("distrito"));
//					 Calendar data1 = Calendar.getInstance();
//					 fun.setData(rs.getDate("data_registo"));
//					 fun.setData_confirmacao(data1);
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
    public List <PacienteRelatorio> buscarPorGeneroHistPaciente(Date dataI, Date dataF, String genero)
    {
			List <PacienteRelatorio> lista = new ArrayList<PacienteRelatorio>();
			String sql = "SELECT * FROM vwtodasinformacoespaciente WHERE data_registo BETWEEN ? AND ? AND FK_genero = ?";
			try {
				con = Conexao.getConexao();
				PreparedStatement preparador = con.prepareStatement(sql);
				preparador.setDate(1, dataI);
				preparador.setDate(2, dataF);
				preparador.setString(3, genero);
				ResultSet rs = preparador.executeQuery();
				while (rs.next())
				 {
					PacienteRelatorio fun = new PacienteRelatorio();
					 fun.setNumero(rs.getString("numeroProcesso"));
					 fun.setNome(rs.getString("nomeCompleto"));
//					 fun.setNumero_doc(rs.getString("numero_documento"));
					 fun.setNomegenero(rs.getString("genero"));
					 fun.setEndereco(rs.getString("endereco"));
					 Calendar data = Calendar.getInstance();
				     data.setTime(rs.getDate("data_nascimento"));
//				     fun.setDataNasc(data);
//					 fun.setTelefone(rs.getLong("telefone"));
//					 fun.setIdade_dopaciente(rs.getInt("idade"));
//					 fun.setNumero_doc(rs.getString("numero_documento"));
//					 fun.setNomePais(rs.getString("pais"));
//					 fun.setNomeProv(rs.getString("provincia"));
//					 fun.setNomeMunicipio(rs.getString("municipio"));
//					 fun.setNomeDistrito(rs.getString("distrito"));
//					 Calendar data1 = Calendar.getInstance();
//					 fun.setData(rs.getDate("data_registo"));
//					 fun.setData_confirmacao(data1);
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
    public List <Paciente> buscarPorNacionalidadeHistPaciente(Date dataI, Date dataF, String pais, String prov, String mun, String distr)
    {
			List <Paciente> lista = new ArrayList<Paciente>();
			String sql = "SELECT * FROM vwtodasinformacoespaciente WHERE data_registo BETWEEN ? AND ? AND pais = ? AND provincia = ? AND municipio = ? AND distrito = ?";
			try {
				con = Conexao.getConexao();
				PreparedStatement preparador = con.prepareStatement(sql);
				preparador.setDate(1, dataI);
				preparador.setDate(2, dataF);
				preparador.setString(3, pais);
				preparador.setString(4, prov);
				preparador.setString(5, mun);
				preparador.setString(6, distr);
				ResultSet rs = preparador.executeQuery();
				while (rs.next())
				 {
					 Paciente fun = new Paciente();
					 fun.setNumero_processo(rs.getString("numeroProcesso"));
					 fun.setNomeCompleto(rs.getString("nomeCompleto"));
					 fun.setNumero_doc(rs.getString("numero_documento"));
					 fun.setEndereco(rs.getString("endereco"));
					 fun.setNomegenero(rs.getString("genero"));
					 Calendar data = Calendar.getInstance();
				     data.setTime(rs.getDate("data_nascimento"));
				     fun.setDataNasc(data);
					 fun.setTelefone(rs.getLong("telefone"));
					 fun.setIdade_dopaciente(rs.getInt("idade"));
					 fun.setNomePais(rs.getString("pais"));
					 fun.setNomeProv(rs.getString("provincia"));
					 fun.setNomeMunicipio(rs.getString("municipio"));
					 fun.setNomeDistrito(rs.getString("distrito"));
					 Calendar data1 = Calendar.getInstance();
					 fun.setData(rs.getDate("data_registo"));
					 fun.setData_confirmacao(data1);
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
    public List <Paciente> buscarPorNomeHiPacienteAndIdade(Date dataI, Date dataF, String nome, String idade1)
    {
			List <Paciente> lista = new ArrayList<Paciente>();
			String sql = "SELECT * FROM vwtodasinformacoespaciente WHERE data_registo BETWEEN ? AND ? AND nome LIKE '"+nome+"%' OR ULTIMO_NOME LIKE '"+nome+"%' AND FK_entidade = ? ORDER BY (NOME) AND IDADE =?";
			try {
				con = Conexao.getConexao();
				PreparedStatement preparador = con.prepareStatement(sql);
				preparador.setDate(1, dataI);
				preparador.setDate(2, dataF);
				preparador.setString(3, nome);
				preparador.setString(4, idade1);
				ResultSet rs = preparador.executeQuery();
				while (rs.next())
				 {
					 Paciente fun = new Paciente();
					 fun.setNumero_processo(rs.getString("numeroProcesso"));
					 fun.setNomeCompleto(rs.getString("nomeCompleto"));
					 fun.setNumero_doc(rs.getString("numero_documento"));
					 fun.setNomegenero(rs.getString("genero"));
					 fun.setEndereco(rs.getString("endereco"));
					 Calendar data = Calendar.getInstance();
				     data.setTime(rs.getDate("data_nascimento"));
				     fun.setDataNasc(data);
					 fun.setTelefone(rs.getLong("telefone"));
					 fun.setIdade_dopaciente(rs.getInt("idade"));
					 fun.setNomePais(rs.getString("pais"));
					 fun.setNomeProv(rs.getString("provincia"));
					 fun.setNomeMunicipio(rs.getString("municipio"));
					 fun.setNomeDistrito(rs.getString("distrito"));
					 Calendar data1 = Calendar.getInstance();
					 fun.setData(rs.getDate("data_registo"));
					 fun.setData_confirmacao(data1);
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
    public List <Paciente> buscarPorNomeHiPacienteAndIdade(Date dataI, Date dataF, String nome, String idade1, String idade2)
    {
			List <Paciente> lista = new ArrayList<Paciente>();
			String sql = "SELECT * FROM vwtodasinformacoespaciente WHERE data_registo BETWEEN ? AND ? AND nome LIKE '"+nome+"%' OR ULTIMO_NOME LIKE '"+nome+"%' AND FK_entidade = ? ORDER BY (NOME) AND IDADE BETWEEN ? AND ?";
			try {
				con = Conexao.getConexao();
				PreparedStatement preparador = con.prepareStatement(sql);
				preparador.setDate(1, dataI);
				preparador.setDate(2, dataF);
				preparador.setString(3, nome);
				preparador.setString(4, idade1);
				ResultSet rs = preparador.executeQuery();
				while (rs.next())
				 {
					 Paciente fun = new Paciente();
					 fun.setNumero_processo(rs.getString("numeroProcesso"));
					 fun.setNomeCompleto(rs.getString("nomeCompleto"));
					 fun.setNumero_doc(rs.getString("numero_documento"));
					 fun.setNomegenero(rs.getString("genero"));
					 fun.setEndereco(rs.getString("endereco"));
					 Calendar data = Calendar.getInstance();
				     data.setTime(rs.getDate("data_nascimento"));
				     fun.setDataNasc(data);
					 fun.setTelefone(rs.getLong("telefone"));
					 fun.setIdade_dopaciente(rs.getInt("idade"));
					 fun.setNomePais(rs.getString("pais"));
					 fun.setNomeProv(rs.getString("provincia"));
					 fun.setNomeMunicipio(rs.getString("municipio"));
					 fun.setNomeDistrito(rs.getString("distrito"));
					 Calendar data1 = Calendar.getInstance();
					 fun.setData(rs.getDate("data_registo"));
					 fun.setData_confirmacao(data1);
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
    public List <Paciente> buscarPorNomeHiPacienteAndGenero(Date dataI, Date dataF, String nome, String genero)
    {
			List <Paciente> lista = new ArrayList<Paciente>();
			String sql = "SELECT * FROM vwtodasinformacoespaciente WHERE data_registo BETWEEN ? AND ? AND nome LIKE '"+nome+"%' OR ULTIMO_NOME LIKE '"+nome+"%' AND FK_entidade = ? ORDER BY (NOME) AND FK_genero = ?";
			try {
				con = Conexao.getConexao();
				PreparedStatement preparador = con.prepareStatement(sql);
				preparador.setDate(1, dataI);
				preparador.setDate(2, dataF);
				preparador.setString(3, nome);
				preparador.setString(4, genero);
				ResultSet rs = preparador.executeQuery();
				while (rs.next())
				 {
					 Paciente fun = new Paciente();
					 fun.setNumero_processo(rs.getString("numeroProcesso"));
					 fun.setNomeCompleto(rs.getString("nomeCompleto"));
					 fun.setNomegenero(rs.getString("genero"));
					 fun.setNumero_doc(rs.getString("numero_documento"));
					 fun.setEndereco(rs.getString("endereco"));
					 Calendar data = Calendar.getInstance();
				     data.setTime(rs.getDate("data_nascimento"));
				     fun.setDataNasc(data);
					 fun.setTelefone(rs.getLong("telefone"));
					 fun.setIdade_dopaciente(rs.getInt("idade"));
					 fun.setNomePais(rs.getString("pais"));
					 fun.setNomeProv(rs.getString("provincia"));
					 fun.setNomeMunicipio(rs.getString("municipio"));
					 fun.setNomeDistrito(rs.getString("distrito"));
					 Calendar data1 = Calendar.getInstance();
					 fun.setData(rs.getDate("data_registo"));
					 fun.setData_confirmacao(data1);
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
    public List <Paciente> buscarPorNomeHiPacienteAndNacional(Date dataI, Date dataF, String nome, String pais, String prov, String mun, String distr)
    {
			List <Paciente> lista = new ArrayList<Paciente>();
			String sql = "SELECT * FROM vwtodasinformacoespaciente WHERE data_registo BETWEEN ? AND ? AND nome LIKE '"+nome+"%' OR ULTIMO_NOME LIKE '"+nome+"%' AND FK_entidade = ? ORDER BY (NOME) AND pais = ? AND provincia = ? AND municipio = ? AND distrito = ?";
			try {
				con = Conexao.getConexao();
				PreparedStatement preparador = con.prepareStatement(sql);
				preparador.setDate(1, dataI);
				preparador.setDate(2, dataF);
				preparador.setString(3, nome);
				preparador.setString(4, pais);
				preparador.setString(5, prov);
				preparador.setString(6, mun);
				preparador.setString(7, distr);
				ResultSet rs = preparador.executeQuery();
				while (rs.next())
				 {
					 Paciente fun = new Paciente();
					 fun.setNumero_processo(rs.getString("numeroProcesso"));
					 fun.setNomeCompleto(rs.getString("nomeCompleto"));
					 fun.setNumero_doc(rs.getString("numero_documento"));
					 fun.setNomegenero(rs.getString("genero"));
					 fun.setEndereco(rs.getString("endereco"));
					 Calendar data = Calendar.getInstance();
				     data.setTime(rs.getDate("data_nascimento"));
				     fun.setDataNasc(data);
					 fun.setTelefone(rs.getLong("telefone"));
					 fun.setIdade_dopaciente(rs.getInt("idade"));
					 fun.setNomePais(rs.getString("pais"));
					 fun.setNomeProv(rs.getString("provincia"));
					 fun.setNomeMunicipio(rs.getString("municipio"));
					 fun.setNomeDistrito(rs.getString("distrito"));
					 Calendar data1 = Calendar.getInstance();
					 fun.setData(rs.getDate("data_registo"));
					 fun.setData_confirmacao(data1);
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
    public List <Paciente> buscarPorIdadeHiPacienteAndGenero(Date dataI, Date dataF, String idade1, String idade2, String genero)
    {
			List <Paciente> lista = new ArrayList<Paciente>();
			String sql = "SELECT * FROM vwtodasinformacoespaciente WHERE data_registo BETWEEN ? AND ? AND IDADE BETWEEN ? AND ? AND FK_genero = ?";
			try {
				con = Conexao.getConexao();
				PreparedStatement preparador = con.prepareStatement(sql);
				preparador.setDate(1, dataI);
				preparador.setDate(2, dataF);
				preparador.setString(3, idade1);
				preparador.setString(2, idade2);
				preparador.setString(4, genero);
				ResultSet rs = preparador.executeQuery();
				while (rs.next())
				 {
					 Paciente fun = new Paciente();
					 fun.setNumero_processo(rs.getString("numeroProcesso"));
					 fun.setNomeCompleto(rs.getString("nomeCompleto"));
					 fun.setNumero_doc(rs.getString("numero_documento"));
					 fun.setEndereco(rs.getString("endereco"));
					 fun.setNomegenero(rs.getString("genero"));
					 Calendar data = Calendar.getInstance();
				     data.setTime(rs.getDate("data_nascimento"));
				     fun.setDataNasc(data);
					 fun.setTelefone(rs.getLong("telefone"));
					 fun.setIdade_dopaciente(rs.getInt("idade"));
					 fun.setNomePais(rs.getString("pais"));
					 fun.setNomeProv(rs.getString("provincia"));
					 fun.setNomeMunicipio(rs.getString("municipio"));
					 fun.setNomeDistrito(rs.getString("distrito"));
					 fun.setNomeEC(rs.getString("estado_civil"));
					 Calendar data1 = Calendar.getInstance();
					 fun.setData(rs.getDate("data_registo"));
					 fun.setData_confirmacao(data1);
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
    public List <Paciente> buscarPorIdadeHiPacienteAndNacional(Date dataI, Date dataF, String idade1, String idade2, String pais, String prov, String mun, String distr)
    {
			List <Paciente> lista = new ArrayList<Paciente>();
			String sql = "SELECT * FROM vwtodasinformacoespaciente WHERE data_registo BETWEEN ? AND ? AND IDADE BETWEEN ? AND ? AND pais = ? AND provincia = ? AND municipio = ? AND distrito = ?";
			try {
				con = Conexao.getConexao();
				PreparedStatement preparador = con.prepareStatement(sql);
				preparador.setDate(1, dataI);
				preparador.setDate(2, dataF);
				preparador.setString(3, idade1);
				preparador.setString(5, idade2);
				preparador.setString(6, pais);
				preparador.setString(7, prov);
				preparador.setString(8, mun);
				preparador.setString(9, distr);
				ResultSet rs = preparador.executeQuery();
				while (rs.next())
				 {
					 Paciente fun = new Paciente();
					 fun.setNumero_processo(rs.getString("numeroProcesso"));
					 fun.setNomeCompleto(rs.getString("nomeCompleto"));
					 fun.setNumero_doc(rs.getString("numero_documento"));
					 fun.setEndereco(rs.getString("endereco"));
					 fun.setNomegenero(rs.getString("genero"));
					 Calendar data = Calendar.getInstance();
				     data.setTime(rs.getDate("data_nascimento"));
				     fun.setDataNasc(data);
					 fun.setTelefone(rs.getLong("telefone"));
					 fun.setIdade_dopaciente(rs.getInt("idade"));
					 fun.setNomePais(rs.getString("pais"));
					 fun.setNomeProv(rs.getString("provincia"));
					 fun.setNomeMunicipio(rs.getString("municipio"));
					 fun.setNomeDistrito(rs.getString("distrito"));
					 Calendar data1 = Calendar.getInstance();
					 fun.setData(rs.getDate("data_registo"));
					 fun.setData_confirmacao(data1);
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
    public List <Paciente> buscarPorGeneroHiPacienteAndNacional(Date dataI, Date dataF, String genero, String pais, String prov, String mun, String distr)
    {
			List <Paciente> lista = new ArrayList<Paciente>();
			String sql = "SELECT * FROM vwtodasinformacoespaciente WHERE data_registo BETWEEN ? AND ? AND fk_genero =? AND pais = ? AND provincia = ? AND municipio = ? AND distrito = ?";
			try {
				con = Conexao.getConexao();
				PreparedStatement preparador = con.prepareStatement(sql);
				preparador.setDate(1, dataI);
				preparador.setDate(2, dataF);
				preparador.setString(3, genero);
				preparador.setString(4, pais);
				preparador.setString(5, prov);
				preparador.setString(6, mun);
				preparador.setString(7, distr);
				ResultSet rs = preparador.executeQuery();
				while (rs.next())
				 {
					 Paciente fun = new Paciente();
					 fun.setNumero_processo(rs.getString("numeroProcesso"));
					 fun.setNumero_doc(rs.getString("numero_documento"));
					 fun.setNomeCompleto(rs.getString("nomeCompleto"));
					 fun.setNomegenero(rs.getString("genero"));
					 fun.setEndereco(rs.getString("endereco"));
					 Calendar data = Calendar.getInstance();
				     data.setTime(rs.getDate("data_nascimento"));
				     fun.setDataNasc(data);
					 fun.setTelefone(rs.getLong("telefone"));
					 fun.setIdade_dopaciente(rs.getInt("idade"));
					 fun.setNomePais(rs.getString("pais"));
					 fun.setNomeProv(rs.getString("provincia"));
					 fun.setNomeMunicipio(rs.getString("municipio"));
					 fun.setNomeDistrito(rs.getString("distrito"));
					 Calendar data1 = Calendar.getInstance();
					 fun.setData(rs.getDate("data_registo"));
					 fun.setData_confirmacao(data1);
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
    public List <PacienteRelatorio> buscarPorDataHistPaciente(Date dataI, Date dataF)
    {
			List <PacienteRelatorio> lista = new ArrayList<PacienteRelatorio>();
			String sql = "SELECT * FROM vwtodasinformacoespaciente WHERE data_registo BETWEEN ? AND ?";
			try {
				con = Conexao.getConexao();
				PreparedStatement preparador = con.prepareStatement(sql);
				preparador.setDate(1, dataI);
				preparador.setDate(2, dataF);
				ResultSet rs = preparador.executeQuery();
				while (rs.next())
				 {
					PacienteRelatorio fun = new PacienteRelatorio();
					 fun.setNumero(rs.getString("numeroProcesso"));
					 fun.setNome(rs.getString("nomeCompleto"));
					 fun.setNomeEC(rs.getString("estado_civil"));
					 fun.setTelefone(rs.getString("telefone"));
					 fun.setNomegenero(rs.getString("genero"));
					 fun.setEndereco(rs.getString("endereco"));
					 fun.setIdade(rs.getString("idade"));
//					 fun.setNumero_doc(rs.getString("numero_documento"));
//					 Calendar data = Calendar.getInstance();
//				     data.setTime(rs.getDate("data_nascimento"));
//				     fun.setDataNasc(data);
//					 fun.setTelefone(rs.getLong("telefone"));
//					 fun.setNomePais(rs.getString("pais"));
//					 fun.setNomeProv(rs.getString("provincia"));
//					 fun.setNomeMunicipio(rs.getString("municipio"));
//					 fun.setNomeDistrito(rs.getString("distrito"));
//					 Calendar data1 = Calendar.getInstance();
//					 fun.setData(rs.getDate("data_registo"));
//					 fun.setData_confirmacao(data1);
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
    
    
    
    	//    HISTÓRICO DE TRIAGGEM 
    
    public List <Paciente> buscarPacienteTriagem()
    {
			List <Paciente> lista = new ArrayList<Paciente>();
			String sql = "SELECT * FROM vwrelatoriotriagemefectuada where data = curdate() limit 500";
			try {
				con = Conexao.getConexao();
				PreparedStatement preparador = con.prepareStatement(sql);
				ResultSet rs = preparador.executeQuery();
				while (rs.next())
				 {
					 Paciente fun = new Paciente();
					 fun.setNomeCompleto(rs.getString("NomeCompleto"));
					 fun.setNome(rs.getString("n_c_paciente"));
//					 fun.setNomem(rs.getString("nome_meio"));
//					 fun.setApelido(rs.getString("ultimo_nome"));
					 fun.setIdade_dopaciente(rs.getInt("idade"));
					 fun.setNomegenero(rs.getString("genero"));
					 fun.setIdade(rs.getDate("data_nascimento"));
//					 Calendar data = Calendar.getInstance();
//				     data.setTime(rs.getDate("data_nascimento"));
//				     fun.setDataNasc(data);
//				     fun.setIdade(rs.getDate("data_nascimento"));
					 
					 fun.setServico(rs.getString("servico"));
					 Calendar data1 = Calendar.getInstance();
					 fun.setData(rs.getDate("data"));
					 fun.setData_confirmacao(data1);

				     fun.setFK_servico(rs.getInt("FK_servico"));
				     fun.setFK_doutor(rs.getInt("FK_doutor"));
				     //fun.setGenero(rs.getInt("fk_genero"));
				     fun.setId_triagem(rs.getInt("id_triagem"));
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
    public List <Paciente> buscarPorDataAndNomeTriagem(Date dataI, Date dataF, String nome)
    {
			List <Paciente> lista = new ArrayList<Paciente>();
			String sql = "SELECT * FROM vwpacientetriado WHERE data BETWEEN ? AND ? AND nome LIKE ? OR ULTIMO_NOME LIKE ? ORDER BY NOME ASC";
			try {
				con = Conexao.getConexao();
				PreparedStatement preparador = con.prepareStatement(sql);
				preparador.setDate(1, dataI);
				preparador.setDate(2, dataF);
				preparador.setString(3, ""+nome+"");
				preparador.setString(4, nome);
				//preparador.setString(5, nome);
				ResultSet rs = preparador.executeQuery();
				while (rs.next())
				 {
					 Paciente fun = new Paciente();
					 fun.setNomeCompleto(rs.getString("NomeCompleto"));
					 fun.setNome(rs.getString("nome"));
					 fun.setNomem(rs.getString("nome_meio"));
					 fun.setApelido(rs.getString("ultimo_nome"));
					 fun.setNomegenero(rs.getString("genero"));
					 Calendar data = Calendar.getInstance();
				     data.setTime(rs.getDate("data_nascimento"));
				     fun.setDataNasc(data);
				     fun.setIdade(rs.getDate("data_nascimento"));
					 fun.setServico(rs.getString("descricao"));
					 Calendar data1 = Calendar.getInstance();
					 fun.setData(rs.getDate("data"));
					 fun.setData_confirmacao(data1);
				     fun.setFK_servico(rs.getInt("FK_servico"));
				     fun.setFK_doutor(rs.getInt("FK_doutor"));
				     fun.setId_triagem(rs.getInt("id_triagem"));
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
    public List <Paciente> buscarPorDataAndIdadeTriagem(Date dataI, Date dataF, String idade)
    {
			List <Paciente> lista = new ArrayList<Paciente>();
			String sql = "SELECT * FROM vwpacientetriado WHERE data BETWEEN ? AND ? AND IDADE = ?";
			try {
				con = Conexao.getConexao();
				PreparedStatement preparador = con.prepareStatement(sql);
				preparador.setDate(1, dataI);
				preparador.setDate(2, dataF);
				preparador.setString(3, idade);
				ResultSet rs = preparador.executeQuery();
				while (rs.next())
				 {
					 Paciente fun = new Paciente();
					 fun.setNomeCompleto(rs.getString("NomeCompleto"));
					 fun.setNome(rs.getString("nome"));
					 fun.setNomem(rs.getString("nome_meio"));
					 fun.setApelido(rs.getString("ultimo_nome"));
					 fun.setNomegenero(rs.getString("genero"));
					 Calendar data = Calendar.getInstance();
				     data.setTime(rs.getDate("data_nascimento"));
				     fun.setDataNasc(data);
				     fun.setIdade(rs.getDate("data_nascimento"));
					 fun.setServico(rs.getString("descricao"));
					 Calendar data1 = Calendar.getInstance();
					 fun.setData(rs.getDate("data"));
					 fun.setData_confirmacao(data1);
				     fun.setFK_servico(rs.getInt("FK_servico"));
				     fun.setFK_doutor(rs.getInt("FK_doutor"));
				     fun.setId_triagem(rs.getInt("id_triagem"));
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
    public List <Paciente> buscarPorDataAndIdadeTriagens(Date dataI, Date dataF, String idade1, String idade2)
    {
			List <Paciente> lista = new ArrayList<Paciente>();
			String sql = "SELECT * FROM vwpacientetriado WHERE data BETWEEN ? AND ? AND IDADE BETWEEN ? AND ?";
			try {
				con = Conexao.getConexao();
				PreparedStatement preparador = con.prepareStatement(sql);
				preparador.setDate	(1, dataI);
				preparador.setDate	(2, dataF);
				preparador.setString(3, idade1);
				preparador.setString(4, idade2);
				ResultSet rs = preparador.executeQuery();
				while (rs.next())
				 {
					 Paciente fun = new Paciente();
					 fun.setNomeCompleto(rs.getString("NomeCompleto"));
					 fun.setNome(rs.getString("nome"));
					 fun.setNomem(rs.getString("nome_meio"));
					 fun.setApelido(rs.getString("ultimo_nome"));
					 fun.setNomegenero(rs.getString("genero"));
					 Calendar data = Calendar.getInstance();
				     data.setTime(rs.getDate("data_nascimento"));
				     fun.setDataNasc(data);
				     fun.setIdade(rs.getDate("data_nascimento"));
					 fun.setServico(rs.getString("descricao"));
					 Calendar data1 = Calendar.getInstance();
					 fun.setData(rs.getDate("data"));
					 fun.setData_confirmacao(data1);
				     fun.setFK_servico(rs.getInt("FK_servico"));
				     fun.setFK_doutor(rs.getInt("FK_doutor"));
				     fun.setId_triagem(rs.getInt("id_triagem"));
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
    public List <Paciente> buscarPorDataAndGeneroTriagem(Date dataI, Date dataF, String genero)
    {
			List <Paciente> lista = new ArrayList<Paciente>();
			String sql = "SELECT * FROM vwpacientetriado WHERE data BETWEEN ? AND ? AND FK_genero =?";
			try {
				con = Conexao.getConexao();
				PreparedStatement preparador = con.prepareStatement(sql);
				preparador.setDate(1, dataI);
				preparador.setDate(2, dataF);
				preparador.setString(3, genero);
				ResultSet rs = preparador.executeQuery();
				while (rs.next())
				 {
					 Paciente fun = new Paciente();
					 fun.setNomeCompleto(rs.getString("NomeCompleto"));
					 fun.setNome(rs.getString("nome"));
					 fun.setNomem(rs.getString("nome_meio"));
					 fun.setApelido(rs.getString("ultimo_nome"));
					 fun.setNomegenero(rs.getString("genero"));
					 Calendar data = Calendar.getInstance();
				     data.setTime(rs.getDate("data_nascimento"));
				     fun.setDataNasc(data);
				     fun.setIdade(rs.getDate("data_nascimento"));
					 fun.setServico(rs.getString("descricao"));
					 Calendar data1 = Calendar.getInstance();
					 fun.setData(rs.getDate("data"));
					 fun.setData_confirmacao(data1);
				     fun.setFK_servico(rs.getInt("FK_servico"));
				     fun.setFK_doutor(rs.getInt("FK_doutor"));
				     fun.setGenero(rs.getInt("fk_genero"));
				     fun.setId_triagem(rs.getInt("id_triagem"));
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
    public List <Paciente> buscarPorDataTriagens(Date dataI, Date dataF)
    {
			List <Paciente> lista = new ArrayList<Paciente>();
			String sql = "SELECT * FROM vwpacientetriado WHERE data BETWEEN ? AND ?";
			try {
				con = Conexao.getConexao();
				PreparedStatement preparador = con.prepareStatement(sql);
				preparador.setDate(1, dataI);
				preparador.setDate(2, dataF);
				ResultSet rs = preparador.executeQuery();
				while (rs.next())
				 {
					 Paciente fun = new Paciente();
					 fun.setNomeCompleto(rs.getString("NomeCompleto"));
					 fun.setNome(rs.getString("nome"));
					 fun.setNomem(rs.getString("nome_meio"));
					 fun.setApelido(rs.getString("ultimo_nome"));
					 fun.setNomegenero(rs.getString("genero"));
					 Calendar data = Calendar.getInstance();
				     data.setTime(rs.getDate("data_nascimento"));
				     fun.setDataNasc(data);
				     fun.setIdade(rs.getDate("data_nascimento"));
					 fun.setServico(rs.getString("descricao"));
					 Calendar data1 = Calendar.getInstance();
					 fun.setData(rs.getDate("data"));
					 fun.setData_confirmacao(data1);
				     fun.setFK_servico(rs.getInt("FK_servico"));
				     fun.setFK_doutor(rs.getInt("FK_doutor"));
				     fun.setGenero(rs.getInt("fk_genero"));
				     fun.setId_triagem(rs.getInt("id_triagem"));
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
    public List <Paciente> buscarPorDataAndServicoTriagem(Date dataI, Date dataF, String servico)
    {
			List <Paciente> lista = new ArrayList<Paciente>();
			String sql = "SELECT * FROM vwpacientetriado WHERE data BETWEEN ? AND ? AND descricao = ?";
			try {
				con = Conexao.getConexao();
				PreparedStatement preparador = con.prepareStatement(sql);
				preparador.setDate(1, dataI);
				preparador.setDate(2, dataF);
				preparador.setString(3, servico);
				ResultSet rs = preparador.executeQuery();
				while (rs.next())
				 {
					 Paciente fun = new Paciente();
					 fun.setNomeCompleto(rs.getString("NomeCompleto"));
					 fun.setNome(rs.getString("nome"));
					 fun.setNomem(rs.getString("nome_meio"));
					 fun.setApelido(rs.getString("ultimo_nome"));
					 fun.setNomegenero(rs.getString("genero"));
					 Calendar data = Calendar.getInstance();
				     data.setTime(rs.getDate("data_nascimento"));
				     fun.setDataNasc(data);
				     fun.setIdade(rs.getDate("data_nascimento"));
					 fun.setServico(rs.getString("descricao"));
					 Calendar data1 = Calendar.getInstance();
					 fun.setData(rs.getDate("data"));
					 fun.setData_confirmacao(data1);
				     fun.setFK_servico(rs.getInt("FK_servico"));
				     fun.setFK_doutor(rs.getInt("FK_doutor"));
				     fun.setGenero(rs.getInt("fk_genero"));
				     fun.setId_triagem(rs.getInt("id_triagem"));
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
    public List <Paciente> buscarPorDataAndMedicoTriagem(Date dataI, Date dataF, String medico)
    {
			List <Paciente> lista = new ArrayList<Paciente>();
			String sql = "SELECT * FROM vwpacientetriado WHERE data BETWEEN ? AND ? AND NomeCompleto = ?";
			try {
				con = Conexao.getConexao();
				PreparedStatement preparador = con.prepareStatement(sql);
				preparador.setDate(1, dataI);
				preparador.setDate(2, dataF);
				preparador.setString(3, medico);
				ResultSet rs = preparador.executeQuery();
				while (rs.next())
				 {
					 Paciente fun = new Paciente();
					 fun.setNomeCompleto(rs.getString("NomeCompleto"));
					 fun.setNome(rs.getString("nome"));
					 fun.setNomem(rs.getString("nome_meio"));
					 fun.setApelido(rs.getString("ultimo_nome"));
					 fun.setNomegenero(rs.getString("genero"));
					 Calendar data = Calendar.getInstance();
				     data.setTime(rs.getDate("data_nascimento"));
				     fun.setDataNasc(data);
				     fun.setIdade(rs.getDate("data_nascimento"));
					 fun.setServico(rs.getString("descricao"));
					 Calendar data1 = Calendar.getInstance();
					 fun.setData(rs.getDate("data"));
					 fun.setData_confirmacao(data1);
				     fun.setFK_servico(rs.getInt("FK_servico"));
				     fun.setFK_doutor(rs.getInt("FK_doutor"));
				     fun.setGenero(rs.getInt("fk_genero"));
				     fun.setId_triagem(rs.getInt("id_triagem"));
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
    public List <Paciente> buscarPorDataAndNomeAndIdadeTriagem(Date dataI, Date dataF, String nome, String idade1, String idade2)
    {
			List <Paciente> lista = new ArrayList<Paciente>();
			String sql = "SELECT * FROM vwpacientetriado WHERE data BETWEEN ? AND ? AND nome LIKE '"+nome+"%' OR ULTIMO_NOME LIKE '"+nome+"%' AND FK_paciente = ? ORDER BY (NOME) AND IDADE BETWEEN ? AND ?";
			try {
				con = Conexao.getConexao();
				PreparedStatement preparador = con.prepareStatement(sql);
				preparador.setDate(1, dataI);
				preparador.setDate(2, dataF);
				preparador.setString(3, nome);
				preparador.setString(4, idade1);
				preparador.setString(5, idade2);
				ResultSet rs = preparador.executeQuery();
				while (rs.next())
				 {
					 Paciente fun = new Paciente();
					 fun.setNomeCompleto(rs.getString("NomeCompleto"));
					 fun.setNome(rs.getString("nome"));
					 fun.setNomem(rs.getString("nome_meio"));
					 fun.setApelido(rs.getString("ultimo_nome"));
					 fun.setNomegenero(rs.getString("genero"));
					 Calendar data = Calendar.getInstance();
				     data.setTime(rs.getDate("data_nascimento"));
				     fun.setDataNasc(data);
				     fun.setIdade(rs.getDate("data_nascimento"));
					 fun.setServico(rs.getString("descricao"));
					 Calendar data1 = Calendar.getInstance();
					 fun.setData(rs.getDate("data"));
					 fun.setData_confirmacao(data1);
				     fun.setFK_servico(rs.getInt("FK_servico"));
				     fun.setFK_doutor(rs.getInt("FK_doutor"));
				     fun.setId_triagem(rs.getInt("id_triagem"));
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
    public List <Paciente> buscarPorDataAndNomeAndGeneroTriagem(Date dataI, Date dataF, String nome, String genero)
    {
			List <Paciente> lista = new ArrayList<Paciente>();
			String sql = "SELECT * FROM vwpacientetriado WHERE data BETWEEN ? AND ? AND nome LIKE '"+nome+"%' OR ULTIMO_NOME LIKE '"+nome+"%' AND FK_paciente = ? ORDER BY (NOME) AND fk_genero = ?";
			try {
				con = Conexao.getConexao();
				PreparedStatement preparador = con.prepareStatement(sql);
				preparador.setDate(1, dataI);
				preparador.setDate(2, dataF);
				preparador.setString(3, nome);
				preparador.setString(4, genero);
				ResultSet rs = preparador.executeQuery();
				while (rs.next())
				 {
					 Paciente fun = new Paciente();
					 fun.setNomeCompleto(rs.getString("NomeCompleto"));
					 fun.setNome(rs.getString("nome"));
					 fun.setNomem(rs.getString("nome_meio"));
					 fun.setApelido(rs.getString("ultimo_nome"));
					 fun.setNomegenero(rs.getString("genero"));
					 Calendar data = Calendar.getInstance();
				     data.setTime(rs.getDate("data_nascimento"));
				     fun.setDataNasc(data);
				     fun.setIdade(rs.getDate("data_nascimento"));
					 fun.setServico(rs.getString("descricao"));
					 Calendar data1 = Calendar.getInstance();
					 fun.setData(rs.getDate("data"));
					 fun.setData_confirmacao(data1);
				     fun.setFK_servico(rs.getInt("FK_servico"));
				     fun.setFK_doutor(rs.getInt("FK_doutor"));
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
    public List <Paciente> buscarPorDataAndNomeAndServicoTriagem(Date dataI, Date dataF, String nome, String servico)
    {
			List <Paciente> lista = new ArrayList<Paciente>();
			String sql = "SELECT * FROM vwpacientetriado WHERE data BETWEEN ? AND ? AND nome LIKE '"+nome+"%' OR ULTIMO_NOME LIKE '"+nome+"%' AND FK_paciente = ? ORDER BY (NOME) AND descricao = ?";
			try {
				con = Conexao.getConexao();
				PreparedStatement preparador = con.prepareStatement(sql);
				preparador.setDate(1, dataI);
				preparador.setDate(2, dataF);
				preparador.setString(3, nome);
				preparador.setString(4, servico);
				ResultSet rs = preparador.executeQuery();
				while (rs.next())
				 {
					 Paciente fun = new Paciente();
					 fun.setNomeCompleto(rs.getString("NomeCompleto"));
					 fun.setNome(rs.getString("nome"));
					 fun.setNomem(rs.getString("nome_meio"));
					 fun.setApelido(rs.getString("ultimo_nome"));
					 fun.setNomegenero(rs.getString("genero"));
					 Calendar data = Calendar.getInstance();
				     data.setTime(rs.getDate("data_nascimento"));
				     fun.setDataNasc(data);
				     fun.setIdade(rs.getDate("data_nascimento"));
					 fun.setServico(rs.getString("descricao"));
					 Calendar data1 = Calendar.getInstance();
					 fun.setData(rs.getDate("data"));
					 fun.setData_confirmacao(data1);
				     fun.setFK_servico(rs.getInt("FK_servico"));
				     fun.setFK_doutor(rs.getInt("FK_doutor"));
				     fun.setId_triagem(rs.getInt("id_triagem"));
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
    public List <Paciente> buscarPorDataAndNomeAndMedicoTriagem(Date dataI, Date dataF, String nome, String medico)
    {
			List <Paciente> lista = new ArrayList<Paciente>();
			String sql = "SELECT * FROM vwpacientetriado WHERE data BETWEEN ? AND ? AND nome LIKE '"+nome+"%' OR ULTIMO_NOME LIKE '"+nome+"%' AND FK_paciente = ? END NomeCompleto = ? ORDER BY NOME ASC";
			try {
				con = Conexao.getConexao();
				PreparedStatement preparador = con.prepareStatement(sql);
				preparador.setDate(1, dataI);
				preparador.setDate(2, dataF);
				preparador.setString(3, nome);
				preparador.setString(4, medico);
				ResultSet rs = preparador.executeQuery();
				while (rs.next())
				 {
					 Paciente fun = new Paciente();
					 fun.setNomeCompleto(rs.getString("NomeCompleto"));
					 fun.setNome(rs.getString("nome"));
					 fun.setNomem(rs.getString("nome_meio"));
					 fun.setApelido(rs.getString("ultimo_nome"));
					 fun.setNomegenero(rs.getString("genero"));
					 Calendar data = Calendar.getInstance();
				     data.setTime(rs.getDate("data_nascimento"));
				     fun.setDataNasc(data);
				     fun.setIdade(rs.getDate("data_nascimento"));
					 fun.setServico(rs.getString("descricao"));
					 Calendar data1 = Calendar.getInstance();
					 fun.setData(rs.getDate("data"));
					 fun.setData_confirmacao(data1);
				     fun.setFK_servico(rs.getInt("FK_servico"));
				     fun.setFK_doutor(rs.getInt("FK_doutor"));
				     fun.setId_triagem(rs.getInt("id_triagem"));
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
    public List <Paciente> buscarPorDataAndIdadeAndGeneroTriagem(Date dataI, Date dataF, String idade1, String idade2, String genero)
    {
			List <Paciente> lista = new ArrayList<Paciente>();
			String sql = "SELECT * FROM vwpacientetriado WHERE data BETWEEN ? AND ? AND idade BETWEEN ? AND ? AND fk_genero = ?";
			try {
				con = Conexao.getConexao();
				PreparedStatement preparador = con.prepareStatement(sql);
				preparador.setDate(1, dataI);
				preparador.setDate(2, dataF);
				preparador.setString(3, idade1);
				preparador.setString(4, idade2);
				preparador.setString(5, genero);
				ResultSet rs = preparador.executeQuery();
				while (rs.next())
				 {
					 Paciente fun = new Paciente();
					 fun.setNomeCompleto(rs.getString("NomeCompleto"));
					 fun.setNome(rs.getString("nome"));
					 fun.setNomem(rs.getString("nome_meio"));
					 fun.setApelido(rs.getString("ultimo_nome"));
					 fun.setNomegenero(rs.getString("genero"));
					 Calendar data = Calendar.getInstance();
				     data.setTime(rs.getDate("data_nascimento"));
				     fun.setDataNasc(data);
				     fun.setIdade(rs.getDate("data_nascimento"));
					 fun.setServico(rs.getString("descricao"));
					 Calendar data1 = Calendar.getInstance();
					 fun.setData(rs.getDate("data"));
					 fun.setData_confirmacao(data1);
				     fun.setFK_servico(rs.getInt("FK_servico"));
				     fun.setFK_doutor(rs.getInt("FK_doutor"));
				     fun.setId_triagem(rs.getInt("id_triagem"));
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
    public List <Paciente> buscarPorDataAndIdadeAndServicoTriagem(Date dataI, Date dataF, String idade1, String idade2, String servico)
    {
			List <Paciente> lista = new ArrayList<Paciente>();
			String sql = "SELECT * FROM vwpacientetriado WHERE data BETWEEN ? AND ? AND idade BETWEEN ? AND ? AND descricao = ?";
			try {
				con = Conexao.getConexao();
				PreparedStatement preparador = con.prepareStatement(sql);
				preparador.setDate(1, dataI);
				preparador.setDate(2, dataF);
				preparador.setString(3, idade1);
				preparador.setString(4, idade2);
				preparador.setString(5, servico);
				ResultSet rs = preparador.executeQuery();
				while (rs.next())
				 {
					 Paciente fun = new Paciente();
					 fun.setNomeCompleto(rs.getString("NomeCompleto"));
					 fun.setNome(rs.getString("nome"));
					 fun.setNomem(rs.getString("nome_meio"));
					 fun.setApelido(rs.getString("ultimo_nome"));
					 fun.setNomegenero(rs.getString("genero"));
					 Calendar data = Calendar.getInstance();
				     data.setTime(rs.getDate("data_nascimento"));
				     fun.setDataNasc(data);
				     fun.setIdade(rs.getDate("data_nascimento"));
					 fun.setServico(rs.getString("descricao"));
					 Calendar data1 = Calendar.getInstance();
					 fun.setData(rs.getDate("data"));
					 fun.setData_confirmacao(data1);
				     fun.setFK_servico(rs.getInt("FK_servico"));
				     fun.setFK_doutor(rs.getInt("FK_doutor"));
				     fun.setId_triagem(rs.getInt("id_triagem"));
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
    public List <Paciente> buscarPorDataAndIdadeAndMedicoTriagem(Date dataI, Date dataF, String idade1, String idade2, String medico)
    {
			List <Paciente> lista = new ArrayList<Paciente>();
			String sql = "SELECT * FROM vwpacientetriado WHERE data BETWEEN ? AND ? AND idade BETWEEN ? AND ? AND nomeCompleto = ?";
			try {
				con = Conexao.getConexao();
				PreparedStatement preparador = con.prepareStatement(sql);
				preparador.setDate(1, dataI);
				preparador.setDate(2, dataF);
				preparador.setString(3, idade1);
				preparador.setString(4, idade2);
				preparador.setString(5, medico);
				ResultSet rs = preparador.executeQuery();
				while (rs.next())
				 {
					 Paciente fun = new Paciente();
					 fun.setNomeCompleto(rs.getString("NomeCompleto"));
					 fun.setNome(rs.getString("nome"));
					 fun.setNomem(rs.getString("nome_meio"));
					 fun.setApelido(rs.getString("ultimo_nome"));
					 fun.setNomegenero(rs.getString("genero"));
					 Calendar data = Calendar.getInstance();
				     data.setTime(rs.getDate("data_nascimento"));
				     fun.setDataNasc(data);
				     fun.setIdade(rs.getDate("data_nascimento"));
					 fun.setServico(rs.getString("descricao"));
					 Calendar data1 = Calendar.getInstance();
					 fun.setData(rs.getDate("data"));
					 fun.setData_confirmacao(data1);
				     fun.setFK_servico(rs.getInt("FK_servico"));
				     fun.setFK_doutor(rs.getInt("FK_doutor"));
				     fun.setId_triagem(rs.getInt("id_triagem"));
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
    public List <Paciente> buscarPorDataAndGeneroAndServicoTriagem(Date dataI, Date dataF, String genero, String servico)
    {
			List <Paciente> lista = new ArrayList<Paciente>();
			String sql = "SELECT * FROM vwpacientetriado WHERE data BETWEEN ? AND ? AND genero = ? AND descricao = ?";
			try {
				con = Conexao.getConexao();
				PreparedStatement preparador = con.prepareStatement(sql);
				preparador.setDate(1, dataI);
				preparador.setDate(2, dataF);
				preparador.setString(3, genero);
				preparador.setString(4, servico);
				ResultSet rs = preparador.executeQuery();
				while (rs.next())
				 {
					 Paciente fun = new Paciente();
					 fun.setNomeCompleto(rs.getString("NomeCompleto"));
					 fun.setNome(rs.getString("nome"));
					 fun.setNomem(rs.getString("nome_meio"));
					 fun.setApelido(rs.getString("ultimo_nome"));
					 fun.setNomegenero(rs.getString("genero"));
					 Calendar data = Calendar.getInstance();
				     data.setTime(rs.getDate("data_nascimento"));
				     fun.setDataNasc(data);
				     fun.setIdade(rs.getDate("data_nascimento"));
					 fun.setServico(rs.getString("descricao"));
					 Calendar data1 = Calendar.getInstance();
					 fun.setData(rs.getDate("data"));
					 fun.setData_confirmacao(data1);
				     fun.setFK_servico(rs.getInt("FK_servico"));
				     fun.setFK_doutor(rs.getInt("FK_doutor"));
				     fun.setId_triagem(rs.getInt("id_triagem"));
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
    public List <Paciente> buscarPorDataAndGeneroAndMedicoTriagem(Date dataI, Date dataF, String genero, int medico)
    {
			List <Paciente> lista = new ArrayList<Paciente>();
			String sql = "SELECT * FROM vwpacientetriado WHERE data BETWEEN ? AND ? AND genero = ? AND fk_doutor = ?";
			try {
				con = Conexao.getConexao();
				PreparedStatement preparador = con.prepareStatement(sql);
				preparador.setDate(1, dataI);
				preparador.setDate(2, dataF);
				preparador.setString(3, genero);
				preparador.setInt(4, medico);
				ResultSet rs = preparador.executeQuery();
				while (rs.next())
				 {
					 Paciente fun = new Paciente();
					 fun.setNomeCompleto(rs.getString("NomeCompleto"));
					 fun.setNome(rs.getString("nome"));
					 fun.setNomem(rs.getString("nome_meio"));
					 fun.setApelido(rs.getString("ultimo_nome"));
					 fun.setNomegenero(rs.getString("genero"));
					 Calendar data = Calendar.getInstance();
				     data.setTime(rs.getDate("data_nascimento"));
				     fun.setDataNasc(data);
				     fun.setIdade(rs.getDate("data_nascimento"));
					 fun.setServico(rs.getString("descricao"));
					 Calendar data1 = Calendar.getInstance();
					 fun.setData(rs.getDate("data"));
					 fun.setData_confirmacao(data1);
				     fun.setFK_servico(rs.getInt("FK_servico"));
				     fun.setFK_doutor(rs.getInt("FK_doutor"));
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
    public List <Paciente> buscarPorDataAndServicoAndMedicoTriagem(Date dataI, Date dataF, String servico, String medico)
    {
			List <Paciente> lista = new ArrayList<Paciente>();
			String sql = "SELECT * FROM vwpacientetriado WHERE data BETWEEN ? AND ? AND descricao = ? AND nomeCompleto = ?";
			try {
				con = Conexao.getConexao();
				PreparedStatement preparador = con.prepareStatement(sql);
				preparador.setDate(1, dataI);
				preparador.setDate(2, dataF);
				preparador.setString(3, servico);
				preparador.setString(4, medico);
				ResultSet rs = preparador.executeQuery();
				while (rs.next())
				 {
					 Paciente fun = new Paciente();
					 fun.setNomeCompleto(rs.getString("NomeCompleto"));
					 fun.setNome(rs.getString("nome"));
					 fun.setNomem(rs.getString("nome_meio"));
					 fun.setApelido(rs.getString("ultimo_nome"));
					 fun.setNomegenero(rs.getString("genero"));
					 Calendar data = Calendar.getInstance();
				     data.setTime(rs.getDate("data_nascimento"));
				     fun.setDataNasc(data);
				     fun.setIdade(rs.getDate("data_nascimento"));
					 fun.setServico(rs.getString("descricao"));
					 Calendar data1 = Calendar.getInstance();
					 fun.setData(rs.getDate("data"));
					 fun.setData_confirmacao(data1);
				     fun.setFK_servico(rs.getInt("FK_servico"));
				     fun.setFK_doutor(rs.getInt("FK_doutor"));
				     fun.setId_triagem(rs.getInt("id_triagem"));
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
//    FIM DE TRIAGEM
//    COMEÇA INTERNAMENTO
    public List <Paciente> buscarPacienteInternado()
    {
			List <Paciente> lista = new ArrayList<Paciente>();
			String sql = "SELECT * FROM vwinternados where data_internamento = curdate();";
			try {
				con = Conexao.getConexao();
				PreparedStatement preparador = con.prepareStatement(sql);
				ResultSet rs = preparador.executeQuery();
				while (rs.next())
				 {
					 Paciente fun = new Paciente();
					 fun.setFK_internamento(rs.getInt("id_internamento"));
					 fun.setNome_doutor(rs.getString("medico"));
					 fun.setNome(rs.getString("nome"));
					 fun.setNomem(rs.getString("nome_meio"));
					 fun.setApelido(rs.getString("ultimo_nome"));
					 fun.setNomegenero(rs.getString("genero"));
					 Calendar data = Calendar.getInstance();
				     data.setTime(rs.getDate("data_nascimento"));
				     fun.setDataNasc(data);
				     fun.setIdade(rs.getDate("data_nascimento"));
					 fun.setServico(rs.getString("descricao"));
					 Calendar data1 = Calendar.getInstance();
					 fun.setData(rs.getDate("data_internamento"));
					 fun.setData_confirmacao(data1);
					 fun.setSala(rs.getString("numero_sala"));
					 fun.setLeito(rs.getString("leito"));
					 fun.setSector(rs.getString("sector"));
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
    public List <Paciente> buscarPorDataAndNomePacInt(Date dataI, Date dataF, String nome)
    {
			List <Paciente> lista = new ArrayList<Paciente>();
			String sql = "SELECT * FROM vwinternados WHERE data_internamento BETWEEN ? AND ? AND nome LIKE '"+nome+"%' OR ULTIMO_NOME LIKE '"+nome+"%' AND FK_paciente = ? ORDER BY (NOME)";
			try {
				con = Conexao.getConexao();
				PreparedStatement preparador = con.prepareStatement(sql);
				preparador.setDate(1, dataI);
				preparador.setDate(2, dataF);
				preparador.setString(3, nome);
				ResultSet rs = preparador.executeQuery();
				while (rs.next())
				 {
					 Paciente fun = new Paciente();
					 fun.setFK_internamento(rs.getInt("id_internamento"));
					 fun.setNome_doutor(rs.getString("medico"));
					 fun.setNome(rs.getString("nome"));
					 fun.setNomem(rs.getString("nome_meio"));
					 fun.setApelido(rs.getString("ultimo_nome"));
					 fun.setNomegenero(rs.getString("genero"));
					 Calendar data = Calendar.getInstance();
				     data.setTime(rs.getDate("data_nascimento"));
				     fun.setDataNasc(data);
				     fun.setIdade(rs.getDate("data_nascimento"));
					 fun.setServico(rs.getString("descricao"));
					 Calendar data1 = Calendar.getInstance();
					 fun.setData(rs.getDate("data_internamento"));
					 fun.setData_confirmacao(data1);
					 fun.setSala(rs.getString("numero_sala"));
					 fun.setLeito(rs.getString("leito"));
					 fun.setSector(rs.getString("sector"));
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
    public List <Paciente> buscarPorDataPacInt(Date dataI, Date dataF)
    {
			List <Paciente> lista = new ArrayList<Paciente>();
			String sql = "SELECT * FROM vwinternados WHERE data_internamento BETWEEN ? AND ? ";
			try {
				con = Conexao.getConexao();
				PreparedStatement preparador = con.prepareStatement(sql);
				preparador.setDate(1, dataI);
				preparador.setDate(2, dataF);
				ResultSet rs = preparador.executeQuery();
				while (rs.next())
				 {
					 Paciente fun = new Paciente();
					 fun.setFK_internamento(rs.getInt("id_internamento"));
					 fun.setNome_doutor(rs.getString("medico"));
					 fun.setNome(rs.getString("nome"));
					 fun.setNomem(rs.getString("nome_meio"));
					 fun.setApelido(rs.getString("ultimo_nome"));
					 fun.setNomegenero(rs.getString("genero"));
					 Calendar data = Calendar.getInstance();
				     data.setTime(rs.getDate("data_nascimento"));
				     fun.setDataNasc(data);
				     fun.setIdade(rs.getDate("data_nascimento"));
					 fun.setServico(rs.getString("descricao"));
					 Calendar data1 = Calendar.getInstance();
					 fun.setData(rs.getDate("data_internamento"));
					 fun.setData_confirmacao(data1);
					 fun.setSala(rs.getString("numero_sala"));
					 fun.setLeito(rs.getString("leito"));
					 fun.setSector(rs.getString("sector"));
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
    public List <Paciente> buscarPorDataAndIdadePacInt(Date dataI, Date dataF, String idade1)
    {
			List <Paciente> lista = new ArrayList<Paciente>();
			String sql = "SELECT * FROM vwinternados WHERE data_internamento BETWEEN ? AND ? AND IDADE =?";
			try {
				con = Conexao.getConexao();
				PreparedStatement preparador = con.prepareStatement(sql);
				preparador.setDate(1, dataI);
				preparador.setDate(2, dataF);
				preparador.setString(3, idade1);
				ResultSet rs = preparador.executeQuery();
				while (rs.next())
				 {
					 Paciente fun = new Paciente();
					 fun.setFK_internamento(rs.getInt("id_internamento"));
					 fun.setNome_doutor(rs.getString("medico"));
					 fun.setNome(rs.getString("nome"));
					 fun.setNomem(rs.getString("nome_meio"));
					 fun.setApelido(rs.getString("ultimo_nome"));
					 fun.setNomegenero(rs.getString("genero"));
					 Calendar data = Calendar.getInstance();
				     data.setTime(rs.getDate("data_nascimento"));
				     fun.setDataNasc(data);
				     fun.setIdade(rs.getDate("data_nascimento"));
					 fun.setServico(rs.getString("descricao"));
					 Calendar data1 = Calendar.getInstance();
					 fun.setData(rs.getDate("data_internamento"));
					 fun.setData_confirmacao(data1);
					 fun.setSala(rs.getString("numero_sala"));
					 fun.setLeito(rs.getString("leito"));
					 fun.setSector(rs.getString("sector"));
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
    public List <Paciente> buscarPorDataAndIdadesPacInt(Date dataI, Date dataF, String idade1, String idade2)
    {
			List <Paciente> lista = new ArrayList<Paciente>();
			String sql = "SELECT * FROM vwinternados WHERE data_internamento BETWEEN ? AND ? AND IDADE BETWEEN ? AND ?";
			try {
				con = Conexao.getConexao();
				PreparedStatement preparador = con.prepareStatement(sql);
				preparador.setDate(1, dataI);
				preparador.setDate(2, dataF);
				preparador.setString(3, idade1);
				preparador.setString(4, idade2);
				ResultSet rs = preparador.executeQuery();
				while (rs.next())
				 {
					 Paciente fun = new Paciente();
					 fun.setFK_internamento(rs.getInt("id_internamento"));
					 fun.setNome_doutor(rs.getString("medico"));
					 fun.setNome(rs.getString("nome"));
					 fun.setNomem(rs.getString("nome_meio"));
					 fun.setApelido(rs.getString("ultimo_nome"));
					 fun.setNomegenero(rs.getString("genero"));
					 Calendar data = Calendar.getInstance();
				     data.setTime(rs.getDate("data_nascimento"));
				     fun.setDataNasc(data);
				     fun.setIdade(rs.getDate("data_nascimento"));
					 fun.setServico(rs.getString("descricao"));
					 Calendar data1 = Calendar.getInstance();
					 fun.setData(rs.getDate("data_internamento"));
					 fun.setData_confirmacao(data1);
					 fun.setSala(rs.getString("numero_sala"));
					 fun.setLeito(rs.getString("leito"));
					 fun.setSector(rs.getString("sector"));
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
    public List <Paciente> buscarPorDataAndGeneroPacInt(Date dataI, Date dataF, String genero)
    {
			List <Paciente> lista = new ArrayList<Paciente>();
			String sql = "SELECT * FROM vwinternados WHERE data_internamento BETWEEN ? AND ? AND FK_genero =?";
			try {
				con = Conexao.getConexao();
				PreparedStatement preparador = con.prepareStatement(sql);
				preparador.setDate(1, dataI);
				preparador.setDate(2, dataF);
				preparador.setString(3, genero);
				ResultSet rs = preparador.executeQuery();
				while (rs.next())
				 {
					 Paciente fun = new Paciente();
					 fun.setFK_internamento(rs.getInt("id_internamento"));
					 fun.setNome_doutor(rs.getString("medico"));
					 fun.setNome(rs.getString("nome"));
					 fun.setNomem(rs.getString("nome_meio"));
					 fun.setApelido(rs.getString("ultimo_nome"));
					 fun.setNomegenero(rs.getString("genero"));
					 Calendar data = Calendar.getInstance();
				     data.setTime(rs.getDate("data_nascimento"));
				     fun.setDataNasc(data);
				     fun.setIdade(rs.getDate("data_nascimento"));
					 fun.setServico(rs.getString("descricao"));
					 Calendar data1 = Calendar.getInstance();
					 fun.setData(rs.getDate("data_internamento"));
					 fun.setData_confirmacao(data1);
					 fun.setSala(rs.getString("numero_sala"));
					 fun.setLeito(rs.getString("leito"));
					 fun.setSector(rs.getString("sector"));
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
    public List <Paciente> buscarPorDataAndMedicoPacInt(Date dataI, Date dataF, String medico)
    {
			List <Paciente> lista = new ArrayList<Paciente>();
			String sql = "SELECT * FROM vwinternados WHERE data_internamento BETWEEN ? AND ? AND medico =?";
			try {
				con = Conexao.getConexao();
				PreparedStatement preparador = con.prepareStatement(sql);
				preparador.setDate(1, dataI);
				preparador.setDate(2, dataF);
				preparador.setString(3, medico);
				ResultSet rs = preparador.executeQuery();
				while (rs.next())
				 {
					 Paciente fun = new Paciente();
					 fun.setFK_internamento(rs.getInt("id_internamento"));
					 fun.setNome_doutor(rs.getString("medico"));
					 fun.setNome(rs.getString("nome"));
					 fun.setNomem(rs.getString("nome_meio"));
					 fun.setApelido(rs.getString("ultimo_nome"));
					 fun.setNomegenero(rs.getString("genero"));
					 Calendar data = Calendar.getInstance();
				     data.setTime(rs.getDate("data_nascimento"));
				     fun.setDataNasc(data);
				     fun.setIdade(rs.getDate("data_nascimento"));
					 fun.setServico(rs.getString("descricao"));
					 Calendar data1 = Calendar.getInstance();
					 fun.setData(rs.getDate("data_internamento"));
					 fun.setData_confirmacao(data1);
					 fun.setSala(rs.getString("numero_sala"));
					 fun.setLeito(rs.getString("leito"));
					 fun.setSector(rs.getString("sector"));
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
    public List <Paciente> buscarPorDataAndSectorAndSalaPacInt(Date dataI, Date dataF, String sector, String sala)
    {
			List <Paciente> lista = new ArrayList<Paciente>();
			String sql = "SELECT * FROM vwinternados WHERE data_internamento BETWEEN ? AND ? AND sector =? AND numero_sala =?";
			try {
				con = Conexao.getConexao();
				PreparedStatement preparador = con.prepareStatement(sql);
				preparador.setDate(1, dataI);
				preparador.setDate(2, dataF);
				preparador.setString(3, sector);
				preparador.setString(4, sala);
				ResultSet rs = preparador.executeQuery();
				while (rs.next())
				 {
					 Paciente fun = new Paciente();
					 fun.setFK_internamento(rs.getInt("id_internamento"));
					 fun.setNome_doutor(rs.getString("medico"));
					 fun.setNome(rs.getString("nome"));
					 fun.setNomem(rs.getString("nome_meio"));
					 fun.setApelido(rs.getString("ultimo_nome"));
					 fun.setNomegenero(rs.getString("genero"));
					 Calendar data = Calendar.getInstance();
				     data.setTime(rs.getDate("data_nascimento"));
				     fun.setDataNasc(data);
				     fun.setIdade(rs.getDate("data_nascimento"));
					 fun.setServico(rs.getString("descricao"));
					 Calendar data1 = Calendar.getInstance();
					 fun.setData(rs.getDate("data_internamento"));
					 fun.setData_confirmacao(data1);
					 fun.setSala(rs.getString("numero_sala"));
					 fun.setLeito(rs.getString("leito"));
					 fun.setSector(rs.getString("sector"));
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
    public List <Paciente> buscarPorDataAndNomeAndIdadePacInt(Date dataI, Date dataF, String nome, String idade1, String idade2)
    {
			List <Paciente> lista = new ArrayList<Paciente>();
			String sql = "SELECT * FROM vwinternados WHERE data_internamento BETWEEN ? AND ? AND nome LIKE '"+nome+"%' OR ULTIMO_NOME LIKE '"+nome+"%' AND FK_paciente = ? ORDER BY (NOME) AND IDADE BETWEEN ? AND ?";
			try {
				con = Conexao.getConexao();
				PreparedStatement preparador = con.prepareStatement(sql);
				preparador.setDate(1, dataI);
				preparador.setDate(2, dataF);
				preparador.setString(3, nome);
				preparador.setString(4, idade1);
				preparador.setString(5, idade2);
				ResultSet rs = preparador.executeQuery();
				while (rs.next())
				 {
					 Paciente fun = new Paciente();
					 fun.setFK_internamento(rs.getInt("id_internamento"));
					 fun.setNome_doutor(rs.getString("medico"));
					 fun.setNome(rs.getString("nome"));
					 fun.setNomem(rs.getString("nome_meio"));
					 fun.setApelido(rs.getString("ultimo_nome"));
					 fun.setNomegenero(rs.getString("genero"));
					 Calendar data = Calendar.getInstance();
				     data.setTime(rs.getDate("data_nascimento"));
				     fun.setDataNasc(data);
				     fun.setIdade(rs.getDate("data_nascimento"));
					 fun.setServico(rs.getString("descricao"));
					 Calendar data1 = Calendar.getInstance();
					 fun.setData(rs.getDate("data_internamento"));
					 fun.setData_confirmacao(data1);
					 fun.setSala(rs.getString("numero_sala"));
					 fun.setLeito(rs.getString("leito"));
					 fun.setSector(rs.getString("sector"));
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
    public List <Paciente> buscarPorDataAndNomeAndGeneroPacInt(Date dataI, Date dataF, String nome, String genero)
    {
			List <Paciente> lista = new ArrayList<Paciente>();
			String sql = "SELECT * FROM vwinternados WHERE data_internamento BETWEEN ? AND ? AND nome LIKE '"+nome+"%' OR ULTIMO_NOME LIKE '"+nome+"%' AND FK_paciente = ? ORDER BY (NOME) AND FK_genero = ?";
			try {
				con = Conexao.getConexao();
				PreparedStatement preparador = con.prepareStatement(sql);
				preparador.setDate(1, dataI);
				preparador.setDate(2, dataF);
				preparador.setString(3, nome);
				preparador.setString(4, genero);
				ResultSet rs = preparador.executeQuery();
				while (rs.next())
				 {
					 Paciente fun = new Paciente();
					 fun.setFK_internamento(rs.getInt("id_internamento"));
					 fun.setNome_doutor(rs.getString("medico"));
					 fun.setNome(rs.getString("nome"));
					 fun.setNomem(rs.getString("nome_meio"));
					 fun.setApelido(rs.getString("ultimo_nome"));
					 fun.setNomegenero(rs.getString("genero"));
					 Calendar data = Calendar.getInstance();
				     data.setTime(rs.getDate("data_nascimento"));
				     fun.setDataNasc(data);
				     fun.setIdade(rs.getDate("data_nascimento"));
					 fun.setServico(rs.getString("descricao"));
					 Calendar data1 = Calendar.getInstance();
					 fun.setData(rs.getDate("data_internamento"));
					 fun.setData_confirmacao(data1);
					 fun.setSala(rs.getString("numero_sala"));
					 fun.setLeito(rs.getString("leito"));
					 fun.setSector(rs.getString("sector"));
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
    public List <Paciente> buscarPorDataAndNomeAndMedicoPacInt(Date dataI, Date dataF, String nome, String medico)
    {
			List <Paciente> lista = new ArrayList<Paciente>();
			String sql = "SELECT * FROM vwinternados WHERE data_internamento BETWEEN ? AND ? AND nome LIKE '"+nome+"%' OR ULTIMO_NOME LIKE '"+nome+"%' AND FK_paciente = ? ORDER BY (NOME) AND medico = ?";
			try {
				con = Conexao.getConexao();
				PreparedStatement preparador = con.prepareStatement(sql);
				preparador.setDate(1, dataI);
				preparador.setDate(2, dataF);
				preparador.setString(3, nome);
				preparador.setString(4, medico);
				ResultSet rs = preparador.executeQuery();
				while (rs.next())
				 {
					 Paciente fun = new Paciente();
					 fun.setFK_internamento(rs.getInt("id_internamento"));
					 fun.setNome_doutor(rs.getString("medico"));
					 fun.setNome(rs.getString("nome"));
					 fun.setNomem(rs.getString("nome_meio"));
					 fun.setApelido(rs.getString("ultimo_nome"));
					 fun.setNomegenero(rs.getString("genero"));
					 Calendar data = Calendar.getInstance();
				     data.setTime(rs.getDate("data_nascimento"));
				     fun.setDataNasc(data);
				     fun.setIdade(rs.getDate("data_nascimento"));
					 fun.setServico(rs.getString("descricao"));
					 Calendar data1 = Calendar.getInstance();
					 fun.setData(rs.getDate("data_internamento"));
					 fun.setData_confirmacao(data1);
					 fun.setSala(rs.getString("numero_sala"));
					 fun.setLeito(rs.getString("leito"));
					 fun.setSector(rs.getString("sector"));
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
    public List <Paciente> buscarPorDataAndNomeAndSectorAndSalaPacInt(Date dataI, Date dataF, String nome, String sector, String sala)
    {
			List <Paciente> lista = new ArrayList<Paciente>();
			String sql = "SELECT * FROM vwinternados WHERE data_internamento BETWEEN ? AND ? AND nome LIKE '"+nome+"%' OR ULTIMO_NOME LIKE '"+nome+"%' AND FK_paciente = ? ORDER BY (NOME) AND sector = ? AND numero_sala =?";
			try {
				con = Conexao.getConexao();
				PreparedStatement preparador = con.prepareStatement(sql);
				preparador.setDate(1, dataI);
				preparador.setDate(2, dataF);
				preparador.setString(3, nome);
				preparador.setString(4, sector);
				preparador.setString(5, sala);
				ResultSet rs = preparador.executeQuery();
				while (rs.next())
				 {
					 Paciente fun = new Paciente();
					 fun.setFK_internamento(rs.getInt("id_internamento"));
					 fun.setNome_doutor(rs.getString("medico"));
					 fun.setNome(rs.getString("nome"));
					 fun.setNomem(rs.getString("nome_meio"));
					 fun.setApelido(rs.getString("ultimo_nome"));
					 fun.setNomegenero(rs.getString("genero"));
					 Calendar data = Calendar.getInstance();
				     data.setTime(rs.getDate("data_nascimento"));
				     fun.setDataNasc(data);
				     fun.setIdade(rs.getDate("data_nascimento"));
					 fun.setServico(rs.getString("descricao"));
					 Calendar data1 = Calendar.getInstance();
					 fun.setData(rs.getDate("data_internamento"));
					 fun.setData_confirmacao(data1);
					 fun.setSala(rs.getString("numero_sala"));
					 fun.setLeito(rs.getString("leito"));
					 fun.setSector(rs.getString("sector"));
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
    public List <Paciente> buscarPorDataAndIdadeAndGeneroPacInt(Date dataI, Date dataF, String idade1, String idade2, String genero)
    {
			List <Paciente> lista = new ArrayList<Paciente>();
			String sql = "SELECT * FROM vwinternados WHERE data_internamento BETWEEN ? AND ? AND IDADE BETWEEN ? AND ? AND FK_genero =?";
			try {
				con = Conexao.getConexao();
				PreparedStatement preparador = con.prepareStatement(sql);
				preparador.setDate(1, dataI);
				preparador.setDate(2, dataF);
				preparador.setString(3, idade1);
				preparador.setString(4, idade2);
				preparador.setString(5, genero);
				ResultSet rs = preparador.executeQuery();
				while (rs.next())
				 {
					 Paciente fun = new Paciente();
					 fun.setFK_internamento(rs.getInt("id_internamento"));
					 fun.setNome_doutor(rs.getString("medico"));
					 fun.setNome(rs.getString("nome"));
					 fun.setNomem(rs.getString("nome_meio"));
					 fun.setApelido(rs.getString("ultimo_nome"));
					 fun.setNomegenero(rs.getString("genero"));
					 Calendar data = Calendar.getInstance();
				     data.setTime(rs.getDate("data_nascimento"));
				     fun.setDataNasc(data);
				     fun.setIdade(rs.getDate("data_nascimento"));
					 fun.setServico(rs.getString("descricao"));
					 Calendar data1 = Calendar.getInstance();
					 fun.setData(rs.getDate("data_internamento"));
					 fun.setData_confirmacao(data1);
					 fun.setSala(rs.getString("numero_sala"));
					 fun.setLeito(rs.getString("leito"));
					 fun.setSector(rs.getString("sector"));
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
    public List <Paciente> buscarPorDataAndIdadeAndMedicoPacInt(Date dataI, Date dataF, String idade1, String idade2, String medico)
    {
			List <Paciente> lista = new ArrayList<Paciente>();
			String sql = "SELECT * FROM vwinternados WHERE data_internamento BETWEEN ? AND ? AND IDADE BETWEEN ? AND ? AND medico =?";
			try {
				con = Conexao.getConexao();
				PreparedStatement preparador = con.prepareStatement(sql);
				preparador.setDate(1, dataI);
				preparador.setDate(2, dataF);
				preparador.setString(3, idade1);
				preparador.setString(4, idade2);
				preparador.setString(5, medico);
				ResultSet rs = preparador.executeQuery();
				while (rs.next())
				 {
					 Paciente fun = new Paciente();
					 fun.setFK_internamento(rs.getInt("id_internamento"));
					 fun.setNome_doutor(rs.getString("medico"));
					 fun.setNome(rs.getString("nome"));
					 fun.setNomem(rs.getString("nome_meio"));
					 fun.setApelido(rs.getString("ultimo_nome"));
					 fun.setNomegenero(rs.getString("genero"));
					 Calendar data = Calendar.getInstance();
				     data.setTime(rs.getDate("data_nascimento"));
				     fun.setDataNasc(data);
				     fun.setIdade(rs.getDate("data_nascimento"));
					 fun.setServico(rs.getString("descricao"));
					 Calendar data1 = Calendar.getInstance();
					 fun.setData(rs.getDate("data_internamento"));
					 fun.setData_confirmacao(data1);
					 fun.setSala(rs.getString("numero_sala"));
					 fun.setLeito(rs.getString("leito"));
					 fun.setSector(rs.getString("sector"));
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
    public List <Paciente> buscarPorDataAndIdadeAndSectorAndSalaPacInt(Date dataI, Date dataF, String idade1, String idade2, String sector, String sala)
    {
			List <Paciente> lista = new ArrayList<Paciente>();
			String sql = "SELECT * FROM vwinternados WHERE data_internamento BETWEEN ? AND ? AND IDADE BETWEEN ? AND ? AND sector =? AND numero_sala =?";
			try {
				con = Conexao.getConexao();
				PreparedStatement preparador = con.prepareStatement(sql);
				preparador.setDate(1, dataI);
				preparador.setDate(2, dataF);
				preparador.setString(3, idade1);
				preparador.setString(4, idade2);
				preparador.setString(5, sector);
				preparador.setString(6, sala);
				ResultSet rs = preparador.executeQuery();
				while (rs.next())
				 {
					 Paciente fun = new Paciente();
					 fun.setFK_internamento(rs.getInt("id_internamento"));
					 fun.setNome_doutor(rs.getString("medico"));
					 fun.setNome(rs.getString("nome"));
					 fun.setNomem(rs.getString("nome_meio"));
					 fun.setApelido(rs.getString("ultimo_nome"));
					 fun.setNomegenero(rs.getString("genero"));
					 Calendar data = Calendar.getInstance();
				     data.setTime(rs.getDate("data_nascimento"));
				     fun.setDataNasc(data);
				     fun.setIdade(rs.getDate("data_nascimento"));
					 fun.setServico(rs.getString("descricao"));
					 Calendar data1 = Calendar.getInstance();
					 fun.setData(rs.getDate("data_internamento"));
					 fun.setData_confirmacao(data1);
					 fun.setSala(rs.getString("numero_sala"));
					 fun.setLeito(rs.getString("leito"));
					 fun.setSector(rs.getString("sector"));
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
    public List <Paciente> buscarPorDataAndGeneroAndMedicoPacInt(Date dataI, Date dataF, String genero, String medico)
    {
			List <Paciente> lista = new ArrayList<Paciente>();
			String sql = "SELECT * FROM vwinternados WHERE data_internamento BETWEEN ? AND ? AND FK_genero =? AND medico =?";
			try {
				con = Conexao.getConexao();
				PreparedStatement preparador = con.prepareStatement(sql);
				preparador.setDate(1, dataI);
				preparador.setDate(2, dataF);
				preparador.setString(3, genero);
				preparador.setString(4, medico);
				ResultSet rs = preparador.executeQuery();
				while (rs.next())
				 {
					 Paciente fun = new Paciente();
					 fun.setFK_internamento(rs.getInt("id_internamento"));
					 fun.setNome_doutor(rs.getString("medico"));
					 fun.setNome_doutor(rs.getString("NomeCompleto"));
					 fun.setNome(rs.getString("nome"));
					 fun.setNomem(rs.getString("nome_meio"));
					 fun.setApelido(rs.getString("ultimo_nome"));
					 fun.setNomegenero(rs.getString("genero"));
					 Calendar data = Calendar.getInstance();
				     data.setTime(rs.getDate("data_nascimento"));
				     fun.setDataNasc(data);
				     fun.setIdade(rs.getDate("data_nascimento"));
					 fun.setServico(rs.getString("descricao"));
					 Calendar data1 = Calendar.getInstance();
					 fun.setData(rs.getDate("data_internamento"));
					 fun.setData_confirmacao(data1);
					 fun.setSala(rs.getString("numero_sala"));
					 fun.setLeito(rs.getString("leito"));
					 fun.setSector(rs.getString("sector"));
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
    public List <Paciente> buscarPorDataAndGeneroAndSectorAndSalaPacInt(Date dataI, Date dataF, String genero, String sector, String sala)
    {
			List <Paciente> lista = new ArrayList<Paciente>();
			String sql = "SELECT * FROM vwinternados WHERE data_internamento BETWEEN ? AND ? AND FK_genero =? AND sector =? AND numero_sala =?";
			try {
				con = Conexao.getConexao();
				PreparedStatement preparador = con.prepareStatement(sql);
				preparador.setDate(1, dataI);
				preparador.setDate(2, dataF);
				preparador.setString(3, genero);
				preparador.setString(4, sector);
				preparador.setString(5, sala);
				ResultSet rs = preparador.executeQuery();
				while (rs.next())
				 {
					 Paciente fun = new Paciente();
					 fun.setFK_internamento(rs.getInt("id_internamento"));
					 fun.setNome_doutor(rs.getString("medico"));
					 fun.setNome(rs.getString("nome"));
					 fun.setNomem(rs.getString("nome_meio"));
					 fun.setApelido(rs.getString("ultimo_nome"));
					 fun.setNomegenero(rs.getString("genero"));
					 Calendar data = Calendar.getInstance();
				     data.setTime(rs.getDate("data_nascimento"));
				     fun.setDataNasc(data);
				     fun.setIdade(rs.getDate("data_nascimento"));
					 fun.setServico(rs.getString("descricao"));
					 Calendar data1 = Calendar.getInstance();
					 fun.setData(rs.getDate("data_internamento"));
					 fun.setData_confirmacao(data1);
					 fun.setSala(rs.getString("numero_sala"));
					 fun.setLeito(rs.getString("leito"));
					 fun.setSector(rs.getString("sector"));
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
    public List <Paciente> buscarPorDataAndMedicoAndSectorAndSalaPacInt(Date dataI, Date dataF, String medico, String sector, String sala)
    {
			List <Paciente> lista = new ArrayList<Paciente>();
			String sql = "SELECT * FROM vwinternados WHERE data_internamento BETWEEN ? AND ? AND medico =? AND sector =? AND numero_sala =?";
			try {
				con = Conexao.getConexao();
				PreparedStatement preparador = con.prepareStatement(sql);
				preparador.setDate(1, dataI);
				preparador.setDate(2, dataF);
				preparador.setString(3, medico);
				preparador.setString(4, sector);
				preparador.setString(5, sala);
				ResultSet rs = preparador.executeQuery();
				while (rs.next())
				 {
					 Paciente fun = new Paciente();
					 fun.setFK_internamento(rs.getInt("id_internamento"));
					 fun.setNome_doutor(rs.getString("medico"));
					 fun.setNome(rs.getString("nome"));
					 fun.setNomem(rs.getString("nome_meio"));
					 fun.setApelido(rs.getString("ultimo_nome"));
					 fun.setNomegenero(rs.getString("genero"));
					 Calendar data = Calendar.getInstance();
				     data.setTime(rs.getDate("data_nascimento"));
				     fun.setDataNasc(data);
				     fun.setIdade(rs.getDate("data_nascimento"));
					 fun.setServico(rs.getString("descricao"));
					 Calendar data1 = Calendar.getInstance();
					 fun.setData(rs.getDate("data_internamento"));
					 fun.setData_confirmacao(data1);
					 fun.setSala(rs.getString("numero_sala"));
					 fun.setLeito(rs.getString("leito"));
					 fun.setSector(rs.getString("sector"));
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
    
//    FIM DO INTERNAMENTO
//    ÍNICIO HISTÓRICO ALTA MEDICA
    public List <Paciente> buscarHistoricoAltaMedica()
    {
			List <Paciente> lista = new ArrayList<Paciente>();
			String sql = "SELECT * FROM vwrelatorioaltamedica where data_registo = curdate();";
			try {
				con = Conexao.getConexao();
				PreparedStatement preparador = con.prepareStatement(sql);
				ResultSet rs = preparador.executeQuery();
				if(rs.next())
				 {
					Paciente fun = new Paciente();
					 fun.setNomeCompleto(rs.getString("paciente"));
					 fun.setNomegenero(rs.getString("genero"));
					 fun.setSector(rs.getString("sector"));
					 fun.setSala(rs.getString("numero_sala"));
					 fun.setLeito(rs.getString("leito"));
					 Calendar data1 = Calendar.getInstance();
					 fun.setData(rs.getDate("data_registo"));
					 fun.setData_confirmacao(data1);
				     fun.setDescricao(rs.getString("motivo_alta"));
				     fun.setIdade_dopaciente(rs.getInt("idade"));
				     fun.setFK_alta(rs.getInt("id_alta"));
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
    public List <Paciente> buscarPorNomeHistAltaMedico(Date dataI, Date dataF, String nome)
    {
			List <Paciente> lista = new ArrayList<Paciente>();
			String sql = "SELECT * FROM vwrelatorioaltamedica WHERE data_registo BETWEEN ? AND ? AND paciente LIKE '"+nome+"%' OR paciente LIKE '"+nome+"%' AND FK_paciente = ? ORDER BY (paciente)";
			try {
				con = Conexao.getConexao();
				PreparedStatement preparador = con.prepareStatement(sql);
				preparador.setDate(1, dataI);
				preparador.setDate(2, dataF);
				preparador.setString(3, nome);
				ResultSet rs = preparador.executeQuery();
				while (rs.next())
				 {
					 Paciente fun = new Paciente();
					 fun.setNomeCompleto(rs.getString("paciente"));
					 fun.setNomegenero(rs.getString("genero"));
					 fun.setSector(rs.getString("sector"));
					 fun.setSala(rs.getString("numero_sala"));
					 fun.setLeito(rs.getString("leito"));
					 Calendar data1 = Calendar.getInstance();
					 fun.setData(rs.getDate("data_registo"));
					 fun.setData_confirmacao(data1);
				     fun.setDescricao(rs.getString("motivo_alta"));
				     fun.setIdade_dopaciente(rs.getInt("idade"));
				     fun.setFK_alta(rs.getInt("id_alta"));
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
    public List <Paciente> buscarPorDataHistAltaMedico(Date dataI, Date dataF)
    {
			List <Paciente> lista = new ArrayList<Paciente>();
			String sql = "SELECT * FROM vwrelatorioaltamedica WHERE data_registo BETWEEN ? AND ?";
			try {
				con = Conexao.getConexao();
				PreparedStatement preparador = con.prepareStatement(sql);
				preparador.setDate(1, dataI);
				preparador.setDate(2, dataF);
				ResultSet rs = preparador.executeQuery();
				while (rs.next())
				 {
					 Paciente fun = new Paciente();
					 fun.setNomeCompleto(rs.getString("paciente"));
					 fun.setNomegenero(rs.getString("genero"));
					 fun.setSector(rs.getString("sector"));
					 fun.setSala(rs.getString("numero_sala"));
					 fun.setLeito(rs.getString("leito"));
					 Calendar data1 = Calendar.getInstance();
					 fun.setData(rs.getDate("data_registo"));
					 fun.setData_confirmacao(data1);
				     fun.setDescricao(rs.getString("motivo_alta"));
				     fun.setIdade_dopaciente(rs.getInt("idade"));
				     fun.setFK_alta(rs.getInt("id_alta"));
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
    public List <Paciente> buscarPorIdadesHistAltaMedico(Date dataI, Date dataF, String idade1, String idade2)
    {
			List <Paciente> lista = new ArrayList<Paciente>();
			String sql = "SELECT * FROM vwrelatorioaltamedica WHERE data_registo BETWEEN ? AND ? AND IDADE BETWEEN ? AND ?";
			try {
				con = Conexao.getConexao();
				PreparedStatement preparador = con.prepareStatement(sql);
				preparador.setDate(1, dataI);
				preparador.setDate(2, dataF);
				preparador.setString(3, idade1);
				preparador.setString(4, idade2);
				ResultSet rs = preparador.executeQuery();
				while (rs.next())
				 {
					 Paciente fun = new Paciente();
					 fun.setNomeCompleto(rs.getString("paciente"));
					 fun.setNomegenero(rs.getString("genero"));
					 fun.setSector(rs.getString("sector"));
					 fun.setSala(rs.getString("numero_sala"));
					 fun.setLeito(rs.getString("leito"));
					 Calendar data1 = Calendar.getInstance();
					 fun.setData(rs.getDate("data_registo"));
					 fun.setData_confirmacao(data1);
				     fun.setDescricao(rs.getString("motivo_alta"));
				     fun.setIdade_dopaciente(rs.getInt("idade"));
				     fun.setFK_alta(rs.getInt("id_alta"));
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
    public List <Paciente> buscarPorIdadeHistAltaMedico(Date dataI, Date dataF, String idade)
    {
			List <Paciente> lista = new ArrayList<Paciente>();
			String sql = "SELECT * FROM vwrelatorioaltamedica WHERE data_registo BETWEEN ? AND ? AND IDADE =?";
			try {
				con = Conexao.getConexao();
				PreparedStatement preparador = con.prepareStatement(sql);
				preparador.setDate(1, dataI);
				preparador.setDate(2, dataF);
				preparador.setString(3, idade);
				ResultSet rs = preparador.executeQuery();
				while (rs.next())
				 {
					 Paciente fun = new Paciente();
					 fun.setNomeCompleto(rs.getString("paciente"));
					 fun.setNomegenero(rs.getString("genero"));
					 fun.setSector(rs.getString("sector"));
					 fun.setSala(rs.getString("numero_sala"));
					 fun.setLeito(rs.getString("leito"));
					 Calendar data1 = Calendar.getInstance();
					 fun.setData(rs.getDate("data_registo"));
					 fun.setData_confirmacao(data1);
				     fun.setDescricao(rs.getString("motivo_alta"));
				     fun.setIdade_dopaciente(rs.getInt("idade"));
				     fun.setFK_alta(rs.getInt("id_alta"));
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
    
    public List <Paciente> buscarPorGeneroHistAltaMedico(Date dataI, Date dataF, String sexo)
    {
			List <Paciente> lista = new ArrayList<Paciente>();
			String sql = "SELECT * FROM vwrelatorioaltamedica WHERE data_registo BETWEEN ? AND ? AND FK_genero =?";
			try {
				con = Conexao.getConexao();
				PreparedStatement preparador = con.prepareStatement(sql);
				preparador.setDate(1, dataI);
				preparador.setDate(2, dataF);
				preparador.setString(3, sexo);
				ResultSet rs = preparador.executeQuery();
				while (rs.next())
				 {
					 Paciente fun = new Paciente();
					 fun.setNomeCompleto(rs.getString("paciente"));
					 fun.setNomegenero(rs.getString("genero"));
					 fun.setSector(rs.getString("sector"));
					 fun.setSala(rs.getString("numero_sala"));
					 fun.setLeito(rs.getString("leito"));
					 Calendar data1 = Calendar.getInstance();
					 fun.setData(rs.getDate("data_registo"));
					 fun.setData_confirmacao(data1);
				     fun.setDescricao(rs.getString("motivo_alta"));
				     fun.setIdade_dopaciente(rs.getInt("idade"));
				     fun.setFK_alta(rs.getInt("id_alta"));
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
    public List <Paciente> buscarPorMedicoHistAltaMedico(Date dataI, Date dataF, String medico)
    {
			List <Paciente> lista = new ArrayList<Paciente>();
			String sql = "SELECT * FROM vwrelatorioaltamedica WHERE data_registo BETWEEN ? AND ? AND nome_med =?";
			try {
				con = Conexao.getConexao();
				PreparedStatement preparador = con.prepareStatement(sql);
				preparador.setDate(1, dataI);
				preparador.setDate(2, dataF);
				preparador.setString(3, medico);
				ResultSet rs = preparador.executeQuery();
				while (rs.next())
				 {
					 Paciente fun = new Paciente();
					 fun.setNomeCompleto(rs.getString("paciente"));
					 fun.setNomegenero(rs.getString("genero"));
					 fun.setSector(rs.getString("sector"));
					 fun.setSala(rs.getString("numero_sala"));
					 fun.setLeito(rs.getString("leito"));
					 Calendar data1 = Calendar.getInstance();
					 fun.setData(rs.getDate("data_registo"));
					 fun.setData_confirmacao(data1);
				     fun.setDescricao(rs.getString("motivo_alta"));
				     fun.setIdade_dopaciente(rs.getInt("idade"));
				     fun.setFK_alta(rs.getInt("id_alta"));
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
    public List <Paciente> buscarPorSectorAndSalaHistAltaMedico(Date dataI, Date dataF, String sector, String sala)
    {
			List <Paciente> lista = new ArrayList<Paciente>();
			String sql = "SELECT * FROM vwrelatorioaltamedica WHERE data_registo BETWEEN ? AND ? AND sector =? AND numero_sala =?";
			try {
				con = Conexao.getConexao();
				PreparedStatement preparador = con.prepareStatement(sql);
				preparador.setDate(1, dataI);
				preparador.setDate(2, dataF);
				preparador.setString(3, sector);
				preparador.setString(4, sala);
				ResultSet rs = preparador.executeQuery();
				while (rs.next())
				 {
					 Paciente fun = new Paciente();
					 fun.setNomeCompleto(rs.getString("paciente"));
					 fun.setNomegenero(rs.getString("genero"));
					 fun.setSector(rs.getString("sector"));
					 fun.setSala(rs.getString("numero_sala"));
					 fun.setLeito(rs.getString("leito"));
					 Calendar data1 = Calendar.getInstance();
					 fun.setData(rs.getDate("data_registo"));
					 fun.setData_confirmacao(data1);
				     fun.setDescricao(rs.getString("motivo_alta"));
				     fun.setIdade_dopaciente(rs.getInt("idade"));
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
    public List <Paciente> buscarPorMotivoHistAltaMedico(Date dataI, Date dataF, String motivo)
    {
			List <Paciente> lista = new ArrayList<Paciente>();
			String sql = "SELECT * FROM vwrelatorioaltamedica WHERE data_registo BETWEEN ? AND ? AND motivo_alta =?";
			try {
				con = Conexao.getConexao();
				PreparedStatement preparador = con.prepareStatement(sql);
				preparador.setDate(1, dataI);
				preparador.setDate(2, dataF);
				preparador.setString(3, motivo);
				ResultSet rs = preparador.executeQuery();
				while (rs.next())
				 {
					 Paciente fun = new Paciente();
					 fun.setNomeCompleto(rs.getString("paciente"));
					 fun.setNomegenero(rs.getString("genero"));
					 fun.setSector(rs.getString("sector"));
					 fun.setSala(rs.getString("numero_sala"));
					 fun.setLeito(rs.getString("leito"));
					 Calendar data1 = Calendar.getInstance();
					 fun.setData(rs.getDate("data_registo"));
					 fun.setData_confirmacao(data1);
				     fun.setDescricao(rs.getString("motivo_alta"));
				     fun.setIdade_dopaciente(rs.getInt("idade"));
				     fun.setFK_alta(rs.getInt("id_alta"));
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
    public List <Paciente> buscarPorNomeAndIdadeHistAltaMedico(Date dataI, Date dataF, String nome, String idade1, String idade2)
    {
			List <Paciente> lista = new ArrayList<Paciente>();
			String sql = "SELECT * FROM vwrelatorioaltamedica WHERE data_registo BETWEEN ? AND ? AND paciente LIKE '"+nome+"%' OR paciente LIKE '"+nome+"%' AND FK_paciente = ? ORDER BY (paciente) AND IDADE BETWEEN ? AND ?";
			try {
				con = Conexao.getConexao();
				PreparedStatement preparador = con.prepareStatement(sql);
				preparador.setDate(1, dataI);
				preparador.setDate(2, dataF);
				preparador.setString(3, nome);
				preparador.setString(4, idade1);
				preparador.setString(5, idade2);
				ResultSet rs = preparador.executeQuery();
				while (rs.next())
				 {
					 Paciente fun = new Paciente();
					 fun.setNomeCompleto(rs.getString("paciente"));
					 fun.setNomegenero(rs.getString("genero"));
					 fun.setSector(rs.getString("sector"));
					 fun.setSala(rs.getString("numero_sala"));
					 fun.setLeito(rs.getString("leito"));
					 Calendar data1 = Calendar.getInstance();
					 fun.setData(rs.getDate("data_registo"));
					 fun.setData_confirmacao(data1);
				     fun.setDescricao(rs.getString("motivo_alta"));
				     fun.setIdade_dopaciente(rs.getInt("idade"));
				     fun.setFK_alta(rs.getInt("id_alta"));
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
    public List <Paciente> buscarPorNomeAndGeneroHistAltaMedico(Date dataI, Date dataF, String nome, String sexo)
    {
			List <Paciente> lista = new ArrayList<Paciente>();
			String sql = "SELECT * FROM vwrelatorioaltamedica WHERE data_registo BETWEEN ? AND ? AND paciente LIKE '"+nome+"%' OR paciente LIKE '"+nome+"%' AND FK_paciente = ? ORDER BY (paciente) AND sexo =?";
			try {
				con = Conexao.getConexao();
				PreparedStatement preparador = con.prepareStatement(sql);
				preparador.setDate(1, dataI);
				preparador.setDate(2, dataF);
				preparador.setString(3, nome);
				preparador.setString(4, sexo);
				ResultSet rs = preparador.executeQuery();
				while (rs.next())
				 {
					 Paciente fun = new Paciente();
					 fun.setNomeCompleto(rs.getString("paciente"));
					 fun.setNomegenero(rs.getString("genero"));
					 fun.setSector(rs.getString("sector"));
					 fun.setSala(rs.getString("numero_sala"));
					 fun.setLeito(rs.getString("leito"));
					 Calendar data1 = Calendar.getInstance();
					 fun.setData(rs.getDate("data_registo"));
					 fun.setData_confirmacao(data1);
				     fun.setDescricao(rs.getString("motivo_alta"));
				     fun.setIdade_dopaciente(rs.getInt("idade"));
				     fun.setFK_alta(rs.getInt("id_alta"));
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
    public List <Paciente> buscarPorNomeAndMedicoHistAltaMedico(Date dataI, Date dataF, String nome, String medico)
    {
			List <Paciente> lista = new ArrayList<Paciente>();
			String sql = "SELECT * FROM vwrelatorioaltamedica WHERE data_registo BETWEEN ? AND ? AND paciente LIKE '"+nome+"%' OR paciente LIKE '"+nome+"%' AND FK_paciente = ? ORDER BY (paciente) AND sexo =?";
			try {
				con = Conexao.getConexao();
				PreparedStatement preparador = con.prepareStatement(sql);
				preparador.setDate(1, dataI);
				preparador.setDate(2, dataF);
				preparador.setString(3, nome);
				preparador.setString(4, medico);
				ResultSet rs = preparador.executeQuery();
				while (rs.next())
				 {
					 Paciente fun = new Paciente();
					 fun.setNomeCompleto(rs.getString("paciente"));
					 fun.setNomegenero(rs.getString("genero"));
					 fun.setSector(rs.getString("sector"));
					 fun.setSala(rs.getString("numero_sala"));
					 fun.setLeito(rs.getString("leito"));
					 Calendar data1 = Calendar.getInstance();
					 fun.setData(rs.getDate("data_registo"));
					 fun.setData_confirmacao(data1);
				     fun.setDescricao(rs.getString("motivo_alta"));
				     fun.setIdade_dopaciente(rs.getInt("idade"));
				     fun.setFK_alta(rs.getInt("id_alta"));
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
    public List <Paciente> buscarPorNomeAndSectorAndSalaHistAltaMedico(Date dataI, Date dataF, String nome, String sector, String sala)
    {
			List <Paciente> lista = new ArrayList<Paciente>();
			String sql = "SELECT * FROM vwrelatorioaltamedica WHERE data_registo BETWEEN ? AND ? AND paciente LIKE '"+nome+"%' OR paciente LIKE '"+nome+"%' AND FK_paciente = ? ORDER BY (paciente) AND sector =? AND numero_sala =?";
			try {
				con = Conexao.getConexao();
				PreparedStatement preparador = con.prepareStatement(sql);
				preparador.setDate(1, dataI);
				preparador.setDate(2, dataF);
				preparador.setString(3, nome);
				preparador.setString(4, sector);
				preparador.setString(5, sala);
				ResultSet rs = preparador.executeQuery();
				while (rs.next())
				 {
					 Paciente fun = new Paciente();
					 fun.setNomeCompleto(rs.getString("paciente"));
					 fun.setNomegenero(rs.getString("genero"));
					 fun.setSector(rs.getString("sector"));
					 fun.setSala(rs.getString("numero_sala"));
					 fun.setLeito(rs.getString("leito"));
					 Calendar data1 = Calendar.getInstance();
					 fun.setData(rs.getDate("data_registo"));
					 fun.setData_confirmacao(data1);
				     fun.setDescricao(rs.getString("motivo_alta"));
				     fun.setIdade_dopaciente(rs.getInt("idade"));
				     fun.setFK_alta(rs.getInt("id_alta"));
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
    public List <Paciente> buscarPorNomeAndMotivoHistAltaMedico(Date dataI, Date dataF, String nome, String motivo)
    {
			List <Paciente> lista = new ArrayList<Paciente>();
			String sql = "SELECT * FROM vwrelatorioaltamedica WHERE data_registo BETWEEN ? AND ? AND paciente LIKE '"+nome+"%' OR paciente LIKE '"+nome+"%' AND FK_paciente = ? ORDER BY (paciente) AND motivo =?";
			try {
				con = Conexao.getConexao();
				PreparedStatement preparador = con.prepareStatement(sql);
				preparador.setDate(1, dataI);
				preparador.setDate(2, dataF);
				preparador.setString(3, nome);
				preparador.setString(4, motivo);
				ResultSet rs = preparador.executeQuery();
				while (rs.next())
				 {
					 Paciente fun = new Paciente();
					 fun.setNomeCompleto(rs.getString("paciente"));
					 fun.setNomegenero(rs.getString("genero"));
					 fun.setSector(rs.getString("sector"));
					 fun.setSala(rs.getString("numero_sala"));
					 fun.setLeito(rs.getString("leito"));
					 Calendar data1 = Calendar.getInstance();
					 fun.setData(rs.getDate("data_registo"));
					 fun.setData_confirmacao(data1);
				     fun.setDescricao(rs.getString("motivo_alta"));
				     fun.setIdade_dopaciente(rs.getInt("idade"));
				     fun.setFK_alta(rs.getInt("id_alta"));
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
    public List <Paciente> buscarPorIdadeAndGeneroHistAltaMedico(Date dataI, Date dataF, String idade1, String idade2, String sexo)
    {
			List <Paciente> lista = new ArrayList<Paciente>();
			String sql = "SELECT * FROM vwrelatorioaltamedica WHERE data_registo BETWEEN ? AND ? AND IDADE BETWEEN ? AND ? AND sexo =?";
			try {
				con = Conexao.getConexao();
				PreparedStatement preparador = con.prepareStatement(sql);
				preparador.setDate(1, dataI);
				preparador.setDate(2, dataF);
				preparador.setString(3, idade1);
				preparador.setString(4, idade2);
				preparador.setString(5, sexo);
				ResultSet rs = preparador.executeQuery();
				while (rs.next())
				 {
					 Paciente fun = new Paciente();
					 fun.setNomeCompleto(rs.getString("paciente"));
					 fun.setNomegenero(rs.getString("genero"));
					 fun.setSector(rs.getString("sector"));
					 fun.setSala(rs.getString("numero_sala"));
					 fun.setLeito(rs.getString("leito"));
					 Calendar data1 = Calendar.getInstance();
					 fun.setData(rs.getDate("data_registo"));
					 fun.setData_confirmacao(data1);
				     fun.setDescricao(rs.getString("motivo_alta"));
				     fun.setIdade_dopaciente(rs.getInt("idade"));
				     fun.setFK_alta(rs.getInt("id_alta"));
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
    public List <Paciente> buscarPorIdadeAndMedicoHistAltaMedico(Date dataI, Date dataF, String idade1, String idade2, String medico)
    {
			List <Paciente> lista = new ArrayList<Paciente>();
			String sql = "SELECT * FROM vwrelatorioaltamedica WHERE data_registo BETWEEN ? AND ? AND IDADE BETWEEN ? AND ? AND medico =?";
			try {
				con = Conexao.getConexao();
				PreparedStatement preparador = con.prepareStatement(sql);
				preparador.setDate(1, dataI);
				preparador.setDate(2, dataF);
				preparador.setString(3, idade1);
				preparador.setString(4, idade2);
				preparador.setString(5, medico);
				ResultSet rs = preparador.executeQuery();
				while (rs.next())
				 {
					 Paciente fun = new Paciente();
					 fun.setNomeCompleto(rs.getString("paciente"));
					 fun.setNomegenero(rs.getString("genero"));
					 fun.setSector(rs.getString("sector"));
					 fun.setSala(rs.getString("numero_sala"));
					 fun.setLeito(rs.getString("leito"));
					 Calendar data1 = Calendar.getInstance();
					 fun.setData(rs.getDate("data_registo"));
					 fun.setData_confirmacao(data1);
				     fun.setDescricao(rs.getString("motivo_alta"));
				     fun.setIdade_dopaciente(rs.getInt("idade"));
				     fun.setFK_alta(rs.getInt("id_alta"));
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
    public List <Paciente> buscarPorIdadeAndSectorAndSalaHistAltaMedico(Date dataI, Date dataF, String idade1, String idade2, String sector, String sala)
    {
			List <Paciente> lista = new ArrayList<Paciente>();
			String sql = "SELECT * FROM vwrelatorioaltamedica WHERE data_registo BETWEEN ? AND ? AND IDADE BETWEEN ? AND ? AND sector =? AND numero_sala =?";
			try {
				con = Conexao.getConexao();
				PreparedStatement preparador = con.prepareStatement(sql);
				preparador.setDate(1, dataI);
				preparador.setDate(2, dataF);
				preparador.setString(3, idade1);
				preparador.setString(4, idade2);
				preparador.setString(5, sector);
				preparador.setString(6, sala);
				ResultSet rs = preparador.executeQuery();
				while (rs.next())
				 {
					 Paciente fun = new Paciente();
					 fun.setNomeCompleto(rs.getString("paciente"));
					 fun.setNomegenero(rs.getString("genero"));
					 fun.setSector(rs.getString("sector"));
					 fun.setSala(rs.getString("numero_sala"));
					 fun.setLeito(rs.getString("leito"));
					 Calendar data1 = Calendar.getInstance();
					 fun.setData(rs.getDate("data_registo"));
					 fun.setData_confirmacao(data1);
				     fun.setDescricao(rs.getString("motivo_alta"));
				     fun.setIdade_dopaciente(rs.getInt("idade"));
				     fun.setFK_alta(rs.getInt("id_alta"));
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
    public List <Paciente> buscarPorIdadeAndMotivoHistAltaMedico(Date dataI, Date dataF, String idade1, String idade2, String motivo)
    {
			List <Paciente> lista = new ArrayList<Paciente>();
			String sql = "SELECT * FROM vwrelatorioaltamedica WHERE data_registo BETWEEN ? AND ? AND IDADE BETWEEN ? AND ? AND motivo =?";
			try {
				con = Conexao.getConexao();
				PreparedStatement preparador = con.prepareStatement(sql);
				preparador.setDate(1, dataI);
				preparador.setDate(2, dataF);
				preparador.setString(3, idade1);
				preparador.setString(4, idade2);
				preparador.setString(5, motivo);
				ResultSet rs = preparador.executeQuery();
				while (rs.next())
				 {
					 Paciente fun = new Paciente();
					 fun.setNomeCompleto(rs.getString("paciente"));
					 fun.setNomegenero(rs.getString("genero"));
					 fun.setSector(rs.getString("sector"));
					 fun.setSala(rs.getString("numero_sala"));
					 fun.setLeito(rs.getString("leito"));
					 Calendar data1 = Calendar.getInstance();
					 fun.setData(rs.getDate("data_registo"));
					 fun.setData_confirmacao(data1);
				     fun.setDescricao(rs.getString("motivo_alta"));
				     fun.setIdade_dopaciente(rs.getInt("idade"));
				     fun.setFK_alta(rs.getInt("id_alta"));
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
    public List <Paciente> buscarPorGeneroAndMedicoHistAltaMedico(Date dataI, Date dataF, String sexo, String medico)
    {
			List <Paciente> lista = new ArrayList<Paciente>();
			String sql = "SELECT * FROM vwrelatorioaltamedica WHERE data_registo BETWEEN ? AND ? AND sexo = ? AND medico =?";
			try {
				con = Conexao.getConexao();
				PreparedStatement preparador = con.prepareStatement(sql);
				preparador.setDate(1, dataI);
				preparador.setDate(2, dataF);
				preparador.setString(3, sexo);
				preparador.setString(4, medico);
				ResultSet rs = preparador.executeQuery();
				while (rs.next())
				 {
					 Paciente fun = new Paciente();
					 fun.setNomeCompleto(rs.getString("paciente"));
					 fun.setNomegenero(rs.getString("genero"));
					 fun.setSector(rs.getString("sector"));
					 fun.setSala(rs.getString("numero_sala"));
					 fun.setLeito(rs.getString("leito"));
					 Calendar data1 = Calendar.getInstance();
					 fun.setData(rs.getDate("data_registo"));
					 fun.setData_confirmacao(data1);
				     fun.setDescricao(rs.getString("motivo_alta"));
				     fun.setIdade_dopaciente(rs.getInt("idade"));
				     fun.setFK_alta(rs.getInt("id_alta"));
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
    public List <Paciente> buscarPorGeneroAndSectorAndSalaHistAltaMedico(Date dataI, Date dataF, String sexo, String sector, String sala)
    {
			List <Paciente> lista = new ArrayList<Paciente>();
			String sql = "SELECT * FROM vwrelatorioaltamedica WHERE data_registo BETWEEN ? AND ? AND sexo = ? AND sector =? AND numero_sala =?";
			try {
				con = Conexao.getConexao();
				PreparedStatement preparador = con.prepareStatement(sql);
				preparador.setDate(1, dataI);
				preparador.setDate(2, dataF);
				preparador.setString(3, sexo);
				preparador.setString(4, sector);
				preparador.setString(5, sala);
				ResultSet rs = preparador.executeQuery();
				while (rs.next())
				 {
					 Paciente fun = new Paciente();
					 fun.setNomeCompleto(rs.getString("paciente"));
					 fun.setNomegenero(rs.getString("genero"));
					 fun.setSector(rs.getString("sector"));
					 fun.setSala(rs.getString("numero_sala"));
					 fun.setLeito(rs.getString("leito"));
					 Calendar data1 = Calendar.getInstance();
					 fun.setData(rs.getDate("data_registo"));
					 fun.setData_confirmacao(data1);
				     fun.setDescricao(rs.getString("motivo_alta"));
				     fun.setIdade_dopaciente(rs.getInt("idade"));
				     fun.setFK_alta(rs.getInt("id_alta"));
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
    public List <Paciente> buscarPorGeneroAndMotivoHistAltaMedico(Date dataI, Date dataF, String sexo, String motivo)
    {
			List <Paciente> lista = new ArrayList<Paciente>();
			String sql = "SELECT * FROM vwrelatorioaltamedica WHERE data_registo BETWEEN ? AND ? AND sexo = ? AND motivo =?";
			try {
				con = Conexao.getConexao();
				PreparedStatement preparador = con.prepareStatement(sql);
				preparador.setDate(1, dataI);
				preparador.setDate(2, dataF);
				preparador.setString(3, sexo);
				preparador.setString(4, motivo);
				ResultSet rs = preparador.executeQuery();
				while (rs.next())
				 {
					 Paciente fun = new Paciente();
					 fun.setNomeCompleto(rs.getString("paciente"));
					 fun.setNomegenero(rs.getString("genero"));
					 fun.setSector(rs.getString("sector"));
					 fun.setSala(rs.getString("numero_sala"));
					 fun.setLeito(rs.getString("leito"));
					 Calendar data1 = Calendar.getInstance();
					 fun.setData(rs.getDate("data_registo"));
					 fun.setData_confirmacao(data1);
				     fun.setDescricao(rs.getString("motivo_alta"));
				     fun.setIdade_dopaciente(rs.getInt("idade"));
				     fun.setFK_alta(rs.getInt("id_alta"));
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
    public List <Paciente> buscarPorMedicoAndSectorAndSalaHistAltaMedico(Date dataI, Date dataF, String medico, String sector, String sala)
    {
			List <Paciente> lista = new ArrayList<Paciente>();
			String sql = "SELECT * FROM vwrelatorioaltamedica WHERE data_registo BETWEEN ? AND ? AND medico = ? AND sector =? AND sala =?";
			try {
				con = Conexao.getConexao();
				PreparedStatement preparador = con.prepareStatement(sql);
				preparador.setDate(1, dataI);
				preparador.setDate(2, dataF);
				preparador.setString(3, medico);
				preparador.setString(4, sector);
				preparador.setString(5, sala);
				ResultSet rs = preparador.executeQuery();
				while (rs.next())
				 {
					 Paciente fun = new Paciente();
					 fun.setNomeCompleto(rs.getString("paciente"));
					 fun.setNomegenero(rs.getString("genero"));
					 fun.setSector(rs.getString("sector"));
					 fun.setSala(rs.getString("numero_sala"));
					 fun.setLeito(rs.getString("leito"));
					 Calendar data1 = Calendar.getInstance();
					 fun.setData(rs.getDate("data_registo"));
					 fun.setData_confirmacao(data1);
				     fun.setDescricao(rs.getString("motivo_alta"));
				     fun.setIdade_dopaciente(rs.getInt("idade"));
				     fun.setFK_alta(rs.getInt("id_alta"));
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
    public List <Paciente> buscarPorMedicoAndMotivoHistAltaMedico(Date dataI, Date dataF, String medico, String motivo)
    {
			List <Paciente> lista = new ArrayList<Paciente>();
			String sql = "SELECT * FROM vwrelatorioaltamedica WHERE data_registo BETWEEN ? AND ? AND medico = ? AND motivo =?";
			try {
				con = Conexao.getConexao();
				PreparedStatement preparador = con.prepareStatement(sql);
				preparador.setDate(1, dataI);
				preparador.setDate(2, dataF);
				preparador.setString(3, medico);
				preparador.setString(4, motivo);
				ResultSet rs = preparador.executeQuery();
				while (rs.next())
				 {
					 Paciente fun = new Paciente();
					 fun.setNomeCompleto(rs.getString("paciente"));
					 fun.setNomegenero(rs.getString("genero"));
					 fun.setSector(rs.getString("sector"));
					 fun.setSala(rs.getString("numero_sala"));
					 fun.setLeito(rs.getString("leito"));
					 Calendar data1 = Calendar.getInstance();
					 fun.setData(rs.getDate("data_registo"));
					 fun.setData_confirmacao(data1);
				     fun.setDescricao(rs.getString("motivo_alta"));
				     fun.setIdade_dopaciente(rs.getInt("idade"));
				     fun.setFK_alta(rs.getInt("id_alta"));
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
    public List <Paciente> buscarPorSectorAndSalaAndMotivoHistAltaMedico(Date dataI, Date dataF, String sector, String sala, String motivo)
    {
			List <Paciente> lista = new ArrayList<Paciente>();
			String sql = "SELECT * FROM vwrelatorioaltamedica WHERE data_registo BETWEEN ? AND ? AND sector = ? AND sala =? AND motivo =?";
			try {
				con = Conexao.getConexao();
				PreparedStatement preparador = con.prepareStatement(sql);
				preparador.setDate(1, dataI);
				preparador.setDate(2, dataF);
				preparador.setString(3, sector);
				preparador.setString(4, sala);
				preparador.setString(5, motivo);
				ResultSet rs = preparador.executeQuery();
				while (rs.next())
				 {
					 Paciente fun = new Paciente();
					 fun.setNomeCompleto(rs.getString("paciente"));
					 fun.setNomegenero(rs.getString("genero"));
					 fun.setSector(rs.getString("sector"));
					 fun.setSala(rs.getString("numero_sala"));
					 fun.setLeito(rs.getString("leito"));
					 Calendar data1 = Calendar.getInstance();
					 fun.setData(rs.getDate("data_registo"));
					 fun.setData_confirmacao(data1);
				     fun.setDescricao(rs.getString("motivo_alta"));
				     fun.setIdade_dopaciente(rs.getInt("idade"));
				     fun.setFK_alta(rs.getInt("id_alta"));
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
    
    
    
//    FINAL ALTA MEDICA
    
    
    
    
    
    
    
    
    
//    Converter o Id para texto
    public String buscarPais(String pac)
	{
		String fkp = null;
		String sql = "SELECT PAIS FROM TBLPAIS where id_pais = ?";
		try {
			con = Conexao.getConexao();
			PreparedStatement preparador = con.prepareStatement(sql);
			preparador.setString(1, pac);
			ResultSet rs = preparador.executeQuery();
			
			if(rs.next())
				fkp = (rs.getString("PAIS"));
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
			return fkp;
	}
    public String buscarProvincia(String pac)
	{
		String fkp = null;
		String sql = "SELECT provincia FROM TBLPROVINCIA where id_provincia = ?";
		try {
			con = Conexao.getConexao();
			PreparedStatement preparador = con.prepareStatement(sql);
			preparador.setString(1, pac);
			ResultSet rs = preparador.executeQuery();
			
			if(rs.next())
				fkp = (rs.getString("provincia"));
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
			return fkp;
	}
    public String buscarMunicipio(String pac)
	{
		String fkp = null;
		String sql = "SELECT municipio FROM TBLMUNICIPIO where id_municipio = ?";
		try {
			con = Conexao.getConexao();
			PreparedStatement preparador = con.prepareStatement(sql);
			preparador.setString(1, pac);
			ResultSet rs = preparador.executeQuery();
			
			if(rs.next())
				fkp = (rs.getString("municipio"));
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
			return fkp;
	}
    public String buscarDistrito(String pac)
	{
		String fkp = null;
		String sql = "SELECT DISTRITO FROM TBLDISTRITO where id_distrito = ?";
		try {
			con = Conexao.getConexao();
			PreparedStatement preparador = con.prepareStatement(sql);
			preparador.setString(1, pac);
			ResultSet rs = preparador.executeQuery();
			
			if(rs.next())
				fkp = (rs.getString("DISTRITO"));
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
			return fkp;
	}
    
    
}
