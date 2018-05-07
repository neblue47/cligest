package ao.co.cligest.negocio;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

import ao.co.cligest.entidades.Facturacao;
import ao.co.cligest.entidades.Paciente;
import ao.co.cligest.entidades.Produtos;

public class VacinacaoDAO {

	private Connection con ;
	Formatando ft = new Formatando();
	
	//1-INSERIR PRODUTOS
			public int addVacina(Produtos pd)
			{
				int ultimoCod = 0;
			    String sql = "INSERT INTO tblvacinas_efectuadas (fk_dosagem,fk_paciente,lote,fk_medicamento,data_vacina,fk_funcionario,FK_factura) VALUES (?,?,?,?,?,?,?)";
			    try {
					 
					 //1-tblfactura
			    	 con = Conexao.getConexao();
			    	 PreparedStatement cp = con.prepareStatement(sql);
					 cp.setInt(1, pd.getFK_dosagem());
					 cp.setInt(2, pd.getFK_paciente());
					 cp.setString(3, pd.getNumero_lote_sere());
					 cp.setInt(4, pd.getFk_produto());
					 cp.setDate(5, new Formatando().data_registo());
					 cp.setInt(6, pd.getFK_funcionario());
					 cp.setInt(7, pd.getId());
					 cp.execute();
					 ResultSet rs = cp.executeQuery("SELECT LAST_INSERT_ID()");
					 if(rs.next()){
						ultimoCod = rs.getInt(1); 
					 }
					 cp.close();
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
			
			
	
}
