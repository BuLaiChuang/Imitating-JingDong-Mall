package com.taotao.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.taotao.common.pojo.EasyUIDataGridResult;
import com.taotao.common.pojo.TaotaoResult;
import com.taotao.pojo.TbContent;
import com.taotao.content.service.ContentService;

@Controller
@RequestMapping("/content")
public class ContentController {

	@Autowired
	private ContentService contentService;
	
	@RequestMapping("/query/list")
	@ResponseBody
	public EasyUIDataGridResult findAllContentCategoryById(long categoryId){
		EasyUIDataGridResult contentAll = contentService.findContentAll(categoryId);
		return contentAll;
	}
	
	@RequestMapping("/save")
	@ResponseBody
	public TaotaoResult addTbContent(TbContent tbContent) {
		TaotaoResult result = contentService.addContent(tbContent);
		return result;
	}
	
	
}
