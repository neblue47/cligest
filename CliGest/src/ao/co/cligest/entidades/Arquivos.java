package ao.co.cligest.entidades;

import java.io.Serializable;

public class Arquivos implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private Integer fk_entidade;
	private Integer arquivo; 
	private String localArq;
	private String nomeArq;
	public Integer getFk_entidade() {
		return fk_entidade;
	}
	public void setFk_entidade(Integer fk_entidade) {
		this.fk_entidade = fk_entidade;
	}
	public Integer getArquivo() {
		return arquivo;
	}
	public void setArquivo(Integer arquivo) {
		this.arquivo = arquivo;
	}
	public String getLocalArq() {
		return localArq;
	}
	public void setLocalArq(String localArq) {
		this.localArq = localArq;
	}
	public String getNomeArq() {
		return nomeArq;
	}
	public void setNomeArq(String nomeArq) {
		this.nomeArq = nomeArq;
	}
	
	
}
