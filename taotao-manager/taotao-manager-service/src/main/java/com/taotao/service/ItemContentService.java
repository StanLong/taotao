package com.taotao.service;

import com.taotao.common.pojo.EasyUIDataGridResult;

public interface ItemContentService {

	EasyUIDataGridResult getContentList(int page, int rows, long categoryId);
}
