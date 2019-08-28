package com.taotao.httpclient;

import java.util.ArrayList;
import java.util.List;

import org.apache.http.HttpEntity;
import org.apache.http.NameValuePair;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.client.utils.URIBuilder;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.util.EntityUtils;
import org.junit.Test;

public class HttpClientTest {

	/**
	 * httpclient 执行 get 请求
	 * @throws Exception
	 */
	@Test
	public void doGet() throws Exception{
		// 创建一个 httpclient对象
		CloseableHttpClient httpClient = HttpClients.createDefault();
		
		// 创建一个GET对象
		HttpGet get = new HttpGet("http://www.baidu.com");
		
		//执行请求
		CloseableHttpResponse response = httpClient.execute(get);
		
		//取响应的结果
		int statusCode = response.getStatusLine().getStatusCode();
		System.out.println("状态码: " + statusCode);
		HttpEntity entity = response.getEntity();
		String string = EntityUtils.toString(entity, "utf-8");
		System.out.println(string);
		
		//关闭 httpclient
		response.close();
		httpClient.close();
	}
	
	/**
	 * httpclient 执行 get 请求带参数
	 * @throws Exception
	 */
	@Test
	public void doGetWithParam() throws Exception{
		// 创建一个 httpclient对象
		CloseableHttpClient httpClient = HttpClients.createDefault();
		
		// 创建一个uri对象
		URIBuilder uriBuilder = new URIBuilder("http://www.sogou.com/web");
		uriBuilder.addParameter("query", "香港");
		HttpGet get = new HttpGet(uriBuilder.build());
		
		//执行请求
		CloseableHttpResponse response = httpClient.execute(get);
		
		//取响应的结果
		int statusCode = response.getStatusLine().getStatusCode();
		System.out.println("状态码: " + statusCode);
		HttpEntity entity = response.getEntity();
		String string = EntityUtils.toString(entity, "utf-8");
		System.out.println(string);
		
		//关闭 httpclient
		response.close();
		httpClient.close();
	}
	
	/**
	 * httpclient 执行 post 请求
	 * @throws Exception
	 */
	@Test
	public void doPost() throws Exception{
		// 创建一个 httpclient对象
		CloseableHttpClient httpClient = HttpClients.createDefault();
		
		// 创建一个GET对象
		HttpPost post = new HttpPost("http://localhost:8082/httpclient/post.html");
		
		//执行请求
		CloseableHttpResponse response = httpClient.execute(post);
		
		//取响应的结果
		int statusCode = response.getStatusLine().getStatusCode();
		System.out.println("状态码: " + statusCode);
		String string = EntityUtils.toString(response.getEntity());
		System.out.println(string);
		
		//关闭 httpclient
		response.close();
		httpClient.close();
	}
	
	/**
	 * httpclient 执行 post 请求 带参数
	 * @throws Exception
	 */
	@Test
	public void doPostWithParam() throws Exception{
		// 创建一个 httpclient对象
		CloseableHttpClient httpClient = HttpClients.createDefault();
		
		// 创建一个post对象
		HttpPost post = new HttpPost("http://localhost:8082/httpclient/postwithparam.html");
		
		//创建一个Entity， 模拟一个表单
		List<NameValuePair> kvList = new ArrayList<>();
		kvList.add(new BasicNameValuePair("username", "zhangsan"));  //有中文乱码问题
		kvList.add(new BasicNameValuePair("password", "123456"));
		
		StringEntity entity = new UrlEncodedFormEntity(kvList);
		
		post.setEntity(entity);
		//执行请求
		CloseableHttpResponse response = httpClient.execute(post);
		
		//取响应的结果
		int statusCode = response.getStatusLine().getStatusCode();
		System.out.println("状态码: " + statusCode);
		String string = EntityUtils.toString(response.getEntity());
		System.out.println(string);
		
		//关闭 httpclient
		response.close();
		httpClient.close();
	}
}
