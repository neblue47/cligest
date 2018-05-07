package ao.co.cligest.entidades;

import java.io.Serializable;
import java.util.Calendar;

@SuppressWarnings("serial")
public class Diverso implements Serializable{

 
	private Integer est_civil;
	 private Integer genero;
	 private Integer tipo_doc;
	 private Integer pais;
	 private Integer provincia;
	 private Integer profissao;
	 private Integer especialidade;
	 private Integer funcao; 
	 private String nomeInternamento;
	
	 private String nomeEsp;
	 private String nomeFun;
	 private String nomeProv;
	 private String nomePais;
	 private String nomeProf;
	 private String nomeAfl;
	 private String nomeDoc;
	 private String nomeEC;
	 //Modulos - Menu
	 private Integer id_mod;
	 private String mod_nome;
	 private String mdlink;
	 private String codCl;
	 private String imagem;
	 private String imagem_md;
	 private String modulo;
	 // Telas  - SubMenu
	 private Integer id_tela;
	 private String tela;
	 private String linktela;
	 private String codTela;
	 private String imagens_tela;
	 
	 private Integer id_mes;
	 private String mes;
	 
	 private Integer id_semana;
	 private String semana;
	 private int fk_perfil;
	 private String perfil;
	 private int qtd_telas;
	 
	 
 
	 private String descricao_tx;
	 
	 // tblviasadministracao
	 private Integer id_via; 
	 private String via;
	 
	 // tbldosagem;
	 private Integer id_dosagem;
	 private String dosagem;
	 
	 // tblandarbloco
	 private Integer id_andar_bloco;
	 private String andar_bloco ;
	 
	 // tblsector 
	 private Integer id_sector;
	 private String sector;
	 private Integer extensao;
	 
	 // tblsala
	 private Integer id_sala;
	 private Integer num_sala;
	 private String desc_sala;
	 
	 // tblleito
	 private Integer id_leito;
	 private String leito;
	 private Integer status;
	 private String statuss;
	 
	 // tblTemperatura
	 private Integer id_temperatura;
	 private String valor_tmp;
	 
	// tblTensao 
		 private Integer id_tensao_arterial ;
		 private String valor_tns;
		 
	// tblPulso 
		 private Integer id_pulso ;
		 private String valor_pulso;
		 
		// tblRespiracao 
		 private Integer id_respiracao ;
		 private String valor_resp;
		 
		 private int id_lab;
		 private String lab;
	 
	 // tblMunicipio
		 private Integer municipio;
		 private String nomeMunicipio;
	//tblDistrito
		 private Integer distrito;
		 private String nomeDistrito;
		 
		 // Estado do Paciente  - Triagem
		 private int id_estadoPaciente;
		 private String estadoPaciente;
		 
		 // Generos
		 private String Nomgenero;
		 
		 // Emergencia Como Chegou
		 private int id_como_chegou;
		 private String como_chegou;
		 
		 // Exames 
		 private int id_servicodeanalise_clinica;
		 private String analise_clinica;
		 private double preco;
		 
		 // Hospital
		 private int id_hospital;
		 private String nomeHospital;
		 
		// tbltaxa
					private Integer id_taxa;
					private String tipo_de_taxa;
					private double taxa;
					
		// Tabela Prodecimentos no Internamento e BUE
			private int FK_funcionario;
			private int id_intern_proced;
			private int id_procedimento;
			private String procedimento;
			private int id_material;
			private String material;
			private int id_medicamento;
			private String medicamento;
			private int qtd_procedimento;
			private int qtd_material;
			private int qtd_medicamento;
			private int id_intern_mat_usado;
			private int qtd_funcionarios;
			private int quantidade;
			private Calendar data_registo;
			private String hora_registo;
			private double preco_venda;
			private int FK_apresentacao;
			private int id_servico;
			private String servico;
			private int qtd_exame;
			private String exame;
			private int FK_exame;
			 
			
			
					
		// campo q representa ano na tela
					private Integer ano;
					
		private String cod_cid;
		private String descricao;
		private int FK_cid;
		private int FK_internamento;
		 
	 public int getId_hospital() {
			return id_hospital;
		}
		public void setId_hospital(int id_hospital) {
			this.id_hospital = id_hospital;
		}
		public String getNomeHospital() {
			return nomeHospital;
		}
		public void setNomeHospital(String nomeHospital) {
			this.nomeHospital = nomeHospital;
		}
	public Integer getMunicipio() {
			return municipio;
		}
		public void setMunicipio(Integer municipio) {
			this.municipio = municipio;
		}
		public String getNomeMunicipio() {
			return nomeMunicipio;
		}
		public void setNomeMunicipio(String nomeMunicipio) {
			this.nomeMunicipio = nomeMunicipio;
		}
		public Integer getDistrito() {
			return distrito;
		}
		public void setDistrito(Integer distrito) {
			this.distrito = distrito;
		}
		public String getNomeDistrito() {
			return nomeDistrito;
		}
		public void setNomeDistrito(String nomeDistrito) {
			this.nomeDistrito = nomeDistrito;
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
	public Integer getProfissao() {
		return profissao;
	}
	public void setProfissao(Integer profissao) {
		this.profissao = profissao;
	}
	public Integer getEspecialidade() {
		return especialidade;
	}
	public void setEspecialidade(Integer especialidade) {
		this.especialidade = especialidade;
	}
	public Integer getFuncao() {
		return funcao;
	}
	public void setFuncao(Integer funcao) {
		this.funcao = funcao;
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
	public Integer getId_mes() {
		return id_mes;
	}
	public void setId_mes(Integer id_mes) {
		this.id_mes = id_mes;
	}
	public String getMes() {
		return mes;
	}
	public void setMes(String mes) {
		this.mes = mes;
	}
	public Integer getId_semana() {
		return id_semana;
	}
	public void setId_semana(Integer id_semana) {
		this.id_semana = id_semana;
	}
	public String getSemana() {
		return semana;
	}
	public void setSemana(String semana) {
		this.semana = semana;
	}
	public Integer getId_mod() {
		return id_mod;
	}
	public void setId_mod(Integer id_mod) {
		this.id_mod = id_mod;
	}
	
	public String getMod_nome() {
		return mod_nome;
	}
	public void setMod_nome(String mod_nome) {
		this.mod_nome = mod_nome;
	}
	public String getMdlink() {
		return mdlink;
	}
	public void setMdlink(String mdlink) {
		this.mdlink = mdlink;
	}
	public String getCodCl() {
		return codCl;
	}
	public void setCodCl(String codCl) {
		this.codCl = codCl;
	}
	
	public String getImagem_md() {
		return imagem_md;
	}
	public void setImagem_md(String imagem_md) {
		this.imagem_md = imagem_md;
	}
	public Integer getId_tela() {
		return id_tela;
	}
	public void setId_tela(Integer id_tela) {
		this.id_tela = id_tela;
	}
	public String getTela() {
		return tela;
	}
	public void setTela(String tela) {
		this.tela = tela;
	}
	public String getLinktela() {
		return linktela;
	}
	public void setLinktela(String linktela) {
		this.linktela = linktela;
	}
	public String getCodTela() {
		return codTela;
	}
	public void setCodTela(String codTela) {
		this.codTela = codTela;
	}
	
	public String getImagens_tela() {
		return imagens_tela;
	}
	public void setImagens_tela(String imagens_tela) {
		this.imagens_tela = imagens_tela;
	}
	public Integer getId_via() {
		return id_via;
	}
	public void setId_via(Integer id_via) {
		this.id_via = id_via;
	}
	public String getVia() {
		return via;
	}
	public void setVia(String via) {
		this.via = via;
	}
	public Integer getId_dosagem() {
		return id_dosagem;
	}
	public void setId_dosagem(Integer id_dosagem) {
		this.id_dosagem = id_dosagem;
	}
	public String getDosagem() {
		return dosagem;
	}
	public void setDosagem(String dosagem) {
		this.dosagem = dosagem;
	}
	public Integer getId_andar_bloco() {
		return id_andar_bloco;
	}
	public void setId_andar_bloco(Integer id_andar_bloco) {
		this.id_andar_bloco = id_andar_bloco;
	}
	public String getAndar_bloco() {
		return andar_bloco;
	}
	public void setAndar_bloco(String andar_bloco) {
		this.andar_bloco = andar_bloco;
	}
	public Integer getId_sector() {
		return id_sector;
	}
	public void setId_sector(Integer id_sector) {
		this.id_sector = id_sector;
	}
	public String getSector() {
		return sector;
	}
	public void setSector(String sector) {
		this.sector = sector;
	}
	public Integer getExtensao() {
		return extensao;
	}
	public void setExtensao(Integer extensao) {
		this.extensao = extensao;
	}
	public Integer getId_sala() {
		return id_sala;
	}
	public void setId_sala(Integer id_sala) {
		this.id_sala = id_sala;
	}
	public Integer getNum_sala() {
		return num_sala;
	}
	public void setNum_sala(Integer num_sala) {
		this.num_sala = num_sala;
	}
	public String getDesc_sala() {
		return desc_sala;
	}
	public void setDesc_sala(String desc_sala) {
		this.desc_sala = desc_sala;
	}
	public Integer getId_leito() {
		return id_leito;
	}
	public void setId_leito(Integer id_leito) {
		this.id_leito = id_leito;
	}
	public String getLeito() {
		return leito;
	}
	public void setLeito(String leito) {
		this.leito = leito;
	}
	public Integer getStatus() {
		return status;
	}
	public void setStatus(Integer status) {
		this.status = status;
	}
	public String getImagem() {
		return imagem;
	}
	public void setImagem(String imagem) {
		this.imagem = imagem;
	}
	public Integer getId_temperatura() {
		return id_temperatura;
	}
	public void setId_temperatura(Integer id_temperatura) {
		this.id_temperatura = id_temperatura;
	}
	public String getValor_tmp() {
		return valor_tmp;
	}
	public void setValor_tmp(String valor_tmp) {
		this.valor_tmp = valor_tmp;
	}
	public Integer getId_tensao_arterial() {
		return id_tensao_arterial;
	}
	public void setId_tensao_arterial(Integer id_tensao_arterial) {
		this.id_tensao_arterial = id_tensao_arterial;
	}
	public String getValor_tns() {
		return valor_tns;
	}
	public void setValor_tns(String valor_tns) {
		this.valor_tns = valor_tns;
	}
	public Integer getId_pulso() {
		return id_pulso;
	}
	public void setId_pulso(Integer id_pulso) {
		this.id_pulso = id_pulso;
	}
	public String getValor_pulso() {
		return valor_pulso;
	}
	public void setValor_pulso(String valor_pulso) {
		this.valor_pulso = valor_pulso;
	}
	public Integer getId_respiracao() {
		return id_respiracao;
	}
	public void setId_respiracao(Integer id_respiracao) {
		this.id_respiracao = id_respiracao;
	}
	public String getValor_resp() {
		return valor_resp;
	}
	public void setValor_resp(String valor_resp) {
		this.valor_resp = valor_resp;
	}
	public int getId_lab() {
		return id_lab;
	}
	public void setId_lab(int id_lab) {
		this.id_lab = id_lab;
	}
	public String getLab() {
		return lab;
	}
	public void setLab(String lab) {
		this.lab = lab;
	}
	public int getId_estadoPaciente() {
		return id_estadoPaciente;
	}
	public void setId_estadoPaciente(int id_estadoPaciente) {
		this.id_estadoPaciente = id_estadoPaciente;
	}
	public String getEstadoPaciente() {
		return estadoPaciente;
	}
	public void setEstadoPaciente(String estadoPaciente) {
		this.estadoPaciente = estadoPaciente;
	}
	public String getNomgenero() {
		return Nomgenero;
	}
	public void setNomgenero(String nomgenero) {
		Nomgenero = nomgenero;
	}
	public int getId_como_chegou() {
		return id_como_chegou;
	}
	public void setId_como_chegou(int id_como_chegou) {
		this.id_como_chegou = id_como_chegou;
	}
	public String getComo_chegou() {
		return como_chegou;
	}
	public void setComo_chegou(String como_chegou) {
		this.como_chegou = como_chegou;
	}
	public int getId_servicodeanalise_clinica() {
		return id_servicodeanalise_clinica;
	}
	public void setId_servicodeanalise_clinica(int id_servicodeanalise_clinica) {
		this.id_servicodeanalise_clinica = id_servicodeanalise_clinica;
	}
	public String getAnalise_clinica() {
		return analise_clinica;
	}
	public void setAnalise_clinica(String analise_clinica) {
		this.analise_clinica = analise_clinica;
	}
	public double getPreco() {
		return preco;
	}
	public void setPreco(double preco) {
		this.preco = preco;
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
	public double getTaxa() {
		return taxa;
	}
	public void setTaxa(double taxa) {
		this.taxa = taxa;
	}
	public Integer getAno() {
		return ano;
	}
	public void setAno(Integer ano) {
		this.ano = ano;
	}
	public String getCod_cid() {
		return cod_cid;
	}
	public void setCod_cid(String cod_cid) {
		this.cod_cid = cod_cid;
	}
	public String getDescricao() {
		return descricao;
	}
	public void setDescricao(String descricao) {
		this.descricao = descricao;
	}
	public int getFK_cid() {
		return FK_cid;
	}
	public void setFK_cid(int fK_cid) {
		FK_cid = fK_cid;
	}
	public String getDescricao_tx() {
		return descricao_tx;
	}
	public void setDescricao_tx(String descricao_tx) {
		this.descricao_tx = descricao_tx;
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
	public int getFK_internamento() {
		return FK_internamento;
	}
	public void setFK_internamento(int fK_internamento) {
		FK_internamento = fK_internamento;
	}
	public int getQtd_procedimento() {
		return qtd_procedimento;
	}
	public void setQtd_procedimento(int qtd_procedimento) {
		this.qtd_procedimento = qtd_procedimento;
	}
	public int getId_intern_proced() {
		return id_intern_proced;
	}
	public void setId_intern_proced(int id_intern_proced) {
		this.id_intern_proced = id_intern_proced;
	}
	public int getFK_funcionario() {
		return FK_funcionario;
	}
	public void setFK_funcionario(int fK_funcionario) {
		FK_funcionario = fK_funcionario;
	}
	public String getStatuss() {
		return statuss;
	}
	public void setStatuss(String statuss) {
		this.statuss = statuss;
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
	public int getQtd_material() {
		return qtd_material;
	}
	public void setQtd_material(int qtd_material) {
		this.qtd_material = qtd_material;
	}
	public int getQtd_medicamento() {
		return qtd_medicamento;
	}
	public void setQtd_medicamento(int qtd_medicamento) {
		this.qtd_medicamento = qtd_medicamento;
	}
	public int getId_intern_mat_usado() {
		return id_intern_mat_usado;
	}
	public void setId_intern_mat_usado(int id_intern_mat_usado) {
		this.id_intern_mat_usado = id_intern_mat_usado;
	}
	public int getQtd_funcionarios() {
		return qtd_funcionarios;
	}
	public void setQtd_funcionarios(int qtd_funcionarios) {
		this.qtd_funcionarios = qtd_funcionarios;
	}
	public int getQuantidade() {
		return quantidade;
	}
	public void setQuantidade(int quantidade) {
		this.quantidade = quantidade;
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
	public double getPreco_venda() {
		return preco_venda;
	}
	public void setPreco_venda(double preco_venda) {
		this.preco_venda = preco_venda;
	}
	public int getFK_apresentacao() {
		return FK_apresentacao;
	}
	public void setFK_apresentacao(int fK_apresentacao) {
		FK_apresentacao = fK_apresentacao;
	}
	public int getId_servico() {
		return id_servico;
	}
	public void setId_servico(int id_servico) {
		this.id_servico = id_servico;
	}
	public String getServico() {
		return servico;
	}
	public void setServico(String servico) {
		this.servico = servico;
	}
	public int getQtd_exame() {
		return qtd_exame;
	}
	public void setQtd_exame(int qtd_exame) {
		this.qtd_exame = qtd_exame;
	}
	public String getExame() {
		return exame;
	}
	public void setExame(String exame) {
		this.exame = exame;
	}
	public int getFK_exame() {
		return FK_exame;
	}
	public void setFK_exame(int fK_exame) {
		FK_exame = fK_exame;
	}
	public String getNomeInternamento() {
		return nomeInternamento;
	}
	public void setNomeInternamento(String nomeInternamento) {
		this.nomeInternamento = nomeInternamento;
	}
	public String getModulo() {
		return modulo;
	}
	public void setModulo(String modulo) {
		this.modulo = modulo.toUpperCase();
	}
	public int getFk_perfil() {
		return fk_perfil;
	}
	public void setFk_perfil(int fk_perfil) {
		this.fk_perfil = fk_perfil;
	}
	public String getPerfil() {
		return perfil;
	}
	public void setPerfil(String perfil) {
		this.perfil = perfil;
	}
	public int getQtd_telas() {
		return qtd_telas;
	}
	public void setQtd_telas(int qtd_telas) {
		this.qtd_telas = qtd_telas;
	}
	public String toUpper()
	{
		return String.valueOf(modulo).toUpperCase();
	}
}
