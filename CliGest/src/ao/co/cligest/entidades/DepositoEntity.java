package ao.co.cligest.entidades;

import java.util.Calendar;

public class DepositoEntity extends Paciente {

	private int id_deposito;
	private String sector;
	private  int tipo_depositante;
	private double valor_deposito;
	private Calendar data_reg;
	private String hora_reg;
	private String nome_ficheiro;
	public int getId_deposito() {
		return id_deposito;
	}
	public void setId_deposito(int id_deposito) {
		this.id_deposito = id_deposito;
	}
	public String getSector() {
		return sector;
	}
	public void setSector(String sector) {
		this.sector = sector;
	}
	public int getTipo_depositante() {
		return tipo_depositante;
	}
	public void setTipo_depositante(int tipo_depositante) {
		this.tipo_depositante = tipo_depositante;
	}
	public double getValor_deposito() {
		return valor_deposito;
	}
	public void setValor_deposito(double valor_deposito) {
		this.valor_deposito = valor_deposito;
	}
	public Calendar getData_reg() {
		return data_reg;
	}
	public void setData_reg(Calendar data_reg) {
		this.data_reg = data_reg;
	}
	public String getHora_reg() {
		return hora_reg;
	}
	public void setHora_reg(String hora_reg) {
		this.hora_reg = hora_reg;
	}
	public String getNome_ficheiro() {
		return nome_ficheiro;
	}
	public void setNome_ficheiro(String nome_ficheiro) {
		this.nome_ficheiro = nome_ficheiro;
	}
	
	
}
