package com.apps.blog.front.controller;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;

import javax.servlet.http.HttpServletRequest;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.apps.base.BaseAction;
import com.apps.base.utils.BrowserActiveUtils;
import com.apps.base.utils.IPUtils;
import com.apps.base.utils.MyStringUtils;
import com.apps.blog.back.bean.Article;
import com.apps.blog.back.bean.Category;
import com.apps.blog.back.bean.Comment;
import com.apps.blog.back.bean.User;
import com.apps.blog.back.pager.ArticlePage;
import com.apps.blog.back.service.impl.ArticleServiceImpl;
import com.apps.blog.back.service.impl.CategoryServiceImpl;
import com.apps.blog.back.service.impl.CommentServiceImpl;

@Controller
@RequestMapping("/articleFront")
public class ArticleFrontController extends BaseAction {
	private final int IS_TOP = 1;
	private final int START_PAGE = 1;
	private final int LOGOUT_USER = 1;
	private final int PAGE_SIZE = 5;
	private final static Logger log = Logger.getLogger(ArticleFrontController.class);

	@Autowired(required = false)
	private ArticleServiceImpl<Article> articleService;
	
	@Autowired(required = false)
	private CategoryServiceImpl<Category> categoryService;
	
	@Autowired(required = false)
	private CommentServiceImpl<Comment> commentService;
	
	
	@RequestMapping("/queryAllArticle")
	public String queryAllArticle(HttpServletRequest request, Model model) throws Exception {
		//记录访问者的IP
		String userLogIP = request.getRemoteAddr();
		log.info("front-article visit IP : " + userLogIP +" : " + IPUtils.getAddressByIP(userLogIP));
		
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
//		model.addAttribute("monthMap", monthMap);
		request.getSession().setAttribute("monthMap", monthMap);
		
		model.addAttribute("articleList", articleList);
//		model.addAttribute("categoryList", categoryList);
		request.getSession().setAttribute("categoryList", categoryList);
		
		return "front/articleIndex";
	}
	
	@RequestMapping("/queryAllArticlePage")
	public String queryAllArticlePage(ArticlePage page, Integer pid, String date, String keyword, HttpServletRequest request, Model model) throws Exception {
		String user_agent = request.getHeader("user-agent");

		//记录访问者的IP
		String userLogIP = request.getRemoteAddr();
		/*boolean isLowSpider = IPUtils.checkSpider(userLogIP);
		if(isLowSpider){
			return "error/spider";
		}*/
		boolean isBrowser = BrowserActiveUtils.isBrowserActive(user_agent);
		if(!isBrowser){
			log.info("front-article user_agent : " + user_agent);		
			return "error/spider";
		}
		
		if(null!= page.getPage() && page.getPage() == START_PAGE){
			log.info("front-article page-visit IP : " + userLogIP +" : " + IPUtils.getAddressByIP(userLogIP));			
		}else		
			log.info("-currentpage : " + page.getPage());
		
		if(null != pid){
			page.setPid(pid);
		}
		if(!MyStringUtils.isNull(keyword)){
			page.setKeyword(keyword);
			log.info("-keyword : " + keyword);
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
		page.setRootid(IS_TOP);
		
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

			Map<String, String> monthMap = new TreeMap<String, String>();
			monthMap = MyStringUtils.arrangeEnglishMonth(dateList);
			request.getSession().setAttribute("monthMap", monthMap);
		}
		if(null == request.getSession().getAttribute("categoryList")){
			List<Category> categoryList = categoryService.queryAll();
			request.getSession().setAttribute("categoryList", categoryList);
		}
		model.addAttribute("articleList", articleList);
		model.addAttribute("pageData", page);
		
		if(!MyStringUtils.isNull(keyword)){
			model.addAttribute("keyword", keyword);
		}
		if(!MyStringUtils.isNull(date)){
			model.addAttribute("date", date);
		}
		if(null!=pid){
			model.addAttribute("pid", pid);
		}
		
		return "front/articleIndex";
	}
	
	@RequestMapping("/queryAllArticlePageAjax")
	@ResponseBody
	public Map<String, Object> queryAllArticlePageAjax(ArticlePage page, Integer pid, String date, HttpServletRequest request, Model model) throws Exception {
		Map<String, Object> map = new HashMap<String, Object>();
		
		//记录访问者的IP
		String userLogIP = request.getRemoteAddr();
		log.info("front-article page visit IP : " + userLogIP +" : " + IPUtils.getAddressByIP(userLogIP));
		
		if(null != pid){
			page.setPid(pid);
		}
		if(!MyStringUtils.isNull(date)){
			Date pdate = MyStringUtils.strTransDate(date);
			if(null!=pdate)
				page.setPdate(pdate);
		}
		page.setIsleaf(1);
		page.setRows(1);
		
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
			monthMap = MyStringUtils.arrangeEnglishMonth(dateList,0);
			request.getSession().setAttribute("monthMap", monthMap);
		}
		if(null == request.getSession().getAttribute("categoryList")){
			List<Category> categoryList = categoryService.queryAll();
			request.getSession().setAttribute("categoryList", categoryList);
		}
		model.addAttribute("articleList", articleList);
		model.addAttribute("pageData", page);
		
		map.put("articleList", articleList);
		map.put("pageData", page);
		return map;
	}
	
	@RequestMapping("/queryDetailById")
	public String queryDetailById(HttpServletRequest request, Integer id, Model model) throws Exception {
		//记录访问者的IP
		String userLogIP = request.getRemoteAddr();
		log.info("front-article visit detail IP : " + userLogIP +" : " /*+ IPUtils.getAddressByIP(userLogIP)*/);
		
		String jumpjsp = "";
		if(null != id){
			Object objUser = request.getSession(true).getAttribute("user");
			
			Article article = articleService.queryById(id);
			boolean isSecretArticle = article.getIsleaf() < 1;
			
			//如果是存在id,但不可见,且用户没有登录
			if(isSecretArticle && (null == objUser || !(objUser instanceof User))){
				return "redirect:/articleFront/queryAllArticlePage.shtml";
			}
			
			article.setShortmon(MyStringUtils.arrangeEnglishShortMonth(article.getPdate()));
			articleService.updateClick(id);
			model.addAttribute("article", article);
			
			//文章评论
			/*Comment c = new Comment();
			c.setArticleid(id);
			c.setIsshow(IS_SHOW);
			List<Comment> commentLists = commentService.queryListByArticle(c);
			if(commentLists.size()>0)
				model.addAttribute("comments", commentLists);*/
			
			if(null == request.getSession().getAttribute("monthMap")){
				List<Article> articleMonthList = articleService.queryAllSortDate();
				List<String> dateList = articleService.getAllDate(articleMonthList);
				Map<String, String> monthMap = new TreeMap<String, String>();
				monthMap = MyStringUtils.arrangeEnglishMonth(dateList,0);
				request.getSession().setAttribute("monthMap", monthMap);
			}
			if(null == request.getSession().getAttribute("categoryList")){
				List<Category> categoryList = categoryService.queryAll();
				request.getSession().setAttribute("categoryList", categoryList);
			}
			jumpjsp = "front/articleDetail";
				
			
			//List<Category> categoryList = categoryService.queryAll();
			//model.addAttribute("categoryList", categoryList);
		}
		return jumpjsp;
	}
	
	@RequestMapping("/queryByThing")
	public String queryByThing(HttpServletRequest request, Integer pid, String date, Model model) throws Exception {
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
			
			
			model.addAttribute("articleList", articleList);
			
			/*List<Category> categoryList = categoryService.queryAll();
			model.addAttribute("categoryList", categoryList);*/
			if(null == request.getSession().getAttribute("categoryList")){
				List<Category> categoryList = categoryService.queryAll();
				request.getSession().setAttribute("categoryList", categoryList);
			}
		}
			return "front/articleIndex";
	}
	
}
