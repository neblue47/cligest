package ao.co.cligest.entidades;

public class PacienteRelatorio {

	private String numero;
	private String nome;
	private String nomegenero;
	private String nomeEC; 
	private String telefone;
	private String endereco;
	private String idade;
	
	
	
	 
	public String getNumero() {
		return numero;
	}
	public void setNumero(String numero) {
		this.numero = numero;
	}
	public String getNome() {
		return nome;
	}
	public void setNome(String nome) {
		this.nome = nome;
	}
	public String getIdade() {
		return idade;
	}
	public void setIdade(String idade) {
		this.idade = idade;
	}
	public String getNomegenero() {
		return nomegenero;
	}
	public void setNomegenero(String nomegenero) {
		this.nomegenero = nomegenero;
	}
	public String getNomeEC() {
		return nomeEC;
	}
	public void setNomeEC(String nomeEC) {
		this.nomeEC = nomeEC;
	}
	public String getTelefone() {
		return telefone;
	}
	public void setTelefone(String telefone) {
		this.telefone = telefone;
	}
	public String getEndereco() {
		return endereco;
	}
	public void setEndereco(String endereco) {
		this.endereco = endereco;
	}
	
	public PacienteRelatorio() {
		super();
	}
	
	
	
	
}
