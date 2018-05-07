package ao.co.cligest.entidades;

import java.io.Serializable;
import java.util.Calendar;

public class Funcionario implements Serializable
{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	//tblEntidade
	 private Integer id;
	 private Integer FK_entidade;
	 
	 private Integer estado;
	//tblCidadao 
	 private String nome;
	 private String nomem;
	 private String apelido;
	 private Calendar dataNasc;
	 private Integer est_civil;
	 private Integer genero;
	 private Integer tipo_doc;
	 private Integer pais;
	 private Integer provincia;
	 private Integer profissao;
	 private Integer especialidade;
	 private Integer funcao;
	 //tblTelefone
	 private Long telefone; 
	 private String editfone;
	 //tblEmail
	 private String email;
	 //tblMorada
	 private String endereco;
	
	 //tblFuncionario
	 private String num_fun;
	 private String num_ss;
	 private String num_doc;
	 private String nif;
	 private double salario;
	 private String numOrdem;
	 //tblParente
	 private String nomep;
	 private Long telefonep;
	 private Integer afiliacao;
	 
	 //tblusuario
	 private String nome_us;
	 private int fk_grupo;
	 private String nomgrupo;
	 private String senha;
	 private byte cifras [];
	 
	 
	 //tblArquivos
	 private Integer arquivo;
	 private String localArq;
	 private String nomeArq;
	 
	 // extras 
	 private String nomeEsp;
	 private String nomeFun;
	 private String nomeProv;
	 private String nomePais;
	 private String nomeProf;
	 private String nomeAfl;
	 private String nomeDoc;
	 private String nomeEC;
	 private String generot;
	private String editfonep;
	
	private int funPermitido_id;
	private int funResponsavel_id;
	
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	
	
	public Integer getFK_entidade() {
		return FK_entidade;
	}
	public void setFK_entidade(Integer fK_entidade) {
		FK_entidade = fK_entidade;
	}
	public Integer getEstado() {
		return estado;
	}
	public void setEstado(Integer estado) {
		this.estado = estado;
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
	public Long getTelefone() {
		return telefone;
	}
	public void setTelefone(Long telefone) {
		this.telefone = telefone;
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
	
	public String getNum_fun() {
		return num_fun;
	}
	public void setNum_fun(String num_fun) {
		this.num_fun = num_fun;
	}
	public String getNum_ss() {
		return num_ss;
	}
	public void setNum_ss(String num_ss) {
		this.num_ss = num_ss;
	}
	public String getNum_doc() {
		return num_doc;
	}
	public void setNum_doc(String num_doc) {
		this.num_doc = num_doc;
	}
	public String getNif() {
		return nif;
	}
	public void setNif(String nif) {
		this.nif = nif;
	}
	public double getSalario() {
		return salario;
	}
	public void setSalario(double salario) {
		this.salario = salario;
	}
	public String getNumOrdem() {
		return numOrdem;
	}
	public void setNumOrdem(String numOrdem) {
		this.numOrdem = numOrdem;
	}
	public String getNomep() {
		return nomep;
	}
	public void setNomep(String nomep) {
		this.nomep = nomep;
	}
	public Long getTelefonep() {
		return telefonep;
	}
	public void setTelefonep(Long telefonep) {
		this.telefonep = telefonep;
	}
	public Integer getAfiliacao() {
		return afiliacao;
	}
	public void setAfiliacao(Integer afiliacao) {
		this.afiliacao = afiliacao;
	}
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
	public String getGenerot() {
		return generot;
	}
	public void setGenerot(String generot) {
		this.generot = generot;
	}
	public String getNome_us() {
		return nome_us;
	}
	public void setNome_us(String nome_us) {
		this.nome_us = nome_us;
	}
	public int getFk_grupo() {
		return fk_grupo;
	}
	public void setFk_grupo(int fk_grupo) {
		this.fk_grupo = fk_grupo;
	}
	public String getSenha() {
		return senha;
	}
	public void setSenha(String senha) {
		this.senha = senha;
	}
	public String getNomgrupo() {
		return nomgrupo;
	}
	public void setNomgrupo(String nomgrupo) {
		this.nomgrupo = nomgrupo;
	}
	 
	public String getEditfone() {
		return editfone;
	}
	public void setEditfone(long editfone) {
		// 244924044145
		if(editfone > 1){
		String fonetmp = "" + editfone;
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
			this.editfone = "";
	}
	
	public String getEditfonep() {
		return editfonep;
	}
	public void setEditfonep(long editfonep) {
		// 244924044145
		if(editfonep > 1){
		String fonetmp = "" + editfonep;
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
			this.editfonep = "";

	}
	public byte[] getCifras() {
		return cifras;
	}
	public void setCifras(byte[] cifras) {
		this.cifras = cifras;
	}
	
	 
	public String toString(byte[] cifras){
		return ""+cifras;
	}
	public int getFunPermitido_id() {
		return funPermitido_id;
	}
	public void setFunPermitido_id(int funPermitido_id) {
		this.funPermitido_id = funPermitido_id;
	}
	public int getFunResponsavel_id() {
		return funResponsavel_id;
	}
	public void setFunResponsavel_id(int funResponsavel_id) {
		this.funResponsavel_id = funResponsavel_id;
	}
	
	 
}
	 
	 
	 
