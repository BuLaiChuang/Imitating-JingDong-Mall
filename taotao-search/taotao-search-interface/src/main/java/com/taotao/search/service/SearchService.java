package com.taotao.search.service;

import com.taotao.common.pojo.SearchResult;

public interface SearchService {

	/**
	 * 根据当前页面和需要查询的条件，返回商品类别集合，总记录条数，总页数
	 * @param querystring 查询条件（什么条件）
	 * @param page 当前页
	 * @param rows 每页显示的条数
	 * @return SearchResult
	 */
	public SearchResult search(String querystring, int page, int rows);
}
