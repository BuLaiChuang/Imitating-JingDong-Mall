package com.taotao.search.service.Impl;

import org.apache.solr.client.solrj.SolrQuery;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.taotao.common.pojo.SearchResult;
import com.taotao.search.service.SearchService;

@Service
public class SearchServiceImpl implements SearchService {

	@Autowired
	private SearchDao searchDao;
	//上面这个注入时有两种方式，第一种是在spring中配置，第二种是在对应的类上面加注解@Repository
	
	@Override
	public SearchResult search(String querystring, int page, int rows) {
		//创建查询条件并且封装查询条件
		SolrQuery query = new SolrQuery();
		//设置查询条件
		query.setQuery(querystring);
		//开始
		query .setStart(0);
		//每页显示多少
		query.setRows(rows);
		//开启高亮
		query.setHighlight(true);
		//设置哪个域高亮
		query.addHighlightField("item_title");
		
		//设置前缀
		query.setHighlightSimplePre("<span style='color:red'>");
		//设置后缀
		query.setHighlightSimplePost("</span >");
		
		//拼接查询条件
		//设置默认搜索域（复制域是什么？）
		query.set("df", "item_keywords");
		
		SearchResult result = searchDao.search(query);
		//计算一共有多少页
		long pageCount = result.getRecordCount()%rows==0?result.getRecordCount()/rows:result.getRecordCount()/rows+1;
		result.setPageCount(pageCount);
		
		return null;
	}

}
