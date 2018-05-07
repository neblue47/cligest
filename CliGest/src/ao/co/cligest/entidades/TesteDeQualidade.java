package ao.co.cligest.entidades;

import java.io.Serializable;

public class TesteDeQualidade implements Serializable{
	
	private static final long serialVersionUID = 1L;
	private int id_servico_tq;
	private int FK_categoria_tq;
	private String serv_teste_qualidade;
	private int FK_taxa;
	private String taxa;
	private double taxaemKZ;
	private double preco;
	private double total;
	private String descricao_tq;
	private String empresa;
	private int quantidade;
	private String nome_completo;;
	
	private int id_agen_test_quali;
	private String numero_doservico;
	private int FK_funcionario;
	private int FK_serv_test_quali;
	private int FK_empresa;
	private int FK_dia_dasemana;
	private java.sql.Date data;
	private java.sql.Time hora;
	private String tipo_empresa;
	
	private int id_fac_test_quali_cash;
	private int FK_factura;
	private int FK_requisicao;
	private double preco_unitario;
	private int taxa_percentual;
	private double taxa_emkz;
	private double total_pago;
	private int FK_servico_tq;	
	private int id_fac_test_quali_multicaixa;

	
	public String getNome_completo() {
		return nome_completo;
	}
	public void setNome_completo(String nome_completo) {
		this.nome_completo = nome_completo;
	}
	public int getQuantidade() {
		return quantidade;
	}
	public void setQuantidade(int quantidade) {
		this.quantidade = quantidade;
	}
	public int getId_servico_tq() {
		return id_servico_tq;
	}
	public void setId_servico_tq(int id_servico_tq) {
		this.id_servico_tq = id_servico_tq;
	}
	public int getFK_categoria_tq() {
		return FK_categoria_tq;
	}
	public void setFK_categoria_tq(int fK_categoria_tq) {
		FK_categoria_tq = fK_categoria_tq;
	}
	public String getServ_teste_qualidade() {
		return serv_teste_qualidade;
	}
	public void setServ_teste_qualidade(String serv_teste_qualidade) {
		this.serv_teste_qualidade = serv_teste_qualidade;
	}
	public int getFK_taxa() {
		return FK_taxa;
	}
	public void setFK_taxa(int fK_taxa) {
		FK_taxa = fK_taxa;
	}
	public double getPreco() {
		return preco;
	}
	public void setPreco(double preco) {
		this.preco = preco;
	}
	public String getDescricao_tq() {
		return descricao_tq;
	}
	public void setDescricao_tq(String descricao_tq) {
		this.descricao_tq = descricao_tq;
	}
	public int getId_agen_test_quali() {
		return id_agen_test_quali;
	}
	public void setId_agen_test_quali(int id_agen_test_quali) {
		this.id_agen_test_quali = id_agen_test_quali;
	}
	public String getNumero_doservico() {
		return numero_doservico;
	}
	public void setNumero_doservico(String numero_doservico) {
		this.numero_doservico = numero_doservico;
	}
	public int getFK_funcionario() {
		return FK_funcionario;
	}
	public void setFK_funcionario(int fK_funcionario) {
		FK_funcionario = fK_funcionario;
	}
	public int getFK_serv_test_quali() {
		return FK_serv_test_quali;
	}
	public void setFK_serv_test_quali(int fK_serv_test_quali) {
		FK_serv_test_quali = fK_serv_test_quali;
	}
	public int getFK_empresa() {
		return FK_empresa;
	}
	public void setFK_empresa(int fK_empresa) {
		FK_empresa = fK_empresa;
	}
	public int getFK_dia_dasemana() {
		return FK_dia_dasemana;
	}
	public void setFK_dia_dasemana(int fK_dia_dasemana) {
		FK_dia_dasemana = fK_dia_dasemana;
	}
	public java.sql.Date getData() {
		return data;
	}
	public void setData(java.sql.Date data) {
		this.data = data;
	}
	public java.sql.Time getHora() {
		return hora;
	}
	public void setHora(java.sql.Time hora) {
		this.hora = hora;
	}
	public String getTipo_empresa() {
		return tipo_empresa;
	}
	public void setTipo_empresa(String tipo_empresa) {
		this.tipo_empresa = tipo_empresa;
	}
	public String getTaxa() {
		return taxa;
	}
	public void setTaxa(String taxa) {
		this.taxa = taxa;
	}
	public double getTaxaemKZ() {
		return taxaemKZ;
	}
	public void setTaxaemKZ(double taxaemKZ) {
		this.taxaemKZ = taxaemKZ;
	}
	public double getTotal() {
		return total;
	}
	public void setTotal(double total) {
		this.total = total;
	}
	public String getEmpresa() {
		return empresa;
	}
	public void setEmpresa(String empresa) {
		this.empresa = empresa;
	}
	public int getId_fac_test_quali_cash() {
		return id_fac_test_quali_cash;
	}
	public void setId_fac_test_quali_cash(int id_fac_test_quali_cash) {
		this.id_fac_test_quali_cash = id_fac_test_quali_cash;
	}
	public int getFK_factura() {
		return FK_factura;
	}
	public void setFK_factura(int fK_factura) {
		FK_factura = fK_factura;
	}
	public int getFK_requisicao() {
		return FK_requisicao;
	}
	public void setFK_requisicao(int fK_requisicao) {
		FK_requisicao = fK_requisicao;
	}
	public double getPreco_unitario() {
		return preco_unitario;
	}
	public void setPreco_unitario(double preco_unitario) {
		this.preco_unitario = preco_unitario;
	}
	public int getTaxa_percentual() {
		return taxa_percentual;
	}
	public void setTaxa_percentual(int taxa_percentual) {
		this.taxa_percentual = taxa_percentual;
	}
	public double getTaxa_emkz() {
		return taxa_emkz;
	}
	public void setTaxa_emkz(double taxa_emkz) {
		this.taxa_emkz = taxa_emkz;
	}
	public double getTotal_pago() {
		return total_pago;
	}
	public void setTotal_pago(double total_pago) {
		this.total_pago = total_pago;
	}
	public int getFK_servico_tq() {
		return FK_servico_tq;
	}
	public void setFK_servico_tq(int fK_servico_tq) {
		FK_servico_tq = fK_servico_tq;
	}
	public int getId_fac_test_quali_multicaixa() {
		return id_fac_test_quali_multicaixa;
	}
	public void setId_fac_test_quali_multicaixa(int id_fac_test_quali_multicaixa) {
		this.id_fac_test_quali_multicaixa = id_fac_test_quali_multicaixa;
	}
	
	
}
