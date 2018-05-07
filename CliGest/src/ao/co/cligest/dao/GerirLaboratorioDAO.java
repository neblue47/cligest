package ao.co.cligest.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

import ao.co.cligest.entidades.Produtos;
import ao.co.cligest.entidades.RequisicaoInterna;
import ao.co.cligest.interfaces.IProcedimentoGerirStock;

public class GerirLaboratorioDAO implements IProcedimentoGerirStock {

	private Connection con;
	Formatando ft = new Formatando();
	
	// 1-INSERIR PRODUTOS

		public int RequisicaoProdutos(RequisicaoInterna pd) {
			int ultimoCod = 0;
			String sql = "INSERT INTO tblrequisicaointernageral (numero_requi_interna, obs_requi_interna, fk_funcionario, data_requi_interna,FK_requerente_interno) VALUES (?,?,?,?,?)";
			try {
				con = Conexao.getConexao();
				PreparedStatement cp = con.prepareStatement(sql);
				cp.setString(1, pd.getNumero_requi_interna());
				cp.setString(2, pd.getObs_requi_interna());
				cp.setDouble(3, pd.getFk_funcionario());
				cp.setDate(4, new Formatando().data_registo());
				cp.setInt(5, pd.getFK_requerente_interno());
				cp.execute();
				ResultSet rs = cp.executeQuery("SELECT LAST_INSERT_ID()");
				if (rs.next()) {
					ultimoCod = rs.getInt(1);
				}
				cp.close();
				// System.out.println("Cadastro de produtos efectuado Requisição com sucesso!..");
			} catch (SQLException e) {
				// TODO Auto-generated catch block
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
			return ultimoCod;
		}
	
		public void RequisicaoProdutosItens(RequisicaoInterna pd) {
			String sql = "INSERT INTO tblrequisicaointernageralporitem (FK_requi_interna,fk_produto_requi_interna,qtd_requi_interna) VALUES (?,?,?)";
			try {
				con = Conexao.getConexao();
				PreparedStatement cp = con.prepareStatement(sql);
				cp.setInt(1, pd.getFK_requi_interna());
				cp.setInt(2, pd.getFk_produto_requi_interna());
				cp.setInt(3, pd.getQtd_requi_interna());
				cp.execute();
				cp.close();
				// System.out.println("Cadastro de produtos efectuado Requisição com sucesso!..");
			} catch (SQLException e) {
				// TODO Auto-generated catch block
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
		}
		
	public int numero() {

		int fun = 0;
		String sql = "SELECT count(id_requi_interna) numero FROM tblrequisicaointernageral";
		try {
			con = Conexao.getConexao();
			PreparedStatement preparador = con.prepareStatement(sql);
			ResultSet rs = preparador.executeQuery();

			if (rs.next())
				fun = rs.getInt("numero");
		} catch (Exception e) {
		} finally {
			try {
				con.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}

		}
		return fun + 1;
	}
	
//	2-Visualizar produtos
	public List <Produtos> buscarProdutos ()
	{
		List <Produtos> lista = new ArrayList<Produtos>();
		String sql = "SELECT (CASE WHEN codigo_barra = '' THEN '000000000000' ELSE codigo_barra END) AS codigo_barra, id_produto, nome_comercial, forma_farmaceutica, dosagem, preco_dcompra, preco_dvenda, taxa, fk_grupo_farmaceutico FROM vwlistadeprodutoscadastrados";
		try {
			con = Conexao.getConexao();
			 PreparedStatement preparador = con.prepareStatement(sql);
			 ResultSet rs = preparador.executeQuery();
			 while(rs.next())
			 {
				 Produtos pd = new Produtos();
				 pd.setCodigo_barra(rs.getString("codigo_barra"));
				 pd.setId_produto(rs.getInt("id_produto"));
				 pd.setNome_comercial(rs.getString("nome_comercial"));
				 pd.setValor_forma_farmaceutica(rs.getString("forma_farmaceutica"));
				 pd.setValor_dosagem(rs.getString("dosagem"));
				 pd.setPreco_dcompra(rs.getDouble("preco_dcompra"));
				 pd.setPreco_dvenda(rs.getDouble("preco_dvenda"));
				 pd.setTaxa(rs.getInt("taxa"));
				 pd.setFk_grupo(rs.getInt("fk_grupo_farmaceutico"));
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
	
	public List <Produtos> buscarProdutos (String aux)
	{
		List <Produtos> lista = new ArrayList<Produtos>();
		String sql = "SELECT * FROM vwlistadeprodutoscadastrados where nome_comercial Like ?  order by id_produto ";
		try {
			con = Conexao.getConexao();
			 PreparedStatement preparador = con.prepareStatement(sql);
			 preparador.setString(1, aux+"%");
			 ResultSet rs = preparador.executeQuery();
			 while(rs.next())
			 {
				 Produtos pd = new Produtos();
				 pd.setId_produto(rs.getInt("id_produto"));
				 pd.setNome_comercial(rs.getString("nome_comercial"));
				 pd.setValor_forma_farmaceutica(rs.getString("forma_farmaceutica"));
				 pd.setValor_dosagem(rs.getString("dosagem"));
				 pd.setPreco_dcompra(rs.getDouble("preco_dcompra"));
				 pd.setPreco_dvenda(rs.getDouble("preco_dvenda"));
				 pd.setTaxas(rs.getDouble("taxa"));
				 pd.setTaxa_emkz(rs.getDouble("taxa_emkz"));
				 lista.add(pd);
			 }
			 preparador.close();
			 con.close();
		}  
		catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		 
		
		return lista;
	}
	
	public List <Produtos> buscarProdutosEmEstoque ()
	{
		List <Produtos> lista = new ArrayList<Produtos>();
		String sql = "SELECT (CASE WHEN codigo_barra = '' THEN '000000000000' ELSE codigo_barra END) AS codigo_barra, fk_id_produto, nome_comercial, nome_quimico, forma_farmaceutica, dosagem, numero_dolote_sere, data_decaducidade, preco_dcompra, preco_dvenda, quantidade_produto FROM vwprodutoemestoquelaboratorio order by nome_comercial";
		try {
			con = Conexao.getConexao();
			 PreparedStatement preparador = con.prepareStatement(sql);
			 ResultSet rs = preparador.executeQuery();
			 while(rs.next())
			 {
				 Produtos pd = new Produtos();
				 pd.setCodigo_barra(rs.getString("codigo_barra"));
				 pd.setId_produto(rs.getInt("fk_id_produto"));
				 pd.setNome_comercial(rs.getString("nome_comercial"));
				 pd.setNome_quimico(rs.getString("nome_quimico"));
				 pd.setValor_forma_farmaceutica(rs.getString("forma_farmaceutica"));
				 pd.setValor_dosagem(rs.getString("dosagem"));
				 pd.setNumero_lote_sere(rs.getString("numero_dolote_sere"));
				 Calendar data = Calendar.getInstance();
				 data.setTime(rs.getDate("data_decaducidade"));
				 pd.setData_expiracao(data);
				 pd.setPreco_dcompra(rs.getDouble("preco_dcompra"));
				 pd.setPreco_dvenda(rs.getDouble("preco_dvenda"));
				 pd.setQuantidade(rs.getInt("quantidade_produto"));
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
	
	//total de tipo de produtos em estoque
		public List <Produtos> buscarTotalTipoProdutosEmEstoque ()
		{
			List <Produtos> bttpee = new ArrayList<Produtos>();
			String sql = "SELECT count(codigo_barra) t_p_laboratorio FROM vwtotalportipodeprodutolaboratorio;";
			try {
				con = Conexao.getConexao();
				 PreparedStatement preparador = con.prepareStatement(sql);
				 ResultSet rs = preparador.executeQuery();
				 while(rs.next())
				 {
					 Produtos pdbttpee = new Produtos();
					 pdbttpee.setTotalportipodeproduto(rs.getInt("t_p_laboratorio"));
					 bttpee.add(pdbttpee);
				 }
				 preparador.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}catch (Exception e) {
				e.printStackTrace();
			}finally{
				try{
					con.close();
				}catch(SQLException ef){
					System.out.println("Erro finalizar: "+ef);
				}
			}
			return bttpee;
		}
		
		public Produtos getProdutoPerfil(String codp)
		{
	    	
			Produtos prod = new Produtos();
	    	String sql = "SELECT id_produto, (CASE WHEN codigo_barra = '' THEN '000000000000' ELSE codigo_barra END) AS codigo_barra, nome_comercial, nome_quimico, forma_farmaceutica, dosagem, embalagem, preco_dcompra, preco_dvenda, taxa, (CASE WHEN requer_receita = 0 THEN 'Não' ELSE 'Sim' END) AS requer_receita, (CASE WHEN inactivo = 0 THEN 'Não' ELSE 'Sim' END) AS inactivo FROM vwlistadeprodutoscadastrados WHERE id_produto = ?";
	    	try{
	    		con = Conexao.getConexao();
	    		PreparedStatement ps = con.prepareStatement(sql);
	    		ps.setString(1, codp);
	    		ResultSet rs = ps.executeQuery();
	    		if(rs.next()){
	    		prod.setId(rs.getInt("id_produto"));
	    		prod.setCodigo_barra(rs.getString("codigo_barra"));
	    		prod.setNome_comercial(rs.getString("nome_comercial"));
	    		prod.setNome_quimico(rs.getString("nome_quimico"));
	    		prod.setForma_farmaceutica(rs.getString("forma_farmaceutica"));
	    		prod.setValor_dosagem(rs.getString("dosagem"));;
	    		prod.setValor_embalagem(rs.getString("embalagem"));
	    		prod.setPreco_dcompra(rs.getDouble("preco_dcompra"));
	    		prod.setPreco_dvenda(rs.getDouble("preco_dvenda"));
	    		prod.setTaxa(rs.getInt("taxa"));
	    		prod.setRequer_receitas(rs.getString("requer_receita"));
	    		prod.setEstadoinactivo(rs.getString("inactivo"));
	    		
	    			/*int estato = rs.getInt("inactivo"); 
		    			if(estato > 0){
		    				prod.setEstadoinactivo("Sim");
		    			}else{
		    				prod.setEstadoinactivo("Não");
		    			}*/
	    		}
	    	}catch(SQLException er){
	    		er.printStackTrace();
	    	}
	    	return prod;
	    }
		
		public String foto(int id)
		{
			String sql = "SELECT imagem FROM vwlistadeprodutoscadastrados WHERE id_produto = ?";
			String ficheiro="usuarios.jpg";
			try {
				con = Conexao.getConexao();
				PreparedStatement ent = con.prepareStatement(sql);
				ent.setInt(1,id);
//				ent.setInt(2, 1);
				ResultSet rs = ent.executeQuery();
				if(rs.next())
					ficheiro = rs.getString("imagem");
				ent.close();
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
			return ficheiro;
		}
		
		// Caducidade farmacia
		public List<RequisicaoInterna> buscarProdutoEmStockNaFarmacia() {
			List<RequisicaoInterna> lista = new ArrayList<RequisicaoInterna>();
			String sql = "SELECT * FROM vwprodutosemstocknafarmaciapresteacaducar";
			try {
				con = Conexao.getConexao();
				PreparedStatement preparador = con.prepareStatement(sql);
				ResultSet rs = preparador.executeQuery();
				while (rs.next()) {
					RequisicaoInterna pd = new RequisicaoInterna();
					pd.setCodigo_barra(rs.getString("codigo_barra"));
					pd.setFk_produto(rs.getInt("fk_produto"));
					pd.setNome_comercial(rs.getString("nome_comercial"));
					pd.setNum_lote(rs.getString("lote_sere"));
					Calendar data = Calendar.getInstance();
					pd.setData_requi_interna(data);
					Calendar data1 = Calendar.getInstance();
					data1.setTime(rs.getDate("data_caducidade"));
					pd.setData_registo(data1);
					pd.setDias_restante(rs.getInt("dias_restantes"));
					pd.setQtd_requi_interna(rs.getInt("quantidade_produto"));
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
		
		public List<RequisicaoInterna> buscarProdutoEmStockNaFarmacia(String nome) {
			List<RequisicaoInterna> lista = new ArrayList<RequisicaoInterna>();
			String sql = "SELECT * FROM vwprodutosemstocknafarmaciapresteacaducar where nome_comercial like ? or lote_sere =?";
			try {
				con = Conexao.getConexao();
				PreparedStatement preparador = con.prepareStatement(sql);
				preparador.setString(1, "%" + nome + "%");
				preparador.setString(2, nome);
				ResultSet rs = preparador.executeQuery();
				while (rs.next()) {
					RequisicaoInterna pd = new RequisicaoInterna();
					pd.setCodigo_barra(rs.getString("codigo_barra"));
					pd.setFk_produto(rs.getInt("fk_produto"));
					pd.setNome_comercial(rs.getString("nome_comercial"));
					pd.setNum_lote(rs.getString("lote_sere"));
					Calendar data = Calendar.getInstance();
					pd.setData_requi_interna(data);
					Calendar data1 = Calendar.getInstance();
					data1.setTime(rs.getDate("data_caducidade"));
					pd.setData_registo(data1);
					pd.setDias_restante(rs.getInt("dias_restantes"));
					pd.setQtd_requi_interna(rs.getInt("quantidade_produto"));
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

		public List<RequisicaoInterna> buscarProdutoEmStockNaFarmacias(String nome) {
			List<RequisicaoInterna> lista = new ArrayList<RequisicaoInterna>();
			String sql = "SELECT * FROM vwprodutosemstocknafarmaciapresteacaducar where dias_restantes =?";
			try {
				con = Conexao.getConexao();
				PreparedStatement preparador = con.prepareStatement(sql);
				preparador.setString(1, nome);
				ResultSet rs = preparador.executeQuery();
				while (rs.next()) {
					RequisicaoInterna pd = new RequisicaoInterna();
					pd.setCodigo_barra(rs.getString("codigo_barra"));
					pd.setFk_produto(rs.getInt("fk_produto"));
					pd.setNome_comercial(rs.getString("nome_comercial"));
					pd.setNum_lote(rs.getString("lote_sere"));
					Calendar data = Calendar.getInstance();
					pd.setData_requi_interna(data);
					Calendar data1 = Calendar.getInstance();
					data1.setTime(rs.getDate("data_caducidade"));
					pd.setData_registo(data1);
					pd.setDias_restante(rs.getInt("dias_restantes"));
					pd.setQtd_requi_interna(rs.getInt("quantidade_produto"));
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
	
//		Requisição feita visualizar os dados.
		public List<RequisicaoInterna> buscarRequisicaoFeitas() {
			List<RequisicaoInterna> listaEncomenda = new ArrayList<RequisicaoInterna>();
			String sql = "SELECT * FROM vwrequisicoesinternasgeraishistorico where id_requerente_interno = 6 ";
			try {
				con = Conexao.getConexao();
				PreparedStatement preparador = con.prepareStatement(sql);
				ResultSet rs = preparador.executeQuery();
				while (rs.next()) {
					RequisicaoInterna pd = new RequisicaoInterna();
						pd.setNomeRequerente_interno(rs.getString("requerente_interno"));
						pd.setNumero_requi_interna(rs.getString("numero_requi_interna"));
						pd.setObs_requi_interna(rs.getString("obs_requi_interna"));
						pd.setQtd_requi_interna(rs.getInt("qtd_requi_interna"));
						pd.setNome_funcionario(rs.getString("n_c_funcionario"));
						pd.setTotal(rs.getDouble("total_preco_dcompra"));
						pd.setQtd_requi_interna(rs.getInt("qtd_requi_proce_farma_porlinha"));
						pd.setFK_requi_interna(rs.getInt("id_requi_interna"));
						Calendar data = Calendar.getInstance();
						data.setTime(rs.getDate("data_requi_interna"));
						pd.setData_requi_interna(data);
					listaEncomenda.add(pd);
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
			return listaEncomenda;
		}
		
		public List<Produtos> buscarEncomendas() {
			List<Produtos> listaEncomenda = new ArrayList<Produtos>();
			String sql = "SELECT * FROM vwrequisicaoporprocessargeral where fk_requerente = 4 order by id_requi_proce_farmacia DESC";
			try {
				con = Conexao.getConexao();
				PreparedStatement preparador = con.prepareStatement(sql);
				ResultSet rs = preparador.executeQuery();
				while (rs.next()) {
					Produtos pd = new Produtos();
					pd.setId_numEncomenda(rs.getInt("id_requi_proce_farmacia"));
					pd.setNumEncomenda(rs.getString("numero_requi_proce_farmacia"));
					pd.setFornecedor(rs.getString("n_c_funcionario"));
					pd.setRequisicao_original(rs.getString("requisicao_original"));
					pd.setTotal(rs.getInt("total_por_eceber"));
					pd.setTotalEncomenda(rs.getDouble("total_processado"));
					Calendar data = Calendar.getInstance();
					data.setTime(rs.getDate("data_requi_proce_farmacia"));
					pd.setDataCriacao(data);
					listaEncomenda.add(pd);
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
			return listaEncomenda;
		}
		
		public List<Produtos> buscarProdutosEncomendados(int cod) {
			List<Produtos> lista = new ArrayList<Produtos>();
			String sql = "SELECT * FROM vwrequisicaoporprocessargeralporlinha where id_requi_proce_farmacia = ? and fk_requerente = 4 order by nome_comercial";
			try {
				con = Conexao.getConexao();
				PreparedStatement preparador = con.prepareStatement(sql);
				preparador.setInt(1, cod);
				ResultSet rs = preparador.executeQuery();
				while (rs.next()) {
					Produtos pd = new Produtos();
					pd.setFK_num_encomenda(rs.getInt("id_requi_proce_farmacia"));
					pd.setCodigo_barra(rs.getString("codigo_barra"));
					pd.setId_produto(rs.getInt("id_produto"));
					pd.setNome_comercial(rs.getString("nome_comercial"));
					pd.setQuantidade(rs.getInt("qtd_requi_proce_farma_porlinha"));
					pd.setQdt_recebida(rs.getInt("qtd_por_receber"));
					// pd.setNota(rs.getString("numero_requi_interna"));
					Calendar data = Calendar.getInstance();
					data.setTime(rs.getDate("data_requi_proce_farmacia"));
					pd.setDataCriacao(data);
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
		
		public Produtos buscarProdutoEncomendado(int cod, int codp) {
			Produtos pd = new Produtos();
			String sql = "SELECT * FROM vwrequisicaoporprocessargeralporlinha where id_requi_proce_farmacia = ? and id_produto = ? and fk_requerente = 4";
			try {
				con = Conexao.getConexao();
				PreparedStatement preparador = con.prepareStatement(sql);
				preparador.setInt(1, cod);
				preparador.setInt(2, codp);
				ResultSet rs = preparador.executeQuery();
				if (rs.next()) {
					pd.setFK_num_encomenda(rs.getInt("id_requi_proce_farmacia"));
					pd.setCodigo_barra(rs.getString("codigo_barra"));
					pd.setId_produto(rs.getInt("id_produto"));
					pd.setNome_comercial(rs.getString("nome_comercial"));
					pd.setQuantidade(rs.getInt("qtd_requi_proce_farma_porlinha"));
					pd.setQdt_recebida(rs.getInt("qtd_por_receber"));
					Calendar data = Calendar.getInstance();
					data.setTime(rs.getDate("data_requi_proce_farmacia"));
					pd.setDataCriacao(data);
				}
				preparador.close();
			} catch (SQLException e) {
				e.printStackTrace();
			} catch (Exception e) {
				e.printStackTrace();
			} finally {
				try {
					con.close();
				} catch (SQLException ef) {
					System.out.println("Erro finalizar: " + ef);
				}
			}
			return pd;
		}
		
		public List<Produtos> buscarProdutosEstoque() {
			List<Produtos> lista = new ArrayList<Produtos>();
			String sql = "SELECT FK_id_produto, (CASE WHEN codigo_barra = '' THEN '000000000000' ELSE codigo_barra END) AS codigo_barra, nome_comercial, nome_quimico, quantidade_produto, numero_dolote_sere, preco_dcompra, dosagem, data_decaducidade FROM vwprodutoemestoque order by nome_comercial asc";
			try {
				con = Conexao.getConexao();
				PreparedStatement preparador = con.prepareStatement(sql);
				ResultSet rs = preparador.executeQuery();
				while (rs.next()) {
					Produtos pd = new Produtos();
					pd.setId_produto(rs.getInt("FK_id_produto"));
					pd.setCodigo_barra(rs.getString("codigo_barra"));
					pd.setNome_comercial(rs.getString("nome_comercial"));
					pd.setNome_quimico(rs.getString("nome_quimico"));
					pd.setQuantidade(rs.getInt("quantidade_produto"));
					pd.setNumero_lote_sere(rs.getString("numero_dolote_sere"));
					pd.setPreco_dcompra(rs.getDouble("preco_dcompra"));
					pd.setValor_dosagem(rs.getString("dosagem"));
					Calendar data = Calendar.getInstance();
					data.setTime(rs.getDate("data_decaducidade"));
					pd.setDataCriacao(data);
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
		
//		Histórico de ajuste
		public List<RequisicaoInterna> buscarAjusteProduto() {
			List<RequisicaoInterna> listaEncomenda = new ArrayList<RequisicaoInterna>();
			String sql = "SELECT * FROM vwhistoricodeajustesfarmacia order by numero_histo_ajust_farmacia desc ";
			try {
				con = Conexao.getConexao();
				PreparedStatement preparador = con.prepareStatement(sql);
				ResultSet rs = preparador.executeQuery();
				while (rs.next()) {
					RequisicaoInterna pd = new RequisicaoInterna();
					    pd.setId_requi_interna(rs.getInt("id_histo_ajust_farmacia"));
						pd.setNumero_requi_interna(rs.getString("numero_histo_ajust_farmacia"));
						pd.setTotal(rs.getDouble("total_ajustado__histo_ajust_farmacia"));
						pd.setPreco_dcompra(rs.getDouble("preco_compra_histo_ajust_farma_linha"));
						pd.setNome_funcionario(rs.getString("n_c_funcionario"));
						pd.setDiferenca_positiva(rs.getInt("positivo_histo_ajust_farma_linha"));
						pd.setDiferenca_negativa(rs.getInt("negativo_histo_ajust_farma_linha"));
						Calendar data = Calendar.getInstance();
						data.setTime(rs.getDate("data_histo_ajust_farmacia"));
						pd.setData_requi_interna(data);
					listaEncomenda.add(pd);
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
			return listaEncomenda;
		}
		
		public List<Produtos> buscarHistAjustModal(String prodId) {
			List<Produtos> lista = new ArrayList<Produtos>();
			String sql = "SELECT * FROM vwhistoricodeajustesfarmaciamodal where id_histo_ajust_farmacia = ?";
			try {
				con = Conexao.getConexao();
				PreparedStatement preparador = con.prepareStatement(sql);
				preparador.setString(1, prodId);
				ResultSet rs = preparador.executeQuery();
				while (rs.next()) {
					Produtos pd = new Produtos();
					pd.setFK_num_encomenda(rs.getInt("id_histo_ajust_farmacia"));
					pd.setNum_encomenda(rs.getString("numero_histo_ajust_farmacia"));
					pd.setNome_comercial(rs.getString("nome_comercial"));
					pd.setQuantidade(rs.getInt("positivo_histo_ajust_farma_linha"));
					pd.setTotal(rs.getInt("negativo_histo_ajust_farma_linha"));
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
		

//		Histórico Devolução cussunga
		public List<RequisicaoInterna> buscarRequisicaoDevolucao() {
			List<RequisicaoInterna> listaEncomenda = new ArrayList<RequisicaoInterna>();
			String sql = "SELECT * FROM vwprodutosdevolvidosapartirdafarmacia ";
			try {
				con = Conexao.getConexao();
				PreparedStatement preparador = con.prepareStatement(sql);
				ResultSet rs = preparador.executeQuery();
				while (rs.next()) {
					RequisicaoInterna pd = new RequisicaoInterna();
						pd.setNumero_requi_interna(rs.getString("numero_dev_requi_int_farmacia"));
						pd.setRazao_dev_requi_int_farmacia(rs.getString("razao_dev_requi_int_farmacia"));
						pd.setQtd_requi_interna(rs.getInt("qtd_dev_requi_int_farmacia"));
						pd.setNome_funcionario(rs.getString("n_c_funcionario"));
						Calendar data = Calendar.getInstance();
						data.setTime(rs.getDate("data_dev_requi_int_farmacia"));
						pd.setData_requi_interna(data);
					listaEncomenda.add(pd);
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
			return listaEncomenda;
		}

		@Override
		public List<RequisicaoInterna> buscarDevolucaoProdutoVisual(int codigo) {
			List<RequisicaoInterna> lista = new ArrayList<RequisicaoInterna>();
			String sql = "SELECT * FROM vwdevolverrequisicaointernafarmaciaporlinha where id_produto = ?";
			try {
				con = Conexao.getConexao();
				PreparedStatement preparador = con.prepareStatement(sql);
				preparador.setInt(1, codigo);
				ResultSet rs = preparador.executeQuery();
				while (rs.next()) {
					RequisicaoInterna pd = new RequisicaoInterna();
					pd.setNome_comercial(rs.getString("nome_comercial"));
					pd.setFk_produto(rs.getInt("id_produto"));
					pd.setQtd_requi_interna(rs.getInt("qdt_requi_receb_farmacia_poritem"));
					pd.setNum_lote(rs.getString("lote_requi_receb_farmacia_poritem"));
					Calendar data = Calendar.getInstance();
					data.setTime(rs.getDate("caducidade_requi_receb_farmacia_poritem"));
					pd.setData_requi_interna(data);
					pd.setId_requi_proce_farmacia(rs.getInt("fk_receb_requi_farmacia_poritem"));
					pd.setId_requi_interna(rs.getInt("id_receb_requi_farmacia"));
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

		@Override
		public Produtos buscarProduto(int prodId) {
			Produtos pd = null;
			String sql = "SELECT * FROM vwlistadeprodutoscadastrados where id_produto = ?";
			try {
				con = Conexao.getConexao();
				PreparedStatement preparador = con.prepareStatement(sql);
				preparador.setInt(1, prodId);
				ResultSet rs = preparador.executeQuery();
				if (rs.next()) {
					pd = new Produtos();
					pd.setId_produto(rs.getInt("id_produto"));
					pd.setNome_comercial(rs.getString("nome_comercial"));
					pd.setValor_forma_farmaceutica(rs.getString("forma_farmaceutica"));
					pd.setValor_dosagem(rs.getString("dosagem"));
					pd.setPreco_dcompra(rs.getDouble("preco_dcompra"));
					pd.setPreco_dvenda(rs.getDouble("preco_dvenda"));

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
			return pd;
		}
}
