package com.taotao.mapper;

import com.taotao.pojo.TbItemDesc;

public interface TbItemDescMapper {
	
	int createTbItemDesc(TbItemDesc tbItemDesc);
	
	TbItemDesc getItemDesc(Long itemId);
}