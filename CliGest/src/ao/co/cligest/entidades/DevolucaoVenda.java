package ao.co.cligest.entidades;

import java.util.Calendar;

@SuppressWarnings("serial")
public class DevolucaoVenda extends Exames {

	private int id_devolucao_venda;
	private String numero_devolucao;
	private int FK_factura;
	private int FK_funcionario;
	private Calendar data_devolucao_exame;
	private String hora_devolucao_exame;
	private java.sql.Date data;
	private int FK_paciente;
	private String razaodadevolucao;
	private int FK_servico_exame_devolvido;
	private int FK_devolucao_venda;
	
	//
	private int FK_exame_requisitado;
	private int fk_requisicao_dexame;
	private int taxa;
	private double taxa_pre;
	private double taxa_devolucao;
	private String servico;
	private double total_devolvido;
	
	private int id_produto_devolvido;
	private Calendar data_pro_devolvido;
	private String hora_pro_devolvido;
	private int fk_produto;
	private double preco_pro_devolvido;
	private int qtd_pro_devolvido;
	private double txa_pro_devolvido;
	private double subt_pro_devolvido;
	private int fk_venda_devolvida;
	private int FK_empresa;
	private String tipo_servico;
 
	public void setTaxa_pre(double taxa_pre) {
		this.taxa_pre = taxa_pre;
	}	 
	public double getTaxa_devolucao() {
		return taxa_devolucao;
	}


	public void setTaxa_devolucao(double taxa_devolucao) {
		this.taxa_devolucao = taxa_devolucao;
	}


	public int getFK_exame_requisitado() {
		return FK_exame_requisitado;
	}
	public void setFK_exame_requisitado(int fK_exame_requisitado) {
		FK_exame_requisitado = fK_exame_requisitado;
	}
	public int getFk_requisicao_dexame() {
		return fk_requisicao_dexame;
	}
	public void setFk_requisicao_dexame(int fk_requisicao_dexame) {
		this.fk_requisicao_dexame = fk_requisicao_dexame;
	}
	 
	public void setTaxa(int taxa) {
		this.taxa = taxa;
	}
	public String getServico() {
		return servico;
	}
	public void setServico(String servico) {
		this.servico = servico;
	}
	public int getFK_devolucao_venda() {
		return FK_devolucao_venda;
	}
	public void setFK_devolucao_venda(int fK_devolucao_venda) {
		FK_devolucao_venda = fK_devolucao_venda;
	}
	public int getId_devolucao_venda() {
		return id_devolucao_venda;
	}
	public void setId_devolucao_venda(int id_devolucao_venda) {
		this.id_devolucao_venda = id_devolucao_venda;
	}
	public String getNumero_devolucao() {
		return numero_devolucao;
	}
	public void setNumero_devolucao(String numero_devolucao) {
		this.numero_devolucao = numero_devolucao;
	}
	public int getFK_factura() {
		return FK_factura;
	}
	public void setFK_factura(int fK_factura) {
		FK_factura = fK_factura;
	}
	public int getFK_funcionario() {
		return FK_funcionario;
	}
	public void setFK_funcionario(int fK_funcionario) {
		FK_funcionario = fK_funcionario;
	}
	public Calendar getData_devolucao_exame() {
		return data_devolucao_exame;
	}
	public void setData_devolucao_exame(Calendar data_devolucao_exame) {
		this.data_devolucao_exame = data_devolucao_exame;
	}
	public String getHora_devolucao_exame() {
		return hora_devolucao_exame;
	}
	public void setHora_devolucao_exame(String hora_devolucao_exame) {
		this.hora_devolucao_exame = hora_devolucao_exame;
	}
	public int getFK_paciente() {
		return FK_paciente;
	}
	public void setFK_paciente(int fK_paciente) {
		FK_paciente = fK_paciente;
	}
	public String getRazaodadevolucao() {
		return razaodadevolucao;
	}
	public void setRazaodadevolucao(String razaodadevolucao) {
		this.razaodadevolucao = razaodadevolucao;
	}
	public int getFK_servico_exame_devolvido() {
		return FK_servico_exame_devolvido;
	}
	public void setFK_servico_exame_devolvido(int fK_servico_exame_devolvido) {
		FK_servico_exame_devolvido = fK_servico_exame_devolvido;
	}
//	---------------------------
	private int id_servicodeanalise_clinica;
	private int FK_grupo_analise_clinica;
	private String analise_clinica;
	private String descricao_servico_analise;
	private int codigobarra;

	public int getFK_grupo_analise_clinica() {
		return FK_grupo_analise_clinica;
	}
	public void setFK_grupo_analise_clinica(int fK_grupo_analise_clinica) {
		FK_grupo_analise_clinica = fK_grupo_analise_clinica;
	}
	public String getAnalise_clinica() {
		return analise_clinica;
	}
	public void setAnalise_clinica(String analise_clinica) {
		this.analise_clinica = analise_clinica;
	}
	public String getDescricao_servico_analise() {
		return descricao_servico_analise;
	}
	public void setDescricao_servico_analise(String descricao_servico_analise) {
		this.descricao_servico_analise = descricao_servico_analise;
	}
	public int getCodigobarra() {
		return codigobarra;
	}
	public void setCodigobarra(int codigobarra) {
		this.codigobarra = codigobarra;
	}
	public java.sql.Date getData() {
		return data;
	}
	public void setData(java.sql.Date data) {
		this.data = data;
	}
	public double getTotal_devolvido() {
		return total_devolvido;
	}
	public void setTotal_devolvido(double total_devolvido) {
		this.total_devolvido = total_devolvido;
	}
	public int getId_produto_devolvido() {
		return id_produto_devolvido;
	}
	public void setId_produto_devolvido(int id_produto_devolvido) {
		this.id_produto_devolvido = id_produto_devolvido;
	}
	public Calendar getData_pro_devolvido() {
		return data_pro_devolvido;
	}
	public void setData_pro_devolvido(Calendar data_pro_devolvido) {
		this.data_pro_devolvido = data_pro_devolvido;
	}
	public String getHora_pro_devolvido() {
		return hora_pro_devolvido;
	}
	public void setHora_pro_devolvido(String hora_pro_devolvido) {
		this.hora_pro_devolvido = hora_pro_devolvido;
	}
	public int getFk_produto() {
		return fk_produto;
	}
	public void setFk_produto(int fk_produto) {
		this.fk_produto = fk_produto;
	}
	public double getPreco_pro_devolvido() {
		return preco_pro_devolvido;
	}
	public void setPreco_pro_devolvido(double preco_pro_devolvido) {
		this.preco_pro_devolvido = preco_pro_devolvido;
	}
	public int getQtd_pro_devolvido() {
		return qtd_pro_devolvido;
	}
	public void setQtd_pro_devolvido(int qtd_pro_devolvido) {
		this.qtd_pro_devolvido = qtd_pro_devolvido;
	}
	public double getTxa_pro_devolvido() {
		return txa_pro_devolvido;
	}
	public void setTxa_pro_devolvido(double txa_pro_devolvido) {
		this.txa_pro_devolvido = txa_pro_devolvido;
	}
	public double getSubt_pro_devolvido() {
		return subt_pro_devolvido;
	}
	public void setSubt_pro_devolvido(double subt_pro_devolvido) {
		this.subt_pro_devolvido = subt_pro_devolvido;
	}
	public int getFk_venda_devolvida() {
		return fk_venda_devolvida;
	}
	public void setFk_venda_devolvida(int fk_venda_devolvida) {
		this.fk_venda_devolvida = fk_venda_devolvida;
	}
	public int getFK_empresa() {
		return FK_empresa;
	}
	public void setFK_empresa(int fK_empresa) {
		FK_empresa = fK_empresa;
	}
	public String getTipo_servico() {
		return tipo_servico;
	}
	public void setTipo_servico(String tipo_servico) {
		this.tipo_servico = tipo_servico;
	}
	
	
}
