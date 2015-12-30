package com.apps.blog.front.controller;

import java.util.List;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import com.apps.base.BaseAction;
import com.apps.base.utils.MyStringUtils;
import com.apps.blog.back.bean.Article;
import com.apps.blog.back.bean.Category;
import com.apps.blog.back.service.impl.ArticleImplService;
import com.apps.blog.back.service.impl.CategoryImplService;

@Controller
@RequestMapping("/articleFront")
public class ArticleFrontController extends BaseAction {
	private final static Logger log = Logger.getLogger(ArticleFrontController.class);

	@Autowired(required = false)
	private ArticleImplService<Article> articleService;
	
	@Autowired(required = false)
	private CategoryImplService<Category> categoryService;
	
	
	@RequestMapping("/queryAllArticle")
	public String queryAllArticle(Model model) throws Exception {
		List<Article> articleList = articleService.queryAllSortDate();
		for (int i = 0; i < articleList.size(); i++) {
			Article a = articleList.get(i);
			String imgStr = MyStringUtils.queryImg(a.getCont());
			if(!MyStringUtils.isNull(imgStr)){
				articleList.get(i).setImg(MyStringUtils.appendImgClass(imgStr));
			}
		}
		
		List<Category> categoryList = categoryService.queryAll();
		
		model.addAttribute("articleList", articleList);
		model.addAttribute("categoryList", categoryList);
		
		return "front/articleIndex";
	}
	
	@RequestMapping("/queryDetailById")
	public String queryDetailById(Integer id, Model model) throws Exception {
		if(null != id){
			Article article = articleService.queryById(id);
			model.addAttribute("article", article);
			
			List<Category> categoryList = categoryService.queryAll();
			model.addAttribute("categoryList", categoryList);
		}
		return "front/articleDetail";
	}
	
	@RequestMapping("/queryByThing")
	public String queryByThing(Integer pid, String date, Model model) throws Exception {
		Article artcile = new Article();
		if(null != pid){
			artcile.setPid(pid);
		}
		if(!MyStringUtils.isNull(date)){
			//artcile.setPdate(pdate);
		}
		if(null!=artcile){
			List<Article> articleList = articleService.queryByThing(artcile);
			for (int i = 0; i < articleList.size(); i++) {
				Article a = articleList.get(i);
				String imgStr = MyStringUtils.queryImg(a.getCont());
				if(!MyStringUtils.isNull(imgStr)){
					articleList.get(i).setImg(MyStringUtils.appendImgClass(imgStr));
				}
			}
			
			model.addAttribute("articleList", articleList);
			
			List<Category> categoryList = categoryService.queryAll();
			model.addAttribute("categoryList", categoryList);
			return "front/articleIndex";
		}else{
			return "front/articleIndex";
		}
	}
	
	
	
	
	@RequestMapping("/add")
	public String add(Integer pid, Integer rootid, String title, String cont, Integer isleaf, Model model) throws Exception {
		if(null != title){
			if(!MyStringUtils.isNull(title)){
				Article article = new Article();
				article.setPid(pid);
				article.setRootid(rootid);
				article.setTitle(title);
				article.setCont(cont);
				article.setIsleaf(isleaf);
				articleService.add(article);
				String redirctStr = "redirect:/article/queryAll.do";
				return redirctStr;
			}
		}
		return "back/articleBack";
	}
}
