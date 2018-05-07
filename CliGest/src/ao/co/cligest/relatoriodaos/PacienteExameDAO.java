package ao.co.cligest.relatoriodaos;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

import ao.co.cligest.dao.Conexao;
import ao.co.cligest.entidades.Paciente;


public class PacienteExameDAO {

	private Connection con;
	
	public List<Paciente> buscarAgendadosDiario()
	{
		String sql = "SELECT * FROM vwpacienteagendadoparaexame where data = curDate() "
				   + " AND FK_numero_do_servico_de_exame NOT IN(SELECT FK_numero_do_servico_de_exame FROM tblexamerequisitado) "
				   + " order by NomeCompleto asc, data desc limit 50";
		List<Paciente> lista = new ArrayList<Paciente>();
		try {
			con = Conexao.getConexao();
			PreparedStatement ps = con.prepareStatement(sql);
			ResultSet rs = ps.executeQuery();
			while (rs.next()) {
				Paciente px = new Paciente();
				px.setId(rs.getInt("FK_numero_do_servico_de_exame"));
				px.setNome_paciente(rs.getString("NomeCompleto"));
				px.setNumero_processo(rs.getString("numeroProcesso"));
				px.setIdade(rs.getDate("data_nascimento"));
				px.setNomegenero(rs.getString("genero"));
				px.setQuantidade(rs.getInt("qdt_servicos"));
				Calendar data = Calendar.getInstance();
				data.setTime(rs.getDate("data"));
				px.setData_do_agendamento(data);
				px.setData(rs.getDate("data"));
				lista.add(px);
			}
			ps.close();
			con.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return lista;
	}
	
	public List<Paciente> buscarAgendadosEmDatas(Date data1,Date data2)
	{
		String sql = "SELECT * FROM vwpacienteagendadoparaexame where data between ? and ? "
				   + " AND FK_numero_do_servico_de_exame NOT IN(SELECT FK_numero_do_servico_de_exame FROM tblexamerequisitado) "
				   + " order by data desc  ";
		List<Paciente> lista = new ArrayList<Paciente>();
		try {
			con = Conexao.getConexao();
			PreparedStatement ps = con.prepareStatement(sql);
			ps.setDate(1, data1);
			ps.setDate(2, data2);
			ResultSet rs = ps.executeQuery();
			while (rs.next()) {
				Paciente px = new Paciente();
				px.setId(rs.getInt("FK_numero_do_servico_de_exame"));
				px.setNome_paciente(rs.getString("NomeCompleto"));
				px.setNumero_processo(rs.getString("numeroProcesso"));
				px.setIdade(rs.getDate("data_nascimento"));
				px.setNomegenero(rs.getString("genero"));
				px.setQuantidade(rs.getInt("qdt_servicos"));
				Calendar data = Calendar.getInstance();
				data.setTime(rs.getDate("data"));
				px.setData_do_agendamento(data);
				px.setData(rs.getDate("data"));
				lista.add(px);
			}
			ps.close();
			con.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return lista;
	}
	
	
	
	public List<Paciente> buscarAgendadosEmDatasNome(Date data1,Date data2,String nome){
		String sql = "SELECT * FROM vwpacienteagendadoparaexame where data between ? and ? and NomeCompleto Like ?"
				   + " AND FK_numero_do_servico_de_exame NOT IN(SELECT FK_numero_do_servico_de_exame FROM tblexamerequisitado) "
				   + " order by data desc ";
		List<Paciente> lista = new ArrayList<Paciente>();
		try {
			con = Conexao.getConexao();
			PreparedStatement ps = con.prepareStatement(sql);
			ps.setDate(1, data1);
			ps.setDate(2, data2);
			ps.setString(3, nome+"%");
			ResultSet rs = ps.executeQuery();
			while (rs.next()) {
				Paciente px = new Paciente();
				px.setId(rs.getInt("FK_numero_do_servico_de_exame"));
				px.setNome_paciente(rs.getString("NomeCompleto"));
				px.setNumero_processo(rs.getString("numeroProcesso"));
				px.setIdade(rs.getDate("data_nascimento"));
				px.setNomegenero(rs.getString("genero"));
				px.setQuantidade(rs.getInt("qdt_servicos"));
				Calendar data = Calendar.getInstance();
				data.setTime(rs.getDate("data"));
				px.setData_do_agendamento(data);
				px.setData(rs.getDate("data"));
				lista.add(px);
			}
			ps.close();
			con.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return lista;
	}
	
	public List<Paciente> buscarAgendadosEmDatasExame(Date data1,Date data2,int FK_exame){
		String sql = "SELECT * FROM vwpacienteagendadoparaexame where data between ? and ? and FK_servico_exame = ?"
				   + " AND FK_numero_do_servico_de_exame NOT IN(SELECT FK_numero_do_servico_de_exame FROM tblexamerequisitado) "
				   + " order by data desc  ";
		List<Paciente> lista = new ArrayList<Paciente>();
		try {
			con = Conexao.getConexao();
			PreparedStatement ps = con.prepareStatement(sql);
			ps.setDate(1, data1);
			ps.setDate(2, data2);
			ps.setInt(3, FK_exame);
			ResultSet rs = ps.executeQuery();
			while (rs.next()) {
				Paciente px = new Paciente();
				px.setId(rs.getInt("FK_numero_do_servico_de_exame"));
				px.setNome_paciente(rs.getString("NomeCompleto"));
				px.setNumero_processo(rs.getString("numeroProcesso"));
				px.setIdade(rs.getDate("data_nascimento"));
				px.setNomegenero(rs.getString("genero"));
				px.setQuantidade(rs.getInt("qdt_servicos"));
				Calendar data = Calendar.getInstance();
				data.setTime(rs.getDate("data"));
				px.setData_do_agendamento(data);
				px.setData(rs.getDate("data"));
				lista.add(px);
			}
			ps.close();
			con.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return lista;
	}
	
	public List<Paciente> buscarAgendadosEmDatasIdades(Date data1,Date data2,int idade1,int idade2){
		String sql = "SELECT * FROM vwpacienteagendadoparaexame where data between ? and ? and year(from_days((to_days(now()) - to_days(data_nascimento)))) between ? and ? "
				   + " AND FK_numero_do_servico_de_exame NOT IN(SELECT FK_numero_do_servico_de_exame FROM tblexamerequisitado) "
				   + " order by data desc ";
		List<Paciente> lista = new ArrayList<Paciente>();
		try {
			con = Conexao.getConexao();
			PreparedStatement ps = con.prepareStatement(sql);
			ps.setDate(1, data1);
			ps.setDate(2, data2);
			ps.setInt(3, idade1);
			ps.setInt(4, idade2);
			ResultSet rs = ps.executeQuery();
			while (rs.next()) {
				Paciente px = new Paciente();
				px.setId(rs.getInt("FK_numero_do_servico_de_exame"));
				px.setNome_paciente(rs.getString("NomeCompleto"));
				px.setNumero_processo(rs.getString("numeroProcesso"));
				px.setIdade(rs.getDate("data_nascimento"));
				px.setNomegenero(rs.getString("genero"));
				px.setQuantidade(rs.getInt("qdt_servicos"));
				Calendar data = Calendar.getInstance();
				data.setTime(rs.getDate("data"));
				px.setData_do_agendamento(data);
				px.setData(rs.getDate("data"));
				lista.add(px);
			}
			ps.close();
			con.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return lista;
	}
	
	public List<Paciente> buscarAgendadosEmDatasIdadesExame(Date data1,Date data2,int idade1,int idade2,int FK_exame){
		String sql = "SELECT * FROM vwpacienteagendadoparaexame where data between ? and ? and year(from_days((to_days(now()) - to_days(data_nascimento)))) between ? and ? and FK_servico_exame = ?"
				   + " AND FK_numero_do_servico_de_exame NOT IN(SELECT FK_numero_do_servico_de_exame FROM tblexamerequisitado) "
				   + " order by data desc  ";
		List<Paciente> lista = new ArrayList<Paciente>();
		try {
			con = Conexao.getConexao();
			PreparedStatement ps = con.prepareStatement(sql);
			ps.setDate(1, data1);
			ps.setDate(2, data2);
			ps.setInt(3, idade1);
			ps.setInt(4, idade2);
			ps.setInt(5, FK_exame);
			ResultSet rs = ps.executeQuery();
			while (rs.next()) {
				Paciente px = new Paciente();
				px.setId(rs.getInt("FK_numero_do_servico_de_exame"));
				px.setNome_paciente(rs.getString("NomeCompleto"));
				px.setNumero_processo(rs.getString("numeroProcesso"));
				px.setIdade(rs.getDate("data_nascimento"));
				px.setNomegenero(rs.getString("genero"));
				px.setQuantidade(rs.getInt("qdt_servicos"));
				Calendar data = Calendar.getInstance();
				data.setTime(rs.getDate("data"));
				px.setData_do_agendamento(data);
				px.setData(rs.getDate("data"));
				lista.add(px);
			}
			ps.close();
			con.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return lista;
	}
	
	public List<Paciente> buscarAgendadosEmDatasIdadesGenero(Date data1,Date data2,int idade1,int idade2,int genero){
		String sql = "SELECT * FROM vwpacienteagendadoparaexame where data between ? and ? and year(from_days((to_days(now()) - to_days(data_nascimento)))) between ? and ? and  FK_genero = ? "
				   + " AND FK_numero_do_servico_de_exame NOT IN(SELECT FK_numero_do_servico_de_exame FROM tblexamerequisitado) "
				   + " order by data desc  ";
		List<Paciente> lista = new ArrayList<Paciente>();
		try {
			con = Conexao.getConexao();
			PreparedStatement ps = con.prepareStatement(sql);
			ps.setDate(1, data1);
			ps.setDate(2, data2);
			ps.setInt(3, idade1);
			ps.setInt(4, idade2);
			ps.setInt(5, genero);
			ResultSet rs = ps.executeQuery();
			while (rs.next()) {
				Paciente px = new Paciente();
				px.setId(rs.getInt("FK_numero_do_servico_de_exame"));
				px.setNome_paciente(rs.getString("NomeCompleto"));
				px.setNumero_processo(rs.getString("numeroProcesso"));
				px.setIdade(rs.getDate("data_nascimento"));
				px.setNomegenero(rs.getString("genero"));
				px.setQuantidade(rs.getInt("qdt_servicos"));
				Calendar data = Calendar.getInstance();
				data.setTime(rs.getDate("data"));
				px.setData_do_agendamento(data);
				px.setData(rs.getDate("data"));
				lista.add(px);
			}
			ps.close();
			con.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return lista;
	}
	public List<Paciente> buscarAgendadosEmDatasGenero(Date data1,Date data2,int genero){
		String sql = "SELECT * FROM vwpacienteagendadoparaexame where data between ? and ? and  FK_genero = ?"
				   + " AND FK_numero_do_servico_de_exame NOT IN (SELECT FK_numero_do_servico_de_exame FROM tblexamerequisitado) "
				   + " order by data desc ";
		List<Paciente> lista = new ArrayList<Paciente>();
		try {
			con = Conexao.getConexao();
			PreparedStatement ps = con.prepareStatement(sql);
			ps.setDate(1, data1);
			ps.setDate(2, data2);
			ps.setInt(3, genero);
			ResultSet rs = ps.executeQuery();
			while (rs.next()) {
				Paciente px = new Paciente();
				px.setId(rs.getInt("FK_numero_do_servico_de_exame"));
				px.setNome_paciente(rs.getString("NomeCompleto"));
				px.setNumero_processo(rs.getString("numeroProcesso"));
				px.setIdade(rs.getDate("data_nascimento"));
				px.setNomegenero(rs.getString("genero"));
				px.setQuantidade(rs.getInt("qdt_servicos"));
				Calendar data = Calendar.getInstance();
				data.setTime(rs.getDate("data"));
				px.setData_do_agendamento(data);
				px.setData(rs.getDate("data"));
				lista.add(px);
			}
			ps.close();
			con.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return lista;
	}
	
	public List<Paciente> buscarAgendadosEmDatasGeneroExame(Date data1,Date data2,int genero,int FK_exame){
		String sql = "SELECT * FROM vwpacienteagendadoparaexame where data between ? and ? and  FK_genero = ? and  FK_servico_exame = ?"
				   + " AND FK_numero_do_servico_de_exame NOT IN (SELECT FK_numero_do_servico_de_exame FROM tblexamerequisitado) "
				   + " order by data desc  ";
		List<Paciente> lista = new ArrayList<Paciente>();
		try {
			con = Conexao.getConexao();
			PreparedStatement ps = con.prepareStatement(sql);
			ps.setDate(1, data1);
			ps.setDate(2, data2);
			ps.setInt(3, genero);
			ps.setInt(4, FK_exame);
			ResultSet rs = ps.executeQuery();
			while (rs.next()) {
				Paciente px = new Paciente();
				px.setId(rs.getInt("FK_numero_do_servico_de_exame"));
				px.setNome_paciente(rs.getString("NomeCompleto"));
				px.setNumero_processo(rs.getString("numeroProcesso"));
				px.setIdade(rs.getDate("data_nascimento"));
				px.setNomegenero(rs.getString("genero"));
				px.setQuantidade(rs.getInt("qdt_servicos"));
				Calendar data = Calendar.getInstance();
				data.setTime(rs.getDate("data"));
				px.setData_do_agendamento(data);
				px.setData(rs.getDate("data"));
				lista.add(px);
			}
			ps.close();
			con.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return lista;
	}
	
	public int buscarIdServicoExame(String servico){
		String sql = "Select * from tblservicosdeexamesclinicos where analise_clinica = ?";
		int codSrv = 0;
		try {
			con = Conexao.getConexao();
			PreparedStatement ps = con.prepareStatement(sql);
			ps.setString(1, servico);
			ResultSet rs = ps.executeQuery();
			if(rs.next())
				codSrv = rs.getInt("id_servicodeanalise_clinica");
			con.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return codSrv;
	}
	
	public List<Paciente> buscarAgendadosConfirmadosDiario(){
		String sql = "SELECT * FROM vwexamesrequisitadoparafaturar where data = curdate() order by NomeCompleto asc, data desc Limit 50";
		List<Paciente> lista = new ArrayList<Paciente>();
		try {
			con = Conexao.getConexao();
			PreparedStatement ps = con.prepareStatement(sql);
			ResultSet rs = ps.executeQuery();
			while (rs.next()) {
				Paciente px = new Paciente();
				px.setId(rs.getInt("FK_numero_do_servico_de_exame"));
				px.setNome_paciente(rs.getString("NomeCompleto"));
				px.setNumero_processo(rs.getString("numeroProcesso"));
				px.setIdade(rs.getDate("data_nascimento"));
				px.setQuantidade(rs.getInt("qtd_servico"));
				Calendar data = Calendar.getInstance();
				data.setTime(rs.getDate("data"));
				px.setData_do_agendamento(data);
				px.setData(rs.getDate("data"));
				if(rs.getInt("FK_genero") == 1)
					px.setNomegenero("Masculino");
				if(rs.getInt("FK_genero") == 2)
					px.setNomegenero("Feminino");
				lista.add(px);
			}
			ps.close();
			con.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return lista;
	}
	
	public List<Paciente> buscarAgendadosConfirmadosEmDatas(Date data1,Date data2){
		String sql = "SELECT * FROM vwexamesrequisitadoparafaturar  where data between ? and ? order by data ";
		List<Paciente> lista = new ArrayList<Paciente>();
		try {
			con = Conexao.getConexao();
			PreparedStatement ps = con.prepareStatement(sql);
			ps.setDate(1, data1);
			ps.setDate(2, data2);
			ResultSet rs = ps.executeQuery();
			while (rs.next()) {
				Paciente px = new Paciente();
				px.setId(rs.getInt("FK_numero_do_servico_de_exame"));
				px.setNome_paciente(rs.getString("NomeCompleto"));
				px.setNumero_processo(rs.getString("numeroProcesso"));
				px.setIdade(rs.getDate("data_nascimento"));
				px.setQuantidade(rs.getInt("qtd_servico"));
				Calendar data = Calendar.getInstance();
				data.setTime(rs.getDate("data"));
				px.setData_do_agendamento(data);
				px.setData(rs.getDate("data"));
				if(rs.getInt("FK_genero") == 1)
					px.setNomegenero("Masculino");
				if(rs.getInt("FK_genero") == 2)
					px.setNomegenero("Feminino");
				lista.add(px);
			}
			ps.close();
			con.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return lista;
	}
	
	public List<Paciente> buscarAgendadosConfirmadosEmDatasNome(Date data1,Date data2,String nome){
		String sql = "SELECT * FROM vwexamesrequisitadoparafaturar  where data between ? and ? and NomeCompleto Like ? order by data ";
		List<Paciente> lista = new ArrayList<Paciente>();
		try {
			con = Conexao.getConexao();
			PreparedStatement ps = con.prepareStatement(sql);
			ps.setDate(1, data1);
			ps.setDate(2, data2);
			ps.setString(3, nome+"%");
			ResultSet rs = ps.executeQuery();
			while (rs.next()) {
				Paciente px = new Paciente();
				px.setId(rs.getInt("FK_numero_do_servico_de_exame"));
				px.setNome_paciente(rs.getString("NomeCompleto"));
				px.setNumero_processo(rs.getString("numeroProcesso"));
				px.setIdade(rs.getDate("data_nascimento"));
				px.setQuantidade(rs.getInt("qtd_servico"));
				Calendar data = Calendar.getInstance();
				data.setTime(rs.getDate("data"));
				px.setData_do_agendamento(data);
				px.setData(rs.getDate("data"));
				if(rs.getInt("FK_genero") == 1)
					px.setNomegenero("Masculino");
				if(rs.getInt("FK_genero") == 2)
					px.setNomegenero("Feminino");
				lista.add(px);
			}
			ps.close();
			con.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return lista;
	}
	
	public List<Paciente> buscarAgendadosConfirmadosEmDatasGenero(Date data1,Date data2,int genero){
		String sql = "SELECT * FROM vwexamesrequisitadoparafaturar  where data between ? and ? and FK_genero = ? order by data ";
		List<Paciente> lista = new ArrayList<Paciente>();
		try {
			con = Conexao.getConexao();
			PreparedStatement ps = con.prepareStatement(sql);
			ps.setDate(1, data1);
			ps.setDate(2, data2);
			ps.setInt(3, genero);
			ResultSet rs = ps.executeQuery();
			while (rs.next()) {
				Paciente px = new Paciente();
				px.setId(rs.getInt("FK_numero_do_servico_de_exame"));
				px.setNome_paciente(rs.getString("NomeCompleto"));
				px.setNumero_processo(rs.getString("numeroProcesso"));
				px.setIdade(rs.getDate("data_nascimento"));
				px.setQuantidade(rs.getInt("qtd_servico"));
				Calendar data = Calendar.getInstance();
				data.setTime(rs.getDate("data"));
				px.setData_do_agendamento(data);
				px.setData(rs.getDate("data"));
				if(rs.getInt("FK_genero") == 1)
					px.setNomegenero("Masculino");
				if(rs.getInt("FK_genero") == 2)
					px.setNomegenero("Feminino");
				lista.add(px);
			}
			ps.close();
			con.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return lista;
	}
	
	public List<Paciente> buscarAgendadosConfirmadosEmDatasIdadeGenero(Date data1,Date data2,int idade1,int idade2,int genero){
		String sql = "SELECT * FROM vwexamesrequisitadoparafaturar  where data between ? and year(from_days((to_days(now()) - to_days(data_nascimento)))) between ? and ? and ? and FK_genero = ? order by data ";
		List<Paciente> lista = new ArrayList<Paciente>();
		try {
			con = Conexao.getConexao();
			PreparedStatement ps = con.prepareStatement(sql);
			ps.setDate(1, data1);
			ps.setDate(2, data2);
			ps.setInt(3, idade1);
			ps.setInt(4, idade2);
			ps.setInt(5, genero);
			ResultSet rs = ps.executeQuery();
			while (rs.next()) {
				Paciente px = new Paciente();
				px.setId(rs.getInt("FK_numero_do_servico_de_exame"));
				px.setNome_paciente(rs.getString("NomeCompleto"));
				px.setNumero_processo(rs.getString("numeroProcesso"));
				px.setIdade(rs.getDate("data_nascimento"));
				px.setQuantidade(rs.getInt("qtd_servico"));
				Calendar data = Calendar.getInstance();
				data.setTime(rs.getDate("data"));
				px.setData_do_agendamento(data);
				px.setData(rs.getDate("data"));
				if(rs.getInt("FK_genero") == 1)
					px.setNomegenero("Masculino");
				if(rs.getInt("FK_genero") == 2)
					px.setNomegenero("Feminino");
				lista.add(px);
			}
			ps.close();
			con.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return lista;
	}
	
	public List<Paciente> buscarAgendadosConfirmadosEmDatasIdadeExame(Date data1,Date data2,int idade1,int idade2,int FK_exame){
		String sql = "SELECT * FROM vwexamesrequisitadoparafaturar  where data between ? and year(from_days((to_days(now()) - to_days(data_nascimento)))) between ? and ? and ? and FK_servico_exame = ? order by data ";
		List<Paciente> lista = new ArrayList<Paciente>();
		try {
			con = Conexao.getConexao();
			PreparedStatement ps = con.prepareStatement(sql);
			ps.setDate(1, data1);
			ps.setDate(2, data2);
			ps.setInt(3, idade1);
			ps.setInt(4, idade2);
			ps.setInt(5, FK_exame);
			ResultSet rs = ps.executeQuery();
			while (rs.next()) {
				Paciente px = new Paciente();
				px.setId(rs.getInt("FK_numero_do_servico_de_exame"));
				px.setNome_paciente(rs.getString("NomeCompleto"));
				px.setNumero_processo(rs.getString("numeroProcesso"));
				px.setIdade(rs.getDate("data_nascimento"));
				px.setQuantidade(rs.getInt("qtd_servico"));
				Calendar data = Calendar.getInstance();
				data.setTime(rs.getDate("data"));
				px.setData_do_agendamento(data);
				px.setData(rs.getDate("data"));
				if(rs.getInt("FK_genero") == 1)
					px.setNomegenero("Masculino");
				if(rs.getInt("FK_genero") == 2)
					px.setNomegenero("Feminino");
				lista.add(px);
			}
			ps.close();
			con.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return lista;
	}
	
	public List<Paciente> buscarAgendadosConfirmadosEmDatasIdade(Date data1,Date data2,int idade1,int idade2){
		String sql = "SELECT * FROM vwexamesrequisitadoparafaturar  where data between ? and year(from_days((to_days(now()) - to_days(data_nascimento)))) between ? and ? and ?  order by data ";
		List<Paciente> lista = new ArrayList<Paciente>();
		try {
			con = Conexao.getConexao();
			PreparedStatement ps = con.prepareStatement(sql);
			ps.setDate(1, data1);
			ps.setDate(2, data2);
			ps.setInt(3, idade1);
			ps.setInt(4, idade2);
			
			ResultSet rs = ps.executeQuery();
			while (rs.next()) {
				Paciente px = new Paciente();
				px.setId(rs.getInt("FK_numero_do_servico_de_exame"));
				px.setNome_paciente(rs.getString("NomeCompleto"));
				px.setNumero_processo(rs.getString("numeroProcesso"));
				px.setIdade(rs.getDate("data_nascimento"));
				px.setQuantidade(rs.getInt("qtd_servico"));
				Calendar data = Calendar.getInstance();
				data.setTime(rs.getDate("data"));
				px.setData_do_agendamento(data);
				px.setData(rs.getDate("data"));
				if(rs.getInt("FK_genero") == 1)
					px.setNomegenero("Masculino");
				if(rs.getInt("FK_genero") == 2)
					px.setNomegenero("Feminino");
				lista.add(px);
			}
			ps.close();
			con.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return lista;
	}
	
	public List<Paciente> buscarAgendadosConfirmadosEmDatasGeneroExame(Date data1,Date data2,int genero,int FK_exame){
		String sql = "SELECT * FROM vwexamesrequisitadoparafaturar  where data between ? and ? and FK_genero = ? and FK_servico_exame = ? order by data ";
		List<Paciente> lista = new ArrayList<Paciente>();
		try {
			con = Conexao.getConexao();
			PreparedStatement ps = con.prepareStatement(sql);
			ps.setDate(1, data1);
			ps.setDate(2, data2);
			ps.setInt(3, genero);
			ps.setInt(4, FK_exame);
			ResultSet rs = ps.executeQuery();
			while (rs.next()) {
				Paciente px = new Paciente();
				px.setId(rs.getInt("FK_numero_do_servico_de_exame"));
				px.setNome_paciente(rs.getString("NomeCompleto"));
				px.setNumero_processo(rs.getString("numeroProcesso"));
				px.setIdade(rs.getDate("data_nascimento"));
				px.setQuantidade(rs.getInt("qtd_servico"));
				Calendar data = Calendar.getInstance();
				data.setTime(rs.getDate("data"));
				px.setData_do_agendamento(data);
				px.setData(rs.getDate("data"));
				if(rs.getInt("FK_genero") == 1)
					px.setNomegenero("Masculino");
				if(rs.getInt("FK_genero") == 2)
					px.setNomegenero("Feminino");
				lista.add(px);
			}
			ps.close();
			con.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return lista;
	}
	
}
