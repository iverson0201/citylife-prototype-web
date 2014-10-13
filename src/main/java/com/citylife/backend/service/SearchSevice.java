package com.citylife.backend.service;

import io.searchbox.client.JestClient;
import io.searchbox.client.JestResult;
import io.searchbox.core.Bulk;
import io.searchbox.core.Index;
import io.searchbox.core.Search;
import io.searchbox.indices.CreateIndex;
import io.searchbox.indices.DeleteIndex;

import java.io.IOException;
import java.util.List;

import org.elasticsearch.index.query.QueryBuilder;
import org.elasticsearch.index.query.QueryBuilders;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.citylife.backend.domain.news.News;

/**
 * @author 作者 E-mail:xujw0201@gmail.com
 * @version 创建时间：2014年10月8日 下午3:56:07
 */
@Service
public class SearchSevice {

	@Autowired
	private JestClient jestClient;
	int num = 100000;

	/**
	 * 创建es news索引
	 */
	public void builderSearchIndex() {
		long start = System.currentTimeMillis();
		try {
			// 如果索引存在,删除索引
			DeleteIndex deleteIndex = new DeleteIndex("news");
			jestClient.execute(deleteIndex);

			// 创建索引
			CreateIndex createIndex = new CreateIndex("news");
			jestClient.execute(createIndex);
			// Bulk 两个参数1:索引名称2:类型名称(用文章(article)做类型名称)
			Bulk bulk = new Bulk("news", "article");
			// 添加添加100万条假数据去服务端(ES)
			for (int i = 0; i < num; i++) {
				News news = new News();
				news.setId(i + 1);
				news.setTitle("elasticsearch结合spring springmvc jest 使用做成ＷＥＢ架构" + (i + 1));
				news.setContent("oyhk 学习笔记 上一篇文章,说到了先利用jest junit构架一个ＥＳ的搜索入门例子...现在准备要做一个ES的ＷＥＢ架构例子,希望大家都学习学习ＥＳ分布式搜索引擎,真的非常不错的...欢迎大家一起讨论讨论... 做成ＷＥＢ的架构,当然我不用servlet了...直接使用spring springmvc去做吧...也当是一个ＥＳ跟spring springmvc 集成的例子,为了简单起见,我这里不用freemarker了..我直接使用jsp做视图... 当然我也是用maven了...如果不有熟悉maven的朋友们,可以跟我交流下,大家学习学习..."
						+ (i + 1));
				bulk.addIndex(new Index.Builder(news).build());
			}
			jestClient.execute(bulk);
		} catch (Exception e) {
			e.printStackTrace();
		}
		long end = System.currentTimeMillis();
		System.out.println("创建索引时间:数据量是  " + num + "记录,共用时间 -->> " + (end - start) + " 毫秒");
	}

	/**
	 * 搜索新闻
	 * 
	 * @param param
	 * @return
	 */
	public List<News> searchsNews(String param) {
		try {
			long start = System.currentTimeMillis();
			QueryBuilder queryBuilder = QueryBuilders.queryString(param);
			Search search = new Search(Search.createQueryWithBuilder(queryBuilder.toString()));
			search.addIndex("news");
			search.addType("article");
			JestResult result = jestClient.execute(search);
			long end = System.currentTimeMillis();
			System.out.println("在" + num + "条记录中,搜索新闻,共用时间 -->> " + (end - start) + " 毫秒");
			return result.getSourceAsObjectList(News.class);
		} catch (IOException e) {
			e.printStackTrace();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}
}
