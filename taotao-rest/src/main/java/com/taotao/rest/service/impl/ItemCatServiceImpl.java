package com.taotao.rest.service.impl;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.taotao.mapper.TbItemCatMapper;
import com.taotao.pojo.TbItemCat;
import com.taotao.rest.pojo.CatNode;
import com.taotao.rest.pojo.CatResult;
import com.taotao.rest.service.ItemCatService;

/**
 * 商品分类服务
 * @author 矢量
 *
 * 2019年8月24日
 */
@Service
public class ItemCatServiceImpl implements ItemCatService {

	@Autowired
	private TbItemCatMapper itemCatMapper;
	
	@Override
	public CatResult getItemCatList() {
		CatResult catResult = new CatResult();
		catResult.setData(getCatList(0));
		
		return null;
	}
	
	/**
	 * 查询分类列表方法
	 * @param parentId
	 * @return
	 */
	private List<?> getCatList(long parentId){
		List<TbItemCat> catList = itemCatMapper.getCatList(parentId);
		List resultList = new ArrayList<>();
		for (TbItemCat itemCat : catList) {
			//父节点
			if(itemCat.getIsParent()){
				CatNode catNode = new CatNode();
				if(parentId == 0){
					catNode.setName("<a href='/products/"+ itemCat.getId() +".html'>" + itemCat.getName() +"</a>");
				}else{
					catNode.setName(itemCat.getName());
				}
				catNode.setUrl("/products/"+ itemCat.getId() +".html");
				catNode.setItem(getCatList(itemCat.getId()));
				resultList.add(catNode);
			//叶子节点
			}else{
				resultList.add("/products/"+ itemCat.getId() +".html|" + itemCat.getName());
			}
		}
		return resultList;
	}

}
