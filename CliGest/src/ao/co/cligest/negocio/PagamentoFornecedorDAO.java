package ao.co.cligest.negocio;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

import ao.co.cligest.contracts.IPagamentoFornecedor;
import ao.co.cligest.entidades.Fornecedor;
import ao.co.cligest.entidades.Funcionario;
import ao.co.cligest.entidades.Produtos;

public class PagamentoFornecedorDAO implements IPagamentoFornecedor{
    private Connection con;
    private Formatando ft = new Formatando();
	@Override
	public void validaFuncionario(Funcionario f) {
		String sql = "INSERT INTO tblfuncionario_validador (fk_funcionario,fk_funcionario_validador) values (?,?) ";
		try {
			 con = Conexao.getConexao();
			 PreparedStatement ps = con.prepareStatement(sql);
			 ps.setInt(1, f.getFunPermitido_id());
			 ps.setInt(2, f.getFunResponsavel_id());
			 ps.execute();
			 ps.close();
			 con.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	@Override
	public List<Funcionario> buscarFuncionariosPermitidos() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Fornecedor detalhesFornecedorNaoPagos(int codf) {
		Fornecedor f = new Fornecedor();
		double divida = getTotalEmDivida(codf);
		String sql ="SELECT * FROM vwlistafornecedor_apagar WHERE FK_fornecedor = ?";
		try {
			 con = Conexao.getConexao();
			 PreparedStatement ps = con.prepareStatement(sql);
			 ps.setInt(1, codf);
			 ResultSet rs = ps.executeQuery();
			 if(rs.next()){
				 f.setEmpresa(rs.getString("empresa"));
				 f.setN_forncdor(rs.getString("primeiro_nome")+" "+rs.getString("ultimo_nome"));
				 f.setEditfone(rs.getLong("telefone"));
				 f.setEndereco(rs.getString("endereco"));
				 f.setFK_forncdor(rs.getInt("FK_fornecedor"));
				 f.setTotalDivida(divida);
				 f.setFK_conta(rs.getInt("FK_banco"));
				 f.setConta(rs.getString("numero_conta"));
			 }
		} catch (Exception e) {
			e.printStackTrace();
		}
		return f;
	}
	
	@Override
	public Fornecedor detalhesFornecedorPagos(int codf) {
		Fornecedor f = new Fornecedor();
		String sql ="SELECT * FROM vwlistafornecedor_pagas WHERE FK_fornecedor = ?";
		try {
			 con = Conexao.getConexao();
			 PreparedStatement ps = con.prepareStatement(sql);
			 ps.setInt(1, codf);
			 ResultSet rs = ps.executeQuery();
			 if(rs.next()){
				 f.setEmpresa(rs.getString("empresa"));
				 f.setN_forncdor(rs.getString("primeiro_nome")+" "+rs.getString("ultimo_nome"));
				 f.setEditfone(rs.getLong("telefone"));
				 f.setEndereco(rs.getString("endereco"));
				 f.setFK_forncdor(rs.getInt("FK_fornecedor"));
				 f.setTotalDivida(rs.getDouble("total_pago"));;
				 f.setFK_conta(rs.getInt("FK_banco"));
				 f.setConta(rs.getString("numero_conta"));
			 }
		} catch (Exception e) {
			e.printStackTrace();
		}
		return f;
	}

	@Override
	public boolean validarFuncionario(int codf) {
		String sql = "SELECT * FROM tblfuncionario_validador WHERE fk_funcionario = ? ";
		try {
			 con = Conexao.getConexao();
			 PreparedStatement ps = con.prepareStatement(sql);
			 ps.setInt(1, codf); 
			 ResultSet rs =  ps.executeQuery();
			 if(rs.next())
				 return true;
			 ps.close();
			 con.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return false;
	}

	@Override
	public void validarEncomenda(Produtos enc) {
		String sql = "INSERT INTO tblvalidarencomenda (FK_encomenda, FK_funcionario, ref_factura,dataEmissao) VALUES (?,?,?,?) ";
		try {
			 con = Conexao.getConexao();
			 PreparedStatement ps = con.prepareStatement(sql);
			 ps.setInt(1, enc.getFK_num_encomenda());
			 ps.setInt(2, enc.getFK_funcionario());
			 ps.setString(3, enc.getNumEncomenda());
			 ps.setDate(4, new Date(enc.getDataCriacao().getTimeInMillis()));
			 ps.execute();
			 ps.close();
			 con.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	@Override
	public List<Fornecedor> buscarFornecedoresPagar() {
		String sql = "SELECT * FROM vwlistafornecedor_apagar";
		List<Fornecedor> lista = new ArrayList<Fornecedor>();
		try {
			 con = Conexao.getConexao();
			 PreparedStatement ps = con.prepareStatement(sql);
			 ResultSet rs = ps.executeQuery();
			 while(rs.next()){
				 Fornecedor f = new Fornecedor();
				 f.setEmpresa(rs.getString("empresa"));
				 f.setFK_forncdor(rs.getInt("FK_fornecedor"));
				 lista.add(f);
			 }
		} catch (Exception e) {
			e.printStackTrace();
		}
		return lista;
	}

	@Override
	public List<Fornecedor> buscarFornecedores(String param) {
		String sql = "SELECT * FROM vwlistafornecedor_apagar where empresa like ?  ";
		List<Fornecedor> lista = new ArrayList<Fornecedor>();
		try {
			 con = Conexao.getConexao();
			 PreparedStatement ps = con.prepareStatement(sql);
			 ps.setString(1, "%"+param+"%");
			 ResultSet rs = ps.executeQuery();
			 while(rs.next()){
				 Fornecedor f = new Fornecedor();
				 f.setEmpresa(rs.getString("empresa"));
				 f.setFK_forncdor(rs.getInt("FK_fornecedor"));
				 lista.add(f);
			 }
		} catch (Exception e) {
			e.printStackTrace();
		}
		return lista;
	}

	@Override
	public List<Produtos> buscarEncomendaNaoPagasPorFornecedor(int codf) {
		String sql = "SELECT * FROM vwlistaencomendas_apagar where FK_fornecedor = ? and FK_encomenda not in (select fk_facturas_encomenda from tblencomenda_paga)";
		List<Produtos> lista = new ArrayList<Produtos>();
		try {
			 con = Conexao.getConexao();
			 PreparedStatement ps = con.prepareStatement(sql);
			 ps.setInt(1, codf);
			 ResultSet rs = ps.executeQuery();
			 while(rs.next())
			 {
				 Produtos p = new Produtos();
				 p.setNumEncomenda(rs.getString("num_daencomenda"));
				 p.setFK_num_encomenda(rs.getInt("FK_encomenda"));
				 p.setEncomendaValidada(validadaEncomenda(rs.getInt("FK_encomenda")));
				 Calendar data = Calendar.getInstance();
				 data.setTime(rs.getDate("data_decriacao"));
				 p.setDataCriacao(data);
				 p.setValor_total(rs.getDouble("total"));
				 lista.add(p);
			 }
			} catch (Exception e) {
				e.printStackTrace();
			}
		return lista;
	}

	@Override
	public List<Produtos> buscarEncomendaNaoPagasPorFornecedorValidada(int codf) {
		String sql = "SELECT * FROM vwlistaencomendas_apagar vw join tblvalidarencomenda ve on vw.FK_encomenda = ve.FK_encomenda where FK_fornecedor = ? "
				+ " and vw.FK_encomenda not in (select fk_facturas_encomenda from tblencomenda_paga) order by data_decriacao";
		List<Produtos> lista = new ArrayList<Produtos>();
		try {
			 con = Conexao.getConexao();
			 PreparedStatement ps = con.prepareStatement(sql);
			 ps.setInt(1, codf);
			 ResultSet rs = ps.executeQuery();
			 while(rs.next())
			 {
				 Produtos p = new Produtos();
				 p.setNumEncomenda(rs.getString("num_daencomenda"));
				 p.setFK_num_encomenda(rs.getInt("FK_encomenda"));
				 p.setEncomendaValidada(validadaEncomenda(rs.getInt("FK_encomenda")));
				 Calendar data = Calendar.getInstance();
				 data.setTime(rs.getDate("data_decriacao"));
				 p.setDataCriacao(data);
				 p.setValor_total(rs.getDouble("total"));
				 lista.add(p);
			 }
			} catch (Exception e) {
				e.printStackTrace();
			}
		return lista;
	}
	
	private int validadaEncomenda(int enc)
	{
		String sql = "SELECT * FROM tblvalidarencomenda where FK_encomenda = ?";
		try {
			 con = Conexao.getConexao();
			 PreparedStatement ps = con.prepareStatement(sql);
			 ps.setInt(1, enc);
			 ResultSet rs = ps.executeQuery();
			 if(rs.next())
				 return 1;
		} catch (Exception e) {
			e.printStackTrace();
		}
		return 0;
	}
	
	double getTotalEmDivida(int codf)
	{
		double soma = 0;
		String sql = "SELECT * FROM vwlistaencomendas_apagar where FK_fornecedor = ? and FK_encomenda not in (select fk_facturas_encomenda from tblencomenda_paga)";
		try {
			 con = Conexao.getConexao();
			 PreparedStatement ps = con.prepareStatement(sql);
			 ps.setInt(1, codf);
			 ResultSet rs = ps.executeQuery();
			 while(rs.next())
				 soma = soma + rs.getDouble("total");
		} catch (Exception e) {
			e.printStackTrace();
		}
		return soma;
	}
 
	protected boolean xequeFornecedor(int codf) {
		 
		String sql = "SELECT * FROM tblfornecedor_saldo a inner join tblpagar_fornecedore b  where a.FK_pagamento = b.id_paga_fornecedor and b.fk_fornecedor = ?";
		try {
			 con = Conexao.getConexao();
			 PreparedStatement ps = con.prepareStatement(sql);
			 ps.setInt(1, codf);
			 ResultSet rs = ps.executeQuery();
			 while(rs.next())
				return true;
		} catch (Exception e) {
			e.printStackTrace();
		}
		return false;
	}
	@Override
	public int facturaPagamentoEncomenda(Fornecedor f) {
		String sql = "INSERT INTO tblpagar_fornecedore (fk_fornecedor, fk_modo_pagamento, total_pago, descricao_pagamento, fk_funcionario) VALUES (?,?,?,?,?)";
		int ultimoId = 0;
		try {
			 con = Conexao.getConexao();
			 PreparedStatement ps = con.prepareStatement(sql);
			 ps.setInt(1, f.getFK_forncdor());
			 ps.setInt(2, f.getFK_mdpagamento());
			 ps.setDouble(3, f.getTotalDivida());
			 ps.setString(4, f.getDescricao());
			 ps.setInt(5, f.getFK_funcionario());
			 ps.execute();
			 ResultSet rs = ps.executeQuery("SELECT LAST_INSERT_ID()");
			    if(rs.next())
			      ultimoId = rs.getInt(1);
			 ps.close();
			 con.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return ultimoId;
	}

	@Override
	public void facturaEncomendaDeposito(Fornecedor f) {
		String  sql = "INSERT INTO tblpagar_fornecedor_deposito (fk_paga_fornecedor, fk_conta_banc_instituicao, numero_deposito, depositado_por, observacao) VALUES (?,?,?,?,?)";
		try {
			 con = Conexao.getConexao();
			 PreparedStatement ps = con.prepareStatement(sql);
			 ps.setInt(1, f.getFK_paga_fornecedor());
			 ps.setInt(2, f.getFK_conta());
			 ps.setString(3, f.getConta());
			 ps.setString(4, f.getBeneficiario());
			 ps.setString(5, f.getNota());
			 ps.execute();
			 ps.close();
			 con.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	@Override
	public void facturaEncomendaCredito(Fornecedor f) {
		String  sql = "INSERT INTO tblpagar_fornecedore_credito (fk_paga_fornecedor, fk_fornecedor_devedor,observacao_credito) VALUES (?,?,?)";
		try {
			 con = Conexao.getConexao();
			 PreparedStatement ps = con.prepareStatement(sql);
			 ps.setInt(1, f.getFK_paga_fornecedor());
			 ps.setInt(2, f.getFK_forncdor());
			 ps.setString(3, f.getNota());
			 ps.execute();
			 ps.close();
			 con.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	@Override
	public void actualizarSaldoFornecedor(Fornecedor f) {
		String  sql = "INSERT INTO tblfornecedor_saldo (saldo_positivo, motivo_saldo, fk_funcionario, fk_pagamento) VALUES (?,?,?,?)";
		try {
			 con = Conexao.getConexao();
			 PreparedStatement ps = con.prepareStatement(sql);
			 ps.setDouble(1, f.getSaldo());
			 ps.setString(2, "TESTE");
			 ps.setInt(3, f.getFK_funcionario());
			 ps.setInt(4, f.getFK_paga_fornecedor());
			 ps.execute();
			 ps.close();
			 con.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	@Override
	public void facturaEncomendaItem(Fornecedor f) {
		String  sql = "INSERT INTO tblencomenda_paga (fk_facturas_encomenda, fk_pagar_fornecedore) VALUES (?,?)";
		try {
			 con = Conexao.getConexao();
			 PreparedStatement ps = con.prepareStatement(sql);
			 ps.setInt(1, f.getFk_facturas_encomenda());
			 ps.setInt(2, f.getFK_paga_fornecedor());
			 ps.execute();
			 ps.close();
			 con.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

@Override
	public List<Fornecedor> buscarFornecedoresPagos() {
		String sql = "SELECT * FROM vwlistafornecedor_pagas ";
		List<Fornecedor> lista = new ArrayList<Fornecedor>();
		try {
			 con = Conexao.getConexao();
			 PreparedStatement ps = con.prepareStatement(sql);
			 ResultSet rs = ps.executeQuery();
			 while(rs.next()){
				 Fornecedor f = new Fornecedor();
				 f.setEmpresa(rs.getString("empresa"));
				 f.setFK_forncdor(rs.getInt("FK_fornecedor"));
				 lista.add(f);
			 }
		} catch (Exception e) {
			e.printStackTrace();
		}
		return lista;
	}

@Override
public double saldoFornecedor(int codf) {
	double soma = 0;
	String sql = "SELECT * FROM tblfornecedor_saldo a inner join tblpagar_fornecedore b  where a.FK_pagamento = b.id_paga_fornecedor and b.fk_fornecedor = ?";
	try {
		 con = Conexao.getConexao();
		 PreparedStatement ps = con.prepareStatement(sql);
		 ps.setInt(1, codf);
		 ResultSet rs = ps.executeQuery();
		 while(rs.next())
			 soma = soma + rs.getDouble("a.saldo_positivo");
	} catch (Exception e) {
		e.printStackTrace();
	}
	return soma;
}

@Override
public List<Produtos> buscarEncomendaPagasPorFornecedor(int codf) {
	String sql = "SELECT * FROM vwlistaencomendas_pagas where FK_fornecedor = ? ";
	List<Produtos> lista = new ArrayList<Produtos>();
	try {
		 con = Conexao.getConexao();
		 PreparedStatement ps = con.prepareStatement(sql);
		 ps.setInt(1, codf);
		 ResultSet rs = ps.executeQuery();
		 while(rs.next())
		 {
			 Produtos p = new Produtos();
			 p.setNumEncomenda(rs.getString("num_daencomenda"));
			 p.setFK_num_encomenda(rs.getInt("FK_encomenda"));
			 Calendar data = Calendar.getInstance();
			 data.setTime(rs.getDate("data_pagamento"));
			 p.setDataCriacao(data);
			 p.setValor_total(rs.getDouble("total"));
			 lista.add(p);
		 }
		} catch (Exception e) {
			e.printStackTrace();
		}
	return lista;
}

@Override
public List<Produtos> buscarEncomendaPagasPorFornecedor(int codf,String param) {
	String sql = "SELECT * FROM vwlistaencomendas_pagas where FK_fornecedor = ? and num_daencomenda LIKE ?";
	List<Produtos> lista = new ArrayList<Produtos>();
	try {
		 con = Conexao.getConexao();
		 PreparedStatement ps = con.prepareStatement(sql);
		 ps.setInt(1, codf);
		 ps.setString(2, "%"+param+"%");
		 ResultSet rs = ps.executeQuery();
		 while(rs.next())
		 {
			 Produtos p = new Produtos();
			 p.setNumEncomenda(rs.getString("num_daencomenda"));
			 p.setFK_num_encomenda(rs.getInt("FK_encomenda"));
			 Calendar data = Calendar.getInstance();
			 data.setTime(rs.getDate("data_pagamento"));
			 p.setDataCriacao(data);
			 p.setValor_total(rs.getDouble("total"));
			 lista.add(p);
		 }
		} catch (Exception e) {
			e.printStackTrace();
		}
	return lista;
}


@Override
public void eliminarValidador(int codi) {
	String sql = "DELETE FROM tblfuncionario_validador WHERE fk_funcionario = ?";
	try {
		 con = Conexao.getConexao();
		 PreparedStatement ps = con.prepareStatement(sql);
		 ps.setInt(1, codi);
		 ps.execute();
	} catch (Exception e) {
		e.printStackTrace();
	}
}

@Override
public void facturaEncomendaTransferencia(Fornecedor f) {
	String  sql = "INSERT INTO tblpagar_fornecedor_transferencia (fk_paga_fornecedor, fk_banco_instituicao, conta_destinatario_transferencia, iban_transferencai,swift_transferencia,referencia_transferencia,data_transferencia) VALUES (?,?,?,?,?,?,?)";
	try {
		 con = Conexao.getConexao();
		 PreparedStatement ps = con.prepareStatement(sql);
		 ps.setInt(1, f.getFK_paga_fornecedor());
		 ps.setInt(2, f.getFK_conta());
		 ps.setString(3, f.getConta());
		 ps.setString(4, f.getIban());
		 ps.setString(5, f.getSwift());
		 ps.setString(6, f.getReferencia());
		 ps.setDate(7, new java.sql.Date(f.getDataTransf().getTimeInMillis()));
		 ps.execute();
		 ps.close();
		 con.close();
	} catch (Exception e) {
		e.printStackTrace();
	}
}

@Override
public void facturaEncomendaCheque(Fornecedor f) {
	String  sql = "INSERT INTO tblpagar_fornecedor_cheque (fk_paga_fornecedor, numero_cheque, agencia_cheque, rota_cheque,fk_conta_banc_instituicao) VALUES (?,?,?,?,?)";
	try {
		 con = Conexao.getConexao();
		 PreparedStatement ps = con.prepareStatement(sql);
		 ps.setInt(1, f.getFK_paga_fornecedor());
		 ps.setString(2, f.getNumCheque());
		 ps.setString(3, f.getAgencia());
		 ps.setString(4, f.getRota());
		 ps.setInt(5, f.getFK_conta());
		 ps.execute();
		 ps.close();
		 con.close();
	} catch (Exception e) {
		e.printStackTrace();
	}
}

@Override
public void facturaEncomendaOrdemSaque(Fornecedor f) {
	String  sql = "INSERT INTO tblpagar_fornecedor_ordem_saque (fk_pagar_fornecedor, referencia_ordem_saque, observacao_ordem_saque, fk_conta_banc_instituicao) VALUES (?,?,?,?)";
	try {
		 con = Conexao.getConexao();
		 PreparedStatement ps = con.prepareStatement(sql);
		 ps.setInt(1, f.getFK_paga_fornecedor());
		 ps.setString(2, f.getReferencia());
		 ps.setString(3, f.getNota());
		 ps.setInt(4, f.getFK_conta());
		 ps.execute();
		 ps.close();
		 con.close();
	} catch (Exception e) {
		e.printStackTrace();
	}
}
}
