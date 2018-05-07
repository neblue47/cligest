package ao.co.cligest.negocio;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import ao.co.cligest.entidades.Exames;
import ao.co.cligest.entidades.Isencao;
import ao.co.cligest.entidades.LaboratorioOutrosExames;

public class LaboratorioOutrosExamesDAO {
	private Connection con;
	
	//SALVAR OUTROS EXAMES
    public int inserirOutrosExames(LaboratorioOutrosExames ex)
	{
    	int ultimoId = 0;
    	String sql = "insert into tblexamesclinicoscadastrados  (fk_grupo,exames_clinicos_cadastrados,nota_explicativa,codigo_barra,genero,disponibilidade,categoria,recomendacoes,abreviacao,fk_amostra,FK_tipo_exame_noapp,fk_tipo_serv_exames,FK_sub_grupo) values(?,?,?,?,?,?,?,?,?,?,?,?,?)";
		try {
			con = Conexao.getConexao(); 
			PreparedStatement preparador = con.prepareStatement(sql);
			 preparador = con.prepareStatement(sql);			
			 preparador.setInt(1, ex.getFk_grupo());
			 preparador.setString(2, ex.getExames_clinicos_cadastrados());
			 preparador.setString(3, ex.getNota_explicativa());
			 preparador.setInt(4, ex.getCodigo_barra());
			 preparador.setInt(5, ex.getGenero());
			 preparador.setInt(6, ex.getDisponibilidade());
			 preparador.setInt(7, ex.getCategoria());
			 preparador.setString(8, ex.getRecomendacoes());
			 preparador.setString(9, ex.getAbreviacao());
			 preparador.setInt(10, ex.getFk_amostra());
			 preparador.setInt(11, ex.getFk_tipo_de_exame_noapp());
			 preparador.setInt(12, ex.getFK_tipo_servicoEx());
			 preparador.setInt(13, ex.getId_sub_grupo());
			 preparador.execute();
//			 ResultSet rs = preparador.executeQuery("SELECT LAST_INSERT_ID()");
//			 if(rs.next())
//			      ultimoId = rs.getInt(1);
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
			}
		}
		return ultimoId;
	}
    
    public List<LaboratorioOutrosExames> buscarServicosExamesOutrosExame()
	{
		List <LaboratorioOutrosExames> listaOExames = new ArrayList<LaboratorioOutrosExames>();
		String sql = "SELECT * FROM vwoutrosexamescadastrados where id_tipos_servico_exames = 3";
		try {
			 con = Conexao.getConexao(); 
			 PreparedStatement preparador = con.prepareStatement(sql);
			 ResultSet rs = preparador.executeQuery();
			 while(rs.next())
			 {
				 LaboratorioOutrosExames loe = new LaboratorioOutrosExames();
				 loe.setIdExamesClinicosCadastrados(rs.getInt("id_exames_clinicos_cadastrados"));
				 loe.setExames_clinicos_cadastrados(rs.getString("exames_clinicos_cadastrados"));
				 loe.setTxtGroupo_exame(rs.getString("grupo_danalise"));
				 loe.setTipo_deamostra(rs.getString("tipo_deamostra"));
				 loe.setCodigo_barra(rs.getInt("codigo_barra"));
				 loe.setAbreviacao(rs.getString("abreviacao"));
				 loe.setCategoria(rs.getInt("categoria"));
				 loe.setId_sub_grupo(rs.getInt("FK_sub_grupo"));
				 loe.setDisponibilidade(rs.getInt("disponibilidade"));
				 listaOExames.add(loe);
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
		return listaOExames;
	}
    
    public List<LaboratorioOutrosExames> buscarServicosExamesOutrosExameEditar(int IDOExamesEdiConv){
		List <LaboratorioOutrosExames> listaOExamesEdi = new ArrayList<LaboratorioOutrosExames>();
		String sql = "SELECT * FROM vwoutrosexamescadastrados WHERE id_exames_clinicos_cadastrados = ?";
		try{
			 con = Conexao.getConexao();
			 PreparedStatement preparador = con.prepareStatement(sql);
			 preparador.setInt(1, IDOExamesEdiConv);
			 ResultSet rs = preparador.executeQuery();
			 while(rs.next())
			 {
				 LaboratorioOutrosExames loe = new LaboratorioOutrosExames();
				 loe.setIdExamesClinicosCadastrados(rs.getInt("id_exames_clinicos_cadastrados"));
				 loe.setExames_clinicos_cadastrados(rs.getString("exames_clinicos_cadastrados"));
				 loe.setAbreviacao(rs.getString("abreviacao"));
				 loe.setCodigo_barra(rs.getInt("codigo_barra"));
				 loe.setRecomendacoes(rs.getString("recomendacoes"));
				 loe.setNota_explicativa(rs.getString("nota_explicativa"));
				 loe.setDisponibilidade(rs.getInt("disponibilidade"));
				 loe.setCategoria(rs.getInt("categoria"));
				 loe.setGenero(rs.getInt("genero"));
				 loe.setTxtGroupo_exame(rs.getString("grupo_danalise"));
				 loe.setFk_grupo(rs.getInt("fk_grupo"));
				 loe.setFk_amostra(rs.getInt("fk_amostra"));
				 loe.setTipo_deamostra(rs.getString("tipo_deamostra"));
				 loe.setId_sub_grupo(rs.getInt("FK_sub_grupo"));
				 listaOExamesEdi.add(loe);
			 }
			 preparador.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}catch (Exception e) {
			 
		}finally{
					try{
						con.close();
					}
					catch(SQLException ef){
						System.out.println("Erro finalizar: "+ef);
					}
		}
		return listaOExamesEdi;
	}
    public void modificarOutrosExames(LaboratorioOutrosExames ediExaVeri)
	{
		String sql = "UPDATE tblexamesclinicoscadastrados set fk_grupo= ?,exames_clinicos_cadastrados= ?,nota_explicativa= ?,codigo_barra= ?,genero= ?,disponibilidade= ?,categoria= ?,recomendacoes= ?,abreviacao= ?,fk_amostra= ?,FK_tipo_exame_noapp= ?, FK_sub_grupo =? where id_exames_clinicos_cadastrados = ?";
		try {
			con = Conexao.getConexao();
			PreparedStatement cp = con.prepareStatement(sql);
			
			cp.setInt(1, ediExaVeri.getFk_grupo());
			cp.setString(2, ediExaVeri.getExames_clinicos_cadastrados());
			cp.setString(3, ediExaVeri.getNota_explicativa());
			cp.setInt(4, ediExaVeri.getCodigo_barra());
			cp.setInt(5, ediExaVeri.getGenero());
			cp.setInt(6, ediExaVeri.getDisponibilidade());
			cp.setInt(7, ediExaVeri.getCategoria());
			cp.setString(8, ediExaVeri.getRecomendacoes());
			cp.setString(9, ediExaVeri.getAbreviacao());
			cp.setInt(10, ediExaVeri.getFk_amostra());
			cp.setInt(11, ediExaVeri.getFk_tipo_de_exame_noapp());
			cp.setInt(12, ediExaVeri.getId_sub_grupo());
			cp.setInt(13, ediExaVeri.getIdExamesClinicosCadastrados());
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
    
    public void ExcluirUmServicoOutroExame(int ediExaVeri){
		 
		String sql = "DELETE FROM tblexamesclinicoscadastrados where id_exames_clinicos_cadastrados = ?";
		try {
			 con = Conexao.getConexao();
			PreparedStatement preparador = con.prepareStatement(sql);
			preparador.setInt(1, ediExaVeri);
			preparador.execute();
			preparador.close();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}catch (Exception e) {
			 
		}finally {
			try {
				con.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		} 
	}

}
