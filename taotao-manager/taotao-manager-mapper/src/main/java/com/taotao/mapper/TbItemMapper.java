package com.taotao.mapper;

import java.util.List;

import com.taotao.pojo.TbItem;

public interface TbItemMapper {

	TbItem getItemById(Long itemId);
	
	List<TbItem> getItemList();
	
	int createItem(TbItem tbItem);
}
