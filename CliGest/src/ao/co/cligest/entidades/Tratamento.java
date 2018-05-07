package ao.co.cligest.entidades;

@SuppressWarnings("serial")
public class Tratamento extends Facturacao {

	private int id_facturar_tratamento;
	private double preco_tratamento;
	private double imposto_selo;
	private double taxa_emkz;
	private double total_pago_tratamento;
	private int quantidade;
	private String funcionario;
	private int FK_devolucao_venda;
	
	public int getId_facturar_tratamento() {
		return id_facturar_tratamento;
	}
	public void setId_facturar_tratamento(int id_facturar_tratamento) {
		this.id_facturar_tratamento = id_facturar_tratamento;
	}
	public double getPreco_tratamento() {
		return preco_tratamento;
	}
	public void setPreco_tratamento(double preco_tratamento) {
		this.preco_tratamento = preco_tratamento;
	}
	public double getImposto_selo() {
		return imposto_selo;
	}
	public void setImposto_selo(double imposto_selo) {
		this.imposto_selo = imposto_selo;
	}
	public double getTaxa_emkz() {
		return taxa_emkz;
	}
	public void setTaxa_emkz(double taxa_emkz) {
		this.taxa_emkz = taxa_emkz;
	}
	public double getTotal_pago_tratamento() {
		return total_pago_tratamento;
	}
	public void setTotal_pago_tratamento(double total_pago_tratamento) {
		this.total_pago_tratamento = total_pago_tratamento;
	}
	public int getQuantidade() {
		return quantidade;
	}
	public void setQuantidade(int quantidade) {
		this.quantidade = quantidade;
	}
	public String getFuncionario() {
		return funcionario;
	}
	public void setFuncionario(String funcionario) {
		this.funcionario = funcionario;
	}
	public int getFK_devolucao_venda() {
		return FK_devolucao_venda;
	}
	public void setFK_devolucao_venda(int fK_devolucao_venda) {
		FK_devolucao_venda = fK_devolucao_venda;
	}
	
	
}
