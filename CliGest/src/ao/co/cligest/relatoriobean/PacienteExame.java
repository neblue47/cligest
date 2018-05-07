package ao.co.cligest.relatoriobean;

import java.util.Calendar;

public class PacienteExame {

	private int FK_num_servico;
	private String numProcesso;
	private String nome_paciente;
	private int idade;
	private String genero;
	 
	private int qdt_servico;
	private Calendar data_exame;
	
	public PacienteExame() {
		super();
		// TODO Auto-generated constructor stub
	}

	public int getFK_num_servico() {
		return FK_num_servico;
	}

	public void setFK_num_servico(int fK_num_servico) {
		FK_num_servico = fK_num_servico;
	}

	 

	public String getNumProcesso() {
		return numProcesso;
	}

	public void setNumProcesso(String numProcesso) {
		this.numProcesso = numProcesso;
	}

	public String getNome_paciente() {
		return nome_paciente;
	}

	public void setNome_paciente(String nome_paciente) {
		this.nome_paciente = nome_paciente;
	}

	public int getIdade() {
		return idade;
	}

	public void setIdade(int idade) {
		this.idade = idade;
	}

	public String getGenero() {
		return genero;
	}

	public void setGenero(String genero) {
		this.genero = genero;
	}

	 

	public int getQdt_servico() {
		return qdt_servico;
	}

	public void setQdt_servico(int qdt_servico) {
		this.qdt_servico = qdt_servico;
	}

	public Calendar getData_exame() {
		return data_exame;
	}

	public void setData_exame(Calendar data_exame) {
		this.data_exame = data_exame;
	}

	
		
	
}
