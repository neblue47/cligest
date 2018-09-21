package ao.co.cligest.cripto;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.security.KeyPair;
import java.security.KeyPairGenerator;
import java.security.PrivateKey;
import java.security.PublicKey;

import javax.crypto.Cipher;

import ao.co.cligest.util.MetodosBuscas;
 
 
public class EncriptaDecriptaRSA {
 
  public static final String ALGORITHM = "RSA";
 
  /**
   * Local da  no sistema de arquivos.
   */
//  String jrxml =    .getRealPath("/WEB-INF/relatorios/exemploLista.jrxml");
  
 
  /**
   * Local da chave privada e chave pública no sistema de arquivos.
   */
  
  public static final String PATH_CHAVE_PRIVADA = MetodosBuscas.getLocal() + "private.key";
  public static final String PATH_CHAVE_PUBLICA = MetodosBuscas.getLocal() + "public.key";
  public static final String PATH_CHAVE_PASTA   = MetodosBuscas.getLocal();
 
  /**
   * Gera a chave que contém um par de chave Privada e Pública usando 1025 bytes.
   * Armazena o conjunto de chaves nos arquivos private.key e public.key
   */
  public static void geraChave() {
    try {
      final KeyPairGenerator keyGen = KeyPairGenerator.getInstance(ALGORITHM);
      keyGen.initialize(1024);
      final KeyPair key = keyGen.generateKeyPair();
 
      File chavePrivadaFile = new File(PATH_CHAVE_PRIVADA);
      File chavePublicaFile = new File(PATH_CHAVE_PUBLICA);
 
      // Cria os arquivos para armazenar a chave Privada e a chave Publica
      if (chavePrivadaFile.getParentFile() != null) {
        chavePrivadaFile.getParentFile().mkdirs();
      }
      
      chavePrivadaFile.createNewFile();
 
      if (chavePublicaFile.getParentFile() != null) {
        chavePublicaFile.getParentFile().mkdirs();
      }
      
      chavePublicaFile.createNewFile();
 
      // Salva a Chave Pública no arquivo
      ObjectOutputStream chavePublicaOS = new ObjectOutputStream(new FileOutputStream(chavePublicaFile));
      chavePublicaOS.writeObject(key.getPublic());
      chavePublicaOS.close();
 
      // Salva a Chave Privada no arquivo
      ObjectOutputStream chavePrivadaOS = new ObjectOutputStream(new FileOutputStream(chavePrivadaFile));
      chavePrivadaOS.writeObject(key.getPrivate());
      chavePrivadaOS.close();
    } catch (Exception e) {
      e.printStackTrace();
    }
 
  }
 /**
  * Metodo para ocultar a pasta que vai conter os arquivos chaves para encripta e decriptar a senha do 
  * Utilizador
  * @param file
  */
  static void ocultarFileOrFolder(File file){ 

	   try{ 
	   Runtime.getRuntime().exec("attrib +a +s +h \""+file.getAbsolutePath().toString()+"\""); 
	   }catch(Exception e){} 

	   } 
  
  /**
   * Metodo para desocultar a pasta que vai conter os arquivos chaves para encripta e decriptar a senha do 
   * Utilizador
   * @param file
   */
   static void desocultarFileOrFolder(File file){ 

 	   try{ 
 	   Runtime.getRuntime().exec("attrib -a -s -h \""+file.getAbsolutePath().toString()+"\""); 
 	   }catch(Exception e){} 

 	   } 
  /**
   * Verifica se o par de chaves Pública e Privada já foram geradas.
   */
  public static boolean verificaSeExisteChavesNoSO() {
 
    File chavePrivada = new File(PATH_CHAVE_PRIVADA);
    File chavePublica = new File(PATH_CHAVE_PUBLICA);
    File chavePasta = new File(PATH_CHAVE_PASTA);
    
    if (chavePrivada.exists() && chavePublica.exists()) {
         //ocultarFileOrFolder(chavePasta);
    	return true;
    }
    else{
    	MetodosBuscas.logs("erroChave", "ficheiro de chave publica nao localizada");
    }
    return false;
  }
 
  /**
   * Criptografa o texto puro usando chave pública.
   */
  public static byte[] criptografa(String texto, PublicKey chave) {
    byte[] cipherText = null;
    
    try {
      final Cipher cipher = Cipher.getInstance(ALGORITHM);
      // Criptografa o texto puro usando a chave Púlica
      cipher.init(Cipher.ENCRYPT_MODE, chave);
      cipherText = cipher.doFinal(texto.getBytes());
    } catch (Exception e) {
      e.printStackTrace();
    }
    
    return cipherText;
  }
  
  public static String criptografa(String texto ) {
	    
	  // Verifica se já existe um par de chaves, caso contrário gera-se as chaves..
      if (!verificaSeExisteChavesNoSO()) {
       // Método responsável por gerar um par de chaves usando o algoritmo RSA e
       // armazena as chaves nos seus respectivos arquivos.
        geraChave();
      }
      
	    byte[] cipherText = null;
	    String hash = "";
	    try {
	    	
	    	  ObjectInputStream inputStream = null;
	           
	          // Lendo arquivo da  Chave Pública
	          inputStream = new ObjectInputStream(new FileInputStream(PATH_CHAVE_PUBLICA));
	          final PublicKey chavePublica = (PublicKey) inputStream.readObject();
		      final Cipher cipher = Cipher.getInstance(ALGORITHM);
		      
		      // Criptografa o texto puro usando a chave Púlica
		      cipher.init(Cipher.ENCRYPT_MODE, chavePublica);
		      cipherText = cipher.doFinal(texto.getBytes());
		      for (int i = 0; i < cipherText.length; i++) {
					hash += cipherText[i] + ":";
		        	 
				}
		      inputStream.close();
		       
	    } catch (Exception e) {
	      e.printStackTrace();
	    }
	   
	    return hash;
	  }
 
  /**
   * Decriptografa o texto puro usando chave privada.
   */
  public static String decriptografa(byte[] texto, PrivateKey chave) {
    byte[] dectyptedText = null;
    
    try {
      final Cipher cipher = Cipher.getInstance(ALGORITHM);
      // Decriptografa o texto puro usando a chave Privada
      cipher.init(Cipher.DECRYPT_MODE, chave);
      dectyptedText = cipher.doFinal(texto);
 
    } catch (Exception ex) {
      ex.printStackTrace();
    }
 
    return new String(dectyptedText);
  }
  
  /**
   * Decriptografa o texto puro usando chave privada.
   */
  public static String decriptografa(String hash ) {
    
	  // Verifica se já existe um par de chaves, caso contrário gera-se as chaves..
	  if (!verificaSeExisteChavesNoSO()) {
	   // Método responsável por gerar um par de chaves usando o algoritmo RSA e
	   // armazena as chaves nos seus respectivos arquivos.
	    geraChave();
	  }
	  
	String []  temp = hash.split(":");
    byte[] hashbByte = new byte[temp.length];
    for (int i = 0; i < temp.length; i++) {
		 
    	hashbByte[i] = Byte.parseByte(temp[i]);
	}
	  byte[] dectyptedText = null;
    
    try {
     
    		final Cipher cipher = Cipher.getInstance(ALGORITHM);
    		// Decriptografa o texto puro usando a chave Privada

    		ObjectInputStream inputStream = new ObjectInputStream(new FileInputStream(PATH_CHAVE_PRIVADA));
    		final PrivateKey chavePrivada = (PrivateKey) inputStream.readObject();
    		cipher.init(Cipher.DECRYPT_MODE, chavePrivada);
    		dectyptedText = cipher.doFinal(hashbByte);
    		inputStream.close();
    } catch (Exception ex) {
      ex.printStackTrace();
    }
 
    return new String(dectyptedText);
  }
 

}
