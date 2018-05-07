package ao.co.cligest.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import ao.co.cligest.entidades.Diverso;

public class OutrosServicosDAO {

	Connection con;
	
	public List<Diverso> listaUltimosProcedimentos ()
	{
		String sql = "SELECT   `prc`.`id_procedimento`, `prc`.`procedimento`,  `prc`.`preco`,  `prc`.`preco_venda`,  `prc`.`FK_taxa`,  `tx`.`taxa` "
				+ " FROM   `tblprocedimentos` `prc`,   `tbltaxas` `tx` WHERE   `tx`.`id_taxa` = `prc`.`FK_taxa` order by `prc`.`procedimento` asc Limit 30 ";
		List<Diverso> lista = new ArrayList<Diverso>();
		try {
			con = Conexao.getConexao();
			PreparedStatement ps = con.prepareStatement(sql);
			ResultSet rs = ps.executeQuery();
			while (rs.next()) {
				Diverso	ds = new Diverso();
				ds.setId_procedimento(rs.getInt("id_procedimento"));
				ds.setProcedimento(rs.getString("procedimento"));
				ds.setPreco(rs.getDouble("preco"));
				ds.setTaxa(rs.getDouble("taxa"));
				lista.add(ds);
				
			}
			ps.close();
			con.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return lista;
	}
	
	public List<Diverso> listaUltimosProcedimentos (String aux)
	{
		String sql = "SELECT   `prc`.`id_procedimento`, `prc`.`procedimento`,  `prc`.`preco`,  `prc`.`preco_venda`,  `prc`.`FK_taxa`,  `tx`.`taxa` "
				+ " FROM   `tblprocedimentos` `prc`,   `tbltaxas` `tx` WHERE   `tx`.`id_taxa` = `prc`.`FK_taxa` and `prc`.`procedimento` Like ? ";
		List<Diverso> lista = new ArrayList<Diverso>();
		try {
			con = Conexao.getConexao();
			PreparedStatement ps = con.prepareStatement(sql);
			ps.setString(1, aux+"%");
			ResultSet rs = ps.executeQuery();
			while (rs.next()) {
				Diverso	ds = new Diverso();
				ds.setId_procedimento(rs.getInt("id_procedimento"));
				ds.setProcedimento(rs.getString("procedimento"));
				ds.setPreco(rs.getDouble("preco"));
				ds.setTaxa(rs.getDouble("taxa"));
				
				lista.add(ds);
				
			}
			ps.close();
			con.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return lista;
	}
	
	public Diverso buscaProcedimento(int aux){
		String sql = "Select * from tblprocedimentos where id_procedimento = ?";
		Diverso ds = new Diverso();
		try {
			con = Conexao.getConexao();
			PreparedStatement ps = con.prepareStatement(sql);
			ps.setInt(1, aux);
			ResultSet rs = ps.executeQuery();
			if(rs.next()){
				ds.setId_procedimento(rs.getInt("id_procedimento"));
				ds.setProcedimento(rs.getString("procedimento"));
				ds.setPreco(rs.getDouble("preco"));
				ds.setId_taxa(rs.getInt("FK_taxa"));
				ds.setDescricao(rs.getString("observacao"));
			}
			ps.close();
			con.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return ds;
	}
	
	public void delProcedimento(int aux){
		String sql = "Delete from tblprocedimentos where id_procedimento = ?";
		 
		try {
			con = Conexao.getConexao();
			PreparedStatement ps = con.prepareStatement(sql);
			ps.setInt(1, aux);
			ps.execute();
			 
			ps.close();
			con.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
		 
	}
	
	public Diverso XeckNomeProcedimento(String aux){
		String sql = "Select * from tblprocedimentos where procedimento = ?";
		Diverso ds = new Diverso();
		try {
			con = Conexao.getConexao();
			PreparedStatement ps = con.prepareStatement(sql);
			ps.setString(1, aux);
			ResultSet rs = ps.executeQuery();
			if(rs.next()){
				ds.setId_procedimento(rs.getInt("id_procedimento"));
				ds.setProcedimento(rs.getString("procedimento"));
			 
			}
			ps.close();
			con.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return ds;
	}
	
	public void novoCadastroProcedimento(Diverso ds){
		String sql = "Insert  into tblprocedimentos (procedimento,preco,FK_taxa,preco_venda,observacao) value (?,?,?,?,?)";
		try {
			con = Conexao.getConexao();
			PreparedStatement ps = con.prepareStatement(sql);
			ps.setString(1, ds.getProcedimento());
			ps.setDouble(2, ds.getPreco());
			ps.setInt(3, ds.getId_taxa());
			ps.setDouble(4, ds.getPreco());
			ps.setString(5, ds.getDescricao());
			ps.execute();
			ps.close();
			con.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	public void EditCadastroProcedimento(Diverso ds){
		String sql = "Update tblprocedimentos set procedimento = ?,preco = ?,FK_taxa = ?,preco_venda = ?,observacao = ? where id_procedimento = ?";
		try {
			con = Conexao.getConexao();
			PreparedStatement ps = con.prepareStatement(sql);
			ps.setString(1, ds.getProcedimento());
			ps.setDouble(2, ds.getPreco());
			ps.setInt(3, ds.getId_taxa());
			ps.setDouble(4, ds.getPreco());
			ps.setString(5, ds.getDescricao());
			ps.setInt(6, ds.getId_procedimento());
			ps.execute();
			ps.close();
			con.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	public List<Diverso> listaUltimosMateriais (){
		String sql = "SELECT `mt`.`id_material`,`mt`.`material`,`mt`.`quantidade`, `ff`.`forma_farmaceutica`, `mt`.`preco`,`mt`.`FK_taxa`,`tx`.`taxa`,`mt`.`preco_venda` "
				   +" FROM `tblmateriais` `mt`,  `tblformafarmaceutica` `ff` ,    `tbltaxas` `tx` "
				   +" Where `mt`.`FK_taxa` = `tx`.`id_taxa` and  `mt`.`FK_apresentacao` =    `ff`.`id_forma_farmaceutica` order by `mt`.`material` asc Limit 30 ";
		List<Diverso> lista = new ArrayList<Diverso>();
		
		try {
			con = Conexao.getConexao();
			PreparedStatement ps = con.prepareStatement(sql);
			ResultSet rs = ps.executeQuery();
			while(rs.next()){
				Diverso ds = new Diverso();
				ds.setId_material(rs.getInt("id_material"));
				ds.setMaterial(rs.getString("material"));
				ds.setQtd_material(rs.getInt("quantidade"));
				ds.setPreco(rs.getDouble("preco"));
				ds.setPreco_venda(rs.getDouble("preco_venda"));
				ds.setDescricao(rs.getString("forma_farmaceutica"));
				ds.setTaxa(rs.getDouble("taxa"));
				lista.add(ds);
			}
			ps.close();
			con.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return lista;
	}
	
	public List<Diverso> listaUltimosMateriais (String aux){
		String sql = "SELECT `mt`.`id_material`,`mt`.`material`,`mt`.`quantidade`, `ff`.`forma_farmaceutica`, `mt`.`preco`,`mt`.`FK_taxa`,`tx`.`taxa`,`mt`.`preco_venda` "
				   +" FROM `tblmateriais` `mt`,  `tblformafarmaceutica` `ff` ,    `tbltaxas` `tx` "
				   +" Where `mt`.`FK_taxa` = `tx`.`id_taxa` and  `mt`.`FK_apresentacao` =    `ff`.`id_forma_farmaceutica` "
				   + " AND material like ?   ";
		 
		List<Diverso> lista = new ArrayList<Diverso>();
		
		try {
			con = Conexao.getConexao();
			PreparedStatement ps = con.prepareStatement(sql);
			ps.setString(1, aux+"%");
			ResultSet rs = ps.executeQuery();
			while(rs.next()){
				Diverso ds = new Diverso();
				ds.setId_material(rs.getInt("id_material"));
				ds.setMaterial(rs.getString("material"));
				ds.setQtd_material(rs.getInt("quantidade"));
				ds.setPreco(rs.getDouble("preco"));
				ds.setPreco_venda(rs.getDouble("preco_venda"));
				ds.setTaxa(rs.getDouble("taxa"));
				lista.add(ds);
			}
			ps.close();
			con.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return lista;
	}
	
	public Diverso buscaMaterial(int aux){
		String sql = "Select * from tblmateriais where id_material = ?";
		Diverso ds = new Diverso();
		try {
			con = Conexao.getConexao();
			PreparedStatement ps = con.prepareStatement(sql);
			ps.setInt(1, aux);
			ResultSet rs = ps.executeQuery();
			if(rs.next()){
				ds.setId_material(rs.getInt("id_material"));
				ds.setMaterial(rs.getString("material"));
				ds.setQtd_material(rs.getInt("quantidade"));
				ds.setPreco(rs.getDouble("preco"));
				ds.setId_taxa(rs.getInt("FK_taxa"));
				ds.setPreco_venda(rs.getDouble("preco_venda"));
				ds.setFK_apresentacao(rs.getInt("FK_apresentacao")); 
			}
			ps.close();
			con.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return ds;
	}
	
	
	public void delMaterial(int aux){
		String sql = "Delete from tblmateriais where id_material = ?";
		 
		try {
			con = Conexao.getConexao();
			PreparedStatement ps = con.prepareStatement(sql);
			ps.setInt(1, aux);
			ps.execute();
			 
			ps.close();
			con.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
		 
	}
	public List<Diverso> buscaApresentacao (){
		String sql = "Select * from tblformafarmaceutica ";
		List<Diverso> lista = new ArrayList<Diverso>();
		
		try {
			con = Conexao.getConexao();
			PreparedStatement ps = con.prepareStatement(sql);
			 
			ResultSet rs = ps.executeQuery();
			while(rs.next()){
				Diverso ds = new Diverso();
				ds.setFK_apresentacao(rs.getInt("id_forma_farmaceutica"));
				ds.setDescricao(rs.getString("forma_farmaceutica"));
				lista.add(ds); 
			}
			ps.close();
			con.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return lista;
	}
	
	public void novoCadMaterial(Diverso ds){
		String sql = "insert into tblmateriais (material,quantidade,preco,FK_taxa,preco_venda,FK_apresentacao) value (?,?,?,?,?,?)";
		try {
			con = Conexao.getConexao();
			PreparedStatement ps = con.prepareStatement(sql);
			ps.setString(1, ds.getMaterial());
			ps.setInt(2, ds.getQtd_material());
			ps.setDouble(3, ds.getPreco());
			ps.setInt(4, ds.getId_taxa());
			ps.setDouble(5, ds.getPreco_venda());
			ps.setInt(6, ds.getFK_apresentacao());
			ps.execute();
			ps.close();
			con.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	public void editCadMaterial(Diverso ds){
		String sql = "Update tblmateriais set material = ?, quantidade = ?,preco = ?,FK_taxa = ?,preco_venda = ?, FK_apresentacao = ? where id_material = ?";
		try {
			con = Conexao.getConexao();
			PreparedStatement ps = con.prepareStatement(sql);
			ps.setString(1, ds.getMaterial());
			ps.setInt(2, ds.getQtd_material());
			ps.setDouble(3, ds.getPreco());
			ps.setInt(4, ds.getId_taxa());
			ps.setDouble(5, ds.getPreco_venda());
			ps.setInt(6, ds.getFK_apresentacao());
			ps.setInt(7, ds.getId_material());
			ps.execute();
			ps.close();
			con.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	public Diverso XeckNomeMaterial(String aux){
		String sql = "Select * from tblmateriais where material = ?";
		Diverso ds = new Diverso();
		try {
			con = Conexao.getConexao();
			PreparedStatement ps = con.prepareStatement(sql);
			ps.setString(1, aux);
			ResultSet rs = ps.executeQuery();
			if(rs.next()){
				ds.setId_material(rs.getInt("id_material"));
				ds.setMaterial(rs.getString("material"));
			 
			}
			ps.close();
			con.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return ds;
	}
	
	public List<Diverso> listaUltimosMedicamentos (){
		String sql = "SELECT   `md`.`id_medicamento`, `md`.`medicamento`, `ff`.`forma_farmaceutica`, `md`.`quantidade`, `md`.`preco`, `md`.`preco_venda`,`md`.`FK_taxa`,  `tx`.`taxa` "
				+ " FROM   `tbltaxas` `tx` , `tblmedicamentosbue` `md` ,`tblformafarmaceutica` `ff`  "
				+ " WHERE  `md`.`FK_taxa` = `tx`.`id_taxa` and `md`.`FK_apresentacao` =    `ff`.`id_forma_farmaceutica` order by `md`.`medicamento` Limit 30 ";
		List<Diverso> lista = new ArrayList<Diverso>();
		
		try {
			con = Conexao.getConexao();
			PreparedStatement ps = con.prepareStatement(sql);
			ResultSet rs = ps.executeQuery();
			while(rs.next()){
				Diverso ds = new Diverso();
				ds.setId_medicamento(rs.getInt("id_medicamento"));
				ds.setMedicamento(rs.getString("medicamento"));
				ds.setQtd_medicamento(rs.getInt("quantidade"));
				ds.setPreco(rs.getDouble("preco"));
				ds.setDescricao(rs.getString("forma_farmaceutica"));
				ds.setPreco_venda(rs.getDouble("preco_venda"));
				ds.setTaxa(rs.getDouble("taxa"));
				lista.add(ds);
			}
			ps.close();
			con.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return lista;
	}
	
	public List<Diverso> listaUltimosMedicamentos (String aux){
		String sql = "SELECT   `md`.`id_medicamento`, `md`.`medicamento`, `md`.`quantidade`, `md`.`preco`, `md`.`preco_venda`,`md`.`FK_taxa`,  `tx`.`taxa` "
				+ " FROM   `tbltaxas` `tx` , `tblmedicamentosbue` `md` "
				+ " WHERE  `md`.`FK_taxa` = `tx`.`id_taxa` AND `md`.`medicamento` Like ? Limit 30 ";
		List<Diverso> lista = new ArrayList<Diverso>();
		
		try {
			con = Conexao.getConexao();
			PreparedStatement ps = con.prepareStatement(sql);
			ps.setString(1, aux+"%");
			ResultSet rs = ps.executeQuery();
			while(rs.next()){
				Diverso ds = new Diverso();
				ds.setId_medicamento(rs.getInt("id_medicamento"));
				ds.setMedicamento(rs.getString("medicamento"));
				ds.setQtd_medicamento(rs.getInt("quantidade"));
				ds.setPreco(rs.getDouble("preco"));
				ds.setPreco_venda(rs.getDouble("preco_venda"));
				ds.setTaxa(rs.getDouble("taxa"));
				lista.add(ds);
			}
			ps.close();
			con.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return lista;
	}
	
	public Diverso buscaMedicamento(int aux){
		String sql = "Select * from tblmedicamentosbue where id_medicamento = ?";
		Diverso ds = new Diverso();
		try {
			con = Conexao.getConexao();
			PreparedStatement ps = con.prepareStatement(sql);
			ps.setInt(1, aux);
			ResultSet rs = ps.executeQuery();
			if(rs.next()){
				ds.setId_medicamento(rs.getInt("id_medicamento"));
				ds.setMedicamento(rs.getString("medicamento"));
				ds.setQtd_medicamento(rs.getInt("quantidade"));
				ds.setPreco(rs.getDouble("preco"));
				ds.setId_taxa(rs.getInt("FK_taxa"));
				ds.setPreco_venda(rs.getDouble("preco_venda"));
				ds.setFK_apresentacao(rs.getInt("FK_apresentacao")); 
			}
			ps.close();
			con.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return ds;
	}
	
	public Diverso XeckNomeMedicamento(String aux){
		String sql = "Select * from tblmedicamentosbue where medicamento = ?";
		Diverso ds = new Diverso();
		try {
			con = Conexao.getConexao();
			PreparedStatement ps = con.prepareStatement(sql);
			ps.setString(1, aux);
			ResultSet rs = ps.executeQuery();
			if(rs.next()){
				ds.setId_medicamento(rs.getInt("id_medicamento"));
				ds.setMedicamento(rs.getString("medicamento"));
			 
			}
			ps.close();
			con.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return ds;
	}
	
	public void delMedicamento(int aux){
		String sql = "Delete from tblmedicamentosbue where id_medicamento= ?";
		 
		try {
			con = Conexao.getConexao();
			PreparedStatement ps = con.prepareStatement(sql);
			ps.setInt(1, aux);
			ps.execute();
			 
			ps.close();
			con.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
		 
	}
	
	public void novoCadMedicamentoIntBUE(Diverso ds){
		String sql = "Insert into tblmedicamentosbue (medicamento,quantidade,preco,preco_venda,FK_taxa,FK_apresentacao) value(?,?,?,?,?,?)";
		try {
			con = Conexao.getConexao();
			PreparedStatement ps = con.prepareStatement(sql);
			ps.setString(1, ds.getMedicamento());
			ps.setInt(2, ds.getQtd_medicamento());
			ps.setDouble(3, ds.getPreco());
			ps.setDouble(4, ds.getPreco_venda());
			ps.setInt(5, ds.getId_taxa());
			ps.setInt(6, ds.getFK_apresentacao());
			ps.execute();
			ps.close();
			con.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	public void editCadMedicamentoIntBUE(Diverso ds){
		String sql = "Update tblmedicamentosbue set medicamento = ?, quantidade = ?, preco = ?, preco_venda = ?, FK_taxa = ?, FK_apresentacao = ? Where id_medicamento = ?";
		try {
			con = Conexao.getConexao();
			PreparedStatement ps = con.prepareStatement(sql);
			ps.setString(1, ds.getMedicamento());
			ps.setInt(2, ds.getQtd_medicamento());
			ps.setDouble(3, ds.getPreco());
			ps.setDouble(4, ds.getPreco_venda());
			ps.setInt(5, ds.getId_taxa());
			ps.setInt(6, ds.getFK_apresentacao());
			ps.setInt(7, ds.getId_medicamento());
			ps.execute();
			ps.close();
			con.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	public List<Diverso> listaUltimosServicos (){
		String sql = "SELECT `out`.`id_tservico`,`out`.`descricao`,`out`.`preco`, `out`.`FK_taxa`, `out`.`observacao`, `tx`.`taxa` "
				+ " FROM  `tbltaxas` `tx`,`tbloutroservico` `out` WHERE `out`.`FK_taxa` = `tx`.`id_taxa` order by `out`.`descricao` Limit 30 ";
		List<Diverso> lista = new ArrayList<Diverso>();
		
		try {
			con = Conexao.getConexao();
			PreparedStatement ps = con.prepareStatement(sql);
			ResultSet rs = ps.executeQuery();
			while(rs.next()){
				Diverso ds = new Diverso();
				ds.setId_servico(rs.getInt("id_tservico"));
				ds.setServico(rs.getString("descricao"));
				ds.setPreco(rs.getDouble("preco"));
			 
				ds.setTaxa(rs.getDouble("taxa"));
				lista.add(ds);
			}
			ps.close();
			con.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return lista;
	}
	
	public List<Diverso> listaUltimosServicos (String aux){
		String sql = "SELECT `out`.`id_tservico`,`out`.`descricao`,`out`.`preco`, `out`.`FK_taxa`, `out`.`observacao`, `tx`.`taxa` "
				+ " FROM  `tbltaxas` `tx`,`tbloutroservico` `out` WHERE `out`.`FK_taxa` = `tx`.`id_taxa` AND `out`.`descricao` Like ? ";
		List<Diverso> lista = new ArrayList<Diverso>();
		
		try {
			con = Conexao.getConexao();
			PreparedStatement ps = con.prepareStatement(sql);
			ps.setString(1, aux+"%");
			ResultSet rs = ps.executeQuery();
			while(rs.next()){
				Diverso ds = new Diverso();
				ds.setId_servico(rs.getInt("id_tservico"));
				ds.setServico(rs.getString("descricao"));
				ds.setPreco(rs.getDouble("preco"));
			 
				ds.setTaxa(rs.getDouble("taxa"));
				lista.add(ds);
			}
			ps.close();
			con.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return lista;
	}
	
	public Diverso buscaTServicos(int aux){
		String sql = "Select * from tbloutroservico where id_tservico = ?";
		Diverso ds = new Diverso();
		try {
			con = Conexao.getConexao();
			PreparedStatement ps = con.prepareStatement(sql);
			ps.setInt(1, aux);
			ResultSet rs = ps.executeQuery();
			if(rs.next()){
				ds.setId_servico(rs.getInt("id_tservico"));
				ds.setServico(rs.getString("descricao"));
				 
				ds.setPreco(rs.getDouble("preco"));
				ds.setId_taxa(rs.getInt("FK_taxa"));
				ds.setDescricao(rs.getString("observacao")); 
			}
			ps.close();
			con.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return ds;
	}
	
	public Diverso buscaTServicos(String aux){
		String sql = "SELECT   `t`.`taxa`,  `s`.`id_tservico`, `s`.`fk_taxa`, `s`.`descricao`,  ((`t`.`taxa` / 100 * `s`.`preco`) + `s`.`preco`) AS 'preco_tx',  `s`.`preco` "
				+ " FROM   `tbloutroservico` `s`, `tbltaxas` `t` WHERE  `s`.`FK_taxa` = `t`.`id_taxa` "
				+ " AND `s`.`descricao` = ? ";
		Diverso ds = null;
		
		try {
			con = Conexao.getConexao();
			PreparedStatement ps = con.prepareStatement(sql);
			ps.setString(1, aux);
			ResultSet rs = ps.executeQuery();
			if(rs.next()){
				ds = new Diverso();
				ds.setId_servico(rs.getInt("id_tservico"));
				ds.setServico(rs.getString("descricao"));
				ds.setPreco(rs.getDouble("preco_tx"));
				ds.setId_taxa(rs.getInt("FK_taxa"));
				System.out.println(ds.getServico()); 
			}
			ps.close();
			con.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return ds;
	}
	
	
	public Diverso XeckNomeTServico(String aux){
		String sql = "Select * from tbloutroservico where descricao = ?";
		Diverso ds = new Diverso();
		try {
			con = Conexao.getConexao();
			PreparedStatement ps = con.prepareStatement(sql);
			ps.setString(1, aux);
			ResultSet rs = ps.executeQuery();
			if(rs.next()){
				ds.setId_servico(rs.getInt("id_tservico"));
				ds.setServico(rs.getString("descricao"));
			 
			}
			ps.close();
			con.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return ds;
	}
	
	public void novoCadServicosInternamentoBUE(Diverso ds){
		String sql = "Insert into tbloutroservico (descricao,preco,FK_taxa,observacao) value (?,?,?,?)";
		try {
			con = Conexao.getConexao();
			PreparedStatement ps = con.prepareStatement(sql);
			ps.setString(1, ds.getServico());
			ps.setDouble(2, ds.getPreco());
			ps.setInt(3, ds.getId_taxa());
			ps.setString(4, ds.getDescricao());
			ps.execute();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	public void editCadServicosInternamentoBUE(Diverso ds){
		String sql = "Update tbloutroservico set descricao= ?, preco = ?, FK_taxa = ?, observacao = ? Where id_tservico = ?";
		try {
			con = Conexao.getConexao();
			PreparedStatement ps = con.prepareStatement(sql);
			ps.setString(1, ds.getServico());
			ps.setDouble(2, ds.getPreco());
			ps.setInt(3, ds.getId_taxa());
			ps.setString(4, ds.getDescricao());
			ps.setInt(5, ds.getId_servico());
			ps.execute();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	public void delTServico(int aux){
		String sql = "Delete from tbloutroservico where id_tservico= ?";
		 
		try {
			con = Conexao.getConexao();
			PreparedStatement ps = con.prepareStatement(sql);
			ps.setInt(1, aux);
			ps.execute();
			 
			ps.close();
			con.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
		 
	}
}
