package ao.co.cligest.entidades;

import java.io.Serializable;
import java.util.Calendar;
/**
 * 
 * @author ARIDJA ANDRE
 * @manutencao ARIDJA ANDRE, NELSON DIWIDI
 *
 */

public class Instituicao implements Serializable{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private int id_instituicao;
	private int FK_pais;
	private int FK_provincia;
	private int FK_municipio;
	private int FK_distrito;
	private String nome_registo;
	private String nome_comercial;
	private String rua;
	private String bairro;
	private String site;
	private String email;
	private int numero_registo;
	private String nif;
	private Calendar data_fundacao;
	private String slogan;
	private String logo;
	private int num_funcionario;
	private String numContribuente;
	//tblTelefone
	 private Long telefone;
	 private Long telefones;
	 
	public Long getTelefone() {
		return telefone;
	}
	public void setTelefone(Long telefone) {
		this.telefone = telefone;
	}
	public Long getTelefones() {
		return telefones;
	}
	public void setTelefones(Long telefones) {
		this.telefones = telefones;
	}
	// tblnossahistoria
	
	private String historia;
	
	public String getHistoria() {
		return historia;
	}
	public void setHistoria(String historia) {
		this.historia = historia;
	}
	
	//tblMissao
	private String missao;
	private String visao;
	private String valor;
	
	public String getMissao() {
		return missao;
	}
	public void setMissao(String missao) {
		this.missao = missao;
	}
	public String getVisao() {
		return visao;
	}
	public void setVisao(String visao) {
		this.visao = visao;
	}
	public String getValor() {
		return valor;
	}
	public void setValor(String valor) {
		this.valor = valor;
	}
	
	//tblSumarioExecutivo
	private String sumario_executivo;
	
	public String getSumario_executivo() {
		return sumario_executivo;
	}
	public void setSumario_executivo(String sumario_executivo) {
		this.sumario_executivo = sumario_executivo;
	}
	
	// tblestruturaorganizacional
	private String estrutura_org;
	
	public String getEstrutura_org() {
		return estrutura_org;
	}
	public void setEstrutura_org(String estrutura_org) {
		this.estrutura_org = estrutura_org;
	}
	
	// tblquemsomos
	private String quem_somos;
	
	public String getQuem_somos() {
		return quem_somos;
	}
	public void setQuem_somos(String quem_somos) {
		this.quem_somos = quem_somos;
	}
	
	//tblmilstone
	private int id_milestone;
	private String milestone;
	private String responsavel;
	private String detalhe;
	private Calendar data;
	
	public int getId_milestone() {
		return id_milestone;
	}
	public void setId_milestone(int id_milestone) {
		this.id_milestone = id_milestone;
	}
	public String getMilestone() {
		return milestone;
	}
	public void setMilestone(String milestone) {
		this.milestone = milestone;
	}
	public String getResponsavel() {
		return responsavel;
	}
	public void setResponsavel(String responsavel) {
		this.responsavel = responsavel;
	}
	public String getDetalhe() {
		return detalhe;
	}
	public void setDetalhe(String detalhe) {
		this.detalhe = detalhe;
	}
	public Calendar getData() {
		return data;
	}
	public void setData(Calendar data) {
		this.data = data;
	}
	// Institui��o
	public int getId_instituicao() {
		return id_instituicao;
	}
	public void setId_instituicao(int id_instituicao) {
		this.id_instituicao = id_instituicao;
	}
	public int getFK_pais() {
		return FK_pais;
	}
	public void setFK_pais(int fK_pais) {
		FK_pais = fK_pais;
	}
	public int getFK_provincia() {
		return FK_provincia;
	}
	public void setFK_provincia(int fK_provincia) {
		FK_provincia = fK_provincia;
	}
	public int getFK_municipio() {
		return FK_municipio;
	}
	public void setFK_municipio(int fK_municipio) {
		FK_municipio = fK_municipio;
	}
	public int getFK_distrito() {
		return FK_distrito;
	}
	public void setFK_distrito(int fK_distrito) {
		FK_distrito = fK_distrito;
	}
	public String getNome_registo() {
		return nome_registo;
	}
	public void setNome_registo(String nome_registo) {
		this.nome_registo = nome_registo;
	}
	public String getNome_comercial() {
		return nome_comercial;
	}
	public void setNome_comercial(String nome_comercial) {
		this.nome_comercial = nome_comercial;
	}
	public String getRua() {
		return rua;
	}
	public void setRua(String rua) {
		this.rua = rua;
	}
	public String getBairro() {
		return bairro;
	}
	public void setBairro(String bairro) {
		this.bairro = bairro;
	}
	public String getSite() {
		return site;
	}
	public void setSite(String site) {
		this.site = site;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public int getNumero_registo() {
		return numero_registo;
	}
	public void setNumero_registo(int numero_registo) {
		this.numero_registo = numero_registo;
	}
	public String getNif() {
		return nif;
	}
	public void setNif(String nif) {
		this.nif = nif;
	}
	public Calendar getData_fundacao() {
		return data_fundacao;
	}
	public void setData_fundacao(Calendar data_fundacao) {
		this.data_fundacao = data_fundacao;
	}
	public String getSlogan() {
		return slogan;
	}
	public void setSlogan(String slogan) {
		this.slogan = slogan;
	}
	public String getLogo() {
		return logo;
	}
	public void setLogo(String logo) {
		this.logo = logo;
	}
	
	private String quem_vendemo;
	private String oferta_valor;
	
	private String segmento_cliente;
	private String mercado_alvo;
	
	private String como_vendemo;
	private String canal_venda;
	
	private String relacao_cliente;
	
	private String fonte_receita;
	
	private String recurso_chave;
	
	private String atividade_chave;
	private String parceiro_chave;

	public String getQuem_vendemo() {
		return quem_vendemo;
	}
	public void setQuem_vendemo(String quem_vendemo) {
		this.quem_vendemo = quem_vendemo;
	}
	public String getOferta_valor() {
		return oferta_valor;
	}
	public void setOferta_valor(String oferta_valor) {
		this.oferta_valor = oferta_valor;
	}
	public String getSegmento_cliente() {
		return segmento_cliente;
	}
	public void setSegmento_cliente(String segmento_cliente) {
		this.segmento_cliente = segmento_cliente;
	}
	public String getMercado_alvo() {
		return mercado_alvo;
	}
	public void setMercado_alvo(String mercado_alvo) {
		this.mercado_alvo = mercado_alvo;
	}
	public String getComo_vendemo() {
		return como_vendemo;
	}
	public void setComo_vendemo(String como_vendemo) {
		this.como_vendemo = como_vendemo;
	}
	public String getCanal_venda() {
		return canal_venda;
	}
	public void setCanal_venda(String canal_venda) {
		this.canal_venda = canal_venda;
	}
	public String getRelacao_cliente() {
		return relacao_cliente;
	}
	public void setRelacao_cliente(String relacao_cliente) {
		this.relacao_cliente = relacao_cliente;
	}
	public String getFonte_receita() {
		return fonte_receita;
	}
	public void setFonte_receita(String fonte_receita) {
		this.fonte_receita = fonte_receita;
	}
	public String getRecurso_chave() {
		return recurso_chave;
	}
	public void setRecurso_chave(String recurso_chave) {
		this.recurso_chave = recurso_chave;
	}
	public String getAtividade_chave() {
		return atividade_chave;
	}
	public void setAtividade_chave(String atividade_chave) {
		this.atividade_chave = atividade_chave;
	}
	public String getParceiro_chave() {
		return parceiro_chave;
	}
	public void setParceiro_chave(String parceiro_chave) {
		this.parceiro_chave = parceiro_chave;
	}
	public int getNum_funcionario() {
		return num_funcionario;
	}
	public void setNum_funcionario(int num_funcionario) {
		this.num_funcionario = num_funcionario;
	}
	public String getNumContribuente() {
		return numContribuente;
	}
	public void setNumContribuente(String numContribuente) {
		this.numContribuente = numContribuente;
	}
	
	
}
