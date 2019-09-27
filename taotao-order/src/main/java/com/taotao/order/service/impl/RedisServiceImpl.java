package com.taotao.order.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import com.taotao.common.pojo.TaotaoResult;
import com.taotao.common.utils.ExceptionUtil;
import com.taotao.order.dao.JedisClient;
import com.taotao.order.service.RedisService;

/**
 * 缓存同步 Service
 * @author 矢量
 *
 * 2019年9月1日
 */
@Service
public class RedisServiceImpl implements RedisService{
	
	@Autowired
	private JedisClient jedisClient;
	
	@Value("${INDEX_CONTENT_REDIS_KEY}")
	private String INDEX_CONTENT_REDIS_KEY;

	@Override
	public TaotaoResult syscContent(long categoryId) {
		try{
			jedisClient.hdel(INDEX_CONTENT_REDIS_KEY, categoryId + "");
		}catch(Exception e){
			e.printStackTrace();
			return TaotaoResult.build(500, ExceptionUtil.getStackTrace(e));
		}
		return TaotaoResult.ok();
	}

}
