package ao.co.cligest.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.sql.Date;
import java.util.List;

import ao.co.cligest.cripto.EncriptaDecriptaRSA;
import ao.co.cligest.cripto.Seguranca;
import ao.co.cligest.entidades.Funcionario;
import ao.co.cligest.entidades.Usuario;
public class FuncionarioDAO 
{
    private Connection con ;
    private static final String key = "140b41b22a29beb4061bda66b6747e14";
	
	// 1 tabela - entidade 
	
	public int getIdEntidade(Funcionario fun)
	{
		int ultimoId = -1;
		String sql = "INSERT INTO TBLENTIDADE (EFECTIVIDADE) VALUE (1)";
		try {
			con = Conexao.getConexao();
			PreparedStatement ent = con.prepareStatement(sql);
		    ent.execute();
		    ResultSet rs = ent.executeQuery("SELECT LAST_INSERT_ID()");
		    if(rs.next())
		      ultimoId = rs.getInt(1);
		    ent.close();
		} catch (SQLException e) {
			e.printStackTrace();
		} catch (Exception e) {			
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
		return ultimoId;
	}
	
	public int nova_entidade()
	{
		String sql = "Select count(id_entidade) total from tblentidade";
		int cod=0;
		try {
			con = Conexao.getConexao();
			PreparedStatement ent = con.prepareStatement(sql);
			ResultSet rs = ent.executeQuery();
			if(rs.next())
			  cod = rs.getInt("total");	
			  cod++;
			ent.close();
			return cod;			
		} catch (SQLException e) {
			e.printStackTrace();
		} catch (Exception e) {
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
		return 0;
	}
	
	public void Delete_Arq(Funcionario fun)
	{
		String sql = "DELETE FROM TBLARQUIVODOFUNCIONARIO WHERE fk_entidade=? AND fk_arquivo=?";
		try {
			con = Conexao.getConexao();
			PreparedStatement ent = con.prepareStatement(sql);
			ent.setInt(1, fun.getId());
			ent.setInt(2, fun.getArquivo());
			ent.execute();
			ent.close();
		} catch (SQLException e) {
			e.printStackTrace();
		} catch (Exception e) {
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
	public String Find_Arq(Funcionario fun)
	{
		String sql = "SELECT CAMINHO,NOME_FICHEIRO FROM TBLARQUIVODOFUNCIONARIO WHERE fk_entidade=? AND fk_arquivo=?";
		String ficheiro="";
		try {
			con = Conexao.getConexao();
			PreparedStatement ent = con.prepareStatement(sql);
			ent.setInt(1, fun.getId());
			ent.setInt(2, fun.getArquivo());
			ResultSet rs = ent.executeQuery();
			if(rs.next())
				ficheiro = rs.getString("caminho")+rs.getString("nome_ficheiro");
			ent.close();
		} catch (SQLException e) {
			e.printStackTrace();
		} catch (Exception e) {
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
		return ficheiro;
	}
	public String foto(int id)
	{
		String sql = "SELECT NOME_FICHEIRO FROM TBLARQUIVODOFUNCIONARIO WHERE fk_entidade=? AND fk_arquivo=?";
		String ficheiro="usuarios.jpg";
		try {
			con = Conexao.getConexao();
			PreparedStatement ent = con.prepareStatement(sql);
			ent.setInt(1,id);
			ent.setInt(2, 1);
			ResultSet rs = ent.executeQuery();
			if(rs.next())
				ficheiro = rs.getString("nome_ficheiro");
			ent.close();
		} catch (SQLException e) {
			e.printStackTrace();
		} catch (Exception e) {
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
		return ficheiro;
	}
	
	public int fk_entidade()
	{
		int fun = 0;
		String sql = "SELECT * FROM TBLENTIDADE ORDER BY ID_ENTIDADE";
		try {
			con = Conexao.getConexao();
			PreparedStatement preparador = con.prepareStatement(sql);
			ResultSet rs = preparador.executeQuery();
			if(rs.last())
				fun = rs.getInt("ID_ENTIDADE");
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
			return fun;
	}
	
	

	public String acao_nova(int num)
	{
		String nome = "";
		String sql = "SELECT * FROM VWPESQUISAR WHERE IDFUN = ?";
		try {
			con = Conexao.getConexao();
			PreparedStatement pr = con.prepareStatement(sql);
			pr.setInt(1, num);
			ResultSet rs = pr.executeQuery();
			if(rs.next())
				nome = rs.getString("nome")+" "+rs.getString("ultimo_nome");			
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
			return nome;
	}
	public String novo()
	{
		 
		int fun = 0;
		String sql = "SELECT max(id_entidade) num FROM tblentidade";
		try {
			con = Conexao.getConexao();
			PreparedStatement preparador = con.prepareStatement(sql);
			ResultSet rs = preparador.executeQuery();
			if(rs.last())
				fun = rs.getInt("num");
			
			
			 
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
			return "ATG0"+(fun+1);
	}
	public java.sql.Date data_registo()
	{
		java.sql.Date dt_registo = null;
		Calendar c = Calendar.getInstance(); // Cria um objeto para armazenar a data atual
        //c.setTime(new Date()); // pega a data atual do sistema
        int diaAtual = c.get(Calendar.DAY_OF_MONTH); // DIA
        int mesAtual = c.get(Calendar.MONTH); // MES
        int anoAtual = c.get(Calendar.YEAR);  // ANO
        String hoje = anoAtual+"/"+mesAtual+"/"+diaAtual;
        DateFormat formate = new SimpleDateFormat("yyyy/MM/dd");
	    java.util.Date a; 
		try {
			a = (java.util.Date) formate.parse(hoje);
			dt_registo = new java.sql.Date(a.getTime());
			} catch (ParseException e) { }
		
		return dt_registo;
	}
	// 2 tabela - cidadao
	
	// 7 tabela - endereco
	
	
	public List<Funcionario> buscaEspecialidade ()
	{
		List <Funcionario> lista = new ArrayList<Funcionario>();
		String sql = "SELECT * FROM TBLESPECIALIDADE ORDER BY ID_ESPECIALIDADE = 17 DESC, ESPECIALIDADE ASC";
		try {
			con = Conexao.getConexao(); 
			PreparedStatement preparador = con.prepareStatement(sql);
			 ResultSet rs = preparador.executeQuery();
			 while(rs.next())
			 {
				 Funcionario fun = new Funcionario();
				 fun.setEspecialidade(rs.getInt("id_especialidade"));
				 fun.setNomeEsp(rs.getString("especialidade"));
				 lista.add(fun);
			 }
			 preparador.close();
		} catch (SQLException e) {

			e.printStackTrace();
		} catch (Exception e) {
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
	// Metodos de Pesquisas (Consultas no BD)
	public List<Funcionario> buscaProfissao ()
	{
		List <Funcionario> lista = new ArrayList<Funcionario>();
		String sql = "SELECT * FROM TBLPROFISSAO ORDER BY ID_PROFISSAO = 0 DESC, PROFISSAO ASC";
		try {
			con = Conexao.getConexao(); 
			PreparedStatement preparador = con.prepareStatement(sql);
			 ResultSet rs = preparador.executeQuery();
			 while(rs.next())
			 {
				 Funcionario fun = new Funcionario();
				 fun.setProfissao(rs.getInt("id_profissao"));
				 fun.setNomeProf(rs.getString("profissao"));
				 lista.add(fun);
			 }
			 preparador.close();
		} catch (SQLException e) {
			
			e.printStackTrace();
		} catch (Exception e) {
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
	public List<Funcionario> buscaEstadoCivil ()
	{
		List <Funcionario> lista = new ArrayList<Funcionario>();
		String sql = "SELECT * FROM TBLESTADOCIVIL ORDER BY estado_civil ASC";
		try {
			con = Conexao.getConexao(); 
			PreparedStatement preparador = con.prepareStatement(sql);
			 ResultSet rs = preparador.executeQuery();
			 while(rs.next())
			 {
				 Funcionario fun = new Funcionario();
				 fun.setEst_civil(rs.getInt("id_estadocivil"));
				 fun.setNomeEC(rs.getString("estado_civil"));
				 lista.add(fun);
			 }
			 preparador.close();
		} catch (SQLException e) {
			
			e.printStackTrace();
		} catch (Exception e) {
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
	public List<Funcionario> buscaTipoDocumento ()
	{
		List <Funcionario> lista = new ArrayList<Funcionario>();
		String sql = "SELECT * FROM TBLTIPODOCUMENTO ORDER BY ID_TIPO_DOCUMENTO = 0 DESC, TIPO_DOCUMENTO ASC";
		try {
			con = Conexao.getConexao(); 
			PreparedStatement preparador = con.prepareStatement(sql);
			 ResultSet rs = preparador.executeQuery();
			 while(rs.next())
			 {
				 Funcionario fun = new Funcionario();
				 fun.setTipo_doc(rs.getInt("id_tipo_documento"));
				 fun.setNomeDoc(rs.getString("tipo_documento"));
				 lista.add(fun);
			 }
			 preparador.close();
		} catch (SQLException e) {
			
			e.printStackTrace();
		} catch (Exception e) {
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
	public List<Funcionario> buscaAfilicao ()
	{
		List <Funcionario> lista = new ArrayList<Funcionario>();
		String sql = "SELECT * FROM TBLAFILIACAO ORDER BY ID_AFILIACAO = 0 DESC, AFILIACAO ASC";
		try {
			con = Conexao.getConexao(); 
			PreparedStatement preparador = con.prepareStatement(sql);
			 ResultSet rs = preparador.executeQuery();
			 while(rs.next())
			 {
				 Funcionario fun = new Funcionario();
				 fun.setAfiliacao(rs.getInt("id_afiliacao"));
				 fun.setNomeAfl(rs.getString("afiliacao"));
				 lista.add(fun);
			 }
			 preparador.close();
		} catch (SQLException e) {
			
			e.printStackTrace();
		} catch (Exception e) {
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
	public List<Funcionario> buscaPais ()
	{
		List <Funcionario> lista = new ArrayList<Funcionario>();
		String sql = "SELECT * FROM TBLPAIS ORDER BY ID_PAIS = 0 DESC, PAIS ASC";
		try {
			 con = Conexao.getConexao();
			 PreparedStatement preparador = con.prepareStatement(sql);
			 ResultSet rs = preparador.executeQuery();
			 while(rs.next())
			 {
				 Funcionario fun = new Funcionario();
				 fun.setPais(rs.getInt("id_pais"));
				 fun.setNomePais(rs.getString("pais"));
				 lista.add(fun);
			 }
			 preparador.close();
		} catch (SQLException e) {
			
			e.printStackTrace();
		} catch (Exception e) {
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
	
	public List <Funcionario> buscaPorNum (String num,int est)
	{
		List <Funcionario> lista = new ArrayList<Funcionario>();
		//Funcionario fun = null;
		String sql = "SELECT * FROM VWPESQUISAR WHERE NUMERO_FUNCIONARIO LIKE ? AND EFECTIVIDADE = ? ORDER BY (NOME)";
		try {
			con = Conexao.getConexao(); 
			PreparedStatement preparador = con.prepareStatement(sql);
			 preparador.setString(1, num);
			 preparador.setInt(2, est);
			 ResultSet rs = preparador.executeQuery();
			 while(rs.next())
			 {
				 Funcionario fun = new Funcionario();
				 fun.setId(rs.getInt("IDFUN"));
				 fun.setNome(rs.getString("NOME"));
				 fun.setNomem(rs.getString("NOME_MEIO"));
				 fun.setApelido(rs.getString("ULTIMO_NOME"));
				 Calendar data = Calendar.getInstance();
				 data.setTime(rs.getDate("DATA_NASCIMENTO"));
				 fun.setDataNasc(data);
				 fun.setEndereco(rs.getString("ENDERECO"));
				 fun.setNum_fun(rs.getString("NUMERO_FUNCIONARIO"));
				 fun.setEmail(rs.getString("EMAIL"));
				 fun.setEditfone(rs.getLong("TELEFONE"));
				 lista.add(fun);
			 }
			 preparador.close();
		} catch (SQLException e) {
		
			e.printStackTrace();
		} catch (Exception e) {
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
	
	public List <Funcionario> buscaPorNome (String nome,int est)
	{
		List <Funcionario> lista = new ArrayList<Funcionario>();
		String sql = "SELECT * FROM VWPESQUISAR WHERE NOME LIKE '"+nome+"%' OR ULTIMO_NOME LIKE '"+nome+"%' AND EFECTIVIDADE = ? ORDER BY (NOME)";
		try {
			con = Conexao.getConexao(); 
			PreparedStatement preparador = con.prepareStatement(sql);
			 preparador.setInt(1, est);
			 ResultSet rs = preparador.executeQuery();
			 while(rs.next())
			 {
				 Funcionario fun = new Funcionario();
				 fun.setId(rs.getInt("IDFUN"));
				 fun.setNome(rs.getString("NOME"));
				 fun.setNomem(rs.getString("NOME_MEIO"));
				 fun.setApelido(rs.getString("ULTIMO_NOME"));
				 Calendar data = Calendar.getInstance();
				 data.setTime(rs.getDate("DATA_NASCIMENTO"));
				 fun.setDataNasc(data);
				 fun.setEndereco(rs.getString("ENDERECO"));
				 fun.setNum_fun(rs.getString("NUMERO_FUNCIONARIO"));
				 fun.setEmail(rs.getString("EMAIL"));
				 fun.setEditfone(rs.getLong("TELEFONE"));
				 lista.add(fun);
			 }
			 preparador.close();
		} catch (SQLException e) {
			
			e.printStackTrace();
		} catch (Exception e) {
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
	public List <Funcionario> buscaFuncionario()
	{
		List <Funcionario> lista = new ArrayList<Funcionario>();
		String sql = "SELECT * FROM vwpesquisarprofissionais ORDER BY NOME";
		try {
			con = Conexao.getConexao(); 
			PreparedStatement preparador = con.prepareStatement(sql);
			 
			 ResultSet rs = preparador.executeQuery();
			 while(rs.next())
			 {
				 Funcionario fun = new Funcionario();
				 fun.setId(rs.getInt("ID_ENTIDADE"));
				 fun.setNome(rs.getString("NOME"));
				 fun.setNomem(rs.getString("NOME_MEIO"));
				 fun.setApelido(rs.getString("ULTIMO_NOME"));
				 fun.setNomeProf(rs.getString("PROFISSAO"));
				 lista.add(fun);
			 }
			 preparador.close();
		} catch (SQLException e) {
			
			e.printStackTrace();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return lista;
	}
	public List <Funcionario> buscaUsuario()
	{
		List <Funcionario> lista = new ArrayList<Funcionario>();
		String sql = "SELECT * FROM VWUSUARIOS WHERE FK_ENTIDADE <> 1 ORDER BY NOME";
		try {
			con = Conexao.getConexao(); 
			PreparedStatement preparador = con.prepareStatement(sql);
			 ResultSet rs = preparador.executeQuery();
			 while(rs.next())
			 {
				 Funcionario fun = new Funcionario();
				 fun.setId(rs.getInt("FK_ENTIDADE"));
				 fun.setNome(rs.getString("NOME"));
				 fun.setNomem(rs.getString("NOME_MEIO"));
				 fun.setApelido(rs.getString("ULTIMO_NOME"));
				 fun.setNome_us(rs.getString("NOME_USUARIO"));
				 fun.setNomgrupo(rs.getString("GRUPO"));
				 lista.add(fun);
			 }
			 preparador.close();
		} catch (SQLException e) {
			
			e.printStackTrace();
		} catch (Exception e) {
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
	public List <Funcionario> buscaUsuario(String nome)
	{
		List <Funcionario> lista = new ArrayList<Funcionario>();
		String sql = "SELECT * FROM VWUSUARIOS  WHERE NOME LIKE '"+nome+"%' ORDER BY (NOME)";
		try {
			con = Conexao.getConexao(); 
			PreparedStatement preparador = con.prepareStatement(sql);			 
			 ResultSet rs = preparador.executeQuery();
			 while(rs.next())
			 {
				 Funcionario fun = new Funcionario();
				 fun.setId(rs.getInt("FK_ENTIDADE"));
				 fun.setNome(rs.getString("NOME"));
				 fun.setNomem(rs.getString("NOME_MEIO"));
				 fun.setApelido(rs.getString("ULTIMO_NOME"));
				 fun.setNome_us(rs.getString("NOME_USUARIO"));
				 fun.setNomgrupo(rs.getString("GRUPO"));
				 lista.add(fun);
			 }
			 preparador.close();
		} catch (SQLException e) {
			
			e.printStackTrace();
		} catch (Exception e) {
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
	public List <Funcionario> buscaFuncionario(String nome) throws Exception
	{
		List <Funcionario> lista = new ArrayList<Funcionario>();
		String sql = "SELECT * FROM VWPESQUISARFUNESPECIALIDADES WHERE NOME LIKE '"+nome+"%' ORDER BY (NOME)";
		try {
			con = Conexao.getConexao(); 
			PreparedStatement preparador = con.prepareStatement(sql);
			 ResultSet rs = preparador.executeQuery();
			 while(rs.next())
			 {
				 Funcionario fun = new Funcionario();
				 fun.setId(rs.getInt("ID_ENTIDADE"));
				 fun.setNome(rs.getString("NOME"));
				 fun.setNomem(rs.getString("NOME_MEIO"));
				 fun.setApelido(rs.getString("ULTIMO_NOME"));
				 fun.setNomeEsp(rs.getString("ESPECIALIDADE"));
				 lista.add(fun);
			 }
			 preparador.close();
		} catch (SQLException e) {
			
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
	
	public List <Funcionario> getFuncionarioParaCaixa ()  {
		List <Funcionario> lista = new ArrayList<Funcionario>();
		String sql = "Select * from vwcidadofuncionariocaixa where fk_entidade <> 1 "
				+    "and fk_entidade not in (select FK_entidade_caixa from tblestadodocaixa where data_deatribuicao_troco = curdate() and estadodocaixa <> 0) order by nome asc";
		try {
			con = Conexao.getConexao(); 
			PreparedStatement preparador = con.prepareStatement(sql);
			 ResultSet rs = preparador.executeQuery();
			 while(rs.next())
			 {
				 Funcionario fun = new Funcionario();
				 fun.setFK_entidade(rs.getInt("fk_entidade"));
				 fun.setNome(rs.getString("NOME"));
				 fun.setNomem(rs.getString("NOME_MEIO"));
				 fun.setApelido(rs.getString("ULTIMO_NOME"));
				 fun.setNomeFun(rs.getString("FUNCAO"));
				 fun.setEditfone(rs.getLong("telefone"));
				 lista.add(fun);
			 }
			 preparador.close();
		} catch (SQLException e) {
			
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
	
	public Funcionario getFuncionarioParaCaixa (int cod)  {
		
		Funcionario fun = new Funcionario();
		String sql = "Select * from vwcidadofuncionariocaixa where fk_entidade <> 1 and fk_entidade = ?";
		try {
			con = Conexao.getConexao(); 
			PreparedStatement preparador = con.prepareStatement(sql);
			preparador.setInt(1, cod);
			 ResultSet rs = preparador.executeQuery();
			 if(rs.next())
			 {
				 
				 fun.setFK_entidade(rs.getInt("fk_entidade"));
				 fun.setNome(rs.getString("NOME"));
				 fun.setNomem(rs.getString("NOME_MEIO"));
				 fun.setApelido(rs.getString("ULTIMO_NOME"));
				 fun.setNomeFun(rs.getString("FUNCAO"));				  
			 }
			 preparador.close();
		} catch (SQLException e) {
			
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
		 return fun;
	}
	
	public Funcionario getFunUsuario(int num)
	{
		Funcionario fun = null;
		String sql = "SELECT * FROM vwpesquisarprofissionais WHERE ID_ENTIDADE LIKE ? ";
		try {
			con = Conexao.getConexao(); 
			PreparedStatement preparador = con.prepareStatement(sql);
			 preparador.setInt(1,num);
			 ResultSet rs = preparador.executeQuery();
			 if(rs.next())
			 {
				 fun = new Funcionario();
				 fun.setId(rs.getInt("ID_ENTIDADE"));
				 fun.setNome(rs.getString("NOME"));
				 fun.setNomem(rs.getString("NOME_MEIO"));
				 fun.setApelido(rs.getString("ULTIMO_NOME"));
				 			 
			 }
			 preparador.close();
		} catch (SQLException e) {
			
			e.printStackTrace();
		} catch (Exception e) {
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
		return fun;
	}
	
	public boolean checkNomeUsuario(String aux){
		String sql = " Select * from tblusuario where nome_usuario = ?";
		try {
			con = Conexao.getConexao();
			PreparedStatement ps = con.prepareStatement(sql);
			ps.setString(1, aux);
			ResultSet rs = ps.executeQuery();
			if(rs.next())
				return true;
			
			ps.close();
			con.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return false;
	}
	
	public int checkNomeUsuarioId(String aux){
		String sql = " Select * from tblusuario where nome_usuario = ?";
		 
		try {
			con = Conexao.getConexao();
			PreparedStatement ps = con.prepareStatement(sql);
			ps.setString(1, aux);
			ResultSet rs = ps.executeQuery();
			if(rs.next())
				return rs.getInt("FK_entidade");
			
			ps.close();
			con.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return 0;
	}
	public Funcionario getUsuarioEdit(int num)
	{
		Funcionario fun = null;
		String sql = "SELECT * FROM VWUSUARIOS WHERE FK_ENTIDADE LIKE ? ";
		try {
			con = Conexao.getConexao(); 
			PreparedStatement preparador = con.prepareStatement(sql);
			 preparador.setInt(1,num);
			 ResultSet rs = preparador.executeQuery();
			 if(rs.next())
			 {
				 fun = new Funcionario();
				 fun.setId(rs.getInt("FK_ENTIDADE"));
				 fun.setNome(rs.getString("NOME"));
				 fun.setNomem(rs.getString("NOME_MEIO"));
				 fun.setApelido(rs.getString("ULTIMO_NOME"));
				 fun.setNome_us(rs.getString("NOME_USUARIO"));
				 fun.setNomgrupo(rs.getString("GRUPO"));
				 String senha = rs.getString("SENHA");
				 String decifrada = EncriptaDecriptaRSA.decriptografa(senha);
				 System.out.println("SENHA: "+decifrada);
//				 String senhajsp = decifrada.substring(0, decifrada.length()-1);
//				 System.out.println("SENHA: "+senhajsp);
				 fun.setSenha(decifrada);
				 
			 }
			 preparador.close();
		} catch (Exception e) {
			
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
		return fun;
	}

	public Funcionario buscaEditId (String id)
	{
		Funcionario fun = null;
		String sql = "SELECT * FROM VWENTIDADECIDADAOCOMOFUNCIONARIO WHERE numero_funcionario LIKE ? ORDER BY (NOME)";
		try {
			con = Conexao.getConexao(); 
			PreparedStatement preparador = con.prepareStatement(sql);
			 preparador.setString(1, id);
			 ResultSet rs = preparador.executeQuery();
			 if(rs.next())
			 {
				 fun = new Funcionario();
				 fun.setId(rs.getInt("id_entidade"));
				 fun.setEstado(rs.getInt("efectividade"));
				 fun.setNome(rs.getString("nome"));
				 fun.setNomem(rs.getString("nome_meio"));
				 fun.setApelido(rs.getString("ultimo_nome"));
				 fun.setNum_doc(rs.getString("numero_documento"));
				 Calendar data = Calendar.getInstance();
				 data.setTime(rs.getDate("DATA_NASCIMENTO"));
				 fun.setDataNasc(data);
				 fun.setEndereco(rs.getString("endereco"));
				 fun.setNomePais(rs.getString("pais"));
				 fun.setNomeProv(rs.getString("provincia"));
				 fun.setSalario(rs.getDouble("salario"));
				 fun.setNum_fun(rs.getString("numero_funcionario"));
				 fun.setNum_ss(rs.getString("seguranca_social"));
				 fun.setNif(rs.getString("nif"));
				 fun.setNomeFun(rs.getString("funcao"));
				 fun.setNomeProf(rs.getString("profissao"));
				 fun.setNomeEsp(rs.getString("especialidade"));
				 fun.setEmail(rs.getString("email"));
				 fun.setEditfone(rs.getLong("telefone"));
				 fun.setNomep(rs.getString("nome_parente"));
				 fun.setEditfonep(rs.getLong("telefone_parente"));
				 fun.setNomeAfl(rs.getString("afiliacao"));
				 fun.setNomeEC(rs.getString("estado_civil"));
				 fun.setNomeDoc(rs.getString("tipo_documento"));
				 fun.setGenerot(rs.getString("genero"));
			 }
			 preparador.close();
		} catch (SQLException e) {
			
			e.printStackTrace();
		} catch (Exception e) {
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
		return fun;
	}
	
	public List<Funcionario> buscaEditLista (String id)
	{
		
		List <Funcionario> lista = new ArrayList<Funcionario>();
		String sql = "SELECT * FROM VWENTIDADECIDADAOCOMOFUNCIONARIO WHERE numero_funcionario LIKE ? ORDER BY (NOME)";
		try {
			con = Conexao.getConexao(); 
			PreparedStatement preparador = con.prepareStatement(sql);
			 preparador.setString(1, id);
			 ResultSet rs = preparador.executeQuery();
			 while(rs.next())
			 {
				 Funcionario fun = new Funcionario();
				 fun = new Funcionario();
				 fun.setId(rs.getInt("id_entidade"));
				 fun.setEstado(rs.getInt("efectividade"));
				 fun.setNome(rs.getString("nome"));
				 fun.setNomem(rs.getString("nome_meio"));
				 fun.setApelido(rs.getString("ultimo_nome"));
				 fun.setNum_doc(rs.getString("numero_documento"));
				 Calendar data = Calendar.getInstance();
				 data.setTime(rs.getDate("DATA_NASCIMENTO"));
				 fun.setDataNasc(data);
				 fun.setEndereco(rs.getString("endereco"));
				 fun.setNomePais(rs.getString("pais"));
				 fun.setNumOrdem(rs.getString("numero_ordem"));
				 fun.setNomeProv(rs.getString("provincia"));
				 fun.setSalario(rs.getDouble("salario"));
				 fun.setNum_fun(rs.getString("numero_funcionario"));
				 fun.setNum_ss(rs.getString("seguranca_social"));
				 fun.setNif(rs.getString("nif"));
				 fun.setNomeFun(rs.getString("funcao"));
				 fun.setNomeProf(rs.getString("profissao"));
				 fun.setNomeEsp(rs.getString("especialidade"));
				 fun.setEmail(rs.getString("email"));
				 fun.setEditfone(rs.getLong("telefone"));
				 fun.setNomep(rs.getString("nome_parente"));
				 fun.setEditfonep(rs.getLong("telefone_parente"));
				 fun.setNomeAfl(rs.getString("afiliacao"));
				 fun.setNomeEC(rs.getString("estado_civil"));
				 fun.setNomeDoc(rs.getString("tipo_documento"));
				 fun.setGenerot(rs.getString("genero"));
				 lista.add(fun);
			 }
			 preparador.close();
		} catch (SQLException e) {
			
			e.printStackTrace();
		} catch (Exception e) {
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
		return lista ;
	}
	public Funcionario buscaPorPerfil(String id)
	{
		Funcionario fun = null;
		String sql = "SELECT * FROM VWENTIDADECIDADAOCOMOFUNCIONARIO WHERE id_entidade LIKE ? ";
		try {
			con = Conexao.getConexao(); 
			PreparedStatement preparador = con.prepareStatement(sql);
			 preparador.setString(1, id);
			 ResultSet rs = preparador.executeQuery();
			 if(rs.next())
			 {
				 fun = new Funcionario();
				 fun.setId(rs.getInt("id_entidade"));
				 fun.setEstado(rs.getInt("efectividade"));
				 fun.setNome(rs.getString("nome"));
				 fun.setNomem(rs.getString("nome_meio"));
				 fun.setApelido(rs.getString("ultimo_nome"));
				 fun.setNum_doc(rs.getString("numero_documento"));
				 Calendar data = Calendar.getInstance();
				 data.setTime(rs.getDate("data_nascimento"));
				 fun.setDataNasc(data);
				 fun.setEndereco(rs.getString("endereco"));
				 fun.setNomePais(rs.getString("pais"));
				 fun.setNomeProv(rs.getString("provincia"));
				 fun.setSalario(rs.getDouble("salario"));
				 fun.setNum_fun(rs.getString("numero_funcionario"));
				 fun.setNum_ss(rs.getString("seguranca_social"));
				 fun.setNif(rs.getString("nif"));
				 fun.setGenerot(rs.getString("genero"));
				 fun.setNomeFun(rs.getString("funcao"));
				 fun.setNomeProf(rs.getString("profissao"));
				 fun.setNomeEsp(rs.getString("especialidade"));
				 fun.setEmail(rs.getString("email"));
				 fun.setEditfone(rs.getLong("telefone"));
				 fun.setNomep(rs.getString("nome_parente"));
				 fun.setEditfonep(rs.getLong("telefone_parente"));
				 fun.setNomeAfl(rs.getString("afiliacao"));
				 fun.setNomeEC(rs.getString("estado_civil"));
				 fun.setNomeDoc(rs.getString("tipo_documento"));
			 }
			 preparador.close();
		} catch (SQLException e) {
			
			e.printStackTrace();
		} catch (Exception e) {
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
		return fun;
	}
	public List <Funcionario> buscaPorFuncao (String funcao,int est)
	{
		 
		List <Funcionario> lista = new ArrayList<Funcionario>();
		String sql = "SELECT * FROM vwpesquisarfunfuncao WHERE funcao LIKE ? AND EFECTIVIDADE = ? ORDER BY (NOME)  ";
		try {
			con = Conexao.getConexao(); 
			PreparedStatement preparador = con.prepareStatement(sql);
			 preparador.setString(1, funcao);
			 preparador.setInt(2, est);
			 ResultSet rs = preparador.executeQuery();
			 while(rs.next())
			 {
				 Funcionario fun = new Funcionario();
				 fun.setId(rs.getInt("id_entidade"));
				 fun.setNome(rs.getString("nome"));
				 fun.setNomem(rs.getString("nome_meio"));
				 fun.setApelido(rs.getString("ultimo_nome"));
				 Calendar data = Calendar.getInstance();
				 data.setTime(rs.getDate("DATA_NASCIMENTO"));
				 fun.setDataNasc(data);
				 fun.setEndereco(rs.getString("endereco"));			
				 fun.setNum_fun(rs.getString("numero_funcionario"));
				 fun.setEmail(rs.getString("email"));
				 fun.setEditfone(rs.getLong("TELEFONE"));
				 lista.add(fun);
			 }
			 preparador.close();
		} catch (SQLException e) {
			
			e.printStackTrace();
		} catch (Exception e) {
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
	
	public List <Funcionario> buscaPorTodos (String funcao,int est)
	{
		 
		List <Funcionario> lista = new ArrayList<Funcionario>();
		String sql = "SELECT * FROM vwpesquisarfunfuncao WHERE NUMERO_FUNCIONARIO LIKE ? and NOME LIKE ? FUNCAO LIKE ? AND EFECTIVIDADE = ?";
		try {
			con = Conexao.getConexao(); 
			PreparedStatement preparador = con.prepareStatement(sql);
			 preparador.setString(1, funcao);
			 preparador.setString(2, funcao);
			 preparador.setString(1, funcao);
			 preparador.setString(1, funcao);
			 
			 preparador.setInt(2, est);
			 ResultSet rs = preparador.executeQuery();
			 while(rs.next())
			 {
				 Funcionario fun = new Funcionario();
				 fun.setId(rs.getInt("id_entidade"));
				 fun.setNome(rs.getString("nome"));
				 fun.setNomem(rs.getString("nome_meio"));
				 fun.setApelido(rs.getString("ultimo_nome"));
				 Calendar data = Calendar.getInstance();
				 data.setTime(rs.getDate("DATA_NASCIMENTO"));
				 fun.setDataNasc(data);
				 fun.setEndereco(rs.getString("endereco"));			
				 fun.setNum_fun(rs.getString("numero_funcionario"));
				 fun.setEmail(rs.getString("email"));
				 fun.setTelefone(rs.getLong("telefone"));
				 lista.add(fun);
			 }
			 preparador.close();
		} catch (SQLException e) {
			
			e.printStackTrace();
		} catch (Exception e) {
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
	
	
	public List <Funcionario> buscaTodosPorValidar (String param)
	{
		 
		List <Funcionario> lista = new ArrayList<Funcionario>();
		String sql = "SELECT * FROM vwpesquisarfunfuncao WHERE (NUMERO_FUNCIONARIO LIKE ? OR NOME LIKE ? )  AND EFECTIVIDADE = ? "
				+ " AND id_entidade not in (select fk_funcionario from tblfuncionario_validador) ";
		try {
			con = Conexao.getConexao(); 
			PreparedStatement preparador = con.prepareStatement(sql);
			 preparador.setString(1, "%"+param+"%");
			 preparador.setString(2, "%"+param+"%");
			 preparador.setInt(3, 1);
			 ResultSet rs = preparador.executeQuery();
			 while(rs.next())
			 {
				 Funcionario fun = new Funcionario();
				 fun.setId(rs.getInt("id_entidade"));
				 fun.setNome(rs.getString("nome"));
				 fun.setNomem(rs.getString("nome_meio"));
				 fun.setApelido(rs.getString("ultimo_nome"));
				 Calendar data = Calendar.getInstance();
				 data.setTime(rs.getDate("DATA_NASCIMENTO"));
				 fun.setDataNasc(data);
				 fun.setEndereco(rs.getString("endereco"));			
				 fun.setNum_fun(rs.getString("numero_funcionario"));
				 fun.setEmail(rs.getString("email"));
				 fun.setTelefone(rs.getLong("telefone"));
				 lista.add(fun);
			 }
			 preparador.close();
		} catch (SQLException e) {
			
			e.printStackTrace();
		} catch (Exception e) {
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
	
	public List <Funcionario> buscaTodosPorValidar ()
	{
		 
		List <Funcionario> lista = new ArrayList<Funcionario>();
		String sql = "SELECT * FROM vwpesquisarfunfuncao WHERE  EFECTIVIDADE = 1 AND id_entidade not in (select fk_funcionario from tblfuncionario_validador) LIMIT 100   ";
		try {
			con = Conexao.getConexao(); 
			PreparedStatement preparador = con.prepareStatement(sql);
			 ResultSet rs = preparador.executeQuery();
			 while(rs.next())
			 {
				 Funcionario fun = new Funcionario();
				 fun.setId(rs.getInt("id_entidade"));
				 fun.setNome(rs.getString("nome"));
				 fun.setNomem(rs.getString("nome_meio"));
				 fun.setApelido(rs.getString("ultimo_nome"));
				 Calendar data = Calendar.getInstance();
				 data.setTime(rs.getDate("DATA_NASCIMENTO"));
				 fun.setDataNasc(data);
				 fun.setEndereco(rs.getString("endereco"));			
				 fun.setNum_fun(rs.getString("numero_funcionario"));
				 fun.setEmail(rs.getString("email"));
				 fun.setTelefone(rs.getLong("telefone"));
				 lista.add(fun);
			 }
			 preparador.close();
		} catch (SQLException e) {
			
			e.printStackTrace();
		} catch (Exception e) {
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
	
	
	
	public List <Funcionario> buscaTodosPorValidados ()
	{
		 
		List <Funcionario> lista = new ArrayList<Funcionario>();
		String sql = "SELECT * FROM vwpesquisarfunfuncao WHERE  EFECTIVIDADE = 1 AND id_entidade  in (select fk_funcionario from tblfuncionario_validador) LIMIT 100   ";
		try {
			con = Conexao.getConexao(); 
			PreparedStatement preparador = con.prepareStatement(sql);
			 ResultSet rs = preparador.executeQuery();
			 while(rs.next())
			 {
				 Funcionario fun = new Funcionario();
				 fun.setId(rs.getInt("id_entidade"));
				 fun.setNome(rs.getString("nome"));
				 fun.setNomem(rs.getString("nome_meio"));
				 fun.setApelido(rs.getString("ultimo_nome"));
				 Calendar data = Calendar.getInstance();
				 data.setTime(rs.getDate("DATA_NASCIMENTO"));
				 fun.setDataNasc(data);
				 fun.setEndereco(rs.getString("endereco"));			
				 fun.setNum_fun(rs.getString("numero_funcionario"));
				 fun.setEmail(rs.getString("email"));
				 fun.setTelefone(rs.getLong("telefone"));
				 lista.add(fun);
			 }
			 preparador.close();
		} catch (SQLException e) {
			
			e.printStackTrace();
		} catch (Exception e) {
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
	
	public Funcionario buscaTodosPorValidar (int codf)
	{
		Funcionario fun = new Funcionario();
		List <Funcionario> lista = new ArrayList<Funcionario>();
		String sql = "SELECT * FROM vwpesquisarfunfuncao WHERE id_entidade = ? AND EFECTIVIDADE = 1 ";
		try {
			con = Conexao.getConexao(); 
			PreparedStatement preparador = con.prepareStatement(sql);
			preparador.setInt(1, codf);
			 ResultSet rs = preparador.executeQuery();
			 if(rs.next())
			 {
				 
				 fun.setId(rs.getInt("id_entidade"));
				 fun.setNome(rs.getString("nome"));
				 fun.setNomem(rs.getString("nome_meio"));
				 fun.setApelido(rs.getString("ultimo_nome"));
				 Calendar data = Calendar.getInstance();
				 data.setTime(rs.getDate("DATA_NASCIMENTO"));
				 fun.setDataNasc(data);
				 fun.setEndereco(rs.getString("endereco"));			
				 fun.setNum_fun(rs.getString("numero_funcionario"));
				 fun.setEmail(rs.getString("email"));
				 fun.setTelefone(rs.getLong("telefone"));
				 fun.setEditfone(rs.getLong("telefone"));
				 fun.setNomeFun(rs.getString("funcao"));
			 }
			 preparador.close();
		} catch (SQLException e) {
			
			e.printStackTrace();
		} catch (Exception e) {
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
		return fun;
	}
	public List<Funcionario> buscaFuncao()
	{
		List <Funcionario> lista = new ArrayList<Funcionario>();
		String sql = "SELECT * FROM TBLFUNCAO ORDER BY ID_FUNCAO = 0 DESC, FUNCAO ASC";
		try {
			con = Conexao.getConexao(); 
			PreparedStatement preparador = con.prepareStatement(sql);
			 ResultSet rs = preparador.executeQuery();
			 while(rs.next())
			 {
				 Funcionario fun = new Funcionario();
				 fun.setFuncao(rs.getInt("id_funcao"));
				 fun.setNomeFun(rs.getString("funcao"));
				 lista.add(fun);
			 }
			 preparador.close();
		} catch (SQLException e) {
		
			e.printStackTrace();
		} catch (Exception e) {
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
	public List<Funcionario> buscaProvincia (int pais)
	{
		List <Funcionario> lista = new ArrayList<Funcionario>();
		String sql = "SELECT * FROM TBLPROVINCIA WHERE FK_PAIS = ?";
		try {
			con = Conexao.getConexao(); 
			PreparedStatement preparador = con.prepareStatement(sql);
			 preparador.setInt(1, pais);
			 ResultSet rs = preparador.executeQuery();
			 while(rs.next())
			 {
				 Funcionario fun = new Funcionario();
				 fun.setProvincia(rs.getInt("id_provincia"));
				 fun.setNomeProv(rs.getString("provincia"));
				 lista.add(fun);
			 }
			 preparador.close();
		} catch (SQLException e) {
			e.printStackTrace();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return lista;
	}
	
	public int id_prov(String prov)
	{
		int id=0;
		String sql = "SELECT * FROM TBLPROVINCIA WHERE PROVINCIA LIKE ?";
		try {
			con = Conexao.getConexao(); 
			PreparedStatement preparador = con.prepareStatement(sql);
			 preparador.setString(1, prov);
			 ResultSet rs = preparador.executeQuery();
			 if(rs.next())
			 id = rs.getInt("id_provincia");
			 preparador.close();
		} catch (SQLException e) {
			e.printStackTrace();
		} catch (Exception e) {
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
		return id;
	}
	
	public void fun_cidadao (Funcionario fun)
	{
		
		String sql = "INSERT INTO TBLCIDADAO (fk_entidade,nome,nome_meio,ultimo_nome,data_nascimento,fk_estadocivil,fk_genero,fk_pais,fk_tipo_documento,numero_documento) VALUES (?,?,?,?,?,?,?,?,?,?)";
		try {
			 
			 //tblcidadao
			con = Conexao.getConexao(); 
			PreparedStatement cid = con.prepareStatement(sql);
			 cid.setInt(1, fun.getId()); 
			 cid.setString(2, fun.getNome());
			 cid.setString(3, fun.getNomem());
			 cid.setString(4, fun.getApelido());
			 cid.setDate(5, new Date (fun.getDataNasc().getTimeInMillis()));
			 cid.setInt (6, fun.getEst_civil());
			 cid.setInt(7, fun.getGenero());
			 cid.setInt(8, fun.getPais());
			 cid.setInt(9, fun.getTipo_doc());
			 cid.setString(10, fun.getNum_doc());
			 cid.execute();
			 cid.close();
			 System.out.println("Cadastro de sucesso...TBLCIDADAO"); 
		} catch (SQLException e) {
		
			e.printStackTrace();
		} catch (Exception e) {
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
	//3 tabela - telefone
	public void privilegios(Funcionario fun)
	{
		String sql = "INSERT INTO TBLPRIVILEGIOATRIBUIDO (fk_funcionalidade,fk_grupo,fk_funcionario,data,fk_tela,fk_modulo) VALUES (?,?,?,?,?,?)";
		try {
			con = Conexao.getConexao();
			PreparedStatement ent = con.prepareStatement(sql);
			ent.setInt(1, fun.getId());
			ent.setInt(2, fun.getId());
			ent.setInt(3, fun.getId());
			ent.setDate(4,data_registo());
			ent.setInt(5, fun.getId());
			ent.setInt(6, fun.getId());
			ent.execute();
			ent.close();
			System.out.println("Cadastro de sucesso...TBLTELEFONE"); 
		} catch (SQLException e) {
			
			e.printStackTrace();
		} catch (Exception e) {
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
	public void fun_fone(Funcionario fun)
	{
		String sql = "INSERT INTO TBLTELEFONE (fk_entidade,telefone) VALUES (?,?)";
		try {
			con = Conexao.getConexao();
			PreparedStatement ent = con.prepareStatement(sql);
			ent.setInt(1, fun.getId());
			ent.setLong(2, fun.getTelefone());
			ent.execute();
			ent.close();
			System.out.println("Cadastro de sucesso...TBLTELEFONE"); 
		} catch (SQLException e) {
			
			e.printStackTrace();
		} catch (Exception e) {
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
	
	// 4 tabela - email
	public void fun_email(Funcionario fun)
	{
		String sql = "INSERT INTO TBLEMAIL (fk_entidade,email) VALUES (?,?)";
		try {
			con = Conexao.getConexao();
			PreparedStatement ent = con.prepareStatement(sql);
			ent.setInt(1, fun.getId());
			ent.setString(2, fun.getEmail());
			ent.execute();
			ent.close();
			System.out.println("Cadastro de sucesso...TBLEMAIL"); 
		} catch (SQLException e) {
			
			e.printStackTrace();
		} catch (Exception e) {
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
	// 5 tabela - funcionario
	public void fun_funcionario(Funcionario fun)
	{
		String sql = "INSERT INTO TBLFUNCIONARIO (fk_entidade,seguranca_social,nif,numero_funcionario,numero_ordem,salario,fk_especialidade,fk_profissao,fk_funcao,data_registo) VALUES (?,?,?,?,?,?,?,?,?,?)";
		try {
			con = Conexao.getConexao();
			PreparedStatement ent = con.prepareStatement(sql);
			ent.setInt(1, fun.getId());
			ent.setString(2, fun.getNum_ss());
			ent.setString(3, fun.getNif());
			ent.setString(4, fun.getNum_fun());
			ent.setString(5, fun.getNumOrdem());
			ent.setDouble(6, fun.getSalario());
			ent.setInt(7, fun.getEspecialidade());
			ent.setInt(8, fun.getProfissao());
			ent.setInt(9, fun.getFuncao());
			ent.setDate(10, data_registo());
			ent.execute();
			ent.close();
			System.out.println("Cadastro de sucesso...TBLFUNCIONARIO"); 
		} catch (SQLException e) {
			
			e.printStackTrace();
		} catch (Exception e) {
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
	// 6 tabela - parente
	public void fun_parente(Funcionario fun)
	{
		String sql = "INSERT INTO TBLPARENTE (fk_entidade,nome_parente,telefone_parente,fk_afiliacao) VALUES (?,?,?,?)";
		try {
			con = Conexao.getConexao();
			PreparedStatement ent = con.prepareStatement(sql);
			ent.setInt(1, fun.getId());
			ent.setString(2, fun.getNomep());
			ent.setLong(3, fun.getTelefonep());
			ent.setInt(4, fun.getAfiliacao());
			ent.execute();			
			ent.close();
			System.out.println("Cadastro de sucesso...TBLPARENTE"); 
		} catch (SQLException e) {
			
			e.printStackTrace();
		} catch (Exception e) {
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
	public void fun_endereco(Funcionario fun)
	{
		String sql = "INSERT INTO TBLENDERECO (fk_entidade,endereco) VALUE (?,?)";
		try {
			con = Conexao.getConexao();
			PreparedStatement ent = con.prepareStatement(sql);
			ent.setInt(1, fun.getId());
			ent.setString(2, fun.getEndereco());
			ent.execute();
			ent.close();
			System.out.println("Cadastro de sucesso...TBLENDERECO"); 
		} catch (SQLException e) {
			
			e.printStackTrace();
		} catch (Exception e) {
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
	// 8 tabela - naturalidade
	public void fun_naturalidade(Funcionario fun)
	{
		String sql = "INSERT INTO TBLNATURALIDADE (fk_entidade,fk_pais,fk_provincia) VALUES (?,?,?)";
		try {
			con = Conexao.getConexao();
			PreparedStatement ent = con.prepareStatement(sql);
			ent.setInt(1, fun.getId());
			ent.setInt(2, fun.getPais());
			ent.setInt(3, fun.getProvincia());
			ent.execute();
			ent.close();
			System.out.println("Cadastro de sucesso...TBLNATURALIDADE"); 
		} catch (SQLException e) {

			e.printStackTrace();
		} catch (Exception e) {
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
	public void NovoUsurio(Funcionario fun)
	{
		String sql = "INSERT INTO TBLUSUARIO (NOME_USUARIO,SENHA,FK_GRUPO,FK_ENTIDADE) VALUES (?,?,?,?)";
		try {
			con = Conexao.getConexao();
			PreparedStatement ent = con.prepareStatement(sql);
			ent.setString(1, fun.getNome_us());
			ent.setString(2, fun.getSenha());
			ent.setInt(3, fun.getFk_grupo());
			ent.setInt(4, fun.getId());
			ent.execute();
			ent.close();
			con.close();
			System.out.println("Cadastro com sucesso...TBLUSUARIO SENHA: "+fun.getSenha()); 
		} catch (SQLException e) {

			e.printStackTrace();
		}  
	}
	public void NovoUsurioRSA(Funcionario fun )
	{
		String sql = "INSERT INTO TBLUSUARIO (NOME_USUARIO,SENHA,FK_GRUPO,FK_ENTIDADE) VALUES (?,?,?,?)";
		try {
			con = Conexao.getConexao();
			PreparedStatement ent = con.prepareStatement(sql);
			ent.setString(1, fun.getNome_us());
			ent.setString(2, fun.getSenha());
			ent.setInt(3, fun.getFk_grupo());
			ent.setInt(4, fun.getId());
			ent.execute();
			ent.close();
			con.close();
			System.out.println("Cadastro com sucesso...TBLUSUARIO SENHA: "+fun.getSenha()); 
		} catch (SQLException e) {

			e.printStackTrace();
		}  
	}
	
	public void EditarSenhaUsuarioRSA(Funcionario fun )
	{
		String sql = "UPDATE TBLUSUARIO SET SENHA = ? WHERE FK_ENTIDADE = ? ";
		try {
			con = Conexao.getConexao();
			PreparedStatement ent = con.prepareStatement(sql);
			ent.setString(1, fun.getSenha());
			ent.setInt(2, fun.getFK_entidade());
			ent.execute();
			ent.close();
			con.close();
			 
		} catch (SQLException e) {

			e.printStackTrace();
		}  
	}
	public void fun_arquivos(Funcionario fun)
	{
		String sql = "INSERT INTO TBLARQUIVODOFUNCIONARIO (fk_entidade,fk_arquivo,caminho,nome_ficheiro) VALUES (?,?,?,?)";
		try {
			con = Conexao.getConexao();
			PreparedStatement ent = con.prepareStatement(sql);
			ent.setInt(1, fun.getId());
			ent.setInt(2, fun.getArquivo());
			ent.setString(3, fun.getLocalArq());
			ent.setString(4, fun.getNomeArq());
			ent.execute();
			ent.close();
			System.out.println("Cadastro de sucesso...TBLARQUIVOS"); 
		} catch (SQLException e) {

			e.printStackTrace();
		} catch (Exception e) {
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
	
	public void up_estado(Funcionario fun)
	{
		String sql = "UPDATE TBLENTIDADE SET EFECTIVIDADE=? WHERE ID_ENTIDADE=?";
		try {
			con = Conexao.getConexao();
			PreparedStatement ent = con.prepareStatement(sql);
			ent.setInt(1, fun.getEstado());
			ent.setInt(2, fun.getId());
			ent.execute();
			ent.close();
		} catch (SQLException e) {
			e.printStackTrace();
		} catch (Exception e) {
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
	
	public void up_endereco(Funcionario fun)
	{
		String sql = "UPDATE TBLENDERECO SET endereco =? WHERE fk_entidade=?";
		try {
			con = Conexao.getConexao();
			PreparedStatement ent = con.prepareStatement(sql);
			ent.setString(1, fun.getEndereco());
			ent.setInt(2, fun.getId());
			ent.execute();
			ent.close();
			System.out.println("Alteracao com sucesso...TBLENDERECO"); 
		} catch (SQLException e) {
		
			e.printStackTrace();
		} catch (Exception e) {
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
	// 8 tabela - naturalidade
	public void up_naturalidade(Funcionario fun)
	{
		String sql = "UPDATE TBLNATURALIDADE SET fk_pais=?,fk_provincia=? WHERE fk_entidade=?";
		try {
			con = Conexao.getConexao();
			PreparedStatement ent = con.prepareStatement(sql);
			ent.setInt(1, fun.getPais());
			ent.setInt(2, fun.getProvincia());
			ent.setInt(3, fun.getId());
			ent.execute();
			ent.close();
			System.out.println("Cadastro de sucesso...TBLNATURALIDADE"); 
		} catch (SQLException e) {
			
			e.printStackTrace();
		} catch (Exception e) {
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
	public void up_cidadao (Funcionario fun)
	{
		
		String sql = "UPDATE TBLCIDADAO SET nome=?," +"nome_meio=?," + "ultimo_nome=?," +
				                           "data_nascimento=?," +"fk_estadocivil=?," +
				                           "fk_genero=?," + "fk_pais=?," +
				                             "fk_tipo_documento=?," +
				                           "numero_documento=? " + 
				                           "WHERE  fk_entidade=?";
		try {
			 
			 //tblcidadao
			con = Conexao.getConexao(); 
			PreparedStatement cid = con.prepareStatement(sql);
			 
			 cid.setString(1, fun.getNome());
			 cid.setString(2, fun.getNomem());
			 cid.setString(3, fun.getApelido());
			 cid.setDate(4, new Date (fun.getDataNasc().getTimeInMillis()));
			 cid.setInt (5, fun.getEst_civil());
			 cid.setInt(6, fun.getGenero());
			 cid.setInt(7, fun.getPais());
			 cid.setInt(8, fun.getTipo_doc());
			 cid.setString(9, fun.getNum_doc()); 
			 cid.setInt(10, fun.getId());
			 cid.execute();
			 cid.close();
			 System.out.println("Alteracao efectuada com sucesso...TBLCIDADAO"); 
		} catch (SQLException e) {

			e.printStackTrace();
		} catch (Exception e) {
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
	//3 tabela - telefone
	public void up_fone(Funcionario fun)
	{
		
			String sql = "UPDATE TBLTELEFONE SET telefone=? WHERE fk_entidade = ?";
			try {
				con = Conexao.getConexao();
				PreparedStatement ent = con.prepareStatement(sql);
				ent.setLong(1, fun.getTelefone());
				ent.setLong(2, fun.getId());
				ent.execute();
				ent.close();
				System.out.println("Alteracao efectuada com sucesso...TBLTELEFONE"); 
			} catch (SQLException e) {
				e.printStackTrace();
			} catch (Exception e) {
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
	
	public int checkTelf(Funcionario fun)
	{
		String sql = "SELECT fk_entidade FROM TBLTELEFONE WHERE TELEFONE LIKE ?";
		int foneCod =0;
		try {
			con = Conexao.getConexao();
			PreparedStatement ent = con.prepareStatement(sql);
			ent.setLong(1, fun.getTelefone());
			ResultSet rs = ent.executeQuery();
			if( rs.next())
				foneCod = rs.getInt("fk_entidade");
		    } 
		catch (SQLException e) {	e.printStackTrace();	} 
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
		return foneCod;
	}
	// 4 tabela - email
	public void up_email(Funcionario fun)
	{
		String sql = "UPDATE TBLEMAIL SET email=? WHERE fk_entidade=?";
		try {
			con = Conexao.getConexao();
			PreparedStatement ent = con.prepareStatement(sql);
			ent.setString(1, fun.getEmail());
			ent.setInt(2, fun.getId());
			ent.execute();
			ent.close();
			System.out.println("Alteracao efectuada com sucesso...TBLEMAIL"); 
		} catch (SQLException e) {
			e.printStackTrace();
		} catch (Exception e) {
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
	// 5 tabela - funcionario
	public void MedNumOr(Funcionario fun)
	{
		String sql = "UPDATE TBLFUNCIONARIO SET numero_ordem=? WHERE fk_entidade=?";
		try {
			con = Conexao.getConexao();
			PreparedStatement ent = con.prepareStatement(sql);
			ent.setString(1, fun.getNumOrdem());
			ent.setInt   (2, fun.getId());
			ent.execute();
			ent.close();
			System.out.println("Adicionado com sucesso...TBLFUNCIONARIO - Numero Ordem"); 
		} catch (SQLException e) {

			e.printStackTrace();
		} catch (Exception e) {
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
	public void up_funcionario(Funcionario fun)
	{
		String sql = "UPDATE TBLFUNCIONARIO SET seguranca_social=?,nif=?,salario=?,fk_especialidade=?,fk_profissao=?,fk_funcao=?,data_registo=? WHERE fk_entidade=?";
		try {
			con = Conexao.getConexao();
			PreparedStatement ent = con.prepareStatement(sql);
			ent.setString(1, fun.getNum_ss());
			ent.setString(2, fun.getNif());
			ent.setDouble(3, fun.getSalario());
			ent.setInt   (4, fun.getEspecialidade());
			ent.setInt   (5, fun.getProfissao());
			ent.setInt   (6, fun.getFuncao());
			ent.setDate  (7, data_registo());
			ent.setInt   (8, fun.getId());
			ent.execute();
			ent.close();
			System.out.println("ALTERACAO com sucesso...TBLFUNCIONARIO"); 
		} catch (SQLException e) {

			e.printStackTrace();
		} catch (Exception e) {
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
	// 6 tabela - parente
	@SuppressWarnings("resource")
	public void up_parente(Funcionario fun)
	{
		
		String sql = "Select * from TBLPARENTE  where FK_entidade = ?";
//		
		try {
			con = Conexao.getConexao();
			PreparedStatement ent = con.prepareStatement(sql);
			ent.setInt(1, fun.getId());
			ResultSet rs = ent.executeQuery();
			if(rs.next()){
			    sql = "UPDATE TBLPARENTE SET nome_parente=?,telefone_parente=?,fk_afiliacao=? WHERE fk_entidade =?";
			    ent = con.prepareStatement(sql);
		        ent.setString(1, fun.getNomep());
				ent.setLong(2, fun.getTelefonep());
				ent.setInt(3, fun.getAfiliacao());
				ent.setInt(4, fun.getId());
				ent.execute();			
				ent.close();
			}
			else{
				sql = "Insert into TBLPARENTE (nome_parente,telefone_parente,FK_afiliacao,FK_entidade) value (?,?,?,?)";
				ent = con.prepareStatement(sql);
				ent.setString(1, fun.getNomep());
				ent.setLong(2, fun.getTelefonep());
				ent.setInt(3, fun.getAfiliacao());
				ent.setInt(4, fun.getId());
				ent.execute();			
				ent.close();
			}
			
			System.out.println("ALTERACAO com sucesso...TBLPARENTE"); 
		} catch (SQLException e) {

			e.printStackTrace();
		} catch (Exception e) {
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
	
	public void up_usuario(Funcionario fun)
	{
		String sql = "UPDATE TBLUSUARIO SET nome_usuario=?,senha=?,fk_grupo=? WHERE fk_entidade =?";
		try {
			con = Conexao.getConexao();
			PreparedStatement ent = con.prepareStatement(sql);
			ent.setString(1, fun.getNome_us());
			ent.setString(2, fun.getSenha());
			ent.setInt(3, fun.getFk_grupo());
			ent.setInt(4, fun.getId());
			ent.execute();			
			ent.close();
			System.out.println("ALTERACAO com sucesso...TBLUSUARIO SENHA: "+fun.getSenha()); 
		} catch (SQLException e) {
			e.printStackTrace();
		} catch (Exception e) {
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
	public int checkeEmail(String email)
	{
		String sql = "SELECT fk_entidade FROM TBLEMAIL WHERE email LIKE ? ";
		int idEmailCh=0;
		try {
			con = Conexao.getConexao(); 
			PreparedStatement preparador = con.prepareStatement(sql);
			 preparador.setString   (1, email);
			 ResultSet rs = preparador.executeQuery();
			 if(rs.next())
				 idEmailCh = rs.getInt("fk_entidade"); 
			 
			 preparador.close();
			 System.out.println("Exclusao com sucesso...TBLCIDADAO"); 
		} catch (SQLException e) {
		
			e.printStackTrace();
		} catch (Exception e) {
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
		return idEmailCh;
	}
	public void delete_cid(int id)
	{
		String sql = "DELETE FROM TBLCIDADAO  WHERE FK_entidade=?";
		try {
			con = Conexao.getConexao();
			 PreparedStatement preparador = con.prepareStatement(sql);
			 preparador.setInt   (1, id);
			 preparador.execute();
			 preparador.close();
			 System.out.println("Exclusao com sucesso...TBLCIDADAO"); 
		} catch (SQLException e) {
		
			e.printStackTrace();
		} catch (Exception e) {
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
	public void delete_cidEnd(int id)
	{
		String sql = "DELETE FROM TBLENDERECO  WHERE FK_entidade=?";
		try {
			con = Conexao.getConexao();
			 PreparedStatement preparador = con.prepareStatement(sql);
			 preparador.setInt   (1, id);
			 preparador.execute();
			 preparador.close();
			 System.out.println("Exclusao com sucesso...TBLENDERECO"); 
		} catch (SQLException e) {
			
			e.printStackTrace();
		} catch (Exception e) {
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
	public void delete_cidparente(int id)
	{
		String sql = "DELETE FROM TBLPARENTE  WHERE FK_entidade=?";
		try {
			con = Conexao.getConexao();
			 PreparedStatement preparador = con.prepareStatement(sql);
			 preparador.setInt   (1, id);
			 preparador.execute();
			 preparador.close();
			 System.out.println("Exclusao com sucesso...TBLPARENTE"); 
		} catch (SQLException e) {
			
			e.printStackTrace();
		} catch (Exception e) {
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
	public void delete_cidfone(int id)
	{
		String sql = "DELETE FROM TBLTELEFONE  WHERE FK_entidade=?";
		try {
			con = Conexao.getConexao();
			 PreparedStatement preparador = con.prepareStatement(sql);
			 preparador.setInt   (1, id);
			 preparador.execute();
			 preparador.close();
			 System.out.println("Exclusao com sucesso...TBLTELEFONE"); 
		} catch (SQLException e) {
			
			e.printStackTrace();
		} catch (Exception e) {
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
	public void delete_cidfun(int id) throws Exception
	{
		String sql = "DELETE FROM TBLFUNCIONARIO  WHERE FK_entidade=?";
		try {
			con = Conexao.getConexao();
			 PreparedStatement preparador = con.prepareStatement(sql);
			 preparador.setInt   (1, id);
			 preparador.execute();
			 preparador.close();
			 System.out.println("Exclusao com sucesso...TBLFUNCIONARIO"); 
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
	
	
	public void delete_cidEnt(int id) throws Exception
	{
		String sql = "DELETE FROM TBLENTIDADE  WHERE id_entidade=?";
		try {
			con = Conexao.getConexao();
			 PreparedStatement preparador = con.prepareStatement(sql);
			 preparador.setInt   (1, id);
			 preparador.execute(); 
			 preparador.close();
			 System.out.println("Exclusao com sucesso...TBLENTIDADE"); 
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
	public void delete_cidNat(int id)
	{
		String sql = "DELETE FROM TBLNATURALIDADE WHERE FK_entidade=?";
		try {
			con = Conexao.getConexao();
			 PreparedStatement preparador = con.prepareStatement(sql);
			 preparador.setInt   (1, id);
			 preparador.execute();
			 preparador.close();
			 System.out.println("Exclusao com sucesso...TBLENTIDADE"); 
		} catch (SQLException e) {
			
			e.printStackTrace();
		} catch (Exception e) {
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
	public void delete_cidEmail(int id)
	{
		String sql = "DELETE FROM TBLEMAIL  WHERE FK_entidade=?";
		try {
			con = Conexao.getConexao();
			 PreparedStatement preparador = con.prepareStatement(sql);
			 preparador.setInt   (1, id);
			 preparador.execute();
			 preparador.close();
			 System.out.println("Exclusao com sucesso...TBLEMAIL"); 
		} catch (SQLException e) {
			
			e.printStackTrace();
		} catch (Exception e) {
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
	
	public void delete_usu(int id)
	{
		String sql = "DELETE FROM TBLUSUARIO  WHERE FK_entidade=?";
		try {
			con = Conexao.getConexao();
			 PreparedStatement preparador = con.prepareStatement(sql);
			 preparador.setInt   (1, id);
			 preparador.execute();
			 preparador.close();
			 System.out.println("Exclusao com sucesso...TBLUSUARIO"); 
		} catch (SQLException e) {
		
			e.printStackTrace();
		} catch (Exception e) {
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
	
	public List<Funcionario> listaDoutoreTodos()
	{
		String sql = "SELECT * FROM vwfuncionariomedico order by nome";
		List<Funcionario> lista = new ArrayList<Funcionario>();
		try {
			 con = Conexao.getConexao();
			 PreparedStatement ps = con.prepareStatement(sql);
			 ResultSet rs = ps.executeQuery();
			 while(rs.next())
			 {
				 Funcionario f = new Funcionario();
				 f.setFK_entidade(rs.getInt("FK_entidade"));
				 f.setNome(rs.getString("nome"));
				 f.setApelido(rs.getString("ultimo_nome"));
				 lista.add(f);
			 }
			 
		} catch (Exception e) {
			e.printStackTrace();
		}
		return lista;
	}
	

}
