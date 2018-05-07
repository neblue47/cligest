package ao.co.cligest.test;

import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;

import javax.crypto.BadPaddingException;
import javax.crypto.Cipher;
import javax.crypto.IllegalBlockSizeException;
import javax.crypto.KeyGenerator;
import javax.crypto.NoSuchPaddingException;
import javax.crypto.SecretKey;

class ListarQuintasFeiras {
	public static void main(String a[]) 
	{
		String senha = "nelson";
		cifraTest(senha);
		cifraTest(senha);
	}
	
	static String cifra_senha(String senha)
	{
		try {
			KeyGenerator keyGerador = KeyGenerator.getInstance("AES");
			SecretKey keySecret = keyGerador.generateKey();
			Cipher cipher;
			cipher = Cipher.getInstance("AES");
			cipher.init(Cipher.ENCRYPT_MODE, keySecret);
			byte[] cifrada = cipher.doFinal(senha.getBytes());
			return cifrada.toString();

		} catch (NoSuchAlgorithmException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (NoSuchPaddingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (InvalidKeyException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IllegalBlockSizeException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (BadPaddingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return "";
	}
	static String decifra_senha(String cifrada)
	{
		byte cifradas[] = cifrada.getBytes();
		try {
			KeyGenerator keyGerador = KeyGenerator.getInstance("AES");
			SecretKey keySecret = keyGerador.generateKey();
			Cipher cipher;
			cipher = Cipher.getInstance("AES");		
			cipher.init(Cipher.DECRYPT_MODE, keySecret);
			byte[] decifrada = cipher.doFinal(cifradas);
			return new String(decifrada);

		} catch (NoSuchAlgorithmException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (NoSuchPaddingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (InvalidKeyException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IllegalBlockSizeException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (BadPaddingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return "";
	}
	  @SuppressWarnings("unused")
	private static String generateKey() throws NoSuchAlgorithmException {
			// Get the KeyGenerator
			KeyGenerator kgen = KeyGenerator.getInstance("AES");
			kgen.init(128); // 192 and 256 bits may not be available
			// Generate the secret key specs.
			SecretKey skey = kgen.generateKey();

			return skey.toString();
		}
	  
	  static void cifraTest(String senha)
	  {
		  
			System.out.println("Senha Original: " + senha);
			try {
				KeyGenerator keyGerador = KeyGenerator.getInstance("AES");
				SecretKey keySecret = keyGerador.generateKey();
				Cipher cipher;
				cipher = Cipher.getInstance("AES");
				cipher.init(Cipher.ENCRYPT_MODE, keySecret);
				byte[] cifrada = cipher.doFinal(senha.getBytes());
				System.out.print("Senha cifrada : "+cifrada+" SecretK: "+keySecret);
								
				cipher.init(Cipher.DECRYPT_MODE, keySecret);
				byte[] decifrada = cipher.doFinal(cifrada);
				System.out.println("\nSenha decifrada: " + new String(decifrada));

			} catch (NoSuchAlgorithmException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (NoSuchPaddingException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (InvalidKeyException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (IllegalBlockSizeException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (BadPaddingException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
	  }
}