package com.taotao.service;

import java.util.List;

import com.taotao.common.pojo.EasyUITreeNode;

/**
 * 
 * @author 矢量
 *
 * 2019年8月25日
 */
public interface ContentCategoryService {

	List<EasyUITreeNode> getCategoryList(long parentId);
}
