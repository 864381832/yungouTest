package com.main;

import java.io.IOException;
import java.net.URLEncoder;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;

import com.util.HttpGetUtil;

public class TestJsoup {
	public static void main(String[] args) {
		long beginTime = System.currentTimeMillis();
		try {
			TestUrl();
		} catch (IOException e) {
			e.printStackTrace();
		}
		System.out.println("执行耗时 : " + (System.currentTimeMillis() - beginTime) / 1000f + " 秒 ");
	}

	public static String TestUrl() throws IOException {
		String url = null;
		String refererUrl = null;
		String content = null;

		String url2 = "http://www.google.com/";         
		
		url = "https://authzui.alipay.com:443/login/index.htm?logonId=18356971618&password_rsainput="+URLEncoder.encode("xu>520?", "UTF-8");
		System.out.println(url);
		content = HttpGetUtil.getHttpData(url, "https://auth.alipay.com/login/index.htm");
		System.out.println("内容：" + content);
		
		Document doc = null;
		doc = Jsoup.connect("http://www.baidu.com/").get();
		System.out.println(doc.html());
		String title = doc.title();
		System.out.println("内容：" + title);
		return content;

	}

}