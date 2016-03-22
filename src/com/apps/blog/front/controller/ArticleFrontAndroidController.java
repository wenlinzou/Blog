package com.apps.blog.front.controller;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.apps.base.utils.IPUtils;
import com.apps.base.utils.MyStringUtils;
import com.apps.blog.back.bean.Article;
import com.apps.blog.back.bean.Category;
import com.apps.blog.back.bean.User;
import com.apps.blog.back.pager.ArticlePage;
import com.apps.blog.back.service.impl.ArticleServiceImpl;
import com.apps.blog.back.service.impl.CategoryServiceImpl;
import com.apps.blog.front.rss.RSSUtils;

@Controller
@RequestMapping("/articleFrontAndroid")
public class ArticleFrontAndroidController {
	private final int START_PAGE = 1;
	private final int LOGOUT_USER = 1;
	private final int PAGE_SIZE = 5;
	private final static Logger log = Logger.getLogger(ArticleFrontAndroidController.class);

	@Autowired(required = false)
	private ArticleServiceImpl<Article> articleService;
	
	@Autowired(required = false)
	private CategoryServiceImpl<Category> categoryService;
	
	@RequestMapping("/queryArticleAndroidPage")
	@ResponseBody
	public Map<String, Object> queryArticleAndroidPage(ArticlePage page, Integer pid, String date, HttpServletRequest request, Model model) throws Exception {
		Map<String, Object> map = new HashMap<String, Object>();
		//记录访问者的IP
		String userLogIP = request.getRemoteAddr();
		boolean isLowSpider = IPUtils.checkSpider(userLogIP);
		if(isLowSpider){
			//return "error/spider";
			map.put("success", false);
			map.put("message", "爬虫!");
		}
		
		
		if(null!= page.getPage() && page.getPage() == START_PAGE){
			log.info("front-article page-visit IP : " + userLogIP +" : " + IPUtils.getAddressByIP(userLogIP));			
		}else		
			log.info("-currentpage : " + page.getPage());
		if(null != pid){
			page.setPid(pid);
		}
		if(!MyStringUtils.isNull(date)){
			Date pdate = MyStringUtils.strTransDate(date);
			if(null != pdate)
				page.setPdate(pdate);
		}
		Object objUser = request.getSession(true).getAttribute("user");
		if(null != objUser && objUser instanceof User) {
			page.setIsleaf(null);
		} else {
			page.setIsleaf(LOGOUT_USER);
		}
		page.setRows(PAGE_SIZE);
		
		List<Article> articleList = articleService.queryListByPage(page);
		for (int i = 0; i < articleList.size(); i++) {
			Article a = articleList.get(i);
			String imgStr = MyStringUtils.queryImg(a.getCont());
			if(!MyStringUtils.isNull(imgStr)){
				articleList.get(i).setImg(MyStringUtils.appendImgClass(imgStr));
			}
			articleList.get(i).setShortmon(MyStringUtils.arrangeEnglishShortMonth(a.getPdate()));
		}
		
		if(null == request.getSession().getAttribute("monthMap")){
			List<Article> articleMonthList = articleService.queryAllSortDate();
			List<String> dateList = articleService.getAllDate(articleMonthList);
			Map<String, String> monthMap = new HashMap<String, String>();
			monthMap = MyStringUtils.arrangeEnglishMonth(dateList);
			request.getSession().setAttribute("monthMap", monthMap);
		}
		if(null == request.getSession().getAttribute("categoryList")){
			List<Category> categoryList = categoryService.queryAll();
			request.getSession().setAttribute("categoryList", categoryList);
		}

		map.put("articleList", articleList);
		map.put("pageData", page);
		
		map.put("success", true);
		map.put("message", "查询成功!");
		
		if(!MyStringUtils.isNull(date)){
			map.put("date", date);
		}
		if(null!=pid){
			map.put("pid", pid);
		}
		String webpath="", rsspath = "";
		new RSSUtils().testBuilder(articleService, rsspath, webpath);
		return map;
	}
	
	@RequestMapping("/queryDetailByIdAndroid")
	@ResponseBody
	public Map<String, Object> queryDetailByIdAndroid(HttpServletRequest request, Integer id, Model model) throws Exception {
		Map<String, Object> map = new HashMap<String, Object>();
		//记录访问者的IP
		String userLogIP = request.getRemoteAddr();
		log.info("front-article visit detail IP : " + userLogIP +" : " /*+ IPUtils.getAddressByIP(userLogIP)*/);
		
//		String jumpjsp = "";
		if(null != id){
			Object objUser = request.getSession(true).getAttribute("user");
			
			Article article = articleService.queryById(id);
			boolean isSecretArticle = article.getIsleaf() < 1;
			
			//如果是存在id,但不可见,且用户没有登录
			if(isSecretArticle && (null == objUser || !(objUser instanceof User))){
				//return "redirect:/articleFront/queryAllArticlePage.shtml";
				map.put("success", false);
				map.put("message", "请登录!");
				return null;
			}
			
			article.setShortmon(MyStringUtils.arrangeEnglishShortMonth(article.getPdate()));
			articleService.updateClick(id);

			map.put("article", article);
			map.put("success", true);
			map.put("message", "查询成功!");
			
			if(null == request.getSession().getAttribute("monthMap")){
				List<Article> articleMonthList = articleService.queryAllSortDate();
				List<String> dateList = articleService.getAllDate(articleMonthList);
				Map<String, String> monthMap = new HashMap<String, String>();
				monthMap = MyStringUtils.arrangeEnglishMonth(dateList,0);
				request.getSession().setAttribute("monthMap", monthMap);
			}
			if(null == request.getSession().getAttribute("categoryList")){
				List<Category> categoryList = categoryService.queryAll();
				request.getSession().setAttribute("categoryList", categoryList);
			}
//			jumpjsp = "front/articleDetail";
				
			
			//List<Category> categoryList = categoryService.queryAll();
			//model.addAttribute("categoryList", categoryList);
		}
		return map;
	}
	
	@RequestMapping("/queryByThingAndroid")
	@ResponseBody
	public Map<String, Object> queryByThingAndroid(HttpServletRequest request, Integer pid, String date, Model model) throws Exception {
		Map<String, Object> map = new HashMap<String, Object>();
		
		//记录访问者的IP
		String userLogIP = request.getRemoteAddr();
		log.info("front-article visit by-search IP : " + userLogIP +" : " + IPUtils.getAddressByIP(userLogIP));
		
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
			
			
			map.put("articleList", articleList);
			if(null == request.getSession().getAttribute("categoryList")){
				List<Category> categoryList = categoryService.queryAll();
				request.getSession().setAttribute("categoryList", categoryList);
			}
			
			map.put("success", true);
			map.put("message", "查询成功!");
		}
			return map;
	}
}
