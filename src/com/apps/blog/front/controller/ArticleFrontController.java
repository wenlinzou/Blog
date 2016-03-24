package com.apps.blog.front.controller;

import java.io.File;
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
import com.apps.blog.front.rss.RSSUtils;
/**
 * 文章博客前台操作类
 * @author Pet
 *
 */
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
	
	
	/**
	 * 查询所有分页文章
	 * @param page 分页对象
	 * @param pid 类别id
	 * @param date 存档日期（年月）
	 * @param keyword 关键字
	 * @param request
	 * @param model
	 * @return 综合添加查询分页文章页面jsp
	 * @throws Exception
	 */
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
			
			//快速rss
			String serverName = request.getServerName();
			int port = request.getLocalPort();
			String webName = request.getContextPath();
			StringBuilder webTemp = new StringBuilder();
			
			String webpath="", rsspath = "";
			webTemp = port==80?webTemp.append(serverName):webTemp.append(serverName).append(":").append(port);
			webpath = webTemp.append(webName).toString();
			//D:\server\apache-tomcat-7.0.54\webapps\Blog
			String web = request.getSession().getServletContext().getRealPath("");
			File rssFile = new File(web + File.separatorChar + "rss");
			if(!rssFile.exists()){
				rssFile.mkdir();
			}
			rsspath = rssFile.getAbsolutePath() + File.separatorChar;
			page.setRows(10);
			new RSSUtils().rssBuilder(articleService, page, rsspath, webpath);
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
	
	/**
	 * 查询文章详情
	 * @param request
	 * @param id 文章id
	 * @param model
	 * @return 文章详情页面jsp
	 * @throws Exception
	 */
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
		}
		return jumpjsp;
	}
	
}
