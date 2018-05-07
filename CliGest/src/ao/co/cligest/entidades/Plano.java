package ao.co.cligest.entidades;

import java.io.Serializable;
import java.util.Date;

public class Plano implements Serializable {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private int id_plano_dsaude;
	private int FK_paciente;
	private int FK_fornecedor;
	private int FK_tipo_dcobertura;
	private String numero_dcartao;
	private Date data_emisao;
	private Date data_dexpiracao;
	private String caminho_do_arquivo;
	private java.sql.Date data;
	private int FK_funcionario;
//	----------------------- EMPPRESA
	private String num_fornecedor;
	private String empresa;
	private String numero_doalvara;
	private String nif;
	private String nota;
//	----------------------- FIM EMPRESA
//	------------------------ TIPO DE COBERTURA
	private int id_tipo_dcobertura;
	private String tipo_dcobertura;
	private int FK_beneficio;
//	------------------------ FIM TIPO DE COBERTURA
//	------------------------ DADOS DO PACIENTE
	private int FK_entidade;
	private String nome;
	private String nomem;
	private String apelido;
//	------------------------ FIM PACIENTE
	public int getId_plano_dsaude() {
		return id_plano_dsaude;
	}
	public void setId_plano_dsaude(int id_plano_dsaude) {
		this.id_plano_dsaude = id_plano_dsaude;
	}
	public int getFK_paciente() {
		return FK_paciente;
	}
	public void setFK_paciente(int fK_paciente) {
		FK_paciente = fK_paciente;
	}
	public int getFK_fornecedor() {
		return FK_fornecedor;
	}
	public void setFK_fornecedor(int fK_fornecedor) {
		FK_fornecedor = fK_fornecedor;
	}
	public int getFK_tipo_dcobertura() {
		return FK_tipo_dcobertura;
	}
	public void setFK_tipo_dcobertura(int fK_tipo_dcobertura) {
		FK_tipo_dcobertura = fK_tipo_dcobertura;
	}
	public String getNumero_dcartao() {
		return numero_dcartao;
	}
	public void setNumero_dcartao(String numero_dcartao) {
		this.numero_dcartao = numero_dcartao;
	}
	
	public String getCaminho_do_arquivo() {
		return caminho_do_arquivo;
	}
	public void setCaminho_do_arquivo(String caminho_do_arquivo) {
		this.caminho_do_arquivo = caminho_do_arquivo;
	}
	
	public int getFK_funcionario() {
		return FK_funcionario;
	}
	public void setFK_funcionario(int fK_funcionario) {
		FK_funcionario = fK_funcionario;
	}
	public Date getData_emisao() {
		return data_emisao;
	}
	public void setData_emisao(Date data_emisao) {
		this.data_emisao = data_emisao;
	}
	public Date getData_dexpiracao() {
		return data_dexpiracao;
	}
	public void setData_dexpiracao(Date data_dexpiracao) {
		this.data_dexpiracao = data_dexpiracao;
	}
public java.sql.Date getData() {
		return data;
	}
	public void setData(java.sql.Date data) {
		this.data = data;
	}
	//	-----------------------------------------------------
	public String getNum_fornecedor() {
		return num_fornecedor;
	}
	public void setNum_fornecedor(String num_fornecedor) {
		this.num_fornecedor = num_fornecedor;
	}
	public String getEmpresa() {
		return empresa;
	}
	public void setEmpresa(String empresa) {
		this.empresa = empresa;
	}
	public String getNumero_doalvara() {
		return numero_doalvara;
	}
	public void setNumero_doalvara(String numero_doalvara) {
		this.numero_doalvara = numero_doalvara;
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
//	-----------------------------------------------
	public int getId_tipo_dcobertura() {
		return id_tipo_dcobertura;
	}
	public void setId_tipo_dcobertura(int id_tipo_dcobertura) {
		this.id_tipo_dcobertura = id_tipo_dcobertura;
	}
	public String getTipo_dcobertura() {
		return tipo_dcobertura;
	}
	public void setTipo_dcobertura(String tipo_dcobertura) {
		this.tipo_dcobertura = tipo_dcobertura;
	}
	public int getFK_beneficio() {
		return FK_beneficio;
	}
	public void setFK_beneficio(int fK_beneficio) {
		FK_beneficio = fK_beneficio;
	}
	public java.sql.Date data_registo() {
		// TODO Auto-generated method stub
		return null;
	}
//	---------------------------------------------- Paciente
	public int getFK_entidade() {
		return FK_entidade;
	}
	public void setFK_entidade(int fK_entidade) {
		FK_entidade = fK_entidade;
	}
	public String getNome() {
		return nome;
	}
	public void setNome(String nome) {
		this.nome = nome;
	}
	public String getNomem() {
		return nomem;
	}
	public void setNomem(String nomem) {
		this.nomem = nomem;
	}
	public String getApelido() {
		return apelido;
	}
	public void setApelido(String apelido) {
		this.apelido = apelido;
	}
	
}
