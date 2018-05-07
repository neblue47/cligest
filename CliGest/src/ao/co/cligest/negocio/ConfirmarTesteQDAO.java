package ao.co.cligest.negocio;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import ao.co.cligest.entidades.Agenda;
import ao.co.cligest.entidades.Exames;

public class ConfirmarTesteQDAO {

	private Connection con ;
	
	
	public boolean confirmarExameTesteDeQualidade(Exames ex){
			
			String sql = "INSERT INTO tbltestedequalidadeconfirmado (FK_requisicao,FK_servico_tq,data,hora,FK_funcionario,FK_empresa) VALUES (?,?,?,?,?,?)";
			try {
				con = Conexao.getConexao();
				PreparedStatement cp = con.prepareStatement(sql);
				cp.setInt(1, ex.getFK_requisicao());
				cp.setInt(2, ex.getFK_servico_tq());
				((PreparedStatement) cp).setDate(3, new Formatando().data_registo());
				cp.setString(4, new Formatando().getHoraAtual());
				cp.setInt(5, ex.getFK_funcionario());
				cp.setInt(6	, ex.getFK_empresa());
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

}
