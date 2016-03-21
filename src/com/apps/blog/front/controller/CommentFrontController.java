package com.apps.blog.front.controller;

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
import com.apps.base.utils.WordsFilterUtils;
import com.apps.blog.back.bean.Comment;
import com.apps.blog.back.service.impl.CommentServiceImpl;
/**
 * 前端文章评论操作类
 * @author Pet
 *
 */
@Controller
@RequestMapping("/commentFront")
public class CommentFrontController extends BaseAction {
	private final int IS_SHOW = 1;
	private final static Logger log = Logger.getLogger(CommentFrontController.class);

	@Autowired(required = false)
	private CommentServiceImpl<Comment> commentService;
	
	@Autowired(required = false)
	
	
	/**
	 * 判断评论用户名是否已存在
	 * @param request
	 * @param visitname 用户评论名
	 * @param articleid 文章id
	 * @param comment 评论内容
	 * @param email 邮箱
	 * @return 评论用户名是否已存在ajax
	 */
	@RequestMapping("checkName")
	@ResponseBody
	public Map<String, Object> checkName(HttpServletRequest request, String visitname, Integer articleid, String comment, String email){
		Map<String, Object> map = new HashMap<String, Object>();
		boolean visitnameisnull = MyStringUtils.isNull(visitname);
		if(!visitnameisnull){
			Comment c = new Comment();
			if(null!=articleid)c.setArticleid(articleid);
			c.setVisitname(visitname);
			Comment commentnameTemp = commentService.queryCommentByName(c);
			if(null == commentnameTemp){
				map.put("canSave", true);
				map.put("msg", "commented");
				//add comment
				if((null != articleid) && (!MyStringUtils.isNull(comment)) && (!MyStringUtils.isNull(visitname) && (!MyStringUtils.isNull(email)))){
					c.setArticleid(articleid);
					
					//过滤敏感词
					comment = WordsFilterUtils.wordFilter(comment);
					c.setComment(comment);
					
					//过滤敏感词
					visitname = WordsFilterUtils.wordFilter(visitname);
					c.setVisitname(visitname);
					
					c.setEmail(email);
					c.setIsshow(IS_SHOW);
					commentService.add(c);
					
					String userLogIP = request.getRemoteAddr();
					log.info("front-comment add visit by-search IP : " + userLogIP +" : " + IPUtils.getAddressByIP(userLogIP));
				}
				
			} else {
				map.put("canSave", false);
				map.put("msg", "existed");
			}
		}
		return map;
	}
	
	/**
	 * 加载文章所有评论信息
	 * @param request
	 * @param articleid 文章id
	 * @param model
	 * @return 评论信息ajax
	 * @throws Exception
	 */
	@RequestMapping("/queryCommectArticleAjax")
	@ResponseBody
	public Map<String, Object> queryCommectArticleAjax(HttpServletRequest request, Integer articleid, Model model) throws Exception {
		Map<String, Object> map = new HashMap<String, Object>();
		
		//记录访问者的IP
		String userLogIP = request.getRemoteAddr();
		log.info("front-comment visit by-search IP : " + userLogIP +" : " + IPUtils.getAddressByIP(userLogIP));
		
		if((null != articleid)){
			Comment c = new Comment();
			c.setArticleid(articleid);
			
			c.setIsshow(IS_SHOW);
			commentService.add(c);
			
			if(null != articleid){
				List<Comment> commentLists = commentService.queryListByArticle(c);
				if(commentLists.size()>0){
					map.put("comments", commentLists);
				}
			}
		}
		
		return map;
	}
	
}
