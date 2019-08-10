package com.taotao.service;

import com.taotao.common.pojo.EasyUIDataGridResult;
import com.taotao.common.pojo.TaotaoResult;
import com.taotao.pojo.TbItemParam;

public interface ItemParamService {

	TaotaoResult getItemParamByCid(long cid);
	
	TaotaoResult createItemParam(TbItemParam tbItemParam);
	
	EasyUIDataGridResult getItemParamList(int page, int rows);
}
