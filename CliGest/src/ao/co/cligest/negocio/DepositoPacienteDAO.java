package ao.co.cligest.negocio;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

import ao.co.cligest.entidades.DepositoEntity;

public class DepositoPacienteDAO {
	
	Connection con; 
	Formatando ft = new Formatando();
	
	 
	public int novoDeposito(DepositoEntity p){
		String sql = "Insert into tbldeposito (FK_entidade,sector,tipo_depositante,valor_deposito,data_reg,hora_reg) value (?,?,?,?,?,?)";
		int ultimoCod = 0; 
		try {
			con = Conexao.getConexao();
			PreparedStatement ps = con.prepareStatement(sql);	
			ps.setInt(1, p.getFK_entidade());
			ps.setString(2, p.getSector());
			ps.setInt(3, p.getTipo_depositante());
			ps.setDouble(4, p.getValor_deposito());
			ps.setDate(5, ft.data_registo());
			ps.setString(6, ft.getHoraAtual());
			ps.execute();
			ResultSet rs = ps.executeQuery("SELECT LAST_INSERT_ID()");
				 if(rs.next()){
					ultimoCod = rs.getInt(1); 
				 }
				ps.close();
			 
				
			con.close();		
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return ultimoCod;
	}
	
	public void depositanteTerceiro(DepositoEntity dp){
		String sql = "Insert into tbldepositoterceiro (FK_deposito,FK_tipo_documento,FK_afiliacao,primeiro_nome,ultimo_nome,telefone,numero_documento,nome_ficheiro) value (?,?,?,?,?,?,?,?)";
		try {
			con = Conexao.getConexao();
			PreparedStatement ps = con.prepareStatement(sql);
			ps.setInt(1, dp.getId_deposito());
			ps.setInt(2, dp.getTipo_doc());
			ps.setInt(3, dp.getIdAfiliacao());
			ps.setString(4, dp.getNome());
			ps.setString(5, dp.getApelido());
			ps.setLong(6, dp.getTelefonep());
			ps.setString(7, dp.getNumero_doc());
			ps.setString(8, dp.getNome_ficheiro());
			ps.execute();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	public List<DepositoEntity> buscarDepositos(){
		String sql = "SELECT  `dp`.`id_deposito`,`dp`.`FK_entidade`, `dp`.`sector`, `dp`.`tipo_depositante`, `dp`.`valor_deposito`, `dp`.`data_reg`, `dp`.`hora_reg`, `vp`.`NumeroProcesso`,  `vp`.`NomeCompleto`,  `vp`.`data_nascimento`, `gn`.`genero`"
				  + " FROM   `tbldeposito` `dp`, `vwcidadaopacientenomecompleto` `vp`,`tblgenero` `gn` "
				  + " WHERE `dp`.`FK_entidade` =  `vp`.`Entidade` and `vp`.`FK_genero` = `gn`.`id_genero` ";
		List<DepositoEntity> lista = new ArrayList<DepositoEntity>();
		try {
			con = Conexao.getConexao();
			PreparedStatement ps = con.prepareStatement(sql);
			ResultSet rs = ps.executeQuery();
			while(rs.next()){
				DepositoEntity dp = new DepositoEntity();
				dp.setNumero_processo(rs.getString("NumeroProcesso"));
				dp.setNome_paciente(rs.getString("NomeCompleto"));
				dp.setIdade(rs.getDate("data_nascimento"));
				dp.setValor_deposito(rs.getDouble("valor_deposito"));
				dp.setSector(rs.getString("sector"));
				dp.setId_deposito(rs.getInt("id_deposito"));
				dp.setFK_entidade(rs.getInt("FK_entidade"));
				dp.setNomegenero(rs.getString("genero"));
				dp.setHora_reg(rs.getString("hora_reg"));
				Calendar data = Calendar.getInstance();
				data.setTime(rs.getDate("data_reg"));
				dp.setData_reg(data);
				if(rs.getInt("tipo_depositante") == 1)
					dp.setDescricao("Proprio");
				else
					dp.setDescricao("Terceiro");
				lista.add(dp);
			}
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		return lista;
	}
	
	public DepositoEntity buscarSeuDepositos(int pac){
		String sql = "SELECT  *   FROM   `tbldeposito` `dp`	WHERE `dp`.`FK_entidade` =  ? ";
		DepositoEntity dp = new DepositoEntity();
		try {
			con = Conexao.getConexao();
			PreparedStatement ps = con.prepareStatement(sql);
			ps.setInt(1, pac);
			ResultSet rs = ps.executeQuery();
			if(rs.next()){
				
				 
				dp.setValor_deposito(rs.getDouble("valor_deposito"));
			 
				dp.setId_deposito(rs.getInt("id_deposito"));
				dp.setFK_entidade(rs.getInt("FK_entidade"));
 
				dp.setHora_reg(rs.getString("hora_reg"));
				Calendar data = Calendar.getInstance();
				data.setTime(rs.getDate("data_reg"));
				dp.setData_reg(data);
				if(rs.getInt("tipo_depositante") == 1)
					dp.setDescricao("Proprio");
				else
					dp.setDescricao("Terceiro");
				 
			}
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		return dp;
	}
}
