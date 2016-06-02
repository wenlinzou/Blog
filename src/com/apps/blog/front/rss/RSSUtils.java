package com.apps.blog.front.rss;

import java.util.Date;
import java.util.List;

import com.apps.base.utils.MyStringUtils;
import com.apps.blog.back.bean.Article;
import com.apps.blog.back.pager.ArticlePage;
import com.apps.blog.back.service.ArticleService;
import com.yx.xml.bo.ChannelEItem;
import com.yx.xml.builder.RssBuildFactory;

/**
 * 给博客生成rss
 * @author Pet
 *
 */
public class RSSUtils {

	
	public void rssBuilder(ArticleService<Article> articleService,ArticlePage page, String rsspath, String webpath) {
		//获取所有的文章
		List<Article> articleLists = articleService.queryListByPage(page);
		
		RssBuildFactory factory = new RssBuildFactory();
		if(null!=articleLists && articleLists.size()>0){
			for (int i = 0; i < articleLists.size(); i++) {
				ChannelEItem item = new ChannelEItem();
				Article article = articleLists.get(i);
				item.setTitle(article.getTitle());
				item.setLink("/articleFront/queryDetailById.shtml?id=" + article.getId());
//				item.setLink(webpath + "/articleFront/queryDetailById.shtml?id=" + article.getId());
//				item.setDescription("<![CDATA[" + article.getCont() + "]]>");
				String content = article.getCont();
				content = MyStringUtils.replaceStr(content, "..", webpath);
				item.setDescription(content);
				item.setAuthor("wenlinzou");
				item.setPubDate(article.getPdate());
				item.setCategory(article.getPid().toString());
				
				factory.buildItems(item);
			}
			factory.buildChannel("爱折腾生活", "http://" + webpath, "分享爱折腾生活", "zh-cn", new Date(), "爱折腾生活");
			try {
				factory.buildChannel(rsspath + "feed.xml");
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
	}
}
