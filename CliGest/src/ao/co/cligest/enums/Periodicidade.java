package ao.co.cligest.enums;

public enum Periodicidade {
	PONTUAL(1),
	PERMANENTE(2);
	
	private int valor;
	Periodicidade(int valor){
		this.valor = valor;
	}
	public int getValor()
	{
		return this.valor;
	}
}
