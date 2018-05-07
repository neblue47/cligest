package ao.co.cligest.entidades;

import java.util.Calendar;

@SuppressWarnings("serial")
public class Facturacao  extends Paciente{
	
	/**
	 * 
	 */
	 
	private String numero_factura;
	private int FK_paciente;
	private int FK_confirmacao_do_agendamento;
	private int FK_funcionario;
	private int FK_servico;
	private int FK_doutor;
	private double preco;
	private int quantidade;
	private double totais; 
	private String Metodo_dpagamento;
	private int FK_Metodo_dpagamento;
	private double desconto;
	private int id_consulta_confirmada;
	private int FK_numero_do_servico_de_exame;
	private Calendar data_inicio;
	private Calendar data_fim;
	private double taxa_percentual;
	private double taxa;
	private int FK_aberturadecaixa;
	private int FK_tipo_deservico;
	private String tipo_deservico;
	private int FK_isencao;
	private int FK_requisicao_dexame;
	private double total_factura;
	private int FK_fornecedor;
	private String codigo_barra;
	 
	/*Factura multicaixa*/
	private int FK_factura;
	private int FK_banco;
	private int ter;
	private int m;
	private int numero_cartao;
	
	private int FK_categoria;
	private String numero_daisencao;
	private int titular;
	
	public int getFK_categoria() {
		return FK_categoria;
	}
	public void setFK_categoria(int fK_categoria) {
		FK_categoria = fK_categoria;
	}
	public String getNumero_daisencao() {
		return numero_daisencao;
	}
	public void setNumero_daisencao(String numero_daisencao) {
		this.numero_daisencao = numero_daisencao;
	}
	private int identificacao_unica_do_exame,data,FK_servico_exame,gestante_pra_exame;
	
	/**
	 * Consulta Isentada Tabela
	 */
	private int FK_fac_isentada;
	private double taxa_porcento;
	private double tot_tax_emkz;
	private double total_isentado;
 
	/*consulta cancelada*/
	private String descricao_do_canselamento;
 
	
	private int FK_aseguradora;
	private int FK_tipo_dcobertura;
	
//	tblfacturartratamento
	private int id_facturar_tratamento;
	private int FK_tratamento;
	private double preco_tratamento;
	private double imposto_selo;
	private double taxa_emkz;
	private double total_pago_tratamento;
	private int quantdd;
	
	// tblfacturarprodutos 
	private int id_produto_fact;
	private int FK_produto;
	private double taxa_perc_produto;
	private double preco_produto;
	private double tx_emkz_produto;
	private double tot_pago_produto;
	private String numLote;
	private String maquina;
	
	// tblfacturaprodutoisentado 
	private int id_prod_isentado;
	private int qtd_produto;
	private double t_isen_produto;
	private int FK_receita;
	
	// tbldetalhesdarecitafacturada
	private int id_deta_recei_facturada;
	private String nome_medico;
	private String hospital;
	
	// tblfacturaisencaoporidadeservicogerais
	private int id_isen_idade_s_geral;
	private int fk_isen_principal;
	private double isen_idade_s_g_preco;
	private int isen_idade_s_g_qtd;
	private double isen_idade_s_g_taxa_perc;
	private double isen_idade_s_g_taxal_emkz;
	private double isen_idade_s_g_total_pago;
	
	// tblfacturademulticaixaservicosgerais
	private int id_fac_multicaixa;
	private double sg_mcaixa_preco;
	private int sg_mcaixa_qtd;
	private double sg_mcaixa_taxa_perc;
	private double sg_mcaixa_taxa_emkz;
	private double sg_mcaixa_total;
	
	// tblfacturademulticaixaexames
	private double fac_exame_mcaixa_preco;
	private double fac_exame_mcaixa_taxa_perc;
	private double fac_exame_mcaixa_taxa_emkz;
	private double fac_exame_mcaixa_total;
	
	private int FK_plano_dsaude;
	private double sg_seguro_preco;
	private int sg_seguro_qtd;
	private double sg_seguro_taxa_perc;
	private double sg_seguro_taxa_emkz;
	private double sg_seguro_total;
	
	private double ex_seguro_preco;
	private int ex_seguro_qtd;
	private double ex_seguro_taxa_perc;
	private double ex_seguro_taxa_emkz;
	private double ex_seguro_total;
	
	private int id_multi_pagamentos;
	private String tipo_pagamento;
	private double quantia_paga;	
	
	private int id_factura_plano_saude;
	private double comparticipacao_aseguradora;
	private double comparticipacao_paciente;
	private double total_pago_plano_saude;
	
	public String getCodigo_barra() {
		return codigo_barra;
	}
	public void setCodigo_barra(String codigo_barra) {
		this.codigo_barra = codigo_barra;
	}
	public int getFK_requisicao_dexame() {
		return FK_requisicao_dexame;
	}
	public void setFK_requisicao_dexame(int fK_requisicao_dexame) {
		FK_requisicao_dexame = fK_requisicao_dexame;
	}
	public double getFac_exame_mcaixa_preco() {
		return fac_exame_mcaixa_preco;
	}
	public void setFac_exame_mcaixa_preco(double fac_exame_mcaixa_preco) {
		this.fac_exame_mcaixa_preco = fac_exame_mcaixa_preco;
	}
	public double getFac_exame_mcaixa_taxa_perc() {
		return fac_exame_mcaixa_taxa_perc;
	}
	public void setFac_exame_mcaixa_taxa_perc(double fac_exame_mcaixa_taxa_perc) {
		this.fac_exame_mcaixa_taxa_perc = fac_exame_mcaixa_taxa_perc;
	}
	public double getFac_exame_mcaixa_taxa_emkz() {
		return fac_exame_mcaixa_taxa_emkz;
	}
	public void setFac_exame_mcaixa_taxa_emkz(double fac_exame_mcaixa_taxa_emkz) {
		this.fac_exame_mcaixa_taxa_emkz = fac_exame_mcaixa_taxa_emkz;
	}
	public double getFac_exame_mcaixa_total() {
		return fac_exame_mcaixa_total;
	}
	public void setFac_exame_mcaixa_total(double fac_exame_mcaixa_total) {
		this.fac_exame_mcaixa_total = fac_exame_mcaixa_total;
	}
	public String getNumero_factura() {
		return numero_factura;
	}
	public void setNumero_factura(String numero_factura) {
		this.numero_factura = numero_factura;
	}
	public int getFK_paciente() {
		return FK_paciente;
	}
	public void setFK_paciente(int fK_paciente) {
		FK_paciente = fK_paciente;
	}
	
	public int getFK_confirmacao_do_agendamento() {
		return FK_confirmacao_do_agendamento;
	}
	public void setFK_confirmacao_do_agendamento(int fK_confirmacao_do_agendamento) {
		FK_confirmacao_do_agendamento = fK_confirmacao_do_agendamento;
	}
	public int getFK_funcionario() {
		return FK_funcionario;
	}
	public void setFK_funcionario(int fK_funcionario) {
		FK_funcionario = fK_funcionario;
	}
	public int getFK_servico() {
		return FK_servico;
	}
	public void setFK_servico(int fK_servico) {
		FK_servico = fK_servico;
	}
	public int getFK_doutor() {
		return FK_doutor;
	}
	public void setFK_doutor(int fK_doutor) {
		FK_doutor = fK_doutor;
	}
	public double getPreco() {
		return preco;
	}
	public void setPreco(double preco) {
		this.preco = preco;
	}
	public int getQuantidade() {
		return quantidade;
	}
	public void setQuantidade(int quantidade) {
		this.quantidade = quantidade;
	}
	public double getTotais() {
		return this.totais;
	}
	public void setTotais(double total) {
		this.totais = total;
	}
	
	public String getMetodo_dpagamento() {
		return Metodo_dpagamento;
	}
	public void setMetodo_dpagamento(String metodo_dpagamento) {
		Metodo_dpagamento = metodo_dpagamento;
	}
	public double getDesconto() {
		return desconto;
	}
	public void setDesconto(double desconto) {
		this.desconto = desconto;
	}
	
	public int getFK_factura() {
		return FK_factura;
	}
	public void setFK_factura(int fK_factura) {
		FK_factura = fK_factura;
	}
	public int getFK_banco() {
		return FK_banco;
	}
	public void setFK_banco(int fK_banco) {
		FK_banco = fK_banco;
	}
	public int getTer() {
		return ter;
	}
	public void setTer(int ter) {
		this.ter = ter;
	}
	public int getM() {
		return m;
	}
	public void setM(int m) {
		this.m = m;
	}
	public int getNumero_cartao() {
		return numero_cartao;
	}
	public void setNumero_cartao(int numero_cartao) {
		this.numero_cartao = numero_cartao;
	}
	/*public int getFK_fornecedor() {
		return FK_fornecedor;
	}
	
	public void setFK_fornecedor(int fK_fornecedor) {
		FK_fornecedor = fK_fornecedor;
	}
	public int getId_tipo_dcobertura() {
		return id_tipo_dcobertura;
	}
	public void setId_tipo_dcobertura(int id_tipo_dcobertura) {
		this.id_tipo_dcobertura = id_tipo_dcobertura;
	}*/
	public int getFK_aseguradora() {
		return FK_aseguradora;
	}
	public void setFK_aseguradora(int fK_aseguradora) {
		FK_aseguradora = fK_aseguradora;
	}
	public int getFK_tipo_dcobertura() {
		return FK_tipo_dcobertura;
	}
	public void setFK_tipo_dcobertura(int fK_tipo_dcobertura) {
		FK_tipo_dcobertura = fK_tipo_dcobertura;
	}
	public int getId_consulta_confirmada() {
		return id_consulta_confirmada;
	}
	public void setId_consulta_confirmada(int id_consulta_confirmada) {
		this.id_consulta_confirmada = id_consulta_confirmada;
	}
	public String getDescricao_do_canselamento() {
		return descricao_do_canselamento;
	}
	public void setDescricao_do_canselamento(String descricao_do_canselamento) {
		this.descricao_do_canselamento = descricao_do_canselamento;
	}
	public int getFK_numero_do_servico_de_exame() {
		return FK_numero_do_servico_de_exame;
	}
	public void setFK_numero_do_servico_de_exame(int fK_numero_do_servico_de_exame) {
		FK_numero_do_servico_de_exame = fK_numero_do_servico_de_exame;
	}
	public int getIdentificacao_unica_do_exame() {
		return identificacao_unica_do_exame;
	}
	public void setIdentificacao_unica_do_exame(int identificacao_unica_do_exame) {
		this.identificacao_unica_do_exame = identificacao_unica_do_exame;
	}
	public int getFK_servico_exame() {
		return FK_servico_exame;
	}
	public void setFK_servico_exame(int fK_servico_exame) {
		FK_servico_exame = fK_servico_exame;
	}
	public int getGestante_pra_exame() {
		return gestante_pra_exame;
	}
	public void setGestante_pra_exame(int gestante_pra_exame) {
		this.gestante_pra_exame = gestante_pra_exame;
	}
	public Calendar getData_inicio() {
		return data_inicio;
	}
	public void setData_inicio(Calendar data_inicio) {
		this.data_inicio = data_inicio;
	}
	public Calendar getData_fim() {
		return data_fim;
	}
	public void setData_fim(Calendar data_fim) {
		this.data_fim = data_fim;
	}
	public double getTaxa_percentual() {
		return taxa_percentual;
	}
	public void setTaxa_percentual(double taxa_percentual) {
		this.taxa_percentual = taxa_percentual;
	}
	public double getTaxa() {
		return taxa;
	}
	public void setTaxa(double taxa) {
		this.taxa = taxa;
	}
	public int getFK_Metodo_dpagamento() {
		return FK_Metodo_dpagamento;
	}
	public void setFK_Metodo_dpagamento(int fK_Metodo_dpagamento) {
		FK_Metodo_dpagamento = fK_Metodo_dpagamento;
	}
	public int getFK_aberturadecaixa() {
		return FK_aberturadecaixa;
	}
	public void setFK_aberturadecaixa(int fK_aberturadecaixa) {
		FK_aberturadecaixa = fK_aberturadecaixa;
	}
	public int getId_facturar_tratamento() {
		return id_facturar_tratamento;
	}
	public void setId_facturar_tratamento(int id_facturar_tratamento) {
		this.id_facturar_tratamento = id_facturar_tratamento;
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
	public int getQuantdd() {
		return quantdd;
	}
	public void setQuantdd(int quantdd) {
		this.quantdd = quantdd;
	}
	public int getFK_fac_isentada() {
		return FK_fac_isentada;
	}
	public void setFK_fac_isentada(int fK_fac_isentada) {
		FK_fac_isentada = fK_fac_isentada;
	}
	public double getTaxa_porcento() {
		return taxa_porcento;
	}
	public void setTaxa_porcento(double taxa_porcento) {
		this.taxa_porcento = taxa_porcento;
	}
	public double getTot_tax_emkz() {
		return tot_tax_emkz;
	}
	public void setTot_tax_emkz(double tot_tax_emkz) {
		this.tot_tax_emkz = tot_tax_emkz;
	}
	public double getTotal_isentado() {
		return total_isentado;
	}
	public void setTotal_isentado(double total_isentado) {
		this.total_isentado = total_isentado;
	}
	public int getFK_tipo_deservico() {
		return FK_tipo_deservico;
	}
	public void setFK_tipo_deservico(int fK_tipo_deservico) {
		FK_tipo_deservico = fK_tipo_deservico;
	}
	public String getTipo_deservico() {
		return tipo_deservico;
	}
	public void setTipo_deservico(String tipo_deservico) {
		this.tipo_deservico = tipo_deservico;
	}
	public int getFK_isencao() {
		return FK_isencao;
	}
	public void setFK_isencao(int fK_isencao) {
		FK_isencao = fK_isencao;
	}
	public double getTotal_factura() {
		return total_factura;
	}
	public void setTotal_factura(double total_factura) {
		this.total_factura = total_factura;
	}
	public int getId_produto_fact() {
		return id_produto_fact;
	}
	public void setId_produto_fact(int id_produto_fact) {
		this.id_produto_fact = id_produto_fact;
	}
	public int getFK_produto() {
		return FK_produto;
	}
	public void setFK_produto(int fK_produto) {
		FK_produto = fK_produto;
	}
	public double getTaxa_perc_produto() {
		return taxa_perc_produto;
	}
	public void setTaxa_perc_produto(double taxa_perc_produto) {
		this.taxa_perc_produto = taxa_perc_produto;
	}
	public double getPreco_produto() {
		return preco_produto;
	}
	public void setPreco_produto(double preco_produto) {
		this.preco_produto = preco_produto;
	}
	public double getTx_emkz_produto() {
		return tx_emkz_produto;
	}
	public void setTx_emkz_produto(double tx_emkz_produto) {
		this.tx_emkz_produto = tx_emkz_produto;
	}
	public double getTot_pago_produto() {
		return tot_pago_produto;
	}
	public void setTot_pago_produto(double tot_pago_produto) {
		this.tot_pago_produto = tot_pago_produto;
	}
	public int getId_prod_isentado() {
		return id_prod_isentado;
	}
	public void setId_prod_isentado(int id_prod_isentado) {
		this.id_prod_isentado = id_prod_isentado;
	}
	public int getQtd_produto() {
		return qtd_produto;
	}
	public void setQtd_produto(int qtd_produto) {
		this.qtd_produto = qtd_produto;
	}
	public double getT_isen_produto() {
		return t_isen_produto;
	}
	public void setT_isen_produto(double t_isen_produto) {
		this.t_isen_produto = t_isen_produto;
	}
	public String getNumLote() {
		return numLote;
	}
	public void setNumLote(String numLote) {
		this.numLote = numLote;
	}
	public int getFK_receita() {
		return FK_receita;
	}
	public void setFK_receita(int fK_receita) {
		FK_receita = fK_receita;
	}
	public int getId_deta_recei_facturada() {
		return id_deta_recei_facturada;
	}
	public void setId_deta_recei_facturada(int id_deta_recei_facturada) {
		this.id_deta_recei_facturada = id_deta_recei_facturada;
	}
	public String getNome_medico() {
		return nome_medico;
	}
	public void setNome_medico(String nome_medico) {
		this.nome_medico = nome_medico;
	}
	public String getHospital() {
		return hospital;
	}
	public void setHospital(String hospital) {
		this.hospital = hospital;
	}
	public int getId_isen_idade_s_geral() {
		return id_isen_idade_s_geral;
	}
	public void setId_isen_idade_s_geral(int id_isen_idade_s_geral) {
		this.id_isen_idade_s_geral = id_isen_idade_s_geral;
	}
	public int getFk_isen_principal() {
		return fk_isen_principal;
	}
	public void setFk_isen_principal(int fk_isen_principal) {
		this.fk_isen_principal = fk_isen_principal;
	}
	public double getIsen_idade_s_g_preco() {
		return isen_idade_s_g_preco;
	}
	public void setIsen_idade_s_g_preco(double isen_idade_s_g_preco) {
		this.isen_idade_s_g_preco = isen_idade_s_g_preco;
	}
	public int getIsen_idade_s_g_qtd() {
		return isen_idade_s_g_qtd;
	}
	public void setIsen_idade_s_g_qtd(int isen_idade_s_g_qtd) {
		this.isen_idade_s_g_qtd = isen_idade_s_g_qtd;
	}
	public double getIsen_idade_s_g_taxa_perc() {
		return isen_idade_s_g_taxa_perc;
	}
	public void setIsen_idade_s_g_taxa_perc(double isen_idade_s_g_taxa_perc) {
		this.isen_idade_s_g_taxa_perc = isen_idade_s_g_taxa_perc;
	}
	public double getIsen_idade_s_g_taxal_emkz() {
		return isen_idade_s_g_taxal_emkz;
	}
	public void setIsen_idade_s_g_taxal_emkz(double isen_idade_s_g_taxal_emkz) {
		this.isen_idade_s_g_taxal_emkz = isen_idade_s_g_taxal_emkz;
	}
	public double getIsen_idade_s_g_total_pago() {
		return isen_idade_s_g_total_pago;
	}
	public void setIsen_idade_s_g_total_pago(double isen_idade_s_g_total_pago) {
		this.isen_idade_s_g_total_pago = isen_idade_s_g_total_pago;
	}
	public int getId_fac_multicaixa() {
		return id_fac_multicaixa;
	}
	public void setId_fac_multicaixa(int id_fac_multicaixa) {
		this.id_fac_multicaixa = id_fac_multicaixa;
	}
	public double getSg_mcaixa_preco() {
		return sg_mcaixa_preco;
	}
	public void setSg_mcaixa_preco(double sg_mcaixa_preco) {
		this.sg_mcaixa_preco = sg_mcaixa_preco;
	}
	public int getSg_mcaixa_qtd() {
		return sg_mcaixa_qtd;
	}
	public void setSg_mcaixa_qtd(int sg_mcaixa_qtd) {
		this.sg_mcaixa_qtd = sg_mcaixa_qtd;
	}
	public double getSg_mcaixa_taxa_perc() {
		return sg_mcaixa_taxa_perc;
	}
	public void setSg_mcaixa_taxa_perc(double sg_mcaixa_taxa_perc) {
		this.sg_mcaixa_taxa_perc = sg_mcaixa_taxa_perc;
	}
	public double getSg_mcaixa_taxa_emkz() {
		return sg_mcaixa_taxa_emkz;
	}
	public void setSg_mcaixa_taxa_emkz(double sg_mcaixa_taxa_emkz) {
		this.sg_mcaixa_taxa_emkz = sg_mcaixa_taxa_emkz;
	}
	public double getSg_mcaixa_total() {
		return sg_mcaixa_total;
	}
	public void setSg_mcaixa_total(double sg_mcaixa_total) {
		this.sg_mcaixa_total = sg_mcaixa_total;
	}
	public int getFK_fornecedor(){
		return FK_fornecedor;
	}
	public void setFK_fornecedor(int FK_fornecedor){
		this.FK_fornecedor = FK_fornecedor;
	}
	public String getMaquina() {
		return maquina;
	}
	public void setMaquina(String maquina) {
		this.maquina = maquina;
	}
	public int getFK_plano_dsaude() {
		return FK_plano_dsaude;
	}
	public void setFK_plano_dsaude(int fK_plano_dsaude) {
		FK_plano_dsaude = fK_plano_dsaude;
	}
	public double getSg_seguro_preco() {
		return sg_seguro_preco;
	}
	public void setSg_seguro_preco(double sg_seguro_preco) {
		this.sg_seguro_preco = sg_seguro_preco;
	}
	public int getSg_seguro_qtd() {
		return sg_seguro_qtd;
	}
	public void setSg_seguro_qtd(int sg_seguro_qtd) {
		this.sg_seguro_qtd = sg_seguro_qtd;
	}
	public double getSg_seguro_taxa_perc() {
		return sg_seguro_taxa_perc;
	}
	public void setSg_seguro_taxa_perc(double sg_seguro_taxa_perc) {
		this.sg_seguro_taxa_perc = sg_seguro_taxa_perc;
	}
	public double getSg_seguro_taxa_emkz() {
		return sg_seguro_taxa_emkz;
	}
	public void setSg_seguro_taxa_emkz(double sg_seguro_taxa_emkz) {
		this.sg_seguro_taxa_emkz = sg_seguro_taxa_emkz;
	}
	public double getSg_seguro_total() {
		return sg_seguro_total;
	}
	public void setSg_seguro_total(double sg_seguro_total) {
		this.sg_seguro_total = sg_seguro_total;
	}
	public int getId_multi_pagamentos() {
		return id_multi_pagamentos;
	}
	public void setId_multi_pagamentos(int id_multi_pagamentos) {
		this.id_multi_pagamentos = id_multi_pagamentos;
	}
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
	public int getId_factura_plano_saude() {
		return id_factura_plano_saude;
	}
	public void setId_factura_plano_saude(int id_factura_plano_saude) {
		this.id_factura_plano_saude = id_factura_plano_saude;
	}
	public double getComparticipacao_aseguradora() {
		return comparticipacao_aseguradora;
	}
	public void setComparticipacao_aseguradora(double comparticipacao_aseguradora) {
		this.comparticipacao_aseguradora = comparticipacao_aseguradora;
	}
	public double getComparticipacao_paciente() {
		return comparticipacao_paciente;
	}
	public void setComparticipacao_paciente(double comparticipacao_paciente) {
		this.comparticipacao_paciente = comparticipacao_paciente;
	}
	public double getTotal_pago_plano_saude() {
		return total_pago_plano_saude;
	}
	public void setTotal_pago_plano_saude(double total_pago_plano_saude) {
		this.total_pago_plano_saude = total_pago_plano_saude;
	}
	public double getEx_seguro_preco() {
		return ex_seguro_preco;
	}
	public void setEx_seguro_preco(double ex_seguro_preco) {
		this.ex_seguro_preco = ex_seguro_preco;
	}
	public int getEx_seguro_qtd() {
		return ex_seguro_qtd;
	}
	public void setEx_seguro_qtd(int ex_seguro_qtd) {
		this.ex_seguro_qtd = ex_seguro_qtd;
	}
	public double getEx_seguro_taxa_perc() {
		return ex_seguro_taxa_perc;
	}
	public void setEx_seguro_taxa_perc(double ex_seguro_taxa_perc) {
		this.ex_seguro_taxa_perc = ex_seguro_taxa_perc;
	}
	public double getEx_seguro_taxa_emkz() {
		return ex_seguro_taxa_emkz;
	}
	public void setEx_seguro_taxa_emkz(double ex_seguro_taxa_emkz) {
		this.ex_seguro_taxa_emkz = ex_seguro_taxa_emkz;
	}
	public double getEx_seguro_total() {
		return ex_seguro_total;
	}
	public void setEx_seguro_total(double ex_seguro_total) {
		this.ex_seguro_total = ex_seguro_total;
	}
	public int getTitular() {
		return titular;
	}
	public void setTitular(int titular) {
		this.titular = titular;
	}
	
	
	
}	
