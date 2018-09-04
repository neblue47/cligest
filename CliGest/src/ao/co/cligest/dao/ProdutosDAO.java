package ao.co.cligest.dao;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

import ao.co.cligest.entidades.Facturacao;
import ao.co.cligest.entidades.Isencao;
import ao.co.cligest.entidades.Paciente;
import ao.co.cligest.entidades.Produtos;
import ao.co.cligest.util.CarEntidade;
import ao.co.cligest.util.ListaCarrinho;

public class ProdutosDAO {

	/**
	 * @Nelson Diwidi
	 * 27-08-2015
	 * Trabalhando em Encomendas
	 * */
	private Connection con ;
	Formatando ft = new Formatando();
	//1-INSERIR PRODUTOS
			public void addProdutos(Produtos pd)
			{
				String sql = "INSERT INTO tblprodutofarmaceuticos (codigo_barra, requer_receita, nome_comercial, nome_quimico, FK_embalagem, preco_dcompra, preco_dvenda, hora_cadastro_farmaco, FK_funcionario, FK_dosagem, FK_forma_farmaceutica, data_cadastro, inactivo, imagem, FK_taxa, fk_grupo_farmaceutico) VALUES (?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)";
				try {
					con = Conexao.getConexao();
					PreparedStatement cp = con.prepareStatement(sql);
					cp.setString(1, pd.getCodigo_barra());
					cp.setInt(2, pd.getRequer_receita());
					cp.setString(3, pd.getNome_comercial());
					cp.setString(4, pd.getNome_quimico());
					cp.setInt(5, pd.getFK_embalagem());
					cp.setDouble(6, pd.getPreco_dcompra());
					cp.setDouble(7, pd.getPreco_dvenda());
					cp.setString(8, new Formatando().horaAtual());
					cp.setInt(9, pd.getFK_funcionario());
					cp.setInt(10, pd.getFK_dosagem());
					cp.setInt(11, pd.getFK_forma_farmaceutica());
					cp.setDate(12, new Formatando().data_registo());
					cp.setInt(13, pd.getInactivo());
					cp.setString(14, pd.getImagem());
					cp.setInt(15, pd.getFK_taxa());
					cp.setInt(16, pd.getFk_grupo());
					cp.execute();
					cp.close();
					System.out.println("Cadastro de produtos efectuado com sucesso!.."); 
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
/*#############################VERIFICAÇOES PARA ACTUALIZAÇOES####################################################*/
			public String receberValor(String nomeComercial, int chave ){
				String sql = "SELECT * FROM tblprodutofarmaceuticos WHERE nome_comercial like  ? AND  ID_PRODUTO != ?";
				
				con = Conexao.getConexao();
				try {
					PreparedStatement ps = con.prepareStatement(sql);
					ps.setString(1, nomeComercial);
					ps.setInt(2, chave);
					ResultSet rs = ps.executeQuery();
					
					if(rs.next()){
						return rs.getString("nome_comercial");
					}
					
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				return "";
			}
			
			public Integer verificarExistenciaCodigoBarra(String codigBarra, int chave){
				String sql = "SELECT * FROM tblprodutofarmaceuticos WHERE codigo_barra like  ? AND  ID_PRODUTO != ?";
				
				con = Conexao.getConexao();
				try {
					PreparedStatement ps = con.prepareStatement(sql);
					ps.setString(1, codigBarra);
					ps.setInt(2, chave);
					ResultSet rs = ps.executeQuery();
					
					if(rs.next()){
						return rs.getInt("codigo_barra");
					}
				} catch (SQLException e) {
					e.printStackTrace();
				}
				return 0;
			}
			
			public Integer verificarFormaFarmaceutica(int formaFarmaceutica, int chave){
				String sql = "SELECT * FROM tblprodutofarmaceuticos WHERE FK_forma_farmaceutica like  ? AND  ID_PRODUTO != ?";
				
				con = Conexao.getConexao();
				try {
					PreparedStatement ps = con.prepareStatement(sql);
					ps.setInt(1, formaFarmaceutica);
					ps.setInt(2, chave);
					ResultSet rs = ps.executeQuery();
					
					if(rs.next()){
						return rs.getInt("FK_forma_farmaceutica");
					}
				} catch (SQLException e) {
					e.printStackTrace();
				}
				return 0;
			}
			public Integer verificarApresentacao(int apresentacao, int chave){
				String sql = "SELECT * FROM tblprodutofarmaceuticos WHERE FK_dosagem like  ? AND  ID_PRODUTO != ?";
				
				con = Conexao.getConexao();
				try {
					PreparedStatement ps = con.prepareStatement(sql);
					ps.setInt(1, apresentacao);
					ps.setInt(2, chave);
					ResultSet rs = ps.executeQuery();
					
					if(rs.next()){
						return rs.getInt("FK_dosagem");
					}
				} catch (SQLException e) {
					e.printStackTrace();
				}
				return 0;
			}
/*#######################FIM DAS VERIFICAÇÕES#############################################################################*/					
			public int XequeProduto(String termo, String quim, int dosagem, int formaf)
			{
				int ok = 0;
				String sql = "SELECT * FROM tblprodutofarmaceuticos WHERE nome_comercial like ? and nome_quimico like ? and FK_dosagem = ? and FK_forma_farmaceutica = ? ";
				try {
					con = Conexao.getConexao();
					PreparedStatement preparador = con.prepareStatement(sql);
					 preparador.setString(1, termo);
					 preparador.setString(2, quim);
					 preparador.setInt(3, dosagem);
					 preparador.setInt(4, formaf);
					 ResultSet rs = preparador.executeQuery();
					 if(rs.next()){
						 ok = 1;				 
					 }
					 preparador.close();
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
				return ok;
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
	
	public List <Produtos> buscarProdutosPorNome (String prodNome)
	{
		List <Produtos> lista = new ArrayList<Produtos>();
		String sql = "SELECT (CASE WHEN codigo_barra = '' THEN '000000000000' ELSE codigo_barra END) AS codigo_barra, id_produto, nome_comercial, forma_farmaceutica, dosagem, preco_dcompra, preco_dvenda, taxa, fk_grupo_farmaceutico FROM vwlistadeprodutoscadastrados"
				   + " WHERE nome_comercial LIKE ? OR nome_comercial LIKE ? OR codigo_barra LIKE ?";
		try {
			con = Conexao.getConexao();
			 PreparedStatement ps = con.prepareStatement(sql);
			 ps.setString(1, prodNome+"%");
			 ps.setString(2, "%"+prodNome);
			 ps.setString(3, prodNome);
			 ResultSet rs = ps.executeQuery();
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
			 ps.close();
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
	
	public List <Produtos> buscarProdutos_pesq(String nome)
	{
		List <Produtos> lista = new ArrayList<Produtos>();
		String sql = "SELECT (CASE WHEN codigo_barra = '' THEN '000000000000' ELSE codigo_barra END) AS codigo_barra, id_produto, nome_comercial, forma_farmaceutica, dosagem, preco_dcompra, preco_dvenda, taxa, fk_grupo_farmaceutico FROM vwlistadeprodutoscadastrados where nome_comercial LIKE ? or codigo_barra LIKE ?";
		try {
			con = Conexao.getConexao();
			 PreparedStatement preparador = con.prepareStatement(sql);
			 preparador.setString(1, "%"+nome+"%");
			 preparador.setString(2, "%"+nome+"%");
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
	
	public List <Produtos> getProdutoIniciais (String aux)
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
				 pd.setNome_comercial(rs.getString("nome_comercial")+" "+rs.getString("dosagem")+" "+rs.getString("forma_farmaceutica"));
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
	
	public void XeqQtdPrd(String aux){
		String sql = "Select * from tblprodutoemestoque where nome_comercial Like ? and quantidade_produto = 0";
		try {
			con = Conexao.getConexao();
			PreparedStatement ps = con.prepareStatement(sql);
			ps.setString(1, aux);
			ResultSet rs = ps.executeQuery();
			if(rs.next())
			{
				sql = "delete from tblprodutoemestoque where nome_comercial Like ? and quantidade_produto = 0";
				ps = con.prepareStatement(sql);
				ps.setString(1, aux);
				ps.execute();
			}
			ps.close();
			con.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
		
	}
	public List <Produtos> buscarProdutosEmStock (String aux)
	{
		List <Produtos> lista = new ArrayList<Produtos>();
		String sql = "SELECT * FROM vwprodutoemestoque where nome_comercial Like ? or nome_quimico like ? or codigo_barra like ?  group by nome_comercial  order by data_decaducidade asc";
		try {
			con = Conexao.getConexao();
			 PreparedStatement preparador = con.prepareStatement(sql);
			 preparador.setString(1, aux+"%");
			 preparador.setString(2, aux+"%");
			 preparador.setString(3, aux+"%");
			 ResultSet rs = preparador.executeQuery();
			 while(rs.next())
			 {
				 Produtos pd = new Produtos();
				 pd.setId_produto(rs.getInt("id_produto"));
				 pd.setNome_comercial(rs.getString("nome_comercial"));
				 pd.setForma_farmaceutica(rs.getString("forma_farmaceutica"));
				 pd.setValor_dosagem(rs.getString("dosagem"));
				 pd.setPreco_dcompra(rs.getDouble("preco_dcompra"));
				 pd.setPreco_dvenda(rs.getDouble("preco_dvenda"));
				 pd.setTaxas(rs.getDouble("taxa"));
				 pd.setCodigo_barra(rs.getString("codigo_barra"));
				 pd.setNome_quimico(rs.getString("nome_quimico"));
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
	
	public List <Produtos> buscarProdutosEmStockArmazem (String aux)
	{
		List <Produtos> lista = new ArrayList<Produtos>();
		String sql = "SELECT * FROM vwprodutoemestoquearmazem where nome_comercial Like ? or nome_quimico like ? or codigo_barra like ?  group by nome_comercial  order by data_decaducidade asc";
		try {
			con = Conexao.getConexao();
			 PreparedStatement preparador = con.prepareStatement(sql);
			 preparador.setString(1, aux+"%");
			 preparador.setString(2, aux+"%");
			 preparador.setString(3, aux+"%");
			 ResultSet rs = preparador.executeQuery();
			 while(rs.next())
			 {
				 Produtos pd = new Produtos();
				 pd.setId_produto(rs.getInt("id_produto"));
				 pd.setNome_comercial(rs.getString("nome_comercial"));
				 pd.setForma_farmaceutica(rs.getString("forma_farmaceutica"));
				 pd.setValor_dosagem(rs.getString("dosagem"));
				 pd.setPreco_dcompra(rs.getDouble("preco_dcompra"));
				 pd.setPreco_dvenda(rs.getDouble("preco_dvenda"));
				 pd.setTaxas(rs.getDouble("taxa"));
				 pd.setCodigo_barra(rs.getString("codigo_barra"));
				 pd.setNome_quimico(rs.getString("nome_quimico"));
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
	
	public Produtos detalhesReceita(int cod){
		String sql = "select * from vwlistadereceitaafacturar where id_receita = ?";
		Produtos isen = new Produtos();
		try{
			 con = Conexao.getConexao();
			 PreparedStatement preparador = con.prepareStatement(sql);
			 preparador.setInt(1, cod);
			 ResultSet rs = preparador.executeQuery();
			 if(rs.next())
			 {
				 
				 isen.setNum_receita(rs.getString("numero"));
				 isen.setNomeCompleto(rs.getString("nomeCompleto"));
				 isen.setTotal_s(""+rs.getDouble("total"));
				 isen.setQuantidade(rs.getInt("qtd"));
				 isen.setFK_receita(rs.getInt("id_receita"));
				 isen.setFK_paciente(rs.getInt("fk_paciente"));
				 isen.setNome_doutor(rs.getString("n_c_funcionario"));
//				 Calendar data = Calendar.getInstance();
//				 data.setTime(rs.getDate("data_nascimento"));
				 isen.setIdade(rs.getDate("data_nascimento"));
				  
			 }
			 preparador.close();
			 con.close();
		}
		catch (Exception e) {
			e.printStackTrace();
		}
		return isen;
	}
	public List<Produtos> buscarReceitas(){
		List <Produtos> lista = new ArrayList<Produtos>();
		String sql = "select * from vwlistadereceitaafacturar ";
		try{
			 con = Conexao.getConexao();
			 PreparedStatement preparador = con.prepareStatement(sql);
			 ResultSet rs = preparador.executeQuery();
			 while(rs.next())
			 {
				 Produtos isen = new Produtos();
				 isen.setNum_receita(rs.getString("numero"));
				 isen.setNomeCompleto(rs.getString("nomeCompleto"));
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
	
	public List<Produtos> buscarPacientesIsentados(){
		List <Produtos> lista = new ArrayList<Produtos>();
		String sql = "SELECT * FROM vwlistarpacientecomprodutoisentado ";
		try{
			 con = Conexao.getConexao();
			 PreparedStatement preparador = con.prepareStatement(sql);
			 ResultSet rs = preparador.executeQuery();
			 while(rs.next())
			 {
				 Produtos isen = new Produtos();
				 isen.setNum_receita(rs.getString("numero_daisencao"));
				 isen.setNomeCompleto(rs.getString("nomeCompleto"));
				  
				 isen.setQuantidade(rs.getInt("qtd_farmaco_isentado"));
				 isen.setId_isencao(rs.getInt("id_isencao"));
				 isen.setFK_paciente(rs.getInt("FK_paciente"));
				 
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
	
	public List<Produtos> buscarProdutosIsentados(int cod){
		List <Produtos> lista = new ArrayList<Produtos>();
		String sql = "SELECT * FROM vwlistarprodutoisentado where id_isencao = ? group by id_produto";
		try{
			 con = Conexao.getConexao();
			 PreparedStatement preparador = con.prepareStatement(sql);
			 preparador.setInt(1, cod);
			 ResultSet rs = preparador.executeQuery();
			 while(rs.next())
			 {
				 Produtos pd = new Produtos();
				 pd.setId_produto(rs.getInt("id_produto"));
				 pd.setNome_comercial(rs.getString("nome_comercial"));
				 pd.setPreco_dvenda(rs.getDouble("preco_dvenda"));
				 pd.setTaxas(rs.getDouble("total_tx_farm_isent"));
//				 pd.setCodigo_barra(rs.getInt("codigo_barra"));
				 pd.setFK_paciente(rs.getInt("FK_paciente"));
				 pd.setNumero_lote_sere(rs.getString("numero_dolote_sere")); 
				 pd.setQuantidade(rs.getInt("qtd_farmaco_isentado"));
				 pd.setQdt_recebida(rs.getInt("q_emstock"));
				 lista.add(pd);
			 }
			 preparador.close();
			 con.close();
		}
		catch (Exception e) {
			e.printStackTrace();
		}
			 
		return lista;
	}
	
	public List<Produtos> buscarUmaReceitas(int codr){
		List <Produtos> lista = new ArrayList<Produtos>();
		String sql = "select * from vwlistarreceitacomisencaoesemisencao where id_receita = ? group by fk_farmaco";
		try{
			 con = Conexao.getConexao();
			 PreparedStatement preparador = con.prepareStatement(sql);
			 preparador.setInt(1, codr);
			 ResultSet rs = preparador.executeQuery();
			 while(rs.next())
			 {
				 Produtos pd = new Produtos();
				 pd.setId_produto(rs.getInt("fk_farmaco"));
				 pd.setNome_comercial(rs.getString("nome_comercial"));
				 pd.setPreco_dvenda(rs.getDouble("preco_dvenda"));
				 pd.setTaxas(rs.getDouble("taxa_percentual"));
				 pd.setFK_paciente(rs.getInt("FK_paciente"));
				 pd.setNumero_lote_sere(rs.getString("numero_dolote_sere")); 
				 pd.setQuantidade(rs.getInt("quantidade"));
				 pd.setQdt_recebida(rs.getInt("qtd_emstock"));
				 pd.setTotal_s(""+rs.getDouble("ttl_preco_dvenda"));
				 // Dados a isencao do produto
				 pd.setFK_receita(rs.getInt("id_receita"));
				 pd.setQtd_farmaco_isentado(rs.getInt("qtd_farmaco_isentado"));
				 pd.setTota_farmaco_isentado(rs.getDouble("subtotal_isentado"));
				 pd.setTotal_tx_farm_isent(rs.getDouble("txa_emkz_isentado"));
				 lista.add(pd);
				  
			 }
			 preparador.close();
			 con.close();
		}
		catch (Exception e) {
			e.printStackTrace();
		}
			 
		return lista;
	}
	
	public boolean VerificarProdutoEmStock (String aux)
	{ 
		boolean auxs = false;
		String sql = "SELECT * FROM vwprodutoemestoque where nome_comercial Like ? or nome_quimico like ? or codigo_barra like ?  group by nome_comercial order by data_decaducidade asc";
		try {
			 con = Conexao.getConexao();
			 PreparedStatement preparador = con.prepareStatement(sql);
			 preparador.setString(1, aux);
			 preparador.setString(2, aux);
			 preparador.setString(3, aux);
			 ResultSet rs = preparador.executeQuery();
			 if(rs.next())
			   auxs =  true;
			 
			 preparador.close();
			 con.close();
		}  
		catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}	
		return auxs;
	}
	
	public Produtos buscarProdutoEmStock (String aux,int qtd)
	{
		 Produtos pd = null; 
		String sql = "SELECT   id_produto,  preco_dvenda, taxa, taxa_emkz, codigo_barra, requer_receita, numero_dolote_sere,  quantidade_produto,  nome_comercial,  numero_dolote_sere,  fk_id_produto, data_decaducidade, sum(quantidade_produto) qtd_somada FROM vwprodutoemestoque where nome_comercial Like ? or nome_quimico like ? or codigo_barra like ? group by nome_comercial having qtd_somada >= ? order by data_decaducidade asc";
		try {
			con = Conexao.getConexao();
			 PreparedStatement preparador = con.prepareStatement(sql);
			 preparador.setString(1, aux);
			 preparador.setString(2, aux);
			 preparador.setString(3, aux);
			 preparador.setInt(4, qtd);
			 ResultSet rs = preparador.executeQuery();
			 if(rs.next())
			 {
				 pd = new Produtos();
				 pd.setId_produto(rs.getInt("id_produto"));
				 pd.setNome_comercial(rs.getString("nome_comercial"));
				 pd.setPreco_dvenda(rs.getDouble("preco_dvenda"));
				 pd.setTaxas(rs.getDouble("taxa"));
				 pd.setTaxa_emkz(rs.getDouble("taxa_emkz"));
				 pd.setCodigo_barra(rs.getString("codigo_barra"));
				 pd.setRequer_receita(rs.getInt("requer_receita"));
				 pd.setNumero_lote_sere(rs.getString("numero_dolote_sere")); 
				 pd.setQuantidade(rs.getInt("qtd_somada"));
				 pd.setData_de_caducidade(rs.getString("data_decaducidade"));
			 }
			 preparador.close();
			 con.close();
		}  
		catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}	
		return pd;
	}
	
	public Produtos buscarUmProduto (String aux)
	{
		Produtos pd = null;
		String sql = "SELECT * FROM vwlistadeprodutoscadastrados where nome_comercial Like ?  order by id_produto ";
		try {
			con = Conexao.getConexao();
			 PreparedStatement preparador = con.prepareStatement(sql);
			 preparador.setString(1, aux);
			 ResultSet rs = preparador.executeQuery();
			 if(rs.next())
			 {
				 pd = new Produtos();
				 pd.setId_produto(rs.getInt("id_produto"));
				 pd.setNome_comercial(rs.getString("nome_comercial"));
				 pd.setValor_forma_farmaceutica(rs.getString("forma_farmaceutica"));
				 pd.setValor_dosagem(rs.getString("dosagem"));
				 pd.setPreco_dcompra(rs.getDouble("preco_dcompra"));
				 pd.setPreco_dvenda(rs.getDouble("preco_dvenda"));
				 pd.setTaxas(rs.getDouble("taxa"));
				 pd.setTaxa_emkz(rs.getDouble("taxa_emkz"));
				  
			 }
			 preparador.close();
			 con.close();
		}  
		catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		 
		return pd;
}
	
//	2-Visualizar produtos
	public List <Produtos> buscarFarmaceutico ()
	{
		List <Produtos> lista = new ArrayList<Produtos>();
		String sql = "SELECT * FROM tblformafarmaceutica ORDER BY id_forma_farmaceutica = 0 desc, forma_farmaceutica asc";
		try {
			con = Conexao.getConexao();
			 PreparedStatement preparador = con.prepareStatement(sql);
			 ResultSet rs = preparador.executeQuery();
			 while(rs.next())
			 {
				 Produtos pd = new Produtos();
				 pd.setId_forma_farmaceutica(rs.getInt("id_forma_farmaceutica"));
				 pd.setValor_forma_farmaceutica(rs.getString("forma_farmaceutica"));
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
	
//	2-Visualizar produtos
	public List<Produtos> buscarTaxa() {
		List<Produtos> lista = new ArrayList<Produtos>();
		String sql = "SELECT * FROM tbltaxas";
		try {
			con = Conexao.getConexao();
			PreparedStatement preparador = con.prepareStatement(sql);
			ResultSet rs = preparador.executeQuery();
			while (rs.next()) {
				Produtos pd = new Produtos();
				pd.setId_taxa(rs.getInt("id_taxa"));
				pd.setTipo_de_taxa(rs.getString("tipo_de_taxa"));
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
	
//	2-Visualizar produtos
	public List <Produtos> buscarApresentacao()
	{
		List <Produtos> lista = new ArrayList<Produtos>();
		String sql = "SELECT * FROM tbldosagem ORDER BY id_dosagem = 0 desc, dosagem asc";
		try {
			con = Conexao.getConexao();
			 PreparedStatement preparador = con.prepareStatement(sql);
			 ResultSet rs = preparador.executeQuery();
			 while(rs.next())
			 {
				 Produtos pd = new Produtos();
				 pd.setId_dosagem(rs.getInt("id_dosagem"));
				 pd.setValor_dosagem(rs.getString("dosagem"));
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
	
//	  6-Editar serviï¿½o
	public Produtos edit_produto(int ed)
	{
		Produtos pd = null;
		String sql = "SELECT * FROM vwlistadeprodutoscadastrados WHERE id_produto = ?";
		try {
			con = Conexao.getConexao();
			PreparedStatement preparador = con.prepareStatement(sql);
			 preparador.setInt(1, ed);
			 ResultSet rs = preparador.executeQuery();
			 while(rs.next())
			 {
				 pd = new Produtos();
				 pd.setId_produto(rs.getInt("id_produto"));
				 pd.setCodigo_barra(rs.getString("codigo_barra"));
				 pd.setNome_comercial(rs.getString("nome_comercial"));
				 pd.setNome_quimico(rs.getString("nome_quimico"));
				 pd.setValor_embalagem(rs.getString("embalagem"));
				 pd.setPreco_dcompra(rs.getDouble("preco_dcompra"));
				 pd.setPreco_dvenda(rs.getDouble("preco_dvenda"));
				 pd.setValor_dosagem(rs.getString("dosagem"));
				 pd.setRequer_receita(rs.getInt("requer_receita"));
				 pd.setValor_forma_farmaceutica(rs.getString("forma_farmaceutica"));
				 pd.setInactivo(rs.getInt("inactivo"));
				 pd.setImagem(rs.getString("imagem"));
				 pd.setTipo_de_taxa(rs.getString("tipo_de_taxa"));
				 pd.setFk_grupo(rs.getInt("fk_grupo_farmaceutico"));
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
		return pd;
	}

	
//	5- Alterar serviï¿½o
	public void alt_produto(Produtos rs)
	{
		String sql = "UPDATE tblprodutofarmaceuticos SET codigo_barra =?, requer_receita =?, nome_comercial =?, nome_quimico =?, FK_embalagem =?, preco_dcompra =?, preco_dvenda =?, FK_dosagem =?, FK_forma_farmaceutica =?, inactivo =?, imagem =?, FK_taxa =?, fk_grupo_farmaceutico =? WHERE id_produto = ?";
		try {
			con = Conexao.getConexao();
			PreparedStatement pd = con.prepareStatement(sql);
			
			pd.setString(1, rs.getCodigo_barra());
			pd.setInt(2, rs.getRequer_receita());
			pd.setString(3, rs.getNome_comercial());
			pd.setString(4, rs.getNome_quimico());
			pd.setInt(5, rs.getFK_embalagem());
			pd.setDouble(6, rs.getPreco_dcompra());
			pd.setDouble(7, rs.getPreco_dvenda());
			pd.setInt(8, rs.getFK_dosagem());
			pd.setInt(9, rs.getFK_forma_farmaceutica());
			pd.setInt(10, rs.getInactivo());
			pd.setString(11, rs.getImagem());
			pd.setInt(12, rs.getFK_taxa());
			pd.setInt(13, rs.getFk_grupo());
			pd.setInt(14, rs.getId_produto());
			pd.execute();
			pd.close();
			
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

	//3-Eliminar Serviï¿½o
			public void del_produto (int id)
			{
				String sql = "DELETE FROM tblprodutofarmaceuticos  WHERE id_produto=?";
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
						
			public List <Produtos> buscarLocal ()
			{
				List <Produtos> lista = new ArrayList<Produtos>();
				String sql = "SELECT * FROM tbllocaldearmazenamento";
				try {
					con = Conexao.getConexao();
					 PreparedStatement preparador = con.prepareStatement(sql);
					 ResultSet rs = preparador.executeQuery();
					 while(rs.next())
					 {
						 Produtos pd = new Produtos();
						 pd.setId_local_dearmazenamento(rs.getInt("id_local_dearmazenamento"));
						 pd.setLocaldearmazenamento(rs.getString("localdearmazenamento"));
						 lista.add(pd);
					 }
					 preparador.close();
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
				return lista;
		}
//			Farmácia buscarLocal
			public List <Produtos> buscarLocalFar ()
			{
				List <Produtos> lista = new ArrayList<Produtos>();
				String sql = "SELECT * FROM tbllocaldearmazenamentofarmacia";
				try {
					con = Conexao.getConexao();
					 PreparedStatement preparador = con.prepareStatement(sql);
					 ResultSet rs = preparador.executeQuery();
					 while(rs.next())
					 {
						 Produtos pd = new Produtos();
						 pd.setId_local_dearmazenamento(rs.getInt("id_local_dearmazenamento_farmacia"));
						 pd.setLocaldearmazenamento(rs.getString("localdearmazenamento_farmacia"));
						 lista.add(pd);
					 }
					 preparador.close();
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
				return lista;
		}
			
			public List <Produtos> buscarAlaBlocoPorLocal(int dado)
			{
				List <Produtos> lista = new ArrayList<Produtos>();
				String sql = "SELECT * FROM tblalabloco where FK_local_dearmazenamento = ?";
				try {
					con = Conexao.getConexao();
					 PreparedStatement preparador = con.prepareStatement(sql);
					 preparador.setInt(1, dado);
					 ResultSet rs = preparador.executeQuery();
					 while(rs.next())
					 {
						 Produtos pd = new Produtos();
						 pd.setId_alabloco(rs.getInt("id_alabloco"));
						 pd.setAlabloco_doproduto(rs.getString("alabloco_doproduto"));
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
			
			public List <Produtos> buscarAlaBlocoPorLocalFar(int dado)
			{
				List <Produtos> lista = new ArrayList<Produtos>();
				String sql = "SELECT * FROM tblalablocofarmacia where FK_local_dearmazenamento_farmacia = ?";
				try {
					con = Conexao.getConexao();
					 PreparedStatement preparador = con.prepareStatement(sql);
					 preparador.setInt(1, dado);
					 ResultSet rs = preparador.executeQuery();
					 while(rs.next())
					 {
						 Produtos pd = new Produtos();
						 pd.setId_alabloco(rs.getInt("id_alabloco_farmacia"));
						 pd.setAlabloco_doproduto(rs.getString("alabloco_doproduto_farmacia"));
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
			
			public List <Produtos> buscarMontraPorAlcaBloco(int dado)
			{
				List <Produtos> lista = new ArrayList<Produtos>();
				String sql = "SELECT * FROM tblmontra where FK_alabloco = ?";
				try {
					con = Conexao.getConexao();
					 PreparedStatement preparador = con.prepareStatement(sql);
					 preparador.setInt(1, dado);
					 ResultSet rs = preparador.executeQuery();
					 while(rs.next())
					 {
						 Produtos pd = new Produtos();
						 pd.setId_montra_deproduto(rs.getInt("id_montra_deproduto"));
						 pd.setMontra_deproduto(rs.getString("montra_deproduto"));
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
//			Farmacia buscarMontraPorAlcaBlocoFar
			public List <Produtos> buscarMontraPorAlcaBlocoFar(int dado)
			{
				List <Produtos> lista = new ArrayList<Produtos>();
				String sql = "SELECT * FROM tblmontrafarmacia where FK_alabloco_farmacia = ?";
				try {
					con = Conexao.getConexao();
					 PreparedStatement preparador = con.prepareStatement(sql);
					 preparador.setInt(1, dado);
					 ResultSet rs = preparador.executeQuery();
					 while(rs.next())
					 {
						 Produtos pd = new Produtos();
						 pd.setId_montra_deproduto(rs.getInt("id_montra_deproduto_farmacia"));
						 pd.setMontra_deproduto(rs.getString("montra_deproduto_farmacia"));
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
			
			public List <Produtos> buscarPratileiraPorMontra(int dados)
			{
				List <Produtos> lista = new ArrayList<Produtos>();
				String sql = "SELECT * FROM tblprartileira WHERE FK_montra_deproduto =?";
				try {
					con = Conexao.getConexao();
					 PreparedStatement preparador = con.prepareStatement(sql);
					 preparador.setInt(1, dados);
					 ResultSet rs = preparador.executeQuery();
					 while(rs.next())
					 {
						 Produtos pd = new Produtos();
						 pd.setId_prartileira(rs.getInt("id_prartileira"));
						 pd.setPrartileira(rs.getString("prartileira"));
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

			// FarmaciabuscarPratileiraPorMontra
			public List <Produtos> buscarPratileiraPorMontraFar(int dados)
			{
				List <Produtos> lista = new ArrayList<Produtos>();
				String sql = "SELECT * FROM tblprartileirafarmacia WHERE FK_montra_deproduto_farmacia =?";
				try {
					con = Conexao.getConexao();
					 PreparedStatement preparador = con.prepareStatement(sql);
					 preparador.setInt(1, dados);
					 ResultSet rs = preparador.executeQuery();
					 while(rs.next())
					 {
						 Produtos pd = new Produtos();
						 pd.setId_prartileira(rs.getInt("id_prartileira_farmacia"));
						 pd.setPrartileira(rs.getString("prartileira_farmacia"));
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
			
			// Adicionando Metodos para implementar em Encomendas
			
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
	
	public int novaEncomenda(Produtos pd)
	{
		int ultimoId=0;
		String sql = "INSERT INTO tblnumerodaencomenda (data_decriacao,hora_decriacao,num_daencomenda) VALUES (?,?,?)";
		try {
			con = Conexao.getConexao();
			PreparedStatement cp = con.prepareStatement(sql);
			cp.setDate  (1, ft.data_registo());
			cp.setString(2, ft.getHoraAtual());
			cp.setString(3, pd.getNumEncomenda());
			cp.execute();
			ResultSet rs = cp.executeQuery("SELECT LAST_INSERT_ID()");
			if(rs.next())
				ultimoId = rs.getInt(1);
			cp.close();
//			System.out.println("Nova Encomenda efectuado com sucesso!.."); 
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
		return ultimoId;
	}
	
	public void Encomendas(Produtos pd)
	{
		String sql = "INSERT INTO tblencomenda (FK_num_encomenda, FK_fornecedor,  FK_funcionario) VALUES (?,?,?)";
		try {
			con = Conexao.getConexao();
			PreparedStatement cp = con.prepareStatement(sql);
			cp.setInt   (1, pd.getId_numEncomenda());
			cp.setInt	(2, pd.getFK_fornecedor());
			cp.setInt	(3, pd.getFK_funcionario());
			cp.execute();
			cp.close();
//			System.out.println("Cadastro de Encomenda efectuado com sucesso!.."); 
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
	
	public void EncomendaRecebida(Produtos pd)
	{
		String sql = "INSERT INTO tblencomendarecebida (FK_encomenda, FK_produto, numero_lote_sere, data_expiracao,data_darececao,hora_recebida,nao_se_aplica_encomenda,quantidade_recebida,FK_funcionario,preco_decompra,alocado) VALUES (?,?,?,?,?,?,?,?,?,?,?)";
		try {
			con = Conexao.getConexao();
			PreparedStatement cp = con.prepareStatement(sql);
			cp.setInt   (1, pd.getId_numEncomenda());
			cp.setInt	(2, pd.getId_produto());
			cp.setString(3, pd.getNumero_lote_sere());
			cp.setDate  (4, new Date(pd.getData_expiracao().getTimeInMillis()));
			cp.setDate	(5,  ft.data_registo());
			cp.setString(6,  ft.getHoraAtual());
			cp.setInt(7, pd.getnSeAplica());
			cp.setInt(8, pd.getQdt_recebida());
			cp.setInt(9, pd.getFK_funcionario());
			cp.setDouble(10, pd.getPreco_dcompra());
			cp.setInt(11, 0);
			cp.execute();
			cp.close();
//			System.out.println("Cadastro de Encomenda efectuado com sucesso!.."); 
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
	
	public void EncomendaRecebidaAtualizar(Produtos pd)
	{
		String sql = "update tblencomendarecebida set numero_lote_sere = ?, data_expiracao = ?,data_darececao = ?,hora_recebida = ?,nao_se_aplica_encomenda = ?,quantidade_recebida = ?,FK_funcionario = ?, preco_decompra = ?, alocado =? where FK_encomenda = ? and FK_produto = ? ";
		try {
			con = Conexao.getConexao();
			PreparedStatement cp = con.prepareStatement(sql);
			cp.setString(1, pd.getNumero_lote_sere());
			cp.setDate  (2, new Date(pd.getData_expiracao().getTimeInMillis()));
			cp.setDate	(3,  ft.data_registo());
			cp.setString(4,  ft.getHoraAtual());
			cp.setInt	(5, pd.getnSeAplica());
			cp.setInt	(6, pd.getQdt_recebida());
			cp.setInt	(7, pd.getFK_funcionario());
			cp.setDouble(8, pd.getPreco_dcompra());
			cp.setInt	(9, 0);
			cp.setInt   (10, pd.getId_numEncomenda());
			cp.setInt	(11, pd.getId_produto());
			cp.execute();
			cp.close();
//			System.out.println("Atualizado de Encomenda efectuado com sucesso!.."); 
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
	
	public void EncomendasProdutos(Produtos pd)
	{
		String sql = "INSERT INTO tblprodutoencomendado (FK_num_encomenda, FK_id_produto, preco_ao_encomendar, qt_unitaria, enco_total,FK_laboratorio, metodo_transportacao,qt_recebida) VALUES (?,?,?,?,?,?,?,?)";
		try {
			con = Conexao.getConexao();
			PreparedStatement cp = con.prepareStatement(sql);
			cp.setInt	(1, pd.getId_numEncomenda());
			cp.setInt	(2, pd.getId_produto());
			cp.setDouble(3, pd.getPreco_dcompra());
			cp.setInt	(4, pd.getQuantidade());
			cp.setDouble(5, pd.getTotalEncomenda());
			cp.setInt 	(6, pd.getFK_laboratorio());
			cp.setString(7, pd.getmTransportacao());
			cp.setInt(8, 0);
			cp.execute();
			cp.close();
//			System.out.println("Cadastro de produtos efectuado Encomenda com sucesso!.."); 
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
	
	public int numero() { 

		int fun = 0;
		String sql = "SELECT count(id_num_difinventario) numero FROM tblnumerodadiferencanoinventario";
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
	
	public int novoAjuste(Produtos pd)
	{
		int ultimoId=0;
		String sql = "INSERT INTO tblnumerodadiferencanoinventario (numero_dif_inventario,data_numero_h_ajuste,fk_funcionario) VALUES (?,?,?)";
		try {
			con = Conexao.getConexao();
			PreparedStatement cp = con.prepareStatement(sql);
			cp.setString(1, pd.getNum_difnoinventario());
			cp.setDate 	(2, new Formatando().data_registo());
			cp.setInt(3, pd.getFK_funcionario());
			cp.execute();
			ResultSet rs = cp.executeQuery("SELECT LAST_INSERT_ID()");
			if(rs.next())
				ultimoId = rs.getInt(1);
			cp.close();
//			System.out.println("Novo Ajuste efectuado com sucesso!.."); 
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
		return ultimoId;
	}
	
	public int novoAjusteFarmacia(Produtos pd)
	{
		int ultimoId=0;
		String sql = "INSERT INTO tblhistoricodeajustesfarmacia (numero_histo_ajust_farmacia,data_histo_ajust_farmacia,fk_funcionario,total_ajustado__histo_ajust_farmacia) VALUES (?,?,?,?)";
		try {
			con = Conexao.getConexao();
			PreparedStatement cp = con.prepareStatement(sql);
			cp.setString(1, pd.getNum_difnoinventario());
			cp.setDate 	(2, new Formatando().data_registo());
			cp.setInt(3, pd.getFK_funcionario());
			cp.setDouble(4, pd.getTotalEncomenda());
			cp.execute();
			ResultSet rs = cp.executeQuery("SELECT LAST_INSERT_ID()");
			if(rs.next())
				ultimoId = rs.getInt(1);
			cp.close();
//			System.out.println("Novo Ajuste efectuado com sucesso!.."); 
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
		return ultimoId;
	}
	
	public void EstoqueAjuste(Produtos pd)
	{
		String sql = "INSERT INTO tbldiferencanoinventario (FK_num_difnoinventario,FK_id_produto,lote_sere,diferenca_negativa,diferenca_positiva,data_expiracao,preco_negativo,preco_positivo) VALUES (?,?,?,?,?,?,?,?)";
		try {
			con = Conexao.getConexao();
			PreparedStatement cp = con.prepareStatement(sql);
			cp.setInt	(1, pd.getFK_num_difnoinventario());
			cp.setInt	(2, pd.getId_produto());
			cp.setString(3, pd.getNumero_lote_sere());
			cp.setInt   (4, pd.getValor_negativo());
			cp.setInt   (5, pd.getValor_positivo());
			cp.setDate 	(6, new Formatando().data_registo());
			cp.setDouble(7, pd.getPreco_dcompra());
			cp.setDouble(8, pd.getPreco_dvenda());
			cp.execute();
			cp.close();
//			System.out.println("Ajuste de produtos efectuado Encomenda com sucesso!.."); 
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
	
	public void EstoqueAjusteFarmacia(Produtos pd)
	{
		String sql = "INSERT INTO tblhistoricodeajustesfarmacialinha(fk_produto,fk_histo_ajust_farma_linha,preco_compra_histo_ajust_farma_linha,lote_histo_ajust_farma_linha,data_expirar_histo_ajust_farma_linha,positivo_histo_ajust_farma_linha,negativo_histo_ajust_farma_linha) VALUES (?,?,?,?,?,?,?)";
		try {
			con = Conexao.getConexao();
			PreparedStatement cp = con.prepareStatement(sql);
			cp.setInt	(1, pd.getId_produto());
			cp.setInt	(2, pd.getFK_num_difnoinventario());
			cp.setDouble(3, pd.getPreco_dcompra());
			cp.setString(4, pd.getNumero_lote_sere());
			cp.setDate 	(5, new Formatando().data_registo());
			cp.setInt(6, pd.getValor_positivo());
			cp.setInt(7, pd.getValor_negativo());
			cp.execute();
			cp.close();
//			System.out.println("Ajuste de produtos efectuado Encomenda com sucesso!.."); 
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
	
	public void AtualizarPreco(Produtos p) {
		String sql = "SELECT * FROM vwlistadeprodutoscadastrados where id_produto = ?";
		Produtos pd = new Produtos();
		try {
			con = Conexao.getConexao();
			PreparedStatement preparador = con.prepareStatement(sql);
			preparador.setInt(1, p.getId_produto());
			ResultSet rs = preparador.executeQuery();
			if (rs.next()) {
				pd.setPreco_dcompra(rs.getDouble("preco_dcompra"));
			}
			if(p.getPreco_dcompra() != pd.getPreco_dcompra()){
				sql = "update tblprodutofarmaceuticos set preco_dcompra = ? where id_produto = ?";
				preparador = con.prepareStatement(sql);
				preparador.setDouble(1, p.getPreco_dcompra());
				preparador.setInt(2, p.getId_produto());
				preparador.execute();
//				System.out.println("Atualizado o Preço de Compra com sucesso!..");
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
			if(p.getQuantidade() == 0){
				sql = "DELETE FROM tblprodutoemestoque WHERE quantidade_produto = ? and fk_id_produto = ? and numero_dolote_sere like ?";
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
	
	
	public void AtualizarProdutoEstoqueArmazem(Produtos p) {
		String sql = "SELECT * FROM tblprodutoemestoquearmazem where fk_id_produto = ? and numero_dolote_sere like ?";
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
				sql = "update tblprodutoemestoquearmazem set quantidade_produto = ? where fk_id_produto = ? and numero_dolote_sere like ?";
				preparador = con.prepareStatement(sql);
				preparador.setInt(1, p.getQuantidade());
				preparador.setInt(2, p.getId_produto());
				preparador.setString(3, p.getNumero_lote_sere());
				preparador.execute();
//				System.out.println("Atualizado a Quantidade em Stock com sucesso!..");
			}
			if(p.getQuantidade() == 0){
				sql = "DELETE FROM tblprodutoemestoquearmazem  WHERE quantidade_produto = ? and fk_id_produto = ? and numero_dolote_sere like ? ";
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
	
	
	public List<Produtos> buscarEncomendas() {
		List<Produtos> listaEncomenda = new ArrayList<Produtos>();
		String sql = "SELECT * FROM vwlistadeencomendas order by fk_num_encomenda DESC";
		try {
			con = Conexao.getConexao();
			PreparedStatement preparador = con.prepareStatement(sql);
			ResultSet rs = preparador.executeQuery();
			while (rs.next()) {
				Produtos pd = new Produtos();
				pd.setId_numEncomenda(rs.getInt("fk_num_encomenda"));
				pd.setNumEncomenda(rs.getString("num_daencomenda"));
				pd.setFornecedor(rs.getString("empresa"));
				pd.setNomFuncionario(rs.getString("funcionario"));
				pd.setQuantidade(rs.getInt("quantidade"));
				pd.setTotalEncomenda(rs.getDouble("total"));
				Calendar data = Calendar.getInstance();
				data.setTime(rs.getDate("data_decriacao"));
				pd.setDataCriacao(data);
				if(rs.getInt("quantidade") == rs.getInt("balanco"))
				{
					pd.setBalanco(0);
				}else{
					pd.setBalanco(rs.getInt("balanco"));
				}
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
	
	public List<Produtos> buscarProdutosEncomendados(String cod) {
		List<Produtos> lista = new ArrayList<Produtos>();
		String sql = "SELECT * FROM vwlistadoprodutoencomendado where fk_num_encomenda = ? ";
		try {
			con = Conexao.getConexao();
			PreparedStatement preparador = con.prepareStatement(sql);
			preparador.setString(1, cod);
			ResultSet rs = preparador.executeQuery();
			while (rs.next()) {
				Produtos pd = new Produtos();
				pd.setFK_num_encomenda(rs.getInt("fk_num_encomenda"));
				pd.setId_produto(rs.getInt("FK_id_produto"));
				pd.setNome_comercial(rs.getString("nome_comercial"));
				pd.setQuantidade(rs.getInt("qt_unitaria"));
				pd.setTotalEncomenda(rs.getDouble("enco_total"));
				Calendar data = Calendar.getInstance();
				data.setTime(rs.getDate("data_encomenda"));
				pd.setDataCriacao(data);
				pd.setNomFuncionario(rs.getString("colaborador"));
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
	
	public List<Produtos> buscarProdutosEncomendados(int cod) {
		List<Produtos> lista = new ArrayList<Produtos>();
		String sql = "SELECT FK_num_encomenda, (CASE WHEN codigo_barra = '' THEN '000000000000' ELSE codigo_barra END) AS codigo_barra, fk_id_produto, nome_comercial, qt_unitaria, balanco, enco_total, data_decriacao, preco_ao_encomendar FROM vwlistadeprodutosporencomendasaconfirmar where FK_num_encomenda = ? and qt_unitaria <> qt_recebida order by nome_comercial";
		try {
			con = Conexao.getConexao();
			PreparedStatement preparador = con.prepareStatement(sql);
			preparador.setInt(1, cod);
			ResultSet rs = preparador.executeQuery();
			while (rs.next()) {
				Produtos pd = new Produtos();
				pd.setFK_num_encomenda(rs.getInt("FK_num_encomenda"));
				pd.setCodigo_barra(rs.getString("codigo_barra"));
				pd.setId_produto(rs.getInt("fk_id_produto"));
				pd.setNome_comercial(rs.getString("nome_comercial"));
				pd.setQuantidade(rs.getInt("qt_unitaria"));
				pd.setQdt_recebida(rs.getInt("balanco"));
				pd.setTotalEncomenda(rs.getDouble("enco_total"));
				Calendar data = Calendar.getInstance();
				data.setTime(rs.getDate("data_decriacao"));
				pd.setDataCriacao(data);
//				pd.setCNomFuncionario(rs.getString("colaborador"));
				pd.setPreco_dcompra(rs.getDouble("preco_ao_encomendar"));
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
	
	public Produtos buscarProdutoEncomendado(int cod,int codp) {
		Produtos pd = new Produtos();
		String sql = "SELECT * FROM vwlistadeprodutosporencomendasaconfirmar where FK_num_encomenda = ? and fk_id_produto = ?";
		try {
			con = Conexao.getConexao();
			PreparedStatement preparador = con.prepareStatement(sql);
			preparador.setInt(1, cod);
			preparador.setInt(2, codp);
			ResultSet rs = preparador.executeQuery();
			if(rs.next())
			{	
				pd.setId_produto(rs.getInt("fk_id_produto"));
				pd.setNome_comercial(rs.getString("nome_comercial"));
				pd.setQuantidade(rs.getInt("qt_unitaria"));
				pd.setQdt_recebida(rs.getInt("balanco"));
				pd.setTotalEncomenda(rs.getDouble("enco_total"));
				pd.setPreco_dcompra(rs.getDouble("preco_ao_encomendar"));
				Calendar data = Calendar.getInstance();
				data.setTime(rs.getDate("data_decriacao"));
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
	
//	2-Visualizar produtos
	public List <Produtos> buscarListaDeEncomendado()
	{
		List <Produtos> lista = new ArrayList<Produtos>();
		String sql = "SELECT * FROM vwlistadeencomendasconfirmadas";
		try {
			con = Conexao.getConexao();
			 PreparedStatement preparador = con.prepareStatement(sql);
			 ResultSet rs = preparador.executeQuery();
			 while(rs.next())
			 {
				 Produtos pd = new Produtos();
				 pd.setFK_num_encomenda(rs.getInt("FK_num_encomenda"));
				 pd.setNum_encomenda(rs.getString("num_daencomenda"));
				 pd.setFuncionario(rs.getString("funcionario"));
				 pd.setTotal(rs.getInt("total"));
				 pd.setEmpresa(rs.getString("empresa"));
				 pd.setData_encomenda(rs.getString("data_darececao"));
				 pd.setQuantidade(rs.getInt("quantidade"));
				 lista.add(pd);
			 }
			 preparador.close();
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
		return lista;
}
	
	
	public List <Produtos> buscarListaDeEncomendadoAl()
	{
		List <Produtos> lista = new ArrayList<Produtos>();
		String sql = "SELECT * FROM vwlistadeencomendasconfirmadas where alocado <> 1";
		try {
			con = Conexao.getConexao();
			 PreparedStatement preparador = con.prepareStatement(sql);
			 ResultSet rs = preparador.executeQuery();
			 while(rs.next())
			 {
				 Produtos pd = new Produtos();
				 pd.setFK_num_encomenda(rs.getInt("FK_num_encomenda"));
				 pd.setNum_encomenda(rs.getString("num_daencomenda"));
				 pd.setFuncionario(rs.getString("funcionario"));
				 pd.setTotal(rs.getInt("total"));
				 pd.setEmpresa(rs.getString("empresa"));
				 pd.setData_encomenda(rs.getString("data_darececao"));
				 pd.setQuantidade(rs.getInt("quantidade"));
				 lista.add(pd);
			 }
			 preparador.close();
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
		return lista;
}
	
	//1-INSERIR PRODUTOS EM ESTOQUE
	public void AlocarProdutosEmEstoque(Produtos pd)
	{
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
	
	public List<Produtos> buscarProdutosRecebidos(int cod) {
		List<Produtos> lista = new ArrayList<Produtos>();
		String sql = "SELECT fk_encomenda, (CASE WHEN codigo_barra = '' THEN '000000000000' ELSE codigo_barra END) AS codigo_barra, FK_produto, nome_comercial, quantidade_recebida, total, data_expiracao, funcionario, numero_lote_sere, num_daencomenda FROM vwlistadeprodutosconfirmadoaalocar where fk_encomenda = ? and alocado <> 1 order by nome_comercial asc";
		try {
			con = Conexao.getConexao();
			PreparedStatement preparador = con.prepareStatement(sql);
			preparador.setInt(1, cod);
			ResultSet rs = preparador.executeQuery();
			while(rs.next()) {
				Produtos pd = new Produtos();
				pd.setFK_num_encomenda(rs.getInt("fk_encomenda"));
				pd.setCodigo_barra(rs.getString("codigo_barra"));
				pd.setId_produto(rs.getInt("FK_produto"));
				pd.setNome_comercial(rs.getString("nome_comercial"));
				pd.setQdt_recebida(rs.getInt("quantidade_recebida"));
				pd.setTotal(rs.getInt("total"));
				Calendar data = Calendar.getInstance();
				data.setTime(rs.getDate("data_expiracao"));
				pd.setData_expiracao(data);
				pd.setNomFuncionario(rs.getString("funcionario"));
				pd.setNumero_lote_sere(rs.getString("numero_lote_sere"));
				pd.setNum_encomenda(rs.getString("num_daencomenda"));
				lista.add(pd);
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
		return lista;
	}
	
	public ListaCarrinho EditProdutosEncomendados(int cod) {
		 
		ListaCarrinho lsEncomenda = new ListaCarrinho();;
		String sql = "SELECT * FROM vwlistadoprodutoencomendado where fk_num_encomenda = ? ";
		try {
			con = Conexao.getConexao();
			PreparedStatement preparador = con.prepareStatement(sql);
			preparador.setInt(1, cod);
			ResultSet rs = preparador.executeQuery();
			while(rs.next()) {
				CarEntidade pd = new CarEntidade();
				pd.setCod_id(rs.getString("FK_id_produto"));
				pd.setFarmaco(rs.getString("nome_comercial"));
				pd.setDosage(rs.getString("dosagem"));
				pd.setPreco_c(rs.getString("enco_total"));
				pd.setQuantdd(rs.getString("qt_unitaria"));
				pd.setFk_laboratorio(rs.getInt("FK_laboratorio"));
				pd.setFk_fornecedor(rs.getInt("FK_fornecedor"));
				lsEncomenda.adFarmacos(pd);
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
		return lsEncomenda;
	}
	public List<Produtos> buscarProdutosRecebidosLista(int cod) {
		List<Produtos> lista = new ArrayList<Produtos>();
		String sql = "SELECT FK_encomenda, (CASE WHEN codigo_barra = '' THEN '000000000000' ELSE codigo_barra END) AS codigo_barra, FK_produto, nome_comercial, quantidade_recebida, total, data_expiracao, funcionario, numero_lote_sere, num_daencomenda, preco_dcompra,data_darececao FROM vwlistadeprodutosconfirmadoaalocar where fk_encomenda = ? order by nome_comercial asc";
		try {
			con = Conexao.getConexao();
			PreparedStatement preparador = con.prepareStatement(sql);
			preparador.setInt(1, cod);
			ResultSet rs = preparador.executeQuery();
			while(rs.next()) {
				Produtos pd = new Produtos();
				pd.setFK_num_encomenda(rs.getInt("FK_encomenda"));
				pd.setCodigo_barra(rs.getString("codigo_barra"));
				pd.setId_produto(rs.getInt("FK_produto"));
				pd.setPreco_dcompra(rs.getDouble("preco_dcompra"));
				pd.setNome_comercial(rs.getString("nome_comercial"));
				pd.setQdt_recebida(rs.getInt("quantidade_recebida"));
				pd.setTotal(rs.getInt("total"));
				Calendar data = Calendar.getInstance();
				data.setTime(rs.getDate("data_expiracao"));
				pd.setData_expiracao(data);
				pd.setNomFuncionario(rs.getString("funcionario"));
				pd.setNumero_lote_sere(rs.getString("numero_lote_sere"));
				pd.setNum_encomenda(rs.getString("num_daencomenda"));
				data.setTime(rs.getDate("data_darececao"));
				pd.setDataCriacao(data);
				lista.add(pd);
			}
			preparador.close();
			con.close();
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
		return lista;
	}
	
	public Produtos buscarProdutoRecebido(int cod,int codp) {
		Produtos pd = new Produtos();
		String sql = "SELECT * FROM vwlistadeprodutosconfirmadoaalocar where fk_encomenda = ? and FK_produto = ? ";
		try {
			con = Conexao.getConexao();
			PreparedStatement preparador = con.prepareStatement(sql);
			preparador.setInt(1, cod);
			preparador.setInt(2, codp);
			ResultSet rs = preparador.executeQuery();
			while(rs.next()) {
				
				pd.setFK_num_encomenda(rs.getInt("fk_encomenda"));
				pd.setId_produto(rs.getInt("FK_produto"));
				pd.setNome_comercial(rs.getString("nome_comercial"));
				pd.setQdt_recebida(rs.getInt("quantidade_recebida"));
				pd.setTotal(rs.getInt("total"));
//				Calendar data = Calendar.getInstance();
//				data.setTime(rs.getDate("data_expiracao"));
//				pd.setData_expiracao(data);
				pd.setData_de_caducidade(rs.getString("data_expiracao"));
				pd.setNomFuncionario(rs.getString("funcionario"));
				pd.setNumero_lote_sere(rs.getString("numero_lote_sere"));
				pd.setNum_encomenda(rs.getString("num_daencomenda"));
				 
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
	
	public Produtos checkBalancoProdutoEncomendado(int cod,int codp) {
		Produtos pd = new Produtos();
		String sql = "SELECT * FROM tblencomendarecebida where fk_encomenda = ? and fk_produto = ?";
		try {
			con = Conexao.getConexao();
			PreparedStatement preparador = con.prepareStatement(sql);
			preparador.setInt(1, cod);
			preparador.setInt(2, codp);
			ResultSet rs = preparador.executeQuery();
			if (rs.next()) {
				
				pd.setId_produto(rs.getInt("FK_produto"));
				pd.setQdt_recebida(rs.getInt("quantidade_recebida"));
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
	
	public int checkProdutoAlocado(Produtos p) {
		int aux = 0;
		String sql = "SELECT * FROM tblencomendarecebida where fk_encomenda = ? and fk_produto = ?";
		try {
			con = Conexao.getConexao();
			PreparedStatement preparador = con.prepareStatement(sql);
			preparador.setInt(1, p.getId_numEncomenda());
			preparador.setInt(2, p.getId_produto());
			ResultSet rs = preparador.executeQuery();
			if (rs.next()) {
				aux = rs.getInt("alocado");						
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
	
	public int getQDtProdutoNAlocado(Produtos p) {
		int aux = 0;
		String sql = "SELECT * FROM tblencomendarecebida where fk_encomenda = ? and fk_produto = ?";
		try {
			con = Conexao.getConexao();
			PreparedStatement preparador = con.prepareStatement(sql);
			preparador.setInt(1, p.getId_numEncomenda());
			preparador.setInt(2, p.getId_produto());
			ResultSet rs = preparador.executeQuery();
			if (rs.next()) {
				aux = rs.getInt("quantidade_recebida");						
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
	
	public int getQDtProdutoEstoque(Produtos p) {
		int aux = 0;
		String sql = "SELECT * FROM tblprodutoemestoquearmazem where FK_id_produto = ? and numero_dolote_sere = ?";
		try {
			con = Conexao.getConexao();
			PreparedStatement preparador = con.prepareStatement(sql);
			preparador.setInt(1, p.getId_produto());
			preparador.setString(2, p.getNumero_lote_sere());
			ResultSet rs = preparador.executeQuery();
			if (rs.next()) {
				aux = rs.getInt("quantidade_produto");						
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
		 System.out.println(aux);
		return aux;
	}
	public void AtualizarQDtProdutoAlocado(Produtos p) {
		String sql = "update tblprodutoemestoquearmazem set quantidade_produto = ?  where FK_id_produto = ? and numero_dolote_sere = ?";
		try {
			con = Conexao.getConexao();
			PreparedStatement preparador = con.prepareStatement(sql);
			preparador.setInt(1, p.getQdt_recebida());
			preparador.setInt(2, p.getId_produto());
			preparador.setString(3, p.getNumero_lote_sere());
			preparador.execute();
			
			sql = "update tblencomendarecebida set alocado =? where FK_encomenda = ? and FK_produto = ?  ";
			preparador = con.prepareStatement(sql);
			preparador.setInt(1, 1);
			preparador.setInt(2, p.getId_numEncomenda());
			preparador.setInt(3, p.getId_produto());
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
	public int checkQdtRecebido(Produtos p) {
		int retorno = -1;
		int qdtEnc = 0;
		int qdtRec  = 0;
		String sql = "SELECT * FROM tblprodutoencomendado where fk_num_encomenda = ? and fk_id_produto = ?";
		try {
			con = Conexao.getConexao();
			PreparedStatement preparador = con.prepareStatement(sql);
			preparador.setInt(1, p.getId_numEncomenda());
			preparador.setInt(2, p.getId_produto());
			ResultSet rs = preparador.executeQuery();
			if (rs.next()) {
				qdtRec = rs.getInt("qt_recebida");
				qdtEnc = rs.getInt("qt_unitaria");
				if(qdtRec == 0){
					sql = "Update tblprodutoencomendado set qt_recebida = ? where fk_num_encomenda = ? and fk_id_produto = ?";
					preparador = con.prepareStatement(sql);
					preparador.setInt(1, p.getQdt_recebida());
					preparador.setInt(2, p.getId_numEncomenda());
					preparador.setInt(3, p.getId_produto());
					preparador.executeUpdate();
					retorno = 0;
				}
				if(qdtRec > 0){
					qdtRec = qdtRec + p.getQdt_recebida();
					if(qdtEnc >= qdtRec){
						sql = "Update tblprodutoencomendado set qt_recebida = ? where fk_num_encomenda = ? and fk_id_produto = ?";
						preparador = con.prepareStatement(sql);
						preparador.setInt(1, qdtRec);
						preparador.setInt(2, p.getId_numEncomenda());
						preparador.setInt(3, p.getId_produto());
						preparador.executeUpdate();
						retorno = 1;
					}
				}
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
		return retorno;
	}
	
//	2-Visualizar produtos EM ESTOQUE
	public List <Produtos> buscarProdutosEmEstoque ()
	{
		List <Produtos> lista = new ArrayList<Produtos>();
		String sql = "SELECT (CASE WHEN codigo_barra = '' THEN '000000000000' ELSE codigo_barra END) AS codigo_barra, fk_id_produto, nome_comercial, nome_quimico, forma_farmaceutica, dosagem, numero_dolote_sere, data_decaducidade, preco_dcompra, preco_dvenda, quantidade_produto FROM vwprodutoemestoque order by nome_comercial";
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
	
	public List <Produtos> buscarProdutosEmEstoqueArmazem()
	{
		List <Produtos> lista = new ArrayList<Produtos>();
		String sql = "SELECT (CASE WHEN codigo_barra = '' THEN '000000000000' ELSE codigo_barra END) AS codigo_barra, fk_id_produto, nome_comercial, nome_quimico, forma_farmaceutica, dosagem, numero_dolote_sere, data_decaducidade, preco_dcompra, preco_dvenda, quantidade_produto FROM vwprodutoemestoquearmazem order by nome_comercial asc";
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
	
	public List <Produtos> buscarProdutosEmEstoqueArmazem(String nome)
	{
		List <Produtos> lista = new ArrayList<Produtos>();
		String sql = "SELECT (CASE WHEN codigo_barra = '' THEN '000000000000' ELSE codigo_barra END) AS codigo_barra, fk_id_produto, nome_comercial, nome_quimico, forma_farmaceutica, dosagem, numero_dolote_sere, data_decaducidade, preco_dcompra, preco_dvenda, quantidade_produto FROM vwprodutoemestoquearmazem where nome_comercial = ? or codigo_barra = ? or numero_dolote_sere = ? order by nome_comercial";
		try {
			con = Conexao.getConexao();
			 PreparedStatement preparador = con.prepareStatement(sql);
			 preparador.setString(1, nome);
			 preparador.setString(2, nome);
			 preparador.setString(3, nome);
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
		String sql = "SELECT count(codigo_barra) totalportipodeproduto  FROM vwtotalportipodeproduto";
		try {
			con = Conexao.getConexao();
			 PreparedStatement preparador = con.prepareStatement(sql);
			 ResultSet rs = preparador.executeQuery();
			 while(rs.next())
			 {
				 Produtos pdbttpee = new Produtos();
				 pdbttpee.setTotalportipodeproduto(rs.getInt("totalportipodeproduto"));
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
	
	public Produtos buscarProdutosEmEstoqueDevolucao(int produto){
		String sql = "SELECT * FROM vwprodutoemestoque WHERE fk_id_produto = ? ";
		Produtos pd = new Produtos();
		try {
			con = Conexao.getConexao();
			 PreparedStatement preparador = con.prepareStatement(sql);
			 preparador.setInt(1, produto);
//			 preparador.setString(2, lote);
//			 preparador.setString(3, data_caducidade);
			 ResultSet rs = preparador.executeQuery();
			 if(rs.next())
			 {
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
		return pd;
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
//			ent.setInt(2, 1);
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
	
	public Produtos buscarProdutoEncomendados(int cod,int codp) {
		Produtos pd = new Produtos();
		String sql = "SELECT * FROM vwlistadoprodutoencomendado where fk_num_encomenda = ? and fk_id_produto = ?";
		try {
			con = Conexao.getConexao();
			PreparedStatement preparador = con.prepareStatement(sql);
			preparador.setInt(1, cod);
			preparador.setInt(2, codp);
			ResultSet rs = preparador.executeQuery();
			if(rs.next())
			{	
				pd.setId_produto(rs.getInt("fk_id_produto"));
				pd.setNome_comercial(rs.getString("nome_comercial"));
				pd.setQuantidade(rs.getInt("qt_unitaria"));
				pd.setQdt_recebida(rs.getInt("qt_unitaria"));
				pd.setTotalEncomenda(rs.getDouble("enco_total"));
				pd.setNumero_lote_sere(rs.getString("num_daencomenda"));
//				Calendar data = Calendar.getInstance();
//				data.setTime(rs.getDate("data_decriacao"));
//				pd.setDataCriacao(data);
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
	
	public List<Produtos> buscarProdutosEstoques() {
		List<Produtos> lista = new ArrayList<Produtos>();
		String sql = "SELECT FK_id_produto, (CASE WHEN codigo_barra = '' THEN '000000000000' ELSE codigo_barra END) AS codigo_barra, nome_comercial, nome_quimico, quantidade_produto, numero_dolote_sere, preco_dcompra, dosagem, data_decaducidade FROM vwprodutoemestoquearmazem order by nome_comercial asc";
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
	
	public Produtos buscarProdutoEstoque(String p,String n) {
		Produtos pd = null;
		String sql = "SELECT FK_id_produto, (CASE WHEN codigo_barra = '' THEN '000000000000' ELSE codigo_barra END) AS codigo_barra, nome_comercial, nome_quimico, quantidade_produto, numero_dolote_sere, preco_dcompra, dosagem, data_decaducidade FROM vwprodutoemestoque where FK_id_produto Like ? and numero_dolote_sere Like ? ";
		try {
			con = Conexao.getConexao();
			PreparedStatement preparador = con.prepareStatement(sql);
			preparador.setString(1, p);
			preparador.setString(2, n);
			ResultSet rs = preparador.executeQuery();
			if (rs.next()) {
				pd = new Produtos();
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
				System.out.println("Passagem area dentro do metodo: "+pd.getId_produto()+"Número de Lute "+pd.getNumero_lote_sere());
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
	
	public Produtos buscarProdutoEstoqueArmazem(String p,String n) {
		Produtos pd = null;
		String sql = "SELECT FK_id_produto, (CASE WHEN codigo_barra = '' THEN '000000000000' ELSE codigo_barra END) AS codigo_barra, nome_comercial, nome_quimico, quantidade_produto, numero_dolote_sere, preco_dcompra, dosagem, data_decaducidade FROM vwprodutoemestoquearmazem where FK_id_produto Like ? and numero_dolote_sere Like ? ";
		try {
			con = Conexao.getConexao();
			PreparedStatement preparador = con.prepareStatement(sql);
			preparador.setString(1, p);
			preparador.setString(2, n);
			ResultSet rs = preparador.executeQuery();
			if (rs.next()) {
				pd = new Produtos();
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
				System.out.println("Passagem area dentro do metodo: "+pd.getId_produto()+"Número de Lute "+pd.getNumero_lote_sere());
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
	
	public List<Facturacao> buscarVendaProdutosParaDevolucao()
	{
		String sql = "SELECT * FROM vwprodutoadevolverlistado WHERE Data_da_facturacao BETWEEN SUBDATE(CURDATE(), INTERVAL 15 DAY) AND CURDATE() ORDER BY Data_da_facturacao DESC";
		List<Facturacao> lsFat = new ArrayList<Facturacao>();
		try {
			con = Conexao.getConexao();
			PreparedStatement ps = con.prepareStatement(sql);
			ResultSet rs = ps.executeQuery();
			while(rs.next()){
				Facturacao fp = new Facturacao();
				fp.setFK_factura(rs.getInt("id_factura"));
				fp.setFK_paciente(rs.getInt("FK_paciente"));
				fp.setNome_paciente(rs.getString("NomeCompleto"));
				fp.setNumero_factura(rs.getString("numero_factura"));
				fp.setNome(rs.getString("n_c_funcionario"));
				fp.setQuantidade(rs.getInt("qtd_produto_vendido"));
				fp.setTotal_factura(rs.getDouble("total_pago"));
				Calendar data = Calendar.getInstance();
				data.setTime(rs.getDate("Data_da_facturacao"));
				fp.setData_registo(data);
				lsFat.add(fp); 
			}
		} catch (Exception e) {
			 e.printStackTrace();
		}
		return lsFat;
	}
	
	
	public List<Facturacao> buscarHistoricoDevolucaoProduto(){
		String sql = "SELECT * FROM vwhistoricodevolucaoproduto limit 500";
		List<Facturacao> lsFat = new ArrayList<Facturacao>();
		try {
			con = Conexao.getConexao();
			PreparedStatement ps = con.prepareStatement(sql);
			ResultSet rs = ps.executeQuery();
			while(rs.next()){
				Facturacao fp = new Facturacao();
				fp.setFK_factura(rs.getInt("id_devolucao_venda"));
				fp.setFK_paciente(rs.getInt("FK_paciente"));
				fp.setNumero_factura(rs.getString("numero_devolucao"));
				fp.setNomeCompleto(rs.getString("nomeCompleto"));
				fp.setNome(rs.getString("n_c_funcionario"));
				fp.setQuantidade(rs.getInt("qtd_produto"));
				fp.setTotal_factura(rs.getDouble("total_devolvido"));
				fp.setHora_registo(rs.getString("hora_pro_devolvido"));
				Calendar data = Calendar.getInstance();
				data.setTime(rs.getDate("data_pro_devolvido"));
				fp.setData_registo(data);
				lsFat.add(fp); 
			}
		} catch (Exception e) {
			 e.printStackTrace();
		}
		return lsFat;
	}
	
	public List<Facturacao> buscarProdutosDevolvidoModel(String codigo){
		String sql = "SELECT (CASE WHEN codigo_barra = '' THEN '000000000000' ELSE codigo_barra END) AS codigo_barra, nome_comercial, quantidade, preco_pro_devolvido, data_pro_devolvido, txa_pro_devolvido, subt_pro_devolvido, numero_devolucao FROM vwhistoricodevolucaoprodutoitens where id_devolucao_venda = ?";
		List<Facturacao> lsFat = new ArrayList<Facturacao>();
		try {
			con = Conexao.getConexao();
			PreparedStatement ps = con.prepareStatement(sql);
			ps.setString(1, codigo);
			ResultSet rs = ps.executeQuery();
			while(rs.next()){
				Facturacao fp = new Facturacao();
				fp.setCodigo_barra(rs.getString("codigo_barra"));
				fp.setNomeCompleto(rs.getString("nome_comercial"));
				fp.setQuantidade(rs.getInt("quantidade"));
				fp.setPreco(rs.getDouble("preco_pro_devolvido"));
				Calendar data = Calendar.getInstance();
				data.setTime(rs.getDate("data_pro_devolvido"));
				fp.setTaxa_emkz(rs.getDouble("txa_pro_devolvido"));
				fp.setTot_pago_produto(rs.getDouble("subt_pro_devolvido"));
				fp.setNumero_ordem(rs.getString("numero_devolucao"));
				fp.setData_registo(data);
				lsFat.add(fp); 
			}
		} catch (Exception e) {
			 e.printStackTrace();
		}
		return lsFat;
	}
	
	public List<Facturacao> buscarHistoricoDevolucaoProduto(String nome)
	{
		String sql = "SELECT * FROM vwhistoricodevolucaoproduto where nomeCompleto LIKE ? or numero_devolucao LIKE ?";
		List<Facturacao> lsFat = new ArrayList<Facturacao>();
		try {
			con = Conexao.getConexao();
			PreparedStatement ps = con.prepareStatement(sql);
			ps.setString(1, "%"+nome+"%");
			ps.setString(2, nome);
			ResultSet rs = ps.executeQuery();
			while(rs.next()){
				Facturacao fp = new Facturacao();
				fp.setFK_factura(rs.getInt("id_devolucao_venda"));
				fp.setFK_paciente(rs.getInt("FK_paciente"));
				fp.setNumero_factura(rs.getString("numero_devolucao"));
				fp.setNomeCompleto(rs.getString("nomeCompleto"));
				fp.setNome(rs.getString("n_c_funcionario"));
				fp.setQuantidade(rs.getInt("quantidade"));
				fp.setTotal_factura(rs.getDouble("total_devolvido"));
				fp.setHora_registo(rs.getString("hora_pro_devolvido"));
				Calendar data = Calendar.getInstance();
				data.setTime(rs.getDate("data_pro_devolvido"));
				fp.setData_registo(data);
				lsFat.add(fp); 
			}
		} catch (Exception e) {
			 e.printStackTrace();
		}
		return lsFat;
	}
	
	public List<Facturacao> buscarHistoricoVendaProdutos()
	{
		String sql = "SELECT * FROM vwhistoricodevendaproduto where Data_da_facturacao = CurDate() order by hora_da_facturacao desc";
		List<Facturacao> lsFat = new ArrayList<Facturacao>();
		try {
			con = Conexao.getConexao();
			PreparedStatement ps = con.prepareStatement(sql);
			ResultSet rs = ps.executeQuery();
			while(rs.next()){
				Facturacao fp = new Facturacao();
				fp.setNumero_factura(rs.getString("numero_factura"));
				fp.setNome(rs.getString("n_c_funcionario"));
				fp.setNome_paciente(rs.getString("NomeCompleto"));
				fp.setNumero_processo(rs.getString("numero_processo"));
				fp.setQuantidade(rs.getInt("qtd"));
				fp.setTotal_factura(rs.getDouble("total"));
				fp.setHora_registo(rs.getString("hora_da_facturacao"));
				fp.setFK_factura(rs.getInt("id_factura"));
				Calendar data = Calendar.getInstance();
				data.setTime(rs.getDate("Data_da_facturacao"));
				fp.setData_registo(data);
				fp.setMetodo_dpagamento(rs.getString("metodo_depagameto"));
				lsFat.add(fp); 
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return lsFat;
	}
	
	
	public List<Facturacao> buscarHistoricoVendaProdutosRelatorio()
	{
		
		String sql = "SELECT * FROM vwhistoricodevendaproduto order by Data_da_facturacao desc, hora_da_facturacao desc limit 500";
		List<Facturacao> lsFat = new ArrayList<Facturacao>();
		try {
			con = Conexao.getConexao();
			PreparedStatement ps = con.prepareStatement(sql);
			ResultSet rs = ps.executeQuery();
			while(rs.next()){
				Facturacao fp = new Facturacao();
				fp.setNumero_factura(rs.getString("numero_factura"));
				fp.setNome(rs.getString("n_c_funcionario"));
				fp.setNome_paciente(rs.getString("NomeCompleto"));
				fp.setNumero_processo(rs.getString("numero_processo"));
				fp.setQuantidade(rs.getInt("qtd"));
				fp.setTotal_factura(rs.getDouble("total"));
				fp.setHora_registo(rs.getString("hora_da_facturacao"));
				fp.setFK_factura(rs.getInt("id_factura"));
				Calendar data = Calendar.getInstance();
				data.setTime(rs.getDate("Data_da_facturacao"));
				fp.setData_registo(data);
				fp.setMetodo_dpagamento(rs.getString("metodo_depagameto"));
				lsFat.add(fp); 
			}
		} catch (Exception e) {
			// TODO: handle exception
		}
		return lsFat;
	}
	
	public List<Facturacao> buscarHistoricoVendaProdutosRelatorio(String valor){
		String sql = "SELECT * FROM vwhistoricodevendaproduto where NomeCompleto Like ? or numero_factura Like ? or Data_da_facturacao Like ?";
		List<Facturacao> lsFat = new ArrayList<Facturacao>();
		try {
			con = Conexao.getConexao();
			PreparedStatement ps = con.prepareStatement(sql);
			ps.setString(1, valor+"%");
			ps.setString(2, valor);
			ps.setString(3, valor);
			ResultSet rs = ps.executeQuery();
			while(rs.next()){
				Facturacao fp = new Facturacao();
				fp.setNumero_factura(rs.getString("numero_factura"));
				fp.setNome(rs.getString("n_c_funcionario"));
				fp.setNome_paciente(rs.getString("NomeCompleto"));
				fp.setNumero_processo(rs.getString("numero_processo"));
				fp.setQuantidade(rs.getInt("qtd"));
				fp.setTotal_factura(rs.getDouble("total"));
				fp.setHora_registo(rs.getString("hora_da_facturacao"));
				fp.setFK_factura(rs.getInt("id_factura"));
				Calendar data = Calendar.getInstance();
				data.setTime(rs.getDate("Data_da_facturacao"));
				fp.setData_registo(data);
				fp.setMetodo_dpagamento(rs.getString("metodo_depagameto"));
				lsFat.add(fp); 
			}
		} catch (Exception e) {
			// TODO: handle exception
		}
		return lsFat;
	}
	
	public List<Produtos> buscarHistoricoVendaUmProduto(int cod)
	{
		String sql = "SELECT (CASE WHEN codigo_barra = '' THEN '000000000000' ELSE codigo_barra END) AS codigo_barra, numero_factura, produto, qtd, preco, taxa, taxa_kz, total, id_factura, id_produto, Data_da_facturacao FROM vwrelpagamentoprodutos where id_factura = ?";
		List<Produtos> lsFat = new ArrayList<Produtos>();
		try {
			con = Conexao.getConexao();
			PreparedStatement ps = con.prepareStatement(sql);
			ps.setInt(1, cod);
			ResultSet rs = ps.executeQuery();
			while(rs.next()){
				Produtos pd = new Produtos();
				pd.setNumero_doc(rs.getString("numero_factura"));
				pd.setNome_comercial(rs.getString("produto"));
				pd.setQuantidade(rs.getInt("qtd"));
				pd.setCodigo_barra(rs.getString("codigo_barra"));
				pd.setPreco_dvenda(rs.getDouble("preco"));
				pd.setTaxa(rs.getInt("taxa"));
				pd.setTaxa_emkz(rs.getInt("taxa_kz"));
				pd.setTotal(rs.getInt("total"));
				pd.setId(rs.getInt("id_factura"));
				pd.setId_produto(rs.getInt("id_produto"));
				Calendar data = Calendar.getInstance();
				data.setTime(rs.getDate("Data_da_facturacao"));
				pd.setData_registo(data);
				 lsFat.add(pd); 
			}
		} catch (Exception e) {
			// TODO: handle exception
		}
		return lsFat;
	}
	
	public List<Produtos> buscarVendaUmProdutoEmDevolucao(int cod){
		String sql = "SELECT * FROM vwprodutoadevolverlistadoitens where id_factura = ?";
		List<Produtos> lsFat = new ArrayList<Produtos>();
		try {
			con = Conexao.getConexao();
			PreparedStatement ps = con.prepareStatement(sql);
			ps.setInt(1, cod);
			ResultSet rs = ps.executeQuery();
			while(rs.next()){
				Produtos pd = new Produtos();
				pd.setNome_comercial(rs.getString("nome_comercial"));
				pd.setQuantidade(rs.getInt("qtd_produto"));
				pd.setPreco_dvenda(rs.getDouble("preco_produto"));
				pd.setTaxa_emkz(rs.getDouble("tx_emkz_produto"));
				pd.setTaxas(rs.getDouble("taxa_perc_produto"));
				pd.setTotal_s(rs.getString("tot_pago_produto"));
				pd.setId(rs.getInt("id_factura"));
				pd.setId_produto(rs.getInt("fk_produto"));
				pd.setFk_produto(rs.getInt("fk_produto"));
				lsFat.add(pd); 
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return lsFat;
	}
	
	public Produtos buscarVendaUmProdutoEmDevolucao(int cod,int codp){
		String sql = "SELECT * FROM vwprodutoadevolverlistadoitens where id_factura = ? and fk_produto = ?";
		Produtos pd = new Produtos();
		try {
			con = Conexao.getConexao();
			PreparedStatement ps = con.prepareStatement(sql);
			ps.setInt(1, cod);
			ps.setInt(2, codp);
			ResultSet rs = ps.executeQuery();
			if(rs.next()){
				
				pd.setNome_comercial(rs.getString("nome_comercial"));
				pd.setQuantidade(rs.getInt("qtd_produto"));
				pd.setPreco_dvenda(rs.getDouble("preco_produto"));
				pd.setTaxa_emkz(rs.getDouble("tx_emkz_produto"));
				pd.setTaxas(rs.getDouble("taxa_perc_produto"));
				pd.setTotal_s(rs.getString("tot_pago_produto"));
				pd.setId(rs.getInt("id_factura"));
				pd.setId_produto(rs.getInt("fk_produto"));
				
				// --- Para Salvar no Banco Devolvido
				pd.setFk_produto(rs.getInt("fk_produto"));
				pd.setPreco_pro_devolvido(rs.getDouble("preco_produto"));

			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return pd;
	}
	 
}
