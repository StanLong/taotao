package com.taotao.service.impl;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.taotao.common.pojo.EasyUITreeNode;
import com.taotao.mapper.TbContentCategoryMapper;
import com.taotao.pojo.TbContentCategory;
import com.taotao.service.ContentCategoryService;

@Service
public class ContentCategoryServiceImpl implements ContentCategoryService {
	
	@Autowired
	private TbContentCategoryMapper contentCategoryMapper; 

	@Override
	public List<EasyUITreeNode> getCategoryList(long parentId) {
		List<TbContentCategory> categoryList = contentCategoryMapper.getCategoryList(parentId);
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

}
