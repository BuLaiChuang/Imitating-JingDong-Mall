package com.taotao.content.service.Impl;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.taotao.common.pojo.EasyUITreeNode;
import com.taotao.common.pojo.TaotaoResult;
import com.taotao.content.service.ContentCategoryService;
import com.taotao.mapper.TbContentCategoryMapper;
import com.taotao.pojo.TbContent;
import com.taotao.pojo.TbContentCategory;

@Service
public class ContentCategoryServiceImpl implements ContentCategoryService {

	@Autowired
	private TbContentCategoryMapper contentCategoryMapper;

	@Override
	public List<EasyUITreeNode> getContentCategoryList(long parentId) {
		List<TbContentCategory> contentCategorylist = contentCategoryMapper.getTbContentCategoryByParentId(parentId);
		List<EasyUITreeNode> result = new ArrayList<EasyUITreeNode>();
		for (TbContentCategory tbContentCategory : contentCategorylist) {
			EasyUITreeNode node = new EasyUITreeNode();
			node.setId(tbContentCategory.getId());
			node.setText(tbContentCategory.getName());
			node.setState(tbContentCategory.getIsParent()?"closed":"open");
			result.add(node);
		}
		return result;
	}


	/*
	 * 内容管理，内容类别的添加，从页面传过来两个值，一个是id（是要在哪个目录下面创建新目录时 的父级目录），name是新目录的名称
	 */
	@Override
	public TaotaoResult addContentCategory(long parentId, String name) {
		TbContentCategory tbContentCategory = new TbContentCategory();
		Date date = new Date();
		tbContentCategory.setParentId(parentId);
		tbContentCategory.setName(name);
		tbContentCategory.setIsParent(false);
		tbContentCategory.setStatus(1);
		tbContentCategory.setSortOrder(1);
		tbContentCategory.setCreated(date);
		tbContentCategory.setUpdated(date);
		contentCategoryMapper.addTbContentCatefory(tbContentCategory);
		/*
		 * 修改父级目录
		 */
		TbContentCategory categoryById = contentCategoryMapper.getTbContentCategoryById(parentId);
		if(!categoryById.getIsParent()){
			categoryById.setIsParent(true);
			contentCategoryMapper.updatetbContentCategory(tbContentCategory);
		}
		//回传给controller层,即时添加即时显示
		return TaotaoResult.ok(tbContentCategory);
	}
}
