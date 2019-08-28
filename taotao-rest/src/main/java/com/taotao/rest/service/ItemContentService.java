package com.taotao.rest.service;

import java.util.List;

import com.taotao.pojo.TbContent;

public interface ItemContentService {

	List<TbContent> getContentList(long categoryId);
}
