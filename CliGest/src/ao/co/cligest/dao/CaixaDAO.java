package ao.co.cligest.dao;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

import ao.co.cligest.entidades.Ajuste;
import ao.co.cligest.entidades.Caixa;
import ao.co.cligest.entidades.Instituicao;

public class CaixaDAO {

	private Connection con = null;

	public double getFundoTrocos() {

		double fundo = 0;
		String sql = "Select * from tblreservaparatroco";
		try {
			con = Conexao.getConexao();
			PreparedStatement preparador = con.prepareStatement(sql);
			ResultSet rs = preparador.executeQuery();
			if (rs.next())
				fundo = rs.getDouble("reserva_para_trococol");
		} catch (Exception e) {
		} finally {
			try {
				con.close();
			} catch (SQLException ef) {
				System.out.println("Erro finalizar: " + ef);
			}
		}

		return fundo;

	}

	public void alteraValorFundo(Connection cx, double valor) {

		String sql = "Update tblreservaparatroco set reserva_para_trococol = ?";
		try {

			PreparedStatement ps = cx.prepareStatement(sql);
			ps.setDouble(1, valor);
			ps.execute();
			ps.close();
			System.out.println("Alterando valor do Troco");
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	public void salvaTrocoParaCaixa(Connection cx, Caixa c) {

		String sql = "Insert into tblestadodocaixa (valor_de_troco,estadodocaixa,data_deatribuicao_troco,hora_deatribuicao_troco,FK_entidade_caixa,FK_profissional) value (?,?,?,?,?,?)";
		try {

			PreparedStatement ps = cx.prepareStatement(sql);
			ps.setDouble(1, c.getValor_dtroco());
			ps.setInt(2, c.getEstado_docaixa());
			ps.setDate(3, new Formatando().data_registo());
			ps.setString(4, new Formatando().getHoraAtual());
			ps.setInt(5, c.getFk_entidade_caixa());
			ps.setInt(6, c.getFK_entidade());
			ps.execute();
			ps.close();
			System.out.println("Salvo o valor do Troco - Caixa");
		} catch (SQLException e) {
			e.printStackTrace();
		}

	}

	public void abrirCaixa(Caixa c) {

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
			sql = "Update tblestadodocaixa set estadodocaixa = 2 where id_estado_docaixa = ? and FK_entidade_caixa = ?";
			ps = con.prepareStatement(sql);
			ps.setInt(1, c.getId_estado_docaixa());
			ps.setInt(2, c.getFk_entidade_caixa());
			ps.execute();
			ps.close();
			System.out.println("Aberto o Caixa : " + c.getMaquina_dabertuara());
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			try {
				con.close();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}

	}

	public int fecharCaixa(Caixa c) {
		
		int idFecho = 0;
		String sql = "Insert into tblfechodecaixa (FK_entidade,FK_abertura_decaixa,total_facturado,data_dfecho,hora_dfecho) value (?,?,?,?,?)";
		try {
			con = Conexao.getConexao();
			PreparedStatement ps = con.prepareStatement(sql);
			ps.setInt(1, c.getFK_entidade());
			ps.setInt(2, c.getId_aberturadecaixa());
			ps.setDouble(3, c.getTotal_facturado());
			ps.setDate(4, new Formatando().data_registo());
			ps.setString(5, new Formatando().getHoraAtual());
			ps.execute();
			ResultSet rs = ps.executeQuery("SELECT LAST_INSERT_ID()");
		    if(rs.next())
		      idFecho = rs.getInt(1);
			// Atualizando Estado de Caixa, passando para 0 quando � fechado
			sql = "Update tblestadodocaixa set estadodocaixa = 0 where id_estado_docaixa = ? and FK_entidade_caixa = ?";
			ps = con.prepareStatement(sql);
			ps.setInt(1, c.getId_estado_docaixa());
			ps.setInt(2, c.getFk_entidade_caixa());
			ps.execute();
			ps.close();

		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			try {
				con.close();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		return idFecho;
	}

	public Caixa getTrocoDisponivel(int cod) {
		Caixa caixa = new Caixa();
		String sql = "Select * from tblestadodocaixa where FK_entidade_caixa = ? and data_deatribuicao_troco = curdate() and estadodocaixa = 1";
		try {
			con = Conexao.getConexao();
			PreparedStatement ps = con.prepareStatement(sql);
			ps.setInt(1, cod);
			ResultSet rs = ps.executeQuery();
			if (rs.next()) {

				caixa.setValor_dtroco(rs.getDouble("valor_de_troco"));
				caixa.setFk_entidade_caixa(rs.getInt("FK_entidade_caixa"));
				caixa.setId_estado_docaixa(rs.getInt("id_estado_docaixa"));
				caixa.setEstado_docaixa(rs.getInt("estadodocaixa"));
			}
			ps.close();

		} catch (SQLException e) {
			e.printStackTrace();
		} catch (Exception e) {
		} finally {
			try {
				con.close();
			} catch (SQLException ef) {
				System.out.println("Erro finalizar: " + ef);
			}
		}
		return caixa;
	}

	public Caixa getEstadoCaixa(int cod) {
		Caixa caixa = new Caixa();
		String sql = "Select * from vwestadodacaixa where FK_entidade_caixa = ? and data_deatribuicao_troco = curdate() and estadodocaixa = 2";
		try {
			con = Conexao.getConexao();
			PreparedStatement ps = con.prepareStatement(sql);
			ps.setInt(1, cod);
			ResultSet rs = ps.executeQuery();
			if (rs.next()) {
				caixa.setFk_entidade_caixa(rs.getInt("FK_entidade_caixa"));
				caixa.setId_estado_docaixa(rs.getInt("id_estado_docaixa"));
				caixa.setId_aberturadecaixa(rs.getInt("id_aberturadecaixa"));
				caixa.setMontante_dabertura(rs.getDouble("montante_dabertura"));
			}
			ps.close();

		} catch (SQLException e) {
			e.printStackTrace();
		} catch (Exception e) {
		} finally {
			try {
				con.close();
			} catch (SQLException ef) {
				System.out.println("Erro finalizar: " + ef);
			}
		}
		return caixa;
	}
	
	public int getFluxoCaixaNum() {
		Caixa caixa = new Caixa();
		String sql = "Select count(*) total from tblfluxodecaixa ";
		try {
			con = Conexao.getConexao();
			PreparedStatement ps = con.prepareStatement(sql);
			ResultSet rs = ps.executeQuery();
			if (rs.next()) {
				return rs.getInt("total") + 1;
			}
			ps.close();

		} catch (SQLException e) {
			e.printStackTrace();
		} catch (Exception e) {
		} finally {
			try {
				con.close();
			} catch (SQLException ef) {
				System.out.println("Erro finalizar: " + ef);
			}
		}
		return 0;
	}
	
	public List<Caixa> getFechosCaixa(int cod) {

		List<Caixa> lsCaixa = new ArrayList<Caixa>();
		String sql = "Select * from vwfechosajustados where FK_entidade = ? "
				   + "and FK_fechodecaixa not in (select FK_fecho_decaixa from  tblfluxodecaixa)";
		try {
			con = Conexao.getConexao();
			PreparedStatement ps = con.prepareStatement(sql);
			ps.setInt(1, cod);
			ResultSet rs = ps.executeQuery();
			while (rs.next()) {
				Caixa c = new Caixa();
				c.setId_fecho_decaixa(rs.getInt("FK_fechodecaixa"));
				c.setTotal_fluxo(rs.getDouble("total_facturado"));
				lsCaixa.add(c);
			}
			ps.close();

		} catch (SQLException e) {
			e.printStackTrace();
		} catch (Exception e) {
		} finally {
			try {
				con.close();
			} catch (SQLException ef) {
				System.out.println("Erro finalizar: " + ef);
			}
		}
		return lsCaixa;
	}
	
	public int novoNumeroFluxoCaixa(int funcionario,String num){
		
		String sql = "Insert into tblnumerodofluxodecaixa (FK_funcionario,data_dofluxo,hora_dofluxo,numero_dofluxo) value(?,?,?,?)";
		int numFluxo = 0;
		try{
			con = Conexao.getConexao();
			PreparedStatement ps = con.prepareStatement(sql);
			ps.setInt(1, funcionario);
			ps.setDate(2, new Formatando().data_registo());
			ps.setString(3, new Formatando().getHoraAtual());
			ps.setString(4, num);
			ps.execute();
			ResultSet rs = ps.executeQuery("SELECT LAST_INSERT_ID()");
			if(rs.next()){
				numFluxo = rs.getInt(1);
			}
		}catch (SQLException e) {
			e.printStackTrace();
		} catch (Exception e) {
		} finally {
			try {
				con.close();
			} catch (SQLException ef) {
				System.out.println("Erro finalizar: " + ef);
			}
		}
		return numFluxo;
	}
	
	public void novoFluxoCaixa(Caixa c){
		
		String sql = "Insert into tblfluxodecaixa (FK_numero_dofluxo,total_fluxo,FK_fecho_decaixa) value(?,?,?)";
		 
		try{
			con = Conexao.getConexao();
			PreparedStatement ps = con.prepareStatement(sql);
			ps.setInt(1, c.getId_numero_dofluxo());
			ps.setDouble(2, c.getTotal_fluxo());
			ps.setInt(3, c.getId_fecho_decaixa());		 
			ps.execute();
			 
		}catch (SQLException e) {
			e.printStackTrace();
		} catch (Exception e) {
		} finally {
			try {
				con.close();
			} catch (SQLException ef) {
				System.out.println("Erro finalizar: " + ef);
			}
		}
		 
	}

	public boolean XequeCaixa(int cod) {

		String sql = "Select * from vwaberturadecaixa where FK_entidade_caixa = ? and data_dabertura = curdate() and estadodocaixa = 2";
		try {
			con = Conexao.getConexao();
			PreparedStatement ps = con.prepareStatement(sql);
			ps.setInt(1, cod);
			ResultSet rs = ps.executeQuery();
			if (rs.next())
				return true;

			ps.close();

		} catch (SQLException e) {
			e.printStackTrace();
		} catch (Exception e) {
		} finally {
			try {
				con.close();
			} catch (SQLException ef) {
				System.out.println("Erro finalizar: " + ef);
			}
		}
		return false;
	}

	public boolean getXequeFinalizado(int cod) {

		String sql = "Select * from tblhistoricodeajuste where FK_fechodecaixa = ? ";
		try {
			con = Conexao.getConexao();
			PreparedStatement ps = con.prepareStatement(sql);
			ps.setInt(1, cod);
			ResultSet rs = ps.executeQuery();
			if (rs.next())
				return false;

			ps.close();

		} catch (SQLException e) {
			e.printStackTrace();
		} catch (Exception e) {
		} finally {
			try {
				con.close();
			} catch (SQLException ef) {
				System.out.println("Erro finalizar: " + ef);
			}
		}
		return true;
	}

	
	
	
	
	public String XequeAjusteDia(){
		String sql = "select * from tblfechodecaixa where data_dfecho = curdate() and id_fecho_decaixa in(select fk_fecho_decaixa from tblfluxodecaixa)";
		try {
			con = Conexao.getConexao();;
			PreparedStatement ps = con.prepareStatement(sql);
			ResultSet rs = ps.executeQuery();
			if(rs.next())
				return "disabled";
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		return "";
	}
	
	public boolean habilitaBotao() {
		if(XeckCaixaAberto() == 0)
		{
			if(XequeTodasCaixaAjustadas() == 0){
				return true;
			}
		}
		return false;
	}
	
	public int XeckCaixaAberto(){
		int estado = 0;
		String sql = "Select conexao_arede from vwfolhadecaixa";
		try {
			con = Conexao.getConexao();
			PreparedStatement ps = con.prepareStatement(sql);
			ResultSet rs = ps.executeQuery();
			String tmpEstado= "";
			
			while (rs.next()) {
				tmpEstado += rs.getString("conexao_arede") + ";";	
			}
			String arrEstados[] = tmpEstado.split(";");	
			
			for(int i = 0; i < arrEstados.length; i++){
				if(arrEstados[i].equals("1"))
					estado = 1;
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return estado;
	}
	
	public int  XequeTodasCaixaAjustadas() {
		String sql = "select * from tblfechodecaixa where id_fecho_decaixa not in(Select fk_fechodecaixa from tblhistoricodeajuste)";
		try {
			con = Conexao.getConexao();;
			PreparedStatement ps = con.prepareStatement(sql);
			ResultSet rs = ps.executeQuery();
			if(rs.next())
				return 1;
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		return 0;
	}
	
	public int getCaixaFechado() {
		int rsss = 0;
		String sql = "Select conexao_arede from vwfolhadecaixa ";
		try {
			con = Conexao.getConexao();
			PreparedStatement ps = con.prepareStatement(sql);
			String rss = ""; 
			ResultSet rs = ps.executeQuery();
			while (rs.next())
			{
				rss += rs.getString("conexao_arede") + ";";
			}
			String arr[] = rss.split(";");	
			for(int i = 0; i < arr.length; i++){
				if(arr[i].equals("1"))
					rsss = 1;
			}
			ps.close();
			con.close();

		} catch (SQLException e) {
			e.printStackTrace();
		} catch (Exception e) {
		}  
		return rsss;
	}
	
	public Caixa XequeFechaCaixa(String maq) {
		Caixa caixa = null;
		String sql = "Select * from vwaberturadecaixa where  data_dabertura < curdate() and estadodocaixa = 2 and maquina_dabertuara Like ?";
		try {
			con = Conexao.getConexao();
			PreparedStatement ps = con.prepareStatement(sql);
			ps.setString(1, maq);
			ResultSet rs = ps.executeQuery();
			if (rs.next()) {
				caixa = new Caixa();
				caixa.setFk_entidade_caixa(rs.getInt("FK_entidade_caixa"));
				caixa.setFK_entidade(rs.getInt("FK_entidade_caixa"));
				caixa.setMontante_dabertura(rs.getDouble("montante_dabertura"));
				caixa.setId_estado_docaixa(rs.getInt("id_estado_docaixa"));
				caixa.setEstado(rs.getInt("estadodocaixa"));
				caixa.setId_aberturadecaixa(rs.getInt("id_aberturadecaixa"));
				caixa.setTotal_facturado(0);
			}

			ps.close();

		} catch (SQLException e) {
			e.printStackTrace();
		} catch (Exception e) {
		} finally {
			try {
				con.close();
			} catch (SQLException ef) {
				System.out.println("Erro finalizar: " + ef);
			}
		}
		return caixa;
	}

	public List<Caixa> XequeFechasCaixas() {
		List<Caixa> caixasAbertas = new ArrayList<Caixa>();
		String sql = "Select * from vwaberturadecaixa where  data_dabertura < curdate() and estadodocaixa = 2 ";
		try {
			con = Conexao.getConexao();
			PreparedStatement ps = con.prepareStatement(sql);

			ResultSet rs = ps.executeQuery();
			while (rs.next()) {
				Caixa caixa = new Caixa();
				caixa.setFk_entidade_caixa(rs.getInt("FK_entidade_caixa"));
				caixa.setFK_entidade(rs.getInt("FK_entidade_caixa"));
				caixa.setMontante_dabertura(rs.getDouble("montante_dabertura"));
				caixa.setId_estado_docaixa(rs.getInt("id_estado_docaixa"));
				caixa.setEstado(rs.getInt("estadodocaixa"));
				caixa.setId_aberturadecaixa(rs.getInt("id_aberturadecaixa"));
				caixa.setTotal_facturado(0);
				caixasAbertas.add(caixa);
			}

			ps.close();

		} catch (SQLException e) {
			e.printStackTrace();
		} catch (Exception e) {
		} finally {
			try {
				con.close();
			} catch (SQLException ef) {
				System.out.println("Erro finalizar: " + ef);
			}
		}
		return caixasAbertas;
	}

	public List<Caixa> getCaixasGeral() {

		List<Caixa> lstCaixa = new ArrayList<Caixa>();
		String sql = "Select * from vwfolhadecaixa";
		try {
			con = Conexao.getConexao();
			PreparedStatement ps = con.prepareStatement(sql);
			ResultSet rs = ps.executeQuery();
			while (rs.next()) {
				Caixa caixa = new Caixa();
				caixa.setId_fecho_decaixa(rs.getInt("id_fecho_decaixa"));
				caixa.setNome(rs.getString("n_c_funcionario"));
				caixa.setMaquina_dabertuara(rs.getString("maquina_dabertuara"));
				caixa.setTotal_facturado(rs.getDouble("total"));
				caixa.setNum_fecho(rs.getInt("fecho_decaixa"));
				caixa.setNum_movimentos(rs.getInt("totalmovimento"));
				caixa.setFK_entidade(rs.getInt("FK_entidade"));
				caixa.setStatus(rs.getInt("conexao_arede"));
				lstCaixa.add(caixa);
			}
			ps.close();

		} catch (SQLException e) {
			e.printStackTrace();
		} catch (Exception e) {
		} finally {
			try {
				con.close();
			} catch (SQLException ef) {
				System.out.println("Erro finalizar: " + ef);
			}
		}
		return lstCaixa;
	}

	public String getCaixasGeralUNome(int cod) {

		String caixa = "";
		String sql = "Select * from vwfolhadecaixa where FK_entidade = ?";
		try {
			con = Conexao.getConexao();
			PreparedStatement ps = con.prepareStatement(sql);
			ps.setInt(1, cod);
			ResultSet rs = ps.executeQuery();
			if (rs.next()) {
				caixa = rs.getString("n_c_funcionario");
			}
			ps.close();

		} catch (SQLException e) {
			e.printStackTrace();
		} catch (Exception e) {
		} finally {
			try {
				con.close();
			} catch (SQLException ef) {
				System.out.println("Erro finalizar: " + ef);
			}
		}
		return caixa;
	}

	public Caixa getCaixasMovimentosTop(int cod1, int cod2) {
		Caixa caixa = new Caixa();
		String sql = "SELECT fc.FK_abertura_decaixa AS FK_abertura_decaixa, fc.id_fecho_decaixa, fs.n_c_Funcionario, fc.fk_entidade As fk_entidade, ac.maquina_Dabertuara As maquina_dabertuara, ac.Data_Dabertura  As Data_Dabertura, fc.data_dfecho As data_dfecho, sum(ffc.f_c_t) AS Valor_Dosistema, fc.total_facturado FROM tblfechodecaixa fc  JOIN vwfacturadasfolhasdecaixas ffc ON ffc.FK_abertura_decaixa = fc.FK_abertura_decaixa Join vwfuncionariosimples fs On fs.fk_Entidade = fc.fk_entidade Join tblaberturadecaixa ac On ac.Id_Aberturadecaixa   = fc.FK_abertura_decaixa WHERE fc.id_fecho_decaixa  = ? AND fc.FK_abertura_decaixa = ?";
		try {
			con = Conexao.getConexao();
			PreparedStatement ps = con.prepareStatement(sql);
			ps.setInt(1, cod2);
			ps.setInt(2, cod1);
			ResultSet rs = ps.executeQuery();
			if (rs.next()) {

				Calendar dia = Calendar.getInstance();
				dia.setTime(rs.getDate("data_dfecho"));
				caixa.setData_dfecho(dia);
				dia.setTime(rs.getDate("data_dabertura"));
				caixa.setData_dabertura(dia);
				caixa.setTotal_sistema(rs.getDouble("valor_dosistema"));
				caixa.setTotal_facturado(rs.getDouble("total_facturado"));
				caixa.setNome(rs.getString("n_c_funcionario"));
				caixa.setMaquina_dabertuara(rs.getString("maquina_dabertuara"));
				caixa.setId_aberturadecaixa(rs.getInt("FK_abertura_decaixa"));
				caixa.setId_fecho_decaixa(rs.getInt("id_fecho_decaixa"));
				caixa.setTotal_facturado(rs.getDouble("total_facturado"));
				caixa.setTotal_sistema(rs.getDouble("valor_dosistema"));
				//System.out.println(rs.getString("numero_factura")); 
			}
			ps.close();

		} catch (SQLException e) {
			e.printStackTrace();
		} catch (Exception e) {
		} finally {
			try {
				con.close();
			} catch (SQLException ef) {
				System.out.println("Erro finalizar: " + ef);
			}
		}
		return caixa;
	}

	public Caixa getCaixasMovimentosBottom(int cod1, int cod2) {
		Caixa caixa = new Caixa();
		String sql = "SELECT (SUM(VTFI.total_consulta_i_idade) + SUM(VTFI.t_e_i_idade) + SUM(VTFI.t_s_g_i_p_idade) + SUM(VTFI.t_p_i_p_i)) AS isencao_idade, SUM(VTFI.total_consulta_i_normal) + SUM(VTFI.t_e_i_normal) + SUM(VTFI.t_s_g_i_normal) + SUM(VTFI.t_p_i_normal) AS isencao_normal, VTFI.FK_FUNCIONARIO, VTFI.fk_abertura_dcaixa, IFNULL(TD.TOTAL_DEVOLVIDO,0) AS devolucoes FROM vwvalortotalfolhaindividual VTFI LEFT JOIN vwtotaldevolvido TD ON TD.FK_abertura_dcaixa = VTFI.FK_ABERTURA_DCAIXA WHERE VTFI.fk_abertura_dcaixa = ? and VTFI.FK_FUNCIONARIO = ?";
		try {
			con = Conexao.getConexao();
			PreparedStatement ps = con.prepareStatement(sql);
			ps.setInt(1, cod1);
			ps.setInt(2, cod2);
			ResultSet rs = ps.executeQuery();
			if (rs.next()) {
				caixa.setTotal_isencaoIdade(rs.getDouble("isencao_idade"));
				caixa.setTotal_isencao(rs.getDouble("isencao_normal"));
				caixa.setTotalDevolvido(rs.getDouble("devolucoes"));
				caixa.setTotalReduzido(rs.getDouble("isencao_idade") + rs.getDouble("isencao_normal") + rs.getDouble("devolucoes"));			
				//Double totalDevolvido = rs.getDouble("isencao_idade") + rs.getDouble("isencao_normal") + rs.getDouble("devolucoes");
			}
			ps.close();

		} catch (SQLException e) {
			e.printStackTrace();
		} catch (Exception e) {
		} finally {
			try {
				con.close();
			} catch (SQLException ef) {
				System.out.println("Erro finalizar: " + ef);
			}
		}
		return caixa;
	}

	public List<Caixa> getFechosCaixasGeralUnd(int cod) {
		List<Caixa> lstCaixa = new ArrayList<Caixa>();
		String sql = "SELECT fc.id_fecho_decaixa, fs.n_c_funcionario, fc.FK_entidade, fc.FK_abertura_decaixa, fc.total_facturado,fc.data_dfecho,fc.hora_dfecho,f.id_factura, total_reg_sistema as movimento_dosistema,tfpfc.nf as num_facturas FROM  tblfechodecaixa fc join vwfuncionariosimples fs on fs.FK_entidade = fc.FK_entidade join  tblfactura f on f.FK_abertura_dcaixa = fc.FK_abertura_decaixa join vwtotaldecadafacturadosistemafinal tcfsf on tcfsf.id_factura = f.id_factura left join  tblfacturartratamento ft on ft.fk_factura = f.id_factura join vwtotalfacturaparafolhadecaixa tfpfc on tfpfc.fk_abertura_dcaixa = fc.fk_abertura_decaixa where fc.fk_entidade = ? group by id_fecho_decaixa order by fc.id_fecho_decaixa desc";
		try {
			con = Conexao.getConexao();
			PreparedStatement ps = con.prepareStatement(sql);
			ps.setInt(1, cod);
			ResultSet rs = ps.executeQuery();
			while (rs.next()) {
				Caixa caixa = new Caixa();
				Calendar dia = Calendar.getInstance();
				dia.setTime(rs.getDate("data_dfecho"));
				caixa.setData_dfecho(dia);
				caixa.setFK_entidade(rs.getInt("FK_entidade"));
				caixa.setMontante_dabertura(0);
				caixa.setNum_fecho(rs.getInt("num_facturas"));
				caixa.setTotal_facturado(rs.getDouble("total_facturado"));
				caixa.setTotal_sistema(rs.getDouble("movimento_dosistema"));
				caixa.setId_aberturadecaixa(rs.getInt("FK_abertura_decaixa"));
				caixa.setId_fecho_decaixa(rs.getInt("id_fecho_decaixa"));
				// caixa.setNum_doc(rs.getString("numero_factura"));
				caixa.setId(rs.getInt("id_factura"));
				lstCaixa.add(caixa);
			}
			ps.close();

		} catch (SQLException e) {
			e.printStackTrace();
		} catch (Exception e) {
		} finally {
			try {
				con.close();
			} catch (SQLException ef) {
				System.out.println("Erro finalizar: " + ef);
			}
		}
		return lstCaixa;
	}

	public List<Caixa> getCaixasGeralUnd(int cod, int cod2) {
		List<Caixa> lstCaixa = new ArrayList<Caixa>();
		String sql = "select f.id_factura, f.numero_factura, ac.montante_dabertura, fc.FK_entidade, fc.total_facturado, fc.fk_abertura_decaixa, mfnf.numero_demovimento, mfs.movimento_dosistema, fc.id_fecho_decaixa, fc.data_dfecho, tsf.tipode_servico_facturado, mp.metodo_depagameto from tblfechodecaixa fc join tblfactura f on f.FK_abertura_dcaixa = fc.FK_abertura_decaixa join tblaberturadecaixa ac on ac.id_aberturadecaixa = fc.FK_abertura_decaixa left join vwtotaldemovimento tm on tm.fk_entidade = fc.FK_entidade left join tblfacturaconsulta fct on fct.FK_factura = f.id_factura join vwmovimentofinalnafactura mfnf on mfnf.id_factura = f.id_factura left join vwfacturasdecadafechosfinal mfs on mfs.id_factura = f.id_factura join tbltipodeservicofacturado tsf on tsf.id_tip_serv_facturado = f.FK_tipo_deservico join tblmetododepagameto mp on mp.id_metodo_depagameto = f.FK_metodo_de_pagameto where fc.FK_entidade = ? and fc.id_fecho_decaixa = ? group by f.id_factura";
		try {
			con = Conexao.getConexao();
			PreparedStatement ps = con.prepareStatement(sql);
			ps.setInt(1, cod);
			ps.setInt(2, cod2);
			ResultSet rs = ps.executeQuery();
			while (rs.next()) {
				Caixa caixa = new Caixa();
				Calendar dia = Calendar.getInstance();
				dia.setTime(rs.getDate("data_dfecho"));
				caixa.setData_dfecho(dia);
				caixa.setFK_entidade(rs.getInt("FK_entidade"));
				caixa.setMontante_dabertura(rs.getDouble("montante_dabertura"));
				caixa.setNum_fecho(rs.getInt("numero_demovimento"));
				caixa.setTotal_facturado(rs.getDouble("total_facturado"));
				caixa.setTotal_sistema(rs.getDouble("movimento_dosistema"));
				caixa.setId_aberturadecaixa(rs.getInt("FK_abertura_decaixa"));
				caixa.setId_fecho_decaixa(rs.getInt("id_fecho_decaixa"));
				caixa.setNum_doc(rs.getString("numero_factura"));
				caixa.setId(rs.getInt("id_factura"));
				caixa.setTipo_da_venda(rs.getString("tipode_servico_facturado"));
				caixa.setValor_formatado(rs.getString("metodo_depagameto"));
				lstCaixa.add(caixa);
			}
			ps.close();

		} catch (SQLException e) {
			e.printStackTrace();
		} catch (Exception e) {
		} finally {
			try {
				con.close();
			} catch (SQLException ef) {
				System.out.println("Erro finalizar: " + ef);
			}
		}
		return lstCaixa;
	}
	
	public List<Caixa> getCaixasGeralUnd(int cod, int cod2, Date dat1, Date Dat2) {
		List<Caixa> lstCaixa = new ArrayList<Caixa>();
		String sql = "select  f.id_factura,   f.numero_factura,    ac.montante_dabertura,  fc.FK_entidade,  fc.total_facturado,    fc.fk_abertura_decaixa,    mfnf.numero_demovimento,    mfs.movimento_dosistema,  fc.id_fecho_decaixa,  fc.data_dfecho "
				+ "from tblfechodecaixa fc join tblfactura f on f.FK_abertura_dcaixa = fc.FK_abertura_decaixa "
				+ "join tblaberturadecaixa ac on ac.id_aberturadecaixa = fc.FK_abertura_decaixa "
				+ "left join vwtotaldemovimento tm on tm.fk_entidade = fc.FK_entidade "
				+ "join tblfacturaexame fe on fe.fk_factura = f.id_factura "
				+ "left join tblfacturaconsulta fct on fct.FK_factura = f.id_factura "
				+ "join vwmovimentofinalnafactura mfnf on mfnf.id_factura = f.id_factura "
				+ "left join vwmovimentofinaldosistema mfs on mfs.id_factura = f.id_factura "
				+ "where fc.FK_entidade = ? and fc.id_fecho_decaixa = ? and fc.data_dfecho between ? and ? group by f.id_factura";
		try {
			con = Conexao.getConexao();
			PreparedStatement ps = con.prepareStatement(sql);
			ps.setInt(1, cod);
			ps.setInt(2, cod2);
			ps.setDate(3, dat1);
			ps.setDate(4, Dat2);
			ResultSet rs = ps.executeQuery();
			while (rs.next()) {
				Caixa caixa = new Caixa();
				Calendar dia = Calendar.getInstance();
				dia.setTime(rs.getDate("data_dfecho"));
				caixa.setData_dfecho(dia);
				caixa.setFK_entidade(rs.getInt("FK_entidade"));
				caixa.setMontante_dabertura(rs.getDouble("montante_dabertura"));
				caixa.setNum_fecho(rs.getInt("numero_demovimento"));
				caixa.setTotal_facturado(rs.getDouble("total_facturado"));
				caixa.setTotal_sistema(rs.getDouble("movimento_dosistema"));
				caixa.setId_aberturadecaixa(rs.getInt("FK_abertura_decaixa"));
				caixa.setId_fecho_decaixa(rs.getInt("id_fecho_decaixa"));
				caixa.setNum_doc(rs.getString("numero_factura"));
				caixa.setId(rs.getInt("id_factura"));
				
				lstCaixa.add(caixa);
			}
			ps.close();

		} catch (SQLException e) {
			e.printStackTrace();
		} catch (Exception e) {
		} finally {
			try {
				con.close();
			} catch (SQLException ef) {
				System.out.println("Erro finalizar: " + ef);
			}
		}
		return lstCaixa;
	}
	
	public List<Caixa> getCaixasGeralUnd(int cod, int cod2, Date dat1) {
		List<Caixa> lstCaixa = new ArrayList<Caixa>();
		String sql = "select  f.id_factura,   f.numero_factura,    ac.montante_dabertura,  fc.FK_entidade,  fc.total_facturado,    fc.fk_abertura_decaixa,    mfnf.numero_demovimento,    mfs.movimento_dosistema,  fc.id_fecho_decaixa,  fc.data_dfecho "
				+ "from tblfechodecaixa fc join tblfactura f on f.FK_abertura_dcaixa = fc.FK_abertura_decaixa "
				+ "join tblaberturadecaixa ac on ac.id_aberturadecaixa = fc.FK_abertura_decaixa "
				+ "left join vwtotaldemovimento tm on tm.fk_entidade = fc.FK_entidade "
				+ "join tblfacturaexame fe on fe.fk_factura = f.id_factura "
				+ "left join tblfacturaconsulta fct on fct.FK_factura = f.id_factura "
				+ "join vwmovimentofinalnafactura mfnf on mfnf.id_factura = f.id_factura "
				+ "left join vwmovimentofinaldosistema mfs on mfs.id_factura = f.id_factura "
				+ "where fc.FK_entidade = ? and fc.id_fecho_decaixa = ? and fc.data_dfecho between ? and curdate() group by f.id_factura";
		try {
			con = Conexao.getConexao();
			PreparedStatement ps = con.prepareStatement(sql);
			ps.setInt(1, cod);
			ps.setInt(2, cod2);
			ps.setDate(3, dat1);
			ResultSet rs = ps.executeQuery();
			while (rs.next()) {
				Caixa caixa = new Caixa();
				Calendar dia = Calendar.getInstance();
				dia.setTime(rs.getDate("data_dfecho"));
				caixa.setData_dfecho(dia);
				caixa.setFK_entidade(rs.getInt("FK_entidade"));
				caixa.setMontante_dabertura(rs.getDouble("montante_dabertura"));
				caixa.setNum_fecho(rs.getInt("numero_demovimento"));
				caixa.setTotal_facturado(rs.getDouble("total_facturado"));
				caixa.setTotal_sistema(rs.getDouble("movimento_dosistema"));
				caixa.setId_aberturadecaixa(rs.getInt("FK_abertura_decaixa"));
				caixa.setId_fecho_decaixa(rs.getInt("id_fecho_decaixa"));
				caixa.setNum_doc(rs.getString("numero_factura"));
				caixa.setId(rs.getInt("id_factura"));
				lstCaixa.add(caixa);
			}
			ps.close();

		} catch (SQLException e) {
			e.printStackTrace();
		} catch (Exception e) {
		} finally {
			try {
				con.close();
			} catch (SQLException ef) {
				System.out.println("Erro finalizar: " + ef);
			}
		}
		return lstCaixa;
	}

	public List<Caixa> getVendasCaixasGeralUnd(int cod, int cod2) {
		List<Caixa> lstCaixa = new ArrayList<Caixa>();
		String sql = "SELECT fc.id_fecho_decaixa, fc.fk_entidade, fc.FK_abertura_decaixa, f.id_factura, f.Data_da_facturacao, f.numero_factura, f.FK_metodo_de_pagameto, mp.metodo_depagameto, (vtfi.total_consulta + vtfi.total_consulta_multicaixa + vtfi.total_consulta_i_idade + vtfi.total_consulta_i_normal+ vtfi.total_exame+vtfi.t_e_m_caixa+vtfi.t_e_i_idade+vtfi.t_e_i_normal+vtfi.total_tratamento+vtfi.t_o_s_m_caixa+ vtfi.t_s_g_i_p_idade+ vtfi.t_s_g_i_normal+ vtfi.total_produto+ vtfi.t_p_m_caixa+ vtfi.t_p_i_p_i+ vtfi.t_p_i_normal) as movimento_dosistema, ifnull(qt.totalmovimento,'0') quatidade_total, tsf.tipode_servico_facturado	FROM tblfechodecaixa fc JOIN tblfactura f ON f.FK_abertura_dcaixa = fc.fk_abertura_decaixa JOIN tblmetododepagameto mp ON mp.id_metodo_depagameto = f.FK_metodo_de_pagameto	JOIN vwvalortotalfolhaindividual vtfi ON vtfi.id_factura = f.id_factura	LEFT JOIN vwtotaldemovimento qt ON qt.id_factura = f.id_factura	JOIN tbltipodeservicofacturado tsf ON tsf.id_tip_serv_facturado = f.FK_tipo_deservico	WHERE fc.id_fecho_decaixa = ? AND fc.fk_entidade = ? GROUP BY f.id_factura";
		try {
			con = Conexao.getConexao();
			PreparedStatement ps = con.prepareStatement(sql);
			ps.setInt(1, cod);
			ps.setInt(2, cod2);
			ResultSet rs = ps.executeQuery();
			while (rs.next()) {
				Caixa caixa = new Caixa();
				Calendar dia = Calendar.getInstance();
				dia.setTime(rs.getDate("Data_da_facturacao"));
				caixa.setData_dfecho(dia);
				caixa.setFK_entidade(rs.getInt("FK_entidade"));
				caixa.setId(rs.getInt("id_factura"));
				caixa.setId_fecho_decaixa(rs.getInt("id_fecho_decaixa"));
				caixa.setId_aberturadecaixa(rs.getInt("FK_abertura_decaixa"));
				caixa.setNum_doc(rs.getString("numero_factura"));
				caixa.setValor_formatado(rs.getString("metodo_depagameto"));
				caixa.setNum_fecho(rs.getInt("quatidade_total"));
				caixa.setTotal_sistema(rs.getDouble("movimento_dosistema"));
				caixa.setTipo_da_venda(rs.getString("tipode_servico_facturado"));

				lstCaixa.add(caixa);
			}
			ps.close();

		} catch (SQLException e) {
			e.printStackTrace();
		} catch (Exception e) {
		} finally {
			try {
				con.close();
			} catch (SQLException ef) {
				System.out.println("Erro finalizar: " + ef);
			}
		}
		return lstCaixa;
	}

	@SuppressWarnings("resource")
	public void finalizarCaixa(Ajuste a) 
	{

		String sql = "Insert into tblhistoricodeajuste (diferenca_novalor,FK_fechodecaixa,data_doajuste,hora_doajuste,obs_doajuste,fechado_pelosistema,FK_estatu_doajuste) value(?,?,?,?,?,?,?)";
		try {
			con = Conexao.getConexao();
			PreparedStatement ps = con.prepareStatement(sql);
			ps.setDouble(1, a.getDiferenca_novalor());
			ps.setInt(2, a.getId_fecho_decaixa());
			ps.setDate(3, new Formatando().data_registo());
			ps.setString(4, new Formatando().getHoraAtual());
			ps.setString(5, a.getComentario_ajs());
			ps.setDouble(6, a.getTotal_sistema());
			ps.setInt(7, 0);
			ps.execute();

			if (a.getTotal_facturado() > 0) 
			{
				sql = "Update tblfechodecaixa set total_facturado = ?,total_reg_sistema = ? where id_fecho_decaixa = ?";
				ps = con.prepareStatement(sql);
				ps.setDouble(1, a.getTotal_facturado());
				ps.setDouble(2, a.getTotal_sistema());
				ps.setInt(3, a.getId_fecho_decaixa());
				ps.execute();
			} 
			else 
			{
				sql = "Update tblfechodecaixa set total_reg_sistema = ? where id_fecho_decaixa = ?";
				ps = con.prepareStatement(sql);
				ps.setDouble(1, a.getTotal_sistema());
				ps.setInt(2, a.getId_fecho_decaixa());
				ps.execute();
			}

		} catch (SQLException er) {
			er.printStackTrace();
		}
	}

	public List<Ajuste> getCaixasDesempenhoGeral() {
		String sql = "Select * from vwhistoricoajuste order by data_dfecho desc";
		List<Ajuste> lsCaixaDG = new ArrayList<Ajuste>();
		try {
			con = Conexao.getConexao();
			PreparedStatement ps = con.prepareStatement(sql);
			ResultSet rs = ps.executeQuery();
			while (rs.next()) {
				Ajuste a = new Ajuste();
				a.setNome(rs.getString("funcionario"));
				a.setId_fecho_decaixa(rs.getInt("id_fecho_decaixa"));
				a.setDiferenca_novalor(rs.getDouble("diferenca"));
				a.setTotal_facturado(rs.getDouble("fecho_caixa"));
				a.setTotal_sistema(rs.getDouble("fecho_sistema"));
				Calendar dfecho = Calendar.getInstance();
				dfecho.setTime(rs.getDate("data_dfecho"));
				a.setData_dfecho(dfecho);
				lsCaixaDG.add(a);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return lsCaixaDG;
	}

	public List<Ajuste> getCaixasDesempenhoGeral(Date d1, Date d2) {
		String sql = "Select * from vwhistoricoajuste where data_dfecho between ? and ? order by data_dfecho desc";
		List<Ajuste> lsCaixaDG = new ArrayList<Ajuste>();
		try {
			con = Conexao.getConexao();
			PreparedStatement ps = con.prepareStatement(sql);
			ps.setDate(1, d1);
			ps.setDate(2, d2);
			ResultSet rs = ps.executeQuery();
			while (rs.next()) {
				Ajuste a = new Ajuste();
				a.setNome(rs.getString("funcionario"));
				a.setId_fecho_decaixa(rs.getInt("id_fecho_decaixa"));
				a.setDiferenca_novalor(rs.getDouble("diferenca"));
				a.setTotal_facturado(rs.getDouble("fecho_caixa"));
				a.setTotal_sistema(rs.getDouble("fecho_sistema"));
				Calendar dfecho = Calendar.getInstance();
				dfecho.setTime(rs.getDate("data_dfecho"));
				a.setData_dfecho(dfecho);
				lsCaixaDG.add(a);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return lsCaixaDG;
	}

	public List<Ajuste> getCaixasDesempenhoNormais() {
		String sql = "Select * from vwhistoricoajuste where diferenca = 0 order by data_dfecho desc";
		List<Ajuste> lsCaixaDG = new ArrayList<Ajuste>();
		try {
			con = Conexao.getConexao();
			PreparedStatement ps = con.prepareStatement(sql);
			ResultSet rs = ps.executeQuery();
			while (rs.next()) {
				Ajuste a = new Ajuste();
				a.setNome(rs.getString("funcionario"));
				a.setId_fecho_decaixa(rs.getInt("id_fecho_decaixa"));
				a.setDiferenca_novalor(rs.getDouble("diferenca"));
				a.setTotal_facturado(rs.getDouble("fecho_caixa"));
				a.setTotal_sistema(rs.getDouble("fecho_sistema"));
				Calendar dfecho = Calendar.getInstance();
				dfecho.setTime(rs.getDate("data_dfecho"));
				a.setData_dfecho(dfecho);
				lsCaixaDG.add(a);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return lsCaixaDG;
	}

	public List<Ajuste> getCaixasDesempenhoAnormais() {
		String sql = "Select * from vwhistoricoajuste where diferenca <> 0 order by data_dfecho desc";
		List<Ajuste> lsCaixaDG = new ArrayList<Ajuste>();
		try {
			con = Conexao.getConexao();
			PreparedStatement ps = con.prepareStatement(sql);
			ResultSet rs = ps.executeQuery();
			while (rs.next()) {
				Ajuste a = new Ajuste();
				a.setNome(rs.getString("funcionario"));
				a.setId_fecho_decaixa(rs.getInt("id_fecho_decaixa"));
				a.setDiferenca_novalor(rs.getDouble("diferenca"));
				a.setTotal_facturado(rs.getDouble("fecho_caixa"));
				a.setTotal_sistema(rs.getDouble("fecho_sistema"));
				Calendar dfecho = Calendar.getInstance();
				dfecho.setTime(rs.getDate("data_dfecho"));
				a.setData_dfecho(dfecho);
				lsCaixaDG.add(a);
			}
			ps.close();
			con.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return lsCaixaDG;
	}
	
	public List<Ajuste> getListaFluxosCaixasInd(int cod){
		
		String sql = "SELECT nflc.numero_dofluxo,fc.id_fecho_decaixa, fc.id_fecho_decaixa, flc.FK_numero_dofluxo, fc.FK_entidade, fc.FK_abertura_decaixa, fc.total_facturado, fc.total_reg_sistema, fc.data_dfecho, fc.hora_dfecho,  fs.n_c_funcionario,  ac.montante_dabertura,   count(f.id_factura) total_movimento "
				   + "FROM  tblfluxodecaixa flc "
				   + "JOIN  tblfechodecaixa fc ON fc.id_fecho_decaixa = flc.fk_fecho_decaixa "
				   + "JOIN tblnumerodofluxodecaixa nflc ON nflc.id_numero_dofluxo = flc.FK_numero_dofluxo "
				   + "JOIN vwfuncionariosimples fs ON fs.FK_entidade = nflc.FK_funcionario "
				   + "JOIN tblaberturadecaixa ac ON ac.id_aberturadecaixa = fc.FK_abertura_decaixa "
				   + "left JOIN tblfactura f on f.fk_abertura_dcaixa = ac.id_aberturadecaixa "
				   + "left JOIN  tblfacturaexame fe on fe.FK_factura = f.id_factura "
				   + "WHERE flc.FK_numero_dofluxo = ? "
				   + "group by fc.FK_abertura_decaixa ";
		List<Ajuste> lsfluxo = new ArrayList<Ajuste>();
		try{
			con = Conexao.getConexao();
			PreparedStatement ps = con.prepareStatement(sql);
			ps.setInt(1, cod);
			ResultSet rs = ps.executeQuery();
			while(rs.next()){
				Ajuste fluxo = new Ajuste();
				fluxo.setNumero_fechodecaixa(rs.getString("id_fecho_decaixa"));
				fluxo.setId_fluxo_decaixa(rs.getInt("FK_numero_dofluxo"));
				fluxo.setFK_entidade(rs.getInt("FK_entidade"));
				fluxo.setTotal_facturado(rs.getDouble("total_facturado"));
				fluxo.setTotal_sistema(rs.getDouble("total_reg_sistema"));
				fluxo.setMontante_dabertura(rs.getDouble("montante_dabertura"));
				fluxo.setNum_movimentos(rs.getInt("total_movimento"));
				fluxo.setHora_dfecho(rs.getString("hora_dfecho"));
				fluxo.setNome(rs.getString("n_c_funcionario"));
				Calendar data = Calendar.getInstance();
				data.setTime(rs.getDate("data_dfecho"));
				fluxo.setData_dfecho(data);
				fluxo.setNumero_dofluxo(rs.getString("numero_dofluxo"));
				lsfluxo.add(fluxo);
			}
			ps.close();
			con.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return lsfluxo;
	}
	
public List<Ajuste> getListaFluxosCaixasInd(int cod,Date dat1, Date dat2){
		
		String sql = "SELECT fc.id_fecho_decaixa, flc.FK_numero_dofluxo, fc.FK_entidade, fc.FK_abertura_decaixa, fc.total_facturado, fc.total_reg_sistema, fc.data_dfecho, fc.hora_dfecho,  fs.n_c_funcionario,  ac.montante_dabertura,   count(f.id_factura) total_movimento "
				   + "FROM  tblfluxodecaixa flc "
				   + "JOIN  tblfechodecaixa fc ON fc.id_fecho_decaixa = flc.fk_fecho_decaixa "
				   + "JOIN tblnumerodofluxodecaixa nflc ON nflc.id_numero_dofluxo = flc.FK_numero_dofluxo "
				   + "JOIN vwfuncionariosimples fs ON fs.FK_entidade = nflc.FK_funcionario "
				   + "JOIN tblaberturadecaixa ac ON ac.id_aberturadecaixa = fc.FK_abertura_decaixa "
				   + "JOIN tblfactura f on f.fk_abertura_dcaixa = ac.id_aberturadecaixa "
				   + "JOIN  tblfacturaexame fe on fe.FK_factura = f.id_factura "
				   + "WHERE flc.FK_numero_dofluxo = ? and fc.data_dfecho between ? and ?"
				   + "group by fc.FK_abertura_decaixa ";
		List<Ajuste> lsfluxo = new ArrayList<Ajuste>();
		try{
			con = Conexao.getConexao();
			PreparedStatement ps = con.prepareStatement(sql);
			ps.setInt(1, cod);
			ps.setDate(2, dat1);
			ps.setDate(3, dat2);
			ResultSet rs = ps.executeQuery();
			while(rs.next()){
				Ajuste fluxo = new Ajuste();
				fluxo.setNumero_fechodecaixa(rs.getString("id_fecho_decaixa"));
				fluxo.setId_fluxo_decaixa(rs.getInt("FK_numero_dofluxo"));
				fluxo.setFK_entidade(rs.getInt("FK_entidade"));
				fluxo.setTotal_facturado(rs.getDouble("total_facturado"));
				fluxo.setTotal_sistema(rs.getDouble("total_reg_sistema"));
				fluxo.setMontante_dabertura(rs.getDouble("montante_dabertura"));
				fluxo.setNum_movimentos(rs.getInt("total_movimento"));
				fluxo.setHora_dfecho(rs.getString("hora_dfecho"));
				fluxo.setNome(rs.getString("n_c_funcionario"));
				Calendar data = Calendar.getInstance();
				data.setTime(rs.getDate("data_dfecho"));
				fluxo.setData_dfecho(data);
				lsfluxo.add(fluxo);
			}
			ps.close();
			con.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return lsfluxo;
	}

public List<Ajuste> getListaFluxosCaixasInd(int cod,Date dat1){
	
	String sql = "SELECT  fc.id_fecho_decaixa, flc.FK_numero_dofluxo, fc.FK_entidade, fc.FK_abertura_decaixa, fc.total_facturado, fc.total_reg_sistema, fc.data_dfecho, fc.hora_dfecho,  fs.n_c_funcionario,  ac.montante_dabertura,   count(f.id_factura) total_movimento "
			   + "FROM  tblfluxodecaixa flc "
			   + "JOIN  tblfechodecaixa fc ON fc.id_fecho_decaixa = flc.fk_fecho_decaixa "
			   + "JOIN tblnumerodofluxodecaixa nflc ON nflc.id_numero_dofluxo = flc.FK_numero_dofluxo "
			   + "JOIN vwfuncionariosimples fs ON fs.FK_entidade = nflc.FK_funcionario "
			   + "JOIN tblaberturadecaixa ac ON ac.id_aberturadecaixa = fc.FK_abertura_decaixa "
			   + "JOIN tblfactura f on f.fk_abertura_dcaixa = ac.id_aberturadecaixa "
			   + "JOIN  tblfacturaexame fe on fe.FK_factura = f.id_factura "
			   + "WHERE flc.FK_numero_dofluxo = ? and fc.data_dfecho between ? and curdate()"
			   + "group by fc.FK_abertura_decaixa ";
	List<Ajuste> lsfluxo = new ArrayList<Ajuste>();
	try{
		con = Conexao.getConexao();
		PreparedStatement ps = con.prepareStatement(sql);
		ps.setInt(1, cod);
		ps.setDate(2, dat1);
		ResultSet rs = ps.executeQuery();
		while(rs.next()){
			Ajuste fluxo = new Ajuste();
			fluxo.setNumero_fechodecaixa(rs.getString("id_fecho_decaixa"));
			fluxo.setId_fluxo_decaixa(rs.getInt("FK_numero_dofluxo"));
			fluxo.setFK_entidade(rs.getInt("FK_entidade"));
			fluxo.setTotal_facturado(rs.getDouble("total_facturado"));
			fluxo.setTotal_sistema(rs.getDouble("total_reg_sistema"));
			fluxo.setMontante_dabertura(rs.getDouble("montante_dabertura"));
			fluxo.setNum_movimentos(rs.getInt("total_movimento"));
			fluxo.setHora_dfecho(rs.getString("hora_dfecho"));
			fluxo.setNome(rs.getString("n_c_funcionario"));
			Calendar data = Calendar.getInstance();
			data.setTime(rs.getDate("data_dfecho"));
			fluxo.setData_dfecho(data);
			lsfluxo.add(fluxo);
		}
		ps.close();
		con.close();
	} catch (SQLException e) {
		e.printStackTrace();
	}
	return lsfluxo;
}
	
	public List<Ajuste> getListaFluxosCaixas(){
		
		String sql = "select * from vwfluxodecaixa";
		List<Ajuste> lsfluxo = new ArrayList<Ajuste>();
		try{
			con = Conexao.getConexao();
			PreparedStatement ps = con.prepareStatement(sql);
			ResultSet rs = ps.executeQuery();
			while(rs.next()){
				Ajuste fluxo = new Ajuste();
				fluxo.setId_fluxo_decaixa(rs.getInt("id_fluxo_decaixa"));
				fluxo.setTotal_fluxo(rs.getDouble("total_fluxo"));
				fluxo.setTotal_facturado(rs.getDouble("total_dodia"));
				fluxo.setNumFluxo(rs.getString("numero_dofluxo"));
				fluxo.setNome(rs.getString("n_c_funcionario"));
				Calendar data = Calendar.getInstance();
				data.setTime(rs.getDate("data_dofluxo"));
				fluxo.setData_dofluxo(data);
				lsfluxo.add(fluxo);
			}
			ps.close();
			con.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return lsfluxo;
	}
	public void totalMulticaixa(Caixa cx)
	{
		String sql = "INSERT INTO tbltotalmulticaixa (fk_fecho_dcaixa, total_mc, total_mao,  fk_func_caixa) VALUES (?,?,?,?)";
		try {
			con = Conexao.getConexao();
			PreparedStatement prd = con.prepareStatement(sql);
			prd.setInt(1, cx.getId_fecho_decaixa());
			prd.setDouble(2, cx.getTotalMcx());
			prd.setDouble(3, cx.getTotalMao());
			prd.setInt(4, cx.getFk_entidade_caixa());
			prd.execute();
			prd.close();
			System.out.println("Cadastro de sucesso...tbltotalmulticaixa"); 
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
}
