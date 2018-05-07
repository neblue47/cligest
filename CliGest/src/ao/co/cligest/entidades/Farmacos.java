package ao.co.cligest.entidades;

import java.io.Serializable;

public class Farmacos implements Serializable
{
	/**
	 * 
	 */
	private int id_farmaco;
	private String nome_cm;
	private int fk_dosagem;
	private String dosagem;
	private int quantidade;
	private String posologia;
	private String farmaco;
	private int fk_vadministrar;
	private String vadministrar;
	private int FK_urgencia;
	public int getFK_urgencia() {
		return FK_urgencia;
	}
	public void setFK_urgencia(int fK_urgencia) {
		FK_urgencia = fK_urgencia;
	}
	public int getId_farmaco() {
		return id_farmaco;
	}
	public void setId_farmaco(int id_farmaco) {
		this.id_farmaco = id_farmaco;
	}
	public String getNome_cm() {
		return nome_cm;
	}
	public void setNome_cm(String nome_cm) {
		this.nome_cm = nome_cm;
	}
	
	
	public int getFk_dosagem() {
		return fk_dosagem;
	}
	public void setFk_dosagem(int fk_dosagem) {
		this.fk_dosagem = fk_dosagem;
	}
	public String getDosagem() {
		return dosagem;
	}
	public void setDosagem(String dosagem) {
		this.dosagem = dosagem;
	}

	public int getQuantidade() {
		return quantidade;
	}
	public void setQuantidade(int quantidade) {
		this.quantidade = quantidade;
	}
	public String getPosologia() {
		return posologia;
	}
	public void setPosologia(String posologia) {
		this.posologia = posologia;
	}
	public String getFarmaco() {
		return farmaco;
	}
	public void setFarmaco(String farmaco) {
		this.farmaco = farmaco;
	}
	public int getFk_vadministrar() {
		return fk_vadministrar;
	}
	public void setFk_vadministrar(int fk_vadministrar) {
		this.fk_vadministrar = fk_vadministrar;
	}
	public String getVadministrar() {
		return vadministrar;
	}
	public void setVadministrar(String vadministrar) {
		this.vadministrar = vadministrar;
	}



	private static final long serialVersionUID = 1L;
}
