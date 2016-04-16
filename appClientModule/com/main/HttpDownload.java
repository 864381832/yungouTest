package com.main;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.util.HttpGetUtil;

public class HttpDownload {

	public static final int cache = 10 * 1024;

	public static String download(String url, String filepath) {
		try {
			HttpClient client = HttpClients.createDefault();
			HttpGet httpget = new HttpGet(url);
			HttpResponse response = client.execute(httpget);

			HttpEntity entity = response.getEntity();
			InputStream is = entity.getContent();
			File file = new File(filepath);
			FileOutputStream fileout = new FileOutputStream(file);
			/**
			 * 根据实际运行效果 设置缓冲区大小
			 */
			byte[] buffer = new byte[cache];
			int ch = 0;
			while ((ch = is.read(buffer)) != -1) {
				fileout.write(buffer, 0, ch);
			}
			is.close();
			fileout.flush();
			fileout.close();

		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}

	public static void main(String[] args) {
		File file = null;
		for (int i = 0; i < 22000; i++) {
			file = new File("D:\\image\\" + i + ".jpg");
			if (file.length() != 0) {
				file.delete();
			}
		}
//		readerGoodsInfoFile();
	}

	// 读取信息
	public static void readerGoodsInfoFile() {
		try {
			File file = new File("D:\\user.txt");
			BufferedReader br = new BufferedReader(new InputStreamReader(new FileInputStream(file), "utf-8"));
			String goodsInfo = br.readLine();
			int i = 0;
			while (goodsInfo != null) {
				if (!goodsInfo.equals("\"/Images/defaultUserFace.png\"")) {
					System.out.print("名" + goodsInfo + (i++));
					System.out.println("jie取" + goodsInfo.substring(13, goodsInfo.length() - 1));
					renameTo(goodsInfo.substring(13, goodsInfo.length() - 1));
				}
				goodsInfo = br.readLine();
			}
			br.close();
		} catch (Exception e) {
		}
	}

	public static void renameTo(String name) {
		File file = null;
		for (int i = 0; i < 22000; i++) {
			file = new File("D:\\image\\" + i + ".jpg");
			if (file.length() != 0) {
				file.renameTo(new File("D:\\image\\" + name));
				return;
			}
		}
	}

	private static void getImage() {
		// String url =
		// "http://faceimg.1yyg.com/UserFace/160/20160224022308425.jpg";
		// String filepath = "D:\\image\\a.jpg";
		// HttpDownload.download(url, filepath);
		Map<String, Integer> sMap = new HashMap<String, Integer>();
		int ind = 20000;
		// 返回值{"Code":0,"Count":55,"Data":{"Tables":{"BuyList":{"Rows":[{"userName":"","userPhoto":"","userWeb":"","buyNum":"","buyIP":"","buyIPAddr":"","buyTime":"","buyDevice":"0","buyID":"318702471"}
		String refererUrl = "http://www.1yyg.com/lottery/4125612.html";
		CloseableHttpClient HttpClient = HttpClients.createDefault();
		// HttpGetUtil.getHttpData("http://api.1yyg.com/JPData?action=GetUserBuyListByCodeEnd&codeID=4125615&FIdx=1&EIdx=10&isCount=0",
		// refererUrl, HttpClient);
		for (int i = 1; i < 50000;) {
			String url = "http://api.1yyg.com/JPData?action=GetUserBuyListByCodeEnd&codeID=4125612&FIdx=" + i + "&EIdx="
					+ (i + 9) + "&isCount=0";
			String data = HttpGetUtil.getHttpData(url, refererUrl, HttpClient);
			try {
				data = data.split("Rows\":", 2)[1];
				data = data.substring(0, data.length() - 5);
				List<Map<String, String>> dList = new Gson().fromJson(data, new TypeToken<List<Map<String, String>>>() {
				}.getType());
				for (Map<String, String> maps : dList) {
					String userPhoto = maps.get("userPhoto");
					System.out.println(userPhoto + ":" + !"00000000000000000.jpg".equals(userPhoto) + ":" + ind);
					if (!"00000000000000000.jpg".equals(userPhoto) && sMap.get(userPhoto) == null) {
						sMap.put(userPhoto, ind++);
						String userPhotoUrl = "http://faceimg.1yyg.com/UserFace/160/" + userPhoto;
						String filepath = "D:\\image\\" + ind + ".jpg";
						HttpDownload.download(userPhotoUrl, filepath);
					}
				}
				i = i + 10;
			} catch (Exception e) {
			}
		}
	}
}