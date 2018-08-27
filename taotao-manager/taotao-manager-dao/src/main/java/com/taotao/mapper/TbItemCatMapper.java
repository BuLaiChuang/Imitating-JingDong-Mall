package com.taotao.mapper;

import java.util.List;

import com.taotao.pojo.TbItemCat;

public interface TbItemCatMapper {

	/**
	 * 根据id查询分类信息
	 * @param parentId 当前分类id
	 * @return 当前分类下的子类目录数据
	 */
	List<TbItemCat> getItemCatByParentId(long parentId);
	
}