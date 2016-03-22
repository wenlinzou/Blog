package com.apps.blog.front.rss;

import java.util.Date;
import java.util.List;

import com.apps.blog.back.bean.Article;
import com.apps.blog.back.service.ArticleService;
import com.yx.xml.bo.ChannelEItem;
import com.yx.xml.builder.RssBuildFactory;


public class RSSUtils {

	
	public void testBuilder(ArticleService<Article> articleService, String rsspath, String webpath) {
		//获取所有的文章
		List<Article> articleLists = articleService.queryAll();
		
		RssBuildFactory factory = new RssBuildFactory();
		if(null!=articleLists && articleLists.size()>0){
			for (int i = 0; i < articleLists.size(); i++) {
				ChannelEItem item = new ChannelEItem();
				Article article = articleLists.get(i);
				item.setTitle(article.getTitle());
				item.setLink(webpath + "/articleFront/queryDetailById.shtml?id=" + article.getId());
				item.setAuthor("wenlinzou");
				item.setPubDate(article.getPdate());
				item.setCategory(article.getPid().toString());
				
				factory.buildItems(item);
			}
			factory.buildChannel("爱折腾生活", "[url=http://" + webpath + "/]", "测试", "zh-cn", new Date(), "爱折腾生活");
			try {
				factory.buildChannel(rsspath + "demo.xml");
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
	}
}
