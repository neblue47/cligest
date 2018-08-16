package ao.co.cligest.entidades;

import java.io.Serializable;
import java.sql.Date;
import java.sql.Time;
import java.util.Calendar;
import java.util.GregorianCalendar;

import ao.co.cligest.dao.Formatando;

public class Paciente implements Serializable {
	
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	//tblEntidade
	private int pac_idade;
	 
	/*--------------------------------------------------------------*/
	private int ano_nascimento;
	private int mes_nascimento;
	private int dias_nascimento;
	private String data_em_string;
	private String lote_vacina;
	private boolean isConfirmado;
	private String color;

	private int id_entidade;
	private String numero_processo;
	private String nome_paciente;
	private String nome_doutor;
	private int p_consulta;
	private int p_externos;
	private int p_internados;
	private int p_banco_ue;
	private int FK_requsicao;
	private int FK_empresa;
	private String metodo_dpagamento;
	private int FK_consulta;
	private int opcao;
	private Date p_data;
	private Date s_data;
	private String nomePlano;
	private String morada;
	private int gruposg;
	private String gruposgnome;
	private String data_vacina;
	private String status;
	private int FK_status;

	private String cid;
	// tblCidadao ---
	private String nome;
	private String nomem;
	private String apelido;
	private Calendar dataNasc;
	private Integer est_civil;
	private Integer genero;
	private Integer FK_alta;
	private int FK_banco;
	private String numero_conta;

	private String nomegenero;
	private int idade;
	private String editfone;
	private String editfonep;
	private String nomeCompleto;

	// Dados relacionados a Estatistica
	private int masculino;
	private int feminino;
	private int procedimento;
	private int material;
	private int medicamento;
	private double perct_msc;
	private double perct_fmn;
	private int qtd_admitido;
	private double perc_admitido_msc;
	private double perc_admitido_fmn;
	private double perc_admitido;
	private int qdt_transferido;
	private int qdt_pagas;
	private int qtd_sessao;
	private int qdt_isenta;
	private double perc_pago;
	private double perc_isento;
	private double perc_transferido;
	private double perc_transferido_msc;
	private double perc_transferido_fmn;

	// Para representante de uma empresa
	private int Idcontacto_fornecedor;
	private int CE_cargo;
	private int CE_fornecedor;
		 
		 
		 public int getFK_empresa() {
			return FK_empresa;
		}


		public void setFK_empresa(int fK_empresa) {
			FK_empresa = fK_empresa;
		}


		public int getIdcontacto_fornecedor() {
			return Idcontacto_fornecedor;
		}
		public void setIdcontacto_fornecedor(int idcontacto_fornecedor) {
			Idcontacto_fornecedor = idcontacto_fornecedor;
		}
		public int getCE_cargo() {
			return CE_cargo;
		}
		public void setCE_cargo(int cE_cargo) {
			CE_cargo = cE_cargo;
		}
		public int getCE_fornecedor() {
			return CE_fornecedor;
		}
		public void setCE_fornecedor(int cE_fornecedor) {
			CE_fornecedor = cE_fornecedor;
		}
		
		// TABELA TIPO DE SERVICO
		public String getNomeCompleto() {
			return nomeCompleto;
		}
		public void setNomeCompleto(String nomeCompleto) {
			this.nomeCompleto = nomeCompleto;
		}
		private int id_tipo_de_servico;
		 private String tipo_de_servico;
		 private int id_consulta_confirmada;
		 private int id_triagem;
	
		 public int getId_triagem() {
			return id_triagem;
		}
		public void setId_triagem(int id_triagem) {
			this.id_triagem = id_triagem;
		}
		private int FK_consulta_marcada;
		 private Calendar data_confirmacao;
		 private String dataShows;
		 private String hora_daconfirmacao;
		 private String dia_da_semana;
		 private int dias_trabalhado;
		 private int FK_bairro;
		 private int idade_dopaciente;
		 private int FK_entidade_paciente;
		 private int FK_servico;
		 private int FK_dia_dasemana;
		
		// TABELA DOUTOR ESPECIALIZADO
		 private int FK_entidade;
		 private int FK_doutor;
		 private int FK_funcionario;
		
		private int FK_especialidade;
		
		private String especialidade;
		 private String numero_ordem;
		 private java.sql.Date data_agendamento;
		 private java.sql.Date data;
		 private Calendar data_do_agendamento;
		 
		 private String numero_da_consulta;
		 private int total_servico;
		 private double valor_total;
		 private double valor_media;
		
		// TABELA entidade
		 private int id;
		 private String num_fun;
		// TABELA SERVI�O
			 private int id_servico;
			 private String descricao;
			 private double preco;
			 private String servico;
			 private String encaminhados;
	
			 private String leito;
			 private String sector;
			 private String sala;
			 private int quantidade;
			 
			 private String tratamento,recomendacao;
			 private int FK_emergencia;
			 private int FK_urgencia;

// TABELA seguro
			 
			 public int getFK_emergencia() {
				return FK_emergencia;
			}
			public void setFK_emergencia(int fK_emergencia) {
				FK_emergencia = fK_emergencia;
			}
			public int getFK_urgencia() {
				return FK_urgencia;
			}
			public void setFK_urgencia(int fK_urgencia) {
				FK_urgencia = fK_urgencia;
			}
			public int getQuantidade() {
				return quantidade;
			}
			public String getTratamento() {
				return tratamento;
			}
			public void setTratamento(String tratamento) {
				this.tratamento = tratamento;
			}
			public String getRecomendacao() {
				return recomendacao;
			}
			public void setRecomendacao(String recomendacao) {
				this.recomendacao = recomendacao;
			}
			public void setQuantidade(int quantidade) {
				this.quantidade = quantidade;
			}
			public String getLeito() {
				return leito;
			}
			public void setLeito(String leito) {
				this.leito = leito;
			}
			public String getSector() {
				return sector;
			}
			public void setSector(String sector) {
				this.sector = sector;
			}
			public String getSala() {
				return sala;
			}
			public void setSala(String sala) {
				this.sala = sala;
			}
			public String getEncaminhados() {
				return encaminhados;
			}
			public void setEncaminhados(String encaminhados) {
				this.encaminhados = encaminhados;
			}
			private int KF_seguro_saude;
			 private int FK_fornecedor;  
			 private int FK_internamento;
			 private String nomefornecedor;
			 private String tipo_dcobertura;
			 private int FK_plano_dsaude;
			 private int id_tipo_dcobertura;
			 private String nif;
			 private String nota;
			 private String numero_doalvara;
			 private long telefoneemp;
			 private String emailemp;
			 private String enderecoemp;
			 private String paginaWeb;
			 private String numero_fornecedor;
			 
			 private String numero_dcartao;
			 private String empresa;
		
			// TABELA ISENÇÃO
				private int id_isencao;
				private String isencao;
				private String motivo;
				private int gestante;
				private int isento;
				private int FK_paciente;
				private int FK_consulta_confirmada;
				private Calendar dataIsencao;
				private Time hora;
				private int num_coluna;
				private String total_s;
				
				// Dados para Banco de Urgencia
				private Calendar data_registo;
				private String hora_registo;

			//			------------------------------- Isenção Aridja Cussunga
				public int getId_isencao() {
					return id_isencao;
				}
				public void setId_isencao(int id_isencao) {
					this.id_isencao = id_isencao;
				}
				public String getIsencao() {
					return isencao;
				}
				public void setIsencao(String isencao) {
					this.isencao = isencao;
				}
				public String getMotivo() {
					return motivo;
				}
				public void setMotivo(String motivo) {
					this.motivo = motivo;
				}
				
				public int getIsento() {
					return isento;
				}
				public void setIsento(int isento) {
					this.isento = isento;
				}
				public int getFK_paciente() {
					return FK_paciente;
				}
				public void setFK_paciente(int fK_paciente) {
					FK_paciente = fK_paciente;
				}
				public Calendar getDataIsencao() {
					return dataIsencao;
				}
				public void setDataIsencao(Calendar dataIsencao) {
					this.dataIsencao = dataIsencao;
				}
				public Time getHora() {
					return hora;
				}
				public void setHora(Time hora) {
					this.hora = hora;
				}
				public int getNum_coluna() {
					return num_coluna;
				}
				public void setNum_coluna(int num_coluna) {
					this.num_coluna = num_coluna;
				}
				public String getTotal_s() {
					return total_s;
				}
				public void setTotal_s(String total) {
					this.total_s = total;
				}
		public int getGestante() {
				return gestante;
			}
		public String getNomegenero() {
			return nomegenero;
		}
		public void setNomegenero(String nomegenero) {
			this.nomegenero = nomegenero;
		}
		private Integer tipo_doc;
		 private String numero_doc;
		 
		 public String getNumero_doc() {
			return numero_doc;
		}
		public void setNumero_doc(String numero_doc) {
			this.numero_doc = numero_doc;
		}
		 
		 private Integer pais;
		 
		 private Integer provincia;
		 
		 private Integer municipio;
		 private String nomeMunicipio;
		public String getNomeMunicipio() {
			return nomeMunicipio;
		}
		public void setNomeMunicipio(String nomeMunicip) {
			nomeMunicipio = nomeMunicip;
		}
		
		
		public String getDataShow() {
			return dataShows;
		}
		public void setDataShow(String data) {
			this.dataShows = new Formatando().data(data).toString();
		}
		//tblDistrito
		 private Integer distrito;
		 private String nomeDistrito;
		public String getNomeDistrito() {
			return nomeDistrito;
		}
		public void setNomeDistrito(String nomeDistr) {
			nomeDistrito = nomeDistr;
		}
		//tblBairro
		 private Integer bairro;
		 private String nomeBairro;
		public String getNomeBairro() {
			return nomeBairro;
		}
		public void setNomeBairro(String nomeBai) {
			nomeBairro = nomeBai;
		}
		//tblProfiss�o 
		 private Integer profissao;
		 //tblTelefone
		 private Long telefone;
		 private Long telefones;
		 //tblEmail
		 private String email;
		 //tblMorada
		 private String endereco;
		 private String enderecos;
		 //tblFuncionario
		 //tblParente
		 private String nomep;
		 private Long telefonep;
		  
		 //tblArquivos
		 private Integer arquivo;
		 public Integer getArquivo() {
			return arquivo;
		}
		public void setArquivo(Integer arquivo) {
			this.arquivo = arquivo;
		}
		public String getLocalArq() {
			return localArq;
		}
		public void setLocalArq(String localArq) {
			this.localArq = localArq;
		}
		public String getNomeArq() {
			return nomeArq;
		}
		public void setNomeArq(String nomeArq) { 
			this.nomeArq = nomeArq;
		}
		private String localArq;
		 private String nomeArq;
		 
		 // extras 
		 private String nomeEsp;
		 private String nomeFun;
		 private String nomeProv;
		 private String nomePais;
		 private String nomeProf;
		 private String nomeAfl;
		 private int idAfiliacao;
		 public int getIdAfiliacao() {
			return idAfiliacao;
		}
		public void setIdAfiliacao(int idAfiliacao) {
			this.idAfiliacao = idAfiliacao;
		}
		private String nomeDoc;
		 private String nomeEC;
		private String sanguineo;
		
		public int getId_entidade() {
			return id_entidade;
		}
		public void setId_entidade(int id_entidade) {
			this.id_entidade = id_entidade;
		}
		
		public String getNumero_processo() {
			return numero_processo;
		}
		public void setNumero_processo(String numero_processo) {
			this.numero_processo = numero_processo;
		}
		public String getNome() {
			return nome;
		}
		public void setNome(String nome) {
			this.nome = nome;
		}
		public String getNomem() {
			return nomem;
		}
		public void setNomem(String nomem) {
			this.nomem = nomem;
		}
		public String getApelido() {
			return apelido;
		}
		public void setApelido(String apelido) {
			this.apelido = apelido;
		}
		public Calendar getDataNasc() {
			return dataNasc;
		}
		public void setDataNasc(Calendar dataNasc) {
			this.dataNasc = dataNasc;
		}
		public Integer getEst_civil() {
			return est_civil;
		}
		public void setEst_civil(Integer est_civil) {
			this.est_civil = est_civil;
		}
		public Integer getGenero() {
			return genero;
		}
		public void setGenero(Integer genero) {
			this.genero = genero;
		}
		public Integer getTipo_doc() {
			return tipo_doc;
		}
		public void setTipo_doc(Integer tipo_doc) {
			this.tipo_doc = tipo_doc;
		}
		public Integer getPais() {
			return pais;
		}
		public void setPais(Integer pais) {
			this.pais = pais;
		}
		public Integer getProvincia() {
			return provincia;
		}
		public void setProvincia(Integer provincia) {
			this.provincia = provincia;
		}
		public Integer getMunicipio() {
			return municipio;
		}
		public void setMunicipio(Integer municipio) {
			this.municipio = municipio;
		}
		public Integer getDistrito() {
			return distrito;
		}
		public void setDistrito(Integer distrito) {
			this.distrito = distrito;
		}
		public Integer getBairro() {
			return bairro;
		}
		public void setBairro(Integer bairro) {
			this.bairro = bairro;
		}
		public Integer getProfissao() {
			return profissao;
		}
		public void setProfissao(Integer profissao) {
			this.profissao = profissao;
		}
		public Long getTelefone() {
			return telefone;
		}
		public void setTelefone(Long telefone) {
			this.telefone = telefone;
		}
		public Long getTelefones() {
			return telefones;
		}
		public void setTelefones(Long telefones) {
			this.telefones = telefones;
		}
		public String getEmail() {
			return email;
		}
		public void setEmail(String email) {
			this.email = email;
		}
		public String getEndereco() {
			return endereco;
		}
		public void setEndereco(String endereco) {
			this.endereco = endereco;
		}
		public String getEnderecos() {
			return enderecos;
		}
		public void setEnderecos(String enderecos) {
			this.enderecos = enderecos;
		}
		public String getNomep() {
			return nomep;
		}
		public void setNomep(String nomep) {
			this.nomep = nomep;
		}
		public long getTelefonep() {
			return telefonep;
		}
		public void setTelefonep(Long telefonep) {
			this.telefonep = telefonep;
		}
		public String getNomeEsp() {
			return nomeEsp;
		}
		public void setNomeEsp(String nomeEsp) {
			this.nomeEsp = nomeEsp;
		}
		public String getNomeFun() {
			return nomeFun;
		}
		public void setNomeFun(String nomeFun) {
			this.nomeFun = nomeFun;
		}
		public String getNomeProv() {
			return nomeProv;
		}
		public void setNomeProv(String nomeProv) {
			this.nomeProv = nomeProv;
		}
		public String getNomePais() {
			return nomePais;
		}
		public void setNomePais(String nomePais) {
			this.nomePais = nomePais;
		}
		public String getNomeProf() {
			return nomeProf;
		}
		public void setNomeProf(String nomeProf) {
			this.nomeProf = nomeProf;
		}
		public String getNomeAfl() {
			return nomeAfl;
		}
		public void setNomeAfl(String nomeAfl) {
			this.nomeAfl = nomeAfl;
		}
		public String getNomeDoc() {
			return nomeDoc;
		}
		public void setNomeDoc(String nomeDoc) {
			this.nomeDoc = nomeDoc;
		}
		public String getNomeEC() {
			return nomeEC;
		}
		public void setNomeEC(String nomeEC) {
			this.nomeEC = nomeEC;
		}
		public int getId_tipo_de_servico() {
			return id_tipo_de_servico;
		}
		public void setId_tipo_de_servico(int id_tipo_de_servico) {
			this.id_tipo_de_servico = id_tipo_de_servico;
		}
		public String getTipo_de_servico() {
			return tipo_de_servico;
		}
		public void setTipo_de_servico(String tipo_de_servico) {
			this.tipo_de_servico = tipo_de_servico;
		}
		public int getId_confirmada() {
			return id_consulta_confirmada;
		}
		public void setId_confirmada(int id_consulta_confirmada) {
			this.id_consulta_confirmada = id_consulta_confirmada;
		}
		public int getFK_consulta_marcada() {
			return FK_consulta_marcada;
		}
		public void setFK_consulta_marcada(int fK_consulta_marcada) {
			FK_consulta_marcada = fK_consulta_marcada;
		}
		public Calendar getData_confirmacao() {
			return data_confirmacao;
		}
		public void setData_confirmacao(Calendar data_confirmacao) {
			this.data_confirmacao = data_confirmacao;
		}
		public String getHora_daconfirmacao() {
			return hora_daconfirmacao;
		}
		public void setHora_daconfirmacao(String hora_daconfirmacao) {
			this.hora_daconfirmacao = hora_daconfirmacao;
		}
		public String getDia_da_semana() {
			return dia_da_semana;
		}
		public void setDia_da_semana(String dia_da_semana) {
			this.dia_da_semana = dia_da_semana;
		}
		public int getFK_bairro() {
			return FK_bairro;
		}
		public void setFK_bairro(int fK_bairro) {
			FK_bairro = fK_bairro;
		}
		public int getIdade_dopaciente() {
			return idade_dopaciente;
		}
		public void setIdade_dopaciente(int idade_dopaciente) {
			this.idade_dopaciente = idade_dopaciente;
		}
		public int getFK_entidade_paciente() {
			return FK_entidade_paciente;
		}
		public void setFK_entidade_paciente(int fK_entidade_paciente) {
			FK_entidade_paciente = fK_entidade_paciente;
		}
		public int getFK_servico() {
			return FK_servico;
		}
		public void setFK_servico(int fK_servico) {
			FK_servico = fK_servico;
		}
		public int getFK_dia_dasemana() {
			return FK_dia_dasemana;
		}
		public void setFK_dia_dasemana(int fK_dia_dasemana) {
			FK_dia_dasemana = fK_dia_dasemana;
		}
		public int getFK_entidade() {
			return FK_entidade;
		}
		public void setFK_entidade(int fK_entidade) {
			FK_entidade = fK_entidade;
		}
		public int getFK_doutor() {
			return FK_doutor;
		}
		public void setFK_doutor(int doutor) {
			FK_doutor = doutor;
		}
		public int getFK_funcionario() {
			return FK_funcionario;
		}
		public void setFK_funcionario(int fK_funcionario) {
			FK_funcionario = fK_funcionario;
		}
		public int getFK_especialidade() {
			return FK_especialidade;
		}
		public void setFK_especialidade(int fK_especialidade) {
			FK_especialidade = fK_especialidade;
		}
		public String getEspecialidade() {
			return especialidade;
		}
		public void setEspecialidade(String especialidade) {
			this.especialidade = especialidade;
		}
		public String getNumero_ordem() {
			return numero_ordem;
		}
		public void setNumero_ordem(String numero_ordem) {
			this.numero_ordem = numero_ordem;
		}
		public java.sql.Date getData_agendamento() {
			return data_agendamento;
		}
		public void setData_agendamento(java.sql.Date data_agendamento) {
			this.data_agendamento = data_agendamento;
		}
		public java.sql.Date getData() {
			return data;
		}
		public void setData(java.sql.Date data) {
			this.data = data;
		}
		public Calendar getData_do_agendamento() {
			return data_do_agendamento;
		}
		public void setData_do_agendamento(Calendar data_do_agendamento) {
			this.data_do_agendamento = data_do_agendamento;
		}
		public int getId() {
			return id;
		}
		public void setId(int id) {
			this.id = id;
		}
		public String getNum_fun() {
			return num_fun;
		}
		public void setNum_fun(String num_fun) {
			this.num_fun = num_fun;
		}
		public int getId_servico() {
			return id_servico;
		}
		public void setId_servico(int id_servico) {
			this.id_servico = id_servico;
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
		 
		 public int getId_consulta_confirmada() {
				return id_consulta_confirmada;
			}
			public void setId_consulta_confirmada(int id_consulta_confirmada) {
				this.id_consulta_confirmada = id_consulta_confirmada;
			}
			
			public int getIdade() {
				return idade;
			}
			
			public void setIdade(Date data) 
			{
				
				Calendar dateOfBirth = new GregorianCalendar();
				dateOfBirth.setTime(data);
				Calendar today = Calendar.getInstance();
				int idade = today.get(Calendar.YEAR) - dateOfBirth.get(Calendar.YEAR);
				dateOfBirth.add(Calendar.YEAR, idade);
				if (today.before(dateOfBirth)) 
				{
					idade--;
				}
				this.idade = idade;
			}
			public void setGestante(int gest ) {
				this.gestante = gest;
				
			}
			
			public String getNumero_da_consulta() {
				return numero_da_consulta;
			}
			public void setNumero_da_consulta(String numero_da_consulta) {
				this.numero_da_consulta = numero_da_consulta;
			}
			public String getDataShows() {
				return dataShows;
			}
			public void setDataShows(String dataShows) {
				this.dataShows = dataShows;
			}
			public int getKF_seguro_saude() {
				return KF_seguro_saude;
			}
			public void setKF_seguro_saude(int kF_seguro_saude) {
				KF_seguro_saude = kF_seguro_saude;
			}
			public int getFK_fornecedor() {
				return CE_fornecedor;
			}
			public void setFK_fornecedor(int fK_fornecedor) {
				CE_fornecedor = fK_fornecedor;
			}
			
			public String getNumero_fornecedor() {
				return numero_fornecedor;
			}
			public void setNumero_fornecedor(String numero_fornecedor) {
				this.numero_fornecedor = numero_fornecedor;
			}
			public String getPaginaWeb() {
				return paginaWeb;
			}
			public void setPaginaWeb(String paginaWeb) {
				this.paginaWeb = paginaWeb;
			}
			
			public String getNomefornecedor() {
				return nomefornecedor;
			}
			public void setNomefornecedor(String nomefornecedor) {
				this.nomefornecedor = nomefornecedor;
			}
			public String getTipo_dcobertura() {
				return tipo_dcobertura;
			}
			public void setTipo_dcobertura(String tipo_dcobertura) {
				this.tipo_dcobertura = tipo_dcobertura;
			}
			public int getFK_plano_dsaude() {
				return FK_plano_dsaude;
			}
			public void setFK_plano_dsaude(int fK_plano_dsaude) {
				FK_plano_dsaude = fK_plano_dsaude;
			}
			public int getId_tipo_dcobertura() {
				return id_tipo_dcobertura;
			}
			public void setId_tipo_dcobertura(int id_tipo_dcobertura) {
				this.id_tipo_dcobertura = id_tipo_dcobertura;
			}
			public String getNif() {
				return nif;
			}
			public void setNif(String nif) {
				this.nif = nif;
			}
			public String getNota() {
				return nota;
			}
			public void setNota(String nota) {
				this.nota = nota;
			}
			public String getNumero_doalvara() {
				return numero_doalvara;
			}
			public void setNumero_doalvara(String numero_doalvara) {
				this.numero_doalvara = numero_doalvara;
			}
			public long getTelefoneemp() {
				return telefoneemp;
			}
			public void setTelefoneemp(long telefoneemp) {
				this.telefoneemp = telefoneemp;
			}
			public String getEmailemp() {
				return emailemp;
			}
			public void setEmailemp(String emailemp) {
				this.emailemp = emailemp;
			}
			public String getEnderecoemp() {
				return enderecoemp;
			}
			public void setEnderecoemp(String enderecoemp) {
				this.enderecoemp  = enderecoemp;
			}
			public String getNumero_dcartao() {
				return numero_dcartao;
			}
			public void setNumero_dcartao(String numero_dcartao) {
				this.numero_dcartao = numero_dcartao;
			}
			public String getEmpresa() {
				return empresa;
			}
			public void setEmpresa(String empresa) {
				this.empresa = empresa;
			}
			 
			public String getEditfone() {
				return editfone;
			}
			public void setEditfone(long editfone) {
				// 244924044145
				String fonetmp = "" + editfone;
				if(fonetmp.length()>2){
					try {
						String codp = fonetmp.substring(0, 3);
						String num1 = fonetmp.substring(3, 6);
						String num2 = fonetmp.substring(6, 9);
						String num3 = fonetmp.substring(9);
						String fonet = codp + " " + num1 + "-" + num2 + "-" + num3;
						this.editfone = fonet;
					} catch (NumberFormatException er) {
						System.out.print(er);
					}
				}
				else
				{
					this.editfone = "";
				}
			}
			
			//Comeco do Representante da empresa
			private String primeiroNome;
			private String ultimoNome;
			private long telefoneRep;
			private String enderecoRep;
			private String emailRep;
			private String notaRep;
			private String cargoRep;
			
			public String getCargoRep() {
				return cargoRep;
			}
			public void setCargoRep(String cargoRep) {
				this.cargoRep = cargoRep;
			}
			public String getPrimeiroNome() {
				return primeiroNome;
			}
			public void setPrimeiroNome(String primeiroNome) {
				this.primeiroNome = primeiroNome;
			}
			public String getUltimoNome() {
				return ultimoNome;
			}
			public void setUltimoNome(String ultimoNome) {
				this.ultimoNome = ultimoNome;
			}
			public Long getTelefoneRep() {
				return telefoneRep;
			}
			public void setTelefoneRep(long telefoneRep) {
				this.telefoneRep = telefoneRep;
			}
			public String getEnderecoRep() {
				return enderecoRep;
			}
			public void setEnderecoRep(String enderecoRep) {
				this.enderecoRep = enderecoRep;
			}
			public String getEmailRep() {
				return emailRep;
			}
			public void setEmailRep(String emailRep) {
				this.emailRep = emailRep;
			}
			public String getNotaRep() {
				return notaRep;
			}
			public void setNotaRep(String notaRep) {
				this.notaRep = notaRep;
			}
			
			//fim do representante da empresa
			
			public String getEditfonep() {
				return editfonep;
			}
			public void setEditfonep(long editfonep) {
				// 244924044145
				String fonetmp = "" + editfonep;
				if(fonetmp.length()>2){
					try {
						String codp = fonetmp.substring(0, 3);
						String num1 = fonetmp.substring(3, 6);
						String num2 = fonetmp.substring(6, 9);
						String num3 = fonetmp.substring(9);
						String fonet = codp + " " + num1 + "-" + num2 + "-" + num3;
						this.editfonep = fonet;
					} catch (NumberFormatException er) {
						System.out.print(er);
					}
				}
				else
				{
					this.editfonep = "";
				}
			}
			public int getFK_consulta_confirmada() {
				return FK_consulta_confirmada;
			}
			public void setFK_consulta_confirmada(int fK_consulta_confirmada) {
				FK_consulta_confirmada = fK_consulta_confirmada;
			}
			public String getNome_paciente() {
				return nome_paciente;
			}
			public void setNome_paciente(String nome_completo) {
				this.nome_paciente = nome_completo;
			}
			public String getNome_doutor() {
				return nome_doutor;
			}
			public void setNome_doutor(String nome_doutor) {
				this.nome_doutor = nome_doutor;
			}
			public Calendar getData_registo() {
				return data_registo;
			}
			public void setData_registo(Calendar data_registo) {
				this.data_registo = data_registo;
			}
			
			
			public int getProcedimento() {
				return procedimento;
			}
			public void setProcedimento(int procedimento) {
				this.procedimento = procedimento;
			}
			public int getMaterial() {
				return material;
			}
			public void setMaterial(int material) {
				this.material = material;
			}
			public int getMedicamento() {
				return medicamento;
			}
			public void setMedicamento(int medicamento) {
				this.medicamento = medicamento;
			}
			public String getHora_registo() {
				return hora_registo;
			}
			public void setHora_registo(String hora_registo) {
				this.hora_registo = hora_registo;
			}
			
			public String getSanguineo() {
				return sanguineo;
			}
			public void setSanguineo(String sanguineo) {
				this.sanguineo = sanguineo;
			}
			public int getTotal_servico() {
				return total_servico;
			}
			public void setTotal_servico(int total_servico) {
				this.total_servico = total_servico;
			}
			public double getValor_total() {
				return valor_total;
			}
			public void setValor_total(double valor_total) {
				this.valor_total = valor_total;
			}
			public int getMasculino() {
				return masculino;
			}
			public void setMasculino(int masculino) {
				this.masculino = masculino;
			}
			public int getFeminino() {
				return feminino;
			}
			public void setFeminino(int feminino) {
				this.feminino = feminino;
			}
			public double getPerct_msc() {
				return perct_msc;
			}
			public void setPerct_msc(double perct_msc) {
				this.perct_msc = perct_msc;
			}
			public double getPerct_fmn() {
				return perct_fmn;
			}
			public void setPerct_fmn(double perct_fmn) {
				this.perct_fmn = perct_fmn;
			}
			public int getQtd_admitido() {
				return qtd_admitido;
			}
			public void setQtd_admitido(int qtd_admitido) {
				this.qtd_admitido = qtd_admitido;
			}
			 
			public void setQdt_admitido_msc(int qdt_admitido_msc) {
				this.perc_admitido_msc = qdt_admitido_msc;
			}
			 
			 
			public double getPerc_admitido() {
				return perc_admitido;
			}
			public void setPerc_admitido(double perc_admitido) {
				this.perc_admitido = perc_admitido;
			}
			public int getQdt_transferido() {
				return qdt_transferido;
			}
			public void setQdt_transferido(int qdt_transferido) {
				this.qdt_transferido = qdt_transferido;
			}
			public double getPerc_transferido() {
				return perc_transferido;
			}
			public void setPerc_transferido(double perc_transferido) {
				this.perc_transferido = perc_transferido;
			}
			public double getPerc_admitido_msc() {
				return perc_admitido_msc;
			}
			public void setPerc_admitido_msc(double perc_admitido_msc) {
				this.perc_admitido_msc = perc_admitido_msc;
			}
			public double getPerc_admitido_fmn() {
				return perc_admitido_fmn;
			}
			public void setPerc_admitido_fmn(double perc_admitido_fmn) {
				this.perc_admitido_fmn = perc_admitido_fmn;
			}
			public double getPerc_transferido_msc() {
				return perc_transferido_msc;
			}
			public void setPerc_transferido_msc(double perc_transferido_msc) {
				this.perc_transferido_msc = perc_transferido_msc;
			}
			public double getPerc_transferido_fmn() {
				return perc_transferido_fmn;
			}
			public void setPerc_transferido_fmn(double perc_transferido_fmn) {
				this.perc_transferido_fmn = perc_transferido_fmn;
			}
			public String getCid() {
				return cid;
			}
			public void setCid(String cid) {
				this.cid = cid;
			}
			public int getQdt_pagas() {
				return qdt_pagas;
			}
			public void setQdt_pagas(int qdt_pagas) {
				this.qdt_pagas = qdt_pagas;
			}
			
			public int getQtd_sessao() {
				return qtd_sessao;
			}
			public void setQtd_sessao(int qtd_sessao) {
				this.qtd_sessao = qtd_sessao;
			}
			public int getQdt_isenta() {
				return qdt_isenta;
			}
			public void setQdt_isenta(int qdt_isenta) {
				this.qdt_isenta = qdt_isenta;
			}
			public double getPerc_pago() {
				return perc_pago;
			}
			public void setPerc_pago(double perc_pago) {
				this.perc_pago = perc_pago;
			}
			public double getPerc_isento() {
				return perc_isento;
			}
			public void setPerc_isento(double perc_isento) {
				this.perc_isento = perc_isento;
			}
			public double getValor_media() {
				return valor_media;
			}
			public void setValor_media(double valor_media) {
				this.valor_media = valor_media;
			}
			public int getDias_trabalhado() {
				return dias_trabalhado;
			}
			public void setDias_trabalhado(int dias_trabalhado) {
				this.dias_trabalhado = dias_trabalhado;
			}
			public int getP_consulta() {
				return p_consulta;
			}
			public void setP_consulta(int p_consulta) {
				this.p_consulta = p_consulta;
			}
			public int getP_externos() {
				return p_externos;
			}
			public void setP_externos(int p_externos) {
				this.p_externos = p_externos;
			}
			public int getP_internados() {
				return p_internados;
			}
			public void setP_internados(int p_internados) {
				this.p_internados = p_internados;
			}
			public int getP_banco_ue() {
				return p_banco_ue;
			}
			public void setP_banco_ue(int p_banco_ue) {
				this.p_banco_ue = p_banco_ue;
			}
			
			
			private int id_convenio;
			private String convenio;
			
			
			public int getId_convenio() {
				return id_convenio;
			}

			public void setId_convenio(int id_convenio) {
				this.id_convenio = id_convenio;
			}

			public String getConvenio() {
				return convenio;
			}

			public void setConvenio(String convenio) {
				this.convenio = convenio;
			}

			public int getFK_consulta() {
				return FK_consulta;
			}
			public void setFK_consulta(int fK_consulta) {
				FK_consulta = fK_consulta;
			}
			public int getOpcao() {
				return opcao;
			}
			public void setOpcao(int opcao) {
				this.opcao = opcao;
			}
			public Date getP_data() {
				return p_data;
			}
			public void setP_data(Date p_data) {
				this.p_data = p_data;
			}
			public Date getS_data() {
				return s_data;
			}
			public void setS_data(Date s_data) {
				this.s_data = s_data;
			}
				/*---------------TAXA DE FOR�AS MAIORES-------------*/
				private float taxaIsencaoConsulta;
				private double precoUnitario;
				private double taxaEmkz;
				
				public float getTaxaIsencaoConsulta() {
					return taxaIsencaoConsulta;
				}
				public void setTaxaIsencaoConsulta(float taxaIsencaoConsulta) {
					this.taxaIsencaoConsulta = taxaIsencaoConsulta;
				}
				public double getPrecoUnitario() {
					return precoUnitario;
				}
				public void setPrecoUnitario(double precoUnitario) {
					this.precoUnitario = precoUnitario;
				}
				public double getTaxaEmkz() {
					return taxaEmkz;
				}
				public void setTaxaEmkz(double taxaEmkz) {
					this.taxaEmkz = taxaEmkz;
				}


				public int getFK_banco() {
					return FK_banco;
				}


				public void setFK_banco(int fK_banco) {
					FK_banco = fK_banco;
				}


				public String getNumero_conta() {
					return numero_conta;
				}


				public void setNumero_conta(String numero_conta) {
					this.numero_conta = numero_conta;
				}

				 public String getMetodo_dpagamento() {
					return metodo_dpagamento;
				}

				public void setMetodo_dpagamento(String metodo_dpagamento) {
					this.metodo_dpagamento = metodo_dpagamento;
				}

				public boolean isConfirmado() {
					return isConfirmado;
				}

				public void setConfirmado(int confirmado) {
					if(confirmado > 0)
					{
						this.isConfirmado = true;
						this.color = "#00ff00";
					}
					else
					{
						this.isConfirmado = false;	
						this.color = "#ff8040 ";
					}
				}

				public String getColor() {
					return color;
				}

				public void setColor(String color) {
					this.color = color;
				}


				public int getPac_idade() {
					return pac_idade;
				}


				public void setPac_idade(int pac_idade) {
					this.pac_idade = pac_idade;
				}


				public int getAno_nascimento() {
					return ano_nascimento;
				}


				public void setAno_nascimento(int ano_nascimento) {
					this.ano_nascimento = ano_nascimento;
				}


				public int getMes_nascimento() {
					return mes_nascimento;
				}


				public void setMes_nascimento(int mes_nascimento) {
					this.mes_nascimento = mes_nascimento;
				}


				public int getDias_nascimento() {
					return dias_nascimento;
				}


				public void setDias_nascimento(int dias_nascimento) {
					this.dias_nascimento = dias_nascimento;
				}


				public String getData_em_string() {
					return data_em_string;
				}


				public void setData_em_string(String data_em_string) {
					this.data_em_string = data_em_string;
				}


				public String getLote_vacina() {
					return lote_vacina;
				}


				public void setLote_vacina(String lote_vacina) {
					this.lote_vacina = lote_vacina;
				}


				public int getFK_requsicao() {
					return FK_requsicao;
				}


				public void setFK_requsicao(int fK_requsicao) {
					FK_requsicao = fK_requsicao;
				}


				public String getNomePlano() {
					return nomePlano;
				}


				public void setNomePlano(String nomePlano) {
					this.nomePlano = nomePlano;
				}


				public String getMorada() {
					return morada;
				}


				public void setMorada(String morada) {
					this.morada = morada;
				}


				public int getGruposg() {
					return gruposg;
				}


				public void setGruposg(int gruposg) {
					this.gruposg = gruposg;
				}


				public String getGruposgnome() {
					return gruposgnome;
				}


				public void setGruposgnome(String gruposgnome) {
					this.gruposgnome = gruposgnome;
				}


				public String getData_vacina() {
					return data_vacina;
				}


				public void setData_vacina(String data_vacina) {
					this.data_vacina = data_vacina;
				}


				public Integer getFK_alta() {
					return FK_alta;
				}


				public void setFK_alta(Integer fK_alta) {
					FK_alta = fK_alta;
				}


				public void setConfirmado(boolean isConfirmado) {
					this.isConfirmado = isConfirmado;
				}


				public void setIdade(int idade) {
					this.idade = idade;
				}


				public void setEditfone(String editfone) {
					this.editfone = editfone;
				}


				public void setEditfonep(String editfonep) {
					this.editfonep = editfonep;
				}


				public int getFK_internamento() {
					return FK_internamento;
				}


				public void setFK_internamento(int fK_internamento) {
					FK_internamento = fK_internamento;
				}


				public String getStatus() {
					return status;
				}


				public void setStatus(String status) {
					this.status = status;
				}


				public int getFK_status() {
					return FK_status;
				}


				public void setFK_status(int fK_status) {
					FK_status = fK_status;
				}
				
				
}
