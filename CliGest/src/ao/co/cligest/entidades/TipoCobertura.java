package ao.co.cligest.entidades;

public class TipoCobertura {
	
//	------------------------------------ Cobertura
	private int id_tp_cobertura;
	private String tipo_cobertura;
	private int FK_benef_saude;
	private int FK_tipo_cobertura;
//	------------------------------------ fim Cobertura
	
	public int getId_tp_cobertura() {
		return id_tp_cobertura;
	}
	public void setId_tp_cobertura(int id_tp_cobertura) {
		this.id_tp_cobertura = id_tp_cobertura;
	}
	public String getTipo_cobertura() {
		return tipo_cobertura;
	}
	public void setTipo_cobertura(String tipo_cobertura) {
		this.tipo_cobertura = tipo_cobertura;
	}
	public int getFK_benef_saude() {
		return FK_benef_saude;
	}
	public void setFK_benef_saude(int fK_benef_saude) {
		FK_benef_saude = fK_benef_saude;
	}
	public int getFK_tipo_cobertura() {
		return FK_tipo_cobertura;
	}
	public void setFK_tipo_cobertura(int fK_tipo_cobertura) {
		FK_tipo_cobertura = fK_tipo_cobertura;
	}
	
	

}
