package com.taotao.mapper;

import java.util.List;

import com.taotao.pojo.TbItem;
import com.taotao.pojo.TbItemDesc;

public interface TbItemMapper {
	/**
	 * 根据商品sku找到对应的商品
	 * @param itemId 商品的sku
	 * @return 商品对象
	 */
	public TbItem findItemById(long itemId);
	
	/**
	 * 使用分页查询发生变法
	 * 分页查询商品信息
	 * @param page 当前页
	 * @param rows 每页显示条数
	 * @return 每页显示的商品数量信息
	 * List<TbItem> getTbItemPaging(int page, int rows);
	 */
	
	/**
	 * 分页查询商品信息
	 * @return 每页显示的商品数量信息
	 */
	  List<TbItem> getTbItem();
	  /**
	   * 添加商品基本信息
	   * @param tbItem 商品基本信息对象
	   */
	  void addTbItem(TbItem tbItem);
	  /**
	   * 添加商品描述信息
	   * @param itemDesc 商品描述信息
	   */
	  void addTbItemDesc(TbItemDesc itemDesc);
	
}