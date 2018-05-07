package ao.co.cligest.entidades;

public class Emergencia extends Paciente{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	private int id_emergencia;
	private int fk_cexegou;
	private String cxegou ;
	private String rondate;
	private String unidade;
	private String matricula;
	private int tipoDoc;
	private String numDocs;
	private String status;
	
	
	
	
	public int getId_emergencia() {
		return id_emergencia;
	}
	public void setId_emergencia(int id_emergencia) {
		this.id_emergencia = id_emergencia;
	}
	
	public int getFk_cexegou() {
		return fk_cexegou;
	}
	public void setFk_cexegou(int fk_cexegou) {
		this.fk_cexegou = fk_cexegou;
	}
	public String getCxegou() {
		return cxegou;
	}
	public void setCxegou(String cxegou) {
		this.cxegou = cxegou;
	}
	public String getRondate() {
		return rondate;
	}
	public void setRondate(String rondate) {
		this.rondate = rondate;
	}
	public String getUnidade() {
		return unidade;
	}
	public void setUnidade(String unidade) {
		this.unidade = unidade;
	}
	public String getMatricula() {
		return matricula;
	}
	public void setMatricula(String matricula) {
		this.matricula = matricula;
	}
	public int getTipoDoc() {
		return tipoDoc;
	}
	public void setTipoDoc(int tipoDoc) {
		this.tipoDoc = tipoDoc;
	}
	public String getNumDocs() {
		return numDocs;
	}
	public void setNumDocs(String numDocs) {
		this.numDocs = numDocs;
	}
	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
	}
	
	
	

}
