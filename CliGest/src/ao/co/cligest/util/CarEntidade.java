package ao.co.cligest.util;

import java.io.Serializable;

@SuppressWarnings("serial")
public class CarEntidade implements Serializable{

	 
	
	private String cod_id;
	private String desc_cid;
	private String preco_c;
	
	private int id_produto;
	private String id_produtos;
	private int paciente;
	private String farmaco ;
	private String id_via ;
	private String viadmin ;
	private String posolog ;
	private String quantdd ;
	private String dosage;
	private String formaf;
	private String data;
	private String numero;
	
	
	public String getData() {
		return data;
	}
	public void setData(String data) {
		this.data = data;
	}
	public String getFormaf() {
		return formaf;
	}
	public void setFormaf(String formaf) {
		this.formaf = formaf;
	}
	private String id_dosage;
	private int indice;
	private String lote;
	
	private String id_exame;
	private String nome_exa;
	private String preco_exa;
	private String grupo_exa;
	
	/*Tabela grupo de exames*/
	private int FK_grupo_analise_clinica;
	private String analise_clinica;
	private int preco;
	private int id_servicodeanalise_clinica;
	private String preco_clinico; 
	
	// Tabela Prodecimentos, Material e Medicamento no Internamento e BUE
	private int id_procedimento;
	private String procedimento;
	private int id_material;
	private String material;
	private int id_medicamento;
	private String medicamento;
	
	private int dia;
	
	//para servico de qualidade
	private String nome_servico_qual;
	private String id_servico_qual;
	
//	Atribuição de beneficios
	private String id_servic;
	private String nome_servico;
	private String preco_servico;
	
	/*Tabela de beneficios*/
//----------------------------------------- serviço analise
	private int id_servicodeanalise;
	private int fk_fornecedor;
	private int fk_laboratorio;
	
	
//	tblfacturartratamento
	private int id_facturar_tratamento;
	private int FK_factura;
	private int FK_tratamento;
	private double preco_tratamento;
	private int imposto_selo;
	private double taxa_emkz;
	private double taxa;
	private double total_pago_tratamento;
	private int quantidade;
	
	private String num_lote;
	private int FK_isencao;
	private int FK_receita;
    private int FK_farmaco_isentado;
    private double preco_isencao;
    private int qtd_farmaco_isentado;
    private double tx_farmaco_isentado;
    private double total_tx_farm_isent;
    private double tota_farmaco_isentado;
//	--------------------------------------- serviço analise excluido
    
    private String codigo_barra;
	
public String getCodigo_barra() {
		return codigo_barra;
	}
	public void setCodigo_barra(String codigo_barra) {
		this.codigo_barra = codigo_barra;
	}
public int getId_facturar_tratamento() {
		return id_facturar_tratamento;
	}
	public String getId_produtos() {
	return id_produtos;
}
public void setId_produtos(String id_produtos) {
	this.id_produtos = id_produtos;
}
	public void setId_facturar_tratamento(int id_facturar_tratamento) {
		this.id_facturar_tratamento = id_facturar_tratamento;
	}
	public int getFK_factura() {
		return FK_factura;
	}
	public void setFK_factura(int fK_factura) {
		FK_factura = fK_factura;
	}
	public int getFK_tratamento() {
		return FK_tratamento;
	}
	public void setFK_tratamento(int fK_tratamento) {
		FK_tratamento = fK_tratamento;
	}
	public double getPreco_tratamento() {
		return preco_tratamento;
	}
	public void setPreco_tratamento(double preco_tratamento) {
		this.preco_tratamento = preco_tratamento;
	}
	public int getImposto_selo() {
		return imposto_selo;
	}
	public void setImposto_selo(int imposto_selo) {
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
	public int getFk_fornecedor() {
		return fk_fornecedor;
	}
	public void setFk_fornecedor(int fk_fornecedor) {
		this.fk_fornecedor = fk_fornecedor;
	}
	public int getFk_laboratorio() {
		return fk_laboratorio;
	}
	public void setFk_laboratorio(int fk_laboratorio) {
		this.fk_laboratorio = fk_laboratorio;
	}
	//	--------------------------------------- Serviço
	private int id_servico;
	private int FK_tipo_de_servico;
	private int FK_especialidade;
	private String servico;
	private String descricao;
	private double preco_s;
	private double total_pago;
//	--------------------------------------- Serviço final
	
	
	
	public String getId_exame() {
		return id_exame;
	}
	public int getDia() {
		return dia;
	}
	public void setDia(int dia) {
		this.dia = dia;
	}
	public void setId_exame(String id_exame) {
		this.id_exame = id_exame;
	}
	public String getNome_exa() {
		return nome_exa;
	}
	public void setNome_exa(String nome_exa) {
		this.nome_exa = nome_exa;
	}
	public int getIndice() {
		return indice;
	}
	public void setIndice(int indice) {
		this.indice = indice;
	}
	public String getCod_id() {
		return cod_id;
	}
	public void setCod_id(String cod_id) {
		this.cod_id = cod_id;
	}
	public String getDesc_cid() {
		return desc_cid;
	}
	public void setDesc_cid(String desc_cid) {
		this.desc_cid = desc_cid;
	}
	public String getFarmaco() {
		return farmaco;
	}
	public void setFarmaco(String farmaco) {
		this.farmaco = farmaco;
	}
	public String getViadmin() {
		return viadmin;
	}
	public void setViadmin(String viadmin) {
		this.viadmin = viadmin;
	}
	public String getPosolog() {
		return posolog;
	}
	public void setPosolog(String posolog) {
		this.posolog = posolog;
	}
	public String getQuantdd() {
		return quantdd;
	}
	public void setQuantdd(String quantdd) {
		this.quantdd = quantdd;
	}
	public String getDosage() {
		return dosage;
	}
	public void setDosage(String dosage) {
		this.dosage = dosage;
	}
	public String getId_via() {
		return id_via;
	}
	public void setId_via(String id_via) {
		this.id_via = id_via;
	}
	public String getId_dosage() {
		return id_dosage;
	}
	public void setId_dosage(String id_dosage) {
		this.id_dosage = id_dosage;
	}
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
	public int getPreco() {
		return preco;
	}
	public void setPreco(int preco) {
		this.preco = preco;
	}
	public int getId_servicodeanalise_clinica() {
		return id_servicodeanalise_clinica;
	}
	public void setId_servicodeanalise_clinica(int id_servicodeanalise_clinica) {
		this.id_servicodeanalise_clinica = id_servicodeanalise_clinica;
	}
	public String getPreco_exa() {
		return preco_exa;
	}
	public void setPreco_exa(String preco_exa) {
		this.preco_exa = preco_exa;
	}
	public String getGrupo_exa() {
		return grupo_exa;
	}
	public void setGrupo_exa(String grupo_exa) {
		this.grupo_exa = grupo_exa;
	}
	public String getPreco_clinico() {
		return preco_clinico;
	}
	public void setPreco_clinico(String preco_clinico) {
		this.preco_clinico = preco_clinico;
	}
	public String getId_servic() {
		return id_servic;
	}
	public void setId_servic(String id_servic) {
		this.id_servic = id_servic;
	}
	public String getNome_servico() {
		return nome_servico;
	}
	public void setNome_servico(String nome_servico) {
		this.nome_servico = nome_servico;
	}
	public String getPreco_servico() {
		return preco_servico;
	}
	public void setPreco_servico(String preco_servico) {
		this.preco_servico = preco_servico;
	}
	public int getId_servicodeanalise() {
		return id_servicodeanalise;
	}
	public void setId_servicodeanalise(int id_servicodeanalise) {
		this.id_servicodeanalise = id_servicodeanalise;
	}
	public int getId_servico() {
		return id_servico;
	}
	public void setId_servico(int id_servico) {
		this.id_servico = id_servico;
	}
	public int getFK_tipo_de_servico() {
		return FK_tipo_de_servico;
	}
	public void setFK_tipo_de_servico(int fK_tipo_de_servico) {
		FK_tipo_de_servico = fK_tipo_de_servico;
	}
	public int getFK_especialidade() {
		return FK_especialidade;
	}
	public void setFK_especialidade(int fK_especialidade) {
		FK_especialidade = fK_especialidade;
	}
	public String getServico() {
		return servico;
	}
	public void setServico(String servico) {
		this.servico = servico;
	}
	public String getDescricao() {
		return descricao;
	}
	public void setDescricao(String descricao) {
		this.descricao = descricao;
	}
	public double getPreco_s() {
		return preco_s;
	}
	public void setPreco_s(double preco_s) {
		this.preco_s = preco_s;
	}
	public String getPreco_c() {
		return preco_c;
	}
	public void setPreco_c(String preco_c) {
		this.preco_c = preco_c;
	}
	public String getLote() {
		return lote;
	}
	public void setLote(String lote) {
		this.lote = lote;
	}
	public int getId_produto() {
		return id_produto;
	}
	public void setId_produto(int id_produto) {
		this.id_produto = id_produto;
	}
	public double getTaxa() {
		return taxa;
	}
	public void setTaxa(double taxa) {
		this.taxa = taxa;
	}
	public double getTotal_pago() {
		return total_pago;
	}
	public void setTotal_pago(double total_pago) {
		this.total_pago = total_pago;
	}
	public String getNum_lote() {
		return num_lote;
	}
	public void setNum_lote(String num_lote) {
		this.num_lote = num_lote;
	}
	public int getPaciente() {
		return paciente;
	}
	public void setPaciente(int paciente) {
		this.paciente = paciente;
	}
	public int getFK_isencao() {
		return FK_isencao;
	}
	public void setFK_isencao(int fK_isencao) {
		FK_isencao = fK_isencao;
	}
	public int getFK_farmaco_isentado() {
		return FK_farmaco_isentado;
	}
	public void setFK_farmaco_isentado(int fK_farmaco_isentado) {
		FK_farmaco_isentado = fK_farmaco_isentado;
	}
	public double getPreco_isencao() {
		return preco_isencao;
	}
	public void setPreco_isencao(double preco_isencao) {
		this.preco_isencao = preco_isencao;
	}
	public int getQtd_farmaco_isentado() {
		return qtd_farmaco_isentado;
	}
	public void setQtd_farmaco_isentado(int qtd_farmaco_isentado) {
		this.qtd_farmaco_isentado = qtd_farmaco_isentado;
	}
	public double getTx_farmaco_isentado() {
		return tx_farmaco_isentado;
	}
	public void setTx_farmaco_isentado(double tx_farmaco_isentado) {
		this.tx_farmaco_isentado = tx_farmaco_isentado;
	}
	public double getTotal_tx_farm_isent() {
		return total_tx_farm_isent;
	}
	public void setTotal_tx_farm_isent(double total_tx_farm_isent) {
		this.total_tx_farm_isent = total_tx_farm_isent;
	}
	public double getTota_farmaco_isentado() {
		return tota_farmaco_isentado;
	}
	public void setTota_farmaco_isentado(double tota_farmaco_isentado) {
		this.tota_farmaco_isentado = tota_farmaco_isentado;
	}
	public int getFK_receita() {
		return FK_receita;
	}
	public void setFK_receita(int fK_receita) {
		FK_receita = fK_receita;
	}
	
	private double subTotal;

	public double getSubTotal() {
		return subTotal;
	}
	public void setSubTotal(double subTotal) {
		this.subTotal = subTotal;
	}
	public int getId_procedimento() {
		return id_procedimento;
	}
	public void setId_procedimento(int id_procedimento) {
		this.id_procedimento = id_procedimento;
	}
	public String getProcedimento() {
		return procedimento;
	}
	public void setProcedimento(String procedimento) {
		this.procedimento = procedimento;
	}
	public int getId_material() {
		return id_material;
	}
	public void setId_material(int id_material) {
		this.id_material = id_material;
	}
	public String getMaterial() {
		return material;
	}
	public void setMaterial(String material) {
		this.material = material;
	}
	public int getId_medicamento() {
		return id_medicamento;
	}
	public void setId_medicamento(int id_medicamento) {
		this.id_medicamento = id_medicamento;
	}
	public String getMedicamento() {
		return medicamento;
	}
	public void setMedicamento(String medicamento) {
		this.medicamento = medicamento;
	}
	public String getNumero() {
		return numero;
	}
	public void setNumero(String numero) {
		this.numero = numero;
	}
	
	
}
