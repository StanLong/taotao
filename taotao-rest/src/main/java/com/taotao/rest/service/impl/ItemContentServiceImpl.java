package com.taotao.rest.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.taotao.mapper.TbContentMapper;
import com.taotao.pojo.TbContent;
import com.taotao.rest.service.ItemContentService;

@Service
public class ItemContentServiceImpl implements ItemContentService{

	@Autowired
	private TbContentMapper contentMapper;
	
	@Override
	public List<TbContent> getContentList(long categoryId) {
		TbContent content = new TbContent();
		content.setCategoryId(categoryId);
		List<TbContent> contentList = contentMapper.getContentList(content);
		return contentList;
	}

}
