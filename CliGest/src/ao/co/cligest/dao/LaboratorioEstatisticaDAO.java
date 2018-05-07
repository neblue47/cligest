package ao.co.cligest.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import ao.co.cligest.entidades.Diverso;
import ao.co.cligest.entidades.Exames;
import ao.co.cligest.entidades.Paciente;

public class LaboratorioEstatisticaDAO {

	Connection con;
	
	public List<Paciente> buscarLabExamesDiario(int origem){
		String sql ="SELECT sec.analise_clinica,a.fk_exame,a.FK_provenienca_exame, COUNT(CASE WHEN g.id_genero = 1 THEN 0 END) AS 'masculino', CAST(COUNT(CASE WHEN g.id_genero = 1 THEN 0 END) * 100 / count(a.fk_exame)AS DECIMAL(10, 1)) as 'percet_m',"
				+ " COUNT(CASE WHEN g.id_genero = 2 THEN 0 END) AS 'feminino', CAST(COUNT(CASE WHEN g.id_genero = 2 THEN 0 END) * 100 / count(a.fk_exame)AS DECIMAL(10, 1)) as 'percet_f', COUNT(a.fk_exame) as 'total' "
				+ " FROM tblamostra a, tblservicosdeexamesclinicos sec, tblgenero g, tblcidadao cc, tblnumerodoexame ne "
				+ " WHERE a.FK_requisicao_exame = ne.id_numero_do_exame and ne.FK_paciente = cc.FK_entidade and "
				+ " cc.FK_genero = g.id_genero and a.fk_exame = sec.id_servicodeanalise_clinica AND "
				+ " a.FK_provenienca_exame = ? GROUP BY a.fk_exame";
		List<Paciente> lista = new ArrayList<Paciente>();
		try {
			con = Conexao.getConexao();
			PreparedStatement ps = con.prepareStatement(sql);
			ps.setInt(1, origem);
			ResultSet rs = ps.executeQuery();
			while (rs.next()) {
				Paciente exm = new Paciente();
				exm.setDescricao(rs.getString("analise_clinica"));
				exm.setFK_servico(rs.getInt("fk_exame"));
				exm.setMasculino(rs.getInt("masculino"));
				exm.setPerct_msc(rs.getDouble("percet_m"));
				exm.setFeminino(rs.getInt("feminino"));
				exm.setPerct_fmn(rs.getDouble("percet_f"));
				exm.setQuantidade(rs.getInt("total"));
				lista.add(exm);
			}
			ps.close();
			con.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return lista;
	}
	
	public List<Paciente> buscarLabExamesEmAno(int origem,int ano){
		String sql ="SELECT sec.analise_clinica,a.fk_exame,a.FK_provenienca_exame, COUNT(CASE WHEN g.id_genero = 1 THEN 0 END) AS 'masculino', CAST(COUNT(CASE WHEN g.id_genero = 1 THEN 0 END) * 100 / count(a.fk_exame)AS DECIMAL(10, 1)) as 'percet_m',"
				+ " COUNT(CASE WHEN g.id_genero = 2 THEN 0 END) AS 'feminino', CAST(COUNT(CASE WHEN g.id_genero = 2 THEN 0 END) * 100 / count(a.fk_exame)AS DECIMAL(10, 1)) as 'percet_f', COUNT(a.fk_exame) as 'total' "
				+ " FROM tblamostra a, tblservicosdeexamesclinicos sec, tblgenero g, tblcidadao cc, tblnumerodoexame ne "
				+ " WHERE a.FK_requisicao_exame = ne.id_numero_do_exame and ne.FK_paciente = cc.FK_entidade and "
				+ " cc.FK_genero = g.id_genero and a.fk_exame = sec.id_servicodeanalise_clinica AND "
				+ " a.FK_provenienca_exame = ? AND year(a.data_darecolha) = ? GROUP BY a.fk_exame";
		List<Paciente> lista = new ArrayList<Paciente>();
		try {
			con = Conexao.getConexao();
			PreparedStatement ps = con.prepareStatement(sql);
			ps.setInt(1, origem);
			ps.setInt(2, ano);
			ResultSet rs = ps.executeQuery();
			while (rs.next()) {
				Paciente exm = new Paciente();
				exm.setDescricao(rs.getString("analise_clinica"));
				exm.setFK_servico(rs.getInt("fk_exame"));
				exm.setMasculino(rs.getInt("masculino"));
				exm.setPerct_msc(rs.getDouble("percet_m"));
				exm.setFeminino(rs.getInt("feminino"));
				exm.setPerct_fmn(rs.getDouble("percet_f"));
				exm.setQuantidade(rs.getInt("total"));
				lista.add(exm);
			}
			ps.close();
			con.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return lista;
	}
	
	public List<Paciente> buscarLabExamesEmAnoMes(int origem,int ano,int mes){
		String sql ="SELECT sec.analise_clinica,a.fk_exame,a.FK_provenienca_exame, COUNT(CASE WHEN g.id_genero = 1 THEN 0 END) AS 'masculino', CAST(COUNT(CASE WHEN g.id_genero = 1 THEN 0 END) * 100 / count(a.fk_exame)AS DECIMAL(10, 1)) as 'percet_m',"
				+ " COUNT(CASE WHEN g.id_genero = 2 THEN 0 END) AS 'feminino', CAST(COUNT(CASE WHEN g.id_genero = 2 THEN 0 END) * 100 / count(a.fk_exame)AS DECIMAL(10, 1)) as 'percet_f', COUNT(a.fk_exame) as 'total' "
				+ " FROM tblamostra a, tblservicosdeexamesclinicos sec, tblgenero g, tblcidadao cc, tblnumerodoexame ne "
				+ " WHERE a.FK_requisicao_exame = ne.id_numero_do_exame and ne.FK_paciente = cc.FK_entidade and "
				+ " cc.FK_genero = g.id_genero and a.fk_exame = sec.id_servicodeanalise_clinica AND "
				+ " a.FK_provenienca_exame = ? AND year(a.data_darecolha) = ? AND month(a.data_darecolha) = ? GROUP BY a.fk_exame";
		List<Paciente> lista = new ArrayList<Paciente>();
		try {
			con = Conexao.getConexao();
			PreparedStatement ps = con.prepareStatement(sql);
			ps.setInt(1, origem);
			ps.setInt(2, ano);
			ps.setInt(3, mes);
			ResultSet rs = ps.executeQuery();
			while (rs.next()) {
				Paciente exm = new Paciente();
				exm.setDescricao(rs.getString("analise_clinica"));
				exm.setFK_servico(rs.getInt("fk_exame"));
				exm.setMasculino(rs.getInt("masculino"));
				exm.setPerct_msc(rs.getDouble("percet_m"));
				exm.setFeminino(rs.getInt("feminino"));
				exm.setPerct_fmn(rs.getDouble("percet_f"));
				exm.setQuantidade(rs.getInt("total"));
				lista.add(exm);
			}
			ps.close();
			con.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return lista;
	}
	
	public List<Paciente> buscarLabExamesEmAnoMesDias(int origem,int ano,int mes,int dia1,int dia2){
		String sql ="SELECT sec.analise_clinica,a.fk_exame,a.FK_provenienca_exame, COUNT(CASE WHEN g.id_genero = 1 THEN 0 END) AS 'masculino', CAST(COUNT(CASE WHEN g.id_genero = 1 THEN 0 END) * 100 / count(a.fk_exame)AS DECIMAL(10, 1)) as 'percet_m',"
				+ " COUNT(CASE WHEN g.id_genero = 2 THEN 0 END) AS 'feminino', CAST(COUNT(CASE WHEN g.id_genero = 2 THEN 0 END) * 100 / count(a.fk_exame)AS DECIMAL(10, 1)) as 'percet_f', COUNT(a.fk_exame) as 'total' "
				+ " FROM tblamostra a, tblservicosdeexamesclinicos sec, tblgenero g, tblcidadao cc, tblnumerodoexame ne "
				+ " WHERE a.FK_requisicao_exame = ne.id_numero_do_exame and ne.FK_paciente = cc.FK_entidade and "
				+ " cc.FK_genero = g.id_genero and a.fk_exame = sec.id_servicodeanalise_clinica AND "
				+ " a.FK_provenienca_exame = ? AND year(a.data_darecolha) = ? AND month(a.data_darecolha) = ? AND day(a.data_darecolha) between ? and ? GROUP BY a.fk_exame";
		List<Paciente> lista = new ArrayList<Paciente>();
		try {
			con = Conexao.getConexao();
			PreparedStatement ps = con.prepareStatement(sql);
			ps.setInt(1, origem);
			ps.setInt(2, ano);
			ps.setInt(3, mes);
			ps.setInt(4, dia1);
			ps.setInt(5, dia2);
			ResultSet rs = ps.executeQuery();
			while (rs.next()) {
				Paciente exm = new Paciente();
				exm.setDescricao(rs.getString("analise_clinica"));
				exm.setFK_servico(rs.getInt("fk_exame"));
				exm.setMasculino(rs.getInt("masculino"));
				exm.setPerct_msc(rs.getDouble("percet_m"));
				exm.setFeminino(rs.getInt("feminino"));
				exm.setPerct_fmn(rs.getDouble("percet_f"));
				exm.setQuantidade(rs.getInt("total"));
				lista.add(exm);
			}
			ps.close();
			con.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return lista;
	}
	
	public List<Paciente> buscarLabExamesEmAnoSeTrimestre(int origem,int ano,int aux){
		String sql = "";
		if(aux == 1){
			       sql ="SELECT sec.analise_clinica,a.fk_exame,a.FK_provenienca_exame, COUNT(CASE WHEN g.id_genero = 1 THEN 0 END) AS 'masculino', CAST(COUNT(CASE WHEN g.id_genero = 1 THEN 0 END) * 100 / count(a.fk_exame)AS DECIMAL(10, 1)) as 'percet_m',"
					+ " COUNT(CASE WHEN g.id_genero = 2 THEN 0 END) AS 'feminino', CAST(COUNT(CASE WHEN g.id_genero = 2 THEN 0 END) * 100 / count(a.fk_exame)AS DECIMAL(10, 1)) as 'percet_f', COUNT(a.fk_exame) as 'total' "
					+ " FROM tblamostra a, tblservicosdeexamesclinicos sec, tblgenero g, tblcidadao cc, tblnumerodoexame ne "
					+ " WHERE a.FK_requisicao_exame = ne.id_numero_do_exame and ne.FK_paciente = cc.FK_entidade and "
					+ " cc.FK_genero = g.id_genero and a.fk_exame = sec.id_servicodeanalise_clinica AND "
					+ " a.FK_provenienca_exame = ? AND year(a.data_darecolha) = ? AND month(a.data_darecolha) between 1 and 3 GROUP BY a.fk_exame";
		}
		if(aux == 2){
		       sql ="SELECT sec.analise_clinica,a.fk_exame,a.FK_provenienca_exame, COUNT(CASE WHEN g.id_genero = 1 THEN 0 END) AS 'masculino', CAST(COUNT(CASE WHEN g.id_genero = 1 THEN 0 END) * 100 / count(a.fk_exame)AS DECIMAL(10, 1)) as 'percet_m',"
				+ " COUNT(CASE WHEN g.id_genero = 2 THEN 0 END) AS 'feminino', CAST(COUNT(CASE WHEN g.id_genero = 2 THEN 0 END) * 100 / count(a.fk_exame)AS DECIMAL(10, 1)) as 'percet_f', COUNT(a.fk_exame) as 'total' "
				+ " FROM tblamostra a, tblservicosdeexamesclinicos sec, tblgenero g, tblcidadao cc, tblnumerodoexame ne "
				+ " WHERE a.FK_requisicao_exame = ne.id_numero_do_exame and ne.FK_paciente = cc.FK_entidade and "
				+ " cc.FK_genero = g.id_genero and a.fk_exame = sec.id_servicodeanalise_clinica AND "
				+ " a.FK_provenienca_exame = ? AND year(a.data_darecolha) = ? AND month(a.data_darecolha) between 4 and 6 GROUP BY a.fk_exame";
	}
		if(aux == 3){
		       sql ="SELECT sec.analise_clinica,a.fk_exame,a.FK_provenienca_exame, COUNT(CASE WHEN g.id_genero = 1 THEN 0 END) AS 'masculino', CAST(COUNT(CASE WHEN g.id_genero = 1 THEN 0 END) * 100 / count(a.fk_exame)AS DECIMAL(10, 1)) as 'percet_m',"
				+ " COUNT(CASE WHEN g.id_genero = 2 THEN 0 END) AS 'feminino', CAST(COUNT(CASE WHEN g.id_genero = 2 THEN 0 END) * 100 / count(a.fk_exame)AS DECIMAL(10, 1)) as 'percet_f', COUNT(a.fk_exame) as 'total' "
				+ " FROM tblamostra a, tblservicosdeexamesclinicos sec, tblgenero g, tblcidadao cc, tblnumerodoexame ne "
				+ " WHERE a.FK_requisicao_exame = ne.id_numero_do_exame and ne.FK_paciente = cc.FK_entidade and "
				+ " cc.FK_genero = g.id_genero and a.fk_exame = sec.id_servicodeanalise_clinica AND "
				+ " a.FK_provenienca_exame = ? AND year(a.data_darecolha) = ? AND month(a.data_darecolha) between 7 and 9 GROUP BY a.fk_exame";
	}
		if(aux == 4){
		       sql ="SELECT sec.analise_clinica,a.fk_exame,a.FK_provenienca_exame, COUNT(CASE WHEN g.id_genero = 1 THEN 0 END) AS 'masculino', CAST(COUNT(CASE WHEN g.id_genero = 1 THEN 0 END) * 100 / count(a.fk_exame)AS DECIMAL(10, 1)) as 'percet_m',"
				+ " COUNT(CASE WHEN g.id_genero = 2 THEN 0 END) AS 'feminino', CAST(COUNT(CASE WHEN g.id_genero = 2 THEN 0 END) * 100 / count(a.fk_exame)AS DECIMAL(10, 1)) as 'percet_f', COUNT(a.fk_exame) as 'total' "
				+ " FROM tblamostra a, tblservicosdeexamesclinicos sec, tblgenero g, tblcidadao cc, tblnumerodoexame ne "
				+ " WHERE a.FK_requisicao_exame = ne.id_numero_do_exame and ne.FK_paciente = cc.FK_entidade and "
				+ " cc.FK_genero = g.id_genero and a.fk_exame = sec.id_servicodeanalise_clinica AND "
				+ " a.FK_provenienca_exame = ? AND year(a.data_darecolha) = ? AND month(a.data_darecolha) between 10 and 12 GROUP BY a.fk_exame";
	}
		if(aux == 5){
		       sql ="SELECT sec.analise_clinica,a.fk_exame,a.FK_provenienca_exame, COUNT(CASE WHEN g.id_genero = 1 THEN 0 END) AS 'masculino', CAST(COUNT(CASE WHEN g.id_genero = 1 THEN 0 END) * 100 / count(a.fk_exame)AS DECIMAL(10, 1)) as 'percet_m',"
				+ " COUNT(CASE WHEN g.id_genero = 2 THEN 0 END) AS 'feminino', CAST(COUNT(CASE WHEN g.id_genero = 2 THEN 0 END) * 100 / count(a.fk_exame)AS DECIMAL(10, 1)) as 'percet_f', COUNT(a.fk_exame) as 'total' "
				+ " FROM tblamostra a, tblservicosdeexamesclinicos sec, tblgenero g, tblcidadao cc, tblnumerodoexame ne "
				+ " WHERE a.FK_requisicao_exame = ne.id_numero_do_exame and ne.FK_paciente = cc.FK_entidade and "
				+ " cc.FK_genero = g.id_genero and a.fk_exame = sec.id_servicodeanalise_clinica AND "
				+ " a.FK_provenienca_exame = ? AND year(a.data_darecolha) = ? AND month(a.data_darecolha) between 1 and 6 GROUP BY a.fk_exame";
	}
		if(aux == 6){
		       sql ="SELECT sec.analise_clinica,a.fk_exame,a.FK_provenienca_exame, COUNT(CASE WHEN g.id_genero = 1 THEN 0 END) AS 'masculino', CAST(COUNT(CASE WHEN g.id_genero = 1 THEN 0 END) * 100 / count(a.fk_exame)AS DECIMAL(10, 1)) as 'percet_m',"
				+ " COUNT(CASE WHEN g.id_genero = 2 THEN 0 END) AS 'feminino', CAST(COUNT(CASE WHEN g.id_genero = 2 THEN 0 END) * 100 / count(a.fk_exame)AS DECIMAL(10, 1)) as 'percet_f', COUNT(a.fk_exame) as 'total' "
				+ " FROM tblamostra a, tblservicosdeexamesclinicos sec, tblgenero g, tblcidadao cc, tblnumerodoexame ne "
				+ " WHERE a.FK_requisicao_exame = ne.id_numero_do_exame and ne.FK_paciente = cc.FK_entidade and "
				+ " cc.FK_genero = g.id_genero and a.fk_exame = sec.id_servicodeanalise_clinica AND "
				+ " a.FK_provenienca_exame = ? AND year(a.data_darecolha) = ? AND month(a.data_darecolha) between 7 and 12 GROUP BY a.fk_exame";
	}
		
		List<Paciente> lista = new ArrayList<Paciente>();
		try {
			con = Conexao.getConexao();
			PreparedStatement ps = con.prepareStatement(sql);
			ps.setInt(1, origem);
			ps.setInt(2, ano);
			 
			ResultSet rs = ps.executeQuery();
			while (rs.next()) {
				Paciente exm = new Paciente();
				exm.setDescricao(rs.getString("analise_clinica"));
				exm.setFK_servico(rs.getInt("fk_exame"));
				exm.setMasculino(rs.getInt("masculino"));
				exm.setPerct_msc(rs.getDouble("percet_m"));
				exm.setFeminino(rs.getInt("feminino"));
				exm.setPerct_fmn(rs.getDouble("percet_f"));
				exm.setQuantidade(rs.getInt("total"));
				lista.add(exm);
			}
			ps.close();
			con.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return lista;
	}
	
	public List<Diverso> buscarExamesLaboratorio() {
		String sql = "SELECT sec.analise_clinica,a.FK_provenienca_exame, a.fk_exame FROM tblamostra a, tblservicosdeexamesclinicos sec, tblgenero g, tblcidadao cc, tblnumerodoexame ne "
				+ " WHERE a.FK_requisicao_exame = ne.id_numero_do_exame and ne.FK_paciente = cc.FK_entidade and cc.FK_genero = g.id_genero and "
				+ " a.fk_exame = sec.id_servicodeanalise_clinica GROUP BY a.fk_exame";
		
		List<Diverso> lista = new ArrayList<Diverso>();
		try {
			con = Conexao.getConexao();
			PreparedStatement ps = con.prepareStatement(sql);
			ResultSet rs = ps.executeQuery();
			while (rs.next()) {
				Diverso d = new Diverso();
				d.setCod_cid(rs.getString("fk_exame"));
				d.setDescricao(rs.getString("analise_clinica"));
				lista.add(d);
			}
			ps.close();
			con.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return lista;
	}
	
	
	public List<Paciente> buscarLabGrupoExamesDiario(int origem){
		String sql ="SELECT ge.grupo_danalise, ge.id_grupo_analises_clinicas, COUNT(CASE WHEN g.id_genero = 1 THEN 0 END) AS 'masculino', CAST(COUNT(CASE WHEN g.id_genero = 1 THEN 0 END) * 100 / count(ge.id_grupo_analises_clinicas)AS DECIMAL(10, 1)) as 'percet_m', "
				  + " COUNT(CASE WHEN g.id_genero = 2 THEN 0 END) AS 'feminino', CAST(COUNT(CASE WHEN g.id_genero = 2 THEN 0 END) * 100 / count(ge.id_grupo_analises_clinicas)AS DECIMAL(10, 1)) as 'percet_f', "
				  + " COUNT(ge.id_grupo_analises_clinicas) as 'total' FROM tblamostra a, tblservicosdeexamesclinicos sec, tblgenero g, tblcidadao cc, tblnumerodoexame ne, tblgrupodeanalises ge "
				  + " WHERE a.FK_requisicao_exame = ne.id_numero_do_exame and ne.FK_paciente = cc.FK_entidade and cc.FK_genero = g.id_genero and a.fk_exame = sec.id_servicodeanalise_clinica AND sec.FK_grupo_analise_clinica = ge.id_grupo_analises_clinicas AND"
				  + " a.FK_provenienca_exame = ? GROUP BY ge.id_grupo_analises_clinicas";
		List<Paciente> lista = new ArrayList<Paciente>();
		try {
			con = Conexao.getConexao();
			PreparedStatement ps = con.prepareStatement(sql);
			ps.setInt(1, origem);
			ResultSet rs = ps.executeQuery();
			while (rs.next()) {
				Paciente exm = new Paciente();
				exm.setDescricao(rs.getString("grupo_danalise"));
				exm.setFK_servico(rs.getInt("id_grupo_analises_clinicas"));
				exm.setMasculino(rs.getInt("masculino"));
				exm.setPerct_msc(rs.getDouble("percet_m"));
				exm.setFeminino(rs.getInt("feminino"));
				exm.setPerct_fmn(rs.getDouble("percet_f"));
				exm.setQuantidade(rs.getInt("total"));
				lista.add(exm);
			}
			ps.close();
			con.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return lista;
	}
	
	
	
	public List<Paciente> buscarLabGrupoExamesEmAno(int origem,int ano){
		String sql ="SELECT ge.grupo_danalise,ge.id_grupo_analises_clinicas, COUNT(CASE WHEN g.id_genero = 1 THEN 0 END) AS 'masculino', CAST(COUNT(CASE WHEN g.id_genero = 1 THEN 0 END) * 100 / count(ge.id_grupo_analises_clinicas)AS DECIMAL(10, 1)) as 'percet_m', "
				  + " COUNT(CASE WHEN g.id_genero = 2 THEN 0 END) AS 'feminino', CAST(COUNT(CASE WHEN g.id_genero = 2 THEN 0 END) * 100 / count(ge.id_grupo_analises_clinicas)AS DECIMAL(10, 1)) as 'percet_f', "
				  + " COUNT(ge.id_grupo_analises_clinicas) as 'total' FROM tblamostra a, tblservicosdeexamesclinicos sec, tblgenero g, tblcidadao cc, tblnumerodoexame ne, tblgrupodeanalises ge "
				  + " WHERE a.FK_requisicao_exame = ne.id_numero_do_exame and ne.FK_paciente = cc.FK_entidade and cc.FK_genero = g.id_genero and a.fk_exame = sec.id_servicodeanalise_clinica AND sec.FK_grupo_analise_clinica = ge.id_grupo_analises_clinicas AND"
				  + " a.FK_provenienca_exame = ? AND year(a.data_darecolha) = ? GROUP BY ge.id_grupo_analises_clinicas";
		List<Paciente> lista = new ArrayList<Paciente>();
		try {
			con = Conexao.getConexao();
			PreparedStatement ps = con.prepareStatement(sql);
			ps.setInt(1, origem);
			ps.setInt(2, ano);
			ResultSet rs = ps.executeQuery();
			while (rs.next()) {
				Paciente exm = new Paciente();
				exm.setDescricao(rs.getString("analise_clinica"));
				exm.setFK_servico(rs.getInt("id_grupo_analises_clinicas"));
				exm.setMasculino(rs.getInt("masculino"));
				exm.setPerct_msc(rs.getDouble("percet_m"));
				exm.setFeminino(rs.getInt("feminino"));
				exm.setPerct_fmn(rs.getDouble("percet_f"));
				exm.setQuantidade(rs.getInt("total"));
				lista.add(exm);
			}
			ps.close();
			con.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return lista;
	}
	
	public List<Paciente> buscarLabGrupoExamesEmAnoMes(int origem,int ano,int mes){
		String sql ="SELECT ge.grupo_danalise, ge.id_grupo_analises_clinicas, COUNT(CASE WHEN g.id_genero = 1 THEN 0 END) AS 'masculino', CAST(COUNT(CASE WHEN g.id_genero = 1 THEN 0 END) * 100 / count(ge.id_grupo_analises_clinicas)AS DECIMAL(10, 1)) as 'percet_m', "
				  + " COUNT(CASE WHEN g.id_genero = 2 THEN 0 END) AS 'feminino', CAST(COUNT(CASE WHEN g.id_genero = 2 THEN 0 END) * 100 / count(ge.id_grupo_analises_clinicas)AS DECIMAL(10, 1)) as 'percet_f', "
				  + " COUNT(ge.id_grupo_analises_clinicas) as 'total' FROM tblamostra a, tblservicosdeexamesclinicos sec, tblgenero g, tblcidadao cc, tblnumerodoexame ne, tblgrupodeanalises ge "
				  + " WHERE a.FK_requisicao_exame = ne.id_numero_do_exame and ne.FK_paciente = cc.FK_entidade and cc.FK_genero = g.id_genero and a.fk_exame = sec.id_servicodeanalise_clinica AND sec.FK_grupo_analise_clinica = ge.id_grupo_analises_clinicas AND"
				  + " a.FK_provenienca_exame = ? AND year(a.data_darecolha) = ? AND month(a.data_darecolha) = ? GROUP BY ge.id_grupo_analises_clinicas";
		List<Paciente> lista = new ArrayList<Paciente>();
		try {
			con = Conexao.getConexao();
			PreparedStatement ps = con.prepareStatement(sql);
			ps.setInt(1, origem);
			ps.setInt(2, ano);
			ps.setInt(3, mes);
			ResultSet rs = ps.executeQuery();
			while (rs.next()) {
				Paciente exm = new Paciente();
				exm.setDescricao(rs.getString("grupo_danalise"));
				exm.setFK_servico(rs.getInt("id_grupo_analises_clinicas"));
				exm.setMasculino(rs.getInt("masculino"));
				exm.setPerct_msc(rs.getDouble("percet_m"));
				exm.setFeminino(rs.getInt("feminino"));
				exm.setPerct_fmn(rs.getDouble("percet_f"));
				exm.setQuantidade(rs.getInt("total"));
				lista.add(exm);
			}
			ps.close();
			con.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return lista;
	}
	
	public List<Paciente> buscarLabGrupoExamesEmAnoSeTrimestre(int origem,int ano,int aux){
		String sql = "";
		if(aux == 1){
			sql ="SELECT ge.grupo_danalise,ge.id_grupo_analises_clinicas, COUNT(CASE WHEN g.id_genero = 1 THEN 0 END) AS 'masculino', CAST(COUNT(CASE WHEN g.id_genero = 1 THEN 0 END) * 100 / count(ge.id_grupo_analises_clinicas)AS DECIMAL(10, 1)) as 'percet_m', "
					  + " COUNT(CASE WHEN g.id_genero = 2 THEN 0 END) AS 'feminino', CAST(COUNT(CASE WHEN g.id_genero = 2 THEN 0 END) * 100 / count(ge.id_grupo_analises_clinicas)AS DECIMAL(10, 1)) as 'percet_f', "
					  + " COUNT(ge.id_grupo_analises_clinicas) as 'total' FROM tblamostra a, tblservicosdeexamesclinicos sec, tblgenero g, tblcidadao cc, tblnumerodoexame ne, tblgrupodeanalises ge "
					  + " WHERE a.FK_requisicao_exame = ne.id_numero_do_exame and ne.FK_paciente = cc.FK_entidade and cc.FK_genero = g.id_genero and a.fk_exame = sec.id_servicodeanalise_clinica AND sec.FK_grupo_analise_clinica = ge.id_grupo_analises_clinicas AND"
					  + " a.FK_provenienca_exame = ? AND year(a.data_darecolha) = ? AND month(a.data_darecolha) between 1 and 3 GROUP BY ge.id_grupo_analises_clinicas";
		}
		if(aux == 2){
			sql ="SELECT ge.grupo_danalise,ge.id_grupo_analises_clinicas COUNT(CASE WHEN g.id_genero = 1 THEN 0 END) AS 'masculino', CAST(COUNT(CASE WHEN g.id_genero = 1 THEN 0 END) * 100 / count(ge.id_grupo_analises_clinicas)AS DECIMAL(10, 1)) as 'percet_m', "
					  + " COUNT(CASE WHEN g.id_genero = 2 THEN 0 END) AS 'feminino', CAST(COUNT(CASE WHEN g.id_genero = 2 THEN 0 END) * 100 / count(ge.id_grupo_analises_clinicas)AS DECIMAL(10, 1)) as 'percet_f', "
					  + " COUNT(ge.id_grupo_analises_clinicas) as 'total' FROM tblamostra a, tblservicosdeexamesclinicos sec, tblgenero g, tblcidadao cc, tblnumerodoexame ne, tblgrupodeanalises ge "
					  + " WHERE a.FK_requisicao_exame = ne.id_numero_do_exame and ne.FK_paciente = cc.FK_entidade and cc.FK_genero = g.id_genero and a.fk_exame = sec.id_servicodeanalise_clinica AND sec.FK_grupo_analise_clinica = ge.id_grupo_analises_clinicas AND"
					  + " a.FK_provenienca_exame = ? AND year(a.data_darecolha) = ? AND month(a.data_darecolha) between 4 and 6 GROUP BY ge.id_grupo_analises_clinicas";
		}
		if(aux == 3){
			sql ="SELECT ge.grupo_danalise,ge.id_grupo_analises_clinicas COUNT(CASE WHEN g.id_genero = 1 THEN 0 END) AS 'masculino', CAST(COUNT(CASE WHEN g.id_genero = 1 THEN 0 END) * 100 / count(ge.id_grupo_analises_clinicas)AS DECIMAL(10, 1)) as 'percet_m', "
					  + " COUNT(CASE WHEN g.id_genero = 2 THEN 0 END) AS 'feminino', CAST(COUNT(CASE WHEN g.id_genero = 2 THEN 0 END) * 100 / count(ge.id_grupo_analises_clinicas)AS DECIMAL(10, 1)) as 'percet_f', "
					  + " COUNT(ge.id_grupo_analises_clinicas) as 'total' FROM tblamostra a, tblservicosdeexamesclinicos sec, tblgenero g, tblcidadao cc, tblnumerodoexame ne, tblgrupodeanalises ge "
					  + " WHERE a.FK_requisicao_exame = ne.id_numero_do_exame and ne.FK_paciente = cc.FK_entidade and cc.FK_genero = g.id_genero and a.fk_exame = sec.id_servicodeanalise_clinica AND sec.FK_grupo_analise_clinica = ge.id_grupo_analises_clinicas AND"
					  + " a.FK_provenienca_exame = ? AND year(a.data_darecolha) = ? AND month(a.data_darecolha) between 7 and 9 GROUP BY ge.id_grupo_analises_clinicas";
		}
		if(aux == 4){
			sql ="SELECT ge.grupo_danalise,ge.id_grupo_analises_clinicas COUNT(CASE WHEN g.id_genero = 1 THEN 0 END) AS 'masculino', CAST(COUNT(CASE WHEN g.id_genero = 1 THEN 0 END) * 100 / count(ge.id_grupo_analises_clinicas)AS DECIMAL(10, 1)) as 'percet_m', "
					  + " COUNT(CASE WHEN g.id_genero = 2 THEN 0 END) AS 'feminino', CAST(COUNT(CASE WHEN g.id_genero = 2 THEN 0 END) * 100 / count(ge.id_grupo_analises_clinicas)AS DECIMAL(10, 1)) as 'percet_f', "
					  + " COUNT(ge.id_grupo_analises_clinicas) as 'total' FROM tblamostra a, tblservicosdeexamesclinicos sec, tblgenero g, tblcidadao cc, tblnumerodoexame ne, tblgrupodeanalises ge "
					  + " WHERE a.FK_requisicao_exame = ne.id_numero_do_exame and ne.FK_paciente = cc.FK_entidade and cc.FK_genero = g.id_genero and a.fk_exame = sec.id_servicodeanalise_clinica AND sec.FK_grupo_analise_clinica = ge.id_grupo_analises_clinicas AND"
					  + " a.FK_provenienca_exame = ? AND year(a.data_darecolha) = ? AND month(a.data_darecolha) between 10 and 12 GROUP BY ge.id_grupo_analises_clinicas";
		}
		if(aux == 5){
			sql ="SELECT ge.grupo_danalise,ge.id_grupo_analises_clinicas COUNT(CASE WHEN g.id_genero = 1 THEN 0 END) AS 'masculino', CAST(COUNT(CASE WHEN g.id_genero = 1 THEN 0 END) * 100 / count(ge.id_grupo_analises_clinicas)AS DECIMAL(10, 1)) as 'percet_m', "
					  + " COUNT(CASE WHEN g.id_genero = 2 THEN 0 END) AS 'feminino', CAST(COUNT(CASE WHEN g.id_genero = 2 THEN 0 END) * 100 / count(ge.id_grupo_analises_clinicas)AS DECIMAL(10, 1)) as 'percet_f', "
					  + " COUNT(ge.id_grupo_analises_clinicas) as 'total' FROM tblamostra a, tblservicosdeexamesclinicos sec, tblgenero g, tblcidadao cc, tblnumerodoexame ne, tblgrupodeanalises ge "
					  + " WHERE a.FK_requisicao_exame = ne.id_numero_do_exame and ne.FK_paciente = cc.FK_entidade and cc.FK_genero = g.id_genero and a.fk_exame = sec.id_servicodeanalise_clinica AND sec.FK_grupo_analise_clinica = ge.id_grupo_analises_clinicas AND"
					  + " a.FK_provenienca_exame = ? AND year(a.data_darecolha) = ? AND month(a.data_darecolha) between 1 and 6 GROUP BY ge.id_grupo_analises_clinicas";
		}
		if(aux == 6){
			sql ="SELECT ge.grupo_danalise,ge.id_grupo_analises_clinicas COUNT(CASE WHEN g.id_genero = 1 THEN 0 END) AS 'masculino', CAST(COUNT(CASE WHEN g.id_genero = 1 THEN 0 END) * 100 / count(ge.id_grupo_analises_clinicas)AS DECIMAL(10, 1)) as 'percet_m', "
					  + " COUNT(CASE WHEN g.id_genero = 2 THEN 0 END) AS 'feminino', CAST(COUNT(CASE WHEN g.id_genero = 2 THEN 0 END) * 100 / count(ge.id_grupo_analises_clinicas)AS DECIMAL(10, 1)) as 'percet_f', "
					  + " COUNT(ge.id_grupo_analises_clinicas) as 'total' FROM tblamostra a, tblservicosdeexamesclinicos sec, tblgenero g, tblcidadao cc, tblnumerodoexame ne, tblgrupodeanalises ge "
					  + " WHERE a.FK_requisicao_exame = ne.id_numero_do_exame and ne.FK_paciente = cc.FK_entidade and cc.FK_genero = g.id_genero and a.fk_exame = sec.id_servicodeanalise_clinica AND sec.FK_grupo_analise_clinica = ge.id_grupo_analises_clinicas AND"
					  + " a.FK_provenienca_exame = ? AND year(a.data_darecolha) = ? AND month(a.data_darecolha) between 7 and 12 GROUP BY ge.id_grupo_analises_clinicas";
		}
		List<Paciente> lista = new ArrayList<Paciente>();
		try {
			con = Conexao.getConexao();
			PreparedStatement ps = con.prepareStatement(sql);
			ps.setInt(1, origem);
			ps.setInt(2, ano);
			 
			ResultSet rs = ps.executeQuery();
			while (rs.next()) {
				Paciente exm = new Paciente();
				exm.setDescricao(rs.getString("grupo_danalise"));
				exm.setFK_servico(rs.getInt("id_grupo_analises_clinicas"));
				exm.setMasculino(rs.getInt("masculino"));
				exm.setPerct_msc(rs.getDouble("percet_m"));
				exm.setFeminino(rs.getInt("feminino"));
				exm.setPerct_fmn(rs.getDouble("percet_f"));
				exm.setQuantidade(rs.getInt("total"));
				lista.add(exm);
			}
			ps.close();
			con.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return lista;
	}
	public List<Paciente> buscarLabGrupoExamesEmAnoMesDias(int origem,int ano,int mes,int dia1,int dia2){
		String sql ="SELECT ge.grupo_danalise,ge.id_grupo_analises_clinicas COUNT(CASE WHEN g.id_genero = 1 THEN 0 END) AS 'masculino', CAST(COUNT(CASE WHEN g.id_genero = 1 THEN 0 END) * 100 / count(ge.id_grupo_analises_clinicas)AS DECIMAL(10, 1)) as 'percet_m', "
				  + " COUNT(CASE WHEN g.id_genero = 2 THEN 0 END) AS 'feminino', CAST(COUNT(CASE WHEN g.id_genero = 2 THEN 0 END) * 100 / count(ge.id_grupo_analises_clinicas)AS DECIMAL(10, 1)) as 'percet_f', "
				  + " COUNT(ge.id_grupo_analises_clinicas) as 'total' FROM tblamostra a, tblservicosdeexamesclinicos sec, tblgenero g, tblcidadao cc, tblnumerodoexame ne, tblgrupodeanalises ge "
				  + " WHERE a.FK_requisicao_exame = ne.id_numero_do_exame and ne.FK_paciente = cc.FK_entidade and cc.FK_genero = g.id_genero and a.fk_exame = sec.id_servicodeanalise_clinica AND sec.FK_grupo_analise_clinica = ge.id_grupo_analises_clinicas AND"
				  + " a.FK_provenienca_exame = ? AND year(a.data_darecolha) = ? AND month(a.data_darecolha) = ? AND day(a.data_darecolha) between ? and ? GROUP BY ge.id_grupo_analises_clinicas";
		List<Paciente> lista = new ArrayList<Paciente>();
		try {
			con = Conexao.getConexao();
			PreparedStatement ps = con.prepareStatement(sql);
			ps.setInt(1, origem);
			ps.setInt(2, ano);
			ps.setInt(3, mes);
			ps.setInt(4, dia1);
			ps.setInt(5, dia2);
			ResultSet rs = ps.executeQuery();
			while (rs.next()) {
				Paciente exm = new Paciente();
				exm.setDescricao(rs.getString("analise_clinica"));
				exm.setFK_servico(rs.getInt("id_grupo_analises_clinicas"));
				exm.setMasculino(rs.getInt("masculino"));
				exm.setPerct_msc(rs.getDouble("percet_m"));
				exm.setFeminino(rs.getInt("feminino"));
				exm.setPerct_fmn(rs.getDouble("percet_f"));
				exm.setQuantidade(rs.getInt("total"));
				lista.add(exm);
			}
			ps.close();
			con.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return lista;
	}
	
	public List<Diverso> buscarGrupoExamesLaboratorio() {
		String sql = "SELECT ge.grupo_danalise,ge.id_grupo_analises_clinicas FROM tblamostra a, tblservicosdeexamesclinicos sec, tblgenero g, tblcidadao cc, tblnumerodoexame ne, tblgrupodeanalises ge "
				   + " WHERE a.FK_requisicao_exame = ne.id_numero_do_exame and ne.FK_paciente = cc.FK_entidade and cc.FK_genero = g.id_genero and "
				   + " a.fk_exame = sec.id_servicodeanalise_clinica AND sec.FK_grupo_analise_clinica = ge.id_grupo_analises_clinicas GROUP BY ge.id_grupo_analises_clinicas";
		
		List<Diverso> lista = new ArrayList<Diverso>();
		try {
			con = Conexao.getConexao();
			PreparedStatement ps = con.prepareStatement(sql);
			ResultSet rs = ps.executeQuery();
			while (rs.next()) {
				Diverso d = new Diverso();
				d.setCod_cid(rs.getString("id_grupo_analises_clinicas"));
				d.setDescricao(rs.getString("grupo_danalise"));
				lista.add(d);
			}
			ps.close();
			con.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return lista;
	}
	
	public List<Paciente> buscarLabTotalGrupoExamesDiario(int origem){
		String sql = "";
		
		if(origem == 1){
			sql ="SELECT count(distinct(case when FK_provenienca_exame = 1 then fk_exame end)) as p_consulta,"
					+ " count(distinct(case when FK_provenienca_exame = 2 then fk_exame end)) as p_externos, "
					+ " count(distinct(case when FK_provenienca_exame = 4 then fk_exame end)) as p_internamento, "
					+ " count(distinct(case when FK_provenienca_exame = 3 then fk_exame end)) as p_bue "
					+ " FROM tblamostra";
			System.out.println(origem);
		}
		if(origem == 2){
			sql ="SELECT count(distinct(case when a.FK_provenienca_exame = 1 then sec.fk_grupo_analise_clinica end)) as p_consulta,"
			   + " count(distinct(case when a.FK_provenienca_exame = 2 then sec.fk_grupo_analise_clinica end)) as p_externos, "
			   + " count(distinct(case when a.FK_provenienca_exame = 4 then sec.fk_grupo_analise_clinica end)) as p_internamento, "
			   + " count(distinct(case when a.FK_provenienca_exame = 3 then sec.fk_grupo_analise_clinica end)) as p_bue "
			   + " FROM tblamostra a, tblservicosdeexamesclinicos sec WHERE a.fk_exame = sec.id_servicodeanalise_clinica";
			System.out.println(origem);
		}
				
		List<Paciente> lista = new ArrayList<Paciente>();
		try {
			con = Conexao.getConexao();
			PreparedStatement ps = con.prepareStatement(sql);	 
			ResultSet rs = ps.executeQuery();
			while (rs.next()) {
				Paciente exm = new Paciente();
				exm.setP_banco_ue(rs.getInt("p_bue"));
				exm.setP_consulta(rs.getInt("p_consulta"));
				exm.setP_externos(rs.getInt("p_externos"));
				exm.setP_internados(rs.getInt("p_internamento"));
				lista.add(exm);
			}
			ps.close();
			con.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return lista;
	}
	
	public List<Paciente> buscarLabTotalGrupoExamesEmAno(int origem,int ano){
		String sql = "";
		
		if(origem == 1){
			sql ="SELECT count(distinct(case when FK_provenienca_exame = 1 then fk_exame end)) as p_consulta,"
					+ " count(distinct(case when FK_provenienca_exame = 2 then fk_exame end)) as p_externos, "
					+ " count(distinct(case when FK_provenienca_exame = 4 then fk_exame end)) as p_internamento, "
					+ " count(distinct(case when FK_provenienca_exame = 3 then fk_exame end)) as p_bue "
					+ " FROM tblamostra WHERE  year(data_darecolha) = ? ";
			 
		}
		if(origem == 2){
			sql ="SELECT count(distinct(case when a.FK_provenienca_exame = 1 then sec.fk_grupo_analise_clinica end)) as p_consulta,"
			   + " count(distinct(case when a.FK_provenienca_exame = 2 then sec.fk_grupo_analise_clinica end)) as p_externos, "
			   + " count(distinct(case when a.FK_provenienca_exame = 4 then sec.fk_grupo_analise_clinica end)) as p_internamento, "
			   + " count(distinct(case when a.FK_provenienca_exame = 3 then sec.fk_grupo_analise_clinica end)) as p_bue "
			   + " FROM tblamostra a, tblservicosdeexamesclinicos sec WHERE a.fk_exame = sec.id_servicodeanalise_clinica AND "
			   + " year(a.data_darecolha) = ? ";
			 
		}
				
		List<Paciente> lista = new ArrayList<Paciente>();
		try {
			con = Conexao.getConexao();
			PreparedStatement ps = con.prepareStatement(sql);	
			ps.setInt(1, ano);
			ResultSet rs = ps.executeQuery();
			while (rs.next()) {
				Paciente exm = new Paciente();
				exm.setP_banco_ue(rs.getInt("p_bue"));
				exm.setP_consulta(rs.getInt("p_consulta"));
				exm.setP_externos(rs.getInt("p_externos"));
				exm.setP_internados(rs.getInt("p_internamento"));
				lista.add(exm);
			}
			ps.close();
			con.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return lista;
	}
	
	public List<Paciente> buscarLabTotalGrupoExamesEmAnoMes(int origem,int ano,int mes){
		String sql = "";
		
		if(origem == 1){
			sql ="SELECT count(distinct(case when FK_provenienca_exame = 1 then fk_exame end)) as p_consulta,"
					+ " count(distinct(case when FK_provenienca_exame = 2 then fk_exame end)) as p_externos, "
					+ " count(distinct(case when FK_provenienca_exame = 4 then fk_exame end)) as p_internamento, "
					+ " count(distinct(case when FK_provenienca_exame = 3 then fk_exame end)) as p_bue "
					+ " FROM tblamostra WHERE  year(data_darecolha) = ? and month(data_darecolha) = ? ";
			 
		}
		if(origem == 2){
			sql ="SELECT count(distinct(case when a.FK_provenienca_exame = 1 then sec.fk_grupo_analise_clinica end)) as p_consulta,"
			   + " count(distinct(case when a.FK_provenienca_exame = 2 then sec.fk_grupo_analise_clinica end)) as p_externos, "
			   + " count(distinct(case when a.FK_provenienca_exame = 4 then sec.fk_grupo_analise_clinica end)) as p_internamento, "
			   + " count(distinct(case when a.FK_provenienca_exame = 3 then sec.fk_grupo_analise_clinica end)) as p_bue "
			   + " FROM tblamostra a, tblservicosdeexamesclinicos sec WHERE a.fk_exame = sec.id_servicodeanalise_clinica AND "
			   + " year(a.data_darecolha) = ? and month(a.data_darecolha) = ? ";
			 
		}
				
		List<Paciente> lista = new ArrayList<Paciente>();
		try {
			con = Conexao.getConexao();
			PreparedStatement ps = con.prepareStatement(sql);	
			ps.setInt(1, ano);
			ps.setInt(2, mes);
			ResultSet rs = ps.executeQuery();
			while (rs.next()) {
				Paciente exm = new Paciente();
				exm.setP_banco_ue(rs.getInt("p_bue"));
				exm.setP_consulta(rs.getInt("p_consulta"));
				exm.setP_externos(rs.getInt("p_externos"));
				exm.setP_internados(rs.getInt("p_internamento"));
				lista.add(exm);
			}
			ps.close();
			con.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return lista;
	}
	
	public List<Paciente> buscarLabTotalGrupoExamesEmAnoMesDias(int origem,int ano,int mes,int dia1,int dia2){
		String sql = "";
		
		if(origem == 1){
			sql ="SELECT count(distinct(case when FK_provenienca_exame = 1 then fk_exame end)) as p_consulta,"
					+ " count(distinct(case when FK_provenienca_exame = 2 then fk_exame end)) as p_externos, "
					+ " count(distinct(case when FK_provenienca_exame = 4 then fk_exame end)) as p_internamento, "
					+ " count(distinct(case when FK_provenienca_exame = 3 then fk_exame end)) as p_bue "
					+ " FROM tblamostra WHERE  year(data_darecolha) = ? and month(data_darecolha) = ? and day(data_darecolha) between ? and ? ";
			 
		}
		if(origem == 2){
			sql ="SELECT count(distinct(case when a.FK_provenienca_exame = 1 then sec.fk_grupo_analise_clinica end)) as p_consulta,"
			   + " count(distinct(case when a.FK_provenienca_exame = 2 then sec.fk_grupo_analise_clinica end)) as p_externos, "
			   + " count(distinct(case when a.FK_provenienca_exame = 4 then sec.fk_grupo_analise_clinica end)) as p_internamento, "
			   + " count(distinct(case when a.FK_provenienca_exame = 3 then sec.fk_grupo_analise_clinica end)) as p_bue "
			   + " FROM tblamostra a, tblservicosdeexamesclinicos sec WHERE a.fk_exame = sec.id_servicodeanalise_clinica AND "
			   + " year(a.data_darecolha) = ? and month(a.data_darecolha) = ? and day(a.data_darecolha) between ? and ? ";
			 
		}
				
		List<Paciente> lista = new ArrayList<Paciente>();
		try {
			con = Conexao.getConexao();
			PreparedStatement ps = con.prepareStatement(sql);	
			ps.setInt(1, ano);
			ps.setInt(2, mes);
			ps.setInt(3, dia1);
			ps.setInt(4, dia2);
			ResultSet rs = ps.executeQuery();
			while (rs.next()) {
				Paciente exm = new Paciente();
				exm.setP_banco_ue(rs.getInt("p_bue"));
				exm.setP_consulta(rs.getInt("p_consulta"));
				exm.setP_externos(rs.getInt("p_externos"));
				exm.setP_internados(rs.getInt("p_internamento"));
				lista.add(exm);
			}
			ps.close();
			con.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return lista;
	}
	
	public List<Paciente> buscarLabTotalGrupoExamesEmAnoSeTrimestre(int origem,int ano,int aux){
		String sql = "";
		
		if(origem == 1){
			if(aux == 1){
				sql ="SELECT count(distinct(case when FK_provenienca_exame = 1 then fk_exame end)) as p_consulta,"
						+ " count(distinct(case when FK_provenienca_exame = 2 then fk_exame end)) as p_externos, "
						+ " count(distinct(case when FK_provenienca_exame = 4 then fk_exame end)) as p_internamento, "
						+ " count(distinct(case when FK_provenienca_exame = 3 then fk_exame end)) as p_bue "
						+ " FROM tblamostra WHERE  year(data_darecolha) = ? and month(data_darecolha) between 1 and 3 ";
			}
			if(aux == 2){
				sql ="SELECT count(distinct(case when FK_provenienca_exame = 1 then fk_exame end)) as p_consulta,"
						+ " count(distinct(case when FK_provenienca_exame = 2 then fk_exame end)) as p_externos, "
						+ " count(distinct(case when FK_provenienca_exame = 4 then fk_exame end)) as p_internamento, "
						+ " count(distinct(case when FK_provenienca_exame = 3 then fk_exame end)) as p_bue "
						+ " FROM tblamostra WHERE  year(data_darecolha) = ? and month(data_darecolha) between 4 and 6 ";
			}
			if(aux == 3){
				sql ="SELECT count(distinct(case when FK_provenienca_exame = 1 then fk_exame end)) as p_consulta,"
						+ " count(distinct(case when FK_provenienca_exame = 2 then fk_exame end)) as p_externos, "
						+ " count(distinct(case when FK_provenienca_exame = 4 then fk_exame end)) as p_internamento, "
						+ " count(distinct(case when FK_provenienca_exame = 3 then fk_exame end)) as p_bue "
						+ " FROM tblamostra WHERE  year(data_darecolha) = ? and month(data_darecolha) between 7 and 9 ";
			}
			if(aux == 4){
				sql ="SELECT count(distinct(case when FK_provenienca_exame = 1 then fk_exame end)) as p_consulta,"
						+ " count(distinct(case when FK_provenienca_exame = 2 then fk_exame end)) as p_externos, "
						+ " count(distinct(case when FK_provenienca_exame = 4 then fk_exame end)) as p_internamento, "
						+ " count(distinct(case when FK_provenienca_exame = 3 then fk_exame end)) as p_bue "
						+ " FROM tblamostra WHERE  year(data_darecolha) = ? and month(data_darecolha) between 10 and 12 ";
			}
			if(aux == 5){
				sql ="SELECT count(distinct(case when FK_provenienca_exame = 1 then fk_exame end)) as p_consulta,"
						+ " count(distinct(case when FK_provenienca_exame = 2 then fk_exame end)) as p_externos, "
						+ " count(distinct(case when FK_provenienca_exame = 4 then fk_exame end)) as p_internamento, "
						+ " count(distinct(case when FK_provenienca_exame = 3 then fk_exame end)) as p_bue "
						+ " FROM tblamostra WHERE  year(data_darecolha) = ? and month(data_darecolha) between 1 and 6 ";
			}
			if(aux == 6){
				sql ="SELECT count(distinct(case when FK_provenienca_exame = 1 then fk_exame end)) as p_consulta,"
						+ " count(distinct(case when FK_provenienca_exame = 2 then fk_exame end)) as p_externos, "
						+ " count(distinct(case when FK_provenienca_exame = 4 then fk_exame end)) as p_internamento, "
						+ " count(distinct(case when FK_provenienca_exame = 3 then fk_exame end)) as p_bue "
						+ " FROM tblamostra WHERE  year(data_darecolha) = ? and month(data_darecolha) between 7 and 12 ";
			}
			 
		}
		if(origem == 2){
			if(aux == 1){
				sql ="SELECT count(distinct(case when a.FK_provenienca_exame = 1 then sec.fk_grupo_analise_clinica end)) as p_consulta,"
						   + " count(distinct(case when a.FK_provenienca_exame = 2 then sec.fk_grupo_analise_clinica end)) as p_externos, "
						   + " count(distinct(case when a.FK_provenienca_exame = 4 then sec.fk_grupo_analise_clinica end)) as p_internamento, "
						   + " count(distinct(case when a.FK_provenienca_exame = 3 then sec.fk_grupo_analise_clinica end)) as p_bue "
						   + " FROM tblamostra a, tblservicosdeexamesclinicos sec WHERE a.fk_exame = sec.id_servicodeanalise_clinica AND "
						   + " year(a.data_darecolha) = ? and month(a.data_darecolha) between 1 and 3 ";
			}
			if(aux == 2){
				sql ="SELECT count(distinct(case when a.FK_provenienca_exame = 1 then sec.fk_grupo_analise_clinica end)) as p_consulta,"
						   + " count(distinct(case when a.FK_provenienca_exame = 2 then sec.fk_grupo_analise_clinica end)) as p_externos, "
						   + " count(distinct(case when a.FK_provenienca_exame = 4 then sec.fk_grupo_analise_clinica end)) as p_internamento, "
						   + " count(distinct(case when a.FK_provenienca_exame = 3 then sec.fk_grupo_analise_clinica end)) as p_bue "
						   + " FROM tblamostra a, tblservicosdeexamesclinicos sec WHERE a.fk_exame = sec.id_servicodeanalise_clinica AND "
						   + " year(a.data_darecolha) = ? and month(a.data_darecolha) between 4 and 6 ";
			}
			if(aux == 3){
				sql ="SELECT count(distinct(case when a.FK_provenienca_exame = 1 then sec.fk_grupo_analise_clinica end)) as p_consulta,"
						   + " count(distinct(case when a.FK_provenienca_exame = 2 then sec.fk_grupo_analise_clinica end)) as p_externos, "
						   + " count(distinct(case when a.FK_provenienca_exame = 4 then sec.fk_grupo_analise_clinica end)) as p_internamento, "
						   + " count(distinct(case when a.FK_provenienca_exame = 3 then sec.fk_grupo_analise_clinica end)) as p_bue "
						   + " FROM tblamostra a, tblservicosdeexamesclinicos sec WHERE a.fk_exame = sec.id_servicodeanalise_clinica AND "
						   + " year(a.data_darecolha) = ? and month(a.data_darecolha) between 7 and 9 ";
			}
			if(aux == 4){
				sql ="SELECT count(distinct(case when a.FK_provenienca_exame = 1 then sec.fk_grupo_analise_clinica end)) as p_consulta,"
						   + " count(distinct(case when a.FK_provenienca_exame = 2 then sec.fk_grupo_analise_clinica end)) as p_externos, "
						   + " count(distinct(case when a.FK_provenienca_exame = 4 then sec.fk_grupo_analise_clinica end)) as p_internamento, "
						   + " count(distinct(case when a.FK_provenienca_exame = 3 then sec.fk_grupo_analise_clinica end)) as p_bue "
						   + " FROM tblamostra a, tblservicosdeexamesclinicos sec WHERE a.fk_exame = sec.id_servicodeanalise_clinica AND "
						   + " year(a.data_darecolha) = ? and month(a.data_darecolha) between 10 and 12";
			}
			if(aux == 5){
				sql ="SELECT count(distinct(case when a.FK_provenienca_exame = 1 then sec.fk_grupo_analise_clinica end)) as p_consulta,"
						   + " count(distinct(case when a.FK_provenienca_exame = 2 then sec.fk_grupo_analise_clinica end)) as p_externos, "
						   + " count(distinct(case when a.FK_provenienca_exame = 4 then sec.fk_grupo_analise_clinica end)) as p_internamento, "
						   + " count(distinct(case when a.FK_provenienca_exame = 3 then sec.fk_grupo_analise_clinica end)) as p_bue "
						   + " FROM tblamostra a, tblservicosdeexamesclinicos sec WHERE a.fk_exame = sec.id_servicodeanalise_clinica AND "
						   + " year(a.data_darecolha) = ? and month(a.data_darecolha) between 1 and 6 ";
			}
			if(aux == 6){
				sql ="SELECT count(distinct(case when a.FK_provenienca_exame = 1 then sec.fk_grupo_analise_clinica end)) as p_consulta,"
						   + " count(distinct(case when a.FK_provenienca_exame = 2 then sec.fk_grupo_analise_clinica end)) as p_externos, "
						   + " count(distinct(case when a.FK_provenienca_exame = 4 then sec.fk_grupo_analise_clinica end)) as p_internamento, "
						   + " count(distinct(case when a.FK_provenienca_exame = 3 then sec.fk_grupo_analise_clinica end)) as p_bue "
						   + " FROM tblamostra a, tblservicosdeexamesclinicos sec WHERE a.fk_exame = sec.id_servicodeanalise_clinica AND "
						   + " year(a.data_darecolha) = ? and month(a.data_darecolha) between 7 and 12 ";
			}
			 
		}
				
		List<Paciente> lista = new ArrayList<Paciente>();
		try {
			con = Conexao.getConexao();
			PreparedStatement ps = con.prepareStatement(sql);	
			ps.setInt(1, ano);
			 
			ResultSet rs = ps.executeQuery();
			while (rs.next()) {
				Paciente exm = new Paciente();
				exm.setP_banco_ue(rs.getInt("p_bue"));
				exm.setP_consulta(rs.getInt("p_consulta"));
				exm.setP_externos(rs.getInt("p_externos"));
				exm.setP_internados(rs.getInt("p_internamento"));
				lista.add(exm);
			}
			ps.close();
			con.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return lista;
	}
}
