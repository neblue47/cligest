package ao.co.cligest.entidades;

import java.io.Serializable;

public class SelecionarBanco implements Serializable {
	private static final long serialVersionUID = 1L;
	
	private int id_banco;
	private String banco;
	
	public int getId_banco() {
		return id_banco;
	}
	public void setId_banco(int id_banco) {
		this.id_banco = id_banco;
	}
	public String getBanco() {
		return banco;
	}
	public void setBanco(String banco) {
		this.banco = banco;
	}	
}
