package ao.co.cligest.entidades;

import java.io.Serializable;

public class Beneficios implements Serializable {

	private static final long serialVersionUID = 1L;
	
	private int id_servico;
	private int FK_tipo_de_servico;
	private int FK_especialidade;
	private String servico;
	private String descricao;
	private double preco;

//	Beneficio
	private int id_beneficio;
	private int FK_servico;
	private int FK_funcionario;
	private int FK_tipo_dcobertura;
	private int FK_servico_de_exame;

// Serviço de Exames clínico
	private int id_servicodeanalise_clinica;
	private int FK_grupo_analise_clinica;
	private double preco_clinico;
	private String analise_clinica;
	private String descricao_servico_analise;
	private int codigobarra;
//	----------------------------Beneficio Consulta 
	
	private int id_beneficio_consulta;
	private int FK_beneficio;
	private int FK_beneficio_consulta;
	private int FK_fornecedor;
	private String fornecedor;
	private int FK_tipo_de_cobertura;
	private String tipo_dcobertura;
	private int FK_consulta;
//	----------------------------- Beneficio Exames
	private int id_beneficio_exame;
	private int FK_beneficio_exame;
	private String exame;
	private String data;
	private String hora;
//	------------------------------ Beneficio Farmacos
	private int FK_beneficio_geral;
	private int id_beneficio_farmaco;
	private int FK_beneficio_farmaco;
//	------------------------------- Beneficio farmacos final

public int getId_servico() {
	return id_servico;
}
public int getFK_beneficio_geral() {
	return FK_beneficio_geral;
}
public void setFK_beneficio_geral(int fK_beneficio_geral) {
	FK_beneficio_geral = fK_beneficio_geral;
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
public double getPreco() {
	return preco;
}
public void setPreco(double preco) {
	this.preco = preco;
}
public int getFK_servico() {
	return FK_servico;
}
public void setFK_servico(int fK_servico) {
	FK_servico = fK_servico;
}
public int getFK_funcionario() {
	return FK_funcionario;
}
public void setFK_funcionario(int fK_funcionario) {
	FK_funcionario = fK_funcionario;
}
public int getFK_tipo_dcobertura() {
	return FK_tipo_dcobertura;
}
public void setFK_tipo_dcobertura(int fK_tipo_dcobertura) {
	FK_tipo_dcobertura = fK_tipo_dcobertura;
}

public int getFK_servico_de_exame() {
	return FK_servico_de_exame;
}
public void setFK_servico_de_exame(int fK_servico_de_exame) {
	FK_servico_de_exame = fK_servico_de_exame;
}
//	----------------------> Serviço de analise clinico
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
public double getPreco_clinico() {
	return preco_clinico;
}
public void setPreco_clinico(double preco_clinico) {
	this.preco_clinico = preco_clinico;
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
public int getId_beneficio_consulta() {
	return id_beneficio_consulta;
}
public void setId_beneficio_consulta(int id_beneficio_consulta) {
	this.id_beneficio_consulta = id_beneficio_consulta;
}
public int getFK_beneficio() {
	return FK_beneficio;
}
public void setFK_beneficio(int fK_beneficio) {
	FK_beneficio = fK_beneficio;
}
public int getFK_beneficio_consulta() {
	return FK_beneficio_consulta;
}
public void setFK_beneficio_consulta(int fK_beneficio_consulta) {
	FK_beneficio_consulta = fK_beneficio_consulta;
}
public int getFK_fornecedor() {
	return FK_fornecedor;
}
public void setFK_fornecedor(int fK_fornecedor) {
	FK_fornecedor = fK_fornecedor;
}
public int getFK_tipo_de_cobertura() {
	return FK_tipo_de_cobertura;
}
public void setFK_tipo_de_cobertura(int fK_tipo_de_cobertura) {
	FK_tipo_de_cobertura = fK_tipo_de_cobertura;
}
public int getId_beneficio_exame() {
	return id_beneficio_exame;
}
public void setId_beneficio_exame(int id_beneficio_exame) {
	this.id_beneficio_exame = id_beneficio_exame;
}
public int getFK_beneficio_exame() {
	return FK_beneficio_exame;
}
public void setFK_beneficio_exame(int fK_beneficio_exame) {
	FK_beneficio_exame = fK_beneficio_exame;
}
public int getId_beneficio_farmaco() {
	return id_beneficio_farmaco;
}
public void setId_beneficio_farmaco(int id_beneficio_farmaco) {
	this.id_beneficio_farmaco = id_beneficio_farmaco;
}
public int getFK_beneficio_farmaco() {
	return FK_beneficio_farmaco;
}
public void setFK_beneficio_farmaco(int fK_beneficio_farmaco) {
	FK_beneficio_farmaco = fK_beneficio_farmaco;
}
public int getFK_consulta() {
	return FK_consulta;
}
public void setFK_consulta(int fK_consulta) {
	FK_consulta = fK_consulta;
}
public String getTipo_dcobertura() {
	return tipo_dcobertura;
}
public void setTipo_dcobertura(String tipo_dcobertura) {
	this.tipo_dcobertura = tipo_dcobertura;
}
public String getData() {
	return data;
}
public void setData(String data) {
	this.data = data;
}
public String getHora() {
	return hora;
}
public void setHora(String hora) {
	this.hora = hora;
}
public String getFornecedor() {
	return fornecedor;
}
public void setFornecedor(String fornecedor) {
	this.fornecedor = fornecedor;
}
public String getExame() {
	return exame;
}
public void setExame(String exame) {
	this.exame = exame;
}
public int getId_beneficio() {
	return id_beneficio;
}
public void setId_beneficio(int id_beneficio) {
	this.id_beneficio = id_beneficio;
}
}
