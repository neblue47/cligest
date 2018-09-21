package ao.co.cligest.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

import ao.co.cligest.entidades.*;
import ao.co.cligest.interfaces.IAgendaConsulta;

public class AgendaConsultaDAO implements IAgendaConsulta {
	
	private Connection con ;
	Formatando ft = new Formatando();
	int confirmado = 0;
	
	
	 	
	
public int inserirmarcacao(Paciente mc){
	int ultimoId = -1;
	    String sql = "INSERT INTO TBLCONSULTAMARCADA (FK_paciente,FK_doutor,FK_servico,FK_funcionario,data,data_do_agendamento,FK_especialidade,gestante_pra_consulta,numero_da_consulta,hora_do_agendamento, FK_fornecedor, FK_cont_forn,fk_convenio,fk_plano) values (?,?,?,?,?,?,?,?,?,?,?,?,?,?)";
	    try {
			 
			 //1-TBLCONSULTAMARCADA
	    	 con = Conexao.getConexao();
			 PreparedStatement cid = con.prepareStatement(sql);
			 cid.setInt(1,  mc.getFK_entidade_paciente());
			 cid.setInt(2,  mc.getFK_doutor());
			 cid.setInt(3,  mc.getId_servico());
			 cid.setInt(4,  mc.getFK_entidade());
			 cid.setDate(5, new Formatando().data_registo());
			 cid.setDate(6, mc.getData_agendamento());
			 cid.setInt(7,  mc.getFK_especialidade());
			 cid.setInt(8,  mc.getGestante());
			 cid.setString(9,  mc.getNumero_da_consulta());
			 cid.setString(10, mc.getHora_daconfirmacao());
			 cid.setInt(11, mc.getId_convenio());
			 cid.setInt(12, mc.getId());
			 cid.setInt(13, mc.getId_convenio());
			 cid.setInt(14, mc.getId());
			 cid.execute(); 
			 ResultSet rs = cid.executeQuery("SELECT LAST_INSERT_ID()");
			 if(rs.next())
	    	        ultimoId = rs.getInt(1);
			 cid.close();
			 System.out.println("Cadastro com sucesso...TBLCONSULTAMARCADA"); 
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
	    return ultimoId;
	}

 
		public String numConsulta()
		{ 
			int fun = 0;
			String sql = "SELECT count(id_consulta_marcada) numero FROM tblconsultamarcada";
			try {
				con = Conexao.getConexao();
				PreparedStatement ps = con.prepareStatement(sql);
				ResultSet rs = ps.executeQuery();
				
				if(rs.next())
					fun = rs.getInt("numero");
				
				ps.close();
				con.close();
			}
			catch(Exception e){}
			 
			return "CONS/2018/000"+fun;  
			
		}


		@Override
		public void adicionaConsulta(Paciente p) {
			String sql = "INSERT INTO tblconsultamarcada (FK_paciente,FK_doutor,FK_servico,FK_funcionario,data_do_agendamento,hora_do_agendamento,numero_da_consulta,contacto) VALUES (?,?,?,?,?,?,?,?)";
			try {
				 con = Conexao.getConexao();
				 PreparedStatement ps = con.prepareStatement(sql);
				 ps.setInt(1, p.getFK_paciente());
				 ps.setInt(2, p.getFK_doutor());
				 ps.setInt(3, p.getFK_servico());
				 ps.setInt(4, p.getFK_funcionario());
				 ps.setDate(5, p.getData_agendamento());
				 ps.setString(6, p.getHora_daconfirmacao());
				 ps.setString(7, numConsulta());
				 ps.setLong(8, p.getTelefonep());
				 ps.execute();
			} catch (Exception e) {
				e.printStackTrace();
			}
		}


		@Override
		public void confirmarConsulta(Paciente p) {
			String sql = "INSERT INTO tblconsultaconfirmada (FK_consulta_marcada,FK_servico,data_confirmacao,hora_daconfirmacao,FK_entidade_paciente,FK_doutor,FK_funcionario) VALUES (?,?,?,?,?,?,?)";
			try {
				 con = Conexao.getConexao();
				 PreparedStatement ps = con.prepareStatement(sql);
				 ps.setInt(1, p.getFK_consulta_marcada());
				 ps.setInt(2, p.getFK_servico());
				 ps.setDate(3, ft.data_registo());
				 ps.setString(4, ft.getHoraAtual());
				 ps.setInt(5, p.getFK_paciente());
				 ps.setInt(6, p.getFK_doutor());
				 ps.setInt(7, p.getFK_funcionario());
				 ps.execute();
			} catch (Exception e) {
				e.printStackTrace();
			}
		}


		@Override
		public void alterarConsulta(Paciente p) {
			
		}


		@Override
		public void cancelarConsulta(Paciente p) {
			String sql = "INSERT INTO tblconsultacanselada (FK_funcionario,FK_servico,data,hora,FK_confirmacao_agendamento) VALUES (?,?,?,?,?)";
			try {
				 con = Conexao.getConexao();
				 PreparedStatement ps = con.prepareStatement(sql);
				 ps.setInt(1, p.getFK_funcionario());
				 ps.setInt(2, p.getFK_servico());
				 ps.setDate(3, ft.data_registo());
				 ps.setString(4, ft.getHoraAtual());
				 ps.setInt(5, p.getFK_consulta_confirmada());
				 ps.execute();
			} catch (Exception e) {
				e.printStackTrace();
			}
		}


		@Override
		public List<Paciente> listaConsultaAgendada() {
			String sql = "SELECT * FROM vwlistaConsultaAgendada order by id_consulta_marcada desc limit 5";
			List<Paciente> lista = new ArrayList<>();
			try {
				 con = Conexao.getConexao();
				 PreparedStatement ps = con.prepareStatement(sql);
				 ResultSet rs = ps.executeQuery();
				 while(rs.next())
				 {
					 Paciente p = new Paciente();
					 p.setNome_paciente(rs.getString("NomeCompleto"));
					 p.setNome_doutor(rs.getString("nome")+" "+rs.getString("ultimo_nome"));
					 p.setServico(rs.getString("servico"));
					 p.setTelefonep(rs.getLong("contacto"));
					 p.setFK_consulta_marcada(rs.getInt("id_consulta_marcada"));
					 p.setHora_daconfirmacao(rs.getString("hora_do_agendamento"));
					 Calendar data = Calendar.getInstance();
					 data.setTime(rs.getDate("data_do_agendamento"));
					 p.setData_do_agendamento(data);
					 p.setFK_servico(rs.getInt("FK_servico"));
					 p.setPac_idade(rs.getInt("idade"));
					 p.setFK_paciente(rs.getInt("FK_paciente"));
					 p.setFK_doutor(rs.getInt("FK_doutor"));
					 confirmado = consultaConfirmada(rs.getInt("id_consulta_marcada"));
					 p.setConfirmado(confirmado);
					 p.setFK_consulta_confirmada(confirmado);
					 lista.add(p);
				 }
			} catch (Exception e) {
				e.printStackTrace();
			}
			return lista;
		}
		
		@Override
		public List<Paciente> listaConsultaConfirmada() {
			String sql = "SELECT * FROM vwlistaConsultaAgendada vw join tblconsultaconfirmada cf on vw.id_consulta_marcada = cf.FK_consulta_marcada"
					+ " where cf.id_consulta_confirmada not in (SELECT FK_consulta_confirmada FROM tblfacturaconsulta ) "
					+ " order by id_consulta_marcada asc ";
			List<Paciente> lista = new ArrayList<>();
			try {
				 con = Conexao.getConexao();
				 PreparedStatement ps = con.prepareStatement(sql);
				 ResultSet rs = ps.executeQuery();
				 while(rs.next())
				 {
					 Paciente p = new Paciente();
					 p.setNome_paciente(rs.getString("NomeCompleto"));
					 p.setNome_doutor(rs.getString("nome")+" "+rs.getString("ultimo_nome"));
					 p.setServico(rs.getString("servico"));
					 p.setTelefonep(rs.getLong("contacto"));
					 p.setFK_consulta_marcada(rs.getInt("id_consulta_marcada"));
					 p.setHora_daconfirmacao(rs.getString("hora_do_agendamento"));
					 Calendar data = Calendar.getInstance();
					 data.setTime(rs.getDate("data_do_agendamento"));
					 p.setData_do_agendamento(data);
					 p.setFK_servico(rs.getInt("FK_servico"));
					 p.setPac_idade(rs.getInt("idade"));
					 p.setFK_paciente(rs.getInt("FK_paciente"));
					 p.setFK_doutor(rs.getInt("FK_doutor"));
					 confirmado = consultaConfirmada(rs.getInt("id_consulta_marcada"));
					 p.setFK_consulta_confirmada(confirmado);
					 p.setConfirmado(confirmado);
					 p.setPreco(rs.getDouble("sub_total"));
					 lista.add(p);
				 }
			} catch (Exception e) {
				e.printStackTrace();
			}
			return lista;
		}
		
		@Override
		public List<Paciente> listaConsultaConfirmadaPago() {
			String sql = " SELECT  * FROM vwlistaConsultaAgendada vw  "
					   + " JOIN tblconsultaconfirmada cf ON vw.id_consulta_marcada = cf.FK_consulta_marcada "
					   + " JOIN tblfacturaconsulta fcs ON cf.id_consulta_confirmada = fcs.FK_consulta_confirmada "
					   + " JOIN `tblfactura` `fc` ON `fcs`.`FK_factura` = `fc`.`id_factura` ORDER BY `fc`.`id_factura` DESC";
			List<Paciente> lista = new ArrayList<>();
			try {
				 con = Conexao.getConexao();
				 PreparedStatement ps = con.prepareStatement(sql);
				 ResultSet rs = ps.executeQuery();
				 while(rs.next())
				 {
					 Paciente p = new Paciente();
					 p.setNome_paciente(rs.getString("NomeCompleto"));
					 p.setNome_doutor(rs.getString("nome")+" "+rs.getString("ultimo_nome"));
					 p.setServico(rs.getString("servico"));
					 p.setTelefonep(rs.getLong("contacto"));
					 p.setFK_consulta_marcada(rs.getInt("id_consulta_marcada"));
					 p.setHora_daconfirmacao(rs.getString("hora_do_agendamento"));
					 Calendar data = Calendar.getInstance();
					 data.setTime(rs.getDate("data_do_agendamento"));
					 p.setData_do_agendamento(data);
					 p.setFK_servico(rs.getInt("FK_servico"));
					 p.setPac_idade(rs.getInt("idade"));
					 p.setFK_paciente(rs.getInt("FK_paciente"));
					 p.setFK_doutor(rs.getInt("FK_doutor"));
					 confirmado = consultaConfirmada(rs.getInt("id_consulta_marcada"));
					 p.setFK_consulta_confirmada(confirmado);
					 p.setConfirmado(confirmado);
					 p.setPreco(rs.getDouble("sub_total"));
					 p.setNumero_doc(rs.getString("numero_factura"));
					 lista.add(p);
				 }
			} catch (Exception e) {
				e.printStackTrace();
			}
			return lista;
		}
		
		@Override
		public List<Paciente> listaPagoNaoTriado() {
			String sql = " SELECT  * FROM vwlistaConsultaAgendada vw  "
					   + " JOIN tblconsultaconfirmada cf ON vw.id_consulta_marcada = cf.FK_consulta_marcada "
					   + " JOIN tblfacturaconsulta fcs ON cf.id_consulta_confirmada = fcs.FK_consulta_confirmada "
					   + " WHERE  cf.id_consulta_confirmada NOT IN (SELECT trg.FK_consulta_confirmada FROM tbltriagem trg) ";
			List<Paciente> lista = new ArrayList<>();
			try {
				 con = Conexao.getConexao();
				 PreparedStatement ps = con.prepareStatement(sql);
				 ResultSet rs = ps.executeQuery();
				 while(rs.next())
				 {
					 Paciente p = new Paciente();
					 p.setNome_paciente(rs.getString("NomeCompleto"));
					 p.setNome_doutor(rs.getString("nome")+" "+rs.getString("ultimo_nome"));
					 p.setServico(rs.getString("servico"));
					 p.setTelefonep(rs.getLong("contacto"));
					 p.setFK_consulta_marcada(rs.getInt("id_consulta_marcada"));
					 p.setHora_daconfirmacao(rs.getString("hora_do_agendamento"));
					 Calendar data = Calendar.getInstance();
					 data.setTime(rs.getDate("data_do_agendamento"));
					 p.setData_do_agendamento(data);
					 p.setFK_servico(rs.getInt("FK_servico"));
					 p.setPac_idade(rs.getInt("idade"));
					 p.setFK_paciente(rs.getInt("FK_paciente"));
					 p.setFK_doutor(rs.getInt("FK_doutor"));
					 confirmado = consultaConfirmada(rs.getInt("id_consulta_marcada"));
					 p.setFK_consulta_confirmada(confirmado);
					 p.setConfirmado(confirmado);
					 p.setPreco(rs.getDouble("sub_total"));
					 lista.add(p);
				 }
			} catch (Exception e) {
				e.printStackTrace();
			}
			return lista;
		}
		
		@Override
		public List<Paciente> listaPagoTriado() {
			String sql = " SELECT  * FROM vwlistaConsultaAgendada vw  "
					   + " JOIN tblconsultaconfirmada cf ON vw.id_consulta_marcada = cf.FK_consulta_marcada "
					   + " JOIN tblfacturaconsulta fcs ON cf.id_consulta_confirmada = fcs.FK_consulta_confirmada "
					   + " JOIN tbltriagem trg ON cf.id_consulta_confirmada = trg.FK_consulta_confirmada "
					   + " WHERE cf.id_consulta_confirmada NOT IN (SELECT FK_consulta_confirmada FROM tblconsultafinalizada)";
			List<Paciente> lista = new ArrayList<>();
			try {
				 con = Conexao.getConexao();
				 PreparedStatement ps = con.prepareStatement(sql);
				 ResultSet rs = ps.executeQuery();
				 while(rs.next())
				 {
					 Paciente p = new Paciente();
					 p.setNome_paciente(rs.getString("NomeCompleto"));
					 p.setNome_doutor(rs.getString("nome")+" "+rs.getString("ultimo_nome"));
					 p.setServico(rs.getString("servico"));
					 p.setNomegenero(rs.getString("genero"));
					 p.setTelefonep(rs.getLong("contacto"));
					 p.setFK_consulta_marcada(rs.getInt("id_consulta_marcada"));
					 p.setHora_daconfirmacao(rs.getString("hora_do_agendamento"));
					 Calendar data = Calendar.getInstance();
					 data.setTime(rs.getDate("data_do_agendamento"));
					 p.setData_do_agendamento(data);
					 p.setFK_servico(rs.getInt("FK_servico"));
					 p.setPac_idade(rs.getInt("idade"));
					 p.setFK_paciente(rs.getInt("FK_paciente"));
					 p.setFK_doutor(rs.getInt("FK_doutor"));
					 confirmado = consultaConfirmada(rs.getInt("id_consulta_marcada"));
					 p.setFK_consulta_confirmada(confirmado);
					 p.setConfirmado(confirmado);
					 p.setPreco(rs.getDouble("sub_total"));
					 p.setStatus(retornaStatus(rs.getInt("FK_estado_paciente")));
					 p.setFK_status(rs.getInt("FK_estado_paciente"));
					 lista.add(p);
				 }
			} catch (Exception e) {
				e.printStackTrace();
			}
			return lista;
		}
		@Override
		public List<Paciente> listaPacientesConsultados() {
			String sql = " SELECT  * FROM vwlistaConsultaAgendada vw  "
					   + " JOIN tblconsultaconfirmada cf ON vw.id_consulta_marcada = cf.FK_consulta_marcada "
					   + " JOIN tblfacturaconsulta fcs ON cf.id_consulta_confirmada = fcs.FK_consulta_confirmada "
					   + " JOIN tbltriagem trg ON cf.id_consulta_confirmada = trg.FK_consulta_confirmada "
					   + " JOIN tblconsultafinalizada cff ON cf.id_consulta_confirmada = cff.FK_consulta_confirmada ORDER BY cff.data desc"
					   ;
			List<Paciente> lista = new ArrayList<>();
			try {
				 con = Conexao.getConexao();
				 PreparedStatement ps = con.prepareStatement(sql);
				 ResultSet rs = ps.executeQuery();
				 while(rs.next())
				 {
					 Paciente p = new Paciente();
					 p.setNome_paciente(rs.getString("NomeCompleto"));
					 p.setNome_doutor(rs.getString("nome")+" "+rs.getString("ultimo_nome"));
					 p.setServico(rs.getString("servico"));
					 p.setNomegenero(rs.getString("genero"));
					 p.setTelefonep(rs.getLong("contacto"));
					 p.setFK_consulta_marcada(rs.getInt("id_consulta_marcada"));
					 p.setHora_daconfirmacao(rs.getString("hora_do_agendamento"));
					 Calendar data = Calendar.getInstance();
					 data.setTime(rs.getDate("data"));
					 p.setData_do_agendamento(data);
					 p.setFK_servico(rs.getInt("FK_servico"));
					 p.setPac_idade(rs.getInt("idade"));
					 p.setFK_paciente(rs.getInt("FK_paciente"));
					 p.setFK_doutor(rs.getInt("FK_doutor"));
					 confirmado = consultaConfirmada(rs.getInt("id_consulta_marcada"));
					 p.setFK_consulta_confirmada(confirmado);
					 p.setConfirmado(confirmado);
					 p.setPreco(rs.getDouble("sub_total"));
					 p.setStatus(retornaStatus(rs.getInt("FK_estado_paciente")));
					 p.setFK_status(rs.getInt("FK_estado_paciente"));
					 p.setFK_consulta(rs.getInt("FK_consulta"));
					 lista.add(p);
				 }
			} catch (Exception e) {
				e.printStackTrace();
			}
			return lista;
		}
		
		@Override
		public Paciente getConsultaConfirmada(int consultaMarcadaId) {
			String sql = "SELECT * FROM vwlistaConsultaAgendada vw join tblconsultaconfirmada cf on vw.id_consulta_marcada = cf.FK_consulta_marcada"
					+ " where vw.id_consulta_marcada = ?  ";
			 Paciente p = new Paciente();
			try {
				 con = Conexao.getConexao();
				 PreparedStatement ps = con.prepareStatement(sql);
				 ps.setInt(1, consultaMarcadaId);
				 ResultSet rs = ps.executeQuery();
				 if(rs.next())
				 {
					
					 p.setNome_paciente(rs.getString("NomeCompleto"));
					 p.setNome_doutor(rs.getString("nome")+" "+rs.getString("ultimo_nome"));
					 p.setServico(rs.getString("servico"));
					 p.setTelefonep(rs.getLong("contacto"));
					 p.setFK_consulta_marcada(rs.getInt("id_consulta_marcada"));
					 p.setHora_daconfirmacao(rs.getString("hora_do_agendamento"));
					 Calendar data = Calendar.getInstance();
					 data.setTime(rs.getDate("data_do_agendamento"));
					 p.setData_do_agendamento(data);
					 p.setFK_servico(rs.getInt("FK_servico"));
					 p.setPac_idade(rs.getInt("idade"));
					 p.setFK_paciente(rs.getInt("FK_paciente"));
					 p.setFK_doutor(rs.getInt("FK_doutor"));
					 confirmado = consultaConfirmada(rs.getInt("id_consulta_marcada"));
					 p.setFK_consulta_confirmada(confirmado);
					 p.setConfirmado(confirmado);
					 p.setPreco(rs.getDouble("sub_total"));
				 }
			} catch (Exception e) {
				e.printStackTrace();
			}
			return p;
		}
		
		@Override
		public Paciente ConsultaParaPagar(int codcs) {
			String sql = "SELECT * FROM vwlistaConsultaAgendada vw join tblconsultaconfirmada cf on vw.id_consulta_marcada = cf.FK_consulta_marcada "
					+ " where id_consulta_marcada = ?";
			 Paciente p = new Paciente();
			try {
				 con = Conexao.getConexao();
				 PreparedStatement ps = con.prepareStatement(sql);
				 ps.setInt(1, codcs);
				 ResultSet rs = ps.executeQuery();
				 if(rs.next())
				 {
					 p.setNome_paciente(rs.getString("NomeCompleto"));
					 p.setNome_doutor(rs.getString("nome")+" "+rs.getString("ultimo_nome"));
					 p.setServico(rs.getString("servico"));
					 p.setTelefonep(rs.getLong("contacto"));
					 p.setFK_consulta_marcada(rs.getInt("id_consulta_marcada"));
					 p.setHora_daconfirmacao(rs.getString("hora_do_agendamento"));
					 Calendar data = Calendar.getInstance();
					 data.setTime(rs.getDate("data_do_agendamento"));
					 p.setData_do_agendamento(data);
					 p.setFK_servico(rs.getInt("FK_servico"));
					 p.setPac_idade(rs.getInt("idade"));
					 p.setFK_paciente(rs.getInt("FK_paciente"));
					 p.setFK_doutor(rs.getInt("FK_doutor"));
					 p.setPreco(rs.getDouble("sub_total"));
				 }
			} catch (Exception e) {
				e.printStackTrace();
			}
			return p;
		}


		@Override
		public List<Paciente> listaConsultaAgendada(String query) {
			String sql = "SELECT * FROM vwlistaConsultaAgendada where nomeCompleto Like ? order by id_consulta_marcada desc limit 5";
			List<Paciente> lista = new ArrayList<>();
			try {
				 con = Conexao.getConexao();
				 PreparedStatement ps = con.prepareStatement(sql);
				 ps.setString(1, query+"%");
				 ResultSet rs = ps.executeQuery();
				 while(rs.next())
				 {
					 Paciente p = new Paciente();
					 p.setNome_paciente(rs.getString("NomeCompleto"));
					 p.setNome_doutor(rs.getString("nome")+" "+rs.getString("ultimo_nome"));
					 p.setServico(rs.getString("servico"));
					 p.setTelefonep(rs.getLong("contacto"));
					 p.setFK_consulta_marcada(rs.getInt("id_consulta_marcada"));
					 p.setHora_daconfirmacao(rs.getString("hora_do_agendamento"));
					 Calendar data = Calendar.getInstance();
					 data.setTime(rs.getDate("data_do_agendamento"));
					 p.setData_do_agendamento(data);
					 p.setFK_servico(rs.getInt("FK_servico"));
					 p.setPac_idade(rs.getInt("idade"));
					 p.setFK_paciente(rs.getInt("FK_paciente"));
					 p.setFK_doutor(rs.getInt("FK_doutor"));
					 confirmado = consultaConfirmada(rs.getInt("id_consulta_marcada"));
					 p.setConfirmado(confirmado);
					 p.setFK_consulta_confirmada(confirmado);
					 lista.add(p);
				 }
			} catch (Exception e) {
				e.printStackTrace();
			}
			return lista;
		}


		@Override
		public Paciente listaConsultaPorId(int codcs) {
			String sql = "SELECT * FROM tblconsultamarcada WHERE id_consulta_marcada = ?";
			Paciente pac = new Paciente();
			try {
				 con = Conexao.getConexao();
				 PreparedStatement ps = con.prepareStatement(sql);
				 ps.setInt(1, codcs);
				 ResultSet rs = ps.executeQuery();
				 if(rs.next())
				 {
					 pac.setFK_consulta_marcada(rs.getInt("id_consulta_marcada"));
					 pac.setFK_paciente(rs.getInt("FK_paciente"));
					 pac.setFK_doutor(rs.getInt("FK_doutor"));
					 pac.setFK_servico(rs.getInt("FK_servico"));
					 pac.setHora_daconfirmacao(rs.getString("hora_do_agendamento"));
					 Calendar data = Calendar.getInstance();
					 data.setTime(rs.getDate("data_do_agendamento"));
					 pac.setData_do_agendamento(data);
				 }
			} catch (Exception e) {
				e.printStackTrace();
			}
			return pac;
		}
		
		private int consultaConfirmada(int codcs)
		{
			int ultimoId = 0;
			String sql = "SELECT * FROM tblconsultaconfirmada WHERE FK_consulta_marcada = ?";
			try {
				 con = Conexao.getConexao();
				 PreparedStatement ps = con.prepareStatement(sql);
				 ps.setInt(1, codcs);
				 ResultSet rs = ps.executeQuery();
				 if(rs.next())
					 ultimoId = rs.getInt("id_consulta_confirmada");
			} catch (Exception e) {
				e.printStackTrace();
			}
			return ultimoId;
		}
		@Override
		public int getServicoConfirmadaPorId(int codcs)
		{
			int ultimoId = 0;
			String sql = "SELECT * FROM tblconsultaconfirmada WHERE id_consulta_confirmada = ?";
			try {
				 con = Conexao.getConexao();
				 PreparedStatement ps = con.prepareStatement(sql);
				 ps.setInt(1, codcs);
				 ResultSet rs = ps.executeQuery();
				 if(rs.next())
					 ultimoId = rs.getInt("FK_servico");
			} catch (Exception e) {
				e.printStackTrace();
			}
			return ultimoId;
		}


		@Override
		public List<Paciente> listaAgendasPacientes() {
			String sql = "SELECT  * FROM tblconsultamarcada cons  JOIN vwpacientesimples pac ON cons.FK_paciente = pac.Entidade";
			List<Paciente> lista = new ArrayList<>();
			try {
				 con = Conexao.getConexao();
				 PreparedStatement ps = con.prepareStatement(sql);
				 ResultSet rs = ps.executeQuery();
				 while(rs.next())
				 {
					 Paciente p = new Paciente();
					 p.setNome_paciente(rs.getString("NomeCompleto"));
					 p.setFK_consulta_marcada(rs.getInt("id_consulta_marcada"));
					 p.setHora_daconfirmacao(rs.getString("hora_do_agendamento"));
					 p.setData_em_string(rs.getString("data_do_agendamento"));
					 Calendar data = Calendar.getInstance();
					 data.setTime(rs.getDate("data_do_agendamento"));
					 p.setData_do_agendamento(data);
					 p.setPac_idade(rs.getInt("idade"));
					 p.setFK_paciente(rs.getInt("FK_paciente"));
					 p.setFK_doutor(rs.getInt("FK_doutor"));
					 confirmado = consultaConfirmada(rs.getInt("id_consulta_marcada"));
					 p.setConfirmado(confirmado);
					 lista.add(p);
				 }
			} catch (Exception e) {
				e.printStackTrace();
			}
			return lista;
		}

		private String retornaStatus(int sts){
			String sql ="Select * from tblestadodopaciente where id_estado_do_paciente = ?";
			try {
				con = Conexao.getConexao();
				PreparedStatement ps = con.prepareStatement(sql);
				ps.setInt(1, sts);
				ResultSet rs = ps.executeQuery();
				if(rs.next())
					return rs.getString("estado_do_paciente");
				
				ps.close();
				con.close();
			}catch (Exception e) {
				e.printStackTrace();
			}
			return "";
		}
		
		@Override
		public Triagem getSinais(String codc)
		{
			Triagem pac = null;
			String sql = "SELECT * FROM tbltriagem where FK_consulta_confirmada = ? ";
			try
			{
				con = Conexao.getConexao();
				 PreparedStatement preparador = con.prepareStatement(sql);
				 preparador.setString(1, codc);
				 ResultSet rs = preparador.executeQuery();
				 if(rs.next())
				 {
					 pac = new Triagem();
					 pac.setAltura(rs.getDouble("altura"));
					 pac.setPeso(rs.getDouble("peso"));
					 pac.setImc(rs.getDouble("imc"));
					 pac.setValor_tmp(rs.getString("temperatura"));
					 pac.setValor_pulso(rs.getString("pulso"));
					 pac.setValor_tns(rs.getString("tensao_sistolica"));
					 pac.setTensao_diastolica(rs.getString("tensao_diastolica"));
					 pac.setValor_resp(rs.getString("respiracao"));
					// pac.setEstado(rs.getString("estado_do_paciente"));
					 pac.setDescricao(rs.getString("diagnostico_preliminar"));
					 
				 }
				 preparador.close();
			}
			
			catch(SQLException er){
			er.printStackTrace();
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
}
