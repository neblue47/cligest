package ao.co.cligest.entidades;

import java.io.Serializable;

public class Capitulo implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	//Entidades do Capitulo
	private int idCapitulo;
	private String codigoCapitulo;
	private String descricaoCapitulo;
	
	//Gets & Seters para o Capitulo
	public String getCodigoCapitulo() {
		return codigoCapitulo;
	}
	public void setCodigoCapitulo(String codigoCapitulo) {
		this.codigoCapitulo = codigoCapitulo;
	}
	public String getDescricaoCapitulo() {
		return descricaoCapitulo;
	}
	public void setDescricaoCapitulo(String descricaoCapitulo) {
		this.descricaoCapitulo = descricaoCapitulo;
	}
	public int getIdCapitulo() {
		return idCapitulo;
	}
	public void setIdCapitulo(int idCapitulo) {
		this.idCapitulo = idCapitulo;
	}
}
