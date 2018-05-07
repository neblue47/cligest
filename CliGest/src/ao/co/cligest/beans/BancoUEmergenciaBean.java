package ao.co.cligest.beans;

import java.util.Calendar;

 
public class BancoUEmergenciaBean {

	private String nomeStatus;
	private int qtd_atendidas;
	private Calendar data_atendidas;
	private int FK_especialidade;
	private String especialidade;
	private int qtd_masculino;
	private double perc_masculino;
	private int qtd_feminino;
	private double perc_feminino;
	private int quantidade;
	private String doenca;
	private int FK_doenca;
	private int qt_medicamentos;
	private int qt_materiais;
	private int qt_procedimentos;
	
	
	
	
	public int getQt_medicamentos() {
		return qt_medicamentos;
	}
	public void setQt_medicamentos(int qt_medicamentos) {
		this.qt_medicamentos = qt_medicamentos;
	}
	public int getQt_materiais() {
		return qt_materiais;
	}
	public void setQt_materiais(int qt_materiais) {
		this.qt_materiais = qt_materiais;
	}
	public int getQt_procedimentos() {
		return qt_procedimentos;
	}
	public void setQt_procedimentos(int qt_procedimentos) {
		this.qt_procedimentos = qt_procedimentos;
	}
	public String getDoenca() {
		return doenca;
	}
	public void setDoenca(String doenca) {
		this.doenca = doenca;
	}
	public int getFK_doenca() {
		return FK_doenca;
	}
	public void setFK_doenca(int fK_doenca) {
		FK_doenca = fK_doenca;
	}
	public int getQuantidade() {
		return quantidade;
	}
	public void setQuantidade(int quantidade) {
		this.quantidade = quantidade;
	}
	public String getNomeStatus() {
		return nomeStatus;
	}
	public void setNomeStatus(String nomeStatus) {
		this.nomeStatus = nomeStatus;
	}
	public int getQtd_atendidas() {
		return qtd_atendidas;
	}
	public void setQtd_atendidas(int qtd_atendidas) {
		this.qtd_atendidas = qtd_atendidas;
	}
	public Calendar getData_atendidas() {
		return data_atendidas;
	}
	public void setData_atendidas(Calendar data_atendidas) {
		this.data_atendidas = data_atendidas;
	}
	public int getFK_especialidade() {
		return FK_especialidade;
	}
	public void setFK_especialidade(int fK_especialidade) {
		FK_especialidade = fK_especialidade;
	}
	public String getEspecialidade() {
		return especialidade;
	}
	public void setEspecialidade(String especialidade) {
		this.especialidade = especialidade;
	}
	public int getQtd_masculino() {
		return qtd_masculino;
	}
	public void setQtd_masculino(int qtd_masculino) {
		this.qtd_masculino = qtd_masculino;
	}
	public double getPerc_masculino() {
		return perc_masculino;
	}
	public void setPerc_masculino(double perc_masculino) {
		this.perc_masculino = perc_masculino;
	}
	public int getQtd_feminino() {
		return qtd_feminino;
	}
	public void setQtd_feminino(int qtd_feminino) {
		this.qtd_feminino = qtd_feminino;
	}
	public double getPerc_feminino() {
		return perc_feminino;
	}
	public void setPerc_feminino(double perc_feminino) {
		this.perc_feminino = perc_feminino;
	}
	
	
}
