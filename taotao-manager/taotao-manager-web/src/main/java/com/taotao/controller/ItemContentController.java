package com.taotao.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.taotao.common.pojo.EasyUIDataGridResult;
import com.taotao.common.pojo.TaotaoResult;
import com.taotao.pojo.TbContent;
import com.taotao.service.ItemContentService;

/**
 * 内容管理
 * @author 矢量
 *
 * 2019年8月27日
 */
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
	
	@RequestMapping("/save")
	@ResponseBody
	public TaotaoResult insertContent(TbContent content){
		return itemContentService.insertContent(content);
	}
}
