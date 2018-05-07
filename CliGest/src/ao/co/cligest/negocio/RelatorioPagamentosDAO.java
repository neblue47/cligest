
package ao.co.cligest.negocio;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

import ao.co.cligest.entidades.Exames;
import ao.co.cligest.entidades.Facturacao;
import ao.co.cligest.entidades.Paciente;
import ao.co.cligest.entidades.Produtos;
import ao.co.cligest.entidades.Tratamento;

public class RelatorioPagamentosDAO {

	private Connection con ;
	private Formatando ft = new Formatando();
	
	
	public List<Facturacao> getPacienteFaturados(){
		
		List<Facturacao> lista = new ArrayList<Facturacao>();

	    String sql = "Select * from vwrelpagamentoconsultas order by Data_da_facturacao desc, hora_da_facturacao desc limit 500";

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
				 //f.setTotal_s(rs.getString("total"));
				 Calendar data = Calendar.getInstance();
				 data.setTime(rs.getDate("Data_da_facturacao"));
				 f.setData_registo(data);
				 f.setHora_registo(rs.getString("hora_da_facturacao"));
				 f.setFK_factura(rs.getInt("id_factura"));
				 f.setTotal_factura(rs.getDouble("total"));
				 f.setMetodo_dpagamento(rs.getString("metodo_depagameto"));
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
	
public List<Facturacao> getPacienteFaturadosc(){
		
		List<Facturacao> lista = new ArrayList<Facturacao>();

	    String sql = "Select * from vwrelpagamentoconsultas WHERE Data_da_facturacao = curdate() order by hora_da_facturacao desc";

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
				 f.setMetodo_dpagamento(rs.getString("metodo_depagameto"));
				 f.setFK_Metodo_dpagamento(rs.getInt("FK_metodo_de_pagameto"));
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

public List <Paciente> buscarDevolucaoConsultaPesquisar(String pesquisar)
{
		List <Paciente> lista = new ArrayList<Paciente>();
		String sql = "SELECT * FROM vwhistoricodevendaadevolverconsulta  where NomeCompleto LIKE ? or numero_factura LIKE ?";
		try {
			con = Conexao.getConexao();
			PreparedStatement preparador = con.prepareStatement(sql);
			preparador.setString(1, pesquisar+"%");
			ResultSet rs = preparador.executeQuery();
			while (rs.next())
			 {
				 Paciente fun = new Paciente();
				 fun.setNomeCompleto(rs.getString("NomeCompleto"));
				 Calendar data = Calendar.getInstance();
			     fun.setData_registo(data);
			     fun.setData(rs.getDate("data_da_facturacao"));
			     fun.setHora_registo(rs.getString("hora_da_facturacao"));
			     fun.setNomeFun(rs.getString("n_c_funcionario"));
			     fun.setQuantidade(rs.getInt("quantidade"));
			     fun.setTotal_s(rs.getString("total_pago"));
			     fun.setNumero_ordem(rs.getString("numero_factura"));
			     fun.setId(rs.getInt("FK_factura"));
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

public List<Facturacao> getPacienteFaturados(String valor){
		
		List<Facturacao> lista = new ArrayList<Facturacao>();
	    String sql = "Select * from vwrelpagamentoconsultas where NomeCompleto Like ? or NumeroProceso Like ? ";
	    try {
	    	 con = Conexao.getConexao();
			 PreparedStatement ps = con.prepareStatement(sql);
			 ps.setString(1, "%"+valor+"%");
			 ps.setString(2, valor);
			 ResultSet rs = ps.executeQuery();
			 while(rs.next())
			 {
				 Facturacao f = new Facturacao();
				 f.setNumero_factura(rs.getString("numero_factura"));
				 f.setNumero_processo(rs.getString("NumeroProceso"));
				 f.setNome_paciente(rs.getString("NomeCompleto"));
				 f.setServico(rs.getString("servico"));
				 f.setNome(rs.getString("n_c_funcionario"));
				 Calendar data = Calendar.getInstance();
				 data.setTime(rs.getDate("Data_da_facturacao"));
				 f.setData_registo(data);
				 f.setHora_registo(rs.getString("hora_da_facturacao"));
				 f.setFK_factura(rs.getInt("id_factura"));
				 f.setTotal_factura(rs.getDouble("total"));
				 f.setMetodo_dpagamento(rs.getString("metodo_depagameto"));
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
	
public List<Facturacao> getServicosFaturados(){
		
		List<Facturacao> lista = new ArrayList<Facturacao>();
	    String sql = "select servico, preco, count(preco) as 'quantidade', sum(total) as 'total', taxa, min(Data_da_facturacao) 'data_min', max(Data_da_facturacao) 'data_max' from vwrelpagamentoconsultas where Data_da_facturacao = curdate() group by servico order by servico";
	    try {
	    	 con = Conexao.getConexao();
			 PreparedStatement ps = con.prepareStatement(sql);		
			 ResultSet rs = ps.executeQuery();
			 while(rs.next()){
				 Facturacao f = new Facturacao();
				 f.setServico(rs.getString("servico"));
				 f.setPreco(rs.getDouble("preco"));
				 f.setQuantidade(rs.getInt("quantidade"));
				 f.setTaxa(rs.getDouble("taxa"));
				 f.setTotais(rs.getDouble("total"));
				 Calendar data_inicio = Calendar.getInstance();
				 data_inicio.setTime(rs.getDate("data_min"));
				 f.setData_inicio(data_inicio);
				 Calendar data_fim = Calendar.getInstance();
				 data_fim.setTime(rs.getDate("data_max"));
				 f.setData_fim(data_fim);
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

public List<Facturacao> getFaturadosNasDatas(Date dia_in, Date dia_fim){
	
	List<Facturacao> lista = new ArrayList<Facturacao>();
	String sql = "select servico, preco, count(preco) as 'quantidade', taxa, sum(total) as 'total', min(Data_da_facturacao) 'data_min', max(Data_da_facturacao) 'data_max' from vwrelpagamentoconsultas where Data_da_facturacao BETWEEN ? and ? group by servico, preco order by servico   ";
	
    try {
    	 con = Conexao.getConexao();
		 PreparedStatement ps = con.prepareStatement(sql);	
		 ps.setDate(1, dia_in);
		 ps.setDate(2, dia_fim);
		 ResultSet rs = ps.executeQuery();
		 while(rs.next()){
			 Facturacao f = new Facturacao();
//			 f.setId_servico(rs.getInt("id_servico"));
			 f.setServico(rs.getString("servico"));
			 f.setPreco(rs.getDouble("preco"));
			 f.setQuantidade(rs.getInt("quantidade"));
			 f.setTaxa(rs.getDouble("taxa"));
			 f.setTotais(rs.getDouble("total"));
			 Calendar data_inicio = Calendar.getInstance();
			 data_inicio.setTime(rs.getDate("data_min"));
			 f.setData_inicio(data_inicio);
			 Calendar data_fim = Calendar.getInstance();
			 data_fim.setTime(rs.getDate("data_max"));
			 f.setData_fim(data_fim);
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

// FaturadosPagamentosIsentosPlanos
public List<Facturacao> FaturadosPagamentosIsentosPlanos(Date dia_in, Date dia_fim,int m){
	
	List<Facturacao> lista = new ArrayList<Facturacao>();
	String sql = "";
	if(m == 1)
	{
		sql = "select servico, preco, count(preco) as 'quantidade', taxa, sum(total) as 'total', min(Data_da_facturacao) 'data_min', max(Data_da_facturacao) 'data_max' from vwrelpagamentoconsultas where Data_da_facturacao BETWEEN ? and ? and metodo_depagameto <> 'Isenção' and  metodo_depagameto <> 'Isenção Por Idade' and  metodo_depagameto <> 'Seguro de Saude' group by servico, preco order by servico";
		System.out.println("AAAA00: "+m);
	}
	if(m == 2)
	{
		sql = "select servico, preco, count(preco) as 'quantidade', taxa, sum(total) as 'total', min(Data_da_facturacao) 'data_min', max(Data_da_facturacao) 'data_max' from vwrelpagamentoconsultas where Data_da_facturacao BETWEEN ? and ? and metodo_depagameto <> 'cash' and  metodo_depagameto <> 'Multicaixa' and  metodo_depagameto <> 'Seguro de Saude' group by servico, preco order by servico";
		System.out.println("AAAA01: "+m);
	}
	if(m == 3)
	{
		sql = "select servico, preco, count(preco) as 'quantidade', taxa, sum(total) as 'total', min(Data_da_facturacao) 'data_min', max(Data_da_facturacao) 'data_max' from vwrelpagamentoconsultas where Data_da_facturacao BETWEEN ? and ? and metodo_depagameto <> 'Isenção' and  metodo_depagameto <> 'Isenção Por Idade' and  metodo_depagameto <> 'Cash' and  metodo_depagameto <> 'Multicaixa' group by servico, preco order by servico";
		System.out.println("AAAA02: "+m);
	}
	
    try {
    	 con = Conexao.getConexao();
		 PreparedStatement ps = con.prepareStatement(sql);	
		 ps.setDate(1, dia_in);
		 ps.setDate(2, dia_fim);
		 ResultSet rs = ps.executeQuery();
		 while(rs.next()){
			 Facturacao f = new Facturacao();
			 f.setTaxa(rs.getInt("taxa"));
			 f.setServico(rs.getString("servico"));
			 f.setPreco(rs.getDouble("preco"));
			 f.setQuantidade(rs.getInt("quantidade"));
			 f.setTotais(rs.getDouble("total"));
			 Calendar data_inicio = Calendar.getInstance();
			 data_inicio.setTime(rs.getDate("data_min"));
			 f.setData_inicio(data_inicio);
			 Calendar data_fim = Calendar.getInstance();
			 data_fim.setTime(rs.getDate("data_max"));
			 f.setData_fim(data_fim);
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

// FaturadosComPagamentoIsentoPlanoServico
public List<Facturacao> FaturadosComPagamentoIsentoPlanoServico(Date dia_in, Date dia_fim,int m,String s){
	
	List<Facturacao> lista = new ArrayList<Facturacao>();
	String sql = "";
	if(m == 1)
	{
		sql = "select FK_servico as 'id_servico', FK_metodo_de_pagameto, servico, preco, count(preco) as 'quantidade', taxa, sum(total) as 'total', Data_da_facturacao, min(Data_da_facturacao) 'data_min', max(Data_da_facturacao) 'data_max', metodo_depagameto from vwrelpagamentoconsultas where Data_da_facturacao BETWEEN ? and ? and metodo_depagameto <> 'Isenção' and metodo_depagameto <> 'Isenção Por Idade' and metodo_depagameto <> 'Seguro de Saude' and FK_servico = ? group by servico, preco order by servico";
		System.out.println("Cuss00: "+m);
	}
	if(m == 2)
	{
		sql = "select FK_servico as 'id_servico', FK_metodo_de_pagameto, servico, preco, count(preco) as 'quantidade', taxa, sum(total) as 'total', Data_da_facturacao, min(Data_da_facturacao) 'data_min', max(Data_da_facturacao) 'data_max', metodo_depagameto from vwrelpagamentoconsultas where Data_da_facturacao BETWEEN ? and ? and metodo_depagameto <> 'cash' and metodo_depagameto <> 'Multicaixa' and metodo_depagameto <> 'Seguro de Saude' and FK_servico = ? group by servico, preco order by servico";
		System.out.println("Cuss01: "+m);
	}
	if(m == 3){
		sql = "SELECT s.id_servico,f.FK_metodo_de_pagameto,s.servico,s.preco,Count(s.preco) AS 'quantidade',Sum(fc.total_pago) AS 'total',f.Data_da_facturacao,  Min(f.Data_da_facturacao) data_min,  Max(f.Data_da_facturacao) data_max,  mtpg.metodo_depagameto FROM  tblfactura f,  tblmetododepagameto mtpg,  tblfacturaconsulta fc,  tblconsultaconfirmada cc,  tblservico s "
			+ "WHERE  f.id_factura = fc.FK_factura AND  fc.FK_consulta_confirmada = cc.id_consulta_confirmada AND  cc.FK_servico = s.Id_servico AND  f.FK_metodo_de_pagameto =  mtpg.id_metodo_depagameto AND  f.Data_da_facturacao BETWEEN ? AND ? AND id_servico = ? AND mtpg.metodo_depagameto <> 'Isenção' AND  mtpg.metodo_depagameto <> 'Isenção Por Idade' AND  mtpg.metodo_depagameto <> 'Seguro de Saude' "
			+ "GROUP BY  s.servico,  s.preco ORDER BY  s.servico ";
		System.out.println("Cuss00: "+m);
	}
	
    try {
    	 con = Conexao.getConexao();
		 PreparedStatement ps = con.prepareStatement(sql);	
		 ps.setDate(1, dia_in);
		 ps.setDate(2, dia_fim);
		 ps.setString(3, s);
		 ResultSet rs = ps.executeQuery();
		 while(rs.next()){
			 Facturacao f = new Facturacao();
			 f.setId_servico(rs.getInt("id_servico"));
			 f.setServico(rs.getString("servico"));
			 f.setPreco(rs.getDouble("preco"));
			 f.setQuantidade(rs.getInt("quantidade"));
			 f.setTotais(rs.getDouble("total"));
			 Calendar data_inicio = Calendar.getInstance();
			 data_inicio.setTime(rs.getDate("data_min"));
			 f.setData_inicio(data_inicio);
			 Calendar data_fim = Calendar.getInstance();
			 data_fim.setTime(rs.getDate("data_max"));
			 f.setData_fim(data_fim);
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

// FaturadosPagamentosIsentosOpcao
public List<Facturacao> FaturadosPagamentosIsentosOpcao(Date dia_in, Date dia_fim,int m,int op){
	
	List<Facturacao> lista = new ArrayList<Facturacao>();
	String sql = "";
	if(m == 1 && op == 1){
		sql = "select servico, preco, count(preco) as 'quantidade', taxa, sum(total) as 'total', min(Data_da_facturacao) 'data_min', max(Data_da_facturacao) 'data_max' from vwrelpagamentoconsultas where Data_da_facturacao BETWEEN ? and ? and metodo_depagameto<> 'Isenção' and metodo_depagameto <> 'Isenção Por Idade' and metodo_depagameto <> 'Seguro de Saude' and metodo_depagameto <> 'Multicaixa' group by servico, preco order by servico ";
		System.out.println("BBB00: "+m);
	}
	if(m == 1 && op == 2){
		sql = "select servico, preco, count(preco) as 'quantidade', taxa, sum(total) as 'total', min(Data_da_facturacao) 'data_min', max(Data_da_facturacao) 'data_max' from vwrelpagamentoconsultas where Data_da_facturacao BETWEEN ? and ? and metodo_depagameto <> 'Isenção' and metodo_depagameto <> 'Isenção Por Idade' and metodo_depagameto <> 'Seguro de Saude' and metodo_depagameto <> 'cash' group by servico, preco order by servico ";
		System.out.println("BBB01: "+m);
	}
	if(m == 2 && op == 1){
		sql = "select servico, preco, count(preco) as 'quantidade', taxa, sum(total) as 'total', min(Data_da_facturacao) 'data_min', max(Data_da_facturacao) 'data_max' from vwrelpagamentoconsultas where Data_da_facturacao BETWEEN ? and ? and metodo_depagameto <> 'Cash' and metodo_depagameto <> 'Multicaixa' and metodo_depagameto <> 'Seguro de Saude' and metodo_depagameto <> 'Isenção' group by servico, preco order by servico";
		System.out.println("BBB02: "+m);
	}
	if(m == 2 && op == 2){
		sql = "select servico, preco, count(preco) as 'quantidade', taxa, sum(total) as 'total', min(Data_da_facturacao) 'data_min', max(Data_da_facturacao) 'data_max' from vwrelpagamentoconsultas where Data_da_facturacao BETWEEN ? and ? and metodo_depagameto <> 'Cash' and metodo_depagameto <> 'Isenção Por Idade' and metodo_depagameto <> 'Seguro de Saude' and metodo_depagameto<> 'Multicaixa' group by servico, preco order by servico";
		System.out.println("BBB03: "+m);
	}
	
	
    try {
    	 con = Conexao.getConexao();
		 PreparedStatement ps = con.prepareStatement(sql);	
		 ps.setDate(1, dia_in);
		 ps.setDate(2, dia_fim);
		 ResultSet rs = ps.executeQuery();
		 while(rs.next()){
			 Facturacao f = new Facturacao();
			 f.setTaxa(rs.getDouble("taxa"));
			 f.setServico(rs.getString("servico"));
			 f.setPreco(rs.getDouble("preco"));
			 f.setQuantidade(rs.getInt("quantidade"));
			 f.setTotais(rs.getDouble("total"));
			 Calendar data_inicio = Calendar.getInstance();
			 data_inicio.setTime(rs.getDate("data_min"));
			 f.setData_inicio(data_inicio);
			 Calendar data_fim = Calendar.getInstance();
			 data_fim.setTime(rs.getDate("data_max"));
			 f.setData_fim(data_fim);
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
// FaturadosPagamentosIsentosPlanosOpcaoServico
public List<Facturacao> FaturadosPagamentosIsentosPlanosOpcaoServico(Date dia_in, Date dia_fim,int m,int op,int s){
	
	List<Facturacao> lista = new ArrayList<Facturacao>();
	String sql = "";
	if(m == 1 && op == 1){
		sql = "select servico, preco, count(preco) as 'quantidade', taxa, FK_servico, sum(total) as 'total', min(Data_da_facturacao) 'data_min', max(Data_da_facturacao) 'data_max' from vwrelpagamentoconsultas where Data_da_facturacao BETWEEN ? and ? AND FK_servico = ? and  metodo_depagameto <> 'Isenção' and metodo_depagameto <> 'Isenção Por Idade' and metodo_depagameto <> 'Seguro de Saude' and metodo_depagameto <> 'Multicaixa' group by servico, preco order by servico; ";
		System.out.println("SSSS00: "+m);
	}
	if(m == 1 && op == 2){
		sql = "select servico, preco, count(preco) as 'quantidade', taxa, FK_servico, sum(total) as 'total', min(Data_da_facturacao) 'data_min', max(Data_da_facturacao) 'data_max' from vwrelpagamentoconsultas where Data_da_facturacao BETWEEN ? and ? AND FK_servico = ? and  metodo_depagameto <> 'Isenção' and metodo_depagameto <> 'Isenção Por Idade' and metodo_depagameto <> 'Seguro de Saude' and metodo_depagameto <> 'Cash' group by servico, preco order by servico ";
		System.out.println("SSSS01: "+m);
	}
	if(m == 2 && op == 1){
		sql = "select servico, preco, count(preco) as 'quantidade', taxa, FK_servico, sum(total) as 'total', min(Data_da_facturacao) 'data_min', max(Data_da_facturacao) 'data_max' from vwrelpagamentoconsultas where Data_da_facturacao BETWEEN ? and ? and FK_servico = ? and metodo_depagameto <> 'Cash' and metodo_depagameto <> 'Isenção' and metodo_depagameto <> 'Seguro de Saude' and metodo_depagameto <> 'Multicaixa' group by servico, preco order by servico";
		System.out.println("SSSS02: "+m);
	}
	if(m == 2 && op == 2)
	{
		sql = "select servico, preco, count(preco) as 'quantidade', taxa, FK_servico, sum(total) as 'total', min(Data_da_facturacao) 'data_min', max(Data_da_facturacao) 'data_max' from vwrelpagamentoconsultas where Data_da_facturacao BETWEEN ? and ? and FK_servico = ? and metodo_depagameto <> 'Cash' and metodo_depagameto <> 'Isenção Por Idade' and metodo_depagameto <> 'Seguro de Saude' and metodo_depagameto <> 'Multicaixa' group by servico, preco order by servico";
		System.out.println("SSSS03: "+m);
	}
	
	if(m == 3){
		sql = "select servico, preco, count(preco) as 'quantidade', taxa, FK_servico, sum(total) as 'total', min(Data_da_facturacao) 'data_min', max(Data_da_facturacao) 'data_max' from vwrelpagamentoconsultas where Data_da_facturacao BETWEEN ? and ? AND FK_servico = ? and  metodo_depagameto <> 'Isenção' and metodo_depagameto <> 'Isenção Por Idade' and metodo_depagameto <> 'Multicaixa' and metodo_depagameto <> 'Cash' group by servico, preco order by servico ";
		System.out.println("SSSS04: "+m);
	}
    try {
    	 con = Conexao.getConexao();
		 PreparedStatement ps = con.prepareStatement(sql);	
		 ps.setDate(1, dia_in);
		 ps.setDate(2, dia_fim);
		 ps.setInt(3, s);
		 ResultSet rs = ps.executeQuery();
		 while(rs.next()){
			 Facturacao f = new Facturacao();
			 f.setTaxa(rs.getDouble("taxa"));
			 f.setFK_servico(rs.getInt("FK_servico"));
			 f.setServico(rs.getString("servico"));
			 f.setPreco(rs.getDouble("preco"));
			 f.setQuantidade(rs.getInt("quantidade"));
			 f.setTotais(rs.getDouble("total"));
			 Calendar data_inicio = Calendar.getInstance();
			 data_inicio.setTime(rs.getDate("data_min"));
			 f.setData_inicio(data_inicio);
			 Calendar data_fim = Calendar.getInstance();
			 data_fim.setTime(rs.getDate("data_max"));
			 f.setData_fim(data_fim);
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


//--------------------------------------------- EXAME RELATï¿½RIO
public List <Exames> buscarhistVendasRelatorio()
{
		List <Exames> lista = new ArrayList<Exames>();
		String sql = "select analise_clinica, preco, count(preco) as 'quantidade', sum(total) as 'total', taxa, min(Data_da_facturacao) 'data_min', max(Data_da_facturacao) 'data_max' from vwrelpagamentoexame where Data_da_facturacao = curdate() group by analise_clinica order by analise_clinica";
		try {
			con = Conexao.getConexao();
			PreparedStatement preparador = con.prepareStatement(sql);
			ResultSet rs = preparador.executeQuery();
			while (rs.next())
			 {
				 Exames fun = new Exames();
				 fun.setAnalise_clinica(rs.getString("analise_clinica"));
			     fun.setPreco(rs.getDouble("preco"));
			     fun.setQuantidade(rs.getInt("quantidade"));
			     fun.setSubtotal(rs.getDouble("total"));
			     Calendar data_inicio = Calendar.getInstance();
				 data_inicio.setTime(rs.getDate("data_max"));
			     fun.setData_inicio(data_inicio);
			     Calendar data_fim = Calendar.getInstance();
				 data_fim.setTime(rs.getDate("data_min"));
			     fun.setData_fim(data_fim);
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
public List <Exames> buscarhistVendasRelatorio(Date dia_in, Date dia_fim)
{
		List <Exames> lista = new ArrayList<Exames>();
		String sql = "select analise_clinica, preco, count(preco) as 'quantidade', sum(total) as 'total', taxa, min(Data_da_facturacao) 'data_min', max(Data_da_facturacao) 'data_max' from vwrelpagamentoexame where Data_da_facturacao between ? and ?  group by analise_clinica order by analise_clinica";
		try {
			con = Conexao.getConexao();
			PreparedStatement preparador = con.prepareStatement(sql);
			preparador.setDate(1, dia_in);
			preparador.setDate(2, dia_fim);
			ResultSet rs = preparador.executeQuery();
			while (rs.next())
			 {
				 Exames fun = new Exames();
				 fun.setAnalise_clinica(rs.getString("analise_clinica"));
			     fun.setPreco(rs.getDouble("preco"));
			     fun.setQuantidade(rs.getInt("quantidade"));
			     fun.setSubtotal(rs.getDouble("total"));
			     Calendar data_inicio = Calendar.getInstance();
				 data_inicio.setTime(rs.getDate("data_max"));
			     fun.setData_inicio(data_inicio);
			     Calendar data_fim = Calendar.getInstance();
				 data_fim.setTime(rs.getDate("data_min"));
			     fun.setData_fim(data_fim);
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



public List<Exames> buscarPagamentosIsentosPlanosOpcaoExame(Date dia_in, Date dia_fim,int m,int op,int ex){
	
	List<Exames> lista = new ArrayList<Exames>();
	String sql = "";
	if(m == 1 && op == 1){
		sql = "select analise_clinica, preco, count(preco) as 'quantidade', sum(total) as 'total', taxa, min(Data_da_facturacao) 'data_min', max(Data_da_facturacao) 'data_max' from vwrelpagamentoexame where Data_da_facturacao between ? and ? AND id_servicodeanalise_clinica = ? AND metodo_depagameto <> 'Isenção' AND metodo_depagameto <> 'Isenção Por Idade' AND metodo_depagameto <> 'Seguro de saude' group by analise_clinica order by analise_clinica;";
		System.out.println("CCC01: "+m);
	}
	if(m == 1 && op == 2){
		sql = "select analise_clinica, preco, count(preco) as 'quantidade', sum(total) as 'total', taxa, min(Data_da_facturacao) 'data_min', max(Data_da_facturacao) 'data_max' from vwrelpagamentoexame where Data_da_facturacao between ? and ? AND id_servicodeanalise_clinica = ? AND metodo_depagameto <> 'Isenção' AND metodo_depagameto <> 'Isenção Por Idade' AND metodo_depagameto <> 'Seguro de saude' group by analise_clinica order by analise_clinica;";
		System.out.println("CCC02: "+m);
	}
	if(m == 2 && op == 1){
		sql = "select analise_clinica, preco, count(preco) as 'quantidade', sum(total) as 'total', taxa, min(Data_da_facturacao) 'data_min', max(Data_da_facturacao) 'data_max' from vwrelpagamentoexame where Data_da_facturacao between ? and ? AND id_servicodeanalise_clinica = ? AND metodo_depagameto <> 'Isenção' AND metodo_depagameto <> 'Isenção Por Idade' AND metodo_depagameto <> 'Seguro de saude' group by analise_clinica order by analise_clinica; ";
		System.out.println("CCC03: "+m);
	}
	if(m == 2 && op == 2){
		sql = "select analise_clinica, preco, count(preco) as 'quantidade', sum(total) as 'total', taxa, min(Data_da_facturacao) 'data_min', max(Data_da_facturacao) 'data_max' from vwrelpagamentoexame where Data_da_facturacao between ? and ? AND id_servicodeanalise_clinica = ? AND metodo_depagameto <> 'Isenção' AND metodo_depagameto <> 'Isenção Por Idade' AND metodo_depagameto <> 'Seguro de saude' group by analise_clinica order by analise_clinica; ";
		System.out.println("CCC04: "+m);
	}
	
	if(m == 3){
		sql = "select analise_clinica, preco, count(preco) as 'quantidade', sum(total) as 'total', taxa, min(Data_da_facturacao) 'data_min', max(Data_da_facturacao) 'data_max' from vwrelpagamentoexame where Data_da_facturacao between ? and ? AND id_servicodeanalise_clinica = ? AND metodo_depagameto <> 'Isenção' AND metodo_depagameto <> 'Isenção Por Idade' AND metodo_depagameto <> 'Seguro de saude' group by analise_clinica order by analise_clinica; ";
		System.out.println("CCC05: "+m);
	}
    try {
    	 con = Conexao.getConexao();
		 PreparedStatement ps = con.prepareStatement(sql);	
		 ps.setDate(1, dia_in);
		 ps.setDate(2, dia_fim);
		 ps.setInt(3, ex);
		 ResultSet rs = ps.executeQuery();
		 while(rs.next()){
			 Exames fun = new Exames();
			 fun.setAnalise_clinica(rs.getString("analise_clinica"));
		     fun.setPreco(rs.getDouble("preco"));
		     fun.setQuantidade(rs.getInt("quantidade"));
		     fun.setSubtotal(rs.getDouble("total"));
		     Calendar data_inicio = Calendar.getInstance();
			 data_inicio.setTime(rs.getDate("data_max"));
		     fun.setData_inicio(data_inicio);
		     Calendar data_fim = Calendar.getInstance();
			 data_fim.setTime(rs.getDate("data_min"));
		     fun.setData_fim(data_fim);
			 lista.add(fun);
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

public List<Exames> buscarPagamentosIsentosPlanosDeExame(Date dia_in, Date dia_fim,int m){
	
	List<Exames> lista = new ArrayList<Exames>();
	String sql = "";
	if(m == 1){
		sql = "select analise_clinica, preco, count(preco) as 'quantidade', sum(total) as 'total', taxa, min(Data_da_facturacao) 'data_min', max(Data_da_facturacao) 'data_max' from vwrelpagamentoexame where Data_da_facturacao between ? and ? AND metodo_depagameto <> 'Isenção' AND metodo_depagameto <> 'Isenção Por Idade' AND metodo_depagameto <> 'Cash' AND metodo_depagameto <> 'Multicaixa' group by analise_clinica order by analise_clinica;";
	System.out.println("CCU001: "+m);
	}
	if(m == 2){
		sql = "select analise_clinica, preco, count(preco) as 'quantidade', sum(total) as 'total', taxa, min(Data_da_facturacao) 'data_min', max(Data_da_facturacao) 'data_max' from vwrelpagamentoexame where Data_da_facturacao between ? and ? AND metodo_depagameto <> 'Isenção' AND metodo_depagameto <> 'Isenção Por Idade' AND metodo_depagameto <> 'Cash' AND metodo_depagameto <> 'Seguro de saude' group by analise_clinica order by analise_clinica;";
		System.out.println("CCU002: "+m);
	}
	if(m == 3){
		sql = "select analise_clinica, preco, count(preco) as 'quantidade', sum(total) as 'total', taxa, min(Data_da_facturacao) 'data_min', max(Data_da_facturacao) 'data_max' from vwrelpagamentoexame where Data_da_facturacao between ? and ? AND metodo_depagameto <> 'Isenção' AND metodo_depagameto <> 'Isenção Por Idade' AND metodo_depagameto <> 'Cash' AND metodo_depagameto <> 'Multicaixa' group by analise_clinica order by analise_clinica;";
		System.out.println("CCU003: "+m);
	}
	
    try {
    	 con = Conexao.getConexao();
		 PreparedStatement ps = con.prepareStatement(sql);	
		 ps.setDate(1, dia_in);
		 ps.setDate(2, dia_fim);
		 ResultSet rs = ps.executeQuery();
		 while(rs.next()){
			 Exames fun = new Exames();
			 fun.setAnalise_clinica(rs.getString("analise_clinica"));
		     fun.setPreco(rs.getDouble("preco"));
		     fun.setQuantidade(rs.getInt("quantidade"));
		     fun.setSubtotal(rs.getDouble("total"));
		     Calendar data_inicio = Calendar.getInstance();
			 data_inicio.setTime(rs.getDate("data_max"));
		     fun.setData_inicio(data_inicio);
		     Calendar data_fim = Calendar.getInstance();
			 data_fim.setTime(rs.getDate("data_min"));
			 fun.setId_servicodeanalise_clinica(rs.getInt("id_servicodeanalise_clinica"));
			 fun.setFK_metodo_pagamento(rs.getInt("FK_metodo_de_pagameto"));
		     fun.setData_fim(data_fim);
			 lista.add(fun);
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

public List<Exames> buscarPagamentosIsentosPlanosOpcaoExmeIsento(Date dia_in, Date dia_fim,int m,int op,int s){
	
	List<Exames> lista = new ArrayList<Exames>();
	String sql = "";
	if(m == 1 && op == 1){
		sql = "select analise_clinica, preco, count(preco) as 'quantidade', sum(total) as 'total', taxa, min(Data_da_facturacao) 'data_min', max(Data_da_facturacao) 'data_max' from vwrelpagamentoexame where Data_da_facturacao between ? and ? AND id_servicodeanalise_clinica = ? AND metodo_depagameto <> 'Isenção' AND metodo_depagameto <> 'Isenção Por Idade' AND metodo_depagameto <> 'Seguro de saude' AND metodo_depagameto <> 'Multicaixa' group by analise_clinica order by analise_clinica; ";
	System.out.println("Ar001: "+m);
	}
	if(m == 1 && op == 2){
		sql = "select analise_clinica, preco, count(preco) as 'quantidade', sum(total) as 'total', taxa, min(Data_da_facturacao) 'data_min', max(Data_da_facturacao) 'data_max' from vwrelpagamentoexame where Data_da_facturacao between ? and ? AND id_servicodeanalise_clinica = ? AND metodo_depagameto <> 'Isenção' AND metodo_depagameto <> 'Isenção Por Idade' AND metodo_depagameto <> 'Cash' AND metodo_depagameto <> 'Multicaixa' group by analise_clinica order by analise_clinica; ";
		System.out.println("Ar002: "+m);
	}
	if(m == 2 && op == 1){
		sql = "select analise_clinica, preco, count(preco) as 'quantidade', sum(total) as 'total', taxa, min(Data_da_facturacao) 'data_min', max(Data_da_facturacao) 'data_max' from vwrelpagamentoexame where Data_da_facturacao between ? and ? AND id_servicodeanalise_clinica = ? AND metodo_depagameto <> 'Seguro de saude' AND metodo_depagameto <> 'Cash' AND metodo_depagameto <> 'Multicaixa' group by analise_clinica order by analise_clinica; ";
		System.out.println("Ar003: "+m);
	}
	if(m == 2 && op == 2){
		sql = "select analise_clinica, preco, count(preco) as 'quantidade', sum(total) as 'total', taxa, min(Data_da_facturacao) 'data_min', max(Data_da_facturacao) 'data_max' from vwrelpagamentoexame where Data_da_facturacao between ? and ? AND id_servicodeanalise_clinica = ? AND metodo_depagameto <> 'Seguro de saude' AND metodo_depagameto <> 'Cash' AND metodo_depagameto <> 'Multicaixa' group by analise_clinica order by analise_clinica; ";
		System.out.println("Ar004: "+m);
	}
	
	if(m == 3 ){
		sql = "select analise_clinica, preco, count(preco) as 'quantidade', sum(total) as 'total', taxa, min(Data_da_facturacao) 'data_min', max(Data_da_facturacao) 'data_max' from vwrelpagamentoexame where Data_da_facturacao between ? and ? AND id_servicodeanalise_clinica = ? AND metodo_depagameto <> 'Isenção' AND metodo_depagameto <> 'Isenção Por Idade' AND metodo_depagameto <> 'Seguro de saude' AND metodo_depagameto <> 'Multicaixa' group by analise_clinica order by analise_clinica; ";
		System.out.println("Ar005: "+m);
	}
    try {
    	 con = Conexao.getConexao();
		 PreparedStatement ps = con.prepareStatement(sql);	
		 ps.setDate(1, dia_in);
		 ps.setDate(2, dia_fim);
		 ps.setInt(3, s);
		 ResultSet rs = ps.executeQuery();
		 while(rs.next()){
			 Exames fun = new Exames();
			 fun.setAnalise_clinica(rs.getString("analise_clinica"));
		     fun.setPreco(rs.getDouble("preco"));
		     fun.setQuantidade(rs.getInt("quantidade"));
		     fun.setSubtotal(rs.getDouble("total"));
		     Calendar data_inicio = Calendar.getInstance();
			 data_inicio.setTime(rs.getDate("data_max"));
		     fun.setData_inicio(data_inicio);
		     Calendar data_fim = Calendar.getInstance();
			 data_fim.setTime(rs.getDate("data_min"));
			 fun.setId_servicodeanalise_clinica(rs.getInt("id_servicodeanalise_clinica"));
		     fun.setData_fim(data_fim);
			 lista.add(fun);
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

public List<Exames> buscarComPagamentoIsentoPlanoExamea(Date dia_in, Date dia_fim,int m,String s){
	
	List<Exames> lista = new ArrayList<Exames>();
	String sql = "";
	if(m == 1){
		sql = "select analise_clinica, preco, count(preco) as 'quantidade', sum(total) as 'total', taxa, min(Data_da_facturacao) 'data_min', max(Data_da_facturacao) 'data_max' from vwrelpagamentoexame where Data_da_facturacao between ? and ? and id_servicodeanalise_clinica = ? AND metodo_depagameto <> 'Isenção' AND metodo_depagameto <> 'Isenção Por Idade' AND metodo_depagameto <> 'Seguro de saude' AND metodo_depagameto <> 'Multicaixa' group by analise_clinica order by analise_clinica ";
	}
	if(m == 2){
		sql = "select analise_clinica, preco, count(preco) as 'quantidade', sum(total) as 'total', taxa, min(Data_da_facturacao) 'data_min', max(Data_da_facturacao) 'data_max' from vwrelpagamentoexame where Data_da_facturacao between ? and ? and id_servicodeanalise_clinica = ? AND metodo_depagameto <> 'Isenção' AND metodo_depagameto <> 'Isenção Por Idade' AND metodo_depagameto <> 'Seguro de saude' AND metodo_depagameto <> 'Multicaixa' group by analise_clinica order by analise_clinica";
	}
	if(m == 3){
		sql = "select analise_clinica, preco, count(preco) as 'quantidade', sum(total) as 'total', taxa, min(Data_da_facturacao) 'data_min', max(Data_da_facturacao) 'data_max' from vwrelpagamentoexame where Data_da_facturacao between ? and ? and id_servicodeanalise_clinica = ? AND metodo_depagameto <> 'Seguro de saude' AND metodo_depagameto <> 'Isenção Por Idade' AND metodo_depagameto <> 'Cash' AND metodo_depagameto <> 'Multicaixa' group by analise_clinica order by analise_clinica; ";
	}
	
    try {
    	 con = Conexao.getConexao();
		 PreparedStatement ps = con.prepareStatement(sql);	
		 ps.setDate(1, dia_in);
		 ps.setDate(2, dia_fim);
		 ps.setString(3, s);
		 ResultSet rs = ps.executeQuery();
		 while(rs.next()){
			 Exames fun = new Exames();
			 fun.setAnalise_clinica(rs.getString("analise_clinica"));
		     fun.setPreco(rs.getDouble("preco"));
		     fun.setQuantidade(rs.getInt("quantidade"));
		     fun.setSubtotal(rs.getDouble("total"));
		     Calendar data_inicio = Calendar.getInstance();
			 data_inicio.setTime(rs.getDate("data_max"));
		     fun.setData_inicio(data_inicio);
		     Calendar data_fim = Calendar.getInstance();
			 data_fim.setTime(rs.getDate("data_min"));
			 fun.setId_servicodeanalise_clinica(rs.getInt("id_servicodeanalise_clinica"));
			 fun.setFK_metodo_pagamento(rs.getInt("FK_metodo_de_pagameto"));
		     fun.setData_fim(data_fim);
			 lista.add(fun);
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


public List<Exames> buscarPagamentosIsentosOpcaob(Date dia_in, Date dia_fim,int m,int op){
	
	List<Exames> lista = new ArrayList<Exames>();
	String sql = "";
	if(m == 1 && op == 1){
		sql = "select analise_clinica, preco, count(preco) as 'quantidade', sum(total) as 'total', taxa, min(Data_da_facturacao) 'data_min', max(Data_da_facturacao) 'data_max' from vwrelpagamentoexame where Data_da_facturacao between ? and ? AND metodo_depagameto <> 'Isenção' AND metodo_depagameto <> 'Isenção Por Idade' AND metodo_depagameto <> 'Seguro de saude' group by analise_clinica order by analise_clinica";
		System.out.println("FFF01: "+m);
	}
	if(m == 1 && op == 2){
		sql = "select analise_clinica, preco, count(preco) as 'quantidade', sum(total) as 'total', taxa, min(Data_da_facturacao) 'data_min', max(Data_da_facturacao) 'data_max' from vwrelpagamentoexame where Data_da_facturacao between ? and ? AND metodo_depagameto <> 'Isenção' AND metodo_depagameto <> 'Isenção Por Idade' AND metodo_depagameto <> 'Seguro de saude' group by analise_clinica order by analise_clinica; ";
		System.out.println("FFF02: "+m);
	}
	if(m == 2 && op == 1){
		sql = "select analise_clinica, preco, count(preco) as 'quantidade', sum(total) as 'total', taxa, min(Data_da_facturacao) 'data_min', max(Data_da_facturacao) 'data_max' from vwrelpagamentoexame where Data_da_facturacao between ? and ? AND metodo_depagameto <> 'Cash' AND metodo_depagameto <> 'Multicaixa' AND metodo_depagameto <> 'Seguro de saude' group by analise_clinica order by analise_clinica; ";
		System.out.println("FFF03: "+m);
	}
	if(m == 2 && op == 2){
		sql = "select analise_clinica, preco, count(preco) as 'quantidade', sum(total) as 'total', taxa, min(Data_da_facturacao) 'data_min', max(Data_da_facturacao) 'data_max' from vwrelpagamentoexame where Data_da_facturacao between ? and ? AND metodo_depagameto <> 'Seguro de saude' AND metodo_depagameto <> 'Cash' AND metodo_depagameto <> 'Multicaixa' group by analise_clinica order by analise_clinica; ";
		System.out.println("FFF04: "+m);
	}
	
	
    try {
    	 con = Conexao.getConexao();
		 PreparedStatement ps = con.prepareStatement(sql);	
		 ps.setDate(1, dia_in);
		 ps.setDate(2, dia_fim);
		 ResultSet rs = ps.executeQuery();
		 while(rs.next()){
			 Exames fun = new Exames();
			 fun.setAnalise_clinica(rs.getString("analise_clinica"));
		     fun.setPreco(rs.getDouble("preco"));
		     fun.setQuantidade(rs.getInt("quantidade"));
		     fun.setSubtotal(rs.getDouble("total"));
		     Calendar data_inicio = Calendar.getInstance();
			 data_inicio.setTime(rs.getDate("data_max"));
		     fun.setData_inicio(data_inicio);
		     Calendar data_fim = Calendar.getInstance();
			 data_fim.setTime(rs.getDate("data_min"));
			 fun.setId_servicodeanalise_clinica(rs.getInt("id_servicodeanalise_clinica"));
		     fun.setData_fim(data_fim);
			 lista.add(fun);
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

public List<Exames> buscarPagamentosIsentosPlanoc(Date dia_in, Date dia_fim,int m){
	
	List<Exames> lista = new ArrayList<Exames>();
	String sql = "";
	if(m == 1){
		sql = "select analise_clinica, preco, count(preco) as 'quantidade', sum(total) as 'total', taxa, min(Data_da_facturacao) 'data_min', max(Data_da_facturacao) 'data_max' from vwrelpagamentoexame where Data_da_facturacao between ? and ? AND metodo_depagameto <> 'Isenção' AND metodo_depagameto <> 'Isenção Por Idade' AND metodo_depagameto <> 'Seguro de saude' group by analise_clinica order by analise_clinica ";
		System.out.println("EEE01: "+m);
	}
	if(m == 2){
		sql = "select analise_clinica, preco, count(preco) as 'quantidade', sum(total) as 'total', taxa, min(Data_da_facturacao) 'data_min', max(Data_da_facturacao) 'data_max' from vwrelpagamentoexame where Data_da_facturacao between ? and ? AND metodo_depagameto <> 'Seguro de saude' AND metodo_depagameto <> 'Isenção Por Idade' AND metodo_depagameto <> 'Cash' AND metodo_depagameto <> 'Multicaixa' group by analise_clinica order by analise_clinica";
		System.out.println("EEE02: "+m);
	}
	if(m == 3){
		sql = "select analise_clinica, preco, count(preco) as 'quantidade', sum(total) as 'total', taxa, min(Data_da_facturacao) 'data_min', max(Data_da_facturacao) 'data_max' from vwrelpagamentoexame where Data_da_facturacao between ? and ? AND metodo_depagameto <> 'Isenção' AND metodo_depagameto <> 'Isenção Por Idade' AND metodo_depagameto <> 'Seguro de saude' group by analise_clinica order by analise_clinica ";
		System.out.println("EEE03: "+m);
	}
	
    try {
    	 con = Conexao.getConexao();
		 PreparedStatement ps = con.prepareStatement(sql);	
		 ps.setDate(1, dia_in);
		 ps.setDate(2, dia_fim);
		 ResultSet rs = ps.executeQuery();
		 while(rs.next()){
			 Exames fun = new Exames();
			 fun.setAnalise_clinica(rs.getString("analise_clinica"));
		     fun.setPreco(rs.getDouble("preco"));
		     fun.setQuantidade(rs.getInt("quantidade"));
		     fun.setSubtotal(rs.getDouble("total"));
		     Calendar data_inicio = Calendar.getInstance();
			 data_inicio.setTime(rs.getDate("data_max"));
		     fun.setData_inicio(data_inicio);
		     Calendar data_fim = Calendar.getInstance();
			 data_fim.setTime(rs.getDate("data_min"));
			 fun.setId_servicodeanalise_clinica(rs.getInt("id_servicodeanalise_clinica"));
		     fun.setData_fim(data_fim);
			 lista.add(fun);
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

public List<Exames> buscarComPagamentoIsentoPlanoExame(Date dia_in, Date dia_fim,int m,String s){
	
	List<Exames> lista = new ArrayList<Exames>();
	String sql = "";
	if(m == 1)
	{
		sql = "select fk_servico as 'id_servicodeanalise_clinica', analise_clinica, preco, count(preco) as 'quantidade', sum(total) as 'total', taxa, min(Data_da_facturacao) 'data_min', max(Data_da_facturacao) 'data_max', FK_metodo_de_pagameto from vwrelpagamentoexame where Data_da_facturacao between ? and ? and fk_servico = ? AND metodo_depagameto <> 'Isenção' AND metodo_depagameto <> 'Isenção Por Idade' AND metodo_depagameto <> 'Seguro de saude' AND metodo_depagameto <> 'Multicaixa' group by analise_clinica order by analise_clinica ";
		System.out.println("zzz01: "+m);
	}
	if(m == 2){
		sql = "select fk_servico as 'id_servicodeanalise_clinica', analise_clinica, preco, count(preco) as 'quantidade', sum(total) as 'total', taxa, min(Data_da_facturacao) 'data_min', max(Data_da_facturacao) 'data_max', FK_metodo_de_pagameto from vwrelpagamentoexame where Data_da_facturacao between ? and ? and fk_servico = ? AND metodo_depagameto <> 'Isenção' AND metodo_depagameto <> 'Isenção Por Idade' AND metodo_depagameto <> 'Seguro de saude' AND metodo_depagameto <> 'cash' group by analise_clinica order by analise_clinica ";
		System.out.println("zzz02: "+m);
	}
	if(m == 3){
		sql = "select fk_servico as 'id_servicodeanalise_clinica', analise_clinica, preco, count(preco) as 'quantidade', sum(total) as 'total', taxa, min(Data_da_facturacao) 'data_min', max(Data_da_facturacao) 'data_max', FK_metodo_de_pagameto from vwrelpagamentoexame where Data_da_facturacao between ? and ? and fk_servico = ? AND metodo_depagameto <> 'Cash' AND metodo_depagameto <> 'Isenção Por Idade' AND metodo_depagameto <> 'Seguro de saude' AND metodo_depagameto <> 'Multicaixa' group by analise_clinica order by analise_clinica ";
		System.out.println("zzz03: "+m);
	}
	
    try {
    	 con = Conexao.getConexao();
		 PreparedStatement ps = con.prepareStatement(sql);	
		 ps.setDate(1, dia_in);
		 ps.setDate(2, dia_fim);
		 ps.setString(3, s);
		 ResultSet rs = ps.executeQuery();
		 while(rs.next()){
			 Exames fun = new Exames();
			 fun.setAnalise_clinica(rs.getString("analise_clinica"));
		     fun.setPreco(rs.getDouble("preco"));
		     fun.setQuantidade(rs.getInt("quantidade"));
		     fun.setSubtotal(rs.getDouble("total"));
		     Calendar data_inicio = Calendar.getInstance();
			 data_inicio.setTime(rs.getDate("data_max"));
		     fun.setData_inicio(data_inicio);
		     Calendar data_fim = Calendar.getInstance();
			 data_fim.setTime(rs.getDate("data_min"));
			 fun.setId_servicodeanalise_clinica(rs.getInt("id_servicodeanalise_clinica"));
			 fun.setFK_metodo_pagamento(rs.getInt("FK_metodo_de_pagameto"));
		     fun.setData_fim(data_fim);
			 lista.add(fun);
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

public List<Exames> buscarPagamentosIsentosPlanosOpcaoEx(Date dia_in, Date dia_fim,int m,int op,int s){
	
	List<Exames> lista = new ArrayList<Exames>();
	String sql = "";
	if(m == 1 && op == 1){
		sql = "select fk_servico as 'id_servicodeanalise_clinica', analise_clinica, preco, count(preco) as 'quantidade', sum(total) as 'total', taxa, Data_da_facturacao, min(Data_da_facturacao) 'data_min', max(Data_da_facturacao) 'data_max' from vwrelpagamentoexame where Data_da_facturacao between ? and ? and fk_servico = ? and metodo_depagameto <> 'Isenção' AND metodo_depagameto <> 'Isenção Por Idade' AND metodo_depagameto <> 'Seguro de saude' AND metodo_depagameto <> 'Multicaixa' group by analise_clinica order by analise_clinica ";
		System.out.println("DDD01: "+m);
	}
	if(m == 1 && op == 2){
		sql = "select fk_servico as 'id_servicodeanalise_clinica', analise_clinica, preco, count(preco) as 'quantidade', sum(total) as 'total', taxa, Data_da_facturacao, min(Data_da_facturacao) 'data_min', max(Data_da_facturacao) 'data_max' from vwrelpagamentoexame where Data_da_facturacao between ? and ? and fk_servico = ? and metodo_depagameto <> 'Isenção' AND metodo_depagameto <> 'Isenção Por Idade' AND metodo_depagameto <> 'Seguro de saude' AND metodo_depagameto <> 'cash' group by analise_clinica order by analise_clinica; ";
		System.out.println("DDD02: "+m);
	}
	if(m == 2 && op == 1){
		sql = "select fk_servico as 'id_servicodeanalise_clinica', analise_clinica, preco, count(preco) as 'quantidade', sum(total) as 'total', taxa, Data_da_facturacao, min(Data_da_facturacao) 'data_min', max(Data_da_facturacao) 'data_max' from vwrelpagamentoexame where Data_da_facturacao between ? and ? and fk_servico = ? and metodo_depagameto <> 'Isenção' AND metodo_depagameto <> 'Isenção Por Idade' AND metodo_depagameto <> 'Seguro de saude' group by analise_clinica order by analise_clinica; ";
		System.out.println("DDD03: "+m);
	}
	if(m == 2 && op == 2){
		sql = "select fk_servico as 'id_servicodeanalise_clinica', analise_clinica, preco, count(preco) as 'quantidade', sum(total) as 'total', taxa, Data_da_facturacao, min(Data_da_facturacao) 'data_min', max(Data_da_facturacao) 'data_max' from vwrelpagamentoexame where Data_da_facturacao between ? and ? and fk_servico = ? and metodo_depagameto <> 'Isenção' AND metodo_depagameto <> 'Isenção Por Idade' AND metodo_depagameto <> 'Seguro de saude' group by analise_clinica order by analise_clinica ";
		System.out.println("DDD04: "+m);
	}
	
	if(m == 3){
		sql = "select fk_servico as 'id_servicodeanalise_clinica', analise_clinica, preco, count(preco) as 'quantidade', sum(total) as 'total', taxa, Data_da_facturacao, min(Data_da_facturacao) 'data_min', max(Data_da_facturacao) 'data_max' from vwrelpagamentoexame where Data_da_facturacao between ? and ? and fk_servico = ? and metodo_depagameto <> 'Isenção' AND metodo_depagameto <> 'Isenção Por Idade' AND metodo_depagameto <> 'Seguro de saude' group by analise_clinica order by analise_clinica ";
		System.out.println("DDD05: "+m);
	}
    try {
    	 con = Conexao.getConexao();
		 PreparedStatement ps = con.prepareStatement(sql);	
		 ps.setDate(1, dia_in);
		 ps.setDate(2, dia_fim);
		 ps.setInt(3, s);
		 ResultSet rs = ps.executeQuery();
		 while(rs.next()){
			 Exames fun = new Exames();
			 fun.setAnalise_clinica(rs.getString("analise_clinica"));
		     fun.setPreco(rs.getDouble("preco"));
		     fun.setQuantidade(rs.getInt("quantidade"));
		     fun.setSubtotal(rs.getDouble("total"));
		     Calendar data_inicio = Calendar.getInstance();
			 data_inicio.setTime(rs.getDate("Data_da_facturacao"));
		     fun.setData_inicio(data_inicio);
		     Calendar data_fim = Calendar.getInstance();
			 data_fim.setTime(rs.getDate("data_min"));
			 fun.setId_servicodeanalise_clinica(rs.getInt("id_servicodeanalise_clinica"));
		     fun.setData_fim(data_fim);
			 lista.add(fun);
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

public List<Exames> buscarPagamentosIsentosOpcaox(Date dia_in, Date dia_fim,int m,int op){
	
	List<Exames> lista = new ArrayList<Exames>();
	String sql = "";
	if(m == 1 && op == 1){
		sql = "select fk_servico as 'id_servicodeanalise_clinica', analise_clinica, preco, count(preco) as 'quantidade', sum(total) as 'total', taxa, min(Data_da_facturacao) 'data_min', max(Data_da_facturacao) 'data_max' from vwrelpagamentoexame where Data_da_facturacao between ? and ? AND metodo_depagameto <> 'Isenção' AND metodo_depagameto <> 'Isenção Por Idade' AND metodo_depagameto <> 'Seguro de saude' AND metodo_depagameto <> 'Multicaixa' group by analise_clinica order by analise_clinica; ";
		System.out.println("BBB01: "+m);
	}
	if(m == 1 && op == 2){
		sql = "select fk_servico as 'id_servicodeanalise_clinica', analise_clinica, preco, count(preco) as 'quantidade', sum(total) as 'total', taxa, min(Data_da_facturacao) 'data_min', max(Data_da_facturacao) 'data_max' from vwrelpagamentoexame where Data_da_facturacao between ? and ? AND metodo_depagameto <> 'Isenção' AND metodo_depagameto <> 'Isenção Por Idade' AND metodo_depagameto <> 'Cash' AND metodo_depagameto <> 'Seguro de saude' group by analise_clinica order by analise_clinica; ";
		System.out.println("BBB02: "+m);
	}
	if(m == 2 && op == 1){
		sql = "select fk_servico as 'id_servicodeanalise_clinica', analise_clinica, preco, count(preco) as 'quantidade', sum(total) as 'total', taxa, min(Data_da_facturacao) 'data_min', max(Data_da_facturacao) 'data_max' from vwrelpagamentoexame where Data_da_facturacao between ? and ? AND metodo_depagameto <> 'Isenção' AND metodo_depagameto <> 'Isenção Por Idade' AND metodo_depagameto <> 'Seguro de saude' group by analise_clinica order by analise_clinica;";
		System.out.println("BBB03: "+m);
	}
	if(m == 2 && op == 2){
		sql = "select analise_clinica, preco, count(preco) as 'quantidade', sum(total) as 'total', taxa, min(Data_da_facturacao) 'data_min', max(Data_da_facturacao) 'data_max' from vwrelpagamentoexame where Data_da_facturacao between ? and ? AND metodo_depagameto <> 'Isenção' AND metodo_depagameto <> 'Isenção Por Idade' AND metodo_depagameto <> 'Seguro de saude' group by analise_clinica order by analise_clinica; ";
		System.out.println("BBB04: "+m);
	}
	
	
    try {
    	 con = Conexao.getConexao();
		 PreparedStatement ps = con.prepareStatement(sql);	
		 ps.setDate(1, dia_in);
		 ps.setDate(2, dia_fim);
		 ResultSet rs = ps.executeQuery();
		 while(rs.next()){
			 Exames fun = new Exames();
			 fun.setAnalise_clinica(rs.getString("analise_clinica"));
		     fun.setPreco(rs.getDouble("preco"));
		     fun.setQuantidade(rs.getInt("quantidade"));
		     fun.setSubtotal(rs.getDouble("total"));
		     Calendar data_inicio = Calendar.getInstance();
			 data_inicio.setTime(rs.getDate("data_max"));
		     fun.setData_inicio(data_inicio);
		     Calendar data_fim = Calendar.getInstance();
			 data_fim.setTime(rs.getDate("data_min"));
			 fun.setId_servicodeanalise_clinica(rs.getInt("id_servicodeanalise_clinica"));
		     fun.setData_fim(data_fim);
			 lista.add(fun);
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

public List<Exames> buscarPagamentosIsentosPlanosx(Date dia_in, Date dia_fim,int m){
	
	List<Exames> lista = new ArrayList<Exames>();
	String sql = "";
	if(m == 1)
	{
		sql = "select fk_servico as 'id_servicodeanalise_clinica', analise_clinica, preco, count(preco) as 'quantidade', sum(total) as 'total', taxa, min(Data_da_facturacao) 'data_min', max(Data_da_facturacao) 'data_max' from vwrelpagamentoexame where Data_da_facturacao between ? and ? AND metodo_depagameto <> 'Isenção' AND metodo_depagameto <> 'Isenção Por Idade' AND metodo_depagameto <> 'Seguro de saude' group by analise_clinica order by analise_clinica";
		System.out.println("AAAA01: "+m);
	}
	if(m == 2){
		sql = "select analise_clinica, preco, count(preco) as 'quantidade', sum(total) as 'total', taxa, min(Data_da_facturacao) 'data_min', max(Data_da_facturacao) 'data_max' from vwrelpagamentoexame where Data_da_facturacao between ? and ? AND metodo_depagameto <> 'Isenção' AND metodo_depagameto <> 'Isenção Por Idade' AND metodo_depagameto <> 'Seguro de saude' group by analise_clinica order by analise_clinica";
		System.out.println("AAAA02: "+m);
	}
	if(m == 3){
		sql = "select analise_clinica, preco, count(preco) as 'quantidade', sum(total) as 'total', taxa, min(Data_da_facturacao) 'data_min', max(Data_da_facturacao) 'data_max' from vwrelpagamentoexame where Data_da_facturacao between ? and ? AND metodo_depagameto <> 'Isenção' AND metodo_depagameto <> 'Isenção Por Idade' AND metodo_depagameto <> 'Seguro de saude' group by analise_clinica order by analise_clinica";
		System.out.println("AAAA03: "+m);
	}
	
    try {
    	 con = Conexao.getConexao();
		 PreparedStatement ps = con.prepareStatement(sql);	
		 ps.setDate(1, dia_in);
		 ps.setDate(2, dia_fim);
		 ResultSet rs = ps.executeQuery();
		 while(rs.next()){
			 Exames fun = new Exames();
			 fun.setAnalise_clinica(rs.getString("analise_clinica"));
		     fun.setPreco(rs.getDouble("preco"));
		     fun.setQuantidade(rs.getInt("quantidade"));
		     fun.setSubtotal(rs.getDouble("total"));
		     Calendar data_inicio = Calendar.getInstance();
			 data_inicio.setTime(rs.getDate("data_max"));
		     fun.setData_inicio(data_inicio);
		     Calendar data_fim = Calendar.getInstance();
			 data_fim.setTime(rs.getDate("data_min"));
			 fun.setId_servicodeanalise_clinica(rs.getInt("id_servicodeanalise_clinica"));
		     fun.setData_fim(data_fim);
			 lista.add(fun);
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


//------------------------------------------------------------------- FIM RELATï¿½RIO
//------------------------------------------------------------------- INICIO SERVIï¿½OS GERAIS
public List<Tratamento> getVendasTratamentoItens()
{

	String sql = "select servico, preco, count(preco) as 'quantidade', sum(total) as 'total', taxa, min(Data_da_facturacao) 'data_min', max(Data_da_facturacao) 'data_max' from vwrelpagamentoservicosgerais where Data_da_facturacao = curdate() group by servico order by servico";
	List<Tratamento> lsVenda = new ArrayList<Tratamento>();
	try{
		con = Conexao.getConexao();
		PreparedStatement ps = con.prepareStatement(sql);
		ResultSet rs = ps.executeQuery();
		while(rs.next())
		{
			Tratamento trat = new Tratamento();
//			trat.setFK_tratamento(rs.getInt("FK_tratamento"));
			trat.setServico(rs.getString("servico"));
			trat.setQuantidade(rs.getInt("quantidade"));
			trat.setPreco_tratamento(rs.getDouble("preco"));
			trat.setTotal_pago_tratamento(rs.getInt("total"));
			Calendar inicio = Calendar.getInstance();
			inicio.setTime(rs.getDate("data_max"));
			trat.setData_inicio(inicio);
			Calendar fim = Calendar.getInstance();
			fim.setTime(rs.getDate("data_min"));
			trat.setData_fim(fim);
			lsVenda.add(trat);
			
		}
		
	}catch(Exception e){
		e.printStackTrace();
	}
	return lsVenda;
}

public List<Tratamento> buscarVendasTratamentoPorData(Date dia_in, Date dia_fim){

	String sql = "select servico, preco, count(preco) as 'quantidade', sum(total) as 'total', taxa, min(Data_da_facturacao) 'data_min', max(Data_da_facturacao) 'data_max' from vwrelpagamentoservicosgerais where Data_da_facturacao between ? and ? group by servico order by servico";
	List<Tratamento> lsVenda = new ArrayList<Tratamento>();
	try{
		con = Conexao.getConexao();
		PreparedStatement ps = con.prepareStatement(sql);
		ps.setDate(1, dia_in);
		ps.setDate(2, dia_fim);
		ResultSet rs = ps.executeQuery();
		while(rs.next()){
			Tratamento trat = new Tratamento();
//			trat.setFK_tratamento(rs.getInt("FK_tratamento"));
			trat.setServico(rs.getString("servico"));
			trat.setQuantidade(rs.getInt("quantidade"));
			trat.setPreco_tratamento(rs.getDouble("preco"));
			trat.setTotal_pago_tratamento(rs.getInt("total"));
			Calendar inicio = Calendar.getInstance();
			inicio.setTime(rs.getDate("data_max"));
			trat.setData_inicio(inicio);
			Calendar fim = Calendar.getInstance();
			fim.setTime(rs.getDate("data_min"));
			trat.setData_fim(fim);
			lsVenda.add(trat);
		}
		
	}catch(Exception e){
		e.printStackTrace();
	}
	return lsVenda;
}

public List<Tratamento> buscarPagamentosIsentosPlanosOpcaoServico(Date dia_in, Date dia_fim,int m,int op,int sg){
	
	List<Tratamento> lsVenda = new ArrayList<Tratamento>();
	String sql = "";
	if(m == 1 && op == 1){
		sql = "select servico, preco, count(preco) as 'quantidade', sum(total) as 'total', taxa, min(Data_da_facturacao) 'data_min', max(Data_da_facturacao) 'data_max' from vwrelpagamentoservicosgerais where Data_da_facturacao between ? and ? and fk_servico = ? and metodo_depagameto <> 'Isenção' and metodo_depagameto <> 'Isenção Por Idade' and metodo_depagameto <> 'Seguro de Saude' and metodo_depagameto <> 'Multicaixa' group by servico order by servico";
		System.out.println("Jose00: "+m+" op: "+op);
	}
	if(m == 1 && op == 2){
		sql = "select servico, preco, count(preco) as 'quantidade', sum(total) as 'total', taxa, min(Data_da_facturacao) 'data_min', max(Data_da_facturacao) 'data_max' from vwrelpagamentoservicosgerais where Data_da_facturacao between ? and ? and fk_servico = ? and metodo_depagameto <> 'Isenção' and metodo_depagameto <> 'Isenção Por Idade' and metodo_depagameto <> 'Seguro de Saude' and metodo_depagameto <> 'Multicaixa' group by servico order by servico";
		System.out.println("Jose01: "+m+" op: "+op);
	}
	if(m == 2 && op == 1){
		sql = "select servico, preco, count(preco) as 'quantidade', sum(total) as 'total', taxa, min(Data_da_facturacao) 'data_min', max(Data_da_facturacao) 'data_max' from vwrelpagamentoservicosgerais where Data_da_facturacao between ? and ? and fk_servico = ? and metodo_depagameto <> 'Isenção' and metodo_depagameto <> 'Isenção Por Idade' and metodo_depagameto <> 'Seguro de Saude' and metodo_depagameto <> 'Multicaixa' group by servico order by servico; ";
		System.out.println("Jose02: "+m+" op: "+op);
	}
	if(m == 2 && op == 2){
		sql = "select servico, preco, count(preco) as 'quantidade', sum(total) as 'total', taxa, min(Data_da_facturacao) 'data_min', max(Data_da_facturacao) 'data_max' from vwrelpagamentoservicosgerais where Data_da_facturacao between ? and ? and fk_servico = ? and metodo_depagameto <> 'Isenção' and metodo_depagameto <> 'Isenção Por Idade' and metodo_depagameto <> 'Seguro de Saude' and metodo_depagameto <> 'Multicaixa' group by servico order by servico; ";
		System.out.println("Jose03: "+m+" op: "+op);
	}
	
	if(m == 3){
		sql = "select servico, preco, count(preco) as 'quantidade', sum(total) as 'total', taxa, min(Data_da_facturacao) 'data_min', max(Data_da_facturacao) 'data_max' from vwrelpagamentoservicosgerais where Data_da_facturacao between ? and ? and fk_servico = ? and metodo_depagameto <> 'Isenção' and metodo_depagameto <> 'Isenção Por Idade' and metodo_depagameto <> 'Cash' and metodo_depagameto <> 'Multicaixa' group by servico order by servico; ";
		System.out.println("Jose04: "+m+" op: "+op);
	}
    try {
    	 con = Conexao.getConexao();
		 PreparedStatement ps = con.prepareStatement(sql);	
		 ps.setDate(1, dia_in);
		 ps.setDate(2, dia_fim);
		 ps.setInt(3, sg);
		 ResultSet rs = ps.executeQuery();
		 while(rs.next()){
			 Tratamento trat = new Tratamento();
				trat.setFK_tratamento(rs.getInt("fk_servico"));
				trat.setServico(rs.getString("servico"));
				trat.setQuantidade(rs.getInt("quantidade"));
				trat.setPreco_tratamento(rs.getDouble("preco"));
				trat.setTotal_pago_tratamento(rs.getInt("total"));
				Calendar inicio = Calendar.getInstance();
				inicio.setTime(rs.getDate("data_max"));
				trat.setData_inicio(inicio);
				Calendar fim = Calendar.getInstance();
				fim.setTime(rs.getDate("data_min"));
				trat.setData_fim(fim);
				lsVenda.add(trat);
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
	return lsVenda;
}

public List<Tratamento> buscarPagamentosIsentosPlanosDeServicog(Date dia_in, Date dia_fim,int m){
	
	List<Tratamento> lsVenda = new ArrayList<Tratamento>();
	String sql = "";
	if(m == 1)
	{
		sql = "select servico, preco, count(preco) as 'quantidade', sum(total) as 'total', taxa, min(Data_da_facturacao) 'data_min', max(Data_da_facturacao) 'data_max' from vwrelpagamentoservicosgerais where Data_da_facturacao between ? and ? and metodo_depagameto <> 'Isenção' and metodo_depagameto <> 'Isenção Por Idade' and metodo_depagameto <> 'Seguro de Saude' and metodo_depagameto <> 'Multicaixa' group by servico order by servico; ";
		System.out.println("ZZZZ00: "+m);
	}
	if(m == 2)
	{
		sql = "select servico, preco, count(preco) as 'quantidade', sum(total) as 'total', taxa, min(Data_da_facturacao) 'data_min', max(Data_da_facturacao) 'data_max' from vwrelpagamentoservicosgerais where Data_da_facturacao between ? and ? and metodo_depagameto <> 'Isenção' and metodo_depagameto <> 'Isenção Por Idade' and metodo_depagameto <> 'Seguro de Saude' and metodo_depagameto <> 'Multicaixa' group by servico order by servico; ";
		System.out.println("ZZZZ01: "+m);
	}
	if(m == 3)
	{
		sql = "select servico, preco, count(preco) as 'quantidade', sum(total) as 'total', taxa, min(Data_da_facturacao) 'data_min', max(Data_da_facturacao) 'data_max' from vwrelpagamentoservicosgerais where Data_da_facturacao between ? and ? and metodo_depagameto <> 'Isenção' and metodo_depagameto <> 'Isenção Por Idade' and metodo_depagameto <> 'Cash' and metodo_depagameto <> 'Multicaixa' group by servico order by servico; ";
		System.out.println("ZZZZ02: "+m);
	}
	
    try {
    	 con = Conexao.getConexao();
		 PreparedStatement ps = con.prepareStatement(sql);	
		 ps.setDate(1, dia_in);
		 ps.setDate(2, dia_fim);
		 ResultSet rs = ps.executeQuery();
		 while(rs.next()){
			 Tratamento trat = new Tratamento();
				trat.setFK_tratamento(rs.getInt("fk_servico"));
				trat.setServico(rs.getString("servico"));
				trat.setQuantidade(rs.getInt("quantidade"));
				trat.setPreco_tratamento(rs.getDouble("preco"));
				trat.setTotal_pago_tratamento(rs.getInt("total"));
				Calendar inicio = Calendar.getInstance();
				inicio.setTime(rs.getDate("data_max"));
				trat.setData_inicio(inicio);
				Calendar fim = Calendar.getInstance();
				fim.setTime(rs.getDate("data_min"));
				trat.setData_fim(fim);
				lsVenda.add(trat);
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
	return lsVenda;
}

public List<Tratamento> buscarPagamentosIsentosPlanosOpcaoServicogIsento(Date dia_in, Date dia_fim,int m,int op,int s){
	
	List<Tratamento> lsVenda = new ArrayList<Tratamento>();
	String sql = "";
	if(m == 1 && op == 1){
		sql = "select servico, preco, count(preco) as 'quantidade', sum(total) as 'total', taxa, min(Data_da_facturacao) 'data_min', max(Data_da_facturacao) 'data_max' from vwrelpagamentoservicosgerais where Data_da_facturacao between ? and ? and fk_servico = ? and metodo_depagameto <> 'Isenção' and metodo_depagameto <> 'Isenção Por Idade' and metodo_depagameto <> 'Seguro de Saude' and metodo_depagameto <> 'Multicaixa' group by servico order by servico; ";
	}
	if(m == 1 && op == 2){
		sql = "select servico, preco, count(preco) as 'quantidade', sum(total) as 'total', taxa, min(Data_da_facturacao) 'data_min', max(Data_da_facturacao) 'data_max' from vwrelpagamentoservicosgerais where Data_da_facturacao between ? and ? and fk_servico = ? and metodo_depagameto <> 'Isenção' and metodo_depagameto <> 'Isenção Por Idade' and metodo_depagameto <> 'Seguro de Saude' and metodo_depagameto <> 'Multicaixa' group by servico order by servico; ";
	}
	if(m == 2 && op == 1){
		sql = "select servico, preco, count(preco) as 'quantidade', sum(total) as 'total', taxa, min(Data_da_facturacao) 'data_min', max(Data_da_facturacao) 'data_max' from vwrelpagamentoservicosgerais where Data_da_facturacao between ? and ? and fk_servico = ? and metodo_depagameto <> 'Isenção' and metodo_depagameto <> 'Isenção Por Idade' and metodo_depagameto <> 'Seguro de Saude' and metodo_depagameto <> 'Multicaixa' group by servico order by servico; ";
	}
	
	if(m == 3){
		sql = "select servico, preco, count(preco) as 'quantidade', sum(total) as 'total', taxa, min(Data_da_facturacao) 'data_min', max(Data_da_facturacao) 'data_max' from vwrelpagamentoservicosgerais where Data_da_facturacao between ? and ? and fk_servico = ? and metodo_depagameto <> 'Isenção' and metodo_depagameto <> 'Isenção Por Idade' and metodo_depagameto <> 'Seguro de Saude' and metodo_depagameto <> 'Multicaixa' group by servico order by servico; ";
	}
    try {
    	 con = Conexao.getConexao();
		 PreparedStatement ps = con.prepareStatement(sql);	
		 ps.setDate(1, dia_in);
		 ps.setDate(2, dia_fim);
		 ps.setInt(3, s);
		 ResultSet rs = ps.executeQuery();
		 while(rs.next()){
			 Tratamento trat = new Tratamento();
				trat.setFK_tratamento(rs.getInt("fk_servico"));
				trat.setServico(rs.getString("servico"));
				trat.setQuantidade(rs.getInt("quantidade"));
				trat.setPreco_tratamento(rs.getDouble("preco"));
				trat.setTotal_pago_tratamento(rs.getInt("total"));
				Calendar inicio = Calendar.getInstance();
				inicio.setTime(rs.getDate("data_max"));
				trat.setData_inicio(inicio);
				Calendar fim = Calendar.getInstance();
				fim.setTime(rs.getDate("data_min"));
				trat.setData_fim(fim);
				lsVenda.add(trat);
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
	return lsVenda;
}

public List<Tratamento> buscarComPagamentoIsentoPlanoServicog(Date dia_in, Date dia_fim,int m,String s){
	
	List<Tratamento> lista = new ArrayList<Tratamento>();
	String sql = "";
	if(m == 1){
		sql = "select servico, preco, count(preco) as 'quantidade', sum(total) as 'total', taxa, min(Data_da_facturacao) 'data_min', max(Data_da_facturacao) 'data_max' from vwrelpagamentoservicosgerais where Data_da_facturacao between ? and ? and metodo_depagameto <> 'Isenção' and metodo_depagameto <> 'Isenção Por Idade' and metodo_depagameto <> 'Seguro de Saude' and metodo_depagameto <> 'Multicaixa' group by servico order by servico; ";
	}
	if(m == 2){
		sql = "select servico, preco, count(preco) as 'quantidade', sum(total) as 'total', taxa, min(Data_da_facturacao) 'data_min', max(Data_da_facturacao) 'data_max' from vwrelpagamentoservicosgerais where Data_da_facturacao between ? and ? and metodo_depagameto <> 'Isenção' and metodo_depagameto <> 'Isenção Por Idade' and metodo_depagameto <> 'Seguro de Saude' and metodo_depagameto <> 'Multicaixa' group by servico order by servico; ";
	}
	if(m == 3){
		sql = "select servico, preco, count(preco) as 'quantidade', sum(total) as 'total', taxa, min(Data_da_facturacao) 'data_min', max(Data_da_facturacao) 'data_max' from vwrelpagamentoservicosgerais where Data_da_facturacao between ? and ? and metodo_depagameto <> 'Isenção' and metodo_depagameto <> 'Isenção Por Idade' and metodo_depagameto <> 'Seguro de Saude' and metodo_depagameto <> 'Multicaixa' group by servico order by servico; ";
	}
	
    try {
    	 con = Conexao.getConexao();
		 PreparedStatement ps = con.prepareStatement(sql);	
		 ps.setDate(1, dia_in);
		 ps.setDate(2, dia_fim);
		 ps.setString(3, s);
		 ResultSet rs = ps.executeQuery();
		 while(rs.next()){
			 Tratamento trat = new Tratamento();
				trat.setFK_tratamento(rs.getInt("fk_servico"));
				trat.setServico(rs.getString("servico"));
				trat.setQuantidade(rs.getInt("quantidade"));
				trat.setPreco_tratamento(rs.getDouble("preco"));
				trat.setTotal_pago_tratamento(rs.getInt("total"));
				Calendar inicio = Calendar.getInstance();
				inicio.setTime(rs.getDate("data_max"));
				trat.setData_inicio(inicio);
				Calendar fim = Calendar.getInstance();
				fim.setTime(rs.getDate("data_min"));
				trat.setData_fim(fim);
				lista.add(trat);
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

public List<Tratamento> buscarPagamentosIsentosOpcaoSG(Date dia_in, Date dia_fim,int m,int op){
	
	List<Tratamento> lista = new ArrayList<Tratamento>();
	String sql = "";
	if(m == 1 && op == 1){
		sql = "select servico, preco, count(preco) as 'quantidade', sum(total) as 'total', taxa, min(Data_da_facturacao) 'data_min', max(Data_da_facturacao) 'data_max' from vwrelpagamentoservicosgerais where Data_da_facturacao between ? and ? and metodo_depagameto <> 'Isenção' and metodo_depagameto <> 'Isenção Por Idade' and metodo_depagameto <> 'Seguro de Saude' and metodo_depagameto <> 'Multicaixa' group by servico order by servico; ";
		System.out.println("Linda00: "+m);
	}
	if(m == 1 && op == 2){
		sql = "select servico, preco, count(preco) as 'quantidade', sum(total) as 'total', taxa, min(Data_da_facturacao) 'data_min', max(Data_da_facturacao) 'data_max' from vwrelpagamentoservicosgerais where Data_da_facturacao between ? and ? and metodo_depagameto <> 'Isenção' and metodo_depagameto <> 'Isenção Por Idade' and metodo_depagameto <> 'Seguro de Saude' and metodo_depagameto <> 'Multicaixa' group by servico order by servico; ";
		System.out.println("Linda01: "+m);
	}
	if(m == 2 && op == 1){
		sql = "select servico, preco, count(preco) as 'quantidade', sum(total) as 'total', taxa, min(Data_da_facturacao) 'data_min', max(Data_da_facturacao) 'data_max' from vwrelpagamentoservicosgerais where Data_da_facturacao between ? and ? and metodo_depagameto <> 'Isenção' and metodo_depagameto <> 'Cash' and metodo_depagameto <> 'Seguro de Saude' and metodo_depagameto <> 'Multicaixa' group by servico order by servico; ";
		System.out.println("Linda02: "+m);
	}
	if(m == 2 && op == 2){
		sql = "select servico, preco, count(preco) as 'quantidade', sum(total) as 'total', taxa, min(Data_da_facturacao) 'data_min', max(Data_da_facturacao) 'data_max' from vwrelpagamentoservicosgerais where Data_da_facturacao between ? and ? and metodo_depagameto <> 'Cash' and metodo_depagameto <> 'Isenção Por Idade' and metodo_depagameto <> 'Seguro de Saude' and metodo_depagameto <> 'Multicaixa' group by servico order by servico; ";
		System.out.println("Linda03: "+m);
	}
	
	
    try {
    	 con = Conexao.getConexao();
		 PreparedStatement ps = con.prepareStatement(sql);	
		 ps.setDate(1, dia_in);
		 ps.setDate(2, dia_fim);
		 ResultSet rs = ps.executeQuery();
		 while(rs.next()){
			 Tratamento trat = new Tratamento();
				trat.setFK_tratamento(rs.getInt("fk_servico"));
				trat.setServico(rs.getString("servico"));
				trat.setQuantidade(rs.getInt("quantidade"));
				trat.setPreco_tratamento(rs.getDouble("preco"));
				trat.setTotal_pago_tratamento(rs.getInt("total"));
				Calendar inicio = Calendar.getInstance();
				inicio.setTime(rs.getDate("data_max"));
				trat.setData_inicio(inicio);
				Calendar fim = Calendar.getInstance();
				fim.setTime(rs.getDate("data_min"));
				trat.setData_fim(fim);
				lista.add(trat);
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

public List<Tratamento> buscarPagamentosIsentosPlanoSG(Date dia_in, Date dia_fim,int m){
	
	List<Tratamento> lista = new ArrayList<Tratamento>();
	String sql = "";
	if(m == 1){
		sql = "select servico, preco, count(preco) as 'quantidade', sum(total) as 'total', taxa, min(Data_da_facturacao) 'data_min', max(Data_da_facturacao) 'data_max' from vwrelpagamentoservicosgerais where Data_da_facturacao between ? and ? and metodo_depagameto <> 'Isenção' and metodo_depagameto <> 'Isenção Por Idade' and metodo_depagameto <> 'Seguro de Saude' and metodo_depagameto <> 'Multicaixa' and metodo_metodo <> 'Cash' group by servico order by servico; ";
		System.out.println("QQQQ00: "+m);
	}
	if(m == 2){
		sql = "select servico, preco, count(preco) as 'quantidade', sum(total) as 'total', taxa, min(Data_da_facturacao) 'data_min', max(Data_da_facturacao) 'data_max' from vwrelpagamentoservicosgerais where Data_da_facturacao between ? and ? and metodo_depagameto <> 'Cash' and metodo_depagameto <> 'Isenção Por Idade' and metodo_depagameto <> 'Seguro de Saude' and metodo_depagameto <> 'Multicaixa' group by servico order by servico; ";
		System.out.println("QQQQ01: "+m);
	}
	if(m == 3){
		sql = "select servico, preco, count(preco) as 'quantidade', sum(total) as 'total', taxa, min(Data_da_facturacao) 'data_min', max(Data_da_facturacao) 'data_max' from vwrelpagamentoservicosgerais where Data_da_facturacao between ? and ? and metodo_depagameto <> 'Isenção' and metodo_depagameto <> 'Isenção Por Idade' and metodo_depagameto <> 'Seguro de Saude' and metodo_depagameto <> 'Multicaixa' group by servico order by servico; ";
		System.out.println("QQQQ03: "+m);
	}
	
    try {
    	 con = Conexao.getConexao();
		 PreparedStatement ps = con.prepareStatement(sql);	
		 ps.setDate(1, dia_in);
		 ps.setDate(2, dia_fim);
		 ResultSet rs = ps.executeQuery();
		 while(rs.next()){
			 Tratamento trat = new Tratamento();
				trat.setFK_tratamento(rs.getInt("fk_servico"));
				trat.setServico(rs.getString("servico"));
				trat.setQuantidade(rs.getInt("quantidade"));
				trat.setPreco_tratamento(rs.getDouble("preco"));
				trat.setTotal_pago_tratamento(rs.getInt("total"));
				Calendar inicio = Calendar.getInstance();
				inicio.setTime(rs.getDate("data_max"));
				trat.setData_inicio(inicio);
				Calendar fim = Calendar.getInstance();
				fim.setTime(rs.getDate("data_min"));
				trat.setData_fim(fim);
				lista.add(trat);
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

public List<Tratamento> buscarComPagamentoIsentoPlanoServicoGerais(Date dia_in, Date dia_fim,int m,String s){
	
	List<Tratamento> lista = new ArrayList<Tratamento>();
	String sql = "";
	if(m == 1){
		sql = "select servico, preco, count(preco) as 'quantidade', sum(total) as 'total', taxa, min(Data_da_facturacao) 'data_min', max(Data_da_facturacao) 'data_max' from vwrelpagamentoservicosgerais where Data_da_facturacao between ? and ? and fk_servico = ? and metodo_depagameto <> 'Isenção' and metodo_depagameto <> 'Isenção Por Idade' and metodo_depagameto <> 'Seguro de Saude' and metodo_depagameto <> 'Multicaixa' group by servico order by servico; ";
	}
	if(m == 2){
		sql = "select servico, preco, count(preco) as 'quantidade', sum(total) as 'total', taxa, min(Data_da_facturacao) 'data_min', max(Data_da_facturacao) 'data_max' from vwrelpagamentoservicosgerais where Data_da_facturacao between ? and ? and fk_servico = ? and metodo_depagameto <> 'Isenção' and metodo_depagameto <> 'Isenção Por Idade' and metodo_depagameto <> 'Seguro de Saude' and metodo_depagameto <> 'Multicaixa' group by servico order by servico;";
	}
	if(m == 3){
		sql = "select servico, preco, count(preco) as 'quantidade', sum(total) as 'total', taxa, min(Data_da_facturacao) 'data_min', max(Data_da_facturacao) 'data_max' from vwrelpagamentoservicosgerais where Data_da_facturacao between ? and ? and fk_servico = ? and metodo_depagameto <> 'Isenção' and metodo_depagameto <> 'Isenção Por Idade' and metodo_depagameto <> 'Seguro de Saude' and metodo_depagameto <> 'Multicaixa' group by servico order by servico; ";
	}
	
    try {
    	 con = Conexao.getConexao();
		 PreparedStatement ps = con.prepareStatement(sql);	
		 ps.setDate(1, dia_in);
		 ps.setDate(2, dia_fim);
		 ps.setString(3, s);
		 ResultSet rs = ps.executeQuery();
		 while(rs.next()){
			 Tratamento trat = new Tratamento();
				trat.setFK_tratamento(rs.getInt("fk_servico"));
				trat.setServico(rs.getString("servico"));
				trat.setQuantidade(rs.getInt("quantidade"));
				trat.setPreco_tratamento(rs.getDouble("preco"));
				trat.setTotal_pago_tratamento(rs.getInt("total"));
				Calendar inicio = Calendar.getInstance();
				inicio.setTime(rs.getDate("data_max"));
				trat.setData_inicio(inicio);
				Calendar fim = Calendar.getInstance();
				fim.setTime(rs.getDate("data_min"));
				trat.setData_fim(fim);
				lista.add(trat);
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

public List<Tratamento> buscarPagamentosIsentosOpcaoSGx(Date dia_in, Date dia_fim,int m,int op){
	
	List<Tratamento> lista = new ArrayList<Tratamento>();
	String sql = "";
	if(m == 1 && op == 1){
		sql = "select servico, preco, count(preco) as 'quantidade', sum(total) as 'total', taxa, min(Data_da_facturacao) 'data_min', max(Data_da_facturacao) 'data_max' from vwrelpagamentoservicosgerais where Data_da_facturacao between ? and ? and metodo_depagameto <> 'Isenção' and metodo_depagameto <> 'Isenção Por Idade' and metodo_depagameto <> 'Seguro de Saude' and metodo_depagameto <> 'Multicaixa' group by servico order by servico; ";
		System.out.println("zzzzz00: "+m+" op:"+op);
	}
	if(m == 1 && op == 2){
		sql = "select servico, preco, count(preco) as 'quantidade', sum(total) as 'total', taxa, min(Data_da_facturacao) 'data_min', max(Data_da_facturacao) 'data_max' from vwrelpagamentoservicosgerais where Data_da_facturacao between ? and ? and metodo_depagameto <> 'Isenção' and metodo_depagameto <> 'Isenção Por Idade' and metodo_depagameto <> 'Seguro de Saude' and metodo_depagameto <> 'Cash' group by servico order by servico; ";
		System.out.println("zzzzz01: "+m+" op:"+op);
	}
	if(m == 2 && op == 1){
		sql = "select servico, preco, count(preco) as 'quantidade', sum(total) as 'total', taxa, min(Data_da_facturacao) 'data_min', max(Data_da_facturacao) 'data_max' from vwrelpagamentoservicosgerais where Data_da_facturacao between ? and ? and metodo_depagameto <> 'Isenção' and metodo_depagameto <> 'Isenção Por Idade' and metodo_depagameto <> 'Seguro de Saude' and metodo_depagameto <> 'Multicaixa' group by servico order by servico; ";
		System.out.println("zzzzz02: "+m+" op:"+op);
	}
	if(m == 2 && op == 2){
		sql = "select servico, preco, count(preco) as 'quantidade', sum(total) as 'total', taxa, min(Data_da_facturacao) 'data_min', max(Data_da_facturacao) 'data_max' from vwrelpagamentoservicosgerais where Data_da_facturacao between ? and ? and metodo_depagameto <> 'Isenção' and metodo_depagameto <> 'Isenção Por Idade' and metodo_depagameto <> 'Seguro de Saude' and metodo_depagameto <> 'Multicaixa' group by servico order by servico; ";
		System.out.println("zzzzz03: "+m+" op:"+op);
	}
	
	
    try {
    	 con = Conexao.getConexao();
		 PreparedStatement ps = con.prepareStatement(sql);	
		 ps.setDate(1, dia_in);
		 ps.setDate(2, dia_fim);
		 ResultSet rs = ps.executeQuery();
		 while(rs.next()){
			 Tratamento trat = new Tratamento();
				trat.setFK_tratamento(rs.getInt("fk_servico"));
				trat.setServico(rs.getString("servico"));
				trat.setQuantidade(rs.getInt("quantidade"));
				trat.setPreco_tratamento(rs.getDouble("preco"));
				trat.setTotal_pago_tratamento(rs.getInt("total"));
				Calendar inicio = Calendar.getInstance();
				inicio.setTime(rs.getDate("data_max"));
				trat.setData_inicio(inicio);
				Calendar fim = Calendar.getInstance();
				fim.setTime(rs.getDate("data_min"));
				trat.setData_fim(fim);
				lista.add(trat);
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

public List<Tratamento> buscarPagamentosIsentosPlanoGSx(Date dia_in, Date dia_fim,int m){
	
	List<Tratamento> lista = new ArrayList<Tratamento>();
	String sql = "";
	if(m == 1){
		sql = "select servico, preco, count(preco) as 'quantidade', sum(total) as 'total', taxa, min(Data_da_facturacao) 'data_min', max(Data_da_facturacao) 'data_max' from vwrelpagamentoservicosgerais where Data_da_facturacao between ? and ? and metodo_depagameto <> 'Isenção' and metodo_depagameto <> 'Isenção Por Idade' and metodo_depagameto <> 'Seguro de Saude' group by servico order by servico; ";
		System.out.println("Ang00: "+m);
	}
	if(m == 2){
		sql = "select servico, preco, count(preco) as 'quantidade', sum(total) as 'total', taxa, min(Data_da_facturacao) 'data_min', max(Data_da_facturacao) 'data_max' from vwrelpagamentoservicosgerais where Data_da_facturacao between ? and ? and metodo_depagameto <> 'Isenção' and metodo_depagameto <> 'Isenção Por Idade' and metodo_depagameto <> 'Seguro de Saude' and metodo_depagameto <> 'Multicaixa' group by servico order by servico; ";
		System.out.println("Ang01: "+m);
	}
	if(m == 3){
		sql = "select servico, preco, count(preco) as 'quantidade', sum(total) as 'total', taxa, min(Data_da_facturacao) 'data_min', max(Data_da_facturacao) 'data_max' from vwrelpagamentoservicosgerais where Data_da_facturacao between ? and ? and metodo_depagameto <> 'Isenção' and metodo_depagameto <> 'Isenção Por Idade' and metodo_depagameto <> 'Seguro de Saude' and metodo_depagameto <> 'Multicaixa' group by servico order by servico; ";
		System.out.println("Ang03: "+m);
	}
	
    try {
    	 con = Conexao.getConexao();
		 PreparedStatement ps = con.prepareStatement(sql);	
		 ps.setDate(1, dia_in);
		 ps.setDate(2, dia_fim);
		 ResultSet rs = ps.executeQuery();
		 while(rs.next()){
			 Tratamento trat = new Tratamento();
//				trat.setFK_tratamento(rs.getInt("FK_tratamento"));
				trat.setServico(rs.getString("servico"));
				trat.setQuantidade(rs.getInt("quantidade"));
				trat.setPreco_tratamento(rs.getDouble("preco"));
				trat.setTotal_pago_tratamento(rs.getInt("total"));
				Calendar inicio = Calendar.getInstance();
				inicio.setTime(rs.getDate("data_max"));
				trat.setData_inicio(inicio);
				Calendar fim = Calendar.getInstance();
				fim.setTime(rs.getDate("data_min"));
				trat.setData_fim(fim);
				lista.add(trat);
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

public List<Tratamento> buscarPagamentosIsentosPlanosOpcaoSg(Date dia_in, Date dia_fim,int m,int op,int s){
	
	List<Tratamento> lista = new ArrayList<Tratamento>();
	String sql = "";
	if(m == 1 && op == 1){
		sql = "select servico, preco, count(preco) as 'quantidade', sum(total) as 'total', taxa, min(Data_da_facturacao) 'data_min', max(Data_da_facturacao) 'data_max' from vwrelpagamentoservicosgerais where Data_da_facturacao between ? and ? and fk_servico = ? and metodo_depagameto <> 'Isenção' and metodo_depagameto <> 'Isenção Por Idade' and metodo_depagameto <> 'Seguro de Saude' and metodo_depagameto <> 'Multicaixa' group by servico order by servico; ";
		System.out.println("KKKKK00: "+m+" op"+op);
	}
	if(m == 1 && op == 2){
		sql = "select servico, preco, count(preco) as 'quantidade', sum(total) as 'total', taxa, min(Data_da_facturacao) 'data_min', max(Data_da_facturacao) 'data_max' from vwrelpagamentoservicosgerais where Data_da_facturacao between ? and ? and fk_servico = ? and metodo_depagameto <> 'Isenção' and metodo_depagameto <> 'Isenção Por Idade' and metodo_depagameto <> 'Seguro de Saude' and metodo_depagameto <> 'Cash' group by servico order by servico; ";
		System.out.println("KKKKK01: "+m+" op"+op);
	}
	if(m == 2 && op == 1){
		sql = "select servico, preco, count(preco) as 'quantidade', sum(total) as 'total', taxa, min(Data_da_facturacao) 'data_min', max(Data_da_facturacao) 'data_max' from vwrelpagamentoservicosgerais where Data_da_facturacao between ? and ? and fk_servico = ? and metodo_depagameto <> 'Isenção' and metodo_depagameto <> 'Isenção Por Idade' and metodo_depagameto <> 'Seguro de Saude' and metodo_depagameto <> 'Multicaixa' group by servico order by servico; ";
		System.out.println("KKKKK02: "+m+" op"+op);
	}
	if(m == 2 && op == 2){
		sql = "select servico, preco, count(preco) as 'quantidade', sum(total) as 'total', taxa, min(Data_da_facturacao) 'data_min', max(Data_da_facturacao) 'data_max' from vwrelpagamentoservicosgerais where Data_da_facturacao between ? and ? and fk_servico = ? and metodo_depagameto <> 'Isenção' and metodo_depagameto <> 'Isenção Por Idade' and metodo_depagameto <> 'Seguro de Saude' and metodo_depagameto <> 'Multicaixa' group by servico order by servico; ";
		System.out.println("KKKKK03: "+m+" op"+op);
	}
	
	if(m == 3){
		sql = "select servico, preco, count(preco) as 'quantidade', sum(total) as 'total', taxa, min(Data_da_facturacao) 'data_min', max(Data_da_facturacao) 'data_max' from vwrelpagamentoservicosgerais where Data_da_facturacao between ? and ? and fk_servico = ? and metodo_depagameto <> 'Isenção' and metodo_depagameto <> 'Isenção Por Idade' and metodo_depagameto <> 'Seguro de Saude' and metodo_depagameto <> 'Multicaixa' group by servico order by servico; ";
		System.out.println("KKKKK04: "+m+" op"+op);
	}
    try {
    	 con = Conexao.getConexao();
		 PreparedStatement ps = con.prepareStatement(sql);	
		 ps.setDate(1, dia_in);
		 ps.setDate(2, dia_fim);
		 ps.setInt(3, s);
		 ResultSet rs = ps.executeQuery();
		 while(rs.next()){
			 Tratamento trat = new Tratamento();
				trat.setFK_tratamento(rs.getInt("fk_servico"));
				trat.setServico(rs.getString("servico"));
				trat.setQuantidade(rs.getInt("quantidade"));
				trat.setPreco_tratamento(rs.getDouble("preco"));
				trat.setTotal_pago_tratamento(rs.getInt("total"));
				Calendar inicio = Calendar.getInstance();
				inicio.setTime(rs.getDate("data_max"));
				trat.setData_inicio(inicio);
				Calendar fim = Calendar.getInstance();
				fim.setTime(rs.getDate("data_min"));
				trat.setData_fim(fim);
				lista.add(trat);
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
//---------------------------------------------------------FINAL SERVIï¿½OS GERAIS
////---------------------------------------------------------- INICIO PAGAMENTO PRODUTO
public List<Produtos> buscarHistoricoVendaUmProduto()
{
	
	List<Produtos> lista = new ArrayList<Produtos>();
    String sql = "select id_produto as 'fk_produto', produto as 'nome_comercial', preco as 'preco_produto', count(preco) as 'quantidade', sum(qtd) as 'qtd_vendido', sum(total) as 'total_pago', taxa, min(Data_da_facturacao) 'data_inicio', max(Data_da_facturacao) 'data_fim' "
    		   + "from vwrelpagamentoprodutos where Data_da_facturacao = curdate() group by produto order by produto asc";
    			System.out.println("Produtos Vendidos Hoje - Tela Inicial");
    try {
    	 con = Conexao.getConexao();
		 PreparedStatement ps = con.prepareStatement(sql);		
		 ResultSet rs = ps.executeQuery();
		 while(rs.next()){
			 Produtos pd = new Produtos();
				pd.setNome_comercial(rs.getString("nome_comercial"));
				pd.setQuantidade(rs.getInt("quantidade"));
				pd.setQtd_geral_vendido(rs.getInt("qtd_vendido"));
				pd.setTotal(rs.getInt("total_pago"));
				pd.setPreco_dvenda(rs.getDouble("preco_produto"));
				pd.setTaxa(rs.getInt("taxa"));
				pd.setId_produto(rs.getInt("fk_produto"));
				Calendar inicio = Calendar.getInstance();
				inicio.setTime(rs.getDate("data_inicio"));
				pd.setData_inicio(inicio);
				Calendar fim = Calendar.getInstance();
				fim.setTime(rs.getDate("data_fim"));
				pd.setData_fim(fim);
				lista.add(pd);
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

public List<Produtos> buscarHistoricoVendaUmProdutoVisualiza(){
	
	List<Produtos> lista = new ArrayList<Produtos>();
    String sql = "SELECT fp.fk_produto, pf.nome_comercial, fp.preco_produto, sum(fp.qtd_produto) as 'quantidade', sum(fp.tot_pago_produto) as 'total_pago',  min(f.Data_da_facturacao) as 'data_inicio', max(f.Data_da_facturacao) as 'data_fim', m.metodo_depagameto "
    		   + "FROM tblfacturarprodutos fp LEFT JOIN tblfactura f ON f.id_factura = fp.FK_factura LEFT JOIN tblprodutofarmaceuticos pf ON pf.id_produto = fp.fk_produto JOIN tblmetododepagameto m ON f.FK_metodo_de_pagameto = m.id_metodo_depagameto GROUP BY fp.fk_produto";
    try {
    	 con = Conexao.getConexao();
		 PreparedStatement ps = con.prepareStatement(sql);		
		 ResultSet rs = ps.executeQuery();
		 while(rs.next()){
			 Produtos pd = new Produtos();
				pd.setNome_comercial(rs.getString("nome_comercial"));
				pd.setQuantidade(rs.getInt("quantidade"));
				pd.setTotal(rs.getInt("total_pago"));
				pd.setPreco_dvenda(rs.getDouble("preco_produto"));
				pd.setId_produto(rs.getInt("fk_produto"));
				Calendar inicio = Calendar.getInstance();
				inicio.setTime(rs.getDate("data_inicio"));
				pd.setData_inicio(inicio);
				Calendar fim = Calendar.getInstance();
				fim.setTime(rs.getDate("data_fim"));
				pd.setData_fim(fim);
				lista.add(pd);
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

public List<Produtos> buscarProdutoPorData(Date dia_in, Date dia_fim)
{

	String sql = "select id_produto as 'fk_produto', produto as 'nome_comercial', preco as 'preco_produto', count(preco) as 'quantidade', sum(qtd) as 'qtd_vendido', sum(total) as 'total_pago', taxa, min(Data_da_facturacao) 'data_inicio', max(Data_da_facturacao) 'data_fim' from vwrelpagamentoprodutos where Data_da_facturacao between ? and ? group by produto order by produto asc";
	System.out.println("Pesquisa pela Data inicio e Data fim");
	List<Produtos> lista = new ArrayList<Produtos>();
	try
	{
		con = Conexao.getConexao();
		PreparedStatement ps = con.prepareStatement(sql);
		ps.setDate(1, dia_in);
		ps.setDate(2, dia_fim);
		ResultSet rs = ps.executeQuery();
		while(rs.next())
		{
			 Produtos pd = new Produtos();
				pd.setNome_comercial(rs.getString("nome_comercial"));
				pd.setQuantidade(rs.getInt("quantidade"));
				pd.setQtd_geral_vendido(rs.getInt("qtd_vendido"));
				pd.setTotal(rs.getInt("total_pago"));
				pd.setPreco_dvenda(rs.getDouble("preco_produto"));
				pd.setTaxa(rs.getInt("taxa"));
				pd.setId_produto(rs.getInt("fk_produto"));
				Calendar inicio = Calendar.getInstance();
				inicio.setTime(rs.getDate("data_inicio"));
				pd.setData_inicio(inicio);
				Calendar fim = Calendar.getInstance();
				fim.setTime(rs.getDate("data_fim"));
				pd.setData_fim(fim);
				lista.add(pd);
		}
		
	}catch(Exception e){
		e.printStackTrace();
	}
	return lista;
}

public List<Produtos> buscarProdutosPagoPlano(Date dia_in, Date dia_fim,int m)
{
	
	List<Produtos> lista = new ArrayList<Produtos>();
	String sql = "";
	if(m == 1)
	{
		sql = "select id_produto as 'fk_produto', produto as 'nome_comercial', preco as 'preco_produto', count(preco) as 'quantidade', sum(qtd) as 'qtd_vendido', sum(total) as 'total_pago', taxa, min(Data_da_facturacao) 'data_inicio', max(Data_da_facturacao) 'data_fim' from vwrelpagamentoprodutos where Data_da_facturacao between ? and ? and (metodo_depagameto = 'Cash' or metodo_depagameto = 'Multicaixa') group by produto order by produto asc";
		System.out.println("DataIn and DataFim-opcção Produtos Pagos: "+m);
	}
	if(m == 2)
	{
		sql = "select id_produto as 'fk_produto', produto as 'nome_comercial', preco as 'preco_produto', count(preco) as 'quantidade', sum(qtd) as 'qtd_vendido', sum(total) as 'total_pago', taxa, min(Data_da_facturacao) 'data_inicio', max(Data_da_facturacao) 'data_fim' from vwrelpagamentoprodutos where Data_da_facturacao between ? and ? and (metodo_depagameto = 'Isenção' or metodo_depagameto = 'Isenção Por Idade') group by produto order by produto asc";
		System.out.println("DataIn and DataFim-opcção Não sei ainda: "+m);
	}
	if(m == 3)
	{
		sql = "select id_produto as 'fk_produto', produto as 'nome_comercial', preco as 'preco_produto', count(preco) as 'quantidade', sum(qtd) as 'qtd_vendido', sum(total) as 'total_pago', taxa, min(Data_da_facturacao) 'data_inicio', max(Data_da_facturacao) 'data_fim' from vwrelpagamentoprodutos where Data_da_facturacao between ? and ? and (metodo_depagameto = 'Seguro de Saúde') group by produto order by produto asc";
		System.out.println("DataIn and DataFim-opcção Também não sei: "+m);
	}
	
    try {
    	 con = Conexao.getConexao();
		 PreparedStatement ps = con.prepareStatement(sql);	
		 ps.setDate(1, dia_in);
		 ps.setDate(2, dia_fim);
		 ResultSet rs = ps.executeQuery();
		 while(rs.next()){
			 Produtos pd = new Produtos();
				pd.setNome_comercial(rs.getString("nome_comercial"));
				pd.setQuantidade(rs.getInt("quantidade"));
				pd.setQtd_geral_vendido(rs.getInt("qtd_vendido"));
				pd.setTotal(rs.getInt("total_pago"));
				pd.setPreco_dvenda(rs.getDouble("preco_produto"));
				pd.setTaxa(rs.getInt("taxa"));
				pd.setId_produto(rs.getInt("fk_produto"));
				Calendar inicio = Calendar.getInstance();
				inicio.setTime(rs.getDate("data_inicio"));
				pd.setData_inicio(inicio);
				Calendar fim = Calendar.getInstance();
				fim.setTime(rs.getDate("data_fim"));
				pd.setData_fim(fim);
				lista.add(pd);
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
public List<Produtos> buscarProdutoPagarPorOpcao(Date dia_in, Date dia_fim,int m,int op)
{
	
	List<Produtos> lista = new ArrayList<Produtos>();
	String sql = "";
	if(m == 1 && op == 1)
	{
		sql = "select id_produto as 'fk_produto', produto as 'nome_comercial', preco as 'preco_produto', count(preco) as 'quantidade', sum(qtd) as 'qtd_vendido', sum(total) as 'total_pago', taxa, min(Data_da_facturacao) 'data_inicio', max(Data_da_facturacao) 'data_fim' from vwrelpagamentoprodutos where Data_da_facturacao between ? and ? and metodo_depagameto = 'Cash' group by produto order by produto asc";
		System.out.println("DataIn and DataFim, Produtos Pagos and Dinheiro: "+m+" op: "+op);
	}
	if(m == 1 && op == 2)
	{
		sql = "select id_produto as 'fk_produto', produto as 'nome_comercial', preco as 'preco_produto', count(preco) as 'quantidade', sum(qtd) as 'qtd_vendido', sum(total) as 'total_pago', taxa, min(Data_da_facturacao) 'data_inicio', max(Data_da_facturacao) 'data_fim' from vwrelpagamentoprodutos where Data_da_facturacao between ? and ? and metodo_depagameto = 'Multicaixa' group by produto order by produto asc";
		System.out.println("DataIn and DataFim, Produtos Pagos and Cartão: "+m+" op: "+op);
	}
	if(m == 2 && op == 1)
	{
		sql = "SELECT fp.fk_produto, pf.nome_comercial, fp.preco_produto, sum(fp.qtd_produto) as 'quantidade', sum(fp.tot_pago_produto) as 'total_pago',  min(f.Data_da_facturacao) as 'data_inicio', max(f.Data_da_facturacao) as 'data_fim', m.metodo_depagameto FROM tblfacturarprodutos fp LEFT JOIN tblfactura f ON f.id_factura = fp.FK_factura LEFT JOIN tblprodutofarmaceuticos pf ON pf.id_produto = fp.fk_produto JOIN tblmetododepagameto m ON f.FK_metodo_de_pagameto = m.id_metodo_depagameto "
			+ "WHERE data_da_facturacao between ? and ? and m.metodo_depagameto <> 'Isenção' AND m.metodo_depagameto <> 'Isenção Por Idade' AND m.metodo_depagameto <> 'Seguro de Saude' GROUP BY fp.fk_produto ";
		System.out.println("Opcao de pagamento cash****: "+m);	
	}
	if(m == 2 && op == 2)
	{
		sql = "SELECT fp.fk_produto, pf.nome_comercial, fp.preco_produto, sum(fp.qtd_produto) as 'quantidade', sum(fp.tot_pago_produto) as 'total_pago',  min(f.Data_da_facturacao) as 'data_inicio', max(f.Data_da_facturacao) as 'data_fim', m.metodo_depagameto FROM tblfacturarprodutos fp LEFT JOIN tblfactura f ON f.id_factura = fp.FK_factura LEFT JOIN tblprodutofarmaceuticos pf ON pf.id_produto = fp.fk_produto JOIN tblmetododepagameto m ON f.FK_metodo_de_pagameto = m.id_metodo_depagameto "
			+ "WHERE data_da_facturacao between ? and ? and m.metodo_depagameto <> 'Isenção' AND m.metodo_depagameto <> 'Isenção Por Idade' AND m.metodo_depagameto <> 'Seguro de Saude' GROUP BY fp.fk_produto ";
		System.out.println("Opcao de pagamento cash////: "+m);
	}
	
	
    try {
    	 con = Conexao.getConexao();
		 PreparedStatement ps = con.prepareStatement(sql);	
		 ps.setDate(1, dia_in);
		 ps.setDate(2, dia_fim);
		 ResultSet rs = ps.executeQuery();
		 while(rs.next()){
			 Produtos pd = new Produtos();
				pd.setNome_comercial(rs.getString("nome_comercial"));
				pd.setQuantidade(rs.getInt("quantidade"));
				pd.setQtd_geral_vendido(rs.getInt("qtd_vendido"));
				pd.setTotal(rs.getInt("total_pago"));
				pd.setPreco_dvenda(rs.getDouble("preco_produto"));
				pd.setTaxa(rs.getInt("taxa"));
				pd.setId_produto(rs.getInt("fk_produto"));
				Calendar inicio = Calendar.getInstance();
				inicio.setTime(rs.getDate("data_inicio"));
				pd.setData_inicio(inicio);
				Calendar fim = Calendar.getInstance();
				fim.setTime(rs.getDate("data_fim"));
				pd.setData_fim(fim);
				lista.add(pd);
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
public List<Produtos> buscarProdutosIsentosPlanosOpcao(Date dia_in, Date dia_fim,int m,int op,int s){
	
	List<Produtos> lista = new ArrayList<Produtos>();
	String sql = "";
	if(m == 1 && op == 1)
	{
		sql = "select id_produto as 'fk_produto', produto as 'nome_comercial', preco as 'preco_produto', count(preco) as 'quantidade', sum(qtd) as 'qtd_vendido', sum(total) as 'total_pago', taxa, min(Data_da_facturacao) 'data_inicio', max(Data_da_facturacao) 'data_fim' from vwrelpagamentoprodutos where Data_da_facturacao between ? and ? and (metodo_depagameto = 'Cash') and id_produto = ? group by produto order by produto";
		System.out.println("DataIn and DataFim, Produtos Pagos, Dinheiro and Produtos: "+m+" op: "+op);
	}
	if(m == 1 && op == 2)
	{
		sql = "select id_produto as 'fk_produto', produto as 'nome_comercial', preco as 'preco_produto', count(preco) as 'quantidade', sum(qtd) as 'qtd_vendido', sum(total) as 'total_pago', taxa, min(Data_da_facturacao) 'data_inicio', max(Data_da_facturacao) 'data_fim' from vwrelpagamentoprodutos where Data_da_facturacao between ? and ? and (metodo_depagameto = 'Multicaixa') and id_produto = ? group by produto order by produto";
		System.out.println("DataIn and DataFim, Produtos Pagos, Cartão and Produtos: "+m+" op: "+op);
	}
	if(m == 2 && op == 1)
	{
		sql = "SELECT fp.fk_produto, pf.nome_comercial, fp.preco_produto, sum(fp.qtd_produto) as 'quantidade', sum(fp.tot_pago_produto) as 'total_pago',  min(f.Data_da_facturacao) as 'data_inicio', max(f.Data_da_facturacao) as 'data_fim', m.metodo_depagameto "
			+ "FROM tblfacturarprodutos fp LEFT JOIN tblfactura f ON f.id_factura = fp.FK_factura LEFT JOIN tblprodutofarmaceuticos pf ON pf.id_produto = fp.fk_produto JOIN tblmetododepagameto m ON f.FK_metodo_de_pagameto = m.id_metodo_depagameto WHERE data_da_facturacao between ? and ? and fk_produto = ? and m.metodo_depagameto <> 'Isenção' AND m.metodo_depagameto <> 'Isenção Por Idade' AND m.metodo_depagameto <> 'Seguro de Saude' GROUP BY fp.fk_produto; ";
		System.out.println("XXXXXX33: "+m+" op: "+op);
	}
	if(m == 2 && op == 2){
		sql = "SELECT fp.fk_produto, pf.nome_comercial, fp.preco_produto, sum(fp.qtd_produto) as 'quantidade', sum(fp.tot_pago_produto) as 'total_pago',  min(f.Data_da_facturacao) as 'data_inicio', max(f.Data_da_facturacao) as 'data_fim', m.metodo_depagameto "
			+ "FROM tblfacturarprodutos fp LEFT JOIN tblfactura f ON f.id_factura = fp.FK_factura LEFT JOIN tblprodutofarmaceuticos pf ON pf.id_produto = fp.fk_produto JOIN tblmetododepagameto m ON f.FK_metodo_de_pagameto = m.id_metodo_depagameto WHERE data_da_facturacao between ? and ? and fk_produto = ? and m.metodo_depagameto <> 'Isenção' AND m.metodo_depagameto <> 'Isenção Por Idade' AND m.metodo_depagameto <> 'Seguro de Saude' GROUP BY fp.fk_produto; ";
		System.out.println("XXXXXX44: "+m+" op: "+op);
	}
	
	if(m == 3){
		sql = "SELECT fp.fk_produto, pf.nome_comercial, fp.preco_produto, sum(fp.qtd_produto) as 'quantidade', sum(fp.tot_pago_produto) as 'total_pago',  min(f.Data_da_facturacao) as 'data_inicio', max(f.Data_da_facturacao) as 'data_fim', m.metodo_depagameto "
			+ "FROM tblfacturarprodutos fp LEFT JOIN tblfactura f ON f.id_factura = fp.FK_factura LEFT JOIN tblprodutofarmaceuticos pf ON pf.id_produto = fp.fk_produto JOIN tblmetododepagameto m ON f.FK_metodo_de_pagameto = m.id_metodo_depagameto WHERE data_da_facturacao between ? and ? and fk_produto = ? and m.metodo_depagameto <> 'Isenção' AND m.metodo_depagameto <> 'Isenção Por Idade' AND m.metodo_depagameto <> 'Seguro de Saude' GROUP BY fp.fk_produto; ";
		System.out.println("XXXXXX55: "+m+" op: "+op);
	}
    try {
    	 con = Conexao.getConexao();
		 PreparedStatement ps = con.prepareStatement(sql);	
		 ps.setDate(1, dia_in);
		 ps.setDate(2, dia_fim);
		 ps.setInt(3, s);
		 ResultSet rs = ps.executeQuery();
		 while(rs.next()){
			 Produtos pd = new Produtos();
				pd.setNome_comercial(rs.getString("nome_comercial"));
				pd.setQuantidade(rs.getInt("quantidade"));
				pd.setQtd_geral_vendido(rs.getInt("qtd_vendido"));
				pd.setTotal(rs.getInt("total_pago"));
				pd.setPreco_dvenda(rs.getDouble("preco_produto"));
				pd.setTaxa(rs.getInt("taxa"));
				pd.setId_produto(rs.getInt("fk_produto"));
				Calendar inicio = Calendar.getInstance();
				inicio.setTime(rs.getDate("data_inicio"));
				pd.setData_inicio(inicio);
				Calendar fim = Calendar.getInstance();
				fim.setTime(rs.getDate("data_fim"));
				pd.setData_fim(fim);
				lista.add(pd);
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

public List<Produtos> buscarComProdutoIsentoPlano(Date dia_in, Date dia_fim,int m,String s){
	
	List<Produtos> lista = new ArrayList<Produtos>();
	String sql = "";
	if(m == 1)
	{
		sql = "select id_produto as 'fk_produto', produto as 'nome_comercial', preco as 'preco_produto', count(preco) as 'quantidade', sum(qtd) as 'qtd_vendido', sum(total) as 'total_pago', taxa, min(Data_da_facturacao) 'data_inicio', max(Data_da_facturacao) 'data_fim' from vwrelpagamentoprodutos where Data_da_facturacao between ? and ? and (metodo_depagameto = 'Cash' or metodo_depagameto = 'Multicaixa') and id_produto = ? group by produto order by produto asc";
		System.out.print("DataIn and DataFim, Produtos Pagos and Produtos :"+m);
	}
	if(m == 2)
	{
		sql = "SELECT fp.fk_produto, pf.nome_comercial, fp.preco_produto, sum(fp.qtd_produto) as 'quantidade', sum(fp.tot_pago_produto) as 'total_pago',  min(f.Data_da_facturacao) as 'data_inicio', max(f.Data_da_facturacao) as 'data_fim', m.metodo_depagameto "
			+ "FROM tblfacturarprodutos fp LEFT JOIN tblfactura f ON f.id_factura = fp.FK_factura LEFT JOIN tblprodutofarmaceuticos pf ON pf.id_produto = fp.fk_produto JOIN tblmetododepagameto m ON f.FK_metodo_de_pagameto = m.id_metodo_depagameto WHERE data_da_facturacao between ? and ? and fk_produto = ? and m.metodo_depagameto <> 'Isenção' AND m.metodo_depagameto <> 'Isenção Por Idade' AND m.metodo_depagameto <> 'Seguro de Saude' GROUP BY fp.fk_produto";
	}
	if(m == 3){
		sql = "SELECT fp.fk_produto, pf.nome_comercial, fp.preco_produto, sum(fp.qtd_produto) as 'quantidade', sum(fp.tot_pago_produto) as 'total_pago',  min(f.Data_da_facturacao) as 'data_inicio', max(f.Data_da_facturacao) as 'data_fim', m.metodo_depagameto "
			+ "FROM tblfacturarprodutos fp LEFT JOIN tblfactura f ON f.id_factura = fp.FK_factura LEFT JOIN tblprodutofarmaceuticos pf ON pf.id_produto = fp.fk_produto JOIN tblmetododepagameto m ON f.FK_metodo_de_pagameto = m.id_metodo_depagameto WHERE data_da_facturacao between ? and ? and fk_produto = ? and m.metodo_depagameto <> 'Isenção' AND m.metodo_depagameto <> 'Isenção Por Idade' AND m.metodo_depagameto <> 'Seguro de Saude' GROUP BY fp.fk_produto";
	}
	
    try {
    	 con = Conexao.getConexao();
		 PreparedStatement ps = con.prepareStatement(sql);	
		 ps.setDate(1, dia_in);
		 ps.setDate(2, dia_fim);
		 ps.setString(3, s);
		 ResultSet rs = ps.executeQuery();
		 while(rs.next()){
			 Produtos pd = new Produtos();
				pd.setNome_comercial(rs.getString("nome_comercial"));
				pd.setQuantidade(rs.getInt("quantidade"));
				pd.setQtd_geral_vendido(rs.getInt("qtd_vendido"));
				pd.setTotal(rs.getInt("total_pago"));
				pd.setPreco_dvenda(rs.getDouble("preco_produto"));
				pd.setTaxa(rs.getInt("taxa"));
				pd.setId_produto(rs.getInt("fk_produto"));
				Calendar inicio = Calendar.getInstance();
				inicio.setTime(rs.getDate("data_inicio"));
				pd.setData_inicio(inicio);
				Calendar fim = Calendar.getInstance();
				fim.setTime(rs.getDate("data_fim"));
				pd.setData_fim(fim);
				lista.add(pd);	
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

public List<Produtos> buscarPagamentosProdutoIsentosPlano(Date dia_in, Date dia_fim,int m){
	
	List<Produtos> lista = new ArrayList<Produtos>();
	String sql = "";
	if(m == 1)
	{
		sql = "SELECT fp.fk_produto, pf.nome_comercial, fp.preco_produto, sum(fp.qtd_produto) as 'quantidade', sum(fp.tot_pago_produto) as 'total_pago',  min(f.Data_da_facturacao) as 'data_inicio', max(f.Data_da_facturacao) as 'data_fim', m.metodo_depagameto "
			+ "FROM tblfacturarprodutos fp LEFT JOIN tblfactura f ON f.id_factura = fp.FK_factura LEFT JOIN tblprodutofarmaceuticos pf ON pf.id_produto = fp.fk_produto JOIN tblmetododepagameto m ON f.FK_metodo_de_pagameto = m.id_metodo_depagameto WHERE data_da_facturacao between ? and ? and m.metodo_depagameto <> 'Isenção' AND m.metodo_depagameto <> 'Isenção Por Idade' AND m.metodo_depagameto <> 'Seguro de Saude' GROUP BY fp.fk_produto; ";
		System.out.println("servico sento* :"+m);
	}
	if(m == 2)
	{
		sql = "select id_produto as 'fk_produto', produto as 'nome_comercial', preco as 'preco_produto', count(preco) as 'quantidade', sum(qtd) as 'qtd_vendido', sum(total) as 'total_pago', taxa, min(Data_da_facturacao) 'data_inicio', max(Data_da_facturacao) 'data_fim' from vwrelpagamentoprodutos where Data_da_facturacao between ? and ? and (metodo_depagameto = 'Isenção' or metodo_depagameto = 'Isenção Por Idade') group by produto order by produto";
		System.out.println("DataIn and DataFim-opcção Produtos Isentos: "+m);
	}
	if(m == 3){
		
		sql = "SELECT fp.fk_produto, pf.nome_comercial, fp.preco_produto, sum(fp.qtd_produto) as 'quantidade', sum(fp.tot_pago_produto) as 'total_pago',  min(f.Data_da_facturacao) as 'data_inicio', max(f.Data_da_facturacao) as 'data_fim', m.metodo_depagameto "
			+ "FROM tblfacturarprodutos fp LEFT JOIN tblfactura f ON f.id_factura = fp.FK_factura LEFT JOIN tblprodutofarmaceuticos pf ON pf.id_produto = fp.fk_produto JOIN tblmetododepagameto m ON f.FK_metodo_de_pagameto = m.id_metodo_depagameto WHERE data_da_facturacao between ? and ? and m.metodo_depagameto <> 'Isenção' AND m.metodo_depagameto <> 'Isenção Por Idade' AND m.metodo_depagameto <> 'Seguro de Saude' GROUP BY fp.fk_produto; ";
		System.out.println("servico sento isencao atribuicao: ***+*** :"+m);
		}
	
    try {
    	 con = Conexao.getConexao();
		 PreparedStatement ps = con.prepareStatement(sql);	
		 ps.setDate(1, dia_in);
		 ps.setDate(2, dia_fim);
		 ResultSet rs = ps.executeQuery();
		 while(rs.next()){
			 Produtos pd = new Produtos();
				pd.setNome_comercial(rs.getString("nome_comercial"));
				pd.setQuantidade(rs.getInt("quantidade"));
				pd.setQtd_geral_vendido(rs.getInt("qtd_vendido"));
				pd.setTotal(rs.getInt("total_pago"));
				pd.setPreco_dvenda(rs.getDouble("preco_produto"));
				pd.setTaxa(rs.getInt("taxa"));
				pd.setId_produto(rs.getInt("fk_produto"));
				Calendar inicio = Calendar.getInstance();
				inicio.setTime(rs.getDate("data_inicio"));
				pd.setData_inicio(inicio);
				Calendar fim = Calendar.getInstance();
				fim.setTime(rs.getDate("data_fim"));
				pd.setData_fim(fim);
				lista.add(pd);
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

public List<Produtos> buscarPagamentosProdutoIsentosOpcao(Date dia_in, Date dia_fim,int m,int op){
	
	List<Produtos> lista = new ArrayList<Produtos>();
	String sql = "";
	if(m == 1 && op == 1){
		sql = "SELECT fp.fk_produto, pf.nome_comercial, fp.preco_produto, sum(fp.qtd_produto) as 'quantidade', sum(fp.tot_pago_produto) as 'total_pago',  min(f.Data_da_facturacao) as 'data_inicio', max(f.Data_da_facturacao) as 'data_fim', m.metodo_depagameto "
			+ "FROM tblfacturarprodutos fp LEFT JOIN tblfactura f ON f.id_factura = fp.FK_factura LEFT JOIN tblprodutofarmaceuticos pf ON pf.id_produto = fp.fk_produto JOIN tblmetododepagameto m ON f.FK_metodo_de_pagameto = m.id_metodo_depagameto WHERE data_da_facturacao between ? and ? and m.metodo_depagameto <> 'Isenção' AND m.metodo_depagameto <> 'Isenção Por Idade' AND m.metodo_depagameto <> 'Seguro de Saude' GROUP BY fp.fk_produto asc ";
		System.out.println("LLLLLLL11: "+m+" op: "+op);
	}
	if(m == 1 && op == 2){
		sql = "SELECT fp.fk_produto, pf.nome_comercial, fp.preco_produto, sum(fp.qtd_produto) as 'quantidade', sum(fp.tot_pago_produto) as 'total_pago',  min(f.Data_da_facturacao) as 'data_inicio', max(f.Data_da_facturacao) as 'data_fim', m.metodo_depagameto "
			+ "FROM tblfacturarprodutos fp LEFT JOIN tblfactura f ON f.id_factura = fp.FK_factura LEFT JOIN tblprodutofarmaceuticos pf ON pf.id_produto = fp.fk_produto JOIN tblmetododepagameto m ON f.FK_metodo_de_pagameto = m.id_metodo_depagameto WHERE data_da_facturacao between ? and ? and m.metodo_depagameto <> 'Isenção' AND m.metodo_depagameto <> 'Isenção Por Idade' AND m.metodo_depagameto <> 'Seguro de Saude' GROUP BY fp.fk_produto asc ";
		System.out.println("LLLLLLL22: "+m+" op: "+op);
	}
	if(m == 2 && op == 1)
	{
		sql = "select id_produto as 'fk_produto', produto as 'nome_comercial', preco as 'preco_produto', count(preco) as 'quantidade', sum(qtd) as 'qtd_vendido', sum(total) as 'total_pago', taxa, min(Data_da_facturacao) 'data_inicio', max(Data_da_facturacao) 'data_fim' from vwrelpagamentoprodutos where Data_da_facturacao between ? and ? and metodo_depagameto = 'Isenção Por Idade' group by produto order by produto asc";
		System.out.println("DataIn and DataFim, Produtos Isentos and Idade: "+m+" op: "+op);
	}
	if(m == 2 && op == 2)
	{
		sql = "select id_produto as 'fk_produto', produto as 'nome_comercial', preco as 'preco_produto', count(preco) as 'quantidade', sum(qtd) as 'qtd_vendido', sum(total) as 'total_pago', taxa, min(Data_da_facturacao) 'data_inicio', max(Data_da_facturacao) 'data_fim' from vwrelpagamentoprodutos where Data_da_facturacao between ? and ? and metodo_depagameto = 'Isenção' group by produto order by produto asc";
		System.out.println("DataIn and DataFim, Produtos Isentos and Isenção Atríbuida: "+m+" op: "+op);
	}
	
	
    try {
    	 con = Conexao.getConexao();
		 PreparedStatement ps = con.prepareStatement(sql);	
		 ps.setDate(1, dia_in);
		 ps.setDate(2, dia_fim);
		 ResultSet rs = ps.executeQuery();
		 while(rs.next()){
			 Produtos pd = new Produtos();
				pd.setNome_comercial(rs.getString("nome_comercial"));
				pd.setQuantidade(rs.getInt("quantidade"));
				pd.setQtd_geral_vendido(rs.getInt("qtd_vendido"));
				pd.setTotal(rs.getInt("total_pago"));
				pd.setPreco_dvenda(rs.getDouble("preco_produto"));
				pd.setTaxa(rs.getInt("taxa"));
				pd.setId_produto(rs.getInt("fk_produto"));
				Calendar inicio = Calendar.getInstance();
				inicio.setTime(rs.getDate("data_inicio"));
				pd.setData_inicio(inicio);
				Calendar fim = Calendar.getInstance();
				fim.setTime(rs.getDate("data_fim"));
				pd.setData_fim(fim);
				lista.add(pd);
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

public List<Produtos> buscarComPagamentoProdutoIsentoPlano(Date dia_in, Date dia_fim,int m,String s){
	
	List<Produtos> lista = new ArrayList<Produtos>();
	String sql = "";
	if(m == 1){
		sql = "SELECT fp.fk_produto, pf.nome_comercial, fp.preco_produto, sum(fp.qtd_produto) as 'quantidade', sum(fp.tot_pago_produto) as 'total_pago',  min(f.Data_da_facturacao) as 'data_inicio', max(f.Data_da_facturacao) as 'data_fim', m.metodo_depagameto "
			+ "FROM tblfacturarprodutos fp LEFT JOIN tblfactura f ON f.id_factura = fp.FK_factura LEFT JOIN tblprodutofarmaceuticos pf ON pf.id_produto = fp.fk_produto JOIN tblmetododepagameto m ON f.FK_metodo_de_pagameto = m.id_metodo_depagameto WHERE data_da_facturacao between ? and ? and fk_produto = ? and m.metodo_depagameto <> 'Isenção' AND m.metodo_depagameto <> 'Isenção Por Idade' AND m.metodo_depagameto <> 'Seguro de Saude' GROUP BY fp.fk_produto ";
	System.out.println("QQQQ1: "+m);
	}
	if(m == 2)
	{
		sql = "select id_produto as 'fk_produto', produto as 'nome_comercial', preco as 'preco_produto', count(preco) as 'quantidade', sum(qtd) as 'qtd_vendido', sum(total) as 'total_pago', taxa, min(Data_da_facturacao) 'data_inicio', max(Data_da_facturacao) 'data_fim' from vwrelpagamentoprodutos where Data_da_facturacao between ? and ? and (metodo_depagameto = 'Isenção' or metodo_depagameto = 'Isenção Por Idade') and id_produto = ? group by produto order by produto";
		System.out.print("DataIn and DataFim, Produtos Isentos and Produtos :"+m);
	}
	if(m == 3){
		sql = "SELECT fp.fk_produto, pf.nome_comercial, fp.preco_produto, sum(fp.qtd_produto) as 'quantidade', sum(fp.tot_pago_produto) as 'total_pago',  min(f.Data_da_facturacao) as 'data_inicio', max(f.Data_da_facturacao) as 'data_fim', m.metodo_depagameto "
			+ "FROM tblfacturarprodutos fp LEFT JOIN tblfactura f ON f.id_factura = fp.FK_factura LEFT JOIN tblprodutofarmaceuticos pf ON pf.id_produto = fp.fk_produto JOIN tblmetododepagameto m ON f.FK_metodo_de_pagameto = m.id_metodo_depagameto WHERE data_da_facturacao between ? and ? and fk_produto = ? and m.metodo_depagameto <> 'Isenção' AND m.metodo_depagameto <> 'Isenção Por Idade' AND m.metodo_depagameto <> 'Seguro de Saude' GROUP BY fp.fk_produto; ";
		System.out.println("QQQQ3: "+m);
	}
	
    try {
    	 con = Conexao.getConexao();
		 PreparedStatement ps = con.prepareStatement(sql);	
		 ps.setDate(1, dia_in);
		 ps.setDate(2, dia_fim);
		 ps.setString(3, s);
		 ResultSet rs = ps.executeQuery();
		 while(rs.next()){
			 Produtos pd = new Produtos();
				pd.setNome_comercial(rs.getString("nome_comercial"));
				pd.setQuantidade(rs.getInt("quantidade"));
				pd.setQtd_geral_vendido(rs.getInt("qtd_vendido"));
				pd.setTotal(rs.getInt("total_pago"));
				pd.setPreco_dvenda(rs.getDouble("preco_produto"));
				pd.setTaxa(rs.getInt("taxa"));
				pd.setId_produto(rs.getInt("fk_produto"));
				Calendar inicio = Calendar.getInstance();
				inicio.setTime(rs.getDate("data_inicio"));
				pd.setData_inicio(inicio);
				Calendar fim = Calendar.getInstance();
				fim.setTime(rs.getDate("data_fim"));
				pd.setData_fim(fim);
				lista.add(pd);
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

public List<Produtos> buscarPagamentosIsentosPlanosOpcaoProduto(Date dia_in, Date dia_fim,int m,int op,int s){
	
	List<Produtos> lista = new ArrayList<Produtos>();
	String sql = "";
	if(m == 1 && op == 1){
		sql = "SELECT fp.fk_produto, pf.nome_comercial, fp.preco_produto, sum(fp.qtd_produto) as 'quantidade', sum(fp.tot_pago_produto) as 'total_pago',  min(f.Data_da_facturacao) as 'data_inicio', max(f.Data_da_facturacao) as 'data_fim', m.metodo_depagameto "
			+ "FROM tblfacturarprodutos fp LEFT JOIN tblfactura f ON f.id_factura = fp.FK_factura LEFT JOIN tblprodutofarmaceuticos pf ON pf.id_produto = fp.fk_produto JOIN tblmetododepagameto m ON f.FK_metodo_de_pagameto = m.id_metodo_depagameto WHERE data_da_facturacao between ? and ? and fk_produto = ? and m.metodo_depagameto <> 'Isenção' AND m.metodo_depagameto <> 'Isenção Por Idade' AND m.metodo_depagameto <> 'Seguro de Saude' GROUP BY fp.fk_produto;  ";
		System.out.println("YYYY1: "+ m);
	}
	if(m == 1 && op == 2)
	{
		sql = "SELECT fp.fk_produto, pf.nome_comercial, fp.preco_produto, sum(fp.qtd_produto) as 'quantidade', sum(fp.tot_pago_produto) as 'total_pago',  min(f.Data_da_facturacao) as 'data_inicio', max(f.Data_da_facturacao) as 'data_fim', m.metodo_depagameto "
			+ "FROM tblfacturarprodutos fp LEFT JOIN tblfactura f ON f.id_factura = fp.FK_factura LEFT JOIN tblprodutofarmaceuticos pf ON pf.id_produto = fp.fk_produto JOIN tblmetododepagameto m ON f.FK_metodo_de_pagameto = m.id_metodo_depagameto WHERE data_da_facturacao between ? and ? and fk_produto = ? and m.metodo_depagameto <> 'Isenção' AND m.metodo_depagameto <> 'Isenção Por Idade' AND m.metodo_depagameto <> 'Seguro de Saude' GROUP BY fp.fk_produto;  ";
		System.out.println("YYYY2: "+ m);
	}
	if(m == 2 && op == 1)
	{
		sql = "select id_produto as 'fk_produto', produto as 'nome_comercial', preco as 'preco_produto', count(preco) as 'quantidade', sum(qtd) as 'qtd_vendido', sum(total) as 'total_pago', taxa, min(Data_da_facturacao) 'data_inicio', max(Data_da_facturacao) 'data_fim' from vwrelpagamentoprodutos where Data_da_facturacao between ? and ? and (metodo_depagameto = 'Isenção Por Idade') and id_produto = ? group by produto order by produto";
		System.out.println("DataIn and DataFim, Produtos Isentos, Idade and Produtos: "+m+" op: "+op);
	}
	if(m == 2 && op == 2)
	{
		sql = "select id_produto as 'fk_produto', produto as 'nome_comercial', preco as 'preco_produto', count(preco) as 'quantidade', sum(qtd) as 'qtd_vendido', sum(total) as 'total_pago', taxa, min(Data_da_facturacao) 'data_inicio', max(Data_da_facturacao) 'data_fim' from vwrelpagamentoprodutos where Data_da_facturacao between ? and ? and (metodo_depagameto = 'Isenção') and id_produto = ? group by produto order by produto";
		System.out.println("DataIn and DataFim, Produtos Isentos, Isenção Atríbuida and Produtos: "+m+" op: "+op);
	}
	if(m == 3){
		sql = "SELECT fp.fk_produto, pf.nome_comercial, fp.preco_produto, sum(fp.qtd_produto) as 'quantidade', sum(fp.tot_pago_produto) as 'total_pago',  min(f.Data_da_facturacao) as 'data_inicio', max(f.Data_da_facturacao) as 'data_fim', m.metodo_depagameto "
			+ "FROM tblfacturarprodutos fp LEFT JOIN tblfactura f ON f.id_factura = fp.FK_factura LEFT JOIN tblprodutofarmaceuticos pf ON pf.id_produto = fp.fk_produto JOIN tblmetododepagameto m ON f.FK_metodo_de_pagameto = m.id_metodo_depagameto WHERE data_da_facturacao between ? and ? and fk_produto = ? and m.metodo_depagameto <> 'Cash' and m.metodo_depagameto <> 'Multicaixa' AND m.metodo_depagameto <> 'Isenção Por Idade' AND m.metodo_depagameto <> 'Seguro de Saude' GROUP BY fp.fk_produto;  ";
		System.out.println("YYYY5: "+ m);
	}
    try {
    	 con = Conexao.getConexao();
		 PreparedStatement ps = con.prepareStatement(sql);	
		 ps.setDate(1, dia_in);
		 ps.setDate(2, dia_fim);
		 ps.setInt(3, s);
		 ResultSet rs = ps.executeQuery();
		 while(rs.next()){
			 Produtos pd = new Produtos();
				pd.setNome_comercial(rs.getString("nome_comercial"));
				pd.setQuantidade(rs.getInt("quantidade"));
				pd.setQtd_geral_vendido(rs.getInt("qtd_vendido"));
				pd.setTotal(rs.getInt("total_pago"));
				pd.setPreco_dvenda(rs.getDouble("preco_produto"));
				pd.setTaxa(rs.getInt("taxa"));
				pd.setId_produto(rs.getInt("fk_produto"));
				Calendar inicio = Calendar.getInstance();
				inicio.setTime(rs.getDate("data_inicio"));
				pd.setData_inicio(inicio);
				Calendar fim = Calendar.getInstance();
				fim.setTime(rs.getDate("data_fim"));
				pd.setData_fim(fim);
				lista.add(pd);
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

public List<Produtos> buscarPagamentosIsentosPlanosProduto(Date dia_in, Date dia_fim,int m){
	
	List<Produtos> lista = new ArrayList<Produtos>();
	String sql = "";
	if(m == 1)
	{
		sql = "SELECT fp.fk_produto, pf.nome_comercial, fp.preco_produto, sum(fp.qtd_produto) as 'quantidade', sum(fp.tot_pago_produto) as 'total_pago',  min(f.Data_da_facturacao) as 'data_inicio', max(f.Data_da_facturacao) as 'data_fim', m.metodo_depagameto FROM tblfacturarprodutos fp LEFT JOIN tblfactura f ON f.id_factura = fp.FK_factura LEFT JOIN tblprodutofarmaceuticos pf ON pf.id_produto = fp.fk_produto JOIN tblmetododepagameto m ON f.FK_metodo_de_pagameto = m.id_metodo_depagameto "
			+ "WHERE data_da_facturacao between ? and ? and fk_produto = ? and m.metodo_depagameto <> 'Cash' and m.metodo_depagameto <> 'Multicaixa' AND m.metodo_depagameto <> 'Isenção Por Idade' GROUP BY fp.fk_produto; ";
		System.out.println("opcao zildo P"+m);
	}
	if(m == 2)
	{
		sql = "SELECT fp.fk_produto, pf.nome_comercial, fp.preco_produto, sum(fp.qtd_produto) as 'quantidade', sum(fp.tot_pago_produto) as 'total_pago',  min(f.Data_da_facturacao) as 'data_inicio', max(f.Data_da_facturacao) as 'data_fim', m.metodo_depagameto FROM tblfacturarprodutos fp LEFT JOIN tblfactura f ON f.id_factura = fp.FK_factura LEFT JOIN tblprodutofarmaceuticos pf ON pf.id_produto = fp.fk_produto JOIN tblmetododepagameto m ON f.FK_metodo_de_pagameto = m.id_metodo_depagameto "
			+ "WHERE data_da_facturacao between ? and ? and fk_produto = ? and m.metodo_depagameto <> 'Isenção' AND m.metodo_depagameto <> 'Isenção Por Idade' AND m.metodo_depagameto <> 'Seguro de Saude' GROUP BY fp.fk_produto; ";
		System.out.println("opcao zildo D"+m);
	}
	if(m == 3)
	{
		sql = "select id_produto as 'fk_produto', produto as 'nome_comercial', preco as 'preco_produto', count(preco) as 'quantidade', sum(qtd) as 'qtd_vendido', sum(total) as 'total_pago', taxa, min(Data_da_facturacao) 'data_inicio', max(Data_da_facturacao) 'data_fim' from vwrelpagamentoprodutos where Data_da_facturacao between ? and ? and (metodo_depagameto = 'Seguro de Saúde') group by produto order by produto";
		System.out.println("DataIn and DataFim-opcção Plano de Saúde: "+m);
	}
	
    try {
    	 con = Conexao.getConexao();
		 PreparedStatement ps = con.prepareStatement(sql);	
		 ps.setDate(1, dia_in);
		 ps.setDate(2, dia_fim);
		 ResultSet rs = ps.executeQuery();
		 while(rs.next()){
			 Produtos pd = new Produtos();
				pd.setNome_comercial(rs.getString("nome_comercial"));
				pd.setQuantidade(rs.getInt("quantidade"));
				pd.setQtd_geral_vendido(rs.getInt("qtd_vendido"));
				pd.setTotal(rs.getInt("total_pago"));
				pd.setPreco_dvenda(rs.getDouble("preco_produto"));
				pd.setTaxa(rs.getInt("taxa"));
				pd.setId_produto(rs.getInt("fk_produto"));
				Calendar inicio = Calendar.getInstance();
				inicio.setTime(rs.getDate("data_inicio"));
				pd.setData_inicio(inicio);
				Calendar fim = Calendar.getInstance();
				fim.setTime(rs.getDate("data_fim"));
				pd.setData_fim(fim);
				lista.add(pd);
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

public List<Produtos> buscarPagamentosProdutosIsentosPlanosOpcao(Date dia_in, Date dia_fim,int m,int op,int sg){
	
	List<Produtos> lista = new ArrayList<Produtos>();
	String sql = "";
	if(m == 1 && op == 1)
	{
		sql = "SELECT fp.fk_produto, pf.nome_comercial, fp.preco_produto, sum(fp.qtd_produto) as 'quantidade', sum(fp.tot_pago_produto) as 'total_pago',  min(f.Data_da_facturacao) as 'data_inicio', max(f.Data_da_facturacao) as 'data_fim', m.metodo_depagameto FROM tblfacturarprodutos fp LEFT JOIN tblfactura f ON f.id_factura = fp.FK_factura LEFT JOIN tblprodutofarmaceuticos pf ON pf.id_produto = fp.fk_produto JOIN tblmetododepagameto m ON f.FK_metodo_de_pagameto = m.id_metodo_depagameto "
			+ "WHERE data_da_facturacao between ? and ? and fk_produto = ? and m.metodo_depagameto <> 'Isenção' AND m.metodo_depagameto <> 'Isenção Por Idade' AND m.metodo_depagameto <> 'Seguro de Saude' AND m.metodo_depagameto <> 'Multicaixa' GROUP BY fp.fk_produto;";
		System.out.println("Servicos pagos dinheiro servico: "+m+"op:"+op);
	}
	if(m == 1 && op == 2)
	{
		sql = "SELECT fp.fk_produto, pf.nome_comercial, fp.preco_produto, sum(fp.qtd_produto) as 'quantidade', sum(fp.tot_pago_produto) as 'total_pago',  min(f.Data_da_facturacao) as 'data_inicio', max(f.Data_da_facturacao) as 'data_fim', m.metodo_depagameto FROM tblfacturarprodutos fp LEFT JOIN tblfactura f ON f.id_factura = fp.FK_factura LEFT JOIN tblprodutofarmaceuticos pf ON pf.id_produto = fp.fk_produto JOIN tblmetododepagameto m ON f.FK_metodo_de_pagameto = m.id_metodo_depagameto "
			+ "WHERE data_da_facturacao between ? and ? and fk_produto = ? and m.metodo_depagameto <> 'Isenção' AND m.metodo_depagameto <> 'Isenção Por Idade' AND m.metodo_depagameto <> 'Seguro de Saude' AND m.metodo_depagameto <> 'Cash' GROUP BY fp.fk_produto;";
		System.out.println("Servicos pagos dinheiro servico___: "+m+"op:"+op);
	}
	if(m == 2 && op == 1)
	{
		sql = "SELECT fp.fk_produto, pf.nome_comercial, fp.preco_produto, sum(fp.qtd_produto) as 'quantidade', sum(fp.tot_pago_produto) as 'total_pago',  min(f.Data_da_facturacao) as 'data_inicio', max(f.Data_da_facturacao) as 'data_fim', m.metodo_depagameto FROM tblfacturarprodutos fp LEFT JOIN tblfactura f ON f.id_factura = fp.FK_factura LEFT JOIN tblprodutofarmaceuticos pf ON pf.id_produto = fp.fk_produto JOIN tblmetododepagameto m ON f.FK_metodo_de_pagameto = m.id_metodo_depagameto "
			+ "WHERE data_da_facturacao between ? and ? and fk_produto = ? and m.metodo_depagameto <> 'Isenção' AND m.metodo_depagameto <> 'Isenção Por Idade' AND m.metodo_depagameto <> 'Seguro de Saude' GROUP BY fp.fk_produto; ";
		System.out.println("Servicos pagos dinheiro servicoLLLL: "+m+"op:"+op);
	}
	if(m == 2 && op == 2)
	{
		sql = "SELECT fp.fk_produto, pf.nome_comercial, fp.preco_produto, sum(fp.qtd_produto) as 'quantidade', sum(fp.tot_pago_produto) as 'total_pago',  min(f.Data_da_facturacao) as 'data_inicio', max(f.Data_da_facturacao) as 'data_fim', m.metodo_depagameto FROM tblfacturarprodutos fp LEFT JOIN tblfactura f ON f.id_factura = fp.FK_factura LEFT JOIN tblprodutofarmaceuticos pf ON pf.id_produto = fp.fk_produto JOIN tblmetododepagameto m ON f.FK_metodo_de_pagameto = m.id_metodo_depagameto "
			+ "WHERE data_da_facturacao between ? and ? and fk_produto = ? and m.metodo_depagameto <> 'Isenção' AND m.metodo_depagameto <> 'Isenção Por Idade' AND m.metodo_depagameto <> 'Seguro de Saude' GROUP BY fp.fk_produto; ";
		System.out.println("Servicos pagos dinheiro servico:xxxx "+m+"op:"+op);
	}
	
	if(m == 3)
	{
		sql = "select id_produto as 'fk_produto', produto as 'nome_comercial', preco as 'preco_produto', count(preco) as 'quantidade', sum(qtd) as 'qtd_vendido', sum(total) as 'total_pago', taxa, min(Data_da_facturacao) 'data_inicio', max(Data_da_facturacao) 'data_fim' from vwrelpagamentoprodutos where Data_da_facturacao between ? and ? and (metodo_depagameto = 'Seguro de Saúde') and id_produto = ? group by produto order by produto";
		System.out.print("DataIn and DataFim, Plano de Saúde and Produtos :"+m);
	}
    try {
    	 con = Conexao.getConexao();
		 PreparedStatement ps = con.prepareStatement(sql);	
		 ps.setDate(1, dia_in);
		 ps.setDate(2, dia_fim);
		 ps.setInt(3, sg);
		 ResultSet rs = ps.executeQuery();
		 while(rs.next()){
			 Produtos pd = new Produtos();
				pd.setNome_comercial(rs.getString("nome_comercial"));
				pd.setQuantidade(rs.getInt("quantidade"));
				pd.setQtd_geral_vendido(rs.getInt("qtd_vendido"));
				pd.setTotal(rs.getInt("total_pago"));
				pd.setPreco_dvenda(rs.getDouble("preco_produto"));
				pd.setTaxa(rs.getInt("taxa"));
				pd.setId_produto(rs.getInt("fk_produto"));
				Calendar inicio = Calendar.getInstance();
				inicio.setTime(rs.getDate("data_inicio"));
				pd.setData_inicio(inicio);
				Calendar fim = Calendar.getInstance();
				fim.setTime(rs.getDate("data_fim"));
				pd.setData_fim(fim);
				lista.add(pd);
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
//---------------------------------------------------------- FINAL PAGAMENTO PRODUTO
//---------------------------------------------------------- COMEï¿½O PAGAMENTO ISENï¿½ï¿½O
public List<Produtos> buscarPagamentoIsencao(){
	
	List<Produtos> lista = new ArrayList<Produtos>();
    String sql = "select  tipode_servico_facturado as 'servico', FK_tipo_deservico, count(FK_tipo_deservico) as 'qtd', sum(t_isen_produto) as 'total', min(Data_da_facturacao) as 'data_inicio', max(Data_da_facturacao) as 'data_fim' from vwrelpagamentoisencao where Data_da_facturacao = curdate() group by FK_tipo_deservico";
    try {
    	 con = Conexao.getConexao();
		 PreparedStatement ps = con.prepareStatement(sql);		
		 ResultSet rs = ps.executeQuery();
		 while(rs.next()){
			 Produtos pd = new Produtos();
				pd.setFK_tipo_deservico(rs.getInt("FK_tipo_deservico"));
				pd.setTipode_servico_facturado(rs.getString("servico"));
				pd.setQuantidade(rs.getInt("qtd"));
				pd.setPreco(rs.getDouble("total"));
				Calendar inicio = Calendar.getInstance();
				inicio.setTime(rs.getDate("data_inicio"));
				pd.setData_inicio(inicio);
				Calendar fim = Calendar.getInstance();
				fim.setTime(rs.getDate("data_fim"));
				pd.setData_fim(fim);
				lista.add(pd);
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

public List<Produtos> buscarPagamentoIsencaoPorData(Date dia_in, Date dia_fim){
	
	List<Produtos> lista = new ArrayList<Produtos>();
    String sql = "select  tipode_servico_facturado as 'servico', FK_tipo_deservico, count(FK_tipo_deservico) as 'qtd', sum(t_isen_produto) as 'total', min(Data_da_facturacao) as 'data_inicio', max(Data_da_facturacao) as 'data_fim' from vwrelpagamentoisencao WHERE data_da_facturacao between ? and ? group by FK_tipo_deservico";
    try {
    	 con = Conexao.getConexao();
		 PreparedStatement ps = con.prepareStatement(sql);	
		 ps.setDate(1, dia_in);
		 ps.setDate(2, dia_fim);
		 ResultSet rs = ps.executeQuery();
		 while(rs.next()){
			 Produtos pd = new Produtos();
			 	pd.setFK_tipo_deservico(rs.getInt("FK_tipo_deservico"));
				pd.setTipode_servico_facturado(rs.getString("servico"));
				pd.setQuantidade(rs.getInt("qtd"));
				pd.setPreco(rs.getDouble("total"));
				Calendar inicio = Calendar.getInstance();
				inicio.setTime(rs.getDate("data_inicio"));
				pd.setData_inicio(inicio);
				Calendar fim = Calendar.getInstance();
				fim.setTime(rs.getDate("data_fim"));
				pd.setData_fim(fim);
				lista.add(pd);
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

public List<Produtos> buscarPagamentoIsencaoAll(Date dia_in, Date dia_fim, int op){
	
	List<Produtos> lista = new ArrayList<Produtos>();
    String sql = "";
    if(op == 1){
    	   sql = "select  tipode_servico_facturado as 'servico', FK_tipo_deservico, count(FK_tipo_deservico) as 'qtd', sum(t_isen_produto) as 'total', min(Data_da_facturacao) as 'data_inicio', max(Data_da_facturacao) as 'data_fim' from vwrelpagamentoisencao WHERE data_da_facturacao between ? and ? and metodo_depagameto <> 'Isenção Por Idade' group by FK_tipo_deservico;";
    }
    if(op == 2){
 	   sql = "select  tipode_servico_facturado as 'servico', FK_tipo_deservico, count(FK_tipo_deservico) as 'qtd', sum(t_isen_produto) as 'total', min(Data_da_facturacao) as 'data_inicio', max(Data_da_facturacao) as 'data_fim' from vwrelpagamentoisencao WHERE data_da_facturacao between ? and ? and metodo_depagameto <> 'Isenção' group by FK_tipo_deservico;";
    }
    try {
    	 con = Conexao.getConexao();
		 PreparedStatement ps = con.prepareStatement(sql);	
		 ps.setDate(1, dia_in);
		 ps.setDate(2, dia_fim);
		 ResultSet rs = ps.executeQuery();
		 while(rs.next()){
			 Produtos pd = new Produtos();
			 	pd.setFK_tipo_deservico(rs.getInt("FK_tipo_deservico"));
				pd.setTipode_servico_facturado(rs.getString("servico"));
				pd.setQuantidade(rs.getInt("qtd"));
				pd.setPreco(rs.getDouble("total"));
				Calendar inicio = Calendar.getInstance();
				inicio.setTime(rs.getDate("data_inicio"));
				pd.setData_inicio(inicio);
				Calendar fim = Calendar.getInstance();
				fim.setTime(rs.getDate("data_fim"));
				pd.setData_fim(fim);
				lista.add(pd);
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

public List<Produtos> buscarPagamentoIsencaoConsulta(Date dia_in, Date dia_fim, int op, int cons){
	
	List<Produtos> lista = new ArrayList<Produtos>();
    String sql = "";
    if(op == 1){
    	   sql = "select  tipode_servico_facturado as 'servico', FK_tipo_deservico, count(FK_tipo_deservico) as 'qtd', sum(t_isen_produto) as 'total', min(Data_da_facturacao) as 'data_inicio', max(Data_da_facturacao) as 'data_fim' from vwrelpagamentoisencao WHERE data_da_facturacao between ? and ? and FK_tipo_deservico = ? and metodo_depagameto <> 'Isenção Por Idade' group by FK_tipo_deservico;";
    }
    if(op == 2){
 	   sql = "select  tipode_servico_facturado as 'servico', FK_tipo_deservico, count(FK_tipo_deservico) as 'qtd', sum(t_isen_produto) as 'total', min(Data_da_facturacao) as 'data_inicio', max(Data_da_facturacao) as 'data_fim' from vwrelpagamentoisencao WHERE data_da_facturacao between ? and ? and FK_tipo_deservico = ? and metodo_depagameto <> 'Isenção' group by FK_tipo_deservico;";
    }
    try {
    	 con = Conexao.getConexao();
		 PreparedStatement ps = con.prepareStatement(sql);	
		 ps.setDate(1, dia_in);
		 ps.setDate(2, dia_fim);
		 ps.setInt(3, cons);
		 ResultSet rs = ps.executeQuery();
		 while(rs.next()){
			 Produtos pd = new Produtos();
			 	pd.setFK_tipo_deservico(rs.getInt("FK_tipo_deservico"));
				pd.setTipode_servico_facturado(rs.getString("servico"));
				pd.setQuantidade(rs.getInt("qtd"));
				pd.setPreco(rs.getDouble("total"));
				Calendar inicio = Calendar.getInstance();
				inicio.setTime(rs.getDate("data_inicio"));
				pd.setData_inicio(inicio);
				Calendar fim = Calendar.getInstance();
				fim.setTime(rs.getDate("data_fim"));
				pd.setData_fim(fim);
				lista.add(pd);
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

public List<Produtos> buscarPagamentoIsencaoExame(Date dia_in, Date dia_fim, int op, int cons){
	
	List<Produtos> lista = new ArrayList<Produtos>();
    String sql = "";
    if(op == 1){
    	   sql = "select  tipode_servico_facturado as 'servico', FK_tipo_deservico, count(FK_tipo_deservico) as 'qtd', sum(t_isen_produto) as 'total', min(Data_da_facturacao) as 'data_inicio', max(Data_da_facturacao) as 'data_fim' from vwrelpagamentoisencao WHERE data_da_facturacao between ? and ? and FK_tipo_deservico = ? and metodo_depagameto <> 'Isenção Por Idade' group by FK_tipo_deservico;";
    }
    if(op == 2){
 	   sql = "select  tipode_servico_facturado as 'servico', FK_tipo_deservico, count(FK_tipo_deservico) as 'qtd', sum(t_isen_produto) as 'total', min(Data_da_facturacao) as 'data_inicio', max(Data_da_facturacao) as 'data_fim' from vwrelpagamentoisencao WHERE data_da_facturacao between ? and ? and FK_tipo_deservico = ? and metodo_depagameto <> 'Isenção' group by FK_tipo_deservico;";
    }
    try {
    	 con = Conexao.getConexao();
		 PreparedStatement ps = con.prepareStatement(sql);	
		 ps.setDate(1, dia_in);
		 ps.setDate(2, dia_fim);
		 ps.setInt(3, cons);
		 ResultSet rs = ps.executeQuery();
		 while(rs.next()){
			 Produtos pd = new Produtos();
			 	pd.setFK_tipo_deservico(rs.getInt("FK_tipo_deservico"));
				pd.setTipode_servico_facturado(rs.getString("servico"));
				pd.setQuantidade(rs.getInt("qtd"));
				pd.setPreco(rs.getDouble("total"));
				Calendar inicio = Calendar.getInstance();
				inicio.setTime(rs.getDate("data_inicio"));
				pd.setData_inicio(inicio);
				Calendar fim = Calendar.getInstance();
				fim.setTime(rs.getDate("data_fim"));
				pd.setData_fim(fim);
				lista.add(pd);
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

public List<Produtos> buscarPagamentoIsencaoServicoGerais(Date dia_in, Date dia_fim, int op, int cons){
	
	List<Produtos> lista = new ArrayList<Produtos>();
    String sql = "";
    if(op == 1){
    	   sql = "select  tipode_servico_facturado as 'servico', FK_tipo_deservico, count(FK_tipo_deservico) as 'qtd', sum(t_isen_produto) as 'total', min(Data_da_facturacao) as 'data_inicio', max(Data_da_facturacao) as 'data_fim' from vwrelpagamentoisencao WHERE data_da_facturacao between ? and ? and FK_tipo_deservico = ? and metodo_depagameto <> 'Isenção Por Idade' group by FK_tipo_deservico;";
    }
    if(op == 2){
 	   sql = "select  tipode_servico_facturado as 'servico', FK_tipo_deservico, count(FK_tipo_deservico) as 'qtd', sum(t_isen_produto) as 'total', min(Data_da_facturacao) as 'data_inicio', max(Data_da_facturacao) as 'data_fim' from vwrelpagamentoisencao WHERE data_da_facturacao between ? and ? and FK_tipo_deservico = ? and metodo_depagameto <> 'Isenção' group by FK_tipo_deservico;";
    }
    try {
    	 con = Conexao.getConexao();
		 PreparedStatement ps = con.prepareStatement(sql);	
		 ps.setDate(1, dia_in);
		 ps.setDate(2, dia_fim);
		 ps.setInt(3, cons);
		 ResultSet rs = ps.executeQuery();
		 while(rs.next()){
			 Produtos pd = new Produtos();
			 	pd.setFK_tipo_deservico(rs.getInt("FK_tipo_deservico"));
				pd.setTipode_servico_facturado(rs.getString("servico"));
				pd.setQuantidade(rs.getInt("qtd"));
				pd.setPreco(rs.getDouble("total"));
				Calendar inicio = Calendar.getInstance();
				inicio.setTime(rs.getDate("data_inicio"));
				pd.setData_inicio(inicio);
				Calendar fim = Calendar.getInstance();
				fim.setTime(rs.getDate("data_fim"));
				pd.setData_fim(fim);
				lista.add(pd);
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

public List<Produtos> buscarPagamentoIsencaoFarmaco(Date dia_in, Date dia_fim, int op, int cons){
	
	List<Produtos> lista = new ArrayList<Produtos>();
    String sql = "";
    if(op == 1){
    	   sql = "select  tipode_servico_facturado as 'servico', FK_tipo_deservico, count(FK_tipo_deservico) as 'qtd', sum(t_isen_produto) as 'total', min(Data_da_facturacao) as 'data_inicio', max(Data_da_facturacao) as 'data_fim' from vwrelpagamentoisencao WHERE data_da_facturacao between ? and ? and FK_tipo_deservico = ? and metodo_depagameto <> 'Isenção Por Idade' group by FK_tipo_deservico;";
    }
    if(op == 2){
 	   sql = "select  tipode_servico_facturado as 'servico', FK_tipo_deservico, count(FK_tipo_deservico) as 'qtd', sum(t_isen_produto) as 'total', min(Data_da_facturacao) as 'data_inicio', max(Data_da_facturacao) as 'data_fim' from vwrelpagamentoisencao WHERE data_da_facturacao between ? and ? and FK_tipo_deservico = ? and metodo_depagameto <> 'Isenção' group by FK_tipo_deservico;";
    }
    try {
    	 con = Conexao.getConexao();
		 PreparedStatement ps = con.prepareStatement(sql);	
		 ps.setDate(1, dia_in);
		 ps.setDate(2, dia_fim);
		 ps.setInt(3, cons);
		 ResultSet rs = ps.executeQuery();
		 while(rs.next()){
			 Produtos pd = new Produtos();
			 	pd.setFK_tipo_deservico(rs.getInt("FK_tipo_deservico"));
				pd.setTipode_servico_facturado(rs.getString("servico"));
				pd.setQuantidade(rs.getInt("qtd"));
				pd.setPreco(rs.getDouble("total"));
				Calendar inicio = Calendar.getInstance();
				inicio.setTime(rs.getDate("data_inicio"));
				pd.setData_inicio(inicio);
				Calendar fim = Calendar.getInstance();
				fim.setTime(rs.getDate("data_fim"));
				pd.setData_fim(fim);
				lista.add(pd);
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

public List<Produtos> buscarPagamentoIsencaoReceita(Date dia_in, Date dia_fim, int op, int cons){
	
	List<Produtos> lista = new ArrayList<Produtos>();
    String sql = "";
    if(op == 1){
    	   sql = "select  tipode_servico_facturado as 'servico', FK_tipo_deservico, count(FK_tipo_deservico) as 'qtd', sum(t_isen_produto) as 'total', min(Data_da_facturacao) as 'data_inicio', max(Data_da_facturacao) as 'data_fim' from vwrelpagamentoisencao WHERE data_da_facturacao between ? and ? and FK_tipo_deservico = ? and metodo_depagameto <> 'Isenção Por Idade' group by FK_tipo_deservico;";
    }
    if(op == 2){
 	   sql = "select  tipode_servico_facturado as 'servico', FK_tipo_deservico, count(FK_tipo_deservico) as 'qtd', sum(t_isen_produto) as 'total', min(Data_da_facturacao) as 'data_inicio', max(Data_da_facturacao) as 'data_fim' from vwrelpagamentoisencao WHERE data_da_facturacao between ? and ? and FK_tipo_deservico = ? and metodo_depagameto <> 'Isenção' group by FK_tipo_deservico;";
    }
    try {
    	 con = Conexao.getConexao();
		 PreparedStatement ps = con.prepareStatement(sql);	
		 ps.setDate(1, dia_in);
		 ps.setDate(2, dia_fim);
		 ps.setInt(3, cons);
		 ResultSet rs = ps.executeQuery();
		 while(rs.next()){
			 Produtos pd = new Produtos();
			 	pd.setFK_tipo_deservico(rs.getInt("FK_tipo_deservico"));
				pd.setTipode_servico_facturado(rs.getString("servico"));
				pd.setQuantidade(rs.getInt("qtd"));
				pd.setPreco(rs.getDouble("total"));
				Calendar inicio = Calendar.getInstance();
				inicio.setTime(rs.getDate("data_inicio"));
				pd.setData_inicio(inicio);
				Calendar fim = Calendar.getInstance();
				fim.setTime(rs.getDate("data_fim"));
				pd.setData_fim(fim);
				lista.add(pd);
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

public List<Produtos> buscarPagamentoIsencaoConsultaExameReceita(Date dia_in, Date dia_fim, int op, int cons, int exam, int serv){
	
	List<Produtos> lista = new ArrayList<Produtos>();
    String sql = "";
    if(op == 1){
    	   sql = "select  tipode_servico_facturado as 'servico', FK_tipo_deservico, count(FK_tipo_deservico) as 'qtd', sum(t_isen_produto) as 'total', min(Data_da_facturacao) as 'data_inicio', max(Data_da_facturacao) as 'data_fim' from vwrelpagamentoisencao WHERE data_da_facturacao between ? and ? and FK_tipo_deservico between ? and ? and ? and metodo_depagameto <> 'Isenção Por Idade' group by FK_tipo_deservico;";
    }
    if(op == 2){
 	   sql = "select  tipode_servico_facturado as 'servico', FK_tipo_deservico, count(FK_tipo_deservico) as 'qtd', sum(t_isen_produto) as 'total', min(Data_da_facturacao) as 'data_inicio', max(Data_da_facturacao) as 'data_fim' from vwrelpagamentoisencao WHERE data_da_facturacao between ? and ? and FK_tipo_deservico between ? and ? and ? and metodo_depagameto <> 'Isenção' group by FK_tipo_deservico;";
    }
    try {
    	 con = Conexao.getConexao();
		 PreparedStatement ps = con.prepareStatement(sql);	
		 ps.setDate(1, dia_in);
		 ps.setDate(2, dia_fim);
		 ps.setInt(3, cons);
		 ps.setInt(4, exam);
		 ps.setInt(5, serv);
		 ResultSet rs = ps.executeQuery();
		 while(rs.next()){
			 Produtos pd = new Produtos();
			 	pd.setFK_tipo_deservico(rs.getInt("FK_tipo_deservico"));
				pd.setTipode_servico_facturado(rs.getString("servico"));
				pd.setQuantidade(rs.getInt("qtd"));
				pd.setPreco(rs.getDouble("total"));
				Calendar inicio = Calendar.getInstance();
				inicio.setTime(rs.getDate("data_inicio"));
				pd.setData_inicio(inicio);
				Calendar fim = Calendar.getInstance();
				fim.setTime(rs.getDate("data_fim"));
				pd.setData_fim(fim);
				lista.add(pd);
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

public List<Produtos> buscarPagamentoIsencaoConsultaExameServicoGFarmacoReceita(Date dia_in, Date dia_fim, int op, int cons, int exam, int ser, int prod, int rec){
	
	List<Produtos> lista = new ArrayList<Produtos>();
    String sql = "";
    if(op == 1){
    	   sql = "select  tipode_servico_facturado as 'servico', FK_tipo_deservico, count(FK_tipo_deservico) as 'qtd', sum(t_isen_produto) as 'total', min(Data_da_facturacao) as 'data_inicio', max(Data_da_facturacao) as 'data_fim' from vwrelpagamentoisencao WHERE data_da_facturacao between ? and ? and FK_tipo_deservico between ? and ? and ? and ? and ? and metodo_depagameto <> 'Isenção Por Idade' group by FK_tipo_deservico;";
    }
    if(op == 2){
 	   sql = "select  tipode_servico_facturado as 'servico', FK_tipo_deservico, count(FK_tipo_deservico) as 'qtd', sum(t_isen_produto) as 'total', min(Data_da_facturacao) as 'data_inicio', max(Data_da_facturacao) as 'data_fim' from vwrelpagamentoisencao WHERE data_da_facturacao between ? and ? and FK_tipo_deservico between ? and ? and ? and ? and ? and metodo_depagameto <> 'Isenção' group by FK_tipo_deservico;";
    }
    try {
    	 con = Conexao.getConexao();
		 PreparedStatement ps = con.prepareStatement(sql);	
		 ps.setDate(1, dia_in);
		 ps.setDate(2, dia_fim);
		 ps.setInt(3, cons);
		 ps.setInt(4, exam);
		 ps.setInt(5, ser);
		 ps.setInt(6, prod);
		 ps.setInt(7, rec);
		 ResultSet rs = ps.executeQuery();
		 while(rs.next()){
			 Produtos pd = new Produtos();
			 	pd.setFK_tipo_deservico(rs.getInt("FK_tipo_deservico"));
				pd.setTipode_servico_facturado(rs.getString("servico"));
				pd.setQuantidade(rs.getInt("qtd"));
				pd.setPreco(rs.getDouble("total"));
				Calendar inicio = Calendar.getInstance();
				inicio.setTime(rs.getDate("data_inicio"));
				pd.setData_inicio(inicio);
				Calendar fim = Calendar.getInstance();
				fim.setTime(rs.getDate("data_fim"));
				pd.setData_fim(fim);
				lista.add(pd);
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

public List<Produtos> buscarPagamentoIsencaoConsultaExameServicoGFarmaco(Date dia_in, Date dia_fim, int op, int cons, int exam, int ser, int prod){
	
	List<Produtos> lista = new ArrayList<Produtos>();
    String sql = "";
    if(op == 1){
    	   sql = "select  tipode_servico_facturado as 'servico', FK_tipo_deservico, count(FK_tipo_deservico) as 'qtd', sum(t_isen_produto) as 'total', min(Data_da_facturacao) as 'data_inicio', max(Data_da_facturacao) as 'data_fim' from vwrelpagamentoisencao WHERE data_da_facturacao between ? and ? and FK_tipo_deservico between ? and ? and ? and ? and metodo_depagameto <> 'Isenção Por Idade' group by FK_tipo_deservico;";
    }
    if(op == 2){
 	   sql = "select  tipode_servico_facturado as 'servico', FK_tipo_deservico, count(FK_tipo_deservico) as 'qtd', sum(t_isen_produto) as 'total', min(Data_da_facturacao) as 'data_inicio', max(Data_da_facturacao) as 'data_fim' from vwrelpagamentoisencao WHERE data_da_facturacao between ? and ? and FK_tipo_deservico between ? and ? and ? and ? and metodo_depagameto <> 'Isenção' group by FK_tipo_deservico;";
    }
    try {
    	 con = Conexao.getConexao();
		 PreparedStatement ps = con.prepareStatement(sql);	
		 ps.setDate(1, dia_in);
		 ps.setDate(2, dia_fim);
		 ps.setInt(3, cons);
		 ps.setInt(4, exam);
		 ps.setInt(5, ser);
		 ps.setInt(6, prod);
		 ResultSet rs = ps.executeQuery();
		 while(rs.next()){
			 Produtos pd = new Produtos();
			 	pd.setFK_tipo_deservico(rs.getInt("FK_tipo_deservico"));
				pd.setTipode_servico_facturado(rs.getString("servico"));
				pd.setQuantidade(rs.getInt("qtd"));
				pd.setPreco(rs.getDouble("total"));
				Calendar inicio = Calendar.getInstance();
				inicio.setTime(rs.getDate("data_inicio"));
				pd.setData_inicio(inicio);
				Calendar fim = Calendar.getInstance();
				fim.setTime(rs.getDate("data_fim"));
				pd.setData_fim(fim);
				lista.add(pd);
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

public List<Produtos> buscarPagamentoIsencaoExameServicoGFarmacoReceita(Date dia_in, Date dia_fim, int op, int exam, int ser, int prod, int rec){
	
	List<Produtos> lista = new ArrayList<Produtos>();
    String sql = "";
    if(op == 1){
    	   sql = "select  tipode_servico_facturado as 'servico', FK_tipo_deservico, count(FK_tipo_deservico) as 'qtd', sum(t_isen_produto) as 'total', min(Data_da_facturacao) as 'data_inicio', max(Data_da_facturacao) as 'data_fim' from vwrelpagamentoisencao WHERE data_da_facturacao between ? and ? and FK_tipo_deservico between ? and ? and ? and ? and metodo_depagameto <> 'Isenção Por Idade' group by FK_tipo_deservico;";
    }
    if(op == 2){
 	   sql = "select  tipode_servico_facturado as 'servico', FK_tipo_deservico, count(FK_tipo_deservico) as 'qtd', sum(t_isen_produto) as 'total', min(Data_da_facturacao) as 'data_inicio', max(Data_da_facturacao) as 'data_fim' from vwrelpagamentoisencao WHERE data_da_facturacao between ? and ? and FK_tipo_deservico between ? and ? and ? and ? and metodo_depagameto <> 'Isenção' group by FK_tipo_deservico;";
    }
    try {
    	 con = Conexao.getConexao();
		 PreparedStatement ps = con.prepareStatement(sql);	
		 ps.setDate(1, dia_in);
		 ps.setDate(2, dia_fim);
		 ps.setInt(3, exam);
		 ps.setInt(4, ser);
		 ps.setInt(5, prod);
		 ps.setInt(6, rec);
		 ResultSet rs = ps.executeQuery();
		 while(rs.next()){
			 Produtos pd = new Produtos();
			 	pd.setFK_tipo_deservico(rs.getInt("FK_tipo_deservico"));
				pd.setTipode_servico_facturado(rs.getString("servico"));
				pd.setQuantidade(rs.getInt("qtd"));
				pd.setPreco(rs.getDouble("total"));
				Calendar inicio = Calendar.getInstance();
				inicio.setTime(rs.getDate("data_inicio"));
				pd.setData_inicio(inicio);
				Calendar fim = Calendar.getInstance();
				fim.setTime(rs.getDate("data_fim"));
				pd.setData_fim(fim);
				lista.add(pd);
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

public List<Produtos> buscarPagamentoIsencaoServicoGFarmacoReceita(Date dia_in, Date dia_fim, int op, int exam, int serv, int prod){
	
	List<Produtos> lista = new ArrayList<Produtos>();
    String sql = "";
    if(op == 1){
    	   sql = "select  tipode_servico_facturado as 'servico', FK_tipo_deservico, count(FK_tipo_deservico) as 'qtd', sum(t_isen_produto) as 'total', min(Data_da_facturacao) as 'data_inicio', max(Data_da_facturacao) as 'data_fim' from vwrelpagamentoisencao WHERE data_da_facturacao between ? and ? and FK_tipo_deservico between ? and ? and ? and metodo_depagameto <> 'Isenção Por Idade' group by FK_tipo_deservico;";
    }
    if(op == 2){
 	   sql = "select  tipode_servico_facturado as 'servico', FK_tipo_deservico, count(FK_tipo_deservico) as 'qtd', sum(t_isen_produto) as 'total', min(Data_da_facturacao) as 'data_inicio', max(Data_da_facturacao) as 'data_fim' from vwrelpagamentoisencao WHERE data_da_facturacao between ? and ? and FK_tipo_deservico between ? and ? and ? and metodo_depagameto <> 'Isenção' group by FK_tipo_deservico;";
    }
    try {
    	 con = Conexao.getConexao();
		 PreparedStatement ps = con.prepareStatement(sql);	
		 ps.setDate(1, dia_in);
		 ps.setDate(2, dia_fim);
		 ps.setInt(3, exam);
		 ps.setInt(4, serv);
		 ps.setInt(5, prod);
		 ResultSet rs = ps.executeQuery();
		 while(rs.next()){
			 Produtos pd = new Produtos();
			 	pd.setFK_tipo_deservico(rs.getInt("FK_tipo_deservico"));
				pd.setTipode_servico_facturado(rs.getString("servico"));
				pd.setQuantidade(rs.getInt("qtd"));
				pd.setPreco(rs.getDouble("total"));
				Calendar inicio = Calendar.getInstance();
				inicio.setTime(rs.getDate("data_inicio"));
				pd.setData_inicio(inicio);
				Calendar fim = Calendar.getInstance();
				fim.setTime(rs.getDate("data_fim"));
				pd.setData_fim(fim);
				lista.add(pd);
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

public List<Produtos> buscarPagamentoIsencaoExameServicoGFormaco(Date dia_in, Date dia_fim, int op, int exam, int serv, int prod){
	
	List<Produtos> lista = new ArrayList<Produtos>();
    String sql = "";
    if(op == 1){
    	   sql = "select  tipode_servico_facturado as 'servico', FK_tipo_deservico, count(FK_tipo_deservico) as 'qtd', sum(t_isen_produto) as 'total', min(Data_da_facturacao) as 'data_inicio', max(Data_da_facturacao) as 'data_fim' from vwrelpagamentoisencao WHERE data_da_facturacao between ? and ? and FK_tipo_deservico between ? and ? and ? and metodo_depagameto <> 'Isenção Por Idade' group by FK_tipo_deservico;";
    }
    if(op == 2){
 	   sql = "select  tipode_servico_facturado as 'servico', FK_tipo_deservico, count(FK_tipo_deservico) as 'qtd', sum(t_isen_produto) as 'total', min(Data_da_facturacao) as 'data_inicio', max(Data_da_facturacao) as 'data_fim' from vwrelpagamentoisencao WHERE data_da_facturacao between ? and ? and FK_tipo_deservico between ? and ? and ? and metodo_depagameto <> 'Isenção' group by FK_tipo_deservico;";
    }
    try {
    	 con = Conexao.getConexao();
		 PreparedStatement ps = con.prepareStatement(sql);	
		 ps.setDate(1, dia_in);
		 ps.setDate(2, dia_fim);
		 ps.setInt(3, exam);
		 ps.setInt(4, serv);
		 ps.setInt(5, prod);
		 ResultSet rs = ps.executeQuery();
		 while(rs.next()){
			 Produtos pd = new Produtos();
			 	pd.setFK_tipo_deservico(rs.getInt("FK_tipo_deservico"));
				pd.setTipode_servico_facturado(rs.getString("servico"));
				pd.setQuantidade(rs.getInt("qtd"));
				pd.setPreco(rs.getDouble("total"));
				Calendar inicio = Calendar.getInstance();
				inicio.setTime(rs.getDate("data_inicio"));
				pd.setData_inicio(inicio);
				Calendar fim = Calendar.getInstance();
				fim.setTime(rs.getDate("data_fim"));
				pd.setData_fim(fim);
				lista.add(pd);
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

public List<Produtos> buscarPagamentoIsencaoConsultaExameFarmaco(Date dia_in, Date dia_fim, int op, int cons, int exam, int serv){
	
	List<Produtos> lista = new ArrayList<Produtos>();
    String sql = "";
    if(op == 1){
    	   sql = "select  tipode_servico_facturado as 'servico', FK_tipo_deservico, count(FK_tipo_deservico) as 'qtd', sum(t_isen_produto) as 'total', min(Data_da_facturacao) as 'data_inicio', max(Data_da_facturacao) as 'data_fim' from vwrelpagamentoisencao WHERE data_da_facturacao between ? and ? and FK_tipo_deservico between ? and ? and ? and metodo_depagameto <> 'Isenção Por Idade' group by FK_tipo_deservico;";
    }
    if(op == 2){
 	   sql = "select  tipode_servico_facturado as 'servico', FK_tipo_deservico, count(FK_tipo_deservico) as 'qtd', sum(t_isen_produto) as 'total', min(Data_da_facturacao) as 'data_inicio', max(Data_da_facturacao) as 'data_fim' from vwrelpagamentoisencao WHERE data_da_facturacao between ? and ? and FK_tipo_deservico between ? and ? and ? and metodo_depagameto <> 'Isenção' group by FK_tipo_deservico;";
    }
    try {
    	 con = Conexao.getConexao();
		 PreparedStatement ps = con.prepareStatement(sql);	
		 ps.setDate(1, dia_in);
		 ps.setDate(2, dia_fim);
		 ps.setInt(3, cons);
		 ps.setInt(4, exam);
		 ps.setInt(5, serv);
		 ResultSet rs = ps.executeQuery();
		 while(rs.next()){
			 Produtos pd = new Produtos();
			 	pd.setFK_tipo_deservico(rs.getInt("FK_tipo_deservico"));
				pd.setTipode_servico_facturado(rs.getString("servico"));
				pd.setQuantidade(rs.getInt("qtd"));
				pd.setPreco(rs.getDouble("total"));
				Calendar inicio = Calendar.getInstance();
				inicio.setTime(rs.getDate("data_inicio"));
				pd.setData_inicio(inicio);
				Calendar fim = Calendar.getInstance();
				fim.setTime(rs.getDate("data_fim"));
				pd.setData_fim(fim);
				lista.add(pd);
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



public List<Produtos> buscarPagamentoIsencaoConsultaExameServicoG(Date dia_in, Date dia_fim, int op, int cons, int exam, int serv){
	
	List<Produtos> lista = new ArrayList<Produtos>();
    String sql = "";
    if(op == 1){
    	   sql = "select  tipode_servico_facturado as 'servico', FK_tipo_deservico, count(FK_tipo_deservico) as 'qtd', sum(t_isen_produto) as 'total', min(Data_da_facturacao) as 'data_inicio', max(Data_da_facturacao) as 'data_fim' from vwrelpagamentoisencao WHERE data_da_facturacao between ? and ? and FK_tipo_deservico between ? and ? and ? and metodo_depagameto <> 'Isenção Por Idade' group by FK_tipo_deservico;";
    }
    if(op == 2){
 	   sql = "select  tipode_servico_facturado as 'servico', FK_tipo_deservico, count(FK_tipo_deservico) as 'qtd', sum(t_isen_produto) as 'total', min(Data_da_facturacao) as 'data_inicio', max(Data_da_facturacao) as 'data_fim' from vwrelpagamentoisencao WHERE data_da_facturacao between ? and ? and FK_tipo_deservico between ? and ? and ? and metodo_depagameto <> 'Isenção' group by FK_tipo_deservico;";
    }
    try {
    	 con = Conexao.getConexao();
		 PreparedStatement ps = con.prepareStatement(sql);	
		 ps.setDate(1, dia_in);
		 ps.setDate(2, dia_fim);
		 ps.setInt(3, cons);
		 ps.setInt(4, exam);
		 ps.setInt(5, serv);
		 ResultSet rs = ps.executeQuery();
		 while(rs.next()){
			 Produtos pd = new Produtos();
			 	pd.setFK_tipo_deservico(rs.getInt("FK_tipo_deservico"));
				pd.setTipode_servico_facturado(rs.getString("servico"));
				pd.setQuantidade(rs.getInt("qtd"));
				pd.setPreco(rs.getDouble("total"));
				Calendar inicio = Calendar.getInstance();
				inicio.setTime(rs.getDate("data_inicio"));
				pd.setData_inicio(inicio);
				Calendar fim = Calendar.getInstance();
				fim.setTime(rs.getDate("data_fim"));
				pd.setData_fim(fim);
				lista.add(pd);
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

public List<Produtos> buscarPagamentoIsencaoFarmacoReceita(Date dia_in, Date dia_fim, int op, int exa, int rec){
	
	List<Produtos> lista = new ArrayList<Produtos>();
    String sql = "";
    if(op == 1){
    	   sql = "select  tipode_servico_facturado as 'servico', FK_tipo_deservico, count(FK_tipo_deservico) as 'qtd', sum(t_isen_produto) as 'total', min(Data_da_facturacao) as 'data_inicio', max(Data_da_facturacao) as 'data_fim' from vwrelpagamentoisencao WHERE data_da_facturacao between ? and ? and FK_tipo_deservico between ? and ? and metodo_depagameto <> 'Isenção Por Idade' group by FK_tipo_deservico;";
    }
    if(op == 2){
 	   sql = "select  tipode_servico_facturado as 'servico', FK_tipo_deservico, count(FK_tipo_deservico) as 'qtd', sum(t_isen_produto) as 'total', min(Data_da_facturacao) as 'data_inicio', max(Data_da_facturacao) as 'data_fim' from vwrelpagamentoisencao WHERE data_da_facturacao between ? and ? and FK_tipo_deservico between ? and ? and metodo_depagameto <> 'Isenção' group by FK_tipo_deservico;";
    }
    try {
    	 con = Conexao.getConexao();
		 PreparedStatement ps = con.prepareStatement(sql);	
		 ps.setDate(1, dia_in);
		 ps.setDate(2, dia_fim);
		 ps.setInt(3, exa);
		 ps.setInt(4, rec);
		 ResultSet rs = ps.executeQuery();
		 while(rs.next()){
			 Produtos pd = new Produtos();
			 	pd.setFK_tipo_deservico(rs.getInt("FK_tipo_deservico"));
				pd.setTipode_servico_facturado(rs.getString("servico"));
				pd.setQuantidade(rs.getInt("qtd"));
				pd.setPreco(rs.getDouble("total"));
				Calendar inicio = Calendar.getInstance();
				inicio.setTime(rs.getDate("data_inicio"));
				pd.setData_inicio(inicio);
				Calendar fim = Calendar.getInstance();
				fim.setTime(rs.getDate("data_fim"));
				pd.setData_fim(fim);
				lista.add(pd);
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

public List<Produtos> buscarPagamentoIsencaoServicoGReceita(Date dia_in, Date dia_fim, int op, int exa, int rec){
	
	List<Produtos> lista = new ArrayList<Produtos>();
    String sql = "";
    if(op == 1){
    	   sql = "select  tipode_servico_facturado as 'servico', FK_tipo_deservico, count(FK_tipo_deservico) as 'qtd', sum(t_isen_produto) as 'total', min(Data_da_facturacao) as 'data_inicio', max(Data_da_facturacao) as 'data_fim' from vwrelpagamentoisencao WHERE data_da_facturacao between ? and ? and FK_tipo_deservico between ? and ? and metodo_depagameto <> 'Isenção Por Idade' group by FK_tipo_deservico;";
    }
    if(op == 2){
 	   sql = "select  tipode_servico_facturado as 'servico', FK_tipo_deservico, count(FK_tipo_deservico) as 'qtd', sum(t_isen_produto) as 'total', min(Data_da_facturacao) as 'data_inicio', max(Data_da_facturacao) as 'data_fim' from vwrelpagamentoisencao WHERE data_da_facturacao between ? and ? and FK_tipo_deservico between ? and ? and metodo_depagameto <> 'Isenção' group by FK_tipo_deservico;";
    }
    try {
    	 con = Conexao.getConexao();
		 PreparedStatement ps = con.prepareStatement(sql);	
		 ps.setDate(1, dia_in);
		 ps.setDate(2, dia_fim);
		 ps.setInt(3, exa);
		 ps.setInt(4, rec);
		 ResultSet rs = ps.executeQuery();
		 while(rs.next()){
			 Produtos pd = new Produtos();
			 	pd.setFK_tipo_deservico(rs.getInt("FK_tipo_deservico"));
				pd.setTipode_servico_facturado(rs.getString("servico"));
				pd.setQuantidade(rs.getInt("qtd"));
				pd.setPreco(rs.getDouble("total"));
				Calendar inicio = Calendar.getInstance();
				inicio.setTime(rs.getDate("data_inicio"));
				pd.setData_inicio(inicio);
				Calendar fim = Calendar.getInstance();
				fim.setTime(rs.getDate("data_fim"));
				pd.setData_fim(fim);
				lista.add(pd);
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

public List<Produtos> buscarPagamentoIsencaoServicoGFarmaco(Date dia_in, Date dia_fim, int op, int exa, int rec){
	
	List<Produtos> lista = new ArrayList<Produtos>();
    String sql = "";
    if(op == 1){
    	   sql = "select  tipode_servico_facturado as 'servico', FK_tipo_deservico, count(FK_tipo_deservico) as 'qtd', sum(t_isen_produto) as 'total', min(Data_da_facturacao) as 'data_inicio', max(Data_da_facturacao) as 'data_fim' from vwrelpagamentoisencao WHERE data_da_facturacao between ? and ? and FK_tipo_deservico between ? and ? and metodo_depagameto <> 'Isenção Por Idade' group by FK_tipo_deservico;";
    }
    if(op == 2){
 	   sql = "select  tipode_servico_facturado as 'servico', FK_tipo_deservico, count(FK_tipo_deservico) as 'qtd', sum(t_isen_produto) as 'total', min(Data_da_facturacao) as 'data_inicio', max(Data_da_facturacao) as 'data_fim' from vwrelpagamentoisencao WHERE data_da_facturacao between ? and ? and FK_tipo_deservico between ? and ? and metodo_depagameto <> 'Isenção' group by FK_tipo_deservico;";
    }
    try {
    	 con = Conexao.getConexao();
		 PreparedStatement ps = con.prepareStatement(sql);	
		 ps.setDate(1, dia_in);
		 ps.setDate(2, dia_fim);
		 ps.setInt(3, exa);
		 ps.setInt(4, rec);
		 ResultSet rs = ps.executeQuery();
		 while(rs.next()){
			 Produtos pd = new Produtos();
			 	pd.setFK_tipo_deservico(rs.getInt("FK_tipo_deservico"));
				pd.setTipode_servico_facturado(rs.getString("servico"));
				pd.setQuantidade(rs.getInt("qtd"));
				pd.setPreco(rs.getDouble("total"));
				Calendar inicio = Calendar.getInstance();
				inicio.setTime(rs.getDate("data_inicio"));
				pd.setData_inicio(inicio);
				Calendar fim = Calendar.getInstance();
				fim.setTime(rs.getDate("data_fim"));
				pd.setData_fim(fim);
				lista.add(pd);
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

public List<Produtos> buscarPagamentoIsencaoExameReceita(Date dia_in, Date dia_fim, int op, int exa, int rec){
	
	List<Produtos> lista = new ArrayList<Produtos>();
    String sql = "";
    if(op == 1){
    	   sql = "select  tipode_servico_facturado as 'servico', FK_tipo_deservico, count(FK_tipo_deservico) as 'qtd', sum(t_isen_produto) as 'total', min(Data_da_facturacao) as 'data_inicio', max(Data_da_facturacao) as 'data_fim' from vwrelpagamentoisencao WHERE data_da_facturacao between ? and ? and FK_tipo_deservico between ? and ? and metodo_depagameto <> 'Isenção Por Idade' group by FK_tipo_deservico;";
    }
    if(op == 2){
 	   sql = "select  tipode_servico_facturado as 'servico', FK_tipo_deservico, count(FK_tipo_deservico) as 'qtd', sum(t_isen_produto) as 'total', min(Data_da_facturacao) as 'data_inicio', max(Data_da_facturacao) as 'data_fim' from vwrelpagamentoisencao WHERE data_da_facturacao between ? and ? and FK_tipo_deservico between ? and ? and metodo_depagameto <> 'Isenção' group by FK_tipo_deservico;";
    }
    try {
    	 con = Conexao.getConexao();
		 PreparedStatement ps = con.prepareStatement(sql);	
		 ps.setDate(1, dia_in);
		 ps.setDate(2, dia_fim);
		 ps.setInt(3, exa);
		 ps.setInt(4, rec);
		 ResultSet rs = ps.executeQuery();
		 while(rs.next()){
			 Produtos pd = new Produtos();
			 	pd.setFK_tipo_deservico(rs.getInt("FK_tipo_deservico"));
				pd.setTipode_servico_facturado(rs.getString("servico"));
				pd.setQuantidade(rs.getInt("qtd"));
				pd.setPreco(rs.getDouble("total"));
				Calendar inicio = Calendar.getInstance();
				inicio.setTime(rs.getDate("data_inicio"));
				pd.setData_inicio(inicio);
				Calendar fim = Calendar.getInstance();
				fim.setTime(rs.getDate("data_fim"));
				pd.setData_fim(fim);
				lista.add(pd);
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


public List<Produtos> buscarPagamentoIsencaoExameFarmaco(Date dia_in, Date dia_fim, int op, int exa, int prod){
	
	List<Produtos> lista = new ArrayList<Produtos>();
    String sql = "";
    if(op == 1){
    	   sql = "select  tipode_servico_facturado as 'servico', FK_tipo_deservico, count(FK_tipo_deservico) as 'qtd', sum(t_isen_produto) as 'total', min(Data_da_facturacao) as 'data_inicio', max(Data_da_facturacao) as 'data_fim' from vwrelpagamentoisencao WHERE data_da_facturacao between ? and ? and FK_tipo_deservico between ? and ? and metodo_depagameto <> 'Isenção Por Idade' group by FK_tipo_deservico;";
    }
    if(op == 2){
 	   sql = "select  tipode_servico_facturado as 'servico', FK_tipo_deservico, count(FK_tipo_deservico) as 'qtd', sum(t_isen_produto) as 'total', min(Data_da_facturacao) as 'data_inicio', max(Data_da_facturacao) as 'data_fim' from vwrelpagamentoisencao WHERE data_da_facturacao between ? and ? and FK_tipo_deservico between ? and ? and metodo_depagameto <> 'Isenção' group by FK_tipo_deservico;";
    }
    try {
    	 con = Conexao.getConexao();
		 PreparedStatement ps = con.prepareStatement(sql);	
		 ps.setDate(1, dia_in);
		 ps.setDate(2, dia_fim);
		 ps.setInt(3, exa);
		 ps.setInt(4, prod);
		 ResultSet rs = ps.executeQuery();
		 while(rs.next()){
			 Produtos pd = new Produtos();
			 	pd.setFK_tipo_deservico(rs.getInt("FK_tipo_deservico"));
				pd.setTipode_servico_facturado(rs.getString("servico"));
				pd.setQuantidade(rs.getInt("qtd"));
				pd.setPreco(rs.getDouble("total"));
				Calendar inicio = Calendar.getInstance();
				inicio.setTime(rs.getDate("data_inicio"));
				pd.setData_inicio(inicio);
				Calendar fim = Calendar.getInstance();
				fim.setTime(rs.getDate("data_fim"));
				pd.setData_fim(fim);
				lista.add(pd);
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

public List<Produtos> buscarPagamentoIsencaoExameServico(Date dia_in, Date dia_fim, int op, int cons, int exa){
	
	List<Produtos> lista = new ArrayList<Produtos>();
    String sql = "";
    if(op == 1){
    	   sql = "select  tipode_servico_facturado as 'servico', FK_tipo_deservico, count(FK_tipo_deservico) as 'qtd', sum(t_isen_produto) as 'total', min(Data_da_facturacao) as 'data_inicio', max(Data_da_facturacao) as 'data_fim' from vwrelpagamentoisencao WHERE data_da_facturacao between ? and ? and FK_tipo_deservico between ? and ? and metodo_depagameto <> 'Isenção Por Idade' group by FK_tipo_deservico;";
    }
    if(op == 2){
 	   sql = "select  tipode_servico_facturado as 'servico', FK_tipo_deservico, count(FK_tipo_deservico) as 'qtd', sum(t_isen_produto) as 'total', min(Data_da_facturacao) as 'data_inicio', max(Data_da_facturacao) as 'data_fim' from vwrelpagamentoisencao WHERE data_da_facturacao between ? and ? and FK_tipo_deservico between ? and ? and metodo_depagameto <> 'Isenção' group by FK_tipo_deservico;";
    }
    try {
    	 con = Conexao.getConexao();
		 PreparedStatement ps = con.prepareStatement(sql);	
		 ps.setDate(1, dia_in);
		 ps.setDate(2, dia_fim);
		 ps.setInt(3, cons);
		 ps.setInt(4, exa);
		 ResultSet rs = ps.executeQuery();
		 while(rs.next()){
			 Produtos pd = new Produtos();
			 	pd.setFK_tipo_deservico(rs.getInt("FK_tipo_deservico"));
				pd.setTipode_servico_facturado(rs.getString("servico"));
				pd.setQuantidade(rs.getInt("qtd"));
				pd.setPreco(rs.getDouble("total"));
				Calendar inicio = Calendar.getInstance();
				inicio.setTime(rs.getDate("data_inicio"));
				pd.setData_inicio(inicio);
				Calendar fim = Calendar.getInstance();
				fim.setTime(rs.getDate("data_fim"));
				pd.setData_fim(fim);
				lista.add(pd);
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

public List<Produtos> buscarPagamentoIsencaoConsultaExame(Date dia_in, Date dia_fim, int op, int cons, int exa){
	
	List<Produtos> lista = new ArrayList<Produtos>();
    String sql = "";
    if(op == 1){
    	   sql = "select  tipode_servico_facturado as 'servico', FK_tipo_deservico, count(FK_tipo_deservico) as 'qtd', sum(t_isen_produto) as 'total', min(Data_da_facturacao) as 'data_inicio', max(Data_da_facturacao) as 'data_fim' from vwrelpagamentoisencao WHERE data_da_facturacao between ? and ? and FK_tipo_deservico between ? and ? and metodo_depagameto <> 'Isenção Por Idade' group by FK_tipo_deservico;";
    }
    if(op == 2){
 	   sql = "select  tipode_servico_facturado as 'servico', FK_tipo_deservico, count(FK_tipo_deservico) as 'qtd', sum(t_isen_produto) as 'total', min(Data_da_facturacao) as 'data_inicio', max(Data_da_facturacao) as 'data_fim' from vwrelpagamentoisencao WHERE data_da_facturacao between ? and ? and FK_tipo_deservico between ? and ? and metodo_depagameto <> 'Isenção' group by FK_tipo_deservico;";
    }
    try {
    	 con = Conexao.getConexao();
		 PreparedStatement ps = con.prepareStatement(sql);	
		 ps.setDate(1, dia_in);
		 ps.setDate(2, dia_fim);
		 ps.setInt(3, cons);
		 ps.setInt(4, exa);
		 ResultSet rs = ps.executeQuery();
		 while(rs.next()){
			 Produtos pd = new Produtos();
			 	pd.setFK_tipo_deservico(rs.getInt("FK_tipo_deservico"));
				pd.setTipode_servico_facturado(rs.getString("servico"));
				pd.setQuantidade(rs.getInt("qtd"));
				pd.setPreco(rs.getDouble("total"));
				Calendar inicio = Calendar.getInstance();
				inicio.setTime(rs.getDate("data_inicio"));
				pd.setData_inicio(inicio);
				Calendar fim = Calendar.getInstance();
				fim.setTime(rs.getDate("data_fim"));
				pd.setData_fim(fim);
				lista.add(pd);
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

public List<Produtos> buscarPagamentoIsencaoConsultaServico(Date dia_in, Date dia_fim, int op, int cons, int ser){
	
	List<Produtos> lista = new ArrayList<Produtos>();
    String sql = "";
    if(op == 1){
    	   sql = "select  tipode_servico_facturado as 'servico', FK_tipo_deservico, count(FK_tipo_deservico) as 'qtd', sum(t_isen_produto) as 'total', min(Data_da_facturacao) as 'data_inicio', max(Data_da_facturacao) as 'data_fim' from vwrelpagamentoisencao WHERE data_da_facturacao between ? and ? and FK_tipo_deservico between ? and ? and metodo_depagameto <> 'Isenção Por Idade' group by FK_tipo_deservico;";
    }
    if(op == 2){
 	   sql = "select  tipode_servico_facturado as 'servico', FK_tipo_deservico, count(FK_tipo_deservico) as 'qtd', sum(t_isen_produto) as 'total', min(Data_da_facturacao) as 'data_inicio', max(Data_da_facturacao) as 'data_fim' from vwrelpagamentoisencao WHERE data_da_facturacao between ? and ? and FK_tipo_deservico between ? and ? and metodo_depagameto <> 'Isenção' group by FK_tipo_deservico;";
    }
    try {
    	 con = Conexao.getConexao();
		 PreparedStatement ps = con.prepareStatement(sql);	
		 ps.setDate(1, dia_in);
		 ps.setDate(2, dia_fim);
		 ps.setInt(3, cons);
		 ps.setInt(4, ser);
		 ResultSet rs = ps.executeQuery();
		 while(rs.next()){
			 Produtos pd = new Produtos();
			 	pd.setFK_tipo_deservico(rs.getInt("FK_tipo_deservico"));
				pd.setTipode_servico_facturado(rs.getString("servico"));
				pd.setQuantidade(rs.getInt("qtd"));
				pd.setPreco(rs.getDouble("total"));
				Calendar inicio = Calendar.getInstance();
				inicio.setTime(rs.getDate("data_inicio"));
				pd.setData_inicio(inicio);
				Calendar fim = Calendar.getInstance();
				fim.setTime(rs.getDate("data_fim"));
				pd.setData_fim(fim);
				lista.add(pd);
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

public List<Produtos> buscarPagamentoIsencaoConsulaFormaco(Date dia_in, Date dia_fim, int op, int cons, int prod){
	
	List<Produtos> lista = new ArrayList<Produtos>();
    String sql = "";
    if(op == 1){
    	   sql = "select  tipode_servico_facturado as 'servico', FK_tipo_deservico, count(FK_tipo_deservico) as 'qtd', sum(t_isen_produto) as 'total', min(Data_da_facturacao) as 'data_inicio', max(Data_da_facturacao) as 'data_fim' from vwrelpagamentoisencao WHERE data_da_facturacao between ? and ? and FK_tipo_deservico between ? and ? and metodo_depagameto <> 'Isenção Por Idade' group by FK_tipo_deservico;";
    }
    if(op == 2){
 	   sql = "select  tipode_servico_facturado as 'servico', FK_tipo_deservico, count(FK_tipo_deservico) as 'qtd', sum(t_isen_produto) as 'total', min(Data_da_facturacao) as 'data_inicio', max(Data_da_facturacao) as 'data_fim' from vwrelpagamentoisencao WHERE data_da_facturacao between ? and ? and FK_tipo_deservico between ? and ? and metodo_depagameto <> 'Isenção' group by FK_tipo_deservico;";
    }
    try {
    	 con = Conexao.getConexao();
		 PreparedStatement ps = con.prepareStatement(sql);	
		 ps.setDate(1, dia_in);
		 ps.setDate(2, dia_fim);
		 ps.setInt(3, cons);
		 ps.setInt(4, prod);
		 ResultSet rs = ps.executeQuery();
		 while(rs.next()){
			 Produtos pd = new Produtos();
			 	pd.setFK_tipo_deservico(rs.getInt("FK_tipo_deservico"));
				pd.setTipode_servico_facturado(rs.getString("servico"));
				pd.setQuantidade(rs.getInt("qtd"));
				pd.setPreco(rs.getDouble("total"));
				Calendar inicio = Calendar.getInstance();
				inicio.setTime(rs.getDate("data_inicio"));
				pd.setData_inicio(inicio);
				Calendar fim = Calendar.getInstance();
				fim.setTime(rs.getDate("data_fim"));
				pd.setData_fim(fim);
				lista.add(pd);
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

//---------------------------------------------------------- FINAL PAGAMENTO ISENï¿½ï¿½O
}