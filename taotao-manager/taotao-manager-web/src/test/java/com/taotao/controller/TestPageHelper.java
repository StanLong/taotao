package com.taotao.controller;

import java.util.List;

import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.taotao.mapper.TbItemMapper;
import com.taotao.pojo.TbItem;

public class TestPageHelper {

	@Test
	public void testPageHelper(){
		//创建一个Spring容器，从容器中获得Mapper的代理对象
		ApplicationContext applicationContext = new ClassPathXmlApplicationContext("classpath:spring/applicationContext-*");
		TbItemMapper itemMapper = applicationContext.getBean(TbItemMapper.class);
		PageHelper.startPage(1, 10);
		List<TbItem> itemList = itemMapper.getItemList();
		for(TbItem item : itemList){
			System.out.println(item.getTitle());
		}
		PageInfo<TbItem> pageInfo = new PageInfo<>(itemList);
		long total = pageInfo.getTotal();
		System.out.println("共有商品信息：" + total);
	}
}
