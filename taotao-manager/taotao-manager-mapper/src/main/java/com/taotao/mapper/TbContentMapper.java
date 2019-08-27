package com.taotao.mapper;

import java.util.List;

import com.taotao.pojo.TbContent;

public interface TbContentMapper {
	
	List<TbContent> getContentList(TbContent content);
	
	void insertContent(TbContent content);
}