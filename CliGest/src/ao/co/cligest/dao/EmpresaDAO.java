package ao.co.cligest.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

//import com.sun.corba.se.spi.legacy.connection.GetEndPointInfoAgainException;

import ao.co.cligest.entidades.*;

public class EmpresaDAO {

	private Connection con;

	// gerar numero de Fornecedor
	public String gerarNumeroForn() {
		con = Conexao.getConexao();
		int seqnciaNumeroForn = 0;
		String sql = "select MAX(FK_fornecedor) from tblfornecedor";
		try {
			PreparedStatement stm = con.prepareStatement(sql);
			ResultSet recebeSeqncia = stm.executeQuery();
			if (recebeSeqncia.next()) {
				seqnciaNumeroForn = recebeSeqncia.getInt(1);
				seqnciaNumeroForn++;
			}
		} catch (Exception e) {
			System.out.println("Sequencia de conta falhou");
			System.out.println(e.getMessage());
		}

		return "EMPR0" + seqnciaNumeroForn;
	}

	// Comeco Para verificar dados repetidos na insercaso de ua nova empresa
	public boolean verNomeRepetido(String termo) {
		boolean ok = false;
		String sql = "SELECT * FROM tblfornecedor WHERE empresa Like ?";
		try {
			con = Conexao.getConexao();
			PreparedStatement preparador = con.prepareStatement(sql);
			preparador.setString(1, termo);
			ResultSet rs = preparador.executeQuery();
			if (rs.next()) {
				ok = true;
			}
			preparador.close();
		} catch (SQLException e) {
			e.printStackTrace();
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			try {
				con.close();
			} catch (SQLException ef) {
				System.out.println("Erro finalizar: " + ef);
			}
		}
		return ok;
	}

	// Para ver nis repetidos
	public boolean verNIFRepetido(String termo) {
		boolean ok = false;
		String sql = "SELECT * FROM tblfornecedor WHERE nif Like ?";
		try {
			con = Conexao.getConexao();
			PreparedStatement preparador = con.prepareStatement(sql);
			preparador.setString(1, termo);
			ResultSet rs = preparador.executeQuery();
			if (rs.next()) {
				ok = true;
			}
			preparador.close();
		} catch (SQLException e) {
			e.printStackTrace();
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			try {
				con.close();
			} catch (SQLException ef) {
				System.out.println("Erro finalizar: " + ef);
			}
		}
		return ok;
	}

	// Para ver nis repetidos
	public boolean verAlvaraRepetido(String termo) {
		boolean ok = false;
		String sql = "SELECT * FROM tblfornecedor WHERE numero_doalvara Like ?";
		try {
			con = Conexao.getConexao();
			PreparedStatement preparador = con.prepareStatement(sql);
			preparador.setString(1, termo);
			ResultSet rs = preparador.executeQuery();
			if (rs.next()) {
				ok = true;
			}
			preparador.close();
		} catch (SQLException e) {
			e.printStackTrace();
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			try {
				con.close();
			} catch (SQLException ef) {
				System.out.println("Erro finalizar: " + ef);
			}
		}
		return ok;
	}

	// Fim Para verificar dados repetidos na insercaso de ua nova empresa

	public void entidadeFornecedor(Paciente fun) {

		String sql = "INSERT INTO TBLENTIDADE (ID_ENTIDADE) VALUE (?)";
		try {
			con = Conexao.getConexao();
			PreparedStatement ent = con.prepareStatement(sql);
			ent.setInt(1, fun.getFK_fornecedor());
			ent.execute();
			ent.close();
			con.close();
			System.out.println("Cadastro de sucesso...TBLENTIDADE");
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	public List<Paciente> buscarempresa() {
		List<Paciente> lista = new ArrayList<Paciente>();
		String sql = "SELECT * FROM vwfornecedor";
		try {
			con = Conexao.getConexao();
			PreparedStatement preparador = con.prepareStatement(sql);
			ResultSet rs = preparador.executeQuery();
			while (rs.next()) {
				Paciente fun = new Paciente();
				fun.setFK_fornecedor(rs.getInt("FK_fornecedor"));
				fun.setNomefornecedor(rs.getString("empresa"));
				fun.setNumero_doalvara(rs.getString("numero_doalvara"));
				fun.setTelefoneemp(rs.getLong("telefone"));
				fun.setNif(rs.getString("nif"));
				fun.setEmailemp(rs.getString("email"));
				lista.add(fun);
			}
			preparador.close();
			con.close();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return lista;
	}
	


	// para pegar dados da empresa e do fornecedor
	public List<Paciente> buscarDadosRepres(int numeroEmpresa) { //and cg.id_funcao=rep.CE_cargo
		List<Paciente> lista = new ArrayList<Paciente>();
		String sql = "select * from tblcontacto_fornecedor rep, tblfuncao cg where cg.id_funcao=rep.FK_cargo and  FK_fornecedor ="+numeroEmpresa;

		try {
			con = Conexao.getConexao();
			PreparedStatement preparador = con.prepareStatement(sql);
			ResultSet rs = preparador.executeQuery();
			while (rs.next()) {
				Paciente fun = new Paciente();
				fun.setIdcontacto_fornecedor(rs.getInt("Idcontacto_fornecedor"));
				fun.setPrimeiroNome(rs.getString("primeiro_nome"));
				fun.setUltimoNome(rs.getString("ultimo_nome"));
				fun.setTelefoneRep(rs.getLong("telefone"));
				fun.setEmailRep(rs.getString("email"));
				fun.setEnderecoRep(rs.getString("endereco"));
				fun.setNotaRep(rs.getString("notas_do_cont_forn"));
				fun.setCargoRep(rs.getString("funcao"));
				fun.setCE_fornecedor(rs.getInt("FK_fornecedor"));
				fun.setCE_cargo(rs.getInt("Id_funcao"));

				lista.add(fun);
			}
			preparador.close();
			con.close();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return lista;
	}

	// para pegar dados da empresa e do fornecedor
	public List<Paciente> buscarDadosUmaEmp(String termo) {
		List<Paciente> lista = new ArrayList<Paciente>();
		String sql = "select distinct * from tblfornecedor emp,tbltelefone fone, tblemail emai, tblendereco ende "
				+ "where emai.FK_entidade = emp.FK_fornecedor and "
				+ "ende.FK_entidade = emp.FK_fornecedor and "
				+ "fone.FK_entidade = emp.FK_fornecedor and (empresa like  ? or numero_doalvara like  ?)"; 
		try {
			con = Conexao.getConexao();
			PreparedStatement preparador = con.prepareStatement(sql);
			preparador.setString(1, termo);
			preparador.setString(2, termo);
			ResultSet rs = preparador.executeQuery();
			while (rs.next()) {
				Paciente emps = new Paciente();
				emps.setFK_fornecedor(rs.getInt("FK_fornecedor"));
				emps.setNumero_fornecedor(rs.getString("numero_fornecedor"));
				emps.setNomefornecedor(rs.getString("empresa"));
				emps.setNumero_doalvara(rs.getString("numero_doalvara"));
				emps.setNif(rs.getString("nif"));
				emps.setNota(rs.getString("nota"));
				emps.setEmailemp(rs.getString("email"));
				emps.setPaginaWeb(rs.getString("paginaWeb"));
				emps.setTelefoneemp(rs.getLong("telefone"));
				emps.setEnderecoemp(rs.getString("endereco"));
				lista.add(emps);
			}
			preparador.close();
			con.close();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return lista;
	}

	// para pegar dados da empresa e do fornecedor
	public List<Paciente> buscarDadosEmp_Repres() 
	{
		List<Paciente> lista = new ArrayList<Paciente>();
		String sql = "select emp.FK_fornecedor, emp.numero_fornecedor, emp.numero_doalvara, emp.nif, emp.empresa, emp.nota, emp.paginaWeb, fone.telefone, emai.email, ende.endereco from tblfornecedor emp, tbltelefone fone, tblemail emai, tblendereco ende where emai.FK_entidade = emp.FK_fornecedor and ende.FK_entidade = emp.FK_fornecedor and fone.FK_entidade=emp.FK_fornecedor order by empresa asc"; 
		try {
			con = Conexao.getConexao();
			PreparedStatement preparador = con.prepareStatement(sql);
			ResultSet rs = preparador.executeQuery();
			while (rs.next()) {
				Paciente emps = new Paciente();
				emps.setFK_fornecedor(rs.getInt("FK_fornecedor"));
				emps.setNumero_fornecedor(rs.getString("numero_fornecedor"));
				emps.setNomefornecedor(rs.getString("empresa"));
				emps.setNumero_doalvara(rs.getString("numero_doalvara"));
				emps.setNif(rs.getString("nif"));
				emps.setNota(rs.getString("nota"));
				emps.setEmailemp(rs.getString("email"));
				emps.setPaginaWeb(rs.getString("paginaWeb"));
				emps.setTelefoneemp(rs.getLong("telefone"));
				emps.setEnderecoemp(rs.getString("endereco"));
				lista.add(emps);
			}
			preparador.close();
			con.close();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return lista;
	}

	
	
	public List<Paciente> buscarEmpPesquisa(String termo) {
		List<Paciente> lista = new ArrayList<Paciente>();
		String sql = "select * from tblfornecedor where empresa like '%"
				+ termo + "%' or numero_doalvara like '%"
				+ termo;
		try {
			con = Conexao.getConexao();
			PreparedStatement preparador = con.prepareStatement(sql);
			ResultSet rs = preparador.executeQuery();
			while (rs.next()) {
				Paciente fun = new Paciente();
				fun.setFK_fornecedor(rs.getInt("FK_fornecedor"));
				fun.setNumero_fornecedor(rs.getString("numero_fornecedor"));
				fun.setNomefornecedor(rs.getString("empresa"));
				fun.setNumero_doalvara(rs.getString("numero_doalvara"));
				fun.setTelefone(rs.getLong("nif"));
				fun.setNif(rs.getString("nota"));
				fun.setPaginaWeb(rs.getString("paginaWeb"));
				lista.add(fun);
			}
			preparador.close();
			con.close();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return lista;
	}
	
	public List<Paciente> buscarEmpPesqPorId(int numEmp) {
		List<Paciente> lista = new ArrayList<Paciente>();
		String sql = "select * from tblfornecedor where FK_fornecedor="+numEmp;
		try {
			con = Conexao.getConexao();
			PreparedStatement preparador = con.prepareStatement(sql);
			ResultSet rs = preparador.executeQuery();
			while (rs.next()) {
				Paciente fun = new Paciente();
				fun.setFK_fornecedor(rs.getInt("FK_fornecedor"));
				fun.setNumero_fornecedor(rs.getString("numero_fornecedor"));
				fun.setNomefornecedor(rs.getString("empresa"));
				fun.setNumero_doalvara(rs.getString("numero_doalvara"));
				fun.setTelefone(rs.getLong("nif"));
				fun.setNif(rs.getString("nota"));
				fun.setPaginaWeb(rs.getString("paginaWeb"));
				lista.add(fun);
			}
			preparador.close();
			con.close();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return lista;
	}


	public List<Paciente> getEmpresa(String termo) {
		List<Paciente> ls = new ArrayList<Paciente>();
		String sql = "SELECT * from tblfornecedor where empresa Like ? or numero_fornecedor like ?";
		try {
			con = Conexao.getConexao();
			PreparedStatement preparador = con.prepareStatement(sql);
			preparador.setString(1, termo);
			preparador.setString(2, termo);
			ResultSet rs = preparador.executeQuery();
			while (rs.next()) {
				Paciente nv = new Paciente();
				nv.setFK_fornecedor(rs.getInt("FK_fornecedor"));
				nv.setNumero_fornecedor(rs.getString("numero_fornecedor"));
				nv.setNomefornecedor(rs.getString("empresa"));
				;
				ls.add(nv);
			}
			preparador.close();
		} catch (SQLException e) {
			e.printStackTrace();
		} catch (Exception e) {
			System.out.println(e.getMessage());
		} finally {
			try {
				con.close();
			} catch (SQLException ef) {
				System.out.println("Erro finalizar: " + ef);
			}
		}
		return ls;
	}

	public List<Paciente> getEmpresas(String termo) {
		List<Paciente> ls = new ArrayList<Paciente>();
		String sql = "SELECT * from tblfornecedor where empresa Like ?";
		try {
			con = Conexao.getConexao();
			PreparedStatement preparador = con.prepareStatement(sql);
			preparador.setString(1, termo + "%");
			ResultSet rs = preparador.executeQuery();
			while (rs.next()) {
				Paciente nv = new Paciente();
				nv.setNumero_fornecedor(rs.getString("numero_fornecedor"));
				nv.setNomefornecedor(rs.getString("empresa"));
				;
				ls.add(nv);
			}
			preparador.close();
		} catch (SQLException e) {
			e.printStackTrace();
		} catch (Exception e) {
			System.out.println(e.getMessage());
		} finally {
			try {
				con.close();
			} catch (SQLException ef) {
				System.out.println("Erro finalizar: " + ef);
			}
		}
		return ls;
	}

	public List<Paciente> buscarempresapesq() {
		List<Paciente> lista = new ArrayList<Paciente>();
		String sql = "SELECT * FROM vwfornecedor WHERE empresa LIKE=?";
		try {
			con = Conexao.getConexao();
			PreparedStatement preparador = con.prepareStatement(sql);
			ResultSet rs = preparador.executeQuery();
			while (rs.next()) {
				Paciente fun = new Paciente();
				fun.setFK_fornecedor(rs.getInt("FK_fornecedor"));
				fun.setNomefornecedor(rs.getString("empresa"));
				fun.setNumero_doalvara(rs.getString("numero_doalvara"));
				fun.setTelefone(rs.getLong("telefone"));
				fun.setNif(rs.getString("nif"));
				fun.setEmail(rs.getString("email"));
				lista.add(fun);
			}
			preparador.close();
			con.close();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return lista;
	}

	public List<Paciente> pesquisarempresa(String nome) {
		List<Paciente> lista = new ArrayList<Paciente>();
		String sql = "SELECT * FROM vwfornecedor WHERE empresa LIKE '" + nome
				+ "%'";
		try {
			con = Conexao.getConexao();
			PreparedStatement preparador = con.prepareStatement(sql);
			ResultSet rs = preparador.executeQuery();
			while (rs.next()) {
				Paciente pac = new Paciente();
				pac.setFK_fornecedor(rs.getInt("FK_fornecedor"));
				pac.setNomefornecedor(rs.getString("empresa"));
				pac.setNumero_doalvara(rs.getString("numero_doalvara"));
				pac.setTelefone(rs.getLong("telefone"));
				pac.setNif(rs.getString("nif"));
				pac.setEmail(rs.getString("email"));
				lista.add(pac);
			}
			preparador.close();
			con.close();
		} catch (SQLException e) {

			e.printStackTrace();
		}
		// System.out.print(lista.get(0).getNome());
		return lista;
	}

	public Paciente buscarempresas(int cod) {
		Paciente med = null;
		String sql = "select distinct * from tblfornecedor emp,tbltelefone fone, tblemail emai, tblendereco end where emai.FK_entidade=emp.FK_fornecedor and end.FK_entidade=emp.FK_fornecedor and fone.FK_entidade=emp.FK_fornecedor and FK_fornecedor = ?";

		try {
			con = Conexao.getConexao();
			PreparedStatement preparador = con.prepareStatement(sql);
			preparador.setInt(1, cod);
			ResultSet rs = preparador.executeQuery();
			if (rs.next()) {
				med = new Paciente();
				med.setFK_fornecedor(rs.getInt("FK_fornecedor"));
				med.setNomefornecedor(rs.getString("empresa"));
				med.setNumero_doalvara(rs.getString("numero_doalvara"));
				med.setTelefoneemp(rs.getLong("telefone"));
				med.setNif(rs.getString("nif"));
				med.setEmailemp(rs.getString("email"));
				med.setEnderecoemp(rs.getString("endereco"));
				med.setNota(rs.getString("nota"));
				med.setPaginaWeb(rs.getString("paginaWeb"));
				med.setFK_banco(rs.getInt("FK_banco"));
				med.setNumero_conta(rs.getString("numero_conta"));
			}
			preparador.close();
			con.close();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return med;
	}

	public Paciente buscarempresa(String termo) {
		Paciente med = null;
		String sql = "select distinct * from tblfornecedor emp,tbltelefone fone, tblemail emai, tblendereco end where emai.FK_entidade=emp.FK_fornecedor and end.FK_entidade=emp.FK_fornecedor and fone.FK_entidade=emp.FK_fornecedor and (empresa like  ? or numero_doalvara like  ?)";

		try {
			con = Conexao.getConexao();
			PreparedStatement preparador = con.prepareStatement(sql);
			preparador.setString(1, termo);
			preparador.setString(2, termo);
			ResultSet rs = preparador.executeQuery();
			if (rs.next()) {
				med = new Paciente();
				med.setFK_fornecedor(rs.getInt("FK_fornecedor"));
				med.setNomefornecedor(rs.getString("empresa"));
				med.setNumero_doalvara(rs.getString("numero_doalvara"));
				med.setTelefoneemp(rs.getLong("telefone"));
				med.setNif(rs.getString("nif"));
				med.setEmailemp(rs.getString("email"));
				med.setEnderecoemp(rs.getString("endereco"));
				med.setNota(rs.getString("nota"));
				med.setPaginaWeb(rs.getString("paginaWeb"));

			}
			preparador.close();
			con.close();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return med;
	}

	
	public boolean inserirNovoFonercedor(Paciente emp) {
		boolean verificaErro = false;
		Fornecedor fornec = new Fornecedor();
		String sql = "INSERT INTO tblfornecedor (FK_fornecedor,numero_fornecedor,empresa,numero_doalvara,nif,nota,paginaWeb,FK_banco,numero_conta) VALUES (?,?,?,?,?,?,?,?,?)";
		try {

			con = Conexao.getConexao();
			PreparedStatement ent = con.prepareStatement(sql);
			ent.setInt(1, emp.getFK_fornecedor());
			ent.setString(2, gerarNumeroForn());
			ent.setString(3, emp.getNomefornecedor());
			ent.setString(4, emp.getNumero_doalvara());
			ent.setString(5, emp.getNif());
			ent.setString(6, emp.getNota());
			ent.setString(7, emp.getPaginaWeb());
			ent.setInt(8, emp.getFK_banco());
			ent.setString(9, emp.getNumero_conta());
			ent.execute();

			sql = "INSERT into tbltelefone (fk_entidade,telefone) VALUES (?,?)";
			ent = con.prepareStatement(sql);
			ent.setInt(1, emp.getFK_fornecedor());
			ent.setLong(2, emp.getTelefoneemp());
			ent.execute();

			sql = "INSERT INTO TBLENDERECO (fk_entidade,endereco) VALUES (?,?)";
			ent = con.prepareStatement(sql);
			ent.setInt(1, emp.getFK_fornecedor());
			ent.setString(2, emp.getEnderecoemp());
			ent.execute();

			sql = "INSERT INTO TBLEMAIL (fk_entidade,email) VALUES (?,?)";
			ent = con.prepareStatement(sql);
			ent.setInt(1, emp.getFK_fornecedor());
			ent.setString(2, emp.getEmailemp());
			ent.execute();
			ent.close();
			con.close();
			System.out.println("Cadastro de sucesso...tblfornecedor");
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			verificaErro = true;
			e.printStackTrace();
		}
		if (!verificaErro)
			return true;

		return false;
	}

	// Eliminar representante
	public boolean eliminarReprEmp(int id) {
		String sql = "DELETE FROM tblcontacto_fornecedor WHERE FK_fornecedor=?";
		boolean verElim=true;
		
		try {
			con = Conexao.getConexao();
			PreparedStatement preparador = con.prepareStatement(sql);
			preparador.setInt(1, id);
			preparador.execute();
			preparador.close();
			con.close();
			System.out.println("Exclusao com sucesso...representante");
		} catch (SQLException e) {
			verElim=false;
			e.printStackTrace();
		}
		if(verElim)
			return true;
		return false;
	}

	// Eliminar fornecedor
	public boolean eliminarempresa(int id) {
		String sql = "DELETE FROM tblfornecedor WHERE FK_fornecedor=?";
		boolean verElim=true;
		try {
			con = Conexao.getConexao();
			PreparedStatement preparador = con.prepareStatement(sql);
			preparador.setInt(1, id);
			preparador.execute();
			preparador.close();
			con.close();
			System.out.println("Exclusao com sucesso...tblfornecedor");
		} catch (SQLException e) {
			verElim=false;
			e.printStackTrace();
		}
		
		if(verElim)
			return true;
		return false;
	}

	public int ultimoFornecedorInserido() throws SQLException {

		con = Conexao.getConexao();
		int seqnciaNumeroForn = 0;
		String sql = "select MAX(FK_fornecedor) from tblfornecedor";
		try {
			PreparedStatement stm = con.prepareStatement(sql);
			ResultSet recebeSeqncia = stm.executeQuery();
			if (recebeSeqncia.next()) {
				seqnciaNumeroForn = recebeSeqncia.getInt(1);

			}
		} catch (Exception e) {
			System.out.println("Sequencia de conta falhou");
			System.out.println(e.getMessage());
		}

		return seqnciaNumeroForn;
	}

	public int ultimaEntidadeInserida() throws SQLException {

		con = Conexao.getConexao();
		int seqnciaNumeroForn = 0;
		String sql = "select MAX(id_entidade) from tblentidade";
		try {
			PreparedStatement stm = con.prepareStatement(sql);
			ResultSet recebeSeqncia = stm.executeQuery();
			if (recebeSeqncia.next()) {
				seqnciaNumeroForn = recebeSeqncia.getInt(1);

			}
		} catch (Exception e) {
			System.out.println("Sequencia de conta falhou");
			System.out.println(e.getMessage());
		}

		return seqnciaNumeroForn;
	}

	public void inserirRepEmpresa(Paciente emp) {

		// Para o representante
		try {
			con = Conexao.getConexao();
			String sqlrep = "insert into tblcontacto_fornecedor (primeiro_nome, ultimo_nome , telefone ,email, notas_do_cont_forn,FK_cargo, FK_fornecedor, endereco) VALUES(?, ?, ? , ?, ?,?, ?, ?) ";
			PreparedStatement rep = con.prepareStatement(sqlrep);
			rep.setString(1, emp.getPrimeiroNome());
			rep.setString(2, emp.getUltimoNome());
			rep.setLong(3, emp.getTelefoneRep());
			rep.setString(4, emp.getEmailRep());
			rep.setString(5, emp.getNotaRep());
			rep.setInt(6, emp.getCE_cargo());
			rep.setInt(7, ultimoFornecedorInserido());
			rep.setString(8, emp.getEnderecoRep());
			rep.execute();

			rep.close();
			con.close();
			System.out
					.println("Alteracao efectuada com sucesso...tblfornecedor");
		} catch (SQLException e) {

			e.printStackTrace();
		}
	}
	
	//Para editar um representate da empresa
	public void modificarFonercedor (Paciente emp)
	{
		
		String sql = "UPDATE tblfornecedor SET empresa=?,numero_doalvara=?,nif=?,nota=?,paginaWeb=?, FK_banco =?, numero_conta =? WHERE  FK_fornecedor = ? ";
		try {
			con = Conexao.getConexao();
			 PreparedStatement ps = con.prepareStatement(sql);
			 ps.setString(1, emp.getNomefornecedor());
			 ps.setString(2, emp.getNumero_doalvara());
			 ps.setString(3, emp.getNif());
			 ps.setString(4, emp.getNota());
			 ps.setString(5, emp.getPaginaWeb());
			 ps.setInt(6, emp.getFK_banco());
			 ps.setString(7, emp.getNumero_conta());
			 ps.setInt(8, emp.getCE_fornecedor());
			 ps.execute();

			 sql = "UPDATE TBLTELEFONE SET telefone=? WHERE fk_entidade =?";
			 ps = con.prepareStatement(sql);
			 ps.setLong(1, emp.getTelefoneemp()); 
			 ps.setInt(2, emp.getCE_fornecedor());
			 ps.execute();
			 
			 sql = "UPDATE TBLENDERECO SET endereco =? WHERE fk_entidade=?";
			 ps = con.prepareStatement(sql);
			 ps.setString(1, emp.getEnderecoemp()); 
			 ps.setInt(2, emp.getCE_fornecedor());
			 ps.execute();
			 
			 sql = "UPDATE TBLEMAIL SET email=? WHERE fk_entidade=?";
			 ps = con.prepareStatement(sql);
			 ps.setString(1, emp.getEmailemp()); 
			 ps.setInt(2, emp.getCE_fornecedor());
			 ps.execute();
			 
			 ps.close();
			 con.close();
			 System.out.println("Alteracao efectuada com sucesso...tblfornecedor"); 
		} catch (SQLException e) {

			e.printStackTrace();
		}
	}

	// Para editar uma empresa
	public void editarRepEmpresas(Paciente emp) {

		String sql = "UPDATE tblcontacto_fornecedor SET primeiro_nome=?, ultimo_nome=?, telefone=? ,email=?, notas_do_cont_forn=?,FK_cargo=?, endereco= ? WHERE  FK_fornecedor = ?  ";
		try {
			con = Conexao.getConexao();
			PreparedStatement ps = con.prepareStatement(sql);
			ps.setString(1, emp.getPrimeiroNome());
			ps.setString(2, emp.getUltimoNome());
			ps.setLong(3, emp.getTelefoneRep());
			ps.setString(4, emp.getEmailRep());
			ps.setString(5, emp.getNotaRep());
			ps.setInt(6, emp.getCE_cargo());
			ps.setString(7, emp.getEnderecoRep());
			ps.setInt(8, emp.getCE_fornecedor());
			ps.execute();
			ps.close();
			con.close();
			System.out.println("Alteracao efectuada com sucesso...contacto fornecedor"+ emp.getCE_fornecedor());
		} catch (SQLException e) {

			e.printStackTrace();
		}
	}

}
