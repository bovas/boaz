package com.mycare.actions.test;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.math.BigInteger;
import java.security.InvalidAlgorithmParameterException;
import java.security.KeyPair;
import java.security.KeyPairGenerator;
import java.security.NoSuchAlgorithmException;
import java.security.Provider;
import java.security.PublicKey;
import java.security.Security;
import java.security.cert.CertificateException;
import java.security.cert.CertificateFactory;
import java.security.cert.X509Certificate;
import java.security.spec.RSAKeyGenParameterSpec;
import java.util.Arrays;
import java.util.Date;

import javax.security.auth.x500.X500Principal;

import org.bouncycastle.x509.X509V1CertificateGenerator;

import sun.misc.BASE64Decoder;

public class X509{
	
	public static void main(String[] args){
		X509V1CertificateGenerator certGen = new X509V1CertificateGenerator();
		X500Principal              dnName = new X500Principal("CN=Test CA Certificate");
		try{
		KeyPairGenerator keyGen = KeyPairGenerator.getInstance("RSA");
        RSAKeyGenParameterSpec spec = new RSAKeyGenParameterSpec(1024,RSAKeyGenParameterSpec.F4);
        keyGen.initialize(spec);
                
        KeyPair keyPair = keyGen.generateKeyPair();
        PublicKey pk =keyPair.getPublic(); 
        
		/*CertificateFactory cf = CertificateFactory.getInstance("X.509");
        String PKstr = pk.toString();
        System.out.println(PKstr.getBytes());
        InputStream PKstream = new ByteArrayInputStream(PKstr.getBytes());*/
                
        //X509Certificate pkcert = (X509Certificate)cf.generateCertificate(PKstream);
        //System.out.println(pkcert.toString());
        
	
                		
		certGen.setSerialNumber(new BigInteger("123456789"));
        certGen.setIssuerDN(dnName);
        Date startDate = new Date();
		certGen.setNotBefore(startDate);
        Date expiryDate = new Date();
		certGen.setNotAfter(expiryDate);
        certGen.setSubjectDN(dnName);                       // note: same as issuer
        certGen.setPublicKey(keyPair.getPublic());
        certGen.setSignatureAlgorithm("SHA1withRSA");

        X509Certificate cert = certGen.generate(keyPair.getPrivate(), "BC");
        System.out.println(cert.toString());
		}catch(Exception e){e.printStackTrace();}
	}	        	
}