package com.taotao.mapper;

import java.util.List;

import com.taotao.pojo.TbItemParamItem;

public interface TbItemParamItemMapper {
	
	/**
	 * 保存商品规格参数
	 * @param itemParamItem
	 * @return
	 */
	int insertItemParamItem(TbItemParamItem itemParamItem);
	
	List<TbItemParamItem> getItemParamItemByItemId(Long itemId);
}