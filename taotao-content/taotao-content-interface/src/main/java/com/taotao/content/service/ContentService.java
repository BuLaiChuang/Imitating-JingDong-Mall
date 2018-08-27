package com.taotao.content.service;

import java.util.List;

import com.taotao.common.pojo.EasyUIDataGridResult;
import com.taotao.common.pojo.TaotaoResult;
import com.taotao.pojo.TbContent;

public interface ContentService {

	/**
	 * 根据内容分类id查询的到指定内容信息
	 * @param categoryId 分类id
	 * @return json数据  包含总记录条数+每条记录的json数据
	 */
	public EasyUIDataGridResult findContentAll(long categoryId);
	/**
	 * 添加一个cms内容该信息
	 * @param tbContent 添加内容的信息
	 * @return 200表示成功
	 */
	public TaotaoResult addContent(TbContent tbContent);
	
	/**
	 * 前端根据id查询指定的内容
	 * @param categoryId 分类id
	 * @return 指定分类下面的所有内容
	 */
	List<TbContent> getContentAll(long categoryId);
}
