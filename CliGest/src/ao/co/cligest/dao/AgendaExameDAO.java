package ao.co.cligest.dao;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

import org.apache.commons.lang3.StringUtils;

 
import ao.co.cligest.entidades.Exames;
import ao.co.cligest.entidades.Paciente;
import ao.co.cligest.entidades.Servico;
import ao.co.cligest.interfaces.IAgendaExame;

public class AgendaExameDAO implements IAgendaExame{

	private Connection con ;
	Formatando ft = new Formatando();
	int confirmado = 0;
	@Override
	public void requisitarExame(Exames ex  ) {
		String sql = "INSERT INTO tblexamerequisitado (FK_numero_do_servico_de_exame,FK_servico_exame,data,hora,FK_funcionario,FK_paciente,gestante_pra_exame,FK_tipo_dcobertura,fk_convenio,fk_plano) VALUES (?,?,?,?,?,?,?,?,?,?)";
		
		try {
				con = Conexao.getConexao();
				PreparedStatement cp = con.prepareStatement(sql);
				cp.setInt(1, ex.getFK_numero_do_servico_de_exame());
				cp.setInt(2, ex.getFK_servico_exame());
				cp.setDate(3, ft.data_registo());
				cp.setString(4, ft.getHoraAtual());
				cp.setInt(5, ex.getFK_funcionario());
				cp.setInt(6, ex.getFK_paciente());
				cp.setInt(7, 0);
				cp.setInt(8, ex.getFK_tipo_dcobertura());
				cp.setInt(9, ex.getFK_convenio());
				cp.setInt(10, ex.getFK_plano());
				cp.execute();
				cp.close(); 
				con.close();
			} 
			catch (Exception e) {
				e.printStackTrace();
			}
		
	}

	@Override
	public void confirmarRequisicaoExame(Exames ex) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void alterarRequisicaoExame(Exames ex) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void cancelarRequisicaoExame(Exames ex) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public Paciente listaRequisicaoExamePorId(int codcs) {
		String sql = "select *   FROM vwexamesrequisitadoparafaturar vw join tbltelefone fn on vw.FK_paciente = fn.FK_entidade where vw.data = curdate() and FK_numero_do_servico_de_exame = ? ";
		 Paciente pac = new Paciente();
		try {
			 con = Conexao.getConexao();
			 PreparedStatement ps = con.prepareStatement(sql);
			 ps.setInt(1, codcs);
			 ResultSet rs = ps.executeQuery();
			 if(rs.next())
			 {
				 pac.setNome_paciente(rs.getString("NomeCompleto").toUpperCase());
				 pac.setTotal_servico(rs.getInt("qtd_servico"));
				 pac.setPreco(rs.getDouble("somatorio"));
				 pac.setFK_consulta_confirmada(rs.getInt("FK_numero_do_servico_de_exame"));
				 pac.setFK_paciente(rs.getInt("FK_paciente"));
				 pac.setHora_daconfirmacao(rs.getString("hora"));
				 pac.setTelefonep(rs.getLong("telefone"));
				 Calendar data = Calendar.getInstance();
				 data.setTime(rs.getDate("data"));
				 pac.setData_do_agendamento(data);
			 }
		} catch (Exception e) {
			e.printStackTrace();
		}
		return pac;
		 
	}

	@Override
	public List<Paciente> listaRequisicaoExameAgendada() {
		List <Paciente> lista = new ArrayList<Paciente>();
		String sql = "select *   FROM vwexamesrequisitadoparafaturar vw join tbltelefone fn on vw.FK_paciente = fn.FK_entidade where vw.data = curdate() "
				+ "and vw.FK_numero_do_servico_de_exame not in (select fk_requisicao_dexame from tblfacturaexame) "
				+ "and vw.FK_numero_do_servico_de_exame not in (select fk_requisicao_dexame from tblfacturademulticaixaexames) "
				+ "and vw.FK_numero_do_servico_de_exame not in (select DISTINCT fk_requisicao_dexame from tblfacturadeseguroexames  ) "
				+ "and vw.FK_numero_do_servico_de_exame not in (select fk_requisicao_dexame from tblfacturaisencaoporidadeexame) "
				+ "and vw.FK_numero_do_servico_de_exame not in (select fk_requisicao_dexame from tblfacturaexameisentado) "
				+ "AND vw.FK_numero_do_servico_de_exame not in (SELECT FK_numero_do_servico_de_exame FROM tblexamerequisitadocancelado) order by hora  ";
		try {
			con = Conexao.getConexao();
			PreparedStatement ps = con.prepareStatement(sql);
			 ResultSet rs = ps.executeQuery();
			 while(rs.next())
			 {
				 Paciente pac = new Paciente();
				 pac.setNome_paciente(rs.getString("NomeCompleto").toUpperCase());
				 pac.setTotal_servico(rs.getInt("qtd_servico"));
				 pac.setValor_total(rs.getDouble("somatorio"));
				 pac.setFK_consulta_confirmada(rs.getInt("FK_numero_do_servico_de_exame"));
				 pac.setFK_paciente(rs.getInt("FK_paciente"));
				 pac.setHora_daconfirmacao(rs.getString("hora"));
				 pac.setTelefonep(rs.getLong("telefone"));
				 Calendar data = Calendar.getInstance();
				 data.setTime(rs.getDate("data"));
				 pac.setData_do_agendamento(data);
				 lista.add(pac);
			 }
			 ps.close();
			 con.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return lista;
	}

	@Override
	public List<Servico> listaRequisicaoExames(int cods) {
		List<Servico> lsServicos = new ArrayList<Servico>();
		String sql = "SELECT * FROM vwservicosexamesrequisitado WHERE FK_numero_do_servico_de_exame = ? order by analise_clinica asc";
		try {
			con = Conexao.getConexao();
			PreparedStatement preparador = con.prepareStatement(sql);
			preparador.setInt(1, cods);
			 ResultSet rs = preparador.executeQuery();
			 while(rs.next())
			 {
				 Servico srv = new Servico();
				 
				 srv.setServico(rs.getString("analise_clinica"));
				 srv.setPreco(rs.getDouble("preco"));
				 srv.setFK_servico_exame(rs.getInt("FK_servico_exame"));
				 srv.setFK_numero_do_servico_de_exame(rs.getInt("FK_numero_do_servico_de_exame"));
				 srv.setTaxa(rs.getFloat("taxa"));
				 srv.setFK_paciente(rs.getInt("fk_paciente"));
				 srv.setId_servico(rs.getInt("id_servico_requisitado")); 
				 srv.setNumero_doservico(rs.getString("numero_do_exame"));
				 Calendar data = Calendar.getInstance();
			     data.setTime(rs.getDate("data"));
			     srv.setData_registo(data);
				 
				 lsServicos.add(srv);
			 }
			 preparador.close();
			 con.close();
		}  
		catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		 
		 
		return lsServicos;
	}

	@Override
	public List<Paciente> listaRequisicaoExameConfirmada() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<Paciente> listaAgendasPacientes() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Paciente ExameParaPagar(int codcs) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public int inserirnumerodoexame(Exames ex)
	{
    	int ultimoId = 0;
    	String sql = "insert into tblnumerodoexame (numero_do_exame,FK_paciente,FK_profissional,recomendacao,observacao,data_registo) values(?,?,?,?,?,?)";
		try {
			con = Conexao.getConexao(); 
			PreparedStatement ps = con.prepareStatement(sql);
			 ps = con.prepareStatement(sql);			
			 ps.setString(1, ex.getNumero_do_exame());
			 ps.setInt(2, ex.getFK_paciente());
			 ps.setInt(3, ex.getFK_funcionario());
			 ps.setString(4, ex.getRecomendacao());
			 ps.setString(5, ex.getObservacao_recomendacao());
			 ps.setDate(6, new Date(ex.getData_darecolha().getTimeInMillis()));
			 ps.execute();
			 ResultSet rs = ps.executeQuery("SELECT LAST_INSERT_ID()");
			 if(rs.next())
			      ultimoId = rs.getInt(1);
			 ps.close();
			 con.close();
		}  
		catch (Exception e) {
			e.printStackTrace();
		}
		 
		return ultimoId;
	}
	@Override
	public String retornaNumExame ()
		{ 
			int valor = 0;
			String sql = "SELECT count(id_numero_do_exame) numero FROM tblnumerodoexame";
			try {
				con = Conexao.getConexao();
				PreparedStatement ps = con.prepareStatement(sql);
				ResultSet rs = ps.executeQuery();
				
				if(rs.next())
					valor =   rs.getInt("numero") + 1;
				ps.close();
				con.close();
			}
			catch (Exception e) {
				e.printStackTrace();
			}
				return "EXAM" +StringUtils.leftPad("0", 4, String.valueOf(valor));  
			
		}
}
