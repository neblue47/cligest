package ao.co.cligest.beans;

import java.util.Calendar;

public class PagamentoBean {

	private String descricao;
	private double preco;
	private int qtd_total;
	private int qtd_pagas;
	private int qtd_isenta;
	private int qtd_sessao;
	private double perc_pago;
	private double perc_isento;
	private String total_s;
	private double valor_total;
	private double valor_media;
	private double perc_msc;
	private double perc_fmn;
	private int dias_trabalho;
	private int num_funcionarios;
	private Calendar data_vendas;
	
	public String getDescricao() {
		return descricao;
	}
	public void setDescricao(String descricao) {
		this.descricao = descricao;
	}
	public double getPreco() {
		return preco;
	}
	public void setPreco(double preco) {
		this.preco = preco;
	}
	public int getQtd_total() {
		return qtd_total;
	}
	public void setQtd_total(int qtd_total) {
		this.qtd_total = qtd_total;
	}
	public int getQtd_pagas() {
		return qtd_pagas;
	}
	public void setQtd_pagas(int qtd_pagas) {
		this.qtd_pagas = qtd_pagas;
	}
	public int getQtd_isenta() {
		return qtd_isenta;
	}
	public void setQtd_isenta(int qtd_isenta) {
		this.qtd_isenta = qtd_isenta;
	}
	public double getPerc_pago() {
		return perc_pago;
	}
	public void setPerc_pago(double perc_pago) {
		this.perc_pago = perc_pago;
	}
	public double getPerc_isento() {
		return perc_isento;
	}
	public void setPerc_isento(double perc_isento) {
		this.perc_isento = perc_isento;
	}
	public String getTotal_s() {
		return total_s;
	}
	public void setTotal_s(String total_s) {
		this.total_s = total_s;
	}
	public int getQtd_sessao() {
		return qtd_sessao;
	}
	public void setQtd_sessao(int qtd_sessao) {
		this.qtd_sessao = qtd_sessao;
	}
	public double getValor_total() {
		return valor_total;
	}
	public void setValor_total(double valor_total) {
		this.valor_total = valor_total;
	}
	public double getValor_media() {
		return valor_media;
	}
	public void setValor_media(double valor_media) {
		this.valor_media = valor_media;
	}
	public double getPerc_msc() {
		return perc_msc;
	}
	public void setPerc_msc(double perc_msc) {
		this.perc_msc = perc_msc;
	}
	public double getPerc_fmn() {
		return perc_fmn;
	}
	public void setPerc_fmn(double perc_fmn) {
		this.perc_fmn = perc_fmn;
	}
	public int getDias_trabalho() {
		return dias_trabalho;
	}
	public void setDias_trabalho(int dias_trabalho) {
		this.dias_trabalho = dias_trabalho;
	}
	public int getNum_funcionarios() {
		return num_funcionarios;
	}
	public void setNum_funcionarios(int num_funcionarios) {
		this.num_funcionarios = num_funcionarios;
	}
	public Calendar getData_vendas() {
		return data_vendas;
	}
	public void setData_vendas(Calendar data_vendas) {
		this.data_vendas = data_vendas;
	}
	
	
}
