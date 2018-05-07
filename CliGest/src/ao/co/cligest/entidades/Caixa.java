package ao.co.cligest.entidades;

import java.io.Serializable;
import java.util.Calendar;

@SuppressWarnings("serial")
public class Caixa extends Funcionario implements Serializable{

	/**
	 * @autor Criado Por Nelson Diwidi - Angotech
	 * 
	 */
	
	private int id_estado_docaixa;
	private int estado_docaixa;
	private double valor_dtroco;
	private int fk_entidade_caixa;
	private double fundos_reserva;
	private Calendar data_atribuicao;
	private String hora_atribuicao;
	private int id_aberturadecaixa;
	private int id_fecho_decaixa;
	private int fk_fecho_dcaixa;
	private double montante_dabertura;
	private String maquina_dabertuara;
	private Calendar data_dabertura;
	private String hora_dabertura;
	private String descr_abertura_decaixa;
	private double total_facturado;
	private double total_sistema;
	private Calendar data_dfecho;
	private String hora_dfecho; 
	private int num_fecho;
	private int num_movimentos;
	private int status;
	private String valor_formatado;
	private String tipo_da_venda;

	// fluxo de caixa
	private int id_numero_dofluxo;
	private Calendar data_dofluxo;
	private String hora_dofluxo;
	private int id_fluxo_decaixa;
	private double total_fluxo;
	private String numFluxo;
	
	// extra-fields
	private double total_cash;
	private double total_atm;
	private double total_isencao;
	private double total_isencaoIdade;
	private double total_seguro;
	private double totalDevolvido;
	private double totalReduzido;
	
	public double getTotalReduzido() {
		return totalReduzido;
	}
	public void setTotalReduzido(double totalReduzido) {
		this.totalReduzido = totalReduzido;
	}
	public double getTotalDevolvido() {
		return totalDevolvido;
	}
	public void setTotalDevolvido(double totalDevolvido) {
		this.totalDevolvido = totalDevolvido;
	}
	private double totalMao;
	private double totalMcx;
	
	public int getId_estado_docaixa() {
		return id_estado_docaixa;
	}
	public void setId_estado_docaixa(int id_estado_docaixa) {
		this.id_estado_docaixa = id_estado_docaixa;
	}
	public int getEstado_docaixa() {
		return estado_docaixa;
	}
	public void setEstado_docaixa(int estado_docaixa) {
		this.estado_docaixa = estado_docaixa;
	}
	public double getValor_dtroco() {
		return valor_dtroco;
	}
	public void setValor_dtroco(double valor_dtroco) {
		this.valor_dtroco = valor_dtroco;
	}
	public int getFk_entidade_caixa() {
		return fk_entidade_caixa;
	}
	public void setFk_entidade_caixa(int fk_entidade_caixa) {
		this.fk_entidade_caixa = fk_entidade_caixa;
	}
	public double getFundos_reserva() {
		return fundos_reserva;
	}
	public void setFundos_reserva(double fundos_reserva) {
		this.fundos_reserva = fundos_reserva;
	}
	public Calendar getData_atribuicao() {
		return data_atribuicao;
	}
	public void setData_atribuicao(Calendar data_atribuicao) {
		this.data_atribuicao = data_atribuicao;
	}
	public String getHora_atribuicao() {
		return hora_atribuicao;
	}
	public void setHora_atribuicao(String hora_atribuicao) {
		this.hora_atribuicao = hora_atribuicao;
	}
	public int getId_aberturadecaixa() {
		return id_aberturadecaixa;
	}
	public void setId_aberturadecaixa(int id_aberturadecaixa) {
		this.id_aberturadecaixa = id_aberturadecaixa;
	}
	public double getMontante_dabertura() {
		return montante_dabertura;
	}
	public void setMontante_dabertura(double montante_dabertura) {
		this.montante_dabertura = montante_dabertura;
	}
	public String getMaquina_dabertuara() {
		return maquina_dabertuara;
	}
	public void setMaquina_dabertuara(String maquina_dabertuara) {
		this.maquina_dabertuara = maquina_dabertuara;
	}
	public Calendar getData_dabertura() {
		return data_dabertura;
	}
	public void setData_dabertura(Calendar data_dabertura) {
		this.data_dabertura = data_dabertura;
	}
	public String getHora_dabertura() {
		return hora_dabertura;
	}
	public void setHora_dabertura(String hora_dabertura) {
		this.hora_dabertura = hora_dabertura;
	}
	public String getDescr_abertura_decaixa() {
		return descr_abertura_decaixa;
	}
	public void setDescr_abertura_decaixa(String descr_abertura_decaixa) {
		this.descr_abertura_decaixa = descr_abertura_decaixa;
	}
	public double getTotal_facturado() {
		return total_facturado;
	}
	public void setTotal_facturado(double total_facturado) {
		this.total_facturado = total_facturado;
	}
	public Calendar getData_dfecho() {
		return data_dfecho;
	}
	public void setData_dfecho(Calendar data_dfecho) {
		this.data_dfecho = data_dfecho;
	}
	public String getHora_dfecho() {
		return hora_dfecho;
	}
	public void setHora_dfecho(String hora_dfecho) {
		this.hora_dfecho = hora_dfecho;
	}
	public int getNum_fecho() {
		return num_fecho;
	}
	public void setNum_fecho(int num_fecho) {
		this.num_fecho = num_fecho;
	}
	public int getNum_movimentos() {
		return num_movimentos;
	}
	public void setNum_movimentos(int num_movimentos) {
		this.num_movimentos = num_movimentos;
	}
	public int getStatus() {
		return status;
	}
	public void setStatus(int status) {
		this.status = status;
	}
	public double getTotal_sistema() {
		return total_sistema;
	}
	public void setTotal_sistema(double total_sistema) {
		this.total_sistema = total_sistema;
	}
	public String getValor_formatado() {
		return valor_formatado;
	}
	public void setValor_formatado(String valor_formatado) {
		this.valor_formatado = valor_formatado;
	}
	public int getId_fecho_decaixa() {
		return id_fecho_decaixa;
	}
	public void setId_fecho_decaixa(int id_fecho_decaixa) {
		this.id_fecho_decaixa = id_fecho_decaixa;
	}
	public double getTotal_cash() {
		return total_cash;
	}
	public void setTotal_cash(double total_cash) {
		this.total_cash = total_cash;
	}
	public double getTotal_atm() {
		return total_atm;
	}
	public void setTotal_atm(double total_atm) {
		this.total_atm = total_atm;
	}
	public double getTotal_isencao() {
		return total_isencao;
	}
	public void setTotal_isencao(double total_isencao) {
		this.total_isencao = total_isencao;
	}
	public double getTotal_isencaoIdade() {
		return total_isencaoIdade;
	}
	public void setTotal_isencaoIdade(double total_isencaoIdade) {
		this.total_isencaoIdade = total_isencaoIdade;
	}
	public double getTotal_seguro() {
		return total_seguro;
	}
	public void setTotal_seguro(double total_seguro) {
		this.total_seguro = total_seguro;
	}
	public int getId_numero_dofluxo() {
		return id_numero_dofluxo;
	}
	public void setId_numero_dofluxo(int id_numero_dofluxo) {
		this.id_numero_dofluxo = id_numero_dofluxo;
	}
	public Calendar getData_dofluxo() {
		return data_dofluxo;
	}
	public void setData_dofluxo(Calendar data_dofluxo) {
		this.data_dofluxo = data_dofluxo;
	}
	public String getHora_dofluxo() {
		return hora_dofluxo;
	}
	public void setHora_dofluxo(String hora_dofluxo) {
		this.hora_dofluxo = hora_dofluxo;
	}
	public int getId_fluxo_decaixa() {
		return id_fluxo_decaixa;
	}
	public void setId_fluxo_decaixa(int id_fluxo_decaixa) {
		this.id_fluxo_decaixa = id_fluxo_decaixa;
	}
	public double getTotal_fluxo() {
		return total_fluxo;
	}
	public void setTotal_fluxo(double total_fluxo) {
		this.total_fluxo = total_fluxo;
	}
	public String getNumFluxo() {
		return numFluxo;
	}
	public void setNumFluxo(String numFluxo) {
		this.numFluxo = numFluxo;
	}
	public int getFk_fecho_dcaixa() {
		return fk_fecho_dcaixa;
	}
	public void setFk_fecho_dcaixa(int fk_fecho_dcaixa) {
		this.fk_fecho_dcaixa = fk_fecho_dcaixa;
	}
	public double getTotalMao() {
		return totalMao;
	}
	public void setTotalMao(double totalMao) {
		this.totalMao = totalMao;
	}
	public double getTotalMcx() {
		return totalMcx;
	}
	public void setTotalMcx(double totalMcx) {
		this.totalMcx = totalMcx;
	}
	public String getTipo_da_venda() {
		return tipo_da_venda;
	}
	public void setTipo_da_venda(String tipo_da_venda) {
		this.tipo_da_venda = tipo_da_venda;
	}
	
}
