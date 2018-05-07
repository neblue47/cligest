package ao.co.cligest.entidades;

import java.io.Serializable;
import java.util.Calendar;

@SuppressWarnings("serial")
public class Ajuste extends Caixa implements Serializable{

	private int id_historico_ajuste;
	private double diferenca_novalor;
	private Calendar data_doajuste;
	private String hora_doajuste;
	private int FK_estatu_doajuste;
	private String comentario_ajs;

	private String numero_fechodecaixa;
	private String numero_dofluxo;
	
	
	
	
	public String getNumero_fechodecaixa() {
		return numero_fechodecaixa;
	}
	public void setNumero_fechodecaixa(String numero_fechodecaixa) {
		this.numero_fechodecaixa = numero_fechodecaixa;
	}
	public String getNumero_dofluxo() {
		return numero_dofluxo;
	}
	public void setNumero_dofluxo(String numero_dofluxo) {
		this.numero_dofluxo = numero_dofluxo;
	}

	public int getId_historico_ajuste() {
		return id_historico_ajuste;
	}
	public void setId_historico_ajuste(int id_historico_ajuste) {
		this.id_historico_ajuste = id_historico_ajuste;
	}
	public double getDiferenca_novalor() {
		return diferenca_novalor;
	}
	public void setDiferenca_novalor(double diferenca_novalor) {
		this.diferenca_novalor = diferenca_novalor;
	}
	public Calendar getData_doajuste() {
		return data_doajuste;
	}
	public void setData_doajuste(Calendar data_doajuste) {
		this.data_doajuste = data_doajuste;
	}
	public String getHora_doajuste() {
		return hora_doajuste;
	}
	public void setHora_doajuste(String hora_doajuste) {
		this.hora_doajuste = hora_doajuste;
	}
	public int getFK_estatu_doajuste() {
		return FK_estatu_doajuste;
	}
	public void setFK_estatu_doajuste(int fK_estatu_doajuste) {
		FK_estatu_doajuste = fK_estatu_doajuste;
	}
	public String getComentario_ajs() {
		return comentario_ajs;
	}
	public void setComentario_ajs(String comentario_ajs) {
		this.comentario_ajs = comentario_ajs;
	}
	
	
}
