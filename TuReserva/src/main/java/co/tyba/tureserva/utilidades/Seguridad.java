package co.tyba.tureserva.utilidades;

import java.math.BigInteger;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

import co.tyba.tureserva.excepciones.ExcepcionTecnica;

public class Seguridad {
	
	private Seguridad(){
		
	}
	
	public static String encriptarClave(String clave) throws ExcepcionTecnica   {
		
		try {
			
			MessageDigest md = MessageDigest.getInstance("MD5");
			byte[] messageDigest = md.digest(clave.getBytes());
			BigInteger number = new BigInteger(1, messageDigest);
			String claveEncriptada = number.toString(16);

			while (claveEncriptada.length() < 32) {
				claveEncriptada = "0" + claveEncriptada;
			}
			return claveEncriptada;
		} catch (NoSuchAlgorithmException e) {
			throw new ExcepcionTecnica(e);
		}
	}
}
