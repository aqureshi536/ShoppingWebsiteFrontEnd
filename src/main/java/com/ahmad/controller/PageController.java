package com.ahmad.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import com.ahmad.dao.UserLoginDAO;

@Controller
public class PageController {

	@Autowired
	private UserLoginDAO userLoginDAO;

	public void setUserLoginDAO(UserLoginDAO userLoginDAO) {
		this.userLoginDAO = userLoginDAO;
	}

//	Activates When Home Page Is accessed
	@RequestMapping(value = { "/", "/index" })
	public ModelAndView home() {
		ModelAndView mv = new ModelAndView("index");
		mv.addObject("isHomeClicked", "true");
		mv.addObject("active", "home");
		mv.addObject("displayLogin", "true");
		mv.addObject("displayCart", "true");
		return mv;
	}

//	Activates WHen Login Clicked
	@RequestMapping("/login")
	public ModelAndView loginPage() {
		ModelAndView mv = new ModelAndView("/index");
		mv.addObject("isLoginClicked", "true");
		mv.addObject("displayLogin", "true");
		mv.addObject("active", "login");
		mv.addObject("displayCart", "true");
		return mv;
	}

//	Activate When clicked on any Product
	@RequestMapping("/productDetail")
	public ModelAndView productDetail() {
		ModelAndView mv = new ModelAndView("/index");
		mv.addObject("isClickedProductDetail", "true");
		mv.addObject("active", "login");
		mv.addObject("displayCart", "true");
		return mv;
	}
	
//	activates when clicked View All Products on NavBar
	@RequestMapping("/allProducts")
	public ModelAndView allProducts()
	{
		ModelAndView mv = new ModelAndView("index");
		mv.addObject("isClickedViewAllProducts", "true");
		mv.addObject("displayLogin", "true");
		mv.addObject("displayCart", "true");
		mv.addObject("activeNavMenu", "viewAllProducts");
		return mv;
	}

//	activates when admin clicked Product on Side Bar
	@RequestMapping("/adminViewProducts")
	public ModelAndView adminViewProducts()
	{
		ModelAndView mv = new ModelAndView("index");
		mv.addObject("isClickedAdminViewProducts","true");
		mv.addObject("active", "adminProducts");
		mv.addObject("displayAdminAction", "true");
		return mv;
	}
	
//	activates when admin licked Category on sideBar
	@RequestMapping("/adminViewCategory")
	public ModelAndView adminViewCategory()
	{
		ModelAndView mv =new ModelAndView("/index");
		mv.addObject("isClickedAdminViewCategory", "true");
		mv.addObject("active", "adminCategory");
		mv.addObject("displayAdminAction", "true");
		return mv;
	}
	
//	activates when admin licked Category on sideBar
	@RequestMapping("/adminViewSupplier")
	public ModelAndView adminViewSupplier()
	{
		ModelAndView mv =new ModelAndView("/index");
		mv.addObject("isClickedAdminViewSupplier", "true");
		mv.addObject("active", "adminSupplier");
		mv.addObject("displayAdminAction", "true");
		return mv;
	}
	
	
	@RequestMapping("/addProduct")
	public ModelAndView addProduct() {
		ModelAndView mv = new ModelAndView("/index");
		mv.addObject("isAddProductClicked", "true");
		
		mv.addObject("displayLogout", "true");
		mv.addObject("displayAdminAction", "true");
		return mv;
	}

	@RequestMapping("/updateProduct")
	public ModelAndView updateProduct() {
		ModelAndView mv = new ModelAndView("/index");
		mv.addObject("isUpdateProductClicked", "true");
		
		mv.addObject("displayLogout", "true");
		mv.addObject("displayAdminAction", "true");
		return mv;
	}

	@RequestMapping("/addCategory")
	public ModelAndView addCategory() {
		ModelAndView mv = new ModelAndView("/index");
		mv.addObject("isAddCategoryClicked", "true");
		
		mv.addObject("displayLogout", "true");
		mv.addObject("displayAdminAction", "true");
		return mv;
	}

	@RequestMapping("/updateCategory")
	public ModelAndView updateCategory() {
		ModelAndView mv = new ModelAndView("/index");
		mv.addObject("isUpdateCategoryClicked", "true");
		
		mv.addObject("displayLogout", "true");
		mv.addObject("displayAdminAction", "true");
		return mv;
	}

	@RequestMapping("/addSupplier")
	public ModelAndView addSupplier() {
		ModelAndView mv = new ModelAndView("/index");
		mv.addObject("isAddSupplierClicked", "true");
		
		mv.addObject("displayLogout", "true");
		mv.addObject("displayAdminAction", "true");
		return mv;
	}

	@RequestMapping("/updateSupplier")
	public ModelAndView updateSupplier() {
		ModelAndView mv = new ModelAndView("/index");
		mv.addObject("isUpdateSupplierClicked", "true");
	
		mv.addObject("displayLogout", "true");
		mv.addObject("displayAdminAction", "true");
		return mv;
	}

}
