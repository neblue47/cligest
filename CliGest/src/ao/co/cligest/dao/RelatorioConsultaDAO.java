package ao.co.cligest.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

import ao.co.cligest.entidades.Paciente;
import ao.co.cligest.entidades.Triagem;

public class RelatorioConsultaDAO extends ConsultaDAO{

	private Connection con ;
	private Formatando ft = new Formatando();
	
	public List<Paciente> getPacientesConsultados()
	{
		List<Paciente> lista = new ArrayList<Paciente>();
		String sql = "SELECT * FROM vwpacientesconsultados order by data_daconsulta limit 500";
		try
		{	
			 con = Conexao.getConexao();
			 PreparedStatement preparador = con.prepareStatement(sql);
			 ResultSet rs = preparador.executeQuery();
			 while(rs.next())
			 {
				 Paciente p = new Paciente();
				 p.setFK_paciente(rs.getInt("FK_paciente"));
				 p.setNumero_processo(rs.getString("NumeroProcesso"));
				 p.setNome(rs.getString("nome"));
				 p.setApelido(rs.getString("ultimo_nome"));
				 p.setNome_paciente(rs.getString("NomeCompleto"));
				 p.setIdade(rs.getDate("data_nascimento"));
				 lista.add(p);
			 }
			 preparador.close();
		}
		
		catch(SQLException er){
			throw new RuntimeException(er);
		}
		catch (Exception e) {	e.printStackTrace();	}
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
	
	public List<Paciente> getPacientesConsultados(String aux)
	{
		List<Paciente> lista = new ArrayList<Paciente>();
		String sql = "SELECT * FROM vwpacientesconsultados where NomeCompleto Like ? or numeroprocesso Like ? order by data_daconsulta ";
		try
		{	
			 con = Conexao.getConexao();
			 PreparedStatement preparador = con.prepareStatement(sql);
			 preparador.setString(1, aux+"%"); 
			 preparador.setString(2, aux);
			 ResultSet rs = preparador.executeQuery();
			 while(rs.next())
			 {
				 Paciente p = new Paciente();
				 p.setFK_paciente(rs.getInt("FK_paciente"));
				 p.setNumero_processo(rs.getString("NumeroProcesso"));
				 p.setNome(rs.getString("nome"));
				 p.setApelido(rs.getString("ultimo_nome"));
				 p.setNome_paciente(rs.getString("NomeCompleto"));
				 p.setIdade(rs.getDate("data_nascimento"));
				 lista.add(p);
			 }
			 preparador.close();
		}
		
		catch(SQLException er){
			throw new RuntimeException(er);
		}
		catch (Exception e) {	e.printStackTrace();	}
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
	
	public List<Paciente> getPacientesConsultadosExames(int cod_p)
	{
		List<Paciente> lista = new ArrayList<Paciente>();
		String sql = "SELECT * FROM vwexamesclinicosdatas where FK_paciente = ? group by data_daconsulta order by data_daconsulta desc";
		try
		{	
			 con = Conexao.getConexao();
			 PreparedStatement preparador = con.prepareStatement(sql);
			 preparador.setInt(1, cod_p);
			 ResultSet rs = preparador.executeQuery();
			 while(rs.next())
			 {
				 Paciente p = new Paciente();
				 p.setFK_paciente(rs.getInt("FK_paciente"));
				 p.setId(rs.getInt("id_consulta"));
				 Calendar data = Calendar.getInstance();
				 data.setTime(rs.getDate("data_daconsulta"));
				 p.setData_registo(data);
				 p.setHora_registo(rs.getString("hora_daconsulta"));
				 lista.add(p);
			 }
			 preparador.close();
		}
		
		catch(SQLException er){
			throw new RuntimeException(er);
		}
		catch (Exception e) {	e.printStackTrace();	}
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
	
	public List<Paciente> getPacientesConsultadosTratamRecom(int cod_p)
	{
		List<Paciente> lista = new ArrayList<Paciente>();
		String sql = "SELECT * FROM vwtratamentosrecomendas where FK_paciente = ? group by data_daconsulta order by data_daconsulta desc";
		try
		{	
			 con = Conexao.getConexao();
			 PreparedStatement preparador = con.prepareStatement(sql);
			 preparador.setInt(1, cod_p);
			 ResultSet rs = preparador.executeQuery();
			 while(rs.next())
			 {
				 Paciente p = new Paciente();
				 p.setFK_paciente(rs.getInt("FK_paciente"));
				 p.setId(rs.getInt("id_consulta"));
				 Calendar data = Calendar.getInstance();
				 data.setTime(rs.getDate("data_daconsulta"));
				 p.setData_registo(data);
				 p.setHora_registo(rs.getString("hora_daconsulta"));
				 lista.add(p);
			 }
			 preparador.close();
		}
		
		catch(SQLException er){
			throw new RuntimeException(er);
		}
		catch (Exception e) {	e.printStackTrace();	}
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
	
	public List<Paciente> getPacientesConsultadosReceituario(int cod_p)
	{
		List<Paciente> lista = new ArrayList<Paciente>();
		String sql = "SELECT * FROM vwreceituario where FK_paciente = ? group by data_daconsulta order by data_daconsulta desc";
		try
		{	
			 con = Conexao.getConexao();
			 PreparedStatement preparador = con.prepareStatement(sql);
			 preparador.setInt(1, cod_p);
			 ResultSet rs = preparador.executeQuery();
			 while(rs.next())
			 {
				 Paciente p = new Paciente();
				 p.setFK_paciente(rs.getInt("FK_paciente"));
				 p.setId(rs.getInt("id_consulta"));
				 Calendar data = Calendar.getInstance();
				 data.setTime(rs.getDate("data_daconsulta"));
				 p.setData_registo(data);
				 p.setHora_registo(rs.getString("hora_daconsulta"));
				 lista.add(p);
			 }
			 preparador.close();
		}
		
		catch(SQLException er){
			throw new RuntimeException(er);
		}
		catch (Exception e) {	e.printStackTrace();	}
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
	
	public List<Triagem> getPacientesReceituario(int cod_p)
	{
		List<Triagem> lista = new ArrayList<Triagem>();
		String sql = "SELECT * FROM vwreceituario where id_consulta = ? group by data_daconsulta order by data_daconsulta desc";
		try
		{	
			 con = Conexao.getConexao();
			 PreparedStatement preparador = con.prepareStatement(sql);
			 preparador.setInt(1, cod_p);
			 ResultSet rs = preparador.executeQuery();
			 while(rs.next())
			 {
				 Triagem p = new Triagem();
				 p.setFarmaco(rs.getString("nome_comercial"));
				 p.setQuantidade(rs.getInt("quantidade"));
				 p.setDosagem(rs.getString("dosagem"));
				 p.setPosologia(rs.getString("possologia"));
				 p.setVidAd(rs.getString("viaadministrar"));
				 lista.add(p); 
			 }
			 preparador.close();
		}
		catch(SQLException er){
			throw new RuntimeException(er);
		}
		catch (Exception e) {	e.printStackTrace();	}
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
	
	public List<Paciente> getPacientesNaConsulta(int cod_p)
	{
		List<Paciente> lista = new ArrayList<Paciente>();
		String sql = "SELECT * FROM tblconsulta where FK_paciente = ?   order by data_daconsulta desc";
		try
		{	
			 con = Conexao.getConexao();
			 PreparedStatement preparador = con.prepareStatement(sql);
			 preparador.setInt(1, cod_p);
			 ResultSet rs = preparador.executeQuery();
			 while(rs.next())
			 {
				 Paciente p = new Paciente();
				 p.setFK_paciente(rs.getInt("FK_paciente"));
				 p.setId(rs.getInt("id_consulta"));
				 Calendar data = Calendar.getInstance();
				 data.setTime(rs.getDate("data_daconsulta"));
				 p.setData_registo(data);
				 p.setHora_registo(rs.getString("hora_daconsulta"));
				 lista.add(p);
			 }
			 preparador.close();
		}
		catch(SQLException er){
			throw new RuntimeException(er);
		}
		catch (Exception e) {	e.printStackTrace();	}
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
	
	public List<Paciente> getJusticativosNaConsulta(int cod_p)
	{
		List<Paciente> lista = new ArrayList<Paciente>();
		String sql = "SELECT * FROM vwjustificadosnaconsulta where FK_paciente = ? group by data_daconsulta order by data_daconsulta desc";
		try
		{	
			 con = Conexao.getConexao();
			 PreparedStatement preparador = con.prepareStatement(sql);
			 preparador.setInt(1, cod_p);
			 ResultSet rs = preparador.executeQuery();
			 while(rs.next())
			 {
				 Paciente p = new Paciente();
				 p.setFK_paciente(rs.getInt("FK_paciente"));
				 p.setId(rs.getInt("id_consulta"));
				 Calendar data = Calendar.getInstance();
				 data.setTime(rs.getDate("data_daconsulta"));
				 p.setData_registo(data);
				 p.setHora_registo(rs.getString("hora_daconsulta"));
				 lista.add(p);
			 }
			 preparador.close();
		}
		catch(SQLException er){
			throw new RuntimeException(er);
		}
		catch (Exception e) {	e.printStackTrace();	}
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
	
	public Paciente getPacienteTratamRecom(int cod_c)
	{
		Paciente p = new Paciente();
		String sql = "SELECT * FROM vwtratamentosrecomendas where id_consulta = ? ";
		try
		{	
			 con = Conexao.getConexao();
			 PreparedStatement preparador = con.prepareStatement(sql);
			 preparador.setInt(1, cod_c);
			 ResultSet rs = preparador.executeQuery();
			 if(rs.next())
			 {
				 
				 p.setTratamento(rs.getString("descricao_tratamento"));
				 p.setRecomendacao(rs.getString("descricao_recomendacao")); 
				 
			 }
			 preparador.close();
			 con.close();
		}
		
		catch(SQLException er){
			er.printStackTrace();
		}
		return p;
	}
	
	public Paciente getPaciente(int cod_c)
	{
		Paciente p = new Paciente();
		String sql = "SELECT * FROM vwperfilcompleto where FK_paciente = ? ";
		try
		{	
			 con = Conexao.getConexao();
			 PreparedStatement preparador = con.prepareStatement(sql);
			 preparador.setInt(1, cod_c);
			 ResultSet rs = preparador.executeQuery();
			 if(rs.next())
			 {
				  p.setFK_paciente(rs.getInt("FK_paciente"));
				  p.setNome_paciente(rs.getString("NomeCompleto"));
				  p.setNumero_processo(rs.getString("NumeroProcesso"));
			 }
			 preparador.close();
			 con.close();
		}
		
		catch(Exception er){
			 
	         System.out.println("Erro finalizar: "+er);
		}
	
	return p;
}
}
