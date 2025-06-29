package ao.co.cligest.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

import ao.co.cligest.entidades.Caixa;
import ao.co.cligest.entidades.ConfNomeFactura;
import ao.co.cligest.entidades.Diverso;
import ao.co.cligest.entidades.FacturaAtrasos;
import ao.co.cligest.entidades.Facturacao;
import ao.co.cligest.entidades.Isencao;
import ao.co.cligest.entidades.MenorIdade;
import ao.co.cligest.entidades.Paciente;
import ao.co.cligest.entidades.Produtos;
import ao.co.cligest.entidades.SelecionarBanco;
import ao.co.cligest.entidades.Servico;

public class FacturacaoDAO {
	
	private Connection con = null;
	private Formatando ft = new Formatando();
	//Lista Prioritaria para Factura de Consultas
	
	 public List<Paciente> factuaraConsultaConfirmada()
	 {
			List <Paciente> lista = new ArrayList<Paciente>();
			String sql = "SELECT * FROM vwconsultaconfirmadafaturando where data_confirmacao = curdate() "
					   + "and id_consulta_confirmada NOT IN(SELECT fk_consulta_confirmada FROM tblfacturaconsulta)"
					   + "and id_consulta_confirmada NOT IN(SELECT fk_consulta_confirmada FROM tblfacturademulticaxa)"
					   + "and id_consulta_confirmada NOT IN(SELECT fk_consulta_confirmada FROM tblfacturadeisencaoporidade)"
					   + "and id_consulta_confirmada NOT IN(SELECT fk_consulta_confirmada FROM tblfacturaconsultaisentada)"
					   + "and id_consulta_confirmada NOT IN(SELECT fk_confirmacao FROM tblfacturadesegurodesaude)"
					   + "and id_consulta_confirmada NOT IN(SELECT FK_confirmacao_agendamento FROM tblconsultacanselada) Limit 3 ";
			try {
				con = Conexao.getConexao();
				PreparedStatement preparador = con.prepareStatement(sql);
				 ResultSet rs = preparador.executeQuery();
				 while(rs.next())
				 {
					 Paciente fun = new Paciente();
					 fun.setId_consulta_confirmada(rs.getInt("id_consulta_confirmada"));
					 fun.setNome(rs.getString("nome"));
					 fun.setApelido(rs.getString("ultimo_nome"));
					 fun.setServico(rs.getString("servico"));
					 fun.setId_servico(rs.getInt("id_servico"));
					 fun.setFK_doutor(rs.getInt("fK_doutor"));
					 fun.setFK_entidade_paciente(rs.getInt("fK_entidade_paciente"));
//					 fun.setIsento(rs.getInt("isento"));
					 fun.setPreco(rs.getDouble("preco"));
					// fun.setFK_fornecedor(rs.getInt("fK_fornecedor"));
					// fun.setId_tipo_dcobertura(rs.getInt("fk_tipo_dcobertura"));
					 lista.add(fun);
				 }
				 preparador.close();
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
				 
			return lista;
		}
	 
	 public List<Paciente> factuaraConsultaConfirmadaNaIsensao(){
			List <Paciente> lista = new ArrayList<Paciente>();
			String sql = "SELECT * FROM vwconsultaconfirmadafaturando where data_confirmacao = curdate() AND idade > 16 AND id_consulta_confirmada NOT IN (SELECT fk_consulta_confirmada FROM tblfacturaconsulta) AND id_consulta_confirmada NOT IN (SELECT FK_con_confirmada FROM tblisencaoconsulta)";
			try {
				con = Conexao.getConexao();
				PreparedStatement preparador = con.prepareStatement(sql);
				 ResultSet rs = preparador.executeQuery();
				 while(rs.next())
				 {
					 Paciente fun = new Paciente();
					 fun.setId_consulta_confirmada(rs.getInt("id_consulta_confirmada"));
					 fun.setNome(rs.getString("nome"));
					 fun.setApelido(rs.getString("ultimo_nome"));
					 fun.setServico(rs.getString("servico"));
					 fun.setId_servico(rs.getInt("id_servico"));
					 fun.setFK_doutor(rs.getInt("fK_doutor"));
					 fun.setFK_entidade_paciente(rs.getInt("fK_entidade_paciente"));
//					 fun.setIsento(rs.getInt("isento"));
					 fun.setPreco(rs.getDouble("subtotal"));
					// fun.setFK_fornecedor(rs.getInt("fK_fornecedor"));
					// fun.setId_tipo_dcobertura(rs.getInt("fk_tipo_dcobertura"));
					 fun.setNumero_processo(rs.getString("numero_processo"));
					 fun.setTaxaIsencaoConsulta(rs.getFloat("taxa_emkz"));
					 
					 fun.setIdade(rs.getDate("data_nascimento"));
					 System.out.println("idade: "+fun.getIdade());
//					 Calendar data = Calendar.getInstance();
//					 data.setTime(rs.getDate("data"));
//					 fun.setData_registo(data);
					 
					 lista.add(fun);
				 }
				 preparador.close();
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
				 
			return lista;
		}
	 
	 // Lista de espera para factura de consultas
	 
	 public List<Paciente> factuaraListaEspera(){
			List <Paciente> lista = new ArrayList<Paciente>();
			String sql = "SELECT * FROM vwconsultaconfirmadafaturando WHERE data_confirmacao = curdate() "
					   + "and id_consulta_confirmada NOT IN(SELECT fk_consulta_confirmada FROM tblfacturaconsulta) "
					   + "and id_consulta_confirmada NOT IN(SELECT fk_consulta_confirmada FROM tblfacturademulticaxa) "
					   + "and id_consulta_confirmada NOT IN(SELECT fk_consulta_confirmada FROM tblfacturadeisencaoporidade) "
					   + "and id_consulta_confirmada NOT IN(SELECT fk_consulta_confirmada FROM tblfacturaconsultaisentada) "
					   + "and id_consulta_confirmada NOT IN(SELECT fk_confirmacao FROM tblfacturadesegurodesaude)"
					   + "and id_consulta_confirmada NOT IN(SELECT FK_confirmacao_agendamento FROM tblconsultacanselada) LIMIT 100 OFFSET 3";
			try {
				con = Conexao.getConexao();
				PreparedStatement preparador = con.prepareStatement(sql);
				 ResultSet rs = preparador.executeQuery();
				 while(rs.next())
				 {
					 Paciente fun = new Paciente();
					 fun.setId_consulta_confirmada(rs.getInt("id_consulta_confirmada"));
					 fun.setNome(rs.getString("nome"));
					 fun.setApelido(rs.getString("ultimo_nome"));
					 fun.setServico(rs.getString("servico"));
					 fun.setPreco(rs.getDouble("preco"));
					 lista.add(fun);
				 }
				 preparador.close();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			catch (Exception e) {
				// TODO: handle exception
			}finally{
						try{
							con.close();
						}
						catch(SQLException ef){
							System.out.println("Erro finalizar: "+ef);
						}
				}
				 
			return lista;
		}
	 
	 public List<Paciente> ListaCancelados(String nome){
		   List <Paciente> lista = new ArrayList<Paciente>();
		   String sql = "SELECT * FROM vwconsultascanceladas WHERE NomeCompleto LIKE ? OR NumeroProcesso LIKE ? order by data desc limit 500";
		   try {
		    con = Conexao.getConexao();
		    PreparedStatement preparador = con.prepareStatement(sql);
		    preparador.setString(1, nome);
		    preparador.setString(2, nome);
		     ResultSet rs = preparador.executeQuery();
		     while(rs.next())
		     {
		      Paciente fun = new Paciente();
		      fun.setNumero_processo(rs.getString("NumeroProcesso"));
		      fun.setFK_consulta_confirmada(rs.getInt("id_consulta_canselada"));
		      fun.setNome_paciente(rs.getString("NomeCompleto"));
		      fun.setServico(rs.getString("servico"));
		      fun.setNomeDoc(rs.getString("nome_funcionario"));
		      fun.setPreco(rs.getDouble("precotaxado"));
		      Calendar data = Calendar.getInstance();
		         data.setTime(rs.getDate("data"));
		         fun.setData_registo(data);
		         fun.setData(rs.getDate("data"));
		      fun.setHora_registo(rs.getString("hora"));
		      lista.add(fun);
		     }
		     preparador.close();
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
		     
		   return lista;
		  }
	 
	 public List<Paciente> ListaCancelados()
	  {
	   List <Paciente> lista = new ArrayList<Paciente>();
	   String sql = "SELECT * FROM vwconsultascanceladas order by data desc limit 500";
	   try {
	    con = Conexao.getConexao();
	    PreparedStatement preparador = con.prepareStatement(sql);
	     ResultSet rs = preparador.executeQuery();
	     while(rs.next())
	     {
	      Paciente fun = new Paciente();
	      fun.setNumero_processo(rs.getString("NumeroProcesso"));
	      fun.setFK_consulta_confirmada(rs.getInt("id_consulta_canselada"));
	      fun.setNome_paciente(rs.getString("NomeCompleto"));
	      fun.setServico(rs.getString("servico"));
	      fun.setNomeDoc(rs.getString("nome_funcionario"));
	      fun.setPreco(rs.getDouble("precotaxado"));
	      Calendar data = Calendar.getInstance();
	         data.setTime(rs.getDate("data"));
	         fun.setData_registo(data);
	         fun.setData(rs.getDate("data"));
	      fun.setHora_registo(rs.getString("hora"));
	      lista.add(fun);
	     }
	     preparador.close();
	   } catch (SQLException e) 
	   {
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
	     
	   return lista;
	  }
	 
	 public Paciente getCancelado(int cod){
		 	Paciente fun = new Paciente();
			String sql = "SELECT * FROM vwconsultascanceladas where id_consulta_canselada = ?";
			try {
				con = Conexao.getConexao();
				PreparedStatement preparador = con.prepareStatement(sql);
				preparador.setInt(1, cod);
				 ResultSet rs = preparador.executeQuery();
				 if(rs.next())
				 {
					 fun.setFK_consulta_confirmada(rs.getInt("id_consulta_canselada"));
					 fun.setNome_paciente(rs.getString("NomeCompleto"));
					 fun.setServico(rs.getString("servico"));
					 fun.setHora_registo(rs.getString("hora"));
					 fun.setNome(rs.getString("nome_funcionario"));
					 fun.setDescricao(rs.getString("descricao_do_canselamento"));
					 fun.setIdade(rs.getDate("data_nascimento"));
					 Calendar data = Calendar.getInstance();
					 data.setTime(rs.getDate("data"));
					 fun.setData_registo(data);
					 if(rs.getInt("fk_genero")==1)
						 fun.setNomegenero("Masculino");
					 else
						 fun.setNomegenero("Feminino");
				 }
				 preparador.close();
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
				 
			return fun;
		}
	 
	 public void anularCancelado(int cod){
		 	Paciente fun = new Paciente();
			String sql = "DELETE FROM tblconsultacanselada where id_consulta_canselada = ?";
			try {
				con = Conexao.getConexao();
				PreparedStatement exec = con.prepareStatement(sql);
				exec.setInt(1, cod);
				exec.execute();
				System.out.println("RESTAURADO");
				exec.close();
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
	 	 
	 
	// Config Nome da Factura
	 
	 public List<ConfNomeFactura> configFacturaMostrar ()
		{
			List <ConfNomeFactura> listaMostrar = new ArrayList<ConfNomeFactura>();
			String sql = "SELECT * FROM tblconfigurarnomefactura LIMIT 0,1";
			try {
				con = Conexao.getConexao();
				 PreparedStatement preparador = con.prepareStatement(sql);
				 ResultSet menor = preparador.executeQuery();
				 while(menor.next())
				 {
					 ConfNomeFactura fun = new ConfNomeFactura();  
					 fun.setId_nome_factura(menor.getInt("id_nome_factura"));				
					 fun.setConfig_nome_factura(menor.getString("config_nome_factura"));
					 listaMostrar.add(fun);
				 }
				 preparador.close();
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
				 
			return listaMostrar;
		}
	 
	 public String nomeFactaura ()
		{
		    String nomeFactaura = ""; 
			String sql = "SELECT * FROM tblconfigurarnomefactura LIMIT 0,1";
			try {
				con = Conexao.getConexao();
				 PreparedStatement preparador = con.prepareStatement(sql);
				 ResultSet rs = preparador.executeQuery();
				 if(rs.next())
				 {		
					 nomeFactaura = rs.getString("config_nome_factura")+"V";  
				 }
				 preparador.close();
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
				 
			return nomeFactaura;
		}
	 
	// Config Condi��o paga ou n�o		 
		 
	public MenorIdade configMenorIdadeMostrar() {
		MenorIdade pac = null;
		String sql = "SELECT * FROM tblconfigurarmenor";
		try {
			con = Conexao.getConexao();
			PreparedStatement preparador = con.prepareStatement(sql);
			ResultSet rs = preparador.executeQuery();
			if (rs.next()) {
				pac = new MenorIdade();
				pac.setId_configurar_menor(rs.getInt("id_configurar_menor"));
				pac.setPaga_maxima(rs.getInt("Paga_maxima"));
				pac.setIdade(rs.getInt("idade_maxima"));

			}
			preparador.close();
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

		return pac;
	}
	 
	 
	// Seleccionar banco
	 
		 public List<SelecionarBanco> bancoSelecao ()
			{
				List <SelecionarBanco> listaMostrar = new ArrayList<SelecionarBanco>();
				String sql = "SELECT * FROM tblbanco";
				try {
					con = Conexao.getConexao();
					 PreparedStatement preparador = con.prepareStatement(sql);
					 ResultSet menor = preparador.executeQuery();
					 while(menor.next())
					 {
						 SelecionarBanco fun = new SelecionarBanco();  
						 fun.setId_banco(menor.getInt("id_banco"));				
						 fun.setBanco(menor.getString("banco"));
						 listaMostrar.add(fun);
					 }
					 preparador.close();
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
			 
				return listaMostrar;
			}
		
		 

	 // Numero de Factura
	 
		public int numerofactura ()
		{ 
			
			int fun = 0;
			String sql = "SELECT count(numero_factura) ID FROM tblfactura";
			try {
				con = Conexao.getConexao();
				PreparedStatement preparador = con.prepareStatement(sql);
				ResultSet rs = preparador.executeQuery();
				
				if(rs.next())
					fun = rs.getInt("ID");
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
				 
				return fun+1;  
			
		}
		
		public int NumFacturaConvenio ()
		{ 
			
			int fun = 0;
			String sql = "SELECT MAX(id_criar_factura_convenio) ID FROM tblcriarfacturaconvenio";
			try {
				con = Conexao.getConexao();
				PreparedStatement ps = con.prepareStatement(sql);
				ResultSet rs = ps.executeQuery();
				if(rs.next())
					fun = rs.getInt("ID");
			}
			catch(Exception e){e.printStackTrace();}
				
			return fun+1;  
			
		}
		
		public int NumFacturaPagaConvenio ()
		{ 
			
			int fun = 0;
			String sql = "SELECT MAX(id_facturas_pagas_convenio) ID FROM tblfacturaspagasconvenio";
			try {
				con = Conexao.getConexao();
				PreparedStatement ps = con.prepareStatement(sql);
				ResultSet rs = ps.executeQuery();
				if(rs.next())
					fun = rs.getInt("ID");
			}
			catch(Exception e){e.printStackTrace();}
				
			return fun+1;  
			
		}
		public boolean PacienteConsultaConvenio(String idConfirma){
			String sql = "SELECT * FROM tblconsultaconfirmada where  id_consulta_confirmada = ?";
			
			try {
				 con = Conexao.getConexao();
				 PreparedStatement ps = con.prepareStatement(sql);
				 ps.setString(1, idConfirma);
				 ResultSet rs = ps.executeQuery();
				 if(rs.next())
					 if(rs.getInt("FK_fornecedor")!=0)
						 return true;
				 
			ps.close();
			con.close();
			} catch (Exception e) {
				e.printStackTrace();
			}
			return false;
		}
		public Paciente buscarpacientefactura (String num)
		  {
			Paciente pac = null;  
		 String sql = "SELECT * FROM vwconsultaconfirmadafaturando where  id_consulta_confirmada LIKE ?" ;
		 try {
			 con = Conexao.getConexao();
		   PreparedStatement preparador = con.prepareStatement(sql);
		   preparador.setString(1, num);
		   ResultSet rs = preparador.executeQuery();
		   if(rs.next())
		   {
			 pac = new Paciente();
			 pac.setFK_entidade_paciente(rs.getInt("FK_entidade_paciente"));
		 	 pac.setNumero_processo(rs.getString("numero_processo"));
		 	 pac.setNome(rs.getString("nome"));
		 	 pac.setNomem(rs.getString("nome_meio"));
		 	 pac.setApelido(rs.getString("ultimo_nome"));
		     pac.setServico(rs.getString("servico")); 
		     pac.setFK_servico(rs.getInt("id_servico"));
		     Calendar data = Calendar.getInstance();
			 data.setTime(rs.getDate("data_nascimento"));
			 pac.setDataNasc(data);
			 pac.setIdade(rs.getDate("data_nascimento"));	     
		     pac.setPreco(rs.getDouble("preco"));
		     pac.setPrecoUnitario(rs.getDouble("preco_unitario"));
		     pac.setTaxaEmkz(rs.getDouble("taxa_emkz"));
		     pac.setTelefone(rs.getLong("telefone"));
		     //pac.setNumero_dcartao(rs.getString("numero_dcartao"));
//		     pac.setEmpresa(rs.getString("empresa"));
//		     pac.setFK_fornecedor(rs.getInt("fk_fornecedor"));
//		     pac.setId_tipo_dcobertura(rs.getInt("fk_tipo_dcobertura"));
		     pac.setFK_consulta_confirmada(rs.getInt("id_consulta_confirmada"));
		     pac.setFK_consulta_marcada(rs.getInt("FK_consulta_marcada"));
		 	 
		   }
		   preparador.close();
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
				 
		 return pac;
		}

		public Servico getConsultasFaturando(String cod){
			String sql = "select * from vwconsultaconfirmada where id_consulta_confirmada = ?";
			Servico consFaturar = new Servico();
			try{
				con = Conexao.getConexao();
				PreparedStatement ps = con.prepareStatement(sql);
				ps.setString(1, cod);
				ResultSet rs = ps.executeQuery();
				if (rs.next()) {
					 
				     consFaturar.setServico(rs.getString("servico")); 
				     consFaturar.setId_servico(rs.getInt("id_servico"));		 	     
				     consFaturar.setPreco(rs.getDouble("preco"));
				     consFaturar.setTaxa(rs.getFloat("taxa")); 
				     consFaturar.setTaxaEmkz(rs.getDouble("taxa_emkz"));
				     consFaturar.setPreco_unitario(rs.getDouble("preco_unitario"));;
				     consFaturar.setId(rs.getInt("id_consulta_confirmada"));
					
				}
			}catch(Exception e){
				e.printStackTrace();
			}
			return consFaturar;
		}
		public Paciente modPagamento (String num)
		  {
			Paciente pac = null;  
		 String sql = "SELECT * FROM vwplanodesaude where  FK_paciente LIKE ?" ;
		 try {
			 con = Conexao.getConexao();
		   PreparedStatement preparador = con.prepareStatement(sql);
		   preparador.setString(1, num);
		   ResultSet rs = preparador.executeQuery();
		   if(rs.next())
		   {
			 pac = new Paciente();
			 pac.setFK_entidade_paciente(rs.getInt("FK_paciente"));
		     pac.setNumero_dcartao(rs.getString("numero_dcartao"));
		     pac.setEmpresa(rs.getString("empresa"));
		     pac.setIsento(rs.getInt("isento"));
		     pac.setFK_fornecedor(rs.getInt("fk_fornecedor"));
		     pac.setId_tipo_dcobertura(rs.getInt("fk_tipo_dcobertura"));
		 	 
		   }
		   preparador.close();
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
				 
		 return pac;
		}

		public Paciente getIsentoConsulta (String num)
		  {
			Paciente pac = new Paciente();  
			String sql = "SELECT * FROM tblisencaoconsulta ic,tblisencaoprincipal ip where  ic.FK_con_confirmada LIKE ? and ic.FK_isencao = ip.id_isencao" ;
			 try {
				 con = Conexao.getConexao();
			   PreparedStatement preparador = con.prepareStatement(sql);
			   preparador.setString(1, num);
			   ResultSet rs = preparador.executeQuery();
			   if(rs.next())
			   {
				 pac.setId_isencao(rs.getInt("FK_paciente"));		 	 
			   }
			   preparador.close();
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
			 
			 return pac;
		}

		
		//Inserir dados na tabela facturacao 
		public int inserir_faturacao  (Facturacao fun)
		{
			int ultimoCod = 0;
		    String sql = "INSERT INTO tblfactura (numero_factura,FK_funcionario,Data_da_facturacao,hora_da_facturacao,FK_abertura_dcaixa,FK_metodo_de_pagameto,FK_paciente,data_doatendimento,FK_tipo_deservico,total_factura) VALUES (?,?,?,?,?,?,?,?,?,?)";
		    try {
				 
				 //1-tblfactura
		    	 con = Conexao.getConexao();
				 PreparedStatement cid = con.prepareStatement(sql);
				 cid.setString(1, fun.getNumero_factura());
				 cid.setInt(2, fun.getFK_funcionario());
				 cid.setDate(3, new Formatando().data_registo());
				 cid.setString(4, new Formatando().getHoraAtual());	
				 cid.setInt(5, fun.getFK_aberturadecaixa());
				 cid.setInt(6, fun.getFK_Metodo_dpagamento());
				 cid.setInt(7, fun.getFK_paciente());
				 cid.setDate(8	, new Formatando().data_registo());
				 cid.setInt(9, fun.getFK_tipo_deservico());
				 cid.setDouble(10, fun.getTotal_factura());
				 cid.execute();
				 
				 ResultSet rs = cid.executeQuery("SELECT LAST_INSERT_ID()");
				 if(rs.next()){
					ultimoCod = rs.getInt(1); 
				 }
				 cid.close();
				 System.out.println("Cadastrado com sucesso...tblfactura"); 
			} catch (SQLException e) {
				// TODO Auto-generated catch block
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
				 
		    
		    return ultimoCod;
		}
		
		
		// factura chequer.
	    public int XequeFactura(String termo)
	    {
	     int ok = 0;
	     String sql = "SELECT * FROM tblfactura WHERE numero_factura like ?";
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
		
		//Inserir dados na tabela facturacao 
				public int inserir_faturacaot  (Facturacao fun)
				{
					int ultimoCod = 0;
				    String sql = "INSERT INTO tblfactura (numero_factura,FK_funcionario,Data_da_facturacao,hora_da_facturacao,FK_abertura_dcaixa,FK_metodo_de_pagameto,FK_paciente,data_doatendimento,FK_tipo_deservico,total_factura,FK_fornecedor) VALUES (?,?,?,?,?,?,?,?,?,?,?)";
				    try {
						 
						 //1-tblfactura
				    	 con = Conexao.getConexao();
						 PreparedStatement cid = con.prepareStatement(sql);
						 cid.setString(1, fun.getNumero_factura());
						 cid.setInt(2, fun.getFK_funcionario());
						 cid.setDate(3, new Formatando().data_registo());
						 cid.setString(4, new Formatando().getHoraAtual());	
						 cid.setInt(5, fun.getFK_aberturadecaixa());
						 cid.setInt(6, fun.getFK_Metodo_dpagamento());
						 cid.setInt(7, fun.getFK_paciente());
						 cid.setDate(8	, new Formatando().data_registo());
						 cid.setInt(9, fun.getFK_tipo_deservico());
						 cid.setDouble(10, fun.getTotal_factura());
						 cid.setInt(11, fun.getFK_fornecedor());
						 cid.execute();
						 
						 ResultSet rs = cid.executeQuery("SELECT LAST_INSERT_ID()");
						 if(rs.next()){
							ultimoCod = rs.getInt(1); 
						 }
						 cid.close();
						 System.out.println("Cadastrado com sucesso...tblfactura"); 
					} catch (SQLException e) {
						// TODO Auto-generated catch block
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
						 
				    
				    return ultimoCod;
				}
		
		//Inserir dados na tabela facturacaomulticaixa 
		public void inserir_faturademulticaixa  (Facturacao fun)
		{
			String sql = "INSERT INTO tblfacturademulticaxa (FK_paciente,FK_factura,FK_consulta_confirmada,FK_servico,preco,quantidade,taxa_emkz,total_pago,FK_banco,numero_cartao) VALUES (?,?,?,?,?,?,?,?,?,?)";
		    try {
				 
				 //1-tblfacturademulticaixa
		    	con = Conexao.getConexao();
				 PreparedStatement cid = con.prepareStatement(sql);
				 cid.setInt(1, fun.getFK_paciente());
				 cid.setInt(2, fun.getFK_factura());
				 cid.setInt(3, fun.getFK_confirmacao_do_agendamento());
				 cid.setInt(4, fun.getFK_servico());
				 cid.setDouble(5, fun.getPreco());
				 cid.setInt(6, fun.getQuantidade());
				 cid.setDouble(7, fun.getTaxa_emkz());
				 cid.setDouble(8, fun.getTotais());
				 cid.setInt(9, fun.getFK_banco());
				 cid.setInt(10, fun.getNumero_cartao());
				 cid.execute();
				 cid.close();
				 System.out.println("Cadastrado com sucesso...tblfacturademulticaixa"); 
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
		
		public void inserir_faturadeSeguro  (Facturacao f) throws SQLException{
			String sql = "INSERT INTO tblfacturadesegurodesaude (FK_factura,FK_paciente,total,FK_aseguradora,FK_tipo_dcobertura,FK_plano_dsaude) VALUE (?,?,?,?,?,?)";
			try {
				con = Conexao.getConexao();
				PreparedStatement ps = con.prepareStatement(sql);
				ps.setInt(1, f.getFK_factura());
				ps.setInt(2, f.getFK_paciente());
				ps.setDouble(3, f.getTotal_factura());
				ps.setInt(4, f.getFK_aseguradora());
				ps.setInt(5, f.getFK_tipo_dcobertura());
				ps.setInt(6, f.getFK_plano_dsaude());
				ps.execute();
				ps.close();
				con.close();
			} catch (Exception e) {
				e.printStackTrace();
				con.rollback();
			}
		}
		
		
		public void inserir_faturadeSeguroSGerais  (Facturacao f) throws SQLException{
			String sql = "INSERT INTO tblfacturadeseguroservicosgerais "
					+ " (FK_factura,FK_servico,sg_seguro_preco,sg_seguro_qtd,sg_seguro_taxa_perc,sg_seguro_taxa_emkz,sg_seguro_total) "
					+ " VALUE (?,?,?,?,?,?,?)";
			try {
				con = Conexao.getConexao();
				PreparedStatement ps = con.prepareStatement(sql);
				ps.setInt(1, f.getFK_factura());
				ps.setInt(2, f.getFK_servico());
				ps.setDouble(3, f.getSg_seguro_preco());
				ps.setInt(4, f.getSg_seguro_qtd());
				ps.setDouble(5, f.getSg_seguro_taxa_perc());
				ps.setDouble(6, f.getSg_seguro_taxa_emkz());
				ps.setDouble(7, f.getSg_seguro_total());
				ps.execute();
				ps.close();
				con.close();
				System.out.println("SALVO");
			} catch (Exception e) {
				e.printStackTrace();
				con.rollback();
			}
		}
		
		public void inserir_faturadeSeguroSExames  (Facturacao f)  {
			String sql = "INSERT INTO tblfacturadeseguroexames (FK_factura,FK_exame,ex_seguro_preco,ex_seguro_qtd,ex_seguro_taxa_perc,ex_seguro_taxa_emkz,ex_seguro_total,FK_requisicao_dexame) VALUE (?,?,?,?,?,?,?,?)";
			try {
				con = Conexao.getConexao();
				PreparedStatement ps = con.prepareStatement(sql);
				ps.setInt(1, f.getFK_factura());
				ps.setInt(2, f.getFK_servico());
				ps.setDouble(3, f.getEx_seguro_preco());
				ps.setInt(4, f.getEx_seguro_qtd());
				ps.setDouble(5, f.getEx_seguro_taxa_perc());
				ps.setDouble(6, f.getEx_seguro_taxa_emkz());
				ps.setDouble(7, f.getEx_seguro_total());
				ps.setDouble(8, f.getFK_requisicao_dexame());
				ps.execute();
				ps.close();
				con.close();
				System.out.println("SALVO");
			} catch (Exception e) {
				e.printStackTrace();
				 
			}
		}
		
		// fatura��o exame multicaixa cussunga
		public void inserir_faturadeMulticaixaExame(Facturacao fun)
		{
			
		    String sql = "INSERT INTO tblfacturademulticaixaexames (fk_factura, fk_servico, fac_exame_mcaixa_preco, fac_exame_mcaixa_taxa_perc, fac_exame_mcaixa_taxa_emkz, fac_exame_mcaixa_total, FK_banco, numero_cartao,fk_requisicao_dexame) VALUES (?,?,?,?,?,?,?,?,?)";
		    try {
				 
				 //1-tblfacturademulticaixa
		    	 con = Conexao.getConexao();
				 PreparedStatement cid = con.prepareStatement(sql);
				 cid.setInt(1, fun.getFK_factura());
				 cid.setInt(2, fun.getFK_servico_exame());			 
				 cid.setDouble(3, fun.getFac_exame_mcaixa_preco());
				 cid.setDouble(4, fun.getFac_exame_mcaixa_taxa_perc());
				 cid.setDouble(5, fun.getFac_exame_mcaixa_taxa_emkz());
				 cid.setDouble(6, fun.getFac_exame_mcaixa_total());
				 cid.setInt(7, fun.getFK_banco());
				 cid.setInt(8, fun.getNumero_cartao());
				 cid.setInt(9, fun.getFK_requisicao_dexame());
				 cid.execute();
				 cid.close();
				 System.out.println("Cadastrado com sucesso...tblfacturademulticaixaexames"); 
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
		
		// fatura��o exame isencao cussunga
				public void inserir_faturadeIsencaoPorIdadeExame(Facturacao fun)
				{
					
				    String sql = "INSERT INTO tblfacturaisencaoporidadeexame (fk_factura, fk_servico, isen_idade_exame_preco, isen_idade_exame_taxa_perc, isen_idade_exame_taxal_emkz, isen_idade_exame_total_pago, fk_fac_isentada) VALUES (?,?,?,?,?,?,?)";
				    try {
						 
						 //1-tblfacturademulticaixa
				    	con = Conexao.getConexao();
						 PreparedStatement cid = con.prepareStatement(sql);
						 cid.setInt(1, fun.getFK_factura());
						 cid.setInt(2, fun.getFK_servico_exame());			 
						 cid.setDouble(3, fun.getFac_exame_mcaixa_preco());
						 cid.setDouble(4, fun.getFac_exame_mcaixa_taxa_perc());
						 cid.setDouble(5, fun.getFac_exame_mcaixa_taxa_emkz());
						 cid.setDouble(6, fun.getFac_exame_mcaixa_total());
						 cid.setInt(7, fun.getFK_fac_isentada());
						 cid.execute();
						 cid.close();
						 System.out.println("Cadastrado com sucesso...tblfacturaisencaoporidadeexame"); 
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
		
		public void inserir_faturaconsulta  (Facturacao fun)
		{
			 
		    String sql = "INSERT INTO tblfacturaconsulta (FK_factura,total_pago,FK_serv_consulta,FK_paciente,FK_consulta_confirmada) VALUES (?,?,?,?,?)";
		    try {

		    	 con = Conexao.getConexao();
				 PreparedStatement cid = con.prepareStatement(sql);
				 cid.setInt(1, fun.getFK_factura());
				 cid.setDouble(2, fun.getTotais());
				 cid.setInt(3, fun.getFK_servico());
				 cid.setInt(4, fun.getFK_paciente());
				 cid.setInt(5, fun.getFK_confirmacao_do_agendamento());
				 cid.execute();
				 cid.close();
				 con.close();
				 System.out.println("Cadastrado com sucesso...tblfacturaconsulta"); 
			} catch (SQLException e) {
				 e.printStackTrace();
			}
		 
				 
		}
		
		
		public void inserir_faturaconsultaisentada(Facturacao fun)
		{
			
		    String sql = "INSERT INTO tblfacturaconsultaisentada (FK_factura,FK_consulta_confirmada,FK_fac_isentada,FK_servico,preco,quantidade,taxa_porcento,tot_tax_emkz,total_isentado) VALUES (?,?,?,?,?,?,?,?,?)";
		    try {
				 
		    	con = Conexao.getConexao();
				 PreparedStatement cid = con.prepareStatement(sql);
				 cid.setInt(1, fun.getFK_factura());
				 cid.setInt(2, fun.getFK_confirmacao_do_agendamento());
				 cid.setInt(3, fun.getFK_fac_isentada());
				 cid.setInt(4, fun.getFK_servico());
				 cid.setDouble(5, fun.getPreco());
				 cid.setInt(6, fun.getQuantidade());
				 cid.setDouble(7, fun.getTaxa_percentual());
				 cid.setDouble(8, fun.getTaxa_emkz());
				 cid.setDouble(9, fun.getTotais());
				 cid.execute();
				 cid.close();
				 System.out.println("Cadastrado com sucesso...tblfacturaconsulta"); 
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
		
		public void inserir_faturaExameIsentada(Facturacao fun)
		{
			String sql = "INSERT INTO tblfacturaexameisentado (FK_fac_isentada,FK_factura,FK_serv_exame,taxa_porcento,tot_tax_emkz,total_isentado,fk_requisicao_dexame,fac_exa_isen_preco,fac_exa_isen_qtd) VALUES (?,?,?,?,?,?,?,?,?)";
		    try {
		    		con = Conexao.getConexao();
		    		PreparedStatement cid = con.prepareStatement(sql);
		    		cid.setInt(1, fun.getFK_fac_isentada());
		    		cid.setInt(2, fun.getFK_factura());
		    		cid.setInt(3, fun.getFK_servico_exame());
		    		cid.setDouble(4, fun.getTaxa_porcento());
		    		cid.setDouble(5, fun.getTot_tax_emkz());
		    		cid.setDouble(6, fun.getTotal_isentado());
		    		cid.setInt(7, fun.getFK_requisicao_dexame());
		    		cid.setDouble(8, fun.getPreco());
		    		cid.setInt(9, fun.getQuantidade());
		    		cid.execute();
		    		cid.close();
		    		System.out.println("Cadastrado com sucesso...tblExameIsentado"); 
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
		
		//
		//------------------------------------------------------------------------------------------
		public void inserirfacturaisencaoporidadeexame(Facturacao fun)
		{
			String sql = "INSERT INTO tblfacturaisencaoporidadeexame (fk_factura,fk_servico,isen_idade_exame_preco,isen_idade_exame_taxa_perc,isen_idade_exame_taxal_emkz,isen_idade_exame_total_pago,fk_fac_isentada,fk_requisicao_dexame) VALUES (?,?,?,?,?,?,?,?)";
		    try {
		    		con = Conexao.getConexao();
					PreparedStatement cid = con.prepareStatement(sql);
					//cid.setInt(1, fun.getFK_fac_isentada());
					cid.setInt(1, fun.getFK_factura());
					cid.setInt(2, fun.getFK_servico());	
					cid.setDouble(3, fun.getIsen_idade_s_g_preco());
					cid.setDouble(4, fun.getIsen_idade_s_g_taxa_perc());
					cid.setDouble(5, fun.getIsen_idade_s_g_taxal_emkz());
					cid.setDouble(6, fun.getIsen_idade_s_g_total_pago());
					cid.setInt(7, fun.getFK_fac_isentada());
					cid.setInt(8, fun.getFK_requisicao_dexame());
					cid.execute();
					cid.close();
			 
		    		System.out.println("Cadastrado com sucesso...tblfacturaisencaoexameporidade"); 
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
		//
		//--------------------------------------------------------------------------------------------
		
		
		
		
		public void inserir_faturaexame  (Facturacao fun)
		{
			
		    String sql = "INSERT INTO tblexamerequisitado (identificacao_unica_do_exame,data,FK_funcionario,FK_paciente,FK_servico_exame,FK_tipo_dcobertura,gestante_pra_exame,hora) VALUES (?,?,?,?,?,?,?,?)";
		    try {
				 
				 //1-tblfacturademulticaixa
		    	con = Conexao.getConexao();
				 PreparedStatement cid = con.prepareStatement(sql);
				 cid.setInt(1, fun.getIdentificacao_unica_do_exame());
				 cid.setDate(2, new Formatando().data_registo());
				 cid.setInt(3, fun.getFK_funcionario());
				 cid.setInt(4, fun.getFK_paciente());
				 cid.setInt(5, fun.getFK_servico_exame());
				 cid.setInt(6, fun.getFK_tipo_dcobertura());
				 cid.setInt(7, fun.getGestante_pra_exame());
				 cid.setString(8, new Formatando().getHoraAtual());
				 cid.execute();
				 cid.close();
				 System.out.println("Cadastrado com sucesso...tblfacturaexames"); 
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
		
//	    Inserir dados na tabela facturadeisensao
		
		public int inserir_faturadeisensao  (Facturacao fun)
		{
			int ultimoCod = 0;
		    String sql = "INSERT INTO tblfacturadeisencao (FK_factura,numero_factura,FK_paciente,FK_servico,preco,total) VALUES (?,?,?,?,?,?)";
		    try {
				 
				 //1-tblfacturadeisensao
		    	con = Conexao.getConexao();
				 PreparedStatement cid = con.prepareStatement(sql);
				 cid.setInt(1, fun.getFK_factura());
				 cid.setString(2, fun.getNumero_factura());
				 cid.setInt(3, fun.getFK_paciente());
				 cid.setInt(4, fun.getFK_servico());				
				 cid.setDouble (5, fun.getPreco());
				 cid.setDouble(6, fun.getTotais());
				 cid.execute();
				 ResultSet rs = cid.executeQuery("SELECT LAST_INSERT_ID()");
				 if(rs.next()){
					ultimoCod = rs.getInt(1); 
				 }
				 cid.close();
				 System.out.println("Cadastrado com sucesso...tblfacturadeisensao"); 
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
		    
		    return ultimoCod;
				 
		}
		
		
		public int getIsentoExame(int cod1,int cod2) {

			int tipoFac = 0;
	    	String sql = "Select * from tblisencaoexame where FK_requisicao_exame = ? and FK_exame_isentado = ?";
			try {
				con = Conexao.getConexao();
				PreparedStatement ps = con.prepareStatement(sql);
				ps.setInt(1, cod1);
				ps.setInt(2, cod2);
				ResultSet rs = ps.executeQuery();
				if (rs.next())
					tipoFac = rs.getInt("FK_isencao");

				ps.close();
				con.close();

			} catch (SQLException e) {
				e.printStackTrace();
			}
			return tipoFac;
		}

		
		
		
		public int inserir_faturadeisensetada  (Facturacao fun)
		{
			int ultimoCod = 0;
		    String sql = "INSERT INTO tblfacturaisentada (FK_factura,FK_isencao,tot_tax_emkz,total_isentado) VALUES (?,?,?,?)";
		    try {
				 
				 //1-tblfacturadeisensao
		    	 con = Conexao.getConexao();
				 PreparedStatement cid = con.prepareStatement(sql);
				 cid.setInt(1, fun.getFK_factura());
				 cid.setInt(2, fun.getFK_isencao());
				 cid.setDouble(3, fun.getTaxa_emkz());
				 cid.setDouble(4, fun.getTotais());
				 cid.execute();
				 ResultSet rs = cid.executeQuery("SELECT LAST_INSERT_ID()");
				 if(rs.next()){
					ultimoCod = rs.getInt(1); 
				 }
				 cid.close();
				 System.out.println("Cadastrado com sucesso...tblfacturadeisentada"); 
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
		    
		    return ultimoCod;
				 
		}
		
//	    Inserir dados na tabela facturadeisensaoPorIdade
		
		public void inserir_faturadeisensaoPorIdade  (Facturacao fun)
		{
			String sql = "INSERT INTO tblfacturadeisencaoporidade (FK_factura,FK_consulta_confirmada,FK_paciente,FK_servico,preco,quantidade,taxa_emkz,total_pago,fk_fac_isentada) VALUES (?,?,?,?,?,?,?,?,?)";
		    try {
				 
				 //1-tblfacturadeisensao
		    	con = Conexao.getConexao();
				 PreparedStatement cid = con.prepareStatement(sql);
				 cid.setInt(1, fun.getFK_factura());
				 cid.setInt(2, fun.getFK_confirmacao_do_agendamento());
				 cid.setInt(3, fun.getFK_paciente());
				 cid.setInt(4, fun.getFK_servico());				
				 cid.setDouble(5, fun.getPreco());
				 cid.setInt(6, fun.getQuantidade());
				 cid.setDouble(7, fun.getTaxa_emkz());
				 cid.setDouble(8, fun.getTotais());	
				 cid.setInt(9, fun.getFK_fac_isentada());
				 cid.execute();
				 cid.close();
				 System.out.println("Cadastrado com sucesso...tblfacturadeisencaoporidade"); 
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
		
//	    Inserir dados na tabela facturasegurodesaude
		

		public void inserir_faturadesegurodesaude  (Facturacao fun)
		{
			
		    String sql = "INSERT INTO tblfacturadesegurodesaude (FK_factura,FK_paciente,FK_servico,preco,total,fk_confirmacao, taxa_perce, taxa_emkz) "
		    		+ " VALUES (?,?,?,?,?,?,?,?)";
		    try {
				 
				 //1-facturasegurodesaude
		    	 con = Conexao.getConexao();
				 PreparedStatement cid = con.prepareStatement(sql);
				 cid.setInt(1, fun.getFK_factura());
				 cid.setInt(2, fun.getTitular());
				 cid.setInt(3, fun.getFK_servico());
				 cid.setDouble(4, fun.getPreco());
				 cid.setDouble(5, fun.getTotal_factura());
				 cid.setInt(6, fun.getFK_confirmacao_do_agendamento());				 
				 cid.setDouble(7, fun.getTaxa_porcento());
				 cid.setDouble(8,fun.getTaxa_emkz());
			
				 cid.execute();
				 cid.close();
				 System.out.println("Cadastrado com sucesso...tblfacturadesegurodesaude"); 
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
		
		public void inserir_multipagamentos (Facturacao f)
		{
			
		    String sql = "INSERT INTO tblmultipagamentos (fk_factura,tipo_pagamento,quantia_paga) VALUES (?,?,?)";
		    try {
		    	con = Conexao.getConexao();
		    	PreparedStatement ps = con.prepareStatement(sql);
		    	ps.setInt(1, f.getFK_factura());
		    	ps.setString(2, f.getTipo_pagamento());
		    	ps.setDouble(3, f.getQuantia_paga());
		    	ps.execute();
		    	ps.close();
		    	con.close();
		    } 
		    catch (Exception e) {
				e.printStackTrace();
			}
				 
		}
		
		public void inserir_facturaplanodesaude (Facturacao f)
		{
			
		    String sql = "INSERT INTO tblfacturaplanodesaude (FK_factura,comparticipacao_aseguradora,comparticipacao_paciente,total_pago_plano_saude,FK_aseguradora,FK_tipo_dcobertura,FK_titular) VALUES (?,?,?,?,?,?,?)";
		    try {
		    	con = Conexao.getConexao();
		    	PreparedStatement ps = con.prepareStatement(sql);
		    	ps.setInt(1, f.getFK_factura());
		    	ps.setDouble(2, f.getComparticipacao_aseguradora());
		    	ps.setDouble(3, f.getComparticipacao_paciente());
		    	ps.setDouble(4, f.getTotal_pago_plano_saude());
		   	 	ps.setInt(5, f.getFK_aseguradora());
		   	 	ps.setInt(6, f.getFK_tipo_dcobertura());
		   	 	ps.setInt(7, f.getTitular());
		    	ps.execute();
		    	ps.close();
		    	con.close();
		    } 
		    catch (Exception e) {
				e.printStackTrace();
			}
		}
		
		//Pega ID da factura
		
		public Facturacao getIdFactura(int id_fatura){ 
			   
			Facturacao t = null;
			  String sql = "SELECT * FROM tblfactura where id_factura = ? ";
			  try
			  {
				  con = Conexao.getConexao();
			    PreparedStatement preparador = con.prepareStatement(sql);
			    preparador.setInt(1, id_fatura);
			    ResultSet rs = preparador.executeQuery();
			    if(rs.next()){
			     t = new Facturacao();
			     t.setFK_factura(rs.getInt("id_factura"));
			     t.setNumero_factura(rs.getString("numero_factura"));
			    
			    }
			     preparador.close(); 
			  }
			  catch(SQLException er){
			   throw new RuntimeException(er);
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
			  
			 
			  return t;
			 }
		
	// Del paciente	
		public void delpaciente(Facturacao funUp)
		 {
		  String sql = "delete from tblconsultaconfirmada where id_consulta_confirmada = ? ";
		  try {
			  con = Conexao.getConexao();
		    PreparedStatement preparador = con.prepareStatement(sql);
		    preparador = con.prepareStatement(sql);
		    preparador.setInt(1, funUp.getId_consulta_confirmada());
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
							con.close();
						}
						catch(SQLException ef){
							System.out.println("Erro finalizar: "+ef);
						}
				}
				 
		 }

//	    Inserir dados na tabela consultacancelada
		

		public void inserir_consultacancelada  (Facturacao fun)
		{
			
		    String sql = "INSERT INTO tblconsultacanselada (FK_funcionario,FK_servico,data,hora,descricao_do_canselamento,FK_confirmacao_agendamento) VALUES (?,?,?,?,?,?)";
		    try {
				 
				 //1-facturasegurodesaude
		    	con = Conexao.getConexao();
				 PreparedStatement cid = con.prepareStatement(sql);
				 cid.setInt(1, fun.getFK_funcionario());
				 cid.setInt(2, fun.getFK_servico());
				 cid.setDate(3, new Formatando().data_registo());
				 cid.setString(4, new Formatando().getHoraAtual());
				 cid.setString(5, fun.getDescricao_do_canselamento());	
				 cid.setInt(6, fun.getFK_confirmacao_do_agendamento());
				 
				 cid.execute();
				 cid.close();
				 System.out.println("Cadastrado com sucesso...tblconsultacanselada"); 
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
		
		
		public List<Paciente> getfacturarExamesConfirmada(){
			List <Paciente> lista = new ArrayList<Paciente>();
			String sql = "SELECT * FROM vwexamesrequisitadoparafaturar where data = curdate() "
					+ "and FK_numero_do_servico_de_exame not in (select fk_requisicao_dexame from tblfacturaexame) "
					+ "and FK_numero_do_servico_de_exame not in (select fk_requisicao_dexame from tblfacturademulticaixaexames) "
					+ "and FK_numero_do_servico_de_exame not in (select DISTINCT fk_requisicao_dexame from tblfacturadeseguroexames  ) "
					+ "and FK_numero_do_servico_de_exame not in (select fk_requisicao_dexame from tblfacturaisencaoporidadeexame) "
					+ "and FK_numero_do_servico_de_exame not in (select fk_requisicao_dexame from tblfacturaexameisentado) "
					+ "AND FK_numero_do_servico_de_exame not in (SELECT FK_numero_do_servico_de_exame FROM tblexamerequisitadocancelado) order by hora Limit 3";
			try {
				con = Conexao.getConexao();
				PreparedStatement preparador = con.prepareStatement(sql);
				 ResultSet rs = preparador.executeQuery();
				 while(rs.next())
				 {
					 Paciente pac = new Paciente();
					 pac.setNome_paciente(rs.getString("NomeCompleto"));
					 pac.setTotal_servico(rs.getInt("qtd_servico"));
					 pac.setValor_total(rs.getDouble("somatorio"));
					 pac.setFK_consulta_confirmada(rs.getInt("FK_numero_do_servico_de_exame"));
					 pac.setFK_paciente(rs.getInt("FK_paciente"));
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
		
		public List<Paciente> getListaExamesConfirmada(){
			List <Paciente> lista = new ArrayList<Paciente>();
			String sql = "SELECT * FROM vwexamesrequisitadoparafaturar order by data desc, hora desc Limit 50";
			try {
				con = Conexao.getConexao();
				PreparedStatement preparador = con.prepareStatement(sql);
				 ResultSet rs = preparador.executeQuery();
				 while(rs.next())
				 {
					 Paciente pac = new Paciente();
					 pac.setNome_paciente(rs.getString("NomeCompleto"));
					 pac.setQuantidade(rs.getInt("qtd_servico"));
					 pac.setNumero_processo(rs.getString("numeroprocesso"));
					 Calendar data = Calendar.getInstance();
	    		     data.setTime(rs.getDate("data"));
	    		     pac.setData_do_agendamento(data);
					 pac.setFK_consulta_confirmada(rs.getInt("FK_numero_do_servico_de_exame"));
					 pac.setFK_paciente(rs.getInt("FK_paciente"));
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
		
		public List<Paciente> getListaExamesConfirmada(String aux){
			List <Paciente> lista = new ArrayList<Paciente>();
			String sql = "SELECT * FROM vwexamesrequisitadoparafaturar where numeroprocesso = ? or nomecompleto Like ? order by data desc Limit 50;";
			try {
				con = Conexao.getConexao();
				PreparedStatement preparador = con.prepareStatement(sql);
				preparador.setString(1, aux);
				preparador.setString(2, aux+"%");
				ResultSet rs = preparador.executeQuery();
				 while(rs.next())
				 {
					 Paciente pac = new Paciente();
					 pac.setNome_paciente(rs.getString("NomeCompleto"));
					 pac.setQuantidade(rs.getInt("qtd_servico"));
					 pac.setNumero_processo(rs.getString("numeroprocesso"));
					 Calendar data = Calendar.getInstance();
	    		     data.setTime(rs.getDate("data"));
	    		     pac.setData_do_agendamento(data);
					 pac.setFK_consulta_confirmada(rs.getInt("FK_numero_do_servico_de_exame"));
					 pac.setFK_paciente(rs.getInt("FK_paciente"));
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
		
		public Paciente getfacturarExamesConfirmada(int cod){
			Paciente pac = new Paciente(); 
			String sql = "SELECT * FROM vwexamesrequisitadoparafaturar where FK_numero_do_servico_de_exame = ?";
			try {
				con = Conexao.getConexao();
				PreparedStatement preparador = con.prepareStatement(sql);
				preparador.setInt(1, cod);
				 ResultSet rs = preparador.executeQuery();
				 if(rs.next())
				 {
					 pac.setNome_paciente(rs.getString("NomeCompleto"));
					 pac.setNumero_processo(rs.getString("numeroprocesso"));
					 pac.setId_tipo_dcobertura(rs.getInt("FK_tipo_dcobertura"));
					 pac.setIdade(rs.getDate("data_nascimento"));
					 pac.setFK_requsicao(rs.getInt("FK_numero_do_servico_de_exame"));
					 pac.setFK_paciente(rs.getInt("FK_paciente"));
					 pac.setFK_servico(rs.getInt("FK_servico_exame"));
					 
				 }
				 preparador.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
			catch (Exception e) {
				System.out.println(e.getMessage());
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
		
		public List<Paciente> ListaEsperaExamesConfirmada(){
			List <Paciente> lista = new ArrayList<Paciente>();
			String sql = "SELECT * FROM vwexamesrequisitadoparafaturar WHERE data = curdate() AND FK_numero_do_servico_de_exame NOT IN ( SELECT fk_requisicao_dexame FROM tblfacturaexame ) AND FK_numero_do_servico_de_exame NOT IN ( SELECT fk_requisicao_dexame FROM  tblfacturademulticaixaexames ) AND FK_numero_do_servico_de_exame NOT IN ( SELECT fk_requisicao_dexame FROM tblfacturaisencaoporidadeexame )  AND FK_numero_do_servico_de_exame NOT IN ( SELECT fk_requisicao_dexame FROM tblfacturaexameisentado ) AND FK_numero_do_servico_de_exame NOT IN ( SELECT FK_numero_do_servico_de_exame FROM tblexamerequisitadocancelado ) ORDER BY hora LIMIT 100 OFFSET 3";
			try {
				con = Conexao.getConexao();
				PreparedStatement preparador = con.prepareStatement(sql);
				 ResultSet rs = preparador.executeQuery();
				 while(rs.next())
				 {
					 Paciente pac = new Paciente();
					 pac.setNome_paciente(rs.getString("NomeCompleto"));
					 pac.setTotal_servico(rs.getInt("qtd_servico"));
					 pac.setValor_total(rs.getDouble("somatorio"));
					 pac.setFK_consulta_confirmada(rs.getInt("FK_numero_do_servico_de_exame"));
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
		
		public void facturarServicosExames  (Facturacao fun)
		{
			
		    String sql = "INSERT INTO tblfacturaexame (FK_paciente,preco,desconto,FK_factura,FK_exame_requisitado,taxa_porcento,taxa,total_pago,fk_requisicao_dexame) VALUES (?,?,?,?,?,?,?,?,?)";
		    try {
				 
				 //1-facturasegurodesaude
		    	 con = Conexao.getConexao();
				 PreparedStatement cid = con.prepareStatement(sql);
				 cid.setInt(1, fun.getFK_paciente());
				 cid.setDouble(2, fun.getPreco());
				 cid.setDouble(3, fun.getDesconto());
				 cid.setInt(4, fun.getFK_factura());
				 cid.setInt(5, fun.getFK_servico_exame());	
				 cid.setDouble(6, fun.getTaxa_percentual());
				 cid.setDouble(7, fun.getTaxa());
				 cid.setDouble(8, fun.getTotais());
				 cid.setInt(9, fun.getFK_requisicao_dexame());
				 
				 cid.execute();
				 cid.close();
				 System.out.println("Cadastrado com sucesso...tblfacturaexame"); 
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
		    
		    public void eliminarUmServicoExame  (int cod)
			{
			    
		    	String sql = "Delete from tblexamerequisitado where id_servico_requisitado = ?";
			    try {
					 //1-facturasegurodesaude
			    	 con = Conexao.getConexao();
					 PreparedStatement cid = con.prepareStatement(sql);
					 cid.setInt(1, cod);					 
					 cid.execute();
					 cid.close();
					 System.out.println("Eliminado o Servicos"); 
				} catch (SQLException e) {
					e.printStackTrace();
				}
			    catch (Exception e) {
					 System.out.println(e);
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
		    public void facturarTratamento(Facturacao fun)
			{
			    String sql = "INSERT INTO tblfacturartratamento(FK_factura,FK_tratamento,preco_tratamento,imposto_selo,taxa_emkz,total_pago_tratamento,quantidade) VALUES (?,?,?,?,?,?,?)";
			    try {
					 //1-tblfacturartratamento
			    	 con = Conexao.getConexao();
					 PreparedStatement cid = con.prepareStatement(sql);
					 cid.setInt(1, fun.getFK_factura());
					 cid.setInt(2, fun.getFK_tratamento());
					 cid.setDouble(3, fun.getPreco_tratamento());
					 cid.setDouble(4, fun.getImposto_selo());
					 cid.setDouble(5, fun.getTaxa_emkz());	
					 cid.setDouble(6, fun.getTotal_pago_tratamento());
					 cid.setInt(7, fun.getQuantdd());
					 cid.execute();
					 cid.close();
					 System.out.println("Cadastrado com sucesso...tblfacturartratamento"); 
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
		    
		    public void facturarMulticaixaTratamento(Facturacao fun)
			{
			    String sql = "INSERT INTO tblfacturademulticaixaservicosgerais(FK_factura, FK_servico, sg_mcaixa_preco, sg_mcaixa_qtd, sg_mcaixa_taxa_perc, sg_mcaixa_taxa_emkz, sg_mcaixa_total, FK_banco, numero_cartao) VALUES (?,?,?,?,?,?,?,?,?)";
			    try {
					 //1-tblfacturartratamento
			    	 con = Conexao.getConexao();
					 PreparedStatement cid = con.prepareStatement(sql);
					 cid.setInt(1, fun.getFK_factura());
					 cid.setInt(2, fun.getFK_servico());
					 cid.setDouble(3, fun.getSg_mcaixa_preco());
					 cid.setInt(4, fun.getSg_mcaixa_qtd());
					 cid.setDouble(5, fun.getSg_mcaixa_taxa_perc());
					 cid.setDouble(6, fun.getSg_mcaixa_taxa_emkz());
					 cid.setDouble(7, fun.getSg_mcaixa_total());
					 cid.setDouble(8, fun.getFK_banco());
					 cid.setInt(9, fun.getNumero_cartao());
					 cid.execute();
					 cid.close();
					 System.out.println("Cadastrado com sucesso...tblfacturademulticaixaservicosgerais"); 
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
		    
		    public void inserir_faturaServGeralIsentada(Facturacao fun)
			{
				
			    String sql = "INSERT INTO tblfacturaservicogeralisentado (FK_fac_isentada,FK_factura,FK_serv_geral,ttl_taxa_emkz,total_serv_geral,taxa_porcento,qtd_serv_geral,preco_sev_geral) VALUES (?,?,?,?,?,?,?,?)";
			    try {
					 
					 
			    	con = Conexao.getConexao();
					 PreparedStatement cid = con.prepareStatement(sql);
					 cid.setInt(1, fun.getFK_fac_isentada());
					 cid.setInt(2, fun.getFK_factura());
					 cid.setInt(3, fun.getFK_tratamento());
					 cid.setDouble(4, fun.getTot_tax_emkz());
					 cid.setDouble(5, fun.getTotal_pago_tratamento());
					 cid.setDouble(6, fun.getTaxa_porcento());
					 cid.setDouble(7, fun.getQuantidade());
					 cid.setDouble(8, fun.getPreco_tratamento());
					 cid.execute();
					 cid.close();
					 System.out.println("Cadastrado com sucesso...tblServicoGeralIsentado"); 
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
		    
		    public int getTipoFactura(int cod) {

				int tipoFac = 0;
		    	String sql = "Select * from tblfactura where id_factura = ? ";
				try {
					con = Conexao.getConexao();
					PreparedStatement ps = con.prepareStatement(sql);
					ps.setInt(1, cod);
					ResultSet rs = ps.executeQuery();
					if (rs.next())
						tipoFac = rs.getInt("FK_tipo_deservico");

					ps.close();
					con.close();

				} catch (SQLException e) {
					e.printStackTrace();
				}
				return tipoFac;
			}
		    
		    public int getFacturaExameIsentada(int cod1,int cod2) {

				int tipoFac = 0;
		    	String sql = "Select * from tblfacturaexameisentado where FK_requisicao_exame = ? and FK_exame_isentado = ?";
				try {
					con = Conexao.getConexao();
					PreparedStatement ps = con.prepareStatement(sql);
					ps.setInt(1, cod1);
					ps.setInt(2, cod2);
					ResultSet rs = ps.executeQuery();
					if (rs.next())
						tipoFac = rs.getInt("FK_fac_isentada");

					ps.close();
					con.close();

				} catch (SQLException e) {
					e.printStackTrace();
				}
				return tipoFac;
			}
		    
		    public int isentarPaciente(Facturacao isn) {
				 String sql = "Insert into tblisencaoprincipal (numero_daisencao,FK_categoria_isencao,total_taxa_emkz,total_isentado,FK_paciente,FK_funcionario,razao_isencao,data_isencao,hora_isencao) value (?,?,?,?,?,?,?,?,?)";
				 int ultimoId = 0;
				 try{
					 con = Conexao.getConexao();
					 PreparedStatement ps = con.prepareStatement(sql);
					 ps.setString(1, isn.getNumero_daisencao());
					 ps.setInt(2, isn.getFK_categoria());
					 ps.setDouble(3, isn.getTaxa_emkz());
					 ps.setDouble(4, isn.getTotais());
					 ps.setInt(5, isn.getFK_paciente());
					 ps.setInt(6, isn.getFK_funcionario());
					 ps.setString(7, isn.getMotivo());
					 ps.setDate(8, ft.data_registo());
					 ps.setString(9, ft.getHoraAtual());
					 ps.execute();
					 ResultSet rs = ps.executeQuery("SELECT LAST_INSERT_ID()");
					 if(rs.next())
			    	        ultimoId = rs.getInt(1);
				 }catch(Exception e){
					 e.printStackTrace();
				 }
				 return ultimoId;
			}
		    
		    public int getIsentoConsulta(int cod1) {

				int tipoFac = 0;
		    	String sql = "Select * from tblisencaoconsulta where FK_con_confirmada = ? ";
				try {
					con = Conexao.getConexao();
					PreparedStatement ps = con.prepareStatement(sql);
					ps.setInt(1, cod1);
					 
					ResultSet rs = ps.executeQuery();
					if (rs.next())
						tipoFac = rs.getInt("FK_isencao");

					ps.close();
					con.close();

				} catch (SQLException e) {
					e.printStackTrace();
				}
				return tipoFac;
			}
		    
		    public boolean xeckIsentada(int cod1) {

				 
		    	String sql = "Select * from tblisencaoexame where FK_requisicao_exame = ? ";
				try {
					con = Conexao.getConexao();
					PreparedStatement ps = con.prepareStatement(sql);
					ps.setInt(1, cod1);
				 
					ResultSet rs = ps.executeQuery();
					if (rs.next())
						return true;

					ps.close();
					con.close();

				} catch (SQLException e) {
					e.printStackTrace();
				}
				return false;
			}
	
		    public void faturarProdutos(Facturacao fct){
		    	String sql = "Insert into tblfacturarprodutos (fk_factura,fk_produto,preco_produto,tx_emkz_produto,taxa_perc_produto,tot_pago_produto,qtd_produto,numero_lote,data_expiracao) value(?,?,?,?,?,?,?,?,?)";
		    	int qdt = 0;
		    	int codp = 0;
		    	try {
					con = Conexao.getConexao();
					PreparedStatement ps = con.prepareStatement(sql);
					ps.setInt(1, fct.getFK_factura());
					ps.setInt(2, fct.getFK_produto());
					ps.setDouble(3, fct.getPreco_produto());
					ps.setDouble(4, fct.getTx_emkz_produto());
					ps.setDouble(5, fct.getTaxa_perc_produto());
					ps.setDouble(6, fct.getTot_pago_produto());
					ps.setInt(7, fct.getQtd_produto());
					ps.setString(8, fct.getNumLote());
					ps.setDate(9, fct.getData());
					ps.execute();
					System.out.println("Salvo com Sucesso Facturacao de Produto");
					// Limpando todos os parametros iniciais
					ps.clearParameters();
					while(fct.getQtd_produto()>0){
						sql = "Select * from tblprodutoemestoque where FK_id_produto = ? order by data_decaducidade asc";
						ps = con.prepareStatement(sql);
						ps.setInt(1, fct.getFK_produto());
						ResultSet rs = ps.executeQuery();
						if(rs.next()){
							qdt = rs.getInt("quantidade_produto");
							codp= rs.getInt("FK_id_produto");
							String lote = rs.getString("numero_dolote_sere");
							if(fct.getQtd_produto() >= qdt){
								// Excluindo o produto em stock atual
								sql = "delete from tblprodutoemestoque where FK_id_produto = ? and numero_dolote_sere = ?";
								ps = con.prepareStatement(sql);
								ps.setInt(1, codp);
								ps.setString(2, lote);
								ps.execute();
							}
							else{
									// passando atual valor da quantidade e atualizando o estoque
									fct.setQuantidade(qdt-fct.getQtd_produto());
									sql = "Update tblprodutoemestoque set quantidade_produto= ? where FK_id_produto = ? and numero_dolote_sere = ?";
									ps = con.prepareStatement(sql);
									ps.setInt(1, fct.getQuantidade());
									ps.setInt(2, codp);
									ps.setString(3, lote);
									ps.execute();
									ps.close();
								}
							
							}
						fct.setQtd_produto(fct.getQtd_produto()-qdt);
						}			
										
					System.out.println("Atualizado com Sucesso Produto em Stock");
					
					con.close();
				} catch (Exception e) {
					e.printStackTrace();
				}
		    }
		    
		    public void faturarMulticaixaProdutos(Facturacao fct){
		    	String sql = "Insert into tblfacturademulticaixaprodutos (FK_factura,FK_servico,pro_mcaixa_preco,pro_mcaixa_qtd,pro_mcaixa_taxa_perc,pro_mcaixa_taxa_emkz,pro_mcaixa_total,FK_banco,numero_cartao,numero_lote,data_expiracao) value(?,?,?,?,?,?,?,?,?,?,?)";
		    	int qdt = 0;
		    	int codp = 0;
		    	try {
					con = Conexao.getConexao();
					PreparedStatement ps = con.prepareStatement(sql);
					ps.setInt(1, fct.getFK_factura());
					ps.setInt(2, fct.getFK_produto());
					ps.setDouble(3, fct.getPreco_produto());
					ps.setInt(4, fct.getQtd_produto());
					ps.setDouble(5, fct.getTaxa_perc_produto());
					ps.setDouble(6, fct.getTx_emkz_produto());
					ps.setDouble(7, fct.getTot_pago_produto());
					ps.setInt(8, fct.getFK_banco());
					ps.setInt(9, fct.getNumero_cartao());
					ps.setString(10, fct.getNumLote());
					ps.setDate(11, fct.getData());
					ps.execute();
					System.out.println("Salvo com Sucesso Facturacao multicaixa de Produto");
					// Limpando todos os parametros iniciais
					ps.clearParameters();
					while(fct.getQtd_produto()>0){
						sql = "Select * from tblprodutoemestoque where FK_id_produto = ? order by data_decaducidade asc";
						ps = con.prepareStatement(sql);
						ps.setInt(1, fct.getFK_produto());
						ResultSet rs = ps.executeQuery();
						if(rs.next()){
							qdt = rs.getInt("quantidade_produto");
							codp= rs.getInt("FK_id_produto");
							String lote = rs.getString("numero_dolote_sere");
							if(fct.getQtd_produto() >= qdt){
								// Excluindo o produto em stock atual
								sql = "delete from tblprodutoemestoque where FK_id_produto = ? and numero_dolote_sere = ?";
								ps = con.prepareStatement(sql);
								ps.setInt(1, codp);
								ps.setString(2, lote);
								ps.execute();
							}
							else{
									// passando atual valor da quantidade e atualizando o estoque
									fct.setQuantidade(qdt-fct.getQtd_produto());
									sql = "Update tblprodutoemestoque set quantidade_produto= ? where FK_id_produto = ? and numero_dolote_sere = ?";
									ps = con.prepareStatement(sql);
									ps.setInt(1, fct.getQuantidade());
									ps.setInt(2, codp);
									ps.setString(3, lote);
									ps.execute();
									ps.close();
								}
							
							}
						fct.setQtd_produto(fct.getQtd_produto()-qdt);
						}			
										
					System.out.println("Atualizado com Sucesso Produto em Stock");
					
					con.close();
				} catch (Exception e) {
					e.printStackTrace();
				}
		    }
		    
		    public void faturarSeguroProdutos(Facturacao fct){
		    	String sql = "Insert into tblfacturadeseguroprodutos (FK_factura,FK_produto,pro_seguro_preco,pro_seguro_qtd,pro_seguro_taxa_perc,pro_seguro_taxa_emkz,pro_seguro_total) value(?,?,?,?,?,?,?)";
		    	int qdt = 0;
		    	int codp = 0;
		    	try {
					con = Conexao.getConexao();
					PreparedStatement ps = con.prepareStatement(sql);
					ps.setInt(1, fct.getFK_factura());
					ps.setInt(2, fct.getFK_produto());
					ps.setDouble(3, fct.getPreco_produto());
					ps.setInt(4, fct.getQtd_produto());
					ps.setDouble(5, fct.getTaxa_perc_produto());
					ps.setDouble(6, fct.getTx_emkz_produto());
					ps.setDouble(7, fct.getTot_pago_produto());
					ps.execute();
					System.out.println("Salvo com Sucesso Facturacao seguro de Produto");
					// Limpando todos os parametros iniciais
					ps.clearParameters();
					while(fct.getQtd_produto()>0){
						sql = "Select * from tblprodutoemestoque where FK_id_produto = ? order by data_decaducidade asc";
						ps = con.prepareStatement(sql);
						ps.setInt(1, fct.getFK_produto());
						ResultSet rs = ps.executeQuery();
						if(rs.next()){
							qdt = rs.getInt("quantidade_produto");
							codp= rs.getInt("FK_id_produto");
							String lote = rs.getString("numero_dolote_sere");
							if(fct.getQtd_produto() >= qdt){
								// Excluindo o produto em stock atual
								sql = "delete from tblprodutoemestoque where FK_id_produto = ? and numero_dolote_sere = ?";
								ps = con.prepareStatement(sql);
								ps.setInt(1, codp);
								ps.setString(2, lote);
								ps.execute();
							}
							else{
									// passando atual valor da quantidade e atualizando o estoque
									fct.setQuantidade(qdt-fct.getQtd_produto());
									sql = "Update tblprodutoemestoque set quantidade_produto= ? where FK_id_produto = ? and numero_dolote_sere = ?";
									ps = con.prepareStatement(sql);
									ps.setInt(1, fct.getQuantidade());
									ps.setInt(2, codp);
									ps.setString(3, lote);
									ps.execute();
									ps.close();
								}
							
							}
						fct.setQtd_produto(fct.getQtd_produto()-qdt);
						}			
										
					System.out.println("Atualizado com Sucesso Produto em Stock");
					
					con.close();
				} catch (Exception e) {
					e.printStackTrace();
				}
		    }
		    
		    public void faturarProdutosIsentado(Facturacao fct){
		    	String sql = "Insert into tblfacturaprodutoisentado (fk_fac_isentada,fk_factura,fk_produto,qtd_produto,preco_produto,tx_perc_produto,tx_emkz_produto,t_isen_produto) value(?,?,?,?,?,?,?,?)";
		    	int qdt = 0;
		    	int codp = 0;
		    	try {
					con = Conexao.getConexao();
					PreparedStatement ps = con.prepareStatement(sql);
					ps.setInt(1, fct.getFK_fac_isentada());
					ps.setInt(2, fct.getFK_factura());
					ps.setInt(3, fct.getFK_produto());
					ps.setInt(4, fct.getQtd_produto());
					ps.setDouble(5, fct.getPreco_produto());
					ps.setDouble(6, fct.getTaxa_perc_produto());
					ps.setDouble(7, fct.getTx_emkz_produto());
					ps.setDouble(8, fct.getTot_pago_produto());
					ps.execute();
					System.out.println("Salvo com Sucesso faturarProdutosIsentado");
					// Limpando todos os parametros iniciais
					ps.clearParameters();
					while(fct.getQtd_produto()>0){
						sql = "Select * from tblprodutoemestoque where FK_id_produto = ? order by data_decaducidade asc";
						ps = con.prepareStatement(sql);
						ps.setInt(1, fct.getFK_produto());
						ResultSet rs = ps.executeQuery();
						if(rs.next()){
							qdt = rs.getInt("quantidade_produto");
							codp= rs.getInt("FK_id_produto");
							String lote = rs.getString("numero_dolote_sere");
							if(fct.getQtd_produto() >= qdt){
								// Excluindo o produto em stock atual
								sql = "delete from tblprodutoemestoque where FK_id_produto = ? and numero_dolote_sere = ?";
								ps = con.prepareStatement(sql);
								ps.setInt(1, codp);
								ps.setString(2, lote);
								ps.execute();
							}
							else{
									// passando atual valor da quantidade e atualizando o estoque
									fct.setQuantidade(qdt-fct.getQtd_produto());
									sql = "Update tblprodutoemestoque set quantidade_produto= ? where FK_id_produto = ? and numero_dolote_sere = ?";
									ps = con.prepareStatement(sql);
									ps.setInt(1, fct.getQuantidade());
									ps.setInt(2, codp);
									ps.setString(3, lote);
									ps.execute();
									ps.close();
								}
							
							}
						fct.setQtd_produto(fct.getQtd_produto()-qdt);
						}			
										
					System.out.println("Atualizado com Sucesso Produto em Stock");
					
					con.close();
				} catch (Exception e) {
					e.printStackTrace();
				}
		    }
		    
//		    public void faturarProdutosIsentado(Facturacao fct){
//		    	String sql = "Insert into tblfacturaprodutoisentado (fk_fac_isentada,fk_factura,fk_produto,qtd_produto,preco_produto,tx_emkz_produto,tx_perc_produto,t_isen_produto) value(?,?,?,?,?,?,?,?)";
//		    	try {
//					con = Conexao.getConexao();
//					PreparedStatement ps = con.prepareStatement(sql);
//					ps.setInt(1, fct.getFK_fac_isentada());
//					ps.setInt(2, fct.getFK_factura());
//					ps.setInt(3, fct.getFK_produto());
//					ps.setInt(4, fct.getQtd_produto());
//					ps.setDouble(5, fct.getPreco_produto());
//					ps.setDouble(6, fct.getTx_emkz_produto());
//					ps.setDouble(7, fct.getTaxa_perc_produto());
//					ps.setDouble(8, fct.getT_isen_produto());
//					ps.execute();
//					ps.close();
//					con.close();
//				} catch (Exception e) {
//					e.printStackTrace();
//				}
//		    }
		    
		    public int getIsentoReceita(int cod1,int cod2) {

				int tipoFac = 0;
		    	String sql = "Select * from tblisencaoreceita where FK_receita = ? and FK_farmaco = ?";
				try {
					con = Conexao.getConexao();
					PreparedStatement ps = con.prepareStatement(sql);
					ps.setInt(1, cod1);
					ps.setInt(2, cod2);
					ResultSet rs = ps.executeQuery();
					if (rs.next())
						tipoFac = rs.getInt("FK_isencao_principal");

					ps.close();
					con.close();
//					System.out.println(tipoFac);
				} catch (SQLException e) {
					e.printStackTrace();
				}
				return tipoFac;
			}
		    
		    public Facturacao getIsentoNaReceita(int cod1,int cod2) {
				 
				Facturacao isento = new Facturacao();
		    	String sql = "Select * from tblisencaoreceita where FK_receita = ? and FK_farmaco = ?";
				try {
					con = Conexao.getConexao();
					PreparedStatement ps = con.prepareStatement(sql);
					ps.setInt(1, cod1);
					ps.setInt(2, cod2);
					ResultSet rs = ps.executeQuery();
					if (rs.next()){
						isento.setId_isencao(rs.getInt("FK_isencao_principal"));
						isento.setFK_isencao(rs.getInt("FK_isencao_principal"));
						isento.setFK_produto(rs.getInt("FK_farmaco"));
						isento.setPreco_produto(rs.getDouble("preco_rec"));
						isento.setQtd_produto(rs.getInt("quantidade"));
						isento.setTx_emkz_produto(rs.getDouble("txa_percentual"));
						isento.setTaxa_perc_produto(rs.getDouble("txa_emkwanza"));
						isento.setT_isen_produto(rs.getDouble("total_isentado"));
						
					}

					ps.close();
					con.close();
//					System.out.println(tipoFac);
				} catch (SQLException e) {
					e.printStackTrace();
				}
				return isento;
			}

		    public Facturacao getIsentoNaFacturaPd(int cod1,int cod2) {
				 
				Facturacao isento = new Facturacao();
		    	String sql = "Select * from tblfacturaprodutoisentado where fk_factura = ? and fk_produto = ?";
				try {
					con = Conexao.getConexao();
					PreparedStatement ps = con.prepareStatement(sql);
					ps.setInt(1, cod1);
					ps.setInt(2, cod2);
					ResultSet rs = ps.executeQuery();
					if (rs.next()){
						isento.setFK_factura(rs.getInt("fk_factura"));
						isento.setFK_fac_isentada(rs.getInt("fk_fac_isentada"));
						isento.setFK_produto(rs.getInt("fk_produto"));
						isento.setQtd_produto(rs.getInt("qtd_produto"));
						isento.setTaxa_emkz(rs.getDouble("tx_emkz_produto"));
						isento.setT_isen_produto(rs.getDouble("t_isen_produto"));
						
					}

					ps.close();
					con.close();
//					System.out.println(tipoFac);
				} catch (SQLException e) {
					e.printStackTrace();
				}
				return isento;
			}
		    public Facturacao getTotaisIsentoNaReceita(int cod1) {
				int tipoFac = 0;
				Facturacao isento = new Facturacao();
		    	String sql = "Select sum(total_isentado) as total_emkza, sum(txa_emkwanza) as ttax_emkza "
		    			+    "from tblisencaoreceita where FK_receita = ? group by (FK_receita)";
				try {
					con = Conexao.getConexao();
					PreparedStatement ps = con.prepareStatement(sql);
					ps.setInt(1, cod1);
					ResultSet rs = ps.executeQuery();
					if (rs.next()){
						isento.setTot_tax_emkz(rs.getDouble("ttax_emkza"));
						isento.setTotal_isentado(rs.getDouble("total_emkza"));
					}

					ps.close();
					con.close();
//					System.out.println(tipoFac);
				} catch (SQLException e) {
					e.printStackTrace();
				}
				return isento;
			}

		    public List<Produtos> buscarReceitasIsentados(int isen){
		    	String sql = "Select * from vwhistoriocoisencaoitemreceita where FK_receita = ?";
		    	List<Produtos> lista = new ArrayList<Produtos>();
		    	try {
					con = Conexao.getConexao();
					PreparedStatement ps = con.prepareStatement(sql);
					ps.setInt(1, isen);
					ResultSet rs = ps.executeQuery();
					while(rs.next()){
						Produtos fct = new Produtos();
						fct.setId_produto(rs.getInt("id_produto"));
						fct.setFK_receita(rs.getInt("FK_receita"));
						fct.setFK_isencao(rs.getInt("id_isencao"));
						fct.setTaxa_emkz(rs.getDouble("txa_emkwanza"));
						fct.setTaxas(rs.getDouble("txa_percentual"));
						fct.setTotal_tx_farm_isent(rs.getDouble("total_isentado"));
						fct.setQtd_farmaco_isentado(rs.getInt("quantidade"));
						lista.add(fct);
					}
					ps.close();
					con.close();
				} catch (Exception e) {
					e.printStackTrace();
				}
		    	return lista;
		    }
		    
		    public Facturacao getConsultaConfirmada(int aux){
		    	String sql = "Select * from vwconsultaconfirmada where id_consulta_confirmada = ?";
		    	Facturacao cons = new Facturacao();
		    	double txa_kz = 0;
		    	try {
					con = Conexao.getConexao();
					PreparedStatement ps = con.prepareStatement(sql);
					ps.setInt(1, aux);
					ResultSet rs = ps.executeQuery();
					if(rs.next())
					{					  
						
						cons.setFK_servico(rs.getInt("id_servico"));
						cons.setPreco(rs.getDouble("preco_unitario"));
						cons.setQuantidade(1);
						cons.setTaxa(rs.getDouble("taxa"));
						txa_kz = (rs.getDouble("preco_unitario")*rs.getDouble("taxa"))/100;
						cons.setTaxa_emkz(txa_kz);
						cons.setTotais(rs.getDouble("preco_unitario")+txa_kz);
						
					}
					ps.close();
					con.close();
				} catch (Exception e) {
					e.printStackTrace();
				}
		    	return cons;
		    }
		    
		    public void inserirDetalhesReceita(Facturacao f){
		    	String sql = "Insert into tbldetalhesdarecitafacturada (fk_factura,nome_medico,hospital) value (?,?,?)";
		    	
		    	try {
		    		con = Conexao.getConexao();
					PreparedStatement ps = con.prepareStatement(sql);
					ps.setInt(1, f.getFK_factura());
					ps.setString(2, f.getNome_medico());
					ps.setString(3, f.getHospital());
					ps.execute();
					ps.close();
					con.close();
				} catch (Exception e) {
					e.printStackTrace();
				}
		    }
		    
		    public List<Diverso> buscarTaxas(){
		    	String sql = "select * from tbltaxas ";
		    	List<Diverso> lista = new  ArrayList<Diverso>();
		    	try {
		    		con = Conexao.getConexao();
		    		PreparedStatement ps = con.prepareStatement(sql);
		    		ResultSet rs = ps.executeQuery();
		    		while(rs.next()){
		    			Diverso d = new Diverso();
		    			d.setId_taxa(rs.getInt("id_taxa"));
		    			d.setTipo_de_taxa(rs.getString("tipo_de_taxa"));
		    			d.setTaxa(rs.getDouble("taxa"));
		    			d.setDescricao_tx(rs.getString("descricao"));
		    			lista.add(d);
		    		}
					ps.close();
					con.close();
				} catch (Exception e) {
					e.printStackTrace();
				}
		    	return lista;
		    }
		    
		    public  Diverso buscarTaxas(int cod){
		    	String sql = "select * from tbltaxas where id_taxa = ?";
		    	Diverso d = new Diverso();
		    	try {
		    		con = Conexao.getConexao();
		    		PreparedStatement ps = con.prepareStatement(sql);
		    		ps.setInt(1, cod);
		    		ResultSet rs = ps.executeQuery();
		    		if(rs.next()){
		    			
		    			d.setId_taxa(rs.getInt("id_taxa"));
		    			d.setTipo_de_taxa(rs.getString("tipo_de_taxa"));
		    			d.setTaxa(rs.getDouble("taxa"));
		    			d.setDescricao_tx(rs.getString("descricao"));
		    			 
		    		}
					ps.close();
					con.close();
				} catch (Exception e) {
					e.printStackTrace();
				}
		    	return d;
		    }
		    
		    public  int xeckNomeTaxas(String cod){
		    	String sql = "select * from tbltaxas where tipo_de_taxa = ?";
		    	int status = 0;
		    	try {
		    		con = Conexao.getConexao();
		    		PreparedStatement ps = con.prepareStatement(sql);
		    		ps.setString(1, cod);
		    		ResultSet rs = ps.executeQuery();
		    		if(rs.next()) 		    			
		    			status = rs.getInt("id_taxa");
					ps.close();
					con.close();
				} catch (Exception e) {
					e.printStackTrace();
				}
		    	 
		    	return status;
    			
		    }
		    
		    
		    
		    public void novaTaxa(Diverso d){
		    	String sql = "Insert into tbltaxas (tipo_de_taxa,taxa,descricao) value (?,?,?)";
		    	try {
					con = Conexao.getConexao();
					PreparedStatement ps = con.prepareStatement(sql);
					ps.setString(1, d.getTipo_de_taxa());
					ps.setDouble(2, d.getTaxa());
					ps.setString(3, d.getDescricao_tx());
					ps.execute();
					System.out.println("Operacao concluida em taxa OK!");
					ps.close();
					con.close();
				} catch (Exception e) {
					e.printStackTrace();
				}
		    }
		    
		    public void editaTaxa(Diverso d){
		    	String sql = "Update tbltaxas set tipo_de_taxa = ?,taxa = ?,descricao = ? where id_taxa = ?";
		    	try {
					con = Conexao.getConexao();
					PreparedStatement ps = con.prepareStatement(sql);
					ps.setString(1, d.getTipo_de_taxa());
					ps.setDouble(2, d.getTaxa());
					ps.setString(3, d.getDescricao_tx());
					ps.setInt(4, d.getId_taxa());
					ps.execute();
					System.out.println("Operacao concluida em taxa OK!");
					ps.close();
					con.close();
				} catch (Exception e) {
					e.printStackTrace();
				}
		    }
		    
		    public void excluirTaxa(int cod){
		    	String sql = "Delete from tbltaxas where id_taxa = ?";
		    	try {
					con = Conexao.getConexao();
					PreparedStatement ps = con.prepareStatement(sql);
					ps.setInt(1, cod);
					ps.execute();
					System.out.println("Operacao concluida em taxa OK!");
					ps.close();
					con.close();
				} catch (Exception e) {
					e.printStackTrace();
				}
		    }

		    
		    public void inserir_facturaAtrasoInternamento(FacturaAtrasos fa){
		    	String sql = "Insert into tblfacturatrasointernamento (FK_internamento,total_exame,total_procedimento,total_medicamento,total_diaria,total_geral,tipo_pagamento,total_material,FK_funcionario,data_reg,hora_reg) value (?,?,?,?,?,?,?,?,?,?,?)";
		    	try {
					con = Conexao.getConexao();
					PreparedStatement ps = con.prepareStatement(sql);
					ps.setInt(1, 0);
					ps.setDouble(2, fa.getTotal_exame());
					ps.setDouble(3, fa.getTotal_procedimento());
					ps.setDouble(4, fa.getTotal_medicamento());
					ps.setDouble(5, fa.getTotal_diaria());
					ps.setDouble(6, fa.getTotal_geral());
					ps.setString(7, fa.getTipo_pagamento());
					ps.setDouble(8, fa.getTotal_material());
					ps.setInt(9, fa.getFK_funcionario());
					ps.setDate(10, ft.data_registo());
					ps.setString(11, ft.getHoraAtual());
					ps.execute();
					con.close();
				} catch (Exception e) {
					e.printStackTrace();
				}
		    }
		    
		    public void inserir_facturaAtrasoInternamentoDiaria(FacturaAtrasos fa){
		    	String sql = "Insert into tblfacturatrasointdiarias (FK_internamento,FK_outro_servico,qtd,preco,preco_total,data_reg,hora_reg) value (?,?,?,?,?,?,?)";
		    	try {
					con = Conexao.getConexao();
					PreparedStatement ps = con.prepareStatement(sql);
					ps.setInt(1, 0);
					ps.setInt(2, fa.getFK_servico());
					ps.setInt(3, fa.getQuantidade());
					ps.setDouble(4, fa.getPreco());
					ps.setDouble(5, fa.getTotal_preco());
					ps.setDate(6, ft.data_registo());
					ps.setString(7, ft.getHoraAtual());
					ps.execute();
					con.close();
				} catch (Exception e) {
					e.printStackTrace();
				}
		    }
		    
		    public void inserir_facturaAtrasoInternamentoExame(FacturaAtrasos fa){
		    	String sql = "Insert into tblfacturatrasointexame (FK_internamento,FK_exame,qtd,preco,preco_total,data_reg,hora_reg) value (?,?,?,?,?,?,?)";
		    	try {
					con = Conexao.getConexao();
					PreparedStatement ps = con.prepareStatement(sql);
					ps.setInt(1, 0);
					ps.setInt(2, fa.getFK_servico_exame());
					ps.setInt(3, fa.getQuantidade());
					ps.setDouble(4, fa.getPreco());
					ps.setDouble(5, fa.getTotal_preco());
					ps.setDate(6, ft.data_registo());
					ps.setString(7, ft.getHoraAtual());
					ps.execute();
					con.close();
				} catch (Exception e) {
					e.printStackTrace();
				}
		    }
		    
		    public void inserir_facturaAtrasoInternamentoProcedimento(FacturaAtrasos fa){
		    	String sql = "Insert into tblfacturatrasointprocedimento (FK_internamento,FK_procedimento,qtd,preco,preco_total,data_reg,hora_reg) value (?,?,?,?,?,?,?)";
		    	try {
					con = Conexao.getConexao();
					PreparedStatement ps = con.prepareStatement(sql);
					ps.setInt(1, 0);
					ps.setInt(2, fa.getFK_procedimento());
					ps.setInt(3, fa.getQuantidade());
					ps.setDouble(4, fa.getPreco());
					ps.setDouble(5, fa.getTotal_preco());
					ps.setDate(6, ft.data_registo());
					ps.setString(7, ft.getHoraAtual());
					ps.execute();
					con.close();
				} catch (Exception e) {
					e.printStackTrace();
				}
		    }
		    
		    public void inserir_facturaAtrasoInternamentoMedicamento(FacturaAtrasos fa){
		    	String sql = "Insert into tblfacturatrasointmedicamento (FK_internamento,FK_medicamento,qtd,preco,preco_total,data_reg,hora_reg) value (?,?,?,?,?,?,?)";
		    	try {
					con = Conexao.getConexao();
					PreparedStatement ps = con.prepareStatement(sql);
					ps.setInt(1, 0);
					ps.setInt(2, fa.getFK_medicamento());
					ps.setInt(3, fa.getQuantidade());
					ps.setDouble(4, fa.getPreco());
					ps.setDouble(5, fa.getTotal_preco());
					ps.setDate(6, ft.data_registo());
					ps.setString(7, ft.getHoraAtual());
					ps.execute();
					con.close();
				} catch (Exception e) {
					e.printStackTrace();
				}
		    }
		    
		    public void inserir_facturaAtrasoInternamentoMaterial(FacturaAtrasos fa){
		    	String sql = "Insert into tblfacturatrasointmedicamento (FK_internamento,FK_material,qtd,preco,preco_total,data_reg,hora_reg) value (?,?,?,?,?,?,?)";
		    	try {
					con = Conexao.getConexao();
					PreparedStatement ps = con.prepareStatement(sql);
					ps.setInt(1, 0);
					ps.setInt(2, fa.getFK_material());
					ps.setInt(3, fa.getQuantidade());
					ps.setDouble(4, fa.getPreco());
					ps.setDouble(5, fa.getTotal_preco());
					ps.setDate(6, ft.data_registo());
					ps.setString(7, ft.getHoraAtual());
					ps.execute();
					con.close();
				} catch (Exception e) {
					e.printStackTrace();
				}
		    }
		    
//		    Inserir dados na tabela facturadeisensaoPorIdade
			
			public void inserir_faturadeisensaoPorIdadeServicosGerais(Facturacao fun)
			{
				String sql = "INSERT INTO tblfacturaisencaoporidadeservicogerais (fk_factura, fk_servico, isen_idade_s_g_preco, isen_idade_s_g_qtd, isen_idade_s_g_taxa_perc, isen_idade_s_g_taxal_emkz, isen_idade_s_g_total_pago) VALUES (?,?,?,?,?,?,?)";
			    try {
					 
					 //1-tblfacturadeisensao
			    	con = Conexao.getConexao();
					 PreparedStatement cid = con.prepareStatement(sql);
					 cid.setInt(1, fun.getFK_factura());
					 cid.setInt(2, fun.getFK_servico());				
					 cid.setDouble(3, fun.getSg_mcaixa_preco());
					 cid.setInt(4, fun.getSg_mcaixa_qtd());
					 cid.setDouble(5, fun.getSg_mcaixa_taxa_perc());
					 cid.setDouble(6, fun.getSg_mcaixa_taxa_emkz());
					 cid.setDouble(7, fun.getSg_mcaixa_total());		 
					 cid.execute();
					 cid.close();
					 System.out.println("Cadastrado com sucesso...tblfacturaisencaoporidadeservicogerais"); 
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
			
			public void atualizaCreditoPaciente(int paciente, double valor){
				String sql = "Update tblcredito set credito = ?, data_docredito = ?,hora_docredito = ? where fk_paciente = ?";
				try {
					con = Conexao.getConexao();
					PreparedStatement ps = con.prepareStatement(sql);
					ps.setDouble(1,valor);
					ps.setDate(2, new Formatando().data_registo());
					ps.setString(3, new Formatando().getHoraAtual());
					ps.setInt(4, paciente);
					ps.execute();
					con.close();
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
}

