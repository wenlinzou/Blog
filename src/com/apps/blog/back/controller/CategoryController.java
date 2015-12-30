package com.apps.blog.back.controller;


import java.util.List;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import com.apps.base.BaseAction;
import com.apps.base.utils.MyStringUtils;
import com.apps.blog.back.bean.Category;
import com.apps.blog.back.bean.User;
import com.apps.blog.back.service.impl.CategoryImplService;

@Controller
@RequestMapping("/category")
public class CategoryController extends BaseAction {
	private final static Logger log = Logger.getLogger(CategoryController.class);

	// 接口中写自己的方法的时候用的
	@Autowired(required = false)
	private CategoryImplService<Category> categoryService;
	
	/**
	 * 跳转到数据展示及操作页面，添加列表数据到页面。
	 * @param model
	 * @return
	 * @throws Exception 
	 */
	@RequestMapping("/save")
	public String save(String name){
		boolean nameisnull = MyStringUtils.isNull(name);
		if(!nameisnull){
			Category category = new Category();
			category.setName(name);
			categoryService.add(category);
		}
		String redirctStr = "redirect:/category/queryAll.do";
		return redirctStr;
	}
	
	@RequestMapping("/queryAll")
	public String queryAll(Model model){
		List<Category> list = categoryService.queryAll();
		model.addAttribute("categorys", list);
		return "back/categoryBack";
	}
	
	@RequestMapping("/queryById")
	public String queryById(Integer id, Model model){
		if(null!=id){
			Category category = categoryService.queryById(id);
			model.addAttribute("category", category);
		}
		return "back/categoryeditBack";
	}
	@RequestMapping("/update")
	public String update(String name, Integer id, Model model){
		if(null!=id){
			Category category = new Category();
			if(!MyStringUtils.isNull(name) && null!=id){
				category.setId(id);
				category.setName(name);
				categoryService.update(category);
				String redirctStr = "redirect:/category/queryAll.do";
				return redirctStr;
			}
		}
		return "back/categoryBack";
	}
}
