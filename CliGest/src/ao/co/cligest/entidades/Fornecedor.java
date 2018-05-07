package ao.co.cligest.entidades;

import java.io.Serializable;
import java.sql.Time;
import java.util.Calendar;
import java.util.Date;

public class Fornecedor implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private int FK_forncdor;
	private String n_forncdor;
	private String empresa;
	private String n_alvara;
	private String nif;
	private String nota;
	private Long telefone;
	private int FK_funcionario;
	private String endereco;
	private String editfone;
	private double totalDivida;
	private int FK_conta;
	private String conta;
	private int FK_paga_fornecedor;
	private String beneficiario;
	private String referencia;
	
	private int FK_mdpagamento;
	private double saldo;
	private int fk_facturas_encomenda;
	
	private String numCheque;
	private String agencia;
	private String rota;
	private String iban;
	private String swift;
	private Calendar dataTransf;
	
	
	// ------------------------------------ Cobertura
	private int id_tp_cobertura;
	private String tipo_cobertura;
	private int FK_benef_saude;
	private int FK_tipo_cobertura;
	private String nome_do_plano;
	private double teto;
	private String descricao;
	private Date data;
	private Time hora;
	private String email;
	private double valor_acrescido;
	private double coparticipacao;
	private double desconto;

	// ------------------------------------ fim Cobertura
	public int getFK_forncdor() {
		return FK_forncdor;
	}
	public void setFK_forncdor(int fK_forncdor) {
		this.FK_forncdor = fK_forncdor;
	}
	public String getN_forncdor() {
		return n_forncdor;
	}
	public void setN_forncdor(String n_forncdor) {
		this.n_forncdor = n_forncdor;
	}
	public String getEmpresa() {
		return empresa;
	}
	public void setEmpresa(String empresa) {
		this.empresa = empresa;
	}
	public String getN_alvara() {
		return n_alvara;
	}
	public void setN_alvara(String n_alvara) {
		this.n_alvara = n_alvara;
	}
	public String getNif() {
		return nif;
	}
	public void setNif(String nif) {
		this.nif = nif;
	}
	public String getNota() {
		return nota;
	}
	public void setNota(String nota) {
		this.nota = nota;
	}
	public Long getTelefone() {
		return telefone;
	}
	public void setTelefone(Long telefone) {
		this.telefone = telefone;
	}
	public int getId_tp_cobertura() {
		return id_tp_cobertura;
	}
	public void setId_tp_cobertura(int id_tp_cobertura) {
		this.id_tp_cobertura = id_tp_cobertura;
	}
	public String getTipo_cobertura() {
		return tipo_cobertura;
	}
	public void setTipo_cobertura(String tipo_cobertura) {
		this.tipo_cobertura = tipo_cobertura;
	}
	public int getFK_benef_saude() {
		return FK_benef_saude;
	}
	public void setFK_benef_saude(int fK_benef_saude) {
		FK_benef_saude = fK_benef_saude;
	}
	public int getFK_tipo_cobertura() {
		return FK_tipo_cobertura;
	}
	public void setFK_tipo_cobertura(int fK_tipo_cobertura) {
		FK_tipo_cobertura = fK_tipo_cobertura;
	}
	public String getNome_do_plano() {
		return nome_do_plano;
	}
	public void setNome_do_plano(String nome_do_plano) {
		this.nome_do_plano = nome_do_plano;
	}
	public double getTeto() {
		return teto;
	}
	public void setTeto(double teto) {
		this.teto = teto;
	}
	public String getDescricao() {
		return descricao;
	}
	public void setDescricao(String descricao) {
		this.descricao = descricao;
	}
	public Date getData() {
		return data;
	}
	public void setData(Date data) {
		this.data = data;
	}
	public Time getHora() {
		return hora;
	}
	public void setHora(Time hora) {
		this.hora = hora;
	}
	public int getFK_funcionario() {
		return FK_funcionario;
	}
	public void setFK_funcionario(int fK_funcionario) {
		FK_funcionario = fK_funcionario;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}

	public double getValor_acrescido() {
		return valor_acrescido;
	}

	public void setValor_acrescido(double valor_acrescido) {
		this.valor_acrescido = valor_acrescido;
	}

	public double getCoparticipacao() {
		return coparticipacao;
	}

	public void setCoparticipacao(double coparticipacao) {
		this.coparticipacao = coparticipacao;
	}

	public double getDesconto() {
		return desconto;
	}

	public void setDesconto(double desconto) {
		this.desconto = desconto;
	}

	 
		public String getEditfone() {
			return editfone;
		}
		public void setEditfone(long editfone) {
			// 244924044145
			if(editfone > 1){
			String fonetmp = "" + editfone;
			try {
				String codp = fonetmp.substring(0, 3);
				String num1 = fonetmp.substring(3, 6);
				String num2 = fonetmp.substring(6, 9);
				String num3 = fonetmp.substring(9);
				String fonet = codp + " " + num1 + "-" + num2 + "-" + num3;
				this.editfone = fonet;
			} catch (NumberFormatException er) {
				System.out.print(er);
			}
			}
			else
				this.editfone = "";
		}
		public String getEndereco() {
			return endereco;
		}
		public void setEndereco(String endereco) {
			this.endereco = endereco;
		}
		public double getTotalDivida() {
			return totalDivida;
		}
		public void setTotalDivida(double totalDivida) {
			this.totalDivida = totalDivida;
		}
		public int getFK_conta() {
			return FK_conta;
		}
		public void setFK_conta(int fK_conta) {
			FK_conta = fK_conta;
		}
		public String getConta() {
			return conta;
		}
		public void setConta(String conta) {
			this.conta = conta;
		}
		public int getFK_mdpagamento() {
			return FK_mdpagamento;
		}
		public void setFK_mdpagamento(int fK_mdpagamento) {
			FK_mdpagamento = fK_mdpagamento;
		}
		public int getFK_paga_fornecedor() {
			return FK_paga_fornecedor;
		}
		public void setFK_paga_fornecedor(int fK_paga_fornecedor) {
			FK_paga_fornecedor = fK_paga_fornecedor;
		}
		public String getBeneficiario() {
			return beneficiario;
		}
		public void setBeneficiario(String beneficiario) {
			this.beneficiario = beneficiario;
		}
		public double getSaldo() {
			return saldo;
		}
		public void setSaldo(double saldo) {
			this.saldo = saldo;
		}
		public int getFk_facturas_encomenda() {
			return fk_facturas_encomenda;
		}
		public void setFk_facturas_encomenda(int fk_facturas_encomenda) {
			this.fk_facturas_encomenda = fk_facturas_encomenda;
		}
		public String getReferencia() {
			return referencia;
		}
		public void setReferencia(String referencia) {
			this.referencia = referencia;
		}
		public String getNumCheque() {
			return numCheque;
		}
		public void setNumCheque(String numCheque) {
			this.numCheque = numCheque;
		}
		public String getAgencia() {
			return agencia;
		}
		public void setAgencia(String agencia) {
			this.agencia = agencia;
		}
		public String getRota() {
			return rota;
		}
		public void setRota(String rota) {
			this.rota = rota;
		}
		public String getIban() {
			return iban;
		}
		public void setIban(String iban) {
			this.iban = iban;
		}
		public String getSwift() {
			return swift;
		}
		public void setSwift(String swift) {
			this.swift = swift;
		}
		public Calendar getDataTransf() {
			return dataTransf;
		}
		public void setDataTransf(Calendar dataTransf) {
			this.dataTransf = dataTransf;
		}
}
