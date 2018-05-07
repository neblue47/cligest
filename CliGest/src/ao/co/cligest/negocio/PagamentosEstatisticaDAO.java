package ao.co.cligest.negocio;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

import ao.co.cligest.entidades.Paciente;

public class PagamentosEstatisticaDAO {

	private Connection con;
	
	public List<Paciente> buscarHistoricoPagConsultasDiario()
	{
		String sql = "SELECT servico as 'servico', CAST((preco) AS DECIMAL(10,2)) as 'preco_unitario', "
				+ "COUNT(FK_servico) as 'qtd_cons_venda', SUM(qtd) as 'qtd_total_consultas', "
				+ "COUNT(CASE WHEN FK_metodo_de_pagameto = 1 OR FK_metodo_de_pagameto = 2 THEN 0 END) AS 'qtd_cons_pagas', "
				+ "COUNT(CASE WHEN FK_metodo_de_pagameto = 4 OR FK_metodo_de_pagameto = 5 THEN 0 END) AS 'qtd_cons_i', "
				+ "CAST(SUM(total) AS DECIMAL(10,2)) as 'total_pago' "
				+ "FROM vwrelpagamentoconsultas WHERE Data_da_facturacao = Curdate() GROUP BY servico ORDER BY servico";
		
		List<Paciente> lista = new ArrayList<Paciente>();
		try {
			con = Conexao.getConexao();
			PreparedStatement ps = con.prepareStatement(sql);
			ResultSet rs = ps.executeQuery();
			while(rs.next()){
				Paciente p = new Paciente();
				p.setServico(rs.getString("servico"));
				p.setPreco(rs.getDouble("preco_unitario"));
				p.setQuantidade(rs.getInt("qtd_cons_venda"));
				p.setQtd_sessao(rs.getInt("qtd_total_consultas"));
				p.setQdt_pagas(rs.getInt("qtd_cons_pagas"));
				p.setQdt_isenta(rs.getInt("qtd_cons_i"));
				p.setTotal_s(rs.getString("total_pago"));
				lista.add(p);
			}
			ps.close();
			con.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return lista;
	}
	
	public List<Paciente> buscarHistoricoPagConsultasEmAno(int ano)
	{
		String sql = "SELECT servico as 'servico', CAST((preco) AS DECIMAL(10,2)) as 'preco_unitario', "
				+ "COUNT(FK_servico) as 'qtd_cons_venda', SUM(qtd) as 'qtd_total_consultas', "
				+ "COUNT(CASE WHEN FK_metodo_de_pagameto = 1 OR FK_metodo_de_pagameto = 2 THEN 0 END) AS 'qtd_cons_pagas', "
				+ "COUNT(CASE WHEN FK_metodo_de_pagameto = 4 OR FK_metodo_de_pagameto = 5 THEN 0 END) AS 'qtd_cons_i', "
				+ "CAST(SUM(total) AS DECIMAL(10,2)) as 'total_pago' "
				+ "FROM vwrelpagamentoconsultas WHERE YEAR(Data_da_facturacao) = ? GROUP BY servico ORDER BY servico";
		List<Paciente> lista = new ArrayList<Paciente>();
		try {
			con = Conexao.getConexao();
			PreparedStatement ps = con.prepareStatement(sql);
			ps.setInt(1, ano);
			ResultSet rs = ps.executeQuery();
			while(rs.next()){
				Paciente p = new Paciente();
				p.setServico(rs.getString("servico"));
				p.setPreco(rs.getDouble("preco_unitario"));
				p.setQuantidade(rs.getInt("qtd_cons_venda"));
				p.setQtd_sessao(rs.getInt("qtd_total_consultas"));
				p.setQdt_pagas(rs.getInt("qtd_cons_pagas"));
				p.setQdt_isenta(rs.getInt("qtd_cons_i"));
				p.setTotal_s(rs.getString("total_pago"));
				lista.add(p);
			}
			ps.close();
			con.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return lista;
	}
	
	public List<Paciente> buscarHistoricoPagConsultasEmAnoMes(int ano,int mes)
	{
		String sql = "SELECT servico as 'servico', CAST((preco) AS DECIMAL(10,2)) as 'preco_unitario', "
				+ "COUNT(FK_servico) as 'qtd_cons_venda', SUM(qtd) as 'qtd_total_consultas', "
				+ "COUNT(CASE WHEN FK_metodo_de_pagameto = 1 OR FK_metodo_de_pagameto = 2 THEN 0 END) AS 'qtd_cons_pagas', "
				+ "COUNT(CASE WHEN FK_metodo_de_pagameto = 4 OR FK_metodo_de_pagameto = 5 THEN 0 END) AS 'qtd_cons_i', "
				+ "CAST(SUM(total) AS DECIMAL(10,2)) as 'total_pago' "
				+ "FROM vwrelpagamentoconsultas WHERE YEAR(Data_da_facturacao) = ? AND MONTH(Data_da_facturacao) = ? GROUP BY servico ORDER BY servico";
		List<Paciente> lista = new ArrayList<Paciente>();
		try {
			con = Conexao.getConexao();
			PreparedStatement ps = con.prepareStatement(sql);
			ps.setInt(1, ano);
			ps.setInt(2, mes);
			ResultSet rs = ps.executeQuery();
			while(rs.next()){
				Paciente p = new Paciente();
				p.setServico(rs.getString("servico"));
				p.setPreco(rs.getDouble("preco_unitario"));
				p.setQuantidade(rs.getInt("qtd_cons_venda"));
				p.setQtd_sessao(rs.getInt("qtd_total_consultas"));
				p.setQdt_pagas(rs.getInt("qtd_cons_pagas"));
				p.setQdt_isenta(rs.getInt("qtd_cons_i"));
				p.setTotal_s(rs.getString("total_pago"));
				lista.add(p);
			}
			ps.close();
			con.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return lista;
	}
	
	
	
	public List<Paciente> buscarHistoricoPagConsultasEmAnoMesDias(int ano,int mes,int dia1,int dia2)
	{
		String sql = "SELECT servico as 'servico', CAST((preco) AS DECIMAL(10,2)) as 'preco_unitario', "
				+ "COUNT(FK_servico) as 'qtd_cons_venda', SUM(qtd) as 'qtd_total_consultas', "
				+ "COUNT(CASE WHEN FK_metodo_de_pagameto = 1 OR FK_metodo_de_pagameto = 2 THEN 0 END) AS 'qtd_cons_pagas', "
				+ "COUNT(CASE WHEN FK_metodo_de_pagameto = 4 OR FK_metodo_de_pagameto = 5 THEN 0 END) AS 'qtd_cons_i', "
				+ "CAST(SUM(total) AS DECIMAL(10,2)) as 'total_pago' "
				+ "FROM vwrelpagamentoconsultas WHERE YEAR(Data_da_facturacao) = ? AND MONTH(Data_da_facturacao) = ? AND DAY(Data_da_facturacao) between ? AND ? "
				+ "GROUP BY servico ORDER BY servico";
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
				p.setServico(rs.getString("servico"));
				p.setPreco(rs.getDouble("preco_unitario"));
				p.setQuantidade(rs.getInt("qtd_cons_venda"));
				p.setQtd_sessao(rs.getInt("qtd_total_consultas"));
				p.setQdt_pagas(rs.getInt("qtd_cons_pagas"));
				p.setQdt_isenta(rs.getInt("qtd_cons_i"));
				p.setTotal_s(rs.getString("total_pago"));
				lista.add(p);
			}
			ps.close();
			con.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return lista;
	}
	
	public List<Paciente> buscarHistoricoPagConsultasEmAnoSetrimestre(int ano,int aux){
		String sql = "";
		if(aux == 1){
			sql = "SELECT servico as 'servico', CAST((preco) AS DECIMAL(10,2)) as 'preco_unitario', "
				+ "COUNT(FK_servico) as 'qtd_cons_venda', SUM(qtd) as 'qtd_total_consultas', "
				+ "COUNT(CASE WHEN FK_metodo_de_pagameto = 1 OR FK_metodo_de_pagameto = 2 THEN 0 END) AS 'qtd_cons_pagas', "
				+ "COUNT(CASE WHEN FK_metodo_de_pagameto = 4 OR FK_metodo_de_pagameto = 5 THEN 0 END) AS 'qtd_cons_i', "
				+ "CAST(SUM(total) AS DECIMAL(10,2)) as 'total_pago' "
				+ "FROM vwrelpagamentoconsultas WHERE YEAR(Data_da_facturacao) = ? AND MONTH(Data_da_facturacao) BETWEEN 1 AND 3 GROUP BY servico ORDER BY servico";
		}
		if(aux == 2){
			sql = "SELECT servico as 'servico', CAST((preco) AS DECIMAL(10,2)) as 'preco_unitario', "
				+ "COUNT(FK_servico) as 'qtd_cons_venda', SUM(qtd) as 'qtd_total_consultas', "
				+ "COUNT(CASE WHEN FK_metodo_de_pagameto = 1 OR FK_metodo_de_pagameto = 2 THEN 0 END) AS 'qtd_cons_pagas', "
				+ "COUNT(CASE WHEN FK_metodo_de_pagameto = 4 OR FK_metodo_de_pagameto = 5 THEN 0 END) AS 'qtd_cons_i', "
				+ "CAST(SUM(total) AS DECIMAL(10,2)) as 'total_pago' "
				+ "FROM vwrelpagamentoconsultas WHERE YEAR(Data_da_facturacao) = ? AND MONTH(Data_da_facturacao) BETWEEN 4 AND 6 GROUP BY servico ORDER BY servico";
		}
		if(aux == 3){
			sql = "SELECT servico as 'servico', CAST((preco) AS DECIMAL(10,2)) as 'preco_unitario', "
				+ "COUNT(FK_servico) as 'qtd_cons_venda', SUM(qtd) as 'qtd_total_consultas', "
				+ "COUNT(CASE WHEN FK_metodo_de_pagameto = 1 OR FK_metodo_de_pagameto = 2 THEN 0 END) AS 'qtd_cons_pagas', "
				+ "COUNT(CASE WHEN FK_metodo_de_pagameto = 4 OR FK_metodo_de_pagameto = 5 THEN 0 END) AS 'qtd_cons_i', "
				+ "CAST(SUM(total) AS DECIMAL(10,2)) as 'total_pago' "
				+ "FROM vwrelpagamentoconsultas WHERE YEAR(Data_da_facturacao) = ? AND MONTH(Data_da_facturacao) BETWEEN 7 AND 9 GROUP BY servico ORDER BY servico";
		}
		if(aux == 4){
			sql = "SELECT servico as 'servico', CAST((preco) AS DECIMAL(10,2)) as 'preco_unitario', "
				+ "COUNT(FK_servico) as 'qtd_cons_venda', SUM(qtd) as 'qtd_total_consultas', "
				+ "COUNT(CASE WHEN FK_metodo_de_pagameto = 1 OR FK_metodo_de_pagameto = 2 THEN 0 END) AS 'qtd_cons_pagas', "
				+ "COUNT(CASE WHEN FK_metodo_de_pagameto = 4 OR FK_metodo_de_pagameto = 5 THEN 0 END) AS 'qtd_cons_i', "
				+ "CAST(SUM(total) AS DECIMAL(10,2)) as 'total_pago' "
				+ "FROM vwrelpagamentoconsultas WHERE YEAR(Data_da_facturacao) = ? AND MONTH(Data_da_facturacao) BETWEEN 9 AND 12 GROUP BY servico ORDER BY servico";
		}
		if(aux == 5){
			sql = "SELECT servico as 'servico', CAST((preco) AS DECIMAL(10,2)) as 'preco_unitario', "
				+ "COUNT(FK_servico) as 'qtd_cons_venda', SUM(qtd) as 'qtd_total_consultas', "
				+ "COUNT(CASE WHEN FK_metodo_de_pagameto = 1 OR FK_metodo_de_pagameto = 2 THEN 0 END) AS 'qtd_cons_pagas', "
				+ "COUNT(CASE WHEN FK_metodo_de_pagameto = 4 OR FK_metodo_de_pagameto = 5 THEN 0 END) AS 'qtd_cons_i', "
				+ "CAST(SUM(total) AS DECIMAL(10,2)) as 'total_pago' "
				+ "FROM vwrelpagamentoconsultas WHERE YEAR(Data_da_facturacao) = ? AND MONTH(Data_da_facturacao) BETWEEN 1 AND 6 GROUP BY servico ORDER BY servico";
		}
		if(aux == 6)
		{
			sql = "SELECT servico as 'servico', CAST((preco) AS DECIMAL(10,2)) as 'preco_unitario', "
				+ "COUNT(FK_servico) as 'qtd_cons_venda', SUM(qtd) as 'qtd_total_consultas', "
				+ "COUNT(CASE WHEN FK_metodo_de_pagameto = 1 OR FK_metodo_de_pagameto = 2 THEN 0 END) AS 'qtd_cons_pagas', "
				+ "COUNT(CASE WHEN FK_metodo_de_pagameto = 4 OR FK_metodo_de_pagameto = 5 THEN 0 END) AS 'qtd_cons_i', "
				+ "CAST(SUM(total) AS DECIMAL(10,2)) as 'total_pago' "
				+ "FROM vwrelpagamentoconsultas WHERE YEAR(Data_da_facturacao) = ? AND MONTH(Data_da_facturacao) BETWEEN 7 AND 12 GROUP BY servico ORDER BY servico";
		}
		List<Paciente> lista = new ArrayList<Paciente>();
		try {
			con = Conexao.getConexao();
			PreparedStatement ps = con.prepareStatement(sql);
			ps.setInt(1, ano);
			ResultSet rs = ps.executeQuery();
			while(rs.next()){
				Paciente p = new Paciente();
				p.setServico(rs.getString("servico"));
				p.setPreco(rs.getDouble("preco_unitario"));
				p.setQuantidade(rs.getInt("qtd_cons_venda"));
				p.setQtd_sessao(rs.getInt("qtd_total_consultas"));
				p.setQdt_pagas(rs.getInt("qtd_cons_pagas"));
				p.setQdt_isenta(rs.getInt("qtd_cons_i"));
				p.setTotal_s(rs.getString("total_pago"));
				lista.add(p);
			}
			ps.close();
			con.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return lista;
	}
	
	public List<Paciente> buscarHistoricoPagExamesDiario()
	{
		String sql = "select analise_clinica, CAST((preco) AS DECIMAL(10,2)) as 'preco_unitario', COUNT(fk_servico) AS 'qtd_total', "
				+ "COUNT(CASE WHEN FK_metodo_de_pagameto = 1 OR FK_metodo_de_pagameto = 2 THEN 0 END) AS 'qtd_pagas', "
				+ "COUNT(CASE WHEN FK_metodo_de_pagameto = 4 OR FK_metodo_de_pagameto = 5 THEN 0 END) AS 'qtd_isentas', "
				+ "CAST(SUM(total) AS DECIMAL(10,2)) as 'total_pago' "
				+ "from vwrelpagamentoexame WHERE Data_da_facturacao = Curdate() "
				+ "GROUP BY analise_clinica ORDER BY analise_clinica asc";
		List<Paciente> lista = new ArrayList<Paciente>();
		try {
			con = Conexao.getConexao();
			PreparedStatement ps = con.prepareStatement(sql);
			ResultSet rs = ps.executeQuery();
			while(rs.next()){
				Paciente p = new Paciente();
				p.setServico(rs.getString("analise_clinica"));
				p.setPreco(rs.getDouble("preco_unitario"));
				p.setQuantidade(rs.getInt("qtd_total"));
				p.setQdt_pagas(rs.getInt("qtd_pagas"));
				p.setQdt_isenta(rs.getInt("qtd_isentas"));
				p.setTotal_s(rs.getString("total_pago"));
				lista.add(p);
			}
			ps.close();
			con.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return lista;
	}
	
	public List<Paciente> buscarHistoricoPagExamesEmAnos(int ano)
	{
		String sql = "select analise_clinica, CAST((preco) AS DECIMAL(10,2)) as 'preco_unitario', COUNT(fk_servico) AS 'qtd_total', "
				+ "COUNT(CASE WHEN FK_metodo_de_pagameto = 1 OR FK_metodo_de_pagameto = 2 THEN 0 END) AS 'qtd_pagas', "
				+ "COUNT(CASE WHEN FK_metodo_de_pagameto = 4 OR FK_metodo_de_pagameto = 5 THEN 0 END) AS 'qtd_isentas', "
				+ "CAST(SUM(total) AS DECIMAL(10,2)) as 'total_pago' "
				+ "from vwrelpagamentoexame WHERE YEAR(Data_da_facturacao) = ? "
				+ "GROUP BY analise_clinica ORDER BY analise_clinica asc";
		List<Paciente> lista = new ArrayList<Paciente>();
		try {
			con = Conexao.getConexao();
			PreparedStatement ps = con.prepareStatement(sql);
			ps.setInt(1, ano);
			ResultSet rs = ps.executeQuery();
			while(rs.next()){
				Paciente p = new Paciente();
				p.setServico(rs.getString("analise_clinica"));
				p.setPreco(rs.getDouble("preco_unitario"));
				p.setQuantidade(rs.getInt("qtd_total"));
				p.setQdt_pagas(rs.getInt("qtd_pagas"));
				p.setQdt_isenta(rs.getInt("qtd_isentas"));
				p.setTotal_s(rs.getString("total_pago"));
				lista.add(p);
			}
			ps.close();
			con.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return lista;
	}
	
	public List<Paciente> buscarHistoricoPagExamesEmAnosMes(int ano,int mes){
		String sql = "select analise_clinica, CAST((preco) AS DECIMAL(10,2)) as 'preco_unitario', COUNT(fk_servico) AS 'qtd_total', "
				+ "COUNT(CASE WHEN FK_metodo_de_pagameto = 1 OR FK_metodo_de_pagameto = 2 THEN 0 END) AS 'qtd_pagas', "
				+ "COUNT(CASE WHEN FK_metodo_de_pagameto = 4 OR FK_metodo_de_pagameto = 5 THEN 0 END) AS 'qtd_isentas', "
				+ "CAST(SUM(total) AS DECIMAL(10,2)) as 'total_pago' "
				+ "from vwrelpagamentoexame WHERE YEAR(Data_da_facturacao) = ? AND MONTH(Data_da_facturacao) = ? "
				+ "GROUP BY analise_clinica ORDER BY analise_clinica asc";
		List<Paciente> lista = new ArrayList<Paciente>();
		try {
			con = Conexao.getConexao();
			PreparedStatement ps = con.prepareStatement(sql);
			ps.setInt(1, ano);
			ps.setInt(2, mes);
			ResultSet rs = ps.executeQuery();
			while(rs.next()){
				Paciente p = new Paciente();
				p.setServico(rs.getString("analise_clinica"));
				p.setPreco(rs.getDouble("preco_unitario"));
				p.setQuantidade(rs.getInt("qtd_total"));
				p.setQdt_pagas(rs.getInt("qtd_pagas"));
				p.setQdt_isenta(rs.getInt("qtd_isentas"));
				p.setTotal_s(rs.getString("total_pago"));
				lista.add(p);
			}
			ps.close();
			con.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return lista;
	}
	
	public List<Paciente> buscarHistoricoPagExamesEmAnosMesDias(int ano,int mes,int dia1,int dia2){
		String sql = "select analise_clinica, CAST((preco) AS DECIMAL(10,2)) as 'preco_unitario', COUNT(fk_servico) AS 'qtd_total', "
				+ "COUNT(CASE WHEN FK_metodo_de_pagameto = 1 OR FK_metodo_de_pagameto = 2 THEN 0 END) AS 'qtd_pagas', "
				+ "COUNT(CASE WHEN FK_metodo_de_pagameto = 4 OR FK_metodo_de_pagameto = 5 THEN 0 END) AS 'qtd_isentas', "
				+ "CAST(SUM(total) AS DECIMAL(10,2)) as 'total_pago' "
				+ "from vwrelpagamentoexame WHERE YEAR(Data_da_facturacao) = ? AND MONTH(Data_da_facturacao) = ? AND DAY(Data_da_facturacao) between ? AND ? "
				+ "GROUP BY analise_clinica ORDER BY analise_clinica asc";
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
				p.setServico(rs.getString("analise_clinica"));
				p.setPreco(rs.getDouble("preco_unitario"));
				p.setQuantidade(rs.getInt("qtd_total"));
				p.setQdt_pagas(rs.getInt("qtd_pagas"));
				p.setQdt_isenta(rs.getInt("qtd_isentas"));
				p.setTotal_s(rs.getString("total_pago"));
				lista.add(p);
			}
			ps.close();
			con.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return lista;
	}
	

	public List<Paciente> buscarHistoricoPagExamesEmAnosSeTrimestre(int ano,int aux){
		String sql ="";
		if(aux == 1)
		{
			sql = "select analise_clinica, CAST((preco) AS DECIMAL(10,2)) as 'preco_unitario', COUNT(fk_servico) AS 'qtd_total', "
				+ "COUNT(CASE WHEN FK_metodo_de_pagameto = 1 OR FK_metodo_de_pagameto = 2 THEN 0 END) AS 'qtd_pagas', "
				+ "COUNT(CASE WHEN FK_metodo_de_pagameto = 4 OR FK_metodo_de_pagameto = 5 THEN 0 END) AS 'qtd_isentas', "
				+ "CAST(SUM(total) AS DECIMAL(10,2)) as 'total_pago' "
				+ "from vwrelpagamentoexame WHERE YEAR(Data_da_facturacao) = ? AND MONTH(Data_da_facturacao) BETWEEN 1 AND 3 "
				+ "GROUP BY analise_clinica ORDER BY analise_clinica asc";
		}
		if(aux == 2)
		{
			sql = "select analise_clinica, CAST((preco) AS DECIMAL(10,2)) as 'preco_unitario', COUNT(fk_servico) AS 'qtd_total', "
				+ "COUNT(CASE WHEN FK_metodo_de_pagameto = 1 OR FK_metodo_de_pagameto = 2 THEN 0 END) AS 'qtd_pagas', "
				+ "COUNT(CASE WHEN FK_metodo_de_pagameto = 4 OR FK_metodo_de_pagameto = 5 THEN 0 END) AS 'qtd_isentas', "
				+ "CAST(SUM(total) AS DECIMAL(10,2)) as 'total_pago' "
				+ "from vwrelpagamentoexame WHERE YEAR(Data_da_facturacao) = ? AND MONTH(Data_da_facturacao) BETWEEN 4 AND 6 "
				+ "GROUP BY analise_clinica ORDER BY analise_clinica asc";
		}
		if(aux == 3)
		{
			sql = "select analise_clinica, CAST((preco) AS DECIMAL(10,2)) as 'preco_unitario', COUNT(fk_servico) AS 'qtd_total', "
				+ "COUNT(CASE WHEN FK_metodo_de_pagameto = 1 OR FK_metodo_de_pagameto = 2 THEN 0 END) AS 'qtd_pagas', "
				+ "COUNT(CASE WHEN FK_metodo_de_pagameto = 4 OR FK_metodo_de_pagameto = 5 THEN 0 END) AS 'qtd_isentas', "
				+ "CAST(SUM(total) AS DECIMAL(10,2)) as 'total_pago' "
				+ "from vwrelpagamentoexame WHERE YEAR(Data_da_facturacao) = ? AND MONTH(Data_da_facturacao) BETWEEN 7 AND 9 "
				+ "GROUP BY analise_clinica ORDER BY analise_clinica asc";
		}
		if(aux == 4)
		{
			sql = "select analise_clinica, CAST((preco) AS DECIMAL(10,2)) as 'preco_unitario', COUNT(fk_servico) AS 'qtd_total', "
				+ "COUNT(CASE WHEN FK_metodo_de_pagameto = 1 OR FK_metodo_de_pagameto = 2 THEN 0 END) AS 'qtd_pagas', "
				+ "COUNT(CASE WHEN FK_metodo_de_pagameto = 4 OR FK_metodo_de_pagameto = 5 THEN 0 END) AS 'qtd_isentas', "
				+ "CAST(SUM(total) AS DECIMAL(10,2)) as 'total_pago' "
				+ "from vwrelpagamentoexame WHERE YEAR(Data_da_facturacao) = ? AND MONTH(Data_da_facturacao) BETWEEN 10 AND 12 "
				+ "GROUP BY analise_clinica ORDER BY analise_clinica asc";
		}
		if(aux == 5)
		{
			sql = "select analise_clinica, CAST((preco) AS DECIMAL(10,2)) as 'preco_unitario', COUNT(fk_servico) AS 'qtd_total', "
				+ "COUNT(CASE WHEN FK_metodo_de_pagameto = 1 OR FK_metodo_de_pagameto = 2 THEN 0 END) AS 'qtd_pagas', "
				+ "COUNT(CASE WHEN FK_metodo_de_pagameto = 4 OR FK_metodo_de_pagameto = 5 THEN 0 END) AS 'qtd_isentas', "
				+ "CAST(SUM(total) AS DECIMAL(10,2)) as 'total_pago' "
				+ "from vwrelpagamentoexame WHERE YEAR(Data_da_facturacao) = ? AND MONTH(Data_da_facturacao) BETWEEN 1 AND 6 "
				+ "GROUP BY analise_clinica ORDER BY analise_clinica asc";
		}
		if(aux == 6)
		{
			sql = "select analise_clinica, CAST((preco) AS DECIMAL(10,2)) as 'preco_unitario', COUNT(fk_servico) AS 'qtd_total', "
				+ "COUNT(CASE WHEN FK_metodo_de_pagameto = 1 OR FK_metodo_de_pagameto = 2 THEN 0 END) AS 'qtd_pagas', "
				+ "COUNT(CASE WHEN FK_metodo_de_pagameto = 4 OR FK_metodo_de_pagameto = 5 THEN 0 END) AS 'qtd_isentas', "
				+ "CAST(SUM(total) AS DECIMAL(10,2)) as 'total_pago' "
				+ "from vwrelpagamentoexame WHERE YEAR(Data_da_facturacao) = ? AND MONTH(Data_da_facturacao) BETWEEN 7 AND 12 "
				+ "GROUP BY analise_clinica ORDER BY analise_clinica asc";
		}
		List<Paciente> lista = new ArrayList<Paciente>();
		try {
			con = Conexao.getConexao();
			PreparedStatement ps = con.prepareStatement(sql);
			ps.setInt(1, ano);
			 
			ResultSet rs = ps.executeQuery();
			while(rs.next()){
				Paciente p = new Paciente();
				p.setServico(rs.getString("analise_clinica"));
				p.setPreco(rs.getDouble("preco_unitario"));
				p.setQuantidade(rs.getInt("qtd_total"));
				p.setQdt_pagas(rs.getInt("qtd_pagas"));
				p.setQdt_isenta(rs.getInt("qtd_isentas"));
				p.setTotal_s(rs.getString("total_pago"));
				lista.add(p);
			}
			ps.close();
			con.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return lista;
	}
	
	public List<Paciente> buscarHistoricoPagServGeraisDiario()
	{
		String sql = "SELECT servico as 'servico', CAST((preco) AS DECIMAL(10,2)) as 'preco_unitario', COUNT(fk_servico) as 'qtd_ser_venda', SUM(qtd) as 'qtd_total_sessoes', "
				+ "COUNT(CASE WHEN FK_metodo_de_pagameto = 1 OR FK_metodo_de_pagameto = 2 THEN 0 END) AS 'qtd_sessoes_pagas', "
				+ "COUNT(CASE WHEN FK_metodo_de_pagameto = 4 OR FK_metodo_de_pagameto = 5 THEN 0 END) AS 'qtd_sessoes_i', "
				+ "CAST(SUM(total) AS DECIMAL(10,2)) as 'total_pago' "
				+ "FROM vwrelpagamentoservicosgerais WHERE Data_da_facturacao = Curdate() GROUP BY servico ORDER BY servico asc";
		List<Paciente> lista = new ArrayList<Paciente>();
		try {
			con = Conexao.getConexao();
			PreparedStatement ps = con.prepareStatement(sql);
			ResultSet rs = ps.executeQuery();
			while(rs.next()){
				Paciente p = new Paciente();
				p.setServico(rs.getString("servico"));
				p.setPreco(rs.getDouble("preco_unitario"));
				p.setQuantidade(rs.getInt("qtd_ser_venda"));
				p.setQtd_sessao(rs.getInt("qtd_total_sessoes"));
				p.setQdt_pagas(rs.getInt("qtd_sessoes_pagas"));
				p.setQdt_isenta(rs.getInt("qtd_sessoes_i"));
				p.setTotal_s(rs.getString("total_pago"));
				lista.add(p);
			}
			ps.close();
			con.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return lista;
	}
	
	public List<Paciente> buscarHistoricoPagServGeraisEmAnos(int ano)
	{
		String sql = "SELECT servico as 'servico', CAST((preco) AS DECIMAL(10,2)) as 'preco_unitario', COUNT(fk_servico) as 'qtd_ser_venda', SUM(qtd) as 'qtd_total_sessoes', "
				+ "COUNT(CASE WHEN FK_metodo_de_pagameto = 1 OR FK_metodo_de_pagameto = 2 THEN 0 END) AS 'qtd_sessoes_pagas', "
				+ "COUNT(CASE WHEN FK_metodo_de_pagameto = 4 OR FK_metodo_de_pagameto = 5 THEN 0 END) AS 'qtd_sessoes_i', "
				+ "CAST(SUM(total) AS DECIMAL(10,2)) as 'total_pago' "
				+ "FROM vwrelpagamentoservicosgerais WHERE YEAR(Data_da_facturacao) = ? GROUP BY servico ORDER BY servico asc";
		List<Paciente> lista = new ArrayList<Paciente>();
		try {
			con = Conexao.getConexao();
			PreparedStatement ps = con.prepareStatement(sql);
			ps.setInt(1, ano);
			ResultSet rs = ps.executeQuery();
			while(rs.next()){
				Paciente p = new Paciente();
				p.setServico(rs.getString("servico"));
				p.setPreco(rs.getDouble("preco_unitario"));
				p.setQuantidade(rs.getInt("qtd_ser_venda"));
				p.setQtd_sessao(rs.getInt("qtd_total_sessoes"));
				p.setQdt_pagas(rs.getInt("qtd_sessoes_pagas"));
				p.setQdt_isenta(rs.getInt("qtd_sessoes_i"));
				p.setTotal_s(rs.getString("total_pago"));
				lista.add(p);
			}
			ps.close();
			con.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return lista;
	}
	
	public List<Paciente> buscarHistoricoPagServGeraisEmAnosMes(int ano,int mes)
	{
		String sql = "SELECT servico as 'servico', CAST((preco) AS DECIMAL(10,2)) as 'preco_unitario', COUNT(fk_servico) as 'qtd_ser_venda', SUM(qtd) as 'qtd_total_sessoes', "
				+ "COUNT(CASE WHEN FK_metodo_de_pagameto = 1 OR FK_metodo_de_pagameto = 2 THEN 0 END) AS 'qtd_sessoes_pagas', "
				+ "COUNT(CASE WHEN FK_metodo_de_pagameto = 4 OR FK_metodo_de_pagameto = 5 THEN 0 END) AS 'qtd_sessoes_i', "
				+ "CAST(SUM(total) AS DECIMAL(10,2)) as 'total_pago' "
				+ "FROM vwrelpagamentoservicosgerais WHERE YEAR(Data_da_facturacao) = ? AND MONTH(Data_da_facturacao) = ? GROUP BY servico ORDER BY servico asc";
		List<Paciente> lista = new ArrayList<Paciente>();
		try {
			con = Conexao.getConexao();
			PreparedStatement ps = con.prepareStatement(sql);
			ps.setInt(1, ano);
			ps.setInt(2, mes);
			ResultSet rs = ps.executeQuery();
			while(rs.next()){
				Paciente p = new Paciente();
				p.setServico(rs.getString("servico"));
				p.setPreco(rs.getDouble("preco_unitario"));
				p.setQuantidade(rs.getInt("qtd_ser_venda"));
				p.setQtd_sessao(rs.getInt("qtd_total_sessoes"));
				p.setQdt_pagas(rs.getInt("qtd_sessoes_pagas"));
				p.setQdt_isenta(rs.getInt("qtd_sessoes_i"));
				p.setTotal_s(rs.getString("total_pago"));
				lista.add(p);
			}
			ps.close();
			con.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return lista;
	}
	
	public List<Paciente> buscarHistoricoPagServGeraisEmAnosMesDias(int ano,int mes,int dia1,int dia2)
	{
		String sql = "SELECT servico as 'servico', CAST((preco) AS DECIMAL(10,2)) as 'preco_unitario', COUNT(fk_servico) as 'qtd_ser_venda', SUM(qtd) as 'qtd_total_sessoes', "
				+ "COUNT(CASE WHEN FK_metodo_de_pagameto = 1 OR FK_metodo_de_pagameto = 2 THEN 0 END) AS 'qtd_sessoes_pagas', "
				+ "COUNT(CASE WHEN FK_metodo_de_pagameto = 4 OR FK_metodo_de_pagameto = 5 THEN 0 END) AS 'qtd_sessoes_i', "
				+ "CAST(SUM(total) AS DECIMAL(10,2)) as 'total_pago' "
				+ "FROM vwrelpagamentoservicosgerais WHERE YEAR(Data_da_facturacao) = ? AND MONTH(Data_da_facturacao) = ? AND DAY(Data_da_facturacao) between ? AND ? GROUP BY servico ORDER BY servico asc";
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
				p.setServico(rs.getString("servico"));
				p.setPreco(rs.getDouble("preco_unitario"));
				p.setQuantidade(rs.getInt("qtd_ser_venda"));
				p.setQtd_sessao(rs.getInt("qtd_total_sessoes"));
				p.setQdt_pagas(rs.getInt("qtd_sessoes_pagas"));
				p.setQdt_isenta(rs.getInt("qtd_sessoes_i"));
				p.setTotal_s(rs.getString("total_pago"));
				lista.add(p);
			}
			ps.close();
			con.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return lista;
	}
	
	public List<Paciente> buscarHistoricoPagServGeraisEmAnosSeTrimestre(int ano,int aux)
	{
		String sql = "";
		
		if(aux == 1)
		{
			sql = "SELECT servico as 'servico', CAST((preco) AS DECIMAL(10,2)) as 'preco_unitario', COUNT(fk_servico) as 'qtd_ser_venda', SUM(qtd) as 'qtd_total_sessoes', "
				+ "COUNT(CASE WHEN FK_metodo_de_pagameto = 1 OR FK_metodo_de_pagameto = 2 THEN 0 END) AS 'qtd_sessoes_pagas', "
				+ "COUNT(CASE WHEN FK_metodo_de_pagameto = 4 OR FK_metodo_de_pagameto = 5 THEN 0 END) AS 'qtd_sessoes_i', "
				+ "CAST(SUM(total) AS DECIMAL(10,2)) as 'total_pago' "
				+ "FROM vwrelpagamentoservicosgerais WHERE YEAR(Data_da_facturacao) = ? AND MONTH(Data_da_facturacao) BETWEEN 1 AND 3 GROUP BY servico ORDER BY servico asc";
		}
		if(aux == 2)
		{
			sql = "SELECT servico as 'servico', CAST((preco) AS DECIMAL(10,2)) as 'preco_unitario', COUNT(fk_servico) as 'qtd_ser_venda', SUM(qtd) as 'qtd_total_sessoes', "
				+ "COUNT(CASE WHEN FK_metodo_de_pagameto = 1 OR FK_metodo_de_pagameto = 2 THEN 0 END) AS 'qtd_sessoes_pagas', "
				+ "COUNT(CASE WHEN FK_metodo_de_pagameto = 4 OR FK_metodo_de_pagameto = 5 THEN 0 END) AS 'qtd_sessoes_i', "
				+ "CAST(SUM(total) AS DECIMAL(10,2)) as 'total_pago' "
				+ "FROM vwrelpagamentoservicosgerais WHERE YEAR(Data_da_facturacao) = ? AND MONTH(Data_da_facturacao) BETWEEN 4 AND 6 GROUP BY servico ORDER BY servico asc";
		}
		if(aux == 3)
		{
			sql = "SELECT servico as 'servico', CAST((preco) AS DECIMAL(10,2)) as 'preco_unitario', COUNT(fk_servico) as 'qtd_ser_venda', SUM(qtd) as 'qtd_total_sessoes', "
				+ "COUNT(CASE WHEN FK_metodo_de_pagameto = 1 OR FK_metodo_de_pagameto = 2 THEN 0 END) AS 'qtd_sessoes_pagas', "
				+ "COUNT(CASE WHEN FK_metodo_de_pagameto = 4 OR FK_metodo_de_pagameto = 5 THEN 0 END) AS 'qtd_sessoes_i', "
				+ "CAST(SUM(total) AS DECIMAL(10,2)) as 'total_pago' "
				+ "FROM vwrelpagamentoservicosgerais WHERE YEAR(Data_da_facturacao) = ? AND MONTH(Data_da_facturacao) BETWEEN 7 AND 9 GROUP BY servico ORDER BY servico asc";
		}
		if(aux == 4)
		{
			sql = "SELECT servico as 'servico', CAST((preco) AS DECIMAL(10,2)) as 'preco_unitario', COUNT(fk_servico) as 'qtd_ser_venda', SUM(qtd) as 'qtd_total_sessoes', "
				+ "COUNT(CASE WHEN FK_metodo_de_pagameto = 1 OR FK_metodo_de_pagameto = 2 THEN 0 END) AS 'qtd_sessoes_pagas', "
				+ "COUNT(CASE WHEN FK_metodo_de_pagameto = 4 OR FK_metodo_de_pagameto = 5 THEN 0 END) AS 'qtd_sessoes_i', "
				+ "CAST(SUM(total) AS DECIMAL(10,2)) as 'total_pago' "
				+ "FROM vwrelpagamentoservicosgerais WHERE YEAR(Data_da_facturacao) = ? AND MONTH(Data_da_facturacao) BETWEEN 10 AND 12 GROUP BY servico ORDER BY servico asc";
		}
		if(aux == 5)
		{
			sql = "SELECT servico as 'servico', CAST((preco) AS DECIMAL(10,2)) as 'preco_unitario', COUNT(fk_servico) as 'qtd_ser_venda', SUM(qtd) as 'qtd_total_sessoes', "
				+ "COUNT(CASE WHEN FK_metodo_de_pagameto = 1 OR FK_metodo_de_pagameto = 2 THEN 0 END) AS 'qtd_sessoes_pagas', "
				+ "COUNT(CASE WHEN FK_metodo_de_pagameto = 4 OR FK_metodo_de_pagameto = 5 THEN 0 END) AS 'qtd_sessoes_i', "
				+ "CAST(SUM(total) AS DECIMAL(10,2)) as 'total_pago' "
				+ "FROM vwrelpagamentoservicosgerais WHERE YEAR(Data_da_facturacao) = ? AND MONTH(Data_da_facturacao) BETWEEN 1 AND 6 GROUP BY servico ORDER BY servico asc";
		}
		if(aux == 6)
		{
			sql = "SELECT servico as 'servico', CAST((preco) AS DECIMAL(10,2)) as 'preco_unitario', COUNT(fk_servico) as 'qtd_ser_venda', SUM(qtd) as 'qtd_total_sessoes', "
				+ "COUNT(CASE WHEN FK_metodo_de_pagameto = 1 OR FK_metodo_de_pagameto = 2 THEN 0 END) AS 'qtd_sessoes_pagas', "
				+ "COUNT(CASE WHEN FK_metodo_de_pagameto = 4 OR FK_metodo_de_pagameto = 5 THEN 0 END) AS 'qtd_sessoes_i', "
				+ "CAST(SUM(total) AS DECIMAL(10,2)) as 'total_pago' "
				+ "FROM vwrelpagamentoservicosgerais WHERE YEAR(Data_da_facturacao) = ? AND MONTH(Data_da_facturacao) BETWEEN 7 AND 12 GROUP BY servico ORDER BY servico asc";
		}
		List<Paciente> lista = new ArrayList<Paciente>();
		try {
			con = Conexao.getConexao();
			PreparedStatement ps = con.prepareStatement(sql);
			ps.setInt(1, ano);
			 
			ResultSet rs = ps.executeQuery();
			while(rs.next()){
				Paciente p = new Paciente();
				p.setServico(rs.getString("servico"));
				p.setPreco(rs.getDouble("preco_unitario"));
				p.setQuantidade(rs.getInt("qtd_ser_venda"));
				p.setQtd_sessao(rs.getInt("qtd_total_sessoes"));
				p.setQdt_pagas(rs.getInt("qtd_sessoes_pagas"));
				p.setQdt_isenta(rs.getInt("qtd_sessoes_i"));
				p.setTotal_s(rs.getString("total_pago"));
				lista.add(p);
			}
			ps.close();
			con.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return lista;
	}
	
	public List<Paciente> buscarHistoricoPagProdutosDiario()
	{
		String sql = "SELECT produto as 'servico', CAST((preco) AS DECIMAL(10,2)) as 'preco_unitario', COUNT(id_produto) as 'qtd_prod_venda', SUM(qtd) as 'qtd_total_produtos', "
				+ "COUNT(CASE WHEN FK_metodo_de_pagameto = 1 OR FK_metodo_de_pagameto = 2 THEN 0 END) AS 'qtd_produto_pagas', "
				+ "COUNT(CASE WHEN FK_metodo_de_pagameto = 4 OR FK_metodo_de_pagameto = 5 THEN 0 END) AS 'qtd_produtos_i', "
				+ "CAST(SUM(total) AS DECIMAL(10,2)) as 'total_pago' "
				+ "FROM vwrelpagamentoprodutos WHERE Data_da_facturacao = Curdate() GROUP BY produto ORDER BY produto";
		List<Paciente> lista = new ArrayList<Paciente>();
		try {
			con = Conexao.getConexao();
			PreparedStatement ps = con.prepareStatement(sql);
			ResultSet rs = ps.executeQuery();
			while(rs.next()){
				Paciente p = new Paciente();
				p.setServico(rs.getString("servico"));
				p.setPreco(rs.getDouble("preco_unitario"));
				p.setQuantidade(rs.getInt("qtd_prod_venda"));
				p.setQtd_sessao(rs.getInt("qtd_total_produtos"));
				p.setQdt_pagas(rs.getInt("qtd_produto_pagas"));
				p.setQdt_isenta(rs.getInt("qtd_produtos_i"));
				p.setTotal_s(rs.getString("total_pago"));
				lista.add(p);
			}
			ps.close();
			con.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return lista;
	}
	
	
	public List<Paciente> buscarHistoricoPagProdutosEmAno(int ano)
	{
		String sql = "SELECT produto as 'servico', CAST((preco) AS DECIMAL(10,2)) as 'preco_unitario', COUNT(id_produto) as 'qtd_prod_venda', SUM(qtd) as 'qtd_total_produtos', "
				+ "COUNT(CASE WHEN FK_metodo_de_pagameto = 1 OR FK_metodo_de_pagameto = 2 THEN 0 END) AS 'qtd_produto_pagas', "
				+ "COUNT(CASE WHEN FK_metodo_de_pagameto = 4 OR FK_metodo_de_pagameto = 5 THEN 0 END) AS 'qtd_produtos_i', "
				+ "CAST(SUM(total) AS DECIMAL(10,2)) as 'total_pago' "
				+ "FROM vwrelpagamentoprodutos WHERE YEAR(Data_da_facturacao) = ? GROUP BY produto ORDER BY produto";
		List<Paciente> lista = new ArrayList<Paciente>();
		try {
			con = Conexao.getConexao();
			PreparedStatement ps = con.prepareStatement(sql);
			ps.setInt(1, ano);
			ResultSet rs = ps.executeQuery();
			while(rs.next()){
				Paciente p = new Paciente();
				p.setServico(rs.getString("servico"));
				p.setPreco(rs.getDouble("preco_unitario"));
				p.setQuantidade(rs.getInt("qtd_prod_venda"));
				p.setQtd_sessao(rs.getInt("qtd_total_produtos"));
				p.setQdt_pagas(rs.getInt("qtd_produto_pagas"));
				p.setQdt_isenta(rs.getInt("qtd_produtos_i"));
				p.setTotal_s(rs.getString("total_pago"));
				lista.add(p);
			}
			ps.close();
			con.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return lista;
	}

	public List<Paciente> buscarHistoricoPagProdutosEmAnoMes(int ano,int mes){
		String sql = "SELECT produto as 'servico', CAST((preco) AS DECIMAL(10,2)) as 'preco_unitario', COUNT(id_produto) as 'qtd_prod_venda', SUM(qtd) as 'qtd_total_produtos', "
				+ "COUNT(CASE WHEN FK_metodo_de_pagameto = 1 OR FK_metodo_de_pagameto = 2 THEN 0 END) AS 'qtd_produto_pagas', "
				+ "COUNT(CASE WHEN FK_metodo_de_pagameto = 4 OR FK_metodo_de_pagameto = 5 THEN 0 END) AS 'qtd_produtos_i', "
				+ "CAST(SUM(total) AS DECIMAL(10,2)) as 'total_pago' "
				+ "FROM vwrelpagamentoprodutos WHERE YEAR(Data_da_facturacao) = ? AND MONTH(Data_da_facturacao) = ? GROUP BY produto ORDER BY produto";
		List<Paciente> lista = new ArrayList<Paciente>();
		try {
			con = Conexao.getConexao();
			PreparedStatement ps = con.prepareStatement(sql);
			ps.setInt(1, ano);
			ps.setInt(2, mes);
			ResultSet rs = ps.executeQuery();
			while(rs.next()){
				Paciente p = new Paciente();
				p.setServico(rs.getString("servico"));
				p.setPreco(rs.getDouble("preco_unitario"));
				p.setQuantidade(rs.getInt("qtd_prod_venda"));
				p.setQtd_sessao(rs.getInt("qtd_total_produtos"));
				p.setQdt_pagas(rs.getInt("qtd_produto_pagas"));
				p.setQdt_isenta(rs.getInt("qtd_produtos_i"));
				p.setTotal_s(rs.getString("total_pago"));
				lista.add(p);
			}
			ps.close();
			con.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return lista;
	}
	
	public List<Paciente> buscarHistoricoPagProdutosEmAnoMesDias(int ano,int mes,int dia1,int dia2)
	{
		String sql = "SELECT produto as 'servico', CAST((preco) AS DECIMAL(10,2)) as 'preco_unitario', COUNT(id_produto) as 'qtd_prod_venda', SUM(qtd) as 'qtd_total_produtos', "
				+ "COUNT(CASE WHEN FK_metodo_de_pagameto = 1 OR FK_metodo_de_pagameto = 2 THEN 0 END) AS 'qtd_produto_pagas', "
				+ "COUNT(CASE WHEN FK_metodo_de_pagameto = 4 OR FK_metodo_de_pagameto = 5 THEN 0 END) AS 'qtd_produtos_i', "
				+ "CAST(SUM(total) AS DECIMAL(10,2)) as 'total_pago' "
				+ "FROM vwrelpagamentoprodutos WHERE YEAR(Data_da_facturacao) = ? AND MONTH(Data_da_facturacao) = ? AND DAY(Data_da_facturacao) between ? AND ? GROUP BY produto ORDER BY produto asc";
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
				p.setServico(rs.getString("servico"));
				p.setPreco(rs.getDouble("preco_unitario"));
				p.setQuantidade(rs.getInt("qtd_prod_venda"));
				p.setQtd_sessao(rs.getInt("qtd_total_produtos"));
				p.setQdt_pagas(rs.getInt("qtd_produto_pagas"));
				p.setQdt_isenta(rs.getInt("qtd_produtos_i"));
				p.setTotal_s(rs.getString("total_pago"));
				lista.add(p);
			}
			ps.close();
			con.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return lista;
	}
	
	public List<Paciente> buscarHistoricoPagProdutosEmAnoSeTrimestre(int ano,int aux){
		String sql = "";
		if(aux == 1){
			sql = "SELECT produto as 'servico', CAST((preco) AS DECIMAL(10,2)) as 'preco_unitario', COUNT(id_produto) as 'qtd_prod_venda', SUM(qtd) as 'qtd_total_produtos', "
				+ "COUNT(CASE WHEN FK_metodo_de_pagameto = 1 OR FK_metodo_de_pagameto = 2 THEN 0 END) AS 'qtd_produto_pagas', "
				+ "COUNT(CASE WHEN FK_metodo_de_pagameto = 4 OR FK_metodo_de_pagameto = 5 THEN 0 END) AS 'qtd_produtos_i', "
				+ "CAST(SUM(total) AS DECIMAL(10,2)) as 'total_pago' "
				+ "FROM vwrelpagamentoprodutos WHERE YEAR(Data_da_facturacao) = ? AND MONTH(Data_da_facturacao) BETWEEN 1 AND 3 GROUP BY produto ORDER BY produto";
		}
		if(aux == 2){
			sql = "SELECT produto as 'servico', CAST((preco) AS DECIMAL(10,2)) as 'preco_unitario', COUNT(id_produto) as 'qtd_prod_venda', SUM(qtd) as 'qtd_total_produtos', "
				+ "COUNT(CASE WHEN FK_metodo_de_pagameto = 1 OR FK_metodo_de_pagameto = 2 THEN 0 END) AS 'qtd_produto_pagas', "
				+ "COUNT(CASE WHEN FK_metodo_de_pagameto = 4 OR FK_metodo_de_pagameto = 5 THEN 0 END) AS 'qtd_produtos_i', "
				+ "CAST(SUM(total) AS DECIMAL(10,2)) as 'total_pago' "
				+ "FROM vwrelpagamentoprodutos WHERE YEAR(Data_da_facturacao) = ? AND MONTH(Data_da_facturacao) BETWEEN 4 AND 6 GROUP BY produto ORDER BY produto";
		}
		if(aux == 3){
			sql = "SELECT produto as 'servico', CAST((preco) AS DECIMAL(10,2)) as 'preco_unitario', COUNT(id_produto) as 'qtd_prod_venda', SUM(qtd) as 'qtd_total_produtos', "
				+ "COUNT(CASE WHEN FK_metodo_de_pagameto = 1 OR FK_metodo_de_pagameto = 2 THEN 0 END) AS 'qtd_produto_pagas', "
				+ "COUNT(CASE WHEN FK_metodo_de_pagameto = 4 OR FK_metodo_de_pagameto = 5 THEN 0 END) AS 'qtd_produtos_i', "
				+ "CAST(SUM(total) AS DECIMAL(10,2)) as 'total_pago' "
				+ "FROM vwrelpagamentoprodutos WHERE YEAR(Data_da_facturacao) = ? AND MONTH(Data_da_facturacao) BETWEEN 7 AND 9 GROUP BY produto ORDER BY produto";
		}
		if(aux == 4){
			sql = "SELECT produto as 'servico', CAST((preco) AS DECIMAL(10,2)) as 'preco_unitario', COUNT(id_produto) as 'qtd_prod_venda', SUM(qtd) as 'qtd_total_produtos', "
				+ "COUNT(CASE WHEN FK_metodo_de_pagameto = 1 OR FK_metodo_de_pagameto = 2 THEN 0 END) AS 'qtd_produto_pagas', "
				+ "COUNT(CASE WHEN FK_metodo_de_pagameto = 4 OR FK_metodo_de_pagameto = 5 THEN 0 END) AS 'qtd_produtos_i', "
				+ "CAST(SUM(total) AS DECIMAL(10,2)) as 'total_pago' "
				+ "FROM vwrelpagamentoprodutos WHERE YEAR(Data_da_facturacao) = ? AND MONTH(Data_da_facturacao) BETWEEN 10 AND 12 GROUP BY produto ORDER BY produto";
		}
		if(aux == 5){
			sql = "SELECT produto as 'servico', CAST((preco) AS DECIMAL(10,2)) as 'preco_unitario', COUNT(id_produto) as 'qtd_prod_venda', SUM(qtd) as 'qtd_total_produtos', "
				+ "COUNT(CASE WHEN FK_metodo_de_pagameto = 1 OR FK_metodo_de_pagameto = 2 THEN 0 END) AS 'qtd_produto_pagas', "
				+ "COUNT(CASE WHEN FK_metodo_de_pagameto = 4 OR FK_metodo_de_pagameto = 5 THEN 0 END) AS 'qtd_produtos_i', "
				+ "CAST(SUM(total) AS DECIMAL(10,2)) as 'total_pago' "
				+ "FROM vwrelpagamentoprodutos WHERE YEAR(Data_da_facturacao) = ? AND MONTH(Data_da_facturacao) BETWEEN 1 AND 6 GROUP BY produto ORDER BY produto";
		}
		if(aux == 6){
			sql = "SELECT produto as 'servico', CAST((preco) AS DECIMAL(10,2)) as 'preco_unitario', COUNT(id_produto) as 'qtd_prod_venda', SUM(qtd) as 'qtd_total_produtos', "
				+ "COUNT(CASE WHEN FK_metodo_de_pagameto = 1 OR FK_metodo_de_pagameto = 2 THEN 0 END) AS 'qtd_produto_pagas', "
				+ "COUNT(CASE WHEN FK_metodo_de_pagameto = 4 OR FK_metodo_de_pagameto = 5 THEN 0 END) AS 'qtd_produtos_i', "
				+ "CAST(SUM(total) AS DECIMAL(10,2)) as 'total_pago' "
				+ "FROM vwrelpagamentoprodutos WHERE YEAR(Data_da_facturacao) = ? AND MONTH(Data_da_facturacao) BETWEEN 7 AND 12 GROUP BY produto ORDER BY produto";
		}
		List<Paciente> lista = new ArrayList<Paciente>();
		try {
			con = Conexao.getConexao();
			PreparedStatement ps = con.prepareStatement(sql);
			ps.setInt(1, ano);
			 
			ResultSet rs = ps.executeQuery();
			while(rs.next()){
				Paciente p = new Paciente();
				p.setServico(rs.getString("servico"));
				p.setPreco(rs.getDouble("preco_unitario"));
				p.setQuantidade(rs.getInt("qtd_prod_venda"));
				p.setQtd_sessao(rs.getInt("qtd_total_produtos"));
				p.setQdt_pagas(rs.getInt("qtd_produto_pagas"));
				p.setQdt_isenta(rs.getInt("qtd_produtos_i"));
				p.setTotal_s(rs.getString("total_pago"));
				lista.add(p);
			}
			ps.close();
			con.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return lista;
	}
	
	public List<Paciente> buscarHistoricoPagamentosGeralDiario()
	{
		String sql = "SELECT ts.tipode_servico_facturado AS 'designacao', Count(ts.id_tip_serv_facturado) AS 'qtd', CAST(ifnull(sum(f.total_factura),0) AS DECIMAL(10,1)) AS 'total', "
				   + " CAST(ifnull(avg(f.total_factura),0) AS DECIMAL(10, 1)) AS 'media', CAST(COUNT(CASE WHEN g.id_genero = 1 THEN 0 END) * 100 / count(ts.id_tip_serv_facturado)AS DECIMAL(10, 1)) as 'percet_m', "
				   + " CAST(COUNT(CASE WHEN g.id_genero = 2 THEN 0 END) * 100 / count(ts.id_tip_serv_facturado)AS DECIMAL(10, 1)) as 'percet_f' FROM tbltipodeservicofacturado ts LEFT JOIN tblfactura f ON f.FK_tipo_deservico = ts.id_tip_serv_facturado "
				   + " JOIN tblcidadao c ON f.FK_paciente = c.FK_entidade JOIN tblgenero g ON c.FK_genero = g.id_genero "
				   + " WHERE f.Data_da_facturacao = curdate() GROUP BY ts.id_tip_serv_facturado";
		List<Paciente> lista = new ArrayList<Paciente>();
		try {
			con = Conexao.getConexao();
			PreparedStatement ps = con.prepareStatement(sql);
			ResultSet rs = ps.executeQuery();
			while(rs.next()){
				Paciente p = new Paciente();
				p.setDescricao(rs.getString("designacao"));
				 
				p.setQuantidade(rs.getInt("qtd"));
				p.setValor_total(rs.getDouble("total"));
				p.setValor_media(rs.getDouble("media"));
				p.setPerct_msc(rs.getDouble("percet_m"));
				p.setPerct_fmn(rs.getDouble("percet_f"));
				lista.add(p);
			}
			ps.close();
			con.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return lista;
	}

	public List<Paciente> buscarHistoricoPagamentosGeralEmAnos(int ano)
	{
		String sql = "SELECT ts.tipode_servico_facturado AS 'designacao', Count(ts.id_tip_serv_facturado) AS 'qtd', CAST(ifnull(sum(f.total_factura),0) AS DECIMAL(10,1)) AS 'total', "
				   + " CAST(ifnull(avg(f.total_factura),0) AS DECIMAL(10, 1)) AS 'media', CAST(COUNT(CASE WHEN g.id_genero = 1 THEN 0 END) * 100 / count(ts.id_tip_serv_facturado)AS DECIMAL(10, 1)) as 'percet_m', "
				   + " CAST(COUNT(CASE WHEN g.id_genero = 2 THEN 0 END) * 100 / count(ts.id_tip_serv_facturado)AS DECIMAL(10, 1)) as 'percet_f' FROM tbltipodeservicofacturado ts LEFT JOIN tblfactura f ON f.FK_tipo_deservico = ts.id_tip_serv_facturado "
				   + " JOIN tblcidadao c ON f.FK_paciente = c.FK_entidade JOIN tblgenero g ON c.FK_genero = g.id_genero "
				   + " WHERE Year(f.Data_da_facturacao) = ? GROUP BY ts.id_tip_serv_facturado";
		List<Paciente> lista = new ArrayList<Paciente>();
		try {
			con = Conexao.getConexao();
			PreparedStatement ps = con.prepareStatement(sql);
			ps.setInt(1, ano);
			ResultSet rs = ps.executeQuery();
			while(rs.next()){
				Paciente p = new Paciente();
				p.setDescricao(rs.getString("designacao"));
				 
				p.setQuantidade(rs.getInt("qtd"));
				p.setValor_total(rs.getDouble("total"));
				p.setValor_media(rs.getDouble("media"));
				p.setPerct_msc(rs.getDouble("percet_m"));
				p.setPerct_fmn(rs.getDouble("percet_f"));
				lista.add(p);
			}
			ps.close();
			con.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return lista;
	}
	
	public List<Paciente> buscarHistoricoPagamentosGeralEmAnosMes(int ano,int mes){
		String sql = "SELECT ts.tipode_servico_facturado AS 'designacao', Count(ts.id_tip_serv_facturado) AS 'qtd', CAST(ifnull(sum(f.total_factura),0) AS DECIMAL(10,1)) AS 'total', "
				   + " CAST(ifnull(avg(f.total_factura),0) AS DECIMAL(10, 1)) AS 'media', CAST(COUNT(CASE WHEN g.id_genero = 1 THEN 0 END) * 100 / count(ts.id_tip_serv_facturado)AS DECIMAL(10, 1)) as 'percet_m', "
				   + " CAST(COUNT(CASE WHEN g.id_genero = 2 THEN 0 END) * 100 / count(ts.id_tip_serv_facturado)AS DECIMAL(10, 1)) as 'percet_f' FROM tbltipodeservicofacturado ts LEFT JOIN tblfactura f ON f.FK_tipo_deservico = ts.id_tip_serv_facturado "
				   + " JOIN tblcidadao c ON f.FK_paciente = c.FK_entidade JOIN tblgenero g ON c.FK_genero = g.id_genero "
				   + " WHERE Year(f.Data_da_facturacao) = ? and month(f.Data_da_facturacao) = ? GROUP BY ts.id_tip_serv_facturado";
		List<Paciente> lista = new ArrayList<Paciente>();
		try {
			con = Conexao.getConexao();
			PreparedStatement ps = con.prepareStatement(sql);
			ps.setInt(1, ano);
			ps.setInt(2, mes);
			ResultSet rs = ps.executeQuery();
			while(rs.next()){
				Paciente p = new Paciente();
				p.setDescricao(rs.getString("designacao"));
				 
				p.setQuantidade(rs.getInt("qtd"));
				p.setValor_total(rs.getDouble("total"));
				p.setValor_media(rs.getDouble("media"));
				p.setPerct_msc(rs.getDouble("percet_m"));
				p.setPerct_fmn(rs.getDouble("percet_f"));
				lista.add(p);
			}
			ps.close();
			con.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return lista;
	}
	
	public List<Paciente> buscarHistoricoPagamentosGeralEmAnosMesDias(int ano,int mes,int dia1,int dia2){
		String sql = "SELECT ts.tipode_servico_facturado AS 'designacao', Count(ts.id_tip_serv_facturado) AS 'qtd', CAST(ifnull(sum(f.total_factura),0) AS DECIMAL(10,1)) AS 'total', "
				   + " CAST(ifnull(avg(f.total_factura),0) AS DECIMAL(10, 1)) AS 'media', CAST(COUNT(CASE WHEN g.id_genero = 1 THEN 0 END) * 100 / count(ts.id_tip_serv_facturado)AS DECIMAL(10, 1)) as 'percet_m', "
				   + " CAST(COUNT(CASE WHEN g.id_genero = 2 THEN 0 END) * 100 / count(ts.id_tip_serv_facturado)AS DECIMAL(10, 1)) as 'percet_f' FROM tbltipodeservicofacturado ts LEFT JOIN tblfactura f ON f.FK_tipo_deservico = ts.id_tip_serv_facturado "
				   + " JOIN tblcidadao c ON f.FK_paciente = c.FK_entidade JOIN tblgenero g ON c.FK_genero = g.id_genero "
				   + " WHERE Year(f.Data_da_facturacao) = ? and month(f.Data_da_facturacao) = ? and day(f.Data_da_facturacao) between ? and ? GROUP BY ts.id_tip_serv_facturado";
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
				p.setDescricao(rs.getString("designacao"));
				 
				p.setQuantidade(rs.getInt("qtd"));
				p.setValor_total(rs.getDouble("total"));
				p.setValor_media(rs.getDouble("media"));
				p.setPerct_msc(rs.getDouble("percet_m"));
				p.setPerct_fmn(rs.getDouble("percet_f"));
				lista.add(p);
			}
			ps.close();
			con.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return lista;
	}

	public List<Paciente> buscarHistoricoPagamentosGeralEmAnosSeTrimestre(int ano,int aux){
		String sql = "";
		if(aux == 1){
			sql = "SELECT ts.tipode_servico_facturado AS 'designacao', Count(ts.id_tip_serv_facturado) AS 'qtd', CAST(ifnull(sum(f.total_factura),0) AS DECIMAL(10,1)) AS 'total', "
					   + " CAST(ifnull(avg(f.total_factura),0) AS DECIMAL(10, 1)) AS 'media', CAST(COUNT(CASE WHEN g.id_genero = 1 THEN 0 END) * 100 / count(ts.id_tip_serv_facturado)AS DECIMAL(10, 1)) as 'percet_m', "
					   + " CAST(COUNT(CASE WHEN g.id_genero = 2 THEN 0 END) * 100 / count(ts.id_tip_serv_facturado)AS DECIMAL(10, 1)) as 'percet_f' FROM tbltipodeservicofacturado ts LEFT JOIN tblfactura f ON f.FK_tipo_deservico = ts.id_tip_serv_facturado "
					   + " JOIN tblcidadao c ON f.FK_paciente = c.FK_entidade JOIN tblgenero g ON c.FK_genero = g.id_genero "
					   + " WHERE Year(f.Data_da_facturacao) = ? and month(f.Data_da_facturacao) between 1 and 3 GROUP BY ts.id_tip_serv_facturado";
		}
		if(aux == 2){
			sql = "SELECT ts.tipode_servico_facturado AS 'designacao', Count(ts.id_tip_serv_facturado) AS 'qtd', CAST(ifnull(sum(f.total_factura),0) AS DECIMAL(10,1)) AS 'total', "
					   + " CAST(ifnull(avg(f.total_factura),0) AS DECIMAL(10, 1)) AS 'media', CAST(COUNT(CASE WHEN g.id_genero = 1 THEN 0 END) * 100 / count(ts.id_tip_serv_facturado)AS DECIMAL(10, 1)) as 'percet_m', "
					   + " CAST(COUNT(CASE WHEN g.id_genero = 2 THEN 0 END) * 100 / count(ts.id_tip_serv_facturado)AS DECIMAL(10, 1)) as 'percet_f' FROM tbltipodeservicofacturado ts LEFT JOIN tblfactura f ON f.FK_tipo_deservico = ts.id_tip_serv_facturado "
					   + " JOIN tblcidadao c ON f.FK_paciente = c.FK_entidade JOIN tblgenero g ON c.FK_genero = g.id_genero "
					   + " WHERE Year(f.Data_da_facturacao) = ? and month(f.Data_da_facturacao) between 4 and 6 GROUP BY ts.id_tip_serv_facturado";
		}
		if(aux == 3){
			sql = "SELECT ts.tipode_servico_facturado AS 'designacao', Count(ts.id_tip_serv_facturado) AS 'qtd', CAST(ifnull(sum(f.total_factura),0) AS DECIMAL(10,1)) AS 'total', "
					   + " CAST(ifnull(avg(f.total_factura),0) AS DECIMAL(10, 1)) AS 'media', CAST(COUNT(CASE WHEN g.id_genero = 1 THEN 0 END) * 100 / count(ts.id_tip_serv_facturado)AS DECIMAL(10, 1)) as 'percet_m', "
					   + " CAST(COUNT(CASE WHEN g.id_genero = 2 THEN 0 END) * 100 / count(ts.id_tip_serv_facturado)AS DECIMAL(10, 1)) as 'percet_f' FROM tbltipodeservicofacturado ts LEFT JOIN tblfactura f ON f.FK_tipo_deservico = ts.id_tip_serv_facturado "
					   + " JOIN tblcidadao c ON f.FK_paciente = c.FK_entidade JOIN tblgenero g ON c.FK_genero = g.id_genero "
					   + " WHERE Year(f.Data_da_facturacao) = ? and month(f.Data_da_facturacao) between 7 and 9 GROUP BY ts.id_tip_serv_facturado";
		}
		if(aux == 4){
			sql = "SELECT ts.tipode_servico_facturado AS 'designacao', Count(ts.id_tip_serv_facturado) AS 'qtd', CAST(ifnull(sum(f.total_factura),0) AS DECIMAL(10,1)) AS 'total', "
					   + " CAST(ifnull(avg(f.total_factura),0) AS DECIMAL(10, 1)) AS 'media', CAST(COUNT(CASE WHEN g.id_genero = 1 THEN 0 END) * 100 / count(ts.id_tip_serv_facturado)AS DECIMAL(10, 1)) as 'percet_m', "
					   + " CAST(COUNT(CASE WHEN g.id_genero = 2 THEN 0 END) * 100 / count(ts.id_tip_serv_facturado)AS DECIMAL(10, 1)) as 'percet_f' FROM tbltipodeservicofacturado ts LEFT JOIN tblfactura f ON f.FK_tipo_deservico = ts.id_tip_serv_facturado "
					   + " JOIN tblcidadao c ON f.FK_paciente = c.FK_entidade JOIN tblgenero g ON c.FK_genero = g.id_genero "
					   + " WHERE Year(f.Data_da_facturacao) = ? and month(f.Data_da_facturacao) between 10 and 12 GROUP BY ts.id_tip_serv_facturado";
		}
		if(aux == 5){
			sql = "SELECT ts.tipode_servico_facturado AS 'designacao', Count(ts.id_tip_serv_facturado) AS 'qtd', CAST(ifnull(sum(f.total_factura),0) AS DECIMAL(10,1)) AS 'total', "
					   + " CAST(ifnull(avg(f.total_factura),0) AS DECIMAL(10, 1)) AS 'media', CAST(COUNT(CASE WHEN g.id_genero = 1 THEN 0 END) * 100 / count(ts.id_tip_serv_facturado)AS DECIMAL(10, 1)) as 'percet_m', "
					   + " CAST(COUNT(CASE WHEN g.id_genero = 2 THEN 0 END) * 100 / count(ts.id_tip_serv_facturado)AS DECIMAL(10, 1)) as 'percet_f' FROM tbltipodeservicofacturado ts LEFT JOIN tblfactura f ON f.FK_tipo_deservico = ts.id_tip_serv_facturado "
					   + " JOIN tblcidadao c ON f.FK_paciente = c.FK_entidade JOIN tblgenero g ON c.FK_genero = g.id_genero "
					   + " WHERE Year(f.Data_da_facturacao) = ? and month(f.Data_da_facturacao) between 1 and 6 GROUP BY ts.id_tip_serv_facturado";
		}
		if(aux == 6){
			sql = "SELECT ts.tipode_servico_facturado AS 'designacao', Count(ts.id_tip_serv_facturado) AS 'qtd', CAST(ifnull(sum(f.total_factura),0) AS DECIMAL(10,1)) AS 'total', "
					   + " CAST(ifnull(avg(f.total_factura),0) AS DECIMAL(10, 1)) AS 'media', CAST(COUNT(CASE WHEN g.id_genero = 1 THEN 0 END) * 100 / count(ts.id_tip_serv_facturado)AS DECIMAL(10, 1)) as 'percet_m', "
					   + " CAST(COUNT(CASE WHEN g.id_genero = 2 THEN 0 END) * 100 / count(ts.id_tip_serv_facturado)AS DECIMAL(10, 1)) as 'percet_f' FROM tbltipodeservicofacturado ts LEFT JOIN tblfactura f ON f.FK_tipo_deservico = ts.id_tip_serv_facturado "
					   + " JOIN tblcidadao c ON f.FK_paciente = c.FK_entidade JOIN tblgenero g ON c.FK_genero = g.id_genero "
					   + " WHERE Year(f.Data_da_facturacao) = ? and month(f.Data_da_facturacao) between 7 and 12 GROUP BY ts.id_tip_serv_facturado";
		}
		List<Paciente> lista = new ArrayList<Paciente>();
		try {
			con = Conexao.getConexao();
			PreparedStatement ps = con.prepareStatement(sql);
			ps.setInt(1, ano);
			
			ResultSet rs = ps.executeQuery();
			while(rs.next()){
				Paciente p = new Paciente();
				p.setDescricao(rs.getString("designacao"));
				 
				p.setQuantidade(rs.getInt("qtd"));
				p.setValor_total(rs.getDouble("total"));
				p.setValor_media(rs.getDouble("media"));
				p.setPerct_msc(rs.getDouble("percet_m"));
				p.setPerct_fmn(rs.getDouble("percet_f"));
				lista.add(p);
			}
			ps.close();
			con.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return lista;
	}

	public List<Paciente> buscarPagamentosDesempenhoGeral()
	{
		String sql = "SELECT fn.FK_entidade, fn.n_c_funcionario, COUNT(Distinct(f.data_da_facturacao)) AS  'dias_trabalho', COUNT(f.id_factura) AS 'qtd_factura', "
				   + " CAST(ifnull(sum(f.total_factura),0) AS DECIMAL(10,1)) AS 'total_vendas', CAST(ifnull(avg(f.id_factura),0) AS DECIMAL(10, 1)) AS 'media' "
				   + " FROM vwfuncionariosimples fn JOIN tblfactura f ON fn.FK_entidade = f.FK_funcionario WHERE f.Data_da_facturacao = Curdate() GROUP BY f.FK_funcionario";
		List<Paciente> lista = new ArrayList<Paciente>();
		try {
			con = Conexao.getConexao();
			PreparedStatement ps = con.prepareStatement(sql);
			ResultSet rs = ps.executeQuery();
			while(rs.next()){
				Paciente p = new Paciente();
				p.setNomeCompleto(rs.getString("n_c_funcionario"));
				p.setFK_funcionario(rs.getInt("FK_entidade"));
				p.setDias_trabalhado(rs.getInt("dias_trabalho"));
				p.setQuantidade(rs.getInt("qtd_factura"));
				p.setValor_total(rs.getDouble("total_vendas"));
				p.setValor_media(rs.getDouble("media"));
				
				lista.add(p);
			}
			ps.close();
			con.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return lista;
	}
	
	
	public List<Paciente> buscarPagamentosDesempenhoGeralEmAno(int ano){
		String sql = "SELECT fn.FK_entidade, fn.n_c_funcionario, COUNT(Distinct(f.data_da_facturacao)) AS  'dias_trabalho', COUNT(f.id_factura) AS 'qtd_factura', "
				   + " CAST(ifnull(sum(f.total_factura),0) AS DECIMAL(10,1)) AS 'total_vendas', CAST(ifnull(avg(f.id_factura),0) AS DECIMAL(10, 1)) AS 'media' "
				   + " FROM vwfuncionariosimples fn JOIN tblfactura f ON fn.FK_entidade = f.FK_funcionario "
				   + " Where year(data_da_facturacao) = ? GROUP BY f.FK_funcionario";
		List<Paciente> lista = new ArrayList<Paciente>();
		try {
			con = Conexao.getConexao();
			PreparedStatement ps = con.prepareStatement(sql);
			ps.setInt(1, ano); 
			ResultSet rs = ps.executeQuery();
			while(rs.next()){
				Paciente p = new Paciente();
				p.setNomeCompleto(rs.getString("n_c_funcionario"));
				p.setFK_funcionario(rs.getInt("FK_entidade"));
				p.setDias_trabalhado(rs.getInt("dias_trabalho"));
				p.setQuantidade(rs.getInt("qtd_factura"));
				p.setValor_total(rs.getDouble("total_vendas"));
				p.setValor_media(rs.getDouble("media"));
				
				lista.add(p);
			}
			ps.close();
			con.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return lista;
	}
	
	public List<Paciente> buscarPagamentosDesempenhoGeralEmAnoMes(int ano,int mes){
		String sql = "SELECT fn.FK_entidade, fn.n_c_funcionario, COUNT(Distinct(f.data_da_facturacao)) AS  'dias_trabalho', COUNT(f.id_factura) AS 'qtd_factura', "
				   + " CAST(ifnull(sum(f.total_factura),0) AS DECIMAL(10,1)) AS 'total_vendas', CAST(ifnull(avg(f.id_factura),0) AS DECIMAL(10, 1)) AS 'media' "
				   + " FROM vwfuncionariosimples fn JOIN tblfactura f ON fn.FK_entidade = f.FK_funcionario "
				   + " Where year(data_da_facturacao) = ? and month(data_da_facturacao) = ? GROUP BY f.FK_funcionario";
		List<Paciente> lista = new ArrayList<Paciente>();
		try {
			con = Conexao.getConexao();
			PreparedStatement ps = con.prepareStatement(sql);
			ps.setInt(1, ano); 
			ps.setInt(2, mes);
			ResultSet rs = ps.executeQuery();
			while(rs.next()){
				Paciente p = new Paciente();
				p.setNomeCompleto(rs.getString("n_c_funcionario"));
				p.setFK_funcionario(rs.getInt("FK_entidade"));
				p.setDias_trabalhado(rs.getInt("dias_trabalho"));
				p.setQuantidade(rs.getInt("qtd_factura"));
				p.setValor_total(rs.getDouble("total_vendas"));
				p.setValor_media(rs.getDouble("media"));
				
				lista.add(p);
			}
			ps.close();
			con.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return lista;
	}
	
	public List<Paciente> buscarPagamentosDesempenhoGeralEmAnoMesDias(int ano,int mes,int dia1,int dia2){
		String sql = "SELECT fn.FK_entidade, fn.n_c_funcionario, COUNT(Distinct(f.data_da_facturacao)) AS  'dias_trabalho', COUNT(f.id_factura) AS 'qtd_factura', "
				   + " CAST(ifnull(sum(f.total_factura),0) AS DECIMAL(10,1)) AS 'total_vendas', CAST(ifnull(avg(f.id_factura),0) AS DECIMAL(10, 1)) AS 'media' "
				   + " FROM vwfuncionariosimples fn JOIN tblfactura f ON fn.FK_entidade = f.FK_funcionario "
				   + " Where year(data_da_facturacao) = ? and month(data_da_facturacao) = ? and day(data_da_facturacao) between ? and ? GROUP BY f.FK_funcionario";
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
				p.setNomeCompleto(rs.getString("n_c_funcionario"));
				p.setFK_funcionario(rs.getInt("FK_entidade"));
				p.setDias_trabalhado(rs.getInt("dias_trabalho"));
				p.setQuantidade(rs.getInt("qtd_factura"));
				p.setValor_total(rs.getDouble("total_vendas"));
				p.setValor_media(rs.getDouble("media"));
				
				lista.add(p);
			}
			ps.close();
			con.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return lista;
	}
	
	public List<Paciente> buscarDesempenhoGeralFuncionarios()
	{
		String sql = "SELECT fn.FK_entidade, fn.n_c_funcionario"
				   + " FROM vwfuncionariosimples fn JOIN tblfactura f ON fn.FK_entidade = f.FK_funcionario GROUP BY f.FK_funcionario";
		List<Paciente> lista = new ArrayList<Paciente>();
		try {
			con = Conexao.getConexao();
			PreparedStatement ps = con.prepareStatement(sql);
			ResultSet rs = ps.executeQuery();
			while(rs.next()){
				Paciente p = new Paciente();
				p.setNomeCompleto(rs.getString("n_c_funcionario"));
				p.setFK_funcionario(rs.getInt("FK_entidade"));
				
				lista.add(p);
			}
			ps.close();
			con.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return lista;
	}
	
	public List<Paciente> buscarPagamentosDesempenhoGeralEmAnoSeTrimestre(int ano,int aux){
		String sql = "";
		if(aux == 1){
			sql = "SELECT fn.FK_entidade, fn.n_c_funcionario, COUNT(Distinct(f.data_da_facturacao)) AS  'dias_trabalho', COUNT(f.id_factura) AS 'qtd_factura', "
					   + " CAST(ifnull(sum(f.total_factura),0) AS DECIMAL(10,1)) AS 'total_vendas', CAST(ifnull(avg(f.id_factura),0) AS DECIMAL(10, 1)) AS 'media' "
					   + " FROM vwfuncionariosimples fn JOIN tblfactura f ON fn.FK_entidade = f.FK_funcionario "
					   + " Where year(data_da_facturacao) = ? and month(data_da_facturacao) between 1 and 3 GROUP BY f.FK_funcionario";
		}
		if(aux == 2){
			sql = "SELECT fn.FK_entidade, fn.n_c_funcionario, COUNT(Distinct(f.data_da_facturacao)) AS  'dias_trabalho', COUNT(f.id_factura) AS 'qtd_factura', "
					   + " CAST(ifnull(sum(f.total_factura),0) AS DECIMAL(10,1)) AS 'total_vendas', CAST(ifnull(avg(f.id_factura),0) AS DECIMAL(10, 1)) AS 'media' "
					   + " FROM vwfuncionariosimples fn JOIN tblfactura f ON fn.FK_entidade = f.FK_funcionario "
					   + " Where year(data_da_facturacao) = ? and month(data_da_facturacao) between 4 and 6 GROUP BY f.FK_funcionario";
		}
		if(aux == 3){
			sql = "SELECT fn.FK_entidade, fn.n_c_funcionario, COUNT(Distinct(f.data_da_facturacao)) AS  'dias_trabalho', COUNT(f.id_factura) AS 'qtd_factura', "
					   + " CAST(ifnull(sum(f.total_factura),0) AS DECIMAL(10,1)) AS 'total_vendas', CAST(ifnull(avg(f.id_factura),0) AS DECIMAL(10, 1)) AS 'media' "
					   + " FROM vwfuncionariosimples fn JOIN tblfactura f ON fn.FK_entidade = f.FK_funcionario "
					   + " Where year(data_da_facturacao) = ? and month(data_da_facturacao) between 7 and 9 GROUP BY f.FK_funcionario";
		}
		if(aux == 4){
			sql = "SELECT fn.FK_entidade, fn.n_c_funcionario, COUNT(Distinct(f.data_da_facturacao)) AS  'dias_trabalho', COUNT(f.id_factura) AS 'qtd_factura', "
					   + " CAST(ifnull(sum(f.total_factura),0) AS DECIMAL(10,1)) AS 'total_vendas', CAST(ifnull(avg(f.id_factura),0) AS DECIMAL(10, 1)) AS 'media' "
					   + " FROM vwfuncionariosimples fn JOIN tblfactura f ON fn.FK_entidade = f.FK_funcionario "
					   + " Where year(data_da_facturacao) = ? and month(data_da_facturacao) between 10 and 12 GROUP BY f.FK_funcionario";
		}
		if(aux == 5){
			sql = "SELECT fn.FK_entidade, fn.n_c_funcionario, COUNT(Distinct(f.data_da_facturacao)) AS  'dias_trabalho', COUNT(f.id_factura) AS 'qtd_factura', "
					   + " CAST(ifnull(sum(f.total_factura),0) AS DECIMAL(10,1)) AS 'total_vendas', CAST(ifnull(avg(f.id_factura),0) AS DECIMAL(10, 1)) AS 'media' "
					   + " FROM vwfuncionariosimples fn JOIN tblfactura f ON fn.FK_entidade = f.FK_funcionario "
					   + " Where year(data_da_facturacao) = ? and month(data_da_facturacao) between 1 and 6 GROUP BY f.FK_funcionario";
		}
		if(aux == 6){
			sql = "SELECT fn.FK_entidade, fn.n_c_funcionario, COUNT(Distinct(f.data_da_facturacao)) AS  'dias_trabalho', COUNT(f.id_factura) AS 'qtd_factura', "
					   + " CAST(ifnull(sum(f.total_factura),0) AS DECIMAL(10,1)) AS 'total_vendas', CAST(ifnull(avg(f.id_factura),0) AS DECIMAL(10, 1)) AS 'media' "
					   + " FROM vwfuncionariosimples fn JOIN tblfactura f ON fn.FK_entidade = f.FK_funcionario "
					   + " Where year(data_da_facturacao) = ? and month(data_da_facturacao) between 7 and 12 GROUP BY f.FK_funcionario";
		}
		List<Paciente> lista = new ArrayList<Paciente>();
		try {
			con = Conexao.getConexao();
			PreparedStatement ps = con.prepareStatement(sql);
			ps.setInt(1, ano); 
			 
			ResultSet rs = ps.executeQuery();
			while(rs.next()){
				Paciente p = new Paciente();
				p.setNomeCompleto(rs.getString("n_c_funcionario"));
				p.setFK_funcionario(rs.getInt("FK_entidade"));
				p.setDias_trabalhado(rs.getInt("dias_trabalho"));
				p.setQuantidade(rs.getInt("qtd_factura"));
				p.setValor_total(rs.getDouble("total_vendas"));
				p.setValor_media(rs.getDouble("media"));
				
				lista.add(p);
			}
			ps.close();
			con.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return lista;
	}
	
	public List<Paciente> buscarPagamentosBalancoGeral(){
		String sql = "SELECT f.data_da_facturacao AS 'data_venda', COUNT(DISTINCT(f.FK_funcionario)) AS 'num_funcionarios', COUNT(f.id_factura) AS 'qtd_facturas', "
				   + " CAST(IFNULL(SUM(f.total_factura),0) AS DECIMAL(10,1)) AS 'total_vendas', CAST(IFNULL((SUM(f.total_factura)/COUNT(DISTINCT(f.FK_funcionario))),0) AS DECIMAL(10, 1)) AS 'media' "
				   + " FROM tblfactura f WHERE f.Data_da_facturacao = CurDate() GROUP BY f.data_da_facturacao ORDER BY f.data_da_facturacao DESC";
		
		List<Paciente> lista = new ArrayList<Paciente>();
		try {
			con = Conexao.getConexao();
			PreparedStatement ps = con.prepareStatement(sql);
			ResultSet rs = ps.executeQuery();
			while(rs.next()){
				Paciente p = new Paciente();
				Calendar data = Calendar.getInstance();
				data.setTime(rs.getDate("data_venda"));
				p.setData_registo(data);
				p.setQuantidade(rs.getInt("num_funcionarios"));
				p.setQdt_pagas(rs.getInt("qtd_facturas"));
				p.setValor_total(rs.getDouble("total_vendas"));
				p.setValor_media(rs.getDouble("media"));
				
				lista.add(p);
			}
			ps.close();
			con.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return lista;
	}
	
	public List<Paciente> buscarPagamentosBalancoGeralEmAno(int ano){
		String sql = "SELECT f.data_da_facturacao AS 'data_venda',  COUNT(DISTINCT(f.FK_funcionario)) AS 'num_funcionarios', COUNT(f.id_factura) AS 'qtd_facturas', "
				   + " CAST(IFNULL(SUM(f.total_factura),0) AS DECIMAL(10,1)) AS 'total_vendas', CAST(IFNULL((SUM(f.total_factura)/COUNT(DISTINCT(f.FK_funcionario))),0) AS DECIMAL(10, 1)) AS 'media' "
				   + " FROM tblfactura f WHERE year(f.data_da_facturacao) = ? GROUP BY f.data_da_facturacao ORDER BY f.data_da_facturacao DESC";
		
		List<Paciente> lista = new ArrayList<Paciente>();
		try {
			con = Conexao.getConexao();
			PreparedStatement ps = con.prepareStatement(sql);
			ps.setInt(1, ano);
			ResultSet rs = ps.executeQuery();
			while(rs.next()){
				Paciente p = new Paciente();
				Calendar data = Calendar.getInstance();
				data.setTime(rs.getDate("data_venda"));
				p.setData_registo(data);
				p.setQuantidade(rs.getInt("num_funcionarios"));
				p.setQdt_pagas(rs.getInt("qtd_facturas"));
				p.setValor_total(rs.getDouble("total_vendas"));
				p.setValor_media(rs.getDouble("media"));
				
				lista.add(p);
			}
			ps.close();
			con.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return lista;
	}

	public List<Paciente> buscarPagamentosBalancoGeralEmAnoMes(int ano,int mes){
		String sql = "SELECT f.data_da_facturacao AS 'data_venda',  COUNT(DISTINCT(f.FK_funcionario)) AS 'num_funcionarios', COUNT(f.id_factura) AS 'qtd_facturas', "
				   + " CAST(IFNULL(SUM(f.total_factura),0) AS DECIMAL(10,1)) AS 'total_vendas', CAST(IFNULL((SUM(f.total_factura)/COUNT(DISTINCT(f.FK_funcionario))),0) AS DECIMAL(10, 1)) AS 'media' "
				   + " FROM tblfactura f WHERE year(f.data_da_facturacao) = ? and month(f.data_da_facturacao) = ? GROUP BY f.data_da_facturacao ORDER BY f.data_da_facturacao DESC";
		
		List<Paciente> lista = new ArrayList<Paciente>();
		try {
			con = Conexao.getConexao();
			PreparedStatement ps = con.prepareStatement(sql);
			ps.setInt(1, ano);
			ps.setInt(2, mes);
			ResultSet rs = ps.executeQuery();
			while(rs.next()){
				Paciente p = new Paciente();
				Calendar data = Calendar.getInstance();
				data.setTime(rs.getDate("data_venda"));
				p.setData_registo(data);
				p.setQuantidade(rs.getInt("num_funcionarios"));
				p.setQdt_pagas(rs.getInt("qtd_facturas"));
				p.setValor_total(rs.getDouble("total_vendas"));
				p.setValor_media(rs.getDouble("media"));
				
				lista.add(p);
			}
			ps.close();
			con.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return lista;
	}
	
	public List<Paciente> buscarPagamentosBalancoGeralEmAnoMesDias(int ano,int mes,int dia1,int dia2){
		String sql = "SELECT f.data_da_facturacao AS 'data_venda',  COUNT(DISTINCT(f.FK_funcionario)) AS 'num_funcionarios', COUNT(f.id_factura) AS 'qtd_facturas', "
				   + " CAST(IFNULL(SUM(f.total_factura),0) AS DECIMAL(10,1)) AS 'total_vendas', CAST(IFNULL((SUM(f.total_factura)/COUNT(DISTINCT(f.FK_funcionario))),0) AS DECIMAL(10, 1)) AS 'media' "
				   + " FROM tblfactura f WHERE year(f.data_da_facturacao) = ? and month(f.data_da_facturacao) = ? and day(f.data_da_facturacao) between ? and ? GROUP BY f.data_da_facturacao ORDER BY f.data_da_facturacao DESC";
		
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
				Calendar data = Calendar.getInstance();
				data.setTime(rs.getDate("data_venda"));
				p.setData_registo(data);
				p.setQuantidade(rs.getInt("num_funcionarios"));
				p.setQdt_pagas(rs.getInt("qtd_facturas"));
				p.setValor_total(rs.getDouble("total_vendas"));
				p.setValor_media(rs.getDouble("media"));
				
				lista.add(p);
			}
			ps.close();
			con.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return lista;
	}

	public List<Paciente> buscarPagamentosBalancoGeralEmAnoSeTrimestre(int ano,int aux){
		String sql = "";
		
		if(aux == 1){
			sql = "SELECT f.data_da_facturacao AS 'data_venda',  COUNT(DISTINCT(f.FK_funcionario)) AS 'num_funcionarios', COUNT(f.id_factura) AS 'qtd_facturas', "
					   + " CAST(IFNULL(SUM(f.total_factura),0) AS DECIMAL(10,1)) AS 'total_vendas', CAST(IFNULL((SUM(f.total_factura)/COUNT(DISTINCT(f.FK_funcionario))),0) AS DECIMAL(10, 1)) AS 'media' "
					   + " FROM tblfactura f WHERE year(f.data_da_facturacao) = ? and month(f.data_da_facturacao) between 1 and 3 GROUP BY f.data_da_facturacao ORDER BY f.data_da_facturacao DESC";
		}
		if(aux == 2){
			sql = "SELECT f.data_da_facturacao AS 'data_venda',  COUNT(DISTINCT(f.FK_funcionario)) AS 'num_funcionarios', COUNT(f.id_factura) AS 'qtd_facturas', "
					   + " CAST(IFNULL(SUM(f.total_factura),0) AS DECIMAL(10,1)) AS 'total_vendas', CAST(IFNULL((SUM(f.total_factura)/COUNT(DISTINCT(f.FK_funcionario))),0) AS DECIMAL(10, 1)) AS 'media' "
					   + " FROM tblfactura f WHERE year(f.data_da_facturacao) = ? and month(f.data_da_facturacao) between 4 and 6 GROUP BY f.data_da_facturacao ORDER BY f.data_da_facturacao DESC";
		}
		if(aux == 3){
			sql = "SELECT f.data_da_facturacao AS 'data_venda',  COUNT(DISTINCT(f.FK_funcionario)) AS 'num_funcionarios', COUNT(f.id_factura) AS 'qtd_facturas', "
					   + " CAST(IFNULL(SUM(f.total_factura),0) AS DECIMAL(10,1)) AS 'total_vendas', CAST(IFNULL((SUM(f.total_factura)/COUNT(DISTINCT(f.FK_funcionario))),0) AS DECIMAL(10, 1)) AS 'media' "
					   + " FROM tblfactura f WHERE year(f.data_da_facturacao) = ? and month(f.data_da_facturacao) between 7 and 9 GROUP BY f.data_da_facturacao ORDER BY f.data_da_facturacao DESC";
		}
		if(aux == 4){
			sql = "SELECT f.data_da_facturacao AS 'data_venda',  COUNT(DISTINCT(f.FK_funcionario)) AS 'num_funcionarios', COUNT(f.id_factura) AS 'qtd_facturas', "
					   + " CAST(IFNULL(SUM(f.total_factura),0) AS DECIMAL(10,1)) AS 'total_vendas', CAST(IFNULL((SUM(f.total_factura)/COUNT(DISTINCT(f.FK_funcionario))),0) AS DECIMAL(10, 1)) AS 'media' "
					   + " FROM tblfactura f WHERE year(f.data_da_facturacao) = ? and month(f.data_da_facturacao) between 10 and 12 GROUP BY f.data_da_facturacao ORDER BY f.data_da_facturacao DESC";
		}
		if(aux == 5){
			sql = "SELECT f.data_da_facturacao AS 'data_venda',  COUNT(DISTINCT(f.FK_funcionario)) AS 'num_funcionarios', COUNT(f.id_factura) AS 'qtd_facturas', "
					   + " CAST(IFNULL(SUM(f.total_factura),0) AS DECIMAL(10,1)) AS 'total_vendas', CAST(IFNULL((SUM(f.total_factura)/COUNT(DISTINCT(f.FK_funcionario))),0) AS DECIMAL(10, 1)) AS 'media' "
					   + " FROM tblfactura f WHERE year(f.data_da_facturacao) = ? and month(f.data_da_facturacao) between 1 and 6 GROUP BY f.data_da_facturacao ORDER BY f.data_da_facturacao DESC";
		}
		if(aux == 6){
			sql = "SELECT f.data_da_facturacao AS 'data_venda',  COUNT(DISTINCT(f.FK_funcionario)) AS 'num_funcionarios', COUNT(f.id_factura) AS 'qtd_facturas', "
					   + " CAST(IFNULL(SUM(f.total_factura),0) AS DECIMAL(10,1)) AS 'total_vendas', CAST(IFNULL((SUM(f.total_factura)/COUNT(DISTINCT(f.FK_funcionario))),0) AS DECIMAL(10, 1)) AS 'media' "
					   + " FROM tblfactura f WHERE year(f.data_da_facturacao) = ? and month(f.data_da_facturacao) between 7 and 12 GROUP BY f.data_da_facturacao ORDER BY f.data_da_facturacao DESC";
		}
		List<Paciente> lista = new ArrayList<Paciente>();
		try {
			con = Conexao.getConexao();
			PreparedStatement ps = con.prepareStatement(sql);
			ps.setInt(1, ano);
			 
			ResultSet rs = ps.executeQuery();
			while(rs.next()){
				Paciente p = new Paciente();
				Calendar data = Calendar.getInstance();
				data.setTime(rs.getDate("data_venda"));
				p.setData_registo(data);
				p.setQuantidade(rs.getInt("num_funcionarios"));
				p.setQdt_pagas(rs.getInt("qtd_facturas"));
				p.setValor_total(rs.getDouble("total_vendas"));
				p.setValor_media(rs.getDouble("media"));
				
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
