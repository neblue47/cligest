package ao.co.cligest.dao;

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
import ao.co.cligest.entidades.Servico;
import ao.co.cligest.entidades.TesteDeQualidade;

public class FacturarTesteDAO {

	private Connection con = null;
	private Formatando ft = new Formatando();
	

	public List<Paciente> getfacturarTesteQConfirmada(){
		List <Paciente> lista = new ArrayList<Paciente>();
		String sql = "SELECT empresa, FK_requisicao, FK_empresa, count(FK_requisicao) as qtd, cast(sum(total) as decimal(10,2)) as total "
					+ "FROM vwtestedequalidade where data_confirmacao = curdate() "
					+ "and FK_requisicao not in(select FK_requisicao from tblfacturatestedequalidadecash) "
					+ "and FK_requisicao not in(select FK_requisicao from tblfacturatestedequalidademulticaixa) "
					+ "and FK_requisicao not in(select FK_requisicao from tbltesteqerequisitadocancelado) group by FK_requisicao order by hora_confirmacao desc";
		try {
			con = Conexao.getConexao();
			PreparedStatement preparador = con.prepareStatement(sql);
			 ResultSet rs = preparador.executeQuery();
			 while(rs.next())
			 {
				 Paciente pac = new Paciente();
				 pac.setNome_paciente(rs.getString("empresa"));
				 pac.setTotal_servico(rs.getInt("qtd"));
				 pac.setValor_total(rs.getDouble("total"));
				 pac.setFK_consulta_confirmada(rs.getInt("FK_requisicao"));
				 pac.setFK_empresa(rs.getInt("FK_empresa"));
				 pac.setFK_requsicao(rs.getInt("FK_requisicao"));
//				 pac.setFK_paciente(rs.getInt("FK_paciente"));
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
	
	public List<Paciente> getfacturarTesteQHistVenda(){
		List <Paciente> lista = new ArrayList<Paciente>();
		String sql = "select * from vwhistoricodevendastesteq  where data_da_facturacao = curdate() order by hora_da_facturacao desc limit 500";
		try {
			con = Conexao.getConexao();
			PreparedStatement preparador = con.prepareStatement(sql);
			 ResultSet rs = preparador.executeQuery();
			 while(rs.next())
			 {
				 Paciente pac = new Paciente();
				 pac.setNumero_fornecedor(rs.getString("numero_factura"));
				 pac.setEmpresa(rs.getString("empresa"));
				 pac.setNumero_processo(rs.getString("numero_factura"));
				 pac.setQuantidade(rs.getInt("qtd"));
				 pac.setNomeFun(rs.getString("n_c_funcionario"));
				 Calendar data = Calendar.getInstance();
				 data.setTime(rs.getDate("data_da_facturacao"));
				 pac.setData_registo(data);
				 pac.setHora_registo(rs.getString("hora_da_facturacao"));
				 pac.setTotal_s(rs.getString("total"));
				 pac.setFK_fornecedor(rs.getInt("id_factura"));
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
	
	public List<Paciente> getfacturarTesteQHistVendas(){
		List <Paciente> lista = new ArrayList<Paciente>();
		String sql = "SELECT ht.empresa, ht.Data_da_facturacao, ht.FK_fornecedor, ht.total, ht.hora_da_facturacao, ht.n_c_funcionario, ht.numero_factura, ht.id_factura, ht.qtd, (total - (ifnull(t_d,0))) AS n_t, (qtd - (ifnull(qt.n_qtd,0))) AS nova_qtd, ifnull(qt.n_qtd,0) AS n_q "
				+ "FROM vwhistoricodevendastesteq ht LEFT JOIN vwqtdexametesteqdevolvido qt ON qt.FK_factura = ht.id_factura "
				+ "WHERE ht.Data_da_facturacao BETWEEN SUBDATE(CURDATE(), INTERVAL 15 DAY) AND CURDATE() AND ht.FK_metodo_de_pagameto = 1 "
				+ "GROUP BY ht.id_factura HAVING n_q < qtd ORDER BY ht.Data_da_facturacao DESC";
		try {
			con = Conexao.getConexao();
			PreparedStatement preparador = con.prepareStatement(sql);
			 ResultSet rs = preparador.executeQuery();
			 while(rs.next())
			 {
				 Paciente pac = new Paciente();
				 pac.setNumero_fornecedor(rs.getString("numero_factura"));
				 pac.setEmpresa(rs.getString("empresa"));
				 pac.setQuantidade(rs.getInt("nova_qtd"));
				 pac.setNomeFun(rs.getString("n_c_funcionario"));
				 Calendar data = Calendar.getInstance();
				 data.setTime(rs.getDate("Data_da_facturacao"));
				 pac.setData_registo(data);
				 pac.setHora_registo(rs.getString("hora_da_facturacao"));
				 pac.setTotal_s(rs.getString("total"));
				 pac.setFK_fornecedor(rs.getInt("FK_fornecedor"));
				 pac.setId(rs.getInt("id_factura"));
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
	
public DevolucaoVenda getCliente (String cod){
		
		DevolucaoVenda cliente = new DevolucaoVenda();
		String sql = "SELECT numero_fornecedor, empresa FROM vwhistoricodevendastesteq WHERE id_factura = ?";
		try{
			con = Conexao.getConexao();
			PreparedStatement ps = con.prepareStatement(sql);
			ps.setString(1, cod);
			ResultSet rs = ps.executeQuery();
			if(rs.next()){
				cliente.setNomeCompleto(rs.getString("empresa"));
				cliente.setNumero_factura(rs.getString("numero_fornecedor"));
			}
		}catch(Exception e){
			e.printStackTrace();
		}
		return cliente;
	}
	
	public List<Paciente> getfacturarTesteQHistVendas(String cod){
		List <Paciente> lista = new ArrayList<Paciente>();
		String sql = "select * from vwrelapagamentotestq where FK_factura = ?";
		try {
			con = Conexao.getConexao();
			PreparedStatement preparador = con.prepareStatement(sql);
			preparador.setString(1, cod);
			 ResultSet rs = preparador.executeQuery();
			 while(rs.next())
			 {
				 Paciente pac = new Paciente();
				 pac.setNumero_fornecedor(rs.getString("numero_fornecedor"));
				 pac.setServico(rs.getString("serv_teste_qualidade"));
				 pac.setEmpresa(rs.getString("empresa"));
				 pac.setQuantidade(rs.getInt("qtd"));
				 pac.setNomeFun(rs.getString("n_c_funcionario"));
				 Calendar data = Calendar.getInstance();
				 data.setTime(rs.getDate("data_da_facturacao"));
				 pac.setData_registo(data);
				 pac.setHora_registo(rs.getString("hora_da_facturacao"));
				 pac.setTotal_s(rs.getString("total"));
				 pac.setPreco(rs.getDouble("preco_unitario"));
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
	
	public List<Paciente> getfacturarTesteQHistVenda(String cod){
		List <Paciente> lista = new ArrayList<Paciente>();
		String sql = "select * from vwrelapagamentotestq where FK_factura = ?";
		try {
			con = Conexao.getConexao();
			PreparedStatement preparador = con.prepareStatement(sql);
			preparador.setString(1, cod);
			 ResultSet rs = preparador.executeQuery();
			 while(rs.next())
			 {
				 Paciente pac = new Paciente();
				 pac.setNumero_fornecedor(rs.getString("numero_fornecedor"));
				 pac.setNumero_processo(rs.getString("numero_factura"));
				 pac.setServico(rs.getString("serv_teste_qualidade"));
				 pac.setEmpresa(rs.getString("empresa"));
				 pac.setQuantidade(rs.getInt("qtd"));
				 pac.setNomeFun(rs.getString("n_c_funcionario"));
				 Calendar data = Calendar.getInstance();
				 data.setTime(rs.getDate("data_da_facturacao"));
				 pac.setData_registo(data);
				 pac.setHora_registo(rs.getString("hora_da_facturacao"));
				 pac.setTotal_s(rs.getString("total"));
				 pac.setPreco(rs.getDouble("preco_unitario"));
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
	
	public List<TesteDeQualidade> getfacturarTesteQualidade(int cod){
		List <TesteDeQualidade> lista = new ArrayList<TesteDeQualidade>();
		String sql = "SELECT * FROM vwtestedequalidade where FK_requisicao = ? ";
		try {
			con = Conexao.getConexao();
			PreparedStatement preparador = con.prepareStatement(sql);
			preparador.setInt(1, cod);
			 ResultSet rs = preparador.executeQuery();
			 while(rs.next())
			 {
				 TesteDeQualidade pac = new TesteDeQualidade();
				 pac.setServ_teste_qualidade(rs.getString("serv_teste_qualidade"));
				 pac.setEmpresa(rs.getString("empresa"));
				 pac.setPreco(rs.getDouble("preco"));
				 pac.setTaxaemKZ(rs.getDouble("taxa_kz"));
				 pac.setFK_taxa(rs.getInt("taxa"));
				 pac.setTotal(rs.getDouble("total"));
				 pac.setFK_empresa(rs.getInt("FK_empresa"));
				 pac.setNumero_doservico(rs.getString("numero_doservico"));
				 pac.setTipo_empresa(rs.getString("tipo_empresa"));
				 pac.setFK_serv_test_quali(rs.getInt("FK_serv_test_quali"));
				 pac.setFK_requisicao(rs.getInt("FK_requisicao"));
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
	
	public List<TesteDeQualidade> getfacturarTesteQualidades(int cod){
		List <TesteDeQualidade> lista = new ArrayList<TesteDeQualidade>();
		String sql = "SELECT * FROM vwtestedequalidade where FK_requisicao = ?";
		try {
			con = Conexao.getConexao();
			PreparedStatement preparador = con.prepareStatement(sql);
			preparador.setInt(1, cod);
			 ResultSet rs = preparador.executeQuery();
			 while(rs.next())
			 {
				 TesteDeQualidade pac = new TesteDeQualidade();
				 pac.setServ_teste_qualidade(rs.getString("serv_teste_qualidade"));
				 pac.setEmpresa(rs.getString("empresa"));
				 pac.setPreco(rs.getDouble("preco"));
				 pac.setTaxaemKZ(rs.getDouble("taxa_kz"));
				 pac.setFK_taxa(rs.getInt("taxa"));
				 pac.setTotal(rs.getDouble("total"));
				 pac.setFK_empresa(rs.getInt("FK_empresa"));
				 pac.setNumero_doservico(rs.getString("numero_doservico"));
				 pac.setTipo_empresa(rs.getString("tipo_empresa"));
				 pac.setFK_serv_test_quali(rs.getInt("FK_serv_test_quali"));
				 pac.setFK_requisicao(rs.getInt("FK_requisicao"));
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
	
	public void inserir_faturaTesteQualidade  (Facturacao fun)
	{
	    String sql = "INSERT INTO tblfacturatestedequalidadecash (FK_factura, FK_requisicao, preco_unitario, taxa_percentual, taxa_emkz, total_pago, FK_servico_tq) VALUES (?,?,?,?,?,?,?)";
	    try {
			 //1-facturasegurodesaude
	    	con = Conexao.getConexao();
			 PreparedStatement cid = con.prepareStatement(sql);
			 cid.setInt(1, fun.getFK_factura());
			 cid.setInt(2, fun.getFK_requisicao_dexame());
			 cid.setDouble(3, fun.getPreco());
			 cid.setDouble(4, fun.getTaxa_percentual());
			 cid.setDouble(5, fun.getTaxa());
			 cid.setDouble(6, fun.getTotais());
			 cid.setInt(7, fun.getFK_servico_exame());
			 cid.execute();
			 cid.close();
			 System.out.println("Cadastrado com sucesso...tblfacturaQualidade"); 
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
	
	public void inserir_faturadeMulticaixaTeste(Facturacao fun)
	{
	    String sql = "INSERT INTO tblfacturatestedequalidademulticaixa (FK_factura,FK_requisicao,preco_unitario,taxa_percentual,taxa_emkz,total_pago,FK_servico_tq) VALUES (?,?,?,?,?,?,?)";
	    try {
			 //1-tblfacturatestedequalidademulticaixa
	    	con = Conexao.getConexao();
			 PreparedStatement cid = con.prepareStatement(sql);
			 cid.setInt(1, fun.getFK_factura());
			 cid.setInt(2, fun.getFK_requisicao_dexame());
			 cid.setDouble(3, fun.getFac_exame_mcaixa_preco());
			 cid.setDouble(4, fun.getFac_exame_mcaixa_taxa_perc());
			 cid.setDouble(5, fun.getFac_exame_mcaixa_taxa_emkz());
			 cid.setDouble(6, fun.getFac_exame_mcaixa_total());
			 cid.setInt(7, fun.getFK_servico_exame());
//			 cid.setInt(7, fun.getFK_banco());
//			 cid.setInt(8, fun.getNumero_cartao());
//			 cid.setInt(9, fun.getFK_requisicao_dexame());
			 cid.execute();
			 cid.close();
			 System.out.println("Cadastrado com sucesso...tblfacturademulticaTeste"); 
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
	
	public void addTesteQualidadeRequisitadoCancelado(Facturacao ex)
		{
			String sql = "INSERT INTO tbltesteqerequisitadocancelado (FK_requisicao,FK_funcionario,FK_empresa,data_requisicao_canc,hora_requisicao_canc,motivo_do_cancelameto) VALUES (?,?,?,?,?,?)";
			try {
				con = Conexao.getConexao();
				PreparedStatement cp = con.prepareStatement(sql);
				cp.setInt(1, ex.getFK_requsicao());
				cp.setInt(2, ex.getFK_funcionario());
				cp.setInt(3, ex.getFK_empresa());
				cp.setDate(4, new Formatando().data_registo());
				cp.setString(5, new Formatando().getHoraAtual());
				cp.setString(6, ex.getRecomendacao());
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
	
	public void eliminarFacturarTeste (int cod)
	{
    	String sql = "Delete from tbltestedequalidadeconfirmado where FK_empresa = ?";
	    try {
			 //1-facturasegurodesaude
	    	 con = Conexao.getConexao();
			 PreparedStatement cid = con.prepareStatement(sql);
			 cid.setInt(1, cod);					 
			 cid.execute();
			 cid.close();
			 System.out.println("Eliminado o teste qualidade"); 
		} catch (SQLException e) {
			e.printStackTrace();
		}
	    catch (Exception e) {
			 System.out.println(e);
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
	
	public List<Paciente> getVisualizarCancelados(){
		List <Paciente> lista = new ArrayList<Paciente>();
		String sql = "select * from vwtesteqrequisitadocancelado";
		try {
			con = Conexao.getConexao();
			PreparedStatement preparador = con.prepareStatement(sql);
			 ResultSet rs = preparador.executeQuery();
			 while(rs.next())
			 {
				 Paciente pac = new Paciente();
				 pac.setNumero_fornecedor(rs.getString("numero_fornecedor"));
				 pac.setEmpresa(rs.getString("empresa"));
				 pac.setQuantidade(rs.getInt("qtd"));
				 pac.setNomeFun(rs.getString("n_c_funcionario"));
				 Calendar data = Calendar.getInstance();
				 data.setTime(rs.getDate("data_requisicao_canc"));
				 pac.setData_registo(data);
				 pac.setHora_registo(rs.getString("hora_requisicao_canc"));
				 pac.setTotal_s(rs.getString("total"));
				 pac.setFK_requsicao(rs.getInt("FK_requisicao"));
				 pac.setId(rs.getInt("id_testeq_cancelado"));
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
	
	public List<TesteDeQualidade> getModelCancelados(String cod){
		List <TesteDeQualidade> lista = new ArrayList<TesteDeQualidade>();
		String sql = "SELECT * FROM vwvisualizartesteqcancelado where FK_requisicao = ?";
		try {
			con = Conexao.getConexao();
			PreparedStatement preparador = con.prepareStatement(sql);
			preparador.setString(1, cod);
			 ResultSet rs = preparador.executeQuery();
			 while(rs.next())
			 {
				 TesteDeQualidade pac = new TesteDeQualidade();
				 pac.setServ_teste_qualidade(rs.getString("serv_teste_qualidade"));
//				 pac.setEmpresa(rs.getString("empresa"));
				 pac.setPreco(rs.getDouble("preco"));
//				 pac.setTaxaemKZ(rs.getDouble("taxa_kz"));
				 pac.setFK_taxa(rs.getInt("taxa"));
				 pac.setTotal(rs.getDouble("subtotal"));
//				 pac.setFK_empresa(rs.getInt("FK_empresa"));
//				 pac.setNumero_doservico(rs.getString("numero_doservico"));
//				 pac.setTipo_empresa(rs.getString("tipo_empresa"));
//				 pac.setFK_serv_test_quali(rs.getInt("FK_serv_test_quali"));
				 pac.setFK_requisicao(rs.getInt("FK_requisicao"));
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
	
	public void del_cancelados(int id)
	{
		String sql = "DELETE FROM tbltesteqerequisitadocancelado  WHERE id_testeq_cancelado=?";
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
	
	public List <Exames> buscarDevolucaoTeste(String codigo)
    {
		List <Exames> lista = new ArrayList<Exames>();
			String sql = "SELECT * FROM vwservicostesteqvendidos WHERE FK_factura = ? AND FK_servico_tq NOT IN(SELECT FK_servico_exame_devolvido FROM vwqtdexametesteqdevolvido WHERE FK_factura = ?)";
			try {
				con = Conexao.getConexao();
				PreparedStatement preparador = con.prepareStatement(sql);
				preparador.setString(1, codigo);
				preparador.setString(2, codigo);
				ResultSet rs = preparador.executeQuery();
				while (rs.next())
				 {
					 Exames fun = new Exames();	
					 fun.setAnalise_clinica(rs.getString("serv_teste_qualidade"));
//				     fun.setDescricao_servico_analise(rs.getString("numero_fornecedor"));
				     fun.setPreco(rs.getDouble("preco"));
				     fun.setFK_servico_tq(rs.getInt("FK_servico_tq"));
				     fun.setFK_exame_requisitado(rs.getInt("FK_servico_tq"));
				     fun.setFk_requisicao_dexame(rs.getInt("FK_requisicao"));
				     fun.setFK_factura(rs.getInt("FK_factura"));
				     fun.setTaxa(rs.getString("taxa_emkz"));
				     fun.setTotal(rs.getInt("total_pago"));
				     fun.setTaxa_pre(rs.getInt("taxa_percentual"));
//				     fun.setCodigobarra(rs.getInt("codigobarra"));
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
	
	public DevolucaoVenda buscarDevolucaoServicoTeste(String factura, String exame)
    {
		DevolucaoVenda exa = null;
		String sql = "SELECT * FROM vwservicostesteqvendidos WHERE FK_factura = ? and FK_servico_tq = ?";
		
		try {
				con = Conexao.getConexao();
				PreparedStatement preparador = con.prepareStatement(sql);
				preparador.setString(1, factura);
				preparador.setString(2, exame);
				ResultSet rs = preparador.executeQuery();
				
				if (rs.next())
				 {
					 exa = new DevolucaoVenda();	
					 exa.setAnalise_clinica(rs.getString("serv_teste_qualidade"));
//				     exa.setDescricao_servico_analise(rs.getString("descricao_servico_analise"));
				     exa.setPreco(rs.getDouble("preco"));;
				     exa.setFK_exame_requisitado(rs.getInt("FK_servico_tq"));
				     exa.setFk_requisicao_dexame(rs.getInt("FK_requisicao"));
				     exa.setFK_factura(rs.getInt("FK_factura"));
				     exa.setTaxa_pre(rs.getDouble("taxa_emkz"));
				     System.out.println("Testando novemente: "+exa.getTaxa_emkz());
				     exa.setTotal(rs.getInt("total_pago"));
				     exa.setTaxa_pre(rs.getInt("taxa_percentual"));
//				     exa.setCodigobarra(rs.getInt("codigobarra"));
				     System.out.println("YA CHEGUEI AQUI ->" + rs.getDouble("taxa_emkz"));
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
	
	public void devolverServicoTesteQ(DevolucaoVenda exam)
	{
		String sql = "INSERT INTO tblexametesteqdevolvido (FK_devolucao_venda_tq,FK_servico_exame_devolvido,preco_ex_devolvido,taxa_pre_exam,taxa_exame_kz,valor_pago_exame,FK_requisicao_dexame) value (?,?,?,?,?,?,?)";
		try {
				con = Conexao.getConexao(); 
				PreparedStatement cp = con.prepareStatement(sql);
				cp.setInt(1, exam.getFK_devolucao_venda());
				cp.setInt(2, exam.getFK_exame_requisitado());
				cp.setDouble(3, exam.getPreco());
				cp.setInt(4, exam.getTaxa_pre());
				cp.setString(5, exam.getTaxa());
				cp.setDouble(6, exam.getTotal());
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
//	-------------------------------- HISTÓRICO DE DEVOLUÇÃO
	public List<Paciente> getTesteQualidadeHistDevolucao(){
		List <Paciente> lista = new ArrayList<Paciente>();
		String sql = "select * from vwexametesteqdevolvido";
		try {
			con = Conexao.getConexao();
			PreparedStatement preparador = con.prepareStatement(sql);
			 ResultSet rs = preparador.executeQuery();
			 while(rs.next())
			 {
				 Paciente pac = new Paciente();
				 pac.setNumero_fornecedor(rs.getString("numero_devolucao"));
				 pac.setEmpresa(rs.getString("empresa"));
				 pac.setQuantidade(rs.getInt("qtd"));
				 pac.setNomeFun(rs.getString("n_c_funcionario"));
				 Calendar data = Calendar.getInstance();
				 data.setTime(rs.getDate("data_devolucao"));
				 pac.setData_registo(data);
				 pac.setHora_registo(rs.getString("hora_devolucao"));
				 pac.setTotal_s(rs.getString("total"));
				 pac.setFK_fornecedor(rs.getInt("id_devolucao_venda"));
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
	
	public List<Paciente> getTesteQualidadeHistDevolucao(String nome){
		List <Paciente> lista = new ArrayList<Paciente>();
		String sql = "select * from vwexametesteqdevolvido where empresa LIKE ? or numero_devolucao LIKE ?";
		try {
			con = Conexao.getConexao();
			PreparedStatement preparador = con.prepareStatement(sql);
			preparador.setString(1, "%"+nome+"%");
			preparador.setString(2, "%"+nome+"%");
			 ResultSet rs = preparador.executeQuery();
			 while(rs.next())
			 {
				 Paciente pac = new Paciente();
				 pac.setNumero_fornecedor(rs.getString("numero_devolucao"));
				 pac.setEmpresa(rs.getString("empresa"));
				 pac.setQuantidade(rs.getInt("qtd"));
				 pac.setNomeFun(rs.getString("n_c_funcionario"));
				 Calendar data = Calendar.getInstance();
				 data.setTime(rs.getDate("data_devolucao"));
				 pac.setData_registo(data);
				 pac.setHora_registo(rs.getString("hora_devolucao"));
				 pac.setTotal_s(rs.getString("total"));
//				 pac.setFK_fornecedor(rs.getInt("FK_fornecedor"));
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
	
	public List <Exames> buscarVisualizarHistDevolucaoTQ(String cod)
    {
			List <Exames> lista = new ArrayList<Exames>();
			String sql = "SELECT * FROM vwvizualizartesteqdevolvido WHERE numero_devolucao =?";
			try {
				con = Conexao.getConexao();
				PreparedStatement preparador = con.prepareStatement(sql);
				preparador.setString(1, cod);
				ResultSet rs = preparador.executeQuery();
				while (rs.next())
				 {
					 Exames fun = new Exames();
					 fun.setAnalise_clinica(rs.getString("serv_teste_qualidade"));
				     fun.setPreco(rs.getDouble("preco"));
				     fun.setTaxa(rs.getString("taxa"));
				     fun.setSubtotal(rs.getDouble("subtotal"));
				     fun.setNumero_do_exame(rs.getString("numero_devolucao"));
//				     fun.setFK_exame(rs.getInt("FK_devolucao_venda"));
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
