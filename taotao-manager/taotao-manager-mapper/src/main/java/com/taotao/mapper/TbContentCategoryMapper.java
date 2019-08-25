package com.taotao.mapper;

import java.util.List;

import com.taotao.pojo.TbContentCategory;

public interface TbContentCategoryMapper {
	
	public List<TbContentCategory> getCategoryList(long parentId);
}