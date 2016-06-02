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
import org.springframework.web.bind.annotation.ResponseBody;

import com.apps.base.BaseAction;
import com.apps.base.utils.IPUtils;
import com.apps.base.utils.MyStringUtils;
import com.apps.blog.back.bean.Article;
import com.apps.blog.back.bean.Category;
import com.apps.blog.back.bean.Share;
import com.apps.blog.back.service.impl.ArticleServiceImpl;
import com.apps.blog.back.service.impl.CategoryServiceImpl;
import com.apps.blog.back.service.impl.ShareServiceImpl;
/**
 * 文章操作类
 * @author Pet
 *
 */
@Controller
@RequestMapping("/article")
public class ArticleController extends BaseAction {
	private final static Logger log = Logger.getLogger(ArticleController.class);

	@Autowired(required = false)
	private ArticleServiceImpl<Article> articleService;
	@Autowired(required = false)
	private CategoryServiceImpl<Category> categoryService;
	@Autowired(required = false)
	private ShareServiceImpl<Share> shareService;
	
	/**
	 * 添加文章时，加载文章类别
	 * @param request
	 * @param model
	 * @return
	 * @throws Exception
	 */
	@RequestMapping("/queryLoadAdd")
	public String queryLoadAdd(HttpServletRequest request, Model model) throws Exception {
		if(null == request.getSession().getAttribute("categoryList")){
			List<Category> categoryList = categoryService.queryAll();
			request.getSession().setAttribute("categoryList", categoryList);
		}
		return "back/artcileaddBack";
	}
	
	/**
	 * 查询所有文章
	 * @param request
	 * @param model
	 * @return
	 * @throws Exception
	 */
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
	
	/**
	 * 含有评论的所有文章
	 * @param model
	 * @return
	 * @throws Exception
	 */
	@RequestMapping("/queryAllComment")
	public String queryAllComment(Model model) throws Exception {
		List<Article> articleList = articleService.queryAllComment();
		model.addAttribute("articleList", articleList);
		return "back/commentBack";
	}
	
	/**
	 * 查询文章根据id
	 * @param id 文章id
	 * @param model
	 * @return 当前文章页面jsp
	 * @throws Exception
	 */
	@RequestMapping("/queryById")
	public String queryById(Integer id, Model model) throws Exception {
		if(null != id){
			Article article = articleService.queryById(id);
			model.addAttribute("article", article);
		}
		return "back/articleeditBack";
	}
	
	/**
	 * 分享文章
	 * @param articleid
	 * @param isshare
	 * @return
	 */
	@RequestMapping("updateShare")
	@ResponseBody
	public Map<String, Object> updateShare(HttpServletRequest request, Integer articleid, Integer isshare){
		Map<String, Object> map = new HashMap<String, Object>();
		if(null != isshare){
			Share share = shareService.queryByArticleId(articleid);
			if(null == share){
				share = new Share();
				share.setArticleid(articleid);
				share.setIsshare(isshare);
				shareService.add(share);
			}else{
				share.setIsshare(isshare);
				shareService.update(share);
			}
		}
		
		if(1==isshare){
			String shareCode = shareService.queryByArticleId(articleid).getCode();
			map.put("shareCode", shareCode);
			String shareUrl = request.getRequestURL().toString();
			//http://localhost:8080/Blog/article/updateShare.do
			//http://localhost:8080/Blog/articleFront/queryDetailById.shtml?id=
			StringBuilder sb = new StringBuilder();
			String tempUrl = MyStringUtils.arrangeShareUrl(shareUrl);
			sb.append(tempUrl).append("/").append("articleFront/queryDetailById.shtml?id=").append(articleid);
			shareUrl = sb.toString();
			map.put("shareUrl", shareUrl);
		}else{
			map.put("articleId", articleid);
		}
		map.put("can", isshare==1?true:false);
		return map;
	
	}
	
	/**
	 * 修改文章信息
	 * @param request
	 * @param id 文章id
	 * @param pid	类别id
	 * @param rootid 是否置顶
	 * @param title 标题
	 * @param cont 内容
	 * @param isleaf 是否可见
	 * @param model
	 * @return 所有文章页面jsp
	 * @throws Exception
	 */
	@RequestMapping("/update")
	public String update(HttpServletRequest request, Integer id, Integer pid, Integer rootid, String title, String cont, Integer isleaf, Integer isshare, Model model) throws Exception {
		//记录访问者的IP
		String userLogIP = request.getRemoteAddr();
		log.info("back-article update IP : " + userLogIP +" : " + IPUtils.getAddressByIP(userLogIP));
		
		if(null != id ){
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
		}
		
		
		return "back/articleBack";
	}
	
	/**
	 * 添加文章信息
	 * @param request
	 * @param pid 类别id
	 * @param rootid 是否置顶
	 * @param title 标题
	 * @param cont 内容
	 * @param isleaf 是否可见
	 * @param model
	 * @return 查询所有文章，跳转所有文章页面jsp
	 * @throws Exception
	 */
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
