package ao.co.cligest.entidades;

import java.io.Serializable;

import ao.co.cligest.enums.PagaNaoPaga;

public class MenorIdade implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private int paga_maxima;
	
	private int idade;
	private String data;
	private int FK_funcionario;
	private int id_configurar_menor;
	private int idade_minima;
	private int paga_minima;
	private PagaNaoPaga paga;
	
	
	public int getId_configurar_menor() {
		return id_configurar_menor;
	}
	public void setId_configurar_menor(int id_configurar_menor) {
		this.id_configurar_menor = id_configurar_menor;
	}
	public int getPaga_maxima() {
		return paga_maxima;
	}
	public void setPaga_maxima(int paga_maxima) {
		this.paga_maxima = paga_maxima;
	}
	public int getIdade() {
		return idade;
	}
	public void setIdade(int idade) {
		this.idade = idade;
	}
	public String getData() {
		return data;
	}
	public void setData(String data) {
		this.data = data;
	}
	public int getFK_funcionario() {
		return FK_funcionario;
	}
	public void setFK_funcionario(int fK_funcionario) {
		FK_funcionario = fK_funcionario;
	}
	public int getIdade_minima() {
		return idade_minima;
	}
	public void setIdade_minima(int idade_minima) {
		this.idade_minima = idade_minima;
	}
	public int getPaga_minima() {
		return paga_minima;
	}
	public void setPaga_minima(int paga_minima) {
		this.paga_minima = paga_minima;
	}
	public PagaNaoPaga getPaga() {
		return paga;
	}
	 
	
	
	
}
