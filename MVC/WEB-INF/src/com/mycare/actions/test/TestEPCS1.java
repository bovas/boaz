package com.mycare.actions.test;

import java.nio.charset.Charset;
import java.security.InvalidAlgorithmParameterException;
import java.security.InvalidKeyException;
import java.security.KeyPair;
import java.security.KeyPairGenerator;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.security.PrivateKey;
import java.security.PublicKey;
import java.security.Signature;
import java.security.SignatureException;
import java.security.spec.RSAKeyGenParameterSpec;

import javax.crypto.Cipher;

import org.apache.commons.codec.binary.Hex;

import sun.security.rsa.RSAKeyPairGenerator;


public class TestEPCS1 {
private String publicKey;
public byte[] getPlainTextDigestValue(String data){			
		byte[] result =null;		
		try {							
			
			
			MessageDigest md = MessageDigest.getInstance("SHA-1");
			result = md.digest(data.getBytes());
			/*System.out.println(new sun.misc.BASE64Encoder().encode(md.digest(data.getBytes())));			
			System.out.println(new sun.misc.BASE64Encoder().encode(result.getBytes()));
			*/
			/*md.reset();
			md.update(data.getBytes("ASCII"));			
            result = new String(md.digest());*/            						
		} catch (NoSuchAlgorithmException e) {
			e.printStackTrace();
		}
		finally{
			return result;
		}
		
	}
private String convertToHex(byte[] data) {
    StringBuffer buf = new StringBuffer();
    for (int i = 0; i < data.length; i++) {
        int halfbyte = (data[i] >>> 4) & 0x0F;
        int two_halfs = 0;
        do {
            if ((0 <= halfbyte) && (halfbyte <= 9))
                buf.append((char) ('0' + halfbyte));
            else
            	buf.append((char) ('a' + (halfbyte - 10)));
            halfbyte = data[i] & 0x0F;
        } while(two_halfs++ < 1);
    }
    return buf.toString();
}
public String getEncryptedSignature(String digestData) throws SignatureException, InvalidKeyException{
	
	
	KeyPairGenerator keyGen;
    String result = "";
    boolean isPrivateKeyNull = false;
    boolean isPublicKeyNull = false;
    try {
            keyGen = KeyPairGenerator.getInstance("RSA");
            RSAKeyGenParameterSpec spec = new RSAKeyGenParameterSpec(1024,RSAKeyGenParameterSpec.F4);
            keyGen.initialize(spec);
            String keySet = getPrivateAndPublicKey();
            KeyPair keyPair = keyGen.generateKeyPair();
            System.out.println("keyPair ::::: "+keyPair);
            PrivateKey privateKey = keyPair.getPrivate();
            System.out.println("privateKey::"+privateKey);
            
           	PublicKey publicKey = keyPair.getPublic();
           	System.out.println("publicKey::"+publicKey.toString());
           	publicKey = keyPair.getPublic();
            setPublicKey(publicKey.toString());
            
            byte digestDataByteForm[] = digestData.getBytes();
            Signature signer = Signature.getInstance("SHA1withRSA");
            try {
                    signer.initSign(privateKey);
                    signer.update(digestDataByteForm);
                    byte[] signature = signer.sign();
                    result = signature.toString();
            } catch (InvalidKeyException e) {
                    e.printStackTrace();
            }
    } catch (NoSuchAlgorithmException e1) {
            e1.printStackTrace();
    }
    catch (InvalidAlgorithmParameterException e) {
            e.printStackTrace();
    }
    finally{
    	return result;
    }
}

public void create() throws NoSuchAlgorithmException{
	KeyPairGenerator keyGen = KeyPairGenerator.getInstance("RSA");	
	//keyGen.initialize(1024, sr);
	KeyPair keypair = keyGen.generateKeyPair();
	PrivateKey privKey = keypair.getPrivate();
	PublicKey pubKey = keypair.getPublic();
	RSAKeyPairGenerator gen = new RSAKeyPairGenerator();
	//gen.init(new RSAKeyGenerationParameters(BigInteger.valueOf(3), sr, 1024, 80));
}
public PublicKey getPublicKey(String spi_root){
	
	PublicKey publicKey = null;
	try {
		System.out.println("--------- inside PublicKey try -----------");
		//publicKey = (PublicKey) dbUtils.newTableLookUp("prescriber_public_key", "prescriber_details" , "doctorid = "+spi_root);
		System.out.println("------ publicKey ------ "+publicKey);
	} catch (Exception e) {
		e.printStackTrace();
	}
	return publicKey;
}
public String getPublicKey() {
	return publicKey;
}
public String getPrivateAndPublicKey(){
	
	String result = "";
	
	return result;
}
public void setPublicKey(String publicKey) {
	this.publicKey = publicKey;
}
	public static void main(String[] args) throws InvalidKeyException, SignatureException {
		TestEPCS1 epcs = new TestEPCS1();
		//String plainTextData = "BA1234568DoctorTest1903, new south streetNew JersyDC804521321PLOWERHOWARD76 DEERLAKE RDPHILADELPHIAPA01913OxyCODONE Hydrochloride 20 mg tablet5.555Take a tablet  twice a day with plenty of water.2012091320120914R0send required drug abuse treatment code and GHB reason code.";
		String plainTextData = "BA1234568DoctorTest1903, new south streetNew JersyDC804521321PLOWERHOWARD76 DEERLAKE RDPHILADELPHIAPA01913OxyCODONE Hydrochloride 20 mg tablet5.555Take a tablet  twice a day with plenty of water.2012091320120914R0send required drug abuse treatment code and GHB reason code.";
		String digestValue = new sun.misc.BASE64Encoder().encode(epcs.getPlainTextDigestValue(plainTextData));
		String encryptedSignature = new sun.misc.BASE64Encoder().encode(epcs.getEncryptedSignature(digestValue).getBytes());
		String x509Data = new sun.misc.BASE64Encoder().encode(epcs.getPublicKey().getBytes());
		System.out.println("digestValue::"+digestValue);
		System.out.println(encryptedSignature);
		System.out.println(x509Data);
	}
}
