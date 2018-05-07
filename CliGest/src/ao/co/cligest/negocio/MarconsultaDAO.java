package ao.co.cligest.negocio;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import ao.co.cligest.entidades.*;

public class MarconsultaDAO {
	
	private Connection con ;
	
	public List<Paciente> buscaservicos (int codigo)
	{
		List <Paciente> lista = new ArrayList<Paciente>();
		String sql = "SELECT * FROM TBLTIPODESERVICO where id_tipo_de_servico=  ?";
		try {
			 con = Conexao.getConexao();
			 PreparedStatement preparador = con.prepareStatement(sql);
			 preparador.setInt(1, codigo);
			 ResultSet rs = preparador.executeQuery();
			 while(rs.next())
			 {
				 Paciente bc = new Paciente();
				 bc.setId_tipo_de_servico(rs.getInt("id_tipo_de_servico"));
				 bc.setTipo_de_servico(rs.getString("tipo_de_servico"));
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
		finally{
			try {
				con.close();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		return lista;
	}

	
	public List<Paciente> buscaMedico (int especialdade, int dsemana)
	{
		List <Paciente> lista = new ArrayList<Paciente>();
		String sql = "SELECT * FROM vwfuncespecializadoeagendado where id_especialidade= ? and id_da_semana =? group by FK_entidade";
	try {
		     con = Conexao.getConexao();
			 PreparedStatement preparador = con.prepareStatement(sql);
			 preparador.setInt(1, especialdade);
			 preparador.setInt(2, dsemana);
			 ResultSet rs = preparador.executeQuery();
		 while(rs.next())
			 {
			 	 Paciente med = new Paciente();
				 med.setId(rs.getInt("FK_entidade"));
				 med.setNome(rs.getString("NOME"));
				 med.setApelido(rs.getString("ULTIMO_NOME"));
				 lista.add(med);
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
		try {
			con.close();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	return lista;
}
	
	public boolean checkHoraConsulta (Paciente p)
	{
		boolean bolean = false;
		String sql = "SELECT * FROM vwfuncespecializadoeagendado where fk_entidade = ? "
				   + "and id_da_semana = ? "
				   + "and  hora_de_inicio <= ? AND hora_do_fin >= ?";
	try {
		     con = Conexao.getConexao();
			 PreparedStatement preparador = con.prepareStatement(sql);
			 preparador.setInt	 (1, p.getFK_doutor());
			 preparador.setInt	 (2, p.getFK_dia_dasemana());
			 preparador.setString(3, p.getHora_daconfirmacao());
			 preparador.setString(4, p.getHora_daconfirmacao());
			 ResultSet rs = preparador.executeQuery();
		    if(rs.next())
			 	 bolean = true;
			 
			 preparador.close();
		} catch (SQLException e) {			e.printStackTrace();		}
	catch (Exception e) {System.out.println(e);	}
	finally{
		try {
			con.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	return bolean;
}
	
	public boolean checkConsulta (Paciente p)
	{
		boolean bolean = true;
		String sql = "SELECT * FROM tblconsultamarcada where FK_paciente = ? and FK_servico = ? and  data_do_agendamento = ? ";
	try {
		     con = Conexao.getConexao();
			 PreparedStatement preparador = con.prepareStatement(sql);
			 preparador.setInt	 (1, p.getFK_paciente());
			 preparador.setInt	 (2, p.getFK_servico());
			 preparador.setDate(3, p.getData_agendamento());
			 ResultSet rs = preparador.executeQuery();
		    if(rs.next()){
		    	System.out.println("TEM VALOR");
		    	return false;
		    }
			 	
			 
			 preparador.close();
		} 
	catch (SQLException e) 	{			System.out.println(e.getMessage());		}
	catch (Exception e)		{System.out.println(e.getMessage());	}
	finally{
		try {
			con.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	return true;
}
	
	public List<Paciente> buscaServicodescricao (int codigo )
	{
		System.out.println("Codigo cussunga: "+codigo);
		List <Paciente> lista = new ArrayList<Paciente>();
		String sql = "SELECT svc.id_servico, svc.descricao, (txa.taxa / 100 * svc.preco) + svc.preco AS preco, "
				+ "  svc.servico, svc.fk_especialidade, svc.fk_taxa, svc.fk_categoria FROM TBLSERVICO svc JOIN tbltaxas txa ON txa.id_taxa = svc.fk_taxa "
				+ " where svc.fk_especialidade = ? and svc.fk_categoria = 1 ";
	try {
		    con = Conexao.getConexao(); 
			PreparedStatement preparador = con.prepareStatement(sql);
			 preparador.setInt(1, codigo);
	         ResultSet rs = preparador.executeQuery();
	         //boolean te = rs.next();
			 while(rs.next())
				 {
				     Paciente ser = new Paciente();
					 ser.setId_servico(rs.getInt("id_servico"));
					 ser.setDescricao(rs.getString("descricao"));
					 ser.setPreco(rs.getDouble("Preco"));
					 
					 lista.add(ser);
					 System.out.print("ID "+rs.getInt("id_servico"));
				 }
			 //System.out.print("ID "+rs.next());
			 preparador.close();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	catch (Exception e) {
		// TODO: handle exception
	}
	finally{
		try {
			con.close();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	return lista;
}
	
	
	
public int inserirmarcacao(Paciente mc){
	int ultimoId = -1;
	    String sql = "INSERT INTO TBLCONSULTAMARCADA (FK_paciente,FK_doutor,FK_servico,FK_funcionario,data,data_do_agendamento,FK_especialidade,gestante_pra_consulta,numero_da_consulta,hora_do_agendamento, FK_fornecedor, FK_cont_forn,fk_convenio,fk_plano) values (?,?,?,?,?,?,?,?,?,?,?,?,?,?)";
	    try {
			 
			 //1-TBLCONSULTAMARCADA
	    	con = Conexao.getConexao();
			 PreparedStatement cid = con.prepareStatement(sql);
			 cid.setInt(1,  mc.getFK_entidade_paciente());
			 cid.setInt(2,  mc.getFK_doutor());
			 cid.setInt(3,  mc.getId_servico());
			 cid.setInt(4,  mc.getFK_entidade());
			 cid.setDate(5, new Formatando().data_registo());
			 cid.setDate(6, mc.getData_agendamento());
			 cid.setInt(7,  mc.getFK_especialidade());
			 cid.setInt(8,  mc.getGestante());
			 cid.setString(9,  mc.getNumero_da_consulta());
			 cid.setString(10, mc.getHora_daconfirmacao());
			 cid.setInt(11, mc.getId_convenio());
			 cid.setInt(12, mc.getId());
			 cid.setInt(13, mc.getId_convenio());
			 cid.setInt(14, mc.getId());
			 cid.execute(); 
			 ResultSet rs = cid.executeQuery("SELECT LAST_INSERT_ID()");
			 if(rs.next())
	    	        ultimoId = rs.getInt(1);
			 cid.close();
			 System.out.println("Cadastro com sucesso...TBLCONSULTAMARCADA"); 
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
	    return ultimoId;
	}

//Numero de servico consulta

		public int numeroconsulta()
		{ 
			
			int fun = 0;
			String sql = "SELECT count(numero_da_consulta) numero FROM tblconsultamarcada";
			try {
				con = Conexao.getConexao();
				PreparedStatement preparador = con.prepareStatement(sql);
				ResultSet rs = preparador.executeQuery();
				
				if(rs.next())
					fun = rs.getInt("numero");
			}
			catch(Exception e){}
			finally{
				try {
					con.close();
				} catch (SQLException e) {
					e.printStackTrace();
				}
			}
				return fun+1;  
			
		}

}
