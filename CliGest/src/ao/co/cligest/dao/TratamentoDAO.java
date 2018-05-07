package ao.co.cligest.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

import ao.co.cligest.entidades.Paciente;
import ao.co.cligest.entidades.Tratamento;

public class TratamentoDAO {

	private Connection con;

    public List<Paciente> buscarTratamentoPaciente(String nome) {
		List<Paciente> lista = new ArrayList<Paciente>();
		String sql = "SELECT * FROM vwpacientenaagenda WHERE fk_entidade <> 0 and (nome LIKE ? or ultimo_nome LIKE ?) order by nome asc";
		try {
			con = Conexao.getConexao();
			PreparedStatement preparador = con.prepareStatement(sql);
			preparador.setString(1, "%"+nome+"%");
			preparador.setString(2, nome);
			ResultSet rs = preparador.executeQuery();
			while (rs.next()) {
				Paciente pac = new Paciente();
				  pac.setId_entidade(rs.getInt("FK_entidade"));
	        	  pac.setNome(rs.getString("nome"));
	        	  pac.setNomem(rs.getString("nome_meio"));
	        	  pac.setApelido(rs.getString("ultimo_nome"));
	        	  pac.setNumero_processo(rs.getString("numero_processo"));
	        	  //pac.setNome_paciente(rs.getString("paciente"));
	        	  Calendar data = Calendar.getInstance();
			      data.setTime(rs.getDate("data_nascimento"));
			      pac.setDataNasc(data);
				lista.add(pac);
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
		return lista;
	}
    
    public List<Paciente> buscarTratamentoPacientes(String nome) {
		List<Paciente> lista = new ArrayList<Paciente>();
		String sql = "SELECT * FROM vwpacientenaagenda WHERE fk_entidade <> 0 and numero_processo LIKE ? order by numero_processo asc";
		try {
			con = Conexao.getConexao();
			PreparedStatement preparador = con.prepareStatement(sql);
			preparador.setString(1, "%"+nome+"%");
			ResultSet rs = preparador.executeQuery();
			while (rs.next()) {
				Paciente pac = new Paciente();
				  pac.setId_entidade(rs.getInt("FK_entidade"));
	        	  pac.setNome(rs.getString("nome"));
	        	  pac.setNomem(rs.getString("nome_meio"));
	        	  pac.setApelido(rs.getString("ultimo_nome"));
	        	  pac.setNumero_processo(rs.getString("numero_processo"));
	        	  pac.setNome_paciente(rs.getString("paciente"));
	        	  Calendar data = Calendar.getInstance();
			      data.setTime(rs.getDate("data_nascimento"));
			      pac.setDataNasc(data);
				lista.add(pac);
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
		return lista;
	}
    
    public List<Paciente> buscarRequisicaoPaciente(String nome) {
		List<Paciente> lista = new ArrayList<Paciente>();
		String sql = "SELECT * FROM VWENTIDADECOMOPACIENTE WHERE nome = ?";
		try {
			con = Conexao.getConexao();
			PreparedStatement preparador = con.prepareStatement(sql);
			preparador.setString(1, nome);
			ResultSet rs = preparador.executeQuery();
			while (rs.next()) {
				Paciente pac = new Paciente();
				  pac.setId_entidade(rs.getInt("FK_entidade"));
	        	  pac.setNome(rs.getString("nome"));
	        	  pac.setNomem(rs.getString("nome_meio"));
	        	  pac.setApelido(rs.getString("ultimo_nome"));
	        	  pac.setNumero_processo(rs.getString("numero_processo"));
	        	  Calendar data = Calendar.getInstance();
			      data.setTime(rs.getDate("data_nascimento"));
			      pac.setDataNasc(data);
				lista.add(pac);
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
		return lista;
	}
    public Paciente buscarUmPacientes(String valor)
	
	{
		Paciente fun = null;
		 
		String sql = "SELECT * FROM vwtodasinformacoespaciente where nomecompleto Like ? or numeroprocesso Like ? ";
		try {
			con = Conexao.getConexao();
			 PreparedStatement preparador = con.prepareStatement(sql);
			 preparador.setString(1, "%"+valor+"%");
			 preparador.setString(2, valor);
			 ResultSet rs = preparador.executeQuery();
			 while(rs.next())
			 {
				fun = new Paciente();
				fun.setId_entidade(rs.getInt("entidade"));
				fun.setNumero_processo(rs.getString("numeroprocesso"));
				fun.setNome(rs.getString("nomecompleto"));
				fun.setNome_paciente(rs.getString("nomecompleto"));
				fun.setNumero_doc(rs.getString("numero_documento"));
				Calendar data = Calendar.getInstance();
				data.setTime(rs.getDate("data_nascimento"));
				fun.setDataNasc(data);
				fun.setIdade(rs.getDate("data_nascimento"));
				fun.setEndereco(rs.getString("endereco"));
				fun.setNomePais(rs.getString("pais"));
				fun.setNomegenero(rs.getString("genero"));
				fun.setNomeProv(rs.getString("provincia"));
				fun.setNomeMunicipio(rs.getString("municipio"));
				fun.setNomeDistrito(rs.getString("distrito"));
				fun.setNomeBairro(rs.getString("bairro"));
				fun.setNomeProf(rs.getString("profissao"));
				fun.setEmail(rs.getString("email"));
				fun.setTelefone(rs.getLong("telefone"));
				fun.setNomep(rs.getString("nome_parente"));
				fun.setTelefonep(rs.getLong("telefone_parente"));
				fun.setNomeAfl(rs.getString("afiliacao"));
				fun.setNomeEC(rs.getString("estado_civil"));
				fun.setNomeDoc(rs.getString("tipo_documento"));
				  
			 }
			 preparador.close();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		catch (Exception e) {
			 
		}
	    finally{
					try{
						con.close();
					}
					catch(SQLException ef){
						System.out.println("Erro finalizar: "+ef);
					}
			}
		return fun;
	}
   
    public List<Tratamento> getVendasTratamento(String valor){

    	String sql = "select id_factura, numero_factura, NumeroProceso, NomeCompleto, count(id_factura) as 'qtd_servico', n_c_funcionario, Data_da_facturacao, hora_da_facturacao, total, metodo_depagameto from vwrelpagamentoservicosgerais where NomeCompleto like ? or NumeroProceso like ? group by id_factura order by Data_da_facturacao desc;";
    	List<Tratamento> lsVenda = new ArrayList<Tratamento>();
    	try{
    		con = Conexao.getConexao();
    		PreparedStatement ps = con.prepareStatement(sql);
    		ps.setString(1, valor+"%");
    		ps.setString(2, valor);
    		ResultSet rs = ps.executeQuery();
    		while(rs.next()){
    			Tratamento trat = new Tratamento();
    			trat.setNome_paciente(rs.getString("NomeCompleto"));
    			trat.setNumero_factura(rs.getString("numero_factura"));
    			trat.setNumero_processo(rs.getString("NumeroProceso"));
    			trat.setFuncionario(rs.getString("n_c_funcionario"));
    			trat.setHora_registo(rs.getString("hora_da_facturacao"));
    			trat.setQuantidade(rs.getInt("qtd_servico"));
    			trat.setFK_factura(rs.getInt("id_factura"));
    			Calendar data = Calendar.getInstance();
    			data.setTime(rs.getDate("Data_da_facturacao"));
    			trat.setData_registo(data);
    			trat.setMetodo_dpagamento(rs.getString("metodo_depagameto"));
    			trat.setTotal_pago_tratamento(rs.getDouble("total"));
    			lsVenda.add(trat);
    			
    		}
    		
    	}catch(Exception e){
    		e.printStackTrace();
    	}
    	return lsVenda;
    }
    public List<Tratamento> getVendasTratamentos()
    {

    	String sql = " select * from vwhistoricodevendatratamento WHERE Data_da_facturacao = curdate()";
    	List<Tratamento> lsVenda = new ArrayList<Tratamento>();
    	try{
    		con = Conexao.getConexao();
    		PreparedStatement ps = con.prepareStatement(sql);
    		ResultSet rs = ps.executeQuery();
    		while(rs.next()){
    			Tratamento trat = new Tratamento();
    			trat.setNome_paciente(rs.getString("NomeCompleto"));
    			trat.setNumero_factura(rs.getString("numero_factura"));
    			trat.setNumero_processo(rs.getString("NumeroProceso"));
    			trat.setFuncionario(rs.getString("n_c_funcionario"));
    			trat.setTotal_pago_tratamento(rs.getDouble("total"));
    			trat.setHora_registo(rs.getString("hora_da_facturacao"));
    			trat.setQuantidade(rs.getInt("qtd_servico"));
    			trat.setFK_factura(rs.getInt("id_factura"));
    			Calendar data = Calendar.getInstance();
    			data.setTime(rs.getDate("Data_da_facturacao"));
    			trat.setData_registo(data);
    			trat.setMetodo_dpagamento(rs.getString("metodo_depagameto"));
    			lsVenda.add(trat);
    		}
    		
    	}catch(Exception e){
    		e.printStackTrace();
    	}
    	return lsVenda;
    }
    
    public List<Tratamento> getVendasTratamentosR(){

    	String sql = "select id_factura, numero_factura, Data_da_facturacao, hora_da_facturacao, NumeroProceso, NomeCompleto, count(id_factura) as 'qtd_servico', n_c_funcionario, total, Data_da_facturacao, hora_da_facturacao, total, metodo_depagameto from vwrelpagamentoservicosgerais group by id_factura order by Data_da_facturacao desc, hora_da_facturacao desc limit 500";
    	List<Tratamento> lsVenda = new ArrayList<Tratamento>();
    	try{
    		con = Conexao.getConexao();
    		PreparedStatement ps = con.prepareStatement(sql);
    		ResultSet rs = ps.executeQuery();
    		while(rs.next()){
    			Tratamento trat = new Tratamento();
    			trat.setNome_paciente(rs.getString("NomeCompleto"));
    			trat.setNumero_factura(rs.getString("numero_factura"));
    			trat.setNumero_processo(rs.getString("NumeroProceso"));
    			trat.setFuncionario(rs.getString("n_c_funcionario"));
    			trat.setTotal_pago_tratamento(rs.getDouble("total"));
    			trat.setHora_registo(rs.getString("hora_da_facturacao"));
    			trat.setQuantidade(rs.getInt("qtd_servico"));
    			trat.setFK_factura(rs.getInt("id_factura"));
    			Calendar data = Calendar.getInstance();
    			data.setTime(rs.getDate("Data_da_facturacao"));
    			trat.setData_registo(data);
    			trat.setMetodo_dpagamento(rs.getString("metodo_depagameto"));
    			trat.setTotal_pago_tratamento(rs.getDouble("total"));
    			lsVenda.add(trat);
    			
    		}
    		
    	}catch(Exception e){
    		e.printStackTrace();
    	}
    	return lsVenda;
    }
    
    public List<Tratamento> getPacientesIsentados(){

    	String sql = "Select * from vwpacientesisentadostratamentos where data_isencao = curdate()  and id_isencao not in (select fk_isencao from tblfacturaisentada) ";
    	List<Tratamento> lista = new ArrayList<Tratamento>();
    	try{
    		con = Conexao.getConexao();
    		PreparedStatement ps = con.prepareStatement(sql);
    		ResultSet rs = ps.executeQuery();
    		while(rs.next()){
    			Tratamento trat = new Tratamento();
    			trat.setFK_paciente(rs.getInt("FK_paciente"));
    			trat.setNome_paciente(rs.getString("NomeCompleto"));
    			trat.setId_isencao(rs.getInt("id_isencao"));
    			trat.setNumero_doc(rs.getString("numero_daisencao"));
    			trat.setQuantidade(rs.getInt("qtd_isentado"));
    			trat.setTotal_isentado(rs.getDouble("total_isentado"));
    			lista.add(trat);
    			 
    		}
    		
    	}catch(Exception e){
    		e.printStackTrace();
    	}
    	return lista;
    }
    
    public Paciente getPacienteIsentado(String cod){

    	String sql = "Select * from vwpacientesisentadostratamentos where id_isencao = ?";
    	Paciente trat = new Paciente();
    	try{
    		con = Conexao.getConexao();
    		PreparedStatement ps = con.prepareStatement(sql);
    		ps.setString(1, cod);
    		ResultSet rs = ps.executeQuery();
    		if(rs.next()){
    			
    			trat.setFK_paciente(rs.getInt("FK_paciente"));
    			trat.setId_entidade(rs.getInt("FK_paciente"));
    			trat.setNome_paciente(rs.getString("NomeCompleto"));
    			trat.setId_isencao(rs.getInt("id_isencao"));
    			trat.setNumero_processo(rs.getString("NumeroProcesso"));
    			trat.setIdade(rs.getDate("data_nascimento"));     
    			trat.setNome(rs.getString("nome"));
    			trat.setApelido(rs.getString("ultimo_nome"));
    		}
    		
    	}catch(Exception e){
    		e.printStackTrace();
    	}
    	return trat;
    }
    
    public List<Tratamento> getVendasTratamentos(String aux){

    	String sql = "Select * from vwhistoricodevendatratamento where numero_factura Like ? or NomeCompleto Like ?";
    	List<Tratamento> lsVenda = new ArrayList<Tratamento>();
    	try{
    		con = Conexao.getConexao();
    		PreparedStatement ps = con.prepareStatement(sql);
    		ps.setString(1, aux);
    		ps.setString(2, aux+"%");
    		ResultSet rs = ps.executeQuery();
    		while(rs.next()){
    			Tratamento trat = new Tratamento();
    			trat.setFK_paciente(rs.getInt("FK_paciente"));
    			trat.setNome_paciente(rs.getString("NomeCompleto"));
    			trat.setNumero_factura(rs.getString("numero_factura"));
    			trat.setFuncionario(rs.getString("n_c_funcionario"));
    			trat.setHora_registo(rs.getString("hora_da_facturacao"));
    			trat.setQuantidade(rs.getInt("qtd"));
    			trat.setTotal_pago_tratamento(rs.getDouble("total_pago"));
    			trat.setFK_factura(rs.getInt("FK_factura"));
    			trat.setId_facturar_tratamento(rs.getInt("id_facturar_tratamento"));
    			Calendar data = Calendar.getInstance();
    			data.setTime(rs.getDate("Data_da_facturacao"));
    			trat.setData_registo(data);
    			lsVenda.add(trat);
    			
    		}
    		
    	}catch(Exception e){
    		e.printStackTrace();
    	}
    	return lsVenda;
    }
    
    public List<Tratamento> getVendasTratamentosDev(){

    	String sql = "Select * from vwhistoricovendadevolucaofactgeral WHERE Data_da_facturacao BETWEEN SUBDATE(CURDATE(), INTERVAL 15 DAY) AND CURDATE()";
    	List<Tratamento> lsVenda = new ArrayList<Tratamento>();
    	try{
    		con = Conexao.getConexao();
    		PreparedStatement ps = con.prepareStatement(sql);
    		ResultSet rs = ps.executeQuery();
    		while(rs.next())
    		{
    			Tratamento trat = new Tratamento();
    			trat.setFK_paciente(rs.getInt("FK_paciente"));
    			trat.setNome_paciente(rs.getString("NomeCompleto"));
    			trat.setNumero_factura(rs.getString("numero_factura"));
    			trat.setFuncionario(rs.getString("n_c_funcionario"));
    			trat.setHora_registo(rs.getString("hora_da_facturacao"));
    			trat.setQuantidade(rs.getInt("qtd"));
    			trat.setQtd_sessao(rs.getInt("qtd_servico"));
    			trat.setTotal_pago_tratamento(rs.getDouble("total_pago"));
    			trat.setFK_factura(rs.getInt("FK_factura"));
    			trat.setId_facturar_tratamento(rs.getInt("id_facturar_tratamento"));
    			Calendar data = Calendar.getInstance();
    			data.setTime(rs.getDate("Data_da_facturacao"));
    			trat.setData_registo(data);
    			lsVenda.add(trat);
    			
    		}
    		
    	}catch(Exception e){
    		e.printStackTrace();
    	}
    	return lsVenda;
    }
    
    public List<Tratamento> getVendasTratamentosDev(String num){

    	String sql = "Select * from vwhistoricovendadevolucaofactgeral where numero_factura Like ? or NomeCompleto like ?";
    	List<Tratamento> lsVenda = new ArrayList<Tratamento>();
    	try{
    		con = Conexao.getConexao();
    		PreparedStatement ps = con.prepareStatement(sql);
    		ps.setString(1, num);
    		ps.setString(2, num+"%");
    		ResultSet rs = ps.executeQuery();
    		while(rs.next()){
    			Tratamento trat = new Tratamento();
    			trat.setFK_paciente(rs.getInt("FK_paciente"));
    			trat.setNome_paciente(rs.getString("NomeCompleto"));
    			trat.setNumero_factura(rs.getString("numero_factura"));
    			trat.setFuncionario(rs.getString("n_c_funcionario"));
    			trat.setHora_registo(rs.getString("hora_da_facturacao"));
    			trat.setQuantidade(rs.getInt("qtd"));
    			trat.setTotal_pago_tratamento(rs.getDouble("total_pago"));
    			trat.setFK_factura(rs.getInt("FK_factura"));
    			trat.setId_facturar_tratamento(rs.getInt("id_facturar_tratamento"));
    			Calendar data = Calendar.getInstance();
    			data.setTime(rs.getDate("Data_da_facturacao"));
    			trat.setData_registo(data);
    			lsVenda.add(trat);
    			
    		}
    		
    	}catch(Exception e){
    		e.printStackTrace();
    	}
    	return lsVenda;
    }
    
    public List<Tratamento> getVendasTratamentoItens(String codf){

    	String sql = "select * from vwrelpagamentoservicosgerais where id_factura = ? group by servico";
    	List<Tratamento> lsVenda = new ArrayList<Tratamento>();
    	try{
    		String fk_factura = codf;
    		con = Conexao.getConexao();
    		PreparedStatement ps = con.prepareStatement(sql);
    		ps.setString(1, fk_factura);
    		ResultSet rs = ps.executeQuery();
    		while(rs.next()){
    			Tratamento trat = new Tratamento();
    			trat.setFK_tratamento(rs.getInt("fk_servico"));
    			trat.setFK_factura(rs.getInt("id_factura"));
    			trat.setServico(rs.getString("servico"));
    			trat.setQuantidade(rs.getInt("qtd"));
    			trat.setPreco_tratamento(rs.getDouble("preco"));
    			//trat.setFK_factura(rs.getInt("id_factura"));
    			trat.setNumero_factura(rs.getString("numero_factura"));
    			trat.setTaxa(rs.getDouble("taxa"));
    			trat.setTaxa_emkz(rs.getDouble("taxa_kz"));
    			trat.setTotal_pago_tratamento(rs.getDouble("total"));
    			Calendar data = Calendar.getInstance();
    			data.setTime(rs.getDate("Data_da_facturacao"));
    			trat.setData_registo(data);
    			lsVenda.add(trat);
    			
    		}
    		
    	}catch(Exception e){
    		e.printStackTrace();
    	}
    	return lsVenda;
    }
    
    public Tratamento getVendasTratamentoUnico(String codf,String codt){

    	String sql = "Select * from vwtratamentolistaritemdafactura where id_factura = ? and FK_tratamento = ?  ";
    	Tratamento trat = new Tratamento(); 
    	try{
    		int fk_factura = Integer.parseInt(codf);
    		int fk_tratamento = Integer.parseInt(codt);
    		con = Conexao.getConexao();
    		PreparedStatement ps = con.prepareStatement(sql);
    		ps.setInt(1, fk_factura);
    		ps.setInt(2, fk_tratamento);
    		ResultSet rs = ps.executeQuery();
    		if(rs.next()){
    			
    			trat.setFK_tratamento(rs.getInt("FK_tratamento"));
    			trat.setServico(rs.getString("servico"));
    			trat.setQuantidade(rs.getInt("quantidade"));
    			trat.setPreco_tratamento(rs.getDouble("preco_tratamento"));
    			trat.setTaxa(rs.getDouble("imposto_selo"));
    			trat.setTaxa_emkz(rs.getDouble("taxa_emkz"));
    			trat.setTotal_pago_tratamento(rs.getDouble("total_pago_tratamento"));
    			trat.setFK_factura(rs.getInt("id_factura"));
    			trat.setNumero_factura(rs.getString("numero_factura"));
    			Calendar data = Calendar.getInstance();
    			data.setTime(rs.getDate("Data_da_facturacao"));
    			trat.setData_registo(data);
    			 
    			
    		}
    		
    	}catch(Exception e){
    		e.printStackTrace();
    	}
    	return trat;
    }
    
    public Tratamento getTratamentoDevolvido(int codDv,String codt){

    	String sql = "Select * from tbltratamentodevolvido where FK_devolucao_venda = ? and FK_trat_devolvido = ? ";
    	Tratamento trat = new Tratamento(); 
    	try{
    		 
    		int fk_tratamento = Integer.parseInt(codt);
    		con = Conexao.getConexao();
    		PreparedStatement ps = con.prepareStatement(sql);
    		ps.setInt(1, codDv);
    		ps.setInt(2, fk_tratamento);
    		ResultSet rs = ps.executeQuery();
    		if(rs.next()){
    			trat.setQuantidade(rs.getInt("quant_devolvida"));
    			trat.setTotal_pago_tratamento(rs.getDouble("valor_devolvido_trat"));
    			trat.setTaxa_emkz(rs.getDouble("taxa_trat_emkz"));
    		}
    		
    	}catch(Exception e){
    		e.printStackTrace();
    	}
    	return trat;
    }
    
	public Tratamento getCliente (String cod){
		
		Tratamento cliente = new Tratamento();
		String sql = "SELECT NomeCompleto, numero_factura FROM vwhistoricodevendatratamento where Fk_factura = ?";
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
	public int numerotratamento()
	{ 
		
		int fun = 0;
		String sql = "SELECT count(numero_factura) numero FROM tblfactura";
		try {
			con = Conexao.getConexao();
			PreparedStatement preparador = con.prepareStatement(sql);
			ResultSet rs = preparador.executeQuery();
			
			if(rs.next())
				fun = rs.getInt("numero");
			preparador.close();
		}
		catch(Exception e){}
		finally{
			try {
				con.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
			return fun+1;  
		
	}
}
