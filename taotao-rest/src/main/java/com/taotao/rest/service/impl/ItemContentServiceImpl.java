package com.taotao.rest.service.impl;

import java.util.List;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import com.taotao.common.utils.JsonUtils;
import com.taotao.mapper.TbContentMapper;
import com.taotao.pojo.TbContent;
import com.taotao.rest.dao.JedisClient;
import com.taotao.rest.service.ItemContentService;

@Service
public class ItemContentServiceImpl implements ItemContentService{

	@Autowired
	private TbContentMapper contentMapper;
	
	@Autowired
	private JedisClient jedisClient;
	
	@Value("${INDEX_CONTENT_REDIS_KEY}")
	private String INDEX_CONTENT_REDIS_KEY;
	
	@Override
	public List<TbContent> getContentList(long categoryId) {
		// 从缓存中取内容
		try{
			String result = jedisClient.hget(INDEX_CONTENT_REDIS_KEY, categoryId + "");
			if(!StringUtils.isBlank(result)){
				// 把字符串转换成list
				List<TbContent> resultList = JsonUtils.jsonToList(result, TbContent.class);
				return resultList;
			}
		}catch(Exception e){
			e.printStackTrace();
		}
		TbContent content = new TbContent();
		content.setCategoryId(categoryId);
		
		// 执行查询
		List<TbContent> contentList = contentMapper.getContentList(content);
		
		// 向缓存中添加内容
		try {
			// 需要把 contentList 转换成字符串
			String cacheString = JsonUtils.objectToJson(contentList);
			jedisClient.hset(INDEX_CONTENT_REDIS_KEY, categoryId + "", cacheString);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return contentList;
	}

}
