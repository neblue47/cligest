package ao.co.cligest.entidades;

import java.util.Calendar;


public class ServicoConsumivel {

	private int id_servico_consumiveis;
	private String referencia_serv_con;
	private String servico_consumiveis;
	private double custo_preco;
	private int fk_taxa;
	private double taxakz;
	private double taxaperc;
	private String taxa;
	private String descricao_serv_con;
	private String categoria_serv_con;
	
	private int id_plani_contracto;
	private int id_tipo_contracto;
	private String tipo_contracto;
	private Calendar data_fim__contracto;
	private Calendar dia_data_contracto;
	private String refContrato;
	
	private int id_agregar_consumiveis;
	private double total_agregar_consumiveis;
	private String referencia_agregar_consumiveis;
	private int fk_funcionario;
	private Calendar data_agregar_consumiveis;
	private int fk_fornecedor;
	private String nomeFornecedor;
	private String nomeFuncionario;
	private String refFornecedor;
	
	private int id_agregar_consumiveis_linha;
	private int qtd_servico_consumiveis;
	
	private int id_planificar_serv_consu;
	private int periocidade_serv_consu;
	private int fk_contas_contabeis;
	private Calendar data_serv_consu;
	private double total_butro_serv_consu;
	private String descricao_planificar;
	
	private int id_planificar_periodicidade;
	private Calendar data_planificar_pontual;
	private int dia_consumiveis;
	private Calendar data_inicio__consumiveis;
	private Calendar data_fim__consumiveis;
	private Calendar dia_data_consumiveis;
	
	private int id_planificados_dividas;
	private String ref_planificados_dividas;
	private double total_por_pagar_dividas;
	private Calendar data_planificados_dividas;
	private String categoria_plan_dividas;
	private double total_kz_plan_dividas;
	
	
	
	
	public String getDescricao_planificar() {
		return descricao_planificar;
	}
	public void setDescricao_planificar(String descricao_planificar) {
		this.descricao_planificar = descricao_planificar;
	}
	public int getId_planificar_periodicidade() {
		return id_planificar_periodicidade;
	}
	public void setId_planificar_periodicidade(int id_planificar_periodicidade) {
		this.id_planificar_periodicidade = id_planificar_periodicidade;
	}
	public Calendar getData_planificar_pontual() {
		return data_planificar_pontual;
	}
	public void setData_planificar_pontual(Calendar data_planificar_pontual) {
		this.data_planificar_pontual = data_planificar_pontual;
	}
	public int getDia_consumiveis() {
		return dia_consumiveis;
	}
	public void setDia_consumiveis(int dia_consumiveis) {
		this.dia_consumiveis = dia_consumiveis;
	}
	public Calendar getData_inicio__consumiveis() {
		return data_inicio__consumiveis;
	}
	public void setData_inicio__consumiveis(Calendar data_inicio__consumiveis) {
		this.data_inicio__consumiveis = data_inicio__consumiveis;
	}
	public Calendar getData_fim__consumiveis() {
		return data_fim__consumiveis;
	}
	public void setData_fim__consumiveis(Calendar data_fim__consumiveis) {
		this.data_fim__consumiveis = data_fim__consumiveis;
	}
	public Calendar getDia_data_consumiveis() {
		return dia_data_consumiveis;
	}
	public void setDia_data_consumiveis(Calendar dia_data_consumiveis) {
		this.dia_data_consumiveis = dia_data_consumiveis;
	}
	public int getId_planificar_serv_consu() {
		return id_planificar_serv_consu;
	}
	public void setId_planificar_serv_consu(int id_planificar_serv_consu) {
		this.id_planificar_serv_consu = id_planificar_serv_consu;
	}
	public int getFk_contas_contabeis() {
		return fk_contas_contabeis;
	}
	public void setFk_contas_contabeis(int fk_contas_contabeis) {
		this.fk_contas_contabeis = fk_contas_contabeis;
	}
	public Calendar getData_serv_consu() {
		return data_serv_consu;
	}
	public void setData_serv_consu(Calendar data_serv_consu) {
		this.data_serv_consu = data_serv_consu;
	}
	public double getTotal_butro_serv_consu() {
		return total_butro_serv_consu;
	}
	public void setTotal_butro_serv_consu(double total_butro_serv_consu) {
		this.total_butro_serv_consu = total_butro_serv_consu;
	}
	public int getId_servico_consumiveis() {
		return id_servico_consumiveis;
	}
	public void setId_servico_consumiveis(int id_servico_consumiveis) {
		this.id_servico_consumiveis = id_servico_consumiveis;
	}
	public String getReferencia_serv_con() {
		return referencia_serv_con;
	}
	public void setReferencia_serv_con(String referencia_serv_con) {
		this.referencia_serv_con = referencia_serv_con;
	}
	public String getServico_consumiveis() {
		return servico_consumiveis;
	}
	public void setServico_consumiveis(String servico_consumiveis) {
		this.servico_consumiveis = servico_consumiveis;
	}
	public double getCusto_preco() {
		return custo_preco;
	}
	public void setCusto_preco(double custo_preco) {
		this.custo_preco = custo_preco;
	}
	public int getFk_taxa() {
		return fk_taxa;
	}
	public void setFk_taxa(int fk_taxa) {
		this.fk_taxa = fk_taxa;
	}
	public double getTaxakz() {
		return taxakz;
	}
	public void setTaxakz(double taxakz) {
		this.taxakz = taxakz;
	}
	public String getTaxa() {
		return taxa;
	}
	public void setTaxa(String taxa) {
		this.taxa = taxa;
	}
	public String getDescricao_serv_con() {
		return descricao_serv_con;
	}
	public void setDescricao_serv_con(String descricao_serv_con) {
		this.descricao_serv_con = descricao_serv_con;
	}
	public String getCategoria_serv_con() {
		return categoria_serv_con;
	}
	public void setCategoria_serv_con(String categoria_serv_con) {
		this.categoria_serv_con = categoria_serv_con;
	}
	
	public ServicoConsumivel() {
	}
	public double getTaxaperc() {
		return taxaperc;
	}
	public void setTaxaperc(double taxaperc) {
		this.taxaperc = taxaperc;
	}
	public int getId_tipo_contracto() {
		return id_tipo_contracto;
	}
	public void setId_tipo_contracto(int id_tipo_contracto) {
		this.id_tipo_contracto = id_tipo_contracto;
	}
	public String getTipo_contracto() {
		return tipo_contracto;
	}
	public void setTipo_contracto(String tipo_contracto) {
		this.tipo_contracto = tipo_contracto;
	}
	public int getId_agregar_consumiveis() {
		return id_agregar_consumiveis;
	}
	public void setId_agregar_consumiveis(int id_agregar_consumiveis) {
		this.id_agregar_consumiveis = id_agregar_consumiveis;
	}
	public double getTotal_agregar_consumiveis() {
		return total_agregar_consumiveis;
	}
	public void setTotal_agregar_consumiveis(double total_agregar_consumiveis) {
		this.total_agregar_consumiveis = total_agregar_consumiveis;
	}
	public String getReferencia_agregar_consumiveis() {
		return referencia_agregar_consumiveis;
	}
	public void setReferencia_agregar_consumiveis(String referencia_agregar_consumiveis) {
		this.referencia_agregar_consumiveis = referencia_agregar_consumiveis;
	}
	public int getFk_funcionario() {
		return fk_funcionario;
	}
	public void setFk_funcionario(int fk_funcionario) {
		this.fk_funcionario = fk_funcionario;
	}
	public Calendar getData_agregar_consumiveis() {
		return data_agregar_consumiveis;
	}
	public void setData_agregar_consumiveis(Calendar data_agregar_consumiveis) {
		this.data_agregar_consumiveis = data_agregar_consumiveis;
	}
	public int getFk_fornecedor() {
		return fk_fornecedor;
	}
	public void setFk_fornecedor(int fk_fornecedor) {
		this.fk_fornecedor = fk_fornecedor;
	}
	public int getId_agregar_consumiveis_linha() {
		return id_agregar_consumiveis_linha;
	}
	public void setId_agregar_consumiveis_linha(int id_agregar_consumiveis_linha) {
		this.id_agregar_consumiveis_linha = id_agregar_consumiveis_linha;
	}
	public int getQtd_servico_consumiveis() {
		return qtd_servico_consumiveis;
	}
	public void setQtd_servico_consumiveis(int qtd_servico_consumiveis) {
		this.qtd_servico_consumiveis = qtd_servico_consumiveis;
	}
	public int getPeriocidade_serv_consu() {
		return periocidade_serv_consu;
	}
	public void setPeriocidade_serv_consu(int periocidade_serv_consu) {
		this.periocidade_serv_consu = periocidade_serv_consu;
	}
	public Calendar getData_fim__contracto() {
		return data_fim__contracto;
	}
	public void setData_fim__contracto(Calendar data_fim__contracto) {
		this.data_fim__contracto = data_fim__contracto;
	}
	public Calendar getDia_data_contracto() {
		return dia_data_contracto;
	}
	public void setDia_data_contracto(Calendar dia_data_contracto) {
		this.dia_data_contracto = dia_data_contracto;
	}
	public String getNomeFornecedor() {
		return nomeFornecedor;
	}
	public void setNomeFornecedor(String nomeFornecedor) {
		this.nomeFornecedor = nomeFornecedor;
	}
	public String getNomeFuncionario() {
		return nomeFuncionario;
	}
	public void setNomeFuncionario(String nomeFuncionario) {
		this.nomeFuncionario = nomeFuncionario;
	}
	 
	public String getRefFornecedor() {
		return refFornecedor;
	}
	public void setRefFornecedor(String refFornecedor) {
		this.refFornecedor = refFornecedor;
	}
	public int getId_plani_contracto() {
		return id_plani_contracto;
	}
	public void setId_plani_contracto(int id_plani_contracto) {
		this.id_plani_contracto = id_plani_contracto;
	}
	public String getRefContrato() {
		return refContrato;
	}
	public void setRefContrato(String refContrato) {
		this.refContrato = refContrato;
	}
	public int getId_planificados_dividas() {
		return id_planificados_dividas;
	}
	public void setId_planificados_dividas(int id_planificados_dividas) {
		this.id_planificados_dividas = id_planificados_dividas;
	}
	public String getRef_planificados_dividas() {
		return ref_planificados_dividas;
	}
	public void setRef_planificados_dividas(String ref_planificados_dividas) {
		this.ref_planificados_dividas = ref_planificados_dividas;
	}
	public double getTotal_por_pagar_dividas() {
		return total_por_pagar_dividas;
	}
	public void setTotal_por_pagar_dividas(double total_por_pagar_dividas) {
		this.total_por_pagar_dividas = total_por_pagar_dividas;
	}
	public Calendar getData_planificados_dividas() {
		return data_planificados_dividas;
	}
	public void setData_planificados_dividas(Calendar data_planificados_dividas) {
		this.data_planificados_dividas = data_planificados_dividas;
	}
	public String getCategoria_plan_dividas() {
		return categoria_plan_dividas;
	}
	public void setCategoria_plan_dividas(String categoria_plan_dividas) {
		this.categoria_plan_dividas = categoria_plan_dividas;
	}
	public double getTotal_kz_plan_dividas() {
		return total_kz_plan_dividas;
	}
	public void setTotal_kz_plan_dividas(double total_kz_plan_dividas) {
		this.total_kz_plan_dividas = total_kz_plan_dividas;
	}
	 
	
}
