package ao.co.cligest.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.text.ParseException;
import java.text.DateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import ao.co.cligest.entidades.*;


public class PlanoDAO {
	
	private Connection con;
	Formatando ft =new Formatando();
 
	
	
	 //1-INSERIR PLANO DE SA�DE
		public void InserirPlano(Plano sv)
		{
			String sql = "INSERT INTO TBLPLANODESAUDE (FK_paciente,FK_fornecedor,FK_tipo_dcobertura,numero_dcartao,data_emisao,data_dexpiracao,caminho_do_arquivo,data_dentrada,FK_funcionario) VALUES (?,?,?,?,?,?,?,?,?)";
			try {
				con = Conexao.getConexao();
				PreparedStatement serv = con.prepareStatement(sql);
				serv.setInt(1, sv.getFK_paciente());
				serv.setInt(2, sv.getFK_fornecedor());
				serv.setInt(3, sv.getFK_tipo_dcobertura());
				serv.setString(4, sv.getNumero_dcartao());
				serv.setDate(5, (java.sql.Date) sv.getData_emisao());
				serv.setDate(6, (java.sql.Date) sv.getData_dexpiracao());
				serv.setString(7, sv.getCaminho_do_arquivo());
				serv.setDate(8, sv.getData());
				serv.setInt(9, sv.getFK_funcionario());
				serv.execute();
				serv.close();
				System.out.println("Cadastro com sucesso...TBLPLANODESAUDE"); 
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
//		2- Mostrar o nome no texte field Paciente
		public Paciente buscarNome (String num)
		{
			Paciente pac = new Paciente();
			String sql = "SELECT * FROM vwentidadecomopaciente WHERE FK_entidade LIKE ?";
			try {
				con = Conexao.getConexao();
				 PreparedStatement preparador = con.prepareStatement(sql);
				 preparador.setString(1, num);
				 ResultSet rs = preparador.executeQuery();
				 if(rs.next())
				 {
					 pac.setId_entidade(rs.getInt("FK_entidade"));
					 pac.setNome(rs.getString("NOME"));
					 pac.setNomem(rs.getString("NOME_MEIO"));
					 pac.setApelido(rs.getString("ULTIMO_NOME"));
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
		 
			return pac;
	}
//		3-Mostrar os dados do asseguradora
		public List <Plano> ForncdorVisualizar()
		{
			List <Plano> lista = new ArrayList<Plano>();
			String sql = "SELECT * FROM vwaseguradora";
			try{
				con = Conexao.getConexao();
				PreparedStatement preparador = con.prepareStatement(sql);
				ResultSet ag = preparador.executeQuery();
				while(ag.next())
				 {
					Plano pn = new Plano();
					pn.setFK_fornecedor(ag.getInt("FK_fornecedor"));
					pn.setNum_fornecedor(ag.getString("numero_fornecedor"));
					pn.setNumero_doalvara(ag.getString("numero_doalvara"));
					pn.setEmpresa(ag.getString("empresa"));
					pn.setNif(ag.getString("nif"));
					pn.setNota(ag.getString("nota"));
				    lista.add(pn);
				    }
				    preparador.close();
//				    con.close();
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
				  return lista;
				 }

//		5-Mostrar os dados do fornecedor
		public List <Plano> CobertVisualizar()
		{
			List <Plano> lista = new ArrayList<Plano>();
			String sql = "SELECT * FROM TBLTIPODECOBERTURA";
			try{
				con = Conexao.getConexao();
				PreparedStatement preparador = con.prepareStatement(sql);
				ResultSet ag = preparador.executeQuery();
				while(ag.next())
				 {
					Plano pn = new Plano();
					pn.setId_tipo_dcobertura(ag.getInt("id_tipo_dcobertura"));
//					pn.setTipo_dcobertura(ag.getString("tipo_dcobertura"));
//					pn.setFK_beneficio(ag.getInt("FK_beneficio_dsaude"));
				     lista.add(pn);
				    }
				    preparador.close();
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
				  return lista;
				 }
//		6- pesquisar o plano de saude
		 public List<Paciente> buscarpacientepornome(String nome)
			
			{
				Paciente fun = null;
				List <Paciente> lista = new ArrayList<Paciente>();
				String sql = "SELECT * FROM VWENTIDADECOMOPACIENTE where nome LIKE ?";
				try {
					con = Conexao.getConexao();
					PreparedStatement preparador = con.prepareStatement(sql);
					 preparador.setString(1, nome);
					 ResultSet rs = preparador.executeQuery();
					 while (rs.next())
					 {
						 fun = new Paciente();
						 fun.setId_entidade(rs.getInt("FK_entidade"));
						 fun.setNumero_processo(rs.getString("numero_processo"));
						 fun.setNome(rs.getString("nome"));
						 fun.setNomem(rs.getString("nome_meio"));
						 fun.setApelido(rs.getString("ultimo_nome"));
						 Calendar data = Calendar.getInstance();
						 data.setTime(rs.getDate("data_nascimento"));
						 fun.setDataNasc(data);
						 fun.setTelefone(rs.getLong("telefone"));
						 fun.setEmail(rs.getString("email"));
						 fun.setEndereco(rs.getString("endereco"));
						 lista.add(fun);
					 }
					 preparador.close();
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
				return lista;
			}
//		 7- preencher combobox
		 public List<Plano> pesquisarCoberturaPorAssegu (int codigo)
			{
				List <Plano> lista = new ArrayList<Plano>();
				String sql = "SELECT * FROM TBLASEGURADORA where id_tipo_dcobertura = ? ";
				try {
					con = Conexao.getConexao();
					 PreparedStatement preparador = con.prepareStatement(sql);
					 preparador.setInt(1, codigo);
					 ResultSet rs = preparador.executeQuery();
					 while(rs.next())
					 {
						 Plano adPn = new Plano();
						 adPn.setId_tipo_dcobertura(rs.getInt("id_tipo_dcobertura"));
						 adPn.setTipo_dcobertura(rs.getString("tipo_dcobertura"));
						 lista.add(adPn);
					 }
					 preparador.close();
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
				return lista;
			}
//		 8- mostrar dados na tabela editPlano.
		 public List <Plano> PlanoVisualizarEdP()
			{
				List <Plano> lista = new ArrayList<Plano>();
				String sql = "SELECT * FROM VWPLANODESAUDE";
				try{
					con = Conexao.getConexao();
					PreparedStatement preparador = con.prepareStatement(sql);
					ResultSet ag = preparador.executeQuery();
					while(ag.next())
					 {
						Plano ep = new Plano();
						ep.setId_plano_dsaude(ag.getInt("id_plano_dsaude"));
						ep.setNumero_dcartao(ag.getString("numero_dcartao"));
						ep.setNome(ag.getString("nome"));
						ep.setNomem(ag.getString("nome_meio"));
						ep.setApelido(ag.getString("ultimo_nome"));
						ep.setData_dexpiracao(ag.getDate("data_dexpiracao"));
//						ep.setCaminho_do_arquivo(ag.getString("caminho_do_arquivo"));
						ep.setTipo_dcobertura(ag.getString("tipo_dcobertura"));
						lista.add(ep);
					    }
					    preparador.close();
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
					  return lista;
					 }
//			3-Eliminar Servi�o
			public void planoEliminar (int id)
			{
				String sql = "DELETE FROM TBLPLANODESAUDE  WHERE id_plano_dsaude=?";
				try {
					con = Conexao.getConexao();
					 PreparedStatement preparador = con.prepareStatement(sql);
					 preparador.setInt(1, id);
					 preparador.execute();
					 preparador.close();
					 System.out.println("Exclusao com sucesso...TBLPLANODESAUDE"); 
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
//		 ------------------------------------- MODIFICA��O PLANO DE SA�DE
//			9-Pesquisar Plano de sa�de
			public Plano PesquisarPlanoEdit (String nome)
			{
				Plano pn = new Plano();
				String sql = "SELECT * FROM VWPLANODESAUDE where id_plano_dsaude Like ?";
				try {
					con = Conexao.getConexao();
					 PreparedStatement preparador = con.prepareStatement(sql);
					 preparador.setString(1, nome);
					 ResultSet rs = preparador.executeQuery();
					 while(rs.next())
					 {
						 
						 pn.setId_plano_dsaude(rs.getInt("id_plano_dsaude"));
						 pn.setFK_paciente(rs.getInt("FK_paciente"));
						 pn.setFK_fornecedor(rs.getInt("FK_fornecedor"));
						 pn.setFK_tipo_dcobertura(rs.getInt("FK_tipo_dcobertura"));
						 pn.setNumero_dcartao(rs.getString("numero_dcartao"));
						 pn.setData_emisao(rs.getDate("data_emisao"));
						 pn.setData_dexpiracao(rs.getDate("data_dexpiracao"));
//						 pn.setCaminho_do_arquivo(rs.getString("caminho_do_arquivo"));
						 
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
				return pn;
			}
			
//			10- Alterar Plano de sa�de
			public void alt_plano(Plano pns)
			{
				String sql = "UPDATE TBLPLANODESAUDE SET FK_paciente =?, "
						                              + "FK_fornecedor =?, "
						                              + "FK_tipo_dcobertura =?, "
						                              + "numero_dcartao =?, "
						                              + "data_emisao =?, "
						                              + "data_dexpiracao =? "
						                              + "WHERE id_plano_dsaude = ?";
				try {
					con = Conexao.getConexao();
					PreparedStatement ps = con.prepareStatement(sql);
					ps.setInt(1, pns.getFK_paciente());
					ps.setInt(2, pns.getFK_fornecedor());
					ps.setInt(3, pns.getFK_tipo_dcobertura());
					ps.setString(4, pns.getNumero_dcartao());
					ps.setDate(5, (java.sql.Date)pns.getData_emisao());
					ps.setDate(6, (java.sql.Date)pns.getData_dexpiracao());
					ps.setInt(7, pns.getId_plano_dsaude());
					ps.execute();
					ps.close();
					System.out.println("Alteracao com sucesso...TBLPLANODESAUDE"); 
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
			
//			  11-Editar Plano de sa�dde
			public Plano buscarPlanoEdit(int ed)
			{
				Plano pc = new Plano();
				String sql = "SELECT * FROM VWPLANODESAUDE WHERE id_plano_dsaude = ?";
				try {
					con = Conexao.getConexao();
					PreparedStatement preparador = con.prepareStatement(sql);
					 preparador.setInt(1, ed);
					 ResultSet rs = preparador.executeQuery();
					 if(rs.next())
					 {
						 pc.setId_plano_dsaude(rs.getInt("id_plano_dsaude"));
						 pc.setFK_paciente(rs.getInt("FK_entidade"));
						 pc.setFK_fornecedor(rs.getInt("FK_fornecedor"));
						 pc.setFK_tipo_dcobertura(rs.getInt("id_tipo_dcobertura"));
						 pc.setNumero_dcartao(rs.getString("Numero_dcartao"));
						 pc.setData_emisao(rs.getDate("Data_emisao"));
						 pc.setData_dexpiracao(rs.getDate("data_dexpiracao"));
//						 pc.setCaminho_do_arquivo(rs.getString("caminho_do_arquivo"));
						 pc.setNome(rs.getString("nome"));
						 pc.setNomem(rs.getString("nome_meio"));
						 pc.setApelido(rs.getString("ultimo_nome"));
					 }
					 System.out.println("Encontrado com sucesso!!!");
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
				return pc;
			}
			
			public void salvar(Plano ps)
			{
				if(ps.getFK_entidade()!=0)
				{
					alt_plano(ps);
				}
				else
				{
					InserirPlano(ps);
				}
			}
			
			public Plano getPlanoDoPaciente(int cod)
			{
				Plano plPacinte = new Plano();
				String sql = "SELECT * FROM VWPLANODESAUDE where FK_paciente = ?";
				try{
					con = Conexao.getConexao();
					PreparedStatement preparador = con.prepareStatement(sql);
					preparador.setInt(1, cod);
					ResultSet ag = preparador.executeQuery();
					if(ag.next())
					 {
						
						plPacinte.setId_plano_dsaude(ag.getInt("id_plano_dsaude"));
						plPacinte.setNumero_dcartao(ag.getString("numero_dcartao"));
						plPacinte.setNome(ag.getString("nome"));
						plPacinte.setNomem(ag.getString("nome_meio"));
						plPacinte.setApelido(ag.getString("ultimo_nome"));
						plPacinte.setData_dexpiracao(ag.getDate("data_dexpiracao"));
						plPacinte.setTipo_dcobertura(ag.getString("tipo_dcobertura"));
						plPacinte.setFK_tipo_dcobertura(ag.getInt("id_tipo_dcobertura"));
						plPacinte.setNumero_dcartao(ag.getString("numero_dcartao"));
						plPacinte.setEmpresa(ft.transforma(ag.getString("Empresa")));
						plPacinte.setFK_fornecedor(ag.getInt("fk_fornecedor"));
						 
					    }
					    preparador.close();
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
					  return plPacinte;
					 }
}
