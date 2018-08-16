package ao.co.cligest.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Calendar;

import ao.co.cligest.contracts.IPagamentos;
import ao.co.cligest.entidades.Caixa;

public class PagamentoDAO extends FacturacaoDAO implements IPagamentos {

	private Connection con;
	private Formatando ft = new Formatando();

	@Override
	public String nomeFactaura() {
		String nomeFactaura = "";
		String sql = "SELECT * FROM tblconfigurarnomefactura LIMIT 0,1";
		try {
			con = Conexao.getConexao();
			PreparedStatement preparador = con.prepareStatement(sql);
			ResultSet rs = preparador.executeQuery();
			if (rs.next()) {
				nomeFactaura = rs.getString("config_nome_factura") + "V";
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

		return nomeFactaura;
	}

	@Override
	public String getNomeFactura() {
		String nomeFactura = "";
		String sql = "SELECT * FROM tblconfigurarnomefactura LIMIT 0,1";
		try {
			con = Conexao.getConexao();
			PreparedStatement preparador = con.prepareStatement(sql);
			ResultSet rs = preparador.executeQuery();
			if (rs.next())
				nomeFactura = rs.getString("config_nome_factura");
			preparador.close();
			con.close();
		} catch (Exception e) {
			e.printStackTrace();
		}

		return nomeFactura;
	}

	@Override
	public int getCaixaUtente(int codUser, Caixa c) {
		int ultimoId = 0;
		String sql = "Select * from vwaberturadecaixa where FK_entidade_caixa = ? and estadodocaixa = 2";
		try {
			con = Conexao.getConexao();
			PreparedStatement ps = con.prepareStatement(sql);
			ps.setInt(1, codUser);
			ResultSet rs = ps.executeQuery();
			if (rs.next()) {
				ultimoId = rs.getInt("id_aberturadecaixa");
			} else {
				int estadoId = salvaTrocoParaCaixa(c);
				c.setId_estado_docaixa(estadoId);
				ultimoId = abrirCaixa(c);
			}
			ps.close();
			con.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return ultimoId;
	}

	private int salvaTrocoParaCaixa(Caixa c) {
		int ultimoId = 0;
		String sql = "Insert into tblestadodocaixa (valor_de_troco,estadodocaixa,data_deatribuicao_troco,hora_deatribuicao_troco,FK_entidade_caixa,FK_profissional) value (?,?,?,?,?,?)";
		try {
			con = Conexao.getConexao();
			PreparedStatement ps = con.prepareStatement(sql);
			ps.setDouble(1, c.getValor_dtroco());
			ps.setInt(2, c.getEstado_docaixa());
			ps.setDate(3, ft.data_registo());
			ps.setString(4, ft.getHoraAtual());
			ps.setInt(5, c.getFk_entidade_caixa());
			ps.setInt(6, c.getFK_entidade());
			ps.execute();
			ResultSet rs = ps.executeQuery("SELECT LAST_INSERT_ID()");
			if (rs.next()) {
				ultimoId = rs.getInt(1);
			}
			ps.close();
			con.close();
			System.out.println("Salvo o valor do Troco - Caixa");
		} catch (Exception e) {
			e.printStackTrace();
		}
		return ultimoId;
	}

	private int abrirCaixa(Caixa c) {
		int ultimoId = 0;
		String sql = "Insert into tblaberturadecaixa (montante_dabertura,maquina_dabertuara,data_dabertura,hora_dabertura,descr_abertura_decaixa,FK_funcionario,FK_estado_docaixa) value (?,?,?,?,?,?,?)";
		try {
			con = Conexao.getConexao();
			PreparedStatement ps = con.prepareStatement(sql);
			ps.setDouble(1, c.getMontante_dabertura());
			ps.setString(2, c.getMaquina_dabertuara());
			ps.setDate(3, new Formatando().data_registo());
			ps.setString(4, new Formatando().getHoraAtual());
			ps.setString(5, c.getDescr_abertura_decaixa());
			ps.setInt(6, c.getFk_entidade_caixa());
			ps.setInt(7, c.getId_estado_docaixa());
			ps.execute();

			ResultSet rs = ps.executeQuery("SELECT LAST_INSERT_ID()");
			if (rs.next()) {
				ultimoId = rs.getInt(1);
			}

			sql = "Update tblestadodocaixa set estadodocaixa = 2 where id_estado_docaixa = ? and FK_entidade_caixa = ?";
			ps = con.prepareStatement(sql);
			ps.setInt(1, c.getId_estado_docaixa());
			ps.setInt(2, c.getFk_entidade_caixa());
			ps.execute();
			ps.close();
			System.out.println("Aberto o Caixa : " + c.getMaquina_dabertuara());
		} catch (SQLException e) {
			e.printStackTrace();
		}

		return ultimoId;

	}

}
