package com.taotao.rest.service.impl;

import java.util.List;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import com.taotao.common.pojo.TaotaoResult;
import com.taotao.common.utils.JsonUtils;
import com.taotao.mapper.TbItemDescMapper;
import com.taotao.mapper.TbItemMapper;
import com.taotao.mapper.TbItemParamItemMapper;
import com.taotao.pojo.TbItem;
import com.taotao.pojo.TbItemDesc;
import com.taotao.pojo.TbItemParamItem;
import com.taotao.rest.dao.JedisClient;
import com.taotao.rest.service.ItemService;

/**
 * 
 * @author 矢量
 *
 * 2019年9月7日
 */
@Service
public class ItemServiceImpl implements ItemService{
	
	@Autowired
	private TbItemMapper itemMapper;
	
	@Autowired
	private TbItemDescMapper itemDescMapper;
	
	@Autowired
	private TbItemParamItemMapper tbItemParamItemMapper;
	
	@Autowired
	private JedisClient jedisClient;
	
	@Value("${REDIS_ITEM_KEY}")
	private String REDIS_ITEM_KEY;
	
	@Value("${REDIS_ITEM_EXPIRE}")
	private Integer REDIS_ITEM_EXPIRE;

	@Override
	public TaotaoResult getItemBaseInfo(long itemId) {
		// 添加缓存逻辑
		try {
			String json = jedisClient.get(REDIS_ITEM_KEY + ":" + itemId + ":base");
			if(!StringUtils.isBlank(json)){
				// 把json转换成java 对象
				TbItem item = JsonUtils.jsonToPojo(json, TbItem.class);
				return TaotaoResult.ok(item);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}

		// 根据商品id查询商品信息
		TbItem tbItem = itemMapper.getItemById(itemId);
		try {
			// 将商品信息写入缓存
			jedisClient.set(REDIS_ITEM_KEY + ":" + itemId + ":base", JsonUtils.objectToJson(tbItem));
			//设置key的有效期
			jedisClient.expire(REDIS_ITEM_KEY + ":" + itemId + ":base", REDIS_ITEM_EXPIRE);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return TaotaoResult.ok(tbItem);
	}

	/**
	 * 查询商品描述信息
	 */
	@Override
	public TaotaoResult getItemDescBaseInfo(long itemId) {
		
		// 添加缓存逻辑
		try {
			String json = jedisClient.get(REDIS_ITEM_KEY + ":" + itemId + ":desc");
			if(!StringUtils.isBlank(json)){
				// 把json转换成java 对象
				TbItemDesc tbItemDesc = JsonUtils.jsonToPojo(json, TbItemDesc.class);
				return TaotaoResult.ok(tbItemDesc);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}

		TbItemDesc itemDesc = itemDescMapper.getItemDesc(itemId);
		
		try {
			// 将商品描述信息写入缓存
			jedisClient.set(REDIS_ITEM_KEY + ":" + itemId + ":desc", JsonUtils.objectToJson(itemDesc));
			//设置key的有效期
			jedisClient.expire(REDIS_ITEM_KEY + ":" + itemId + ":desc", REDIS_ITEM_EXPIRE);
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return TaotaoResult.ok(itemDesc);
	}

	@Override
	public TaotaoResult getItemParam(Long itemId) {
		
		// 添加缓存逻辑
		try {
			String json = jedisClient.get(REDIS_ITEM_KEY + ":" + itemId + ":param");
			if(!StringUtils.isBlank(json)){
				// 把json转换成java 对象
				TbItemParamItem tbItemParamItem = JsonUtils.jsonToPojo(json, TbItemParamItem.class);
				return TaotaoResult.ok(tbItemParamItem);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		List<TbItemParamItem> itemParamList = tbItemParamItemMapper.getItemParamItemByItemId(itemId);
		if(itemParamList != null && itemParamList.size() > 0){
			TbItemParamItem paramItem = itemParamList.get(0);
			
			try {
				// 将商品描述信息写入缓存
				jedisClient.set(REDIS_ITEM_KEY + ":" + itemId + ":param", JsonUtils.objectToJson(paramItem));
				//设置key的有效期
				jedisClient.expire(REDIS_ITEM_KEY + ":" + itemId + ":param", REDIS_ITEM_EXPIRE);
			} catch (Exception e) {
				e.printStackTrace();
			}
			
			return TaotaoResult.ok(paramItem);
		}
		return TaotaoResult.build(400, "无此商品规格信息！");
	}
}
