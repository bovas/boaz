package com.mycare.actions.test;

import org.apache.commons.codec.DecoderException;
import org.apache.commons.codec.net.BCodec;

class EncodePassword extends BCodec
{
	BCodec bc=new BCodec();
	
	String getEncodedPassword(byte[] password)
	{
		try
		{
			String s=new String(password,"UTF-8");
			byte result[]=this.doEncoding(s.getBytes());
			return new String(result);
		}catch(Exception e){e.printStackTrace();}
		return "";
	}
	public static void main(String[] args) throws Exception {
		EncodePassword ep = new EncodePassword();
		System.out.println(ep.decode(("SUUT7XSJsgXCepx9hYRvRTQ9e48=").getBytes()));
		System.out.println(ep.getEncodedPassword("Glenwood10ss!".getBytes()));
	}
}