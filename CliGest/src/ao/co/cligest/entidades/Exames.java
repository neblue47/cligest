package ao.co.cligest.entidades;
import java.io.Serializable;
import java.sql.Date;
import java.sql.Time;
import java.util.Calendar;

@SuppressWarnings("serial")
public class Exames implements Serializable {
 
	/* Grupo de nalise*/
	private int id_grupo_analises_clinicas;
	private String grupo_danalise;
	private int id_sub_grupo;
	private String nome_sub_grupo;
	
	private int id_unidade;
	private String unidade;
	private int id_resultado;
	private String resultado;
	private int id_valor_resultado;
	private int id_grupo_lab;
	private String grupo_lab;
	
	/*Servicos de analises clinicas*/
	private int id_servicodeanalise_clinica;
	private int FK_grupo_analise_clinica;	
	private Double preco;
	private double preco_txa;
	private String analise_clinica;
	private String descricao_servico_analise;
	private int codigobarra;
	private int sexo;
	private int disponibilidade;
	private int FK_servico_obrigatorio;
	private int id;
	private String nome_categoria;
	
	/*tblexamerequisitado*/
	private int FK_servico_exame;
	private int FK_funcionario;
	private int FK_paciente;
	private int fk_profissional;
	private int FK_tipo_dcobertura;
	private int FK_metodo_pagamento;
	
	private String faixa_etaria;
	private int idade_inicial;
	private int idade_final;

	private int gestante;
	private int FK_numero_do_servico_de_exame;
	private String recomendacao;
	private String hora_agendamento;
	private Calendar data_agendamento;
	private int fk_dia_semana;
	private int FK_provenienca_exame;
//	Tabela tblnumerodoexame
	private int id_numero_do_exame;
	private String numero_do_exame;
	
// Tabela tblamostra
	private int id_amostra;
	private int FK_tipo_deamostra;
	private String observacao_amostra;
	private int FK_exame;
	private int FK_estatu_darecolha;
	
	//Tabela tblTipoDeAmostra
	private int id_tipo_demostra;
	private String tipo_deamostra;
	 
	private int fk_taxa;
	private String taxa;
	private int taxa_pre;
	private int FK_convenio;
	private int FK_plano;
	
	private int quantidade;
	private double subtotal;
	
	private int id_parametro_lab;
	private String parametro_lab;
	
	//
	private Calendar data_darecolha;
	private Calendar data_nascimento;
	
	//
	private Calendar data_inicio;
	private Calendar data_fim;
	
	//Para Agendar teste de qualidade
	private String numeroDoServico;
	private String nomeDaEmpresa;
	private String numeroDaEmpresa;
	private String dataAgendaTQ;
	private int id_agen_test_quali;
	private int FK_servico_tq;
	private int FK_requisicao;
	private String abreviatura;
	private int categoria;
	private String nome_genero;
	private String nome_disponibilidade;
	
	
	//Para confirmar teste de qualidade
	private int id_testedequalidade;
	private String FK_numero_do_servico;
	private int FK_servico_teste;
	private int FK_tipo_servicoEx;
	private int FK_empresa;
	
	private int id_valores_referencias_lab;
	public int getId_valores_referencias_lab() {
		return id_valores_referencias_lab;
	}
	public void setId_valores_referencias_lab(int id_valores_referencias_lab) {
		this.id_valores_referencias_lab = id_valores_referencias_lab;
	}

	

	public int getId_grupo_lab() {
		return id_grupo_lab;
	}
	public void setId_grupo_lab(int id_grupo_lab) {
		this.id_grupo_lab = id_grupo_lab;
	}
	public String getGrupo_lab() {
		return grupo_lab;
	}
	public void setGrupo_lab(String grupo_lab) {
		this.grupo_lab = grupo_lab;
	}
	public int getId_resultado() {
		return id_resultado;
	}
	public void setId_resultado(int id_resultado) {
		this.id_resultado = id_resultado;
	}
	public String getResultado() {
		return resultado;
	}
	public void setResultado(String resultado) {
		this.resultado = resultado;
	}
	


	public int getId_valor_resultado() {
		return id_valor_resultado;
	}
	public void setId_valor_resultado(int id_valor_resultado) {
		this.id_valor_resultado = id_valor_resultado;
	}



	private String valor_minimo_masculino;
	private String valor_maximo_masculino;
	private String valor_minimo_feminino;
	private String valor_maximo_feminino;
	private int fk_unidade_genero;
	private String valor_minimo_faixa_etaria;
	private String valor_maximo_faixa_etaria;
	private int fk_unidade_faixa_etaria;
	
	public int getFK_requisicao() {
		return FK_requisicao;
	}
	public int getFK_tipo_servicoEx() {
		return FK_tipo_servicoEx;
	}

	public void setFK_tipo_servicoEx(int fK_tipo_servicoEx) {
		FK_tipo_servicoEx = fK_tipo_servicoEx;
	}

	public void setFK_requisicao(int fK_requisicao) {
		FK_requisicao = fK_requisicao;
	}

	public int getFK_servico_tq() {
		return FK_servico_tq;
	}

	public void setFK_servico_tq(int fK_servico_tq) {
		FK_servico_tq = fK_servico_tq;
	}

	public void setId_agen_test_quali(int id_agen_test_quali) {
		this.id_agen_test_quali = id_agen_test_quali;
	}

	public int getId_agen_test_quali() {
		return id_agen_test_quali;
	}

	public void setIdAgenTestQuali(int id_agen_test_quali) {
		this.id_agen_test_quali = id_agen_test_quali;
	}

	public String getDataAgendaTQ() {
		return dataAgendaTQ;
	}

	public void setDataAgendaTQ(String dataAgendaTQ) {
		this.dataAgendaTQ = dataAgendaTQ;
	}

	public int getId_testedequalidade() {
		return id_testedequalidade;
	}

	public void setId_testedequalidade(int id_testedequalidade) {
		this.id_testedequalidade = id_testedequalidade;
	}

	public String getFK_numero_do_servico() {
		return FK_numero_do_servico;
	}

	public void setFK_numero_do_servico(String fK_numero_do_servico) {
		FK_numero_do_servico = fK_numero_do_servico;
	}

	public int getFK_servico_teste() {
		return FK_servico_teste;
	}

	public void setFK_servico_teste(int fK_servico_teste) {
		FK_servico_teste = fK_servico_teste;
	}

	public int getFK_empresa() {
		return FK_empresa;
	}

	public void setFK_empresa(int fK_empresa) {
		FK_empresa = fK_empresa;
	}

	//Metodos Para agendar teste de qualidade
	public String getNumeroDoServico() {
		return numeroDoServico;
	}

	public void setNumeroDoServico(String numeroDoServico) {
		this.numeroDoServico = numeroDoServico;
	}

	public String getNomeDaEmpresa() {
		return nomeDaEmpresa;
	}

	public void setNomeDaEmpresa(String nomeDaEmpresa) {
		this.nomeDaEmpresa = nomeDaEmpresa;
	}

	public String getNumeroDaEmpresa() {
		return numeroDaEmpresa;
	}

	public void setNumeroDaEmpresa(String numeroDaEmpresa) {
		this.numeroDaEmpresa = numeroDaEmpresa;
	}

	public int getFK_metodo_pagamento() {
		return FK_metodo_pagamento;
	}
	
	public void setFK_metodo_pagamento(int fK_metodo_pagamento) {
		FK_metodo_pagamento = fK_metodo_pagamento;
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
	public int getQuantidade() {
		return quantidade;
	}
	
	
	public int getFK_convenio() {
		return FK_convenio;
	}

	public void setFK_convenio(int fK_convenio) {
		FK_convenio = fK_convenio;
	}

	public int getFK_plano() {
		return FK_plano;
	}

	public void setFK_plano(int fK_plano) {
		FK_plano = fK_plano;
	}
	public String getNome_categoria() {
		return nome_categoria;
	}

	public void setNome_categoria(String nome_categoria) {
		this.nome_categoria = nome_categoria;
	}



	//
	private int FK_exame_requisitado;
	private int fk_requisicao_dexame;
	private int FK_factura;
	private int total;
	
	//
	private String numero_factura;
	private String numero_processo;
	private String nomeCompleto;
	private String nome_funcionario;
	private Integer ano;
	private String genero;
	private Calendar data_da_facturacao;
	private Time hora_da_facturacao;
	
	//tblamostraprontaparaserexaminada
	private int id_pronto_aserexaminado;
	private String numero_doatendimento;
	private Calendar data_doatendimento;
	private Time hora_doatendimento;
	private String observacao_recomendacao;
	
	//tbllistadeesperadoexame
	private int id_lista_deespera;
	private Calendar Data_listade_espera;
	private Time hora_listade_espera;
	
//	tblfacturartratamento
	private int id_facturar_tratamento;
	private int FK_tratamento;
	private double preco_tratamento;
	private int imposto_selo;
	private double taxa_emkz;
	private double total_pago_tratamento;
	
	public int getId_lista_deespera() {
		return id_lista_deespera;
	}
	public void setId_lista_deespera(int id_lista_deespera) {
		this.id_lista_deespera = id_lista_deespera;
	}
	public Calendar getData_listade_espera() {
		return Data_listade_espera;
	}
	public void setData_listade_espera(Calendar data_listade_espera) {
		Data_listade_espera = data_listade_espera;
	}
	public Time getHora_listade_espera() {
		return hora_listade_espera;
	}
	public void setHora_listade_espera(Time hora_listade_espera) {
		this.hora_listade_espera = hora_listade_espera;
	}
	public int getId_pronto_aserexaminado() {
		return id_pronto_aserexaminado;
	}
	public void setId_pronto_aserexaminado(int id_pronto_aserexaminado) {
		this.id_pronto_aserexaminado = id_pronto_aserexaminado;
	}
	public String getNumero_doatendimento() {
		return numero_doatendimento;
	}
	public void setNumero_doatendimento(String numero_doatendimento) {
		this.numero_doatendimento = numero_doatendimento;
	}
	public Calendar getData_doatendimento() {
		return data_doatendimento;
	}
	public void setData_doatendimento(Calendar data_doatendimento) {
		this.data_doatendimento = data_doatendimento;
	}
	public Time getHora_doatendimento() {
		return hora_doatendimento;
	}
	public void setHora_doatendimento(Time hora_doatendimento) {
		this.hora_doatendimento = hora_doatendimento;
	}
	public String getObservacao_recomendacao() {
		return observacao_recomendacao;
	}
	public void setObservacao_recomendacao(String observacao_recomendacao) {
		this.observacao_recomendacao = observacao_recomendacao;
	}
	public String getNumero_factura() {
		return numero_factura;
	}
	public void setNumero_factura(String numero_factura) {
		this.numero_factura = numero_factura;
	}
	public String getNumero_processo() {
		return numero_processo;
	}
	public void setNumero_processo(String numero_processo) {
		this.numero_processo = numero_processo;
	}
	public String getNomeCompleto() {
		return nomeCompleto;
	}
	public void setNomeCompleto(String nomeCompleto) {
		this.nomeCompleto = nomeCompleto;
	}
	public Integer getAno() {
		return ano;
	}
	public void setAno(Integer ano) {
		this.ano = ano;
	}
	public String getGenero() {
		return genero;
	}
	public void setGenero(String genero) {
		this.genero = genero;
	}
	public Calendar getData_da_facturacao() {
		return data_da_facturacao;
	}
	public void setData_da_facturacao(Calendar data_da_facturacao) {
		this.data_da_facturacao = data_da_facturacao;
	}
	public Time getHora_da_facturacao() {
		return hora_da_facturacao;
	}
	public void setHora_da_facturacao(Time hora_da_facturacao) {
		this.hora_da_facturacao = hora_da_facturacao;
	}
	public int getFk_requisicao_dexame() {
		return fk_requisicao_dexame;
	}
	public void setFk_requisicao_dexame(int fk_requisicao_dexame) {
		this.fk_requisicao_dexame = fk_requisicao_dexame;
	}
	public int getTotal() 
	{
		return total;
	}
	public void setTotal(int total) {
		this.total = total;
	}
	public int getFK_exame_requisitado() {
		return FK_exame_requisitado;
	}
	public void setFK_exame_requisitado(int fK_exame_requisitado) {
		FK_exame_requisitado = fK_exame_requisitado;
	}
	public int getFK_factura() {
		return FK_factura;
	}
	public void setFK_factura(int fK_factura) {
		FK_factura = fK_factura;
	}
	public void setQuantidade(int quantidade) {
		this.quantidade = quantidade;
	}
	public double getSubtotal() {
		return subtotal;
	}
	public void setSubtotal(double subtotal) {
		this.subtotal = subtotal;
	}
	public int getId_grupo_analises_clinicas() {
		return id_grupo_analises_clinicas;
	}
	public void setId_grupo_analises_clinicas(int id_grupo_analises_clinicas) {
		this.id_grupo_analises_clinicas = id_grupo_analises_clinicas;
	}
	public String getGrupo_danalise() {
		return grupo_danalise;
	}
	public void setGrupo_danalise(String grupo_danalise) {
		this.grupo_danalise = grupo_danalise;
	}
	public int getId_servicodeanalise_clinica() {
		return id_servicodeanalise_clinica;
	}
	public void setId_servicodeanalise_clinica(int id_servicodeanalise_clinica) {
		this.id_servicodeanalise_clinica = id_servicodeanalise_clinica;
	}
	public int getFK_grupo_analise_clinica() {
		return FK_grupo_analise_clinica;
	}
	public void setFK_grupo_analise_clinica(int fK_grupo_analise_clinica) {
		FK_grupo_analise_clinica = fK_grupo_analise_clinica;
	}
	public Double getPreco() {
		return preco;
	}
	public void setPreco(Double preco) {
		this.preco = preco;
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
	public int getGestante() {
		return gestante;
	}
	public void setGestante(int gestante) {
		this.gestante = gestante;
	}
	
	
	public int getFK_numero_do_servico_de_exame() {
		return FK_numero_do_servico_de_exame;
	}
	public void setFK_numero_do_servico_de_exame(int fK_numero_do_servico_de_exame) {
		FK_numero_do_servico_de_exame = fK_numero_do_servico_de_exame;
	}
	public int getId_numero_do_exame() {
		return id_numero_do_exame;
	}
	public void setId_numero_do_exame(int id_numero_do_exame) {
		this.id_numero_do_exame = id_numero_do_exame;
	}
	public String getNumero_do_exame() {
		return numero_do_exame;
	}
	public void setNumero_do_exame(String numero_do_exame) {
		this.numero_do_exame = numero_do_exame;
	}
	public int getFk_profissional() {
		return fk_profissional;
	}
	public void setFk_profissional(int fk_profissional) {
		this.fk_profissional = fk_profissional;
	}
	public String getRecomendacao() {
		return recomendacao;
	}
	public void setRecomendacao(String recomendacao) {
		this.recomendacao = recomendacao;
	}
	public String getHora_agendamento() {
		return hora_agendamento;
	}
	public void setHora_agendamento(String hora_agendamento) {
		this.hora_agendamento = hora_agendamento;
	}
	public Calendar getData_agendamento() {
		return data_agendamento;
	}
	public void setData_agendamento(Calendar data_agendamento) {
		this.data_agendamento = data_agendamento;
	}
	public int getFk_dia_semana() {
		return fk_dia_semana;
	}
	public void setFk_dia_semana(int fk_dia_semana) {
		this.fk_dia_semana = fk_dia_semana;
	}
	public int getFk_taxa() {
		return fk_taxa;
	}
	public void setFk_taxa(int fk_taxa) {
		this.fk_taxa = fk_taxa;
	}
	public String getTaxa() {
		return taxa;
	}
	public void setTaxa(String taxa) {
		this.taxa = taxa;
	}
	public int getTaxa_pre() {
		return taxa_pre;
	}
	public void setTaxa_pre(int taxa_pre) {
		this.taxa_pre = taxa_pre;
	}
	public int getId_amostra() {
		return id_amostra;
	}
	public void setId_amostra(int id_amostra) {
		this.id_amostra = id_amostra;
	}
	public int getFK_tipo_deamostra() {
		return FK_tipo_deamostra;
	}
	public void setFK_tipo_deamostra(int fK_tipo_deamostra) {
		FK_tipo_deamostra = fK_tipo_deamostra;
	}
	public String getObservacao_amostra() {
		return observacao_amostra;
	}
	public void setObservacao_amostra(String observacao_amostra) {
		this.observacao_amostra = observacao_amostra;
	}
	public int getFK_exame() {
		return FK_exame;
	}
	public void setFK_exame(int fK_exame) {
		FK_exame = fK_exame;
	}
	public int getId_tipo_demostra() {
		return id_tipo_demostra;
	}
	public void setId_tipo_demostra(int id_tipo_demostra) {
		this.id_tipo_demostra = id_tipo_demostra;
	}
	public String getTipo_deamostra() {
		return tipo_deamostra;
	}
	
	public double getPreco_txa() {
		return preco_txa;
	}
	public void setPreco_txa(double preco_txa) {
		this.preco_txa = preco_txa;
	}
	public void setTipo_deamostra(String tipo_deamostra) {
		this.tipo_deamostra = tipo_deamostra;
	}
	public int getSexo() {
		return sexo;
	}
	public void setSexo(int sexo) {
		this.sexo = sexo;
	}
	public int getFK_estatu_darecolha() {
		return FK_estatu_darecolha;
	}
	public void setFK_estatu_darecolha(int fK_estatu_darecolha) {
		FK_estatu_darecolha = fK_estatu_darecolha;
	}
	public String getNome_funcionario() {
		return nome_funcionario;
	}
	public void setNome_funcionario(String nome_funcionario) {
		this.nome_funcionario = nome_funcionario;
	}
	public Calendar getData_darecolha() {
		return data_darecolha;
	}
	public void setData_darecolha(Calendar data_darecolha) {
		this.data_darecolha = data_darecolha;
	}
	public Calendar getData_nascimento() {
		return data_nascimento;
	}
	public void setData_nascimento(Calendar data_nascimento) {
		this.data_nascimento = data_nascimento;
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
	public int getFK_provenienca_exame() {
		return FK_provenienca_exame;
	}
	public void setFK_provenienca_exame(int fK_provenienca_exame) {
		FK_provenienca_exame = fK_provenienca_exame;
	}

	public int getDisponibilidade() {
		return disponibilidade;
	}

	public void setDisponibilidade(int disponibilidade) {
		this.disponibilidade = disponibilidade;
	}

	public int getFK_servico_obrigatorio() {
		return FK_servico_obrigatorio;
	}

	public void setFK_servico_obrigatorio(int fK_servico_obrigatorio) {
		FK_servico_obrigatorio = fK_servico_obrigatorio;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getAbreviatura() {
		return abreviatura;
	}

	public void setAbreviatura(String abreviatura) {
		this.abreviatura = abreviatura;
	}

	public int getCategoria() {
		return categoria;
	}

	public void setCategoria(int categoria) {
		this.categoria = categoria;
	}

	public String getNome_genero() {
		return nome_genero;
	}

	public void setNome_genero(String nome_genero) {
		this.nome_genero = nome_genero;
	}

	public String getNome_disponibilidade() {
		return nome_disponibilidade;
	}

	public void setNome_disponibilidade(String nome_disponibilidade) {
		this.nome_disponibilidade = nome_disponibilidade;
	}
	public int getId_sub_grupo() {
		return id_sub_grupo;
	}
	public void setId_sub_grupo(int id_sub_grupo) {
		this.id_sub_grupo = id_sub_grupo;
	}
	public String getNome_sub_grupo() {
		return nome_sub_grupo;
	}
	public void setNome_sub_grupo(String nome_sub_grupo) {
		this.nome_sub_grupo = nome_sub_grupo;
	}
	public int getId_unidade() {
		return id_unidade;
	}
	public void setId_unidade(int id_unidade) {
		this.id_unidade = id_unidade;
	}
	public String getUnidade() {
		return unidade;
	}
	public void setUnidade(String unidade) {
		this.unidade = unidade;
	}
	public String getFaixa_etaria() {
		return faixa_etaria;
	}
	public void setFaixa_etaria(String faixa_etaria) {
		this.faixa_etaria = faixa_etaria;
	}
	public int getIdade_inicial() {
		return idade_inicial;
	}
	public void setIdade_inicial(int idade_inicial) {
		this.idade_inicial = idade_inicial;
	}
	public int getIdade_final() {
		return idade_final;
	}
	public void setIdade_final(int idade_final) {
		this.idade_final = idade_final;
	}
	public int getId_parametro_lab() {
		return id_parametro_lab;
	}
	public void setId_parametro_lab(int id_parametro_lab) {
		this.id_parametro_lab = id_parametro_lab;
	}
	public String getParametro_lab() {
		return parametro_lab;
	}
	public void setParametro_lab(String parametro_lab) {
		this.parametro_lab = parametro_lab;
	}
	public String getValor_minimo_masculino() {
		return valor_minimo_masculino;
	}
	public void setValor_minimo_masculino(String valor_minimo_masculino) {
		this.valor_minimo_masculino = valor_minimo_masculino;
	}
	public String getValor_maximo_masculino() {
		return valor_maximo_masculino;
	}
	public void setValor_maximo_masculino(String valor_maximo_masculino) {
		this.valor_maximo_masculino = valor_maximo_masculino;
	}
	public String getValor_minimo_feminino() {
		return valor_minimo_feminino;
	}
	public void setValor_minimo_feminino(String valor_minimo_feminino) {
		this.valor_minimo_feminino = valor_minimo_feminino;
	}
	public String getValor_maximo_feminino() {
		return valor_maximo_feminino;
	}
	public void setValor_maximo_feminino(String valor_maximo_feminino) {
		this.valor_maximo_feminino = valor_maximo_feminino;
	}
	public int getFk_unidade_genero() {
		return fk_unidade_genero;
	}
	public void setFk_unidade_genero(int fk_unidade_genero) {
		this.fk_unidade_genero = fk_unidade_genero;
	}
	public String getValor_minimo_faixa_etaria() {
		return valor_minimo_faixa_etaria;
	}
	public void setValor_minimo_faixa_etaria(String valor_minimo_faixa_etaria) {
		this.valor_minimo_faixa_etaria = valor_minimo_faixa_etaria;
	}
	public String getValor_maximo_faixa_etaria() {
		return valor_maximo_faixa_etaria;
	}
	public void setValor_maximo_faixa_etaria(String valor_maximo_faixa_etaria) {
		this.valor_maximo_faixa_etaria = valor_maximo_faixa_etaria;
	}
	public int getFk_unidade_faixa_etaria() {
		return fk_unidade_faixa_etaria;
	}
	public void setFk_unidade_faixa_etaria(int fk_unidade_faixa_etaria) {
		this.fk_unidade_faixa_etaria = fk_unidade_faixa_etaria;
	}
	
}
