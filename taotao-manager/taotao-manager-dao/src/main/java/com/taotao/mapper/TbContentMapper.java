package com.taotao.mapper;

import java.util.List;

import com.taotao.pojo.TbContent;

public interface TbContentMapper {
 
	/**
	 * 根据分类id查询内容管理中的多有内容
	 * @param categoryId 分类id
	 * @return 当前这个分类下面的所有内容信息
	 */
	List<TbContent> findTbContentAll(long categoryId);
	/**
	 * 添加一个cms内容到数据库
	 * @param tbContent 需要添加的内容对象
	 */
	void addTbContent(TbContent tbContent);
}