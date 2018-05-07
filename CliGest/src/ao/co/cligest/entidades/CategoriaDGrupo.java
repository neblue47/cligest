package ao.co.cligest.entidades;

import java.io.Serializable;

public class CategoriaDGrupo implements Serializable {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	int idCategorizarGrupo;
	int fkcapitulo;
	int fkgrupo;
	
	String listarCodigoCapitulo;
	String listarCodigoGrupo;
	String categorizaGrupo;
	String descricaoCategoria;
	public int getIdCategorizarGrupo() {
		return idCategorizarGrupo;
	}
	public void setIdCategorizarGrupo(int idCategorizarGrupo) {
		this.idCategorizarGrupo = idCategorizarGrupo;
	}
	public int getFkcapitulo() {
		return fkcapitulo;
	}
	public void setFkcapitulo(int fkcapitulo) {
		this.fkcapitulo = fkcapitulo;
	}
	public int getFkgrupo() {
		return fkgrupo;
	}
	public void setFkgrupo(int fkgrupo) {
		this.fkgrupo = fkgrupo;
	}
	public String getListarCodigoCapitulo() {
		return listarCodigoCapitulo;
	}
	public void setListarCodigoCapitulo(String listarCodigoCapitulo) {
		this.listarCodigoCapitulo = listarCodigoCapitulo;
	}
	public String getListarCodigoGrupo() {
		return listarCodigoGrupo;
	}
	public void setListarCodigoGrupo(String listarCodigoGrupo) {
		this.listarCodigoGrupo = listarCodigoGrupo;
	}
	public String getCategorizaGrupo() {
		return categorizaGrupo;
	}
	public void setCategorizaGrupo(String categorizaGrupo) {
		this.categorizaGrupo = categorizaGrupo;
	}
	public String getDescricaoCategoria() {
		return descricaoCategoria;
	}
	public void setDescricaoCategoria(String descricaoCategoria) {
		this.descricaoCategoria = descricaoCategoria;
	}
	
		
}
