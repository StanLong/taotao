package com.taotao.mapper;

import java.util.List;

import com.taotao.pojo.TbContentCategory;

public interface TbContentCategoryMapper {
	
	public TbContentCategory getByPrimaryKey(long id);
	
	public List<TbContentCategory> getCategoryListByParentId(long parentId);
	
	public void insertContentCategory(TbContentCategory contentCategory);
	
	public TbContentCategory getContentCategory(TbContentCategory contentCategory);
	
	public void updateContentCategory(TbContentCategory contentCategory);
	
	public void deleteContentCategory(TbContentCategory contentCategory);
}