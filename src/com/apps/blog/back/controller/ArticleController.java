package com.apps.blog.back.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import com.apps.base.BaseAction;
import com.apps.base.utils.IPUtils;
import com.apps.base.utils.MyStringUtils;
import com.apps.blog.back.bean.Article;
import com.apps.blog.back.bean.Category;
import com.apps.blog.back.service.impl.ArticleServiceImpl;
import com.apps.blog.back.service.impl.CategoryServiceImpl;

@Controller
@RequestMapping("/article")
public class ArticleController extends BaseAction {
	private final static Logger log = Logger.getLogger(ArticleController.class);

	// 接口中写自己的方法的时候用的
	@Autowired(required = false)
	private ArticleServiceImpl<Article> articleService;
	@Autowired(required = false)
	private CategoryServiceImpl<Category> categoryService;
	
	@RequestMapping("/queryLoadAdd")
	public String queryLoadAdd(HttpServletRequest request, Model model) throws Exception {
		if(null == request.getSession().getAttribute("categoryList")){
			List<Category> categoryList = categoryService.queryAll();
			request.getSession().setAttribute("categoryList", categoryList);
		}
		return "back/artcileaddBack";
	}
	
	@RequestMapping("/queryAll")
	public String queryAll(HttpServletRequest request, Model model) throws Exception {
		List<Article> articleList = articleService.queryAll();
		model.addAttribute("articleList", articleList);
		
		if(null == request.getSession().getAttribute("categoryList")){
			List<Category> categoryList = categoryService.queryAll();
			request.getSession().setAttribute("categoryList", categoryList);
		}
		return "back/artcileBack";
	}
	
	@RequestMapping("/queryAllComment")
	public String queryAllComment(Model model) throws Exception {
		List<Article> articleList = articleService.queryAll();
		model.addAttribute("articleList", articleList);
		return "back/commentBack";
	}
	
	@RequestMapping("/queryById")
	public String queryById(Integer id, Model model) throws Exception {
		if(null != id){
			Article article = articleService.queryById(id);
			model.addAttribute("article", article);
		}
		return "back/articleeditBack";
	}
	@RequestMapping("/update")
	public String update(HttpServletRequest request, Integer id, Integer pid, Integer rootid, String title, String cont, Integer isleaf, Model model) throws Exception {
		//记录访问者的IP
		String userLogIP = request.getRemoteAddr();
		log.info("back-article update IP : " + userLogIP +" : " + IPUtils.getAddressByIP(userLogIP));
		
		if(null != id){
//			if(!MyStringUtils.isNull(title)){
				Article article = new Article();
				article.setId(id);
				article.setPid(pid);
				article.setRootid(rootid);
				article.setTitle(title);
				article.setCont(cont);
				article.setIsleaf(isleaf);
				articleService.update(article);
				String redirctStr = "redirect:/article/queryAll.do";
				return redirctStr;
//			}
		}
		
		
		return "back/articleBack";
	}
	
	@RequestMapping("/add")
	public String add(HttpServletRequest request, Integer pid, Integer rootid, String title, String cont, Integer isleaf, Model model) throws Exception {
		//记录访问者的IP
		String userLogIP = request.getRemoteAddr();
		log.info("back-article add IP : " + userLogIP +" : " + IPUtils.getAddressByIP(userLogIP));
				
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
		
		List<Article> articleMonthList = articleService.queryAllSortDate();
		List<String> dateList = articleService.getAllDate(articleMonthList);

		Map<String, String> monthMap = new HashMap<String, String>();
		monthMap = MyStringUtils.arrangeEnglishMonth(dateList,0);
		request.getSession().setAttribute("monthMap", monthMap);
		
		return "back/articleBack";
	}
}
