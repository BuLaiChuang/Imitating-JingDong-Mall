package com.taotao.search.dao;

import java.util.List;

import com.taotao.common.pojo.SearchItem;

public interface SearchItemMapper {
	
    /**
     * 查询数据库中的三张表tbItem  tbitemcat  tbitemdesc
     * @return SearchItem
     */
	List<SearchItem> getItemList();
}
