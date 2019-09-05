package com.taotao.rest.solrj;

import org.apache.solr.client.solrj.SolrQuery;
import org.apache.solr.client.solrj.SolrServer;
import org.apache.solr.client.solrj.impl.HttpSolrServer;
import org.apache.solr.client.solrj.response.QueryResponse;
import org.apache.solr.common.SolrDocument;
import org.apache.solr.common.SolrDocumentList;
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
	
	/**
	 * 查询
	 * @throws Exception
	 */
	@Test
	public void queryDocument() throws Exception{
		SolrServer solrServer = new HttpSolrServer("http://192.168.235.20:8080/solr");
		// 创建一个查询对象
		SolrQuery solrQuery = new SolrQuery();
		
		//设置查询条件
		solrQuery.setQuery("*:*");
		
		//设置分页
		solrQuery.setStart(20);
		solrQuery.setRows(50);
		
		// 执行查询
		QueryResponse response = solrServer.query(solrQuery);
		
		//取得查询结果
		SolrDocumentList solrDocumentList = response.getResults();
		System.out.println( "共查询到记录：" + solrDocumentList.getNumFound());
		for (SolrDocument solrDocument : solrDocumentList) {
			System.out.println(solrDocument.get("id"));
			System.out.println(solrDocument.get("item_title"));
		}
	}
}
