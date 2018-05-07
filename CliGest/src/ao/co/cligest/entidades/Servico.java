package ao.co.cligest.entidades;

import java.io.Serializable;
import java.util.Calendar;

public class Servico extends TipoServico implements Serializable {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private int id_servico;
	private int FK_tipo_de_servico;
	private int FK_especialidade;
	private String descricao;
	private double preco;
	private double preco_unitario;
	private String servico;
	private int id_especialidade;
	private String especialidade;
	private String tipo_de_servico;
	private double valor_total;
	private String numero_factura;
	private int vacinacao;

	private int FK_numero_do_servico_de_exame;
	private int FK_servico_exame;
	private int FK_funcionario;
	private int FK_paciente;
	private int FK_tipo_dcobertura;
	private Calendar data_registo;
	private String hora_registo;
	private int FK_servico_obrigatorio;
	
	// taxas
	private int id_taxa;
	private String tipo_de_taxa;
	private double imposto;
	private int categoria;
	private String nome_categoria;
	private int quantidade;
	private int id;
	
	//chaves estrangeiras servicos de teste de qualidade
	private int FK_taxa;	
	private int FK_categoria_tq;
	
	//para categoria de teste de qualidade
	private int id_categoria_tq;
	private String categoria_tq;
	private int id_servico_tq;

	
	//Para teste de qualidade
		private java.sql.Date data;
		private int id_agen_test_quali; 
		private String numero_doservico;
		private int FK_serv_test_quali;
		private int FK_empresa;
		private int FK_dia_dasemana;
		private java.sql.Time hora;
		
		public int getId_agen_test_quali() {
			return id_agen_test_quali;
		}
		public void setId_agen_test_quali(int id_agen_test_quali) {
			this.id_agen_test_quali = id_agen_test_quali;
		}
		public String getNumero_doservico() {
			return numero_doservico;
		}
		public void setNumero_doservico(String numero_dpservico) {
			this.numero_doservico = numero_dpservico;
		}
		
		public int getFK_serv_test_quali() {
			return FK_serv_test_quali;
		}
		public void setFK_serv_test_quali(int fK_serv_test_quali) {
			FK_serv_test_quali = fK_serv_test_quali;
		}
	
		public String getNumero_factura() 
		{
			return numero_factura;
		}
		public void setNumero_factura(String numero_factura) 
		{
			this.numero_factura = numero_factura;
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
		public java.sql.Time getHora() {
			return hora;
		}
		public void setHora(java.sql.Time hora) {
			this.hora = hora;
		}
		
		public java.sql.Date getData() {
			return data;
		}
		public void setData(java.sql.Date data) {
			this.data = data;
		}
	
	
	//para servico de teste de qualidade
	private String serv_teste_qualidade;
	
	public int getId_servico_tq() {
		return id_servico_tq;
	}
	public void setId_servico_tq(int id_servico_tq) {
		this.id_servico_tq = id_servico_tq;
	}

	private String descricao_tq;
	
	//para servico de teste de qualidade
	public String getServ_teste_qualidade() {
		return serv_teste_qualidade;
	}
	public void setServ_teste_qualidade(String serv_teste_qualidade) {
		this.serv_teste_qualidade = serv_teste_qualidade;
	}
	public String getDescricao_tq() {
		return descricao_tq;
	}
	public void setDescricao_tq(String descricao_tq) {
		this.descricao_tq = descricao_tq;
	}
	
	//chaves estrangeiras 
	public int getFK_taxa() {
		return FK_taxa;
	}
	public void setFK_taxa(int fK_taxa) {
		FK_taxa = fK_taxa;
	}
	public int getId_categoria_tq() {
		return id_categoria_tq;
	}
	public int getFK_categoria_tq() {
		return FK_categoria_tq;
	}
	
	//get e set para categoria de teste qualidade
	
	public void setFK_categoria_tq(int fK_categoria_tq) {
		FK_categoria_tq = fK_categoria_tq;
	}
	public void setId_categoria_tq(int id_categoria_tq) {
		this.id_categoria_tq = id_categoria_tq;
	}
	public String getCategoria_tq() {
		return categoria_tq;
	}
	public void setCategoria_tq(String categoria_tq) {
		this.categoria_tq = categoria_tq;
	}
	
	
	public String getNome_categoria() {
		return nome_categoria;
	}
	public void setNome_categoria(String nome_categoria) {
		this.nome_categoria = nome_categoria;
	}
	public int getCategoria() {
		return categoria;
	}
	public void setCategoria(int categoria) {
		this.categoria = categoria;
	}
	public int getId_taxa() {
		return id_taxa;
	}
	public void setId_taxa(int id_taxa) {
		this.id_taxa = id_taxa;
	}
	public String getTipo_de_taxa() {
		return tipo_de_taxa;
	}
	public void setTipo_de_taxa(String tipo_de_taxa) {
		this.tipo_de_taxa = tipo_de_taxa;
	}
	public int getFK_especialidade() {
		return FK_especialidade;
	}
	public void setFK_especialidade(int fK_especialidade) {
		FK_especialidade = fK_especialidade;
	}
	public int getId_especialidade() {
		return id_especialidade;
	}
	public void setId_especialidade(int id_especialidade) {
		this.id_especialidade = id_especialidade;
	}
	public String getEspecialidade() {
		return especialidade;
	}
	public void setEspecialidade(String especialidade) {
		this.especialidade = especialidade;
	}
	public String getTipo_de_servico() {
		return tipo_de_servico;
	}
	public void setTipo_de_servico(String tipo_de_servico) {
		this.tipo_de_servico = tipo_de_servico;
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
	public String getDescricao() {
		return descricao;
	}
	public void setDescricao(String descricao) {
		this.descricao = descricao;
	}
	public double getPreco() {
		return preco;
	}
	public void setPreco(double preco) {
		this.preco = preco;
	}
	public String getServico() {
		return servico;
	}
	public void setServico(String servico) {
		this.servico = servico;
	}
	public int getFK_numero_do_servico_de_exame() {
		return FK_numero_do_servico_de_exame;
	}
	public void setFK_numero_do_servico_de_exame(int fK_numero_do_servico_de_exame) {
		FK_numero_do_servico_de_exame = fK_numero_do_servico_de_exame;
	}
	public int getFK_servico_exame() {
		return FK_servico_exame;
	}
	public void setFK_servico_exame(int fK_servico_exame) {
		FK_servico_exame = fK_servico_exame;
	}
	public int getFK_funcionario() {
		return FK_funcionario;
	}
	public void setFK_funcionario(int fK_funcionario) {
		FK_funcionario = fK_funcionario;
	}
	public int getFK_paciente() {
		return FK_paciente;
	}
	public void setFK_paciente(int fK_paciente) {
		FK_paciente = fK_paciente;
	}
	public int getFK_tipo_dcobertura() {
		return FK_tipo_dcobertura;
	}
	public void setFK_tipo_dcobertura(int fK_tipo_dcobertura) {
		FK_tipo_dcobertura = fK_tipo_dcobertura;
	}
	public Calendar getData_registo() {
		return data_registo;
	}
	public void setData_registo(Calendar data_registo) {
		this.data_registo = data_registo;
	}
	public String getHora_registo() {
		return hora_registo;
	}
	public void setHora_registo(String hora_registo) {
		this.hora_registo = hora_registo;
	}
	public double getValor_total() {
		return valor_total;
	}
	public void setValor_total(double valor_total) {
		this.valor_total = valor_total;
	}
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	
	
	
	public double getImposto() {
		return imposto;
	}
	public void setImposto(double imposto) {
		this.imposto = imposto;
	}



	/**/
	private double subTotal;
	private double taxa;
	private double taxaEmkz;
	
	public double getTaxaEmkz() {
		return taxaEmkz;
	}
	public void setTaxaEmkz(double taxaEmkz) {
		this.taxaEmkz = taxaEmkz;
	}
	public static long getSerialversionuid() {
		return serialVersionUID;
	}
	public double getTaxa() {
		return taxa;
	}
	public void setTaxa(double taxa) {
		this.taxa = taxa;
	}
	/**/
	public double getSubTotal() {
		return subTotal;
	}
	public void setSubTotal(double subTotal) {
		this.subTotal = getPreco();
	}
	public double getPreco_unitario() {
		return preco_unitario;
	}
	public void setPreco_unitario(double preco_unitario) {
		this.preco_unitario = preco_unitario;
	}
	public int getQuantidade() {
		return quantidade;
	}
	public void setQuantidade(int quantidade) {
		this.quantidade = quantidade;
	}
	public int getFK_servico_obrigatorio() {
		return FK_servico_obrigatorio;
	}
	public void setFK_servico_obrigatorio(int fK_servico_obrigatorio) {
		FK_servico_obrigatorio = fK_servico_obrigatorio;
	}
	public int getVacinacao() {
		return vacinacao;
	}
	public void setVacinacao(int vacinacao) {
		this.vacinacao = vacinacao;
	}
	
	
}
