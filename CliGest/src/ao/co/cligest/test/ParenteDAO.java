package ao.co.cligest.test;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import ao.co.cligest.dao.Conexao;
import ao.co.cligest.entidades.Paciente;


public class ParenteDAO {
 
	Connection coneta;
	
	public List<Parente> getListaParente() {
		String sql = "SELECT * FROM vwtodasinformacoespaciente";
		List<Parente> lista = new ArrayList<Parente>();
		try {
			coneta = Conexao.getConexao();
			PreparedStatement ps = coneta.prepareStatement(sql);
			ResultSet rs = ps.executeQuery();
			while(rs.next()){
				Parente p = new Parente();
				p.setFK_entidade(rs.getInt("entidade"));
				p.setNome_parente(rs.getString("nome_parente"));
				p.setTelefone_parente(rs.getLong("telefone_parente"));
				lista.add(p);
			}
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		return lista;
	}
	
	public List<Parente> getListaParente(Date a, Date b) {
		String sql = "SELECT * FROM vwtodasinformacoespaciente where data_registo between ? and ?";
		List<Parente> lista = new ArrayList<Parente>();
		try {
			coneta = Conexao.getConexao();
			PreparedStatement ps = coneta.prepareStatement(sql);
			ps.setDate(1, a);
			ps.setDate(2, b);
			ResultSet rs = ps.executeQuery();
			while(rs.next()){
				Parente p = new Parente();
				p.setFK_entidade(rs.getInt("entidade"));
				p.setNome_parente(rs.getString("nome_parente"));
				p.setTelefone_parente(rs.getLong("telefone_parente"));
				lista.add(p);
			}
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		return lista;
	}
	
	public List<Paciente> getListaPaciente(Date a, Date b) {
		String sql = "SELECT * FROM vwtodasinformacoespaciente where data_registo between ? and ?";
		List<Paciente> lista = new ArrayList<Paciente>();
		try {
			coneta = Conexao.getConexao();
			PreparedStatement ps = coneta.prepareStatement(sql);
			ps.setDate(1, a);
			ps.setDate(2, b);
			ResultSet rs = ps.executeQuery();
			while(rs.next()){
				Paciente p = new Paciente();
				p.setFK_entidade(rs.getInt("entidade"));
				p.setNome_paciente(rs.getString("nome_parente"));


				
				
				
				lista.add(p);
			}
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		return lista;
	}
}
