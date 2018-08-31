package com.taotao.search.service.Impl;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.apache.solr.client.solrj.SolrQuery;
import org.apache.solr.client.solrj.SolrServer;
import org.apache.solr.client.solrj.SolrServerException;
import org.apache.solr.client.solrj.response.QueryResponse;
import org.apache.solr.common.SolrDocument;
import org.apache.solr.common.SolrDocumentList;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.fasterxml.jackson.annotation.JsonAutoDetect;
import com.taotao.common.pojo.SearchItem;
import com.taotao.common.pojo.SearchResult;

@Repository
public class SearchDao {

	/**
	 * 	根据业务等传入的条件查询文档库
	 * @param query 业务层的查询条件
	 * @return 商品结果集，总记录条数，总页数
	 */
	//这里查询数据不是从数据库中查询的
	@Autowired
	private SolrServer solrServer;
	
	public SearchResult search(SolrQuery query){
		try {
			//返回结果集
			QueryResponse queryResponse = solrServer.query(query); 
			//返回的真正的结果集，问什么么？因为在solr后台点击查询，先返回的是response，response下面才是真正的结果集(里边自带总记录条数
			SolrDocumentList results = queryResponse.getResults();
			//遍历得到每个商品信息，将商品信息封住到searchResult中
			//很多条数据，就需要list集合,SearchResult类里边的集合,SearchItem这里边需要的属性都可以或得到
			List<SearchItem> list = new ArrayList<>();
			for (SolrDocument solrDocument : results) {
				SearchItem item = new SearchItem();
				item.setId((String) solrDocument.get("id"));
				item.setCategory_name((String) solrDocument.get("item_category_name"));
				item.setImage((String) solrDocument.get("item_image"));
				item.setPrice((long) solrDocument.get("item_price"));
				item.setSell_point((String) solrDocument.get("item_sell_point"));
	//高亮，高亮是什么
				Map<String, Map<String, List<String>>> highlighting = queryResponse.getHighlighting();
				//得到高亮的数据
				List<String> list2 = highlighting.get(solrDocument.get("id")).get("item_title");
				//
				String itemTitle = "";
				//有高亮显示的内容时
				if(list2 != null&& list2.size()>0){
					itemTitle = list2.get(0);
				}else{
					itemTitle = (String) solrDocument.get("item_title");
				}
				item.setTitle(itemTitle);
		     //添加到商品列表中.这里的list从哪里来的
				list.add(item);
			}
			SearchResult result = new SearchResult();
			//得到总记录条数
			result.setPageCount(results.getNumFound());
			//得到文档库中所有商品信息加入到集合
			result.setItemList(list);
			
			return result;
			
		} catch (SolrServerException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
	}
}
