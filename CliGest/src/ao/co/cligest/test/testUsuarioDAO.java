package ao.co.cligest.test;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Random;

import ao.co.cligest.dao.Conexao;
import ao.co.cligest.dao.ServicoConsumivelDAO;
import ao.co.cligest.entidades.ServicoConsumivel;
import ao.co.cligest.enums.Periodicidade;
import ao.co.cligest.interfaces.IServicoConsumivel;


public class testUsuarioDAO {

	private static Connection con ;
	static IServicoConsumivel  isser = new ServicoConsumivelDAO();
	public static void main(String[] args)   {
         
		/*for (ServicoConsumivel sc : isser.gerarDividaServicoConsumivelFornecedor()) {
			System.out.println(sc.getCategoria_plan_dividas());
		}*/
		
    }
	
	public static long diffData(String data1,String data2){
		long rs = 0;
		try {
	        DateFormat df = new SimpleDateFormat ("dd/MM/yyyy");
	        df.setLenient(false);
	        Date d1 = df.parse (data1);
	        Date d2 = df.parse (data2);	        
	        long dt = (d2.getTime() - d1.getTime()) + 3600000; // 1 hora para compensar horário de verão
	        rs = dt / 86400000L;
	        System.out.println (); // passaram-se 67111 diasreturn 0;
		} catch (Exception e) {
			e.printStackTrace();
		}
		return rs;
	}
	
	 
	
	void gerarDividaServicoConsumivelFornecedor()
	{
		
	}
	static String dataToPadrao(String data)
	{
		String dia = data.substring(8);
		String mes = data.substring(5,7);
		String ano = data.substring(0,4);
		String dataSql = dia+"/"+mes+"/"+ano;
		
	return dataSql;
	}
	static String transforma(String frase){
		
		frase = frase.toLowerCase();
	    StringBuffer frase2 = new StringBuffer(frase);
	    
	    for(int i = 0; i < frase2.length(); i++){
	        Character letra = frase2.charAt(i);
	        if(i == 0){
	            letra = Character.toUpperCase(letra);
	            frase2.setCharAt(i, letra);
	        }
	        else if((i > 0) && (frase2.charAt(i - 1) == ' ')){
	            letra = Character.toUpperCase(letra);
	            frase2.setCharAt(i, letra);
	        }
	    }
	        
	    frase = frase2.toString(); 
		return frase;
	}
	static void geradorInts(){
		// inst�ncia um objeto da classe Random usando o construtor padr�o
		Random gerador = new Random(1000);
		System.out.println("Meu Valor: "+gerador.nextInt(1000)*100);
		// imprime sequ�ncia de 10 n�meros inteiros aleat�rios
		
	}
	static boolean Verificafone(String fone)
	{
		boolean retorno = true;
		System.out.println(fone);
		String sql = "select * from TBLTELEFONE where telefone = ?";
		try {
			con = Conexao.getConexao();
			PreparedStatement ent = con.prepareStatement(sql);	
			long fon = Long.parseLong(fone);
			System.out.println(fon);
			ent.setLong(1, fon);
			ResultSet rs = ent.executeQuery();
			if(rs.next()){
				retorno = false; 
			}
			ent.close();
			 
		} catch (SQLException e) {
			System.out.println(e);
		}
		catch (Exception er) {
			System.out.println(er);
			// TODO: handle exception
		}
		 
		 return retorno;
	}
	
	static String mesToString (int mes)
	{
		String mesString = "";
		switch(mes)
		{
		case 0: mesString = "JAN";
			break;
		case 1: mesString = "FEV";
			break;
		case 2: mesString = "MAR";
			break;
		case 3: mesString = "ABR";
			break;
		case 4: mesString = "MAI";
			break;
		case 5: mesString = "JUN";
			break;
		case 6: mesString = "JUL";
			break;
		case 7: mesString = "AGO";
			break;
		case 8: mesString = "SET";
			break;
		case 9: mesString = "OUT";
			break;
		case 10: mesString = "NOV";
			break;
		case 11: mesString = "DEZ";
			break;
		}
		
		return mesString;
	}
	
	static String mesToStringCompleto (int mes)
	{
		String mesString = "";
		switch(mes)
		{
		case 0: mesString = "Janeiro";
			break;
		case 1: mesString = "Fevereiro";
			break;
		case 2: mesString = "Mar�o";
			break;
		case 3: mesString = "Abril";
			break;
		case 4: mesString = "Maio";
			break;
		case 5: mesString = "Junho";
			break;
		case 6: mesString = "Julho";
			break;
		case 7: mesString = "Agosto";
			break;
		case 8: mesString = "Setembro";
			break;
		case 9: mesString = "Outubro";
			break;
		case 10: mesString = "Novembro";
			break;
		case 11: mesString = "Dezembro";
			break;
		}
		
		return mesString;
	}
	
}
