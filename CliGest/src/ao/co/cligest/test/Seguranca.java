package ao.co.cligest.test;

import java.math.BigInteger;
import java.security.InvalidKeyException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

import javax.crypto.BadPaddingException;
import javax.crypto.Cipher;
import javax.crypto.IllegalBlockSizeException;
import javax.crypto.KeyGenerator;
import javax.crypto.NoSuchPaddingException;
import javax.crypto.SecretKey;
import javax.crypto.spec.SecretKeySpec;



public class Seguranca {
	
	private static final String hexDigits = "0123456789abcdef";
	private static final String key = "140b41b22a29beb4061bda66b6747e14";
	/**
	 * Construtor
	 */
	public Seguranca() {
	}
	
	/**
	* Realiza a encripta��o apartir de uma chave.
	* @param Key - chave de codifica��o.
	* @param message - menssagem a ser codificada.
	* @return String - O resultado da criptografia em hexadecimal.
	* @throws Exception - Caso o algoritmo fornecido n�o seja v�lido
	*/
	public static String encrypt(String key, String message) throws Exception {

		byte[] hexByte = asByte(key);
		SecretKeySpec skeySpec = new SecretKeySpec(hexByte, "AES");

		// Instantiate the cipher
		Cipher cipher = Cipher.getInstance("AES");

		cipher.init(Cipher.ENCRYPT_MODE, skeySpec);

		byte[] encrypted = cipher.doFinal((message.getBytes()));
		return asHex(encrypted);
	}

	/**
	* Realiza a dencripta��o apartir de uma chave.
	* @param Key - chave de codifica��o.
	* @param message - menssagem a ser decodificada.
	* @return String - Retorna a String original.
	 * @throws NoSuchPaddingException 
	 * @throws NoSuchAlgorithmException 
	 * @throws InvalidKeyException 
	 * @throws BadPaddingException 
	 * @throws IllegalBlockSizeException 
	* @throws Exception - Caso o algoritmo fornecido n�o seja v�lido
	*/	
	public static String decrypt(String key, String hex) throws Exception{

		byte[] hexByte = asByte(key);
		SecretKeySpec skeySpec = new SecretKeySpec(hexByte, "AES");

		Cipher cipher = null;
		try {
			cipher = Cipher.getInstance("AES");
		} catch (NoSuchAlgorithmException e) {
			e.printStackTrace();
		} catch (NoSuchPaddingException e) {
			e.printStackTrace();
		}

		byte[] encrypted = new BigInteger(hex, 16).toByteArray();

		try {
			cipher.init(Cipher.DECRYPT_MODE, skeySpec);
		} catch (InvalidKeyException e) {
			e.printStackTrace();
		}
		
		byte[] original = null;
		
		try {
			original = cipher.doFinal(encrypted);
		} catch (IllegalBlockSizeException e) {
			e.printStackTrace();
		} catch (BadPaddingException e) {
			e.printStackTrace();
		}

		String originalString = new String(original);

		return originalString;
	}

	   @SuppressWarnings("unused")
	private static String generateKey() throws NoSuchAlgorithmException {
		// Get the KeyGenerator
		KeyGenerator kgen = KeyGenerator.getInstance("AES");
		kgen.init(128); // 192 and 256 bits may not be available

		// Generate the secret key specs.
		SecretKey skey = kgen.generateKey();
		byte[] raw = skey.getEncoded();

		return asHex(raw);
	}
	
	/**
	* Realiza um digest em um array de bytes atrav�s do algoritmo especificado
	* @param input - O array de bytes a ser criptografado
	* @param algoritmo - O algoritmo a ser utilizado
	* @return String - O resultado da criptografia
	* @throws NoSuchAlgorithmException - Caso o algoritmo fornecido n�o seja
	* v�lido
	*/
	private static String digest(byte[] input, String algoritmo)	throws Exception {
		MessageDigest md = null;
		try {
			md = MessageDigest.getInstance(algoritmo);
		} catch (NoSuchAlgorithmException e) {
			e.printStackTrace();
		}
		md.reset();
		
		return asHex(md.digest(input));
	}	
	
	/**
	 * Converte o array de bytes em uma representa��o hexadecimal.
	 * 
	 * @param input - O array de bytes a ser convertido.
	 * @return Uma String com a representa��o hexa do array
	 */
	private static String asHex(byte[] b) {
		StringBuffer buf = new StringBuffer();

		for (int i = 0; i < b.length; i++) {
			int j = (b[i]) & 0xFF;
			buf.append(hexDigits.charAt(j / 16));
			buf.append(hexDigits.charAt(j % 16));
		}

		return buf.toString();
	}

	/**
	 * Converte uma String hexa no array de bytes correspondente.
	 * 
	 * @param hexa
	 *            - A String hexa
	 * @return O vetor de bytes
	 * @throws IllegalArgumentException
	 *             - Caso a String n�o sej auma representa��o haxadecimal v�lida
	 */
	private static byte[] asByte(String hexa) throws IllegalArgumentException {

		// verifica se a String possui uma quantidade par de elementos
		if (hexa.length() % 2 != 0) {
			throw new IllegalArgumentException("String hexa inv�lida");
		}
		
		byte[] b = new byte[hexa.length() / 2];
		  
		for (int i = 0; i < hexa.length(); i += 2) {
			b[i / 2] = (byte) ((hexDigits.indexOf(hexa.charAt(i)) << 4) | (hexDigits.indexOf(hexa.charAt(i + 1))));
		}
		return b;
	}
	
	public static void main(String [] a) {
		try
		{
			String mes = encrypt(key, "1234567890");
			decrypt(key, "1d5b6c98122d061888cd5bc2f3810f35");
			System.out.println(decrypt(key, "1d5b6c98122d061888cd5bc2f3810f35"));
		}catch(Exception e){
			
		}
		
	}
}

