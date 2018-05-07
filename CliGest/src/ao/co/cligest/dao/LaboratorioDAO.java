package ao.co.cligest.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Random;

import ao.co.cligest.entidades.Embalagem;
import ao.co.cligest.entidades.Exames;
import ao.co.cligest.entidades.Laboratorio;

import java.sql.Date;
import java.util.List;

public class LaboratorioDAO {

	private Connection con;
	Random gerador = new Random();
	
	public List<Exames> buscarPacienteAtendidos(){
		    List <Exames> lista = new ArrayList<Exames>();
				String sql = "SELECT NomeCompleto,FK_requisicao_exame,idade,genero,data_darecolha,sum(qtd_servicos) as qtd_servicos,FK_factura FROM vwpacientesatendidoslaboratorio where data_darecolha = curdate() group by FK_factura";
				try {
					 con = Conexao.getConexao();
					PreparedStatement preparador = con.prepareStatement(sql);
					 ResultSet rs = preparador.executeQuery();
					 while(rs.next())
					 {
						 Exames pac = new Exames();
						 pac.setFK_factura(rs.getInt("FK_factura"));
						 pac.setFK_numero_do_servico_de_exame(rs.getInt("FK_requisicao_exame"));
						 pac.setNomeCompleto(rs.getString("NomeCompleto"));
						 pac.setGenero(rs.getString("genero"));
						 pac.setQuantidade(rs.getInt("qtd_servicos"));
		    		     pac.setAno(rs.getInt("idade"));
		    		     Calendar data = Calendar.getInstance();
		    		     data.setTime(rs.getDate("data_darecolha"));
		    		     pac.setData_doatendimento(data);
						 lista.add(pac);
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
					}
				}
				return lista;
			}
	public List<Exames> buscarDataAtendidos(Date inicio, Date Fim,int orgin){
	    List <Exames> lista = new ArrayList<Exames>();
			String sql = "SELECT FK_paciente, FK_grupo_analise_clinica,FK_requisicao_exame,NumeroProcesso,NomeCompleto, FK_estatu_darecolha,idade,data_darecolha, "
					+ " genero,count(qtd_servicos) as 'qtd_servicos', data_darecolha, FK_factura "
					+ "FROM vwpacientesatendidoslaboratorio WHERE data_darecolha between ? AND ? and FK_provenienca_exame = ? group by fk_factura ";
			try {
				 con = Conexao.getConexao();
				PreparedStatement preparador = con.prepareStatement(sql);
				preparador.setDate(1, inicio);
				preparador.setDate(2, Fim);
				preparador.setInt(3, orgin);
				 ResultSet rs = preparador.executeQuery();
				 while(rs.next())
				 {
					 Exames pac = new Exames();
//					 pac.setId_pronto_aserexaminado(rs.getInt("id_pronto_aserexaminado"));
					 pac.setFK_factura(rs.getInt("FK_factura"));
					 pac.setFK_paciente(rs.getInt("FK_paciente"));
//					 pac.setFK_exame(rs.getInt("fk_exame"));
					 pac.setFK_numero_do_servico_de_exame(rs.getInt("FK_requisicao_exame"));
					 Calendar data = Calendar.getInstance();
	    		     data.setTime(rs.getDate("data_darecolha"));
	    		     pac.setData_darecolha(data);
					 pac.setFK_estatu_darecolha(rs.getInt("FK_estatu_darecolha"));
					 pac.setNumero_processo(rs.getString("NumeroProcesso"));
					 pac.setNomeCompleto(rs.getString("NomeCompleto"));
//					 Calendar datan = Calendar.getInstance();
//	    		     datan.setTime(rs.getDate("data_nascimento"));
//	    		     pac.setData_nascimento(datan);
					 pac.setGenero(rs.getString("genero"));
					 pac.setQuantidade(rs.getInt("qtd_servicos"));
					 Calendar datat = Calendar.getInstance();
	    		     datat.setTime(rs.getDate("data_darecolha"));
	    		     pac.setData_doatendimento(datat);
	    		     pac.setAno(rs.getInt("idade"));
					 lista.add(pac);
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
				}
			}
			return lista;
		}
	public List<Exames> buscarDataAndGrupoExameAtendidos(Date inicio, Date Fim, String grupo,int orig){
	    List <Exames> lista = new ArrayList<Exames>();
			String sql = "SELECT * FROM vwpacientesatendidoslaboratorio WHERE data_darecolha between ? AND ? and FK_grupo_analise_clinica =? and FK_provenienca_exame = ?";
			try {
				 con = Conexao.getConexao();
				PreparedStatement preparador = con.prepareStatement(sql);
				preparador.setDate(1, inicio);
				preparador.setDate(2, Fim);
				preparador.setString(3, grupo);
				preparador.setInt(4, orig);
				 ResultSet rs = preparador.executeQuery();
				 while(rs.next())
				 {
					 Exames pac = new Exames();
					 pac.setId_pronto_aserexaminado(rs.getInt("id_pronto_aserexaminado"));
					 pac.setFK_numero_do_servico_de_exame(rs.getInt("FK_requisicao_exame"));
					 pac.setFK_factura(rs.getInt("FK_factura"));
					 pac.setFK_paciente(rs.getInt("FK_paciente"));
					 pac.setFK_exame(rs.getInt("fk_exame"));
					 Calendar data = Calendar.getInstance();
	    		     data.setTime(rs.getDate("data_darecolha"));
	    		     pac.setData_darecolha(data);
					 pac.setFK_estatu_darecolha(rs.getInt("FK_estatu_darecolha"));
					 pac.setNumero_processo(rs.getString("NumeroProcesso"));
					 pac.setNomeCompleto(rs.getString("NomeCompleto"));
					 Calendar datan = Calendar.getInstance();
	    		     datan.setTime(rs.getDate("data_nascimento"));
	    		     pac.setData_nascimento(datan);
					 pac.setGenero(rs.getString("genero"));
					 pac.setQuantidade(rs.getInt("qtd_servicos"));
					 Calendar datat = Calendar.getInstance();
	    		     datat.setTime(rs.getDate("data_darecolha"));
	    		     pac.setData_doatendimento(datat);
	    		     pac.setAno(rs.getInt("idade"));
	    		     pac.setFK_grupo_analise_clinica(rs.getInt("FK_grupo_analise_clinica"));
					 lista.add(pac);
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
				}
			}
			return lista;
		}
	public List<Exames> buscarDataAndIdadeAtendidos(Date inicio, Date Fim, String idadeI, String idadeF,int orig){
	    List <Exames> lista = new ArrayList<Exames>();
			String sql = "SELECT * FROM vwpacientesatendidoslaboratorio WHERE data_darecolha between ? AND ? and idade between ? and ? and FK_provenienca_exame = ?";
			try {
				 con = Conexao.getConexao();
				PreparedStatement preparador = con.prepareStatement(sql);
				preparador.setDate(1, inicio);
				preparador.setDate(2, Fim);
				preparador.setString(3, idadeI);
				preparador.setString(4, idadeF);
				preparador.setInt(5, orig);
				 ResultSet rs = preparador.executeQuery();
				 while(rs.next())
				 {
					 Exames pac = new Exames();
					 pac.setId_pronto_aserexaminado(rs.getInt("id_pronto_aserexaminado"));
					 pac.setFK_numero_do_servico_de_exame(rs.getInt("FK_requisicao_exame"));
					 pac.setFK_factura(rs.getInt("FK_factura"));
					 pac.setFK_paciente(rs.getInt("FK_paciente"));
					 pac.setFK_exame(rs.getInt("fk_exame"));
					 Calendar data = Calendar.getInstance();
	    		     data.setTime(rs.getDate("data_darecolha"));
	    		     pac.setData_darecolha(data);
					 pac.setFK_estatu_darecolha(rs.getInt("FK_estatu_darecolha"));
					 pac.setNumero_processo(rs.getString("NumeroProcesso"));
					 pac.setNomeCompleto(rs.getString("NomeCompleto"));
					 Calendar datan = Calendar.getInstance();
	    		     datan.setTime(rs.getDate("data_nascimento"));
	    		     pac.setData_nascimento(datan);
					 pac.setGenero(rs.getString("genero"));
					 pac.setQuantidade(rs.getInt("qtd_servicos"));
					 Calendar datat = Calendar.getInstance();
	    		     datat.setTime(rs.getDate("data_darecolha"));
	    		     pac.setData_doatendimento(datat);
	    		     pac.setAno(rs.getInt("idade"));
	    		     pac.setFK_grupo_analise_clinica(rs.getInt("FK_grupo_analise_clinica"));
					 lista.add(pac);
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
				}
			}
			return lista;
		}
	public List<Exames> buscarDataAndIdade1Atendidos(Date inicio, Date Fim, String idadeI,int orig){
	    List <Exames> lista = new ArrayList<Exames>();
			String sql = "SELECT * FROM vwpacientesatendidoslaboratorio WHERE data_darecolha between ? AND ? and idade = ? and FK_provenienca_exame = ?";
			try {
				 con = Conexao.getConexao();
				PreparedStatement preparador = con.prepareStatement(sql);
				preparador.setDate(1, inicio);
				preparador.setDate(2, Fim);
				preparador.setString(3, idadeI);
				preparador.setInt(4, orig);
				 ResultSet rs = preparador.executeQuery();
				 while(rs.next())
				 {
					 Exames pac = new Exames();
					 pac.setId_pronto_aserexaminado(rs.getInt("id_pronto_aserexaminado"));
					 pac.setFK_numero_do_servico_de_exame(rs.getInt("FK_requisicao_exame"));
					 pac.setFK_factura(rs.getInt("FK_factura"));
					 pac.setFK_paciente(rs.getInt("FK_paciente"));
					 pac.setFK_exame(rs.getInt("fk_exame"));
					 Calendar data = Calendar.getInstance();
	    		     data.setTime(rs.getDate("data_darecolha"));
	    		     pac.setData_darecolha(data);
					 pac.setFK_estatu_darecolha(rs.getInt("FK_estatu_darecolha"));
					 pac.setNumero_processo(rs.getString("NumeroProcesso"));
					 pac.setNomeCompleto(rs.getString("NomeCompleto"));
					 Calendar datan = Calendar.getInstance();
	    		     datan.setTime(rs.getDate("data_nascimento"));
	    		     pac.setData_nascimento(datan);
					 pac.setGenero(rs.getString("genero"));
					 pac.setQuantidade(rs.getInt("qtd_servicos"));
					 Calendar datat = Calendar.getInstance();
	    		     datat.setTime(rs.getDate("data_darecolha"));
	    		     pac.setData_doatendimento(datat);
	    		     pac.setAno(rs.getInt("idade"));
	    		     pac.setFK_grupo_analise_clinica(rs.getInt("FK_grupo_analise_clinica"));
					 lista.add(pac);
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
				}
			}
			return lista;
		}
	public List<Exames> buscarDataAndGeneroAtendidos(Date inicio, Date Fim, String sexo,int orig){
	    List <Exames> lista = new ArrayList<Exames>();
			String sql = "SELECT * FROM vwpacientesatendidoslaboratorio WHERE data_darecolha between ? and ? and genero =? and FK_provenienca_exame = ?";
			try {
				 con = Conexao.getConexao();
				PreparedStatement preparador = con.prepareStatement(sql);
				preparador.setDate(1, inicio);
				preparador.setDate(2, Fim);
				preparador.setString(3, sexo);
				preparador.setInt(4, orig);
				 ResultSet rs = preparador.executeQuery();
				 while(rs.next())
				 {
					 Exames pac = new Exames();
					 pac.setId_pronto_aserexaminado(rs.getInt("id_pronto_aserexaminado"));
					 pac.setFK_numero_do_servico_de_exame(rs.getInt("FK_requisicao_exame"));
					 pac.setFK_factura(rs.getInt("FK_factura"));
					 pac.setFK_paciente(rs.getInt("FK_paciente"));
					 pac.setFK_exame(rs.getInt("fk_exame"));
					 Calendar data = Calendar.getInstance();
	    		     data.setTime(rs.getDate("data_darecolha"));
	    		     pac.setData_darecolha(data);
					 pac.setFK_estatu_darecolha(rs.getInt("FK_estatu_darecolha"));
					 pac.setNumero_processo(rs.getString("NumeroProcesso"));
					 pac.setNomeCompleto(rs.getString("NomeCompleto"));
					 Calendar datan = Calendar.getInstance();
	    		     datan.setTime(rs.getDate("data_nascimento"));
	    		     pac.setData_nascimento(datan);
					 pac.setGenero(rs.getString("genero"));
					 pac.setQuantidade(rs.getInt("qtd_servicos"));
					 Calendar datat = Calendar.getInstance();
	    		     datat.setTime(rs.getDate("data_darecolha"));
	    		     pac.setData_doatendimento(datat);
	    		     pac.setAno(rs.getInt("idade"));
	    		     pac.setFK_grupo_analise_clinica(rs.getInt("FK_grupo_analise_clinica"));
					 lista.add(pac);
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
				}
			}
			return lista;
		}
	public List<Exames> buscarDataAndExameAtendidos(Date inicio, Date Fim, String exame,int orig){
	    List <Exames> lista = new ArrayList<Exames>();
			String sql = "SELECT * FROM vwpacientesatendidoslaboratorio WHERE data_darecolha between ? AND ? and fk_exame =? and FK_provenienca_exame = ?";
			try {
				 con = Conexao.getConexao();
				PreparedStatement preparador = con.prepareStatement(sql);
				preparador.setDate(1, inicio);
				preparador.setDate(2, Fim);
				preparador.setString(3, exame);
				preparador.setInt(4, orig);
				 ResultSet rs = preparador.executeQuery();
				 while(rs.next())
				 {
					 Exames pac = new Exames();
					 pac.setId_pronto_aserexaminado(rs.getInt("id_pronto_aserexaminado"));
					 pac.setFK_numero_do_servico_de_exame(rs.getInt("FK_requisicao_exame"));
					 pac.setFK_factura(rs.getInt("FK_factura"));
					 pac.setFK_paciente(rs.getInt("FK_paciente"));
					 pac.setFK_exame(rs.getInt("fk_exame"));
					 Calendar data = Calendar.getInstance();
	    		     data.setTime(rs.getDate("data_darecolha"));
	    		     pac.setData_darecolha(data);
					 pac.setFK_estatu_darecolha(rs.getInt("FK_estatu_darecolha"));
					 pac.setNumero_processo(rs.getString("NumeroProcesso"));
					 pac.setNomeCompleto(rs.getString("NomeCompleto"));
					 Calendar datan = Calendar.getInstance();
	    		     datan.setTime(rs.getDate("data_nascimento"));
	    		     pac.setData_nascimento(datan);
					 pac.setGenero(rs.getString("genero"));
					 pac.setQuantidade(rs.getInt("qtd_servicos"));
					 Calendar datat = Calendar.getInstance();
	    		     datat.setTime(rs.getDate("data_darecolha"));
	    		     pac.setData_doatendimento(datat);
	    		     pac.setAno(rs.getInt("idade"));
	    		     pac.setFK_grupo_analise_clinica(rs.getInt("FK_grupo_analise_clinica"));
					 lista.add(pac);
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
				}
			}
			return lista;
		}
	public List<Exames> buscarDataAndGrupoAndIdadeAtendidos(Date inicio, Date Fim, String grupo, String idadeI, String idadeF,int orig){
	    List <Exames> lista = new ArrayList<Exames>();
			String sql = "SELECT * FROM vwpacientesatendidoslaboratorio WHERE data_darecolha between ? AND ? "
					+ "AND FK_grupo_analise_clinica =? AND idade between ? and ? and FK_provenienca_exame = ?";
			try {
				 con = Conexao.getConexao();
				PreparedStatement preparador = con.prepareStatement(sql);
				preparador.setDate(1, inicio);
				preparador.setDate(2, Fim);
				preparador.setString(3, grupo);
				preparador.setString(4, idadeI);
				preparador.setString(5, idadeF);
				preparador.setInt(6, orig);
				 ResultSet rs = preparador.executeQuery();
				 while(rs.next())
				 {
					 Exames pac = new Exames();
					 pac.setId_pronto_aserexaminado(rs.getInt("id_pronto_aserexaminado"));
					 pac.setFK_numero_do_servico_de_exame(rs.getInt("FK_requisicao_exame"));
					 pac.setFK_factura(rs.getInt("FK_factura"));
					 pac.setFK_paciente(rs.getInt("FK_paciente"));
					 pac.setFK_exame(rs.getInt("fk_exame"));
					 Calendar data = Calendar.getInstance();
	    		     data.setTime(rs.getDate("data_darecolha"));
	    		     pac.setData_darecolha(data);
					 pac.setFK_estatu_darecolha(rs.getInt("FK_estatu_darecolha"));
					 pac.setNumero_processo(rs.getString("NumeroProcesso"));
					 pac.setNomeCompleto(rs.getString("NomeCompleto"));
					 Calendar datan = Calendar.getInstance();
	    		     datan.setTime(rs.getDate("data_nascimento"));
	    		     pac.setData_nascimento(datan);
					 pac.setGenero(rs.getString("genero"));
					 pac.setQuantidade(rs.getInt("qtd_servicos"));
					 Calendar datat = Calendar.getInstance();
	    		     datat.setTime(rs.getDate("data_darecolha"));
	    		     pac.setData_doatendimento(datat);
	    		     pac.setAno(rs.getInt("idade"));
	    		     pac.setFK_grupo_analise_clinica(rs.getInt("FK_grupo_analise_clinica"));
					 lista.add(pac);
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
				}
			}
			return lista;
		}
	public List<Exames> buscarDataAndGrupoAndGeneroAtendidos(Date inicio, Date Fim, String grupo, String sexo,int orig){
	    List <Exames> lista = new ArrayList<Exames>();
			String sql = "SELECT * FROM vwpacientesatendidoslaboratorio WHERE data_darecolha between ? AND ? AND FK_grupo_analise_clinica =? AND genero =? and FK_provenienca_exame = ?";
			try {
				 con = Conexao.getConexao();
				PreparedStatement preparador = con.prepareStatement(sql);
				preparador.setDate(1, inicio);
				preparador.setDate(2, Fim);
				preparador.setString(3, grupo);
				preparador.setString(4, sexo);
				preparador.setInt(5, orig);
				 ResultSet rs = preparador.executeQuery();
				 while(rs.next())
				 {
					 Exames pac = new Exames();
					 pac.setId_pronto_aserexaminado(rs.getInt("id_pronto_aserexaminado"));
					 pac.setFK_numero_do_servico_de_exame(rs.getInt("FK_requisicao_exame"));
					 pac.setFK_factura(rs.getInt("FK_factura"));
					 pac.setFK_paciente(rs.getInt("FK_paciente"));
					 pac.setFK_exame(rs.getInt("fk_exame"));
					 Calendar data = Calendar.getInstance();
	    		     data.setTime(rs.getDate("data_darecolha"));
	    		     pac.setData_darecolha(data);
					 pac.setFK_estatu_darecolha(rs.getInt("FK_estatu_darecolha"));
					 pac.setNumero_processo(rs.getString("NumeroProcesso"));
					 pac.setNomeCompleto(rs.getString("NomeCompleto"));
					 Calendar datan = Calendar.getInstance();
	    		     datan.setTime(rs.getDate("data_nascimento"));
	    		     pac.setData_nascimento(datan);
					 pac.setGenero(rs.getString("genero"));
					 pac.setQuantidade(rs.getInt("qtd_servicos"));
					 Calendar datat = Calendar.getInstance();
	    		     datat.setTime(rs.getDate("data_darecolha"));
	    		     pac.setData_doatendimento(datat);
	    		     pac.setAno(rs.getInt("idade"));
	    		     pac.setFK_grupo_analise_clinica(rs.getInt("FK_grupo_analise_clinica"));
					 lista.add(pac);
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
				}
			}
			return lista;
		}
	public List<Exames> buscarDataAndGrupoAndExameAtendidos(Date inicio, Date Fim, String grupo, String exame,int orig){
	    List <Exames> lista = new ArrayList<Exames>();
			String sql = "SELECT * FROM vwpacientesatendidoslaboratorio WHERE data_darecolha between ? AND ? AND FK_grupo_analise_clinica =? AND fk_exame =? and FK_provenienca_exame = ?";
			try {
				 con = Conexao.getConexao();
				PreparedStatement preparador = con.prepareStatement(sql);
				preparador.setDate(1, inicio);
				preparador.setDate(2, Fim);
				preparador.setString(3, grupo);
				preparador.setString(4, exame);
				preparador.setInt(5, orig);
				 ResultSet rs = preparador.executeQuery();
				 while(rs.next())
				 {
					 Exames pac = new Exames();
					 pac.setId_pronto_aserexaminado(rs.getInt("id_pronto_aserexaminado"));
					 pac.setFK_numero_do_servico_de_exame(rs.getInt("FK_requisicao_exame"));
					 pac.setFK_factura(rs.getInt("FK_factura"));
					 pac.setFK_paciente(rs.getInt("FK_paciente"));
					 pac.setFK_exame(rs.getInt("fk_exame"));
					 Calendar data = Calendar.getInstance();
	    		     data.setTime(rs.getDate("data_darecolha"));
	    		     pac.setData_darecolha(data);
					 pac.setFK_estatu_darecolha(rs.getInt("FK_estatu_darecolha"));
					 pac.setNumero_processo(rs.getString("NumeroProcesso"));
					 pac.setNomeCompleto(rs.getString("NomeCompleto"));
					 Calendar datan = Calendar.getInstance();
	    		     datan.setTime(rs.getDate("data_nascimento"));
	    		     pac.setData_nascimento(datan);
					 pac.setGenero(rs.getString("genero"));
					 pac.setQuantidade(rs.getInt("qtd_servicos"));
					 Calendar datat = Calendar.getInstance();
	    		     datat.setTime(rs.getDate("data_darecolha"));
	    		     pac.setData_doatendimento(datat);
	    		     pac.setAno(rs.getInt("idade"));
	    		     pac.setFK_grupo_analise_clinica(rs.getInt("FK_grupo_analise_clinica"));
					 lista.add(pac);
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
				}
			}
			return lista;
		}
	public List<Exames> buscarDataAndIdadeAndGeneroAtendidos(Date inicio, Date Fim, String idadeI, String idadeF, String sexo,int orig){
	    List <Exames> lista = new ArrayList<Exames>();
			String sql = "SELECT * FROM vwpacientesatendidoslaboratorio WHERE data_darecolha between ? AND ? and idade between ? and ? and genero =? and FK_provenienca_exame = ?";
			try {
				 con = Conexao.getConexao();
				PreparedStatement preparador = con.prepareStatement(sql);
				preparador.setDate(1, inicio);
				preparador.setDate(2, Fim);
				preparador.setString(3, idadeI);
				preparador.setString(4, idadeF);
				preparador.setString(5, sexo);
				preparador.setInt(6, orig);
				 ResultSet rs = preparador.executeQuery();
				 while(rs.next())
				 {
					 Exames pac = new Exames();
					 pac.setId_pronto_aserexaminado(rs.getInt("id_pronto_aserexaminado"));
					 pac.setFK_numero_do_servico_de_exame(rs.getInt("FK_requisicao_exame"));
					 pac.setFK_factura(rs.getInt("FK_factura"));
					 pac.setFK_paciente(rs.getInt("FK_paciente"));
					 pac.setFK_exame(rs.getInt("fk_exame"));
					 Calendar data = Calendar.getInstance();
	    		     data.setTime(rs.getDate("data_darecolha"));
	    		     pac.setData_darecolha(data);
					 pac.setFK_estatu_darecolha(rs.getInt("FK_estatu_darecolha"));
					 pac.setNumero_processo(rs.getString("NumeroProcesso"));
					 pac.setNomeCompleto(rs.getString("NomeCompleto"));
					 Calendar datan = Calendar.getInstance();
	    		     datan.setTime(rs.getDate("data_nascimento"));
	    		     pac.setData_nascimento(datan);
					 pac.setGenero(rs.getString("genero"));
					 pac.setQuantidade(rs.getInt("qtd_servicos"));
					 Calendar datat = Calendar.getInstance();
	    		     datat.setTime(rs.getDate("data_darecolha"));
	    		     pac.setData_doatendimento(datat);
	    		     pac.setAno(rs.getInt("idade"));
	    		     pac.setFK_grupo_analise_clinica(rs.getInt("FK_grupo_analise_clinica"));
					 lista.add(pac);
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
				}
			}
			return lista;
		}
	public List<Exames> buscarDataAndIdade1AndGeneroAtendidos(Date inicio, Date Fim, String idadeI, String sexo,int orig){
	    List <Exames> lista = new ArrayList<Exames>();
			String sql = "SELECT * FROM vwpacientesatendidoslaboratorio WHERE data_darecolha between ? AND ? and idade = ? and genero =? and FK_provenienca_exame = ?";
			try {
				 con = Conexao.getConexao();
				PreparedStatement preparador = con.prepareStatement(sql);
				preparador.setDate(1, inicio);
				preparador.setDate(2, Fim);
				preparador.setString(3, idadeI);
				preparador.setString(4, sexo);
				preparador.setInt(5, orig);
				 ResultSet rs = preparador.executeQuery();
				 while(rs.next())
				 {
					 Exames pac = new Exames();
					 pac.setId_pronto_aserexaminado(rs.getInt("id_pronto_aserexaminado"));
					 pac.setFK_numero_do_servico_de_exame(rs.getInt("FK_requisicao_exame"));
					 pac.setFK_factura(rs.getInt("FK_factura"));
					 pac.setFK_paciente(rs.getInt("FK_paciente"));
					 pac.setFK_exame(rs.getInt("fk_exame"));
					 Calendar data = Calendar.getInstance();
	    		     data.setTime(rs.getDate("data_darecolha"));
	    		     pac.setData_darecolha(data);
					 pac.setFK_estatu_darecolha(rs.getInt("FK_estatu_darecolha"));
					 pac.setNumero_processo(rs.getString("NumeroProcesso"));
					 pac.setNomeCompleto(rs.getString("NomeCompleto"));
					 Calendar datan = Calendar.getInstance();
	    		     datan.setTime(rs.getDate("data_nascimento"));
	    		     pac.setData_nascimento(datan);
					 pac.setGenero(rs.getString("genero"));
					 pac.setQuantidade(rs.getInt("qtd_servicos"));
					 Calendar datat = Calendar.getInstance();
	    		     datat.setTime(rs.getDate("data_darecolha"));
	    		     pac.setData_doatendimento(datat);
	    		     pac.setAno(rs.getInt("idade"));
	    		     pac.setFK_grupo_analise_clinica(rs.getInt("FK_grupo_analise_clinica"));
					 lista.add(pac);
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
				}
			}
			return lista;
		}
	public List<Exames> buscarDataAndIdadeAndExameAtendidos(Date inicio, Date Fim, String idadeI, String idadeF, String exame){
	    List <Exames> lista = new ArrayList<Exames>();
			String sql = "SELECT * FROM vwpacientesatendidoslaboratorio WHERE data_darecolha between ? AND ? and idade between ? and ? and fk_exame =?";
			try {
				 con = Conexao.getConexao();
				PreparedStatement preparador = con.prepareStatement(sql);
				preparador.setDate(1, inicio);
				preparador.setDate(2, Fim);
				preparador.setString(3, idadeI);
				preparador.setString(4, idadeF);
				preparador.setString(5, exame);
				 ResultSet rs = preparador.executeQuery();
				 while(rs.next())
				 {
					 Exames pac = new Exames();
					 pac.setId_pronto_aserexaminado(rs.getInt("id_pronto_aserexaminado"));
					 pac.setFK_numero_do_servico_de_exame(rs.getInt("FK_requisicao_exame"));
					 pac.setFK_factura(rs.getInt("FK_factura"));
					 pac.setFK_paciente(rs.getInt("FK_paciente"));
					 pac.setFK_exame(rs.getInt("fk_exame"));
					 Calendar data = Calendar.getInstance();
	    		     data.setTime(rs.getDate("data_darecolha"));
	    		     pac.setData_darecolha(data);
					 pac.setFK_estatu_darecolha(rs.getInt("FK_estatu_darecolha"));
					 pac.setNumero_processo(rs.getString("NumeroProcesso"));
					 pac.setNomeCompleto(rs.getString("NomeCompleto"));
					 Calendar datan = Calendar.getInstance();
	    		     datan.setTime(rs.getDate("data_nascimento"));
	    		     pac.setData_nascimento(datan);
					 pac.setGenero(rs.getString("genero"));
					 pac.setQuantidade(rs.getInt("qtd_servicos"));
					 Calendar datat = Calendar.getInstance();
	    		     datat.setTime(rs.getDate("data_darecolha"));
	    		     pac.setData_doatendimento(datat);
	    		     pac.setAno(rs.getInt("idade"));
	    		     pac.setFK_grupo_analise_clinica(rs.getInt("FK_grupo_analise_clinica"));
					 lista.add(pac);
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
				}
			}
			return lista;
		}
	public List<Exames> buscarDataAndGeneroAndExameAtendidos(Date inicio, Date Fim, String sexo, String exame,int orig){
	    List <Exames> lista = new ArrayList<Exames>();
			String sql = "SELECT * FROM vwpacientesatendidoslaboratorio WHERE data_darecolha between ? and ? and genero =? and fk_exame=? and FK_provenienca_exame = ?";
			try {
				 con = Conexao.getConexao();
				PreparedStatement preparador = con.prepareStatement(sql);
				preparador.setDate(1, inicio);
				preparador.setDate(2, Fim);
				preparador.setString(3, sexo);
				preparador.setString(4, exame);
				preparador.setInt(5, orig);
				 ResultSet rs = preparador.executeQuery();
				 while(rs.next())
				 {
					 Exames pac = new Exames();
					 pac.setId_pronto_aserexaminado(rs.getInt("id_pronto_aserexaminado"));
					 pac.setFK_numero_do_servico_de_exame(rs.getInt("FK_requisicao_exame"));
					 pac.setFK_factura(rs.getInt("FK_factura"));
					 pac.setFK_paciente(rs.getInt("FK_paciente"));
					 pac.setFK_exame(rs.getInt("fk_exame"));
					 Calendar data = Calendar.getInstance();
	    		     data.setTime(rs.getDate("data_darecolha"));
	    		     pac.setData_darecolha(data);
					 pac.setFK_estatu_darecolha(rs.getInt("FK_estatu_darecolha"));
					 pac.setNumero_processo(rs.getString("NumeroProcesso"));
					 pac.setNomeCompleto(rs.getString("NomeCompleto"));
					 Calendar datan = Calendar.getInstance();
	    		     datan.setTime(rs.getDate("data_nascimento"));
	    		     pac.setData_nascimento(datan);
					 pac.setGenero(rs.getString("genero"));
					 pac.setQuantidade(rs.getInt("qtd_servicos"));
					 Calendar datat = Calendar.getInstance();
	    		     datat.setTime(rs.getDate("data_darecolha"));
	    		     pac.setData_doatendimento(datat);
	    		     pac.setAno(rs.getInt("idade"));
	    		     pac.setFK_grupo_analise_clinica(rs.getInt("FK_grupo_analise_clinica"));
					 lista.add(pac);
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
				}
			}
			return lista;
		}
	
	public int xeckBarcode(int codF,int code){
		String sql = "Select * from tblamostra where FK_requisicao_exame = ? and fk_exame = ?";
		int codBar = 0;
		try {
			con = Conexao.getConexao();
			PreparedStatement ps = con.prepareStatement(sql);
			ps.setInt(1, codF);
			ps.setInt(2, code);
			ResultSet rs = ps.executeQuery();
			if(rs.next())
				codBar = rs.getInt("id_amostra");
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return codBar;
	}
	
	public int novoBarCode(int codF,int code,int fun){
		int ultimoCod = 0;
		String sql = "Insert into tblamostra (fk_exame,FK_requisicao_exame,FK_funcionario,codigo_barra_exame) value(?,?,?,?) ";
		try {
			con = Conexao.getConexao();
			PreparedStatement ps = con.prepareStatement(sql);
			ps.setInt(1, code);
			ps.setInt(2, codF);
			ps.setInt(3, fun);
			ps.setInt(4, gerador.nextInt(23111115));
			ps.execute();
			ResultSet rs = ps.executeQuery("SELECT LAST_INSERT_ID()");
			 if(rs.next())
				ultimoCod = rs.getInt(1);
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		return ultimoCod;
	}
	
	public void atualizaBarCode(int codF,int code,int fun){
		 
		String sql = "Update tblamostra set codigo_barra_exame = ?, FK_funcionario = ? where fk_exame= ? and FK_requisicao_exame = ? ";
		try {
			con = Conexao.getConexao();
			PreparedStatement ps = con.prepareStatement(sql);
			ps.setInt(1, gerador.nextInt(23111115));
			ps.setInt(2, fun);
			ps.setInt(3, code);
			ps.setInt(4, codF);
			ps.execute();
						
		} catch (Exception e) {
			e.printStackTrace();
		}
		 
	}
	
//	---------------------- Cadastrar os dados na tela "Embalagem" Cussunga
	
	public void addLaboratorio(Laboratorio pd)
	{
		String sql = "INSERT INTO tbllaboratorio (laboratorio) VALUES (?)";
		try {
			con = Conexao.getConexao();
			PreparedStatement cp = con.prepareStatement(sql);
			cp.setString(1, pd.getLaboratorio());
			cp.execute();
			cp.close(); 
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
	
//	2-Visualizar produtos
	public List <Laboratorio> buscarLaboratorio()
	{
		List <Laboratorio> lista = new ArrayList<Laboratorio>();
		String sql = "SELECT * FROM tbllaboratorio where id_laboratorio != 1 order by laboratorio asc";
		try {
			con = Conexao.getConexao();
			 PreparedStatement preparador = con.prepareStatement(sql);
			 ResultSet rs = preparador.executeQuery();
			 while(rs.next())
			 {
				 Laboratorio pd = new Laboratorio();
				 pd.setId_laboratorio(rs.getInt("id_laboratorio"));
				 pd.setLaboratorio(rs.getString("laboratorio"));
				 lista.add(pd);
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


//	  6-Editar servi�o
	public Laboratorio edit_laboratorio(String ed)
	{
		Laboratorio bg = new Laboratorio();
		String sql = "SELECT * FROM tbllaboratorio WHERE id_laboratorio = ?";
		try {
			con = Conexao.getConexao();
			PreparedStatement preparador = con.prepareStatement(sql);
			 preparador.setString(1, ed);
			 ResultSet rs = preparador.executeQuery();
			 while(rs.next())
			 {
				 bg.setId_laboratorio(rs.getInt("id_laboratorio"));
				 bg.setLaboratorio(rs.getString("laboratorio"));				 
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
		return bg;
	}
	
//	5- Alterar servi�o
	public void alt_laboratorio(Laboratorio gb)
	{
		String sql = "UPDATE tbllaboratorio SET laboratorio =? WHERE id_laboratorio = ?";
		try {
			con = Conexao.getConexao();
			PreparedStatement bg = con.prepareStatement(sql);
			
			bg.setString(1, gb.getLaboratorio());
			bg.setInt(2, gb.getId_laboratorio());
			bg.execute();
			bg.close();
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
	public int XequeLaboratorio(String termo)
	{
		int ok = 0;
		String sql = "SELECT * FROM tbllaboratorio WHERE laboratorio Like ?";
		try {
			con = Conexao.getConexao();
			PreparedStatement preparador = con.prepareStatement(sql);
			 preparador.setString(1, termo);
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
	//3-Eliminar Servi�o
			public void del_laboratorio(int id)
			{
				String sql = "DELETE FROM tbllaboratorio  WHERE id_laboratorio=?";
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
}
