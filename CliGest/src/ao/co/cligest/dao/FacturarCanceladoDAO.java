package ao.co.cligest.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

import ao.co.cligest.entidades.Exames;
import ao.co.cligest.entidades.Paciente;
import ao.co.cligest.entidades.Servico;
import ao.co.cligest.entidades.Triagem;

public class FacturarCanceladoDAO {

	private Connection con;
	Formatando ft = new Formatando();
	
	public List <Paciente> buscarCancelados()
    {
			List <Paciente> lista = new ArrayList<Paciente>();
			String sql = "SELECT * FROM vwexamerequisitadocancelado order by data_exam_requ_cancelado desc, hora_exam_requ_cancelado asc limit 500";
			try {
				con = Conexao.getConexao();
				PreparedStatement preparador = con.prepareStatement(sql);
				ResultSet rs = preparador.executeQuery();
				while (rs.next())
				 {
					 Paciente fun = new Paciente();
					 fun.setNomeCompleto(rs.getString("NomeCompleto"));
				     Calendar data = Calendar.getInstance();
				     data.setTime(rs.getDate("data_exam_requ_cancelado"));
				     fun.setData_registo(data);
				     fun.setData(rs.getDate("data_exam_requ_cancelado"));
				     fun.setHora_registo(rs.getString("hora_exam_requ_cancelado"));
				     fun.setNomeFun(rs.getString("n_c_funcionario"));
				     fun.setQuantidade(rs.getInt("qtd"));
				     fun.setTotal_s(rs.getString("total"));
				     fun.setNumero_ordem(rs.getString("numero_do_exame"));
				     fun.setId(rs.getInt("id_exame_cancelado"));
				     fun.setFK_servico(rs.getInt("FK_numero_do_servico_de_exame"));
				     fun.setNumero_processo(rs.getString("NumeroProcesso"));
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
	
	public List <Paciente> buscarCancelados(String nome)
    {
			List <Paciente> lista = new ArrayList<Paciente>();
			String sql = "SELECT * FROM vwexamerequisitadocancelado where NomeCompleto LIKE ?";
			try {
				con = Conexao.getConexao();
				PreparedStatement preparador = con.prepareStatement(sql);
				preparador.setString(1, "%"+nome+"%");
				ResultSet rs = preparador.executeQuery();
				while (rs.next())
				 {
					 Paciente fun = new Paciente();
					 fun.setNomeCompleto(rs.getString("NomeCompleto"));
					 Calendar data = Calendar.getInstance();
					 data.setTime(rs.getDate("data_exam_requ_cancelado"));
				     fun.setData_registo(data);
				     fun.setData(rs.getDate("data_exam_requ_cancelado"));
				     fun.setHora_registo(rs.getString("hora_exam_requ_cancelado"));
				     fun.setNomeFun(rs.getString("n_c_funcionario"));
				     fun.setQuantidade(rs.getInt("qtd"));
				     fun.setTotal_s(rs.getString("total"));
				     fun.setNumero_ordem(rs.getString("numero_do_exame"));
				     fun.setId(rs.getInt("id_exame_cancelado"));
				     fun.setFK_servico(rs.getInt("FK_numero_do_servico_de_exame"));
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
	
	public List <Exames> buscarVisualizarCancelados(String cod)
    {
			List <Exames> lista = new ArrayList<Exames>();
			String sql = "SELECT * FROM vwvisualizarexamecancelado WHERE numero_do_exame =?";
			try {
				con = Conexao.getConexao();
				PreparedStatement preparador = con.prepareStatement(sql);
				preparador.setString(1, cod);
				ResultSet rs = preparador.executeQuery();
				while (rs.next())
				 {
					 Exames fun = new Exames();
					 fun.setAnalise_clinica(rs.getString("analise_clinica"));
				     fun.setPreco(rs.getDouble("preco"));
				     fun.setTaxa(rs.getString("taxa"));
				     fun.setSubtotal(rs.getDouble("subtotal"));
				     fun.setTaxa_emkz(rs.getDouble("taxa_kz"));
				     fun.setNumero_do_exame(rs.getString("numero_do_exame"));
				     fun.setId_servicodeanalise_clinica(rs.getInt("FK_numero_do_servico_de_exame"));
				     
					 Calendar data = Calendar.getInstance();
					 data.setTime(rs.getDate("data_cancelado"));
					 fun.setData_agendamento(data);
					 
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
	public void del_cancelados(int id)
	{
		String sql = "DELETE FROM tblexamerequisitadocancelado WHERE id_exame_cancelado=?";
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
}
