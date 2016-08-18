package com.ahmad.controller;

import java.security.Principal;
import java.util.List;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import com.ahmad.dao.CartDAO;
import com.ahmad.dao.CategoryDAO;
import com.ahmad.dao.CustomerDAO;
import com.ahmad.dao.ProductDAO;
import com.ahmad.dao.UserAuthoritiesDAO;
import com.ahmad.dao.UsersDAO;
import com.ahmad.model.Cart;
import com.ahmad.model.Category;
import com.ahmad.model.Customer;
import com.ahmad.model.Product;
import com.ahmad.model.UserAuthorities;
import com.ahmad.model.Users;

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
	@Autowired
	Users users;
	@Autowired
	UsersDAO usersDAO;
	@Autowired
	UserAuthorities userAuthorities;
	@Autowired
	UserAuthoritiesDAO userAuthoritiesDAO;

	@Autowired
	HttpSession httpSession;
	@Autowired
	CartDAO cartDAO;
	@Autowired
	Cart cart;

	// Activates When Home Page Is accessed

	@RequestMapping(value = { "/", "/index" })
	public ModelAndView home(Principal username) {
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
		if (username != null) {
			if (customerDAO.getCustomerByUserName(username.getName()) != null) {
				customer = customerDAO.getCustomerByUserName(username.getName());
				String customerId = customer.getCustomerId();
				// If customer doesnt exist like admin
				if (customerId != null) {
					if (cartDAO.getCartByCustomerId(customerId) != null) {
						int noOfProduct = cartDAO
								.getCartByCustomerId(
										customerDAO.getCustomerByUserName(username.getName()).getCustomerId())
								.getNoOfProducts();
						httpSession.setAttribute("noOfProducts", noOfProduct);
					} else {
						httpSession.setAttribute("noOfProducts", 0);
					}
				}
			}

		}

		return mv;
	}

	// Activates WHen Login Clicked

	@RequestMapping(value = { "/login" })
	public ModelAndView loginPage(@RequestParam(value = "error", required = false) String error,
			@RequestParam(value = "logout", required = false) String logout, Model model,
			@RequestParam(value = "registrationSuccessfull", required = false) String registered) {
		ModelAndView mv = new ModelAndView("/index");
		mv.addObject("customer", new Customer());
		if (error != null) {
			model.addAttribute("error", "Oops! Invalid credentials,Please try again");
		}
		if (logout != null) {
			model.addAttribute("msg", "Thank You, You're successfully logged out");
		}
		if (registered != null) {
			model.addAttribute("registered", "Registration Successfull, Please login again");
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

	@RequestMapping(value = "/login", method = RequestMethod.POST)
	public String registerCustomer(@ModelAttribute("customer") Customer customer) {
		this.customer = customer;
		// save to the customer table
		customerDAO.saveOrUpdate(customer);	
		
		// After saving to customer table save to users table
		users.setCustomerId(customer.getCustomerId());
		users.setEnabled(true);
		users.setUsername(customer.getUsername());
		users.setPassword(customer.getPassword());
		usersDAO.saveOrUpdate(users);
		
		// After saving users table now save to user authorities table
		userAuthorities.setCustomerId(customer.getCustomerId());
		userAuthorities.setUsername(customer.getUsername());
		userAuthorities.setAuthority("ROLE_USER");
		userAuthoritiesDAO.saveOrUpdate(userAuthorities);
		return "redirect:/login?registrationSuccessfull";
	}

	// activates when clicked View All Products on NavBar

	@RequestMapping("/allProducts")
	public ModelAndView allProducts(Model model,
			@RequestParam(value = "addToCartAllProducts", required = false) String addToCartAllProducts) {
		if (addToCartAllProducts != null) {
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
	public ModelAndView categoryProducts(@PathVariable("categoryId") String categoryId,Model model) {
		ModelAndView mv = new ModelAndView("index");

		List<Product> productList = categoryDAO.selectedCategoryProductList(categoryId);
		if (!productList.isEmpty()) {
			mv.addObject("productList", productList);
		} else {
			model.addAttribute("productNotPresent", "Sorry, No products present in "+categoryDAO.get(categoryId).getCategoryName()+" category");
		}
		mv.addObject("isViewProductByCategory", "true");

		// Gets the category on the navber
		List<Category> categoryList = categoryDAO.listCategory();
		mv.addObject("categoryList", categoryList);
		// ================================================================
		return mv;
	}

}
