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
import com.apps.base.utils.Code;
import com.apps.base.utils.IPUtils;
import com.apps.base.utils.MyStringUtils;
import com.apps.base.utils.Utils;
import com.apps.blog.back.bean.Category;
import com.apps.blog.back.service.CategoryService;
/**
 * 文章类别操作类
 * @author Pet
 *
 */
@Controller
@RequestMapping("/category")
public class CategoryController extends BaseAction {
	private final static Logger log = Logger.getLogger(CategoryController.class);

	@Autowired(required = false)
	private CategoryService<Category> categoryService;
	
	/**
	 * 跳转到数据展示及操作页面，添加列表数据到页面。 
	 * @param request
	 * @param name 类别名称
	 * @return 当前所有类别页面jsp
	 */
	@RequestMapping("/save")
	public String save(HttpServletRequest request, String name){
		//记录访问者的IP
		String userLogIP = request.getRemoteAddr();
		log.info("back-category add IP : " + userLogIP +" : " + IPUtils.getAddressByIP(userLogIP));
				
		boolean nameisnull = MyStringUtils.isNull(name);
		if(!nameisnull){
			Category category = new Category();
			category.setName(name);
			categoryService.add(category);
		}
		//增加category update categoryList session
		List<Category> categoryList = categoryService.queryAll();
		request.getSession().setAttribute("categoryList", categoryList);
		
		String redirctStr = "redirect:/category/queryAll.do";
		return redirctStr;
	}
	
	/**
	 * 所有类别
	 * @param model
	 * @return 所有类别页面jsp
	 */
	@RequestMapping("/queryAll")
	public String queryAll(Model model){
		/*List<Category> list = categoryService.queryAll();
		model.addAttribute("categorys", list);*/
		return "back/categoryBack";
	}
	/**
	 * 所有类别
	 * @param model
	 * @return 所有类别页面jsp
	 */
	@RequestMapping("/queryAllAjax")
	@ResponseBody
    public Map<String, Object> queryAllAjax() {
	    List<Category> list = categoryService.queryAll();
	    Map<String, Object> map = new HashMap<String, Object>();
	    map.put("categorys", list);
        Utils.getResMap(map, Code.SUCCESS, "查询成功！");
        return map;
	}
	
	/**
	 * 查询类别根据类别id
	 * @param id 类别id
	 * @param model
	 * @return 当前修改页面jsp
	 */
	@RequestMapping("/queryById")
	public String queryById(Integer id, Model model){
		if(null!=id){
			Category category = categoryService.queryById(id);
			model.addAttribute("category", category);
		}
		return "back/categoryeditBack";
	}
	
	/**
	 * 修改类别名称 
	 * @param request
	 * @param name 类别名称
	 * @param id 类别id
	 * @param model
	 * @return 修改类别后所有类别页面jsp
	 */
	@RequestMapping("/update")
	public String update(HttpServletRequest request, String name, Integer id, Model model){
		//记录访问者的IP
		String userLogIP = request.getRemoteAddr();
		log.info("back-category update IP : " + userLogIP +" : " + IPUtils.getAddressByIP(userLogIP));
		
		if(null!=id){
			Category category = new Category();
			if(!MyStringUtils.isNull(name) && null!=id){
				category.setId(id);
				category.setName(name);
				categoryService.update(category);
				
				//修改category update categoryList session
				List<Category> categoryList = categoryService.queryAll();
				request.getSession().setAttribute("categoryList", categoryList);
				
				String redirctStr = "redirect:/category/queryAll.do";
				return redirctStr;
			}
		}
		return "back/categoryBack";
	}
	/**
	 * 修改类别名称 
	 * @param request
	 * @param name 类别名称
	 * @param id 类别id
	 * @param model
	 * @return 修改类别后所有类别页面jsp
	 */
	@RequestMapping("/updateAjax")
	@ResponseBody
	public Map<String, Object> updateAjax(HttpServletRequest request, Category category, Model model){
	    Map<String, Object> map = new HashMap<String, Object>();
	    //记录访问者的IP
	    String userLogIP = request.getRemoteAddr();
	    log.info("back-category update IP : " + userLogIP +" : " + IPUtils.getAddressByIP(userLogIP));
	    Integer id = category.getId();
	    String backJsp = "category/queryAll.do";
	    Map<String, Object> body = new HashMap<String, Object>();
	    body.put("url", backJsp);
	    map.put("body", body);
	    if(null != id && !MyStringUtils.isNull(category.getName())) {
	        //判断修改的业务名是否已存在
	        if (categoryService.hasCategory(category) > 0) {
	            Utils.getResMap(map, Code.ERR_SYS, "类别名称已存在，无法修改！");
	        } else {
	            categoryService.update(category);
	            Utils.getResMap(map, Code.SUCCESS, "修改成功！");
	        }
	    }
	    return map;
	}
}
