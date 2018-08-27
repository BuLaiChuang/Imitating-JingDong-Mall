package com.taotao.service;

import java.util.List;

import com.taotao.common.pojo.EasyUITreeNode;

public interface ItemCatService {

	//返回的应该是list的EasyUITreeNode
	/**
	 * 查询分类类目
	 * @param parentId 分类id
	 * @return 返回json集合，里边包含了id、test、state
	 */
	List<EasyUITreeNode> getCatList(long parentId);
	
	
}
