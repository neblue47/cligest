package ao.co.cligest.util;

public class Percentagem {
	
	
	  public double Valorpagar (double valor , double desconto)
	{

		   double valorapagar;   
	       valorapagar= valor-desconto; 
		   return valorapagar;   		   
    }
			
	public double calpercetagem(double valor ,int percentagem) 
	{ 
	  return  (valor*percentagem)/100; 
	}
		
}


