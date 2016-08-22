package com.ahmad.controller;

import java.security.Principal;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
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
import com.ahmad.model.OrderedItems;
import com.ahmad.model.Product;
import com.ahmad.model.Supplier;
import com.ahmad.viewmodel.CartItemModel;

@Controller
@RequestMapping("/user/cart")
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

	@Autowired
	HttpSession httpSession;

	@RequestMapping("/")
	public ModelAndView viewCart(Model model, Principal userName,
			@RequestParam(value = "cartItemRemoved", required = false) String cartItemRemoved)

	{
		ModelAndView mv = new ModelAndView("index");
		String customerName = userName.getName();
		if (cartItemRemoved != null) {
			model.addAttribute("cartItemRemoved", "Cart item removed successfully");
		}

		customer = customerDAO.getCustomerByUserName(customerName);
		String customerId = customer.getCustomerId();

		List<CartItemModel> cartItems = null;

		// When there are products in cart
		if (returnProductName(customerId) != null && !returnProductName(customerId).isEmpty()) {
			cartItems = returnProductName(customerId);
			
			mv.addObject("cartItems", cartItems);
			double grandTotal = cartDAO.getCartByCustomerId(customerId).getGrandTotal();
			mv.addObject("grandTotal", grandTotal);

		}
		// When there are no products in cart
		else {
			model.addAttribute("cartEmpty", "No items present in the cart");

			mv.addObject("noOfProducts", 0);
		}

		// Gets the category on the navber
		List<Category> categoryList = categoryDAO.listCategory();
		mv.addObject("categoryList", categoryList);
		// ================================================================

		mv.addObject("isClickedViewCart", true);
		mv.addObject("displayCart", "true");
		mv.addObject("activeNavMenu", "viewCart");

		return mv;
	}

	// Method to get name of product
	public List<CartItemModel> returnProductName(String customerId) {

		List<CartItem> cartItems = cartItemDAO.getCartItemsByCustomerId(customerId);

		List<CartItemModel> cartItemModelList = new ArrayList<>();

		CartItemModel cartItemModel = null;

		for (CartItem item : cartItems) {
			cartItemModel = new CartItemModel();
			cartItemModel.setCartItem(item);
			if (item.getProductId() != null && !item.getProductId().isEmpty()) {
				product = productDAO.get(item.getProductId());
				cartItemModel.setProductName(product.getProductName());
			} else {
				cartItemModel.setProductName("Currently not avilable");
			}
			cartItemModelList.add(cartItemModel);
		}
		return cartItemModelList;
	}

	@RequestMapping("/addToCart/{productId}")
	public String addToCart(@PathVariable("productId") String productId, Model model, Principal userName) {

		// System.out.println(name);

		// 1.Get the customer id by its user name
		String customerName = userName.getName();
		customer = customerDAO.getCustomerByUserName(customerName);
		String customerId = customer.getCustomerId();

		// 2.Check whether his cart is present in the cart table
		// If cart is not present then make a cart for him

		if (cartDAO.getCartByCustomerId(customerId) == null) {
			cart = new Cart();
			cart.setCustomerId(customerId);
			cartDAO.saveOrUpdate(cart);

			// cartItem.setCartId(cart.getCartId());
		}

		// This statement changes the cart if cart is present and due to
		// unpresence of this there where errors
		else {
			cart = cartDAO.getCartByCustomerId(customerId);
		}

		String cartId = cart.getCartId();

		// 3.get the product price

		product = productDAO.get(productId);

		// If cart is present then go into the cartItem table and search for
		// product
		// this customer selected whether it exists or it is a new product.
		// -------------
		// passing the customerId and productId to a method name returnCartItem
		// through a method

		// if we get null means the product is not present

		if (addCartItem(customerId, productId, cartId, model) == null) {
			cartItem = new CartItem();
			cartItem.setCartId(cartId);
			cartItem.setCustomerId(customerId);
			cartItem.setProductId(product.getProductId());
			cartItem.setQuantity(1);
			cartItem.setTotalPrice(product.getPrice());
			cartItemDAO.saveOrUpdate(cartItem);
			System.out.println("Insertion of cartItem");
			updateCartAgain(cartId, customerId);

		}
		httpSession.setAttribute("noOfProducts", returnNoOfProducts(userName));
		// Now navigate to the same page
		return "redirect:/productDetail/{productId}?addToCartSuccessMessage";
	}

	// This function will update the cart
	public int updateCartAgain(String cartId, String customerId) {

		List<CartItem> listOfSelectedCartItems;
		// Now after getting the cartItem change grandTotal and No of Products
		listOfSelectedCartItems = cartItemDAO.getCartItemsByCustomerId(customerId);
		double grandTotal = 0;
		for (CartItem item : listOfSelectedCartItems) {
			grandTotal = grandTotal + item.getTotalPrice();
		}
		cart.setGrandTotal(grandTotal);

		int noOfProducts = listOfSelectedCartItems.size();

		cart.setCartId(cartId);
		cart.setCustomerId(customerId);
		cart.setNoOfProducts(noOfProducts);
		cartDAO.saveOrUpdate(cart); // This method updates the cart

		return noOfProducts;
		// =========== Completed Adding the item to cart =====

	}

	// This is the method which perform all the operations related to addition
	// of product cartItem
	public String addCartItem(String customerId, String productId, String cartId, Model model) {
		List<CartItem> listOfSelectedCartItems = cartItemDAO.getCartItemsByCustomerId(customerId);
		Product product = productDAO.get(productId);
		for (CartItem item : listOfSelectedCartItems) {
			String itemProductId = item.getProductId();
			System.out.println(itemProductId);
			if (itemProductId.equals(product.getProductId())) {
				System.out.println(item.getCartItemId());
				item.setCartItemId(item.getCartItemId());

				System.out.println(item.getQuantity());
				if (product.getQuantity() <= item.getQuantity()) {
					return "redirect:/productDetail/{productId}?cancelledAddToCart";
				} else {
					item.setQuantity(item.getQuantity() + 1);

					System.out.println(item.getTotalPrice());
					item.setTotalPrice(item.getTotalPrice() + product.getPrice());

					System.out.println(item.toString());
					cartItemDAO.saveOrUpdate(item);
					updateCartAgain(cartId, customerId);

					return "redirect:/productDetail/{productId}?addToCartSuccessMessage";
				}
			}

		}

		return null;
	}

	// To remove the cart items one by one from the cart
	@RequestMapping("/remove/{cartItemId}")
	public String removeCartItems(@PathVariable("cartItemId") String cartItemId, Model model, Principal username) {
		cartItem = cartItemDAO.getCartItem(cartItemId);
		String customerId = cartItem.getCustomerId();
		String cartId = cartItem.getCartId();
		cartItemDAO.delete(cartItemId);
		int noOfProducts = updateCartAgain(cartId, customerId);
		model.addAttribute("noOfProducts", noOfProducts);
		httpSession.setAttribute("noOfProducts", returnNoOfProducts(username));
		return "redirect:/user/cart/?cartItemRemoved";
	}

	public int returnNoOfProducts(Principal username) {
		if (username != null) {
			int noOfProduct = cartDAO
					.getCartByCustomerId(customerDAO.getCustomerByUserName(username.getName()).getCustomerId())
					.getNoOfProducts();
			return noOfProduct;
		}
		return 0;
	}

	// To get the listOf ordered items
	@RequestMapping("/history")
	public ModelAndView listOrderedItems(Principal principal, Model model) {
		ModelAndView mv = new ModelAndView("index");
		mv.addObject("isViewHistoryclicked", "true");
		customer = customerDAO.getCustomerByUserName(principal.getName());

		List<OrderedItems> listofOrderedItems = cartDAO.listOrderedItems(customer.getCustomerId());
		if (listofOrderedItems != null && !listofOrderedItems.isEmpty()) {
			model.addAttribute("listOfOrderedItems", listofOrderedItems);
		} else {
			model.addAttribute("noProductsinHistory", "No products ordered till now");
		}
		mv.addObject("activeNavMenu", "viewCart");
		return mv;

	}
}
