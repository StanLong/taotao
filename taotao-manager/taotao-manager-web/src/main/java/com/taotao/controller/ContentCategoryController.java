package com.taotao.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.taotao.common.pojo.EasyUITreeNode;
import com.taotao.common.pojo.TaotaoResult;
import com.taotao.service.ContentCategoryService;

/**
 * 
 * @author 矢量
 *
 * 2019年8月25日
 */
@Controller
@RequestMapping("/content/category")
public class ContentCategoryController {

	@Autowired
	private ContentCategoryService categoryService;
	
	@RequestMapping("/list")
	@ResponseBody
	public List<EasyUITreeNode> getCategoryList(@RequestParam(value="id", defaultValue="0") long parentId){
		List<EasyUITreeNode> categoryList = categoryService.getCategoryList(parentId);
		return categoryList;
	}
	
	@RequestMapping("/create")
	@ResponseBody
	public TaotaoResult insertContentCategory(Long parentId, String name){
		return categoryService.insertContentCategory(parentId, name);
	}
	
	@RequestMapping("/delete")
	@ResponseBody
	public TaotaoResult deleteContentCategory(Long id){
		TaotaoResult reslut = categoryService.deleteContentCategory(id);
		return reslut;
	}
}
