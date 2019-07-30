package com.taotao.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.taotao.pojo.TbItem;
import com.taotao.service.ItemService;

/**
 * 
 * @author Administrator
 *
 */
@Controller
public class ItemController {

	@Autowired
	private ItemService itemService;
	
	@RequestMapping("/item/{itemId}")
	@ResponseBody
	//@PathVariable URL 模板映射 ，把url路径里的itemId映射到 value="itemId"，参数的名称要一致
	public TbItem getItemById(@PathVariable(value="itemId") Long itemId){
		TbItem tbItem = itemService.getItemById(itemId);
		return tbItem;
	}
	
}
