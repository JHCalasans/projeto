package br.com.ido.qpedido.util;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

public class HashUtil {

	public static String gerarHash(String frase, String algoritmo) throws NoSuchAlgorithmException {
		return gerarHash(frase.getBytes(), algoritmo);
	}

	public static String gerarHashMD5(String frase) throws NoSuchAlgorithmException {
		return gerarHash(frase, "MD5");
	}
	
	public static String gerarHashMD5(byte[] arquivo) throws NoSuchAlgorithmException {
		return gerarHash(arquivo, "MD5");
	}	
	
	public static String gerarHash(byte[] arquivo, String algoritmo) throws NoSuchAlgorithmException {
		String md5Hash = "";
		MessageDigest md = MessageDigest.getInstance("MD5");
		byte[] hash = md.digest(arquivo);
		for (int i = 0; i < hash.length; i++) {
			md5Hash += String.format("%02x", (int) (hash[i] & 0xFF));
		}
		return md5Hash;
	}	

}
