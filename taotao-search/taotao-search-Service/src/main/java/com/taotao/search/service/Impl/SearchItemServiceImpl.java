package com.taotao.search.service.Impl;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import org.apache.solr.client.solrj.SolrServer;
import org.apache.solr.client.solrj.SolrServerException;
import org.apache.solr.common.SolrInputDocument;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.taotao.common.pojo.SearchItem;
import com.taotao.common.pojo.TaotaoResult;
import com.taotao.search.dao.SearchItemMapper;
import com.taotao.search.service.SearchItemService;
@Service
public class SearchItemServiceImpl implements SearchItemService {

	@Autowired
	private SearchItemMapper searchItemMapper;
	
	@Autowired
	private SolrServer solrServer;
	
	@Override
	public TaotaoResult importAllItems() {
		try {
			//查询数据库得到所有的数据
			List<SearchItem> itemList = searchItemMapper.getItemList();
			for (SearchItem searchItem : itemList) {
				//定义solr域
				SolrInputDocument document = new SolrInputDocument();
				// 4、为文档添加域
							document.addField("id", searchItem.getId());
							document.addField("item_title", searchItem.getTitle());
							document.addField("item_sell_point", searchItem.getSell_point());
							document.addField("item_price", searchItem.getPrice());
							document.addField("item_image", searchItem.getImage());
							document.addField("item_category_name", searchItem.getCategory_name());
							document.addField("item_desc", searchItem.getItem_desc());
							// 5、向索引库中添加文档。
							solrServer.add(document);
						}
						//提交修改
						solrServer.commit();
						// 6、返回TaotaoResult。
						return TaotaoResult.ok();
		} catch (SolrServerException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
				return TaotaoResult.build(500, "報錯了");
	}

}

