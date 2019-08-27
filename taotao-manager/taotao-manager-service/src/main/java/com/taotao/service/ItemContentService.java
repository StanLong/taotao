package com.taotao.service;

import com.taotao.common.pojo.EasyUIDataGridResult;
import com.taotao.common.pojo.TaotaoResult;
import com.taotao.pojo.TbContent;

public interface ItemContentService {

	EasyUIDataGridResult getContentList(int page, int rows, long categoryId);
	
	TaotaoResult insertContent(TbContent content);
}
