package ao.co.cligest.negocio;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import ao.co.cligest.entidades.Diverso;
import ao.co.cligest.entidades.Paciente;

public class ConsultasExternasEstatiscaDAO {

	private Connection con;
	
	public List<Paciente> buscarHistoricoConsultasDiaria(){
		String sql = "SELECT s.descricao, s.id_servico, count(c.FK_servico) as 'qtd', COUNT(CASE WHEN g.id_genero = 1 THEN 0 END) AS 'masculino', CAST(COUNT(CASE WHEN g.id_genero = 1 THEN 0 END) / count(c.FK_servico) * 100 AS DECIMAL(10, 1)) as 'percet_m',"
				   + " COUNT(CASE WHEN g.id_genero = 2 THEN 0 END) AS 'feminino', CAST(COUNT(CASE WHEN g.id_genero = 2 THEN 0 END) / count(c.FK_servico) * 100 AS DECIMAL(10, 1)) as 'percent_f'"
				    + "FROM tblconsulta c, tblservico s, tblgenero g, tblcidadao cc WHERE c.FK_servico = s.id_servico and c.FK_paciente = cc.FK_entidade and cc.FK_genero = g.id_genero "
				    + "and c.data_daconsulta  = curdate() GROUP BY c.FK_servico";
		List<Paciente> lista = new ArrayList<Paciente>();
		try {
			con = Conexao.getConexao();
			PreparedStatement ps = con.prepareStatement(sql);
			ResultSet rs = ps.executeQuery();
			while(rs.next()){
				Paciente p = new Paciente();
				p.setDescricao(rs.getString("descricao"));
				p.setQuantidade(rs.getInt("qtd"));
				p.setMasculino(rs.getInt("masculino"));
				p.setFeminino(rs.getInt("feminino"));
				p.setPerct_fmn(rs.getDouble("percent_f"));
				p.setPerct_msc(rs.getDouble("percet_m"));
				lista.add(p);
			}
			ps.close();
			con.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return lista;
	}
	
	public List<Paciente> buscarHistoricoConsultasEmAnos(int ano){
		String sql = "SELECT s.descricao,s.id_servico, count(c.FK_servico) as 'qtd', COUNT(CASE WHEN g.id_genero = 1 THEN 0 END) AS 'masculino', CAST(COUNT(CASE WHEN g.id_genero = 1 THEN 0 END) / count(c.FK_servico) * 100 AS DECIMAL(10, 1)) as 'percet_m',"
				   + " COUNT(CASE WHEN g.id_genero = 2 THEN 0 END) AS 'feminino', CAST(COUNT(CASE WHEN g.id_genero = 2 THEN 0 END) / count(c.FK_servico) * 100 AS DECIMAL(10, 1)) as 'percent_f'"
				    + "FROM tblconsulta c, tblservico s, tblgenero g, tblcidadao cc WHERE c.FK_servico = s.id_servico and c.FK_paciente = cc.FK_entidade and cc.FK_genero = g.id_genero "
				    + "and year(c.data_daconsulta) = ? GROUP BY c.FK_servico";
		List<Paciente> lista = new ArrayList<Paciente>();
		try {
			con = Conexao.getConexao();
			PreparedStatement ps = con.prepareStatement(sql);
			ps.setInt(1, ano);
			ResultSet rs = ps.executeQuery();
			while(rs.next()){
				Paciente p = new Paciente();
				p.setDescricao(rs.getString("descricao"));
				p.setQuantidade(rs.getInt("qtd"));
				p.setMasculino(rs.getInt("masculino"));
				p.setFeminino(rs.getInt("feminino"));
				p.setPerct_fmn(rs.getDouble("percent_f"));
				p.setPerct_msc(rs.getDouble("percet_m"));
				p.setFK_especialidade(rs.getInt("id_servico"));
				lista.add(p);
			}
			ps.close();
			con.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return lista;
	}
	
	public List<Paciente> buscarHistoricoConsultasEmAnosMes(int ano,int mes){
		String sql = "SELECT s.descricao, s.id_servico, count(c.FK_servico) as 'qtd', COUNT(CASE WHEN g.id_genero = 1 THEN 0 END) AS 'masculino', CAST(COUNT(CASE WHEN g.id_genero = 1 THEN 0 END) / count(c.FK_servico) * 100 AS DECIMAL(10, 1)) as 'percet_m',"
				   + " COUNT(CASE WHEN g.id_genero = 2 THEN 0 END) AS 'feminino', CAST(COUNT(CASE WHEN g.id_genero = 2 THEN 0 END) / count(c.FK_servico) * 100 AS DECIMAL(10, 1)) as 'percent_f'"
				    + "FROM tblconsulta c, tblservico s, tblgenero g, tblcidadao cc WHERE c.FK_servico = s.id_servico and c.FK_paciente = cc.FK_entidade and cc.FK_genero = g.id_genero "
				    + "and year(c.data_daconsulta) = ? and month(c.data_daconsulta) = ? GROUP BY c.FK_servico";
		List<Paciente> lista = new ArrayList<Paciente>();
		try {
			con = Conexao.getConexao();
			PreparedStatement ps = con.prepareStatement(sql);
			ps.setInt(1, ano);
			ps.setInt(2, mes);
			ResultSet rs = ps.executeQuery();
			while(rs.next()){
				Paciente p = new Paciente();
				p.setDescricao(rs.getString("descricao"));
				p.setQuantidade(rs.getInt("qtd"));
				p.setMasculino(rs.getInt("masculino"));
				p.setFeminino(rs.getInt("feminino"));
				p.setPerct_fmn(rs.getDouble("percent_f"));
				p.setPerct_msc(rs.getDouble("percet_m"));
				p.setFK_especialidade(rs.getInt("id_servico"));
				lista.add(p);
			}
			ps.close();
			con.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return lista;
	}
	
	public List<Paciente> buscarHistoricoConsultasEmAnosSeTrimestre(int ano,int aux){
		String sql = "";
		if(aux == 1){
			sql = "SELECT s.descricao, s.id_servico, count(c.FK_servico) as 'qtd', COUNT(CASE WHEN g.id_genero = 1 THEN 0 END) AS 'masculino', CAST(COUNT(CASE WHEN g.id_genero = 1 THEN 0 END) / count(c.FK_servico) * 100 AS DECIMAL(10, 1)) as 'percet_m',"
				   + " COUNT(CASE WHEN g.id_genero = 2 THEN 0 END) AS 'feminino', CAST(COUNT(CASE WHEN g.id_genero = 2 THEN 0 END) / count(c.FK_servico) * 100 AS DECIMAL(10, 1)) as 'percent_f'"
					    + "FROM tblconsulta c, tblservico s, tblgenero g, tblcidadao cc WHERE c.FK_servico = s.id_servico and c.FK_paciente = cc.FK_entidade and cc.FK_genero = g.id_genero "
					    + "and year(c.data_daconsulta) = ? and month(c.data_daconsulta) between 1 and 3 GROUP BY c.FK_servico";
		}
		if(aux == 2){
			sql = "SELECT s.descricao, s.id_servico, count(c.FK_servico) as 'qtd', COUNT(CASE WHEN g.id_genero = 1 THEN 0 END) AS 'masculino', CAST(COUNT(CASE WHEN g.id_genero = 1 THEN 0 END) / count(c.FK_servico) * 100 AS DECIMAL(10, 1)) as 'percet_m',"
				   + " COUNT(CASE WHEN g.id_genero = 2 THEN 0 END) AS 'feminino', CAST(COUNT(CASE WHEN g.id_genero = 2 THEN 0 END) / count(c.FK_servico) * 100 AS DECIMAL(10, 1)) as 'percent_f'"
					    + "FROM tblconsulta c, tblservico s, tblgenero g, tblcidadao cc WHERE c.FK_servico = s.id_servico and c.FK_paciente = cc.FK_entidade and cc.FK_genero = g.id_genero "
					    + "and year(c.data_daconsulta) = ? and month(c.data_daconsulta) between 4 and 6 GROUP BY c.FK_servico";
		}
		if(aux == 3){
			sql = "SELECT s.descricao, s.id_servico, count(c.FK_servico) as 'qtd', COUNT(CASE WHEN g.id_genero = 1 THEN 0 END) AS 'masculino', CAST(COUNT(CASE WHEN g.id_genero = 1 THEN 0 END) / count(c.FK_servico) * 100 AS DECIMAL(10, 1)) as 'percet_m',"
				   + " COUNT(CASE WHEN g.id_genero = 2 THEN 0 END) AS 'feminino', CAST(COUNT(CASE WHEN g.id_genero = 2 THEN 0 END) / count(c.FK_servico) * 100 AS DECIMAL(10, 1)) as 'percent_f'"
					    + "FROM tblconsulta c, tblservico s, tblgenero g, tblcidadao cc WHERE c.FK_servico = s.id_servico and c.FK_paciente = cc.FK_entidade and cc.FK_genero = g.id_genero "
					    + "and year(c.data_daconsulta) = ? and month(c.data_daconsulta) between 7 and 9 GROUP BY c.FK_servico";
		}
		if(aux == 4){
			sql = "SELECT s.descricao, s.id_servico, count(c.FK_servico) as 'qtd', COUNT(CASE WHEN g.id_genero = 1 THEN 0 END) AS 'masculino', CAST(COUNT(CASE WHEN g.id_genero = 1 THEN 0 END) / count(c.FK_servico) * 100 AS DECIMAL(10, 1)) as 'percet_m',"
				   + " COUNT(CASE WHEN g.id_genero = 2 THEN 0 END) AS 'feminino', CAST(COUNT(CASE WHEN g.id_genero = 2 THEN 0 END) / count(c.FK_servico) * 100 AS DECIMAL(10, 1)) as 'percent_f'"
					    + "FROM tblconsulta c, tblservico s, tblgenero g, tblcidadao cc WHERE c.FK_servico = s.id_servico and c.FK_paciente = cc.FK_entidade and cc.FK_genero = g.id_genero "
					    + "and year(c.data_daconsulta) = ? and month(c.data_daconsulta) between 10 and 12 GROUP BY c.FK_servico";
		}
		if(aux == 5){
			sql = "SELECT s.descricao, s.id_servico, count(c.FK_servico) as 'qtd', COUNT(CASE WHEN g.id_genero = 1 THEN 0 END) AS 'masculino', CAST(COUNT(CASE WHEN g.id_genero = 1 THEN 0 END) / count(c.FK_servico) * 100 AS DECIMAL(10, 1)) as 'percet_m',"
				   + " COUNT(CASE WHEN g.id_genero = 2 THEN 0 END) AS 'feminino', CAST(COUNT(CASE WHEN g.id_genero = 2 THEN 0 END) / count(c.FK_servico) * 100 AS DECIMAL(10, 1)) as 'percent_f'"
					    + "FROM tblconsulta c, tblservico s, tblgenero g, tblcidadao cc WHERE c.FK_servico = s.id_servico and c.FK_paciente = cc.FK_entidade and cc.FK_genero = g.id_genero "
					    + "and year(c.data_daconsulta) = ? and month(c.data_daconsulta) between 1 and 6 GROUP BY c.FK_servico";
		}
		if(aux == 6){
			sql = "SELECT s.descricao, s.id_servico, count(c.FK_servico) as 'qtd', COUNT(CASE WHEN g.id_genero = 1 THEN 0 END) AS 'masculino', CAST(COUNT(CASE WHEN g.id_genero = 1 THEN 0 END) / count(c.FK_servico) * 100 AS DECIMAL(10, 1)) as 'percet_m',"
				   + " COUNT(CASE WHEN g.id_genero = 2 THEN 0 END) AS 'feminino', CAST(COUNT(CASE WHEN g.id_genero = 2 THEN 0 END) / count(c.FK_servico) * 100 AS DECIMAL(10, 1)) as 'percent_f'"
					    + "FROM tblconsulta c, tblservico s, tblgenero g, tblcidadao cc WHERE c.FK_servico = s.id_servico and c.FK_paciente = cc.FK_entidade and cc.FK_genero = g.id_genero "
					    + "and year(c.data_daconsulta) = ? and month(c.data_daconsulta) between 7 and 12 GROUP BY c.FK_servico";
		}
		List<Paciente> lista = new ArrayList<Paciente>();
		try {
			con = Conexao.getConexao();
			PreparedStatement ps = con.prepareStatement(sql);
			ps.setInt(1, ano);
			ResultSet rs = ps.executeQuery();
			while(rs.next()){
				Paciente p = new Paciente();
				p.setDescricao(rs.getString("descricao"));
				p.setQuantidade(rs.getInt("qtd"));
				p.setMasculino(rs.getInt("masculino"));
				p.setFeminino(rs.getInt("feminino"));
				p.setPerct_fmn(rs.getDouble("percent_f"));
				p.setPerct_msc(rs.getDouble("percet_m"));
				p.setFK_especialidade(rs.getInt("id_servico"));
				lista.add(p);
			}
			ps.close();
			con.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return lista;
	}
	
	public List<Paciente> buscarHistoricoConsultasEmAnosSeTrimestreIdades(int ano,int aux, int idade1,int idade2){
		String sql = "";
		if(aux == 1){
			sql = "SELECT s.descricao, s.id_servico, count(c.FK_servico) as 'qtd', COUNT(CASE WHEN g.id_genero = 1 THEN 0 END) AS 'masculino', CAST(COUNT(CASE WHEN g.id_genero = 1 THEN 0 END) / count(c.FK_servico) * 100 AS DECIMAL(10, 1)) as 'percet_m',"
				   + " COUNT(CASE WHEN g.id_genero = 2 THEN 0 END) AS 'feminino', CAST(COUNT(CASE WHEN g.id_genero = 2 THEN 0 END) / count(c.FK_servico) * 100 AS DECIMAL(10, 1)) as 'percent_f'"
					    + "FROM tblconsulta c, tblservico s, tblgenero g, tblcidadao cc WHERE c.FK_servico = s.id_servico and c.FK_paciente = cc.FK_entidade and cc.FK_genero = g.id_genero "
					    + "and year(c.data_daconsulta) = ? and month(c.data_daconsulta) between 1 and 3 and year(from_days((to_days(now()) - to_days(cc.data_nascimento)))) between ? and ? GROUP BY c.FK_servico";
		}
		if(aux == 2){
			sql = "SELECT s.descricao, s.id_servico, count(c.FK_servico) as 'qtd', COUNT(CASE WHEN g.id_genero = 1 THEN 0 END) AS 'masculino', CAST(COUNT(CASE WHEN g.id_genero = 1 THEN 0 END) / count(c.FK_servico) * 100 AS DECIMAL(10, 1)) as 'percet_m',"
				   + " COUNT(CASE WHEN g.id_genero = 2 THEN 0 END) AS 'feminino', CAST(COUNT(CASE WHEN g.id_genero = 2 THEN 0 END) / count(c.FK_servico) * 100 AS DECIMAL(10, 1)) as 'percent_f'"
					    + "FROM tblconsulta c, tblservico s, tblgenero g, tblcidadao cc WHERE c.FK_servico = s.id_servico and c.FK_paciente = cc.FK_entidade and cc.FK_genero = g.id_genero "
					    + "and year(c.data_daconsulta) = ? and month(c.data_daconsulta) between 4 and 6  and year(from_days((to_days(now()) - to_days(cc.data_nascimento)))) between ? and ? GROUP BY c.FK_servico";
		}
		if(aux == 3){
			sql = "SELECT s.descricao, s.id_servico, count(c.FK_servico) as 'qtd', COUNT(CASE WHEN g.id_genero = 1 THEN 0 END) AS 'masculino', CAST(COUNT(CASE WHEN g.id_genero = 1 THEN 0 END) / count(c.FK_servico) * 100 AS DECIMAL(10, 1)) as 'percet_m',"
				   + " COUNT(CASE WHEN g.id_genero = 2 THEN 0 END) AS 'feminino', CAST(COUNT(CASE WHEN g.id_genero = 2 THEN 0 END) / count(c.FK_servico) * 100 AS DECIMAL(10, 1)) as 'percent_f'"
					    + "FROM tblconsulta c, tblservico s, tblgenero g, tblcidadao cc WHERE c.FK_servico = s.id_servico and c.FK_paciente = cc.FK_entidade and cc.FK_genero = g.id_genero "
					    + "and year(c.data_daconsulta) = ? and month(c.data_daconsulta) between 7 and 9 and year(from_days((to_days(now()) - to_days(cc.data_nascimento)))) between ? and ? GROUP BY c.FK_servico";
		}
		if(aux == 4){
			sql = "SELECT s.descricao, s.id_servico, count(c.FK_servico) as 'qtd', COUNT(CASE WHEN g.id_genero = 1 THEN 0 END) AS 'masculino', CAST(COUNT(CASE WHEN g.id_genero = 1 THEN 0 END) / count(c.FK_servico) * 100 AS DECIMAL(10, 1)) as 'percet_m',"
				   + " COUNT(CASE WHEN g.id_genero = 2 THEN 0 END) AS 'feminino', CAST(COUNT(CASE WHEN g.id_genero = 2 THEN 0 END) / count(c.FK_servico) * 100 AS DECIMAL(10, 1)) as 'percent_f'"
					    + "FROM tblconsulta c, tblservico s, tblgenero g, tblcidadao cc WHERE c.FK_servico = s.id_servico and c.FK_paciente = cc.FK_entidade and cc.FK_genero = g.id_genero "
					    + "and year(c.data_daconsulta) = ? and month(c.data_daconsulta) between 10 and 12 and year(from_days((to_days(now()) - to_days(cc.data_nascimento)))) between ? and ? GROUP BY c.FK_servico";
		}
		if(aux == 5){
			sql = "SELECT s.descricao, s.id_servico, count(c.FK_servico) as 'qtd', COUNT(CASE WHEN g.id_genero = 1 THEN 0 END) AS 'masculino', CAST(COUNT(CASE WHEN g.id_genero = 1 THEN 0 END) / count(c.FK_servico) * 100 AS DECIMAL(10, 1)) as 'percet_m',"
				   + " COUNT(CASE WHEN g.id_genero = 2 THEN 0 END) AS 'feminino', CAST(COUNT(CASE WHEN g.id_genero = 2 THEN 0 END) / count(c.FK_servico) * 100 AS DECIMAL(10, 1)) as 'percent_f'"
					    + "FROM tblconsulta c, tblservico s, tblgenero g, tblcidadao cc WHERE c.FK_servico = s.id_servico and c.FK_paciente = cc.FK_entidade and cc.FK_genero = g.id_genero "
					    + "and year(c.data_daconsulta) = ? and month(c.data_daconsulta) between 1 and 6 and year(from_days((to_days(now()) - to_days(cc.data_nascimento)))) between ? and ? GROUP BY c.FK_servico";
		}
		if(aux == 6){
			sql = "SELECT s.descricao, s.id_servico, count(c.FK_servico) as 'qtd', COUNT(CASE WHEN g.id_genero = 1 THEN 0 END) AS 'masculino', CAST(COUNT(CASE WHEN g.id_genero = 1 THEN 0 END) / count(c.FK_servico) * 100 AS DECIMAL(10, 1)) as 'percet_m',"
				   + " COUNT(CASE WHEN g.id_genero = 2 THEN 0 END) AS 'feminino', CAST(COUNT(CASE WHEN g.id_genero = 2 THEN 0 END) / count(c.FK_servico) * 100 AS DECIMAL(10, 1)) as 'percent_f'"
					    + "FROM tblconsulta c, tblservico s, tblgenero g, tblcidadao cc WHERE c.FK_servico = s.id_servico and c.FK_paciente = cc.FK_entidade and cc.FK_genero = g.id_genero "
					    + "and year(c.data_daconsulta) = ? and month(c.data_daconsulta) between 7 and 12 and year(from_days((to_days(now()) - to_days(cc.data_nascimento)))) between ? and ? GROUP BY c.FK_servico";
		}
		List<Paciente> lista = new ArrayList<Paciente>();
		try {
			con = Conexao.getConexao();
			PreparedStatement ps = con.prepareStatement(sql);
			ps.setInt(1, ano);
			ps.setInt(2, idade1);
			ps.setInt(3, idade2);
			ResultSet rs = ps.executeQuery();
			while(rs.next()){
				Paciente p = new Paciente();
				p.setDescricao(rs.getString("descricao"));
				p.setQuantidade(rs.getInt("qtd"));
				p.setMasculino(rs.getInt("masculino"));
				p.setFeminino(rs.getInt("feminino"));
				p.setPerct_fmn(rs.getDouble("percent_f"));
				p.setPerct_msc(rs.getDouble("percet_m"));
				p.setFK_especialidade(rs.getInt("id_servico"));
				lista.add(p);
			}
			ps.close();
			con.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return lista;
	}
	
	public List<Paciente> buscarHistoricoConsultasEmAnosMesDias(int ano,int mes,int dia1,int dia2){
		String sql = "SELECT s.descricao, s.id_servico, count(c.FK_servico) as 'qtd', COUNT(CASE WHEN g.id_genero = 1 THEN 0 END) AS 'masculino', CAST(COUNT(CASE WHEN g.id_genero = 1 THEN 0 END) / count(c.FK_servico) * 100 AS DECIMAL(10, 1)) as 'percet_m',"
				   + " COUNT(CASE WHEN g.id_genero = 2 THEN 0 END) AS 'feminino', CAST(COUNT(CASE WHEN g.id_genero = 2 THEN 0 END) / count(c.FK_servico) * 100 AS DECIMAL(10, 1)) as 'percent_f'"
				    + "FROM tblconsulta c, tblservico s, tblgenero g, tblcidadao cc WHERE c.FK_servico = s.id_servico and c.FK_paciente = cc.FK_entidade and cc.FK_genero = g.id_genero "
				    + "and year(c.data_daconsulta) = ? and month(c.data_daconsulta) = ? and day(c.data_daconsulta) between ? and ? GROUP BY c.FK_servico";
		List<Paciente> lista = new ArrayList<Paciente>();
		try {
			con = Conexao.getConexao();
			PreparedStatement ps = con.prepareStatement(sql);
			ps.setInt(1, ano);
			ps.setInt(2, mes);
			ps.setInt(3, dia1);
			ps.setInt(4, dia2);
			ResultSet rs = ps.executeQuery();
			while(rs.next()){
				Paciente p = new Paciente();
				p.setDescricao(rs.getString("descricao"));
				p.setQuantidade(rs.getInt("qtd"));
				p.setMasculino(rs.getInt("masculino"));
				p.setFeminino(rs.getInt("feminino"));
				p.setPerct_fmn(rs.getDouble("percent_f"));
				p.setPerct_msc(rs.getDouble("percet_m"));
				p.setFK_especialidade(rs.getInt("id_servico"));
				lista.add(p);
			}
			ps.close();
			con.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return lista;
	}
	
	public List<Paciente> buscarHistoricoConsultasEmIdades(int idade1,int idade2){
		String sql = "SELECT s.descricao,s.id_servico, count(c.FK_servico) as 'qtd', COUNT(CASE WHEN g.id_genero = 1 THEN 0 END) AS 'masculino', CAST(COUNT(CASE WHEN g.id_genero = 1 THEN 0 END) / count(c.FK_servico) * 100 AS DECIMAL(10, 1)) as 'percet_m',"
				   + " COUNT(CASE WHEN g.id_genero = 2 THEN 0 END) AS 'feminino', CAST(COUNT(CASE WHEN g.id_genero = 2 THEN 0 END) / count(c.FK_servico) * 100 AS DECIMAL(10, 1)) as 'percent_f'"
				    + "FROM tblconsulta c, tblservico s, tblgenero g, tblcidadao cc WHERE c.FK_servico = s.id_servico and c.FK_paciente = cc.FK_entidade and cc.FK_genero = g.id_genero "
				    + "and year(from_days((to_days(now()) - to_days(cc.data_nascimento)))) between ? and ?  GROUP BY c.FK_servico";
		List<Paciente> lista = new ArrayList<Paciente>();
		try {
			con = Conexao.getConexao();
			PreparedStatement ps = con.prepareStatement(sql);
			ps.setInt(1, idade1);
			ps.setInt(2, idade2);
			ResultSet rs = ps.executeQuery();
			while(rs.next()){
				Paciente p = new Paciente();
				p.setDescricao(rs.getString("descricao"));
				p.setQuantidade(rs.getInt("qtd"));
				p.setMasculino(rs.getInt("masculino"));
				p.setFeminino(rs.getInt("feminino"));
				p.setPerct_fmn(rs.getDouble("percent_f"));
				p.setPerct_msc(rs.getDouble("percet_m"));
				p.setFK_especialidade(rs.getInt("id_servico"));
				lista.add(p);
			}
			ps.close();
			con.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return lista;
	}
	
	public List<Paciente> buscarHistoricoConsultasEmAnosIdades(int ano,int idade1,int idade2){
		String sql = "SELECT s.descricao,s.id_servico, count(c.FK_servico) as 'qtd', COUNT(CASE WHEN g.id_genero = 1 THEN 0 END) AS 'masculino', CAST(COUNT(CASE WHEN g.id_genero = 1 THEN 0 END) / count(c.FK_servico) * 100 AS DECIMAL(10, 1)) as 'percet_m',"
				   + " COUNT(CASE WHEN g.id_genero = 2 THEN 0 END) AS 'feminino', CAST(COUNT(CASE WHEN g.id_genero = 2 THEN 0 END) / count(c.FK_servico) * 100 AS DECIMAL(10, 1)) as 'percent_f'"
				    + "FROM tblconsulta c, tblservico s, tblgenero g, tblcidadao cc WHERE c.FK_servico = s.id_servico and c.FK_paciente = cc.FK_entidade and cc.FK_genero = g.id_genero "
				    + "and year(c.data_daconsulta) = ? and year(from_days((to_days(now()) - to_days(cc.data_nascimento)))) between ? and ? GROUP BY c.FK_servico";
		List<Paciente> lista = new ArrayList<Paciente>();
		try {
			con = Conexao.getConexao();
			PreparedStatement ps = con.prepareStatement(sql);
			ps.setInt(1, ano);
			ps.setInt(2, idade1);
			ps.setInt(3, idade2);
			ResultSet rs = ps.executeQuery();
			while(rs.next()){
				Paciente p = new Paciente();
				p.setDescricao(rs.getString("descricao"));
				p.setQuantidade(rs.getInt("qtd"));
				p.setMasculino(rs.getInt("masculino"));
				p.setFeminino(rs.getInt("feminino"));
				p.setPerct_fmn(rs.getDouble("percent_f"));
				p.setPerct_msc(rs.getDouble("percet_m"));
				p.setFK_especialidade(rs.getInt("id_servico"));
				lista.add(p);
			}
			ps.close();
			con.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return lista;
	}
	
	public List<Paciente> buscarHistoricoConsultasEmAnosMesIdade(int ano,int mes,int idade1,int idade2){
		String sql = "SELECT s.descricao, s.id_servico, count(c.FK_servico) as 'qtd', COUNT(CASE WHEN g.id_genero = 1 THEN 0 END) AS 'masculino', CAST(COUNT(CASE WHEN g.id_genero = 1 THEN 0 END) / count(c.FK_servico) * 100 AS DECIMAL(10, 1)) as 'percet_m',"
				   + " COUNT(CASE WHEN g.id_genero = 2 THEN 0 END) AS 'feminino', CAST(COUNT(CASE WHEN g.id_genero = 2 THEN 0 END) / count(c.FK_servico) * 100 AS DECIMAL(10, 1)) as 'percent_f'"
				    + "FROM tblconsulta c, tblservico s, tblgenero g, tblcidadao cc WHERE c.FK_servico = s.id_servico and c.FK_paciente = cc.FK_entidade and cc.FK_genero = g.id_genero "
				    + "and year(c.data_daconsulta) = ? and month(c.data_daconsulta) = ? and year(from_days((to_days(now()) - to_days(cc.data_nascimento)))) between ? and ? GROUP BY c.FK_servico";
		List<Paciente> lista = new ArrayList<Paciente>();
		try {
			con = Conexao.getConexao();
			PreparedStatement ps = con.prepareStatement(sql);
			ps.setInt(1, ano);
			ps.setInt(2, mes);
			ps.setInt(3, idade1);
			ps.setInt(4, idade2);
			ResultSet rs = ps.executeQuery();
			while(rs.next()){
				Paciente p = new Paciente();
				p.setDescricao(rs.getString("descricao"));
				p.setQuantidade(rs.getInt("qtd"));
				p.setMasculino(rs.getInt("masculino"));
				p.setFeminino(rs.getInt("feminino"));
				p.setPerct_fmn(rs.getDouble("percent_f"));
				p.setPerct_msc(rs.getDouble("percet_m"));
				p.setFK_especialidade(rs.getInt("id_servico"));
				lista.add(p);
			}
			ps.close();
			con.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return lista;
	}
	
	public List<Paciente> buscarHistoricoConsultasEmAnosMesDiasIdades(int ano,int mes,int dia1,int dia2,int idade1,int idade2){
		String sql = "SELECT s.descricao, s.id_servico, count(c.FK_servico) as 'qtd', COUNT(CASE WHEN g.id_genero = 1 THEN 0 END) AS 'masculino', CAST(COUNT(CASE WHEN g.id_genero = 1 THEN 0 END) / count(c.FK_servico) * 100 AS DECIMAL(10, 1)) as 'percet_m',"
				   + " COUNT(CASE WHEN g.id_genero = 2 THEN 0 END) AS 'feminino', CAST(COUNT(CASE WHEN g.id_genero = 2 THEN 0 END) / count(c.FK_servico) * 100 AS DECIMAL(10, 1)) as 'percent_f'"
				    + "FROM tblconsulta c, tblservico s, tblgenero g, tblcidadao cc WHERE c.FK_servico = s.id_servico and c.FK_paciente = cc.FK_entidade and cc.FK_genero = g.id_genero "
				    + "and year(c.data_daconsulta) = ? and month(c.data_daconsulta) = ? and day(c.data_daconsulta) between ? and ? and year(from_days((to_days(now()) - to_days(cc.data_nascimento)))) between ? and ?  GROUP BY c.FK_servico";
		List<Paciente> lista = new ArrayList<Paciente>();
		try {
			con = Conexao.getConexao();
			PreparedStatement ps = con.prepareStatement(sql);
			ps.setInt(1, ano);
			ps.setInt(2, mes);
			ps.setInt(3, dia1);
			ps.setInt(4, dia2);
			ps.setInt(5, idade1);
			ps.setInt(6, idade2);
			ResultSet rs = ps.executeQuery();
			while(rs.next()){
				Paciente p = new Paciente();
				p.setDescricao(rs.getString("descricao"));
				p.setQuantidade(rs.getInt("qtd"));
				p.setMasculino(rs.getInt("masculino"));
				p.setFeminino(rs.getInt("feminino"));
				p.setPerct_fmn(rs.getDouble("percent_f"));
				p.setPerct_msc(rs.getDouble("percet_m"));
				p.setFK_especialidade(rs.getInt("id_servico"));
				lista.add(p);
			}
			ps.close();
			con.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return lista;
	}
	
	public List<Diverso> buscarEspecialidadeEmConsultas(){
		String sql = "SELECT s.descricao, s.id_servico FROM tblconsulta c, tblservico s, tblgenero g, tblcidadao cc WHERE c.FK_servico = s.id_servico and c.FK_paciente = cc.FK_entidade and cc.FK_genero = g.id_genero GROUP BY c.FK_servico";
		List<Diverso> lista = new ArrayList<Diverso>();
		try {
			con = Conexao.getConexao();
			PreparedStatement ps = con.prepareStatement(sql);
			ResultSet rs = ps.executeQuery();
			while(rs.next()){
				Diverso d = new Diverso();
				d.setNomeEsp(rs.getString("descricao"));
				d.setEspecialidade(rs.getInt("id_servico"));
				lista.add(d);
			}
			ps.close();
			con.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return lista;
	}
	
	public List<Diverso> buscarEspecialidadeEmSessoes(){
		String sql = "SELECT s.descricao, s.id_servico, count(ft.FK_tratamento) as 'qtd' FROM tblfacturartratamento ft, tblfactura f, tblservico s, tblcidadao cc, tblgenero g WHERE ft.FK_factura = f.id_factura and ft.FK_tratamento = s.id_servico and f.FK_paciente = cc.FK_entidade and cc.FK_genero = g.id_genero  GROUP BY ft.FK_tratamento";
		List<Diverso> lista = new ArrayList<Diverso>();
		try {
			con = Conexao.getConexao();
			PreparedStatement ps = con.prepareStatement(sql);
			ResultSet rs = ps.executeQuery();
			while(rs.next()){
				Diverso d = new Diverso();
				d.setNomeEsp(rs.getString("descricao"));
				d.setEspecialidade(rs.getInt("id_servico"));
				lista.add(d);
			}
			ps.close();
			con.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return lista;
	}
	
	public List<Paciente> buscarHistoricoSessoesDiaria(){
		String sql = "SELECT s.descricao, s.id_servico, count(ft.FK_tratamento) as 'qtd',     COUNT(CASE WHEN g.id_genero = 1 THEN 0 END) AS 'masculino',   CAST(COUNT(CASE WHEN g.id_genero = 1 THEN 0 END) * 100 / count(ft.FK_tratamento)AS DECIMAL(10, 1)) as 'percet_m',"
				   + "COUNT(CASE WHEN g.id_genero = 2 THEN 0 END) AS 'feminino',  CAST(COUNT(CASE WHEN g.id_genero = 2 THEN 0 END) * 100 / count(ft.FK_tratamento)AS DECIMAL(10, 1)) as 'percent_f'"
				   + "FROM tblfacturartratamento ft, tblfactura f, tblservico s, tblcidadao cc, tblgenero g WHERE ft.FK_factura = f.id_factura and ft.FK_tratamento = s.id_servico and f.FK_paciente = cc.FK_entidade and cc.FK_genero = g.id_genero "
				   + "GROUP BY ft.FK_tratamento";
		List<Paciente> lista = new ArrayList<Paciente>();
		try {
			con = Conexao.getConexao();
			PreparedStatement ps = con.prepareStatement(sql);
			ResultSet rs = ps.executeQuery();
			while(rs.next()){
				Paciente p = new Paciente();
				p.setDescricao(rs.getString("descricao"));
				p.setQuantidade(rs.getInt("qtd"));
				p.setMasculino(rs.getInt("masculino"));
				p.setFeminino(rs.getInt("feminino"));
				p.setPerct_fmn(rs.getDouble("percent_f"));
				p.setPerct_msc(rs.getDouble("percet_m"));
				p.setFK_especialidade(rs.getInt("id_servico"));
				lista.add(p);
			}
			ps.close();
			con.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return lista;
	}
	
	public List<Paciente> buscarHistoricoSessoesEmAnos(int ano){
		String sql = "SELECT s.descricao, s.id_servico, count(ft.FK_tratamento) as 'qtd',     COUNT(CASE WHEN g.id_genero = 1 THEN 0 END) AS 'masculino',   CAST(COUNT(CASE WHEN g.id_genero = 1 THEN 0 END) * 100 / count(ft.FK_tratamento)AS DECIMAL(10, 1)) as 'percet_m',"
				   + "COUNT(CASE WHEN g.id_genero = 2 THEN 0 END) AS 'feminino',  CAST(COUNT(CASE WHEN g.id_genero = 2 THEN 0 END) * 100 / count(ft.FK_tratamento)AS DECIMAL(10, 1)) as 'percent_f'"
				   + "FROM tblfacturartratamento ft, tblfactura f, tblservico s, tblcidadao cc, tblgenero g WHERE ft.FK_factura = f.id_factura and ft.FK_tratamento = s.id_servico and f.FK_paciente = cc.FK_entidade and cc.FK_genero = g.id_genero "
				   + "and year(f.Data_da_facturacao) = ? GROUP BY ft.FK_tratamento";
		List<Paciente> lista = new ArrayList<Paciente>();
		try {
			con = Conexao.getConexao();
			PreparedStatement ps = con.prepareStatement(sql);
			ps.setInt(1, ano);
			ResultSet rs = ps.executeQuery();
			while(rs.next()){
				Paciente p = new Paciente();
				p.setDescricao(rs.getString("descricao"));
				p.setQuantidade(rs.getInt("qtd"));
				p.setMasculino(rs.getInt("masculino"));
				p.setFeminino(rs.getInt("feminino"));
				p.setPerct_fmn(rs.getDouble("percent_f"));
				p.setPerct_msc(rs.getDouble("percet_m"));
				p.setFK_especialidade(rs.getInt("id_servico"));
				lista.add(p);
			}
			ps.close();
			con.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return lista;
	}
	
	public List<Paciente> buscarHistoricoSessoesEmAnosMes(int ano,int mes){
		String sql = "SELECT s.descricao, s.id_servico, count(ft.FK_tratamento) as 'qtd',     COUNT(CASE WHEN g.id_genero = 1 THEN 0 END) AS 'masculino',   CAST(COUNT(CASE WHEN g.id_genero = 1 THEN 0 END) * 100 / count(ft.FK_tratamento)AS DECIMAL(10, 1)) as 'percet_m',"
				   + "COUNT(CASE WHEN g.id_genero = 2 THEN 0 END) AS 'feminino',  CAST(COUNT(CASE WHEN g.id_genero = 2 THEN 0 END) * 100 / count(ft.FK_tratamento)AS DECIMAL(10, 1)) as 'percent_f'"
				   + "FROM tblfacturartratamento ft, tblfactura f, tblservico s, tblcidadao cc, tblgenero g WHERE ft.FK_factura = f.id_factura and ft.FK_tratamento = s.id_servico and f.FK_paciente = cc.FK_entidade and cc.FK_genero = g.id_genero "
				   + "and year(f.Data_da_facturacao) = ? and  month(f.Data_da_facturacao) = ? GROUP BY ft.FK_tratamento";
		List<Paciente> lista = new ArrayList<Paciente>();
		try {
			con = Conexao.getConexao();
			PreparedStatement ps = con.prepareStatement(sql);
			ps.setInt(1, ano);
			ps.setInt(2, mes);
			ResultSet rs = ps.executeQuery();
			while(rs.next()){
				Paciente p = new Paciente();
				p.setDescricao(rs.getString("descricao"));
				p.setQuantidade(rs.getInt("qtd"));
				p.setMasculino(rs.getInt("masculino"));
				p.setFeminino(rs.getInt("feminino"));
				p.setPerct_fmn(rs.getDouble("percent_f"));
				p.setPerct_msc(rs.getDouble("percet_m"));
				p.setFK_especialidade(rs.getInt("id_servico"));
				lista.add(p);
			}
			ps.close();
			con.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return lista;
	}
	
	public List<Paciente> buscarHistoricoSessoesEmAnosMesDias(int ano,int mes,int dia1,int dia2){
		String sql = "SELECT s.descricao, s.id_servico, count(ft.FK_tratamento) as 'qtd',     COUNT(CASE WHEN g.id_genero = 1 THEN 0 END) AS 'masculino',   CAST(COUNT(CASE WHEN g.id_genero = 1 THEN 0 END) * 100 / count(ft.FK_tratamento)AS DECIMAL(10, 1)) as 'percet_m',"
				   + "COUNT(CASE WHEN g.id_genero = 2 THEN 0 END) AS 'feminino',  CAST(COUNT(CASE WHEN g.id_genero = 2 THEN 0 END) * 100 / count(ft.FK_tratamento)AS DECIMAL(10, 1)) as 'percent_f'"
				   + "FROM tblfacturartratamento ft, tblfactura f, tblservico s, tblcidadao cc, tblgenero g WHERE ft.FK_factura = f.id_factura and ft.FK_tratamento = s.id_servico and f.FK_paciente = cc.FK_entidade and cc.FK_genero = g.id_genero "
				   + "and year(f.Data_da_facturacao) = ? and  month(f.Data_da_facturacao) = ? and day(f.Data_da_facturacao) between ? and ? GROUP BY ft.FK_tratamento";
		List<Paciente> lista = new ArrayList<Paciente>();
		try {
			con = Conexao.getConexao();
			PreparedStatement ps = con.prepareStatement(sql);
			ps.setInt(1, ano);
			ps.setInt(2, mes);
			ps.setInt(3, dia1);
			ps.setInt(4, dia2);
			ResultSet rs = ps.executeQuery();
			while(rs.next()){
				Paciente p = new Paciente();
				p.setDescricao(rs.getString("descricao"));
				p.setQuantidade(rs.getInt("qtd"));
				p.setMasculino(rs.getInt("masculino"));
				p.setFeminino(rs.getInt("feminino"));
				p.setPerct_fmn(rs.getDouble("percent_f"));
				p.setPerct_msc(rs.getDouble("percet_m"));
				p.setFK_especialidade(rs.getInt("id_servico"));
				lista.add(p);
			}
			ps.close();
			con.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return lista;
	}
	
	public List<Paciente> buscarHistoricoSessoesEmAnosSeTrimestre(int ano,int aux){
		String sql = "";
		
		if(aux == 1){
			sql = "SELECT s.descricao, s.id_servico, count(ft.FK_tratamento) as 'qtd',     COUNT(CASE WHEN g.id_genero = 1 THEN 0 END) AS 'masculino',   CAST(COUNT(CASE WHEN g.id_genero = 1 THEN 0 END) * 100 / count(ft.FK_tratamento)AS DECIMAL(10, 1)) as 'percet_m',"
					   + "COUNT(CASE WHEN g.id_genero = 2 THEN 0 END) AS 'feminino',  CAST(COUNT(CASE WHEN g.id_genero = 2 THEN 0 END) * 100 / count(ft.FK_tratamento)AS DECIMAL(10, 1)) as 'percent_f'"
					   + "FROM tblfacturartratamento ft, tblfactura f, tblservico s, tblcidadao cc, tblgenero g WHERE ft.FK_factura = f.id_factura and ft.FK_tratamento = s.id_servico and f.FK_paciente = cc.FK_entidade and cc.FK_genero = g.id_genero "
					   + "and year(f.Data_da_facturacao) = ? and month(f.Data_da_facturacao) between 1 and 3 GROUP BY ft.FK_tratamento";
		}
		if(aux == 2){
			sql = "SELECT s.descricao, s.id_servico, count(ft.FK_tratamento) as 'qtd',     COUNT(CASE WHEN g.id_genero = 1 THEN 0 END) AS 'masculino',   CAST(COUNT(CASE WHEN g.id_genero = 1 THEN 0 END) * 100 / count(ft.FK_tratamento)AS DECIMAL(10, 1)) as 'percet_m',"
					   + "COUNT(CASE WHEN g.id_genero = 2 THEN 0 END) AS 'feminino',  CAST(COUNT(CASE WHEN g.id_genero = 2 THEN 0 END) * 100 / count(ft.FK_tratamento)AS DECIMAL(10, 1)) as 'percent_f'"
					   + "FROM tblfacturartratamento ft, tblfactura f, tblservico s, tblcidadao cc, tblgenero g WHERE ft.FK_factura = f.id_factura and ft.FK_tratamento = s.id_servico and f.FK_paciente = cc.FK_entidade and cc.FK_genero = g.id_genero "
					   + "and year(f.Data_da_facturacao) = ? and month(f.Data_da_facturacao) between 4 and 6 GROUP BY ft.FK_tratamento";
		}

		if(aux == 3){
			sql = "SELECT s.descricao, s.id_servico, count(ft.FK_tratamento) as 'qtd',     COUNT(CASE WHEN g.id_genero = 1 THEN 0 END) AS 'masculino',   CAST(COUNT(CASE WHEN g.id_genero = 1 THEN 0 END) * 100 / count(ft.FK_tratamento)AS DECIMAL(10, 1)) as 'percet_m',"
					   + "COUNT(CASE WHEN g.id_genero = 2 THEN 0 END) AS 'feminino',  CAST(COUNT(CASE WHEN g.id_genero = 2 THEN 0 END) * 100 / count(ft.FK_tratamento)AS DECIMAL(10, 1)) as 'percent_f'"
					   + "FROM tblfacturartratamento ft, tblfactura f, tblservico s, tblcidadao cc, tblgenero g WHERE ft.FK_factura = f.id_factura and ft.FK_tratamento = s.id_servico and f.FK_paciente = cc.FK_entidade and cc.FK_genero = g.id_genero "
					   + "and year(f.Data_da_facturacao) = ? and month(f.Data_da_facturacao) between 7 and 9 GROUP BY ft.FK_tratamento";
		}
		if(aux == 4){
			sql = "SELECT s.descricao, s.id_servico, count(ft.FK_tratamento) as 'qtd',     COUNT(CASE WHEN g.id_genero = 1 THEN 0 END) AS 'masculino',   CAST(COUNT(CASE WHEN g.id_genero = 1 THEN 0 END) * 100 / count(ft.FK_tratamento)AS DECIMAL(10, 1)) as 'percet_m',"
					   + "COUNT(CASE WHEN g.id_genero = 2 THEN 0 END) AS 'feminino',  CAST(COUNT(CASE WHEN g.id_genero = 2 THEN 0 END) * 100 / count(ft.FK_tratamento)AS DECIMAL(10, 1)) as 'percent_f'"
					   + "FROM tblfacturartratamento ft, tblfactura f, tblservico s, tblcidadao cc, tblgenero g WHERE ft.FK_factura = f.id_factura and ft.FK_tratamento = s.id_servico and f.FK_paciente = cc.FK_entidade and cc.FK_genero = g.id_genero "
					   + "and year(f.Data_da_facturacao) = ? and month(f.Data_da_facturacao) between 10 and 12 GROUP BY ft.FK_tratamento";
		}

		if(aux == 5){
			sql = "SELECT s.descricao, s.id_servico, count(ft.FK_tratamento) as 'qtd',     COUNT(CASE WHEN g.id_genero = 1 THEN 0 END) AS 'masculino',   CAST(COUNT(CASE WHEN g.id_genero = 1 THEN 0 END) * 100 / count(ft.FK_tratamento)AS DECIMAL(10, 1)) as 'percet_m',"
					   + "COUNT(CASE WHEN g.id_genero = 2 THEN 0 END) AS 'feminino',  CAST(COUNT(CASE WHEN g.id_genero = 2 THEN 0 END) * 100 / count(ft.FK_tratamento)AS DECIMAL(10, 1)) as 'percent_f'"
					   + "FROM tblfacturartratamento ft, tblfactura f, tblservico s, tblcidadao cc, tblgenero g WHERE ft.FK_factura = f.id_factura and ft.FK_tratamento = s.id_servico and f.FK_paciente = cc.FK_entidade and cc.FK_genero = g.id_genero "
					   + "and year(f.Data_da_facturacao) = ? and month(f.Data_da_facturacao) between 1 and 6 GROUP BY ft.FK_tratamento";
		}
		if(aux == 6){
			sql = "SELECT s.descricao, s.id_servico, count(ft.FK_tratamento) as 'qtd',     COUNT(CASE WHEN g.id_genero = 1 THEN 0 END) AS 'masculino',   CAST(COUNT(CASE WHEN g.id_genero = 1 THEN 0 END) * 100 / count(ft.FK_tratamento)AS DECIMAL(10, 1)) as 'percet_m',"
					   + "COUNT(CASE WHEN g.id_genero = 2 THEN 0 END) AS 'feminino',  CAST(COUNT(CASE WHEN g.id_genero = 2 THEN 0 END) * 100 / count(ft.FK_tratamento)AS DECIMAL(10, 1)) as 'percent_f'"
					   + "FROM tblfacturartratamento ft, tblfactura f, tblservico s, tblcidadao cc, tblgenero g WHERE ft.FK_factura = f.id_factura and ft.FK_tratamento = s.id_servico and f.FK_paciente = cc.FK_entidade and cc.FK_genero = g.id_genero "
					   + "and year(f.Data_da_facturacao) = ? and month(f.Data_da_facturacao) between 7 and 12 GROUP BY ft.FK_tratamento";
		}
		
		List<Paciente> lista = new ArrayList<Paciente>();
		try {
			con = Conexao.getConexao();
			PreparedStatement ps = con.prepareStatement(sql);
			ps.setInt(1, ano);
			ResultSet rs = ps.executeQuery();
			while(rs.next()){
				Paciente p = new Paciente();
				p.setDescricao(rs.getString("descricao"));
				p.setQuantidade(rs.getInt("qtd"));
				p.setMasculino(rs.getInt("masculino"));
				p.setFeminino(rs.getInt("feminino"));
				p.setPerct_fmn(rs.getDouble("percent_f"));
				p.setPerct_msc(rs.getDouble("percet_m"));
				p.setFK_especialidade(rs.getInt("id_servico"));
				lista.add(p);
			}
			ps.close();
			con.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return lista;
	}
	
	public List<Paciente> buscarHistoricoSessoesEmIdades(int idade1,int idade2){
		String sql = "SELECT s.descricao, s.id_servico, count(ft.FK_tratamento) as 'qtd',     COUNT(CASE WHEN g.id_genero = 1 THEN 0 END) AS 'masculino',   CAST(COUNT(CASE WHEN g.id_genero = 1 THEN 0 END) * 100 / count(ft.FK_tratamento)AS DECIMAL(10, 1)) as 'percet_m',"
				   + "COUNT(CASE WHEN g.id_genero = 2 THEN 0 END) AS 'feminino',  CAST(COUNT(CASE WHEN g.id_genero = 2 THEN 0 END) * 100 / count(ft.FK_tratamento)AS DECIMAL(10, 1)) as 'percent_f'"
				   + "FROM tblfacturartratamento ft, tblfactura f, tblservico s, tblcidadao cc, tblgenero g WHERE ft.FK_factura = f.id_factura and ft.FK_tratamento = s.id_servico and f.FK_paciente = cc.FK_entidade and cc.FK_genero = g.id_genero "
				   + "and year(from_days((to_days(now()) - to_days(cc.data_nascimento)))) between ? and ? GROUP BY ft.FK_tratamento";
		List<Paciente> lista = new ArrayList<Paciente>();
		try {
			con = Conexao.getConexao();
			PreparedStatement ps = con.prepareStatement(sql);
			ps.setInt(1, idade1);
			ps.setInt(2, idade2);
			ResultSet rs = ps.executeQuery();
			while(rs.next()){
				Paciente p = new Paciente();
				p.setDescricao(rs.getString("descricao"));
				p.setQuantidade(rs.getInt("qtd"));
				p.setMasculino(rs.getInt("masculino"));
				p.setFeminino(rs.getInt("feminino"));
				p.setPerct_fmn(rs.getDouble("percent_f"));
				p.setPerct_msc(rs.getDouble("percet_m"));
				p.setFK_especialidade(rs.getInt("id_servico"));
				lista.add(p);
			}
			ps.close();
			con.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return lista;
	}
	
	public List<Paciente> buscarHistoricoSessoesEmAnosIdades(int ano,int idade1,int idade2){
		String sql = "SELECT s.descricao, s.id_servico, count(ft.FK_tratamento) as 'qtd',     COUNT(CASE WHEN g.id_genero = 1 THEN 0 END) AS 'masculino',   CAST(COUNT(CASE WHEN g.id_genero = 1 THEN 0 END) * 100 / count(ft.FK_tratamento)AS DECIMAL(10, 1)) as 'percet_m',"
				   + "COUNT(CASE WHEN g.id_genero = 2 THEN 0 END) AS 'feminino',  CAST(COUNT(CASE WHEN g.id_genero = 2 THEN 0 END) * 100 / count(ft.FK_tratamento)AS DECIMAL(10, 1)) as 'percent_f'"
				   + "FROM tblfacturartratamento ft, tblfactura f, tblservico s, tblcidadao cc, tblgenero g WHERE ft.FK_factura = f.id_factura and ft.FK_tratamento = s.id_servico and f.FK_paciente = cc.FK_entidade and cc.FK_genero = g.id_genero "
				   + "and year(f.Data_da_facturacao) = ? and year(from_days((to_days(now()) - to_days(cc.data_nascimento)))) between ? and ? GROUP BY ft.FK_tratamento";
		List<Paciente> lista = new ArrayList<Paciente>();
		try {
			con = Conexao.getConexao();
			PreparedStatement ps = con.prepareStatement(sql);
			ps.setInt(1, ano);
			ps.setInt(2, idade1);
			ps.setInt(3, idade2);
			ResultSet rs = ps.executeQuery();
			while(rs.next()){
				Paciente p = new Paciente();
				p.setDescricao(rs.getString("descricao"));
				p.setQuantidade(rs.getInt("qtd"));
				p.setMasculino(rs.getInt("masculino"));
				p.setFeminino(rs.getInt("feminino"));
				p.setPerct_fmn(rs.getDouble("percent_f"));
				p.setPerct_msc(rs.getDouble("percet_m"));
				p.setFK_especialidade(rs.getInt("id_servico"));
				lista.add(p);
			}
			ps.close();
			con.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return lista;
	}
	
	public List<Paciente> buscarHistoricoSessoesEmAnosMesIdades(int ano,int mes,int idade1,int idade2){
		String sql = "SELECT s.descricao, s.id_servico, count(ft.FK_tratamento) as 'qtd',     COUNT(CASE WHEN g.id_genero = 1 THEN 0 END) AS 'masculino',   CAST(COUNT(CASE WHEN g.id_genero = 1 THEN 0 END) * 100 / count(ft.FK_tratamento)AS DECIMAL(10, 1)) as 'percet_m',"
				   + "COUNT(CASE WHEN g.id_genero = 2 THEN 0 END) AS 'feminino',  CAST(COUNT(CASE WHEN g.id_genero = 2 THEN 0 END) * 100 / count(ft.FK_tratamento)AS DECIMAL(10, 1)) as 'percent_f'"
				   + "FROM tblfacturartratamento ft, tblfactura f, tblservico s, tblcidadao cc, tblgenero g WHERE ft.FK_factura = f.id_factura and ft.FK_tratamento = s.id_servico and f.FK_paciente = cc.FK_entidade and cc.FK_genero = g.id_genero "
				   + "and year(f.Data_da_facturacao) = ? and  month(f.Data_da_facturacao) = ? and year(from_days((to_days(now()) - to_days(cc.data_nascimento)))) between ? and ? GROUP BY ft.FK_tratamento";
		List<Paciente> lista = new ArrayList<Paciente>();
		try {
			con = Conexao.getConexao();
			PreparedStatement ps = con.prepareStatement(sql);
			ps.setInt(1, ano);
			ps.setInt(2, mes);
			ps.setInt(3, idade1);
			ps.setInt(4, idade2);
			ResultSet rs = ps.executeQuery();
			while(rs.next()){
				Paciente p = new Paciente();
				p.setDescricao(rs.getString("descricao"));
				p.setQuantidade(rs.getInt("qtd"));
				p.setMasculino(rs.getInt("masculino"));
				p.setFeminino(rs.getInt("feminino"));
				p.setPerct_fmn(rs.getDouble("percent_f"));
				p.setPerct_msc(rs.getDouble("percet_m"));
				p.setFK_especialidade(rs.getInt("id_servico"));
				lista.add(p);
			}
			ps.close();
			con.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return lista;
	}
	
	public List<Paciente> buscarHistoricoSessoesEmAnosMesDiasIdades(int ano,int mes,int dia1,int dia2,int idade1,int idade2){
		String sql = "SELECT s.descricao, s.id_servico, count(ft.FK_tratamento) as 'qtd',     COUNT(CASE WHEN g.id_genero = 1 THEN 0 END) AS 'masculino',   CAST(COUNT(CASE WHEN g.id_genero = 1 THEN 0 END) * 100 / count(ft.FK_tratamento)AS DECIMAL(10, 1)) as 'percet_m',"
				   + "COUNT(CASE WHEN g.id_genero = 2 THEN 0 END) AS 'feminino',  CAST(COUNT(CASE WHEN g.id_genero = 2 THEN 0 END) * 100 / count(ft.FK_tratamento)AS DECIMAL(10, 1)) as 'percent_f'"
				   + "FROM tblfacturartratamento ft, tblfactura f, tblservico s, tblcidadao cc, tblgenero g WHERE ft.FK_factura = f.id_factura and ft.FK_tratamento = s.id_servico and f.FK_paciente = cc.FK_entidade and cc.FK_genero = g.id_genero "
				   + "and year(f.Data_da_facturacao) = ? and  month(f.Data_da_facturacao) = ? and day(f.Data_da_facturacao) between ? and ? and year(from_days((to_days(now()) - to_days(cc.data_nascimento)))) between ? and ?  GROUP BY ft.FK_tratamento";
		List<Paciente> lista = new ArrayList<Paciente>();
		try {
			con = Conexao.getConexao();
			PreparedStatement ps = con.prepareStatement(sql);
			ps.setInt(1, ano);
			ps.setInt(2, mes);
			ps.setInt(3, dia1);
			ps.setInt(4, dia2);
			ps.setInt(5, idade1);
			ps.setInt(6, idade2);
			ResultSet rs = ps.executeQuery();
			while(rs.next()){
				Paciente p = new Paciente();
				p.setDescricao(rs.getString("descricao"));
				p.setQuantidade(rs.getInt("qtd"));
				p.setMasculino(rs.getInt("masculino"));
				p.setFeminino(rs.getInt("feminino"));
				p.setPerct_fmn(rs.getDouble("percent_f"));
				p.setPerct_msc(rs.getDouble("percet_m"));
				p.setFK_especialidade(rs.getInt("id_servico"));
				lista.add(p);
			}
			ps.close();
			con.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return lista;
	}
	
	public List<Paciente> buscarHistoricoSessoesEmAnosSeTrimestreIdades(int ano,int aux,int idade1,int idade2){
		String sql = "";
		
		if(aux == 1){
			sql = "SELECT s.descricao, s.id_servico, count(ft.FK_tratamento) as 'qtd',     COUNT(CASE WHEN g.id_genero = 1 THEN 0 END) AS 'masculino',   CAST(COUNT(CASE WHEN g.id_genero = 1 THEN 0 END) * 100 / count(ft.FK_tratamento)AS DECIMAL(10, 1)) as 'percet_m',"
					   + "COUNT(CASE WHEN g.id_genero = 2 THEN 0 END) AS 'feminino',  CAST(COUNT(CASE WHEN g.id_genero = 2 THEN 0 END) * 100 / count(ft.FK_tratamento)AS DECIMAL(10, 1)) as 'percent_f'"
					   + "FROM tblfacturartratamento ft, tblfactura f, tblservico s, tblcidadao cc, tblgenero g WHERE ft.FK_factura = f.id_factura and ft.FK_tratamento = s.id_servico and f.FK_paciente = cc.FK_entidade and cc.FK_genero = g.id_genero "
					   + "and year(f.Data_da_facturacao) = ? and month(f.Data_da_facturacao) between 1 and 3 and year(from_days((to_days(now()) - to_days(cc.data_nascimento)))) between ? and ? GROUP BY ft.FK_tratamento";
		}
		if(aux == 2){
			sql = "SELECT s.descricao, s.id_servico, count(ft.FK_tratamento) as 'qtd',     COUNT(CASE WHEN g.id_genero = 1 THEN 0 END) AS 'masculino',   CAST(COUNT(CASE WHEN g.id_genero = 1 THEN 0 END) * 100 / count(ft.FK_tratamento)AS DECIMAL(10, 1)) as 'percet_m',"
					   + "COUNT(CASE WHEN g.id_genero = 2 THEN 0 END) AS 'feminino',  CAST(COUNT(CASE WHEN g.id_genero = 2 THEN 0 END) * 100 / count(ft.FK_tratamento)AS DECIMAL(10, 1)) as 'percent_f'"
					   + "FROM tblfacturartratamento ft, tblfactura f, tblservico s, tblcidadao cc, tblgenero g WHERE ft.FK_factura = f.id_factura and ft.FK_tratamento = s.id_servico and f.FK_paciente = cc.FK_entidade and cc.FK_genero = g.id_genero "
					   + "and year(f.Data_da_facturacao) = ? and month(f.Data_da_facturacao) between 4 and 6 and year(from_days((to_days(now()) - to_days(cc.data_nascimento)))) between ? and ? GROUP BY ft.FK_tratamento";
		}

		if(aux == 3){
			sql = "SELECT s.descricao, s.id_servico, count(ft.FK_tratamento) as 'qtd',     COUNT(CASE WHEN g.id_genero = 1 THEN 0 END) AS 'masculino',   CAST(COUNT(CASE WHEN g.id_genero = 1 THEN 0 END) * 100 / count(ft.FK_tratamento)AS DECIMAL(10, 1)) as 'percet_m',"
					   + "COUNT(CASE WHEN g.id_genero = 2 THEN 0 END) AS 'feminino',  CAST(COUNT(CASE WHEN g.id_genero = 2 THEN 0 END) * 100 / count(ft.FK_tratamento)AS DECIMAL(10, 1)) as 'percent_f'"
					   + "FROM tblfacturartratamento ft, tblfactura f, tblservico s, tblcidadao cc, tblgenero g WHERE ft.FK_factura = f.id_factura and ft.FK_tratamento = s.id_servico and f.FK_paciente = cc.FK_entidade and cc.FK_genero = g.id_genero "
					   + "and year(f.Data_da_facturacao) = ? and month(f.Data_da_facturacao) between 7 and 9 and year(from_days((to_days(now()) - to_days(cc.data_nascimento)))) between ? and ? GROUP BY ft.FK_tratamento";
		}
		if(aux == 4){
			sql = "SELECT s.descricao, s.id_servico, count(ft.FK_tratamento) as 'qtd',     COUNT(CASE WHEN g.id_genero = 1 THEN 0 END) AS 'masculino',   CAST(COUNT(CASE WHEN g.id_genero = 1 THEN 0 END) * 100 / count(ft.FK_tratamento)AS DECIMAL(10, 1)) as 'percet_m',"
					   + "COUNT(CASE WHEN g.id_genero = 2 THEN 0 END) AS 'feminino',  CAST(COUNT(CASE WHEN g.id_genero = 2 THEN 0 END) * 100 / count(ft.FK_tratamento)AS DECIMAL(10, 1)) as 'percent_f'"
					   + "FROM tblfacturartratamento ft, tblfactura f, tblservico s, tblcidadao cc, tblgenero g WHERE ft.FK_factura = f.id_factura and ft.FK_tratamento = s.id_servico and f.FK_paciente = cc.FK_entidade and cc.FK_genero = g.id_genero "
					   + "and year(f.Data_da_facturacao) = ? and month(f.Data_da_facturacao) between 10 and 12 and year(from_days((to_days(now()) - to_days(cc.data_nascimento)))) between ? and ? GROUP BY ft.FK_tratamento";
		}

		if(aux == 5){
			sql = "SELECT s.descricao, s.id_servico, count(ft.FK_tratamento) as 'qtd',     COUNT(CASE WHEN g.id_genero = 1 THEN 0 END) AS 'masculino',   CAST(COUNT(CASE WHEN g.id_genero = 1 THEN 0 END) * 100 / count(ft.FK_tratamento)AS DECIMAL(10, 1)) as 'percet_m',"
					   + "COUNT(CASE WHEN g.id_genero = 2 THEN 0 END) AS 'feminino',  CAST(COUNT(CASE WHEN g.id_genero = 2 THEN 0 END) * 100 / count(ft.FK_tratamento)AS DECIMAL(10, 1)) as 'percent_f'"
					   + "FROM tblfacturartratamento ft, tblfactura f, tblservico s, tblcidadao cc, tblgenero g WHERE ft.FK_factura = f.id_factura and ft.FK_tratamento = s.id_servico and f.FK_paciente = cc.FK_entidade and cc.FK_genero = g.id_genero "
					   + "and year(f.Data_da_facturacao) = ? and month(f.Data_da_facturacao) between 1 and 6 and year(from_days((to_days(now()) - to_days(cc.data_nascimento)))) between ? and ? GROUP BY ft.FK_tratamento";
		}
		if(aux == 6){
			sql = "SELECT s.descricao, s.id_servico, count(ft.FK_tratamento) as 'qtd',     COUNT(CASE WHEN g.id_genero = 1 THEN 0 END) AS 'masculino',   CAST(COUNT(CASE WHEN g.id_genero = 1 THEN 0 END) * 100 / count(ft.FK_tratamento)AS DECIMAL(10, 1)) as 'percet_m',"
					   + "COUNT(CASE WHEN g.id_genero = 2 THEN 0 END) AS 'feminino',  CAST(COUNT(CASE WHEN g.id_genero = 2 THEN 0 END) * 100 / count(ft.FK_tratamento)AS DECIMAL(10, 1)) as 'percent_f'"
					   + "FROM tblfacturartratamento ft, tblfactura f, tblservico s, tblcidadao cc, tblgenero g WHERE ft.FK_factura = f.id_factura and ft.FK_tratamento = s.id_servico and f.FK_paciente = cc.FK_entidade and cc.FK_genero = g.id_genero "
					   + "and year(f.Data_da_facturacao) = ? and month(f.Data_da_facturacao) between 7 and 12 and year(from_days((to_days(now()) - to_days(cc.data_nascimento)))) between ? and ? GROUP BY ft.FK_tratamento";
		}
		
		List<Paciente> lista = new ArrayList<Paciente>();
		try {
			con = Conexao.getConexao();
			PreparedStatement ps = con.prepareStatement(sql);
			ps.setInt(1, ano);
			ps.setInt(2, idade1);
			ps.setInt(3, idade2);
			ResultSet rs = ps.executeQuery();
			while(rs.next()){
				Paciente p = new Paciente();
				p.setDescricao(rs.getString("descricao"));
				p.setQuantidade(rs.getInt("qtd"));
				p.setMasculino(rs.getInt("masculino"));
				p.setFeminino(rs.getInt("feminino"));
				p.setPerct_fmn(rs.getDouble("percent_f"));
				p.setPerct_msc(rs.getDouble("percet_m"));
				p.setFK_especialidade(rs.getInt("id_servico"));
				lista.add(p);
			}
			ps.close();
			con.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return lista;
	}
	

}


