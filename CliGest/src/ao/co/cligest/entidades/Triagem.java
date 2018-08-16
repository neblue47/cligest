package ao.co.cligest.entidades;

import java.io.Serializable;
import java.sql.Date;
import java.sql.Time;
import java.util.Calendar;
import java.util.GregorianCalendar;

public class Triagem implements Serializable { 
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private int id_triagem;
	private String temperatura;
	private String pulso;
	private String respiracao;
	private String tensao_sistolica;
	private String tensao_diastolica;
	private String objectivo_geral;
	private String sistema_nervoso;
	private double peso;
	private String diagnostico_preliminar;
	private Calendar data_registo;
	private Calendar data_sinais;
	private Calendar data_evolucao;
	private int FK_estado_do_paciente;
	private int FK_paciente;
	private String nome_estado_do_paciente;
	private int FK_numero_requisicao;
	private Time hora_registo;
	private int disponibilidade;
	
	private String encaminhar;
	private String situacao;
	
	private int id_procedimento;
	private String procedimento;
	
	private int id_material;
	private String material;
	
	private int id_medicamento;
	private String medicamento;
	
	private String nome;
	private String nomem;
	private String apelido;
	private String numero_processo;
	private String servico;
	private int id_servico;
	private int id_cons_conf;
	private String estado;
	private double altura;
	private String altura_s;
	private double imc;
	private int usuario;
	private int fk_consulta_confirmada;
	private int FK_doutor;
	private int id_consulta;
	private String horaConsulta;
	private Calendar dataConsulta;
	private String descricao;
	private String queixa;
	private String historial;
	
	private String exa_cabeca;
	private String exa_pescoco;
	private String exa_membSup;
	private String exa_torax;
	private String exa_abdomen;
	private String exa_urinario;
	private String exa_membInf;
	
	private String tratamento;
	private String recomendacao;
	
	private String fk_cid;
	
	private Integer id_recetuario;
	private Integer fk_farmaco;
	private String farmaco;
	private Integer quantidade;
	private Integer fk_dosagem;
	private String dosagem;
	private String hora_ministrada;
	private Integer qdt_medicada;
	
	private String possologia;
	private Integer fk_vidAd;
	private String  vidAd;
	
	private int idade;
	public Paciente paciente ;
	private String nome_doutor;
	private String especialidade_doutor;
	private String foto;
	private int FK_servico;
	
	private int fk_altaMedica;
	private int fk_hospital_Clinica;
	
	
	private int FK_exame_requisitado;
	private int FK_factura;
	private int FK_numero;
	
	
	
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
	public int getFK_servico() {
		return FK_servico;
	}
	public void setFK_servico(int fK_servico) {
		FK_servico = fK_servico;
	}
	private String nomAlergia;

	public String getNome_doutor() {
		return nome_doutor;
	}
	public void setNome_doutor(String nome_doutor) {
		this.nome_doutor = nome_doutor;
	}
	
	public String getEspecialidade_doutor() {
		return especialidade_doutor;
	}
	public void setEspecialidade_doutor(String especialidade_doutor) {
		this.especialidade_doutor = especialidade_doutor;
	}
	public Paciente getPaciente() {
		return paciente;
	}
	
//	public void setPaciente(Paciente paciente) {
//		this.paciente = paciente;
//	}
	
	public Time getHora_registo() {
		return hora_registo;
	}
	public void setHora_registo(Time hora_registo) {
		this.hora_registo = hora_registo;
	}
	private Integer fk_funcionario;
	
	public int getFk_altaMedica() {
		return fk_altaMedica;
	}
	public void setFk_altaMedica(int fk_altaMedica) {
		this.fk_altaMedica = fk_altaMedica;
	}
	public int getFk_hospital_Clinica() {
		return fk_hospital_Clinica;
	}
	public void setFk_hospital_Clinica(int fk_hospital_Clinica) {
		this.fk_hospital_Clinica = fk_hospital_Clinica;
	}
	private Integer id_genero;
	private String genero;
	
	 // tblTemperatura
	 private Integer fk_temperatura;
	 private String valor_tmp;
	 
	// tblTensao 
		 private Integer FK_tensao_sistolica ;
		 private Integer FK_tensao_diastolica ;
		 private String valor_tns;
		 
	// tblPulso 
		 private Integer fk_pulso ;
		 private String valor_pulso;
		 
		// tblRespiracao 
		 private Integer fk_respiracao ;
		 private String valor_resp;
	
//	Campos relacionados a antecedentes (Doen�as)
	
	public String getVidAd() {
		return vidAd;
	}
	public void setVidAd(String vidAd) {
		this.vidAd = vidAd;
	}
	
	public String getDosagem() {
		return dosagem;
	}
	public void setDosagem(String dosagem) {
		this.dosagem = dosagem;
	}
	private int observacao, transfusao, cardiopatia, osteoporose, tuberculose,
			acidente, tabagismo, diabate, cirurgia, hta, avc, doenca_renal_cronica, 
			endocrinas_metabolica, etilismo, alergia, anemia, drogas, virose, cancro, dts;
	
	private int fk_doenca,membro_um,membro_dois,membro_tres,membro_quatro,membro_cinco;
	
	// exames clinicos 
	private int id_grupoanalise;
	private String grupoanalise;
	
	private int id_analise;
	private double preco;
	private String analise;
	
	// Atribuição de beneficios
	 private String id_servic;
	 private String nome_servico;
	 private String preco_servico;
	 
	// Internamento - Consultas
	private Integer id_internamento;
	private Integer modo_internar;
	private String local_prov;
	private Calendar data_internar;
	private String hora_internar;
	private Integer fk_sector;
	private String sector;
	private Integer fk_sala;
	private String sala;
	private Integer fk_leito;
	private String leito;
	private String andar;
	private int id_emergencia;
	
//	-------------------------- Nivel de Consciencia
	private int id_nivel_consciencia;
	private String nivel_consciencia;
	
	private int id_alergia;
	private String nome_alergia;
//	-------------------------- Urgencia Queixas Cussunga

	private int id_hipotese;
	private int id_urgencia_doenca;
	private int FK_urgencia;
	private int FK_cid;
	
public int getId_hipotese() {
		return id_hipotese;
	}
	public void setId_hipotese(int id_hipotese) {
		this.id_hipotese = id_hipotese;
	}
	public int getId_urgencia_doenca() {
		return id_urgencia_doenca;
	}
	public void setId_urgencia_doenca(int id_urgencia_doenca) {
		this.id_urgencia_doenca = id_urgencia_doenca;
	}
	public int getFK_urgencia() {
		return FK_urgencia;
	}
	public void setFK_urgencia(int fK_urgencia) {
		FK_urgencia = fK_urgencia;
	}
	public int getFK_cid() {
		return FK_cid;
	}
	public void setFK_cid(int fK_cid) {
		FK_cid = fK_cid;
	}
	//	public int getId_nivel_consciencia() {
//		return id_nivel_consciencia;
//	}
//	public void setId_nivel_consciencia(int id_nivel_consciencia) {
//		this.id_nivel_consciencia = id_nivel_consciencia;
//	}
//	public String getNivel_consciencia() {
//		return nivel_consciencia;
//	}
//	public void setNivel_consciencia(String nivel_consciencia) {
//		this.nivel_consciencia = nivel_consciencia;
//	}
	private int FK_medico;
	private int FK_alergia;
	private String status;
	private int FK_nivel_consciencia;
	
	public int getFK_medico() {
		return FK_medico;
	}
	public void setFK_medico(int fK_medico) {
		FK_medico = fK_medico;
	}
	public int getFK_alergia() {
		return FK_alergia;
	}
	public void setFK_alergia(int fK_alergia) {
		FK_alergia = fK_alergia;
	}
//	public String getMedicamento() {
//		return medicamento;
//	}
//	public void setMedicamento(String medicamento) {
//		this.medicamento = medicamento;
//	}
	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
	}
	public String getFarmaco() {
		return farmaco;
	}
	public void setFarmaco(String farmaco) {
		this.farmaco = farmaco;
	}
	public int getUsuario() {
		return usuario;
	}
	public void setUsuario(int usuario) {
		this.usuario = usuario;
	}
	public int getId_servico() {
		return id_servico;
	}
	public void setId_servico(int id_servico) {
		this.id_servico = id_servico;
	}
	public String getEstado() {
		return estado;
	}
	public void setEstado(String estado) {
		this.estado = estado;
	}
	public int getId_cons_conf() {
		return id_cons_conf;
	}
	public void setId_cons_conf(int id_cons_conf) {
		this.id_cons_conf = id_cons_conf;
	}
	public int getFK_paciente() {
		return FK_paciente;
	}
	public void setFK_paciente(int fK_paciente) {
		FK_paciente = fK_paciente;
	}
	public String getServico() {
		return servico;
	}
	public void setServico(String servico) {
		this.servico = servico;
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
	public String getNumero_processo() {
		return numero_processo;
	}
	public void setNumero_processo(String numero_processo) {
		this.numero_processo = numero_processo;
	}
	public String getNome_estado_do_paciente() {
		return nome_estado_do_paciente;
	}
	public void setNome_estado_do_paciente(String nome_estado_do_paciente) {
		this.nome_estado_do_paciente = nome_estado_do_paciente;
	}
	public int getFK_estado_do_paciente() {
		return FK_estado_do_paciente;
	}
	public void setFK_estado_do_paciente(int fK_estado_do_paciente) {
		FK_estado_do_paciente = fK_estado_do_paciente;
	}
	public int getId_triagem() {
		return id_triagem;
	}
	public void setId_triagem(int id_triagem) {
		this.id_triagem = id_triagem;
	}
//	public int getFK_temperatura() {
//		return FK_temperatura;
//	}
//	public void setFK_temperatura(int FK_temperatura) {
//		this.FK_temperatura = FK_temperatura;
//	}
//	public int getFK_pulso() {
//		return FK_pulso;
//	}
//	public void setFK_pulso(int FK_pulso) {
//		this.FK_pulso = FK_pulso;
//	}
//	public int getFK_respiracao() {
//		return FK_respiracao;
//	}
//	public void setFK_respiracao(int FK_respiracao) {
//		this.FK_respiracao = FK_respiracao;
//	}
//	public int getFK_tensao_arterial() {
//		return FK_tensao_arterial;
//	}
//	public void setFK_tensao_arterial(int FK_tensao_arterial) {
//		this.FK_tensao_arterial = FK_tensao_arterial;
//	}
	public double getPeso() {
		return peso;
	}
	public void setPeso(double peso) {
		this.peso = peso;
	}
	public String getDiagnostico_preliminar() {
		return diagnostico_preliminar;
	}
	public void setDiagnostico_preliminar(String diagnostico_preliminar) {
		this.diagnostico_preliminar = diagnostico_preliminar;
	}
	public Calendar getData() {
		return data_registo;
	}
	public void setData(Calendar data) {
		this.data_registo = data;
	}
	public double getAltura() {
		return altura;
	}
	public void setAltura(double altura) {
		this.altura = altura;
	}
	public double getImc() {
		return imc;
	}
	public void setImc(double imc) {
		this.imc = imc;
	}
	public int getFk_consulta_confirmada() {
		return fk_consulta_confirmada;
	}
	public void setFk_consulta_confirmada(int fk_consulta_confirmada) {
		this.fk_consulta_confirmada = fk_consulta_confirmada;
	}
	public int getFK_doutor() {
		return FK_doutor;
	}
	public void setFK_doutor(int fK_doutor) {
		FK_doutor = fK_doutor;
	}
	public int getId_consulta() {
		return id_consulta;
	}
	public void setId_consulta(int id_consulta) { 
		this.id_consulta = id_consulta;
	}
	public String getHoraConsulta() {
		return horaConsulta;
	}
	public void setHoraConsulta(String horaConsulta) {
		this.horaConsulta = horaConsulta;
	}
	public Calendar getDataConsulta() {
		return dataConsulta;
	}
	public void setDataConsulta(Calendar dataConsulta) {
		this.dataConsulta = dataConsulta;
	}
	public String getDescricao() {
		return descricao;
	}
	public void setDescricao(String descricao) {
		this.descricao = descricao;
	}
	public int getObservacao() {
		return observacao;
	}
	public void setObservacao(int observacao) {
		this.observacao = observacao;
	}
	public int getTransfusao() {
		return transfusao;
	}
	public void setTransfusao(int transfusao) {
		this.transfusao = transfusao;
	}
	public int getCardiopatia() {
		return cardiopatia;
	}
	public void setCardiopatia(int cardiopatia) {
		this.cardiopatia = cardiopatia;
	}
	public int getOsteoporose() {
		return osteoporose;
	}
	public void setOsteoporose(int osteoporose) {
		this.osteoporose = osteoporose;
	}
	public int getTuberculose() {
		return tuberculose;
	}
	public void setTuberculose(int tuberculose) {
		this.tuberculose = tuberculose;
	}
	public int getAcidente() {
		return acidente;
	}
	public void setAcidente(int acidente) {
		this.acidente = acidente;
	}
	public int getTabagismo() {
		return tabagismo;
	}
	public void setTabagismo(int tabagismo) {
		this.tabagismo = tabagismo;
	}
	public int getDiabate() {
		return diabate;
	}
	public void setDiabete(int diabate) {
		this.diabate = diabate;
	}
	public int getCirurgia() {
		return cirurgia;
	}
	public void setCirurgia(int cirurgia) {
		this.cirurgia = cirurgia;
	}
	public int getHta() {
		return hta;
	}
	public void setHta(int hta) {
		this.hta = hta;
	}
	public int getAvc() {
		return avc;
	}
	public void setAvc(int avc) {
		this.avc = avc;
	}
	public int getDoenca_renal_cronica() {
		return doenca_renal_cronica;
	}
	public void setDoenca_renal_cronica(int doenca_renal_cronica) {
		this.doenca_renal_cronica = doenca_renal_cronica;
	}
	public int getEndocrinas_metabolica() {
		return endocrinas_metabolica;
	}
	public void setEndocrinas_metabolica(int endocrinas_metabolica) {
		this.endocrinas_metabolica = endocrinas_metabolica;
	}
	public int getEtilismo() {
		return etilismo;
	}
	public void setEtilismo(int etilismo) {
		this.etilismo = etilismo;
	}
	public int getAlergia() {
		return alergia;
	}
	public void setAlergia(int alergia) {
		this.alergia = alergia;
	}
	public int getAnemia() {
		return anemia;
	}
	public void setAnemia(int anemia) {
		this.anemia = anemia;
	}
	public int getDrogas() {
		return drogas;
	}
	public void setDrogas(int drogas) {
		this.drogas = drogas;
	}
	public int getVirose() {
		return virose;
	}
	public void setVirose(int virose) {
		this.virose = virose;
	}
	public int getCancro() {
		return cancro;
	}
	public void setCancro(int cancro) {
		this.cancro = cancro;
	}
	public int getDts() {
		return dts;
	}
	public void setDts(int dts) {
		this.dts = dts;
	}
	public String getQueixa() {
		return queixa;
	}
	public void setQueixa(String queixa) {
		this.queixa = queixa;
	}
	public String getHistorial() {
		return historial;
	}
	public void setHistorial(String historial) {
		this.historial = historial;
	}
	public String getExa_cabeca() {
		return exa_cabeca;
	}
	public void setExa_cabeca(String exa_cabeca) {
		this.exa_cabeca = exa_cabeca;
	}
	public String getExa_pescoco() {
		return exa_pescoco;
	}
	public void setExa_pescoco(String exa_pescoco) {
		this.exa_pescoco = exa_pescoco;
	}
	public String getExa_membSup() {
		return exa_membSup;
	}
	public void setExa_membSup(String exa_membSup) {
		this.exa_membSup = exa_membSup;
	}
	public String getExa_torax() {
		return exa_torax;
	}
	public void setExa_torax(String exa_torax) {
		this.exa_torax = exa_torax;
	}
	public String getExa_abdomen() {
		return exa_abdomen;
	}
	public void setExa_abdomen(String exa_abdomen) {
		this.exa_abdomen = exa_abdomen;
	}
	public String getExa_urinario() {
		return exa_urinario;
	}
	public void setExa_urinario(String exa_urinario) {
		this.exa_urinario = exa_urinario;
	}
	public String getExa_membInf() {
		return exa_membInf;
	}
	public void setExa_membInf(String exa_membInf) {
		this.exa_membInf = exa_membInf;
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
	public int getMembro_um() {
		return membro_um;
	}
	public void setMembro_um(int membro_um) {
		this.membro_um = membro_um;
	}
	public int getMembro_dois() {
		return membro_dois;
	}
	public void setMembro_dois(int membro_dois) {
		this.membro_dois = membro_dois;
	}
	public int getMembro_tres() {
		return membro_tres;
	}
	public void setMembro_tres(int membro_tres) {
		this.membro_tres = membro_tres;
	}
	public int getMembro_quatro() {
		return membro_quatro;
	}
	public void setMembro_quatro(int membro_quatro) {
		this.membro_quatro = membro_quatro;
	}
	public int getMembro_cinco() {
		return membro_cinco;
	}
	public void setMembro_cinco(int membro_cinco) {
		this.membro_cinco = membro_cinco;
	}
	public String getFk_cid() {
		return fk_cid;
	}
	public void setFk_cid(String fk_cid) {
		this.fk_cid = fk_cid;
	}
	public Integer getId_recetuario() {
		return id_recetuario;
	}
	public void setId_recetuario(Integer id_recetuario) {
		this.id_recetuario = id_recetuario;
	}
	public Integer getFk_farmaco() {
		return fk_farmaco;
	}
	public void setFk_farmaco(Integer fk_farmaco) {
		this.fk_farmaco = fk_farmaco;
	}
	public Integer getQuantidade() {
		return quantidade;
	}
	public void setQuantidade(Integer quantidade) {
		this.quantidade = quantidade;
	}
	public Integer getFk_dosagem() {
		return fk_dosagem;
	}
	public void setFk_dosagem(Integer fk_dosagem) {
		this.fk_dosagem = fk_dosagem;
	}
	public String getPossologia() {
		return possologia;
	}
	public void setPossologia(String possologia) {
		this.possologia = possologia;
	}
	public void setDiabate(int diabate) {
		this.diabate = diabate;
	}
	public Integer getFk_vidAd() {
		return fk_vidAd;
	}
	public void setFk_vidAd(Integer fk_vidAd) {
		this.fk_vidAd = fk_vidAd;
	}
	public int getId_grupoanalise() {
		return id_grupoanalise;
	}
	public void setId_grupoanalise(int id_grupoanalise) {
		this.id_grupoanalise = id_grupoanalise;
	}
	public String getGrupoanalise() {
		return grupoanalise;
	}
	public void setGrupoanalise(String grupoanalise) {
		this.grupoanalise = grupoanalise;
	}
	public int getId_analise() {
		return id_analise;
	}
	public void setId_analise(int id_analise) {
		this.id_analise = id_analise;
	}
	public double getPreco() {
		return preco;
	}
	public void setPreco(double preco) {
		this.preco = preco;
	}
	public String getAnalise() {
		return analise;
	}
	public void setAnalise(String analise) {
		this.analise = analise;
	}
	public int getFk_doenca() {
		return fk_doenca;
	}
	public void setFk_doenca(int fk_doenca) {
		this.fk_doenca = fk_doenca;
	}
	public Integer getId_internamento() {
		return id_internamento;
	}
	public void setId_internamento(Integer id_internamento) {
		this.id_internamento = id_internamento;
	}
	public Integer getModo_internar() {
		return modo_internar;
	}
	public void setModo_internar(Integer modo_internar) {
		this.modo_internar = modo_internar;
	}
	public String getLocal_prov() {
		return local_prov;
	}
	public void setLocal_prov(String local_prov) {
		this.local_prov = local_prov;
	}
	public Calendar getData_internar() {
		return data_internar;
	}
	public void setData_internar(Calendar data_internar) {
		this.data_internar = data_internar;
	}
	public String getHora_internar() {
		return hora_internar;
	}
	public void setHora_internar(String hora_internar) {
		this.hora_internar = hora_internar;
	}
	public Integer getFk_sector() {
		return fk_sector;
	}
	public void setFk_sector(Integer fk_sector) {
		this.fk_sector = fk_sector;
	}
	public Integer getFk_sala() {
		return fk_sala;
	}
	public void setFk_sala(Integer fk_sala) {
		this.fk_sala = fk_sala;
	}
	public Integer getFk_leito() {
		return fk_leito;
	}
	public void setFk_leito(Integer fk_leito) {
		this.fk_leito = fk_leito;
	}
	
	public int getIdade() {
		return idade;
	}
	
	public void setIdade(Date data) {
		
		Calendar dateOfBirth = new GregorianCalendar();
		dateOfBirth.setTime(data);
		Calendar today = Calendar.getInstance();
		int idade = today.get(Calendar.YEAR) - dateOfBirth.get(Calendar.YEAR);
		dateOfBirth.add(Calendar.YEAR, idade);
		if (today.before(dateOfBirth)) {
			idade--;
		}
		this.idade = idade;
	}
	public Integer getId_genero() {
		return id_genero;
	}
	public void setId_genero(Integer id_genero) {
		this.id_genero = id_genero;
	}
	public String getGenero() {
		return genero;
	}
	public void setGenero(String genero) {
		this.genero = genero;
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
	public String getLeito() {
		return leito;
	}
	public void setLeito(String leito) {
		this.leito = leito;
	}
	public String getAndar() {
		return andar;
	}
	public void setAndar(String andar) {
		this.andar = andar;
	}
	public String getHora_ministrada() {
		return hora_ministrada;
	}
	public void setHora_ministrada(String hora_ministrada) {
		this.hora_ministrada = hora_ministrada;
	}
	public Integer getQdt_medicada() {
		return qdt_medicada;
	}
	public void setQdt_medicada(Integer qdt_medicada) {
		this.qdt_medicada = qdt_medicada;
	}
	public Integer getFk_funcionario() {
		return fk_funcionario;
	}
	public void setFk_funcionario(Integer fk_funcionario) {
		this.fk_funcionario = fk_funcionario;
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
	public Integer getFk_temperatura() {
		return fk_temperatura;
	}
	public void setFk_temperatura(Integer fk_temperatura) {
		this.fk_temperatura = fk_temperatura;
	}
	public String getValor_tmp() {
		return valor_tmp;
	}
	public void setValor_tmp(String valor_tmp) {
		this.valor_tmp = valor_tmp;
	}

	public String getValor_tns() {
		return valor_tns;
	}
	public void setValor_tns(String valor_tns) {
		this.valor_tns = valor_tns;
	}
	public Integer getFk_pulso() {
		return fk_pulso;
	}
	public void setFk_pulso(Integer fk_pulso) {
		this.fk_pulso = fk_pulso;
	}
	public String getValor_pulso() {
		return valor_pulso;
	}
	public void setValor_pulso(String valor_pulso) {
		this.valor_pulso = valor_pulso;
	}
	public Integer getFk_respiracao() {
		return fk_respiracao;
	}
	public void setFk_respiracao(Integer fk_respiracao) {
		this.fk_respiracao = fk_respiracao;
	}
	public String getValor_resp() {
		return valor_resp;
	}
	public void setValor_resp(String valor_resp) {
		this.valor_resp = valor_resp;
	}
	public Calendar getData_evolucao() {
		return data_evolucao;
	}
	public void setData_evolucao(Calendar data_evolucao) {
		this.data_evolucao = data_evolucao;
	}
	public Calendar getData_sinais() {
		return data_sinais;
	}
	public void setData_sinais(Calendar data_sinais) {
		this.data_sinais = data_sinais;
	}
	public String getEncaminhar() {
		return encaminhar;
	}
	public void setEncaminhar(String encaminhar) {
		this.encaminhar = encaminhar;
	}
	public String getSituacao() {
		return situacao;
	}
	public void setSituacao(String situacao) {
		this.situacao = situacao;
	}
	public int getId_alergia() {
		return id_alergia;
	}
	public void setId_alergia(int id_alergia) {
		this.id_alergia = id_alergia;
	}
	public String getNome_alergia() {
		return nome_alergia;
	}
	public void setNome_alergia(String nome_alergia) {
		this.nome_alergia = nome_alergia;
	}
	public String getNomAlergia() {
		return nomAlergia;
	}
	public void setNomAlergia(String nomAlergia) {
		this.nomAlergia = nomAlergia;
	}
	public int getId_nivel_consciencia() {
		return id_nivel_consciencia;
	}
	public void setId_nivel_consciencia(int id_nivel_consciencia) {
		this.id_nivel_consciencia = id_nivel_consciencia;
	}
	public String getNivel_consciencia() {
		return nivel_consciencia;
	}
	public void setNivel_consciencia(String nivel_consciencia) {
		this.nivel_consciencia = nivel_consciencia;
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
	public int getFK_nivel_consciencia() {
		return FK_nivel_consciencia;
	}
	public void setFK_nivel_consciencia(int fK_nivel_consciencia) {
		FK_nivel_consciencia = fK_nivel_consciencia;
	}
	public int getId_emergencia() {
		return id_emergencia;
	}
	public void setId_emergencia(int id_emergencia) {
		this.id_emergencia = id_emergencia;
	}
	
	private int id_servicodeanalise_clinica;
	private String FK_grupo_analise_clinica;
	private String analise_clinica;
	private String descricao_servico_analise;
	private int codigobarra;

	public int getId_servicodeanalise_clinica() {
		return id_servicodeanalise_clinica;
	}
	public void setId_servicodeanalise_clinica(int id_servicodeanalise_clinica) {
		this.id_servicodeanalise_clinica = id_servicodeanalise_clinica;
	}
	public String getFK_grupo_analise_clinica() {
		return FK_grupo_analise_clinica;
	}
	public void setFK_grupo_analise_clinica(String fK_grupo_analise_clinica) {
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
	private int id_receita;
	private int FK_produto;
	private int FK_viadministrar;
	private String posologia;

	public int getId_receita() {
		return id_receita;
	}
	public void setId_receita(int id_receita) {
		this.id_receita = id_receita;
	}
	public int getFK_produto() {
		return FK_produto;
	}
	public void setFK_produto(int fK_produto) {
		FK_produto = fK_produto;
	}
	public int getFK_viadministrar() {
		return FK_viadministrar;
	}
	public void setFK_viadministrar(int fK_viadministrar) {
		FK_viadministrar = fK_viadministrar;
	}
	public String getPosologia() {
		return posologia;
	}
	public void setPosologia(String posologia) {
		this.posologia = posologia;
	}
	private int id_urgencia_recomendacao;

	public int getId_urgencia_recomendacao() {
		return id_urgencia_recomendacao;
	}
	public void setId_urgencia_recomendacao(int id_urgencia_recomendacao) {
		this.id_urgencia_recomendacao = id_urgencia_recomendacao;
	}
	public int getFK_numero() {
		return FK_numero;
	}
	public void setFK_numero(int fK_numero) {
		FK_numero = fK_numero;
	}
	public int getFK_numero_requisicao() {
		return FK_numero_requisicao;
	}
	public void setFK_numero_requisicao(int fK_numero_requisicao) {
		FK_numero_requisicao = fK_numero_requisicao;
	}
	public String getFoto() {
		return foto;
	}
	public void setFoto(String foto) {
		this.foto = foto;
	}
	public Integer getFK_tensao_sistolica() {
		return FK_tensao_sistolica;
	}
	public void setFK_tensao_sistolica(Integer fK_tensao_sistolica) {
		FK_tensao_sistolica = fK_tensao_sistolica;
	}
	public Integer getFK_tensao_diastolica() {
		return FK_tensao_diastolica;
	}
	public void setFK_tensao_diastolica(Integer fK_tensao_diastolica) {
		FK_tensao_diastolica = fK_tensao_diastolica;
	}
	public String getAltura_s() {
		return altura_s;
	}
	public void setAltura_s(String altura_s) {
		this.altura_s = altura_s;
	}
	public String getObjectivo_geral() {
		return objectivo_geral;
	}
	public void setObjectivo_geral(String objectivo_geral) {
		this.objectivo_geral = objectivo_geral;
	}
	public String getSistema_nervoso() {
		return sistema_nervoso;
	}
	public void setSistema_nervoso(String sistema_nervoso) {
		this.sistema_nervoso = sistema_nervoso;
	}
	public int getDisponibilidade() {
		return disponibilidade;
	}
	public void setDisponibilidade(int disponibilidade) {
		this.disponibilidade = disponibilidade;
	}
	
	public String getTemperatura() {
		return temperatura;
	}
	public void setTemperatura(String temperatura) {
		this.temperatura = temperatura;
	}
	public String getPulso() {
		return pulso;
	}
	public void setPulso(String pulso) {
		this.pulso = pulso;
	}
	public String getRespiracao() {
		return respiracao;
	}
	public void setRespiracao(String respiracao) {
		this.respiracao = respiracao;
	}
	public String getTensao_sistolica() {
		return tensao_sistolica;
	}
	public void setTensao_sistolica(String tensao_sistolica) {
		this.tensao_sistolica = tensao_sistolica;
	}
	public String getTensao_diastolica() {
		return tensao_diastolica;
	}
	public void setTensao_diastolica(String tensao_diastolica) {
		this.tensao_diastolica = tensao_diastolica;
	}
	
}
