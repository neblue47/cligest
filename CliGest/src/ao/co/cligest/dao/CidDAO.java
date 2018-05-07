//Versao 0.4
//Autor: Mario Bandeira
//Data de criacao: 02/02/2015
//Ultima data de actualizacao: 28/02/2015

package ao.co.cligest.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.SQLIntegrityConstraintViolationException;
import java.util.List;

import ao.co.cligest.entidades.Capitulo;
import ao.co.cligest.entidades.CategoriaDGrupo;
import ao.co.cligest.entidades.Cid;
import ao.co.cligest.entidades.GrupoDeCapitulo;

import java.sql.ResultSet;
import java.util.ArrayList;



public class CidDAO {
	
	private Connection con = null;
	private PreparedStatement ps = null;
	private ResultSet rs = null;

	// 1 tabela - entidade
 

	public boolean criar_capitulo(Capitulo cident)
	{
		boolean ok = false;
		String sql = "INSERT INTO tblcapitulo (codigo_capitulo,descricao_capitulo) VALUE (?,?)";
		try {
			con = Conexao.getConexao();
			PreparedStatement enccid = con.prepareStatement(sql);
			enccid.setString(1, cident.getCodigoCapitulo());
			enccid.setString(2, cident.getDescricaoCapitulo());
			if(!enccid.execute()){
				ok = true;
			}
			enccid.close();				
		} 
		catch (SQLIntegrityConstraintViolationException e) {
			
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
	
	public List<Capitulo> getCapitulo()
	{
		
		List<Capitulo> listadecapitulo = new ArrayList<Capitulo>();
		
		String sql = "SELECT * FROM tblcapitulo order by codigo_capitulo";
		
		try {
			con = Conexao.getConexao();
			PreparedStatement preparador = con.prepareStatement(sql);
			ResultSet rs = preparador.executeQuery();
		
			while (rs.next()){
        	
				Capitulo capitulo = new Capitulo();
				
				capitulo.setIdCapitulo(rs.getInt("id_capitulo"));
				capitulo.setCodigoCapitulo(rs.getString("codigo_capitulo"));
				capitulo.setDescricaoCapitulo(rs.getString("descricao_capitulo"));
        	
				listadecapitulo.add(capitulo);
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
		return listadecapitulo;
	}
	
	public List<GrupoDeCapitulo> getGruposCapitulo(int codCap)
	{
		
		List<GrupoDeCapitulo> listadegrupo = new ArrayList<GrupoDeCapitulo>();
		String sql = "SELECT * FROM tblgrupodcapitulo where FK_capitulo = ? order by id_grupo_capitulo";
		
		try {
			con = Conexao.getConexao();
			PreparedStatement preparador = con.prepareStatement(sql);
			preparador.setInt(1, codCap);
			ResultSet rs = preparador.executeQuery();
			while (rs.next()){
				GrupoDeCapitulo grupo = new GrupoDeCapitulo();
				grupo.setCodigoGrupo(rs.getString("codigo_grupo"));
				grupo.setDescricaoGrupo(rs.getString("descricao_grupo"));
				grupo.setIdGrupoDCapitulo(rs.getInt("id_grupo_capitulo"));
				listadegrupo.add(grupo);
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
		return listadegrupo;
	}
	
	public List<CategoriaDGrupo> getCategorizacaoGrupos(int codCap)
	{
		
		List<CategoriaDGrupo> listadecategoria = new ArrayList<CategoriaDGrupo>();
		String sql = "SELECT * FROM tblcategoria where FK_grupo = ? order by id_categorizar_grupo";
		try {
			con = Conexao.getConexao();
			PreparedStatement preparador = con.prepareStatement(sql);
			preparador.setInt(1, codCap);
			ResultSet rs = preparador.executeQuery();
			while (rs.next()){
				CategoriaDGrupo categoria = new CategoriaDGrupo();
				categoria.setCategorizaGrupo(rs.getString("categoriza_grupo"));
				categoria.setDescricaoCategoria(rs.getString("descricao_categoria"));
				categoria.setIdCategorizarGrupo(rs.getInt("id_categorizar_grupo"));
				listadecategoria.add(categoria);
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
		return listadecategoria;
	}
	
	public List<Cid> getCIDsCategoria(int codCap)
	{
		
		List<Cid> listadecids = new ArrayList<Cid>();
		String sql = "SELECT * FROM vwcids_detalhes where FK_categoria = ? order by FK_categoria  ";
		try {
			con = Conexao.getConexao();
			PreparedStatement preparador = con.prepareStatement(sql);
			preparador.setInt(1, codCap);
			ResultSet rs = preparador.executeQuery();
			while (rs.next()){
				Cid cids = new Cid();
				cids.setCodigocid(rs.getString("cid"));
				cids.setDescricao(rs.getString("descricao_cid"));
				cids.setListarclassificacao(rs.getString("classificacao"));
				cids.setListarrestricaodsexo(rs.getString("restricao_dsexo"));
				cids.setNota(rs.getString("nota"));
				cids.setListarcausaobito(rs.getString("causa_obito"));
				listadecids.add(cids);
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
		return listadecids;
	}
	
	public Cid getCID(String codCap)
	{
		
		Cid cids = new Cid();
		String sql = "SELECT * FROM vwlistarcid where cid Like ?  ";
		try {
			con = Conexao.getConexao();
			PreparedStatement preparador = con.prepareStatement(sql);
			preparador.setString(1, codCap);
			ResultSet rs = preparador.executeQuery();
			if (rs.next()){
				
				cids.setCodigocid(rs.getString("cid"));
				cids.setDescricao(rs.getString("descricao_cid"));
				cids.setListarclassificacao(rs.getString("classificacao"));
				cids.setListarrestricaodsexo(rs.getString("restricao_dsexo"));
				cids.setNota(rs.getString("nota"));
				cids.setListarcausaobito(rs.getString("causa_obito"));
				 
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
		return cids;
	}
	
	
	public Capitulo getUmCapitulo(int capid) 
	{
		Capitulo capmodf = new Capitulo();
		
		String sql = "SELECT * FROM tblcapitulo WHERE id_capitulo = ?";
			
		try {
			con = Conexao.getConexao();	
			PreparedStatement preparador = con.prepareStatement(sql);
					
				preparador.setInt(1, capid);
					
				ResultSet rs = preparador.executeQuery();
					
				if (rs.next()){
					capmodf.setIdCapitulo(rs.getInt("id_capitulo"));
					capmodf.setCodigoCapitulo(rs.getString("codigo_capitulo"));
					capmodf.setDescricaoCapitulo(rs.getString("descricao_capitulo"));
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
	    return capmodf;
	}
	
	public void modificarCapitulo(Capitulo cident)
	{			
		String sql = "UPDATE tblcapitulo SET codigo_capitulo= ?,descricao_capitulo = ? WHERE id_capitulo = ?";
		try {
			con = Conexao.getConexao();	
			PreparedStatement modcap = con.prepareStatement(sql);
					
				modcap.setString(1, cident.getCodigoCapitulo());
				modcap.setString(2, cident.getDescricaoCapitulo());
				modcap.setInt(3, cident.getIdCapitulo());
				modcap.execute();
				modcap.close();
					
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
	
	public void ApagarCapitulo(int cident)
	{
		String sql = "DELETE FROM tblcapitulo WHERE id_capitulo=?";
		try {
			con = Conexao.getConexao();
			PreparedStatement ent = con.prepareStatement(sql);
			ent.setInt(1, cident);
			ent.execute();
			ent.close();
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
	
	//######################################################################################################
	//SESSAO RESERVADA PARA A GEST�O DO GRUPO DE CAPITULO
	public List<GrupoDeCapitulo> todoGrupo()
	{
		
		List<GrupoDeCapitulo> listaDeGrupo = new ArrayList<GrupoDeCapitulo>();
		
		String sql = "SELECT * FROM vwcapituloegrupodecapitulo";
		
		try {
			con = Conexao.getConexao();
			PreparedStatement preparador = con.prepareStatement(sql);
			ResultSet rs = preparador.executeQuery();
			
			while (rs.next()){
				GrupoDeCapitulo grupo = new GrupoDeCapitulo();					
				grupo.setIdGrupoDCapitulo(rs.getInt("id_grupo_capitulo"));
				grupo.setCapitulolistado(rs.getString("codigo_capitulo"));
				grupo.setCodigoGrupo(rs.getString("codigo_grupo"));
				grupo.setDescricaoGrupo(rs.getString("descricao_grupo"));	        	
				listaDeGrupo.add(grupo);
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
		return listaDeGrupo;
	}
	
	public GrupoDeCapitulo umGrupodecapitulo(int capid) 
	{
		GrupoDeCapitulo modifgrupo = new GrupoDeCapitulo();
		
		String sql = "SELECT * FROM vwcapituloegrupodecapitulo WHERE id_grupo_capitulo = ?";
			
		try {
			con = Conexao.getConexao();	
			PreparedStatement preparador = con.prepareStatement(sql);
					
				preparador.setInt(1, capid);
					
				ResultSet rs = preparador.executeQuery();
					
				if (rs.next()){
					modifgrupo.setIdGrupoDCapitulo(rs.getInt("id_grupo_capitulo"));
					modifgrupo.setCapitulolistado(rs.getString("codigo_capitulo"));
					modifgrupo.setCodigoGrupo(rs.getString("codigo_grupo"));
					modifgrupo.setDescricaoGrupo(rs.getString("descricao_grupo"));
					modifgrupo.setFkCapitulo(rs.getInt("id_capitulo"));
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
	    return modifgrupo;
	}
	
	public void criargrupo(GrupoDeCapitulo grupcap)
	{
		String sql = "INSERT INTO tblgrupodcapitulo (FK_capitulo,codigo_grupo,descricao_grupo) VALUE (?,?,?)";
		try {
			con = Conexao.getConexao();	
			ps = con.prepareStatement(sql);
				
			ps.setInt(1, grupcap.getFkCapitulo());
			ps.setString(2, grupcap.getCodigoGrupo());
			ps.setString(3, grupcap.getDescricaoGrupo());
			ps.execute();			
		} catch (SQLException e) {
			e.printStackTrace();
		}
		catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally{
			try {
				ps.close();
				con.close();
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
	}
	
	public void modificarGrupoCapitulo(GrupoDeCapitulo grupcap)
	{			
		String sql = "UPDATE tblgrupodcapitulo SET FK_capitulo=?, codigo_grupo=?, descricao_grupo=? WHERE id_grupo_capitulo=?";
		try {
			con = Conexao.getConexao();	
			PreparedStatement modgru = con.prepareStatement(sql);
					
				modgru.setInt(1, grupcap.getFkCapitulo());
				modgru.setString(2, grupcap.getCodigoGrupo());
				modgru.setString(3, grupcap.getDescricaoGrupo());
				modgru.setInt(4, grupcap.getIdGrupoDCapitulo());
				modgru.execute();
				modgru.close();
					
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
	
	public void apagarGDCapitulo(int cident)
	{
		String sql = "DELETE FROM tblgrupodcapitulo WHERE id_grupo_capitulo=?";
		try {
			con = Conexao.getConexao();
			ps = con.prepareStatement(sql);
			ps.setInt(1, cident);
			ps.execute();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally{
			try{
				ps.close();
				con.close();
			}
			catch(SQLException ef){
				System.out.println("Erro finalizar: "+ef);
			}
		}
	}
	
	//#############################################################################
	//ESTA PARTE ESTA RESERVADA PARA AS OPERA��ES RELACIONADAS A GEST�O DA CATEGORIA
	//DO GRUPO.
	public void criarcategoria(CategoriaDGrupo catgru){
		String sql = "INSERT INTO tblcategoria (FK_capitulo,FK_grupo,categoriza_grupo,descricao_categoria) VALUE (?,?,?,?)";
		try {
			con = Conexao.getConexao();	
			PreparedStatement iserircat = con.prepareStatement(sql);
				
				iserircat.setInt(1, catgru.getFkcapitulo());
				iserircat.setInt(2, catgru.getFkgrupo());
				iserircat.setString(3, catgru.getCategorizaGrupo());
				iserircat.setString(4, catgru.getDescricaoCategoria());
				iserircat.execute();
				iserircat.close();
				
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
	
	public List<CategoriaDGrupo> todaCategoriaDGrupo()
	{
		List<CategoriaDGrupo> listarCategoriaDGrupo = new ArrayList<CategoriaDGrupo>();
		
		String sql = "SELECT * FROM vwcategoriadeumgrupo";
		
		try {
			con = Conexao.getConexao();
			PreparedStatement preparador = con.prepareStatement(sql);
			ResultSet rs = preparador.executeQuery();
		
			while (rs.next()){
        	
				CategoriaDGrupo catgru = new CategoriaDGrupo();
				
				catgru.setIdCategorizarGrupo(rs.getInt("id_categorizar_grupo"));
				catgru.setListarCodigoCapitulo(rs.getString("codigo_capitulo"));
				catgru.setListarCodigoGrupo(rs.getString("codigo_grupo"));
				catgru.setCategorizaGrupo(rs.getString("categoriza_grupo"));
				catgru.setDescricaoCategoria(rs.getString("descricao_categoria"));
        	
				listarCategoriaDGrupo.add(catgru);
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
		return listarCategoriaDGrupo;
	}
        
    //##################################################################################################
	//#################################PESQUISAR CATEGORIAS CADASTRADAS.
	public List<CategoriaDGrupo> pesquisarCategoria(String categoria){
		List<CategoriaDGrupo> modcat = new ArrayList<CategoriaDGrupo>();
		String sql = "SELECT * FROM vwcategoriadeumgrupo WHERE categoriza_grupo LIKE = ?";
            
		try {
			con = Conexao.getConexao();     
			ps = con.prepareStatement(sql);
			ps.setString(1, categoria);                  
			rs = ps.executeQuery();
                                    
			if (rs.next()){ 
				((CategoriaDGrupo) modcat).setIdCategorizarGrupo(rs.getInt("id_categorizar_grupo"));
				((CategoriaDGrupo) modcat).setListarCodigoCapitulo(rs.getString("codigo_capitulo"));
				((CategoriaDGrupo) modcat).setListarCodigoGrupo(rs.getString("codigo_grupo"));
				((CategoriaDGrupo) modcat).setCategorizaGrupo(rs.getString("categoriza_grupo"));
				((CategoriaDGrupo) modcat).setDescricaoCategoria(rs.getString("descricao_categoria"));
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}catch (Exception e) {
			e.printStackTrace();
		}finally{
			try{
				rs.close();
				ps.close();
				con.close();
			}
            catch(SQLException ef){
            	System.out.println("Erro finalizar: "+ef);
            }
		}
		return modcat;
    }
	
	public CategoriaDGrupo umaCategoriaDGrupo(int catid) 
	{
		CategoriaDGrupo modcat = new CategoriaDGrupo();
		
		String sql = "SELECT * FROM vwcategoriadeumgrupo WHERE id_categorizar_grupo = ?";
			
		try {
			con = Conexao.getConexao();	
			PreparedStatement preparador = con.prepareStatement(sql);
					
				preparador.setInt(1, catid);
					
				ResultSet rs = preparador.executeQuery();
					
				if (rs.next()){	
					modcat.setIdCategorizarGrupo(rs.getInt("id_categorizar_grupo"));
					modcat.setListarCodigoCapitulo(rs.getString("codigo_capitulo"));
					modcat.setListarCodigoGrupo(rs.getString("codigo_grupo"));
					modcat.setCategorizaGrupo(rs.getString("categoriza_grupo"));
					modcat.setDescricaoCategoria(rs.getString("descricao_categoria"));
					modcat.setFkcapitulo(rs.getInt("id_capitulo"));
					modcat.setFkgrupo(rs.getInt("id_grupo_capitulo"));
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
		
	    return modcat;
	}
	
	public void modificarCategoriadeumgrupo(CategoriaDGrupo idcat)
	{			
		String sql = "UPDATE tblcategoria SET FK_capitulo=?, FK_grupo=?, categoriza_grupo=?, descricao_categoria=? WHERE id_categorizar_grupo=?";
		try {
			con = Conexao.getConexao();	
			PreparedStatement modgru = con.prepareStatement(sql);
					
				modgru.setInt(1, idcat.getFkcapitulo());
				modgru.setInt(2, idcat.getFkgrupo());
				modgru.setString(3, idcat.getCategorizaGrupo());
				modgru.setString(4, idcat.getDescricaoCategoria());
				modgru.setInt(5, idcat.getIdCategorizarGrupo());
				modgru.execute();
				modgru.close();
					
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
	
	public void apagarCategoriaDGrupo(int cident)
	{
		String sql = "DELETE FROM tblcategoria WHERE id_categorizar_grupo=?";
		try {
			con = Conexao.getConexao();
			PreparedStatement ent = con.prepareStatement(sql);
			ent.setInt(1, cident);
			ent.execute();
			ent.close();
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
	
	//---------------------------------------------------------------------------------
	//Esta seccao esta reservada para o CID
	public void criarcid(Cid cid){
		String sql = "INSERT INTO tblcid (FK_capitulo,FK_grupo,FK_categoria,cid,descricao_cid, FK_classificacao,FK_restricao_sexo,FK_causa_obito,nota) VALUE (?,?,?,?,?,?,?,?,?)";
		try {
			con = Conexao.getConexao();	
			PreparedStatement inserircid = con.prepareStatement(sql);
				
				inserircid.setInt(1, cid.getFkcapitulo());
				inserircid.setInt(2, cid.getFkgrupodcapitulo());
				inserircid.setInt(3, cid.getFkcategoriadgrupo());
				inserircid.setString(4, cid.getCodigocid());
				inserircid.setString(5, cid.getDescricao());
				inserircid.setInt(6, cid.getFkclassificacao());
				inserircid.setInt(7, cid.getFkrestricaodsexo());
				inserircid.setInt(8, cid.getFkcausaobito());
				inserircid.setString(9, cid.getNota());
				
				inserircid.execute();
				inserircid.close();
				
				
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
	public List<Cid> listarCid()
	{
		List<Cid> listarCid = new ArrayList<Cid>();
		
		String sql = "SELECT * FROM vwlistarcid order by (codigo_capitulo) limit 30";
		
		try {
			con = Conexao.getConexao();
			PreparedStatement preparador = con.prepareStatement(sql);
			ResultSet rs = preparador.executeQuery();
		
			while (rs.next()){
        	
				Cid cidlist = new Cid();
				
				cidlist.setIdCid(rs.getInt("id_cid"));
				cidlist.setListarcapitulo(rs.getString("codigo_capitulo"));		
				cidlist.setListargrupo(rs.getString("codigo_grupo"));			 			
				cidlist.setListarcategoria(rs.getString("categoriza_grupo"));
				//modcid.setFkcategoriadgrupo(rs.getInt("FK_categoria"));				
				cidlist.setCodigocid(rs.getString("cid"));
				cidlist.setDescricao(rs.getString("descricao_cid"));
				cidlist.setListarclassificacao(rs.getString("classificacao"));
				cidlist.setListarrestricaodsexo(rs.getString("restricao_dsexo"));
				cidlist.setListarcausaobito(rs.getString("causa_obito"));
				cidlist.setNota(rs.getString("nota"));
				//--vindo de outra tabela
				cidlist.setFkclassificacao(rs.getInt("FK_classificacao"));
        	
				listarCid.add(cidlist);
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
		return listarCid;
	}
	
	public List<Cid> ListarCIDEmArvore()
	{
		List<Cid> listarCid = new ArrayList<Cid>();
		
		String sql = "SELECT * FROM vwlistarcid  group by codigo_capitulo";
		
		try {
			con = Conexao.getConexao();
			PreparedStatement preparador = con.prepareStatement(sql);
			ResultSet rs = preparador.executeQuery();
		
			while (rs.next()){
        	
				Cid cidlist = new Cid();
				
				cidlist.setIdCid(rs.getInt("id_cid"));
				cidlist.setListarcapitulo(rs.getString("codigo_capitulo"));		
				cidlist.setDescricao(rs.getString("descricao_capitulo"));
				cidlist.setListargrupo(rs.getString("codigo_grupo"));
				cidlist.setNota(rs.getString("descricao_grupo"));
				        	
				listarCid.add(cidlist);
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
		return listarCid;
	}
	
	public List<Cid> listarCidPorCapitulo(int cod)
	{
		List<Cid> listarCid = new ArrayList<Cid>();
		
		String sql = "SELECT * FROM vwlistarcid  where FK_capitulo = ?";
		
		try {
			con = Conexao.getConexao();
			PreparedStatement preparador = con.prepareStatement(sql);
			preparador.setInt(1, cod);
			ResultSet rs = preparador.executeQuery();
		
			while (rs.next()){
        	
				Cid cidlist = new Cid();
				
				cidlist.setIdCid(rs.getInt("id_cid"));
				cidlist.setListarcapitulo(rs.getString("codigo_capitulo"));		
				cidlist.setListargrupo(rs.getString("codigo_grupo"));			 			
				cidlist.setListarcategoria(rs.getString("categoriza_grupo"));				
				cidlist.setCodigocid(rs.getString("cid"));
				cidlist.setDescricao(rs.getString("descricao_cid"));
				cidlist.setListarclassificacao(rs.getString("classificacao"));
				cidlist.setListarrestricaodsexo(rs.getString("restricao_dsexo"));
				cidlist.setListarcausaobito(rs.getString("causa_obito"));
				cidlist.setNota(rs.getString("nota"));
				//--vindo de outra tabela
				cidlist.setFkclassificacao(rs.getInt("FK_classificacao"));
        	
				listarCid.add(cidlist);
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
		return listarCid;
	}
	
	public List<Cid> listarCidPorGrupo(int cod)
	{
		List<Cid> listarCid = new ArrayList<Cid>();
		
		String sql = "SELECT * FROM vwlistarcid  where FK_grupo = ?";
		
		try {
			con = Conexao.getConexao();
			PreparedStatement preparador = con.prepareStatement(sql);
			preparador.setInt(1, cod);
			ResultSet rs = preparador.executeQuery();
		
			while (rs.next()){
        	
				Cid cidlist = new Cid();
				
				cidlist.setIdCid(rs.getInt("id_cid"));
				cidlist.setListarcapitulo(rs.getString("codigo_capitulo"));		
				cidlist.setListargrupo(rs.getString("codigo_grupo"));			 			
				cidlist.setListarcategoria(rs.getString("categoriza_grupo"));				
				cidlist.setCodigocid(rs.getString("cid"));
				cidlist.setDescricao(rs.getString("descricao_cid"));
				cidlist.setListarclassificacao(rs.getString("classificacao"));
				cidlist.setListarrestricaodsexo(rs.getString("restricao_dsexo"));
				cidlist.setListarcausaobito(rs.getString("causa_obito"));
				cidlist.setNota(rs.getString("nota"));
				//--vindo de outra tabela
				cidlist.setFkclassificacao(rs.getInt("FK_classificacao"));
        	
				listarCid.add(cidlist);
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
		return listarCid;
	}
	public List<Cid> listarCidPorGrupo(int cod1,int cod2)
	{
		List<Cid> listarCid = new ArrayList<Cid>();
		
		String sql = "SELECT * FROM vwlistarcid  where FK_capitulo = ? and FK_grupo = ?";
		
		try {
			con = Conexao.getConexao();
			PreparedStatement preparador = con.prepareStatement(sql);
			preparador.setInt(1, cod1);
			preparador.setInt(2, cod2);
			ResultSet rs = preparador.executeQuery();
		
			while (rs.next()){
        	
				Cid cidlist = new Cid();
				
				cidlist.setIdCid(rs.getInt("id_cid"));
				cidlist.setListarcapitulo(rs.getString("codigo_capitulo"));		
				cidlist.setListargrupo(rs.getString("codigo_grupo"));			 			
				cidlist.setListarcategoria(rs.getString("categoriza_grupo"));				
				cidlist.setCodigocid(rs.getString("cid"));
				cidlist.setDescricao(rs.getString("descricao_cid"));
				cidlist.setListarclassificacao(rs.getString("classificacao"));
				cidlist.setListarrestricaodsexo(rs.getString("restricao_dsexo"));
				cidlist.setListarcausaobito(rs.getString("causa_obito"));
				cidlist.setNota(rs.getString("nota"));
				//--vindo de outra tabela
				cidlist.setFkclassificacao(rs.getInt("FK_classificacao"));
        	
				listarCid.add(cidlist);
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
		return listarCid;
	}
	public List<Cid> listarCidPorCategoria(int cod1)
	{
		List<Cid> listarCid = new ArrayList<Cid>();
		
		String sql = "SELECT * FROM vwlistarcid  where FK_categoria = ?";
		
		try {
			con = Conexao.getConexao();
			PreparedStatement preparador = con.prepareStatement(sql);
			preparador.setInt(1, cod1);
			ResultSet rs = preparador.executeQuery();
		
			while (rs.next()){
        	
				Cid cidlist = new Cid();
				
				cidlist.setIdCid(rs.getInt("id_cid"));
				cidlist.setListarcapitulo(rs.getString("codigo_capitulo"));		
				cidlist.setListargrupo(rs.getString("codigo_grupo"));			 			
				cidlist.setListarcategoria(rs.getString("categoriza_grupo"));				
				cidlist.setCodigocid(rs.getString("cid"));
				cidlist.setDescricao(rs.getString("descricao_cid"));
				cidlist.setListarclassificacao(rs.getString("classificacao"));
				cidlist.setListarrestricaodsexo(rs.getString("restricao_dsexo"));
				cidlist.setListarcausaobito(rs.getString("causa_obito"));
				cidlist.setNota(rs.getString("nota"));
				//--vindo de outra tabela
				cidlist.setFkclassificacao(rs.getInt("FK_classificacao"));
        	
				listarCid.add(cidlist);
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
		return listarCid;
	}
	public List<Cid> listarCidPorCategoria(int cod1,int cod2)
	{
		List<Cid> listarCid = new ArrayList<Cid>();
		
		String sql = "SELECT * FROM vwlistarcid  where FK_categoria = ? and FK_grupo = ?";
		
		try {
			con = Conexao.getConexao();
			PreparedStatement preparador = con.prepareStatement(sql);
			preparador.setInt(1, cod1);
			preparador.setInt(2, cod2);
			ResultSet rs = preparador.executeQuery();
		
			while (rs.next()){
        	
				Cid cidlist = new Cid();
				
				cidlist.setIdCid(rs.getInt("id_cid"));
				cidlist.setListarcapitulo(rs.getString("codigo_capitulo"));		
				cidlist.setListargrupo(rs.getString("codigo_grupo"));			 			
				cidlist.setListarcategoria(rs.getString("categoriza_grupo"));				
				cidlist.setCodigocid(rs.getString("cid"));
				cidlist.setDescricao(rs.getString("descricao_cid"));
				cidlist.setListarclassificacao(rs.getString("classificacao"));
				cidlist.setListarrestricaodsexo(rs.getString("restricao_dsexo"));
				cidlist.setListarcausaobito(rs.getString("causa_obito"));
				cidlist.setNota(rs.getString("nota"));
				//--vindo de outra tabela
				cidlist.setFkclassificacao(rs.getInt("FK_classificacao"));
        	
				listarCid.add(cidlist);
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
		return listarCid;
	}
	public List<Cid> listarCidPorCategoria(int cod1,int cod2,int cod3)
	{
		List<Cid> listarCid = new ArrayList<Cid>();
		
		String sql = "SELECT * FROM vwlistarcid  where FK_capitulo = ? and FK_grupo = ? and FK_categoria = ?";
		
		try {
			con = Conexao.getConexao();
			PreparedStatement preparador = con.prepareStatement(sql);
			preparador.setInt(1, cod1);
			preparador.setInt(2, cod2);
			preparador.setInt(3, cod3);
			ResultSet rs = preparador.executeQuery();
		
			while (rs.next()){
        	
				Cid cidlist = new Cid();
				
				cidlist.setIdCid(rs.getInt("id_cid"));
				cidlist.setListarcapitulo(rs.getString("codigo_capitulo"));		
				cidlist.setListargrupo(rs.getString("codigo_grupo"));			 			
				cidlist.setListarcategoria(rs.getString("categoriza_grupo"));				
				cidlist.setCodigocid(rs.getString("cid"));
				cidlist.setDescricao(rs.getString("descricao_cid"));
				cidlist.setListarclassificacao(rs.getString("classificacao"));
				cidlist.setListarrestricaodsexo(rs.getString("restricao_dsexo"));
				cidlist.setListarcausaobito(rs.getString("causa_obito"));
				cidlist.setNota(rs.getString("nota"));
				//--vindo de outra tabela
				cidlist.setFkclassificacao(rs.getInt("FK_classificacao"));
        	
				listarCid.add(cidlist);
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
		return listarCid;
	}
	public Cid getCid(String cod4)
	{
		Cid cidlist = new Cid();
		
		String sql = "SELECT * FROM vwlistarcid  where  cid Like ?";
		
		try {
			con = Conexao.getConexao();
			PreparedStatement preparador = con.prepareStatement(sql);
			preparador.setString(1, cod4);
			ResultSet rs = preparador.executeQuery();
			if (rs.next()){
				cidlist.setIdCid(rs.getInt("id_cid"));
				cidlist.setListarcapitulo(rs.getString("codigo_capitulo"));	
				cidlist.setListarDescricaoCapitulo(rs.getString("descricao_capitulo"));
				cidlist.setListargrupo(rs.getString("codigo_grupo"));
				cidlist.setListarDescricaoGrupo(rs.getString("descricao_grupo"));
				cidlist.setListarcategoria(rs.getString("categoriza_grupo"));	
				cidlist.setListarDescricaoCategoria(rs.getString("descricao_categoria"));
				cidlist.setCodigocid(rs.getString("cid"));
				cidlist.setDescricao(rs.getString("descricao_cid"));
				cidlist.setListarclassificacao(rs.getString("classificacao"));
				cidlist.setListarrestricaodsexo(rs.getString("restricao_dsexo"));
				cidlist.setListarcausaobito(rs.getString("causa_obito"));
				cidlist.setNota(rs.getString("nota"));
				//--vindo de outra tabela
				cidlist.setFkclassificacao(rs.getInt("FK_classificacao"));
        	 
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
		return cidlist;
	}
	public List<Cid> getlistaCid(String cod4)
	{
		List<Cid> listarCid = new ArrayList<Cid>();
		
		String sql = "SELECT * FROM vwlistarcid  where  cid Like ?";
		
		try {
			con = Conexao.getConexao();
			PreparedStatement preparador = con.prepareStatement(sql);
			preparador.setString(1, cod4);
			ResultSet rs = preparador.executeQuery();
		
			while (rs.next()){
        	
				Cid cidlist = new Cid();
				
				cidlist.setIdCid(rs.getInt("id_cid"));
				cidlist.setListarcapitulo(rs.getString("codigo_capitulo"));		
				cidlist.setListargrupo(rs.getString("codigo_grupo"));			 			
				cidlist.setListarcategoria(rs.getString("categoriza_grupo"));				
				cidlist.setCodigocid(rs.getString("cid"));
				cidlist.setDescricao(rs.getString("descricao_cid"));
				cidlist.setListarclassificacao(rs.getString("classificacao"));
				cidlist.setListarrestricaodsexo(rs.getString("restricao_dsexo"));
				cidlist.setListarcausaobito(rs.getString("causa_obito"));
				cidlist.setNota(rs.getString("nota"));
				//--vindo de outra tabela
				cidlist.setFkclassificacao(rs.getInt("FK_classificacao"));
        	
				listarCid.add(cidlist);
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
		return listarCid;
	}
	
	public List<Cid> getlistaCid(int cod1,String cod4)
	{
		List<Cid> listarCid = new ArrayList<Cid>();
		
		String sql = "SELECT * FROM vwlistarcid  where FK_capitulo = ? and cid Like ?";
		
		try {
			con = Conexao.getConexao();
			PreparedStatement preparador = con.prepareStatement(sql);
			preparador.setInt(1, cod1);
			preparador.setString(2, cod4);
			ResultSet rs = preparador.executeQuery();
		
			while (rs.next()){
        	
				Cid cidlist = new Cid();
				
				cidlist.setIdCid(rs.getInt("id_cid"));
				cidlist.setListarcapitulo(rs.getString("codigo_capitulo"));		
				cidlist.setListargrupo(rs.getString("codigo_grupo"));			 			
				cidlist.setListarcategoria(rs.getString("categoriza_grupo"));				
				cidlist.setCodigocid(rs.getString("cid"));
				cidlist.setDescricao(rs.getString("descricao_cid"));
				cidlist.setListarclassificacao(rs.getString("classificacao"));
				cidlist.setListarrestricaodsexo(rs.getString("restricao_dsexo"));
				cidlist.setListarcausaobito(rs.getString("causa_obito"));
				cidlist.setNota(rs.getString("nota"));
				//--vindo de outra tabela
				cidlist.setFkclassificacao(rs.getInt("FK_classificacao"));
        	
				listarCid.add(cidlist);
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
		return listarCid;
	}
	
	public List<Cid> getlistaCid(int cod1,int cod2,String cod4)
	{
		List<Cid> listarCid = new ArrayList<Cid>();
		
		String sql = "SELECT * FROM vwlistarcid  where FK_capitulo = ? and FK_grupo = ? and cid Like ?";
		
		try {
			con = Conexao.getConexao();
			PreparedStatement preparador = con.prepareStatement(sql);
			preparador.setInt(1, cod1);
			preparador.setInt(2, cod2);
			preparador.setString(3, cod4);
			ResultSet rs = preparador.executeQuery();
		
			while (rs.next()){
        	
				Cid cidlist = new Cid();
				
				cidlist.setIdCid(rs.getInt("id_cid"));
				cidlist.setListarcapitulo(rs.getString("codigo_capitulo"));		
				cidlist.setListargrupo(rs.getString("codigo_grupo"));			 			
				cidlist.setListarcategoria(rs.getString("categoriza_grupo"));				
				cidlist.setCodigocid(rs.getString("cid"));
				cidlist.setDescricao(rs.getString("descricao_cid"));
				cidlist.setListarclassificacao(rs.getString("classificacao"));
				cidlist.setListarrestricaodsexo(rs.getString("restricao_dsexo"));
				cidlist.setListarcausaobito(rs.getString("causa_obito"));
				cidlist.setNota(rs.getString("nota"));
				//--vindo de outra tabela
				cidlist.setFkclassificacao(rs.getInt("FK_classificacao"));
        	
				listarCid.add(cidlist);
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
		return listarCid;
	}
	
	public List<Cid> getlistaCid(int cod1,int cod2,int cod3,String cod4)
	{
		List<Cid> listarCid = new ArrayList<Cid>();
		
		String sql = "SELECT * FROM vwlistarcid  where FK_capitulo = ? and FK_grupo = ? and FK_categoria = ? and cid Like ?";
		
		try {
			con = Conexao.getConexao();
			PreparedStatement preparador = con.prepareStatement(sql);
			preparador.setInt(1, cod1);
			preparador.setInt(2, cod2);
			preparador.setInt(3, cod3);
			preparador.setString(4, cod4);
			ResultSet rs = preparador.executeQuery();
		
			while (rs.next()){
        	
				Cid cidlist = new Cid();
				
				cidlist.setIdCid(rs.getInt("id_cid"));
				cidlist.setListarcapitulo(rs.getString("codigo_capitulo"));		
				cidlist.setListargrupo(rs.getString("codigo_grupo"));			 			
				cidlist.setListarcategoria(rs.getString("categoriza_grupo"));				
				cidlist.setCodigocid(rs.getString("cid"));
				cidlist.setDescricao(rs.getString("descricao_cid"));
				cidlist.setListarclassificacao(rs.getString("classificacao"));
				cidlist.setListarrestricaodsexo(rs.getString("restricao_dsexo"));
				cidlist.setListarcausaobito(rs.getString("causa_obito"));
				cidlist.setNota(rs.getString("nota"));
				//--vindo de outra tabela
				cidlist.setFkclassificacao(rs.getInt("FK_classificacao"));
        	
				listarCid.add(cidlist);
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
		return listarCid;
	}
	public List<Cid> buscarCidPCode()
	{
		List<Cid> listarCid = new ArrayList<Cid>();
		
		String sql = "SELECT * FROM TBLCID ORDER BY CID";
		
		try {
			con = Conexao.getConexao();
			PreparedStatement preparador = con.prepareStatement(sql);
			ResultSet rs = preparador.executeQuery();
		
			while (rs.next()){
				Cid cidlist = new Cid();			
				cidlist.setCodigocid(rs.getString("cid"));
				cidlist.setDescricao(rs.getString("descricao_cid"));        	
				listarCid.add(cidlist);
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
		return listarCid;
	}
	public List<Cid> buscarCidPorDesc()
	{
		List<Cid> listarCid = new ArrayList<Cid>();
		
		String sql = "SELECT * FROM TBLCID ORDER BY DESCRICAO_CID desc";
		
		try {
			con = Conexao.getConexao();
			PreparedStatement preparador = con.prepareStatement(sql);
			ResultSet rs = preparador.executeQuery();
		
			while (rs.next()){
				Cid cidlist = new Cid();			
				cidlist.setCodigocid(rs.getString("cid"));
				cidlist.setDescricao(rs.getString("descricao_cid"));        	
				listarCid.add(cidlist);
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
		return listarCid;
	}
	public List<Cid> buscarCid()
	{
		List<Cid> listarCid = new ArrayList<Cid>();
		
		String sql = "SELECT * FROM TBLCID   order by cid limit 1000";
		
		try {
			con = Conexao.getConexao();
			PreparedStatement preparador = con.prepareStatement(sql);
			ResultSet rs = preparador.executeQuery();
		
			while (rs.next()){
				Cid cidlist = new Cid();			
				cidlist.setCodigocid(rs.getString("cid"));
				cidlist.setDescricao(rs.getString("descricao_cid"));        	
				listarCid.add(cidlist);
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
		return listarCid;
	}
	
	public List<Cid> buscarCid(String valor)
	{
		List<Cid> listarCid = new ArrayList<Cid>();
		
		String sql = "SELECT * FROM TBLCID  WHERE cid Like ? or descricao_cid Like ? order by cid";
		
		try {
			con = Conexao.getConexao();
			PreparedStatement preparador = con.prepareStatement(sql);
			preparador.setString(1,valor+"%");
			preparador.setString(2,valor+"%");
			ResultSet rs = preparador.executeQuery();
		
			while (rs.next()){
				Cid cidlist = new Cid();			
				cidlist.setCodigocid(rs.getString("cid"));
				cidlist.setDescricao(rs.getString("descricao_cid"));        	
				listarCid.add(cidlist);
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
		return listarCid;
	}
	
	public List<Cid> buscarCidPNome(String aux)
	{
		List<Cid> listarCid = new ArrayList<Cid>();
		
		String sql = "SELECT * FROM TBLCID WHERE DESCRICAO_CID LIKE '"+aux+"%'";
		
		try {
			con = Conexao.getConexao();
			PreparedStatement preparador = con.prepareStatement(sql);
			ResultSet rs = preparador.executeQuery();
		
			while (rs.next()){
				Cid cidlist = new Cid();			
				cidlist.setCodigocid(rs.getString("cid"));
				cidlist.setDescricao(rs.getString("descricao_cid"));        	
				listarCid.add(cidlist);
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
		return listarCid;
	}
	
	public Cid umCid(int cidid) 
	{
		Cid modcid = new Cid();

		String sql = "SELECT * FROM vwlistarcid WHERE id_cid = ?";

		try {
			con = Conexao.getConexao();
			PreparedStatement preparador = con.prepareStatement(sql);
			preparador.setInt(1, cidid);
			ResultSet rs = preparador.executeQuery();

			if (rs.next()) {
				modcid.setIdCid(rs.getInt("id_cid"));
				modcid.setListarcapitulo(rs.getString("codigo_capitulo"));

				modcid.setListargrupo(rs.getString("codigo_grupo"));

				modcid.setListarcategoria(rs.getString("categoriza_grupo"));

				modcid.setCodigocid(rs.getString("cid"));
				modcid.setDescricao(rs.getString("descricao_cid"));
				modcid.setListarclassificacao(rs.getString("classificacao"));
				modcid.setListarrestricaodsexo(rs.getString("restricao_dsexo"));
				modcid.setListarcausaobito(rs.getString("causa_obito"));
				// --------------------------------------------------------
				modcid.setFkclassificacao(rs.getInt("FK_classificacao"));//
				modcid.setFkrestricaodsexo(rs.getInt("FK_restricao_sexo"));
				modcid.setFkcapitulo(rs.getInt("FK_capitulo"));
				modcid.setFkgrupodcapitulo(rs.getInt("FK_grupo"));
				modcid.setFkcategoriadgrupo(rs.getInt("FK_categoria"));
				modcid.setFkcausaobito(rs.getInt("FK_causa_obito"));
				modcid.setNota(rs.getString("nota"));
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
	    return modcid;
	}
	
	public void modificarCid(Cid idcid)
	{			
		String sql = "UPDATE tblcid SET FK_capitulo=?, FK_grupo=?, FK_categoria=?, cid=?, descricao_cid=?,FK_classificacao=?,FK_restricao_sexo=?,FK_causa_obito=?,nota=? WHERE id_cid=?";
		try {
			con = Conexao.getConexao();	
			PreparedStatement modcid = con.prepareStatement(sql);
				
				modcid.setInt(1, idcid.getFkcapitulo());
				modcid.setInt(2, idcid.getFkgrupodcapitulo());
				modcid.setInt(3, idcid.getFkcategoriadgrupo());
				modcid.setString(4, idcid.getCodigocid());
				modcid.setString(5, idcid.getDescricao());
				modcid.setInt(6, idcid.getFkclassificacao());
				modcid.setInt(7, idcid.getFkrestricaodsexo());
				modcid.setInt(8, idcid.getFkcausaobito());
				modcid.setString(9, idcid.getNota());
				modcid.setInt(10, idcid.getIdCid());
				
				modcid.execute();
				modcid.close();
					
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
	
	public void apagarcid(int idcid)
	{
		String sql = "DELETE FROM tblcid WHERE id_cid=?";
		try {
			con = Conexao.getConexao();
			PreparedStatement ent = con.prepareStatement(sql);
			ent.setInt(1, idcid);
			ent.execute();
			ent.close();
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
	
	//--
	//trabalhando com os botoes radios
	public String checkedclaum(int clum){
	  String check=null;
	  if(clum == 1){
	   check="checked = 'checked'";
	  }
	  return check;
	}
	
	public String checkedcladois(int cldois){
	  String check=null;
	  if(cldois == 2){
	   check="checked = 'checked'";
	  }
	  return check;
	 }
	
	public String checkedclatres(int cltres){
	  String check=null;
	  if(cltres == 3){
	   check = "checked = 'checked'";
	  }
	  return check;
	}
	
	//--------------------------------------------------------------------
	//esta parte esta reservada para o check dos restricoes de sexo
	public String checkedcrsum(int rs){
		String check=null;
		if(rs == 1){
		   check = "checked = 'checked'";
		}
		return check;
	}
	
	public String checkedcrdois(int rs){
		String check=null;
		if(rs == 2){
		   check = "checked = 'checked'";
		}
		return check;
	}
	
	public String checkedcrtres(int rs){
		String check=null;
		if(rs == 3){
		   check = "checked = 'checked'";
		}
		return check;
	}
	
	public String checkedcrquatro(int rs){
		String check=null;
		if(rs == 4){
		   check = "checked = 'checked'";
		}
		return check;
	}
	
	//--------------------------------------------------------
	//------------Esta parte esta reservada para o causa morte
	public String checkedcmum(int cm){
		String check=null;
		if(cm == 1){
		   check = "checked = 'checked'";
		}
		return check;
	}
	
	public String checkedcmdois(int cm){
		String check=null;
		if(cm == 2){
		   check = "checked = 'checked'";
		}
		return check;
	}
	
	//##################################################################################################
	//#################################SELECIONAR GRUPO DE UM CAPITULO ASINCRONICAMENTE
	public List<GrupoDeCapitulo> pesquisarCategoriaPorCapitulo(int codigo)
	{
		List<GrupoDeCapitulo> lg = new ArrayList<GrupoDeCapitulo>();
		String sql = "select id_grupo_capitulo, fk_capitulo, codigo_grupo from tblgrupodcapitulo where FK_capitulo =?";
		try {
			con = Conexao.getConexao();
			ps = con.prepareStatement(sql);
			ps.setInt(1, codigo);
			rs = ps.executeQuery();
			if(!rs.next())
				System.out.println("Nao existem grupos cadastrados com este capitulo.");
			else{
				rs.beforeFirst();
				while (rs.next()) {
					GrupoDeCapitulo gc = new GrupoDeCapitulo();
					gc.setIdGrupoDCapitulo(rs.getInt("id_grupo_capitulo"));
					gc.setCodigoGrupo(rs.getString("codigo_grupo"));
					lg.add(gc);
				}
			}
		} catch (Exception e) {
			System.out.println("Impossivel visualizar grupo cadastrado com este capitulo" + e);
		}
		return lg;
    }
	
	public List<CategoriaDGrupo> pesquisarCategoriaPorGrupo(int codigo)
	{
		List<CategoriaDGrupo> lg = new ArrayList<CategoriaDGrupo>();
		String sql = "select * from tblcategoria where FK_grupo =?";
		try {
			con = Conexao.getConexao();
			ps = con.prepareStatement(sql);
			ps.setInt(1, codigo);
			rs = ps.executeQuery();
			if(!rs.next())
				System.out.println("Nao existem grupos cadastrados com este capitulo.");
			else{
				rs.beforeFirst();
				while (rs.next()) {
					CategoriaDGrupo gc = new CategoriaDGrupo();
					gc.setIdCategorizarGrupo(rs.getInt("id_categorizar_grupo"));
					gc.setCategorizaGrupo(rs.getString("categoriza_grupo"));
					lg.add(gc);
				}
			}
		} catch (Exception e) {
			System.out.println("Impossivel visualizar grupo cadastrado com este capitulo" + e);
		}
		return lg;
    }
	
	public List<Cid> getlistarCid(String cod4)
	{
		List<Cid> listarCid = new ArrayList<Cid>();
		
		String sql = "SELECT * FROM vwlistarcid  where  descricao_cid LIKE ?";
		
		try {
			con = Conexao.getConexao();
			PreparedStatement preparador = con.prepareStatement(sql);
			preparador.setString(1, cod4+"%");
			ResultSet rs = preparador.executeQuery();
		
			while (rs.next()){
        	
				Cid cidlist = new Cid();
				
				cidlist.setIdCid(rs.getInt("id_cid"));
				cidlist.setListarcapitulo(rs.getString("codigo_capitulo"));		
				cidlist.setListargrupo(rs.getString("codigo_grupo"));			 			
				cidlist.setListarcategoria(rs.getString("categoriza_grupo"));				
				cidlist.setCodigocid(rs.getString("cid"));
				cidlist.setDescricao(rs.getString("descricao_cid"));
				cidlist.setListarclassificacao(rs.getString("classificacao"));
				cidlist.setListarrestricaodsexo(rs.getString("restricao_dsexo"));
				cidlist.setListarcausaobito(rs.getString("causa_obito"));
				cidlist.setNota(rs.getString("nota"));
				//--vindo de outra tabela
				cidlist.setFkclassificacao(rs.getInt("FK_classificacao"));
        	
				listarCid.add(cidlist);
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
		return listarCid;
	}
}
