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
import com.taotao.mapper.TbItemDescMapper;
import com.taotao.mapper.TbItemMapper;
import com.taotao.mapper.TbItemParamItemMapper;
import com.taotao.mapper.TbItemParamMapper;
import com.taotao.pojo.TbItem;
import com.taotao.pojo.TbItemDesc;
import com.taotao.pojo.TbItemParam;
import com.taotao.pojo.TbItemParamItem;
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
	
	@Autowired
	private TbItemDescMapper itemDescMapper;
	
	@Autowired
	private TbItemParamItemMapper itemParamItemMapper;  
	
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
	 * @throws Exception 
	 */
	@Override
	public TaotaoResult createItem(TbItem tbItem, String desc, String itemParam) throws Exception {
		// 生成商品id
		Long itemId = IDUtils.genItemId();
		tbItem.setId(itemId);
		// 商品状态 1-正常 2-下架  3- 删除
		tbItem.setStatus((byte)1);
		tbItem.setCreated(new Date());
		tbItem.setUpdated(new Date());
		itemMapper.createItem(tbItem);
		//添加商品描述信息
		TaotaoResult result = createItemDesc(itemId, desc);
		if(result.getStatus() != 200){
			throw new Exception();
		}
		//添加规格参数
		result = insertItemParamItem(itemId, itemParam);
		if(result.getStatus() != 200){
			throw new Exception();
		}
		return TaotaoResult.ok();

	}

	/**
	 * 添加商品描述
	 * @param itemId
	 * @param itemDesc
	 * @return
	 */
	public TaotaoResult createItemDesc(Long itemId, String itemDesc){
		TbItemDesc tbItemDesc = new TbItemDesc();
		tbItemDesc.setItemId(itemId);
		tbItemDesc.setItemDesc(itemDesc);
		tbItemDesc.setUpdated(new Date());
		tbItemDesc.setCreated(new Date());
		itemDescMapper.createTbItemDesc(tbItemDesc);
		return TaotaoResult.ok();
	}
	
	/**
	 * 添加规格参数
	 * @param itemid
	 * @param itemParam
	 * @return
	 */
	public TaotaoResult insertItemParamItem(long itemId, String itemParam){
		TbItemParamItem itemParamItem = new TbItemParamItem();
		itemParamItem.setItemId(itemId);
		itemParamItem.setParamData(itemParam);
		itemParamItem.setCreated(new Date());
		itemParamItem.setUpdated(new Date());
		itemParamItemMapper.insertItemParamItem(itemParamItem);
		return TaotaoResult.ok();
	}

}
