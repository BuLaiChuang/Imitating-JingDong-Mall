package com.taotao.service.Impl;

import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.taotao.common.pojo.EasyUIDataGridResult;
import com.taotao.common.pojo.TaotaoResult;
import com.taotao.common.utils.IDUtils;
import com.taotao.mapper.TbItemMapper;
import com.taotao.pojo.TbItem;
import com.taotao.pojo.TbItemDesc;
import com.taotao.service.ItemService;

@Service
public class ItemServiceImpl implements ItemService {
	
	@Autowired
	private TbItemMapper tbItemMapper;

	@Override
	public TbItem getItemById(long itemId) {
		TbItem findItemById = tbItemMapper.findItemById(itemId);
		return findItemById;
	}

	@Override
	public EasyUIDataGridResult getItems(int page, int rows) {
	    //获取第1页，10条内容，默认查询总数count,使用mybatis插件分页查询
	    PageHelper.startPage(page, rows);
	    //得到多有的商品信息
	    List<TbItem> tbItem = tbItemMapper.getTbItem();
	    //创建一个分页信息
	    PageInfo<TbItem> pageInfo = new PageInfo<>(tbItem);
		//创建返回对象
	    EasyUIDataGridResult result = new EasyUIDataGridResult();
	    //将查询出来的信息封装到分页信息中,进行返回
	    result.setTotal(pageInfo.getTotal());
	    result.setRows(tbItem);
	    return result;
	}

	@Override//这里的desc参数干哈
	public TaotaoResult addItem(TbItem item, String desc) {
		long id = IDUtils.genItemId();
		//补全商品基本信息
		item.setId(id);
		item.setStatus((byte)1);
		Date date = new Date();
		item.setCreated(date);
		item.setUpdated(date);
		//添加商品基本信息
		tbItemMapper.addTbItem(item);
		
		//补全商品描述的信息
		TbItemDesc itemDesc = new TbItemDesc();
		itemDesc.setItemId(id);
		itemDesc.setCreated(date);
		itemDesc.setUpdated(date);
		tbItemMapper.addTbItemDesc(itemDesc);
		return TaotaoResult.ok();
	}

}
