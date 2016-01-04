package com.apps.blog.front.controller;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

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
		List<Date> dates = new ArrayList<Date>(); 
		for (int i = 0; i < articleList.size(); i++) {
			Article a = articleList.get(i);
			dates.add(a.getPdate());
			String imgStr = MyStringUtils.queryImg(a.getCont());
			if(!MyStringUtils.isNull(imgStr)){
				articleList.get(i).setImg(MyStringUtils.appendImgClass(imgStr));
			}
			articleList.get(i).setShortmon(MyStringUtils.arrangeEnglishShortMonth(a.getPdate()));
			
		}
		List<String> dateList = MyStringUtils.queryAllDiffMonth(dates);
//		dateList = MyStringUtils.arrangeEnglishMonth(dateList);
		
		Map<String, String> monthMap = new HashMap<String, String>();
		monthMap = MyStringUtils.arrangeEnglishMonth(dateList,0);
		
		
		List<Category> categoryList = categoryService.queryAll();
		model.addAttribute("monthMap", monthMap);
		
		model.addAttribute("articleList", articleList);
		model.addAttribute("categoryList", categoryList);
		
		return "front/articleIndex";
	}
	
	@RequestMapping("/queryDetailById")
	public String queryDetailById(Integer id, Model model) throws Exception {
		if(null != id){
			Article article = articleService.queryById(id);
			article.setShortmon(MyStringUtils.arrangeEnglishShortMonth(article.getPdate()));
			articleService.updateClick(id);
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
			Date pdate = MyStringUtils.strTransDate(date);
			if(null!=pdate)
				artcile.setPdate(pdate);
		}
		artcile.setIsleaf(1);
		if(null!=artcile){
			List<Article> articleList = articleService.queryByThing(artcile);
			List<Date> dates = new ArrayList<Date>(); 
			for (int i = 0; i < articleList.size(); i++) {
				Article a = articleList.get(i);
				dates.add(a.getPdate());
				String imgStr = MyStringUtils.queryImg(a.getCont());
				if(!MyStringUtils.isNull(imgStr)){
					articleList.get(i).setImg(MyStringUtils.appendImgClass(imgStr));
				}
				articleList.get(i).setShortmon(MyStringUtils.arrangeEnglishShortMonth(a.getPdate()));
			}
			
			List<String> dateList = MyStringUtils.queryAllDiffMonth(dates);
			
			Map<String, String> monthMap = new HashMap<String, String>();
			monthMap = MyStringUtils.arrangeEnglishMonth(dateList,0);
			model.addAttribute("monthMap", monthMap);
			
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
