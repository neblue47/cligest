package ao.co.cligest.beans;

public class MultiPagamento {

	private String tipo_pagamento;
	private double quantia_paga;
	private int FK_factura;
	
	public String getTipo_pagamento() {
		return tipo_pagamento;
	}
	public void setTipo_pagamento(String tipo_pagamento) {
		this.tipo_pagamento = tipo_pagamento;
	}
	public double getQuantia_paga() {
		return quantia_paga;
	}
	public void setQuantia_paga(double quantia_paga) {
		this.quantia_paga = quantia_paga;
	}
	public int getFK_factura() {
		return FK_factura;
	}
	public void setFK_factura(int fK_factura) {
		FK_factura = fK_factura;
	}
	
	
}
