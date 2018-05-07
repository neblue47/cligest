package ao.co.cligest.entidades;

import java.util.Calendar;

@SuppressWarnings("serial")
public class Receitas extends Produtos {

	private int id_receita; 
	private String numero;
	private Calendar data_receita;
	private String hora_receita;
	public int getId_receita() {
		return id_receita;
	}
	public void setId_receita(int id_receita) {
		this.id_receita = id_receita;
	}
	public String getNumero() {
		return numero;
	}
	public void setNumero(String numero) {
		this.numero = numero;
	}
	public Calendar getData_receita() {
		return data_receita;
	}
	public void setData_receita(Calendar data_receita) {
		this.data_receita = data_receita;
	}
	public String getHora_receita() {
		return hora_receita;
	}
	public void setHora_receita(String hora_receita) {
		this.hora_receita = hora_receita;
	} 
	
		
}
