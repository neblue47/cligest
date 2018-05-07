package ao.co.cligest.entidades;

import java.io.Serializable;

public class Cid implements Serializable{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	//ids equivalentes no banco
	Integer idCid;
	Integer fkcapitulo;
	Integer fkgrupodcapitulo;
	Integer fkcategoriadgrupo;
	//--
	Integer fkcausaobito;
	Integer fkrestricaodsexo;
	Integer fkclassificacao;
	private int FK_paciente;
	//--
	String codigocid;
	String descricao;
	String nota;
	//--
	String listarcapitulo;
	String listarDescricaoCapitulo;
	String listargrupo;
	String listarDescricaoGrupo;
	String listarcategoria; 
	String listarDescricaoCategoria;
	String listarclassificacao;
	String listarrestricaodsexo;
	String listarcausaobito;
	
	public Integer getIdCid() {
		return idCid;
	}
	public void setIdCid(Integer idCid) {
		this.idCid = idCid;
	}
	public Integer getFkcapitulo() {
		return fkcapitulo;
	}
	public void setFkcapitulo(Integer fkcapitulo) {
		this.fkcapitulo = fkcapitulo;
	}
	public Integer getFkgrupodcapitulo() {
		return fkgrupodcapitulo;
	}
	public void setFkgrupodcapitulo(Integer fkgrupodcapitulo) {
		this.fkgrupodcapitulo = fkgrupodcapitulo;
	}
	public Integer getFkcategoriadgrupo() {
		return fkcategoriadgrupo;
	}
	public void setFkcategoriadgrupo(Integer fkcategoriadgrupo) {
		this.fkcategoriadgrupo = fkcategoriadgrupo;
	}
	public Integer getFkcausaobito() {
		return fkcausaobito;
	}
	public void setFkcausaobito(Integer fkcausaobito) {
		this.fkcausaobito = fkcausaobito;
	}
	public Integer getFkrestricaodsexo() {
		return fkrestricaodsexo;
	}
	public void setFkrestricaodsexo(Integer fkrestricaodsexo) {
		this.fkrestricaodsexo = fkrestricaodsexo;
	}
	public Integer getFkclassificacao() {
		return fkclassificacao;
	}
	public void setFkclassificacao(Integer fkclassificacao) {
		this.fkclassificacao = fkclassificacao;
	}
	public String getCodigocid() {
		return codigocid;
	}
	public void setCodigocid(String codigocid) {
		this.codigocid = codigocid;
	}
	public String getDescricao() {
		return descricao;
	}
	public void setDescricao(String descricao) {
		this.descricao = descricao;
	}
	public String getNota() {
		return nota;
	}
	public void setNota(String nota) {
		this.nota = nota;
	}
	public String getListarcapitulo() {
		return listarcapitulo;
	}
	public void setListarcapitulo(String listarcapitulo) {
		this.listarcapitulo = listarcapitulo;
	}
	public String getListargrupo() {
		return listargrupo;
	}
	public void setListargrupo(String listargrupo) {
		this.listargrupo = listargrupo;
	}
	public String getListarcategoria() {
		return listarcategoria;
	}
	public void setListarcategoria(String listarcategoria) {
		this.listarcategoria = listarcategoria;
	}
	public String getListarclassificacao() {
		return listarclassificacao;
	}
	public void setListarclassificacao(String listarclassificacao) {
		this.listarclassificacao = listarclassificacao;
	}
	public String getListarrestricaodsexo() {
		return listarrestricaodsexo;
	}
	public void setListarrestricaodsexo(String listarrestricaodsexo) {
		this.listarrestricaodsexo = listarrestricaodsexo;
	}
	public String getListarcausaobito() {
		return listarcausaobito;
	}
	public void setListarcausaobito(String listarcausaobito) {
		this.listarcausaobito = listarcausaobito;
	}
	public String getListarDescricaoCapitulo() {
		return listarDescricaoCapitulo;
	}
	public void setListarDescricaoCapitulo(String listarDescricaoCapitulo) {
		this.listarDescricaoCapitulo = listarDescricaoCapitulo;
	}
	public String getListarDescricaoGrupo() {
		return listarDescricaoGrupo;
	}
	public void setListarDescricaoGrupo(String listarDescricaoGrupo) {
		this.listarDescricaoGrupo = listarDescricaoGrupo;
	}
	public String getListarDescricaoCategoria() {
		return listarDescricaoCategoria;
	}
	public void setListarDescricaoCategoria(String listarDescricaoCategoria) {
		this.listarDescricaoCategoria = listarDescricaoCategoria;
	}
	public int getFK_paciente() {
		return FK_paciente;
	}
	public void setFK_paciente(int fK_paciente) {
		FK_paciente = fK_paciente;
	}
	
}
