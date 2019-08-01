package com.taotao.mapper;

import java.util.List;

import com.taotao.pojo.TbItemCat;

public interface TbItemCatMapper {

	public List<TbItemCat> getCatList(long parentId);
}