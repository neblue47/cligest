package ao.co.cligest.entidades;

import java.io.Serializable;

public class Usuario  implements Serializable
{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private int id;
	private String nome;
	private String senha;
	private String login;
	private int fk_grupo;
	private String nomgrupo;
	private String nomComp;
	private int fk_perfil;
	
	public int getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id; 
	}
	public String getNome() {
		return nome;
	}
	public void setNome(String nome) {
		this.nome = nome;
	}
	public String getSenha() {
		return senha;
	}
	public void setSenha(String senha) {
		this.senha = senha;
	}
	
		
	public String getLogin() {
		return login;
	}
	public void setLogin(String login) {
		this.login = login;
	}
	public int getFk_grupo() {
		return fk_grupo;
	}
	public void setFk_grupo(int fk_grupo) {
		this.fk_grupo = fk_grupo;
	}
	public String getNomgrupo() {
		return nomgrupo;
	}
	public void setNomgrupo(String nomgrupo) {
		this.nomgrupo = nomgrupo;
	}
	public String getNomComp() {
		return nomComp;
	}
	public void setNomComp(String nomComp) {
		this.nomComp = nomComp;
	}
	public int getFk_perfil() {
		return fk_perfil;
	}
	public void setFk_perfil(int fk_perfil) {
		this.fk_perfil = fk_perfil;
	}
	
	
}
