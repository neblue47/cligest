package ao.co.cligest.negocio;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import ao.co.cligest.contracts.IConfiguraIdade;
import ao.co.cligest.entidades.MenorIdade;

public class ConfiguraIdadeDAO implements IConfiguraIdade{

	Connection con; 
	@SuppressWarnings("resource")
	@Override
	public void configuraMenorIdade(MenorIdade m) {
		String sql = "SELECT * FROM tblconfigurarmenor WHERE id_configurar_menor = ?";
		try {
			con = Conexao.getConexao();
			PreparedStatement ps = con.prepareStatement(sql);
			ps.setInt(1, m.getId_configurar_menor()); 
			ResultSet rs = ps.executeQuery();
			if(rs.next())
			{
				ps.clearParameters();
				sql = "UPDATE tblconfigurarmenor SET idade_maxima = ?, paga_maxima = ? , FK_funcionario = ? WHERE id_configurar_menor = ?";
				ps = con.prepareStatement(sql);
				ps.setInt(1, m.getIdade());
				ps.setInt(2, m.getPaga_maxima());
				ps.setInt(3, m.getFK_funcionario());
				ps.setInt(4, m.getId_configurar_menor());
				ps.execute();
				ps.close();
			}
			else
			{
				ps.clearParameters();
				sql = "INSERT INTO tblconfigurarmenor (idade_maxima , paga_maxima, FK_funcionario) VALUE (?,?,?)";
				ps = con.prepareStatement(sql);
				ps.setInt(1, m.getIdade());
				ps.setInt(2, m.getPaga_maxima());
				ps.setInt(3, m.getFK_funcionario());
				ps.execute();
				ps.close();
			}
		con.close();
		}  
		catch (Exception e) {
			e.printStackTrace();
		}
	 
	
	}

	@SuppressWarnings("resource")
	@Override
	public void configuraTerceiraIdade(MenorIdade m) {
		String sql = "SELECT * FROM tblconfigurarmenor WHERE id_configurar_menor = ?";
		try {
			con = Conexao.getConexao();
			PreparedStatement ps = con.prepareStatement(sql);
			ps.setInt(1, m.getId_configurar_menor()); 
			ResultSet rs = ps.executeQuery();
			if(rs.next())
			{
				ps.clearParameters();
				sql = "UPDATE tblconfigurarmenor SET idade_minima = ?, paga_minima = ? , FK_funcionario = ? WHERE id_configurar_menor = ?";
				ps = con.prepareStatement(sql);
				ps.setInt(1, m.getIdade_minima());
				ps.setInt(2, m.getPaga_minima());
				ps.setInt(3, m.getFK_funcionario());
				ps.setInt(4, m.getId_configurar_menor());
				ps.execute();
				ps.close();
			}
			else
			{
				ps.clearParameters();
				sql = "INSERT INTO tblconfigurarmenor (idade_minima , paga_minima, FK_funcionario) VALUE (?,?,?)";
				ps = con.prepareStatement(sql);
				ps.setInt(1, m.getIdade_minima());
				ps.setInt(2, m.getPaga_minima());
				ps.setInt(3, m.getFK_funcionario());
				ps.execute();
				ps.close();
			}
		con.close();
		}  
		catch (Exception e) {
			e.printStackTrace();
		}
		
	}

	@Override
	public void configuraIdade(MenorIdade m) {
		try {
			
		} catch (Exception e) {
			// TODO: handle exception
		}		
	}

	@Override
	public MenorIdade buscarConfiguracoesIdade() {
		String sql = "SELECT * FROM tblconfigurarmenor ";
		
		try {
			con = Conexao.getConexao();
			PreparedStatement ps = con.prepareStatement(sql);
			 
			ResultSet rs = ps.executeQuery();
			if(rs.next())
			{
				MenorIdade m = new MenorIdade();
				m.setId_configurar_menor(rs.getInt("id_configurar_menor"));
				m.setIdade(rs.getInt("idade_maxima"));
				m.setIdade_minima(rs.getInt("idade_minima"));
				m.setPaga_maxima(rs.getInt("paga_maxima"));
				m.setPaga_minima(rs.getInt("paga_minima"));
				return m;
			} 
		 ps.close();
		con.close();
		}  
		catch (Exception e) {
			e.printStackTrace();
		}
		return new MenorIdade();
	}

}
