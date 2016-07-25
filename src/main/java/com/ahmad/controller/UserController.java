package com.ahmad.controller;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import com.ahmad.dao.CartDAO;
import com.ahmad.dao.CategoryDAO;
import com.ahmad.dao.ProductDAO;
import com.ahmad.dao.SupplierDAO;
import com.ahmad.dao.UserDetailsDAO;
import com.ahmad.dao.UserLoginDAO;
import com.ahmad.model.Cart;
import com.ahmad.model.Category;
import com.ahmad.model.Product;
import com.ahmad.model.Supplier;
import com.ahmad.model.UserDetails;
import com.ahmad.model.UserLogin;

@Controller
public class UserController {
	@Autowired
	private UserLoginDAO userLoginDAO;

	@Autowired
	private ProductDAO productDAO;

	@Autowired
	private CategoryDAO categoryDAO;

	@Autowired
	private CartDAO cartDAO;

	@Autowired
	private SupplierDAO supplierDAO;

	@Autowired
	private UserDetailsDAO userDetailsDAO;

	@Autowired
	private Category category;

	@Autowired
	private Cart cart;

	@Autowired
	private Supplier supplier;

	@Autowired
	private Product product;

	@Autowired
	private UserLogin userLogin;

	@Autowired
	private UserDetails userDetails;

	// ============================ While user goes on home page

	@RequestMapping("/")
	public ModelAndView onLoad(HttpSession session) {
		ModelAndView mv = new ModelAndView();
		session.setAttribute("category", category);
		session.setAttribute("categoryList", categoryDAO.listCategory());
		return mv;
	}

	// ================== After user gets registered =================
	@RequestMapping(value = "/login/register", method = RequestMethod.POST)
	// ------------ Sir used (@ModelAttribute UserDetails userDetails) here

	public ModelAndView registeredUser(@RequestParam("reEmail") String emailID, @RequestParam("rePass") String password,
			@RequestParam("gender") String gender, @RequestParam("rePhone") String phoneNo) {

		UserDetails userDetails = new UserDetails();
		userDetails.setUserId("usr");
		userDetails.setUserName(emailID);
		userDetails.setPhoneNo(phoneNo);
		userDetails.setGender(gender);
		userDetails.setPassword(password);
		userDetailsDAO.saveOrUpdate(userDetails);

		// ====================== These two lines are added afterward

		ModelAndView mv = new ModelAndView("/home");
		mv.addObject("registerSuccessMessage", "Thank you for registering with us..");
		// return new ModelAndView("/index");
		return mv;
	}

	// ================= When user clicks Login button ===============
	@RequestMapping("/Login")
	public ModelAndView loginAndRegister() {
		ModelAndView mv = new ModelAndView("/index");
		mv.addObject("userDetails", userDetails);
		mv.addObject("isUserClickedLogin", "true");
		return mv;
	}
	
	
	// ======== Sir USed this method as he separated login here and Register here
	// @RequestMapping("/registerHere")
	// public ModelAndView registerHere()
	// {
	// ModelAndView mv = new ModelAndView("/index");
	// mv.addObject("userDetails",userDetails);
	// mv.addObject("isUserClikedRegisterHere","true");
	// return mv;
	// }

}
