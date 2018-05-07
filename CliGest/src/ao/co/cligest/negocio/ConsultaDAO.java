package ao.co.cligest.negocio;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

import org.jfree.data.general.DefaultPieDataset;

import ao.co.cligest.entidades.Paciente;
import ao.co.cligest.entidades.Receitas;
import ao.co.cligest.entidades.Triagem;

public class ConsultaDAO 
{
	private Connection con ;
	private Formatando ft = new Formatando();
	
	public void novaConsulta(Triagem fun)
	{
		
		String sql = "insert into tblconsulta (data_daconsulta,hora_daconsulta,fk_medico,fk_paciente,fk_servico) values(?,?,?,?,?)";
		try {
			 con = Conexao.getConexao();
			 PreparedStatement preparador = con.prepareStatement(sql);
			 preparador = con.prepareStatement(sql);
			 preparador.setDate(1, new java.sql.Date(Calendar.getInstance().getTimeInMillis()));
			 preparador.setString(2, ft.getHoraAtual());
			 preparador.setInt(3,fun.getFK_doutor());
			 preparador.setInt(4, fun.getFK_paciente());
			 preparador.setInt(5, fun.getId_servico());
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
	
	public Triagem buscarOstriado(int cod)
	{
		Triagem pac = null;
		String sql = "SELECT * FROM vwpacientetriado_nd where data = current_date() and fk_paciente =?";
		try
		{	
			con = Conexao.getConexao();
			 PreparedStatement preparador = con.prepareStatement(sql);
			 preparador.setInt(1, cod);
			 ResultSet rs = preparador.executeQuery();
			 if(rs.next())
			 {
				 pac = new Triagem();
				 pac.setFK_paciente(rs.getInt("fk_paciente"));
				 pac.setId_servico(rs.getInt("fk_servico"));
				 pac.setFK_doutor(rs.getInt("fk_doutor"));
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
		return pac;
	}
	public boolean checkConsulta(int cod){ 
		boolean pac = false;
		String sql = "SELECT * FROM tblconsulta where fk_paciente = ? and data_daconsulta = current_date() and id_consulta not in (select fk_consulta from tblconsultafinalizada)";
		try
		{
			con = Conexao.getConexao(); 
			PreparedStatement preparador = con.prepareStatement(sql);
			 preparador.setInt(1, cod);
			
			 ResultSet rs = preparador.executeQuery();
			 if(rs.next())
			   pac = true;
			 
			 preparador.close();
			 return pac;
		}
		catch(SQLException er){
			 System.out.println(er);
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
		return false;
	}
	
	public boolean checkInternado(int cod){ 
		boolean pac = false;
		String sql = "SELECT * FROM tblinternamento where fk_paciente = ? and data_internamento = current_date()";
		try
		{
			con = Conexao.getConexao(); 
			PreparedStatement preparador = con.prepareStatement(sql);
			 preparador.setInt(1, cod);
			
			 ResultSet rs = preparador.executeQuery();
			 if(rs.next())
			   pac = true;
			 
			 preparador.close();
			 return pac;
		}
		catch(SQLException er){
			 System.out.println(er);
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
		return false;
	}
	public Triagem getIdConsulta(int cod){ 
		 
		Triagem t = null;
		String sql = "SELECT * FROM tblconsulta where fk_paciente = ? and data_daconsulta = current_date() and id_consulta not in (select fk_consulta from tblconsultafinalizada)";
		try
		{
			con = Conexao.getConexao();
			 PreparedStatement preparador = con.prepareStatement(sql);
			 preparador.setInt(1, cod);
			
			 ResultSet rs = preparador.executeQuery();
			 if(rs.next()){
				 t = new Triagem();
				 t.setId_consulta(rs.getInt("id_consulta"));
				 t.setFK_doutor(rs.getInt("fk_medico"));
				 t.setFK_paciente(rs.getInt("fk_paciente"));
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
		return t;
	}
	
	public Triagem getQueixa(int cod){ 
		Triagem t = null;
		String sql = "SELECT * FROM tblqueixaprincipal where fk_consulta  = ?";
		try
		{
			con = Conexao.getConexao();
			 PreparedStatement preparador = con.prepareStatement(sql);
			 preparador.setInt(1, cod);
			 ResultSet rs = preparador.executeQuery();
			 if(rs.next()){
				 t = new Triagem();
				 t.setId_consulta(rs.getInt("fk_consulta"));
				 t.setDescricao(rs.getString("descricao"));
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
		return t;
	}
	
	public List<Triagem> getExamesClinicos(int cod){ 
		 
		List<Triagem> lista = new ArrayList<Triagem>();
		String sql = "SELECT * FROM vwexamesclinicosdatas where id_consulta  = ?";
		try
		{
			 con = Conexao.getConexao();
			 PreparedStatement preparador = con.prepareStatement(sql);
			 preparador.setInt(1, cod);
			 ResultSet rs = preparador.executeQuery();
			 while(rs.next())
			 {
				 Triagem t = new Triagem();
				 t.setAnalise(rs.getString("analise_clinica"));
				 t.setId_analise(rs.getInt("id_exames_clinicos"));
				 t.setDescricao(rs.getString("observacao"));
				 lista.add(t);
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
	
	public Triagem getExameFisicos(int cod){ 
		Triagem t = null;
		String sql = "SELECT * FROM tblexamefisico where fk_consulta  = ?";
		try
		{
			con = Conexao.getConexao();
			 PreparedStatement preparador = con.prepareStatement(sql);
			 preparador.setInt(1, cod);
			 ResultSet rs = preparador.executeQuery();
			 if(rs.next()){
				 t = new Triagem();
				 t.setId_consulta(rs.getInt("fk_consulta"));
				 t.setExa_cabeca(rs.getString("cabeca"));
				 t.setExa_pescoco(rs.getString("pescoco"));
				 t.setExa_abdomen(rs.getString("abdomen"));
				 t.setExa_membSup(rs.getString("membro_superior"));
				 t.setExa_membInf(rs.getString("mebro_inferior"));
				 t.setExa_torax(rs.getString("torax"));
				 t.setExa_urinario(rs.getString("genito_urinario"));
				 t.setObjectivo_geral(rs.getString("objectivo_geral"));
				 t.setSistema_nervoso(rs.getString("sistema_nervoso"));
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
		return t;
	}
	
	public Triagem getHistorial(int cod){ 
		Triagem t = null;
		String sql = "SELECT * FROM tblhistoriadadoenca where fk_consulta  = ?";
		try
		{
			con = Conexao.getConexao();
			 PreparedStatement preparador = con.prepareStatement(sql);
			 preparador.setInt(1, cod);
			 ResultSet rs = preparador.executeQuery();
			 if(rs.next()){
				 t = new Triagem();
				 t.setId_consulta(rs.getInt("fk_consulta"));
				 t.setDescricao(rs.getString("historico_dadoenca"));
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
		return t;
	}
	
	public Triagem getRecetuarioObs(int cod){ 
		Triagem t = null;
		String sql = "SELECT * FROM tblreceituarioobs where FK_consulta  = ?";
		try
		{
			con = Conexao.getConexao();
			 PreparedStatement preparador = con.prepareStatement(sql);
			 preparador.setInt(1, cod);
			 ResultSet rs = preparador.executeQuery();
			 if(rs.next()){
				 t = new Triagem();
				 t.setId_consulta(rs.getInt("FK_consulta"));
				 t.setDescricao(rs.getString("observacao"));
				 t.setFK_numero(rs.getInt("FK_receituario"));
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
		return t;
	}
	
	public Triagem getAntFamiliarObs(int cod){ 
		Triagem t = null;
		String sql = "SELECT * FROM tblantecedentefamiliarobs where FK_antecedente_familiar  = ?";
		try
		{	
			con = Conexao.getConexao();
			 PreparedStatement preparador = con.prepareStatement(sql);
			 preparador.setInt(1, cod);
			 ResultSet rs = preparador.executeQuery();
			 if(rs.next()){
				 t = new Triagem();
				 t.setId_consulta(rs.getInt("FK_antecedente_familiar"));
				 t.setDescricao(rs.getString("obs_antecedentefamiliar"));
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

		return t;
	}
	
	public void queixas(Triagem t)
	{
		String sql = "insert into tblqueixaprincipal (fk_consulta,descricao,fk_doutor,fk_paciente) values(?,?,?,?)";
		try {
			con = Conexao.getConexao(); 
			PreparedStatement preparador = con.prepareStatement(sql);
			 preparador = con.prepareStatement(sql);
			 preparador.setInt(1, t.getId_consulta());
			 preparador.setString(2, t.getDescricao());
			 preparador.setInt(3, t.getFK_doutor());
			 preparador.setInt(4, t.getFK_paciente());
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
	public void atualizarQueixas(Triagem t)
	{
		String sql = "update tblqueixaprincipal set descricao = ? where fk_consulta = ?";
		try {
			con = Conexao.getConexao(); 
			PreparedStatement preparador = con.prepareStatement(sql);
			 preparador = con.prepareStatement(sql);
			 preparador.setString(1, t.getDescricao());
			 preparador.setInt(2, t.getId_consulta());
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
	
	public void historial(Triagem t)
	{
		String sql = "insert into tblhistoriadadoenca (fk_consulta,historico_dadoenca,fk_doutor,fk_paciente) values(?,?,?,?)";
		try {
			con = Conexao.getConexao();
			 PreparedStatement preparador = con.prepareStatement(sql);
			 preparador = con.prepareStatement(sql);
			 preparador.setInt(1, t.getId_consulta());
			 preparador.setString(2, t.getHistorial());
			 preparador.setInt(3, t.getFK_doutor());
			 preparador.setInt(4, t.getFK_paciente());
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
	public void atualizarHistorial(Triagem t)
	{
		String sql = "update tblhistoriadadoenca set historico_dadoenca = ? where fk_consulta = ?";
		try {
			con = Conexao.getConexao();
			 PreparedStatement preparador = con.prepareStatement(sql);
			 preparador = con.prepareStatement(sql);
			 preparador.setString(1, t.getHistorial());
			 preparador.setInt(2, t.getId_consulta());
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
	public void recomendacao(Triagem t)
	{
		String sql = "insert into tblrecomendacao (fk_consulta,descricao_recomendacao) values(?,?)";
		try {
			con = Conexao.getConexao();
			 PreparedStatement preparador = con.prepareStatement(sql);
			 preparador = con.prepareStatement(sql);
			 preparador.setInt(1, t.getId_consulta());
			 preparador.setString(2, t.getRecomendacao());
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
	
	
	
	public void updateRecomendacao(Triagem t)
	{
		String sql = "update tblrecomendacao set descricao_recomendacao =? where fk_consulta = ?";
		try {
			con = Conexao.getConexao();
			 PreparedStatement preparador = con.prepareStatement(sql);
			 preparador = con.prepareStatement(sql);
			 preparador.setString(1, t.getRecomendacao());
			 preparador.setInt(2, t.getId_consulta());
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
	
	public void tratamento(Triagem t)
	{
		String sql = "insert into tbltratamento (fk_consulta,descricao_tratamento) values(?,?)";
		try {
			con = Conexao.getConexao();
			 PreparedStatement preparador = con.prepareStatement(sql);
			 preparador = con.prepareStatement(sql);
			 preparador.setInt(1, t.getId_consulta());
			 preparador.setString(2, t.getTratamento());
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
	
	public void updateTratamento(Triagem t)
	{
		String sql = "update tbltratamento set descricao_tratamento = ? where fk_consulta = ?";
		try {
			con = Conexao.getConexao();
			 PreparedStatement preparador = con.prepareStatement(sql);
			 preparador = con.prepareStatement(sql);
			 preparador.setString(1, t.getTratamento());
			 preparador.setInt(2, t.getId_consulta());
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
	
//	---------------------------------- verification os id´s da consulta cussunga tratamentos e Recomendações
	public Triagem getTratamento(int cod){ 
		Triagem t = null;
		String sql = "SELECT * FROM tbltratamento where fk_consulta  = ?";
		try
		{
			con = Conexao.getConexao();
			 PreparedStatement preparador = con.prepareStatement(sql);
			 preparador.setInt(1, cod);
			 ResultSet rs = preparador.executeQuery();
			 if(rs.next()){
				 t = new Triagem();
				 t.setId_consulta(rs.getInt("fk_consulta"));
				 t.setDescricao(rs.getString("descricao_tratamento"));
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
		return t;
	}
	
	public Triagem getRecomendacao(int cod){ 
		Triagem t = null;
		String sql = "SELECT * FROM tblrecomendacao where fk_consulta  = ?";
		try
		{
			con = Conexao.getConexao();
			 PreparedStatement preparador = con.prepareStatement(sql);
			 preparador.setInt(1, cod);
			 ResultSet rs = preparador.executeQuery();
			 if(rs.next()){
				 t = new Triagem();
				 t.setId_consulta(rs.getInt("fk_consulta"));
				 t.setDescricao(rs.getString("descricao_recomendacao"));
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
		return t;
	}
//	----------------------------------
	
	public void antecedentes(Triagem t)
	{
		String sql = "insert into tblantecedenteaessoal "
				+ "(fk_consulta,fk_paciente,fk_transfusao,fk_cardiopatia,fk_osteoporose,"
				+ "fk_tuberculose,fk_acidente,fk_tabagismo,fk_diabete,fk_cirurgia,fk_hta,fk_avc,fk_doenca_renal_cronica,"
				+ "fk_endocrinas_metabolica,fk_etilismo,fk_alergia,fk_anemia,fk_drogas,fk_virose,fk_cancro,fk_dts,fk_doutor) "
				+ "values(?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)";
		try {
			con = Conexao.getConexao();
			 PreparedStatement preparador = con.prepareStatement(sql);
			 preparador = con.prepareStatement(sql);
			 preparador.setInt(1, t.getId_consulta());
			 preparador.setInt(2, t.getFK_paciente());
			 preparador.setInt(3, t.getTransfusao());
			 preparador.setInt(4, t.getCardiopatia());
			 preparador.setInt(5, t.getOsteoporose());
			 preparador.setInt(6, t.getTuberculose());
			 preparador.setInt(7, t.getAcidente());
			 preparador.setInt(8, t.getTabagismo());
			 preparador.setInt(9, t.getDiabate());
			 preparador.setInt(10, t.getCirurgia());
			 preparador.setInt(11, t.getHta());
			 preparador.setInt(12, t.getAvc());
			 preparador.setInt(13, t.getDoenca_renal_cronica());
			 preparador.setInt(14, t.getEndocrinas_metabolica());
			 preparador.setInt(15, t.getEtilismo());
			 preparador.setInt(16, t.getAlergia());
			 preparador.setInt(17, t.getAnemia());
			 preparador.setInt(18, t.getDrogas());
			 preparador.setInt(19, t.getVirose());
			 preparador.setInt(20, t.getCancro());
			 preparador.setInt(21, t.getDts());
			 preparador.setInt(22, t.getFK_doutor());
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
	
	public void antecedentesAtual(Triagem t)
	{
		String sql = "update tblantecedenteaessoal set fk_transfusao = ?,fk_cardiopatia = ?,"
				   + "fk_osteoporose = ?, fk_tuberculose = ?,fk_acidente = ?,fk_tabagismo = ?,"
				   + "fk_diabete = ?,fk_cirurgia = ?, fk_hta = ?,fk_avc = ?,fk_doenca_renal_cronica = ?,"
				   + "fk_endocrinas_metabolica = ?,fk_etilismo = ?,fk_alergia = ?,fk_anemia = ?,fk_drogas = ?,"
				   + "fk_virose = ?,fk_cancro = ?,fk_dts = ?,fk_doutor = ? where fk_paciente = ? ";
		try {
			con = Conexao.getConexao();
			 PreparedStatement preparador = con.prepareStatement(sql);
			 preparador = con.prepareStatement(sql);		
			 preparador.setInt(1, t.getTransfusao());
			 preparador.setInt(2, t.getCardiopatia());
			 preparador.setInt(3, t.getOsteoporose());
			 preparador.setInt(4, t.getTuberculose());
			 preparador.setInt(5, t.getAcidente());
			 preparador.setInt(6, t.getTabagismo());
			 preparador.setInt(7, t.getDiabate());
			 preparador.setInt(8, t.getCirurgia());
			 preparador.setInt(9, t.getHta());
			 preparador.setInt(10, t.getAvc());
			 preparador.setInt(11, t.getDoenca_renal_cronica());
			 preparador.setInt(12, t.getEndocrinas_metabolica());
			 preparador.setInt(13, t.getEtilismo());
			 preparador.setInt(14, t.getAlergia());
			 preparador.setInt(15, t.getAnemia());
			 preparador.setInt(16, t.getDrogas());
			 preparador.setInt(17, t.getVirose());
			 preparador.setInt(18, t.getCancro());
			 preparador.setInt(19, t.getDts());
			 preparador.setInt(20, t.getFK_doutor());
			 preparador.setInt(21, t.getFK_paciente());
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
	
	public void ant_familiar(Triagem t)
	{
		String sql = "insert into tblantecedentefamiliar (fk_paciente,fk_doenca,membro_um,membro_dois,membro_tres,membro_quatro,membro_cinco) values(?,?,?,?,?,?,?)";
		try {
			 con = Conexao.getConexao();
			 PreparedStatement preparador = con.prepareStatement(sql);
			 preparador = con.prepareStatement(sql);
			 preparador.setInt(1, t.getFK_paciente());
			 preparador.setInt(2, t.getFk_doenca());
			 preparador.setInt(3, t.getMembro_um());
			 preparador.setInt(4, t.getMembro_dois());
			 preparador.setInt(5, t.getMembro_tres());
			 preparador.setInt(6, t.getMembro_quatro());
			 preparador.setInt(7, t.getMembro_cinco());
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
	
	public void ant_familiarAtualizar(Triagem t)
	{
		String sql = "update tblantecedentefamiliar set membro_um = ?,membro_dois = ?,membro_tres = ?,membro_quatro = ?,membro_cinco = ? where fk_doenca = ? and fk_paciente = ?";
		try {
			con = Conexao.getConexao();
			 PreparedStatement preparador = con.prepareStatement(sql);
			 preparador = con.prepareStatement(sql);
			 preparador.setInt(1, t.getMembro_um());
			 preparador.setInt(2, t.getMembro_dois());
			 preparador.setInt(3, t.getMembro_tres());
			 preparador.setInt(4, t.getMembro_quatro());
			 preparador.setInt(5, t.getMembro_cinco());
			 preparador.setInt(6, t.getFk_doenca());
			 preparador.setInt(7, t.getFK_paciente());
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
	public void antecentePesHistorico(Triagem t)
	{
		String sql = "insert into tblantecedenteaessoalhistorico (FK_consulta, FK_paciente,FK_ant_pessoal_doenca) values(?,?,?)";
		try {
			con = Conexao.getConexao();
			 PreparedStatement preparador = con.prepareStatement(sql);
			 preparador = con.prepareStatement(sql);
			 preparador.setInt(1, t.getId_consulta());
			 preparador.setInt(2, t.getFK_paciente());
			 preparador.setInt(3, t.getFk_doenca());		  
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
	// Pendente Ate segundas ideias -------------
	public void antecentePesHistoricoAtual(Triagem t)
	{
		String sql = "update tblantecedenteaessoalhistorico set FK_consulta = ?,FK_ant_pessoal_doenca = ? where  FK_paciente = ?";
		try {
			con = Conexao.getConexao();
			 PreparedStatement preparador = con.prepareStatement(sql);
			 preparador = con.prepareStatement(sql);
			 preparador.setInt(1, t.getId_consulta());
			 preparador.setInt(3, t.getFk_doenca());		  
			 preparador.setInt(3, t.getFK_paciente());
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
	public void antecenteObs(Triagem t)
	{
		String sql = "insert into tblantecedenteaessoalobs (FK_antecedente_pessoal, obs_antecedentepessoal) values(?,?)";
		try {
			con = Conexao.getConexao();
			 PreparedStatement preparador = con.prepareStatement(sql);
			 preparador = con.prepareStatement(sql);
			 preparador.setInt(1, t.getId_consulta());
			 preparador.setString(2, t.getDescricao());
			
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
	
	public void antecenteObsAtual(Triagem t)
	{
		String sql = "update tblantecedenteaessoalobs set obs_antecedentepessoal = ? where FK_antecedente_pessoal = ?";
		try {
			con = Conexao.getConexao();
			 PreparedStatement preparador = con.prepareStatement(sql);
			 preparador = con.prepareStatement(sql);
			 preparador.setInt(2, t.getId_consulta());
			 preparador.setString(1, t.getDescricao());
			
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
	
	public void ant_familiarObs(Triagem t)
	{
		String sql = "insert into tblantecedentefamiliarobs (FK_antecedente_familiar, obs_antecedentefamiliar) values(?,?)";
		try {
			con = Conexao.getConexao();
			 PreparedStatement preparador = con.prepareStatement(sql);
			 preparador = con.prepareStatement(sql);
			 preparador.setInt(1, t.getFK_paciente());
			 preparador.setString(2, t.getDescricao());	
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
	
	public void antualiFamiliarObs(Triagem t)
	{
		String sql = "update tblantecedentefamiliarobs set obs_antecedentefamiliar = ?  where FK_antecedente_familiar = ?";
		try {
			 con = Conexao.getConexao();
			 PreparedStatement preparador = con.prepareStatement(sql);
			 preparador = con.prepareStatement(sql);
			 preparador.setInt(2, t.getFK_paciente());
			 preparador.setString(1, t.getDescricao());			
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
	public void hipoteseObs(Triagem t)
	{
		String sql = "insert into tblhipoteseobs (fk_hipotese, obs_hipotese) values(?,?)";
		try {
			con = Conexao.getConexao();
			 PreparedStatement preparador = con.prepareStatement(sql);
			 preparador = con.prepareStatement(sql);
			 preparador.setInt(1, t.getId_consulta());
			 preparador.setString(2, t.getDescricao());
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
	
	public void atualHipoteseObs(Triagem t)
	{
		String sql = "update tblhipoteseobs set obs_hipotese = ? where fk_hipotese = ?";
		try {
			con = Conexao.getConexao();
			 PreparedStatement preparador = con.prepareStatement(sql);
			 preparador = con.prepareStatement(sql);
			 preparador.setString(1, t.getDescricao());
			 preparador.setInt(2, t.getId_consulta());
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
	
	public Triagem gethipoteseObs(int cod){ 
		Triagem t = null;
		String sql = "SELECT * FROM tblhipoteseobs where FK_hipotese  = ?";
		try
		{
			con = Conexao.getConexao();
			 PreparedStatement preparador = con.prepareStatement(sql);
			 preparador.setInt(1, cod);
			 ResultSet rs = preparador.executeQuery();
			 if(rs.next()){
				 t = new Triagem();
				 t.setId_consulta(rs.getInt("FK_hipotese"));
				 t.setDescricao(rs.getString("obs_hipotese"));
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
		return t;
	}
	public void diagnosticoObs(Triagem t)
	{
		String sql = "insert into tbldiagnosticoobs (FK_diagnostico, obs_diagnostico) values(?,?)";
		try {
			con = Conexao.getConexao();
			 PreparedStatement preparador = con.prepareStatement(sql);
			 preparador = con.prepareStatement(sql);
			 preparador.setInt(1, t.getId_consulta());
			 preparador.setString(2, t.getDescricao());
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
	
	public void diagnosticoObsAtual(Triagem t)
	{
		String sql = "update tbldiagnosticoobs set obs_diagnostico = ? where FK_diagnostico = ? ";
		try {
			con = Conexao.getConexao();
			 PreparedStatement preparador = con.prepareStatement(sql);
			 preparador = con.prepareStatement(sql);
			 preparador.setString(1, t.getDescricao());
			 preparador.setInt(2, t.getId_consulta());
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
	
	public int diagnosticoObsXeck(Triagem t)
	{
		int rss = 0;
		String sql = "select * from tbldiagnosticoobs where FK_diagnostico = ? ";
		try {
			con = Conexao.getConexao();
			 PreparedStatement preparador = con.prepareStatement(sql);
			 preparador = con.prepareStatement(sql);
			 preparador.setInt(1, t.getId_consulta());
			 ResultSet rs = preparador.executeQuery();
			 if(rs.next()){
				 rss = 1;
			 }
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
		return rss;
	}
	
	public void exam_fisicos(Triagem t)
	{
		String sql = "insert into tblexamefisico "
				+ "(fk_consulta,cabeca,pescoco,membro_superior,torax,abdomen,genito_urinario,mebro_inferior,objectivo_geral,sistema_nervoso)"
				+ "values(?,?,?,?,?,?,?,?,?,?)";
		try {
			con = Conexao.getConexao();
			 PreparedStatement preparador = con.prepareStatement(sql);
			 preparador = con.prepareStatement(sql);
			 preparador.setInt(1, t.getId_consulta());
			 preparador.setString(2, t.getExa_cabeca());
			 preparador.setString(3, t.getExa_pescoco());
			 preparador.setString(4, t.getExa_membSup());
			 preparador.setString(5, t.getExa_torax());
			 preparador.setString(6, t.getExa_abdomen());
			 preparador.setString(7, t.getExa_urinario());
			 preparador.setString(8, t.getExa_membInf());
			 preparador.setString(9, t.getObjectivo_geral());
			 preparador.setString(10, t.getSistema_nervoso());
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
	
	public void atualizarExam_fisicos(Triagem t)
	{
		String sql = "update tblexamefisico set cabeca = ?,pescoco = ?,membro_superior = ?,torax = ?, abdomen = ?,genito_urinario = ?,mebro_inferior = ?,objectivo_geral =?,sistema_nervoso=? where fk_consulta = ?";
		try {
			 con = Conexao.getConexao();
			 PreparedStatement preparador = con.prepareStatement(sql);
			 preparador = con.prepareStatement(sql);
			 preparador.setString(1, t.getExa_cabeca());
			 preparador.setString(2, t.getExa_pescoco());
			 preparador.setString(3, t.getExa_membSup());
			 preparador.setString(4, t.getExa_torax());
			 preparador.setString(5, t.getExa_abdomen());
			 preparador.setString(6, t.getExa_urinario());
			 preparador.setString(7, t.getExa_membInf());
			 preparador.setString(8, t.getObjectivo_geral());
			 preparador.setString(9, t.getSistema_nervoso());
			 preparador.setInt(10, t.getId_consulta());
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
	
	public void diagnosticos(Triagem t)
	{
		String sql = "insert into tbldiagnostico (fk_consulta,cid) values(?,?)";
		try {
			 con = Conexao.getConexao();
			 PreparedStatement preparador = con.prepareStatement(sql);
			 preparador = con.prepareStatement(sql);
			 preparador.setInt(1, t.getId_consulta());
			 preparador.setString(2, t.getFk_cid());
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
	public void examesclinicos(Triagem t)
	{
		String sql = "insert into tblexamesclinicos (fk_consulta,FK_servicodeanalise_clinica,FK_numero_requisicao) values(?,?,?)";
		try {
			con = Conexao.getConexao();
			 PreparedStatement preparador = con.prepareStatement(sql);
			 preparador = con.prepareStatement(sql);
			 preparador.setInt(1, t.getId_consulta());
			 preparador.setInt(2, t.getId_analise());
			 preparador.setInt(3, t.getFK_numero_requisicao());
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
	public void ExamescliObs(Triagem t)
	{
		String sql = "insert tblexamesclinicosobs (obs_exame_clinico ,FK_exames_clinicos) values(?,?)";
		try {
			con = Conexao.getConexao();
			 PreparedStatement preparador = con.prepareStatement(sql);
			 preparador = con.prepareStatement(sql);
			 preparador.setString(1, t.getDescricao());
			 preparador.setInt(2, t.getId_consulta());
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
	public void atualExamescliObs(Triagem t)
	{
		String sql = "Update tblexamesclinicosobs set obs_exame_clinico = ? where FK_exames_clinicos = ?";
		try {
			con = Conexao.getConexao();
			 PreparedStatement preparador = con.prepareStatement(sql);
			 preparador = con.prepareStatement(sql);
			 preparador.setString(1, t.getDescricao());
			 preparador.setInt(2, t.getId_consulta());
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
	public String novaReceita(){
		String sql = "Select Max(id_receita) cod from tblreceitanumero ";
		int COD = 0;
		try {
			con = Conexao.getConexao();
			PreparedStatement ps = con.prepareStatement(sql);
			ResultSet rs = ps.executeQuery();
			if(rs.next())
				COD = rs.getInt("cod") + 1;
			ps.execute();
			
			ps.close();
			con.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return "REC"+COD;
	}
	public int numReceita(Receitas rc){
		int COD = 0;
		String sql = "Insert into tblreceitanumero (numero,data_receita,hora_receita,FK_funcionario,FK_paciente) value (?,?,?,?,?)";
		try {
			con = Conexao.getConexao();
			PreparedStatement ps = con.prepareStatement(sql);
			ps.setString(1, rc.getNumero());
			ps.setDate(2, new Formatando().data_registo());
			ps.setString(3, new Formatando().getHoraAtual());
			ps.setInt(4, rc.getFK_funcionario());
			ps.setInt(5, rc.getFK_paciente());
			ps.execute();
			ResultSet rs = ps.executeQuery("SELECT LAST_INSERT_ID()");
			if(rs.next())
				COD = rs.getInt(1);
			ps.close();
			con.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return COD;
	}
	public void recetuario(Triagem t)
	{
		String sql = "insert into tblreceituario (fk_consulta,fk_farmaco,quantidade,fk_dosagem,possologia,FK_via_administrar,FK_numero) values(?,?,?,?,?,?,?)";
		try {
			con = Conexao.getConexao();
			 PreparedStatement preparador = con.prepareStatement(sql);
			 preparador = con.prepareStatement(sql);
			 preparador.setInt(1, t.getId_consulta());
			 preparador.setInt(2, t.getFk_farmaco());
			 preparador.setInt(3, t.getQuantidade());
			 preparador.setInt(4, t.getFk_dosagem());
			 preparador.setString(5, t.getPossologia());
			 preparador.setInt(6, t.getFk_vidAd());
			 preparador.setInt(7, t.getFK_numero());
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
	
	public void excluirUmFarmaReceita(int FK_cons,int FK_farm)
	{
		String sql = "Delete from tblreceituario where fk_consulta = ? and fk_farmaco = ?";
		try {
			con = Conexao.getConexao();
			 PreparedStatement preparador = con.prepareStatement(sql);
			 preparador = con.prepareStatement(sql);
			 preparador.setInt(1, FK_cons);
			 preparador.setInt(2, FK_farm);
			 
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
	
	public void recetuarioObs(Triagem t)
	{
		String sql = "insert into tblreceituarioobs (fk_receituario,observacao,fk_consulta) values(?,?,?)";
		try {
			con = Conexao.getConexao();
			 PreparedStatement preparador = con.prepareStatement(sql);
			 preparador = con.prepareStatement(sql);
			 preparador.setInt(1, t.getFK_numero());
			 preparador.setString(2, t.getDescricao());
			 preparador.setInt(3, t.getId_consulta());
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
	
	public void atualRecetuarioObs(Triagem t)
	{
		String sql = "update tblreceituarioobs set observacao = ? where fk_consulta = ?";
		try {
			con = Conexao.getConexao();
			 PreparedStatement preparador = con.prepareStatement(sql);
			 preparador = con.prepareStatement(sql);
			 preparador.setString(1, t.getDescricao());
			 preparador.setInt(2, t.getId_consulta());
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
	public void hipotese(Triagem t)
	{
		String sql = "insert into tblhipotese (fk_consulta,cid_hipotese) values(?,?)";
		try {
			con = Conexao.getConexao();
			 PreparedStatement preparador = con.prepareStatement(sql);
			 preparador = con.prepareStatement(sql);
			 preparador.setInt(1, t.getId_consulta());
			 preparador.setString(2, t.getFk_cid());
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
	
	public void internamento(Triagem t)
	{
		int cod = 0;
		String sql = "insert into tblinternamento (modo_internamento,local_proveniencia,data_internamento,hora_internamento,fk_doutor,fk_paciente,fk_sector,fk_sala,fk_leito) values(?,?,?,?,?,?,?,?,?)";
		try {
			con = Conexao.getConexao();
			 PreparedStatement preparador = con.prepareStatement(sql);
			  
			 preparador.setInt(1, t.getModo_internar());
			 preparador.setString(2,t.getLocal_prov());
			 preparador.setDate(3, new Formatando().data_registo());
			 preparador.setString(4,  ft.getHoraAtual());
			 preparador.setInt(5, t.getFK_doutor());
			 preparador.setInt(6, t.getFK_paciente());
			 preparador.setInt(7, t.getFk_sector());
			 preparador.setInt(8, t.getFk_sala());
			 preparador.setInt(9, t.getFk_leito());
			 preparador.execute();
			 ResultSet rs = preparador.executeQuery("SELECT LAST_INSERT_ID()");
			 if(rs.next()){
				cod = rs.getInt(1); 
				t.setId_internamento(cod);
			 }
			 sql = "Insert into tblinternamentoconsulta (FK_consulta,FK_internamento) value (?,?)";
			 preparador = con.prepareStatement(sql);
			 preparador.setInt(1, t.getId_consulta());
			 preparador.setInt(2, t.getId_internamento());
			 preparador.execute();
			 
			 sql = "Update tblleito set status = ? where id_leito = ? and FK_sala = ?";
			 preparador = con.prepareStatement(sql);
			 preparador.setString(1, t.getStatus());
			 preparador.setInt(2, t.getFk_leito());
			 preparador.setInt(3, t.getFk_sala());
			 preparador.execute();
			 
			 preparador.close();
			
		} catch (SQLException e) {
			System.err.println(e.getMessage());
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
	public void conFirmarInternamento(Triagem t)
	{
		String sql = "Update tblinternamento set fk_sala = ?, fk_leito = ? where id_internamento = ?";
		try {
			con = Conexao.getConexao();
			 PreparedStatement preparador = con.prepareStatement(sql);
			 preparador = con.prepareStatement(sql);
			 preparador.setInt(1, t.getFk_sala());
			 preparador.setInt(2, t.getFk_leito());
			 preparador.setInt(3, t.getId_internamento());
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
	public void delAntecedentes(int cod)
	{
		String sql = "delete from tblantecedenteaessoal where fk_consulta = ? ";
		try {
			con = Conexao.getConexao();
			 PreparedStatement preparador = con.prepareStatement(sql);
			 preparador = con.prepareStatement(sql);
			 preparador.setInt(1, cod);
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
	public void delHistorial(int cod)
	{
		String sql = "delete from tblhistoriadadoenca where fk_consulta = ? ";
		try {
			con = Conexao.getConexao();
			 PreparedStatement preparador = con.prepareStatement(sql);
			 preparador = con.prepareStatement(sql);
			 preparador.setInt(1, cod);
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
	
	public void delHistorialAntecedesP(int cod)
	{
		String sql = "delete from tblantecedenteaessoalhistorico where fk_consulta = ? ";
		try {
			con = Conexao.getConexao();
			 PreparedStatement preparador = con.prepareStatement(sql);
			 preparador = con.prepareStatement(sql);
			 preparador.setInt(1, cod);
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
	
	public void delHipotese(String cod)
	{
		String sql = "delete from tblhipotese where cid_hipotese Like ? ";
		try {
			con = Conexao.getConexao();
			 PreparedStatement preparador = con.prepareStatement(sql);
			 preparador = con.prepareStatement(sql);
			 preparador.setString(1, cod);
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
	public void delExamesClinicos(String cod)
	{
		String sql = "delete from tblexamesclinicos where id_exames_clinicos Like ? ";
		try {
			con = Conexao.getConexao();
			 PreparedStatement preparador = con.prepareStatement(sql);
			 preparador = con.prepareStatement(sql);
			 preparador.setString(1, cod);
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
	public void delDiagnostico(String cod)
	{
		String sql = "delete from tbldiagnostico where cid Like ? ";
		try {
			con = Conexao.getConexao();
			 PreparedStatement preparador = con.prepareStatement(sql);
			 preparador = con.prepareStatement(sql);
			 preparador.setString(1, cod);
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
	
	public void delQueixas(int cod)
	{
		String sql = "delete from tblqueixaprincipal where fk_consulta = ? ";
		try {
			con = Conexao.getConexao();
			 PreparedStatement preparador = con.prepareStatement(sql);
			 preparador = con.prepareStatement(sql);
			 preparador.setInt(1, cod);
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
	public void finalizarConsulta(Triagem c)
	{
		String sql = "Insert into tblconsultafinalizada (fk_consulta,fk_consulta_confirmada,data,hora) values(?,?,?,?)";
		try {
			con = Conexao.getConexao();
			 PreparedStatement preparador = con.prepareStatement(sql);
			 preparador = con.prepareStatement(sql);
			 preparador.setInt(1, c.getId_consulta());
			 preparador.setInt(2, c.getFk_consulta_confirmada());
			 preparador.setDate(3, ft.data_registo());
			 preparador.setString(4, ft.getHoraAtual());
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
	public void delConsulta(int cons)
	{
		String sql = "delete from tblconsulta where id_consulta = ? and data_daconsulta = current_date()";
		try {
			 	con = Conexao.getConexao();
			 	PreparedStatement ps = con.prepareStatement(sql);
			 	ps = con.prepareStatement(sql);
			 	ps.setInt(1, cons);
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
			 
				ps.executeUpdate("Delete from tblconsulta   where id_consulta = "+cons+" and data_daconsulta = current_date()");
				ps.close();
			
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
	
	// Obtendo Dados relacionados a doenï¿½as de historial de paciente
	
	public Triagem getTuberculose(int cod){ 
		Triagem t = null;
		String sql = "SELECT * FROM tblantecedentefamiliar where fk_paciente  = ? and FK_doenca = 1";
		try
		{
			con = Conexao.getConexao();
			 PreparedStatement preparador = con.prepareStatement(sql);
			 preparador.setInt(1, cod);
			 ResultSet rs = preparador.executeQuery();
			 if(rs.next()){
				 t = new Triagem();
				 t.setMembro_um(rs.getInt("membro_um"));
				 t.setMembro_dois(rs.getInt("membro_dois"));
				 t.setMembro_tres(rs.getInt("membro_tres"));
				 t.setMembro_quatro(rs.getInt("membro_quatro"));
				 t.setMembro_cinco(rs.getInt("membro_cinco"));
				 t.setFK_paciente(rs.getInt("fk_paciente"));
			 }
			  preparador.close(); 
		}
		catch(SQLException er){	throw new RuntimeException(er);	}
		catch (Exception e) {	e.printStackTrace();	}
		finally{
			try{
				con.close();
			}
			catch(SQLException ef){	System.out.println("Erro finalizar: "+ef);	}
		}

		return t;
	}
	
	public Triagem getAsmaBronquial(int cod){ 
		Triagem t = null;
		String sql = "SELECT * FROM tblantecedentefamiliar where fk_paciente  = ? and FK_doenca = 2";
		try
		{
			 con = Conexao.getConexao();
			 PreparedStatement preparador = con.prepareStatement(sql);
			 preparador.setInt(1, cod);
			 ResultSet rs = preparador.executeQuery();
			 if(rs.next()){
				 t = new Triagem();
				 t.setMembro_um(rs.getInt("membro_um"));
				 t.setMembro_dois(rs.getInt("membro_dois"));
				 t.setMembro_tres(rs.getInt("membro_tres"));
				 t.setMembro_quatro(rs.getInt("membro_quatro"));
				 t.setMembro_cinco(rs.getInt("membro_cinco"));
				 t.setFK_paciente(rs.getInt("fk_paciente"));
			 }
			  preparador.close(); 
		}
		catch(SQLException er){	throw new RuntimeException(er);	}
		catch (Exception e) {	e.printStackTrace();	}
		finally{
			try{
				con.close();
			}
			catch(SQLException ef){	System.out.println("Erro finalizar: "+ef);	}
		}
//		System.out.println("OK: "+t.getMembro_um());
		return t;
	}
	
	public Triagem getDiabetes(int cod){ 
		Triagem t = null;
		String sql = "SELECT * FROM tblantecedentefamiliar where fk_paciente  = ? and FK_doenca = 4";
		try
		{
			 con = Conexao.getConexao();
			 PreparedStatement preparador = con.prepareStatement(sql);
			 preparador.setInt(1, cod);
			 ResultSet rs = preparador.executeQuery();
			 if(rs.next()){
				 t = new Triagem();
				 t.setMembro_um(rs.getInt("membro_um"));
				 t.setMembro_dois(rs.getInt("membro_dois"));
				 t.setMembro_tres(rs.getInt("membro_tres"));
				 t.setMembro_quatro(rs.getInt("membro_quatro"));
				 t.setMembro_cinco(rs.getInt("membro_cinco"));
				 t.setFK_paciente(rs.getInt("fk_paciente"));
			 }
			  preparador.close(); 
		}
		catch(SQLException er){	throw new RuntimeException(er);	}
		catch (Exception e) {	e.printStackTrace();	}
		finally{
			try{
				con.close();
			}
			catch(SQLException ef){	System.out.println("Erro finalizar: "+ef);	}
		}
		return t;
	}
	
	public Triagem getEpilepsia(int cod){ 
		Triagem t = null;
		String sql = "SELECT * FROM tblantecedentefamiliar where fk_paciente  = ? and FK_doenca = 3";
		try
		{
			 con = Conexao.getConexao();
			 PreparedStatement preparador = con.prepareStatement(sql);
			 preparador.setInt(1, cod);
			 ResultSet rs = preparador.executeQuery();
			 if(rs.next()){
				 t = new Triagem();
				 t.setMembro_um(rs.getInt("membro_um"));
				 t.setMembro_dois(rs.getInt("membro_dois"));
				 t.setMembro_tres(rs.getInt("membro_tres"));
				 t.setMembro_quatro(rs.getInt("membro_quatro"));
				 t.setMembro_cinco(rs.getInt("membro_cinco"));
				 t.setFK_paciente(rs.getInt("fk_paciente"));
			 }
			  preparador.close(); 
		}
		catch(SQLException er){	throw new RuntimeException(er);	}
		catch (Exception e) {	e.printStackTrace();	}
		finally{
			try{
				con.close();
			}
			catch(SQLException ef){	System.out.println("Erro finalizar: "+ef);	}
		}
		return t;
	}
	
	public Triagem getHepatite(int cod){ 
		Triagem t = null;
		String sql = "SELECT * FROM tblantecedentefamiliar where fk_paciente  = ? and FK_doenca = 5";
		try
		{
			 con = Conexao.getConexao();
			 PreparedStatement preparador = con.prepareStatement(sql);
			 preparador.setInt(1, cod);
			 ResultSet rs = preparador.executeQuery();
			 if(rs.next()){
				 t = new Triagem();
				 t.setMembro_um(rs.getInt("membro_um"));
				 t.setMembro_dois(rs.getInt("membro_dois"));
				 t.setMembro_tres(rs.getInt("membro_tres"));
				 t.setMembro_quatro(rs.getInt("membro_quatro"));
				 t.setMembro_cinco(rs.getInt("membro_cinco"));
				 t.setFK_paciente(rs.getInt("fk_paciente"));
			 }
			  preparador.close(); 
		}
		catch(SQLException er){	throw new RuntimeException(er);	}
		catch (Exception e) {	e.printStackTrace();	}
		finally{
			try{
				con.close();
			}
			catch(SQLException ef){	System.out.println("Erro finalizar: "+ef);	}
		}
		return t;
	}
	
	public Triagem getAnemia(int cod){ 
		Triagem t = null;
		String sql = "SELECT * FROM tblantecedentefamiliar where fk_paciente  = ? and FK_doenca = 6";
		try
		{
			 con = Conexao.getConexao();
			 PreparedStatement preparador = con.prepareStatement(sql);
			 preparador.setInt(1, cod);
			 ResultSet rs = preparador.executeQuery();
			 if(rs.next()){
				 t = new Triagem();
				 t.setMembro_um(rs.getInt("membro_um"));
				 t.setMembro_dois(rs.getInt("membro_dois"));
				 t.setMembro_tres(rs.getInt("membro_tres"));
				 t.setMembro_quatro(rs.getInt("membro_quatro"));
				 t.setMembro_cinco(rs.getInt("membro_cinco"));
				 t.setFK_paciente(rs.getInt("fk_paciente"));
			 }
			  preparador.close(); 
		}
		catch(SQLException er){	throw new RuntimeException(er);	}
		catch (Exception e) {	e.printStackTrace();	}
		finally{
			try{
				con.close();
			}
			catch(SQLException ef){	System.out.println("Erro finalizar: "+ef);	}
		}
		return t;
	}
	
	public Triagem getCancro(int cod){ 
		Triagem t = null;
		String sql = "SELECT * FROM tblantecedentefamiliar where fk_paciente  = ? and FK_doenca = 7";
		try
		{
			 con = Conexao.getConexao();
			 PreparedStatement preparador = con.prepareStatement(sql);
			 preparador.setInt(1, cod);
			 ResultSet rs = preparador.executeQuery();
			 if(rs.next()){
				 t = new Triagem();
				 t.setMembro_um(rs.getInt("membro_um"));
				 t.setMembro_dois(rs.getInt("membro_dois"));
				 t.setMembro_tres(rs.getInt("membro_tres"));
				 t.setMembro_quatro(rs.getInt("membro_quatro"));
				 t.setMembro_cinco(rs.getInt("membro_cinco"));
				 t.setFK_paciente(rs.getInt("fk_paciente"));
			 }
			  preparador.close(); 
		}
		catch(SQLException er){	throw new RuntimeException(er);	}
		catch (Exception e) {	e.printStackTrace();	}
		finally{
			try{
				con.close();
			}
			catch(SQLException ef){	System.out.println("Erro finalizar: "+ef);	}
		}
		return t;
	}
	
	public Triagem getHTA(int cod){ 
		Triagem t = null;
		String sql = "SELECT * FROM tblantecedentefamiliar where fk_paciente  = ? and FK_doenca = 8";
		try
		{
			 con = Conexao.getConexao();
			 PreparedStatement preparador = con.prepareStatement(sql);
			 preparador.setInt(1, cod);
			 ResultSet rs = preparador.executeQuery();
			 if(rs.next()){
				 t = new Triagem();
				 t.setMembro_um(rs.getInt("membro_um"));
				 t.setMembro_dois(rs.getInt("membro_dois"));
				 t.setMembro_tres(rs.getInt("membro_tres"));
				 t.setMembro_quatro(rs.getInt("membro_quatro"));
				 t.setMembro_cinco(rs.getInt("membro_cinco"));
				 t.setFK_paciente(rs.getInt("fk_paciente"));
			 }
			  preparador.close(); 
		}
		catch(SQLException er){	throw new RuntimeException(er);	}
		catch (Exception e) {	e.printStackTrace();	}
		finally{
			try{
				con.close();
			}
			catch(SQLException ef){	System.out.println("Erro finalizar: "+ef);	}
		}
		return t;
	}
	
	public Triagem getAVC(int cod){ 
		Triagem t = null;
		String sql = "SELECT * FROM tblantecedentefamiliar where fk_paciente  = ? and FK_doenca = 9";
		try
		{
			 con = Conexao.getConexao();
			 PreparedStatement preparador = con.prepareStatement(sql);
			 preparador.setInt(1, cod);
			 ResultSet rs = preparador.executeQuery();
			 if(rs.next()){
				 t = new Triagem();
				 t.setMembro_um(rs.getInt("membro_um"));
				 t.setMembro_dois(rs.getInt("membro_dois"));
				 t.setMembro_tres(rs.getInt("membro_tres"));
				 t.setMembro_quatro(rs.getInt("membro_quatro"));
				 t.setMembro_cinco(rs.getInt("membro_cinco"));
				 t.setFK_paciente(rs.getInt("fk_paciente"));
			 }
			  preparador.close(); 
		}
		catch(SQLException er){	throw new RuntimeException(er);	}
		catch (Exception e) {	e.printStackTrace();	}
		finally{
			try{
				con.close();
			}
			catch(SQLException ef){	System.out.println("Erro finalizar: "+ef);	}
		}
		return t;
	}
//	------------------------ Cussunga
	public List<Triagem> buscarAtendimentoFeito(){ 
		 
		List<Triagem> lista = new ArrayList<Triagem>();
		String sql = "select n_c_funcionario, fk_medico, data_daconsulta, count(id_consulta) as 'qtd' "
					+ "from vwrelpacientesconsultados "
					+ "where data_daconsulta = curdate() "
					+ "group by fk_medico";
		try
		{
			 con = Conexao.getConexao();
			 PreparedStatement preparador = con.prepareStatement(sql);
			 ResultSet rs = preparador.executeQuery();
			 while(rs.next())
			 {
				 Triagem pac = new Triagem();
				 Calendar data = Calendar.getInstance();
				 data.setTime(rs.getDate("data_daconsulta"));
				 pac.setDataConsulta(data);
				 pac.setNomem(rs.getString("n_c_funcionario"));
				 pac.setFK_medico(rs.getInt("fk_medico"));
				 pac.setQuantidade(rs.getInt("qtd"));
				 lista.add(pac);
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
	public List<Triagem> buscarAtendimentoFeitoTeste(){ 
		 
		List<Triagem> lista = new ArrayList<Triagem>();
		String sql = "select n_c_funcionario, fk_medico from vwrelpacientesconsultados where data_daconsulta = curdate() group by fk_medico";
		try
		{
			 con = Conexao.getConexao();
			 PreparedStatement preparador = con.prepareStatement(sql);
			 ResultSet rs = preparador.executeQuery();
			 while(rs.next())
			 {
				 Triagem pac = new Triagem();
				 pac.setNomem(rs.getString("n_c_funcionario"));
				 pac.setFK_medico(rs.getInt("fk_medico"));
				 lista.add(pac);
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
	public List<Triagem> buscarAtendimentoFeitoTesteM(String medico){ 
		 
		List<Triagem> lista = new ArrayList<Triagem>();
		String sql = "select n_c_funcionario, fk_medico from vwrelpacientesconsultados where n_c_funcionario =? group by fk_medico";
		try
		{
			 con = Conexao.getConexao();
			 PreparedStatement preparador = con.prepareStatement(sql);
			 preparador.setString(1, medico);
			 ResultSet rs = preparador.executeQuery();
			 while(rs.next())
			 {
				 Triagem pac = new Triagem();
				 pac.setNomem(rs.getString("n_c_funcionario"));
				 pac.setFK_medico(rs.getInt("fk_medico"));
				 lista.add(pac);
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
	public List<Triagem> buscarAtendimentoFeitoTesteANo(String ano){ 
		 
		List<Triagem> lista = new ArrayList<Triagem>();
		String sql = "select n_c_funcionario, fk_medico, data_daconsulta from vwrelpacientesconsultados "
				+ "where year(data_daconsulta) =? group by fk_medico";
		try
		{
			 con = Conexao.getConexao();
			 PreparedStatement preparador = con.prepareStatement(sql);
			 preparador.setString(1, ano);
			 ResultSet rs = preparador.executeQuery();
			 while(rs.next())
			 {
				 Triagem pac = new Triagem();
				 pac.setNomem(rs.getString("n_c_funcionario"));
				 pac.setFK_medico(rs.getInt("fk_medico"));
				 lista.add(pac);
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
	public List<Triagem> buscarAtendimentoFeitoTesteAM(String ano, String medico){ 
		 
		List<Triagem> lista = new ArrayList<Triagem>();
		String sql = "select n_c_funcionario, fk_medico, data_daconsulta from vwrelpacientesconsultados "
				+ " where year(data_daconsulta) =? and n_c_funcionario =? group by fk_medico";
		try
		{
			 con = Conexao.getConexao();
			 PreparedStatement preparador = con.prepareStatement(sql);
			 preparador.setString(1, ano);
			 preparador.setString(2, medico);
			 ResultSet rs = preparador.executeQuery();
			 while(rs.next())
			 {
				 Triagem pac = new Triagem();
				 pac.setNomem(rs.getString("n_c_funcionario"));
				 pac.setFK_medico(rs.getInt("fk_medico"));
				 lista.add(pac);
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
	public List<Triagem> buscarAtendimentoFeitoTestes(){ 
		 
		List<Triagem> lista = new ArrayList<Triagem>();
		String sql = "select n_c_funcionario, fk_medico,data_daconsulta as 'data_daconsulta',fk_medico ,count(data_daconsulta) as 'qtd' from vwrelpacientesconsultados where data_daconsulta = curdate()  group by  fk_medico ";
		try
		{
			 con = Conexao.getConexao();
			 PreparedStatement preparador = con.prepareStatement(sql);
			 ResultSet rs = preparador.executeQuery();
			 while(rs.next())
			 {
				 Triagem pac = new Triagem();
				 Calendar data = Calendar.getInstance();
				 data.setTime(rs.getDate("data_daconsulta"));
				 pac.setDataConsulta(data);
				 pac.setFK_medico(rs.getInt("fk_medico"));
				 pac.setNomem(rs.getString("n_c_funcionario"));
				 pac.setQuantidade(rs.getInt("qtd"));
				 lista.add(pac);
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
	
	public List<Triagem> buscarAtendimentoFeitoTestesGrafico(){ 
		 
		List<Triagem> lista = new ArrayList<Triagem>();
		String sql = "select n_c_funcionario, fk_medico,data_daconsulta as 'data_daconsulta',fk_medico ,count(data_daconsulta) as 'qtd' from vwrelpacientesconsultados where data_daconsulta = curdate() group by fk_medico ";
		try
		{
			 con = Conexao.getConexao();
			 PreparedStatement preparador = con.prepareStatement(sql);
			 ResultSet rs = preparador.executeQuery();
			 while(rs.next())
			 {
				 Triagem pac = new Triagem();
				 Calendar data = Calendar.getInstance();
				 data.setTime(rs.getDate("data_daconsulta"));
				 pac.setDataConsulta(data);
				 pac.setFK_medico(rs.getInt("fk_medico"));
				 pac.setNomem(rs.getString("n_c_funcionario"));
				 pac.setQuantidade(rs.getInt("qtd"));
				 lista.add(pac);
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
	
	public List<Triagem> buscarAtendimentoFeitoNoGrafico(){ 
		 
		List<Triagem> lista = new ArrayList<Triagem>();
		String sql = "select n_c_funcionario, fk_medico,data_daconsulta as 'data_daconsulta',fk_medico ,count(data_daconsulta) as 'qtd' from vwrelpacientesconsultados group by fk_medico ";
		try
		{
			 con = Conexao.getConexao();
			 PreparedStatement preparador = con.prepareStatement(sql);
			 ResultSet rs = preparador.executeQuery();
			 while(rs.next())
			 {
				 Triagem pac = new Triagem();
				 Calendar data = Calendar.getInstance();
				 data.setTime(rs.getDate("data_daconsulta"));
				 pac.setDataConsulta(data);
				 pac.setFK_medico(rs.getInt("fk_medico"));
				 pac.setNomem(rs.getString("n_c_funcionario"));
				 pac.setQuantidade(rs.getInt("qtd"));
				 lista.add(pac);
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
	public List <Triagem> buscarAtendimentoFeitoN(String ano)
    {
			List <Triagem> lista = new ArrayList<Triagem>();
			String sql = "select n_c_funcionario, fk_medico, data_daconsulta as 'data_daconsulta', count(id_consulta) as 'qtd' "
						+ "from vwrelpacientesconsultados "
						+ "where year(data_daconsulta) = ? "
						+ "group by data_daconsulta,fk_medico";
			try {
				con = Conexao.getConexao();
				PreparedStatement preparador = con.prepareStatement(sql);
				preparador.setString(1, ano);
				ResultSet rs = preparador.executeQuery();
				while (rs.next())
				 {
					Triagem pac = new Triagem();
					 Calendar data = Calendar.getInstance();
					 data.setTime(rs.getDate("data_daconsulta"));
					 pac.setDataConsulta(data);
					 pac.setQuantidade(rs.getInt("qtd"));
					 pac.setFK_medico(rs.getInt("fk_medico"));
					 pac.setNomem(rs.getString("n_c_funcionario"));
					 lista.add(pac);
				 }
				 preparador.close();
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
			return lista;
		}
	
	public List <Triagem> buscarAtendimentoFeitoNGrafico(String ano)
    {
			List <Triagem> lista = new ArrayList<Triagem>();
			String sql = "select n_c_funcionario, fk_medico, data_daconsulta as 'data_daconsulta', count(id_consulta) as 'qtd' "
						+ "from vwrelpacientesconsultados "
						+ "where year(data_daconsulta) = ? "
						+ "group by fk_medico";
			try {
				con = Conexao.getConexao();
				PreparedStatement preparador = con.prepareStatement(sql);
				preparador.setString(1, ano);
				ResultSet rs = preparador.executeQuery();
				while (rs.next())
				 {
					Triagem pac = new Triagem();
					 Calendar data = Calendar.getInstance();
					 data.setTime(rs.getDate("data_daconsulta"));
					 pac.setDataConsulta(data);
					 pac.setQuantidade(rs.getInt("qtd"));
					 pac.setFK_medico(rs.getInt("fk_medico"));
					 pac.setNomem(rs.getString("n_c_funcionario"));
					 lista.add(pac);
				 }
				 preparador.close();
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
			return lista;
		}
	public List <Triagem> buscarAtendimentoFeitoAM(String ano, String mes)
    {
			List <Triagem> lista = new ArrayList<Triagem>();
			String sql = "select n_c_funcionario, fk_medico, data_daconsulta as 'data_daconsulta', count(id_consulta) as 'qtd' "
						+ "from vwrelpacientesconsultados "
						+ "where year(data_daconsulta) = ? and month(data_daconsulta) =?"
						+ "group by data_daconsulta,fk_medico";
			try {
				con = Conexao.getConexao();
				PreparedStatement preparador = con.prepareStatement(sql);
				preparador.setString(1, ano);
				preparador.setString(2, mes);
				ResultSet rs = preparador.executeQuery();
				while (rs.next())
				 {
					Triagem pac = new Triagem();
					 Calendar data = Calendar.getInstance();
					 data.setTime(rs.getDate("data_daconsulta"));
					 pac.setDataConsulta(data);
					 pac.setFK_medico(rs.getInt("fk_medico"));
					 pac.setNomem(rs.getString("n_c_funcionario"));
					 pac.setQuantidade(rs.getInt("qtd"));
					 lista.add(pac);
				 }
				 preparador.close();
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
			return lista;
		}
	
	public List <Triagem> buscarAtendimentoFeitoAMGrafico(String ano, String mes)
    {
			List <Triagem> lista = new ArrayList<Triagem>();
			String sql = "select n_c_funcionario, fk_medico, data_daconsulta as 'data_daconsulta', count(id_consulta) as 'qtd' "
						+ "from vwrelpacientesconsultados "
						+ "where year(data_daconsulta) = ? and month(data_daconsulta) =?"
						+ "group by fk_medico";
			try {
				con = Conexao.getConexao();
				PreparedStatement preparador = con.prepareStatement(sql);
				preparador.setString(1, ano);
				preparador.setString(2, mes);
				ResultSet rs = preparador.executeQuery();
				while (rs.next())
				 {
					Triagem pac = new Triagem();
					 Calendar data = Calendar.getInstance();
					 data.setTime(rs.getDate("data_daconsulta"));
					 pac.setDataConsulta(data);
					 pac.setFK_medico(rs.getInt("fk_medico"));
					 pac.setNomem(rs.getString("n_c_funcionario"));
					 pac.setQuantidade(rs.getInt("qtd"));
					 lista.add(pac);
				 }
				 preparador.close();
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
			return lista;
		}
	public List<Triagem> buscarAtendimentoFeitoTesteAMes(String ano, String mes){ 
		 
		List<Triagem> lista = new ArrayList<Triagem>();
		String sql = "select n_c_funcionario, fk_medico, data_daconsulta from vwrelpacientesconsultados "
				+ "where year(data_daconsulta) = ? and month(data_daconsulta) =? group by fk_medico";
		try
		{
			 con = Conexao.getConexao();
			 PreparedStatement preparador = con.prepareStatement(sql);
			 preparador.setString(1, ano);
			 preparador.setString(2, mes);
			 ResultSet rs = preparador.executeQuery();
			 while(rs.next())
			 {
				 Triagem pac = new Triagem();
				 pac.setNomem(rs.getString("n_c_funcionario"));
				 pac.setFK_medico(rs.getInt("fk_medico"));
				 lista.add(pac);
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
	public List <Triagem> buscarAtendimentoFeitoAMed(String ano, String medico)
    {
			List <Triagem> lista = new ArrayList<Triagem>();
			String sql = "select n_c_funcionario, fk_medico ,data_daconsulta as 'data_daconsulta', count(id_consulta) as 'qtd' "
						+ "from vwrelpacientesconsultados "
						+ "where year(data_daconsulta) = ? "
						+ "and n_c_funcionario =?"
						+ "group by data_daconsulta,fk_medico";
			try {
				con = Conexao.getConexao();
				PreparedStatement preparador = con.prepareStatement(sql);
				preparador.setString(1, ano);
				preparador.setString(2, medico);
				ResultSet rs = preparador.executeQuery();
				while (rs.next())
				 {
					Triagem pac = new Triagem();
					 Calendar data = Calendar.getInstance();
					 data.setTime(rs.getDate("data_daconsulta"));
					 pac.setDataConsulta(data);
					 pac.setNomem(rs.getString("n_c_funcionario"));
					 pac.setFK_medico(rs.getInt("fk_medico"));
					 pac.setQuantidade(rs.getInt("qtd"));
					 lista.add(pac);
				 }
				 preparador.close();
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
			return lista;
		}
	
	public List <Triagem> buscarAtendimentoFeitoAMedGrafico(String ano, String medico)
    {
			List <Triagem> lista = new ArrayList<Triagem>();
			String sql = "select n_c_funcionario, fk_medico ,data_daconsulta as 'data_daconsulta', count(id_consulta) as 'qtd' "
						+ "from vwrelpacientesconsultados "
						+ "where year(data_daconsulta) = ? "
						+ "and n_c_funcionario =?"
						+ "group by fk_medico";
			try {
				con = Conexao.getConexao();
				PreparedStatement preparador = con.prepareStatement(sql);
				preparador.setString(1, ano);
				preparador.setString(2, medico);
				ResultSet rs = preparador.executeQuery();
				while (rs.next())
				 {
					Triagem pac = new Triagem();
					 Calendar data = Calendar.getInstance();
					 data.setTime(rs.getDate("data_daconsulta"));
					 pac.setDataConsulta(data);
					 pac.setNomem(rs.getString("n_c_funcionario"));
					 pac.setFK_medico(rs.getInt("fk_medico"));
					 pac.setQuantidade(rs.getInt("qtd"));
					 lista.add(pac);
				 }
				 preparador.close();
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
			return lista;
		}
	public List <Triagem> buscarAtendimentoFeitoAMM(String ano, String mes, String medico)
    {
			List <Triagem> lista = new ArrayList<Triagem>();
			String sql = "select n_c_funcionario, fk_medico, data_daconsulta as 'data_daconsulta', count(id_consulta) as 'qtd' "
						+ "from vwrelpacientesconsultados "
						+ "where year(data_daconsulta) = ? and month(data_daconsulta) =?"
						+ "and n_c_funcionario =?"
						+ "group by data_daconsulta,fk_medico";
			try {
				con = Conexao.getConexao();
				PreparedStatement preparador = con.prepareStatement(sql);
				preparador.setString(1, ano);
				preparador.setString(2, mes);
				preparador.setString(3, medico);
				ResultSet rs = preparador.executeQuery();
				while (rs.next())
				 {
					Triagem pac = new Triagem();
					 Calendar data = Calendar.getInstance();
					 data.setTime(rs.getDate("data_daconsulta"));
					 pac.setDataConsulta(data);
					 pac.setFK_medico(rs.getInt("fk_medico"));
					 pac.setNomem(rs.getString("n_c_funcionario"));
					 pac.setQuantidade(rs.getInt("qtd"));
					 lista.add(pac);
				 }
				 preparador.close();
			} catch (SQLException e) {	e.printStackTrace();		}
			catch (Exception e) {			}
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
	
	public List <Triagem> buscarAtendimentoFeitoGraficoAMM(String ano, String mes, String medico)
    {
			List <Triagem> lista = new ArrayList<Triagem>();
			String sql = "select n_c_funcionario, fk_medico, data_daconsulta as 'data_daconsulta', count(id_consulta) as 'qtd' "
						+ "from vwrelpacientesconsultados "
						+ "where year(data_daconsulta) = ? and month(data_daconsulta) =?"
						+ "and n_c_funcionario =?"
						+ "group by fk_medico";
			try {
				con = Conexao.getConexao();
				PreparedStatement preparador = con.prepareStatement(sql);
				preparador.setString(1, ano);
				preparador.setString(2, mes);
				preparador.setString(3, medico);
				ResultSet rs = preparador.executeQuery();
				while (rs.next())
				 {
					Triagem pac = new Triagem();
					 Calendar data = Calendar.getInstance();
					 data.setTime(rs.getDate("data_daconsulta"));
					 pac.setDataConsulta(data);
					 pac.setFK_medico(rs.getInt("fk_medico"));
					 pac.setNomem(rs.getString("n_c_funcionario"));
					 pac.setQuantidade(rs.getInt("qtd"));
					 lista.add(pac);
				 }
				 preparador.close();
			} catch (SQLException e) {	e.printStackTrace();		}
			catch (Exception e) {			}
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
	public List<Triagem> buscarAtendimentoFeitoTesteAMD(String ano, String mes, String medico){ 
		 
		List<Triagem> lista = new ArrayList<Triagem>();
		String sql = "select n_c_funcionario, fk_medico, data_daconsulta from vwrelpacientesconsultados "
				+ "where year(data_daconsulta) = ? and month(data_daconsulta) =? "
				+ "and n_c_funcionario = ? group by fk_medico";
		try
		{
			 con = Conexao.getConexao();
			 PreparedStatement preparador = con.prepareStatement(sql);
			 preparador.setString(1, ano);
			 preparador.setString(2, mes);
			 preparador.setString(3, medico);
			 ResultSet rs = preparador.executeQuery();
			 while(rs.next())
			 {
				 Triagem pac = new Triagem();
				 pac.setNomem(rs.getString("n_c_funcionario"));
				 pac.setFK_medico(rs.getInt("fk_medico"));
				 lista.add(pac);
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
	public List <Triagem> buscarAtendimentoFeitoAMD(String ano, String mes, String dias, String outroDia)
    {
			List <Triagem> lista = new ArrayList<Triagem>();
			String sql = "select n_c_funcionario, fk_medico, data_daconsulta as 'data_daconsulta', count(id_consulta) as 'qtd' "
						+ "from vwrelpacientesconsultados "
						+ "where year(data_daconsulta) = ? and month(data_daconsulta) =?"
						+ "and day(data_daconsulta) between ? and ?"
						+ "group by data_daconsulta,fk_medico";
			try {
				con = Conexao.getConexao();
				PreparedStatement preparador = con.prepareStatement(sql);
				preparador.setString(1, ano);
				preparador.setString(2, mes);
				preparador.setString(3, dias);
				preparador.setString(4, outroDia);				
				ResultSet rs = preparador.executeQuery();
				while (rs.next())
				 {
					Triagem pac = new Triagem();
					 Calendar data = Calendar.getInstance();
					 data.setTime(rs.getDate("data_daconsulta"));
					 pac.setDataConsulta(data);
					 pac.setFK_medico(rs.getInt("fK_medico"));
					 pac.setNomem(rs.getString("n_c_funcionario"));
					 pac.setQuantidade(rs.getInt("qtd"));
					 lista.add(pac);
				 }
				 preparador.close();
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
			return lista;
		}
	public List<Triagem> buscarAtendimentoFeitoTesteAMD(String ano, String mes, String dia, String outroD){ 
		 
		List<Triagem> lista = new ArrayList<Triagem>();
		String sql = "select n_c_funcionario, fk_medico, data_daconsulta from vwrelpacientesconsultados "
				+ "where year(data_daconsulta) = ? and month(data_daconsulta) =? "
				+ "and day(data_daconsulta) between ? and ? group by fk_medico";
		try
		{
			 con = Conexao.getConexao();
			 PreparedStatement preparador = con.prepareStatement(sql);
			 preparador.setString(1, ano);
			 preparador.setString(2, mes);
			 preparador.setString(3, dia);
			 preparador.setString(4, outroD);
			 ResultSet rs = preparador.executeQuery();
			 while(rs.next())
			 {
				 Triagem pac = new Triagem();
				 pac.setNomem(rs.getString("n_c_funcionario"));
				 pac.setFK_medico(rs.getInt("fk_medico"));
				 lista.add(pac);
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
	public List <Triagem> buscarAtendimentoFeitoAMMD(String ano, String mes, String dias, String outroDia, String medico)
    {
			List <Triagem> lista = new ArrayList<Triagem>();
			String sql = "select n_c_funcionario, fk_medico, data_daconsulta as 'data_daconsulta', count(id_consulta) as 'qtd' "
						+ "from vwrelpacientesconsultados "
						+ "where year(data_daconsulta) = ? and month(data_daconsulta) =?"
						+ "and day(data_daconsulta) between ? and ?"
						+ "and n_c_funcionario =?"
						+ "group by data_daconsulta, fk_medico";
			try {
				con = Conexao.getConexao();
				PreparedStatement preparador = con.prepareStatement(sql);
				preparador.setString(1, ano);
				preparador.setString(2, mes);
				preparador.setString(3, dias);
				preparador.setString(4, outroDia);
				preparador.setString(5, medico);
				ResultSet rs = preparador.executeQuery();
				while (rs.next())
				 {
					Triagem pac = new Triagem();
					 Calendar data = Calendar.getInstance();
					 data.setTime(rs.getDate("data_daconsulta"));
					 pac.setDataConsulta(data);
					 pac.setFK_medico(rs.getInt("fK_medico"));
					 pac.setNomem(rs.getString("n_c_funcionario"));
					 pac.setQuantidade(rs.getInt("qtd"));
					 lista.add(pac);
				 }
				 preparador.close();
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
			return lista;
		}
	
	public List <Triagem> buscarAtendimentoGraficoAMMD(String ano, String mes, String dias, String outroDia, String medico)
    {
			List <Triagem> lista = new ArrayList<Triagem>();
			String sql = "select n_c_funcionario, fk_medico, data_daconsulta as 'data_daconsulta', count(id_consulta) as 'qtd' "
						+ "from vwrelpacientesconsultados "
						+ "where year(data_daconsulta) = ? and month(data_daconsulta) =?"
						+ "and day(data_daconsulta) between ? and ?"
						+ "and n_c_funcionario =?"
						+ "group by data_daconsulta";
			try {
				con = Conexao.getConexao();
				PreparedStatement preparador = con.prepareStatement(sql);
				preparador.setString(1, ano);
				preparador.setString(2, mes);
				preparador.setString(3, dias);
				preparador.setString(4, outroDia);
				preparador.setString(5, medico);
				ResultSet rs = preparador.executeQuery();
				while (rs.next())
				 {
					Triagem pac = new Triagem();
					 Calendar data = Calendar.getInstance();
					 data.setTime(rs.getDate("data_daconsulta"));
					 pac.setDataConsulta(data);
					 pac.setFK_medico(rs.getInt("fK_medico"));
					 pac.setNomem(rs.getString("n_c_funcionario"));
					 pac.setQuantidade(rs.getInt("qtd"));
					 lista.add(pac);
				 }
				 preparador.close();
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
			return lista;
		}
	
	public List <Triagem> buscarAtendimentoFeitoGraficoAMD(String ano, String mes, String dias, String outroDia)
    {
			List <Triagem> lista = new ArrayList<Triagem>();
			String sql = "select n_c_funcionario, fk_medico, data_daconsulta as 'data_daconsulta', count(id_consulta) as 'qtd' "
						+ "from vwrelpacientesconsultados "
						+ "where year(data_daconsulta) = ? and month(data_daconsulta) =?"
						+ "and day(data_daconsulta) between ? and ?"
						+ "group by fk_medico";
			try {
				con = Conexao.getConexao();
				PreparedStatement preparador = con.prepareStatement(sql);
				preparador.setString(1, ano);
				preparador.setString(2, mes);
				preparador.setString(3, dias);
				preparador.setString(4, outroDia);				
				ResultSet rs = preparador.executeQuery();
				while (rs.next())
				 {
					Triagem pac = new Triagem();
					 Calendar data = Calendar.getInstance();
					 data.setTime(rs.getDate("data_daconsulta"));
					 pac.setDataConsulta(data);
					 pac.setFK_medico(rs.getInt("fK_medico"));
					 pac.setNomem(rs.getString("n_c_funcionario"));
					 pac.setQuantidade(rs.getInt("qtd"));
					 lista.add(pac);
				 }
				 preparador.close();
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
			return lista;
		}
	public List<Triagem> buscarAtendimentoFeitoTesteAMMD(String ano, String mes, String dia, String outroD, String medico){ 
		 
		List<Triagem> lista = new ArrayList<Triagem>();
		String sql = "select n_c_funcionario, fk_medico, data_daconsulta from vwrelpacientesconsultados "
				+ "where year(data_daconsulta) = ? and month(data_daconsulta) =? "
				+ "and day(data_daconsulta) between ? and ?"
				+ "and n_c_funcionario =? group by fk_medico";
		try
		{
			 con = Conexao.getConexao();
			 PreparedStatement preparador = con.prepareStatement(sql);
			 preparador.setString(1, ano);
			 preparador.setString(2, mes);
			 preparador.setString(3, dia);
			 preparador.setString(4, outroD);
			 preparador.setString(5, medico);
			 ResultSet rs = preparador.executeQuery();
			 while(rs.next())
			 {
				 Triagem pac = new Triagem();
				 pac.setNomem(rs.getString("n_c_funcionario"));
				 pac.setFK_medico(rs.getInt("fk_medico"));
				 lista.add(pac);
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


	public List <Triagem> buscarAtendimentoFeitoTrimestre(String ano, String esc)
    {
			List <Triagem> lista = new ArrayList<Triagem>();
			String sql = null;
			if(esc.equals("1")){
				 sql = "select n_c_funcionario, fk_medico, data_daconsulta as 'data_daconsulta',count(data_daconsulta) as 'qtd' "
						+ "from vwrelpacientesconsultados where year(data_daconsulta) = ? "
						+ "and month(data_daconsulta) between 1 and 3 "
						+ "group by data_daconsulta,fk_medico";
			}
			if(esc.equals("2")){
				 sql = "select n_c_funcionario, fk_medico, data_daconsulta as 'data_daconsulta',count(data_daconsulta) as 'qtd' "
						+ "from vwrelpacientesconsultados where year(data_daconsulta) = ? "
						+ "and month(data_daconsulta) between 4 and 6 "
						+ "group by data_daconsulta,fk_medico";
			}
			if(esc.equals("3")){
				 sql = "select n_c_funcionario, fk_medico, data_daconsulta as 'data_daconsulta',count(data_daconsulta) as 'qtd' "
						+ "from vwrelpacientesconsultados where year(data_daconsulta) = ? "
						+ "and month(data_daconsulta) between 7 and 9 "
						+ "group by data_daconsulta,fk_medico";
			}
			if(esc.equals("4")){
				 sql = "select n_c_funcionario, fk_medico, data_daconsulta as 'data_daconsulta',count(data_daconsulta) as 'qtd' "
						+ "from vwrelpacientesconsultados where year(data_daconsulta) = ? "
						+ "and month(data_daconsulta) between 10 and 12 "
						+ "group by data_daconsulta,fk_medico";
			}
			if(esc.equals("5")){
				 sql = "select n_c_funcionario, fk_medico, data_daconsulta as 'data_daconsulta',count(data_daconsulta) as 'qtd' "
						+ "from vwrelpacientesconsultados where year(data_daconsulta) = ? "
						+ "and month(data_daconsulta) between 1 and 6 "
						+ "group by data_daconsulta,fk_medico";
			}
			if(esc.equals("6")){
				 sql = "select n_c_funcionario, fk_medico, data_daconsulta as 'data_daconsulta',count(data_daconsulta) as 'qtd' "
						+ "from vwrelpacientesconsultados where year(data_daconsulta) = ? "
						+ "and month(data_daconsulta) between 7 and 12 "
						+ "group by data_daconsulta,fk_medico";
			}
			try {
				con = Conexao.getConexao();
				PreparedStatement preparador = con.prepareStatement(sql);
				preparador.setString(1, ano);
				ResultSet rs = preparador.executeQuery();
				while (rs.next())
				 {
					Triagem pac = new Triagem();
					 Calendar data = Calendar.getInstance();
					 data.setTime(rs.getDate("data_daconsulta"));
					 pac.setDataConsulta(data);
					 pac.setNomem(rs.getString("n_c_funcionario"));
					 pac.setFK_medico(rs.getInt("fk_medico"));
					 pac.setQuantidade(rs.getInt("qtd"));
					 lista.add(pac);
				 }
				 preparador.close();
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
			return lista;
		}public List <Triagem> buscarAtendimentoFeitoTrimestreGrafico(String ano, String esc)
	    {
				List <Triagem> lista = new ArrayList<Triagem>();
				String sql = null;
				if(esc.equals("1")){
					 sql = "select n_c_funcionario, fk_medico, data_daconsulta as 'data_daconsulta',count(data_daconsulta) as 'qtd' "
							+ "from vwrelpacientesconsultados where year(data_daconsulta) = ? "
							+ "and month(data_daconsulta) between 1 and 3 "
							+ "group by fk_medico";
				}
				if(esc.equals("2")){
					 sql = "select n_c_funcionario, fk_medico, data_daconsulta as 'data_daconsulta',count(data_daconsulta) as 'qtd' "
							+ "from vwrelpacientesconsultados where year(data_daconsulta) = ? "
							+ "and month(data_daconsulta) between 4 and 6 "
							+ "group by fk_medico";
				}
				if(esc.equals("3")){
					 sql = "select n_c_funcionario, fk_medico, data_daconsulta as 'data_daconsulta',count(data_daconsulta) as 'qtd' "
							+ "from vwrelpacientesconsultados where year(data_daconsulta) = ? "
							+ "and month(data_daconsulta) between 7 and 9 "
							+ "group by fk_medico";
				}
				if(esc.equals("4")){
					 sql = "select n_c_funcionario, fk_medico, data_daconsulta as 'data_daconsulta',count(data_daconsulta) as 'qtd' "
							+ "from vwrelpacientesconsultados where year(data_daconsulta) = ? "
							+ "and month(data_daconsulta) between 10 and 12 "
							+ "group by fk_medico";
				}
				if(esc.equals("5")){
					 sql = "select n_c_funcionario, fk_medico, data_daconsulta as 'data_daconsulta',count(data_daconsulta) as 'qtd' "
							+ "from vwrelpacientesconsultados where year(data_daconsulta) = ? "
							+ "and month(data_daconsulta) between 1 and 6 "
							+ "group by fk_medico";
				}
				if(esc.equals("6")){
					 sql = "select n_c_funcionario, fk_medico, data_daconsulta as 'data_daconsulta',count(data_daconsulta) as 'qtd' "
							+ "from vwrelpacientesconsultados where year(data_daconsulta) = ? "
							+ "and month(data_daconsulta) between 7 and 12 "
							+ "group by fk_medico";
				}
				try {
					con = Conexao.getConexao();
					PreparedStatement preparador = con.prepareStatement(sql);
					preparador.setString(1, ano);
					ResultSet rs = preparador.executeQuery();
					while (rs.next())
					 {
						Triagem pac = new Triagem();
						 Calendar data = Calendar.getInstance();
						 data.setTime(rs.getDate("data_daconsulta"));
						 pac.setDataConsulta(data);
						 pac.setNomem(rs.getString("n_c_funcionario"));
						 pac.setFK_medico(rs.getInt("fk_medico"));
						 pac.setQuantidade(rs.getInt("qtd"));
						 lista.add(pac);
					 }
					 preparador.close();
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
				return lista;
			}
	public List<Triagem> buscarAtendimentoFeitoTesteTrimestre(String ano, String esc){ 
		 
		List<Triagem> lista = new ArrayList<Triagem>();
		String sql = null;
		if(esc.equals("1")){
			sql = "select n_c_funcionario, fk_medico, data_daconsulta from vwrelpacientesconsultados "
					+ "where year(data_daconsulta) = ? and month(data_daconsulta) between 1 and 3 "
					+ " group by fk_medico";
		}
		if(esc.equals("2")){
			sql = "select n_c_funcionario, fk_medico, data_daconsulta from vwrelpacientesconsultados "
					+ "where year(data_daconsulta) = ? and month(data_daconsulta) between 4 and 6 "
					+ " group by fk_medico";	
		}
		if(esc.equals("3")){
			sql = "select n_c_funcionario, fk_medico, data_daconsulta from vwrelpacientesconsultados "
					+ "where year(data_daconsulta) = ? and month(data_daconsulta) between 7 and 9 "
					+ " group by fk_medico";
		}
		if(esc.equals("4")){
			sql = "select n_c_funcionario, fk_medico, data_daconsulta from vwrelpacientesconsultados "
					+ "where year(data_daconsulta) = ? and month(data_daconsulta) between 10 and 12 "
					+ " group by fk_medico";
		}
		if(esc.equals("5")){
			sql = "select n_c_funcionario, fk_medico, data_daconsulta from vwrelpacientesconsultados "
					+ "where year(data_daconsulta) = ? and month(data_daconsulta) between 1 and 6 "
					+ " group by fk_medico";
		}
		if(esc.equals("6")){
			sql = "select n_c_funcionario, fk_medico, data_daconsulta from vwrelpacientesconsultados "
					+ "where year(data_daconsulta) = ?"
					+ " and month(data_daconsulta) between 7 and 12 "
					+ " group by fk_medico";
		}								
		
		try
		{
			 con = Conexao.getConexao();
			 PreparedStatement preparador = con.prepareStatement(sql);
			 preparador.setString(1, ano);
			 ResultSet rs = preparador.executeQuery();
			 while(rs.next())
			 {
				 Triagem pac = new Triagem();
				 pac.setNomem(rs.getString("n_c_funcionario"));
				 pac.setFK_medico(rs.getInt("fk_medico"));
				 lista.add(pac);
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
	public List <Triagem> buscarAtendimentoFeitoTrimAndMedico(String ano, String esc, String medico)
    {
			List <Triagem> lista = new ArrayList<Triagem>();
			String sql = null;
			if(esc.equals("1")){
				 sql = "select n_c_funcionario, fk_medico, data_daconsulta as 'data_daconsulta',count(data_daconsulta) as 'qtd' "
						+ "from vwrelpacientesconsultados where year(data_daconsulta) = ? "
						+ "and month(data_daconsulta) between 1 and 3 "
						+ "and n_c_funcionario =?"
						+ "group by data_daconsulta,fk_medico";
			}
			if(esc.equals("2")){
				 sql = "select n_c_funcionario, fk_medico, data_daconsulta as 'data_daconsulta',count(data_daconsulta) as 'qtd' "
						+ "from vwrelpacientesconsultados where year(data_daconsulta) = ? "
						+ "and month(data_daconsulta) between 4 and 6 "
						+ "and n_c_funcionario =?"
						+ "group by data_daconsulta,fk_medico";
			}
			if(esc.equals("3")){
				 sql = "select n_c_funcionario, fk_medico, data_daconsulta as 'data_daconsulta',count(data_daconsulta) as 'qtd' "
						+ "from vwrelpacientesconsultados where year(data_daconsulta) = ? "
						+ "and month(data_daconsulta) between 7 and 9 "
						+ "and n_c_funcionario =?"
						+ "group by data_daconsulta,fk_medico";
			}
			if(esc.equals("4")){
				 sql = "select n_c_funcionario, fk_medico, data_daconsulta as 'data_daconsulta',count(data_daconsulta) as 'qtd' "
						+ "from vwrelpacientesconsultados where year(data_daconsulta) = ? "
						+ "and month(data_daconsulta) between 10 and 12 "
						+ "and n_c_funcionario =?"
						+ "group by data_daconsulta,fk_medico";
			}
			if(esc.equals("5")){
				 sql = "select n_c_funcionario, fk_medico, data_daconsulta as 'data_daconsulta',count(data_daconsulta) as 'qtd' "
						+ "from vwrelpacientesconsultados where year(data_daconsulta) = ? "
						+ "and month(data_daconsulta) between 1 and 6 "
						+ "and n_c_funcionario =?"
						+ "group by data_daconsulta,fk_medico";
			}
			if(esc.equals("6")){
				 sql = "select n_c_funcionario, fk_medico, data_daconsulta as 'data_daconsulta',count(data_daconsulta) as 'qtd' "
						+ "from vwrelpacientesconsultados where year(data_daconsulta) = ? "
						+ "and month(data_daconsulta) between 7 and 12 "
						+ "and n_c_funcionario =?"
						+ "group by data_daconsulta,fk_medico";
			}
			try {
				con = Conexao.getConexao();
				PreparedStatement preparador = con.prepareStatement(sql);
				preparador.setString(1, ano);
				preparador.setString(2, medico);
				ResultSet rs = preparador.executeQuery();
				while (rs.next())
				 {
					Triagem pac = new Triagem();
					 Calendar data = Calendar.getInstance();
					 data.setTime(rs.getDate("data_daconsulta"));
					 pac.setDataConsulta(data);
					 pac.setNomem(rs.getString("n_c_funcionario"));
					 pac.setFK_medico(rs.getInt("fk_medico"));
					 pac.setQuantidade(rs.getInt("qtd"));
					 lista.add(pac);
				 }
				 preparador.close();
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
			return lista;
		}
	
	public List <Triagem> buscarAtendimentoFeitoTrimAndMedicoGrafico(String ano, String esc, String medico)
    {
			List <Triagem> lista = new ArrayList<Triagem>();
			String sql = null;
			if(esc.equals("1")){
				 sql = "select n_c_funcionario, fk_medico, data_daconsulta as 'data_daconsulta',count(data_daconsulta) as 'qtd' "
						+ "from vwrelpacientesconsultados where year(data_daconsulta) = ? "
						+ "and month(data_daconsulta) between 1 and 3 "
						+ "and n_c_funcionario =?"
						+ "group by fk_medico";
			}
			if(esc.equals("2")){
				 sql = "select n_c_funcionario, fk_medico, data_daconsulta as 'data_daconsulta',count(data_daconsulta) as 'qtd' "
						+ "from vwrelpacientesconsultados where year(data_daconsulta) = ? "
						+ "and month(data_daconsulta) between 4 and 6 "
						+ "and n_c_funcionario =?"
						+ "group by fk_medico";
			}
			if(esc.equals("3")){
				 sql = "select n_c_funcionario, fk_medico, data_daconsulta as 'data_daconsulta',count(data_daconsulta) as 'qtd' "
						+ "from vwrelpacientesconsultados where year(data_daconsulta) = ? "
						+ "and month(data_daconsulta) between 7 and 9 "
						+ "and n_c_funcionario =?"
						+ "group by fk_medico";
			}
			if(esc.equals("4")){
				 sql = "select n_c_funcionario, fk_medico, data_daconsulta as 'data_daconsulta',count(data_daconsulta) as 'qtd' "
						+ "from vwrelpacientesconsultados where year(data_daconsulta) = ? "
						+ "and month(data_daconsulta) between 10 and 12 "
						+ "and n_c_funcionario =?"
						+ "group by fk_medico";
			}
			if(esc.equals("5")){
				 sql = "select n_c_funcionario, fk_medico, data_daconsulta as 'data_daconsulta',count(data_daconsulta) as 'qtd' "
						+ "from vwrelpacientesconsultados where year(data_daconsulta) = ? "
						+ "and month(data_daconsulta) between 1 and 6 "
						+ "and n_c_funcionario =?"
						+ "group by fk_medico";
			}
			if(esc.equals("6")){
				 sql = "select n_c_funcionario, fk_medico, data_daconsulta as 'data_daconsulta',count(data_daconsulta) as 'qtd' "
						+ "from vwrelpacientesconsultados where year(data_daconsulta) = ? "
						+ "and month(data_daconsulta) between 7 and 12 "
						+ "and n_c_funcionario =?"
						+ "group by fk_medico";
			}
			try {
				con = Conexao.getConexao();
				PreparedStatement preparador = con.prepareStatement(sql);
				preparador.setString(1, ano);
				preparador.setString(2, medico);
				ResultSet rs = preparador.executeQuery();
				while (rs.next())
				 {
					Triagem pac = new Triagem();
					 Calendar data = Calendar.getInstance();
					 data.setTime(rs.getDate("data_daconsulta"));
					 pac.setDataConsulta(data);
					 pac.setNomem(rs.getString("n_c_funcionario"));
					 pac.setFK_medico(rs.getInt("fk_medico"));
					 pac.setQuantidade(rs.getInt("qtd"));
					 lista.add(pac);
				 }
				 preparador.close();
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
			return lista;
		}
	
	public List<Triagem> buscarAtendimentoFeitoTesteTrimAndMedico(String ano, String esc, String medico){ 
		 
		List<Triagem> lista = new ArrayList<Triagem>();
		String sql = null;
		if(esc.equals("1")){
			sql = "select n_c_funcionario, fk_medico, data_daconsulta from vwrelpacientesconsultados "
					+ "where year(data_daconsulta) = ? and month(data_daconsulta) between 1 and 3 "
					+ "and n_c_funcionario = ? group by fk_medico";
		}
		if(esc.equals("2")){
			sql = "select n_c_funcionario, fk_medico, data_daconsulta from vwrelpacientesconsultados "
					+ "where year(data_daconsulta) = ? and month(data_daconsulta) between 4 and 6 "
					+ "and n_c_funcionario = ? group by fk_medico";	
		}
		if(esc.equals("3")){
			sql = "select n_c_funcionario, fk_medico, data_daconsulta from vwrelpacientesconsultados "
					+ "where year(data_daconsulta) = ? and month(data_daconsulta) between 7 and 9 "
					+ "and n_c_funcionario = ? group by fk_medico";
		}
		if(esc.equals("4")){
			sql = "select n_c_funcionario, fk_medico, data_daconsulta from vwrelpacientesconsultados "
					+ "where year(data_daconsulta) = ? and month(data_daconsulta) between 10 and 12 "
					+ "and n_c_funcionario = ? group by fk_medico";
		}
		if(esc.equals("5")){
			sql = "select n_c_funcionario, fk_medico, data_daconsulta from vwrelpacientesconsultados "
					+ "where year(data_daconsulta) = ? and month(data_daconsulta) between 1 and 6 "
					+ "and n_c_funcionario = ? group by fk_medico";
		}
		if(esc.equals("6")){
			sql = "select n_c_funcionario, fk_medico, data_daconsulta from vwrelpacientesconsultados "
					+ "where year(data_daconsulta) = ? and month(data_daconsulta) between 7 and 12 "
					+ "and n_c_funcionario = ? group by fk_medico";
		}								
		
		try
		{
			 con = Conexao.getConexao();
			 PreparedStatement preparador = con.prepareStatement(sql);
			 preparador.setString(1, ano);
			 preparador.setString(2, medico);
			 ResultSet rs = preparador.executeQuery();
			 while(rs.next())
			 {
				 Triagem pac = new Triagem();
				 pac.setNomem(rs.getString("n_c_funcionario"));
				 pac.setFK_medico(rs.getInt("fk_medico"));
				 lista.add(pac);
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
	public List <Triagem> buscarAtendimentoFeitoMed(String medico)
    {
			List <Triagem> lista = new ArrayList<Triagem>();
			String sql = "select n_c_funcionario, fk_medico,data_daconsulta as 'data_daconsulta', count(id_consulta) as 'qtd' "
						+ "from vwrelpacientesconsultados "
						+ "where n_c_funcionario =?"
						+ "group by data_daconsulta,fk_medico";
			try {
				con = Conexao.getConexao();
				PreparedStatement preparador = con.prepareStatement(sql);
				preparador.setString(1, medico);
				ResultSet rs = preparador.executeQuery();
				while (rs.next())
				 {
					Triagem pac = new Triagem();
					 Calendar data = Calendar.getInstance();
					 data.setTime(rs.getDate("data_daconsulta"));
					 pac.setDataConsulta(data);
					 pac.setNomem(rs.getString("n_c_funcionario"));
					 pac.setFK_medico(rs.getInt("fk_medico"));
					 pac.setQuantidade(rs.getInt("qtd"));
					 lista.add(pac);
				 }
				 preparador.close();
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
			return lista;
		}
	
	public List <Triagem> buscarAtendimentoFeitoMedGrafico(String medico)
    {
			List <Triagem> lista = new ArrayList<Triagem>();
			String sql = "select n_c_funcionario, fk_medico,data_daconsulta as 'data_daconsulta', count(id_consulta) as 'qtd' "
						+ "from vwrelpacientesconsultados "
						+ "where n_c_funcionario =?"
						+ "group by fk_medico";
			try {
				con = Conexao.getConexao();
				PreparedStatement preparador = con.prepareStatement(sql);
				preparador.setString(1, medico);
				ResultSet rs = preparador.executeQuery();
				while (rs.next())
				 {
					Triagem pac = new Triagem();
					 Calendar data = Calendar.getInstance();
					 data.setTime(rs.getDate("data_daconsulta"));
					 pac.setDataConsulta(data);
					 pac.setNomem(rs.getString("n_c_funcionario"));
					 pac.setFK_medico(rs.getInt("fk_medico"));
					 pac.setQuantidade(rs.getInt("qtd"));
					 lista.add(pac);
				 }
				 preparador.close();
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
			return lista;
		}
	public List <Triagem> buscarAtendimentoFeitoAD(String ano, String mes, String dias)
    {
			List <Triagem> lista = new ArrayList<Triagem>();
			String sql = "select n_c_funcionario, fk_medico, data_daconsulta as 'data_daconsulta', count(id_consulta) as 'qtd' "
						+ "from vwrelpacientesconsultados "
						+ "where year(data_daconsulta) = ? and month(data_daconsulta) =?"
						+ "and day(data_daconsulta) = ? group by data_daconsulta,fk_medico";
			try {
				con = Conexao.getConexao();
				PreparedStatement preparador = con.prepareStatement(sql);
				preparador.setString(1, ano);
				preparador.setString(2, mes);
				preparador.setString(3, dias);
				ResultSet rs = preparador.executeQuery();
				while (rs.next())
				 {
					Triagem pac = new Triagem();
					 Calendar data = Calendar.getInstance();
					 data.setTime(rs.getDate("data_daconsulta"));
					 pac.setDataConsulta(data);
					 pac.setFK_medico(rs.getInt("fk_medico"));
					 pac.setNomem(rs.getString("n_c_funcionario"));
					 pac.setQuantidade(rs.getInt("qtd"));
					 lista.add(pac);
				 }
				 preparador.close();
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
			return lista;
		}
	
	public List <Triagem> buscarAtendimentoFeitoGraficoAD(String ano, String mes, String dias)
    {
			List <Triagem> lista = new ArrayList<Triagem>();
			String sql = "select n_c_funcionario, fk_medico, data_daconsulta as 'data_daconsulta', count(id_consulta) as 'qtd' "
						+ "from vwrelpacientesconsultados "
						+ "where year(data_daconsulta) = ? and month(data_daconsulta) =?"
						+ "and day(data_daconsulta) = ? group by fk_medico";
			try {
				con = Conexao.getConexao();
				PreparedStatement preparador = con.prepareStatement(sql);
				preparador.setString(1, ano);
				preparador.setString(2, mes);
				preparador.setString(3, dias);
				ResultSet rs = preparador.executeQuery();
				while (rs.next())
				 {
					Triagem pac = new Triagem();
					 Calendar data = Calendar.getInstance();
					 data.setTime(rs.getDate("data_daconsulta"));
					 pac.setDataConsulta(data);
					 pac.setFK_medico(rs.getInt("fk_medico"));
					 pac.setNomem(rs.getString("n_c_funcionario"));
					 pac.setQuantidade(rs.getInt("qtd"));
					 lista.add(pac);
				 }
				 preparador.close();
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
			return lista;
		}
	
	public List<Triagem> buscarAtendimentoFeitoTesteAD(String ano, String mes, String dia){ 
		 
		List<Triagem> lista = new ArrayList<Triagem>();
		String sql = "select n_c_funcionario, fk_medico, data_daconsulta from vwrelpacientesconsultados "
				+ "where year(data_daconsulta) = ? and month(data_daconsulta) =? and day(data_daconsulta) =? group by fk_medico";
		try
		{
			 con = Conexao.getConexao();
			 PreparedStatement preparador = con.prepareStatement(sql);
			 preparador.setString(1, ano);
			 preparador.setString(2, mes);
			 preparador.setString(3, dia);
			 ResultSet rs = preparador.executeQuery();
			 while(rs.next())
			 {
				 Triagem pac = new Triagem();
				 pac.setNomem(rs.getString("n_c_funcionario"));
				 pac.setFK_medico(rs.getInt("fk_medico"));
				 lista.add(pac);
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
	public List <Triagem> buscarAtendimentoFeitoAO(String ano, String mes, String outroD)
    {
			List <Triagem> lista = new ArrayList<Triagem>();
			String sql = "select n_c_funcionario, fk_medico, data_daconsulta as 'data_daconsulta', count(id_consulta) as 'qtd' "
						+ "from vwrelpacientesconsultados "
						+ "where year(data_daconsulta) = ? and month(data_daconsulta) =?"
						+ "and day(data_daconsulta) = ?"
						+ "group by data_daconsulta,fk_medico";
			try {
				con = Conexao.getConexao();
				PreparedStatement preparador = con.prepareStatement(sql);
				preparador.setString(1, ano);
				preparador.setString(2, mes);
				preparador.setString(3, outroD);
				ResultSet rs = preparador.executeQuery();
				while (rs.next())
				 {
					Triagem pac = new Triagem();
					 Calendar data = Calendar.getInstance();
					 data.setTime(rs.getDate("data_daconsulta"));
					 pac.setDataConsulta(data);
					 pac.setFK_medico(rs.getInt("fk_medico"));
					 pac.setNomem(rs.getString("n_c_funcionario"));
					 pac.setQuantidade(rs.getInt("qtd"));
					 lista.add(pac);
				 }
				 preparador.close();
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
			return lista;
		}
	
	public List <Triagem> buscarAtendimentoFeitoAOGrafico(String ano, String mes, String outroD)
    {
			List <Triagem> lista = new ArrayList<Triagem>();
			String sql = "select n_c_funcionario, fk_medico, data_daconsulta as 'data_daconsulta', count(id_consulta) as 'qtd' "
						+ "from vwrelpacientesconsultados "
						+ "where year(data_daconsulta) = ? and month(data_daconsulta) =?"
						+ "and day(data_daconsulta) = ?"
						+ "group by fk_medico";
			try {
				con = Conexao.getConexao();
				PreparedStatement preparador = con.prepareStatement(sql);
				preparador.setString(1, ano);
				preparador.setString(2, mes);
				preparador.setString(3, outroD);
				ResultSet rs = preparador.executeQuery();
				while (rs.next())
				 {
					Triagem pac = new Triagem();
					 Calendar data = Calendar.getInstance();
					 data.setTime(rs.getDate("data_daconsulta"));
					 pac.setDataConsulta(data);
					 pac.setFK_medico(rs.getInt("fk_medico"));
					 pac.setNomem(rs.getString("n_c_funcionario"));
					 pac.setQuantidade(rs.getInt("qtd"));
					 lista.add(pac);
				 }
				 preparador.close();
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
			return lista;
		}
	public List<Triagem> buscarAtendimentoFeitoTesteAO(String ano, String mes, String outroD){ 
		 
		List<Triagem> lista = new ArrayList<Triagem>();
		String sql = "select n_c_funcionario, fk_medico, data_daconsulta from vwrelpacientesconsultados "
				+ "where year(data_daconsulta) = ? and month(data_daconsulta) =? and day(data_daconsulta) =? group by fk_medico";
		try
		{
			 con = Conexao.getConexao();
			 PreparedStatement preparador = con.prepareStatement(sql);
			 preparador.setString(1, ano);
			 preparador.setString(2, mes);
			 preparador.setString(3, outroD);
			 ResultSet rs = preparador.executeQuery();
			 while(rs.next())
			 {
				 Triagem pac = new Triagem();
				 pac.setNomem(rs.getString("n_c_funcionario"));
				 pac.setFK_medico(rs.getInt("fk_medico"));
				 lista.add(pac);
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

	
	@SuppressWarnings("resource")
	public void novoJustificativo(Paciente p){
		String sql = "Select * from tbljustificativo where FK_consulta = ?";
		
		       
		try {
			con = Conexao.getConexao();
			PreparedStatement ps = con.prepareStatement(sql);
			ps.setInt(1, p.getFK_consulta());
			ResultSet rs = ps.executeQuery();
			if(rs.next()){
				sql = "Update tbljustificativo set id_opcao = ?,p_data = ?,s_data = ?,necessidade_cuidado = ? where FK_consulta = ?";
				ps = con.prepareStatement(sql);
				ps.setInt(1, p.getOpcao());
				ps.setDate(2,p.getP_data());
				ps.setDate(3, p.getS_data());
				ps.setString(4, p.getDescricao());
				ps.setInt(5, p.getFK_consulta());
				ps.execute();
				ps.close();
			}else{
				sql = "Insert into tbljustificativo (FK_consulta,id_opcao,p_data,s_data,necessidade_cuidado) value (?,?,?,?,?)";
				ps = con.prepareStatement(sql);
				ps.setInt(1, p.getFK_consulta());
				ps.setInt(2, p.getOpcao());
				ps.setDate(3,p.getP_data());
				ps.setDate(4, p.getS_data());
				ps.setString(5, p.getDescricao());
				ps.execute();
				ps.close();
			}
		con.close();	
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	public void novoJustificativoUrgencia(Paciente p){
		String sql = "Select * from tblurgenciajustificativo where FK_urgencia = ?";
		
		       
		try {
			con = Conexao.getConexao();
			PreparedStatement ps = con.prepareStatement(sql);
			ps.setInt(1, p.getFK_urgencia());
			ResultSet rs = ps.executeQuery();
			if(rs.next()){
				sql = "Update tblurgenciajustificativo set id_opcao = ?,p_data = ?,s_data = ?,necessidade_cuidado = ? where  FK_urgencia = ?";
				ps = con.prepareStatement(sql);
				ps.setInt(1, p.getOpcao());
				ps.setDate(2,p.getP_data());
				ps.setDate(3, p.getS_data());
				ps.setString(4, p.getDescricao());
				ps.setInt(5, p.getFK_urgencia());
				ps.execute();
				ps.close();
			}else{
				sql = "Insert into tblurgenciajustificativo (FK_urgencia,id_opcao,p_data,s_data,necessidade_cuidado) value (?,?,?,?,?)";
				ps = con.prepareStatement(sql);
				ps.setInt(1, p.getFK_urgencia());
				ps.setInt(2, p.getOpcao());
				ps.setDate(3,p.getP_data());
				ps.setDate(4, p.getS_data());
				ps.setString(5, p.getDescricao());
				ps.execute();
				ps.close();
			}
		con.close();	
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
