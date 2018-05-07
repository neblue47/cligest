package ao.co.cligest.negocio;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

import ao.co.cligest.entidades.Embalagem;
import ao.co.cligest.entidades.Instituicao;
import ao.co.cligest.entidades.Paciente;
import ao.co.cligest.entidades.Produtos;

public class InstituicaoDAO {

	private Connection con ;
	
//	1-INSERIR INSTITUI��O
			public int addInstituicao(Instituicao ins)
			{
				int ultimoId = 0;
				String sql = "INSERT INTO tblinstituicao (FK_pais,FK_provincia,FK_municipio,FK_distrito,nome_registo,nome_comercial,rua,bairro,site,email,numero_registo,nif,data_fundacao,slogan,num_funcionario,logo) VALUES (?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)";
				try {
					con = Conexao.getConexao();
					PreparedStatement cp = con.prepareStatement(sql);
					cp.setInt(1, ins.getFK_pais());
					cp.setInt(2, ins.getFK_provincia());
					cp.setInt(3, ins.getFK_municipio());
					cp.setInt(4, ins.getFK_distrito());
					cp.setString(5, ins.getNome_registo());
					cp.setString(6, ins.getNome_comercial());
					cp.setString(7, ins.getRua());
					cp.setString(8, ins.getBairro());
					cp.setString(9, ins.getSite());
					cp.setString(10, ins.getEmail());
					cp.setInt(11, ins.getNumero_registo());
					cp.setString(12, ins.getNif());
					cp.setDate(13,new Date(ins.getData_fundacao().getTimeInMillis()));
					cp.setString(14, ins.getSlogan());
					cp.setInt	(15, ins.getNum_funcionario());
					cp.setString(16, ins.getLogo());
					cp.execute();
					ResultSet rs = cp.executeQuery("SELECT LAST_INSERT_ID()");
				    if(rs.next())
				      ultimoId = rs.getInt(1);
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
				return ultimoId;
			}
			
			public void addLogo(Instituicao pd)
			{
				String sql = "UPDATE tblinstituicao set logo = ? where id_instituicao = ?";
				try {
					con = Conexao.getConexao();
					PreparedStatement cp = con.prepareStatement(sql);
					cp.setString(1, pd.getLogo());
					cp.setInt	(2, pd.getId_instituicao());
					cp.execute();
					cp.close();
					System.out.println("Logo Cadastrado com sucesso -- ND");
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
			
			public Instituicao getInstituicao()
			{
				Instituicao pd = null;
				String sql = "SELECT * FROM tblinstituicao";
				try {
					con = Conexao.getConexao();
					PreparedStatement preparador = con.prepareStatement(sql);
					 ResultSet rs = preparador.executeQuery();
					 if(rs.next())
					 {
						 pd = new Instituicao();
						 pd.setId_instituicao(rs.getInt("id_instituicao"));
						 pd.setNome_registo(rs.getString("nome_registo"));
						 pd.setNome_comercial(rs.getString("nome_comercial"));
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
				return pd;
			}
			
			public Instituicao getInstalacaoRel(){
				String sql = "Select * from tblinstituicao ins,tbltelefoneinstituicao insf where `ins`.`id_instituicao` =   `insf`.`FK_instituicao` ";
				Instituicao isn = new Instituicao();
				try {
					con = Conexao.getConexao();
					PreparedStatement ps = con.prepareStatement(sql);
					ResultSet rs = ps.executeQuery();
					if(rs.next()){
						isn.setNome_comercial(rs.getString("nome_comercial"));
						isn.setRua(rs.getString("rua"));
						isn.setBairro(rs.getString("bairro"));
						isn.setTelefone(rs.getLong("tel1"));
						isn.setTelefones(rs.getLong("tel2"));
						isn.setNumContribuente(rs.getString("nif"));
						isn.setEmail(rs.getString("email"));
						isn.setSite(rs.getString("site"));
						isn.setLogo(rs.getString("logo"));
					}
				} catch (Exception e) {
					e.printStackTrace();
				}
				return null;
			}
			
			public void telefone(Instituicao fun)
			{
				String sql = "INSERT INTO tbltelefoneinstituicao (FK_instituicao,tel1,tel2) VALUES (?,?,?)";
				try {
					con = Conexao.getConexao();
					PreparedStatement ent = con.prepareStatement(sql);
					ent.setInt(1, fun.getId_instituicao());
					ent.setLong(2, fun.getTelefone());
					ent.setLong(3, fun.getTelefones());
					ent.execute();
					ent.close();
					System.out.println("Cadastro de sucesso...TBLTELEFONE"); 
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
			
//			Mostrar os dados da institui��o
//			  6-Editar MileStone
			public Instituicao buscarInstituicao()
			{
				Instituicao pd = null;
				String sql = "SELECT * FROM tblinstituicao, tbltelefoneinstituicao where id_instituicao = fk_instituicao";
				try {
					con = Conexao.getConexao();
					PreparedStatement preparador = con.prepareStatement(sql);
//					 preparador.setInt(1, ed);
					 ResultSet rs = preparador.executeQuery();
					 if(rs.next())
					 {
						 pd = new Instituicao();
						 pd.setId_instituicao(rs.getInt("id_instituicao"));
						 pd.setNome_registo(rs.getString("nome_registo"));
						 pd.setNome_comercial(rs.getString("nome_comercial"));
						 pd.setFK_pais(rs.getInt("fK_pais"));
						 pd.setFK_provincia(rs.getInt("fK_provincia"));
						 pd.setFK_municipio(rs.getInt("fK_municipio"));
						 pd.setFK_distrito(rs.getInt("fK_distrito"));
						 pd.setBairro(rs.getString("bairro"));
						 pd.setRua(rs.getString("rua"));
						 pd.setTelefone(rs.getLong("tel1"));
						 pd.setTelefones(rs.getLong("tel2"));
						 pd.setSite(rs.getString("site"));
						 pd.setEmail(rs.getString("email"));
						 pd.setNumero_registo(rs.getInt("numero_registo"));
						 pd.setNum_funcionario(rs.getInt("num_funcionario"));
						 pd.setNif(rs.getString("nif"));
						 Calendar data = Calendar.getInstance();
						 data.setTime(rs.getDate("data_fundacao"));
						 pd.setData_fundacao(data);
						 pd.setSlogan(rs.getString("slogan"));
						 pd.setLogo(rs.getString("logo"));
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

			
//			5- Alterar milestone
			public void alt_instituicao(Instituicao ins)
			{
				String sql = "UPDATE tblinstituicao set FK_pais = ?, FK_provincia = ?,FK_municipio = ?,FK_distrito = ?,nome_registo = ?,nome_comercial = ?,rua = ?,bairro = ?,"
						+ "	  site = ?,email = ?,numero_registo = ?,nif = ?,data_fundacao = ?,slogan = ?,num_funcionario = ?, logo = ? where id_instituicao = ?";
				try {
					con = Conexao.getConexao();
					PreparedStatement cp = con.prepareStatement(sql);
					
					cp.setInt(1, ins.getFK_pais());
					cp.setInt(2, ins.getFK_provincia());
					cp.setInt(3, ins.getFK_municipio());
					cp.setInt(4, ins.getFK_distrito());
					cp.setString(5, ins.getNome_registo());
					cp.setString(6, ins.getNome_comercial());
					cp.setString(7, ins.getRua());
					cp.setString(8, ins.getBairro());
					cp.setString(9, ins.getSite());
					cp.setString(10, ins.getEmail());
					cp.setInt(11, ins.getNumero_registo());
					cp.setString(12, ins.getNif());
					cp.setDate(13,new Date(ins.getData_fundacao().getTimeInMillis()));
					cp.setString(14, ins.getSlogan());
					cp.setInt	(15, ins.getNum_funcionario());
					cp.setString(16, ins.getLogo());
					cp.setInt	(17, ins.getId_instituicao());
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

			
			public int buscarId() {
				int id = 0;
				String sql = "SELECT * FROM tblinstituicao ";
				try {
					con = Conexao.getConexao();
					PreparedStatement preparador = con.prepareStatement(sql);
					ResultSet rs = preparador.executeQuery();
					if (rs.next())
						id = rs.getInt("id_instituicao");
					preparador.close();
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
				return id;

			}
			
			public void addNossaHistoria(Instituicao pd)
			{
				String sql = "INSERT INTO tblnossahistoria (FK_instituicao, historia) VALUES (?,?)";
				try {
					con = Conexao.getConexao();
					PreparedStatement cp = con.prepareStatement(sql);
					cp.setInt(1, pd.getId_instituicao());
					cp.setString(2, pd.getHistoria());
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

			public Instituicao buscarNossaHistoria()
			{
				Instituicao pd = null;
				String sql = "SELECT * FROM tblnossahistoria";
				try {
					con = Conexao.getConexao();
					PreparedStatement preparador = con.prepareStatement(sql);
					 ResultSet rs = preparador.executeQuery();
					 while(rs.next())
					 {
						 pd = new Instituicao();
						 pd.setId_instituicao(rs.getInt("fk_instituicao"));
						 pd.setHistoria(rs.getString("historia"));
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

			public void alt_nossaHistoria(Instituicao ins)
			{
				String sql = "UPDATE tblnossahistoria set historia = ? where fk_instituicao = ?";
				try {
					con = Conexao.getConexao();
					PreparedStatement cp = con.prepareStatement(sql);
					cp.setString(1, ins.getHistoria());
					cp.setInt	(2, ins.getId_instituicao());
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

			
			public void addMissao(Instituicao pd)
			{
				String sql = "INSERT INTO tblmissaovisaovalor (FK_instituicao,missao,visao,valor) VALUES (?,?,?,?)";
				try {
					con = Conexao.getConexao();
					PreparedStatement cp = con.prepareStatement(sql);
					cp.setInt(1, pd.getId_instituicao());
					cp.setString(2, pd.getMissao());
					cp.setString(3, pd.getVisao());
					cp.setString(4, pd.getValor());
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
			
			public Instituicao buscarMissao()
			{
				Instituicao pd = null;
				String sql = "SELECT * FROM tblmissaovisaovalor";
				try {
					con = Conexao.getConexao();
					PreparedStatement preparador = con.prepareStatement(sql);
					 ResultSet rs = preparador.executeQuery();
					 while(rs.next())
					 {
						 pd = new Instituicao();
						 pd.setId_instituicao(rs.getInt("fk_instituicao"));
						 pd.setMissao(rs.getString("missao"));
						 pd.setVisao(rs.getString("visao"));
						 pd.setValor(rs.getString("valor"));
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

			public void alt_missao(Instituicao ins)
			{
				String sql = "UPDATE tblmissaovisaovalor set missao = ?, visao = ?,valor = ? where fk_instituicao = ?";
				try {
					con = Conexao.getConexao();
					PreparedStatement cp = con.prepareStatement(sql);
					
					cp.setString(1, ins.getMissao());
					cp.setString(2, ins.getVisao());
					cp.setString(3, ins.getValor());
					cp.setInt	(4, ins.getId_instituicao());
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

			
			public void addSumario(Instituicao pd)
			{
				String sql = "INSERT INTO tblsumarioexecutivo (FK_instituicao,sumario_executivo) VALUES (?,?)";
				try {
					con = Conexao.getConexao();
					PreparedStatement cp = con.prepareStatement(sql);
					cp.setInt(1, pd.getId_instituicao());
					cp.setString(2, pd.getSumario_executivo());
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
			
			public Instituicao buscarSumario()
			{
				Instituicao pd = null;
				String sql = "SELECT * FROM tblsumarioexecutivo";
				try {
					con = Conexao.getConexao();
					PreparedStatement preparador = con.prepareStatement(sql);
					 ResultSet rs = preparador.executeQuery();
					 while(rs.next())
					 {
						 pd = new Instituicao();
						 pd.setId_instituicao(rs.getInt("fk_instituicao"));
						 pd.setSumario_executivo(rs.getString("sumario_executivo"));
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
			
			public void alt_sumario(Instituicao ins)
			{
				String sql = "UPDATE tblsumarioexecutivo set sumario_executivo = ? where fk_instituicao = ?";
				try {
					con = Conexao.getConexao();
					PreparedStatement cp = con.prepareStatement(sql);
					cp.setString(1, ins.getSumario_executivo());
					cp.setInt	(2, ins.getId_instituicao());
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

			
			public void addEstrutura(Instituicao pd)
			{
				String sql = "INSERT INTO tblestruraorganizacional (FK_instituicao,estrutura_org) VALUES (?,?)";
				try {
					con = Conexao.getConexao();
					PreparedStatement cp = con.prepareStatement(sql);
					cp.setInt(1, pd.getId_instituicao());
					cp.setString(2, pd.getEstrutura_org());
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
			
			public Instituicao buscarEstrutura()
			{
				Instituicao pd = null;
				String sql = "SELECT * FROM tblestruraorganizacional";
				try {
					con = Conexao.getConexao();
					PreparedStatement preparador = con.prepareStatement(sql);
					 ResultSet rs = preparador.executeQuery();
					 while(rs.next())
					 {
						 pd = new Instituicao();
						 pd.setId_instituicao(rs.getInt("fk_instituicao"));
						 pd.setEstrutura_org(rs.getString("estrutura_org"));
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
			
			public void alt_estrutura(Instituicao ins)
			{
				String sql = "UPDATE tblestruraorganizacional set estrutura_org = ? where fk_instituicao = ?";
				try {
					con = Conexao.getConexao();
					PreparedStatement cp = con.prepareStatement(sql);
					cp.setString(1, ins.getEstrutura_org());
					cp.setInt	(2, ins.getId_instituicao());
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

			
			public void addQuemSomos(Instituicao pd)
			{
				String sql = "INSERT INTO tblquemsomos (FK_instituicao,quem_somos) VALUES (?,?)";
				try {
					con = Conexao.getConexao();
					PreparedStatement cp = con.prepareStatement(sql);
					cp.setInt(1, pd.getId_instituicao());
					cp.setString(2, pd.getQuem_somos());
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
			
			public Instituicao buscarQuemSomos()
			{
				Instituicao pd = null;
				String sql = "SELECT * FROM tblquemsomos";
				try {
					con = Conexao.getConexao();
					PreparedStatement preparador = con.prepareStatement(sql);
					 ResultSet rs = preparador.executeQuery();
					 while(rs.next())
					 {
						 pd = new Instituicao();
						 pd.setId_instituicao(rs.getInt("fk_instituicao"));
						 pd.setQuem_somos(rs.getString("quem_somos"));
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
			
			public void alt_quemSomos(Instituicao ins)
			{
				String sql = "UPDATE tblquemsomos set quem_somos = ? where fk_instituicao = ?";
				try {
					con = Conexao.getConexao();
					PreparedStatement cp = con.prepareStatement(sql);
					cp.setString(1, ins.getQuem_somos());
					cp.setInt	(2, ins.getId_instituicao());
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

			
			public void addMileStone(Instituicao pd)
			{
				String sql = "INSERT INTO tblmilestone (FK_instituicao,milestone,responsavel,detalhe,data) VALUES (?,?,?,?,?)";
				try {
					con = Conexao.getConexao();
					PreparedStatement cp = con.prepareStatement(sql);
					cp.setInt(1, pd.getId_instituicao());
					cp.setString(2, pd.getMilestone());
					cp.setString(3, pd.getResponsavel());
					cp.setString(4, pd.getDetalhe());
					cp.setDate(5, new Date(pd.getData().getTimeInMillis()));
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
			
			public List <Instituicao> buscarMilestones()
			{
				List <Instituicao> lista = new ArrayList<Instituicao>();
				String sql = "SELECT * FROM tblmilestone";
				try {
					con = Conexao.getConexao();
					 PreparedStatement preparador = con.prepareStatement(sql);
					 ResultSet rs = preparador.executeQuery();
					 while(rs.next())
					 {
						 Instituicao pd = new Instituicao();
						 pd.setId_milestone(rs.getInt("id_milestone"));
						 pd.setMilestone(rs.getString("milestone"));
						 pd.setResponsavel(rs.getString("responsavel"));
						 pd.setDetalhe(rs.getString("detalhe"));
						 Calendar data = Calendar.getInstance();
						 data.setTime(rs.getDate("data"));
						 pd.setData(data);
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
			
//			  6-Editar MileStone
			public Instituicao edit_milestone(int ed)
			{
				Instituicao pd = null;
				String sql = "SELECT * FROM tblmilestone WHERE id_milestone = ?";
				try {
					con = Conexao.getConexao();
					PreparedStatement preparador = con.prepareStatement(sql);
					 preparador.setInt(1, ed);
					 ResultSet rs = preparador.executeQuery();
					 while(rs.next())
					 {
						 pd = new Instituicao();
						 pd.setId_milestone(rs.getInt("id_milestone"));
						 pd.setMilestone(rs.getString("milestone"));
						 pd.setResponsavel(rs.getString("responsavel"));
						 pd.setDetalhe(rs.getString("detalhe"));
						 Calendar data = Calendar.getInstance();
						 data.setTime(rs.getDate("data"));
						 pd.setData(data);
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

			
//			5- Alterar milestone
			public void alt_milestone(Instituicao rs)
			{
				String sql = "UPDATE tblmilestone SET milestone =?, responsavel =?, detalhe =?, data =? WHERE id_milestone = ?";
				try {
					con = Conexao.getConexao();
					PreparedStatement pd = con.prepareStatement(sql);
					
					pd.setString(1, rs.getMilestone());
					pd.setString(2, rs.getResponsavel());
					pd.setString(3, rs.getDetalhe());
					pd.setDate(4, new Date(rs.getData().getTimeInMillis()));
					pd.setInt(5, rs.getId_milestone());
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

			//3-Eliminar Servi�o
					public void del_milestone(int id)
					{
						String sql = "DELETE FROM tblmilestone WHERE id_milestone=?";
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
//				Modelo de negocio
					
					public void addVendasEOfertas(Instituicao fun)
					{
						String sql = "INSERT INTO tblquevendemoofertavalor (FK_instituicao,quem_vendemo,oferta_valor) VALUES (?,?,?)";
						try {
							con = Conexao.getConexao();
							PreparedStatement ent = con.prepareStatement(sql);
							ent.setInt(1, fun.getId_instituicao());
							ent.setString(2, fun.getQuem_vendemo());
							ent.setString(3, fun.getOferta_valor());
							ent.execute();
							ent.close();
							System.out.println("Cadastro de sucesso...VendasEOfertas"); 
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
					
					public Instituicao buscarVendasEOfertas()
					{
						Instituicao pd = null;
						String sql = "SELECT * FROM tblquevendemoofertavalor";
						try {
							con = Conexao.getConexao();
							PreparedStatement preparador = con.prepareStatement(sql);
							 ResultSet rs = preparador.executeQuery();
							 while(rs.next())
							 {
								 pd = new Instituicao();
								 pd.setId_instituicao(rs.getInt("fk_instituicao"));
								 pd.setQuem_vendemo(rs.getString("quem_vendemo"));
								 pd.setOferta_valor(rs.getString("oferta_valor"));
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

					
					public void alt_vendaEOfertas(Instituicao rs)
					{
						String sql = "UPDATE tblquevendemoofertavalor SET quem_vendemo =?, oferta_valor =? WHERE fk_instituicao = ?";
						try {
							con = Conexao.getConexao();
							PreparedStatement pd = con.prepareStatement(sql);
							
							pd.setString(1, rs.getQuem_vendemo());
							pd.setString(2, rs.getOferta_valor());
							pd.setInt(3, rs.getId_instituicao());
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

					
					public void addSegmentosDeCliente(Instituicao fun)
					{
						String sql = "INSERT INTO tblquemvendemosegmentocllientemercadoalvo (Fk_instituicao,quem_vendemo,segmento_cliente,mercado_alvo) VALUES (?,?,?,?)";
						try {
							con = Conexao.getConexao();
							PreparedStatement ent = con.prepareStatement(sql);
							ent.setInt(1, fun.getId_instituicao());
							ent.setString(2, fun.getQuem_vendemo());
							ent.setString(3, fun.getSegmento_cliente());
							ent.setString(4, fun.getMercado_alvo());
							ent.execute();
							ent.close();
							System.out.println("Cadastro de sucesso...Segmentos"); 
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
					
					public Instituicao buscarSegmentosDeCliente()
					{
						Instituicao pd = null;
						String sql = "SELECT * FROM tblquemvendemosegmentocllientemercadoalvo";
						try {
							con = Conexao.getConexao();
							PreparedStatement preparador = con.prepareStatement(sql);
							 ResultSet rs = preparador.executeQuery();
							 while(rs.next())
							 {
								 pd = new Instituicao();
								 pd.setId_instituicao(rs.getInt("fk_instituicao"));
								 pd.setQuem_vendemo(rs.getString("quem_vendemo"));
								 pd.setSegmento_cliente(rs.getString("segmento_cliente"));
								 pd.setMercado_alvo(rs.getString("mercado_alvo"));
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

					public void alt_segmentoDeCliente(Instituicao ins)
					{
						String sql = "UPDATE tblquemvendemosegmentocllientemercadoalvo set quem_vendemo = ?, segmento_cliente = ?,mercado_alvo = ? where fk_instituicao = ?";
						try {
							con = Conexao.getConexao();
							PreparedStatement cp = con.prepareStatement(sql);
							
							cp.setString(1, ins.getQuem_vendemo());
							cp.setString(2, ins.getSegmento_cliente());
							cp.setString(3, ins.getMercado_alvo());
							cp.setInt	(4, ins.getId_instituicao());
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

					
					public void addComoVendemos(Instituicao fun)
					{
						String sql = "INSERT INTO tblcomovendemocanalvenda(FK_instituicao,como_vendemo,canal_venda) VALUES (?,?,?)";
						try {
							con = Conexao.getConexao();
							PreparedStatement ent = con.prepareStatement(sql);
							ent.setInt(1, fun.getId_instituicao());
							ent.setString(2, fun.getComo_vendemo());
							ent.setString(3, fun.getCanal_venda());
							ent.execute();
							ent.close();
							System.out.println("Cadastro de sucesso...Segmentos"); 
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
					
					public Instituicao buscarComoVendemos()
					{
						Instituicao pd = null;
						String sql = "SELECT * FROM tblcomovendemocanalvenda";
						try {
							con = Conexao.getConexao();
							PreparedStatement preparador = con.prepareStatement(sql);
							 ResultSet rs = preparador.executeQuery();
							 while(rs.next())
							 {
								 pd = new Instituicao();
								 pd.setId_instituicao(rs.getInt("fk_instituicao"));
								 pd.setComo_vendemo(rs.getString("como_vendemo"));
								 pd.setCanal_venda(rs.getString("canal_venda"));
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

					public void alt_comoVendemos(Instituicao ins)
					{
						String sql = "UPDATE tblcomovendemocanalvenda set como_vendemo = ?, canal_venda = ? where fk_instituicao = ?";
						try {
							con = Conexao.getConexao();
							PreparedStatement cp = con.prepareStatement(sql);
							
							cp.setString(1, ins.getComo_vendemo());
							cp.setString(2, ins.getCanal_venda());
							cp.setInt	(3, ins.getId_instituicao());
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
					
					public void addRelacoes(Instituicao pd)
					{
						String sql = "INSERT INTO tblrelacaocliente(FK_instituicao,relacao_cliente) VALUES (?,?)";
						try {
							con = Conexao.getConexao();
							PreparedStatement cp = con.prepareStatement(sql);
							cp.setInt(1, pd.getId_instituicao());
							cp.setString(2, pd.getRelacao_cliente());
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
					
					public Instituicao buscarRelacoes()
					{
						Instituicao pd = null;
						String sql = "SELECT * FROM tblrelacaocliente";
						try {
							con = Conexao.getConexao();
							PreparedStatement preparador = con.prepareStatement(sql);
							 ResultSet rs = preparador.executeQuery();
							 while(rs.next())
							 {
								 pd = new Instituicao();
								 pd.setId_instituicao(rs.getInt("fk_instituicao"));
								 pd.setRelacao_cliente(rs.getString("relacao_cliente"));
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
					
					public void alt_relacoes(Instituicao ins)
					{
						String sql = "UPDATE tblrelacaocliente set relacao_cliente = ? where fk_instituicao = ?";
						try {
							con = Conexao.getConexao();
							PreparedStatement cp = con.prepareStatement(sql);
							
							cp.setString(1, ins.getRelacao_cliente());
							cp.setInt	(2, ins.getId_instituicao());
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

					
					public void addFontesReceitas(Instituicao pd)
					{
						String sql = "INSERT INTO tblfontereceita(FK_instituicao,fonte_receita) VALUES (?,?)";
						try {
							con = Conexao.getConexao();
							PreparedStatement cp = con.prepareStatement(sql);
							cp.setInt(1, pd.getId_instituicao());
							cp.setString(2, pd.getFonte_receita());
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
					
					public Instituicao buscarFontesReceitas()
					{
						Instituicao pd = null;
						String sql = "SELECT * FROM tblfontereceita";
						try {
							con = Conexao.getConexao();
							PreparedStatement preparador = con.prepareStatement(sql);
							 ResultSet rs = preparador.executeQuery();
							 while(rs.next())
							 {
								 pd = new Instituicao();
								 pd.setId_instituicao(rs.getInt("fk_instituicao"));
								 pd.setFonte_receita(rs.getString("fonte_receita"));
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
					
					public void alt_fontesReceitas(Instituicao ins)
					{
						String sql = "UPDATE tblfontereceita set fonte_receita = ? where fk_instituicao = ?";
						try {
							con = Conexao.getConexao();
							PreparedStatement cp = con.prepareStatement(sql);
							
							cp.setString(1, ins.getFonte_receita());
							cp.setInt	(2, ins.getId_instituicao());
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
					
					public void addRecursosChaves(Instituicao pd)
					{
						String sql = "INSERT INTO tblrecursochave(FK_instituicao,recurso_chave) VALUES (?,?)";
						try {
							con = Conexao.getConexao();
							PreparedStatement cp = con.prepareStatement(sql);
							cp.setInt(1, pd.getId_instituicao());
							cp.setString(2, pd.getRecurso_chave());
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
					
					public Instituicao buscarRecursosChaves()
					{
						Instituicao pd = null;
						String sql = "SELECT * FROM tblrecursochave";
						try {
							con = Conexao.getConexao();
							PreparedStatement preparador = con.prepareStatement(sql);
							 ResultSet rs = preparador.executeQuery();
							 while(rs.next())
							 {
								 pd = new Instituicao();
								 pd.setId_instituicao(rs.getInt("fk_instituicao"));
								 pd.setRecurso_chave(rs.getString("recurso_chave"));
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
					
					public void alt_RecuursosChaves(Instituicao ins)
					{
						String sql = "UPDATE tblrecursochave set recurso_chave = ? where fk_instituicao = ?";
						try {
							con = Conexao.getConexao();
							PreparedStatement cp = con.prepareStatement(sql);
							
							cp.setString(1, ins.getRecurso_chave());
							cp.setInt	(2, ins.getId_instituicao());
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

					public void addActividadesChaves(Instituicao pd)
					{
						String sql = "INSERT INTO tblatividadechaveparceirochave(FK_instituicao,atividade_chave,parceiro_chave) VALUES (?,?,?)";
						try {
							con = Conexao.getConexao();
							PreparedStatement cp = con.prepareStatement(sql);
							cp.setInt(1, pd.getId_instituicao());
							cp.setString(2, pd.getAtividade_chave());
							cp.setString(3, pd.getParceiro_chave());
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
					
					public Instituicao buscarActividadesChaves()
					{
						Instituicao pd = null;
						String sql = "SELECT * FROM tblatividadechaveparceirochave";
						try {
							con = Conexao.getConexao();
							PreparedStatement preparador = con.prepareStatement(sql);
							 ResultSet rs = preparador.executeQuery();
							 while(rs.next())
							 {
								 pd = new Instituicao();
								 pd.setId_instituicao(rs.getInt("fk_instituicao"));
								 pd.setAtividade_chave(rs.getString("atividade_chave"));
								 pd.setParceiro_chave(rs.getString("parceiro_chave"));
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

					public void alt_actividdade(Instituicao ins)
					{
						String sql = "UPDATE tblatividadechaveparceirochave set atividade_chave = ?, parceiro_chave = ? where fk_instituicao = ?";
						try {
							con = Conexao.getConexao();
							PreparedStatement cp = con.prepareStatement(sql);
							
							cp.setString(1, ins.getAtividade_chave());
							cp.setString(2, ins.getParceiro_chave());
							cp.setInt	(3, ins.getId_instituicao());
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

					
					public void atTelefoneInstituicao(Instituicao inst) {
						// Cria aqui o statment para altera dados relacionado a telefones da instituicao
						String sql = "UPDATE tbltelefoneinstituicao set tel1 = ?,tel2 = ? WHERE FK_instituicao = ?";
						try {
							con = Conexao.getConexao();
							PreparedStatement ent = con.prepareStatement(sql);
							ent.setLong(1, inst.getTelefone());
							ent.setLong(2, inst.getTelefones());
							ent.setInt(3, inst.getId_instituicao());
							ent.execute();
							ent.close();
							System.out.println("Cadastro de sucesso...TBLTELEFONE"); 
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
