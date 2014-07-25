package fuentes.md5;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.math.BigInteger;
import java.security.*;
import fuentes.constantes.*;
import java.io.BufferedInputStream;

public class MD5 {

	private String filepath = "";


	/**
	 * @return the filepath
	 */
	public String getFilepath() {
		return filepath;
	}


	/**
	 * @param filepath the filepath to set
	 */
	public void setFilepath(String filepath) {
		this.filepath = filepath;
	}

	/**
	 * Definicion del constructor
	 */
	public MD5() {
	}
	/**
	 * Definicion del constructor
	 * @param path
	 */
	public MD5(String path) {
		this.filepath = path;
	}


	/**
	 * Metodo para calcular el hash MD5 de un fichero
	 * @return
	 * @throws RuntimeException
	 */
	public String calcularMD5() throws RuntimeException
	{
		String output = "";
		try {
			MessageDigest digest = MessageDigest.getInstance("MD5");
			File f = new File(filepath);
			BufferedInputStream is = new BufferedInputStream(new FileInputStream(f));
			byte[] buffer = new byte[8192];
			int read = 0;
			try {
				while( (read = is.read(buffer)) > 0) {
					digest.update(buffer, 0, read);
				}
				byte[] md5sum = digest.digest();
				BigInteger bigInt = new BigInteger(1, md5sum);
				output = bigInt.toString(16);
				 if (Constante.debug) System.out.println("MD5: " + output);
			}
			catch(IOException e) {
				throw new RuntimeException("Unable to process file for MD5", e);
			}
			finally {
				try {
					is.close();
				}
				catch(IOException e) {
					throw new RuntimeException("Unable to close input stream for MD5 calculation", e);
				}
			}
		} catch (NoSuchAlgorithmException e) {
			throw new RuntimeException("El algoritmo espedificado no es valido", e);
		} catch (FileNotFoundException e) {
			throw new RuntimeException("No se puede procesar el algoritmo para el fichero especificado", e);
		}
		return output;
	}

}
