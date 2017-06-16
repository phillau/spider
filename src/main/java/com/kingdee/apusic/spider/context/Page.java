package com.kingdee.apusic.spider.context;

import java.io.BufferedReader;
import java.io.ByteArrayInputStream;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLConnection;

import net.htmlparser.jericho.Source;

/**
 * @author 陈庆钊
 * @version 2017-4-13 下午4:19:39
 * @Email parkme210@126.com
 */
public class Page {

	public static String getContent(String url) throws Exception {

		StringBuffer sb = new StringBuffer("");
		URL urlT = null;
		try {
			urlT = new URL(url);
		} catch (MalformedURLException e) {
			System.out.println("打开超时。。。。");
		}
		URLConnection context = urlT.openConnection();
		InputStream in = context.getInputStream();

		try {
			BufferedReader br = new BufferedReader(new InputStreamReader(in,
					"gb2312"));
			String s = null;
			while ((s = br.readLine()) != null) {// 使用readLine方法，一次读一行
				sb.append(s);
			}
			br.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return sb.toString();
	}

	public static void setProperties(URLConnection conn) {
		conn.setRequestProperty(
				"Cookie",
				"PREF=ID=50ecee25e6158be9:U=09591b7451d583a9:FF=1:LD=zh-CN:NW=1:TM=1276692359:LM=1282280648:S=yCeAMql2fQdx1TNf; NID=36=czadLRFDzxz0Gz9U1Mt7H-6jjdEZEFMABSm7T9cxLOTFpMea6ksmtJzUo2ipuaag4Ju9vgp57RwaN7VvK1sxatXE6LUxRxaI8LPAoNpqZWjFIEQH_AzXD6fk_B91CnIw");
		conn.setRequestProperty("Connection", "keep-alive");
		conn.setRequestProperty("Accept-Encoding", "gzip,deflate");
		conn.setRequestProperty(
				"User-agent",
				"Mozilla/4.0 (compatible; MSIE 8.0; Windows NT 5.1; Trident/4.0;  Embedded Web Browser from: http://bsalsa.com/; .NET CLR 2.0.50727; InfoPath.2; CIBA; .NET CLR 3.0.04506.648; .NET CLR 3.5.21022; .NET CLR 3.0.4506.2152; .NET CLR 3.5.30729; msn OptimizedIE8;ZHCN; AskTB5.6)");
		conn.setRequestProperty("Accept-Charset", "GBK,utf-8;q=0.7,*;q=0.3");
		conn.setRequestProperty("Accept-Language", "zh-cn");
		conn.setRequestProperty(
				"Accept",
				"application/xml,application/xhtml+xml,text/html;q=0.9,text/plain;q=0.8,image/png,*/*;q=0.5");
		conn.setRequestProperty("Cache-Control", "max-age=0");
	}

	public static Source getSource(String url) throws Exception {
		URL urlT = null;
		try {
			urlT = new URL(url);
		} catch (MalformedURLException e) {
			System.out.println("打开超时。。。。");
		}
		URLConnection connection = urlT.openConnection();
		// setProperties(connection);
		InputStream in = connection.getInputStream();

		Source source = new Source(new InputStreamReader(in, "utf-8"));
		return source;

		/*
		 * try{ BufferedReader br = new BufferedReader(new InputStreamReader(in,
		 * "gb2312")); String s = null; while((s =
		 * br.readLine())!=null){//使用readLine方法，一次读一行 sb.append(s); }
		 * br.close(); }catch(Exception e){ e.printStackTrace(); }
		 */
	}

	public static String getSources(String url) throws Exception {

		URL urlT = null;
		try {
			urlT = new URL(url);
		} catch (MalformedURLException e) {
			System.out.println("打开超时。。。。");
		}
		URLConnection context = urlT.openConnection();
		InputStream in = context.getInputStream();
		StringBuffer sb = new StringBuffer("");
		try {
			BufferedReader br = new BufferedReader(new InputStreamReader(in,
					"utf-8"));
			String s = null;
			while ((s = br.readLine()) != null) {// 使用readLine方法，一次读一行
				sb.append(s);
			}
			br.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return sb.toString();
	}

	public static InputStream getStringStream(String sInputString) {
		if (sInputString != null && !sInputString.trim().equals("")) {
			try {
				ByteArrayInputStream tInputStringStream = new ByteArrayInputStream(
						sInputString.getBytes());
				return tInputStringStream;
			} catch (Exception ex) {
				ex.printStackTrace();
			}
		}
		return null;
	}

}
