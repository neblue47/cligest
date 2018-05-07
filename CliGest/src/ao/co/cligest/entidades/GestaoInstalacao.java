package ao.co.cligest.entidades;

public class GestaoInstalacao {

	private int id_andar_bloco;
	private String andar_bloco;
	private String internamento;
	private int FK_internamento;
	private String numSala;
//	-------------------------- Andar Bloco
	private int id_sector;
	private String sector;
	private int extensao;
	private int FK_andar_bloco;
//	------------------------- sector
	private int id_sala;
	private int numero_sala;
	private String descricao;
	private int FK_sector;
//	--------------------- Sala
	private int id_leito;
	private String leito;
	private int status;
//	-------------------- leito
	
	public int getId_andar_bloco() {
		return id_andar_bloco;
	}
	public int getId_leito() {
		return id_leito;
	}
	public void setId_leito(int id_leito) {
		this.id_leito = id_leito;
	}
	public String getLeito() {
		return leito;
	}
	public void setLeito(String leito) {
		this.leito = leito;
	}
	public int getStatus() {
		return status;
	}
	public void setStatus(int status) {
		this.status = status;
	}
	public int getId_sala() {
		return id_sala;
	}
	public void setId_sala(int id_sala) {
		this.id_sala = id_sala;
	}
	public int getNumero_sala() {
		return numero_sala;
	}
	public void setNumero_sala(int numero_sala) {
		this.numero_sala = numero_sala;
	}
	public String getDescricao() {
		return descricao;
	}
	public void setDescricao(String descricao) {
		this.descricao = descricao;
	}
	public int getFK_sector() {
		return FK_sector;
	}
	public void setFK_sector(int fK_sector) {
		FK_sector = fK_sector;
	}
	public void setId_andar_bloco(int id_andar_bloco) {
		this.id_andar_bloco = id_andar_bloco;
	}
	public String getAndar_bloco() {
		return andar_bloco;
	}
	public void setAndar_bloco(String andar_bloco) {
		this.andar_bloco = andar_bloco;
	}
	
	public int getId_sector() {
		return id_sector;
	}
	public void setId_sector(int id_sector) {
		this.id_sector = id_sector;
	}
	public String getSector() {
		return sector;
	}
	public void setSector(String sector) {
		this.sector = sector;
	}
	public int getExtensao() {
		return extensao;
	}
	public void setExtensao(int extensao) {
		this.extensao = extensao;
	}
	public int getFK_andar_bloco() {
		return FK_andar_bloco;
	}
	public void setFK_andar_bloco(int fK_andar_bloco) {
		FK_andar_bloco = fK_andar_bloco;
	}
	public String getInternamento() {
		return internamento;
	}
	public void setInternamento(String internamento) {
		this.internamento = internamento;
	}
	public int getFK_internamento() {
		return FK_internamento;
	}
	public void setFK_internamento(int fK_internamento) {
		FK_internamento = fK_internamento;
	}
	public String getNumSala() {
		return numSala;
	}
	public void setNumSala(String numSala) {
		this.numSala = numSala;
	}
	
}
