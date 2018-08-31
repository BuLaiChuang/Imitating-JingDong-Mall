package com.taotao.content.service.Impl;

import java.util.Date;
import java.util.List;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import com.taotao.common.pojo.EasyUIDataGridResult;
import com.taotao.common.pojo.TaotaoResult;
import com.taotao.common.utils.JsonUtils;
import com.taotao.mapper.TbContentMapper;
import com.taotao.pojo.TbContent;

import redis.clients.jedis.JedisCluster;

import com.taotao.content.service.ContentService;
import com.taotao.jedis.JedisClient;
@Service
public class ContentServiceImpl implements ContentService {

	@Value("${CONTENT_KEY}")
	private String CONTENT_KEY;

	@Autowired//这个地方注入，注入它，它会去找他的子类来注入，他的子类现在有两个，但是配置文件值配置了一个
	//跟这里的名字没关系
	private JedisClient jedisCliect;

	@Autowired
	private TbContentMapper tbContentMapper;

	@Override
	public EasyUIDataGridResult findContentAll(long categoryId) {
		List<TbContent> contentAll = tbContentMapper.findTbContentAll(categoryId);
		EasyUIDataGridResult result = new EasyUIDataGridResult();
		result.setTotal(contentAll.size());
		result.setRows(contentAll);
		return result;
	}

	@Override
	public TaotaoResult addContent(TbContent tbContent) {
		Date date = new Date();
		tbContent.setCreated(date);
		tbContent.setUpdated(date);
		tbContentMapper.addTbContent(tbContent);
		return TaotaoResult.ok();
	}

	@Override
	public List<TbContent> getContentAll(long categoryId) {
		//这里取缓存
		try {
			String hget = jedisCliect.hget(CONTENT_KEY, categoryId+"");
			if(StringUtils.isNotBlank(hget)){
				List<TbContent> jsonToList = JsonUtils.jsonToList(hget, TbContent.class);
				System.out.println("从缓存中取redis");
				return jsonToList;
			}
		} catch (Exception e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}

		/**
		 * 1、第一次请求的时候由于没有缓存，所以直接查询数据库，查询完成数据库以后，在return
		 * 之前被数据库里边的数据加入redis缓存中
		 * 2、第二次访问的时候，由于先走上面的代码，先从缓存里边取取到直接return 没有取到代码继续往下执行，
		 */
		List<TbContent> result = tbContentMapper.findTbContentAll(categoryId);
		System.out.println("查询数据库");
		//这里加缓存
		//代表首页
		try {
			jedisCliect.hset(CONTENT_KEY, categoryId+"", JsonUtils.objectToJson(result));
			System.out.println("加入缓存");
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return result;
	}
}
