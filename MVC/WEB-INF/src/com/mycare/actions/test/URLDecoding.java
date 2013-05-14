package com.mycare.actions.test;

import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;

public class URLDecoding {
	public static void main(String[] args) throws Exception {
		String s="http://hub.glaceemr.com/MultiFactorAuthentication/MFAServlet%3CMultiFactorAuthentication%3E%09%3CSender%3E%09%09%3CAccountId%3Estable%3C%2FAccountId%3E%09%09%3CFullName%3ETest+Doctor%3C%2FFullName%3E%09%09%3CUserName%3Edemodoctor%3C%2FUserName%3E%09%09%3CUserID%3E1115%3C%2FUserID%3E%09%09%3CDateTime%3E2013-01-31T10-56-29.0Z%3C%2FDateTime%3E%09%09%3CTransactionID%3Estable_163_2013-01-31T10-56-29.0Z%3C%2FTransactionID%3E%09%3C%2FSender%3E%09%3CVendor%3E%09%09%3CName%3ESafeNet%3C%2FName%3E%09%09%3CProduct%3ESafeNet%3C%2FProduct%3E%09%09%3CCredentials%3E%09%09%09%3CUserName%3Eglenwood-sraj%3C%2FUserName%3E%09%09%09%3CPassword%3Eglenwood_sraj%3C%2FPassword%3E%09%09%3C%2FCredentials%3E%09%3C%2FVendor%3E%09%3CData%3E%09%09%3CAction%3EValidate%3C%2FAction%3E%09%09%3CUserName%3Esraj%3C%2FUserName%3E%09%09%3Cpasscode%3E12312312%3C%2Fpasscode%3E%09%3C%2FData%3E%3C%2FMultiFactorAuthentication%3E";
		System.out.println(URLDecoder.decode(s, "UTF-8"));
		String str="Septra^Cefizox^^";
		String[] str1=str.split("^");
		System.out.println(str1.length);
	}
}
