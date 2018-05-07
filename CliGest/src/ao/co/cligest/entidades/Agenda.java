package ao.co.cligest.entidades;
import java.io.Serializable;
import java.sql.*;

import javax.xml.crypto.Data;

public class Agenda implements Serializable {

	private static final long serialVersionUID = 1L;
	private int FK_entidade;
	private int id_agendatrabalho;
	private int FK_especialidade;
	private String nomeFuncionario;
	private String nomeMedico;
	private String ultimonome;
	private int dia_da_semana;
	private String nome_especialidade;
	private String nome_dia_da_semana;
	private String hora_de_inicio;
	private String hora_do_fim;
	private java.sql.Date data;
	private java.sql.Time hora;
	
	// para teste de qualidade;
	private int id_agen_test_quali;
	private int FK_funcionario;
	private int FK_serv_test_quali;
	private int FK_empresa;
	private int  FK_dia_dasemana;
	private String numero_doservico;
	private String tipo_empresa;
	private int FK_requisicao;

	//Para agenda teste de qualidade
	public String getNumero_doservico() {
		return numero_doservico;
	}

	public int getFK_requisicao() {
		return FK_requisicao;
	}

	public void setFK_requisicao(int fK_requisicao) {
		FK_requisicao = fK_requisicao;
	}

	public void setNumero_doservico(String numero_doservico) {
		this.numero_doservico = numero_doservico;
	}

	public String getTipo_empresa() {
		return tipo_empresa;
	}

	public void setTipo_empresa(String tipo_empresa) {
		this.tipo_empresa = tipo_empresa;
	}

	public int getId_agen_test_quali() {
		return id_agen_test_quali;
	}

	public void setId_agen_test_quali(int id_agen_test_quali) {
		this.id_agen_test_quali = id_agen_test_quali;
	}

	public int getFK_funcionario() {
		return FK_funcionario;
	}

	public void setFK_funcionario(int fK_funcionario) {
		FK_funcionario = fK_funcionario;
	}

	public int getFK_serv_test_quali() {
		return FK_serv_test_quali;
	}

	public void setFK_serv_test_quali(int fK_serv_test_quali) {
		FK_serv_test_quali = fK_serv_test_quali;
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

	public int getId_agendatrabalho() {
		return id_agendatrabalho;
	}
	
	public java.sql.Time getHora() {
		return hora;
	}
	public void setHora(java.sql.Time hora) {
		this.hora = hora;
	}
	public static long getSerialversionuid() {
		return serialVersionUID;
	}
	public void setId_agendatrabalho(int id_agendatrabalho) {
		this.id_agendatrabalho = id_agendatrabalho;
	}
	public String getUltimonome() {
		return ultimonome;
	}
	public void setUltimonome(String ultimonome) {
		this.ultimonome = ultimonome;
	}
	
	public java.sql.Date getData() {
		return data;
	}
	public void setData(java.sql.Date data) {
		this.data = data;
	}
	public String getNome_dia_da_semana() {
		return nome_dia_da_semana;
	}
	public void setNome_dia_da_semana(String nome_dia_da_semana) {
		this.nome_dia_da_semana = nome_dia_da_semana;
	}
	
	public int getFK_entidade() {
		return FK_entidade;
	}
	public void setFK_entidade(int fK_entidade) {
		FK_entidade = fK_entidade;
	}
	public String getNomeFuncionario() {
		return nomeFuncionario;
	}
	public void setNomeFuncionario(String nomeFuncionario) {
		this.nomeFuncionario = nomeFuncionario;
	}
	public int getDia_da_semana() {
		return dia_da_semana;
	}
	public void setDia_da_semana(int dia_da_semana) {
		this.dia_da_semana = dia_da_semana;
	}
	public String getHora_de_inicio() {
		return hora_de_inicio;
	}
	public void setHora_de_inicio(String hora_de_inicio) {
		this.hora_de_inicio = hora_de_inicio;
	}
	public String getHora_do_fim() {
		return hora_do_fim;
	}
	public void setHora_do_fim(String hora_do_fim) {
		this.hora_do_fim = hora_do_fim;
	}
	public String getNome_especialidade() {
		return nome_especialidade;
	}
	public void setNome_especialidade(String nome_especialidade) {
		this.nome_especialidade = nome_especialidade;
	}
	public int getFK_especialidade() {
		return FK_especialidade;
	}
	public void setFK_especialidade(int fK_especialidade) {
		FK_especialidade = fK_especialidade;
	}
	public String getNomeMedico() {
		return nomeMedico;
	}
	public void setNomeMedico(String nomeMedico) {
		this.nomeMedico = nomeMedico;
	}
	
	
		
}
