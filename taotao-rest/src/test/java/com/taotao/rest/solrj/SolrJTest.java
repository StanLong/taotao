package com.taotao.rest.solrj;

import org.apache.solr.client.solrj.SolrServer;
import org.apache.solr.client.solrj.impl.HttpSolrServer;
import org.apache.solr.common.SolrInputDocument;
import org.junit.Test;

/**
 * 
 * @author 矢量
 *
 * 2019年9月4日
 */
public class SolrJTest {

	/**
	 * 添加文档/修改文档
	 * @throws Exception
	 */
	@Test
	public void addDocument() throws Exception{
		// 创建连接
		SolrServer solrServer = new HttpSolrServer("http://192.168.235.20:8080/solr");

		// 创建一个文档
		SolrInputDocument document = new SolrInputDocument();
		document.addField("id", "test001");
		document.addField("item_title", "小米手机");
		document.addField("item_price", 123456);
		
		// 把文档写入索引库
		solrServer.add(document);
		
		// 提交
		solrServer.commit();
	}
	
	/**
	 * 测试删除
	 * @throws Exception
	 */
	@Test
	public void delDocument() throws Exception{
		// 创建连接
		SolrServer solrServer = new HttpSolrServer("http://192.168.235.20:8080/solr");
		
		// 删除所有
		solrServer.deleteByQuery("*:*");
		
		// 提交
		solrServer.commit();
	}
}
