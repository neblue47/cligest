package ao.co.cligest.dao;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

import ao.co.cligest.entidades.Exames;
import ao.co.cligest.entidades.Facturacao;
import ao.co.cligest.entidades.Paciente;

public class HistVendaDAO {

	private Connection con;
	Formatando ft = new Formatando();
	
	public List <Paciente> buscarhistVendas()
    {
			List <Paciente> lista = new ArrayList<Paciente>();
			String sql = "SELECT * FROM vwhistoricodevendasexames order by Data_da_facturacao desc, hora_da_facturacao desc limit 500";
			try {
				con = Conexao.getConexao();
				PreparedStatement preparador = con.prepareStatement(sql);
				ResultSet rs = preparador.executeQuery();
				while (rs.next())
				 {
					 Paciente fun = new Paciente();
					 fun.setNomeCompleto(rs.getString("NomeCompleto"));
					 Calendar data = Calendar.getInstance();
					 data.setTime(rs.getDate("data_da_facturacao"));
				     fun.setData_registo(data);
				     fun.setData(rs.getDate("data_da_facturacao"));
				     fun.setHora_registo(rs.getString("hora_da_facturacao"));
				     fun.setNomeFun(rs.getString("n_c_funcionario"));
				     fun.setQuantidade(rs.getInt("qtd"));
				     fun.setTotal_s(rs.getString("total"));
				     fun.setNumero_ordem(rs.getString("numero_factura"));
				     fun.setId(rs.getInt("FK_factura"));
				     fun.setNumero_processo(rs.getString("NumeroProceso"));
				     fun.setMetodo_dpagamento(rs.getString("metodo_depagameto"));
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
	
	public List <Paciente> buscarHistPacienteVacina()
    {
			List <Paciente> lista = new ArrayList<Paciente>();
			String sql = "SELECT * FROM vwhistorico_vacinacao group by fk_paciente ORDER BY data_vacina ASC";
			try {
				con = Conexao.getConexao();
				PreparedStatement preparador = con.prepareStatement(sql);
				ResultSet rs = preparador.executeQuery();
				while (rs.next())
				 {
					 Paciente fun = new Paciente();
					 fun.setNomeCompleto(rs.getString("NomeCompleto"));
					 
					 fun.setData_em_string(rs.getString("data_vacina"));;
					 
//					 Calendar data = Calendar.getInstance();
//					 data.setTime(rs.getDate("data_vacina"));
//					 fun.setData_registo(data);
					 
					 fun.setNumero_processo(rs.getString("NumeroProcesso"));
					 fun.setServico(rs.getString("servico"));
					 fun.setFK_paciente(rs.getInt("fk_paciente"));
					 fun.setDias_nascimento(rs.getInt("dia"));
					 fun.setAno_nascimento(rs.getInt("ano"));
					 fun.setMes_nascimento(rs.getInt("mes"));
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
	
	public List <Paciente> buscarPacienteDevem()
    {
			List <Paciente> lista = new ArrayList<Paciente>();
			String sql = "SELECT * FROM vwpacientes_apanharam_certa_vacina ";
			try {
				con = Conexao.getConexao();
				PreparedStatement preparador = con.prepareStatement(sql);
				ResultSet rs = preparador.executeQuery();
				while (rs.next())
				 {
					 Paciente fun = new Paciente();
					 fun.setNomeCompleto(rs.getString("NomeCompleto"));
					 Calendar data = Calendar.getInstance();
					 data.setTime(rs.getDate("data_vacina"));
					 fun.setData_registo(data);
					 fun.setNumero_processo(rs.getString("NumeroProcesso"));
					 fun.setFK_consulta(rs.getInt("fk_medicamento"));
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
	
	public List <Paciente> buscarVacinasParaPaciente(int codigo)
    {
			List <Paciente> lista = new ArrayList<Paciente>();
			String sql = "select `ps`.`NomeCompleto` AS `NomeCompleto`,`fs`.`n_c_funcionario` AS `n_c_funcionario`,"
					+ "`ve`.`fk_paciente` AS `fk_paciente`,`ve`.`fk_medicamento` AS `fk_medicamento`,"
					+ "`ve`.`FK_factura` AS `FK_factura`, date_format(`ve`.`data_vacina`,'%d/%m/%Y') AS `data_vacina`,count(`ve`.`fk_paciente`) AS `veses_apanhada`,floor(((date_format(now(),'%Y%m%d') - date_format(`ps`.`data_nascimento`,'%Y%m%d')) / 10000)) AS `ano`,(floor((((1200 + date_format(now(),'%m%d')) - date_format(`ps`.`data_nascimento`,'%m%d')) / 100)) % 12) AS `mes`,(((((sign((dayofmonth(now()) - dayofmonth(`ps`.`data_nascimento`))) + 1) / 2) * (dayofmonth(now()) - dayofmonth(`ps`.`data_nascimento`))) + (((sign((dayofmonth(now()) - dayofmonth(`ps`.`data_nascimento`))) + 1) / 2) * (dayofmonth(now()) - dayofmonth(`ps`.`data_nascimento`)))) + (((sign((dayofmonth(`ps`.`data_nascimento`) - dayofmonth(now()))) + 1) / 2) * ((dayofmonth((str_to_date(date_format((`ps`.`data_nascimento` + interval 1 month),'%Y-%m-01'),'%Y-%m-%d') - interval 1 day)) - dayofmonth(`ps`.`data_nascimento`)) + dayofmonth(now())))) AS `dia`"
					+ " from ((`tblvacinas_efectuadas` `ve`  "
					+ "join `vwpacientesimples` `ps` on((`ps`.`Entidade` = `ve`.`fk_paciente`))) "
					+ "join `vwfuncionariosimples` `fs` on((`fs`.`FK_entidade` = `ve`.`fk_funcionario`))) "
					+ "WHERE fk_medicamento = ? group by `ve`.`fk_paciente`";
			try {
				con = Conexao.getConexao();
				PreparedStatement preparador = con.prepareStatement(sql);
				preparador.setInt(1, codigo);
				ResultSet rs = preparador.executeQuery();
				while (rs.next())
				 {
					 Paciente fun = new Paciente();
					 fun.setNomeCompleto(rs.getString("NomeCompleto"));
					 fun.setAno_nascimento(rs.getInt("dia"));
					 fun.setMes_nascimento(rs.getInt("mes"));
					 fun.setAno_nascimento(rs.getInt("ano"));
					 fun.setData_em_string(rs.getString("data_vacina"));
					 fun.setNome_doutor(rs.getString("n_c_funcionario"));
					 fun.setFK_consulta(rs.getInt("fk_medicamento"));
					 fun.setFK_paciente(rs.getInt("fk_paciente"));
					 fun.setQuantidade(rs.getInt("veses_apanhada"));
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
	
	public List <Paciente> buscarVacinaTomadaPeloPacienteModal(int codigo, int medi)
    {
			List <Paciente> lista = new ArrayList<Paciente>();
			String sql = "SELECT * FROM vwbuscar_vacina_tomada_pelo_paciente_modal WHERE fk_paciente =? AND  fk_medicamento = ?";
			try {
				con = Conexao.getConexao();
				PreparedStatement preparador = con.prepareStatement(sql);
				preparador.setInt(1, codigo);
				preparador.setInt(2, medi);
				ResultSet rs = preparador.executeQuery();
				while (rs.next())
				 {
					 Paciente fun = new Paciente();
					 fun.setNomeCompleto(rs.getString("NomeCompleto"));
					 fun.setData_em_string(rs.getString("data_vacina"));
					 fun.setLote_vacina(rs.getString("lote"));
					 fun.setNome_doutor(rs.getString("n_c_funcionario"));
					 fun.setFK_consulta(rs.getInt("fk_medicamento"));
					 fun.setFK_paciente(rs.getInt("fk_paciente"));
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
	
	public List <Paciente> buscarVacinasParaPaciente(String codigo)
    {
			List <Paciente> lista = new ArrayList<Paciente>();
			String sql = "SELECT * FROM vwpacientes_apanharam_certa_vacina WHERE NomeCompleto LIKE ? ";
			try {
				con = Conexao.getConexao();
				PreparedStatement preparador = con.prepareStatement(sql);
				preparador.setString(1, codigo+"%");
				ResultSet rs = preparador.executeQuery();
				while (rs.next())
				 {
					 Paciente fun = new Paciente();
					 fun.setNomeCompleto(rs.getString("NomeCompleto"));
					 Calendar data = Calendar.getInstance();
					 data.setTime(rs.getDate("data_vacina"));
					 fun.setData_registo(data);
					 fun.setNome_doutor(rs.getString("n_c_funcionario"));
					 fun.setFK_consulta(rs.getInt("fk_medicamento"));
					 fun.setFK_paciente(rs.getInt("fk_paciente"));
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
	
	public List <Paciente> buscarHistPacienteVacina(String codp)
    {
		
		
		List <Paciente> lista = new ArrayList<Paciente>();
			String sql = "SELECT * FROM vwhistorico_vacinacao where fk_paciente = ? ";
			try {
				
				con = Conexao.getConexao();
				PreparedStatement preparador = con.prepareStatement(sql);
				preparador.setString(1, codp);
				ResultSet rs = preparador.executeQuery();
				while (rs.next())
				{	
					 Paciente fun = new Paciente();
					 fun.setNomeCompleto(rs.getString("NomeCompleto"));
					 fun.setData_em_string(rs.getString("data_vacina"));
					 fun.setServico(rs.getString("servico"));
					 System.out.println(" nova chave e   " + rs.getString("servico"));
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
	
	public List <Paciente> buscarPaciennteVacinados(int numero)
    {
			List <Paciente> lista = new ArrayList<Paciente>();
			String sql = "SELECT * FROM vwvacinas_apanhadas_porpaciente where fk_paciente = ?";
			try {
				con = Conexao.getConexao();
				PreparedStatement preparador = con.prepareStatement(sql);
				preparador.setInt(1, numero);
				System.out.println(numero);
				ResultSet rs = preparador.executeQuery();
				while (rs.next())
				 {
					 Paciente fun = new Paciente();
					 fun.setNomeCompleto(rs.getString("n_c_funcionario"));
					 fun.setData_em_string(rs.getString("data_vacina"));
					 fun.setNome_doutor(rs.getString("n_c_funcionario"));
					 fun.setNomeDoc(rs.getString("lote"));
					 fun.setServico(rs.getString("servico"));
					 fun.setFK_paciente(rs.getInt("fk_paciente"));
					 fun.setId(rs.getInt("fk_factura"));
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
	
	public List <Paciente> buscarPaciennteVacinadoss(int numero)
    {
			List <Paciente> lista = new ArrayList<Paciente>();
			String sql = "SELECT * FROM vwvacinas_apanhadas_porpaciente where fk_medicamento = ?";
			try {
				con = Conexao.getConexao();
				PreparedStatement preparador = con.prepareStatement(sql);
				preparador.setInt(1, numero);
				ResultSet rs = preparador.executeQuery();
				while (rs.next())
				 {
					 Paciente fun = new Paciente();
					 fun.setNomeCompleto(rs.getString("n_c_funcionario"));
					 fun.setData_em_string(rs.getString("data_vacina"));
					 fun.setNome_doutor(rs.getString("n_c_funcionario"));
					 fun.setNomeDoc(rs.getString("lote"));
					 fun.setServico(rs.getString("servico"));
					 fun.setFK_paciente(rs.getInt("fk_paciente"));
					 fun.setId(rs.getInt("fk_factura"));
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
	
	public List <Paciente> buscarPaciennteVacinados()
    {
			List <Paciente> lista = new ArrayList<Paciente>();
			String sql = "select `svc`.`servico` AS `servico`, `ve`.`id_vacina_efectuada` AS `id_vacina_efectuada`, `ve`.`fk_medicamento` AS `fk_medicamento`, `ve`.`fk_paciente` AS `fk_paciente`, count(`ve`.`fk_paciente`) AS `quant_atendimento` from `tblvacinas_efectuadas` `ve` join `tblservico` `svc` on `svc`.`id_servico` = `ve`.`fk_medicamento` group by `svc`.`servico` ASC ";
			try {
				con = Conexao.getConexao();
				PreparedStatement preparador = con.prepareStatement(sql);
				ResultSet rs = preparador.executeQuery();
				while (rs.next())
				 {
					 Paciente fun = new Paciente();

					 fun.setNomeDoc(rs.getString("quant_atendimento"));
					 fun.setServico(rs.getString("servico"));
					 fun.setFK_paciente(rs.getInt("fk_medicamento"));
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
	
	public List <Paciente> buscarPacienteVacinados()
    {
			List <Paciente> lista = new ArrayList<Paciente>();
			String sql = "SELECT * FROM vwpacientes_a_serem_vacinados where data_da_facturacao = curdate() and FK_categoria = 3 order by hora_da_facturacao desc";
			try {
				con = Conexao.getConexao();
				PreparedStatement preparador = con.prepareStatement(sql);
				ResultSet rs = preparador.executeQuery();
				while (rs.next())
				 {
					 Paciente fun = new Paciente();
					 fun.setNomeCompleto(rs.getString("NomeCompleto"));
					 Calendar data = Calendar.getInstance();
					 data.setTime(rs.getDate("data_da_facturacao"));
					 fun.setData_registo(data);
				     fun.setHora_registo(rs.getString("hora_da_facturacao"));
				     fun.setNomeFun(rs.getString("n_c_funcionario"));
//				     fun.setQuantidade(rs.getInt("qtd"));
				     fun.setTotal_s(rs.getString("total"));
				     fun.setNumero_ordem(rs.getString("numero_factura"));
				     fun.setId(rs.getInt("FK_factura"));
				     fun.setNumero_processo(rs.getString("NumeroProceso"));
				     fun.setMetodo_dpagamento(rs.getString("metodo_depagameto"));
					 fun.setServico(rs.getString("servico"));
					 fun.setId_servico(rs.getInt("id_servico"));
					 
//					 Calendar data = Calendar.getInstance();
//					 data.setTime(rs.getDate("data_nascimento"));
//					 fun.setData_registo(data);
//					 fun.setIdade(rs.getDate("data_nascimento"));
					 
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
	
	public List <Paciente> buscarPacienteVacinadoss()
    {
			List <Paciente> lista = new ArrayList<Paciente>();
			String sql = "SELECT * FROM vwlistar_pacientes_por_vacinar where data_da_facturacao = curdate()";
			try {
				con = Conexao.getConexao();
				PreparedStatement preparador = con.prepareStatement(sql);
				ResultSet rs = preparador.executeQuery();
				while (rs.next())
				 {
					 Paciente fun = new Paciente();
					 fun.setNomeCompleto(rs.getString("NomeCompleto"));
					 Calendar data = Calendar.getInstance();
					 data.setTime(rs.getDate("data_da_facturacao"));
					 fun.setData_registo(data);
				     fun.setHora_registo(rs.getString("hora_da_facturacao"));
				     fun.setQuantidade(rs.getInt("qtd_medicamento"));
				     
				     
				     fun.setAno_nascimento(rs.getInt("ano"));
				     fun.setMes_nascimento(rs.getInt("mes"));
				     fun.setDias_nascimento(rs.getInt("dia"));

				     
				     
				     
				     fun.setId(rs.getInt("fk_factura"));
				     fun.setNumero_processo(rs.getString("NumeroProcesso"));
					 
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
	
	public List <Paciente> buscarPacienteVacinadosx(String numero)
    {
			List <Paciente> lista = new ArrayList<Paciente>();
			String sql = "SELECT * FROM vwpacientes_a_serem_vacinados where NumeroProceso =? and FK_categoria = 3 order by hora_da_facturacao desc";
			try {
				con = Conexao.getConexao();
				PreparedStatement preparador = con.prepareStatement(sql);
				preparador.setString(1, numero);
				ResultSet rs = preparador.executeQuery();
				while (rs.next())
				 {
					 Paciente fun = new Paciente();
					 fun.setNomeCompleto(rs.getString("NomeCompleto"));
					 Calendar data = Calendar.getInstance();
					 data.setTime(rs.getDate("data_da_facturacao"));
					 fun.setData_registo(data);
				     fun.setHora_registo(rs.getString("hora_da_facturacao"));
				     fun.setNomeFun(rs.getString("n_c_funcionario"));
//				     fun.setQuantidade(rs.getInt("qtd"));
				     fun.setTotal_s(rs.getString("total"));
				     fun.setNumero_ordem(rs.getString("numero_factura"));
				     fun.setId(rs.getInt("FK_factura"));
				     fun.setNumero_processo(rs.getString("NumeroProceso"));
				     fun.setMetodo_dpagamento(rs.getString("metodo_depagameto"));
					 fun.setServico(rs.getString("servico"));
					 fun.setId_servico(rs.getInt("id_servico"));
					 fun.setFK_paciente(rs.getInt("FK_paciente"));
					 fun.setFK_funcionario(rs.getInt("FK_funcionario"));
					 fun.setNome_doutor(rs.getString("n_c_funcionario"));
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
	
	public List <Paciente> buscarUltimaVacinaApanhada(String numero)
    {
		List <Paciente> lista = new ArrayList<Paciente>();
		String sql = "SELECT servico, idade, date_format(data_vacina, '%d/%m/%Y') AS data_vacina, n_c_funcionario, NumeroProcesso FROM vwhistorico_vacinacao WHERE NumeroProcesso = ? GROUP BY fk_medicamento ORDER BY data_vacina DESC LIMIT 1";
		try {
			con = Conexao.getConexao();
			PreparedStatement preparador = con.prepareStatement(sql);
			preparador.setString(1, numero);
			ResultSet rs = preparador.executeQuery();
			while (rs.next())
			{
				Paciente fun = new Paciente();
				fun.setServico(rs.getString("servico"));
				fun.setData_vacina(rs.getString("data_vacina"));
				fun.setNomeFun(rs.getString("n_c_funcionario"));
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
	
	
	public List <Paciente> buscarVacinadoCombox(int numero)
    {
			List <Paciente> lista = new ArrayList<Paciente>();
			String sql = "SELECT * FROM vwvacinas_para_aplicar WHERE id_servico NOT IN (SELECT fk_medicamento FROM tblvacinas_efectuadas WHERE FK_factura = ?) AND FK_factura = ? AND FK_factura = ? and fk_categoria =3";
			try {
				con = Conexao.getConexao();
				PreparedStatement preparador = con.prepareStatement(sql);
				preparador.setInt(1, numero);
				preparador.setInt(2, numero);
				preparador.setInt(3, numero);
				ResultSet rs = preparador.executeQuery();
				while (rs.next())
				 {
					 Paciente fun = new Paciente();
				     fun.setId(rs.getInt("FK_factura"));
					 fun.setServico(rs.getString("servico"));
					 fun.setId_servico(rs.getInt("id_servico"));
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
	
	public List <Paciente> buscarPacienteVacinados(String codigo)
    {
			List <Paciente> lista = new ArrayList<Paciente>();
			String sql = "SELECT * FROM vwentidadecomopaciente where numero_processo =? order by nome desc";
			try {
				con = Conexao.getConexao();
				PreparedStatement preparador = con.prepareStatement(sql);
				preparador.setString(1, codigo);
				System.out.println(codigo);
				ResultSet rs = preparador.executeQuery();
				while (rs.next())
				 {
					 Paciente fun = new Paciente();
					 fun.setNomeCompleto(rs.getString("nome")+" "+rs.getString("nome_meio")+" "+rs.getString("ultimo_nome"));
					 Calendar data = Calendar.getInstance();
					 data.setTime(rs.getDate("data_nascimento"));
					 fun.setData_registo(data);
					 fun.setIdade(rs.getDate("data_nascimento"));
					 fun.setNumero_processo(rs.getString("numero_processo"));
					 fun.setNumero_doc(rs.getString("numero_documento"));
					 fun.setNomeEC(rs.getString("estado_civil"));
					 fun.setNomegenero(rs.getString("genero"));
					 fun.setSanguineo(rs.getString("grupo_sanguineo"));
					 fun.setNomep(rs.getString("nome_parente"));
					 fun.setFK_paciente(rs.getInt("FK_entidade"));
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
	
	public List <Paciente> buscarhistVendass()
    {
			List <Paciente> lista = new ArrayList<Paciente>();
			String sql = "SELECT * FROM vwpacientes_a_serem_vacinados WHERE data_da_facturacao = curdate() order by hora_da_facturacao desc";
			try {
				con = Conexao.getConexao();
				PreparedStatement preparador = con.prepareStatement(sql);
				ResultSet rs = preparador.executeQuery();
				while (rs.next())
				 {
					 Paciente fun = new Paciente();
					 fun.setNomeCompleto(rs.getString("NomeCompleto"));
					 Calendar data = Calendar.getInstance();
					 data.setTime(rs.getDate("data_da_facturacao"));
					 fun.setData_registo(data);
				     fun.setHora_registo(rs.getString("hora_da_facturacao"));
				     fun.setNomeFun(rs.getString("n_c_funcionario"));
				     fun.setQuantidade(rs.getInt("qtd"));
				     fun.setTotal_s(rs.getString("total"));
				     fun.setNumero_ordem(rs.getString("numero_factura"));
				     fun.setId(rs.getInt("FK_factura"));
				     fun.setNumero_processo(rs.getString("NumeroProceso"));
				     fun.setMetodo_dpagamento(rs.getString("metodo_depagameto"));
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
	
	public List <Paciente> buscarhistVendaAndPesq(String valor)
    {
			List <Paciente> lista = new ArrayList<Paciente>();
			String sql = "SELECT FK_factura, numero_factura, NumeroProceso, NomeCompleto, count(FK_factura) as 'qtd', n_c_funcionario, Data_da_facturacao, hora_da_facturacao, metodo_depagameto, total FROM vwrelpagamentoexame where NomeCompleto like ? or NumeroProceso like ? group by FK_factura order by Data_da_facturacao desc";
			try {
				con = Conexao.getConexao();
				PreparedStatement preparador = con.prepareStatement(sql);
				preparador.setString(1, valor+"%");
				preparador.setString(2, valor);
				ResultSet rs = preparador.executeQuery();
				while (rs.next())
				 {
					 Paciente fun = new Paciente();
					 fun.setNomeCompleto(rs.getString("NomeCompleto"));
					 Calendar data = Calendar.getInstance();
					 data.setTime(rs.getDate("data_da_facturacao"));
				     fun.setData_registo(data);
				     fun.setHora_registo(rs.getString("hora_da_facturacao"));
				     fun.setNomeFun(rs.getString("n_c_funcionario"));
				     fun.setQuantidade(rs.getInt("qtd"));
				     fun.setTotal_s(rs.getString("total"));
				     fun.setNumero_ordem(rs.getString("numero_factura"));
				     fun.setId(rs.getInt("FK_factura"));
//				     fun.setPrecoUnitario(rs.getDouble("preco"));
				     fun.setNumero_processo(rs.getString("NumeroProceso"));
				     fun.setMetodo_dpagamento(rs.getString("metodo_depagameto"));
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
	
	public List <Paciente> buscarhistVendas(String aux)
    {
			List <Paciente> lista = new ArrayList<Paciente>();
			String sql = "SELECT * FROM vwhistoricodevenda where NomeCompleto Like ? or numero_factura = ?";
			try {
				con = Conexao.getConexao();
				PreparedStatement preparador = con.prepareStatement(sql);
				preparador.setString(1, aux+"%");
				preparador.setString(2, aux);
				ResultSet rs = preparador.executeQuery();
				while (rs.next())
				 {
					 Paciente fun = new Paciente();
					 fun.setNomeCompleto(rs.getString("NomeCompleto"));
					 Calendar data = Calendar.getInstance();
					 data.setTime(rs.getDate("data_da_facturacao"));
				     fun.setData_registo(data);
				     fun.setData(rs.getDate("data_da_facturacao"));
				     fun.setHora_registo(rs.getString("hora_da_facturacao"));
				     fun.setNomeFun(rs.getString("n_c_funcionario"));
				     fun.setQuantidade(rs.getInt("qtd"));
				     fun.setTotal_s(rs.getString("total"));
				     fun.setNumero_ordem(rs.getString("numero_factura"));
				     fun.setId(rs.getInt("FK_factura"));
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
	public List <Exames> buscarVisualizarHistVendas(String cod)
    {
			List <Exames> lista = new ArrayList<Exames>();
			String sql = "SELECT * FROM  vwrelpagamentoexame WHERE numero_factura =? order by analise_clinica asc";
			try {
				con = Conexao.getConexao();
				PreparedStatement preparador = con.prepareStatement(sql);
				preparador.setString(1, cod);
				ResultSet rs = preparador.executeQuery();
				while (rs.next())
				 {
					 Exames fun = new Exames();
					 fun.setAnalise_clinica(rs.getString("analise_clinica"));
				     fun.setPreco(rs.getDouble("preco"));
				     fun.setTaxa(rs.getString("taxa"));
				     fun.setTaxa_emkz(rs.getDouble("taxa_kz"));
				     fun.setSubtotal(rs.getDouble("total"));
				     fun.setNumero_do_exame(rs.getString("numero_factura"));
				     fun.setFK_factura(rs.getInt("FK_factura"));
				     
				     Calendar data = Calendar.getInstance();
				     data.setTime(rs.getDate("data_da_facturacao"));
				     fun.setData_da_facturacao(data);
				     
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
	
	public List <Exames> buscarVisualizarHistVendasConsulta(String cod)
    {
			List <Exames> lista = new ArrayList<Exames>();
			String sql = "SELECT * FROM  vwrelpagamentoconsultas WHERE numero_factura =?";
			try {
				con = Conexao.getConexao();
				PreparedStatement preparador = con.prepareStatement(sql);
				preparador.setString(1, cod);
				ResultSet rs = preparador.executeQuery();
				while (rs.next())
				 {
					 Exames fun = new Exames();
					 fun.setAnalise_clinica(rs.getString("servico"));
				     fun.setPreco(rs.getDouble("preco"));
				     fun.setTaxa(rs.getString("taxa"));
				     fun.setSubtotal(rs.getDouble("total"));
				     fun.setNumero_do_exame(rs.getString("numero_factura"));
//				     fun.setFK_factura(rs.getInt("FK_factura"));
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
}
