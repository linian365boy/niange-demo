package com.demo.nian.httpClient;

import java.io.IOException;
import java.security.KeyManagementException;
import java.security.NoSuchAlgorithmException;
import java.security.cert.CertificateException;
import java.security.cert.X509Certificate;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Timer;
import java.util.TimerTask;

import javax.net.ssl.SSLContext;
import javax.net.ssl.TrustManager;
import javax.net.ssl.X509TrustManager;
import javax.swing.JOptionPane;

import org.apache.http.Consts;
import org.apache.http.NameValuePair;
import org.apache.http.client.config.AuthSchemes;
import org.apache.http.client.config.CookieSpecs;
import org.apache.http.client.config.RequestConfig;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.config.Registry;
import org.apache.http.config.RegistryBuilder;
import org.apache.http.conn.socket.ConnectionSocketFactory;
import org.apache.http.conn.socket.PlainConnectionSocketFactory;
import org.apache.http.conn.ssl.NoopHostnameVerifier;
import org.apache.http.conn.ssl.SSLConnectionSocketFactory;
import org.apache.http.cookie.Cookie;
import org.apache.http.impl.client.BasicCookieStore;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.impl.conn.PoolingHttpClientConnectionManager;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.util.EntityUtils;

import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonParser;

/**
 * @ClassName: MySqlHttpClient  
 * @Description: 深圳出入境预约
 * @date: 2016年11月1日 下午2:23:55 
 * 
 * @author tanfan 
 * @version  
 * @since JDK 1.7
 */
public class SzHttpClient {
	
	private static SSLConnectionSocketFactory socketFactory;
	private final static String hostName = "http://yysl.sz3e.com";
	private static String cookie = "JSESSIONID=1C3D12B76DA2D5F62E9F740D4DD4A149";
	private static Timer timer;
	
	// 创建CookieStore实例
	//static CookieStore cookieStore = new BasicCookieStore();
	private static TrustManager manager = new X509TrustManager() {
		@Override
		public X509Certificate[] getAcceptedIssuers() {
			return null;
		}
		@Override
		public void checkServerTrusted(X509Certificate[] chain, String authType) throws CertificateException {
		}
		@Override
		public void checkClientTrusted(X509Certificate[] chain, String authType) throws CertificateException {
		}
	};
	
	private static void enableSSL(){
		try{
			SSLContext context = SSLContext.getInstance("TLS");
			context.init(null, new TrustManager[]{manager}, null);
			socketFactory = new SSLConnectionSocketFactory(context, NoopHostnameVerifier.INSTANCE);
		}catch(NoSuchAlgorithmException e){
			e.printStackTrace();
		}catch(KeyManagementException e){
			e.printStackTrace();
		}
	}
	
	private static Map<String,Object> doHttpPost(String url, List<NameValuePair> values, String cookie, String refer) 
			throws IOException {
		Map<String,Object> responseResultMap = new HashMap<>();
		enableSSL();
		BasicCookieStore cookieStore = new BasicCookieStore();
		RequestConfig defaultRequestConfig = RequestConfig.custom().setCookieSpec(CookieSpecs.STANDARD_STRICT)
				.setExpectContinueEnabled(true).setTargetPreferredAuthSchemes(Arrays.asList(AuthSchemes.NTLM,AuthSchemes.DIGEST))
				.setProxyPreferredAuthSchemes(Arrays.asList(AuthSchemes.BASIC)).build();
		HttpPost post = new HttpPost(url);
		if(cookie != null){
			post.setHeader("Cookie",cookie);
		}
		if(refer != null){
			post.setHeader("Referer", refer);
		}
		UrlEncodedFormEntity entity = new UrlEncodedFormEntity(values, Consts.UTF_8);
		post.setEntity(entity);
		Registry<ConnectionSocketFactory> socketFactoryRegistry = RegistryBuilder.<ConnectionSocketFactory>create()
				.register("http", PlainConnectionSocketFactory.INSTANCE)
				.register("https", socketFactory).build();
		
		PoolingHttpClientConnectionManager connectionManager = new PoolingHttpClientConnectionManager(socketFactoryRegistry);
		CloseableHttpClient httpClient = HttpClients.custom()
				.setDefaultCookieStore(cookieStore)
				.setConnectionManager(connectionManager)
				.setDefaultRequestConfig(defaultRequestConfig).build();
		
		CloseableHttpResponse response = httpClient.execute(post);
		//System.out.println("Post logon cookies:");
        List<Cookie> cookies = cookieStore.getCookies();
        if (cookies.isEmpty()) {
            //System.out.println("None");
        } else {
            //for (int i = 0; i < cookies.size(); i++) {
              //  System.out.println("- " + cookies.get(i).toString());
           // }
        }
        responseResultMap.put("response",response);
        responseResultMap.put("cookie", cookies);
		return responseResultMap;
	}
	
	
	public static void main(String[] args) throws Exception {
		//queryMemberDirtyData();
		timer = new Timer();
		TimerTask task = new TimerTask() {
			@Override
			public void run() {
				try {
					queryMemberDirtyDataByNoLogin();
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		};
		timer.schedule(task, 1000, 5000);
	}
	
	public static void queryMemberDirtyDataByNoLogin()throws Exception {
		String executeUrl = hostName+"/wsyysq/gzRegionTbAction/getSlsjBySldw.do";
		List<NameValuePair> executeParams = new ArrayList<NameValuePair>();
		executeParams.add(new BasicNameValuePair("sldw", "440306000000"));
		executeParams.add(new BasicNameValuePair("beginDate", "2017-02-18"));
		executeParams.add(new BasicNameValuePair("type", "NEW"));
		executeParams.add(new BasicNameValuePair("days", "0"));
		Map<String,Object> executeResponseMap = doHttpPost(executeUrl, executeParams, cookie, null);
		CloseableHttpResponse executeResponse = (CloseableHttpResponse)executeResponseMap.get("response");
		String resultHtml = EntityUtils.toString(executeResponse.getEntity());
		
		System.out.println("宝安出入境结果：");
		JsonArray jsonArray = new JsonParser().parse(resultHtml).getAsJsonArray();
		for(int i=0;i<7;i++){
			JsonElement ele = jsonArray.get(i);
			JsonArray eleJsonArray = ele.getAsJsonArray();
			if("2017-02-18".equals(eleJsonArray.get(1).getAsString())){
				int count = eleJsonArray.get(2).getAsInt();
				if(count>0){
					System.out.println(eleJsonArray.get(0).getAsString()+" 有了。。。。");
					JOptionPane.showMessageDialog(null, "alert", "alert", JOptionPane.DEFAULT_OPTION); 
					timer.cancel();
					break;
				}else{
					System.out.println(eleJsonArray.get(0).getAsString()+" 没有");
				}
			}
		}
		/*executeParams.clear();
		executeParams.add(new BasicNameValuePair("sldw", "440305000000"));
		executeParams.add(new BasicNameValuePair("beginDate", "2017-02-18"));
		executeParams.add(new BasicNameValuePair("type", "NEW"));
		executeParams.add(new BasicNameValuePair("days", "0"));
		executeResponseMap.clear();
		executeResponseMap = doHttpPost(executeUrl, executeParams, cookie, null);
		executeResponse = (CloseableHttpResponse)executeResponseMap.get("response");
		resultHtml = EntityUtils.toString(executeResponse.getEntity());
		System.out.println("南山出入境结果：");
		jsonArray = new JsonParser().parse(resultHtml).getAsJsonArray();
		for(int i=0;i<7;i++){
			JsonElement ele = jsonArray.get(i);
			JsonArray eleJsonArray = ele.getAsJsonArray();
			if("2017-02-18".equals(eleJsonArray.get(1).getAsString())){
				int count = eleJsonArray.get(2).getAsInt();
				if(count>0){
					System.out.println(eleJsonArray.get(0).getAsString()+" 有了。。。。");
					break;
				}else{
					System.out.println(eleJsonArray.get(0).getAsString()+" 没有");
				}
			}
		}*/
	}
}