package ao.co.cligest.filter;

import java.io.Serializable;
import java.sql.Date;

public class AgendaConsultaFilter implements Serializable {

	private static final long serialVersionUID = 1L;
	
	private String nomPaciente;
	private String nomDoutor;
	private int servicoId;
	private Date dataConsulta;
	
	public String getNomPaciente() {
		return nomPaciente;
	}
	public void setNomPaciente(String nomPaciente) {
		this.nomPaciente = nomPaciente;
	}
	public String getNomDoutor() {
		return nomDoutor;
	}
	public void setNomDoutor(String nomDoutor) {
		this.nomDoutor = nomDoutor;
	}
	public int getServicoId() {
		return servicoId;
	}
	public void setServicoId(int servicoId) {
		this.servicoId = servicoId;
	}
	public Date getDataConsulta() {
		return dataConsulta;
	}
	public void setDataConsulta(Date dataConsulta) {
		this.dataConsulta = dataConsulta;
	}
	public AgendaConsultaFilter(String nomPaciente, String nomDoutor, int servicoId, Date dataConsulta) {
		super();
		this.nomPaciente = nomPaciente;
		this.nomDoutor = nomDoutor;
		this.servicoId = servicoId;
		this.dataConsulta = dataConsulta;
	}
	public AgendaConsultaFilter() {
		super();
		this.nomPaciente = nomPaciente;
		this.nomDoutor = nomDoutor;
		this.servicoId = servicoId;
		this.dataConsulta = dataConsulta;
	}
	
}
