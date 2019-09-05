package com.taotao.search.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.taotao.common.pojo.TaotaoResult;
import com.taotao.search.service.ItemService;

/**
 * 导入商品数据到索引库
 * @author 矢量
 *
 * 2019年9月4日
 */
@Controller
@RequestMapping("/manager")
public class ItemController {

	@Autowired
	private ItemService itemService;
	
	/**
	 * 把es中的数据导入到mysql
	 * @return
	 */
	@RequestMapping("/importAll")
	@ResponseBody
	public TaotaoResult importAllItems(){
		return itemService.importAllItems();
	}
	
	
	
}
