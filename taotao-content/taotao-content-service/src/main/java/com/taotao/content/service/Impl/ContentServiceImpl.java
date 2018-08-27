package com.taotao.content.service.Impl;

import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.taotao.common.pojo.EasyUIDataGridResult;
import com.taotao.common.pojo.TaotaoResult;
import com.taotao.mapper.TbContentMapper;
import com.taotao.pojo.TbContent;
import com.taotao.content.service.ContentService;
@Service
public class ContentServiceImpl implements ContentService {

	@Autowired
	private TbContentMapper tbContentMapper;
	
	@Override
	public EasyUIDataGridResult findContentAll(long categoryId) {
		List<TbContent> contentAll = tbContentMapper.findTbContentAll(categoryId);
		EasyUIDataGridResult result = new EasyUIDataGridResult();
		result.setTotal(contentAll.size());
		result.setRows(contentAll);
		return result;
	}

	@Override
	public TaotaoResult addContent(TbContent tbContent) {
		Date date = new Date();
		tbContent.setCreated(date);
		tbContent.setUpdated(date);
		tbContentMapper.addTbContent(tbContent);
		return TaotaoResult.ok();
	}

	@Override
	public List<TbContent> getContentAll(long categoryId) {
		List<TbContent> result = tbContentMapper.findTbContentAll(categoryId);
		return result;
	}
}
