package ao.co.cligest.entidades;

public class FacturaAtrasos extends Facturacao{

	private int id_factura_atraso;
	private double total_exame;
	private double total_procedimento;
	private double total_medicamento;
	private double total_material;
	private double total_diaria;
	private double total_geral;
	private double total_preco;
	private String tipo_pagamento;
	private int FK_procedimento;
	private int FK_medicamento;
	private int FK_material;
	
	 
	
	public int getId_factura_atraso() {
		return id_factura_atraso;
	}
	public void setId_factura_atraso(int id_factura_atraso) {
		this.id_factura_atraso = id_factura_atraso;
	}
	public double getTotal_exame() {
		return total_exame;
	}
	public void setTotal_exame(double total_exame) {
		this.total_exame = total_exame;
	}
	public double getTotal_procedimento() {
		return total_procedimento;
	}
	public void setTotal_procedimento(double total_procedimento) {
		this.total_procedimento = total_procedimento;
	}
	public double getTotal_medicamento() {
		return total_medicamento;
	}
	public void setTotal_medicamento(double total_medicamento) {
		this.total_medicamento = total_medicamento;
	}
	public double getTotal_diaria() {
		return total_diaria;
	}
	public void setTotal_diaria(double total_diaria) {
		this.total_diaria = total_diaria;
	}
	public double getTotal_geral() {
		return total_geral;
	}
	public void setTotal_geral(double total_geral) {
		this.total_geral = total_geral;
	}
	public String getTipo_pagamento() {
		return tipo_pagamento;
	}
	public void setTipo_pagamento(String tipo_pagamento) {
		this.tipo_pagamento = tipo_pagamento;
	}
	public double getTotal_material() {
		return total_material;
	}
	public void setTotal_material(double total_material) {
		this.total_material = total_material;
 	}
	 
	public double getTotal_preco() {
		return total_preco;
	}
	public void setTotal_preco(double total_preco) {
		this.total_preco = total_preco;
	}
	public int getFK_procedimento() {
		return FK_procedimento;
	}
	public void setFK_procedimento(int fK_procedimento) {
		FK_procedimento = fK_procedimento;
	}
	public int getFK_medicamento() {
		return FK_medicamento;
	}
	public void setFK_medicamento(int fK_medicamento) {
		FK_medicamento = fK_medicamento;
	}
	public int getFK_material() {
		return FK_material;
	}
	public void setFK_material(int fK_material) {
		FK_material = fK_material;
	}
	
	
}
