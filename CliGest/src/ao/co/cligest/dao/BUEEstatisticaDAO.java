package ao.co.cligest.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import ao.co.cligest.entidades.Paciente;

public class BUEEstatisticaDAO {

	private Connection con;
	
	public List<Paciente> buscarHistoricoAtendidosUEDiario(){
		String sql = "select 'Urgencia' as tipo, count(u.id_urgencia) as 'qtd', COUNT(CASE WHEN g.id_genero = 1 THEN 0 END) AS 'masculino',"
				   + "CAST(COUNT(CASE WHEN g.id_genero = 1 THEN 0 END) * 100 / count(u.id_urgencia) AS DECIMAL(10, 1)) as 'percet_m',"
				   + "COUNT(CASE WHEN g.id_genero = 2 THEN 0 END) AS 'feminino', CAST(COUNT(CASE WHEN g.id_genero = 2 THEN 0 END) * 100 / count(u.id_urgencia)AS DECIMAL(10, 1)) as 'percet_f'"
				   + "from tblurgencia u, tblgenero g, tblcidadao cc where  u.FK_paciente = cc.FK_entidade and cc.FK_genero = g.id_genero and "
				   + "year(u.data_registo) = 2016 "
				   + "UNION "
				   + "select 'Emergencia' , count(e.id_emergencia) as 'qtd', COUNT(CASE WHEN g.id_genero = 1 THEN 0 END) AS 'masculino',"
				   + "CAST(COUNT(CASE WHEN g.id_genero = 1 THEN 0 END) * 100 / count(e.id_emergencia) AS DECIMAL(10, 1)) as 'percet_m',"
				   + "COUNT(CASE WHEN g.id_genero = 2 THEN 0 END) AS 'feminino',CAST(COUNT(CASE WHEN g.id_genero = 2 THEN 0 END) * 100 / count(e.id_emergencia)AS DECIMAL(10, 1)) as 'percet_f'"
				   + "from tblemergencia e, tblgenero g, tblcidadao cc where e.FK_paciente = cc.FK_entidade and cc.FK_genero = g.id_genero and "
				   + "year(e.data_registo) = 2016";

		List<Paciente> lista = new ArrayList<Paciente>();
		try {
			con = Conexao.getConexao();
			PreparedStatement ps = con.prepareStatement(sql);
			ResultSet rs = ps.executeQuery();
			while(rs.next()){
				Paciente p = new Paciente();
				p.setDescricao(rs.getString("tipo"));
				p.setQuantidade(rs.getInt("qtd"));
				p.setMasculino(rs.getInt("masculino"));
				p.setPerct_msc(rs.getDouble("percet_m"));
				p.setFeminino(rs.getInt("feminino"));
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
	
	public List<Paciente> buscarHistoricoAtendidosUEEmAnos(int ano){
		String sql = "select 'Urgencia' as tipo, count(u.id_urgencia) as 'qtd', COUNT(CASE WHEN g.id_genero = 1 THEN 0 END) AS 'masculino',"
				   + "CAST(COUNT(CASE WHEN g.id_genero = 1 THEN 0 END) * 100 / count(u.id_urgencia) AS DECIMAL(10, 1)) as 'percet_m',"
				   + "COUNT(CASE WHEN g.id_genero = 2 THEN 0 END) AS 'feminino', CAST(COUNT(CASE WHEN g.id_genero = 2 THEN 0 END) * 100 / count(u.id_urgencia)AS DECIMAL(10, 1)) as 'percet_f'"
				   + "from tblurgencia u, tblgenero g, tblcidadao cc where  u.FK_paciente = cc.FK_entidade and cc.FK_genero = g.id_genero and "
				   + "year(u.data_registo) = ? "
				   + "UNION "
				   + "select 'Emergencia' , count(e.id_emergencia) as 'qtd', COUNT(CASE WHEN g.id_genero = 1 THEN 0 END) AS 'masculino',"
				   + "CAST(COUNT(CASE WHEN g.id_genero = 1 THEN 0 END) * 100 / count(e.id_emergencia) AS DECIMAL(10, 1)) as 'percet_m',"
				   + "COUNT(CASE WHEN g.id_genero = 2 THEN 0 END) AS 'feminino',CAST(COUNT(CASE WHEN g.id_genero = 2 THEN 0 END) * 100 / count(e.id_emergencia)AS DECIMAL(10, 1)) as 'percet_f'"
				   + "from tblemergencia e, tblgenero g, tblcidadao cc where e.FK_paciente = cc.FK_entidade and cc.FK_genero = g.id_genero and "
				   + "year(e.data_registo) = ?";

		List<Paciente> lista = new ArrayList<Paciente>();
		try {
			con = Conexao.getConexao();
			PreparedStatement ps = con.prepareStatement(sql);
			ps.setInt(1, ano);
			ps.setInt(2, ano);
			ResultSet rs = ps.executeQuery();
			while(rs.next()){
				Paciente p = new Paciente();
				p.setDescricao(rs.getString("tipo"));
				p.setQuantidade(rs.getInt("qtd"));
				p.setMasculino(rs.getInt("masculino"));
				p.setPerct_msc(rs.getDouble("percet_m"));
				p.setFeminino(rs.getInt("feminino"));
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

	public List<Paciente> buscarHistoricoAtendidosUEEmIdades(int idade1,int idade2){
		String sql = "select 'Urgencia' as tipo, count(u.id_urgencia) as 'qtd', COUNT(CASE WHEN g.id_genero = 1 THEN 0 END) AS 'masculino',"
				   + "CAST(COUNT(CASE WHEN g.id_genero = 1 THEN 0 END) * 100 / count(u.id_urgencia) AS DECIMAL(10, 1)) as 'percet_m',"
				   + "COUNT(CASE WHEN g.id_genero = 2 THEN 0 END) AS 'feminino', CAST(COUNT(CASE WHEN g.id_genero = 2 THEN 0 END) * 100 / count(u.id_urgencia)AS DECIMAL(10, 1)) as 'percet_f'"
				   + "from tblurgencia u, tblgenero g, tblcidadao cc where  u.FK_paciente = cc.FK_entidade and cc.FK_genero = g.id_genero and "
				   + "year(from_days((to_days(now()) - to_days(cc.data_nascimento)))) between ? and ? "
				   + "UNION "
				   + "select 'Emergencia' , count(e.id_emergencia) as 'qtd', COUNT(CASE WHEN g.id_genero = 1 THEN 0 END) AS 'masculino',"
				   + "CAST(COUNT(CASE WHEN g.id_genero = 1 THEN 0 END) * 100 / count(e.id_emergencia) AS DECIMAL(10, 1)) as 'percet_m',"
				   + "COUNT(CASE WHEN g.id_genero = 2 THEN 0 END) AS 'feminino',CAST(COUNT(CASE WHEN g.id_genero = 2 THEN 0 END) * 100 / count(e.id_emergencia)AS DECIMAL(10, 1)) as 'percet_f'"
				   + "from tblemergencia e, tblgenero g, tblcidadao cc where e.FK_paciente = cc.FK_entidade and cc.FK_genero = g.id_genero and "
				   + "year(from_days((to_days(now()) - to_days(cc.data_nascimento)))) between ? and ?";

		List<Paciente> lista = new ArrayList<Paciente>();
		try {
			con = Conexao.getConexao();
			PreparedStatement ps = con.prepareStatement(sql);
			ps.setInt(1, idade1);
			ps.setInt(2, idade2);
			ps.setInt(3, idade1);
			ps.setInt(4, idade2);
			ResultSet rs = ps.executeQuery();
			while(rs.next()){
				Paciente p = new Paciente();
				p.setDescricao(rs.getString("tipo"));
				p.setQuantidade(rs.getInt("qtd"));
				p.setMasculino(rs.getInt("masculino"));
				p.setPerct_msc(rs.getDouble("percet_m"));
				p.setFeminino(rs.getInt("feminino"));
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

	public List<Paciente> buscarHistoricoAtendidosUEEmAnosIdades(int ano,int idade1,int idade2){
		String sql = "select 'Urgencia' as tipo, count(u.id_urgencia) as 'qtd', COUNT(CASE WHEN g.id_genero = 1 THEN 0 END) AS 'masculino',"
				   + "CAST(COUNT(CASE WHEN g.id_genero = 1 THEN 0 END) * 100 / count(u.id_urgencia) AS DECIMAL(10, 1)) as 'percet_m',"
				   + "COUNT(CASE WHEN g.id_genero = 2 THEN 0 END) AS 'feminino', CAST(COUNT(CASE WHEN g.id_genero = 2 THEN 0 END) * 100 / count(u.id_urgencia)AS DECIMAL(10, 1)) as 'percet_f'"
				   + "from tblurgencia u, tblgenero g, tblcidadao cc where  u.FK_paciente = cc.FK_entidade and cc.FK_genero = g.id_genero and "
				   + "year(u.data_registo) = ? and year(from_days((to_days(now()) - to_days(cc.data_nascimento)))) between ? and ? "
				   + "UNION "
				   + "select 'Emergencia' , count(e.id_emergencia) as 'qtd', COUNT(CASE WHEN g.id_genero = 1 THEN 0 END) AS 'masculino',"
				   + "CAST(COUNT(CASE WHEN g.id_genero = 1 THEN 0 END) * 100 / count(e.id_emergencia) AS DECIMAL(10, 1)) as 'percet_m',"
				   + "COUNT(CASE WHEN g.id_genero = 2 THEN 0 END) AS 'feminino',CAST(COUNT(CASE WHEN g.id_genero = 2 THEN 0 END) * 100 / count(e.id_emergencia)AS DECIMAL(10, 1)) as 'percet_f'"
				   + "from tblemergencia e, tblgenero g, tblcidadao cc where e.FK_paciente = cc.FK_entidade and cc.FK_genero = g.id_genero and "
				   + "year(e.data_registo) = ? and year(from_days((to_days(now()) - to_days(cc.data_nascimento)))) between ? and ?";

		List<Paciente> lista = new ArrayList<Paciente>();
		try {
			con = Conexao.getConexao();
			PreparedStatement ps = con.prepareStatement(sql);
			ps.setInt(1, ano);
			ps.setInt(2, idade1);
			ps.setInt(3, idade2);
			ps.setInt(4, ano);
			ps.setInt(5, idade1);
			ps.setInt(6, idade2);
			ResultSet rs = ps.executeQuery();
			while(rs.next()){
				Paciente p = new Paciente();
				p.setDescricao(rs.getString("tipo"));
				p.setQuantidade(rs.getInt("qtd"));
				p.setMasculino(rs.getInt("masculino"));
				p.setPerct_msc(rs.getDouble("percet_m"));
				p.setFeminino(rs.getInt("feminino"));
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

	public List<Paciente> buscarHistoricoAtendidosUEEmAnosIdades(int ano,int mes,int idade1,int idade2){
		String sql = "select 'Urgencia' as tipo, count(u.id_urgencia) as 'qtd', COUNT(CASE WHEN g.id_genero = 1 THEN 0 END) AS 'masculino',"
				   + "CAST(COUNT(CASE WHEN g.id_genero = 1 THEN 0 END) * 100 / count(u.id_urgencia) AS DECIMAL(10, 1)) as 'percet_m',"
				   + "COUNT(CASE WHEN g.id_genero = 2 THEN 0 END) AS 'feminino', CAST(COUNT(CASE WHEN g.id_genero = 2 THEN 0 END) * 100 / count(u.id_urgencia)AS DECIMAL(10, 1)) as 'percet_f'"
				   + "from tblurgencia u, tblgenero g, tblcidadao cc where  u.FK_paciente = cc.FK_entidade and cc.FK_genero = g.id_genero and "
				   + "year(u.data_registo) = ? and month(u.data_registo) = ? and year(from_days((to_days(now()) - to_days(cc.data_nascimento)))) between ? and ? "
				   + "UNION "
				   + "select 'Emergencia' , count(e.id_emergencia) as 'qtd', COUNT(CASE WHEN g.id_genero = 1 THEN 0 END) AS 'masculino',"
				   + "CAST(COUNT(CASE WHEN g.id_genero = 1 THEN 0 END) * 100 / count(e.id_emergencia) AS DECIMAL(10, 1)) as 'percet_m',"
				   + "COUNT(CASE WHEN g.id_genero = 2 THEN 0 END) AS 'feminino',CAST(COUNT(CASE WHEN g.id_genero = 2 THEN 0 END) * 100 / count(e.id_emergencia)AS DECIMAL(10, 1)) as 'percet_f'"
				   + "from tblemergencia e, tblgenero g, tblcidadao cc where e.FK_paciente = cc.FK_entidade and cc.FK_genero = g.id_genero and "
				   + "year(e.data_registo) = ? month(e.data_registo) = ? and year(from_days((to_days(now()) - to_days(cc.data_nascimento)))) between ? and ?";

		List<Paciente> lista = new ArrayList<Paciente>();
		try {
			con = Conexao.getConexao();
			PreparedStatement ps = con.prepareStatement(sql);
			ps.setInt(1, ano);
			ps.setInt(2, mes);
			ps.setInt(3, idade1);
			ps.setInt(4, idade2);
			ps.setInt(5, ano);
			ps.setInt(6, mes);
			ps.setInt(7, idade1);
			ps.setInt(8, idade2);
			ResultSet rs = ps.executeQuery();
			while(rs.next()){
				Paciente p = new Paciente();
				p.setDescricao(rs.getString("tipo"));
				p.setQuantidade(rs.getInt("qtd"));
				p.setMasculino(rs.getInt("masculino"));
				p.setPerct_msc(rs.getDouble("percet_m"));
				p.setFeminino(rs.getInt("feminino"));
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

	public List<Paciente> buscarHistoricoAtendidosUEEmAnosMesDiasIdades(int ano,int mes,int dia1,int dia2,int idade1,int idade2){
		String sql = "select 'Urgencia' as tipo, count(u.id_urgencia) as 'qtd', COUNT(CASE WHEN g.id_genero = 1 THEN 0 END) AS 'masculino',"
				   + "CAST(COUNT(CASE WHEN g.id_genero = 1 THEN 0 END) * 100 / count(u.id_urgencia) AS DECIMAL(10, 1)) as 'percet_m',"
				   + "COUNT(CASE WHEN g.id_genero = 2 THEN 0 END) AS 'feminino', CAST(COUNT(CASE WHEN g.id_genero = 2 THEN 0 END) * 100 / count(u.id_urgencia)AS DECIMAL(10, 1)) as 'percet_f'"
				   + "from tblurgencia u, tblgenero g, tblcidadao cc where  u.FK_paciente = cc.FK_entidade and cc.FK_genero = g.id_genero and "
				   + "year(u.data_registo) = ? and month(u.data_registo) = ? and day(u.data_registo) between ? and ? and year(from_days((to_days(now()) - to_days(cc.data_nascimento)))) between ? and ? "
				   + "UNION "
				   + "select 'Emergencia' , count(e.id_emergencia) as 'qtd', COUNT(CASE WHEN g.id_genero = 1 THEN 0 END) AS 'masculino',"
				   + "CAST(COUNT(CASE WHEN g.id_genero = 1 THEN 0 END) * 100 / count(e.id_emergencia) AS DECIMAL(10, 1)) as 'percet_m',"
				   + "COUNT(CASE WHEN g.id_genero = 2 THEN 0 END) AS 'feminino',CAST(COUNT(CASE WHEN g.id_genero = 2 THEN 0 END) * 100 / count(e.id_emergencia)AS DECIMAL(10, 1)) as 'percet_f'"
				   + "from tblemergencia e, tblgenero g, tblcidadao cc where e.FK_paciente = cc.FK_entidade and cc.FK_genero = g.id_genero and "
				   + "year(e.data_registo) = ? month(e.data_registo) = ? and day(e.data_registo) between ? and ? and year(from_days((to_days(now()) - to_days(cc.data_nascimento)))) between ? and ?";

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
			ps.setInt(7, ano);
			ps.setInt(8, mes);
			ps.setInt(9, dia1);
			ps.setInt(10, dia2);
			ps.setInt(11, idade1);
			ps.setInt(12, idade2);
			ResultSet rs = ps.executeQuery();
			while(rs.next()){
				Paciente p = new Paciente();
				p.setDescricao(rs.getString("tipo"));
				p.setQuantidade(rs.getInt("qtd"));
				p.setMasculino(rs.getInt("masculino"));
				p.setPerct_msc(rs.getDouble("percet_m"));
				p.setFeminino(rs.getInt("feminino"));
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

	public List<Paciente> buscarHistoricoAtendidosUEEmAnosMes(int ano,int mes){
		String sql = "select 'Urgencia' as tipo, count(u.id_urgencia) as 'qtd', COUNT(CASE WHEN g.id_genero = 1 THEN 0 END) AS 'masculino',"
				   + "CAST(COUNT(CASE WHEN g.id_genero = 1 THEN 0 END) * 100 / count(u.id_urgencia) AS DECIMAL(10, 1)) as 'percet_m',"
				   + "COUNT(CASE WHEN g.id_genero = 2 THEN 0 END) AS 'feminino', CAST(COUNT(CASE WHEN g.id_genero = 2 THEN 0 END) * 100 / count(u.id_urgencia)AS DECIMAL(10, 1)) as 'percet_f'"
				   + "from tblurgencia u, tblgenero g, tblcidadao cc where  u.FK_paciente = cc.FK_entidade and cc.FK_genero = g.id_genero and "
				   + "year(u.data_registo) = ? and month(u.data_registo) = ? "
				   + "UNION "
				   + "select 'Emergencia' , count(e.id_emergencia) as 'qtd', COUNT(CASE WHEN g.id_genero = 1 THEN 0 END) AS 'masculino',"
				   + "CAST(COUNT(CASE WHEN g.id_genero = 1 THEN 0 END) * 100 / count(e.id_emergencia) AS DECIMAL(10, 1)) as 'percet_m',"
				   + "COUNT(CASE WHEN g.id_genero = 2 THEN 0 END) AS 'feminino',CAST(COUNT(CASE WHEN g.id_genero = 2 THEN 0 END) * 100 / count(e.id_emergencia)AS DECIMAL(10, 1)) as 'percet_f'"
				   + "from tblemergencia e, tblgenero g, tblcidadao cc where e.FK_paciente = cc.FK_entidade and cc.FK_genero = g.id_genero and "
				   + "year(e.data_registo) = ? and month(e.data_registo) = ? ";

		List<Paciente> lista = new ArrayList<Paciente>();
		try {
			con = Conexao.getConexao();
			PreparedStatement ps = con.prepareStatement(sql);
			ps.setInt(1, ano);
			ps.setInt(2, mes);
			ps.setInt(3, ano);
			ps.setInt(4, mes);
			ResultSet rs = ps.executeQuery();
			while(rs.next()){
				Paciente p = new Paciente();
				p.setDescricao(rs.getString("tipo"));
				p.setQuantidade(rs.getInt("qtd"));
				p.setMasculino(rs.getInt("masculino"));
				p.setPerct_msc(rs.getDouble("percet_m"));
				p.setFeminino(rs.getInt("feminino"));
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

	public List<Paciente> buscarHistoricoAtendidosUEEmAnoSeTrimeste(int ano,int aux){
		String sql = "";
		
		if(aux == 1){
			sql = "select 'Urgencia' as tipo, count(u.id_urgencia) as 'qtd', COUNT(CASE WHEN g.id_genero = 1 THEN 0 END) AS 'masculino',"
					   + "CAST(COUNT(CASE WHEN g.id_genero = 1 THEN 0 END) * 100 / count(u.id_urgencia) AS DECIMAL(10, 1)) as 'percet_m',"
					   + "COUNT(CASE WHEN g.id_genero = 2 THEN 0 END) AS 'feminino', CAST(COUNT(CASE WHEN g.id_genero = 2 THEN 0 END) * 100 / count(u.id_urgencia)AS DECIMAL(10, 1)) as 'percet_f'"
					   + "from tblurgencia u, tblgenero g, tblcidadao cc where  u.FK_paciente = cc.FK_entidade and cc.FK_genero = g.id_genero and "
					   + "year(u.data_registo) = ? and month(u.data_registo) between 1 and 3 "
					   + "UNION "
					   + "select 'Emergencia' , count(e.id_emergencia) as 'qtd', COUNT(CASE WHEN g.id_genero = 1 THEN 0 END) AS 'masculino',"
					   + "CAST(COUNT(CASE WHEN g.id_genero = 1 THEN 0 END) * 100 / count(e.id_emergencia) AS DECIMAL(10, 1)) as 'percet_m',"
					   + "COUNT(CASE WHEN g.id_genero = 2 THEN 0 END) AS 'feminino',CAST(COUNT(CASE WHEN g.id_genero = 2 THEN 0 END) * 100 / count(e.id_emergencia)AS DECIMAL(10, 1)) as 'percet_f'"
					   + "from tblemergencia e, tblgenero g, tblcidadao cc where e.FK_paciente = cc.FK_entidade and cc.FK_genero = g.id_genero and "
					   + "year(e.data_registo) = ? and month(e.data_registo) between 1 and 3 ";
		}
		if(aux == 2){
			sql = "select 'Urgencia' as tipo, count(u.id_urgencia) as 'qtd', COUNT(CASE WHEN g.id_genero = 1 THEN 0 END) AS 'masculino',"
					   + "CAST(COUNT(CASE WHEN g.id_genero = 1 THEN 0 END) * 100 / count(u.id_urgencia) AS DECIMAL(10, 1)) as 'percet_m',"
					   + "COUNT(CASE WHEN g.id_genero = 2 THEN 0 END) AS 'feminino', CAST(COUNT(CASE WHEN g.id_genero = 2 THEN 0 END) * 100 / count(u.id_urgencia)AS DECIMAL(10, 1)) as 'percet_f'"
					   + "from tblurgencia u, tblgenero g, tblcidadao cc where  u.FK_paciente = cc.FK_entidade and cc.FK_genero = g.id_genero and "
					   + "year(u.data_registo) = ? and month(u.data_registo) between 4 and 6 "
					   + "UNION "
					   + "select 'Emergencia' , count(e.id_emergencia) as 'qtd', COUNT(CASE WHEN g.id_genero = 1 THEN 0 END) AS 'masculino',"
					   + "CAST(COUNT(CASE WHEN g.id_genero = 1 THEN 0 END) * 100 / count(e.id_emergencia) AS DECIMAL(10, 1)) as 'percet_m',"
					   + "COUNT(CASE WHEN g.id_genero = 2 THEN 0 END) AS 'feminino',CAST(COUNT(CASE WHEN g.id_genero = 2 THEN 0 END) * 100 / count(e.id_emergencia)AS DECIMAL(10, 1)) as 'percet_f'"
					   + "from tblemergencia e, tblgenero g, tblcidadao cc where e.FK_paciente = cc.FK_entidade and cc.FK_genero = g.id_genero and "
					   + "year(e.data_registo) = ? and month(e.data_registo) between 4 and 6 ";
		}
		if(aux == 3){
			sql = "select 'Urgencia' as tipo, count(u.id_urgencia) as 'qtd', COUNT(CASE WHEN g.id_genero = 1 THEN 0 END) AS 'masculino',"
					   + "CAST(COUNT(CASE WHEN g.id_genero = 1 THEN 0 END) * 100 / count(u.id_urgencia) AS DECIMAL(10, 1)) as 'percet_m',"
					   + "COUNT(CASE WHEN g.id_genero = 2 THEN 0 END) AS 'feminino', CAST(COUNT(CASE WHEN g.id_genero = 2 THEN 0 END) * 100 / count(u.id_urgencia)AS DECIMAL(10, 1)) as 'percet_f'"
					   + "from tblurgencia u, tblgenero g, tblcidadao cc where  u.FK_paciente = cc.FK_entidade and cc.FK_genero = g.id_genero and "
					   + "year(u.data_registo) = ? and month(u.data_registo) between 7 and 9 "
					   + "UNION "
					   + "select 'Emergencia' , count(e.id_emergencia) as 'qtd', COUNT(CASE WHEN g.id_genero = 1 THEN 0 END) AS 'masculino',"
					   + "CAST(COUNT(CASE WHEN g.id_genero = 1 THEN 0 END) * 100 / count(e.id_emergencia) AS DECIMAL(10, 1)) as 'percet_m',"
					   + "COUNT(CASE WHEN g.id_genero = 2 THEN 0 END) AS 'feminino',CAST(COUNT(CASE WHEN g.id_genero = 2 THEN 0 END) * 100 / count(e.id_emergencia)AS DECIMAL(10, 1)) as 'percet_f'"
					   + "from tblemergencia e, tblgenero g, tblcidadao cc where e.FK_paciente = cc.FK_entidade and cc.FK_genero = g.id_genero and "
					   + "year(e.data_registo) = ? and month(e.data_registo) between 7 and 9 ";
		}
		if(aux == 4){
			sql = "select 'Urgencia' as tipo, count(u.id_urgencia) as 'qtd', COUNT(CASE WHEN g.id_genero = 1 THEN 0 END) AS 'masculino',"
					   + "CAST(COUNT(CASE WHEN g.id_genero = 1 THEN 0 END) * 100 / count(u.id_urgencia) AS DECIMAL(10, 1)) as 'percet_m',"
					   + "COUNT(CASE WHEN g.id_genero = 2 THEN 0 END) AS 'feminino', CAST(COUNT(CASE WHEN g.id_genero = 2 THEN 0 END) * 100 / count(u.id_urgencia)AS DECIMAL(10, 1)) as 'percet_f'"
					   + "from tblurgencia u, tblgenero g, tblcidadao cc where  u.FK_paciente = cc.FK_entidade and cc.FK_genero = g.id_genero and "
					   + "year(u.data_registo) = ? and month(u.data_registo) between 10 and 12 "
					   + "UNION "
					   + "select 'Emergencia' , count(e.id_emergencia) as 'qtd', COUNT(CASE WHEN g.id_genero = 1 THEN 0 END) AS 'masculino',"
					   + "CAST(COUNT(CASE WHEN g.id_genero = 1 THEN 0 END) * 100 / count(e.id_emergencia) AS DECIMAL(10, 1)) as 'percet_m',"
					   + "COUNT(CASE WHEN g.id_genero = 2 THEN 0 END) AS 'feminino',CAST(COUNT(CASE WHEN g.id_genero = 2 THEN 0 END) * 100 / count(e.id_emergencia)AS DECIMAL(10, 1)) as 'percet_f'"
					   + "from tblemergencia e, tblgenero g, tblcidadao cc where e.FK_paciente = cc.FK_entidade and cc.FK_genero = g.id_genero and "
					   + "year(e.data_registo) = ? and month(e.data_registo) between 10 and 12 ";
		}
		if(aux == 5){
			sql = "select 'Urgencia' as tipo, count(u.id_urgencia) as 'qtd', COUNT(CASE WHEN g.id_genero = 1 THEN 0 END) AS 'masculino',"
					   + "CAST(COUNT(CASE WHEN g.id_genero = 1 THEN 0 END) * 100 / count(u.id_urgencia) AS DECIMAL(10, 1)) as 'percet_m',"
					   + "COUNT(CASE WHEN g.id_genero = 2 THEN 0 END) AS 'feminino', CAST(COUNT(CASE WHEN g.id_genero = 2 THEN 0 END) * 100 / count(u.id_urgencia)AS DECIMAL(10, 1)) as 'percet_f'"
					   + "from tblurgencia u, tblgenero g, tblcidadao cc where  u.FK_paciente = cc.FK_entidade and cc.FK_genero = g.id_genero and "
					   + "year(u.data_registo) = ? and month(u.data_registo) between 1 and 6 "
					   + "UNION "
					   + "select 'Emergencia' , count(e.id_emergencia) as 'qtd', COUNT(CASE WHEN g.id_genero = 1 THEN 0 END) AS 'masculino',"
					   + "CAST(COUNT(CASE WHEN g.id_genero = 1 THEN 0 END) * 100 / count(e.id_emergencia) AS DECIMAL(10, 1)) as 'percet_m',"
					   + "COUNT(CASE WHEN g.id_genero = 2 THEN 0 END) AS 'feminino',CAST(COUNT(CASE WHEN g.id_genero = 2 THEN 0 END) * 100 / count(e.id_emergencia)AS DECIMAL(10, 1)) as 'percet_f'"
					   + "from tblemergencia e, tblgenero g, tblcidadao cc where e.FK_paciente = cc.FK_entidade and cc.FK_genero = g.id_genero and "
					   + "year(e.data_registo) = ? and month(e.data_registo) between 1 and 6 ";
		}
		if(aux == 6){
			sql = "select 'Urgencia' as tipo, count(u.id_urgencia) as 'qtd', COUNT(CASE WHEN g.id_genero = 1 THEN 0 END) AS 'masculino',"
					   + "CAST(COUNT(CASE WHEN g.id_genero = 1 THEN 0 END) * 100 / count(u.id_urgencia) AS DECIMAL(10, 1)) as 'percet_m',"
					   + "COUNT(CASE WHEN g.id_genero = 2 THEN 0 END) AS 'feminino', CAST(COUNT(CASE WHEN g.id_genero = 2 THEN 0 END) * 100 / count(u.id_urgencia)AS DECIMAL(10, 1)) as 'percet_f'"
					   + "from tblurgencia u, tblgenero g, tblcidadao cc where  u.FK_paciente = cc.FK_entidade and cc.FK_genero = g.id_genero and "
					   + "year(u.data_registo) = ? and month(u.data_registo) between 7 and 12 "
					   + "UNION "
					   + "select 'Emergencia' , count(e.id_emergencia) as 'qtd', COUNT(CASE WHEN g.id_genero = 1 THEN 0 END) AS 'masculino',"
					   + "CAST(COUNT(CASE WHEN g.id_genero = 1 THEN 0 END) * 100 / count(e.id_emergencia) AS DECIMAL(10, 1)) as 'percet_m',"
					   + "COUNT(CASE WHEN g.id_genero = 2 THEN 0 END) AS 'feminino',CAST(COUNT(CASE WHEN g.id_genero = 2 THEN 0 END) * 100 / count(e.id_emergencia)AS DECIMAL(10, 1)) as 'percet_f'"
					   + "from tblemergencia e, tblgenero g, tblcidadao cc where e.FK_paciente = cc.FK_entidade and cc.FK_genero = g.id_genero and "
					   + "year(e.data_registo) = ? and month(e.data_registo) between 7 and 12 ";
		}

		List<Paciente> lista = new ArrayList<Paciente>();
		try {
			con = Conexao.getConexao();
			PreparedStatement ps = con.prepareStatement(sql);
			ps.setInt(1, ano);
			ps.setInt(2, ano);
			 
			ResultSet rs = ps.executeQuery();
			while(rs.next()){
				Paciente p = new Paciente();
				p.setDescricao(rs.getString("tipo"));
				p.setQuantidade(rs.getInt("qtd"));
				p.setMasculino(rs.getInt("masculino"));
				p.setPerct_msc(rs.getDouble("percet_m"));
				p.setFeminino(rs.getInt("feminino"));
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

	public List<Paciente> buscarHistoricoAtendidosUEEmAnosMesDias(int ano,int mes,int dia1,int dia2){
		String sql = "select 'Urgencia' as tipo, count(u.id_urgencia) as 'qtd', COUNT(CASE WHEN g.id_genero = 1 THEN 0 END) AS 'masculino',"
				   + "CAST(COUNT(CASE WHEN g.id_genero = 1 THEN 0 END) * 100 / count(u.id_urgencia) AS DECIMAL(10, 1)) as 'percet_m',"
				   + "COUNT(CASE WHEN g.id_genero = 2 THEN 0 END) AS 'feminino', CAST(COUNT(CASE WHEN g.id_genero = 2 THEN 0 END) * 100 / count(u.id_urgencia)AS DECIMAL(10, 1)) as 'percet_f'"
				   + "from tblurgencia u, tblgenero g, tblcidadao cc where  u.FK_paciente = cc.FK_entidade and cc.FK_genero = g.id_genero and "
				   + "year(u.data_registo) = ? and month(u.data_registo) = ? and day(u.data_registo) between ? and ? "
				   + "UNION "
				   + "select 'Emergencia' , count(e.id_emergencia) as 'qtd', COUNT(CASE WHEN g.id_genero = 1 THEN 0 END) AS 'masculino',"
				   + "CAST(COUNT(CASE WHEN g.id_genero = 1 THEN 0 END) * 100 / count(e.id_emergencia) AS DECIMAL(10, 1)) as 'percet_m',"
				   + "COUNT(CASE WHEN g.id_genero = 2 THEN 0 END) AS 'feminino',CAST(COUNT(CASE WHEN g.id_genero = 2 THEN 0 END) * 100 / count(e.id_emergencia)AS DECIMAL(10, 1)) as 'percet_f'"
				   + "from tblemergencia e, tblgenero g, tblcidadao cc where e.FK_paciente = cc.FK_entidade and cc.FK_genero = g.id_genero and "
				   + "year(e.data_registo) = ? and month(e.data_registo) = ? and day(e.data_registo) between ? and ?";

		List<Paciente> lista = new ArrayList<Paciente>();
		try {
			con = Conexao.getConexao();
			PreparedStatement ps = con.prepareStatement(sql);
			ps.setInt(1, ano);
			ps.setInt(2, mes);
			ps.setInt(3, dia1);
			ps.setInt(4, dia2);
			ps.setInt(5, ano);
			ps.setInt(6, mes);
			ps.setInt(7, dia1);
			ps.setInt(8, dia2);
			ResultSet rs = ps.executeQuery();
			while(rs.next()){
				Paciente p = new Paciente();
				p.setDescricao(rs.getString("tipo"));
				p.setQuantidade(rs.getInt("qtd"));
				p.setMasculino(rs.getInt("masculino"));
				p.setPerct_msc(rs.getDouble("percet_m"));
				p.setFeminino(rs.getInt("feminino"));
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
	
	public List<Paciente> buscarHistoricoDoencasUEDiario(){
		String sql = "SELECT doenca, FK_doenca, count(FK_doenca) as 'qtd', COUNT(CASE WHEN id_genero = 1 THEN 0 END) AS 'masculino',"
				   + "CAST(COUNT(CASE WHEN id_genero = 1 THEN 0 END) * 100 / count(FK_doenca)AS DECIMAL(10, 1)) as 'percet_m',"
				   + "COUNT(CASE WHEN id_genero = 2 THEN 0 END) AS 'feminino', CAST(COUNT(CASE WHEN id_genero = 2 THEN 0 END) * 100 / count(FK_doenca)AS DECIMAL(10, 1)) as 'percet_f'"
				   + "FROM vwdoencasfrequentesurgenciaemergencia GROUP BY FK_doenca";

		List<Paciente> lista = new ArrayList<Paciente>();
		try {
			con = Conexao.getConexao();
			PreparedStatement ps = con.prepareStatement(sql);
			ResultSet rs = ps.executeQuery();
			while(rs.next()){
				Paciente p = new Paciente();
				p.setDescricao(rs.getString("doenca"));
				p.setQuantidade(rs.getInt("qtd"));
				p.setMasculino(rs.getInt("masculino"));
				p.setPerct_msc(rs.getDouble("percet_m"));
				p.setFeminino(rs.getInt("feminino"));
				p.setPerct_fmn(rs.getDouble("percet_f"));
				p.setId(rs.getInt("FK_doenca"));
				lista.add(p);
			}
			ps.close();
			con.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return lista;
	}

	public List<Paciente> buscarHistoricoDoencasUEEmAnos(int ano){
		String sql = "SELECT doenca, FK_doenca, count(FK_doenca) as 'qtd', COUNT(CASE WHEN id_genero = 1 THEN 0 END) AS 'masculino',"
				   + "CAST(COUNT(CASE WHEN id_genero = 1 THEN 0 END) * 100 / count(FK_doenca)AS DECIMAL(10, 1)) as 'percet_m',"
				   + "COUNT(CASE WHEN id_genero = 2 THEN 0 END) AS 'feminino', CAST(COUNT(CASE WHEN id_genero = 2 THEN 0 END) * 100 / count(FK_doenca)AS DECIMAL(10, 1)) as 'percet_f'"
				   + "FROM vwdoencasfrequentesurgenciaemergencia "
				   + "WHERE  year(data_registo) = ? GROUP BY FK_doenca";

		List<Paciente> lista = new ArrayList<Paciente>();
		try {
			con = Conexao.getConexao();
			PreparedStatement ps = con.prepareStatement(sql);
			ps.setInt(1, ano);
			ResultSet rs = ps.executeQuery();
			while(rs.next()){
				Paciente p = new Paciente();
				p.setDescricao(rs.getString("doenca"));
				p.setQuantidade(rs.getInt("qtd"));
				p.setMasculino(rs.getInt("masculino"));
				p.setPerct_msc(rs.getDouble("percet_m"));
				p.setFeminino(rs.getInt("feminino"));
				p.setPerct_fmn(rs.getDouble("percet_f"));
				p.setId(rs.getInt("FK_doenca"));
				lista.add(p);
			}
			ps.close();
			con.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return lista;
	}

	public List<Paciente> buscarHistoricoDoencasUEEmAnosMes(int ano,int mes){
		String sql = "SELECT doenca, FK_doenca, count(FK_doenca) as 'qtd', COUNT(CASE WHEN id_genero = 1 THEN 0 END) AS 'masculino',"
				   + "CAST(COUNT(CASE WHEN id_genero = 1 THEN 0 END) * 100 / count(FK_doenca)AS DECIMAL(10, 1)) as 'percet_m',"
				   + "COUNT(CASE WHEN id_genero = 2 THEN 0 END) AS 'feminino', CAST(COUNT(CASE WHEN id_genero = 2 THEN 0 END) * 100 / count(FK_doenca)AS DECIMAL(10, 1)) as 'percet_f'"
				   + "FROM vwdoencasfrequentesurgenciaemergencia "
				   + "WHERE  year(data_registo) = ? and month(data_registo) = ? GROUP BY FK_doenca";

		List<Paciente> lista = new ArrayList<Paciente>();
		try {
			con = Conexao.getConexao();
			PreparedStatement ps = con.prepareStatement(sql);
			ps.setInt(1, ano);
			ps.setInt(2, mes);
			ResultSet rs = ps.executeQuery();
			while(rs.next()){
				Paciente p = new Paciente();
				p.setDescricao(rs.getString("doenca"));
				p.setQuantidade(rs.getInt("qtd"));
				p.setMasculino(rs.getInt("masculino"));
				p.setPerct_msc(rs.getDouble("percet_m"));
				p.setFeminino(rs.getInt("feminino"));
				p.setPerct_fmn(rs.getDouble("percet_f"));
				p.setId(rs.getInt("FK_doenca"));
				lista.add(p);
			}
			ps.close();
			con.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return lista;
	}

	public List<Paciente> buscarHistoricoDoencasUEEmAnosMesDias(int ano,int mes,int dia1,int dia2){
		String sql = "SELECT doenca, FK_doenca, count(FK_doenca) as 'qtd', COUNT(CASE WHEN id_genero = 1 THEN 0 END) AS 'masculino',"
				   + "CAST(COUNT(CASE WHEN id_genero = 1 THEN 0 END) * 100 / count(FK_doenca)AS DECIMAL(10, 1)) as 'percet_m',"
				   + "COUNT(CASE WHEN id_genero = 2 THEN 0 END) AS 'feminino', CAST(COUNT(CASE WHEN id_genero = 2 THEN 0 END) * 100 / count(FK_doenca)AS DECIMAL(10, 1)) as 'percet_f'"
				   + "FROM vwdoencasfrequentesurgenciaemergencia "
				   + "WHERE  year(data_registo) = ? and month(data_registo) = ? and day(data_registo) between ? and ? GROUP BY FK_doenca";

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
				p.setDescricao(rs.getString("doenca"));
				p.setQuantidade(rs.getInt("qtd"));
				p.setMasculino(rs.getInt("masculino"));
				p.setPerct_msc(rs.getDouble("percet_m"));
				p.setFeminino(rs.getInt("feminino"));
				p.setPerct_fmn(rs.getDouble("percet_f"));
				p.setId(rs.getInt("FK_doenca"));
				lista.add(p);
			}
			ps.close();
			con.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return lista;
	}

	public List<Paciente> buscarHistoricoDoencasUEEmAnosSeTrimestre(int ano,int aux){
		String sql = "";
		
		if(aux == 1){
			sql = "SELECT doenca, FK_doenca, count(FK_doenca) as 'qtd', COUNT(CASE WHEN id_genero = 1 THEN 0 END) AS 'masculino',"
					   + "CAST(COUNT(CASE WHEN id_genero = 1 THEN 0 END) * 100 / count(FK_doenca)AS DECIMAL(10, 1)) as 'percet_m',"
					   + "COUNT(CASE WHEN id_genero = 2 THEN 0 END) AS 'feminino', CAST(COUNT(CASE WHEN id_genero = 2 THEN 0 END) * 100 / count(FK_doenca)AS DECIMAL(10, 1)) as 'percet_f'"
					   + "FROM vwdoencasfrequentesurgenciaemergencia "
					   + "WHERE  year(data_registo) = ? and month(data_registo) between 1 and 3 GROUP BY FK_doenca";
		}
		
		if(aux == 2){
			sql = "SELECT doenca, FK_doenca, count(FK_doenca) as 'qtd', COUNT(CASE WHEN id_genero = 1 THEN 0 END) AS 'masculino',"
					   + "CAST(COUNT(CASE WHEN id_genero = 1 THEN 0 END) * 100 / count(FK_doenca)AS DECIMAL(10, 1)) as 'percet_m',"
					   + "COUNT(CASE WHEN id_genero = 2 THEN 0 END) AS 'feminino', CAST(COUNT(CASE WHEN id_genero = 2 THEN 0 END) * 100 / count(FK_doenca)AS DECIMAL(10, 1)) as 'percet_f'"
					   + "FROM vwdoencasfrequentesurgenciaemergencia "
					   + "WHERE  year(data_registo) = ? and month(data_registo) between 4 and 6 GROUP BY FK_doenca";
		}
		
		if(aux == 3){
			sql = "SELECT doenca, FK_doenca, count(FK_doenca) as 'qtd', COUNT(CASE WHEN id_genero = 1 THEN 0 END) AS 'masculino',"
					   + "CAST(COUNT(CASE WHEN id_genero = 1 THEN 0 END) * 100 / count(FK_doenca)AS DECIMAL(10, 1)) as 'percet_m',"
					   + "COUNT(CASE WHEN id_genero = 2 THEN 0 END) AS 'feminino', CAST(COUNT(CASE WHEN id_genero = 2 THEN 0 END) * 100 / count(FK_doenca)AS DECIMAL(10, 1)) as 'percet_f'"
					   + "FROM vwdoencasfrequentesurgenciaemergencia "
					   + "WHERE  year(data_registo) = ? and month(data_registo) between 7 and 9 GROUP BY FK_doenca";
		}
		
		if(aux == 4){
			sql = "SELECT doenca, FK_doenca, count(FK_doenca) as 'qtd', COUNT(CASE WHEN id_genero = 1 THEN 0 END) AS 'masculino',"
					   + "CAST(COUNT(CASE WHEN id_genero = 1 THEN 0 END) * 100 / count(FK_doenca)AS DECIMAL(10, 1)) as 'percet_m',"
					   + "COUNT(CASE WHEN id_genero = 2 THEN 0 END) AS 'feminino', CAST(COUNT(CASE WHEN id_genero = 2 THEN 0 END) * 100 / count(FK_doenca)AS DECIMAL(10, 1)) as 'percet_f'"
					   + "FROM vwdoencasfrequentesurgenciaemergencia "
					   + "WHERE  year(data_registo) = ? and month(data_registo) between 10 and 12 GROUP BY FK_doenca";
		}
		
		if(aux == 5){
			sql = "SELECT doenca, FK_doenca, count(FK_doenca) as 'qtd', COUNT(CASE WHEN id_genero = 1 THEN 0 END) AS 'masculino',"
					   + "CAST(COUNT(CASE WHEN id_genero = 1 THEN 0 END) * 100 / count(FK_doenca)AS DECIMAL(10, 1)) as 'percet_m',"
					   + "COUNT(CASE WHEN id_genero = 2 THEN 0 END) AS 'feminino', CAST(COUNT(CASE WHEN id_genero = 2 THEN 0 END) * 100 / count(FK_doenca)AS DECIMAL(10, 1)) as 'percet_f'"
					   + "FROM vwdoencasfrequentesurgenciaemergencia "
					   + "WHERE  year(data_registo) = ? and month(data_registo) between 1 and 6 GROUP BY FK_doenca";
		}
		
		if(aux == 6){
			sql = "SELECT doenca, FK_doenca, count(FK_doenca) as 'qtd', COUNT(CASE WHEN id_genero = 1 THEN 0 END) AS 'masculino',"
					   + "CAST(COUNT(CASE WHEN id_genero = 1 THEN 0 END) * 100 / count(FK_doenca)AS DECIMAL(10, 1)) as 'percet_m',"
					   + "COUNT(CASE WHEN id_genero = 2 THEN 0 END) AS 'feminino', CAST(COUNT(CASE WHEN id_genero = 2 THEN 0 END) * 100 / count(FK_doenca)AS DECIMAL(10, 1)) as 'percet_f'"
					   + "FROM vwdoencasfrequentesurgenciaemergencia "
					   + "WHERE  year(data_registo) = ? and month(data_registo) between 7 and 12 GROUP BY FK_doenca";
		}

		List<Paciente> lista = new ArrayList<Paciente>();
		try {
			con = Conexao.getConexao();
			PreparedStatement ps = con.prepareStatement(sql);
			ps.setInt(1, ano);
			 
			ResultSet rs = ps.executeQuery();
			while(rs.next()){
				Paciente p = new Paciente();
				p.setDescricao(rs.getString("doenca"));
				p.setQuantidade(rs.getInt("qtd"));
				p.setMasculino(rs.getInt("masculino"));
				p.setPerct_msc(rs.getDouble("percet_m"));
				p.setFeminino(rs.getInt("feminino"));
				p.setPerct_fmn(rs.getDouble("percet_f"));
				p.setId(rs.getInt("FK_doenca"));
				lista.add(p);
			}
			ps.close();
			con.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return lista;
	}
	
	public List<Paciente> buscarHistoricoDoencasUEEmAnosSeTrimestreIdades(int ano,int aux,int idade1,int idade2){
		String sql = "";
		
		if(aux == 1){
			sql = "SELECT doenca, FK_doenca, count(FK_doenca) as 'qtd', COUNT(CASE WHEN id_genero = 1 THEN 0 END) AS 'masculino',"
					   + "CAST(COUNT(CASE WHEN id_genero = 1 THEN 0 END) * 100 / count(FK_doenca)AS DECIMAL(10, 1)) as 'percet_m',"
					   + "COUNT(CASE WHEN id_genero = 2 THEN 0 END) AS 'feminino', CAST(COUNT(CASE WHEN id_genero = 2 THEN 0 END) * 100 / count(FK_doenca)AS DECIMAL(10, 1)) as 'percet_f'"
					   + "FROM vwdoencasfrequentesurgenciaemergencia "
					   + "WHERE  year(data_registo) = ? and month(data_registo) between 1 and 3 and idade between ? and ? GROUP BY FK_doenca";
		}
		
		if(aux == 2){
			sql = "SELECT doenca, FK_doenca, count(FK_doenca) as 'qtd', COUNT(CASE WHEN id_genero = 1 THEN 0 END) AS 'masculino',"
					   + "CAST(COUNT(CASE WHEN id_genero = 1 THEN 0 END) * 100 / count(FK_doenca)AS DECIMAL(10, 1)) as 'percet_m',"
					   + "COUNT(CASE WHEN id_genero = 2 THEN 0 END) AS 'feminino', CAST(COUNT(CASE WHEN id_genero = 2 THEN 0 END) * 100 / count(FK_doenca)AS DECIMAL(10, 1)) as 'percet_f'"
					   + "FROM vwdoencasfrequentesurgenciaemergencia "
					   + "WHERE  year(data_registo) = ? and month(data_registo) between 4 and 6 and idade between ? and ? GROUP BY FK_doenca";
		}
		
		if(aux == 3){
			sql = "SELECT doenca, FK_doenca, count(FK_doenca) as 'qtd', COUNT(CASE WHEN id_genero = 1 THEN 0 END) AS 'masculino',"
					   + "CAST(COUNT(CASE WHEN id_genero = 1 THEN 0 END) * 100 / count(FK_doenca)AS DECIMAL(10, 1)) as 'percet_m',"
					   + "COUNT(CASE WHEN id_genero = 2 THEN 0 END) AS 'feminino', CAST(COUNT(CASE WHEN id_genero = 2 THEN 0 END) * 100 / count(FK_doenca)AS DECIMAL(10, 1)) as 'percet_f'"
					   + "FROM vwdoencasfrequentesurgenciaemergencia "
					   + "WHERE  year(data_registo) = ? and month(data_registo) between 7 and 9 and idade between ? and ? GROUP BY FK_doenca";
		}
		
		if(aux == 4){
			sql = "SELECT doenca, FK_doenca, count(FK_doenca) as 'qtd', COUNT(CASE WHEN id_genero = 1 THEN 0 END) AS 'masculino',"
					   + "CAST(COUNT(CASE WHEN id_genero = 1 THEN 0 END) * 100 / count(FK_doenca)AS DECIMAL(10, 1)) as 'percet_m',"
					   + "COUNT(CASE WHEN id_genero = 2 THEN 0 END) AS 'feminino', CAST(COUNT(CASE WHEN id_genero = 2 THEN 0 END) * 100 / count(FK_doenca)AS DECIMAL(10, 1)) as 'percet_f'"
					   + "FROM vwdoencasfrequentesurgenciaemergencia "
					   + "WHERE  year(data_registo) = ? and month(data_registo) between 10 and 12 GROUP BY FK_doenca";
		}
		
		if(aux == 5){
			sql = "SELECT doenca, FK_doenca, count(FK_doenca) as 'qtd', COUNT(CASE WHEN id_genero = 1 THEN 0 END) AS 'masculino',"
					   + "CAST(COUNT(CASE WHEN id_genero = 1 THEN 0 END) * 100 / count(FK_doenca)AS DECIMAL(10, 1)) as 'percet_m',"
					   + "COUNT(CASE WHEN id_genero = 2 THEN 0 END) AS 'feminino', CAST(COUNT(CASE WHEN id_genero = 2 THEN 0 END) * 100 / count(FK_doenca)AS DECIMAL(10, 1)) as 'percet_f'"
					   + "FROM vwdoencasfrequentesurgenciaemergencia "
					   + "WHERE  year(data_registo) = ? and month(data_registo) between 1 and 6 and idade between ? and ? GROUP BY FK_doenca";
		}
		
		if(aux == 6){
			sql = "SELECT doenca, FK_doenca, count(FK_doenca) as 'qtd', COUNT(CASE WHEN id_genero = 1 THEN 0 END) AS 'masculino',"
					   + "CAST(COUNT(CASE WHEN id_genero = 1 THEN 0 END) * 100 / count(FK_doenca)AS DECIMAL(10, 1)) as 'percet_m',"
					   + "COUNT(CASE WHEN id_genero = 2 THEN 0 END) AS 'feminino', CAST(COUNT(CASE WHEN id_genero = 2 THEN 0 END) * 100 / count(FK_doenca)AS DECIMAL(10, 1)) as 'percet_f'"
					   + "FROM vwdoencasfrequentesurgenciaemergencia "
					   + "WHERE  year(data_registo) = ? and month(data_registo) between 7 and 12 and idade between ? and ? GROUP BY FK_doenca";
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
				p.setDescricao(rs.getString("doenca"));
				p.setQuantidade(rs.getInt("qtd"));
				p.setMasculino(rs.getInt("masculino"));
				p.setPerct_msc(rs.getDouble("percet_m"));
				p.setFeminino(rs.getInt("feminino"));
				p.setPerct_fmn(rs.getDouble("percet_f"));
				p.setId(rs.getInt("FK_doenca"));
				lista.add(p);
			}
			ps.close();
			con.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return lista;
	}
	
	public List<Paciente> buscarHistoricoDoencasUEEmIdades(int idade1,int idade2){
		String sql = "SELECT doenca, FK_doenca, count(FK_doenca) as 'qtd', COUNT(CASE WHEN id_genero = 1 THEN 0 END) AS 'masculino',"
				   + "CAST(COUNT(CASE WHEN id_genero = 1 THEN 0 END) * 100 / count(FK_doenca)AS DECIMAL(10, 1)) as 'percet_m',"
				   + "COUNT(CASE WHEN id_genero = 2 THEN 0 END) AS 'feminino', CAST(COUNT(CASE WHEN id_genero = 2 THEN 0 END) * 100 / count(FK_doenca)AS DECIMAL(10, 1)) as 'percet_f'"
				   + "FROM vwdoencasfrequentesurgenciaemergencia "
				   + "WHERE  idade between ? and ? GROUP BY FK_doenca";

		List<Paciente> lista = new ArrayList<Paciente>();
		try {
			con = Conexao.getConexao();
			PreparedStatement ps = con.prepareStatement(sql);
			ps.setInt(1, idade1);
			ps.setInt(2, idade2);
			ResultSet rs = ps.executeQuery();
			while(rs.next()){
				Paciente p = new Paciente();
				p.setDescricao(rs.getString("doenca"));
				p.setQuantidade(rs.getInt("qtd"));
				p.setMasculino(rs.getInt("masculino"));
				p.setPerct_msc(rs.getDouble("percet_m"));
				p.setFeminino(rs.getInt("feminino"));
				p.setPerct_fmn(rs.getDouble("percet_f"));
				p.setId(rs.getInt("FK_doenca"));
				lista.add(p);
			}
			ps.close();
			con.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return lista;
	}
	
	public List<Paciente> buscarHistoricoDoencasUEEmAnosIdades(int ano,int idade1,int idade2){
		String sql = "SELECT doenca, FK_doenca, count(FK_doenca) as 'qtd', COUNT(CASE WHEN id_genero = 1 THEN 0 END) AS 'masculino',"
				   + "CAST(COUNT(CASE WHEN id_genero = 1 THEN 0 END) * 100 / count(FK_doenca)AS DECIMAL(10, 1)) as 'percet_m',"
				   + "COUNT(CASE WHEN id_genero = 2 THEN 0 END) AS 'feminino', CAST(COUNT(CASE WHEN id_genero = 2 THEN 0 END) * 100 / count(FK_doenca)AS DECIMAL(10, 1)) as 'percet_f'"
				   + "FROM vwdoencasfrequentesurgenciaemergencia "
				   + "WHERE  year(data_registo) = ? and idade between ? and ? GROUP BY FK_doenca";

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
				p.setDescricao(rs.getString("doenca"));
				p.setQuantidade(rs.getInt("qtd"));
				p.setMasculino(rs.getInt("masculino"));
				p.setPerct_msc(rs.getDouble("percet_m"));
				p.setFeminino(rs.getInt("feminino"));
				p.setPerct_fmn(rs.getDouble("percet_f"));
				p.setId(rs.getInt("FK_doenca"));
				lista.add(p);
			}
			ps.close();
			con.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return lista;
	}
	
	public List<Paciente> buscarHistoricoGastosBEDiario(){
		String sql = "SELECT COUNT(CASE WHEN opcao = 1 THEN 0 END) AS 'qt_procedimentos', COUNT(CASE WHEN opcao = 2 THEN 0 END) AS 'qt_materiais',"
				   + "COUNT(CASE WHEN opcao = 3 THEN 0 END) AS 'qt_medicamentos' FROM vwhistoricogastosemergencia ";

		List<Paciente> lista = new ArrayList<Paciente>();
		try {
			con = Conexao.getConexao();
			PreparedStatement ps = con.prepareStatement(sql);
			ResultSet rs = ps.executeQuery();
			while(rs.next()){
				Paciente p = new Paciente();
				p.setProcedimento(rs.getInt("qt_procedimentos"));
				p.setMaterial(rs.getInt("qt_materiais"));
				p.setMedicamento(rs.getInt("qt_medicamentos"));
				lista.add(p);
			}
			ps.close();
			con.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return lista;
	}
	
	public List<Paciente> buscarHistoricoGastosBEEmAnos(int ano){
		String sql = "SELECT COUNT(CASE WHEN opcao = 1 THEN 0 END) AS 'qt_procedimentos', COUNT(CASE WHEN opcao = 2 THEN 0 END) AS 'qt_materiais',"
				   + "COUNT(CASE WHEN opcao = 3 THEN 0 END) AS 'qt_medicamentos' FROM vwhistoricogastosemergencia "
				   + "where  year(data_registo) = ?";

		List<Paciente> lista = new ArrayList<Paciente>();
		try {
			con = Conexao.getConexao();
			PreparedStatement ps = con.prepareStatement(sql);
			ps.setInt(1, ano);
			ResultSet rs = ps.executeQuery();
			while(rs.next()){
				Paciente p = new Paciente();
				p.setProcedimento(rs.getInt("qt_procedimentos"));
				p.setMaterial(rs.getInt("qt_materiais"));
				p.setMedicamento(rs.getInt("qt_medicamentos"));
				lista.add(p);
			}
			ps.close();
			con.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return lista;
	}
	
	public List<Paciente> buscarHistoricoGastosBEEmAnosMes(int ano,int mes){
		String sql = "SELECT COUNT(CASE WHEN opcao = 1 THEN 0 END) AS 'qt_procedimentos', COUNT(CASE WHEN opcao = 2 THEN 0 END) AS 'qt_materiais',"
				   + "COUNT(CASE WHEN opcao = 3 THEN 0 END) AS 'qt_medicamentos' FROM vwhistoricogastosemergencia "
				   + "where  year(data_registo) = ? and month(data_registo) = ?";

		List<Paciente> lista = new ArrayList<Paciente>();
		try {
			con = Conexao.getConexao();
			PreparedStatement ps = con.prepareStatement(sql);
			ps.setInt(1, ano);
			ps.setInt(2, mes);
			ResultSet rs = ps.executeQuery();
			while(rs.next()){
				Paciente p = new Paciente();
				p.setProcedimento(rs.getInt("qt_procedimentos"));
				p.setMaterial(rs.getInt("qt_materiais"));
				p.setMedicamento(rs.getInt("qt_medicamentos"));
				lista.add(p);
			}
			ps.close();
			con.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return lista;
	}
	
	public List<Paciente> buscarHistoricoGastosBEEmAnosMesIdades(int ano,int mes,int idade1,int idade2){
		String sql = "SELECT COUNT(CASE WHEN opcao = 1 THEN 0 END) AS 'qt_procedimentos', COUNT(CASE WHEN opcao = 2 THEN 0 END) AS 'qt_materiais',"
				   + "COUNT(CASE WHEN opcao = 3 THEN 0 END) AS 'qt_medicamentos' FROM vwhistoricogastosemergencia "
				   + "where  year(data_registo) = ? and month(data_registo) = ? and idade between ? and ?";

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
				p.setProcedimento(rs.getInt("qt_procedimentos"));
				p.setMaterial(rs.getInt("qt_materiais"));
				p.setMedicamento(rs.getInt("qt_medicamentos"));
				lista.add(p);
			}
			ps.close();
			con.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return lista;
	}
	
	public List<Paciente> buscarHistoricoGastosBEEmAnosMesDias(int ano,int mes,int dia1,int dia2){
		String sql = "SELECT COUNT(CASE WHEN opcao = 1 THEN 0 END) AS 'qt_procedimentos', COUNT(CASE WHEN opcao = 2 THEN 0 END) AS 'qt_materiais',"
				   + "COUNT(CASE WHEN opcao = 3 THEN 0 END) AS 'qt_medicamentos' FROM vwhistoricogastosemergencia "
				   + "where  year(data_registo) = ? and month(data_registo) = ? and day(data_registo) between ? and ?";

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
				p.setProcedimento(rs.getInt("qt_procedimentos"));
				p.setMaterial(rs.getInt("qt_materiais"));
				p.setMedicamento(rs.getInt("qt_medicamentos"));
				lista.add(p);
			}
			ps.close();
			con.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return lista;
	}

	public List<Paciente> buscarHistoricoGastosBEEmAnosSeTrimestre(int ano,int aux){
		String sql = "";
		
		if(aux == 1){
			sql = "SELECT COUNT(CASE WHEN opcao = 1 THEN 0 END) AS 'qt_procedimentos', COUNT(CASE WHEN opcao = 2 THEN 0 END) AS 'qt_materiais',"
					   + "COUNT(CASE WHEN opcao = 3 THEN 0 END) AS 'qt_medicamentos' FROM vwhistoricogastosemergencia "
					   + "where  year(data_registo) = ? and month(data_registo) between 1 and 3";
		}

		if(aux == 2){
			sql = "SELECT COUNT(CASE WHEN opcao = 1 THEN 0 END) AS 'qt_procedimentos', COUNT(CASE WHEN opcao = 2 THEN 0 END) AS 'qt_materiais',"
					   + "COUNT(CASE WHEN opcao = 3 THEN 0 END) AS 'qt_medicamentos' FROM vwhistoricogastosemergencia "
					   + "where  year(data_registo) = ? and month(data_registo) between 4 and 6";
		}
		
		if(aux == 3){
			sql = "SELECT COUNT(CASE WHEN opcao = 1 THEN 0 END) AS 'qt_procedimentos', COUNT(CASE WHEN opcao = 2 THEN 0 END) AS 'qt_materiais',"
					   + "COUNT(CASE WHEN opcao = 3 THEN 0 END) AS 'qt_medicamentos' FROM vwhistoricogastosemergencia "
					   + "where  year(data_registo) = ? and month(data_registo) between 7 and 9";
		}
		
		if(aux == 4){
			sql = "SELECT COUNT(CASE WHEN opcao = 1 THEN 0 END) AS 'qt_procedimentos', COUNT(CASE WHEN opcao = 2 THEN 0 END) AS 'qt_materiais',"
					   + "COUNT(CASE WHEN opcao = 3 THEN 0 END) AS 'qt_medicamentos' FROM vwhistoricogastosemergencia "
					   + "where  year(data_registo) = ? and month(data_registo) between 10 and 12";
		}
		
		if(aux == 5){
			sql = "SELECT COUNT(CASE WHEN opcao = 1 THEN 0 END) AS 'qt_procedimentos', COUNT(CASE WHEN opcao = 2 THEN 0 END) AS 'qt_materiais',"
					   + "COUNT(CASE WHEN opcao = 3 THEN 0 END) AS 'qt_medicamentos' FROM vwhistoricogastosemergencia "
					   + "where  year(data_registo) = ? and month(data_registo) between 1 and 6";
		}
		
		if(aux == 6){
			sql = "SELECT COUNT(CASE WHEN opcao = 1 THEN 0 END) AS 'qt_procedimentos', COUNT(CASE WHEN opcao = 2 THEN 0 END) AS 'qt_materiais',"
					   + "COUNT(CASE WHEN opcao = 3 THEN 0 END) AS 'qt_medicamentos' FROM vwhistoricogastosemergencia "
					   + "where  year(data_registo) = ? and month(data_registo) between 7 and 12";
		}
		List<Paciente> lista = new ArrayList<Paciente>();
		try {
			con = Conexao.getConexao();
			PreparedStatement ps = con.prepareStatement(sql);
			ps.setInt(1, ano);
			 
			ResultSet rs = ps.executeQuery();
			while(rs.next()){
				Paciente p = new Paciente();
				p.setProcedimento(rs.getInt("qt_procedimentos"));
				p.setMaterial(rs.getInt("qt_materiais"));
				p.setMedicamento(rs.getInt("qt_medicamentos"));
				lista.add(p);
			}
			ps.close();
			con.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return lista;
	}

	public List<Paciente> buscarHistoricoGastosBEEmAnosSeTrimestreIdades(int ano,int aux,int idade1,int idade2){
		String sql = "";
		
		if(aux == 1){
			sql = "SELECT COUNT(CASE WHEN opcao = 1 THEN 0 END) AS 'qt_procedimentos', COUNT(CASE WHEN opcao = 2 THEN 0 END) AS 'qt_materiais',"
					   + "COUNT(CASE WHEN opcao = 3 THEN 0 END) AS 'qt_medicamentos' FROM vwhistoricogastosemergencia "
					   + "where  year(data_registo) = ? and month(data_registo) between 1 and 3 and idade between ? and ?";
		}

		if(aux == 2){
			sql = "SELECT COUNT(CASE WHEN opcao = 1 THEN 0 END) AS 'qt_procedimentos', COUNT(CASE WHEN opcao = 2 THEN 0 END) AS 'qt_materiais',"
					   + "COUNT(CASE WHEN opcao = 3 THEN 0 END) AS 'qt_medicamentos' FROM vwhistoricogastosemergencia "
					   + "where  year(data_registo) = ? and month(data_registo) between 4 and 6 and idade between ? and ?";
		}
		
		if(aux == 3){
			sql = "SELECT COUNT(CASE WHEN opcao = 1 THEN 0 END) AS 'qt_procedimentos', COUNT(CASE WHEN opcao = 2 THEN 0 END) AS 'qt_materiais',"
					   + "COUNT(CASE WHEN opcao = 3 THEN 0 END) AS 'qt_medicamentos' FROM vwhistoricogastosemergencia "
					   + "where  year(data_registo) = ? and month(data_registo) between 7 and 9 and idade between ? and ?";
		}
		
		if(aux == 4){
			sql = "SELECT COUNT(CASE WHEN opcao = 1 THEN 0 END) AS 'qt_procedimentos', COUNT(CASE WHEN opcao = 2 THEN 0 END) AS 'qt_materiais',"
					   + "COUNT(CASE WHEN opcao = 3 THEN 0 END) AS 'qt_medicamentos' FROM vwhistoricogastosemergencia "
					   + "where  year(data_registo) = ? and month(data_registo) between 10 and 12 and idade between ? and ?";
		}
		
		if(aux == 5){
			sql = "SELECT COUNT(CASE WHEN opcao = 1 THEN 0 END) AS 'qt_procedimentos', COUNT(CASE WHEN opcao = 2 THEN 0 END) AS 'qt_materiais',"
					   + "COUNT(CASE WHEN opcao = 3 THEN 0 END) AS 'qt_medicamentos' FROM vwhistoricogastosemergencia "
					   + "where  year(data_registo) = ? and month(data_registo) between 1 and 6 and idade between ? and ?";
		}
		
		if(aux == 6){
			sql = "SELECT COUNT(CASE WHEN opcao = 1 THEN 0 END) AS 'qt_procedimentos', COUNT(CASE WHEN opcao = 2 THEN 0 END) AS 'qt_materiais',"
					   + "COUNT(CASE WHEN opcao = 3 THEN 0 END) AS 'qt_medicamentos' FROM vwhistoricogastosemergencia "
					   + "where  year(data_registo) = ? and month(data_registo) between 7 and 12 and idade between ? and ?";
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
				p.setProcedimento(rs.getInt("qt_procedimentos"));
				p.setMaterial(rs.getInt("qt_materiais"));
				p.setMedicamento(rs.getInt("qt_medicamentos"));
				lista.add(p);
			}
			ps.close();
			con.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return lista;
	}

	public List<Paciente> buscarHistoricoGastosBEEmIdades(int idade1,int idade2){
		String sql = "SELECT COUNT(CASE WHEN opcao = 1 THEN 0 END) AS 'qt_procedimentos', COUNT(CASE WHEN opcao = 2 THEN 0 END) AS 'qt_materiais',"
				   + "COUNT(CASE WHEN opcao = 3 THEN 0 END) AS 'qt_medicamentos' FROM vwhistoricogastosemergencia "
				   + "where idade between ? and ?";

		List<Paciente> lista = new ArrayList<Paciente>();
		try {
			con = Conexao.getConexao();
			PreparedStatement ps = con.prepareStatement(sql);
			ps.setInt(1, idade1);
			ps.setInt(2, idade2);
			ResultSet rs = ps.executeQuery();
			while(rs.next()){
				Paciente p = new Paciente();
				p.setProcedimento(rs.getInt("qt_procedimentos"));
				p.setMaterial(rs.getInt("qt_materiais"));
				p.setMedicamento(rs.getInt("qt_medicamentos"));
				lista.add(p);
			}
			ps.close();
			con.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return lista;
	}
	
	public List<Paciente> buscarHistoricoGastosBEEmAnoIdades(int ano,int idade1,int idade2){
		String sql = "SELECT COUNT(CASE WHEN opcao = 1 THEN 0 END) AS 'qt_procedimentos', COUNT(CASE WHEN opcao = 2 THEN 0 END) AS 'qt_materiais',"
				   + "COUNT(CASE WHEN opcao = 3 THEN 0 END) AS 'qt_medicamentos' FROM vwhistoricogastosemergencia "
				   + "where year(data_registo) = ? and idade between ? and ?";

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
				p.setProcedimento(rs.getInt("qt_procedimentos"));
				p.setMaterial(rs.getInt("qt_materiais"));
				p.setMedicamento(rs.getInt("qt_medicamentos"));
				lista.add(p);
			}
			ps.close();
			con.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return lista;
	}
	
	public List<Paciente> buscarHistoricoGastosPorItemDiario(int item){
		String sql = "";
		
		if(item == 1){
			sql = "SELECT  procedimento as descricao  , count(fk_procedimento) as qtd FROM vwemergenciagastosprocedimentos "
					   + "WHERE   data_registo = '2015-10-23' GROUP BY fk_procedimento";
		}
		if(item == 2){
			sql = "SELECT  medicamento as descricao  , count(fk_medicamento) as qtd FROM vwemergenciagastosmedicamentos "
					   + "WHERE   data_registo = '2015-10-23' GROUP BY fk_medicamento";
				}
		if(item == 3){
			sql = "SELECT  material as descricao  , count(fk_material) as qtd FROM vwemergenciagastosmateriais "
					   + "WHERE   data_registo = '2015-10-23' GROUP BY fk_material";
		}

		List<Paciente> lista = new ArrayList<Paciente>();
		try {
			con = Conexao.getConexao();
			PreparedStatement ps = con.prepareStatement(sql);
			 
			ResultSet rs = ps.executeQuery();
			while(rs.next()){
				Paciente p = new Paciente();
				p.setDescricao(rs.getString("descricao"));
				p.setQuantidade(rs.getInt("qtd"));
				lista.add(p);
			}
			ps.close();
			con.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return lista;
	}
	
	public List<Paciente> buscarHistoricoGastosPorItemEmAno(int item,int ano){
		String sql = "";
		
		if(item == 1){
			sql = "SELECT  procedimento as descricao  , count(fk_procedimento) as qtd FROM vwemergenciagastosprocedimentos "
					   + "WHERE   year(data_registo) = ? GROUP BY fk_procedimento";
		}
		if(item == 2){
			sql = "SELECT  medicamento as descricao  , count(fk_medicamento) as qtd FROM vwemergenciagastosmedicamentos "
					   + "WHERE   year(data_registo) = ? GROUP BY fk_medicamento";
				}
		if(item == 3){
			sql = "SELECT  material as descricao  , count(fk_material) as qtd FROM vwemergenciagastosmateriais "
					   + "WHERE   year(data_registo) = ? GROUP BY fk_material";
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
				lista.add(p);
			}
			ps.close();
			con.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return lista;
	}
	
	public List<Paciente> buscarHistoricoGastosPorItemEmAnoMes(int item,int ano,int mes){
		String sql = "";
		
		if(item == 1){
			sql = "SELECT  procedimento as descricao  , count(fk_procedimento) as qtd FROM vwemergenciagastosprocedimentos "
					   + "WHERE   year(data_registo) = ? and month(data_registo) = ? GROUP BY fk_procedimento";
		}
		if(item == 2){
			sql = "SELECT  medicamento as descricao  , count(fk_medicamento) as qtd FROM vwemergenciagastosmedicamentos "
					   + "WHERE   year(data_registo) = ? and month(data_registo) = ? GROUP BY fk_medicamento";
				}
		if(item == 3){
			sql = "SELECT  material as descricao  , count(fk_material) as qtd FROM vwemergenciagastosmateriais "
					   + "WHERE   year(data_registo) = ? and month(data_registo) = ? GROUP BY fk_material";
		}

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
				lista.add(p);
			}
			ps.close();
			con.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return lista;
	}

	public List<Paciente> buscarHistoricoGastosPorItemEmAnoMesDias(int item,int ano,int mes,int dia1,int dia2){
		String sql = "";
		
		if(item == 1){
			sql = "SELECT  procedimento as descricao  , count(fk_procedimento) as qtd FROM vwemergenciagastosprocedimentos "
					   + "WHERE   year(data_registo) = ? and month(data_registo) = ? and day(data_registo) between ? and ? GROUP BY fk_procedimento";
		}
		if(item == 2){
			sql = "SELECT  medicamento as descricao  , count(fk_medicamento) as qtd FROM vwemergenciagastosmedicamentos "
					   + "WHERE   year(data_registo) = ? and month(data_registo) = ? and day(data_registo) between ? and ? GROUP BY fk_medicamento";
				}
		if(item == 3){
			sql = "SELECT  material as descricao  , count(fk_material) as qtd FROM vwemergenciagastosmateriais "
					   + "WHERE   year(data_registo) = ? and month(data_registo) = ? and day(data_registo) between ? and ? GROUP BY fk_material";
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
			while(rs.next()){
				Paciente p = new Paciente();
				p.setDescricao(rs.getString("descricao"));
				p.setQuantidade(rs.getInt("qtd"));
				lista.add(p);
			}
			ps.close();
			con.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return lista;
	}

	public List<Paciente> buscarHistoricoGastosPorItemEmAnoSeTrimestre(int item,int ano,int aux){
		String sql = "";
		
		if(item == 1){
			if(aux == 1){
				sql = "SELECT  procedimento as descricao  , count(fk_procedimento) as qtd FROM vwemergenciagastosprocedimentos "
						   + "WHERE   year(data_registo) = ? and month(data_registo) between 1 and 3 GROUP BY fk_procedimento";
			}
			if(aux == 2){
				sql = "SELECT  procedimento as descricao  , count(fk_procedimento) as qtd FROM vwemergenciagastosprocedimentos "
						   + "WHERE   year(data_registo) = ? and month(data_registo) between 4 and 6 GROUP BY fk_procedimento";
			}
			if(aux == 3){
				sql = "SELECT  procedimento as descricao  , count(fk_procedimento) as qtd FROM vwemergenciagastosprocedimentos "
						   + "WHERE   year(data_registo) = ? and month(data_registo) between 7 and 9 GROUP BY fk_procedimento";
			}
			if(aux == 4){
				sql = "SELECT  procedimento as descricao  , count(fk_procedimento) as qtd FROM vwemergenciagastosprocedimentos "
						   + "WHERE   year(data_registo) = ? and month(data_registo) between 10 and 12 GROUP BY fk_procedimento";
			}
			if(aux == 5){
				sql = "SELECT  procedimento as descricao  , count(fk_procedimento) as qtd FROM vwemergenciagastosprocedimentos "
						   + "WHERE   year(data_registo) = ? and month(data_registo) between 1 and 6 GROUP BY fk_procedimento";
			}
			if(aux == 6){
				sql = "SELECT  procedimento as descricao  , count(fk_procedimento) as qtd FROM vwemergenciagastosprocedimentos "
						   + "WHERE   year(data_registo) = ? and month(data_registo) between 7 and 12 GROUP BY fk_procedimento";
			}
			
		}
		if(item == 2){
			if(aux == 1){
				sql = "SELECT  medicamento as descricao  , count(fk_medicamento) as qtd FROM vwemergenciagastosmedicamentos "
						   + "WHERE   year(data_registo) = ? and month(data_registo) between 1 and 3 GROUP BY fk_medicamento";
					}
			if(aux == 2){
				sql = "SELECT  medicamento as descricao  , count(fk_medicamento) as qtd FROM vwemergenciagastosmedicamentos "
						   + "WHERE   year(data_registo) = ? and month(data_registo) between 4 and 6 GROUP BY fk_medicamento";
					}
			if(aux == 3){
				sql = "SELECT  medicamento as descricao  , count(fk_medicamento) as qtd FROM vwemergenciagastosmedicamentos "
						   + "WHERE   year(data_registo) = ? and month(data_registo) between 7 and 9 GROUP BY fk_medicamento";
					}
			if(aux == 4){
				sql = "SELECT  medicamento as descricao  , count(fk_medicamento) as qtd FROM vwemergenciagastosmedicamentos "
						   + "WHERE   year(data_registo) = ? and month(data_registo) between 10 and 12 GROUP BY fk_medicamento";
					}
			if(aux == 5){
				sql = "SELECT  medicamento as descricao  , count(fk_medicamento) as qtd FROM vwemergenciagastosmedicamentos "
						   + "WHERE   year(data_registo) = ? and month(data_registo) between 1 and 6 GROUP BY fk_medicamento";
					}
			if(aux == 6){
				sql = "SELECT  medicamento as descricao  , count(fk_medicamento) as qtd FROM vwemergenciagastosmedicamentos "
						+ "WHERE   year(data_registo) = ? and month(data_registo) between 7 and 12 GROUP BY fk_medicamento";
			}
			}
		
		if(item == 3){
			if(aux == 1){
				sql = "SELECT  material as descricao  , count(fk_material) as qtd FROM vwemergenciagastosmateriais "
						   + "WHERE   year(data_registo) = ? and month(data_registo) between 1 and 3 GROUP BY fk_material";
			}
			if(aux == 2){
				sql = "SELECT  material as descricao  , count(fk_material) as qtd FROM vwemergenciagastosmateriais "
						   + "WHERE   year(data_registo) = ? and month(data_registo) between 4 and 6 GROUP BY fk_material";
			}
			if(aux == 3){
				sql = "SELECT  material as descricao  , count(fk_material) as qtd FROM vwemergenciagastosmateriais "
						   + "WHERE   year(data_registo) = ? and month(data_registo) between 7 and 9 GROUP BY fk_material";
			}
			if(aux == 4){
				sql = "SELECT  material as descricao  , count(fk_material) as qtd FROM vwemergenciagastosmateriais "
						   + "WHERE   year(data_registo) = ? and month(data_registo) between 10 and 12 GROUP BY fk_material";
			}
			if(aux == 5){
				sql = "SELECT  material as descricao  , count(fk_material) as qtd FROM vwemergenciagastosmateriais "
						   + "WHERE   year(data_registo) = ? and month(data_registo) between 1 and 6 GROUP BY fk_material";
			}
			if(aux == 6){
				sql = "SELECT  material as descricao  , count(fk_material) as qtd FROM vwemergenciagastosmateriais "
						   + "WHERE   year(data_registo) = ? and month(data_registo) between 7 and 12 GROUP BY fk_material";
			}
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
				lista.add(p);
			}
			ps.close();
			con.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return lista;
	}
	public List<Paciente> buscarHistoricoGastosPorItemEmIdade(int item,int idade1,int idade2){
		String sql = "";
		
		if(item == 1){
			sql = "SELECT  procedimento as descricao  , count(fk_procedimento) as qtd FROM vwemergenciagastosprocedimentos "
					   + "WHERE   idade between ? and ? GROUP BY fk_procedimento";
		}
		if(item == 2){
			sql = "SELECT  medicamento as descricao  , count(fk_medicamento) as qtd FROM vwemergenciagastosmedicamentos "
					   + "WHERE    idade between ? and ? GROUP BY fk_medicamento";
				}
		if(item == 3){
			sql = "SELECT  material as descricao  , count(fk_material) as qtd FROM vwemergenciagastosmateriais "
					   + "WHERE    idade between ? and ? GROUP BY fk_material";
		}

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
				lista.add(p);
			}
			ps.close();
			con.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return lista;
	}

	public List<Paciente> buscarHistoricoGastosPorItemEmAnoIdade(int item,int ano,int idade1,int idade2){
		String sql = "";
		
		if(item == 1){
			sql = "SELECT  procedimento as descricao  , count(fk_procedimento) as qtd FROM vwemergenciagastosprocedimentos "
					   + "WHERE   year(data_registo) = ? and idade between ? and ? GROUP BY fk_procedimento";
		}
		if(item == 2){
			sql = "SELECT  medicamento as descricao  , count(fk_medicamento) as qtd FROM vwemergenciagastosmedicamentos "
					   + "WHERE   year(data_registo) = ? and idade between ? and ? GROUP BY fk_medicamento";
				}
		if(item == 3){
			sql = "SELECT  material as descricao  , count(fk_material) as qtd FROM vwemergenciagastosmateriais "
					   + "WHERE   year(data_registo) = ? and idade between ? and ? GROUP BY fk_material";
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
