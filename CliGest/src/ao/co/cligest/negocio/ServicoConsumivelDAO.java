package ao.co.cligest.negocio;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

import org.apache.commons.lang3.StringUtils;

import ao.co.cligest.contracts.IServicoConsumivel;
import ao.co.cligest.entidades.Fornecedor;
import ao.co.cligest.entidades.ServicoConsumivel;

public class ServicoConsumivelDAO implements IServicoConsumivel{

	private Connection con;
	Formatando ft = new Formatando();
	Calendar data = Calendar.getInstance();
	private Calendar cal = Calendar.getInstance();
	@Override
	public void novoServicoConsumivel(ServicoConsumivel sc) {
		String sql = "INSERT INTO tblplani_servico_consumiveis (referencia_serv_con,servico_consumiveis,custo_preco,fk_taxa,descricao_serv_con,categoria_serv_con) VALUES (?,?,?,?,?,?)";
		try {
			 con = Conexao.getConexao();
			 PreparedStatement ps = con.prepareStatement(sql);
			 ps.setString(1, sc.getReferencia_serv_con());
			 ps.setString(2, sc.getServico_consumiveis());
			 ps.setDouble(3, sc.getCusto_preco());
			 ps.setInt(4, sc.getFk_taxa());
			 ps.setString(5, sc.getDescricao_serv_con());
			 ps.setString(6, sc.getCategoria_serv_con());
			 ps.execute();
			 con.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	@Override
	public void editaServicoConsumivel(ServicoConsumivel sc) {
		String sql = "UPDATE tblplani_servico_consumiveis SET referencia_serv_con = ?,servico_consumiveis = ?,custo_preco = ? ,fk_taxa = ? ,descricao_serv_con = ? ,categoria_serv_con = ? WHERE id_servico_consumiveis = ?";
		try {
			 con = Conexao.getConexao();
			 PreparedStatement ps = con.prepareStatement(sql);
			 ps.setString(1, sc.getReferencia_serv_con());
			 ps.setString(2, sc.getServico_consumiveis());
			 ps.setDouble(3, sc.getCusto_preco());
			 ps.setInt(4, sc.getFk_taxa());
			 ps.setString(5, sc.getDescricao_serv_con());
			 ps.setString(6, sc.getCategoria_serv_con());
			 ps.setInt(7, sc.getId_servico_consumiveis());
			 ps.execute();
			 con.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
		
	}

	@Override
	public void excluirServicoConsumivel(int cod) {
		String sql = "DELETE FROM tblplani_servico_consumiveis WHERE id_servico_consumiveis = ?";
		try {
			 con = Conexao.getConexao();
			 PreparedStatement ps = con.prepareStatement(sql);
			 ps.setInt(1, cod);
			 ps.execute();
			 con.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
		
	}
	@Override
	public boolean podeEliminarServicoConsumivel(int cod){
		String sql = "SELECT * FROM tblplani_agregar_consumiveis_linha WHERE fk_servico_consumiveis = ?";
		boolean UltimoId = false;
		try {
			 con = Conexao.getConexao();
			 PreparedStatement ps = con.prepareStatement(sql);
			 ps.setInt(1, cod);
			 ResultSet rs = ps.executeQuery();
			 if(rs.next())
				 UltimoId = true;
			 
			 ps.execute();
			 con.close();
		} catch (Exception e) {
			e.printStackTrace();
		}	
		return UltimoId;
	}
	@Override
	public boolean existeServicoConsumivel(ServicoConsumivel sc){
		boolean existe =false;
		if(sc.getId_servico_consumiveis()!=0)
			existe = existeServicoConsumivelEd(sc);
		else 
			existe = existeServicoConsumivelNv(sc);
		
		return existe;
	}

	private boolean existeServicoConsumivelNv(ServicoConsumivel sc){
		boolean existe =false;
		String sql = "SELECT * FROM tblplani_servico_consumiveis WHERE servico_consumiveis = ?";
		
			try {
				 con = Conexao.getConexao();
				 PreparedStatement ps = con.prepareStatement(sql);
				 ps.setString(1, sc.getServico_consumiveis());
				 ResultSet rs = ps.executeQuery();
				 if(rs.next())
					 existe = true;
				 
				 ps.execute();
				 con.close();
			} catch (Exception e) {
				e.printStackTrace();
			}	
		return existe;
	}
	private boolean existeServicoConsumivelEd(ServicoConsumivel sc){
		
		boolean existe = false;
		ServicoConsumivel temp = buscarServicoConsumiveisPorId(sc.getId_servico_consumiveis());
		if(!temp.getServico_consumiveis().equals(sc.getServico_consumiveis()))
			existe = existeServicoConsumivelNv(sc);
		
		
		return existe;
	}

	@Override
	public List<ServicoConsumivel> buscarServicoConsumiveis() {
		String sql = "SELECT * FROM vwplani_servicos_consumiveis ORDER BY id_servico_consumiveis DESC";
		List<ServicoConsumivel> lista = new ArrayList<ServicoConsumivel>();
		try {
			 con = Conexao.getConexao();
			 PreparedStatement ps = con.prepareStatement(sql);
			 ResultSet rs = ps.executeQuery();
			 while(rs.next()){
				 ServicoConsumivel sc = new ServicoConsumivel();
				 sc.setId_servico_consumiveis(rs.getInt("id_servico_consumiveis"));
				 sc.setServico_consumiveis(rs.getString("servico_consumiveis"));
				 sc.setReferencia_serv_con(rs.getString("referencia_serv_con"));
				 sc.setCusto_preco(rs.getDouble("custo_preco"));
				 sc.setFk_taxa(rs.getInt("fk_taxa"));
				 sc.setTaxakz(rs.getDouble("taxa_kz"));
				 sc.setTaxa(rs.getString("tipo_de_taxa"));
				 sc.setTaxaperc(rs.getDouble("taxa_percent"));
				 sc.setDescricao_serv_con(rs.getString("descricao_serv_con"));
				 sc.setCategoria_serv_con(rs.getString("categoria_serv_con"));
				 lista.add(sc);
			 }
			 
			 ps.execute();
			 con.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return lista;
	}

	@Override
	public List<ServicoConsumivel> buscarServicoConsumiveis(String param) {
		String sql = "SELECT * FROM vwplani_servicos_consumiveis WHERE referencia_serv_con LIKE ? OR servico_consumiveis LIKE ? OR categoria_serv_con = ? ORDER BY id_servico_consumiveis DESC";
		List<ServicoConsumivel> lista = new ArrayList<ServicoConsumivel>();
		try {
			 con = Conexao.getConexao();
			 PreparedStatement ps = con.prepareStatement(sql);
			 ps.setString(1, "%"+param+"%");
			 ps.setString(2, "%"+param+"%");
			 ps.setString(3, param);
			 ResultSet rs = ps.executeQuery();
			 while(rs.next()){
				 ServicoConsumivel sc = new ServicoConsumivel();
				 sc.setId_servico_consumiveis(rs.getInt("id_servico_consumiveis"));
				 sc.setServico_consumiveis(rs.getString("servico_consumiveis"));
				 sc.setReferencia_serv_con(rs.getString("referencia_serv_con"));
				 sc.setCusto_preco(rs.getDouble("custo_preco"));
				 sc.setFk_taxa(rs.getInt("fk_taxa"));
				 sc.setTaxakz(rs.getDouble("taxa_kz"));
				 sc.setTaxa(rs.getString("tipo_de_taxa"));
				 sc.setTaxaperc(rs.getDouble("taxa_percent"));
				 sc.setDescricao_serv_con(rs.getString("descricao_serv_con"));
				 sc.setCategoria_serv_con(rs.getString("categoria_serv_con"));
				 lista.add(sc);
			 }
			 
			 ps.execute();
			 con.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return lista;
	}
	@Override
	public List<ServicoConsumivel> buscarTaxas() {
		String sql = "SELECT * FROM tbltaxas  ";
		List<ServicoConsumivel> lista = new ArrayList<ServicoConsumivel>();
		try {
			 con = Conexao.getConexao();
			 PreparedStatement ps = con.prepareStatement(sql);
			 
			 ResultSet rs = ps.executeQuery();
			 while(rs.next()){
				 ServicoConsumivel sc = new ServicoConsumivel();
				 sc.setFk_taxa(rs.getInt("id_taxa"));
				 sc.setTaxa(rs.getString("tipo_de_taxa"));
				 sc.setTaxaperc(rs.getDouble("taxa"));
		 
				 lista.add(sc);
			 }
			 
			 ps.execute();
			 con.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return lista;
	}

	@Override
	public ServicoConsumivel buscarServicoConsumiveisPorId(int codsc) {
		String sql = "SELECT * FROM vwplani_servicos_consumiveis WHERE id_servico_consumiveis = ? ORDER BY id_servico_consumiveis DESC";
		 ServicoConsumivel sc = new ServicoConsumivel();
		try {
			 con = Conexao.getConexao();
			 PreparedStatement ps = con.prepareStatement(sql);
			 ps.setInt(1, codsc);
			 ResultSet rs = ps.executeQuery();
			 if(rs.next()){
				 sc.setId_servico_consumiveis(rs.getInt("id_servico_consumiveis"));
				 sc.setServico_consumiveis(rs.getString("servico_consumiveis"));
				 sc.setReferencia_serv_con(rs.getString("referencia_serv_con"));
				 sc.setCusto_preco(rs.getDouble("custo_preco"));
				 sc.setFk_taxa(rs.getInt("fk_taxa"));
				 sc.setTaxakz(rs.getDouble("taxa_kz"));
				 sc.setTaxa(rs.getString("tipo_de_taxa"));
				 sc.setTaxaperc(rs.getDouble("taxa_percent"));
				 sc.setDescricao_serv_con(rs.getString("descricao_serv_con"));
				 sc.setCategoria_serv_con(rs.getString("categoria_serv_con"));
			 }
			 
			 ps.execute();
			 con.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return sc;
	}
	
	@Override
	public String numReferencia() {
		String sql = "SELECT MAX(id_servico_consumiveis) id FROM tblplani_servico_consumiveis ";
		int ultimoCod = 1;
		int ano = cal.get(Calendar.YEAR) ;
		try {
			 con = Conexao.getConexao();
			 PreparedStatement ps = con.prepareStatement(sql);
			 ResultSet rs = ps.executeQuery();
			 if(rs.next())
				 ultimoCod = ultimoCod + rs.getInt("id");
			 ps.close();
			 con.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
		String num = "SC/"+ ano + "/" + StringUtils.leftPad(Integer.toString(ultimoCod), 4, "0");
		return num;
	}
	@Override
	public String numReferenciaServico() {
		String sql = "SELECT MAX(id_planificar_serv_consu) id FROM tblplani_planificar_servico_consumiveis ";
		int ultimoCod = 1;
		int ano = cal.get(Calendar.YEAR) ;
		try {
			 con = Conexao.getConexao();
			 PreparedStatement ps = con.prepareStatement(sql);
			 ResultSet rs = ps.executeQuery();
			 if(rs.next())
				 ultimoCod = ultimoCod + rs.getInt("id");
			 ps.close();
			 con.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
		String num = "SER/"+ ano + "/" + StringUtils.leftPad(Integer.toString(ultimoCod), 4, "0");
		return num;
	}
	@Override
	public List<Fornecedor> listaFornecedores() {
		String sql = "SELECT * FROM vwfornecedor ";
		List<Fornecedor> lista = new ArrayList<Fornecedor>();
		try {
			 con = Conexao.getConexao();
			 PreparedStatement ps = con.prepareStatement(sql);
			 ResultSet rs = ps.executeQuery();
			 while(rs.next()){
				 Fornecedor f = new Fornecedor();
				 f.setEmpresa(rs.getString("empresa"));
				 f.setFK_forncdor(rs.getInt("FK_fornecedor"));
				 f.setN_forncdor(rs.getString("numero_fornecedor"));
				 lista.add(f);
			 }
			 ps.close();
			 con.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return lista;
	}
	
	@Override
	public Fornecedor listaFornecedorPorId(int cod) {
		String sql = "SELECT * FROM vwfornecedor where FK_fornecedor = ?";
		Fornecedor f = new Fornecedor();
		try {
			 con = Conexao.getConexao();
			 PreparedStatement ps = con.prepareStatement(sql);
			 ps.setInt(1, cod);
			 ResultSet rs = ps.executeQuery();
			 if(rs.next()){
				 f.setEmpresa(rs.getString("empresa"));
				 f.setFK_forncdor(rs.getInt("FK_fornecedor"));
				 f.setN_forncdor(rs.getString("numero_fornecedor"));
			 }
			 ps.close();
			 con.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return f;
	}

	@Override
	public List<ServicoConsumivel> listaTipoContrato() {
		String sql = "SELECT * FROM tblplani_tipo_contracto ";
		List<ServicoConsumivel> lista = new ArrayList<ServicoConsumivel>();
		try {
			 con = Conexao.getConexao();
			 PreparedStatement ps = con.prepareStatement(sql);
			 ResultSet rs = ps.executeQuery();
			 while(rs.next()){
				 ServicoConsumivel sc = new ServicoConsumivel();
				 sc.setId_tipo_contracto(rs.getInt("id_tipo_contracto"));
				 sc.setTipo_contracto(rs.getString("tipo_contracto"));
				 lista.add(sc);
			 }
			 ps.close();
			 con.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return lista;
	}

	@Override
	public String numReferenciaAgregar() {
		String sql = "SELECT MAX(id_agregar_consumiveis) id FROM tblplani_agregar_consumiveis ";
		int ultimoCod = 1;
		int ano = cal.get(Calendar.YEAR) ;
		try {
			 con = Conexao.getConexao();
			 PreparedStatement ps = con.prepareStatement(sql);
			 ResultSet rs = ps.executeQuery();
			 if(rs.next())
				 ultimoCod = ultimoCod + rs.getInt("id");
			 con.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
		String num = "CON/"+ ano + "/" + StringUtils.leftPad(Integer.toString(ultimoCod), 4, "0");
		return num;
	}

	@Override
	public int novaAgregacaoConsumivel(ServicoConsumivel sc) {
		int ultimoId = 0;
		String sql = "INSERT INTO tblplani_agregar_consumiveis (total_agregar_consumiveis,referencia_agregar_consumiveis,fk_funcionario,data_agregar_consumiveis,fk_fornecedor) VALUES (?,?,?,?,?)";
		try {
			 con = Conexao.getConexao();
			 PreparedStatement ps = con.prepareStatement(sql);
			 ps.setDouble(1, sc.getTotal_agregar_consumiveis());
			 ps.setString(2, sc.getReferencia_agregar_consumiveis());
			 ps.setInt(3, sc.getFk_funcionario());
			 ps.setDate(4, ft.data_registo());
			 ps.setInt(5, sc.getFk_fornecedor());
			 ps.execute();
		
			 ResultSet rs = ps.executeQuery("SELECT LAST_INSERT_ID()");
			    if(rs.next())
			      ultimoId = rs.getInt(1);
			 con.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return ultimoId;
	}

	@Override
	public void novaAgregacaoConsumivelItens(ServicoConsumivel sc) {
		String sql = "INSERT INTO tblplani_agregar_consumiveis_linha (fk_agregar_consumiveis,fk_servico_consumiveis,qtd_servico_consumiveis,custo_preco_servico_consumiveis,serv_consu_taxa_percent,serv_consu_taxa_kz) VALUES (?,?,?,?,?,?)";
		try {
			 con = Conexao.getConexao();
			 PreparedStatement ps = con.prepareStatement(sql);
			 ps.setInt(1, sc.getId_agregar_consumiveis());
			 ps.setInt(2, sc.getId_servico_consumiveis());
			 ps.setInt(3, sc.getQtd_servico_consumiveis());
			 ps.setDouble(4, sc.getCusto_preco());
			 ps.setDouble(5, sc.getTaxaperc());
			 ps.setDouble(6, sc.getTaxakz());
			 ps.execute();
			 con.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
		 
		
	}

	@Override
	public List<ServicoConsumivel> listaConsumiveisAgregados() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public ServicoConsumivel listaDetalheConsumiveisAgregados(int cod) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public int novoPlanoServicoConsumivel(ServicoConsumivel sc) {
		int ultimoId = 0;
		String sql = "INSERT INTO tblplani_planificar_servico_consumiveis (fk_fornecedor,referencia_serv_consu,periocidade_serv_consu,fk_contas_contabeis,fk_funcionario,data_serv_consu,total_butro_serv_consu,fk_servico_agregado) VALUES (?,?,?,?,?,?,?,?)";
		try {
			 con = Conexao.getConexao();
			 PreparedStatement ps = con.prepareStatement(sql);
			 ps.setInt(1, sc.getFk_fornecedor());
			 ps.setString(2, sc.getReferencia_agregar_consumiveis());
			 ps.setInt(3, sc.getPeriocidade_serv_consu());
			 ps.setInt(4, sc.getFk_contas_contabeis());
			 ps.setInt(5, sc.getFk_funcionario());
			 ps.setDate(6, ft.data_registo());
			 ps.setDouble(7, sc.getTotal_butro_serv_consu());
			 ps.setInt(8, sc.getId_servico_consumiveis());
			 ps.execute();
			
			 ResultSet rs = ps.executeQuery("SELECT LAST_INSERT_ID()");
			    if(rs.next())
			      ultimoId = rs.getInt(1);
			    con.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return ultimoId;
	}

	@Override
	public void novoPlanoServicoConsumivelPontual(ServicoConsumivel sc) {
		String sql = "INSERT INTO tblplani_planificar_pontual (fk_planificar_servico_consumiveis,data_planificar_pontual,descricao_planificar_pontual) VALUES (?,?,?)";
		try {
			 con = Conexao.getConexao();
			 PreparedStatement ps = con.prepareStatement(sql);
			 ps.setInt(1, sc.getId_planificar_serv_consu());
			 ps.setDate(2, new java.sql.Date(sc.getData_planificar_pontual().getTimeInMillis()));
			 ps.setString(3, sc.getDescricao_planificar());
			 ps.execute();
			 con.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	@Override
	public void novoPlanoServicoConsumivelPermanente(ServicoConsumivel sc) {
		String sql = "INSERT INTO tblplani_planificar_permanente (fk_planificar_servico_consumiveis,dia_consumiveis,data_inicio__consumiveis,data_fim__consumiveis,descricao_consumiveis,dia_data_consumiveis) VALUES (?,?,?,?,?,?)";
		try {
			 con = Conexao.getConexao();
			 PreparedStatement ps = con.prepareStatement(sql);
			 ps.setInt(1, sc.getId_planificar_serv_consu());
			 ps.setInt(2, sc.getDia_consumiveis());
			 ps.setDate(3, new java.sql.Date(sc.getData_inicio__consumiveis().getTimeInMillis()));
			 ps.setDate(4, new java.sql.Date(sc.getData_fim__consumiveis().getTimeInMillis()));
			 ps.setString(5, sc.getDescricao_planificar());
			 ps.setDate(6, new java.sql.Date(sc.getDia_data_consumiveis().getTimeInMillis()));
			 ps.execute();
			 con.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	@Override
	public void novoPlanoServicoConsumivelContrato(ServicoConsumivel sc) {
		String sql = "INSERT INTO tblplani_contracto (fk_planificar_servico_consumiveis,fk_tipo_contracto,referencia_contracto,data_inicio_contracto,data_fim_contracto,fk_fornecedor) VALUES (?,?,?,?,?,?)";
		try {
			 con = Conexao.getConexao();
			 PreparedStatement ps = con.prepareStatement(sql);
			 ps.setInt(1, sc.getId_planificar_serv_consu());
			 ps.setInt(2, sc.getId_tipo_contracto());
			 ps.setString(3, sc.getReferencia_serv_con());
			 ps.setDate(4, new java.sql.Date(sc.getDia_data_contracto().getTimeInMillis()));
			 ps.setDate(5, new java.sql.Date(sc.getData_fim__contracto().getTimeInMillis()));
			 ps.setInt(6, sc.getFk_fornecedor());
			 ps.execute();
			 con.close();
		} catch (Exception e) {
			e.printStackTrace();
		}		
	}

	@Override
	public void excluirPlanoServicoConsumivelGeral(int cod) {
		ServicoConsumivel sc = retornaPlanoGeradoDivida(cod);
		if(sc!=null) {
			sc = buscarPlanoServicoConsumivelPermanente(sc,cod);
			sc = buscarPlanoServicoConsumivelPontual(sc,cod);
			sc = buscarPlanoServicoConsumivelPermanente(sc,cod);
			planoPagosExpiradoEliminado(sc);
		}
		excluirServicoConsumivelContrato(cod);
		excluirServicoConsumivelPermanente(cod);
		excluirServicoConsumivelPontual(cod);
		excluirPlanoServicoConsumivel(cod);
	}

	private void excluirPlanoServicoConsumivel(int cod) {
		String sql = "DELETE FROM tblplani_planificar_servico_consumiveis WHERE id_planificar_serv_consu = ? ";
		try {
			 con = Conexao.getConexao();
			 PreparedStatement ps = con.prepareStatement(sql);
			 ps.setInt(1, cod);
			 ps.execute();
			 con.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
		
	}

	private void excluirServicoConsumivelPontual(int cod) {
		String sql = "DELETE FROM tblplani_planificar_pontual WHERE fk_planificar_servico_consumiveis = ? ";
		try {
			 con = Conexao.getConexao();
			 PreparedStatement ps = con.prepareStatement(sql);
			 ps.setInt(1, cod);
			 ps.execute();
			 con.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
		
	}

	private void excluirServicoConsumivelPermanente(int cod) {
		String sql = "DELETE FROM tblplani_planificar_permanente WHERE fk_planificar_servico_consumiveis = ? ";
		try {
			 con = Conexao.getConexao();
			 PreparedStatement ps = con.prepareStatement(sql);
			 ps.setInt(1, cod);
			 ps.execute();
			 con.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
		
	}

	private void excluirServicoConsumivelContrato(int cod) {
		String sql = "DELETE FROM tblplani_contracto WHERE fk_planificar_servico_consumiveis = ? ";
		try {
			 con = Conexao.getConexao();
			 PreparedStatement ps = con.prepareStatement(sql);
			 ps.setInt(1, cod);
			 ps.execute();
			 con.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
		
	}

	@Override
	public ServicoConsumivel PlanoServicoConsumiveisPorId(int cod) {
		ServicoConsumivel sc = new ServicoConsumivel();
		sc = buscarPlanoServicoConsumivelPorId(cod);
		sc = buscarPlanoServicoConsumivelPontual(sc,cod);
		sc = buscarPlanoServicoConsumivelPermanente(sc,cod);
		sc = buscarPlanoServicoConsumivelContrato(sc,cod);
		sc = buscarIdAgregadoConsumivel(sc);
		return sc;
	}
	
	 

	private void planoPagosExpiradoEliminado(ServicoConsumivel sc)
	{
		String sql = "INSERT INTO tblplani_planos_pago_expirados_eliminados (fk_planificados_dividas,date_planos_expirados,data_inicio_planificados_dividas,data_fim_planificados_dividas)"
				+ " VALUES (?,?,?,?)";
			
		try {
			 con = Conexao.getConexao();
			 PreparedStatement ps = con.prepareStatement(sql);
			 ps.setInt(1, sc.getId_planificados_dividas());
			 ps.setDate(2, new java.sql.Date(sc.getData_planificados_dividas().getTimeInMillis()));
			 ps.setDate(3, new java.sql.Date(sc.getData_inicio__consumiveis().getTimeInMillis()));
			 ps.setDate(4, new java.sql.Date(sc.getData_fim__consumiveis().getTimeInMillis()));
			 ps.execute();			  
			 ps.close();
			 con.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	private ServicoConsumivel retornaPlanoGeradoDivida(int codsc)
	{
		String sql = "SELECT * FROM  tblplani_gerar_dividas WHERE fk_planificar_servico_consumiveis = ?";
		ServicoConsumivel sc = new ServicoConsumivel();	
		try {
			 con = Conexao.getConexao();
			 PreparedStatement ps = con.prepareStatement(sql);
			 ps.setInt(1, codsc);
			  ResultSet rs = ps.executeQuery();
			  if(rs.next())
			  {
				  sc.setId_planificados_dividas(rs.getInt("id_planificados_dividas"));
				  sc.setCategoria_plan_dividas(rs.getString("categoria_planificados_dividas"));
				  sc.setId_planificar_serv_consu(rs.getInt("fk_planificar_servico_consumiveis"));
				  data = Calendar.getInstance();
				  data.setTime(rs.getDate("data_planificados_dividas"));
				  sc.setData_planificados_dividas(data);
			  }
			 			  
			 ps.close();
			 con.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return sc;
	}
	private ServicoConsumivel buscarIdAgregadoConsumivel(ServicoConsumivel sc) {
		String sql = "SELECT * FROM tblplani_agregar_consumiveis WHERE referencia_agregar_consumiveis = ?";
		 
		if(sc == null)
			sc = new ServicoConsumivel();
		
		try {
			 con = Conexao.getConexao();
			 PreparedStatement ps = con.prepareStatement(sql);
			 ps.setString(1, sc.getReferencia_serv_con());
			 ResultSet rs = ps.executeQuery();
			 if(rs.next())
			 {
				 sc.setId_agregar_consumiveis(rs.getInt("id_agregar_consumiveis"));
			 }
			 ps.close();
			 con.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return sc;
	}

	private ServicoConsumivel buscarPlanoServicoConsumivelContrato(ServicoConsumivel sc, int cod) {
		String sql = "SELECT * FROM tblplani_contracto WHERE fk_planificar_servico_consumiveis = ?";
		 
		if(sc == null)
			sc = new ServicoConsumivel();
		
		try {
			 con = Conexao.getConexao();
			 PreparedStatement ps = con.prepareStatement(sql);
			 ps.setInt(1, cod);
			 ResultSet rs = ps.executeQuery();
			 if(rs.next())
			 {
				 sc.setId_planificar_serv_consu(rs.getInt("fk_planificar_servico_consumiveis"));
				 sc.setId_plani_contracto(rs.getInt("id_plani_contracto"));
				 sc.setRefContrato(rs.getString("referencia_contracto"));
				 sc.setId_tipo_contracto(rs.getInt("fk_tipo_contracto"));
				 data.setTime(rs.getDate("data_inicio_contracto"));
				 sc.setDia_data_contracto(data);
				 data = Calendar.getInstance();
				 data.setTime(rs.getDate("data_fim_contracto"));
				 sc.setData_fim__contracto(data);
				 
			 }
			 ps.close();
			 con.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return sc;
	}

	private ServicoConsumivel buscarPlanoServicoConsumivelPermanente(ServicoConsumivel sc, int cod) {
		String sql = "SELECT * FROM tblplani_planificar_permanente WHERE fk_planificar_servico_consumiveis = ?";
		 
		if(sc == null)
			sc = new ServicoConsumivel();
		
		try {
			 con = Conexao.getConexao();
			 PreparedStatement ps = con.prepareStatement(sql);
			 ps.setInt(1, cod);
			 ResultSet rs = ps.executeQuery();
			 if(rs.next())
			 {
				 sc.setId_planificar_serv_consu(rs.getInt("fk_planificar_servico_consumiveis"));
				 sc.setId_planificar_periodicidade(rs.getInt("id_planificar_permanente"));
				 sc.setDescricao_planificar(rs.getString("descricao_consumiveis"));
				 sc.setDia_consumiveis(rs.getInt("dia_consumiveis"));
				 Calendar data = Calendar.getInstance();
				 data.setTime(rs.getDate("dia_data_consumiveis"));
				 sc.setData_serv_consu(data);
				 data = Calendar.getInstance();
				 data.setTime(rs.getDate("data_inicio__consumiveis"));
				 sc.setData_inicio__consumiveis(data);
				 data = Calendar.getInstance();
				 data.setTime(rs.getDate("data_fim__consumiveis"));
				 sc.setData_fim__consumiveis(data);
				 
			 }
			 ps.close();
			 con.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return sc;
	}

	private ServicoConsumivel buscarPlanoServicoConsumivelPontual(ServicoConsumivel sc, int cod) {
		String sql = "SELECT * FROM tblplani_planificar_pontual WHERE fk_planificar_servico_consumiveis = ?";
		 
		if(sc == null)
			sc = new ServicoConsumivel();
		
		try {
			 con = Conexao.getConexao();
			 PreparedStatement ps = con.prepareStatement(sql);
			 ps.setInt(1, cod);
			 ResultSet rs = ps.executeQuery();
			 if(rs.next())
			 {
				 sc.setId_planificar_serv_consu(rs.getInt("fk_planificar_servico_consumiveis"));
				 sc.setId_planificar_periodicidade(rs.getInt("id_planificar_pontual"));
				 sc.setDescricao_planificar(rs.getString("descricao_planificar_pontual"));
				 Calendar data = Calendar.getInstance();
				 data.setTime(rs.getDate("data_planificar_pontual"));
				 sc.setData_serv_consu(data);
			 }
			 ps.close();
			 con.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return sc;
	}

	 

	private ServicoConsumivel buscarPlanoServicoConsumivelPorId(int cod) {
		String sql = "SELECT * FROM tblplani_planificar_servico_consumiveis WHERE id_planificar_serv_consu = ?";
		ServicoConsumivel sc = new ServicoConsumivel();
		try {
			 con = Conexao.getConexao();
			 PreparedStatement ps = con.prepareStatement(sql);
			 ps.setInt(1, cod);
			 ResultSet rs = ps.executeQuery();
			 if(rs.next())
			 {
				 sc.setId_planificar_serv_consu(rs.getInt("id_planificar_serv_consu"));
				 sc.setFk_fornecedor(rs.getInt("fk_fornecedor"));
				 sc.setReferencia_serv_con(rs.getString("referencia_serv_consu"));
				 sc.setPeriocidade_serv_consu(rs.getInt("periocidade_serv_consu"));
				 Calendar data = Calendar.getInstance();
				 data.setTime(rs.getDate("data_serv_consu"));
				 sc.setData_serv_consu(data);
				 sc.setTotal_butro_serv_consu(rs.getDouble("total_butro_serv_consu"));
				 sc.setId_servico_consumiveis(rs.getInt("fk_servico_agregado"));
			 }
			 ps.close();
			 con.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return sc;
	}

	@Override
	public List<ServicoConsumivel> listaPlanoServicoConsumiveis() {
		// vwbuscarplanosservicosconsumivel
		String sql = "SELECT * FROM vwbuscarplanosservicosconsumivel   ORDER BY id_planificar_serv_consu DESC";
		List<ServicoConsumivel> lista = new ArrayList<ServicoConsumivel>();
		try {
			 con = Conexao.getConexao();
			 PreparedStatement ps = con.prepareStatement(sql);
			 ResultSet rs = ps.executeQuery();
			 while(rs.next()){
				 ServicoConsumivel sc = new ServicoConsumivel();
				 sc.setNomeFornecedor(rs.getString("empresa"));
				 sc.setRefFornecedor(rs.getString("numero_fornecedor"));
				 sc.setId_plani_contracto(rs.getInt("id_plani_contracto"));
				 sc.setTotal_butro_serv_consu(rs.getDouble("total_butro_serv_consu"));
				 sc.setPeriocidade_serv_consu(rs.getInt("periocidade_serv_consu"));
				 sc.setId_plani_contracto(rs.getInt("id_plani_contracto"));
				 sc.setNomeFuncionario(rs.getString("n_c_funcionario"));
				 sc.setReferencia_serv_con(rs.getString("referencia_serv_consu"));
				 sc.setId_planificar_serv_consu(rs.getInt("id_planificar_serv_consu"));
				 Calendar data = Calendar.getInstance();
				 data.setTime(rs.getDate("data_serv_consu"));
				 sc.setData_serv_consu(data);
				 lista.add(sc);
			 }
			 ps.execute();
			 con.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return lista;
	}

	@Override
	public List<ServicoConsumivel> listaPlanoServicoConsumiveis(String param1, String param2) {
		String sql = "SELECT * FROM vwbuscarplanosservicosconsumivel   ORDER BY id_planificar_serv_consu DESC";
		List<ServicoConsumivel> lista = new ArrayList<ServicoConsumivel>();
		try {
			 con = Conexao.getConexao();
			 PreparedStatement ps = con.prepareStatement(sql);
			 ResultSet rs = ps.executeQuery();
			 while(rs.next()){
				 ServicoConsumivel sc = new ServicoConsumivel();
				 sc.setNomeFornecedor(rs.getString("empresa"));
				 sc.setRefFornecedor(rs.getString("numero_fornecedor"));
				 sc.setId_plani_contracto(rs.getInt("id_plani_contracto"));
				 sc.setTotal_butro_serv_consu(rs.getDouble("total_butro_serv_consu"));
				 sc.setPeriocidade_serv_consu(rs.getInt("periocidade_serv_consu"));
				 sc.setNomeFuncionario(rs.getString("n_c_funcionario"));
				 sc.setReferencia_serv_con(rs.getString("referencia_serv_consu"));
				 sc.setId_planificar_serv_consu(rs.getInt("id_planificar_serv_consu"));
				 lista.add(sc);
			 }
			 ps.execute();
			 con.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return lista;
	}

	@Override
	public List<ServicoConsumivel> listaPlanoServicoConsumiveisPeriodicidade(int periodo) {
		String sql = "SELECT * FROM vwbuscarplanosservicosconsumivel   ORDER BY id_planificar_serv_consu DESC";
		List<ServicoConsumivel> lista = new ArrayList<ServicoConsumivel>();
		try {
			 con = Conexao.getConexao();
			 PreparedStatement ps = con.prepareStatement(sql);
			 ResultSet rs = ps.executeQuery();
			 while(rs.next()){
				 ServicoConsumivel sc = new ServicoConsumivel();
				 sc.setNomeFornecedor(rs.getString("empresa"));
				 sc.setRefFornecedor(rs.getString("numero_fornecedor"));
				 sc.setId_plani_contracto(rs.getInt("id_plani_contracto"));
				 sc.setTotal_butro_serv_consu(rs.getDouble("total_butro_serv_consu"));
				 sc.setPeriocidade_serv_consu(rs.getInt("periocidade_serv_consu"));
				 sc.setNomeFuncionario(rs.getString("n_c_funcionario"));
				 sc.setReferencia_serv_con(rs.getString("referencia_serv_consu"));
				 sc.setId_planificar_serv_consu(rs.getInt("id_planificar_serv_consu"));
				 lista.add(sc);
			 }
			 ps.execute();
			 con.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return lista;
	}

	@Override
	public List<ServicoConsumivel> buscarConsumiveis() {
		String sql = "SELECT * FROM vwplani_servicos_consumiveis where categoria_serv_con = 'Consumível' ORDER BY id_servico_consumiveis DESC";
		List<ServicoConsumivel> lista = new ArrayList<ServicoConsumivel>();
		try {
			 con = Conexao.getConexao();
			 PreparedStatement ps = con.prepareStatement(sql);
			 ResultSet rs = ps.executeQuery();
			 while(rs.next()){
				 ServicoConsumivel sc = new ServicoConsumivel();
				 sc.setId_servico_consumiveis(rs.getInt("id_servico_consumiveis"));
				 sc.setServico_consumiveis(rs.getString("servico_consumiveis"));
				 sc.setReferencia_serv_con(rs.getString("referencia_serv_con"));
				 sc.setCusto_preco(rs.getDouble("custo_preco"));
				 sc.setFk_taxa(rs.getInt("fk_taxa"));
				 sc.setTaxakz(rs.getDouble("taxa_kz"));
				 sc.setTaxa(rs.getString("tipo_de_taxa"));
				 sc.setTaxaperc(rs.getDouble("taxa_percent"));
				 sc.setDescricao_serv_con(rs.getString("descricao_serv_con"));
				 sc.setCategoria_serv_con(rs.getString("categoria_serv_con"));
				 lista.add(sc);
			 }
			 
			 ps.execute();
			 con.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return lista;
	}
	
	@Override
	public List<ServicoConsumivel> buscarServicos() {
		String sql = "SELECT * FROM vwplani_servicos_consumiveis where categoria_serv_con = 'Serviço' ORDER BY id_servico_consumiveis DESC";
		List<ServicoConsumivel> lista = new ArrayList<ServicoConsumivel>();
		try {
			 con = Conexao.getConexao();
			 PreparedStatement ps = con.prepareStatement(sql);
			 ResultSet rs = ps.executeQuery();
			 while(rs.next()){
				 ServicoConsumivel sc = new ServicoConsumivel();
				 sc.setId_servico_consumiveis(rs.getInt("id_servico_consumiveis"));
				 sc.setServico_consumiveis(rs.getString("servico_consumiveis"));
				 sc.setReferencia_serv_con(rs.getString("referencia_serv_con"));
				 sc.setCusto_preco(rs.getDouble("custo_preco"));
				 sc.setFk_taxa(rs.getInt("fk_taxa"));
				 sc.setTaxakz(rs.getDouble("taxa_kz"));
				 sc.setTaxa(rs.getString("tipo_de_taxa"));
				 sc.setTaxaperc(rs.getDouble("taxa_percent"));
				 sc.setDescricao_serv_con(rs.getString("descricao_serv_con"));
				 sc.setCategoria_serv_con(rs.getString("categoria_serv_con"));
				 lista.add(sc);
			 }
			 
			 ps.execute();
			 con.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return lista;
	}
	
	@Override
	public List<ServicoConsumivel> buscarServicoConsumiveisAgregados() {
		String sql = "SELECT * FROM tblplani_agregar_consumiveis agr JOIN vwfornecedor vwf ON agr.fk_fornecedor = vwf.FK_fornecedor ORDER BY id_agregar_consumiveis DESC";
		List<ServicoConsumivel> lista = new ArrayList<ServicoConsumivel>();
		try {
			 con = Conexao.getConexao();
			 PreparedStatement ps = con.prepareStatement(sql);
			 ResultSet rs = ps.executeQuery();
			 while(rs.next()){
				 ServicoConsumivel sc = new ServicoConsumivel();
				 sc.setId_agregar_consumiveis(rs.getInt("id_agregar_consumiveis"));;
				 sc.setTotal_agregar_consumiveis(rs.getDouble("total_agregar_consumiveis"));
				 sc.setReferencia_agregar_consumiveis(rs.getString("referencia_agregar_consumiveis"));
				 sc.setNomeFornecedor(rs.getString("empresa"));
				 sc.setFk_fornecedor(rs.getInt("fk_fornecedor"));
				 lista.add(sc);
			 }
			 
			 ps.execute();
			 con.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return lista;
	}
	
	@Override
	public ServicoConsumivel buscarServicoConsumiveisAgregadosPorId(int id) {
		String sql = "SELECT * FROM tblplani_agregar_consumiveis agr JOIN vwfornecedor vwf ON agr.fk_fornecedor = vwf.FK_fornecedor and id_agregar_consumiveis = ? ORDER BY id_agregar_consumiveis DESC";
		 ServicoConsumivel sc = new ServicoConsumivel();
		try {
			 con = Conexao.getConexao();
			 PreparedStatement ps = con.prepareStatement(sql);
			 ps.setInt(1, id);
			 ResultSet rs = ps.executeQuery();
			 if(rs.next()){
				 sc.setId_agregar_consumiveis(rs.getInt("id_agregar_consumiveis"));;
				 sc.setTotal_agregar_consumiveis(rs.getDouble("total_agregar_consumiveis"));
				 sc.setReferencia_agregar_consumiveis(rs.getString("referencia_agregar_consumiveis"));
				 sc.setNomeFornecedor(rs.getString("empresa"));
				 sc.setFk_fornecedor(rs.getInt("fk_fornecedor"));
			 }
			 
			 ps.execute();
			 con.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return sc;
	}

	@Override
	public void editaPlanoServicoConsumivelPontual(ServicoConsumivel sc) {
		String sql = "UPDATE tblplani_planificar_pontual SET data_planificar_pontual = ?,descricao_planificar_pontual = ? WHERE fk_planificar_servico_consumiveis = ?";
		try {
			 con = Conexao.getConexao();
			 PreparedStatement ps = con.prepareStatement(sql);
			 ps.setDate(1, new java.sql.Date(sc.getData_planificar_pontual().getTimeInMillis()));
			 ps.setString(2, sc.getDescricao_planificar());
			 ps.setInt(3, sc.getId_planificar_serv_consu());
			 ps.execute();
			 con.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	@Override
	public void editaPlanoServicoConsumivelPermanente(ServicoConsumivel sc) {
		String sql = "UPDATE tblplani_planificar_permanente SET dia_consumiveis = ?,data_inicio__consumiveis = ?,data_fim__consumiveis = ?,descricao_consumiveis = ?,dia_data_consumiveis = ? WHERE fk_planificar_servico_consumiveis = ?";
		try {
			 con = Conexao.getConexao();
			 PreparedStatement ps = con.prepareStatement(sql);
			 ps.setInt(1, sc.getDia_consumiveis());
			 ps.setDate(2, new java.sql.Date(sc.getData_inicio__consumiveis().getTimeInMillis()));
			 ps.setDate(3, new java.sql.Date(sc.getData_fim__consumiveis().getTimeInMillis()));
			 ps.setString(4, sc.getDescricao_planificar());
			 ps.setDate(5, new java.sql.Date(sc.getDia_data_consumiveis().getTimeInMillis()));
			 ps.setInt(6, sc.getId_planificar_serv_consu());
			 ps.execute();
			 con.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	@Override
	public void editaPlanoServicoConsumivelContrato(ServicoConsumivel sc) {
		String sql = "UPDATE tblplani_contracto SET fk_tipo_contracto = ?,referencia_contracto = ?,data_inicio_contracto = ?,data_fim_contracto = ?,fk_fornecedor = ? WHERE fk_planificar_servico_consumiveis = ?";
		try {
			 con = Conexao.getConexao();
			 PreparedStatement ps = con.prepareStatement(sql);
			 ps.setInt(1, sc.getId_tipo_contracto());
			 ps.setString(2, sc.getReferencia_serv_con());
			 ps.setDate(3, new java.sql.Date(sc.getDia_data_contracto().getTimeInMillis()));
			 ps.setDate(4, new java.sql.Date(sc.getData_fim__contracto().getTimeInMillis()));
			 ps.setInt(5, sc.getFk_fornecedor());
			 ps.setInt(6, sc.getId_planificar_serv_consu());
			 ps.execute();
			 con.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	@Override
	public void editaPlanoServicoConsumivel(ServicoConsumivel sc) {
	 
		String sql = "UPDATE tblplani_planificar_servico_consumiveis set fk_fornecedor = ?,periocidade_serv_consu = ?,fk_contas_contabeis = ?,fk_funcionario = ?,data_serv_consu = ?,total_butro_serv_consu = ?, fk_servico_agregado = ? WHERE id_planificar_serv_consu = ?";
		try {
			 con = Conexao.getConexao();
			 PreparedStatement ps = con.prepareStatement(sql);
			 ps.setInt(1, sc.getFk_fornecedor());
			 ps.setInt(2, sc.getPeriocidade_serv_consu());
			 ps.setInt(3, sc.getFk_contas_contabeis());
			 ps.setInt(4, sc.getFk_funcionario());
			 ps.setDate(5, ft.data_registo());
			 ps.setDouble(6, sc.getTotal_butro_serv_consu());
			 ps.setInt(7, sc.getId_servico_consumiveis());
			 ps.setInt(8, sc.getId_planificar_serv_consu());
			 ps.execute();
			 con.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
		
	}
	
	private void gerarDividaPlanoServicoConsumivel(ServicoConsumivel sc)
	{
		String  sql = "INSERT INTO tblplani_gerar_dividas (ref_planificados_dividas, fk_fornecedor, total_por_pagar_planificados_dividas, data_planificados_dividas,categoria_planificados_dividas,fk_funcionario,total_kz_planificados_dividas,fk_planificar_servico_consumiveis) VALUES (?,?,?,?,?,?,?,?)";
		try {
			 con = Conexao.getConexao();
			 PreparedStatement ps = con.prepareStatement(sql);
			 ps.setString(1, sc.getRef_planificados_dividas());
			 ps.setInt(2, sc.getFk_fornecedor());
			 ps.setDouble(3, sc.getTotal_por_pagar_dividas());
			 ps.setDate(4, new java.sql.Date(sc.getData_planificados_dividas().getTimeInMillis()) );
			 ps.setString(5, sc.getCategoria_plan_dividas());
			 ps.setInt(6, sc.getFk_funcionario());
			 ps.setDouble(7, sc.getTotal_kz_plan_dividas());
			 ps.setInt(8, sc.getId_planificar_serv_consu());
			  
			 ps.execute();
			 ps.close();
			 con.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	private List<ServicoConsumivel> planosServicosConsumiveisParaDividas()
	{
		String sql = "SELECT * FROM vwplanospermanentespontual order by id_planificar_serv_consu ";
		List<ServicoConsumivel> lista = new ArrayList<ServicoConsumivel>();
		try {
			 con = Conexao.getConexao();
			 PreparedStatement ps = con.prepareStatement(sql);
			 ResultSet rs = ps.executeQuery();
			 while (rs.next()){
				 ServicoConsumivel sc = new ServicoConsumivel();
				 sc.setId_planificar_serv_consu(rs.getInt("id_planificar_serv_consu"));
				 sc.setFk_fornecedor(rs.getInt("fk_fornecedor"));
				 sc.setFk_funcionario(rs.getInt("fk_funcionario"));
				 sc.setPeriocidade_serv_consu(rs.getInt("periocidade_serv_consu"));
				 sc.setCategoria_plan_dividas(rs.getString("referencia_serv_consu"));
				 sc.setTotal_por_pagar_dividas(rs.getDouble("total_butro_serv_consu"));
				 sc.setTotal_kz_plan_dividas(rs.getDouble("total_butro_serv_consu"));
				 if(rs.getInt("periocidade_serv_consu") == 1)
				 {
					 data = Calendar.getInstance();
					 data.setTime(rs.getDate("data_planificar_pontual"));
					 sc.setData_planificados_dividas(data);
					 
					 data = Calendar.getInstance();
					 data.setTime(rs.getDate("data_planificar_pontual"));
					 sc.setData_inicio__consumiveis(data);
					 
					 data = Calendar.getInstance();
					 data.setTime(rs.getDate("data_planificar_pontual"));
					 sc.setData_fim__consumiveis(data);
				 }
				 else{
					 data = Calendar.getInstance();
					 data.setTime(rs.getDate("dia_data_consumiveis"));
					 sc.setData_planificados_dividas(data);
					 
					 data = Calendar.getInstance();
					 data.setTime(rs.getDate("data_inicio__consumiveis"));
					 sc.setData_inicio__consumiveis(data);
					 
					 data = Calendar.getInstance();
					 data.setTime(rs.getDate("data_fim__consumiveis"));
					 sc.setData_fim__consumiveis(data);
				 }
				 lista.add(sc);
			 }
		} catch (Exception e) {
			e.printStackTrace();
		}
		return lista;
	}
	@Override
	public void gerarDividaServicoConsumivelFornecedor()
	{
		List<ServicoConsumivel> planos = planosServicosConsumiveisParaDividas();
		for (ServicoConsumivel sc : planos) {
			java.sql.Date dia = new java.sql.Date(sc.getData_planificados_dividas().getTimeInMillis());
			if(dia.equals(ft.data_registo()))
			{
				gerarDividaPlanoServicoConsumivel(sc);
			}
		}
	}

}
