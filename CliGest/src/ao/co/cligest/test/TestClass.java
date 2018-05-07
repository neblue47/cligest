package ao.co.cligest.test;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Time;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Scanner;

public class TestClass {

	/**
	 * @param args
	 * @throws Exception 
	 */
	public static void main(String[] args) throws Exception {
	
		logs("Meu Text Agora");
	}
	
	public static void logs(String aux) throws IOException{
	
	 
	    FileWriter arq = new FileWriter("c:/gestlogs/tabuada.txt");
	    PrintWriter gravarArq = new PrintWriter(arq); 
	    gravarArq.printf(aux);	 
	    arq.close();
	}
	
	public static AdressCredencias getCredencia(String fileName){
		String [] linha = {};
		AdressCredencias adr = new AdressCredencias();
		try {
			
			Scanner in = new Scanner(new FileReader(fileName));
			if(in.hasNextLine()){
				String line = in.nextLine();
				linha  =  line.split("-");
				
			}
			if(linha.length > 0){
				for(int i=0; i<linha.length; i++){
					adr.setUrl(linha[0].split(":")[1]);
					adr.setBdname(linha[1].split(":")[2]);
					adr.setUser(linha[2].split(":")[2]);
					adr.setPass(linha[3].split(":")[2]);
//					System.out.println(""+linha[i].split(":")[1]);
				}
			}
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return adr;
	}
	static int horaVerificada(String aux1,String aux2) throws ParseException{
		SimpleDateFormat formatador = new SimpleDateFormat("HH:mm");
		Date data1 = formatador.parse(aux1);
		Date data2 = formatador.parse(aux2);
		
		Time time1 = new Time(data1.getTime());
		Time time2 = new Time(data2.getTime());

		int res = time1.compareTo(time2);
		
		return res;
	}
}

class AdressCredencias {
	String url;
	String user;
	String pass;
	String bdname;
	public String getUrl() {
		return url;
	}
	public void setUrl(String url) {
		this.url = url;
	}
	public String getUser() {
		return user;
	}
	public void setUser(String user) {
		this.user = user;
	}
	public String getPass() {
		return pass;
	}
	public void setPass(String pass) {
		this.pass = pass;
	}
	public String getBdname() {
		return bdname;
	}
	public void setBdname(String bdname) {
		this.bdname = bdname;
	}
	public AdressCredencias() {
		super();
		// TODO Auto-generated constructor stub
	}
	
	
}
