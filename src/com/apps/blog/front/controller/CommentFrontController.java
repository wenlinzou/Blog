package com.apps.blog.front.controller;

import java.util.HashMap;
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
import com.apps.base.utils.WordsFilterUtils;
import com.apps.blog.back.bean.Comment;
import com.apps.blog.back.service.impl.CommentServiceImpl;

@Controller
@RequestMapping("/commentFront")
public class CommentFrontController extends BaseAction {
	private final int IS_SHOW = 1;
	/*private final int START_PAGE = 1;
	private final int LOGOUT_USER = 1;
	private final int PAGE_SIZE = 5;*/
	private final static Logger log = Logger.getLogger(CommentFrontController.class);

	@Autowired(required = false)
	private CommentServiceImpl<Comment> commentService;
	
	@Autowired(required = false)
	
	
	
	@RequestMapping("checkName")
	@ResponseBody
	public Map<String, Object> checkName(String visitname, Integer articleid, String comment, String email){
		Map<String, Object> map = new HashMap<String, Object>();
		boolean visitnameisnull = MyStringUtils.isNull(visitname);
		if(!visitnameisnull){
			Comment c = new Comment();
			c.setVisitname(visitname);
			Comment commentnameTemp = commentService.queryCommentByName(c);
			if(null == commentnameTemp){
				map.put("canSave", true);
				map.put("jumpUrl", "commentFront/add.do?");
			} else {
				map.put("canSave", false);
				map.put("msg", "existed");
			}
		}
		return map;
	}
	
	@RequestMapping("/add")
	public String add(HttpServletRequest request, Integer articleid, String comment, String visitname, String email, Model model) throws Exception {
		//记录访问者的IP
		String userLogIP = request.getRemoteAddr();
		log.info("front-article visit by-search IP : " + userLogIP +" : " + IPUtils.getAddressByIP(userLogIP));
		
		Comment c = new Comment();
		if(null != articleid){
			c.setArticleid(articleid);
		}
		if(!MyStringUtils.isNull(comment)){
			//过滤敏感词
			comment = WordsFilterUtils.wordFilter(comment);
			c.setComment(comment);
		}
		if(!MyStringUtils.isNull(visitname)){
			//过滤敏感词
			visitname = WordsFilterUtils.wordFilter(visitname);
			c.setVisitname(visitname);
		}
		if(!MyStringUtils.isNull(email)){
			c.setEmail(email);
		}
		c.setIsshow(IS_SHOW);
		commentService.add(c);
		String jumpjsp = "queryAllArticlePage.shtml";
		if(null != articleid){
			jumpjsp = "queryDetailById.shtml?id=" + articleid;
		}
		return "redirect:/articleFront/" + jumpjsp;
	}
	
}
