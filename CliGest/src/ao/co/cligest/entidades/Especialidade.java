package ao.co.cligest.entidades;

import java.io.Serializable;

public class Especialidade implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private int id_espec;
	private String espec;
	
	public int getId_espec() {
		return id_espec;
	}
	public void setId_espec(int id_espec) {
		this.id_espec = id_espec;
	}
	public String getEspec() {
		return espec;
	}
	public void setEspec(String espec) {
		this.espec = espec;
	}
}
