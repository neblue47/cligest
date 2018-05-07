package ao.co.cligest.entidades;

import java.io.Serializable;

public class TipoServico implements Serializable {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private int id_tipo_servico;
	private int FK_especialidade;
	private String tipo_de_servico;
	private String especialidade;
	
	
	public String getEspecialidade() {
		return especialidade;
	}
	public void setEspecialidade(String especialidade) {
		this.especialidade = especialidade;
	}
	public int getId_tipo_servico() {
		return id_tipo_servico;
	}
	public void setId_tipo_servico(int id_tipo_servico) {
		this.id_tipo_servico = id_tipo_servico;
	}
	public int getFK_especialidade() {
		return FK_especialidade;
	}
	public void setFK_especialidade(int fK_especialidade) {
		FK_especialidade = fK_especialidade;
	}
	public String getTipo_de_servico() {
		return tipo_de_servico;
	}
	public void setTipo_de_servico(String tipo_de_servico) {
		this.tipo_de_servico = tipo_de_servico;
	}	
}
