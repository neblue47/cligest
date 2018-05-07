package ao.co.cligest.entidades;

import java.util.Calendar;

public class ServicosObrigatorio {

	private int id_s_obrigatorio;
	private String maquina_cadastro;
	private int fk_funcionario;
	private Calendar data_registo;
	public int getId_s_obrigatorio() {
		return id_s_obrigatorio;
	}
	public void setId_s_obrigatorio(int id_s_obrigatorio) {
		this.id_s_obrigatorio = id_s_obrigatorio;
	}
	public String getMaquina_cadastro() {
		return maquina_cadastro;
	}
	public void setMaquina_cadastro(String maquina_cadastro) {
		this.maquina_cadastro = maquina_cadastro;
	}
	public int getFk_funcionario() {
		return fk_funcionario;
	}
	public void setFk_funcionario(int fk_funcionario) {
		this.fk_funcionario = fk_funcionario;
	}
	public Calendar getData_registo() {
		return data_registo;
	}
	public void setData_registo(Calendar data_registo) {
		this.data_registo = data_registo;
	}
	
	
	
}
