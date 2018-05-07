package ao.co.cligest.negocio;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import org.apache.commons.lang3.StringUtils;

import ao.co.cligest.contracts.IProcedimentoStock;
import ao.co.cligest.entidades.RequisicaoInterna;

public class ProcedimentoStockDAO extends ProdutosDAO implements IProcedimentoStock{ 
    private Connection con;
    Formatando ft = new Formatando();
	@Override
	public void novaRequisicaointernageralItem(RequisicaoInterna r) {
		String sql = "INSERT INTO tblrequisicaointernageralporitem (FK_requi_interna,fk_produto_requi_interna,qtd_requi_interna) VALUES (?,?,?)";
		try {
			con = Conexao.getConexao();
			PreparedStatement ps = con.prepareStatement(sql);
			ps.setInt(1, r.getFK_requi_interna());
			ps.setInt(2, r.getFk_produto_requi_interna());
			ps.setInt(3, r.getQtd_requi_interna());
			ps.execute();
			ps.close();
			con.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	@Override
	public int novaRequisicaointerna(RequisicaoInterna r) 
	{
		int ultimoCod = 0;
		String sql = "INSERT INTO tblrequisicaointernageral (numero_requi_interna,obs_requi_interna,fk_funcionario,data_requi_interna,FK_requerente_interno) VALUES (?,?,?,?,?)";
		try {
			 con = Conexao.getConexao();
			 PreparedStatement ps = con.prepareStatement(sql);
			 ps.setString(1, r.getNumero_requi_interna());
			 ps.setString(2, r.getObs_requi_interna());
			 ps.setInt(3, r.getFk_funcionario());
			 ps.setDate(4, ft.data_registo());
			 ps.setInt(5, r.getFK_requerente_interno());
			 ps.execute();
			 ResultSet rs = ps.executeQuery("SELECT LAST_INSERT_ID()");
				if (rs.next()) {
					ultimoCod = rs.getInt(1);
				}
			 
		} catch (Exception e) {
			e.printStackTrace();
		}
		return ultimoCod;
	}

	@Override
	public String numRequisicao(String origem) {
		String sql = "SELECT MAX(id_requi_interna) as numEnc FROM tblrequisicaointernageral";
		String numEncom = nomeRequisicao(origem);
		int numEnc  = 0;
		try {
			 con = Conexao.getConexao();
			 PreparedStatement ps = con.prepareStatement(sql);
			 ResultSet rs = ps.executeQuery();
			 if(rs.next())
				 numEnc =  rs.getInt("numEnc") + 1;
			 ps.close();
			 con.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return numEncom + StringUtils.leftPad(Integer.toString(numEnc), 4, "0");
	}
	
	@Override
	public String numDevolucao(String origem) {
		String sql = "SELECT MAX(id_dev_requi_int_farmacia) as numEnc FROM tbldevolverrequisicaointernageral";
		String numEncom = nomeRequisicao(origem);
		int numEnc  = 0;
		try {
			 con = Conexao.getConexao();
			 PreparedStatement ps = con.prepareStatement(sql);
			 ResultSet rs = ps.executeQuery();
			 if(rs.next())
				 numEnc =  rs.getInt("numEnc") + 1;
			 ps.close();
			 con.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return numEncom + StringUtils.leftPad(Integer.toString(numEnc), 4, "0");
	}
	
	protected String nomeRequisicao(String origem){
		 
		try {
			Date data = new Date();
			SimpleDateFormat formatar = new SimpleDateFormat("y");
			String dataString = formatar.format(data);
				 return "REF"+dataString+"/"+origem;
		} catch (Exception e) {
			e.printStackTrace();
		}
		return "";
	}

	@Override
	public List<RequisicaoInterna> buscarRequisicaoEfectuada(int origem) {
		
		String sql = "SELECT * FROM vwrequisicoesinternasgerais_procedimento WHERE FK_requerente_interno = ? order by id_requi_interna DESC";
		List<RequisicaoInterna> lista = new ArrayList<RequisicaoInterna>();
		try {
			con = Conexao.getConexao();
			PreparedStatement ps = con.prepareStatement(sql);
			ps.setInt(1, origem);
			ResultSet rs = ps.executeQuery();
			while (rs.next()) {
				RequisicaoInterna pd = new RequisicaoInterna();
				pd.setId_requi_interna(rs.getInt("id_requi_interna"));
				pd.setNumero_requi_interna(rs.getString("numero_requi_interna"));
				pd.setNomeRequerente_interno(rs.getString("requerente_interno"));
				pd.setFk_funcionario(rs.getInt("fk_funcionario"));
				pd.setQtd_requi_interna(rs.getInt("qtd_requi_interna"));
				
				Calendar data = Calendar.getInstance();
				data.setTime(rs.getDate("data_requi_interna"));
				pd.setData_requi_interna(data);
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
	public List<RequisicaoInterna> buscarRequisicaoReceber(int origem) {
		String sql = "SELECT * FROM vwrequisicoesinternasgeraisreceber_procedimento a "
				+ " WHERE FK_requerente_interno = ? and	not a.qtd_requi_interna = QtdProcessadaRecebidaGeral(a.id_requi_proce_geral) "
				+ " order by id_requi_interna DESC ";
		List<RequisicaoInterna> lista = new ArrayList<RequisicaoInterna>();
		try {
			con = Conexao.getConexao();
			PreparedStatement ps = con.prepareStatement(sql);
			ps.setInt(1, origem);
			ResultSet rs = ps.executeQuery();
			while (rs.next()) {
				RequisicaoInterna pd = new RequisicaoInterna();
				pd.setId_requi_interna(rs.getInt("id_requi_interna"));
				pd.setNumero_requi_interna(rs.getString("numero_requi_interna"));
				pd.setNomeRequerente_interno(rs.getString("requerente_interno"));
				pd.setNome_funcionario(rs.getString("n_c_funcionario"));
				pd.setQtd_requi_interna(rs.getInt("qtd_requi_interna"));
				pd.setQtd_requi_processada(rs.getInt("qtd_processada"));
				Calendar data = Calendar.getInstance();
				data.setTime(rs.getDate("data_requi_proce_geral"));
				pd.setData_requi_interna(data);
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
	public List<RequisicaoInterna> buscarRequisicaoReceberItens(int origem, int codreq) {
		String sql = " SELECT FK_requi_interna, fk_produto_requi_interna, nome_comercial,qtd_requi_interna,QtdProcessada(FK_requi_interna,fk_produto_requi_interna) qtd_processada, QtdProcessadaRecebida(FK_requi_interna,fk_produto_requi_interna) QtdProcessadaRecebida,codigo_barra,FK_requerente_interno,id_requi_proce_geral"
				+ " FROM   vwrequisicoesnaoprocessadalinhas where FK_requerente_interno = ? AND FK_requi_interna = ?  AND 	QtdProcessada(FK_requi_interna,fk_produto_requi_interna) > 0 "
				+ "AND QtdProcessada(FK_requi_interna,fk_produto_requi_interna) > QtdProcessadaRecebida(FK_requi_interna,fk_produto_requi_interna) ";
				/*+ " AND id_requi_proce_geral  NOT IN ( SELECT   recg.fk_req_processada  FROM   tblreceberrequisicaogeral recg JOIN tblreceberrequisicaogeralporlinha recgl  ON recg.id_receb_requi_geral = recgl.fk_receb_requi_geral_poritem "
				+ " WHERE   recgl.qdt_requi_receb_geral_poritem = QtdProcessada(FK_requi_interna,fk_produto_requi_interna)	 ) ";*/
		List<RequisicaoInterna> lista = new ArrayList<RequisicaoInterna>();
		try {
			con = Conexao.getConexao();
			PreparedStatement ps = con.prepareStatement(sql);
			ps.setInt(1, origem);
			ps.setInt(2, codreq);
			ResultSet rs = ps.executeQuery();
			while (rs.next()) {
				RequisicaoInterna pd = new RequisicaoInterna();
				pd.setId_requi_interna(rs.getInt("FK_requi_interna"));
				pd.setCodigo_barra(rs.getString("codigo_barra"));
				pd.setNome_comercial(rs.getString("nome_comercial"));
				pd.setFK_requerente_interno(rs.getInt("FK_requerente_interno"));
				pd.setQtd_requi_interna(rs.getInt("qtd_requi_interna"));
				pd.setQtd_requi_processada(rs.getInt("qtd_processada"));
				pd.setQtd_requi_recebida(rs.getInt("QtdProcessadaRecebida"));
				pd.setFk_produto_requi_interna(rs.getInt("fk_produto_requi_interna"));
				
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
	public List<RequisicaoInterna> buscarRequisicaoRecebidoItens(int origem, int codreq) {
		String sql = "SELECT FK_requi_interna, fk_produto_requi_interna, nome_comercial,qtd_requi_interna,QtdProcessada(FK_requi_interna,fk_produto_requi_interna) qtd_processada, QtdProcessadaRecebida(FK_requi_interna,fk_produto_requi_interna) QtdProcessadaRecebida,codigo_barra,FK_requerente_interno,id_requi_proce_geral "
				+ " FROM   vwrequisicoesnaoprocessadalinhas where FK_requerente_interno = ?"
				+ "  AND FK_requi_interna = ? AND  QtdProcessadaRecebida(FK_requi_interna,fk_produto_requi_interna) > 0";
		List<RequisicaoInterna> lista = new ArrayList<RequisicaoInterna>();
		try {
			con = Conexao.getConexao();
			PreparedStatement ps = con.prepareStatement(sql);
			ps.setInt(1, origem);
			ps.setInt(2, codreq);
			ResultSet rs = ps.executeQuery();
			while (rs.next()) {
				RequisicaoInterna pd = new RequisicaoInterna();
				pd.setId_requi_interna(rs.getInt("FK_requi_interna"));
				pd.setCodigo_barra(rs.getString("codigo_barra"));
				pd.setNome_comercial(rs.getString("nome_comercial"));
				pd.setFK_requerente_interno(rs.getInt("FK_requerente_interno"));
				pd.setQtd_requi_interna(rs.getInt("qtd_requi_interna"));
				pd.setQtd_requi_processada(rs.getInt("qtd_processada"));
				pd.setQtd_requi_recebida(rs.getInt("QtdProcessadaRecebida"));
				pd.setFk_produto_requi_interna(rs.getInt("fk_produto_requi_interna"));
				pd.setFk_req_processada(rs.getInt("id_requi_proce_geral"));
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
	public RequisicaoInterna buscarRequisicaoRecebidoItens(int origem, int codreq,int prd) {
		String sql = "SELECT FK_requi_interna, fk_produto_requi_interna, nome_comercial,qtd_requi_interna,QtdProcessada(FK_requi_interna,fk_produto_requi_interna) qtd_processada, QtdProcessadaRecebida(FK_requi_interna,fk_produto_requi_interna) QtdProcessadaRecebida,codigo_barra,FK_requerente_interno,id_requi_proce_geral "
				+ " FROM   vwrequisicoesnaoprocessadalinhas where FK_requerente_interno = ?"
				+ "  AND FK_requi_interna = ? AND fk_produto_requi_interna = ? AND  QtdProcessadaRecebida(FK_requi_interna,fk_produto_requi_interna) > 0";
		RequisicaoInterna pd = new RequisicaoInterna();
		try {
			con = Conexao.getConexao();
			PreparedStatement ps = con.prepareStatement(sql);
			ps.setInt(1, origem);
			ps.setInt(2, codreq);
			ps.setInt(3, prd);
			ResultSet rs = ps.executeQuery();
			if (rs.next()) {
				
				pd.setId_requi_interna(rs.getInt("FK_requi_interna"));
				pd.setCodigo_barra(rs.getString("codigo_barra"));
				pd.setNome_comercial(rs.getString("nome_comercial"));
				pd.setFK_requerente_interno(rs.getInt("FK_requerente_interno"));
				pd.setQtd_requi_interna(rs.getInt("qtd_requi_interna"));
				pd.setQtd_requi_processada(rs.getInt("qtd_processada"));
				pd.setQtd_requi_recebida(rs.getInt("QtdProcessadaRecebida"));
				pd.setFk_produto_requi_interna(rs.getInt("fk_produto_requi_interna"));
			}
			
			ps.close();
			con.close();
		}
			catch (Exception e) {
				e.printStackTrace();
			}
		return pd;
	}

	@Override
	public RequisicaoInterna buscarRequisicaoReceberItem(int origem, int codreq, int prd) {
		String sql = " SELECT FK_requi_interna, fk_produto_requi_interna, nome_comercial,qtd_requi_interna,QtdProcessada(FK_requi_interna,fk_produto_requi_interna) qtd_processada,codigo_barra,FK_requerente_interno,id_requi_proce_geral, QtdProcessadaRecebida(FK_requi_interna,fk_produto_requi_interna) QtdProcessadaRecebida "
				+ " FROM   vwrequisicoesnaoprocessadalinhas where FK_requerente_interno = ? AND FK_requi_interna = ? AND fk_produto_requi_interna = ? "
				+ " AND id_requi_proce_geral  NOT IN ( SELECT   recg.fk_req_processada  FROM   tblreceberrequisicaogeral recg JOIN tblreceberrequisicaogeralporlinha recgl  ON recg.id_receb_requi_geral = recgl.id_receber_requ_geral_poritem "
				+ " WHERE recgl.fk_produto_receber_requi_geral_poritem = ? AND recgl.qdt_requi_receb_geral_poritem = QtdProcessada(FK_requi_interna,fk_produto_requi_interna)	 ) ";
		RequisicaoInterna pd = new RequisicaoInterna();
		try {
			con = Conexao.getConexao();
			PreparedStatement ps = con.prepareStatement(sql);
			ps.setInt(1, origem);
			ps.setInt(2, codreq);
			ps.setInt(3, prd);
			ps.setInt(4, prd);
			ResultSet rs = ps.executeQuery();
			if (rs.next()) {
				pd.setId_requi_interna(rs.getInt("FK_requi_interna"));
				pd.setCodigo_barra(rs.getString("codigo_barra"));
				pd.setNome_comercial(rs.getString("nome_comercial"));
				pd.setFK_requerente_interno(rs.getInt("FK_requerente_interno"));
				pd.setQtd_requi_interna(rs.getInt("qtd_requi_interna"));
				pd.setQtd_requi_processada(rs.getInt("qtd_processada"));
				pd.setFk_produto_requi_interna(rs.getInt("fk_produto_requi_interna"));
				pd.setFk_produto_requi_interna(rs.getInt("fk_produto_requi_interna"));
				pd.setId_requi_proce_farmacia(rs.getInt("id_requi_proce_geral"));
				pd.setQtd_requi_recebida(rs.getInt("QtdProcessadaRecebida"));
			}
			
			ps.close();
			con.close();
		}
			catch (Exception e) {
				e.printStackTrace();
			}
		return pd;
	}

	@Override
	public int novaRecebimentoRequeridoGeral(RequisicaoInterna r) {
		String sql = "INSERT INTO tblreceberrequisicaogeral (numero_receb_requi_geral,fk_funcionario_receb_requi_geral,data_receb_requi_geral,fk_req_processada,fk_requerencte_interno) VALUES (?,?,?,?,?)";
		int ultimoCod = xequeRecebimentoRequeridoGeral(r);
		try {
			if(ultimoCod == 0){
				con = Conexao.getConexao();
				 PreparedStatement ps = con.prepareStatement(sql);
				 ps.setString(1, r.getNumero_receb_requi_farmacia());
				 ps.setInt(2, r.getFk_funcionario());
				 ps.setDate(3, ft.data_registo());
				 ps.setInt(4, r.getFk_req_processada());
				 ps.setInt(5, r.getFk_requerente());
				 ps.execute();
				 ResultSet rs = ps.executeQuery("SELECT LAST_INSERT_ID()");
					if (rs.next()) {
						ultimoCod = rs.getInt(1);
					}
					ps.close();
					con.close();
			  }
		} catch (Exception e) {
			e.printStackTrace();
		}
		return ultimoCod;
	}

	@Override
	public void novaRecebimentoRequeridoItem(RequisicaoInterna r) {
		String sql = "INSERT INTO tblreceberrequisicaogeralporlinha (fk_receb_requi_geral_poritem,fk_produto_receber_requi_geral_poritem,qdt_requi_receb_geral_poritem,caducidade_requi_receb_geral_poritem,lote_requi_receb_geral_poritem) "
				+ " VALUES (?,?,?,?,?)";
		
		if(xequeRecebimentoItem(r) == 0){
			try {
				 con = Conexao.getConexao();
				 PreparedStatement ps = con.prepareStatement(sql);
				 ps.setInt(1, r.getId_receb_requi_farmacia());
				 ps.setInt(2, r.getFk_produto_requi_interna());
				 ps.setInt(3, r.getQtd_requi_processada());
				 ps.setDate(4, new java.sql.Date (r.getCaducidade_item().getTimeInMillis()));
				 ps.setString(5, r.getNum_lote());
				
				 ps.execute();
				 ps.close();
				 con.close();  
			} catch (Exception e) {
				e.printStackTrace();
			}
		} 
		else
		{
			actualizaRecebimentoRequeridoItem(r);
		}
		
	}
	
	@Override
	public void lancarRecebidoItemInternamento(RequisicaoInterna r) {
		String sql = "INSERT INTO tblprodutoemestoqueinternamento (FK_id_produto,quantidade_produto,data_decaducidade,numero_dolote_sere,FK_local,FK_alabloco,FK_montra,FK_prartileira,FK_funcionario,data_dentrada_doestoque,hora_dentrada_doestoque) "
				+ " VALUES (?,?,?,?,?,?,?,?,?,?,?)";
		
		if(xequeLancamentoItem(r) == 0){
			try {
				 con = Conexao.getConexao();
				 PreparedStatement ps = con.prepareStatement(sql);
				 ps.setInt(1, r.getFk_produto_requi_interna());
				 ps.setInt(2, r.getQtd_requi_processada());
				 ps.setDate(3, new java.sql.Date (r.getCaducidade_item().getTimeInMillis()));
				 ps.setString(4, r.getNum_lote());
				 ps.setInt(5, r.getFk_local_armaze_requi_receb_farmacia_poritem());
				 ps.setInt(6, r.getFk_ala_requi_receb_farmacia_poritem());
				 ps.setInt(7, r.getFk_montra_requi_receb_farmacia_poritem());
				 ps.setInt(8, r.getFk_prateleira_requi_receb_farmacia_poritem());
				 ps.setInt(9, r.getFk_funcionario());
				 ps.setDate(10, ft.data_registo());
				 ps.setString(11, ft.horaAtual());
				 ps.execute();
				 ps.close();
				 con.close();  
			} catch (Exception e) {
				e.printStackTrace();
			}
		} 
		else
		{
			actualizaLancamentoItemInternamento(r);
		}
		
	}
	
	 
	protected void actualizaRecebimentoRequeridoItem(RequisicaoInterna r) {
		String sql = "UPDATE tblreceberrequisicaogeralporlinha SET qdt_requi_receb_geral_poritem = ? "
				+ " WHERE  fk_receb_requi_geral_poritem = ? AND fk_produto_receber_requi_geral_poritem = ? ";
		try {
			 int qtd_actual = qtdRecebimentoItem(r);
			 con = Conexao.getConexao();
			 PreparedStatement ps = con.prepareStatement(sql);
			 ps.setInt(1, r.getQtd_requi_processada()+qtd_actual);
			 ps.setInt(2, r.getId_receb_requi_farmacia());
			 ps.setInt(3, r.getFk_produto_requi_interna());
			 ps.execute();
			 ps.close();
			 con.close();  
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	protected void actualizaLancamentoItemInternamento(RequisicaoInterna r) {
		String sql = "UPDATE tblprodutoemestoqueinternamento SET quantidade_produto = ?, FK_local = ?,FK_alabloco = ?,FK_montra = ?,FK_prartileira  = ? "
				+ " WHERE  numero_dolote_sere = ? AND FK_id_produto = ? ";
		try {
			 int qtd_actual = qtdRecebimentoItem(r);
			 con = Conexao.getConexao();
			 PreparedStatement ps = con.prepareStatement(sql);
			 ps.setInt(1, r.getQtd_requi_processada()+qtd_actual);
			 ps.setInt(2, r.getFk_local_armaze_requi_receb_farmacia_poritem());
			 ps.setInt(3, r.getFk_ala_requi_receb_farmacia_poritem());
			 ps.setInt(4, r.getFk_montra_requi_receb_farmacia_poritem());
			 ps.setInt(5, r.getFk_prateleira_requi_receb_farmacia_poritem());
			 ps.setString(6, r.getNum_lote());
			 ps.setInt(7, r.getFk_produto_requi_interna());
			 ps.execute();
			 ps.close();
			 con.close();  
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	protected int xequeRecebimentoRequeridoGeral(RequisicaoInterna r){
		String sql = "SELECT * FROM tblreceberrequisicaogeral WHERE fk_req_processada = ? ";
		int ultimoCod = 0;
		try {
			 con = Conexao.getConexao();
			 PreparedStatement ps = con.prepareStatement(sql);
			 ps.setInt(1, r.getFk_req_processada());
			 ResultSet rs = ps.executeQuery();
			 if (rs.next()) {
					ultimoCod = rs.getInt(1);
				}
			ps.close();
			con.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return ultimoCod;
	}
	
	protected int xequeRecebimentoItem(RequisicaoInterna r){
		String sql = "SELECT id_receber_requ_geral_poritem FROM tblreceberrequisicaogeralporlinha WHERE fk_receb_requi_geral_poritem = ? AND fk_produto_receber_requi_geral_poritem = ?";
		int ultimoCod = 0;
		try {
			 con = Conexao.getConexao();
			 PreparedStatement ps = con.prepareStatement(sql);
			 ps.setInt(1, r.getId_receb_requi_farmacia() );
			 ps.setInt(2, r.getFk_produto_requi_interna());
			 ResultSet rs = ps.executeQuery();
			 if (rs.next()) {
					ultimoCod = rs.getInt(1);
				}
			ps.close();
			con.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return ultimoCod;
	}
	
	protected int xequeLancamentoItem(RequisicaoInterna r){
		String sql = "SELECT id_produto_emestoque_armazem FROM tblprodutoemestoqueinternamento WHERE FK_id_produto = ? AND numero_dolote_sere = ?  ";
		int ultimoCod = 0;
		try {
			 con = Conexao.getConexao();
			 PreparedStatement ps = con.prepareStatement(sql);
			 ps.setInt(1, r.getFk_produto_requi_interna());
			 ps.setString(2, r.getNum_lote() );
			 ResultSet rs = ps.executeQuery();
			 if (rs.next()) {
					ultimoCod = rs.getInt(1);
				}
			ps.close();
			con.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return ultimoCod;
	}
	
	protected int xequeLancamentoUTI(RequisicaoInterna r){
		String sql = "SELECT id_produto_emestoque_armazem FROM tblprodutoemestoqueinternamento WHERE FK_id_produto = ? AND numero_dolote_sere = ?  ";
		int ultimoCod = 0;
		try {
			 con = Conexao.getConexao();
			 PreparedStatement ps = con.prepareStatement(sql);
			 ps.setInt(1, r.getFk_produto_requi_interna());
			 ps.setString(2, r.getNum_lote() );
			 ResultSet rs = ps.executeQuery();
			 if (rs.next()) {
					ultimoCod = rs.getInt(1);
				}
			ps.close();
			con.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return ultimoCod;
	}
	protected int xequeLancamentoItemFar(RequisicaoInterna r){
		String sql = "SELECT id_produtoemestoque FROM tblprodutoemestoque WHERE FK_id_produto = ? AND numero_dolote_sere = ?  ";
		int ultimoCod = 0;
		try {
			 con = Conexao.getConexao();
			 PreparedStatement ps = con.prepareStatement(sql);
			 ps.setInt(1, r.getFk_produto_requi_interna());
			 ps.setString(2, r.getNum_lote() );
			 ResultSet rs = ps.executeQuery();
			 if (rs.next()) {
					ultimoCod = rs.getInt(1);
				}
			ps.close();
			con.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return ultimoCod;
	}

	protected int qtdRecebimentoItem(RequisicaoInterna r){
		String sql = "SELECT qdt_requi_receb_geral_poritem FROM tblreceberrequisicaogeralporlinha WHERE fk_receb_requi_geral_poritem = ? AND fk_produto_receber_requi_geral_poritem = ?";
		int ultimoCod = 0;
		try {
			 con = Conexao.getConexao();
			 PreparedStatement ps = con.prepareStatement(sql);
			 ps.setInt(1, r.getId_receb_requi_farmacia() );
			 ps.setInt(2, r.getFk_produto_requi_interna());
			 ResultSet rs = ps.executeQuery();
			 if (rs.next()) {
					ultimoCod = rs.getInt("qdt_requi_receb_geral_poritem");
				}
			ps.close();
			con.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return ultimoCod;
	}
	@Override
	public List<RequisicaoInterna> buscarProdutosStockProcedimento(int origem) {
		List<RequisicaoInterna> lista = new ArrayList<RequisicaoInterna>();
		try {
			switch(origem){
			case 1: break;
			 case 2:  lista = buscarProdutosStockExpirandoInternamento();
			 	break;
			 case 3:  lista = buscarProdutosStockExpirandoBUE();
			 	break;
			 case 4:  lista = buscarProdutosStockExpirandoLab();
			 	break;
			 case 5:  lista = buscarProdutosStockExpirandoIMA();
			 	break;
			 case 6:  lista = buscarProdutosStockExpirandoUTI();
			 	break;
			 case 7:  lista = buscarProdutosStockExpirandoBercario();
			 	break;
			 case 8:  lista = buscarProdutosStockExpirandoVacinas();
			 	break;
			 }
		}
			catch (Exception e) {
				e.printStackTrace();
			}
		return lista;
	}

	private List<RequisicaoInterna> buscarProdutosStockExpirandoIMA() {
		String sql = " SELECT * FROM vwprodutosemstock_imag  order by expirando asc";
		List<RequisicaoInterna> lista = new ArrayList<RequisicaoInterna>();
		try {
			con = Conexao.getConexao();
			PreparedStatement ps = con.prepareStatement(sql);
			ResultSet rs = ps.executeQuery();
			while (rs.next()) {
				RequisicaoInterna pd = new RequisicaoInterna();
				pd.setCodigo_barra(rs.getString("codigo_barra"));
				pd.setFk_produto(rs.getInt("id_produto"));
				pd.setNome_comercial(rs.getString("nome_comercial"));
				pd.setNome_quimico(rs.getString("nome_quimico"));
				pd.setForma(rs.getString("forma_farmaceutica"));
				pd.setNum_lote(rs.getString("numero_dolote_sere"));
				pd.setPreco_dcompra(rs.getDouble("preco_dcompra"));
				pd.setPreco_dvenda(rs.getDouble("preco_dvenda"));
				pd.setQtd_requi_recebida(rs.getInt("quantidade_produto"));
				pd.setTempoExpiracao(rs.getInt("expirando"));
				Calendar date = Calendar.getInstance();
				date.setTime(rs.getDate("data_decaducidade"));
				pd.setCaducidade_item(date);
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
	public List<RequisicaoInterna> buscarProdutosStockProcedimento(int origem, String termo) {
		String sql = " SELECT * FROM vwprodutosemstock_procedimento where fk_requerencte_interno = ? AND (nome_comercial like ? or nome_quimico like ? )";
		List<RequisicaoInterna> lista = new ArrayList<RequisicaoInterna>();
		try {
			con = Conexao.getConexao();
			PreparedStatement ps = con.prepareStatement(sql);
			ps.setInt(1, origem);
			ps.setString(2, "%"+termo+"%");
			ps.setString(3, "%"+termo+"%");
			ResultSet rs = ps.executeQuery();
			while (rs.next()) {
				RequisicaoInterna pd = new RequisicaoInterna();
				pd.setCodigo_barra(rs.getString("codigo_barra"));
				pd.setFk_produto(rs.getInt("fk_produto_receber_requi_geral_poritem"));
				pd.setNome_comercial(rs.getString("nome_comercial"));
				pd.setNome_quimico(rs.getString("nome_quimico"));
				pd.setForma(rs.getString("forma_farmaceutica"));
				pd.setNum_lote(rs.getString("lote_requi_receb_geral_poritem"));
				pd.setQtd_requi_recebida(rs.getInt("qdt_requi_receb_geral_poritem"));
				pd.setPreco_dcompra(rs.getDouble("preco_dcompra"));
				pd.setPreco_dvenda(rs.getDouble("preco_dvenda"));
				pd.setQtd_requi_recebida(rs.getInt("qdt_requi_receb_geral_poritem"));
				Calendar date = Calendar.getInstance();
				date.setTime(rs.getDate("caducidade_requi_receb_geral_poritem"));
				pd.setCaducidade_item(date);
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
	public List<RequisicaoInterna> buscarProdutosStockExpirandoProcedimento(int origem) {
		List<RequisicaoInterna> lista = new ArrayList<RequisicaoInterna>();
		try {
			 switch(origem){
			 case 2:  lista = buscarProdutosStockExpirandoInternamento();
			 break;
			 case 3:  lista = buscarProdutosStockExpirandoBUE();
			 break;
			 case 4:  lista = buscarProdutosStockExpirandoLab();
			 break;
			 case 5:  lista = buscarProdutosStockExpirandoIMA();
			 break;	
			 case 6:  lista = buscarProdutosStockExpirandoUTI();
			 break;	
			 case 7:  lista = buscarProdutosStockExpirandoBercario();
			 break;	
			 case 8:  lista = buscarProdutosStockExpirandoVacinas();
			 break;	
			 }
				
			 
		}
			catch (Exception e) {
				e.printStackTrace();
			}
		return lista;
	}
	
	private List<RequisicaoInterna> buscarProdutosStockExpirandoVacinas() {
		String sql = " SELECT * FROM vwprodutosemstock_vacinas  order by expirando asc";
		List<RequisicaoInterna> lista = new ArrayList<RequisicaoInterna>();
		try {
			con = Conexao.getConexao();
			PreparedStatement ps = con.prepareStatement(sql);
			ResultSet rs = ps.executeQuery();
			while (rs.next()) {
				RequisicaoInterna pd = new RequisicaoInterna();
				pd.setCodigo_barra(rs.getString("codigo_barra"));
				pd.setFk_produto(rs.getInt("id_produto"));
				pd.setNome_comercial(rs.getString("nome_comercial"));
				pd.setNome_quimico(rs.getString("nome_quimico"));
				pd.setForma(rs.getString("forma_farmaceutica"));
				pd.setNum_lote(rs.getString("numero_dolote_sere"));
				pd.setPreco_dcompra(rs.getDouble("preco_dcompra"));
				pd.setPreco_dvenda(rs.getDouble("preco_dvenda"));
				pd.setQtd_requi_recebida(rs.getInt("quantidade_produto"));
				pd.setTempoExpiracao(rs.getInt("expirando"));
				Calendar date = Calendar.getInstance();
				date.setTime(rs.getDate("data_decaducidade"));
				pd.setCaducidade_item(date);
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

	private List<RequisicaoInterna> buscarProdutosStockExpirandoBercario() {
		String sql = " SELECT * FROM vwprodutosemstock_bercario  order by expirando asc";
		List<RequisicaoInterna> lista = new ArrayList<RequisicaoInterna>();
		try {
			con = Conexao.getConexao();
			PreparedStatement ps = con.prepareStatement(sql);
			ResultSet rs = ps.executeQuery();
			while (rs.next()) {
				RequisicaoInterna pd = new RequisicaoInterna();
				pd.setCodigo_barra(rs.getString("codigo_barra"));
				pd.setFk_produto(rs.getInt("id_produto"));
				pd.setNome_comercial(rs.getString("nome_comercial"));
				pd.setNome_quimico(rs.getString("nome_quimico"));
				pd.setForma(rs.getString("forma_farmaceutica"));
				pd.setNum_lote(rs.getString("numero_dolote_sere"));
				pd.setPreco_dcompra(rs.getDouble("preco_dcompra"));
				pd.setPreco_dvenda(rs.getDouble("preco_dvenda"));
				pd.setQtd_requi_recebida(rs.getInt("quantidade_produto"));
				pd.setTempoExpiracao(rs.getInt("expirando"));
				Calendar date = Calendar.getInstance();
				date.setTime(rs.getDate("data_decaducidade"));
				pd.setCaducidade_item(date);
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
	

	private List<RequisicaoInterna> buscarProdutosStockExpirandoLab() {
		String sql = " SELECT * FROM vwprodutosemstock_lab  order by expirando asc";
		List<RequisicaoInterna> lista = new ArrayList<RequisicaoInterna>();
		try {
			con = Conexao.getConexao();
			PreparedStatement ps = con.prepareStatement(sql);
			ResultSet rs = ps.executeQuery();
			while (rs.next()) {
				RequisicaoInterna pd = new RequisicaoInterna();
				pd.setCodigo_barra(rs.getString("codigo_barra"));
				pd.setFk_produto(rs.getInt("id_produto"));
				pd.setNome_comercial(rs.getString("nome_comercial"));
				pd.setNome_quimico(rs.getString("nome_quimico"));
				pd.setForma(rs.getString("forma_farmaceutica"));
				pd.setNum_lote(rs.getString("numero_dolote_sere"));
				pd.setPreco_dcompra(rs.getDouble("preco_dcompra"));
				pd.setPreco_dvenda(rs.getDouble("preco_dvenda"));
				pd.setQtd_requi_recebida(rs.getInt("quantidade_produto"));
				pd.setTempoExpiracao(rs.getInt("expirando"));
				Calendar date = Calendar.getInstance();
				date.setTime(rs.getDate("data_decaducidade"));
				pd.setCaducidade_item(date);
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

	private List<RequisicaoInterna> buscarProdutosStockExpirandoUTI() {
		String sql = " SELECT * FROM vwprodutosemstock_uti  order by expirando asc";
		List<RequisicaoInterna> lista = new ArrayList<RequisicaoInterna>();
		try {
			con = Conexao.getConexao();
			PreparedStatement ps = con.prepareStatement(sql);
			ResultSet rs = ps.executeQuery();
			while (rs.next()) {
				RequisicaoInterna pd = new RequisicaoInterna();
				pd.setCodigo_barra(rs.getString("codigo_barra"));
				pd.setFk_produto(rs.getInt("id_produto"));
				pd.setNome_comercial(rs.getString("nome_comercial"));
				pd.setNome_quimico(rs.getString("nome_quimico"));
				pd.setForma(rs.getString("forma_farmaceutica"));
				pd.setNum_lote(rs.getString("numero_dolote_sere"));
				pd.setPreco_dcompra(rs.getDouble("preco_dcompra"));
				pd.setPreco_dvenda(rs.getDouble("preco_dvenda"));
				pd.setQtd_requi_recebida(rs.getInt("quantidade_produto"));
				pd.setTempoExpiracao(rs.getInt("expirando"));
				Calendar date = Calendar.getInstance();
				date.setTime(rs.getDate("data_decaducidade"));
				pd.setCaducidade_item(date);
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

	protected List<RequisicaoInterna> buscarProdutosStockExpirandoInternamento() {
		String sql = " SELECT * FROM vwprodutosemstock_internamento  order by expirando asc";
		List<RequisicaoInterna> lista = new ArrayList<RequisicaoInterna>();
		try {
			con = Conexao.getConexao();
			PreparedStatement ps = con.prepareStatement(sql);
			ResultSet rs = ps.executeQuery();
			while (rs.next()) {
				RequisicaoInterna pd = new RequisicaoInterna();
				pd.setCodigo_barra(rs.getString("codigo_barra"));
				pd.setFk_produto(rs.getInt("id_produto"));
				pd.setNome_comercial(rs.getString("nome_comercial"));
				pd.setNome_quimico(rs.getString("nome_quimico"));
				pd.setForma(rs.getString("forma_farmaceutica"));
				pd.setNum_lote(rs.getString("numero_dolote_sere"));
				pd.setPreco_dcompra(rs.getDouble("preco_dcompra"));
				pd.setPreco_dvenda(rs.getDouble("preco_dvenda"));
				pd.setQtd_requi_recebida(rs.getInt("quantidade_produto"));
				pd.setTempoExpiracao(rs.getInt("expirando"));
				Calendar date = Calendar.getInstance();
				date.setTime(rs.getDate("data_decaducidade"));
				pd.setCaducidade_item(date);
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
	
	protected List<RequisicaoInterna> buscarProdutosStockExpirandoInternamento(String param) {
		String sql = " SELECT * FROM vwprodutosemstock_internamento where codigo_barra LIKE ? or nome_comercial LIKE ? order by expirando asc";
		List<RequisicaoInterna> lista = new ArrayList<RequisicaoInterna>();
		try {
			con = Conexao.getConexao();
			PreparedStatement ps = con.prepareStatement(sql);
			ps.setString(1, "%"+param+"%");
			ps.setString(2, "%"+param+"%");
			ResultSet rs = ps.executeQuery();
			while (rs.next()) {
				RequisicaoInterna pd = new RequisicaoInterna();
				pd.setCodigo_barra(rs.getString("codigo_barra"));
				pd.setFk_produto(rs.getInt("id_produto"));
				pd.setNome_comercial(rs.getString("nome_comercial"));
				pd.setNome_quimico(rs.getString("nome_quimico"));
				pd.setForma(rs.getString("forma_farmaceutica"));
				pd.setNum_lote(rs.getString("numero_dolote_sere"));
				pd.setPreco_dcompra(rs.getDouble("preco_dcompra"));
				pd.setPreco_dvenda(rs.getDouble("preco_dvenda"));
				pd.setQtd_requi_recebida(rs.getInt("quantidade_produto"));
				pd.setTempoExpiracao(rs.getInt("expirando"));
				Calendar date = Calendar.getInstance();
				date.setTime(rs.getDate("data_decaducidade"));
				pd.setCaducidade_item(date);
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
	
	protected List<RequisicaoInterna> buscarProdutosStockExpirandoBUE() {
		String sql = " SELECT * FROM vwprodutosemstock_bancoue  order by expirando asc";
		List<RequisicaoInterna> lista = new ArrayList<RequisicaoInterna>();
		try {
			con = Conexao.getConexao();
			PreparedStatement ps = con.prepareStatement(sql);
			ResultSet rs = ps.executeQuery();
			while (rs.next()) {
				RequisicaoInterna pd = new RequisicaoInterna();
				pd.setCodigo_barra(rs.getString("codigo_barra"));
				pd.setFk_produto(rs.getInt("id_produto"));
				pd.setNome_comercial(rs.getString("nome_comercial"));
				pd.setNome_quimico(rs.getString("nome_quimico"));
				pd.setForma(rs.getString("forma_farmaceutica"));
				pd.setNum_lote(rs.getString("numero_dolote_sere"));
				pd.setPreco_dcompra(rs.getDouble("preco_dcompra"));
				pd.setPreco_dvenda(rs.getDouble("preco_dvenda"));
				pd.setQtd_requi_recebida(rs.getInt("quantidade_produto"));
				pd.setTempoExpiracao(rs.getInt("expirando"));
				Calendar date = Calendar.getInstance();
				date.setTime(rs.getDate("data_decaducidade"));
				pd.setCaducidade_item(date);
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
	public List<RequisicaoInterna> buscarProdutosStockExpirandoProcedimento(int origem, String num_lote,String num_dia) {
		String sql = " SELECT * FROM vwprodutosemstock_procedimento where fk_requerencte_interno = ? AND (lote_requi_receb_geral_poritem = ? OR  expirando = ? ) order by expirando asc";
		List<RequisicaoInterna> lista = new ArrayList<RequisicaoInterna>();
		try {
			con = Conexao.getConexao();
			PreparedStatement ps = con.prepareStatement(sql);
			ps.setInt(1, origem);
			ps.setString(2, num_lote);
			ps.setString(3, num_dia);
			ResultSet rs = ps.executeQuery(); 
			while (rs.next()) {
				RequisicaoInterna pd = new RequisicaoInterna();
				pd.setCodigo_barra(rs.getString("codigo_barra"));
				pd.setFk_produto(rs.getInt("id_produto"));
				pd.setNome_comercial(rs.getString("nome_comercial"));
				pd.setNome_quimico(rs.getString("nome_quimico"));
				pd.setForma(rs.getString("forma_farmaceutica"));
				pd.setNum_lote(rs.getString("lote_requi_receb_geral_poritem"));
				pd.setPreco_dcompra(rs.getDouble("preco_dcompra"));
				pd.setPreco_dvenda(rs.getDouble("preco_dvenda"));
				pd.setQtd_requi_recebida(rs.getInt("qdt_requi_receb_geral_poritem"));
				pd.setTempoExpiracao(rs.getInt("expirando"));
				Calendar date = Calendar.getInstance();
				date.setTime(rs.getDate("caducidade_requi_receb_geral_poritem"));
				pd.setCaducidade_item(date);
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
	public RequisicaoInterna buscarItemNoStock(int origem, int prd, String num_lote) {
		String sql = " SELECT * FROM vwprodutosemstock_procedimento where fk_requerencte_interno = ? AND lote_requi_receb_geral_poritem = ? AND  id_produto = ?  order by expirando asc";
		RequisicaoInterna pd = new RequisicaoInterna();
		try {
			con = Conexao.getConexao();
			PreparedStatement ps = con.prepareStatement(sql);
			ps.setInt(1, origem);
			ps.setString(2, num_lote);
			ps.setInt(3, prd);
			ResultSet rs = ps.executeQuery();
			if (rs.next()) {
				pd.setCodigo_barra(rs.getString("codigo_barra"));
				pd.setId_receb_requi_farmacia(rs.getInt("id_receb_requi_geral"));
				pd.setFk_produto(rs.getInt("id_produto"));
				pd.setNome_comercial(rs.getString("nome_comercial"));
				pd.setNome_quimico(rs.getString("nome_quimico"));
				pd.setForma(rs.getString("forma_farmaceutica"));
				pd.setNum_lote(rs.getString("lote_requi_receb_geral_poritem"));
				pd.setPreco_dcompra(rs.getDouble("preco_dcompra"));
				pd.setPreco_dvenda(rs.getDouble("preco_dvenda"));
				pd.setQtd_requi_recebida(rs.getInt("qdt_requi_receb_geral_poritem"));
				pd.setTempoExpiracao(rs.getInt("expirando"));
				Calendar date = Calendar.getInstance();
				date.setTime(rs.getDate("caducidade_requi_receb_geral_poritem"));
				pd.setCaducidade_item(date);
			}
			ps.close();
			con.close();
		}
			catch (Exception e) {
				e.printStackTrace();
			}
		return pd;
	}

	@Override
	public List<RequisicaoInterna> buscarRequisicaoRecebida(int origem) {
		String sql = "SELECT * FROM vwrequisicoesinternasgeraisreceber_procedimento WHERE FK_requerente_interno = ? AND id_requi_proce_geral in (select fk_req_processada from tblreceberrequisicaogeral)order by id_requi_interna DESC";
		List<RequisicaoInterna> lista = new ArrayList<RequisicaoInterna>();
		try {
			con = Conexao.getConexao();
			PreparedStatement ps = con.prepareStatement(sql);
			ps.setInt(1, origem);
			ResultSet rs = ps.executeQuery();
			while (rs.next()) {
				RequisicaoInterna pd = new RequisicaoInterna();
				pd.setId_requi_interna(rs.getInt("id_requi_interna"));
				pd.setNumero_requi_interna(rs.getString("numero_requi_interna"));
				pd.setNomeRequerente_interno(rs.getString("requerente_interno"));
				pd.setNome_funcionario(rs.getString("n_c_funcionario"));
				pd.setQtd_requi_interna(rs.getInt("qtd_requi_interna"));
				pd.setQtd_requi_processada(rs.getInt("qtd_processada"));
				pd.setFk_req_processada(rs.getInt("id_requi_proce_geral"));
				Calendar data = Calendar.getInstance();
				data.setTime(rs.getDate("data_requi_proce_geral"));
				pd.setData_requi_interna(data);
				
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
	public List<RequisicaoInterna> buscarRequisicaoRecebida(int origem,int codreq) {
		String sql = "SELECT * FROM vwrequisicoesinternasgeraisreceber_procedimento WHERE FK_requerente_interno = ? AND id_requi_interna = ? AND id_requi_proce_geral in (select fk_req_processada from tblreceberrequisicaogeral )order by id_requi_interna DESC";
		List<RequisicaoInterna> lista = new ArrayList<RequisicaoInterna>();
		try {
			con = Conexao.getConexao();
			PreparedStatement ps = con.prepareStatement(sql);
			ps.setInt(1, origem);
			ps.setInt(2, codreq);
			ResultSet rs = ps.executeQuery();
			while (rs.next()) {
				RequisicaoInterna pd = new RequisicaoInterna();
				pd.setId_requi_interna(rs.getInt("id_requi_interna"));
				pd.setNumero_requi_interna(rs.getString("numero_requi_interna"));
				pd.setNomeRequerente_interno(rs.getString("requerente_interno"));
				pd.setNome_funcionario(rs.getString("n_c_funcionario"));
				pd.setQtd_requi_interna(rs.getInt("qtd_requi_interna"));
				pd.setQtd_requi_processada(rs.getInt("qtd_processada"));
				
				Calendar data = Calendar.getInstance();
				data.setTime(rs.getDate("data_requi_proce_geral"));
				pd.setData_requi_interna(data);
				
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
	public void actualizarStockItem(RequisicaoInterna r) {
		 switch(r.getFk_requerente())
		 {
		 case 1:
			 break;
		 case 2: actualizaLancamentoItemInternamento(r);
			 break;
		 case 3: actualizaLancamentoItemBUE(r);
			 break;
		 case 4: actualizaLancamentoItemLab(r);
			 break;
		 case 5:
			 break;
		 case 6: actualizaLancamentoItemUTI(r);
			 break;
		 case 7: actualizaLancamentoItemBercario(r);
		 break;
		 }
	}
	
	

	@Override
	public RequisicaoInterna buscarItemNoStock(int origem, int prd) {
		String sql = " SELECT * FROM vwprodutosemstock_procedimento where fk_requerencte_interno = ?  AND  id_produto = ?  order by expirando asc";
		RequisicaoInterna pd = new RequisicaoInterna();
		try {
			con = Conexao.getConexao();
			PreparedStatement ps = con.prepareStatement(sql);
			ps.setInt(1, origem);
			ps.setInt(2, prd);
			ResultSet rs = ps.executeQuery();
			if (rs.next()) {
				pd.setCodigo_barra(rs.getString("codigo_barra"));
				pd.setId_receb_requi_farmacia(rs.getInt("id_receb_requi_geral"));
				pd.setFk_produto(rs.getInt("id_produto"));
				pd.setNome_comercial(rs.getString("nome_comercial"));
				pd.setNome_quimico(rs.getString("nome_quimico"));
				pd.setForma(rs.getString("forma_farmaceutica"));
				pd.setNum_lote(rs.getString("lote_requi_receb_geral_poritem"));
				pd.setPreco_dcompra(rs.getDouble("preco_dcompra"));
				pd.setPreco_dvenda(rs.getDouble("preco_dvenda"));
				pd.setQtd_requi_recebida(rs.getInt("qdt_requi_receb_geral_poritem"));
				pd.setTempoExpiracao(rs.getInt("expirando"));
				Calendar date = Calendar.getInstance();
				date.setTime(rs.getDate("caducidade_requi_receb_geral_poritem"));
				pd.setCaducidade_item(date);
			}
			ps.close();
			con.close();
		}
			catch (Exception e) {
				e.printStackTrace();
			}
		return pd;
	}

	@Override
	public int novaDevolucaoProcedimentoGeral(RequisicaoInterna r) {
		int ultimoCod = 0;
		String sql = "INSERT INTO tbldevolverrequisicaointernageral (numero_dev_requi_int_farmacia,fk_funcionario,data_dev_requi_int_farmacia,razao_dev_requi_int_farmacia,fk_requisicao_recebida,fk_requerencte_interno)"
				+ "   VALUES(?,?,?,?,?,?) ";
		try {
			 con = Conexao.getConexao();
			 PreparedStatement ps = con.prepareStatement(sql);
			 ps.setString(1, r.getNumero_dev_requi_int_farmacia());
			 ps.setInt(2, r.getFk_funcionario());
			 ps.setDate(3, ft.data_registo());
			 ps.setString(4, r.getRazao_dev_requi_int_farmacia());
			 ps.setInt(5, r.getFk_requisicao_recebida());
			 ps.setInt(6, r.getFK_requerente_interno());
			 ps.execute();
			 ResultSet rs = ps.executeQuery("SELECT LAST_INSERT_ID()");
				if (rs.next()) {
					ultimoCod = rs.getInt(1);
				}
			ps.close();
			con.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return ultimoCod;
	}

	@Override
	public void DevolucaoProcedimentoGeralItem(RequisicaoInterna r) {
		String sql = "INSERT INTO tbldevolverrequisicaointernageralcomlinhas (FK_dev_requi_int_farmacia,fk_produto,qtd_dev_requi_int_farmacia) VALUES (?,?,?)";
		try {
			 con = Conexao.getConexao();
			 PreparedStatement ps = con.prepareStatement(sql);
			 ps.setInt(1, r.getFK_dev_requi_int_farmacia());
			 ps.setInt(2, r.getFk_produto());
			 ps.setInt(3, r.getQtd_dev_requi_int_farmacia());
			 ps.execute();
			 ps.close();
			 con.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	@Override
	public List<RequisicaoInterna> buscarProdutosDevolvidosEmStock(int origem) {
		
		String sql = "SELECT * FROM vwprodutostockdevolvidosprocedimento WHERE fk_requerencte_interno = ? order by id_dev_requi_int_farmacia DESC";
		List<RequisicaoInterna> lista = new ArrayList<RequisicaoInterna>();
		try {
			con = Conexao.getConexao();
			PreparedStatement ps = con.prepareStatement(sql);
			ps.setInt(1, origem);
			ResultSet rs = ps.executeQuery();
			while (rs.next()) {
				RequisicaoInterna pd = new RequisicaoInterna();
				pd.setId_dev_requi_int_farm_comlinhas(rs.getInt("id_dev_requi_int_farm_comlinhas"));
				pd.setNumero_dev_requi_int_farmacia(rs.getString("numero_dev_requi_int_farmacia"));
				pd.setFk_produto(rs.getInt("fk_produto"));
				pd.setRazao_dev_requi_int_farmacia(rs.getString("razao_dev_requi_int_farmacia"));
				pd.setNomeRequerente_interno(rs.getString("requerente_interno"));
				pd.setFk_funcionario(rs.getInt("fk_funcionario"));
				pd.setNome_funcionario(rs.getString("n_c_funcionario"));
				pd.setQtd_dev_requi_int_farmacia(rs.getInt("qtd_dev_requi_int_farmacia"));
				Calendar data = Calendar.getInstance();
				data.setTime(rs.getDate("data_dev_requi_int_farmacia"));
				pd.setData_dev_requi_int_farmacia(data);
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
	public RequisicaoInterna buscarRequisicaoItemLoteCaducidade(int codreq, int prd) {
		String sql = "SELECT * FROM vwrequisicaoprocessadaporlinha WHERE fk_requisicao_original = ? AND fk_produto = ?";
		RequisicaoInterna reqLot = new RequisicaoInterna();
		try {
			 con = Conexao.getConexao();
			 PreparedStatement ps = con.prepareStatement(sql);
			 ps.setInt(1, codreq);
			 ps.setInt(2, prd);
			 ResultSet rs = ps.executeQuery();
			 if(rs.next()){
				 reqLot.setNum_lote(rs.getString("lote_sere"));
				 Calendar dataCad = Calendar.getInstance();
				 dataCad.setTime(rs.getDate("data_caducidade"));
				 reqLot.setCaducidade_item(dataCad);
			 }
		} catch (Exception e) {
			e.printStackTrace();
		}
		return reqLot;
	}
	
	protected RequisicaoInterna buscarItemLoteCaducidade(int codreq, int prd) {
		String sql = "SELECT   `a`.`fk_req_processada`,  `a`.`fk_requerencte_interno`,  `al`.`caducidade_requi_receb_geral_poritem`, `al`.`fk_produto_receber_requi_geral_poritem`,  `al`.`lote_requi_receb_geral_poritem` "
				+ " FROM   `tblreceberrequisicaogeral` `a` INNER JOIN `tblreceberrequisicaogeralporlinha` `al` ON `a`.`id_receb_requi_geral` = `al`.`fk_receb_requi_geral_poritem`"
				+ " WHERE `al`.`fk_produto_receber_requi_geral_poritem` = ? AND `a`.`fk_req_processada` = ?";
		RequisicaoInterna reqLot = new RequisicaoInterna();
		try {
			 con = Conexao.getConexao();
			 PreparedStatement ps = con.prepareStatement(sql);
			 ps.setInt(1, prd);
			 ps.setInt(2, codreq);
			 ResultSet rs = ps.executeQuery();
			 if(rs.next()){
				 reqLot.setNum_lote(rs.getString("lote_requi_receb_geral_poritem"));
				 Calendar dataCad = Calendar.getInstance();
				 dataCad.setTime(rs.getDate("caducidade_requi_receb_geral_poritem"));
				 reqLot.setCaducidade_item(dataCad);
			 }
			 else
				 reqLot.setCaducidade_item(Calendar.getInstance());
		} catch (Exception e) {
			e.printStackTrace();
		}
		return reqLot;
	}

	@Override
	public int xequeProdutoStock( int prd, int codreq) {
		String sql = "SELECT FK_id_produto from tblprodutoemestoqueinternamento where FK_id_produto = ? and numero_dolote_sere = ? and quantidade_produto > 0 ";
		RequisicaoInterna r = buscarItemLoteCaducidade(codreq,prd);
		try {
		 
				 con = Conexao.getConexao();
				 PreparedStatement ps = con.prepareStatement(sql);
				 ps.setInt(1, prd);
				 ps.setString(2, r.getNum_lote());
				 
				 ResultSet rs = ps.executeQuery();
				 if(rs.next())
					 return 1;
			 
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		return 0;
	}
	
	@Override
	public int xequeProdutoStockUTI( int prd, int codreq) {
		String sql = "SELECT FK_id_produto from tblprodutoemestoque_uti where FK_id_produto = ? and numero_dolote_sere_uti = ? and quantidade_produto_uti > 0 ";
		RequisicaoInterna r = buscarItemLoteCaducidade(codreq,prd);
		try {
		 
				 con = Conexao.getConexao();
				 PreparedStatement ps = con.prepareStatement(sql);
				 ps.setInt(1, prd);
				 ps.setString(2, r.getNum_lote());
				 
				 ResultSet rs = ps.executeQuery();
				 if(rs.next())
					 return 1;
			 
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		return 0;
	}

	@Override
	public int xequeProdutoStockFarm(int prd, int codreq) {
		String sql = "SELECT FK_id_produto from tblprodutoemestoque where FK_id_produto = ? and numero_dolote_sere = ? and quantidade_produto > 0 ";
		RequisicaoInterna r = buscarItemLoteCaducidade(codreq,prd);
		try {
		 
				 con = Conexao.getConexao();
				 PreparedStatement ps = con.prepareStatement(sql);
				 ps.setInt(1, prd);
				 ps.setString(2, r.getNum_lote());
				 
				 ResultSet rs = ps.executeQuery();
				 if(rs.next())
					 return 1;
			 
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		return 0;
	}
	
	@Override
	public int xequeProdutoStockBUE(int prd, int codreq) {
		String sql = "SELECT FK_id_produto from tblprodutoemestoquebancodeurgencia where FK_id_produto = ? and numero_dolote_sere = ? and quantidade_produto > 0 ";
		RequisicaoInterna r = buscarItemLoteCaducidade(codreq,prd);
		try {
		 
				 con = Conexao.getConexao();
				 PreparedStatement ps = con.prepareStatement(sql);
				 ps.setInt(1, prd);
				 ps.setString(2, r.getNum_lote());
				 
				 ResultSet rs = ps.executeQuery();
				 if(rs.next())
					 return 1;
			 
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		return 0;
	}

	@Override
	public void lancarRecebidoItemFarmacia(RequisicaoInterna r) {
		String sql = "INSERT INTO tblprodutoemestoque (FK_id_produto,quantidade_produto,data_decaducidade,numero_dolote_sere,FK_local,FK_alabloco,FK_montra,FK_prartileira,FK_funcionario,data_dentrada_doestoque,hora_dentrada_doestoque) "
				+ " VALUES (?,?,?,?,?,?,?,?,?,?,?)";
		
		if(xequeLancamentoItemFar(r) == 0){
			try {
				 con = Conexao.getConexao();
				 PreparedStatement ps = con.prepareStatement(sql);
				 ps.setInt(1, r.getFk_produto_requi_interna());
				 ps.setInt(2, r.getQtd_requi_processada());
				 ps.setDate(3, new java.sql.Date (r.getCaducidade_item().getTimeInMillis()));
				 ps.setString(4, r.getNum_lote());
				 ps.setInt(5, r.getFk_local_armaze_requi_receb_farmacia_poritem());
				 ps.setInt(6, r.getFk_ala_requi_receb_farmacia_poritem());
				 ps.setInt(7, r.getFk_montra_requi_receb_farmacia_poritem());
				 ps.setInt(8, r.getFk_prateleira_requi_receb_farmacia_poritem());
				 ps.setInt(9, r.getFk_funcionario());
				 ps.setDate(10, ft.data_registo());
				 ps.setString(11, ft.horaAtual());
				 ps.execute();
				 ps.close();
				 con.close();  
			} catch (Exception e) {
				e.printStackTrace();
			}
		} 
		else
		{
			actualizaLancamentoItemFarmacia(r);
		}
	}

	private void actualizaLancamentoItemFarmacia(RequisicaoInterna r) {
		String sql = "UPDATE tblprodutoemestoque SET quantidade_produto = ?, FK_local = ?,FK_alabloco = ?,FK_montra = ?,FK_prartileira  = ? "
				+ " WHERE  numero_dolote_sere = ? AND FK_id_produto = ? ";
		try {
			 int qtd_actual = qtdRecebimentoItem(r);
			 con = Conexao.getConexao();
			 PreparedStatement ps = con.prepareStatement(sql);
			 ps.setInt(1, r.getQtd_requi_processada()+qtd_actual);
			 ps.setInt(2, r.getFk_local_armaze_requi_receb_farmacia_poritem());
			 ps.setInt(3, r.getFk_ala_requi_receb_farmacia_poritem());
			 ps.setInt(4, r.getFk_montra_requi_receb_farmacia_poritem());
			 ps.setInt(5, r.getFk_prateleira_requi_receb_farmacia_poritem());
			 ps.setString(6, r.getNum_lote());
			 ps.setInt(7, r.getFk_produto_requi_interna());
			 ps.execute();
			 ps.close();
			 con.close();  
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	@Override
	public void lancarRecebidoItemBancoUE(RequisicaoInterna r) {
		String sql = "INSERT INTO tblprodutoemestoquebancodeurgencia (FK_id_produto,quantidade_produto,data_decaducidade,numero_dolote_sere,FK_local,FK_alabloco,FK_montra,FK_prartileira,FK_funcionario,data_dentrada_doestoque,hora_dentrada_doestoque) "
				+ " VALUES (?,?,?,?,?,?,?,?,?,?,?)";
		
		if(xequeLancamentoItemBUE(r) == 0){
			try {
				 con = Conexao.getConexao();
				 PreparedStatement ps = con.prepareStatement(sql);
				 ps.setInt(1, r.getFk_produto_requi_interna());
				 ps.setInt(2, r.getQtd_requi_processada());
				 ps.setDate(3, new java.sql.Date (r.getCaducidade_item().getTimeInMillis()));
				 ps.setString(4, r.getNum_lote());
				 ps.setInt(5, r.getFk_local_armaze_requi_receb_farmacia_poritem());
				 ps.setInt(6, r.getFk_ala_requi_receb_farmacia_poritem());
				 ps.setInt(7, r.getFk_montra_requi_receb_farmacia_poritem());
				 ps.setInt(8, r.getFk_prateleira_requi_receb_farmacia_poritem());
				 ps.setInt(9, r.getFk_funcionario());
				 ps.setDate(10, ft.data_registo());
				 ps.setString(11, ft.horaAtual());
				 ps.execute();
				 ps.close();
				 con.close();  
			} catch (Exception e) {
				e.printStackTrace();
			}
		} 
		else
		{
			actualizaLancamentoItemBUE(r);
		}
	}
	
	private int xequeLancamentoItemBUE(RequisicaoInterna r) {
		String sql = "SELECT id_produto_emestoque_armazem  FROM tblprodutoemestoquebancodeurgencia WHERE FK_id_produto = ? AND numero_dolote_sere = ?  ";
		int ultimoCod = 0;
		try {
			 con = Conexao.getConexao();
			 PreparedStatement ps = con.prepareStatement(sql);
			 ps.setInt(1, r.getFk_produto_requi_interna());
			 ps.setString(2, r.getNum_lote() );
			 ResultSet rs = ps.executeQuery();
			 if (rs.next()) {
					ultimoCod = rs.getInt(1);
				}
			ps.close();
			con.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return ultimoCod;
	}

	private void actualizaLancamentoItemBUE(RequisicaoInterna r) {
		String sql = "UPDATE tblprodutoemestoque SET quantidade_produto = ?, FK_local = ?,FK_alabloco = ?,FK_montra = ?,FK_prartileira  = ? "
				+ " WHERE  numero_dolote_sere = ? AND FK_id_produto = ? ";
		try {
			 int qtd_actual = qtdRecebimentoItem(r);
			 con = Conexao.getConexao();
			 PreparedStatement ps = con.prepareStatement(sql);
			 ps.setInt(1, r.getQtd_requi_processada()+qtd_actual);
			 ps.setInt(2, r.getFk_local_armaze_requi_receb_farmacia_poritem());
			 ps.setInt(3, r.getFk_ala_requi_receb_farmacia_poritem());
			 ps.setInt(4, r.getFk_montra_requi_receb_farmacia_poritem());
			 ps.setInt(5, r.getFk_prateleira_requi_receb_farmacia_poritem());
			 ps.setString(6, r.getNum_lote());
			 ps.setInt(7, r.getFk_produto_requi_interna());
			 ps.execute();
			 ps.close();
			 con.close();  
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	@Override
	public void lancarRecebidoItemUTI(RequisicaoInterna r) {
		String sql = "INSERT INTO tblprodutoemestoque_uti (FK_id_produto,quantidade_produto_uti,data_decaducidade_uti,numero_dolote_sere_uti,FK_local,FK_alabloco,FK_montra,FK_prartileira,FK_funcionario,data_dentrada_doestoque_uti,hora_dentrada_doestoque_uti) "
				+ " VALUES (?,?,?,?,?,?,?,?,?,?,?)";
		
		if(xequeLancamentoUTI(r) == 0){
			try {
				 con = Conexao.getConexao();
				 PreparedStatement ps = con.prepareStatement(sql);
				 ps.setInt(1, r.getFk_produto_requi_interna());
				 ps.setInt(2, r.getQtd_requi_processada());
				 ps.setDate(3, new java.sql.Date (r.getCaducidade_item().getTimeInMillis()));
				 ps.setString(4, r.getNum_lote());
				 ps.setInt(5, r.getFk_local_armaze_requi_receb_farmacia_poritem());
				 ps.setInt(6, r.getFk_ala_requi_receb_farmacia_poritem());
				 ps.setInt(7, r.getFk_montra_requi_receb_farmacia_poritem());
				 ps.setInt(8, r.getFk_prateleira_requi_receb_farmacia_poritem());
				 ps.setInt(9, r.getFk_funcionario());
				 ps.setDate(10, ft.data_registo());
				 ps.setString(11, ft.horaAtual());
				 ps.execute();
				 ps.close();
				 con.close();  
			} catch (Exception e) {
				e.printStackTrace();
			}
		} 
		else
		{
			actualizaLancamentoItemUTI(r);
		}
	}

	private void actualizaLancamentoItemUTI(RequisicaoInterna r) {
		String sql = "UPDATE tblprodutoemestoque_uti SET quantidade_produto_uti = ?, FK_local = ?,FK_alabloco = ?,FK_montra = ?,FK_prartileira  = ? "
				+ " WHERE  numero_dolote_sere_uti = ? AND FK_id_produto = ? ";
		try {
			 int qtd_actual = qtdRecebimentoItem(r);
			 con = Conexao.getConexao();
			 PreparedStatement ps = con.prepareStatement(sql);
			 ps.setInt(1, r.getQtd_requi_processada()+qtd_actual);
			 ps.setInt(2, r.getFk_local_armaze_requi_receb_farmacia_poritem());
			 ps.setInt(3, r.getFk_ala_requi_receb_farmacia_poritem());
			 ps.setInt(4, r.getFk_montra_requi_receb_farmacia_poritem());
			 ps.setInt(5, r.getFk_prateleira_requi_receb_farmacia_poritem());
			 ps.setString(6, r.getNum_lote());
			 ps.setInt(7, r.getFk_produto_requi_interna());
			 ps.execute();
			 ps.close();
			 con.close();  
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	@Override
	public void lancarRecebidoItemBercario(RequisicaoInterna r) {
		String sql = "INSERT INTO tblprodutoemestoque_bercario (FK_id_produto,quantidade_produto,data_decaducidade,numero_dolote_sere,FK_local,FK_alabloco,FK_montra,FK_prartileira,FK_funcionario,data_dentrada_doestoque,hora_dentrada_doestoque) "
				+ " VALUES (?,?,?,?,?,?,?,?,?,?,?)";
		
		if(xequeLancamentoBercario(r) == 0){
			try {
				 con = Conexao.getConexao();
				 PreparedStatement ps = con.prepareStatement(sql);
				 ps.setInt(1, r.getFk_produto_requi_interna());
				 ps.setInt(2, r.getQtd_requi_processada());
				 ps.setDate(3, new java.sql.Date (r.getCaducidade_item().getTimeInMillis()));
				 ps.setString(4, r.getNum_lote());
				 ps.setInt(5, r.getFk_local_armaze_requi_receb_farmacia_poritem());
				 ps.setInt(6, r.getFk_ala_requi_receb_farmacia_poritem());
				 ps.setInt(7, r.getFk_montra_requi_receb_farmacia_poritem());
				 ps.setInt(8, r.getFk_prateleira_requi_receb_farmacia_poritem());
				 ps.setInt(9, r.getFk_funcionario());
				 ps.setDate(10, ft.data_registo());
				 ps.setString(11, ft.horaAtual());
				 ps.execute();
				 ps.close();
				 con.close();  
			} catch (Exception e) {
				e.printStackTrace();
			}
		} 
		else
		{
			actualizaLancamentoItemBercario(r);
		}
	}
	
	private int xequeLancamentoBercario(RequisicaoInterna r) {
		String sql = "SELECT id_produto_emestoque_armazem  FROM tblprodutoemestoque_bercario WHERE FK_id_produto = ? AND numero_dolote_sere = ?  ";
		int ultimoCod = 0;
		try {
			 con = Conexao.getConexao();
			 PreparedStatement ps = con.prepareStatement(sql);
			 ps.setInt(1, r.getFk_produto_requi_interna());
			 ps.setString(2, r.getNum_lote() );
			 ResultSet rs = ps.executeQuery();
			 if (rs.next()) {
					ultimoCod = rs.getInt(1);
				}
			ps.close();
			con.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return ultimoCod;
	}

	private void actualizaLancamentoItemBercario(RequisicaoInterna r) {
		String sql = "UPDATE tblprodutoemestoque_bercario SET quantidade_produto = ?, FK_local = ?,FK_alabloco = ?,FK_montra = ?,FK_prartileira  = ? "
				+ " WHERE  numero_dolote_sere = ? AND FK_id_produto = ? ";
		try {
			 int qtd_actual = qtdRecebimentoItem(r);
			 con = Conexao.getConexao();
			 PreparedStatement ps = con.prepareStatement(sql);
			 ps.setInt(1, r.getQtd_requi_processada()+qtd_actual);
			 ps.setInt(2, r.getFk_local_armaze_requi_receb_farmacia_poritem());
			 ps.setInt(3, r.getFk_ala_requi_receb_farmacia_poritem());
			 ps.setInt(4, r.getFk_montra_requi_receb_farmacia_poritem());
			 ps.setInt(5, r.getFk_prateleira_requi_receb_farmacia_poritem());
			 ps.setString(6, r.getNum_lote());
			 ps.setInt(7, r.getFk_produto_requi_interna());
			 ps.execute();
			 ps.close();
			 con.close();  
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	@Override
	public void lancarRecebidoItemLab(RequisicaoInterna r) {
		String sql = "INSERT INTO tblprodutoemestoquelaboratorio (FK_id_produto,quantidade_produto,data_decaducidade,numero_dolote_sere,FK_local,FK_alabloco,FK_montra,FK_prartileira,FK_funcionario,data_dentrada_doestoque,hora_dentrada_doestoque) "
				+ " VALUES (?,?,?,?,?,?,?,?,?,?,?)";
		
		if(xequeLancamentoLab(r) == 0){
			try {
				 con = Conexao.getConexao();
				 PreparedStatement ps = con.prepareStatement(sql);
				 ps.setInt(1, r.getFk_produto_requi_interna());
				 ps.setInt(2, r.getQtd_requi_processada());
				 ps.setDate(3, new java.sql.Date (r.getCaducidade_item().getTimeInMillis()));
				 ps.setString(4, r.getNum_lote());
				 ps.setInt(5, r.getFk_local_armaze_requi_receb_farmacia_poritem());
				 ps.setInt(6, r.getFk_ala_requi_receb_farmacia_poritem());
				 ps.setInt(7, r.getFk_montra_requi_receb_farmacia_poritem());
				 ps.setInt(8, r.getFk_prateleira_requi_receb_farmacia_poritem());
				 ps.setInt(9, r.getFk_funcionario());
				 ps.setDate(10, ft.data_registo());
				 ps.setString(11, ft.horaAtual());
				 ps.execute();
				 ps.close();
				 con.close();  
			} catch (Exception e) {
				e.printStackTrace();
			}
		} 
		else
		{
			actualizaLancamentoItemLab(r);
		}
	}

	private void actualizaLancamentoItemLab(RequisicaoInterna r) {
		String sql = "UPDATE tblprodutoemestoquelaboratorio SET quantidade_produto = ?, FK_local = ?,FK_alabloco = ?,FK_montra = ?,FK_prartileira  = ? "
				+ " WHERE  numero_dolote_sere = ? AND FK_id_produto = ? ";
		try {
			 int qtd_actual = qtdRecebimentoItem(r);
			 con = Conexao.getConexao();
			 PreparedStatement ps = con.prepareStatement(sql);
			 ps.setInt(1, r.getQtd_requi_processada()+qtd_actual);
			 ps.setInt(2, r.getFk_local_armaze_requi_receb_farmacia_poritem());
			 ps.setInt(3, r.getFk_ala_requi_receb_farmacia_poritem());
			 ps.setInt(4, r.getFk_montra_requi_receb_farmacia_poritem());
			 ps.setInt(5, r.getFk_prateleira_requi_receb_farmacia_poritem());
			 ps.setString(6, r.getNum_lote());
			 ps.setInt(7, r.getFk_produto_requi_interna());
			 ps.execute();
			 ps.close();
			 con.close();  
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	private int xequeLancamentoLab(RequisicaoInterna r) {
		String sql = "SELECT id_produto_emestoque_armazem  FROM tblprodutoemestoquelaboratorio WHERE FK_id_produto = ? AND numero_dolote_sere = ?  ";
		int ultimoCod = 0;
		try {
			 con = Conexao.getConexao();
			 PreparedStatement ps = con.prepareStatement(sql);
			 ps.setInt(1, r.getFk_produto_requi_interna());
			 ps.setString(2, r.getNum_lote() );
			 ResultSet rs = ps.executeQuery();
			 if (rs.next()) {
					ultimoCod = rs.getInt(1);
				}
			ps.close();
			con.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return ultimoCod;
	}

	private int xequeLancamentoVac(RequisicaoInterna r) {
		String sql = "SELECT id_produto_emestoque_armazem  FROM tblprodutoemestoquevacinas WHERE FK_id_produto = ? AND numero_dolote_sere = ?  ";
		int ultimoCod = 0;
		try {
			 con = Conexao.getConexao();
			 PreparedStatement ps = con.prepareStatement(sql);
			 ps.setInt(1, r.getFk_produto_requi_interna());
			 ps.setString(2, r.getNum_lote() );
			 ResultSet rs = ps.executeQuery();
			 if (rs.next()) {
					ultimoCod = rs.getInt(1);
				}
			ps.close();
			con.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return ultimoCod;
	}
	@Override
	public int xequeProdutoStockLab(int prd, int codreq) {
		String sql = "SELECT FK_id_produto from tblprodutoemestoquelaboratorio where FK_id_produto = ? and numero_dolote_sere = ? and quantidade_produto > 0 ";
		RequisicaoInterna r = buscarItemLoteCaducidade(codreq,prd);
		try {
		 
				 con = Conexao.getConexao();
				 PreparedStatement ps = con.prepareStatement(sql);
				 ps.setInt(1, prd);
				 ps.setString(2, r.getNum_lote());
				 
				 ResultSet rs = ps.executeQuery();
				 if(rs.next())
					 return 1;
			 
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		return 0;
	}
	
	@Override
	public int xequeProdutoStockVac(int prd, int codreq) {
		String sql = "SELECT FK_id_produto from tblprodutoemestoquevacinas where FK_id_produto = ? and numero_dolote_sere = ? and quantidade_produto > 0 ";
		RequisicaoInterna r = buscarItemLoteCaducidade(codreq,prd);
		try {
		 
				 con = Conexao.getConexao();
				 PreparedStatement ps = con.prepareStatement(sql);
				 ps.setInt(1, prd);
				 ps.setString(2, r.getNum_lote());
				 
				 ResultSet rs = ps.executeQuery();
				 if(rs.next())
					 return 1;
			 
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		return 0;
	}
	
	@Override
	public int xequeProdutoStockImag(int prd, int codreq) {
		String sql = "SELECT FK_id_produto from tblprodutoemestoqueimagiologia where FK_id_produto = ? and numero_dolote_sere = ? and quantidade_produto > 0 ";
		RequisicaoInterna r = buscarItemLoteCaducidade(codreq,prd);
		try {
		 
				 con = Conexao.getConexao();
				 PreparedStatement ps = con.prepareStatement(sql);
				 ps.setInt(1, prd);
				 ps.setString(2, r.getNum_lote());
				 
				 ResultSet rs = ps.executeQuery();
				 if(rs.next())
					 return 1;
			 
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		return 0;
	}

	@Override
	public void lancarRecebidoItemVacina(RequisicaoInterna r) {
		String sql = "INSERT INTO tblprodutoemestoquevacinas (FK_id_produto,quantidade_produto,data_decaducidade,numero_dolote_sere,FK_local,FK_alabloco,FK_montra,FK_prartileira,FK_funcionario,data_dentrada_doestoque,hora_dentrada_doestoque) "
				+ " VALUES (?,?,?,?,?,?,?,?,?,?,?)";
		
		if(xequeLancamentoVac(r) == 0){
			try {
				 con = Conexao.getConexao();
				 PreparedStatement ps = con.prepareStatement(sql);
				 ps.setInt(1, r.getFk_produto_requi_interna());
				 ps.setInt(2, r.getQtd_requi_processada());
				 ps.setDate(3, new java.sql.Date (r.getCaducidade_item().getTimeInMillis()));
				 ps.setString(4, r.getNum_lote());
				 ps.setInt(5, r.getFk_local_armaze_requi_receb_farmacia_poritem());
				 ps.setInt(6, r.getFk_ala_requi_receb_farmacia_poritem());
				 ps.setInt(7, r.getFk_montra_requi_receb_farmacia_poritem());
				 ps.setInt(8, r.getFk_prateleira_requi_receb_farmacia_poritem());
				 ps.setInt(9, r.getFk_funcionario());
				 ps.setDate(10, ft.data_registo());
				 ps.setString(11, ft.horaAtual());
				 ps.execute();
				 ps.close();
				 con.close();  
			} catch (Exception e) {
				e.printStackTrace();
			}
		} 
		else
		{
			actualizaLancamentoItemVacina(r);
		}
	}

	private void actualizaLancamentoItemVacina(RequisicaoInterna r) {
		String sql = "UPDATE tblprodutoemestoquevacinas SET quantidade_produto = ?, FK_local = ?,FK_alabloco = ?,FK_montra = ?,FK_prartileira  = ? "
				+ " WHERE  numero_dolote_sere = ? AND FK_id_produto = ? ";
		try {
			 int qtd_actual = qtdRecebimentoItem(r);
			 con = Conexao.getConexao();
			 PreparedStatement ps = con.prepareStatement(sql);
			 ps.setInt(1, r.getQtd_requi_processada()+qtd_actual);
			 ps.setInt(2, r.getFk_local_armaze_requi_receb_farmacia_poritem());
			 ps.setInt(3, r.getFk_ala_requi_receb_farmacia_poritem());
			 ps.setInt(4, r.getFk_montra_requi_receb_farmacia_poritem());
			 ps.setInt(5, r.getFk_prateleira_requi_receb_farmacia_poritem());
			 ps.setString(6, r.getNum_lote());
			 ps.setInt(7, r.getFk_produto_requi_interna());
			 ps.execute();
			 ps.close();
			 con.close();  
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	@Override
	public void lancarRecebidoItemIMA(RequisicaoInterna r) {
		// TODO Auto-generated method stub
		String sql = "INSERT INTO tblprodutoemestoqueimagiologia (FK_id_produto,quantidade_produto,data_decaducidade,numero_dolote_sere,FK_local,FK_alabloco,FK_montra,FK_prartileira,FK_funcionario,data_dentrada_doestoque,hora_dentrada_doestoque) "
				+ " VALUES (?,?,?,?,?,?,?,?,?,?,?)";
		
		if(xequeLancamentoImag(r) == 0){
			try {
				 con = Conexao.getConexao();
				 PreparedStatement ps = con.prepareStatement(sql);
				 ps.setInt(1, r.getFk_produto_requi_interna());
				 ps.setInt(2, r.getQtd_requi_processada());
				 ps.setDate(3, new java.sql.Date (r.getCaducidade_item().getTimeInMillis()));
				 ps.setString(4, r.getNum_lote());
				 ps.setInt(5, r.getFk_local_armaze_requi_receb_farmacia_poritem());
				 ps.setInt(6, r.getFk_ala_requi_receb_farmacia_poritem());
				 ps.setInt(7, r.getFk_montra_requi_receb_farmacia_poritem());
				 ps.setInt(8, r.getFk_prateleira_requi_receb_farmacia_poritem());
				 ps.setInt(9, r.getFk_funcionario());
				 ps.setDate(10, ft.data_registo());
				 ps.setString(11, ft.horaAtual());
				 ps.execute();
				 ps.close();
				 con.close();  
			} catch (Exception e) {
				e.printStackTrace();
			}
		} 
		else
		{
			actualizaLancamentoItemImagiologia(r);
		}
	}

	private int xequeLancamentoImag(RequisicaoInterna r) {
		String sql = "SELECT id_produto_emestoque_armazem  FROM tblprodutoemestoqueimagiologia WHERE FK_id_produto = ? AND numero_dolote_sere = ?  ";
		int ultimoCod = 0;
		try {
			 con = Conexao.getConexao();
			 PreparedStatement ps = con.prepareStatement(sql);
			 ps.setInt(1, r.getFk_produto_requi_interna());
			 ps.setString(2, r.getNum_lote() );
			 ResultSet rs = ps.executeQuery();
			 if (rs.next()) {
					ultimoCod = rs.getInt(1);
				}
			ps.close();
			con.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return ultimoCod;
	}

	private void actualizaLancamentoItemImagiologia(RequisicaoInterna r) {
		String sql = "UPDATE tblprodutoemestoqueimagiologia SET quantidade_produto = ?, FK_local = ?,FK_alabloco = ?,FK_montra = ?,FK_prartileira  = ? "
				+ " WHERE  numero_dolote_sere = ? AND FK_id_produto = ? ";
		try {
			 int qtd_actual = qtdRecebimentoItem(r);
			 con = Conexao.getConexao();
			 PreparedStatement ps = con.prepareStatement(sql);
			 ps.setInt(1, r.getQtd_requi_processada()+qtd_actual);
			 ps.setInt(2, r.getFk_local_armaze_requi_receb_farmacia_poritem());
			 ps.setInt(3, r.getFk_ala_requi_receb_farmacia_poritem());
			 ps.setInt(4, r.getFk_montra_requi_receb_farmacia_poritem());
			 ps.setInt(5, r.getFk_prateleira_requi_receb_farmacia_poritem());
			 ps.setString(6, r.getNum_lote());
			 ps.setInt(7, r.getFk_produto_requi_interna());
			 ps.execute();
			 ps.close();
			 con.close();  
		} catch (Exception e) {
			e.printStackTrace();
		}
		}
	public int xequeProdutoStockBerc(int prd, int codreq) {
		String sql = "SELECT FK_id_produto from tblprodutoemestoque_bercario where FK_id_produto = ? and numero_dolote_sere = ? and quantidade_produto > 0 ";
		RequisicaoInterna r = buscarItemLoteCaducidade(codreq,prd);
		try {
		 
				 con = Conexao.getConexao();
				 PreparedStatement ps = con.prepareStatement(sql);
				 ps.setInt(1, prd);
				 ps.setString(2, r.getNum_lote());
				 
				 ResultSet rs = ps.executeQuery();
				 if(rs.next())
					 return 1;
			 
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		return 0;
	}
	
}
