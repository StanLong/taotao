package com.taotao.mapper;

import java.util.List;

import com.taotao.pojo.TbItem;

public interface TbItemMapper {

	public TbItem getItemById(Long itemId);
	
	public List<TbItem> getItemList();
}
