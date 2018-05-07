package ao.co.cligest.negocio;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

import ao.co.cligest.entidades.DevolucaoVenda;
import ao.co.cligest.entidades.Exames;
import ao.co.cligest.entidades.Facturacao;
import ao.co.cligest.entidades.Paciente;
import ao.co.cligest.entidades.Produtos;
import ao.co.cligest.entidades.Tratamento;

public class DevolucaoDAO {

	private Connection con;
	Formatando ft = new Formatando();
	
	public DevolucaoVenda getCliente (String cod){
		
		DevolucaoVenda cliente = new DevolucaoVenda();
		String sql = "SELECT NomeCompleto, numero_factura FROM vwhistoricodevenda where Fk_factura = ?";
		try{
			con = Conexao.getConexao();
			PreparedStatement ps = con.prepareStatement(sql);
			ps.setString(1, cod);
			ResultSet rs = ps.executeQuery();
			if(rs.next()){
				cliente.setNomeCompleto(rs.getString("NomeCompleto"));
				cliente.setNumero_factura(rs.getString("numero_factura"));
			}
		}catch(Exception e){
			e.printStackTrace();
		}
		return cliente;
	}
	public List <Paciente> buscarDevolucao()
    {
			List <Paciente> lista = new ArrayList<Paciente>();
			String sql = "SELECT hve.NomeCompleto, hve.Data_da_facturacao, hve.hora_da_facturacao, hve.n_c_funcionario, hve.numero_factura, hve.FK_factura, hve.FK_paciente, hve.qtd,(total - (ifnull(t_d,0))) AS n_t, (qtd - (ifnull(ed.n_qtd,0))) AS nova_qtd, ifnull(ed.n_qtd,0) AS n_q "
					+ "FROM vwhistoricodevendasexames hve LEFT JOIN vwqtdexamedevolvido ed ON ed.fk_factura = hve.fk_factura "
					+ "WHERE hve.data_da_facturacao BETWEEN SUBDATE(CURDATE(), INTERVAL 15 DAY) AND CURDATE() AND hve.FK_metodo_de_pagameto = 1 "
					+ "GROUP BY hve.fk_factura HAVING n_q < qtd ORDER BY hve.Data_da_facturacao DESC";
			try {
				con = Conexao.getConexao();
				PreparedStatement preparador = con.prepareStatement(sql);
				ResultSet rs = preparador.executeQuery();
				while (rs.next())
				 {
					 Paciente fun = new Paciente();
					 fun.setNomeCompleto(rs.getString("NomeCompleto"));
					 Calendar data = Calendar.getInstance();
				     fun.setData_registo(data);
				     fun.setData(rs.getDate("Data_da_facturacao"));
				     fun.setHora_registo(rs.getString("hora_da_facturacao"));
				     fun.setNomeFun(rs.getString("n_c_funcionario"));
				     fun.setQuantidade(rs.getInt("nova_qtd"));
				     fun.setTotal_s(rs.getString("n_t"));
				     fun.setNumero_ordem(rs.getString("numero_factura"));
				     fun.setId(rs.getInt("FK_factura"));
				     fun.setFK_paciente(rs.getInt("FK_paciente"));
					 lista.add(fun);
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
	
	
	public List <Exames> buscarDevolucaoServico(String codigo)
    {
		List <Exames> lista = new ArrayList<Exames>();
			String sql = "SELECT * FROM vwservicosexamesvendidos WHERE fk_factura = ? AND FK_exame_requisitado NOT IN(SELECT FK_servico_exame_devolvido FROM vwqtdexamedevolvido WHERE fk_factura = ?)";
			try {
				con = Conexao.getConexao();
				PreparedStatement preparador = con.prepareStatement(sql);
				preparador.setString(1, codigo);
				preparador.setString(2, codigo);
				ResultSet rs = preparador.executeQuery();
				while (rs.next())
				 {
					 Exames fun = new Exames();	
					 fun.setAnalise_clinica(rs.getString("analise_clinica"));
				     fun.setDescricao_servico_analise(rs.getString("descricao_servico_analise"));
				     fun.setPreco(rs.getDouble("preco"));
				     fun.setFK_exame_requisitado(rs.getInt("FK_exame_requisitado"));
				     fun.setFk_requisicao_dexame(rs.getInt("fk_requisicao_dexame"));
				     fun.setFK_factura(rs.getInt("FK_factura"));
				     fun.setTaxa_emkz(rs.getDouble("taxa"));
				     fun.setTotal(rs.getInt("total_pago"));
				     fun.setTaxa_pre(rs.getInt("taxa_porcento"));
				     fun.setCodigobarra(rs.getInt("codigobarra"));
					 lista.add(fun);
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
	
	public DevolucaoVenda buscarDevolucaoServico(String factura, String exame)
    {
		DevolucaoVenda exa = null;
		String sql = "SELECT * FROM vwservicosexamesvendidos WHERE FK_factura = ? and FK_exame_requisitado = ?";
		
		try {
				con = Conexao.getConexao();
				PreparedStatement preparador = con.prepareStatement(sql);
				preparador.setString(1, factura);
				preparador.setString(2, exame);
				ResultSet rs = preparador.executeQuery();
				
				if (rs.next())
				 {
					 exa = new DevolucaoVenda();	
					 exa.setAnalise_clinica(rs.getString("analise_clinica"));
				     exa.setDescricao_servico_analise(rs.getString("descricao_servico_analise"));
				     exa.setPreco(rs.getDouble("preco"));;
				     exa.setFK_exame_requisitado(rs.getInt("FK_exame_requisitado"));
				     exa.setFk_requisicao_dexame(rs.getInt("fk_requisicao_dexame"));
				     exa.setFK_factura(rs.getInt("FK_factura"));
				     exa.setTaxa_emkz(rs.getDouble("taxa"));
				     exa.setTotal(rs.getInt("total_pago"));
				     exa.setTaxa_pre(rs.getInt("taxa_porcento"));
				     exa.setCodigobarra(rs.getInt("codigobarra"));
				     System.out.println("YA CHEGUEI AQUI ->" + rs.getDouble("taxa"));
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
			return exa;
		}
	public DevolucaoVenda novo()
	{
		DevolucaoVenda f = new DevolucaoVenda();
		int fun = 0;
		String sql = "SELECT MAX(id_devolucao_venda)num FROM tbldevolucaovenda";
		try {
			con = Conexao.getConexao();
			PreparedStatement preparador = con.prepareStatement(sql);
			ResultSet rs = preparador.executeQuery();
			if(rs.last())
				fun = rs.getInt("num");
			    fun++;
			
			f.setNumero_devolucao("ANG0DV"+fun);
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
			return f;
	}
	
	public void novoCreditoPaciente(Paciente p){
		String sql = "Insert into tblcredito (fk_paciente,credito,data_docredito,hora_docredito) value (?,?,?,?)";
		try {
			con = Conexao.getConexao();
			PreparedStatement ps = con.prepareStatement(sql);
			ps.setInt(1, p.getFK_paciente());
			ps.setDouble(2, p.getValor_total());
			ps.setDate(3, new Formatando().data_registo());
			ps.setString(4, new Formatando().getHoraAtual());
			ps.execute();
			con.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	public void atualizaCreditoPaciente(Paciente p){
		String sql = "Update tblcredito set credito = ?, data_docredito = ?,hora_docredito = ? where fk_paciente = ?";
		try {
			con = Conexao.getConexao();
			PreparedStatement ps = con.prepareStatement(sql);
			ps.setDouble(1, p.getValor_total());
			ps.setDate(2, new Formatando().data_registo());
			ps.setString(3, new Formatando().getHoraAtual());
			ps.setInt(4, p.getFK_paciente());
			ps.execute();
			con.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	public boolean xkCredito(int codp){
		String sql = "Select * from tblcredito where fk_paciente = ?";
		try {
			con = Conexao.getConexao();
			PreparedStatement ps = con.prepareStatement(sql);
			ps.setInt(1, codp);
			ResultSet rs = ps.executeQuery();
			if(rs.next())
				return true;
		} catch (Exception e) {
			// TODO: handle exception
		} 
		return false;
	}
	public DevolucaoVenda novoInt()
	{
		DevolucaoVenda f = new DevolucaoVenda();
		int fun = 0;
		String sql = "SELECT (id_devolucao_venda)num FROM tbldevolucaovenda";
		try {
			con = Conexao.getConexao();
			PreparedStatement preparador = con.prepareStatement(sql);
			ResultSet rs = preparador.executeQuery();
			if(rs.last())
				fun = rs.getInt("num");
//			    fun++;
			
			f.setId_devolucao_venda(fun);
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
			return f;
	}
	public void devolverServicoExame(DevolucaoVenda exam)
	{
		String sql = "INSERT INTO tblexamedevolvido (FK_devolucao_venda,FK_servico_exame_devolvido, preco_ex_devolvido, taxa_pre_exam, taxa_exame_kz, valor_pago_exame, FK_requisicao_dexame) value (?,?,?,?,?,?,?)";
		try {
				con = Conexao.getConexao(); 
				PreparedStatement cp = con.prepareStatement(sql);
				cp.setInt(1, exam.getFK_devolucao_venda());
					System.out.println("Devolucao venda-> "+exam.getFK_devolucao_venda());
				cp.setInt(2, exam.getFK_exame_requisitado());
					System.out.println("Serviço exame dev: "+exam.getFK_exame_requisitado());
				cp.setDouble(3, exam.getPreco());
					System.out.println("Preço devolvido "+exam.getPreco());
				cp.setInt(4, exam.getTaxa_pre());
					System.out.println("Taxa pre "+exam.getTaxa_pre());
				cp.setString(5, exam.getTaxa());
					System.out.println("Taxa "+exam.getTaxa());
				cp.setDouble(6, exam.getTotal());
					System.out.println("Total "+exam.getTotal());
				cp.setInt(7, exam.getFk_requisicao_dexame());
				cp.execute();
				cp.close();
				con.close();	 
		} catch (SQLException e) {
			e.printStackTrace();
		}
		catch (Exception e) {
			e.printStackTrace();
		}

	  }
	
	public void devolverTratamento(Tratamento trat){
		String sql = "INSERT INTO tbltratamentodevolvido (FK_devolucao_venda,FK_trat_devolvido, preco_trat_devolvido, quant_devolvida, taxa_trat_emkz, valor_devolvido_trat) value (?,?,?,?,?,?)";
		try {
				con = Conexao.getConexao(); 
				PreparedStatement cp = con.prepareStatement(sql);
				cp.setInt(1, trat.getFK_devolucao_venda());
				cp.setInt(2, trat.getFK_tratamento());
				cp.setDouble(3, trat.getPreco_tratamento());
				cp.setInt(4, trat.getQuantidade());
				cp.setDouble(5, trat.getTaxa_emkz());
				cp.setDouble(6, trat.getTotal_pago_tratamento());			
				cp.execute();
				cp.close();
				con.close();	 
		} catch (SQLException e) {
			e.printStackTrace();
		}
		catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	public void devolverTratamentoAtual(Tratamento trat){
		String sql = "Update tbltratamentodevolvido set quant_devolvida = ?, valor_devolvido_trat = ?, taxa_trat_emkz = ? where FK_devolucao_venda = ? and FK_trat_devolvido = ?";
		try {
				con = Conexao.getConexao(); 
				PreparedStatement cp = con.prepareStatement(sql);
				cp.setInt(1, trat.getQuantidade());
				cp.setDouble(2, trat.getTotal_pago_tratamento());	
				cp.setDouble(3, trat.getTaxa_emkz());
				cp.setInt(4, trat.getFK_devolucao_venda());
				cp.setInt(5, trat.getFK_tratamento());
				cp.execute();
				cp.close();
				con.close();	 
		} catch (SQLException e) {
			e.printStackTrace();
		}
		catch (Exception e) {
			e.printStackTrace();
		}
	}
	public int devolucaoVenda(DevolucaoVenda dv)
	{
		int num = 0;
		String sql = "INSERT INTO tbldevolucaovenda (numero_devolucao,FK_factura,FK_funcionario,data_devolucao,hora_devolucao,FK_paciente,FK_empresa,razaodadevolucao,total_devolvido,tipo_servico) VALUES (?,?,?,?,?,?,?,?,?,?)";
		System.out.println("Passando por aqui. cussunga");
		try {
			con = Conexao.getConexao();
			PreparedStatement cp = con.prepareStatement(sql);
			cp.setString(1, dv.getNumero_devolucao());
			cp.setInt(2, dv.getFK_factura());
			cp.setInt(3, dv.getFK_funcionario());
			cp.setDate(4, ft.data_registo());
			cp.setString(5, ft.getHoraAtual());
			cp.setInt(6, dv.getFK_paciente());
			cp.setInt(7, dv.getFK_empresa());
			cp.setString(8, dv.getRazaodadevolucao());
			cp.setDouble(9, dv.getTotal_devolvido());
			cp.setString(10, dv.getTipo_servico());
			cp.execute();
			sql = "update tblprodutoemestoque set quantidade_produto = ?  where FK_id_produto = ?"; // and numero_dolote_sere = ?
			cp = con.prepareStatement(sql);
			cp.setInt(1, dv.getQuantidade());
			cp.setInt(2, dv.getFk_produto());
//			cp.setString(3, dv.getNumero_lote_sere());
			cp.execute();
			ResultSet rs = cp.executeQuery("SELECT LAST_INSERT_ID()");
			if(rs.next()){
				 num = rs.getInt(1);
			}
			cp.close(); 
			System.out.println("Dovolução 5000 mil");
		} catch (SQLException e) {
			// TODO Auto-generated catch block
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
		return num;
	}
	public DevolucaoVenda getDevolucaoAnterior(int dv)
	{
		int num = 0;
		String sql = "select * from tbldevolucaovenda where FK_factura = ?";
		DevolucaoVenda dvs = new DevolucaoVenda();
		try {
			con = Conexao.getConexao();
			PreparedStatement cp = con.prepareStatement(sql);
			cp.setInt(1, dv);
			ResultSet rs = cp.executeQuery();
			if(rs.next()){
				 num = rs.getInt("id_devolucao_venda");
				 dvs.setId_devolucao_venda(rs.getInt("id_devolucao_venda"));
				 dvs.setTotal_devolvido(rs.getDouble("total_devolvido"));
				  
			}
			cp.close(); 
		} catch (SQLException e) {
			// TODO Auto-generated catch block
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
		return dvs;
	}
	
	public void devolucaoVendaAtual(DevolucaoVenda trat){
		String sql = "Update tbldevolucaovenda set total_devolvido = ?, data_devolucao = ?, hora_devolucao = ?  where FK_factura = ? ";
		try {
				con = Conexao.getConexao(); 
				PreparedStatement cp = con.prepareStatement(sql);
				cp.setDouble(1, trat.getTotal_devolvido());	
				cp.setDate(2, ft.data_registo());
				cp.setString(3, ft.getHoraAtual());
				cp.setInt(4, trat.getFK_factura());
		
				cp.execute();
				cp.close();
				con.close();	 
		} catch (SQLException e) {
			e.printStackTrace();
		}
		catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	public void devolucaoConsultaVenda(DevolucaoVenda dv)
	{
//		int num = 0;
		String sql = "INSERT INTO tbldevolucaoconsulta (FK_numero_devolucao,FK_factura,FK_paciente,FK_funcionario,FK_servico_devolvido,preco_devolvido,taxa_cons,taxa_kz_cons,data_devolucao_cons,hora_devolucao_cons,razaodadevolucao_cons,total_devolvido_cons) VALUES (?,?,?,?,?,?,?,?,?,?,?,?)";
		try {
			con = Conexao.getConexao();
			PreparedStatement cp = con.prepareStatement(sql);
			cp.setInt(1, dv.getFK_numero_do_servico_de_exame());
			cp.setInt(2, dv.getFK_factura());
			cp.setInt(3, dv.getFK_paciente());
			cp.setInt(4, dv.getFK_funcionario());
			cp.setInt(5, dv.getFK_exame());
			cp.setDouble(6, dv.getPreco());
			cp.setDouble(7, dv.getTaxa_devolucao());
			cp.setDouble(8, dv.getTaxa_emkz());
			cp.setDate(9, ft.data_registo());
			cp.setString(10, ft.getHoraAtual());
			cp.setString(11, dv.getRazaodadevolucao());
			cp.setDouble(12, dv.getTotal_devolvido());
			cp.execute();
//			ResultSet rs = cp.executeQuery("SELECT LAST_INSERT_ID()");
//			if(rs.next()){
//				 num = rs.getInt(1);
//			}
			cp.close(); 
			System.out.println("Passou aki");
		} catch (SQLException e) {
			// TODO Auto-generated catch block
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
//		return num;
	}
	
	// ------------------------- HISTORICO DE DEVOLUÇÃI -----------------------------------------------
	public List <Paciente> buscarHistoricoDevolucao()
    {
			List <Paciente> lista = new ArrayList<Paciente>();
			String sql = "SELECT * FROM vwhistoricodedevolucaodeexame order by data_devolucao_exame desc, hora_devolucao_exame desc limit 500";
			try {
				con = Conexao.getConexao();
				PreparedStatement preparador = con.prepareStatement(sql);
				ResultSet rs = preparador.executeQuery();
				while (rs.next())
				 {
					 Paciente fun = new Paciente();
					 fun.setNomeCompleto(rs.getString("NomeCompleto"));
					 Calendar data = Calendar.getInstance();
					 data.setTime(rs.getDate("data_devolucao_exame"));
				     fun.setData_registo(data);
				     fun.setData(rs.getDate("data_devolucao_exame"));
				     fun.setHora_registo(rs.getString("hora_devolucao_exame"));
				     fun.setNomeFun(rs.getString("n_c_funcionario"));
				     fun.setQuantidade(rs.getInt("qtd"));
				     fun.setTotal_s(rs.getString("total"));
				     fun.setNumero_ordem(rs.getString("numero_devolucao"));
				     fun.setId(rs.getInt("id_exame_devolvido"));
				     fun.setFK_servico(rs.getInt("FK_devolucao_venda"));
					 lista.add(fun);
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
	
	public List <Paciente> buscarHistoricoDevolucao(String nome)
    {
			List <Paciente> lista = new ArrayList<Paciente>();
			String sql = "SELECT * FROM vwhistoricodedevolucaodeexame WHERE NomeCompleto LIKE ?";
			try {
				con = Conexao.getConexao();
				PreparedStatement preparador = con.prepareStatement(sql);
				preparador.setString(1, "%"+nome+"%");
				ResultSet rs = preparador.executeQuery();
				while (rs.next())
				 {
					 Paciente fun = new Paciente();
					 fun.setNomeCompleto(rs.getString("NomeCompleto"));
					 Calendar data = Calendar.getInstance();
				     fun.setData_registo(data);
				     fun.setData(rs.getDate("data_devolucao_exame"));
				     fun.setHora_registo(rs.getString("hora_devolucao_exame"));
				     fun.setNomeFun(rs.getString("n_c_funcionario"));
				     fun.setQuantidade(rs.getInt("qtd"));
				     fun.setTotal_s(rs.getString("total"));
				     fun.setNumero_ordem(rs.getString("numero_devolucao"));
				     fun.setId(rs.getInt("id_exame_devolvido"));
				     fun.setFK_servico(rs.getInt("FK_devolucao_venda"));
					 lista.add(fun);
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
	
	public List <Exames> buscarVisualizarHistDevolucao(String cod)
    {
			List <Exames> lista = new ArrayList<Exames>();
			String sql = "SELECT * FROM vwvizualizarservicodevolvido WHERE numero_devolucao =?";
			try {
				con = Conexao.getConexao();
				PreparedStatement preparador = con.prepareStatement(sql);
				preparador.setString(1, cod);
				ResultSet rs = preparador.executeQuery();
				while (rs.next())
				 {
					 Exames fun = new Exames();
					 fun.setAnalise_clinica(rs.getString("analise_clinica"));
				     fun.setPreco(rs.getDouble("preco"));
				     fun.setTaxa(rs.getString("taxa"));
				     fun.setSubtotal(rs.getDouble("subtotal"));
				     fun.setNumero_do_exame(rs.getString("numero_devolucao"));
				     fun.setFK_exame(rs.getInt("FK_devolucao_venda"));
				     Calendar data = Calendar.getInstance();
				     data.setTime(rs.getDate("data_devolucao"));
				     fun.setData_agendamento(data);
				     
					 lista.add(fun);
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
//	Tratamento
	public List <DevolucaoVenda> buscarHistDevolTratamento()
    {
			List <DevolucaoVenda> lista = new ArrayList<DevolucaoVenda>();
			String sql = "SELECT * FROM vwhistoricodedevolucaoservicogerais WHERE data = curdate() order by hora desc limit 500";
			try {
				con = Conexao.getConexao();
				PreparedStatement preparador = con.prepareStatement(sql);
				ResultSet rs = preparador.executeQuery();
				while (rs.next())
				 {
					DevolucaoVenda fun = new DevolucaoVenda();
					 fun.setId_devolucao_venda(rs.getInt("id_devolucao_venda"));
				     fun.setNumero_devolucao(rs.getString("numero_devolucao"));
				     fun.setNomeCompleto(rs.getString("nomeCompleto"));
				     Calendar data = Calendar.getInstance();
				     data.setTime(rs.getDate("data"));
				     fun.setData_devolucao_exame(data);
				     fun.setData(rs.getDate("data"));
				     fun.setHora_devolucao_exame(rs.getString("hora"));
				     fun.setQuantidade(rs.getInt("qtd"));
				     fun.setTotal(rs.getInt("total_devolvido"));
				     fun.setNome_funcionario(rs.getString("n_c_funcionario"));
					 lista.add(fun);
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
	public List <DevolucaoVenda> buscarHistDevolTratamento(String valor)
    {
			List <DevolucaoVenda> lista = new ArrayList<DevolucaoVenda>();
			String sql = "SELECT * FROM vwhistoricodedevolucaoservicogerais where numero_devolucao Like ? or nomeCompleto Like ?";
			try {
				con = Conexao.getConexao();
				PreparedStatement preparador = con.prepareStatement(sql);
				preparador.setString(1, valor);
				preparador.setString(2, valor+"%");
				ResultSet rs = preparador.executeQuery();
				while (rs.next())
				 {
					DevolucaoVenda fun = new DevolucaoVenda();
					 fun.setId_devolucao_venda(rs.getInt("id_devolucao_venda"));
				     fun.setNumero_devolucao(rs.getString("numero_devolucao"));
				     fun.setNomeCompleto(rs.getString("nomeCompleto"));
				     Calendar data = Calendar.getInstance();
				     fun.setData_devolucao_exame(data);
				     fun.setData(rs.getDate("data"));
				     fun.setHora_devolucao_exame(rs.getString("hora"));
				     fun.setQuantidade(rs.getInt("qtd"));
				     fun.setTotal(rs.getInt("total_devolvido"));
				     fun.setNome_funcionario(rs.getString("n_c_funcionario"));
					 lista.add(fun);
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
	public DevolucaoVenda buscarDevolucaoUnicaTrat(int coddv, int codp){
		String sql = "select * from tbltratamentodevolvido where FK_devolucao_venda = ? and FK_trat_devolvido = ?";
		DevolucaoVenda dv = new DevolucaoVenda();
		try {
			con = Conexao.getConexao();
			PreparedStatement ps = con.prepareStatement(sql);
			ps.setInt(1, coddv);
			ps.setInt(2, codp);
			ResultSet rs = ps.executeQuery();
			if(rs.next()){
				dv.setFK_tratamento(rs.getInt("FK_trat_devolvido"));
				dv.setQuantidade(rs.getInt("quant_devolvida"));
				dv.setTaxa_devolucao(rs.getDouble("taxa_trat_emkz"));
				dv.setTotal_devolvido(rs.getDouble("valor_devolvido_trat"));
				dv.setFK_devolucao_venda(rs.getInt("FK_devolucao_venda"));
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return dv;
	}
	
	public List <DevolucaoVenda> buscarHistDevolTratamentoModal(String cod)
    {
			List <DevolucaoVenda> lista = new ArrayList<DevolucaoVenda>();
			String sql = "select * from vwitensdafacturafacturacaogeral WHERE FK_devolucao_venda =? ";
			try {
				con = Conexao.getConexao();
				PreparedStatement preparador = con.prepareStatement(sql);
				preparador.setString(1, cod);
				ResultSet rs = preparador.executeQuery();
				while (rs.next())
				 {
					DevolucaoVenda fun = new DevolucaoVenda();
					 fun.setFK_devolucao_venda(rs.getInt("FK_devolucao_venda"));
				     fun.setNumero_devolucao(rs.getString("numero_devolucao"));
				     Calendar data = Calendar.getInstance();
				     fun.setData_devolucao_exame(data);
				     fun.setData(rs.getDate("data_devolucao"));
				     fun.setServico(rs.getString("servico"));
				     fun.setPreco(rs.getDouble("preco"));
				     fun.setTaxa_devolucao(rs.getDouble("taxa"));
				     fun.setSubtotal(rs.getDouble("subtotal"));
					 lista.add(fun);
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
	
	public void devolverProduto(Produtos dv){
		String sql = "Insert into tblprodutodevolvido (fk_funcionario,data_pro_devolvido,hora_pro_devolvido,fk_produto,preco_pro_devolvido,qtd_pro_devolvido,txa_pro_devolvido,subt_pro_devolvido,FK_devolucao_venda) value (?,?,?,?,?,?,?,?,?)";
		try {
			con = Conexao.getConexao();
			PreparedStatement ps = con.prepareStatement(sql);
			ps.setInt(1, dv.getFK_funcionario());
			ps.setDate(2, ft.data_registo());
			ps.setString(3, ft.getHoraAtual());
			ps.setInt(4, dv.getFk_produto());
			ps.setDouble(5, dv.getPreco_pro_devolvido());
			ps.setInt(6, dv.getQtd_pro_devolvido());
			ps.setDouble(7, dv.getTxa_pro_devolvido());
			ps.setDouble(8, dv.getSubt_pro_devolvido());
			ps.setInt(9, dv.getFK_devolucao_venda());
			
			ps.execute();
			ps.close();
			con.close();
			System.out.println("Cussunga testando devolverProduto");
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	public void devolverProdutoAtualiza(Produtos dv){
		String sql = "Update tblprodutodevolvido set fk_funcionario = ?,data_pro_devolvido = ?,hora_pro_devolvido = ?,preco_pro_devolvido = ?,qtd_pro_devolvido = ?,txa_pro_devolvido = ?,subt_pro_devolvido = ?  where fk_produto = ? and FK_devolucao_venda = ?";
		try {
			con = Conexao.getConexao();
			PreparedStatement ps = con.prepareStatement(sql);
			ps.setInt(1, dv.getFK_funcionario());
			ps.setDate(2, ft.data_registo());
			ps.setString(3, ft.getHoraAtual());
			ps.setDouble(4, dv.getPreco_pro_devolvido());
			ps.setInt(5, dv.getQtd_pro_devolvido());
			ps.setDouble(6, dv.getTxa_pro_devolvido());
			ps.setDouble(7, dv.getSubt_pro_devolvido());
			ps.setInt(8, dv.getFk_produto());
			ps.setInt(9, dv.getFK_devolucao_venda());
			
			ps.execute();
			ps.close();
			con.close();
			System.out.println("Cussunga passando por aki devolverProdutoAtualiza");
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	public DevolucaoVenda buscarDevolucaoUnicaProd(int coddv, int codp){
		String sql = "select * from tblprodutodevolvido where FK_devolucao_venda = ? and fk_produto = ?";
		DevolucaoVenda dv = new DevolucaoVenda();
		try {
			con = Conexao.getConexao();
			PreparedStatement ps = con.prepareStatement(sql);
			ps.setInt(1, coddv);
			ps.setInt(2, codp);
			ResultSet rs = ps.executeQuery();
			if(rs.next()){
				dv.setFk_produto(rs.getInt("fk_produto"));
				dv.setQtd_pro_devolvido(rs.getInt("qtd_pro_devolvido"));
				dv.setTxa_pro_devolvido(rs.getDouble("txa_pro_devolvido"));
				dv.setSubt_pro_devolvido(rs.getDouble("subt_pro_devolvido"));
				dv.setFK_devolucao_venda(rs.getInt("FK_devolucao_venda"));
			}
		} catch (Exception e) {
			// TODO: handle exception
		}
		return dv;
	}
	
	public List<DevolucaoVenda> buscarHistoricoDevolucaoProd(){
		String sql = "Select * from vwhistoricodevolucaoproduto order by data_pro_devolvido desc Limit 50";
		List<DevolucaoVenda> lista = new ArrayList<DevolucaoVenda>();
		try {
			con = Conexao.getConexao();
			PreparedStatement ps = con.prepareStatement(sql);
			ResultSet rs = ps.executeQuery();
			while(rs.next()){
				DevolucaoVenda dv = new DevolucaoVenda();
				dv.setId_devolucao_venda(rs.getInt("id_devolucao_venda"));
				dv.setNumero_devolucao(rs.getString("numero_devolucao"));
				dv.setNome_funcionario(rs.getString("n_c_funcionario"));
				dv.setNomeCompleto(rs.getString("NomeCompleto"));
				dv.setQtd_pro_devolvido(rs.getInt("quantidade"));
				dv.setSubt_pro_devolvido(rs.getDouble("total_devolvido"));
				dv.setHora_pro_devolvido(rs.getString("hora_pro_devolvido"));
				Calendar data = Calendar.getInstance();
				data.setTime(rs.getDate("data_pro_devolvido"));
				lista.add(dv);
				dv.setData_pro_devolvido(data);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return lista;
	}
	
	public List<DevolucaoVenda> buscarHistoricoDevolucaoProd(String aux){
		String sql = "Select * from vwhistoricodevolucaoproduto where numero_devolucao = ? or NomeCompleto Like ? order by data_pro_devolvido desc Limit 50";
		List<DevolucaoVenda> lista = new ArrayList<DevolucaoVenda>();
		try {
			con = Conexao.getConexao();
			PreparedStatement ps = con.prepareStatement(sql);
			ps.setString(1, aux);
			ps.setString(2, aux+"%");
			ResultSet rs = ps.executeQuery();
			while(rs.next()){
				DevolucaoVenda dv = new DevolucaoVenda();
				dv.setId_devolucao_venda(rs.getInt("id_devolucao_venda"));
				dv.setNumero_devolucao(rs.getString("numero_devolucao"));
				dv.setNome_funcionario(rs.getString("n_c_funcionario"));
				dv.setNomeCompleto(rs.getString("NomeCompleto"));
				dv.setQtd_pro_devolvido(rs.getInt("quantidade"));
				dv.setSubt_pro_devolvido(rs.getDouble("total_devolvido"));
				dv.setHora_pro_devolvido(rs.getString("hora_pro_devolvido"));
				Calendar data = Calendar.getInstance();
				data.setTime(rs.getDate("data_pro_devolvido"));
				dv.setData_pro_devolvido(data);
				lista.add(dv);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return lista;
	}
	
	public DevolucaoVenda topModalInf(int aux){
		String sql = "Select * from vwhistoricodevolucaoproduto where id_devolucao_venda = ?";
		DevolucaoVenda dv = new DevolucaoVenda();
		try {
			con = Conexao.getConexao();
			PreparedStatement ps = con.prepareStatement(sql);
			ps.setInt(1, aux);
			ResultSet rs = ps.executeQuery();
			if(rs.next()){
				dv.setId_devolucao_venda(rs.getInt("id_devolucao_venda"));
				dv.setNumero_devolucao(rs.getString("numero_devolucao"));
				Calendar data = Calendar.getInstance();
				data.setTime(rs.getDate("data_pro_devolvido"));
				dv.setData_pro_devolvido(data);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return dv;
	}
	public List<DevolucaoVenda> buscarHistoricoDevolucaoProdModal(int aux){
		String sql = "Select * from vwhistoricodevolucaoprodutoitens where id_devolucao_venda = ? ";
		List<DevolucaoVenda> lista = new ArrayList<DevolucaoVenda>();
		try {
			con = Conexao.getConexao();
			PreparedStatement ps = con.prepareStatement(sql);
			ps.setInt(1, aux);
			
			ResultSet rs = ps.executeQuery();
			while(rs.next()){
				DevolucaoVenda dv = new DevolucaoVenda();
				dv.setFk_produto(rs.getInt("id_produto"));
				dv.setServico(rs.getString("nome_comercial"));
				dv.setQtd_pro_devolvido(rs.getInt("quantidade"));
				dv.setPreco_pro_devolvido(rs.getDouble("preco_pro_devolvido"));
				dv.setFK_devolucao_venda(rs.getInt("id_devolucao_venda"));
				dv.setId_devolucao_venda(rs.getInt("id_devolucao_venda"));
				lista.add(dv);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return lista;
	}
	
public List<Facturacao> getDevolucaoConsulta(){
		
		List<Facturacao> lista = new ArrayList<Facturacao>();

	    String sql = "SELECT * FROM vwrelpagamentoconsultas WHERE Data_da_facturacao BETWEEN SUBDATE(CURDATE(), INTERVAL 15 DAY) AND "
	    		+ "CURDATE() AND FK_metodo_de_pagameto = 1 AND id_factura NOT IN (SELECT FK_factura FROM tbldevolucaoconsulta) AND "
	    		+ "id_factura NOT IN (SELECT fk_factura FROM tblfacturadeisencaoporidade) AND "
	    		+ "id_factura NOT IN (SELECT fk_factura FROM tblfacturaconsultaisentada) ORDER BY Data_da_facturacao DESC";

	    try {
	    	 con = Conexao.getConexao();
			 PreparedStatement ps = con.prepareStatement(sql);		
			 ResultSet rs = ps.executeQuery();
			 while(rs.next()){
				 Facturacao f = new Facturacao();
				 f.setNumero_factura(rs.getString("numero_factura"));
				 f.setNumero_processo(rs.getString("NumeroProceso"));
				 f.setNome_paciente(rs.getString("NomeCompleto"));
				 f.setServico(rs.getString("servico"));
				 f.setQuantidade(rs.getInt("qtd"));
				 f.setNome(rs.getString("n_c_funcionario"));
				 f.setTotal_s(rs.getString("total"));
				 Calendar data = Calendar.getInstance();
				 data.setTime(rs.getDate("Data_da_facturacao"));
				 f.setData_registo(data);
				 f.setHora_registo(rs.getString("hora_da_facturacao"));
				 f.setFK_factura(rs.getInt("id_factura"));
				 lista.add(f);
			 }
			 ps.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	    catch (Exception e) {
	    	System.out.println(e.getMessage());
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

public List<Facturacao> getDevolucaoConsulta(String cod){
	
	List<Facturacao> lista = new ArrayList<Facturacao>();

    String sql = "Select * from vwrelpagamentoconsultas WHERE numero_factura = ?";

    try {
    	 con = Conexao.getConexao();
		 PreparedStatement ps = con.prepareStatement(sql);	
		 ps.setString(1, cod);
		 ResultSet rs = ps.executeQuery();
		 while(rs.next()){
			 Facturacao f = new Facturacao();
			 f.setNumero_factura(rs.getString("numero_factura"));
			 f.setNumero_processo(rs.getString("NumeroProceso"));
			 f.setNome_paciente(rs.getString("NomeCompleto"));
			 f.setFK_paciente(rs.getInt("FK_paciente"));
			 f.setServico(rs.getString("servico"));
			 f.setFK_consulta(rs.getInt("FK_servico"));
			 f.setPreco(rs.getDouble("preco"));
			 f.setTaxaEmkz(rs.getDouble("taxa_kz"));
			 f.setTotal_s(rs.getString("total"));
			 f.setQuantidade(rs.getInt("qtd"));
			 f.setNome(rs.getString("n_c_funcionario"));
			 f.setTotal_s(rs.getString("total"));
			 Calendar data = Calendar.getInstance();
			 data.setTime(rs.getDate("Data_da_facturacao"));
			 f.setData_registo(data);
			 f.setHora_registo(rs.getString("hora_da_facturacao"));
			 f.setFK_factura(rs.getInt("id_factura"));
			 lista.add(f);
		 }
		 ps.close();
	} catch (SQLException e) {
		e.printStackTrace();
	}
    catch (Exception e) {
    	System.out.println(e.getMessage());
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
	
	public List <Paciente> buscarDevolucaoConsulta()
    {
			List <Paciente> lista = new ArrayList<Paciente>();
			String sql = "SELECT * FROM vwhistoricodevolucaoconsulta order by data_devolucao desc, hora_devolucao desc limit 500";
			try {
				con = Conexao.getConexao();
				PreparedStatement preparador = con.prepareStatement(sql);
				ResultSet rs = preparador.executeQuery();
				while (rs.next())
				 {
					 Paciente fun = new Paciente();
					 fun.setNomeCompleto(rs.getString("NomeCompleto"));
					 Calendar data = Calendar.getInstance();
					 data.setTime(rs.getDate("data_devolucao"));
				     fun.setData_registo(data);
				     fun.setData(rs.getDate("data_devolucao"));
				     fun.setHora_registo(rs.getString("hora_devolucao"));
				     fun.setNomeFun(rs.getString("n_c_funcionario"));
//				     fun.setQuantidade(rs.getInt("quantidade"));
				     fun.setTotal_s(rs.getString("total_devolvido"));
				     fun.setNumero_ordem(rs.getString("numero_devolucao"));
				     fun.setId(rs.getInt("id_devolucao_cons"));
				     fun.setFK_paciente(rs.getInt("FK_paciente"));
				     fun.setServico(rs.getString("servico"));
					 lista.add(fun);
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
	
	public List <Paciente> buscarDevolucaoConsultaPesquisar(String pesquisar)
    {
			List <Paciente> lista = new ArrayList<Paciente>();
			String sql = "SELECT * FROM vwhistoricodevolucaoconsulta where NomeCompleto LIKE ? or numero_devolucao LIKE ?";
			try {
				con = Conexao.getConexao();
				PreparedStatement preparador = con.prepareStatement(sql);
				preparador.setString(1, "%"+pesquisar+"%");
				preparador.setString(2, "%"+pesquisar+"%");
				ResultSet rs = preparador.executeQuery();
				while (rs.next())
				 {
					 Paciente fun = new Paciente();
					 fun.setNomeCompleto(rs.getString("NomeCompleto"));
					 Calendar data = Calendar.getInstance();
					 data.setTime(rs.getDate("data_devolucao"));
				     fun.setData_registo(data);
				     fun.setData(rs.getDate("data_devolucao"));
				     fun.setHora_registo(rs.getString("hora_devolucao"));
				     fun.setNomeFun(rs.getString("n_c_funcionario"));
//				     fun.setQuantidade(rs.getInt("quantidade"));
				     fun.setTotal_s(rs.getString("total_devolvido"));
				     fun.setNumero_ordem(rs.getString("numero_devolucao"));
				     fun.setId(rs.getInt("id_devolucao_cons"));
				     fun.setFK_paciente(rs.getInt("FK_paciente"));
				     fun.setServico(rs.getString("servico"));
					 lista.add(fun);
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
	
	public List <Paciente> buscarConsultaaDevolver(String cod)
    {
			List <Paciente> lista = new ArrayList<Paciente>();
			String sql = "SELECT * FROM vwconsultaadevolver where id_factura = ?";
			try {
				con = Conexao.getConexao();
				PreparedStatement preparador = con.prepareStatement(sql);
				preparador.setString(1, cod);
				ResultSet rs = preparador.executeQuery();
				while (rs.next())
				 {
					 Paciente fun = new Paciente();
					 fun.setNomeCompleto(rs.getString("analise_clinica"));
					 fun.setPreco(rs.getDouble("preco"));
					 fun.setFK_paciente(rs.getInt("FK_paciente"));
					 fun.setFK_consulta(rs.getInt("fk_serv_consulta"));
					 fun.setTaxaEmkz(rs.getDouble("taxa_emkz"));
					 fun.setTotal_s(rs.getString("total_pago"));
					 fun.setId(rs.getInt("id_factura"));
					 lista.add(fun);
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
	
	
	public List <Paciente> buscarHistoricoDevolucaoConsulta()
    {
			List <Paciente> lista = new ArrayList<Paciente>();
			String sql = "SELECT * FROM vwhistoricodevolucaoconsulta";
			try {
				con = Conexao.getConexao();
				PreparedStatement preparador = con.prepareStatement(sql);
				ResultSet rs = preparador.executeQuery();
				while (rs.next())
				 {
					 Paciente fun = new Paciente();
					 fun.setNomeCompleto(rs.getString("NomeCompleto"));
					 Calendar data = Calendar.getInstance();
				     fun.setData_registo(data);
				     fun.setData(rs.getDate("data_devolucao"));
				     fun.setHora_registo(rs.getString("hora_devolucao"));
				     fun.setNomeFun(rs.getString("n_c_funcionario"));
//				     fun.setQuantidade(rs.getInt("quantidade"));
				     fun.setTotal_s(rs.getString("total_devolvido"));
				     fun.setNumero_ordem(rs.getString("numero_devolucao"));
				     fun.setNome_doutor(rs.getString("n_c_funcionario"));
//				     fun.setId(rs.getInt("FK_factura"));
				     fun.setFK_paciente(rs.getInt("FK_paciente"));
					 lista.add(fun);
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
	
	public List <Paciente> buscarHistoricoDevolucaoConsulta(String nome)
    {
			List <Paciente> lista = new ArrayList<Paciente>();
			String sql = "SELECT * FROM vwhistoricodevolucaoconsulta where NomeCompleto LIKE ?";
			try {
				con = Conexao.getConexao();
				PreparedStatement preparador = con.prepareStatement(sql);
				preparador.setString(1, nome+"%");
				ResultSet rs = preparador.executeQuery();
				while (rs.next())
				 {
					 Paciente fun = new Paciente();
					 fun.setNomeCompleto(rs.getString("NomeCompleto"));
					 Calendar data = Calendar.getInstance();
				     fun.setData_registo(data);
				     fun.setData(rs.getDate("data_devolucao"));
				     fun.setHora_registo(rs.getString("hora_devolucao"));
				     fun.setNomeFun(rs.getString("n_c_funcionario"));
//				     fun.setQuantidade(rs.getInt("quantidade"));
				     fun.setTotal_s(rs.getString("total_devolvido"));
				     fun.setNumero_ordem(rs.getString("numero_devolucao"));
				     fun.setNome_doutor(rs.getString("n_c_funcionario"));
//				     fun.setId(rs.getInt("FK_factura"));
				     fun.setFK_paciente(rs.getInt("FK_paciente"));
					 lista.add(fun);
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
}
