package ao.co.cligest.test;
import groovy.json.internal.Byt;

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
   
   
  public class EncriptaDecriptaRSA {
   
    public static final String ALGORITHM = "RSA";
   
    /**
     * Local da chave privada no sistema de arquivos.
     */
    public static final String PATH_CHAVE_PRIVADA = "C:/keys/private.key";
   
    /**
     * Local da chave pública no sistema de arquivos.
     */
    public static final String PATH_CHAVE_PUBLICA = "C:/keys/public.key";
   
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
        ObjectOutputStream chavePublicaOS = new ObjectOutputStream(
            new FileOutputStream(chavePublicaFile));
        chavePublicaOS.writeObject(key.getPublic());
        chavePublicaOS.close();
   
        // Salva a Chave Privada no arquivo
        ObjectOutputStream chavePrivadaOS = new ObjectOutputStream(
            new FileOutputStream(chavePrivadaFile));
        chavePrivadaOS.writeObject(key.getPrivate());
        chavePrivadaOS.close();
      } catch (Exception e) {
        e.printStackTrace();
      }
   
    }
   
    /**
     * Verifica se o par de chaves Pública e Privada já foram geradas.
     */
    public static boolean verificaSeExisteChavesNoSO() {
   
      File chavePrivada = new File(PATH_CHAVE_PRIVADA);
      File chavePublica = new File(PATH_CHAVE_PUBLICA);
   
      if (chavePrivada.exists() && chavePublica.exists()) {
        return true;
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
   
    public static String decriptografa(String hash ) {
        
  	  String []  temVt = hash.split(":");
   
      byte[] textoCriptografado2 = new byte[temVt.length];
      System.out.println("");
      for (int i = 0; i < temVt.length; i++) {
  		 
      	 textoCriptografado2[i] = Byte.parseByte(temVt[i]);
  	}
  	  byte[] dectyptedText = null;
      
      try {
       
      		final Cipher cipher = Cipher.getInstance(ALGORITHM);
      		// Decriptografa o texto puro usando a chave Privada

      		ObjectInputStream inputStream = new ObjectInputStream(new FileInputStream(PATH_CHAVE_PRIVADA));
      		final PrivateKey chavePrivada = (PrivateKey) inputStream.readObject();
      		cipher.init(Cipher.DECRYPT_MODE, chavePrivada);
      		dectyptedText = cipher.doFinal(textoCriptografado2);
      		inputStream.close();
      } catch (Exception ex) {
        ex.printStackTrace();
      }
   
      return new String(dectyptedText);
    }
    /**
     * Testa o Algoritmo
     */
    public static void main(String[] args) {
   
      try {
   
        // Verifica se já existe um par de chaves, caso contrário gera-se as chaves..
        if (!verificaSeExisteChavesNoSO()) {
         // Método responsável por gerar um par de chaves usando o algoritmo RSA e
         // armazena as chaves nos seus respectivos arquivos.
          geraChave();
        }
   
        final String msgOriginal = "administrador";
        ObjectInputStream inputStream = null;
        String hash ="";
        // Criptografa a Mensagem usando a Chave Pública
        inputStream = new ObjectInputStream(new FileInputStream(PATH_CHAVE_PUBLICA));
        final PublicKey chavePublica = (PublicKey) inputStream.readObject();
        final byte[] textoCriptografado = criptografa(msgOriginal, chavePublica);
        for (int i = 0; i < textoCriptografado.length; i++) {
			hash += textoCriptografado[i] + ":";
        	System.out.print (hash);
		}
        String []  temVt = hash.split(":");
//        final byte[] textoCriptografado2 = Byte.parseByte(hash.split(";"));
        byte[] textoCriptografado2 = new byte[temVt.length];
        System.out.println("");
        for (int i = 0; i < temVt.length; i++) {
			 
        	 textoCriptografado2[i] = Byte.parseByte(temVt[i]);
		}
        System.out.println("");
        // Decriptografa a Mensagem usando a Chave Pirvada
        inputStream = new ObjectInputStream(new FileInputStream(PATH_CHAVE_PRIVADA));
        final PrivateKey chavePrivada = (PrivateKey) inputStream.readObject();
        final String textoPuro = decriptografa(textoCriptografado, chavePrivada);
        final String textoBD = decriptografa(hash);
   
        // Imprime o texto original, o texto criptografado e 
        // o texto descriptografado.
        System.out.println("Mensagem Hash: "+hash);
        System.out.println("Mensagem Original: " + msgOriginal);
        System.out.println("Mensagem Criptografada: " +textoCriptografado.toString());
        System.out.println("Mensagem Decriptografada: " + textoPuro);
        System.out.println("Mensagem Decriptografada: " + textoBD);
   
      } catch (Exception e) {
        e.printStackTrace();
      }
    }
  }