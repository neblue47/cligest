package ao.co.cligest.negocio;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

import ao.co.cligest.entidades.Beneficios;
import ao.co.cligest.entidades.Facturacao;
import ao.co.cligest.entidades.Fornecedor;
import ao.co.cligest.entidades.GestaoInstalacao;
import ao.co.cligest.entidades.Produtos;
import ao.co.cligest.entidades.Servico;
import ao.co.cligest.entidades.Triagem;

public class BeneficiosDAO {

	private Connection con = null;
	PreparedStatement prd = null;
	ResultSet rs =null;
	
//1-Mostrar os dados do Serviço
	public List<Servico> buscarServico()
	{
	List <Servico> lista = new ArrayList<Servico>();
	String sql = "SELECT * FROM tblservico";
	try{
	con = Conexao.getConexao();
		con = Conexao.getConexao();
	prd = con.prepareStatement(sql);
	rs = prd.executeQuery();
	while(rs.next())
	 {
		Servico pn = new Servico();
		pn.setId_servico(rs.getInt("id_servico"));
		pn.setFK_tipo_de_servico(rs.getInt("FK_tipo_de_servico"));
		//pn.setFK_especialidade(rs.getInt("FK_especialidade"));
		pn.setServico(rs.getString("servico"));
		pn.setDescricao(rs.getString("descricao"));
		pn.setPreco(rs.getDouble("preco"));
	    lista.add(pn);
	    }
	  } catch (SQLException e) {
	   // TODO Auto-generated catch block
	   e.printStackTrace();
	  }
	catch (Exception e) {
		// TODO: handle exception
	}
	finally{
			try{
				rs.close();
				prd.close();
				con.close();
			}catch(Throwable e){
				System.out.println("Erro ao fechar as conexão");
				e.printStackTrace();
			}
		}
		return lista;
	}
	
	// 4--Salvar na tabela tblbeneficio 
    public int inserirBeneficioReq(Beneficios ex)
	{
    	int ultimoId = -1;
		String sql = "insert into tblbeneficio (data_cadastro_beneficio, FK_funcionario,hora_cadastro_beneficio) values(?,?,?)";
		try {
			 con = Conexao.getConexao();
			con = Conexao.getConexao();
			 PreparedStatement preparador = con.prepareStatement(sql);
			 preparador = con.prepareStatement(sql);
			 preparador.setDate(1, new Formatando().data_registo());
			 preparador.setInt(2, ex.getFK_funcionario());
			 preparador.setString(3, new Formatando().horaAtual());
			 preparador.execute();
			 rs = preparador.executeQuery("SELECT LAST_INSERT_ID()");
			 if(rs.next())
	    	        ultimoId = rs.getInt(1);
			 preparador.close();
			
		} catch (SQLException e) {
			throw new RuntimeException(e);
		}
		catch (Exception e) {
			// TODO: handle exception
		}
		finally{
				try{
					rs.close();
					prd.close();
					con.close();
				}catch(Throwable e){
					System.out.println("Erro ao fechar as conexão");
					e.printStackTrace();
				}
			}
		return ultimoId;
	}
    
    public int getIdEntidade(int a){
    	  int ultimoId = -1;
    	  String sql = "INSERT INTO tblbeneficio (data_cadastro_beneficio,FK_funcionario,hora_cadastro_beneficio) VALUE (?,?,?)";
    	  try {
    		  con = Conexao.getConexao();
    		  Beneficios cons = new Beneficios();
    		  prd = con.prepareStatement(sql);
    		  prd.setDate(1, new Formatando().data_registo());
    		  prd.setInt(2, cons.getFK_funcionario());
    		  prd.setString(3, new Formatando().horaAtual());
    		  prd.execute();
    	      rs = prd.executeQuery("SELECT LAST_INSERT_ID()");
    	      if(rs.next())
    	        ultimoId = rs.getInt(1);
    	      prd.close();
    	      System.out.println("Cadastro de sucesso..."); 
    	  }catch (SQLException e) {
    	   e.printStackTrace();
    	  }
    	  catch (Exception e) {
    			// TODO: handle exception
    		}
    		finally{
    				try{
    					rs.close();
    					prd.close();
    					con.close();
    				}catch(Throwable e){
    					System.out.println("Erro ao fechar as conexão");
    					e.printStackTrace();
    				}
    			}
//    	  System.out.println(ultimoId);
    	  return ultimoId;
    	 }

	//Salvar na tabela tblbeneficio consulta
    public void inseriservicoConsultaReq(Beneficios ex)
	{
		String sql = "insert into tblbeneficioconsulta (FK_consulta,FK_tipo_de_cobertura,FK_funcionario,data) values(?,?,?,?)";
		try {
			con = Conexao.getConexao();
			PreparedStatement preparador = con.prepareStatement(sql);
			preparador = con.prepareStatement(sql);
			preparador.setInt(1, ex.getFK_consulta());
			preparador.setInt(2, ex.getFK_tipo_dcobertura());
			preparador.setInt(3, ex.getFK_funcionario());
			preparador.setDate(4, new Formatando().data_registo());
			preparador.execute();
			preparador.close();
			System.out.println("Cadastro de sucesso...tblbeneficioconsulta"); 
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
    //
    public List<Servico> buscarServicosGERAISCadastrados(int aux){
		String sql = "Select * from vwbeneficioservicogeral WHERE FK_tipo_de_cobertura = ?";
		List<Servico> lista = new ArrayList<Servico>();
		try {
			con = Conexao.getConexao();
			PreparedStatement ps = con.prepareStatement(sql);
			ps.setInt(1, aux);
			ResultSet rs = ps.executeQuery();
			while(rs.next()){
				Servico s = new Servico();
				s.setDescricao(rs.getString("descricao"));
				s.setPreco(rs.getDouble("preco"));
				s.setFK_tipo_dcobertura(rs.getInt("FK_tipo_de_cobertura"));
				s.setId_servico(rs.getInt("id_beneficio_servico_geral"));
				lista.add(s);
			}
			ps.close();
			con.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return lista;
	}
    
    public List<Servico> buscarServicosCadastrados(int aux){
		String sql = "Select * from vwbeneficioconsulta WHERE FK_tipo_de_cobertura = ?";
		List<Servico> lista = new ArrayList<Servico>();
		try {
			con = Conexao.getConexao();
			PreparedStatement ps = con.prepareStatement(sql);
			ps.setInt(1, aux);
			ResultSet rs = ps.executeQuery();
			while(rs.next()){
				Servico s = new Servico();
				s.setDescricao(rs.getString("descricao"));
				s.setPreco(rs.getDouble("preco"));
				s.setFK_tipo_dcobertura(rs.getInt("FK_tipo_de_cobertura"));
				s.setId_servico(rs.getInt("id_beneficio_consulta"));
				lista.add(s);
			}
			ps.close();
			con.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return lista;
	}
    
    
  //Salvar na tabela tblbeneficio Exame
    
    public void inserirServicoExameReq(Beneficios ex)
   	{
   		String sql = "insert into tblbeneficioexame (FK_exame, FK_tipo_de_cobertura, FK_funcionario, data) values(?,?,?,?)";
   		try {
   			con = Conexao.getConexao();
   			PreparedStatement preparador = con.prepareStatement(sql);
   			preparador = con.prepareStatement(sql);
			preparador.setInt(1, ex.getId_beneficio_exame());
			preparador.setInt(2, ex.getFK_tipo_dcobertura());
			preparador.setInt(3, ex.getFK_funcionario());
			preparador.setDate(4, new Formatando().data_registo());
   			preparador.execute();
   			preparador.close();
   			System.out.println("Cadastro de sucesso...tblbeneficioexame"); 
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
    
  //Salvar na tabela tblbeneficio farmaco
    public void inseriservicoFarmacoReq(Beneficios ex)
	{
		String sql = "insert into tblbeneficioconsulta (FK_beneficio, FK_beneficio_consulta,FK_fornecedor,FK_tipo_de_cobertura) values(?,?,?,?)";
		try {
			con = Conexao.getConexao();
			 PreparedStatement preparador = con.prepareStatement(sql);
			 preparador = con.prepareStatement(sql);
			 preparador.setInt(1, ex.getFK_beneficio());
			 preparador.setInt(2, ex.getFK_beneficio_consulta());
			 preparador.setInt(3, ex.getFK_fornecedor());
			 preparador.setInt(4, ex.getFK_tipo_dcobertura());
			 preparador.execute();
			 preparador.close(); 
			
		} catch (SQLException e) {
			throw new RuntimeException(e);
		}
		catch (Exception e) {
			// TODO: handle exception
		}
		finally{
				try{
					rs.close();
					prd.close();
					con.close();
				}catch(Throwable e){
					System.out.println("Erro ao fechar as conexão");
					e.printStackTrace();
				}
			}
	}
//	1-Mostrar os dados do Beneficios
	public List <Beneficios> buscarServicoExame()
	{
		List<Beneficios> lista = new ArrayList<Beneficios>();
		String sql = "SELECT * FROM vwexameslaboratoriais";
		try {
			con = Conexao.getConexao();
			prd = con.prepareStatement(sql);
			rs = prd.executeQuery();
			while (rs.next()) {
				Beneficios pn = new Beneficios();
				pn.setId_servicodeanalise_clinica(rs.getInt("id_servicodeanalise_clinica"));
				pn.setFK_grupo_analise_clinica(rs.getInt("id_grupo_analises_clinicas"));
				pn.setPreco_clinico(rs.getDouble("preco"));
				pn.setAnalise_clinica(rs.getString("analise_clinica"));
				pn.setDescricao_servico_analise(rs.getString("obs_servico_exame"));
				//pn.setCodigobarra(rs.getInt("codigobarra"));
				lista.add(pn);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}

		catch (Exception e) {
			// TODO: handle exception
		} finally {
			try {
				rs.close();
				prd.close();
				con.close();
			} catch (Throwable e) {
				System.out.println("Erro ao fechar as conexão");
				e.printStackTrace();
			}
		}
		return lista;
	}
	
//	/  Busca por id servicos
	public Triagem buscaServicoPorId(int id_servicos) {
		Triagem t = null;
		String sql = "SELECT * FROM vwservico where id_servico = ?";
		try {
			con = Conexao.getConexao();
			PreparedStatement preparador = con.prepareStatement(sql);
			preparador.setInt(1, id_servicos);
			ResultSet rs = preparador.executeQuery();
			if (rs.next()) {
				t = new Triagem();
				t.setId_servico(rs.getInt("id_servico"));
				t.setServico(rs.getString("servico"));
				t.setPreco(rs.getDouble("preco"));
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
		return t;
	}
	
	//  Busca por id servicos Exames
	public Beneficios buscaServicoExamesPorId(int id_servicosExame) {
		Beneficios t = null;
		String sql = "SELECT * FROM vwexameslaboratoriais where id_servicodeanalise_clinica = ?";
		try {
			con = Conexao.getConexao();
			PreparedStatement preparador = con.prepareStatement(sql);
			preparador.setInt(1, id_servicosExame);
			ResultSet rs = preparador.executeQuery();
			if (rs.next()) {
				t = new Beneficios();
				t.setId_servicodeanalise_clinica(rs.getInt("id_servicodeanalise_clinica"));
				t.setAnalise_clinica(rs.getString("analise_clinica"));
				t.setPreco_clinico(rs.getDouble("preco"));
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
		return t;
	}
	  
	//1-Mostrar os dados do Serviço
	public List<Beneficios> buscarBeneficioConsulta() {
		List<Beneficios> lista = new ArrayList<Beneficios>();
		String sql = "SELECT * FROM vwbeneficioconsulta";
		try {
			con = Conexao.getConexao();
			prd = con.prepareStatement(sql);
			rs = prd.executeQuery();
			while (rs.next()) {
				Beneficios pn = new Beneficios();
				pn.setId_beneficio_consulta(rs.getInt("id_beneficio_consulta"));
				pn.setServico(rs.getString("servico"));
				pn.setTipo_dcobertura(rs.getString("tipo_dcobertura"));
				pn.setData(rs.getString("data_cadastro_beneficio"));
				pn.setHora(rs.getString("hora_cadastro_beneficio"));
				pn.setFornecedor(rs.getString("empresa"));
				pn.setPreco(rs.getDouble("preco"));
				lista.add(pn);
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (Exception e) {
			// TODO: handle exception
		} finally {
			try {
				rs.close();
				prd.close();
				con.close();
			} catch (Throwable e) {
				System.out.println("Erro ao fechar as conexão");
				e.printStackTrace();
			}
		}
		return lista;
	}
		
//		6-Remover o Beneficios consultas
	public void del_beneficioConsulta(int id) {
		String sql = "Delete from tblbeneficioconsulta where id_beneficio_consulta = ? ";
		try {

			con = Conexao.getConexao();
			PreparedStatement frc = con.prepareStatement(sql);
			frc.setInt(1, id);
			frc.execute();
			frc.close();
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
		
		// 4--Eliminar na tabela tblbeneficio consulta
	public void del_beneficiosReq(Beneficios ex) {
		String sql = "Delete from tblbeneficio where  id_beneficio =?";
		try {
			con = Conexao.getConexao();
			PreparedStatement preparador = con.prepareStatement(sql);
			preparador = con.prepareStatement(sql);
			preparador.setInt(1, ex.getId_beneficio());
			preparador.execute();
			preparador.close();

		} catch (SQLException e) {
			throw new RuntimeException(e);
		} catch (Exception e) {
			// TODO: handle exception
		} finally {
			try {
				rs.close();
				prd.close();
				con.close();
			} catch (Throwable e) {
				System.out.println("Erro ao fechar as conexão");
				e.printStackTrace();
			}
		}

	}
//	    ----------------------------------------- Exibir benef�cios exames
	  //7-Mostrar os dados do Benef�cios Exames
	public List<Beneficios> buscarBeneficiosExames() {
		List<Beneficios> lista = new ArrayList<Beneficios>();
		String sql = "SELECT * FROM vwbeneficiosdeexames";
		try {
			con = Conexao.getConexao();
			prd = con.prepareStatement(sql);
			rs = prd.executeQuery();
			while (rs.next()) {
				Beneficios pn = new Beneficios();
				pn.setId_beneficio_exame(rs.getInt("id_beneficio_exame"));
				pn.setExame(rs.getString("analise_clinica"));
				pn.setTipo_dcobertura(rs.getString("tipo_dcobertura"));
				pn.setData(rs.getString("data_cadastro_beneficio"));
				//pn.setHora(rs.getString("hora_cadastro_beneficio"));
				pn.setFornecedor(rs.getString("empresa"));
				pn.setPreco(rs.getDouble("preco"));
				lista.add(pn);
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (Exception e) {
			// TODO: handle exception
		} finally {
			try {
				rs.close();
				prd.close();
				con.close();
			} catch (Throwable e) {
				System.out.println("Erro ao fechar as conexão");
				e.printStackTrace();
			}
		}
		return lista;
	}
	
	public List<Beneficios> buscarBeneficiosExames(int aux) {
		List<Beneficios> lista = new ArrayList<Beneficios>();
		String sql = "SELECT * FROM vwbeneficiosdeexames WHERE FK_tipo_de_cobertura = ?";
		try {
			con = Conexao.getConexao();
			prd = con.prepareStatement(sql);
			prd.setInt(1, aux);
			rs = prd.executeQuery();
			while (rs.next()) {
				Beneficios pn = new Beneficios();
				pn.setId_beneficio_exame(rs.getInt("id_beneficio_exame"));
				pn.setExame(rs.getString("analise_clinica"));
				pn.setTipo_dcobertura(rs.getString("tipo_dcobertura"));
				pn.setData(rs.getString("data_cadastro_beneficio"));
				//pn.setHora(rs.getString("hora_cadastro_beneficio"));
				pn.setFornecedor(rs.getString("empresa"));
				pn.setPreco(rs.getDouble("preco"));
				lista.add(pn);
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (Exception e) {
			// TODO: handle exception
		} finally {
			try {
				rs.close();
				prd.close();
				con.close();
			} catch (Throwable e) {
				System.out.println("Erro ao fechar as conexão");
				e.printStackTrace();
			}
		}
		return lista;
	}
	
	public List<Beneficios> buscarBeneficiosProduto(int aux) {
		List<Beneficios> lista = new ArrayList<Beneficios>();
		String sql = "SELECT * FROM vwbeneficiosdeprodutos WHERE FK_tipo_de_cobertura = ?";
		try {
			con = Conexao.getConexao();
			prd = con.prepareStatement(sql);
			prd.setInt(1, aux);
			rs = prd.executeQuery();
			while (rs.next()) {
				Beneficios pn = new Beneficios();
				//pn.setId_beneficio_farmaco(rs.getInt("id_beneficio_exame"));
				pn.setDescricao(rs.getString("nome_comercial"));
				pn.setPreco(rs.getDouble("preco_dvenda"));
				lista.add(pn);
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (Exception e) {
			// TODO: handle exception
		} finally {
			try {
				rs.close();
				prd.close();
				con.close();
			} catch (Throwable e) {
				System.out.println("Erro ao fechar as conexão");
				e.printStackTrace();
			}
		}
		return lista;
	}
	  		
//			6-Remover o Beneficios consultas
			public void del_beneficioExames(Beneficios id)
			{
				String sql = "Delete from tblbeneficioexame where id_beneficio_exame = ? ";
				try {
					con = Conexao.getConexao();
					PreparedStatement frc = con.prepareStatement(sql);
					frc.setInt(1, id.getId_beneficio_exame());
					frc.execute();
					frc.close();
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
			
			public void del_beneficioExames(int id)
			{
				String sql = "Delete from tblbeneficioexame where id_beneficio_exame = ? ";
				try {
					con = Conexao.getConexao();
					PreparedStatement frc = con.prepareStatement(sql);
					frc.setInt(1, id);
					frc.execute();
					frc.close();
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
			
//		----------------------- Estou mexendo nesta area cussunga. Vers�o actualizado
			public List<Beneficios> buscarBeneficioConsultas() {
				List<Beneficios> lista = new ArrayList<Beneficios>();
				String sql = "SELECT * FROM vwlistadeconsultabeneficioconsulta where fk_categoria = 1";
				try {
					con = Conexao.getConexao();
					prd = con.prepareStatement(sql);
					rs = prd.executeQuery();
					while (rs.next()) {
						Beneficios pn = new Beneficios();
						pn.setId_beneficio_consulta(rs.getInt("id_servico"));
						pn.setServico(rs.getString("servico"));
						pn.setPreco(rs.getDouble("preco"));
						lista.add(pn);
					}
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				} catch (Exception e) {
					// TODO: handle exception
				} finally {
					try {
						rs.close();
						prd.close();
						con.close();
					} catch (Throwable e) {
						System.out.println("Erro ao fechar as conexão");
						e.printStackTrace();
					}
				}
				return lista;
			}
			public Beneficios buscarBeneficioConsultas(int id_servicos) {
				Beneficios t = null;
				String sql = "SELECT * FROM vwlistadeconsultabeneficioconsulta where id_servico =?";
				try {
					con = Conexao.getConexao();
					PreparedStatement preparador = con.prepareStatement(sql);
					preparador.setInt(1, id_servicos);
					ResultSet rs = preparador.executeQuery();
					if (rs.next()) {
						t = new Beneficios();
						t.setId_servico(rs.getInt("id_servico"));
						t.setServico(rs.getString("servico"));
						t.setPreco(rs.getDouble("preco"));
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
				return t;
			}
			public Beneficios buscarDetalhesDoBeneficio(int codc, int codf) {
				Beneficios t = null;
				String sql = "SELECT * FROM vwdetalhesdoplanobeneficio WHERE id_tipo_dcobertura = ? AND fk_fornecedor = ?";
				try {
					con = Conexao.getConexao();
					PreparedStatement preparador = con.prepareStatement(sql);
					preparador.setInt(1, codc);
					preparador.setInt(2, codf);
					ResultSet rs = preparador.executeQuery();
					if (rs.next()) {
						t = new Beneficios();
						t.setFK_fornecedor(rs.getInt("fk_fornecedor"));
						t.setFK_tipo_dcobertura(rs.getInt("id_tipo_dcobertura"));
						t.setFornecedor(rs.getString("empresa"));
						t.setTipo_dcobertura(rs.getString("nome_do_plano"));
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
				return t;
			}
			
			public List <Beneficios> buscarEmpresaAndCobertura(int dado)
			{
				List <Beneficios> lista = new ArrayList<Beneficios>();
				String sql = "SELECT * FROM vwempresaecobertura where fk_fornecedor = ?";
				try {
					con = Conexao.getConexao();
					 PreparedStatement preparador = con.prepareStatement(sql);
					 preparador.setInt(1, dado);
					 ResultSet rs = preparador.executeQuery();
					 while(rs.next())
					 {
						 Beneficios pd = new Beneficios();
						 pd.setFK_fornecedor(rs.getInt("fk_fornecedor"));
						 pd.setFK_tipo_dcobertura(rs.getInt("id_tipo_dcobertura"));
						 pd.setServico(rs.getString("nome_do_plano"));
						 lista.add(pd);
						 System.out.println(lista.size());
					 }
					 preparador.close();
					 con.close();
				} catch (Exception e) {
					
					e.printStackTrace();
				}
				return lista;
				
			}	
			
			public List <Beneficios> buscarProdutosSemCobertura(int emp, int cober)
			{
				List <Beneficios> lista = new ArrayList<Beneficios>();
				String sql = "Select * from vwlistadeprodutoscadastrados vwp where vwp.id_produto not in (select FK_farmaco from tblbeneficiofarmaco where FK_fornecedor = ? and FK_tipo_de_cobertura = ?)";
				try {
					 con = Conexao.getConexao();
					 PreparedStatement ps = con.prepareStatement(sql);
					 ps.setInt(1, emp);
					 ps.setInt(2, cober);
					 ResultSet rs = ps.executeQuery();
					 while(rs.next())
					 {
						 Beneficios pd = new Beneficios();
						 pd.setId_beneficio_farmaco(rs.getInt("id_produto"));
						 pd.setPreco(rs.getDouble("preco_dvenda"));
						 pd.setDescricao(rs.getString("nome_comercial"));
						 lista.add(pd);
						  
					 }
					 ps.close();
					 con.close();
				} catch (Exception e) {
					
					e.printStackTrace();
				}
			return lista;
	}
			
			public List <Beneficios> buscarProdutosNaCobertura(int emp, int cober)
			{
				List <Beneficios> lista = new ArrayList<Beneficios>();
				String sql = "Select * from vwlistadeprodutoscadastrados vwp where vwp.id_produto in (select FK_farmaco from tblbeneficiofarmaco where FK_fornecedor = ? and FK_tipo_de_cobertura = ?)";
				try {
					 con = Conexao.getConexao();
					 PreparedStatement ps = con.prepareStatement(sql);
					 ps.setInt(1, emp);
					 ps.setInt(2, cober);
					 ResultSet rs = ps.executeQuery();
					 while(rs.next())
					 {
						 Beneficios pd = new Beneficios();
						 pd.setId_beneficio_farmaco(rs.getInt("id_produto"));
						 pd.setPreco(rs.getDouble("preco_dvenda"));
						 pd.setDescricao(rs.getString("nome_comercial"));
						 lista.add(pd);
						  
					 }
					 ps.close();
					 con.close();
				} catch (Exception e) {
					
					e.printStackTrace();
				}
			return lista;
	}
			public List <Beneficios> buscarConsultaSemCobertura( int cober)
			{
				List <Beneficios> lista = new ArrayList<Beneficios>();
				String sql = "SELECT   * FROM   vwservico WHERE  FK_categoria = 1 AND id_servico  NOT IN (SELECT FK_consulta FROM tblbeneficioconsulta WHERE FK_tipo_de_cobertura = ? )";
				try {
					 con = Conexao.getConexao();
					 PreparedStatement ps = con.prepareStatement(sql);
					 
					 ps.setInt(1, cober);
					 ResultSet rs = ps.executeQuery();
					 while(rs.next())
					 {
						 Beneficios pd = new Beneficios();
						 pd.setId_beneficio_consulta(rs.getInt("id_servico"));
						 pd.setPreco(rs.getDouble("preco"));
						 pd.setDescricao(rs.getString("servico"));
						 lista.add(pd);
						  
					 }
					 ps.close();
					 con.close();
				} catch (Exception e) {
					
					e.printStackTrace();
				}
			
			 
			return lista;
	     }
			
			public List <Beneficios> buscarConsultaNaCobertura( int cober ) {
				List <Beneficios> lista = new ArrayList<Beneficios>();
				String sql = "SELECT   * FROM   vwbeneficioconsulta WHERE  FK_tipo_de_cobertura = ? ";
				try {
					 con = Conexao.getConexao();
					 PreparedStatement ps = con.prepareStatement(sql);
					 ps.setInt(1, cober);
					 ResultSet rs = ps.executeQuery();
					 while(rs.next())
					 {
						 Beneficios pd = new Beneficios();
						 pd.setId_beneficio_consulta(rs.getInt("id_beneficio_consulta"));
						 pd.setPreco(rs.getDouble("preco"));
						 pd.setDescricao(rs.getString("descricao"));
						 lista.add(pd);
					 }
					 ps.close();
					 con.close();
				} catch (Exception e) {
					e.printStackTrace();
				}
		
		 
		return lista;
      }
			
			public List <Beneficios> buscarServicosSemCobertura( int cober)
			{
				List <Beneficios> lista = new ArrayList<Beneficios>();
				String sql = "SELECT * FROM vwservico WHERE FK_categoria = 2 OR FK_categoria = 3 AND id_servico  NOT IN (SELECT FK_servico_geral FROM tblbeneficioservicogeral WHERE FK_tipo_de_cobertura = ? )";
				try {
					 con = Conexao.getConexao();
					 PreparedStatement ps = con.prepareStatement(sql);
					 
					 ps.setInt(1, cober);
					 ResultSet rs = ps.executeQuery();
					 while(rs.next())
					 {
						 Beneficios pd = new Beneficios();
						 pd.setId_beneficio_consulta(rs.getInt("id_servico"));
						 pd.setPreco(rs.getDouble("preco"));
						 pd.setDescricao(rs.getString("servico"));
						 lista.add(pd);
						  
					 }
					 ps.close();
					 con.close();
				} catch (Exception e) {
					
					e.printStackTrace();
				}
			
			 
			return lista;
	}
			
			public List <Beneficios> buscarServicosNaCobertura(int cober,int forn)
			{
				List <Beneficios> lista = new ArrayList<Beneficios>();
				String sql = "SELECT   * FROM   vwbeneficioservicogeral  WHERE FK_tipo_de_cobertura = ? AND FK_fornecedor = ? ";
				try {
					 con = Conexao.getConexao();
					 PreparedStatement ps = con.prepareStatement(sql);
					 ps.setInt(1, cober);
					 ps.setInt(2, forn);
					 ResultSet rs = ps.executeQuery();
					 while(rs.next())
					 {
						 Beneficios pd = new Beneficios();
						 pd.setId_beneficio_consulta(rs.getInt("id_beneficio_servico_geral"));
						 pd.setPreco(rs.getDouble("preco"));
						 pd.setDescricao(rs.getString("descricao"));
						 lista.add(pd);
						  
					 }
					 ps.close();
					 con.close();
				} catch (Exception e) {
					
					e.printStackTrace();
				}
			
			 
			return lista;
	}
			
			public List <Beneficios> buscarExamesSemCobertura( int cober)
			{
				List <Beneficios> lista = new ArrayList<Beneficios>();
				String sql = "SELECT   * FROM   vwexameslaboratoriais  WHERE   id_servicodeanalise_clinica  NOT IN (SELECT FK_exame FROM tblbeneficioexame WHERE  FK_tipo_de_cobertura = ? )";
				try {
					 con = Conexao.getConexao();
					 PreparedStatement ps = con.prepareStatement(sql);
					 
					 ps.setInt(1, cober);
					 ResultSet rs = ps.executeQuery();
					 while(rs.next())
					 {
						 Beneficios pd = new Beneficios();
						 pd.setId_beneficio_exame(rs.getInt("id_servicodeanalise_clinica"));
						 pd.setPreco(rs.getDouble("preco"));
						 pd.setDescricao(rs.getString("analise_clinica"));
						 lista.add(pd);
						  
					 }
					 ps.close();
					 con.close();
				} catch (Exception e) {
					
					e.printStackTrace();
				}
			
			 
			return lista;
	     }
			
			public List <Beneficios> buscarExamesNaCobertura( int cober)
			{
				List <Beneficios> lista = new ArrayList<Beneficios>();
				String sql = "SELECT   * FROM   vwbeneficiosdeexames  WHERE   FK_tipo_de_cobertura= ? ";
				try {
					 con = Conexao.getConexao();
					 PreparedStatement ps = con.prepareStatement(sql);
					 ps.setInt(1, cober);
					 ResultSet rs = ps.executeQuery();
					 while(rs.next())
					 {
						 Beneficios pd = new Beneficios();
						 pd.setId_beneficio_exame(rs.getInt("id_beneficio_exame"));
						 pd.setPreco(rs.getDouble("preco_servico_exame"));
						 pd.setDescricao(rs.getString("exames_clinicos_cadastrados"));
						 lista.add(pd);
					 }
					 ps.close();
					 con.close();
				} catch (Exception e) {
					e.printStackTrace();
				}
			
			 
			return lista;
	}
			/**
			 * Metodos Para Beneficios de Serviços Gerais - Tratamentos e Sessoes 
			 */
			
			public List<Servico> buscarServicosGerais(int aux){
				String sql = "Select * from VWSERVICO WHERE FK_categoria = 2";
				List<Servico> lista = new ArrayList<Servico>();
				try {
					con = Conexao.getConexao();
					PreparedStatement ps = con.prepareStatement(sql);
					ResultSet rs = ps.executeQuery();
					while(rs.next()){
						Servico s = new Servico();
						s.setId_servico(rs.getInt("id_servico"));
						s.setDescricao(rs.getString("descricao"));
						s.setPreco(rs.getDouble("preco"));
						lista.add(s);
					}
					ps.close();
					con.close();
				} catch (Exception e) {
					e.printStackTrace();
				}
				
				return lista;
			}
			
			public Servico buscarServicoGeral(int aux){
				String sql = "Select * from VWSERVICO WHERE ID_SERVICO = ? and FK_categoria = 2";
				Servico s = new Servico();
				try {
					con = Conexao.getConexao();
					PreparedStatement ps = con.prepareStatement(sql);
					ps.setInt(1, aux);
					ResultSet rs = ps.executeQuery();
					if(rs.next()){
						
						s.setId_servico(rs.getInt("id_servico"));
						s.setDescricao(rs.getString("descricao"));
						s.setPreco(rs.getDouble("preco"));
						 
					}
					ps.close();
					con.close();
				} catch (Exception e) {
					e.printStackTrace();
				}
				
				return s;
			}
			
//			----------------------------------------- Beneficios Servi�os Gerais.
			public Beneficios buscarBeneficiosServicosGerais(int id_servicos) {
				Beneficios t = null;
				String sql = "SELECT * FROM vwservico where id_servico =?";
				try {
					con = Conexao.getConexao();
					PreparedStatement preparador = con.prepareStatement(sql);
					preparador.setInt(1, id_servicos);
					ResultSet rs = preparador.executeQuery();
					if (rs.next()) {
						t = new Beneficios();
						t.setId_servicodeanalise_clinica(rs.getInt("id_servico"));
						t.setAnalise_clinica(rs.getString("descricao"));
						t.setPreco_clinico(rs.getDouble("preco"));
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
				return t;
			}
			
			public Beneficios buscarDetalhesDoBeneficioServicoGerais(int codc, int codf) {
				Beneficios t = null;
				String sql = "SELECT * FROM vwdetalhesdoplanobeneficio WHERE id_tipo_dcobertura = ? AND fk_fornecedor = ?";
				try {
					con = Conexao.getConexao();
					PreparedStatement preparador = con.prepareStatement(sql);
					preparador.setInt(1, codc);
					preparador.setInt(2, codf);
					ResultSet rs = preparador.executeQuery();
					if (rs.next()) {
						t = new Beneficios();
						t.setFK_fornecedor(rs.getInt("fk_fornecedor"));
						t.setFK_tipo_dcobertura(rs.getInt("id_tipo_dcobertura"));
						t.setFornecedor(rs.getString("empresa"));
						t.setTipo_dcobertura(rs.getString("nome_do_plano"));
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
				return t;
			}
			
//			public void inseriservicoGeralReq(Beneficios ex)
//			{
//				String sql = "insert into tblbeneficioservicogeral (FK_beneficio, FK_servico_geral, FK_fornecedor, FK_tipo_de_cobertura) values(?,?,?,?)";
//				try {
//					con = Conexao.getConexao();
//					 PreparedStatement preparador = con.prepareStatement(sql);
//					 preparador = con.prepareStatement(sql);
//					 preparador.setInt(1, ex.getFK_beneficio());
//					 preparador.setInt(2, ex.getFK_beneficio_geral());
//					 preparador.setInt(3, ex.getFK_fornecedor());
//					 preparador.setInt(4, ex.getFK_tipo_dcobertura());
//					 preparador.execute();
//					 preparador.close(); 
//					
//				} catch (SQLException e) {
//					throw new RuntimeException(e);
//				}
//				catch (Exception e) {
//					// TODO: handle exception
//				}
//				finally{
//						try{
//							rs.close();
//							prd.close();
//							con.close();
//						}catch(Throwable e){
//							System.out.println("Erro ao fechar as conexão Servi�os Geraos...");
//							e.printStackTrace();
//						}
//					}
//			}
			
			public void inseriservicoGeralReq(Beneficios ex) {

				String sql = "insert into tblbeneficioservicogeral (FK_beneficio, FK_servico_geral, FK_fornecedor, FK_tipo_de_cobertura) values(?,?,?,?)";
				try {
					con = Conexao.getConexao();
					PreparedStatement preparador = con.prepareStatement(sql);
					preparador = con.prepareStatement(sql);
					preparador.setInt(1, ex.getFK_beneficio());
					preparador.setInt(2, ex.getFK_beneficio_geral());
					preparador.setInt(3, ex.getFK_fornecedor());
					preparador.setInt(4, ex.getFK_tipo_dcobertura());
					preparador.execute();
					preparador.close(); 
					System.out.println("Servi�os Geraos Cadastro de Sucesso....");
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
			}
			
			public int XequeServicooGerais(String valor)
			{
				int ok = 0;
				String sql = "SELECT * FROM tblandarbloco WHERE andar_bloco =?";
				try {
					con = Conexao.getConexao();
					PreparedStatement preparador = con.prepareStatement(sql);
					 preparador.setString(1, valor);
					 ResultSet rs = preparador.executeQuery();
					 if(rs.next())
					 {
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
			
			public void del_beneficioServicosGerais(Beneficios id)
			{
				String sql = "Delete from tblbeneficioservicogeral where id_beneficio_servico_geral = ? ";
				try {
					con = Conexao.getConexao();
					PreparedStatement frc = con.prepareStatement(sql);
					frc.setInt(1, id.getId_beneficio_exame());
					frc.execute();
					frc.close();
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
			
			public void del_beneficioServicosGerais(int id)
			{
				String sql = "Delete from tblbeneficioservicogeral where id_beneficio_servico_geral = ? ";
				try {
					con = Conexao.getConexao();
					PreparedStatement frc = con.prepareStatement(sql);
					frc.setInt(1, id);
					frc.execute();
					frc.close();
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
//			----------------------------------- FIM 
//			----------------------------------- Produto 
			public List<Produtos> buscarBeneficioProduto(){
				String sql = "SELECT * FROM tblprodutofarmaceuticos;";
				List<Produtos> lsFat = new ArrayList<Produtos>();
				try {
					con = Conexao.getConexao();
					PreparedStatement ps = con.prepareStatement(sql);
					ResultSet rs = ps.executeQuery();
					while(rs.next()){
						Produtos pd = new Produtos();
						pd.setNome_comercial(rs.getString("nome_comercial"));
						pd.setPreco_dvenda(rs.getInt("preco_dvenda"));
						pd.setId_produto(rs.getInt("id_produto"));
						lsFat.add(pd); 
					}
				} catch (Exception e) {
					e.printStackTrace();
				}
				return lsFat;
			}
			
			public Produtos buscarBeneficiosProdutos(int id_servico) {
				Produtos t = null;
				String sql = "SELECT * FROM tblprodutofarmaceuticos where id_produto = ?";
				try {
					con = Conexao.getConexao();
					PreparedStatement preparador = con.prepareStatement(sql);
					preparador.setInt(1, id_servico);
					ResultSet rs = preparador.executeQuery();
					if (rs.next()) {
						t = new Produtos();
						t.setId_produto(rs.getInt("id_produto"));
						t.setNome_comercial(rs.getString("nome_comercial"));
						t.setPreco_dvenda(rs.getDouble("preco_dvenda"));
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
				return t;
			}
			
			public void inseriservicoProdutoFarmaco(Beneficios ex) {

				String sql = "insert into tblbeneficiofarmaco (FK_beneficio, FK_farmaco, FK_fornecedor, FK_tipo_de_cobertura) values(?,?,?,?)";
				try {
					con = Conexao.getConexao();
					PreparedStatement preparador = con.prepareStatement(sql);
					preparador = con.prepareStatement(sql);
					preparador.setInt(1, ex.getFK_beneficio());
					preparador.setInt(2, ex.getFK_beneficio_farmaco());
					preparador.setInt(3, ex.getFK_fornecedor());
					preparador.setInt(4, ex.getFK_tipo_dcobertura());
					preparador.execute();
					preparador.close(); 
					System.out.println("Servi�os Produto Farmaco Cadastro de Sucesso....");
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
			}
			
			public void del_beneficioServicosProdutoFarmaco(Beneficios id)
			{
				String sql = "Delete from tblbeneficiofarmaco where id_beneficio_farmaco = ? ";
				try {
					con = Conexao.getConexao();
					PreparedStatement frc = con.prepareStatement(sql);
					frc.setInt(1, id.getId_beneficio_exame());
					frc.execute();
					frc.close();
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
			public void del_beneficioServicosProdutoFarmaco(int id)
			{
				String sql = "Delete from tblbeneficiofarmaco where id_beneficio_farmaco = ? ";
				try {
					con = Conexao.getConexao();
					PreparedStatement frc = con.prepareStatement(sql);
					frc.setInt(1, id);
					frc.execute();
					frc.close();
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
//			----------------------------------- Produto Fim
}
