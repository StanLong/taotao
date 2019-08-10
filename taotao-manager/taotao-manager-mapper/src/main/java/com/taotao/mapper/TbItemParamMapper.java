package com.taotao.mapper;

import java.util.List;

import com.taotao.pojo.TbItemParam;

public interface TbItemParamMapper {

	List<TbItemParam> getItemParamByCid(Long cid);

	int createItemParam(TbItemParam tbItemParam);
	
	List<TbItemParam> getItemParamList();

}