package com.taotao.service.impl;

import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.taotao.common.pojo.EasyUIDataGridResult;
import com.taotao.common.pojo.TaotaoResult;
import com.taotao.mapper.TbItemParamMapper;
import com.taotao.pojo.TbItemParam;
import com.taotao.service.ItemParamService;

/**
 * 商品規格
 * @author 矢量
 *
 * 2019年8月10日
 */
@Service
public class ItemParamServiceImpl implements ItemParamService{

	@Autowired
	private TbItemParamMapper itemParamMapper;
	
	@Override
	public TaotaoResult getItemParamByCid(long cid){
		List<TbItemParam> itemParamList = itemParamMapper.getItemParamByCid(cid);
		if (null != itemParamList && itemParamList.size() > 0){
			return TaotaoResult.ok(itemParamList.get(0));
		}
		return TaotaoResult.ok();
	}
	
	@Override
	public TaotaoResult createItemParam(TbItemParam tbItemParam){
		tbItemParam.setCreated(new Date());
		tbItemParam.setUpdated(new Date());
		itemParamMapper.createItemParam(tbItemParam);
		return TaotaoResult.ok();
	}

	@Override
	public EasyUIDataGridResult getItemParamList(int page, int rows) {
		//分页处理
		PageHelper.startPage(page, rows);
		List<TbItemParam> itemParamList = itemParamMapper.getItemParamList();
		EasyUIDataGridResult result = new EasyUIDataGridResult();
		result.setRows(itemParamList);
		
		//取记录条数
		PageInfo<TbItemParam> pageInfo = new PageInfo<>(itemParamList);
		result.setTotal(pageInfo.getTotal());
		return result;
	}
	
}
