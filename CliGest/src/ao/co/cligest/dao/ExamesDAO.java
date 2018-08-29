package ao.co.cligest.dao;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

import ao.co.cligest.entidades.Caixa;
import ao.co.cligest.entidades.DevolucaoVenda;
import ao.co.cligest.entidades.Exames;
import ao.co.cligest.entidades.Facturacao;
import ao.co.cligest.entidades.Funcionario;
import ao.co.cligest.entidades.Instituicao;
import ao.co.cligest.entidades.Paciente;
import ao.co.cligest.entidades.Servico;

public class ExamesDAO {

	private Connection con;
	
	
    public List<Exames> getGrupoExames(){
    List <Exames> lista = new ArrayList<Exames>();
		String sql = "SELECT * FROM tbltipos_servico_exames order by tipos_servico_exames"; 
		try {
			 con = Conexao.getConexao();
			PreparedStatement ps = con.prepareStatement(sql);
			 ResultSet rs = ps.executeQuery();
			 while(rs.next())
			 {
				 Exames pac = new Exames();
				 pac.setId_grupo_analises_clinicas(rs.getInt("id_tipos_servico_exames"));  					
				 pac.setGrupo_danalise(rs.getString("tipos_servico_exames"));				
				 lista.add(pac);
			 }
			 ps.close();
			 con.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return lista;
	}
    
    public List<Exames> getExamesClinicosCadastrados(int tipoExame){
        List <Exames> lista = new ArrayList<Exames>();
    		String sql = "SELECT * FROM tblexamesclinicoscadastrados WHERE fk_tipo_serv_exames = ? order by exames_clinicos_cadastrados "; 
    		try {
    			 con = Conexao.getConexao();
    			PreparedStatement ps = con.prepareStatement(sql);
    			 ResultSet rs = ps.executeQuery();
    			 while(rs.next())
    			 {
    				 Exames pac = new Exames();
    				 pac.setId(rs.getInt("id_exames_clinicos_cadastrados"));
    				 pac.setAnalise_clinica(rs.getString(rs.getString("exames_clinicos_cadastrados")));
    				 lista.add(pac);
    			 }
    			 ps.close();
    			 con.close();
    		} catch (SQLException e) {
    			e.printStackTrace();
    		}
    		return lista;
    	}
    
    public List<Exames> buscarUnidade(){
        List <Exames> lista = new ArrayList<Exames>();
    		String sql = "SELECT * FROM tblunidade order by unidade"; 
    		try {
    			 con = Conexao.getConexao();
    			PreparedStatement preparador = con.prepareStatement(sql);
    			 ResultSet rs = preparador.executeQuery();
    			 while(rs.next())
    			 {
    				 Exames pac = new Exames();
    				 pac.setId_unidade(rs.getInt("id_unidade"));  					
    				 pac.setUnidade(rs.getString("unidade"));				
    				 lista.add(pac);
    			 }
    			 preparador.close();
    		} catch (SQLException e) {
    			// TODO Auto-generated catch block
    			e.printStackTrace();
    		}
    		catch (Exception e) {
    			// TODO: handle exception
    		}
    		finally {
    			try {
    				con.close();
    			} catch (SQLException e) {
    				// TODO Auto-generated catch block
    				e.printStackTrace();
    			}
    		}
    		return lista;
    	}
    
    public List<Exames> buscarsubgrupo(int codigo){
        List <Exames> lista = new ArrayList<Exames>();
    		String sql = "SELECT * FROM tblsubgrupo sub INNER JOIN tblgrupodeanalises gp ON sub.FK_grupo_exame = gp.id_grupo_analises_clinicas where gp.id_grupo_analises_clinicas =? order by nome_subgrupo;"; 
    		try {
    			 con = Conexao.getConexao();
    			PreparedStatement preparador = con.prepareStatement(sql);
    			preparador.setInt(1, codigo);
    			 ResultSet rs = preparador.executeQuery();
    			 while(rs.next())
    			 {
    				 Exames pac = new Exames();
    				 pac.setId_sub_grupo(rs.getInt("id_subgrupo"));  					
    				 pac.setNome_sub_grupo(rs.getString("nome_subgrupo"));
    				 pac.setGrupo_danalise(rs.getString("grupo_danalise"));
    				 lista.add(pac);
    			 }
    			 preparador.close();
    		} catch (SQLException e) {
    			// TODO Auto-generated catch block
    			e.printStackTrace();
    		}
    		catch (Exception e) {
    			// TODO: handle exception
    		}
    		finally {
    			try {
    				con.close();
    			} catch (SQLException e) {
    				// TODO Auto-generated catch block
    				e.printStackTrace();
    			}
    		}
    		return lista;
    	}
    public List<Exames> buscarsubgrupo(){
        List <Exames> lista = new ArrayList<Exames>();
    		String sql = "SELECT * FROM tblsubgrupo sub INNER JOIN tblgrupodeanalises gp ON sub.FK_grupo_exame = gp.id_grupo_analises_clinicas  order by nome_subgrupo"; 
    		try {
    			 con = Conexao.getConexao();
    			PreparedStatement preparador = con.prepareStatement(sql);
    			 ResultSet rs = preparador.executeQuery();
    			 while(rs.next())
    			 {
    				 Exames pac = new Exames();
    				 pac.setId_sub_grupo(rs.getInt("id_subgrupo"));  					
    				 pac.setNome_sub_grupo(rs.getString("nome_subgrupo"));
    				 pac.setGrupo_danalise(rs.getString("grupo_danalise"));
    				 lista.add(pac);
    			 }
    			 preparador.close();
    		} catch (SQLException e) {
    			// TODO Auto-generated catch block
    			e.printStackTrace();
    		}
    		catch (Exception e) {
    			// TODO: handle exception
    		}
    		finally {
    			try {
    				con.close();
    			} catch (SQLException e) {
    				// TODO Auto-generated catch block
    				e.printStackTrace();
    			}
    		}
    		return lista;
    	}
    
    public List<Exames> buscarsubgrupoEdit(int codigo){
        List <Exames> lista = new ArrayList<Exames>();
    		String sql = "SELECT * FROM tblsubgrupo sub INNER JOIN tblgrupodeanalises gp ON sub.FK_grupo_exame = gp.id_grupo_analises_clinicas where sub.id_subgrupo =? order by nome_subgrupo;"; 
    		try {
    			 con = Conexao.getConexao();
    			PreparedStatement preparador = con.prepareStatement(sql);
    			preparador.setInt(1, codigo);
    			 ResultSet rs = preparador.executeQuery();
    			 while(rs.next())
    			 {
    				 Exames pac = new Exames();
    				 pac.setId_sub_grupo(rs.getInt("id_subgrupo"));  					
    				 pac.setNome_sub_grupo(rs.getString("nome_subgrupo"));
    				 pac.setGrupo_danalise(rs.getString("grupo_danalise"));
    				 lista.add(pac);
    			 }
    			 preparador.close();
    		} catch (SQLException e) {
    			// TODO Auto-generated catch block
    			e.printStackTrace();
    		}
    		catch (Exception e) {
    			// TODO: handle exception
    		}
    		finally {
    			try {
    				con.close();
    			} catch (SQLException e) {
    				// TODO Auto-generated catch block
    				e.printStackTrace();
    			}
    		}
    		return lista;
    	}
    
    public List<Exames> buscaAmostraExames(){
        List <Exames> lista = new ArrayList<Exames>();
    		String sql = "SELECT * FROM tblamostraparaexames order by amostra_para_exame";
    		try {
    			 con = Conexao.getConexao();
    			PreparedStatement preparador = con.prepareStatement(sql);
    			 ResultSet rs = preparador.executeQuery();
    			 while(rs.next())
    			 {
    				 Exames pac = new Exames();
    				 pac.setId_amostra(rs.getInt("id_amostra_para_exames"));  					
    				 pac.setObservacao_amostra(rs.getString("amostra_para_exame"));				
    				 lista.add(pac);
    			 }
    			 preparador.close();
    		} catch (SQLException e) {
    			// TODO Auto-generated catch block
    			e.printStackTrace();
    		}
    		catch (Exception e) {
    			// TODO: handle exception
    		}
    		finally {
    			try {
    				con.close();
    			} catch (SQLException e) {
    				// TODO Auto-generated catch block
    				e.printStackTrace();
    			}
    		}
    		return lista;
    	}
    
    public void ExcluirUmServicoExame(int cod){
    		 
    		String sql = "DELETE FROM tblservicosdeexamesclinicos where id_servicodeanalise_clinica = ?";
    		try {
    			 con = Conexao.getConexao();
    			PreparedStatement preparador = con.prepareStatement(sql);
    			preparador.setInt(1, cod);
    			preparador.execute();
    			preparador.close();
    			System.out.println("EXCLUIDO COM SUCESSO-EX");
    		} catch (SQLException e) {
    			// TODO Auto-generated catch block
    			e.printStackTrace();
    		}
    		catch (Exception e) {
    			 
    		}
    		finally {
    			try {
    				con.close();
    			} catch (SQLException e) {
    				 
    				e.printStackTrace();
    			}
    		}
    		 
    	}
    
    public void ExcluirUmServicoExameClinico(int cod){
		 
		String sql = "DELETE FROM tblexamesclinicoscadastrados where id_exames_clinicos_cadastrados = ?";
		try {
			 con = Conexao.getConexao();
			PreparedStatement preparador = con.prepareStatement(sql);
			preparador.setInt(1, cod);
			preparador.execute();
			preparador.close();
			System.out.println("EXCLUIDO COM SUCESSO-EX");
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		catch (Exception e) {
			 
		}
		finally {
			try {
				con.close();
			} catch (SQLException e) {
				 
				e.printStackTrace();
			}
		}
		 
	}
    
    public void ExcluirUmReferencialExame(int cod){
		 
		String sql = "DELETE FROM tblvalores_referenciais_lab where id_valores_referencias_lab = ?";
		try {
			 con = Conexao.getConexao();
			PreparedStatement preparador = con.prepareStatement(sql);
			preparador.setInt(1, cod);
			preparador.execute();
			preparador.close();
			System.out.println("EXCLUIDO COM SUCESSO-EX");
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		catch (Exception e) {
			 
		}
		finally {
			try {
				con.close();
			} catch (SQLException e) {
				 
				e.printStackTrace();
			}
		} 
	}
    
    public void ExcluirTesteRapidoExame(int cod){
		 
		String sql = "DELETE FROM tblvalor_resultado where id_valor_resultado = ?";
		try {
			 con = Conexao.getConexao();
			PreparedStatement preparador = con.prepareStatement(sql);
			preparador.setInt(1, cod);
			preparador.execute();
			preparador.close();
			System.out.println("EXCLUIDO COM SUCESSO-EX");
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		catch (Exception e) {
			 
		}
		finally {
			try {
				con.close();
			} catch (SQLException e) {
				 
				e.printStackTrace();
			}
		}
		 
	}
    
    public void ExcluirUmGrupoLabExame(int cod){
		 
		String sql = "DELETE FROM tblgrupo_lab where id_grupo_lab = ?";
		try {
			 con = Conexao.getConexao();
			PreparedStatement preparador = con.prepareStatement(sql);
			preparador.setInt(1, cod);
			preparador.execute();
			preparador.close();
			System.out.println("EXCLUIDO COM SUCESSO-EX");
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		catch (Exception e) {
			 
		}
		finally {
			try {
				con.close();
			} catch (SQLException e) {
				 
				e.printStackTrace();
			}
		}
		 
	}
    
    public void ExcluirUmParametroExame(int cod){
		 
		String sql = "DELETE FROM tblparametro_lab where id_parametro_lab = ?";
		try {
			 con = Conexao.getConexao();
			PreparedStatement preparador = con.prepareStatement(sql);
			preparador.setInt(1, cod);
			preparador.execute();
			preparador.close();
			System.out.println("EXCLUIDO COM SUCESSO-EX");
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		catch (Exception e) {
			 
		}
		finally {
			try {
				con.close();
			} catch (SQLException e) {
				 
				e.printStackTrace();
			}
		}
		 
	}
    
    public void ExcluirFaixaEtariaExame(int cod){
		 
		String sql = "DELETE FROM tblfaixa_etaria_lab where id_facha_etaria_lab = ?";
		try {
			 con = Conexao.getConexao();
			PreparedStatement preparador = con.prepareStatement(sql);
			preparador.setInt(1, cod);
			preparador.execute();
			preparador.close();
			System.out.println("EXCLUIDO COM SUCESSO-EX");
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		catch (Exception e) {
			 
		}
		finally {
			try {
				con.close();
			} catch (SQLException e) {
				 
				e.printStackTrace();
			}
		}
		 
	}
    
    public void ExcluirUnidadeExameClinico(int cod){
		 
		String sql = "DELETE FROM tblunidade_para_exames_lab where id_unidade_para_exames_lab = ?";
		try {
			 con = Conexao.getConexao();
			PreparedStatement preparador = con.prepareStatement(sql);
			preparador.setInt(1, cod);
			preparador.execute();
			preparador.close();
			System.out.println("EXCLUIDO COM SUCESSO-EX");
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		catch (Exception e) {
			 
		}
		finally {
			try {
				con.close();
			} catch (SQLException e) {
				 
				e.printStackTrace();
			}
		}
		 
	}
    
    public void ExcluirUmGrupoExame(int cod){
		 
		String sql = "DELETE FROM tblgrupodeanalises where id_grupo_analises_clinicas = ?";
		try {
			 con = Conexao.getConexao();
			PreparedStatement preparador = con.prepareStatement(sql);
			preparador.setInt(1, cod);
			preparador.execute();
			preparador.close();
			System.out.println("EXCLUIDO COM SUCESSO-GRUPOEX");
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		catch (Exception e) {
			 
		}
		finally {
			try {
				con.close();
			} catch (SQLException e) {
				 
				e.printStackTrace();
			}
		}
		 
	}
    
    public void ExcluirUnidade(int cod){
		 
		String sql = "DELETE FROM tblunidade where id_unidade = ?";
		try {
			 con = Conexao.getConexao();
			PreparedStatement preparador = con.prepareStatement(sql);
			preparador.setInt(1, cod);
			preparador.execute();
			preparador.close();
			System.out.println("EXCLUIDO COM SUCESSO-Unidade");
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		catch (Exception e) {
			 
		}
		finally {
			try {
				con.close();
			} catch (SQLException e) {
				 
				e.printStackTrace();
			}
		}
		 
	}
    
    public void ExcluirUmSubGrupo(int cod){
		 
		String sql = "DELETE FROM tblsubgrupo where id_subgrupo = ?";
		try {
			 con = Conexao.getConexao();
			PreparedStatement preparador = con.prepareStatement(sql);
			preparador.setInt(1, cod);
			preparador.execute();
			preparador.close();
			System.out.println("EXCLUIDO COM SUCESSO-GRUPOEX");
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		catch (Exception e) {
			 
		}
		finally {
			try {
				con.close();
			} catch (SQLException e) {
				 
				e.printStackTrace();
			}
		}
		 
	}
    
    public Exames buscaUmGrupoExame(int cod){
		Exames pac = new Exames(); 
		String sql = "SELECT * FROM tblgrupodeanalises where id_grupo_analises_clinicas = ?";
		try {
			 con = Conexao.getConexao();
			PreparedStatement preparador = con.prepareStatement(sql);
			preparador.setInt(1, cod);
			 ResultSet rs = preparador.executeQuery();
			 if(rs.next())
			 {
				 pac.setId_grupo_analises_clinicas(rs.getInt("id_grupo_analises_clinicas"));  					
				 pac.setGrupo_danalise(rs.getString("grupo_danalise"));				  
			 }
			 preparador.close();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		catch (Exception e) {
			// TODO: handle exception
		}
		finally {
			try {
				con.close();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		return pac;
	}
    
    public Exames buscaUmSubGrupo(int cod){
		Exames pac = new Exames(); 
		String sql = "SELECT * FROM tblsubgrupo sub INNER JOIN tblgrupodeanalises gp ON sub.FK_grupo_exame = gp.id_grupo_analises_clinicas where sub.id_subgrupo = ? order by nome_subgrupo";
		try {
			 con = Conexao.getConexao();
			PreparedStatement preparador = con.prepareStatement(sql);
			preparador.setInt(1, cod);
			 ResultSet rs = preparador.executeQuery();
			 if(rs.next())
			 {
				 pac.setId_sub_grupo(rs.getInt("id_subgrupo"));  					
				 pac.setNome_sub_grupo(rs.getString("nome_subgrupo"));
				 pac.setFK_grupo_analise_clinica(rs.getInt("FK_grupo_exame"));
			 }
			 preparador.close();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		catch (Exception e) {
			// TODO: handle exception
		}
		finally {
			try {
				con.close();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		return pac;
	}
    
    public Exames buscaUmUnidade(int cod){
		Exames pac = new Exames(); 
		String sql = "SELECT * FROM tblunidade where id_unidade = ? order by unidade";
		try {
			 con = Conexao.getConexao();
			PreparedStatement preparador = con.prepareStatement(sql);
			preparador.setInt(1, cod);
			 ResultSet rs = preparador.executeQuery();
			 if(rs.next())
			 {
				 pac.setId_unidade(rs.getInt("id_unidade"));  					
				 pac.setUnidade(rs.getString("unidade"));
			 }
			 preparador.close();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		catch (Exception e) {
			// TODO: handle exception
		}
		finally {
			try {
				con.close();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		return pac;
	}
    
    public boolean XeckGrupoExame(String termo){
		 
		String sql = "SELECT * FROM tblgrupodeanalises where grupo_danalise = ?";
		try {
			 con = Conexao.getConexao();
			PreparedStatement preparador = con.prepareStatement(sql);
			preparador.setString(1, termo);
			 ResultSet rs = preparador.executeQuery();
			 if(rs.next())
			 {
				 return true;			  
			 }
			 preparador.close();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		catch (Exception e) {
			// TODO: handle exception
		}
		finally {
			try {
				con.close();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		return false;
	}
    
    public boolean XeckUnidade(String termo){
		 
		String sql = "SELECT * FROM tblunidade where unidade = ?";
		try {
			 con = Conexao.getConexao();
			PreparedStatement preparador = con.prepareStatement(sql);
			preparador.setString(1, termo);
			 ResultSet rs = preparador.executeQuery();
			 if(rs.next())
			 {
				 return true;			  
			 }
			 preparador.close();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		catch (Exception e) {
			// TODO: handle exception
		}
		finally {
			try {
				con.close();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		return false;
	}
    
    public boolean XeckSubGrupo(String termo){
		 
		String sql = "SELECT * FROM tblsubgrupo where nome_subgrupo = ?";
		try {
			 con = Conexao.getConexao();
			PreparedStatement preparador = con.prepareStatement(sql);
			preparador.setString(1, termo);
			 ResultSet rs = preparador.executeQuery();
			 if(rs.next())
			 {
				 return true;			  
			 }
			 preparador.close();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		catch (Exception e) {
			// TODO: handle exception
		}
		finally {
			try {
				con.close();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		return false;
	}
    
    public boolean XeckServicoExame(String termo){
		 
		String sql = "SELECT * FROM tblservicosdeexamesclinicos where id_servicodeanalise_clinica = ?";
		try {
			 con = Conexao.getConexao();
			PreparedStatement preparador = con.prepareStatement(sql);
			preparador.setString(1, termo);
			 ResultSet rs = preparador.executeQuery();
			 if(rs.next())
			 {
				 return true;			  
			 }
			 preparador.close();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		catch (Exception e) {
			// TODO: handle exception
		}
		finally {
			try {
				con.close();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		return false;
	}
    
    public boolean XeckServicoExameClinico(String termo){
		 
		String sql = "SELECT * FROM tblexamesclinicoscadastrados where exames_clinicos_cadastrados = ?";
		try {
			 con = Conexao.getConexao();
			PreparedStatement preparador = con.prepareStatement(sql);
			preparador.setString(1, termo);
			 ResultSet rs = preparador.executeQuery();
			 if(rs.next())
			 {
				 return true;			  
			 }
			 preparador.close();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		catch (Exception e) {
			// TODO: handle exception
		}
		finally {
			try {
				con.close();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		return false;
	}
    
    public boolean XeckReferenciaExame(int servico, int parametro, int genero, String vlrMinMasc, String vlrMaxMasc, String vlrMinFem, String vlrMaxFem, int unidade, int faixa){
		 
		String sql = "SELECT * FROM tblvalores_referenciais_lab where fk_servico_exame = ? and fk_parametro =? and fk_genero =? and valor_minimo_masculino =? and valor_maximo_masculino =? and valor_minimo_feminino =? and valor_maximo_feminino =? and fk_unidade_genero =? and fk_faixa_etaria =?";
		try {
			 con = Conexao.getConexao();
			PreparedStatement preparador = con.prepareStatement(sql);
			preparador.setInt(1, servico);
			preparador.setInt(2, parametro);
			preparador.setInt(3, genero);
			preparador.setString(4, vlrMinMasc);
			preparador.setString(5, vlrMaxMasc);
			preparador.setString(6, vlrMinFem);
			preparador.setString(7, vlrMaxFem);
			preparador.setInt(8, unidade);
			preparador.setInt(9, faixa);
			ResultSet rs = preparador.executeQuery();
			 if(rs.next())
			 {
				 return true;			  
			 }
			 preparador.close();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		catch (Exception e) {
			// TODO: handle exception
		}
		finally {
			try {
				con.close();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		return false;
	}
    
    public boolean XeckTesteRapidoExame(int servico, int parametro, int genero, int faixa, int resultado){
		 
		String sql = "SELECT * FROM tblvalor_resultado where fk_servico_exame = ? and fk_parametro =? and fk_genero =? and fk_faixa_etaria = ? and fk_resultado =?";
		try {
			 con = Conexao.getConexao();
			PreparedStatement preparador = con.prepareStatement(sql);
			preparador.setInt(1, servico);
			preparador.setInt(2, parametro);
			preparador.setInt(3, genero);
			preparador.setInt(4, faixa);
			preparador.setInt(5, resultado);
			ResultSet rs = preparador.executeQuery();
			 if(rs.next())
			 {
				 return true;			  
			 }
			 preparador.close();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		catch (Exception e) {
			// TODO: handle exception
		}
		finally {
			try {
				con.close();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		return false;
	}
    
    public boolean XeckParametroExame(String termo, int codigo, int grupo){
		 
		String sql = "SELECT * FROM tblparametro_lab where parametro_lab = ? and fk_servico_exame =? and fk_grupo_lab =?";
		try {
			 con = Conexao.getConexao();
			PreparedStatement preparador = con.prepareStatement(sql);
			preparador.setString(1, termo);
			preparador.setInt(2, codigo);
			preparador.setInt(3, grupo);
			 ResultSet rs = preparador.executeQuery();
			 if(rs.next())
			 {
				 return true;			  
			 }
			 preparador.close();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		catch (Exception e) {
			// TODO: handle exception
		}
		finally {
			try {
				con.close();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		return false;
	}
    
    public boolean XeckGrupoLabExame(String termo){
		 
		String sql = "SELECT * FROM tblgrupo_lab where grupo_lab = ?";
		try {
			 con = Conexao.getConexao();
			PreparedStatement preparador = con.prepareStatement(sql);
			preparador.setString(1, termo);
			 ResultSet rs = preparador.executeQuery();
			 if(rs.next())
			 {
				 return true;			  
			 }
			 preparador.close();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		catch (Exception e) {
			// TODO: handle exception
		}
		finally {
			try {
				con.close();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		return false;
	}
    
    public boolean XeckFaixaEtariaExame(String termo, int idade_inicial, int idade_final){
		 
		String sql = "SELECT * FROM tblfaixa_etaria_lab where facha_etaria_lab = ? and idade_inicial =? and idade_fim =?";
		try {
			 con = Conexao.getConexao();
			PreparedStatement preparador = con.prepareStatement(sql);
			preparador.setString(1, termo);
			preparador.setInt(2, idade_inicial);
			preparador.setInt(3, idade_final);
			 ResultSet rs = preparador.executeQuery();
			 if(rs.next())
			 {
				 return true;			  
			 }
			 preparador.close();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		catch (Exception e) {
			// TODO: handle exception
		}
		finally {
			try {
				con.close();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		return false;
	}
    
    public boolean XeckUnidadeExame(String termo){
		 
		String sql = "SELECT * FROM tblunidade_para_exames_lab where unidade_para_exames_lab = ?";
		try {
			 con = Conexao.getConexao();
			PreparedStatement preparador = con.prepareStatement(sql);
			preparador.setString(1, termo);
			 ResultSet rs = preparador.executeQuery();
			 if(rs.next())
			 {
				 return true;			  
			 }
			 preparador.close();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		catch (Exception e) {
			// TODO: handle exception
		}
		finally {
			try {
				con.close();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		return false;
	}
    
    public List<Funcionario> buscarProfLaboratorio(){
        List <Funcionario> lista = new ArrayList<Funcionario>();
    		String sql = "SELECT * FROM vwcidadaomediconomecompleto";
    		try {
    			 con = Conexao.getConexao();
    			PreparedStatement preparador = con.prepareStatement(sql);
    			 ResultSet rs = preparador.executeQuery();
    			 while(rs.next())
    			 {
    				 Funcionario f = new Funcionario();
    				 f.setId(rs.getInt("entidade"));
    				 f.setNome(rs.getString("nomecompleto"));
    				 lista.add(f);
    			 }
    			 preparador.close();
    		} catch (SQLException e) {
    			// TODO Auto-generated catch block
    			e.printStackTrace();
    		}
    		catch (Exception e) {
    			// TODO: handle exception
    		}
    		finally {
    			try {
    				con.close();
    			} catch (SQLException e) {
    				// TODO Auto-generated catch block
    				e.printStackTrace();
    			}
    		}
    		return lista;
    	}
    
    
    public List<Exames> buscagrupoexame (int codigo)
	{
		List <Exames> lista = new ArrayList<Exames>();
		String sql = "SELECT * FROM tblservicosdeexamesclinicos where FK_grupo_analise_clinica=  ?";
		try {
			 con = Conexao.getConexao(); 
			PreparedStatement preparador = con.prepareStatement(sql);
			 preparador.setInt(1, codigo);
			 ResultSet rs = preparador.executeQuery();
			 while(rs.next())
			 {
				 Exames bc = new Exames();
				 bc.setId_servicodeanalise_clinica(rs.getInt("id_servicodeanalise_clinica"));
				 bc.setAnalise_clinica(rs.getString("analise_clinica"));
				 bc.setPreco(rs.getDouble("preco"));
				 lista.add(bc);
			 }
			 preparador.close();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		catch (Exception e) {
			// TODO: handle exception
		}
		finally {
			try {
				con.close();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}}
		return lista;
	}
    
    public List<Exames> buscagrupoexame ()
	{
		List <Exames> lista = new ArrayList<Exames>();
		String sql = "SELECT * FROM tblservicosdeexamesclinicos";
		try {
			 con = Conexao.getConexao(); 
			PreparedStatement preparador = con.prepareStatement(sql);
			 ResultSet rs = preparador.executeQuery();
			 while(rs.next())
			 {
				 Exames bc = new Exames();
				 bc.setId_servicodeanalise_clinica(rs.getInt("id_servicodeanalise_clinica"));
				 bc.setAnalise_clinica(rs.getString("analise_clinica"));
				 bc.setPreco(rs.getDouble("preco"));
				 lista.add(bc);
			 }
			 preparador.close();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		catch (Exception e) {
			// TODO: handle exception
		}
		finally {
			try {
				con.close();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}}
		return lista;
	}
    
    public List<Exames> buscarServicosExames ()
	{
		List <Exames> lista = new ArrayList<Exames>();
		String sql = "SELECT * FROM vwexameslaboratoriais order by grupo_danalise";
		try {
			 con = Conexao.getConexao(); 
			 PreparedStatement preparador = con.prepareStatement(sql);
			 ResultSet rs = preparador.executeQuery();
			 while(rs.next())
			 {
				 Exames bc = new Exames();
				 bc.setId_servicodeanalise_clinica(rs.getInt("id_servicodeanalise_clinica"));
				 bc.setAnalise_clinica(rs.getString("analise_clinica"));
				 bc.setPreco(rs.getDouble("preco"));
				 bc.setGrupo_danalise(rs.getString("grupo_danalise"));
				 bc.setId_grupo_analises_clinicas(rs.getInt("id_grupo_analises_clinicas"));
				 bc.setDescricao_servico_analise(rs.getString("obs_servico_exame"));
				 bc.setTaxa(rs.getString("taxa"));
				 bc.setPreco_txa(rs.getDouble("preco_taxado"));
				 bc.setDisponibilidade(rs.getInt("disponibilidade"));
				 lista.add(bc);
			 }
			 preparador.close();
		} catch (SQLException e) {
			 
			e.printStackTrace();
		}
		catch (Exception e) {
			 System.out.println(e.getMessage());
		}
		finally {
			try {
				con.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}}
		return lista;
	}
    
    public List<Exames> buscarServicosExamesClinico()
	{
		List <Exames> lista = new ArrayList<Exames>();
		String sql = "SELECT * FROM vwexamesclinicoscadastrados WHERE id_tipos_servico_exames = 2 order by exames_clinicos_cadastrados";
		try {
			 con = Conexao.getConexao(); 
			 PreparedStatement preparador = con.prepareStatement(sql);
			 ResultSet rs = preparador.executeQuery();
			 while(rs.next())
			 {
				 Exames bc = new Exames();
				 bc.setId_servicodeanalise_clinica(rs.getInt("id_exames_clinicos_cadastrados"));
				 bc.setAnalise_clinica(rs.getString("exames_clinicos_cadastrados"));
				 bc.setDescricao_servico_analise(rs.getString("nota_explicativa"));
				 bc.setGrupo_danalise(rs.getString("grupo_danalise"));
				 bc.setCodigobarra(rs.getInt("codigo_barra"));
				 bc.setSexo(rs.getInt("genero"));
				 bc.setId_grupo_analises_clinicas(rs.getInt("fk_grupo"));
				 bc.setDisponibilidade(rs.getInt("disponibilidade"));
				 bc.setRecomendacao(rs.getString("recomendacoes"));
				 bc.setAbreviatura((rs.getString("abreviacao")));
				 bc.setTipo_deamostra(rs.getString("tipo_deamostra"));
				 bc.setId_amostra(rs.getInt("fk_amostra"));
				 bc.setNome_categoria(rs.getString("simbolo_categoria"));
				 bc.setNome_disponibilidade(rs.getString("simbolo_disponibilidade"));
				 bc.setNome_genero(rs.getString("simbolo_genero"));
				 lista.add(bc);
			 }
			 preparador.close();
		} catch (SQLException e) {
			 
			e.printStackTrace();
		}
		catch (Exception e) {
			 System.out.println(e.getMessage());
		}
		finally {
			try {
				con.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}}
		return lista;
	}
    
    public List<Exames> buscarTesteRapidoExamesClinico()
	{
		List <Exames> lista = new ArrayList<Exames>();
		String sql = "SELECT * FROM vwteste_rapido";
		try {
			 con = Conexao.getConexao(); 
			 PreparedStatement preparador = con.prepareStatement(sql);
			 ResultSet rs = preparador.executeQuery();
			 while(rs.next())
			 {
				 Exames bc = new Exames();
				 bc.setId_valor_resultado(rs.getInt("id_valor_resultado"));
				 bc.setFK_servico_exame(rs.getInt("fk_servico_exame"));
				 bc.setAnalise_clinica(rs.getString("exames_clinicos_cadastrados"));
				 bc.setParametro_lab(rs.getString("parametro_lab"));
				 bc.setId_parametro_lab(rs.getInt("fk_parametro"));
				 bc.setSexo(rs.getInt("fk_genero"));
				 bc.setId(rs.getInt("fk_faixa_etaria"));
				 bc.setGenero(rs.getString("genero"));
				 bc.setResultado(rs.getString("resultado"));
				 bc.setFaixa_etaria(rs.getString("facha_etaria_lab"));
				 lista.add(bc);
			 }
			 preparador.close();
		} catch (SQLException e) {
			 
			e.printStackTrace();
		}
		catch (Exception e) {
			 System.out.println(e.getMessage());
		}
		finally {
			try {
				con.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}}
		return lista;
	}
    
    public List<Exames> buscarTesteRapidoExamesClinico(int codigo)
	{
		List <Exames> lista = new ArrayList<Exames>();
		String sql = "SELECT * FROM vwteste_rapido WHERE id_valor_resultado =?";
		try {
			 con = Conexao.getConexao(); 
			 PreparedStatement preparador = con.prepareStatement(sql);
			 preparador.setInt(1, codigo);
			 ResultSet rs = preparador.executeQuery();
			 while(rs.next())
			 {
				 Exames bc = new Exames();
				 bc.setId_valor_resultado(rs.getInt("id_valor_resultado"));
				 bc.setFK_servico_exame(rs.getInt("fk_servico_exame"));
				 bc.setAnalise_clinica(rs.getString("exames_clinicos_cadastrados"));
				 bc.setParametro_lab(rs.getString("parametro_lab"));
				 bc.setId_parametro_lab(rs.getInt("fk_parametro"));
				 bc.setSexo(rs.getInt("fk_genero"));
				 bc.setId(rs.getInt("fk_faixa_etaria"));
				 bc.setGenero(rs.getString("genero"));
				 bc.setResultado(rs.getString("resultado"));
				 bc.setFaixa_etaria(rs.getString("facha_etaria_lab"));
				 bc.setId_resultado(rs.getInt("fk_resultado"));
				 lista.add(bc);
			 }
			 preparador.close();
		} catch (SQLException e) {
			 
			e.printStackTrace();
		}
		catch (Exception e) {
			 System.out.println(e.getMessage());
		}
		finally {
			try {
				con.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}}
		return lista;
	}
    
    public List<Exames> buscarReferenciaExamesClinico()
	{
		List <Exames> lista = new ArrayList<Exames>();
		String sql = "SELECT * FROM vwvalores_de_referencias ";
		try {
			 con = Conexao.getConexao(); 
			 PreparedStatement preparador = con.prepareStatement(sql);
			 ResultSet rs = preparador.executeQuery();
			 while(rs.next())
			 {
				 Exames bc = new Exames();
				 bc.setId_valores_referencias_lab(rs.getInt("id_valores_referencias_lab"));
				 bc.setAnalise_clinica(rs.getString("exames_clinicos_cadastrados"));
				 bc.setParametro_lab(rs.getString("parametro_lab"));
				 bc.setId_parametro_lab(rs.getInt("fk_parametro"));
				 bc.setNome_genero(rs.getString("genero"));
				 bc.setValor_maximo_feminino(rs.getString("valor_maximo_feminino"));
				 bc.setValor_minimo_masculino(rs.getString("valor_minimo_masculino"));
				 bc.setValor_maximo_masculino(rs.getString("valor_maximo_masculino"));
				 bc.setValor_minimo_feminino(rs.getString("valor_minimo_feminino"));
				 bc.setUnidade(rs.getString("unidade_genero"));
				 bc.setFaixa_etaria(rs.getString("facha_etaria_lab"));
				 lista.add(bc);
			 }
			 preparador.close();
		} catch (SQLException e) {
			 
			e.printStackTrace();
		}
		catch (Exception e) {
			 System.out.println(e.getMessage());
		}
		finally {
			try {
				con.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}}
		return lista;
	}
    
    public List<Exames> buscarReferenciaExamesClinicoOrder()
	{
		List <Exames> lista = new ArrayList<Exames>();
		String sql = "SELECT * FROM vwvalores_de_referencias GROUP BY fk_servico_exame ";
		try {
			 con = Conexao.getConexao(); 
			 PreparedStatement preparador = con.prepareStatement(sql);
			 ResultSet rs = preparador.executeQuery();
			 while(rs.next())
			 {
				 Exames bc = new Exames();
				 bc.setId_valores_referencias_lab(rs.getInt("id_valores_referencias_lab"));
				 bc.setAnalise_clinica(rs.getString("exames_clinicos_cadastrados"));
				 bc.setFK_servico_exame(rs.getInt("fk_servico_exame"));
				 bc.setParametro_lab(rs.getString("parametro_lab"));
				 bc.setId_parametro_lab(rs.getInt("fk_parametro"));
				 bc.setNome_genero(rs.getString("genero"));
				 bc.setValor_maximo_feminino(rs.getString("valor_maximo_feminino"));
				 bc.setValor_minimo_masculino(rs.getString("valor_minimo_masculino"));
				 bc.setValor_maximo_masculino(rs.getString("valor_maximo_masculino"));
				 bc.setValor_minimo_feminino(rs.getString("valor_minimo_feminino"));
				 bc.setUnidade(rs.getString("unidade_genero"));
				 bc.setFaixa_etaria(rs.getString("facha_etaria_lab"));
				 lista.add(bc);
			 }
			 preparador.close();
		} catch (SQLException e) {
			 
			e.printStackTrace();
		}
		catch (Exception e) {
			 System.out.println(e.getMessage());
		}
		finally {
			try {
				con.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}}
		return lista;
	}
    
    public List<Exames> buscarReferenciaExamesClinico(int codigo)
	{
		List <Exames> lista = new ArrayList<Exames>();
		String sql = "SELECT * FROM vwvalores_de_referencias WHERE id_valores_referencias_lab =? ";
		try {
			 con = Conexao.getConexao(); 
			 PreparedStatement preparador = con.prepareStatement(sql);
			 preparador.setInt(1, codigo);
			 ResultSet rs = preparador.executeQuery();
			 while(rs.next())
			 {
				 Exames bc = new Exames();
				 bc.setId_valores_referencias_lab(rs.getInt("id_valores_referencias_lab"));
				 bc.setAnalise_clinica(rs.getString("exames_clinicos_cadastrados"));
				 bc.setFK_servico_exame(rs.getInt("fk_servico_exame"));
				 bc.setParametro_lab(rs.getString("parametro_lab"));
				 bc.setId_parametro_lab(rs.getInt("fk_parametro"));
				 bc.setNome_genero(rs.getString("genero"));
				 bc.setSexo(rs.getInt("fk_genero"));
				 bc.setValor_minimo_feminino(rs.getString("valor_minimo_feminino"));
				 bc.setValor_maximo_feminino(rs.getString("valor_maximo_feminino"));
				 bc.setValor_minimo_masculino(rs.getString("valor_minimo_masculino"));
				 bc.setValor_maximo_masculino(rs.getString("valor_maximo_masculino"));
				 bc.setFk_unidade_genero(rs.getInt("fk_unidade_genero"));
				 bc.setId(rs.getInt("fk_faixa_etaria"));
				 lista.add(bc);
			 }
			 preparador.close();
		} catch (SQLException e) {
			 
			e.printStackTrace();
		}
		catch (Exception e) {
			 System.out.println(e.getMessage());
		}
		finally {
			try {
				con.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}}
		return lista;
	}
    
    public List<Exames> buscarReferenciaExamesClinicoModel(int codigo)
	{
		List <Exames> lista = new ArrayList<Exames>();
		String sql = "SELECT * FROM vwvalores_de_referencias WHERE fk_servico_exame =? ";
		try {
			 con = Conexao.getConexao(); 
			 PreparedStatement preparador = con.prepareStatement(sql);
			 preparador.setInt(1, codigo);
			 ResultSet rs = preparador.executeQuery();
			 while(rs.next())
			 {
				 Exames bc = new Exames();
				 bc.setId_valores_referencias_lab(rs.getInt("id_valores_referencias_lab"));
				 bc.setAnalise_clinica(rs.getString("exames_clinicos_cadastrados"));
				 bc.setFK_servico_exame(rs.getInt("fk_servico_exame"));
				 bc.setParametro_lab(rs.getString("parametro_lab"));
				 bc.setId_parametro_lab(rs.getInt("fk_parametro"));
				 bc.setNome_genero(rs.getString("genero"));
				 bc.setSexo(rs.getInt("fk_genero"));
				 bc.setValor_minimo_feminino(rs.getString("valor_minimo_feminino"));
				 bc.setValor_maximo_feminino(rs.getString("valor_maximo_feminino"));
				 bc.setValor_minimo_masculino(rs.getString("valor_minimo_masculino"));
				 bc.setValor_maximo_masculino(rs.getString("valor_maximo_masculino"));
				 bc.setFk_unidade_genero(rs.getInt("fk_unidade_genero"));
				 bc.setId(rs.getInt("fk_faixa_etaria"));
				 lista.add(bc);
			 }
			 preparador.close();
		} catch (SQLException e) {
			 
			e.printStackTrace();
		}
		catch (Exception e) {
			 System.out.println(e.getMessage());
		}
		finally {
			try {
				con.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}}
		return lista;
	}
    
    public List<Exames> buscarParametroExames()
   	{
   		List <Exames> lista = new ArrayList<Exames>();
   		String sql = "SELECT * FROM tblparametro_lab p INNER JOIN vwexamesclinicoscadastrados e ON p.FK_servico_exame = e.id_exames_clinicos_cadastrados INNER JOIN tblgrupo_lab g ON p.fk_grupo_lab = g.id_grupo_lab";
   		try {
   			 con = Conexao.getConexao(); 
   			 PreparedStatement preparador = con.prepareStatement(sql);
   			 ResultSet rs = preparador.executeQuery();
   			 while(rs.next())
   			 {
   				 Exames bc = new Exames();
   				 bc.setId_parametro_lab(rs.getInt("id_parametro_lab"));
   				 bc.setId_grupo_lab(rs.getInt("id_grupo_lab"));
   				 bc.setParametro_lab(rs.getString("parametro_lab"));
   				 bc.setGrupo_lab(rs.getString("grupo_lab"));
   				 bc.setAnalise_clinica(rs.getString("exames_clinicos_cadastrados"));
   				 lista.add(bc);
   			 }
   			 preparador.close();
   		} catch (SQLException e) {
   			 
   			e.printStackTrace();
   		}
   		catch (Exception e) {
   			 System.out.println(e.getMessage());
   		}
   		finally {
   			try {
   				con.close();
   			} catch (SQLException e) {
   				e.printStackTrace();
   			}}
   		return lista;
   	}
    
    public List<Exames> buscarGrupoLabExames()
   	{
   		List <Exames> lista = new ArrayList<Exames>();
   		String sql = "SELECT * FROM tblgrupo_lab";
   		try {
   			 con = Conexao.getConexao(); 
   			 PreparedStatement preparador = con.prepareStatement(sql);
   			 ResultSet rs = preparador.executeQuery();
   			 while(rs.next())
   			 {
   				 Exames bc = new Exames();
   				 bc.setId_grupo_lab(rs.getInt("id_grupo_lab"));
   				 bc.setGrupo_lab(rs.getString("grupo_lab"));
   				 lista.add(bc);
   			 }
   			 preparador.close();
   		} catch (SQLException e) {
   			 
   			e.printStackTrace();
   		}
   		catch (Exception e) {
   			 System.out.println(e.getMessage());
   		}
   		finally {
   			try {
   				con.close();
   			} catch (SQLException e) {
   				e.printStackTrace();
   			}}
   		return lista;
   	}
    
    public List<Exames> buscarGrupoLabExames(int codigo)
   	{
   		List <Exames> lista = new ArrayList<Exames>();
   		String sql = "SELECT * FROM tblgrupo_lab where id_grupo_lab =?";
   		try {
   			 con = Conexao.getConexao(); 
   			 PreparedStatement preparador = con.prepareStatement(sql);
   			 preparador.setInt(1, codigo);
   			 ResultSet rs = preparador.executeQuery();
   			 while(rs.next())
   			 {
   				 Exames bc = new Exames();
   				 bc.setId_grupo_lab(rs.getInt("id_grupo_lab"));
   				 bc.setGrupo_lab(rs.getString("grupo_lab"));
   				 lista.add(bc);
   			 }
   			 preparador.close();
   		} catch (SQLException e) {
   			 
   			e.printStackTrace();
   		}
   		catch (Exception e) {
   			 System.out.println(e.getMessage());
   		}
   		finally {
   			try {
   				con.close();
   			} catch (SQLException e) {
   				e.printStackTrace();
   			}}
   		return lista;
   	}
    
    public List<Exames> buscarParametroExamesHD(int codigo)
   	{
   		List <Exames> lista = new ArrayList<Exames>();
   		String sql = "SELECT * FROM tblparametro_lab p INNER JOIN vwexamesclinicoscadastrados e ON p.FK_servico_exame = e.id_exames_clinicos_cadastrados WHERE p.FK_servico_exame = ?";
   		try {
   			 con = Conexao.getConexao(); 
   			 PreparedStatement preparador = con.prepareStatement(sql);
   			 preparador.setInt(1, codigo);
   			 ResultSet rs = preparador.executeQuery();
   			 while(rs.next())
   			 {
   				 Exames bc = new Exames();
   				 bc.setId_parametro_lab(rs.getInt("id_parametro_lab"));
   				 bc.setParametro_lab(rs.getString("parametro_lab"));
   				 bc.setAnalise_clinica(rs.getString("exames_clinicos_cadastrados"));
   				 lista.add(bc);
   			 }
   			 preparador.close();
   		} catch (SQLException e) {
   			 
   			e.printStackTrace();
   		}
   		catch (Exception e) {
   			 System.out.println(e.getMessage());
   		}
   		finally {
   			try {
   				con.close();
   			} catch (SQLException e) {
   				e.printStackTrace();
   			}}
   		return lista;
   	}
    
    public List<Exames> buscarParametroExames(int codigo)
   	{
   		List <Exames> lista = new ArrayList<Exames>();
   		String sql = "SELECT * FROM tblparametro_lab p INNER JOIN vwexamesclinicoscadastrados e ON p.FK_servico_exame = e.id_exames_clinicos_cadastrados INNER JOIN tblgrupo_lab g ON p.fk_grupo_lab = g.id_grupo_lab WHERE P.id_parametro_lab =?";
   		try {
   			 con = Conexao.getConexao(); 
   			 PreparedStatement preparador = con.prepareStatement(sql);
   			 preparador.setInt(1, codigo);
   			 ResultSet rs = preparador.executeQuery();
   			 while(rs.next())
   			 {
   				 Exames bc = new Exames();
   				 bc.setId_parametro_lab(rs.getInt("id_parametro_lab"));
   				 bc.setId_servicodeanalise_clinica(rs.getInt("id_exames_clinicos_cadastrados"));
   				 bc.setId_grupo_lab(rs.getInt("id_grupo_lab"));
   				 bc.setParametro_lab(rs.getString("parametro_lab"));
   				 bc.setGrupo_lab(rs.getString("grupo_lab"));
   				 bc.setAnalise_clinica(rs.getString("exames_clinicos_cadastrados"));
   				 lista.add(bc);
   			 }
   			 preparador.close();
   		} catch (SQLException e) {
   			 
   			e.printStackTrace();
   		}
   		catch (Exception e) {
   			 System.out.println(e.getMessage());
   		}
   		finally {
   			try {
   				con.close();
   			} catch (SQLException e) {
   				e.printStackTrace();
   			}}
   		return lista;
   	}
    
    public List<Exames> buscarFaixaEtariaExames()
	{
		List <Exames> lista = new ArrayList<Exames>();
		String sql = "SELECT * FROM tblfaixa_etaria_lab";
		try {
			 con = Conexao.getConexao(); 
			 PreparedStatement preparador = con.prepareStatement(sql);
			 ResultSet rs = preparador.executeQuery();
			 while(rs.next())
			 {
				 Exames bc = new Exames();
				 bc.setId(rs.getInt("id_facha_etaria_lab"));
				 bc.setFaixa_etaria(rs.getString("facha_etaria_lab"));
				 bc.setIdade_inicial(rs.getInt("idade_inicial"));
				 bc.setIdade_final(rs.getInt("idade_fim"));
				 lista.add(bc);
			 }
			 preparador.close();
		} catch (SQLException e) {
			 
			e.printStackTrace();
		}
		catch (Exception e) {
			 System.out.println(e.getMessage());
		}
		finally {
			try {
				con.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}}
		return lista;
	}
    
    public List<Exames> buscarResultadoDoExames()
	{
		List <Exames> lista = new ArrayList<Exames>();
		String sql = "SELECT * FROM tblresultado";
		try {
			 con = Conexao.getConexao(); 
			 PreparedStatement preparador = con.prepareStatement(sql);
			 ResultSet rs = preparador.executeQuery();
			 while(rs.next())
			 {
				 Exames bc = new Exames();
				 bc.setId_resultado(rs.getInt("id_resultado"));
				 bc.setResultado(rs.getString("resultado"));
				 lista.add(bc);
			 }
			 preparador.close();
		} catch (SQLException e) {
			 
			e.printStackTrace();
		}
		catch (Exception e) {
			 System.out.println(e.getMessage());
		}
		finally {
			try {
				con.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}}
		return lista;
	}
    
    public List<Exames> buscarFaixaEtariaExames(int codigo)
   	{
   		List <Exames> lista = new ArrayList<Exames>();
   		String sql = "SELECT * FROM tblfaixa_etaria_lab WHERE id_facha_etaria_lab =?";
   		try {
   			 con = Conexao.getConexao(); 
   			 PreparedStatement preparador = con.prepareStatement(sql);
   			 preparador.setInt(1, codigo);
   			 ResultSet rs = preparador.executeQuery();
   			 while(rs.next())
   			 {
   				 Exames bc = new Exames();
   				 bc.setId(rs.getInt("id_facha_etaria_lab"));
   				 bc.setFaixa_etaria(rs.getString("facha_etaria_lab"));
   				 bc.setIdade_inicial(rs.getInt("idade_inicial"));
   				 bc.setIdade_final(rs.getInt("idade_fim"));
   				 lista.add(bc);
   			 }
   			 preparador.close();
   		} catch (SQLException e) {
   			 
   			e.printStackTrace();
   		}
   		catch (Exception e) {
   			 System.out.println(e.getMessage());
   		}
   		finally {
   			try {
   				con.close();
   			} catch (SQLException e) {
   				e.printStackTrace();
   			}}
   		return lista;
   	}
    
    public List<Exames> buscarUnidadeExames()
	{
		List <Exames> lista = new ArrayList<Exames>();
		String sql = "SELECT * FROM tblunidade_para_exames_lab";
		try {
			 con = Conexao.getConexao(); 
			 PreparedStatement preparador = con.prepareStatement(sql);
			 ResultSet rs = preparador.executeQuery();
			 while(rs.next())
			 {
				 Exames bc = new Exames();
				 bc.setId_unidade(rs.getInt("id_unidade_para_exames_lab"));
				 bc.setUnidade(rs.getString("unidade_para_exames_lab"));
				 lista.add(bc);
			 }
			 preparador.close();
		} catch (SQLException e) {
			 
			e.printStackTrace();
		}
		catch (Exception e) {
			 System.out.println(e.getMessage());
		}
		finally {
			try {
				con.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}}
		return lista;
	}
    
    public List<Exames> buscarUnidadeExames(int codigo)
	{
		List <Exames> lista = new ArrayList<Exames>();
		String sql = "SELECT * FROM tblunidade_para_exames_lab WHERE id_unidade_para_exames_lab =?";
		try {
			 con = Conexao.getConexao(); 
			 PreparedStatement preparador = con.prepareStatement(sql);
			 preparador.setInt(1, codigo);
			 ResultSet rs = preparador.executeQuery();
			 while(rs.next())
			 {
				 Exames bc = new Exames();
				 bc.setId_unidade(rs.getInt("id_unidade_para_exames_lab"));
				 bc.setUnidade(rs.getString("unidade_para_exames_lab"));
				 lista.add(bc);
			 }
			 preparador.close();
		} catch (SQLException e) {
			 
			e.printStackTrace();
		}
		catch (Exception e) {
			 System.out.println(e.getMessage());
		}
		finally {
			try {
				con.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}}
		return lista;
	}
    
    public List<Exames> buscarServicosExamesClinicoImag()
	{
		List <Exames> lista = new ArrayList<Exames>();
		String sql = "SELECT * FROM vwexamesclinicoscadastrados WHERE id_tipos_servico_exames = 1 order by exames_clinicos_cadastrados";
		try {
			 con = Conexao.getConexao(); 
			 PreparedStatement preparador = con.prepareStatement(sql);
			 ResultSet rs = preparador.executeQuery();
			 while(rs.next())
			 {
				 Exames bc = new Exames();
				 bc.setId_servicodeanalise_clinica(rs.getInt("id_exames_clinicos_cadastrados"));
				 bc.setAnalise_clinica(rs.getString("exames_clinicos_cadastrados"));
				 bc.setDescricao_servico_analise(rs.getString("nota_explicativa"));
				 bc.setGrupo_danalise(rs.getString("grupo_danalise"));
				 bc.setCodigobarra(rs.getInt("codigo_barra"));
				 bc.setSexo(rs.getInt("genero"));
				 bc.setId_grupo_analises_clinicas(rs.getInt("fk_grupo"));
				 bc.setDisponibilidade(rs.getInt("disponibilidade"));
				 bc.setRecomendacao(rs.getString("recomendacoes"));
				 bc.setAbreviatura((rs.getString("abreviacao")));
				 bc.setTipo_deamostra(rs.getString("tipo_deamostra"));
				 bc.setId_amostra(rs.getInt("fk_amostra"));
				 bc.setNome_categoria(rs.getString("simbolo_categoria"));
				 bc.setNome_disponibilidade(rs.getString("simbolo_disponibilidade"));
				 bc.setNome_genero(rs.getString("simbolo_genero"));
				 bc.setId_sub_grupo(rs.getInt("FK_sub_grupo"));
				 lista.add(bc);
			 }
			 preparador.close();
		} catch (SQLException e) {
			 
			e.printStackTrace();
		}
		catch (Exception e) {
			 System.out.println(e.getMessage());
		}
		finally {
			try {
				con.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}}
		return lista;
	}
    
    public List<Exames> buscarServicosExamesClinicox()
	{
		List <Exames> lista = new ArrayList<Exames>();
		String sql = "SELECT * FROM vwexamesclinicoscadastrados order by exames_clinicos_cadastrados";
		try {
			 con = Conexao.getConexao(); 
			 PreparedStatement preparador = con.prepareStatement(sql);
			 ResultSet rs = preparador.executeQuery();
			 while(rs.next())
			 {
				 Exames bc = new Exames();
				 bc.setId_servicodeanalise_clinica(rs.getInt("id_exames_clinicos_cadastrados"));
				 bc.setAnalise_clinica(rs.getString("exames_clinicos_cadastrados"));
				 bc.setDescricao_servico_analise(rs.getString("nota_explicativa"));
				 bc.setGrupo_danalise(rs.getString("grupo_danalise"));
				 bc.setCodigobarra(rs.getInt("codigo_barra"));
				 bc.setSexo(rs.getInt("genero"));
				 bc.setId_grupo_analises_clinicas(rs.getInt("fk_grupo"));
				 bc.setDisponibilidade(rs.getInt("disponibilidade"));
				 bc.setRecomendacao(rs.getString("recomendacoes"));
				 bc.setAbreviatura((rs.getString("abreviacao")));
				 bc.setTipo_deamostra(rs.getString("tipo_deamostra"));
				 bc.setId_amostra(rs.getInt("fk_amostra"));
				 bc.setNome_categoria(rs.getString("simbolo_categoria"));
				 bc.setNome_disponibilidade(rs.getString("simbolo_disponibilidade"));
				 bc.setNome_genero(rs.getString("simbolo_genero"));
				 lista.add(bc);
			 }
			 preparador.close();
		} catch (SQLException e) {
			 
			e.printStackTrace();
		}
		catch (Exception e) {
			 System.out.println(e.getMessage());
		}
		finally {
			try {
				con.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}}
		return lista;
	}
    
    public Exames buscarServicosExames (int cod)
	{
    	 Exames exame = new Exames(); 
		String sql = "SELECT * FROM tblservicosdeexamesclinicos where id_servicodeanalise_clinica = ?";
		try {
			 con = Conexao.getConexao(); 
			 PreparedStatement preparador = con.prepareStatement(sql);
			 preparador.setInt(1, cod);
			 ResultSet rs = preparador.executeQuery();
			 if(rs.next())
			 {
				 exame.setId_servicodeanalise_clinica(rs.getInt("id_servicodeanalise_clinica"));
				 exame.setPreco(rs.getDouble("preco_servico_exame"));
				 exame.setFK_grupo_analise_clinica(rs.getInt("FK_servico_exame"));
				 exame.setCodigobarra(rs.getInt("codigobarra"));
				 exame.setFk_taxa(rs.getInt("FK_taxa"));
				 exame.setDescricao_servico_analise(rs.getString("obs_servico_exame"));
			 }
			 preparador.close();
		} catch (SQLException e) {
			 
			e.printStackTrace();
		}
		catch (Exception e) {
			 System.out.println(e.getMessage());
		}
		finally {
			try {
				con.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}}
		return exame;
	}
    
    
    public List<Exames> buscarServicosExamesPorGrupo (int cod)
	{
    	 List<Exames> lista = new ArrayList<Exames>(); 
		String sql = "SELECT * FROM tblexamesclinicoscadastrados where fk_tipo_serv_exames = ?";
		try {
			 con = Conexao.getConexao(); 
			 PreparedStatement preparador = con.prepareStatement(sql);
			 preparador.setInt(1, cod);
			 ResultSet rs = preparador.executeQuery();
			 while(rs.next())
			 {
				 Exames exame = new Exames(); 
				 exame.setFK_exame(rs.getInt("id_exames_clinicos_cadastrados"));
				 exame.setDescricao_servico_analise(rs.getString("exames_clinicos_cadastrados"));
				 lista.add(exame);
			 }
			 preparador.close();
			 con.close();
		}
		catch (Exception e) {
			 e.printStackTrace();
		}
	 
		return lista;
	}
    
    public List<Exames> buscarExamesClinicosCadastradoss(String termo,String sexo)
	{
		List <Exames> lista = new ArrayList<Exames>();
		String sql = "SELECT * FROM vwexamesclinicoscadastrados where exames_clinicos_cadastrados = ? and (genero = 3 or genero = ?) and disponibilidade <> 0";
		try {
			con = Conexao.getConexao(); 
			PreparedStatement preparador = con.prepareStatement(sql);
			 preparador.setString(1, termo+"%");
			 preparador.setString(2, sexo);
			 ResultSet rs = preparador.executeQuery();
			 while(rs.next())
			 {
				 Exames bc = new Exames();
				 bc.setId_servicodeanalise_clinica(rs.getInt("id_exames_clinicos_cadastrados"));
				 bc.setAnalise_clinica(rs.getString("exames_clinicos_cadastrados"));
//				 bc.setPreco(rs.getDouble("preco"));
				 lista.add(bc);
			 }
			 preparador.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		catch (Exception e) {
			// TODO: handle exception
		}
		finally {
			try {
				con.close();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}}
		return lista;
	}
    
    public Exames buscarServicosExamesClinico (int cod)
	{
    	 Exames bc = new Exames(); 
		String sql = "SELECT * FROM vwexamesclinicoscadastrados WHERE id_exames_clinicos_cadastrados =? order by exames_clinicos_cadastrados";
		try {
			 con = Conexao.getConexao(); 
			 PreparedStatement preparador = con.prepareStatement(sql);
			 preparador.setInt(1, cod);
			 ResultSet rs = preparador.executeQuery();
			 if(rs.next())
			 {
				 bc.setId_servicodeanalise_clinica(rs.getInt("id_exames_clinicos_cadastrados"));
				 bc.setAnalise_clinica(rs.getString("exames_clinicos_cadastrados"));
				 bc.setDescricao_servico_analise(rs.getString("nota_explicativa"));
				 bc.setGrupo_danalise(rs.getString("grupo_danalise"));
				 bc.setCodigobarra(rs.getInt("codigo_barra"));
				 bc.setSexo(rs.getInt("genero"));
				 bc.setFK_grupo_analise_clinica(rs.getInt("fk_grupo"));
				 bc.setDisponibilidade(rs.getInt("disponibilidade"));
				 bc.setRecomendacao(rs.getString("recomendacoes"));
				 bc.setAbreviatura(rs.getString("abreviacao"));
				 bc.setTipo_deamostra(rs.getString("tipo_deamostra"));
				 bc.setId_amostra(rs.getInt("fk_amostra"));
				 bc.setNome_categoria(rs.getString("simbolo_categoria"));
				 bc.setNome_disponibilidade(rs.getString("simbolo_disponibilidade"));
				 bc.setNome_genero(rs.getString("simbolo_genero"));
				 bc.setCategoria(rs.getInt("categoria"));
				 bc.setId_sub_grupo(rs.getInt("FK_sub_grupo"));
			 }
			 preparador.close();
		} catch (SQLException e) {
			 
			e.printStackTrace();
		}
		catch (Exception e) {
			 System.out.println(e.getMessage());
		}
		finally {
			try {
				con.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}}
		return bc;
	}
    
    public List<Exames> buscarExamesPorIniciais (String termo,String sexo)
	{
		List <Exames> lista = new ArrayList<Exames>();
		String sql = "SELECT * FROM vwexameslaboratoriais where  analise_clinica Like ? and (sexo = 3 or sexo = ?)";
		try {
			con = Conexao.getConexao(); 
			PreparedStatement preparador = con.prepareStatement(sql);
			 preparador.setString(1, termo+"%");
			 preparador.setString(2, sexo);
			 ResultSet rs = preparador.executeQuery();
			 while(rs.next())
			 {
				 Exames bc = new Exames();
				 bc.setId_servicodeanalise_clinica(rs.getInt("id_servicodeanalise_clinica"));
				 bc.setAnalise_clinica(rs.getString("analise_clinica"));
				 bc.setPreco(rs.getDouble("preco"));
				 lista.add(bc);
			 }
			 preparador.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		catch (Exception e) {
			// TODO: handle exception
		}
		finally {
			try {
				con.close();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}}
		return lista;
	}
    
    public List<Exames> buscarExamesClinicosCadastrados (String codigo, String termo)
   	{
   		List <Exames> lista = new ArrayList<Exames>();
   		String sql = "SELECT * FROM vwexamesclinicoscadastrados where fk_grupo = ? and exames_clinicos_cadastrados = ? and disponibilidade <> 0";
   		try {
   			con = Conexao.getConexao(); 
   			PreparedStatement preparador = con.prepareStatement(sql);
   			 preparador.setString(1, codigo);
   			 preparador.setString(2, termo+"%");
   			 ResultSet rs = preparador.executeQuery();
   			 while(rs.next())
   			 {
   				 Exames bc = new Exames();
   				 bc.setId_servicodeanalise_clinica(rs.getInt("id_exames_clinicos_cadastrados"));
   				 bc.setAnalise_clinica(rs.getString("exames_clinicos_cadastrados"));
//   				 bc.setPreco(rs.getDouble("preco"));
   				 lista.add(bc);
   			 }
   			 preparador.close();
   		} catch (SQLException e) {
   			e.printStackTrace();
   		}
   		catch (Exception e) {
   			// TODO: handle exception
   		}
   		finally {
   			try {
   				con.close();
   			} catch (SQLException e) {
   				// TODO Auto-generated catch block
   				e.printStackTrace();
   			}}
   		return lista;
   	}
    
    public List<Exames> buscagrupoexame (String codigo, String termo)
	{
		List <Exames> lista = new ArrayList<Exames>();
		String sql = "SELECT * FROM vwexameslaboratoriais where id_grupo_analises_clinicas Like ? and analise_clinica Like ?";
		try {
			con = Conexao.getConexao(); 
			PreparedStatement preparador = con.prepareStatement(sql);
			 preparador.setString(1, codigo);
			 preparador.setString(2, termo+"%");
			 ResultSet rs = preparador.executeQuery();
			 while(rs.next())
			 {
				 Exames bc = new Exames();
				 bc.setId_servicodeanalise_clinica(rs.getInt("id_servicodeanalise_clinica"));
				 bc.setAnalise_clinica(rs.getString("analise_clinica"));
				 bc.setPreco(rs.getDouble("preco"));
				 lista.add(bc);
			 }
			 preparador.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		catch (Exception e) {
			// TODO: handle exception
		}
		finally {
			try {
				con.close();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}}
		return lista;
	}
    
    public Exames buscagrupoexame (String termo)
	{
    	Exames exams = null;
		String sql = "SELECT * FROM vwexameslaboratoriais where analise_clinica = ? or id_servicodeanalise_clinica = ?";
		try {
			con = Conexao.getConexao(); 
			PreparedStatement preparador = con.prepareStatement(sql);
			 preparador.setString(1, termo);
			 preparador.setString(2, termo);
			 ResultSet rs = preparador.executeQuery();
			 if(rs.next())
			 {
				 exams = new Exames();
				 exams.setId_servicodeanalise_clinica(rs.getInt("FK_servico_exame"));
				 exams.setAnalise_clinica(rs.getString("analise_clinica"));
				 exams.setPreco(rs.getDouble("preco"));
				 exams.setGrupo_danalise(rs.getString("grupo_danalise"));
			 }
			 preparador.close();
		} catch (SQLException e) {
			System.out.println(e);
		}
		catch (Exception e) {
			// TODO: handle exception
		}
		finally {
			try {
				con.close();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}}
		return exams;
	}
    
    
    public Servico buscagrupoexameQuali (String nomeServico)
	{
//    	Servico todosServicos = new ArrayList<Servico>(); 
    	Servico servicosQual = null;
   		String sqlServicos = "SELECT id_servico_tq,serv_teste_qualidade,preco,descricao_tq,tx.taxa FROM tbltaxas tx, tblservicosdetestedequalidade stq where stq.FK_taxa=tx.id_taxa and serv_teste_qualidade like ?";
   		try {
   			con = Conexao.getConexao(); 
   			PreparedStatement preparador = con.prepareStatement(sqlServicos);
   			preparador.setString(1, nomeServico);
   			ResultSet rs = preparador.executeQuery();

   			 while(rs.next())
   			 {
   			
   				servicosQual = new Servico();
   				servicosQual.setId_servico_tq(rs.getInt("id_servico_tq"));
   				servicosQual.setTaxa(rs.getDouble("taxa"));
   				servicosQual.setServ_teste_qualidade(rs.getString("serv_teste_qualidade"));
   				servicosQual.setPreco(rs.getDouble("preco"));
   				servicosQual.setDescricao_tq(rs.getString("descricao_tq"));
   				servicosQual.setTaxaEmkz(servicosQual.getTaxa());
   				servicosQual.setSubTotal(rs.getDouble("preco"));
//   				todosServicos.add(servicosQual);
   			 }
   			 preparador.close();
   		} catch (SQLException e) {
   			System.out.println(e);
   		}
   		catch (Exception e) {
   			e.printStackTrace();
   		}
   		finally {
   			try {
   				con.close();
   			} catch (SQLException e) {
   				// TODO Auto-generated catch block
   				e.printStackTrace();
   			}}
   		return servicosQual;
   		}
    
    //Salvar na tabela tblnumerodoexame
    public int inserirnumerodoexame(Exames ex)
	{
    	int ultimoId = 0;
    	String sql = "insert into tblnumerodoexame (numero_do_exame,FK_paciente,FK_profissional,recomendacao,observacao,data_registo) values(?,?,?,?,?,?)";
		try {
			con = Conexao.getConexao(); 
			PreparedStatement preparador = con.prepareStatement(sql);
			 preparador = con.prepareStatement(sql);			
			 preparador.setString(1, ex.getNumero_do_exame());
			 preparador.setInt(2, ex.getFK_paciente());
			 preparador.setInt(3, ex.getFk_profissional());
			 preparador.setString(4, ex.getRecomendacao());
			 preparador.setString(5, ex.getObservacao_recomendacao());
			 preparador.setDate(6, new Formatando().data_registo());
			 preparador.execute();
			 ResultSet rs = preparador.executeQuery("SELECT LAST_INSERT_ID()");
			 if(rs.next())
			      ultimoId = rs.getInt(1);
			 preparador.close();
			
		} catch (SQLException e) {
			throw new RuntimeException(e);
		}
		catch (Exception e) {
			e.printStackTrace();
		}
		finally {
			try {
				con.close();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}}
		return ultimoId;
	}
    
  //Pega ID da tblnumerodoexame
	
  		public Exames getIdNumeroExame(Exames f){ 
  			   
  			Exames t = null;
  			  String sql = "SELECT * FROM tblnumerodoexame where  numero_do_exame = ? and FK_paciente = ?";
  			  try
  			  {
  				con = Conexao.getConexao();
  				PreparedStatement preparador = con.prepareStatement(sql);
  			    preparador.setString(1, f.getNumero_do_exame());
  			    preparador.setInt(2, f.getFK_paciente());  			    
  			   
  			    ResultSet rs = preparador.executeQuery();
  			    if(rs.last()){
  			     t = new Exames();
  			     t.setFK_numero_do_servico_de_exame(rs.getInt("id_numero_do_exame"));
  			    
  			    }
  			     preparador.close(); 
  			  }
  			  catch(SQLException er){
  			   throw new RuntimeException(er);
  			  }
  			catch (Exception e) {
  				// TODO: handle exception
  			}
  			finally {
  				try {
  					con.close();
  				} catch (SQLException e) {
  					// TODO Auto-generated catch block
  					e.printStackTrace();
  				}}
  			  return t;
  			 }
    
    //Salvar na tabela tblexamerequisitado
  	public int getNumExmRequisitado(int FK_paciente){
  		int NumSerEx = 0;
  		String sql ="Select * from tblnumerodoexame where FK_paciente = ? and data_registo = curdate()";
  		try {
			con = Conexao.getConexao();
			PreparedStatement ps = con.prepareStatement(sql);
			ps.setInt(1, FK_paciente);
			ResultSet rs = ps.executeQuery();
			if(rs.next()){
				NumSerEx = rs.getInt("id_numero_do_exame");
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
  		return NumSerEx;
  	}
    public void inseriExameReq(Exames ex)
	{
		String sql = "insert into tblexamerequisitado (FK_servico_exame, FK_funcionario,FK_paciente,FK_tipo_dcobertura,data,hora,gestante_pra_exame,FK_numero_do_servico_de_exame,FK_provenienca_exame) values(?,?,?,?,?,?,?,?,?)";
		try {
			con = Conexao.getConexao(); 
			PreparedStatement preparador = con.prepareStatement(sql);
			 preparador = con.prepareStatement(sql);
			 preparador.setInt(1, ex.getId_servicodeanalise_clinica());
			 preparador.setInt(2, ex.getFK_funcionario());
			 preparador.setInt(3, ex.getFK_paciente());
			 preparador.setInt(4, ex.getFK_tipo_dcobertura());
			 preparador.setDate(5, new Formatando().data_registo());
			 preparador.setString(6, new Formatando().getHoraAtual());
			 preparador.setInt(7, ex.getGestante());
			 preparador.setInt(8, ex.getFK_numero_do_servico_de_exame());
			 preparador.setInt(9, ex.getFK_provenienca_exame());
			 preparador.execute();
			 preparador.close();
			
		} catch (SQLException e) {
			throw new RuntimeException(e);
		}
		catch (Exception e) {
			System.out.println(e.getMessage());
		}
		finally {
			try {
				con.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
	}
    
    public void salvarServicoExame(Exames ex)
	{
		String sql = "insert into tblservicosdeexamesclinicos (FK_servico_exame,codigobarra,preco_servico_exame,FK_taxa,obs_servico_exame) values(?,?,?,?,?)";
		try {
			con = Conexao.getConexao(); 
			PreparedStatement preparador = con.prepareStatement(sql);
			 preparador = con.prepareStatement(sql);
			 preparador.setInt(1, ex.getFK_grupo_analise_clinica());
			 preparador.setInt(2, ex.getCodigobarra());
			 preparador.setDouble(3, ex.getPreco());
			 preparador.setInt(4, ex.getFk_taxa());
			 preparador.setString(5, ex.getDescricao_servico_analise());
			 preparador.execute();
			 preparador.close();
			
		} catch (SQLException e) {
			throw new RuntimeException(e);
		}
		catch (Exception e) {
			System.out.println(e.getMessage());
		}
		finally {
			try {
				con.close();
			} catch (SQLException e) {
				e.printStackTrace(); 
			}
		}
	}
    
    // Busca de Grupo de Exames
    public List<Exames> buscaExameClinico(){
    List <Exames> lista = new ArrayList<Exames>();
		String sql = "SELECT * FROM tblexamesclinicoscadastrados order by exames_clinicos_cadastrados";
		try {
			 con = Conexao.getConexao();
			PreparedStatement preparador = con.prepareStatement(sql);
			 ResultSet rs = preparador.executeQuery();
			 while(rs.next())
			 {
				 Exames pac = new Exames();
				 pac.setId_grupo_analises_clinicas(rs.getInt("id_exames_clinicos_cadastrados"));  					
				 pac.setGrupo_danalise(rs.getString("exames_clinicos_cadastrados"));				
				 lista.add(pac);
			 }
			 preparador.close();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		catch (Exception e) {
			// TODO: handle exception
		}
		finally {
			try {
				con.close();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		return lista;
	}
    
    public void salvarServicoExameClinico(Exames ex)
	{
		String sql = "insert into tblexamesclinicoscadastrados (fk_grupo,exames_clinicos_cadastrados,nota_explicativa,codigo_barra,genero,disponibilidade,categoria,recomendacoes,abreviacao,fk_amostra,fk_tipo_serv_exames,FK_sub_grupo) values(?,?,?,?,?,?,?,?,?,?,?,?)";
		try {
			con = Conexao.getConexao(); 
			PreparedStatement preparador = con.prepareStatement(sql);
			 preparador = con.prepareStatement(sql);
			 preparador.setInt(1, ex.getFK_grupo_analise_clinica());
			 preparador.setString(2, ex.getAnalise_clinica());
			 preparador.setString(3, ex.getDescricao_servico_analise());
			 preparador.setInt(4, ex.getCodigobarra());
			 preparador.setInt(5, ex.getSexo());
			 preparador.setInt(6, ex.getDisponibilidade());
			 preparador.setInt(7, ex.getCategoria());
			 preparador.setString(8, ex.getRecomendacao());
			 preparador.setString(9, ex.getAbreviatura());
			 preparador.setInt(10, ex.getId_amostra());
			 preparador.setInt(11, ex.getFK_tipo_servicoEx());
			 preparador.setInt(12, ex.getId_sub_grupo());
			 preparador.execute();
			 preparador.close();
			
		} catch (SQLException e) {
			throw new RuntimeException(e);
		}
		catch (Exception e) {
			System.out.println(e.getMessage());
		}
		finally {
			try {
				con.close();
			} catch (SQLException e) {
				e.printStackTrace(); 
			}
		}
	}
    
    public void salvarReferenciaExame(Exames ex)
	{
		String sql = "insert into tblvalores_referenciais_lab (fk_servico_exame,fk_parametro,fk_genero,valor_minimo_masculino,valor_maximo_masculino,valor_minimo_feminino,valor_maximo_feminino,fk_unidade_genero,fk_faixa_etaria) values(?,?,?,?,?,?,?,?,?)";
		try {
			con = Conexao.getConexao(); 
			PreparedStatement preparador = con.prepareStatement(sql);
			 preparador = con.prepareStatement(sql);
			 preparador.setInt(1, ex.getFK_servico_exame());
			 preparador.setInt(2, ex.getId_parametro_lab());
			 preparador.setInt(3, ex.getSexo());
			 preparador.setString(4, ex.getValor_minimo_masculino());
			 preparador.setString(5, ex.getValor_maximo_masculino());
			 preparador.setString(6, ex.getValor_minimo_feminino());
			 preparador.setString(7, ex.getValor_maximo_feminino());
			 preparador.setInt(8, ex.getFk_unidade_genero());
			 preparador.setInt(9, ex.getId());
			 preparador.execute();
			 preparador.close();
			
		} catch (SQLException e) {
			throw new RuntimeException(e);
		}
		catch (Exception e) {
			System.out.println(e.getMessage());
		}
		finally {
			try {
				con.close();
			} catch (SQLException e) {
				e.printStackTrace(); 
			}
		}
	}
    
    public void salvarValorResultadoExame(Exames ex)
	{
		String sql = "insert into tblvalor_resultado (fk_servico_exame,fk_parametro,fk_genero,fk_faixa_etaria,fk_resultado) values (?,?,?,?,?)";
		try {
			con = Conexao.getConexao(); 
			PreparedStatement preparador = con.prepareStatement(sql);
			 preparador = con.prepareStatement(sql);
			 preparador.setInt(1, ex.getFK_servico_exame());
			 preparador.setInt(2, ex.getId_parametro_lab());
			 preparador.setInt(3, ex.getSexo());
			 preparador.setInt(4, ex.getId());
			 preparador.setInt(5, ex.getId_resultado());
			 preparador.execute();
			 preparador.close();
			
		} catch (SQLException e) {
			throw new RuntimeException(e);
		}
		catch (Exception e) {
			System.out.println(e.getMessage());
		}
		finally {
			try {
				con.close();
			} catch (SQLException e) {
				e.printStackTrace(); 
			}
		}
	}
    
    public void editarValorResultadoExame(Exames ex)
   	{
   		String sql = "update tblvalor_resultado set fk_servico_exame =?,fk_parametro =?,fk_genero =?,fk_faixa_etaria =?, fk_resultado =? where id_valor_resultado = ?";
   		try {
   			con = Conexao.getConexao(); 
   			PreparedStatement preparador = con.prepareStatement(sql);
   			 preparador = con.prepareStatement(sql);
   			 preparador.setInt(1, ex.getFK_servico_exame());
   			 preparador.setInt(2, ex.getId_parametro_lab());
   			 preparador.setInt(3, ex.getSexo());
   			 preparador.setInt(4, ex.getId());
   			 preparador.setInt(5, ex.getId_resultado());
   			 preparador.setInt(6, ex.getId_valor_resultado());
   			 preparador.execute();
   			 preparador.close();
   			
   		} catch (SQLException e) {
   			throw new RuntimeException(e);
   		}
   		catch (Exception e) {
   			System.out.println(e.getMessage());
   		}
   		finally {
   			try {
   				con.close();
   			} catch (SQLException e) {
   				e.printStackTrace();
   			}
   		}
   	}
    
    public void editarReferenciaExame(Exames ex)
	{
		String sql = "update tblvalores_referenciais_lab set fk_servico_exame =?,fk_parametro =?,fk_genero =?,valor_minimo_masculino =?,valor_maximo_masculino =?,valor_minimo_feminino =?,valor_maximo_feminino =?,fk_unidade_genero =?,fk_faixa_etaria =? where id_valores_referencias_lab = ?";
		try {
			con = Conexao.getConexao(); 
			PreparedStatement preparador = con.prepareStatement(sql);
			 preparador = con.prepareStatement(sql);
			 preparador.setInt(1, ex.getFK_servico_exame());
			 preparador.setInt(2, ex.getId_parametro_lab());
			 preparador.setInt(3, ex.getSexo());
			 preparador.setString(4, ex.getValor_minimo_masculino());
			 preparador.setString(5, ex.getValor_maximo_masculino());
			 preparador.setString(6, ex.getValor_minimo_feminino());
			 preparador.setString(7, ex.getValor_maximo_feminino());
			 preparador.setInt(8, ex.getFk_unidade_genero());
			 preparador.setInt(9, ex.getId());
			 preparador.setInt(10, ex.getId_valores_referencias_lab());
			 preparador.execute();
			 preparador.close();
			
		} catch (SQLException e) {
			throw new RuntimeException(e);
		}
		catch (Exception e) {
			System.out.println(e.getMessage());
		}
		finally {
			try {
				con.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
	}
    
    public void salvarParametroExame(Exames ex)
	{
		String sql = "insert into tblparametro_lab (parametro_lab,fk_servico_exame,fk_grupo_lab) values(?,?,?)";
		try {
			con = Conexao.getConexao(); 
			PreparedStatement preparador = con.prepareStatement(sql);
			 preparador = con.prepareStatement(sql);
			 preparador.setString(1, ex.getParametro_lab());
			 preparador.setInt(2, ex.getFK_servico_exame());
			 preparador.setInt(3, ex.getId_grupo_lab());
			 preparador.execute();
			 preparador.close();
			
		} catch (SQLException e) {
			throw new RuntimeException(e);
		}
		catch (Exception e) {
			System.out.println(e.getMessage());
		}
		finally {
			try {
				con.close();
			} catch (SQLException e) {
				e.printStackTrace(); 
			}
		}
	}
    
    public void salvarGrupoExame(Exames ex)
	{
		String sql = "insert into tblgrupo_lab (grupo_lab) values(?)";
		try {
			con = Conexao.getConexao(); 
			PreparedStatement preparador = con.prepareStatement(sql);
			 preparador = con.prepareStatement(sql);
			 preparador.setString(1, ex.getGrupo_lab());
			 preparador.execute();
			 preparador.close();
			
		} catch (SQLException e) {
			throw new RuntimeException(e);
		}
		catch (Exception e) {
			System.out.println(e.getMessage());
		}
		finally {
			try {
				con.close();
			} catch (SQLException e) {
				e.printStackTrace(); 
			}
		}
	}
    
    public void salvarFaixaEtariaExame(Exames ex)
	{
		String sql = "insert into tblfaixa_etaria_lab (facha_etaria_lab,idade_inicial,idade_fim) values(?,?,?)";
		try {
			con = Conexao.getConexao(); 
			PreparedStatement preparador = con.prepareStatement(sql);
			 preparador = con.prepareStatement(sql);
			 preparador.setString(1, ex.getFaixa_etaria());
			 preparador.setInt(2, ex.getIdade_inicial());
			 preparador.setInt(3, ex.getIdade_final());
			 preparador.execute();
			 preparador.close();
			
		} catch (SQLException e) {
			throw new RuntimeException(e);
		}
		catch (Exception e) {
			System.out.println(e.getMessage());
		}
		finally {
			try {
				con.close();
			} catch (SQLException e) {
				e.printStackTrace(); 
			}
		}
	}
    
    public void editarServicoExame(Exames ex)
	{
		String sql = "update tblservicosdeexamesclinicos set FK_servico_exame = ?,codigobarra = ?,preco_servico_exame = ?,FK_taxa = ?,obs_servico_exame = ? where id_servicodeanalise_clinica = ?";
		try {
			con = Conexao.getConexao(); 
			PreparedStatement preparador = con.prepareStatement(sql);
			 preparador = con.prepareStatement(sql);
			 preparador.setInt(1, ex.getFK_grupo_analise_clinica());
			 preparador.setInt(2, ex.getCodigobarra());
			 preparador.setDouble(3, ex.getPreco());
			 preparador.setInt(4, ex.getFk_taxa());
			 preparador.setString(5, ex.getDescricao_servico_analise());
			 preparador.setInt(6, ex.getId_servicodeanalise_clinica());
			 preparador.execute();
			 preparador.close();
			
		} catch (SQLException e) {
			throw new RuntimeException(e);
		}
		catch (Exception e) {
			System.out.println(e.getMessage());
		}
		finally {
			try {
				con.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
	}
    
    public void editarServicoExameClinico(Exames ex)
	{
		String sql = "update tblexamesclinicoscadastrados set fk_grupo =?,exames_clinicos_cadastrados =?,nota_explicativa =?,codigo_barra =?,genero =?,disponibilidade =?,categoria =?,recomendacoes =?,abreviacao =?,fk_amostra =?, FK_sub_grupo =? where id_exames_clinicos_cadastrados = ?";
		try {
			con = Conexao.getConexao(); 
			PreparedStatement preparador = con.prepareStatement(sql);
			 preparador = con.prepareStatement(sql);
			 preparador.setInt(1, ex.getFK_grupo_analise_clinica());
			 preparador.setString(2, ex.getAnalise_clinica());
			 preparador.setString(3, ex.getDescricao_servico_analise());
			 preparador.setInt(4, ex.getCodigobarra());
			 preparador.setInt(5, ex.getSexo());
			 preparador.setInt(6, ex.getDisponibilidade());
			 preparador.setInt(7, ex.getCategoria());
			 preparador.setString(8, ex.getRecomendacao());
			 preparador.setString(9, ex.getAbreviatura());
			 preparador.setInt(10, ex.getId_amostra());
			 preparador.setInt(11, ex.getId_sub_grupo());
			 preparador.setInt(12, ex.getId_servicodeanalise_clinica());
			 preparador.execute();
			 preparador.close();
			
		} catch (SQLException e) {
			throw new RuntimeException(e);
		}
		catch (Exception e) {
			System.out.println(e.getMessage());
		}
		finally {
			try {
				con.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
	}
    
    public void editarParametroExame(Exames ex)
	{
		String sql = "update tblparametro_lab set parametro_lab =?,fk_servico_exame =?, id_grupo_lab =? where id_parametro_lab = ?";
		try {
			con = Conexao.getConexao(); 
			PreparedStatement preparador = con.prepareStatement(sql);
			 preparador = con.prepareStatement(sql);
			 preparador.setString(1, ex.getParametro_lab());
			 preparador.setInt(2, ex.getFK_servico_exame());
			 preparador.setInt(3, ex.getId_grupo_lab());
			 preparador.setInt(4, ex.getId_parametro_lab());
			 preparador.execute();
			 preparador.close();
			
		} catch (SQLException e) {
			throw new RuntimeException(e);
		}
		catch (Exception e) {
			System.out.println(e.getMessage());
		}
		finally {
			try {
				con.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
	}
    
    public void editargrupoLabExame(Exames ex)
	{
		String sql = "update tblgrupo_lab set grupo_lab =? where id_grupo_lab = ?";
		try {
			con = Conexao.getConexao(); 
			PreparedStatement preparador = con.prepareStatement(sql);
			 preparador = con.prepareStatement(sql);
			 preparador.setString(1, ex.getGrupo_lab());
			 preparador.setInt(2, ex.getId_grupo_lab());
			 preparador.execute();
			 preparador.close();
			
		} catch (SQLException e) {
			throw new RuntimeException(e);
		}
		catch (Exception e) {
			System.out.println(e.getMessage());
		}
		finally {
			try {
				con.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
	}
    
    public void editarFaixaEtariaExame(Exames ex)
	{
		String sql = "update tblfaixa_etaria_lab set facha_etaria_lab =?,idade_inicial =?,idade_fim =? where id_facha_etaria_lab = ?";
		try {
			con = Conexao.getConexao(); 
			PreparedStatement preparador = con.prepareStatement(sql);
			 preparador = con.prepareStatement(sql);
			 preparador.setString(1, ex.getFaixa_etaria());
			 preparador.setInt(2, ex.getIdade_inicial());
			 preparador.setInt(3, ex.getIdade_final());
			 preparador.setInt(4, ex.getId());
			 preparador.execute();
			 preparador.close();
			
		} catch (SQLException e) {
			throw new RuntimeException(e);
		}
		catch (Exception e) {
			System.out.println(e.getMessage());
		}
		finally {
			try {
				con.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
	}
    
    public void editarUnidadeExame(Exames ex)
	{
		String sql = "update tblunidade_para_exames_lab set unidade_para_exames_lab =? where id_unidade_para_exames_lab = ?";
		try {
			con = Conexao.getConexao(); 
			PreparedStatement preparador = con.prepareStatement(sql);
			 preparador = con.prepareStatement(sql);
			 preparador.setString(1, ex.getUnidade());
			 preparador.setInt(2, ex.getId_unidade());
			 preparador.execute();
			 preparador.close();
			
		} catch (SQLException e) {
			throw new RuntimeException(e);
		}
		catch (Exception e) {
			System.out.println(e.getMessage());
		}
		finally {
			try {
				con.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
	}
    
    public void editarSubGrupo(Exames ex)
	{
		String sql = "update tblsubgrupo set nome_subgrupo = ?, FK_grupo_eamme =? where id_subgrupo = ? ";
		try {
			 con = Conexao.getConexao(); 
			 PreparedStatement preparador = con.prepareStatement(sql);
			 preparador = con.prepareStatement(sql);
			 preparador.setString(1, ex.getNome_sub_grupo());
			 preparador.setInt(2, ex.getFK_grupo_analise_clinica());
			 preparador.setInt(3, ex.getId_sub_grupo());
			 preparador.execute();
			 preparador.close();
			 System.out.println("SALVA UM GR-EXAME");
		} catch (SQLException e) {
			throw new RuntimeException(e);
		}
		catch (Exception e) {
			System.out.println(e.getMessage());
		}
		finally {
			try {
				con.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
	}
    
    public void editarGrupoExames(Exames ex)
	{
		String sql = "update tblgrupodeanalises set grupo_danalise = ? where id_grupo_analises_clinicas = ? ";
		try {
			 con = Conexao.getConexao(); 
			 PreparedStatement preparador = con.prepareStatement(sql);
			 preparador = con.prepareStatement(sql);
			 preparador.setString(1, ex.getGrupo_danalise());
			 preparador.setInt(2, ex.getId_grupo_analises_clinicas());
			 preparador.execute();
			 preparador.close();
			 System.out.println("SALVA UM GR-EXAME");
		} catch (SQLException e) {
			throw new RuntimeException(e);
		}
		catch (Exception e) {
			System.out.println(e.getMessage());
		}
		finally {
			try {
				con.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
	}
    
    public void editarUnidade(Exames ex)
	{
		String sql = "update tblunidade set unidade = ? where id_unidade = ? ";
		try {
			 con = Conexao.getConexao(); 
			 PreparedStatement preparador = con.prepareStatement(sql);
			 preparador = con.prepareStatement(sql);
			 preparador.setString(1, ex.getUnidade());
			 preparador.setInt(2, ex.getId_unidade());
			 preparador.execute();
			 preparador.close();
			 System.out.println("SALVA UMA UNIDADE");
		} catch (SQLException e) {
			throw new RuntimeException(e);
		}
		catch (Exception e) {
			System.out.println(e.getMessage());
		}
		finally {
			try {
				con.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
	}
    
    public void salvarGrupoExames(Exames ex)
	{
		String sql = "insert into tblgrupodeanalises (grupo_danalise) value (?)";
		try {
			 con = Conexao.getConexao(); 
			 PreparedStatement preparador = con.prepareStatement(sql);
			 preparador = con.prepareStatement(sql);
			 preparador.setString(1, ex.getGrupo_danalise());
			 preparador.execute();
			 preparador.close();
			 System.out.println("SALVA UM GR-EXAME");
		} catch (SQLException e) {
			throw new RuntimeException(e);
		}
		catch (Exception e) {
			System.out.println(e.getMessage());
		}
		finally {
			try {
				con.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
	}
    
    public void salvarUnidade(Exames ex)
	{
		String sql = "insert into tblunidade (unidade) value (?)";
		try {
			 con = Conexao.getConexao(); 
			 PreparedStatement preparador = con.prepareStatement(sql);
			 preparador = con.prepareStatement(sql);
			 preparador.setString(1, ex.getUnidade());
			 preparador.execute();
			 preparador.close();
			 System.out.println("SALVA UMA Unidade");
		} catch (SQLException e) {
			throw new RuntimeException(e);
		}
		catch (Exception e) {
			System.out.println(e.getMessage());
		}
		finally {
			try {
				con.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
	}
    
//    Salvar na tabela subgrupo.
    public void salvarSubGrupos(Exames ex)
	{
		String sql = "insert into tblsubgrupo (nome_subgrupo,FK_grupo_exame) value (?,?)";
		try {
			 con = Conexao.getConexao(); 
			 PreparedStatement preparador = con.prepareStatement(sql);
			 preparador = con.prepareStatement(sql);
			 preparador.setString(1, ex.getNome_sub_grupo());
			 preparador.setInt(2, ex.getFK_grupo_analise_clinica());
			 preparador.execute();
			 preparador.close();
			 System.out.println("SALVA UM GR-EXAME");
		} catch (SQLException e) {
			throw new RuntimeException(e);
		}
		catch (Exception e) {
			System.out.println(e.getMessage());
		}
		finally {
			try {
				con.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
	}
  //Salvar na tabela tblagendarexame
    public void agendarServicosExames(Exames ex)
	{
		String sql = "insert into tblagendarexame (FK_numero_do_servico_de_exame,FK_servico_exame,data,hora,FK_funcionario,FK_paciente,gestante_pra_exame,FK_tipo_dcobertura,FK_dia_dasemana,fk_convenio,fk_plano)values(?,?,?,?,?,?,?,?,?,?,?)";
		try {
			 con = Conexao.getConexao(); 
			 PreparedStatement preparador = con.prepareStatement(sql);
			 preparador = con.prepareStatement(sql);
			 preparador.setInt(1, ex.getFK_numero_do_servico_de_exame());
			 preparador.setInt(2, ex.getFK_servico_exame());
			 preparador.setDate(3, new Date(ex.getData_agendamento().getTimeInMillis()));
			 preparador.setString(4, ex.getHora_agendamento());
			 preparador.setInt(5, ex.getFK_funcionario());
			 preparador.setInt(6, ex.getFK_paciente());
			 preparador.setInt(7, ex.getGestante());
			 preparador.setInt(8, ex.getFK_tipo_dcobertura());
			 preparador.setInt(9, ex.getFk_dia_semana());
			 preparador.setInt(10, ex.getFK_convenio());
			 preparador.setInt(11, ex.getFK_plano());
			 preparador.execute();
			 preparador.close();
			
		} catch (SQLException e) {
			throw new RuntimeException(e);
		}
		catch (Exception e) {
			// TODO: handle exception
		}
		finally {
			try {
				con.close();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
	}
	}
    
 // Numero de servico exame
	 
 		public int numeroexame ()
 		{ 
 			
 			int fun = 0;
 			String sql = "SELECT count(id_numero_do_exame) numero FROM tblnumerodoexame";
 			try {
 				con = Conexao.getConexao();
 				PreparedStatement preparador = con.prepareStatement(sql);
 				ResultSet rs = preparador.executeQuery();
 				
 				if(rs.next()){
 					return rs.getInt("numero") + 1;
 				}
 			}
 			catch (Exception e) {
 				 System.out.println(e.getMessage());
 			}
 			finally {
 				try {
 					con.close();
 				} catch (SQLException e) {
 					// TODO Auto-generated catch block
 					e.printStackTrace();
 				}}
 				return 0;  
 			
 		}
 		// tblexamerequisitadocancelado
 		public void addExamesRequisitadoCancelado(Exames ex)
 		{
 			String sql = "INSERT INTO tblexamerequisitadocancelado (FK_numero_do_servico_de_exame,data_exam_requ_cancelado,hora_exam_requ_cancelado,FK_funcionario,FK_paciente,motivo_do_cancelamento) VALUES (?,?,?,?,?,?)";
 			try {
 				con = Conexao.getConexao();
 				PreparedStatement cp = con.prepareStatement(sql);
 				cp.setInt(1, ex.getFK_numero_do_servico_de_exame());
 				cp.setDate(2, new Formatando().data_registo());
 				cp.setString(3, new Formatando().getHoraAtual());
 				cp.setInt(4, ex.getFK_funcionario());
 				cp.setInt(5, ex.getFK_paciente());
 				cp.setString(6, ex.getRecomendacao());
 				cp.execute();
 				cp.close(); 
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
// 		--------------------------- Examinar o Pacientes
 		 public List<Exames> buscarPacienteExameAtendido(int FK_provenienca){
 	        List <Exames> lista = new ArrayList<Exames>();
 	    		String sql = "SELECT * FROM vwexamepacienteporexaminar where FK_provenienca_exame = ?";
 	    		try {
 	    			 con = Conexao.getConexao();
 	    			PreparedStatement preparador = con.prepareStatement(sql);
 	    			preparador.setInt(1, FK_provenienca);
 	    			 ResultSet rs = preparador.executeQuery();
 	    			 while(rs.next())
 	    			 {
 	    				 Exames f = new Exames();
 	    				 
 	    				 f.setNumero_processo(rs.getString("numeroProcesso"));
 	    				 f.setNomeCompleto(rs.getString("nomeCompleto"));
 	    				 f.setAno(rs.getInt("ano"));
 	    				 f.setGenero(rs.getString("genero"));
 	    				 Calendar data = Calendar.getInstance();
 		    		     data.setTime(rs.getDate("data_doatendimento"));
 		    		     f.setData_da_facturacao(data);
 	    				 f.setHora_da_facturacao(rs.getTime("hora_da_facturacao"));
 	    				 f.setFK_factura(rs.getInt("id_factura"));
 	    				 
 	    				 f.setFK_numero_do_servico_de_exame(rs.getInt("fk_requisicao_dexame"));
 	    				 f.setFK_funcionario(rs.getInt("fk_funcionario"));
 	    				 f.setFK_paciente(rs.getInt("fk_paciente"));
 	    				 lista.add(f);
 	    			 }
 	    			 preparador.close();
 	    		} catch (SQLException e) {
 	    			// TODO Auto-generated catch block
 	    			e.printStackTrace();
 	    		}
 	    		catch (Exception e) {
 	    			// TODO: handle exception
 	    		}
 	    		finally {
 	    			try {
 	    				con.close();
 	    			} catch (SQLException e) {
 	    				// TODO Auto-generated catch block
 	    				e.printStackTrace();
 	    			}
 	    		}
 	    		return lista;
 	    	}
 		 
 		public List<Exames> buscarPacienteExameAtendidoBUE(){
 	        List <Exames> lista = new ArrayList<Exames>();
 	    		String sql = "select * from  vwurgenciaexameclinico group by id_urgencia";
 	    		try {
 	    			 con = Conexao.getConexao();
 	    			PreparedStatement preparador = con.prepareStatement(sql);
 	    			 
 	    			 ResultSet rs = preparador.executeQuery();
 	    			 while(rs.next())
 	    			 {
 	    				 Exames f = new Exames();
 	    				 
 	    				 f.setNumero_processo(rs.getString("numeroprocesso"));
 	    				 f.setNomeCompleto(rs.getString("nomecompleto"));
 	    				 f.setAno(rs.getInt("idade"));
 	    				 f.setGenero(rs.getString("genero"));
 	    				 Calendar data = Calendar.getInstance();
 		    		     data.setTime(rs.getDate("data_registo"));
 		    		     f.setData_da_facturacao(data);
 	    				 f.setHora_da_facturacao(rs.getTime("hora_registo"));
 	    				 f.setFK_numero_do_servico_de_exame(rs.getInt("fk_requisicao_dexame"));
 	    				 f.setFK_factura(0);
 	    				 f.setFK_funcionario(1);
 	    				 f.setFK_paciente(rs.getInt("fk_paciente"));
 	    				 lista.add(f);
 	    			 }
 	    			 preparador.close();
 	    		} catch (SQLException e) {
 	    			// TODO Auto-generated catch block
 	    			e.printStackTrace();
 	    		}
 	    		catch (Exception e) {
 	    			// TODO: handle exception
 	    		}
 	    		finally {
 	    			try {
 	    				con.close();
 	    			} catch (SQLException e) {
 	    				// TODO Auto-generated catch block
 	    				e.printStackTrace();
 	    			}
 	    		}
 	    		return lista;
 	    	}
 		
 		
 		public List<Exames> buscarPacienteExameAtendidoInternado(){
 	        List <Exames> lista = new ArrayList<Exames>();
 	    		String sql = "SELECT * FROM vwhistoricointernamentoexames group by fk_numero_requisicao";
 	    		try {
 	    			 con = Conexao.getConexao();
 	    			PreparedStatement preparador = con.prepareStatement(sql);
 	    			 
 	    			 ResultSet rs = preparador.executeQuery();
 	    			 while(rs.next())
 	    			 {
 	    				 Exames f = new Exames(); 	    				 
 	    				 f.setNumero_processo(rs.getString("numeroprocesso"));
 	    				 f.setNomeCompleto(rs.getString("nomecompleto"));
 	    				 f.setFK_numero_do_servico_de_exame(rs.getInt("fk_numero_requisicao"));
 	    				 f.setAno(rs.getInt("idade"));
 	    				 f.setGenero(rs.getString("genero"));
 	    				 Calendar data = Calendar.getInstance();
 		    		     data.setTime(rs.getDate("data_registo"));
 		    		     f.setData_da_facturacao(data);
 	    				 f.setHora_da_facturacao(rs.getTime("hora_registo"));
 	    				 f.setFK_factura(0);
 	    				 f.setFK_funcionario(rs.getInt("FK_doutor"));
 	    				 f.setFK_paciente(rs.getInt("fk_paciente"));
 	    				 lista.add(f);
 	    			 }
 	    			 preparador.close();
 	    		} catch (SQLException e) {
 	    			e.printStackTrace();
 	    		}
 	    		catch (Exception e) {
 	    			e.printStackTrace();
 	    		}
 	    		finally {
 	    			try {
 	    				con.close();
 	    			} catch (SQLException e) {
 	    				// TODO Auto-generated catch block
 	    				e.printStackTrace();
 	    			}
 	    		}
 	    		return lista;
 	    	}
 		
 		public List<Exames> buscarPacienteExameAtendendoInternado(String cod){
 	        List <Exames> lista = new ArrayList<Exames>();
 	    		String sql = "SELECT * FROM vwhistoricointernamentoexames where fk_numero_requisicao = ?";
 	    		try {
 	    			 con = Conexao.getConexao();
 	    			 PreparedStatement preparador = con.prepareStatement(sql);
 	    			 preparador.setString(1, cod);
 	    			 ResultSet rs = preparador.executeQuery();
 	    			 while(rs.next())
 	    			 {
 	    				Exames f = new Exames();
	    				 f.setFK_numero_do_servico_de_exame(rs.getInt("fk_numero_requisicao"));
	    				 f.setFK_exame(rs.getInt("FK_servico"));
	    				 f.setAnalise_clinica(rs.getString("analise_clinica"));  					
						 f.setGrupo_danalise(rs.getString("grupo_danalise"));	
//						 System.out.println("SR: "+rs.getString("analise_clinica")+" COD "+cod);
	    				 lista.add(f);
 	    			 }
 	    			 preparador.close();
 	    		} catch (SQLException e) {
 	    			// TODO Auto-generated catch block
 	    			e.printStackTrace();
 	    		}
 	    		catch (Exception e) {
 	    			e.printStackTrace();
 	    		}
 	    		finally {
 	    			try {
 	    				con.close();
 	    			} catch (SQLException e) {
 	    				// TODO Auto-generated catch block
 	    				e.printStackTrace();
 	    			}
 	    		}
 	    		return lista;
 	    	}
 		
 		public List<Exames> buscarPacienteExameAtendidoInternado(String cod,String code){
 	        List <Exames> lista = new ArrayList<Exames>();
 	    		String sql = "SELECT * FROM vwhistoricointernamentoexames where fk_numero_requisicao = ? and FK_servico = ?";
 	    		try {
 	    			 con = Conexao.getConexao();
 	    			PreparedStatement preparador = con.prepareStatement(sql);
 	    			preparador.setString(1, cod);
 	    			preparador.setString(2, code);
 	    			 
 	    			 ResultSet rs = preparador.executeQuery();
 	    			 while(rs.next())
 	    			 {
 	    				 Exames f = new Exames();
 	    				 
 	    				f.setFK_numero_do_servico_de_exame(rs.getInt("fk_numero_requisicao")); 
 	    				  
 	    				 f.setFK_exame(rs.getInt("FK_servico"));
 	    				 f.setAnalise_clinica(rs.getString("analise_clinica"));  					
						 f.setGrupo_danalise(rs.getString("grupo_danalise"));
						 					 
 	    				 lista.add(f);
 	    			 }
 	    			 preparador.close();
 	    		} catch (SQLException e) {
 	    			// TODO Auto-generated catch block
 	    			e.printStackTrace();
 	    		}
 	    		catch (Exception e) {
 	    			// TODO: handle exception
 	    		}
 	    		finally {
 	    			try {
 	    				con.close();
 	    			} catch (SQLException e) {
 	    				// TODO Auto-generated catch block
 	    				e.printStackTrace();
 	    			}
 	    		}
 	    		return lista;
 	    	}
 		
 		public List<Exames> buscarPacienteExameNaoAtendidoOntem(){
 	        List <Exames> lista = new ArrayList<Exames>();
 	    		String sql = "SELECT * FROM vwexamepacientenaoexaminadoontem WHERE id_factura not in(select FK_factura from tbllistadeesperadoexame) and id_factura not in(select fk_factura from tblamostraprontaparaserexaminada)";
 	    		try {
 	    			 con = Conexao.getConexao();
 	    			 PreparedStatement preparador = con.prepareStatement(sql);
 	    			 ResultSet rs = preparador.executeQuery();
 	    			 while(rs.next())
 	    			 {
 	    				 Exames f = new Exames();
 	    				 f.setFK_factura(rs.getInt("id_factura"));
 	    				 f.setFK_funcionario(rs.getInt("fk_funcionario"));
 	    				 f.setFK_paciente(rs.getInt("fk_paciente"));
 	    				 lista.add(f);
 	    			 }
 	    			 preparador.close();
 	    		} catch (SQLException e) {
 	    			// TODO Auto-generated catch block
 	    			e.printStackTrace();
 	    		}
 	    		catch (Exception e) {
 	    			// TODO: handle exception
 	    		}
 	    		finally {
 	    			try {
 	    				con.close();
 	    			} catch (SQLException e) {
 	    				// TODO Auto-generated catch block
 	    				e.printStackTrace();
 	    			}
 	    		}
 	    		return lista;
 	    	}
 		 public Paciente buscarPerfilDoPaciente(String cod)
 		{
 			Paciente f = new Paciente(); 
 			String sql = "SELECT * FROM vwperfilcompleto WHERE NumeroProcesso = ?";
 			try {
 				 con = Conexao.getConexao(); 
 				 PreparedStatement preparador = con.prepareStatement(sql);
 				 preparador.setString(1, cod);
 				 ResultSet rs = preparador.executeQuery();
 				 if(rs.next())
 				 {
 					f.setNomeCompleto(rs.getString("nomeCompleto"));
	    			f.setNomeEC(rs.getString("estado_civil"));
	    			f.setNomegenero(rs.getString("genero"));
	    			Calendar data = Calendar.getInstance();
		    		data.setTime(rs.getDate("data_nascimento"));
		    		f.setDataNasc(data);
		    		f.setEndereco(rs.getString("endereco"));
		    		f.setNumero_processo(rs.getString("NumeroProcesso"));
		    		f.setFK_paciente(rs.getInt("FK_paciente"));
 				 }
 				 preparador.close();
 			} catch (SQLException e) {
 				 
 				e.printStackTrace();
 			}
 			catch (Exception e) {
 				 System.out.println(e.getMessage());
 			}
 			finally {
 				try {
 					con.close();
 				} catch (SQLException e) {
 					e.printStackTrace();
 				}}
 			return f;
 		}
 		public List<Exames> buscarExamePorFazer(String cod){
 		    List <Exames> lista = new ArrayList<Exames>();
 				String sql = "SELECT * FROM vwexameporfazer WHERE FK_numero_do_servico_de_exame =? group by id_servicodeanalise_clinica";
 				try {
 					 con = Conexao.getConexao();
 					PreparedStatement preparador = con.prepareStatement(sql);
 					preparador.setString(1, cod);
 					 ResultSet rs = preparador.executeQuery();
 					 while(rs.next())
 					 {
 						 Exames pac = new Exames();
 						 pac.setAnalise_clinica(rs.getString("analise_clinica"));  					
 						 pac.setGrupo_danalise(rs.getString("grupo_danalise"));
 						 pac.setFK_exame(rs.getInt("id_servicodeanalise_clinica"));
 						 pac.setNumero_do_exame(rs.getString("numero_do_exame"));
 						 pac.setFK_factura(rs.getInt("id_factura"));
 						 pac.setRecomendacao(rs.getString("recomendacao"));
 						 pac.setFK_numero_do_servico_de_exame(rs.getInt("fK_numero_do_servico_de_exame"));
 						 lista.add(pac);
 						  
 					 }
 					 preparador.close();
 				} catch (SQLException e) {
 					e.printStackTrace();
 				}
 				catch (Exception e) {
 					e.printStackTrace();
 				}
 				finally {
 					try {
 						con.close();
 					} catch (SQLException e) {
 						e.printStackTrace();
 					}
 				}
 				return lista;
 			}
 		
 		public List<Exames> buscarExamePorFazerBUE(String cod){
 		    List <Exames> lista = new ArrayList<Exames>();
 				String sql = "select * from  vwurgenciaexameclinico where fk_requisicao_dexame = ?";
 				try {
 					 con = Conexao.getConexao();
 					PreparedStatement preparador = con.prepareStatement(sql);
 					preparador.setString(1, cod);
 					 ResultSet rs = preparador.executeQuery();
 					 while(rs.next())
 					 {
 						 Exames pac = new Exames();
 						 pac.setAnalise_clinica(rs.getString("analise_clinica"));  					
 						 pac.setGrupo_danalise(rs.getString("grupo_danalise"));
 						 pac.setFK_exame(rs.getInt("fk_servico"));
 						 pac.setFK_paciente(rs.getInt("fk_paciente"));
 						 
 						 
 						 pac.setFK_numero_do_servico_de_exame(rs.getInt("fk_requisicao_dexame"));
 						 lista.add(pac);
 					 }
 					 preparador.close();
 				} catch (SQLException e) {
 					e.printStackTrace();
 				}
 				catch (Exception e) {
 					e.printStackTrace();
 				}
 				finally {
 					try {
 						con.close();
 					} catch (SQLException e) {
 						e.printStackTrace();
 					}
 				}
 				return lista;
 			}
 		public List<Exames> buscarExamePorFazerBUE(String cod,String codex){
 		    List <Exames> lista = new ArrayList<Exames>();
 				String sql = "select * from  vwurgenciaexameclinico where fk_requisicao_dexame = ? and fk_servico = ?";
 				try {
 					 con = Conexao.getConexao();
 					PreparedStatement preparador = con.prepareStatement(sql);
 					preparador.setString(1, cod);
 					preparador.setString(2, codex);
 					 ResultSet rs = preparador.executeQuery();
 					 while(rs.next())
 					 {
 						 Exames pac = new Exames();
 						 pac.setAnalise_clinica(rs.getString("analise_clinica"));  					
 						 pac.setGrupo_danalise(rs.getString("grupo_danalise"));
 						 pac.setFK_exame(rs.getInt("fk_servico"));
 						 pac.setFK_paciente(rs.getInt("fk_paciente"));
 						 
 						 
 						 pac.setFK_numero_do_servico_de_exame(rs.getInt("fk_requisicao_dexame"));
 						 lista.add(pac);
 					 }
 					 preparador.close();
 				} catch (SQLException e) {
 					e.printStackTrace();
 				}
 				catch (Exception e) {
 					e.printStackTrace();
 				}
 				finally {
 					try {
 						con.close();
 					} catch (SQLException e) {
 						e.printStackTrace();
 					}
 				}
 				return lista;
 			}
 		public List<Exames> buscarExamePorFazer(String cod, String codEx){
 		    List <Exames> lista = new ArrayList<Exames>();
 				String sql = "SELECT * FROM vwexameporfazer WHERE FK_numero_do_servico_de_exame =? AND id_servicodeanalise_clinica =? group by id_servicodeanalise_clinica";
 				try {
 					 con = Conexao.getConexao();
 					PreparedStatement preparador = con.prepareStatement(sql);
 					preparador.setString(1, cod);
 					preparador.setString(2, codEx);
 					 ResultSet rs = preparador.executeQuery();
 					 while(rs.next())
 					 {
 						 Exames pac = new Exames();
 						 pac.setAnalise_clinica(rs.getString("analise_clinica"));  					
 						 pac.setGrupo_danalise(rs.getString("grupo_danalise"));
 						 pac.setFK_exame(rs.getInt("id_servicodeanalise_clinica"));
 						 pac.setNumero_do_exame(rs.getString("numero_do_exame"));
 						 pac.setFK_factura(rs.getInt("id_factura"));
 						 pac.setFK_numero_do_servico_de_exame(rs.getInt("FK_numero_do_servico_de_exame"));
 						 lista.add(pac);
 					 }
 					 preparador.close();
 				} catch (SQLException e) {
 					// TODO Auto-generated catch block
 					e.printStackTrace();
 				}
 				catch (Exception e) {
 					// TODO: handle exception
 				}
 				finally {
 					try {
 						con.close();
 					} catch (SQLException e) {
 						// TODO Auto-generated catch block
 						e.printStackTrace();
 					}
 				}
 				return lista;
 			}
 		public void addAmostra(Exames ex)
 		{
 			String sql = "INSERT INTO tblamostra (FK_tipo_deamostra,observacao_amostra,fk_factura,fk_exame,numero_do_exame,data_darecolha,hora_darecolha,FK_funcionario,FK_estatu_darecolha,FK_provenienca_exame,FK_requisicao_exame) values(?,?,?,?,?,?,?,?,?,?,?)";
 			try {
 				con = Conexao.getConexao();
 				PreparedStatement cp = con.prepareStatement(sql);
 				cp.setInt(1, ex.getFK_tipo_deamostra());
 				cp.setString(2, ex.getObservacao_amostra());
 				cp.setInt(3, ex.getFK_factura());
 				cp.setInt(4, ex.getFK_exame());
 				cp.setString(5, ex.getNumero_do_exame());
 				cp.setDate(6, new Formatando().data_registo());
 				cp.setString(7, new Formatando().getHoraAtual());
 				cp.setInt(8, ex.getFK_funcionario());
 				cp.setInt(9, ex.getFK_estatu_darecolha());
 				cp.setInt(10, ex.getFK_provenienca_exame());
 				cp.setInt(11, ex.getFK_numero_do_servico_de_exame());
 				cp.execute();
 				cp.close(); 
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
 		public void alt_amostra(Exames ex)
		{
			String sql = "UPDATE tblamostra set FK_tipo_deamostra = ?, observacao_amostra = ? where id_amostra = ?";
			try {
				con = Conexao.getConexao();
				PreparedStatement cp = con.prepareStatement(sql);
				
				cp.setInt(1, ex.getFK_tipo_deamostra());
				cp.setString(2, ex.getObservacao_amostra());
				cp.setInt	(3, ex.getId_amostra());
				cp.execute();
				cp.close();
				
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
 		
 		public void atualizandoAmostra(Exames e){
 			String sql = "Update tblamostra set numero_do_exame = ?,observacao_amostra = ?,data_darecolha = ?,hora_darecolha= ?,"
 					+ "  FK_tipo_deamostra = ?, fk_factura = ?, FK_estatu_darecolha = ?, FK_provenienca_exame = ? where fk_exame = ? and FK_requisicao_exame = ?";
 			try {
				con = Conexao.getConexao();
				PreparedStatement ps = con.prepareStatement(sql);
				ps.setString(1, e.getNumero_do_exame());
				ps.setString(2, e.getObservacao_amostra());
				ps.setDate(3, new Formatando().data_registo());
				ps.setString(4, new Formatando().getHoraAtual());
				ps.setInt(5, e.getFK_tipo_deamostra());
				ps.setInt(6, e.getFK_factura());
				ps.setInt(7, e.getFK_estatu_darecolha());
				ps.setInt(8, e.getFK_provenienca_exame());
				ps.setInt(9, e.getFK_exame());
				ps.setInt(10, e.getFK_numero_do_servico_de_exame());
				ps.execute();
			} catch (Exception e2) {
				e2.printStackTrace();
			}
 		}
 		public List<Exames> buscarTipoDeAmostra(){
 		    List <Exames> lista = new ArrayList<Exames>();
 				String sql = "SELECT * FROM tbltipodeamostra";
 				try {
 					 con = Conexao.getConexao();
 					PreparedStatement preparador = con.prepareStatement(sql);
 					 ResultSet rs = preparador.executeQuery();
 					 while(rs.next())
 					 {
 						 Exames pac = new Exames();
 						 pac.setId_tipo_demostra(rs.getInt("id_tipo_demostra"));  					
 						 pac.setTipo_deamostra(rs.getString("tipo_deamostra"));
 						 lista.add(pac);
 					 }
 					 preparador.close();
 				} catch (SQLException e) {
 					// TODO Auto-generated catch block
 					e.printStackTrace();
 				}
 				catch (Exception e) {
 					// TODO: handle exception
 				}
 				finally {
 					try {
 						con.close();
 					} catch (SQLException e) {
 						// TODO Auto-generated catch block
 						e.printStackTrace();
 					}
 				}
 				return lista;
 			}
 		public Exames editarAmostra(String cod,String code){
 			Exames pac = new Exames();
 			String sql = "Select * from tblamostra where FK_requisicao_exame = ? and fk_exame = ?";
 			try {
 					con = Conexao.getConexao();
 					PreparedStatement preparador = con.prepareStatement(sql);
 					preparador.setString(1, cod);
 					preparador.setString(2, code);
 					ResultSet rs = preparador.executeQuery();
 					if(rs.next())
 					 {
 						 pac.setId_amostra(rs.getInt("id_amostra")); 
 						 pac.setFK_tipo_deamostra(rs.getInt("FK_tipo_deamostra"));
 						 pac.setObservacao_amostra(rs.getString("observacao_amostra"));
 						 
 					 }
 					 preparador.close();
 				} catch (SQLException e) {
 					e.printStackTrace();
 				}
 				catch (Exception e) {
 					e.printStackTrace();
 				}
 				finally {
 					try {
 						con.close();
 					} catch (SQLException e) {
 						e.printStackTrace();
 					}
 				}
 				return pac;
 			}
 		
 		public boolean confirmarExameTesteDeQualidade(Exames ex){
 			
 			String sql = "INSERT INTO tbltestedequalidadeconfirmado (FK_numero_do_servico,FK_servico_teste,data,hora,FK_funcionario,FK_empresa) VALUES (?,?,?,?,?,?)";
 			try {
 				con = Conexao.getConexao();
 				PreparedStatement cp = con.prepareStatement(sql);
 				cp.setInt(1, ex.getFK_numero_do_servico_de_exame());
 				cp.setInt(2, ex.getFK_servico_exame());
 				cp.setDate(3, new Formatando().data_registo());
 				cp.setString(4, new Formatando().getHoraAtual());
 				cp.setInt(5, ex.getFK_funcionario());
 				cp.setInt(6, ex.getFK_paciente());
 				cp.setInt(7, ex.getGestante());
 				cp.setInt(8, ex.getFK_tipo_dcobertura());
 				cp.execute();
 				cp.close(); 
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
 			return false;
 		}
 		
 		public List<Exames> buscarConfirmarExamesTQ(){
 	        List <Exames> lista = new ArrayList<Exames>();

 	       //String sql = "select id_agen_test_quali,numero_doservico,FK_fornecedor,numero_fornecedor,empresa,data,hora from tblfornecedor emp, tblagendartestedequalidade atq where emp.FK_fornecedor = atq.Fk_empresa and data = curdate() GROUP BY numero_doservico order by hora";

 	       String sql = "select FK_requisicao, numero_doservico,FK_fornecedor,numero_fornecedor,empresa,data from tblfornecedor emp, tblagendartestedequalidade atq where emp.FK_fornecedor=atq.Fk_empresa and data = curdate() and atq.FK_requisicao not in(select FK_requisicao from tbltestedequalidadeconfirmado) GROUP BY atq.FK_requisicao order by hora";

 	    		try {
 	    			 con = Conexao.getConexao();
 	    			PreparedStatement preparador = con.prepareStatement(sql);
 	    			ResultSet rs = preparador.executeQuery();
 	    			 while(rs.next())
 	    			 {
 	    				 Exames f = new Exames();
 	    				 f.setFK_requisicao(rs.getInt("FK_requisicao"));
 	    			     f.setNumeroDoServico(rs.getString("numero_doservico"));
 	    				 f.setNomeDaEmpresa(rs.getString("empresa"));
 	    				 f.setNumeroDaEmpresa(rs.getString("numero_fornecedor"));
 	    				 Calendar data = Calendar.getInstance();
		    		     data.setTime(rs.getDate("data"));
		    		     f.setData_agendamento(data);
	    				 lista.add(f);
 	    			 }
 	    			 preparador.close();
 	    		} catch (SQLException e) {
 	    			// TODO Auto-generated catch block
 	    			e.printStackTrace();
 	    		}
 	    		catch (Exception e) {
 	    			// TODO: handle exception
 	    		}
 	    		finally {
 	    			try {
 	    				con.close();
 	    			} catch (SQLException e) {
 	    				// TODO Auto-generated catch block
 	    				e.printStackTrace();
 	    			}
 	    		}
 	    		return lista;
 	    	}
 		
 		public Exames buscarUmExamesTQ(String FK_requisicao){
 	        List <Exames> lista = new ArrayList<Exames>();
 	      String sql = " select FK_requisicao,numero_doservico,FK_fornecedor,numero_fornecedor,empresa,data from tblfornecedor emp, tblagendartestedequalidade atq where emp.FK_fornecedor=atq.Fk_empresa and atq.FK_requisicao=? GROUP BY atq.FK_requisicao ";
 	     Exames f = new Exames();
 	      try {
 	    			 con = Conexao.getConexao();
 	    			PreparedStatement preparador = con.prepareStatement(sql);
 	    			preparador.setString(1, FK_requisicao); 
 	    			ResultSet rs = preparador.executeQuery();
 	    			 while(rs.next())
 	    			 {
 	    				 f.setFK_requisicao(rs.getInt("FK_requisicao"));
 	    				 f.setNumeroDoServico(rs.getString("numero_doservico"));
 	    				 f.setNomeDaEmpresa(rs.getString("empresa"));
 	    				 f.setNumeroDaEmpresa(rs.getString("numero_fornecedor"));
 	    				 Calendar data = Calendar.getInstance();
		    		     data.setTime(rs.getDate("data"));
		    		     f.setData_agendamento(data);
	    				 lista.add(f);
 	    			 }
 	    			 preparador.close();
 	    		} catch (SQLException e) {
 	    			// TODO Auto-generated catch block
 	    			e.printStackTrace();
 	    		}
 	    		catch (Exception e) {
 	    			// TODO: handle exception
 	    		}
 	    		finally {
 	    			try {
 	    				con.close();
 	    			} catch (SQLException e) {
 	    				// TODO Auto-generated catch block
 	    				e.printStackTrace();
 	    			}
 	    		}
 	    		return f;
 	    	}
 		
 		public List<Exames> buscarConfirmarExames(){
 	        List <Exames> lista = new ArrayList<Exames>();
 	    		String sql = "SELECT * FROM vwpacienteagendadoparaexame WHERE data = curdate() and FK_numero_do_servico_de_exame NOT IN(SELECT FK_numero_do_servico_de_exame FROM tblexamerequisitado)";
 	    		try {
 	    			 con = Conexao.getConexao();
 	    			PreparedStatement preparador = con.prepareStatement(sql);
 	    			 ResultSet rs = preparador.executeQuery();
 	    			 while(rs.next())
 	    			 {
 	    				 Exames f = new Exames();
 	    				 f.setNumero_processo(rs.getString("numeroProcesso"));
 	    				 f.setNomeCompleto(rs.getString("nomeCompleto"));
 	    				 f.setNumero_do_exame(rs.getString("numero_do_exame"));
 	    				 Calendar data = Calendar.getInstance();
		    		     data.setTime(rs.getDate("data"));
		    		     f.setData_agendamento(data);
	    				 f.setHora_agendamento(rs.getString("hora"));
	    				 f.setFK_convenio(rs.getInt("FK_paciente"));
 	    				 lista.add(f);
 	    			 }
 	    			 preparador.close();
 	    		} catch (SQLException e) {
 	    			// TODO Auto-generated catch block
 	    			e.printStackTrace();
 	    		}
 	    		catch (Exception e) {
 	    			// TODO: handle exception
 	    		}
 	    		finally {
 	    			try {
 	    				con.close();
 	    			} catch (SQLException e) {
 	    				// TODO Auto-generated catch block
 	    				e.printStackTrace();
 	    			}
 	    		}
 	    		return lista;
 	    	}
 		
 		public List<Exames> buscarAgendasExames(){
 	        List <Exames> lista = new ArrayList<Exames>();
 	    		String sql = "SELECT * FROM vwpacienteagendadoparaexame "
 	    				   + "where FK_numero_do_servico_de_exame NOT IN(SELECT FK_numero_do_servico_de_exame FROM tblexamerequisitado)"
 	    				   + " order by data desc,hora desc limit 50;";
 	    		try {
 	    			 con = Conexao.getConexao();
 	    			PreparedStatement preparador = con.prepareStatement(sql);
 	    			 ResultSet rs = preparador.executeQuery();
 	    			 while(rs.next())
 	    			 {
 	    				 Exames f = new Exames();
 	    				 f.setNumero_processo(rs.getString("numeroProcesso"));
 	    				 f.setNomeCompleto(rs.getString("nomeCompleto"));
 	    				 f.setQuantidade(rs.getInt("qdt_servicos"));
 	    				 f.setFK_numero_do_servico_de_exame(rs.getInt("FK_numero_do_servico_de_exame"));
 	    				 Calendar data = Calendar.getInstance();
		    		     data.setTime(rs.getDate("data"));
		    		     f.setData_agendamento(data);
	    				 f.setHora_agendamento(rs.getString("hora"));
 	    				 lista.add(f);
 	    			 }
 	    			 preparador.close();
 	    		} catch (SQLException e) {
 	    			// TODO Auto-generated catch block
 	    			e.printStackTrace();
 	    		}
 	    		catch (Exception e) {
 	    			// TODO: handle exception
 	    		}
 	    		finally {
 	    			try {
 	    				con.close();
 	    			} catch (SQLException e) {
 	    				// TODO Auto-generated catch block
 	    				e.printStackTrace();
 	    			}
 	    		}
 	    		return lista;
 	    	}
 		
 		public List<Exames> buscarAgendasExames(String aux){
 	        List <Exames> lista = new ArrayList<Exames>();
 	    		String sql = "SELECT * FROM vwpacienteagendadoparaexame where (numeroProcesso = ? or nomeCompleto Like ?)"
 	    				   + "and FK_numero_do_servico_de_exame NOT IN(SELECT FK_numero_do_servico_de_exame FROM tblexamerequisitado)"
 	    				   + " order by data desc limit 50;";
 	    		try {
 	    			 con = Conexao.getConexao();
 	    			PreparedStatement preparador = con.prepareStatement(sql);
 	    			preparador.setString(1, aux);
 	    			preparador.setString(2, aux+"%");
 	    			 ResultSet rs = preparador.executeQuery();
 	    			 while(rs.next())
 	    			 {
 	    				 Exames f = new Exames();
 	    				 f.setNumero_processo(rs.getString("numeroProcesso"));
 	    				 f.setNomeCompleto(rs.getString("nomeCompleto"));
 	    				 f.setQuantidade(rs.getInt("qdt_servicos"));
 	    				 f.setFK_numero_do_servico_de_exame(rs.getInt("FK_numero_do_servico_de_exame"));
 	    				 Calendar data = Calendar.getInstance();
		    		     data.setTime(rs.getDate("data"));
		    		     f.setData_agendamento(data);
	    				 f.setHora_agendamento(rs.getString("hora"));
 	    				 lista.add(f);
 	    			 }
 	    			 preparador.close();
 	    		} catch (SQLException e) {
 	    			// TODO Auto-generated catch block
 	    			e.printStackTrace();
 	    		}
 	    		catch (Exception e) {
 	    			// TODO: handle exception
 	    		}
 	    		finally {
 	    			try {
 	    				con.close();
 	    			} catch (SQLException e) {
 	    				// TODO Auto-generated catch block
 	    				e.printStackTrace();
 	    			}
 	    		}
 	    		return lista;
 	    	}
 		public List<Exames> buscarConfirmarExames(String cod){
 	        List <Exames> lista = new ArrayList<Exames>();
 	    		String sql = "SELECT * FROM vwpacienteagendadoparaexame WHERE numero_do_exame =?";
 	    		try {
 	    			 con = Conexao.getConexao();
 	    			PreparedStatement preparador = con.prepareStatement(sql);
 	    			preparador.setString(1, cod);
 	    			 ResultSet rs = preparador.executeQuery();
 	    			 while(rs.next())
 	    			 {
 	    				 Exames f = new Exames();
 	    				 f.setNumero_processo(rs.getString("numeroProcesso"));
 	    				 f.setNomeCompleto(rs.getString("nomeCompleto"));
 	    				 f.setNumero_do_exame(rs.getString("numero_do_exame"));
 	    				 Calendar data = Calendar.getInstance();
		    		     data.setTime(rs.getDate("data"));
		    		     f.setData_agendamento(data);
//	    				 f.setHora_agendamento(rs.getString("hora"));
		    		     f.setFK_paciente(rs.getInt("fk_paciente"));
		    		     f.setId_servicodeanalise_clinica(rs.getInt("FK_servico_exame"));
		    		     f.setFK_numero_do_servico_de_exame(rs.getInt("FK_numero_do_servico_de_exame"));
		    		     f.setFK_tipo_dcobertura(rs.getInt("fk_tipo_dcobertura"));
		    		     f.setFK_convenio(rs.getInt("fk_convenio"));
		    		     f.setFK_plano(rs.getInt("fk_plano"));
 	    				 lista.add(f);
 	    			 }
 	    			 preparador.close();
 	    		} catch (SQLException e) {
 	    			// TODO Auto-generated catch block
 	    			e.printStackTrace();
 	    		}
 	    		catch (Exception e) {
 	    			// TODO: handle exception
 	    		}
 	    		finally {
 	    			try {
 	    				con.close();
 	    			} catch (SQLException e) {
 	    				// TODO Auto-generated catch block
 	    				e.printStackTrace();
 	    			}
 	    		}
 	    		return lista;
 	    	}
 		public void addExamesRequisitado(Exames ex)
 		{
 			String sql = "INSERT INTO tblexamerequisitado (FK_numero_do_servico_de_exame,FK_servico_exame,data,hora,FK_funcionario,FK_paciente,gestante_pra_exame,FK_tipo_dcobertura,fk_convenio,fk_plano) VALUES (?,?,?,?,?,?,?,?,?,?)";
 			try {
 				con = Conexao.getConexao();
 				PreparedStatement cp = con.prepareStatement(sql);
 				cp.setInt(1, ex.getFK_numero_do_servico_de_exame());
 				cp.setInt(2, ex.getFK_servico_exame());
 				cp.setDate(3, new Formatando().data_registo());
 				cp.setString(4, new Formatando().getHoraAtual());
 				cp.setInt(5, ex.getFK_funcionario());
 				cp.setInt(6, ex.getFK_paciente());
 				cp.setInt(7, ex.getGestante());
 				cp.setInt(8, ex.getFK_tipo_dcobertura());
 				cp.setInt(9, ex.getFK_convenio());
 				cp.setInt(10, ex.getFK_plano());
 				cp.execute();
 				cp.close(); 
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
 		public Exames buscarAmostra(String codf)
 		{
 	    	Exames exame = null; 
 			String sql = "SELECT * FROM tblamostra WHERE fk_factura = ?";
 			try {
 				 con = Conexao.getConexao(); 
 				 PreparedStatement preparador = con.prepareStatement(sql);
 				 preparador.setString(1, codf);
 				 ResultSet rs = preparador.executeQuery();
 				 if(rs.next())
 				 {
 					 exame = new Exames();
 					 exame.setFK_estatu_darecolha(rs.getInt("FK_estatu_darecolha"));
 				 }
 				 preparador.close();
 			} catch (SQLException e) {
 				 
 				e.printStackTrace();
 			}
 			catch (Exception e) {
 				 System.out.println(e.getMessage());
 			}
 			finally {
 				try {
 					con.close();
 				} catch (SQLException e) {
 					e.printStackTrace();
 				}}
 			return exame;
 		}
 		public boolean getXequeEstatus(int cod){
 			
 			Exames exame = null; 
 			String sql = "Select * from tblamostra where fk_factura = ? ";
 		    try {
 		    	 con = Conexao.getConexao();
 				 PreparedStatement ps = con.prepareStatement(sql);
 				 ps.setInt(1, cod);
 				 ResultSet rs =  ps.executeQuery();
 				 if(rs.next())
 					exame = new Exames();
 				 	exame.setFK_estatu_darecolha(rs.getInt("FK_estatu_darecolha"));
 					return false;
 				
// 				 ps.close();
 				  
 			} catch (SQLException e) {
 				e.printStackTrace();
 			}
 		    catch(Exception e){}
 		    finally{
 						try{
 							con.close();
 						}
 						catch(SQLException ef){
 							System.out.println("Erro finalizar: "+ef);
 						}
 				}
 		    return true;
 		}
 		public void addAtendendoPaciente(Exames ex){
 			
 			String sql = "Insert into tblamostraprontaparaserexaminada (fk_factura,FK_paciente,FK_funcionario,numero_doatendimento,hora_doatendimento,data_doatendimento,observacao_recomendacao,FK_provenienca_exame,FK_requisicao_exame) value (?,?,?,?,?,?,?,?,?)";
 		    try {
 		    	 con = Conexao.getConexao();
 		    	PreparedStatement cp = con.prepareStatement(sql);
 				cp.setInt(1, ex.getFK_factura());
 				cp.setInt(2, ex.getFK_paciente());
 				cp.setInt(3, ex.getFK_funcionario());
 				cp.setString(4, ex.getNumero_doatendimento());
 				cp.setString(5, new Formatando().getHoraAtual());
 				cp.setDate(6, new Formatando().data_registo());
 				cp.setString(7, ex.getObservacao_recomendacao());
 				cp.setInt(8, ex.getFK_provenienca_exame());
 				cp.setInt(9, ex.getFK_numero_do_servico_de_exame());
 				cp.execute();
 				ResultSet rs = cp.executeQuery("SELECT LAST_INSERT_ID()");
 				if(rs.next())
 					ex.setFK_estatu_darecolha(rs.getInt(1));
 				
 				sql = "Update tblamostra set FK_estatu_darecolha = ? where FK_requisicao_exame =?";
 				cp = con.prepareStatement(sql);
 				cp.setInt(1, ex.getFK_estatu_darecolha());
 				cp.setInt(2, ex.getFK_numero_do_servico_de_exame());
 				cp.execute();
 				cp.close();
 				
 				 System.out.println("Passando por este caminho "); 
 			} catch (SQLException e) {
 				e.printStackTrace();
 			}
 		    finally{
 		    	try {
 					con.close();
 				} catch (SQLException e) {
 					// TODO Auto-generated catch block
 					e.printStackTrace();
 				}
 		    }
 		
 		}
 		public Exames novo()
 		{
 			Exames f = new Exames();
 			int fun = 0;
 			String sql = "SELECT MAX(id_pronto_aserexaminado)num FROM tblamostraprontaparaserexaminada";
 			try {
 				con = Conexao.getConexao();
 				PreparedStatement preparador = con.prepareStatement(sql);
 				ResultSet rs = preparador.executeQuery();
 				if(rs.last())
 					fun = rs.getInt("num");
 				    fun++;
 				
 				f.setNumero_doatendimento("ANG0"+fun);
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
 				return f;
 		}
 		public int novoInt()
 		{
 		 
 			int fun = 0;
 			String sql = "SELECT (id_amostra)num FROM tblamostra";
 			try {
 				con = Conexao.getConexao();
 				PreparedStatement preparador = con.prepareStatement(sql);
 				ResultSet rs = preparador.executeQuery();
 				if(rs.last())
 					fun = rs.getInt("num");
 				 				 
 			}
 			catch (Exception e) {
 				e.printStackTrace();
 			}
 		    
 				return fun+1;
 		}
 		public List<Exames> buscarAtendidos(){
 		    List <Exames> lista = new ArrayList<Exames>();
 				String sql = "SELECT * FROM vwlistadeesperadoexame where data_listade_espera = curdate()";
 				try {
 					 con = Conexao.getConexao();
 					PreparedStatement preparador = con.prepareStatement(sql);
 					 ResultSet rs = preparador.executeQuery();
 					 while(rs.next())
 					 {
 						 Exames pac = new Exames();
 						 pac.setFK_funcionario(rs.getInt("FK_funcionario"));
 						 pac.setFK_factura(rs.getInt("FK_factura"));
 						 pac.setNumero_factura(rs.getString("numero_factura"));
 						 pac.setNumero_processo(rs.getString("NumeroProcesso"));
 						 pac.setNomeCompleto(rs.getString("NomeCompleto"));
 						 pac.setAno(rs.getInt("idade"));
 						 pac.setGenero(rs.getString("genero"));
 						 pac.setQuantidade(rs.getInt("quantidade"));
 						 pac.setTotal(rs.getInt("total"));
 						 pac.setNome_funcionario(rs.getString("n_c_funcionario"));
 						 pac.setId_lista_deespera(rs.getInt("id_lista_deespera"));
 						 Calendar data = Calendar.getInstance();
		    		     data.setTime(rs.getDate("data_listade_espera"));
		    		     pac.setData_listade_espera(data);
 						 lista.add(pac);
 					 }
 					 preparador.close();
 				} catch (SQLException e) {
 					// TODO Auto-generated catch block
 					e.printStackTrace();
 				}
 				catch (Exception e) {
 					// TODO: handle exception
 				}
 				finally {
 					try {
 						con.close();
 					} catch (SQLException e) {
 						// TODO Auto-generated catch block
 						e.printStackTrace();
 					}
 				}
 				return lista;
 			}
 		public List<Exames> buscarAtendidos(Date inicio, Date Fim){
 		    List <Exames> lista = new ArrayList<Exames>();
 				String sql = "SELECT * FROM vwlistadeesperadoexame where Data_listade_espera between ? and ?";
 				try {
 					 con = Conexao.getConexao();
 					PreparedStatement preparador = con.prepareStatement(sql);
 					preparador.setDate(1, inicio);
 					preparador.setDate(2, Fim);
 					 ResultSet rs = preparador.executeQuery();
 					 while(rs.next())
 					 {
 						 Exames pac = new Exames();
 						 pac.setFK_funcionario(rs.getInt("FK_funcionario"));
 						 pac.setFK_factura(rs.getInt("FK_factura"));
 						 pac.setNumero_factura(rs.getString("numero_factura"));
 						 pac.setNumero_processo(rs.getString("NumeroProcesso"));
 						 pac.setNomeCompleto(rs.getString("NomeCompleto"));
 						 pac.setAno(rs.getInt("idade"));
 						 pac.setGenero(rs.getString("genero"));
 						 pac.setQuantidade(rs.getInt("quantidade"));
 						 pac.setTotal(rs.getInt("total"));
 						 pac.setNome_funcionario(rs.getString("n_c_funcionario"));
 						 pac.setId_lista_deespera(rs.getInt("id_lista_deespera"));
 						 Calendar data = Calendar.getInstance();
		    		     data.setTime(rs.getDate("data_listade_espera"));
		    		     pac.setData_listade_espera(data);
 						 lista.add(pac);
 					 }
 					 preparador.close();
 				} catch (SQLException e) {
 					// TODO Auto-generated catch block
 					e.printStackTrace();
 				}
 				catch (Exception e) {
 					// TODO: handle exception
 				}
 				finally {
 					try {
 						con.close();
 					} catch (SQLException e) {
 						// TODO Auto-generated catch block
 						e.printStackTrace();
 					}
 				}
 				return lista;
 			}
 		public List<Exames> buscarExamesModal(String cod){
 		    List <Exames> lista = new ArrayList<Exames>();
 				String sql = "SELECT * FROM vwexamedepacientecancelado WHERE FK_factura =?";
 				try {
 					 con = Conexao.getConexao();
 					PreparedStatement preparador = con.prepareStatement(sql);
 					preparador.setString(1, cod);
 					 ResultSet rs = preparador.executeQuery();
 					 while(rs.next())
 					 {
 						 Exames pac = new Exames();
 						 pac.setAnalise_clinica(rs.getString("analise_clinica"));
 						 pac.setNumero_do_exame(rs.getString("numero_do_exame"));
 						 pac.setPreco(rs.getDouble("preco"));
 						 lista.add(pac);
 					 }
 					 preparador.close();
 				} catch (SQLException e) {
 					// TODO Auto-generated catch block
 					e.printStackTrace();
 				}
 				catch (Exception e) {
 					// TODO: handle exception
 				}
 				finally {
 					try {
 						con.close();
 					} catch (SQLException e) {
 						// TODO Auto-generated catch block
 						e.printStackTrace();
 					}
 				}
 				return lista;
 			}
 		public List<Exames> buscarOsAtendidosExaminadosModal(String cod){
 		    List <Exames> lista = new ArrayList<Exames>();
 				String sql = "SELECT analise_clinica, tipo_deamostra, fk_factura,FK_requisicao_exame FROM vwpacientesatendidoslaboratorio WHERE FK_requisicao_exame =? group by analise_clinica";
 				try {
 					 con = Conexao.getConexao();
 					PreparedStatement preparador = con.prepareStatement(sql);
 					preparador.setString(1, cod);
 					 ResultSet rs = preparador.executeQuery();
 					 while(rs.next())
 					 {
 						 Exames pac = new Exames();
 						 pac.setAnalise_clinica(rs.getString("analise_clinica"));
 						 pac.setTipo_deamostra(rs.getString("tipo_deamostra"));
 						 pac.setFK_factura(rs.getInt("fK_factura"));
 						 pac.setFK_numero_do_servico_de_exame(rs.getInt("FK_requisicao_exame"));
 						 lista.add(pac);
 					 }
 					 preparador.close();
 				} catch (SQLException e) {
 					// TODO Auto-generated catch block
 					e.printStackTrace();
 				}
 				catch (Exception e) {
 					// TODO: handle exception
 				}
 				finally {
 					try {
 						con.close();
 					} catch (SQLException e) {
 						// TODO Auto-generated catch block
 						e.printStackTrace();
 					}
 				}
 				return lista;
 			}
 		public List<Exames> buscarServidosAgendados(int cod){
 		    List <Exames> lista = new ArrayList<Exames>();
 				String sql = "SELECT * FROM tblagendarexame WHERE FK_numero_do_servico_de_exame = ?";
 				try {
 					 con = Conexao.getConexao();
 					 PreparedStatement preparador = con.prepareStatement(sql);
 					 preparador.setInt(1, cod);
 					 ResultSet rs = preparador.executeQuery();
 					 while(rs.next())
 					 {
 						 Exames exm = new Exames();
 						 exm.setFK_numero_do_servico_de_exame(rs.getInt("FK_numero_do_servico_de_exame"));
 						 exm.setFK_servico_exame(rs.getInt("FK_servico_exame"));
 						 exm.setFK_paciente(rs.getInt("FK_paciente"));
 						 exm.setFK_funcionario(rs.getInt("FK_funcionario"));
 						 exm.setGestante(rs.getInt("gestante_pra_exame"));
 						 exm.setFK_tipo_dcobertura(rs.getInt("FK_tipo_dcobertura"));
 						 exm.setFk_dia_semana(rs.getInt("FK_dia_dasemana"));
 						 exm.setFK_convenio(rs.getInt("fk_convenio"));
 						 exm.setFK_plano(rs.getInt("fk_plano"));
 						 lista.add(exm);
 					 }
 					 preparador.close();
 				} catch (SQLException e) {
 					e.printStackTrace();
 				}
 				catch (Exception e) {
 					e.printStackTrace();
 				}
 				finally {
 					try {
 						con.close();
 					} catch (SQLException e) {
 						e.printStackTrace();
 					}
 				}
 				return lista;
 			}
 		public void addEsperaDosExames(Exames ex){
 			
 			String sql = "Insert into tbllistadeesperadoexame (Data_listade_espera,hora_listade_espera,FK_funcionario,FK_factura,FK_paciente) value (?,?,?,?,?)";
 		    try {
 		    	 con = Conexao.getConexao();
 		    	PreparedStatement cp = con.prepareStatement(sql);
 		    	cp.setDate(1, new Formatando().data_registo());
 		    	cp.setString(2, new Formatando().getHoraAtual());
 		    	cp.setInt(3, ex.getFK_funcionario());
 				cp.setInt(4, ex.getFK_factura());
 				cp.setInt(5, ex.getFK_paciente());
 				cp.execute();
 				cp.close(); 
 			} catch (SQLException e) {
 				e.printStackTrace();
 			}
 		    finally{
 		    	try {
 					con.close();
 				} catch (SQLException e) {
 					// TODO Auto-generated catch block
 					e.printStackTrace();
 				}
 		    }
 		
 		}
 		
// 		Cadastrar unidade de Laborário
public void cadastrarUnidadeExames(Exames ex){
 			
 			String sql = "Insert into tblunidade_para_exames_lab (unidade_para_exames_lab) value (?)";
 		    try {
 		    	 con = Conexao.getConexao();
 		    	PreparedStatement cp = con.prepareStatement(sql);
 		    	cp.setString(1, ex.getUnidade());
 				cp.execute();
 				cp.close(); 
 			} catch (SQLException e) {
 				e.printStackTrace();
 			}
 		    finally{
 		    	try {
 					con.close();
 				} catch (SQLException e) {
 					// TODO Auto-generated catch block
 					e.printStackTrace();
 				}
 		    }
 		
 		}
 		
 		
//public void del_esperaDosExames (int id)
//{
//	String sql = "DELETE FROM tbllistadeesperadoexame  WHERE id_lista_deespera=?";
//	try {
//		con = Conexao.getConexao();
//		 PreparedStatement preparador = con.prepareStatement(sql);
//		 preparador.setInt(1, id);
//		 preparador.execute();
//		 preparador.close();
//	} catch (SQLException e) {
//		e.printStackTrace();
//	}
//	catch (Exception e) {
//		// TODO Auto-generated catch block
//		e.printStackTrace();
//	}
//	finally{
//		try{
//			con.close();
//		}
//		catch(SQLException ef){
//			System.out.println("Erro finalizar: "+ef);
//		}
//	}
//}
public void del_esperaDosExames(int cod, int codf) {

	String sql = "Update tblfactura set data_doatendimento  = ? where id_factura =?";
	try {
		con = Conexao.getConexao();
		PreparedStatement ps = con.prepareStatement(sql);
		ps.setDate(1, new Formatando().data_registo());
		ps.setInt(2, codf);
		System.out.println("Passando aki actualizando..");
		ps.execute();
		// Atualizando 
		sql = "DELETE FROM tbllistadeesperadoexame  WHERE id_lista_deespera=?";
		ps = con.prepareStatement(sql);
		ps.setInt(1, cod);
		System.out.println("Deletando dado..");
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
}
public String foto(String id)
{
	String sql = "SELECT NOME_FICHEIRO FROM vwlistaarquivopaciente WHERE (fk_entidade = ? or numero_processo = ?) AND fk_arquivo=?";
	String ficheiro="usuarios.jpg";
	try {
		con = Conexao.getConexao();
		PreparedStatement ent = con.prepareStatement(sql);
		ent.setString(1,id);
		ent.setString(2, id);
		ent.setInt(3, 1);
		ResultSet rs = ent.executeQuery();
		if(rs.next())
			ficheiro = rs.getString("nome_ficheiro");
		ent.close();
	} catch (SQLException e) {
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
	return ficheiro;
}

	public boolean getXequeAmostra(int codfac,int codexa) {
	boolean rss = false; 
	String sql = "Select * from tblamostra where FK_requisicao_exame = ? and fk_exame = ?  ";
	try {
		con = Conexao.getConexao();
		PreparedStatement ps = con.prepareStatement(sql);
		ps.setInt(1, codfac);
		ps.setInt(2, codexa);
		ResultSet rs = ps.executeQuery();
		if (rs.next()){
			if(rs.getString("numero_do_exame")!=null && rs.getString("numero_do_exame").trim().length() > 1)
				rss = true;
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
	return rss;
	}
	
	public boolean getXequeTotal(String codfac) {
		int total = 0;
		int soma = -1;
		String sql  = "Select count(FK_requisicao_exame) as somatorio from tblamostra where FK_requisicao_exame = ?";
		try {
			con = Conexao.getConexao();
			PreparedStatement ps = con.prepareStatement(sql);
			ps.setString(1, codfac);
			ResultSet rs = ps.executeQuery();
			if (rs.next()){
				soma = rs.getInt("somatorio");
			}
			ps.close();
			sql  = "SELECT count(fk_requisicao_dexame) as total FROM tblfacturaexame WHERE fk_requisicao_dexame = ?";
			PreparedStatement pt = con.prepareStatement(sql);
			pt.setString(1, codfac);
			rs = pt.executeQuery();
			if (rs.next()){
				total = rs.getInt("total");
			}
			if(total == soma)
				return true;
			
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
	
	public boolean getXequeTotalBUE(String codfac) {
		int total = 0;
		int soma = -1;
		String sql  = "Select count(FK_requisicao_exame) as somatorio from tblamostra where FK_requisicao_exame = ?";
		try {
			con = Conexao.getConexao();
			PreparedStatement ps = con.prepareStatement(sql);
			ps.setString(1, codfac);
			ResultSet rs = ps.executeQuery();
			if (rs.next()){
				soma = rs.getInt("somatorio");
			}
			ps.close();
			sql  = "SELECT count(FK_requisicao_dexame) as total FROM tblurgenciaexameclinico WHERE FK_requisicao_dexame = ?";
			PreparedStatement pt = con.prepareStatement(sql);
			pt.setString(1, codfac);
			rs = pt.executeQuery();
			if (rs.next()){
				total = rs.getInt("total");
			}
			if(total == soma)
				return true;
			
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
	
	public boolean getXequeTotalInt(String codfac) {
		int total = 0;
		int soma = -1;
		String sql  = "Select count(FK_requisicao_exame) as somatorio from tblamostra where FK_requisicao_exame = ?";
		try {
			con = Conexao.getConexao();
			PreparedStatement ps = con.prepareStatement(sql);
			ps.setString(1, codfac);
			ResultSet rs = ps.executeQuery();
			if (rs.next()){
				soma = rs.getInt("somatorio");
			}
			ps.close();
			sql  = "SELECT count(FK_numero_requisicao) as total FROM tblinternamentoexameclinico WHERE FK_numero_requisicao = ?";
			PreparedStatement pt = con.prepareStatement(sql);
			pt.setString(1, codfac);
			rs = pt.executeQuery();
			if (rs.next()){
				total = rs.getInt("total");
			}
			if(total == soma)
				return true;
			
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



}
