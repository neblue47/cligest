package ao.co.cligest.entidades;

public class Alocador {
	
	//Declaração das variáveis alocador Aridja Cussunga André Zinga
	// Local de Armazenamento
	private int id_local_dearmazenamento;
	private String localdearmazenamento;
	
	// Bloco/Ala    tblalabloco   
	private int id_alabloco;
	private int FK_local_dearmazenamento;
	private String alabloco_doproduto;
	
	// Montra      tblmontra
	private int id_montra_deproduto;
	private int FK_alabloco;
	private String montra_deproduto;
	
	// Pratileira         tblprartileira
	private int id_prartileira;
	private int FK_montra_deproduto;
	private String prartileira;
	
	public int getId_local_dearmazenamento() {
		return id_local_dearmazenamento;
	}
	public void setId_local_dearmazenamento(int id_local_dearmazenamento) {
		this.id_local_dearmazenamento = id_local_dearmazenamento;
	}
	public String getLocaldearmazenamento() {
		return localdearmazenamento;
	}
	public void setLocaldearmazenamento(String localdearmazenamento) {
		this.localdearmazenamento = localdearmazenamento;
	}
	public int getId_alabloco() {
		return id_alabloco;
	}
	public void setId_alabloco(int id_alabloco) {
		this.id_alabloco = id_alabloco;
	}
	public int getFK_local_dearmazenamento() {
		return FK_local_dearmazenamento;
	}
	public void setFK_local_dearmazenamento(int FK_local_dearmazenamento) {
		this.FK_local_dearmazenamento = FK_local_dearmazenamento;
	}
	public String getAlabloco_doproduto() {
		return alabloco_doproduto;
	}
	public void setAlabloco_doproduto(String alabloco_doproduto) {
		this.alabloco_doproduto = alabloco_doproduto;
	}
	public int getId_montra_deproduto() {
		return id_montra_deproduto;
	}
	public void setId_montra_deproduto(int id_montra_deproduto) {
		this.id_montra_deproduto = id_montra_deproduto;
	}
	public int getFK_alabloco() {
		return FK_alabloco;
	}
	public void setFK_alabloco(int FK_alabloco) {
		this.FK_alabloco = FK_alabloco;
	}
	public String getMontra_deproduto() {
		return montra_deproduto;
	}
	public void setMontra_deproduto(String montra_deproduto) {
		this.montra_deproduto = montra_deproduto;
	}
	public int getId_prartileira() {
		return id_prartileira;
	}
	public void setId_prartileira(int id_prartileira) {
		this.id_prartileira = id_prartileira;
	}
	public int getFK_montra_deproduto() {
		return FK_montra_deproduto;
	}
	public void setFK_montra_deproduto(int FK_montra_deproduto) {
		this.FK_montra_deproduto = FK_montra_deproduto;
	}
	public String getPrartileira() {
		return prartileira;
	}
	public void setPrartileira(String prartileira) {
		this.prartileira = prartileira;
	}
}
