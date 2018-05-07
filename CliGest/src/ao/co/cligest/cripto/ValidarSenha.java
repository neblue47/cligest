package ao.co.cligest.cripto;

public class ValidarSenha {

	
	public static int validarSenha (String senha){
		String valor = senha;
		int input = -1;
	    boolean valido = false;	
	    // primeiro verificar se o tamanho é maior ou igual a 8 caracteres
	    if(validarTamanho(valor)){
	    	// verificar se a lista de caracter contem pelo menos um Caracter Numerico
	    	if(validarNumero(valor)){
	    		// verificar se a lista de caracter contem pelo menos um Caracter Maiusculo ou Minusculo
	    		if(validarMaiuscula(valor) || validarMinuscula(valor)){	
	    			input = 0;
	    		}
	    		else{
	    			input = 3;
	    	    }
	    	}
	    	else{
	    		input = 2;
		    }
	    }
	    else{
	    	input = 1;
	    }
	    return input;
	}

	/**
	 *  verificar se a lista de caracter contem pelo menos um Caracter Numerico
	 * @param texto
	 * @return boolean
	 */
	  static boolean validarNumero(String texto) {
	    String valor = texto;
	    boolean valido = false;
	
	    for (int i = 0; i < valor.length(); i++) {
	        Character caractere = valor.charAt(i);
	        if (Character.isDigit(caractere)) {
	            valido = true;
	            break;
	        }
	    }
	    
	    return valido  ;
	}
	
	  /**
	   * verificar se a lista de caracter contem pelo menos um Caracter  Minusculo
	   * @param texto
	   * @return boolean
	   */
	  static boolean validarMinuscula(String texto) {
	    String valor = texto;
	    boolean valido = false;
	
	    for (int i = 0; i < valor.length(); i++) {
	        Character caractere = valor.charAt(i);
	        if (Character.isLowerCase(caractere)) {
	            valido = true;
	            break;
	        }
	    }
	    return valido  ;
	}
	  
	  /**
	   * verificar se o tamanho é maior ou igual a 8 caracteres
	   * * @param texto
	   * @return boolean
	   */ 
	  static boolean validarTamanho(String texto) {
	    String valor = texto;
	    boolean valido = false;
	    if(valor.length() >= 8){
	    	valido = true;
	    }
	    return valido  ;
	}
	  
	  /**
	   * verificar se a lista de caracter contem pelo menos um Caracter Maiusculo 
	   * @param texto
	   * @return boolean
	   */
	  static boolean validarMaiuscula(String texto) {
	    String valor = texto;
	    boolean valido = false;
	
	    for (int i = 0; i < valor.length(); i++) {
	        Character caractere = valor.charAt(i);
	        if (Character.isUpperCase(caractere)) {
	            System.out.println(caractere);
	            valido = true;
	            break;
	        }
	    }
	    return valido  ;
	}
	  
	   
}
