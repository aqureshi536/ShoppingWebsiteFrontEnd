package com.ahmad.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import com.ahmad.dao.CategoryDAO;
import com.ahmad.dao.CustomerDAO;
import com.ahmad.dao.ProductDAO;
import com.ahmad.model.Category;
import com.ahmad.model.Customer;
import com.ahmad.model.Product;

@Controller
public class PageController {

	@Autowired
	private ProductDAO productDAO;

	@Autowired
	private CategoryDAO categoryDAO;
	@Autowired
	Customer customer;
	@Autowired
	CustomerDAO customerDAO;

	// Activates When Home Page Is accessed

	@RequestMapping(value = { "/", "/index" })
	public ModelAndView home() {
		ModelAndView mv = new ModelAndView("index");
		// Gets the category on the navber
		List<Category> categoryList = categoryDAO.listCategory();
		mv.addObject("categoryList", categoryList);
		// ================================================================
		mv.addObject("isHomeClicked", "true");
		mv.addObject("activeNavMenu", "home");
		mv.addObject("displayLogin", "true");
		mv.addObject("displayCart", "true");
		mv.addObject("displayAdminAction", "true");

		// Optional
		mv.addObject("pageBeforeAdminAction", "true");

		return mv;
	}

	// Activates WHen Login Clicked

	@RequestMapping(value = { "/login" })
	public ModelAndView loginPage(@RequestParam(value = "error", required = false) String error,
			@RequestParam(value = "logout", required = false) String logout, Model model) {
		ModelAndView mv = new ModelAndView("/index");
		mv.addObject("customer", customer);
		if (error != null) {
			model.addAttribute("error", "Oops! Invalid credentials,Please try again");
		}
		if (logout != null) {
			model.addAttribute("msg", "Thank You, You're successfully logged out");
		}
		// Gets the category on the navber
		List<Category> categoryList = categoryDAO.listCategory();
		mv.addObject("categoryList", categoryList);
		// ================================================================
		mv.addObject("isLoginClicked", "true");
		mv.addObject("displayLogin", "true");
		mv.addObject("activeNavMenu", "login");
		mv.addObject("displayCart", "true");
		return mv;
	}

	// activates when clicked View All Products on NavBar

	@RequestMapping("/allProducts")
	public ModelAndView allProducts(Model model,
			@RequestParam(value = "addToCartAllProducts", required = false) String addToCartAllProducts) {
		if(addToCartAllProducts!=null)
		{
			model.addAttribute("addToCartAllProducts", "Product added to cart Successfully");
		}
		ModelAndView mv = new ModelAndView("index");

		
		// Add products to we page
		List<Product> productListByStock = productDAO.listProductByStock();		
		model.addAttribute("products", productListByStock);
		// Gets the category on the navber
		List<Category> categoryList = categoryDAO.listCategory();
		mv.addObject("categoryList", categoryList);
		// ================================================================
		mv.addObject("isClickedViewAllProducts", "true");
		mv.addObject("displayLogin", "true");
		mv.addObject("displayCart", "true");
		mv.addObject("activeNavMenu", "category");

		// Optional
		mv.addObject("pageBeforeAdminAction", "true");
		return mv;
	}

	// Customer after registered

	@RequestMapping(value = "/customer/home", method = RequestMethod.POST)
	public ModelAndView register(@ModelAttribute("customer") Customer customer) {
		ModelAndView mv = new ModelAndView("index");

		customerDAO.saveOrUpdate(customer);
		mv.addObject("isHomeClicked", "true");
		mv.addObject("active", "home");
		mv.addObject("displayLogin", "true");
		mv.addObject("displayCart", "true");

		// Optional
		mv.addObject("pageBeforeAdminAction", "true");
		// Gets the category on the navber
		List<Category> categoryList = categoryDAO.listCategory();
		mv.addObject("categoryList", categoryList);
		// ================================================================
		return mv;
	}

	// To get the products according to category
	@RequestMapping("/allProducts/{categoryId}")
	public ModelAndView categoryProducts(@PathVariable("categoryId") String categoryId) {
		ModelAndView mv = new ModelAndView("index");
		
		
		List<Product> productList = categoryDAO.selectedCategoryProductList(categoryId);
		if(!productList.isEmpty())
		{
		mv.addObject("productList", productList);
		}
		else{
			mv.addObject("productNotPresent","true");
		}
		mv.addObject("isViewProductByCategory", "true");
		
		// Gets the category on the navber
		List<Category> categoryList = categoryDAO.listCategory();
		mv.addObject("categoryList", categoryList);
		// ================================================================
		return mv;
	}

}
