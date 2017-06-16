package com.kingdee.apusic.spider.common.data;

import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.SocketTimeoutException;
import java.net.URL;
import java.net.URLConnection;

public class FeedParse {

	private URL feedUrl;

	public FeedParse(String feedUrl) {
		try {
			this.feedUrl = new URL(feedUrl);
		} catch (MalformedURLException e) {
			e.printStackTrace();
		}
	}

	public InputStream getInputStream() {
		try {
			HttpURLConnection conn = (HttpURLConnection) feedUrl
					.openConnection();
			conn.setReadTimeout(30 * 1000);
			conn.setRequestMethod("GET");
			conn.setRequestProperty(   
		              "User-Agent",   
		              "Mozilla/4.0 (compatible; MSIE 6.0; Windows NT 5.2; Trident/4.0; .NET CLR 1.1.4322; .NET CLR 2.0.50727)");
			InputStream inStream = conn.getInputStream();
			return inStream;
		}catch (SocketTimeoutException e) {
			System.out.println(feedUrl.toString()+"链接打开超时...");
			return null;
		}catch (IOException e){
			System.out.println(feedUrl.toString()+"链接打开异常...");
			return null ;
		}
	}
	
	public URLConnection getConnetion() {
		try {
			HttpURLConnection conn = (HttpURLConnection) feedUrl
					.openConnection();
			conn.setReadTimeout(30 * 1000);
			conn.setRequestMethod("GET");
			conn.setRequestProperty(   
		              "User-Agent",
		              "Mozilla/4.0 (compatible; MSIE 6.0; Windows NT 5.2; Trident/4.0; .NET CLR 1.1.4322; .NET CLR 2.0.50727)");
			return conn;
		}catch (SocketTimeoutException e) {
			System.out.println(feedUrl.toString()+"RSS打开超时...");
			return null;
		}catch (IOException e){
			System.out.println(feedUrl.toString()+"RSS打开异常...");
			return null ;
		}
	}
}
