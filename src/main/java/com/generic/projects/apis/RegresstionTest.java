package com.generic.projects.apis;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.HashMap;
import java.util.Map;

import org.apache.log4j.Logger;

public class RegresstionTest {

	static Logger logger = Logger.getLogger(RegresstionTest.class);

	public Map<String,Map<String,String>> sendRequest(String number) {
		int rCode;
		BufferedReader in;
		StringBuilder sb;
		Map<String, Map<String, String>>  outerMap = new HashMap<String,Map<String,String>> ();
		HashMap<String, String> innerMap = new HashMap<String,String>();
		try {
			URL postUrl = new URL("http://example.net/new-message.php"+"/"+number);
			HttpURLConnection request = (HttpURLConnection) postUrl.openConnection();
			request.setConnectTimeout(5000);
			request.setUseCaches(false);
			request.setDoOutput(true);
			request.setDoInput(true);
			
			HttpURLConnection.setFollowRedirects(true);
			request.setInstanceFollowRedirects(true);
			
			request	.setRequestProperty("Accept", "*/*");
			request.setRequestProperty("Accept-Encoding", "gzip,deflate,br");
			request.setRequestProperty("User-Agent", "useragent");
			request.setRequestProperty("Content-Type", "application/json");
	//		request.setRequestProperty("Content-length","0");
			
			request.setRequestMethod("POST");
			OutputStreamWriter post = new OutputStreamWriter(request.getOutputStream());
			post.flush();
			
			rCode = request.getResponseCode();
			String responseCode = String.valueOf(rCode);
			logger.info("Response code is "+rCode);
			if (rCode == 406) {
				in = new BufferedReader(new InputStreamReader(request.getErrorStream()));
			} else
				in = new BufferedReader(new InputStreamReader(request.getInputStream()));

			sb = new StringBuilder();
			String inputLine;
			while((inputLine = in.readLine())!=null) {
				sb.append(inputLine);
			}
			innerMap.put(responseCode, sb.toString());
			outerMap.put("response", innerMap);
			post.close();
			in.close();
			return outerMap;

		} catch (IOException e) {
			e.printStackTrace();
		}
		   return null;
	}
}
