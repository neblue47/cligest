package ao.co.cligest.negocio;

import java.net.InetAddress;
import java.net.UnknownHostException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import ao.co.cligest.contracts.IServicosObrigatorios;
import ao.co.cligest.entidades.Exames;
import ao.co.cligest.entidades.Produtos;
import ao.co.cligest.entidades.Servico;
import ao.co.cligest.entidades.ServicosObrigatorio;

public class ServicosObrigatorioDAO implements IServicosObrigatorios{

	private Connection con;
	
	@Override
	public List<Servico> buscarServicosConsultaNObrigatorio() {
		 
		List<Servico> lista = new ArrayList<Servico>();
		String sql = "SELECT * from vwservicosnaoobrigatorioconsulta limit 50";
		try {
			con = Conexao.getConexao();
			PreparedStatement ps = con.prepareStatement(sql);
			ResultSet rs = ps.executeQuery();
			while (rs.next()) {
				Servico ser = new Servico();
				ser.setId_servico(rs.getInt("id_servico"));
				ser.setServico(rs.getString("servico"));
				lista.add(ser);
			}
			ps.close();
		} catch (Exception e) {
			e.printStackTrace();
		}

		return lista;
	}
	
	@Override
	public List<Servico> buscarServicosConsultaNObrigatorio(String nome) {
		 
		List<Servico> lista = new ArrayList<Servico>();
		String sql = "SELECT * from vwservicosnaoobrigatorioconsulta where servico LIKE ?  ";
		try {
			con = Conexao.getConexao();
			PreparedStatement ps = con.prepareStatement(sql);
			ps.setString(1, "%"+nome+"%");
			ResultSet rs = ps.executeQuery();
			while (rs.next()) {
				Servico ser = new Servico();
				ser.setId_servico(rs.getInt("id_servico"));
				ser.setServico(rs.getString("servico"));
			}
			ps.close();
		} catch (Exception e) {
			e.printStackTrace();
		}

		return lista;
	}

	@Override
	public List<Servico> buscarServicosConsultaNObrigatorio(int codEsp) {
		List<Servico> lista = new ArrayList<Servico>();
		String sql = "SELECT * from vwservicosnaoobrigatorioconsulta where fk_especialidade = ? ";
		try {
			con = Conexao.getConexao();
			PreparedStatement ps = con.prepareStatement(sql);
			ps.setInt(1, codEsp);
			ResultSet rs = ps.executeQuery();
			while (rs.next()) {
				Servico ser = new Servico();
				ser.setId_servico(rs.getInt("id_servico"));
				ser.setServico(rs.getString("servico"));
				lista.add(ser);
				System.out.println(lista.size());
			}
			ps.close();
		} catch (Exception e) {
			e.printStackTrace();
		}

		return lista;
	}

	@Override
	public List<Servico> buscarServicosConsultaObrigatorio() {
		List<Servico> lista = new ArrayList<Servico>();
		String sql = "SELECT * from vwservicosobrigatorioconsulta  order by data_cadastro desc ";
		try {
			con = Conexao.getConexao();
			PreparedStatement ps = con.prepareStatement(sql);
			 
			ResultSet rs = ps.executeQuery();
			while (rs.next()) {
				Servico ser = new Servico();
				ser.setId_servico(rs.getInt("fk_servico_c"));
				ser.setServico(rs.getString("servico"));
				ser.setId(rs.getInt("id_obrigatorio_c"));
				lista.add(ser);
				 
			}
			ps.close();
		} catch (Exception e) {
			e.printStackTrace();
		}

		return lista;
	}

	@Override
	public List<Servico> buscarServicosConsultaObrigatorio(String nomServico) {
		List<Servico> lista = new ArrayList<Servico>();
		String sql = "SELECT * from vwservicosobrigatorioconsulta where servico LIKE ? order by data_cadastro desc ";
		try {
			con = Conexao.getConexao();
			PreparedStatement ps = con.prepareStatement(sql);
			ps.setString(1, "%"+nomServico+"%");
			 
			ResultSet rs = ps.executeQuery();
			while (rs.next()) {
				Servico ser = new Servico();
				ser.setId_servico(rs.getInt("fk_servico_c"));
				ser.setServico(rs.getString("servico"));
				ser.setId(rs.getInt("id_obrigatorio_c"));
				lista.add(ser);
				 
			}
			ps.close();
		} catch (Exception e) {
			e.printStackTrace();
		}

		return lista;
	}

	@Override
	public void salvarServicosConsultaObrigatorio(Servico s) {
		String sql = "INSERT INTO tblobrigatorioconsultas (fk_servico_obrigatorio,fk_servico_c) VALUE (?,?)";
		try {
			con = Conexao.getConexao();
			PreparedStatement ps = con.prepareStatement(sql);
			ps.setInt(1, s.getFK_servico_obrigatorio());
			ps.setInt(2, s.getId_servico());
			ps.execute();
			ps.close();
			con.close();
			
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	@Override
	public void excluirServicoConsultaSalvoObrigatorio(int cod) {
		
		String sql = "DELETE from tblobrigatorioconsultas WHERE id_obrigatorio_c = ?";
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

	@Override
	public int novoServicoObrigatorio(ServicosObrigatorio s) {
		String sql = "INSERT INTO tblservicosobrigatorio (maquina_cadastro,fk_funcionario) VALUE (?,?)";
		try {
			con = Conexao.getConexao();
			PreparedStatement ps = con.prepareStatement(sql);
			ps.setString(1, nomeMaquina());
			ps.setInt(2, s.getFk_funcionario());
			ps.execute();
			
			ResultSet rs = ps.executeQuery("SELECT LAST_INSERT_ID()");
			 if(rs.next())
				return rs.getInt(1); 
		ps.close();
		con.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return 0;
	}
	
	private String nomeMaquina() throws UnknownHostException{
		return InetAddress.getLocalHost().getHostName();
	}

	@Override
	public List<Exames> buscarServicosExamesNObrigatorio(int codEsp) {
		List <Exames> lista = new ArrayList<Exames>();
		String sql = "SELECT * FROM tblservicosdeexamesclinicos where FK_grupo_analise_clinica=  ? and id_servicodeanalise_clinica not in (select fk_servico_exa from tblobrigatorioexames)";
		try {
			 con = Conexao.getConexao(); 
			PreparedStatement preparador = con.prepareStatement(sql);
			 preparador.setInt(1, codEsp);
			 ResultSet rs = preparador.executeQuery();
			 while(rs.next())
			 {
				 Exames bc = new Exames();
				 bc.setId_servicodeanalise_clinica(rs.getInt("id_servicodeanalise_clinica"));
				 bc.setAnalise_clinica(rs.getString("analise_clinica"));
				 bc.setPreco(rs.getDouble("preco"));
				 lista.add(bc);
			 }
			 preparador.close();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		catch (Exception e) {
			// TODO: handle exception
		}
		finally {
			try {
				con.close();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}}
		return lista;
	}

	@Override
	public List<Exames> buscarServicosExamesObrigatorio(String nomServico) {
		List<Exames> lista = new ArrayList<Exames>();
		String sql = "SELECT * from vwservicosobrigatorioexames where analise_clinica LIKE ? order by data_cadastro desc ";
		try {
			con = Conexao.getConexao();
			PreparedStatement ps = con.prepareStatement(sql);
			ps.setString(1, "%"+nomServico+"%");
			 
			ResultSet rs = ps.executeQuery();
			while (rs.next()) {
				Exames ser = new Exames();
				ser.setId_servicodeanalise_clinica(rs.getInt("fk_servico_exa"));
				ser.setAnalise_clinica(rs.getString("analise_clinica"));
				ser.setId(rs.getInt("id_obrigatorio_exa"));
				lista.add(ser);
				 
			}
			ps.close();
		} catch (Exception e) {
			e.printStackTrace();
		}

		return lista;
	}

	@Override
	public List<Exames> buscarServicosExamesObrigatorio() {
		List<Exames> lista = new ArrayList<Exames>();
		String sql = "SELECT * from vwservicosobrigatorioexames   order by data_cadastro desc ";
		try {
			con = Conexao.getConexao();
			PreparedStatement ps = con.prepareStatement(sql);
			 
			 
			ResultSet rs = ps.executeQuery();
			while (rs.next()) {
				Exames ser = new Exames();
				ser.setId_servicodeanalise_clinica(rs.getInt("fk_servico_exa"));
				ser.setAnalise_clinica(rs.getString("analise_clinica"));
				ser.setId(rs.getInt("id_obrigatorio_exa"));
				lista.add(ser);
				 
			}
			ps.close();
		} catch (Exception e) {
			e.printStackTrace();
		}

		return lista;
	}

	@Override
	public void salvarServicosExamesObrigatorio(Exames e) {
		// TODO Auto-generated method stub
		String sql = "INSERT INTO tblobrigatorioexames (fk_obrigatorio_exa,fk_servico_exa) VALUE (?,?)";
		try {
			con = Conexao.getConexao();
			PreparedStatement ps = con.prepareStatement(sql);
			ps.setInt(1, e.getFK_servico_obrigatorio());
			ps.setInt(2, e.getId_servicodeanalise_clinica());
			ps.execute();
			ps.close();
			con.close();
			
		} catch (Exception ex) {
			ex.printStackTrace();
		}
	}

	@Override
	public void excluirServicoExamesSalvoObrigatorio(int cod) {
		// TODO Auto-generated method stub
		String sql = "DELETE from tblobrigatorioexames WHERE id_obrigatorio_exa = ?";
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

	@Override
	public List<Servico> buscarServicosGeraisNObrigatorio() {
		List<Servico> lista = new ArrayList<Servico>();
		String sql = "SELECT * from vwservicosnaoobrigatoriogerais   limit 50 ";
		try {
			con = Conexao.getConexao();
			PreparedStatement ps = con.prepareStatement(sql);
		 
			ResultSet rs = ps.executeQuery();
			while (rs.next()) {
				Servico ser = new Servico();
				ser.setId_servico(rs.getInt("id_servico"));
				ser.setServico(rs.getString("servico"));
				lista.add(ser);
			}
			ps.close();
		} catch (Exception e) {
			e.printStackTrace();
		}

		return lista;
	}
	
	@Override
	public List<Servico> buscarServicosGeraisNObrigatorio(String nomSer) {
		List<Servico> lista = new ArrayList<Servico>();
		String sql = "SELECT * from vwservicosnaoobrigatoriogerais where servico LIKE ?  ";
		try {
			con = Conexao.getConexao();
			PreparedStatement ps = con.prepareStatement(sql);
			ps.setString(1, "%"+nomSer+"%");
			ResultSet rs = ps.executeQuery();
			while (rs.next()) {
				Servico ser = new Servico();
				ser.setId_servico(rs.getInt("id_servico"));
				ser.setServico(rs.getString("servico"));
				lista.add(ser);
			}
			ps.close();
		} catch (Exception e) {
			e.printStackTrace();
		}

		return lista;
	}

	@Override
	public List<Servico> buscarServicosGeraisObrigatorio() {
		List<Servico> lista = new ArrayList<Servico>();
		String sql = "SELECT * from vwservicosobrigatoriogerais limit 50 ";
		try {
			con = Conexao.getConexao();
			PreparedStatement ps = con.prepareStatement(sql);
			ResultSet rs = ps.executeQuery();
			while (rs.next()) {
				Servico ser = new Servico();
				ser.setId_servico(rs.getInt("fk_outros_serv"));
				ser.setServico(rs.getString("servico"));
				ser.setId(rs.getInt("id_obrigatorio_o_serv"));
				lista.add(ser);
			}
			ps.close();
		} catch (Exception e) {
			e.printStackTrace();
		}

		return lista;
	}

	@Override
	public List<Servico> buscarServicosGeraisObrigatorio(String nomServico) {
		List<Servico> lista = new ArrayList<Servico>();
		String sql = "SELECT * from vwservicosobrigatoriogerais where servico LIKE ?   ";
		try {
			con = Conexao.getConexao();
			PreparedStatement ps = con.prepareStatement(sql);
			ps.setString(1, "%"+nomServico+"%");
			ResultSet rs = ps.executeQuery();
			while (rs.next()) {
				Servico ser = new Servico();
				ser.setId_servico(rs.getInt("fk_outros_serv"));
				ser.setServico(rs.getString("servico"));
				ser.setId(rs.getInt("id_obrigatorio_o_serv"));
				lista.add(ser);
			}
			ps.close();
		} catch (Exception e) {
			e.printStackTrace();
		}

		return lista;
	}

	@Override
	public void salvarServicosGeraisObrigatorio(Servico s) {
		String sql = "INSERT INTO tblobrigatoriooutrosservicos (fk_obrigatorio_o_serv,fk_outros_serv) VALUE (?,?)";
		try {
			con = Conexao.getConexao();
			PreparedStatement ps = con.prepareStatement(sql);
			ps.setInt(1, s.getFK_servico_obrigatorio());
			ps.setInt(2, s.getId_servico());
			ps.execute();
			ps.close();
			con.close();
			
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	@Override
	public void excluirServicoGeraisSalvoObrigatorio(int cod) {
		String sql = "DELETE from tblobrigatoriooutrosservicos WHERE id_obrigatorio_o_serv = ?";
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

	@Override
	public List<Produtos> buscarServicosProdutoNObrigatorio() {
		
		List <Produtos> lista = new ArrayList<Produtos>();
		String sql = "SELECT * FROM vwservicosnaoobrigatorioprodutos limit 50";
		try {
			con = Conexao.getConexao();
			 PreparedStatement ps = con.prepareStatement(sql);
			 ResultSet rs = ps.executeQuery();
			 while(rs.next())
			 {
				 Produtos pd = new Produtos();
				 pd.setId_produto(rs.getInt("id_produto"));
				 pd.setNome_comercial(rs.getString("nome_comercial"));
				 pd.setValor_forma_farmaceutica(rs.getString("forma_farmaceutica"));
				 pd.setValor_dosagem(rs.getString("dosagem"));
				 pd.setPreco_dcompra(rs.getDouble("preco_dcompra"));
				 pd.setPreco_dvenda(rs.getDouble("preco_dvenda"));
				 pd.setTaxa(rs.getInt("taxa"));
				 lista.add(pd);
			 }
			 ps.close();
			 con.close();
		} 
		catch (Exception e) {
			
			e.printStackTrace();
		}
		return lista;
	}

	@Override
	public List<Produtos> buscarServicosProdutoNObrigatorio(String nome) {
		List <Produtos> lista = new ArrayList<Produtos>();
		String sql = "SELECT * FROM vwservicosnaoobrigatorioprodutos where nome_comercial LIKE ?";
		try {
			con = Conexao.getConexao();
			 PreparedStatement ps = con.prepareStatement(sql);
			 ps.setString(1, "%"+nome+"%");
			 ResultSet rs = ps.executeQuery();
			 while(rs.next())
			 {
				 Produtos pd = new Produtos();
				 pd.setId_produto(rs.getInt("id_produto"));
				 pd.setNome_comercial(rs.getString("nome_comercial"));
				 pd.setValor_forma_farmaceutica(rs.getString("forma_farmaceutica"));
				 pd.setValor_dosagem(rs.getString("dosagem"));
				 pd.setPreco_dcompra(rs.getDouble("preco_dcompra"));
				 pd.setPreco_dvenda(rs.getDouble("preco_dvenda"));
				 pd.setTaxa(rs.getInt("taxa"));
				 lista.add(pd);
			 }
			 ps.close();
			 con.close();
		} catch (SQLException e) {
			
			e.printStackTrace();
		}
		return lista;
	}

	@Override
	public List<Produtos> buscarServicosProdutoObrigatorio() {
		List<Produtos> lista = new ArrayList<Produtos>();
		String sql = "SELECT * from vwservicosobrigatorioprodutos limit 50 "; 
		try {
			con = Conexao.getConexao();
			PreparedStatement ps = con.prepareStatement(sql);
			ResultSet rs = ps.executeQuery();
			while (rs.next()) {
				Produtos ser = new Produtos();
				ser.setId_produto(rs.getInt("fk_produto"));
				ser.setNome_comercial(rs.getString("nome_comercial"));
				ser.setId(rs.getInt("id_obrigatorio_pro"));
				lista.add(ser);
			}
			ps.close();
		} catch (Exception e) {
			e.printStackTrace();
		}

		return lista;
	}

	@Override
	public List<Produtos> buscarServicosProdutoObrigatorio(String nomServico) {
		List<Produtos> lista = new ArrayList<Produtos>();
		String sql = "SELECT * from vwservicosobrigatorioprodutos where nome_comercial LIKE ? ";
		try {
			con = Conexao.getConexao();
			PreparedStatement ps = con.prepareStatement(sql);
			ps.setString(1, "%"+nomServico+"%");
			ResultSet rs = ps.executeQuery();
			while (rs.next()) {
				Produtos ser = new Produtos();
				ser.setId_produto(rs.getInt("fk_produto"));
				ser.setNome_comercial(rs.getString("nome_comercial"));
				ser.setId(rs.getInt("id_obrigatorio_pro"));
				lista.add(ser);
			}
			ps.close();
		} catch (Exception e) {
			e.printStackTrace();
		}

		return lista;
	}

	@Override
	public void salvarServicosProdutoObrigatorio(Produtos s) {
		String sql = "INSERT INTO tblobrigatorioprodutos (fk_obrigatorio_pro,fk_produto) VALUE (?,?)";
		try {
			con = Conexao.getConexao();
			PreparedStatement ps = con.prepareStatement(sql);
			ps.setInt(1, s.getFK_servico_obrigatorio());
			ps.setInt(2, s.getId_produto());
			ps.execute();
			ps.close();
			con.close();
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		
	}

	@Override
	public void excluirServicoProdutoSalvoObrigatorio(int cod) {
		// TODO Auto-generated method stub
		String sql = "DELETE from tblobrigatorioprodutos WHERE id_obrigatorio_pro = ?";
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

}
