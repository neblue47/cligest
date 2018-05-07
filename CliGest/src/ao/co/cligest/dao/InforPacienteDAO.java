package ao.co.cligest.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import ao.co.cligest.entidades.InforPaciente;
import ao.co.cligest.entidades.Paciente;
import ao.co.cligest.entidades.Plano;

public class InforPacienteDAO {
	
	private Connection con ;

//	1- INSERIR INFORMA��ES ADICIONAIS DO PACIENTE
			public void informPaciente (InforPaciente infP){	
					
	String sql = "INSERT INTO TBLINFORMACAOCLINICA (FK_paciente, FK_grupo_sanguineo, e_deficiente_fisico, FK_membro_amputado, sofre_dalergia, observacao_dalegia, notas,FK_alergia, factor_rh,proveniencia ) " +
						"VALUES (?,?,?,?,?,?,?,?,?,?)";
				try {
					con = Conexao.getConexao();
					PreparedStatement inf = con.prepareStatement(sql);
					inf.setInt(1, infP.getFK_paciente());
					inf.setInt(2, infP.getFK_sanguineo());
					inf.setInt(3, infP.getDif_fisico());
					inf.setInt(4, infP.getFK_m_amputado());
					inf.setInt(5, infP.getSof_dalergia());
					inf.setString(6, infP.getObser_dalegia());
					inf.setString(7, infP.getNotas());
					inf.setInt(8, infP.getFK_alergia());
					inf.setString(9, infP.getFactor_rh());
					inf.setString(10, infP.getProveniencia());
					inf.execute();
					inf.close();
					System.out.println("Cadastro de sucesso...TBLINFORMACAOCLINICA"); 
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
//			2-Mostrar os dados na Combobox
			public List<InforPaciente> buscarGrupoSanguineo()
			{
				List <InforPaciente> lista = new ArrayList<InforPaciente>();
				String sql = "SELECT * FROM TBLGRUPOSANGUINEO";
				try {
					con = Conexao.getConexao();
					 PreparedStatement preparador = con.prepareStatement(sql);
					 ResultSet rs = preparador.executeQuery();
					 while(rs.next())
					 {
						 InforPaciente ip = new InforPaciente();
						 ip.setId_sanguineo(rs.getInt("id_grupo_sanguineo"));
						 ip.setSanguineo(rs.getString("grupo_sanguineo"));
						 ip.setDescricao(rs.getString("descricao"));
						 lista.add(ip);
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
//			4-Mostrar os dados na combobox
			public List<InforPaciente> buscarMembroAmputado()
			{
				List <InforPaciente> lista = new ArrayList<InforPaciente>();
				String sql = "SELECT * FROM TBLMEMBROAMPUTADO";
				try {
					con = Conexao.getConexao();
					 PreparedStatement preparador = con.prepareStatement(sql);
					 ResultSet rs = preparador.executeQuery();
					 while(rs.next())
					 {
						 InforPaciente ip = new InforPaciente();
						 ip.setId_m_amputado(rs.getInt("id_membro_amputado"));
						 ip.setM_amputado(rs.getString("membro_amputado"));
						 lista.add(ip);
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
//			5- pesquisar o plano de saude
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
							 //fun.setDataNasc(rs.getDate("data_nascimento"));
							// fun.setTelefone(rs.getInt("telefone"));
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
//			 6- mostrar dados na tabela editPlano.
			 public List <InforPaciente> InforVisualizarEdInfP()
				{
					List <InforPaciente> lista = new ArrayList<InforPaciente>();
					String sql = "SELECT * FROM VWINFORMACAOCLINICA ";
					try{
						con = Conexao.getConexao();
						PreparedStatement preparador = con.prepareStatement(sql);
						ResultSet ag = preparador.executeQuery();
						while(ag.next())
						 {
							InforPaciente ep = new InforPaciente();
							ep.setFK_paciente(ag.getInt("FK_paciente"));
							ep.setFK_sanguineo(ag.getInt("FK_grupo_sanguineo"));
							ep.setDif_fisico(ag.getInt("e_deficiente_fisico"));
							ep.setFK_m_amputado(ag.getInt("FK_membro_amputado"));
							ep.setSof_dalergia(ag.getInt("sofre_dalergia"));
							ep.setObser_dalegia(ag.getString("observacao_dalegia"));
							ep.setNotas(ag.getString("notas"));
							ep.setNumero_processo(ag.getString("numero_processo"));
							ep.setDescricao(ag.getString("descricao"));
							ep.setSanguineo(ag.getString("grupo_sanguineo"));
							ep.setM_amputado(ag.getString("membro_amputado"));
							ep.setNome(ag.getString("nome"));
							ep.setNomem(ag.getString("nome_meio"));
							ep.setApelido(ag.getString("ultimo_nome"));
							ep.setDataNasc(ag.getDate("data_nascimento"));
							ep.setNum_Doc(ag.getString("numero_documento"));
							  ep.setFK_alergia(ag.getInt("FK_alergia"));
							   ep.setFactor_rh(ag.getString("factor_rh"));
							ep.setProveniencia(ag.getString("proveniencia"));
							ep.setAlergia(ag.getString("alergia"));
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
//			 Eliminar informa��o do paciente
				public void delete_InforPaciente (int id)
				{
					String sql = "DELETE FROM TBLINFORMACAOCLINICA  WHERE FK_paciente=?";
					try {
						con = Conexao.getConexao();
						 PreparedStatement preparador = con.prepareStatement(sql);
						 preparador.setInt(1, id);
						 preparador.execute();
						 preparador.close();
						 System.out.println("Exclusao com sucesso...TBLINFORMACAOCLINICA"); 
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
//			  7-Editar Plano de sa�de
			public InforPaciente edit_inforPaciente(int ed)
			{
				InforPaciente pc = new InforPaciente();
				String sql = "SELECT * FROM VWINFORMACAOCLINICA WHERE FK_paciente =?";
				try {
					con = Conexao.getConexao();
					PreparedStatement preparador = con.prepareStatement(sql);
					 preparador.setInt(1, ed);
					 ResultSet rs = preparador.executeQuery();
					 while(rs.next())
					 {
						pc.setFK_paciente(rs.getInt("FK_paciente"));
						pc.setId_entidade(rs.getInt("FK_paciente"));
						pc.setFK_sanguineo(rs.getInt("FK_grupo_sanguineo"));
						pc.setDif_fisico(rs.getInt("e_deficiente_fisico"));
						pc.setFK_m_amputado(rs.getInt("FK_membro_amputado"));
						pc.setSof_dalergia(rs.getInt("sofre_dalergia"));
						pc.setObser_dalegia(rs.getString("observacao_dalegia"));
						pc.setNotas(rs.getString("notas"));
						pc.setNumero_processo(rs.getString("numero_processo"));
						pc.setDescricao(rs.getString("descricao"));
						pc.setSanguineo(rs.getString("grupo_sanguineo"));
						pc.setM_amputado(rs.getString("membro_amputado"));
						pc.setNome(rs.getString("nome"));
						pc.setNomem(rs.getString("nome_meio"));
						pc.setApelido(rs.getString("ultimo_nome"));
						pc.setDataNasc(rs.getDate("data_nascimento"));
						pc.setNum_Doc(rs.getString("numero_documento"));
						pc.setFK_alergia(rs.getInt("FK_alergia"));
						pc.setFactor_rh(rs.getString("factor_rh"));
						pc.setProveniencia(rs.getString("proveniencia"));
						
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
			
			public InforPaciente edit_inforPaciente(String ed)
			{
				InforPaciente pc = new InforPaciente();
				String sql = "SELECT * FROM vwentidadecomopaciente WHERE FK_entidade LIKE ?";
				try {
					con = Conexao.getConexao();
					PreparedStatement preparador = con.prepareStatement(sql);
					 preparador.setString(1, ed);
					 ResultSet rs = preparador.executeQuery();
					 if(rs.next())
					 {
					
						pc.setId_entidade(rs.getInt("FK_entidade"));
						pc.setNome(rs.getString("nome"));
						pc.setNomem(rs.getString("nome_meio"));
						pc.setApelido(rs.getString("ultimo_nome"));
					 }
					 
					 System.out.println("Encontrado com sucesso!!!");
					 preparador.close();
				} catch (SQLException e) {
					System.out.println("Eroo "+e);
				}
				catch (Exception e) {
				System.out.println("Eroo "+e);
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
//				8- Alterar Plano de sa�de
				public void alterarInformacaoClinica(InforPaciente pns)
				{
					String sql = "UPDATE TBLINFORMACAOCLINICA SET FK_grupo_sanguineo =?, e_deficiente_fisico =?, FK_membro_amputado =?, sofre_dalergia =?, observacao_dalegia =?, notas =? , FK_alergia = ?, factor_rh = ? WHERE FK_paciente =?";
					try {
						con = Conexao.getConexao();
						PreparedStatement ps = con.prepareStatement(sql);
						ps.setInt(1, pns.getFK_sanguineo());
						ps.setInt(2, pns.getDif_fisico());
						ps.setInt(3, pns.getFK_m_amputado());
						ps.setInt(4, pns.getSof_dalergia());
						ps.setString(5, pns.getObser_dalegia());
						ps.setString(6, pns.getNotas());
						ps.setInt(7, pns.getFK_alergia());
						ps.setString(8, pns.getFactor_rh());
						ps.setInt(9, pns.getFK_paciente());
						
						ps.execute();
						ps.close();
						System.out.println("Alteracao com sucesso...TBLINFORMACAOCLINICA"); 
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
//				2- Mostrar o nome no texte field Paciente
				public InforPaciente buscarInforPaciente (String num)
				{
					InforPaciente pac = new InforPaciente();
					String sql = "SELECT * FROM VWINFORMACAOCLINICA WHERE FK_paciente LIKE ?";
					try {
						con = Conexao.getConexao();
						 PreparedStatement preparador = con.prepareStatement(sql);
						 preparador.setString(1, num);
						 ResultSet rs = preparador.executeQuery();
						 if(rs.next())
						 {
							 pac.setFK_paciente(rs.getInt("FK_paciente"));
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
				
				public String checkedcmzero(int cmzero){
					String check=null;
					if(cmzero == 0){
					   check = "checked = 'checked'";
					}
					return check;
				}
				
				public String checkedcmum(int cmum){
					String check=null;
					if(cmum == 1){
					   check = "checked = 'checked'";
					}
					return check;
				}
}
