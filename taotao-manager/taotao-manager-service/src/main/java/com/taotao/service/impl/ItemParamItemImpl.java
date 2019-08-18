package com.taotao.service.impl;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.taotao.common.utils.JsonUtils;
import com.taotao.mapper.TbItemParamItemMapper;
import com.taotao.pojo.TbItemParamItem;
import com.taotao.service.ItemParamItemService;

@Service
public class ItemParamItemImpl implements ItemParamItemService{

	@Autowired
	TbItemParamItemMapper itemParamItemMapper;
	
	/**
	 * 展示商品规格参数
	 */
	@Override
	public String getItemParamItemByItemId(Long itemId) {
		List<TbItemParamItem> itemParamItemList = itemParamItemMapper.getItemParamItemByItemId(itemId);
		if(null == itemParamItemList || itemParamItemList.size()==0){
			return "";
		}
		TbItemParamItem itemParamItem = itemParamItemList.get(0);
		String paramData = itemParamItem.getParamData();
		
		//生成 html 信息
		//把json数据转换成java对象
		List<Map> paramList = JsonUtils.jsonToList(paramData, Map.class);
		//将参数信息转换成html
		StringBuffer sb = new StringBuffer(); 
		sb.append("<table cellpadding=\"0\" cellspacing=\"1\" width=\"100%\" border=\"1\" class=\"Ptable\">\n");
		sb.append("    <tbody>\n");
		for (Map map : paramList) {
			sb.append("        <tr>\n");
			sb.append("            <th class=\"tdTitle\" colspan=\"2\">"+map.get("group")+"</th>\n");
			sb.append("        </tr>\n");
			List<Map> params = (List<Map>) map.get("params");
			for (Map map2 : params) {
				sb.append("        <tr>\n");
				sb.append("            <td class=\"tdTitle\">"+map2.get("k")+"</td>\n");
				sb.append("            <td>"+map2.get("v")+"</td>\n");
				sb.append("        </tr>\n");
			}
		}
		sb.append("    </tbody>\n");
		sb.append("</table>");
		return sb.toString();

	}

}
