package ao.co.cligest.negocio;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import ao.co.cligest.entidades.*;
import ao.co.cligest.navegacao.FacturacaoController;


public class ServicoDAO {
	
	private Connection con ;
	
	//para listar as catregorias de servicos de teste de qualidade
	public List <Servico> buscarCategoriaTesteQualidade()
	{
		List <Servico> lista = new ArrayList<Servico>();
		String sql = "SELECT * FROM tblcategoriatestequalidade";
		try {
			con = Conexao.getConexao();
			 PreparedStatement preparador = con.prepareStatement(sql);
			 ResultSet rs = preparador.executeQuery();
			 while(rs.next())
			 {
				 Servico ser = new Servico();
				 ser.setId_categoria_tq(rs.getInt("id_categoria_tq"));
				 ser.setCategoria_tq(rs.getString("categoria_tq"));
				 lista.add(ser);
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
	
	 //1-INSERIR novo SERVICO de teste de qualidade
	public void inserirServTesteQualid(Servico sv)
	{
		String sql = "INSERT INTO tblservicosdetestedequalidade (FK_taxa,FK_categoria_tq,serv_teste_qualidade,preco,descricao_tq) VALUES (?,?,?,?,?)";
		try {
			con = Conexao.getConexao();
			PreparedStatement serv = con.prepareStatement(sql);
			serv.setInt(1, sv.getFK_taxa());
			serv.setInt(2, sv.getFK_categoria_tq());
			serv.setString(3, sv.getServ_teste_qualidade());
			serv.setDouble(4, sv.getPreco());
			serv.setString(5, sv.getDescricao_tq());
			serv.execute();
			serv.close();
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
	
	//1-INSERIR novo SERVICO de teste de qualidade
		public void editarServTesteQualid(Servico sv)
		{
			String sql = "UPDATE tblservicosdetestedequalidade SET FK_taxa=?,FK_categoria_tq=?,serv_teste_qualidade=?,preco=?,descricao_tq=? where id_servico_tq=?";
			try {
				con = Conexao.getConexao();
				PreparedStatement serv = con.prepareStatement(sql);
				serv.setInt(1, sv.getFK_taxa());
				serv.setInt(2, sv.getFK_categoria_tq());
				serv.setString(3, sv.getServ_teste_qualidade());
				serv.setDouble(4, sv.getPreco());
				serv.setString(5, sv.getDescricao_tq());
				serv.setInt(6, sv.getId_servico_tq());
				serv.execute();
				serv.close();
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
	
		//para teste de qualidade
		
		//para listar  is servios teste de Qualidade
		public List<Servico> listarServQualidade(String nomeServioQualidade) {
			List<Servico> listaAge = new ArrayList<Servico>();
			String sql = "SELECT * FROM tblagendartestedequalidade WHERE numero_doservico LIKE ?";
			try {
				con = Conexao.getConexao();
				PreparedStatement preparador = con.prepareStatement(sql);
				preparador.setString(1, nomeServioQualidade);
				ResultSet rs = preparador.executeQuery();
				while (rs.next()) {
					Servico fun = new Servico();
					fun.setNumero_doservico(rs.getString("numero_doservico"));
					fun.setFK_funcionario(rs.getInt("FK_funcionario"));
					fun.setFK_serv_test_quali(rs.getInt("FK_serv_test_quali"));
					fun.setFK_empresa(rs.getInt("FK_empresa"));
					fun.setFK_dia_dasemana(rs.getInt("FK_dia_dasemana"));
					fun.setData(rs.getDate("data"));
					fun.setHora(rs.getTime("hora"));
					listaAge.add(fun);
				}
				preparador.close();
			} catch (SQLException e) {
				e.printStackTrace();
			} catch (Exception e) {
				System.out.println(e);
			} finally {
				try {
					con.close();
				} catch (SQLException ef) {
					System.out.println("Erro finalizar: " + ef);
				}
			}
			return listaAge;
		}
		
		// INSERIR DATA AUTOMMï¿½TICA
		public java.sql.Date data_registo() {
			java.sql.Date dt_registo = null;
			Calendar c = Calendar.getInstance(); // Cria um objeto para armazenar a
													// data atual
			c.setTime(new Date()); // pega a data atual do sistema
			int diaAtual = c.get(Calendar.DAY_OF_MONTH); // DIA
			int mesAtual = c.get(Calendar.MONTH) + 1; // MES
			int anoAtual = c.get(Calendar.YEAR); // ANO
			String hoje = anoAtual + "/" + mesAtual + "/" + diaAtual;
			DateFormat formate = new SimpleDateFormat("yyyy/MM/dd");
			java.util.Date a;
			try {
				a = (java.util.Date) formate.parse(hoje);
				dt_registo = new java.sql.Date(a.getTime());
			} catch (ParseException e) {
			}
			return dt_registo;
		}
		
		
		
	public void inseriAgendTesteQual(Servico fun) {

			
			String sql = "INSERT INTO tblagendartestedequalidade(numero_doservico, FK_funcionario, FK_serv_test_quali, FK_empresa, FK_dia_dasemana, data, hora) VALUES(?,?,?,?,?,?,?)"
					+ "hora_do_fin,data)" + " VALUES(?,?,?,?,?)";
			try {
				con = Conexao.getConexao();
				PreparedStatement agendaHora = con.prepareStatement(sql);
				agendaHora.setString(1, fun.getNumero_doservico());
				agendaHora.setInt(2, fun.getFK_funcionario());
				agendaHora.setInt(3, fun.getFK_serv_test_quali());
				agendaHora.setInt(4, fun.getFK_empresa());
				agendaHora.setInt(5, fun.getFK_dia_dasemana());
				agendaHora.setDate(6, data_registo());
				agendaHora.setTime(7, fun.getHora());
				agendaHora.execute();
				agendaHora.close();
				System.out.println("Cadastro de Sucesso....");
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

	
	 //1-INSERIR SERVICO
		public void ServicoInserir(Servico sv)
		{
			String sql = "INSERT INTO TBLSERVICO (descricao,preco,servico,FK_especialidade,FK_taxa,FK_categoria) VALUES (?,?,?,?,?,?)";
			try {
				con = Conexao.getConexao();
				PreparedStatement serv = con.prepareStatement(sql);
				serv.setString(1, sv.getDescricao());
				serv.setDouble(2, sv.getPreco());
				serv.setString(3, sv.getServico());
				serv.setInt(4, sv.getFK_especialidade());
				serv.setDouble(5, sv.getTaxa());
				serv.setInt(6, sv.getCategoria());
				serv.execute();
				serv.close();
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

//		2-Visualizar servico
		public List <Servico> BuscarServico ()
		{
			List <Servico> lista = new ArrayList<Servico>();
			String sql = "SELECT * FROM vwservico order by servico asc";
			try {
				con = Conexao.getConexao();
				 PreparedStatement preparador = con.prepareStatement(sql);
				 ResultSet rs = preparador.executeQuery();
				 while(rs.next())
				 {
					 Servico ser = new Servico();
					 ser.setId_servico(rs.getInt("id_servico"));
					 ser.setServico(rs.getString("servico"));
//					 ser.setFK_tipo_de_servico(rs.getInt("fk_tipo_de_servico"));
					 ser.setId_especialidade(rs.getInt("fk_especialidade"));
					 ser.setEspecialidade(rs.getString("especialidade"));
					 ser.setTaxa(rs.getFloat("taxa"));
					 ser.setPreco(rs.getDouble("preco"));
					 ser.setDescricao(rs.getString("descricao"));
//					 ser.setTipo_de_servico(rs.getString("tipo_de_servico"));
					 ser.setNome_categoria(rs.getString("categoriadeservico"));
					 lista.add(ser);
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
		
		public List <Servico> BuscarServicoConsulta ()
		{
			List <Servico> lista = new ArrayList<Servico>();
			String sql = "SELECT * FROM vwservico where fk_categoria = 1 order by servico asc";
			try {
				con = Conexao.getConexao();
				 PreparedStatement preparador = con.prepareStatement(sql);
				 ResultSet rs = preparador.executeQuery();
				 while(rs.next())
				 {
					 Servico ser = new Servico();
					 ser.setId_servico(rs.getInt("id_servico"));
					 ser.setServico(rs.getString("servico"));
//					 ser.setFK_tipo_de_servico(rs.getInt("fk_tipo_de_servico"));
					 ser.setId_especialidade(rs.getInt("fk_especialidade"));
					 ser.setEspecialidade(rs.getString("especialidade"));
					 ser.setTaxa(rs.getFloat("taxa"));
					 ser.setPreco(rs.getDouble("preco"));
					 ser.setDescricao(rs.getString("descricao"));
//					 ser.setTipo_de_servico(rs.getString("tipo_de_servico"));
					 ser.setNome_categoria(rs.getString("categoriadeservico"));
					 lista.add(ser);
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
		
//		2-Visualizar servico
		public List <Servico> BuscarServicos ()
		{
			List <Servico> lista = new ArrayList<Servico>();
			String sql = "SELECT * FROM vwservico where FK_categoria = 2";
			try {
				con = Conexao.getConexao();
				 PreparedStatement preparador = con.prepareStatement(sql);
				 ResultSet rs = preparador.executeQuery();
				 while(rs.next())
				 {
					 Servico ser = new Servico();
					 ser.setId_servico(rs.getInt("id_servico"));
					 ser.setServico(rs.getString("servico"));
					 ser.setFK_tipo_de_servico(rs.getInt("fk_tipo_de_servico"));
					 ser.setId_especialidade(rs.getInt("fk_especialidade"));
					 ser.setEspecialidade(rs.getString("especialidade"));
					 ser.setTaxa(rs.getFloat("taxa"));
					 ser.setPreco(rs.getDouble("preco"));
					 ser.setDescricao(rs.getString("descricao"));
					 ser.setTipo_de_servico(rs.getString("tipo_de_servico"));
					 ser.setNome_categoria(rs.getString("categoriadeservico"));
					 lista.add(ser);
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

		
		public List <Servico> buscarTaxa()
		{
			List <Servico> lista = new ArrayList<Servico>();
			String sql = "SELECT * FROM tbltaxas";
			try {
				con = Conexao.getConexao();
				 PreparedStatement preparador = con.prepareStatement(sql);
				 ResultSet rs = preparador.executeQuery();
				 while(rs.next())
				 {
					 Servico ser = new Servico();
					 ser.setId_taxa(rs.getInt("id_taxa"));
					 ser.setTipo_de_taxa(rs.getString("tipo_de_taxa"));
					 ser.setTaxa(rs.getFloat("taxa"));
					 lista.add(ser);
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
		public int XequeServico(String termo)
		{
			int ok = 0;
			String sql = "SELECT * FROM TBLSERVICO WHERE servico Like ?";
			try {
				con = Conexao.getConexao();
				PreparedStatement preparador = con.prepareStatement(sql);
				 preparador.setString(1, termo);
				 ResultSet rs = preparador.executeQuery();
				 if(rs.next())
				 {
					 ok = rs.getInt("id_servico");				 
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
		
		
		//Verifica se ja existe um nome de servico  na base
				public int verNomeServQualRepetEditar(String termo, int id)
				{
					int ok = 0;
					String sql = "SELECT * FROM tblservicosdetestedequalidade WHERE serv_teste_qualidade Like ? and id_servico_tq<>?";
					try {
						con = Conexao.getConexao();
						PreparedStatement preparador = con.prepareStatement(sql);
						 preparador.setString(1, termo);
						 preparador.setInt(2, id);
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
		
		//Verifica se ja existe um nome de servico  na base
		public int verificaNomeServQualRepet(String termo)
		{
			int ok = 0;
			String sql = "SELECT * FROM tblservicosdetestedequalidade WHERE serv_teste_qualidade Like ?";
			try {
				con = Conexao.getConexao();
				PreparedStatement preparador = con.prepareStatement(sql);
				 preparador.setString(1, termo);
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
		
//3-Eliminar Serviï¿½o
		public void ServicoEliminar (int id)
		{
			String sql = "DELETE FROM TBLSERVICO WHERE id_servico=?";
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
		
//		4-Pesquisar serviï¿½o
		public Servico PesquisarSerEdit (String num)
		{
			Servico svcs = new Servico();
			String sql = "SELECT * FROM VWSERVICO where id_servico Like ?";
			try {
				con = Conexao.getConexao();
				 PreparedStatement preparador = con.prepareStatement(sql);
				 preparador.setString(1, num);
				 ResultSet rs = preparador.executeQuery();
				 while(rs.next())
				 {
					 //Servico svcs = new Servico();
					 svcs.setId_servico(rs.getInt("id_servico"));
					 svcs.setEspecialidade(rs.getString("especialidade"));
					 svcs.setFK_especialidade(rs.getInt("id_especialidade"));
					 svcs.setPreco(rs.getDouble("preco"));
					 svcs.setServico(rs.getString("servico"));
					// svcs.setTipo_de_servico(rs.getString("tipo_de_servico"));
					 //svcs.setFK_tipo_de_servico(rs.getInt("id_tipo_de_servico"));
					 svcs.setDescricao(rs.getString("descricao"));
					 //lista.add(svcs);
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
			return svcs;
		}
		
		public List <Servico> PesquisarServico (String num)
		{
			List <Servico> lista = new ArrayList<Servico>();
			String sql = "SELECT * FROM VWSERVICO where servico Like ? and fk_categoria =2 or fk_categoria =3";
			try {
				con = Conexao.getConexao();
				 PreparedStatement preparador = con.prepareStatement(sql);
				 preparador.setString(1, "%"+num+"%");
				 ResultSet rs = preparador.executeQuery();
				 while(rs.next())
				 {
					 Servico svcs = new Servico();
					 svcs.setId_servico(rs.getInt("id_servico"));
					 svcs.setEspecialidade(rs.getString("especialidade"));
					 svcs.setId_especialidade(rs.getInt("fk_especialidade"));
					 svcs.setPreco(rs.getDouble("preco"));
					 svcs.setServico(rs.getString("servico"));
					// svcs.setTipo_de_servico(rs.getString("tipo_de_servico"));
					 //svcs.setFK_tipo_de_servico(rs.getInt("fk_tipo_de_servico"));
					 svcs.setDescricao(rs.getString("descricao"));
					 lista.add(svcs);
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
		
		
		public int PesquisarServicos(String pac)
		{
			int fkp = 0;
			String sql = "SELECT id_servico FROM TBLSERVICO where servico = ?";
			try {
				con = Conexao.getConexao();
				PreparedStatement preparador = con.prepareStatement(sql);
				preparador.setString(1, pac);
				ResultSet rs = preparador.executeQuery();
				
				if(rs.next())
					fkp = (rs.getInt("id_servico"));
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
				return fkp;
		}
//		5- Alterar serviï¿½o
		public void alt_servico(Servico srv)
		{
			String sql = "UPDATE TBLSERVICO SET servico =?, preco =?, descricao =?, FK_especialidade =?, FK_taxa =?, FK_categoria =? WHERE id_servico = ?";
			try {
				con = Conexao.getConexao();
				PreparedStatement serC = con.prepareStatement(sql);
				
				serC.setString(1, srv.getServico());
//				serC.setInt(2, srv.getFK_tipo_de_servico());
				serC.setDouble(2, srv.getPreco());
				serC.setString(3, srv.getDescricao());
				serC.setInt(4, srv.getFK_especialidade());
				serC.setDouble(5, srv.getTaxa());
				serC.setInt(6, srv.getCategoria());
				serC.setInt(7, srv.getId_servico());
				serC.execute();
				serC.close(); 
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
		
//		  6-Editar serviï¿½o
		public Servico edit_servico(String ed)
		{
			Servico srv = null;
			String sql = "SELECT * FROM vwservico WHERE id_servico = ?";
			try {
				con = Conexao.getConexao();
				PreparedStatement preparador = con.prepareStatement(sql);
				 preparador.setString(1, ed);
				 ResultSet rs = preparador.executeQuery();
				 while(rs.next())
				 {
					 srv = new Servico();
					 srv.setFK_especialidade(rs.getInt("fk_especialidade"));
//					 srv.setFK_tipo_de_servico(rs.getInt("fK_tipo_de_servico"));
					 srv.setDescricao(rs.getString("descricao"));
					 srv.setPreco(rs.getDouble("preco"));
					 srv.setServico(rs.getString("servico"));	
					 srv.setId_taxa(rs.getInt("fk_taxa"));
					 srv.setCategoria(rs.getInt("fk_categoria"));
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
			return srv;
		}
		
		// Metodos Para pegar Exames Requisitados para a faturaï¿½ï¿½o
		public List<Servico> getServicosExamesRequisitados(int cod)
		{
			List<Servico> lsServicos = new ArrayList<Servico>();
			String sql = "SELECT * FROM vwservicosexamesrequisitado WHERE FK_numero_do_servico_de_exame = ? order by analise_clinica asc";
			try {
				con = Conexao.getConexao();
				PreparedStatement preparador = con.prepareStatement(sql);
				preparador.setInt(1, cod);
				 ResultSet rs = preparador.executeQuery();
				 while(rs.next())
				 {
					 Servico srv = new Servico();
					 
					 srv.setServico(rs.getString("analise_clinica"));
					 srv.setPreco(rs.getDouble("preco"));
					 srv.setFK_servico_exame(rs.getInt("FK_servico_exame"));
					 srv.setFK_numero_do_servico_de_exame(rs.getInt("FK_numero_do_servico_de_exame"));
					 srv.setTaxa(rs.getFloat("taxa"));
					 srv.setFK_paciente(rs.getInt("fk_paciente"));
					 srv.setId_servico(rs.getInt("id_servico_requisitado")); 
					 srv.setNumero_doservico(rs.getString("numero_do_exame"));
					 Calendar data = Calendar.getInstance();
				     data.setTime(rs.getDate("data"));
				     srv.setData_registo(data);
					 
					 lsServicos.add(srv);
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
			return lsServicos;
		}
		
		public List<Servico> getServicosExamesAgendados(int cod)
		{
			List<Servico> lsServicos = new ArrayList<Servico>();
			String sql = "SELECT * FROM vwservicosexamesagendadosem WHERE FK_numero_do_servico_de_exame = ? order by analise_clinica asc";
			try {
				con = Conexao.getConexao();
				PreparedStatement preparador = con.prepareStatement(sql);
				preparador.setInt(1, cod);
				 ResultSet rs = preparador.executeQuery();
				 while(rs.next())
				 {
					 Servico srv = new Servico();
					 srv.setServico(rs.getString("analise_clinica"));
					 srv.setPreco(rs.getDouble("preco"));
					 srv.setFK_servico_exame(rs.getInt("FK_servico_exame"));
					 srv.setFK_numero_do_servico_de_exame(rs.getInt("FK_numero_do_servico_de_exame"));
					 srv.setNumero_doservico(rs.getString("numero_do_exame"));
					 
					 Calendar data = Calendar.getInstance();
				     data.setTime(rs.getDate("data_agendamento"));
				     srv.setData_registo(data);
					 lsServicos.add(srv);
					 			 
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
			return lsServicos;
		}
		
		public List<Servico> getServicosExamesFacturados(int cod)
		{
			List<Servico> lsServicos = new ArrayList<Servico>();
			System.out.println(cod);
			String sql = "select * from vwfacturadasfolhasdecaixas where f_c = ? GROUP BY s_v";
			try {
				con = Conexao.getConexao();
				PreparedStatement preparador = con.prepareStatement(sql);
				preparador.setInt(1, cod);
				 ResultSet rs = preparador.executeQuery();
				 while(rs.next())
				 {
					 Servico srv = new Servico();
					 srv.setServico(rs.getString("c_c"));
					 srv.setPreco(rs.getDouble("f_c_p"));
//					 srv.setFK_servico_exame(rs.getInt("fk_exame_requisitado"));
//					 srv.setFK_numero_do_servico_de_exame(rs.getInt("fk_requisicao_dexame"));
					 srv.setTaxa(rs.getFloat("f_t_k_c"));
					 srv.setId_servico(rs.getInt("s_v"));
					 srv.setValor_total(rs.getDouble("f_c_t"));
					 srv.setId(rs.getInt("f_c"));
					 srv.setNumero_factura(rs.getString("n_f_c"));
					 srv.setTipo_de_servico(rs.getString("metodo_depagameto"));
					 lsServicos.add(srv); 			 
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
			return lsServicos;
		}
		
		public List<Servico> getServicosConsultaFacturados(int cod)
		{
			List<Servico> lsServicos = new ArrayList<Servico>();
			System.out.println(cod);
			String sql = "select * from vwfacturadasfolhasdecaixas where f_c = ?  GROUP BY s_v";
			try {
				con = Conexao.getConexao();
				PreparedStatement preparador = con.prepareStatement(sql);
				preparador.setInt(1, cod);
				 ResultSet rs = preparador.executeQuery();
				 while(rs.next())
				 {
					 Servico srv = new Servico();
					 
					 srv.setId(rs.getInt("f_c"));
					 srv.setNumero_factura(rs.getString("n_f_c"));
					 srv.setServico(rs.getString("c_c"));
					 srv.setPreco(rs.getDouble("f_c_p"));
					 srv.setTaxa(rs.getFloat("f_t_k_c"));
					 srv.setValor_total(rs.getDouble("f_c_t"));
					 srv.setTipo_de_servico(rs.getString("metodo_depagameto"));
					 
					 lsServicos.add(srv);
					 			 
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
			return lsServicos;
		}
		
		public List<Servico> getServicosConsultaProdutos(int cod)
		{
			List<Servico> lsServicos = new ArrayList<Servico>();
			System.out.println(cod);
			String sql = "select * from vwfacturadasfolhasdecaixas where f_c = ? GROUP BY s_v";
			try {
				con = Conexao.getConexao();
				PreparedStatement preparador = con.prepareStatement(sql);
				preparador.setInt(1, cod);
				 ResultSet rs = preparador.executeQuery();
				 while(rs.next())
				 {
					 Servico srv = new Servico();
					 
					 srv.setId(rs.getInt("f_c"));
					 srv.setNumero_factura(rs.getString("n_f_c"));
					 srv.setServico(rs.getString("c_c"));
					 srv.setPreco(rs.getDouble("f_c_p"));
					 srv.setQuantidade(rs.getInt("f_c_q"));
					 srv.setTaxa(rs.getFloat("f_t_k_c"));
					 srv.setValor_total(rs.getDouble("f_c_t"));
					 srv.setTipo_de_servico(rs.getString("metodo_depagameto"));
					 lsServicos.add(srv);
					 			 
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
			return lsServicos;
		}
		
		public List<Servico> getServicosConsultaServicosGerais(int cod)
		{
			List<Servico> lsServicos = new ArrayList<Servico>();
			System.out.println(cod);
			String sql = "select * from vwfacturadasfolhasdecaixas where f_c = ? GROUP BY s_v";
			try {
				con = Conexao.getConexao();
				PreparedStatement preparador = con.prepareStatement(sql);
				preparador.setInt(1, cod);
				 ResultSet rs = preparador.executeQuery();
				 while(rs.next())
				 {
					 Servico srv = new Servico();
					 srv.setServico(rs.getString("c_c"));
					 srv.setId(rs.getInt("f_c"));
					 srv.setNumero_factura(rs.getString("n_f_c"));
					 srv.setPreco(rs.getDouble("f_c_p"));
					 srv.setQuantidade(rs.getInt("f_c_q"));
					 srv.setValor_total(rs.getDouble("f_c_t"));
					 srv.setTaxa(rs.getDouble("f_t_k_c"));
					 srv.setTipo_de_servico(rs.getString("metodo_depagameto"));
					 lsServicos.add(srv); 			 
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
			return lsServicos;
		}
		
		 public Servico buscarServicoTratamentos(String termo)
		   	{
		    	Servico exams = null;
		   		String sql = "SELECT * FROM VWSERVICO where servico = ? or id_servico = ?";
		   		try {
		   			con = Conexao.getConexao(); 
		   			PreparedStatement preparador = con.prepareStatement(sql);
		   			 preparador.setString(1, termo); 
		   			 preparador.setString(2, termo);
		   			 ResultSet rs = preparador.executeQuery();
		   			 if(rs.next())
		   			 {
		   				 exams = new Servico();
		   				 exams.setId_servico(rs.getInt("id_servico"));
		   				 exams.setServico(rs.getString("servico"));
		   				 exams.setDescricao(rs.getString("descricao"));
		   				// exams.setTipo_de_servico(rs.getString("tipo_de_servico"));
		   				 exams.setPreco(rs.getDouble("preco"));
		   				 exams.setTaxa(rs.getDouble("taxa"));
		   				 exams.setImposto(rs.getDouble("taxa_percentual"));
		   				 exams.setSubTotal(rs.getDouble("sub_total"));
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
		   		return exams;
		   	}
		 
		 //Para listas todos servicos de qualidade
		 public List<Servico> listarServicoQualidade()
		   	{
		    	List<Servico> todosServicos = new ArrayList<Servico>(); 
		   		String sqlServicos = "SELECT id_servico_tq,serv_teste_qualidade,preco,descricao_tq,tx.tipo_de_taxa, tx.taxa, ctq.categoria_tq FROM tbltaxas tx, tblcategoriatestequalidade ctq, tblservicosdetestedequalidade stq where stq.FK_taxa=tx.id_taxa and stq.FK_categoria_tq=ctq.id_categoria_tq order by id_servico_tq desc";
		   		try {
		   			con = Conexao.getConexao(); 
		   			PreparedStatement preparador = con.prepareStatement(sqlServicos);
		   			 ResultSet rs = preparador.executeQuery();
		   			 while(rs.next())
		   			 {
		   			
		   				Servico servicosQual = new Servico();
		   				servicosQual.setId_servico_tq(rs.getInt("id_servico_tq"));
		   				servicosQual.setTipo_de_taxa(rs.getString("tipo_de_taxa"));
		   				servicosQual.setTaxa(rs.getDouble("taxa"));
		   				servicosQual.setCategoria_tq(rs.getString("categoria_tq"));
		   				servicosQual.setServ_teste_qualidade(rs.getString("serv_teste_qualidade"));
		   				servicosQual.setPreco(rs.getDouble("preco"));
		   				servicosQual.setDescricao_tq(rs.getString("descricao_tq"));
		   				todosServicos.add(servicosQual);
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
		   		return todosServicos;
		   	}
		 
		//Para listas todos servicos de qualidade em funcao do nome do serviço
		 public List<Servico> procurarServoQual(String nomeServico)
		   	{
		    	List<Servico> todosServicos = new ArrayList<Servico>(); 
		   		String sqlServicos = "SELECT id_servico_tq,serv_teste_qualidade,preco,descricao_tq,tx.taxa FROM tbltaxas tx, tblservicosdetestedequalidade stq where stq.FK_taxa=tx.id_taxa and serv_teste_qualidade like ?";
		   		try {
		   			con = Conexao.getConexao(); 
		   			PreparedStatement preparador = con.prepareStatement(sqlServicos);
		   			preparador.setString(1, nomeServico);
		   			ResultSet rs = preparador.executeQuery();
		
		   			 while(rs.next())
		   			 {
		   			
		   				Servico servicosQual = new Servico();
		   				servicosQual.setId_servico_tq(rs.getInt("id_servico_tq"));
		   				servicosQual.setTaxa(rs.getDouble("taxa"));
		   				servicosQual.setServ_teste_qualidade(rs.getString("serv_teste_qualidade"));
		   				servicosQual.setPreco(rs.getDouble("preco"));
		   				servicosQual.setDescricao_tq(rs.getString("descricao_tq"));
		   				servicosQual.setTaxaEmkz(servicosQual.getTaxa());
		   				servicosQual.setSubTotal(rs.getDouble("preco"));
		   				todosServicos.add(servicosQual);
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
		   		return todosServicos;
		   	}
		 
		 //procurar servico qualidade autocomplit
		//Para listas todos servicos de qualidade em funcao do nome do serviço
		 public List<Servico> procurarNomeServoQual(String nomeServico)
		   	{
		    	List<Servico> todosServicos = new ArrayList<Servico>(); 
		   		String sqlServicos = "SELECT id_servico_tq,serv_teste_qualidade FROM tblservicosdetestedequalidade where serv_teste_qualidade like ?";
		   		try {
		   			con = Conexao.getConexao(); 
		   			PreparedStatement preparador = con.prepareStatement(sqlServicos);
		   			preparador.setString(1, "%"+nomeServico+"%"); 
		   			ResultSet rs = preparador.executeQuery();
		
		   			 while(rs.next())
		   			 {
		   			
		   				Servico servicosQual = new Servico();
		   				servicosQual.setId_servico_tq(rs.getInt("id_servico_tq"));
		   				servicosQual.setServ_teste_qualidade(rs.getString("serv_teste_qualidade"));
		   				todosServicos.add(servicosQual);
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
		   		return todosServicos;
		   	}
		 
		 
		//3-Eliminar Serviï¿½o
			public void eliminarServicoQualid (int id)
			{
				String sql = "DELETE FROM tblservicosdetestedequalidade  WHERE id_servico_tq=?";
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

		 

		//Para listas todos servicos de qualidade
		 public List<Servico> verUmServicoQualidade(int cod)
		   	{
		    	List<Servico> todosServicos = new ArrayList<Servico>(); 
		   		String sqlServicos = "SELECT FK_taxa,FK_categoria_tq,id_servico_tq,serv_teste_qualidade,preco,descricao_tq,tx.tipo_de_taxa, ctq.categoria_tq FROM tbltaxas tx, tblcategoriatestequalidade ctq, tblservicosdetestedequalidade stq where stq.FK_taxa=tx.id_taxa and stq.FK_categoria_tq=ctq.id_categoria_tq and id_servico_tq = ?";
		   		try {     
		   			con = Conexao.getConexao(); 
		   			PreparedStatement preparador = con.prepareStatement(sqlServicos);
		   			preparador.setInt(1, cod);
		   			 ResultSet rs = preparador.executeQuery();
		   			 if(rs.next())
		   			 {
		   			
		   				Servico servicosQual = new Servico();
		   				servicosQual.setFK_taxa(rs.getInt("FK_taxa"));
		   				servicosQual.setFK_categoria_tq(rs.getInt("FK_categoria_tq"));
		   				servicosQual.setId_servico_tq(rs.getInt("id_servico_tq"));
		   				servicosQual.setTipo_de_taxa(rs.getString("tipo_de_taxa"));
		   				servicosQual.setCategoria_tq(rs.getString("categoria_tq"));
		   				servicosQual.setServ_teste_qualidade(rs.getString("serv_teste_qualidade"));
		   				servicosQual.setPreco(rs.getDouble("preco"));
		   				servicosQual.setDescricao_tq(rs.getString("descricao_tq"));
		   				todosServicos.add(servicosQual);
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
		   		return todosServicos;
		   	}

		 
		 
		 public List<Servico> buscarServicoExames(String aux){
			 String sql = "Select * from tblservicosdeexamesclinicos where analise_clinica Like ?";
			 List<Servico> lista = new ArrayList<Servico>();
			 try {
				con = Conexao.getConexao();
				PreparedStatement ps = con.prepareStatement(sql);
				ps.setString(1, aux+"%");
				ResultSet rs = ps.executeQuery();
				while(rs.next()){
					Servico exa = new Servico();
					exa.setServico(rs.getString("analise_clinica"));
					exa.setId_servico(rs.getInt("id_servicodeanalise_clinica"));
//					System.out.println("OKOKOKO");
					lista.add(exa);
				}
			} catch (Exception e) {
				e.printStackTrace();
			}
			 return lista;
		 }
		  
}
