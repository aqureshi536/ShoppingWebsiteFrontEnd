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
		if (cartDAO.getCartByCustomerId(customerId) == null) {
			cart.setCustomerId(customerId);
			cartDAO.saveOrUpdate(cart);
			String cartId = cart.getCartId();
			cartItem.setCartId(cartId);
			cartItem.setProductId(productId);
			cartItem.setCustomerId(cart.getCustomerId());
			if (cartItem.getProductId().equals(productId)) {
				cartItem.setQuantity(cartItem.getQuantity() + 1);
			} else {
				cartItem.setQuantity(1);
			}
			cartItem.setTotalPrice(cartItem.getTotalPrice() + product.getPrice());
			
		} else {
			Cart cart = cartDAO.getCartByCustomerId(customerId);
			cartItem.setProductId(productId);
			cartItem.setCartId(cart.getCartId());
			cartItem.setCustomerId(customerId);
			if (cartItem.getProductId().equals(productId)) {
				cartItem.setQuantity(cartItem.getQuantity() + 1);
			} else {
				cartItem.setQuantity(1);
			}
			cartItem.setTotalPrice(cartItem.getTotalPrice() + product.getPrice());
			
		}
		cartItemDAO.saveOrUpdate(cartItem);
		
		
//		Setting the values of grand total and noof products
		List<CartItem> cartItems = cartItemDAO.getCartItemsByCustomerId(customerId);
		double grandTotalPrice = 0;
		for (CartItem cartItem : cartItems) {
			grandTotalPrice = grandTotalPrice + cartItem.getTotalPrice();
		}
		cart.setCartId(cart.getCartId());
		cart.setCustomerId(customerId);
		cart.setGrandTotal(grandTotalPrice);
		cart.setNoOfProducts(cartItemDAO.getCartItemsByCustomerId(customerId).size());
		cartDAO.saveOrUpdate(cart);
		mv.addObject("addToCartSuccessMessage", true);
		// This helps not to navigate to another page
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

}
