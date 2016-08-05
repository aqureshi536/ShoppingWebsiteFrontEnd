package com.ahmad.controller;

import java.io.File;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;

import com.ahmad.dao.CategoryDAO;
import com.ahmad.dao.ProductDAO;
import com.ahmad.dao.SupplierDAO;
import com.ahmad.dao.UserLoginDAO;
import com.ahmad.model.Category;
import com.ahmad.model.Product;
import com.ahmad.model.Supplier;
import com.ahmad.viewmodel.CategoryModel;
import com.ahmad.viewmodel.ProductModel;

@Controller
public class PageController {

	@Autowired
	private Product product;
	@Autowired
	private ProductDAO productDAO;

	

	// Activates When Home Page Is accessed

	@RequestMapping(value = { "/", "/index"})
	public ModelAndView home() {
		ModelAndView mv = new ModelAndView("index");
		mv.addObject("isHomeClicked", "true");
		mv.addObject("active", "home");
		mv.addObject("displayLogin", "true");
		mv.addObject("displayCart", "true");
		
//		Optional
		mv.addObject("pageBeforeAdminAction", "true");
		
		return mv;
	}

	// Activates WHen Login Clicked

	@RequestMapping(value={"/login"})
	public ModelAndView loginPage(@RequestParam(value="error", required=false)String error,
								  @RequestParam(value="logout", required=false)String logout,Model model) {
		ModelAndView mv = new ModelAndView("/index");
		if(error!=null)
		{
			model.addAttribute("error","Oops! Invalid credentials,Please try again");
		}
		if(logout!=null)
		{
			model.addAttribute("msg", "Thank You, You're successfully logged out");
		}
		mv.addObject("isLoginClicked", "true");
		mv.addObject("displayLogin", "true");
		mv.addObject("active", "login");
		mv.addObject("displayCart", "true");
		return mv;
	}

	// activates when clicked View All Products on NavBar

	@RequestMapping("/allProducts")
	public ModelAndView allProducts(Model model) {
		ModelAndView mv = new ModelAndView("index");

		// Add products to we page
		List<Product> productList = productDAO.listProduct();
		model.addAttribute("products", productList);

		mv.addObject("isClickedViewAllProducts", "true");
		mv.addObject("displayLogin", "true");
		mv.addObject("displayCart", "true");
		mv.addObject("activeNavMenu", "viewAllProducts");
		
		
//		Optional
		mv.addObject("pageBeforeAdminAction", "true");
		return mv;
	}

	// Activate When clicked on any Product

}
