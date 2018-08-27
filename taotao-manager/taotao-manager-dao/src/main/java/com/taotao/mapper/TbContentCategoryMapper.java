package com.taotao.mapper;

import java.util.List;

import com.taotao.pojo.TbContentCategory;

public interface TbContentCategoryMapper {

	/**
	 * 根据当前id查询内容分类信息
	 * @param parentId 父级类目id
	 * @return 当前父级类目下的子类目
	 */
	List<TbContentCategory> getTbContentCategoryByParentId(long parentId);
	/**
	 * 添加一个内容分类管理中的分类列表信息到数据库
	 * @param tbContentCategory 需要添加的分类信息对象
	 */
	void addTbContentCatefory(TbContentCategory tbContentCategory);
	/**
	 * 根据当前id查询指定的内容
	 * @param parentId 当前父级目录的id
	 * @return 返回父级目录下的分类信息
	 */
	TbContentCategory getTbContentCategoryById(long parentId);
	/**
	 * 增加内容管理中的商品分类后修改节点状态
	 * @param tbContentCategory 需要修改的内容分类信息对象
	 */
	void updatetbContentCategory(TbContentCategory tbContentCategory);
}