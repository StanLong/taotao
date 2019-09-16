package com.taotao.portal.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import com.taotao.portal.pojo.ItemInfo;
import com.taotao.portal.service.impl.ItemServiceImpl;

@Controller
public class ItemController {

	@Autowired
	private ItemServiceImpl itemService;
	
	@RequestMapping("/item/{itemId}")
	public String getItemById(@PathVariable Long itemId, Model model){
		ItemInfo itemInfo = itemService.getItemById(itemId);
		model.addAttribute("item", itemInfo);
		return "item";
	}
}
