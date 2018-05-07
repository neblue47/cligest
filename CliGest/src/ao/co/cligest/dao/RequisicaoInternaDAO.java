package ao.co.cligest.dao;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

import ao.co.cligest.entidades.ConfNomeFactura;
import ao.co.cligest.entidades.Exames;
import ao.co.cligest.entidades.Produtos;
import ao.co.cligest.entidades.RequisicaoInterna;

public class RequisicaoInternaDAO {

	/**
	 * @Aridja C. André 27-08-2015 Trabalhando em Requisição Interna
	 * */
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

	public RequisicaoInterna novo() {
		RequisicaoInterna f = new RequisicaoInterna();
		int fun = 0;
		String sql = "SELECT MAX(id_requi_interna)num from tblrequisicaointernafarmacia";
		try {
			con = Conexao.getConexao();
			PreparedStatement preparador = con.prepareStatement(sql);
			ResultSet rs = preparador.executeQuery();
			if (rs.last())
				fun = rs.getInt("num");
			fun++;

			f.setNumero_requi_interna("REF00" + fun);
		} catch (Exception e) {
			// TODO: handle exception
		} finally {
			try {
				con.close();
			} catch (SQLException ef) {
				System.out.println("Erro finalizar: " + ef);
			}
		}
		return f;
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

	public int numeroRecReq() {

		int fun = 0;
		String sql = "SELECT count(id_receb_requi_farmacia) numero FROM tblreceberrequisicaogeral ";
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

	public List<Produtos> buscarRequisicaoInternas() {
		List<Produtos> listaEncomenda = new ArrayList<Produtos>();
		String sql = "SELECT * FROM vwrequisicoesinternasgerais order by id_requi_interna DESC";
		try {
			con = Conexao.getConexao();
			PreparedStatement preparador = con.prepareStatement(sql);
			ResultSet rs = preparador.executeQuery();
			while (rs.next()) {
				Produtos pd = new Produtos();
				pd.setId_numEncomenda(rs.getInt("id_requi_interna"));
				pd.setNumEncomenda(rs.getString("numero_requi_interna"));
				pd.setFornecedor(rs.getString("requerente_interno"));
				pd.setId(rs.getInt("FK_requerente_interno"));
				pd.setNomFuncionario(rs.getString("fk_funcionario"));
				pd.setFK_funcionario(rs.getInt("fk_funcionario"));
				pd.setFK_requerente_interno(rs.getInt("FK_requerente_interno"));
				pd.setQuantidade(rs.getInt("qtd_requi_interna"));
				pd.setTotalEncomenda(rs.getDouble("total_preco_dcompra"));
				Calendar data = Calendar.getInstance();
				data.setTime(rs.getDate("data_requi_interna"));
				pd.setDataCriacao(data);
				// if(rs.getInt("quantidade") == rs.getInt("balanco"))
				// {
				// pd.setBalanco(0);
				// }else{
				// pd.setBalanco(rs.getInt("balanco"));
				// }
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
		String sql = "SELECT * FROM vwrequisicaoporprocessarfarmacia";
		try {
			con = Conexao.getConexao();
			PreparedStatement preparador = con.prepareStatement(sql);
			ResultSet rs = preparador.executeQuery();
			while (rs.next()) {
				Produtos pd = new Produtos();
				pd.setId_numEncomenda(rs.getInt("id_requi_proce_geral"));
				pd.setNumEncomenda(rs.getString("numero_requi_proce_geral"));
				pd.setFornecedor(rs.getString("n_c_funcionario"));
				pd.setRequisicao_original(rs.getString("requisicao_original"));
					System.out.println(rs.getString("requisicao_original"));
				//pd.setTotal(rs.getInt("total_por_eceber"));
				Calendar data = Calendar.getInstance();
				data.setTime(rs.getDate("data_requi_proce_geral"));
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
		String sql = "SELECT * FROM vwrequisicaoporprocessargeralporlinha where id_requi_proce_geral = ? order by nome_comercial";
		try {
			con = Conexao.getConexao();
			PreparedStatement preparador = con.prepareStatement(sql);
			preparador.setInt(1, cod);
			ResultSet rs = preparador.executeQuery();
			while (rs.next()) {
				Produtos pd = new Produtos();
				pd.setFK_num_encomenda(rs.getInt("id_requi_proce_geral"));
				pd.setCodigo_barra(rs.getString("codigo_barra"));
				pd.setId_produto(rs.getInt("id_produto"));
				pd.setNome_comercial(rs.getString("nome_comercial"));
//				pd.setQuantidade(rs.getInt("qtd_requi_proce_geral_porlinha"));
//				pd.setQdt_recebida(rs.getInt("qtd_por_receber"));
//				 pd.setNota(rs.getString("numero_requi_interna"));
				Calendar data = Calendar.getInstance();
				data.setTime(rs.getDate("data_requi_proce_geral"));
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

	public Produtos buscarProduto(String prodId) {
		Produtos pd = null;
		String sql = "SELECT * FROM vwprodutoemestoquearmazem where nome_comercial = ?";
		try {
			con = Conexao.getConexao();
			PreparedStatement preparador = con.prepareStatement(sql);
			preparador.setString(1, prodId);
			ResultSet rs = preparador.executeQuery();
			if (rs.next()) {
				pd = new Produtos();
				pd.setId_produto(rs.getInt("id_produto"));
				pd.setNome_comercial(rs.getString("nome_comercial"));
				pd.setValor_forma_farmaceutica(rs.getString("forma_farmaceutica"));
				pd.setValor_dosagem(rs.getString("dosagem"));
				pd.setPreco_dcompra(rs.getDouble("preco_dcompra"));
				pd.setPreco_dvenda(rs.getDouble("preco_dvenda"));
				pd.setNumero_lote_sere(rs.getString("numero_dolote_sere"));
				pd.setData_de_caducidade(rs.getString("data_decaducidade"));

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

	public Produtos buscarProdutoEncomendado(int cod, int codp) {
		Produtos pd = new Produtos();
		String sql = "SELECT * FROM vwrequisicaoporprocessargeralporlinha where id_requi_proce_geral = ? and id_produto = ?";
		try {
			con = Conexao.getConexao();
			PreparedStatement preparador = con.prepareStatement(sql);
			preparador.setInt(1, cod);
			preparador.setInt(2, codp);
			ResultSet rs = preparador.executeQuery();
			if (rs.next()) {
				pd.setFK_num_encomenda(rs.getInt("id_requi_proce_geral"));
				pd.setCodigo_barra(rs.getString("codigo_barra"));
				pd.setId_produto(rs.getInt("id_produto"));
				pd.setNome_comercial(rs.getString("nome_comercial"));
				pd.setQuantidade(rs.getInt("qtd_requi_proce_geral_porlinha"));
				pd.setQdt_recebida(rs.getInt("qtd_por_receber"));
				Calendar data = Calendar.getInstance();
				data.setTime(rs.getDate("data_requi_proce_geral"));
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

	public int ReceberRequisicaoCadastrar(RequisicaoInterna pd) {
		int ultimoCod = 0;
		String sql = "INSERT INTO tblreceberrequisicaogeral (numero_receb_requi_farmacia,fk_funcionario_receb_requi_farmacia,data_receb_requi_farmacia,fk_local_armaze_requi_receb_farmacia_poritem,fk_ala_requi_receb_farmacia_poritem,fk_montra_requi_receb_farmacia_poritem,fk_prateleira_requi_receb_farmacia_poritem,fk_req_processada) VALUES (?,?,?,?,?,?,?,?)";
		try {
			con = Conexao.getConexao();
			PreparedStatement cp = con.prepareStatement(sql);
			cp.setString(1, pd.getNumero_requi_interna());
			cp.setInt(2, pd.getFk_funcionario());
			cp.setDate(3, new Formatando().data_registo());
			cp.setInt(4, pd.getArmazenamento());
			cp.setInt(5, pd.getBloco());
			cp.setInt(6, pd.getMontra());
			cp.setInt(7, pd.getPratileira());
			cp.setInt(8, pd.getFk_req_processada());
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

	public void RequisicaoReceberPorItens(RequisicaoInterna pd) {
		String sql = "INSERT INTO tblreceberrequisicaogeralporlinha (fk_receb_requi_farmacia_poritem,fk_produto_receber_requi_farmacia_poritem,qdt_requi_receb_farmacia_poritem,caducidade_requi_receb_farmacia_poritem,lote_requi_receb_farmacia_poritem) VALUES (?,?,?,?,?)";
		try {
			con = Conexao.getConexao();
			PreparedStatement cp = con.prepareStatement(sql);
			cp.setInt(1, pd.getFK_requi_interna());
			cp.setInt(2, pd.getFk_produto_requi_interna());
			cp.setInt(3, pd.getQtd_requi_interna());
			cp.setString(4, pd.getData_expiracao());
			cp.setString(5, pd.getNum_lote());
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

	public List<Produtos> buscarProdutoRequisitado(int prodId) {
		List<Produtos> lista = new ArrayList<Produtos>();
		String sql = "SELECT * FROM vwrequisicoesinternasgeraisporitens where id_requi_interna = ? group by nome_comercial";
		try {
			con = Conexao.getConexao();
			PreparedStatement preparador = con.prepareStatement(sql); 
			preparador.setInt(1, prodId);
			ResultSet rs = preparador.executeQuery();
			while (rs.next()) {
				Produtos pd = new Produtos();
				pd.setFK_num_encomenda(rs.getInt("fk_produto_requi_interna"));
				pd.setId_produto(rs.getInt("fk_produto_requi_interna"));
				pd.setNome_comercial(rs.getString("nome_comercial"));
				pd.setNome(rs.getString("requerente_interno"));
				pd.setNumero_ordem(rs.getString("numero_requi_interna"));
				pd.setQdt_recebida(rs.getInt("qtd_requi_interna"));
				pd.setQuantidade(rs.getInt("produto_emstock"));
				pd.setPrecoUnitario(rs.getDouble("preco_dcompra"));
				pd.setTotal(rs.getInt("subtotal_r_geral"));
				pd.setQtd_por_receber(rs.getInt("qtd_por_receber"));
				pd.setNumero_lote_sere(rs.getString("numero_dolote_sere"));
				pd.setData_de_caducidade(rs.getString("data_decaducidade"));
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

	public int requisicaoProcessada(RequisicaoInterna pd) {
		int ultimoCod = 0;
		String sql = "INSERT INTO tblrequisicaoprocessada (fk_funcionario,data_requi_proce_geral,fk_requerente,fk_requisicao_original,numero_requi_proce_geral) VALUES (?,?,?,?,?)";
		try {
			con = Conexao.getConexao();
			PreparedStatement cp = con.prepareStatement(sql);
			cp.setInt(1, pd.getFk_funcionario());
			cp.setDate(2, new Formatando().data_registo());
			cp.setInt(3, pd.getFk_requerente());
			cp.setInt(4, pd.getFk_requisicao_original());
			cp.setString(5, pd.getNumero_requi_proce_farmacia());
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
	
	public int requisicaoProcessadaVerificar(RequisicaoInterna p) {
		int aux = 0;
		String sql = "SELECT * FROM tblrequisicaoprocessada where fk_requisicao_original = ? "; // and fk_requi_proce_geral = ?
		try {
			con = Conexao.getConexao();
			PreparedStatement preparador = con.prepareStatement(sql);
			preparador.setInt(1, p.getFk_requisicao_original());
			ResultSet rs = preparador.executeQuery();
			if (rs.next()) {
				aux = rs.getInt("fk_requisicao_original");						
			}
			preparador.close();
		} catch (SQLException e) {		e.printStackTrace();		} 
		  catch (Exception e) {			e.printStackTrace();		} 
		finally {
			try {
				con.close();
			} catch (SQLException ef) {
				System.out.println("Erro finalizar: " + ef);
			}
		}
		 
		return aux;
	}
	
	public int getIdrequisicaoProcessada(RequisicaoInterna p) {
		int aux = 0;
		String sql = "SELECT * FROM tblrequisicaoprocessada where fk_requisicao_original = ? "; // and id_requi_proce_geral = ?
		try {
			con = Conexao.getConexao();
			PreparedStatement preparador = con.prepareStatement(sql);
			preparador.setInt(1, p.getFk_requisicao_original());
			ResultSet rs = preparador.executeQuery();
			if (rs.next()) {
				aux = rs.getInt("id_requi_proce_geral");						
			}
			preparador.close();
		} catch (SQLException e) {		e.printStackTrace();		} 
		  catch (Exception e) {			e.printStackTrace();		} 
		finally {
			try {
				con.close();
			} catch (SQLException ef) {
				System.out.println("Erro finalizar: " + ef);
			}
		}
		 
		return aux;
	}
	
//	public int requisicaoProcessadaaa(RequisicaoInterna pd) {
//		String sql = "INSERT INTO tblrequisicaoprocessada (fk_funcionario,data_requi_proce_geral,fk_requerente,fk_requisicao_original,numero_requi_proce_geral) VALUES (?,?,?,?,?)";
//		int ultimoCod = xegarRequisicaoExiste(pd);
//		System.out.println("ultimoCod: "+ultimoCod);
//		try {
//			if(ultimoCod == 0){
//			con = Conexao.getConexao();
//			PreparedStatement cp = con.prepareStatement(sql);
//			cp.setInt(1, pd.getFk_funcionario());
//			cp.setDate(2, new Formatando().data_registo());
//			cp.setInt(3, pd.getFk_requerente());
//			cp.setInt(4, pd.getFk_requisicao_original());
//			cp.setString(5, pd.getNumero_requi_proce_farmacia());
//			cp.execute();
//			ResultSet rs = cp.executeQuery("SELECT LAST_INSERT_ID()");
//			if (rs.next()) {
//				ultimoCod = rs.getInt(1);
//			}
//			cp.close();
//			// System.out.println("Cadastro de produtos efectuado Requisição com sucesso!..");
//			}
//		} catch (SQLException e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//		} catch (Exception e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//		} finally {
//			try {
//				con.close();
//			} catch (SQLException ef) {
//				System.out.println("Erro finalizar: " + ef);
//			}
//		}
//		return ultimoCod;
//	}
	
	public void requisicaoProcessadaPorItens(RequisicaoInterna pd) {
		String sql = "INSERT INTO tblrequisicaoprocessadaporlinha (fk_requi_proce_geral,fk_produto,qtd_requi_proce_geral_porlinha,data_caducidade,lote_sere) VALUES (?,?,?,?,?)";
		try {
			con = Conexao.getConexao();
			PreparedStatement cp = con.prepareStatement(sql);
			cp.setInt(1, pd.getFk_ala_requi_receb_farmacia_poritem());
			cp.setInt(2, pd.getFk_produto_requi_interna());
			cp.setInt(3, pd.getQtd_requi_interna());
			cp.setDate  (4, new Date(pd.getData_requi_interna().getTimeInMillis()));
			cp.setString(5, pd.getNum_lote());
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
	
	public int xegarRequisicaoExiste(RequisicaoInterna pd) {
		int aux = 0;
		String sql = "SELECT * FROM tblrequisicaoprocessada where fk_requisicao_original =?";
		try {
			con = Conexao.getConexao();
			PreparedStatement preparador = con.prepareStatement(sql);
			preparador.setInt(1, pd.getFk_requisicao_original());
			
			ResultSet rs = preparador.executeQuery();
			if(rs.next()) {
				aux = rs.getInt("id_requi_proce_geral");	
			}
			preparador.close();
		} catch (SQLException e) {		e.printStackTrace();		} 
		  catch (Exception e) {			e.printStackTrace();		} 
		finally {
			try {
				con.close();
			} catch (SQLException ef) {
				System.out.println("Erro finalizar: " + ef);
			}
		}
		 
		return aux;
	}
	
	public void AtualizarQDtRequisicaoProcessada(RequisicaoInterna p, int  codigo) {
		String sql = "update tblrequisicaoprocessadaporlinha set qtd_requi_proce_geral_porlinha = ?  where id_requi_proce_geral_porlinha =?";
		try {
			con = Conexao.getConexao();
			PreparedStatement preparador = con.prepareStatement(sql);
			preparador.setInt(1, p.getQtd_requi_interna());
			preparador.setInt(2, codigo);
			preparador.execute();
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
	}
	
	public void AtualizarQDtRequisicaoProcessadas(RequisicaoInterna p){
		String sql = "update tblrequisicaointernageralporitem set requisicao_processada = ? where fk_produto_requi_interna =? and FK_requi_interna =?";
		try {
			con = Conexao.getConexao();
			PreparedStatement preparador = con.prepareStatement(sql);
			preparador.setInt(1, p.getQtd_requi_interna());
			preparador.setInt(2, p.getFk_produto_requi_interna());
			preparador.setInt(3, p.getFk_requisicao_original());
			preparador.execute();
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
	}
	
	public int getIdRequisicaoProcessadaLinha(RequisicaoInterna p) {
		int aux = 0;
		String sql = "SELECT * FROM tblrequisicaoprocessadaporlinha where fk_produto =? and data_caducidade =? and lote_sere = ? and fk_requi_proce_geral = ?";
		try {
			con = Conexao.getConexao();
			PreparedStatement preparador = con.prepareStatement(sql);
			preparador.setInt(1, p.getFk_produto_requi_interna());
			preparador.setDate  (2, new Date(p.getData_requi_interna().getTimeInMillis()));
			preparador.setString(3, p.getNum_lote());
			preparador.setInt(4, p.getFk_ala_requi_receb_farmacia_poritem());
			ResultSet rs = preparador.executeQuery();
			if (rs.next()) {
				aux = rs.getInt("id_requi_proce_geral_porlinha");						
			}
			preparador.close();
		} catch (SQLException e) {		e.printStackTrace();		} 
		  catch (Exception e) {			e.printStackTrace();		} 
		finally {
			try {
				con.close();
			} catch (SQLException ef) {
				System.out.println("Erro finalizar: " + ef);
			}
		}
		 
		return aux;
	}
	
//	public int getQDtRequisicaoProcessadaValor(RequisicaoInterna p) {
//		int aux = 0;
//		String sql = "SELECT * FROM tblrequisicaoprocessadaporlinha where fk_produto =? and data_caducidade =? and lote_sere = ? "; // and fk_requi_proce_geral = ?
//		try {
//			con = Conexao.getConexao();
//			PreparedStatement preparador = con.prepareStatement(sql);
//			preparador.setInt(1, p.getFk_produto_requi_interna());
//			preparador.setDate  (2, new Date(p.getData_requi_interna().getTimeInMillis()));
//			preparador.setString(3, p.getNum_lote());
////			preparador.setInt(4, p.getFk_ala_requi_receb_farmacia_poritem());
////			System.out.println("fk_requi_farmacia: "+p.getFk_ala_requi_receb_farmacia_poritem());
//			ResultSet rs = preparador.executeQuery();
//			if (rs.next()) {
//				aux = rs.getInt("fk_requi_proce_geral");						
//			}
//			preparador.close();
//		} catch (SQLException e) {		e.printStackTrace();		} 
//		  catch (Exception e) {			e.printStackTrace();		} 
//		finally {
//			try {
//				con.close();
//			} catch (SQLException ef) {
//				System.out.println("Erro finalizar: " + ef);
//			}
//		}
//		 
//		return aux;
//	}
	
	public int getRecuperarQDtRequisicaoProcessada(RequisicaoInterna p) {
		int aux = 0;
		String sql = "SELECT * FROM tblrequisicaoprocessadaporlinha where fk_produto =? and data_caducidade =? and lote_sere = ? and fk_requi_proce_geral =?"; 
		try {
			con = Conexao.getConexao();
			PreparedStatement preparador = con.prepareStatement(sql);
			preparador.setInt(1, p.getFk_produto_requi_interna());
			preparador.setDate  (2, new Date(p.getData_requi_interna().getTimeInMillis()));
			preparador.setString(3, p.getNum_lote());
			preparador.setInt(4, p.getFk_ala_requi_receb_farmacia_poritem());
			ResultSet rs = preparador.executeQuery();
			if (rs.next()) {
				aux = rs.getInt("qtd_requi_proce_geral_porlinha");						
			}
			preparador.close();
		} catch (SQLException e) {		e.printStackTrace();		} 
		  catch (Exception e) {			e.printStackTrace();		} 
		finally {
			try {
				con.close();
			} catch (SQLException ef) {
				System.out.println("Erro finalizar: " + ef);
			}
		}
		 
		return aux;
	}

	public int numeroProcessada() {

		int fun = 0;
		String sql = "SELECT count(id_requi_proce_geral) numero FROM tblrequisicaoprocessada";
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
	
	public int numeroProcessadas(int numero) {

		int fun = 0;
		String sql = "SELECT count(id_requi_proce_geral) numero FROM tblrequisicaoprocessada where id_requi_proce_geral =?";
		try {
			con = Conexao.getConexao();
			PreparedStatement preparador = con.prepareStatement(sql);
			preparador.setInt(1, numero);
			ResultSet rs = preparador.executeQuery();

			if (rs.next())
				fun = rs.getInt("id_requi_proce_geral");
		} catch (Exception e) {
		} finally {
			try {
				con.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		return fun+1;
	}


	public List<RequisicaoInterna> buscarRequisitadoDistribuida() {
		List<RequisicaoInterna> lista = new ArrayList<RequisicaoInterna>();
		String sql = "SELECT * FROM vwrequisicaoprocessada";
		try {
			con = Conexao.getConexao();
			PreparedStatement preparador = con.prepareStatement(sql);
			ResultSet rs = preparador.executeQuery();
			while (rs.next()) {
				RequisicaoInterna pd = new RequisicaoInterna();
				pd.setId_receb_requi_farmacia(rs
						.getInt("id_requi_proce_geral"));
				pd.setFK_requi_interna(rs.getInt("FK_requisicao_interna"));
				pd.setFk_funcionario(rs.getInt("fK_funcionario"));
				pd.setNumero_receb_requi_farmacia(rs
						.getString("numero_requi_proce_geral"));
				pd.setNome_funcionario(rs.getString("n_c_funcionario"));
				pd.setNomeRequerente_interno(rs.getString("requerente_interno"));
				pd.setQtd_requi_interna(rs.getInt("qtd_req_proce"));
				Calendar data = Calendar.getInstance();
				data.setTime(rs.getDate("data_requi_proce_geral"));
				pd.setData_requi_interna(data);
				pd.setRequisicao_original(rs.getString("requisicao_original"));
				pd.setTotal(rs.getDouble("total_req_proce"));
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

	public List<RequisicaoInterna> buscarRequisitadoDistribuida(String nome) {
		List<RequisicaoInterna> lista = new ArrayList<RequisicaoInterna>();
		String sql = "SELECT * FROM vwrequisicaoprocessadafarmacia where n_c_funcionario like ? or numero_requi_proce_geral like ?";
		try {
			con = Conexao.getConexao();
			PreparedStatement preparador = con.prepareStatement(sql);
			preparador.setString(1, "%" + nome + "%");
			preparador.setString(2, nome);
			ResultSet rs = preparador.executeQuery();
			while (rs.next()) {
				RequisicaoInterna pd = new RequisicaoInterna();
				pd.setId_receb_requi_farmacia(rs
						.getInt("id_requi_proce_geral"));
				pd.setFk_funcionario(rs.getInt("fK_funcionario"));
				pd.setNumero_receb_requi_farmacia(rs
						.getString("numero_requi_proce_geral"));
				pd.setNome_funcionario(rs.getString("n_c_funcionario"));
				pd.setQtd_requi_interna(rs.getInt("qtd_req_proce"));
				Calendar data = Calendar.getInstance();
				data.setTime(rs.getDate("data_requi_proce_geral"));
				pd.setData_requi_interna(data);
				pd.setRequisicao_original(rs.getString("requisicao_original"));
				pd.setTotal(rs.getDouble("total_req_proce"));
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

	public List<RequisicaoInterna> buscarProdutoRequisitadoIntModal(String prodId) {
		List<RequisicaoInterna> lista = new ArrayList<RequisicaoInterna>();
		String sql = "SELECT * FROM vwrequisicaointernadeclinadageralmodal where numero_requi_decli_farmacia = ?";
		try {
			con = Conexao.getConexao();
			PreparedStatement preparador = con.prepareStatement(sql);
			preparador.setString(1, prodId);
			ResultSet rs = preparador.executeQuery();
			while (rs.next()) {
				RequisicaoInterna pd = new RequisicaoInterna();
				pd.setNumero_requi_interna(rs.getString("numero_requi_decli_farmacia"));
				Calendar data = Calendar.getInstance();
				data.setTime(rs.getDate("data_requi_decli_farmacia"));
				pd.setData_requi_interna(data);
				pd.setFk_produto(rs.getInt("fk_produto"));
				pd.setNome_comercial(rs.getString("nome_comercial"));
				pd.setQtd_requi_interna(rs.getInt("qtd_requi_decli_farm_linhas"));
				pd.setPreco_dcompra(rs.getDouble("preco_dcompra"));
				pd.setTotal(rs.getInt("sub_total"));
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
	public List<Produtos> buscarProdutoRequisitadoModal(String prodId) {
		List<Produtos> lista = new ArrayList<Produtos>();
		String sql = "SELECT * FROM vwrequisicaoprocessadafarmaciaporlinhamodal where fk_requi_proce_geral = ?";
		try {
			con = Conexao.getConexao();
			PreparedStatement preparador = con.prepareStatement(sql);
			preparador.setString(1, prodId);
			ResultSet rs = preparador.executeQuery();
			while (rs.next()) {
				Produtos pd = new Produtos();
				pd.setFK_num_encomenda(rs.getInt("fk_requi_proce_geral"));
				// pd.setId_produto(rs.getInt("fk_produto_requi_interna"));
				pd.setNum_encomenda(rs.getString("numero_requi_proce_geral"));
				pd.setRequerente_interno(rs.getString("requerente_interno"));
				pd.setNome_comercial(rs.getString("nome_comercial"));
				pd.setQuantidade(rs.getInt("qtd_requi_proce_farma_porlinha"));
				pd.setPrecoUnitario(rs.getDouble("preco_dcompra"));
				pd.setTotal(rs.getInt("subtotal"));
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

	public List<Produtos> buscarProdutosEncomendadosModel(String cod) {
		List<Produtos> lista = new ArrayList<Produtos>();
		String sql = "SELECT * FROM vwrequisicaointernaporreceberfarmaciaporlinha where id_requi_interna = ?";
		try {
			con = Conexao.getConexao();
			PreparedStatement preparador = con.prepareStatement(sql);
			preparador.setString(1, cod);
			ResultSet rs = preparador.executeQuery();
			while (rs.next()) {
				Produtos pd = new Produtos();
				pd.setFK_num_encomenda(rs.getInt("fk_produto_requi_interna"));
				pd.setNumEncomenda(rs.getString("numero_requi_interna"));
				pd.setCodigo_barra(rs.getString("codigo_barra"));
				pd.setId_produto(rs.getInt("fk_produto_requi_interna"));
				pd.setNome_comercial(rs.getString("nome_comercial"));
				pd.setQuantidade(rs.getInt("qtd_original_r_i"));
				pd.setPreco(rs.getDouble("preco_dcompra"));
				pd.setTotalEncomenda(rs.getDouble("subtotal"));
				pd.setQdt_recebida(rs.getInt("qtd_por_receber_r_i"));
				pd.setNota(rs.getString("numero_requi_interna"));
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

	public List<Produtos> buscarRequisicaoRecebida() {
		List<Produtos> listaEncomenda = new ArrayList<Produtos>();
		String sql = "SELECT * FROM vwrequisicaorecebidafarmacia order by id_receb_requi_farmacia DESC";
		try {
			con = Conexao.getConexao();
			PreparedStatement preparador = con.prepareStatement(sql);
			ResultSet rs = preparador.executeQuery();
			while (rs.next()) {
				Produtos pd = new Produtos();
				pd.setId_numEncomenda(rs.getInt("id_receb_requi_farmacia"));
				pd.setNumEncomenda(rs.getString("numero_receb_requi_farmacia"));
				pd.setNome(rs.getString("n_c_funcionario"));
				pd.setTotalEncomenda(rs.getDouble("total_requisitado"));
				pd.setTotal_s(rs.getString("total_recebido"));
				Calendar data = Calendar.getInstance();
				data.setTime(rs.getDate("data_receb_requi_farmacia"));
				pd.setDataCriacao(data);
				// if(rs.getInt("quantidade") == rs.getInt("balanco"))
				// {
				// pd.setBalanco(0);
				// }else{
				// pd.setBalanco(rs.getInt("balanco"));
				// }
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

	public List<Produtos> buscarRequisicaoRecebida(String nome) {
		List<Produtos> listaEncomenda = new ArrayList<Produtos>();
		String sql = "SELECT * FROM vwrequisicaorecebidafarmacia where numero_receb_requi_farmacia = ? ";
		try {
			con = Conexao.getConexao();
			PreparedStatement preparador = con.prepareStatement(sql);
			preparador.setString(1, nome);
			ResultSet rs = preparador.executeQuery();
			while (rs.next()) {
				Produtos pd = new Produtos();
				pd.setId_numEncomenda(rs.getInt("id_receb_requi_farmacia"));
				pd.setNumEncomenda(rs.getString("numero_receb_requi_farmacia"));
				pd.setNome(rs.getString("n_c_funcionario"));
				pd.setTotalEncomenda(rs.getDouble("total_requisitado"));
				pd.setTotal_s(rs.getString("total_recebido"));
				Calendar data = Calendar.getInstance();
				data.setTime(rs.getDate("data_receb_requi_farmacia"));
				pd.setDataCriacao(data);
				// if(rs.getInt("quantidade") == rs.getInt("balanco"))
				// {
				// pd.setBalanco(0);
				// }else{
				// pd.setBalanco(rs.getInt("balanco"));
				// }
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

	public List<Produtos> buscarRequisicaoRecebidaDetalhes(int cod) {
		List<Produtos> lista = new ArrayList<Produtos>();
		String sql = "SELECT * FROM vwrequisicaorecebidafarmaciaporlinha where fk_receb_requi_farmacia_poritem = ? order by nome_comercial";
		try {
			con = Conexao.getConexao();
			PreparedStatement preparador = con.prepareStatement(sql);
			preparador.setInt(1, cod);
			ResultSet rs = preparador.executeQuery();
			while (rs.next()) {
				Produtos pd = new Produtos();
				pd.setFK_num_encomenda(rs
						.getInt("fk_receb_requi_farmacia_poritem"));
				pd.setId_produto(rs
						.getInt("fk_produto_receber_requi_farmacia_poritem"));
				pd.setCodigo_barra(rs.getString("codigo_barra"));
				pd.setNome_comercial(rs.getString("nome_comercial"));
				pd.setQuantidade(rs.getInt("qdt_requi_receb_farmacia_poritem"));
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

	// verificar estoque em farmácia
	public int getQDtProdutoEstoque(RequisicaoInterna p) {
		int aux = 0;
		String sql = "SELECT * FROM tblprodutoemestoquearmazem where FK_id_produto = ? "; // and
																							// numero_dolote_sere
																							// =
																							// ?
		try {
			con = Conexao.getConexao();
			PreparedStatement preparador = con.prepareStatement(sql);
			preparador.setInt(1, p.getFk_produto_requi_interna());
			// preparador.setString(2, p.getNum_lote());
			// System.out.println("Dentro do metodo numero de lote: "+p.getNum_lote());
			ResultSet rs = preparador.executeQuery();
			if (rs.next()) {
				aux = rs.getInt("quantidade_produto");
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
		System.out.println(aux);
		return aux;
	}

	// 1-INSERIR PRODUTOS EM ESTOQUE
	public void AlocarProdutosEmEstoque(Produtos pd) {
		String sql = "INSERT INTO tblprodutoemestoquearmazem (FK_id_produto, FK_local, FK_alabloco, FK_montra, FK_prartileira, quantidade_produto, FK_funcionario, data_dentrada_doestoque, hora_dentrada_doestoque, numero_dolote_sere, data_decaducidade) VALUES (?,?,?,?,?,?,?,?,?,?,?)";
		try {
			con = Conexao.getConexao();
			PreparedStatement cp = con.prepareStatement(sql);
			cp.setInt(1, pd.getId_produto());
			cp.setInt(2, pd.getId_local_dearmazenamento());
			cp.setInt(3, pd.getId_alabloco());
			cp.setInt(4, pd.getId_montra_deproduto());
			cp.setInt(5, pd.getId_prartileira());
			cp.setInt(6, pd.getQdt_recebida());
			cp.setInt(7, pd.getFK_funcionario());
			cp.setDate(8, new Formatando().data_registo());
			cp.setString(9, new Formatando().horaAtual());
			cp.setString(10, pd.getNumero_lote_sere());
			cp.setString(11, pd.getData_de_caducidade());
			cp.execute();
			sql = "update tblencomendarecebida set alocado =? where FK_encomenda = ? and FK_produto = ?  ";
			cp = con.prepareStatement(sql);
			cp.setInt(1, 1);
			cp.setInt(2, pd.getId_numEncomenda());
			cp.setInt(3, pd.getId_produto());
			cp.execute();
			cp.close();
			System.out.println(pd.getData_expiracao());
			// System.out.println("Cadastro de produtos Em Estoque efectuado com sucesso!..");
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
	}

	public void AtualizarRequisicaoInterna(RequisicaoInterna p) {
		String sql = "update tblrequisicaoprocessadaporlinha set qtd_requi_proce_farma_porlinha = ?  where fk_produto = ? and lote_sere =?"; // and numero_dolote_sere = ?
		try {
			con = Conexao.getConexao();
			PreparedStatement preparador = con.prepareStatement(sql);
			preparador.setInt(1, p.getQtd_requi_interna());
			preparador.setInt(2, p.getFk_produto_requi_interna());
			 preparador.setString(3, p.getNum_lote());
			preparador.execute();

			// sql =
			// "update tblencomendarecebida set alocado =? where FK_encomenda = ? and FK_produto = ?  ";
			// preparador = con.prepareStatement(sql);
			// preparador.setInt(1, 1);
			// preparador.setInt(2, p.getid);
			// preparador.setInt(3, p.getId_produto());
			// preparador.execute();
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
	}
	
	public void AtualizarQDtProdutoAlocado(RequisicaoInterna p) {
		String sql = "update tblprodutoemestoquearmazem set quantidade_produto = ?  where FK_id_produto = ? and numero_dolote_sere =?"; // and numero_dolote_sere = ?
		try {
			con = Conexao.getConexao();
			PreparedStatement preparador = con.prepareStatement(sql);
			preparador.setInt(1, p.getQtd_requi_interna());
			preparador.setInt(2, p.getFk_produto_requi_interna());
			 preparador.setString(3, p.getNum_lote());
			preparador.execute();

			// sql =
			// "update tblencomendarecebida set alocado =? where FK_encomenda = ? and FK_produto = ?  ";
			// preparador = con.prepareStatement(sql);
			// preparador.setInt(1, 1);
			// preparador.setInt(2, p.getid);
			// preparador.setInt(3, p.getId_produto());
			// preparador.execute();
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
	}
	
	

	// verificar estoque em farmácia
	public int getProdutoRequisitado(RequisicaoInterna p) {
		int aux = 0;
		String sql = "SELECT * FROM tblreceberrequisicaogeralporlinha where fk_produto_receber_requi_farmacia_poritem = ? and fk_receb_requi_farmacia_poritem = ? "; // and
																																										// numero_dolote_sere
																																										// =
																																										// ?
		try {
			con = Conexao.getConexao();
			PreparedStatement preparador = con.prepareStatement(sql);
			preparador.setInt(1, p.getFk_produto_requi_interna());
			System.out.println("Dentro do metodo numero produto: "
					+ p.getFk_produto_requi_interna());
			preparador.setInt(2, p.getFK_requi_interna());
			System.out.println("Dentro do metodo numero requisicao: "
					+ p.getFK_requi_interna());
			ResultSet rs = preparador.executeQuery();
			if (rs.next()) {
				aux = rs.getInt("fk_receb_requi_farmacia_poritem");
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
		System.out.println(aux);
		return aux;
	}

	public int getQtdProdutoRequisitado(RequisicaoInterna p) {
		int aux = 0;
		String sql = "SELECT * FROM tblreceberrequisicaogeralporlinha where fk_produto_receber_requi_farmacia_poritem = ? and fk_receb_requi_farmacia_poritem = ? "; // and
																																										// numero_dolote_sere
																																										// =
																																										// ?
		try {
			con = Conexao.getConexao();
			PreparedStatement preparador = con.prepareStatement(sql);
			preparador.setInt(1, p.getFk_produto_requi_interna());
			System.out.println("Dentro do metodo numero produto: "
					+ p.getFk_produto_requi_interna());
			preparador.setInt(2, p.getFK_requi_interna());
			System.out.println("Dentro do metodo numero requisicao: "
					+ p.getFK_requi_interna());
			ResultSet rs = preparador.executeQuery();
			if (rs.next()) {
				aux = rs.getInt("qtd_requi_proce_farma_porlinha");
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
		System.out.println(aux);
		return aux;
	}

	public void AtualizarQDtRequisicao(RequisicaoInterna p) {
		String sql = "update tblreceberrequisicaofarmaciaporitem set qtd_requi_receb_farmacia_poritem = ?  where fk_produto_receber_farmacia_poritem = ? "; // and
																																							// numero_dolote_sere
																																							// =
																																							// ?
		try {
			con = Conexao.getConexao();
			PreparedStatement preparador = con.prepareStatement(sql);
			preparador.setInt(1, p.getQtd_requi_interna());
			preparador.setInt(2, p.getFk_produto_requi_interna());
			preparador.execute();
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

	// Devolução farmacia

	// Devolução farmacia
	public List<RequisicaoInterna> buscarDevolucaoProduto() {
		List<RequisicaoInterna> lista = new ArrayList<RequisicaoInterna>();
		String sql = "SELECT * FROM vwdevolverrequisicaointernafarmacia";
		try {
			con = Conexao.getConexao();
			PreparedStatement preparador = con.prepareStatement(sql);
			ResultSet rs = preparador.executeQuery();
			while (rs.next()) {
				RequisicaoInterna pd = new RequisicaoInterna();
				pd.setNome_funcionario(rs.getString("n_c_funcionario"));
				pd.setNumero_receb_requi_farmacia(rs.getString("numero_receb_requi_geral"));
				pd.setId_requi_proce_farmacia(rs.getInt("id_receb_requi_farmacia"));
				pd.setQtd_requi_interna(rs.getInt("qtd_adevolver"));
				Calendar data = Calendar.getInstance();
				data.setTime(rs.getDate("data_receb_requi_geral"));
				pd.setData_requi_interna(data);
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

	public int cadastarRequisicaoDevolvida(RequisicaoInterna pd) {
		int ultimoCod = 0;
		String sql = "INSERT INTO tbldevolverrequisicaointernafarmacia (numero_dev_requi_int_farmacia,fk_funcionario,data_dev_requi_int_farmacia,razao_dev_requi_int_farmacia,fk_requisicao_recebida) VALUES (?,?,?,?,?)";
		try {
			con = Conexao.getConexao();
			PreparedStatement cp = con.prepareStatement(sql);
			cp.setString(1, pd.getNumero_dev_requi_int_farmacia());
			cp.setInt(2, pd.getFk_funcionario());
			cp.setDate(3, new Formatando().data_registo());
			cp.setString(4, pd.getRazao_dev_requi_int_farmacia());
			cp.setInt(5, pd.getFk_requisicao_recebida());
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

	public void cadastrarRequisicaoDevolvidaPorItens(RequisicaoInterna pd) {
		String sql = "INSERT INTO tbldevolverrequisicaointernafarmaciacomlinhas (FK_dev_requi_int_farmacia,fk_produto,qtd_dev_requi_int_farmacia) VALUES (?,?,?)";
		try {
			con = Conexao.getConexao();
			PreparedStatement cp = con.prepareStatement(sql);
			cp.setInt(1, pd.getFK_dev_requi_int_farmacia());
			cp.setInt(2, pd.getFk_produto_requi_interna());
			cp.setInt(3, pd.getQtd_dev_requi_int_farmacia());
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

	public int numeroDevolucao() {

		int fun = 0;
		String sql = "SELECT count(id_dev_requi_int_farm_comlinhas) numero FROM tbldevolverrequisicaointernafarmacia";
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
	
	public int numeroAjuste() {

		int fun = 0;
		String sql = "SELECT count(id_histo_ajust_farmacia) numero FROM tblhistoricodeajustesfarmacia";
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
	
	public void AtualizarProdutoEstoque(Produtos p) {
		String sql = "SELECT * FROM vwprodutoemestoque where fk_id_produto = ? and numero_dolote_sere like ?";
		Produtos pd = new Produtos();
		try {
			con = Conexao.getConexao();
			PreparedStatement preparador = con.prepareStatement(sql);
			preparador.setInt(1, p.getId_produto());
			preparador.setString(2, p.getNumero_lote_sere());
			ResultSet rs = preparador.executeQuery();
			if (rs.next()) {
				pd.setQuantidade(rs.getInt("quantidade_produto"));
			}
			if(p.getQuantidade() != pd.getQuantidade()){
				sql = "update tblprodutoemestoque set quantidade_produto = ? where fk_id_produto = ? and numero_dolote_sere like ?";
				preparador = con.prepareStatement(sql);
				preparador.setInt(1, p.getQuantidade());
				preparador.setInt(2, p.getId_produto());
				preparador.setString(3, p.getNumero_lote_sere());
				preparador.execute();
//				System.out.println("Atualizado a Quantidade em Stock com sucesso!..");
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
		 
	}

	public void AlocarProdutosEmEstoques(RequisicaoInterna pd)
	{
		String sql = "INSERT INTO tblprodutoemestoque (FK_id_produto, FK_local, FK_alabloco, FK_montra, FK_prartileira, quantidade_produto, FK_funcionario, data_dentrada_doestoque, hora_dentrada_doestoque, numero_dolote_sere, data_decaducidade) VALUES (?,?,?,?,?,?,?,?,?,?,?)";
		try {
			con = Conexao.getConexao();
			PreparedStatement cp = con.prepareStatement(sql);
			cp.setInt(1, pd.getFk_produto_requi_interna());
			cp.setInt(2, pd.getArmazenamento());
			cp.setInt(3, pd.getBloco());
			cp.setInt(4, pd.getMontra());
			cp.setInt(5, pd.getPratileira());
			cp.setInt(6, pd.getQtd_requi_interna());
			cp.setInt(7, pd.getFk_funcionario());
			cp.setDate(8, new Formatando().data_registo());
			cp.setString(9, new Formatando().horaAtual());
			cp.setString(10, pd.getNum_lote());
			cp.setString(11, pd.getData_expiracao());
			cp.execute();
//			sql = "update tblencomendarecebida set alocado =? where FK_encomenda = ? and FK_produto = ?  ";
//			cp = con.prepareStatement(sql);
//			cp.setInt(1, 1);
//			cp.setInt(2, pd.getId_numEncomenda());
//			cp.setInt(3, pd.getId_produto());
//			cp.execute();
			cp.close();
			System.out.println(pd.getData_expiracao());
//			System.out.println("Cadastro de produtos Em Estoque efectuado com sucesso!.."); 
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
	}
	
	
	public void AlocarProdutosEmEstoquesUti(RequisicaoInterna pd)
	{
		String sql = "INSERT INTO tblprodutoemestoque_uti (FK_id_produto,FK_local,FK_alabloco,FK_montra,FK_prartileira,quantidade_produto_uti,FK_funcionario,data_dentrada_doestoque_uti,hora_dentrada_doestoque_uti,numero_dolote_sere_uti,data_decaducidade_uti) VALUES (?,?,?,?,?,?,?,?,?,?,?)";
		try {
			con = Conexao.getConexao();
			PreparedStatement cp = con.prepareStatement(sql);
			cp.setInt(1, pd.getFk_produto_requi_interna());
			cp.setInt(2, pd.getArmazenamento());
			cp.setInt(3, pd.getBloco());
			cp.setInt(4, pd.getMontra());
			cp.setInt(5, pd.getPratileira());
			cp.setInt(6, pd.getQtd_requi_interna());
			cp.setInt(7, pd.getFk_funcionario());
			cp.setDate(8, new Formatando().data_registo());
			cp.setString(9, new Formatando().horaAtual());
			cp.setString(10, pd.getNum_lote());
			cp.setString(11, pd.getData_expiracao());
			cp.execute();
//			sql = "update tblencomendarecebida set alocado =? where FK_encomenda = ? and FK_produto = ?  ";
//			cp = con.prepareStatement(sql);
//			cp.setInt(1, 1);
//			cp.setInt(2, pd.getId_numEncomenda());
//			cp.setInt(3, pd.getId_produto());
//			cp.execute();
			cp.close();
			System.out.println(pd.getData_expiracao());
//			System.out.println("Cadastro de produtos Em Estoque efectuado com sucesso!.."); 
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
	}
	
	public void AtualizarQDtProdutoAlocados(RequisicaoInterna p) {
		String sql = "update tblprodutoemestoque set quantidade_produto = ?  where FK_id_produto = ? and numero_dolote_sere = ?";
		try {
			con = Conexao.getConexao();
			PreparedStatement preparador = con.prepareStatement(sql);
			preparador.setInt(1, p.getQtd_requi_interna());
			preparador.setInt(2, p.getFk_produto_requi_interna());
			preparador.setString(3, p.getNum_lote());
			preparador.execute();
			
//			sql = "update tblencomendarecebida set alocado =? where FK_encomenda = ? and FK_produto = ?  ";
//			preparador = con.prepareStatement(sql);
//			preparador.setInt(1, 1);
//			preparador.setInt(2, p.getId_numEncomenda());
//			preparador.setInt(3, p.getId_produto());
//			preparador.execute();
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
		 
	}
	
	public void AtualizarProdutoEstoques(RequisicaoInterna p) {
		String sql = "SELECT * FROM vwprodutoemestoque where fk_id_produto = ? and numero_dolote_sere like ?";
		Produtos pd = new Produtos();
		try {
			con = Conexao.getConexao();
			PreparedStatement preparador = con.prepareStatement(sql);
			preparador.setInt(1, p.getFk_produto());
			preparador.setString(2, p.getNum_lote());
			ResultSet rs = preparador.executeQuery();
			if (rs.next()) {
				pd.setQuantidade(rs.getInt("quantidade_produto"));
			}
			if(p.getQtd_requi_interna() != pd.getQuantidade()){
				sql = "update tblprodutoemestoque set quantidade_produto = ? where fk_id_produto = ? and numero_dolote_sere like ?";
				preparador = con.prepareStatement(sql);
				preparador.setInt(1, p.getQtd_requi_interna());
				preparador.setInt(2, p.getFk_produto());
				preparador.setString(3, p.getNum_lote());
				preparador.execute();
//				System.out.println("Atualizado a Quantidade em Stock com sucesso!..");
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
		 
	}
	
//	public void AtualizarProdutoEstoque(Produtos p) {
//		String sql = "SELECT * FROM vwprodutoemestoque where fk_id_produto = ? and numero_dolote_sere like ?";
//		Produtos pd = new Produtos();
//		try {
//			con = Conexao.getConexao();
//			PreparedStatement preparador = con.prepareStatement(sql);
//			preparador.setInt(1, p.getId_produto());
//			preparador.setString(2, p.getNumero_lote_sere());
//			ResultSet rs = preparador.executeQuery();
//			if (rs.next()) {
//				pd.setQuantidade(rs.getInt("quantidade_produto"));
//			}
//			if(p.getQuantidade() != pd.getQuantidade()){
//				sql = "update tblprodutoemestoque set quantidade_produto = ? where fk_id_produto = ? and numero_dolote_sere like ?";
//				preparador = con.prepareStatement(sql);
//				preparador.setInt(1, p.getQuantidade());
//				preparador.setInt(2, p.getId_produto());
//				preparador.setString(3, p.getNumero_lote_sere());
//				preparador.execute();
////				System.out.println("Atualizado a Quantidade em Stock com sucesso!..");
//			}
//			preparador.close();
//		} catch (SQLException e) {
//
//			e.printStackTrace();
//		} catch (Exception e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//		} finally {
//			try {
//				con.close();
//			} catch (SQLException ef) {
//				System.out.println("Erro finalizar: " + ef);
//			}
//		}
//		 
//	}
//	Visualizar as requisições feitas
	public List<RequisicaoInterna> buscarRequisicaoFeitas() {
		List<RequisicaoInterna> listaEncomenda = new ArrayList<RequisicaoInterna>();
		String sql = "SELECT * FROM vwrequisicoesinternasgeraishistorico where id_requerente_interno = 1";
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
	
//	Histórico Devolução cussunga
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
	
//	Histórico de ajuste
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
	
	public RequisicaoInterna buscarVerificarRequisicao(RequisicaoInterna rq) {
		RequisicaoInterna pd = null;
		String sql = "SELECT * FROM tblrequisicaoprocessadaporlinha where fk_produto = ? AND fk_requi_proce_geral =? AND data_caducidade =? AND lote_sere=?";
		try {
			con = Conexao.getConexao();
			PreparedStatement preparador = con.prepareStatement(sql);
			preparador.setInt(1, rq.getFk_produto_requi_interna());
			preparador.setInt(2, rq.getFk_requerente());
			preparador.setString(3, rq.getData_expiracao());
			preparador.setString(4, rq.getNum_lote());
			
			System.out.println("produto: "+rq.getFk_produto_requi_interna());
			System.out.println("fk_requisicao: "+rq.getFk_requerente());
			System.out.println("Data expiracao: "+rq.getData_expiracao());
			System.out.println("Numero de lote: "+rq.getNum_lote());
			
			ResultSet rs = preparador.executeQuery();
			if (rs.next()) {
				pd = new RequisicaoInterna();
				pd.setFk_produto(rs.getInt("fk_produto"));
				pd.setFk_requerente(rs.getInt("fk_requi_proce_geral"));
				pd.setData_expiracao(rs.getString("data_caducidade"));
				pd.setNum_lote(rs.getString("lote_sere"));

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
	
	
	public void delete_declinada(int id) {
		String sql = "DELETE FROM tblrequisicaodeclinadageral WHERE id_requi_decli_farmacia = ?";
		try {
			con = Conexao.getConexao();
			PreparedStatement preparador = con.prepareStatement(sql);
			preparador.setInt(1, id);
			preparador.execute();
			preparador.close();
			System.out.println("Exclusao com sucesso...tblrequisicaodeclinadageral");
		} catch (SQLException e) {

			e.printStackTrace();
		} catch (Exception e) {
			// TODO: handle exception
		} finally {
			try {
				con.close();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}
	
	public void delete_declinadaPorlinha(int id) {
		String sql = "DELETE FROM tblrequisicaodeclinadasgeralcomlinhas  WHERE fk_requi_declinada = ?";
		try {
			con = Conexao.getConexao();
			PreparedStatement preparador = con.prepareStatement(sql);
			preparador.setInt(1, id);
			preparador.execute();
			preparador.close();
			System.out.println("Exclusao com sucesso...tblrequisicaodeclinadasgeralcomlinhas");
		} catch (SQLException e) {

			e.printStackTrace();
		} catch (Exception e) {
			// TODO: handle exception
		} finally {
			try {
				con.close();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}
	
	public void AtualizarProdutoEstoque(RequisicaoInterna p) {
		String sql = "SELECT * FROM vwprodutoemestoque where fk_id_produto = ? and numero_dolote_sere like ?";
		Produtos pd = new Produtos();
		try {
			con = Conexao.getConexao();
			PreparedStatement preparador = con.prepareStatement(sql);
			preparador.setInt(1, p.getFk_produto_requi_interna());
			preparador.setString(2, p.getNum_lote());
			ResultSet rs = preparador.executeQuery();
			if (rs.next()) {
				pd.setQuantidade(rs.getInt("quantidade_produto"));
			}
			if(p.getQtd_requi_interna() != pd.getQuantidade()){
				sql = "update tblprodutoemestoque set quantidade_produto = ? where fk_id_produto = ? and numero_dolote_sere like ?";
				preparador = con.prepareStatement(sql);
				preparador.setInt(1, p.getQtd_requi_interna());
				preparador.setInt(2, p.getFk_produto());
				preparador.setString(3, p.getNum_lote());
				preparador.execute();
//				System.out.println("Atualizado a Quantidade em Stock com sucesso!..");
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
		 
	}
	
	public int numeroDeclinada() {

		int fun = 0;
		String sql = "SELECT count(id_requi_decli_farmacia) numero FROM tblrequisicaodeclinadageral";
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
	
	public int cadastRequisicaoDeclinacao(RequisicaoInterna pd) {
		int ultimoCod = 0;
		String sql = "INSERT INTO tblrequisicaodeclinadageral (data_requi_decli_farmacia,numero_requi_decli_farmacia,fk_funcionario,fk_requisicao_original,fk_requerente_interno) VALUES (?,?,?,?,?)";
		try {
			con = Conexao.getConexao();
			PreparedStatement cp = con.prepareStatement(sql);
			cp.setDate(1, new Formatando().data_registo());
			cp.setString(2, pd.getNumero_requi_interna());
			cp.setInt(3, pd.getFk_funcionario());
			cp.setInt(4, pd.getFk_requisicao_original());
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
	
	public void cadastRequicaoDeclinadaPorItens(RequisicaoInterna pd) {
		String sql = "INSERT INTO tblrequisicaodeclinadasgeralcomlinhas (fk_requi_declinada,qtd_requi_decli_farm_linhas,preco_requi_decli_farm_linhas,fk_produto) VALUES (?,?,?,?)";
		try {
			con = Conexao.getConexao();
			PreparedStatement cp = con.prepareStatement(sql);
			cp.setInt(1, pd.getFK_requi_interna());
			cp.setInt(2, pd.getQtd_requi_interna());
			cp.setDouble(3, pd.getPreco_dcompra());
			cp.setInt(4, pd.getFk_produto_requi_interna());
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
	
//	Preste a caducar no armazem

	
	public List<RequisicaoInterna> buscarHistAjustModals(String prodId) {
		List<RequisicaoInterna> lista = new ArrayList<RequisicaoInterna>();
		String sql = "SELECT * FROM vwhistoricodeajustesarmazemmodal where id_num_difinventario = ?";
		try {
			con = Conexao.getConexao();
			PreparedStatement preparador = con.prepareStatement(sql);
			preparador.setString(1, prodId);
			ResultSet rs = preparador.executeQuery();
			while (rs.next()) {
				RequisicaoInterna pd = new RequisicaoInterna();
				pd.setCodigo_barra(rs.getString("codigo_barra"));
				pd.setNome_comercial(rs.getString("nome_comercial"));
				pd.setPreco_dcompra(rs.getDouble("preco_dcompra"));
				pd.setPreco_negativo(rs.getDouble("preco_negativo"));
				pd.setPreco_positivo(rs.getDouble("preco_positivo"));
				pd.setDiferenca_negativa(rs.getInt("diferenca_negativa"));
				pd.setDiferenca_positiva(rs.getInt("diferenca_positiva"));
				pd.setNumero_dif_inventario(rs.getString("numero_dif_inventario"));
				pd.setId_num_difinventario(rs.getInt("id_num_difinventario"));
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
	
	public List<RequisicaoInterna> buscarHistoricoDeAjusteArmazem() {
		List<RequisicaoInterna> listaEncomenda = new ArrayList<RequisicaoInterna>();
		String sql = "SELECT * FROM vwhistoricodeajustearmazem";
		try {
			con = Conexao.getConexao();
			PreparedStatement preparador = con.prepareStatement(sql);
			ResultSet rs = preparador.executeQuery();
			while (rs.next()) {
				RequisicaoInterna pd = new RequisicaoInterna();
				    pd.setId_requi_interna(rs.getInt("id_num_difinventario"));
					pd.setNumero_requi_interna(rs.getString("numero_dif_inventario"));
					pd.setTotal_h_ajuste(rs.getDouble("total_h_ajuste"));
					pd.setPreco_positivo(rs.getDouble("preco_positivo"));
					pd.setPreco_negativo(rs.getDouble("preco_negativo"));
					pd.setNome_funcionario(rs.getString("n_c_funcionario"));
					Calendar data = Calendar.getInstance();
					data.setTime(rs.getDate("data_numero_h_ajuste"));
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
	
	public List<RequisicaoInterna> buscarRequisicaoDeclininada() {
		List<RequisicaoInterna> lista = new ArrayList<RequisicaoInterna>();
		String sql = "SELECT * FROM vwrequisicaointernadeclinadageral order by numero_requi_decli_farmacia DESC";
		try {
			con = Conexao.getConexao();
			PreparedStatement preparador = con.prepareStatement(sql);
			ResultSet rs = preparador.executeQuery();
			while (rs.next()) {
				RequisicaoInterna pd = new RequisicaoInterna();
				pd.setId_requi_interna(rs.getInt("id_requi_decli_farmacia"));
				pd.setNumero_requi_interna(rs.getString("numero_requi_decli_farmacia"));
				pd.setNomeRequerente_interno(rs.getString("requerente_interno"));
				pd.setNome_funcionario(rs.getString("n_c_funcionario"));
				pd.setNome(rs.getString("nome")+" "+rs.getString("ultimo_nome"));
				pd.setTotal(rs.getDouble("total_declinado"));
				pd.setQtd_requi_interna(rs.getInt("total_qtd_declinada"));
				Calendar data = Calendar.getInstance();
				data.setTime(rs.getDate("data_requi_interna"));
				pd.setData_requi_interna(data);
				Calendar data1 = Calendar.getInstance();
				data1.setTime(rs.getDate("data_requi_decli_farmacia"));
				pd.setData_registo(data1);
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
	
	public List<RequisicaoInterna> buscarRequisicaoDeclininada(String valorp) {
		List<RequisicaoInterna> lista = new ArrayList<RequisicaoInterna>();
		String sql = "SELECT * FROM vwrequisicaointernadeclinadageral where numero_requi_decli_farmacia =? order by numero_requi_decli_farmacia DESC";
		try {
			con = Conexao.getConexao();
			PreparedStatement preparador = con.prepareStatement(sql);
			preparador.setString(1, valorp);
			ResultSet rs = preparador.executeQuery();
			while (rs.next()) {
				RequisicaoInterna pd = new RequisicaoInterna();
				pd.setId_requi_interna(rs.getInt("id_requi_decli_farmacia"));
				pd.setNumero_requi_interna(rs.getString("numero_requi_decli_farmacia"));
				pd.setNomeRequerente_interno(rs.getString("requerente_interno"));
				pd.setNome_funcionario(rs.getString("n_c_funcionario"));
				pd.setNome(rs.getString("nome")+" "+rs.getString("ultimo_nome"));
				pd.setTotal(rs.getDouble("total_declinado"));
				pd.setQtd_requi_interna(rs.getInt("total_qtd_declinada"));
				Calendar data = Calendar.getInstance();
				data.setTime(rs.getDate("data_requi_interna"));
				pd.setData_requi_interna(data);
				Calendar data1 = Calendar.getInstance();
				data1.setTime(rs.getDate("data_requi_decli_farmacia"));
				pd.setData_registo(data1);
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
	
//	Preste a caducar no armazem
	public List<RequisicaoInterna> buscarProdutosExpirados() {
		List<RequisicaoInterna> lista = new ArrayList<RequisicaoInterna>();
		String sql = "SELECT * FROM vwprodutosemstocknoarmazempresteacaducar ORDER BY dias_restantes asc";
		try {
			con = Conexao.getConexao();
			PreparedStatement preparador = con.prepareStatement(sql);
			ResultSet rs = preparador.executeQuery();
			while (rs.next()) {
				RequisicaoInterna pd = new RequisicaoInterna();
				pd.setFk_produto(rs.getInt("fk_produto"));
				pd.setNome_comercial(rs.getString("nome_comercial"));
				pd.setCodigo_barra(rs.getString("codigo_barra"));
				Calendar data = Calendar.getInstance();
				data.setTime(rs.getDate("data_caducidade"));
				pd.setData_registo(data);
				pd.setNum_lote(rs.getString("lote_sere"));
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
	
	public void AtualizarQDtProdutoAlocadoIn(RequisicaoInterna p) {
		String sql = "update tblprodutoemestoqueinternamento set quantidade_produto = ?  where FK_id_produto = ? and numero_dolote_sere =?"; // and numero_dolote_sere = ?
		try {
			con = Conexao.getConexao();
			PreparedStatement preparador = con.prepareStatement(sql);
			preparador.setInt(1, p.getQtd_requi_interna());
			preparador.setInt(2, p.getFk_produto_requi_interna());
			 preparador.setString(3, p.getNum_lote());
			preparador.execute();

			// sql ="update tblencomendarecebida set alocado =? where FK_encomenda = ? and FK_produto = ?  ";
			// preparador = con.prepareStatement(sql);
			// preparador.setInt(1, 1);
			// preparador.setInt(2, p.getid);
			// preparador.setInt(3, p.getId_produto());
			// preparador.execute();
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
	}
	
	public void AtualizarQDtProdutoAlocadoBue(RequisicaoInterna p) {
		String sql = "update tblprodutoemestoqueemergencia set quantidade_produto = ?  where FK_id_produto = ? and numero_dolote_sere =?"; // and numero_dolote_sere = ?
		try {
			con = Conexao.getConexao();
			PreparedStatement preparador = con.prepareStatement(sql);
			preparador.setInt(1, p.getQtd_requi_interna());
			preparador.setInt(2, p.getFk_produto_requi_interna());
			 preparador.setString(3, p.getNum_lote());
			preparador.execute();

			// sql ="update tblencomendarecebida set alocado =? where FK_encomenda = ? and FK_produto = ?  ";
			// preparador = con.prepareStatement(sql);
			// preparador.setInt(1, 1);
			// preparador.setInt(2, p.getid);
			// preparador.setInt(3, p.getId_produto());
			// preparador.execute();
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
	}
	
	public void AtualizarQDtProdutoAlocadoLab(RequisicaoInterna p) {
		String sql = "update tblprodutoemestoquelaboratorio set quantidade_produto = ?  where FK_id_produto = ? and numero_dolote_sere =?"; // and numero_dolote_sere = ?
		try {
			con = Conexao.getConexao();
			PreparedStatement preparador = con.prepareStatement(sql);
			preparador.setInt(1, p.getQtd_requi_interna());
			preparador.setInt(2, p.getFk_produto_requi_interna());
			 preparador.setString(3, p.getNum_lote());
			preparador.execute();

			// sql ="update tblencomendarecebida set alocado =? where FK_encomenda = ? and FK_produto = ?  ";
			// preparador = con.prepareStatement(sql);
			// preparador.setInt(1, 1);
			// preparador.setInt(2, p.getid);
			// preparador.setInt(3, p.getId_produto());
			// preparador.execute();
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
	}
	
	public void AtualizarQDtProdutoAlocadoImag(RequisicaoInterna p) {
		String sql = "update tblprodutoemestoqueimagiologia set quantidade_produto = ?  where FK_id_produto = ? and numero_dolote_sere =?"; // and numero_dolote_sere = ?
		try {
			con = Conexao.getConexao();
			PreparedStatement preparador = con.prepareStatement(sql);
			preparador.setInt(1, p.getQtd_requi_interna());
			preparador.setInt(2, p.getFk_produto_requi_interna());
			 preparador.setString(3, p.getNum_lote());
			preparador.execute();
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
	}
	
	public List<RequisicaoInterna> buscarProdutosExpirados(String nome) {
		List<RequisicaoInterna> lista = new ArrayList<RequisicaoInterna>();
		String sql = "SELECT * FROM vwprodutosemstocknoarmazempresteacaducar WHERE nome_comercial LIKE ? or lote_sere LIKE ? OR dias_restantes =? ";
		try {
			con = Conexao.getConexao();
			PreparedStatement preparador = con.prepareStatement(sql);
			preparador.setString(1, "%"+nome+"%");
			preparador.setString(2, "%"+nome+"%");
			preparador.setString(3, nome);
			ResultSet rs = preparador.executeQuery();
			while (rs.next()) {
				RequisicaoInterna pd = new RequisicaoInterna();
				pd.setFk_produto(rs.getInt("fk_produto"));
				pd.setNome_comercial(rs.getString("nome_comercial"));
				pd.setCodigo_barra(rs.getString("codigo_barra"));
				Calendar data = Calendar.getInstance();
				data.setTime(rs.getDate("data_caducidade"));
				pd.setData_registo(data);
				pd.setNum_lote(rs.getString("lote_sere"));
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
	
	public List<Produtos> buscarProdutosExpirados(int prodId) {
		List<Produtos> lista = new ArrayList<Produtos>();
		String sql = "SELECT * FROM vwrequisicoesinternasgeraisporitens where id_requi_interna = ? group by nome_comercial";
		try {
			con = Conexao.getConexao();
			PreparedStatement preparador = con.prepareStatement(sql);
			preparador.setInt(1, prodId);
			ResultSet rs = preparador.executeQuery();
			while (rs.next()) {
				Produtos pd = new Produtos();
				pd.setFK_num_encomenda(rs.getInt("fk_produto_requi_interna"));
				pd.setId_produto(rs.getInt("fk_produto_requi_interna"));
				pd.setNome_comercial(rs.getString("nome_comercial"));
				pd.setNome(rs.getString("requerente_interno"));
				pd.setQdt_recebida(rs.getInt("produto_emstock"));
				pd.setQuantidade(rs.getInt("qtd_requi_interna"));
				pd.setPrecoUnitario(rs.getDouble("preco_dcompra"));
				pd.setTotal(rs.getInt("subtotal_r_geral"));
				pd.setQtd_por_receber(rs.getInt("qtd_por_receber"));
				pd.setNumero_lote_sere(rs.getString("numero_dolote_sere"));
				pd.setData_de_caducidade(rs.getString("data_decaducidade"));
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
	
}
