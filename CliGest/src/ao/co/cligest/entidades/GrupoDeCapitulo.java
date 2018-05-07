package ao.co.cligest.entidades;

import java.io.Serializable;

public class GrupoDeCapitulo implements Serializable {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private Integer idGrupoDCapitulo;
	private Integer fkCapitulo;
	private String codigoGrupo;
	private String descricaoGrupo;
	private String capitulolistado;
	
	public Integer getIdGrupoDCapitulo() {
		return idGrupoDCapitulo;
	}
	public void setIdGrupoDCapitulo(Integer idGrupoDCapitulo) {
		this.idGrupoDCapitulo = idGrupoDCapitulo;
	}
	public Integer getFkCapitulo() {
		return fkCapitulo;
	}
	public void setFkCapitulo(Integer fkCapitulo) {
		this.fkCapitulo = fkCapitulo;
	}
	public String getCodigoGrupo() {
		return codigoGrupo;
	}
	public void setCodigoGrupo(String codigoGrupo) {
		this.codigoGrupo = codigoGrupo;
	}
	public String getDescricaoGrupo() {
		return descricaoGrupo;
	}
	public void setDescricaoGrupo(String descricaoGrupo) {
		this.descricaoGrupo = descricaoGrupo;
	}
	public String getCapitulolistado() {
		return capitulolistado;
	}
	public void setCapitulolistado(String capitulolistado) {
		this.capitulolistado = capitulolistado;
	}
}
