package com.taotao.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.taotao.common.pojo.EasyUIDataGridResult;
import com.taotao.mapper.TbContentMapper;
import com.taotao.pojo.TbContent;
import com.taotao.service.ItemContentService;

@Service
public class ItemContentServiceImpl implements ItemContentService{

	@Autowired
	private TbContentMapper contentMapper;

	@Override
	public EasyUIDataGridResult getContentList(int page, int rows, long categoryId) {
		TbContent content = new TbContent();
		content.setCategoryId(categoryId);
		//分页处理
		PageHelper.startPage(page, rows);
		List<TbContent> contentList = contentMapper.getContentList(content);
		EasyUIDataGridResult result = new EasyUIDataGridResult();
		result.setRows(contentList);
		
		//取记录条数
		PageInfo<TbContent> pageInfo = new PageInfo<>(contentList);
		result.setTotal(pageInfo.getTotal());
		return result;
	}
	
	

}
