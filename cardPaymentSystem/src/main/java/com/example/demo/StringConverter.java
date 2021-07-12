package com.example.demo;

import java.security.NoSuchAlgorithmException;
import java.time.LocalDateTime;
import java.util.Base64;

import javax.crypto.Cipher;
import javax.crypto.NoSuchPaddingException;
import javax.crypto.spec.IvParameterSpec;
import javax.crypto.spec.SecretKeySpec;

public class StringConverter {

	public static String fillRight(String paramString, char paramChar, int paramInt)
	{
		byte[] arrayOfByte1;
		if(paramString == null)
		{
			paramString = "";
		}
		
		try {
			arrayOfByte1 = paramString.getBytes("UTF-8");
		} catch (Exception e) {
			return paramString;
		}
		
		if(paramInt <= arrayOfByte1.length)
			return paramString;
		
		byte[] arrayOfByte2 = new byte[paramInt];
		int i = 0;
		for (int j=0; j<arrayOfByte1.length; ++j)
			arrayOfByte2[i++] = arrayOfByte1[j];
		byte paramByte = (byte) paramChar;
		while (i<paramInt)
		{
			arrayOfByte2[i] = paramByte;
		    ++i;
		}
		try {
			return new String(arrayOfByte2, "UTF-8");
		} catch (Exception e) {
			// TODO: handle exception
		}
		return paramString;
	}
	
	public static String fillLeft(String paramString, char paramChar, int paramInt)
	{
		byte[] arrayOfByte1;
		if(paramString == null)
		{
			paramString = "";
		}
		
		try {
			arrayOfByte1 = paramString.getBytes("UTF-8");
		} catch (Exception e) {
			return paramString;
		}
		
		if(paramInt <= arrayOfByte1.length)
			return paramString;
		
		byte[] arrayOfByte2 = new byte[paramInt];
		int i = paramInt-1;
		for (int j=arrayOfByte1.length-1; j>=0; --j)
			arrayOfByte2[i--] = arrayOfByte1[j];
		byte paramByte = (byte) paramChar;
		while (i>=0)
		{
			arrayOfByte2[i] = paramByte;
		    --i;
		}
		try {
			return new String(arrayOfByte2, "UTF-8");
		} catch (Exception e) {
			// TODO: handle exception
		}
		return paramString;
	}
	
	
	// 관리번호 생성
	public static String setMngtNo()
	{
		
		LocalDateTime now = LocalDateTime.now();
		
		String mngtNo = now.toString().replaceAll("-", "");
		mngtNo = mngtNo.replaceAll(":", "");
		mngtNo = mngtNo.substring(0, mngtNo.indexOf("."));
		
		return fillRight(mngtNo,'0', 20);
	}
	
	
	public static String setMsgFormat(String name, String value)
	{
//		if(name == null || name.trim().length() == 0)
//		{
//			return "";
//		}
//		if(value == null || value.trim().length() == 0)
//		{
//			return "";
//		}

		
		switch (name) {
		case "dataLen":		// 데이터길이 : 숫자
			value = fillLeft(value, ' ', 4);
			break;
		case "dataFlag":	// 데이터구분 : 문자
			value = fillRight(value, ' ', 10);
			break;
		case "cardNo":		// 카드번호 : 숫자(L)
			value = fillRight(value, ' ', 20);
			break;	
		case "miCnt":		// 할부개월수 : 숫자(0)
			value = fillLeft(value, '0', 2);
			break;	
		case "cardLife":	// 카드유효기간 : 숫자(L)
			value = fillRight(value, ' ', 4);
			break;
		case "cvc":			// cvc : 숫자(L)
			value = fillRight(value, ' ', 3);
			break;
		case "tradeAmt":	// 거래금액 : 숫자
			value = fillLeft(value, ' ', 10);
			break;
		case "vat":			// 부가가치세 : 숫자(0)
			value = fillLeft(value, '0', 10);
			break;
		case "originMngtNo":	// 원거래관리번호 : 문자
			value = fillRight(value, ' ', 20);
			break;
		case "encodeCardInfo":	// 암호화된카드정보 : 문자
			value = fillRight(value, ' ', 300);
			break;
		case "filler":			// 예비필드 : 문자
			value = fillRight(value, ' ', 47);
			break;
		default:
			value = "";
			break;
		}
		
		return value;
	}
	
	
	public static String alg = "AES/CBC/PKCS5Padding";
	public static String key = "01234567890123456789012345678901";
	public static String iv = key.substring(0, 16); // 16byte
    
	public static String ecryptCardInfo(String cardNo, String cardLife, String cvc) throws Exception
	{
		String text = cardNo + "," + cardLife +","+ cvc;
				
		
		Cipher cipher = Cipher.getInstance(alg);
		SecretKeySpec keySpec = new SecretKeySpec(iv.getBytes(), "AES");
		IvParameterSpec ivParamSpec = new IvParameterSpec(iv.getBytes());
		cipher.init(Cipher.ENCRYPT_MODE, keySpec, ivParamSpec);
		
		byte[] encrypted = cipher.doFinal(text.getBytes("UTF-8"));
		return Base64.getEncoder().encodeToString(encrypted);
	}
	
	public static String decrypt(String cipherText) throws Exception {
        Cipher cipher = Cipher.getInstance(alg);
        SecretKeySpec keySpec = new SecretKeySpec(iv.getBytes(), "AES");
        IvParameterSpec ivParamSpec = new IvParameterSpec(iv.getBytes());
        cipher.init(Cipher.DECRYPT_MODE, keySpec, ivParamSpec);

        byte[] decodedBytes = Base64.getDecoder().decode(cipherText);
        byte[] decrypted = cipher.doFinal(decodedBytes);
        return new String(decrypted, "UTF-8");
    }
	
}
