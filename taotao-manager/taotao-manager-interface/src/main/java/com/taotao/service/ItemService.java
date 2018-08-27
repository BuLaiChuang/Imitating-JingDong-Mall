package com.taotao.service;

import com.taotao.common.pojo.EasyUIDataGridResult;
import com.taotao.common.pojo.TaotaoResult;
import com.taotao.pojo.TbItem;

public interface ItemService {

	/**
	 * 根据商品的id查询指定的商品信息
	 * @param itemId 商品id
	 * @return 商品信息
	 */
	TbItem getItemById(long itemId);
	
	//	这里用了分页插件，因为下面的返回值类型是common中的类，
	// 里边有两个参数 总条数（得从数据库中单独查询出来，用插件就不需要了）、集合类型的商品对象
	
	/**
	 * 根据页面传递过来的参数分页显示商品信息
	 * @param page 	当前页
	 * @param rows 每一页的条数
	 * @return 总记录条数+每一个商品信息
	 */
	EasyUIDataGridResult getItems(int page, int rows);
	/**
	 * 添加商品的基本信息和商品描述信息，商品的规格参数以后添加
	 * @param item 商品的基本信息
	 * @param desc 商品描述信息
	 * @return 包含了状态码 是否成功的message和json数据
	 */
	TaotaoResult addItem(TbItem item, String desc);
	
	
}
