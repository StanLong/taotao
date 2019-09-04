package com.taotao.service.impl;

import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.taotao.common.pojo.EasyUIDataGridResult;
import com.taotao.common.pojo.TaotaoResult;
import com.taotao.common.utils.HttpClientUtil;
import com.taotao.mapper.TbContentMapper;
import com.taotao.pojo.TbContent;
import com.taotao.service.ItemContentService;

@Service
public class ItemContentServiceImpl implements ItemContentService{

	@Autowired
	private TbContentMapper contentMapper;
	
	@Value("${REST_BASE_URL}")
	private String REST_BASE_URL;
	
	@Value("${REST_CONTENT_SYNC_URL}")
	private String REST_CONTENT_SYNC_URL;
	

	@Override
	public EasyUIDataGridResult getContentList(int page, int rows, long categoryId) {
		TbContent content = new TbContent();
		content.setCategoryId(categoryId);
		//分页处理
		PageHelper.startPage(page, rows);
		List<TbContent> contentList = contentMapper.getContentList(content);
		EasyUIDataGridResult result = new EasyUIDataGridResult();
		result.setRows(contentList);
		
		//取记录条数
		PageInfo<TbContent> pageInfo = new PageInfo<>(contentList);
		result.setTotal(pageInfo.getTotal());
		return result;
	}

	/**
	 * 新增商品内容分类
	 */
	@Override
	public TaotaoResult insertContent(TbContent content) {
		content.setCreated(new Date());
		content.setUpdated(new Date());
		contentMapper.insertContent(content);
		
		try {
			//添加缓存同步服务
			HttpClientUtil.doGet(REST_BASE_URL + REST_CONTENT_SYNC_URL + content.getCategoryId());
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return TaotaoResult.ok();
	}
	
	

}
