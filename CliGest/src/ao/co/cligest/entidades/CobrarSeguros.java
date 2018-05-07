package ao.co.cligest.entidades;

public class CobrarSeguros extends Facturacao{

	private int FK_criar_fac_convenio;
	private int fk_conv_modo_pagamento;
	private int numero_paga_conv;
	private int agencia_paga_conv;
	private int rota_paga_conv_cheque;
	private int fk_fac_paga_convenio;
	private double valor_pago__paga_conv;
	private int fk_banco_origem_paga_conv;
	private String codigo_paga_conv_transferencia;
	private int fk_banco_ditinatario_paga_conv;
	private String iban_paga_conv_transferencia;
	private String swift_paga_conv_transferencia;
	private String obs_paga_conv_transferencia;
	private String numero_fac_paga_convenio;
	
	

	public int getFK_criar_fac_convenio() {
		return FK_criar_fac_convenio;
	}

	public void setFK_criar_fac_convenio(int fK_criar_fac_convenio) {
		FK_criar_fac_convenio = fK_criar_fac_convenio;
	}

	public int getFk_conv_modo_pagamento() {
		return fk_conv_modo_pagamento;
	}

	public void setFk_conv_modo_pagamento(int fk_conv_modo_pagamento) {
		this.fk_conv_modo_pagamento = fk_conv_modo_pagamento;
	}

	public int getNumero_paga_conv() {
		return numero_paga_conv;
	}

	public void setNumero_paga_conv(int numero_paga_conv) {
		this.numero_paga_conv = numero_paga_conv;
	}

	public int getAgencia_paga_conv() {
		return agencia_paga_conv;
	}

	public void setAgencia_paga_conv(int agencia_paga_conv) {
		this.agencia_paga_conv = agencia_paga_conv;
	}

	public int getRota_paga_conv_cheque() {
		return rota_paga_conv_cheque;
	}

	public void setRota_paga_conv_cheque(int rota_paga_conv_cheque) {
		this.rota_paga_conv_cheque = rota_paga_conv_cheque;
	}

	public int getFk_fac_paga_convenio() {
		return fk_fac_paga_convenio;
	}

	public void setFk_fac_paga_convenio(int fk_fac_paga_convenio) {
		this.fk_fac_paga_convenio = fk_fac_paga_convenio;
	}

	public double getValor_pago__paga_conv() {
		return valor_pago__paga_conv;
	}

	public void setValor_pago__paga_conv(double valor_pago__paga_conv) {
		this.valor_pago__paga_conv = valor_pago__paga_conv;
	}

	public int getFk_banco_origem_paga_conv() {
		return fk_banco_origem_paga_conv;
	}

	public void setFk_banco_origem_paga_conv(int fk_banco_origem_paga_conv) {
		this.fk_banco_origem_paga_conv = fk_banco_origem_paga_conv;
	}

	public String getCodigo_paga_conv_transferencia() {
		return codigo_paga_conv_transferencia;
	}

	public void setCodigo_paga_conv_transferencia(String codigo_paga_conv_transferencia) {
		this.codigo_paga_conv_transferencia = codigo_paga_conv_transferencia;
	}

	public int getFk_banco_ditinatario_paga_conv() {
		return fk_banco_ditinatario_paga_conv;
	}

	public void setFk_banco_ditinatario_paga_conv(int fk_banco_ditinatario_paga_conv) {
		this.fk_banco_ditinatario_paga_conv = fk_banco_ditinatario_paga_conv;
	}

	public String getIban_paga_conv_transferencia() {
		return iban_paga_conv_transferencia;
	}

	public void setIban_paga_conv_transferencia(String iban_paga_conv_transferencia) {
		this.iban_paga_conv_transferencia = iban_paga_conv_transferencia;
	}

	public String getSwift_paga_conv_transferencia() {
		return swift_paga_conv_transferencia;
	}

	public void setSwift_paga_conv_transferencia(String swift_paga_conv_transferencia) {
		this.swift_paga_conv_transferencia = swift_paga_conv_transferencia;
	}

	public String getObs_paga_conv_transferencia() {
		return obs_paga_conv_transferencia;
	}

	public void setObs_paga_conv_transferencia(String obs_paga_conv_transferencia) {
		this.obs_paga_conv_transferencia = obs_paga_conv_transferencia;
	}

	public String getNumero_fac_paga_convenio() {
		return numero_fac_paga_convenio;
	}

	public void setNumero_fac_paga_convenio(String numero_fac_paga_convenio) {
		this.numero_fac_paga_convenio = numero_fac_paga_convenio;
	}
	
	
}
