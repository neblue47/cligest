package ao.co.cligest.entidades;

import java.io.Serializable;

public class ConfNomeFactura implements Serializable{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private int id_nome_factura;
	private String config_nome_factura;
	private String data;
	private int FK_funcionario;
	private int numEnco;
	private String config_nome;
	
	
	
	 
	public int getNumEnco() {
		return numEnco;
	}
	public void setNumEnco(int numEnco) {
		this.numEnco = numEnco;
	}
	public String getConfig_nome() {
		return config_nome;
	}
	public void setConfig_nome(String config_nome) {
		this.config_nome = config_nome;
	}
	public int getId_nome_factura() {
		return id_nome_factura;
	}
	public void setId_nome_factura(int id_nome_factura) {
		this.id_nome_factura = id_nome_factura;
	}
	public String getConfig_nome_factura() {
		return config_nome_factura;
	}
	public void setConfig_nome_factura(String config_nome_factura) {
		this.config_nome_factura = config_nome_factura;
	}
	public String getData() {
		return data;
	}
	public void setData(String data) {
		this.data = data;
	}
	public int getFK_funcionario() {
		return FK_funcionario;
	}
	public void setFK_funcionario(int fK_funcionario) {
		FK_funcionario = fK_funcionario;
	}	
	
}
