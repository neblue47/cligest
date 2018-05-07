package ao.co.cligest.test;

public class Regex {
	   public  static  void  main(String[]  args)  {
	      String  texto  =  "31/11/2010";
	      if  (regerData(texto)){
	         System.out.println("valido");
	      }else
	         System.out.println("invalido");
	      }
	   
	   
	   public static boolean regerData(String data)
	   {
		   String  padrao  =  ("(0[1-9]|[12][0-9]|3[01])[-  /.](0[1-9]|[0-9]|1[012])[-  /.]((19|20)\\d\\d)");
		    boolean retorno = data.matches(padrao);		       
		   return retorno;
	   }
	}
