package ao.co.cligest.negocio;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

import ao.co.cligest.contracts.IPacientePlano;
import ao.co.cligest.entidades.Fornecedor;
import ao.co.cligest.entidades.PacientePlano;
import ao.co.cligest.entidades.Plano;;

public class PacientePlanoDAO implements IPacientePlano {

	


	private Connection con;

	public List<PacientePlano> buscarPacientesPlano() {
		List<PacientePlano> lista = new ArrayList<PacientePlano>();
		String sql = "SELECT * FROM vwpacientessemplanodesaude limit 30 ";
		try {
			con = Conexao.getConexao();
			PreparedStatement ps = con.prepareStatement(sql);
			ResultSet rs = ps.executeQuery();
			while (rs.next()) {
				PacientePlano pp = new PacientePlano();
				pp.setFK_paciente(rs.getInt("entidade"));
				pp.setNumero_processo(rs.getString("numeroprocesso"));
				pp.setNomeCompleto(rs.getString("nomecompleto"));
				pp.setIdade(rs.getInt("idade"));
				Calendar data = Calendar.getInstance();
				data.setTime(rs.getDate("data_nascimento"));
				pp.setDataNasc(data);
				lista.add(pp);
			}
			ps.close();
			con.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return lista;
	}

	public List<PacientePlano> buscarPacientesPlano(String aux) {
		List<PacientePlano> lista = new ArrayList<PacientePlano>();
		String sql = "SELECT * FROM vwpacientessemplanodesaude where nomecompleto Like ? or nomecompleto Like ? or numeroprocesso = ?   ";
		try {
			con = Conexao.getConexao();
			PreparedStatement ps = con.prepareStatement(sql);
			ps.setString(1, aux.trim() + "%");
			ps.setString(2, aux.trim() );
			ps.setString(3, aux.trim());
			ResultSet rs = ps.executeQuery();
			while (rs.next()) {
				PacientePlano pp = new PacientePlano();
				pp.setFK_paciente(rs.getInt("entidade"));
				pp.setNumero_processo(rs.getString("numeroprocesso"));
				pp.setNomeCompleto(rs.getString("nomecompleto"));
				pp.setIdade(rs.getInt("idade"));
				Calendar data = Calendar.getInstance();
				data.setTime(rs.getDate("data_nascimento"));
				pp.setDataNasc(data);
				lista.add(pp);
			}
			ps.close();
			con.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return lista;
	}

	@Override
	public PacientePlano buscarPacienteDetalhe(int id) {
		String sql = "SELECT * FROM vwpacientessemplanodesaude where entidade = ? ";
		PacientePlano pp = new PacientePlano();
		try {
			con = Conexao.getConexao();
			PreparedStatement ps = con.prepareStatement(sql);
			ps.setInt(1, id);
			ResultSet rs = ps.executeQuery();
			if (rs.next()) {
				pp.setFK_paciente(rs.getInt("entidade"));
				pp.setNumero_processo(rs.getString("numeroprocesso"));
				pp.setNomeCompleto(rs.getString("nomecompleto"));
				pp.setNumero_doc(rs.getString("numero_documento"));
				pp.setTipoDoc(rs.getString("tipo_documento"));
				pp.setIdade(rs.getInt("idade"));
				Calendar data = Calendar.getInstance();
				data.setTime(rs.getDate("data_nascimento"));
				pp.setDataNasc(data);
			}
			ps.close();
			con.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return pp;
	}

	@Override
	public List<Fornecedor> buscarAsseguradoras() {
		List<Fornecedor> lista = new ArrayList<Fornecedor>();
		String sql = "SELECT * FROM vwseguradoras  ";
		try {
			con = Conexao.getConexao();
			PreparedStatement ps = con.prepareStatement(sql);
			ResultSet rs = ps.executeQuery();
			while (rs.next()) {
				Fornecedor f = new Fornecedor();
				f.setEmpresa(rs.getString("empresa"));
				f.setFK_forncdor(rs.getInt("fk_fornecedor"));
				lista.add(f);
			}
			ps.close();
			con.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return lista;
	}

	@Override
	public List<Plano> buscarPlanosSaude() {
		List<Plano> lista = new ArrayList<Plano>();
		String sql = "SELECT * FROM TBLTIPODECOBERTURA";
		try {
			con = Conexao.getConexao();
			PreparedStatement ps = con.prepareStatement(sql);
			ResultSet rs = ps.executeQuery();
			while (rs.next()) {
				Plano pn = new Plano();
				pn.setId_tipo_dcobertura(rs.getInt("id_tipo_dcobertura"));
				pn.setTipo_dcobertura(rs.getString("nome_do_plano"));
				lista.add(pn);
			}
			ps.close();
			con.close();
		} catch (Exception e) {
			e.printStackTrace();
		}

		return lista;
	}

	@Override
	public void novoSeguradoPlano(PacientePlano pl) {
		String sql = "INSERT INTO TBLPLANODESAUDE "
				+ "(numero_dcartao,FK_tipo_dcobertura,FK_fornecedor,data_emisao,data_dexpiracao,caminho_cartao,"
				+ "FK_paciente,FK_parente,data_dentrada,FK_funcionario,nome_da_maquina,num_apolice)"
				+ " VALUES (?,?,?,?,?,?,?,?,?,?,?,?)";
		try {
			con = Conexao.getConexao();
			PreparedStatement serv = con.prepareStatement(sql);
			serv.setString(1, pl.getNumero_dcartao());
			serv.setInt(2, pl.getFK_tipo_dcobertura());
			serv.setInt(3, pl.getFK_fornecedor());
			serv.setDate(4, (new java.sql.Date(pl.getData_emissao().getTimeInMillis())));
			serv.setDate(5, (new java.sql.Date(pl.getData_dexpiracao().getTimeInMillis())));
			serv.setString(6, pl.getCopiaCartao());
			serv.setInt(7, pl.getFK_paciente());
			serv.setInt(8, pl.getFK_parente());
			serv.setDate(9, new Formatando().data_registo());
			serv.setInt(10, pl.getFK_funcionario());
			serv.setString(11, pl.getNome_da_maquina());
			serv.setString(12, pl.getNum_apolice());
			serv.execute();
			serv.close();
			System.out.println("Cadastro com sucesso...TBLPLANODESAUDE");
		} catch (SQLException e) {
			e.printStackTrace();
		} catch (Exception e) {
			e.printStackTrace();
		}

	}

	public void EditnovoSeguradoPlano(PacientePlano pl) {
		String sql = "UPDATE TBLPLANODESAUDE SET "
				+ " numero_dcartao = ?,FK_tipo_dcobertura = ?,FK_fornecedor = ?,data_emisao = ?,data_dexpiracao = ?,caminho_cartao = ?,"
				+ " FK_paciente = ?,FK_parente = ?,data_dentrada = ?,FK_funcionario = ?,nome_da_maquina = ?,num_apolice = ? "
				+ " WHERE id_plano_dsaude = ?";
		try {
			con = Conexao.getConexao();
			PreparedStatement serv = con.prepareStatement(sql);
			serv.setString(1, pl.getNumero_dcartao());
			serv.setInt(2, pl.getFK_tipo_dcobertura());
			serv.setInt(3, pl.getFK_fornecedor());
			serv.setDate(4, (new java.sql.Date(pl.getData_emissao().getTimeInMillis())));
			serv.setDate(5, (new java.sql.Date(pl.getData_dexpiracao().getTimeInMillis())));
			serv.setString(6, pl.getCopiaCartao());
			serv.setInt(7, pl.getFK_paciente());
			serv.setInt(8, pl.getFK_parente());
			serv.setDate(9, new Formatando().data_registo());
			serv.setInt(10, pl.getFK_funcionario());
			serv.setString(11, pl.getNome_da_maquina());
			serv.setString(12, pl.getNum_apolice());
			serv.setInt(13, pl.getId_plano_dsaude());
			serv.execute();
			serv.close();
			System.out.println("Alterado com sucesso...TBLPLANODESAUDE");
		} catch (SQLException e) {
			e.printStackTrace();
		} catch (Exception e) {
			e.printStackTrace();
		}

	}

	@Override
	public List<PacientePlano> buscarAsseguradosPlano() {
		List<PacientePlano> lista = new ArrayList<PacientePlano>();
		String sql = "SELECT * FROM vwpacienteassegurado limit 30 ";
		try {
			con = Conexao.getConexao();
			PreparedStatement ps = con.prepareStatement(sql);
			ResultSet rs = ps.executeQuery();
			while (rs.next()) {
				PacientePlano pp = new PacientePlano();
				pp.setFK_paciente(rs.getInt("fk_paciente"));
				pp.setId_plano_dsaude(rs.getInt("id_plano_dsaude"));
				pp.setNumero_dcartao(rs.getString("numero_dcartao"));
				pp.setNum_apolice(rs.getString("num_apolice"));
				pp.setTotal_servico(rs.getInt("total"));
				pp.setNomeCompleto(rs.getString("nome") + " " + rs.getString("nome_meio") + " " + rs.getString("ultimo_nome"));
				Calendar data = Calendar.getInstance();
				data.setTime(rs.getDate("data_emisao"));
				pp.setData_emissao(data);
				data.setTime(rs.getDate("data_dexpiracao"));
				pp.setData_dexpiracao(data);
				lista.add(pp);
			}
			ps.close();
			con.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return lista;
	}

	@Override
	public List<PacientePlano> buscarAsseguradosPlano(String aux) {
		List<PacientePlano> lista = new ArrayList<PacientePlano>();
		String sql = "SELECT * FROM vwpacienteassegurado where numero_dcartao = ? or nome Like ?   or ultimo_nome like ?  ";
		try {
			
			con = Conexao.getConexao();
			PreparedStatement ps = con.prepareStatement(sql);
			ps.setString(1, aux);
			ps.setString(2,aux.split("" )[0]  + "%");
			ps.setString(3, "%" +aux );
			 
			ResultSet rs = ps.executeQuery();
			while (rs.next()) {
				PacientePlano pp = new PacientePlano();
				pp.setFK_paciente(rs.getInt("fk_paciente"));
				pp.setId_plano_dsaude(rs.getInt("id_plano_dsaude"));
				pp.setNumero_dcartao(rs.getString("numero_dcartao"));
				pp.setNum_apolice(rs.getString("num_apolice"));
				pp.setTotal_servico(rs.getInt("total"));
				pp.setNomeCompleto(
						rs.getString("nome") + " " + rs.getString("nome_meio") + " " + rs.getString("ultimo_nome"));
				Calendar data = Calendar.getInstance();
				data.setTime(rs.getDate("data_emisao"));
				pp.setData_emissao(data);
				data.setTime(rs.getDate("data_dexpiracao"));
				pp.setData_dexpiracao(data);
				lista.add(pp);
			}
			ps.close();
			con.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return lista;
	}

	public PacientePlano getAsseguradoPlano(int aux) {
		PacientePlano pp = new PacientePlano();

		String sql = "SELECT * FROM tblplanodesaude where id_plano_dsaude = ? ";
		try {
			con = Conexao.getConexao();
			PreparedStatement ps = con.prepareStatement(sql);
			ps.setInt(1, aux);
			ResultSet rs = ps.executeQuery();
			if (rs.next()) {
				pp.setId_plano_dsaude(rs.getInt("id_plano_dsaude"));
				pp.setNumero_dcartao(rs.getString("numero_dcartao"));
				pp.setFK_tipo_dcobertura(rs.getInt("FK_tipo_dcobertura"));
				pp.setFK_fornecedor(rs.getInt("FK_fornecedor"));
				pp.setFK_paciente(rs.getInt("fk_paciente"));
				pp.setFK_parente(rs.getInt("fk_parente"));
				pp.setCopiaCartao(rs.getString("caminho_cartao"));
				pp.setNum_apolice(rs.getString("num_apolice"));
				if (rs.getInt("fk_paciente") != rs.getInt("fk_parente"))
					pp.setVerbado(true);
				else
					pp.setVerbado(false);
				Calendar data = Calendar.getInstance();
				data.setTime(rs.getDate("data_emisao"));
				pp.setData_emissao(data);
				data.setTime(rs.getDate("data_dexpiracao"));
				pp.setData_dexpiracao(data);

			}
			ps.close();
			con.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return pp;
	}
	/*
	 * (non-Javadoc)
	 * @see ao.co.angotech.interfaces.IPacientePlano#getAsseguradoPlano(java.lang.String)
	 */
	public PacientePlano getAsseguradoPlano(String aux) {
		PacientePlano pp = new PacientePlano();

		String sql = "SELECT * FROM vwpacienteassegurado where num_apolice Like ? or numero_dcartao Like ? or fk_paciente = ?";
		try {
			con = Conexao.getConexao();
			PreparedStatement ps = con.prepareStatement(sql);
			ps.setString(1, aux);
			ps.setString(2, aux);
			ps.setString(3, aux);
			ResultSet rs = ps.executeQuery();
			if (rs.next()) {
				pp.setId_plano_dsaude(rs.getInt("id_plano_dsaude"));
				pp.setNome(rs.getString("nome"));
				pp.setApelido(rs.getString("ultimo_nome"));
				pp.setNumero_dcartao(rs.getString("numero_dcartao"));
				pp.setFK_tipo_dcobertura(rs.getInt("FK_tipo_dcobertura"));
				pp.setFK_fornecedor(rs.getInt("FK_fornecedor"));
				pp.setFK_paciente(rs.getInt("fk_paciente"));
				pp.setFK_parente(rs.getInt("fk_parente"));
				pp.setCopiaCartao(rs.getString("caminho_cartao"));
				pp.setNum_apolice(rs.getString("num_apolice"));
				if (rs.getInt("fk_paciente") != rs.getInt("fk_parente"))
					pp.setVerbado(true);
				else
					pp.setVerbado(false);
				Calendar data = Calendar.getInstance();
				data.setTime(rs.getDate("data_emisao"));
				pp.setData_emissao(data);
				data.setTime(rs.getDate("data_dexpiracao"));
				pp.setData_dexpiracao(data);

			}
			ps.close();
			con.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return pp;
	}

	@Override
	public List<Plano> buscarPlanosSaude(int assgEm) {
		List<Plano> lista = new ArrayList<Plano>();
		String sql = "SELECT * FROM TBLTIPODECOBERTURA WHERE FK_fornecedor = ?";
		try {
			con = Conexao.getConexao();
			PreparedStatement ps = con.prepareStatement(sql);
			ps.setInt(1, assgEm);
			ResultSet rs = ps.executeQuery();
			while (rs.next()) {
				Plano pn = new Plano();
				pn.setId_tipo_dcobertura(rs.getInt("id_tipo_dcobertura"));
				pn.setTipo_dcobertura(rs.getString("nome_do_plano"));
				lista.add(pn);
			}
			ps.close();
			con.close();
		} catch (Exception e) {
			e.printStackTrace();
		}

		return lista;
	}

	@Override
	public List<PacientePlano> buscarAsseguradosPlanoDetalhes(String aux) {
		List<PacientePlano> lista = new ArrayList<PacientePlano>();

		String sql = "SELECT * FROM vwpacienteassegurado_semgroup where fk_paciente = ?  ";
		try {
			con = Conexao.getConexao();
			PreparedStatement ps = con.prepareStatement(sql);
			ps.setString(1, aux);

			ResultSet rs = ps.executeQuery();
			while (rs.next()) {
				PacientePlano pp = new PacientePlano();
				pp.setFK_paciente(rs.getInt("fk_paciente"));
				pp.setId_plano_dsaude(rs.getInt("id_plano_dsaude"));
				pp.setNumero_dcartao(rs.getString("numero_dcartao"));
				pp.setNum_apolice(rs.getString("num_apolice"));
				pp.setFK_parente(rs.getInt("FK_parente"));
				pp.setNomeCompleto(
						rs.getString("nome") + " " + rs.getString("nome_meio") + " " + rs.getString("ultimo_nome"));
				pp.setNomep(rs.getString("parente_nome") + " " + rs.getString("parente_meio") + " "
						+ rs.getString("parente_ultimo"));
				pp.setEmpresa(rs.getString("empresa"));
				Calendar data = Calendar.getInstance();
				data.setTime(rs.getDate("data_emisao"));
				pp.setData_emissao(data);
				data.setTime(rs.getDate("data_dexpiracao"));
				pp.setData_dexpiracao(data);
				lista.add(pp);
			}
			ps.close();
			con.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return lista;
	}

	@Override
	public String getCopiaCartao(String aux) {
		String sql = "SELECT * FROM tblplanodesaude where id_plano_dsaude = ? ";
		try {
			con = Conexao.getConexao();
			PreparedStatement ps = con.prepareStatement(sql);
			ps.setInt(1, Integer.parseInt(aux));
			ResultSet rs = ps.executeQuery();
			if (rs.next())
				return rs.getString("caminho_cartao");

			ps.close();
			con.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return "";
	}

	public boolean checkTitularPlanos(String aux, List<PacientePlano> planos) {
		boolean result = false;
		try {
			int id = Integer.parseInt(aux);
			for (PacientePlano p : planos) {
				if (p.getFK_parente() != id) {
					// System.out.println("ENCONTRO: "+p.getFK_parente()+"
					// "+id);
					result = true;
				}

			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return result;
	}

	public void excluirPacienPlano(String aux) {
		String sql = "DELETE FROM tblplanodesaude where id_plano_dsaude = ? ";
		try {
			con = Conexao.getConexao();
			PreparedStatement ps = con.prepareStatement(sql);
			ps.setInt(1, Integer.parseInt(aux));
			ps.execute();

			ps.close();
			con.close();
		} catch (Exception e) {
			e.printStackTrace();
		}

	}

	 
	  boolean validarNovoPlano(PacientePlano pl) {
		boolean isValido = true;
		String sql = "SELECT * FROM tblplanodesaude WHERE numero_dcartao = ? AND FK_tipo_dcobertura = ? "
				+ "AND FK_fornecedor = ? AND data_emisao = ? AND data_dexpiracao = ? AND FK_paciente = ? "
				+ "AND num_apolice = ?";
		try {
			con = Conexao.getConexao();
			PreparedStatement ps = con.prepareStatement(sql);
			ps.setString(1, pl.getNumero_dcartao());
			ps.setInt(2, pl.getFK_tipo_dcobertura());
			ps.setInt(3, pl.getFK_fornecedor());
			ps.setDate(4, (new java.sql.Date(pl.getData_emissao().getTimeInMillis())));
			ps.setDate(5, (new java.sql.Date(pl.getData_dexpiracao().getTimeInMillis())));
			ps.setInt(6, pl.getFK_paciente());
			ps.setString(7, pl.getNum_apolice());
			ResultSet rs = ps.executeQuery();
			if(rs.next())
				isValido = false;
		} catch (Exception e) {
			e.printStackTrace();
		}
		return isValido;
	}

	 
	 boolean validarAverbadoPlano(PacientePlano pl) {
		boolean isValido = true;
		String sql = "SELECT * FROM tblplanodesaude WHERE id_plano_dsaude = ?  AND FK_paciente = ? AND num_apolice = ?";
		try {
			con = Conexao.getConexao();
			PreparedStatement ps = con.prepareStatement(sql);
			ps.setInt(1, pl.getId_plano_dsaude());
			ps.setInt(2, pl.getFK_paciente());
			ps.setString(3, pl.getNum_apolice());
			ResultSet rs = ps.executeQuery();
			if(rs.next())
				isValido = false;
		} catch (Exception e) {
			e.printStackTrace();
		}
		return isValido;
	}
	 
	 @Override
	public boolean isValidoPlano(PacientePlano pl) {
			boolean isValido;
			if(pl.getId_plano_dsaude() > 0)
				isValido = validarAverbadoPlano(pl);
			else
				isValido = validarNovoPlano(pl);
			
			return isValido;
		}
	 
	 public PacientePlano getPlanoBeneficioServico(int codp, int cods){
     	PacientePlano p = new PacientePlano();
     	String sql = "SELECT * from vwservicosnoplanopaciente where FK_paciente = ? and id_servico = ? ";
     	try {
				con = Conexao.getConexao();
				PreparedStatement ps = con.prepareStatement(sql);
				ps.setInt(1, codp);
				ps.setInt(2, cods);
				ResultSet rs = ps.executeQuery();
				if(rs.next())
				{
					p.setId_plano_dsaude(rs.getInt("id_plano_dsaude"));
					p.setNomefornecedor(rs.getString("empresa"));
					p.setFK_paciente(rs.getInt("FK_paciente"));
					p.setFK_fornecedor(rs.getInt("FK_fornecedor"));
					p.setNomePlano(rs.getString("nome_do_plano"));
					p.setFK_tipo_dcobertura(rs.getInt("FK_tipo_dcobertura"));
					p.setPreco(rs.getDouble("preco_acrescido"));
					p.setCoparticipacao(rs.getDouble("coparticipacao_akz"));
					p.setDesconto(rs.getDouble("desconto_akz"));
					p.setId_plano_dsaude(rs.getInt("id_plano_dsaude"));
					p.setId_servico(rs.getInt("id_servico"));
					
				}
			} catch (Exception e) {
				e.printStackTrace();
			}
     	return p;
     }
	 
	 public PacientePlano getPlanoBeneficioExame(int codp, int cods){
	     	PacientePlano p = new PacientePlano();
	     	System.out.println(codp + " " + cods);
	     	String sql = "SELECT * from vwservicosexamesnoplanopaciente where FK_paciente = ? and FK_servico_exame = ? ";
	     	try {
					con = Conexao.getConexao();
					PreparedStatement ps = con.prepareStatement(sql);
					ps.setInt(1, codp);
					ps.setInt(2, cods);
					ResultSet rs = ps.executeQuery();
					if(rs.next())
					{
						p.setId_plano_dsaude(rs.getInt("id_plano_dsaude"));
						p.setNomefornecedor(rs.getString("empresa"));
						p.setFK_paciente(rs.getInt("FK_paciente"));
						p.setFK_fornecedor(rs.getInt("FK_fornecedor"));
						p.setNomePlano(rs.getString("nome_do_plano"));
						p.setFK_tipo_dcobertura(rs.getInt("FK_tipo_dcobertura"));
						p.setPreco(rs.getDouble("preco_acrescido"));
						p.setCoparticipacao(rs.getDouble("coparticipacao_akz"));
						p.setDesconto(rs.getDouble("desconto_akz"));
						p.setId_plano_dsaude(rs.getInt("id_plano_dsaude"));
						p.setId_servico(rs.getInt("FK_servico_exame"));
						
					}
				} catch (Exception e) {
					e.printStackTrace();
				}
	     	return p;
	     }
	 
	 public PacientePlano getPlanoBeneficioProduto(int codp, int cods){
	     	PacientePlano p = new PacientePlano();
	     	String sql = "SELECT * from vwservicosprodutonoplanopaciente where FK_paciente = ? and FK_farmaco = ? ";
	     	try {
					con = Conexao.getConexao();
					PreparedStatement ps = con.prepareStatement(sql);
					ps.setInt(1, codp);
					ps.setInt(2, cods);
					ResultSet rs = ps.executeQuery();
					if(rs.next())
					{
						p.setId_plano_dsaude(rs.getInt("id_plano_dsaude"));
						p.setNomefornecedor(rs.getString("empresa"));
						p.setFK_paciente(rs.getInt("FK_paciente"));
						p.setFK_fornecedor(rs.getInt("FK_fornecedor"));
						p.setNomePlano(rs.getString("nome_do_plano"));
						p.setFK_tipo_dcobertura(rs.getInt("FK_tipo_dcobertura"));
						p.setPreco(rs.getDouble("preco_acrescido"));
						p.setCoparticipacao(rs.getDouble("coparticipacao_akz"));
						p.setDesconto(rs.getDouble("desconto_akz"));
						p.setId_servico(rs.getInt("FK_farmaco"));
					}
				} catch (Exception e) {
					e.printStackTrace();
				}
	     	return p;
	     }
	 
	 public PacientePlano getPlanoBeneficioConsulta(int codp, int cods){
	     	PacientePlano p = new PacientePlano();
	     	String sql = "SELECT * from vwservicosconsultanoplanopaciente where FK_paciente = ? and id_servico = ? ";
	     	try {
					con = Conexao.getConexao();
					PreparedStatement ps = con.prepareStatement(sql);
					ps.setInt(1, codp);
					ps.setInt(2, cods);
					ResultSet rs = ps.executeQuery();
					if(rs.next())
					{
						p.setId_plano_dsaude(rs.getInt("id_plano_dsaude"));
						//p.setNomefornecedor(rs.getString("empresa"));
						p.setFK_paciente(rs.getInt("FK_paciente"));
						p.setFK_fornecedor(rs.getInt("FK_fornecedor"));
						p.setNomePlano(rs.getString("nome_do_plano"));
						p.setFK_tipo_dcobertura(rs.getInt("FK_tipo_dcobertura"));
						p.setPreco(rs.getDouble("preco_acrescido"));
						 
						p.setCoparticipacao(rs.getDouble("coparticipacao_akz"));
						p.setDesconto(rs.getDouble("desconto_akz"));
						
					}
				} catch (Exception e) {
					e.printStackTrace();
				}
	     	return p;
	     }

	@Override
	public double creditoPaciente(int codp) {
		String sql = "SELECT * FROM tblcredito WHERE fk_paciente = ?";
		try {
			con = Conexao.getConexao();
			PreparedStatement ps = con.prepareStatement(sql);
			ps.setInt(1, codp);
			ResultSet rs = ps.executeQuery();
			if(rs.next())
				return rs.getDouble("credito");
						
		} catch (Exception e) {
			// TODO: handle exception
		}
		return 0;
	}

}
