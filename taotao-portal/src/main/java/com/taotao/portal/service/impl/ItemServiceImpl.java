package com.taotao.portal.service.impl;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import com.taotao.common.pojo.TaotaoResult;
import com.taotao.common.utils.HttpClientUtil;
import com.taotao.portal.pojo.ItemInfo;
import com.taotao.portal.service.ItemService;

/**
 * 
 * @author 矢量
 *
 * 2019年9月8日
 */
@Service
public class ItemServiceImpl implements ItemService {

	@Value("${REST_BASE_URL}")
	private String REST_BASE_URL;
	
	@Value("${ITEM_INFO_URL}")
	private String ITEM_INFO_URL;
	
	
	@Override
	public ItemInfo getItemById(Long itemId) {
		try {
			String json = HttpClientUtil.doGet(REST_BASE_URL + ITEM_INFO_URL + itemId);
			if(!StringUtils.isBlank(json)){
				TaotaoResult result = TaotaoResult.formatToPojo(json, ItemInfo.class); // 这里报空指针了，后面继续定位
				if(result.getStatus() == 200){
					ItemInfo itemInfo = (ItemInfo) result.getData();
					return itemInfo;
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}

		return null;
	}

}
