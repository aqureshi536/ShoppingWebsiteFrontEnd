package com.ahmad.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import com.ahmad.dao.CartDAO;
import com.ahmad.dao.CartItemDAO;
import com.ahmad.dao.CategoryDAO;
import com.ahmad.dao.CustomerDAO;
import com.ahmad.dao.ProductDAO;
import com.ahmad.dao.SupplierDAO;
import com.ahmad.model.Cart;
import com.ahmad.model.CartItem;
import com.ahmad.model.Category;
import com.ahmad.model.Customer;
import com.ahmad.model.Product;
import com.ahmad.model.Supplier;

@Controller
@RequestMapping("/cart")
public class CartController {

	@Autowired
	Product product;
	@Autowired
	ProductDAO productDAO;

	@Autowired
	Category category;
	@Autowired
	CategoryDAO categoryDAO;

	@Autowired
	Supplier supplier;
	@Autowired
	SupplierDAO supplierDAO;

	@Autowired
	Customer customer;
	@Autowired
	CustomerDAO customerDAO;

	@Autowired
	Cart cart;
	@Autowired
	CartItem cartItem;

	@Autowired
	CartDAO cartDAO;
	@Autowired
	CartItemDAO cartItemDAO;

	@RequestMapping("/addToCart/{productId}/{userName}")
	public ModelAndView addToCart(@PathVariable("userName") String userName,
			@PathVariable("productId") String productId, Model model) {
		ModelAndView mv = new ModelAndView("index");

		customer = customerDAO.getCustomerByUserName(userName);
		String customerId = customer.getCustomerId();
		product = productDAO.get(productId);
		String cartId = null;
		if (cartDAO.getCartByCustomerId(customerId) == null) {
			cart.setCustomerId(customerId);
			cartDAO.saveOrUpdate(cart);

			cartId = cart.getCartId();
			cartItem.setCartId(cartId);

		}
		/*
		 * cartItem.setProductId(productId); *
		 * cartItem.setCustomerId(cart.getCustomerId()); if *
		 * (cartItem.getProductId().equals(productId)) {
		 * cartItem.setQuantity(cartItem.getQuantity() + 1); }
		 */ // cartItem.setTotalPrice(cartItem.getTotalPrice() +//
			// product.getPrice());

		else {
			Cart cart = cartDAO.getCartByCustomerId(customerId);
			cartId = cart.getCartId();
			cartItem.setCartId(cartId); // This id is a global variable
		}
		
	
//	Now get the productId from a method
	
		if (returnProductId(customerId, productId) != null) {
			if (returnProductId(customerId, productId).equals(productId)) {
				cartItem.setQuantity(cartItem.getQuantity() + 1);
																	// Sets
																	// Quantity
			} else {
				cartItem = new CartItem();

				Cart cart = cartDAO.getCartByCustomerId(customerId);
				cartId = cart.getCartId();
				cartItem.setCartId(cartId); // This cart id got from the uppper
											// part
				cartItem.setQuantity(1);// sets new cart item quantity
			}
		} else {
			cartItem = new CartItem();

			Cart cart = cartDAO.getCartByCustomerId(customerId);
			cartId = cart.getCartId();
			cartItem.setCartId(cartId); // This cart id got from the uppper part
			cartItem.setQuantity(1);// sets new cart item quantity
		}
		cartItem.setProductId(productId); // sets product product id
		cartItem.setCustomerId(customerId); // sets customer id
		cartItem.setTotalPrice(cartItem.getTotalPrice() + product.getPrice());

		cartItemDAO.saveOrUpdate(cartItem);

		// Setting the values of grand total and no Of products
		List<CartItem> cartItems = cartItemDAO.getCartItemsByCustomerId(customerId);
		double grandTotalPrice = 0;
		for (CartItem cartItem : cartItems) {
			grandTotalPrice = grandTotalPrice + cartItem.getTotalPrice();
		}
		cart.setCartId(cartItem.getCartId());
		cart.setCustomerId(customerId);
		cart.setGrandTotal(grandTotalPrice);

		// Sets no of products in the carts
		int noOfProducts = cartItemDAO.getCartItemsByCustomerId(customerId).size();
		cart.setNoOfProducts(noOfProducts);

		cartDAO.saveOrUpdate(cart);
		mv.addObject("cartItems", noOfProducts);
		mv.addObject("addToCartSuccessMessage", true);

		// This helps not to navigate to another page this is a different page
		// on which
		product = productDAO.get(productId);
		model.addAttribute("product", product);

		// gets category name
		String categoryName;
		if (product.getCategoryId() != null && !product.getCategoryId().isEmpty()) {
			category = categoryDAO.get(product.getCategoryId());
			categoryName = category.getCategoryName();
		} else {
			category.setCategoryName("'Not Available'");
			categoryName = category.getCategoryName();
		}
		mv.addObject("categoryName", categoryName);

		// gets supplier name
		String supplierName;
		if (product.getSupplierId() != null && !product.getSupplierId().isEmpty()) {
			supplier = supplierDAO.get(product.getSupplierId());
			supplierName = supplier.getSupplierName();
		} else {
			supplier.setSupplierName("'Not Available'");
			supplierName = supplier.getSupplierName();
		}
		mv.addObject("supplierName", supplierName);

		mv.addObject("isClickedProductDetail", "true");
		mv.addObject("active", "login");
		mv.addObject("displayCart", "true");

		return mv;
	}

	{
		cart = null;
		cartItem = null;
		product = null;
	}
	
public	String returnProductId(String customerId,String productId)
		{
			List<CartItem> listOfCartItems=	cartItemDAO.getCartItemsByCustomerId(customerId);
			String idToUse="";
		for(CartItem GotCartItem : listOfCartItems)
		{
			if(GotCartItem.getProductId().equals(productId)){
				idToUse=GotCartItem.getProductId();
			return idToUse;
			}
			
		}
		return null;
		}
}
