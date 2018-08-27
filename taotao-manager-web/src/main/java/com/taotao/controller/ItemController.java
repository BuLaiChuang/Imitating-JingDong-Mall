package com.taotao.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.taotao.common.pojo.EasyUIDataGridResult;
import com.taotao.common.pojo.TaotaoResult;
import com.taotao.pojo.TbItem;
import com.taotao.service.ItemService;

@Controller
public class ItemController {

	@Autowired
	private ItemService itemService;
	/*
	 * 下面，虽然引用了service，但是两个在不同的工程，但是不能调用的，这种情况需要用到dubbo
	 */
	
	@RequestMapping("/item/{itemId}")
	@ResponseBody//这里是返回json数据类型的数据，下面的是传参的注解
	public TbItem getItemById(@PathVariable Long itemId){
		TbItem tbItem = itemService.getItemById(itemId);
		return tbItem;
	}
	
	@RequestMapping("/item/list")
	@ResponseBody
	public EasyUIDataGridResult getItemList(Integer page,Integer rows){
		EasyUIDataGridResult items = itemService.getItems(page, rows);
		return items;
	}
	
	@RequestMapping("/item/save")
	@ResponseBody
	public TaotaoResult addItem(TbItem item, String desc){
		TaotaoResult result = itemService.addItem(item, desc);
		return result;
	}
	
	@RequestMapping("/rest/item/update")
	@ResponseBody
	public TaotaoResult updateItem(@RequestBody TbItem tbItem ){
		
		return null;
	}
	
	
	
}
