package com.apps.blog.back.controller;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import com.apps.base.BaseAction;
import com.apps.base.utils.IPUtils;
import com.apps.blog.back.bean.Article;
import com.apps.blog.back.bean.Comment;
import com.apps.blog.back.service.impl.ArticleServiceImpl;
import com.apps.blog.back.service.impl.CommentServiceImpl;

@Controller
@RequestMapping("/comment")
public class CommentController extends BaseAction {
	private final Integer ALL_SHOW = null;
	
	private final static Logger log = Logger.getLogger(CommentController.class);

	@Autowired(required = false)
	private ArticleServiceImpl<Article> articleService;
	@Autowired(required = false)
	private CommentServiceImpl<Comment> commentService;
	
	
	@RequestMapping("/queryListByArticle")
	public String queryListByArticle(HttpServletRequest request, Integer articleid, Model model) throws Exception {
		//记录访问者的IP
		String userLogIP = request.getRemoteAddr();
		log.info("front-article visit by-search IP : " + userLogIP +" : " + IPUtils.getAddressByIP(userLogIP));
		String jumpjsp = "redirect:/article/queryAll.do";
		if(null != articleid){
			Comment c = new Comment();
			c.setArticleid(articleid);
			c.setIsshow(ALL_SHOW);
			
			List<Comment> commentLists = commentService.queryListByArticle(c);
			model.addAttribute("comments", commentLists);
			
			Article article = articleService.queryById(articleid);
			model.addAttribute("article", article);
			
			jumpjsp = "back/commenteditBack";
		}
		return jumpjsp;
	}
	@RequestMapping("/updateCommentShow")
	public String updateCommentShow(HttpServletRequest request, Integer id, Integer articleid, Integer isshow, Model model) throws Exception {
		//记录访问者的IP
		String userLogIP = request.getRemoteAddr();
		log.info("front-article visit by-search IP : " + userLogIP +" : " + IPUtils.getAddressByIP(userLogIP));
		String jumpjsp = "redirect:/article/queryAll.do";
		if(null != id && null != isshow){
			Comment c = new Comment();
			c.setIsshow(isshow);
			c.setId(id);
			commentService.update(c);
			
			jumpjsp = "redirect:/comment/queryListByArticle.do?articleid=" + articleid;
		}
		return jumpjsp;
	}
	
}
