package ao.co.cligest.entidades;

import java.io.Serializable;
import java.sql.Date;

@SuppressWarnings("serial")
public class InforPaciente implements Serializable {

	private int id_entidade;
	private int FK_paciente;
	private int FK_sanguineo;
	private int dif_fisico;
	private int FK_m_amputado;
	private int sof_dalergia;
	private String obser_dalegia;
	private String notas;
	private int FK_alergia;
	private String alergia;
	private String factor_rh;
	private String proveniencia;
	
//	---------------------------- Grupo Sanguineo
	private int id_sanguineo;
	private String sanguineo;
	private String descricao;
	
//	---------------------------- Membro Amputado
	private int id_m_amputado;
	private String m_amputado;
//	--------------------------- Dados do Paciente
	private String nome;
	private String nomem;
	private String apelido;
	private String numero_processo;
	private String num_Doc;
	private Date dataNasc;

//	---------------------------- FIM
	
	
	public int getFK_paciente() {
		return FK_paciente;
	}
	public int getId_entidade() {
		return id_entidade;
	}
	public void setId_entidade(int id_entidade) {
		this.id_entidade = id_entidade;
	}
	public void setFK_paciente(int fK_paciente) {
		FK_paciente = fK_paciente;
	}
	public int getFK_sanguineo() {
		return FK_sanguineo;
	}
	public void setFK_sanguineo(int fK_sanguineo) {
		FK_sanguineo = fK_sanguineo;
	}
	public int getDif_fisico() {
		return dif_fisico;
	}
	public void setDif_fisico(int dif_fisico) {
		this.dif_fisico = dif_fisico;
	}
	public int getFK_m_amputado() {
		return FK_m_amputado;
	}
	public void setFK_m_amputado(int fK_m_amputado) {
		FK_m_amputado = fK_m_amputado;
	}
	public int getSof_dalergia() {
		return sof_dalergia;
	}
	public void setSof_dalergia(int sof_dalergia) {
		this.sof_dalergia = sof_dalergia;
	}
	public String getObser_dalegia() {
		return obser_dalegia;
	}
	public void setObser_dalegia(String obser_dalegia) {
		this.obser_dalegia = obser_dalegia;
	}
	public String getNotas() {
		return notas;
	}
	public void setNotas(String notas) {
		this.notas = notas;
	}
//	------------------------------- GRUPO SANGUINEO
	public int getId_sanguineo() {
		return id_sanguineo;
	}
	public void setId_sanguineo(int id_sanguineo) {
		this.id_sanguineo = id_sanguineo;
	}
	public String getSanguineo() {
		return sanguineo;
	}
	public void setSanguineo(String sanguineo) {
		this.sanguineo = sanguineo;
	}
	public String getDescricao() {
		return descricao;
	}
	public void setDescricao(String descricao) {
		this.descricao = descricao;
	}	
//	------------------------------------------ MEMBRO AMPUTADO
	public int getId_m_amputado() {
		return id_m_amputado;
	}
	public void setId_m_amputado(int id_m_amputado) {
		this.id_m_amputado = id_m_amputado;
	}
	public String getM_amputado() {
		return m_amputado;
	}
	public void setM_amputado(String m_amputado) {
		this.m_amputado = m_amputado;
	}
//	------------------------------------------- DADOS DO PACIENTE
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
	public String getNumero_processo() {
		return numero_processo;
	}
	public void setNumero_processo(String numero_processo) {
		this.numero_processo = numero_processo;
	}
	public String getNum_Doc() {
		return num_Doc;
	}
	public void setNum_Doc(String num_Doc) {
		this.num_Doc = num_Doc;
	}
	public Date getDataNasc() {
		return dataNasc;
	}
	public void setDataNasc(Date dataNasc) {
		this.dataNasc = dataNasc;
	}
	public int getFK_alergia() {
		return FK_alergia;
	}
	public void setFK_alergia(int fK_alergia) {
		FK_alergia = fK_alergia;
	}
	public String getAlergia() {
		return alergia;
	}
	public void setAlergia(String alergia) {
		this.alergia = alergia;
	}
	public String getFactor_rh() {
		return factor_rh;
	}
	public void setFactor_rh(String factor_rh) {
		this.factor_rh = factor_rh;
	}
	public String getProveniencia() {
		return proveniencia;
	}
	public void setProveniencia(String proveniencia) {
		this.proveniencia = proveniencia;
	}
	
	
}
