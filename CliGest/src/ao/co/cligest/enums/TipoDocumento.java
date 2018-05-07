package ao.co.cligest.enums;

public enum TipoDocumento {

BI(1,"Bilhete"),  CD(2,"Cedula"), PP(3,"Passaporte"),NA(0,"Não Aplicavel");
	
	public int pos;
	public String titulo;
	TipoDocumento(int pos,String titulo){
		this.pos = pos;
		this.titulo = titulo;
	}
	public int getPos() {
		return pos;
	}
	public void setPos(int pos) {
		this.pos = pos;
	}
	public String getTitulo() {
		return titulo;
	}
	public void setTitulo(String titulo) {
		this.titulo = titulo;
	}
	
	
}
