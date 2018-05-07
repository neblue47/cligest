package ao.co.cligest.dao;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

import ao.co.cligest.entidades.Exames;
import ao.co.cligest.entidades.Produtos;

public class LaboratorioAtendidosDAO {
	
	private Connection con;
//	------------------------------------------------------- Pacientes Consultas Atendidos Come�o
	public List<Exames> buscarPacienteConsultasAtendidos(){
	    List <Exames> lista = new ArrayList<Exames>();
			String sql = "SELECT NomeCompleto,FK_requisicao_exame,idade,genero,data_darecolha,sum(qtd_servicos)as qtd_servicos,FK_factura "
					   + "FROM vwpacientesatendidoslaboratorio where FK_provenienca_exame = 1 AND data_darecolha = curdate() group by FK_factura";
			try {
				 con = Conexao.getConexao();
				PreparedStatement preparador = con.prepareStatement(sql);
				 ResultSet rs = preparador.executeQuery();
				 while(rs.next())
				 {
					 Exames pac = new Exames();
					 pac.setFK_factura(rs.getInt("FK_factura"));
					 pac.setNomeCompleto(rs.getString("NomeCompleto"));
					 pac.setGenero(rs.getString("genero"));
					 pac.setQuantidade(rs.getInt("qtd_servicos"));
	    		     pac.setAno(rs.getInt("idade"));
	    		     Calendar data = Calendar.getInstance();
	 				 data.setTime(rs.getDate("data_darecolha"));
	 				 pac.setData_darecolha(data);
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
	
	public List<Exames> buscarPacienteConsultasAtendidosPorData(Date dia_in, Date dia_fim){
	    List <Exames> lista = new ArrayList<Exames>();
			String sql = "SELECT NomeCompleto,FK_requisicao_exame,idade,genero,data_darecolha,sum(qtd_servicos)as qtd_servicos,FK_factura "
					+ "FROM vwpacientesatendidoslaboratorio where data_darecolha between ? and ? group by FK_factura";
			try {
				 con = Conexao.getConexao();
				PreparedStatement preparador = con.prepareStatement(sql);
				preparador.setDate(1, dia_in);
				preparador.setDate(2, dia_fim);
				 ResultSet rs = preparador.executeQuery();
				 while(rs.next())
				 {
					 Exames pac = new Exames();
					 pac.setFK_factura(rs.getInt("FK_factura"));
					 pac.setNomeCompleto(rs.getString("NomeCompleto"));
					 pac.setGenero(rs.getString("genero"));
					 pac.setQuantidade(rs.getInt("qtd_servicos"));
	    		     pac.setAno(rs.getInt("idade"));
	    		     Calendar data = Calendar.getInstance();
	 				 data.setTime(rs.getDate("data_darecolha"));
	 				 pac.setData_darecolha(data);
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
	
	public List<Exames> buscarPacienteConsultasAtendidosUm(Date dia_in, Date dia_fim,int m){
		
		List <Exames> lista = new ArrayList<Exames>();
		String sql = "SELECT NomeCompleto,FK_requisicao_exame,idade,genero,data_darecolha,sum(qtd_servicos)as qtd_servicos,FK_factura "
				+ "FROM vwpacientesatendidoslaboratorio where data_darecolha between ? and ? and FK_grupo_analise_clinica = ? group by FK_factura";
		try {
			 con = Conexao.getConexao();
			PreparedStatement preparador = con.prepareStatement(sql);
			preparador.setDate(1, dia_in);
			preparador.setDate(2, dia_fim);
			preparador.setInt(3, m);
			 ResultSet rs = preparador.executeQuery();
			 while(rs.next())
			 {
				 Exames pac = new Exames();
				 pac.setFK_factura(rs.getInt("FK_factura"));
				 pac.setNomeCompleto(rs.getString("NomeCompleto"));
				 pac.setGenero(rs.getString("genero"));
				 pac.setQuantidade(rs.getInt("qtd_servicos"));
    		     pac.setAno(rs.getInt("idade"));
    		     Calendar data = Calendar.getInstance();
 				 data.setTime(rs.getDate("data_darecolha"));
 				 pac.setData_darecolha(data);
				 lista.add(pac);
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
		return lista;
	}
	
public List<Exames> buscarPacienteConsultasAtendidosDois(Date dia_in, Date dia_fim, int m, int x, int y){
		
		List <Exames> lista = new ArrayList<Exames>();
		String sql = "SELECT NomeCompleto,FK_requisicao_exame,idade,genero,data_darecolha,sum(qtd_servicos)as qtd_servicos,FK_factura "
				+ "FROM vwpacientesatendidoslaboratorio where data_darecolha between ? and ? and FK_grupo_analise_clinica = ? and idade between ? and ? group by FK_factura";
		try {
			 con = Conexao.getConexao();
			PreparedStatement preparador = con.prepareStatement(sql);
			preparador.setDate(1, dia_in);
			preparador.setDate(2, dia_fim);
			preparador.setInt(3, m);
			preparador.setInt(4, x);
			preparador.setInt(5, y);
			 ResultSet rs = preparador.executeQuery();
			 while(rs.next())
			 {
				 Exames pac = new Exames();
				 pac.setFK_factura(rs.getInt("FK_factura"));
				 pac.setNomeCompleto(rs.getString("NomeCompleto"));
				 pac.setGenero(rs.getString("genero"));
				 pac.setQuantidade(rs.getInt("qtd_servicos"));
    		     pac.setAno(rs.getInt("idade"));
    		     Calendar data = Calendar.getInstance();
 				 data.setTime(rs.getDate("data_darecolha"));
 				 pac.setData_darecolha(data);
				 lista.add(pac);
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
		return lista;
	}

public List<Exames> buscarPacienteConsultasAtendidosTres(Date dia_in, Date dia_fim, int m, int x, int y, int s){
	
	List <Exames> lista = new ArrayList<Exames>();
	String sql = "SELECT NomeCompleto,FK_requisicao_exame,idade,genero,data_darecolha,sum(qtd_servicos)as qtd_servicos,FK_factura "
			   + "FROM vwpacientesatendidoslaboratorio where data_darecolha between ? and ? and FK_grupo_analise_clinica = ? and idade between ? and ? and id_genero = ? group by FK_factura";
	try {
		 con = Conexao.getConexao();
		PreparedStatement preparador = con.prepareStatement(sql);
		preparador.setDate(1, dia_in);
		preparador.setDate(2, dia_fim);
		preparador.setInt(3, m);
		preparador.setInt(4, x);
		preparador.setInt(5, y);
		preparador.setInt(6, s);
		 ResultSet rs = preparador.executeQuery();
		 while(rs.next())
		 {
			 Exames pac = new Exames();
			 pac.setFK_factura(rs.getInt("FK_factura"));
			 pac.setNomeCompleto(rs.getString("NomeCompleto"));
			 pac.setGenero(rs.getString("genero"));
			 pac.setQuantidade(rs.getInt("qtd_servicos"));
		     pac.setAno(rs.getInt("idade"));
		     Calendar data = Calendar.getInstance();
				 data.setTime(rs.getDate("data_darecolha"));
				 pac.setData_darecolha(data);
			 lista.add(pac);
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
	return lista;
}

public List<Exames> buscarPacienteConsultasAtendidosQuarto(Date dia_in, Date dia_fim, int m, int x, int y, int s, int ex){
	
	List <Exames> lista = new ArrayList<Exames>();
	String sql = "SELECT NomeCompleto,FK_requisicao_exame,idade,genero,data_darecolha,sum(qtd_servicos)as qtd_servicos,FK_factura "
			   + "FROM vwpacientesatendidoslaboratorio where data_darecolha between ? and ? and FK_grupo_analise_clinica = ? and idade between ? and ? and id_genero = ? and id_servicodeanalise_clinica = ? group by FK_factura";
	try {
		 con = Conexao.getConexao();
		PreparedStatement preparador = con.prepareStatement(sql);
		preparador.setDate(1, dia_in);
		preparador.setDate(2, dia_fim);
		preparador.setInt(3, m);
		preparador.setInt(4, x);
		preparador.setInt(5, y);
		preparador.setInt(6, s);
		preparador.setInt(7, ex);
		 ResultSet rs = preparador.executeQuery();
		 while(rs.next())
		 {
			 Exames pac = new Exames();
			 pac.setFK_factura(rs.getInt("FK_factura"));
			 pac.setNomeCompleto(rs.getString("NomeCompleto"));
			 pac.setGenero(rs.getString("genero"));
			 pac.setQuantidade(rs.getInt("qtd_servicos"));
		     pac.setAno(rs.getInt("idade"));
		     Calendar data = Calendar.getInstance();
				 data.setTime(rs.getDate("data_darecolha"));
				 pac.setData_darecolha(data);
			 lista.add(pac);
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
	return lista;
}

public List<Exames> buscarPacienteConsultasAtendidosIdade(Date dia_in, Date dia_fim, int x, int y){
	
	List <Exames> lista = new ArrayList<Exames>();
	String sql = "SELECT NomeCompleto,FK_requisicao_exame,idade,genero,data_darecolha,sum(qtd_servicos)as qtd_servicos,FK_factura "
			   + "FROM vwpacientesatendidoslaboratorio where data_darecolha between ? and ? and idade between ? and ? group by FK_factura";
	try {
		 con = Conexao.getConexao();
		PreparedStatement preparador = con.prepareStatement(sql);
		preparador.setDate(1, dia_in);
		preparador.setDate(2, dia_fim);
		preparador.setInt(3, x);
		preparador.setInt(4, y);
		 ResultSet rs = preparador.executeQuery();
		 while(rs.next())
		 {
			 Exames pac = new Exames();
			 pac.setFK_factura(rs.getInt("FK_factura"));
			 pac.setNomeCompleto(rs.getString("NomeCompleto"));
			 pac.setGenero(rs.getString("genero"));
			 pac.setQuantidade(rs.getInt("qtd_servicos"));
		     pac.setAno(rs.getInt("idade"));
		     Calendar data = Calendar.getInstance();
				 data.setTime(rs.getDate("data_darecolha"));
				 pac.setData_darecolha(data);
			 lista.add(pac);
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
	return lista;
}
public List<Exames> buscarPacienteConsultasAtendidosIdadeUm(Date dia_in, Date dia_fim, int x, int y, int s){
	
	List <Exames> lista = new ArrayList<Exames>();
	String sql = "SELECT NomeCompleto,FK_requisicao_exame,idade,genero,data_darecolha,sum(qtd_servicos)as qtd_servicos,FK_factura "
			   + "FROM vwpacientesatendidoslaboratorio where data_darecolha between ? and ? and idade between ? and ? and id_genero = ? group by FK_factura";
	try {
		 con = Conexao.getConexao();
		PreparedStatement preparador = con.prepareStatement(sql);
		preparador.setDate(1, dia_in);
		preparador.setDate(2, dia_fim);
		preparador.setInt(3, x);
		preparador.setInt(4, y);
		preparador.setInt(5, s);
		 ResultSet rs = preparador.executeQuery();
		 while(rs.next())
		 {
			 Exames pac = new Exames();
			 pac.setFK_factura(rs.getInt("FK_factura"));
			 pac.setNomeCompleto(rs.getString("NomeCompleto"));
			 pac.setGenero(rs.getString("genero"));
			 pac.setQuantidade(rs.getInt("qtd_servicos"));
		     pac.setAno(rs.getInt("idade"));
		     Calendar data = Calendar.getInstance();
				 data.setTime(rs.getDate("data_darecolha"));
				 pac.setData_darecolha(data);
			 lista.add(pac);
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
	return lista;
}

public List<Exames> buscarPacienteConsultasAtendidosIdadeDois(Date dia_in, Date dia_fim, int x, int y, int s, int ex){
	
	List <Exames> lista = new ArrayList<Exames>();
	String sql = "SELECT NomeCompleto,FK_requisicao_exame,idade,genero,data_darecolha,sum(qtd_servicos)as qtd_servicos,FK_factura "
			   + "FROM vwpacientesatendidoslaboratorio where data_darecolha between ? and ? and idade between ? and ? and id_genero = ? and id_servicodeanalise_clinica = ? group by FK_factura";
	try {
		 con = Conexao.getConexao();
		PreparedStatement preparador = con.prepareStatement(sql);
		preparador.setDate(1, dia_in);
		preparador.setDate(2, dia_fim);
		preparador.setInt(3, x);
		preparador.setInt(4, y);
		preparador.setInt(5, ex);
		preparador.setInt(5, s);
		 ResultSet rs = preparador.executeQuery();
		 while(rs.next())
		 {
			 Exames pac = new Exames();
			 pac.setFK_factura(rs.getInt("FK_factura"));
			 pac.setNomeCompleto(rs.getString("NomeCompleto"));
			 pac.setGenero(rs.getString("genero"));
			 pac.setQuantidade(rs.getInt("qtd_servicos"));
		     pac.setAno(rs.getInt("idade"));
		     Calendar data = Calendar.getInstance();
				 data.setTime(rs.getDate("data_darecolha"));
				 pac.setData_darecolha(data);
			 lista.add(pac);
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
	return lista;
}

public List<Exames> buscarPacienteConsultasAtendidosGenero(Date dia_in, Date dia_fim, int s){
	
	List <Exames> lista = new ArrayList<Exames>();
	String sql = "SELECT NomeCompleto,FK_requisicao_exame,idade,genero,data_darecolha,sum(qtd_servicos)as qtd_servicos,FK_factura "
			   + "FROM vwpacientesatendidoslaboratorio where data_darecolha between ? and ? and id_genero = ? group by FK_factura";
	try {
		 con = Conexao.getConexao();
		PreparedStatement preparador = con.prepareStatement(sql);
		preparador.setDate(1, dia_in);
		preparador.setDate(2, dia_fim);
		preparador.setInt(3, s);
		 ResultSet rs = preparador.executeQuery();
		 while(rs.next())
		 {
			 Exames pac = new Exames();
			 pac.setFK_factura(rs.getInt("FK_factura"));
			 pac.setNomeCompleto(rs.getString("NomeCompleto"));
			 pac.setGenero(rs.getString("genero"));
			 pac.setQuantidade(rs.getInt("qtd_servicos"));
		     pac.setAno(rs.getInt("idade"));
		     Calendar data = Calendar.getInstance();
				 data.setTime(rs.getDate("data_darecolha"));
				 pac.setData_darecolha(data);
			 lista.add(pac);
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
	return lista;
}

public List<Exames> buscarPacienteConsultasAtendidosGeneroUm(Date dia_in, Date dia_fim, int s, int ex){
	
	List <Exames> lista = new ArrayList<Exames>();
	String sql = "SELECT NomeCompleto,FK_requisicao_exame,idade,genero,data_darecolha,sum(qtd_servicos)as qtd_servicos,FK_factura "
			   + "FROM vwpacientesatendidoslaboratorio where data_darecolha between ? and ? and id_genero = ? and id_servicodeanalise_clinica =? group by FK_factura";
	try {
		 con = Conexao.getConexao();
		PreparedStatement preparador = con.prepareStatement(sql);
		preparador.setDate(1, dia_in);
		preparador.setDate(2, dia_fim);
		preparador.setInt(3, s);
		preparador.setInt(4, ex);
		 ResultSet rs = preparador.executeQuery();
		 while(rs.next())
		 {
			 Exames pac = new Exames();
			 pac.setFK_factura(rs.getInt("FK_factura"));
			 pac.setNomeCompleto(rs.getString("NomeCompleto"));
			 pac.setGenero(rs.getString("genero"));
			 pac.setQuantidade(rs.getInt("qtd_servicos"));
		     pac.setAno(rs.getInt("idade"));
		     Calendar data = Calendar.getInstance();
				 data.setTime(rs.getDate("data_darecolha"));
				 pac.setData_darecolha(data);
			 lista.add(pac);
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
	return lista;
}

public List<Exames> buscarPacienteConsultasAtendidosExame(Date dia_in, Date dia_fim, int ex){
	
	List <Exames> lista = new ArrayList<Exames>();
	String sql = "SELECT NomeCompleto,FK_requisicao_exame,idade,genero,data_darecolha,sum(qtd_servicos)as qtd_servicos,FK_factura "
			   + "FROM vwpacientesatendidoslaboratorio where data_darecolha between ? and ? and id_servicodeanalise_clinica =? group by FK_factura";
	try {
		 con = Conexao.getConexao();
		PreparedStatement preparador = con.prepareStatement(sql);
		preparador.setDate(1, dia_in);
		preparador.setDate(2, dia_fim);
		preparador.setInt(3, ex);
		 ResultSet rs = preparador.executeQuery();
		 while(rs.next())
		 {
			 Exames pac = new Exames();
			 pac.setFK_factura(rs.getInt("FK_factura"));
			 pac.setNomeCompleto(rs.getString("NomeCompleto"));
			 pac.setGenero(rs.getString("genero"));
			 pac.setQuantidade(rs.getInt("qtd_servicos"));
		     pac.setAno(rs.getInt("idade"));
		     Calendar data = Calendar.getInstance();
				 data.setTime(rs.getDate("data_darecolha"));
				 pac.setData_darecolha(data);
			 lista.add(pac);
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
	return lista;
}

public List<Exames> buscarPacienteConsultasAtendidosExameUm(Date dia_in, Date dia_fim, int ex, int gr){
	
	List <Exames> lista = new ArrayList<Exames>();
	String sql = "SELECT NomeCompleto,FK_requisicao_exame,idade,genero,data_darecolha,sum(qtd_servicos)as qtd_servicos,FK_factura "
			   + "FROM vwpacientesatendidoslaboratorio where data_darecolha between ? and ? and id_servicodeanalise_clinica =? and FK_grupo_analise_clinica = ? group by FK_factura";
	try {
		 con = Conexao.getConexao();
		PreparedStatement preparador = con.prepareStatement(sql);
		preparador.setDate(1, dia_in);
		preparador.setDate(2, dia_fim);
		preparador.setInt(3, ex);
		preparador.setInt(4, gr);
		 ResultSet rs = preparador.executeQuery();
		 while(rs.next())
		 {
			 Exames pac = new Exames();
			 pac.setFK_factura(rs.getInt("FK_factura"));
			 pac.setNomeCompleto(rs.getString("NomeCompleto"));
			 pac.setGenero(rs.getString("genero"));
			 pac.setQuantidade(rs.getInt("qtd_servicos"));
		     pac.setAno(rs.getInt("idade"));
		     Calendar data = Calendar.getInstance();
				 data.setTime(rs.getDate("data_darecolha"));
				 pac.setData_darecolha(data);
			 lista.add(pac);
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
	return lista;
}

public List<Exames> buscarPacienteConsultasAtendidosExameDois(Date dia_in, Date dia_fim, int ex, int gr, int gnr){
	
	List <Exames> lista = new ArrayList<Exames>();
	String sql = "SELECT NomeCompleto,FK_requisicao_exame,idade,genero,data_darecolha,sum(qtd_servicos)as qtd_servicos,FK_factura "
			   + "FROM vwpacientesatendidoslaboratorio where data_darecolha between ? and ? and id_servicodeanalise_clinica =? and FK_grupo_analise_clinica = ? and id_genero = ? group by FK_factura";
	try {
		 con = Conexao.getConexao();
		PreparedStatement preparador = con.prepareStatement(sql);
		preparador.setDate(1, dia_in);
		preparador.setDate(2, dia_fim);
		preparador.setInt(3, ex);
		preparador.setInt(4, gr);
		preparador.setInt(5, gnr);
		 ResultSet rs = preparador.executeQuery();
		 while(rs.next())
		 {
			 Exames pac = new Exames();
			 pac.setFK_factura(rs.getInt("FK_factura"));
			 pac.setNomeCompleto(rs.getString("NomeCompleto"));
			 pac.setGenero(rs.getString("genero"));
			 pac.setQuantidade(rs.getInt("qtd_servicos"));
		     pac.setAno(rs.getInt("idade"));
		     Calendar data = Calendar.getInstance();
				 data.setTime(rs.getDate("data_darecolha"));
				 pac.setData_darecolha(data);
			 lista.add(pac);
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
	return lista;
}

public List<Exames> buscarPacienteConsultasAtendidosExameTres(Date dia_in, Date dia_fim, int ex, int gr, int x, int y){
	
	List <Exames> lista = new ArrayList<Exames>();
	String sql = "SELECT NomeCompleto,FK_requisicao_exame,idade,genero,data_darecolha,sum(qtd_servicos)as qtd_servicos,FK_factura "
			   + "FROM vwpacientesatendidoslaboratorio where data_darecolha between ? and ? and id_servicodeanalise_clinica =? and FK_grupo_analise_clinica = ? and idade between ? and ? group by FK_factura";
	try {
		 con = Conexao.getConexao();
		PreparedStatement preparador = con.prepareStatement(sql);
		preparador.setDate(1, dia_in);
		preparador.setDate(2, dia_fim);
		preparador.setInt(3, ex);
		preparador.setInt(4, gr);
		preparador.setInt(5, x);
		preparador.setInt(6, y);
		 ResultSet rs = preparador.executeQuery();
		 while(rs.next())
		 {
			 Exames pac = new Exames();
			 pac.setFK_factura(rs.getInt("FK_factura"));
			 pac.setNomeCompleto(rs.getString("NomeCompleto"));
			 pac.setGenero(rs.getString("genero"));
			 pac.setQuantidade(rs.getInt("qtd_servicos"));
		     pac.setAno(rs.getInt("idade"));
		     Calendar data = Calendar.getInstance();
				 data.setTime(rs.getDate("data_darecolha"));
				 pac.setData_darecolha(data);
			 lista.add(pac);
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
	return lista;
}

public List<Exames> buscarPacienteConsultasAtendidosExameQuarto(Date dia_in, Date dia_fim, int ex, int x, int y){
	
	List <Exames> lista = new ArrayList<Exames>();
	String sql = "SELECT NomeCompleto,FK_requisicao_exame,idade,genero,data_darecolha,sum(qtd_servicos)as qtd_servicos,FK_factura "
			   + "FROM vwpacientesatendidoslaboratorio where data_darecolha between ? and ? and id_servicodeanalise_clinica =? and idade between ? and ? group by FK_factura";
	try {
		 con = Conexao.getConexao();
		PreparedStatement preparador = con.prepareStatement(sql);
		preparador.setDate(1, dia_in);
		preparador.setDate(2, dia_fim);
		preparador.setInt(3, ex);
		preparador.setInt(4, x);
		preparador.setInt(5, y);
		 ResultSet rs = preparador.executeQuery();
		 while(rs.next())
		 {
			 Exames pac = new Exames();
			 pac.setFK_factura(rs.getInt("FK_factura"));
			 pac.setNomeCompleto(rs.getString("NomeCompleto"));
			 pac.setGenero(rs.getString("genero"));
			 pac.setQuantidade(rs.getInt("qtd_servicos"));
		     pac.setAno(rs.getInt("idade"));
		     Calendar data = Calendar.getInstance();
				 data.setTime(rs.getDate("data_darecolha"));
				 pac.setData_darecolha(data);
			 lista.add(pac);
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
	return lista;
}


//	------------------------------------------------------- Paciente Consultas Atendidos Fim

//------------------------------------------------------- Paciente Consultas Externa Inicio

public List<Exames> buscarPacienteConsultasExternaAtendidos(){
    List <Exames> lista = new ArrayList<Exames>();
		String sql = "SELECT NomeCompleto,FK_requisicao_exame,idade,genero,data_darecolha,sum(qtd_servicos)as qtd_servicos,FK_factura "
				   + "FROM vwpacientesatendidoslaboratorio where FK_provenienca_exame = 1 AND data_darecolha = curdate() group by FK_factura";
		try {
			 con = Conexao.getConexao();
			PreparedStatement preparador = con.prepareStatement(sql);
			 ResultSet rs = preparador.executeQuery();
			 while(rs.next())
			 {
				 Exames pac = new Exames();
				 pac.setFK_factura(rs.getInt("FK_factura"));
				 pac.setNomeCompleto(rs.getString("NomeCompleto"));
				 pac.setGenero(rs.getString("genero"));
				 pac.setQuantidade(rs.getInt("qtd_servicos"));
    		     pac.setAno(rs.getInt("idade"));
    		     Calendar data = Calendar.getInstance();
 				 data.setTime(rs.getDate("data_darecolha"));
 				 pac.setData_darecolha(data);
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

public List<Exames> buscarPacienteConsultasExternaAtendidosPorData(Date dia_in, Date dia_fim){
    List <Exames> lista = new ArrayList<Exames>();
		String sql = "SELECT NomeCompleto,FK_requisicao_exame,idade,genero,data_darecolha,sum(qtd_servicos)as qtd_servicos,FK_factura "
				+ "FROM vwpacientesatendidoslaboratorio where data_darecolha between ? and ? group by FK_factura";
		try {
			 con = Conexao.getConexao();
			PreparedStatement preparador = con.prepareStatement(sql);
			preparador.setDate(1, dia_in);
			preparador.setDate(2, dia_fim);
			 ResultSet rs = preparador.executeQuery();
			 while(rs.next())
			 {
				 Exames pac = new Exames();
				 pac.setFK_factura(rs.getInt("FK_factura"));
				 pac.setNomeCompleto(rs.getString("NomeCompleto"));
				 pac.setGenero(rs.getString("genero"));
				 pac.setQuantidade(rs.getInt("qtd_servicos"));
    		     pac.setAno(rs.getInt("idade"));
    		     Calendar data = Calendar.getInstance();
 				 data.setTime(rs.getDate("data_darecolha"));
 				 pac.setData_darecolha(data);
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

public List<Exames> buscarPacienteConsultasExternaAtendidosUm(Date dia_in, Date dia_fim,int m){
	
	List <Exames> lista = new ArrayList<Exames>();
	String sql = "SELECT NomeCompleto,FK_requisicao_exame,idade,genero,data_darecolha,sum(qtd_servicos)as qtd_servicos,FK_factura "
			+ "FROM vwpacientesatendidoslaboratorio where data_darecolha between ? and ? and FK_grupo_analise_clinica = ? group by FK_factura";
	try {
		 con = Conexao.getConexao();
		PreparedStatement preparador = con.prepareStatement(sql);
		preparador.setDate(1, dia_in);
		preparador.setDate(2, dia_fim);
		preparador.setInt(3, m);
		 ResultSet rs = preparador.executeQuery();
		 while(rs.next())
		 {
			 Exames pac = new Exames();
			 pac.setFK_factura(rs.getInt("FK_factura"));
			 pac.setNomeCompleto(rs.getString("NomeCompleto"));
			 pac.setGenero(rs.getString("genero"));
			 pac.setQuantidade(rs.getInt("qtd_servicos"));
		     pac.setAno(rs.getInt("idade"));
		     Calendar data = Calendar.getInstance();
				 data.setTime(rs.getDate("data_darecolha"));
				 pac.setData_darecolha(data);
			 lista.add(pac);
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
	return lista;
}

public List<Exames> buscarPacienteConsultasExternaAtendidosDois(Date dia_in, Date dia_fim, int m, int x, int y){
	
	List <Exames> lista = new ArrayList<Exames>();
	String sql = "SELECT NomeCompleto,FK_requisicao_exame,idade,genero,data_darecolha,sum(qtd_servicos)as qtd_servicos,FK_factura "
			+ "FROM vwpacientesatendidoslaboratorio where data_darecolha between ? and ? and FK_grupo_analise_clinica = ? and idade between ? and ? group by FK_factura";
	try {
		 con = Conexao.getConexao();
		PreparedStatement preparador = con.prepareStatement(sql);
		preparador.setDate(1, dia_in);
		preparador.setDate(2, dia_fim);
		preparador.setInt(3, m);
		preparador.setInt(4, x);
		preparador.setInt(5, y);
		 ResultSet rs = preparador.executeQuery();
		 while(rs.next())
		 {
			 Exames pac = new Exames();
			 pac.setFK_factura(rs.getInt("FK_factura"));
			 pac.setNomeCompleto(rs.getString("NomeCompleto"));
			 pac.setGenero(rs.getString("genero"));
			 pac.setQuantidade(rs.getInt("qtd_servicos"));
		     pac.setAno(rs.getInt("idade"));
		     Calendar data = Calendar.getInstance();
				 data.setTime(rs.getDate("data_darecolha"));
				 pac.setData_darecolha(data);
			 lista.add(pac);
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
	return lista;
}

public List<Exames> buscarPacienteConsultasExternaAtendidosTres(Date dia_in, Date dia_fim, int m, int x, int y, int s){

List <Exames> lista = new ArrayList<Exames>();
String sql = "SELECT NomeCompleto,FK_requisicao_exame,idade,genero,data_darecolha,sum(qtd_servicos)as qtd_servicos,FK_factura "
		   + "FROM vwpacientesatendidoslaboratorio where data_darecolha between ? and ? and FK_grupo_analise_clinica = ? and idade between ? and ? and id_genero = ? group by FK_factura";
System.out.println("Data inicio: "+dia_in);
System.out.println("Data final: "+dia_fim);
System.out.println(" grupo: "+m);
System.out.println(" idade1: "+x);
System.out.println(" idade2: "+y);
System.out.println(" genero: "+s);
try {
	 con = Conexao.getConexao();
	PreparedStatement preparador = con.prepareStatement(sql);
	preparador.setDate(1, dia_in);
	preparador.setDate(2, dia_fim);
	preparador.setInt(3, m);
	preparador.setInt(4, x);
	preparador.setInt(5, y);
	preparador.setInt(6, s);
	 ResultSet rs = preparador.executeQuery();
	 while(rs.next())
	 {
		 Exames pac = new Exames();
		 pac.setFK_factura(rs.getInt("FK_factura"));
		 pac.setNomeCompleto(rs.getString("NomeCompleto"));
		 pac.setGenero(rs.getString("genero"));
		 pac.setQuantidade(rs.getInt("qtd_servicos"));
	     pac.setAno(rs.getInt("idade"));
	     Calendar data = Calendar.getInstance();
			 data.setTime(rs.getDate("data_darecolha"));
			 pac.setData_darecolha(data);
		 lista.add(pac);
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
return lista;
}

public List<Exames> buscarPacienteConsultasExternaAtendidosQuarto(Date dia_in, Date dia_fim, int m, int x, int y, int s, int ex){

List <Exames> lista = new ArrayList<Exames>();
String sql = "SELECT NomeCompleto,FK_requisicao_exame,idade,genero,data_darecolha,sum(qtd_servicos)as qtd_servicos,FK_factura "
		   + "FROM vwpacientesatendidoslaboratorio where data_darecolha between ? and ? and FK_grupo_analise_clinica = ? and idade between ? and ? and id_genero = ? and id_servicodeanalise_clinica = ? group by FK_factura";
try {
	 con = Conexao.getConexao();
	PreparedStatement preparador = con.prepareStatement(sql);
	preparador.setDate(1, dia_in);
	preparador.setDate(2, dia_fim);
	preparador.setInt(3, m);
	preparador.setInt(4, x);
	preparador.setInt(5, y);
	preparador.setInt(6, s);
	preparador.setInt(7, ex);
	 ResultSet rs = preparador.executeQuery();
	 while(rs.next())
	 {
		 Exames pac = new Exames();
		 pac.setFK_factura(rs.getInt("FK_factura"));
		 pac.setNomeCompleto(rs.getString("NomeCompleto"));
		 pac.setGenero(rs.getString("genero"));
		 pac.setQuantidade(rs.getInt("qtd_servicos"));
	     pac.setAno(rs.getInt("idade"));
	     Calendar data = Calendar.getInstance();
			 data.setTime(rs.getDate("data_darecolha"));
			 pac.setData_darecolha(data);
		 lista.add(pac);
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
return lista;
}

public List<Exames> buscarPacienteConsultasExternaAtendidosIdade(Date dia_in, Date dia_fim, int x, int y){

List <Exames> lista = new ArrayList<Exames>();
String sql = "SELECT NomeCompleto,FK_requisicao_exame,idade,genero,data_darecolha,sum(qtd_servicos)as qtd_servicos,FK_factura "
		   + "FROM vwpacientesatendidoslaboratorio where data_darecolha between ? and ? and idade between ? and ? group by FK_factura";
try {
	 con = Conexao.getConexao();
	PreparedStatement preparador = con.prepareStatement(sql);
	preparador.setDate(1, dia_in);
	preparador.setDate(2, dia_fim);
	preparador.setInt(3, x);
	preparador.setInt(4, y);
	 ResultSet rs = preparador.executeQuery();
	 while(rs.next())
	 {
		 Exames pac = new Exames();
		 pac.setFK_factura(rs.getInt("FK_factura"));
		 pac.setNomeCompleto(rs.getString("NomeCompleto"));
		 pac.setGenero(rs.getString("genero"));
		 pac.setQuantidade(rs.getInt("qtd_servicos"));
	     pac.setAno(rs.getInt("idade"));
	     Calendar data = Calendar.getInstance();
			 data.setTime(rs.getDate("data_darecolha"));
			 pac.setData_darecolha(data);
		 lista.add(pac);
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
return lista;
}
public List<Exames> buscarPacienteConsultasExternaAtendidosIdadeUm(Date dia_in, Date dia_fim, int x, int y, int s){

List <Exames> lista = new ArrayList<Exames>();
String sql = "SELECT NomeCompleto,FK_requisicao_exame,idade,genero,data_darecolha,sum(qtd_servicos)as qtd_servicos,FK_factura "
		   + "FROM vwpacientesatendidoslaboratorio where data_darecolha between ? and ? and idade between ? and ? and id_genero = ? group by FK_factura";
try {
	 con = Conexao.getConexao();
	PreparedStatement preparador = con.prepareStatement(sql);
	preparador.setDate(1, dia_in);
	preparador.setDate(2, dia_fim);
	preparador.setInt(3, x);
	preparador.setInt(4, y);
	preparador.setInt(5, s);
	 ResultSet rs = preparador.executeQuery();
	 while(rs.next())
	 {
		 Exames pac = new Exames();
		 pac.setFK_factura(rs.getInt("FK_factura"));
		 pac.setNomeCompleto(rs.getString("NomeCompleto"));
		 pac.setGenero(rs.getString("genero"));
		 pac.setQuantidade(rs.getInt("qtd_servicos"));
	     pac.setAno(rs.getInt("idade"));
	     Calendar data = Calendar.getInstance();
			 data.setTime(rs.getDate("data_darecolha"));
			 pac.setData_darecolha(data);
		 lista.add(pac);
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
return lista;
}

public List<Exames> buscarPacienteConsultasExternaAtendidosIdadeDois(Date dia_in, Date dia_fim, int x, int y, int s, int ex){

List <Exames> lista = new ArrayList<Exames>();
String sql = "SELECT NomeCompleto,FK_requisicao_exame,idade,genero,data_darecolha,sum(qtd_servicos)as qtd_servicos,FK_factura "
		   + "FROM vwpacientesatendidoslaboratorio where data_darecolha between ? and ? and idade between ? and ? and id_genero = ? and id_servicodeanalise_clinica = ? group by FK_factura";
try {
	 con = Conexao.getConexao();
	PreparedStatement preparador = con.prepareStatement(sql);
	preparador.setDate(1, dia_in);
	preparador.setDate(2, dia_fim);
	preparador.setInt(3, x);
	preparador.setInt(4, y);
	preparador.setInt(5, ex);
	preparador.setInt(5, s);
	 ResultSet rs = preparador.executeQuery();
	 while(rs.next())
	 {
		 Exames pac = new Exames();
		 pac.setFK_factura(rs.getInt("FK_factura"));
		 pac.setNomeCompleto(rs.getString("NomeCompleto"));
		 pac.setGenero(rs.getString("genero"));
		 pac.setQuantidade(rs.getInt("qtd_servicos"));
	     pac.setAno(rs.getInt("idade"));
	     Calendar data = Calendar.getInstance();
			 data.setTime(rs.getDate("data_darecolha"));
			 pac.setData_darecolha(data);
		 lista.add(pac);
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
return lista;
}

public List<Exames> buscarPacienteConsultasExternaAtendidosGenero(Date dia_in, Date dia_fim, int s){

List <Exames> lista = new ArrayList<Exames>();
String sql = "SELECT NomeCompleto,FK_requisicao_exame,idade,genero,data_darecolha,sum(qtd_servicos)as qtd_servicos,FK_factura "
		   + "FROM vwpacientesatendidoslaboratorio where data_darecolha between ? and ? and id_genero = ? group by FK_factura";
try {
	 con = Conexao.getConexao();
	PreparedStatement preparador = con.prepareStatement(sql);
	preparador.setDate(1, dia_in);
	preparador.setDate(2, dia_fim);
	preparador.setInt(3, s);
	 ResultSet rs = preparador.executeQuery();
	 while(rs.next())
	 {
		 Exames pac = new Exames();
		 pac.setFK_factura(rs.getInt("FK_factura"));
		 pac.setNomeCompleto(rs.getString("NomeCompleto"));
		 pac.setGenero(rs.getString("genero"));
		 pac.setQuantidade(rs.getInt("qtd_servicos"));
	     pac.setAno(rs.getInt("idade"));
	     Calendar data = Calendar.getInstance();
			 data.setTime(rs.getDate("data_darecolha"));
			 pac.setData_darecolha(data);
		 lista.add(pac);
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
return lista;
}

public List<Exames> buscarPacienteConsultasExternaAtendidosGeneroUm(Date dia_in, Date dia_fim, int s, int ex){

List <Exames> lista = new ArrayList<Exames>();
String sql = "SELECT NomeCompleto,FK_requisicao_exame,idade,genero,data_darecolha,sum(qtd_servicos)as qtd_servicos,FK_factura "
		   + "FROM vwpacientesatendidoslaboratorio where data_darecolha between ? and ? and id_genero = ? and id_servicodeanalise_clinica =? group by FK_factura";
try {
	 con = Conexao.getConexao();
	PreparedStatement preparador = con.prepareStatement(sql);
	preparador.setDate(1, dia_in);
	preparador.setDate(2, dia_fim);
	preparador.setInt(3, s);
	preparador.setInt(4, ex);
	 ResultSet rs = preparador.executeQuery();
	 while(rs.next())
	 {
		 Exames pac = new Exames();
		 pac.setFK_factura(rs.getInt("FK_factura"));
		 pac.setNomeCompleto(rs.getString("NomeCompleto"));
		 pac.setGenero(rs.getString("genero"));
		 pac.setQuantidade(rs.getInt("qtd_servicos"));
	     pac.setAno(rs.getInt("idade"));
	     Calendar data = Calendar.getInstance();
			 data.setTime(rs.getDate("data_darecolha"));
			 pac.setData_darecolha(data);
		 lista.add(pac);
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
return lista;
}

public List<Exames> buscarPacienteConsultasExternaAtendidosExame(Date dia_in, Date dia_fim, int ex){

List <Exames> lista = new ArrayList<Exames>();
String sql = "SELECT NomeCompleto,FK_requisicao_exame,idade,genero,data_darecolha,sum(qtd_servicos)as qtd_servicos,FK_factura "
		   + "FROM vwpacientesatendidoslaboratorio where data_darecolha between ? and ? and id_servicodeanalise_clinica =? group by FK_factura";
try {
	 con = Conexao.getConexao();
	PreparedStatement preparador = con.prepareStatement(sql);
	preparador.setDate(1, dia_in);
	preparador.setDate(2, dia_fim);
	preparador.setInt(3, ex);
	 ResultSet rs = preparador.executeQuery();
	 while(rs.next())
	 {
		 Exames pac = new Exames();
		 pac.setFK_factura(rs.getInt("FK_factura"));
		 pac.setNomeCompleto(rs.getString("NomeCompleto"));
		 pac.setGenero(rs.getString("genero"));
		 pac.setQuantidade(rs.getInt("qtd_servicos"));
	     pac.setAno(rs.getInt("idade"));
	     Calendar data = Calendar.getInstance();
			 data.setTime(rs.getDate("data_darecolha"));
			 pac.setData_darecolha(data);
		 lista.add(pac);
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
return lista;
}

public List<Exames> buscarPacienteConsultasExternaAtendidosExameUm(Date dia_in, Date dia_fim, int ex, int gr){

List <Exames> lista = new ArrayList<Exames>();
String sql = "SELECT NomeCompleto,FK_requisicao_exame,idade,genero,data_darecolha,sum(qtd_servicos)as qtd_servicos,FK_factura "
		   + "FROM vwpacientesatendidoslaboratorio where data_darecolha between ? and ? and id_servicodeanalise_clinica =? and FK_grupo_analise_clinica = ? group by FK_factura";
try {
	 con = Conexao.getConexao();
	PreparedStatement preparador = con.prepareStatement(sql);
	preparador.setDate(1, dia_in);
	preparador.setDate(2, dia_fim);
	preparador.setInt(3, ex);
	preparador.setInt(4, gr);
	 ResultSet rs = preparador.executeQuery();
	 while(rs.next())
	 {
		 Exames pac = new Exames();
		 pac.setFK_factura(rs.getInt("FK_factura"));
		 pac.setNomeCompleto(rs.getString("NomeCompleto"));
		 pac.setGenero(rs.getString("genero"));
		 pac.setQuantidade(rs.getInt("qtd_servicos"));
	     pac.setAno(rs.getInt("idade"));
	     Calendar data = Calendar.getInstance();
			 data.setTime(rs.getDate("data_darecolha"));
			 pac.setData_darecolha(data);
		 lista.add(pac);
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
return lista;
}

public List<Exames> buscarPacienteConsultasExternaAtendidosExameDois(Date dia_in, Date dia_fim, int ex, int gr, int gnr){

List <Exames> lista = new ArrayList<Exames>();
String sql = "SELECT NomeCompleto,FK_requisicao_exame,idade,genero,data_darecolha,sum(qtd_servicos)as qtd_servicos,FK_factura "
		   + "FROM vwpacientesatendidoslaboratorio where data_darecolha between ? and ? and id_servicodeanalise_clinica =? and FK_grupo_analise_clinica = ? and id_genero = ? group by FK_factura";
try {
	 con = Conexao.getConexao();
	PreparedStatement preparador = con.prepareStatement(sql);
	preparador.setDate(1, dia_in);
	preparador.setDate(2, dia_fim);
	preparador.setInt(3, ex);
	preparador.setInt(4, gr);
	preparador.setInt(5, gnr);
	 ResultSet rs = preparador.executeQuery();
	 while(rs.next())
	 {
		 Exames pac = new Exames();
		 pac.setFK_factura(rs.getInt("FK_factura"));
		 pac.setNomeCompleto(rs.getString("NomeCompleto"));
		 pac.setGenero(rs.getString("genero"));
		 pac.setQuantidade(rs.getInt("qtd_servicos"));
	     pac.setAno(rs.getInt("idade"));
	     Calendar data = Calendar.getInstance();
			 data.setTime(rs.getDate("data_darecolha"));
			 pac.setData_darecolha(data);
		 lista.add(pac);
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
return lista;
}

public List<Exames> buscarPacienteConsultasExternaAtendidosExameTres(Date dia_in, Date dia_fim, int ex, int gr, int x, int y){

List <Exames> lista = new ArrayList<Exames>();
String sql = "SELECT NomeCompleto,FK_requisicao_exame,idade,genero,data_darecolha,sum(qtd_servicos)as qtd_servicos,FK_factura "
		   + "FROM vwpacientesatendidoslaboratorio where data_darecolha between ? and ? and id_servicodeanalise_clinica =? and FK_grupo_analise_clinica = ? and idade between ? and ? group by FK_factura";
try {
	 con = Conexao.getConexao();
	PreparedStatement preparador = con.prepareStatement(sql);
	preparador.setDate(1, dia_in);
	preparador.setDate(2, dia_fim);
	preparador.setInt(3, ex);
	preparador.setInt(4, gr);
	preparador.setInt(5, x);
	preparador.setInt(6, y);
	 ResultSet rs = preparador.executeQuery();
	 while(rs.next())
	 {
		 Exames pac = new Exames();
		 pac.setFK_factura(rs.getInt("FK_factura"));
		 pac.setNomeCompleto(rs.getString("NomeCompleto"));
		 pac.setGenero(rs.getString("genero"));
		 pac.setQuantidade(rs.getInt("qtd_servicos"));
	     pac.setAno(rs.getInt("idade"));
	     Calendar data = Calendar.getInstance();
			 data.setTime(rs.getDate("data_darecolha"));
			 pac.setData_darecolha(data);
		 lista.add(pac);
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
return lista;
}

public List<Exames> buscarPacienteConsultasExternaAtendidosExameQuarto(Date dia_in, Date dia_fim, int ex, int x, int y){

List <Exames> lista = new ArrayList<Exames>();
String sql = "SELECT NomeCompleto,FK_requisicao_exame,idade,genero,data_darecolha,sum(qtd_servicos)as qtd_servicos,FK_factura "
		   + "FROM vwpacientesatendidoslaboratorio where data_darecolha between ? and ? and id_servicodeanalise_clinica =? and idade between ? and ? group by FK_factura";
try {
	 con = Conexao.getConexao();
	PreparedStatement preparador = con.prepareStatement(sql);
	preparador.setDate(1, dia_in);
	preparador.setDate(2, dia_fim);
	preparador.setInt(3, ex);
	preparador.setInt(4, x);
	preparador.setInt(5, y);
	 ResultSet rs = preparador.executeQuery();
	 while(rs.next())
	 {
		 Exames pac = new Exames();
		 pac.setFK_factura(rs.getInt("FK_factura"));
		 pac.setNomeCompleto(rs.getString("NomeCompleto"));
		 pac.setGenero(rs.getString("genero"));
		 pac.setQuantidade(rs.getInt("qtd_servicos"));
	     pac.setAno(rs.getInt("idade"));
	     Calendar data = Calendar.getInstance();
			 data.setTime(rs.getDate("data_darecolha"));
			 pac.setData_darecolha(data);
		 lista.add(pac);
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
return lista;
}


//------------------------------------------------------- Paciente Consultas Externa Fim

//--------------------------------------------------------- Paciente Internados come�o
public List<Exames> buscarPacienteConsultasInternadosAtendidos(){
    List <Exames> lista = new ArrayList<Exames>();
		String sql = "SELECT NomeCompleto,FK_requisicao_exame,idade,genero,data_darecolha,sum(qtd_servicos)as qtd_servicos,FK_factura "
				   + "FROM vwpacientesatendidoslaboratorio where FK_provenienca_exame = 1 AND data_darecolha = curdate() group by FK_factura";
		try {
			 con = Conexao.getConexao();
			PreparedStatement preparador = con.prepareStatement(sql);
			 ResultSet rs = preparador.executeQuery();
			 while(rs.next())
			 {
				 Exames pac = new Exames();
				 pac.setFK_factura(rs.getInt("FK_factura"));
				 pac.setNomeCompleto(rs.getString("NomeCompleto"));
				 pac.setGenero(rs.getString("genero"));
				 pac.setQuantidade(rs.getInt("qtd_servicos"));
    		     pac.setAno(rs.getInt("idade"));
    		     Calendar data = Calendar.getInstance();
 				 data.setTime(rs.getDate("data_darecolha"));
 				 pac.setData_darecolha(data);
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

public List<Exames> buscarPacienteConsultasInternadosAtendidosPorData(Date dia_in, Date dia_fim){
    List <Exames> lista = new ArrayList<Exames>();
		String sql = "SELECT NomeCompleto,FK_requisicao_exame,idade,genero,data_darecolha,sum(qtd_servicos)as qtd_servicos,FK_factura "
				+ "FROM vwpacientesatendidoslaboratorio where data_darecolha between ? and ? group by FK_factura";
		try {
			 con = Conexao.getConexao();
			PreparedStatement preparador = con.prepareStatement(sql);
			preparador.setDate(1, dia_in);
			preparador.setDate(2, dia_fim);
			 ResultSet rs = preparador.executeQuery();
			 while(rs.next())
			 {
				 Exames pac = new Exames();
				 pac.setFK_factura(rs.getInt("FK_factura"));
				 pac.setNomeCompleto(rs.getString("NomeCompleto"));
				 pac.setGenero(rs.getString("genero"));
				 pac.setQuantidade(rs.getInt("qtd_servicos"));
    		     pac.setAno(rs.getInt("idade"));
    		     Calendar data = Calendar.getInstance();
 				 data.setTime(rs.getDate("data_darecolha"));
 				 pac.setData_darecolha(data);
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

public List<Exames> buscarPacienteConsultasInternadosAtendidosUm(Date dia_in, Date dia_fim,int m){
	
	List <Exames> lista = new ArrayList<Exames>();
	String sql = "SELECT NomeCompleto,FK_requisicao_exame,idade,genero,data_darecolha,sum(qtd_servicos)as qtd_servicos,FK_factura "
			+ "FROM vwpacientesatendidoslaboratorio where data_darecolha between ? and ? and FK_grupo_analise_clinica = ? group by FK_factura";
	try {
		 con = Conexao.getConexao();
		PreparedStatement preparador = con.prepareStatement(sql);
		preparador.setDate(1, dia_in);
		preparador.setDate(2, dia_fim);
		preparador.setInt(3, m);
		 ResultSet rs = preparador.executeQuery();
		 while(rs.next())
		 {
			 Exames pac = new Exames();
			 pac.setFK_factura(rs.getInt("FK_factura"));
			 pac.setNomeCompleto(rs.getString("NomeCompleto"));
			 pac.setGenero(rs.getString("genero"));
			 pac.setQuantidade(rs.getInt("qtd_servicos"));
		     pac.setAno(rs.getInt("idade"));
		     Calendar data = Calendar.getInstance();
				 data.setTime(rs.getDate("data_darecolha"));
				 pac.setData_darecolha(data);
			 lista.add(pac);
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
	return lista;
}

public List<Exames> buscarPacienteConsultasInternadosAtendidosDois(Date dia_in, Date dia_fim, int m, int x, int y){
	
	List <Exames> lista = new ArrayList<Exames>();
	String sql = "SELECT NomeCompleto,FK_requisicao_exame,idade,genero,data_darecolha,sum(qtd_servicos)as qtd_servicos,FK_factura "
			+ "FROM vwpacientesatendidoslaboratorio where data_darecolha between ? and ? and FK_grupo_analise_clinica = ? and idade between ? and ? group by FK_factura";
	try {
		 con = Conexao.getConexao();
		PreparedStatement preparador = con.prepareStatement(sql);
		preparador.setDate(1, dia_in);
		preparador.setDate(2, dia_fim);
		preparador.setInt(3, m);
		preparador.setInt(4, x);
		preparador.setInt(5, y);
		 ResultSet rs = preparador.executeQuery();
		 while(rs.next())
		 {
			 Exames pac = new Exames();
			 pac.setFK_factura(rs.getInt("FK_factura"));
			 pac.setNomeCompleto(rs.getString("NomeCompleto"));
			 pac.setGenero(rs.getString("genero"));
			 pac.setQuantidade(rs.getInt("qtd_servicos"));
		     pac.setAno(rs.getInt("idade"));
		     Calendar data = Calendar.getInstance();
				 data.setTime(rs.getDate("data_darecolha"));
				 pac.setData_darecolha(data);
			 lista.add(pac);
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
	return lista;
}

public List<Exames> buscarPacienteConsultasInternadosAtendidosTres(Date dia_in, Date dia_fim, int m, int x, int y, int s){

List <Exames> lista = new ArrayList<Exames>();
String sql = "SELECT NomeCompleto,FK_requisicao_exame,idade,genero,data_darecolha,sum(qtd_servicos)as qtd_servicos,FK_factura "
		   + "FROM vwpacientesatendidoslaboratorio where data_darecolha between ? and ? and FK_grupo_analise_clinica = ? and idade between ? and ? and id_genero = ? group by FK_factura";
System.out.println("Data inicio: "+dia_in);
System.out.println("Data final: "+dia_fim);
System.out.println(" grupo: "+m);
System.out.println(" idade1: "+x);
System.out.println(" idade2: "+y);
System.out.println(" genero: "+s);
try {
	 con = Conexao.getConexao();
	PreparedStatement preparador = con.prepareStatement(sql);
	preparador.setDate(1, dia_in);
	preparador.setDate(2, dia_fim);
	preparador.setInt(3, m);
	preparador.setInt(4, x);
	preparador.setInt(5, y);
	preparador.setInt(6, s);
	 ResultSet rs = preparador.executeQuery();
	 while(rs.next())
	 {
		 Exames pac = new Exames();
		 pac.setFK_factura(rs.getInt("FK_factura"));
		 pac.setNomeCompleto(rs.getString("NomeCompleto"));
		 pac.setGenero(rs.getString("genero"));
		 pac.setQuantidade(rs.getInt("qtd_servicos"));
	     pac.setAno(rs.getInt("idade"));
	     Calendar data = Calendar.getInstance();
			 data.setTime(rs.getDate("data_darecolha"));
			 pac.setData_darecolha(data);
		 lista.add(pac);
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
return lista;
}

public List<Exames> buscarPacienteConsultasInternadosAtendidosQuarto(Date dia_in, Date dia_fim, int m, int x, int y, int s, int ex){

List <Exames> lista = new ArrayList<Exames>();
String sql = "SELECT NomeCompleto,FK_requisicao_exame,idade,genero,data_darecolha,sum(qtd_servicos)as qtd_servicos,FK_factura "
		   + "FROM vwpacientesatendidoslaboratorio where data_darecolha between ? and ? and FK_grupo_analise_clinica = ? and idade between ? and ? and id_genero = ? and id_servicodeanalise_clinica = ? group by FK_factura";
try {
	 con = Conexao.getConexao();
	PreparedStatement preparador = con.prepareStatement(sql);
	preparador.setDate(1, dia_in);
	preparador.setDate(2, dia_fim);
	preparador.setInt(3, m);
	preparador.setInt(4, x);
	preparador.setInt(5, y);
	preparador.setInt(6, s);
	preparador.setInt(7, ex);
	 ResultSet rs = preparador.executeQuery();
	 while(rs.next())
	 {
		 Exames pac = new Exames();
		 pac.setFK_factura(rs.getInt("FK_factura"));
		 pac.setNomeCompleto(rs.getString("NomeCompleto"));
		 pac.setGenero(rs.getString("genero"));
		 pac.setQuantidade(rs.getInt("qtd_servicos"));
	     pac.setAno(rs.getInt("idade"));
	     Calendar data = Calendar.getInstance();
			 data.setTime(rs.getDate("data_darecolha"));
			 pac.setData_darecolha(data);
		 lista.add(pac);
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
return lista;
}

public List<Exames> buscarPacienteConsultasInternadosAtendidosIdade(Date dia_in, Date dia_fim, int x, int y){

List <Exames> lista = new ArrayList<Exames>();
String sql = "SELECT NomeCompleto,FK_requisicao_exame,idade,genero,data_darecolha,sum(qtd_servicos)as qtd_servicos,FK_factura "
		   + "FROM vwpacientesatendidoslaboratorio where data_darecolha between ? and ? and idade between ? and ? group by FK_factura";
try {
	 con = Conexao.getConexao();
	PreparedStatement preparador = con.prepareStatement(sql);
	preparador.setDate(1, dia_in);
	preparador.setDate(2, dia_fim);
	preparador.setInt(3, x);
	preparador.setInt(4, y);
	 ResultSet rs = preparador.executeQuery();
	 while(rs.next())
	 {
		 Exames pac = new Exames();
		 pac.setFK_factura(rs.getInt("FK_factura"));
		 pac.setNomeCompleto(rs.getString("NomeCompleto"));
		 pac.setGenero(rs.getString("genero"));
		 pac.setQuantidade(rs.getInt("qtd_servicos"));
	     pac.setAno(rs.getInt("idade"));
	     Calendar data = Calendar.getInstance();
			 data.setTime(rs.getDate("data_darecolha"));
			 pac.setData_darecolha(data);
		 lista.add(pac);
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
return lista;
}
public List<Exames> buscarPacienteConsultasInternadosAtendidosIdadeUm(Date dia_in, Date dia_fim, int x, int y, int s){

List <Exames> lista = new ArrayList<Exames>();
String sql = "SELECT NomeCompleto,FK_requisicao_exame,idade,genero,data_darecolha,sum(qtd_servicos)as qtd_servicos,FK_factura "
		   + "FROM vwpacientesatendidoslaboratorio where data_darecolha between ? and ? and idade between ? and ? and id_genero = ? group by FK_factura";
try {
	 con = Conexao.getConexao();
	PreparedStatement preparador = con.prepareStatement(sql);
	preparador.setDate(1, dia_in);
	preparador.setDate(2, dia_fim);
	preparador.setInt(3, x);
	preparador.setInt(4, y);
	preparador.setInt(5, s);
	 ResultSet rs = preparador.executeQuery();
	 while(rs.next())
	 {
		 Exames pac = new Exames();
		 pac.setFK_factura(rs.getInt("FK_factura"));
		 pac.setNomeCompleto(rs.getString("NomeCompleto"));
		 pac.setGenero(rs.getString("genero"));
		 pac.setQuantidade(rs.getInt("qtd_servicos"));
	     pac.setAno(rs.getInt("idade"));
	     Calendar data = Calendar.getInstance();
			 data.setTime(rs.getDate("data_darecolha"));
			 pac.setData_darecolha(data);
		 lista.add(pac);
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
return lista;
}

public List<Exames> buscarPacienteConsultasInternadosAtendidosIdadeDois(Date dia_in, Date dia_fim, int x, int y, int s, int ex){

List <Exames> lista = new ArrayList<Exames>();
String sql = "SELECT NomeCompleto,FK_requisicao_exame,idade,genero,data_darecolha,sum(qtd_servicos)as qtd_servicos,FK_factura "
		   + "FROM vwpacientesatendidoslaboratorio where data_darecolha between ? and ? and idade between ? and ? and id_genero = ? and id_servicodeanalise_clinica = ? group by FK_factura";
try {
	 con = Conexao.getConexao();
	PreparedStatement preparador = con.prepareStatement(sql);
	preparador.setDate(1, dia_in);
	preparador.setDate(2, dia_fim);
	preparador.setInt(3, x);
	preparador.setInt(4, y);
	preparador.setInt(5, ex);
	preparador.setInt(5, s);
	 ResultSet rs = preparador.executeQuery();
	 while(rs.next())
	 {
		 Exames pac = new Exames();
		 pac.setFK_factura(rs.getInt("FK_factura"));
		 pac.setNomeCompleto(rs.getString("NomeCompleto"));
		 pac.setGenero(rs.getString("genero"));
		 pac.setQuantidade(rs.getInt("qtd_servicos"));
	     pac.setAno(rs.getInt("idade"));
	     Calendar data = Calendar.getInstance();
			 data.setTime(rs.getDate("data_darecolha"));
			 pac.setData_darecolha(data);
		 lista.add(pac);
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
return lista;
}

public List<Exames> buscarPacienteConsultasInternadosAtendidosGenero(Date dia_in, Date dia_fim, int s){

List <Exames> lista = new ArrayList<Exames>();
String sql = "SELECT NomeCompleto,FK_requisicao_exame,idade,genero,data_darecolha,sum(qtd_servicos)as qtd_servicos,FK_factura "
		   + "FROM vwpacientesatendidoslaboratorio where data_darecolha between ? and ? and id_genero = ? group by FK_factura";
try {
	 con = Conexao.getConexao();
	PreparedStatement preparador = con.prepareStatement(sql);
	preparador.setDate(1, dia_in);
	preparador.setDate(2, dia_fim);
	preparador.setInt(3, s);
	 ResultSet rs = preparador.executeQuery();
	 while(rs.next())
	 {
		 Exames pac = new Exames();
		 pac.setFK_factura(rs.getInt("FK_factura"));
		 pac.setNomeCompleto(rs.getString("NomeCompleto"));
		 pac.setGenero(rs.getString("genero"));
		 pac.setQuantidade(rs.getInt("qtd_servicos"));
	     pac.setAno(rs.getInt("idade"));
	     Calendar data = Calendar.getInstance();
			 data.setTime(rs.getDate("data_darecolha"));
			 pac.setData_darecolha(data);
		 lista.add(pac);
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
return lista;
}

public List<Exames> buscarPacienteConsultasInternadosAtendidosGeneroUm(Date dia_in, Date dia_fim, int s, int ex){

List <Exames> lista = new ArrayList<Exames>();
String sql = "SELECT NomeCompleto,FK_requisicao_exame,idade,genero,data_darecolha,sum(qtd_servicos)as qtd_servicos,FK_factura "
		   + "FROM vwpacientesatendidoslaboratorio where data_darecolha between ? and ? and id_genero = ? and id_servicodeanalise_clinica =? group by FK_factura";
try {
	 con = Conexao.getConexao();
	PreparedStatement preparador = con.prepareStatement(sql);
	preparador.setDate(1, dia_in);
	preparador.setDate(2, dia_fim);
	preparador.setInt(3, s);
	preparador.setInt(4, ex);
	 ResultSet rs = preparador.executeQuery();
	 while(rs.next())
	 {
		 Exames pac = new Exames();
		 pac.setFK_factura(rs.getInt("FK_factura"));
		 pac.setNomeCompleto(rs.getString("NomeCompleto"));
		 pac.setGenero(rs.getString("genero"));
		 pac.setQuantidade(rs.getInt("qtd_servicos"));
	     pac.setAno(rs.getInt("idade"));
	     Calendar data = Calendar.getInstance();
			 data.setTime(rs.getDate("data_darecolha"));
			 pac.setData_darecolha(data);
		 lista.add(pac);
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
return lista;
}

public List<Exames> buscarPacienteConsultasInternadosAtendidosExame(Date dia_in, Date dia_fim, int ex){

List <Exames> lista = new ArrayList<Exames>();
String sql = "SELECT NomeCompleto,FK_requisicao_exame,idade,genero,data_darecolha,sum(qtd_servicos)as qtd_servicos,FK_factura "
		   + "FROM vwpacientesatendidoslaboratorio where data_darecolha between ? and ? and id_servicodeanalise_clinica =? group by FK_factura";
try {
	 con = Conexao.getConexao();
	PreparedStatement preparador = con.prepareStatement(sql);
	preparador.setDate(1, dia_in);
	preparador.setDate(2, dia_fim);
	preparador.setInt(3, ex);
	 ResultSet rs = preparador.executeQuery();
	 while(rs.next())
	 {
		 Exames pac = new Exames();
		 pac.setFK_factura(rs.getInt("FK_factura"));
		 pac.setNomeCompleto(rs.getString("NomeCompleto"));
		 pac.setGenero(rs.getString("genero"));
		 pac.setQuantidade(rs.getInt("qtd_servicos"));
	     pac.setAno(rs.getInt("idade"));
	     Calendar data = Calendar.getInstance();
			 data.setTime(rs.getDate("data_darecolha"));
			 pac.setData_darecolha(data);
		 lista.add(pac);
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
return lista;
}

public List<Exames> buscarPacienteConsultasInternadosAtendidosExameUm(Date dia_in, Date dia_fim, int ex, int gr){

List <Exames> lista = new ArrayList<Exames>();
String sql = "SELECT NomeCompleto,FK_requisicao_exame,idade,genero,data_darecolha,sum(qtd_servicos)as qtd_servicos,FK_factura "
		   + "FROM vwpacientesatendidoslaboratorio where data_darecolha between ? and ? and id_servicodeanalise_clinica =? and FK_grupo_analise_clinica = ? group by FK_factura";
try {
	 con = Conexao.getConexao();
	PreparedStatement preparador = con.prepareStatement(sql);
	preparador.setDate(1, dia_in);
	preparador.setDate(2, dia_fim);
	preparador.setInt(3, ex);
	preparador.setInt(4, gr);
	 ResultSet rs = preparador.executeQuery();
	 while(rs.next())
	 {
		 Exames pac = new Exames();
		 pac.setFK_factura(rs.getInt("FK_factura"));
		 pac.setNomeCompleto(rs.getString("NomeCompleto"));
		 pac.setGenero(rs.getString("genero"));
		 pac.setQuantidade(rs.getInt("qtd_servicos"));
	     pac.setAno(rs.getInt("idade"));
	     Calendar data = Calendar.getInstance();
			 data.setTime(rs.getDate("data_darecolha"));
			 pac.setData_darecolha(data);
		 lista.add(pac);
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
return lista;
}

public List<Exames> buscarPacienteConsultasInternadosAtendidosExameDois(Date dia_in, Date dia_fim, int ex, int gr, int gnr){

List <Exames> lista = new ArrayList<Exames>();
String sql = "SELECT NomeCompleto,FK_requisicao_exame,idade,genero,data_darecolha,sum(qtd_servicos)as qtd_servicos,FK_factura "
		   + "FROM vwpacientesatendidoslaboratorio where data_darecolha between ? and ? and id_servicodeanalise_clinica =? and FK_grupo_analise_clinica = ? and id_genero = ? group by FK_factura";
try {
	 con = Conexao.getConexao();
	PreparedStatement preparador = con.prepareStatement(sql);
	preparador.setDate(1, dia_in);
	preparador.setDate(2, dia_fim);
	preparador.setInt(3, ex);
	preparador.setInt(4, gr);
	preparador.setInt(5, gnr);
	 ResultSet rs = preparador.executeQuery();
	 while(rs.next())
	 {
		 Exames pac = new Exames();
		 pac.setFK_factura(rs.getInt("FK_factura"));
		 pac.setNomeCompleto(rs.getString("NomeCompleto"));
		 pac.setGenero(rs.getString("genero"));
		 pac.setQuantidade(rs.getInt("qtd_servicos"));
	     pac.setAno(rs.getInt("idade"));
	     Calendar data = Calendar.getInstance();
			 data.setTime(rs.getDate("data_darecolha"));
			 pac.setData_darecolha(data);
		 lista.add(pac);
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
return lista;
}

public List<Exames> buscarPacienteConsultasInternadosAtendidosExameTres(Date dia_in, Date dia_fim, int ex, int gr, int x, int y){

List <Exames> lista = new ArrayList<Exames>();
String sql = "SELECT NomeCompleto,FK_requisicao_exame,idade,genero,data_darecolha,sum(qtd_servicos)as qtd_servicos,FK_factura "
		   + "FROM vwpacientesatendidoslaboratorio where data_darecolha between ? and ? and id_servicodeanalise_clinica =? and FK_grupo_analise_clinica = ? and idade between ? and ? group by FK_factura";
try {
	 con = Conexao.getConexao();
	PreparedStatement preparador = con.prepareStatement(sql);
	preparador.setDate(1, dia_in);
	preparador.setDate(2, dia_fim);
	preparador.setInt(3, ex);
	preparador.setInt(4, gr);
	preparador.setInt(5, x);
	preparador.setInt(6, y);
	 ResultSet rs = preparador.executeQuery();
	 while(rs.next())
	 {
		 Exames pac = new Exames();
		 pac.setFK_factura(rs.getInt("FK_factura"));
		 pac.setNomeCompleto(rs.getString("NomeCompleto"));
		 pac.setGenero(rs.getString("genero"));
		 pac.setQuantidade(rs.getInt("qtd_servicos"));
	     pac.setAno(rs.getInt("idade"));
	     Calendar data = Calendar.getInstance();
			 data.setTime(rs.getDate("data_darecolha"));
			 pac.setData_darecolha(data);
		 lista.add(pac);
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
return lista;
}

public List<Exames> buscarPacienteConsultasInternadosAtendidosExameQuarto(Date dia_in, Date dia_fim, int ex, int x, int y){

List <Exames> lista = new ArrayList<Exames>();
String sql = "SELECT NomeCompleto,FK_requisicao_exame,idade,genero,data_darecolha,sum(qtd_servicos)as qtd_servicos,FK_factura "
		   + "FROM vwpacientesatendidoslaboratorio where data_darecolha between ? and ? and id_servicodeanalise_clinica =? and idade between ? and ? group by FK_factura";
try {
	 con = Conexao.getConexao();
	PreparedStatement preparador = con.prepareStatement(sql);
	preparador.setDate(1, dia_in);
	preparador.setDate(2, dia_fim);
	preparador.setInt(3, ex);
	preparador.setInt(4, x);
	preparador.setInt(5, y);
	 ResultSet rs = preparador.executeQuery();
	 while(rs.next())
	 {
		 Exames pac = new Exames();
		 pac.setFK_factura(rs.getInt("FK_factura"));
		 pac.setNomeCompleto(rs.getString("NomeCompleto"));
		 pac.setGenero(rs.getString("genero"));
		 pac.setQuantidade(rs.getInt("qtd_servicos"));
	     pac.setAno(rs.getInt("idade"));
	     Calendar data = Calendar.getInstance();
			 data.setTime(rs.getDate("data_darecolha"));
			 pac.setData_darecolha(data);
		 lista.add(pac);
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
return lista;
}


//--------------------------------------------------------- Paciente Internados final

//----------------------------------------------------------- Paciente BUE inicio
public List<Exames> buscarPacienteConsultasBUEAtendidos(){
    List <Exames> lista = new ArrayList<Exames>();
		String sql = "SELECT NomeCompleto,FK_requisicao_exame,idade,genero,data_darecolha,sum(qtd_servicos)as qtd_servicos,FK_factura "
				   + "FROM vwpacientesatendidoslaboratorio where FK_provenienca_exame = 1 AND data_darecolha = curdate() group by FK_factura";
		try {
			 con = Conexao.getConexao();
			PreparedStatement preparador = con.prepareStatement(sql);
			 ResultSet rs = preparador.executeQuery();
			 while(rs.next())
			 {
				 Exames pac = new Exames();
				 pac.setFK_factura(rs.getInt("FK_factura"));
				 pac.setNomeCompleto(rs.getString("NomeCompleto"));
				 pac.setGenero(rs.getString("genero"));
				 pac.setQuantidade(rs.getInt("qtd_servicos"));
    		     pac.setAno(rs.getInt("idade"));
    		     Calendar data = Calendar.getInstance();
 				 data.setTime(rs.getDate("data_darecolha"));
 				 pac.setData_darecolha(data);
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

public List<Exames> buscarPacienteConsultasBUEAtendidosPorData(Date dia_in, Date dia_fim){
    List <Exames> lista = new ArrayList<Exames>();
		String sql = "SELECT NomeCompleto,FK_requisicao_exame,idade,genero,data_darecolha,sum(qtd_servicos)as qtd_servicos,FK_factura "
				+ "FROM vwpacientesatendidoslaboratorio where data_darecolha between ? and ? group by FK_factura";
		try {
			 con = Conexao.getConexao();
			PreparedStatement preparador = con.prepareStatement(sql);
			preparador.setDate(1, dia_in);
			preparador.setDate(2, dia_fim);
			 ResultSet rs = preparador.executeQuery();
			 while(rs.next())
			 {
				 Exames pac = new Exames();
				 pac.setFK_factura(rs.getInt("FK_factura"));
				 pac.setNomeCompleto(rs.getString("NomeCompleto"));
				 pac.setGenero(rs.getString("genero"));
				 pac.setQuantidade(rs.getInt("qtd_servicos"));
    		     pac.setAno(rs.getInt("idade"));
    		     Calendar data = Calendar.getInstance();
 				 data.setTime(rs.getDate("data_darecolha"));
 				 pac.setData_darecolha(data);
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

public List<Exames> buscarPacienteConsultasBUEAtendidosUm(Date dia_in, Date dia_fim,int m){
	
	List <Exames> lista = new ArrayList<Exames>();
	String sql = "SELECT NomeCompleto,FK_requisicao_exame,idade,genero,data_darecolha,sum(qtd_servicos)as qtd_servicos,FK_factura "
			+ "FROM vwpacientesatendidoslaboratorio where data_darecolha between ? and ? and FK_grupo_analise_clinica = ? group by FK_factura";
	try {
		 con = Conexao.getConexao();
		PreparedStatement preparador = con.prepareStatement(sql);
		preparador.setDate(1, dia_in);
		preparador.setDate(2, dia_fim);
		preparador.setInt(3, m);
		 ResultSet rs = preparador.executeQuery();
		 while(rs.next())
		 {
			 Exames pac = new Exames();
			 pac.setFK_factura(rs.getInt("FK_factura"));
			 pac.setNomeCompleto(rs.getString("NomeCompleto"));
			 pac.setGenero(rs.getString("genero"));
			 pac.setQuantidade(rs.getInt("qtd_servicos"));
		     pac.setAno(rs.getInt("idade"));
		     Calendar data = Calendar.getInstance();
				 data.setTime(rs.getDate("data_darecolha"));
				 pac.setData_darecolha(data);
			 lista.add(pac);
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
	return lista;
}

public List<Exames> buscarPacienteConsultasBUEAtendidosDois(Date dia_in, Date dia_fim, int m, int x, int y){
	
	List <Exames> lista = new ArrayList<Exames>();
	String sql = "SELECT NomeCompleto,FK_requisicao_exame,idade,genero,data_darecolha,sum(qtd_servicos)as qtd_servicos,FK_factura "
			+ "FROM vwpacientesatendidoslaboratorio where data_darecolha between ? and ? and FK_grupo_analise_clinica = ? and idade between ? and ? group by FK_factura";
	try {
		 con = Conexao.getConexao();
		PreparedStatement preparador = con.prepareStatement(sql);
		preparador.setDate(1, dia_in);
		preparador.setDate(2, dia_fim);
		preparador.setInt(3, m);
		preparador.setInt(4, x);
		preparador.setInt(5, y);
		 ResultSet rs = preparador.executeQuery();
		 while(rs.next())
		 {
			 Exames pac = new Exames();
			 pac.setFK_factura(rs.getInt("FK_factura"));
			 pac.setNomeCompleto(rs.getString("NomeCompleto"));
			 pac.setGenero(rs.getString("genero"));
			 pac.setQuantidade(rs.getInt("qtd_servicos"));
		     pac.setAno(rs.getInt("idade"));
		     Calendar data = Calendar.getInstance();
				 data.setTime(rs.getDate("data_darecolha"));
				 pac.setData_darecolha(data);
			 lista.add(pac);
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
	return lista;
}

public List<Exames> buscarPacienteConsultasBUEAtendidosTres(Date dia_in, Date dia_fim, int m, int x, int y, int s){

List <Exames> lista = new ArrayList<Exames>();
String sql = "SELECT NomeCompleto,FK_requisicao_exame,idade,genero,data_darecolha,sum(qtd_servicos)as qtd_servicos,FK_factura "
		   + "FROM vwpacientesatendidoslaboratorio where data_darecolha between ? and ? and FK_grupo_analise_clinica = ? and idade between ? and ? and id_genero = ? group by FK_factura";
System.out.println("Data inicio: "+dia_in);
System.out.println("Data final: "+dia_fim);
System.out.println(" grupo: "+m);
System.out.println(" idade1: "+x);
System.out.println(" idade2: "+y);
System.out.println(" genero: "+s);
try {
	 con = Conexao.getConexao();
	PreparedStatement preparador = con.prepareStatement(sql);
	preparador.setDate(1, dia_in);
	preparador.setDate(2, dia_fim);
	preparador.setInt(3, m);
	preparador.setInt(4, x);
	preparador.setInt(5, y);
	preparador.setInt(6, s);
	 ResultSet rs = preparador.executeQuery();
	 while(rs.next())
	 {
		 Exames pac = new Exames();
		 pac.setFK_factura(rs.getInt("FK_factura"));
		 pac.setNomeCompleto(rs.getString("NomeCompleto"));
		 pac.setGenero(rs.getString("genero"));
		 pac.setQuantidade(rs.getInt("qtd_servicos"));
	     pac.setAno(rs.getInt("idade"));
	     Calendar data = Calendar.getInstance();
			 data.setTime(rs.getDate("data_darecolha"));
			 pac.setData_darecolha(data);
		 lista.add(pac);
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
return lista;
}

public List<Exames> buscarPacienteConsultasBUEAtendidosQuarto(Date dia_in, Date dia_fim, int m, int x, int y, int s, int ex){

List <Exames> lista = new ArrayList<Exames>();
String sql = "SELECT NomeCompleto,FK_requisicao_exame,idade,genero,data_darecolha,sum(qtd_servicos)as qtd_servicos,FK_factura "
		   + "FROM vwpacientesatendidoslaboratorio where data_darecolha between ? and ? and FK_grupo_analise_clinica = ? and idade between ? and ? and id_genero = ? and id_servicodeanalise_clinica = ? group by FK_factura";
try {
	 con = Conexao.getConexao();
	PreparedStatement preparador = con.prepareStatement(sql);
	preparador.setDate(1, dia_in);
	preparador.setDate(2, dia_fim);
	preparador.setInt(3, m);
	preparador.setInt(4, x);
	preparador.setInt(5, y);
	preparador.setInt(6, s);
	preparador.setInt(7, ex);
	 ResultSet rs = preparador.executeQuery();
	 while(rs.next())
	 {
		 Exames pac = new Exames();
		 pac.setFK_factura(rs.getInt("FK_factura"));
		 pac.setNomeCompleto(rs.getString("NomeCompleto"));
		 pac.setGenero(rs.getString("genero"));
		 pac.setQuantidade(rs.getInt("qtd_servicos"));
	     pac.setAno(rs.getInt("idade"));
	     Calendar data = Calendar.getInstance();
			 data.setTime(rs.getDate("data_darecolha"));
			 pac.setData_darecolha(data);
		 lista.add(pac);
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
return lista;
}

public List<Exames> buscarPacienteConsultasBUEAtendidosIdade(Date dia_in, Date dia_fim, int x, int y){

List <Exames> lista = new ArrayList<Exames>();
String sql = "SELECT NomeCompleto,FK_requisicao_exame,idade,genero,data_darecolha,sum(qtd_servicos)as qtd_servicos,FK_factura "
		   + "FROM vwpacientesatendidoslaboratorio where data_darecolha between ? and ? and idade between ? and ? group by FK_factura";
try {
	 con = Conexao.getConexao();
	PreparedStatement preparador = con.prepareStatement(sql);
	preparador.setDate(1, dia_in);
	preparador.setDate(2, dia_fim);
	preparador.setInt(3, x);
	preparador.setInt(4, y);
	 ResultSet rs = preparador.executeQuery();
	 while(rs.next())
	 {
		 Exames pac = new Exames();
		 pac.setFK_factura(rs.getInt("FK_factura"));
		 pac.setNomeCompleto(rs.getString("NomeCompleto"));
		 pac.setGenero(rs.getString("genero"));
		 pac.setQuantidade(rs.getInt("qtd_servicos"));
	     pac.setAno(rs.getInt("idade"));
	     Calendar data = Calendar.getInstance();
			 data.setTime(rs.getDate("data_darecolha"));
			 pac.setData_darecolha(data);
		 lista.add(pac);
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
return lista;
}
public List<Exames> buscarPacienteConsultasBUEAtendidosIdadeUm(Date dia_in, Date dia_fim, int x, int y, int s){

List <Exames> lista = new ArrayList<Exames>();
String sql = "SELECT NomeCompleto,FK_requisicao_exame,idade,genero,data_darecolha,sum(qtd_servicos)as qtd_servicos,FK_factura "
		   + "FROM vwpacientesatendidoslaboratorio where data_darecolha between ? and ? and idade between ? and ? and id_genero = ? group by FK_factura";
try {
	 con = Conexao.getConexao();
	PreparedStatement preparador = con.prepareStatement(sql);
	preparador.setDate(1, dia_in);
	preparador.setDate(2, dia_fim);
	preparador.setInt(3, x);
	preparador.setInt(4, y);
	preparador.setInt(5, s);
	 ResultSet rs = preparador.executeQuery();
	 while(rs.next())
	 {
		 Exames pac = new Exames();
		 pac.setFK_factura(rs.getInt("FK_factura"));
		 pac.setNomeCompleto(rs.getString("NomeCompleto"));
		 pac.setGenero(rs.getString("genero"));
		 pac.setQuantidade(rs.getInt("qtd_servicos"));
	     pac.setAno(rs.getInt("idade"));
	     Calendar data = Calendar.getInstance();
			 data.setTime(rs.getDate("data_darecolha"));
			 pac.setData_darecolha(data);
		 lista.add(pac);
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
return lista;
}

public List<Exames> buscarPacienteConsultasBUEAtendidosIdadeDois(Date dia_in, Date dia_fim, int x, int y, int s, int ex){

List <Exames> lista = new ArrayList<Exames>();
String sql = "SELECT NomeCompleto,FK_requisicao_exame,idade,genero,data_darecolha,sum(qtd_servicos)as qtd_servicos,FK_factura "
		   + "FROM vwpacientesatendidoslaboratorio where data_darecolha between ? and ? and idade between ? and ? and id_genero = ? and id_servicodeanalise_clinica = ? group by FK_factura";
try {
	 con = Conexao.getConexao();
	PreparedStatement preparador = con.prepareStatement(sql);
	preparador.setDate(1, dia_in);
	preparador.setDate(2, dia_fim);
	preparador.setInt(3, x);
	preparador.setInt(4, y);
	preparador.setInt(5, ex);
	preparador.setInt(5, s);
	 ResultSet rs = preparador.executeQuery();
	 while(rs.next())
	 {
		 Exames pac = new Exames();
		 pac.setFK_factura(rs.getInt("FK_factura"));
		 pac.setNomeCompleto(rs.getString("NomeCompleto"));
		 pac.setGenero(rs.getString("genero"));
		 pac.setQuantidade(rs.getInt("qtd_servicos"));
	     pac.setAno(rs.getInt("idade"));
	     Calendar data = Calendar.getInstance();
			 data.setTime(rs.getDate("data_darecolha"));
			 pac.setData_darecolha(data);
		 lista.add(pac);
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
return lista;
}

public List<Exames> buscarPacienteConsultasBUEAtendidosGenero(Date dia_in, Date dia_fim, int s){

List <Exames> lista = new ArrayList<Exames>();
String sql = "SELECT NomeCompleto,FK_requisicao_exame,idade,genero,data_darecolha,sum(qtd_servicos)as qtd_servicos,FK_factura "
		   + "FROM vwpacientesatendidoslaboratorio where data_darecolha between ? and ? and id_genero = ? group by FK_factura";
try {
	 con = Conexao.getConexao();
	PreparedStatement preparador = con.prepareStatement(sql);
	preparador.setDate(1, dia_in);
	preparador.setDate(2, dia_fim);
	preparador.setInt(3, s);
	 ResultSet rs = preparador.executeQuery();
	 while(rs.next())
	 {
		 Exames pac = new Exames();
		 pac.setFK_factura(rs.getInt("FK_factura"));
		 pac.setNomeCompleto(rs.getString("NomeCompleto"));
		 pac.setGenero(rs.getString("genero"));
		 pac.setQuantidade(rs.getInt("qtd_servicos"));
	     pac.setAno(rs.getInt("idade"));
	     Calendar data = Calendar.getInstance();
			 data.setTime(rs.getDate("data_darecolha"));
			 pac.setData_darecolha(data);
		 lista.add(pac);
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
return lista;
}

public List<Exames> buscarPacienteConsultasBUEAtendidosGeneroUm(Date dia_in, Date dia_fim, int s, int ex){

List <Exames> lista = new ArrayList<Exames>();
String sql = "SELECT NomeCompleto,FK_requisicao_exame,idade,genero,data_darecolha,sum(qtd_servicos)as qtd_servicos,FK_factura "
		   + "FROM vwpacientesatendidoslaboratorio where data_darecolha between ? and ? and id_genero = ? and id_servicodeanalise_clinica =? group by FK_factura";
try {
	 con = Conexao.getConexao();
	PreparedStatement preparador = con.prepareStatement(sql);
	preparador.setDate(1, dia_in);
	preparador.setDate(2, dia_fim);
	preparador.setInt(3, s);
	preparador.setInt(4, ex);
	 ResultSet rs = preparador.executeQuery();
	 while(rs.next())
	 {
		 Exames pac = new Exames();
		 pac.setFK_factura(rs.getInt("FK_factura"));
		 pac.setNomeCompleto(rs.getString("NomeCompleto"));
		 pac.setGenero(rs.getString("genero"));
		 pac.setQuantidade(rs.getInt("qtd_servicos"));
	     pac.setAno(rs.getInt("idade"));
	     Calendar data = Calendar.getInstance();
			 data.setTime(rs.getDate("data_darecolha"));
			 pac.setData_darecolha(data);
		 lista.add(pac);
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
return lista;
}

public List<Exames> buscarPacienteConsultasBUEAtendidosExame(Date dia_in, Date dia_fim, int ex){

List <Exames> lista = new ArrayList<Exames>();
String sql = "SELECT NomeCompleto,FK_requisicao_exame,idade,genero,data_darecolha,sum(qtd_servicos)as qtd_servicos,FK_factura "
		   + "FROM vwpacientesatendidoslaboratorio where data_darecolha between ? and ? and id_servicodeanalise_clinica =? group by FK_factura";
try {
	 con = Conexao.getConexao();
	PreparedStatement preparador = con.prepareStatement(sql);
	preparador.setDate(1, dia_in);
	preparador.setDate(2, dia_fim);
	preparador.setInt(3, ex);
	 ResultSet rs = preparador.executeQuery();
	 while(rs.next())
	 {
		 Exames pac = new Exames();
		 pac.setFK_factura(rs.getInt("FK_factura"));
		 pac.setNomeCompleto(rs.getString("NomeCompleto"));
		 pac.setGenero(rs.getString("genero"));
		 pac.setQuantidade(rs.getInt("qtd_servicos"));
	     pac.setAno(rs.getInt("idade"));
	     Calendar data = Calendar.getInstance();
			 data.setTime(rs.getDate("data_darecolha"));
			 pac.setData_darecolha(data);
		 lista.add(pac);
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
return lista;
}

public List<Exames> buscarPacienteConsultasBUEAtendidosExameUm(Date dia_in, Date dia_fim, int ex, int gr){

List <Exames> lista = new ArrayList<Exames>();
String sql = "SELECT NomeCompleto,FK_requisicao_exame,idade,genero,data_darecolha,sum(qtd_servicos)as qtd_servicos,FK_factura "
		   + "FROM vwpacientesatendidoslaboratorio where data_darecolha between ? and ? and id_servicodeanalise_clinica =? and FK_grupo_analise_clinica = ? group by FK_factura";
try {
	 con = Conexao.getConexao();
	PreparedStatement preparador = con.prepareStatement(sql);
	preparador.setDate(1, dia_in);
	preparador.setDate(2, dia_fim);
	preparador.setInt(3, ex);
	preparador.setInt(4, gr);
	 ResultSet rs = preparador.executeQuery();
	 while(rs.next())
	 {
		 Exames pac = new Exames();
		 pac.setFK_factura(rs.getInt("FK_factura"));
		 pac.setNomeCompleto(rs.getString("NomeCompleto"));
		 pac.setGenero(rs.getString("genero"));
		 pac.setQuantidade(rs.getInt("qtd_servicos"));
	     pac.setAno(rs.getInt("idade"));
	     Calendar data = Calendar.getInstance();
			 data.setTime(rs.getDate("data_darecolha"));
			 pac.setData_darecolha(data);
		 lista.add(pac);
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
return lista;
}

public List<Exames> buscarPacienteConsultasBUEAtendidosExameDois(Date dia_in, Date dia_fim, int ex, int gr, int gnr){

List <Exames> lista = new ArrayList<Exames>();
String sql = "SELECT NomeCompleto,FK_requisicao_exame,idade,genero,data_darecolha,sum(qtd_servicos)as qtd_servicos,FK_factura "
		   + "FROM vwpacientesatendidoslaboratorio where data_darecolha between ? and ? and id_servicodeanalise_clinica =? and FK_grupo_analise_clinica = ? and id_genero = ? group by FK_factura";
try {
	 con = Conexao.getConexao();
	PreparedStatement preparador = con.prepareStatement(sql);
	preparador.setDate(1, dia_in);
	preparador.setDate(2, dia_fim);
	preparador.setInt(3, ex);
	preparador.setInt(4, gr);
	preparador.setInt(5, gnr);
	 ResultSet rs = preparador.executeQuery();
	 while(rs.next())
	 {
		 Exames pac = new Exames();
		 pac.setFK_factura(rs.getInt("FK_factura"));
		 pac.setNomeCompleto(rs.getString("NomeCompleto"));
		 pac.setGenero(rs.getString("genero"));
		 pac.setQuantidade(rs.getInt("qtd_servicos"));
	     pac.setAno(rs.getInt("idade"));
	     Calendar data = Calendar.getInstance();
			 data.setTime(rs.getDate("data_darecolha"));
			 pac.setData_darecolha(data);
		 lista.add(pac);
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
return lista;
}

public List<Exames> buscarPacienteConsultasBUEAtendidosExameTres(Date dia_in, Date dia_fim, int ex, int gr, int x, int y){

List <Exames> lista = new ArrayList<Exames>();
String sql = "SELECT NomeCompleto,FK_requisicao_exame,idade,genero,data_darecolha,sum(qtd_servicos)as qtd_servicos,FK_factura "
		   + "FROM vwpacientesatendidoslaboratorio where data_darecolha between ? and ? and id_servicodeanalise_clinica =? and FK_grupo_analise_clinica = ? and idade between ? and ? group by FK_factura";
try {
	 con = Conexao.getConexao();
	PreparedStatement preparador = con.prepareStatement(sql);
	preparador.setDate(1, dia_in);
	preparador.setDate(2, dia_fim);
	preparador.setInt(3, ex);
	preparador.setInt(4, gr);
	preparador.setInt(5, x);
	preparador.setInt(6, y);
	 ResultSet rs = preparador.executeQuery();
	 while(rs.next())
	 {
		 Exames pac = new Exames();
		 pac.setFK_factura(rs.getInt("FK_factura"));
		 pac.setNomeCompleto(rs.getString("NomeCompleto"));
		 pac.setGenero(rs.getString("genero"));
		 pac.setQuantidade(rs.getInt("qtd_servicos"));
	     pac.setAno(rs.getInt("idade"));
	     Calendar data = Calendar.getInstance();
			 data.setTime(rs.getDate("data_darecolha"));
			 pac.setData_darecolha(data);
		 lista.add(pac);
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
return lista;
}

public List<Exames> buscarPacienteConsultasBUEAtendidosExameQuarto(Date dia_in, Date dia_fim, int ex, int x, int y){

List <Exames> lista = new ArrayList<Exames>();
String sql = "SELECT NomeCompleto,FK_requisicao_exame,idade,genero,data_darecolha,sum(qtd_servicos)as qtd_servicos,FK_factura "
		   + "FROM vwpacientesatendidoslaboratorio where data_darecolha between ? and ? and id_servicodeanalise_clinica =? and idade between ? and ? group by FK_factura";
try {
	 con = Conexao.getConexao();
	PreparedStatement preparador = con.prepareStatement(sql);
	preparador.setDate(1, dia_in);
	preparador.setDate(2, dia_fim);
	preparador.setInt(3, ex);
	preparador.setInt(4, x);
	preparador.setInt(5, y);
	 ResultSet rs = preparador.executeQuery();
	 while(rs.next())
	 {
		 Exames pac = new Exames();
		 pac.setFK_factura(rs.getInt("FK_factura"));
		 pac.setNomeCompleto(rs.getString("NomeCompleto"));
		 pac.setGenero(rs.getString("genero"));
		 pac.setQuantidade(rs.getInt("qtd_servicos"));
	     pac.setAno(rs.getInt("idade"));
	     Calendar data = Calendar.getInstance();
			 data.setTime(rs.getDate("data_darecolha"));
			 pac.setData_darecolha(data);
		 lista.add(pac);
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
return lista;
}


//----------------------------------------------------------- Paciente BUE final
	
}
