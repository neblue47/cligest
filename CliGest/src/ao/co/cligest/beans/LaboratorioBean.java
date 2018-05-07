package ao.co.cligest.beans;

public class LaboratorioBean {

	private String nomeStatus;
	private int qtd_atendidas;	
	private int qtd_masculino;
	private double perc_masculino;
	private int qtd_feminino;
	private double perc_feminino;
	private int quantidade;
	private String doenca;
	private int FK_doenca;
	
	private int p_consulta;
	 private int p_externos;
	 private int p_internados;
	 private int p_banco_ue;
	
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
	public int getQuantidade() {
		return quantidade;
	}
	public void setQuantidade(int quantidade) {
		this.quantidade = quantidade;
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
	public int getP_consulta() {
		return p_consulta;
	}
	public void setP_consulta(int p_consulta) {
		this.p_consulta = p_consulta;
	}
	public int getP_externos() {
		return p_externos;
	}
	public void setP_externos(int p_externos) {
		this.p_externos = p_externos;
	}
	public int getP_internados() {
		return p_internados;
	}
	public void setP_internados(int p_internados) {
		this.p_internados = p_internados;
	}
	public int getP_banco_ue() {
		return p_banco_ue;
	}
	public void setP_banco_ue(int p_banco_ue) {
		this.p_banco_ue = p_banco_ue;
	}
	
	
	
}
