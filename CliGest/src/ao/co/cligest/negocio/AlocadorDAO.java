package ao.co.cligest.negocio;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import ao.co.cligest.entidades.Alocador;
import ao.co.cligest.entidades.Embalagem;
import ao.co.cligest.entidades.Produtos;

public class AlocadorDAO {
	
	private Connection con ;
	
	public void setLocalDeArmazena(Alocador pd)
	{
		String sql = "INSERT INTO tbllocaldearmazenamento (localdearmazenamento) VALUES (?)";
		try {
			con = Conexao.getConexao();
			PreparedStatement cp = con.prepareStatement(sql);
			cp.setString(1, pd.getLocaldearmazenamento());
			cp.execute();
			cp.close(); 
		} catch (SQLException e) {
			// TODO Auto-generated catch block
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
	public List<Alocador> getLocalDeArmazena() {
		List<Alocador> lista = new ArrayList<Alocador>();
		String sql = "SELECT * FROM tbllocaldearmazenamento";
		try {
			Alocador pd = null;
			con = Conexao.getConexao();
			PreparedStatement preparador = con.prepareStatement(sql);
			ResultSet rs = preparador.executeQuery();
			while (rs.next()) {
				pd = new Alocador();
				pd.setId_local_dearmazenamento(rs.getInt("id_local_dearmazenamento"));
				pd.setLocaldearmazenamento(rs.getString("localdearmazenamento"));
				lista.add(pd);
			}
			preparador.close();
		} catch (SQLException e) {

			e.printStackTrace();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			try {
				con.close();
			} catch (SQLException ef) {
				System.out.println("Erro finalizar: " + ef);
			}
		}
		return lista;
	}
	public Alocador edit_alocador(String ed)
	{
		Alocador bg = new Alocador();
		String sql = "SELECT * FROM tbllocaldearmazenamento WHERE id_local_dearmazenamento = ?";
		try {
			con = Conexao.getConexao();
			PreparedStatement preparador = con.prepareStatement(sql);
			 preparador.setString(1, ed);
			 ResultSet rs = preparador.executeQuery();
			 while(rs.next())
			 {
				 bg.setId_local_dearmazenamento(rs.getInt("id_local_dearmazenamento"));
				 bg.setLocaldearmazenamento(rs.getString("localdearmazenamento"));				 
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
		return bg;
	}
	
	public int XequeAlocadora(String termo)
	{
		int ok = 0;
		String sql = "SELECT * FROM tbllocaldearmazenamento WHERE localdearmazenamento Like ?";
		try {
			con = Conexao.getConexao();
			PreparedStatement preparador = con.prepareStatement(sql);
			 preparador.setString(1, termo);
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
	
//	5- Alterar servi�o
	public void alt_alocador(Alocador gb)
	{
		String sql = "UPDATE tbllocaldearmazenamento SET localdearmazenamento =? WHERE id_local_dearmazenamento = ?";
		try {
			con = Conexao.getConexao();
			PreparedStatement bg = con.prepareStatement(sql);
			
			bg.setString(1, gb.getLocaldearmazenamento());
			bg.setInt(2, gb.getId_local_dearmazenamento());
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
	public void del_alocar(int id)
	{
		String sql = "DELETE FROM tbllocaldearmazenamento  WHERE id_local_dearmazenamento=?";
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
	public void setBlocoAla(Alocador pd)
	{
		String sql = "INSERT INTO tblalabloco (FK_local_dearmazenamento, alabloco_doproduto) VALUES (?,?)";
		try {
			con = Conexao.getConexao();
			PreparedStatement cp = con.prepareStatement(sql);
			cp.setInt(1, pd.getFK_local_dearmazenamento());
			cp.setString(2, pd.getAlabloco_doproduto());
			cp.execute();
			cp.close(); 
		} catch (SQLException e) {
			// TODO Auto-generated catch block
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
	public int XequeBlocoAla(String termo)
	{
		int ok = 0;
		String sql = "SELECT * FROM tblalabloco WHERE alabloco_doproduto Like ?";
		try {
			con = Conexao.getConexao();
			PreparedStatement preparador = con.prepareStatement(sql);
			 preparador.setString(1, termo);
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
	public List<Alocador> getBlocoAla() {
		List<Alocador> lista = new ArrayList<Alocador>();
		String sql = "SELECT * FROM vwalabloco";
		try {
			Alocador pd = null;
			con = Conexao.getConexao();
			PreparedStatement preparador = con.prepareStatement(sql);
			ResultSet rs = preparador.executeQuery();
			while (rs.next()) {
				pd = new Alocador();
				pd.setId_alabloco(rs.getInt("id_alabloco"));
				pd.setFK_local_dearmazenamento(rs.getInt("fk_local_dearmazenamento"));
				pd.setLocaldearmazenamento(rs.getString("localdearmazenamento"));
				pd.setAlabloco_doproduto(rs.getString("alabloco_doproduto"));
				lista.add(pd);
			}
			preparador.close();
		} catch (SQLException e) {

			e.printStackTrace();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			try {
				con.close();
			} catch (SQLException ef) {
				System.out.println("Erro finalizar: " + ef);
			}
		}
		return lista;
	}
	public List <Alocador> buscarAlaBlocoPorLocal(int dado)
	{
		List <Alocador> lista = new ArrayList<Alocador>();
		String sql = "SELECT * FROM tblalabloco where FK_local_dearmazenamento = ?";
		try {
			con = Conexao.getConexao();
			 PreparedStatement preparador = con.prepareStatement(sql);
			 preparador.setInt(1, dado);
			 ResultSet rs = preparador.executeQuery();
			 while(rs.next())
			 {
				 Alocador pd = new Alocador();
				 pd.setId_alabloco(rs.getInt("id_alabloco"));
				 pd.setAlabloco_doproduto(rs.getString("alabloco_doproduto"));
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
	public Alocador edit_alaBloco(String ed)
	{
		Alocador bg = null;
		String sql = "SELECT * FROM vwalabloco WHERE id_alabloco = ?";
		try {
			con = Conexao.getConexao();
			PreparedStatement preparador = con.prepareStatement(sql);
			 preparador.setString(1, ed);
			 ResultSet rs = preparador.executeQuery();
			 while(rs.next())
			 {
				 bg = new Alocador();
				 bg.setId_alabloco(rs.getInt("id_alabloco"));
				 bg.setFK_local_dearmazenamento(rs.getInt("FK_local_dearmazenamento"));
				 bg.setLocaldearmazenamento(rs.getString("localdearmazenamento"));
				 bg.setAlabloco_doproduto(rs.getString("alabloco_doproduto"));				 
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
		return bg;
	}
	
//	5- Alterar servi�o
	public void alt_alaBloco(Alocador gb)
	{
		String sql = "UPDATE tblalabloco SET Alabloco_doproduto =?, FK_local_dearmazenamento=? WHERE id_alabloco = ?";
		try {
			con = Conexao.getConexao();
			PreparedStatement bg = con.prepareStatement(sql);
			
			bg.setString(1, gb.getAlabloco_doproduto());
			bg.setInt(2, gb.getFK_local_dearmazenamento());
			bg.setInt(3, gb.getId_alabloco());
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
	public void del_alaBloco(int id)
	{
		String sql = "DELETE FROM tblalabloco  WHERE id_alabloco=?";
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
	public void setMontra(Alocador pd)
	{
		String sql = "INSERT INTO tblmontra (FK_alabloco,montra_deproduto,FK_local_armazenamento) VALUES (?,?,?)";
		try {
			con = Conexao.getConexao();
			PreparedStatement cp = con.prepareStatement(sql);
			cp.setInt(1, pd.getFK_alabloco());
			cp.setString(2, pd.getMontra_deproduto());
			cp.setInt(3, pd.getFK_local_dearmazenamento());
			cp.execute();
			cp.close(); 
		} catch (SQLException e) {
			// TODO Auto-generated catch block
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
	public int XequeMontra(String termo)
	{
		int ok = 0;
		String sql = "SELECT * FROM tblmontra WHERE montra_deproduto Like ?";
		try {
			con = Conexao.getConexao();
			PreparedStatement preparador = con.prepareStatement(sql);
			 preparador.setString(1, termo);
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

	public List <Alocador> buscarMontraPorAlcaBloco(int dado)
	{
		List <Alocador> lista = new ArrayList<Alocador>();
		String sql = "SELECT * FROM tblmontra where FK_alabloco =?";
		try {
			con = Conexao.getConexao();
			 PreparedStatement preparador = con.prepareStatement(sql);
			 preparador.setInt(1, dado);
			 ResultSet rs = preparador.executeQuery();
			 while(rs.next())
			 {
				 Alocador pd = new Alocador();
				 pd.setId_montra_deproduto(rs.getInt("id_montra_deproduto"));
				 pd.setMontra_deproduto(rs.getString("montra_deproduto"));
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
	public List<Alocador> getMontra() {
		Alocador pd = null;
		List<Alocador> lista = new ArrayList<Alocador>();
		String sql = "SELECT * FROM vwmontra";
		try {
			
			con = Conexao.getConexao();
			PreparedStatement preparador = con.prepareStatement(sql);
			ResultSet rs = preparador.executeQuery();
			while (rs.next()) {
				pd = new Alocador();
				pd.setId_montra_deproduto(rs.getInt("id_montra_deproduto"));
				pd.setLocaldearmazenamento(rs.getString("localdearmazenamento"));
				pd.setFK_local_dearmazenamento(rs.getInt("FK_local_armazenamento"));
				pd.setAlabloco_doproduto(rs.getString("alabloco_doproduto"));
				pd.setFK_alabloco(rs.getInt("FK_alabloco"));
				pd.setMontra_deproduto(rs.getString("montra_deproduto"));
				lista.add(pd);
			}
			preparador.close();
		} catch (SQLException e) {

			e.printStackTrace();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			try {
				con.close();
			} catch (SQLException ef) {
				System.out.println("Erro finalizar: " + ef);
			}
		}
		return lista;
	}
	public Alocador edit_montra(String ed)
	{
		Alocador bg = null;
		String sql = "SELECT * FROM vwmontra WHERE id_montra_deproduto = ?";
		try {
			con = Conexao.getConexao();
			PreparedStatement preparador = con.prepareStatement(sql);
			 preparador.setString(1, ed);
			 ResultSet rs = preparador.executeQuery();
			 while(rs.next())
			 {
				 bg = new Alocador();
				 bg.setId_montra_deproduto(rs.getInt("id_montra_deproduto"));
				 bg.setFK_local_dearmazenamento(rs.getInt("FK_local_armazenamento"));
				 bg.setLocaldearmazenamento(rs.getString("localdearmazenamento"));
				 bg.setFK_alabloco(rs.getInt("FK_alabloco"));
				 bg.setAlabloco_doproduto(rs.getString("alabloco_doproduto"));
				 bg.setMontra_deproduto(rs.getString("montra_deproduto"));				 
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
		return bg;
	}
	
//	5- Alterar servi�o
	public void alt_montra(Alocador gb)
	{
		String sql = "UPDATE tblmontra SET FK_alabloco =?, montra_deproduto =?,FK_local_armazenamento=? WHERE id_montra_deproduto = ?";
		try {
			con = Conexao.getConexao();
			PreparedStatement bg = con.prepareStatement(sql);
			
			bg.setInt(1, gb.getFK_alabloco());
			bg.setString(2, gb.getMontra_deproduto());
			bg.setInt(3, gb.getFK_local_dearmazenamento());
			bg.setInt(4, gb.getId_montra_deproduto());
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
	public void del_montra(int id)
	{
		String sql = "DELETE FROM tblmontra  WHERE id_montra_deproduto=?";
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
	public void setPratileira(Alocador pd)
	{
		String sql = "INSERT INTO tblprartileira (FK_montra_deproduto,prartileira,FK_local_armazenamento,FK_ala_bloco) VALUES (?,?,?,?)";
		try {
			con = Conexao.getConexao();
			PreparedStatement cp = con.prepareStatement(sql);
			cp.setInt(1, pd.getFK_montra_deproduto());
			cp.setString(2, pd.getPrartileira());
			cp.setInt(3, pd.getFK_local_dearmazenamento());
			cp.setInt(4, pd.getFK_alabloco());
			cp.execute();
			cp.close(); 
		} catch (SQLException e) {
			// TODO Auto-generated catch block
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
	public int XequePratileira(String termo)
	{
		int ok = 0;
		String sql = "SELECT * FROM tblprartileira WHERE prartileira Like ?";
		try {
			con = Conexao.getConexao();
			PreparedStatement preparador = con.prepareStatement(sql);
			 preparador.setString(1, termo);
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

	public List<Alocador> getPratileira() {
		List<Alocador> lista = new ArrayList<Alocador>();
		String sql = "SELECT * FROM vwprartileira";
		try {
			Alocador pd = null;
			con = Conexao.getConexao();
			PreparedStatement preparador = con.prepareStatement(sql);
			ResultSet rs = preparador.executeQuery();
			while (rs.next()) {
				pd = new Alocador();
				pd.setId_prartileira(rs.getInt("id_prartileira"));
				pd.setPrartileira(rs.getString("prartileira"));
				pd.setLocaldearmazenamento(rs.getString("localdearmazenamento"));
				pd.setFK_local_dearmazenamento(rs.getInt("FK_local_armazenamento"));
				pd.setAlabloco_doproduto(rs.getString("alabloco_doproduto"));
				pd.setFK_alabloco(rs.getInt("FK_ala_bloco"));
				pd.setMontra_deproduto(rs.getString("montra_deproduto"));
				pd.setFK_montra_deproduto(rs.getInt("FK_montra_deproduto"));
				lista.add(pd);
			}
			preparador.close();
		} catch (SQLException e) {

			e.printStackTrace();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			try {
				con.close();
			} catch (SQLException ef) {
				System.out.println("Erro finalizar: " + ef);
			}
		}
		return lista;
	}
	public Alocador edit_Pratileira(String ed)
	{
		Alocador bg = null;
		String sql = "SELECT * FROM vwprartileira WHERE id_prartileira = ?";
		try {
			con = Conexao.getConexao();
			PreparedStatement preparador = con.prepareStatement(sql);
			 preparador.setString(1, ed);
			 ResultSet rs = preparador.executeQuery();
			 while(rs.next())
			 {
				 bg = new Alocador();
				 bg.setId_prartileira(rs.getInt("id_prartileira"));
				 bg.setPrartileira(rs.getString("prartileira"));
				 bg.setLocaldearmazenamento(rs.getString("localdearmazenamento"));
				 bg.setFK_local_dearmazenamento(rs.getInt("FK_local_armazenamento"));
				 bg.setAlabloco_doproduto(rs.getString("alabloco_doproduto"));
				 bg.setFK_alabloco(rs.getInt("FK_ala_bloco"));
				 bg.setMontra_deproduto(rs.getString("montra_deproduto"));
				 bg.setFK_montra_deproduto(rs.getInt("FK_montra_deproduto"));				 
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
		return bg;
	}
	
//	5- Alterar servi�o
	public void alt_pratileira(Alocador gb)
	{
		String sql = "UPDATE tblprartileira SET FK_montra_deproduto =?, prartileira =?, FK_local_armazenamento =?, FK_ala_bloco =? WHERE id_prartileira = ?";
		try {
			con = Conexao.getConexao();
			PreparedStatement bg = con.prepareStatement(sql);
			
			bg.setInt(1, gb.getFK_montra_deproduto());
			bg.setString(2, gb.getPrartileira());
			bg.setInt(3, gb.getFK_local_dearmazenamento());
			bg.setInt(4, gb.getFK_alabloco());
			bg.setInt(5, gb.getId_prartileira());
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
	public void del_pratileira(int id)
	{
		String sql = "DELETE FROM tblprartileira  WHERE id_prartileira=?";
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
}
