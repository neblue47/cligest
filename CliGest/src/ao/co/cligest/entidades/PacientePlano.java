package ao.co.cligest.entidades;

import java.sql.Date;
import java.util.Calendar;

@SuppressWarnings("serial")
public class PacientePlano extends Paciente {

	private int id_plano_dsaude;
	private String tipoDoc;
	private String numero_dcartao;
	private String num_apolice;
	private int FK_tipo_dcobertura;
	private int FK_fornecedor;
	private Calendar data_emissao;
	private Calendar data_dexpiracao;
	private String copiaCartao;
	private String nome_da_maquina;
	private boolean verbado;
	private int FK_parente;
	private int idade;
	
	private double valor_acrescido;
	private double coparticipacao;
	private double desconto;
	 
 
	 

	
	
	public PacientePlano(int FK_paciente,int FK_funcionario, String numero_dcartao, String num_apolice, int fK_tipo_dcobertura, int fK_fornecedor,Calendar data_emissao, Calendar data_dexpiracao, String copiaCartao, String nome_da_maquina,int fK_parente) {
		this.tipoDoc = tipoDoc;
		this.numero_dcartao = numero_dcartao;
		this.FK_tipo_dcobertura = fK_tipo_dcobertura;
		this.FK_fornecedor = fK_fornecedor;
		this.data_emissao = data_emissao;
		this.data_dexpiracao = data_dexpiracao;
		this.copiaCartao = copiaCartao;
		this.nome_da_maquina = nome_da_maquina;
		this.FK_parente = fK_parente;
		this.num_apolice = num_apolice;
		this.setFK_paciente(FK_paciente);
		this.setFK_funcionario(FK_funcionario);
	}

	public PacientePlano() {
		super();
		// TODO Auto-generated constructor stub
	}

	public int getId_plano_dsaude() {
		return id_plano_dsaude;
	}

	public void setId_plano_dsaude(int id_plano_dsaude) {
		this.id_plano_dsaude = id_plano_dsaude;
	}

	public String getNumero_dcartao() {
		return numero_dcartao;
	}

	public void setNumero_dcartao(String numero_dcartao) {
		this.numero_dcartao = numero_dcartao;
	}

	public int getFK_tipo_dcobertura() {
		return FK_tipo_dcobertura;
	}

	public void setFK_tipo_dcobertura(int fK_tipo_dcobertura) {
		FK_tipo_dcobertura = fK_tipo_dcobertura;
	}

	public int getFK_fornecedor() {
		return FK_fornecedor;
	}

	public void setFK_fornecedor(int fK_fornecedor) {
		FK_fornecedor = fK_fornecedor;
	}

	public Calendar getData_emissao() {
		return data_emissao;
	}

	public void setData_emissao(Calendar data_emissao) {
		this.data_emissao = data_emissao;
	}

	public Calendar getData_dexpiracao() {
		return data_dexpiracao;
	}

	public void setData_dexpiracao(Calendar data_dexpiracao) {
		this.data_dexpiracao = data_dexpiracao;
	}

	public String getCopiaCartao() {
		return copiaCartao;
	}

	public void setCopiaCartao(String copiaCartao) {
		this.copiaCartao = copiaCartao;
	}

	public int getIdade() {
		return idade;
	}

	public void setIdade(int idade) {
		this.idade = idade;
	}

	public String getNome_da_maquina() {
		return nome_da_maquina;
	}

	public void setNome_da_maquina(String nome_da_maquina) {
		this.nome_da_maquina = nome_da_maquina;
	}

	public int getFK_parente() {
		return FK_parente;
	}

	public void setFK_parente(int fK_parente) {
		FK_parente = fK_parente;
	}

	public String getTipoDoc() {
		return tipoDoc;
	}

	public void setTipoDoc(String tipoDoc) {
		this.tipoDoc = tipoDoc;
	}

	public String getNum_apolice() {
		return num_apolice;
	}

	public void setNum_apolice(String num_apolice) {
		this.num_apolice = num_apolice;
	}

	public boolean isVerbado() {
		return verbado;
	}

	public void setVerbado(boolean verbado) {
		this.verbado = verbado;
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

	 


	

	 
	
	
	
}
