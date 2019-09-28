package com.taotao.mapper;

import com.taotao.pojo.TbOrderItem;
import com.taotao.pojo.TbOrderItemExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface TbOrderItemMapper {
	
	void insertOrderItem(TbOrderItem orderItem);
}