
package ao.co.cligest.entidades;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

@SuppressWarnings("serial")
public class Produtos extends Paciente{
	
	private int id_produto;
	private String codigo_barra;
	private int requer_receita;
	private String requer_receitas;
	private String nome_comercial;
	private String nome_quimico;
	private String requerente_interno;
	private int FK_embalagem;
	private double preco_dcompra;
	private double preco_dvenda;
	private String hora_cadastro_farmaco;
	private int FK_funcionario;
	private int FK_dosagem;
	private int FK_forma_farmaceutica;
	private String data_cadastro;
	private int inactivo;
	private String estadoinactivo;
	private int balanco;
	private int qtd_por_receber;
	private int valor_positivo;
	private int valor_negativo;
	private int FK_requerente_interno;
	private int encomendaValidada;
	
	private String imagem;
	private int FK_taxa;
	
	private int FK_devolucao_venda;
	private int id_produto_devolvido;
	private Calendar data_pro_devolvido;
	private String hora_pro_devolvido;
	private String requisicao_original;
	private String  dataEmTexto;
	private int fk_produto;
	private double preco_pro_devolvido;
	private int qtd_pro_devolvido;
	private double txa_pro_devolvido;
	private double subt_pro_devolvido;
	private int  FK_servico_obrigatorio;
	private int fk_grupo;
	
	private int Qtd_geral_vendido;
	
	public String getRequer_receitas() {
		return requer_receitas;
	}
	public void setRequer_receitas(String requer_receitas) {
		this.requer_receitas = requer_receitas;
	}
	public int getQtd_geral_vendido() 
	{
		return Qtd_geral_vendido;
	}
	public void setQtd_geral_vendido(int qtd_geral_vendido) {
		Qtd_geral_vendido = qtd_geral_vendido;
	}
	private String data_de_caducidade;
	
	
	public String getData_de_caducidade() {
		return data_de_caducidade;
	}
	public void setData_de_caducidade(String data_de_caducidade) {
		this.data_de_caducidade = data_de_caducidade;
	}
		// tblmonstra
		private Integer id_montra_deproduto;
		private Integer FK_alabloco;
		private String montra_deproduto;
		// tblalabloco
		private Integer id_alabloco;
		private Integer FK_local_dearmazenamento;
		private String alabloco_doproduto;
		// tblprarteleira
		private Integer id_prartileira;
		private Integer FK_montra_deproduto;
		private String prartileira;
		//tbllocal
		private Integer id_local_dearmazenamento;
		private String localdearmazenamento;
		
	// tblEmbalagem
	private Integer id_embalagem;
	private String valor_embalagem;
	// tbltblformafarmaceutico
	private Integer id_forma_farmaceutica;
	private String valor_forma_farmaceutica;
	private String forma_farmaceutica;
	// tbltaxa
	private Integer id_taxa;
	private String tipo_de_taxa;
	private Integer taxa;
	private double taxa_emkz;
	private double taxas;
	//tbldosagem
	private Integer id_dosagem;
	private String valor_dosagem;
	
	// Encomenda Geral
	private int id_numEncomenda;
	private Calendar dataCriacao;
	private String horaCriacao;
	private String numEncomenda;
	private int FK_fornecedor;
	private String fornecedor;
	private String mTransportacao;
	private int FK_laboratorio;
	private String laboratorio;
	private int quantidade;
	private double totalEncomenda;
	private String nomFuncionario;
	
 
	// Encomenda
		private Integer FK_num_encomenda;
		private String metodo_transportacao;
		private String data_encomenda;
		private String hora_encomenda;
		private String num_encomenda;
		private String funcionario;
		private String empresa;
//		Diverso
		private String colaborador;
		private Integer total;
		
//		Isençao
		private int FK_tipo_deservico;
		private String tipode_servico_facturado;
		private int FK_metodo_de_pagameto;
		private String metodo_depagameto;
		private double t_isen_produto;
		
		private Integer totalportipodeproduto;
		
		
		
		
		public Integer getTotalportipodeproduto() {
			return totalportipodeproduto;
		}
		public void setTotalportipodeproduto(Integer totalportipodeproduto) {
			this.totalportipodeproduto = totalportipodeproduto;
		}
		public String getEstadoinactivo() {
			return estadoinactivo;
		}
		public void setEstadoinactivo(String estadoinactivo) {
			this.estadoinactivo = estadoinactivo;
		}
		
		public int getFK_tipo_deservico() {
			return FK_tipo_deservico;
		}
		public void setFK_tipo_deservico(int fK_tipo_deservico) {
			FK_tipo_deservico = fK_tipo_deservico;
		}
		public String getTipode_servico_facturado() {
			return tipode_servico_facturado;
		}
		public void setTipode_servico_facturado(String tipode_servico_facturado) {
			this.tipode_servico_facturado = tipode_servico_facturado;
		}
		public int getFK_metodo_de_pagameto() {
			return FK_metodo_de_pagameto;
		}
		public void setFK_metodo_de_pagameto(int fK_metodo_de_pagameto) {
			FK_metodo_de_pagameto = fK_metodo_de_pagameto;
		}
		public String getMetodo_depagameto() {
			return metodo_depagameto;
		}
		public void setMetodo_depagameto(String metodo_depagameto) {
			this.metodo_depagameto = metodo_depagameto;
		}
		public double getT_isen_produto() {
			return t_isen_produto;
		}
		public void setT_isen_produto(double t_isen_produto) {
			this.t_isen_produto = t_isen_produto;
		}
		private Calendar data_inicio;
		private Calendar data_fim;
  
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
	// Recebendo Lote de Produtos 
	private String numero_lote_sere;
	private Calendar data_expiracao;
	private int qdt_recebida;
	private int nSeAplica;
	private int FK_num_difnoinventario;
	private String num_difnoinventario;
	
	private Integer FK_receita;
    private Double txa_percentual;
    private Double txa_emkwanza;
    private String produto;
    private String num_receita; 
    
    private int FK_isencao;
    private int FK_farmaco_isentado;
    private double preco_isencao;
    private int qtd_farmaco_isentado;
    private double tx_farmaco_isentado;
    private double total_tx_farm_isent;
    private double tota_farmaco_isentado;
    
    public int getBalanco() {
		return balanco;
	}
	public void setBalanco(int balanco) {
		this.balanco = balanco;
	}
 
	public int getId_produto() { 
		return id_produto;
	}
	public void setId_produto(int id_produto) {
		this.id_produto = id_produto;
	}
	public String getCodigo_barra() {
		return codigo_barra;
	}
	public void setCodigo_barra(String codigo_barra) {
		this.codigo_barra = codigo_barra;
	}
	public int getRequer_receita() {
		return requer_receita;
	}
	public void setRequer_receita(int requer_receita) {
		this.requer_receita = requer_receita;
	}
	public String getNome_comercial() {
		return nome_comercial;
	}
	public void setNome_comercial(String nome_comercial) {
		this.nome_comercial = nome_comercial;
	}
	public String getNome_quimico() {
		return nome_quimico;
	}
	public void setNome_quimico(String nome_quimico) {
		this.nome_quimico = nome_quimico;
	}
	public int getFK_embalagem() {
		return FK_embalagem;
	}
	public void setFK_embalagem(int fK_embalagem) {
		FK_embalagem = fK_embalagem;
	}
	public double getPreco_dcompra() {
		return preco_dcompra;
	}
	public void setPreco_dcompra(double preco_dcompra) {
		this.preco_dcompra = preco_dcompra;
	}
	public double getPreco_dvenda() {
		return preco_dvenda;
	}
	public void setPreco_dvenda(double preco_dvenda) {
		this.preco_dvenda = preco_dvenda;
	}
	public String getHora_cadastro_farmaco() {
		return hora_cadastro_farmaco;
	}
	public void setHora_cadastro_farmaco(String hora_cadastro_farmaco) {
		this.hora_cadastro_farmaco = hora_cadastro_farmaco;
	}
	public int getFK_funcionario() {
		return FK_funcionario;
	}
	public void setFK_funcionario(int fK_funcionario) {
		FK_funcionario = fK_funcionario;
	}
	public int getFK_dosagem() {
		return FK_dosagem;
	}
	public void setFK_dosagem(int fK_dosagem) {
		FK_dosagem = fK_dosagem;
	}
	public int getFK_forma_farmaceutica() {
		return FK_forma_farmaceutica;
	}
	public void setFK_forma_farmaceutica(int fK_forma_farmaceutica) {
		FK_forma_farmaceutica = fK_forma_farmaceutica;
	}
	public String getData_cadastro() {
		return data_cadastro;
	}
	public void setData_cadastro(String data_cadastro) {
		this.data_cadastro = data_cadastro;
	}
	public int getInactivo() {
		return inactivo;
	}
	public void setInactivo(int inactivo) {
		this.inactivo = inactivo;
	}
	public String getImagem() {
		return imagem;
	}
	public void setImagem(String imagem) {
		this.imagem = imagem;
	}
	public int getFK_taxa() {
		return FK_taxa;
	}
	public void setFK_taxa(int fK_taxa) {
		FK_taxa = fK_taxa;
	}
	public Integer getId_embalagem() {
		return id_embalagem;
	}
	public void setId_embalagem(Integer id_embalagem) {
		this.id_embalagem = id_embalagem;
	}
	public String getValor_embalagem() {
		return valor_embalagem;
	}
	public void setValor_embalagem(String valor_embalagem) {
		this.valor_embalagem = valor_embalagem;
	}
	public Integer getId_forma_farmaceutica() {
		return id_forma_farmaceutica;
	}
	public void setId_forma_farmaceutica(Integer id_forma_farmaceutica) {
		this.id_forma_farmaceutica = id_forma_farmaceutica;
	}
	public String getValor_forma_farmaceutica() {
		return valor_forma_farmaceutica;
	}
	public void setValor_forma_farmaceutica(String valor_forma_farmaceutica) {
		this.valor_forma_farmaceutica = valor_forma_farmaceutica;
	}
	
	
	public String getForma_farmaceutica() {
		return forma_farmaceutica;
	}
	public void setForma_farmaceutica(String forma_farmaceutica) {
		this.forma_farmaceutica = forma_farmaceutica;
	}
	public Integer getId_taxa() {
		return id_taxa;
	}
	public void setId_taxa(Integer id_taxa) {
		this.id_taxa = id_taxa;
	}
	public String getTipo_de_taxa() {
		return tipo_de_taxa;
	}
	public void setTipo_de_taxa(String tipo_de_taxa) {
		this.tipo_de_taxa = tipo_de_taxa;
	}
	public Integer getId_dosagem() {
		return id_dosagem;
	}
	public void setId_dosagem(Integer id_dosagem) {
		this.id_dosagem = id_dosagem;
	}
	public String getValor_dosagem() {
		return valor_dosagem;
	}
	public void setValor_dosagem(String valor_dosagem) {
		this.valor_dosagem = valor_dosagem;
	}
	
	public Calendar getDataCriacao() {
		return dataCriacao;
	}
	public void setDataCriacao(Calendar dataCriacao) {
		this.dataCriacao = dataCriacao;
	}
	public String getHoraCriacao() {
		return horaCriacao;
	}
	public void setHoraCriacao(String horaCriacao) {
		this.horaCriacao = horaCriacao;
	}
	public String getNumEncomenda() {
		return numEncomenda;
	}
	public void setNumEncomenda(String numEncomenda) {
		this.numEncomenda = numEncomenda;
	}
	public int getId_numEncomenda() {
		return id_numEncomenda;
	}
	public void setId_numEncomenda(int id_numEncomenda) {
		this.id_numEncomenda = id_numEncomenda;
	}
	public int getFK_fornecedor() {
		return FK_fornecedor;
	}
	public void setFK_fornecedor(int fK_fornecedor) {
		FK_fornecedor = fK_fornecedor;
	}
	public String getmTransportacao() {
		return mTransportacao;
	}
	public void setmTransportacao(String mTransportacao) {
		this.mTransportacao = mTransportacao;
	}
	public int getFK_laboratorio() {
		return FK_laboratorio;
	}
	public void setFK_laboratorio(int fK_laboratorio) {
		FK_laboratorio = fK_laboratorio;
	}
	public int getQuantidade() {
		return quantidade;
	}
	public void setQuantidade(int quantidade) {
		this.quantidade = quantidade;
	}
	public double getTotalEncomenda() {
		return totalEncomenda;
	}
	public void setTotalEncomenda(double totalEncomenda) {
		this.totalEncomenda = totalEncomenda;
	}
	public String getFornecedor() {
		return fornecedor;
	}
	public void setFornecedor(String fornecedor) {
		this.fornecedor = fornecedor;
	}
	public String getLaboratorio() {
		return laboratorio;
	}
	public void setLaboratorio(String laboratorio) {
		this.laboratorio = laboratorio;
	}
	public String getNomFuncionario() {
		return nomFuncionario;
	}
	public void setNomFuncionario(String nomFuncionario) {
		this.nomFuncionario = nomFuncionario;
	}
 
	public Integer getId_montra_deproduto() {
		return id_montra_deproduto;
	}
	public void setId_montra_deproduto(Integer id_montra_deproduto) {
		this.id_montra_deproduto = id_montra_deproduto;
	}
	public Integer getFK_alabloco() {
		return FK_alabloco;
	}
	public void setFK_alabloco(Integer fK_alabloco) {
		FK_alabloco = fK_alabloco;
	}
	public String getMontra_deproduto() {
		return montra_deproduto;
	}
	public void setMontra_deproduto(String montra_deproduto) {
		this.montra_deproduto = montra_deproduto;
	}
	public Integer getId_alabloco() {
		return id_alabloco;
	}
	public void setId_alabloco(Integer id_alabloco) {
		this.id_alabloco = id_alabloco;
	}
	public Integer getFK_local_dearmazenamento() {
		return FK_local_dearmazenamento;
	}
	public void setFK_local_dearmazenamento(Integer fK_local_dearmazenamento) {
		FK_local_dearmazenamento = fK_local_dearmazenamento;
	}
	public String getAlabloco_doproduto() {
		return alabloco_doproduto;
	}
	public void setAlabloco_doproduto(String alabloco_doproduto) {
		this.alabloco_doproduto = alabloco_doproduto;
	}
	public Integer getId_prartileira() {
		return id_prartileira;
	}
	public void setId_prartileira(Integer id_prartileira) {
		this.id_prartileira = id_prartileira;
	}
	public Integer getFK_montra_deproduto() {
		return FK_montra_deproduto;
	}
	public void setFK_montra_deproduto(Integer fK_montra_deproduto) {
		FK_montra_deproduto = fK_montra_deproduto;
	}
	public String getPrartileira() {
		return prartileira;
	}
	public void setPrartileira(String prartileira) {
		this.prartileira = prartileira;
	}
	public Integer getId_local_dearmazenamento() {
		return id_local_dearmazenamento;
	}
	public void setId_local_dearmazenamento(Integer id_local_dearmazenamento) {
		this.id_local_dearmazenamento = id_local_dearmazenamento;
	}
	public String getLocaldearmazenamento() {
		return localdearmazenamento;
	}
	public void setLocaldearmazenamento(String localdearmazenamento) {
		this.localdearmazenamento = localdearmazenamento;
	}
	public Integer getTaxa() {
		return taxa;
	}
	public void setTaxa(Integer taxa) {
		this.taxa = taxa;
	}
	public Integer getFK_num_encomenda() {
		return FK_num_encomenda;
	}
	public void setFK_num_encomenda(Integer fK_num_encomenda) {
		FK_num_encomenda = fK_num_encomenda;
	}
	public String getMetodo_transportacao() {
		return metodo_transportacao;
	}
	public void setMetodo_transportacao(String metodo_transportacao) {
		this.metodo_transportacao = metodo_transportacao;
	}
	public String getData_encomenda() {
		return data_encomenda;
	}
	public void setData_encomenda(String data_encomenda) {
		this.data_encomenda = data_encomenda;
	}
	public String getHora_encomenda() {
		return hora_encomenda;
	}
	public void setHora_encomenda(String hora_encomenda) {
		this.hora_encomenda = hora_encomenda;
	}
	public String getNum_encomenda() {
		return num_encomenda;
	}
	public void setNum_encomenda(String num_encomenda) {
		this.num_encomenda = num_encomenda;
	}
	public String getFuncionario() {
		return funcionario;
	}
	public void setFuncionario(String funcionario) {
		this.funcionario = funcionario;
	}
	public String getEmpresa() {
		return empresa;
	}
	public void setEmpresa(String empresa) {
		this.empresa = empresa;
	}
	public String getColaborador() {
		return colaborador;
	}
	public void setColaborador(String colaborador) {
		this.colaborador = colaborador;
	}
	public Integer getTotal() {
		return total;
	}
	public void setTotal(Integer total) {
		this.total = total;
	}
 
	public String getNumero_lote_sere() {
		return numero_lote_sere;
	}
	public void setNumero_lote_sere(String numero_lote_sere) {
		this.numero_lote_sere = numero_lote_sere;
	}
	public Calendar getData_expiracao() {
		return data_expiracao;
	}
	public void setData_expiracao(Calendar data_expiracao) {
		this.data_expiracao = data_expiracao;
	}
	public int getQdt_recebida() {
		return qdt_recebida;
	}
	public void setQdt_recebida(int qdt_recebida) {
		this.qdt_recebida = qdt_recebida;
	}
	public int getnSeAplica() {
		return nSeAplica;
	}
	public void setnSeAplica(int nSeAplica) {
		this.nSeAplica = nSeAplica;
	}

	public int getFK_num_difnoinventario() {
		return FK_num_difnoinventario;
	}
	public void setFK_num_difnoinventario(int fK_num_difnoinventario) {
		FK_num_difnoinventario = fK_num_difnoinventario;
	}
	public String getNum_difnoinventario() {
		return num_difnoinventario;
	}
	public void setNum_difnoinventario(String num_difnoinventario) {
		this.num_difnoinventario = num_difnoinventario;
	}
	public double getTaxas() {
		return taxas;
	}
	public void setTaxas(double taxas) {
		this.taxas = taxas;
	}
	public double getTaxa_emkz() {
		return taxa_emkz;
	}
	public void setTaxa_emkz(double taxa_emkz) {
		this.taxa_emkz = taxa_emkz;
	}
	public Integer getFK_receita() {
		return FK_receita;
	}
	public void setFK_receita(Integer fK_receita) {
		FK_receita = fK_receita;
	}
	public Double getTxa_percentual() {
		return txa_percentual;
	}
	public void setTxa_percentual(Double txa_percentual) {
		this.txa_percentual = txa_percentual;
	}
	public Double getTxa_emkwanza() {
		return txa_emkwanza;
	}
	public void setTxa_emkwanza(Double txa_emkwanza) {
		this.txa_emkwanza = txa_emkwanza;
	}
	public String getProduto() {
		return produto;
	}
	public void setProduto(String produto) {
		this.produto = produto;
	}
	public String getNum_receita() {
		return num_receita;
	}
	public void setNum_receita(String num_receita) {
		this.num_receita = num_receita;
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
	public int getFK_devolucao_venda() {
		return FK_devolucao_venda;
	}
	public void setFK_devolucao_venda(int fK_devolucao_venda) {
		FK_devolucao_venda = fK_devolucao_venda;
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
	public int getFk_produto() {
		return fk_produto;
	}
	public void setFk_produto(int fk_produto) {
		this.fk_produto = fk_produto;
	}
	public int getFK_servico_obrigatorio() {
		return FK_servico_obrigatorio;
	}
	public void setFK_servico_obrigatorio(int fK_servico_obrigatorio) {
		FK_servico_obrigatorio = fK_servico_obrigatorio;
	}
	public int getFk_grupo() {
		return fk_grupo;
	}
	public void setFk_grupo(int fk_grupo) {
		this.fk_grupo = fk_grupo;
	}
	public String getRequisicao_original() {
		return requisicao_original;
	}
	public void setRequisicao_original(String requisicao_original) {
		this.requisicao_original = requisicao_original;
	}
	public String getRequerente_interno() {
		return requerente_interno;
	}
	public void setRequerente_interno(String requerente_interno) {
		this.requerente_interno = requerente_interno;
	}
	public int getQtd_por_receber() {
		return qtd_por_receber;
	}
	public void setQtd_por_receber(int qtd_por_receber) {
		this.qtd_por_receber = qtd_por_receber;
	}
	public int getValor_positivo() {
		return valor_positivo;
	}
	public void setValor_positivo(int valor_positivo) {
		this.valor_positivo = valor_positivo;
	}
	public int getValor_negativo() {
		return valor_negativo;
	}
	public void setValor_negativo(int valor_negativo) {
		this.valor_negativo = valor_negativo;
	}
	public int getFK_requerente_interno() {
		return FK_requerente_interno;
	}
	public void setFK_requerente_interno(int fK_requerente_interno) {
		FK_requerente_interno = fK_requerente_interno;
	}
	public int getEncomendaValidada() {
		return encomendaValidada;
	}
	public void setEncomendaValidada(int encomendaValidada) {
		this.encomendaValidada = encomendaValidada;
	}
	
	Calendar toCalendar(){
		Calendar dataCalendar=null;
		try {
			Date date = new SimpleDateFormat("dd/MM/yyyy").parse(this.getDataEmTexto());
			dataCalendar = Calendar.getInstance();
			dataCalendar.setTime(date);
			} catch (ParseException e) {
				throw new RuntimeException(e);
			}
		return dataCalendar;
	}
	private String getDataEmTexto() {
		return dataEmTexto;
	}
	private void setDataEmTexto(String dataEmTexto) {
		this.dataEmTexto = dataEmTexto;
	}
}


