package ao.co.cligest.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import ao.co.cligest.entidades.Agenda;
import ao.co.cligest.entidades.Embalagem;
import ao.co.cligest.entidades.GestaoInstalacao;
import ao.co.cligest.entidades.Produtos;

public class GestaoInstalacaoDAO {
	
	private Connection con ;

	public List <GestaoInstalacao> buscarGestaoInstalacao (){
		
		List <GestaoInstalacao> lista = new ArrayList<GestaoInstalacao>();
		String sql = "SELECT ab.andar_bloco, i.internamento_interno, ab.id_andar_bloco FROM tblandarbloco ab INNER JOIN tblinternamentointerno i ON ab.FK_internamento = i.id_internamento_interno";
		try {
			con = Conexao.getConexao();
			 PreparedStatement preparador = con.prepareStatement(sql);
			 ResultSet rs = preparador.executeQuery();
			 while(rs.next())
			 {
				 GestaoInstalacao gInst = new GestaoInstalacao();
				 gInst.setId_andar_bloco(rs.getInt("id_andar_bloco"));
				 gInst.setInternamento(rs.getString("internamento_interno"));
				 gInst.setAndar_bloco(rs.getString("andar_bloco"));
				 lista.add(gInst);
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
		return lista;
}
	
public List <GestaoInstalacao> buscarInternamento(){
		
		List <GestaoInstalacao> lista = new ArrayList<GestaoInstalacao>();
		String sql = "SELECT * FROM tblinternamentointerno";
		try {
			con = Conexao.getConexao();
			 PreparedStatement preparador = con.prepareStatement(sql);
			 ResultSet rs = preparador.executeQuery();
			 while(rs.next())
			 {
				 GestaoInstalacao gInst = new GestaoInstalacao();
				 gInst.setFK_internamento(rs.getInt("id_internamento_interno"));
				 gInst.setInternamento(rs.getString("internamento_interno"));
				 lista.add(gInst);
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
		return lista;
}
	
	public void AddGestaoInstalacao(GestaoInstalacao gInst) {

		String sql = "INSERT INTO tblandarbloco (andar_bloco, FK_internamento)VALUES(?,?)";
		try {
			con = Conexao.getConexao();
			PreparedStatement ps = con.prepareStatement(sql);
			ps.setString(1, gInst.getAndar_bloco());
			ps.setInt(2, gInst.getFK_internamento());
			ps.execute();
			ps.close();
			System.out.println("Cadastro de Sucesso....");
		} catch (SQLException e) {
			e.printStackTrace();
		} catch (Exception e) {
			// TODO: handle exception
		} finally {
			try {
				con.close();
			} catch (SQLException ef) {
				System.out.println("Erro finalizar: " + ef);
			}
		}
	}
	
	public int XequeGestaoInstalacao(String valor, int FK_internamento)
	{
		int ok = 0;
		String sql = "SELECT * FROM tblandarbloco WHERE andar_bloco =? and FK_internamento =?";
		try {
			con = Conexao.getConexao();
			PreparedStatement preparador = con.prepareStatement(sql);
			 preparador.setString(1, valor);
			 preparador.setInt(2, FK_internamento);
			 ResultSet rs = preparador.executeQuery();
			 if(rs.next())
			 {
				 ok = 1;				 
			 }
			 preparador.close();
		} catch (SQLException e) {			
			e.printStackTrace();
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
		return ok;
	}
	
	public GestaoInstalacao edit_gestaoInstalacao(int ed) {
		GestaoInstalacao gestao = new GestaoInstalacao();
		String sql = "SELECT ab.andar_bloco, FK_internamento, i.internamento_interno, ab.id_andar_bloco FROM tblandarbloco ab INNER JOIN tblinternamentointerno i ON ab.FK_internamento = i.id_internamento_interno WHERE id_andar_bloco = ?";
		try {
			con = Conexao.getConexao();
			PreparedStatement preparador = con.prepareStatement(sql);
			preparador.setInt(1, ed);
			ResultSet rs = preparador.executeQuery();
			while (rs.next()) {
				gestao.setAndar_bloco(rs.getString("andar_bloco"));
				gestao.setFK_internamento(rs.getInt("FK_internamento"));
			}
			preparador.close();
		} catch (SQLException e) {
			e.printStackTrace();
		} catch (Exception e) {
			// TODO: handle exception
		} finally {
			try {
				con.close();
			} catch (SQLException ef) {
				System.out.println("Erro finalizar: " + ef);
			}
		}
		return gestao;
	}
	
	public void alt_gestaoInstalacao(GestaoInstalacao gInst)
	{
		String sql = "UPDATE tblandarbloco SET andar_bloco =?, FK_internamento =? WHERE id_andar_bloco = ?";
		try {
			con = Conexao.getConexao();
			PreparedStatement bg = con.prepareStatement(sql);
			
			bg.setString(1, gInst.getAndar_bloco());
			bg.setInt(2, gInst.getFK_internamento());
			bg.setInt(3, gInst.getId_andar_bloco());
			bg.execute();
			bg.close();
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
	}
	
	public void del_gestaoInstalacao (int id)
	{
		String sql = "DELETE FROM tblandarbloco  WHERE id_andar_bloco = ?";
		try {
			con = Conexao.getConexao();
			 PreparedStatement preparador = con.prepareStatement(sql);
			 preparador.setInt(1, id);
			 preparador.execute();
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
	}
//	------------------------------------------------ SECTOR
public List <GestaoInstalacao> buscarGestaoSector (){
		
		List <GestaoInstalacao> lista = new ArrayList<GestaoInstalacao>();
		String sql = "SELECT a.andar_bloco,	s.sector, s.extensao, s.id_sector, s.FK_internamento, i.internamento_interno  FROM tblandarbloco a JOIN tblsector s ON a.id_andar_bloco = s.FK_andar_bloco join tblinternamentointerno i  on s.FK_internamento = i.id_internamento_interno";
		try {
			con = Conexao.getConexao();
			 PreparedStatement preparador = con.prepareStatement(sql);
			 ResultSet rs = preparador.executeQuery();
			 while(rs.next())
			 {
				 GestaoInstalacao gInst = new GestaoInstalacao();
				 gInst.setAndar_bloco(rs.getString("andar_bloco"));
				 gInst.setSector(rs.getString("sector"));
				 gInst.setExtensao(rs.getInt("extensao"));
				 gInst.setId_sector(rs.getInt("id_sector"));
				 gInst.setFK_internamento(rs.getInt("FK_internamento"));
				 gInst.setInternamento(rs.getString("internamento_interno"));
				 lista.add(gInst);
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
		return lista;
}
public void AddGestaoSector(GestaoInstalacao gInst) {

	String sql = "INSERT INTO tblsector (sector, extensao, FK_andar_bloco, FK_internamento) VALUES (?,?,?,?)";
	try {
		con = Conexao.getConexao();
		PreparedStatement ps = con.prepareStatement(sql);
		ps.setString(1, gInst.getSector());
		ps.setInt(2, gInst.getExtensao());
		ps.setInt(3, gInst.getFK_andar_bloco());
		ps.setInt(4, gInst.getFK_internamento());
		ps.execute();
		ps.close();
		System.out.println("Cadastro de Sucesso Sector....");
	} catch (SQLException e) {
		e.printStackTrace();
	} catch (Exception e) {
		// TODO: handle exception
	} finally {
		try {
			con.close();
		} catch (SQLException ef) {
			System.out.println("Erro finalizar: " + ef);
		}
	}
}

public int XequeGestaoSector(int valor, int fk_internamento)
{
	int ok = 0;
	String sql = "SELECT * FROM tblsector WHERE FK_andar_bloco = ? and FK_internamento =?";
	try {
		con = Conexao.getConexao();
		PreparedStatement preparador = con.prepareStatement(sql);
		 preparador.setInt(1, valor);
		 preparador.setInt(2, fk_internamento);
		 ResultSet rs = preparador.executeQuery();
		 if(rs.next())
		 {
			 ok = 1;				 
		 }
		 preparador.close();
	} catch (SQLException e) {			
		e.printStackTrace();
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
	return ok;
}

public GestaoInstalacao edit_gestaoSector(int ed) {
	GestaoInstalacao gestao = null;
	String sql = "SELECT * FROM tblsector WHERE id_sector = ?";
	try {
		con = Conexao.getConexao();
		PreparedStatement preparador = con.prepareStatement(sql);
		preparador.setInt(1, ed);
		ResultSet rs = preparador.executeQuery();
		while (rs.next()) {
			gestao = new GestaoInstalacao();
			gestao.setSector(rs.getString("sector"));
			gestao.setExtensao(rs.getInt("extensao"));
			gestao.setFK_andar_bloco(rs.getInt("FK_andar_bloco"));
			gestao.setFK_internamento(rs.getInt("FK_internamento"));
		}
		preparador.close();
	} catch (SQLException e) {
		e.printStackTrace();
	} catch (Exception e) {
		// TODO: handle exception
	} finally {
		try {
			con.close();
		} catch (SQLException ef) {
			System.out.println("Erro finalizar: " + ef);
		}
	}
	return gestao;
}

public void alt_gestaoSector(GestaoInstalacao gInst)
{
	String sql = "UPDATE tblsector SET sector =?, extensao = ?, FK_andar_bloco = ?, FK_internamento =? WHERE id_sector = ?";
	try {
		con = Conexao.getConexao();
		PreparedStatement bg = con.prepareStatement(sql);
		bg.setString(1, gInst.getSector());
		bg.setInt(2, gInst.getExtensao());
		bg.setInt(3, gInst.getFK_andar_bloco());
		bg.setInt(4, gInst.getFK_internamento());
		bg.setInt(5, gInst.getId_sector());
		bg.execute();
		bg.close();
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
}

public void del_gestaoSector (int id)
{
	String sql = "DELETE FROM tblsector  WHERE id_sector = ?";
	try {
		con = Conexao.getConexao();
		 PreparedStatement preparador = con.prepareStatement(sql);
		 preparador.setInt(1, id);
		 preparador.execute();
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
}
//------------------------------------------------- FIM SECTOR
//------------------------------------------------- SALA
public List <GestaoInstalacao> buscarGestaoDeSala (){
	
	List <GestaoInstalacao> lista = new ArrayList<GestaoInstalacao>();
	String sql = "SELECT a.andar_bloco, s.sector, sa.id_sala, sa.numero_sala, sa.descricao, sa.FK_internamento, i.internamento_interno FROM tblandarbloco a JOIN tblsector s ON a.id_andar_bloco = s.FK_andar_bloco JOIN tblsala sa ON s.id_sector = sa.FK_sector JOIN tblinternamentointerno i ON sa.FK_internamento = i.id_internamento_interno";
	try {
		con = Conexao.getConexao();
		 PreparedStatement preparador = con.prepareStatement(sql);
		 ResultSet rs = preparador.executeQuery();
		 while(rs.next())
		 {
			 GestaoInstalacao gInst = new GestaoInstalacao();
			 gInst.setAndar_bloco(rs.getString("andar_bloco"));
			 gInst.setSector(rs.getString("sector"));
			 gInst.setNumero_sala(rs.getInt("numero_sala"));
			 gInst.setDescricao(rs.getString("descricao"));
			 gInst.setId_sala(rs.getInt("id_sala"));
			 gInst.setFK_internamento(rs.getInt("FK_internamento"));
			 gInst.setInternamento(rs.getString("internamento_interno"));
			 lista.add(gInst);
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
	return lista;
}

public void AddGestaoDeSala(GestaoInstalacao gInst) {

	String sql = "INSERT INTO tblsala (numero_sala, descricao, FK_sector, FK_internamento) VALUES (?,?,?,?)";
	try {
		con = Conexao.getConexao();
		PreparedStatement ps = con.prepareStatement(sql);
		ps.setInt(1, gInst.getNumero_sala());
		ps.setString(2, gInst.getDescricao());
		ps.setInt(3, gInst.getFK_sector());
		ps.setInt(4, gInst.getFK_internamento());
		ps.execute();
		ps.close();
		System.out.println("Cadastro de Sucesso Sector....");
	} catch (SQLException e) {
		e.printStackTrace();
	} catch (Exception e) {
		// TODO: handle exception
	} finally {
		try {
			con.close();
		} catch (SQLException ef) {
			System.out.println("Erro finalizar: " + ef);
		}
	}
}

public List <GestaoInstalacao> buscarSectorDepAndarBloco(int dados)
{
	List <GestaoInstalacao> lista = new ArrayList<GestaoInstalacao>();
	String sql = "SELECT a.andar_bloco,	s.sector, s.extensao, s.id_sector, s.FK_internamento, i.internamento_interno  FROM tblandarbloco a JOIN tblsector s ON a.id_andar_bloco = s.FK_andar_bloco join tblinternamentointerno i  on s.FK_internamento = i.id_internamento_interno where a.id_andar_bloco = ?";
	try {
		con = Conexao.getConexao();
		 PreparedStatement preparador = con.prepareStatement(sql);
		 preparador.setInt(1, dados);
		 ResultSet rs = preparador.executeQuery();
		 while(rs.next())
		 {
			 GestaoInstalacao pd = new GestaoInstalacao();
			 pd.setFK_sector(rs.getInt("id_sector"));
			 pd.setSector(rs.getString("sector"));
			 lista.add(pd);
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
	return lista;
}

public List <GestaoInstalacao> buscarSectorDepAndarBloco2(int dados)
{
	List <GestaoInstalacao> lista = new ArrayList<GestaoInstalacao>();
	String sql = "SELECT a.andar_bloco, s.sector, sa.id_sala, sa.numero_sala, sa.descricao, sa.FK_internamento, i.internamento_interno FROM tblandarbloco a JOIN tblsector s ON a.id_andar_bloco = s.FK_andar_bloco JOIN tblsala sa ON s.id_sector = sa.FK_sector JOIN tblinternamentointerno i ON sa.FK_internamento = i.id_internamento_interno where s.id_sector = ?";
	try {
		con = Conexao.getConexao();
		 PreparedStatement preparador = con.prepareStatement(sql);
		 preparador.setInt(1, dados);
		 ResultSet rs = preparador.executeQuery();
		 while(rs.next())
		 {
			 GestaoInstalacao pd = new GestaoInstalacao();
			 pd.setId_sala(rs.getInt("id_sala"));
			 pd.setNumSala(rs.getString("numero_sala"));
			 lista.add(pd);
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
	return lista;
}

public List <GestaoInstalacao> buscarSectorDepAndarBlocos(int dados)
{
	List <GestaoInstalacao> lista = new ArrayList<GestaoInstalacao>();
	String sql = "SELECT a.andar_bloco, a.id_andar_bloco, s.sector, s.extensao, s.id_sector, s.FK_internamento, i.internamento_interno,sa.id_sala,sa.numero_sala FROM tblandarbloco a JOIN tblsector s ON a.id_andar_bloco = s.FK_andar_bloco join tblinternamentointerno i  on s.FK_internamento = i.id_internamento_interno inner join tblsala sa  on  s.id_sector = sa.FK_sector where a.id_andar_bloco = ?";
	try {
		con = Conexao.getConexao();
		 PreparedStatement preparador = con.prepareStatement(sql);
		 preparador.setInt(1, dados);
		 ResultSet rs = preparador.executeQuery();
		 while(rs.next())
		 {
			 GestaoInstalacao pd = new GestaoInstalacao();
			 pd.setFK_sector(rs.getInt("id_sector"));
			 pd.setSector(rs.getString("sector"));
			 pd.setId_sala(rs.getInt("id_sala"));
			 pd.setNumSala(rs.getString("numero_sala"));
			 lista.add(pd);
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
	return lista;
}

public int XequeGestaoDeSala(int num, int internam, int numSala)
{
	int ok = 0;
	String sql = "SELECT * FROM tblsala WHERE FK_sector = ? and FK_internamento =? and numero_sala =?";
	try {
		con = Conexao.getConexao();
		PreparedStatement preparador = con.prepareStatement(sql);
		preparador.setInt(1, num);
		preparador.setInt(2, internam);
		preparador.setInt(3, numSala);
		 ResultSet rs = preparador.executeQuery();
		 if(rs.next())
		 {
			 ok = 1;				 
		 }
		 preparador.close();
	} catch (SQLException e) {			
		e.printStackTrace();
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
	return ok;
}

public GestaoInstalacao edit_gestaoDeSala(int ed) {
	GestaoInstalacao gestao = null;
	String sql = "SELECT a.andar_bloco, s.sector, s.FK_andar_bloco, s.id_sector, sa.id_sala, sa.numero_sala, sa.descricao, sa.FK_internamento, i.internamento_interno FROM tblandarbloco a JOIN tblsector s ON a.id_andar_bloco = s.FK_andar_bloco JOIN tblsala sa ON s.id_sector = sa.FK_sector JOIN tblinternamentointerno i ON sa.FK_internamento = i.id_internamento_interno where sa.id_sala = ?";
	try {
		con = Conexao.getConexao();
		PreparedStatement preparador = con.prepareStatement(sql);
		preparador.setInt(1, ed);
		ResultSet rs = preparador.executeQuery();
		while (rs.next()) {
			gestao = new GestaoInstalacao();
			gestao.setFK_andar_bloco(rs.getInt("FK_andar_bloco"));
			gestao.setFK_sector(rs.getInt("id_sector"));
			gestao.setSector(rs.getString("sector"));
			gestao.setNumero_sala(rs.getInt("numero_sala"));
			gestao.setDescricao(rs.getString("descricao"));
			gestao.setFK_internamento(rs.getInt("FK_internamento"));
			gestao.setInternamento(rs.getString("internamento_interno"));
		}
		preparador.close();
	} catch (SQLException e) {
		e.printStackTrace();
	} catch (Exception e) {
		// TODO: handle exception
	} finally {
		try {
			con.close();
		} catch (SQLException ef) {
			System.out.println("Erro finalizar: " + ef);
		}
	}
	return gestao;
}

public void alt_gestaoDeSala(GestaoInstalacao gInst)
{
	String sql = "UPDATE tblsala SET numero_sala =?, descricao = ?, FK_sector = ? WHERE id_sala = ?";
	try {
		con = Conexao.getConexao();
		PreparedStatement bg = con.prepareStatement(sql);
		bg.setInt(1, gInst.getNumero_sala());
		bg.setString(2, gInst.getDescricao());
		bg.setInt(3, gInst.getFK_sector());
		bg.setInt(4, gInst.getId_sala());
		bg.execute();
		bg.close();
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
}

public void del_gestaoDeSala (int id)
{
	String sql = "DELETE FROM tblsala  WHERE id_sala = ?";
	try {
		con = Conexao.getConexao();
		 PreparedStatement preparador = con.prepareStatement(sql);
		 preparador.setInt(1, id);
		 preparador.execute();
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
}
//------------------------------------------ FIM SALA
//------------------------------------------ LEITO
public List <GestaoInstalacao> buscarGestaoDeLeito (){
	
	List <GestaoInstalacao> lista = new ArrayList<GestaoInstalacao>();
	String sql = "SELECT a.andar_bloco, s.sector, sala.numero_sala, sala.descricao, l.leito, l.id_leito FROM tblandarbloco a JOIN tblsector s ON a.id_andar_bloco = s.FK_andar_bloco JOIN tblsala sala ON s.id_sector = sala.FK_sector JOIN tblleito l ON sala.id_sala = l.FK_sala;";
	try {
		con = Conexao.getConexao();
		 PreparedStatement preparador = con.prepareStatement(sql);
		 ResultSet rs = preparador.executeQuery();
		 while(rs.next())
		 {
			 GestaoInstalacao gInst = new GestaoInstalacao();
			 gInst.setAndar_bloco(rs.getString("andar_bloco"));
			 gInst.setSector(rs.getString("sector"));
			 gInst.setNumero_sala(rs.getInt("numero_sala"));
			 gInst.setDescricao(rs.getString("descricao"));
			 gInst.setLeito(rs.getString("leito"));
			 gInst.setId_leito(rs.getInt("id_leito"));
			 lista.add(gInst);
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
	return lista;
}

public List <GestaoInstalacao> buscarSalaDepAndarBloco(int dados)
{
	List <GestaoInstalacao> lista = new ArrayList<GestaoInstalacao>();
	String sql = "SELECT a.andar_bloco, s.sector, sala.FK_sector, sala.id_sala, sala.numero_sala, sala.descricao, l.leito , l.id_leito FROM tblandarbloco a JOIN tblsector s ON a.id_andar_bloco = s.FK_andar_bloco JOIN tblsala sala ON s.id_sector = sala.FK_sector JOIN tblleito l ON sala.id_sala = l.FK_sala WHERE sala.FK_sector = ?;";
	try {
		con = Conexao.getConexao();
		 PreparedStatement preparador = con.prepareStatement(sql);
		 preparador.setInt(1, dados);
		 ResultSet rs = preparador.executeQuery();
		 while(rs.next())
		 {
			 GestaoInstalacao pd = new GestaoInstalacao();
			 pd.setId_sala(rs.getInt("id_sala"));
			 pd.setNumero_sala(rs.getInt("numero_sala"));
			 lista.add(pd);
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
	return lista;
}

public void AddGestaoDeLeito(GestaoInstalacao gInst) {

	String sql = "INSERT INTO tblleito (leito, status, FK_sala) VALUES (?,?,?)";
	try {
		con = Conexao.getConexao();
		PreparedStatement ps = con.prepareStatement(sql);
		ps.setString(1, gInst.getLeito());
		ps.setInt(2, gInst.getStatus());
		ps.setInt(3, gInst.getId_sala());
		ps.execute();
		ps.close();
		System.out.println("Cadastro de Sucesso Leito....");
	} catch (SQLException e) {
		e.printStackTrace();
	} catch (Exception e) {
		// TODO: handle exception
	} finally {
		try {
			con.close();
		} catch (SQLException ef) {
			System.out.println("Erro finalizar: " + ef);
		}
	}
}

public GestaoInstalacao edit_gestaoLeito(int ed) {
	GestaoInstalacao gestao = null;
	String sql = "SELECT a.andar_bloco, s.sector, id_andar_bloco, sala.FK_sector, sala.id_sala, sala.numero_sala, sala.descricao, l.leito , l.id_leito FROM tblandarbloco a JOIN tblsector s ON a.id_andar_bloco = s.FK_andar_bloco JOIN tblsala sala ON s.id_sector = sala.FK_sector JOIN tblleito l ON sala.id_sala = l.FK_sala WHERE id_leito = ?";
	try {
		con = Conexao.getConexao();
		PreparedStatement preparador = con.prepareStatement(sql);
		preparador.setInt(1, ed);
		ResultSet rs = preparador.executeQuery();
		while (rs.next()) {
			gestao = new GestaoInstalacao();
			gestao.setAndar_bloco(rs.getString("andar_bloco"));
			gestao.setId_andar_bloco(rs.getInt("id_andar_bloco"));
			gestao.setSector(rs.getString("sector"));
			gestao.setId_sector(rs.getInt("FK_sector"));
			gestao.setNumero_sala(rs.getInt("numero_sala"));
			gestao.setId_sala(rs.getInt("id_sala"));
			gestao.setDescricao(rs.getString("descricao"));
			gestao.setLeito(rs.getString("leito"));
		}
		preparador.close();
	} catch (SQLException e) {
		e.printStackTrace();
	} catch (Exception e) {
		// TODO: handle exception
	} finally {
		try {
			con.close();
		} catch (SQLException ef) {
			System.out.println("Erro finalizar: " + ef);
		}
	}
	return gestao;
}

public void alt_gestaoDeLeito(GestaoInstalacao gInst)
{
	String sql = "UPDATE tblleito SET leito =?, status = ?, FK_sala = ? WHERE id_leito = ?";
	try {
		con = Conexao.getConexao();
		PreparedStatement bg = con.prepareStatement(sql);
		bg.setString(1, gInst.getLeito());
		bg.setInt(2, gInst.getStatus());
		bg.setInt(3, gInst.getId_sala());
		bg.setInt(4, gInst.getId_leito());
		bg.execute();
		bg.close();
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
}

public void del_gestaoDeLeito (int id)
{
	String sql = "DELETE FROM tblleito  WHERE id_leito = ?";
	try {
		con = Conexao.getConexao();
		 PreparedStatement preparador = con.prepareStatement(sql);
		 preparador.setInt(1, id);
		 preparador.execute();
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
}

public int XequeGestaoDeLeito(String valor, int valor1)
{
	int ok = 0;
	String sql = "SELECT * FROM tblleito WHERE leito = ? and FK_sala =?";
	try {
		con = Conexao.getConexao();
		PreparedStatement preparador = con.prepareStatement(sql);
		preparador.setString(1, valor);
		preparador.setInt(2, valor1);
		 ResultSet rs = preparador.executeQuery();
		 if(rs.next())
		 {
			 ok = 1;				 
		 }
		 preparador.close();
	} catch (SQLException e) {			
		e.printStackTrace();
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
	return ok;
}
//------------------------------------------ FIM LEITO
}
