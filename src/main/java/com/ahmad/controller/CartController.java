package com.ahmad.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import com.ahmad.dao.CartDAO;
import com.ahmad.dao.CartItemDAO;
import com.ahmad.model.Cart;
import com.ahmad.model.CartItem;
import com.ahmad.model.Customer;
import com.ahmad.model.Product;
import com.sun.org.apache.xpath.internal.operations.Mod;

@Controller
@RequestMapping("/cart")
public class CartController {

	@Autowired
	Product product;
	
	@Autowired
	Customer customer;
	
	
	@Autowired
	Cart cart;
	@Autowired
	CartItem cartItem;

	@Autowired
	CartDAO cartDAO;
	@Autowired
	CartItemDAO cartItemDAO;
	
	@RequestMapping("/addToCart")
	public ModelAndView addToCart(){
		ModelAndView mv = new ModelAndView("index");
		
		
		return mv;
	}
	
	

}
