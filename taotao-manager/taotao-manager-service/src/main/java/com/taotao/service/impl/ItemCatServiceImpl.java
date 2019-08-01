package com.taotao.service.impl;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.taotao.common.pojo.EasyUITreeNode;
import com.taotao.mapper.TbItemCatMapper;
import com.taotao.pojo.TbItemCat;
import com.taotao.service.ItemCatService;

/**
 * 商品分类管理
 * @author 矢量
 *
 * 2019年8月1日
 */
@Service
public class ItemCatServiceImpl implements ItemCatService {

	@Autowired
	private TbItemCatMapper TbItemCatMapper;
	
	@Override
	public List<EasyUITreeNode> getCatList(long parentId) {
		List<TbItemCat> catList = TbItemCatMapper.getCatList(parentId);
		List<EasyUITreeNode> resultList = new ArrayList<>();
		
		//把列表转换成 EasyUITreeNodeList
		for(TbItemCat tbItemCat : catList){
			EasyUITreeNode treeNode = new EasyUITreeNode();
			treeNode.setId(tbItemCat.getId());
			treeNode.setText(tbItemCat.getName());
			treeNode.setState(tbItemCat.getIsParent()?"closed":"open");
			resultList.add(treeNode);
		}
		return resultList;
	}

}
