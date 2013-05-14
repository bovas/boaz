package com.mycare.actions.utils;

import java.io.BufferedReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;

import org.apache.commons.httpclient.HttpClient;
import org.apache.commons.httpclient.HttpException;
import org.apache.commons.httpclient.methods.PostMethod;


public class HttpClientCheckMethod {
	public static void main(String[] args) throws HttpException, IOException {
		HttpClient client = new HttpClient();
		PostMethod post =new PostMethod("http://localhostfile/MVC/SendMail");
		client.executeMethod(post);
		InputStream in = post.getResponseBodyAsStream();
		BufferedReader br = new BufferedReader(new InputStreamReader(in));
		String str;
		FileWriter fw = new FileWriter("/home/glace/newfile.txt");
		while((str = br.readLine())!=null){
			fw.write(str);
		}
		if(fw!=null)
			fw.close();
	}
}
