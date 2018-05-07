package ao.co.cligest.entidades;

import java.io.Serializable;
import java.sql.Date;

public class Grupos implements Serializable
{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	//tblgrupo
	 private Integer id_grupo;
	 private String nomgrupo;
	 
	 //tblmodulo
	 private Integer id_modulo;
	 private String modulo;
	 
	//tbltela 
	 private Integer id_tela;
	 private Integer fk_modulo;
	 private String tela;
	 
	 //tbllistarfuncionalidade
	 private Integer id_funcionalidade;
	 private Integer fk_tela;
	 private String funcionalidade;
	 private String codigo_ext;
	 private String descricao;
	 
	 // tblprevilegioatribuido
	 private Integer fk_funcionalidades;
	 private Integer fk_grupo;
	 private Integer fk_funcionario;
	 private Date dt_registo;
	 private Integer fk_telas;
	 private Integer fk_modulos;
	public Integer getId_grupo() {
		return id_grupo;
	}
	public void setId_grupo(Integer id_grupo) {
		this.id_grupo = id_grupo;
	}
	public String getNomgrupo() {
		return nomgrupo;
	}
	public void setNomgrupo(String nomgrupo) {
		this.nomgrupo = nomgrupo;
	}
	public Integer getId_modulo() {
		return id_modulo;
	}
	public void setId_modulo(Integer id_modulo) {
		this.id_modulo = id_modulo;
	}
	public String getModulo() {
		return modulo;
	}
	public void setModulo(String modulo) {
		this.modulo = modulo;
	}
	public Integer getId_tela() {
		return id_tela;
	}
	public void setId_tela(Integer id_tela) {
		this.id_tela = id_tela;
	}
	public Integer getFk_modulo() {
		return fk_modulo;
	}
	public void setFk_modulo(Integer fk_modulo) {
		this.fk_modulo = fk_modulo;
	}
	public String getTela() {
		return tela;
	}
	public void setTela(String tela) {
		this.tela = tela;
	}
	public Integer getId_funcionalidade() {
		return id_funcionalidade;
	}
	public void setId_funcionalidade(Integer id_funcionalidade) {
		this.id_funcionalidade = id_funcionalidade;
	}
	public Integer getFk_tela() {
		return fk_tela;
	}
	public void setFk_tela(Integer fk_tela) {
		this.fk_tela = fk_tela;
	}
	public String getFuncionalidade() {
		return funcionalidade;
	}
	public void setFuncionalidade(String funcionalidade) {
		this.funcionalidade = funcionalidade;
	}
	public String getCodigo_ext() {
		return codigo_ext;
	}
	public void setCodigo_ext(String codigo_ext) {
		this.codigo_ext = codigo_ext;
	}
	public String getDescricao() {
		return descricao;
	}
	public void setDescricao(String descricao) {
		this.descricao = descricao;
	}
	public Integer getFk_funcionalidades() {
		return fk_funcionalidades;
	}
	public void setFk_funcionalidades(Integer fk_funcionalidades) {
		this.fk_funcionalidades = fk_funcionalidades;
	}
	public Integer getFk_grupo() {
		return fk_grupo;
	}
	public void setFk_grupo(Integer fk_grupo) {
		this.fk_grupo = fk_grupo;
	}
	public Integer getFk_funcionario() {
		return fk_funcionario;
	}
	public void setFk_funcionario(Integer fk_funcionario) {
		this.fk_funcionario = fk_funcionario;
	}
	public Date getDt_registo() {
		return dt_registo;
	}
	public void setDt_registo(Date dt_registo) {
		this.dt_registo = dt_registo;
	}
	public Integer getFk_telas() {
		return fk_telas;
	}
	public void setFk_telas(Integer fk_telas) {
		this.fk_telas = fk_telas;
	}
	public Integer getFk_modulos() {
		return fk_modulos;
	}
	public void setFk_modulos(Integer fk_modulos) {
		this.fk_modulos = fk_modulos;
	}
	 
	 
	
}
