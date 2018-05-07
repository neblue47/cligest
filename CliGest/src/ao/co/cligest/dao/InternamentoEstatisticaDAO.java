package ao.co.cligest.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import ao.co.cligest.beans.AltaMedicaBean;
import ao.co.cligest.entidades.Diverso;
import ao.co.cligest.entidades.Paciente;

public class InternamentoEstatisticaDAO {

	private Connection con;
	
	public List<Paciente> buscarHistoricoInternadosDiario() {
		String sql = "select count(i.id_internamento) as 'pacientes_internados', COUNT(CASE WHEN i.modo_internamento = 0 THEN 0 END) AS 'por_admissao', CAST(COUNT(CASE WHEN i.modo_internamento = 0 THEN 0 END) * 100 / count(i.id_internamento) AS DECIMAL(10, 1)) as 'p_admissao_total', CAST(COUNT(CASE WHEN i.modo_internamento = 0 AND g.id_genero = 1 THEN 0 END) * 100 / count(i.id_internamento) AS DECIMAL(10, 1)) as 'p_adt_m',"
				   + "CAST(COUNT(CASE WHEN i.modo_internamento = 0 AND g.id_genero = 2 THEN 0 END) * 100 / count(i.id_internamento) AS DECIMAL(10, 1)) as 'p_adt_f',"
				   + "COUNT(CASE WHEN i.modo_internamento = 1 THEN 0 END) AS 'por_trans', CAST(COUNT(CASE WHEN i.modo_internamento = 1 THEN 0 END) * 100 / count(i.id_internamento) AS DECIMAL(10, 1)) as 'p_trans_total',"
				   + "CAST(COUNT(CASE WHEN i.modo_internamento = 1 AND g.id_genero = 1 THEN 0 END) * 100 / count(i.id_internamento) AS DECIMAL(10, 1)) as 'p_trans_t_m',"
				   + "CAST(COUNT(CASE WHEN i.modo_internamento = 1 AND g.id_genero = 2 THEN 0 END) * 100 / count(i.id_internamento) AS DECIMAL(10, 1)) as 'p_trans_t_f'"
				   + "from tblinternamento i, tblgenero g, tblcidadao cc where i.FK_paciente = cc.FK_entidade and cc.FK_genero = g.id_genero and i.id_internamento not in (select FK_internamento from tblaltamedica)";
		
		List<Paciente> lista = new ArrayList<Paciente>();
		try {
			con = Conexao.getConexao();
			PreparedStatement ps = con.prepareStatement(sql);
			ResultSet rs = ps.executeQuery();
			while (rs.next()) {
				Paciente p = new Paciente();
				p.setQuantidade(rs.getInt("pacientes_internados"));
				
				p.setQtd_admitido(rs.getInt("por_admissao"));
				p.setPerc_admitido(rs.getDouble("p_admissao_total"));
				p.setPerc_admitido_msc(rs.getDouble("p_adt_m"));
				p.setPerc_admitido_fmn(rs.getDouble("p_adt_f"));
				
				p.setQdt_transferido(rs.getInt("por_trans"));
				p.setPerc_transferido(rs.getDouble("p_trans_total"));
				p.setPerc_transferido_msc(rs.getDouble("p_trans_t_m"));
				p.setPerc_transferido_fmn(rs.getDouble("p_trans_t_f"));
				p.setDescricao("Internados");
				
				lista.add(p);
				
			}
			ps.close();
			con.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return lista;
	}
	
	
	
	
	public List<Paciente> buscarHistoricoInternadosEmAnos(int ano) {
		String sql = "select count(i.id_internamento) as 'pacientes_internados', COUNT(CASE WHEN i.modo_internamento = 0 THEN 0 END) AS 'por_admissao', CAST(COUNT(CASE WHEN i.modo_internamento = 0 THEN 0 END) * 100 / count(i.id_internamento) AS DECIMAL(10, 1)) as 'p_admissao_total', CAST(COUNT(CASE WHEN i.modo_internamento = 0 AND g.id_genero = 1 THEN 0 END) * 100 / count(i.id_internamento) AS DECIMAL(10, 1)) as 'p_adt_m',"
				   + "CAST(COUNT(CASE WHEN i.modo_internamento = 0 AND g.id_genero = 2 THEN 0 END) * 100 / count(i.id_internamento) AS DECIMAL(10, 1)) as 'p_adt_f',"
				   + "COUNT(CASE WHEN i.modo_internamento = 1 THEN 0 END) AS 'por_trans', CAST(COUNT(CASE WHEN i.modo_internamento = 1 THEN 0 END) * 100 / count(i.id_internamento) AS DECIMAL(10, 1)) as 'p_trans_total',"
				   + "CAST(COUNT(CASE WHEN i.modo_internamento = 1 AND g.id_genero = 1 THEN 0 END) * 100 / count(i.id_internamento) AS DECIMAL(10, 1)) as 'p_trans_t_m',"
				   + "CAST(COUNT(CASE WHEN i.modo_internamento = 1 AND g.id_genero = 2 THEN 0 END) * 100 / count(i.id_internamento) AS DECIMAL(10, 1)) as 'p_trans_t_f'"
				   + "from tblinternamento i, tblgenero g, tblcidadao cc where i.FK_paciente = cc.FK_entidade and cc.FK_genero = g.id_genero "
				   + "and year(i.data_internamento) = ? and i.id_internamento not in (select FK_internamento from tblaltamedica)";
		
		List<Paciente> lista = new ArrayList<Paciente>();
		try {
			con = Conexao.getConexao();
			PreparedStatement ps = con.prepareStatement(sql);
			ps.setInt(1, ano);
			ResultSet rs = ps.executeQuery();
			while (rs.next()) {
				Paciente p = new Paciente();
				p.setQuantidade(rs.getInt("pacientes_internados"));
				
				p.setQtd_admitido(rs.getInt("por_admissao"));
				p.setPerc_admitido(rs.getDouble("p_admissao_total"));
				p.setPerc_admitido_msc(rs.getDouble("p_adt_m"));
				p.setPerc_admitido_fmn(rs.getDouble("p_adt_f"));
				
				p.setQdt_transferido(rs.getInt("por_trans"));
				p.setPerc_transferido(rs.getDouble("p_trans_total"));
				p.setPerc_transferido_msc(rs.getDouble("p_trans_t_m"));
				p.setPerc_transferido_fmn(rs.getDouble("p_trans_t_f"));
				p.setDescricao("Internados");
				
				lista.add(p);
				
			}
			ps.close();
			con.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return lista;
	}
	
	public List<Paciente> buscarHistoricoInternadosEmAnosMes(int ano,int mes) {
		String sql = "select count(i.id_internamento) as 'pacientes_internados', COUNT(CASE WHEN i.modo_internamento = 0 THEN 0 END) AS 'por_admissao', CAST(COUNT(CASE WHEN i.modo_internamento = 0 THEN 0 END) * 100 / count(i.id_internamento) AS DECIMAL(10, 1)) as 'p_admissao_total', CAST(COUNT(CASE WHEN i.modo_internamento = 0 AND g.id_genero = 1 THEN 0 END) * 100 / count(i.id_internamento) AS DECIMAL(10, 1)) as 'p_adt_m',"
				   + "CAST(COUNT(CASE WHEN i.modo_internamento = 0 AND g.id_genero = 2 THEN 0 END) * 100 / count(i.id_internamento) AS DECIMAL(10, 1)) as 'p_adt_f',"
				   + "COUNT(CASE WHEN i.modo_internamento = 1 THEN 0 END) AS 'por_trans', CAST(COUNT(CASE WHEN i.modo_internamento = 1 THEN 0 END) * 100 / count(i.id_internamento) AS DECIMAL(10, 1)) as 'p_trans_total',"
				   + "CAST(COUNT(CASE WHEN i.modo_internamento = 1 AND g.id_genero = 1 THEN 0 END) * 100 / count(i.id_internamento) AS DECIMAL(10, 1)) as 'p_trans_t_m',"
				   + "CAST(COUNT(CASE WHEN i.modo_internamento = 1 AND g.id_genero = 2 THEN 0 END) * 100 / count(i.id_internamento) AS DECIMAL(10, 1)) as 'p_trans_t_f'"
				   + "from tblinternamento i, tblgenero g, tblcidadao cc where i.FK_paciente = cc.FK_entidade and cc.FK_genero = g.id_genero "
				   + "and year(i.data_internamento) = ? and month(i.data_internamento) = ?  and i.id_internamento not in (select FK_internamento from tblaltamedica)";
		
		List<Paciente> lista = new ArrayList<Paciente>();
		try {
			con = Conexao.getConexao();
			PreparedStatement ps = con.prepareStatement(sql);
			ps.setInt(1, ano);
			ps.setInt(2, mes);
			ResultSet rs = ps.executeQuery();
			while (rs.next()) {
				Paciente p = new Paciente();
				p.setQuantidade(rs.getInt("pacientes_internados"));
				
				p.setQtd_admitido(rs.getInt("por_admissao"));
				p.setPerc_admitido(rs.getDouble("p_admissao_total"));
				p.setPerc_admitido_msc(rs.getDouble("p_adt_m"));
				p.setPerc_admitido_fmn(rs.getDouble("p_adt_f"));
				
				p.setQdt_transferido(rs.getInt("por_trans"));
				p.setPerc_transferido(rs.getDouble("p_trans_total"));
				p.setPerc_transferido_msc(rs.getDouble("p_trans_t_m"));
				p.setPerc_transferido_fmn(rs.getDouble("p_trans_t_f"));
				p.setDescricao("Internados");
				
				lista.add(p);
				
			}
			ps.close();
			con.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return lista;
	}
	
	public List<Paciente> buscarHistoricoInternadosEmAnosMesDias(int ano,int mes,int dia1,int dia2) {
		String sql = "select count(i.id_internamento) as 'pacientes_internados', COUNT(CASE WHEN i.modo_internamento = 0 THEN 0 END) AS 'por_admissao', CAST(COUNT(CASE WHEN i.modo_internamento = 0 THEN 0 END) * 100 / count(i.id_internamento) AS DECIMAL(10, 1)) as 'p_admissao_total', CAST(COUNT(CASE WHEN i.modo_internamento = 0 AND g.id_genero = 1 THEN 0 END) * 100 / count(i.id_internamento) AS DECIMAL(10, 1)) as 'p_adt_m',"
				   + "CAST(COUNT(CASE WHEN i.modo_internamento = 0 AND g.id_genero = 2 THEN 0 END) * 100 / count(i.id_internamento) AS DECIMAL(10, 1)) as 'p_adt_f',"
				   + "COUNT(CASE WHEN i.modo_internamento = 1 THEN 0 END) AS 'por_trans', CAST(COUNT(CASE WHEN i.modo_internamento = 1 THEN 0 END) * 100 / count(i.id_internamento) AS DECIMAL(10, 1)) as 'p_trans_total',"
				   + "CAST(COUNT(CASE WHEN i.modo_internamento = 1 AND g.id_genero = 1 THEN 0 END) * 100 / count(i.id_internamento) AS DECIMAL(10, 1)) as 'p_trans_t_m',"
				   + "CAST(COUNT(CASE WHEN i.modo_internamento = 1 AND g.id_genero = 2 THEN 0 END) * 100 / count(i.id_internamento) AS DECIMAL(10, 1)) as 'p_trans_t_f'"
				   + "from tblinternamento i, tblgenero g, tblcidadao cc where i.FK_paciente = cc.FK_entidade and cc.FK_genero = g.id_genero "
				   + "and year(i.data_internamento) = ? and month(i.data_internamento) = ? and (day(i.data_internamento) between ? and ?)  and i.id_internamento not in (select FK_internamento from tblaltamedica)";
		
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
				Paciente p = new Paciente();
				p.setQuantidade(rs.getInt("pacientes_internados"));
				
				p.setQtd_admitido(rs.getInt("por_admissao"));
				p.setPerc_admitido(rs.getDouble("p_admissao_total"));
				p.setPerc_admitido_msc(rs.getDouble("p_adt_m"));
				p.setPerc_admitido_fmn(rs.getDouble("p_adt_f"));
				
				p.setQdt_transferido(rs.getInt("por_trans"));
				p.setPerc_transferido(rs.getDouble("p_trans_total"));
				p.setPerc_transferido_msc(rs.getDouble("p_trans_t_m"));
				p.setPerc_transferido_fmn(rs.getDouble("p_trans_t_f"));
				p.setDescricao("Internados");
				
				lista.add(p);
				
			}
			ps.close();
			con.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return lista;
	}
	
	public List<Paciente> buscarHistoricoInternadosEmAnosSeTrimestre(int ano,int aux) {
		String sql = "";
		if(aux == 1){
			sql = "select count(i.id_internamento) as 'pacientes_internados', COUNT(CASE WHEN i.modo_internamento = 0 THEN 0 END) AS 'por_admissao', CAST(COUNT(CASE WHEN i.modo_internamento = 0 THEN 0 END) * 100 / count(i.id_internamento) AS DECIMAL(10, 1)) as 'p_admissao_total', CAST(COUNT(CASE WHEN i.modo_internamento = 0 AND g.id_genero = 1 THEN 0 END) * 100 / count(i.id_internamento) AS DECIMAL(10, 1)) as 'p_adt_m',"
					   + "CAST(COUNT(CASE WHEN i.modo_internamento = 0 AND g.id_genero = 2 THEN 0 END) * 100 / count(i.id_internamento) AS DECIMAL(10, 1)) as 'p_adt_f',"
					   + "COUNT(CASE WHEN i.modo_internamento = 1 THEN 0 END) AS 'por_trans', CAST(COUNT(CASE WHEN i.modo_internamento = 1 THEN 0 END) * 100 / count(i.id_internamento) AS DECIMAL(10, 1)) as 'p_trans_total',"
					   + "CAST(COUNT(CASE WHEN i.modo_internamento = 1 AND g.id_genero = 1 THEN 0 END) * 100 / count(i.id_internamento) AS DECIMAL(10, 1)) as 'p_trans_t_m',"
					   + "CAST(COUNT(CASE WHEN i.modo_internamento = 1 AND g.id_genero = 2 THEN 0 END) * 100 / count(i.id_internamento) AS DECIMAL(10, 1)) as 'p_trans_t_f'"
					   + "from tblinternamento i, tblgenero g, tblcidadao cc where i.FK_paciente = cc.FK_entidade and cc.FK_genero = g.id_genero "
					   + "and year(i.data_internamento) = ? and month(i.data_internamento) between 1 and 3  and i.id_internamento not in (select FK_internamento from tblaltamedica)";
		}
		if(aux == 2){
			sql = "select count(i.id_internamento) as 'pacientes_internados', COUNT(CASE WHEN i.modo_internamento = 0 THEN 0 END) AS 'por_admissao', CAST(COUNT(CASE WHEN i.modo_internamento = 0 THEN 0 END) * 100 / count(i.id_internamento) AS DECIMAL(10, 1)) as 'p_admissao_total', CAST(COUNT(CASE WHEN i.modo_internamento = 0 AND g.id_genero = 1 THEN 0 END) * 100 / count(i.id_internamento) AS DECIMAL(10, 1)) as 'p_adt_m',"
					   + "CAST(COUNT(CASE WHEN i.modo_internamento = 0 AND g.id_genero = 2 THEN 0 END) * 100 / count(i.id_internamento) AS DECIMAL(10, 1)) as 'p_adt_f',"
					   + "COUNT(CASE WHEN i.modo_internamento = 1 THEN 0 END) AS 'por_trans', CAST(COUNT(CASE WHEN i.modo_internamento = 1 THEN 0 END) * 100 / count(i.id_internamento) AS DECIMAL(10, 1)) as 'p_trans_total',"
					   + "CAST(COUNT(CASE WHEN i.modo_internamento = 1 AND g.id_genero = 1 THEN 0 END) * 100 / count(i.id_internamento) AS DECIMAL(10, 1)) as 'p_trans_t_m',"
					   + "CAST(COUNT(CASE WHEN i.modo_internamento = 1 AND g.id_genero = 2 THEN 0 END) * 100 / count(i.id_internamento) AS DECIMAL(10, 1)) as 'p_trans_t_f'"
					   + "from tblinternamento i, tblgenero g, tblcidadao cc where i.FK_paciente = cc.FK_entidade and cc.FK_genero = g.id_genero "
					   + "and year(i.data_internamento) = ? and month(i.data_internamento) between 4 and 6  and i.id_internamento not in (select FK_internamento from tblaltamedica)";
		}
		if(aux == 3){
			sql = "select count(i.id_internamento) as 'pacientes_internados', COUNT(CASE WHEN i.modo_internamento = 0 THEN 0 END) AS 'por_admissao', CAST(COUNT(CASE WHEN i.modo_internamento = 0 THEN 0 END) * 100 / count(i.id_internamento) AS DECIMAL(10, 1)) as 'p_admissao_total', CAST(COUNT(CASE WHEN i.modo_internamento = 0 AND g.id_genero = 1 THEN 0 END) * 100 / count(i.id_internamento) AS DECIMAL(10, 1)) as 'p_adt_m',"
					   + "CAST(COUNT(CASE WHEN i.modo_internamento = 0 AND g.id_genero = 2 THEN 0 END) * 100 / count(i.id_internamento) AS DECIMAL(10, 1)) as 'p_adt_f',"
					   + "COUNT(CASE WHEN i.modo_internamento = 1 THEN 0 END) AS 'por_trans', CAST(COUNT(CASE WHEN i.modo_internamento = 1 THEN 0 END) * 100 / count(i.id_internamento) AS DECIMAL(10, 1)) as 'p_trans_total',"
					   + "CAST(COUNT(CASE WHEN i.modo_internamento = 1 AND g.id_genero = 1 THEN 0 END) * 100 / count(i.id_internamento) AS DECIMAL(10, 1)) as 'p_trans_t_m',"
					   + "CAST(COUNT(CASE WHEN i.modo_internamento = 1 AND g.id_genero = 2 THEN 0 END) * 100 / count(i.id_internamento) AS DECIMAL(10, 1)) as 'p_trans_t_f'"
					   + "from tblinternamento i, tblgenero g, tblcidadao cc where i.FK_paciente = cc.FK_entidade and cc.FK_genero = g.id_genero "
					   + "and year(i.data_internamento) = ? and month(i.data_internamento) between 7 and 9  and i.id_internamento not in (select FK_internamento from tblaltamedica)";
		}
		if(aux == 4){
			sql = "select count(i.id_internamento) as 'pacientes_internados', COUNT(CASE WHEN i.modo_internamento = 0 THEN 0 END) AS 'por_admissao', CAST(COUNT(CASE WHEN i.modo_internamento = 0 THEN 0 END) * 100 / count(i.id_internamento) AS DECIMAL(10, 1)) as 'p_admissao_total', CAST(COUNT(CASE WHEN i.modo_internamento = 0 AND g.id_genero = 1 THEN 0 END) * 100 / count(i.id_internamento) AS DECIMAL(10, 1)) as 'p_adt_m',"
					   + "CAST(COUNT(CASE WHEN i.modo_internamento = 0 AND g.id_genero = 2 THEN 0 END) * 100 / count(i.id_internamento) AS DECIMAL(10, 1)) as 'p_adt_f',"
					   + "COUNT(CASE WHEN i.modo_internamento = 1 THEN 0 END) AS 'por_trans', CAST(COUNT(CASE WHEN i.modo_internamento = 1 THEN 0 END) * 100 / count(i.id_internamento) AS DECIMAL(10, 1)) as 'p_trans_total',"
					   + "CAST(COUNT(CASE WHEN i.modo_internamento = 1 AND g.id_genero = 1 THEN 0 END) * 100 / count(i.id_internamento) AS DECIMAL(10, 1)) as 'p_trans_t_m',"
					   + "CAST(COUNT(CASE WHEN i.modo_internamento = 1 AND g.id_genero = 2 THEN 0 END) * 100 / count(i.id_internamento) AS DECIMAL(10, 1)) as 'p_trans_t_f'"
					   + "from tblinternamento i, tblgenero g, tblcidadao cc where i.FK_paciente = cc.FK_entidade and cc.FK_genero = g.id_genero "
					   + "and year(i.data_internamento) = ? and month(i.data_internamento) between 10 and 12  and i.id_internamento not in (select FK_internamento from tblaltamedica)";
		}
		if(aux == 5){
			sql = "select count(i.id_internamento) as 'pacientes_internados', COUNT(CASE WHEN i.modo_internamento = 0 THEN 0 END) AS 'por_admissao', CAST(COUNT(CASE WHEN i.modo_internamento = 0 THEN 0 END) * 100 / count(i.id_internamento) AS DECIMAL(10, 1)) as 'p_admissao_total', CAST(COUNT(CASE WHEN i.modo_internamento = 0 AND g.id_genero = 1 THEN 0 END) * 100 / count(i.id_internamento) AS DECIMAL(10, 1)) as 'p_adt_m',"
					   + "CAST(COUNT(CASE WHEN i.modo_internamento = 0 AND g.id_genero = 2 THEN 0 END) * 100 / count(i.id_internamento) AS DECIMAL(10, 1)) as 'p_adt_f',"
					   + "COUNT(CASE WHEN i.modo_internamento = 1 THEN 0 END) AS 'por_trans', CAST(COUNT(CASE WHEN i.modo_internamento = 1 THEN 0 END) * 100 / count(i.id_internamento) AS DECIMAL(10, 1)) as 'p_trans_total',"
					   + "CAST(COUNT(CASE WHEN i.modo_internamento = 1 AND g.id_genero = 1 THEN 0 END) * 100 / count(i.id_internamento) AS DECIMAL(10, 1)) as 'p_trans_t_m',"
					   + "CAST(COUNT(CASE WHEN i.modo_internamento = 1 AND g.id_genero = 2 THEN 0 END) * 100 / count(i.id_internamento) AS DECIMAL(10, 1)) as 'p_trans_t_f'"
					   + "from tblinternamento i, tblgenero g, tblcidadao cc where i.FK_paciente = cc.FK_entidade and cc.FK_genero = g.id_genero "
					   + "and year(i.data_internamento) = ? and month(i.data_internamento) between 1 and 6  and i.id_internamento not in (select FK_internamento from tblaltamedica)";
		}
		if(aux == 6){
			sql = "select count(i.id_internamento) as 'pacientes_internados', COUNT(CASE WHEN i.modo_internamento = 0 THEN 0 END) AS 'por_admissao', CAST(COUNT(CASE WHEN i.modo_internamento = 0 THEN 0 END) * 100 / count(i.id_internamento) AS DECIMAL(10, 1)) as 'p_admissao_total', CAST(COUNT(CASE WHEN i.modo_internamento = 0 AND g.id_genero = 1 THEN 0 END) * 100 / count(i.id_internamento) AS DECIMAL(10, 1)) as 'p_adt_m',"
					   + "CAST(COUNT(CASE WHEN i.modo_internamento = 0 AND g.id_genero = 2 THEN 0 END) * 100 / count(i.id_internamento) AS DECIMAL(10, 1)) as 'p_adt_f',"
					   + "COUNT(CASE WHEN i.modo_internamento = 1 THEN 0 END) AS 'por_trans', CAST(COUNT(CASE WHEN i.modo_internamento = 1 THEN 0 END) * 100 / count(i.id_internamento) AS DECIMAL(10, 1)) as 'p_trans_total',"
					   + "CAST(COUNT(CASE WHEN i.modo_internamento = 1 AND g.id_genero = 1 THEN 0 END) * 100 / count(i.id_internamento) AS DECIMAL(10, 1)) as 'p_trans_t_m',"
					   + "CAST(COUNT(CASE WHEN i.modo_internamento = 1 AND g.id_genero = 2 THEN 0 END) * 100 / count(i.id_internamento) AS DECIMAL(10, 1)) as 'p_trans_t_f'"
					   + "from tblinternamento i, tblgenero g, tblcidadao cc where i.FK_paciente = cc.FK_entidade and cc.FK_genero = g.id_genero "
					   + "and year(i.data_internamento) = ? and month(i.data_internamento) between 7 and 12  and i.id_internamento not in (select FK_internamento from tblaltamedica)";
		}

		List<Paciente> lista = new ArrayList<Paciente>();
		try {
			con = Conexao.getConexao();
			PreparedStatement ps = con.prepareStatement(sql);
			ps.setInt(1, ano);
			ResultSet rs = ps.executeQuery();
			while (rs.next()) {
				Paciente p = new Paciente();
				p.setQuantidade(rs.getInt("pacientes_internados"));
				
				p.setQtd_admitido(rs.getInt("por_admissao"));
				p.setPerc_admitido(rs.getDouble("p_admissao_total"));
				p.setPerc_admitido_msc(rs.getDouble("p_adt_m"));
				p.setPerc_admitido_fmn(rs.getDouble("p_adt_f"));
				
				p.setQdt_transferido(rs.getInt("por_trans"));
				p.setPerc_transferido(rs.getDouble("p_trans_total"));
				p.setPerc_transferido_msc(rs.getDouble("p_trans_t_m"));
				p.setPerc_transferido_fmn(rs.getDouble("p_trans_t_f"));
				p.setDescricao("Internados");
				
				lista.add(p);
				
			}
			ps.close();
			con.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return lista;
	}
	
	public List<Paciente> buscarHistoricoInternadosEmIdades(int idade1,int idade2) {
		String sql = "select count(i.id_internamento) as 'pacientes_internados', COUNT(CASE WHEN i.modo_internamento = 0 THEN 0 END) AS 'por_admissao', CAST(COUNT(CASE WHEN i.modo_internamento = 0 THEN 0 END) * 100 / count(i.id_internamento) AS DECIMAL(10, 1)) as 'p_admissao_total', CAST(COUNT(CASE WHEN i.modo_internamento = 0 AND g.id_genero = 1 THEN 0 END) * 100 / count(i.id_internamento) AS DECIMAL(10, 1)) as 'p_adt_m',"
				   + "CAST(COUNT(CASE WHEN i.modo_internamento = 0 AND g.id_genero = 2 THEN 0 END) * 100 / count(i.id_internamento) AS DECIMAL(10, 1)) as 'p_adt_f',"
				   + "COUNT(CASE WHEN i.modo_internamento = 1 THEN 0 END) AS 'por_trans', CAST(COUNT(CASE WHEN i.modo_internamento = 1 THEN 0 END) * 100 / count(i.id_internamento) AS DECIMAL(10, 1)) as 'p_trans_total',"
				   + "CAST(COUNT(CASE WHEN i.modo_internamento = 1 AND g.id_genero = 1 THEN 0 END) * 100 / count(i.id_internamento) AS DECIMAL(10, 1)) as 'p_trans_t_m',"
				   + "CAST(COUNT(CASE WHEN i.modo_internamento = 1 AND g.id_genero = 2 THEN 0 END) * 100 / count(i.id_internamento) AS DECIMAL(10, 1)) as 'p_trans_t_f'"
				   + "from tblinternamento i, tblgenero g, tblcidadao cc where i.FK_paciente = cc.FK_entidade and cc.FK_genero = g.id_genero "
				   + "and year(from_days((to_days(now()) - to_days(cc.data_nascimento)))) between ? and ?  and i.id_internamento not in (select FK_internamento from tblaltamedica)";
		
		List<Paciente> lista = new ArrayList<Paciente>();
		try {
			con = Conexao.getConexao();
			PreparedStatement ps = con.prepareStatement(sql);
			ps.setInt(1, idade1);
			ps.setInt(2, idade2);
			ResultSet rs = ps.executeQuery();
			while (rs.next()) {
				Paciente p = new Paciente();
				p.setQuantidade(rs.getInt("pacientes_internados"));
				
				p.setQtd_admitido(rs.getInt("por_admissao"));
				p.setPerc_admitido(rs.getDouble("p_admissao_total"));
				p.setPerc_admitido_msc(rs.getDouble("p_adt_m"));
				p.setPerc_admitido_fmn(rs.getDouble("p_adt_f"));
				
				p.setQdt_transferido(rs.getInt("por_trans"));
				p.setPerc_transferido(rs.getDouble("p_trans_total"));
				p.setPerc_transferido_msc(rs.getDouble("p_trans_t_m"));
				p.setPerc_transferido_fmn(rs.getDouble("p_trans_t_f"));
				p.setDescricao("Internados");
				
				lista.add(p);
				
			}
			ps.close();
			con.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return lista;
	}
	
	public List<Paciente> buscarHistoricoInternadosEmAnoIdades(int ano,int idade1,int idade2) {
		String sql = "select count(i.id_internamento) as 'pacientes_internados', COUNT(CASE WHEN i.modo_internamento = 0 THEN 0 END) AS 'por_admissao', CAST(COUNT(CASE WHEN i.modo_internamento = 0 THEN 0 END) * 100 / count(i.id_internamento) AS DECIMAL(10, 1)) as 'p_admissao_total', CAST(COUNT(CASE WHEN i.modo_internamento = 0 AND g.id_genero = 1 THEN 0 END) * 100 / count(i.id_internamento) AS DECIMAL(10, 1)) as 'p_adt_m',"
				   + "CAST(COUNT(CASE WHEN i.modo_internamento = 0 AND g.id_genero = 2 THEN 0 END) * 100 / count(i.id_internamento) AS DECIMAL(10, 1)) as 'p_adt_f',"
				   + "COUNT(CASE WHEN i.modo_internamento = 1 THEN 0 END) AS 'por_trans', CAST(COUNT(CASE WHEN i.modo_internamento = 1 THEN 0 END) * 100 / count(i.id_internamento) AS DECIMAL(10, 1)) as 'p_trans_total',"
				   + "CAST(COUNT(CASE WHEN i.modo_internamento = 1 AND g.id_genero = 1 THEN 0 END) * 100 / count(i.id_internamento) AS DECIMAL(10, 1)) as 'p_trans_t_m',"
				   + "CAST(COUNT(CASE WHEN i.modo_internamento = 1 AND g.id_genero = 2 THEN 0 END) * 100 / count(i.id_internamento) AS DECIMAL(10, 1)) as 'p_trans_t_f'"
				   + "from tblinternamento i, tblgenero g, tblcidadao cc where i.FK_paciente = cc.FK_entidade and cc.FK_genero = g.id_genero "
				   + "and year(i.data_internamento) = ? and year(from_days((to_days(now()) - to_days(cc.data_nascimento)))) between ? and ?  and i.id_internamento not in (select FK_internamento from tblaltamedica)";
		
		List<Paciente> lista = new ArrayList<Paciente>();
		try {
			con = Conexao.getConexao();
			PreparedStatement ps = con.prepareStatement(sql);
			ps.setInt(1, ano);
			ps.setInt(2, idade1);
			ps.setInt(3, idade2);
			ResultSet rs = ps.executeQuery();
			while (rs.next()) {
				Paciente p = new Paciente();
				p.setQuantidade(rs.getInt("pacientes_internados"));
				
				p.setQtd_admitido(rs.getInt("por_admissao"));
				p.setPerc_admitido(rs.getDouble("p_admissao_total"));
				p.setPerc_admitido_msc(rs.getDouble("p_adt_m"));
				p.setPerc_admitido_fmn(rs.getDouble("p_adt_f"));
				
				p.setQdt_transferido(rs.getInt("por_trans"));
				p.setPerc_transferido(rs.getDouble("p_trans_total"));
				p.setPerc_transferido_msc(rs.getDouble("p_trans_t_m"));
				p.setPerc_transferido_fmn(rs.getDouble("p_trans_t_f"));
				p.setDescricao("Internados");
				
				lista.add(p);
				
			}
			ps.close();
			con.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return lista;
	}
	
	public List<Paciente> buscarHistoricoInternadosEmAnosSeTrimestreIdade(int ano,int aux,int idade1,int idade2) {
		String sql = "";
		if(aux == 1){
			sql = "select count(i.id_internamento) as 'pacientes_internados', COUNT(CASE WHEN i.modo_internamento = 0 THEN 0 END) AS 'por_admissao', CAST(COUNT(CASE WHEN i.modo_internamento = 0 THEN 0 END) * 100 / count(i.id_internamento) AS DECIMAL(10, 1)) as 'p_admissao_total', CAST(COUNT(CASE WHEN i.modo_internamento = 0 AND g.id_genero = 1 THEN 0 END) * 100 / count(i.id_internamento) AS DECIMAL(10, 1)) as 'p_adt_m',"
					   + "CAST(COUNT(CASE WHEN i.modo_internamento = 0 AND g.id_genero = 2 THEN 0 END) * 100 / count(i.id_internamento) AS DECIMAL(10, 1)) as 'p_adt_f',"
					   + "COUNT(CASE WHEN i.modo_internamento = 1 THEN 0 END) AS 'por_trans', CAST(COUNT(CASE WHEN i.modo_internamento = 1 THEN 0 END) * 100 / count(i.id_internamento) AS DECIMAL(10, 1)) as 'p_trans_total',"
					   + "CAST(COUNT(CASE WHEN i.modo_internamento = 1 AND g.id_genero = 1 THEN 0 END) * 100 / count(i.id_internamento) AS DECIMAL(10, 1)) as 'p_trans_t_m',"
					   + "CAST(COUNT(CASE WHEN i.modo_internamento = 1 AND g.id_genero = 2 THEN 0 END) * 100 / count(i.id_internamento) AS DECIMAL(10, 1)) as 'p_trans_t_f'"
					   + "from tblinternamento i, tblgenero g, tblcidadao cc where i.FK_paciente = cc.FK_entidade and cc.FK_genero = g.id_genero "
					   + "and year(i.data_internamento) = ? and month(i.data_internamento) between 1 and 3 and year(from_days((to_days(now()) - to_days(cc.data_nascimento)))) between ? and ?  and i.id_internamento not in (select FK_internamento from tblaltamedica)";
		}
		if(aux == 2){
			sql = "select count(i.id_internamento) as 'pacientes_internados', COUNT(CASE WHEN i.modo_internamento = 0 THEN 0 END) AS 'por_admissao', CAST(COUNT(CASE WHEN i.modo_internamento = 0 THEN 0 END) * 100 / count(i.id_internamento) AS DECIMAL(10, 1)) as 'p_admissao_total', CAST(COUNT(CASE WHEN i.modo_internamento = 0 AND g.id_genero = 1 THEN 0 END) * 100 / count(i.id_internamento) AS DECIMAL(10, 1)) as 'p_adt_m',"
					   + "CAST(COUNT(CASE WHEN i.modo_internamento = 0 AND g.id_genero = 2 THEN 0 END) * 100 / count(i.id_internamento) AS DECIMAL(10, 1)) as 'p_adt_f',"
					   + "COUNT(CASE WHEN i.modo_internamento = 1 THEN 0 END) AS 'por_trans', CAST(COUNT(CASE WHEN i.modo_internamento = 1 THEN 0 END) * 100 / count(i.id_internamento) AS DECIMAL(10, 1)) as 'p_trans_total',"
					   + "CAST(COUNT(CASE WHEN i.modo_internamento = 1 AND g.id_genero = 1 THEN 0 END) * 100 / count(i.id_internamento) AS DECIMAL(10, 1)) as 'p_trans_t_m',"
					   + "CAST(COUNT(CASE WHEN i.modo_internamento = 1 AND g.id_genero = 2 THEN 0 END) * 100 / count(i.id_internamento) AS DECIMAL(10, 1)) as 'p_trans_t_f'"
					   + "from tblinternamento i, tblgenero g, tblcidadao cc where i.FK_paciente = cc.FK_entidade and cc.FK_genero = g.id_genero "
					   + "and year(i.data_internamento) = ? and month(i.data_internamento) between 4 and 6 and year(from_days((to_days(now()) - to_days(cc.data_nascimento)))) between ? and ? and i.id_internamento not in (select FK_internamento from tblaltamedica)";
		}
		if(aux == 3){
			sql = "select count(i.id_internamento) as 'pacientes_internados', COUNT(CASE WHEN i.modo_internamento = 0 THEN 0 END) AS 'por_admissao', CAST(COUNT(CASE WHEN i.modo_internamento = 0 THEN 0 END) * 100 / count(i.id_internamento) AS DECIMAL(10, 1)) as 'p_admissao_total', CAST(COUNT(CASE WHEN i.modo_internamento = 0 AND g.id_genero = 1 THEN 0 END) * 100 / count(i.id_internamento) AS DECIMAL(10, 1)) as 'p_adt_m',"
					   + "CAST(COUNT(CASE WHEN i.modo_internamento = 0 AND g.id_genero = 2 THEN 0 END) * 100 / count(i.id_internamento) AS DECIMAL(10, 1)) as 'p_adt_f',"
					   + "COUNT(CASE WHEN i.modo_internamento = 1 THEN 0 END) AS 'por_trans', CAST(COUNT(CASE WHEN i.modo_internamento = 1 THEN 0 END) * 100 / count(i.id_internamento) AS DECIMAL(10, 1)) as 'p_trans_total',"
					   + "CAST(COUNT(CASE WHEN i.modo_internamento = 1 AND g.id_genero = 1 THEN 0 END) * 100 / count(i.id_internamento) AS DECIMAL(10, 1)) as 'p_trans_t_m',"
					   + "CAST(COUNT(CASE WHEN i.modo_internamento = 1 AND g.id_genero = 2 THEN 0 END) * 100 / count(i.id_internamento) AS DECIMAL(10, 1)) as 'p_trans_t_f'"
					   + "from tblinternamento i, tblgenero g, tblcidadao cc where i.FK_paciente = cc.FK_entidade and cc.FK_genero = g.id_genero "
					   + "and year(i.data_internamento) = ? and month(i.data_internamento) between 7 and 9 and year(from_days((to_days(now()) - to_days(cc.data_nascimento)))) between ? and ? and i.id_internamento not in (select FK_internamento from tblaltamedica)";
		}
		if(aux == 4){
			sql = "select count(i.id_internamento) as 'pacientes_internados', COUNT(CASE WHEN i.modo_internamento = 0 THEN 0 END) AS 'por_admissao', CAST(COUNT(CASE WHEN i.modo_internamento = 0 THEN 0 END) * 100 / count(i.id_internamento) AS DECIMAL(10, 1)) as 'p_admissao_total', CAST(COUNT(CASE WHEN i.modo_internamento = 0 AND g.id_genero = 1 THEN 0 END) * 100 / count(i.id_internamento) AS DECIMAL(10, 1)) as 'p_adt_m',"
					   + "CAST(COUNT(CASE WHEN i.modo_internamento = 0 AND g.id_genero = 2 THEN 0 END) * 100 / count(i.id_internamento) AS DECIMAL(10, 1)) as 'p_adt_f',"
					   + "COUNT(CASE WHEN i.modo_internamento = 1 THEN 0 END) AS 'por_trans', CAST(COUNT(CASE WHEN i.modo_internamento = 1 THEN 0 END) * 100 / count(i.id_internamento) AS DECIMAL(10, 1)) as 'p_trans_total',"
					   + "CAST(COUNT(CASE WHEN i.modo_internamento = 1 AND g.id_genero = 1 THEN 0 END) * 100 / count(i.id_internamento) AS DECIMAL(10, 1)) as 'p_trans_t_m',"
					   + "CAST(COUNT(CASE WHEN i.modo_internamento = 1 AND g.id_genero = 2 THEN 0 END) * 100 / count(i.id_internamento) AS DECIMAL(10, 1)) as 'p_trans_t_f'"
					   + "from tblinternamento i, tblgenero g, tblcidadao cc where i.FK_paciente = cc.FK_entidade and cc.FK_genero = g.id_genero "
					   + "and year(i.data_internamento) = ? and month(i.data_internamento) between 10 and 12 and year(from_days((to_days(now()) - to_days(cc.data_nascimento)))) between ? and ? and i.id_internamento not in (select FK_internamento from tblaltamedica)";
		}
		if(aux == 5){
			sql = "select count(i.id_internamento) as 'pacientes_internados', COUNT(CASE WHEN i.modo_internamento = 0 THEN 0 END) AS 'por_admissao', CAST(COUNT(CASE WHEN i.modo_internamento = 0 THEN 0 END) * 100 / count(i.id_internamento) AS DECIMAL(10, 1)) as 'p_admissao_total', CAST(COUNT(CASE WHEN i.modo_internamento = 0 AND g.id_genero = 1 THEN 0 END) * 100 / count(i.id_internamento) AS DECIMAL(10, 1)) as 'p_adt_m',"
					   + "CAST(COUNT(CASE WHEN i.modo_internamento = 0 AND g.id_genero = 2 THEN 0 END) * 100 / count(i.id_internamento) AS DECIMAL(10, 1)) as 'p_adt_f',"
					   + "COUNT(CASE WHEN i.modo_internamento = 1 THEN 0 END) AS 'por_trans', CAST(COUNT(CASE WHEN i.modo_internamento = 1 THEN 0 END) * 100 / count(i.id_internamento) AS DECIMAL(10, 1)) as 'p_trans_total',"
					   + "CAST(COUNT(CASE WHEN i.modo_internamento = 1 AND g.id_genero = 1 THEN 0 END) * 100 / count(i.id_internamento) AS DECIMAL(10, 1)) as 'p_trans_t_m',"
					   + "CAST(COUNT(CASE WHEN i.modo_internamento = 1 AND g.id_genero = 2 THEN 0 END) * 100 / count(i.id_internamento) AS DECIMAL(10, 1)) as 'p_trans_t_f'"
					   + "from tblinternamento i, tblgenero g, tblcidadao cc where i.FK_paciente = cc.FK_entidade and cc.FK_genero = g.id_genero "
					   + "and year(i.data_internamento) = ? and month(i.data_internamento) between 1 and 6  and i.id_internamento not in (select FK_internamento from tblaltamedica)";
		}
		if(aux == 6){
			sql = "select count(i.id_internamento) as 'pacientes_internados', COUNT(CASE WHEN i.modo_internamento = 0 THEN 0 END) AS 'por_admissao', CAST(COUNT(CASE WHEN i.modo_internamento = 0 THEN 0 END) * 100 / count(i.id_internamento) AS DECIMAL(10, 1)) as 'p_admissao_total', CAST(COUNT(CASE WHEN i.modo_internamento = 0 AND g.id_genero = 1 THEN 0 END) * 100 / count(i.id_internamento) AS DECIMAL(10, 1)) as 'p_adt_m',"
					   + "CAST(COUNT(CASE WHEN i.modo_internamento = 0 AND g.id_genero = 2 THEN 0 END) * 100 / count(i.id_internamento) AS DECIMAL(10, 1)) as 'p_adt_f',"
					   + "COUNT(CASE WHEN i.modo_internamento = 1 THEN 0 END) AS 'por_trans', CAST(COUNT(CASE WHEN i.modo_internamento = 1 THEN 0 END) * 100 / count(i.id_internamento) AS DECIMAL(10, 1)) as 'p_trans_total',"
					   + "CAST(COUNT(CASE WHEN i.modo_internamento = 1 AND g.id_genero = 1 THEN 0 END) * 100 / count(i.id_internamento) AS DECIMAL(10, 1)) as 'p_trans_t_m',"
					   + "CAST(COUNT(CASE WHEN i.modo_internamento = 1 AND g.id_genero = 2 THEN 0 END) * 100 / count(i.id_internamento) AS DECIMAL(10, 1)) as 'p_trans_t_f'"
					   + "from tblinternamento i, tblgenero g, tblcidadao cc where i.FK_paciente = cc.FK_entidade and cc.FK_genero = g.id_genero "
					   + "and year(i.data_internamento) = ? and month(i.data_internamento) between 7 and 12 and year(from_days((to_days(now()) - to_days(cc.data_nascimento)))) between ? and ? and i.id_internamento not in (select FK_internamento from tblaltamedica)";
		}

		List<Paciente> lista = new ArrayList<Paciente>();
		try {
			con = Conexao.getConexao();
			PreparedStatement ps = con.prepareStatement(sql);
			ps.setInt(1, ano);
			ps.setInt(2, idade1);
			ps.setInt(3, idade2);
			ResultSet rs = ps.executeQuery();
			while (rs.next()) {
				Paciente p = new Paciente();
				p.setQuantidade(rs.getInt("pacientes_internados"));
				
				p.setQtd_admitido(rs.getInt("por_admissao"));
				p.setPerc_admitido(rs.getDouble("p_admissao_total"));
				p.setPerc_admitido_msc(rs.getDouble("p_adt_m"));
				p.setPerc_admitido_fmn(rs.getDouble("p_adt_f"));
				
				p.setQdt_transferido(rs.getInt("por_trans"));
				p.setPerc_transferido(rs.getDouble("p_trans_total"));
				p.setPerc_transferido_msc(rs.getDouble("p_trans_t_m"));
				p.setPerc_transferido_fmn(rs.getDouble("p_trans_t_f"));
				p.setDescricao("Internados");
				
				lista.add(p);
				
			}
			ps.close();
			con.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return lista;
	}
	
	public List<Paciente> buscarHistoricoInternadosEmAnosMesDiasIdades(int ano,int mes,int dia1,int dia2,int idade1,int idade2) {
		String sql = "select count(i.id_internamento) as 'pacientes_internados', COUNT(CASE WHEN i.modo_internamento = 0 THEN 0 END) AS 'por_admissao', CAST(COUNT(CASE WHEN i.modo_internamento = 0 THEN 0 END) * 100 / count(i.id_internamento) AS DECIMAL(10, 1)) as 'p_admissao_total', CAST(COUNT(CASE WHEN i.modo_internamento = 0 AND g.id_genero = 1 THEN 0 END) * 100 / count(i.id_internamento) AS DECIMAL(10, 1)) as 'p_adt_m',"
				   + "CAST(COUNT(CASE WHEN i.modo_internamento = 0 AND g.id_genero = 2 THEN 0 END) * 100 / count(i.id_internamento) AS DECIMAL(10, 1)) as 'p_adt_f',"
				   + "COUNT(CASE WHEN i.modo_internamento = 1 THEN 0 END) AS 'por_trans', CAST(COUNT(CASE WHEN i.modo_internamento = 1 THEN 0 END) * 100 / count(i.id_internamento) AS DECIMAL(10, 1)) as 'p_trans_total',"
				   + "CAST(COUNT(CASE WHEN i.modo_internamento = 1 AND g.id_genero = 1 THEN 0 END) * 100 / count(i.id_internamento) AS DECIMAL(10, 1)) as 'p_trans_t_m',"
				   + "CAST(COUNT(CASE WHEN i.modo_internamento = 1 AND g.id_genero = 2 THEN 0 END) * 100 / count(i.id_internamento) AS DECIMAL(10, 1)) as 'p_trans_t_f'"
				   + "from tblinternamento i, tblgenero g, tblcidadao cc where i.FK_paciente = cc.FK_entidade and cc.FK_genero = g.id_genero "
				   + "and year(i.data_internamento) = ? and month(i.data_internamento) = ? and (day(i.data_internamento) between ? and ?) and year(from_days((to_days(now()) - to_days(cc.data_nascimento)))) between ? and ? and i.id_internamento not in (select FK_internamento from tblaltamedica)";
		
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
			while (rs.next()) {
				Paciente p = new Paciente();
				p.setQuantidade(rs.getInt("pacientes_internados"));
				
				p.setQtd_admitido(rs.getInt("por_admissao"));
				p.setPerc_admitido(rs.getDouble("p_admissao_total"));
				p.setPerc_admitido_msc(rs.getDouble("p_adt_m"));
				p.setPerc_admitido_fmn(rs.getDouble("p_adt_f"));
				
				p.setQdt_transferido(rs.getInt("por_trans"));
				p.setPerc_transferido(rs.getDouble("p_trans_total"));
				p.setPerc_transferido_msc(rs.getDouble("p_trans_t_m"));
				p.setPerc_transferido_fmn(rs.getDouble("p_trans_t_f"));
				p.setDescricao("Internados");
				
				lista.add(p);
				
			}
			ps.close();
			con.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return lista;
	}
	
	public List<Paciente> buscarHistoricoDiagnosticosDiario() {
		String sql = "select cid.descricao_cid, d.cid, count(d.cid) as 'qtd', COUNT(CASE WHEN g.id_genero = 1 THEN 0 END) AS 'masculino', "
				   + "CAST(COUNT(CASE WHEN g.id_genero = 1 THEN 0 END) * 100 / count(d.cid)AS DECIMAL(10, 1)) as 'percet_m',"
				   + "COUNT(CASE WHEN g.id_genero = 2 THEN 0 END) AS 'feminino', CAST(COUNT(CASE WHEN g.id_genero = 2 THEN 0 END) * 100 / count(d.cid)AS DECIMAL(10, 1)) as 'percet_f'"
				   + "from tblconsulta c, tblinternamento i, tblgenero g, tblcidadao cc, tbldiagnostico d, tblcid cid where i.FK_paciente = c.FK_paciente and i.FK_paciente = cc.FK_entidade "
				   + "and c.id_consulta = d.FK_consulta and  d.cid = cid.cid and cc.FK_genero = g.id_genero "
				   + "and  i.id_internamento not in (select FK_internamento from tblaltamedica) group by d.cid";
		
		List<Paciente> lista = new ArrayList<Paciente>();
		try {
			con = Conexao.getConexao();
			PreparedStatement ps = con.prepareStatement(sql);
			ResultSet rs = ps.executeQuery();
			while (rs.next()) {
				Paciente p = new Paciente();
				p.setDescricao(rs.getString("descricao_cid"));
				p.setQuantidade(rs.getInt("qtd"));
				p.setMasculino(rs.getInt("masculino"));
				p.setFeminino(rs.getInt("feminino"));
				p.setPerct_msc(rs.getDouble("percet_m"));
				p.setPerct_fmn(rs.getDouble("percet_f"));
				p.setCid(rs.getString("cid"));
				lista.add(p);
			}
			ps.close();
			con.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return lista;
	}
	
	public List<Paciente> buscarHistoricoDiagnosticosEmAno(int ano) {
		String sql = "select cid.descricao_cid, d.cid, count(d.cid) as 'qtd', COUNT(CASE WHEN g.id_genero = 1 THEN 0 END) AS 'masculino', "
				   + "CAST(COUNT(CASE WHEN g.id_genero = 1 THEN 0 END) * 100 / count(d.cid)AS DECIMAL(10, 1)) as 'percet_m',"
				   + "COUNT(CASE WHEN g.id_genero = 2 THEN 0 END) AS 'feminino', CAST(COUNT(CASE WHEN g.id_genero = 2 THEN 0 END) * 100 / count(d.cid)AS DECIMAL(10, 1)) as 'percet_f'"
				   + "from tblconsulta c, tblinternamento i, tblgenero g, tblcidadao cc, tbldiagnostico d, tblcid cid where i.FK_paciente = c.FK_paciente and i.FK_paciente = cc.FK_entidade "
				   + "and c.id_consulta = d.FK_consulta and  d.cid = cid.cid and cc.FK_genero = g.id_genero "
				   + "and year(i.data_internamento) = ? and  i.id_internamento not in (select FK_internamento from tblaltamedica) group by d.cid";
		
		List<Paciente> lista = new ArrayList<Paciente>();
		try {
			con = Conexao.getConexao();
			PreparedStatement ps = con.prepareStatement(sql);
			ps.setInt(1, ano);
			ResultSet rs = ps.executeQuery();
			while (rs.next()) {
				Paciente p = new Paciente();
				p.setDescricao(rs.getString("descricao_cid"));
				p.setQuantidade(rs.getInt("qtd"));
				p.setMasculino(rs.getInt("masculino"));
				p.setFeminino(rs.getInt("feminino"));
				p.setPerct_msc(rs.getDouble("percet_m"));
				p.setPerct_fmn(rs.getDouble("percet_f"));
				p.setCid(rs.getString("cid"));
				lista.add(p);
			}
			ps.close();
			con.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return lista;
	}
	
	public List<Paciente> buscarHistoricoDiagnosticosEmAnoMes(int ano,int mes) {
		String sql = "select cid.descricao_cid, d.cid, count(d.cid) as 'qtd', COUNT(CASE WHEN g.id_genero = 1 THEN 0 END) AS 'masculino', "
				   + "CAST(COUNT(CASE WHEN g.id_genero = 1 THEN 0 END) * 100 / count(d.cid)AS DECIMAL(10, 1)) as 'percet_m',"
				   + "COUNT(CASE WHEN g.id_genero = 2 THEN 0 END) AS 'feminino', CAST(COUNT(CASE WHEN g.id_genero = 2 THEN 0 END) * 100 / count(d.cid)AS DECIMAL(10, 1)) as 'percet_f'"
				   + "from tblconsulta c, tblinternamento i, tblgenero g, tblcidadao cc, tbldiagnostico d, tblcid cid where i.FK_paciente = c.FK_paciente and i.FK_paciente = cc.FK_entidade "
				   + "and c.id_consulta = d.FK_consulta and  d.cid = cid.cid and cc.FK_genero = g.id_genero "
				   + "and year(i.data_internamento) = ? and month(i.data_internamento) = ? and  i.id_internamento not in (select FK_internamento from tblaltamedica) group by d.cid";
		
		List<Paciente> lista = new ArrayList<Paciente>();
		try {
			con = Conexao.getConexao();
			PreparedStatement ps = con.prepareStatement(sql);
			ps.setInt(1, ano);
			ps.setInt(2, mes);
			ResultSet rs = ps.executeQuery();
			while (rs.next()) {
				Paciente p = new Paciente();
				p.setDescricao(rs.getString("descricao_cid"));
				p.setQuantidade(rs.getInt("qtd"));
				p.setMasculino(rs.getInt("masculino"));
				p.setFeminino(rs.getInt("feminino"));
				p.setPerct_msc(rs.getDouble("percet_m"));
				p.setPerct_fmn(rs.getDouble("percet_f"));
				p.setCid(rs.getString("cid"));
				lista.add(p);
			}
			ps.close();
			con.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return lista;
	}
	
	public List<Paciente> buscarHistoricoDiagnosticosEmAnoMesDias(int ano,int mes,int dia1,int dia2) {
		String sql = "select cid.descricao_cid, d.cid, count(d.cid) as 'qtd', COUNT(CASE WHEN g.id_genero = 1 THEN 0 END) AS 'masculino', "
				   + "CAST(COUNT(CASE WHEN g.id_genero = 1 THEN 0 END) * 100 / count(d.cid)AS DECIMAL(10, 1)) as 'percet_m',"
				   + "COUNT(CASE WHEN g.id_genero = 2 THEN 0 END) AS 'feminino', CAST(COUNT(CASE WHEN g.id_genero = 2 THEN 0 END) * 100 / count(d.cid)AS DECIMAL(10, 1)) as 'percet_f'"
				   + "from tblconsulta c, tblinternamento i, tblgenero g, tblcidadao cc, tbldiagnostico d, tblcid cid where i.FK_paciente = c.FK_paciente and i.FK_paciente = cc.FK_entidade "
				   + "and c.id_consulta = d.FK_consulta and  d.cid = cid.cid and cc.FK_genero = g.id_genero "
				   + "and year(i.data_internamento) = ? and month(i.data_internamento) = ? and (day(i.data_internamento) between ? and ?) and  i.id_internamento not in (select FK_internamento from tblaltamedica) group by d.cid";
		
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
				Paciente p = new Paciente();
				p.setDescricao(rs.getString("descricao_cid"));
				p.setQuantidade(rs.getInt("qtd"));
				p.setMasculino(rs.getInt("masculino"));
				p.setFeminino(rs.getInt("feminino"));
				p.setPerct_msc(rs.getDouble("percet_m"));
				p.setPerct_fmn(rs.getDouble("percet_f"));
				p.setCid(rs.getString("cid"));
				lista.add(p);
			}
			ps.close();
			con.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return lista;
	}
	
	public List<Paciente> buscarHistoricoDiagnosticosEmAnoSeTrimestre(int ano,int aux) {
		String sql = "";
		
		if(aux == 1){
			sql = "select cid.descricao_cid, d.cid, count(d.cid) as 'qtd', COUNT(CASE WHEN g.id_genero = 1 THEN 0 END) AS 'masculino', "
					   + "CAST(COUNT(CASE WHEN g.id_genero = 1 THEN 0 END) * 100 / count(d.cid)AS DECIMAL(10, 1)) as 'percet_m',"
					   + "COUNT(CASE WHEN g.id_genero = 2 THEN 0 END) AS 'feminino', CAST(COUNT(CASE WHEN g.id_genero = 2 THEN 0 END) * 100 / count(d.cid)AS DECIMAL(10, 1)) as 'percet_f'"
					   + "from tblconsulta c, tblinternamento i, tblgenero g, tblcidadao cc, tbldiagnostico d, tblcid cid where i.FK_paciente = c.FK_paciente and i.FK_paciente = cc.FK_entidade "
					   + "and c.id_consulta = d.FK_consulta and  d.cid = cid.cid and cc.FK_genero = g.id_genero "
					   + "and year(i.data_internamento) = ? and month(i.data_internamento) between 1 and 3 and  i.id_internamento not in (select FK_internamento from tblaltamedica) group by d.cid";
		}
		
		if(aux == 2){
			sql = "select cid.descricao_cid, d.cid, count(d.cid) as 'qtd', COUNT(CASE WHEN g.id_genero = 1 THEN 0 END) AS 'masculino', "
					   + "CAST(COUNT(CASE WHEN g.id_genero = 1 THEN 0 END) * 100 / count(d.cid)AS DECIMAL(10, 1)) as 'percet_m',"
					   + "COUNT(CASE WHEN g.id_genero = 2 THEN 0 END) AS 'feminino', CAST(COUNT(CASE WHEN g.id_genero = 2 THEN 0 END) * 100 / count(d.cid)AS DECIMAL(10, 1)) as 'percet_f'"
					   + "from tblconsulta c, tblinternamento i, tblgenero g, tblcidadao cc, tbldiagnostico d, tblcid cid where i.FK_paciente = c.FK_paciente and i.FK_paciente = cc.FK_entidade "
					   + "and c.id_consulta = d.FK_consulta and  d.cid = cid.cid and cc.FK_genero = g.id_genero "
					   + "and year(i.data_internamento) = ? and month(i.data_internamento) between 4 and 6 and  i.id_internamento not in (select FK_internamento from tblaltamedica) group by d.cid";
		}
		
		if(aux == 3){
			sql = "select cid.descricao_cid, d.cid, count(d.cid) as 'qtd', COUNT(CASE WHEN g.id_genero = 1 THEN 0 END) AS 'masculino', "
					   + "CAST(COUNT(CASE WHEN g.id_genero = 1 THEN 0 END) * 100 / count(d.cid)AS DECIMAL(10, 1)) as 'percet_m',"
					   + "COUNT(CASE WHEN g.id_genero = 2 THEN 0 END) AS 'feminino', CAST(COUNT(CASE WHEN g.id_genero = 2 THEN 0 END) * 100 / count(d.cid)AS DECIMAL(10, 1)) as 'percet_f'"
					   + "from tblconsulta c, tblinternamento i, tblgenero g, tblcidadao cc, tbldiagnostico d, tblcid cid where i.FK_paciente = c.FK_paciente and i.FK_paciente = cc.FK_entidade "
					   + "and c.id_consulta = d.FK_consulta and  d.cid = cid.cid and cc.FK_genero = g.id_genero "
					   + "and year(i.data_internamento) = ? and month(i.data_internamento) between 7 and 9 and  i.id_internamento not in (select FK_internamento from tblaltamedica) group by d.cid";
		}
		
		if(aux == 4){
			sql = "select cid.descricao_cid, d.cid, count(d.cid) as 'qtd', COUNT(CASE WHEN g.id_genero = 1 THEN 0 END) AS 'masculino', "
					   + "CAST(COUNT(CASE WHEN g.id_genero = 1 THEN 0 END) * 100 / count(d.cid)AS DECIMAL(10, 1)) as 'percet_m',"
					   + "COUNT(CASE WHEN g.id_genero = 2 THEN 0 END) AS 'feminino', CAST(COUNT(CASE WHEN g.id_genero = 2 THEN 0 END) * 100 / count(d.cid)AS DECIMAL(10, 1)) as 'percet_f'"
					   + "from tblconsulta c, tblinternamento i, tblgenero g, tblcidadao cc, tbldiagnostico d, tblcid cid where i.FK_paciente = c.FK_paciente and i.FK_paciente = cc.FK_entidade "
					   + "and c.id_consulta = d.FK_consulta and  d.cid = cid.cid and cc.FK_genero = g.id_genero "
					   + "and year(i.data_internamento) = ? and month(i.data_internamento) between 10 and 12 and  i.id_internamento not in (select FK_internamento from tblaltamedica) group by d.cid";
		}
		
		if(aux == 5){
			sql = "select cid.descricao_cid, d.cid, count(d.cid) as 'qtd', COUNT(CASE WHEN g.id_genero = 1 THEN 0 END) AS 'masculino', "
					   + "CAST(COUNT(CASE WHEN g.id_genero = 1 THEN 0 END) * 100 / count(d.cid)AS DECIMAL(10, 1)) as 'percet_m',"
					   + "COUNT(CASE WHEN g.id_genero = 2 THEN 0 END) AS 'feminino', CAST(COUNT(CASE WHEN g.id_genero = 2 THEN 0 END) * 100 / count(d.cid)AS DECIMAL(10, 1)) as 'percet_f'"
					   + "from tblconsulta c, tblinternamento i, tblgenero g, tblcidadao cc, tbldiagnostico d, tblcid cid where i.FK_paciente = c.FK_paciente and i.FK_paciente = cc.FK_entidade "
					   + "and c.id_consulta = d.FK_consulta and  d.cid = cid.cid and cc.FK_genero = g.id_genero "
					   + "and year(i.data_internamento) = ? and month(i.data_internamento) between 1 and 6 and  i.id_internamento not in (select FK_internamento from tblaltamedica) group by d.cid";
		}
		
		if(aux == 6){
			sql = "select cid.descricao_cid, d.cid, count(d.cid) as 'qtd', COUNT(CASE WHEN g.id_genero = 1 THEN 0 END) AS 'masculino', "
					   + "CAST(COUNT(CASE WHEN g.id_genero = 1 THEN 0 END) * 100 / count(d.cid)AS DECIMAL(10, 1)) as 'percet_m',"
					   + "COUNT(CASE WHEN g.id_genero = 2 THEN 0 END) AS 'feminino', CAST(COUNT(CASE WHEN g.id_genero = 2 THEN 0 END) * 100 / count(d.cid)AS DECIMAL(10, 1)) as 'percet_f'"
					   + "from tblconsulta c, tblinternamento i, tblgenero g, tblcidadao cc, tbldiagnostico d, tblcid cid where i.FK_paciente = c.FK_paciente and i.FK_paciente = cc.FK_entidade "
					   + "and c.id_consulta = d.FK_consulta and  d.cid = cid.cid and cc.FK_genero = g.id_genero "
					   + "and year(i.data_internamento) = ? and month(i.data_internamento) between 7 and 12 and  i.id_internamento not in (select FK_internamento from tblaltamedica) group by d.cid";
		}
		
		List<Paciente> lista = new ArrayList<Paciente>();
		try {
			con = Conexao.getConexao();
			PreparedStatement ps = con.prepareStatement(sql);
			ps.setInt(1, ano);
			 
			ResultSet rs = ps.executeQuery();
			while (rs.next()) {
				Paciente p = new Paciente();
				p.setDescricao(rs.getString("descricao_cid"));
				p.setQuantidade(rs.getInt("qtd"));
				p.setMasculino(rs.getInt("masculino"));
				p.setFeminino(rs.getInt("feminino"));
				p.setPerct_msc(rs.getDouble("percet_m"));
				p.setPerct_fmn(rs.getDouble("percet_f"));
				p.setCid(rs.getString("cid"));
				lista.add(p);
			}
			ps.close();
			con.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return lista;
	}
	
	public List<Paciente> buscarHistoricoDiagnosticosEmAnoSeTrimestreIdades(int ano,int aux,int idade1,int idade2) {
		String sql = "";
		
		if(aux == 1){
			sql = "select cid.descricao_cid, d.cid, count(d.cid) as 'qtd', COUNT(CASE WHEN g.id_genero = 1 THEN 0 END) AS 'masculino', "
					   + "CAST(COUNT(CASE WHEN g.id_genero = 1 THEN 0 END) * 100 / count(d.cid)AS DECIMAL(10, 1)) as 'percet_m',"
					   + "COUNT(CASE WHEN g.id_genero = 2 THEN 0 END) AS 'feminino', CAST(COUNT(CASE WHEN g.id_genero = 2 THEN 0 END) * 100 / count(d.cid)AS DECIMAL(10, 1)) as 'percet_f'"
					   + "from tblconsulta c, tblinternamento i, tblgenero g, tblcidadao cc, tbldiagnostico d, tblcid cid where i.FK_paciente = c.FK_paciente and i.FK_paciente = cc.FK_entidade "
					   + "and c.id_consulta = d.FK_consulta and  d.cid = cid.cid and cc.FK_genero = g.id_genero "
					   + "and year(i.data_internamento) = ? and month(i.data_internamento) between 1 and 3 and year(from_days((to_days(now()) - to_days(cc.data_nascimento)))) between ? and ? and  i.id_internamento not in (select FK_internamento from tblaltamedica) group by d.cid";
		}
		
		if(aux == 2){
			sql = "select cid.descricao_cid, d.cid, count(d.cid) as 'qtd', COUNT(CASE WHEN g.id_genero = 1 THEN 0 END) AS 'masculino', "
					   + "CAST(COUNT(CASE WHEN g.id_genero = 1 THEN 0 END) * 100 / count(d.cid)AS DECIMAL(10, 1)) as 'percet_m',"
					   + "COUNT(CASE WHEN g.id_genero = 2 THEN 0 END) AS 'feminino', CAST(COUNT(CASE WHEN g.id_genero = 2 THEN 0 END) * 100 / count(d.cid)AS DECIMAL(10, 1)) as 'percet_f'"
					   + "from tblconsulta c, tblinternamento i, tblgenero g, tblcidadao cc, tbldiagnostico d, tblcid cid where i.FK_paciente = c.FK_paciente and i.FK_paciente = cc.FK_entidade "
					   + "and c.id_consulta = d.FK_consulta and  d.cid = cid.cid and cc.FK_genero = g.id_genero "
					   + "and year(i.data_internamento) = ? and month(i.data_internamento) between 4 and 6 and year(from_days((to_days(now()) - to_days(cc.data_nascimento)))) between ? and ? and  i.id_internamento not in (select FK_internamento from tblaltamedica) group by d.cid";
		}
		
		if(aux == 3){
			sql = "select cid.descricao_cid, d.cid, count(d.cid) as 'qtd', COUNT(CASE WHEN g.id_genero = 1 THEN 0 END) AS 'masculino', "
					   + "CAST(COUNT(CASE WHEN g.id_genero = 1 THEN 0 END) * 100 / count(d.cid)AS DECIMAL(10, 1)) as 'percet_m',"
					   + "COUNT(CASE WHEN g.id_genero = 2 THEN 0 END) AS 'feminino', CAST(COUNT(CASE WHEN g.id_genero = 2 THEN 0 END) * 100 / count(d.cid)AS DECIMAL(10, 1)) as 'percet_f'"
					   + "from tblconsulta c, tblinternamento i, tblgenero g, tblcidadao cc, tbldiagnostico d, tblcid cid where i.FK_paciente = c.FK_paciente and i.FK_paciente = cc.FK_entidade "
					   + "and c.id_consulta = d.FK_consulta and  d.cid = cid.cid and cc.FK_genero = g.id_genero "
					   + "and year(i.data_internamento) = ? and month(i.data_internamento) between 7 and 9 and year(from_days((to_days(now()) - to_days(cc.data_nascimento)))) between ? and ? and  i.id_internamento not in (select FK_internamento from tblaltamedica) group by d.cid";
		}
		
		if(aux == 4){
			sql = "select cid.descricao_cid, d.cid, count(d.cid) as 'qtd', COUNT(CASE WHEN g.id_genero = 1 THEN 0 END) AS 'masculino', "
					   + "CAST(COUNT(CASE WHEN g.id_genero = 1 THEN 0 END) * 100 / count(d.cid)AS DECIMAL(10, 1)) as 'percet_m',"
					   + "COUNT(CASE WHEN g.id_genero = 2 THEN 0 END) AS 'feminino', CAST(COUNT(CASE WHEN g.id_genero = 2 THEN 0 END) * 100 / count(d.cid)AS DECIMAL(10, 1)) as 'percet_f'"
					   + "from tblconsulta c, tblinternamento i, tblgenero g, tblcidadao cc, tbldiagnostico d, tblcid cid where i.FK_paciente = c.FK_paciente and i.FK_paciente = cc.FK_entidade "
					   + "and c.id_consulta = d.FK_consulta and  d.cid = cid.cid and cc.FK_genero = g.id_genero "
					   + "and year(i.data_internamento) = ? and month(i.data_internamento) between 10 and 12 and year(from_days((to_days(now()) - to_days(cc.data_nascimento)))) between ? and ? and  i.id_internamento not in (select FK_internamento from tblaltamedica) group by d.cid";
		}
		
		if(aux == 5){
			sql = "select cid.descricao_cid, d.cid, count(d.cid) as 'qtd', COUNT(CASE WHEN g.id_genero = 1 THEN 0 END) AS 'masculino', "
					   + "CAST(COUNT(CASE WHEN g.id_genero = 1 THEN 0 END) * 100 / count(d.cid)AS DECIMAL(10, 1)) as 'percet_m',"
					   + "COUNT(CASE WHEN g.id_genero = 2 THEN 0 END) AS 'feminino', CAST(COUNT(CASE WHEN g.id_genero = 2 THEN 0 END) * 100 / count(d.cid)AS DECIMAL(10, 1)) as 'percet_f'"
					   + "from tblconsulta c, tblinternamento i, tblgenero g, tblcidadao cc, tbldiagnostico d, tblcid cid where i.FK_paciente = c.FK_paciente and i.FK_paciente = cc.FK_entidade "
					   + "and c.id_consulta = d.FK_consulta and  d.cid = cid.cid and cc.FK_genero = g.id_genero "
					   + "and year(i.data_internamento) = ? and month(i.data_internamento) between 1 and 6 and year(from_days((to_days(now()) - to_days(cc.data_nascimento)))) between ? and ? and  i.id_internamento not in (select FK_internamento from tblaltamedica) group by d.cid";
		}
		
		if(aux == 6){
			sql = "select cid.descricao_cid, d.cid, count(d.cid) as 'qtd', COUNT(CASE WHEN g.id_genero = 1 THEN 0 END) AS 'masculino', "
					   + "CAST(COUNT(CASE WHEN g.id_genero = 1 THEN 0 END) * 100 / count(d.cid)AS DECIMAL(10, 1)) as 'percet_m',"
					   + "COUNT(CASE WHEN g.id_genero = 2 THEN 0 END) AS 'feminino', CAST(COUNT(CASE WHEN g.id_genero = 2 THEN 0 END) * 100 / count(d.cid)AS DECIMAL(10, 1)) as 'percet_f'"
					   + "from tblconsulta c, tblinternamento i, tblgenero g, tblcidadao cc, tbldiagnostico d, tblcid cid where i.FK_paciente = c.FK_paciente and i.FK_paciente = cc.FK_entidade "
					   + "and c.id_consulta = d.FK_consulta and  d.cid = cid.cid and cc.FK_genero = g.id_genero "
					   + "and year(i.data_internamento) = ? and month(i.data_internamento) between 7 and 12 and year(from_days((to_days(now()) - to_days(cc.data_nascimento)))) between ? and ? and  i.id_internamento not in (select FK_internamento from tblaltamedica) group by d.cid";
		}
		
		List<Paciente> lista = new ArrayList<Paciente>();
		try {
			con = Conexao.getConexao();
			PreparedStatement ps = con.prepareStatement(sql);
			ps.setInt(1, ano);
			ps.setInt(2, idade1);
			ps.setInt(3, idade2);
			ResultSet rs = ps.executeQuery();
			while (rs.next()) {
				Paciente p = new Paciente();
				p.setDescricao(rs.getString("descricao_cid"));
				p.setQuantidade(rs.getInt("qtd"));
				p.setMasculino(rs.getInt("masculino"));
				p.setFeminino(rs.getInt("feminino"));
				p.setPerct_msc(rs.getDouble("percet_m"));
				p.setPerct_fmn(rs.getDouble("percet_f"));
				p.setCid(rs.getString("cid"));
				lista.add(p);
			}
			ps.close();
			con.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return lista;
	}
	
	public List<Paciente> buscarHistoricoDiagnosticosEmIdades(int idade1,int idade2) {
		String sql = "select cid.descricao_cid, d.cid, count(d.cid) as 'qtd', COUNT(CASE WHEN g.id_genero = 1 THEN 0 END) AS 'masculino', "
				   + "CAST(COUNT(CASE WHEN g.id_genero = 1 THEN 0 END) * 100 / count(d.cid)AS DECIMAL(10, 1)) as 'percet_m',"
				   + "COUNT(CASE WHEN g.id_genero = 2 THEN 0 END) AS 'feminino', CAST(COUNT(CASE WHEN g.id_genero = 2 THEN 0 END) * 100 / count(d.cid)AS DECIMAL(10, 1)) as 'percet_f'"
				   + "from tblconsulta c, tblinternamento i, tblgenero g, tblcidadao cc, tbldiagnostico d, tblcid cid where i.FK_paciente = c.FK_paciente and i.FK_paciente = cc.FK_entidade "
				   + "and c.id_consulta = d.FK_consulta and  d.cid = cid.cid and cc.FK_genero = g.id_genero "
				   + "and year(from_days((to_days(now()) - to_days(cc.data_nascimento)))) between ? and ? and  i.id_internamento not in (select FK_internamento from tblaltamedica) group by d.cid";
		
		List<Paciente> lista = new ArrayList<Paciente>();
		try {
			con = Conexao.getConexao();
			PreparedStatement ps = con.prepareStatement(sql);
			ps.setInt(1, idade1);
			ps.setInt(2, idade2);
			ResultSet rs = ps.executeQuery();
			while (rs.next()) {
				Paciente p = new Paciente();
				p.setDescricao(rs.getString("descricao_cid"));
				p.setQuantidade(rs.getInt("qtd"));
				p.setMasculino(rs.getInt("masculino"));
				p.setFeminino(rs.getInt("feminino"));
				p.setPerct_msc(rs.getDouble("percet_m"));
				p.setPerct_fmn(rs.getDouble("percet_f"));
				p.setCid(rs.getString("cid"));
				lista.add(p);
			}
			ps.close();
			con.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return lista;
	}
	
	
	
	public List<Paciente> buscarHistoricoDiagnosticosEmAnoIdades(int ano,int idade1,int idade2) {
		String sql = "select cid.descricao_cid, d.cid, count(d.cid) as 'qtd', COUNT(CASE WHEN g.id_genero = 1 THEN 0 END) AS 'masculino', "
				   + "CAST(COUNT(CASE WHEN g.id_genero = 1 THEN 0 END) * 100 / count(d.cid)AS DECIMAL(10, 1)) as 'percet_m',"
				   + "COUNT(CASE WHEN g.id_genero = 2 THEN 0 END) AS 'feminino', CAST(COUNT(CASE WHEN g.id_genero = 2 THEN 0 END) * 100 / count(d.cid)AS DECIMAL(10, 1)) as 'percet_f'"
				   + "from tblconsulta c, tblinternamento i, tblgenero g, tblcidadao cc, tbldiagnostico d, tblcid cid where i.FK_paciente = c.FK_paciente and i.FK_paciente = cc.FK_entidade "
				   + "and c.id_consulta = d.FK_consulta and  d.cid = cid.cid and cc.FK_genero = g.id_genero "
				   + "and year(i.data_internamento) = ? and year(from_days((to_days(now()) - to_days(cc.data_nascimento)))) between ? and ? and  i.id_internamento not in (select FK_internamento from tblaltamedica) group by d.cid";
		
		List<Paciente> lista = new ArrayList<Paciente>();
		try {
			con = Conexao.getConexao();
			PreparedStatement ps = con.prepareStatement(sql);
			ps.setInt(1, ano);
			ps.setInt(2, idade1);
			ps.setInt(3, idade2);
			ResultSet rs = ps.executeQuery();
			while (rs.next()) {
				Paciente p = new Paciente();
				p.setDescricao(rs.getString("descricao_cid"));
				p.setQuantidade(rs.getInt("qtd"));
				p.setMasculino(rs.getInt("masculino"));
				p.setFeminino(rs.getInt("feminino"));
				p.setPerct_msc(rs.getDouble("percet_m"));
				p.setPerct_fmn(rs.getDouble("percet_f"));
				p.setCid(rs.getString("cid"));
				lista.add(p);
			}
			ps.close();
			con.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return lista;
	}
	
	public List<Paciente> buscarHistoricoDiagnosticosEmAnoMesIdades(int ano,int mes,int idade1,int idade2) {
		String sql = "select cid.descricao_cid, d.cid, count(d.cid) as 'qtd', COUNT(CASE WHEN g.id_genero = 1 THEN 0 END) AS 'masculino', "
				   + "CAST(COUNT(CASE WHEN g.id_genero = 1 THEN 0 END) * 100 / count(d.cid)AS DECIMAL(10, 1)) as 'percet_m',"
				   + "COUNT(CASE WHEN g.id_genero = 2 THEN 0 END) AS 'feminino', CAST(COUNT(CASE WHEN g.id_genero = 2 THEN 0 END) * 100 / count(d.cid)AS DECIMAL(10, 1)) as 'percet_f'"
				   + "from tblconsulta c, tblinternamento i, tblgenero g, tblcidadao cc, tbldiagnostico d, tblcid cid where i.FK_paciente = c.FK_paciente and i.FK_paciente = cc.FK_entidade "
				   + "and c.id_consulta = d.FK_consulta and  d.cid = cid.cid and cc.FK_genero = g.id_genero "
				   + "and year(i.data_internamento) = ? and month(i.data_internamento) = ? and year(from_days((to_days(now()) - to_days(cc.data_nascimento)))) between ? and ? and  i.id_internamento not in (select FK_internamento from tblaltamedica) group by d.cid";
		
		List<Paciente> lista = new ArrayList<Paciente>();
		try {
			con = Conexao.getConexao();
			PreparedStatement ps = con.prepareStatement(sql);
			ps.setInt(1, ano);
			ps.setInt(2, mes);
			ps.setInt(3, idade1);
			ps.setInt(4, idade2);
			ResultSet rs = ps.executeQuery();
			while (rs.next()) {
				Paciente p = new Paciente();
				p.setDescricao(rs.getString("descricao_cid"));
				p.setQuantidade(rs.getInt("qtd"));
				p.setMasculino(rs.getInt("masculino"));
				p.setFeminino(rs.getInt("feminino"));
				p.setPerct_msc(rs.getDouble("percet_m"));
				p.setPerct_fmn(rs.getDouble("percet_f"));
				p.setCid(rs.getString("cid"));
				lista.add(p);
			}
			ps.close();
			con.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return lista;
	}
	
	public List<Paciente> buscarHistoricoDiagnosticosEmAnoMesDiasIdades(int ano,int mes,int dia1,int dia2,int idade1,int idade2) {
		String sql = "select cid.descricao_cid, d.cid, count(d.cid) as 'qtd', COUNT(CASE WHEN g.id_genero = 1 THEN 0 END) AS 'masculino', "
				   + "CAST(COUNT(CASE WHEN g.id_genero = 1 THEN 0 END) * 100 / count(d.cid)AS DECIMAL(10, 1)) as 'percet_m',"
				   + "COUNT(CASE WHEN g.id_genero = 2 THEN 0 END) AS 'feminino', CAST(COUNT(CASE WHEN g.id_genero = 2 THEN 0 END) * 100 / count(d.cid)AS DECIMAL(10, 1)) as 'percet_f'"
				   + "from tblconsulta c, tblinternamento i, tblgenero g, tblcidadao cc, tbldiagnostico d, tblcid cid where i.FK_paciente = c.FK_paciente and i.FK_paciente = cc.FK_entidade "
				   + "and c.id_consulta = d.FK_consulta and  d.cid = cid.cid and cc.FK_genero = g.id_genero "
				   + "and year(i.data_internamento) = ? and month(i.data_internamento) = ? and (day(i.data_internamento) between ? and ? ) and year(from_days((to_days(now()) - to_days(cc.data_nascimento)))) between ? and ? and  i.id_internamento not in (select FK_internamento from tblaltamedica) group by d.cid";
		
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
			while (rs.next()) {
				Paciente p = new Paciente();
				p.setDescricao(rs.getString("descricao_cid"));
				p.setQuantidade(rs.getInt("qtd"));
				p.setMasculino(rs.getInt("masculino"));
				p.setFeminino(rs.getInt("feminino"));
				p.setPerct_msc(rs.getDouble("percet_m"));
				p.setPerct_fmn(rs.getDouble("percet_f"));
				p.setCid(rs.getString("cid"));
				lista.add(p);
			}
			ps.close();
			con.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return lista;
	}
	public List<Diverso> buscarDoencasDiagnosticos() {
		String sql = "select cid.descricao_cid, d.cid from tblconsulta c, tblinternamento i, tblgenero g, tblcidadao cc, tbldiagnostico d, tblcid cid where i.FK_paciente = c.FK_paciente and i.FK_paciente = cc.FK_entidade "
				   + "and c.id_consulta = d.FK_consulta and  d.cid = cid.cid and cc.FK_genero = g.id_genero "
				   + "and  i.id_internamento not in (select FK_internamento from tblaltamedica) group by d.cid";
		
		List<Diverso> lista = new ArrayList<Diverso>();
		try {
			con = Conexao.getConexao();
			PreparedStatement ps = con.prepareStatement(sql);
			ResultSet rs = ps.executeQuery();
			while (rs.next()) {
				Diverso d = new Diverso();
				d.setCod_cid(rs.getString("cid"));
				d.setDescricao(rs.getString("descricao_cid"));
				lista.add(d);
			}
			ps.close();
			con.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return lista;
	}
	
	public List<AltaMedicaBean> buscarHistoricoAltaMedicaDiario() {
		String sql = "select count(a.id_alta) as 'pacientes_com_alta', COUNT(CASE WHEN a.motivo_alta = 'transferencia' THEN 0 END) AS 'por_transferencia',"
				   + "CAST(COUNT(CASE WHEN a.motivo_alta = 'transferencia' THEN 0 END) * 100 / count(a.id_alta) AS DECIMAL(10, 1)) as 'p_trasf_total',"
				   + "CAST(COUNT(CASE WHEN a.motivo_alta = 'transferencia' AND g.id_genero = 1 THEN 0 END) * 100 / count(a.id_alta) AS DECIMAL(10, 1)) as 'p_trans_m',"
				   + "CAST(COUNT(CASE WHEN a.motivo_alta = 'transferencia' AND g.id_genero = 2 THEN 0 END) * 100 / count(a.id_alta) AS DECIMAL(10, 1)) as 'p_trans_f',"
				   + "COUNT(CASE WHEN a.motivo_alta = 'morte' THEN 0 END) AS 'por_morte', CAST(COUNT(CASE WHEN a.motivo_alta = 'morte' THEN 0 END) * 100 / count(a.id_alta) AS DECIMAL(10, 1)) as 'p_morte_total',"
				   + "CAST(COUNT(CASE WHEN a.motivo_alta = 'morte' AND g.id_genero = 1 THEN 0 END) * 100 / count(a.id_alta) AS DECIMAL(10, 1)) as 'p_morte_m',"
				   + "CAST(COUNT(CASE WHEN a.motivo_alta = 'morte' AND g.id_genero = 2 THEN 0 END) * 100 / count(a.id_alta) AS DECIMAL(10, 1)) as 'p_morte_f',"
				   + "COUNT(CASE WHEN a.motivo_alta = 'melhoria' THEN 0 END) AS 'por_melhoria', CAST(COUNT(CASE WHEN a.motivo_alta = 'melhoria' THEN 0 END) * 100 / count(a.id_alta) AS DECIMAL(10, 1)) as 'p_melhoria_total',"
				   + "CAST(COUNT(CASE WHEN a.motivo_alta = 'melhoria' AND g.id_genero = 1 THEN 0 END) * 100 / count(a.id_alta) AS DECIMAL(10, 1)) as 'p_melhoria_m',"
				   + "CAST(COUNT(CASE WHEN a.motivo_alta = 'melhoria' AND g.id_genero = 2 THEN 0 END) * 100 / count(a.id_alta) AS DECIMAL(10, 1)) as 'p_melhoria_f'"
				   + "from tblaltamedica a, tblinternamento i, tblgenero g, tblcidadao cc where  i.id_internamento = a.FK_internamento and  i.FK_paciente = cc.FK_entidade and cc.FK_genero = g.id_genero ";
		
		List<AltaMedicaBean> lista = new ArrayList<AltaMedicaBean>();
		try {
			con = Conexao.getConexao();
			PreparedStatement ps = con.prepareStatement(sql);
			ResultSet rs = ps.executeQuery();
			while (rs.next()) {
				AltaMedicaBean p = new AltaMedicaBean();
				p.setQtd_paciente_alta(rs.getInt("pacientes_com_alta"));
				p.setQtd_alta_transf(rs.getInt("por_transferencia"));
				p.setPerc_alta_transf(rs.getDouble("p_trasf_total"));
				p.setPerc_alta_transf_msc(rs.getDouble("p_trans_m"));
				p.setPerc_alta_transf_fmn(rs.getDouble("p_trans_f"));
				
				p.setQtd_alta_morte(rs.getInt("por_morte"));
				p.setPerc_alta_morte(rs.getDouble("p_morte_total"));
				p.setPerc_alta_morte_msc(rs.getDouble("p_morte_m"));
				p.setPerc_alta_morte_fmn(rs.getDouble("p_morte_f"));
				
				p.setQtd_alta_melhoria(rs.getInt("por_melhoria"));
				p.setPerc_alta_melhoria(rs.getDouble("p_melhoria_total"));
				p.setPerc_alta_melhoria_msc(rs.getDouble("p_melhoria_m"));
				p.setPerc_alta_melhoria_fmn(rs.getDouble("p_melhoria_f"));
				
				lista.add(p);
				
			}
			ps.close();
			con.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return lista;
	}

	public List<AltaMedicaBean> buscarHistoricoAltaMedicaEmAno(int ano) {
		String sql = "select count(a.id_alta) as 'pacientes_com_alta', COUNT(CASE WHEN a.motivo_alta = 'transferencia' THEN 0 END) AS 'por_transferencia',"
				   + "CAST(COUNT(CASE WHEN a.motivo_alta = 'transferencia' THEN 0 END) * 100 / count(a.id_alta) AS DECIMAL(10, 1)) as 'p_trasf_total',"
				   + "CAST(COUNT(CASE WHEN a.motivo_alta = 'transferencia' AND g.id_genero = 1 THEN 0 END) * 100 / count(a.id_alta) AS DECIMAL(10, 1)) as 'p_trans_m',"
				   + "CAST(COUNT(CASE WHEN a.motivo_alta = 'transferencia' AND g.id_genero = 2 THEN 0 END) * 100 / count(a.id_alta) AS DECIMAL(10, 1)) as 'p_trans_f',"
				   + "COUNT(CASE WHEN a.motivo_alta = 'morte' THEN 0 END) AS 'por_morte', CAST(COUNT(CASE WHEN a.motivo_alta = 'morte' THEN 0 END) * 100 / count(a.id_alta) AS DECIMAL(10, 1)) as 'p_morte_total',"
				   + "CAST(COUNT(CASE WHEN a.motivo_alta = 'morte' AND g.id_genero = 1 THEN 0 END) * 100 / count(a.id_alta) AS DECIMAL(10, 1)) as 'p_morte_m',"
				   + "CAST(COUNT(CASE WHEN a.motivo_alta = 'morte' AND g.id_genero = 2 THEN 0 END) * 100 / count(a.id_alta) AS DECIMAL(10, 1)) as 'p_morte_f',"
				   + "COUNT(CASE WHEN a.motivo_alta = 'melhoria' THEN 0 END) AS 'por_melhoria', CAST(COUNT(CASE WHEN a.motivo_alta = 'melhoria' THEN 0 END) * 100 / count(a.id_alta) AS DECIMAL(10, 1)) as 'p_melhoria_total',"
				   + "CAST(COUNT(CASE WHEN a.motivo_alta = 'melhoria' AND g.id_genero = 1 THEN 0 END) * 100 / count(a.id_alta) AS DECIMAL(10, 1)) as 'p_melhoria_m',"
				   + "CAST(COUNT(CASE WHEN a.motivo_alta = 'melhoria' AND g.id_genero = 2 THEN 0 END) * 100 / count(a.id_alta) AS DECIMAL(10, 1)) as 'p_melhoria_f'"
				   + "from tblaltamedica a, tblinternamento i, tblgenero g, tblcidadao cc where  i.id_internamento = a.FK_internamento and  i.FK_paciente = cc.FK_entidade and cc.FK_genero = g.id_genero "
				   + "and year(a.data_registo) = ?";
		
		List<AltaMedicaBean> lista = new ArrayList<AltaMedicaBean>();
		try {
			con = Conexao.getConexao();
			PreparedStatement ps = con.prepareStatement(sql);
			ps.setInt(1, ano);
			ResultSet rs = ps.executeQuery();
			while (rs.next()) {
				AltaMedicaBean p = new AltaMedicaBean();
				p.setQtd_paciente_alta(rs.getInt("pacientes_com_alta"));
				p.setQtd_alta_transf(rs.getInt("por_transferencia"));
				p.setPerc_alta_transf(rs.getDouble("p_trasf_total"));
				p.setPerc_alta_transf_msc(rs.getDouble("p_trans_m"));
				p.setPerc_alta_transf_fmn(rs.getDouble("p_trans_f"));
				
				p.setQtd_alta_morte(rs.getInt("por_morte"));
				p.setPerc_alta_morte(rs.getDouble("p_morte_total"));
				p.setPerc_alta_morte_msc(rs.getDouble("p_morte_m"));
				p.setPerc_alta_morte_fmn(rs.getDouble("p_morte_f"));
				
				p.setQtd_alta_melhoria(rs.getInt("por_melhoria"));
				p.setPerc_alta_melhoria(rs.getDouble("p_melhoria_total"));
				p.setPerc_alta_melhoria_msc(rs.getDouble("p_melhoria_m"));
				p.setPerc_alta_melhoria_fmn(rs.getDouble("p_melhoria_f"));
				
				lista.add(p);
				
			}
			ps.close();
			con.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return lista;
	}
	
	public List<AltaMedicaBean> buscarHistoricoAltaMedicaEmAnoMes(int ano,int mes) {
		String sql = "select count(a.id_alta) as 'pacientes_com_alta', COUNT(CASE WHEN a.motivo_alta = 'transferencia' THEN 0 END) AS 'por_transferencia',"
				   + "CAST(COUNT(CASE WHEN a.motivo_alta = 'transferencia' THEN 0 END) * 100 / count(a.id_alta) AS DECIMAL(10, 1)) as 'p_trasf_total',"
				   + "CAST(COUNT(CASE WHEN a.motivo_alta = 'transferencia' AND g.id_genero = 1 THEN 0 END) * 100 / count(a.id_alta) AS DECIMAL(10, 1)) as 'p_trans_m',"
				   + "CAST(COUNT(CASE WHEN a.motivo_alta = 'transferencia' AND g.id_genero = 2 THEN 0 END) * 100 / count(a.id_alta) AS DECIMAL(10, 1)) as 'p_trans_f',"
				   + "COUNT(CASE WHEN a.motivo_alta = 'morte' THEN 0 END) AS 'por_morte', CAST(COUNT(CASE WHEN a.motivo_alta = 'morte' THEN 0 END) * 100 / count(a.id_alta) AS DECIMAL(10, 1)) as 'p_morte_total',"
				   + "CAST(COUNT(CASE WHEN a.motivo_alta = 'morte' AND g.id_genero = 1 THEN 0 END) * 100 / count(a.id_alta) AS DECIMAL(10, 1)) as 'p_morte_m',"
				   + "CAST(COUNT(CASE WHEN a.motivo_alta = 'morte' AND g.id_genero = 2 THEN 0 END) * 100 / count(a.id_alta) AS DECIMAL(10, 1)) as 'p_morte_f',"
				   + "COUNT(CASE WHEN a.motivo_alta = 'melhoria' THEN 0 END) AS 'por_melhoria', CAST(COUNT(CASE WHEN a.motivo_alta = 'melhoria' THEN 0 END) * 100 / count(a.id_alta) AS DECIMAL(10, 1)) as 'p_melhoria_total',"
				   + "CAST(COUNT(CASE WHEN a.motivo_alta = 'melhoria' AND g.id_genero = 1 THEN 0 END) * 100 / count(a.id_alta) AS DECIMAL(10, 1)) as 'p_melhoria_m',"
				   + "CAST(COUNT(CASE WHEN a.motivo_alta = 'melhoria' AND g.id_genero = 2 THEN 0 END) * 100 / count(a.id_alta) AS DECIMAL(10, 1)) as 'p_melhoria_f'"
				   + "from tblaltamedica a, tblinternamento i, tblgenero g, tblcidadao cc where  i.id_internamento = a.FK_internamento and  i.FK_paciente = cc.FK_entidade and cc.FK_genero = g.id_genero "
				   + "and year(a.data_registo) = ? and month(a.data_registo) = ?";
		
		List<AltaMedicaBean> lista = new ArrayList<AltaMedicaBean>();
		try {
			con = Conexao.getConexao();
			PreparedStatement ps = con.prepareStatement(sql);
			ps.setInt(1, ano);
			ps.setInt(2, mes);
			ResultSet rs = ps.executeQuery();
			while (rs.next()) {
				AltaMedicaBean p = new AltaMedicaBean();
				p.setQtd_paciente_alta(rs.getInt("pacientes_com_alta"));
				p.setQtd_alta_transf(rs.getInt("por_transferencia"));
				p.setPerc_alta_transf(rs.getDouble("p_trasf_total"));
				p.setPerc_alta_transf_msc(rs.getDouble("p_trans_m"));
				p.setPerc_alta_transf_fmn(rs.getDouble("p_trans_f"));
				
				p.setQtd_alta_morte(rs.getInt("por_morte"));
				p.setPerc_alta_morte(rs.getDouble("p_morte_total"));
				p.setPerc_alta_morte_msc(rs.getDouble("p_morte_m"));
				p.setPerc_alta_morte_fmn(rs.getDouble("p_morte_f"));
				
				p.setQtd_alta_melhoria(rs.getInt("por_melhoria"));
				p.setPerc_alta_melhoria(rs.getDouble("p_melhoria_total"));
				p.setPerc_alta_melhoria_msc(rs.getDouble("p_melhoria_m"));
				p.setPerc_alta_melhoria_fmn(rs.getDouble("p_melhoria_f"));
				
				lista.add(p);
				
			}
			ps.close();
			con.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return lista;
	}

	public List<AltaMedicaBean> buscarHistoricoAltaMedicaEmIdades(int idade1,int idade2) {
		String sql = "select count(a.id_alta) as 'pacientes_com_alta', COUNT(CASE WHEN a.motivo_alta = 'transferencia' THEN 0 END) AS 'por_transferencia',"
				   + "CAST(COUNT(CASE WHEN a.motivo_alta = 'transferencia' THEN 0 END) * 100 / count(a.id_alta) AS DECIMAL(10, 1)) as 'p_trasf_total',"
				   + "CAST(COUNT(CASE WHEN a.motivo_alta = 'transferencia' AND g.id_genero = 1 THEN 0 END) * 100 / count(a.id_alta) AS DECIMAL(10, 1)) as 'p_trans_m',"
				   + "CAST(COUNT(CASE WHEN a.motivo_alta = 'transferencia' AND g.id_genero = 2 THEN 0 END) * 100 / count(a.id_alta) AS DECIMAL(10, 1)) as 'p_trans_f',"
				   + "COUNT(CASE WHEN a.motivo_alta = 'morte' THEN 0 END) AS 'por_morte', CAST(COUNT(CASE WHEN a.motivo_alta = 'morte' THEN 0 END) * 100 / count(a.id_alta) AS DECIMAL(10, 1)) as 'p_morte_total',"
				   + "CAST(COUNT(CASE WHEN a.motivo_alta = 'morte' AND g.id_genero = 1 THEN 0 END) * 100 / count(a.id_alta) AS DECIMAL(10, 1)) as 'p_morte_m',"
				   + "CAST(COUNT(CASE WHEN a.motivo_alta = 'morte' AND g.id_genero = 2 THEN 0 END) * 100 / count(a.id_alta) AS DECIMAL(10, 1)) as 'p_morte_f',"
				   + "COUNT(CASE WHEN a.motivo_alta = 'melhoria' THEN 0 END) AS 'por_melhoria', CAST(COUNT(CASE WHEN a.motivo_alta = 'melhoria' THEN 0 END) * 100 / count(a.id_alta) AS DECIMAL(10, 1)) as 'p_melhoria_total',"
				   + "CAST(COUNT(CASE WHEN a.motivo_alta = 'melhoria' AND g.id_genero = 1 THEN 0 END) * 100 / count(a.id_alta) AS DECIMAL(10, 1)) as 'p_melhoria_m',"
				   + "CAST(COUNT(CASE WHEN a.motivo_alta = 'melhoria' AND g.id_genero = 2 THEN 0 END) * 100 / count(a.id_alta) AS DECIMAL(10, 1)) as 'p_melhoria_f'"
				   + "from tblaltamedica a, tblinternamento i, tblgenero g, tblcidadao cc where  i.id_internamento = a.FK_internamento and  i.FK_paciente = cc.FK_entidade and cc.FK_genero = g.id_genero "
				   + "and year(from_days((to_days(now()) - to_days(cc.data_nascimento)))) between ? and ?";
		
		List<AltaMedicaBean> lista = new ArrayList<AltaMedicaBean>();
		try {
			con = Conexao.getConexao();
			PreparedStatement ps = con.prepareStatement(sql);
			ps.setInt(1, idade1);
			ps.setInt(2, idade2);
			ResultSet rs = ps.executeQuery();
			while (rs.next()) {
				AltaMedicaBean p = new AltaMedicaBean();
				p.setQtd_paciente_alta(rs.getInt("pacientes_com_alta"));
				p.setQtd_alta_transf(rs.getInt("por_transferencia"));
				p.setPerc_alta_transf(rs.getDouble("p_trasf_total"));
				p.setPerc_alta_transf_msc(rs.getDouble("p_trans_m"));
				p.setPerc_alta_transf_fmn(rs.getDouble("p_trans_f"));
				
				p.setQtd_alta_morte(rs.getInt("por_morte"));
				p.setPerc_alta_morte(rs.getDouble("p_morte_total"));
				p.setPerc_alta_morte_msc(rs.getDouble("p_morte_m"));
				p.setPerc_alta_morte_fmn(rs.getDouble("p_morte_f"));
				
				p.setQtd_alta_melhoria(rs.getInt("por_melhoria"));
				p.setPerc_alta_melhoria(rs.getDouble("p_melhoria_total"));
				p.setPerc_alta_melhoria_msc(rs.getDouble("p_melhoria_m"));
				p.setPerc_alta_melhoria_fmn(rs.getDouble("p_melhoria_f"));
				
				lista.add(p);
				
			}
			ps.close();
			con.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return lista;
	}

	public List<AltaMedicaBean> buscarHistoricoAltaMedicaEmAnoIdades(int ano,int idade1,int idade2) {
		String sql = "select count(a.id_alta) as 'pacientes_com_alta', COUNT(CASE WHEN a.motivo_alta = 'transferencia' THEN 0 END) AS 'por_transferencia',"
				   + "CAST(COUNT(CASE WHEN a.motivo_alta = 'transferencia' THEN 0 END) * 100 / count(a.id_alta) AS DECIMAL(10, 1)) as 'p_trasf_total',"
				   + "CAST(COUNT(CASE WHEN a.motivo_alta = 'transferencia' AND g.id_genero = 1 THEN 0 END) * 100 / count(a.id_alta) AS DECIMAL(10, 1)) as 'p_trans_m',"
				   + "CAST(COUNT(CASE WHEN a.motivo_alta = 'transferencia' AND g.id_genero = 2 THEN 0 END) * 100 / count(a.id_alta) AS DECIMAL(10, 1)) as 'p_trans_f',"
				   + "COUNT(CASE WHEN a.motivo_alta = 'morte' THEN 0 END) AS 'por_morte', CAST(COUNT(CASE WHEN a.motivo_alta = 'morte' THEN 0 END) * 100 / count(a.id_alta) AS DECIMAL(10, 1)) as 'p_morte_total',"
				   + "CAST(COUNT(CASE WHEN a.motivo_alta = 'morte' AND g.id_genero = 1 THEN 0 END) * 100 / count(a.id_alta) AS DECIMAL(10, 1)) as 'p_morte_m',"
				   + "CAST(COUNT(CASE WHEN a.motivo_alta = 'morte' AND g.id_genero = 2 THEN 0 END) * 100 / count(a.id_alta) AS DECIMAL(10, 1)) as 'p_morte_f',"
				   + "COUNT(CASE WHEN a.motivo_alta = 'melhoria' THEN 0 END) AS 'por_melhoria', CAST(COUNT(CASE WHEN a.motivo_alta = 'melhoria' THEN 0 END) * 100 / count(a.id_alta) AS DECIMAL(10, 1)) as 'p_melhoria_total',"
				   + "CAST(COUNT(CASE WHEN a.motivo_alta = 'melhoria' AND g.id_genero = 1 THEN 0 END) * 100 / count(a.id_alta) AS DECIMAL(10, 1)) as 'p_melhoria_m',"
				   + "CAST(COUNT(CASE WHEN a.motivo_alta = 'melhoria' AND g.id_genero = 2 THEN 0 END) * 100 / count(a.id_alta) AS DECIMAL(10, 1)) as 'p_melhoria_f'"
				   + "from tblaltamedica a, tblinternamento i, tblgenero g, tblcidadao cc where  i.id_internamento = a.FK_internamento and  i.FK_paciente = cc.FK_entidade and cc.FK_genero = g.id_genero "
				   + "and year(a.data_registo) = ? and year(from_days((to_days(now()) - to_days(cc.data_nascimento)))) between ? and ?";
		
		List<AltaMedicaBean> lista = new ArrayList<AltaMedicaBean>();
		try {
			con = Conexao.getConexao();
			PreparedStatement ps = con.prepareStatement(sql);
			ps.setInt(1, ano);
			ps.setInt(2, idade1);
			ps.setInt(3, idade2);
			ResultSet rs = ps.executeQuery();
			while (rs.next()) {
				AltaMedicaBean p = new AltaMedicaBean();
				p.setQtd_paciente_alta(rs.getInt("pacientes_com_alta"));
				p.setQtd_alta_transf(rs.getInt("por_transferencia"));
				p.setPerc_alta_transf(rs.getDouble("p_trasf_total"));
				p.setPerc_alta_transf_msc(rs.getDouble("p_trans_m"));
				p.setPerc_alta_transf_fmn(rs.getDouble("p_trans_f"));
				
				p.setQtd_alta_morte(rs.getInt("por_morte"));
				p.setPerc_alta_morte(rs.getDouble("p_morte_total"));
				p.setPerc_alta_morte_msc(rs.getDouble("p_morte_m"));
				p.setPerc_alta_morte_fmn(rs.getDouble("p_morte_f"));
				
				p.setQtd_alta_melhoria(rs.getInt("por_melhoria"));
				p.setPerc_alta_melhoria(rs.getDouble("p_melhoria_total"));
				p.setPerc_alta_melhoria_msc(rs.getDouble("p_melhoria_m"));
				p.setPerc_alta_melhoria_fmn(rs.getDouble("p_melhoria_f"));
				
				lista.add(p);
				
			}
			ps.close();
			con.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return lista;
	}
	
	

	public List<AltaMedicaBean> buscarHistoricoAltaMedicaEmAnoMesDias(int ano,int mes,int dia1,int dia2) {
		String sql = "select count(a.id_alta) as 'pacientes_com_alta', COUNT(CASE WHEN a.motivo_alta = 'transferencia' THEN 0 END) AS 'por_transferencia',"
				   + "CAST(COUNT(CASE WHEN a.motivo_alta = 'transferencia' THEN 0 END) * 100 / count(a.id_alta) AS DECIMAL(10, 1)) as 'p_trasf_total',"
				   + "CAST(COUNT(CASE WHEN a.motivo_alta = 'transferencia' AND g.id_genero = 1 THEN 0 END) * 100 / count(a.id_alta) AS DECIMAL(10, 1)) as 'p_trans_m',"
				   + "CAST(COUNT(CASE WHEN a.motivo_alta = 'transferencia' AND g.id_genero = 2 THEN 0 END) * 100 / count(a.id_alta) AS DECIMAL(10, 1)) as 'p_trans_f',"
				   + "COUNT(CASE WHEN a.motivo_alta = 'morte' THEN 0 END) AS 'por_morte', CAST(COUNT(CASE WHEN a.motivo_alta = 'morte' THEN 0 END) * 100 / count(a.id_alta) AS DECIMAL(10, 1)) as 'p_morte_total',"
				   + "CAST(COUNT(CASE WHEN a.motivo_alta = 'morte' AND g.id_genero = 1 THEN 0 END) * 100 / count(a.id_alta) AS DECIMAL(10, 1)) as 'p_morte_m',"
				   + "CAST(COUNT(CASE WHEN a.motivo_alta = 'morte' AND g.id_genero = 2 THEN 0 END) * 100 / count(a.id_alta) AS DECIMAL(10, 1)) as 'p_morte_f',"
				   + "COUNT(CASE WHEN a.motivo_alta = 'melhoria' THEN 0 END) AS 'por_melhoria', CAST(COUNT(CASE WHEN a.motivo_alta = 'melhoria' THEN 0 END) * 100 / count(a.id_alta) AS DECIMAL(10, 1)) as 'p_melhoria_total',"
				   + "CAST(COUNT(CASE WHEN a.motivo_alta = 'melhoria' AND g.id_genero = 1 THEN 0 END) * 100 / count(a.id_alta) AS DECIMAL(10, 1)) as 'p_melhoria_m',"
				   + "CAST(COUNT(CASE WHEN a.motivo_alta = 'melhoria' AND g.id_genero = 2 THEN 0 END) * 100 / count(a.id_alta) AS DECIMAL(10, 1)) as 'p_melhoria_f'"
				   + "from tblaltamedica a, tblinternamento i, tblgenero g, tblcidadao cc where  i.id_internamento = a.FK_internamento and  i.FK_paciente = cc.FK_entidade and cc.FK_genero = g.id_genero "
				   + "and year(a.data_registo) = ? and year(a.data_registo) = ? and day(a.data_registo) between ? and ?";
		
		List<AltaMedicaBean> lista = new ArrayList<AltaMedicaBean>();
		try {
			con = Conexao.getConexao();
			PreparedStatement ps = con.prepareStatement(sql);
			ps.setInt(1, ano);
			ps.setInt(2, mes);
			ps.setInt(3, dia1);
			ps.setInt(4, dia2);
			ResultSet rs = ps.executeQuery();
			while (rs.next()) {
				AltaMedicaBean p = new AltaMedicaBean();
				p.setQtd_paciente_alta(rs.getInt("pacientes_com_alta"));
				p.setQtd_alta_transf(rs.getInt("por_transferencia"));
				p.setPerc_alta_transf(rs.getDouble("p_trasf_total"));
				p.setPerc_alta_transf_msc(rs.getDouble("p_trans_m"));
				p.setPerc_alta_transf_fmn(rs.getDouble("p_trans_f"));
				
				p.setQtd_alta_morte(rs.getInt("por_morte"));
				p.setPerc_alta_morte(rs.getDouble("p_morte_total"));
				p.setPerc_alta_morte_msc(rs.getDouble("p_morte_m"));
				p.setPerc_alta_morte_fmn(rs.getDouble("p_morte_f"));
				
				p.setQtd_alta_melhoria(rs.getInt("por_melhoria"));
				p.setPerc_alta_melhoria(rs.getDouble("p_melhoria_total"));
				p.setPerc_alta_melhoria_msc(rs.getDouble("p_melhoria_m"));
				p.setPerc_alta_melhoria_fmn(rs.getDouble("p_melhoria_f"));
				
				lista.add(p);
				
			}
			ps.close();
			con.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return lista;
	}

	public List<AltaMedicaBean> buscarHistoricoAltaMedicaEmAnoMesDiasIdades(int ano,int mes,int dia1,int dia2,int idade1,int idade2) {
		String sql = "select count(a.id_alta) as 'pacientes_com_alta', COUNT(CASE WHEN a.motivo_alta = 'transferencia' THEN 0 END) AS 'por_transferencia',"
				   + "CAST(COUNT(CASE WHEN a.motivo_alta = 'transferencia' THEN 0 END) * 100 / count(a.id_alta) AS DECIMAL(10, 1)) as 'p_trasf_total',"
				   + "CAST(COUNT(CASE WHEN a.motivo_alta = 'transferencia' AND g.id_genero = 1 THEN 0 END) * 100 / count(a.id_alta) AS DECIMAL(10, 1)) as 'p_trans_m',"
				   + "CAST(COUNT(CASE WHEN a.motivo_alta = 'transferencia' AND g.id_genero = 2 THEN 0 END) * 100 / count(a.id_alta) AS DECIMAL(10, 1)) as 'p_trans_f',"
				   + "COUNT(CASE WHEN a.motivo_alta = 'morte' THEN 0 END) AS 'por_morte', CAST(COUNT(CASE WHEN a.motivo_alta = 'morte' THEN 0 END) * 100 / count(a.id_alta) AS DECIMAL(10, 1)) as 'p_morte_total',"
				   + "CAST(COUNT(CASE WHEN a.motivo_alta = 'morte' AND g.id_genero = 1 THEN 0 END) * 100 / count(a.id_alta) AS DECIMAL(10, 1)) as 'p_morte_m',"
				   + "CAST(COUNT(CASE WHEN a.motivo_alta = 'morte' AND g.id_genero = 2 THEN 0 END) * 100 / count(a.id_alta) AS DECIMAL(10, 1)) as 'p_morte_f',"
				   + "COUNT(CASE WHEN a.motivo_alta = 'melhoria' THEN 0 END) AS 'por_melhoria', CAST(COUNT(CASE WHEN a.motivo_alta = 'melhoria' THEN 0 END) * 100 / count(a.id_alta) AS DECIMAL(10, 1)) as 'p_melhoria_total',"
				   + "CAST(COUNT(CASE WHEN a.motivo_alta = 'melhoria' AND g.id_genero = 1 THEN 0 END) * 100 / count(a.id_alta) AS DECIMAL(10, 1)) as 'p_melhoria_m',"
				   + "CAST(COUNT(CASE WHEN a.motivo_alta = 'melhoria' AND g.id_genero = 2 THEN 0 END) * 100 / count(a.id_alta) AS DECIMAL(10, 1)) as 'p_melhoria_f'"
				   + "from tblaltamedica a, tblinternamento i, tblgenero g, tblcidadao cc where  i.id_internamento = a.FK_internamento and  i.FK_paciente = cc.FK_entidade and cc.FK_genero = g.id_genero "
				   + "and year(a.data_registo) = ? and year(a.data_registo) = ? and day(a.data_registo) between ? and ? and year(from_days((to_days(now()) - to_days(cc.data_nascimento)))) between ? and ?";
		
		List<AltaMedicaBean> lista = new ArrayList<AltaMedicaBean>();
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
			while (rs.next()) {
				AltaMedicaBean p = new AltaMedicaBean();
				p.setQtd_paciente_alta(rs.getInt("pacientes_com_alta"));
				p.setQtd_alta_transf(rs.getInt("por_transferencia"));
				p.setPerc_alta_transf(rs.getDouble("p_trasf_total"));
				p.setPerc_alta_transf_msc(rs.getDouble("p_trans_m"));
				p.setPerc_alta_transf_fmn(rs.getDouble("p_trans_f"));
				
				p.setQtd_alta_morte(rs.getInt("por_morte"));
				p.setPerc_alta_morte(rs.getDouble("p_morte_total"));
				p.setPerc_alta_morte_msc(rs.getDouble("p_morte_m"));
				p.setPerc_alta_morte_fmn(rs.getDouble("p_morte_f"));
				
				p.setQtd_alta_melhoria(rs.getInt("por_melhoria"));
				p.setPerc_alta_melhoria(rs.getDouble("p_melhoria_total"));
				p.setPerc_alta_melhoria_msc(rs.getDouble("p_melhoria_m"));
				p.setPerc_alta_melhoria_fmn(rs.getDouble("p_melhoria_f"));
				
				lista.add(p);
				
			}
			ps.close();
			con.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return lista;
	}

	public List<AltaMedicaBean> buscarHistoricoAltaMedicaEmAnoSetTrimestre(int ano,int aux) {
		String sql = "";
		
		if(aux == 1){
			sql = "select count(a.id_alta) as 'pacientes_com_alta', COUNT(CASE WHEN a.motivo_alta = 'transferencia' THEN 0 END) AS 'por_transferencia',"
					   + "CAST(COUNT(CASE WHEN a.motivo_alta = 'transferencia' THEN 0 END) * 100 / count(a.id_alta) AS DECIMAL(10, 1)) as 'p_trasf_total',"
					   + "CAST(COUNT(CASE WHEN a.motivo_alta = 'transferencia' AND g.id_genero = 1 THEN 0 END) * 100 / count(a.id_alta) AS DECIMAL(10, 1)) as 'p_trans_m',"
					   + "CAST(COUNT(CASE WHEN a.motivo_alta = 'transferencia' AND g.id_genero = 2 THEN 0 END) * 100 / count(a.id_alta) AS DECIMAL(10, 1)) as 'p_trans_f',"
					   + "COUNT(CASE WHEN a.motivo_alta = 'morte' THEN 0 END) AS 'por_morte', CAST(COUNT(CASE WHEN a.motivo_alta = 'morte' THEN 0 END) * 100 / count(a.id_alta) AS DECIMAL(10, 1)) as 'p_morte_total',"
					   + "CAST(COUNT(CASE WHEN a.motivo_alta = 'morte' AND g.id_genero = 1 THEN 0 END) * 100 / count(a.id_alta) AS DECIMAL(10, 1)) as 'p_morte_m',"
					   + "CAST(COUNT(CASE WHEN a.motivo_alta = 'morte' AND g.id_genero = 2 THEN 0 END) * 100 / count(a.id_alta) AS DECIMAL(10, 1)) as 'p_morte_f',"
					   + "COUNT(CASE WHEN a.motivo_alta = 'melhoria' THEN 0 END) AS 'por_melhoria', CAST(COUNT(CASE WHEN a.motivo_alta = 'melhoria' THEN 0 END) * 100 / count(a.id_alta) AS DECIMAL(10, 1)) as 'p_melhoria_total',"
					   + "CAST(COUNT(CASE WHEN a.motivo_alta = 'melhoria' AND g.id_genero = 1 THEN 0 END) * 100 / count(a.id_alta) AS DECIMAL(10, 1)) as 'p_melhoria_m',"
					   + "CAST(COUNT(CASE WHEN a.motivo_alta = 'melhoria' AND g.id_genero = 2 THEN 0 END) * 100 / count(a.id_alta) AS DECIMAL(10, 1)) as 'p_melhoria_f'"
					   + "from tblaltamedica a, tblinternamento i, tblgenero g, tblcidadao cc where  i.id_internamento = a.FK_internamento and  i.FK_paciente = cc.FK_entidade and cc.FK_genero = g.id_genero "
					   + "and year(a.data_registo) = ? and month(a.data_registo) between 1 and 3";
		}
		
		if(aux == 2){
			sql = "select count(a.id_alta) as 'pacientes_com_alta', COUNT(CASE WHEN a.motivo_alta = 'transferencia' THEN 0 END) AS 'por_transferencia',"
					   + "CAST(COUNT(CASE WHEN a.motivo_alta = 'transferencia' THEN 0 END) * 100 / count(a.id_alta) AS DECIMAL(10, 1)) as 'p_trasf_total',"
					   + "CAST(COUNT(CASE WHEN a.motivo_alta = 'transferencia' AND g.id_genero = 1 THEN 0 END) * 100 / count(a.id_alta) AS DECIMAL(10, 1)) as 'p_trans_m',"
					   + "CAST(COUNT(CASE WHEN a.motivo_alta = 'transferencia' AND g.id_genero = 2 THEN 0 END) * 100 / count(a.id_alta) AS DECIMAL(10, 1)) as 'p_trans_f',"
					   + "COUNT(CASE WHEN a.motivo_alta = 'morte' THEN 0 END) AS 'por_morte', CAST(COUNT(CASE WHEN a.motivo_alta = 'morte' THEN 0 END) * 100 / count(a.id_alta) AS DECIMAL(10, 1)) as 'p_morte_total',"
					   + "CAST(COUNT(CASE WHEN a.motivo_alta = 'morte' AND g.id_genero = 1 THEN 0 END) * 100 / count(a.id_alta) AS DECIMAL(10, 1)) as 'p_morte_m',"
					   + "CAST(COUNT(CASE WHEN a.motivo_alta = 'morte' AND g.id_genero = 2 THEN 0 END) * 100 / count(a.id_alta) AS DECIMAL(10, 1)) as 'p_morte_f',"
					   + "COUNT(CASE WHEN a.motivo_alta = 'melhoria' THEN 0 END) AS 'por_melhoria', CAST(COUNT(CASE WHEN a.motivo_alta = 'melhoria' THEN 0 END) * 100 / count(a.id_alta) AS DECIMAL(10, 1)) as 'p_melhoria_total',"
					   + "CAST(COUNT(CASE WHEN a.motivo_alta = 'melhoria' AND g.id_genero = 1 THEN 0 END) * 100 / count(a.id_alta) AS DECIMAL(10, 1)) as 'p_melhoria_m',"
					   + "CAST(COUNT(CASE WHEN a.motivo_alta = 'melhoria' AND g.id_genero = 2 THEN 0 END) * 100 / count(a.id_alta) AS DECIMAL(10, 1)) as 'p_melhoria_f'"
					   + "from tblaltamedica a, tblinternamento i, tblgenero g, tblcidadao cc where  i.id_internamento = a.FK_internamento and  i.FK_paciente = cc.FK_entidade and cc.FK_genero = g.id_genero "
					   + "and year(a.data_registo) = ? and month(a.data_registo) between 4 and 6";
		}
		
		if(aux == 3){
			sql = "select count(a.id_alta) as 'pacientes_com_alta', COUNT(CASE WHEN a.motivo_alta = 'transferencia' THEN 0 END) AS 'por_transferencia',"
					   + "CAST(COUNT(CASE WHEN a.motivo_alta = 'transferencia' THEN 0 END) * 100 / count(a.id_alta) AS DECIMAL(10, 1)) as 'p_trasf_total',"
					   + "CAST(COUNT(CASE WHEN a.motivo_alta = 'transferencia' AND g.id_genero = 1 THEN 0 END) * 100 / count(a.id_alta) AS DECIMAL(10, 1)) as 'p_trans_m',"
					   + "CAST(COUNT(CASE WHEN a.motivo_alta = 'transferencia' AND g.id_genero = 2 THEN 0 END) * 100 / count(a.id_alta) AS DECIMAL(10, 1)) as 'p_trans_f',"
					   + "COUNT(CASE WHEN a.motivo_alta = 'morte' THEN 0 END) AS 'por_morte', CAST(COUNT(CASE WHEN a.motivo_alta = 'morte' THEN 0 END) * 100 / count(a.id_alta) AS DECIMAL(10, 1)) as 'p_morte_total',"
					   + "CAST(COUNT(CASE WHEN a.motivo_alta = 'morte' AND g.id_genero = 1 THEN 0 END) * 100 / count(a.id_alta) AS DECIMAL(10, 1)) as 'p_morte_m',"
					   + "CAST(COUNT(CASE WHEN a.motivo_alta = 'morte' AND g.id_genero = 2 THEN 0 END) * 100 / count(a.id_alta) AS DECIMAL(10, 1)) as 'p_morte_f',"
					   + "COUNT(CASE WHEN a.motivo_alta = 'melhoria' THEN 0 END) AS 'por_melhoria', CAST(COUNT(CASE WHEN a.motivo_alta = 'melhoria' THEN 0 END) * 100 / count(a.id_alta) AS DECIMAL(10, 1)) as 'p_melhoria_total',"
					   + "CAST(COUNT(CASE WHEN a.motivo_alta = 'melhoria' AND g.id_genero = 1 THEN 0 END) * 100 / count(a.id_alta) AS DECIMAL(10, 1)) as 'p_melhoria_m',"
					   + "CAST(COUNT(CASE WHEN a.motivo_alta = 'melhoria' AND g.id_genero = 2 THEN 0 END) * 100 / count(a.id_alta) AS DECIMAL(10, 1)) as 'p_melhoria_f'"
					   + "from tblaltamedica a, tblinternamento i, tblgenero g, tblcidadao cc where  i.id_internamento = a.FK_internamento and  i.FK_paciente = cc.FK_entidade and cc.FK_genero = g.id_genero "
					   + "and year(a.data_registo) = ? and month(a.data_registo) between 7 and 9";
		}
		
		if(aux == 4){
			sql = "select count(a.id_alta) as 'pacientes_com_alta', COUNT(CASE WHEN a.motivo_alta = 'transferencia' THEN 0 END) AS 'por_transferencia',"
					   + "CAST(COUNT(CASE WHEN a.motivo_alta = 'transferencia' THEN 0 END) * 100 / count(a.id_alta) AS DECIMAL(10, 1)) as 'p_trasf_total',"
					   + "CAST(COUNT(CASE WHEN a.motivo_alta = 'transferencia' AND g.id_genero = 1 THEN 0 END) * 100 / count(a.id_alta) AS DECIMAL(10, 1)) as 'p_trans_m',"
					   + "CAST(COUNT(CASE WHEN a.motivo_alta = 'transferencia' AND g.id_genero = 2 THEN 0 END) * 100 / count(a.id_alta) AS DECIMAL(10, 1)) as 'p_trans_f',"
					   + "COUNT(CASE WHEN a.motivo_alta = 'morte' THEN 0 END) AS 'por_morte', CAST(COUNT(CASE WHEN a.motivo_alta = 'morte' THEN 0 END) * 100 / count(a.id_alta) AS DECIMAL(10, 1)) as 'p_morte_total',"
					   + "CAST(COUNT(CASE WHEN a.motivo_alta = 'morte' AND g.id_genero = 1 THEN 0 END) * 100 / count(a.id_alta) AS DECIMAL(10, 1)) as 'p_morte_m',"
					   + "CAST(COUNT(CASE WHEN a.motivo_alta = 'morte' AND g.id_genero = 2 THEN 0 END) * 100 / count(a.id_alta) AS DECIMAL(10, 1)) as 'p_morte_f',"
					   + "COUNT(CASE WHEN a.motivo_alta = 'melhoria' THEN 0 END) AS 'por_melhoria', CAST(COUNT(CASE WHEN a.motivo_alta = 'melhoria' THEN 0 END) * 100 / count(a.id_alta) AS DECIMAL(10, 1)) as 'p_melhoria_total',"
					   + "CAST(COUNT(CASE WHEN a.motivo_alta = 'melhoria' AND g.id_genero = 1 THEN 0 END) * 100 / count(a.id_alta) AS DECIMAL(10, 1)) as 'p_melhoria_m',"
					   + "CAST(COUNT(CASE WHEN a.motivo_alta = 'melhoria' AND g.id_genero = 2 THEN 0 END) * 100 / count(a.id_alta) AS DECIMAL(10, 1)) as 'p_melhoria_f'"
					   + "from tblaltamedica a, tblinternamento i, tblgenero g, tblcidadao cc where  i.id_internamento = a.FK_internamento and  i.FK_paciente = cc.FK_entidade and cc.FK_genero = g.id_genero "
					   + "and year(a.data_registo) = ? and month(a.data_registo) between 10 and 12";
		}
		
		if(aux == 5){
			sql = "select count(a.id_alta) as 'pacientes_com_alta', COUNT(CASE WHEN a.motivo_alta = 'transferencia' THEN 0 END) AS 'por_transferencia',"
					   + "CAST(COUNT(CASE WHEN a.motivo_alta = 'transferencia' THEN 0 END) * 100 / count(a.id_alta) AS DECIMAL(10, 1)) as 'p_trasf_total',"
					   + "CAST(COUNT(CASE WHEN a.motivo_alta = 'transferencia' AND g.id_genero = 1 THEN 0 END) * 100 / count(a.id_alta) AS DECIMAL(10, 1)) as 'p_trans_m',"
					   + "CAST(COUNT(CASE WHEN a.motivo_alta = 'transferencia' AND g.id_genero = 2 THEN 0 END) * 100 / count(a.id_alta) AS DECIMAL(10, 1)) as 'p_trans_f',"
					   + "COUNT(CASE WHEN a.motivo_alta = 'morte' THEN 0 END) AS 'por_morte', CAST(COUNT(CASE WHEN a.motivo_alta = 'morte' THEN 0 END) * 100 / count(a.id_alta) AS DECIMAL(10, 1)) as 'p_morte_total',"
					   + "CAST(COUNT(CASE WHEN a.motivo_alta = 'morte' AND g.id_genero = 1 THEN 0 END) * 100 / count(a.id_alta) AS DECIMAL(10, 1)) as 'p_morte_m',"
					   + "CAST(COUNT(CASE WHEN a.motivo_alta = 'morte' AND g.id_genero = 2 THEN 0 END) * 100 / count(a.id_alta) AS DECIMAL(10, 1)) as 'p_morte_f',"
					   + "COUNT(CASE WHEN a.motivo_alta = 'melhoria' THEN 0 END) AS 'por_melhoria', CAST(COUNT(CASE WHEN a.motivo_alta = 'melhoria' THEN 0 END) * 100 / count(a.id_alta) AS DECIMAL(10, 1)) as 'p_melhoria_total',"
					   + "CAST(COUNT(CASE WHEN a.motivo_alta = 'melhoria' AND g.id_genero = 1 THEN 0 END) * 100 / count(a.id_alta) AS DECIMAL(10, 1)) as 'p_melhoria_m',"
					   + "CAST(COUNT(CASE WHEN a.motivo_alta = 'melhoria' AND g.id_genero = 2 THEN 0 END) * 100 / count(a.id_alta) AS DECIMAL(10, 1)) as 'p_melhoria_f'"
					   + "from tblaltamedica a, tblinternamento i, tblgenero g, tblcidadao cc where  i.id_internamento = a.FK_internamento and  i.FK_paciente = cc.FK_entidade and cc.FK_genero = g.id_genero "
					   + "and year(a.data_registo) = ? and month(a.data_registo) between 1 and 6";
		}
		
		if(aux == 6){
			sql = "select count(a.id_alta) as 'pacientes_com_alta', COUNT(CASE WHEN a.motivo_alta = 'transferencia' THEN 0 END) AS 'por_transferencia',"
					   + "CAST(COUNT(CASE WHEN a.motivo_alta = 'transferencia' THEN 0 END) * 100 / count(a.id_alta) AS DECIMAL(10, 1)) as 'p_trasf_total',"
					   + "CAST(COUNT(CASE WHEN a.motivo_alta = 'transferencia' AND g.id_genero = 1 THEN 0 END) * 100 / count(a.id_alta) AS DECIMAL(10, 1)) as 'p_trans_m',"
					   + "CAST(COUNT(CASE WHEN a.motivo_alta = 'transferencia' AND g.id_genero = 2 THEN 0 END) * 100 / count(a.id_alta) AS DECIMAL(10, 1)) as 'p_trans_f',"
					   + "COUNT(CASE WHEN a.motivo_alta = 'morte' THEN 0 END) AS 'por_morte', CAST(COUNT(CASE WHEN a.motivo_alta = 'morte' THEN 0 END) * 100 / count(a.id_alta) AS DECIMAL(10, 1)) as 'p_morte_total',"
					   + "CAST(COUNT(CASE WHEN a.motivo_alta = 'morte' AND g.id_genero = 1 THEN 0 END) * 100 / count(a.id_alta) AS DECIMAL(10, 1)) as 'p_morte_m',"
					   + "CAST(COUNT(CASE WHEN a.motivo_alta = 'morte' AND g.id_genero = 2 THEN 0 END) * 100 / count(a.id_alta) AS DECIMAL(10, 1)) as 'p_morte_f',"
					   + "COUNT(CASE WHEN a.motivo_alta = 'melhoria' THEN 0 END) AS 'por_melhoria', CAST(COUNT(CASE WHEN a.motivo_alta = 'melhoria' THEN 0 END) * 100 / count(a.id_alta) AS DECIMAL(10, 1)) as 'p_melhoria_total',"
					   + "CAST(COUNT(CASE WHEN a.motivo_alta = 'melhoria' AND g.id_genero = 1 THEN 0 END) * 100 / count(a.id_alta) AS DECIMAL(10, 1)) as 'p_melhoria_m',"
					   + "CAST(COUNT(CASE WHEN a.motivo_alta = 'melhoria' AND g.id_genero = 2 THEN 0 END) * 100 / count(a.id_alta) AS DECIMAL(10, 1)) as 'p_melhoria_f'"
					   + "from tblaltamedica a, tblinternamento i, tblgenero g, tblcidadao cc where  i.id_internamento = a.FK_internamento and  i.FK_paciente = cc.FK_entidade and cc.FK_genero = g.id_genero "
					   + "and year(a.data_registo) = ? and month(a.data_registo) between 7 and 12";
		}
		
		List<AltaMedicaBean> lista = new ArrayList<AltaMedicaBean>();
		try {
			con = Conexao.getConexao();
			PreparedStatement ps = con.prepareStatement(sql);
			ps.setInt(1, ano);
			 
			ResultSet rs = ps.executeQuery();
			while (rs.next()) {
				AltaMedicaBean p = new AltaMedicaBean();
				p.setQtd_paciente_alta(rs.getInt("pacientes_com_alta"));
				p.setQtd_alta_transf(rs.getInt("por_transferencia"));
				p.setPerc_alta_transf(rs.getDouble("p_trasf_total"));
				p.setPerc_alta_transf_msc(rs.getDouble("p_trans_m"));
				p.setPerc_alta_transf_fmn(rs.getDouble("p_trans_f"));
				
				p.setQtd_alta_morte(rs.getInt("por_morte"));
				p.setPerc_alta_morte(rs.getDouble("p_morte_total"));
				p.setPerc_alta_morte_msc(rs.getDouble("p_morte_m"));
				p.setPerc_alta_morte_fmn(rs.getDouble("p_morte_f"));
				
				p.setQtd_alta_melhoria(rs.getInt("por_melhoria"));
				p.setPerc_alta_melhoria(rs.getDouble("p_melhoria_total"));
				p.setPerc_alta_melhoria_msc(rs.getDouble("p_melhoria_m"));
				p.setPerc_alta_melhoria_fmn(rs.getDouble("p_melhoria_f"));
				
				lista.add(p);
				
			}
			ps.close();
			con.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return lista;
	}
	
	public List<AltaMedicaBean> buscarHistoricoAltaMedicaEmAnoSetTrimestreIdades(int ano,int aux,int idade1,int idade2) {
		String sql = "";
		
		if(aux == 1){
			sql = "select count(a.id_alta) as 'pacientes_com_alta', COUNT(CASE WHEN a.motivo_alta = 'transferencia' THEN 0 END) AS 'por_transferencia',"
					   + "CAST(COUNT(CASE WHEN a.motivo_alta = 'transferencia' THEN 0 END) * 100 / count(a.id_alta) AS DECIMAL(10, 1)) as 'p_trasf_total',"
					   + "CAST(COUNT(CASE WHEN a.motivo_alta = 'transferencia' AND g.id_genero = 1 THEN 0 END) * 100 / count(a.id_alta) AS DECIMAL(10, 1)) as 'p_trans_m',"
					   + "CAST(COUNT(CASE WHEN a.motivo_alta = 'transferencia' AND g.id_genero = 2 THEN 0 END) * 100 / count(a.id_alta) AS DECIMAL(10, 1)) as 'p_trans_f',"
					   + "COUNT(CASE WHEN a.motivo_alta = 'morte' THEN 0 END) AS 'por_morte', CAST(COUNT(CASE WHEN a.motivo_alta = 'morte' THEN 0 END) * 100 / count(a.id_alta) AS DECIMAL(10, 1)) as 'p_morte_total',"
					   + "CAST(COUNT(CASE WHEN a.motivo_alta = 'morte' AND g.id_genero = 1 THEN 0 END) * 100 / count(a.id_alta) AS DECIMAL(10, 1)) as 'p_morte_m',"
					   + "CAST(COUNT(CASE WHEN a.motivo_alta = 'morte' AND g.id_genero = 2 THEN 0 END) * 100 / count(a.id_alta) AS DECIMAL(10, 1)) as 'p_morte_f',"
					   + "COUNT(CASE WHEN a.motivo_alta = 'melhoria' THEN 0 END) AS 'por_melhoria', CAST(COUNT(CASE WHEN a.motivo_alta = 'melhoria' THEN 0 END) * 100 / count(a.id_alta) AS DECIMAL(10, 1)) as 'p_melhoria_total',"
					   + "CAST(COUNT(CASE WHEN a.motivo_alta = 'melhoria' AND g.id_genero = 1 THEN 0 END) * 100 / count(a.id_alta) AS DECIMAL(10, 1)) as 'p_melhoria_m',"
					   + "CAST(COUNT(CASE WHEN a.motivo_alta = 'melhoria' AND g.id_genero = 2 THEN 0 END) * 100 / count(a.id_alta) AS DECIMAL(10, 1)) as 'p_melhoria_f'"
					   + "from tblaltamedica a, tblinternamento i, tblgenero g, tblcidadao cc where  i.id_internamento = a.FK_internamento and  i.FK_paciente = cc.FK_entidade and cc.FK_genero = g.id_genero "
					   + "and year(a.data_registo) = ? and month(a.data_registo) between 1 and 3 and year(from_days((to_days(now()) - to_days(cc.data_nascimento)))) between ? and ?";
		}
		
		if(aux == 2){
			sql = "select count(a.id_alta) as 'pacientes_com_alta', COUNT(CASE WHEN a.motivo_alta = 'transferencia' THEN 0 END) AS 'por_transferencia',"
					   + "CAST(COUNT(CASE WHEN a.motivo_alta = 'transferencia' THEN 0 END) * 100 / count(a.id_alta) AS DECIMAL(10, 1)) as 'p_trasf_total',"
					   + "CAST(COUNT(CASE WHEN a.motivo_alta = 'transferencia' AND g.id_genero = 1 THEN 0 END) * 100 / count(a.id_alta) AS DECIMAL(10, 1)) as 'p_trans_m',"
					   + "CAST(COUNT(CASE WHEN a.motivo_alta = 'transferencia' AND g.id_genero = 2 THEN 0 END) * 100 / count(a.id_alta) AS DECIMAL(10, 1)) as 'p_trans_f',"
					   + "COUNT(CASE WHEN a.motivo_alta = 'morte' THEN 0 END) AS 'por_morte', CAST(COUNT(CASE WHEN a.motivo_alta = 'morte' THEN 0 END) * 100 / count(a.id_alta) AS DECIMAL(10, 1)) as 'p_morte_total',"
					   + "CAST(COUNT(CASE WHEN a.motivo_alta = 'morte' AND g.id_genero = 1 THEN 0 END) * 100 / count(a.id_alta) AS DECIMAL(10, 1)) as 'p_morte_m',"
					   + "CAST(COUNT(CASE WHEN a.motivo_alta = 'morte' AND g.id_genero = 2 THEN 0 END) * 100 / count(a.id_alta) AS DECIMAL(10, 1)) as 'p_morte_f',"
					   + "COUNT(CASE WHEN a.motivo_alta = 'melhoria' THEN 0 END) AS 'por_melhoria', CAST(COUNT(CASE WHEN a.motivo_alta = 'melhoria' THEN 0 END) * 100 / count(a.id_alta) AS DECIMAL(10, 1)) as 'p_melhoria_total',"
					   + "CAST(COUNT(CASE WHEN a.motivo_alta = 'melhoria' AND g.id_genero = 1 THEN 0 END) * 100 / count(a.id_alta) AS DECIMAL(10, 1)) as 'p_melhoria_m',"
					   + "CAST(COUNT(CASE WHEN a.motivo_alta = 'melhoria' AND g.id_genero = 2 THEN 0 END) * 100 / count(a.id_alta) AS DECIMAL(10, 1)) as 'p_melhoria_f'"
					   + "from tblaltamedica a, tblinternamento i, tblgenero g, tblcidadao cc where  i.id_internamento = a.FK_internamento and  i.FK_paciente = cc.FK_entidade and cc.FK_genero = g.id_genero "
					   + "and year(a.data_registo) = ? and month(a.data_registo) between 4 and 6 and year(from_days((to_days(now()) - to_days(cc.data_nascimento)))) between ? and ?";
		}
		
		if(aux == 3){
			sql = "select count(a.id_alta) as 'pacientes_com_alta', COUNT(CASE WHEN a.motivo_alta = 'transferencia' THEN 0 END) AS 'por_transferencia',"
					   + "CAST(COUNT(CASE WHEN a.motivo_alta = 'transferencia' THEN 0 END) * 100 / count(a.id_alta) AS DECIMAL(10, 1)) as 'p_trasf_total',"
					   + "CAST(COUNT(CASE WHEN a.motivo_alta = 'transferencia' AND g.id_genero = 1 THEN 0 END) * 100 / count(a.id_alta) AS DECIMAL(10, 1)) as 'p_trans_m',"
					   + "CAST(COUNT(CASE WHEN a.motivo_alta = 'transferencia' AND g.id_genero = 2 THEN 0 END) * 100 / count(a.id_alta) AS DECIMAL(10, 1)) as 'p_trans_f',"
					   + "COUNT(CASE WHEN a.motivo_alta = 'morte' THEN 0 END) AS 'por_morte', CAST(COUNT(CASE WHEN a.motivo_alta = 'morte' THEN 0 END) * 100 / count(a.id_alta) AS DECIMAL(10, 1)) as 'p_morte_total',"
					   + "CAST(COUNT(CASE WHEN a.motivo_alta = 'morte' AND g.id_genero = 1 THEN 0 END) * 100 / count(a.id_alta) AS DECIMAL(10, 1)) as 'p_morte_m',"
					   + "CAST(COUNT(CASE WHEN a.motivo_alta = 'morte' AND g.id_genero = 2 THEN 0 END) * 100 / count(a.id_alta) AS DECIMAL(10, 1)) as 'p_morte_f',"
					   + "COUNT(CASE WHEN a.motivo_alta = 'melhoria' THEN 0 END) AS 'por_melhoria', CAST(COUNT(CASE WHEN a.motivo_alta = 'melhoria' THEN 0 END) * 100 / count(a.id_alta) AS DECIMAL(10, 1)) as 'p_melhoria_total',"
					   + "CAST(COUNT(CASE WHEN a.motivo_alta = 'melhoria' AND g.id_genero = 1 THEN 0 END) * 100 / count(a.id_alta) AS DECIMAL(10, 1)) as 'p_melhoria_m',"
					   + "CAST(COUNT(CASE WHEN a.motivo_alta = 'melhoria' AND g.id_genero = 2 THEN 0 END) * 100 / count(a.id_alta) AS DECIMAL(10, 1)) as 'p_melhoria_f'"
					   + "from tblaltamedica a, tblinternamento i, tblgenero g, tblcidadao cc where  i.id_internamento = a.FK_internamento and  i.FK_paciente = cc.FK_entidade and cc.FK_genero = g.id_genero "
					   + "and year(a.data_registo) = ? and month(a.data_registo) between 7 and 9 and year(from_days((to_days(now()) - to_days(cc.data_nascimento)))) between ? and ?";
		}
		
		if(aux == 4){
			sql = "select count(a.id_alta) as 'pacientes_com_alta', COUNT(CASE WHEN a.motivo_alta = 'transferencia' THEN 0 END) AS 'por_transferencia',"
					   + "CAST(COUNT(CASE WHEN a.motivo_alta = 'transferencia' THEN 0 END) * 100 / count(a.id_alta) AS DECIMAL(10, 1)) as 'p_trasf_total',"
					   + "CAST(COUNT(CASE WHEN a.motivo_alta = 'transferencia' AND g.id_genero = 1 THEN 0 END) * 100 / count(a.id_alta) AS DECIMAL(10, 1)) as 'p_trans_m',"
					   + "CAST(COUNT(CASE WHEN a.motivo_alta = 'transferencia' AND g.id_genero = 2 THEN 0 END) * 100 / count(a.id_alta) AS DECIMAL(10, 1)) as 'p_trans_f',"
					   + "COUNT(CASE WHEN a.motivo_alta = 'morte' THEN 0 END) AS 'por_morte', CAST(COUNT(CASE WHEN a.motivo_alta = 'morte' THEN 0 END) * 100 / count(a.id_alta) AS DECIMAL(10, 1)) as 'p_morte_total',"
					   + "CAST(COUNT(CASE WHEN a.motivo_alta = 'morte' AND g.id_genero = 1 THEN 0 END) * 100 / count(a.id_alta) AS DECIMAL(10, 1)) as 'p_morte_m',"
					   + "CAST(COUNT(CASE WHEN a.motivo_alta = 'morte' AND g.id_genero = 2 THEN 0 END) * 100 / count(a.id_alta) AS DECIMAL(10, 1)) as 'p_morte_f',"
					   + "COUNT(CASE WHEN a.motivo_alta = 'melhoria' THEN 0 END) AS 'por_melhoria', CAST(COUNT(CASE WHEN a.motivo_alta = 'melhoria' THEN 0 END) * 100 / count(a.id_alta) AS DECIMAL(10, 1)) as 'p_melhoria_total',"
					   + "CAST(COUNT(CASE WHEN a.motivo_alta = 'melhoria' AND g.id_genero = 1 THEN 0 END) * 100 / count(a.id_alta) AS DECIMAL(10, 1)) as 'p_melhoria_m',"
					   + "CAST(COUNT(CASE WHEN a.motivo_alta = 'melhoria' AND g.id_genero = 2 THEN 0 END) * 100 / count(a.id_alta) AS DECIMAL(10, 1)) as 'p_melhoria_f'"
					   + "from tblaltamedica a, tblinternamento i, tblgenero g, tblcidadao cc where  i.id_internamento = a.FK_internamento and  i.FK_paciente = cc.FK_entidade and cc.FK_genero = g.id_genero "
					   + "and year(a.data_registo) = ? and month(a.data_registo) between 10 and 12 and year(from_days((to_days(now()) - to_days(cc.data_nascimento)))) between ? and ?";
		}
		
		if(aux == 5){
			sql = "select count(a.id_alta) as 'pacientes_com_alta', COUNT(CASE WHEN a.motivo_alta = 'transferencia' THEN 0 END) AS 'por_transferencia',"
					   + "CAST(COUNT(CASE WHEN a.motivo_alta = 'transferencia' THEN 0 END) * 100 / count(a.id_alta) AS DECIMAL(10, 1)) as 'p_trasf_total',"
					   + "CAST(COUNT(CASE WHEN a.motivo_alta = 'transferencia' AND g.id_genero = 1 THEN 0 END) * 100 / count(a.id_alta) AS DECIMAL(10, 1)) as 'p_trans_m',"
					   + "CAST(COUNT(CASE WHEN a.motivo_alta = 'transferencia' AND g.id_genero = 2 THEN 0 END) * 100 / count(a.id_alta) AS DECIMAL(10, 1)) as 'p_trans_f',"
					   + "COUNT(CASE WHEN a.motivo_alta = 'morte' THEN 0 END) AS 'por_morte', CAST(COUNT(CASE WHEN a.motivo_alta = 'morte' THEN 0 END) * 100 / count(a.id_alta) AS DECIMAL(10, 1)) as 'p_morte_total',"
					   + "CAST(COUNT(CASE WHEN a.motivo_alta = 'morte' AND g.id_genero = 1 THEN 0 END) * 100 / count(a.id_alta) AS DECIMAL(10, 1)) as 'p_morte_m',"
					   + "CAST(COUNT(CASE WHEN a.motivo_alta = 'morte' AND g.id_genero = 2 THEN 0 END) * 100 / count(a.id_alta) AS DECIMAL(10, 1)) as 'p_morte_f',"
					   + "COUNT(CASE WHEN a.motivo_alta = 'melhoria' THEN 0 END) AS 'por_melhoria', CAST(COUNT(CASE WHEN a.motivo_alta = 'melhoria' THEN 0 END) * 100 / count(a.id_alta) AS DECIMAL(10, 1)) as 'p_melhoria_total',"
					   + "CAST(COUNT(CASE WHEN a.motivo_alta = 'melhoria' AND g.id_genero = 1 THEN 0 END) * 100 / count(a.id_alta) AS DECIMAL(10, 1)) as 'p_melhoria_m',"
					   + "CAST(COUNT(CASE WHEN a.motivo_alta = 'melhoria' AND g.id_genero = 2 THEN 0 END) * 100 / count(a.id_alta) AS DECIMAL(10, 1)) as 'p_melhoria_f'"
					   + "from tblaltamedica a, tblinternamento i, tblgenero g, tblcidadao cc where  i.id_internamento = a.FK_internamento and  i.FK_paciente = cc.FK_entidade and cc.FK_genero = g.id_genero "
					   + "and year(a.data_registo) = ? and month(a.data_registo) between 1 and 6 and year(from_days((to_days(now()) - to_days(cc.data_nascimento)))) between ? and ?";
		}
		
		if(aux == 6){
			sql = "select count(a.id_alta) as 'pacientes_com_alta', COUNT(CASE WHEN a.motivo_alta = 'transferencia' THEN 0 END) AS 'por_transferencia',"
					   + "CAST(COUNT(CASE WHEN a.motivo_alta = 'transferencia' THEN 0 END) * 100 / count(a.id_alta) AS DECIMAL(10, 1)) as 'p_trasf_total',"
					   + "CAST(COUNT(CASE WHEN a.motivo_alta = 'transferencia' AND g.id_genero = 1 THEN 0 END) * 100 / count(a.id_alta) AS DECIMAL(10, 1)) as 'p_trans_m',"
					   + "CAST(COUNT(CASE WHEN a.motivo_alta = 'transferencia' AND g.id_genero = 2 THEN 0 END) * 100 / count(a.id_alta) AS DECIMAL(10, 1)) as 'p_trans_f',"
					   + "COUNT(CASE WHEN a.motivo_alta = 'morte' THEN 0 END) AS 'por_morte', CAST(COUNT(CASE WHEN a.motivo_alta = 'morte' THEN 0 END) * 100 / count(a.id_alta) AS DECIMAL(10, 1)) as 'p_morte_total',"
					   + "CAST(COUNT(CASE WHEN a.motivo_alta = 'morte' AND g.id_genero = 1 THEN 0 END) * 100 / count(a.id_alta) AS DECIMAL(10, 1)) as 'p_morte_m',"
					   + "CAST(COUNT(CASE WHEN a.motivo_alta = 'morte' AND g.id_genero = 2 THEN 0 END) * 100 / count(a.id_alta) AS DECIMAL(10, 1)) as 'p_morte_f',"
					   + "COUNT(CASE WHEN a.motivo_alta = 'melhoria' THEN 0 END) AS 'por_melhoria', CAST(COUNT(CASE WHEN a.motivo_alta = 'melhoria' THEN 0 END) * 100 / count(a.id_alta) AS DECIMAL(10, 1)) as 'p_melhoria_total',"
					   + "CAST(COUNT(CASE WHEN a.motivo_alta = 'melhoria' AND g.id_genero = 1 THEN 0 END) * 100 / count(a.id_alta) AS DECIMAL(10, 1)) as 'p_melhoria_m',"
					   + "CAST(COUNT(CASE WHEN a.motivo_alta = 'melhoria' AND g.id_genero = 2 THEN 0 END) * 100 / count(a.id_alta) AS DECIMAL(10, 1)) as 'p_melhoria_f'"
					   + "from tblaltamedica a, tblinternamento i, tblgenero g, tblcidadao cc where  i.id_internamento = a.FK_internamento and  i.FK_paciente = cc.FK_entidade and cc.FK_genero = g.id_genero "
					   + "and year(a.data_registo) = ? and month(a.data_registo) between 7 and 12 and year(from_days((to_days(now()) - to_days(cc.data_nascimento)))) between ? and ?";
		}
		
		List<AltaMedicaBean> lista = new ArrayList<AltaMedicaBean>();
		try {
			con = Conexao.getConexao();
			PreparedStatement ps = con.prepareStatement(sql);
			ps.setInt(1, ano);
			ps.setInt(2, idade1);
			ps.setInt(3, idade2);
			ResultSet rs = ps.executeQuery();
			while (rs.next()) {
				AltaMedicaBean p = new AltaMedicaBean();
				p.setQtd_paciente_alta(rs.getInt("pacientes_com_alta"));
				p.setQtd_alta_transf(rs.getInt("por_transferencia"));
				p.setPerc_alta_transf(rs.getDouble("p_trasf_total"));
				p.setPerc_alta_transf_msc(rs.getDouble("p_trans_m"));
				p.setPerc_alta_transf_fmn(rs.getDouble("p_trans_f"));
				
				p.setQtd_alta_morte(rs.getInt("por_morte"));
				p.setPerc_alta_morte(rs.getDouble("p_morte_total"));
				p.setPerc_alta_morte_msc(rs.getDouble("p_morte_m"));
				p.setPerc_alta_morte_fmn(rs.getDouble("p_morte_f"));
				
				p.setQtd_alta_melhoria(rs.getInt("por_melhoria"));
				p.setPerc_alta_melhoria(rs.getDouble("p_melhoria_total"));
				p.setPerc_alta_melhoria_msc(rs.getDouble("p_melhoria_m"));
				p.setPerc_alta_melhoria_fmn(rs.getDouble("p_melhoria_f"));
				
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



