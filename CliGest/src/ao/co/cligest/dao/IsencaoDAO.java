package ao.co.cligest.dao;

import groovyjarjarasm.asm.tree.TryCatchBlockNode;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

import ao.co.cligest.entidades.Exames;
import ao.co.cligest.entidades.Isencao;
import ao.co.cligest.entidades.Paciente;

public class IsencaoDAO {

	private Connection con ;
	private Formatando ft = new Formatando();
	
	public String numIsencao(){
		String sql1 = "SELECT config_nome_factura as nomIsen FROM tblconfigurarnomefactura";
		String sql2 = "SELECT COUNT(*) AS TOTAL FROM tblisencaoprincipal";
		String nomIns = "";
		try{
			con  = Conexao.getConexao();
			PreparedStatement ps = con.prepareStatement(sql1);
			ResultSet rs = ps.executeQuery();
			if (rs.next()) {
				nomIns = rs.getString("nomIsen");
			}
			ps = con.prepareStatement(sql2);
			rs = ps.executeQuery();
			if (rs.next()) {
				nomIns = nomIns +"ISEN" +(rs.getInt("total")+1);
			}
		}catch(Exception e){
			e.printStackTrace();
		}
		return nomIns;
	}
	public List<Isencao> IsentarConsulta(int codc){
		List <Isencao> lista = new ArrayList<Isencao>();
		String sql = "Select * from vwconsultaconfirmada where id_consulta_confirmada = ?";
		try{
			 con = Conexao.getConexao();
			 PreparedStatement preparador = con.prepareStatement(sql);
			 preparador.setInt(1, codc);
			 ResultSet rs = preparador.executeQuery();
			 while(rs.next())
			 {
				 Isencao isen = new Isencao();
				 isen.setId_consulta_confirmada(rs.getInt("id_consulta_confirmada"));
				 isen.setNome(rs.getString("nome"));
				 isen.setApelido(rs.getString("ultimo_nome"));
				 isen.setServico(rs.getString("servico"));
				 isen.setId_servico(rs.getInt("id_servico"));
				 isen.setFK_doutor(rs.getInt("fK_doutor"));
				 isen.setFK_entidade_paciente(rs.getInt("fK_entidade_paciente"));
				 isen.setPreco(rs.getDouble("preco"));
				 isen.setFK_fornecedor(rs.getInt("fK_fornecedor"));
				 isen.setId_tipo_dcobertura(rs.getInt("fk_tipo_dcobertura"));
				 lista.add(isen);
			 }
			 preparador.close();
		} catch (SQLException e) {
			 
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
			 
		return lista;
	}
	
	public Isencao getConsultaIsenta(int codc){
		Isencao isen = null;
		String sql = "Select * from vwconsultaconfirmada where id_consulta_confirmada = ? and data_confirmacao = curdate()";
		try{
			 con = Conexao.getConexao();
			 PreparedStatement preparador = con.prepareStatement(sql);
			 preparador.setInt(1, codc);
			 ResultSet rs = preparador.executeQuery();
			 if(rs.next())
			 {
				 isen = new Isencao();
				 isen.setId_consulta_confirmada(rs.getInt("id_consulta_confirmada"));
				 isen.setNome(rs.getString("nome"));
				 isen.setApelido(rs.getString("ultimo_nome"));
				 isen.setFK_servico(rs.getInt("id_servico"));
				 isen.setPreco(rs.getDouble("sub_total"));
				 isen.setFK_taxa(rs.getInt("FK_taxa"));
				 isen.setPrecoUnitario(rs.getDouble("preco_unitario"));
				 isen.setTotal_taxa_emkz(rs.getDouble("taxa_emkz"));
				 isen.setTaxas(rs.getDouble("taxa"));
				 isen.setNumero_da_consulta(rs.getString("numero_da_consulta"));
				 isen.setFK_paciente(rs.getInt("fK_entidade_paciente"));				 
			 }
			 preparador.close();
		} catch (SQLException e) {
			 
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
			 
		return isen;
	}
	
	public List<Isencao> isentarExames(){
		List <Isencao> lista = new ArrayList<Isencao>();
		String sql = "select * from vwexameprontoaserisentado";
		try{
			 con = Conexao.getConexao();
			 PreparedStatement preparador = con.prepareStatement(sql);
			 ResultSet rs = preparador.executeQuery();
			 while(rs.next())
			 {
				 Isencao isen = new Isencao();
				 isen.setNumero_processo(rs.getString("numeroProcesso"));
				 isen.setNomeCompleto(rs.getString("nomeCompleto"));
				 isen.setPreco(rs.getDouble("total_pagar"));
				 isen.setFK_numero_servico(rs.getInt("FK_numero_do_servico_de_exame"));
				 isen.setQtd_exa_isentado(rs.getInt("qtd_exames"));
				 lista.add(isen);
			 }
			 preparador.close();
			 con.close();
		}
		catch (Exception e) {
			e.printStackTrace();
		}
			 
		return lista;
	}
	
	public List<Isencao> isentarReceitas(){
		List <Isencao> lista = new ArrayList<Isencao>();
		String sql = "select * from vwlistadereceitaisentar ";
		try{
			 con = Conexao.getConexao();
			 PreparedStatement preparador = con.prepareStatement(sql);
			 ResultSet rs = preparador.executeQuery();
			 while(rs.next())
			 {
				 Isencao isen = new Isencao();
				 isen.setNumero_doc(rs.getString("numero"));
				 System.out.println("nomero "+isen.getNumero_doc());
				 isen.setNomeCompleto(rs.getString("nomeCompleto"));
				 System.out.println("nome completo "+isen.getNomeCompleto());
				 isen.setTotal_s(""+rs.getDouble("total"));
				 isen.setQuantidade(rs.getInt("qtd"));
				 isen.setFK_receita(rs.getInt("id_receita"));
				 isen.setFK_paciente(rs.getInt("fk_paciente"));
				 isen.setNome_doutor(rs.getString("n_c_funcionario"));
				 lista.add(isen);
			 }
			 preparador.close();
			 con.close();
		}
		catch (Exception e) {
			e.printStackTrace();
		}
			 
		return lista;
	}
	
	public Isencao isentarReceita(String cod){
		 Isencao isen = new Isencao();
		String sql = "select * from vwlistadereceitaisentar where id_receita = ? ";
		try{
			 con = Conexao.getConexao();
			 PreparedStatement preparador = con.prepareStatement(sql);
			 preparador.setString(1, cod);
			 ResultSet rs = preparador.executeQuery();
			 if(rs.next())
			 {
				
				 isen.setNumero_doc(rs.getString("numero"));
				 isen.setNomeCompleto(rs.getString("nomeCompleto"));
				 isen.setTotal_s(""+rs.getDouble("total"));
				 isen.setQuantidade(rs.getInt("qtd"));
				 isen.setFK_receita(rs.getInt("id_receita"));
				 isen.setFK_paciente(rs.getInt("fk_paciente"));
				 isen.setTxa_percentual(rs.getDouble("taxa"));
				 
			 }
			 preparador.close();
			 con.close();
		}
		catch (Exception e) {
			e.printStackTrace();
		}
			 
		return isen;
	}
	
	public List<Isencao> isentarReceitasItens(String cod){
		List <Isencao> lista = new ArrayList<Isencao>();
		String sql = "select * from vwlistadereceitaafacturar WHERE id_receita = ? ";
		try{
			 con = Conexao.getConexao();
			 PreparedStatement preparador = con.prepareStatement(sql);
			 preparador.setString(1, cod);
			 ResultSet rs = preparador.executeQuery();
			 while(rs.next())
			 {
				 Isencao isen = new Isencao();
				 isen.setFK_produto(rs.getInt("id_produto"));
				 isen.setProduto(rs.getString("produto"));
				 isen.setPreco(rs.getDouble("preco_unitario"));
				 isen.setTotal_isentado(rs.getDouble("preco"));
				 isen.setQuantidade(rs.getInt("quantidade"));
				 isen.setTxa_percentual(rs.getDouble("taxa"));
				 isen.setFK_receita(rs.getInt("id_receita"));
				 lista.add(isen);
			 }
			 preparador.close();
			 con.close();
		}
		catch (Exception e) {
			e.printStackTrace();
		}
			 
		return lista;
	}
	public Isencao getExamesIsentar(String cod){
		Isencao isen = new Isencao();
		String sql = "select * from vwexameprontoaserisentado where FK_numero_do_servico_de_exame =? and data = curdate()";
		try{
			 con = Conexao.getConexao();
			 PreparedStatement preparador = con.prepareStatement(sql);
			 preparador.setString(1, cod);
			 ResultSet rs = preparador.executeQuery();
			 if(rs.next())
			 {
				 isen.setNumero_processo(rs.getString("numeroProcesso"));
				 isen.setNomeCompleto(rs.getString("nomeCompleto"));
				 isen.setPreco(rs.getDouble("total_pagar"));
				 isen.setNumero_daisencao(rs.getString("numero_do_exame"));
				 isen.setFK_numero_servico(rs.getInt("FK_numero_do_servico_de_exame"));
				 isen.setFK_paciente(rs.getInt("FK_paciente"));
				 isen.setTotal_taxa_emkz(rs.getDouble("total_taxa"));
			 }
			 preparador.close();
			 con.close();
		}
		catch (Exception e) {
			// TODO: handle exception
		}
	  
			 
		return isen;
	}
	
	public List<Exames> getServicosIsentar(String cod)
	{
		List<Exames> dados = new ArrayList<Exames>();
		String sql = "select * from vwservicosexamesrequisitado where FK_numero_do_servico_de_exame =? and data = curdate()";
		try{
			 con = Conexao.getConexao();
			 PreparedStatement preparador = con.prepareStatement(sql);
			 preparador.setString(1, cod);
			 ResultSet rs = preparador.executeQuery();
			 while(rs.next())
			 {
				 Exames exam = new Exames();
				 exam.setFK_servico_exame(rs.getInt("FK_servico_exame"));
				 exam.setAnalise_clinica(rs.getString("analise_clinica"));
				 exam.setPreco(rs.getDouble("preco"));
				 exam.setTaxa(rs.getString("taxa"));
				 dados.add(exam);
			 }
			 preparador.close();
			 con.close();
		}
		catch (Exception e) {
			e.printStackTrace();
		}
	  
			 
		return dados;
	}
	
	
	
	 
	public int isentarPaciente(Isencao isn) {
		 String sql = "Insert into tblisencaoprincipal (numero_daisencao,FK_categoria_isencao,total_taxa_emkz,total_isentado,FK_paciente,FK_funcionario,razao_isencao,data_isencao,hora_isencao) value (?,?,?,?,?,?,?,?,?)";
		 int ultimoId = 0;
		 try{
			 con = Conexao.getConexao();
			 PreparedStatement ps = con.prepareStatement(sql);
			 ps.setString(1, isn.getNumero_daisencao());
			 ps.setInt(2, isn.getId_categoriaisencao());
			 ps.setDouble(3, isn.getTotal_taxa_emkz());
			 ps.setDouble(4, isn.getTotal_isentado());
			 ps.setInt(5, isn.getFK_paciente());
			 ps.setInt(6, isn.getFK_funcionario());
			 ps.setString(7, isn.getMotivo());
			 ps.setDate(8, ft.data_registo());
			 ps.setString(9, ft.getHoraAtual());
			 ps.execute();
			 ResultSet rs = ps.executeQuery("SELECT LAST_INSERT_ID()");
			 if(rs.next())
	    	        ultimoId = rs.getInt(1);
		 }catch(Exception e){
			 e.printStackTrace();
		 }
		 return ultimoId;
	}
	
	public void isentarConsulta(Isencao isn) {
		 String sql = "Insert into tblisencaoconsulta (FK_isencao,FK_con_confirmada,FK_servico_consulta,preco,taxa_percentual,taxa_consulta_kz,preco_isencao_total) value (?,?,?,?,?,?,?)";
	 
		 try{
			 con = Conexao.getConexao();
			 PreparedStatement ps = con.prepareStatement(sql);
			 ps.setInt(1, isn.getFK_isencao());
			 ps.setInt(2, isn.getFK_consulta_confirmada());
			 ps.setInt(3, isn.getFK_servico());
			 ps.setDouble(4, isn.getPreco());
			 ps.setDouble(5, isn.getPerc_isento());
			 ps.setDouble(6, isn.getTaxa_consulta());
			 ps.setDouble(7, isn.getPreco_isencao_consulta());
			 ps.execute();
		 }catch(Exception e){
			 e.printStackTrace();
		 }
	 
	}
	
	public void isentarExames(Isencao isn) {
		 String sql = "Insert into tblisencaoexame (FK_isencao,FK_exame_isentado,taxa_exame,preco_isencao_exame,FK_requisicao_exame) value (?,?,?,?,?)";
	 
		 try{
			 con = Conexao.getConexao();
			 PreparedStatement ps = con.prepareStatement(sql);
			 ps.setInt(1, isn.getFK_isencao());
			 ps.setInt(2, isn.getFK_servico());
			 ps.setDouble(3, isn.getTaxa_exame());
			 ps.setDouble(4, isn.getPreco_isencao_exame());
			 ps.setInt(5, isn.getFK_numero_servico());
			 ps.execute();
		 }catch(Exception e){
			 e.printStackTrace();
		 }
	 
	}
	
	public void isentarTratamento(Isencao isn) {
		 String sql = "Insert into tblisencaotratamento (FK_isencao,FK_tratamento_isentado,preco_isencao_trat,qtd_trat_isentado,tx_trat_isentado,total_tx_trat_isent,tota_trat_isentado) value (?,?,?,?,?,?,?)";
		 try{
			 con = Conexao.getConexao();
			 PreparedStatement ps = con.prepareStatement(sql);
			 ps.setInt(1, isn.getFK_isencao());
			 ps.setInt(2, isn.getFK_tratamento_isentado());
			 ps.setDouble(3, isn.getPreco_isencao_trat());
			 ps.setInt(4, isn.getQtd_trat_isentado());
			 ps.setDouble(5, isn.getTx_trat_isentado());
			 ps.setDouble(6, isn.getTotal_tx_trat_isent());
			 ps.setDouble(7, isn.getTota_trat_isentado());
			 ps.execute();
		 }catch(Exception e){
			 e.printStackTrace();
		 }
	 
	}
	
	public void isentarProduto(Isencao isn) {
		 String sql = "Insert into tblisencaofarmaco (FK_isencao,FK_farmaco_isentado,preco_isencao,qtd_farmaco_isentado,tx_farmaco_isentado,total_tx_farm_isent,tota_farmaco_isentado) value (?,?,?,?,?,?,?)";
		 try{
			 con = Conexao.getConexao();
			 PreparedStatement ps = con.prepareStatement(sql);
			 ps.setInt(1, isn.getFK_isencao());
			 ps.setInt(2, isn.getFK_farmaco_isentado());
			 ps.setDouble(3, isn.getPreco_isencao());
			 ps.setInt(4, isn.getQtd_farmaco_isentado());
			 ps.setDouble(5, isn.getTx_farmaco_isentado());
			 ps.setDouble(6, isn.getTotal_tx_farm_isent());
			 ps.setDouble(7, isn.getTota_farmaco_isentado());
			 ps.execute();
		 }catch(Exception e){
			 e.printStackTrace();
		 }
	 
	}
	
	public void isentarReceita(Isencao isn) {
		 String sql = "Insert into tblisencaoreceita (FK_isencao_principal,FK_receita,FK_farmaco,quantidade,txa_percentual,txa_emkwanza,total_isentado,preco_rec) value (?,?,?,?,?,?,?,?)";
		 try{
			 con = Conexao.getConexao();
			 PreparedStatement ps = con.prepareStatement(sql);
			 ps.setInt(1, isn.getFK_isencao());
			 ps.setInt(2, isn.getFK_receita());
			 ps.setInt(3, isn.getFK_farmaco_isentado());
			 ps.setInt(4, isn.getQuantidade());
			 ps.setDouble(5, isn.getTxa_percentual());
			 ps.setDouble(6, isn.getTxa_emkwanza());
			 ps.setDouble(7, isn.getTotal_isentado());
			 ps.setDouble(8, isn.getPreco());
			 ps.execute();
		 }catch(Exception e){
			 e.printStackTrace();
		 }
	 
	}
	public List<Isencao> getHistoricoIsencao(){
		List <Isencao> lista = new ArrayList<Isencao>();
		String sql = "select * from vwhistoricoisencao";
		try{
			 con = Conexao.getConexao();
			 PreparedStatement preparador = con.prepareStatement(sql);
			 ResultSet rs = preparador.executeQuery();
			 while(rs.next())
			 {
				 Isencao isen = new Isencao();
				 isen.setId_isencao(rs.getInt("id_isencao"));
				 isen.setNumero_processo(rs.getString("numeroProcesso"));
				 isen.setNomeCompleto(rs.getString("nomeCompleto"));
				 isen.setTotal_isentado(rs.getDouble("total_isentado"));
				 isen.setCategoriaisencao(rs.getString("categoriaisencao"));
				 isen.setId_categoriaisencao(rs.getInt("id_categoriaisencao"));
				 Calendar data = Calendar.getInstance();
				 data.setTime(rs.getDate("data_isencao"));
				 isen.setData_isencao(data);
				 lista.add(isen);
			 }
			 preparador.close();
			 con.close();
		}
		catch (Exception e) {
			// TODO: handle exception
		}
			 
		return lista;
	}
	
	public Isencao getHistoricoIsencaoTop(int cod){
		Isencao isen = new Isencao();
		String sql = "select * from vwhistoricoisencao where id_isencao = ? ";
		try{
			 con = Conexao.getConexao();
			 PreparedStatement preparador = con.prepareStatement(sql);
			 preparador.setInt(1, cod);
			 ResultSet rs = preparador.executeQuery();
			 if(rs.next())
			 {
				 
				 isen.setId_isencao(rs.getInt("id_isencao"));
				 isen.setNumero_daisencao(rs.getString("numero_daisencao"));
				 isen.setNomeCompleto(rs.getString("nomeCompleto"));
				// isen.setNome(rs.getString("nome")+" "+rs.getString("nome_meio").charAt(0)+". "+rs.getString("ultimo_nome"));
				 isen.setHora_registo(rs.getString("hora_isencao"));
				 isen.setMotivo(rs.getString("razao_isencao"));
				 System.out.println(isen.getMotivo()+" ok "+rs.getString("razao_isencao"));
				 Calendar data = Calendar.getInstance();
				 data.setTime(rs.getDate("data_isencao"));
				 isen.setData_isencao(data);
				 
			 }
			 preparador.close();
			 con.close();
		}
		catch (Exception e) {
			e.printStackTrace();
		}
			 
		return isen;
	}
	
	public List<Isencao> getHisctoricoItensTratamento(int cod){
		String sql = " select * from vwhistoriocoisencaoitemtratamento where id_isencao = ? ";
		List<Isencao> lista = new ArrayList<Isencao>();
		try {
			con = Conexao.getConexao();
			PreparedStatement ps = con.prepareStatement(sql);
			ps.setInt(1, cod);
			ResultSet rs = ps.executeQuery();
			while(rs.next()){
				Isencao ins = new Isencao();
				ins.setId_isencao(rs.getInt("id_isencao"));
				ins.setFK_tratamento_isentado(rs.getInt("FK_tratamento_isentado"));
				ins.setServico(rs.getString("servico"));
				ins.setPreco_isencao_trat(rs.getDouble("preco_isencao_trat"));
				ins.setQtd_trat_isentado(rs.getInt("qtd_trat_isentado"));
				ins.setTaxas(rs.getDouble("taxa_sg"));
				ins.setTxa_emkwanza(rs.getDouble("tx_trat_isentado"));
				ins.setTota_trat_isentado(rs.getDouble("tota_trat_isentado"));
				lista.add(ins);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return lista;
	}
	
	public List<Isencao> getHisctoricoItensConsulta(int cod){
		String sql = "select * from vwhistoriocoisencaoitemconsulta where id_isencao = ? ";
		List<Isencao> lista = new ArrayList<Isencao>();
		try {
			con = Conexao.getConexao();
			PreparedStatement ps = con.prepareStatement(sql);
			ps.setInt(1, cod);
			ResultSet rs = ps.executeQuery();
			while(rs.next()){
				Isencao ins = new Isencao();
				ins.setServico(rs.getString("servico"));
				ins.setPreco_isencao_consulta(rs.getDouble("preco_unitario"));
				ins.setTxa_emkwanza(rs.getDouble("taxa_consulta"));
				ins.setTota_trat_isentado(rs.getDouble("total_isentado"));
				 
				lista.add(ins);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return lista;
	}
	
	public List<Isencao> getHisctoricoItensExames(int cod){
		String sql = "select * from vwhistoriocoisencaoitemexame where id_isencao = ? ";
		List<Isencao> lista = new ArrayList<Isencao>();
		try {
			con = Conexao.getConexao();
			PreparedStatement ps = con.prepareStatement(sql);
			ps.setInt(1, cod);
			ResultSet rs = ps.executeQuery();
			while(rs.next()){
				Isencao ins = new Isencao();
				ins.setServico(rs.getString("ana_clin"));
				ins.setPreco_isencao_exame(rs.getDouble("preco"));
				ins.setTxa_emkwanza(rs.getDouble("taxa"));
				ins.setTota_trat_isentado(rs.getDouble("subtotal"));
				lista.add(ins);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return lista;
	}
	
	public List<Isencao> getHisctoricoItensProduto(int cod){
		String sql = "select * from vwhistoriocoisencaoitemproduto where id_isencao = ? ";
		List<Isencao> lista = new ArrayList<Isencao>();
		try {
			con = Conexao.getConexao();
			PreparedStatement ps = con.prepareStatement(sql);
			ps.setInt(1, cod);
			ResultSet rs = ps.executeQuery();
			while(rs.next()){
				Isencao ins = new Isencao();
				ins.setServico(rs.getString("nome_comercial"));
				ins.setPreco_isencao(rs.getDouble("preco_isencao"));
				ins.setTxa_emkwanza(rs.getDouble("tx_farmaco_isentado"));
				ins.setTota_farmaco_isentado(rs.getDouble("tota_farmaco_isentado"));
				ins.setQtd_farmaco_isentado(rs.getInt("qtd_farmaco_isentado"));
				lista.add(ins);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return lista;
	}
	
	public List<Isencao> getHisctoricoItensReceita(int cod){
		String sql = "select * from vwhistoriocoisencaoitemreceita where id_isencao = ? ";
		List<Isencao> lista = new ArrayList<Isencao>();
		try {
			con = Conexao.getConexao();
			PreparedStatement ps = con.prepareStatement(sql);
			ps.setInt(1, cod);
			ResultSet rs = ps.executeQuery();
			while(rs.next()){
				Isencao ins = new Isencao();
				ins.setServico(rs.getString("nome_comercial"));
				ins.setPreco_isencao(rs.getDouble("preco_rec"));
				ins.setTxa_emkwanza(rs.getDouble("txa_percentual"));
				ins.setQtd_farmaco_isentado(rs.getInt("quantidade"));
				ins.setTota_farmaco_isentado(rs.getDouble("total_isentado"));
				lista.add(ins);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return lista;
	}
	
	public List<Isencao> getHistoricoIsencaoData(Date data1){
		List <Isencao> lista = new ArrayList<Isencao>();
		String sql = "select * from vwhistoricoisencao where data_isencao between ? and curdate() order by id_isencao";
		try{
			 con = Conexao.getConexao();
			 PreparedStatement preparador = con.prepareStatement(sql);
			 preparador.setDate(1, data1);
			 ResultSet rs = preparador.executeQuery();
			 while(rs.next())
			 {
				 Isencao isen = new Isencao();
				 isen.setId_isencao(rs.getInt("id_isencao"));
				 isen.setNumero_processo(rs.getString("numeroProcesso"));
				 isen.setNomeCompleto(rs.getString("nomeCompleto"));
				 isen.setTotal_isentado(rs.getDouble("total_isentado"));
				 isen.setCategoriaisencao(rs.getString("categoriaisencao"));
				 isen.setId_categoriaisencao(rs.getInt("id_categoriaisencao"));
				 Calendar data = Calendar.getInstance();
				 data.setTime(rs.getDate("data_isencao"));
				 isen.setData_isencao(data);
				 lista.add(isen);
			 }
			 preparador.close();
			 con.close();
		}
		catch (Exception e) {
			// TODO: handle exception
		}
			 
		return lista;
	}
	
	public List<Isencao> getHistoricoIsencaoData(Date data1,Date data2){
		List <Isencao> lista = new ArrayList<Isencao>();
		String sql = "select * from vwhistoricoisencao where data_isencao between ? and ? order by id_isencao";
		try{
			 con = Conexao.getConexao();
			 PreparedStatement preparador = con.prepareStatement(sql);
			 preparador.setDate(1, data1);
			 preparador.setDate(2, data2);
			 ResultSet rs = preparador.executeQuery();
			 while(rs.next())
			 {
				 Isencao isen = new Isencao();
				 isen.setId_isencao(rs.getInt("id_isencao"));
				 isen.setNumero_processo(rs.getString("numeroProcesso"));
				 isen.setNomeCompleto(rs.getString("nomeCompleto"));
				 isen.setTotal_isentado(rs.getDouble("total_isentado"));
				 isen.setCategoriaisencao(rs.getString("categoriaisencao"));
				 isen.setId_categoriaisencao(rs.getInt("id_categoriaisencao"));
				 Calendar data = Calendar.getInstance();
				 data.setTime(rs.getDate("data_isencao"));
				 isen.setData_isencao(data);
				 lista.add(isen);
			 }
			 preparador.close();
			 con.close();
		}
		catch (Exception e) {
			// TODO: handle exception
		}
			 
		return lista;
	}
	
	public List<Isencao> getHistoricoIsencaoDia(){
		List <Isencao> lista = new ArrayList<Isencao>();
		String sql = "select * from vwhistoricoisencao where data_isencao = curdate() order by id_isencao";
		try{
			 con = Conexao.getConexao();
			 PreparedStatement preparador = con.prepareStatement(sql);
			 ResultSet rs = preparador.executeQuery();
			 while(rs.next())
			 {
				 Isencao isen = new Isencao();
				 isen.setId_isencao(rs.getInt("id_isencao"));
				 isen.setNumero_processo(rs.getString("numeroProcesso"));
				 isen.setNomeCompleto(rs.getString("nomeCompleto"));
				 isen.setTotal_isentado(rs.getDouble("total_isentado"));
				 isen.setCategoriaisencao(rs.getString("categoriaisencao"));
				 isen.setId_categoriaisencao(rs.getInt("id_categoriaisencao"));
				 Calendar data = Calendar.getInstance();
				 data.setTime(rs.getDate("data_isencao"));
				 isen.setData_isencao(data);
				 lista.add(isen);
			 }
			 preparador.close();
			 con.close();
		}
		catch (Exception e) {
			// TODO: handle exception
		}
			 
		return lista;
	}
}
