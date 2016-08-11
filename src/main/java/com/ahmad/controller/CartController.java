package com.ahmad.controller;

import java.security.Principal;
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

	@RequestMapping("/addToCart/{productId}")
	public ModelAndView addToCart(@PathVariable("productId") String productId, Model model, Principal userName) {
		ModelAndView mv = new ModelAndView("index");
		// System.out.println(name);

		// 1.Get the customer id by its user name
		String customerName = userName.getName();
		customer = customerDAO.getCustomerByUserName(customerName);
		String customerId = customer.getCustomerId();

		// 2.Check whether his cart is present in the cart table
		// If cart is not present then make a cart for him
		if (cartDAO.getCartByCustomerId(customerId) == null) {
			cart.setCustomerId(customerId);
			cartDAO.saveOrUpdate(cart);
			// cartItem.setCartId(cart.getCartId());
		}

		// 3.get the product price

		product = productDAO.get(productId);
		

		// If cart is present then go into the cartItem table and search for
		// product
		// this customer selected whether it exists or it is a new product.
		// -------------
		// passing the customerId and productId to a method name returnCartItem
		// through a method

		//if we get null means the product is not present
		
		if (addCartItem(customerId, productId, cart.getCartId()) == null) {
			cartItem=new CartItem();
			cartItem.setCartId(cart.getCartId());
			cartItem.setCustomerId(customerId);
			cartItem.setProductId(product.getProductId());
			cartItem.setQuantity(1);
			cartItem.setTotalPrice(product.getPrice());
			cartItemDAO.saveOrUpdate(cartItem);
			System.out.println("Insertion of cartItem");
		}
		

		List<CartItem> listOfSelectedCartItems;
		// Now after getting the cartItem change grandTotal and No of Products
		listOfSelectedCartItems = cartItemDAO.getCartItemsByCustomerId(customerId);
		double grandTotal = 0;
		for (CartItem item : listOfSelectedCartItems) {
			grandTotal = grandTotal + item.getTotalPrice();
		}
		cart.setGrandTotal(grandTotal);

		int noOfProducts = listOfSelectedCartItems.size();
		cart.setCartId(cart.getCartId());
		cart.setNoOfProducts(noOfProducts);
		cartDAO.saveOrUpdate(cart);

		mv.addObject("cartItems", noOfProducts);
		mv.addObject("addToCartSuccessMessage", true);

		// =========== Completed Adding the item to cart =====

		// Now navigate to the same page

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

	// This is the method which perform all the operations related to addition
	// of product cartItem
	public String addCartItem(String customerId, String productId, String cartId) {
		List<CartItem> listOfSelectedCartItems = cartItemDAO.getCartItemsByCustomerId(customerId);

		Product product = productDAO.get(productId);
		
		
		
		for (CartItem item : listOfSelectedCartItems) {
			String itemProductId = item.getProductId();
			System.out.println(itemProductId);
			if (itemProductId.equals(product.getProductId())) {
				System.out.println(item.getCartItemId());
				item.setCartItemId(item.getCartItemId());
				
				System.out.println(item.getQuantity());
				item.setQuantity(item.getQuantity() + 1);
				
				System.out.println(item.getTotalPrice());
				item.setTotalPrice(item.getTotalPrice() + product.getPrice());
				
				System.out.println(item.toString());
				cartItemDAO.saveOrUpdate(item);
				return "Updation Successful";	
			}
			
		}

		return null;
	}
	
	

	
}
