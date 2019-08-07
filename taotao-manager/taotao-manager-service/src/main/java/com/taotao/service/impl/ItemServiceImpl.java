package com.taotao.service.impl;

import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.taotao.common.pojo.EasyUIDataGridResult;
import com.taotao.common.pojo.TaotaoResult;
import com.taotao.common.utils.IDUtils;
import com.taotao.mapper.TbItemMapper;
import com.taotao.pojo.TbItem;
import com.taotao.service.ItemService;

/**
 * 商品管理Service
 * @author Administrator
 *
 */
@Service
public class ItemServiceImpl implements ItemService {

	
	@Autowired
	private TbItemMapper itemMapper;
	
	@Override
	public TbItem getItemById(long itemId) {
		TbItem item = itemMapper.getItemById(itemId);
		return item;
	}

	/**
	 * 查询商品列表
	 */
	@Override
	public EasyUIDataGridResult getItemList(int page, int rows) {
		
		//分页处理
		PageHelper.startPage(page, rows);
		List<TbItem> itemList = itemMapper.getItemList();
		EasyUIDataGridResult result = new EasyUIDataGridResult();
		result.setRows(itemList);
		
		//取记录条数
		PageInfo<TbItem> pageInfo = new PageInfo<>(itemList);
		result.setTotal(pageInfo.getTotal());
		return result;
	}

	/**
	 * 保存商品
	 */
	@Override
	public TaotaoResult createItem(TbItem tbItem) {
		// 生成商品id
		Long itemId = IDUtils.genItemId();
		tbItem.setId(itemId);
		// 商品状态 1-正常 2-下架  3- 删除
		tbItem.setStatus((byte)1);
		tbItem.setCreated(new Date());
		tbItem.setUpdated(new Date());
		itemMapper.createItem(tbItem);
		return TaotaoResult.ok();
	}

	

}
