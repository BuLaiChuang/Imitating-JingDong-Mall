package com.taotao.search.service;


import com.taotao.common.pojo.TaotaoResult;

public interface SearchItemService {
    /**
     * 查询数据库并且导入到索引库里边去
     * @return
     */
	public TaotaoResult importAllItems();
	/**
	 * 	根据业务等传入的条件查询文档库
	 * @param query 业务层的查询条件
	 * @return 商品结果集，总记录条数，总页数
	 */
	/*public SearchResult search(SolrQuery query);*///这里的dao是查询索引库和文档库的，就在searchresult里边,//
	
}
