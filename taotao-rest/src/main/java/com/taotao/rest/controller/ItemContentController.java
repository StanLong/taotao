package com.taotao.rest.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.taotao.common.pojo.TaotaoResult;
import com.taotao.common.utils.ExceptionUtil;
import com.taotao.pojo.TbContent;
import com.taotao.rest.service.ItemContentService;

/**
 * 内容管理 rest
 * @author 矢量
 *
 * 2019年8月27日
 */
@Controller
@RequestMapping("/content")
public class ItemContentController {

	@Autowired
	private ItemContentService itemContentService;
	
	@RequestMapping("/list/{categoryId}")
	@ResponseBody
	public TaotaoResult getContentList(@PathVariable Long categoryId){
		try{
			List<TbContent> contentList = itemContentService.getContentList(categoryId);
			return TaotaoResult.ok(contentList);
		}catch(Exception e){
			e.printStackTrace();
			return TaotaoResult.build(500, ExceptionUtil.getStackTrace(e));
		}
		
	}
}
