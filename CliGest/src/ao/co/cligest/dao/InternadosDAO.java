package ao.co.cligest.dao;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

import ao.co.cligest.entidades.Diverso;
import ao.co.cligest.entidades.Triagem;

public class InternadosDAO {

	private Connection con ;
	private Formatando ft = new Formatando();
 
	public List<Triagem> getInternados(){
		List<Triagem> lista = new ArrayList<Triagem>();
		String sql = "SELECT * FROM vwinternados where id_internamento not in (select FK_internamento from tblfacturatrasointernamento) and FK_internamento = 1 order by (numero_processo)";
		try
		{	
			 con = Conexao.getConexao();
			 PreparedStatement preparador = con.prepareStatement(sql);
			 ResultSet rs = preparador.executeQuery();
			 while(rs.next())
			 {
				 Triagem pacInt = new Triagem();
				 pacInt.setFK_paciente(rs.getInt("fk_paciente"));
				 pacInt.setNome(rs.getString("nome"));
				 pacInt.setNomem(rs.getString("nome_meio"));
				 pacInt.setApelido(rs.getString("ultimo_nome"));
				 pacInt.setNumero_processo(rs.getString("numero_processo"));
				 pacInt.setFK_doutor(rs.getInt("fk_doutor"));
				 pacInt.setGenero(rs.getString("genero"));
				 pacInt.setIdade(rs.getDate("data_nascimento"));	
				 Calendar data = Calendar.getInstance();
				 data.setTime(rs.getDate("data_internamento"));
				 pacInt.setData_internar(data);
				 lista.add(pacInt);
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
	
	public List<Triagem> getInternadosInt(){
		List<Triagem> lista = new ArrayList<Triagem>();
		String sql = "SELECT * FROM vwinternados where id_internamento not in (select FK_internamento from tblaltamedica) and FK_internamento = 1 order by (numero_processo)";
		try
		{	
			 con = Conexao.getConexao();
			 PreparedStatement preparador = con.prepareStatement(sql);
			 ResultSet rs = preparador.executeQuery();
			 while(rs.next())
			 {
				 Triagem pacInt = new Triagem();
				 pacInt.setFK_paciente(rs.getInt("fk_paciente"));
				 pacInt.setNome(rs.getString("nome"));
				 pacInt.setNomem(rs.getString("nome_meio"));
				 pacInt.setApelido(rs.getString("ultimo_nome"));
				 pacInt.setNumero_processo(rs.getString("numero_processo"));
				 pacInt.setFK_doutor(rs.getInt("fk_doutor"));
				 pacInt.setGenero(rs.getString("genero"));
				 pacInt.setIdade(rs.getDate("data_nascimento"));	
				 Calendar data = Calendar.getInstance();
				 data.setTime(rs.getDate("data_internamento"));
				 pacInt.setData_internar(data);
				 lista.add(pacInt);
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
	
	public List<Triagem> getInternadosUti(){
		List<Triagem> lista = new ArrayList<Triagem>();
		String sql = "SELECT * FROM vwinternados where id_internamento not in (select FK_internamento from tblaltamedica) and FK_internamento = 2 order by (numero_processo)";
		try
		{	
			 con = Conexao.getConexao();
			 PreparedStatement preparador = con.prepareStatement(sql);
			 ResultSet rs = preparador.executeQuery();
			 while(rs.next())
			 {
				 Triagem pacInt = new Triagem();
				 pacInt.setFK_paciente(rs.getInt("fk_paciente"));
				 pacInt.setNome(rs.getString("nome"));
				 pacInt.setNomem(rs.getString("nome_meio"));
				 pacInt.setApelido(rs.getString("ultimo_nome"));
				 pacInt.setNumero_processo(rs.getString("numero_processo"));
				 pacInt.setFK_doutor(rs.getInt("fk_doutor"));
				 pacInt.setGenero(rs.getString("genero"));
				 pacInt.setIdade(rs.getDate("data_nascimento"));	
				 Calendar data = Calendar.getInstance();
				 data.setTime(rs.getDate("data_internamento"));
				 pacInt.setData_internar(data);
				 lista.add(pacInt);
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
	
	public List<Triagem> getInternadosUtis(){
		List<Triagem> lista = new ArrayList<Triagem>();
		String sql = "SELECT * FROM vwinternados where FK_internamento = 2 order by (numero_processo)";
		try
		{	
			 con = Conexao.getConexao();
			 PreparedStatement preparador = con.prepareStatement(sql);
			 ResultSet rs = preparador.executeQuery();
			 while(rs.next())
			 {
				 Triagem pacInt = new Triagem();
				 pacInt.setFK_paciente(rs.getInt("fk_paciente"));
				 pacInt.setNome(rs.getString("nome"));
				 pacInt.setNomem(rs.getString("nome_meio"));
				 pacInt.setApelido(rs.getString("ultimo_nome"));
				 pacInt.setNumero_processo(rs.getString("numero_processo"));
				 pacInt.setFK_doutor(rs.getInt("fk_doutor"));
				 pacInt.setGenero(rs.getString("genero"));
				 pacInt.setIdade(rs.getDate("data_nascimento"));	
				 Calendar data = Calendar.getInstance();
				 data.setTime(rs.getDate("data_internamento"));
				 pacInt.setData_internar(data);
				 lista.add(pacInt);
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
	
	public List<Triagem> getInternadosBUEs(){
		List<Triagem> lista = new ArrayList<Triagem>();
		String sql = "SELECT * FROM vwinternados where FK_internamento = 3 order by (numero_processo)";
		try
		{	
			 con = Conexao.getConexao();
			 PreparedStatement preparador = con.prepareStatement(sql);
			 ResultSet rs = preparador.executeQuery();
			 while(rs.next())
			 {
				 Triagem pacInt = new Triagem();
				 pacInt.setFK_paciente(rs.getInt("fk_paciente"));
				 pacInt.setNome(rs.getString("nome"));
				 pacInt.setNomem(rs.getString("nome_meio"));
				 pacInt.setApelido(rs.getString("ultimo_nome"));
				 pacInt.setNumero_processo(rs.getString("numero_processo"));
				 pacInt.setFK_doutor(rs.getInt("fk_doutor"));
				 pacInt.setGenero(rs.getString("genero"));
				 pacInt.setIdade(rs.getDate("data_nascimento"));	
				 Calendar data = Calendar.getInstance();
				 data.setTime(rs.getDate("data_internamento"));
				 pacInt.setData_internar(data);
				 lista.add(pacInt);
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
	
	public List<Triagem> getInternadosBUE(){
		List<Triagem> lista = new ArrayList<Triagem>();
		String sql = "SELECT * FROM vwinternados where id_internamento not in (select FK_internamento from tblaltamedica) and FK_internamento = 3 order by (numero_processo)";
		try
		{	
			 con = Conexao.getConexao();
			 PreparedStatement preparador = con.prepareStatement(sql);
			 ResultSet rs = preparador.executeQuery();
			 while(rs.next())
			 {
				 Triagem pacInt = new Triagem();
				 pacInt.setFK_paciente(rs.getInt("fk_paciente"));
				 pacInt.setNome(rs.getString("nome"));
				 pacInt.setNomem(rs.getString("nome_meio"));
				 pacInt.setApelido(rs.getString("ultimo_nome"));
				 pacInt.setNumero_processo(rs.getString("numero_processo"));
				 pacInt.setFK_doutor(rs.getInt("fk_doutor"));
				 pacInt.setGenero(rs.getString("genero"));
				 pacInt.setIdade(rs.getDate("data_nascimento"));	
				 Calendar data = Calendar.getInstance();
				 data.setTime(rs.getDate("data_internamento"));
				 pacInt.setData_internar(data);
				 lista.add(pacInt);
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
	
	public List<Triagem> getInternadosBercario(){
		List<Triagem> lista = new ArrayList<Triagem>();
		String sql = "SELECT * FROM vwinternados where id_internamento not in (select FK_internamento from tblaltamedica) and FK_internamento = 5 order by (numero_processo)";
		try
		{	
			 con = Conexao.getConexao();
			 PreparedStatement preparador = con.prepareStatement(sql);
			 ResultSet rs = preparador.executeQuery();
			 while(rs.next())
			 {
				 Triagem pacInt = new Triagem();
				 pacInt.setFK_paciente(rs.getInt("fk_paciente"));
				 pacInt.setNome(rs.getString("nome"));
				 pacInt.setNomem(rs.getString("nome_meio"));
				 pacInt.setApelido(rs.getString("ultimo_nome"));
				 pacInt.setNumero_processo(rs.getString("numero_processo"));
				 pacInt.setFK_doutor(rs.getInt("fk_doutor"));
				 pacInt.setGenero(rs.getString("genero"));
				 pacInt.setIdade(rs.getDate("data_nascimento"));	
				 Calendar data = Calendar.getInstance();
				 data.setTime(rs.getDate("data_internamento"));
				 pacInt.setData_internar(data);
				 lista.add(pacInt);
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
	
	public List<Triagem> getInternadosBercarios(){
		List<Triagem> lista = new ArrayList<Triagem>();
		String sql = "SELECT * FROM vwinternados where FK_internamento = 5 order by (numero_processo)";
		try
		{	
			 con = Conexao.getConexao();
			 PreparedStatement preparador = con.prepareStatement(sql);
			 ResultSet rs = preparador.executeQuery();
			 while(rs.next())
			 {
				 Triagem pacInt = new Triagem();
				 pacInt.setFK_paciente(rs.getInt("fk_paciente"));
				 pacInt.setNome(rs.getString("nome"));
				 pacInt.setNomem(rs.getString("nome_meio"));
				 pacInt.setApelido(rs.getString("ultimo_nome"));
				 pacInt.setNumero_processo(rs.getString("numero_processo"));
				 pacInt.setFK_doutor(rs.getInt("fk_doutor"));
				 pacInt.setGenero(rs.getString("genero"));
				 pacInt.setIdade(rs.getDate("data_nascimento"));	
				 Calendar data = Calendar.getInstance();
				 data.setTime(rs.getDate("data_internamento"));
				 pacInt.setData_internar(data);
				 lista.add(pacInt);
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
	
	public List<Triagem> getInternados(String aux){
		List<Triagem> lista = new ArrayList<Triagem>();
		String sql = "SELECT * FROM vwinternados where nome Like ? or ultimo_nome Like ? and id_internamento not in (select FK_internamento from tblfacturatrasointernamento) order by (numero_processo)";
		try
		{	
			 con = Conexao.getConexao();
			 PreparedStatement preparador = con.prepareStatement(sql);
			 preparador.setString(1, aux+"%");
			 preparador.setString(2, aux+"%");
			 ResultSet rs = preparador.executeQuery();
			 while(rs.next())
			 {
				 Triagem pacInt = new Triagem();
				 pacInt.setFK_paciente(rs.getInt("fk_paciente"));
				 pacInt.setId_internamento(rs.getInt("id_internamento"));
				 pacInt.setNome(rs.getString("nome"));
				 pacInt.setNomem(rs.getString("nome_meio"));
				 pacInt.setApelido(rs.getString("ultimo_nome"));
				 pacInt.setNumero_processo(rs.getString("numero_processo"));
				 pacInt.setFK_doutor(rs.getInt("fk_doutor"));
				 pacInt.setGenero(rs.getString("genero"));
				 pacInt.setIdade(rs.getDate("data_nascimento"));	
				 Calendar data = Calendar.getInstance();
				 data.setTime(rs.getDate("data_internamento"));
				 pacInt.setData_internar(data);
				 lista.add(pacInt);
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
	public void TransfeMedica(Triagem fun)
	{
//		Triagem fun = new Triagem();
		String sql = "INSERT INTO tblaltamedicatransferencia (FK_alta, FK_hospital_clinica) VALUES (?,?)";
		try {
			con = Conexao.getConexao();
			PreparedStatement ent = con.prepareStatement(sql);
			ent.setInt(1, fun.getFk_altaMedica());
			ent.setLong(2, fun.getFk_hospital_Clinica());
			ent.execute();
			
			sql = "Update tblleito set status = ? where id_leito = ? and FK_sala = ?";
			ent = con.prepareStatement(sql);
			ent.setString(1, fun.getStatus());
			ent.setInt(2, fun.getFk_leito());
			ent.setInt(3, fun.getFk_sala());
			ent.execute();
			
			ent.close();
			System.out.println("Cadastro de sucesso...tblaltamedicatransferencia"); 
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		catch (Exception e) {
			// TODO: handle exception
		}
	    finally{
					try{
						con.close();
					}
					catch(SQLException ef){
						System.out.println("Erro finalizar: "+ef);
					}
			}
	}
//	Converter String em id
	 public  int buscarTransfMedId (String num)
	  {
   	int fk_hospital=0;
	   String sql = "SELECT id_hospital_clinica FROM tblhospitaisclinicas WHERE nome = ?";
	   try {
		   con = Conexao.getConexao();
	    PreparedStatement preparador = con.prepareStatement(sql);
	     preparador.setString(1, num);
	     ResultSet rs = preparador.executeQuery();
	     if(rs.next())
	     {
	     
	    	 fk_hospital= rs.getInt("id_hospital_clinica");
	     }
	     preparador.close();
	   } catch (SQLException e) {

	    e.printStackTrace();
	   }
	   catch (Exception e) {
			// TODO Auto-generated catch block
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

	   return fk_hospital;
	  }
//	fim converter
	public List<Triagem> getInternados(int cod,int ngrupo){
		List<Triagem> lista = new ArrayList<Triagem>();
		String sql = "SELECT * FROM vwinternados where (fk_doutor = ? or 28 = ?) and id_internamento not in(select FK_internamento from tblaltamedica);";
		try
		{	
			con = Conexao.getConexao();
			 PreparedStatement preparador = con.prepareStatement(sql);
			 preparador.setInt(1, cod);
			 preparador.setInt(2, ngrupo);
			 ResultSet rs = preparador.executeQuery();
			 while(rs.next())
			 {
				 Triagem pacInt = new Triagem();
				 pacInt.setFK_paciente(rs.getInt("fk_paciente"));
				 pacInt.setNome(rs.getString("nome"));
				 pacInt.setNomem(rs.getString("nome_meio"));
				 pacInt.setApelido(rs.getString("ultimo_nome"));
				 pacInt.setNumero_processo(rs.getString("numero_processo"));
				 pacInt.setFK_doutor(rs.getInt("fk_doutor"));
				 lista.add(pacInt);
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
	
	public List<Triagem> getInternadosPesquisar(String cod){
		List<Triagem> lista = new ArrayList<Triagem>();
		String sql = "SELECT * FROM vwinternados where paciente LIKE ? ";
		try
		{	
			con = Conexao.getConexao();
			 PreparedStatement preparador = con.prepareStatement(sql);
			 preparador.setString(1, cod+"%");
			 ResultSet rs = preparador.executeQuery();
			 while(rs.next())
			 {
				 Triagem pacInt = new Triagem();
				 pacInt.setFK_paciente(rs.getInt("fk_paciente"));
				 pacInt.setNome(rs.getString("nome"));
				 pacInt.setNomem(rs.getString("nome_meio"));
				 pacInt.setApelido(rs.getString("ultimo_nome"));
				 pacInt.setNome_doutor(rs.getString("paciente"));
				 pacInt.setNumero_processo(rs.getString("numero_processo"));
				 pacInt.setFK_doutor(rs.getInt("fk_doutor"));
				 lista.add(pacInt);
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
	
	public List<Triagem> getMedicao(int cod){
		List<Triagem> lista = new ArrayList<Triagem>();
		String sql = "SELECT * FROM vwfarmacoministrado where fk_internamento = ? and status like 'ok'";
		try
		{	
			 con = Conexao.getConexao();
			 PreparedStatement preparador = con.prepareStatement(sql);
			 preparador.setInt(1, cod);
			 ResultSet rs = preparador.executeQuery();
			 while(rs.next())
			 {
				 Triagem md = new Triagem();
				 md.setFarmaco(rs.getString("nome_comercial"));
				 md.setVidAd(rs.getString("viaadministrar"));
				 md.setQuantidade(rs.getInt("qtd"));
				 md.setDosagem(rs.getString("dosagem"));
				 md.setPossologia(rs.getString("posologia"));
				 md.setHora_ministrada(rs.getString("hora_administrada"));
				 md.setQdt_medicada(rs.getInt("qtd_medicada"));
				 md.setId_recetuario(rs.getInt("id_receita"));
				 lista.add(md);
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
	
	public List<Triagem> getEvolucao(int cod){
		List<Triagem> lista = new ArrayList<Triagem>();
		String sql = "SELECT * FROM vwfarmacoministrado where fk_internamento = ? and status like 'ok'";
		try
		{	
			 con = Conexao.getConexao();
			 PreparedStatement preparador = con.prepareStatement(sql);
			 preparador.setInt(1, cod);
			 ResultSet rs = preparador.executeQuery();
			 while(rs.next())
			 {
				 Triagem md = new Triagem();
				 md.setFarmaco(rs.getString("nome_comercial"));
				 md.setVidAd(rs.getString("viaadministrar"));
				 md.setQuantidade(rs.getInt("qtd"));
				 md.setDosagem(rs.getString("dosagem"));
				 md.setPossologia(rs.getString("posologia"));
				 md.setHora_ministrada(rs.getString("hora_administrada"));
				 md.setQdt_medicada(rs.getInt("qtd_medicada"));
				 md.setId_recetuario(rs.getInt("id_receita"));
				 lista.add(md);
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
	
	
	
	
	public List<Triagem> getEvolucaoInternamento(int cod){
		List<Triagem> lista = new ArrayList<Triagem>();
		String sql = "SELECT * FROM vwevolucaodopraciente where fk_internamento = ? group by (data_registo)";
		try
		{	
			 con = Conexao.getConexao();
			 PreparedStatement preparador = con.prepareStatement(sql);
			 preparador.setInt(1, cod);
			 ResultSet rs = preparador.executeQuery();
			 while(rs.next())
			 {
				 Triagem md = new Triagem();
				 md.setDescricao(rs.getString("evolucao"));
				 md.setHora_ministrada(rs.getString("hora_registo"));
				 Calendar data = Calendar.getInstance();
				 data.setTime(rs.getDate("data_registo"));
				 md.setData_evolucao(data);
				 md.setNomem(rs.getString("enfermeiro"));
				 lista.add(md);
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
	
	public List<Triagem> getEvolucaoInternamentoData(int cod,Date dia){
		List<Triagem> lista = new ArrayList<Triagem>();
		String sql = "SELECT * FROM vwevolucaodopraciente where fk_internamento = ? and data_registo = ?";
		try
		{	
			 con = Conexao.getConexao();
			 PreparedStatement preparador = con.prepareStatement(sql);
			 preparador.setInt(1, cod);
			 preparador.setDate(2, dia);
			 ResultSet rs = preparador.executeQuery();
			 while(rs.next())
			 {
				 Triagem md = new Triagem();
				 md.setDescricao(rs.getString("evolucao"));
				 md.setHora_ministrada(rs.getString("hora_registo"));
				 Calendar data = Calendar.getInstance();
				 data.setTime(rs.getDate("data_registo"));
				 md.setData_evolucao(data);
				 md.setNomem(rs.getString("enfermeiro"));
				 lista.add(md);
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
	public List<Triagem> getSinaisInternamento(int cod){
		List<Triagem> lista = new ArrayList<Triagem>();
		String sql = "SELECT * FROM vwsinaisinternados_enfermeiro where fk_internamento = ?";
		try
		{	
			 con = Conexao.getConexao();
			 PreparedStatement preparador = con.prepareStatement(sql);
			 preparador.setInt(1, cod);
			 ResultSet rs = preparador.executeQuery();
			 while(rs.next())
			 {
				 Triagem md = new Triagem();
				 md.setValor_pulso(rs.getString("pulso"));
				 md.setValor_resp(rs.getString("respiracao"));
				 md.setValor_tmp(rs.getString("temperatura"));
				 md.setValor_tns(rs.getString("tensao"));
				 md.setHora_ministrada(rs.getString("hora_registo"));
				 Calendar data = Calendar.getInstance();
				 data.setTime(rs.getDate("data_registo"));
				 md.setData_sinais(data);
				 md.setNomem(rs.getString("enfermeiro"));
				 lista.add(md);
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
	public List<Triagem> getSinaisInternamentoDatas(int cod){
		List<Triagem> lista = new ArrayList<Triagem>();
		String sql = "SELECT * FROM vwsinaisinternados_enfermeiro where fk_internamento = ? group by (data_registo)";
		try
		{	
			 con = Conexao.getConexao();
			 PreparedStatement preparador = con.prepareStatement(sql);
			 preparador.setInt(1, cod);
			 ResultSet rs = preparador.executeQuery();
			 while(rs.next())
			 {
				 Triagem md = new Triagem();
				 md.setValor_pulso(rs.getString("pulso"));
				 md.setValor_resp(rs.getString("respiracao"));
				 md.setValor_tmp(rs.getString("temperatura"));
				 md.setValor_tns(rs.getString("tensao"));
				 md.setHora_ministrada(rs.getString("hora_registo"));
				 Calendar data = Calendar.getInstance();
				 data.setTime(rs.getDate("data_registo"));
				 md.setData_sinais(data);
				 md.setNomem(rs.getString("enfermeiro"));
				 lista.add(md);
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
	public List<Triagem> getSinaisInternamento(int cod, Date dia){
		List<Triagem> lista = new ArrayList<Triagem>();
		String sql = "SELECT * FROM vwsinaisinternados_enfermeiro where fk_internamento = ? and data_registo Like ? order by (id_sinais_vitais)";
		try
		{	
			 con = Conexao.getConexao();
			 PreparedStatement preparador = con.prepareStatement(sql);
			 preparador.setInt(1, cod);
			 preparador.setDate(2, dia);
			 ResultSet rs = preparador.executeQuery();
			 while(rs.next())
			 {
				 Triagem md = new Triagem();
				 md.setValor_pulso(rs.getString("pulso"));
				 md.setValor_resp(rs.getString("respiracao"));
				 md.setValor_tmp(rs.getString("temperatura"));
				 md.setValor_tns(rs.getString("tensao"));
				 md.setHora_ministrada(rs.getString("hora_registo"));
				 Calendar data = Calendar.getInstance();
				 data.setTime(rs.getDate("data_registo"));
				 md.setData_sinais(data);
				 md.setNomem(rs.getString("enfermeiro"));
				 lista.add(md);
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
	
	public List<Triagem> getMedicaoSuspenso(int cod){
		List<Triagem> lista = new ArrayList<Triagem>();
		String sql = "SELECT * FROM vwfarmacoministrado where fk_internamento = ? and status like 'susp'";
		try
		{	
			 con = Conexao.getConexao();
			 PreparedStatement preparador = con.prepareStatement(sql);
			 preparador.setInt(1, cod);
			 ResultSet rs = preparador.executeQuery();
			 while(rs.next())
			 {
				 Triagem md = new Triagem();
				 md.setFarmaco(rs.getString("nome_comercial"));
				 md.setVidAd(rs.getString("viaadministrar"));
				 md.setQuantidade(rs.getInt("qtd"));
				 md.setDosagem(rs.getString("dosagem"));
				 md.setPossologia(rs.getString("posologia"));
				 md.setHora_ministrada(rs.getString("hora_administrada"));
				 md.setQdt_medicada(rs.getInt("qtd_medicada"));
				 md.setId_recetuario(rs.getInt("id_receita"));
				 lista.add(md);
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
	public List<Triagem> getMedicaoInt(int cod){
		List<Triagem> lista = new ArrayList<Triagem>();
		String sql = "SELECT * FROM vwfarmacoministrado where fk_internamento = ?";
		try
		{	
			 con = Conexao.getConexao();
			 PreparedStatement preparador = con.prepareStatement(sql);
			 preparador.setInt(1, cod);
			 ResultSet rs = preparador.executeQuery();
			 while(rs.next())
			 {
				 Triagem md = new Triagem();
				 md.setFarmaco(rs.getString("nome_comercial"));
				 md.setVidAd(rs.getString("viaadministrar"));
				 md.setQuantidade(rs.getInt("qtd"));
				 md.setDosagem(rs.getString("dosagem"));
				 md.setPossologia(rs.getString("posologia"));
				 md.setHora_ministrada(rs.getString("hora_administrada"));
				 md.setQdt_medicada(rs.getInt("qtd_medicada"));
				 md.setId_recetuario(rs.getInt("id_receita"));
				 lista.add(md);
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
	public List<Triagem> getExames(int cod){
		List<Triagem> lista = new ArrayList<Triagem>();
		String sql = "SELECT * FROM vwhistoricointernamentoexames where fk_internamento = ?";
		try
		{	
			 con = Conexao.getConexao();
			 PreparedStatement preparador = con.prepareStatement(sql);
			 preparador.setInt(1, cod);
			 ResultSet rs = preparador.executeQuery();
			 while(rs.next())
			 {
				 Triagem md = new Triagem();
				 md.setId_servico(rs.getInt("FK_servico"));
				 md.setServico(rs.getString("analise_clinica"));
				 lista.add(md);
			 }
			 preparador.close();
		}
		
		catch(SQLException er){
			er.printStackTrace();
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
	
	public List<Triagem> getMedicos(){
		List<Triagem> lista = new ArrayList<Triagem>();
		String sql = "SELECT * FROM vwcidadaomedicocompleto ";
		try
		{	
			 con = Conexao.getConexao();
			 PreparedStatement preparador = con.prepareStatement(sql);
			 ResultSet rs = preparador.executeQuery();
			 while(rs.next())
			 {
				 Triagem md = new Triagem();
				 md.setFK_doutor(rs.getInt("entidade"));
				 md.setNome_doutor(rs.getString("nomecompleto"));
				 md.setEspecialidade_doutor(rs.getString("especialidade"));
				 lista.add(md);
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
	
	public List<Triagem> getMedicos(int FK){
		List<Triagem> lista = new ArrayList<Triagem>();
		String sql = "select * from vwfuncespecializadoeagendado where id_da_semana = DAYOFWEEK(CURDATE()) and FK_entidade "
					+ "not in (select FK_doutor from vwpacientetriado_nd where data = curdate() and FK_doutor = ?)";
		try
		{	
			 con = Conexao.getConexao();
			 PreparedStatement preparador = con.prepareStatement(sql);
			 preparador.setInt(1, FK);
			 ResultSet rs = preparador.executeQuery();
			 while(rs.next())
			 {
				 Triagem md = new Triagem();
				 md.setFK_doutor(rs.getInt("FK_entidade"));
				 md.setNome_doutor(rs.getString("nome")+" "+rs.getString("nome_meio")+" "+rs.getString("ultimo_nome"));
				 md.setEspecialidade_doutor(rs.getString("especialidade"));
				 lista.add(md);
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

	public Triagem checkReceita(int cod){
			int ckR = 0; 
			 Triagem md = new Triagem();
			String sql = "SELECT * FROM tblinternamento_medicamento_administrado where fk_receita = ?";
			try
			{	
				 con = Conexao.getConexao();
				 PreparedStatement preparador = con.prepareStatement(sql);
				 preparador.setInt(1, cod);
				 ResultSet rs = preparador.executeQuery();
				 if(rs.next())		
				 {
					 md.setId_recetuario(rs.getInt("fk_receita"));
					 md.setQdt_medicada(rs.getInt("qtd_medicada"));					 
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
		return md;
	}
	
	public Triagem getDoutorCompleto(int cod){
		  
		Triagem md = new Triagem();
		String sql = "select * from vwcidadaomedicocompleto where entidade = ?";
		try
		{	
			 con = Conexao.getConexao();
			 PreparedStatement preparador = con.prepareStatement(sql);
			 preparador.setInt(1, cod);
			 ResultSet rs = preparador.executeQuery();
			 if(rs.next())		
			 {
				 md.setFK_doutor(rs.getInt("entidade"));
				 md.setEspecialidade_doutor(rs.getString("especialidade"));				 
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
		return md;
	}
	
		public List<Triagem> getRecomendacao(int cod){
		List<Triagem> lista = new ArrayList<Triagem>();
		String sql = "SELECT * FROM tblinternamentorecomendacao where fk_internamento = ?   order by  (data_registo) desc";
		try
		{	
			con = Conexao.getConexao();
			 PreparedStatement preparador = con.prepareStatement(sql);
			 preparador.setInt(1, cod);
			 ResultSet rs = preparador.executeQuery();
			 while(rs.next())
			 {
				 Triagem md = new Triagem();
				 md.setDescricao(rs.getString("recomendacao"));
				 Calendar data = Calendar.getInstance();
				 data.setTime(rs.getDate("data_registo"));
				 md.setData(data);
				 lista.add(md);
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
	
	public Triagem getInternado(int cod){
		Triagem pacInt = new Triagem();
		String sql = "SELECT   * FROM   `vwinternadoscomnome_medico`   "
				+ " LEFT JOIN `tblarquivodofuncionario`   "
				+ "  ON `vwinternadoscomnome_medico`.`FK_paciente` =   `tblarquivodofuncionario`.`FK_entidade` "
				+ " where FK_paciente = ?";
		try
		{	
			con = Conexao.getConexao();
			 PreparedStatement preparador = con.prepareStatement(sql);
			 preparador.setInt(1, cod);
			 System.out.println("codigo "+cod);
			 ResultSet rs = preparador.executeQuery();
			 if(rs.next())
			 {
				 pacInt.setFK_paciente(rs.getInt("fk_paciente"));
				 pacInt.setNome(rs.getString("nome"));
				 pacInt.setNomem(rs.getString("nome_meio"));
				 pacInt.setApelido(rs.getString("ultimo_nome"));
				 pacInt.setNumero_processo(rs.getString("numero_processo"));
				 pacInt.setIdade(rs.getDate("data_nascimento"));
				 pacInt.setGenero(rs.getString("genero"));
				 pacInt.setLocal_prov(rs.getString("local_proveniencia"));
				 pacInt.setModo_internar(rs.getInt("modo_internamento"));
				 pacInt.setAndar(rs.getString("andar_bloco"));
				 pacInt.setSector(rs.getString("sector"));
				 pacInt.setFk_sala(rs.getInt("id_sala"));
				 pacInt.setSala(rs.getInt("numero_sala")+" - "+ rs.getString("descricao"));
				 pacInt.setLeito(rs.getString("leito"));
				 pacInt.setHora_internar(rs.getString("hora_internamento"));
				 Calendar data = Calendar.getInstance();
				 data.setTime(rs.getDate("data_internamento"));
				 pacInt.setData_internar(data);
				 pacInt.setFK_doutor(rs.getInt("fk_doutor"));
				 pacInt.setId_internamento(rs.getInt("id_internamento"));
				 pacInt.setNome_doutor(rs.getString("nomecompleto")); 
				 pacInt.setEspecialidade_doutor(rs.getString("especialidade"));
				 pacInt.setFoto(rs.getString("nome_ficheiro"));
				 pacInt.setFk_leito(rs.getInt("id_leito"));
//				 pacInt.setFk_sala(rs.getInt("id_sector"));
//				 System.out.println("No DAO: "+pacInt.getId_internamento());
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
		return pacInt;
	}
	
	public List<Triagem> diagnosticados_views(int cod)
	{
		List<Triagem> lista = new ArrayList<Triagem>();
		String sql = "SELECT * FROM vwdiagnosticados where fk_paciente = ? ";
		try
		{
			 con = Conexao.getConexao();
			 PreparedStatement preparador = con.prepareStatement(sql);
			 preparador.setInt(1, cod);
			 ResultSet rs = preparador.executeQuery();
			 while(rs.next())
			 {
				 Triagem pac = new Triagem();
				 pac.setDescricao(rs.getString("diagnostico_cid"));
				 lista.add(pac);
			 }
			 preparador.close();
		}
		catch(SQLException er){
			System.out.println("Eror aqui: "+er);
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
		return lista;
	}
	
	public void  receiturarioInt(Triagem t){
		
		String sql = "insert into tblinternamentoreceituario (qtd,posologia,data_registo,hora_registo,FK_internamento,FK_via_administracao,FK_medicamento,FK_dosagem,status,FK_numero) values(?,?,?,?,?,?,?,?,?,?)";
		try
		{	
			 con = Conexao.getConexao();
			 PreparedStatement preparador = con.prepareStatement(sql);
			 preparador.setInt(1, t.getQuantidade());	
			 preparador.setString(2, t.getPossologia());
			 preparador.setDate(3, ft.data_registo());
			 preparador.setString(4, ft.getHoraAtual());
			 preparador.setInt(5, t.getId_internamento());
			 preparador.setInt(6, t.getFk_vidAd());
			 preparador.setInt(7, t.getFk_farmaco());
			 preparador.setInt(8, t.getFk_dosagem());
			 preparador.setString(9, "ok");
			 preparador.setInt(10, t.getFK_numero());
			 preparador.execute();
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
	 
	}
	
	public int numReceita(int codi){
		String sql = "Select * from tblinternamentoreceituario where FK_internamento = ?";
		return 0;
	}
	public void  transferencia(Triagem t){
		String sql_insert ="Insert into tblInternamentoTransferencia (sector_antigo,sala_antiga,leito_antigo,observacao,data_registo,hora_registo,FK_internamento,FK_sector_atual,FK_sala_atual,FK_leito_atual,medico_antigo) values(?,?,?,?,?,?,?,?,?,?,?)";
		String sql_update = "Update tblinternamento set fk_doutor = ?, fk_sector = ?,fk_sala = ?,fk_leito = ? where id_internamento = ?";
		try
		{	
			 con = Conexao.getConexao();
			 PreparedStatement preparador = con.prepareStatement(sql_update);
			 preparador.setInt(1, t.getFK_doutor());
			 preparador.setInt(2, t.getFk_sector());
			 preparador.setInt(3, t.getFk_sala());
			 preparador.setInt(4, t.getFk_leito());
			 preparador.setInt(5, t.getId_internamento());
			 preparador.execute();
			 
			 preparador = con.prepareStatement(sql_insert);
			 preparador.setString(1, t.getSector());
			 preparador.setString(2, t.getSala());
			 preparador.setString(3, t.getLeito());
			 preparador.setString(4, t.getDescricao());
			 preparador.setDate(5, ft.data_registo());
			 preparador.setString(6, ft.getHoraAtual());
			 preparador.setInt(7, t.getId_internamento());
			 preparador.setInt(8, t.getFk_sector());
			 preparador.setInt(9, t.getFk_sala());
			 preparador.setInt(10, t.getFk_leito());
			 preparador.setString(11, t.getNome_doutor());
			 preparador.execute();
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
	 
	}

	public void  receiturarioIntSuspender(int cod){
		
		String sql = "update tblinternamentoreceituario set status = ?, data_registo = ?, hora_registo = ? where id_receita = ?";
		try
		{	
			 con = Conexao.getConexao();
			 PreparedStatement preparador = con.prepareStatement(sql);
			 preparador.setString(1, "susp");
			 preparador.setDate(2, ft.data_registo());
			 preparador.setString(3, ft.getHoraAtual());
			 preparador.setInt(4, cod);
			 preparador.execute();
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
	 
	}
	
public void  medicarNova(Triagem t){
		
		String sql = "insert into tblinternamento_medicamento_administrado (qtd_medicada,hora_administrada,FK_receita,FK_enfermeiro) values(?,?,?,?)";
		try
		{	
			 con = Conexao.getConexao();
			 PreparedStatement preparador = con.prepareStatement(sql);
			 preparador.setInt(1, t.getQdt_medicada());	
			 preparador.setString(2, ft.getHoraAtual());
			 preparador.setInt(3, t.getId_recetuario());
			 preparador.setInt(4, t.getFk_funcionario());
			 preparador.execute();
			 preparador.close();
			 System.out.println("Nova");
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
	 
	}
public void  medicarAtualizar(Triagem t){
	
	String sql = "update tblinternamento_medicamento_administrado set qtd_medicada = ?,hora_administrada = ? where FK_receita = ?";
	try
	{	
		 con = Conexao.getConexao();
		 PreparedStatement preparador = con.prepareStatement(sql);
		 preparador.setInt(1, t.getQdt_medicada());	
		 preparador.setString(2, ft.getHoraAtual());
		 preparador.setInt(3, t.getId_recetuario());
		 preparador.execute();
		 preparador.close();
		 System.out.println("Atualizada");
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
 
}
	
	public void receituarioIntObs(Triagem t)
	{
		String sql = "insert into tblInternamentoRecomendacao (data_registo,hora_registo,FK_internamento,recomendacao) values(?,?,?,?)";
		try {
			con = Conexao.getConexao();
			 PreparedStatement preparador = con.prepareStatement(sql);
			 preparador = con.prepareStatement(sql);
			 preparador.setDate(1, ft.data_registo());
			 preparador.setString(2, ft.getHoraAtual());
			 preparador.setInt(3, t.getId_internamento());
			 preparador.setString(4, t.getRecomendacao());
			 preparador.execute();
			 preparador.close();
			
		} catch (SQLException e) {
			throw new RuntimeException(e);
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
	}
	
	public int altaMedicaInt(Triagem t)
	{
		int ultimoId = -1;
		String sql = "insert into tblaltamedica (data_registo,hora_registo,FK_internamento,observacao,motivo_alta) values(?,?,?,?,?)";
		try {
			con = Conexao.getConexao();
			 PreparedStatement preparador = con.prepareStatement(sql);
			 preparador = con.prepareStatement(sql);
			 preparador.setDate(1, ft.data_registo());
			 preparador.setString(2, ft.getHoraAtual());
			 preparador.setInt(3, t.getId_internamento());
			 preparador.setString(4, t.getDescricao());
			 preparador.setString(5, t.getServico());
			 preparador.execute();
//			 preparador.close();
			 ResultSet rs = preparador.executeQuery("SELECT LAST_INSERT_ID()");
			 if(rs.next())
	    	        ultimoId = rs.getInt(1);
			 
			 sql = "Update tblleito set status = ? where id_leito = ? and FK_sala = ?";
			 preparador = con.prepareStatement(sql);
			 preparador.setString(1, t.getStatus());
			 System.out.println("status: "+t.getStatus());
			 preparador.setInt(2, t.getFk_leito());
			 System.out.println("leito: "+t.getFk_leito());
			 preparador.setInt(3, t.getFk_sala());
			 System.out.println("sala: "+t.getFk_sala());
			 preparador.execute();
			 
			 preparador.close();
		} catch (SQLException e) {
			throw new RuntimeException(e);
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
		return ultimoId;
	}
	
	
	public void sinaisVitaisInt(Triagem t)
	{
		String sql = "insert into tblInternamentoSinaisVitais (data_registo,hora_registo,FK_internamento,observacao,FK_enfermeiro,FK_temperatura,FK_tensao_arterial,FK_pulso,FK_respiracao,FK_tensao_arterial_dois) values(?,?,?,?,?,?,?,?,?,?)";
		try {
			con = Conexao.getConexao();
			 PreparedStatement preparador = con.prepareStatement(sql);
			 preparador = con.prepareStatement(sql);
			 preparador.setDate(1, ft.data_registo());
			 preparador.setString(2, ft.getHoraAtual());
			 preparador.setInt(3, t.getId_internamento());
			 preparador.setString(4, t.getDescricao());
			 preparador.setInt(5, t.getFk_funcionario());
			 preparador.setInt(6, t.getFk_temperatura());
			 preparador.setInt(7, t.getFK_tensao_sistolica());
			 preparador.setInt(8, t.getFk_pulso());
			 preparador.setInt(9, t.getFk_respiracao());
			 preparador.setInt(10, t.getFK_tensao_diastolica());
			 preparador.execute();
			 preparador.close();
			
		} catch (SQLException e) {
			throw new RuntimeException(e);
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
	}
	
	public void evolucaoInt(Triagem t)
	{
		String sql = "insert into tblInternamentoEvolucao (data_registo,hora_registo,FK_internamento,evolucao,FK_enfermeiro) values(?,?,?,?,?)";
		try {
			con = Conexao.getConexao();
			 PreparedStatement preparador = con.prepareStatement(sql);
			 preparador = con.prepareStatement(sql);
			 preparador.setDate(1, ft.data_registo());
			 preparador.setString(2, ft.getHoraAtual());
			 preparador.setInt(3, t.getId_internamento());
			 preparador.setString(4, t.getDescricao());
			 preparador.setInt(5, t.getFk_funcionario());
			 preparador.execute();
			 preparador.close();
			
		} catch (SQLException e) {
			throw new RuntimeException(e);
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
	}
	
public void  examesClinicosNova(Triagem t){
		
		String sql = "insert into tblInternamentoExameClinico (data_registo,hora_registo,fk_internamento,fk_servico,FK_numero_requisicao) values(?,?,?,?,?)";
		try
		{	
			 con = Conexao.getConexao();
			 PreparedStatement preparador = con.prepareStatement(sql);
			 preparador.setDate(1, ft.data_registo());	
			 preparador.setString(2, ft.getHoraAtual());
			 preparador.setInt(3, t.getId_internamento());
			 preparador.setInt(4, t.getId_servico());
			 preparador.setInt(5, t.getFK_numero_requisicao());
			 preparador.execute();
			 preparador.close();
//			 System.out.println("Nova");
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
	 
	}

	public Diverso buscarProcedimento (String aux){
		String sql = "Select * from tblprocedimentos where procedimento = ? ";
		Diverso ds = null;
		try {
			con = Conexao.getConexao();
			PreparedStatement ps = con.prepareStatement(sql);
			ps.setString(1, aux);
			ResultSet rs = ps.executeQuery();
			if(rs.next()){
				ds = new Diverso();
				ds.setProcedimento(rs.getString("procedimento"));
				ds.setId_procedimento(rs.getInt("id_procedimento"));
				
			}
			ps.close();
			con.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return ds;
	}
	
	public List<Diverso> buscarListaIntProcedimento (int aux){
		String sql = "Select * from vwlistadeprocedimentosinternamentos where FK_internamento = ? ";
		List<Diverso> lista = new ArrayList<Diverso>();
		try {
			con = Conexao.getConexao();
			PreparedStatement ps = con.prepareStatement(sql);
			ps.setInt(1, aux);
			ResultSet rs = ps.executeQuery();
			while(rs.next()){
				Diverso	ds = new Diverso();
				ds.setProcedimento(rs.getString("procedimento"));
				ds.setId_procedimento(rs.getInt("FK_procedimento"));
				ds.setId_intern_proced(rs.getInt("id_intern_proced"));
				ds.setFK_internamento(rs.getInt("FK_internamento"));
				ds.setDescricao(rs.getString("observacao"));
				ds.setStatuss(rs.getString("status"));
				ds.setQtd_procedimento(rs.getInt("qtd"));
//				ds.setNomeProf(rs.getString("n_c_funcionario"));
				lista.add(ds);
				
			}
			ps.close();
			con.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return lista;
	}
	
	public List<Diverso> buscarListaIntProcedimentoEmf (int aux){
		String sql = "Select * from vwlistadeprocedimentosinternamentos where FK_internamento = ? and status <> 'suspenso'";
		List<Diverso> lista = new ArrayList<Diverso>();
		try {
			con = Conexao.getConexao();
			PreparedStatement ps = con.prepareStatement(sql);
			ps.setInt(1, aux);
			ResultSet rs = ps.executeQuery();
			while(rs.next()){
				Diverso	ds = new Diverso();
				ds.setProcedimento(rs.getString("procedimento"));
				ds.setId_procedimento(rs.getInt("FK_procedimento"));
				ds.setId_intern_proced(rs.getInt("id_intern_proced"));
				ds.setFK_internamento(rs.getInt("FK_internamento"));
				ds.setDescricao(rs.getString("observacao"));
				ds.setStatuss(rs.getString("status"));
				ds.setQtd_procedimento(rs.getInt("qtd"));
//				ds.setNomeProf(rs.getString("n_c_funcionario"));
				lista.add(ds);
				
			}
			ps.close();
			con.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return lista;
	}
	
	public List<Diverso> buscarListaIntProcedimentos (int aux){
		String sql = "SELECT  intprc.id_intern_proced,intprc.FK_internamento,intprc.FK_procedimento,intprc.qtd, prc.procedimento, Count(intprcf.FK_funcionario) AS qtd_funcionarios "
				+ " FROM   tblprocedimentos prc,tblinternamentoprocedimento intprc, tblinternamentoprocedimentofeito intprcf "
				+ " WHERE      intprc.FK_procedimento =  prc.id_procedimento AND intprc.id_intern_proced = intprcf.FK_procedimento_internproced "
				+ " AND intprc.FK_internamento = ? "
				+ " GROUP BY   intprc.FK_procedimento";
		List<Diverso> lista = new ArrayList<Diverso>();
		try {
			con = Conexao.getConexao();
			PreparedStatement ps = con.prepareStatement(sql);
			ps.setInt(1, aux);
			ResultSet rs = ps.executeQuery();
			while(rs.next()){
				Diverso	ds = new Diverso();
				ds.setProcedimento(rs.getString("procedimento"));
				ds.setId_procedimento(rs.getInt("FK_procedimento"));
				ds.setId_intern_proced(rs.getInt("id_intern_proced"));
				ds.setFK_internamento(rs.getInt("FK_internamento"));
				ds.setQtd_procedimento(rs.getInt("qtd"));
				ds.setQtd_funcionarios(rs.getInt("qtd_funcionarios"));
				lista.add(ds);
				
			}
			ps.close();
			con.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return lista;
	}
	
	public List<Diverso> buscarListaIntProcedimentosComPreco (int aux){
		String sql = " SELECT   `ip`.`FK_internamento`, `prc`.`id_procedimento`, `prc`.`procedimento`,`ip`.`qtd`,"
				  + " ((`t`.`taxa` / 100 * `prc`.`preco`) + `prc`.`preco`) AS 'preco', "
				  + "  (((`t`.`taxa` / 100 * `prc`.`preco`) + `prc`.`preco`) *  `ip`.`qtd`) AS   'preco_total' "
				  + " FROM   `tblprocedimentos` `prc`, `tbltaxas` `t`, `tblinternamentoprocedimento` `ip` "
				  + "WHERE`t`.`id_taxa` = `prc`.`FK_taxa`   AND `ip`.`FK_procedimento` = `prc`.`id_procedimento` "
				  + " AND `ip`.`FK_internamento` = ? GROUP BY  `prc`.`id_procedimento`";
		List<Diverso> lista = new ArrayList<Diverso>();
		try {
			con = Conexao.getConexao();
			PreparedStatement ps = con.prepareStatement(sql);
			ps.setInt(1, aux);
			ResultSet rs = ps.executeQuery();
			while(rs.next()){
				Diverso	ds = new Diverso();
				ds.setProcedimento(rs.getString("procedimento"));
				ds.setId_procedimento(rs.getInt("id_procedimento"));
				ds.setFK_internamento(rs.getInt("FK_internamento"));
				ds.setQtd_procedimento(rs.getInt("qtd"));
				ds.setPreco(rs.getDouble("preco"));
				ds.setPreco_venda(rs.getDouble("preco_total"));
				lista.add(ds);
				
			}
			ps.close();
			con.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return lista;
	}
	
	
	public List<Diverso> buscarListaIntMaterial (int aux){
		String sql = "SELECT  mtu.id_intern_mat_usado, mtu.FK_internamento,mtu.FK_material,mt.material,mtu.qtd, Count(mtu.FK_enfermeiro) as qtd_funcionarios "
				+ " FROM tblinternamentomaterialusado mtu, tblmateriais mt "
				+ "WHERE mtu.FK_material = mt.id_material AND mtu.FK_internamento = ? GROUP BY mtu.FK_material ";
		List<Diverso> lista = new ArrayList<Diverso>();
		try {
			con = Conexao.getConexao();
			PreparedStatement ps = con.prepareStatement(sql);
			ps.setInt(1, aux);
			ResultSet rs = ps.executeQuery();
			while(rs.next()){
				Diverso	ds = new Diverso();
				ds.setMaterial(rs.getString("material"));
				ds.setId_material(rs.getInt("FK_material"));
				ds.setId_intern_mat_usado(rs.getInt("id_intern_mat_usado"));
				ds.setFK_internamento(rs.getInt("FK_internamento"));
				ds.setQtd_material(rs.getInt("qtd"));
				ds.setQtd_funcionarios(rs.getInt("qtd_funcionarios"));
				lista.add(ds);
				
			}
			ps.close();
			con.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return lista;
	}
	
	public List<Diverso> buscarListaIntMaterialComPreco (int aux){
		String sql = "SELECT   `mtu`.`id_intern_mat_usado`,  `mtu`.`FK_internamento`,  `mtu`.`FK_material`,  `mt`.`material`,"
				+ "  `mtu`.`qtd`,  ((`t`.`taxa` / 100 * `mt`.`preco`) + `mt`.`preco`) AS 'preco', "
				+ "   (((`t`.`taxa` / 100 * `mt`.`preco`) + `mt`.`preco`) *  `mtu`.`qtd`) AS   'preco_total', "
				+ "  `mt`.`FK_taxa`,  `t`.`taxa` FROM   `tblinternamentomaterialusado` `mtu`,   `tblmateriais` `mt` "
				+ "  INNER JOIN `tbltaxas` `t` ON `mt`.`FK_taxa` = `t`.`id_taxa` WHERE   `mtu`.`FK_material` = `mt`.`id_material` "
				+ " AND `mtu`.`FK_internamento` = ?  GROUP BY   `mtu`.`FK_material`";
		List<Diverso> lista = new ArrayList<Diverso>();
		try {
			con = Conexao.getConexao();
			PreparedStatement ps = con.prepareStatement(sql);
			ps.setInt(1, aux);
			ResultSet rs = ps.executeQuery();
			while(rs.next()){
				Diverso	ds = new Diverso();
				ds.setMaterial(rs.getString("material"));
				ds.setId_material(rs.getInt("FK_material"));
				ds.setId_intern_mat_usado(rs.getInt("id_intern_mat_usado"));
				ds.setFK_internamento(rs.getInt("FK_internamento"));
				ds.setQtd_material(rs.getInt("qtd"));
				ds.setPreco(rs.getDouble("preco"));
				ds.setPreco_venda(rs.getDouble("preco_total"));
				lista.add(ds);
				
			}
			ps.close();
			con.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return lista;
	}
	
	public List<Diverso> buscarListaIntMedicamento (int aux){
		String sql = "SELECT   mdu.id_intern_med_usado, mdu.FK_internamento, mdu.FK_medicamento, md.medicamento, mdu.FK_enfermeiro, Count(mdu.FK_enfermeiro) as qtd_funcionarios, mdu.qtd "
				+ " FROM   tblinternamentomedicamentousado mdu, tblmedicamentosbue md WHERE   mdu.FK_medicamento = md.id_medicamento "
				+ " AND mdu.FK_internamento = ? GROUP BY   mdu.FK_medicamento "; 
		List<Diverso> lista = new ArrayList<Diverso>();
		try {
			con = Conexao.getConexao();
			PreparedStatement ps = con.prepareStatement(sql);
			ps.setInt(1, aux);
			ResultSet rs = ps.executeQuery();
			while(rs.next()){
				Diverso	ds = new Diverso();
				ds.setMedicamento(rs.getString("medicamento"));
				ds.setId_medicamento(rs.getInt("FK_medicamento"));
				ds.setId_intern_mat_usado(rs.getInt("id_intern_med_usado"));
				ds.setFK_internamento(rs.getInt("FK_internamento"));
				ds.setQtd_medicamento(rs.getInt("qtd"));
				ds.setQtd_funcionarios(rs.getInt("qtd_funcionarios"));
				lista.add(ds);
				
			}
			ps.close();
			con.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return lista;
	}
	
	public List<Diverso> buscarListaIntMedicamentoComPreco (int aux){
		String sql = "SELECT   `mdu`.`id_intern_med_usado`,  `mdu`.`FK_internamento`, `mdu`.`FK_medicamento`, `md`.`medicamento`, `mdu`.`FK_enfermeiro`, "
				+ "  ((`t`.`taxa` / 100 * `md`.`preco`) + `md`.`preco`) AS 'preco', "
				+ "  (((`t`.`taxa` / 100 * `md`.`preco`) + `md`.`preco`) *  `mdu`.`qtd`) AS   'preco_total',   `mdu`.`qtd` "
				+ "  FROM   `tblinternamentomedicamentousado` `mdu`,`tblmedicamentosbue` `md` INNER JOIN `tbltaxas` `t` ON `md`.`FK_taxa` = `t`.`id_taxa` "
				+ "  WHERE   `mdu`.`FK_medicamento` = `md`.`id_medicamento` "
				+ "  AND `mdu`.`FK_internamento` = ? GROUP BY   `mdu`.`FK_medicamento` "; 
		List<Diverso> lista = new ArrayList<Diverso>();
		try {
			con = Conexao.getConexao();
			PreparedStatement ps = con.prepareStatement(sql);
			ps.setInt(1, aux);
			ResultSet rs = ps.executeQuery();
			while(rs.next()){
				Diverso	ds = new Diverso();
				ds.setMedicamento(rs.getString("medicamento"));
				ds.setId_medicamento(rs.getInt("FK_medicamento"));
				ds.setId_intern_mat_usado(rs.getInt("id_intern_med_usado"));
				ds.setFK_internamento(rs.getInt("FK_internamento"));
				ds.setQtd_medicamento(rs.getInt("qtd"));
				ds.setPreco(rs.getDouble("preco"));
				ds.setPreco_venda(rs.getDouble("preco_total"));
				lista.add(ds);
				
			}
			ps.close();
			con.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return lista;
	}
	
	public List<Diverso> buscarListaIntProcFuncModal (int intm,int aux ){
		String sql = "SELECT `intprc`.`id_intern_proced`,`intprc`.`FK_internamento`,`intprc`.`FK_procedimento`,`intprc`.`qtd`,`prc`.`procedimento`,`intprcf`.`FK_funcionario`,  `vwfnc`.`n_c_funcionario`,  `intprcf`.`data`, `intprcf`.`hora`,Sum(`intprcf`.`qtd`) as qtd_procedimento "
				  + " FROM  `tblprocedimentos` `prc`, `tblinternamentoprocedimento` `intprc`,`tblinternamentoprocedimentofeito` `intprcf`,`vwfuncionariosimples` `vwfnc` "
				  + " WHERE  `intprc`.`FK_procedimento` = `prc`.`id_procedimento` AND  `intprc`.`id_intern_proced` = `intprcf`.`FK_procedimento_internproced` AND   `intprcf`.`FK_funcionario` = `vwfnc`.`FK_entidade` "
				  + " AND `intprc`.`FK_internamento` = ?  AND `intprc`.`FK_procedimento` = ? "
				  + " GROUP BY   `intprc`.`FK_procedimento`,   `intprcf`.`FK_funcionario` "; 
		List<Diverso> lista = new ArrayList<Diverso>();
		try {
			con = Conexao.getConexao();
			PreparedStatement ps = con.prepareStatement(sql);
			ps.setInt(1, intm);
			ps.setInt(2, aux);
			ResultSet rs = ps.executeQuery();
			while(rs.next()){
				Diverso	ds = new Diverso();
				ds.setDescricao(rs.getString("procedimento"));
				ds.setNomeFun(rs.getString("n_c_funcionario"));
				ds.setQuantidade(rs.getInt("qtd_procedimento"));
				Calendar data = Calendar.getInstance();
				data.setTime(rs.getDate("data"));
				ds.setData_registo(data);
				ds.setHora_registo(rs.getString("hora"));
				lista.add(ds);
				
			}
			ps.close();
			con.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return lista;
	}
	
	public List<Diverso> buscarListaIntMedFuncModal (int intm,int aux ){
		String sql = "SELECT   `mdu`.`id_intern_med_usado`, `mdu`.`FK_internamento`,`mdu`.`FK_medicamento`,`md`.`medicamento`,`mdu`.`qtd`,`vwfnc`.`n_c_funcionario`,`mdu`.`data`, `mdu`.`hora`"
				+ "   FROM   `tblinternamentomedicamentousado` `mdu`,`tblmedicamentosbue` `md`, `vwfuncionariosimples` `vwfnc` "
				+ "   WHERE `mdu`.`FK_medicamento` = `md`.`id_medicamento`   AND `mdu`.`FK_enfermeiro` = `vwfnc`.`FK_entidade` "
				+ "   AND `mdu`.`FK_internamento` = ?  AND `mdu`.`FK_medicamento` = ? "; 
		List<Diverso> lista = new ArrayList<Diverso>();
		try {
			con = Conexao.getConexao();
			PreparedStatement ps = con.prepareStatement(sql);
			ps.setInt(1, intm);
			ps.setInt(2, aux);
			ResultSet rs = ps.executeQuery();
			while(rs.next()){
				Diverso	ds = new Diverso();
				ds.setDescricao(rs.getString("medicamento"));
				ds.setNomeFun(rs.getString("n_c_funcionario"));
				ds.setQuantidade(rs.getInt("qtd"));
				Calendar data = Calendar.getInstance();
				data.setTime(rs.getDate("data"));
				ds.setData_registo(data);
				ds.setHora_registo(rs.getString("hora"));
				lista.add(ds);
				
			}
			ps.close();
			con.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return lista;
	}
	
	public List<Diverso> buscarListaIntMatFuncModal (int intm,int aux ){
		String sql = "SELECT `mtu`.`id_intern_mat_usado`, `mtu`.`FK_internamento`,`mtu`.`FK_material`, `mt`.`material`,`mtu`.`data`,`mtu`.`hora`,`mtu`.`qtd`,`vwfnc`.`n_c_funcionario` "
				+ "  FROM `tblinternamentomaterialusado` `mtu`,`vwfuncionariosimples` `vwfnc`, `tblmateriais` `mt` "
				+ "  WHERE `mtu`.`FK_material` = `mt`.`id_material` AND `mtu`.`FK_enfermeiro` =    `vwfnc`.`FK_entidade` "
				+ "  AND `mtu`.`FK_internamento` = ? AND `mtu`.`FK_material` = ?"; 
		List<Diverso> lista = new ArrayList<Diverso>();
		try {
			con = Conexao.getConexao();
			PreparedStatement ps = con.prepareStatement(sql);
			ps.setInt(1, intm);
			ps.setInt(2, aux);
			ResultSet rs = ps.executeQuery();
			while(rs.next()){
				Diverso	ds = new Diverso();
				ds.setDescricao(rs.getString("material"));
				ds.setNomeFun(rs.getString("n_c_funcionario"));
				ds.setQuantidade(rs.getInt("qtd"));
				Calendar data = Calendar.getInstance();
				data.setTime(rs.getDate("data"));
				ds.setData_registo(data);
				ds.setHora_registo(rs.getString("hora"));
				lista.add(ds);
				
			}
			ps.close();
			con.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return lista;
	}
	
	public void novoProdecimentoInternado (Diverso ds){
		String sql = "Insert into tblinternamentoprocedimento (FK_internamento,FK_procedimento,qtd) value (?,?,?)";
		try {
			con = Conexao.getConexao();
			PreparedStatement ps = con.prepareStatement(sql);
			ps.setInt(1, ds.getFK_internamento());
			ps.setInt(2, ds.getId_procedimento());
			ps.setInt(3, 0);
			ps.execute();
			ps.close();
			con.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	public void suspernderProdecimentoInternado (int cod){
		String sql = "update tblinternamentoprocedimento set status = 'suspenso' where id_intern_proced = ?";
		try {
			con = Conexao.getConexao();
			PreparedStatement ps = con.prepareStatement(sql);
			ps.setInt(1, cod);
		 
			ps.execute();
			ps.close();
			con.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	 
	@SuppressWarnings("resource")
	public void novoProdecimentoInternadoObs (Diverso ds){
		String sql = "Select * from tblinternamentoprocedimentoobs where FK_internamento = ?";
		try {
			con = Conexao.getConexao();
			PreparedStatement ps = con.prepareStatement(sql);
			ps.setInt(1, ds.getFK_internamento());
			ResultSet rs = ps.executeQuery();
			if(rs.next()){
				String aux = rs.getString("observacao");
				sql = "Update tblinternamentoprocedimentoobs set observacao = ? where FK_internamento = ?";
				ps = con.prepareStatement(sql);
				ps.setString(1, ds.getDescricao()+"\n"+aux);
				ps.setInt(2, ds.getFK_internamento());
				ps.execute();
				ps.close();
			}
			else{
				sql = "Insert into tblinternamentoprocedimentoobs (observacao,FK_internamento) value (?,?)";
				ps.clearParameters();
				ps = con.prepareStatement(sql);
				ps.setString(1, ds.getDescricao());
				ps.setInt(2, ds.getFK_internamento());
				ps.execute();
				ps.close();
			}
			 
			con.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	@SuppressWarnings("resource")
	public void procedentoEmInternamento(Diverso ds){
		String sql = "Select * from tblinternamentoprocedimentofeito where FK_procedimento_internproced = ?  and FK_funcionario = ? and data = curdate()";
		  int tmp_qtd = 0;
		  try {
			con = Conexao.getConexao();
			PreparedStatement ps = con.prepareStatement(sql);
			ps.setInt(1, ds.getId_intern_proced());
			ps.setInt(2, ds.getFK_funcionario());
			ResultSet rs = ps.executeQuery();
			if(rs.next()){
					tmp_qtd = ds.getQtd_procedimento() + rs.getInt("qtd");
					int aux_int = rs.getInt("id_proced_feito");

					sql = "update tblinternamentoprocedimentofeito set qtd = ?, hora = ? where id_proced_feito = ?";
					ps = con.prepareStatement(sql);
					ps.setInt(1, tmp_qtd);
					ps.setString(2, ft.getHoraAtual());
					ps.setInt(3, aux_int);
					ps.execute();
					ps.close();
			}
			else{
				    sql = "Insert into tblinternamentoprocedimentofeito (FK_procedimento_internproced,FK_funcionario,qtd,data,hora) value (?,?,?,?,?)";
					ps = con.prepareStatement(sql);
					ps.setInt(1, ds.getId_intern_proced());
					ps.setInt(2, ds.getFK_funcionario());
					ps.setInt(3, ds.getQtd_procedimento());
					ps.setDate(4, ft.data_registo());
					ps.setString(5, ft.getHoraAtual());
					ps.execute();
					ps.close();
			}
			
			sql = "Select * from tblinternamentoprocedimento where id_intern_proced = ?";
			ps = con.prepareStatement(sql);
			ps.setInt(1, ds.getId_intern_proced());
			rs = ps.executeQuery();
			if(rs.next()){
				int aux_qtd = ds.getQtd_procedimento() + rs.getInt("qtd");
				sql = "update tblinternamentoprocedimento set qtd = ? where id_intern_proced = ?";
				ps = con.prepareStatement(sql);
				ps.setInt(1, aux_qtd);
				ps.setInt(2, ds.getId_intern_proced());
				ps.execute();
				ps.close();
			}
			 
			con.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	public Diverso buscarMaterial(String aux){
		String sql = "Select * from tblmateriais where material = ? ";
		Diverso ds = null;
		try {
			con = Conexao.getConexao();
			PreparedStatement ps = con.prepareStatement(sql);
			ps.setString(1, aux);
			ResultSet rs = ps.executeQuery();
			if(rs.next()){
				ds = new Diverso();
				ds.setMaterial(rs.getString("material"));
				ds.setId_material(rs.getInt("id_material"));
			}
			ps.close();
			con.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return ds;
	}
	
	@SuppressWarnings("resource")
	public void novoMaterialInternado (Diverso ds){
		String sql = "Select * from tblinternamentomaterialusado where FK_internamento = ? and FK_material = ? and FK_enfermeiro = ? and data = curdate()";
		     
		try {
			con = Conexao.getConexao();
			PreparedStatement ps = con.prepareStatement(sql);
			ps.setInt(1, ds.getFK_internamento());
			ps.setInt(2, ds.getId_material());
			ps.setInt(3, ds.getFK_funcionario());
			ResultSet rs = ps.executeQuery();
			if(rs.next()){
				int aux_qtd = ds.getQtd_material() + rs.getInt("qtd");
				int cod_linha = rs.getInt("id_intern_mat_usado");
				sql = "Update tblinternamentomaterialusado set qtd = ?,hora = ? where id_intern_mat_usado = ?";
				ps = con.prepareStatement(sql);
				ps.setInt(1, aux_qtd);
				ps.setString(2, ft.getHoraAtual());
				ps.setInt(3, cod_linha);
				ps.execute();
				ps.close();
			}
			else{
				 sql = "Insert into tblinternamentomaterialusado (FK_internamento,FK_material,qtd,FK_enfermeiro,data,hora) value (?,?,?,?,?,?)";
				ps = con.prepareStatement(sql);
				ps.setInt(1, ds.getFK_internamento());
				ps.setInt(2, ds.getId_material());
				ps.setInt(3, ds.getQtd_material());
				ps.setInt(4, ds.getFK_funcionario());
				ps.setDate(5, ft.data_registo());
				ps.setString(6, ft.getHoraAtual());
				ps.execute();
				ps.close();
			}
			 
			ps.close();
			con.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	@SuppressWarnings("resource")
	public void novoMedicamentoInternado (Diverso ds){
		String sql = "Select * from tblinternamentomedicamentousado where FK_internamento = ? and FK_medicamento = ? and FK_enfermeiro = ? and data = curdate()";
		     
		try {
			con = Conexao.getConexao();
			PreparedStatement ps = con.prepareStatement(sql);
			ps.setInt(1, ds.getFK_internamento());
			ps.setInt(2, ds.getId_medicamento());
			ps.setInt(3, ds.getFK_funcionario());
			ResultSet rs = ps.executeQuery();
			if(rs.next()){
				int aux_qtd = ds.getQtd_medicamento() + rs.getInt("qtd");
				int cod_linha = rs.getInt("id_intern_med_usado");
				sql = "Update tblinternamentomedicamentousado set qtd = ?,hora = ? where id_intern_med_usado = ?";
				ps = con.prepareStatement(sql);
				ps.setInt(1, aux_qtd);
				ps.setString(2, ft.getHoraAtual());
				ps.setInt(3, cod_linha);
				ps.execute();
				ps.close();
			}
			else{
				 sql = "Insert into tblinternamentomedicamentousado (FK_internamento,FK_medicamento,qtd,FK_enfermeiro,data,hora) value (?,?,?,?,?,?)";
				ps = con.prepareStatement(sql);
				ps.setInt(1, ds.getFK_internamento());
				ps.setInt(2, ds.getId_medicamento());
				ps.setInt(3, ds.getQtd_medicamento());
				ps.setInt(4, ds.getFK_funcionario());
				ps.setDate(5, ft.data_registo());
				ps.setString(6, ft.getHoraAtual());
				ps.execute();
				ps.close();
			}
			 
			ps.close();
			con.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	public Diverso buscarMatedicamento(String aux){
		String sql = "Select * from tblmedicamentosbue where medicamento = ? ";
		Diverso ds = null;
		try {
			con = Conexao.getConexao();
			PreparedStatement ps = con.prepareStatement(sql);
			ps.setString(1, aux);
			ResultSet rs = ps.executeQuery();
			if(rs.next()){
				ds = new Diverso();
				ds.setMedicamento(rs.getString("medicamento"));
				ds.setId_medicamento(rs.getInt("id_medicamento"));
			}
			ps.close();
			con.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return ds;
	}
	
	
	public List<Diverso> buscarListaIntExamesComPreco (int  aux){
		String sql = "SELECT  *  FROM vwexamesatendidoslab WHERE  FK_internamento = ? ";
		List<Diverso> lista = new ArrayList<Diverso>();
		try {
			con = Conexao.getConexao();
			PreparedStatement ps = con.prepareStatement(sql);
			ps.setInt(1,aux);
			 
			ResultSet rs = ps.executeQuery();
			while(rs.next()){
				Diverso	ds = new Diverso();
				ds.setExame(rs.getString("analise_clinica"));
				ds.setFK_exame(rs.getInt("fk_exame"));
				ds.setQtd_exame(rs.getInt("qtd_servicos"));
				ds.setPreco(rs.getDouble("preco"));
				ds.setPreco_venda(rs.getDouble("preco_total"));
				ds.setFK_internamento(rs.getInt("FK_internamento"));
				lista.add(ds);
				
			}
			ps.close();
			con.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return lista;
	}
	
	public void transferirPaciente(int doc, int pac,int cons){
		String sql = "Update tbltriagem set FK_doutor = ? where FK_consulta_confirmada = ?";
		try {
			con = Conexao.getConexao();
			PreparedStatement ps = con.prepareStatement(sql);
			ps.setInt(1, doc);
			ps.setInt(2, pac);
			ps.execute();
			 
			ps.executeUpdate("Delete from tblantecedenteaessoal   where FK_consulta = "+cons);
			ps.executeUpdate("Delete from tblantecedenteaessoalhistorico   where FK_consulta = "+cons);
			ps.executeUpdate("Delete from tblexamefisico   where FK_consulta = "+cons);
			ps.executeUpdate("Delete from tblexamesclinicos   where FK_consulta = "+cons);
			ps.executeUpdate("Delete from tblhipotese   where FK_consulta = "+cons);
 
			
			ps.executeUpdate("Delete from tblhistoriadadoenca   where FK_consulta = "+cons);
			ps.executeUpdate("Delete from tblinternamentoconsulta   where FK_consulta = "+cons);
			ps.executeUpdate("Delete from tbldiagnostico   where FK_consulta = "+cons);
			ps.executeUpdate("Delete from tbltratamento   where FK_consulta = "+cons);
			ps.executeUpdate("Delete from tblreceituario   where FK_consulta = "+cons);
			ps.executeUpdate("Delete from tblrecomendacao   where FK_consulta = "+cons);
			ps.executeUpdate("Delete from tbljustificativo   where FK_consulta = "+cons);
			
			ps.executeUpdate("Delete from tblconsulta   where id_consulta = "+cons);
			
			ps.close();
			con.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}