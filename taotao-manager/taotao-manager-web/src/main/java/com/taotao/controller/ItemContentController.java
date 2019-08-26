package com.taotao.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.taotao.common.pojo.EasyUIDataGridResult;
import com.taotao.service.ItemContentService;

@Controller
@RequestMapping("/content")
public class ItemContentController {
	
	@Autowired
	private ItemContentService itemContentService;

	/**
	 * 展示内容
	 * @param page
	 * @param rows
	 * @return
	 */
	@RequestMapping("/query/list")
	@ResponseBody
	public EasyUIDataGridResult getItemList(Integer page, Integer rows, Long categoryId){
		return itemContentService.getContentList(page, rows, categoryId);
	}
}
