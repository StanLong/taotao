package com.taotao.rest.service;

import com.taotao.common.pojo.TaotaoResult;

public interface ItemService {

	TaotaoResult getItemBaseInfo(long itemId);
	
	TaotaoResult getItemDescBaseInfo(long itemId);
	
	TaotaoResult getItemParam(Long itemId);
}
