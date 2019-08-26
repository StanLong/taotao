package com.taotao.service.impl;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.taotao.common.pojo.EasyUITreeNode;
import com.taotao.common.pojo.TaotaoResult;
import com.taotao.mapper.TbContentCategoryMapper;
import com.taotao.pojo.TbContentCategory;
import com.taotao.service.ContentCategoryService;

@Service
public class ContentCategoryServiceImpl implements ContentCategoryService {
	
	@Autowired
	private TbContentCategoryMapper contentCategoryMapper; 

	@Override
	public List<EasyUITreeNode> getCategoryList(long parentId) {
		List<TbContentCategory> categoryList = contentCategoryMapper.getCategoryListByParentId(parentId);
		List<EasyUITreeNode> resultList = new ArrayList<EasyUITreeNode>();
		for (TbContentCategory tbContentCategory : categoryList) {
			EasyUITreeNode node = new EasyUITreeNode();
			node.setId(tbContentCategory.getId());
			node.setText(tbContentCategory.getName());
			node.setState(tbContentCategory.getIsParent()?"closed":"open");
			resultList.add(node);
		}
		return resultList;
	}

	@Override
	public TaotaoResult insertContentCategory(long parentId, String name) {
		TbContentCategory contentCategory = new TbContentCategory();
		contentCategory.setName(name);
		contentCategory.setParentId(parentId);
		contentCategory.setIsParent(false);
		contentCategory.setStatus(1);
		contentCategory.setSortOrder(1);
		contentCategory.setCreated(new Date());
		contentCategory.setUpdated(new Date());
		contentCategoryMapper.insertContentCategory(contentCategory);
		
		//查看父节点的 isParent 列是否为 true， 如果不为 true 改成 true
		TbContentCategory parentCat = contentCategoryMapper.getByPrimaryKey(parentId);
		if(!parentCat.getIsParent()){
			parentCat.setIsParent(true);
			contentCategoryMapper.updateContentCategory(parentCat);
		}
		return TaotaoResult.ok(contentCategory);
	}
	
	@Override
	public TaotaoResult deleteContentCategory(long id){
		TbContentCategory contentCategory = new TbContentCategory();
		contentCategory.setId(id);
		TbContentCategory tempCat = contentCategoryMapper.getContentCategory(contentCategory);
		
		// 删除
		contentCategoryMapper.deleteContentCategory(contentCategory);
		
		TbContentCategory parentCat = new TbContentCategory();
		parentCat = contentCategoryMapper.getByPrimaryKey(tempCat.getParentId());
		
		//判断  parentId 下是否还有子节点，如果没有子节点，需要把 parentId对应的记录的 isParent 改成 false
		List<TbContentCategory> categoryList = contentCategoryMapper.getCategoryListByParentId(parentCat.getId());
		if(categoryList.size() == 0){
			parentCat.setIsParent(false);
			contentCategoryMapper.updateContentCategory(parentCat);
		}
		return TaotaoResult.ok();
	}

	@Override
	public TaotaoResult updateContentCategory(long id, String name) {
		TbContentCategory contentCategory = new TbContentCategory();
		contentCategory.setId(id);
		contentCategory.setName(name);
		contentCategoryMapper.updateContentCategory(contentCategory);
		return TaotaoResult.ok();
	}

}
